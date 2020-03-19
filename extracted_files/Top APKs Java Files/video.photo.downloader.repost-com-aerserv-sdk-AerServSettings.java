package com.aerserv.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.aerserv.sdk.http.HttpPostTask;
import com.aerserv.sdk.utils.AerServLog;
import com.aerserv.sdk.utils.ReflectionUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AerServSettings
{
  private static final String ADVERTISING_ID_KEY = "adId";
  private static final String APPS_KEYS = "apps";
  private static final String BUNDLE_ID_KEY = "bundleId";
  private static final String CONFIG_SERVER_URL = "https://ads.aerserv.com/as/sdk/configure/";
  private static final String GET_DEVICE_INFO_KEY = "update";
  private static final String HTTP_TIMEOUT_KEY = "requestTimeout";
  private static final String INSTALLED_KEY = "installed";
  private static final String LOG_TAG = AerServSettings.class.getSimpleName();
  private static final String UNINSTALLED_KEY = "uninstalled";
  private static final String UPDATE_SERVER_URL = "https://dmp.aerserv.com/update";
  private static final String VPAID_KEY = "vpaid";
  private static String applicationId = null;
  private static Boolean centralLoggingEnabled;
  private static Integer centralLoggingLineCount;
  private static Boolean centralLoggingSendStackTrace;
  private static JSONObject configJson;
  private static AtomicBoolean hasUpdatedInformation = new AtomicBoolean(false);
  private static int httpTimeout;
  private static Boolean isAnalyticsEnabled;
  private static Boolean isSimultaneousEnabled;
  private static Integer loadAdTimeout;
  private static final Object lock = new Object();
  private static Integer showAdTimeout;
  private static String siteId;
  
  static
  {
    configJson = null;
    isAnalyticsEnabled = null;
    centralLoggingEnabled = null;
    centralLoggingLineCount = null;
    centralLoggingSendStackTrace = null;
    httpTimeout = 6000;
    loadAdTimeout = null;
    showAdTimeout = null;
    isSimultaneousEnabled = null;
    siteId = null;
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
  
  /* Error */
  @android.annotation.TargetApi(14)
  private static JSONObject getConfigFromServer(String paramString)
  {
    // Byte code:
    //   0: bipush 14
    //   2: invokestatic 164	com/aerserv/sdk/utils/VersionUtils:checkVersion	(I)Z
    //   5: ifne +17 -> 22
    //   8: new 166	org/json/JSONObject
    //   11: dup
    //   12: invokespecial 167	org/json/JSONObject:<init>	()V
    //   15: putstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   18: getstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   21: areturn
    //   22: getstatic 72	com/aerserv/sdk/AerServSettings:lock	Ljava/lang/Object;
    //   25: astore_2
    //   26: aload_2
    //   27: monitorenter
    //   28: getstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   31: ifnull +16 -> 47
    //   34: getstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   37: astore_0
    //   38: aload_2
    //   39: monitorexit
    //   40: aload_0
    //   41: areturn
    //   42: astore_0
    //   43: aload_2
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    //   47: new 166	org/json/JSONObject
    //   50: dup
    //   51: invokespecial 167	org/json/JSONObject:<init>	()V
    //   54: putstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   57: new 166	org/json/JSONObject
    //   60: dup
    //   61: invokespecial 167	org/json/JSONObject:<init>	()V
    //   64: astore_3
    //   65: aload_3
    //   66: ldc -87
    //   68: ldc -85
    //   70: invokevirtual 175	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   73: pop
    //   74: aload_3
    //   75: ldc -79
    //   77: ldc -77
    //   79: invokevirtual 175	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   82: pop
    //   83: aload_0
    //   84: ifnull +23 -> 107
    //   87: ldc -75
    //   89: aload_0
    //   90: invokevirtual 186	java/lang/String:trim	()Ljava/lang/String;
    //   93: invokevirtual 190	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   96: ifne +11 -> 107
    //   99: aload_3
    //   100: ldc -65
    //   102: aload_0
    //   103: invokevirtual 175	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   106: pop
    //   107: new 193	com/aerserv/sdk/http/HttpPostTask
    //   110: dup
    //   111: ldc 19
    //   113: aload_3
    //   114: invokevirtual 194	org/json/JSONObject:toString	()Ljava/lang/String;
    //   117: invokespecial 197	com/aerserv/sdk/http/HttpPostTask:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: astore_0
    //   121: aload_0
    //   122: getstatic 203	android/os/AsyncTask:THREAD_POOL_EXECUTOR	Ljava/util/concurrent/Executor;
    //   125: iconst_0
    //   126: anewarray 205	java/lang/Void
    //   129: invokevirtual 209	com/aerserv/sdk/http/HttpPostTask:executeOnExecutor	(Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   132: pop
    //   133: aload_0
    //   134: invokevirtual 213	com/aerserv/sdk/http/HttpPostTask:get	()Ljava/lang/Object;
    //   137: checkcast 183	java/lang/String
    //   140: astore_0
    //   141: aload_0
    //   142: invokestatic 219	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   145: istore_1
    //   146: iload_1
    //   147: ifne +14 -> 161
    //   150: new 166	org/json/JSONObject
    //   153: dup
    //   154: aload_0
    //   155: invokespecial 220	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   158: putstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   161: getstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   164: astore_0
    //   165: aload_2
    //   166: monitorexit
    //   167: aload_0
    //   168: areturn
    //   169: astore_0
    //   170: getstatic 67	com/aerserv/sdk/AerServSettings:LOG_TAG	Ljava/lang/String;
    //   173: new 118	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   180: ldc -34
    //   182: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: aload_0
    //   186: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   189: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: aload_0
    //   196: invokestatic 231	com/aerserv/sdk/utils/AerServLog:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   199: getstatic 74	com/aerserv/sdk/AerServSettings:configJson	Lorg/json/JSONObject;
    //   202: astore_0
    //   203: aload_2
    //   204: monitorexit
    //   205: aload_0
    //   206: areturn
    //   207: astore_0
    //   208: getstatic 67	com/aerserv/sdk/AerServSettings:LOG_TAG	Ljava/lang/String;
    //   211: new 118	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   218: ldc -23
    //   220: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_0
    //   224: invokevirtual 234	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   227: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokestatic 236	com/aerserv/sdk/utils/AerServLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   236: goto -75 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	239	0	paramString	String
    //   145	2	1	bool	boolean
    //   25	179	2	localObject	Object
    //   64	50	3	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   28	40	42	finally
    //   43	45	42	finally
    //   47	57	42	finally
    //   57	83	42	finally
    //   87	107	42	finally
    //   107	141	42	finally
    //   141	146	42	finally
    //   150	161	42	finally
    //   161	167	42	finally
    //   170	205	42	finally
    //   208	236	42	finally
    //   57	83	169	java/lang/Exception
    //   87	107	169	java/lang/Exception
    //   107	141	169	java/lang/Exception
    //   150	161	207	org/json/JSONException
  }
  
  public static void getDeviceInfo(Context paramContext)
  {
    if (((paramContext instanceof Activity)) && (hasUpdatedInformation.compareAndSet(false, true)))
    {
      readConfig();
      paramContext = (Activity)paramContext;
      final JSONArray localJSONArray = configJson.optJSONArray("update");
      if ((localJSONArray != null) && (localJSONArray.length() > 0)) {
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
                      break label254;
                    }
                    localHashSet.add(localObject);
                    if (((String)localObject).equals("apps"))
                    {
                      AerServLog.v(AerServSettings.LOG_TAG, "Updating device informations for apps.");
                      localObject = new JSONObject();
                      getDeviceInfo((JSONObject)localObject);
                      localJSONObject.put("apps", localObject);
                      break label254;
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
                new HttpPostTask("https://dmp.aerserv.com/update", localJSONObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
              }
              return;
              label254:
              i += 1;
            }
          }
        }).start();
      }
    }
    else
    {
      return;
    }
    AerServLog.v(LOG_TAG, "Cannot update advertising information.");
  }
  
  public static int getHttpTimeout()
  {
    return httpTimeout;
  }
  
  public static int getLoadAdTimeout(Integer paramInteger)
  {
    readConfig();
    int i;
    if (paramInteger != null) {
      i = paramInteger.intValue();
    }
    for (;;)
    {
      return Math.max(1000, i);
      if (loadAdTimeout != null) {
        i = loadAdTimeout.intValue();
      } else {
        i = 15000;
      }
    }
  }
  
  public static int getShowAdTimeout(Long paramLong)
  {
    int i;
    if (paramLong != null) {
      i = paramLong.intValue();
    }
    for (;;)
    {
      return Math.max(1000, i);
      readConfig();
      if (showAdTimeout != null) {
        i = showAdTimeout.intValue();
      } else {
        i = 8000;
      }
    }
  }
  
  public static String getSiteId()
  {
    return siteId;
  }
  
  public static JSONObject getVpaidConfig()
  {
    return configJson.optJSONObject("vpaid");
  }
  
  public static boolean isAnalyticsEnabled()
  {
    readConfig();
    if (isAnalyticsEnabled != null) {
      return isAnalyticsEnabled.booleanValue();
    }
    return false;
  }
  
  public static boolean isAnalyticsEventEnabled(String paramString1, String paramString2)
  {
    readConfig();
    if (!isAnalyticsEnabled()) {
      return false;
    }
    Object localObject1 = configJson.optJSONObject("analytics");
    if (localObject1 != null)
    {
      localObject1 = ((JSONObject)localObject1).optJSONArray("disabledEvents");
      if (localObject1 != null)
      {
        int i = 0;
        for (;;)
        {
          if (i >= ((JSONArray)localObject1).length()) {
            break label104;
          }
          Object localObject2 = ((JSONArray)localObject1).optJSONObject(i);
          String str = ((JSONObject)localObject2).optString("category", "");
          localObject2 = ((JSONObject)localObject2).optString("action", "");
          if ((str.equals(paramString1)) && (((String)localObject2).equals(paramString2))) {
            break;
          }
          i += 1;
        }
      }
    }
    label104:
    return true;
  }
  
  public static boolean isSimultaneousEnabled()
  {
    if (isSimultaneousEnabled == null) {
      return true;
    }
    return isSimultaneousEnabled.booleanValue();
  }
  
  private static JSONObject readConfig()
  {
    return readConfig(null, "");
  }
  
  public static JSONObject readConfig(Activity paramActivity, String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      siteId = paramString;
    }
    if (paramActivity != null) {}
    try
    {
      applicationId = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).packageName;
      if (configJson != null) {
        return configJson;
      }
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        AerServLog.i(LOG_TAG, "Exception setting applicatioon ID: " + paramActivity.getMessage());
      }
      configJson = getConfigFromServer(paramString);
      if (configJson == null) {
        configJson = new JSONObject();
      }
      if (configJson.has("analytics"))
      {
        paramActivity = configJson.optJSONObject("analytics");
        if (paramActivity == null) {}
      }
    }
    try
    {
      isAnalyticsEnabled = Boolean.valueOf(paramActivity.getBoolean("enabled"));
      paramActivity = configJson.optJSONObject("centralLogging");
      if (paramActivity == null) {}
    }
    catch (JSONException paramActivity)
    {
      try
      {
        centralLoggingEnabled = Boolean.valueOf(paramActivity.getBoolean("enabled"));
      }
      catch (JSONException paramActivity)
      {
        try
        {
          centralLoggingLineCount = Integer.valueOf(paramActivity.getInt("lineCount"));
        }
        catch (JSONException paramActivity)
        {
          try
          {
            centralLoggingSendStackTrace = Boolean.valueOf(paramActivity.getBoolean("sendStackTrace"));
            if (!configJson.has("loadTimeout")) {}
          }
          catch (JSONException paramActivity)
          {
            try
            {
              loadAdTimeout = Integer.valueOf(configJson.getInt("loadTimeout"));
              if (!configJson.has("showTimeout")) {}
            }
            catch (JSONException paramActivity)
            {
              try
              {
                for (;;)
                {
                  showAdTimeout = Integer.valueOf(configJson.getInt("showTimeout"));
                  if (configJson.has("enableSimultaneous")) {
                    isSimultaneousEnabled = Boolean.valueOf(configJson.optBoolean("enableSimultaneous", true));
                  }
                  if (configJson.has("requestTimeout")) {
                    httpTimeout = Math.min(loadAdTimeout.intValue(), configJson.optInt("requestTimeout", httpTimeout));
                  }
                  return configJson;
                  paramActivity = paramActivity;
                  AerServLog.w(LOG_TAG, "Error reading analytics enabled setting: " + paramActivity.getMessage());
                  continue;
                  paramString = paramString;
                  AerServLog.w(LOG_TAG, "Error reading Central Logging enabled setting: " + paramString.getMessage());
                  continue;
                  paramString = paramString;
                  AerServLog.w(LOG_TAG, "Error reading Central Logging line count setting: " + paramString.getMessage());
                  continue;
                  paramActivity = paramActivity;
                  AerServLog.w(LOG_TAG, "Error reading Central Logging send stack trace setting: " + paramActivity.getMessage());
                }
                paramActivity = paramActivity;
                AerServLog.w(LOG_TAG, "Error reading loadAdTimeout: " + paramActivity.getMessage());
              }
              catch (JSONException paramActivity)
              {
                for (;;)
                {
                  AerServLog.w(LOG_TAG, "Error reading showAdTimeout: " + paramActivity.getMessage());
                }
              }
            }
          }
        }
      }
    }
  }
}
