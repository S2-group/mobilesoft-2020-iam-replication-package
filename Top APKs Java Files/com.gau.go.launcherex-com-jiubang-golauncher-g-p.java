package com.jiubang.golauncher.g;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.jiubang.golauncher.app.info.AppInfo;
import com.jiubang.golauncher.ay;
import com.jiubang.golauncher.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class p
{
  public static double a(double paramDouble)
  {
    return (paramDouble - 32.0D) / 1.8D;
  }
  
  public static boolean a(Context paramContext)
  {
    boolean bool1 = true;
    if (paramContext == null) {
      return false;
    }
    try
    {
      if (Build.VERSION.SDK_INT >= 19)
      {
        if (Settings.Secure.getInt(paramContext.getContentResolver(), "location_mode") != 0) {
          return bool1;
        }
      }
      else
      {
        paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "location_providers_allowed");
        if (paramContext != null)
        {
          boolean bool2 = TextUtils.isEmpty(paramContext);
          if (!bool2) {
            return bool1;
          }
        }
      }
      bool1 = false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      bool1 = false;
    }
    return bool1;
  }
  
  public static void b(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return;
      Object localObject1 = paramContext.getPackageManager();
      Object localObject2 = new a().a.iterator();
      int i = 0;
      int j = i;
      Object localObject3;
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (a)((Iterator)localObject2).next();
        j = i;
      }
      try
      {
        Intent localIntent = new Intent("android.intent.action.MAIN");
        j = i;
        localIntent.setClassName(((a)localObject3).a(), ((a)localObject3).c());
        j = i;
        if (((a)localObject3).b())
        {
          j = i;
          localIntent.addCategory("android.intent.category.LAUNCHER");
        }
        j = i;
        localIntent.setFlags(268435456);
        j = i;
        localObject3 = ((PackageManager)localObject1).queryIntentActivities(localIntent, 0);
        if (localObject3 != null)
        {
          j = i;
          if (((List)localObject3).size() > 0)
          {
            j = 1;
            i = 1;
            paramContext.startActivity(localIntent);
            j = i;
            if (j != 0) {
              continue;
            }
            localObject1 = ay.e().b("com.android.settings");
            if (localObject1 == null) {
              continue;
            }
            localObject1 = ((ArrayList)localObject1).iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject2 = (AppInfo)((Iterator)localObject1).next();
              try
              {
                localObject2 = ((AppInfo)localObject2).getIntent();
                if (localObject2 != null)
                {
                  paramContext.startActivity((Intent)localObject2);
                  return;
                }
              }
              catch (Exception localException1)
              {
                localException1.printStackTrace();
              }
            }
          }
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          i = j;
          localException2.printStackTrace();
        }
      }
    }
  }
  
  public static void c(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return;
      PackageManager localPackageManager = paramContext.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (PackageInfo)localIterator.next();
        try
        {
          if ((((PackageInfo)localObject).packageName.toLowerCase().contains("calendar")) && (!((PackageInfo)localObject).packageName.toLowerCase().contains("widget")) && ((((PackageInfo)localObject).applicationInfo.flags & 0x1) > 0))
          {
            localObject = localPackageManager.getLaunchIntentForPackage(((PackageInfo)localObject).packageName);
            if (localObject != null)
            {
              ((Intent)localObject).setFlags(268435456);
              paramContext.startActivity((Intent)localObject);
              return;
            }
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
  }
  
  public static void d(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      Intent localIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
