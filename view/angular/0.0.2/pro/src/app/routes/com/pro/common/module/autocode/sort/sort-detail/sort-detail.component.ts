import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Sort } from '@com/pro/common/module/autocode/sort/sort';
import { SortService } from '@com/pro/common/module/autocode/sort/sort.service';

@Component({
  selector: 'sort-detail',
  templateUrl: './sort-detail.component.html',
  styleUrls: ['./sort-detail.component.css']
})
export class SortDetailComponent implements OnInit {
  sort: Sort = new Sort();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private sortService: SortService
  ) { }

  ngOnInit() {
    this.sort.sortId = this.route.snapshot.paramMap.get('sortId');
    if (this.sort.sortId) {
      this.sortService.getByPk(this.sort.sortId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.sort = data['data'][0];
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
