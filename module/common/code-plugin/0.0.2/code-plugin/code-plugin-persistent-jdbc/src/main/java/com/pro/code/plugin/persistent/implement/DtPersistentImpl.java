package com.pro.code.plugin.persistent.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.persistent.IDtPersistent;
import com.pro.code.plugin.query.DtQuery;
import com.pro.tool.persistent.implement.ToolPersistent;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Repository("com.pro.code.plugin.DtPersistent")
public class DtPersistentImpl extends ToolPersistent implements IDtPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(DtPersistentImpl.class);

  public static final String TABLE_ALIAS = "dt";

  public static final String COLUMN_DT_ID = "dtId";
  public static final String COLUMN_DT_SQL = "dtSql";
  public static final String COLUMN_DT_NAME = "dtName";
  public static final String COLUMN_DT_NAME_ANNOTATION = "dtNameAnnotation";
  public static final String COLUMN_DT_PREFIX = "dtPrefix";
  public static final String COLUMN_INITIAL_CASE_ENTITY_NAME = "initialCaseEntityName";
  public static final String COLUMN_INITIAL_LOWERCASE_ENTITY_NAME = "initialLowercaseEntityName";
  public static final String COLUMN_PRO_PATH = "proPath";
  public static final String COLUMN_PRO_ALL_NAME = "proAllName";
  public static final String COLUMN_PRO_NAME = "proName";

  public static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<>();
  public static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> COLUMNS_PARAMETER = new LinkedHashMap<>();
  public static final LinkedHashMap<String, String> SORT = new LinkedHashMap<>();

  private static final StringBuilder INSERT_SQL;
  private static final StringBuilder UPDATE_SQL;
  private static final StringBuilder DEL_SQL_BY_PK;
  public static final StringBuilder SELECT_SQL;
  public static final StringBuilder COUNT_SQL;

  static {
    PRIMARY_KEY.add(COLUMN_DT_ID);

    COLUMNS.add(COLUMN_DT_ID);
    COLUMNS.add(COLUMN_DT_SQL);
    COLUMNS.add(COLUMN_DT_NAME);
    COLUMNS.add(COLUMN_DT_NAME_ANNOTATION);
    COLUMNS.add(COLUMN_DT_PREFIX);
    COLUMNS.add(COLUMN_INITIAL_CASE_ENTITY_NAME);
    COLUMNS.add(COLUMN_INITIAL_LOWERCASE_ENTITY_NAME);
    COLUMNS.add(COLUMN_PRO_PATH);
    COLUMNS.add(COLUMN_PRO_ALL_NAME);
    COLUMNS.add(COLUMN_PRO_NAME);

    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_DT_SQL, "DT_SQL");
    COLUMNS_PARAMETER.put(COLUMN_DT_NAME, "DT_NAME");
    COLUMNS_PARAMETER.put(COLUMN_DT_NAME_ANNOTATION, "DT_NAME_ANNOTATION");
    COLUMNS_PARAMETER.put(COLUMN_DT_PREFIX, "DT_PREFIX");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_CASE_ENTITY_NAME, "INITIAL_CASE_ENTITY_NAME");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_LOWERCASE_ENTITY_NAME, "INITIAL_LOWERCASE_ENTITY_NAME");
    COLUMNS_PARAMETER.put(COLUMN_PRO_PATH, "PRO_PATH");
    COLUMNS_PARAMETER.put(COLUMN_PRO_ALL_NAME, "PRO_ALL_NAME");
    COLUMNS_PARAMETER.put(COLUMN_PRO_NAME, "PRO_NAME");

    SORT.put(COLUMN_DT_NAME, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public void saveDt(Dt dt) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.saveDt ");
      log.debug("parameter dt is : " + dt);
    }
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      this.insert(INSERT_SQL, dt);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void batchSaveDt(Collection<Dt> dts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.batchSaveDt ");
      log.debug("parameter dts is : " + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      this.insert(INSERT_SQL, dts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void updateDt(Dt dt) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.updateDt ");
      log.debug("parameter dt is : " + dt);
    }
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      this.update(UPDATE_SQL, dt);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void batchUpdateDt(Collection<Dt> dts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.batchUpdateDt ");
      log.debug("parameter dts is : " + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      this.update(UPDATE_SQL, dts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void removeDt(Dt dt) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.removeDt ");
      log.debug("parameter dt is : " + dt);
    }
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, dt.getDtId());
        }
        this.del(DEL_SQL_BY_PK, mapSqlParameterSource);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void batchRemoveDt(Collection<Dt> dts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.batchRemoveDt ");
      log.debug("parameter dts is : " + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      this.del(DEL_SQL_BY_PK, dts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountDt(DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.getCountDt ");
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      return this.queryCount(countSql, dtQuery, Long.class);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Dt getDtByPk(java.lang.String dtId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.getDtByPk ");
      log.debug("parameter dtId is : " + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, dtId);
        }
      }
      Collection<Dt> dtSet = this.query(querySql, mapSqlParameterSource, Dt.class);
      return ToolUtil.isNotEmpty(dtSet) ? dtSet.iterator().next() : null;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Dt> getAllDt() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.getAllDt ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Dt.class);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Dt> pagingGetDt(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.pagingGetDt ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      if (parameter.getRows() < 0) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " rows ", " 大于等于0" });
      }
      if (parameter.getPage() < 1) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page ", " 大于等于1" });
      }
      Paging<Dt> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Dt> dtSet = this.query(querySql, Dt.class);
        if (ToolUtil.isNotEmpty(dtSet)) {
          Set<String> inDtId = new LinkedHashSet<>();
          for (Dt dt : dtSet) {
            inDtId.add(dt.getDtId());
          }
          DtQuery dtQuery = new DtQuery();
          dtQuery.setDtIdAndin(new ArrayList<>(inDtId));
          Collection<Dt> rDtSet = this.queryDt(dtQuery);
          if (ToolUtil.isNotEmpty(rDtSet)) {
            paging.setData(rDtSet);
          }
        }
      }
      return paging;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Dt> queryDt(DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.queryDt ");
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Dt.class, dtQuery);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Dt> pagingQueryDt(Parameter parameter, DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.pagingQueryDt ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      if (parameter == null) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      if (parameter.getRows() < 0) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " rows ", " 大于等于0" });
      }
      if (parameter.getPage() < 1) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page ", " 大于等于1" });
      }
      Paging<Dt> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Dt> dtSet = this.query(querySql, Dt.class, dtQuery);
        if (ToolUtil.isNotEmpty(dtSet)) {
          Set<String> inDtId = new LinkedHashSet<>();
          for (Dt dt : dtSet) {
            inDtId.add(dt.getDtId());
          }
          DtQuery rDtQuery = new DtQuery();
          rDtQuery.setDtIdAndin(new ArrayList<>(inDtId));
          Collection<Dt> rDtSet = this.queryDt(rDtQuery);
          if (ToolUtil.isNotEmpty(rDtSet)) {
            paging.setData(rDtSet);
          }
        }
      }
      return paging;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public boolean isUnique(DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call DtPersistent.isUnique ");
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      Long count = this.queryCount(countSql, dtQuery, Long.class);
      return count < 1;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public String getNotUniqueErrorMessage(DtQuery dtQuery) throws CodePluginException {
    return this.getNotUniqueErrorMsg(COLUMNS, COLUMNS_PARAMETER, dtQuery).toString();
  }

}
