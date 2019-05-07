package com.handycloset.android.plslibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v7.app.AppCompatActivity;
import b.c.b.a;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class m
{
  public static final m a = new m();
  
  private m() {}
  
  public static String a(Context paramContext)
  {
    a.b(paramContext, "context");
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      a.a(paramContext, "packageInfo.versionName");
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a.b(paramContext, "context");
    a.b(paramString1, "homeUrlString");
    a.b(paramString2, "page");
    try
    {
      Object localObject = Locale.getDefault();
      a.a(localObject, "Locale.getDefault()");
      localObject = ((Locale)localObject).getLanguage();
      long l = b(paramContext);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append("?vc=");
      localStringBuilder.append(l);
      localStringBuilder.append("&api=");
      localStringBuilder.append(Build.VERSION.SDK_INT);
      localStringBuilder.append("&hl=");
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("&page=");
      localStringBuilder.append(paramString2);
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
      paramString1.setFlags(268435456);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void a(AppCompatActivity paramAppCompatActivity)
  {
    a.b(paramAppCompatActivity, "activity");
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setFlags(268435456);
      StringBuilder localStringBuilder = new StringBuilder("package:");
      localStringBuilder.append(paramAppCompatActivity.getPackageName());
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
      paramAppCompatActivity.startActivity(localIntent);
      return;
    }
    catch (Throwable paramAppCompatActivity) {}
  }
  
  public static void a(AppCompatActivity paramAppCompatActivity, String paramString)
  {
    a.b(paramAppCompatActivity, "activity");
    a.b(paramString, "packageName");
    try
    {
      paramString = "https://play.google.com/store/apps/details?id=".concat(String.valueOf(paramString));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("&referrer=");
      paramString = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("utm_source%3D");
      localStringBuilder.append(paramAppCompatActivity.getPackageName());
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
      paramString.setFlags(268435456);
      paramAppCompatActivity.startActivity(paramString);
      return;
    }
    catch (Throwable paramAppCompatActivity) {}
  }
  
  public static void a(AppCompatActivity paramAppCompatActivity, String paramString1, String paramString2)
  {
    a.b(paramAppCompatActivity, "activity");
    a.b(paramString1, "packageName");
    a.b(paramString2, "activityName");
    if (b((Context)paramAppCompatActivity, paramString1)) {}
    try
    {
      Intent localIntent = new Intent();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append('.');
      localStringBuilder.append(paramString2);
      localIntent.setClassName(paramString1, localStringBuilder.toString());
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setFlags(270532608);
      paramAppCompatActivity.startActivity(localIntent);
      return;
    }
    catch (Throwable paramAppCompatActivity) {}
    a(paramAppCompatActivity, paramString1);
    return;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    a.b(paramContext, "context");
    a.b(paramString, "packageName");
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if (paramContext != null) {
        bool = paramContext.enabled;
      }
      return bool;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static long b(Context paramContext)
  {
    a.b(paramContext, "context");
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if (Build.VERSION.SDK_INT < 28) {
        return paramContext.versionCode;
      }
      a.a(paramContext, "packageInfo");
      long l = paramContext.getLongVersionCode();
      return l;
    }
    catch (Throwable paramContext) {}
    return 0L;
  }
  
  public static void b(AppCompatActivity paramAppCompatActivity)
  {
    a.b(paramAppCompatActivity, "activity");
    String str = paramAppCompatActivity.getPackageName();
    a.a(str, "activity.packageName");
    a(paramAppCompatActivity, str);
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext()) {
          if (a.a(paramString, ((ApplicationInfo)paramContext.next()).processName))
          {
            paramContext = new StringBuilder(" '");
            paramContext.append(paramString);
            paramContext.append("' is already installed");
            return true;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder(" '");
    paramContext.append(paramString);
    paramContext.append("' is not installed");
    return false;
  }
  
  public static void c(AppCompatActivity paramAppCompatActivity)
  {
    a.b(paramAppCompatActivity, "activity");
    try
    {
      Object localObject2 = (Context)paramAppCompatActivity;
      a.b(localObject2, "context");
      Object localObject1 = ((Context)localObject2).getPackageName();
      Object localObject3 = Locale.getDefault();
      a.a(localObject3, "Locale.getDefault()");
      localObject3 = ((Locale)localObject3).getLanguage();
      long l = b((Context)localObject2);
      localObject2 = new StringBuilder("https://privacy.handycloset.app/");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("?hl=");
      ((StringBuilder)localObject2).append((String)localObject3);
      ((StringBuilder)localObject2).append("&vc=");
      ((StringBuilder)localObject2).append(l);
      localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject2).toString()));
      ((Intent)localObject1).setFlags(268435456);
      paramAppCompatActivity.startActivity((Intent)localObject1);
      return;
    }
    catch (Throwable paramAppCompatActivity) {}
  }
}
