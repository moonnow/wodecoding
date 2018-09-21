import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Group } from '@com/pro/common/module/account/group/group';
import { GroupService } from '@com/pro/common/module/account/group/group.service';

@Component({
  selector: 'group-edit',
  templateUrl: './group-edit.component.html',
  styleUrls: ['./group-edit.component.css']
})
export class GroupEditComponent implements OnInit {
  groupForm: FormGroup;
  group: Group = new Group();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private groupService: GroupService
  ) { }

  ngOnInit(): void {
    this.groupForm = this.fb.group({
      groupId: [null, Validators.compose([])],
      name: [null, Validators.compose([Validators.required])],
    });
    this.group.groupId = this.route.snapshot.paramMap.get('groupId');
    if (this.group.groupId) {
      this.groupService.getByPk(this.group.groupId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.group = data['data'][0];
              this.groupForm.patchValue(this.group);
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

  get groupId() {
    return this.groupForm.controls['groupId'];
  }

  get name() {
    return this.groupForm.controls['name'];
  }

  submit() {
    for (const i in this.groupForm.controls) {
      this.groupForm.controls[i].markAsDirty();
      this.groupForm.controls[i].updateValueAndValidity();
    }
    if (this.groupForm.invalid) return;
    this.submitting = true;
    let groupId = this.group.groupId;
    if (groupId && groupId !== '') {
      this.group = Object.assign(this.group, this.groupForm.value);
      this.group.groupId = groupId;
      this.groupService.update(this.group)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`组修改成功.`);
              this.resetForm();
              this.group = new Group();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/group/list' ]);
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
      this.groupService.save(Object.assign(this.group, this.groupForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`组保存成功.`);
              this.resetForm();
              this.group = new Group();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/account/group/list' ]);
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
    this.groupForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
