package com.pro.code.plugin.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.Pk;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.PkQuery;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin/pk" })
public class PkController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(PkController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodePluginService")
  private ICodePluginService codePluginService;

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  @ResponseBody
  public Responses<Pk> save(Parameter parameter, @RequestBody Pk pk) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.save ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pk is : " + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
      }
      codePluginService.savePk(pk);
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
  public Responses<Pk> batchSave(Parameter parameter, @RequestBody List<Pk> pks) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.batchSave ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pks is : " + pks);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pks ");
      }
      codePluginService.batchSavePk(pks);
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
  public Responses<Pk> update(Parameter parameter, @RequestBody Pk pk) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.update ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pk is : " + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
      }
      codePluginService.updatePk(pk);
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
  public Responses<Pk> batchUpdate(Parameter parameter, @RequestBody List<Pk> pks) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.batchUpdate ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pks is : " + pks);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pks ");
      }
      codePluginService.batchUpdatePk(pks);
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
  public Responses<Pk> remove(Parameter parameter, @RequestBody Pk pk) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.remove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pk is : " + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
      }
      codePluginService.removePk(pk);
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
  public Responses<Pk> batchRemove(Parameter parameter, @RequestBody List<Pk> pks) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.batchRemove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pks is : " + pks);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pks ");
      }
      codePluginService.batchRemovePk(pks);
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
  public Responses<Pk> getByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.getByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pkId ");
      }
      responses.setData(codePluginService.getPkByPk(parameter.getPrimaryKey()));
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
  public Responses<Pk> get(Parameter parameter, @RequestBody PkQuery pkQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call PkController.get ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pkQuery is : " + pkQuery);
    }
    Responses<Pk> responses = new Responses<>(parameter);
    try {
      if (pkQuery == null || ToolUtil.isNullEntityAllFieldValue(pkQuery)) {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllPk());
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetPk(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.queryPk(pkQuery));
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQueryPk(parameter, pkQuery));
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
