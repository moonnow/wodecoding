import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { SimpleTableColumn, SimpleTableChange, SimpleTableFilter, SimpleTableButton } from '@delon/abc';

import { SortService } from '@com/pro/common/module/autocode/sort/sort.service';

@Component({
  selector: 'sort-list',
  templateUrl: './sort-list.component.html',
  styleUrls: ['./sort-list.component.css']
})
export class SortListComponent implements OnInit {
  dataSet: any[];
  list: any[];
  listLength: number;
  count: number;
  rows: number;
  page: number;
  isSpinning: boolean;
  columns: SimpleTableColumn[] = [
    { title: '排序编号', index: 'sortId', type: 'checkbox', fixed: 'left', width: '1px' },
    { title: '数据库表编号', index: 'dtId', width: '400px' },
    { title: '列编号', index: 'columnsId', width: '400px' },
    { title: '排序规则', index: 'sortRule', width: '100px' },
    {
      title: '操作',
      fixed: 'right',
      width: '180px',
      buttons: [
        {
          text: '编辑',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/autocode/sort/edit/' + record['sortId'] ])
        },
        {
          text: '删除',
          type: 'del',
          click: (record: any) => this.remove(record)
        },
        {
          text: '详情',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/autocode/sort/detail/' + record['sortId'] ])
        }
      ]
    }
  ];

  constructor(
    private msg: NzMessageService,
    private router: Router,
    private sortService: SortService
  ) { }

  ngOnInit() {
    this.getDataSet();
  }

  addSort() {
    this.router.navigate([ '/pro/common/module/autocode/sort/edit' ]);
  }

  upSort() {
    this.router.navigate([ '/pro/common/module/autocode/sort/edit/' + this.list[0]['sortId'] ]);
  }

  delSort() {
    this.batchRemove();
  }

  detailSort() {
    this.router.navigate([ '/pro/common/module/autocode/sort/detail/' + this.list[0]['sortId'] ]);
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
    this.sortService.paging(this.rows, this.page)
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
    this.sortService.remove(record)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`排序删除成功.`);
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
    this.sortService.batchRemove(this.list)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`排序删除成功.`);
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
