package com.pro.tool.util;

import java.util.LinkedHashMap;

public final class ConfigTools {

  public static final LinkedHashMap<String, String> configMap = new LinkedHashMap<String, String>();

  static {
    configMap.put("ci", "  ");
  }

  // 从配置参数得到jdbc实体类文件路径
  public static String getJdbcEntityFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "entity-jdbc/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "entity/" + initialCaseEntityName + ".java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到jdbc实体类包名
  public static String getEntityPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "entity";
      return proAllName;
    }
    return "";
  }

  // 传入字符串(以"-"分隔)以及大小写控制变量返回首字母大写或小写驼峰，true返回大写，false返回小写
  public static String getInitialCaseOrInitialLowercaseHumpFromStr(String str, Boolean initialCaseOrLowercase) {
    StringBuilder initialCase = new StringBuilder();
    StringBuilder initialLowercase = new StringBuilder();
    if (str.indexOf("-") == -1) {
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
      String str1 = str.substring(0, str.indexOf("-"));
      str = str.substring(str.indexOf("-") + 1);
      if (str1.length() == 1) {
        initialCase.append(str1.toUpperCase());
        initialLowercase.append(str1.toLowerCase());
      } else {
        initialCase.append(str1.substring(0, 1).toUpperCase());
        initialLowercase.append(str1.substring(0, 1).toLowerCase());
        initialCase.append(str1.substring(1).toLowerCase());
        initialLowercase.append(str1.substring(1).toLowerCase());
      }
      while (str.indexOf("-") != -1) {
        String str2 = str.substring(0, str.indexOf("-"));
        str = str.substring(str.indexOf("-") + 1);
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

  // 从配置参数得到异常类文件路径
  public static String getExceptionFilePathStrFromConfig(String proPath, String proAllName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "exception/" + getInitialCaseOrInitialLowercaseHumpFromStr(proAllName, true) + "Exception.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到异常类包名
  public static String getExceptionPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "exception";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到查询实体类文件路径
  public static String getQueryEntityFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/com/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "query/" + initialCaseEntityName + "Query.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到查询实体类包名
  public static String getQueryEntityPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "query";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到持久化接口类文件路径
  public static String getIPersistentFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "persistent/I" + initialCaseEntityName + "Persistent.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到持久化接口类包名
  public static String getIPersistentPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "persistent";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到jdbc持久化实现类文件路径
  public static String getJdbcPersistentImplFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "persistent-jdbc/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "persistent/implement/" + initialCaseEntityName + "PersistentImpl.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到jdbc持久化实现类包名
  public static String getJdbcPersistentImplPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "persistent.implement";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到持久化实现类名称前缀
  public static String getPersistentImplNamePrefixStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到服务接口类文件路径
  public static String getIServiceFilePathStrFromConfig(String proPath, String proAllName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "service/I" + getInitialCaseOrInitialLowercaseHumpFromStr(proAllName, true) + "Service.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到服务接口类包名
  public static String getIServicePackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "service";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到服务实现类文件路径
  public static String getServiceImplFilePathStrFromConfig(String proPath, String proAllName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "service/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "service/implement/" + getInitialCaseOrInitialLowercaseHumpFromStr(proAllName, true) + "ServiceImpl.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到服务实现类包名
  public static String getServiceImplPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "service.implement";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到服务实现类名称前缀
  public static String getServiceImplNamePrefixStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到控制器类文件路径
  public static String getControllerFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "rest/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "rest/" + initialCaseEntityName + "Controller.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到控制器类包名
  public static String getControllerPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "rest";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到控制器url
  public static String getControllerUrlStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到Angular模块文件路径
  public static String getAngularModuleFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + ".module.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular路由文件路径
  public static String getAngularRoutingFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-routing.module.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular实体类文件路径
  public static String getAngularEntityFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + ".ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular服务文件路径
  public static String getAngularServiceFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + ".service.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular列表组件文件路径
  public static String getAngularListComponentFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-list/" + initialLowercaseEntityName + "-list.component.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular列表网页文件路径
  public static String getAngularListHtmlFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-list/" + initialLowercaseEntityName + "-list.component.html";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular列表样式文件路径
  public static String getAngularListCssFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-list/" + initialLowercaseEntityName + "-list.component.css";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular编辑组件文件路径
  public static String getAngularEditComponentFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-edit/" + initialLowercaseEntityName + "-edit.component.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular编辑网页文件路径
  public static String getAngularEditHtmlFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-edit/" + initialLowercaseEntityName + "-edit.component.html";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular编辑样式文件路径
  public static String getAngularEditCssFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-edit/" + initialLowercaseEntityName + "-edit.component.css";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular详情组件文件路径
  public static String getAngularDetailComponentFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-detail/" + initialLowercaseEntityName + "-detail.component.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular详情网页文件路径
  public static String getAngularDetailHtmlFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-detail/" + initialLowercaseEntityName + "-detail.component.html";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到Angular详情样式文件路径
  public static String getAngularDetailCssFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-detail/" + initialLowercaseEntityName + "-detail.component.css";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到查询类文件路径
  public static String getQueryFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "query/" + initialCaseEntityName + "Query.java";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到查询类包名
  public static String getQueryPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "com.pro.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "query";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到Angular查询类文件路径
  public static String getAngularQueryFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/com/pro/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "-query.ts";
      return proPath;
    }
    return "";
  }

  // 从配置参数得到项目版本
  public static String getProjectVersionStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "version";
      return proAllName;
    }
    return "";
  }

  // 从配置参数得到业务类路径
  public static String getBusinessPathStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

}
