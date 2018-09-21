import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Zhanghao } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = this.toolService.url + 'pro/ds/houtaidenglu';
  }

  login(zhanghao: Zhanghao) {
    this.parameter = new Parameter();
    return this.http.post(this.url + '/login' + this.toolService.getUrl(this.parameter), zhanghao, this.toolService.httpOptions);
  }

}
