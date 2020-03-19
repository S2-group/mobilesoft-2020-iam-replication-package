package com.baselib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class af
{
  private static HashMap<String, HashMap<String, String>> a = new HashMap();
  private static Map<String, CharSequence> b;
  
  public static Intent a()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return localIntent;
  }
  
  public static Intent a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    Intent localIntent = new Intent();
    int i = Build.VERSION.SDK_INT;
    if (i >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
      return localIntent;
    }
    String str;
    if (i > 7) {
      str = "pkg";
    } else {
      str = "com.android.settings.ApplicationPkgName";
    }
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
    localIntent.putExtra(str, paramString);
    return localIntent;
  }
  
  public static List<ResolveInfo> a(Context paramContext)
  {
    return a(paramContext, a());
  }
  
  public static List<ResolveInfo> a(Context paramContext, Intent paramIntent)
  {
    Object localObject3 = paramContext.getPackageManager();
    try
    {
      localObject1 = ((PackageManager)localObject3).queryIntentActivities(paramIntent, 0);
      i = 1;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= 3) {
          break;
        }
        if (!a((Collection)localObject1))
        {
          localObject2 = localObject1;
          if (((List)localObject1).size() >= 20) {
            break;
          }
        }
        localObject1 = ((PackageManager)localObject3).queryIntentActivities(paramIntent, 0);
        i += 1;
      }
    }
    catch (Exception localException)
    {
      Object localObject1;
      int i;
      Object localObject2;
      int j;
      for (;;) {}
    }
    localObject1 = paramContext.getPackageManager();
    localObject3 = ((PackageManager)localObject1).getInstalledApplications(0);
    paramContext = new ArrayList(0);
    j = ((List)localObject3).size();
    i = 0;
    for (;;)
    {
      localObject2 = paramContext;
      if (i >= j) {
        break;
      }
      paramIntent.setPackage(((ApplicationInfo)((List)localObject3).get(i)).packageName);
      paramContext.addAll(((PackageManager)localObject1).queryIntentActivities(paramIntent, 0));
      i += 1;
    }
    return localObject2;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramString = a(paramString);
    if (paramString == null) {
      return false;
    }
    paramString.addFlags(335544320);
    try
    {
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean a(Collection<?> paramCollection)
  {
    boolean bool = true;
    if (paramCollection != null)
    {
      if (paramCollection.size() < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    if (b == null) {
      b = new HashMap();
    }
    CharSequence localCharSequence = (CharSequence)b.get(paramString);
    if (!TextUtils.isEmpty(localCharSequence)) {
      return localCharSequence.toString();
    }
    paramContext = c(paramContext, paramString);
    if (!TextUtils.isEmpty(paramContext))
    {
      b.put(paramString, paramContext);
      return paramContext.toString();
    }
    return null;
  }
  
  public static CharSequence c(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if ((paramContext != null) && (paramContext.applicationInfo != null))
      {
        paramContext = paramContext.applicationInfo.loadLabel(localPackageManager);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static CharSequence d(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if ((paramContext != null) && (paramContext.applicationInfo != null))
      {
        paramContext = paramContext.versionName;
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void e(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        Intent localIntent = new Intent();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("package:");
        localStringBuilder.append(paramString);
        paramString = Uri.parse(localStringBuilder.toString());
        localIntent.addFlags(268435456);
        localIntent.setAction("android.intent.action.DELETE");
        localIntent.setData(paramString);
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext != null;
    }
    catch (Exception paramContext) {}
    return false;
  }
}
