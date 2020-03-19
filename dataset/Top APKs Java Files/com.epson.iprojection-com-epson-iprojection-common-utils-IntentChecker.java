package com.epson.iprojection.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public final class IntentChecker
{
  private IntentChecker() {}
  
  public static boolean isInstalledApp(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
    } while (((ApplicationInfo)paramContext.next()).packageName.compareTo(paramString) != 0);
    return true;
  }
  
  public static boolean isIntentAvailable(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().queryIntentActivities(new Intent(paramString), 65536).size() > 0;
  }
}
