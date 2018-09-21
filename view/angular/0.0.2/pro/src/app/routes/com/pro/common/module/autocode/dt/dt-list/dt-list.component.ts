import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { SimpleTableColumn, SimpleTableChange, SimpleTableFilter, SimpleTableButton } from '@delon/abc';

import { DtService } from '@com/pro/common/module/autocode/dt/dt.service';
import { CodingService } from '@com/pro/common/module/autocode/coding/coding.service';

@Component({
  selector: 'dt-list',
  templateUrl: './dt-list.component.html',
  styleUrls: ['./dt-list.component.css']
})
export class DtListComponent implements OnInit {
  dataSet: any[];
  list: any[];
  listLength: number;
  count: number;
  rows: number;
  page: number;
  isSpinning: boolean;
  columns: SimpleTableColumn[] = [
    { title: '数据库表编号', index: 'dtId', type: 'checkbox', fixed: 'left', width: '1px' },
    // { title: '数据库表sql', index: 'dtSql', width: '100px' },
    { title: '表名', index: 'dtName', width: '200px' },
    { title: '表名注释', index: 'dtNameAnnotation', width: '200px' },
    { title: '数据库表前缀', index: 'dtPrefix', width: '200px' },
    { title: '首字母大写实体类名', index: 'initialCaseEntityName', width: '200px' },
    { title: '首字母小写实体类名', index: 'initialLowercaseEntityName', width: '200px' },
    { title: '项目路径', index: 'proPath', width: '800px' },
    { title: '项目全称', index: 'proAllName', width: '500px' },
    { title: '项目名称', index: 'proName', width: '200px' },
    {
      title: '操作',
      fixed: 'right',
      width: '250px',
      buttons: [
        {
          text: '编辑',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/autocode/dt/edit/' + record['dtId'] ])
        },
        {
          text: '删除',
          type: 'del',
          click: (record: any) => this.remove(record)
        },
        {
          text: '详情',
          click: (record: any, modal: any) => this.router.navigate([ '/pro/common/module/autocode/dt/detail/' + record['dtId'] ])
        },
        {
          text: '更多',
          children: [
            {
              text: `提取`,
              click: (record: any) => this.extract(record),
              format: (record: any) => `<i class="anticon anticon-tool"></i> 提取`
            }
          ]
        }
      ]
    }
  ];

  constructor(
    private msg: NzMessageService,
    private router: Router,
    private dtService: DtService,
    private codingService: CodingService
  ) { }

  ngOnInit() {
    this.getDataSet();
  }

  addDt() {
    this.router.navigate([ '/pro/common/module/autocode/dt/edit' ]);
  }

  upDt() {
    this.router.navigate([ '/pro/common/module/autocode/dt/edit/' + this.list[0]['dtId'] ]);
  }

  delDt() {
    this.batchRemove();
  }

  detailDt() {
    this.router.navigate([ '/pro/common/module/autocode/dt/detail/' + this.list[0]['dtId'] ]);
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
    this.dtService.paging(this.rows, this.page)
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
    this.dtService.remove(record)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`数据库表信息删除成功.`);
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
    this.dtService.batchRemove(this.list)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`数据库表信息删除成功.`);
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

  extract(record) {
    this.isSpinning = true;
    this.codingService.extract(record['dtId'])
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`数据库表信息提取成功.`);
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
}
