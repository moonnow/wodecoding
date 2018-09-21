import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Account } from '@com/pro/common/module/account/account/account';
import { AccountQuery } from '@com/pro/common/module/account/account/account-query';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = this.toolService.url + 'pro/common/module/account/account';
  }

  save(account: Account) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.save;
    return this.http.post(this.url + this.toolService.getUrl(this.parameter), account, this.toolService.httpOptions);
  }

  batchSave(account: Account | Array<Account>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchSave;
    if (account instanceof Array) {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), account, this.toolService.httpOptions);
    } else {
      return this.http.post(this.url + this.toolService.getUrl(this.parameter), new Array(account), this.toolService.httpOptions);
    }
  }

  update(account: Account) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.update;
    return this.http.put(this.url + this.toolService.getUrl(this.parameter), account, this.toolService.httpOptions);
  }

  batchUpdate(account: Account | Array<Account>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchUpdate;
    if (account instanceof Array) {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), account, this.toolService.httpOptions);
    } else {
      return this.http.put(this.url + this.toolService.getUrl(this.parameter), new Array(account), this.toolService.httpOptions);
    }
  }

  remove(account: Account) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.remove;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['body'] = account;
    return this.http.request(this.toolService.DELETE, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  batchRemove(account: Account | Array<Account>) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.batchRemove;
    let httpOptions = this.toolService.httpOptions;
    if (account instanceof Array) {
      httpOptions['body'] = account;
    } else {
      httpOptions['body'] = new Array(account);
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

  query(accountQuery: AccountQuery) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.getAll;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = accountQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getUrl(this.parameter), httpOptions);
  }

  pagingQuery(accountQuery: AccountQuery, rows: number, page: number) {
    this.parameter = new Parameter();
    this.parameter.action = this.toolService.paging;
    let httpOptions = this.toolService.httpOptions;
    httpOptions['params'] = accountQuery;
    return this.http.request(this.toolService.GET, this.url + this.toolService.getPagingUrl(this.parameter, rows, page), httpOptions);
  }
}
