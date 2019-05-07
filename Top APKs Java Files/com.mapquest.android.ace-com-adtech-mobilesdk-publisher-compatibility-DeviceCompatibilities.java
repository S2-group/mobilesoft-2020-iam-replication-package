package com.adtech.mobilesdk.publisher.compatibility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeviceCompatibilities
{
  private static Map<DeviceCompatibilities.Features, Boolean> a = new HashMap();
  
  public static boolean a(Context paramContext, DeviceCompatibilities.Features paramFeatures)
  {
    switch (DeviceCompatibilities.1.a[paramFeatures.ordinal()])
    {
    }
    do
    {
      while (!paramContext.hasNext())
      {
        a.put(DeviceCompatibilities.Features.a, Boolean.valueOf(false));
        return false;
        if (a.containsKey(DeviceCompatibilities.Features.a)) {
          return ((Boolean)a.get(DeviceCompatibilities.Features.a)).booleanValue();
        }
        paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
      }
    } while (!((PackageInfo)paramContext.next()).packageName.toLowerCase().contains("com.adobe.flashplayer"));
    a.put(DeviceCompatibilities.Features.a, Boolean.valueOf(true));
    return true;
  }
}
