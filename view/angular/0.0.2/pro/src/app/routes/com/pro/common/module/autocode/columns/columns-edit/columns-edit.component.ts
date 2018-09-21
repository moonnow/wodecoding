import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Columns } from '@com/pro/common/module/autocode/columns/columns';
import { ColumnsService } from '@com/pro/common/module/autocode/columns/columns.service';

@Component({
  selector: 'columns-edit',
  templateUrl: './columns-edit.component.html',
  styleUrls: ['./columns-edit.component.css']
})
export class ColumnsEditComponent implements OnInit {
  columnsForm: FormGroup;
  columns: Columns = new Columns();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private columnsService: ColumnsService
  ) { }

  ngOnInit(): void {
    this.columnsForm = this.fb.group({
      columnsId: [null, Validators.compose([])],
      columnName: [null, Validators.compose([])],
      columnNameAnnotation: [null, Validators.compose([])],
      dataType: [null, Validators.compose([])],
      isNull: [null, Validators.compose([])],
      initialCaseColumnName: [null, Validators.compose([])],
      initialLowercaseColumnName: [null, Validators.compose([])],
      weightOrder: [null, Validators.compose([])],
      dtId: [null, Validators.compose([Validators.required])],
    });
    this.columns.columnsId = this.route.snapshot.paramMap.get('columnsId');
    if (this.columns.columnsId) {
      this.columnsService.getByPk(this.columns.columnsId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.columns = data['data'][0];
              this.columnsForm.patchValue(this.columns);
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

  get columnsId() {
    return this.columnsForm.controls['columnsId'];
  }

  get columnName() {
    return this.columnsForm.controls['columnName'];
  }

  get columnNameAnnotation() {
    return this.columnsForm.controls['columnNameAnnotation'];
  }

  get dataType() {
    return this.columnsForm.controls['dataType'];
  }

  get isNull() {
    return this.columnsForm.controls['isNull'];
  }

  get initialCaseColumnName() {
    return this.columnsForm.controls['initialCaseColumnName'];
  }

  get initialLowercaseColumnName() {
    return this.columnsForm.controls['initialLowercaseColumnName'];
  }

  get weightOrder() {
    return this.columnsForm.controls['weightOrder'];
  }

  get dtId() {
    return this.columnsForm.controls['dtId'];
  }

  submit() {
    for (const i in this.columnsForm.controls) {
      this.columnsForm.controls[i].markAsDirty();
      this.columnsForm.controls[i].updateValueAndValidity();
    }
    if (this.columnsForm.invalid) return;
    this.submitting = true;
    let columnsId = this.columns.columnsId;
    if (columnsId && columnsId !== '') {
      this.columns = Object.assign(this.columns, this.columnsForm.value);
      this.columns.columnsId = columnsId;
      this.columnsService.update(this.columns)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`列修改成功.`);
              this.resetForm();
              this.columns = new Columns();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/columns/list' ]);
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
      this.columnsService.save(Object.assign(this.columns, this.columnsForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`列保存成功.`);
              this.resetForm();
              this.columns = new Columns();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/columns/list' ]);
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
    this.columnsForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }
}
