package com.ihs.app.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import com.ihs.app.framework.HSApplication;
import java.io.File;
import java.util.List;

public final class a
{
  public static boolean a()
  {
    Object localObject = HSApplication.a();
    PackageManager localPackageManager;
    if (Build.VERSION.SDK_INT > 7) {
      localPackageManager = ((Context)localObject).getPackageManager();
    }
    for (;;)
    {
      try
      {
        int i = localPackageManager.getPackageInfo(((Context)localObject).getPackageName(), 0).applicationInfo.flags;
        return (i & 0x40000) == 262144;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      try
      {
        localObject = ((Context)localObject).getFilesDir().getAbsolutePath();
        if (((String)localObject).startsWith("/data/")) {
          return false;
        }
        if (!((String)localObject).contains("/mnt/"))
        {
          boolean bool = ((String)localObject).contains(Environment.getExternalStorageDirectory().getPath());
          if (bool) {}
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    List localList = HSApplication.a().getPackageManager().getInstalledPackages(8192);
    int j = localList.size();
    int i = 0;
    if (i < j) {
      if (!((PackageInfo)localList.get(i)).packageName.equalsIgnoreCase(paramString)) {}
    }
    for (boolean bool = true;; bool = false)
    {
      new StringBuilder("isAppInstalled(").append(paramString).append(") = ").append(bool).toString();
      return bool;
      i += 1;
      break;
    }
  }
}
