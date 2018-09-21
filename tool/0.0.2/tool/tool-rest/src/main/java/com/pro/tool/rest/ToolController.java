package com.pro.tool.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class ToolController {

  @InitBinder
  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired(required = false)
  private HttpServletRequest httpServletRequest;

  @Autowired(required = false)
  private HttpServletResponse httpServletResponse;

  protected static final java.util.HashMap<String, String> SUFFIXMAP = new java.util.HashMap<String, String>();

  static {
    SUFFIXMAP.put("wav", "audio/x-wav");
    SUFFIXMAP.put("vox", "audio/voxware");
    SUFFIXMAP.put("txt", "text/plain");
    SUFFIXMAP.put("xls", "application/vnd.ms-excel");
    SUFFIXMAP.put("doc", "application/vnd.ms-word");
    SUFFIXMAP.put("dat", "application/octet-stream");
    SUFFIXMAP.put("pdf", "application/pdf");
    SUFFIXMAP.put("rtf", "application/rtf");
    SUFFIXMAP.put("ppt", "application/ms-powerpoint");
    SUFFIXMAP.put("wps", "application/ms-works");
    SUFFIXMAP.put("hlp", "application/winhlp");
    SUFFIXMAP.put("tgz", "application/x-compressed");
    SUFFIXMAP.put("gz", "application/x-gzip");
    SUFFIXMAP.put("wri", "application/x-mswrite");
    SUFFIXMAP.put("tar", "application/x-tar");
    SUFFIXMAP.put("zip", "application/zip");
    SUFFIXMAP.put("mp3", "audio/mpeg");
    SUFFIXMAP.put("wma", "audio/x-ms-wma");
    SUFFIXMAP.put("rar", "application/x-rar-compressed");
    SUFFIXMAP.put("art", "image/x-jg");
    SUFFIXMAP.put("bmp", "image/bmp");
    SUFFIXMAP.put("dib", "image/bmp");
    SUFFIXMAP.put("gif", "image/gif");
    SUFFIXMAP.put("ief", "image/ief");
    SUFFIXMAP.put("jpe", "image/jpeg");
    SUFFIXMAP.put("jpeg", "image/jpeg");
    SUFFIXMAP.put("jpg", "image/jpeg");
    SUFFIXMAP.put("mac", "image/x-macpaint");
    SUFFIXMAP.put("odi", "application/vnd.oasis.opendocument.image");
    SUFFIXMAP.put("pbm", "image/x-portable-bitmap");
    SUFFIXMAP.put("pct", "image/pict");
    SUFFIXMAP.put("pgm", "image/x-portable-graymap");
    SUFFIXMAP.put("pic", "image/pict");
    SUFFIXMAP.put("pict", "image/pict");
    SUFFIXMAP.put("png", "image/png");
    SUFFIXMAP.put("pnm", "image/x-portable-anymap");
    SUFFIXMAP.put("pnt", "image/x-macpaint");
    SUFFIXMAP.put("ppm", "image/x-portable-pixmap");
    SUFFIXMAP.put("psd", "image/x-photoshop");
    SUFFIXMAP.put("qti", "image/x-quicktime");
    SUFFIXMAP.put("qtif", "image/x-quicktime");
    SUFFIXMAP.put("ras", "image/x-cmu-raster");
    SUFFIXMAP.put("rgb", "image/x-rgb");
    SUFFIXMAP.put("svg", "image/svg+xml");
    SUFFIXMAP.put("svgz", "image/svg+xml");
    SUFFIXMAP.put("tif", "image/tiff");
    SUFFIXMAP.put("tiff", "image/tiff");
    SUFFIXMAP.put("xbm", "image/x-xbitmap");
    SUFFIXMAP.put("xpm", "image/x-xpixmap");
    SUFFIXMAP.put("xwd", "image/x-xwindowdump");
    SUFFIXMAP.put("wbmp", "image/vnd.wap.wbmp");
  }

  public HttpServletRequest getHttpServletRequest() {
    return httpServletRequest;
  }

  public HttpServletResponse getHttpServletResponse() {
    return httpServletResponse;
  }

  public String getMimeType(String suffix) {
    return SUFFIXMAP.get(suffix.toLowerCase());
  }

}
