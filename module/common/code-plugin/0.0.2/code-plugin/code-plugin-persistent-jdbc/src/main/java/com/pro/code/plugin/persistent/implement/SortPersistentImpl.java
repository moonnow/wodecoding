package com.pro.code.plugin.persistent.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.pro.code.plugin.entity.Sort;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.persistent.ISortPersistent;
import com.pro.code.plugin.query.SortQuery;
import com.pro.tool.persistent.implement.ToolPersistent;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Repository("com.pro.code.plugin.SortPersistent")
public class SortPersistentImpl extends ToolPersistent implements ISortPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(SortPersistentImpl.class);

  public static final String TABLE_ALIAS = "sort";

  public static final String COLUMN_SORT_ID = "sortId";
  public static final String COLUMN_SORT_RULE = "sortRule";
  public static final String COLUMN_DT_ID = "dtId";
  public static final String COLUMN_COLUMNS_ID = "columnsId";

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
    PRIMARY_KEY.add(COLUMN_SORT_ID);

    COLUMNS.add(COLUMN_SORT_ID);
    COLUMNS.add(COLUMN_SORT_RULE);
    COLUMNS.add(COLUMN_DT_ID);
    COLUMNS.add(COLUMN_COLUMNS_ID);

    COLUMNS_PARAMETER.put(COLUMN_SORT_ID, "SORT_ID");
    COLUMNS_PARAMETER.put(COLUMN_SORT_RULE, "SORT_RULE");
    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_COLUMNS_ID, "COLUMNS_ID");

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public void saveSort(Sort sort) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.saveSort ");
      log.debug("parameter sort is : " + sort);
    }
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      this.insert(INSERT_SQL, sort);
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
  public void batchSaveSort(Collection<Sort> sorts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.batchSaveSort ");
      log.debug("parameter sorts is : " + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      this.insert(INSERT_SQL, sorts);
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
  public void updateSort(Sort sort) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.updateSort ");
      log.debug("parameter sort is : " + sort);
    }
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      this.update(UPDATE_SQL, sort);
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
  public void batchUpdateSort(Collection<Sort> sorts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.batchUpdateSort ");
      log.debug("parameter sorts is : " + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      this.update(UPDATE_SQL, sorts);
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
  public void removeSort(Sort sort) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.removeSort ");
      log.debug("parameter sort is : " + sort);
    }
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, sort.getSortId());
        }
      }
      this.del(DEL_SQL_BY_PK, mapSqlParameterSource);
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
  public void batchRemoveSort(Collection<Sort> sorts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.batchRemoveSort ");
      log.debug("parameter sorts is : " + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      this.del(DEL_SQL_BY_PK, sorts);
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
  public Long getCountSort(SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.getCountSort ");
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      return this.queryCount(countSql, sortQuery, Long.class);
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
  public Sort getSortByPk(java.lang.String sortId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.getSortByPk ");
      log.debug("parameter sortId is : " + sortId);
    }
    try {
      if (ToolUtil.isNullStr(sortId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sortId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, sortId);
        }
      }
      Collection<Sort> sortSet = this.query(querySql, mapSqlParameterSource, Sort.class);
      return ToolUtil.isNotEmpty(sortSet) ? sortSet.iterator().next() : null;
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
  public Collection<Sort> getAllSort() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.getAllSort ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Sort.class);
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
  public Paging<Sort> pagingGetSort(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.pagingGetSort ");
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
      Paging<Sort> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Sort> sortSet = this.query(querySql, Sort.class);
        if (ToolUtil.isNotEmpty(sortSet)) {
          Set<String> inSortId = new LinkedHashSet<>();
          for (Sort sort : sortSet) {
            inSortId.add(sort.getSortId());
          }
          SortQuery sortQuery = new SortQuery();
          sortQuery.setSortIdAndin(new ArrayList<>(inSortId));
          Collection<Sort> rSortSet = this.querySort(sortQuery);
          if (ToolUtil.isNotEmpty(rSortSet)) {
            paging.setData(rSortSet);
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
  public Collection<Sort> querySort(SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.querySort ");
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Sort.class, sortQuery);
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
  public Paging<Sort> pagingQuerySort(Parameter parameter, SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.pagingQuerySort ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sortQuery is : " + sortQuery);
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
      Paging<Sort> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Sort> sortSet = this.query(querySql, Sort.class, sortQuery);
        if (ToolUtil.isNotEmpty(sortSet)) {
          Set<String> inSortId = new LinkedHashSet<>();
          for (Sort sort : sortSet) {
            inSortId.add(sort.getSortId());
          }
          SortQuery rSortQuery = new SortQuery();
          rSortQuery.setSortIdAndin(new ArrayList<>(inSortId));
          Collection<Sort> rSortSet = this.querySort(rSortQuery);
          if (ToolUtil.isNotEmpty(rSortSet)) {
            paging.setData(rSortSet);
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
  public boolean isUnique(SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call SortPersistent.isUnique ");
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      Long count = this.queryCount(countSql, sortQuery, Long.class);
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
  public String getNotUniqueErrorMessage(SortQuery sortQuery) throws CodePluginException {
    return this.getNotUniqueErrorMsg(COLUMNS, COLUMNS_PARAMETER, sortQuery).toString();
  }

}
