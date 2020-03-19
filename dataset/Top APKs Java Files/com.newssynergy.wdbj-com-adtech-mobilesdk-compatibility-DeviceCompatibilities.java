package com.adtech.mobilesdk.compatibility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeviceCompatibilities
{
  private static final String PACKAGE_COM_ADOBE_FLASHPLAYER = "com.adobe.flashplayer";
  private static Map<Features, Boolean> features = new HashMap();
  
  private DeviceCompatibilities() {}
  
  public static boolean hasFeature(Context paramContext, Features paramFeatures)
  {
    switch (1.$SwitchMap$com$adtech$mobilesdk$compatibility$DeviceCompatibilities$Features[paramFeatures.ordinal()])
    {
    }
    do
    {
      while (!paramContext.hasNext())
      {
        features.put(Features.IS_ADOBE_FLASHPLAYER_READY, Boolean.valueOf(false));
        return false;
        if (features.containsKey(Features.IS_ADOBE_FLASHPLAYER_READY)) {
          return ((Boolean)features.get(Features.IS_ADOBE_FLASHPLAYER_READY)).booleanValue();
        }
        paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
      }
    } while (!((PackageInfo)paramContext.next()).packageName.toLowerCase().contains("com.adobe.flashplayer"));
    features.put(Features.IS_ADOBE_FLASHPLAYER_READY, Boolean.valueOf(true));
    return true;
  }
  
  public static enum Features
  {
    IS_ADOBE_FLASHPLAYER_READY;
    
    private Features() {}
  }
}
