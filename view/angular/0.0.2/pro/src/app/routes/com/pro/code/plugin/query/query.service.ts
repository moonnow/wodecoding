import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Query } from '@com/pro/code/plugin/query/query';
import { QueryQuery } from '@com/pro/code/plugin/query/query-query';

@Injectable({
  providedIn: 'root'
})
export class QueryService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = 'pro/code/plugin/query';
  }

  save(query: Query) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), query, this.toolService.httpOptions);
  }

  batchSave(query: Query | Array<Query>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (query instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), query, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(query), this.toolService.httpOptions);
    }
  }

  update(query: Query) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), query, this.toolService.httpOptions);
  }

  batchUpdate(query: Query | Array<Query>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (query instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), query, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(query), this.toolService.httpOptions);
    }
  }

  remove(query: Query) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = query;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(query: Query | Array<Query>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (query instanceof Array) {
      httpOptions['body'] = query;
    } else {
      httpOptions['body'] = new Array(query);
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
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), this.toolService.httpOptions);
  }

  paging(rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    return this.http.post(this.url + this.toolService.getPagingUrl(this.parameter, rows, page), this.toolService.httpOptions);
  }

  query(queryQuery: QueryQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), queryQuery, this.toolService.httpOptions);
  }

  pagingQuery(rows: number, page: number, queryQuery: QueryQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    return this.http.post(this.url + this.toolService.getPagingUrl(this.parameter, rows, page), queryQuery, this.toolService.httpOptions);
  }
}
