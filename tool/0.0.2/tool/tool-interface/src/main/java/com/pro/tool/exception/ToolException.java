package com.pro.tool.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ToolException extends Exception {

  private static final long serialVersionUID = 1L;

  private static Log log = LogFactory.getLog(ToolException.class);

  public static final String FK_ERROR_MESSAGE_KAY = "a foreign key constraint fails";
  protected static final int FK_ERROR_MESSAGE_KAY_LENGTH = FK_ERROR_MESSAGE_KAY.length();

  public static final int FW_SUCCESS = 0; // 0=成功
  public static final int FW_UNKOWN_ERROR = -10000; // -10000=未知错误
  public static final int FW_ERROR = -10001; // -10001=错误：%1

  public static final int FW_DB_ERROR = -9000; // -9000=一般数据库错误
  public static final int FW_DAO_ACCESS_ERROR = -9001; // -9001=数据库错误: %1
  public static final int FW_UPDATE_DATA_STALE_ERROR = -9002; // -9002=操作数据的版本错误
  public static final int FW_SELECT_DATA_DELETE_ERROR = -9003; // -9003=此记录以被删除
  public static final int FW_PARAMETER_IS_NULL_ERROR = -9004; // -9004=操作参数{%1}为空
  public static final int FW_DATA_CONTENTION_ERROR = -9005; // -9005=数据已经存在{%1}

  public static final int FW_PARAMETER_IS_ERROR = -9006; // -9006=参数{%1}取值错误
  public static final int FW_DATA_IS_USE_ERROR = -9007;// -9007=数据正在被{%1}使用，不能删除
  public static final int FW_DB_CONNECT_ERROR = -9008;// -9008=数据库连接错误
  public static final int FW_CONNECT_ERROR = -9009; // -9009=连接错误
  public static final int FW_PARAMETER_CONT_LONG_ERROR = -9010; // -9010=参数{%1}最大不能超过{%2}
  public static final int FW_UPDATE_DATA_STALE_OR_DATA_CONTENTION_ERROR = -9011;// -9011=操作数据的版本错误或数据已经存在{%1}
  public static final int FW_DATA_NOT_ALLOWED_DELETE = -9012; // -9012=数据不允许删除
  public static final int FW_DATA_NOT_FIND_ERROR = -9013;// -9013=数据不存在
  public static final int FW_DATA_IS_LOCKING_NOT_UPDATE_ERROR = -9014;// -9014=数据已经被锁定不能进行修改
  public static final int FW_DATA_IS_FIXED_NOT_DELETE_ERROR = -9015;// -9015=固定数据不能删除
  public static final int FW_DATA_IS_LOCKING_NOT_DELETE_ERROR = -9016;// -9016=数据已经被锁定不能进行删除
  public static final int FW_UPDATE_DATA_NOT_FIND_ERROR = -9017;// -9017=要修改的数据不存在
  public static final int FW_DELETE_DATA_NOT_FIND_ERROR = -9018;// -9018=要删除的数据不存在
  public static final int FW_DATA_IS_FIXED_NOT_UPDATE_ERROR = -9019;// -9019=数据已经被锁定不能进行修改

  public static final int FW_NONEXISTING_ERRFILE = -8000; // -8000=错误代码配置文件不存在
  public static final int FW_UNICODE_TO_GB_ERROR = -8001; // -8001=unicode转换到GB错误
  public static final int FW_GB_TO_UNICODE_ERROR = -8002; // -8002=GB转换到unicode错误
  public static final int FW_INVALID_ERROR_CODE = -8003; // -8003=错误代码%1不存在

  public static final int FW_MD5ENCODE_UNSUPPORTED_ENCODING = -7000; // -7000=MD5签名中不支持的编码
  public static final int FW_MD5ENCODE_ALGORITHM_NOT_FOUND = -7001; // -7001=MD5签名的算法找不到
  public static final int FW_DESENCODE_UNSUPPORTED_ENCODING = -7002; // -7002=DES加密中不支持的编码
  public static final int FW_DESENCODE_ERROR = -7003; // -7003=DES加密错误
  public static final int FW_DESDECODE_UNSUPPORTED_ENCODING = -7004; // -7004=DES解密中不支持的编码
  public static final int FW_DESDECODE_ERROR = -7005; // -7005=DES解密错误
  public static final int FW_DATA_TRANSFORM_ERROR = -7006; // -7006=数据转换错误
  public static final int FW_SEQUENCE_EXCEEDS_MAXIMUM = -7007;// -7007=Sequence取值超预定义的最大值
  public static final int FW_SEQUENCE_GET_VALUE_ERROR = -7008;// -7008=Sequence取值方法应用错误,应该用%1

  public static final int FW_DATA_MEMENTO_FILE_TYPE_ERROR = -6000; // -6000=处理的文件类型错误
  public static final int FW_DATA_MEMENTO_FILE_NOT_FOUND_ERROR = -6001;// -6001=处理的文件%1未找到
  public static final int FW_DATA_MEMENTO_FILE_IO_ERROR = -6002; // -6002=处理的文件读取错误

  public static final int FW_VERIFY_CODE_ERROR = -5001; // -5001=验证码错误
  public static final int FW_NOT_AUTHORIZED_ERROR = -5002; // -5002=无权限操作

  private final static Properties errorMessages = new Properties();
  private static volatile boolean isLoadMessage = false;

  protected int errCode;
  protected String strErrorMessage;

  /**
   * 加载异常资源文件：ErrorCode.properties，默认编码为GBK
   */
  private static synchronized void reload() {
    if (!isLoadMessage) {
      isLoadMessage = true;
      InputStream is = ToolException.class.getResourceAsStream("ErrorCode.properties");
      if (is != null) {
        try {
          errorMessages.load(is);
          isLoadMessage = true;
        } catch (IOException ioe) {
          if (log.isErrorEnabled()) {
            log.error("ErrorCode.properties load is error !!!", ioe);
          }
        }
      }
    }
  }

  protected ToolException(int errCode, String errMessage, Exception innerException) {
    super(errMessage, innerException);
    this.errCode = errCode;
    this.strErrorMessage = errMessage;
  }

  public static ToolException getException(int errCode) {
    return getException(errCode, null, null);
  }

  public static ToolException getException(int errCode, String[] lstPattern) {
    return getException(errCode, lstPattern, null);
  }

  public static ToolException getException(int errCode, String[] lstPattern, Exception innerException) {
    String errMessage = itselfCodeToMessage(errCode, lstPattern);
    if (FW_UNKOWN_ERROR == errCode) {
      Throwable throwable = innerException;
      while (throwable != null) {
        if (throwable.getMessage().toLowerCase().indexOf("could not open connection") != -1) {
          errCode = FW_DB_CONNECT_ERROR;
          errMessage = itselfCodeToMessage(errCode, null);
          break;
        }
        throwable = throwable.getCause();
      }
    }
    return new ToolException(errCode, errMessage, innerException);
  }

  protected static String itselfCodeToMessage(int errCode, String[] lstPattern) {
    reload();
    return ErrorMessageUtilities.codeToMessage(errorMessages, errCode, lstPattern);
  }

  public int getErrCode() {
    return errCode;
  }

  public String getStrErrorMessage() {
    return strErrorMessage;
  }

}
