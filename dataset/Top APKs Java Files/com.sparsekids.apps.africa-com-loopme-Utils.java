package com.loopme;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Utils
{
  private static final String LOG_TAG = Utils.class.getSimpleName();
  private static Context sContext;
  
  public Utils() {}
  
  public static void animateAppear(View paramView)
  {
    paramView.animate().setDuration(500L).alpha(1.0F);
  }
  
  public static int convertDpToPixel(float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, sContext.getResources().getDisplayMetrics());
  }
  
  public static DisplayMetrics getDisplayMetrics(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (paramContext == null) {
      return localDisplayMetrics;
    }
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static Location getLastKnownLocation()
  {
    Object localObject3 = (LocationManager)sContext.getSystemService("location");
    if (localObject3 == null) {
      return null;
    }
    localObject1 = null;
    try
    {
      localObject2 = ((LocationManager)localObject3).getLastKnownLocation("gps");
      localObject1 = localObject2;
    }
    catch (SecurityException localSecurityException1)
    {
      for (;;)
      {
        Object localObject2;
        Logging.out(LOG_TAG, "Failed to retrieve GPS location: access appears to be disabled.", Logging.LogLevel.DEBUG);
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      for (;;)
      {
        Logging.out(LOG_TAG, "Failed to retrieve GPS location: device has no GPS provider.", Logging.LogLevel.DEBUG);
      }
      return localIllegalArgumentException1;
    }
    localObject2 = null;
    try
    {
      localObject3 = ((LocationManager)localObject3).getLastKnownLocation("network");
      localObject2 = localObject3;
    }
    catch (SecurityException localSecurityException2)
    {
      for (;;)
      {
        Logging.out(LOG_TAG, "Failed to retrieve network location: access appears to be disabled.", Logging.LogLevel.DEBUG);
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException2)
    {
      for (;;)
      {
        Logging.out(LOG_TAG, "Failed to retrieve network location: device has no network provider.", Logging.LogLevel.DEBUG);
      }
      if ((localObject1 == null) || (localIllegalArgumentException1 == null)) {
        break label134;
      }
      if (localObject1.getTime() <= localIllegalArgumentException1.getTime()) {
        break label132;
      }
      return localObject1;
      return localIllegalArgumentException1;
      if (localObject1 == null) {
        return localIllegalArgumentException1;
      }
      return localObject1;
    }
    if ((localObject1 == null) && (localObject2 == null)) {
      return null;
    }
  }
  
  public static String getStringFromStream(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localStringBuilder.append(new String(arrayOfByte, 0, i));
    }
    paramInputStream.close();
    return localStringBuilder.toString();
  }
  
  static void init(Context paramContext)
  {
    sContext = paramContext;
  }
  
  public static boolean isOnline(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected()))
      {
        boolean bool = paramContext.isAvailable();
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isPackageInstalled(List<String> paramList)
  {
    if (sContext == null) {
      return false;
    }
    Iterator localIterator = sContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int i = 0;
      while (i < paramList.size())
      {
        if (((String)paramList.get(i)).equalsIgnoreCase(localPackageInfo.packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
}
