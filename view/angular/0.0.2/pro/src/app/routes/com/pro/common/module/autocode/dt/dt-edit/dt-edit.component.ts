import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Dt } from '@com/pro/common/module/autocode/dt/dt';
import { DtService } from '@com/pro/common/module/autocode/dt/dt.service';

@Component({
  selector: 'dt-edit',
  templateUrl: './dt-edit.component.html',
  styleUrls: ['./dt-edit.component.css']
})
export class DtEditComponent implements OnInit {
  dtForm: FormGroup;
  dt: Dt = new Dt();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private dtService: DtService
  ) { }

  ngOnInit(): void {
    this.dtForm = this.fb.group({
      dtId: [null, Validators.compose([])],
      dtSql: [null, Validators.compose([Validators.required])],
      dtName: [null, Validators.compose([])],
      dtNameAnnotation: [null, Validators.compose([])],
      dtPrefix: [null, Validators.compose([])],
      initialCaseEntityName: [null, Validators.compose([])],
      initialLowercaseEntityName: [null, Validators.compose([])],
      proPath: [null, Validators.compose([])],
      proAllName: [null, Validators.compose([])],
      proName: [null, Validators.compose([])]
    });
    this.dt.dtId = this.route.snapshot.paramMap.get('dtId');
    if (this.dt.dtId) {
      this.dtService.getByPk(this.dt.dtId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.dt = data['data'][0];
              this.dtForm.patchValue(this.dt);
              this.isSpinning = false;
            } else {
              this.msg.error(data['msg'], { nzDuration: 6000 });
              this.isSpinning = false;
            }
          },
          error => {
            this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
            this.isSpinning = false;
          }
        );
    } else {
      this.isSpinning = false;
    }
  }

  get dtId() {
    return this.dtForm.controls['dtId'];
  }

  get dtSql() {
    return this.dtForm.controls['dtSql'];
  }

  get dtName() {
    return this.dtForm.controls['dtName'];
  }

  get dtNameAnnotation() {
    return this.dtForm.controls['dtNameAnnotation'];
  }

  get dtPrefix() {
    return this.dtForm.controls['dtPrefix'];
  }

  get initialCaseEntityName() {
    return this.dtForm.controls['initialCaseEntityName'];
  }

  get initialLowercaseEntityName() {
    return this.dtForm.controls['initialLowercaseEntityName'];
  }

  get proPath() {
    return this.dtForm.controls['proPath'];
  }

  get proAllName() {
    return this.dtForm.controls['proAllName'];
  }

  get proName() {
    return this.dtForm.controls['proName'];
  }

  submit() {
    for (const i in this.dtForm.controls) {
      this.dtForm.controls[i].markAsDirty();
      this.dtForm.controls[i].updateValueAndValidity();
    }
    if (this.dtForm.invalid) return;
    this.submitting = true;
    let dtId = this.dt.dtId;
    if (dtId && dtId !== '') {
      this.dt = Object.assign(this.dt, this.dtForm.value);
      this.dt.dtId = dtId;
      this.dtService.update(this.dt)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`数据库表信息修改成功.`);
              this.resetForm();
              this.dt = new Dt();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/dt/list' ]);
            } else {
              setTimeout(() => this.submitting = false, 3000);
              this.msg.error(data['msg'], { nzDuration: 10000 });
            }
          },
          error => {
            this.submitting = false;
            this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
          }
        );
    } else {
      this.dtService.save(Object.assign(this.dt, this.dtForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`数据库表信息保存成功.`);
              this.resetForm();
              this.dt = new Dt();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/dt/list' ]);
            } else {
              setTimeout(() => this.submitting = false, 3000);
              this.msg.error(data['msg'], { nzDuration: 10000 });
            }
          },
          error => {
            this.submitting = false;
            this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
          }
        );
    }
  }

  goBack(): void {
    this.location.back();
  }

  resetForm(): void {
    this.dtForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
