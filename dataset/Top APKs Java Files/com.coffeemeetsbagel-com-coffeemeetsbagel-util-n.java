package com.coffeemeetsbagel.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.coffeemeetsbagel.bakery.Bakery;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class n
{
  public static String a()
  {
    return Locale.getDefault().toString();
  }
  
  public static void a(Activity paramActivity)
  {
    try
    {
      if (i.b())
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("samsungapps://ProductDetail/");
        ((StringBuilder)localObject).append(paramActivity.getApplicationContext().getPackageName());
        localObject = Uri.parse(((StringBuilder)localObject).toString());
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("market://details?id=");
        ((StringBuilder)localObject).append(paramActivity.getApplicationContext().getPackageName());
        localObject = Uri.parse(((StringBuilder)localObject).toString());
      }
      a.a(paramActivity, new Intent("android.intent.action.VIEW", (Uri)localObject));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Object localObject;
      for (;;) {}
    }
    if (i.b())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://www.samsungapps.com/appquery/appDetail.as?appId=");
      ((StringBuilder)localObject).append(paramActivity.getApplicationContext().getPackageName());
      localObject = Uri.parse(((StringBuilder)localObject).toString());
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("https://play.google.com/store/apps/details?id=");
      ((StringBuilder)localObject).append(paramActivity.getApplicationContext().getPackageName());
      localObject = Uri.parse(((StringBuilder)localObject).toString());
    }
    a.a(paramActivity, new Intent("android.intent.action.VIEW", (Uri)localObject));
  }
  
  public static boolean a(String paramString)
  {
    Iterator localIterator = Bakery.a().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b()
  {
    return a().toLowerCase().equals("en_us");
  }
}
