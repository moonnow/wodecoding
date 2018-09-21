import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ToolService } from '@com/pro/tool/tool.service';
import { Parameter } from '@com/pro/tool/parameter';
import { Config } from '@com/pro/code/plugin/coding/config';

@Injectable({
  providedIn: 'root'
})
export class CodingService {
  url: string;
  parameter: Parameter;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
  ) {
    this.url = 'pro/code/plugin';
  }

  extract(primaryKey: string) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.get(this.url + '/extract' + this.toolService.getUrl(this.parameter), this.toolService.httpOptions);
  }

  getConfig(primaryKey: string) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.get(this.url + '/get_config' + this.toolService.getUrl(this.parameter), this.toolService.httpOptions);
  }

  codingJdbcEntity(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_jdbc_entity' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingException(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_exception' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingIPersistent(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_i_persistent' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingJdbcPersistentImpl(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_jdbc_persistent_impl' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingIService(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_i_service' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingServiceImpl(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_service_impl' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingController(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_controller' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularModule(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_module' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularRouting(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_routing' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularEntity(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_entity' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularService(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_service' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularListComponent(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_list_component' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularListHtml(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_list_html' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularListCss(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_list_css' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularEditComponent(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_edit_component' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularEditHtml(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_edit_html' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularEditCss(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_edit_css' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularDetailComponent(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_detail_component' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularDetailHtml(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_detail_html' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularDetailCss(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_detail_css' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingQuery(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_query' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAngularQuery(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_angular_query' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingProject(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_project' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }

  codingAll(primaryKey: string, config: Config) {
    this.parameter = new Parameter();
    this.parameter.primaryKey = primaryKey;
    return this.http.post(this.url + '/coding_all' + this.toolService.getUrl(this.parameter), config, this.toolService.httpOptions);
  }
}
