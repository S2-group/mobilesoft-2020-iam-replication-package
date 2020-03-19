package com.appnext.base.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
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
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.appnext.base.b;
import com.appnext.base.operations.imp.cdm;
import com.appnext.base.operations.imp.rcd;
import com.appnext.base.services.OperationService;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.net.HttpRetryException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
  private static final long hA = 1000L;
  private static final long hB = 60000L;
  private static final long hC = 3600000L;
  private static final long hD = 86400000L;
  private static Random hE = new Random();
  public static final int hz = 120000;
  
  public j() {}
  
  public static long a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return b(paramInt1, paramInt2) + (hE.nextInt(paramInt3 + paramInt4) - paramInt3) * 1000L;
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
    localStringBuilder.append(cd());
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
  
  public static void a(Context paramContext, Class paramClass, long paramLong, com.appnext.base.a.b.c paramC)
  {
    if ((paramContext == null) || (paramC == null) || (paramClass == null)) {}
    long l;
    AlarmManager localAlarmManager;
    for (;;)
    {
      return;
      if ("time".equalsIgnoreCase(paramC.aV())) {}
      for (l = 86400000L; l != -1L; l = d(paramC.aU(), paramC.aV()))
      {
        localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
        paramClass = new Intent(paramContext, paramClass);
        paramClass.putExtra("config_data_obj", paramC.getKey());
        paramContext = PendingIntent.getService(paramContext, paramC.getKey().hashCode(), paramClass, 134217728);
        if (!paramC.aY()) {
          break label159;
        }
        if (Build.VERSION.SDK_INT < 23) {
          break label133;
        }
        localAlarmManager.setExactAndAllowWhileIdle(0, paramLong, paramContext);
        return;
      }
    }
    label133:
    if (Build.VERSION.SDK_INT >= 19)
    {
      localAlarmManager.setExact(0, paramLong, paramContext);
      return;
    }
    localAlarmManager.set(0, paramLong, paramContext);
    return;
    label159:
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
  
  public static long aO(String paramString)
  {
    return h.bT().getLong(paramString + "_lastupdate", 0L);
  }
  
  public static String ay(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = paramString + "_hash";
    return h.bT().getString(paramString, null);
  }
  
  public static long b(int paramInt1, int paramInt2)
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
  
  public static void c(String paramString1, String paramString2, c.a paramA)
  {
    Intent localIntent = new Intent(d.getContext(), OperationService.class);
    localIntent.putExtra("config_data_obj", paramString1);
    localIntent.putExtra("data", paramString2);
    localIntent.putExtra("type", paramA);
    localIntent.putExtra("action", rcd.class.getSimpleName());
    d.getContext().startService(localIntent);
  }
  
  public static boolean c(Class paramClass)
  {
    boolean bool = false;
    try
    {
      int i = d.getContext().getPackageManager().queryIntentServices(new Intent(d.getContext(), paramClass), 65536).size();
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Throwable paramClass)
    {
      com.appnext.core.f.c(paramClass);
    }
    return false;
  }
  
  public static void cc()
  {
    Object localObject = (ActivityManager)d.getContext().getSystemService("activity");
    d.getContext().getPackageManager();
    new TreeMap(Collections.reverseOrder());
    localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        Log.e("Running app", "Name : - " + localRunningAppProcessInfo.processName + " Importance - " + localRunningAppProcessInfo.importance + " Lru - " + localRunningAppProcessInfo.lru + " LastTrimLevel - " + localRunningAppProcessInfo.lastTrimLevel + " ImportanceReasonCode - " + localRunningAppProcessInfo.importanceReasonCode + " importanceReasonComponent - " + localRunningAppProcessInfo.importanceReasonComponent);
      }
    }
  }
  
  public static String cd()
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
        localObject = e.bO().aq(paramString1);
      } while ((localObject == null) || ("off".equalsIgnoreCase(((com.appnext.base.a.b.c)localObject).aT())));
      str3 = c.bL() + "/data";
      localHashMap = new HashMap();
      String str2 = com.appnext.core.f.u(d.getContext());
      localObject = str2;
      if (TextUtils.isEmpty(str2)) {
        localObject = h.bT().getString("google_ads_id", "");
      }
    } while (TextUtils.isEmpty((CharSequence)localObject));
    localHashMap.put("aid", localObject);
    localHashMap.put("cuid", (String)localObject + "_" + System.currentTimeMillis());
    localHashMap.put("lvid", "4.6.2");
    try
    {
      localHashMap.put("localdate", a(new Date()));
      localHashMap.put("timezone", cd());
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
    h.bT().putString(paramString1, paramString2);
  }
  
  public static List<String> k(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (paramContext == null) {
      return localArrayList;
    }
    for (;;)
    {
      try
      {
        localObject1 = (ActivityManager)paramContext.getSystemService("activity");
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
          continue;
        }
      }
      catch (Throwable paramContext)
      {
        b.a(paramContext);
        continue;
        if ((Build.VERSION.SDK_INT < 21) || (!l(paramContext.getApplicationContext()))) {
          continue;
        }
        Object localObject1 = (UsageStatsManager)paramContext.getSystemService("usagestats");
        long l = System.currentTimeMillis();
        paramContext = ((UsageStatsManager)localObject1).queryUsageStats(4, l - 300000L, l);
        if (paramContext != null) {
          continue;
        }
        return localArrayList;
        Object localObject2 = paramContext.listIterator();
        if (!((ListIterator)localObject2).hasNext()) {
          continue;
        }
        UsageStats localUsageStats = (UsageStats)((ListIterator)localObject2).next();
        if ((Build.VERSION.SDK_INT < 23) || (!((UsageStatsManager)localObject1).isAppInactive(localUsageStats.getPackageName()))) {
          continue;
        }
        ((ListIterator)localObject2).remove();
        continue;
        if (paramContext.size() <= 0) {
          continue;
        }
        localObject1 = new TreeMap();
        paramContext = paramContext.iterator();
        if (!paramContext.hasNext()) {
          continue;
        }
        localObject2 = (UsageStats)paramContext.next();
        ((SortedMap)localObject1).put(Long.valueOf(((UsageStats)localObject2).getLastTimeUsed()), localObject2);
        continue;
        if (((SortedMap)localObject1).isEmpty()) {
          continue;
        }
        localArrayList.add(((UsageStats)((SortedMap)localObject1).get(((SortedMap)localObject1).lastKey())).getPackageName());
        continue;
      }
      try
      {
        if (f.b(d.getContext(), "android.permission.GET_TASKS"))
        {
          paramContext = ((ActivityManager)localObject1).getRunningTasks(3);
          if ((paramContext != null) && (!paramContext.isEmpty())) {
            localArrayList.add(((ActivityManager.RunningTaskInfo)paramContext.get(0)).baseActivity.getPackageName());
          }
        }
      }
      catch (Throwable paramContext)
      {
        b.a(paramContext);
      }
    }
    return localArrayList;
  }
  
  @TargetApi(21)
  public static boolean l(Context paramContext)
  {
    return ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), paramContext.getPackageName()) == 0;
  }
  
  public static void m(Context paramContext)
  {
    try
    {
      Object localObject = com.appnext.base.a.a.aH().aM().be();
      if ((localObject != null) && (((List)localObject).size() == 0))
      {
        localObject = new JSONObject("{ \"status\": \"on\", \"sample\": \"1\", \"sample_type\": \"hour\", \"cycle\": \"1\", \"cycle_type\": \"interval\", \"exact\": \"false\", \"key\": \"cdm\" }");
        com.appnext.base.a.a.aH().aM().a((JSONObject)localObject);
        localObject = new Intent(paramContext, OperationService.class);
        ((Intent)localObject).putExtra("config_data_obj", cdm.class.getSimpleName());
        paramContext.startService((Intent)localObject);
        return;
      }
      paramContext = com.appnext.base.a.a.aH().aM().be();
      com.appnext.base.a.aD().a(paramContext);
      com.appnext.base.a.aD().aE();
      return;
    }
    catch (Throwable paramContext)
    {
      b.a(paramContext);
    }
  }
  
  public static boolean n(Context paramContext)
    throws Exception
  {
    paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
    return (paramContext != null) && (paramContext.isLimitAdTrackingEnabled());
  }
  
  public static boolean o(Context paramContext)
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
