import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { SimpleTableColumn, SimpleTableChange, SimpleTableFilter, SimpleTableButton } from '@delon/abc';

import { ColumnsService } from '@com/pro/common/module/autocode/columns/columns.service';

@Component({
  selector: 'columns-list',
  templateUrl: './columns-list.component.html',
  styleUrls: ['./columns-list.component.css']
})
export class ColumnsListComponent implements OnInit {
  dataSet: any[];
  list: any[];
  listLength: number;
  count: number;
  rows: number;
  page: number;
  isSpinning: boolean;
  columns: SimpleTableColumn[] = [
    { title: '列编号', index: 'columnsId', type: 'checkbox', fixed: 'left', width: '1px' },
    { title: '列名', index: 'columnName', width: '300px' },
    { title: '列名注释', index: 'columnNameAnnotation', width: '200px' },
    { title: '数据类型', index: 'dataType', width: '200px' },
    { title: '是否为空', index: 'isNull', width: '100px' },
    { title: '首字母大写列名', index: 'initialCaseColumnName', width: '200px' },
    { title: '首字母小写列名', index: 'initialLowercaseColumnName', width: '200px' },
    { title: '排序权重', index: 'weightOrder', width: '100px' },
    { title: '数据库表编号', index: 'dtId', width: '400px' },
    {
      title: '操作',
      fixed: 'right',
      width: '180px',
      buttons: [
        {
          text: '编辑',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/autocode/columns/edit/' + record['columnsId'] ])
        },
        {
          text: '删除',
          type: 'del',
          click: (record: any) => this.remove(record)
        },
        {
          text: '详情',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/autocode/columns/detail/' + record['columnsId'] ])
        }
      ]
    }
  ];

  constructor(
    private msg: NzMessageService,
    private router: Router,
    private columnsService: ColumnsService
  ) { }

  ngOnInit() {
    this.getDataSet();
  }

  addColumns() {
    this.router.navigate([ '/pro/common/module/autocode/columns/edit' ]);
  }

  upColumns() {
    this.router.navigate([ '/pro/common/module/autocode/columns/edit/' + this.list[0]['columnsId'] ]);
  }

  delColumns() {
    this.batchRemove();
  }

  detailColumns() {
    this.router.navigate([ '/pro/common/module/autocode/columns/detail/' + this.list[0]['columnsId'] ]);
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
    this.columnsService.paging(this.rows, this.page)
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
    this.columnsService.remove(record)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`列删除成功.`);
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
    this.columnsService.batchRemove(this.list)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`列删除成功.`);
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
