package com.pro.tool.entity;

public class Dt implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String dtId;

  protected java.lang.String dtSql;

  protected java.lang.String dtName;

  protected java.lang.String dtNameAnnotation;

  protected java.lang.String dtPrefix;

  protected java.lang.String initialCaseEntityName;

  protected java.lang.String initialLowercaseEntityName;

  protected java.lang.String proPath;

  protected java.lang.String proAllName;

  protected java.lang.String proName;

  public java.lang.String getDtId() {
    return dtId;
  }

  public void setDtId(java.lang.String dtId) {
    this.dtId = dtId;
  }

  public java.lang.String getDtSql() {
    return dtSql;
  }

  public void setDtSql(java.lang.String dtSql) {
    this.dtSql = dtSql;
  }

  public java.lang.String getDtName() {
    return dtName;
  }

  public void setDtName(java.lang.String dtName) {
    this.dtName = dtName;
  }

  public java.lang.String getDtNameAnnotation() {
    return dtNameAnnotation;
  }

  public void setDtNameAnnotation(java.lang.String dtNameAnnotation) {
    this.dtNameAnnotation = dtNameAnnotation;
  }

  public java.lang.String getDtPrefix() {
    return dtPrefix;
  }

  public void setDtPrefix(java.lang.String dtPrefix) {
    this.dtPrefix = dtPrefix;
  }

  public java.lang.String getInitialCaseEntityName() {
    return initialCaseEntityName;
  }

  public void setInitialCaseEntityName(java.lang.String initialCaseEntityName) {
    this.initialCaseEntityName = initialCaseEntityName;
  }

  public java.lang.String getInitialLowercaseEntityName() {
    return initialLowercaseEntityName;
  }

  public void setInitialLowercaseEntityName(java.lang.String initialLowercaseEntityName) {
    this.initialLowercaseEntityName = initialLowercaseEntityName;
  }

  public java.lang.String getProPath() {
    return proPath;
  }

  public void setProPath(java.lang.String proPath) {
    this.proPath = proPath;
  }

  public java.lang.String getProAllName() {
    return proAllName;
  }

  public void setProAllName(java.lang.String proAllName) {
    this.proAllName = proAllName;
  }

  public java.lang.String getProName() {
    return proName;
  }

  public void setProName(java.lang.String proName) {
    this.proName = proName;
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