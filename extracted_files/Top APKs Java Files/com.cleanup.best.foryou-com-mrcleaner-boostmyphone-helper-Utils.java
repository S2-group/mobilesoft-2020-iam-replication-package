package com.mrcleaner.boostmyphone.helper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityManager;
import cleaner.com.accessibilitycorelib.model.Application;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mrcleaner.boostmyphone.device.helper.ExecutorController;
import com.mrcleaner.boostmyphone.device.ram.BoosterService;
import com.mrcleaner.boostmyphone.device.storage.PackageInfo;
import com.mrcleaner.boostmyphone.web.CallbackAdapter;
import com.mrcleaner.boostmyphone.web.ResticRiniApi;
import com.mrcleaner.boostmyphone.web.ResticRiniRestService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;

public class Utils
{
  public static long a(Context paramContext)
  {
    long l = PlatformHelper.c(paramContext);
    return (System.currentTimeMillis() - l) / 86400000L;
  }
  
  public static long a(Context paramContext, int paramInt)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    paramContext.getMemoryInfo(new ActivityManager.MemoryInfo());
    return paramContext.getProcessMemoryInfo(new int[] { paramInt })[0].getTotalPss();
  }
  
  public static Intent a(String paramString)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    if (Build.VERSION.SDK_INT >= 21) {
      localIntent.setFlags(277348352);
    } else {
      localIntent.setFlags(277348352);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    return localIntent;
  }
  
  public static List<PackageInfo> a(Context paramContext, List<Application> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Application localApplication = (Application)paramList.next();
      PackageInfo localPackageInfo = new PackageInfo();
      try
      {
        localPackageInfo.c = ((String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(localApplication.c(), 128)));
        localPackageInfo.b = a(paramContext, localApplication.b());
        localPackageInfo.g = localApplication.b();
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        ThrowableExtension.a(localNameNotFoundException);
        localPackageInfo.c = "Android App Name";
      }
      localPackageInfo.e = localApplication.c();
      if (localApplication.a() != 0L) {
        localPackageInfo.a = localApplication.a();
      }
      localPackageInfo.j = true;
      localArrayList.add(localPackageInfo);
    }
    return localArrayList;
  }
  
  public static void a(Utils.ExecCallBack paramExecCallBack, Context paramContext)
  {
    Executors.newSingleThreadExecutor().execute(new Utils.1(paramContext, paramExecCallBack));
  }
  
  public static boolean a()
  {
    String str = Build.MANUFACTURER;
    return (str != null) && (str.equalsIgnoreCase("xiaomi"));
  }
  
  public static boolean a(String paramString, PackageManager paramPackageManager)
  {
    try
    {
      paramPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      ThrowableExtension.a(paramString);
    }
    return false;
  }
  
  public static boolean b()
  {
    String str = Build.MANUFACTURER;
    return (str != null) && (str.equalsIgnoreCase("huawei"));
  }
  
  public static boolean b(Context paramContext)
  {
    String str = paramContext.getString(2131755050);
    paramContext = ((AccessibilityManager)paramContext.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1).iterator();
    while (paramContext.hasNext()) {
      if (str.equals(((AccessibilityServiceInfo)paramContext.next()).getId())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean c()
  {
    String str = Build.MANUFACTURER;
    return (str != null) && (str.equalsIgnoreCase("HTC"));
  }
  
  public static boolean c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.facebook.katana", 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException|Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  private static int d(String paramString)
  {
    paramString = paramString.split(" ");
    int m = paramString.length;
    int i = 0;
    int k;
    for (int j = 0; i < m; j = k)
    {
      String str = paramString[i];
      k = j;
      if ((str.trim().isEmpty()) || (j == 1)) {}
      try
      {
        i = Integer.parseInt(str);
        return i;
      }
      catch (NumberFormatException paramString) {}
      k = j + 1;
      i += 1;
    }
    return -1;
    return 0;
  }
  
  public static List<Application> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x81) <= 0) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName())) && (!BoosterService.a.contains(localApplicationInfo.packageName))) {
        localArrayList.add(new Application(1, localApplicationInfo.packageName));
      }
    }
    return localArrayList;
  }
  
  private static String e(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      paramString = Runtime.getRuntime().exec(paramString);
      paramString.waitFor();
      paramString = new BufferedReader(new InputStreamReader(paramString.getInputStream()));
      try
      {
        for (;;)
        {
          String str = paramString.readLine();
          if (str == null) {
            break;
          }
          localStringBuffer.append(str);
          localStringBuffer.append("-");
        }
        return localStringBuffer.toString();
      }
      finally
      {
        if (paramString != null) {
          paramString.close();
        }
      }
      return null;
    }
    catch (InterruptedException paramString)
    {
      ThrowableExtension.a(paramString);
    }
    catch (IOException paramString)
    {
      ThrowableExtension.a(paramString);
    }
  }
  
  public static boolean e(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (((localApplicationInfo.flags & 0x81) <= 0) && (localApplicationInfo.packageName.equals("com.whatsapp"))) {
        return true;
      }
    }
    return false;
  }
  
  @TargetApi(21)
  public static List<Application> f(Context paramContext)
  {
    Object localObject3 = (UsageStatsManager)paramContext.getSystemService("usagestats");
    Object localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).add(5, -1);
    long l1 = ((Calendar)localObject1).getTimeInMillis();
    long l2 = System.currentTimeMillis();
    Object localObject2 = p(paramContext);
    localObject1 = new HashSet();
    localObject3 = ((UsageStatsManager)localObject3).queryUsageStats(0, l1, l2).iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = ((UsageStats)((Iterator)localObject3).next()).getPackageName();
      if (((Set)localObject2).contains(localObject4)) {
        ((Set)localObject1).add(localObject4);
      }
    }
    localObject2 = new ArrayList();
    localObject1 = ((Set)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (String)((Iterator)localObject1).next();
      if (!paramContext.getPackageName().equals(localObject3))
      {
        localObject4 = new Application();
        ((Application)localObject4).a((String)localObject3);
        ((List)localObject2).add(localObject4);
      }
    }
    return localObject2;
  }
  
  public static Locale g(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return paramContext.getResources().getConfiguration().getLocales().get(0);
    }
    return paramContext.getResources().getConfiguration().locale;
  }
  
  public static void h(Context paramContext)
  {
    paramContext = g(paramContext).getLanguage();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("not_support_");
    localStringBuilder.append(paramContext);
    Logger.b(localStringBuilder.toString());
  }
  
  public static void i(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    ExecutorController.a().a(new Utils..Lambda.0(paramContext));
  }
  
  public static boolean j(Context paramContext)
  {
    String str = paramContext.getString(2131755050);
    paramContext = ((AccessibilityManager)paramContext.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1).iterator();
    while (paramContext.hasNext()) {
      if (str.equals(((AccessibilityServiceInfo)paramContext.next()).getId())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean k(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName("com.facebook.katana", "com.facebook.katana.LoginActivity"));
    paramContext = localIntent.resolveActivityInfo(paramContext.getPackageManager(), localIntent.getFlags());
    return (paramContext != null) && (paramContext.exported);
  }
  
  public static void l(Context paramContext)
  {
    String str = FirebaseInstanceId.a().c();
    if (str != null) {
      ResticRiniRestService.a().a(PlatformHelper.e(paramContext), "com.cleanup.best.foryou", str).a(new CallbackAdapter());
    }
  }
  
  public static boolean m(Context paramContext)
  {
    return !PreferenceUtils.l(paramContext).isEmpty();
  }
  
  public static boolean n(Context paramContext)
  {
    if (PreferenceUtils.d(paramContext)) {
      return false;
    }
    long l = PlatformHelper.c(paramContext);
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.set(1, 2018);
    localCalendar1.set(2, 11);
    localCalendar1.set(5, 20);
    localCalendar1.set(11, 0);
    localCalendar1.set(12, 0);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(l);
    if ((!PreferenceUtils.l(paramContext).isEmpty()) && (localCalendar2.after(localCalendar1)) && (Build.VERSION.SDK_INT < 26) && (!a()) && (!b()) && (!c()))
    {
      Logger.b("DELETE_ICON");
      return true;
    }
    return false;
  }
  
  private static Set<String> p(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x81) <= 0) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName())) && (!BoosterService.a.contains(localApplicationInfo.packageName))) {
        localHashSet.add(localApplicationInfo.packageName);
      }
    }
    return localHashSet;
  }
}
