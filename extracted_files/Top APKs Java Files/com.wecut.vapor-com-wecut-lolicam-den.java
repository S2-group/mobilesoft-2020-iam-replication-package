package com.wecut.lolicam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.ali.mobisecenhance.Init;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import z.z.z.z2;

public final class den
{
  static List<String> ʻ(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((PackageInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  @SuppressLint({"HardwareIds"})
  public static String ʼ(Context paramContext)
  {
    if (paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName()) != 0) {
      paramContext = "";
    }
    for (;;)
    {
      return paramContext;
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext == null) {
          return "";
        }
        paramContext = paramContext.getDeviceId();
        if (!TextUtils.isEmpty(paramContext))
        {
          long l = Long.parseLong(paramContext);
          if (l > 0L) {
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          Log.e("TelephonyManager", "TelephonyManager exception: " + paramContext);
        }
      }
    }
    return "";
  }
}
