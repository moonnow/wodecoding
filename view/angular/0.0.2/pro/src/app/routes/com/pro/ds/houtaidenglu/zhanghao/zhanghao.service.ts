import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Zhanghao } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao';
import { ZhanghaoQuery } from '@com/pro/ds/houtaidenglu/zhanghao/zhanghao-query';

@Injectable({
  providedIn: 'root'
})
export class ZhanghaoService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = this.toolService.url + 'pro/ds/houtaidenglu/zhanghao';
  }

  save(zhanghao: Zhanghao) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), zhanghao, this.toolService.httpOptions);
  }

  batchSave(zhanghao: Zhanghao | Array<Zhanghao>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (zhanghao instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), zhanghao, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(zhanghao), this.toolService.httpOptions);
    }
  }

  update(zhanghao: Zhanghao) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), zhanghao, this.toolService.httpOptions);
  }

  batchUpdate(zhanghao: Zhanghao | Array<Zhanghao>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (zhanghao instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), zhanghao, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(zhanghao), this.toolService.httpOptions);
    }
  }

  remove(zhanghao: Zhanghao) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = zhanghao;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(zhanghao: Zhanghao | Array<Zhanghao>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (zhanghao instanceof Array) {
      httpOptions['body'] = zhanghao;
    } else {
      httpOptions['body'] = new Array(zhanghao);
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

  query(zhanghaoQuery: ZhanghaoQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = zhanghaoQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  pagingQuery(zhanghaoQuery: ZhanghaoQuery, rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = zhanghaoQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getPagingUrl(this.parameter, rows, page), httpOptions);
  }
}
