package com.pro.tool.exception;

import java.io.UnsupportedEncodingException;

public final class CharsetConvertUtilities {

  private CharsetConvertUtilities() {
    super();
  }

  public static String unicodeToGB(String strIn) {
    if (strIn == null || strIn.equals("")) {
      return strIn;
    }
    String strOut;
    try {
      strOut = new String(strIn.getBytes("GBK"), "ISO8859_1");
      return strOut;
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }

  public static String GBToUnicode(String strIn) {
    if (strIn == null || strIn.equals("")) {
      return strIn;
    }
    String strOut;
    try {
      strOut = new String(strIn.getBytes("ISO8859_1"), "GBK");
      return strOut;
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }

}
