package com.clevertap.android.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

final class Utils
{
  private static int installedApps = -1;
  private static int serviceCount = -1;
  
  static Bitmap drawableToBitmap(Drawable paramDrawable)
    throws NullPointerException, PackageManager.NameNotFoundException
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  static Bitmap getAppIcon(Context paramContext)
    throws NullPointerException, PackageManager.NameNotFoundException
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 9)
      {
        Drawable localDrawable = paramContext.getPackageManager().getApplicationLogo(paramContext.getApplicationInfo());
        if (localDrawable != null) {
          break label60;
        }
        throw new Exception("Logo is null");
      }
    }
    catch (Exception localException)
    {
      return drawableToBitmap(paramContext.getPackageManager().getApplicationIcon(paramContext.getApplicationInfo()));
    }
    throw new Exception("API level is < 9");
    label60:
    Bitmap localBitmap = drawableToBitmap(localException);
    return localBitmap;
  }
  
  static Bitmap getBitmapFromURL(String paramString)
  {
    String str2 = paramString.replace("///", "/").replace("//", "/").replace("http:/", "http://").replace("https:/", "https://");
    Object localObject = null;
    paramString = null;
    for (;;)
    {
      try
      {
        localHttpURLConnection = (HttpURLConnection)new URL(str2).openConnection();
        paramString = localHttpURLConnection;
        localObject = localHttpURLConnection;
        localHttpURLConnection.setDoInput(true);
        paramString = localHttpURLConnection;
        localObject = localHttpURLConnection;
        localHttpURLConnection.connect();
        paramString = localHttpURLConnection;
        localObject = localHttpURLConnection;
        Bitmap localBitmap = BitmapFactory.decodeStream(localHttpURLConnection.getInputStream());
        paramString = localBitmap;
        localObject = paramString;
      }
      catch (IOException localIOException)
      {
        HttpURLConnection localHttpURLConnection;
        str1 = paramString;
        Logger.logFine("Couldn't download the notification icon. URL was: " + str2);
        str1 = null;
        if (paramString == null) {
          continue;
        }
        try
        {
          paramString.disconnect();
          return null;
        }
        catch (Throwable paramString)
        {
          Logger.error("Couldn't close connection!", paramString);
          return null;
        }
      }
      finally
      {
        if (str1 == null) {
          break label161;
        }
      }
      try
      {
        localHttpURLConnection.disconnect();
        localObject = paramString;
        return localObject;
      }
      catch (Throwable localThrowable1)
      {
        Logger.error("Couldn't close connection!", localThrowable1);
        return paramString;
      }
    }
    try
    {
      String str1;
      str1.disconnect();
      label161:
      throw paramString;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        Logger.error("Couldn't close connection!", localThrowable2);
      }
    }
  }
  
  static String getCurrentNetworkType(Context paramContext)
  {
    try
    {
      if (((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
        return "WiFi";
      }
      switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
      {
      case 13: 
        return "LTE";
      }
    }
    catch (Throwable paramContext)
    {
      return "Unavailable";
    }
    return "Unknown";
    return "CDMA";
    return "EDGE";
    return "GPRS";
    return "3G";
  }
  
  static int getInstalledAppsCount(Context paramContext)
  {
    if (installedApps == -1)
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
      int i = 0;
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((!localApplicationInfo.packageName.startsWith("com.google")) && (!localApplicationInfo.packageName.startsWith("com.android"))) {
          i += 1;
        }
      }
      installedApps = i;
    }
    return installedApps;
  }
  
  static long getMemoryConsumption()
  {
    long l = Runtime.getRuntime().freeMemory();
    return Runtime.getRuntime().totalMemory() - l;
  }
  
  static Bitmap getNotificationBitmap(String paramString, boolean paramBoolean, Context paramContext)
    throws NullPointerException, PackageManager.NameNotFoundException
  {
    Object localObject = null;
    if ((paramString == null) || (paramString.equals("")))
    {
      paramString = (String)localObject;
      if (paramBoolean) {
        paramString = getAppIcon(paramContext);
      }
    }
    do
    {
      return paramString;
      localObject = paramString;
      if (!paramString.startsWith("http")) {
        localObject = "http://static.wizrocket.com/android/ico//" + paramString;
      }
      localObject = getBitmapFromURL((String)localObject);
      paramString = (String)localObject;
    } while (localObject != null);
    if (paramBoolean) {
      return getAppIcon(paramContext);
    }
    return null;
  }
  
  static int getServiceCount(Context paramContext)
  {
    if (serviceCount == -1) {}
    try
    {
      serviceCount = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4).services.length;
      return serviceCount;
    }
    catch (Throwable paramContext) {}
    return 0;
  }
}
