package com.calimoto.calimoto;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import com.calimoto.calimoto.android.i;
import com.calimoto.calimoto.q.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class j
{
  public static double a(double paramDouble, int paramInt)
  {
    double d = paramInt;
    return Math.round(paramDouble * Math.pow(10.0D, d)) / Math.pow(10.0D, d);
  }
  
  public static String a(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static void a(Activity paramActivity)
  {
    paramActivity.setRequestedOrientation(new a(paramActivity).aN());
  }
  
  public static void a(final Activity paramActivity, final Intent paramIntent1, Intent paramIntent2)
  {
    paramActivity.startActivity(paramIntent2);
    new Handler().postDelayed(new i(paramActivity)
    {
      protected void a()
      {
        paramActivity.startActivity(paramIntent1);
      }
    }, 20L);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    paramString = paramString.split(Pattern.quote("_"));
    if (paramString.length == 1) {
      paramString = new Locale(paramString[0], "");
    } else {
      paramString = new Locale(paramString[0], paramString[1]);
    }
    paramContext = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = paramContext.getDisplayMetrics();
    Configuration localConfiguration = paramContext.getConfiguration();
    localConfiguration.locale = paramString;
    paramContext.updateConfiguration(localConfiguration, localDisplayMetrics);
    Locale.setDefault(paramString);
  }
  
  public static void a(Window paramWindow)
  {
    View localView2 = paramWindow.getCurrentFocus();
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = new View(paramWindow.getContext());
    }
    ((InputMethodManager)paramWindow.getContext().getSystemService("input_method")).hideSoftInputFromWindow(localView1.getWindowToken(), 0);
  }
  
  public static boolean a(Activity paramActivity, String paramString, int paramInt)
  {
    for (;;)
    {
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("market://details?id=");
        ((StringBuilder)localObject).append(paramString);
        localObject = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
        if (paramInt == -1)
        {
          paramActivity.startActivity((Intent)localObject);
          return true;
        }
        paramActivity.startActivityForResult((Intent)localObject, paramInt);
        return true;
      }
      catch (Exception localException)
      {
        Object localObject;
        continue;
      }
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("https://play.google.com/store/apps/details?id=");
        ((StringBuilder)localObject).append(paramString);
        paramString = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
        if (paramInt == -1)
        {
          paramActivity.startActivity(paramString);
          return true;
        }
        paramActivity.startActivityForResult(paramString, paramInt);
        return true;
      }
      catch (Exception paramString) {}
    }
    h.a(paramActivity, 2131624860);
    return false;
  }
  
  public static boolean a(Context paramContext, Class<?> paramClass)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.next();
      if (paramClass.getName().equals(localRunningServiceInfo.service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  public static int b(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static void b(Window paramWindow)
  {
    View localView2 = paramWindow.getCurrentFocus();
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = new View(paramWindow.getContext());
    }
    ((InputMethodManager)paramWindow.getContext().getSystemService("input_method")).showSoftInput(localView1, 0);
  }
  
  public static boolean b(Activity paramActivity)
  {
    return a(paramActivity, paramActivity.getPackageName(), -1);
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static List<String> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledApplications(9344).iterator();
    while (paramContext.hasNext()) {
      localArrayList.add(((ApplicationInfo)paramContext.next()).packageName);
    }
    return localArrayList;
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso();
      if ((paramContext != null) && (paramContext.length() == 2))
      {
        paramContext = paramContext.toUpperCase(Locale.US);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext.getPhoneType() != 2)
      {
        paramContext = paramContext.getNetworkCountryIso();
        if ((paramContext != null) && (paramContext.length() == 2))
        {
          paramContext = paramContext.toUpperCase(Locale.US);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String g(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getConfiguration().locale.getCountry();
      if ((paramContext != null) && (paramContext.length() == 2))
      {
        paramContext = paramContext.toUpperCase(Locale.US);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String h(Context paramContext)
  {
    Object localObject2 = e(paramContext);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = f(paramContext);
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = g(paramContext);
    }
    return localObject2;
  }
  
  public static int i(Context paramContext)
  {
    paramContext = new a(paramContext);
    int j = paramContext.ae() + 1;
    int i = j;
    if (j >= 1000) {
      i = 0;
    }
    paramContext.d(i);
    return 22000 + i;
  }
}
