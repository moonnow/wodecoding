import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Huihua } from '@com/pro/ds/houtaidenglu/huihua/huihua';
import { HuihuaService } from '@com/pro/ds/houtaidenglu/huihua/huihua.service';

@Component({
  selector: 'huihua-detail',
  templateUrl: './huihua-detail.component.html',
  styleUrls: ['./huihua-detail.component.css']
})
export class HuihuaDetailComponent implements OnInit {
  huihua: Huihua = new Huihua();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private huihuaService: HuihuaService
  ) { }

  ngOnInit() {
    this.huihua.huihuaId = this.route.snapshot.paramMap.get('huihuaId');
    if (this.huihua.huihuaId) {
      this.huihuaService.getByPk(this.huihua.huihuaId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.huihua = data['data'][0];
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
