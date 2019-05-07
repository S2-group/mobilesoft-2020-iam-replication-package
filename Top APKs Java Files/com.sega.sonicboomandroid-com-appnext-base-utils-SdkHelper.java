package com.appnext.base.utils;

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
import com.appnext.base.ServicesStart;
import com.appnext.base.Wrapper;
import com.appnext.base.database.DatabaseFactory;
import com.appnext.base.database.models.CollectedDataModel;
import com.appnext.base.database.models.ConfigDataModel;
import com.appnext.base.database.repo.CollectedDataRepo;
import com.appnext.base.database.repo.ConfigDataRepo;
import com.appnext.base.operations.CollectedDataOperation;
import com.appnext.base.operations.OperationsManager;
import com.appnext.base.operations.imp.cdm;
import com.appnext.base.operations.imp.rcd;
import com.appnext.base.receivers.IMonitoring;
import com.appnext.base.services.OperationService;
import com.appnext.base.services.ReceiverService;
import com.appnext.core.AppnextHelperClass;
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

public class SdkHelper
{
  private static final long DAY_AS_MS = 86400000L;
  private static final long HOUR_AS_MS = 3600000L;
  private static final long MIN_AS_MS = 60000L;
  private static final long MS = 1000L;
  private static final String TAG = "SdkHelper";
  public static final int TWO_MINUTES = 120000;
  private static Random mRandom = new Random();
  
  public SdkHelper() {}
  
  private static void appendNumber(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
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
  
  public static long calculateLocationStartTime(int paramInt1, int paramInt2)
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
  
  public static long calculateLocationStartTimeWithRandom(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return calculateLocationStartTime(paramInt1, paramInt2) + (mRandom.nextInt(paramInt4 + paramInt3) - paramInt3) * 1000L;
  }
  
  public static long calculateTimeInMilis(String paramString1, String paramString2)
  {
    try
    {
      if ((TextUtils.isDigitsOnly(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (!TextUtils.isEmpty(paramString1)))
      {
        long l = Long.parseLong(paramString1);
        if ("time".equalsIgnoreCase(paramString2))
        {
          if (paramString1.length() == 4) {
            return calculateLocationStartTimeWithRandom(Integer.parseInt(paramString1.substring(0, 2)), Integer.parseInt(paramString1.substring(2, 4)), 60, 60);
          }
        }
        else
        {
          if ("second".equalsIgnoreCase(paramString2)) {
            return l * 1000L;
          }
          if ("minute".equalsIgnoreCase(paramString2)) {
            return l * 60000L;
          }
          if ("hour".equalsIgnoreCase(paramString2)) {
            return l * 3600000L;
          }
          boolean bool = "day".equalsIgnoreCase(paramString2);
          if (bool) {
            return l * 86400000L;
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
      Wrapper.logException(paramString1);
      return -1L;
    }
    return -1L;
  }
  
  public static boolean checkLimitAdTrackingEnabled(Context paramContext)
    throws Exception
  {
    paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
    return (paramContext != null) && (paramContext.isLimitAdTrackingEnabled());
  }
  
  public static void collectDataOperation(String paramString1, String paramString2, Constants.DATA_TYPE paramDATA_TYPE)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new CollectedDataModel(paramString1, paramString2, paramDATA_TYPE.getType()));
    collectDataOperation(paramString1, localArrayList);
  }
  
  public static void collectDataOperation(String paramString, List<CollectedDataModel> paramList)
  {
    Intent localIntent = new Intent(ContextUtil.getContext(), OperationService.class);
    localIntent.putExtra("config_data_obj", paramString);
    paramString = CollectedDataUtils.convertCollectedDataListToJsonArray(paramList, false);
    if (paramString != null)
    {
      if (paramString.length() == 0) {
        return;
      }
      localIntent.putExtra("data", paramString.toString());
      localIntent.putExtra("action", rcd.class.getSimpleName());
      startService(ContextUtil.getContext(), localIntent);
      return;
    }
  }
  
  public static Location constructLocation(double paramDouble1, double paramDouble2, float paramFloat)
  {
    Location localLocation = new Location("");
    localLocation.setLatitude(paramDouble1);
    localLocation.setLongitude(paramDouble2);
    localLocation.setAccuracy(paramFloat);
    return localLocation;
  }
  
  public static Object convertData(String paramString, Constants.DATA_TYPE paramDATA_TYPE)
  {
    try
    {
      switch (1.$SwitchMap$com$appnext$base$utils$Constants$DATA_TYPE[paramDATA_TYPE.ordinal()])
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
  
  public static String createFormattedDate(Date paramDate)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(paramDate));
    localStringBuilder.append(" ");
    localStringBuilder.append(createFormattedTimezone());
    localStringBuilder.append(" ");
    localStringBuilder.append(new SimpleDateFormat("yyyy", Locale.US).format(paramDate));
    return localStringBuilder.toString();
  }
  
  public static String createFormattedTimezone()
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
    appendNumber((StringBuilder)localObject, 2, i / 60);
    ((StringBuilder)localObject).append(':');
    appendNumber((StringBuilder)localObject, 2, i % 60);
    return ((StringBuilder)localObject).toString();
  }
  
  public static Object fetch(String paramString, Constants.DATA_TYPE paramDATA_TYPE)
  {
    try
    {
      paramString = DatabaseFactory.getInstance().getCollectedDataRepo().fetchByType(paramString);
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        paramString = convertData(((CollectedDataModel)paramString.get(0)).getCollectedData(), paramDATA_TYPE);
        return paramString;
      }
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static List<String> getForegroundApp(Context paramContext)
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
          if (!DataUtils.appHasPermission(ContextUtil.getContext(), "android.permission.GET_TASKS")) {
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
          Wrapper.logException(paramContext);
          return localArrayList;
        }
      }
      else if ((Build.VERSION.SDK_INT >= 21) && (usageAccessGranted(paramContext.getApplicationContext())))
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
      Wrapper.logException(paramContext);
    }
    label332:
    return localArrayList;
  }
  
  public static List<ApplicationInfo> getInstalledApps(PackageManager paramPackageManager, int paramInt)
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
  
  public static String getLastHashData(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("_hash");
    paramString = localStringBuilder.toString();
    return LibrarySettings.getInstance().getString(paramString, null);
  }
  
  public static long getLastUpdate(String paramString)
  {
    LibrarySettings localLibrarySettings = LibrarySettings.getInstance();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("_lastupdate");
    return localLibrarySettings.getLong(localStringBuilder.toString(), 0L);
  }
  
  public static void getRa()
  {
    Object localObject = (ActivityManager)ContextUtil.getContext().getSystemService("activity");
    ContextUtil.getContext().getPackageManager();
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
  
  public static boolean hasPermission(String paramString1, String paramString2, ConfigDataModel paramConfigDataModel)
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
      Wrapper.logException(paramString1);
      return false;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.getCause().printStackTrace();
      Wrapper.logException(paramString1.getCause());
      return false;
    }
    catch (ClassNotFoundException paramString1)
    {
      return false;
    }
    return ((IMonitoring)Class.forName(ReceiverService.getOperationClassName(paramString1)).getConstructor(new Class[0]).newInstance(new Object[0])).hasPermission();
    bool = ((CollectedDataOperation)Class.forName(OperationsManager.getOperationClassName(paramString1)).getConstructor(new Class[] { ConfigDataModel.class, Bundle.class }).newInstance(new Object[] { paramConfigDataModel, null })).hasPermission();
    return bool;
    switch (i)
    {
    }
    return false;
  }
  
