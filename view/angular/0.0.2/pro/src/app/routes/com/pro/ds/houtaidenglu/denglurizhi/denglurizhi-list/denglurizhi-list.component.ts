import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { SimpleTableColumn, SimpleTableChange, SimpleTableFilter, SimpleTableButton } from '@delon/abc';

import { DenglurizhiService } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi.service';

@Component({
  selector: 'denglurizhi-list',
  templateUrl: './denglurizhi-list.component.html',
  styleUrls: ['./denglurizhi-list.component.css']
})
export class DenglurizhiListComponent implements OnInit {
  dataSet: any[];
  list: any[];
  listLength: number;
  count: number;
  rows: number;
  page: number;
  isSpinning: boolean;
  columns: SimpleTableColumn[] = [
    { title: '登录日志编号', index: 'denglurizhiId', type: 'checkbox', fixed: 'left', width: '1px' },
    { title: '账号编号', index: 'zhanghaoId', width: '100px' },
    { title: '帐号', index: 'account', width: '100px' },
    { title: '昵称', index: 'nicheng', width: '100px' },
    { title: '登录时间', index: 'denglushijian', width: '100px' },
    { title: '登录信息', index: 'dengluxinxi', width: '100px' },
    {
      title: '操作',
      fixed: 'right',
      width: '180px',
      buttons: [
        {
          text: '编辑',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/edit/' + record['denglurizhiId'] ])
        },
        {
          text: '删除',
          type: 'del',
          click: (record: any) => this.remove(record)
        },
        {
          text: '详情',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/detail/' + record['denglurizhiId'] ])
        }
      ]
    }
  ];

  constructor(
    private msg: NzMessageService,
    private router: Router,
    private denglurizhiService: DenglurizhiService
  ) { }

  ngOnInit() {
    this.getDataSet();
  }

  addDenglurizhi() {
    this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/edit' ]);
  }

  upDenglurizhi() {
    this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/edit/' + this.list[0]['denglurizhiId'] ]);
  }

  delDenglurizhi() {
    this.batchRemove();
  }

  detailDenglurizhi() {
    this.router.navigate([ '/pro/ds/houtaidenglu/denglurizhi/detail/' + this.list[0]['denglurizhiId'] ]);
  }

  checkboxChange(list: any[]) {
    this.listLength = list.length;
    this.list = list;
  }

  pageIndexChange(nzPageIndex) {
    this.page = nzPageIndex;
    this.getDataSet();
  }

  pageSizeChange(nzPageSize) {
    this.rows = nzPageSize;
    this.page = 1;
    this.getDataSet();
  }

  getDataSet() {
    this.isSpinning = true;
    this.denglurizhiService.paging(this.rows, this.page)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.dataSet = data['data'];
            this.count = data['count'];
            this.rows = data['rows'];
            this.page = data['page'];
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

  remove(record) {
    this.isSpinning = true;
    this.denglurizhiService.remove(record)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`登录日志删除成功.`);
            this.getDataSet();
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

  batchRemove() {
    this.isSpinning = true;
    this.denglurizhiService.batchRemove(this.list)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`登录日志删除成功.`);
            this.getDataSet();
            this.list = Array.of();
            this.listLength = this.list.length;
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
}
