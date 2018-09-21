package com.pro.code.plugin.entity;

public class Sort implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String sortId; // 排序编号

  protected java.lang.String sortRule; // 排序规则

  protected java.lang.String dtId; // 数据库表编号

  protected java.lang.String columnsId; // 列编号

  public java.lang.String getSortId() {
    return sortId;
  }

  public void setSortId(java.lang.String sortId) {
    this.sortId = sortId;
  }

  public java.lang.String getSortRule() {
    return sortRule;
  }

  public void setSortRule(java.lang.String sortRule) {
    this.sortRule = sortRule;
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
