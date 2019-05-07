package com.mindefy.phoneaddiction.mobilepe.Util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.mindefy.phoneaddiction.mobilepe.YourHour;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfoOfflineJsonPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.GoalChallengePojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.UsageInstanceDailyDetailPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.WeekDataPojo;
import com.mindefy.phoneaddiction.mobilepe.receiver.DailyReportReceiver;
import com.mindefy.phoneaddiction.mobilepe.receiver.UpdateObjectBoxReceiver;
import com.mindefy.phoneaddiction.mobilepe.receiver.WeeklyReportReceiver;
import com.mindefy.phoneaddiction.mobilepe.receiver.WidgetReceiver;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Util
{
  public Util() {}
  
  public static boolean IsOverlayTimeAllow(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      boolean bool = paramContext.getBoolean("OverlayTimeAllow", false);
      return bool;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toBoolean(TypeSafeSharedPreference.getAll(paramContext).get("OverlayTimeAllow"));
  }
  
  public static void alarmManagerDaily(int paramInt1, int paramInt2, Context paramContext)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Object localObject1 = new Intent(paramContext, DailyReportReceiver.class);
    ((Intent)localObject1).putExtra("notificationClick", true);
    paramContext = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject1, 134217728);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("custom://");
    ((StringBuilder)localObject2).append(System.currentTimeMillis());
    ((Intent)localObject1).setData(Uri.parse(((StringBuilder)localObject2).toString()));
    if (localAlarmManager != null)
    {
      localAlarmManager.cancel(paramContext);
      localObject1 = Calendar.getInstance();
      localObject2 = Calendar.getInstance();
      ((Calendar)localObject1).set(11, paramInt1);
      ((Calendar)localObject1).set(12, paramInt2);
      ((Calendar)localObject1).set(13, 0);
      Log.v("hour", String.valueOf(paramInt1));
      Log.v("minute", String.valueOf(paramInt2));
      if (((Calendar)localObject2).after(localObject1)) {
        ((Calendar)localObject1).add(5, 1);
      }
      localAlarmManager.setRepeating(0, ((Calendar)localObject1).getTimeInMillis(), 86400000L, paramContext);
    }
  }
  
  public static void alarmManagerUpdateObjectbox(Context paramContext)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar1.set(11, 0);
    localCalendar1.set(12, 30);
    localCalendar1.set(13, 0);
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, UpdateObjectBoxReceiver.class), 134217728);
    paramContext = (AlarmManager)paramContext.getSystemService("alarm");
    if (localCalendar2.after(localCalendar1)) {
      localCalendar1.add(5, 1);
    }
    if (paramContext != null) {
      paramContext.setRepeating(0, localCalendar1.getTimeInMillis(), 86400000L, localPendingIntent);
    }
  }
  
  public static void alarmManagerUpdateWidget(Context paramContext)
  {
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, WidgetReceiver.class), 134217728);
    paramContext = (AlarmManager)paramContext.getSystemService("alarm");
    if (paramContext != null) {
      paramContext.setRepeating(2, SystemClock.elapsedRealtime(), 120000L, localPendingIntent);
    }
  }
  
  public static void alarmManagerWeekly(int paramInt1, int paramInt2, Context paramContext)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Object localObject1 = new Intent(paramContext, WeeklyReportReceiver.class);
    ((Intent)localObject1).putExtra("weeklyNotificationClick", true);
    paramContext = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject1, 134217728);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("custom://");
    ((StringBuilder)localObject2).append(System.currentTimeMillis());
    ((Intent)localObject1).setData(Uri.parse(((StringBuilder)localObject2).toString()));
    System.currentTimeMillis();
    if (localAlarmManager != null)
    {
      localAlarmManager.cancel(paramContext);
      localObject1 = Calendar.getInstance();
      localObject2 = Calendar.getInstance();
      ((Calendar)localObject1).set(7, 1);
      ((Calendar)localObject1).set(11, paramInt1);
      ((Calendar)localObject1).set(12, paramInt2);
      ((Calendar)localObject1).set(13, 0);
      Log.v("hour", String.valueOf(paramInt1));
      Log.v("minute", String.valueOf(paramInt2));
      if (((Calendar)localObject2).after(localObject1)) {
        ((Calendar)localObject1).add(4, 1);
      }
      localAlarmManager.setRepeating(0, ((Calendar)localObject1).getTimeInMillis(), 86400000L, paramContext);
    }
  }
  
  public static String convertMiliToHMS(long paramLong)
  {
    return String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toHours(paramLong)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(paramLong) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(paramLong))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(paramLong) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(paramLong))) });
  }
  
  public static int convertMiliToHour(long paramLong)
  {
    return (int)TimeUnit.MILLISECONDS.toHours(paramLong);
  }
  
  public static int convertMiliToMin(long paramLong)
  {
    return (int)TimeUnit.MILLISECONDS.toMinutes(paramLong);
  }
  
  public static int convertMiliToMinute(long paramLong)
  {
    return (int)(TimeUnit.MILLISECONDS.toMinutes(paramLong) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(paramLong)));
  }
  
  public static String convertMilliToTime(long paramLong)
  {
    long l1 = TimeUnit.MILLISECONDS.toHours(paramLong);
    long l2 = TimeUnit.MILLISECONDS.toMinutes(paramLong) % TimeUnit.HOURS.toMinutes(1L);
    long l3 = TimeUnit.MILLISECONDS.toSeconds(paramLong);
    paramLong = TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(paramLong));
    if (l1 > 0L)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(l1);
      localStringBuilder.append("h ");
      localStringBuilder.append(l2);
      localStringBuilder.append("m");
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(l2);
    localStringBuilder.append("m ");
    localStringBuilder.append(l3 - paramLong);
    localStringBuilder.append("s");
    return localStringBuilder.toString();
  }
  
  public static String convertMilliToTimeString(long paramLong)
  {
    long l = TimeUnit.MILLISECONDS.toHours(paramLong);
    paramLong = TimeUnit.MILLISECONDS.toMinutes(paramLong) % TimeUnit.HOURS.toMinutes(1L);
    if (l < 10L)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("0");
      localStringBuilder.append(l);
      localStringBuilder.append(" hr ");
      localStringBuilder.append(paramLong);
      localStringBuilder.append(" min");
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(l);
    localStringBuilder.append(" hr ");
    localStringBuilder.append(paramLong);
    localStringBuilder.append(" min");
    return localStringBuilder.toString();
  }
  
  private static String convertMillisToDate(long paramLong)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  
  public static List<AppInfo> getAllAppInfo(Activity paramActivity)
    throws PackageManager.NameNotFoundException
  {
    paramActivity = paramActivity.getPackageManager();
    Object localObject = paramActivity.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      AppInfo localAppInfo = new AppInfo();
      if (((localPackageInfo.applicationInfo.flags & 0x80) != 0) || ((localPackageInfo.applicationInfo.flags & 0x1) == 0))
      {
        String str = localPackageInfo.packageName;
        Drawable localDrawable = localPackageInfo.applicationInfo.loadIcon(paramActivity);
        localAppInfo.setPname(str);
        localAppInfo.setAppname((String)localPackageInfo.applicationInfo.loadLabel(paramActivity));
        localAppInfo.setIcon(localDrawable);
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  public static int getAppLaunchCount(String paramString1, Context paramContext, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences(paramString2, 0);
    try
    {
      int i = paramContext.getInt(paramString1, 0);
      return i;
    }
    catch (ClassCastException paramString2)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toInteger(TypeSafeSharedPreference.getAll(paramContext).get(paramString1));
  }
  
  public static long getAppUsageTimeLimit(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      long l = paramContext.getLong(paramString, 1800000L);
      return l;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toLong(TypeSafeSharedPreference.getAll(paramContext).get(paramString));
  }
  
  public static String getCurrentAddictionCategory(Activity paramActivity)
  {
    return paramActivity.getSharedPreferences("mobilePePreference", 0).getString("CurrentAddictionCategory", "");
  }
  
  public static String getCurrentDate()
  {
    Date localDate = Calendar.getInstance().getTime();
    return new SimpleDateFormat("dd-MM").format(localDate);
  }
  
  public static String getCurrentDay()
  {
    return new SimpleDateFormat("EEEE").format(new Date());
  }
  
  public static String getCurrentTime()
  {
    return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
  }
  
  public static int getDailyUnlockCountGoal(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      int i = paramContext.getInt("dailyUnlockCountGoal", 50);
      return i;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toInteger(TypeSafeSharedPreference.getAll(paramContext).get("dailyUnlockCountGoal"));
  }
  
  public static long getDailyUsageTimeGoal(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      long l = paramContext.getLong("dailyGoalValue", 5400000L);
      return l;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toLong(TypeSafeSharedPreference.getAll(paramContext).get("dailyGoalValue"));
  }
  
  static int getDateInteger()
  {
    Date localDate = Calendar.getInstance().getTime();
    return Integer.parseInt(new SimpleDateFormat("dd").format(localDate));
  }
  
  public static long getGoalChallengeStartDate(Activity paramActivity)
  {
    return paramActivity.getSharedPreferences("mobilePePreference", 0).getLong("GoalChallengeStartDate", 0L);
  }
  
  public static int getHeavyUsageGoalLimit(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      int i = paramContext.getInt("heavyUsageGoal", 80);
      return i;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toInteger(TypeSafeSharedPreference.getAll(paramContext).get("heavyUsageGoal"));
  }
  
  public static int getLast7dayAppLaunchCount(String paramString, Context paramContext)
  {
    Calendar localCalendar = GregorianCalendar.getInstance();
    int i = 0;
    int j = 0;
    while (i < 7)
    {
      localCalendar.setTime(new Date());
      localCalendar.add(6, -i);
      Date localDate = localCalendar.getTime();
      j += getAppLaunchCount(paramString, paramContext, new SimpleDateFormat("dd-MM").format(localDate));
      i += 1;
    }
    return j;
  }
  
  public static List<String> getLast7dayFromYesterdayList()
  {
    ArrayList localArrayList = new ArrayList();
    Calendar localCalendar = GregorianCalendar.getInstance();
    int i = 1;
    while (i < 8)
    {
      localCalendar.setTime(new Date());
      localCalendar.add(6, -i);
      Date localDate = localCalendar.getTime();
      localArrayList.add(new SimpleDateFormat("dd-MM").format(localDate));
      i += 1;
    }
    return localArrayList;
  }
  
  public static List<String> getLast7dayList()
  {
    ArrayList localArrayList = new ArrayList();
    Calendar localCalendar = GregorianCalendar.getInstance();
    int i = 0;
    while (i < 7)
    {
      localCalendar.setTime(new Date());
      localCalendar.add(6, -i);
      Date localDate = localCalendar.getTime();
      localArrayList.add(new SimpleDateFormat("dd-MM").format(localDate));
      i += 1;
    }
    return localArrayList;
  }
  
  public static List<String> getLastWeekFromYesterdayDate()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(6, -7);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < 7)
    {
      localCalendar.add(6, 1);
      localArrayList.add(localSimpleDateFormat.format(localCalendar.getTime()));
      i += 1;
    }
    return localArrayList;
  }
  
  public static String getLauncherPackage(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if (paramContext != null)
    {
      paramContext = paramContext.activityInfo.packageName;
      if (!paramContext.equalsIgnoreCase("")) {
        return paramContext;
      }
      return "#";
    }
    return "#";
  }
  
  public static int getLightUsageGoalLimit(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      int i = paramContext.getInt("lightUsageGoal", 30);
      return i;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toInteger(TypeSafeSharedPreference.getAll(paramContext).get("lightUsageGoal"));
  }
  
  public static int getModerateUsageGoalLimit(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      int i = paramContext.getInt("moderateUsageGoal", 50);
      return i;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toInteger(TypeSafeSharedPreference.getAll(paramContext).get("moderateUsageGoal"));
  }
  
  static int getMonthInteger()
  {
    Date localDate = Calendar.getInstance().getTime();
    return Integer.parseInt(new SimpleDateFormat("MM").format(localDate));
  }
  
  public static List<GoalChallengePojo> getNext21Dates(long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = YourHour.getApp().getBoxStore().boxFor(AppInfoOfflineJsonPojo.class).getAll();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = 0;
    while (i < 21)
    {
      if (i < localList.size())
      {
        ((AppInfoOfflineJsonPojo)localList.get(i)).getDateNumber();
        ((AppInfoOfflineJsonPojo)localList.get(i)).getMonthNumber();
      }
      GoalChallengePojo localGoalChallengePojo = new GoalChallengePojo();
      localGoalChallengePojo.setTimeInMilli(localCalendar.getTimeInMillis());
      localCalendar.add(5, 1);
      localArrayList.add(localGoalChallengePojo);
      i += 1;
    }
    return localArrayList;
  }
  
  public static int getUnlockCount(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("unlockCount");
      int i = paramContext.getInt(localStringBuilder.toString(), 0);
      return i;
    }
    catch (ClassCastException localClassCastException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    paramContext = TypeSafeSharedPreference.getAll(paramContext);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("unlockCount");
    return TypeSafeSharedPreference.toInteger(paramContext.get(localStringBuilder.toString()));
  }
  
  public static ArrayList<AppInfo> getWeekAppInfo(Activity paramActivity)
    throws PackageManager.NameNotFoundException
  {
    PackageManager localPackageManager = paramActivity.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = UsageStat.getWeekUsageStatsList(paramActivity);
    List localList = UsageStat.getWeekUsageStatisticsList(paramActivity);
    HashMap localHashMap = new HashMap();
    paramActivity = ((List)localObject2).iterator();
    while (paramActivity.hasNext())
    {
      localObject2 = (AppInfo)paramActivity.next();
      localHashMap.put(((AppInfo)localObject2).getPname(), localObject2);
    }
    localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
      AppInfo localAppInfo = new AppInfo();
      paramActivity = null;
      String str = localPackageInfo.packageName;
      if ((!str.equalsIgnoreCase("com.android.systemui")) && (!str.equalsIgnoreCase("android")) && (!str.equalsIgnoreCase("com.google.android.packageinstaller")) && (localHashMap.containsKey(str)))
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          UsageStats localUsageStats = (UsageStats)localIterator.next();
          if (localUsageStats.getPackageName().equals(str))
          {
            UsageInstanceDailyDetailPojo localUsageInstanceDailyDetailPojo = new UsageInstanceDailyDetailPojo();
            localObject1 = paramActivity;
            if (paramActivity == null) {
              localObject1 = new ArrayList();
            }
            localUsageInstanceDailyDetailPojo.setDate(convertMillisToDate(localUsageStats.getLastTimeStamp()));
            localUsageInstanceDailyDetailPojo.setTotalTimeInForeground(localUsageStats.getTotalTimeInForeground());
            ((ArrayList)localObject1).add(localUsageInstanceDailyDetailPojo);
            paramActivity = (Activity)localObject1;
          }
        }
        long l = ((AppInfo)localHashMap.get(str)).getTotalAppForgroundTime();
        int i = ((AppInfo)localHashMap.get(str)).getAppLaunchCount();
        localAppInfo.setAppRunTime(convertMilliToTime(l));
        localAppInfo.setTotalAppForgroundTime(l);
        localAppInfo.setUsageInstanceDailyDetailPojoList(paramActivity);
        paramActivity = localPackageInfo.applicationInfo.loadIcon(localPackageManager);
        localAppInfo.setPname(str);
        localAppInfo.setAppname((String)localPackageInfo.applicationInfo.loadLabel(localPackageManager));
        localAppInfo.setIcon(paramActivity);
        localAppInfo.setAppLaunchCount(i);
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  public static List<WeekDataPojo> getWeekData(Context paramContext, List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      WeekDataPojo localWeekDataPojo = new WeekDataPojo();
      localWeekDataPojo.setNoOfUnlock(getUnlockCount(paramContext, (String)paramList.get(i)));
      i += 1;
      localWeekDataPojo.setDayOfWeek(i);
      localArrayList.add(localWeekDataPojo);
    }
    return localArrayList;
  }
  
  public static String getYesterday()
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).add(5, -1);
    localObject = ((Calendar)localObject).getTime();
    return new SimpleDateFormat("EEEE").format((Date)localObject);
  }
  
  public static String getYesterdayDate()
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).add(5, -1);
    localObject = ((Calendar)localObject).getTime();
    return new SimpleDateFormat("dd-MM").format((Date)localObject);
  }
  
  public static String getYesterdayUsageTime(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      String str = paramContext.getString("YesterdayUsageTime", "");
      return str;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toString(TypeSafeSharedPreference.getAll(paramContext).get("YesterdayUsageTime"));
  }
  
  public static boolean hasPermissions(Context paramContext, String... paramVarArgs)
  {
    if ((paramContext != null) && (paramVarArgs != null))
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        if (ActivityCompat.checkSelfPermission(paramContext, paramVarArgs[i]) != 0) {
          return false;
        }
        i += 1;
      }
    }
    return true;
  }
  
  public static boolean isFirstTime(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    try
    {
      boolean bool = paramContext.getBoolean("isFirstTime", true);
      return bool;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    return TypeSafeSharedPreference.toBoolean(TypeSafeSharedPreference.getAll(paramContext).get("isFirstTime"));
  }
  
  public static boolean isFirstTimeGoalChallengeIntro(Activity paramActivity)
  {
    return paramActivity.getSharedPreferences("mobilePePreference", 0).getBoolean("goalChallengeIsFirstTimeIntro", true);
  }
  
  public static boolean isGoalChallengeStart(Context paramContext)
  {
    return paramContext.getSharedPreferences("mobilePePreference", 0).getBoolean("isGoalChallengeStart", false);
  }
  
  public static boolean isInternetAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null);
  }
  
  public static boolean isMyServiceRunning(Class<?> paramClass, Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      paramContext = paramContext.getRunningServices(Integer.MAX_VALUE).iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.next();
        if (paramClass.getName().equals(localRunningServiceInfo.service.getClassName())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isUsageStatPermission(Context paramContext)
  {
    boolean bool = false;
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
      paramContext = (AppOpsManager)paramContext.getSystemService("appops");
      int i = -1;
      if (paramContext != null) {
        i = paramContext.checkOpNoThrow("android:get_usage_stats", localApplicationInfo.uid, localApplicationInfo.packageName);
      }
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean needsOverlayPermission(Context paramContext)
  {
    return (Build.VERSION.SDK_INT >= 23) && (!Settings.canDrawOverlays(paramContext));
  }
  
  public static void setAppUsageTimeLimit(Context paramContext, long paramLong, String paramString)
  {
    paramContext.getSharedPreferences("mobilePePreference", 0).edit().putLong(paramString, paramLong).apply();
  }
  
  public static void setCurrentAddictionCategory(Activity paramActivity, String paramString)
  {
    paramActivity = paramActivity.getSharedPreferences("mobilePePreference", 0).edit();
    paramActivity.putString("CurrentAddictionCategory", paramString);
    paramActivity.apply();
  }
  
  public static void setFirstTime(Context paramContext)
  {
    paramContext.getSharedPreferences("mobilePePreference", 0).edit().putBoolean("isFirstTime", false).apply();
  }
  
  public static void setGoalChallengeIsFirstTimeIntro(Activity paramActivity)
  {
    paramActivity = paramActivity.getSharedPreferences("mobilePePreference", 0).edit();
    paramActivity.putBoolean("goalChallengeIsFirstTimeIntro", false);
    paramActivity.apply();
  }
  
  public static void setGoalChallengeStart(Context paramContext, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("mobilePePreference", 0).edit().putBoolean("isGoalChallengeStart", paramBoolean).apply();
  }
  
  public static void setGoalChallengeStartDate(Activity paramActivity, long paramLong)
  {
    paramActivity = paramActivity.getSharedPreferences("mobilePePreference", 0).edit();
    paramActivity.putLong("GoalChallengeStartDate", paramLong);
    paramActivity.apply();
  }
  
  public static void setOverlayTimeAllow(Context paramContext, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("mobilePePreference", 0).edit().putBoolean("OverlayTimeAllow", paramBoolean).apply();
  }
  
  public static void setYesterdayUsageTime(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("mobilePePreference", 0).edit().putString("YesterdayUsageTime", paramString).apply();
  }
  
  public static void showSnackbar(View paramView, String paramString, Context paramContext)
  {
    paramView = Snackbar.make(paramView, paramString, 0);
    paramView.setActionTextColor(-1);
    paramString = paramView.getView();
    paramString.setBackgroundColor(ContextCompat.getColor(paramContext, 2131099696));
    paramString = (TextView)paramString.findViewById(2131296570);
    paramString.setTextColor(-1);
    paramString.setTextSize(15.0F);
    paramView.show();
  }
  
  public static void updateAppLaunchCount(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    paramContext.getSharedPreferences(paramString2, 0).edit().putInt(paramString1, paramInt).apply();
  }
  
  public static void updateCount(String paramString, Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("unlockCount");
    int i = paramContext.getInt(localStringBuilder.toString(), 0);
    paramContext = paramContext.edit();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("unlockCount");
    paramContext.putInt(localStringBuilder.toString(), i + 1);
    paramContext.apply();
  }
  
  public static void updateDailyUnlockCountGoal(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0).edit();
    paramContext.putInt("dailyUnlockCountGoal", paramInt);
    paramContext.apply();
  }
  
  public static void updateDailyUsageTimeGoal(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("mobilePePreference", 0).edit();
    paramContext.putString("dailyGoalValue", paramString);
    paramContext.apply();
  }
  
  public static void updateHeavyUsageGoal(Activity paramActivity, int paramInt)
  {
    paramActivity = paramActivity.getSharedPreferences("mobilePePreference", 0).edit();
    paramActivity.putInt("heavyUsageGoal", paramInt);
    paramActivity.apply();
  }
  
  public static void updateLightUsageGoal(Activity paramActivity, int paramInt)
  {
    paramActivity = paramActivity.getSharedPreferences("mobilePePreference", 0).edit();
    paramActivity.putInt("lightUsageGoal", paramInt);
    paramActivity.apply();
  }
  
  public static void updateModerateUsageGoal(Activity paramActivity, int paramInt)
  {
    paramActivity = paramActivity.getSharedPreferences("mobilePePreference", 0).edit();
    paramActivity.putInt("moderateUsageGoal", paramInt);
    paramActivity.apply();
  }
}
