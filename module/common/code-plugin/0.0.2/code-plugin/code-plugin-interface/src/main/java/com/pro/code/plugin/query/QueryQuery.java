package com.pro.code.plugin.query;

public class QueryQuery implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String queryId; // 查询编号

  protected java.lang.String queryType; // 查询类型

  protected java.lang.String dtId; // 数据库表编号

  protected java.lang.String columnsId; // 列编号

  protected java.lang.Integer weightOrder;

  protected java.util.List<java.lang.String> queryIdAndin;

  protected java.lang.String queryTypeAndeq;

  protected java.lang.String dtIdAndeq;

  protected java.lang.String columnsIdAndeq;

  public java.lang.String getQueryId() {
    return queryId;
  }

  public void setQueryId(java.lang.String queryId) {
    this.queryId = queryId;
  }

  public java.lang.String getQueryType() {
    return queryType;
  }

  public void setQueryType(java.lang.String queryType) {
    this.queryType = queryType;
  }

  public java.lang.String getDtId() {
    return dtId;
  }

  public void setDtId(java.lang.String dtId) {
    this.dtId = dtId;
  }

  public java.lang.String getColumnsId() {
    return columnsId;
  }

  public void setColumnsId(java.lang.String columnsId) {
    this.columnsId = columnsId;
  }

  public java.lang.Integer getWeightOrder() {
    return weightOrder;
  }

  public void setWeightOrder(java.lang.Integer weightOrder) {
    this.weightOrder = weightOrder;
  }

  public java.util.List<java.lang.String> getQueryIdAndin() {
    return queryIdAndin;
  }

  public void setQueryIdAndin(java.util.List<java.lang.String> queryIdAndin) {
    this.queryIdAndin = queryIdAndin;
  }

  public java.lang.String getQueryTypeAndeq() {
    return queryTypeAndeq;
  }

  public void setQueryTypeAndeq(java.lang.String queryTypeAndeq) {
    this.queryTypeAndeq = queryTypeAndeq;
  }

  public java.lang.String getDtIdAndeq() {
    return dtIdAndeq;
  }

  public void setDtIdAndeq(java.lang.String dtIdAndeq) {
    this.dtIdAndeq = dtIdAndeq;
  }

  public java.lang.String getColumnsIdAndeq() {
    return columnsIdAndeq;
  }

  public void setColumnsIdAndeq(java.lang.String columnsIdAndeq) {
    this.columnsIdAndeq = columnsIdAndeq;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object object) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, object);
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }

}
