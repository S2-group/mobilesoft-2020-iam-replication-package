package com.latvisoft.jabraassist.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import com.jabra.assist.diagnostics.AppLog;
import com.jabra.assist.ui.diagnostics.DiagnosticsPreferences.Libs.JabraService;
import java.util.Iterator;
import java.util.List;

public final class VersionChecker
{
  public static final int[] BLACKLISTED_MCC = { 460 };
  public static final String CLASS_NAME = "VersionChecker";
  public static final int FIRST_JS_VERSION = 1050399;
  public static final String SERVICE_PACKAGE_NAME = "com.gnnetcom.jabraservice";
  private static final boolean versionCheckOverride = false;
  private static final String versionExpected = "1.5.3 #1050399";
  
  public VersionChecker() {}
  
  public static boolean isJabraServiceInstalled(Context paramContext)
  {
    boolean bool2 = false;
    Boolean localBoolean = DiagnosticsPreferences.Libs.JabraService.isInstalled(paramContext);
    boolean bool1;
    if (localBoolean != null)
    {
      bool1 = localBoolean.booleanValue();
      return bool1;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((PackageInfo)paramContext.next()).packageName.equalsIgnoreCase("com.gnnetcom.jabraservice"));
    return true;
  }
  
  public static boolean isJabraServiceVersionOk(Context paramContext)
  {
    Object localObject = DiagnosticsPreferences.Libs.JabraService.isVersionOk(paramContext);
    if (localObject != null) {
      return ((Boolean)localObject).booleanValue();
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    int j = 0;
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      if (((PackageInfo)localObject).packageName.equalsIgnoreCase("com.gnnetcom.jabraservice"))
      {
        int k = 1;
        AppLog.msg(new Object[] { "VersionChecker", "Jabra Service: " + ((PackageInfo)localObject).versionCode });
        i = k;
        if (((PackageInfo)localObject).versionCode >= 1050399)
        {
          j = 1;
          i = k;
        }
      }
    }
    return (i != 0) && (j != 0);
  }
  
  public static boolean isOperatorBlacklisted(Context paramContext)
  {
    int i = 0;
    boolean bool2 = false;
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    String str = paramContext.getNetworkOperator();
    int j = paramContext.getSimState();
    int k = paramContext.getPhoneType();
    boolean bool1;
    if ((j == 1) && (k != 2))
    {
      bool1 = true;
      return bool1;
    }
    if ((str != null) && (str.length() >= 3))
    {
      j = Integer.parseInt(str.substring(0, 3));
      paramContext = BLACKLISTED_MCC;
      k = paramContext.length;
      for (;;)
      {
        bool1 = bool2;
        if (i >= k) {
          break;
        }
        if (j == paramContext[i]) {
          return true;
        }
        i += 1;
      }
    }
    return true;
  }
}
