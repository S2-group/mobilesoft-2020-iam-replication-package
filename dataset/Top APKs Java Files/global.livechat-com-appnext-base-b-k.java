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
import com.google.android.gms.ads.a.a.a;
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
  public static final int ku = 120000;
  private static final long kv = 1000L;
  private static final long kw = 60000L;
  private static final long kx = 3600000L;
  private static final long ky = 86400000L;
  private static Random kz = new Random();
  
  public k() {}
  
  public static long a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return c(paramInt1, paramInt2) + (kz.nextInt(paramInt4 + paramInt3) - paramInt3) * 1000L;
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
        paramString = b(((com.appnext.base.a.b.b)paramString.get(0)).aY(), paramA);
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
    localStringBuilder.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(paramDate));
    localStringBuilder.append(" ");
    localStringBuilder.append(cO());
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
    if ((paramContext != null) && (paramC != null))
    {
      if (paramClass == null) {
        return;
      }
      long l;
      if ("time".equalsIgnoreCase(paramC.bc())) {
        l = 86400000L;
      } else {
        l = i(paramC.bb(), paramC.bc());
      }
      if (l == -1L) {
        return;
      }
      AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
      paramClass = new Intent(paramContext, paramClass);
      paramClass.putExtra("config_data_obj", paramC.getKey());
      int i = paramC.bf().hashCode();
      l.k(" *** alarm *** ", String.valueOf(paramC.bf()));
      paramContext = PendingIntent.getService(paramContext, i, paramClass, 134217728);
      if (paramC.bg())
      {
        if (Build.VERSION.SDK_INT >= 23) {
          localAlarmManager.setExactAndAllowWhileIdle(0, paramLong, paramContext);
        } else if (Build.VERSION.SDK_INT >= 19) {
          localAlarmManager.setExact(0, paramLong, paramContext);
        } else {
          localAlarmManager.set(0, paramLong, paramContext);
        }
      }
      else {
        localAlarmManager.setInexactRepeating(1, paramLong, l, paramContext);
      }
      localAlarmManager.setInexactRepeating(1, paramLong, l, paramContext);
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
    try
    {
      int i = d.getContext().getPackageManager().queryIntentServices(new Intent(d.getContext(), paramClass), 65536).size();
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
    try
    {
      int j = paramString2.hashCode();
      if (j != 570418373)
      {
        if ((j == 1852089416) && (paramString2.equals("monitoring"))) {
          i = 1;
        }
      }
      else if (paramString2.equals("interval")) {
        i = 0;
      }
    }
    catch (Throwable paramString1)
    {
      boolean bool;
      com.appnext.base.b.a(paramString1);
      return false;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.getCause().printStackTrace();
      com.appnext.base.b.a(paramString1.getCause());
      return false;
    }
    catch (ClassNotFoundException paramString1)
    {
      return false;
    }
    return ((com.appnext.base.receivers.c)Class.forName(ReceiverService.getOperationClassName(paramString1)).getConstructor(new Class[0]).newInstance(new Object[0])).hasPermission();
    bool = ((com.appnext.base.operations.a)Class.forName(com.appnext.base.operations.d.getOperationClassName(paramString1)).getConstructor(new Class[] { com.appnext.base.a.b.c.class, Bundle.class }).newInstance(new Object[] { paramC, null })).hasPermission();
    return bool;
    switch (i)
    {
    }
    return false;
  }
  
  public static long aF(String paramString)
  {
    i localI = i.cE();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("_lastupdate");
    return localI.getLong(localStringBuilder.toString(), 0L);
  }
  
  public static String aG(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("_hash");
    paramString = localStringBuilder.toString();
    return i.cE().getString(paramString, null);
  }
  
  public static Object b(String paramString, c.a paramA)
  {
    try
    {
      switch (1.kA[paramA.ordinal()])
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
  
  public static void b(String paramString, List<com.appnext.base.a.b.b> paramList)
  {
    Intent localIntent = new Intent(d.getContext(), OperationService.class);
    localIntent.putExtra("config_data_obj", paramString);
    paramString = b.a(paramList, false);
    if (paramString != null)
    {
      if (paramString.length() == 0) {
        return;
      }
      localIntent.putExtra("data", paramString.toString());
      localIntent.putExtra("action", rcd.class.getSimpleName());
      a(d.getContext(), localIntent);
    }
  }
  
  public static boolean b(String paramString, Map<String, String> paramMap)
  {
    Object localObject1 = e.cy().av(paramString);
    if (localObject1 != null)
    {
      if ("off".equalsIgnoreCase(((com.appnext.base.a.b.c)localObject1).ba())) {
        return true;
      }
      if (paramMap.isEmpty()) {
        return true;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(c.cv());
      ((StringBuilder)localObject1).append("/data");
      String str = ((StringBuilder)localObject1).toString();
      HashMap localHashMap = new HashMap();
      Object localObject3 = g.u(d.getContext());
      localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3)) {
        localObject1 = i.cE().getString("google_ads_id", "");
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
      localHashMap.put("lvid", "4.6.9");
      try
      {
        localHashMap.put("localdate", a(new Date()));
        localHashMap.put("timezone", cO());
        localHashMap.put("app_package", d.getPackageName());
      }
      catch (Throwable localThrowable)
      {
        com.appnext.base.b.a(localThrowable);
        localHashMap.put("app_package", "");
      }
      paramMap = paramMap.entrySet().iterator();
      Object localObject2;
      while (paramMap.hasNext())
      {
        localObject2 = (Map.Entry)paramMap.next();
        localHashMap.put((String)((Map.Entry)localObject2).getKey(), (String)((Map.Entry)localObject2).getValue());
      }
      paramMap = new StringBuilder();
      paramMap.append("-------Sending to server data for key = ");
      paramMap.append(paramString);
      paramMap.append(" ----------");
      l.k(paramString, paramMap.toString());
      paramMap = localHashMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        localObject2 = (Map.Entry)paramMap.next();
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("---- ");
        ((StringBuilder)localObject3).append((String)((Map.Entry)localObject2).getKey());
        ((StringBuilder)localObject3).append(" : ");
        ((StringBuilder)localObject3).append((String)((Map.Entry)localObject2).getValue());
        ((StringBuilder)localObject3).append(" ----");
        l.k(paramString, ((StringBuilder)localObject3).toString());
      }
      try
      {
        paramMap = g.a(str, localHashMap, false, 15000, c.a.HashMap);
        if (paramMap != null)
        {
          paramMap = new String(paramMap, "UTF-8");
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("result send data: ");
          ((StringBuilder)localObject2).append(paramMap);
          l.k(paramString, ((StringBuilder)localObject2).toString());
        }
        return true;
      }
      catch (Throwable paramMap)
      {
        paramMap = paramMap.getMessage();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("(Type:Throwable) ");
        ((StringBuilder)localObject2).append(paramMap);
        paramMap = ((StringBuilder)localObject2).toString();
        l.n(paramString, paramMap);
        return false;
      }
      catch (HttpRetryException paramMap)
      {
        for (;;)
        {
          int i = paramMap.responseCode();
          paramMap = paramMap.getMessage();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("(Type:HttpRetryException)");
          ((StringBuilder)localObject2).append(paramMap);
          ((StringBuilder)localObject2).append("  ");
          ((StringBuilder)localObject2).append(i);
          paramMap = ((StringBuilder)localObject2).toString();
        }
      }
    }
    return true;
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
  
  public static void cN()
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
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Name : - ");
        localStringBuilder.append(localRunningAppProcessInfo.processName);
        localStringBuilder.append(" Importance - ");
        localStringBuilder.append(localRunningAppProcessInfo.importance);
        localStringBuilder.append(" Lru - ");
        localStringBuilder.append(localRunningAppProcessInfo.lru);
        localStringBuilder.append(" LastTrimLevel - ");
        localStringBuilder.append(localRunningAppProcessInfo.lastTrimLevel);
        localStringBuilder.append(" ImportanceReasonCode - ");
        localStringBuilder.append(localRunningAppProcessInfo.importanceReasonCode);
        localStringBuilder.append(" importanceReasonComponent - ");
        localStringBuilder.append(localRunningAppProcessInfo.importanceReasonComponent);
        Log.e("Running app", localStringBuilder.toString());
      }
    }
  }
  
  public static String cO()
  {
    Object localObject = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
    int i = (((Calendar)localObject).get(15) + ((Calendar)localObject).get(16)) / 60000;
    char c;
    if (i < 0)
    {
      c = '-';
      i = -i;
    }
    else
    {
      c = '+';
    }
    localObject = new StringBuilder(9);
    ((StringBuilder)localObject).append("GMT");
    ((StringBuilder)localObject).append(c);
    a((StringBuilder)localObject, 2, i / 60);
    ((StringBuilder)localObject).append(':');
    a((StringBuilder)localObject, 2, i % 60);
    return ((StringBuilder)localObject).toString();
  }
  
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
    long l2;
    long l1;
    try
    {
      if ((TextUtils.isDigitsOnly(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (!TextUtils.isEmpty(paramString1)))
      {
        l2 = Long.parseLong(paramString1);
        if ("time".equalsIgnoreCase(paramString2))
        {
          if (paramString1.length() == 4) {
            return a(Integer.parseInt(paramString1.substring(0, 2)), Integer.parseInt(paramString1.substring(2, 4)), 60, 60);
          }
        }
        else
        {
          if ("second".equalsIgnoreCase(paramString2))
          {
            l1 = 1000L;
            break label164;
          }
          if ("minute".equalsIgnoreCase(paramString2))
          {
            l1 = 60000L;
            break label164;
          }
          if ("hour".equalsIgnoreCase(paramString2))
          {
            l1 = 3600000L;
            break label164;
          }
          boolean bool = "day".equalsIgnoreCase(paramString2);
          if (bool)
          {
            l1 = 86400000L;
            break label164;
          }
          return -1L;
        }
      }
      else
      {
        return 0L;
      }
    }
    catch (Throwable paramString1)
    {
      com.appnext.base.b.a(paramString1);
      return -1L;
    }
    return -1L;
    label164:
    return l2 * l1;
  }
  
  public static void j(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("_hash");
    paramString1 = localStringBuilder.toString();
    i.cE().putString(paramString1, paramString2);
  }
  
  public static List<String> m(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext == null) {
      return localArrayList;
    }
    try
    {
      Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
      int i = Build.VERSION.SDK_INT;
      if (i < 21)
      {
        try
        {
          if (!f.g(d.getContext(), "android.permission.GET_TASKS")) {
            break label332;
          }
          paramContext = ((ActivityManager)localObject1).getRunningTasks(3);
          if ((paramContext == null) || (paramContext.isEmpty())) {
            break label332;
          }
          localArrayList.add(((ActivityManager.RunningTaskInfo)paramContext.get(0)).baseActivity.getPackageName());
          return localArrayList;
        }
        catch (Throwable paramContext)
        {
          com.appnext.base.b.a(paramContext);
          return localArrayList;
        }
      }
      else if ((Build.VERSION.SDK_INT >= 21) && (n(paramContext.getApplicationContext())))
      {
        localObject1 = (UsageStatsManager)paramContext.getSystemService("usagestats");
        long l = System.currentTimeMillis();
        paramContext = ((UsageStatsManager)localObject1).queryUsageStats(4, l - 300000L, l);
        if (paramContext == null) {
          return localArrayList;
        }
        Object localObject2 = paramContext.listIterator();
        while (((ListIterator)localObject2).hasNext())
        {
          UsageStats localUsageStats = (UsageStats)((ListIterator)localObject2).next();
          if ((Build.VERSION.SDK_INT >= 23) && (((UsageStatsManager)localObject1).isAppInactive(localUsageStats.getPackageName()))) {
            ((ListIterator)localObject2).remove();
          }
        }
        if (paramContext.size() > 0)
        {
          localObject1 = new TreeMap();
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            localObject2 = (UsageStats)paramContext.next();
            ((SortedMap)localObject1).put(Long.valueOf(((UsageStats)localObject2).getLastTimeUsed()), localObject2);
          }
          if (!((SortedMap)localObject1).isEmpty())
          {
            localArrayList.add(((UsageStats)((SortedMap)localObject1).get(((SortedMap)localObject1).lastKey())).getPackageName());
            return localArrayList;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      com.appnext.base.b.a(paramContext);
    }
    label332:
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
      Object localObject = com.appnext.base.a.a.aM().aR().bm();
      if ((localObject != null) && (((List)localObject).size() == 0))
      {
        localObject = new JSONObject("{ \"status\": \"on\", \"sample\": \"1\", \"sample_type\": \"hour\", \"cycle\": \"1\", \"cycle_type\": \"interval\", \"exact\": \"false\", \"key\": \"cdm\" }");
        com.appnext.base.a.a.aM().aR().a((JSONObject)localObject);
        localObject = new Intent(paramContext, OperationService.class);
        ((Intent)localObject).putExtra("config_data_obj", cdm.class.getSimpleName());
        a(paramContext, (Intent)localObject);
        return;
      }
      paramContext = com.appnext.base.a.a.aM().aR().bm();
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
    paramContext = com.google.android.gms.ads.a.a.a(paramContext);
    return (paramContext != null) && (paramContext.b());
  }
  
  public static boolean q(Context paramContext)
  {
    boolean bool = true;
    try
    {
      paramContext = com.google.android.gms.ads.a.a.a(paramContext);
      if (paramContext != null)
      {
        bool = paramContext.b();
        if (bool) {
          return true;
        }
        bool = false;
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
