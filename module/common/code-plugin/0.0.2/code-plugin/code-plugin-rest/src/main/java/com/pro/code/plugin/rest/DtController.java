package com.pro.code.plugin.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.DtQuery;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin/dt" })
public class DtController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(DtController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodePluginService")
  private ICodePluginService codePluginService;

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  @ResponseBody
  public Responses<Dt> save(Parameter parameter, @RequestBody Dt dt) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.save ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dt is : " + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      codePluginService.saveDt(dt);
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
  public Responses<Dt> batchSave(Parameter parameter, @RequestBody List<Dt> dts) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.batchSave ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dts is : " + dts);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      codePluginService.batchSaveDt(dts);
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
  public Responses<Dt> update(Parameter parameter, @RequestBody Dt dt) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.update ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dt is : " + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      codePluginService.updateDt(dt);
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
  public Responses<Dt> batchUpdate(Parameter parameter, @RequestBody List<Dt> dts) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.batchUpdate ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dts is : " + dts);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      codePluginService.batchUpdateDt(dts);
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
  public Responses<Dt> remove(Parameter parameter, @RequestBody Dt dt) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.remove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dt is : " + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      codePluginService.removeDt(dt);
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
  public Responses<Dt> batchRemove(Parameter parameter, @RequestBody List<Dt> dts) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.batchRemove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dts is : " + dts);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      codePluginService.batchRemoveDt(dts);
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
  public Responses<Dt> getByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.getByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      responses.setData(codePluginService.getDtByPk(parameter.getPrimaryKey()));
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
  public Responses<Dt> get(Parameter parameter, @RequestBody DtQuery dtQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtController.get ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (dtQuery == null || ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllDt());
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetDt(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.queryDt(dtQuery));
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQueryDt(parameter, dtQuery));
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
