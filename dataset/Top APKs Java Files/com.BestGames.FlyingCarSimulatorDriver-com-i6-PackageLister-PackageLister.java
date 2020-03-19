package com.i6.PackageLister;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

public class PackageLister
{
  private static Toast ActiveToast = null;
  
  public PackageLister() {}
  
  public static void DisplayToast(Context paramContext, final String paramString, final int paramInt)
  {
    
    try
    {
      Handler localHandler = new Handler(Looper.getMainLooper());
      localHandler.post(new Runnable()
      {
        public void run()
        {
          try
          {
            PackageLister.ActiveToast = Toast.makeText(PackageLister.this, paramString, paramInt);
            return;
          }
          catch (Exception localException1)
          {
            try
            {
              PackageLister.ActiveToast.show();
              return;
            }
            catch (Exception localException2)
            {
              Log.i("i6PKG", "Failed to show new toast! - " + localException2);
            }
            localException1 = localException1;
            Log.i("i6PKG", "Failed to makeText of new toast! - " + localException1);
            return;
          }
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      Log.i("i6PKG", "Failed to getMainLooper() (DisplayToast) - " + paramContext);
    }
  }
  
  public static void ForceEndToast()
  {
    if (ActiveToast != null) {}
    try
    {
      Handler localHandler = new Handler(Looper.getMainLooper());
      localHandler.post(new Runnable()
      {
        public void run()
        {
          try
          {
            PackageLister.ActiveToast.cancel();
            return;
          }
          catch (Exception localException)
          {
            Log.i("i6PKG", "Failed to cancel active toast! - " + localException);
          }
        }
      });
      ActiveToast = null;
      return;
    }
    catch (Exception localException)
    {
      Log.i("i6PKG", "Failed to getMainLooper() (ForceEndToast) - " + localException);
    }
  }
  
  public static int GetDensity(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  public static String GetPackageList(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramContext = new StringBuilder();
    }
    catch (Exception paramContext)
    {
      try
      {
        localObject = paramContext.getInstalledApplications(0);
        if (!((List)localObject).isEmpty()) {
          break label83;
        }
        Log.i("i6PKG", "(GetPackageList) ERROR_3");
        return "";
      }
      catch (Exception paramContext)
      {
        Log.i("i6PKG", "(GetPackageList) ERROR_2 - " + paramContext);
        return "";
      }
      paramContext = paramContext;
      Log.i("i6PKG", "(GetPackageList) ERROR_1 - " + paramContext);
      return "";
    }
    label83:
    Object localObject = ((List)localObject).iterator();
    label202:
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if (paramContext.length() <= 0)
        {
          Log.i("i6PKG", "(GetPackageList) ERROR_4");
          return "";
        }
      }
      else
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if (localApplicationInfo.packageName == null) {
          continue;
        }
        if ((!paramString.isEmpty()) && ((paramString.isEmpty()) || (!localApplicationInfo.packageName.toLowerCase().contains(paramString)))) {}
        for (int i = 0;; i = 1)
        {
          if (i == 0) {
            break label202;
          }
          paramContext.append(localApplicationInfo.packageName);
          paramContext.append(",");
          break;
        }
      }
    }
    return paramContext.toString();
  }
}
