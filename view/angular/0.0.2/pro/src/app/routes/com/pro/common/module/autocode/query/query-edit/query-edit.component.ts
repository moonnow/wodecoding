import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Query } from '@com/pro/common/module/autocode/query/query';
import { QueryService } from '@com/pro/common/module/autocode/query/query.service';
import { DtService } from '@com/pro/common/module/autocode/dt/dt.service';
import { ColumnsService } from '@com/pro/common/module/autocode/columns/columns.service';
import { ColumnsQuery } from '@com/pro/common/module/autocode/columns/columns-query';

@Component({
  selector: 'query-edit',
  templateUrl: './query-edit.component.html',
  styleUrls: ['./query-edit.component.css']
})
export class QueryEditComponent implements OnInit {
  queryForm: FormGroup;
  query: Query = new Query();
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
    private queryService: QueryService,
    private dtService: DtService,
    private columnsService: ColumnsService
  ) { }

  ngOnInit(): void {
    this.queryForm = this.fb.group({
      queryId: [null, Validators.compose([])],
      queryType: [null, Validators.compose([Validators.required])],
      dtId: [null, Validators.compose([Validators.required])],
      columnsId: [null, Validators.compose([Validators.required])],
      weightOrder: [null, Validators.compose([])],
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
    this.query.queryId = this.route.snapshot.paramMap.get('queryId');
    if (this.query.queryId) {
      this.queryService.getByPk(this.query.queryId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.query = data['data'][0];
              this.queryForm.patchValue(this.query);
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

  get queryId() {
    return this.queryForm.controls['queryId'];
  }

  get queryType() {
    return this.queryForm.controls['queryType'];
  }

  get dtId() {
    return this.queryForm.controls['dtId'];
  }

  get columnsId() {
    return this.queryForm.controls['columnsId'];
  }

  get weightOrder() {
    return this.queryForm.controls['weightOrder'];
  }

  submit() {
    for (const i in this.queryForm.controls) {
      this.queryForm.controls[i].markAsDirty();
      this.queryForm.controls[i].updateValueAndValidity();
    }
    if (this.queryForm.invalid) return;
    this.submitting = true;
    let queryId = this.query.queryId;
    if (queryId && queryId !== '') {
      this.query = Object.assign(this.query, this.queryForm.value);
      this.query.queryId = queryId;
      this.queryService.update(this.query)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`查询修改成功.`);
              this.resetForm();
              this.query = new Query();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/query/list' ]);
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
      this.queryService.save(Object.assign(this.query, this.queryForm.value))
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.submitting = false;
              this.msg.success(`查询保存成功.`);
              this.resetForm();
              this.query = new Query();
              if (!this.continue)
                this.router.navigate([ '/pro/common/module/autocode/query/list' ]);
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
    this.queryForm.reset();
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
