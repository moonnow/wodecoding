package com.pro.tool.util;

import java.util.Collections;

import com.pro.tool.exception.ToolException;

public class Responses<T> implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private int statusCode;

  private Long count;

  private Long rows;

  private Long page;

  private java.util.Collection<T> data;

  private boolean isSuccess;

  private String msg;

  public Responses() {
    super();
    this.statusCode = 200;
    this.count = 0l;
    this.rows = 10l;
    this.page = 1l;
    this.data = Collections.emptyList();
    this.isSuccess = true;
  }

  public Responses(Parameter parameter) {
    super();
    this.statusCode = 200;
    this.count = 0l;
    this.rows = parameter.getRows();
    this.page = parameter.getPage();
    this.data = Collections.emptyList();
    this.isSuccess = true;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
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
    this.count = (long) data.size();
    this.data = data;
  }

  public void setData(T data) {
    this.count = 1l;
    this.data = new java.util.ArrayList<T>();
    this.data.add(data);
  }

  public void setData(Paging<T> paging) {
    this.count = paging.getCount();
    this.rows = paging.getRows();
    this.page = paging.getPage();
    if (paging.getData() == null) {
      this.data = Collections.emptyList();
    } else {
      this.data = paging.getData();
    }
  }

  public boolean getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public final void setException(Exception e) {
    isSuccess = false;
    if (e instanceof ToolException) {
      statusCode = ((ToolException) e).getErrCode();
      msg = ((ToolException) e).getStrErrorMessage();
    } else {
      statusCode = 500;
      msg = e.getMessage();
    }
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
