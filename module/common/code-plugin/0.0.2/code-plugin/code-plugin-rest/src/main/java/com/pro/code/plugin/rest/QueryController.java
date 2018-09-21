package com.pro.code.plugin.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.Query;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.QueryQuery;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin/query" })
public class QueryController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(QueryController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodePluginService")
  private ICodePluginService codePluginService;

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  @ResponseBody
  public Responses<Query> save(Parameter parameter, @RequestBody Query query) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.save ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter query is : " + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      codePluginService.saveQuery(query);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  @ResponseBody
  public Responses<Query> batchSave(Parameter parameter, @RequestBody List<Query> querys) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.batchSave ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter querys is : " + querys);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      codePluginService.batchSaveQuery(querys);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.PUT }, params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  @ResponseBody
  public Responses<Query> update(Parameter parameter, @RequestBody Query query) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.update ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter query is : " + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      codePluginService.updateQuery(query);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.PUT }, params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  @ResponseBody
  public Responses<Query> batchUpdate(Parameter parameter, @RequestBody List<Query> querys) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.batchUpdate ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter querys is : " + querys);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      codePluginService.batchUpdateQuery(querys);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.DELETE }, params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  @ResponseBody
  public Responses<Query> remove(Parameter parameter, @RequestBody Query query) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.remove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter query is : " + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      codePluginService.removeQuery(query);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.DELETE }, params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  @ResponseBody
  public Responses<Query> batchRemove(Parameter parameter, @RequestBody List<Query> querys) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.batchRemove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter querys is : " + querys);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      codePluginService.batchRemoveQuery(querys);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.GET }, params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  @ResponseBody
  public Responses<Query> getByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.getByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " queryId ");
      }
      responses.setData(codePluginService.getQueryByPk(parameter.getPrimaryKey()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Query> get(Parameter parameter, @RequestBody QueryQuery queryQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryController.get ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    Responses<Query> responses = new Responses<>(parameter);
    try {
      if (queryQuery == null || ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllQuery());
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetQuery(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.queryQuery(queryQuery));
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQueryQuery(parameter, queryQuery));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

}
