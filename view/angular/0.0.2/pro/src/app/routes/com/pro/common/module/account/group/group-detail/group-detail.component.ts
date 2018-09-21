import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Group } from '@com/pro/common/module/account/group/group';
import { GroupService } from '@com/pro/common/module/account/group/group.service';

@Component({
  selector: 'group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})
export class GroupDetailComponent implements OnInit {
  group: Group = new Group();
  isSpinning: boolean = true;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private groupService: GroupService
  ) { }

  ngOnInit() {
    this.group.groupId = this.route.snapshot.paramMap.get('groupId');
    if (this.group.groupId) {
      this.groupService.getByPk(this.group.groupId)
        .subscribe(
          data => {
            if (data['isSuccess'] && data['statusCode'] === 200) {
              this.group = data['data'][0];
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
