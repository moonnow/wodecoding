import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { User } from '@com/pro/common/module/account/user/user';
import { UserService } from '@com/pro/common/module/account/user/user.service';

@Component({
  selector: 'user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  userForm: FormGroup;
  user: User = new User();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      userId: [null, Validators.compose([])],
      name: [null, Validators.compose([Validators.required])],
      idNumber: [null, Validators.compose([Validators.required])],
    });
    this.user.userId = this.route.snapshot.paramMap.get('userId');
    if (this.user.userId) {
      this.userService.getByPk(this.user.userId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.user = data['data'][0];
              this.userForm.patchValue(this.user);
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

  get userId() {
    return this.userForm.controls['userId'];
  }

  get name() {
    return this.userForm.controls['name'];
  }

  get idNumber() {
    return this.userForm.controls['idNumber'];
  }

  submit() {
    for (const i in this.userForm.controls) {
      this.userForm.controls[i].markAsDirty();
      this.userForm.controls[i].updateValueAndValidity();
    }
    if (this.userForm.invalid) return;
    this.submitting = true;
    let userId = this.user.userId;
    if (userId && userId !== '') {
      this.user = Object.assign(this.user, this.userForm.value);
      this.user.userId = userId;
      this.userService.update(this.user)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`用户修改成功.`);
              this.resetForm();
              this.user = new User();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/user/list' ]);
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
      this.userService.save(Object.assign(this.user, this.userForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`用户保存成功.`);
              this.resetForm();
              this.user = new User();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/user/list' ]);
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
    this.userForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
