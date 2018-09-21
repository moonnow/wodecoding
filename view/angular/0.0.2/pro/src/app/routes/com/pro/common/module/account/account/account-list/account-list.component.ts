import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { SimpleTableColumn, SimpleTableChange, SimpleTableFilter, SimpleTableButton } from '@delon/abc';

import { AccountService } from '@com/pro/common/module/account/account/account.service';

@Component({
  selector: 'account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {
  dataSet: any[];
  list: any[];
  listLength: number;
  count: number;
  rows: number;
  page: number;
  isSpinning: boolean;
  columns: SimpleTableColumn[] = [
    { title: '帐号编号', index: 'accountId', type: 'checkbox', fixed: 'left', width: '1px' },
    { title: '帐号', index: 'account', width: '100px' },
    { title: '密码', index: 'password', width: '100px' },
    { title: '用户编号', index: 'userId', width: '100px' },
    {
      title: '操作',
      fixed: 'right',
      width: '180px',
      buttons: [
        {
          text: '编辑',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/account/account/edit/' + record['accountId'] ])
        },
        {
          text: '删除',
          type: 'del',
          click: (record: any) => this.remove(record)
        },
        {
          text: '详情',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/account/account/detail/' + record['accountId'] ])
        }
      ]
    }
  ];

  constructor(
    private msg: NzMessageService,
    private router: Router,
    private accountService: AccountService
  ) { }

  ngOnInit() {
    this.getDataSet();
  }

  addAccount() {
    this.router.navigate([ '/pro/common/module/account/account/edit' ]);
  }

  upAccount() {
    this.router.navigate([ '/pro/common/module/account/account/edit/' + this.list[0]['accountId'] ]);
  }

  delAccount() {
    this.batchRemove();
  }

  detailAccount() {
    this.router.navigate([ '/pro/common/module/account/account/detail/' + this.list[0]['accountId'] ]);
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
    this.accountService.paging(this.rows, this.page)
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
    this.accountService.remove(record)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`帐号删除成功.`);
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
    this.accountService.batchRemove(this.list)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`帐号删除成功.`);
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
