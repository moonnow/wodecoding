package com.pro.code.plugin.service;

import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.vo.Config;

public interface ICodingService {

  public void extract(java.lang.String dtId) throws CodePluginException;

  public Config getConfig(java.lang.String dtId) throws CodePluginException;

  public void codingJdbcEntity(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingException(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingIPersistent(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingJdbcPersistentImpl(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingIService(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingServiceImpl(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingController(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularModule(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularRouting(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularEntity(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularService(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularListComponent(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularListHtml(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularListCss(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularEditComponent(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularEditHtml(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularEditCss(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularDetailComponent(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularDetailHtml(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularDetailCss(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingQuery(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingAngularQuery(java.lang.String dtId, Config config) throws CodePluginException;

  public void codingProject(java.lang.String dtId, Config config) throws CodePluginException;

}
