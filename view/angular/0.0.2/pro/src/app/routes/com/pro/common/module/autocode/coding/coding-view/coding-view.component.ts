import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { DtService } from '@com/pro/common/module/autocode/dt/dt.service';
import { CodingService } from '@com/pro/common/module/autocode/coding/coding.service';
import { Config } from '@com/pro/common/module/autocode/coding/config';

@Component({
  selector: 'coding-view',
  templateUrl: './coding-view.component.html',
  styleUrls: ['./coding-view.component.css']
})
export class CodingViewComponent implements OnInit {
  selectedDtPrimaryKey: string;
  dtDataSet: any[];
  config: Config = new Config();
  isSpinning: boolean;
  jdbcEntityFilePathInputIsDisabled: boolean;
  hibernateEntityFilePathInputIsDisabled: boolean;
  exceptionFilePathInputIsDisabled: boolean;
  iPersistentFilePathInputIsDisabled: boolean;
  jdbcPersistentImplFilePathInputIsDisabled: boolean;
  iServiceFilePathInputIsDisabled: boolean;
  serviceImplFilePathInputIsDisabled: boolean;
  controllerFilePathInputIsDisabled: boolean;
  angularModuleFilePathInputIsDisabled: boolean;
  angularRoutingFilePathInputIsDisabled: boolean;
  angularEntityFilePathInputIsDisabled: boolean;
  angularServiceFilePathInputIsDisabled: boolean;
  angularListComponentFilePathInputIsDisabled: boolean;
  angularListHtmlFilePathInputIsDisabled: boolean;
  angularListCssFilePathInputIsDisabled: boolean;
  angularEditComponentFilePathInputIsDisabled: boolean;
  angularEditHtmlFilePathInputIsDisabled: boolean;
  angularEditCssFilePathInputIsDisabled: boolean;
  angularDetailComponentFilePathInputIsDisabled: boolean;
  angularDetailHtmlFilePathInputIsDisabled: boolean;
  angularDetailCssFilePathInputIsDisabled: boolean;
  queryFilePathInputIsDisabled: boolean;
  angularQueryFilePathInputIsDisabled: boolean;
  projectPathInputIsDisabled: boolean;

  constructor(
    private msg: NzMessageService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private dtService: DtService,
    private codingService: CodingService
  ) { }

