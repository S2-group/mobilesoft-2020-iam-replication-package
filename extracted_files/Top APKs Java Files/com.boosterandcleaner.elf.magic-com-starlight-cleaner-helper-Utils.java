package com.starlight.cleaner.helper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AppOpsManager;
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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.LocaleList;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.starlight.cleaner.device.helper.ExecutorController;
import com.starlight.cleaner.device.ram.Application;
import com.starlight.cleaner.device.ram.BoosterService2;
import com.starlight.cleaner.device.storage.PackageInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;

public class Utils
{
  public static long a(Context paramContext, int paramInt)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    paramContext.getMemoryInfo(new ActivityManager.MemoryInfo());
    return paramContext.getProcessMemoryInfo(new int[] { paramInt })[0].getTotalPss();
  }
  
  public static Intent a(Context paramContext, String paramString)
  {
    paramContext = new Intent();
    paramContext.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
    paramContext.setFlags(1350565888);
    paramContext.setData(Uri.parse("market://details?id=" + paramString));
    return paramContext;
  }
  
  public static Intent a(String paramString)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    if (Build.VERSION.SDK_INT >= 21) {
      localIntent.setFlags(277348352);
    }
    for (;;)
    {
      localIntent.setData(Uri.parse("package:" + paramString));
      return localIntent;
      localIntent.setFlags(277348352);
    }
  }
  
  public static Drawable a(String paramString, Context paramContext)
  {
    try
    {
      paramString = paramContext.getPackageManager().getApplicationIcon(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public static List<PackageInfo> a(Context paramContext, List<Application> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramList = paramList.iterator();
    for (;;)
    {
      if (paramList.hasNext())
      {
        Application localApplication = (Application)paramList.next();
        PackageInfo localPackageInfo = new PackageInfo();
        try
        {
          localPackageInfo.c = ((String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(localApplication.c(), 128)));
          localPackageInfo.b = a(paramContext, localApplication.b());
          localPackageInfo.g = localApplication.b();
          localPackageInfo.e = localApplication.c();
          if (localApplication.a() != 0L) {
            localPackageInfo.a = localApplication.a();
          }
          localPackageInfo.j = true;
          localArrayList.add(localPackageInfo);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            ThrowableExtension.a(localNameNotFoundException);
            localPackageInfo.c = "Android App Name";
          }
        }
      }
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
  
  public static boolean a(Context paramContext)
  {
    String str = paramContext.getString(2131755040);
    paramContext = ((AccessibilityManager)paramContext.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1).iterator();
    while (paramContext.hasNext()) {
      if (str.equals(((AccessibilityServiceInfo)paramContext.next()).getId())) {
        return true;
      }
    }
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
  
  public static Intent b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
    paramContext.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.MarketDeepLinkHandlerActivity"));
    paramContext.setFlags(1350565888);
    paramContext.setData(Uri.parse("market://details?id=" + paramString));
    return paramContext;
  }
  
  public static List<Application> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x81) <= 0) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName())) && (!BoosterService2.a.contains(localApplicationInfo.packageName))) {
        localArrayList.add(new Application(1, localApplicationInfo.packageName));
      }
    }
    return localArrayList;
  }
  
  public static boolean c(Context paramContext)
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
      if (!str.trim().isEmpty())
      {
        if (j == 1) {
          try
          {
            i = Integer.parseInt(str);
            return i;
          }
          catch (NumberFormatException paramString)
          {
            return 0;
          }
        }
        k = j + 1;
      }
      i += 1;
    }
    return -1;
  }
  
  @TargetApi(21)
  public static List<Application> d(Context paramContext)
  {
    Object localObject3 = (UsageStatsManager)paramContext.getSystemService("usagestats");
    Object localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).add(5, -1);
    long l1 = ((Calendar)localObject1).getTimeInMillis();
    long l2 = System.currentTimeMillis();
    Object localObject2 = s(paramContext);
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
  
  /* Error */
  private static String e(String paramString)
  {
    // Byte code:
    //   0: new 380	java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial 381	java/lang/StringBuffer:<init>	()V
    //   7: astore_1
    //   8: invokestatic 387	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   11: aload_0
    //   12: invokevirtual 391	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   15: astore_0
    //   16: aload_0
    //   17: invokevirtual 396	java/lang/Process:waitFor	()I
    //   20: pop
    //   21: new 398	java/io/BufferedReader
    //   24: dup
    //   25: new 400	java/io/InputStreamReader
    //   28: dup
    //   29: aload_0
    //   30: invokevirtual 404	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   33: invokespecial 407	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   36: invokespecial 410	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   39: astore_0
    //   40: aload_0
    //   41: invokevirtual 413	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   44: astore_2
    //   45: aload_2
    //   46: ifnull +38 -> 84
    //   49: aload_1
    //   50: aload_2
    //   51: invokevirtual 416	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   54: pop
    //   55: aload_1
    //   56: ldc_w 418
    //   59: invokevirtual 416	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   62: pop
    //   63: goto -23 -> 40
    //   66: astore_1
    //   67: aload_0
    //   68: ifnull +7 -> 75
    //   71: aload_0
    //   72: invokevirtual 421	java/io/BufferedReader:close	()V
    //   75: aload_1
    //   76: athrow
    //   77: astore_0
    //   78: aload_0
    //   79: invokestatic 187	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:a	(Ljava/lang/Throwable;)V
    //   82: aconst_null
    //   83: areturn
    //   84: aload_0
    //   85: ifnull +7 -> 92
    //   88: aload_0
    //   89: invokevirtual 421	java/io/BufferedReader:close	()V
    //   92: aload_1
    //   93: invokevirtual 422	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   96: astore_0
    //   97: aload_0
    //   98: areturn
    //   99: astore_0
    //   100: aload_0
    //   101: invokestatic 187	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:a	(Ljava/lang/Throwable;)V
    //   104: goto -22 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	paramString	String
    //   7	49	1	localStringBuffer	StringBuffer
    //   66	27	1	localObject	Object
    //   44	7	2	str	String
    // Exception table:
    //   from	to	target	type
    //   40	45	66	finally
    //   49	63	66	finally
    //   8	40	77	java/io/IOException
    //   71	75	77	java/io/IOException
    //   75	77	77	java/io/IOException
    //   88	92	77	java/io/IOException
    //   92	97	77	java/io/IOException
    //   8	40	99	java/lang/InterruptedException
    //   71	75	99	java/lang/InterruptedException
    //   75	77	99	java/lang/InterruptedException
    //   88	92	99	java/lang/InterruptedException
    //   92	97	99	java/lang/InterruptedException
  }
  
  public static Locale e(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return paramContext.getResources().getConfiguration().getLocales().get(0);
    }
    return paramContext.getResources().getConfiguration().locale;
  }
  
  public static void f(Context paramContext)
  {
    paramContext = e(paramContext).getLanguage();
    Logger.b("not_support_" + paramContext);
  }
  
  public static String g(Context paramContext)
  {
    paramContext = e(paramContext);
    Log.e("Language", paramContext.getLanguage());
    paramContext = paramContext.getLanguage();
    int i = -1;
    switch (paramContext.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return "";
        if (paramContext.equals("ru"))
        {
          i = 0;
          continue;
          if (paramContext.equals("uk"))
          {
            i = 1;
            continue;
            if (paramContext.equals("en"))
            {
              i = 2;
              continue;
              if (paramContext.equals("es")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    return "Остановить";
    return "Зупинити";
    return "FORCE STOP";
    return "FORZAR DETENCIÓN";
  }
  
  public static String h(Context paramContext)
  {
    paramContext = e(paramContext);
    Log.e("Language", paramContext.getLanguage());
    paramContext = paramContext.getLanguage();
    int i = -1;
    switch (paramContext.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return "";
        if (paramContext.equals("ru"))
        {
          i = 0;
          continue;
          if (paramContext.equals("uk"))
          {
            i = 1;
            continue;
            if (paramContext.equals("en"))
            {
              i = 2;
              continue;
              if (paramContext.equals("es")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    return "Хранилище";
    return "Пам'ять";
    return "Storage";
    return "Almacenamiento";
  }
  
  public static String i(Context paramContext)
  {
    paramContext = e(paramContext).getLanguage();
    int i = -1;
    switch (paramContext.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return "";
        if (paramContext.equals("ru"))
        {
          i = 0;
          continue;
          if (paramContext.equals("uk"))
          {
            i = 1;
            continue;
            if (paramContext.equals("en"))
            {
              i = 2;
              continue;
              if (paramContext.equals("es")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    return "ОЧИСТИТЬ КЕШ";
    return "ОЧИСТИТИ КЕШ";
    return "CLEAR CACHE";
    return "BORRAR CACHÉ";
  }
  
  public static void j(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    ExecutorController.a().a(new Utils.2(paramContext));
  }
  
  public static Intent k(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
    paramContext.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
    paramContext.setData(Uri.parse("market://details?id=com.facebook.katana"));
    paramContext.setFlags(1350565888);
    return paramContext;
  }
  
  public static Intent l(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
    paramContext.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.MarketDeepLinkHandlerActivity"));
    paramContext.setData(Uri.parse("market://details?id=com.facebook.katana"));
    paramContext.setFlags(1350565888);
    return paramContext;
  }
  
  public static boolean m(Context paramContext)
  {
    String str = paramContext.getString(2131755040);
    paramContext = ((AccessibilityManager)paramContext.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1).iterator();
    while (paramContext.hasNext()) {
      if (str.equals(((AccessibilityServiceInfo)paramContext.next()).getId())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean n(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return Settings.canDrawOverlays(paramContext);
    }
    return true;
  }
  
  public static boolean o(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return t(paramContext);
    }
    return true;
  }
  
  public static boolean p(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName("com.facebook.katana", "com.facebook.katana.LoginActivity"));
    paramContext = localIntent.resolveActivityInfo(paramContext.getPackageManager(), localIntent.getFlags());
    return (paramContext != null) && (paramContext.exported);
  }
  
  public static void q(Context paramContext)
  {
    String str = PlatformHelper.e(paramContext);
    CRC32 localCRC32 = new CRC32();
    localCRC32.update(str.getBytes());
    if (Long.valueOf(localCRC32.getValue()).longValue() % 2L != 0L)
    {
      Logger.b("show_quiz");
      PreferenceUtils.e(paramContext, true);
      return;
    }
    Logger.b("not_show_quiz");
    PreferenceUtils.e(paramContext, false);
  }
  
  private static Set<String> s(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x81) <= 0) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName())) && (!BoosterService2.a.contains(localApplicationInfo.packageName))) {
        localHashSet.add(localApplicationInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  @TargetApi(21)
  private static boolean t(Context paramContext)
  {
    boolean bool = true;
    if (Build.VERSION.SDK_INT < 21) {
      return false;
    }
    int i = ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), paramContext.getPackageName());
    if (i == 3) {
      if (paramContext.checkCallingOrSelfPermission("android.permission.PACKAGE_USAGE_STATS") == 0) {
        bool = true;
      }
    }
    while (i == 0) {
      for (;;)
      {
        Log.e("Grant", String.valueOf(bool));
        return bool;
        bool = false;
      }
    }
    for (;;)
    {
      bool = false;
    }
  }
}
