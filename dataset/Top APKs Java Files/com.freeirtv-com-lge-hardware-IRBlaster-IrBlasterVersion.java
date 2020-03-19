package com.lge.hardware.IRBlaster;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class IrBlasterVersion
{
  protected static final String UEICONTROLPACKAGEBASENAME = "com.uei.lg.quicksetsdk";
  protected static final String UEICONTROLSERVICENAME = "com.uei.control.Service";
  private static String sInstalledPackage = null;
  private static String sUEICONTROLPACKAGE = "com.uei.lg.quicksetsdk";
  
  public IrBlasterVersion() {}
  
  protected static String getPackageName(Context paramContext)
  {
    if (sInstalledPackage != null) {
      return sInstalledPackage;
    }
    if (isInstalledApplication("com.uei.lg.quicksetsdk", paramContext)) {}
    for (sInstalledPackage = sUEICONTROLPACKAGE;; sInstalledPackage = "com.uei.lg.quicksetsdk") {
      return sInstalledPackage;
    }
  }
  
  protected static String getServiceName()
  {
    return "com.uei.control.Service";
  }
  
  private static boolean isInstalledApplication(String paramString, Context paramContext)
  {
    paramString = paramContext.getPackageManager();
    if (paramString != null) {
      try
      {
        paramString = paramString.getInstalledPackages(4);
        if (paramString != null)
        {
          paramString = paramString.iterator();
          while (paramString.hasNext())
          {
            paramContext = (PackageInfo)paramString.next();
            if (paramContext.packageName.startsWith("com.uei.lg.quicksetsdk")) {
              sUEICONTROLPACKAGE = paramContext.packageName;
            }
          }
        }
      }
      catch (Exception paramString)
      {
        return false;
      }
    }
    return true;
  }
}
