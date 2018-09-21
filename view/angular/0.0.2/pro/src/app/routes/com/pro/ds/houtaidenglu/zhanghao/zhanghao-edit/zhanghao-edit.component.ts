import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Zhanghao } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao';
import { ZhanghaoService } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao.service';

@Component({
  selector: 'zhanghao-edit',
  templateUrl: './zhanghao-edit.component.html',
  styleUrls: ['./zhanghao-edit.component.css']
})
export class ZhanghaoEditComponent implements OnInit {
  zhanghaoForm: FormGroup;
  zhanghao: Zhanghao = new Zhanghao();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private zhanghaoService: ZhanghaoService
  ) { }

  ngOnInit(): void {
    this.zhanghaoForm = this.fb.group({
      zhanghaoId: [null, Validators.compose([])],
      account: [null, Validators.compose([Validators.required])],
      password: [null, Validators.compose([Validators.required])],
      nicheng: [null, Validators.compose([Validators.required])],
    });
    this.zhanghao.zhanghaoId = this.route.snapshot.paramMap.get('zhanghaoId');
    if (this.zhanghao.zhanghaoId) {
      this.zhanghaoService.getByPk(this.zhanghao.zhanghaoId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.zhanghao = data['data'][0];
              this.zhanghaoForm.patchValue(this.zhanghao);
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

  get zhanghaoId() {
    return this.zhanghaoForm.controls['zhanghaoId'];
  }

  get account() {
    return this.zhanghaoForm.controls['account'];
  }

  get password() {
    return this.zhanghaoForm.controls['password'];
  }

  get nicheng() {
    return this.zhanghaoForm.controls['nicheng'];
  }

  submit() {
    for (const i in this.zhanghaoForm.controls) {
      this.zhanghaoForm.controls[i].markAsDirty();
      this.zhanghaoForm.controls[i].updateValueAndValidity();
    }
    if (this.zhanghaoForm.invalid) return;
    this.submitting = true;
    let zhanghaoId = this.zhanghao.zhanghaoId;
    if (zhanghaoId && zhanghaoId !== '') {
      this.zhanghao = Object.assign(this.zhanghao, this.zhanghaoForm.value);
      this.zhanghao.zhanghaoId = zhanghaoId;
      this.zhanghaoService.update(this.zhanghao)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`账号修改成功.`);
              this.resetForm();
              this.zhanghao = new Zhanghao();
              if (!this.continue)
                this.router.navigate([ '/pro/ds/houtaidenglu/zhanghao/list' ]);
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
      this.zhanghaoService.save(Object.assign(this.zhanghao, this.zhanghaoForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`账号保存成功.`);
              this.resetForm();
              this.zhanghao = new Zhanghao();
              if (!this.continue)
                this.router.navigate([ '/pro/ds/houtaidenglu/zhanghao/list' ]);
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
    this.zhanghaoForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