  public static void insert(String paramString1, String paramString2, Constants.DATA_TYPE paramDATA_TYPE)
  {
    DatabaseFactory.getInstance().getCollectedDataRepo().insertOrUpdate(new CollectedDataModel(paramString1, paramString2, paramDATA_TYPE.getType()));
  }
  
  public static boolean isLimitAdTrackingEnabled(Context paramContext)
  {
    boolean bool = true;
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      if (paramContext != null)
      {
        bool = paramContext.isLimitAdTrackingEnabled();
        if (bool) {
          return true;
        }
        bool = false;
      }
      return bool;
    }
    catch (Throwable paramContext)
    {
      Wrapper.logException(paramContext);
      SdkLog.e("SdkHelper", paramContext.toString());
    }
    return true;
  }
  
  public static boolean isServiceExist(Class paramClass)
  {
    try
    {
      int i = ContextUtil.getContext().getPackageManager().queryIntentServices(new Intent(ContextUtil.getContext(), paramClass), 65536).size();
      return i > 0;
    }
    catch (Throwable paramClass)
    {
      AppnextHelperClass.printStackTrace(paramClass);
    }
    return false;
  }
  
  public static void restartAlarm(Context paramContext, Class paramClass, long paramLong, ConfigDataModel paramConfigDataModel)
  {
    if ((paramContext != null) && (paramConfigDataModel != null))
    {
      if (paramClass == null) {
        return;
      }
      long l;
      if ("time".equalsIgnoreCase(paramConfigDataModel.getSampleType())) {
        l = 86400000L;
      } else {
        l = calculateTimeInMilis(paramConfigDataModel.getSample(), paramConfigDataModel.getSampleType());
      }
      if (l == -1L) {
        return;
      }
      AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
      paramClass = new Intent(paramContext, paramClass);
      paramClass.putExtra("config_data_obj", paramConfigDataModel.getKey());
      int i = paramConfigDataModel.getServiceKey().hashCode();
      SdkLog.d(" *** alarm *** ", String.valueOf(paramConfigDataModel.getServiceKey()));
      paramContext = PendingIntent.getService(paramContext, i, paramClass, 134217728);
      if (paramConfigDataModel.isExact())
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
      return;
    }
  }
  
  public static void saveLastHashData(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("_hash");
    paramString1 = localStringBuilder.toString();
    LibrarySettings.getInstance().putString(paramString1, paramString2);
  }
  
  public static boolean sendSyncData(String paramString, Map<String, String> paramMap)
  {
    Object localObject1 = DataLayerManager.getInstance().getConfigDataModelByKey(paramString);
    if (localObject1 != null)
    {
      if ("off".equalsIgnoreCase(((ConfigDataModel)localObject1).getStatus())) {
        return true;
      }
      if (paramMap.isEmpty()) {
        return true;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(Constants.getServerUrl());
      ((StringBuilder)localObject1).append("/data");
      String str = ((StringBuilder)localObject1).toString();
      HashMap localHashMap = new HashMap();
      Object localObject3 = AppnextHelperClass.getAdsID(ContextUtil.getContext());
      localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3)) {
        localObject1 = LibrarySettings.getInstance().getString("google_ads_id", "");
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
      localHashMap.put("lvid", "4.6.5");
      try
      {
        localHashMap.put("localdate", createFormattedDate(new Date()));
        localHashMap.put("timezone", createFormattedTimezone());
        localHashMap.put("app_package", ContextUtil.getPackageName());
      }
      catch (Throwable localThrowable)
      {
        Wrapper.logException(localThrowable);
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
      SdkLog.d(paramString, paramMap.toString());
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
        SdkLog.d(paramString, ((StringBuilder)localObject3).toString());
      }
      try
      {
        paramMap = AppnextHelperClass.performURLCall(str, localHashMap, false, 15000);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("result send data: ");
        ((StringBuilder)localObject2).append(paramMap);
        SdkLog.d(paramString, ((StringBuilder)localObject2).toString());
        return true;
      }
      catch (Throwable paramMap)
      {
        paramMap = paramMap.getMessage();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("(Type:Throwable) ");
        ((StringBuilder)localObject2).append(paramMap);
        SdkLog.e(paramString, ((StringBuilder)localObject2).toString());
        return false;
      }
      catch (HttpRetryException paramMap)
      {
        int i = paramMap.responseCode();
        paramMap = paramMap.getMessage();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("(Type:HttpRetryException)");
        ((StringBuilder)localObject2).append(paramMap);
        ((StringBuilder)localObject2).append("  ");
        ((StringBuilder)localObject2).append(i);
        SdkLog.e(paramString, ((StringBuilder)localObject2).toString());
        return false;
      }
    }
    return true;
  }
  
  public static void startConfigManagerService(Context paramContext)
  {
    try
    {
      Object localObject = DatabaseFactory.getInstance().getConfigDataRepo().fetch();
      if ((localObject != null) && (((List)localObject).size() == 0))
      {
        localObject = new JSONObject("{ \"status\": \"on\", \"sample\": \"1\", \"sample_type\": \"hour\", \"cycle\": \"1\", \"cycle_type\": \"interval\", \"exact\": \"false\", \"key\": \"cdm\" }");
        DatabaseFactory.getInstance().getConfigDataRepo().insertOrUpdate((JSONObject)localObject);
        localObject = new Intent(paramContext, OperationService.class);
        ((Intent)localObject).putExtra("config_data_obj", cdm.class.getSimpleName());
        startService(paramContext, (Intent)localObject);
        return;
      }
      paramContext = DatabaseFactory.getInstance().getConfigDataRepo().fetch();
      ServicesStart.getInstance().stopAllServices(paramContext);
      ServicesStart.getInstance().startServices();
      return;
    }
    catch (Throwable paramContext)
    {
      Wrapper.logException(paramContext);
    }
  }
  
  public static void startService(Context paramContext, Intent paramIntent)
  {
    try
    {
      paramContext.startService(paramIntent);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  @TargetApi(21)
  public static boolean usageAccessGranted(Context paramContext)
  {
    return ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), paramContext.getPackageName()) == 0;
  }
}
