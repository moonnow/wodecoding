import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Dt } from '@com/pro/common/module/autocode/dt/dt';
import { DtQuery } from '@com/pro/common/module/autocode/dt/dt-query';

@Injectable({
  providedIn: 'root'
})
export class DtService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = this.toolService.url + 'pro/common/module/autocode/dt';
  }

  save(dt: Dt) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), dt, this.toolService.httpOptions);
  }

  batchSave(dt: Dt | Array<Dt>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (Dt instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), dt, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(dt), this.toolService.httpOptions);
    }
  }

  update(dt: Dt) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), dt, this.toolService.httpOptions);
  }

  batchUpdate(dt: Dt | Array<Dt>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (Dt instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), dt, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(dt), this.toolService.httpOptions);
    }
  }

  remove(dt: Dt) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = dt;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(dt: Dt | Array<Dt>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (Dt instanceof Array) {
      httpOptions['body'] = dt;
    } else {
      httpOptions['body'] = new Array(dt);
    }
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  getByPk(primaryKey: string) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getByPk;
    this.parameter.primaryKey = primaryKey;
    return this.http.get(this.url + this.toolService.getUrl(this.parameter), this.toolService.httpOptions);
  }

  getAll() {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    return this.http.get(this.url + this.toolService.getUrl(this.parameter), this.toolService.httpOptions);
  }

  paging(rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    return this.http.get(this.url + this.toolService.getPagingUrl(this.parameter, rows, page), this.toolService.httpOptions);
  }

  query(dtQuery: DtQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = dtQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  pagingQuery(dtQuery: DtQuery, rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = dtQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getPagingUrl(this.parameter, rows, page), httpOptions);
  }
}
