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
import com.google.android.gms.ads.a.a.a;
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
      paramString = com.appnext.base.a.a.aN().aQ().af(paramString);
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        paramString = b(((com.appnext.base.a.b.b)paramString.get(0)).aZ(), paramA);
        return paramString;
      }
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
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
    }
    catch (Throwable paramDate)
    {
      g.c(paramDate);
    }
    return localStringBuilder.toString();
  }
  
  public static List<String> a(Context paramContext, long paramLong1, long paramLong2)
  {
    localArrayList = new ArrayList();
    if (paramContext == null) {
      return localArrayList;
    }
    try
    {
      Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
      int i = Build.VERSION.SDK_INT;
      Object localObject2;
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
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
            if (!h(paramContext, ((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName())) {
              localArrayList.add(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName());
            }
          }
          if (Build.VERSION.SDK_INT < 21) {
            break label311;
          }
        }
        catch (Throwable paramContext)
        {
          g.c(paramContext);
          return localArrayList;
        }
      }
      if (m(paramContext.getApplicationContext()))
      {
        localObject1 = (UsageStatsManager)paramContext.getSystemService("usagestats");
        long l = System.currentTimeMillis();
        localObject2 = ((UsageStatsManager)localObject1).queryUsageStats(4, l - paramLong1, l);
        if (localObject2 == null) {
          return localArrayList;
        }
        localObject2 = ((List)localObject2).listIterator();
        while (((ListIterator)localObject2).hasNext())
        {
          UsageStats localUsageStats = (UsageStats)((ListIterator)localObject2).next();
          if (Build.VERSION.SDK_INT >= 23) {
            if ((!((UsageStatsManager)localObject1).isAppInactive(localUsageStats.getPackageName())) && (localUsageStats.getTotalTimeInForeground() >= paramLong2) && (!h(paramContext, localUsageStats.getPackageName()))) {
              localArrayList.add(localUsageStats.getPackageName());
            } else {
              ((ListIterator)localObject2).remove();
            }
          }
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      g.c(paramContext);
    }
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
    try
    {
      int i = e.getContext().getPackageManager().queryIntentServices(new Intent(e.getContext(), paramClass), 65536).size();
      return i > 0;
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
    do
    {
      try
      {
        if ((paramString2.hashCode() != 570418373) || (!paramString2.equals("interval"))) {
          continue;
        }
        i = 0;
      }
      catch (Throwable paramString1)
      {
        boolean bool;
        g.c(paramString1);
        return false;
      }
      catch (InvocationTargetException paramString1)
      {
        paramString1.getCause().printStackTrace();
        g.c(paramString1.getCause());
        return false;
      }
      catch (ClassNotFoundException paramString1)
      {
        return false;
      }
      bool = ((com.appnext.base.operations.a)Class.forName(com.appnext.base.operations.c.al(paramString1)).getConstructor(new Class[] { com.appnext.base.a.b.c.class, Bundle.class }).newInstance(new Object[] { paramC, null })).bz();
      return bool;
    } while (i == 0);
    return false;
  }
  
  public static Object b(String paramString, d.a paramA)
  {
    try
    {
      switch (1.jN[paramA.ordinal()])
      {
      case 7: 
        return new JSONObject(paramString);
      }
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return new JSONArray(paramString);
    return new HashSet(Arrays.asList(paramString.split(",")));
    return Boolean.valueOf(paramString);
    return Long.valueOf(paramString);
    return Double.valueOf(paramString);
    paramString = Integer.valueOf(paramString);
    return paramString;
    return null;
    return paramString;
  }
  
  public static void b(String paramString1, String paramString2, d.a paramA)
  {
    com.appnext.base.a.a.aN().aQ().b(new com.appnext.base.a.b.b(paramString1, paramString2, paramA.getType()));
  }
  
  public static boolean b(String paramString, Map<String, String> paramMap)
  {
    Object localObject1 = com.appnext.base.a.a.aN().aR().ad(paramString);
    if (localObject1 != null)
    {
      if ("off".equalsIgnoreCase(((com.appnext.base.a.b.c)localObject1).bb())) {
        return true;
      }
      if (paramMap.isEmpty()) {
        return true;
      }
      localObject1 = new StringBuilder("http://apis.appnxt.net:443");
      ((StringBuilder)localObject1).append("/data");
      String str = ((StringBuilder)localObject1).toString();
      HashMap localHashMap = new HashMap();
      Object localObject3 = g.t(e.getContext());
      localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3)) {
        localObject1 = i.ck().getString("google_ads_id", "");
      }
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        return false;
      }
      localHashMap.put("aid", localObject1);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject1);
      ((StringBuilder)localObject3).append("_");
      ((StringBuilder)localObject3).append(System.currentTimeMillis());
      localHashMap.put("cuid", ((StringBuilder)localObject3).toString());
      localHashMap.put("lvid", "4.7.2");
      try
      {
        localHashMap.put("localdate", a(new Date()));
        localHashMap.put("timezone", cu());
        localHashMap.put("app_package", e.getPackageName());
      }
      catch (Throwable localThrowable)
      {
        g.c(localThrowable);
        localHashMap.put("app_package", "");
      }
      paramMap = paramMap.entrySet().iterator();
      Object localObject2;
      while (paramMap.hasNext())
      {
        localObject2 = (Map.Entry)paramMap.next();
        localHashMap.put((String)((Map.Entry)localObject2).getKey(), (String)((Map.Entry)localObject2).getValue());
      }
      paramMap = new StringBuilder("-------Sending to server data for key = ");
      paramMap.append(paramString);
      paramMap.append(" ----------");
      paramString = localHashMap.entrySet().iterator();
      while (paramString.hasNext())
      {
        paramMap = (Map.Entry)paramString.next();
        localObject2 = new StringBuilder("---- ");
        ((StringBuilder)localObject2).append((String)paramMap.getKey());
        ((StringBuilder)localObject2).append(" : ");
        ((StringBuilder)localObject2).append((String)paramMap.getValue());
        ((StringBuilder)localObject2).append(" ----");
      }
      try
      {
        paramString = g.a(str, localHashMap, false, 15000, d.a.HashMap);
        if (paramString != null)
        {
          paramString = new String(paramString, "UTF-8");
          new StringBuilder("result send data: ").append(paramString);
        }
        return true;
      }
      catch (Throwable paramString)
      {
        paramString = paramString.getMessage();
        new StringBuilder("(Type:Throwable) ").append(paramString);
        return false;
      }
      catch (HttpRetryException paramString)
      {
        int i = paramString.responseCode();
        paramString = paramString.getMessage();
        paramMap = new StringBuilder("(Type:HttpRetryException)");
        paramMap.append(paramString);
        paramMap.append("  ");
        paramMap.append(i);
        return false;
      }
    }
    return true;
  }
  
  public static String cu()
  {
    StringBuilder localStringBuilder = new StringBuilder(9);
    try
    {
      Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
      int j = (localCalendar.get(15) + localCalendar.get(16)) / 60000;
      char c = '+';
      int i = j;
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
      g.c(localThrowable);
    }
    return localStringBuilder.toString();
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
  
  public static int i(String paramString1, String paramString2)
  {
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (TextUtils.isDigitsOnly(paramString1)))
      {
        if (TextUtils.isEmpty(paramString2)) {
          return -1;
        }
        int i = Integer.valueOf(paramString1).intValue();
        if ("second".equalsIgnoreCase(paramString2)) {
          return i;
        }
        if ("minute".equalsIgnoreCase(paramString2)) {
          return (int)(i * 60000L);
        }
        if ("hour".equalsIgnoreCase(paramString2)) {
          return (int)(i * 3600000L);
        }
        boolean bool = "day".equalsIgnoreCase(paramString2);
        if (bool) {
          return (int)(i * 86400000L);
        }
        return -1;
      }
      return -1;
    }
    catch (Throwable paramString1)
    {
      g.c(paramString1);
    }
    return -1;
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
        localObject = new StringBuilder("cdm");
        ((StringBuilder)localObject).append(System.currentTimeMillis());
        localObject = new com.appnext.base.a.b.c("on", "1", "hour", "1", "interval", "cdm", ((StringBuilder)localObject).toString(), null);
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
    paramContext = com.google.android.gms.ads.a.a.getAdvertisingIdInfo(paramContext);
    return (paramContext != null) && (paramContext.isLimitAdTrackingEnabled());
  }
  
  public static boolean p(Context paramContext)
  {
    try
    {
      paramContext = com.google.android.gms.ads.a.a.getAdvertisingIdInfo(paramContext);
      if (paramContext != null)
      {
        boolean bool = paramContext.isLimitAdTrackingEnabled();
        return bool;
      }
      return true;
    }
    catch (Throwable paramContext)
    {
      g.c(paramContext);
      paramContext.toString();
    }
    return true;
  }
}
