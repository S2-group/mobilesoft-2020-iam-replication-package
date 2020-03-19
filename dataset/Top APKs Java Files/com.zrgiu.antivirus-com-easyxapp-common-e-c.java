package com.easyxapp.common.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.easyxapp.CommonDefine;
import com.easyxapp.common.test.TestDataType;
import com.easyxapp.common.test.b;
import com.easyxapp.xp.common.util.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class c
{
  private static List a = new ArrayList();
  
  public static String a()
  {
    String str = com.easyxapp.common.test.a.a().a(TestDataType.OS_VERSION);
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    return Build.VERSION.RELEASE;
  }
  
  public static String a(Context paramContext)
  {
    String str;
    try
    {
      str = com.easyxapp.common.test.a.a().a(TestDataType.IMSI);
      if (!TextUtils.isEmpty(str)) {
        return str;
      }
      if (paramContext != null) {
        str = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        str = "";
        i.e(paramContext);
      }
    }
    if ((TextUtils.isEmpty(str)) || (!Pattern.compile("[0-9]*").matcher(str).matches())) {
      return "000000000000000";
    }
    return str;
  }
  
  public static void a(Context paramContext, String paramString)
  {
    com.easyxapp.xp.common.a.a(paramContext).a("013", paramString);
  }
  
  public static String b()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public static String b(Context paramContext)
  {
    String str1 = "000";
    String str2 = a(paramContext);
    paramContext = str1;
    if (!TextUtils.isEmpty(str2))
    {
      paramContext = str1;
      if (str2.length() > 3) {
        paramContext = str2.substring(0, 3);
      }
    }
    return paramContext;
  }
  
  public static String c()
  {
    return Build.MODEL;
  }
  
  public static String c(Context paramContext)
  {
    Object localObject = "";
    for (;;)
    {
      try
      {
        String str = com.easyxapp.common.test.a.a().a(TestDataType.IMEI);
        localObject = str;
        if (!TextUtils.isEmpty(str)) {
          return str;
        }
        if (paramContext == null) {
          break label65;
        }
        localObject = str;
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      }
      catch (Exception localException)
      {
        paramContext = (Context)localObject;
        i.e(localException);
        continue;
      }
      if (TextUtils.isEmpty(paramContext)) {
        return "000000000000000";
      }
      return paramContext;
      label65:
      paramContext = localException;
    }
  }
  
  public static String d()
  {
    return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
  }
  
  public static String d(Context paramContext)
  {
    String str = com.easyxapp.common.test.a.a().a(TestDataType.ANDROID_ID);
    if (!TextUtils.isEmpty(str)) {
      paramContext = str;
    }
    for (;;)
    {
      return paramContext;
      if (paramContext != null) {}
      try
      {
        str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
        paramContext = str;
        if (!TextUtils.isEmpty(str)) {
          continue;
        }
        return "";
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          str = "";
        }
      }
    }
  }
  
  public static String e()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String e(Context paramContext)
  {
    String str = com.easyxapp.common.test.a.a().a(TestDataType.WIFI_MAC);
    if (!TextUtils.isEmpty(str)) {
      return str.replace(":", "");
    }
    try
    {
      ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress().replace(":", "");
      return "";
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String f()
  {
    Object localObject = TimeZone.getDefault();
    int i = ((TimeZone)localObject).getOffset(GregorianCalendar.getInstance((TimeZone)localObject).getTimeInMillis());
    String str = String.format("%02d:%02d", new Object[] { Integer.valueOf(Math.abs(i / 3600000)), Integer.valueOf(Math.abs(i / 60000 % 60)) });
    StringBuilder localStringBuilder = new StringBuilder();
    if (i >= 0) {}
    for (localObject = "+";; localObject = "-") {
      return (String)localObject + str;
    }
  }
  
  public static List f(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    paramContext = localArrayList.iterator();
    while (paramContext.hasNext()) {
      if (!com.easyxapp.kr.a.a.a((String)paramContext.next())) {
        paramContext.remove();
      }
    }
    return localArrayList;
  }
  
  public static boolean g(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isAvailable());
  }
  
  public static String h(Context paramContext)
  {
    String str3 = Locale.getDefault().getLanguage();
    String str1;
    if (a.size() == 0) {
      str1 = null;
    }
    do
    {
      try
      {
        if (CommonDefine.ENABLE_EXTRA_TEST_FILE) {
          str1 = com.easyxapp.common.test.a.a().b().k;
        }
        String str2 = str1;
        if (TextUtils.isEmpty(str1)) {
          str2 = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("supportLanguage");
        }
        if (str2 == null)
        {
          paramContext = new String[1];
          paramContext[0] = "";
        }
        for (;;)
        {
          paramContext = new ArrayList(Arrays.asList(paramContext));
          a = paramContext;
          paramContext.remove("");
          if (a.size() != 0) {
            break;
          }
          return str3;
          paramContext = str2.split(",");
        }
        paramContext = d();
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        i.c(paramContext);
        return str3;
      }
      if (a.contains(paramContext)) {
        return paramContext;
      }
    } while (a.contains(str3));
    return (String)a.get(0);
  }
  
  public static boolean i(Context paramContext)
  {
    return !h(paramContext).equals(com.easyxapp.xp.common.a.a(paramContext).b("013", ""));
  }
  
  /* Error */
  public static String j(Context paramContext)
  {
    // Byte code:
    //   0: ldc_w 392
    //   3: invokestatic 398	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: pop
    //   7: aload_0
    //   8: invokevirtual 402	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   11: invokestatic 407	com/google/android/gms/ads/a/a:b	(Landroid/content/Context;)Lcom/google/android/gms/ads/a/b;
    //   14: astore_0
    //   15: aload_0
    //   16: ifnull +28 -> 44
    //   19: aload_0
    //   20: invokevirtual 411	com/google/android/gms/ads/a/b:a	()Ljava/lang/String;
    //   23: areturn
    //   24: astore_0
    //   25: ldc_w 413
    //   28: invokestatic 367	com/easyxapp/xp/common/util/i:c	(Ljava/lang/Object;)V
    //   31: ldc 84
    //   33: areturn
    //   34: astore_0
    //   35: aload_0
    //   36: invokestatic 415	com/easyxapp/xp/common/util/i:b	(Ljava/lang/Object;)V
    //   39: aconst_null
    //   40: astore_0
    //   41: goto -26 -> 15
    //   44: ldc 84
    //   46: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   0	7	24	java/lang/ClassNotFoundException
    //   7	15	34	java/lang/Throwable
  }
}
