import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Info } from '@com/pro/common/module/account/info/info';
import { InfoService } from '@com/pro/common/module/account/info/info.service';
import { UserService } from '@com/pro/common/module/account/user/user.service';

@Component({
  selector: 'info-edit',
  templateUrl: './info-edit.component.html',
  styleUrls: ['./info-edit.component.css']
})
export class InfoEditComponent implements OnInit {
  infoForm: FormGroup;
  info: Info = new Info();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;
  userDataSet: any[];

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private infoService: InfoService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.infoForm = this.fb.group({
      infoId: [null, Validators.compose([])],
      cellphone: [null, Validators.compose([])],
      email: [null, Validators.compose([])],
      userId: [null, Validators.compose([])],
    });
    this.userService.getAll()
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.userDataSet = data['data'];
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
    this.info.infoId = this.route.snapshot.paramMap.get('infoId');
    if (this.info.infoId) {
      this.infoService.getByPk(this.info.infoId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.info = data['data'][0];
              this.infoForm.patchValue(this.info);
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

  get infoId() {
    return this.infoForm.controls['infoId'];
  }

  get cellphone() {
    return this.infoForm.controls['cellphone'];
  }

  get email() {
    return this.infoForm.controls['email'];
  }

  get userId() {
    return this.infoForm.controls['userId'];
  }

  submit() {
    for (const i in this.infoForm.controls) {
      this.infoForm.controls[i].markAsDirty();
      this.infoForm.controls[i].updateValueAndValidity();
    }
    if (this.infoForm.invalid) return;
    this.submitting = true;
    let infoId = this.info.infoId;
    if (infoId && infoId !== '') {
      this.info = Object.assign(this.info, this.infoForm.value);
      this.info.infoId = infoId;
      this.infoService.update(this.info)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`用户信息修改成功.`);
              this.resetForm();
              this.info = new Info();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/info/list' ]);
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
      this.infoService.save(Object.assign(this.info, this.infoForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`用户信息保存成功.`);
              this.resetForm();
              this.info = new Info();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/info/list' ]);
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
    this.infoForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }

  userDisplay(name: string, idNumber: string): any {
    return name + '  [' + idNumber + ']';
  }
}
