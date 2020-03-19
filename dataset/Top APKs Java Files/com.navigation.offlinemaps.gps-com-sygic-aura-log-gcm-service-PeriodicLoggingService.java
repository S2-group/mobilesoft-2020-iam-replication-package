package com.sygic.aura.log.gcm.service;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.PeriodicTask.Builder;
import com.google.android.gms.gcm.TaskParams;
import com.sygic.aura.analytics.InfinarioAnalyticsLogger;
import com.sygic.aura.analytics.InfinarioAnalyticsLogger.AttributeProvider;
import com.sygic.aura.analytics.InfinarioLoggerInterface;
import com.sygic.aura.c2dm.C2DMessaging;
import com.sygic.aura.generated.features.SygicFeatures;
import com.sygic.aura.helper.CrashlyticsHelper;
import com.sygic.aura.log.SearchLogProvider;
import com.sygic.aura.tracker.DeviceSensors;
import com.sygic.aura.tracker.SygicTracker;
import com.sygic.aura.tracker.TrackerUtils;
import com.sygic.aura.tracker.model.NightlyEvent;
import com.sygic.aura.utils.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeriodicLoggingService
  extends GcmTaskService
{
  public PeriodicLoggingService() {}
  
  private void collectNightlyStatistics()
  {
    try
    {
      CrashlyticsHelper.initFabric(this);
      String str1 = Utils.getDeviceId(this);
      CrashlyticsHelper.logInfo("PeriodicLoggingService", "collecting nightly event for device: " + str1);
      String str2 = C2DMessaging.getRegistrationId(this);
      long l = TrackerUtils.getFreeDiskSpaceInMegabytes(this);
      Object localObject4 = getPackageManager();
      Object localObject5 = ((PackageManager)localObject4).getInstalledApplications(128);
      int i;
      if ((localObject5 != null) && (((List)localObject5).size() > 0))
      {
        localObject1 = new String[((List)localObject5).size()];
        i = 0;
        for (;;)
        {
          localObject3 = localObject1;
          if (i >= ((List)localObject5).size()) {
            break;
          }
          localObject1[i] = ((ApplicationInfo)((List)localObject5).get(i)).packageName;
          i += 1;
        }
      }
      Object localObject3 = null;
      Object localObject1 = PreferenceManager.getDefaultSharedPreferences(this).getString("installed_maps", null);
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = ((String)localObject1).split(";");
        localObject5 = DeviceSensors.getAllSensors((SensorManager)getSystemService("sensor"));
        final boolean bool = ((PackageManager)localObject4).hasSystemFeature("android.hardware.location.gps");
        InfinarioAnalyticsLogger.getInstance(this).track("Nightly", new InfinarioAnalyticsLogger.AttributeProvider()
        {
          public void fillAttributes(Map<String, Object> paramAnonymousMap)
          {
            Object localObject = PeriodicLoggingService.this;
            paramAnonymousMap.put("total space (mb)", Long.valueOf(TrackerUtils.getTotalDiskSpaceInMegabytes((Context)localObject)));
            paramAnonymousMap.put("free space (mb)", Long.valueOf(TrackerUtils.getFreeDiskSpaceInMegabytes((Context)localObject)));
            paramAnonymousMap.put("installed maps", this.val$installedMaps);
            paramAnonymousMap.put("installed apps", this.val$installedPackageNames);
            paramAnonymousMap.put("gps module", Boolean.valueOf(bool));
            localObject = this.val$deviceSensors.entrySet().iterator();
            while (((Iterator)localObject).hasNext())
            {
              Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
              paramAnonymousMap.put("available_sensor_" + (String)localEntry.getKey(), localEntry.getValue());
            }
          }
        });
        if (SygicFeatures.FEATURE_NIGHTLY_STATISTICS_TO_SYGIC_SERVER)
        {
          localObject3 = getContentResolver().query(SearchLogProvider.SEARCH_URI, null, null, null, "time ASC");
          localObject4 = new JSONArray();
          if ((localObject3 != null) && (((Cursor)localObject3).moveToFirst())) {
            do
            {
              localObject5 = new JSONObject();
              Object localObject6 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("status"));
              Object localObject7 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("params"));
              String str3 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("results"));
              int k = ((Cursor)localObject3).getInt(((Cursor)localObject3).getColumnIndex("canceled"));
              i = ((Cursor)localObject3).getInt(((Cursor)localObject3).getColumnIndex("transaction_id"));
              try
              {
                ((JSONObject)localObject5).put("session_id", i);
                ((JSONObject)localObject5).put("session_status", localObject6);
                localObject6 = new JSONArray();
                localObject7 = ((String)localObject7).split(":");
                i = 0;
                for (int j = 0 + 1; j < localObject7.length; j = i + 1)
                {
                  JSONObject localJSONObject = new JSONObject();
                  localJSONObject.put(localObject7[i], localObject7[j]);
                  ((JSONArray)localObject6).put(localJSONObject);
                  i += 2;
                }
                ((JSONObject)localObject5).put("params", localObject6);
                ((JSONObject)localObject5).put("results", new JSONObject(str3).getJSONArray("results"));
                ((JSONObject)localObject5).put("canceled", k);
              }
              catch (JSONException localJSONException)
              {
                for (;;)
                {
                  CrashlyticsHelper.logException("PeriodicLoggingService", "Error processing logs", localJSONException, false);
                }
              }
              ((JSONArray)localObject4).put(localObject5);
            } while (((Cursor)localObject3).moveToNext());
          }
          if (localObject3 != null) {
            ((Cursor)localObject3).close();
          }
          getContentResolver().delete(SearchLogProvider.SEARCH_URI, null, null);
          SygicTracker.get(this).sendEvent(new NightlyEvent(str1, str2, l, (JSONArray)localObject4, (String[])localObject1));
          SygicTracker.get(this).forceDispatch();
        }
        return;
      }
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      for (;;)
      {
        continue;
        Object localObject2 = null;
      }
    }
  }
  
  public static void setupNightlyAlarm(Context paramContext)
  {
    int i = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(paramContext);
    switch (i)
    {
    default: 
      CrashlyticsHelper.logInfo("PeriodicLoggingService", "GooglePlayServices connection successful with result: " + i);
      PeriodicTask localPeriodicTask = new PeriodicTask.Builder().setService(PeriodicLoggingService.class).setPeriod(86400L).setFlex(1800L).setTag("GCMTask | 789:86400s").setRequiresCharging(true).setRequiredNetwork(1).build();
      GcmNetworkManager.getInstance(paramContext).schedule(localPeriodicTask);
      return;
    }
    CrashlyticsHelper.logException("PeriodicLoggingService", "GooglePlayServices connection failed with result: " + i, new IllegalStateException("GooglePlayServices not available"));
  }
  
  public void onInitializeTasks()
  {
    super.onInitializeTasks();
    setupNightlyAlarm(this);
  }
  
  public int onRunTask(TaskParams paramTaskParams)
  {
    if ("GCMTask | 789:86400s".equals(paramTaskParams.getTag()))
    {
      collectNightlyStatistics();
      return 0;
    }
    return 2;
  }
}
