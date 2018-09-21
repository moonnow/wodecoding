import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Dt } from '@com/pro/code/plugin/dt/dt';
import { DtService } from '@com/pro/code/plugin/dt/dt.service';

@Component({
  selector: 'dt-detail',
  templateUrl: './dt-detail.component.html',
  styleUrls: ['./dt-detail.component.css']
})
export class DtDetailComponent implements OnInit {
  dt: Dt = new Dt();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private dtService: DtService
  ) { }

  ngOnInit() {
    this.dt.dtId = this.route.snapshot.paramMap.get('dtId');
    if (this.dt.dtId) {
      this.dtService.getByPk(this.dt.dtId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.dt = data['data'][0];
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
