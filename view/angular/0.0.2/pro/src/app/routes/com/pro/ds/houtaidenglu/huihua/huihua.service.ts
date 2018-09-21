import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Huihua } from '@com/pro/ds/houtaidenglu/huihua/huihua';
import { HuihuaQuery } from '@com/pro/ds/houtaidenglu/huihua/huihua-query';

@Injectable({
  providedIn: 'root'
})
export class HuihuaService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = this.toolService.url + 'pro/ds/houtaidenglu/huihua';
  }

  save(huihua: Huihua) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), huihua, this.toolService.httpOptions);
  }

  batchSave(huihua: Huihua | Array<Huihua>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (huihua instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), huihua, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(huihua), this.toolService.httpOptions);
    }
  }

  update(huihua: Huihua) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), huihua, this.toolService.httpOptions);
  }

  batchUpdate(huihua: Huihua | Array<Huihua>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (huihua instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), huihua, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(huihua), this.toolService.httpOptions);
    }
  }

  remove(huihua: Huihua) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = huihua;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(huihua: Huihua | Array<Huihua>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (huihua instanceof Array) {
      httpOptions['body'] = huihua;
    } else {
      httpOptions['body'] = new Array(huihua);
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

  query(huihuaQuery: HuihuaQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = huihuaQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  pagingQuery(huihuaQuery: HuihuaQuery, rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = huihuaQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getPagingUrl(this.parameter, rows, page), httpOptions);
  }
}
