package com.pro.code.plugin.service.implement;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import com.pro.code.plugin.entity.Columns;
import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.entity.Pk;
import com.pro.code.plugin.entity.Query;
import com.pro.code.plugin.entity.Sort;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.persistent.IColumnsPersistent;
import com.pro.code.plugin.persistent.IDtPersistent;
import com.pro.code.plugin.persistent.IPkPersistent;
import com.pro.code.plugin.persistent.IQueryPersistent;
import com.pro.code.plugin.persistent.ISortPersistent;
import com.pro.code.plugin.query.ColumnsQuery;
import com.pro.code.plugin.query.PkQuery;
import com.pro.code.plugin.query.QueryQuery;
import com.pro.code.plugin.query.SortQuery;
import com.pro.code.plugin.service.ICodingService;
import com.pro.code.plugin.vo.Config;
import com.pro.tool.util.ConfigTools;
import com.pro.tool.util.SqlTools;
import com.pro.tool.util.StrTools;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Service("com.pro.code.plugin.CodingService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class CodingServiceImpl implements ICodingService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(CodingServiceImpl.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.DtPersistent")
  private IDtPersistent dtPersistent;

  @javax.annotation.Resource(name = "com.pro.code.plugin.ColumnsPersistent")
  private IColumnsPersistent columnsPersistent;

  @javax.annotation.Resource(name = "com.pro.code.plugin.PkPersistent")
  private IPkPersistent pkPersistent;

  @javax.annotation.Resource(name = "com.pro.code.plugin.SortPersistent")
  private ISortPersistent sortPersistent;

  @javax.annotation.Resource(name = "com.pro.code.plugin.QueryPersistent")
  private IQueryPersistent queryPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void extract(java.lang.String dtId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.extract ");
      log.debug("parameter dtId is : " + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      // 取到表数据并且设值然后更新数据
      Dt dt = dtPersistent.getDtByPk(dtId);
      dt.setDtNameAnnotation(SqlTools.getDtNameAnnotationFromDtStr(dt.getDtSql()));
      if (ToolUtil.isNullStr(dt.getDtPrefix())) {
        dt.setInitialCaseEntityName(SqlTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName(), true));
        dt.setInitialLowercaseEntityName(SqlTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName(), false));
      } else {
        dt.setInitialCaseEntityName(SqlTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName().replaceAll(dt.getDtPrefix(), "").trim(), true));
        dt.setInitialLowercaseEntityName(SqlTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getDtName().replaceAll(dt.getDtPrefix(), "").trim(), false));
      }
      dtPersistent.updateDt(dt);
      // 删除表相关的查询数据
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      Collection<Query> delQuery = queryPersistent.queryQuery(queryQuery);
      if (ToolUtil.isNotEmpty(delQuery)) {
        queryPersistent.batchRemoveQuery(delQuery);
      }
      // 删除表相关的排序数据
      SortQuery sortQuery = new SortQuery();
      sortQuery.setDtId(dt.getDtId());
      Collection<Sort> delSort = sortPersistent.querySort(sortQuery);
      if (ToolUtil.isNotEmpty(delSort)) {
        sortPersistent.batchRemoveSort(delSort);
      }
      // 删除表的主键数据
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> delPk = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(delPk)) {
        pkPersistent.batchRemovePk(delPk);
      }
      // 删除表的列数据
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> delColumns = columnsPersistent.queryColumns(columnsQuery);
      if (ToolUtil.isNotEmpty(delColumns)) {
        columnsPersistent.batchRemoveColumns(delColumns);
      }
      // 取表的列map
      LinkedHashMap<Integer, String> columnsMap = SqlTools.getColumnsMapFromDtStr(dt.getDtSql());
      String primaryKey = "";
      boolean isOr = true;
      LinkedHashMap<String, String> queryMap = new LinkedHashMap<>();
      if (ToolUtil.isNotEmpty(columnsMap)) {
        LinkedHashSet<Columns> saveColumns = new LinkedHashSet<Columns>();
        for (Entry<Integer, String> entry : columnsMap.entrySet()) {
          if (entry.getValue().indexOf("`") == 0) {
            // 判断是否为列数据 如果是 就保存列数据
            Columns columns = new Columns();
            columns.setColumnsId(ToolUtil.getUUID());
            columns.setColumnName(SqlTools.getColumnsNameFromDtColumnsStr(entry.getValue()));
            columns.setColumnNameAnnotation(SqlTools.getColumnsNameAnnotationFromDtColumnsStr(entry.getValue()));
            columns.setDataType(SqlTools.getDataTypeFromDtColumnsStr(entry.getValue()));
            columns.setIsNull(SqlTools.getIsNullFromDtColumnsStr(entry.getValue()));
            columns.setInitialCaseColumnName(SqlTools.getInitialCaseOrInitialLowercaseHumpFromStr(columns.getColumnName(), true));
            columns.setInitialLowercaseColumnName(SqlTools.getInitialCaseOrInitialLowercaseHumpFromStr(columns.getColumnName(), false));
            columns.setWeightOrder(entry.getKey());
            columns.setDtId(dt.getDtId());
            saveColumns.add(columns);
          } else if (entry.getValue().indexOf("PRIMARY KEY") == 0) {
            // 判断是否为主键 如果是 就设主键的值
            primaryKey = SqlTools.getPrimaryKeyColumnNameFromDtColumnsStr(entry.getValue());
          } else if (entry.getValue().indexOf("UNIQUE KEY") == 0) {
            // 唯一列的判断
            if (isOr) {
              if (-1 != entry.getValue().indexOf("(`")) {
                String str = entry.getValue().substring(entry.getValue().indexOf("(`") + 2);
                queryMap.put(str.substring(0, str.indexOf("`")), "Andeq");
                str = str.substring(str.indexOf("`") + 1);
                for (int i = 1; i > 0; i++) {
                  if (-1 != str.indexOf("`")) {
                    str = str.substring(str.indexOf("`") + 1);
                    queryMap.put(str.substring(0, str.indexOf("`")), "Andeq");
                    str = str.substring(str.indexOf("`") + 1);
                  } else {
                    break;
                  }
                }
                isOr = false;
              }
            } else {
              if (-1 != entry.getValue().indexOf("(`")) {
                String str = entry.getValue().substring(entry.getValue().indexOf("(`") + 2);
                queryMap.put(str.substring(0, str.indexOf("`")), "Oreq");
                str = str.substring(str.indexOf("`") + 1);
                for (int i = 1; i > 0; i++) {
                  if (-1 != str.indexOf("`")) {
                    str = str.substring(str.indexOf("`") + 1);
                    queryMap.put(str.substring(0, str.indexOf("`")), "Andeq");
                    str = str.substring(str.indexOf("`") + 1);
                  } else {
                    break;
                  }
                }
              }
            }
          }
        }
        columnsPersistent.batchSaveColumns(saveColumns);
      }
      // 主键不为空就保存主键数据
      if (ToolUtil.isNotNullStr(primaryKey)) {
        ColumnsQuery primaryKeyColumnsQuery = new ColumnsQuery();
        primaryKeyColumnsQuery.setColumnName(primaryKey);
        primaryKeyColumnsQuery.setDtId(dt.getDtId());
        Collection<Columns> primaryKeyColumns = columnsPersistent.queryColumns(primaryKeyColumnsQuery);
        if (ToolUtil.isNotEmpty(primaryKeyColumns)) {
          Pk pk = new Pk();
          pk.setPkId(ToolUtil.getUUID());
          pk.setDtId(dt.getDtId());
          pk.setColumnsId(primaryKeyColumns.iterator().next().getColumnsId());
          pkPersistent.savePk(pk);
        }
      }
      // 其不为空时 保存主键in查询数据和唯一列查询数据
      if (ToolUtil.isNotEmpty(queryMap) || ToolUtil.isNotNullStr(primaryKey)) {
        LinkedHashSet<Query> saveQuery = new LinkedHashSet<Query>();
        int weightOrder = 1;
        if (ToolUtil.isNotNullStr(primaryKey)) {
          ColumnsQuery primaryKeyColumnsQuery = new ColumnsQuery();
          primaryKeyColumnsQuery.setColumnName(primaryKey);
          primaryKeyColumnsQuery.setDtId(dt.getDtId());
          Collection<Columns> primaryKeyColumns = columnsPersistent.queryColumns(primaryKeyColumnsQuery);
          if (ToolUtil.isNotEmpty(primaryKeyColumns)) {
            Query query = new Query();
            query.setQueryId(ToolUtil.getUUID());
            query.setQueryType("Andin");
            query.setDtId(dt.getDtId());
            query.setColumnsId(primaryKeyColumns.iterator().next().getColumnsId());
            query.setWeightOrder(weightOrder);
            saveQuery.add(query);
            weightOrder++;
          }
        }
        if (ToolUtil.isNotEmpty(queryMap)) {
          for (Entry<String, String> entry : queryMap.entrySet()) {
            ColumnsQuery queryColumnsQuery = new ColumnsQuery();
            queryColumnsQuery.setColumnName(entry.getKey());
            queryColumnsQuery.setDtId(dt.getDtId());
            Collection<Columns> queryColumns = columnsPersistent.queryColumns(queryColumnsQuery);
            if (ToolUtil.isNotEmpty(queryColumns)) {
              Query query = new Query();
              query.setQueryId(ToolUtil.getUUID());
              query.setQueryType(entry.getValue());
              query.setDtId(dt.getDtId());
              query.setColumnsId(queryColumns.iterator().next().getColumnsId());
              query.setWeightOrder(weightOrder);
              saveQuery.add(query);
              weightOrder++;
            }
          }
        }
        queryPersistent.batchSaveQuery(saveQuery);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Config getConfig(java.lang.String dtId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.getConfig ");
      log.debug("parameter dtId is : " + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      Config config = new Config();
      Dt dt = dtPersistent.getDtByPk(dtId);
      config.setJdbcEntityFilePath(ConfigTools.getJdbcEntityFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      config.setExceptionFilePath(ConfigTools.getExceptionFilePathStrFromConfig(dt.getProPath(), dt.getProAllName()));
      config.setPersistentInterfaceFilePath(ConfigTools.getIPersistentFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      config.setJdbcPersistentImplFilePath(ConfigTools.getJdbcPersistentImplFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      config.setServiceInterfaceFilePath(ConfigTools.getIServiceFilePathStrFromConfig(dt.getProPath(), dt.getProAllName()));
      config.setServiceImplFilePath(ConfigTools.getServiceImplFilePathStrFromConfig(dt.getProPath(), dt.getProAllName()));
      config.setControllerFilePath(ConfigTools.getControllerFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      config.setAngularModuleFilePath(ConfigTools.getAngularModuleFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularRoutingFilePath(ConfigTools.getAngularRoutingFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularEntityFilePath(ConfigTools.getAngularEntityFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularServiceFilePath(ConfigTools.getAngularServiceFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularListComponentFilePath(ConfigTools.getAngularListComponentFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularListHtmlFilePath(ConfigTools.getAngularListHtmlFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularListCssFilePath(ConfigTools.getAngularListCssFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularEditComponentFilePath(ConfigTools.getAngularEditComponentFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularEditHtmlFilePath(ConfigTools.getAngularEditHtmlFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularEditCssFilePath(ConfigTools.getAngularEditCssFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularDetailComponentFilePath(ConfigTools.getAngularDetailComponentFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularDetailHtmlFilePath(ConfigTools.getAngularDetailHtmlFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setAngularDetailCssFilePath(ConfigTools.getAngularDetailCssFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setQueryFilePath(ConfigTools.getQueryFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialCaseEntityName()));
      config.setAngularQueryFilePath(ConfigTools.getAngularQueryFilePathStrFromConfig(dt.getProPath(), dt.getProAllName(), dt.getInitialLowercaseEntityName()));
      config.setProjectPath(dt.getProPath());
      return config;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingJdbcEntity(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingJdbcEntity ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder entityStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("entityPackageName", ConfigTools.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtName", dt.getDtName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      entityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/entity/jdbc/1.txt"), configMap)).append("\n");
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          entityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/entity/jdbc/2.txt"), columnConfigMap)).append("\n");
        }
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialCaseColumnName", eachColumns.getInitialCaseColumnName());
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          entityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/entity/jdbc/3.txt"), columnConfigMap)).append("\n");
        }
      }
      entityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/entity/jdbc/4.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(entityStr.toString(), config.getJdbcEntityFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingException(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingException ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder exceptionStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("exceptionPackageName", ConfigTools.getExceptionPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("proName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProAllName(), true));
      exceptionStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/exception/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(exceptionStr.toString(), config.getExceptionFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingIPersistent(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingIPersistent ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder iPersistentStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("iPersistentPackageName", ConfigTools.getIPersistentPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ConfigTools.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("exceptionPackageName", ConfigTools.getExceptionPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryEntityPackageName", ConfigTools.getQueryEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("proName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProAllName(), true));
      configMap.put("dtName", dt.getDtName());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      iPersistentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/i/persistent/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(iPersistentStr.toString(), config.getPersistentInterfaceFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingJdbcPersistentImpl(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingJdbcPersistentImpl ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder jdbcPersistentImplStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("persistentImplPackageName", ConfigTools.getJdbcPersistentImplPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("persistentImplNamePrefix", ConfigTools.getPersistentImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("iPersistentPackageName", ConfigTools.getIPersistentPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ConfigTools.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("exceptionPackageName", ConfigTools.getExceptionPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryEntityPackageName", ConfigTools.getQueryEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("proName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProAllName(), true));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialCaseColumnName", columns.getInitialCaseColumnName());
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      jdbcPersistentImplStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/persistent/jdbc/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(jdbcPersistentImplStr.toString(), config.getJdbcPersistentImplFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingIService(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingIService ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder iServiceStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("iServicePackageName", ConfigTools.getIServicePackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ConfigTools.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("exceptionPackageName", ConfigTools.getExceptionPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryEntityPackageName", ConfigTools.getQueryEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("proName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProAllName(), true));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      File f = new File(config.getServiceInterfaceFilePath());
      if (!f.exists()) {
        iServiceStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/i/service/1.txt"), configMap));
        StrTools.getFileFromContentStrAndPath(iServiceStr.toString(), config.getServiceInterfaceFilePath());
        String oldIServiceStr = StrTools.getStrFromFilePath(config.getServiceInterfaceFilePath());
        oldIServiceStr = oldIServiceStr.substring(0, oldIServiceStr.lastIndexOf("}"));
        iServiceStr = new StringBuilder(oldIServiceStr.substring(0, oldIServiceStr.indexOf("public interface")));
        iServiceStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/i/service/2.txt"), configMap)).append("\n");
        iServiceStr.append(oldIServiceStr.substring(oldIServiceStr.indexOf("public interface")));
        iServiceStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/i/service/3.txt"), configMap));
        StrTools.getFileFromContentStrAndPath(iServiceStr.toString(), config.getServiceInterfaceFilePath());
      } else {
        String oldIServiceStr = StrTools.getStrFromFilePath(config.getServiceInterfaceFilePath());
        oldIServiceStr = oldIServiceStr.substring(0, oldIServiceStr.lastIndexOf("}"));
        iServiceStr = new StringBuilder(oldIServiceStr.substring(0, oldIServiceStr.indexOf("public interface")));
        iServiceStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/i/service/2.txt"), configMap)).append("\n");
        iServiceStr.append(oldIServiceStr.substring(oldIServiceStr.indexOf("public interface")));
        iServiceStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/i/service/3.txt"), configMap));
        StrTools.getFileFromContentStrAndPath(iServiceStr.toString(), config.getServiceInterfaceFilePath());
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingServiceImpl(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingServiceImpl ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder serviceImplStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      Collection<Query> querySet = queryPersistent.queryQuery(queryQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("serviceImplPackageName", ConfigTools.getServiceImplPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("serviceImplNamePrefix", ConfigTools.getServiceImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("persistentImplNamePrefix", ConfigTools.getPersistentImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("iServicePackageName", ConfigTools.getIServicePackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("iPersistentPackageName", ConfigTools.getIPersistentPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ConfigTools.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("exceptionPackageName", ConfigTools.getExceptionPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryEntityPackageName", ConfigTools.getQueryEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("proName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProAllName(), true));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyDataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
          configMap.put("primaryKeyInitialCaseColumnName", columns.getInitialCaseColumnName());
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      File f = new File(config.getServiceImplFilePath());
      if (!f.exists()) {
        serviceImplStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/1.txt"), configMap));
        StrTools.getFileFromContentStrAndPath(serviceImplStr.toString(), config.getServiceImplFilePath());
        String oldIServiceStr = StrTools.getStrFromFilePath(config.getServiceImplFilePath());
        oldIServiceStr = oldIServiceStr.substring(0, oldIServiceStr.lastIndexOf("}"));
        serviceImplStr = new StringBuilder(oldIServiceStr.substring(0, oldIServiceStr.indexOf("@org.springframework.stereotype.Service")));
        serviceImplStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/2.txt"), configMap)).append("\n");
        serviceImplStr.append(oldIServiceStr.substring(oldIServiceStr.indexOf("@org.springframework.stereotype.Service")));
        StringBuilder saveUniqueStr = new StringBuilder();
        StringBuilder batchSaveUniqueStr = new StringBuilder();
        StringBuilder updateUniqueStr = new StringBuilder();
        StringBuilder batchUpdateUniqueStr = new StringBuilder();
        if (-1 != dt.getDtSql().indexOf("UNIQUE KEY")) {
          StringBuilder uniqueStr = new StringBuilder();
          int u = 0;
          saveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/4.txt"), configMap));
          batchSaveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/10.txt"), configMap));
          updateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/4.txt"), configMap));
          batchUpdateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/10.txt"), configMap));
          if (ToolUtil.isNotEmpty(querySet)) {
            for (Query eachQuery : querySet) {
              LinkedHashMap<String, String> uniqueMap = new LinkedHashMap<String, String>();
              uniqueMap.putAll(configMap);
              // 生成service的时候不可以改默认生成的查询数据也不能手动增加查询数据
              if ("Andeq".equals(eachQuery.getQueryType()) || "Oreq".equals(eachQuery.getQueryType())) {
                Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
                uniqueMap.put("initialCaseUniqueColumnName", columns.getInitialCaseColumnName() + eachQuery.getQueryType());
                uniqueMap.put("initialCaseColumnName", columns.getInitialCaseColumnName());
                saveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/5.txt"), uniqueMap));
                batchSaveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/11.txt"), uniqueMap));
                updateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/5.txt"), uniqueMap));
                batchUpdateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/11.txt"), uniqueMap));
                if (u > 0) {
                  uniqueStr.append(" && ");
                }
                uniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/9.txt"), uniqueMap));
                u++;
              }
            }
          }
          saveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/7.txt"), configMap));
          batchSaveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/13.txt"), configMap));
          if (u != 0 && u == 1) {
            configMap.put("unique", uniqueStr.toString().replaceAll("\n", ""));
          } else {
            configMap.put("unique", "(" + uniqueStr.toString().replaceAll("\n", "") + ")");
          }
          updateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/8.txt"), configMap));
          batchUpdateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/14.txt"), configMap));
          configMap.put("saveUnique", saveUniqueStr.toString());
          configMap.put("batchSaveUnique", batchSaveUniqueStr.toString());
          configMap.put("updateUnique", updateUniqueStr.toString());
          configMap.put("batchUpdateUnique", batchUpdateUniqueStr.toString());
        } else {
          configMap.put("saveUnique", "");
          configMap.put("batchSaveUnique", "");
          configMap.put("updateUnique", "");
          configMap.put("batchUpdateUnique", "");
        }
        serviceImplStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/3.txt"), configMap));
        StrTools.getFileFromContentStrAndPath(serviceImplStr.toString(), config.getServiceImplFilePath());
      } else {
        String oldIServiceStr = StrTools.getStrFromFilePath(config.getServiceImplFilePath());
        oldIServiceStr = oldIServiceStr.substring(0, oldIServiceStr.lastIndexOf("}"));
        serviceImplStr = new StringBuilder(oldIServiceStr.substring(0, oldIServiceStr.indexOf("@org.springframework.stereotype.Service")));
        serviceImplStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/2.txt"), configMap)).append("\n");
        serviceImplStr.append(oldIServiceStr.substring(oldIServiceStr.indexOf("@org.springframework.stereotype.Service")));
        StringBuilder saveUniqueStr = new StringBuilder();
        StringBuilder batchSaveUniqueStr = new StringBuilder();
        StringBuilder updateUniqueStr = new StringBuilder();
        StringBuilder batchUpdateUniqueStr = new StringBuilder();
        if (-1 != dt.getDtSql().indexOf("UNIQUE KEY")) {
          StringBuilder uniqueStr = new StringBuilder();
          int u = 0;
          saveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/4.txt"), configMap));
          batchSaveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/10.txt"), configMap));
          updateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/4.txt"), configMap));
          batchUpdateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/10.txt"), configMap));
          if (ToolUtil.isNotEmpty(querySet)) {
            for (Query eachQuery : querySet) {
              LinkedHashMap<String, String> uniqueMap = new LinkedHashMap<String, String>();
              uniqueMap.putAll(configMap);
              if ("Andeq".equals(eachQuery.getQueryType()) || "Oreq".equals(eachQuery.getQueryType())) {
                Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
                uniqueMap.put("initialCaseUniqueColumnName", columns.getInitialCaseColumnName() + eachQuery.getQueryType());
                uniqueMap.put("initialCaseColumnName", columns.getInitialCaseColumnName());
                saveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/5.txt"), uniqueMap));
                batchSaveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/11.txt"), uniqueMap));
                updateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/5.txt"), uniqueMap));
                batchUpdateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/11.txt"), uniqueMap));
                if (u > 0) {
                  uniqueStr.append(" && ");
                }
                uniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/9.txt"), uniqueMap));
                u++;
              }
            }
          }
          saveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/7.txt"), configMap));
          batchSaveUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/13.txt"), configMap));
          if (u != 0 && u == 1) {
            configMap.put("unique", uniqueStr.toString().replaceAll("\n", ""));
          } else {
            configMap.put("unique", "(" + uniqueStr.toString().replaceAll("\n", "") + ")");
          }
          updateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/8.txt"), configMap));
          batchUpdateUniqueStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/14.txt"), configMap));
          configMap.put("saveUnique", saveUniqueStr.toString());
          configMap.put("batchSaveUnique", batchSaveUniqueStr.toString());
          configMap.put("updateUnique", updateUniqueStr.toString());
          configMap.put("batchUpdateUnique", batchUpdateUniqueStr.toString());
        } else {
          configMap.put("saveUnique", "");
          configMap.put("batchSaveUnique", "");
          configMap.put("updateUnique", "");
          configMap.put("batchUpdateUnique", "");
        }
        serviceImplStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/impl/service/3.txt"), configMap));
        StrTools.getFileFromContentStrAndPath(serviceImplStr.toString(), config.getServiceImplFilePath());
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingController(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingController ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder controllerStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("controllerPackageName", ConfigTools.getControllerPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("controllerUrl", ConfigTools.getControllerUrlStrFromConfig(dt.getProAllName()));
      configMap.put("iServicePackageName", ConfigTools.getIServicePackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("serviceImplNamePrefix", ConfigTools.getServiceImplNamePrefixStrFromConfig(dt.getProAllName()));
      configMap.put("entityPackageName", ConfigTools.getEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("exceptionPackageName", ConfigTools.getExceptionPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("queryEntityPackageName", ConfigTools.getQueryEntityPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("proName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProAllName(), true));
      configMap.put("initialLowercaseProName", ConfigTools.getInitialCaseOrInitialLowercaseHumpFromStr(dt.getProName(), false));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      controllerStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/controller/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(controllerStr.toString(), config.getControllerFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularModule(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularModule ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularModuleStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      angularModuleStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/module/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularModuleStr.toString(), config.getAngularModuleFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularRouting(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularRouting ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularRoutingStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      angularRoutingStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/routing/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularRoutingStr.toString(), config.getAngularRoutingFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularEntity(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularEntity ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularEntityStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      angularEntityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/entity/1.txt"), configMap));
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", SqlTools.getAngularDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          angularEntityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/entity/2.txt"), columnConfigMap));
        }
      }
//      angularEntityStr.append("\n").append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/entity/3.txt"), configMap));
//      if (ToolUtil.isNotEmpty(columnsSet)) {
//        for (Columns eachColumns : columnsSet) {
//          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
//          columnConfigMap.putAll(configMap);
//          String dataType = SqlTools.getAngularDataTypeFromDtDataTypeStr(eachColumns.getDataType());
//          if ("string".equals(dataType)) {
//            columnConfigMap.put("initValue", "''");
//          } else if ("number".equals(dataType)) {
//            columnConfigMap.put("initValue", "0");
//          }
//          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
//          angularEntityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/entity/4.txt"), columnConfigMap));
//        }
//      }
//      angularEntityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/entity/5.txt"), configMap));
      angularEntityStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/entity/6.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularEntityStr.toString(), config.getAngularEntityFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularService(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularService ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularServiceStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("controllerUrl", ConfigTools.getControllerUrlStrFromConfig(dt.getProAllName()));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      angularServiceStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/service/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularServiceStr.toString(), config.getAngularServiceFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularListComponent(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularListComponent ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularListComponentStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("controllerUrl", ConfigTools.getControllerUrlStrFromConfig(dt.getProAllName()));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      String primaryKey = "";
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
          configMap.put("primaryKeyColumnNameAnnotation", columns.getColumnNameAnnotation());
          primaryKey = columns.getColumnsId();
        }
      }
      angularListComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/list/component/1.txt"), configMap));
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          if (ToolUtil.isNotNullStr(primaryKey) && primaryKey.equals(eachColumns.getColumnsId())) {
            continue;
          }
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          angularListComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/list/component/2.txt"), columnConfigMap));
        }
      }
      angularListComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/list/component/3.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularListComponentStr.toString(), config.getAngularListComponentFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularListHtml(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularListHtml ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularListHtmlStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      angularListHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/list/html/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularListHtmlStr.toString(), config.getAngularListHtmlFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularListCss(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularListCss ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularListCssStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      configMap.putAll(ConfigTools.configMap);
      angularListCssStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/list/css/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularListCssStr.toString(), config.getAngularListCssFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularEditComponent(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularEditComponent ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularEditComponentStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("controllerUrl", ConfigTools.getControllerUrlStrFromConfig(dt.getProAllName()));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      String primaryKey = "";
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
          primaryKey = columns.getColumnsId();
        }
      }
      angularEditComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/component/1.txt"), configMap));
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          if (ToolUtil.isNotNullStr(primaryKey) && primaryKey.equals(eachColumns.getColumnsId())) {
            continue;
          }
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          if ("y".equals(eachColumns.getIsNull())) {
            columnConfigMap.put("isNullValidators", "");
          } else if ("n".equals(eachColumns.getIsNull())) {
            columnConfigMap.put("isNullValidators", "Validators.required");
          }
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          angularEditComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/component/2.txt"), columnConfigMap));
        }
      }
      angularEditComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/component/3.txt"), configMap)).append("\n");
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          if (ToolUtil.isNotNullStr(primaryKey) && primaryKey.equals(eachColumns.getColumnsId())) {
            continue;
          }
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          angularEditComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/component/4.txt"), columnConfigMap)).append("\n");
        }
      }
      angularEditComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/component/5.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularEditComponentStr.toString(), config.getAngularEditComponentFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularEditHtml(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularEditHtml ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularEditHtmlStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      String primaryKey = "";
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          primaryKey = columns.getColumnsId();
        }
      }
      angularEditHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/html/1.txt"), configMap));
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          if (ToolUtil.isNotNullStr(primaryKey) && primaryKey.equals(eachColumns.getColumnsId())) {
            continue;
          }
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          String dataType = SqlTools.getAngularDataTypeFromDtDataTypeStr(eachColumns.getDataType());
          if ("y".equals(eachColumns.getIsNull())) {
            if ("string".equals(dataType)) {
              angularEditHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/html/3.txt"), columnConfigMap));
            } else if ("number".equals(dataType)) {
              angularEditHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/html/5.txt"), columnConfigMap));
            }
          } else if ("n".equals(eachColumns.getIsNull())) {
            if ("string".equals(dataType)) {
              angularEditHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/html/2.txt"), columnConfigMap));
            } else if ("number".equals(dataType)) {
              angularEditHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/html/4.txt"), columnConfigMap));
            }
          }
        }
      }
      angularEditHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/html/6.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularEditHtmlStr.toString(), config.getAngularEditHtmlFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularEditCss(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularEditCss ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularEditCssStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      configMap.putAll(ConfigTools.configMap);
      angularEditCssStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/edit/css/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularEditCssStr.toString(), config.getAngularEditCssFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularDetailComponent(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularDetailComponent ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularDetailComponentStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("controllerUrl", ConfigTools.getControllerUrlStrFromConfig(dt.getProAllName()));
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
        }
      }
      angularDetailComponentStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/detail/component/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularDetailComponentStr.toString(), config.getAngularDetailComponentFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularDetailHtml(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularDetailHtml ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularDetailHtmlStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("initialLowercaseEntityName", dt.getInitialLowercaseEntityName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(dt.getDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      String primaryKey = "";
      if (ToolUtil.isNotEmpty(pkSet)) {
        Columns columns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
        if (!(columns == null || ToolUtil.isNullEntityAllFieldValue(columns))) {
          configMap.put("primaryKeyInitialLowercaseColumnName", columns.getInitialLowercaseColumnName());
          configMap.put("primaryKeyColumnNameAnnotation", columns.getColumnNameAnnotation());
          primaryKey = columns.getColumnsId();
        }
      }
      angularDetailHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/detail/html/1.txt"), configMap));
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          if (ToolUtil.isNotNullStr(primaryKey) && primaryKey.equals(eachColumns.getColumnsId())) {
            continue;
          }
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          angularDetailHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/detail/html/2.txt"), columnConfigMap));
        }
      }
      angularDetailHtmlStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/detail/html/3.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularDetailHtmlStr.toString(), config.getAngularDetailHtmlFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularDetailCss(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularDetailCss ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularDetailCssStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      configMap.putAll(ConfigTools.configMap);
      angularDetailCssStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/detail/css/1.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularDetailCssStr.toString(), config.getAngularDetailCssFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingQuery(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingQuery ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder queryStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      Collection<Query> querySet = queryPersistent.queryQuery(queryQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("queryPackageName", ConfigTools.getQueryPackageNameStrFromConfig(dt.getProAllName()));
      configMap.put("dtName", dt.getDtName());
      configMap.put("dtNameAnnotation", dt.getDtNameAnnotation());
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/1.txt"), configMap)).append("\n");
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          columnConfigMap.put("columnNameAnnotation", eachColumns.getColumnNameAnnotation());
          queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/2.txt"), columnConfigMap)).append("\n");
        }
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        for (Query eachQuery : querySet) {
          if ("Andin".equals(eachQuery.getQueryType()) || "Andnin".equals(eachQuery.getQueryType()) || "Orin".equals(eachQuery.getQueryType()) || "Ornin".equals(eachQuery.getQueryType())) {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", "java.util.List<" + SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()) + ">");
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            columnConfigMap.put("columnNameAnnotation", columns.getColumnNameAnnotation() + eachQuery.getQueryType() + "查询");
            queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/2.txt"), columnConfigMap)).append("\n");
          } else {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            columnConfigMap.put("columnNameAnnotation", columns.getColumnNameAnnotation() + eachQuery.getQueryType() + "查询");
            queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/2.txt"), columnConfigMap)).append("\n");
          }
        }
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialCaseColumnName", eachColumns.getInitialCaseColumnName());
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/3.txt"), columnConfigMap)).append("\n");
        }
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        for (Query eachQuery : querySet) {
          if ("Andin".equals(eachQuery.getQueryType()) || "Andnin".equals(eachQuery.getQueryType()) || "Orin".equals(eachQuery.getQueryType()) || "Ornin".equals(eachQuery.getQueryType())) {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", "java.util.List<" + SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()) + ">");
            columnConfigMap.put("initialCaseColumnName", columns.getInitialCaseColumnName() + eachQuery.getQueryType());
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/3.txt"), columnConfigMap)).append("\n");
          } else {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", SqlTools.getJavaDataTypeFromDtDataTypeStr(columns.getDataType()));
            columnConfigMap.put("initialCaseColumnName", columns.getInitialCaseColumnName() + eachQuery.getQueryType());
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/3.txt"), columnConfigMap)).append("\n");
          }
        }
      }
      queryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/query/4.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(queryStr.toString(), config.getQueryFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void codingAngularQuery(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingAngularQuery ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder angularQueryStr = new StringBuilder();
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      Dt dt = dtPersistent.getDtByPk(dtId);
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setDtId(dt.getDtId());
      Collection<Columns> columnsSet = columnsPersistent.queryColumns(columnsQuery);
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setDtId(dt.getDtId());
      Collection<Query> querySet = queryPersistent.queryQuery(queryQuery);
      configMap.putAll(ConfigTools.configMap);
      configMap.put("initialCaseEntityName", dt.getInitialCaseEntityName());
      angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/1.txt"), configMap));
      if (ToolUtil.isNotEmpty(columnsSet)) {
        for (Columns eachColumns : columnsSet) {
          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
          columnConfigMap.putAll(configMap);
          columnConfigMap.put("dataType", SqlTools.getAngularDataTypeFromDtDataTypeStr(eachColumns.getDataType()));
          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
          angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/2.txt"), columnConfigMap));
        }
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        for (Query eachQuery : querySet) {
          if ("Andin".equals(eachQuery.getQueryType()) || "Andnin".equals(eachQuery.getQueryType()) || "Orin".equals(eachQuery.getQueryType()) || "Ornin".equals(eachQuery.getQueryType())) {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", "Array<" + SqlTools.getAngularDataTypeFromDtDataTypeStr(columns.getDataType()) + ">");
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/2.txt"), columnConfigMap));
          } else {
            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
            columnConfigMap.putAll(configMap);
            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
            columnConfigMap.put("dataType", SqlTools.getAngularDataTypeFromDtDataTypeStr(columns.getDataType()));
            columnConfigMap.put("initialLowercaseColumnName", columns.getInitialLowercaseColumnName() + eachQuery.getQueryType());
            angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/2.txt"), columnConfigMap));
          }
        }
      }
//      angularQueryStr.append("\n").append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/3.txt"), configMap));
//      if (ToolUtil.isNotEmpty(columnsSet)) {
//        for (Columns eachColumns : columnsSet) {
//          LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
//          columnConfigMap.putAll(configMap);
//          String dataType = SqlTools.getAngularDataTypeFromDtDataTypeStr(eachColumns.getDataType());
//          if ("string".equals(dataType)) {
//            columnConfigMap.put("initValue", "''");
//          } else if ("number".equals(dataType)) {
//            columnConfigMap.put("initValue", "0");
//          }
//          columnConfigMap.put("initialLowercaseColumnName", eachColumns.getInitialLowercaseColumnName());
//          angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/4.txt"), columnConfigMap));
//        }
//      }
//      if (ToolUtil.isNotEmpty(querySet)) {
//        for (Query eachQuery : querySet) {
//          if ("in".equals(eachQuery.getQueryType()) || "nin".equals(eachQuery.getQueryType())) {
//            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
//            columnConfigMap.putAll(configMap);
//            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
//            columnConfigMap.put("initValue", "new Array()");
//            columnConfigMap.put("initialLowercaseColumnName", eachQuery.getQueryType() + columns.getInitialCaseColumnName());
//            angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/4.txt"), columnConfigMap));
//          } else {
//            LinkedHashMap<String, String> columnConfigMap = new LinkedHashMap<String, String>();
//            columnConfigMap.putAll(configMap);
//            Columns columns = columnsPersistent.getColumnsByPk(eachQuery.getColumnsId());
//            String dataType = SqlTools.getAngularDataTypeFromDtDataTypeStr(columns.getDataType());
//            if ("string".equals(dataType)) {
//              columnConfigMap.put("initValue", "''");
//            } else if ("number".equals(dataType)) {
//              columnConfigMap.put("initValue", "0");
//            }
//            columnConfigMap.put("initialLowercaseColumnName", eachQuery.getQueryType() + columns.getInitialCaseColumnName());
//            angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/4.txt"), columnConfigMap));
//          }
//        }
//      }
//      angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/5.txt"), configMap));
      angularQueryStr.append(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/angular/query/6.txt"), configMap));
      StrTools.getFileFromContentStrAndPath(angularQueryStr.toString(), config.getAngularQueryFilePath());
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

//  @org.springframework.beans.factory.annotation.Autowired
//  private com.pro.code.plugin.mapper.ColumnsMapper columnsMapper;

  @Override
  public void codingProject(java.lang.String dtId, Config config) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodingService.codingProject ");
      log.debug("parameter dtId is : " + dtId);
      log.debug("parameter config is : " + config);
    }
    try {
      
//      Columns ss = columnsMapper.selectByPrimaryKey("05b548f9-1156-42bb-bcac-48e93a356b2e");
//      System.out.println("asdfghjkl");
//      System.out.println(ss);
      
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      Dt dt = dtPersistent.getDtByPk(dtId);
      LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();
      configMap.putAll(ConfigTools.configMap);
      configMap.put("projectName", dt.getProAllName());
      configMap.put("projectVersion", "0.0.2");
      String name = "皓月";
      String proAllName = dt.getProAllName();
      String proPath = "";
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(config.getProjectPath().substring(config.getProjectPath().length() - 1, config.getProjectPath().length()))) {
        proPath = config.getProjectPath() + "/";
      } else {
        proPath = config.getProjectPath();
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      StrTools.getFileFromContentStrAndPath(name, proPath + "sql/README.md");
      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/1.txt"), configMap), proPath + "pom.xml");
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/2.txt"), configMap), proPath + "entity-jdbc/pom.xml");
      StrTools.getFileFromContentStrAndPath(name, proPath + "entity-jdbc/src/main/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "entity-jdbc/src/main/resources/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "entity-jdbc/src/test/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "entity-jdbc/src/test/resources/README.md");
      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/3.txt"), configMap), proPath + "interface/pom.xml");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/resources/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/test/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/test/resources/README.md");

      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/persistent/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/service/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/vo/README.md");

      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "mapper/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "interface/src/main/resources/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "mapping/README.md");

      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/4.txt"), configMap), proPath + "persistent-jdbc/pom.xml");
      StrTools.getFileFromContentStrAndPath(name, proPath + "persistent-jdbc/src/main/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "persistent-jdbc/src/main/resources/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "persistent-jdbc/src/test/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "persistent-jdbc/src/test/resources/README.md");

      StrTools.getFileFromContentStrAndPath(name, proPath + "persistent-jdbc/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/persistent/implement/README.md");

      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/5.txt"), configMap), proPath + "service/pom.xml");
      StrTools.getFileFromContentStrAndPath(name, proPath + "service/src/main/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "service/src/main/resources/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "service/src/test/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "service/src/test/resources/README.md");

      StrTools.getFileFromContentStrAndPath(name, proPath + "service/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/service/implement/README.md");

      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/6.txt"), configMap), proPath + "rest/pom.xml");
      StrTools.getFileFromContentStrAndPath(name, proPath + "rest/src/main/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "rest/src/main/resources/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "rest/src/test/java/README.md");
      StrTools.getFileFromContentStrAndPath(name, proPath + "rest/src/test/resources/README.md");

      StrTools.getFileFromContentStrAndPath(name, proPath + "rest/src/main/java/com/pro/" + ConfigTools.getBusinessPathStrFromConfig(dt.getProAllName()) + "business/rest/README.md");

      StrTools.getFileFromContentStrAndPath(StrTools.renderString(StrTools.getStrFromFileResourcesPath("/pro/7.txt"), configMap), proPath + "jdbc-rest/pom.xml");
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

}
