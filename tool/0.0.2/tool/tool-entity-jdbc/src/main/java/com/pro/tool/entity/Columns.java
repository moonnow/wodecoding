package com.pro.tool.entity;

public class Columns implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String columnsId;

  protected java.lang.String columnName;

  protected java.lang.String columnNameAnnotation;

  protected java.lang.String dataType;

  protected java.lang.String isNull;

  protected java.lang.String initialCaseColumnName;

  protected java.lang.String initialLowercaseColumnName;

  protected java.lang.Integer weightOrder;

  protected java.lang.String dtId;

  public java.lang.String getColumnsId() {
    return columnsId;
  }

  public void setColumnsId(java.lang.String columnsId) {
    this.columnsId = columnsId;
  }

  public java.lang.String getColumnName() {
    return columnName;
  }

  public void setColumnName(java.lang.String columnName) {
    this.columnName = columnName;
  }

  public java.lang.String getColumnNameAnnotation() {
    return columnNameAnnotation;
  }

  public void setColumnNameAnnotation(java.lang.String columnNameAnnotation) {
    this.columnNameAnnotation = columnNameAnnotation;
  }

  public java.lang.String getDataType() {
    return dataType;
  }

  public void setDataType(java.lang.String dataType) {
    this.dataType = dataType;
  }

  public java.lang.String getIsNull() {
    return isNull;
  }

  public void setIsNull(java.lang.String isNull) {
    this.isNull = isNull;
  }

  public java.lang.String getInitialCaseColumnName() {
    return initialCaseColumnName;
  }

  public void setInitialCaseColumnName(java.lang.String initialCaseColumnName) {
    this.initialCaseColumnName = initialCaseColumnName;
  }

  public java.lang.String getInitialLowercaseColumnName() {
    return initialLowercaseColumnName;
  }

  public void setInitialLowercaseColumnName(java.lang.String initialLowercaseColumnName) {
    this.initialLowercaseColumnName = initialLowercaseColumnName;
  }

  public java.lang.Integer getWeightOrder() {
    return weightOrder;
  }

  public void setWeightOrder(java.lang.Integer weightOrder) {
    this.weightOrder = weightOrder;
  }

  public java.lang.String getDtId() {
    return dtId;
  }

  public void setDtId(java.lang.String dtId) {
    this.dtId = dtId;
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