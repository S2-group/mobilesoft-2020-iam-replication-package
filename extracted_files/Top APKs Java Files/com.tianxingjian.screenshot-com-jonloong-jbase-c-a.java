package com.jonloong.jbase.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static ApplicationInfo a(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      paramString = e().getApplicationInfo(paramString, paramInt);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static Object a(String paramString)
  {
    return g().getSystemService(paramString);
  }
  
  public static String a()
  {
    return g().getPackageName();
  }
  
  public static List<ResolveInfo> a(Intent paramIntent, int paramInt)
  {
    try
    {
      paramIntent = e().queryIntentActivities(paramIntent, paramInt);
      return paramIntent;
    }
    finally
    {
      paramIntent = finally;
      throw paramIntent;
    }
  }
  
  public static int b(String paramString)
  {
    paramString = d(paramString);
    if (paramString == null) {
      return -1;
    }
    return paramString.versionCode;
  }
  
  public static String b()
  {
    return k.a(k.b().getIdentifier("app_name", "string", a()));
  }
  
  public static List<ResolveInfo> b(Intent paramIntent, int paramInt)
  {
    try
    {
      paramIntent = e().queryIntentServices(paramIntent, paramInt);
      return paramIntent;
    }
    finally
    {
      paramIntent = finally;
      throw paramIntent;
    }
  }
  
  public static int c()
  {
    return b(a());
  }
  
  public static String c(String paramString)
  {
    paramString = d(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.versionName;
  }
  
  /* Error */
  public static PackageInfo d(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 12	com/jonloong/jbase/c/a:e	()Landroid/content/pm/PackageManager;
    //   6: aload_0
    //   7: iconst_1
    //   8: invokevirtual 87	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   11: astore_0
    //   12: ldc 2
    //   14: monitorexit
    //   15: aload_0
    //   16: areturn
    //   17: astore_0
    //   18: ldc 2
    //   20: monitorexit
    //   21: aload_0
    //   22: athrow
    //   23: ldc 2
    //   25: monitorexit
    //   26: aconst_null
    //   27: areturn
    //   28: astore_0
    //   29: goto -6 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	paramString	String
    // Exception table:
    //   from	to	target	type
    //   3	12	17	finally
    //   3	12	28	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static String d()
  {
    return c(a());
  }
  
  public static Intent e(String paramString)
  {
    try
    {
      paramString = e().getLaunchIntentForPackage(paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static PackageManager e()
  {
    return g().getPackageManager();
  }
  
  public static List<PackageInfo> f()
  {
    try
    {
      List localList = e().getInstalledPackages(0);
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static boolean f(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      Iterator localIterator = f().iterator();
      while (localIterator.hasNext()) {
        if (((PackageInfo)localIterator.next()).packageName.equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static Context g()
  {
    return k.a();
  }
  
  public static String g(String paramString)
  {
    try
    {
      boolean bool = TextUtils.isEmpty(paramString);
      Object localObject3 = null;
      Object localObject2 = null;
      if (bool) {
        return null;
      }
      Object localObject1;
      try
      {
        ApplicationInfo localApplicationInfo = a(a(), 128);
        localObject1 = localObject3;
        if (localApplicationInfo != null)
        {
          localObject1 = localObject3;
          if (localApplicationInfo.metaData != null)
          {
            localObject1 = localApplicationInfo.metaData.getString(paramString);
            try
            {
              if (TextUtils.isEmpty((CharSequence)localObject1))
              {
                paramString = String.valueOf(localApplicationInfo.metaData.getInt(paramString));
                localObject1 = paramString;
                break label101;
              }
            }
            catch (PackageManager.NameNotFoundException paramString) {}
            paramString.printStackTrace();
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        localObject1 = localObject2;
      }
      label101:
      return localObject1;
    }
    finally {}
  }
}
