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
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.appnext.base.operations.imp.cdm;
import com.appnext.base.operations.imp.rcd;
import com.appnext.base.services.OperationService;
import com.appnext.base.services.ReceiverService;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class k
{
  private static final String TAG = "SdkHelper";
  private static final long kl = 1000L;
  private static final long km = 60000L;
  private static final long kn = 3600000L;
  private static final long ko = 86400000L;
  private static final String kp = "cldb";
  private static Random kq = new Random();
  
  public k() {}
  
  public static long a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return c(paramInt1, paramInt2) + (kq.nextInt(paramInt3 + paramInt4) - paramInt3) * 1000L;
  }
  
  public static Location a(double paramDouble1, double paramDouble2, float paramFloat)
  {
    Location localLocation = new Location("");
    localLocation.setLatitude(paramDouble1);
    localLocation.setLongitude(paramDouble2);
    localLocation.setAccuracy(paramFloat);
    return localLocation;
  }
  
  public static Object a(String paramString, c.a paramA)
  {
    try
    {
      paramString = com.appnext.base.a.a.aM().aP().ae(paramString);
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        paramString = b(((com.appnext.base.a.b.b)paramString.get(0)).aX(), paramA);
        return paramString;
      }
    }
    catch (Throwable paramString) {}
    return null;
  }
  
  public static String a(Date paramDate)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(paramDate));
    localStringBuilder.append(" ");
    localStringBuilder.append(cM());
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
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    try
    {
      paramContext.startService(paramIntent);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void a(Context paramContext, Class paramClass, long paramLong, com.appnext.base.a.b.c paramC)
  {
    if ((paramContext == null) || (paramC == null) || (paramClass == null)) {
      return;
    }
    long l;
    label32:
    AlarmManager localAlarmManager;
    if ("time".equalsIgnoreCase(paramC.bb()))
    {
      l = 86400000L;
      if (l == -1L) {
        break label158;
      }
      localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
      paramClass = new Intent(paramContext, paramClass);
      paramClass.putExtra("config_data_obj", paramC.getKey());
      int i = paramC.be().hashCode();
      l.k(" *** alarm *** ", String.valueOf(paramC.be()));
      paramContext = PendingIntent.getService(paramContext, i, paramClass, 134217728);
      if (!paramC.bf()) {
        break label190;
      }
      if (Build.VERSION.SDK_INT < 23) {
        break label160;
      }
      localAlarmManager.setExactAndAllowWhileIdle(0, paramLong, paramContext);
    }
    for (;;)
    {
      localAlarmManager.setInexactRepeating(1, paramLong, l, paramContext);
      return;
      l = i(paramC.ba(), paramC.bb());
      break label32;
      label158:
      break;
      label160:
      if (Build.VERSION.SDK_INT >= 19)
      {
        localAlarmManager.setExact(0, paramLong, paramContext);
      }
      else
      {
        localAlarmManager.set(0, paramLong, paramContext);
        continue;
        label190:
        localAlarmManager.setInexactRepeating(1, paramLong, l, paramContext);
      }
    }
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
  
  public static boolean a(Class paramClass)
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
          break label170;
        }
        i = 0;
      }
    }
    catch (InvocationTargetException paramString1)
    {
      boolean bool;
      paramString1.getCause().printStackTrace();
      com.appnext.base.b.a(paramString1.getCause());
      return false;
    }
    catch (ClassNotFoundException paramString1)
    {
      return false;
    }
    catch (Throwable paramString1)
    {
      com.appnext.base.b.a(paramString1);
      return false;
    }
    if (paramString2.equals("monitoring"))
    {
      i = 1;
      break label170;
      return ((com.appnext.base.operations.a)Class.forName(com.appnext.base.operations.c.getOperationClassName(paramString1)).getConstructor(new Class[] { com.appnext.base.a.b.c.class, Bundle.class }).newInstance(new Object[] { paramC, null })).hasPermission();
      bool = ((com.appnext.base.receivers.c)Class.forName(ReceiverService.getOperationClassName(paramString1)).getConstructor(new Class[0]).newInstance(new Object[0])).hasPermission();
      return bool;
    }
    label170:
    switch (i)
    {
    }
    return false;
  }
  
  public static long aF(String paramString)
  {
    return i.cC().getLong(paramString + "_lastupdate", 0L);
  }
  
  public static String aG(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = paramString + "_hash";
    return i.cC().getString(paramString, null);
  }
  
  public static Object b(String paramString, c.a paramA)
  {
    try
    {
      switch (k.1.kr[paramA.ordinal()])
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
  
  public static void b(String paramString, List<com.appnext.base.a.b.b> paramList)
  {
    Intent localIntent = new Intent(d.getContext(), OperationService.class);
    localIntent.putExtra("config_data_obj", paramString);
    paramString = b.a(paramList, false);
    if ((paramString == null) || (paramString.length() == 0)) {
      return;
    }
    localIntent.putExtra("data", paramString.toString());
    localIntent.putExtra("action", rcd.class.getSimpleName());
    a(d.getContext(), localIntent);
  }
  
  public static boolean b(String paramString, Map<String, String> paramMap)
  {
    Object localObject = e.cv().au(paramString);
    if ((localObject == null) || ("off".equalsIgnoreCase(((com.appnext.base.a.b.c)localObject).aZ()))) {
      return true;
    }
    if (paramMap.isEmpty()) {
      return true;
    }
    String str2 = c.cs() + "/data";
    HashMap localHashMap = new HashMap();
    String str1 = g.u(d.getContext());
    localObject = str1;
    if (TextUtils.isEmpty(str1)) {
      localObject = i.cC().getString("google_ads_id", "");
    }
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return false;
    }
    localHashMap.put("aid", localObject);
    localHashMap.put("cuid", (String)localObject + "_" + System.currentTimeMillis());
    localHashMap.put("lvid", "4.7.1");
    try
    {
      localHashMap.put("localdate", a(new Date()));
      localHashMap.put("timezone", cM());
      localHashMap.put("app_package", d.getPackageName());
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
        com.appnext.base.b.a(localThrowable);
        localHashMap.put("app_package", "");
      }
      l.k(paramString, "------- cky = " + paramString + " ----------");
      paramMap = localHashMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        l.k(paramString, "---- " + (String)localEntry.getKey() + " : " + (String)localEntry.getValue() + " ----");
      }
      try
      {
        paramMap = g.a(str2, localHashMap, false, 15000, c.a.HashMap);
        if (paramMap != null)
        {
          paramMap = new String(paramMap, "UTF-8");
          l.k(paramString, "result: " + paramMap);
        }
        return true;
      }
      catch (HttpRetryException paramMap)
      {
        int i = paramMap.responseCode();
        paramMap = paramMap.getMessage();
        l.n(paramString, "(Type:HttpRetryException)" + paramMap + "  " + i);
        return false;
      }
      catch (Throwable paramMap)
      {
        paramMap = paramMap.getMessage();
        l.n(paramString, "(Type:Throwable) " + paramMap);
      }
    }
    return false;
  }
  
  public static long c(int paramInt1, int paramInt2)
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
  
  public static void cL()
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
  
  public static String cM()
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
  
  public static void cN() {}
  
  public static void d(String paramString1, String paramString2, c.a paramA)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new com.appnext.base.a.b.b(paramString1, paramString2, paramA.getType()));
    b(paramString1, localArrayList);
  }
  
  public static void e(String paramString1, String paramString2, c.a paramA)
  {
    com.appnext.base.a.a.aM().aP().b(new com.appnext.base.a.b.b(paramString1, paramString2, paramA.getType()));
  }
  
  public static long i(String paramString1, String paramString2)
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
          return a(Integer.parseInt(paramString1.substring(0, 2)), Integer.parseInt(paramString1.substring(2, 4)), 60, 60);
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
      com.appnext.base.b.a(paramString1);
      return -1L;
    }
    l1 = 0L;
    return l1;
  }
  
  public static void j(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return;
    }
    paramString1 = paramString1 + "_hash";
    i.cC().putString(paramString1, paramString2);
  }
  
  public static List<String> m(Context paramContext)
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
        com.appnext.base.b.a(paramContext);
        continue;
        if ((Build.VERSION.SDK_INT < 21) || (!n(paramContext.getApplicationContext()))) {
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
        if (f.g(d.getContext(), "android.permission.GET_TASKS"))
        {
          paramContext = ((ActivityManager)localObject1).getRunningTasks(3);
          if ((paramContext != null) && (!paramContext.isEmpty())) {
            localArrayList.add(((ActivityManager.RunningTaskInfo)paramContext.get(0)).baseActivity.getPackageName());
          }
        }
      }
      catch (Throwable paramContext)
      {
        com.appnext.base.b.a(paramContext);
      }
    }
    return localArrayList;
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
      Object localObject = com.appnext.base.a.a.aM().aQ().bl();
      if ((localObject != null) && (((List)localObject).size() == 0))
      {
        localObject = new JSONObject("{ \"status\": \"on\", \"sample\": \"1\", \"sample_type\": \"hour\", \"cycle\": \"1\", \"cycle_type\": \"interval\", \"exact\": \"false\", \"key\": \"cdm\" }");
        com.appnext.base.a.a.aM().aQ().a((JSONObject)localObject);
        localObject = new Intent(paramContext, OperationService.class);
        ((Intent)localObject).putExtra("config_data_obj", cdm.class.getSimpleName());
        a(paramContext, (Intent)localObject);
        return;
      }
      paramContext = com.appnext.base.a.a.aM().aQ().bl();
      com.appnext.base.a.aI().a(paramContext);
      com.appnext.base.a.aI().aJ();
      return;
    }
    catch (Throwable paramContext)
    {
      com.appnext.base.b.a(paramContext);
    }
  }
  
  public static boolean p(Context paramContext)
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
      com.appnext.base.b.a(paramContext);
      l.n("SdkHelper", paramContext.toString());
    }
    return true;
  }
}
