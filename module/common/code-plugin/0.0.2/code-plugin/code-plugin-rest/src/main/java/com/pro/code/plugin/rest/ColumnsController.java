package com.pro.code.plugin.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.Columns;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.ColumnsQuery;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin/columns" })
public class ColumnsController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(ColumnsController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodePluginService")
  private ICodePluginService codePluginService;

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  @ResponseBody
  public Responses<Columns> save(Parameter parameter, @RequestBody Columns columns) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.save ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columns is : " + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      codePluginService.saveColumns(columns);
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
  public Responses<Columns> batchSave(Parameter parameter, @RequestBody List<Columns> columnses) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.batchSave ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnses is : " + columnses);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(columnses)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnses ");
      }
      codePluginService.batchSaveColumns(columnses);
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
  public Responses<Columns> update(Parameter parameter, @RequestBody Columns columns) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.update ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columns is : " + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      codePluginService.updateColumns(columns);
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
  public Responses<Columns> batchUpdate(Parameter parameter, @RequestBody List<Columns> columnses) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.batchUpdate ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnses is : " + columnses);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(columnses)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnses ");
      }
      codePluginService.batchUpdateColumns(columnses);
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
  public Responses<Columns> remove(Parameter parameter, @RequestBody Columns columns) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.remove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columns is : " + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      codePluginService.removeColumns(columns);
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
  public Responses<Columns> batchRemove(Parameter parameter, @RequestBody List<Columns> columnses) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.batchRemove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnses is : " + columnses);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(columnses)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnses ");
      }
      codePluginService.batchRemoveColumns(columnses);
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
  public Responses<Columns> getByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.getByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
      }
      responses.setData(codePluginService.getColumnsByPk(parameter.getPrimaryKey()));
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
  public Responses<Columns> get(Parameter parameter, @RequestBody ColumnsQuery columnsQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsController.get ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (columnsQuery == null || ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllColumns());
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetColumns(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.queryColumns(columnsQuery));
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQueryColumns(parameter, columnsQuery));
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
