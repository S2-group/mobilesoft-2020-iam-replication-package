package com.appnext.base.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.appnext.core.g;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpRetryException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k
{
  private static final String TAG = "SdkHelper";
  private static final long iv = 1000L;
  private static final long iw = 60000L;
  private static final long ix = 3600000L;
  private static final long iy = 86400000L;
  
  public k() {}
  
  public static Object a(String paramString, d.a paramA)
  {
    try
    {
      paramString = com.appnext.base.a.a.aN().aQ().ae(paramString);
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        paramString = b(((com.appnext.base.a.b.b)paramString.get(0)).aZ(), paramA);
        return paramString;
      }
    }
    catch (Throwable paramString) {}
    return null;
  }
  
  public static String a(Date paramDate)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(paramDate));
      localStringBuilder.append(" ");
      localStringBuilder.append(cu());
      localStringBuilder.append(" ");
      localStringBuilder.append(new SimpleDateFormat("yyyy", Locale.US).format(paramDate));
      return localStringBuilder.toString();
    }
    catch (Throwable paramDate)
    {
      for (;;)
      {
        g.c(paramDate);
      }
    }
  }
  
  public static List<String> a(Context paramContext, long paramLong1, long paramLong2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext == null) {
      return localArrayList;
    }
    for (;;)
    {
      try
      {
        localObject1 = (ActivityManager)paramContext.getSystemService("activity");
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
          try
          {
            if (!f.g(e.getContext(), "android.permission.GET_TASKS")) {
              break label311;
            }
            localObject1 = ((ActivityManager)localObject1).getRunningTasks(20);
            if ((localObject1 == null) || (((List)localObject1).isEmpty())) {
              break label311;
            }
            localObject1 = ((List)localObject1).iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break label311;
            }
            localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
            if (h(paramContext, ((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName())) {
              continue;
            }
            localArrayList.add(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName());
            continue;
            if (Build.VERSION.SDK_INT < 21) {
              break label311;
            }
          }
          catch (Throwable paramContext)
          {
            g.c(paramContext);
          }
        }
        if (!m(paramContext.getApplicationContext())) {
          break label311;
        }
        localObject1 = (UsageStatsManager)paramContext.getSystemService("usagestats");
        long l = System.currentTimeMillis();
        localObject2 = ((UsageStatsManager)localObject1).queryUsageStats(4, l - paramLong1, l);
        if (localObject2 == null) {
          return localArrayList;
        }
        localObject2 = ((List)localObject2).listIterator();
      }
      catch (Throwable paramContext)
      {
        Object localObject1;
        Object localObject2;
        UsageStats localUsageStats;
        g.c(paramContext);
        break label311;
        localArrayList.add(localUsageStats.getPackageName());
        continue;
      }
      if (!((ListIterator)localObject2).hasNext()) {
        break label311;
      }
      localUsageStats = (UsageStats)((ListIterator)localObject2).next();
      if (Build.VERSION.SDK_INT >= 23)
      {
        if ((!((UsageStatsManager)localObject1).isAppInactive(localUsageStats.getPackageName())) && (localUsageStats.getTotalTimeInForeground() >= paramLong2) && (!h(paramContext, localUsageStats.getPackageName()))) {
          continue;
        }
        ((ListIterator)localObject2).remove();
      }
    }
    label311:
    return localArrayList;
  }
  
  public static List<ApplicationInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    localArrayList = new ArrayList();
    try
    {
      paramPackageManager = paramPackageManager.getInstalledApplications(128).iterator();
      while (paramPackageManager.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramPackageManager.next();
        if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
          localArrayList.add(localApplicationInfo);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramPackageManager)
    {
      g.c(paramPackageManager);
    }
  }
  
  private static void a(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    try
    {
      String str = Integer.toString(paramInt2);
      paramInt1 = 0;
      while (paramInt1 < 2 - str.length())
      {
        paramStringBuilder.append('0');
        paramInt1 += 1;
      }
      paramStringBuilder.append(str);
      return;
    }
    catch (Throwable paramStringBuilder)
    {
      g.c(paramStringBuilder);
    }
  }
  
  public static boolean a(Class paramClass)
  {
    boolean bool = false;
    try
    {
      int i = e.getContext().getPackageManager().queryIntentServices(new Intent(e.getContext(), paramClass), 65536).size();
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Throwable paramClass)
    {
      g.c(paramClass);
    }
    return false;
  }
  
  public static boolean a(String paramString1, String paramString2, com.appnext.base.a.b.c paramC)
  {
    int i = -1;
    try
    {
      switch (paramString2.hashCode())
      {
      case 570418373: 
        if (!paramString2.equals("interval")) {
          break label118;
        }
        i = 0;
      }
    }
    catch (InvocationTargetException paramString1)
    {
      boolean bool;
      paramString1.getCause().printStackTrace();
      g.c(paramString1.getCause());
      return false;
    }
    catch (ClassNotFoundException paramString1)
    {
      return false;
    }
    catch (Throwable paramString1)
    {
      g.c(paramString1);
      return false;
    }
    bool = ((com.appnext.base.operations.a)Class.forName(com.appnext.base.operations.c.ak(paramString1)).getConstructor(new Class[] { com.appnext.base.a.b.c.class, Bundle.class }).newInstance(new Object[] { paramC, null })).bz();
    return bool;
    label118:
    switch (i)
    {
    }
    return false;
  }
  
  public static Object b(String paramString, d.a paramA)
  {
    try
    {
      switch (1.jN[paramA.ordinal()])
      {
      case 1: 
        return Integer.valueOf(paramString);
      }
    }
    catch (Throwable paramString)
    {
      return null;
    }
    return Double.valueOf(paramString);
    return Long.valueOf(paramString);
    return Boolean.valueOf(paramString);
    return new HashSet(Arrays.asList(paramString.split(",")));
    return new JSONArray(paramString);
    paramString = new JSONObject(paramString);
    return paramString;
    return paramString;
  }
  
  public static void b(String paramString1, String paramString2, d.a paramA)
  {
    com.appnext.base.a.a.aN().aQ().b(new com.appnext.base.a.b.b(paramString1, paramString2, paramA.getType()));
  }
  
  public static boolean b(String paramString, Map<String, String> paramMap)
  {
    Object localObject = com.appnext.base.a.a.aN().aR().ac(paramString);
    if ((localObject == null) || ("off".equalsIgnoreCase(((com.appnext.base.a.b.c)localObject).bb()))) {
      return true;
    }
    if (paramMap.isEmpty()) {
      return true;
    }
    String str2 = "http://apis.appnxt.net:443" + "/data";
    HashMap localHashMap = new HashMap();
    String str1 = g.u(e.getContext());
    localObject = str1;
    if (TextUtils.isEmpty(str1)) {
      localObject = i.ck().getString("google_ads_id", "");
    }
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return false;
    }
    localHashMap.put("aid", localObject);
    localHashMap.put("cuid", (String)localObject + "_" + System.currentTimeMillis());
    localHashMap.put("lvid", "4.7.2");
    try
    {
      localHashMap.put("localdate", a(new Date()));
      localHashMap.put("timezone", cu());
      localHashMap.put("app_package", e.getPackageName());
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        localObject = (Map.Entry)paramMap.next();
        localHashMap.put((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue());
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        g.c(localThrowable);
        localHashMap.put("app_package", "");
      }
      new StringBuilder("-------Sending to server data for key = ").append(paramString).append(" ----------");
      paramString = localHashMap.entrySet().iterator();
      while (paramString.hasNext())
      {
        paramMap = (Map.Entry)paramString.next();
        new StringBuilder("---- ").append((String)paramMap.getKey()).append(" : ").append((String)paramMap.getValue()).append(" ----");
      }
      try
      {
        paramString = g.a(str2, localHashMap, false, 15000, d.a.HashMap);
        if (paramString != null)
        {
          paramString = new String(paramString, "UTF-8");
          new StringBuilder("result send data: ").append(paramString);
        }
        return true;
      }
      catch (HttpRetryException paramString)
      {
        int i = paramString.responseCode();
        paramString = paramString.getMessage();
        new StringBuilder("(Type:HttpRetryException)").append(paramString).append("  ").append(i);
        return false;
      }
      catch (Throwable paramString)
      {
        paramString = paramString.getMessage();
        new StringBuilder("(Type:Throwable) ").append(paramString);
      }
    }
    return false;
  }
  
  public static String cu()
  {
    StringBuilder localStringBuilder = new StringBuilder(9);
    try
    {
      Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
      int i = localCalendar.get(15);
      int j = (localCalendar.get(16) + i) / 60000;
      char c = '+';
      i = j;
      if (j < 0)
      {
        c = '-';
        i = -j;
      }
      localStringBuilder.append("GMT");
      localStringBuilder.append(c);
      a(localStringBuilder, 2, i / 60);
      localStringBuilder.append(':');
      a(localStringBuilder, 2, i % 60);
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        g.c(localThrowable);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static int h(String paramString1, String paramString2)
  {
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (TextUtils.isDigitsOnly(paramString1)) && (!TextUtils.isEmpty(paramString2)))
      {
        int j = Integer.valueOf(paramString1).intValue();
        i = j;
        if ("second".equalsIgnoreCase(paramString2)) {
          return i;
        }
        if ("minute".equalsIgnoreCase(paramString2)) {
          return (int)(j * 60000L);
        }
        if ("hour".equalsIgnoreCase(paramString2)) {
          return (int)(j * 3600000L);
        }
        boolean bool = "day".equalsIgnoreCase(paramString2);
        if (bool) {
          return (int)(j * 86400000L);
        }
        return -1;
      }
    }
    catch (Throwable paramString1)
    {
      g.c(paramString1);
      return -1;
    }
    int i = -1;
    return i;
  }
  
  private static boolean h(Context paramContext, String paramString)
  {
    try
    {
      if (paramString.contains("com.android")) {
        return true;
      }
      Object localObject1 = paramContext.getPackageManager();
      Object localObject2 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject2).addCategory("android.intent.category.HOME");
      localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 65536);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ResolveInfo)((Iterator)localObject1).next();
          if ((((ResolveInfo)localObject2).activityInfo != null) && (((ResolveInfo)localObject2).activityInfo.packageName.equals(paramString))) {
            return true;
          }
        }
      }
      localObject1 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
      paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          localObject1 = (ResolveInfo)paramContext.next();
          if ((((ResolveInfo)localObject1).activityInfo != null) && (((ResolveInfo)localObject1).activityInfo.packageName.equals(paramString)))
          {
            int i = ((ResolveInfo)localObject1).activityInfo.flags;
            return (i & 0x81) != 0;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      g.c(paramContext);
    }
    return false;
  }
  
  @TargetApi(21)
  public static boolean m(Context paramContext)
  {
    return ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), paramContext.getPackageName()) == 0;
  }
  
  public static void n(Context paramContext)
  {
    try
    {
      Object localObject = com.appnext.base.a.a.aN().aR().bm();
      if ((localObject != null) && (((List)localObject).size() == 0))
      {
        localObject = new com.appnext.base.a.b.c("on", "1", "hour", "1", "interval", "cdm", "cdm" + System.currentTimeMillis(), null);
        com.appnext.base.a.a.aN().aR().a((com.appnext.base.a.b.c)localObject);
        com.appnext.base.services.b.a.g(paramContext).a((com.appnext.base.a.b.c)localObject, true);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      g.c(paramContext);
    }
  }
  
  public static boolean o(Context paramContext)
    throws Exception
  {
    paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
    return (paramContext != null) && (paramContext.isLimitAdTrackingEnabled());
  }
  
  public static boolean p(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      boolean bool;
      if (paramContext != null) {
        bool = paramContext.isLimitAdTrackingEnabled();
      }
      return bool;
    }
    catch (Throwable paramContext)
    {
      g.c(paramContext);
      paramContext.toString();
    }
    return true;
  }
}
