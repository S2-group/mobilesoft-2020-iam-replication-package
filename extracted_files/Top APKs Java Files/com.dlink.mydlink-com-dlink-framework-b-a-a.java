package com.dlink.framework.b.a;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static String a(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      str = paramString.replaceAll(":", "").toUpperCase();
    }
    return str;
  }
  
  public static String a(String paramString, Context paramContext)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new ByteArrayOutputStream(1024);
      for (;;)
      {
        int i = paramContext.read();
        if (i == -1) {
          break;
        }
        paramString.write(i);
      }
      paramContext.printStackTrace();
    }
    catch (IOException paramContext)
    {
      paramString = "";
    }
    for (;;)
    {
      return paramString;
      paramString = paramString.toString();
      try
      {
        paramContext.close();
        return paramString;
      }
      catch (IOException paramContext) {}
    }
  }
  
  public static void a(Activity paramActivity)
  {
    try
    {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 2);
      return;
    }
    catch (Exception paramActivity) {}
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    boolean bool = false;
    if (paramContext.hasNext())
    {
      if (!((PackageInfo)paramContext.next()).packageName.equals(paramString)) {
        break label51;
      }
      bool = true;
    }
    label51:
    for (;;)
    {
      break;
      return bool;
    }
  }
  
  public static String b(String paramString)
  {
    String str = paramString.replace(":", "");
    paramString = str.substring(0, 2);
    int i = 2;
    while (i < str.length())
    {
      paramString = paramString + ":" + str.substring(i, i + 2);
      i += 2;
    }
    return paramString;
  }
  
  public static String c(String paramString)
  {
    int k = 0;
    Object localObject1 = b(paramString.toUpperCase());
    paramString = null;
    try
    {
      localObject2 = MessageDigest.getInstance("SHA-256");
      ((MessageDigest)localObject2).update(((String)localObject1 + "reombielrcth").getBytes("UTF-8"));
      localObject1 = ((MessageDigest)localObject2).digest();
      paramString = (String)localObject1;
    }
    catch (Exception localException1)
    {
      int j;
      for (;;)
      {
        Object localObject2;
        int i;
        localException1.printStackTrace();
      }
      for (;;)
      {
        if (j < paramString.length * 2)
        {
          char c = localException1[j];
          try
          {
            localException1[j] = Integer.toHexString(Integer.parseInt(String.valueOf(c)) + 2).charAt(0);
            j += 1;
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              localException1[j] = ((char)(localException1[j] + '\002'));
            }
          }
        }
      }
      paramString = new String(localException1).toLowerCase();
    }
    localObject2 = "0123456789ABCDEF".toCharArray();
    localObject1 = new char[paramString.length * 2];
    i = 0;
    for (;;)
    {
      j = k;
      if (i >= paramString.length) {
        break;
      }
      j = paramString[i] & 0xFF;
      localObject1[(i * 2)] = localObject2[(j >>> 4)];
      localObject1[(i * 2 + 1)] = localObject2[(j & 0xF)];
      i += 1;
    }
    return paramString.substring(paramString.length() - 8);
  }
}
