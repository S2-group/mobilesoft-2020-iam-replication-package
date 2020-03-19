package com.yahoo.mobile.client.android.libs.feedback;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Patterns;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class i
{
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null))
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext()) {
        if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    return Patterns.EMAIL_ADDRESS.matcher(paramString).matches();
  }
}
