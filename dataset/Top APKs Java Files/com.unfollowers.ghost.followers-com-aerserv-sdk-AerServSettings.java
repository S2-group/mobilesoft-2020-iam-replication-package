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
import com.google.a.a.a.a.a.a;
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
  private static final String LOG_TAG = "AerServSettings";
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
  private static JSONObject configJson;
  private static int httpTimeout = 6000;
  private static Boolean isAnalyticsEnabled;
  private static final String sessionId = UUID.randomUUID().toString();
  private static String siteId;
  private static Integer step1Timeout;
  private static Integer step2Timeout;
  private static Integer step3Timeout;
  private static Integer step4Timeout;
  
  public AerServSettings() {}
  
  private static void checkDependency(String... paramVarArgs)
  {
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (!ReflectionUtils.canFindClass(str))
      {
        paramVarArgs = new StringBuilder();
        paramVarArgs.append("Could not find library: ");
        paramVarArgs.append(str);
        paramVarArgs.append(".");
        throw new IllegalStateException(paramVarArgs.toString());
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
    if ((localJSONArray != null) && (localJSONArray.length() != 0))
    {
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
              String str1 = ((AdvertisingIdClient.Info)localObject).getId();
              JSONObject localJSONObject = new JSONObject();
              HashSet localHashSet = new HashSet();
              i = 0;
              if (i < localJSONArray.length())
              {
                if (localJSONArray.isNull(i)) {
                  localObject = null;
                } else {
                  localObject = localJSONArray.optString(i);
                }
                if ((TextUtils.isEmpty((CharSequence)localObject)) || (localHashSet.contains(localObject))) {
                  break label271;
                }
                localHashSet.add(localObject);
                if (((String)localObject).equals("apps"))
                {
                  AerServLog.v(AerServSettings.LOG_TAG, "Updating device informations for apps.");
                  localObject = new JSONObject();
                  getDeviceInfo((JSONObject)localObject);
                  localJSONObject.put("apps", localObject);
                  break label271;
                }
                String str2 = AerServSettings.LOG_TAG;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("Unsupported field to update: ");
                localStringBuilder.append((String)localObject);
                AerServLog.i(str2, localStringBuilder.toString());
                break label271;
              }
              if (localJSONObject.length() > 0)
              {
                localJSONObject.put("adId", str1);
                new HttpPostTask("https://dmp.aerserv.com/update", localJSONObject.toString(), AerServSettings.httpTimeout).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return;
              }
            }
            catch (Exception localException)
            {
              AerServLog.i(AerServSettings.LOG_TAG, "Error getting device information.", localException);
            }
            return;
            label271:
            i += 1;
          }
        }
      }).start();
      return;
    }
    AerServLog.v(LOG_TAG, "No device info fields to update.");
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
    int i;
    if (step1Timeout != null) {
      i = step1Timeout.intValue();
    } else {
      i = 3000;
    }
    return Math.max(1000, i);
  }
  
  public static int getStep2Timeout()
  {
    int i;
    if (step2Timeout != null) {
      i = step2Timeout.intValue();
    } else {
      i = 6000;
    }
    return Math.max(1000, i);
  }
  
  public static int getStep3Timeout()
  {
    int i;
    if (step3Timeout != null) {
      i = step3Timeout.intValue();
    } else {
      i = 11000;
    }
    return Math.max(1000, i);
  }
  
  public static int getStep4ShowAdTimeout(Long paramLong)
  {
    int i;
    if (paramLong != null) {
      i = paramLong.intValue();
    } else {
      i = getStep4Timeout();
    }
    return Math.max(1000, i);
  }
  
  public static int getStep4Timeout()
  {
    int i;
    if (step4Timeout != null) {
      i = step4Timeout.intValue();
    } else {
      i = 8000;
    }
    return Math.max(1000, i);
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
    if (!isInitDone())
    {
      if ((STATE.get() == 2) && (configJson.length() == 0)) {
        return;
      }
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
      return;
    }
  }
  
  private static boolean initAdapterClassByName(final Activity paramActivity, final String paramString, final JSONArray paramJSONArray)
  {
    try
    {
      localObject = Class.forName(AdapterFactory.getAdapterClassName(paramString)).getMethod("initPartnerSdk", new Class[] { Activity.class, JSONArray.class });
      if ((!"Chartboost".equals(paramString)) && (!"Vungle".equals(paramString)) && (!"AdColony".equals(paramString)) && (!"AppLovin".equals(paramString)))
      {
        ((Method)localObject).invoke(null, new Object[] { paramActivity, paramJSONArray });
        return true;
      }
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
            String str = AerServSettings.LOG_TAG;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("pre-init: Exception initializing ");
            localStringBuilder.append(paramString);
            localStringBuilder.append(" SDK: ");
            localStringBuilder.append(localThrowable.getMessage());
            AerServLog.e(str, localStringBuilder.toString());
          }
        }
      }).start();
      return true;
    }
    catch (Throwable paramActivity)
    {
      paramJSONArray = LOG_TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("pre-init: Exception initializing ");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(" SDK: ");
      ((StringBuilder)localObject).append(paramActivity.getMessage());
      AerServLog.e(paramJSONArray, ((StringBuilder)localObject).toString());
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
    if (localJSONObject == null)
    {
      AerServLog.w(LOG_TAG, "pre-init: Cannot initialize adapters because adapterConfigurationLists is missing in config response");
      return;
    }
    Iterator localIterator = localJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      initAdapterClassByName(paramActivity, str, localJSONObject.optJSONArray(str));
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
        while (i < paramString1.length())
        {
          Object localObject = paramString1.optJSONObject(i);
          String str = ((JSONObject)localObject).optString("category", "");
          localObject = ((JSONObject)localObject).optString("action", "");
          if ((str.equals(paramString2)) && (((String)localObject).equals(paramString3))) {
            return false;
          }
          i += 1;
        }
      }
    }
    return true;
  }
  
  public static boolean isInitDone()
  {
    return STATE.get() == 4;
  }
  
  private static void parseConfigJson()
  {
    Object localObject2;
    if (configJson.has("analytics"))
    {
      JSONObject localJSONObject1 = configJson.optJSONObject("analytics");
      if (localJSONObject1 != null) {
        try
        {
          isAnalyticsEnabled = Boolean.valueOf(localJSONObject1.getBoolean("enabled"));
        }
        catch (JSONException localJSONException1)
        {
          String str1 = LOG_TAG;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("pre-init: Error reading analytics enabled setting: ");
          ((StringBuilder)localObject2).append(localJSONException1.getMessage());
          AerServLog.w(str1, ((StringBuilder)localObject2).toString());
        }
      }
    }
    JSONObject localJSONObject2 = configJson.optJSONObject("centralLogging");
    Object localObject1;
    if (localJSONObject2 != null)
    {
      StringBuilder localStringBuilder1;
      try
      {
        centralLoggingEnabled = Boolean.valueOf(localJSONObject2.getBoolean("enabled"));
      }
      catch (JSONException localJSONException5)
      {
        localObject2 = LOG_TAG;
        localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("pre-init: Error reading Central Logging enabled setting: ");
        localStringBuilder1.append(localJSONException5.getMessage());
        AerServLog.w((String)localObject2, localStringBuilder1.toString());
      }
      try
      {
        centralLoggingLineCount = Integer.valueOf(localJSONObject2.getInt("lineCount"));
      }
      catch (JSONException localJSONException6)
      {
        localObject2 = LOG_TAG;
        localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("pre-init: Error reading Central Logging line count setting: ");
        localStringBuilder1.append(localJSONException6.getMessage());
        AerServLog.w((String)localObject2, localStringBuilder1.toString());
      }
      try
      {
        centralLoggingSendStackTrace = Boolean.valueOf(localJSONObject2.getBoolean("sendStackTrace"));
      }
      catch (JSONException localJSONException2)
      {
        localObject1 = LOG_TAG;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("pre-init: Error reading Central Logging send stack trace setting: ");
        ((StringBuilder)localObject2).append(localJSONException2.getMessage());
        AerServLog.w((String)localObject1, ((StringBuilder)localObject2).toString());
      }
    }
    if (configJson.has("timeouts")) {
      try
      {
        JSONObject localJSONObject3 = configJson.getJSONObject("timeouts");
        step1Timeout = Integer.valueOf(localJSONObject3.optInt("step1", 3000));
        step2Timeout = Integer.valueOf(localJSONObject3.optInt("step2", 6000));
        step3Timeout = Integer.valueOf(localJSONObject3.optInt("step3", 11000));
        step4Timeout = Integer.valueOf(localJSONObject3.optInt("step4", 8000));
      }
      catch (JSONException localJSONException3)
      {
        localObject1 = LOG_TAG;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("pre-init: Error reading timeouts: ");
        ((StringBuilder)localObject2).append(localJSONException3.getMessage());
        AerServLog.w((String)localObject1, ((StringBuilder)localObject2).toString());
      }
    }
    if (configJson.has("requestTimeout")) {
      httpTimeout = Math.min(step3Timeout.intValue(), configJson.optInt("requestTimeout", httpTimeout));
    }
    try
    {
      JSONObject localJSONObject4 = configJson.optJSONObject("adapterConfigurationLists");
      if (localJSONObject4 != null)
      {
        localObject1 = localJSONObject4.keys();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = localJSONObject4.optJSONArray((String)((Iterator)localObject1).next());
          if (localObject2 != null)
          {
            int i = 0;
            for (;;)
            {
              int j = ((JSONArray)localObject2).length();
              if (i >= j) {
                break;
              }
              try
              {
                Asplc.putAsplc(((JSONArray)localObject2).getJSONObject(i));
              }
              catch (JSONException localJSONException7)
              {
                String str2 = LOG_TAG;
                StringBuilder localStringBuilder2 = new StringBuilder();
                localStringBuilder2.append("pre-init: Exception initializing asplc: ");
                localStringBuilder2.append(localJSONException7.getMessage());
                AerServLog.w(str2, localStringBuilder2.toString());
              }
              i += 1;
            }
          }
        }
      }
      JSONObject localJSONObject5;
      return;
    }
    catch (Throwable localThrowable)
    {
      localObject1 = LOG_TAG;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("pre-init: Exception initializing asplc ");
      ((StringBuilder)localObject2).append(localThrowable.getMessage());
      AerServLog.e((String)localObject1, ((StringBuilder)localObject2).toString());
      if (configJson.has("ad")) {
        try
        {
          localJSONObject5 = configJson.getJSONObject("ad");
          if (localJSONObject5.has("refreshEnabled")) {
            AdapterAdRefresher.setEnableFlag(localJSONObject5.getBoolean("refreshEnabled"));
          }
          if (localJSONObject5.has("adCacheExpiration")) {
            AdapterAdRefresher.setSleepTimeInMilliseconds(localJSONObject5.getLong("adCacheExpiration"));
          }
        }
        catch (JSONException localJSONException4)
        {
          localObject1 = LOG_TAG;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("pre-init: Error reading ad: ");
          ((StringBuilder)localObject2).append(localJSONException4.getMessage());
          AerServLog.w((String)localObject1, ((StringBuilder)localObject2).toString());
        }
      }
      Asplc.setLimit(configJson);
    }
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
      String str = LOG_TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("pre-init: Exception setting applicatioon ID: ");
      localStringBuilder.append(paramActivity.getMessage());
      AerServLog.i(str, localStringBuilder.toString());
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
        paramAnonymousString1 = AerServSettings.LOG_TAG;
        paramAnonymousMap = new StringBuilder();
        paramAnonymousMap.append("pre-init: response: ");
        paramAnonymousMap.append(paramAnonymousString2);
        AerServLog.d(paramAnonymousString1, paramAnonymousMap.toString());
        if (!TextUtils.isEmpty(paramAnonymousString2)) {
          try
          {
            AerServSettings.access$302(new JSONObject(paramAnonymousString2));
          }
          catch (JSONException paramAnonymousString1)
          {
            paramAnonymousMap = AerServSettings.LOG_TAG;
            paramAnonymousString2 = new StringBuilder();
            paramAnonymousString2.append("pre-init: Error parsing config data from server: ");
            paramAnonymousString2.append(paramAnonymousString1.getMessage());
            AerServLog.w(paramAnonymousMap, paramAnonymousString2.toString());
          }
        }
        if (AerServSettings.configJson == null)
        {
          this.val$responseStatusCode[0] = 0;
        }
        else
        {
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
        localCountDownLatch.countDown();
      }
    });
    try
    {
      localCountDownLatch.await(getInitTimeout(), TimeUnit.MILLISECONDS);
    }
    catch (InterruptedException paramString1)
    {
      a.a(paramString1);
    }
    if ((localCountDownLatch.getCount() > 0L) || (arrayOfInt[0] == 10001))
    {
      localPreInitProxy.cancel();
      AerServLog.w(LOG_TAG, "pre-init: server call time is up. Need to continue executing.");
      SybokProxy.sendTimeoutLogSybok(paramActivity, "", 0, 5000, paramString2);
    }
    if (((localCountDownLatch.getCount() > 0L) || (arrayOfInt[0] == 10001) || (arrayOfInt[0] == 0)) && (configJson == null))
    {
      int i = RETRY_COUNTDOWN.get();
      if (i == 0)
      {
        if (STATE.compareAndSet(1, 2)) {
          configJson = new JSONObject();
        }
      }
      else if (RETRY_COUNTDOWN.compareAndSet(i, i - 1)) {
        STATE.compareAndSet(1, 0);
      }
    }
  }
}
