import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

import { Parameter } from '@com/pro/tool/parameter';

@Injectable({
  providedIn: 'root'
})
export class ToolService {
  _save: string;
  _batchSave: string;
  _update: string;
  _batchUpdate: string;
  _remove: string;
  _batchRemove: string;
  _getByPk: string;
  _getAll: string;
  _paging: string;
  _GET: string;
  _POST: string;
  _PUT: string;
  _DELETE: string;
  _httpOptions: any;
  _url: string;

  constructor() {
    this._save = 'save';
    this._batchSave = 'batch_save';
    this._update = 'update';
    this._batchUpdate = 'batch_update';
    this._remove = 'remove';
    this._batchRemove = 'batch_remove';
    this._getByPk = 'get_by_pk';
    this._getAll = 'get_all';
    this._paging = 'paging';
    this._GET = 'GET';
    this._POST = 'POST';
    this._PUT = 'PUT';
    this._DELETE = 'DELETE';
    this._httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    this._url = 'api/';
  }

  get save(): string {
    return this._save;
  }

  get batchSave(): string {
    return this._batchSave;
  }

  get update(): string {
    return this._update;
  }

  get batchUpdate(): string {
    return this._batchUpdate;
  }

  get remove(): string {
    return this._remove;
  }

  get batchRemove(): string {
    return this._batchRemove;
  }

  get getByPk(): string {
    return this._getByPk;
  }

  get getAll(): string {
    return this._getAll;
  }

  get paging(): string {
    return this._paging;
  }

  get GET(): string {
    return this._GET;
  }

  get POST(): string {
    return this._POST;
  }

  get PUT(): string {
    return this._PUT;
  }

  get DELETE(): string {
    return this._DELETE;
  }

  get httpOptions() {
    return this._httpOptions;
  }

  get url(): string {
    return this._url;
  }

  getUrl(parameter: Parameter): string {
    let url: string = '?pro=' + parameter.pro;
    if (parameter.action && parameter.action !== '') {
      url = url + '&action=' + parameter.action;
    }
    if (parameter.primaryKey && parameter.primaryKey !== '') {
      url = url + '&primaryKey=' + parameter.primaryKey;
    }
    if (parameter.rows && parameter.rows != null) {
      url = url + '&rows=' + parameter.rows;
    }
    if (parameter.page && parameter.page != null) {
      url = url + '&page=' + parameter.page;
    }
    if (parameter.token && parameter.token !== '') {
      url = url + '&token=' + parameter.token;
    }
    return url;
  }

  getPagingUrl(parameter: Parameter, rows: number, page: number): string {
    let url: string = '?pro=' + parameter.pro;
    if (parameter.action && parameter.action !== '') {
      url = url + '&action=' + parameter.action;
    }
    if (parameter.primaryKey && parameter.primaryKey !== '') {
      url = url + '&primaryKey=' + parameter.primaryKey;
    }
    if (parameter.rows && parameter.rows != null) {
      if (rows > 0) {
        parameter.rows = rows;
      }
      url = url + '&rows=' + parameter.rows;
    }
    if (parameter.page && parameter.page != null) {
      if (page > 0) {
        parameter.page = page;
      }
      url = url + '&page=' + parameter.page;
    }
    if (parameter.token && parameter.token !== '') {
      url = url + '&token=' + parameter.token;
    }
    return url;
  }
}
