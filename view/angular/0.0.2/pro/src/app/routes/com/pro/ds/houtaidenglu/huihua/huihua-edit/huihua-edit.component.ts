import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Huihua } from '@com/pro/ds/houtaidenglu/huihua/huihua';
import { HuihuaService } from '@com/pro/ds/houtaidenglu/huihua/huihua.service';

@Component({
  selector: 'huihua-edit',
  templateUrl: './huihua-edit.component.html',
  styleUrls: ['./huihua-edit.component.css']
})
export class HuihuaEditComponent implements OnInit {
  huihuaForm: FormGroup;
  huihua: Huihua = new Huihua();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private huihuaService: HuihuaService
  ) { }

  ngOnInit(): void {
    this.huihuaForm = this.fb.group({
      huihuaId: [null, Validators.compose([])],
      zhanghaoId: [null, Validators.compose([Validators.required])],
      account: [null, Validators.compose([Validators.required])],
      nicheng: [null, Validators.compose([Validators.required])],
    });
    this.huihua.huihuaId = this.route.snapshot.paramMap.get('huihuaId');
    if (this.huihua.huihuaId) {
      this.huihuaService.getByPk(this.huihua.huihuaId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.huihua = data['data'][0];
              this.huihuaForm.patchValue(this.huihua);
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

  get huihuaId() {
    return this.huihuaForm.controls['huihuaId'];
  }

  get zhanghaoId() {
    return this.huihuaForm.controls['zhanghaoId'];
  }

  get account() {
    return this.huihuaForm.controls['account'];
  }

  get nicheng() {
    return this.huihuaForm.controls['nicheng'];
  }

  submit() {
    for (const i in this.huihuaForm.controls) {
      this.huihuaForm.controls[i].markAsDirty();
      this.huihuaForm.controls[i].updateValueAndValidity();
    }
    if (this.huihuaForm.invalid) return;
    this.submitting = true;
    let huihuaId = this.huihua.huihuaId;
    if (huihuaId && huihuaId !== '') {
      this.huihua = Object.assign(this.huihua, this.huihuaForm.value);
      this.huihua.huihuaId = huihuaId;
      this.huihuaService.update(this.huihua)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`会话修改成功.`);
              this.resetForm();
              this.huihua = new Huihua();
              if (!this.continue)
                this.router.navigate([ '/pro/ds/houtaidenglu/huihua/list' ]);
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
      this.huihuaService.save(Object.assign(this.huihua, this.huihuaForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`会话保存成功.`);
              this.resetForm();
              this.huihua = new Huihua();
              if (!this.continue)
                this.router.navigate([ '/pro/ds/houtaidenglu/huihua/list' ]);
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
    this.huihuaForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
