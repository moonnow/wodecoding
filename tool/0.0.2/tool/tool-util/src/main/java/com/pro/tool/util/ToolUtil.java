package com.pro.tool.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.UUID;

public final class ToolUtil {

  public static final String ACTION = "action=";

  public static final String ACTION_SAVE = "save";

  public static final String ACTION_BATCH_SAVE = "batch_save";

  public static final String ACTION_UPDATE = "update";

  public static final String ACTION_BATCH_UPDATE = "batch_update";

  public static final String ACTION_REMOVE = "remove";

  public static final String ACTION_BATCH_REMOVE = "batch_remove";

  public static final String ACTION_GET_BY_PK = "get_by_pk";

  public static final String ACTION_GET_ALL = "get_all";

  public static final String ACTION_PAGING = "paging";

  public static final String AND_EQ = "Andeq";

  public static final String AND_LIKE = "Andlike";

  public static final String AND_IN = "Andin";

  public static final String AND_NE = "Andne";

  public static final String AND_NIN = "Andnin";

  public static final String AND_G = "Andg";

  public static final String AND_L = "Andl";

  public static final String AND_GE = "Andge";

  public static final String AND_LE = "Andle";

  public static final String OR_EQ = "Oreq";

  public static final String OR_LIKE = "Orlike";

  public static final String OR_IN = "Orin";

  public static final String OR_NE = "Orne";

  public static final String OR_NIN = "Ornin";

  public static final String OR_G = "Org";

  public static final String OR_L = "Orl";

  public static final String OR_GE = "Orge";

  public static final String OR_LE = "Orle";

  // 判断是否是空字符串
  // 字符串是null或空字符串或全空格字符串 返回true
  // 返回true是无效字符串 反之返回false
  public static boolean isNullStr(String str) {
    return str == null ? true : (str.trim().length() == 0 ? true : false);
  }

  public static boolean isNotNullStr(String str) {
    return str != null && str.trim().length() != 0;
  }

  public static String getUUID() {
    return UUID.randomUUID().toString();
  }

  public static boolean isNotEmpty(Collection<?> arg) {
    return arg != null && (!arg.isEmpty());
  }

  public static boolean isEmpty(Collection<?> arg) {
    return arg == null ? true : arg.isEmpty();
  }

  public static boolean isNotEmpty(Map<?, ? extends Object> map) {
    return map != null && (!map.isEmpty());
  }

  public static boolean isEmpty(Map<?, ? extends Object> map) {
    return map == null ? true : map.isEmpty();
  }

  // 传入目标对象
  // 判断目标对象的成员变量值是否都为空
  // 都为空返回true 否则返回false
  public static boolean isNullEntityAllFieldValue(Object o) {
    try {
      // 取得目标对象的字段集合以及方法集合
      LinkedHashSet<Field> tFields = new LinkedHashSet<Field>();
      LinkedHashSet<Method> getMethods = new LinkedHashSet<Method>();
      Class<?> ppclass = o.getClass();
      while (ppclass != null) {
        tFields.addAll(Arrays.asList(ppclass.getDeclaredFields()));
        getMethods.addAll(Arrays.asList(ppclass.getMethods()));
        ppclass = ppclass.getSuperclass();
      }
      if (tFields != null && tFields.size() >= 1) {
        for (Field tField : tFields) {
          // 根据成员变量名定义get方法名
          String getMethodStr = "get" + tField.getName().substring(0, 1).toUpperCase() + tField.getName().substring(1);
          // 标识成员变量是否有get方法
          boolean isGetMethodStr = false;
          // 目标对象成员变量有get方法 改变isSetMethodStr变量的值为true
          for (Method getMethod : getMethods) {
            if (getMethodStr.equals(getMethod.getName())) {
              isGetMethodStr = true;
              break;
            }
          }
          // 目标对象成员变量有get方法 就进行非空判断
          if (isGetMethodStr) {
            Method tM = o.getClass().getMethod(getMethodStr);
            if (tM.invoke(o) != null) {
              return false;
            }
          }
        }
      }
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return true;
  }

  // 传入目标对象和数据对象
  // 将数据对象中的属性复制到目标对象(数据类型和属性名称匹配时复制)
  // 返回目标对象
  public static <T> T attributeReplication(Class<T> c, Object o) {
    try {
      T dataBean = c.newInstance();
      // 取得目标对象的字段集合以及方法集合
      LinkedHashSet<Field> tFields = new LinkedHashSet<Field>();
      LinkedHashSet<Method> setMethods = new LinkedHashSet<Method>();
      Class<?> ptclass = c;
      while (ptclass != null) {
        tFields.addAll(Arrays.asList(ptclass.getDeclaredFields()));
        setMethods.addAll(Arrays.asList(ptclass.getMethods()));
        ptclass = ptclass.getSuperclass();
      }
      // 取得数据对象的字段集合以及方法集合
      LinkedHashSet<Field> pFields = new LinkedHashSet<Field>();
      LinkedHashSet<Method> getMethods = new LinkedHashSet<Method>();
      Class<?> ppclass = o.getClass();
      while (ppclass != null) {
        pFields.addAll(Arrays.asList(ppclass.getDeclaredFields()));
        getMethods.addAll(Arrays.asList(ppclass.getMethods()));
        ppclass = ppclass.getSuperclass();
      }
      if (tFields != null && tFields.size() >= 1 && pFields != null && pFields.size() >= 1) {
        for (Field tField : tFields) {
          // 根据成员变量名定义set方法名
          String setMethodStr = "set" + tField.getName().substring(0, 1).toUpperCase() + tField.getName().substring(1);
          // 标识成员变量是否有set方法
          boolean isSetMethodStr = false;
          // 目标对象成员变量有set方法 改变isSetMethodStr变量的值为true
          for (Method setMethod : setMethods) {
            if (setMethodStr.equals(setMethod.getName())) {
              isSetMethodStr = true;
              break;
            }
          }
          // 目标对象成员变量有set方法 那么就去找数据对象对应的get方法
          if (isSetMethodStr) {
            for (Field pField : pFields) {
              // 根据成员变量名定义get方法名
              String getMethodStr = "get" + pField.getName().substring(0, 1).toUpperCase() + pField.getName().substring(1);
              // 标识成员变量是否有get方法
              boolean isGetMethodStr = false;
              // 数据对象成员变量有get方法 改变isGetMethodStr变量的值为true
              for (Method getMethod : getMethods) {
                if (getMethodStr.equals(getMethod.getName())) {
                  isGetMethodStr = true;
                  break;
                }
              }
              // 数据对象成员变量有get方法 那么就给目标对象的成员变量设值
              if (isGetMethodStr) {
                // 判断目标对象与数据对象的成员变量名是否一致
                if (tField.getName().equals(pField.getName())) {
                  // 判断目标对象与数据对象的成员变量类型是否一致
                  if (tField.getType().equals(pField.getType())) {
                    // 用反射进行设值
                    if (tField.getType().toString().indexOf("class") != -1) {
                      String type = tField.getType().toString().replaceAll("class", "").trim();
                      Class<?> cc = Class.forName(type);
                      Method tM = c.getMethod(setMethodStr, cc);
                      Method pM = o.getClass().getMethod(getMethodStr);
                      tM.invoke(dataBean, pM.invoke(o));
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
      return dataBean;
    } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

}
