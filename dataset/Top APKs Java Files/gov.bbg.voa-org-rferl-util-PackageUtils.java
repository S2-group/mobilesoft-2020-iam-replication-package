package org.rferl.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class PackageUtils
{
  public static final String orbotPackage = "org.torproject.android";
  public static final String psiphonPackage = "com.psiphon3";
  public static final String youtubePackage = "com.google.android.youtube";
  
  public PackageUtils() {}
  
  public static boolean isPackageInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
}
