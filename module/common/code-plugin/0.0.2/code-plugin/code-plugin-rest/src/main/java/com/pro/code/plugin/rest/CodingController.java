package com.pro.code.plugin.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.service.ICodingService;
import com.pro.code.plugin.vo.Config;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin" })
public class CodingController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(CodingController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodingService")
  private ICodingService codingService;

  @RequestMapping(value = { "extract" }, method = { RequestMethod.GET })
  @ResponseBody
  public Responses<Dt> extract(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.extract ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.extract(parameter.getPrimaryKey());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "get_config" }, method = { RequestMethod.GET })
  @ResponseBody
  public Responses<Config> getConfig(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.getConfig ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      responses.setData(codingService.getConfig(parameter.getPrimaryKey()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_jdbc_entity" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingJdbcEntity(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingJdbcEntity ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingJdbcEntity(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_exception" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingException(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingException ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingException(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_i_persistent" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingIPersistent(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingIPersistent ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingIPersistent(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_jdbc_persistent_impl" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingJdbcPersistentImpl(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingJdbcPersistentImpl ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingJdbcPersistentImpl(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_i_service" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingIService(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingIService ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingIService(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_service_impl" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingServiceImpl(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingServiceImpl ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingServiceImpl(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_controller" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingController(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingController ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingController(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_module" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularModule(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularModule ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularModule(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_routing" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularRouting(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularRouting ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularRouting(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_entity" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularEntity(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularEntity ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularEntity(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_service" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularService(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularService ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularService(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_list_component" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularListComponent(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularListComponent ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularListComponent(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_list_html" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularListHtml(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularListHtml ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularListHtml(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_list_css" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularListCss(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularListCss ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularListCss(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_edit_component" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularEditComponent(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularEditComponent ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularEditComponent(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_edit_html" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularEditHtml(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularEditHtml ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularEditHtml(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_edit_css" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularEditCss(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularEditCss ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularEditCss(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_detail_component" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularDetailComponent(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularDetailComponent ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularDetailComponent(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_detail_html" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularDetailHtml(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularDetailHtml ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularDetailHtml(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_detail_css" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularDetailCss(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularDetailCss ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularDetailCss(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_query" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingQuery(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingQuery ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingQuery(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_angular_query" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAngularQuery(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAngularQuery ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingAngularQuery(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_project" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingProject(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingProject ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingProject(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "coding_all" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<Config> codingAll(Parameter parameter, @RequestBody Config config) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingController.codingAll ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter config is : " + config);
    }
    Responses<Config> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      codingService.codingJdbcEntity(parameter.getPrimaryKey(), config);
      codingService.codingQuery(parameter.getPrimaryKey(), config);
      codingService.codingException(parameter.getPrimaryKey(), config);
      codingService.codingIPersistent(parameter.getPrimaryKey(), config);
      codingService.codingJdbcPersistentImpl(parameter.getPrimaryKey(), config);
      codingService.codingIService(parameter.getPrimaryKey(), config);
      codingService.codingServiceImpl(parameter.getPrimaryKey(), config);
      codingService.codingController(parameter.getPrimaryKey(), config);
      codingService.codingAngularModule(parameter.getPrimaryKey(), config);
      codingService.codingAngularRouting(parameter.getPrimaryKey(), config);
      codingService.codingAngularEntity(parameter.getPrimaryKey(), config);
      codingService.codingAngularQuery(parameter.getPrimaryKey(), config);
      codingService.codingAngularService(parameter.getPrimaryKey(), config);
      codingService.codingAngularListComponent(parameter.getPrimaryKey(), config);
      codingService.codingAngularListHtml(parameter.getPrimaryKey(), config);
      codingService.codingAngularListCss(parameter.getPrimaryKey(), config);
      codingService.codingAngularEditComponent(parameter.getPrimaryKey(), config);
      codingService.codingAngularEditHtml(parameter.getPrimaryKey(), config);
      codingService.codingAngularEditCss(parameter.getPrimaryKey(), config);
      codingService.codingAngularDetailComponent(parameter.getPrimaryKey(), config);
      codingService.codingAngularDetailHtml(parameter.getPrimaryKey(), config);
      codingService.codingAngularDetailCss(parameter.getPrimaryKey(), config);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

}
