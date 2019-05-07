package com.bsoft.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

final class s
{
  private static final String a = "https://play.google.com/store/apps/details?id=";
  
  private s() {}
  
  static Uri a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    return Uri.parse(localStringBuilder.toString());
  }
  
  static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
}
