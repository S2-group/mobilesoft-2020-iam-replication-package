package com.mindefy.phoneaddiction.mobilepe.Util;

import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mindefy.phoneaddiction.mobilepe.YourHour;
import com.mindefy.phoneaddiction.mobilepe.pojo.AllAppInfoPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AllEventsPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfoOfflineJsonPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfoOfflinePojo;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UsageStat
{
  private static final String TAG = "UsageStat";
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
  
  public UsageStat() {}
  
  public static List<UsageEvents.Event> getDailyUsageEvent(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    new HashMap();
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).set(11, 0);
    ((Calendar)localObject).set(12, 0);
    ((Calendar)localObject).set(13, 0);
    ((Calendar)localObject).set(14, 0);
    long l1 = ((Calendar)localObject).getTimeInMillis();
    long l2 = System.currentTimeMillis();
    paramContext = ((UsageStatsManager)paramContext.getSystemService("usagestats")).queryEvents(l1, l2);
    while (paramContext.hasNextEvent())
    {
      localObject = new UsageEvents.Event();
      paramContext.getNextEvent((UsageEvents.Event)localObject);
      if (((((UsageEvents.Event)localObject).getEventType() == 1) || (((UsageEvents.Event)localObject).getEventType() == 2)) && (!((UsageEvents.Event)localObject).getPackageName().equalsIgnoreCase("com.google.android.googlequicksearchbox"))) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  public static long getHour(long paramLong)
  {
    return TimeUnit.MILLISECONDS.toHours(paramLong);
  }
  
  public static String getHourMinute(long paramLong)
  {
    long l = TimeUnit.MILLISECONDS.toHours(paramLong);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(l);
    ((StringBuilder)localObject1).append("h");
    String str = ((StringBuilder)localObject1).toString();
    paramLong = TimeUnit.MILLISECONDS.toMinutes(paramLong) % TimeUnit.HOURS.toMinutes(1L);
    if (paramLong < 10L)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramLong);
      ((StringBuilder)localObject1).append("m");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramLong);
      ((StringBuilder)localObject1).append("m");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    Object localObject2 = localObject1;
    if (l != 0L)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(str);
      ((StringBuilder)localObject2).append(" ");
      ((StringBuilder)localObject2).append((String)localObject1);
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    return localObject2;
  }
  
  public static long getMinute(long paramLong)
  {
    return TimeUnit.MILLISECONDS.toMinutes(paramLong) % TimeUnit.HOURS.toMinutes(1L);
  }
  
  public static AllEventsPojo getUsageStatistics(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new HashMap();
    Object localObject2 = Calendar.getInstance();
    int i = 0;
    ((Calendar)localObject2).set(11, 0);
    ((Calendar)localObject2).set(12, 0);
    ((Calendar)localObject2).set(13, 0);
    ((Calendar)localObject2).set(14, 0);
    long l1 = ((Calendar)localObject2).getTimeInMillis();
    long l2 = System.currentTimeMillis();
    paramContext = ((UsageStatsManager)paramContext.getSystemService("usagestats")).queryEvents(l1, l2);
    while (paramContext.hasNextEvent())
    {
      localObject2 = new UsageEvents.Event();
      paramContext.getNextEvent((UsageEvents.Event)localObject2);
      if ((((UsageEvents.Event)localObject2).getEventType() == 1) || (((UsageEvents.Event)localObject2).getEventType() == 2))
      {
        localArrayList.add(localObject2);
        localObject2 = ((UsageEvents.Event)localObject2).getPackageName();
        if (((HashMap)localObject1).get(localObject2) == null) {
          ((HashMap)localObject1).put(localObject2, new AppUsageInfo((String)localObject2));
        }
      }
    }
    l1 = 0L;
    while (i < localArrayList.size() - 1)
    {
      paramContext = (UsageEvents.Event)localArrayList.get(i);
      int j = i + 1;
      localObject2 = (UsageEvents.Event)localArrayList.get(j);
      if ((!paramContext.getPackageName().equals(((UsageEvents.Event)localObject2).getPackageName())) && (((UsageEvents.Event)localObject2).getEventType() == 1))
      {
        AppUsageInfo localAppUsageInfo = (AppUsageInfo)((HashMap)localObject1).get(((UsageEvents.Event)localObject2).getPackageName());
        localAppUsageInfo.launchCount += 1;
      }
      i = j;
      if (paramContext.getEventType() == 1)
      {
        i = j;
        if (((UsageEvents.Event)localObject2).getEventType() == 2)
        {
          i = j;
          if (paramContext.getClassName().equals(((UsageEvents.Event)localObject2).getClassName()))
          {
            i = j;
            if (!paramContext.getPackageName().equalsIgnoreCase("com.google.android.googlequicksearchbox"))
            {
              l2 = ((UsageEvents.Event)localObject2).getTimeStamp() - paramContext.getTimeStamp();
              l1 += l2;
              paramContext = (AppUsageInfo)((HashMap)localObject1).get(paramContext.getPackageName());
              paramContext.timeInForeground += l2;
              i = j;
            }
          }
        }
      }
    }
    paramContext = new ArrayList(((HashMap)localObject1).values());
    localObject1 = new AllEventsPojo();
    ((AllEventsPojo)localObject1).setTotalScreenOnTime(l1);
    ((AllEventsPojo)localObject1).setAllEvents(localArrayList);
    ((AllEventsPojo)localObject1).setAppUsageInfoList(paramContext);
    return localObject1;
  }
  
  public static AllAppInfoPojo getUsageStatsList(Context paramContext)
  {
    AllAppInfoPojo localAllAppInfoPojo = new AllAppInfoPojo();
    AllEventsPojo localAllEventsPojo = getUsageStatistics(paramContext);
    Object localObject1 = localAllEventsPojo.getAppUsageInfoList();
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = new Gson();
    Object localObject3 = paramContext.getSharedPreferences("mobilePePreference", 0);
    String str = ((Gson)localObject2).toJson(new ArrayList());
    paramContext = new HashMap();
    localObject2 = ((ArrayList)((Gson)localObject2).fromJson(((SharedPreferences)localObject3).getString("disabledAppsJson", str), new TypeToken() {}.getType())).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (String)((Iterator)localObject2).next();
      paramContext.put(localObject3, localObject3);
    }
    localObject1 = ((List)localObject1).iterator();
    long l = 0L;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (AppUsageInfo)((Iterator)localObject1).next();
      if (!paramContext.containsKey(((AppUsageInfo)localObject2).packageName))
      {
        l += ((AppUsageInfo)localObject2).timeInForeground;
        localObject3 = new AppInfo();
        ((AppInfo)localObject3).setTotalAppForgroundTime(((AppUsageInfo)localObject2).timeInForeground);
        ((AppInfo)localObject3).setAppLaunchCount(((AppUsageInfo)localObject2).launchCount);
        ((AppInfo)localObject3).setPname(((AppUsageInfo)localObject2).packageName);
        localArrayList.add(localObject3);
      }
    }
    localAllAppInfoPojo.setAppInfoList(localArrayList);
    localAllAppInfoPojo.setAllEventsPojo(localAllEventsPojo);
    localAllAppInfoPojo.setTotalUsageTime(l);
    return localAllAppInfoPojo;
  }
  
  private static UsageStatsManager getUsageStatsManager(Context paramContext)
  {
    return (UsageStatsManager)paramContext.getSystemService("usagestats");
  }
  
  public static List<AppInfo> getWeekAppInfo(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = getWeekUsageStatsList(paramContext);
    HashMap localHashMap = new HashMap();
    localObject2 = ((List)localObject2).iterator();
    AppInfo localAppInfo;
    while (((Iterator)localObject2).hasNext())
    {
      localAppInfo = (AppInfo)((Iterator)localObject2).next();
      localHashMap.put(localAppInfo.getPname(), localAppInfo);
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      localAppInfo = new AppInfo();
      String str = ((PackageInfo)localObject2).packageName;
      if ((!str.equalsIgnoreCase(Util.getLauncherPackage(paramContext))) && (!str.equalsIgnoreCase("com.android.systemui")) && (!str.equalsIgnoreCase("android")) && (!str.equalsIgnoreCase("com.google.android.packageinstaller")) && (localHashMap.containsKey(str)))
      {
        long l = ((AppInfo)localHashMap.get(str)).getTotalAppForgroundTime();
        int i = ((AppInfo)localHashMap.get(str)).getAppLaunchCount();
        localAppInfo.setAppRunTime(Util.convertMilliToTime(l));
        localAppInfo.setTotalAppForgroundTime(l);
        Drawable localDrawable = ((PackageInfo)localObject2).applicationInfo.loadIcon(localPackageManager);
        localAppInfo.setPname(str);
        localAppInfo.setAppname((String)((PackageInfo)localObject2).applicationInfo.loadLabel(localPackageManager));
        localAppInfo.setIcon(localDrawable);
        localAppInfo.setAppLaunchCount(i);
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  private static List<AppUsageInfo> getWeekUsageStatistics(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject = Calendar.getInstance();
    long l1 = System.currentTimeMillis();
    ((Calendar)localObject).add(5, -7);
    int j = 0;
    ((Calendar)localObject).set(11, 0);
    ((Calendar)localObject).set(12, 0);
    ((Calendar)localObject).set(13, 0);
    ((Calendar)localObject).set(14, 0);
    long l2 = ((Calendar)localObject).getTimeInMillis();
    paramContext = ((UsageStatsManager)paramContext.getSystemService("usagestats")).queryEvents(l2, l1);
    int i;
    for (;;)
    {
      i = j;
      if (!paramContext.hasNextEvent()) {
        break;
      }
      localObject = new UsageEvents.Event();
      paramContext.getNextEvent((UsageEvents.Event)localObject);
      if ((((UsageEvents.Event)localObject).getEventType() == 1) || (((UsageEvents.Event)localObject).getEventType() == 2))
      {
        localArrayList.add(localObject);
        localObject = ((UsageEvents.Event)localObject).getPackageName();
        if (localHashMap.get(localObject) == null) {
          localHashMap.put(localObject, new AppUsageInfo((String)localObject));
        }
      }
    }
    while (i < localArrayList.size() - 1)
    {
      paramContext = (UsageEvents.Event)localArrayList.get(i);
      j = i + 1;
      localObject = (UsageEvents.Event)localArrayList.get(j);
      if ((!paramContext.getPackageName().equals(((UsageEvents.Event)localObject).getPackageName())) && (((UsageEvents.Event)localObject).getEventType() == 1))
      {
        AppUsageInfo localAppUsageInfo = (AppUsageInfo)localHashMap.get(((UsageEvents.Event)localObject).getPackageName());
        localAppUsageInfo.launchCount += 1;
      }
      i = j;
      if (paramContext.getEventType() == 1)
      {
        i = j;
        if (((UsageEvents.Event)localObject).getEventType() == 2)
        {
          i = j;
          if (paramContext.getClassName().equals(((UsageEvents.Event)localObject).getClassName()))
          {
            l1 = ((UsageEvents.Event)localObject).getTimeStamp();
            l2 = paramContext.getTimeStamp();
            paramContext = (AppUsageInfo)localHashMap.get(paramContext.getPackageName());
            paramContext.timeInForeground += l1 - l2;
            i = j;
          }
        }
      }
    }
    return new ArrayList(localHashMap.values());
  }
  
  static List<UsageStats> getWeekUsageStatisticsList(Context paramContext)
  {
    paramContext = getUsageStatsManager(paramContext);
    Object localObject = Calendar.getInstance();
    long l1 = ((Calendar)localObject).getTimeInMillis();
    ((Calendar)localObject).set(5, -7);
    ((Calendar)localObject).set(11, 0);
    ((Calendar)localObject).set(12, 0);
    ((Calendar)localObject).set(13, 0);
    long l2 = ((Calendar)localObject).getTimeInMillis();
    localObject = TAG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Range start:");
    localStringBuilder.append(dateFormat.format(Long.valueOf(l2)));
    Log.d((String)localObject, localStringBuilder.toString());
    localObject = TAG;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Range end:");
    localStringBuilder.append(dateFormat.format(Long.valueOf(l1)));
    Log.d((String)localObject, localStringBuilder.toString());
    return paramContext.queryUsageStats(0, l2, l1);
  }
  
  static List<AppInfo> getWeekUsageStatsList(Context paramContext)
  {
    Object localObject1 = getWeekUsageStatistics(paramContext);
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = new Gson();
    Object localObject3 = paramContext.getSharedPreferences("mobilePePreference", 0);
    String str = ((Gson)localObject2).toJson(new ArrayList());
    paramContext = new HashMap();
    localObject2 = ((ArrayList)((Gson)localObject2).fromJson(((SharedPreferences)localObject3).getString("disabledAppsJson", str), new TypeToken() {}.getType())).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (String)((Iterator)localObject2).next();
      paramContext.put(localObject3, localObject3);
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (AppUsageInfo)((Iterator)localObject1).next();
      if (!paramContext.containsKey(((AppUsageInfo)localObject2).packageName))
      {
        localObject3 = new AppInfo();
        ((AppInfo)localObject3).setPname(((AppUsageInfo)localObject2).packageName);
        ((AppInfo)localObject3).setTotalAppForgroundTime(((AppUsageInfo)localObject2).timeInForeground);
        ((AppInfo)localObject3).setAppLaunchCount(((AppUsageInfo)localObject2).launchCount);
        localArrayList.add(localObject3);
      }
    }
    return localArrayList;
  }
  
  private static List<AppUsageInfo> getYesterdayUsageStatistics(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).add(5, -1);
    ((Calendar)localObject).set(11, 23);
    ((Calendar)localObject).set(12, 59);
    ((Calendar)localObject).set(13, 59);
    ((Calendar)localObject).set(14, 999);
    long l1 = ((Calendar)localObject).getTimeInMillis();
    int j = 0;
    ((Calendar)localObject).set(11, 0);
    ((Calendar)localObject).set(12, 0);
    ((Calendar)localObject).set(13, 0);
    ((Calendar)localObject).set(14, 0);
    long l2 = ((Calendar)localObject).getTimeInMillis();
    paramContext = ((UsageStatsManager)paramContext.getSystemService("usagestats")).queryEvents(l2, l1);
    int i;
    for (;;)
    {
      i = j;
      if (!paramContext.hasNextEvent()) {
        break;
      }
      localObject = new UsageEvents.Event();
      paramContext.getNextEvent((UsageEvents.Event)localObject);
      if ((((UsageEvents.Event)localObject).getEventType() == 1) || (((UsageEvents.Event)localObject).getEventType() == 2))
      {
        localArrayList.add(localObject);
        localObject = ((UsageEvents.Event)localObject).getPackageName();
        if (localHashMap.get(localObject) == null) {
          localHashMap.put(localObject, new AppUsageInfo((String)localObject));
        }
      }
    }
    while (i < localArrayList.size() - 1)
    {
      paramContext = (UsageEvents.Event)localArrayList.get(i);
      j = i + 1;
      localObject = (UsageEvents.Event)localArrayList.get(j);
      if ((!paramContext.getPackageName().equals(((UsageEvents.Event)localObject).getPackageName())) && (((UsageEvents.Event)localObject).getEventType() == 1))
      {
        AppUsageInfo localAppUsageInfo = (AppUsageInfo)localHashMap.get(((UsageEvents.Event)localObject).getPackageName());
        localAppUsageInfo.launchCount += 1;
      }
      i = j;
      if (paramContext.getEventType() == 1)
      {
        i = j;
        if (((UsageEvents.Event)localObject).getEventType() == 2)
        {
          i = j;
          if (paramContext.getClassName().equals(((UsageEvents.Event)localObject).getClassName()))
          {
            i = j;
            if (!paramContext.getPackageName().equalsIgnoreCase("com.google.android.googlequicksearchbox"))
            {
              l1 = ((UsageEvents.Event)localObject).getTimeStamp();
              l2 = paramContext.getTimeStamp();
              paramContext = (AppUsageInfo)localHashMap.get(paramContext.getPackageName());
              paramContext.timeInForeground += l1 - l2;
              i = j;
            }
          }
        }
      }
    }
    return new ArrayList(localHashMap.values());
  }
  
  public static long printCurrentUsageStatus(Context paramContext)
  {
    return printUsageStats(getUsageStatistics(paramContext));
  }
  
  private static long printUsageStats(AllEventsPojo paramAllEventsPojo)
  {
    paramAllEventsPojo = paramAllEventsPojo.getAppUsageInfoList().iterator();
    for (long l = 0L; paramAllEventsPojo.hasNext(); l += ((AppUsageInfo)paramAllEventsPojo.next()).timeInForeground) {}
    return l;
  }
  
  public static String printYesterdayUsageStatus(Context paramContext, boolean paramBoolean)
  {
    return printYesterdayUsageTime(paramContext, getYesterdayUsageStatistics(paramContext), paramBoolean);
  }
  
  private static String printYesterdayUsageTime(Context paramContext, List<AppUsageInfo> paramList, boolean paramBoolean)
  {
    long l1 = 0L;
    long l2;
    if (paramBoolean)
    {
      int i = 0;
      Object localObject = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        AppUsageInfo localAppUsageInfo = (AppUsageInfo)paramList.next();
        l1 += localAppUsageInfo.timeInForeground;
        AppInfoOfflinePojo localAppInfoOfflinePojo = new AppInfoOfflinePojo();
        localAppInfoOfflinePojo.setTotalAppForgroundTime(localAppUsageInfo.timeInForeground);
        localAppInfoOfflinePojo.setAppLaunchCount(localAppUsageInfo.launchCount);
        localAppInfoOfflinePojo.setPackageName(localAppUsageInfo.packageName);
        i += 1;
        localAppInfoOfflinePojo.setId(i);
        ((List)localObject).add(localAppInfoOfflinePojo);
      }
      paramList = new GsonBuilder().create().toJsonTree(localObject).getAsJsonArray().toString();
      localObject = Calendar.getInstance();
      i = Util.getUnlockCount(paramContext, Util.getYesterdayDate());
      paramContext = new AppInfoOfflineJsonPojo();
      paramContext.setDateMilli(((Calendar)localObject).getTimeInMillis());
      paramContext.setDate(Util.getYesterdayDate());
      paramContext.setDateNumber(Util.getDateInteger());
      paramContext.setMonthNumber(Util.getMonthInteger());
      paramContext.setAllAppListJson(paramList);
      paramContext.setTotalUsageOfTheDay(l1);
      paramContext.setTotalUnlockCount(i);
      YourHour.getApp().getBoxStore().boxFor(AppInfoOfflineJsonPojo.class).put(paramContext);
      l2 = l1;
    }
    else
    {
      paramContext = paramList.iterator();
      for (;;)
      {
        l2 = l1;
        if (!paramContext.hasNext()) {
          break;
        }
        l1 += ((AppUsageInfo)paramContext.next()).timeInForeground;
      }
    }
    return getHourMinute(l2);
  }
  
  public static class AppUsageInfo
  {
    Drawable appIcon;
    String appName;
    int launchCount;
    String packageName;
    long timeInForeground;
    
    AppUsageInfo(String paramString)
    {
      this.packageName = paramString;
    }
  }
}
