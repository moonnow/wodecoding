import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Denglurizhi } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi';
import { DenglurizhiService } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi.service';

@Component({
  selector: 'denglurizhi-edit',
  templateUrl: './denglurizhi-edit.component.html',
  styleUrls: ['./denglurizhi-edit.component.css']
})
export class DenglurizhiEditComponent implements OnInit {
  denglurizhiForm: FormGroup;
  denglurizhi: Denglurizhi = new Denglurizhi();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private denglurizhiService: DenglurizhiService
  ) { }

  ngOnInit(): void {
    this.denglurizhiForm = this.fb.group({
      denglurizhiId: [null, Validators.compose([])],
      zhanghaoId: [null, Validators.compose([Validators.required])],
      account: [null, Validators.compose([Validators.required])],
      nicheng: [null, Validators.compose([Validators.required])],
      denglushijian: [null, Validators.compose([Validators.required])],
      dengluxinxi: [null, Validators.compose([])],
    });
    this.denglurizhi.denglurizhiId = this.route.snapshot.paramMap.get('denglurizhiId');
    if (this.denglurizhi.denglurizhiId) {
      this.denglurizhiService.getByPk(this.denglurizhi.denglurizhiId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.denglurizhi = data['data'][0];
              this.denglurizhiForm.patchValue(this.denglurizhi);
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

  get denglurizhiId() {
    return this.denglurizhiForm.controls['denglurizhiId'];
  }

  get zhanghaoId() {
    return this.denglurizhiForm.controls['zhanghaoId'];
  }

  get account() {
    return this.denglurizhiForm.controls['account'];
  }

  get nicheng() {
    return this.denglurizhiForm.controls['nicheng'];
  }

  get denglushijian() {
    return this.denglurizhiForm.controls['denglushijian'];
  }

  get dengluxinxi() {
    return this.denglurizhiForm.controls['dengluxinxi'];
  }

  submit() {
    for (const i in this.denglurizhiForm.controls) {
      this.denglurizhiForm.controls[i].markAsDirty();
      this.denglurizhiForm.controls[i].updateValueAndValidity();
    }
    if (this.denglurizhiForm.invalid) return;
    this.submitting = true;
    let denglurizhiId = this.denglurizhi.denglurizhiId;
    if (denglurizhiId && denglurizhiId !== '') {
      this.denglurizhi = Object.assign(this.denglurizhi, this.denglurizhiForm.value);
      this.denglurizhi.denglurizhiId = denglurizhiId;
      this.denglurizhiService.update(this.denglurizhi)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`登录日志修改成功.`);
              this.resetForm();
              this.denglurizhi = new Denglurizhi();
              if (!this.continue)
                this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/list' ]);
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
      this.denglurizhiService.save(Object.assign(this.denglurizhi, this.denglurizhiForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`登录日志保存成功.`);
              this.resetForm();
              this.denglurizhi = new Denglurizhi();
              if (!this.continue)
                this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/list' ]);
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
    this.denglurizhiForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
