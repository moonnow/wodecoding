package com.pro.tool.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class ErrorMessageUtilities {
  private static Log log = LogFactory.getLog(ErrorMessageUtilities.class);

  public static void loadErrorMessages(Properties errorMessages, InputStream is) {
    if (is != null) {
      try {
        errorMessages.load(is);
      } catch (IOException ioe) {
        if (log.isErrorEnabled()) {
          log.error("ErrorCode.properties load is error !!!", ioe);
        }
      }
    }
  }

  /**
   * 通过ErrorCode获取ErrorCode.properties文件当中对应的异常消息
   *
   * @param errorMessages
   *          全部异常消息
   * @param errCode
   *          异常编号{ErrorCode.properties}文件中当的KEY
   * @return 异常消息
   */
  public static String codeToMessage(Properties errorMessages, int errCode) {
    String errCodeStr = Integer.toString(errCode);
    if (errorMessages == null) {
      return errCodeStr;
    }
    String strErrorMessage = errorMessages.getProperty(errCodeStr);
    strErrorMessage = CharsetConvertUtilities.GBToUnicode(strErrorMessage);
    return strErrorMessage;
  }

  /**
   * 通过ErrorCode获取ErrorCode.properties文件当中对应的异常消息
   *
   * @param errorMessages
   *          全部异常消息
   * @param errCode
   *          异常编号{ErrorCode.properties}文件中当的KEY
   * @param lstPattern
   *          异常消息对应参数值
   * @return 异常消息
   */
  public static String codeToMessage(Properties errorMessages, int errCode, String[] lstPattern) {
    String strErrorMessage = codeToMessage(errorMessages, errCode);
    if (strErrorMessage != null && lstPattern != null) {
      for (int i = 0; i < lstPattern.length; i++) {
        strErrorMessage = strErrorMessage.replaceAll("%" + (i + 1), lstPattern[i]);
      }
    }
    return strErrorMessage;
  }

}