  ngOnInit(): void {
    this.jdbcEntityFilePathInputIsDisabled = true;
    this.hibernateEntityFilePathInputIsDisabled = true;
    this.exceptionFilePathInputIsDisabled = true;
    this.iPersistentFilePathInputIsDisabled = true;
    this.jdbcPersistentImplFilePathInputIsDisabled = true;
    this.iServiceFilePathInputIsDisabled = true;
    this.serviceImplFilePathInputIsDisabled = true;
    this.controllerFilePathInputIsDisabled = true;
    this.angularModuleFilePathInputIsDisabled = true;
    this.angularRoutingFilePathInputIsDisabled = true;
    this.angularEntityFilePathInputIsDisabled = true;
    this.angularServiceFilePathInputIsDisabled = true;
    this.angularListComponentFilePathInputIsDisabled = true;
    this.angularListHtmlFilePathInputIsDisabled = true;
    this.angularListCssFilePathInputIsDisabled = true;
    this.angularEditComponentFilePathInputIsDisabled = true;
    this.angularEditHtmlFilePathInputIsDisabled = true;
    this.angularEditCssFilePathInputIsDisabled = true;
    this.angularDetailComponentFilePathInputIsDisabled = true;
    this.angularDetailHtmlFilePathInputIsDisabled = true;
    this.angularDetailCssFilePathInputIsDisabled = true;
    this.queryFilePathInputIsDisabled = true;
    this.angularQueryFilePathInputIsDisabled = true;
    this.projectPathInputIsDisabled = true;
    this.dtService.getAll()
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.dtDataSet = data['data'];
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  ngModelChangeDt(primaryKey: any | any[]): void {
    this.isSpinning = true;
    this.codingService.getConfig(primaryKey)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`配置信息获取成功.`);
            this.config = data['data'][0];
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

  upJdbcEntityFilePath(): void {
    this.jdbcEntityFilePathInputIsDisabled = false;
  }

  saveJdbcEntityFilePath(): void {
    this.jdbcEntityFilePathInputIsDisabled = true;
  }

  codingJdbcEntity(): void {
    this.codingService.codingJdbcEntity(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Jdbc实体类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upHibernateEntityFilePath(): void {
    this.hibernateEntityFilePathInputIsDisabled = false;
  }

  saveHibernateEntityFilePath(): void {
    this.hibernateEntityFilePathInputIsDisabled = true;
  }

  codingHibernateEntity(): void {
    this.codingService.codingJdbcEntity(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Jdbc实体类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upExceptionFilePath(): void {
    this.exceptionFilePathInputIsDisabled = false;
  }

  saveExceptionFilePath(): void {
    this.exceptionFilePathInputIsDisabled = true;
  }

  codingException(): void {
    this.codingService.codingException(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`异常类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upIPersistentFilePath(): void {
    this.iPersistentFilePathInputIsDisabled = false;
  }

  saveIPersistentFilePath(): void {
    this.iPersistentFilePathInputIsDisabled = true;
  }

  codingIPersistent(): void {
    this.codingService.codingIPersistent(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`持久化接口类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upJdbcPersistentImplFilePath(): void {
    this.jdbcPersistentImplFilePathInputIsDisabled = false;
  }

  saveJdbcPersistentImplFilePath(): void {
    this.jdbcPersistentImplFilePathInputIsDisabled = true;
  }

  codingJdbcPersistentImpl(): void {
    this.codingService.codingJdbcPersistentImpl(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Jdbc持久化实现类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upIServiceFilePath(): void {
    this.iServiceFilePathInputIsDisabled = false;
  }

  saveIServiceFilePath(): void {
    this.iServiceFilePathInputIsDisabled = true;
  }

  codingIService(): void {
    this.codingService.codingIService(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`服务接口类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upServiceImplFilePath(): void {
    this.serviceImplFilePathInputIsDisabled = false;
  }

  saveServiceImplFilePath(): void {
    this.serviceImplFilePathInputIsDisabled = true;
  }

  codingServiceImpl(): void {
    this.codingService.codingServiceImpl(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`服务实现类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upControllerFilePath(): void {
    this.controllerFilePathInputIsDisabled = false;
  }

  saveControllerFilePath(): void {
    this.controllerFilePathInputIsDisabled = true;
  }

  codingController(): void {
    this.codingService.codingController(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`控制器类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularModuleFilePath(): void {
    this.angularModuleFilePathInputIsDisabled = false;
  }

  saveAngularModuleFilePath(): void {
    this.angularModuleFilePathInputIsDisabled = true;
  }

  codingAngularModule(): void {
    this.codingService.codingAngularModule(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular模块生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularRoutingFilePath(): void {
    this.angularRoutingFilePathInputIsDisabled = false;
  }

  saveAngularRoutingFilePath(): void {
    this.angularRoutingFilePathInputIsDisabled = true;
  }

  codingAngularRouting(): void {
    this.codingService.codingAngularRouting(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular路由生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularEntityFilePath(): void {
    this.angularEntityFilePathInputIsDisabled = false;
  }

  saveAngularEntityFilePath(): void {
    this.angularEntityFilePathInputIsDisabled = true;
  }

  codingAngularEntity(): void {
    this.codingService.codingAngularEntity(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular实体类生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularServiceFilePath(): void {
    this.angularServiceFilePathInputIsDisabled = false;
  }

  saveAngularServiceFilePath(): void {
    this.angularServiceFilePathInputIsDisabled = true;
  }

  codingAngularService(): void {
    this.codingService.codingAngularService(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular服务生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularListComponentFilePath(): void {
    this.angularListComponentFilePathInputIsDisabled = false;
  }

  saveAngularListComponentFilePath(): void {
    this.angularListComponentFilePathInputIsDisabled = true;
  }

  codingAngularListComponent(): void {
    this.codingService.codingAngularListComponent(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular列表组件生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularListHtmlFilePath(): void {
    this.angularListHtmlFilePathInputIsDisabled = false;
  }

  saveAngularListHtmlFilePath(): void {
    this.angularListHtmlFilePathInputIsDisabled = true;
  }

  codingAngularListHtml(): void {
    this.codingService.codingAngularListHtml(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular列表网页生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularListCssFilePath(): void {
    this.angularListCssFilePathInputIsDisabled = false;
  }

  saveAngularListCssFilePath(): void {
    this.angularListCssFilePathInputIsDisabled = true;
  }

  codingAngularListCss(): void {
    this.codingService.codingAngularListCss(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular列表样式生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularEditComponentFilePath(): void {
    this.angularEditComponentFilePathInputIsDisabled = false;
  }

  saveAngularEditComponentFilePath(): void {
    this.angularEditComponentFilePathInputIsDisabled = true;
  }

  codingAngularEditComponent(): void {
    this.codingService.codingAngularEditComponent(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular编辑组件生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularEditHtmlFilePath(): void {
    this.angularEditHtmlFilePathInputIsDisabled = false;
  }

  saveAngularEditHtmlFilePath(): void {
    this.angularEditHtmlFilePathInputIsDisabled = true;
  }

  codingAngularEditHtml(): void {
    this.codingService.codingAngularEditHtml(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular编辑网页生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularEditCssFilePath(): void {
    this.angularEditCssFilePathInputIsDisabled = false;
  }

  saveAngularEditCssFilePath(): void {
    this.angularEditCssFilePathInputIsDisabled = true;
  }

  codingAngularEditCss(): void {
    this.codingService.codingAngularEditCss(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular编辑样式生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularDetailComponentFilePath(): void {
    this.angularDetailComponentFilePathInputIsDisabled = false;
  }

  saveAngularDetailComponentFilePath(): void {
    this.angularDetailComponentFilePathInputIsDisabled = true;
  }

  codingAngularDetailComponent(): void {
    this.codingService.codingAngularDetailComponent(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular详情组件生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularDetailHtmlFilePath(): void {
    this.angularDetailHtmlFilePathInputIsDisabled = false;
  }

  saveAngularDetailHtmlFilePath(): void {
    this.angularDetailHtmlFilePathInputIsDisabled = true;
  }

  codingAngularDetailHtml(): void {
    this.codingService.codingAngularDetailHtml(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular详情网页生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularDetailCssFilePath(): void {
    this.angularDetailCssFilePathInputIsDisabled = false;
  }

  saveAngularDetailCssFilePath(): void {
    this.angularDetailCssFilePathInputIsDisabled = true;
  }

  codingAngularDetailCss(): void {
    this.codingService.codingAngularDetailCss(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular详情样式生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upQueryFilePath(): void {
    this.queryFilePathInputIsDisabled = false;
  }

  saveQueryFilePath(): void {
    this.queryFilePathInputIsDisabled = true;
  }

  codingQuery(): void {
    this.codingService.codingQuery(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Java查询生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upAngularQueryFilePath(): void {
    this.angularQueryFilePathInputIsDisabled = false;
  }

  saveAngularQueryFilePath(): void {
    this.angularQueryFilePathInputIsDisabled = true;
  }

  codingAngularQuery(): void {
    this.codingService.codingAngularQuery(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`Angular查询生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  upProjectPath(): void {
    this.projectPathInputIsDisabled = false;
  }

  saveProjectPath(): void {
    this.projectPathInputIsDisabled = true;
  }

  codingProject(): void {
    this.codingService.codingProject(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`项目生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }

  codingAll(): void {
    this.codingService.codingAll(this.selectedDtPrimaryKey, this.config)
      .subscribe(
        data => {
          if (data['isSuccess'] && data['statusCode'] === 200) {
            this.msg.success(`代码生成成功.`);
          } else {
            this.msg.error(data['msg'], { nzDuration: 6000 });
          }
        },
        error => {
          this.msg.error('请求失败！状态码 ' + error['status'] + ' ' + error['statusText']);
        }
      );
  }
}
