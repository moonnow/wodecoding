import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Denglurizhi } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi';
import { DenglurizhiService } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi.service';

@Component({
  selector: 'denglurizhi-detail',
  templateUrl: './denglurizhi-detail.component.html',
  styleUrls: ['./denglurizhi-detail.component.css']
})
export class DenglurizhiDetailComponent implements OnInit {
  denglurizhi: Denglurizhi = new Denglurizhi();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private denglurizhiService: DenglurizhiService
  ) { }

  ngOnInit() {
    this.denglurizhi.denglurizhiId = this.route.snapshot.paramMap.get('denglurizhiId');
    if (this.denglurizhi.denglurizhiId) {
      this.denglurizhiService.getByPk(this.denglurizhi.denglurizhiId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.denglurizhi = data['data'][0];
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
