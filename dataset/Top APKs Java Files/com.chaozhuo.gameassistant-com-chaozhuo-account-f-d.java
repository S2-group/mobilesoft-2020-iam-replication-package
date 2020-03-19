package com.chaozhuo.account.f;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

public class d
{
  public static final String a = d.class.getSimpleName();
  
  public d() {}
  
  public static Long a(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return Long.valueOf(l);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return Long.valueOf(0L);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getInstalledPackages(0).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!TextUtils.equals(((PackageInfo)paramContext.next()).packageName.toLowerCase(), paramString));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean a(String... paramVarArgs)
  {
    boolean bool2 = false;
    int j = paramVarArgs.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (TextUtils.isEmpty(paramVarArgs[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static String b(String paramString)
  {
    for (;;)
    {
      int i;
      try
      {
        localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
        paramString = ((MessageDigest)localObject).digest();
        localObject = new StringBuilder();
        int j = paramString.length;
        i = 0;
        if (i < j)
        {
          int k = paramString[i];
          if (Integer.toHexString(k & 0xFF).length() == 1) {
            ((StringBuilder)localObject).append("0").append(Integer.toHexString(k & 0xFF));
          } else {
            ((StringBuilder)localObject).append(Integer.toHexString(k & 0xFF));
          }
        }
      }
      catch (NoSuchAlgorithmException paramString)
      {
        Object localObject;
        return "";
        paramString = ((StringBuilder)localObject).toString();
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        return "";
      }
      i += 1;
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
