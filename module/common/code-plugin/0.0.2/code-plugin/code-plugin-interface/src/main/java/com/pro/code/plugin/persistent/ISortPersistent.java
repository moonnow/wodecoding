package com.pro.code.plugin.persistent;

import java.util.Collection;

import com.pro.code.plugin.entity.Sort;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.SortQuery;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

public interface ISortPersistent {

  public static final String TABLE_NAME = "PRO_SORT";

  public void saveSort(Sort sort) throws CodePluginException;

  public void batchSaveSort(Collection<Sort> sorts) throws CodePluginException;

  public void updateSort(Sort sort) throws CodePluginException;

  public void batchUpdateSort(Collection<Sort> sorts) throws CodePluginException;

  public void removeSort(Sort sort) throws CodePluginException;

  public void batchRemoveSort(Collection<Sort> sorts) throws CodePluginException;

  public Long getCountSort(SortQuery sortQuery) throws CodePluginException;

  public Sort getSortByPk(java.lang.String sortId) throws CodePluginException;

  public Collection<Sort> getAllSort() throws CodePluginException;

  public Paging<Sort> pagingGetSort(Parameter parameter) throws CodePluginException;

  public Collection<Sort> querySort(SortQuery sortQuery) throws CodePluginException;

  public Paging<Sort> pagingQuerySort(Parameter parameter, SortQuery sortQuery) throws CodePluginException;

  public boolean isUnique(SortQuery sortQuery) throws CodePluginException;

  public String getNotUniqueErrorMessage(SortQuery sortQuery) throws CodePluginException;

}
