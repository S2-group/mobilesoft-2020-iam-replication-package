package com.kayak.studio.gifmaker.message;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class b
{
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String a(Context paramContext, String paramString)
  {
    try
    {
      paramString = paramContext.getPackageManager().getInstallerPackageName(paramString);
      paramContext = paramString;
      if (TextUtils.isEmpty(paramString)) {
        paramContext = "None";
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static boolean a(PackageInfo paramPackageInfo)
  {
    boolean bool = false;
    try
    {
      int i = paramPackageInfo.applicationInfo.flags;
      if ((i & 0x1) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (RuntimeException paramPackageInfo) {}
    return false;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static int[] c(Context paramContext)
  {
    int[] arrayOfInt = new int[2];
    int[] tmp5_4 = arrayOfInt;
    tmp5_4[0] = 0;
    int[] tmp9_5 = tmp5_4;
    tmp9_5[1] = 0;
    tmp9_5;
    if (paramContext == null) {
      return arrayOfInt;
    }
    try
    {
      Point localPoint = new Point();
      paramContext = (WindowManager)paramContext.getSystemService("window");
      int j;
      int i;
      if (Build.VERSION.SDK_INT >= 13)
      {
        paramContext.getDefaultDisplay().getSize(localPoint);
        j = localPoint.x;
        i = localPoint.y;
      }
      else
      {
        paramContext = paramContext.getDefaultDisplay();
        j = paramContext.getWidth();
        i = paramContext.getHeight();
      }
      arrayOfInt[0] = j;
      arrayOfInt[1] = i;
      return arrayOfInt;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return arrayOfInt;
  }
  
  public static String d(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramContext = c(paramContext);
    localStringBuilder.append(String.valueOf(paramContext[0]));
    localStringBuilder.append("x");
    localStringBuilder.append(String.valueOf(paramContext[1]));
    return localStringBuilder.toString();
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      String str = paramContext.getNetworkOperatorName();
      if (TextUtils.isEmpty(str)) {
        return paramContext.getSimOperatorName();
      }
      paramContext = str.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      String str = paramContext.getNetworkOperatorName();
      if (TextUtils.isEmpty(str)) {
        return paramContext.getSimCountryIso();
      }
      paramContext = str.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String g(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(1).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (!a(localPackageInfo))
        {
          localStringBuilder.append(localPackageInfo.packageName);
          localStringBuilder.append("|");
          localStringBuilder.append(localPackageInfo.versionCode);
          localStringBuilder.append("|");
          localStringBuilder.append(localPackageInfo.versionName);
          localStringBuilder.append("|");
          localStringBuilder.append(a(paramContext, localPackageInfo.packageName));
          if (localIterator.hasNext()) {
            localStringBuilder.append(",");
          }
        }
      }
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String h(Context paramContext)
  {
    return a(paramContext, paramContext.getPackageName());
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      paramContext = Arrays.asList(AccountManager.get(paramContext).getAccountsByType("com.google"));
      if (paramContext.isEmpty()) {
        return "";
      }
      paramContext = paramContext.iterator();
      StringBuilder localStringBuilder = new StringBuilder();
      while (paramContext.hasNext())
      {
        localStringBuilder.append(((Account)paramContext.next()).name);
        if (paramContext.hasNext()) {
          localStringBuilder.append(",");
        }
      }
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
}
