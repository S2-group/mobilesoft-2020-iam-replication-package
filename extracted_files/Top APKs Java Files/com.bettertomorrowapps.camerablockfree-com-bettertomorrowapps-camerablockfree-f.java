package com.bettertomorrowapps.camerablockfree;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.support.v4.app.NotificationCompat.Builder;
import com.google.b.a.a.a.a.a;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class f
{
  public static String a = "ca-app-pub-7394879093093087/9742311275";
  public static String b = "pub-7394879093093087";
  public static NotificationCompat.Builder c;
  private static Integer d = Integer.valueOf(20);
  private static Integer e = Integer.valueOf(22);
  private static Long f = Long.valueOf(864000000L);
  private static Long g = Long.valueOf(345600000L);
  
  public static ContextWrapper a(Context paramContext)
  {
    Object localObject2 = paramContext.getSharedPreferences("blockCamera", 0).getString("appLanguageCode", "none");
    Object localObject1 = paramContext;
    if (localObject2 != "none")
    {
      localObject1 = paramContext.getResources().getConfiguration();
      if (Build.VERSION.SDK_INT < 24) {
        break label99;
      }
      ((Configuration)localObject1).getLocales().get(0);
      localObject2 = new Locale((String)localObject2);
      Locale.setDefault((Locale)localObject2);
      if (Build.VERSION.SDK_INT < 24) {
        break label107;
      }
      ((Configuration)localObject1).setLocale((Locale)localObject2);
      label76:
      if (Build.VERSION.SDK_INT < 17) {
        break label115;
      }
    }
    for (localObject1 = paramContext.createConfigurationContext((Configuration)localObject1);; localObject1 = paramContext)
    {
      return new ContextWrapper((Context)localObject1);
      label99:
      Locale localLocale = ((Configuration)localObject1).locale;
      break;
      label107:
      ((Configuration)localObject1).locale = ((Locale)localObject2);
      break label76;
      label115:
      paramContext.getResources().updateConfiguration((Configuration)localObject1, paramContext.getResources().getDisplayMetrics());
    }
  }
  
  public static Boolean a(SharedPreferences paramSharedPreferences)
  {
    boolean bool = true;
    Calendar.getInstance().get(11);
    if (paramSharedPreferences.getBoolean("l_pro", false) == true) {
      return Boolean.valueOf(true);
    }
    if (System.currentTimeMillis() <= paramSharedPreferences.getLong("installedDate", 0L) + f.longValue()) {}
    for (;;)
    {
      return Boolean.valueOf(bool);
      bool = false;
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Object localObject1 = "";
    try
    {
      localObject2 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject1 = "\n\n\n\n\n\n\n----------------------------------------------------------\nVersion: " + (String)localObject1 + "p, Lang: " + Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry() + ", Mobile: " + Build.MANUFACTURER + " " + Build.MODEL + ", Android: " + Build.VERSION.RELEASE;
    localObject2 = new String[2];
    localObject2[0] = "contact@bytepioneers.com";
    localObject2[1] = "";
    try
    {
      Intent localIntent1 = new Intent("android.intent.action.SEND");
      localIntent1.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
      localIntent1.putExtra("android.intent.extra.EMAIL", (String[])localObject2);
      localIntent1.putExtra("android.intent.extra.SUBJECT", paramString);
      localIntent1.putExtra("android.intent.extra.TEXT", (String)localObject1);
      localIntent1.setType("text/plain");
      paramContext.startActivity(localIntent1);
      return;
    }
    catch (Exception localException1)
    {
      try
      {
        Intent localIntent2 = new Intent("android.intent.action.SENDTO");
        localIntent2.setData(Uri.parse("mailto:"));
        localIntent2.putExtra("android.intent.extra.EMAIL", (String[])localObject2);
        localIntent2.putExtra("android.intent.extra.SUBJECT", paramString);
        localIntent2.putExtra("android.intent.extra.TEXT", (String)localObject1);
        paramContext.startActivity(localIntent2);
        return;
      }
      catch (Exception localException2)
      {
        a.a(localException2);
        Intent localIntent3 = new Intent("android.intent.action.SEND");
        localIntent3.putExtra("android.intent.extra.EMAIL", (String[])localObject2);
        localIntent3.putExtra("android.intent.extra.SUBJECT", paramString);
        localIntent3.putExtra("android.intent.extra.TEXT", (String)localObject1);
        localIntent3.setType("text/plain");
        localIntent3.setFlags(268435456);
        paramContext.startActivity(Intent.createChooser(localIntent3, paramContext.getString(2131427453)));
        return;
      }
    }
  }
  
  public static Boolean b(SharedPreferences paramSharedPreferences)
  {
    boolean bool = true;
    int i = Calendar.getInstance().get(11);
    if (paramSharedPreferences.getBoolean("l_pro", false) == true) {
      return Boolean.valueOf(false);
    }
    if ((i >= d.intValue()) && (i < e.intValue()) && (System.currentTimeMillis() > paramSharedPreferences.getLong("installedDate", 0L) + f.longValue())) {}
    for (;;)
    {
      return Boolean.valueOf(bool);
      bool = false;
    }
  }
  
  public static List<HashMap<String, String>> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(4096).iterator();
    int j = 0;
    int i = 0;
    int k = 0;
    int m;
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      m = k + 1;
      String[] arrayOfString = localPackageInfo.requestedPermissions;
      HashMap localHashMap = new HashMap();
      if (arrayOfString == null) {
        break label391;
      }
      Boolean localBoolean1 = Boolean.valueOf(false);
      Boolean localBoolean2 = Boolean.valueOf(false);
      k = 0;
      while (k < arrayOfString.length)
      {
        if (arrayOfString[k].equals("android.permission.INTERNET")) {
          localBoolean2 = Boolean.valueOf(true);
        }
        if (arrayOfString[k].equals("android.permission.CAMERA")) {
          localBoolean1 = Boolean.valueOf(true);
        }
        k += 1;
      }
      if ((localBoolean1.booleanValue()) && (localBoolean2.booleanValue()))
      {
        j += 1;
        localHashMap.put("nameOfApp", localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo));
        localHashMap.put("description", localPackageInfo.packageName);
        localHashMap.put("isInternet", paramContext.getString(2131427470));
        localArrayList.add(localHashMap);
        k = m;
      }
      else
      {
        if (!localBoolean1.booleanValue()) {
          break label391;
        }
        localHashMap.put("nameOfApp", localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo));
        localHashMap.put("description", localPackageInfo.packageName);
        localHashMap.put("isInternet", " ");
        localArrayList.add(localHashMap);
        i += 1;
      }
    }
    label391:
    for (;;)
    {
      k = m;
      break;
      paramContext = paramContext.getSharedPreferences("blockCamera", 0).edit();
      paramContext.putInt("numberOfCameraApps", i + j);
      paramContext.putInt("numberOfCameraInternetApps", j);
      paramContext.putInt("numberOfApps", k);
      paramContext.commit();
      return localArrayList;
    }
  }
  
  public static Boolean c(SharedPreferences paramSharedPreferences)
  {
    if ((paramSharedPreferences.getBoolean("ratingDialogNeverShow", false) == true) || (paramSharedPreferences.getBoolean("rated", false) == true) || (paramSharedPreferences.getFloat("ratingValue", 10.0F) < 5.0F)) {
      return Boolean.valueOf(false);
    }
    if (System.currentTimeMillis() < paramSharedPreferences.getLong("ratingDialogLastTimeShown", 0L) + g.longValue()) {
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(true);
  }
  
  public static String c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(4096);
    Object localObject1 = paramContext.iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if ((((PackageInfo)localObject2).packageName.equals("com.sec.android.app.camera")) || (((PackageInfo)localObject2).packageName.equals("com.huawei.camera")) || (((PackageInfo)localObject2).packageName.equals("com.google.android.GoogleCamera"))) {
        return ((PackageInfo)localObject2).packageName.toString();
      }
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      localObject1 = (PackageInfo)paramContext.next();
      localObject2 = ((PackageInfo)localObject1).requestedPermissions;
      if (localObject2 != null)
      {
        int i = 0;
        while (i < localObject2.length)
        {
          if ((localObject2[i].equals("android.permission.CAMERA")) && ((((PackageInfo)localObject1).packageName.toString().contains("camera") == true) || (((PackageInfo)localObject1).packageName.toString().contains("Camera") == true))) {
            return ((PackageInfo)localObject1).packageName.toString();
          }
          i += 1;
        }
      }
    }
    return "";
  }
}
