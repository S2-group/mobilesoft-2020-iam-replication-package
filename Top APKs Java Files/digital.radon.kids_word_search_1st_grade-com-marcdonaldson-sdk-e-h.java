package com.marcdonaldson.sdk.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

final class h
{
  static Uri a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://play.google.com/store/apps/details?id=" + paramString);
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
  
  static Uri b(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("amzn://apps/android?p=" + paramString);
  }
}
