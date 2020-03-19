package com.prilaga.a.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import java.util.Iterator;
import java.util.List;

public final class i
{
  private static String a;
  
  public static boolean a()
  {
    boolean bool = Build.FINGERPRINT.startsWith("generic");
    int k = 0;
    int i;
    if ((!bool) && (!Build.FINGERPRINT.startsWith("unknown")) && (!Build.MODEL.contains("google_sdk")) && (!Build.MODEL.contains("Emulator")) && (!Build.MODEL.contains("Android SDK built for x86")) && (!Build.MANUFACTURER.contains("Genymotion"))) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 0) {
      return true;
    }
    int j = k;
    if (Build.BRAND.startsWith("generic"))
    {
      j = k;
      if (Build.DEVICE.startsWith("generic")) {
        j = 1;
      }
    }
    i |= j;
    if (i != 0) {
      return true;
    }
    return i | "google_sdk".equals(Build.PRODUCT);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        boolean bool = ((ApplicationInfo)paramContext.next()).packageName.equalsIgnoreCase(paramString);
        if (bool) {
          return true;
        }
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static String b()
  {
    return Settings.Secure.getString(a.a().d().getContentResolver(), "android_id");
  }
  
  public static String c()
  {
    return a;
  }
  
  @TargetApi(21)
  public static String d()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return Build.SUPPORTED_ABIS[0];
    }
    return Build.CPU_ABI;
  }
}
