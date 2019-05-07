package com.aerserv.sdk;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.aerserv.sdk.adapter.AdapterAdRefresher;
import com.aerserv.sdk.adapter.AdapterFactory;
import com.aerserv.sdk.http.HttpPostTask;
import com.aerserv.sdk.http.HttpTaskListener;
import com.aerserv.sdk.model.Asplc;
import com.aerserv.sdk.proxy.PreInitProxy;
import com.aerserv.sdk.proxy.SybokProxy;
import com.aerserv.sdk.utils.AerServLog;
import com.aerserv.sdk.utils.CommonUtils;
import com.aerserv.sdk.utils.ReflectionUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AerServSettings
{
  private static final String ADVERTISING_ID_KEY = "adId";
  private static final String APPS_KEYS = "apps";
  private static final String BUNDLE_ID_KEY = "bundleId";
  private static final int CONFIG_READY = 2;
  public static final String CONFIG_SERVER_URL = "https://ads.aerserv.com/as/sdk/configure/";
  private static final String GET_DEVICE_INFO_KEY = "update";
  private static final String HTTP_TIMEOUT_KEY = "requestTimeout";
  private static final int INIT_ADAPTER = 3;
  private static final int INIT_DONE = 4;
  private static final String INSTALLED_KEY = "installed";
  private static final int LOADING = 1;
  private static final String LOG_TAG = AerServSettings.class.getSimpleName();
  private static final int NOT_INIT = 0;
  private static final int PRE_INIT_TIMEOUT_DEFAULT = 5000;
  private static AtomicInteger RETRY_COUNTDOWN = new AtomicInteger(1);
  private static AtomicInteger STATE = new AtomicInteger(0);
  private static final int STEP1_TIMEOUT_DEFAULT = 3000;
  private static final int STEP2_TIMEOUT_DEFAULT = 6000;
  private static final int STEP3_TIMEOUT_DEFAULT = 11000;
  private static final int STEP4_TIMEOUT_DEFAULT = 8000;
  private static final String UNINSTALLED_KEY = "uninstalled";
  private static final String UPDATE_SERVER_URL = "https://dmp.aerserv.com/update";
  private static final String VPAID_KEY = "vpaid";
  private static String applicationId;
  private static Boolean centralLoggingEnabled;
  private static Integer centralLoggingLineCount;
  private static Boolean centralLoggingSendStackTrace;
  private static JSONObject configJson = null;
  private static int httpTimeout;
  private static Boolean isAnalyticsEnabled = null;
  private static final String sessionId;
  private static String siteId;
  private static Integer step1Timeout;
  private static Integer step2Timeout;
  private static Integer step3Timeout;
  private static Integer step4Timeout;
  
  static
  {
    centralLoggingEnabled = null;
    centralLoggingLineCount = null;
    centralLoggingSendStackTrace = null;
    httpTimeout = 6000;
    step1Timeout = null;
    step2Timeout = null;
    step3Timeout = null;
    step4Timeout = null;
    siteId = null;
    applicationId = null;
    sessionId = UUID.randomUUID().toString();
  }
  
  public AerServSettings() {}
  
  private static void checkDependency(String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (!ReflectionUtils.canFindClass(str)) {
        throw new IllegalStateException("Could not find library: " + str + ".");
      }
      i += 1;
    }
  }
  
  public static String getApplicationId()
  {
    return applicationId;
  }
  
  public static boolean getCentralLoggingEnabled()
  {
    if (centralLoggingEnabled != null) {
      return centralLoggingEnabled.booleanValue();
    }
    return true;
  }
  
  public static int getCentralLoggingLineCount()
  {
    if (centralLoggingLineCount != null) {
      return centralLoggingLineCount.intValue();
    }
    return 500;
  }
  
  public static boolean getCentralLoggingSendStackTrace()
  {
    if (centralLoggingSendStackTrace != null) {
      return centralLoggingSendStackTrace.booleanValue();
    }
    return true;
  }
  
  private static void getDeviceInfo(Activity paramActivity)
  {
    final JSONArray localJSONArray = configJson.optJSONArray("update");
    if ((localJSONArray == null) || (localJSONArray.length() == 0))
    {
      AerServLog.v(LOG_TAG, "No device info fields to update.");
      return;
    }
    new Thread(new Runnable()
    {
      private JSONObject getBundleInfo(PackageInfo paramAnonymousPackageInfo)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("bundleId", paramAnonymousPackageInfo.applicationInfo.packageName);
        return localJSONObject;
      }
      
      private JSONObject getDeviceInfo(JSONObject paramAnonymousJSONObject)
      {
        AerServLog.v(AerServSettings.LOG_TAG, "Getting device information.");
        JSONArray localJSONArray = new JSONArray();
        Iterator localIterator = getPackages().iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
            localJSONArray.put(getBundleInfo(localPackageInfo));
          }
        }
        paramAnonymousJSONObject.put("installed", localJSONArray);
        return paramAnonymousJSONObject;
      }
      
      private List<PackageInfo> getPackages()
      {
        return this.val$activity.getPackageManager().getInstalledPackages(0);
      }
      
      public void run()
      {
        for (;;)
        {
          String str;
          JSONObject localJSONObject;
          int i;
          try
          {
            AerServSettings.checkDependency(new String[] { "com.google.android.gms.ads.identifier.AdvertisingIdClient", "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info" });
            Object localObject = AdvertisingIdClient.getAdvertisingIdInfo(this.val$activity);
            if (((AdvertisingIdClient.Info)localObject).isLimitAdTrackingEnabled())
            {
              AerServLog.v(AerServSettings.LOG_TAG, "Updating information is disabled.");
              return;
            }
            str = ((AdvertisingIdClient.Info)localObject).getId();
            localJSONObject = new JSONObject();
            HashSet localHashSet = new HashSet();
            i = 0;
            if (i < localJSONArray.length())
            {
              if (localJSONArray.isNull(i))
              {
                localObject = null;
                if ((TextUtils.isEmpty((CharSequence)localObject)) || (localHashSet.contains(localObject))) {
                  break label257;
                }
                localHashSet.add(localObject);
                if (((String)localObject).equals("apps"))
                {
                  AerServLog.v(AerServSettings.LOG_TAG, "Updating device informations for apps.");
                  localObject = new JSONObject();
                  getDeviceInfo((JSONObject)localObject);
                  localJSONObject.put("apps", localObject);
                  break label257;
                }
              }
              else
              {
                localObject = localJSONArray.optString(i);
                continue;
              }
              AerServLog.i(AerServSettings.LOG_TAG, "Unsupported field to update: " + (String)localObject);
            }
          }
          catch (Exception localException)
          {
            AerServLog.i(AerServSettings.LOG_TAG, "Error getting device information.", localException);
            return;
          }
          if (localJSONObject.length() > 0)
          {
            localJSONObject.put("adId", str);
            new HttpPostTask("https://dmp.aerserv.com/update", localJSONObject.toString(), AerServSettings.httpTimeout).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
          }
          return;
          label257:
          i += 1;
        }
      }
    }).start();
  }
  
  public static int getHttpTimeout()
  {
    return httpTimeout;
  }
  
  public static int getInitTimeout()
  {
    return 5000;
  }
  
  public static int getLoadAdTimeout()
  {
    return Math.max(1000, getStep1Timeout() + getStep2Timeout() + getStep3Timeout());
  }
  
  public static int getLoadAdTimeout(String paramString)
  {
    readConfig(paramString);
    return getLoadAdTimeout();
  }
  
  public static String getSessionId()
  {
    return sessionId;
  }
  
  public static String getSiteId()
  {
    return siteId;
  }
  
  public static int getStep1Timeout()
  {
    if (step1Timeout != null) {}
    for (int i = step1Timeout.intValue();; i = 3000) {
      return Math.max(1000, i);
    }
  }
  
  public static int getStep2Timeout()
  {
    if (step2Timeout != null) {}
    for (int i = step2Timeout.intValue();; i = 6000) {
      return Math.max(1000, i);
    }
  }
  
  public static int getStep3Timeout()
  {
    if (step3Timeout != null) {}
    for (int i = step3Timeout.intValue();; i = 11000) {
      return Math.max(1000, i);
    }
  }
  
  public static int getStep4ShowAdTimeout(Long paramLong)
  {
    if (paramLong != null) {}
    for (int i = paramLong.intValue();; i = getStep4Timeout()) {
      return Math.max(1000, i);
    }
  }
  
  public static int getStep4Timeout()
  {
    if (step4Timeout != null) {}
    for (int i = step4Timeout.intValue();; i = 8000) {
      return Math.max(1000, i);
    }
  }
  
  public static JSONObject getVpaidConfig()
  {
    return configJson.optJSONObject("vpaid");
  }
  
  public static void init(Activity paramActivity, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1)) {
      siteId = paramString1;
    }
    if ((isInitDone()) || ((STATE.get() == 2) && (configJson.length() == 0))) {}
    for (;;)
    {
      return;
      if (STATE.compareAndSet(0, 1))
      {
        start(paramActivity, paramString1, paramString2);
        return;
      }
      if ((paramActivity != null) && (STATE.compareAndSet(2, 3)))
      {
        initAdapters(paramActivity);
        setApplicationId(paramActivity);
        getDeviceInfo(paramActivity);
        STATE.compareAndSet(3, 4);
        AerServLog.d(LOG_TAG, "pre-init: done");
        return;
      }
      long l = System.currentTimeMillis();
      while ((System.currentTimeMillis() - l < 5000L) && (!isInitDone()) && ((STATE.get() != 2) || (configJson.length() != 0))) {
        CommonUtils.sleepInMillisSeconds(100L);
      }
    }
  }
  
  private static boolean initAdapterClassByName(final Activity paramActivity, final String paramString, final JSONArray paramJSONArray)
  {
    try
    {
      Method localMethod = Class.forName(AdapterFactory.getAdapterClassName(paramString)).getMethod("initPartnerSdk", new Class[] { Activity.class, JSONArray.class });
      if (("Chartboost".equals(paramString)) || ("Vungle".equals(paramString)) || ("AdColony".equals(paramString)) || ("AppLovin".equals(paramString)))
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              this.val$initPartnerSdkMethod.invoke(null, new Object[] { paramActivity, paramJSONArray });
              return;
            }
            catch (Throwable localThrowable)
            {
              AerServLog.e(AerServSettings.LOG_TAG, "pre-init: Exception initializing " + paramString + " SDK: " + localThrowable.getMessage());
            }
          }
        }).start();
        return true;
      }
      localMethod.invoke(null, new Object[] { paramActivity, paramJSONArray });
      return true;
    }
    catch (Throwable paramActivity)
    {
      AerServLog.e(LOG_TAG, "pre-init: Exception initializing " + paramString + " SDK: " + paramActivity.getMessage());
    }
    return false;
  }
  
  public static boolean initAdapterClassByName(Activity paramActivity, String paramString, JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(paramJSONObject);
    return initAdapterClassByName(paramActivity, paramString, localJSONArray);
  }
  
  private static void initAdapters(Activity paramActivity)
  {
    JSONObject localJSONObject = configJson.optJSONObject("adapterConfigurationLists");
    if (localJSONObject == null) {
      AerServLog.w(LOG_TAG, "pre-init: Cannot initialize adapters because adapterConfigurationLists is missing in config response");
    }
    for (;;)
    {
      return;
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        initAdapterClassByName(paramActivity, str, localJSONObject.optJSONArray(str));
      }
    }
  }
  
  public static boolean isAnalyticsEnabled(String paramString)
  {
    readConfig(paramString);
    if (isAnalyticsEnabled != null) {
      return isAnalyticsEnabled.booleanValue();
    }
    return false;
  }
  
  public static boolean isAnalyticsEventEnabled(String paramString1, String paramString2, String paramString3)
  {
    readConfig(paramString1);
    if (!isAnalyticsEnabled(paramString1)) {
      return false;
    }
    paramString1 = configJson.optJSONObject("analytics");
    if (paramString1 != null)
    {
      paramString1 = paramString1.optJSONArray("disabledEvents");
      if (paramString1 != null)
      {
        int i = 0;
        for (;;)
        {
          if (i >= paramString1.length()) {
            break label108;
          }
          Object localObject = paramString1.optJSONObject(i);
          String str = ((JSONObject)localObject).optString("category", "");
          localObject = ((JSONObject)localObject).optString("action", "");
          if ((str.equals(paramString2)) && (((String)localObject).equals(paramString3))) {
            break;
          }
          i += 1;
        }
      }
    }
    label108:
    return true;
  }
  
  public static boolean isInitDone()
  {
    return STATE.get() == 4;
  }
  
  private static void parseConfigJson()
  {
    JSONObject localJSONObject1;
    if (configJson.has("analytics"))
    {
      localJSONObject1 = configJson.optJSONObject("analytics");
      if (localJSONObject1 == null) {}
    }
    try
    {
      isAnalyticsEnabled = Boolean.valueOf(localJSONObject1.getBoolean("enabled"));
      localJSONObject1 = configJson.optJSONObject("centralLogging");
      if (localJSONObject1 == null) {}
    }
    catch (JSONException localJSONException3)
    {
      try
      {
        centralLoggingEnabled = Boolean.valueOf(localJSONObject1.getBoolean("enabled"));
      }
      catch (JSONException localJSONException3)
      {
        try
        {
          label66:
          centralLoggingLineCount = Integer.valueOf(localJSONObject1.getInt("lineCount"));
        }
        catch (JSONException localJSONException3)
        {
          try
          {
            label79:
            centralLoggingSendStackTrace = Boolean.valueOf(localJSONObject1.getBoolean("sendStackTrace"));
            label92:
            if (!configJson.has("timeouts")) {}
          }
          catch (JSONException localJSONException3)
          {
            try
            {
              localJSONObject1 = configJson.getJSONObject("timeouts");
              step1Timeout = Integer.valueOf(localJSONObject1.optInt("step1", 3000));
              step2Timeout = Integer.valueOf(localJSONObject1.optInt("step2", 6000));
              step3Timeout = Integer.valueOf(localJSONObject1.optInt("step3", 11000));
              step4Timeout = Integer.valueOf(localJSONObject1.optInt("step4", 8000));
              if (configJson.has("requestTimeout")) {
                httpTimeout = Math.min(step3Timeout.intValue(), configJson.optInt("requestTimeout", httpTimeout));
              }
            }
            catch (JSONException localJSONException3)
            {
              try
              {
                for (;;)
                {
                  localJSONObject1 = configJson.optJSONObject("adapterConfigurationLists");
                  if (localJSONObject1 != null)
                  {
                    Iterator localIterator = localJSONObject1.keys();
                    for (;;)
                    {
                      if (localIterator.hasNext())
                      {
                        JSONArray localJSONArray = localJSONObject1.optJSONArray((String)localIterator.next());
                        if (localJSONArray != null)
                        {
                          int i = 0;
                          label262:
                          int j = localJSONArray.length();
                          if (i < j) {
                            try
                            {
                              Asplc.putAsplc(localJSONArray.getJSONObject(i));
                              i += 1;
                              break label262;
                              localJSONException1 = localJSONException1;
                              AerServLog.w(LOG_TAG, "pre-init: Error reading analytics enabled setting: " + localJSONException1.getMessage());
                              break;
                              localJSONException5 = localJSONException5;
                              AerServLog.w(LOG_TAG, "pre-init: Error reading Central Logging enabled setting: " + localJSONException5.getMessage());
                              break label66;
                              localJSONException6 = localJSONException6;
                              AerServLog.w(LOG_TAG, "pre-init: Error reading Central Logging line count setting: " + localJSONException6.getMessage());
                              break label79;
                              localJSONException2 = localJSONException2;
                              AerServLog.w(LOG_TAG, "pre-init: Error reading Central Logging send stack trace setting: " + localJSONException2.getMessage());
                              break label92;
                              localJSONException3 = localJSONException3;
                              AerServLog.w(LOG_TAG, "pre-init: Error reading timeouts: " + localJSONException3.getMessage());
                            }
                            catch (JSONException localJSONException7)
                            {
                              for (;;)
                              {
                                AerServLog.w(LOG_TAG, "pre-init: Exception initializing asplc: " + localJSONException7.getMessage());
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                try
                {
                  JSONObject localJSONObject2 = configJson.getJSONObject("ad");
                  if (localJSONObject2.has("refreshEnabled")) {
                    AdapterAdRefresher.setEnableFlag(localJSONObject2.getBoolean("refreshEnabled"));
                  }
                  if (localJSONObject2.has("adCacheExpiration")) {
                    AdapterAdRefresher.setSleepTimeInMilliseconds(localJSONObject2.getLong("adCacheExpiration"));
                  }
                }
                catch (JSONException localJSONException4)
                {
                  for (;;)
                  {
                    AerServLog.w(LOG_TAG, "pre-init: Error reading ad: " + localJSONException4.getMessage());
                  }
                }
              }
              catch (Throwable localThrowable)
              {
                AerServLog.e(LOG_TAG, "pre-init: Exception initializing asplc " + localThrowable.getMessage());
                if (!configJson.has("ad")) {}
              }
            }
          }
        }
      }
    }
    Asplc.setLimit(configJson);
  }
  
  public static JSONObject readConfig(String paramString)
  {
    if (!TextUtils.isEmpty(siteId)) {
      siteId = siteId;
    }
    if (STATE.compareAndSet(0, 1))
    {
      AerServLog.d(LOG_TAG, "pre-init: start pre-init from old entry point");
      start(null, null, paramString);
    }
    return configJson;
  }
  
  private static void setApplicationId(Activity paramActivity)
  {
    try
    {
      applicationId = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).packageName;
      return;
    }
    catch (Exception paramActivity)
    {
      AerServLog.i(LOG_TAG, "pre-init: Exception setting applicatioon ID: " + paramActivity.getMessage());
    }
  }
  
  private static void start(final Activity paramActivity, String paramString1, String paramString2)
  {
    AerServLog.d(LOG_TAG, "pre-init: start");
    System.setProperty("http.keepAlive", "false");
    final CountDownLatch localCountDownLatch = new CountDownLatch(1);
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    PreInitProxy localPreInitProxy = new PreInitProxy();
    localPreInitProxy.fetchConfig(paramString1, paramString2, new HttpTaskListener()
    {
      public void onHttpTaskFailure(String paramAnonymousString, int paramAnonymousInt)
      {
        this.val$responseStatusCode[0] = 10001;
        localCountDownLatch.countDown();
      }
      
      public void onHttpTaskSuccess(String paramAnonymousString1, int paramAnonymousInt, Map<String, List<String>> paramAnonymousMap, String paramAnonymousString2)
      {
        AerServLog.d(AerServSettings.LOG_TAG, "pre-init: response: " + paramAnonymousString2);
        if (!TextUtils.isEmpty(paramAnonymousString2)) {}
        try
        {
          AerServSettings.access$302(new JSONObject(paramAnonymousString2));
          if (AerServSettings.configJson == null)
          {
            this.val$responseStatusCode[0] = 0;
            localCountDownLatch.countDown();
            return;
          }
        }
        catch (JSONException paramAnonymousString1)
        {
          for (;;)
          {
            AerServLog.w(AerServSettings.LOG_TAG, "pre-init: Error parsing config data from server: " + paramAnonymousString1.getMessage());
            continue;
            this.val$responseStatusCode[0] = paramAnonymousInt;
            if (AerServSettings.STATE.compareAndSet(1, 2))
            {
              AerServSettings.access$500();
              if ((paramActivity != null) && (AerServSettings.STATE.compareAndSet(2, 3)))
              {
                AerServSettings.initAdapters(paramActivity);
                AerServSettings.setApplicationId(paramActivity);
                AerServSettings.getDeviceInfo(paramActivity);
                AerServSettings.STATE.compareAndSet(3, 4);
                AerServLog.d(AerServSettings.LOG_TAG, "pre-init: done");
              }
            }
          }
        }
      }
    });
    try
    {
      localCountDownLatch.await(getInitTimeout(), TimeUnit.MILLISECONDS);
      if ((localCountDownLatch.getCount() > 0L) || (arrayOfInt[0] == 10001))
      {
        localPreInitProxy.cancel();
        AerServLog.w(LOG_TAG, "pre-init: server call time is up. Need to continue executing.");
        SybokProxy.sendTimeoutLogSybok(paramActivity, "", 0, 5000, paramString2);
      }
      if (((localCountDownLatch.getCount() > 0L) || (arrayOfInt[0] == 10001) || (arrayOfInt[0] == 0)) && (configJson == null))
      {
        i = RETRY_COUNTDOWN.get();
        if (i != 0) {
          break label200;
        }
        if (STATE.compareAndSet(1, 2)) {
          configJson = new JSONObject();
        }
      }
      return;
    }
    catch (InterruptedException paramString1)
    {
      int i;
      label200:
      do
      {
        for (;;)
        {
          paramString1.printStackTrace();
        }
      } while (!RETRY_COUNTDOWN.compareAndSet(i, i - 1));
      STATE.compareAndSet(1, 0);
    }
  }
}
