import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Columns } from '@com/pro/code/plugin/columns/columns';
import { ColumnsQuery } from '@com/pro/code/plugin/columns/columns-query';

@Injectable({
  providedIn: 'root'
})
export class ColumnsService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = 'pro/code/plugin/columns';
  }

  save(columns: Columns) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), columns, this.toolService.httpOptions);
  }

  batchSave(columns: Columns | Array<Columns>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (columns instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), columns, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(columns), this.toolService.httpOptions);
    }
  }

  update(columns: Columns) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), columns, this.toolService.httpOptions);
  }

  batchUpdate(columns: Columns | Array<Columns>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (columns instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), columns, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(columns), this.toolService.httpOptions);
    }
  }

  remove(columns: Columns) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = columns;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(columns: Columns | Array<Columns>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (columns instanceof Array) {
      httpOptions['body'] = columns;
    } else {
      httpOptions['body'] = new Array(columns);
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

  query(columnsQuery: ColumnsQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), columnsQuery, this.toolService.httpOptions);
  }

  pagingQuery(rows: number, page: number, columnsQuery: ColumnsQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    return this.http.post(this.url + this.toolService.getPagingUrl(this.parameter, rows, page), columnsQuery, this.toolService.httpOptions);
  }
}
