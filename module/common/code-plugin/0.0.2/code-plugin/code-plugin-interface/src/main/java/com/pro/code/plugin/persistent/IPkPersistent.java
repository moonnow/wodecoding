package com.pro.code.plugin.persistent;

import java.util.Collection;

import com.pro.code.plugin.entity.Pk;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.PkQuery;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

public interface IPkPersistent {

  public static final String TABLE_NAME = "PRO_PK";

  public void savePk(Pk pk) throws CodePluginException;

  public void batchSavePk(Collection<Pk> pks) throws CodePluginException;

  public void updatePk(Pk pk) throws CodePluginException;

  public void batchUpdatePk(Collection<Pk> pks) throws CodePluginException;

  public void removePk(Pk pk) throws CodePluginException;

  public void batchRemovePk(Collection<Pk> pks) throws CodePluginException;

  public Long getCountPk(PkQuery pkQuery) throws CodePluginException;

  public Pk getPkByPk(java.lang.String pkId) throws CodePluginException;

  public Collection<Pk> getAllPk() throws CodePluginException;

  public Paging<Pk> pagingGetPk(Parameter parameter) throws CodePluginException;

  public Collection<Pk> queryPk(PkQuery pkQuery) throws CodePluginException;

  public Paging<Pk> pagingQueryPk(Parameter parameter, PkQuery pkQuery) throws CodePluginException;

  public boolean isUnique(PkQuery pkQuery) throws CodePluginException;

  public String getNotUniqueErrorMessage(PkQuery pkQuery) throws CodePluginException;

}
