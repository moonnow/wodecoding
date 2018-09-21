import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Denglurizhi } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi';
import { DenglurizhiQuery } from '@com/pro/ds/houtaidenglu/denglurizhi/denglurizhi-query';

@Injectable({
  providedIn: 'root'
})
export class DenglurizhiService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = this.toolService.url + 'pro/ds/houtaidenglu/denglurizhi';
  }

  save(denglurizhi: Denglurizhi) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), denglurizhi, this.toolService.httpOptions);
  }

  batchSave(denglurizhi: Denglurizhi | Array<Denglurizhi>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (denglurizhi instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), denglurizhi, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(denglurizhi), this.toolService.httpOptions);
    }
  }

  update(denglurizhi: Denglurizhi) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), denglurizhi, this.toolService.httpOptions);
  }

  batchUpdate(denglurizhi: Denglurizhi | Array<Denglurizhi>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (denglurizhi instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), denglurizhi, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(denglurizhi), this.toolService.httpOptions);
    }
  }

  remove(denglurizhi: Denglurizhi) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = denglurizhi;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(denglurizhi: Denglurizhi | Array<Denglurizhi>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (denglurizhi instanceof Array) {
      httpOptions['body'] = denglurizhi;
    } else {
      httpOptions['body'] = new Array(denglurizhi);
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

  query(denglurizhiQuery: DenglurizhiQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = denglurizhiQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  pagingQuery(denglurizhiQuery: DenglurizhiQuery, rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = denglurizhiQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getPagingUrl(this.parameter, rows, page), httpOptions);
  }
}
