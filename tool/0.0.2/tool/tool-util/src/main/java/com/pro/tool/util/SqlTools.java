package com.pro.tool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public final class SqlTools {

  // 从sql文件绝对路径获取表信息字符串
  public static String getTableInfoStrFromSqlPath(String str) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    String str3 = null;
    File file = new File(str);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      while ((str2 = reader.readLine()) != null) {
        str1.append(str2).append("\n");
      }
      str3 = str1.toString().substring(str1.toString().indexOf("CREATE TABLE"));
      str3 = str3.substring(0, str3.indexOf(";") + 1);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    return str3;
  }

  // 传入数据库表字符串返回表名
  public static String getDtNameFromDtStr(String str) {
    str = str.substring(str.indexOf("`") + 1);
    str = str.substring(0, str.indexOf("`"));
    return str.trim().toUpperCase();
  }

  // 传入数据库表字符串返回表名注释
  public static String getDtNameAnnotationFromDtStr(String str) {
    return str.substring(str.indexOf("='") + 2, str.indexOf("';")).trim();
  }

  // 传入字符串(以"_"分隔)以及大小写控制变量返回首字母大写或小写驼峰，true返回大写，false返回小写
  public static String getInitialCaseOrInitialLowercaseHumpFromStr(String str, Boolean initialCaseOrLowercase) {
    StringBuilder initialCase = new StringBuilder();
    StringBuilder initialLowercase = new StringBuilder();
    if (str.indexOf("_") == -1) {
      if (str.length() == 1) {
        initialCase.append(str.toUpperCase());
        initialLowercase.append(str.toLowerCase());
      } else {
        initialCase.append(str.substring(0, 1).toUpperCase());
        initialLowercase.append(str.substring(0, 1).toLowerCase());
        initialCase.append(str.substring(1).toLowerCase());
        initialLowercase.append(str.substring(1).toLowerCase());
      }
    } else {
      String str1 = str.substring(0, str.indexOf("_"));
      str = str.substring(str.indexOf("_") + 1);
      if (str1.length() == 1) {
        initialCase.append(str1.toUpperCase());
        initialLowercase.append(str1.toLowerCase());
      } else {
        initialCase.append(str1.substring(0, 1).toUpperCase());
        initialLowercase.append(str1.substring(0, 1).toLowerCase());
        initialCase.append(str1.substring(1).toLowerCase());
        initialLowercase.append(str1.substring(1).toLowerCase());
      }
      while (str.indexOf("_") != -1) {
        String str2 = str.substring(0, str.indexOf("_"));
        str = str.substring(str.indexOf("_") + 1);
        if (str2.length() == 1) {
          initialCase.append(str2.toUpperCase());
          initialLowercase.append(str2.toUpperCase());
        } else {
          initialCase.append(str2.substring(0, 1).toUpperCase());
          initialLowercase.append(str2.substring(0, 1).toUpperCase());
          initialCase.append(str2.substring(1).toLowerCase());
          initialLowercase.append(str2.substring(1).toLowerCase());
        }
      }
      if (str.length() == 1) {
        initialCase.append(str.toUpperCase());
        initialLowercase.append(str.toUpperCase());
      } else {
        initialCase.append(str.substring(0, 1).toUpperCase());
        initialLowercase.append(str.substring(0, 1).toUpperCase());
        initialCase.append(str.substring(1).toLowerCase());
        initialLowercase.append(str.substring(1).toLowerCase());
      }
    }
    if (initialCaseOrLowercase) {
      return initialCase.toString();
    } else {
      return initialLowercase.toString();
    }
  }

  // 传入数据库表字符串返回列map
  public static LinkedHashMap<Integer, String> getColumnsMapFromDtStr(String str) {
    LinkedHashMap<Integer, String> columnsMap = new LinkedHashMap<Integer, String>();
    int code = 1;
    str = str.substring(str.indexOf("(") + 1);
    while (str.indexOf(",\n") != -1) {
      columnsMap.put(code, str.substring(0, str.indexOf(",\n") + 1).replaceAll("\n", "").trim());
      code++;
      str = str.substring(str.indexOf(",\n") + 1);
    }
    columnsMap.put(code, str.substring(0, str.indexOf("\n)")).replaceAll("\n", "").trim());
    return columnsMap;
  }

  // 传入数据库表列字符串返回列名
  public static String getColumnsNameFromDtColumnsStr(String str) {
    str = str.substring(str.indexOf("`") + 1);
    str = str.substring(0, str.indexOf("`"));
    return str.trim().toUpperCase();
  }

  // 传入数据库表列字符串返回列名注释
  public static String getColumnsNameAnnotationFromDtColumnsStr(String str) {
    if (-1 == str.indexOf("'")) {
      return str.trim();
    } else {
      str = str.substring(str.indexOf("'") + 1);
      str = str.substring(0, str.indexOf("'"));
      return str.trim();
    }
  }

  // 传入数据库表列字符串返回数据类型
  public static String getDataTypeFromDtColumnsStr(String str) {
    if (-1 == str.indexOf(" ")) {
      return str.trim();
    } else {
      str = str.substring(str.indexOf(" ") + 1);
      if (-1 == str.indexOf(" ")) {
        str = str.substring(0, str.length() - 1);
        return str.trim();
      } else {
        str = str.substring(0, str.indexOf(" "));
        return str.trim();
      }
    }
  }

  // 传入数据库表列字符串返回是否为空
  public static String getIsNullFromDtColumnsStr(String str) {
    if (-1 != str.indexOf("NOT NULL")) {
      return "n";
    }
    if (-1 != str.indexOf("DEFAULT NULL")) {
      return "y";
    }
    return "y";
  }

  // 传入数据库表数据类型字符串返回java数据类型
  public static String getJavaDataTypeFromDtDataTypeStr(String str) {
    if (str.indexOf("varchar") != -1) {
      return "java.lang.String";
    } else if (str.indexOf("char") != -1) {
      return "java.lang.String";
    } else if (str.indexOf("bigint") != -1) {
      return "java.lang.Long";
    } else if (str.indexOf("integer") != -1) {
      return "java.lang.Integer";
    } else if (str.indexOf("int") != -1) {
      return "java.lang.Integer";
    } else if (str.indexOf("decimal") != -1) {
      return "java.math.BigDecimal";
    } else if (str.indexOf("text") != -1) {
      return "java.lang.String";
    } else if (str.indexOf("date") != -1) {
      return "java.util.Date";
    } else {
      return "java.lang.String";
    }
  }

  // 传入数据库表数据类型字符串返回Angular数据类型
  public static String getAngularDataTypeFromDtDataTypeStr(String str) {
    if (str.indexOf("varchar") != -1) {
      return "string";
    } else if (str.indexOf("char") != -1) {
      return "string";
    } else if (str.indexOf("bigint") != -1) {
      return "number";
    } else if (str.indexOf("integer") != -1) {
      return "number";
    } else if (str.indexOf("int") != -1) {
      return "number";
    } else if (str.indexOf("decimal") != -1) {
      return "number";
    } else if (str.indexOf("text") != -1) {
      return "string";
    } else if (str.indexOf("date") != -1) {
      return "string";
    } else {
      return "string";
    }
  }

  // 传入数据库表列字符串返回主键列名
  public static String getPrimaryKeyColumnNameFromDtColumnsStr(String str) {
    str = str.substring(str.indexOf("`") + 1);
    str = str.substring(0, str.indexOf("`"));
    return str.trim().toUpperCase();
  }

}
