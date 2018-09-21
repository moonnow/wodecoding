package com.pro.tool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StrTools {

  public static String renderString(String content, Map<String, String> map) {
    Set<Entry<String, String>> sets = map.entrySet();
    for (Entry<String, String> entry : sets) {
      String regex = "\\$\\{" + entry.getKey() + "\\}";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(content);
      content = matcher.replaceAll(entry.getValue());
    }
    return content;
  }

  public static String getStrFromFileResourcesPath(String str) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(StrTools.class.getResourceAsStream(str)));
      while ((str2 = reader.readLine()) != null) {
        str1.append(str2).append("\n");
      }
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
    return str1.toString();
  }

  public static String getStrFromFilePath(String str) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    File file = new File(str);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      while ((str2 = reader.readLine()) != null) {
        str1.append(str2).append("\n");
      }
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
    return str1.toString();
  }

  public static void getFileFromContentStrAndPath(String content, String path) {
    try {
      File f = new File(path);
      if (!f.exists()) {
        new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
      }
      FileOutputStream fop = new FileOutputStream(f);
      OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
      writer.append(content);
      writer.close();
      fop.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
