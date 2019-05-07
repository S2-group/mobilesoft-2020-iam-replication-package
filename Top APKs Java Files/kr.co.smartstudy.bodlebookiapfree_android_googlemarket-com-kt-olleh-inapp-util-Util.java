package com.kt.olleh.inapp.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;

public class Util
{
  static String a = "com.kt.olleh.istore";
  static String b = "com.kt.olleh.storefront";
  static String c = "01.00.00";
  
  public Util() {}
  
  private static int a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(512);
    int j;
    int i;
    if (paramContext != null)
    {
      j = paramContext.size();
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return -1;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (a.equals(localApplicationInfo.packageName)) {
        return 1;
      }
      if (b.equals(localApplicationInfo.packageName)) {
        return 2;
      }
      i += 1;
    }
  }
  
  private static String a(String paramString)
  {
    int i;
    try
    {
      paramString = paramString.trim();
      if ((paramString == null) || ("".equals(paramString))) {
        return c;
      }
      arrayOfString = paramString.split("[.]+");
      if (arrayOfString.length > 3)
      {
        i = 3;
        break label191;
        return paramString + ".00.00";
      }
      else
      {
        i = arrayOfString.length;
      }
    }
    catch (Exception paramString)
    {
      String[] arrayOfString;
      String str;
      paramString.printStackTrace();
      return c;
    }
    str = paramString;
    int j;
    if (j > 0) {
      str = paramString + ".";
    }
    paramString = arrayOfString[j].trim();
    if (paramString.length() < 2)
    {
      paramString = str + "0" + paramString;
    }
    else
    {
      paramString = str + paramString;
      break label226;
      paramString = paramString + ".00";
      return paramString;
      label191:
      paramString = "";
      j = 0;
    }
    while (j >= i)
    {
      switch (i)
      {
      }
      return paramString;
      label226:
      j += 1;
    }
  }
  
  private static void a(StringBuffer paramStringBuffer, String paramString, float paramFloat)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append(":");
    paramStringBuffer.append(paramFloat);
    paramStringBuffer.append("\n");
  }
  
  private static void a(StringBuffer paramStringBuffer, String paramString, int paramInt)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append(":");
    paramStringBuffer.append(paramInt);
    paramStringBuffer.append("\n");
  }
  
  public static void a(StringBuffer paramStringBuffer, String paramString1, String paramString2)
  {
    paramStringBuffer.append(paramString1);
    paramStringBuffer.append(":");
    paramStringBuffer.append(paramString2);
    paramStringBuffer.append("\n");
  }
  
  private static void a(StringBuffer paramStringBuffer, String paramString1, String paramString2, boolean paramBoolean)
  {
    paramStringBuffer.append(paramString1);
    paramStringBuffer.append("=");
    paramStringBuffer.append(paramString2);
    if (paramBoolean) {
      paramStringBuffer.append("&");
    }
  }
  
  private static void a(StringBuffer paramStringBuffer, String paramString, boolean paramBoolean)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append(":");
    paramStringBuffer.append(paramBoolean);
    paramStringBuffer.append("\n");
  }
  
  private static String b(Context paramContext)
  {
    int j;
    int i;
    try
    {
      localPackageManager = paramContext.getPackageManager();
      paramContext = paramContext.getPackageManager().getInstalledApplications(512);
      if (paramContext == null) {
        break label129;
      }
      j = paramContext.size();
      i = 0;
    }
    catch (Exception paramContext)
    {
      PackageManager localPackageManager;
      label41:
      return c;
    }
    if (i == 1)
    {
      paramContext = a;
      return a(localPackageManager.getPackageInfo(paramContext, 0).versionName);
    }
    label129:
    label139:
    for (;;)
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (a.equals(localApplicationInfo.packageName))
      {
        i = 1;
        break;
      }
      if (b.equals(localApplicationInfo.packageName))
      {
        i = 2;
        break;
        if (i == 2)
        {
          paramContext = b;
          break label41;
        }
        return "unknown";
      }
      for (;;)
      {
        if (i < j) {
          break label139;
        }
        i = -1;
        break;
        i += 1;
      }
    }
  }
}
