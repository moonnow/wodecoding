package com.pro.code.plugin.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.Sort;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.SortQuery;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin/sort" })
public class SortController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(SortController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodePluginService")
  private ICodePluginService codePluginService;

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  @ResponseBody
  public Responses<Sort> save(Parameter parameter, @RequestBody Sort sort) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.save ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sort is : " + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      codePluginService.saveSort(sort);
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
  public Responses<Sort> batchSave(Parameter parameter, @RequestBody List<Sort> sorts) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.batchSave ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sorts is : " + sorts);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      codePluginService.batchSaveSort(sorts);
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
  public Responses<Sort> update(Parameter parameter, @RequestBody Sort sort) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.update ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sort is : " + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      codePluginService.updateSort(sort);
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
  public Responses<Sort> batchUpdate(Parameter parameter, @RequestBody List<Sort> sorts) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.batchUpdate ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sorts is : " + sorts);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      codePluginService.batchUpdateSort(sorts);
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
  public Responses<Sort> remove(Parameter parameter, @RequestBody Sort sort) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.remove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sort is : " + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      codePluginService.removeSort(sort);
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
  public Responses<Sort> batchRemove(Parameter parameter, @RequestBody List<Sort> sorts) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.batchRemove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sorts is : " + sorts);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      codePluginService.batchRemoveSort(sorts);
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
  public Responses<Sort> getByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.getByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sortId ");
      }
      responses.setData(codePluginService.getSortByPk(parameter.getPrimaryKey()));
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
  public Responses<Sort> get(Parameter parameter, @RequestBody SortQuery sortQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortController.get ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    Responses<Sort> responses = new Responses<>(parameter);
    try {
      if (sortQuery == null || ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllSort());
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetSort(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.querySort(sortQuery));
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQuerySort(parameter, sortQuery));
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
