package com.pickle.PackageLister;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import java.io.File;
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
              Log.i("PicklePKG", "Failed to show new toast! - " + localException2);
            }
            localException1 = localException1;
            Log.i("PicklePKG", "Failed to makeText of new toast! - " + localException1);
            return;
          }
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      Log.i("PicklePKG", "Failed to getMainLooper() (DisplayToast) - " + paramContext);
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
            Log.i("PicklePKG", "Failed to cancel active toast! - " + localException);
          }
        }
      });
      ActiveToast = null;
      return;
    }
    catch (Exception localException)
    {
      Log.i("PicklePKG", "Failed to getMainLooper() (ForceEndToast) - " + localException);
    }
  }
  
  public static long GetAvailableMemory()
  {
    Runtime localRuntime = Runtime.getRuntime();
    return localRuntime.maxMemory() - (localRuntime.totalMemory() - localRuntime.freeMemory());
  }
  
  public static int GetDensity(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  public static long GetFreeMemory()
  {
    return Runtime.getRuntime().freeMemory();
  }
  
  public static long GetInstallTimestamp(Context paramContext)
  {
    PackageManager localPackageManager;
    try
    {
      localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null)
      {
        Log.i("PicklePKG", "(GetInstallTimestamp) ERROR_2 - ContextPackageManager is null!");
        return 0L;
      }
    }
    catch (Exception paramContext)
    {
      Log.i("PicklePKG", "(GetInstallTimestamp) ERROR_1 - " + paramContext);
      return 0L;
    }
    try
    {
      paramContext = localPackageManager.getApplicationInfo(GetSelfPackageName(paramContext), 0);
      if (paramContext == null)
      {
        Log.i("PicklePKG", "(GetInstallTimestamp) ERROR_4 - AppInfo is null!");
        return 0L;
      }
    }
    catch (Exception paramContext)
    {
      Log.i("PicklePKG", "(GetInstallTimestamp) ERROR_3 - " + paramContext);
      return 0L;
    }
    paramContext = paramContext.sourceDir;
    if (paramContext.isEmpty())
    {
      Log.i("PicklePKG", "(GetInstallTimestamp) ERROR_5 - AppFile is empty!");
      return 0L;
    }
    try
    {
      long l = new File(paramContext).lastModified();
      return l;
    }
    catch (Exception paramContext)
    {
      Log.i("PicklePKG", "(GetInstallTimestamp) ERROR_6 - " + paramContext);
    }
    return 0L;
  }
  
  public static long GetMaxMemory()
  {
    return Runtime.getRuntime().maxMemory();
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
        Log.i("PicklePKG", "(GetPackageList) ERROR_3");
        return "";
      }
      catch (Exception paramContext)
      {
        Log.i("PicklePKG", "(GetPackageList) ERROR_2 - " + paramContext);
        return "";
      }
      paramContext = paramContext;
      Log.i("PicklePKG", "(GetPackageList) ERROR_1 - " + paramContext);
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
          Log.i("PicklePKG", "(GetPackageList) ERROR_4");
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
  
  public static String GetSelfPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.i("PicklePKG", "(GetSelfPackageName) ERROR_1 - " + paramContext);
    }
    return "";
  }
  
  public static long GetTotalMemory()
  {
    return Runtime.getRuntime().totalMemory();
  }
  
  public static long GetUsedMemory()
  {
    Runtime localRuntime = Runtime.getRuntime();
    return localRuntime.totalMemory() - localRuntime.freeMemory();
  }
  
  public static void HapticFeedback(Context paramContext)
  {
    HapticFeedback(paramContext, 1);
  }
  
  public static void HapticFeedback(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = ((Activity)paramContext).getWindow().getDecorView().findViewById(16908290);
      if (paramContext == null)
      {
        Log.i("PicklePKG", "(HapticFeedback) ERROR_2 - RootView is null!");
        return;
      }
    }
    catch (Exception paramContext)
    {
      Log.i("PicklePKG", "(HapticFeedback) ERROR_1 - " + paramContext);
      return;
    }
    if (!paramContext.isHapticFeedbackEnabled()) {
      paramContext.setHapticFeedbackEnabled(true);
    }
    switch (paramInt)
    {
    default: 
      paramContext.performHapticFeedback(1);
      return;
    case 2: 
      paramContext.performHapticFeedback(3);
      return;
    }
    paramContext.performHapticFeedback(0);
  }
}
