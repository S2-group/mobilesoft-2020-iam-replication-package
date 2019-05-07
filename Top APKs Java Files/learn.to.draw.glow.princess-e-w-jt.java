package e.w;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.a;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class jt
{
  public static int a()
  {
    try
    {
      int i = gs.a.getPackageManager().getPackageInfo(gs.a.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      it.a("Get Version Code Error!!!", localException);
    }
    return -1;
  }
  
  public static List<String> a(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((PackageInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      it.a(paramContext);
    }
  }
  
  public static boolean a(String paramString)
  {
    paramString = fy.a().f(paramString);
    return (paramString != null) && (b(paramString.b));
  }
  
  public static int b(Context paramContext)
  {
    if (a.b(paramContext, "android.permission.GET_ACCOUNTS") != 0) {}
    do
    {
      return 1;
      paramContext = AccountManager.get(paramContext).getAccounts();
    } while ((paramContext != null) && (paramContext.length > 0));
    return 0;
  }
  
  public static boolean b()
  {
    NetworkInfo localNetworkInfo;
    if (gs.a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      localNetworkInfo = ((ConnectivityManager)gs.a.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null) {}
    }
    else
    {
      return false;
    }
    int i = localNetworkInfo.getType();
    if (((i == 1) || (i == 0) || (i == 6)) && (localNetworkInfo.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = null;
      try
      {
        paramString = gs.a.getPackageManager().getPackageInfo(paramString, 0);
        if (paramString == null) {
          continue;
        }
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;)
        {
          paramString = localObject;
        }
      }
    }
  }
  
  public static int c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return -1;
      Object localObject = gs.a;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getInt(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        it.a("Get Meta_Data Info error:", paramString);
      }
    }
    return -1;
  }
  
  public static String c()
  {
    int i = e();
    if (i == -1) {
      return "unknow";
    }
    if (i == 1) {
      return "wifi";
    }
    switch (((TelephonyManager)gs.a.getSystemService("phone")).getNetworkType())
    {
    case 13: 
    default: 
      return "4g";
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return "2g";
    }
    return "3g";
  }
  
  public static String d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      Object localObject = gs.a;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getString(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        it.a("Get Meta_Data Info error:", paramString);
      }
    }
    return null;
  }
  
  public static boolean d()
  {
    return (gs.a.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  private static int e()
  {
    Object localObject = (ConnectivityManager)gs.a.getSystemService("connectivity");
    if (localObject == null) {
      return -1;
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if (localObject == null) {
      return -1;
    }
    return ((NetworkInfo)localObject).getType();
  }
  
  public static boolean e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = gs.a;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getBoolean(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        it.a("Get Meta_Data Info error:", paramString);
      }
    }
    return false;
  }
}
