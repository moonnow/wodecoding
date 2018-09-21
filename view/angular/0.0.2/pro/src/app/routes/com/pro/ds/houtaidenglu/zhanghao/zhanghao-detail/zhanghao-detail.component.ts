import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Zhanghao } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao';
import { ZhanghaoService } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao.service';

@Component({
  selector: 'zhanghao-detail',
  templateUrl: './zhanghao-detail.component.html',
  styleUrls: ['./zhanghao-detail.component.css']
})
export class ZhanghaoDetailComponent implements OnInit {
  zhanghao: Zhanghao = new Zhanghao();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private zhanghaoService: ZhanghaoService
  ) { }

  ngOnInit() {
    this.zhanghao.zhanghaoId = this.route.snapshot.paramMap.get('zhanghaoId');
    if (this.zhanghao.zhanghaoId) {
      this.zhanghaoService.getByPk(this.zhanghao.zhanghaoId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.zhanghao = data['data'][0];
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
