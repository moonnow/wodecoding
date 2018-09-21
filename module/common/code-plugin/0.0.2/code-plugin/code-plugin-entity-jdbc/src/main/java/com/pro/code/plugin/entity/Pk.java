package com.pro.code.plugin.entity;

public class Pk implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String pkId; // 主键编号

  protected java.lang.String dtId; // 数据库表编号

  protected java.lang.String columnsId; // 列编号

  public java.lang.String getPkId() {
    return pkId;
  }

  public void setPkId(java.lang.String pkId) {
    this.pkId = pkId;
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
