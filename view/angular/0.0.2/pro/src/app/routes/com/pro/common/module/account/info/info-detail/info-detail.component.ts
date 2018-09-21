import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Info } from '@com/pro/common/module/account/info/info';
import { InfoService } from '@com/pro/common/module/account/info/info.service';

@Component({
  selector: 'info-detail',
  templateUrl: './info-detail.component.html',
  styleUrls: ['./info-detail.component.css']
})
export class InfoDetailComponent implements OnInit {
  info: Info = new Info();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private infoService: InfoService
  ) { }

  ngOnInit() {
    this.info.infoId = this.route.snapshot.paramMap.get('infoId');
    if (this.info.infoId) {
      this.infoService.getByPk(this.info.infoId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.info = data['data'][0];
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
