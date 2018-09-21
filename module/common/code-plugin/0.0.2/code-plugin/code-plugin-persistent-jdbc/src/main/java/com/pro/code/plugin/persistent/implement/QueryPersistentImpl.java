package com.pro.code.plugin.persistent.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.pro.code.plugin.entity.Query;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.persistent.IQueryPersistent;
import com.pro.code.plugin.query.QueryQuery;
import com.pro.tool.persistent.implement.ToolPersistent;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Repository("com.pro.code.plugin.QueryPersistent")
public class QueryPersistentImpl extends ToolPersistent implements IQueryPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(QueryPersistentImpl.class);

  public static final String TABLE_ALIAS = "query";

  public static final String COLUMN_QUERY_ID = "queryId";
  public static final String COLUMN_QUERY_TYPE = "queryType";
  public static final String COLUMN_DT_ID = "dtId";
  public static final String COLUMN_COLUMNS_ID = "columnsId";
  public static final String COLUMN_WEIGHT_ORDER = "weightOrder";

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
    PRIMARY_KEY.add(COLUMN_QUERY_ID);

    COLUMNS.add(COLUMN_QUERY_ID);
    COLUMNS.add(COLUMN_QUERY_TYPE);
    COLUMNS.add(COLUMN_DT_ID);
    COLUMNS.add(COLUMN_COLUMNS_ID);
    COLUMNS.add(COLUMN_WEIGHT_ORDER);

    COLUMNS_PARAMETER.put(COLUMN_QUERY_ID, "QUERY_ID");
    COLUMNS_PARAMETER.put(COLUMN_QUERY_TYPE, "QUERY_TYPE");
    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_COLUMNS_ID, "COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_WEIGHT_ORDER, "WEIGHT_ORDER");

    SORT.put(COLUMN_WEIGHT_ORDER, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public void saveQuery(Query query) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.saveQuery ");
      log.debug("parameter query is : " + query);
    }
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      this.insert(INSERT_SQL, query);
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
  public void batchSaveQuery(Collection<Query> querys) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.batchSaveQuery ");
      log.debug("parameter querys is : " + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      this.insert(INSERT_SQL, querys);
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
  public void updateQuery(Query query) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.updateQuery ");
      log.debug("parameter query is : " + query);
    }
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      this.update(UPDATE_SQL, query);
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
  public void batchUpdateQuery(Collection<Query> querys) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.batchUpdateQuery ");
      log.debug("parameter querys is : " + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      this.update(UPDATE_SQL, querys);
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
  public void removeQuery(Query query) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.removeQuery ");
      log.debug("parameter query is : " + query);
    }
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, query.getQueryId());
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
  public void batchRemoveQuery(Collection<Query> querys) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.batchRemoveQuery ");
      log.debug("parameter querys is : " + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      this.del(DEL_SQL_BY_PK, querys);
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
  public Long getCountQuery(QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.getCountQuery ");
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      return this.queryCount(countSql, queryQuery, Long.class);
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
  public Query getQueryByPk(java.lang.String queryId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.getQueryByPk ");
      log.debug("parameter queryId is : " + queryId);
    }
    try {
      if (ToolUtil.isNullStr(queryId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " queryId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, queryId);
        }
      }
      Collection<Query> querySet = this.query(querySql, mapSqlParameterSource, Query.class);
      return ToolUtil.isNotEmpty(querySet) ? querySet.iterator().next() : null;
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
  public Collection<Query> getAllQuery() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.getAllQuery ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Query.class);
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
  public Paging<Query> pagingGetQuery(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.pagingGetQuery ");
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
      Paging<Query> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Query> querySet = this.query(querySql, Query.class);
        if (ToolUtil.isNotEmpty(querySet)) {
          Set<String> inQueryId = new LinkedHashSet<>();
          for (Query query : querySet) {
            inQueryId.add(query.getQueryId());
          }
          QueryQuery queryQuery = new QueryQuery();
          queryQuery.setQueryIdAndin(new ArrayList<>(inQueryId));
          Collection<Query> rQuerySet = this.queryQuery(queryQuery);
          if (ToolUtil.isNotEmpty(rQuerySet)) {
            paging.setData(rQuerySet);
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
  public Collection<Query> queryQuery(QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.queryQuery ");
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Query.class, queryQuery);
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
  public Paging<Query> pagingQueryQuery(Parameter parameter, QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.pagingQueryQuery ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter queryQuery is : " + queryQuery);
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
      Paging<Query> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Query> querySet = this.query(querySql, Query.class, queryQuery);
        if (ToolUtil.isNotEmpty(querySet)) {
          Set<String> inQueryId = new LinkedHashSet<>();
          for (Query query : querySet) {
            inQueryId.add(query.getQueryId());
          }
          QueryQuery rQueryQuery = new QueryQuery();
          rQueryQuery.setQueryIdAndin(new ArrayList<>(inQueryId));
          Collection<Query> rQuerySet = this.queryQuery(rQueryQuery);
          if (ToolUtil.isNotEmpty(rQuerySet)) {
            paging.setData(rQuerySet);
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
  public boolean isUnique(QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call QueryPersistent.isUnique ");
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      Long count = this.queryCount(countSql, queryQuery, Long.class);
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
  public String getNotUniqueErrorMessage(QueryQuery queryQuery) throws CodePluginException {
    return this.getNotUniqueErrorMsg(COLUMNS, COLUMNS_PARAMETER, queryQuery).toString();
  }

}
