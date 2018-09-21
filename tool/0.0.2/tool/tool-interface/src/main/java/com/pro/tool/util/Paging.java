package com.pro.tool.util;

public class Paging<T> implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Long count;

  private Long offset;

  private Long rows;

  private Long page;

  private java.util.Collection<T> data;

  public Paging() {
    super();
  }

  public Paging(Parameter parameter) {
    this();
    this.offset = (parameter.getPage() - 1) * parameter.getRows();
    this.rows = parameter.getRows();
    this.page = parameter.getPage();
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getOffset() {
    return offset;
  }

  public void setOffset(Long offset) {
    this.offset = offset;
  }

  public Long getRows() {
    return rows;
  }

  public void setRows(Long rows) {
    this.rows = rows;
  }

  public Long getPage() {
    return page;
  }

  public void setPage(Long page) {
    this.page = page;
  }

  public java.util.Collection<T> getData() {
    return data;
  }

  public void setData(java.util.Collection<T> data) {
    this.data = data;
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
