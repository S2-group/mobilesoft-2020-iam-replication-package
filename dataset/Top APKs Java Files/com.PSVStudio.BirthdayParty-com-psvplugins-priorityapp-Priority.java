package com.psvplugins.priorityapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.psvplugins.notification.Notification;
import com.psvplugins.notification.NotificationInfo;
import com.unity3d.player.UnityPlayer;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority
{
  public static final String ACTION_ON_CHECK_PRIORITY = "com.psvplugins.notification.CHECK_PRIORITY";
  public static final String ACTION_OPEN_NOTIFY = "NotificationPushAds";
  public static final int AMOUNT_DAYS_IN_WEEK = 7;
  public static final int ARE_NOT_POPULAR = -1;
  public static final String DATA_STORED_VERSION_PREF = "PriorityLogicVersion";
  public static final String EXTRA_INSTALLED_APP_LIST_DATA = "InstalledAppList";
  public static final String EXTRA_POPULARITY_DATA = "PopularityDataInfo";
  public static final String EXTRA_SKIP_BUNDLE_ON_CHANGE = "SkipBundle";
  public static final String LAST_NOTIFY_TITLE_PREF = "LastNotifyHashTitle";
  public static final String LOG_TAG = "PSV_Priority_App";
  public static final byte MODE_SCHEDULE_COUNT_INSTALLED = 2;
  public static final byte MODE_SCHEDULE_CURRENT_DAY = 1;
  public static final byte MODE_SCHEDULE_DAY_OF_WEEK = 0;
  public static final String NOTIFY_BACKGROUND_PREF = "PopNotifyStyle";
  public static final String NOTIFY_MESSAGE_PREF = "PopNotifyStyle";
  public static final String NOTIFY_STYLE_PREF = "PopNotifyStyle";
  public static final String NOTIFY_TITLE_PREF = "PopNotifyStyle";
  public static final int NOT_EXIST_IN_LIST = -5;
  public static final int NOT_FOUND_LIST = -10;
  public static final String POPULARITY_DATA_FILE_NAME = "LocalPriorityGames.json";
  public static final String PRIORITY_POSITION_PREF = "PriorityPositionSelfBundle";
  public static final String SELECTED_LANGUAGE_PREF = "SelectedLanguage";
  private String bundleName;
  private Context context;
  private int countInstalledPSVGames;
  private String[] installedApps;
  private byte modeScheduleNotify = 2;
  private PopularityData popularityData;
  private int priorityListCountBetter;
  private int priorityPosition;
  private long[] timeWeeklySchedule = new long[0];
  
  public Priority()
  {
    this(UnityPlayer.currentActivity);
  }
  
  public Priority(Context paramContext)
  {
    this.context = paramContext;
    this.bundleName = paramContext.getPackageName();
  }
  
  private static boolean equals(String paramString1, String paramString2)
  {
    return (paramString1.length() == paramString2.length()) && (paramString1.equals(paramString2));
  }
  
  public static void setSelectedLanguage(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(UnityPlayer.currentActivity).edit().putString("SelectedLanguage", paramString).apply();
  }
  
  public void buildNotification()
  {
    if (this.priorityPosition == -1) {
      this.timeWeeklySchedule = new long[0];
    }
    for (;;)
    {
      return;
      if ((this.priorityPosition < 1) || (this.priorityPosition > 7))
      {
        Log.e("PSV_Priority_App", "setTimeWeeklySchedule support only Calendar CONST days 1 - 7 value.");
        this.timeWeeklySchedule = new long[0];
        return;
      }
      if (this.popularityData == null)
      {
        Log.e("PSV_Priority_App", "Not found saved WeeklySchedule! Please call setWeeklySchedule() before getTargetTimeShow() method.");
        this.timeWeeklySchedule = new long[0];
        return;
      }
      if (this.modeScheduleNotify == 0)
      {
        localObject1 = new int[1];
        localObject1[0] = this.priorityPosition;
      }
      int i;
      int j;
      for (;;)
      {
        this.timeWeeklySchedule = new long[localObject1.length];
        i = 0;
        while (i < localObject1.length)
        {
          localObject2 = Calendar.getInstance();
          j = ((Calendar)localObject2).get(7);
          if (j != localObject1[i]) {
            ((Calendar)localObject2).add(6, (7 - j + localObject1[i]) % 7);
          }
          ((Calendar)localObject2).set(11, 0);
          ((Calendar)localObject2).clear(12);
          ((Calendar)localObject2).clear(13);
          ((Calendar)localObject2).clear(14);
          this.timeWeeklySchedule[i] = (((Calendar)localObject2).getTimeInMillis() + this.popularityData.getTime(localObject1[i]));
          i += 1;
        }
        if (this.modeScheduleNotify == 2)
        {
          if (this.countInstalledPSVGames < 1)
          {
            this.timeWeeklySchedule = new long[0];
            return;
          }
          localObject2 = this.popularityData.getDays(this.priorityPosition, this.countInstalledPSVGames);
          localObject1 = localObject2;
          if (localObject2.length == 0) {
            this.timeWeeklySchedule = new long[0];
          }
        }
        else
        {
          localObject2 = Calendar.getInstance();
          localObject1 = new int[1];
          localObject1[0] = ((Calendar)localObject2).get(7);
        }
      }
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
      Object localObject2 = "";
      Object localObject1 = "";
      boolean bool = localSharedPreferences.contains("PopNotifyStyle");
      if (bool)
      {
        localObject2 = localSharedPreferences.getString("PopNotifyStyle", "");
        localObject1 = localSharedPreferences.getString("PopNotifyStyle", "");
      }
      Object localObject4;
      Object localObject3;
      if (bool)
      {
        localObject4 = localObject1;
        localObject3 = localObject2;
        if (((String)localObject2).length() == 0)
        {
          localObject4 = localObject1;
          localObject3 = localObject2;
          if (((String)localObject1).length() != 0) {}
        }
      }
      else
      {
        localObject1 = localSharedPreferences.getString("SelectedLanguage", "English");
        int k = 0;
        i = 0;
        j = k;
        if (i < this.popularityData.languages.length)
        {
          if (!equals(this.popularityData.languages[i], (String)localObject1)) {
            break label609;
          }
          j = i;
        }
        localObject3 = this.popularityData.localTitle[j];
        localObject4 = this.popularityData.localMessage[j];
      }
      try
      {
        localObject1 = new NotificationInfo();
        ((NotificationInfo)localObject1).title = ((String)localObject3);
        localSharedPreferences.edit().putString("LastNotifyHashTitle", ((NotificationInfo)localObject1).title).apply();
        ((NotificationInfo)localObject1).message = ((String)localObject4);
        ((NotificationInfo)localObject1).intentInfo = "NotificationPushAds";
        ((NotificationInfo)localObject1).styleId = localSharedPreferences.getInt("PopNotifyStyle", 2);
        ((NotificationInfo)localObject1).backgroundName = localSharedPreferences.getString("PopNotifyStyle", "");
        ((NotificationInfo)localObject1).repeatRate = TimeUnit.DAYS.toMillis(7L);
        i = 0;
        while (i < this.timeWeeklySchedule.length)
        {
          if (this.timeWeeklySchedule[i] > 0L)
          {
            ((NotificationInfo)localObject1).targetTime = this.timeWeeklySchedule[i];
            Notification.initNotification(this.context, (NotificationInfo)localObject1, true);
          }
          i += 1;
        }
        label609:
        i += 1;
      }
      catch (Exception localException)
      {
        Log.e("PSV_Priority_App", "buildNotification ERROR! Bundle: " + this.context.getPackageName(), localException);
      }
    }
  }
  
  public void callCheckAllApplications()
  {
    Intent localIntent = new Intent("com.psvplugins.notification.CHECK_PRIORITY");
    localIntent.putExtra("SkipBundle", this.bundleName);
    Bundle localBundle = new Bundle();
    localBundle.putSerializable("PopularityDataInfo", this.popularityData);
    localIntent.putExtra("PopularityDataInfo", localBundle);
    localIntent.putExtra("InstalledAppList", this.installedApps);
    this.context.sendBroadcast(localIntent);
  }
  
  public void cancelNotify()
  {
    try
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
      if (localSharedPreferences.contains("LastNotifyHashTitle"))
      {
        Notification.cancelNotificationByTitle(this.context, localSharedPreferences.getString("LastNotifyHashTitle", "New game"));
        localSharedPreferences.edit().remove("LastNotifyHashTitle").apply();
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e("PSV_Priority_App", "cancelNotify ERROR! Bundle: " + this.context.getPackageName(), localException);
    }
  }
  
  public void checkSelfPriorityNotify(Intent paramIntent)
  {
    if (paramIntent.getStringExtra("SkipBundle").equals(this.bundleName))
    {
      Log.i("PSV_Priority_App", "checkSelfPriorityNotify bundle Skipped! Bundle: " + this.context.getPackageName());
      return;
    }
    PopularityData localPopularityData = (PopularityData)paramIntent.getBundleExtra("PopularityDataInfo").getSerializable("PopularityDataInfo");
    if (localPopularityData == null)
    {
      Log.e("PSV_Priority_App", "checkSelfPriorityNotify PopularityData is Empty! Bundle: " + this.context.getPackageName());
      return;
    }
    if (localPopularityData.version > getStoredDataVersion()) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        localPopularityData.saveJSON(this.context);
        setStoredDataVersion(localPopularityData.version);
      }
      this.popularityData = localPopularityData;
      int j = setPriorityList();
      if ((j == -10) || (j == -5)) {
        break;
      }
      this.installedApps = paramIntent.getStringArrayExtra("InstalledAppList");
      if (this.installedApps == null)
      {
        Log.e("PSV_Priority_App", "checkSelfPriorityNotify Installed App List is Empty! Bundle: " + this.context.getPackageName());
        setInstalledApplication();
      }
      j = getLastPriorityPosition();
      this.priorityPosition = setPriorityPosition();
      if ((i == 0) && (j == this.priorityPosition)) {
        break;
      }
      cancelNotify();
      buildNotification();
      return;
    }
  }
  
  public void clearNotificationText()
  {
    PreferenceManager.getDefaultSharedPreferences(this.context).edit().remove("PopNotifyStyle").remove("PopNotifyStyle").apply();
  }
  
  public String getInstalledList()
  {
    setInstalledApplication();
    return TextUtils.join("\n", this.installedApps);
  }
  
  public int getLastPriorityPosition()
  {
    return PreferenceManager.getDefaultSharedPreferences(this.context).getInt("PriorityPositionSelfBundle", -1);
  }
  
  public String getPopularityDataPath()
  {
    return new File(this.context.getFilesDir(), "LocalPriorityGames.json").getPath();
  }
  
  public int getPriorityPostion()
  {
    return this.priorityPosition;
  }
  
  public int getStoredDataVersion()
  {
    return PreferenceManager.getDefaultSharedPreferences(this.context).getInt("PriorityLogicVersion", -1);
  }
  
  public long[] getTimeWeeklySchedule()
  {
    return this.timeWeeklySchedule;
  }
  
  public void loadPopularityData(String paramString)
  {
    PopularityData localPopularityData = new PopularityData();
    localPopularityData.parseFromJSONString(paramString);
    this.popularityData = localPopularityData;
  }
  
  public int setInstalledApplication()
  {
    int j;
    if ((this.installedApps != null) && (this.installedApps.length > 0)) {
      j = 0;
    }
    do
    {
      return j;
      for (;;)
      {
        try
        {
          localList = this.context.getPackageManager().getInstalledPackages(128);
          if (localList == null) {
            return -10;
          }
          arrayOfString = new String[localList.size()];
          i = -1;
          j = 0;
          k = localList.size();
          if (j >= k) {
            break;
          }
        }
        catch (Exception localException1)
        {
          List localList;
          String[] arrayOfString;
          Log.e("PSV_Priority_App", "setInstalledApplication ERROR! Bundle: " + this.context.getPackageName(), localException1);
          return -10;
        }
        try
        {
          arrayOfString[j] = ((PackageInfo)localList.get(j)).packageName;
          k = i;
          if (i < 0)
          {
            boolean bool = equals(arrayOfString[j], this.bundleName);
            k = i;
            if (bool) {
              k = j;
            }
          }
        }
        catch (Exception localException2)
        {
          k = i;
          continue;
        }
        j += 1;
        i = k;
      }
      this.installedApps = arrayOfString;
      j = i;
    } while (i >= 0);
    return -5;
  }
  
  public void setModeScheduleNotify(byte paramByte)
  {
    this.modeScheduleNotify = paramByte;
  }
  
  public void setNotificationStyle(int paramInt, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this.context).edit().putInt("PopNotifyStyle", paramInt).putString("PopNotifyStyle", paramString).apply();
  }
  
  public void setNotificationText(String paramString1, String paramString2)
  {
    PreferenceManager.getDefaultSharedPreferences(this.context).edit().putString("PopNotifyStyle", paramString1).putString("PopNotifyStyle", paramString2).apply();
  }
  
  public int setPriorityList()
  {
    if ((this.popularityData == null) || (this.popularityData.rating == null) || (this.popularityData.rating.length == 0))
    {
      this.priorityListCountBetter = -10;
      return -10;
    }
    int i = 0;
    while (i < this.popularityData.rating.length)
    {
      if (equals(this.bundleName, this.popularityData.rating[i]))
      {
        i += 1;
        this.priorityListCountBetter = i;
        return i;
      }
      i += 1;
    }
    this.priorityListCountBetter = -5;
    return -5;
  }
  
  public int setPriorityPosition()
  {
    if ((this.popularityData == null) || (this.popularityData.rating == null) || (this.popularityData.rating.length == 0) || (this.installedApps == null) || (this.installedApps.length == 0))
    {
      this.countInstalledPSVGames = 0;
      Log.e("PSV_Priority_App", "setPriorityPosition ERROR! EMPTY DATA! Bundle: " + this.context.getPackageName());
      return setPriorityPosition(-1);
    }
    int i = 0;
    int m = 0;
    int k = 0;
    int j;
    while (k < this.popularityData.rating.length)
    {
      j = 0;
      int n;
      int i1;
      for (;;)
      {
        n = m;
        i1 = i;
        if (j >= this.installedApps.length) {
          break;
        }
        if (equals(this.installedApps[j], this.popularityData.rating[k]))
        {
          j = i;
          if (k < this.priorityListCountBetter) {
            j = i + 1;
          }
          m += 1;
          n = m;
          i1 = j;
          if (m != 7) {
            break;
          }
          i = j;
          if (j > 7) {
            i = -1;
          }
          this.countInstalledPSVGames = m;
          return setPriorityPosition(i);
        }
        j += 1;
      }
      k += 1;
      m = n;
      i = i1;
    }
    this.countInstalledPSVGames = m;
    if (i != 0)
    {
      j = i;
      if (i <= 7) {}
    }
    else
    {
      j = -1;
    }
    return setPriorityPosition(j);
  }
  
  public int setPriorityPosition(int paramInt)
  {
    this.priorityPosition = paramInt;
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
    localEditor.putInt("PriorityPositionSelfBundle", paramInt);
    localEditor.apply();
    Log.i("PSV_Priority_App", "setPriorityPosition done position = " + paramInt + " Bundle: " + this.context.getPackageName());
    return paramInt;
  }
  
  public void setStoredDataVersion(int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(this.context).edit().putInt("PriorityLogicVersion", paramInt).apply();
  }
}
