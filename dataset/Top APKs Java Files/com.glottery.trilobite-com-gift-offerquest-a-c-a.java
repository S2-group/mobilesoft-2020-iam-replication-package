package com.gift.offerquest.a.c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import java.util.List;

public class a
{
  private static List<PackageInfo> a;
  
  public static boolean a(Context paramContext, String paramString)
  {
    for (;;)
    {
      int i;
      try
      {
        if (a != null) {
          break label68;
        }
        a = paramContext.getPackageManager().getInstalledPackages(0);
      }
      catch (Exception paramContext) {}
      if (i < a.size())
      {
        boolean bool = ((PackageInfo)a.get(i)).packageName.equals(paramString);
        if (bool) {
          return true;
        }
        i += 1;
      }
      else
      {
        return false;
        label68:
        i = 0;
      }
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null)
    {
      paramString.setFlags(337641472);
      paramContext.startActivity(paramString);
    }
  }
  
  public static void c(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      Log.e("GameFixUtils", "activityNotFoundException uri:" + paramString);
    }
  }
}
