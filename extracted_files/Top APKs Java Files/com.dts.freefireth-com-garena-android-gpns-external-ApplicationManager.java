package com.garena.android.gpns.external;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.garena.android.gpns.utility.AppLogger;
import java.util.Iterator;
import java.util.List;

public final class ApplicationManager
{
  public static final String APPLICATION_ID = "com.garena.sdk.push.applicationId";
  
  static
  {
    if (!ApplicationManager.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private ApplicationManager() {}
  
  public static String getPackageForId(int paramInt, Context paramContext)
  {
    assert (paramContext.getPackageManager() != null);
    AppLogger.d("Receive Package App ID " + paramInt);
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if ((localApplicationInfo.metaData != null) && (Integer.valueOf(localApplicationInfo.metaData.getInt("com.garena.sdk.push.applicationId", -1)).intValue() == paramInt))
      {
        AppLogger.d("Found a match for push notification " + localApplicationInfo.packageName);
        return localApplicationInfo.packageName;
      }
    }
    return "";
  }
}
