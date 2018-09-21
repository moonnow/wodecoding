package com.pro.code.plugin.persistent;

import java.util.Collection;

import com.pro.code.plugin.entity.Query;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.QueryQuery;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

public interface IQueryPersistent {

  public static final String TABLE_NAME = "PRO_QUERY";

  public void saveQuery(Query query) throws CodePluginException;

  public void batchSaveQuery(Collection<Query> querys) throws CodePluginException;

  public void updateQuery(Query query) throws CodePluginException;

  public void batchUpdateQuery(Collection<Query> querys) throws CodePluginException;

  public void removeQuery(Query query) throws CodePluginException;

  public void batchRemoveQuery(Collection<Query> querys) throws CodePluginException;

  public Long getCountQuery(QueryQuery queryQuery) throws CodePluginException;

  public Query getQueryByPk(java.lang.String queryId) throws CodePluginException;

  public Collection<Query> getAllQuery() throws CodePluginException;

  public Paging<Query> pagingGetQuery(Parameter parameter) throws CodePluginException;

  public Collection<Query> queryQuery(QueryQuery queryQuery) throws CodePluginException;

  public Paging<Query> pagingQueryQuery(Parameter parameter, QueryQuery queryQuery) throws CodePluginException;

  public boolean isUnique(QueryQuery queryQuery) throws CodePluginException;

  public String getNotUniqueErrorMessage(QueryQuery queryQuery) throws CodePluginException;

}
