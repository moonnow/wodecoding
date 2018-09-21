import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Account } from '@com/pro/common/module/account/account/account';
import { AccountService } from '@com/pro/common/module/account/account/account.service';
import { UserService } from '@com/pro/common/module/account/user/user.service';

@Component({
  selector: 'account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {
  accountForm: FormGroup;
  accounts: Account = new Account();
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
    private accountService: AccountService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.accountForm = this.fb.group({
      accountId: [null, Validators.compose([])],
      account: [null, Validators.compose([Validators.required])],
      password: [null, Validators.compose([Validators.required])],
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
    this.accounts.accountId = this.route.snapshot.paramMap.get('accountId');
    if (this.accounts.accountId) {
      this.accountService.getByPk(this.accounts.accountId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.accounts = data['data'][0];
              this.accountForm.patchValue(this.accounts);
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

  get accountId() {
    return this.accountForm.controls['accountId'];
  }

  get account() {
    return this.accountForm.controls['account'];
  }

  get password() {
    return this.accountForm.controls['password'];
  }

  get userId() {
    return this.accountForm.controls['userId'];
  }

  submit() {
    for (const i in this.accountForm.controls) {
      this.accountForm.controls[i].markAsDirty();
      this.accountForm.controls[i].updateValueAndValidity();
    }
    if (this.accountForm.invalid) return;
    this.submitting = true;
    let accountId = this.accounts.accountId;
    if (accountId && accountId !== '') {
      this.accounts = Object.assign(this.accounts, this.accountForm.value);
      this.accounts.accountId = accountId;
      this.accountService.update(this.accounts)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`帐号修改成功.`);
              this.resetForm();
              this.accounts = new Account();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/account/list' ]);
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
      this.accountService.save(Object.assign(this.accounts, this.accountForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`帐号保存成功.`);
              this.resetForm();
              this.accounts = new Account();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/account/list' ]);
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
    this.accountForm.reset();
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
