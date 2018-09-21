import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Sort } from '@com/pro/common/module/autocode/sort/sort';
import { SortService } from '@com/pro/common/module/autocode/sort/sort.service';
import { DtService } from '@com/pro/common/module/autocode/dt/dt.service';
import { ColumnsService } from '@com/pro/common/module/autocode/columns/columns.service';
import { ColumnsQuery } from '@com/pro/common/module/autocode/columns/columns-query';

@Component({
  selector: 'sort-edit',
  templateUrl: './sort-edit.component.html',
  styleUrls: ['./sort-edit.component.css']
})
export class SortEditComponent implements OnInit {
  sortForm: FormGroup;
  sort: Sort = new Sort();
  submitting = false;
  continue: boolean;
  isSpinning: boolean = true;
  dtDataSet: any[];
  columnsDataSet: any[];
  columnsQuery: ColumnsQuery = new ColumnsQuery();

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private sortService: SortService,
    private dtService: DtService,
    private columnsService: ColumnsService
  ) { }

  ngOnInit(): void {
    this.sortForm = this.fb.group({
      sortId: [null, Validators.compose([])],
      sortRule: [null, Validators.compose([Validators.required])],
      dtId: [null, Validators.compose([Validators.required])],
      columnsId: [null, Validators.compose([Validators.required])],
    });
    this.dtService.getAll()
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.dtDataSet = data['data'];
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
    this.sort.sortId = this.route.snapshot.paramMap.get('sortId');
    if (this.sort.sortId) {
      this.sortService.getByPk(this.sort.sortId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.sort = data['data'][0];
              this.sortForm.patchValue(this.sort);
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

  get sortId() {
    return this.sortForm.controls['sortId'];
  }

  get sortRule() {
    return this.sortForm.controls['sortRule'];
  }

  get dtId() {
    return this.sortForm.controls['dtId'];
  }

  get columnsId() {
    return this.sortForm.controls['columnsId'];
  }

  submit() {
    for (const i in this.sortForm.controls) {
      this.sortForm.controls[i].markAsDirty();
      this.sortForm.controls[i].updateValueAndValidity();
    }
    if (this.sortForm.invalid) return;
    this.submitting = true;
    let sortId = this.sort.sortId;
    if (sortId && sortId !== '') {
      this.sort = Object.assign(this.sort, this.sortForm.value);
      this.sort.sortId = sortId;
      this.sortService.update(this.sort)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`排序修改成功.`);
              this.resetForm();
              this.sort = new Sort();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/sort/list' ]);
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
      this.sortService.save(Object.assign(this.sort, this.sortForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`排序保存成功.`);
              this.resetForm();
              this.sort = new Sort();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/sort/list' ]);
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
    this.sortForm.reset();
  }

  isContinue(): void {
    this.continue = true;
  }

  isNotContinue(): void {
    this.continue = false;
  }

  ngModelChangeDtId(primaryKey: any | any[]): void {
    this.isSpinning = true;
    this.columnsQuery.dtId = primaryKey;
    this.columnsService.query(this.columnsQuery)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.columnsDataSet = data['data'];
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
  }

  dtDisplay(dtName: string, dtNameAnnotation: string): any {
    return dtName + '  [' + dtNameAnnotation + ']';
  }

  columnsDisplay(columnName: string, columnNameAnnotation: string): any {
    return columnName + '  [' + columnNameAnnotation + ']';
  }
}
