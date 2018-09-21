import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Columns } from '@com/pro/common/module/autocode/columns/columns';
import { ColumnsService } from '@com/pro/common/module/autocode/columns/columns.service';

@Component({
  selector: 'columns-detail',
  templateUrl: './columns-detail.component.html',
  styleUrls: ['./columns-detail.component.css']
})
export class ColumnsDetailComponent implements OnInit {
  columns: Columns = new Columns();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private columnsService: ColumnsService
  ) { }

  ngOnInit() {
    this.columns.columnsId = this.route.snapshot.paramMap.get('columnsId');
    if (this.columns.columnsId) {
      this.columnsService.getByPk(this.columns.columnsId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.columns = data['data'][0];
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

  goBack(): void {
    this.location.back();
  }
}
