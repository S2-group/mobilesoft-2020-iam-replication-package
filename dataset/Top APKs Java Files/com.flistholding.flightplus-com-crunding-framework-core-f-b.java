package com.crunding.framework.core.f;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.crunding.framework.c.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class b
{
  public static String a()
  {
    Object localObject2 = Build.MODEL;
    String str = Build.MANUFACTURER;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "unknown";
    }
    localObject2 = str;
    if (str == null) {
      localObject2 = "unknown";
    }
    if (((String)localObject1).startsWith((String)localObject2)) {
      return localObject1;
    }
    return (String)localObject2 + " " + (String)localObject1;
  }
  
  public static String a(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static ArrayList<HashMap> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    List localList = paramContext.getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((!paramBoolean) && (a(localPackageInfo))) {}
      for (;;)
      {
        i += 1;
        break;
        HashMap localHashMap = new HashMap();
        localHashMap.put("appName", localPackageInfo.applicationInfo.loadLabel(paramContext).toString());
        localHashMap.put("pName", localPackageInfo.packageName);
        localHashMap.put("versionName", localPackageInfo.versionName);
        localArrayList.add(localHashMap);
      }
    }
    return localArrayList;
  }
  
  public static boolean a(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public static boolean b()
  {
    return a(11);
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return false;
      paramContext = paramContext.getResources();
    } while (paramContext == null);
    return paramContext.getBoolean(c.b.tablet);
  }
  
  public static boolean c(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
}
