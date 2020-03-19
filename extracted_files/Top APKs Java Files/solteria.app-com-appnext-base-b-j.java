package com.appnext.base.b;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Process;
import android.text.TextUtils;
import com.appnext.base.b;
import com.appnext.base.operations.impl.cdm;
import com.appnext.base.operations.impl.rcd;
import com.appnext.base.services.OperationService;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpRetryException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import org.json.JSONObject;

public class j
{
  private static final String TAG = "SdkHelper";
  public static final int gv = 120000;
  private static final long gw = 1000L;
  private static final long gx = 60000L;
  private static final long gy = 3600000L;
  private static final long gz = 86400000L;
  private static Random iF = new Random();
  
  public j() {}
  
  public static long a(int paramInt1, int paramInt2)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(11, paramInt1);
    localCalendar.set(12, paramInt2);
    localCalendar.set(13, 0);
    if (new Date().after(localCalendar.getTime())) {
      localCalendar.add(5, 1);
    }
    return localCalendar.getTimeInMillis();
  }
  
  public static long a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return a(paramInt1, paramInt2) + (iF.nextInt(paramInt3 + paramInt4) - paramInt3) * 1000L;
  }
  
  public static Location a(double paramDouble1, double paramDouble2, float paramFloat)
  {
    Location localLocation = new Location("");
    localLocation.setLatitude(paramDouble1);
    localLocation.setLongitude(paramDouble2);
    localLocation.setAccuracy(paramFloat);
    return localLocation;
  }
  
  public static String a(Date paramDate)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(paramDate));
    localStringBuilder.append(" ");
    localStringBuilder.append(bQ());
    localStringBuilder.append(" ");
    localStringBuilder.append(new SimpleDateFormat("yyyy", Locale.US).format(paramDate));
    return localStringBuilder.toString();
  }
  
  public static List<ApplicationInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    paramPackageManager = paramPackageManager.getInstalledApplications(128).iterator();
    while (paramPackageManager.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramPackageManager.next();
      if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == paramInt)) {
        localArrayList.add(localApplicationInfo);
      }
    }
    return localArrayList;
  }
  
  public static SortedMap<Double, String> a(Context paramContext, Double paramDouble)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    paramContext = paramContext.getPackageManager();
    TreeMap localTreeMap = new TreeMap(Collections.reverseOrder());
    Object localObject = localActivityManager.getRunningAppProcesses();
    if ((localObject == null) || (((List)localObject).isEmpty())) {
      return localTreeMap;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      try
      {
        ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo(localRunningAppProcessInfo.processName, 0);
        if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
          localTreeMap.put(Double.valueOf(Math.round(localActivityManager.getProcessMemoryInfo(new int[] { localRunningAppProcessInfo.pid })[0].getTotalPss() / paramDouble.doubleValue() * 100.0D * 100.0D) / 100.0D), localApplicationInfo.packageName);
        }
      }
      catch (Throwable localThrowable) {}
    }
    return localTreeMap;
  }
  
  public static void a(Context paramContext, Class paramClass, long paramLong, com.appnext.base.a.b.c paramC)
  {
    if ((paramContext == null) || (paramC == null) || (paramClass == null)) {}
    long l;
    AlarmManager localAlarmManager;
    for (;;)
    {
      return;
      if ("time".equalsIgnoreCase(paramC.aT())) {}
      for (l = 86400000L; l != -1L; l = d(paramC.aS(), paramC.aT()))
      {
        localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
        paramClass = new Intent(paramContext, paramClass);
        paramClass.putExtra("config_data_obj", paramC.getKey());
        paramContext = PendingIntent.getService(paramContext, paramC.getKey().hashCode(), paramClass, 134217728);
        if (!paramC.aW()) {
          break label162;
        }
        if (Build.VERSION.SDK_INT < 23) {
          break label136;
        }
        localAlarmManager.setExactAndAllowWhileIdle(0, paramLong, paramContext);
        return;
      }
    }
    label136:
    if (Build.VERSION.SDK_INT >= 19)
    {
      localAlarmManager.setExact(0, paramLong, paramContext);
      return;
    }
    localAlarmManager.set(0, paramLong, paramContext);
    return;
    label162:
    localAlarmManager.setInexactRepeating(1, paramLong, l, paramContext);
  }
  
  private static void a(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt2);
    paramInt2 = 0;
    while (paramInt2 < paramInt1 - str.length())
    {
      paramStringBuilder.append('0');
      paramInt2 += 1;
    }
    paramStringBuilder.append(str);
  }
  
  public static String ar(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = paramString + "_hash";
    return h.bG().getString(paramString, null);
  }
  
  protected static long bP()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      long l = Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue();
      localBufferedReader.close();
      return l;
    }
    catch (IOException localIOException)
    {
      b.a(localIOException);
    }
    return -1L;
  }
  
  public static String bQ()
  {
    Object localObject = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
    int i = ((Calendar)localObject).get(15);
    int j = (((Calendar)localObject).get(16) + i) / 60000;
    char c = '+';
    i = j;
    if (j < 0)
    {
      c = '-';
      i = -j;
    }
    localObject = new StringBuilder(9);
    ((StringBuilder)localObject).append("GMT");
    ((StringBuilder)localObject).append(c);
    a((StringBuilder)localObject, 2, i / 60);
    ((StringBuilder)localObject).append(':');
    a((StringBuilder)localObject, 2, i % 60);
    return ((StringBuilder)localObject).toString();
  }
  
  public static void c(String paramString1, String paramString2, c.a paramA)
  {
    Intent localIntent = new Intent(d.getContext(), OperationService.class);
    localIntent.putExtra("config_data_obj", paramString1);
    localIntent.putExtra("data", paramString2);
    localIntent.putExtra("type", paramA);
    localIntent.putExtra("action", rcd.class.getSimpleName());
    d.getContext().startService(localIntent);
  }
  
  public static long d(String paramString1, String paramString2)
  {
    long l1 = -1L;
    try
    {
      if ((TextUtils.isDigitsOnly(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (!TextUtils.isEmpty(paramString1)))
      {
        long l2 = Long.parseLong(paramString1);
        if ("time".equalsIgnoreCase(paramString2))
        {
          if (paramString1.length() != 4) {
            return l1;
          }
          return a(Integer.parseInt(paramString1.substring(0, 2)), Integer.parseInt(paramString1.substring(2, 4)), 1800, 1800);
        }
        if ("second".equalsIgnoreCase(paramString2)) {
          return 1000L * l2;
        }
        if ("minute".equalsIgnoreCase(paramString2)) {
          return 60000L * l2;
        }
        if ("hour".equalsIgnoreCase(paramString2)) {
          return 3600000L * l2;
        }
        boolean bool = "day".equalsIgnoreCase(paramString2);
        if (!bool) {
          return l1;
        }
        return 86400000L * l2;
      }
    }
    catch (Throwable paramString1)
    {
      b.a(paramString1);
      return -1L;
    }
    l1 = 0L;
    return l1;
  }
  
  public static void e(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {}
    Object localObject;
    String str3;
    HashMap localHashMap;
    do
    {
      do
      {
        return;
        localObject = e.bB().aj(paramString1);
      } while ((localObject == null) || ("off".equalsIgnoreCase(((com.appnext.base.a.b.c)localObject).aR())));
      str3 = c.by() + "/data";
      localHashMap = new HashMap();
      String str2 = com.appnext.core.f.w(d.getContext());
      localObject = str2;
      if (TextUtils.isEmpty(str2)) {
        localObject = h.bG().getString("google_ads_id", "");
      }
    } while (TextUtils.isEmpty((CharSequence)localObject));
    localHashMap.put("aid", localObject);
    localHashMap.put("cuid", (String)localObject + "_" + System.currentTimeMillis());
    localHashMap.put("lvid", "4.5.9");
    try
    {
      localHashMap.put("localdate", a(new Date()));
      localHashMap.put("timezone", bQ());
      try
      {
        localHashMap.put("app_package", d.getPackageName());
        localHashMap.put(paramString1, paramString2);
        k.g(paramString1, "-------Sending to server data for key = " + paramString1 + " ----------");
        paramString2 = localHashMap.entrySet().iterator();
        while (paramString2.hasNext())
        {
          localObject = (Map.Entry)paramString2.next();
          k.g(paramString1, "---- " + (String)((Map.Entry)localObject).getKey() + " : " + (String)((Map.Entry)localObject).getValue() + " ----");
        }
      }
      catch (Throwable localThrowable1)
      {
        for (;;)
        {
          b.a(localThrowable1);
          localHashMap.put("app_package", "");
        }
        try
        {
          paramString2 = com.appnext.core.f.a(str3, localHashMap, false, 15000);
          k.g(paramString1, "result send data: " + paramString2);
          return;
        }
        catch (HttpRetryException paramString2)
        {
          int i = paramString2.responseCode();
          String str1 = paramString2.getMessage();
          if ((i == 400) || (i == 401) || (i == 402) || (i == 403) || (i == 404) || (i == 405) || (i == 500) || (i == 501) || (i == 503)) {
            b.a(paramString2);
          }
          k.j(paramString1, "server error (Type:HttpRetryException)" + str1 + "  " + i);
          return;
        }
        catch (Throwable paramString2)
        {
          paramString2 = paramString2.getMessage();
          k.j(paramString1, "server error (Type:Exception) " + paramString2);
          return;
        }
      }
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
    }
  }
  
  public static void f(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return;
    }
    paramString1 = paramString1 + "_hash";
    h.bG().putString(paramString1, paramString2);
  }
  
  public static SortedMap<Double, String> j(Context paramContext)
  {
    TreeMap localTreeMap = new TreeMap(Collections.reverseOrder());
    List localList = l(paramContext);
    int i = 0;
    while (i < localList.size())
    {
      try
      {
        Object localObject = paramContext.getPackageManager().getPackageInfo((String)localList.get(i), 0);
        if (localObject != null)
        {
          localObject = ((PackageInfo)localObject).applicationInfo;
          if ((localObject != null) && ((((ApplicationInfo)localObject).flags & 0x1) == 0)) {
            localTreeMap.put(Double.valueOf(i + 1), ((ApplicationInfo)localObject).packageName);
          }
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          b.a(localThrowable);
        }
      }
      i += 1;
    }
    return localTreeMap;
  }
  
  public static double k(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (Build.VERSION.SDK_INT >= 16)
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      paramContext.getMemoryInfo(localMemoryInfo);
      return localMemoryInfo.totalMem / 1024.0D;
    }
    return bP();
  }
  
  @SuppressLint({"all"})
  private static List<String> l(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (n(paramContext.getApplicationContext()))
    {
      paramContext = (UsageStatsManager)paramContext.getSystemService("usagestats");
      long l = System.currentTimeMillis();
      Object localObject = paramContext.queryUsageStats(4, l - 300000L, l);
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          UsageStats localUsageStats = (UsageStats)((Iterator)localObject).next();
          if (((Build.VERSION.SDK_INT <= 23) || (!paramContext.isAppInactive(localUsageStats.getPackageName()))) && (localUsageStats.getLastTimeUsed() >= l - 300000L)) {
            localArrayList.add(localUsageStats.getPackageName());
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static List<String> m(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext == null) {
      return localArrayList;
    }
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    if (Build.VERSION.SDK_INT < 21) {}
    for (;;)
    {
      try
      {
        if (f.b(d.getContext(), "android.permission.GET_TASKS"))
        {
          paramContext = ((ActivityManager)localObject).getRunningTasks(3);
          if ((paramContext != null) && (!paramContext.isEmpty())) {
            localArrayList.add(((ActivityManager.RunningTaskInfo)paramContext.get(0)).baseActivity.getPackageName());
          }
        }
      }
      catch (Throwable paramContext)
      {
        b.a(paramContext);
        continue;
      }
      return localArrayList;
      if ((Build.VERSION.SDK_INT >= 21) && (n(paramContext.getApplicationContext())))
      {
        paramContext = (UsageStatsManager)paramContext.getSystemService("usagestats");
        long l = System.currentTimeMillis();
        localObject = paramContext.queryUsageStats(4, l - 60000L, l);
        if (localObject == null) {
          return localArrayList;
        }
        int i = ((List)localObject).size() - 1;
        while (i >= 0)
        {
          if ((((UsageStats)((List)localObject).get(i)).getLastTimeUsed() < l - 120000L) || (((UsageStats)((List)localObject).get(i)).getTotalTimeInForeground() == 0L)) {
            ((List)localObject).remove(i);
          }
          i -= 1;
        }
        if (((List)localObject).size() > 0)
        {
          paramContext = new TreeMap();
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            UsageStats localUsageStats = (UsageStats)((Iterator)localObject).next();
            paramContext.put(Long.valueOf(localUsageStats.getLastTimeUsed()), localUsageStats);
          }
          if (!paramContext.isEmpty()) {
            localArrayList.add(((UsageStats)paramContext.get(paramContext.lastKey())).getPackageName());
          }
        }
      }
    }
  }
  
  @TargetApi(21)
  public static boolean n(Context paramContext)
  {
    return ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), paramContext.getPackageName()) == 0;
  }
  
  public static void o(Context paramContext)
  {
    try
    {
      Object localObject = com.appnext.base.a.a.aE().aJ().bb();
      if ((localObject != null) && (((List)localObject).size() == 0))
      {
        localObject = new JSONObject("{ \"status\": \"on\", \"sample\": \"1\", \"sample_type\": \"hour\", \"cycle\": \"1\", \"cycle_type\": \"interval\", \"exact\": \"false\", \"key\": \"cdm\" }");
        com.appnext.base.a.a.aE().aJ().a((JSONObject)localObject);
        localObject = new Intent(paramContext, OperationService.class);
        ((Intent)localObject).putExtra("config_data_obj", cdm.class.getSimpleName());
        paramContext.startService((Intent)localObject);
        return;
      }
      paramContext = com.appnext.base.a.a.aE().aJ().bb();
      com.appnext.base.a.aA().a(paramContext);
      com.appnext.base.a.aA().aB();
      return;
    }
    catch (Throwable paramContext)
    {
      b.a(paramContext);
    }
  }
  
  public static boolean p(Context paramContext)
    throws Exception
  {
    paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
    return (paramContext != null) && (paramContext.isLimitAdTrackingEnabled());
  }
  
  public static boolean q(Context paramContext)
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
      b.a(paramContext);
      k.j("SdkHelper", paramContext.toString());
    }
    return true;
  }
}
