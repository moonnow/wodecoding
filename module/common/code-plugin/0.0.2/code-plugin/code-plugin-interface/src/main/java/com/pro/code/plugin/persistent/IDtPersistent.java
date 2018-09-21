package com.pro.code.plugin.persistent;

import java.util.Collection;

import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.DtQuery;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

public interface IDtPersistent {

  public static final String TABLE_NAME = "PRO_DT";

  public void saveDt(Dt dt) throws CodePluginException;

  public void batchSaveDt(Collection<Dt> dts) throws CodePluginException;

  public void updateDt(Dt dt) throws CodePluginException;

  public void batchUpdateDt(Collection<Dt> dts) throws CodePluginException;

  public void removeDt(Dt dt) throws CodePluginException;

  public void batchRemoveDt(Collection<Dt> dts) throws CodePluginException;

  public Long getCountDt(DtQuery dtQuery) throws CodePluginException;

  public Dt getDtByPk(java.lang.String dtId) throws CodePluginException;

  public Collection<Dt> getAllDt() throws CodePluginException;

  public Paging<Dt> pagingGetDt(Parameter parameter) throws CodePluginException;

  public Collection<Dt> queryDt(DtQuery dtQuery) throws CodePluginException;

  public Paging<Dt> pagingQueryDt(Parameter parameter, DtQuery dtQuery) throws CodePluginException;

  public boolean isUnique(DtQuery dtQuery) throws CodePluginException;

  public String getNotUniqueErrorMessage(DtQuery dtQuery) throws CodePluginException;

}
