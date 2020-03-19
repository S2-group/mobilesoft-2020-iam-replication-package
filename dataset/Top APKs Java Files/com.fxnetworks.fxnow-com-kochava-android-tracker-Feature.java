package com.kochava.android.tracker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.kochava.android.util.Logging;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Feature
{
  protected static final String ADLOGTAG = "KochavaAds";
  private static final int AD_CLICK_RESET_TIME = 2500;
  private static final int AD_UNAVAILABLE_CLICK_RESET_TIME = 2500;
  private static final int ANDROID_ID_WAIT = 2;
  private static final String ANDROID_RECEIVER = "android_receiver";
  public static final String ATTRIBUTION_DATA = "attributionData";
  private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  private static final Uri ATTRIBUTION_ID_CONTENT_URI;
  protected static final String ATTRIBUTION_PREF_DATA = "attributionData";
  protected static final String ATTRIBUTION_PREF_NAME = "attributionPref";
  private static final String BAD_INIT_MESSAGE = "The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.";
  private static final int BULK_UPLOAD_LIMIT = 50;
  private static final String CONTROL_DEFAULT_HOST = "control.kochava.com";
  private static final String DEVICE_ID_GENERATED = "kochava_app_id_generated";
  private static final String EVENTNAMEBLACKLIST = "eventnameblacklist";
  private static final int EVENT_FLUSH_TIME = 60000;
  private static final String EXIT = "exit";
  private static final int FB_ID_WAIT = 2;
  private static final int FLUSH_RATE_MAX_MINS = 360;
  private static final String GETATTRIBUTION_WAIT = "getattribution_wait";
  private static final int GET_ATTRIBUTION_WAIT_MAX = 30;
  private static final int GET_ATTRIBUTION_WAIT_MIN = 1;
  private static final String HTML_FORMAT = "<html><body style=\"text-align: center; background-color: black; vertical-align: center;\"><img src = \"%s\" /></body></html>";
  private static final String HTTPS_STRING = "https://";
  private static final String HTTP_STRING = "http://";
  private static final String INIT_ENDPOINT = "/track/kvinit";
  private static final int INIT_FORCE_TIME = 600000;
  private static final String KOCHAVA_APP_ID = "kochava_app_id";
  private static final int KVINIT_RETRY_FINAL_STEP = 4;
  private static final String KVINIT_TIMESTAMP = "kvinit_timestamp";
  private static final String KVINIT_WAIT = "kvinit_wait";
  private static final long KVINIT_WAIT_DEFAULT = 60000L;
  private static final int KVINIT_WAIT_MAX = 604800;
  private static final String KVQUERY_ENDPOINT = "/track/kvquery";
  private static final String KVTRACKER_WAIT = "kvtracker_wait";
  private static final int KVTRACKER_WAIT_MAX = 604800;
  private static final int KV_QUERY_DEFAULT_RERUN = 60;
  private static final String LAUNCH = "launch";
  protected static final String LINKIDENTITYBOOL = "linkIdentityBool";
  protected static final String LOGTAG = "KochavaTracker";
  private static final int MAX_ADID_DELAY = 600;
  private static final int NETWORK_TIMEOUT = 30000;
  private static final String OUTSIDE_SERVICES = "outside_services";
  protected static final String PREF_APPDATA = "kochavaappdata";
  protected static final String PREF_CUR = "currency";
  protected static final String PREF_INIT = "initBool";
  protected static final String PREF_INIT_DATA = "initData";
  protected static final String PREF_LAT = "mylat";
  protected static final String PREF_LONG = "mylong";
  protected static final String PREF_NAME = "initPrefs";
  protected static final String PREF_TIMESTOPPED = "timeStampStopped";
  private static final String REGISTER_REMOVE_TOKEN = "register_remove_token";
  protected static final String REQLOGTAG = "KochavaRequirements";
  private static final String RESONANCE_CASCADE = "resonance_cascade";
  private static final String SENDONSTART = "sendonstart";
  private static final String SEND_ID_UPDATES = "send_id_updates";
  private static final String SESSION_ERROR_MESSAGE = "Exception occured during session tracking.";
  private static final String STRING_FALSE = "false";
  private static final String STRING_TRUE = "true";
  private static final String TRACKER_ENDPOINT = "/track/kvTracker.php";
  private static String adid;
  private static String advertisingID;
  protected static Context appContext;
  private static Handler attributionDataHandler;
  private static SharedPreferences attributionDataPrefs;
  private static boolean badInit;
  private static String bssid;
  private static boolean canSendSession;
  private static String carrier;
  private static boolean device_limit_tracking;
  private static boolean doGatherLocation;
  private static boolean event_flush_triggered;
  private static JSONArray eventnameBlacklist;
  private static final ExecutorService executor;
  private static boolean flushTimerSetByInitializer;
  private static boolean flushTimerSetByKVinitResponse;
  private static int flush_rate;
  private static int get_attribution_wait;
  private static String hostControl = "";
  private static Map<String, String> identityLinkMap;
  private static JSONObject identityLinkMapJSON;
  private static List<Feature.ApplicationInfoHolder> installedApps;
  private static boolean is_in_background;
  private static DbAdapter kDbAdapter;
  private static final HashMap<Integer, Integer> kvinitRetrySteps;
  private static long lastCallTime;
  private static long lastEventTimestamp;
  protected static Handler locationHandler = new Feature.17();
  private static String mAppId;
  private static String mEnvDeviceID;
  private static String mKochDevIDStrategy;
  private static Map<String, String> mSuperProperties;
  private static Timer mTimer;
  private static String mUserAgent;
  protected static boolean overrideAutomaticSessions;
  private static HashMap<String, Boolean> paramRestrictions;
  private static SharedPreferences prefs;
  private static int referrerDelayFromInit;
  private static boolean requestAttributionData;
  private static boolean sendEmails;
  private static Runnable sendKVQuery;
  private static boolean sendOnStart;
  private static boolean send_id_updates;
  private static boolean should_flush_in_background;
  private static long startTime;
  private static boolean suppress_adid;
  protected static String versionExtension = "";
  private static final ScheduledExecutorService worker;
  private final String LOCATION_ACCURACY = "location_accuracy";
  private final String LOCATION_STALENESS = "location_staleness";
  private final String LOCATION_TIMEOUT = "location_timeout";
  private Timer adTimer;
  private boolean app_limit_tracking = false;
  private String clickData;
  private Timer eventFlushTimer;
  private Runnable getKVinit = new Feature.6(this);
  private Handler initHandler = null;
  private Timer initTimer;
  private JSONObject initialObject;
  private JSONObject initialPropertiesObject;
  protected JSONObject kvinitdata;
  protected JSONObject kvinitdataholder;
  protected JSONObject kvinitorigdata;
  private String mAndroidID;
  private String mAppName;
  private String mAppPackageName;
  private String mAppVersionCode;
  private String mAppVersionName;
  private String mCarrier;
  private String mDeviceId;
  private int mDisplayHeight;
  private int mDisplayWidth;
  private String mFbId;
  private boolean mIsStartOfLife = true;
  private String mModel;
  private String mOSVersion;
  private Timer mTimerSendOnBegin;
  
  static
  {
    overrideAutomaticSessions = false;
    flush_rate = 60000;
    flushTimerSetByInitializer = false;
    flushTimerSetByKVinitResponse = false;
    kvinitRetrySteps = new Feature.1();
    get_attribution_wait = 7;
    referrerDelayFromInit = 60;
    device_limit_tracking = false;
    send_id_updates = false;
    suppress_adid = false;
    sendOnStart = true;
    doGatherLocation = false;
    lastCallTime = 0L;
    startTime = 0L;
    adid = "";
    installedApps = new ArrayList();
    should_flush_in_background = true;
    event_flush_triggered = false;
    is_in_background = false;
    lastEventTimestamp = 0L;
    badInit = false;
    canSendSession = true;
    sendEmails = false;
    requestAttributionData = false;
    executor = Executors.newFixedThreadPool(1);
    worker = Executors.newSingleThreadScheduledExecutor();
    ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    paramRestrictions = new Feature.2();
    eventnameBlacklist = new JSONArray();
    sendKVQuery = new Feature.7();
  }
  
  public Feature(Context paramContext, String paramString)
  {
    HashMap localHashMap = new HashMap();
    if ((paramString != null) && (paramString.trim().length() != 0)) {
      localHashMap.put("kochava_app_id", paramString);
    }
    init(paramContext, true, localHashMap);
  }
  
  public Feature(Context paramContext, String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    if ((paramString1 != null) && (paramString1.trim().length() != 0)) {
      localHashMap.put("kochava_app_id", paramString1);
    }
    if ((paramString2 != null) && (paramString2.trim().length() != 0)) {
      localHashMap.put("currency", paramString2);
    }
    init(paramContext, true, localHashMap);
  }
  
  public Feature(Context paramContext, String paramString, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    if ((paramString != null) && (paramString.trim().length() != 0)) {
      localHashMap.put("kochava_app_id", paramString);
    }
    init(paramContext, paramBoolean, localHashMap);
  }
  
  public Feature(Context paramContext, String paramString1, boolean paramBoolean, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    if ((paramString1 != null) && (paramString1.trim().length() != 0)) {
      localHashMap.put("kochava_app_id", paramString1);
    }
    if ((paramString2 != null) && (paramString2.trim().length() != 0)) {
      localHashMap.put("currency", paramString2);
    }
    init(paramContext, paramBoolean, localHashMap);
  }
  
  public Feature(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    init(paramContext, true, paramHashMap);
  }
  
  public Feature(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    init(paramContext, paramBoolean, paramHashMap);
  }
  
  private static JSONObject addGlobalProperties(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        paramJSONObject.put("usertime", System.currentTimeMillis() / 1000L + "");
        paramJSONObject.put("uptime", System.currentTimeMillis() / 1000L - startTime + "");
        if (lastCallTime != 0L) {
          paramJSONObject.put("updelta", System.currentTimeMillis() / 1000L - lastCallTime + "");
        }
        for (;;)
        {
          lastCallTime = System.currentTimeMillis() / 1000L;
          prefs = appContext.getSharedPreferences("initPrefs", 0);
          if (prefs.getString("mylat", "").equals("")) {
            break;
          }
          paramJSONObject.put("geo_lat", prefs.getString("mylat", ""));
          paramJSONObject.put("geo_lon", prefs.getString("mylong", ""));
          return paramJSONObject;
          paramJSONObject.put("updelta", "0");
        }
        return paramJSONObject;
      }
      catch (Exception localException)
      {
        Logging.LogError("Error adding time properties to a JSON object " + localException);
      }
    }
  }
  
  private void checkOutsideServices(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray1 = new JSONArray();
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        JSONArray localJSONArray2 = paramJSONObject.getJSONArray(str);
        int i = 0;
        for (;;)
        {
          int j = localJSONArray2.length();
          if (i >= j) {
            break;
          }
          try
          {
            new ComponentName(appContext, Class.forName(localJSONArray2.getString(i)));
            localJSONArray1.put(str);
          }
          catch (Exception localException)
          {
            i += 1;
          }
        }
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      if (localJSONArray1.length() > 0) {
        sendOutsideServicesUpdate(localJSONArray1);
      }
    }
  }
  
  private String createAppData()
  {
    String str = "" + getMyDeviceId() + ":::";
    str = str + getModel() + ":::";
    str = str + getBrand() + ":::";
    str = str + getAppVersion() + ":::";
    return str + getOSVersion();
  }
  
  public static void enableDebug(boolean paramBoolean)
  {
    Global.DEBUG = paramBoolean;
    Logging.Log("enableDebug to " + paramBoolean);
  }
  
  private static void endAppSession()
  {
    for (;;)
    {
      try
      {
        if ((Build.VERSION.SDK_INT < 14) || (overrideAutomaticSessions)) {
          return;
        }
        if (badInit)
        {
          Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
          return;
        }
        Logging.Log("Automatic Session End");
        if (!should_flush_in_background)
        {
          if (mTimer != null)
          {
            Logging.Log("Session end, flush timer was on, canceling timer and flushing current events.");
            flush();
            mTimer.cancel();
            mTimer = null;
          }
        }
        else
        {
          if (!canSendSession) {
            break;
          }
          eventSession("exit");
          return;
        }
      }
      catch (Exception localException)
      {
        Logging.LogError("(Automatic Session End) Exception occured during session tracking.\n" + localException);
        return;
      }
      Logging.Log("Session end, flush timer was already off.");
    }
    Logging.Log("Session events disabled by server.");
  }
  
  private static void eventSession(String paramString)
  {
    new Feature.11(paramString).start();
  }
  
  private void fireEvent(String paramString, Map<String, String> paramMap)
  {
    if ((!paramString.equals("initial")) && (!should_flush_in_background) && (is_in_background) && (!event_flush_triggered))
    {
      event_flush_triggered = true;
      this.eventFlushTimer.schedule(new Feature.12(this), 60000L);
    }
    Logging.Log("FIRE EVENT*** action:" + paramString);
    Logging.Log("FIRE EVENT*** properties:" + paramMap);
    JSONObject localJSONObject1 = new JSONObject();
    label1081:
    int i;
    do
    {
      JSONObject localJSONObject2;
      for (;;)
      {
        try
        {
          localJSONObject1.put("kochava_app_id", mAppId);
          localJSONObject1.put("kochava_device_id", getMyDeviceId());
          localJSONObject1.put("action", paramString);
          localJSONObject1.put("dev_id_strategy", mKochDevIDStrategy);
          localJSONObject1.put("last_post_time", 0);
          prefs = appContext.getSharedPreferences("initPrefs", 0);
          localJSONObject1.put("currency", prefs.getString("currency", "USD"));
          localJSONObject2 = addGlobalProperties(new JSONObject());
          if (paramString.equals("initial"))
          {
            Logging.Log("Event is initial, or initial did not get que'd on first load");
            try
            {
              if (((Boolean)paramRestrictions.get("affinity_group")).booleanValue())
              {
                paramString = new JSONArray();
                localObject = installedApps.iterator();
                if (((Iterator)localObject).hasNext())
                {
                  Feature.ApplicationInfoHolder localApplicationInfoHolder = (Feature.ApplicationInfoHolder)((Iterator)localObject).next();
                  JSONObject localJSONObject3 = new JSONObject();
                  localJSONObject3.put("app_name", localApplicationInfoHolder.appPackage);
                  localJSONObject3.put("mtime", localApplicationInfoHolder.appInstallTime);
                  localJSONObject3.put("utime", localApplicationInfoHolder.appUpdateTime);
                  paramString.put(localJSONObject3);
                  continue;
                }
              }
              if (mSuperProperties == null) {
                break;
              }
            }
            catch (JSONException paramString)
            {
              if (Global.DEBUGERROR) {
                paramString.printStackTrace();
              }
              Logging.LogError("event " + paramString);
              return;
              localJSONObject2.put("affinity_group", paramString);
              localJSONObject1.put("sdk_version", "Android20160203" + versionExtension);
              if (((Boolean)paramRestrictions.get("volume")).booleanValue()) {
                localJSONObject1.put("volume", getVolume());
              }
              if (((Boolean)paramRestrictions.get("bssid")).booleanValue()) {
                localJSONObject2.put("bssid", bssid);
              }
              if (((Boolean)paramRestrictions.get("carrier_name")).booleanValue()) {
                localJSONObject2.put("carrier_name", carrier);
              }
              if (((Boolean)paramRestrictions.get("adid")).booleanValue()) {
                localJSONObject2.put("adid", advertisingID);
              }
              localJSONObject2.put("device", getModel() + "-" + getBrand());
              if (((Boolean)paramRestrictions.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_h", this.mDisplayHeight);
              }
              if (((Boolean)paramRestrictions.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_w", this.mDisplayWidth);
              }
              localJSONObject2.put("package_name", getAppPackageName());
              localJSONObject2.put("app_version", getAppVersion());
              if (!this.mAppVersionName.equals("")) {
                localJSONObject2.put("app_short_string", this.mAppVersionName);
              }
              if (((Boolean)paramRestrictions.get("android_id")).booleanValue()) {
                localJSONObject2.put("android_id", this.mAndroidID);
              }
              localJSONObject2.put("os_version", getOSVersion());
              localJSONObject2.put("app_limit_tracking", this.app_limit_tracking);
              localJSONObject2.put("device_limit_tracking", device_limit_tracking);
              paramString = new JSONObject();
              if (sendEmails)
              {
                localObject = getEmailAccounts();
                if (!((String)localObject).equals("[]")) {
                  paramString.put("email", localObject);
                }
              }
              if (paramString.length() > 0) {
                localJSONObject2.put("ids", paramString);
              }
              if (identityLinkMapJSON != null) {
                localJSONObject2.put("identity_link", identityLinkMapJSON);
              }
              if ((this.clickData != null) && (!this.clickData.isEmpty())) {
                localJSONObject2.put("clickData", this.clickData);
              }
              if (((Boolean)paramRestrictions.get("fb_attribution_id")).booleanValue())
              {
                this.mFbId = getAttributionId(appContext.getContentResolver());
                if (this.mFbId != null) {
                  break label1081;
                }
                localJSONObject2.put("fb_attribution_id", "");
              }
              paramString = (WindowManager)appContext.getSystemService("window");
              localObject = new DisplayMetrics();
              paramString.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
              this.initialPropertiesObject = localJSONObject2;
              this.initialObject = localJSONObject1;
              Logging.Log("Initial Event, saving until next event. ");
              return;
            }
            catch (Exception paramString)
            {
              Logging.LogError("Error during fireEvent - Please review stack trace");
              if (Global.DEBUGERROR) {
                paramString.printStackTrace();
              }
            }
          }
          paramString = mSuperProperties.entrySet().iterator();
          if (!paramString.hasNext()) {
            break;
          }
          Object localObject = (Map.Entry)paramString.next();
          localJSONObject2.put((String)((Map.Entry)localObject).getKey(), ((Map.Entry)localObject).getValue());
          continue;
          localJSONObject2.put("fb_attribution_id", this.mFbId);
        }
        catch (JSONException paramString)
        {
          if (Global.DEBUGERROR) {
            paramString.printStackTrace();
          }
          Logging.LogError("event " + paramString);
          return;
        }
      }
      if (paramMap != null)
      {
        paramString = paramMap.entrySet().iterator();
        while (paramString.hasNext())
        {
          paramMap = (Map.Entry)paramString.next();
          localJSONObject2.put((String)paramMap.getKey(), paramMap.getValue());
        }
      }
      localJSONObject1.put("data", localJSONObject2);
      Logging.Log("fireEvent with properties: " + localJSONObject1);
      queInitial(true);
      i = kDbAdapter.addEvent(localJSONObject1, false, false);
      lastEventTimestamp = System.currentTimeMillis();
    } while (i < 50);
    flush();
  }
  
  private void fireEventBlacklist(String paramString, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("event_name");
    if (str != null)
    {
      int k = 0;
      int i = 0;
      for (;;)
      {
        int j = k;
        if (i < eventnameBlacklist.length()) {}
        try
        {
          boolean bool = eventnameBlacklist.get(i).equals(paramMap.get("event_name"));
          if (bool)
          {
            j = 1;
            if (j == 0) {
              break;
            }
            Logging.Log("fireEvent - Events under the key \"" + str + "\" are blocked!");
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          i += 1;
        }
      }
      fireEvent(paramString, paramMap);
      return;
    }
    fireEvent(paramString, paramMap);
  }
  
  public static void flush()
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    Logging.Log("flush");
    executor.submit(new Feature.TrackTask(null));
  }
  
  private String getAdvertisingId()
  {
    for (;;)
    {
      int i;
      try
      {
        i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(appContext);
        if (i != 0) {}
        switch (i)
        {
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
          Logging.Log("Google Play Services check returned unknown error code (" + i + ").");
          Logging.LogError("Problem getting Advertising ID " + GooglePlayServicesUtil.getErrorString(i));
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(appContext);
          String str = localInfo.getId();
          device_limit_tracking = localInfo.isLimitAdTrackingEnabled();
          return str;
        }
      }
      catch (Exception localException)
      {
        Logging.LogError("Problem getting Advertising ID (catch): " + localException.toString());
        return "";
      }
      Logging.Log("Google Play Services check returned ConnectionResult.SUCCESS (" + i + ").");
      continue;
      Logging.Log("Google Play Services check returned ConnectionResult.SERVICE_MISSING (" + i + ").");
      continue;
      Logging.Log("Google Play Services check returned ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED (" + i + ").");
      continue;
      Logging.Log("Google Play Services check returned ConnectionResult.SERVICE_DISABLED (" + i + ").");
      continue;
      Logging.Log("Google Play Services check returned ConnectionResult.SERVICE_INVALID (" + i + ").");
    }
  }
  
  private String getAppPackageName()
  {
    if (this.mAppPackageName != null) {
      return this.mAppPackageName;
    }
    return "Unknown";
  }
  
  private String getAppVersion()
  {
    return this.mAppName + " " + this.mAppVersionCode;
  }
  
  public static String getAttributionData()
  {
    if (attributionDataPrefs != null) {
      return attributionDataPrefs.getString("attributionData", "");
    }
    return "";
  }
  
  /* Error */
  protected static String getAttributionId(android.content.ContentResolver paramContentResolver)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_2
    //   7: aload_0
    //   8: getstatic 368	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 421	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 44
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1073	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1078 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1081 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1084 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 44
    //   75: invokeinterface 1088 2 0
    //   80: invokeinterface 1089 2 0
    //   85: astore 5
    //   87: aload 5
    //   89: astore_2
    //   90: aload_2
    //   91: astore_3
    //   92: aload_0
    //   93: ifnull +22 -> 115
    //   96: aload_2
    //   97: astore_3
    //   98: aload_0
    //   99: invokeinterface 1081 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1084 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 622	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1091
    //   130: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1081 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1084 1 0
    //   171: aload 4
    //   173: astore_3
    //   174: goto -59 -> 115
    //   177: astore_0
    //   178: aload 4
    //   180: astore_3
    //   181: goto -66 -> 115
    //   184: astore_0
    //   185: aload_3
    //   186: ifnull +18 -> 204
    //   189: aload_3
    //   190: invokeinterface 1081 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1084 1 0
    //   204: aload_0
    //   205: athrow
    //   206: astore_2
    //   207: goto -3 -> 204
    //   210: astore_0
    //   211: aload_2
    //   212: astore_3
    //   213: goto -98 -> 115
    //   216: astore_0
    //   217: goto -152 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	paramContentResolver	android.content.ContentResolver
    //   41	2	1	bool	boolean
    //   6	160	2	localObject1	Object
    //   206	6	2	localException	Exception
    //   4	209	3	localObject2	Object
    //   1	178	4	localObject3	Object
    //   85	3	5	str	String
    // Exception table:
    //   from	to	target	type
    //   7	27	117	java/lang/Exception
    //   35	42	117	java/lang/Exception
    //   71	87	117	java/lang/Exception
    //   156	171	177	java/lang/Exception
    //   7	27	184	finally
    //   35	42	184	finally
    //   71	87	184	finally
    //   120	146	184	finally
    //   189	204	206	java/lang/Exception
    //   98	113	210	java/lang/Exception
    //   50	65	216	java/lang/Exception
  }
  
  private static String getBrand()
  {
    return Build.BRAND;
  }
  
  private String getEmailAccounts()
  {
    Object localObject1 = "";
    if (appContext.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0)
    {
      Account[] arrayOfAccount = AccountManager.get(appContext).getAccounts();
      int j = arrayOfAccount.length;
      int i = 0;
      while (i < j)
      {
        Account localAccount = arrayOfAccount[i];
        Object localObject2 = localObject1;
        if (isEmailValid(localAccount.name))
        {
          localObject2 = localAccount.name.toLowerCase();
          localObject2 = (String)localObject1 + (String)localObject2 + ",";
        }
        i += 1;
        localObject1 = localObject2;
      }
      if (((String)localObject1).length() > 0) {
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
    }
    for (;;)
    {
      return "[" + (String)localObject1 + "]";
      localObject1 = "";
      continue;
      localObject1 = "";
      Logging.Log("****NOTICE**** Gathering of emails was whitelisted, but android.permission.GET_ACCOUNTS declaration was missing from manifest.");
    }
  }
  
  public static String getKochavaDeviceId()
  {
    try
    {
      String str = getMyDeviceId();
      if (str != null) {
        return str;
      }
    }
    catch (Exception localException)
    {
      Logging.LogError(localException.toString());
    }
    return "";
  }
  
  private static String getModel()
  {
    return Build.MODEL;
  }
  
  private static String getMyDeviceId()
  {
    if ((prefs.contains("kochava_app_id_generated")) && (!prefs.getString("kochava_app_id_generated", "").equals(""))) {
      return prefs.getString("kochava_app_id_generated", "");
    }
    String str = UUID.randomUUID().toString().replaceAll("-", "");
    str = "KA" + str;
    prefs.edit().putString("kochava_app_id_generated", str).apply();
    return str;
  }
  
  private static String getOSVersion()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  private String getUserAgent()
  {
    Object localObject5 = "";
    localObject1 = "";
    int i = 1;
    int j = 1;
    try
    {
      localObject6 = "" + "\nTrying user agent method 1";
      localObject1 = localObject6;
      localObject3 = System.getProperty("http.agent");
      localObject5 = localObject3;
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        Object localObject3;
        localObject6 = new StringWriter();
        localException2.printStackTrace(new PrintWriter((Writer)localObject6));
        Logging.LogError(((StringWriter)localObject6).toString());
        localObject6 = (String)localObject1 + "\nError with user agent first method: " + localException2.toString() + "\n" + ((StringWriter)localObject6).toString();
      }
    }
    if (((String)localObject5).trim().isEmpty()) {
      i = 0;
    }
    localObject1 = localObject5;
    localObject3 = localObject6;
    if (i == 0)
    {
      localObject1 = localObject5;
      localObject3 = localObject6;
    }
    try
    {
      localObject6 = (String)localObject6 + "\nTrying user agent method 2";
      localObject1 = localObject5;
      localObject3 = localObject6;
      localObject5 = new WebView(appContext).getSettings().getUserAgentString();
      localObject1 = localObject5;
      localObject3 = localObject6;
      localObject6 = (String)localObject6 + "\nMethod 2 successful";
      localObject3 = localObject6;
      localObject1 = localObject5;
    }
    catch (Exception localException3)
    {
      for (;;)
      {
        Object localObject10;
        Object localObject9;
        localObject6 = new StringWriter();
        localException3.printStackTrace(new PrintWriter((Writer)localObject6));
        Logging.LogError(((StringWriter)localObject6).toString());
        localObject4 = localException2 + "\nError with user agent second method: " + localException3.toString() + "\n" + ((StringWriter)localObject6).toString() + "\n userAgent = error.";
      }
    }
    i = j;
    if (((String)localObject1).trim().isEmpty()) {
      i = 0;
    }
    localObject7 = localObject1;
    localObject8 = localObject3;
    if (i == 0)
    {
      localObject10 = null;
      localObject9 = null;
      localObject5 = localObject9;
      localObject6 = localObject1;
      localObject8 = localObject3;
      localObject7 = localObject10;
    }
    try
    {
      String str2 = (String)localObject3 + "\nTrying user agent method 3";
      localObject5 = localObject9;
      localObject6 = localObject1;
      localObject8 = str2;
      localObject7 = localObject10;
      localObject3 = WebSettings.class.getDeclaredConstructor(new Class[] { Context.class, WebView.class });
      localObject5 = localObject3;
      localObject6 = localObject1;
      localObject8 = str2;
      localObject7 = localObject3;
      ((Constructor)localObject3).setAccessible(true);
      localObject5 = localObject3;
      localObject6 = localObject1;
      localObject8 = str2;
      localObject7 = localObject3;
      localObject1 = ((WebSettings)((Constructor)localObject3).newInstance(new Object[] { appContext, null })).getUserAgentString();
      localObject5 = localObject3;
      localObject6 = localObject1;
      localObject8 = str2;
      localObject7 = localObject3;
      str2 = str2 + "\nMethod 3 successful.";
      localObject5 = str2;
      localObject7 = localObject1;
      localObject8 = localObject5;
      if (localObject3 != null)
      {
        ((Constructor)localObject3).setAccessible(false);
        localObject8 = localObject5;
        localObject7 = localObject1;
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        localObject7 = localException3;
        Object localObject4 = new StringWriter();
        localObject7 = localException3;
        localException1.printStackTrace(new PrintWriter((Writer)localObject4));
        localObject7 = localException3;
        Logging.LogError(((StringWriter)localObject4).toString());
        localObject7 = localException3;
        String str1 = (String)localObject8 + "\nError with user agent third method: " + localException1.toString() + "\n" + ((StringWriter)localObject4).toString() + "\n userAgent = error.";
        localObject7 = localObject6;
        localObject8 = str1;
        if (localException3 != null)
        {
          localException3.setAccessible(false);
          localObject7 = localObject6;
          localObject8 = str1;
        }
      }
    }
    finally
    {
      if (localObject7 == null) {
        break label735;
      }
      ((Constructor)localObject7).setAccessible(false);
    }
    Logging.Log("user agent result: " + (String)localObject8);
    return localObject7;
  }
  
  private static String getVolume()
  {
    try
    {
      Object localObject = (AudioManager)appContext.getSystemService("audio");
      localObject = ((AudioManager)localObject).getStreamVolume(3) + "";
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static boolean haveAttributionData()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (attributionDataPrefs != null)
    {
      bool1 = bool2;
      if (!attributionDataPrefs.getAll().isEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static void httpsError(Exception paramException)
  {
    new Feature.16(paramException).start();
  }
  
  /* Error */
  @android.annotation.TargetApi(14)
  private void init(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +2353 -> 2354
    //   4: aload_1
    //   5: invokevirtual 1263	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 315	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   15: ldc -128
    //   17: new 622	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 1265
    //   27: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: getstatic 288	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   33: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokestatic 1271	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   42: pop
    //   43: aload_0
    //   44: new 755	java/util/Timer
    //   47: dup
    //   48: invokespecial 1272	java/util/Timer:<init>	()V
    //   51: putfield 784	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   54: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   57: ldc -100
    //   59: iconst_0
    //   60: invokevirtual 657	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   63: putstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   66: new 982	com/kochava/android/tracker/DbAdapter
    //   69: dup
    //   70: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   73: invokespecial 1273	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   76: putstatic 551	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   79: aload_3
    //   80: ifnull +231 -> 311
    //   83: aload_3
    //   84: ldc_w 1275
    //   87: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   90: ifnull +44 -> 134
    //   93: aload_3
    //   94: ldc_w 1275
    //   97: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   100: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   103: ldc_w 819
    //   106: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   109: ifeq +25 -> 134
    //   112: aload_3
    //   113: ldc_w 1275
    //   116: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   119: checkcast 819	java/lang/Boolean
    //   122: invokevirtual 822	java/lang/Boolean:booleanValue	()Z
    //   125: istore_2
    //   126: iload_2
    //   127: invokestatic 1281	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   130: iload_2
    //   131: invokestatic 1284	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   134: aload_3
    //   135: ldc_w 1286
    //   138: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   141: ifnull +35 -> 176
    //   144: aload_3
    //   145: ldc_w 1286
    //   148: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: ldc_w 421
    //   157: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   160: ifeq +16 -> 176
    //   163: aload_3
    //   164: ldc_w 1286
    //   167: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 421	java/lang/String
    //   173: putstatic 288	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   176: aload_3
    //   177: ldc_w 1288
    //   180: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: ifnull +38 -> 221
    //   186: aload_3
    //   187: ldc_w 1288
    //   190: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   196: ldc_w 819
    //   199: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   202: ifeq +19 -> 221
    //   205: aload_3
    //   206: ldc_w 1288
    //   209: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   212: checkcast 819	java/lang/Boolean
    //   215: invokevirtual 822	java/lang/Boolean:booleanValue	()Z
    //   218: putstatic 290	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   221: aload_3
    //   222: ldc_w 1290
    //   225: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   228: ifnull +38 -> 266
    //   231: aload_3
    //   232: ldc_w 1290
    //   235: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: ldc_w 819
    //   244: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   247: ifeq +19 -> 266
    //   250: aload_3
    //   251: ldc_w 1290
    //   254: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   257: checkcast 819	java/lang/Boolean
    //   260: invokevirtual 822	java/lang/Boolean:booleanValue	()Z
    //   263: putstatic 313	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   266: aload_3
    //   267: ldc_w 1292
    //   270: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   273: ifnull +38 -> 311
    //   276: aload_3
    //   277: ldc_w 1292
    //   280: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   283: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   286: ldc_w 819
    //   289: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +19 -> 311
    //   295: aload_3
    //   296: ldc_w 1292
    //   299: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   302: checkcast 819	java/lang/Boolean
    //   305: invokevirtual 822	java/lang/Boolean:booleanValue	()Z
    //   308: putstatic 330	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   311: aload_0
    //   312: invokespecial 1294	com/kochava/android/tracker/Feature:initHandler	()V
    //   315: new 622	java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   322: ldc_w 1296
    //   325: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   331: ldc 64
    //   333: ldc_w 284
    //   336: invokeinterface 663 3 0
    //   341: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   350: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   353: ldc 64
    //   355: ldc_w 284
    //   358: invokeinterface 663 3 0
    //   363: ldc_w 284
    //   366: invokevirtual 667	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   369: ifne +22 -> 391
    //   372: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   375: ldc 64
    //   377: ldc_w 284
    //   380: invokeinterface 663 3 0
    //   385: invokestatic 1300	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   388: putstatic 378	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   391: getstatic 744	android/os/Build$VERSION:SDK_INT	I
    //   394: bipush 14
    //   396: if_icmplt +1999 -> 2395
    //   399: getstatic 290	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   402: ifne +1993 -> 2395
    //   405: new 622	java/lang/StringBuilder
    //   408: dup
    //   409: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   412: ldc_w 1302
    //   415: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: getstatic 290	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   421: invokevirtual 736	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   424: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   430: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   433: checkcast 1304	android/app/Application
    //   436: new 13	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   439: dup
    //   440: aload_0
    //   441: invokespecial 1305	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   444: invokevirtual 1309	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   447: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   450: new 15	com/kochava/android/tracker/Feature$MemoryBoss
    //   453: dup
    //   454: aload_0
    //   455: invokespecial 1310	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   458: invokevirtual 1314	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   461: iconst_1
    //   462: putstatic 1317	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   465: iconst_1
    //   466: putstatic 1320	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   469: new 1322	com/kochava/android/tracker/Feature$3
    //   472: dup
    //   473: aload_0
    //   474: invokespecial 1323	com/kochava/android/tracker/Feature$3:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   477: invokevirtual 776	java/lang/Thread:start	()V
    //   480: getstatic 313	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   483: ifne +14 -> 497
    //   486: new 1325	com/kochava/android/tracker/Feature$4
    //   489: dup
    //   490: aload_0
    //   491: invokespecial 1326	com/kochava/android/tracker/Feature$4:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   494: invokevirtual 776	java/lang/Thread:start	()V
    //   497: aload_0
    //   498: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   501: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   504: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   507: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   510: iconst_0
    //   511: invokevirtual 1339	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   514: getfield 1344	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   517: putfield 1058	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   520: aload_0
    //   521: new 642	org/json/JSONObject
    //   524: dup
    //   525: invokespecial 796	org/json/JSONObject:<init>	()V
    //   528: putfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   531: aload_0
    //   532: new 642	org/json/JSONObject
    //   535: dup
    //   536: invokespecial 796	org/json/JSONObject:<init>	()V
    //   539: putfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   542: aload_0
    //   543: new 642	org/json/JSONObject
    //   546: dup
    //   547: invokespecial 796	org/json/JSONObject:<init>	()V
    //   550: putfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   553: aconst_null
    //   554: astore 13
    //   556: aconst_null
    //   557: astore 6
    //   559: aconst_null
    //   560: astore 9
    //   562: aconst_null
    //   563: astore 7
    //   565: ldc_w 809
    //   568: astore_1
    //   569: aconst_null
    //   570: astore 10
    //   572: aconst_null
    //   573: astore 8
    //   575: aconst_null
    //   576: astore 11
    //   578: aconst_null
    //   579: astore 14
    //   581: aload_1
    //   582: astore 12
    //   584: aload_3
    //   585: ifnull +627 -> 1212
    //   588: aload 6
    //   590: astore 5
    //   592: aload_3
    //   593: ldc_w 1352
    //   596: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   599: ifnull +38 -> 637
    //   602: aload 6
    //   604: astore 5
    //   606: aload_3
    //   607: ldc_w 1352
    //   610: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   613: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   616: ldc_w 421
    //   619: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   622: ifeq +15 -> 637
    //   625: aload_3
    //   626: ldc_w 1352
    //   629: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   632: checkcast 421	java/lang/String
    //   635: astore 5
    //   637: aload 7
    //   639: astore 6
    //   641: aload_3
    //   642: ldc 96
    //   644: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   647: ifnull +36 -> 683
    //   650: aload 7
    //   652: astore 6
    //   654: aload_3
    //   655: ldc 96
    //   657: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   660: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   663: ldc_w 421
    //   666: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   669: ifeq +14 -> 683
    //   672: aload_3
    //   673: ldc 96
    //   675: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   678: checkcast 421	java/lang/String
    //   681: astore 6
    //   683: aload_1
    //   684: astore 7
    //   686: aload_3
    //   687: ldc -115
    //   689: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   692: ifnull +35 -> 727
    //   695: aload_1
    //   696: astore 7
    //   698: aload_3
    //   699: ldc -115
    //   701: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   704: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   707: ldc_w 421
    //   710: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   713: ifeq +14 -> 727
    //   716: aload_3
    //   717: ldc -115
    //   719: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   722: checkcast 421	java/lang/String
    //   725: astore 7
    //   727: aload 8
    //   729: astore_1
    //   730: aload_3
    //   731: ldc_w 1354
    //   734: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   737: ifnull +33 -> 770
    //   740: aload_3
    //   741: ldc_w 1354
    //   744: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   747: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   750: ldc_w 421
    //   753: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   756: ifeq +1667 -> 2423
    //   759: aload_3
    //   760: ldc_w 1354
    //   763: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   766: checkcast 421	java/lang/String
    //   769: astore_1
    //   770: aload_3
    //   771: ldc_w 1356
    //   774: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   777: ifnull +34 -> 811
    //   780: aload_3
    //   781: ldc_w 1356
    //   784: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   787: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   790: ldc_w 421
    //   793: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   796: ifeq +15 -> 811
    //   799: aload_3
    //   800: ldc_w 1356
    //   803: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   806: checkcast 421	java/lang/String
    //   809: astore 8
    //   811: aload 14
    //   813: astore 8
    //   815: aload_3
    //   816: ldc_w 1358
    //   819: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   822: ifnull +38 -> 860
    //   825: aload 14
    //   827: astore 8
    //   829: aload_3
    //   830: ldc_w 1358
    //   833: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   836: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   839: ldc_w 421
    //   842: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   845: ifeq +15 -> 860
    //   848: aload_3
    //   849: ldc_w 1358
    //   852: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   855: checkcast 421	java/lang/String
    //   858: astore 8
    //   860: aload_3
    //   861: ldc_w 895
    //   864: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   867: ifnull +39 -> 906
    //   870: aload_3
    //   871: ldc_w 895
    //   874: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   877: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   880: ldc_w 819
    //   883: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   886: ifeq +20 -> 906
    //   889: aload_0
    //   890: aload_3
    //   891: ldc_w 895
    //   894: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   897: checkcast 819	java/lang/Boolean
    //   900: invokevirtual 822	java/lang/Boolean:booleanValue	()Z
    //   903: putfield 393	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   906: aload_3
    //   907: ldc_w 913
    //   910: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   913: ifnull +115 -> 1028
    //   916: aload_3
    //   917: ldc_w 913
    //   920: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   923: instanceof 418
    //   926: ifeq +102 -> 1028
    //   929: aload_3
    //   930: ldc_w 913
    //   933: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   936: checkcast 418	java/util/HashMap
    //   939: putstatic 1360	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   942: new 642	org/json/JSONObject
    //   945: dup
    //   946: invokespecial 796	org/json/JSONObject:<init>	()V
    //   949: putstatic 911	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   952: getstatic 1360	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   955: invokeinterface 965 1 0
    //   960: invokeinterface 968 1 0
    //   965: astore 9
    //   967: aload 9
    //   969: invokeinterface 692 1 0
    //   974: ifeq +54 -> 1028
    //   977: aload 9
    //   979: invokeinterface 696 1 0
    //   984: checkcast 970	java/util/Map$Entry
    //   987: astore 10
    //   989: getstatic 911	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   992: aload 10
    //   994: invokeinterface 973 1 0
    //   999: checkcast 421	java/lang/String
    //   1002: aload 10
    //   1004: invokeinterface 976 1 0
    //   1009: checkcast 421	java/lang/String
    //   1012: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1015: pop
    //   1016: aload 9
    //   1018: invokeinterface 1363 1 0
    //   1023: goto -56 -> 967
    //   1026: astore 9
    //   1028: aload_3
    //   1029: ldc_w 919
    //   1032: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1035: ifnull +36 -> 1071
    //   1038: aload_3
    //   1039: ldc_w 919
    //   1042: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1045: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1048: ldc_w 421
    //   1051: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1054: ifeq +17 -> 1071
    //   1057: aload_0
    //   1058: aload_3
    //   1059: ldc_w 919
    //   1062: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1065: checkcast 421	java/lang/String
    //   1068: putfield 915	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
    //   1071: aload 6
    //   1073: astore 9
    //   1075: aload_1
    //   1076: astore 10
    //   1078: aload 8
    //   1080: astore 11
    //   1082: aload 7
    //   1084: astore 12
    //   1086: aload 5
    //   1088: astore 13
    //   1090: aload_3
    //   1091: ldc_w 1364
    //   1094: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1097: ifnull +115 -> 1212
    //   1100: aload 6
    //   1102: astore 9
    //   1104: aload_1
    //   1105: astore 10
    //   1107: aload 8
    //   1109: astore 11
    //   1111: aload 7
    //   1113: astore 12
    //   1115: aload 5
    //   1117: astore 13
    //   1119: aload_3
    //   1120: ldc_w 1364
    //   1123: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1126: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1129: ldc_w 1366
    //   1132: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1135: ifeq +77 -> 1212
    //   1138: aload_3
    //   1139: ldc_w 1364
    //   1142: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1145: checkcast 1366	java/lang/Integer
    //   1148: invokevirtual 1369	java/lang/Integer:intValue	()I
    //   1151: istore 4
    //   1153: iload 4
    //   1155: iconst_1
    //   1156: if_icmpge +1314 -> 2470
    //   1159: new 622	java/lang/StringBuilder
    //   1162: dup
    //   1163: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1166: ldc_w 1371
    //   1169: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1172: iload 4
    //   1174: invokevirtual 1022	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1177: ldc_w 1373
    //   1180: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1186: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1189: iconst_1
    //   1190: putstatic 294	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
    //   1193: aload 5
    //   1195: astore 13
    //   1197: aload 7
    //   1199: astore 12
    //   1201: aload 8
    //   1203: astore 11
    //   1205: aload_1
    //   1206: astore 10
    //   1208: aload 6
    //   1210: astore 9
    //   1212: aload 11
    //   1214: ifnull +19 -> 1233
    //   1217: aload 11
    //   1219: invokevirtual 425	java/lang/String:trim	()Ljava/lang/String;
    //   1222: invokevirtual 429	java/lang/String:length	()I
    //   1225: ifeq +8 -> 1233
    //   1228: aload 11
    //   1230: putstatic 286	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1233: aload 10
    //   1235: ifnull +17 -> 1252
    //   1238: aload 10
    //   1240: ldc -73
    //   1242: invokevirtual 1376	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1245: ifeq +7 -> 1252
    //   1248: iconst_1
    //   1249: putstatic 344	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1252: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1255: ldc 50
    //   1257: iconst_0
    //   1258: invokevirtual 657	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1261: putstatic 520	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1264: aload 9
    //   1266: ifnull +1296 -> 2562
    //   1269: aload 9
    //   1271: invokevirtual 425	java/lang/String:trim	()Ljava/lang/String;
    //   1274: invokevirtual 429	java/lang/String:length	()I
    //   1277: ifeq +1285 -> 2562
    //   1280: aload 9
    //   1282: putstatic 615	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1285: aload_0
    //   1286: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1289: ldc 96
    //   1291: aload 9
    //   1293: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1296: pop
    //   1297: aload_0
    //   1298: getfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1301: ldc 96
    //   1303: aload 9
    //   1305: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1308: pop
    //   1309: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1312: ldc -118
    //   1314: ldc_w 284
    //   1317: invokeinterface 663 3 0
    //   1322: ldc_w 284
    //   1325: invokevirtual 667	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1328: ifeq +25 -> 1353
    //   1331: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1334: invokeinterface 1157 1 0
    //   1339: ldc -118
    //   1341: aload 9
    //   1343: invokeinterface 1163 3 0
    //   1348: invokeinterface 1166 1 0
    //   1353: aload_0
    //   1354: aload 12
    //   1356: invokespecial 463	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1359: aload_0
    //   1360: getfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1363: ldc_w 1378
    //   1366: aload_0
    //   1367: invokespecial 886	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1370: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1373: pop
    //   1374: aload_0
    //   1375: getfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1378: ldc_w 1380
    //   1381: ldc_w 1382
    //   1384: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1387: pop
    //   1388: aload_0
    //   1389: getfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1392: ldc_w 1384
    //   1395: ldc_w 1386
    //   1398: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1401: pop
    //   1402: aload_0
    //   1403: getfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1406: ldc -115
    //   1408: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1411: ldc -115
    //   1413: ldc_w 809
    //   1416: invokeinterface 663 3 0
    //   1421: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1424: pop
    //   1425: aload_0
    //   1426: getfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1429: ldc -115
    //   1431: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1434: ldc -115
    //   1436: ldc_w 809
    //   1439: invokeinterface 663 3 0
    //   1444: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1447: pop
    //   1448: aload_0
    //   1449: getfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1452: ldc_w 1384
    //   1455: ldc_w 1386
    //   1458: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1461: pop
    //   1462: aload_0
    //   1463: getfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1466: ldc -115
    //   1468: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1471: ldc -115
    //   1473: ldc_w 809
    //   1476: invokeinterface 663 3 0
    //   1481: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1484: pop
    //   1485: aload_0
    //   1486: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1489: ldc_w 852
    //   1492: new 622	java/lang/StringBuilder
    //   1495: dup
    //   1496: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1499: ldc_w 854
    //   1502: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1505: getstatic 288	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1508: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1511: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1514: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1517: pop
    //   1518: aload_0
    //   1519: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1522: ldc_w 1388
    //   1525: ldc_w 1390
    //   1528: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1531: pop
    //   1532: aload_0
    //   1533: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1536: ldc_w 978
    //   1539: aload_0
    //   1540: getfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1543: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1546: pop
    //   1547: aload_0
    //   1548: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1551: ldc_w 1392
    //   1554: aload_0
    //   1555: getfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1558: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1561: pop
    //   1562: invokestatic 628	java/lang/System:currentTimeMillis	()J
    //   1565: ldc2_w 629
    //   1568: ldiv
    //   1569: putstatic 321	com/kochava/android/tracker/Feature:startTime	J
    //   1572: iconst_0
    //   1573: istore 4
    //   1575: ldc_w 284
    //   1578: astore_3
    //   1579: new 703	android/content/ComponentName
    //   1582: dup
    //   1583: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1586: ldc_w 1394
    //   1589: invokespecial 715	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1592: astore_1
    //   1593: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1596: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1599: aload_1
    //   1600: iconst_0
    //   1601: invokevirtual 1398	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1604: pop
    //   1605: ldc_w 1400
    //   1608: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1611: aload_3
    //   1612: astore_1
    //   1613: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1616: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1619: ldc_w 1402
    //   1622: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1625: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1628: invokevirtual 1405	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1631: ifge +27 -> 1658
    //   1634: iconst_1
    //   1635: istore 4
    //   1637: new 622	java/lang/StringBuilder
    //   1640: dup
    //   1641: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1644: aload_3
    //   1645: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1648: ldc_w 1407
    //   1651: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1654: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1657: astore_1
    //   1658: aload_1
    //   1659: astore_3
    //   1660: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1663: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1666: ldc_w 1409
    //   1669: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1672: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1675: invokevirtual 1405	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1678: ifge +27 -> 1705
    //   1681: iconst_1
    //   1682: istore 4
    //   1684: new 622	java/lang/StringBuilder
    //   1687: dup
    //   1688: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1691: aload_1
    //   1692: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1695: ldc_w 1411
    //   1698: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1701: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1704: astore_3
    //   1705: aload_3
    //   1706: astore_1
    //   1707: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1710: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1713: ldc_w 1413
    //   1716: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1719: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1722: invokevirtual 1405	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1725: ifge +27 -> 1752
    //   1728: iconst_1
    //   1729: istore 4
    //   1731: new 622	java/lang/StringBuilder
    //   1734: dup
    //   1735: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1738: aload_3
    //   1739: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1742: ldc_w 1415
    //   1745: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1748: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1751: astore_1
    //   1752: iload 4
    //   1754: ifeq +13 -> 1767
    //   1757: ldc_w 1417
    //   1760: invokestatic 1420	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1763: aload_1
    //   1764: invokestatic 1420	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1767: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1770: ldc_w 1422
    //   1773: invokevirtual 935	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1776: checkcast 1424	android/telephony/TelephonyManager
    //   1779: invokevirtual 1427	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1782: putstatic 866	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1785: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1788: ldc_w 1429
    //   1791: invokevirtual 935	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1794: checkcast 1431	android/net/wifi/WifiManager
    //   1797: invokevirtual 1435	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1800: invokevirtual 1440	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1803: putstatic 862	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1806: aload_0
    //   1807: invokespecial 1442	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1810: putstatic 611	com/kochava/android/tracker/Feature:mUserAgent	Ljava/lang/String;
    //   1813: aload_0
    //   1814: invokestatic 606	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1817: putfield 1444	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1820: aload_0
    //   1821: invokestatic 602	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1824: putfield 1446	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1827: aload_0
    //   1828: ldc_w 1448
    //   1831: putfield 1062	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1834: aload_0
    //   1835: ldc_w 1450
    //   1838: putfield 1066	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1841: aload_0
    //   1842: ldc_w 284
    //   1845: putfield 528	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1848: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1851: invokevirtual 1263	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1854: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1857: astore_3
    //   1858: aload_3
    //   1859: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1862: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1865: iconst_0
    //   1866: invokevirtual 1454	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1869: astore_1
    //   1870: aload_1
    //   1871: ifnull +1034 -> 2905
    //   1874: aload_3
    //   1875: aload_1
    //   1876: invokevirtual 1458	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1879: astore_1
    //   1880: aload_0
    //   1881: aload_1
    //   1882: checkcast 421	java/lang/String
    //   1885: checkcast 421	java/lang/String
    //   1888: putfield 1062	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1891: new 622	java/lang/StringBuilder
    //   1894: dup
    //   1895: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1898: ldc_w 1460
    //   1901: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1904: aload_0
    //   1905: getfield 1062	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1908: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1911: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1914: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1917: aload_0
    //   1918: new 622	java/lang/StringBuilder
    //   1921: dup
    //   1922: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1925: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1928: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1931: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1934: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1937: iconst_0
    //   1938: invokevirtual 1339	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1941: getfield 1463	android/content/pm/PackageInfo:versionCode	I
    //   1944: invokevirtual 1022	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1947: ldc_w 284
    //   1950: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1953: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1956: putfield 1066	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1959: new 622	java/lang/StringBuilder
    //   1962: dup
    //   1963: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   1966: ldc_w 1465
    //   1969: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1972: aload_0
    //   1973: getfield 1066	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1976: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1979: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1982: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1985: aload_0
    //   1986: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1989: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1992: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1995: invokevirtual 1333	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1998: iconst_0
    //   1999: invokevirtual 1339	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2002: getfield 1468	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2005: putfield 528	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2008: new 622	java/lang/StringBuilder
    //   2011: dup
    //   2012: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2015: ldc_w 1470
    //   2018: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2021: aload_0
    //   2022: getfield 528	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2025: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2028: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2031: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2034: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2037: ldc_w 931
    //   2040: invokevirtual 935	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2043: checkcast 937	android/view/WindowManager
    //   2046: astore_1
    //   2047: aload_0
    //   2048: aload_1
    //   2049: invokeinterface 944 1 0
    //   2054: invokevirtual 1473	android/view/Display:getHeight	()I
    //   2057: putfield 877	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2060: aload_0
    //   2061: aload_1
    //   2062: invokeinterface 944 1 0
    //   2067: invokevirtual 1476	android/view/Display:getWidth	()I
    //   2070: putfield 881	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2073: new 622	java/lang/StringBuilder
    //   2076: dup
    //   2077: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2080: ldc_w 1478
    //   2083: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2086: aload_0
    //   2087: getfield 877	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2090: invokevirtual 1022	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2093: ldc_w 1480
    //   2096: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2099: aload_0
    //   2100: getfield 881	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2103: invokevirtual 1022	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2106: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2109: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2112: new 1482	com/kochava/android/tracker/Feature$5
    //   2115: dup
    //   2116: aload_0
    //   2117: invokespecial 1483	com/kochava/android/tracker/Feature$5:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   2120: invokevirtual 776	java/lang/Thread:start	()V
    //   2123: aload_0
    //   2124: invokestatic 513	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2127: putfield 1485	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2130: aload_0
    //   2131: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2134: ldc_w 798
    //   2137: invokestatic 513	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2140: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2143: pop
    //   2144: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2147: ldc -112
    //   2149: ldc_w 284
    //   2152: invokeinterface 663 3 0
    //   2157: ldc -73
    //   2159: invokevirtual 667	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2162: ifne +149 -> 2311
    //   2165: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2168: invokevirtual 1330	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2171: astore 5
    //   2173: aload 5
    //   2175: sipush 128
    //   2178: invokevirtual 1489	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2181: invokeinterface 827 1 0
    //   2186: astore 6
    //   2188: aload 6
    //   2190: invokeinterface 692 1 0
    //   2195: ifeq +116 -> 2311
    //   2198: aload 6
    //   2200: invokeinterface 696 1 0
    //   2205: checkcast 1491	android/content/pm/ApplicationInfo
    //   2208: astore 7
    //   2210: aload 7
    //   2212: invokestatic 1495	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2215: istore_2
    //   2216: iload_2
    //   2217: ifne -29 -> 2188
    //   2220: aconst_null
    //   2221: astore_1
    //   2222: aload 5
    //   2224: aload 7
    //   2226: getfield 1496	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2229: iconst_0
    //   2230: invokevirtual 1339	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2233: astore_3
    //   2234: aload_3
    //   2235: astore_1
    //   2236: aload_1
    //   2237: ifnull -49 -> 2188
    //   2240: getstatic 328	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2243: new 8	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2246: dup
    //   2247: aload_0
    //   2248: aload_1
    //   2249: getfield 1344	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2252: new 622	java/lang/StringBuilder
    //   2255: dup
    //   2256: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2259: aload_1
    //   2260: getfield 1499	android/content/pm/PackageInfo:firstInstallTime	J
    //   2263: invokevirtual 634	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2266: ldc_w 284
    //   2269: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2272: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2275: new 622	java/lang/StringBuilder
    //   2278: dup
    //   2279: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2282: aload_1
    //   2283: getfield 1502	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2286: invokevirtual 634	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2289: ldc_w 284
    //   2292: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2295: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2298: invokespecial 1505	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2301: invokeinterface 1508 2 0
    //   2306: pop
    //   2307: goto -119 -> 2188
    //   2310: astore_1
    //   2311: getstatic 358	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2314: aload_0
    //   2315: getfield 402	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2318: ldc2_w 1509
    //   2321: getstatic 1516	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2324: invokeinterface 1521 5 0
    //   2329: pop
    //   2330: aload_0
    //   2331: ldc_w 1522
    //   2334: invokevirtual 1525	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2337: return
    //   2338: astore_1
    //   2339: ldc_w 1527
    //   2342: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2345: aload_1
    //   2346: invokevirtual 959	java/lang/Exception:printStackTrace	()V
    //   2349: iconst_1
    //   2350: putstatic 338	com/kochava/android/tracker/Feature:badInit	Z
    //   2353: return
    //   2354: ldc_w 1529
    //   2357: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2360: iconst_1
    //   2361: putstatic 338	com/kochava/android/tracker/Feature:badInit	Z
    //   2364: return
    //   2365: astore_1
    //   2366: new 622	java/lang/StringBuilder
    //   2369: dup
    //   2370: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2373: ldc_w 1531
    //   2376: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2379: aload_1
    //   2380: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2383: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2386: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2389: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2392: goto -2001 -> 391
    //   2395: new 622	java/lang/StringBuilder
    //   2398: dup
    //   2399: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2402: ldc_w 1533
    //   2405: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2408: getstatic 290	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2411: invokevirtual 736	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2414: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2417: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2420: goto -1951 -> 469
    //   2423: aload 8
    //   2425: astore_1
    //   2426: aload_3
    //   2427: ldc_w 1354
    //   2430: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2433: invokevirtual 1279	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2436: ldc_w 819
    //   2439: invokevirtual 995	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2442: ifeq -1672 -> 770
    //   2445: aload 8
    //   2447: astore_1
    //   2448: aload_3
    //   2449: ldc_w 1354
    //   2452: invokevirtual 817	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2455: checkcast 819	java/lang/Boolean
    //   2458: invokevirtual 822	java/lang/Boolean:booleanValue	()Z
    //   2461: ifeq -1691 -> 770
    //   2464: ldc -73
    //   2466: astore_1
    //   2467: goto -1697 -> 770
    //   2470: iload 4
    //   2472: sipush 360
    //   2475: if_icmple +42 -> 2517
    //   2478: new 622	java/lang/StringBuilder
    //   2481: dup
    //   2482: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2485: ldc_w 1371
    //   2488: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2491: iload 4
    //   2493: invokevirtual 1022	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2496: ldc_w 1535
    //   2499: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2502: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2505: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2508: ldc_w 1536
    //   2511: putstatic 292	com/kochava/android/tracker/Feature:flush_rate	I
    //   2514: goto -1325 -> 1189
    //   2517: iload 4
    //   2519: bipush 60
    //   2521: imul
    //   2522: sipush 1000
    //   2525: imul
    //   2526: putstatic 292	com/kochava/android/tracker/Feature:flush_rate	I
    //   2529: new 622	java/lang/StringBuilder
    //   2532: dup
    //   2533: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2536: ldc_w 1538
    //   2539: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2542: iload 4
    //   2544: invokevirtual 1022	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2547: ldc_w 1540
    //   2550: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2553: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2556: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2559: goto -1370 -> 1189
    //   2562: aload 13
    //   2564: ifnull +176 -> 2740
    //   2567: aload 13
    //   2569: invokevirtual 425	java/lang/String:trim	()Ljava/lang/String;
    //   2572: invokevirtual 429	java/lang/String:length	()I
    //   2575: ifeq +165 -> 2740
    //   2578: aload_0
    //   2579: getfield 1346	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2582: ldc_w 1352
    //   2585: aload 13
    //   2587: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2590: pop
    //   2591: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2594: ldc -118
    //   2596: ldc_w 284
    //   2599: invokeinterface 663 3 0
    //   2604: ldc_w 284
    //   2607: invokevirtual 667	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2610: ifeq +25 -> 2635
    //   2613: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2616: invokeinterface 1157 1 0
    //   2621: ldc -118
    //   2623: aload 13
    //   2625: invokeinterface 1163 3 0
    //   2630: invokeinterface 1166 1 0
    //   2635: aload 9
    //   2637: ifnull +76 -> 2713
    //   2640: aload 9
    //   2642: invokevirtual 425	java/lang/String:trim	()Ljava/lang/String;
    //   2645: invokevirtual 429	java/lang/String:length	()I
    //   2648: ifeq +65 -> 2713
    //   2651: aload 9
    //   2653: putstatic 615	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2656: aload_0
    //   2657: getfield 1348	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2660: ldc 96
    //   2662: aload 9
    //   2664: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2667: pop
    //   2668: aload_0
    //   2669: getfield 1350	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2672: ldc 96
    //   2674: aload 9
    //   2676: invokevirtual 645	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2679: pop
    //   2680: goto -1327 -> 1353
    //   2683: astore_1
    //   2684: new 622	java/lang/StringBuilder
    //   2687: dup
    //   2688: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2691: ldc_w 1542
    //   2694: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2697: aload_1
    //   2698: invokevirtual 1543	org/json/JSONException:toString	()Ljava/lang/String;
    //   2701: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2704: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2707: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2710: goto -1148 -> 1562
    //   2713: new 622	java/lang/StringBuilder
    //   2716: dup
    //   2717: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2720: ldc_w 1545
    //   2723: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2726: aload 13
    //   2728: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2731: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2734: putstatic 615	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2737: goto -1384 -> 1353
    //   2740: ldc_w 1547
    //   2743: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2746: iconst_1
    //   2747: putstatic 338	com/kochava/android/tracker/Feature:badInit	Z
    //   2750: return
    //   2751: astore_1
    //   2752: iconst_1
    //   2753: istore 4
    //   2755: new 622	java/lang/StringBuilder
    //   2758: dup
    //   2759: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2762: ldc_w 284
    //   2765: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2768: ldc_w 1549
    //   2771: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2774: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2777: astore_3
    //   2778: goto -1167 -> 1611
    //   2781: astore_1
    //   2782: new 622	java/lang/StringBuilder
    //   2785: dup
    //   2786: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2789: ldc_w 1551
    //   2792: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2795: aload_1
    //   2796: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2799: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2802: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2805: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2808: goto -1023 -> 1785
    //   2811: astore_1
    //   2812: new 622	java/lang/StringBuilder
    //   2815: dup
    //   2816: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2819: ldc_w 1553
    //   2822: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2825: aload_1
    //   2826: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2829: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2832: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2835: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2838: goto -1032 -> 1806
    //   2841: astore 5
    //   2843: aconst_null
    //   2844: astore_1
    //   2845: new 622	java/lang/StringBuilder
    //   2848: dup
    //   2849: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2852: ldc_w 1555
    //   2855: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2858: aload 5
    //   2860: invokevirtual 1556	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2863: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2866: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2869: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2872: goto -1002 -> 1870
    //   2875: astore_1
    //   2876: new 622	java/lang/StringBuilder
    //   2879: dup
    //   2880: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2883: ldc_w 1555
    //   2886: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2889: aload_1
    //   2890: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2893: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2896: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2899: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2902: goto -985 -> 1917
    //   2905: ldc_w 1558
    //   2908: astore_1
    //   2909: goto -1029 -> 1880
    //   2912: astore_1
    //   2913: new 622	java/lang/StringBuilder
    //   2916: dup
    //   2917: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2920: ldc_w 1560
    //   2923: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2926: aload_1
    //   2927: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2930: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2933: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2936: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2939: goto -954 -> 1985
    //   2942: astore_1
    //   2943: new 622	java/lang/StringBuilder
    //   2946: dup
    //   2947: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2950: ldc_w 1562
    //   2953: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2956: aload_1
    //   2957: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2960: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2963: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2966: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2969: goto -935 -> 2034
    //   2972: astore_1
    //   2973: new 622	java/lang/StringBuilder
    //   2976: dup
    //   2977: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   2980: ldc_w 1564
    //   2983: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2986: aload_1
    //   2987: invokevirtual 1048	java/lang/Exception:toString	()Ljava/lang/String;
    //   2990: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2993: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2996: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2999: goto -887 -> 2112
    //   3002: astore_1
    //   3003: getstatic 845	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3006: ifeq -862 -> 2144
    //   3009: aload_1
    //   3010: invokevirtual 848	org/json/JSONException:printStackTrace	()V
    //   3013: goto -869 -> 2144
    //   3016: astore_3
    //   3017: new 622	java/lang/StringBuilder
    //   3020: dup
    //   3021: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   3024: ldc_w 1566
    //   3027: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3030: aload 7
    //   3032: getfield 1496	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3035: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3038: ldc_w 1568
    //   3041: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3044: aload_3
    //   3045: invokevirtual 1556	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3048: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3051: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3054: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3057: goto -821 -> 2236
    //   3060: astore_1
    //   3061: goto -2541 -> 520
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3064	0	this	Feature
    //   0	3064	1	paramContext	Context
    //   0	3064	2	paramBoolean	boolean
    //   0	3064	3	paramHashMap	HashMap<String, Object>
    //   1151	1603	4	i	int
    //   590	1633	5	localObject1	Object
    //   2841	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   557	1642	6	localObject2	Object
    //   563	2468	7	localObject3	Object
    //   573	1873	8	localObject4	Object
    //   560	457	9	localIterator	Iterator
    //   1026	1	9	localException	Exception
    //   1073	1602	9	localObject5	Object
    //   570	669	10	localObject6	Object
    //   576	653	11	localObject7	Object
    //   582	773	12	localObject8	Object
    //   554	2173	13	localObject9	Object
    //   579	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   929	967	1026	java/lang/Exception
    //   967	1023	1026	java/lang/Exception
    //   2144	2188	2310	java/lang/Exception
    //   2188	2216	2310	java/lang/Exception
    //   2222	2234	2310	java/lang/Exception
    //   2240	2307	2310	java/lang/Exception
    //   3017	3057	2310	java/lang/Exception
    //   4	11	2338	java/lang/Exception
    //   372	391	2365	java/lang/Exception
    //   1269	1353	2683	org/json/JSONException
    //   1353	1562	2683	org/json/JSONException
    //   2567	2635	2683	org/json/JSONException
    //   2640	2680	2683	org/json/JSONException
    //   2713	2737	2683	org/json/JSONException
    //   2740	2750	2683	org/json/JSONException
    //   1593	1611	2751	android/content/pm/PackageManager$NameNotFoundException
    //   1767	1785	2781	java/lang/Exception
    //   1785	1806	2811	java/lang/Exception
    //   1858	1870	2841	android/content/pm/PackageManager$NameNotFoundException
    //   1848	1858	2875	java/lang/Exception
    //   1858	1870	2875	java/lang/Exception
    //   1874	1880	2875	java/lang/Exception
    //   1880	1917	2875	java/lang/Exception
    //   2845	2872	2875	java/lang/Exception
    //   1917	1985	2912	java/lang/Exception
    //   1985	2034	2942	java/lang/Exception
    //   2034	2112	2972	java/lang/Exception
    //   2130	2144	3002	org/json/JSONException
    //   2222	2234	3016	android/content/pm/PackageManager$NameNotFoundException
    //   497	520	3060	java/lang/Exception
  }
  
  private void initHandler()
  {
    if (this.initHandler == null) {
      this.initHandler = new Feature.13(this);
    }
  }
  
  private void initialHandlerActions()
  {
    int j = 0;
    prefs = appContext.getSharedPreferences("initPrefs", 0);
    if (prefs.getString("initBool", "").equals("")) {
      prefs.edit().putString("initBool", "false").apply();
    }
    String str1;
    String str2;
    if (prefs.getString("kochavaappdata", null) != null)
    {
      str1 = kDbAdapter.getApplicationData(prefs.getString("kochavaappdata", null));
      str2 = createAppData();
      Logging.Log("Stored Data: " + str1);
      Logging.Log("Created Data: " + str2);
      if (str1 == null) {
        kDbAdapter.insertApplicationData(prefs.getString("kochavaappdata", null), str2);
      }
    }
    for (;;)
    {
      if ((this.mIsStartOfLife) || ((!prefs.getString("initBool", "").equals("")) && (prefs.getString("initBool", "").equals("false"))))
      {
        Logging.Log("Initial event has not yet been qued in the database, making initial call");
        fireEvent("initial", null);
      }
      int i;
      if (!should_flush_in_background)
      {
        i = j;
        if (!should_flush_in_background)
        {
          i = j;
          if (is_in_background) {}
        }
      }
      else
      {
        i = 1;
      }
      if ((i != 0) && (mTimer == null))
      {
        mTimer = new Timer();
        mTimer.schedule(new Feature.14(this), 0L, flush_rate);
      }
      this.initTimer = new Timer();
      return;
      if (!str1.equals(str2))
      {
        kDbAdapter.updateApplicationData(prefs.getString("kochavaappdata", null), str2);
      }
      else
      {
        Logging.Log("Set start of life to false");
        this.mIsStartOfLife = false;
        continue;
        this.mIsStartOfLife = false;
      }
    }
  }
  
  private static boolean isEmailValid(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean isSystemPackage(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private static boolean oldLocationTooStale()
  {
    long l3 = LocationDirector.staleness * 60 * 1000;
    long l2 = 0L;
    long l1 = l2;
    if (prefs != null)
    {
      l1 = l2;
      if (prefs.getLong("kochava_old_loc_timestamp", 0L) != 0L) {
        l1 = prefs.getLong("kochava_old_loc_timestamp", 0L);
      }
    }
    if (l1 == 0L) {}
    while (System.currentTimeMillis() - l1 >= l3) {
      return true;
    }
    return false;
  }
  
  private static String postEvent()
  {
    Object localObject1;
    if (!prefs.getString("initBool", "").equals("true"))
    {
      Logging.Log("PREF_INIT not true, waiting for initial to be queued");
      localObject1 = "";
    }
    Object localObject3;
    long l;
    for (;;)
    {
      return localObject1;
      localObject1 = kDbAdapter.generateDataString();
      if (localObject1 == null) {
        return "";
      }
      localObject3 = new String[2];
      localObject1 = ((String)localObject1).split("=", 2);
      l = Long.parseLong(localObject1[0]);
      localObject3 = localObject1[1];
      Logging.Log("Post The Data 3>>>>>>" + (String)localObject3);
      int i = 0;
      if (((String)localObject3).contains("\"action\":\"initial\""))
      {
        Logging.LogError("Post Data: Event is initial, look at response");
        i = 1;
      }
      if ((hostControl == null) || (hostControl.trim().isEmpty()))
      {
        Logging.Log("postEvent - hostControl was empty, using default");
        hostControl = "control.kochava.com";
      }
      for (;;)
      {
        try
        {
          Logging.Log("postEvent - posting to " + "https://" + hostControl + "/track/kvTracker.php");
          localObject1 = (HttpsURLConnection)HttpInstrumentation.openConnection(new URL("https://" + hostControl + "/track/kvTracker.php").openConnection());
          ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", mUserAgent);
          ((HttpsURLConnection)localObject1).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpsURLConnection)localObject1).setRequestMethod("POST");
          ((HttpsURLConnection)localObject1).setConnectTimeout(30000);
          ((HttpsURLConnection)localObject1).setReadTimeout(30000);
          ((HttpsURLConnection)localObject1).setDoInput(true);
          ((HttpsURLConnection)localObject1).setDoOutput(true);
          ((HttpsURLConnection)localObject1).connect();
          localObject4 = new OutputStreamWriter(((HttpsURLConnection)localObject1).getOutputStream());
          ((OutputStreamWriter)localObject4).write((String)localObject3);
          ((OutputStreamWriter)localObject4).close();
        }
        catch (Exception localException)
        {
          Object localObject4;
          Logging.LogError("TrackTask " + localException);
          return "";
          Logging.LogError("TrackTask " + localException);
          continue;
        }
        catch (IOException localIOException2)
        {
          if (!localIOException2.getClass().equals(SSLException.class)) {
            break label665;
          }
        }
        try
        {
          Logging.Log("Grabbing Result...");
          localObject3 = new StringBuffer("");
          localObject1 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject1).getInputStream()));
          localObject4 = ((BufferedReader)localObject1).readLine();
          if (localObject4 != null) {
            ((StringBuffer)localObject3).append((String)localObject4);
          }
        }
        catch (IOException localIOException1)
        {
          if (!localIOException1.getClass().equals(SSLException.class)) {
            continue;
          }
          Logging.LogError("SSLException! Shutting down SDK and sending report." + localIOException1);
          httpsError(localIOException1);
          return "";
          localObject3 = ((StringBuffer)localObject3).toString();
          Logging.Log("Result: " + (String)localObject3);
          if (i != 0)
          {
            if (((String)localObject3).contains("\"success\":\"1\""))
            {
              Logging.Log("Got success response, cleaning database.");
              kDbAdapter.cleanupEvents(l);
            }
            Object localObject2 = localObject3;
            if (haveAttributionData()) {
              break;
            }
            localObject2 = localObject3;
            if (!requestAttributionData) {
              break;
            }
            Logging.Log("Requesting attribution data in " + get_attribution_wait + " seconds...");
            sendKVQuery(get_attribution_wait);
            return localObject3;
          }
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          Logging.LogError("TrackTask " + localOutOfMemoryError);
          return "";
        }
      }
    }
    kDbAdapter.cleanupEvents(l);
    return localObject3;
    Logging.LogError("SSLException! Shutting down SDK and sending report." + localIOException2);
    httpsError(localIOException2);
    for (;;)
    {
      return "";
      label665:
      Logging.LogError("TrackTask " + localIOException2);
    }
  }
  
  private void queInitial(boolean paramBoolean)
  {
    if ((prefs.getString("initBool", "").equals("false")) && (this.initialPropertiesObject != null) && (this.initialObject != null)) {
      try
      {
        Logging.Log("Initial properties: " + this.initialPropertiesObject);
        Logging.Log("Initital Oject: " + this.initialObject);
        if (!prefs.getString("initData", "noData").equals("noData"))
        {
          this.initialPropertiesObject.put("conversion_type", "gplay");
          this.initialPropertiesObject.put("conversion_data", prefs.getString("initData", ""));
          Logging.Log("Got referral, attaching: " + prefs.getString("initData", ""));
        }
        for (;;)
        {
          this.initialObject.put("data", this.initialPropertiesObject);
          kDbAdapter.addEvent(this.initialObject, true, false);
          Logging.Log("Sending Initial");
          prefs.edit().putString("initBool", "true").apply();
          if (!paramBoolean) {
            break;
          }
          this.initTimer.cancel();
          return;
          Logging.LogError("Did not get referral data.");
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        Logging.LogError("An error occured during que initial. " + localJSONException);
        if (Global.DEBUGERROR) {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  private static void registerRemoveToken(JSONObject paramJSONObject)
    throws JSONException
  {
    new Feature.15(paramJSONObject).start();
  }
  
  protected static void sendKVQuery(int paramInt)
  {
    worker.schedule(sendKVQuery, paramInt, TimeUnit.SECONDS);
  }
  
  private static void sendOutsideServicesUpdate(JSONArray paramJSONArray)
  {
    new Feature.18(paramJSONArray).start();
  }
  
  public static void setAttributionHandler(Handler paramHandler)
  {
    attributionDataHandler = paramHandler;
  }
  
  private void setCurrency(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      prefs = appContext.getSharedPreferences("initPrefs", 0);
      prefs.edit().putString("currency", paramString).apply();
    }
  }
  
  public static void setErrorDebug(boolean paramBoolean)
  {
    Logging.Log("setErrorDebug to " + paramBoolean);
    Global.DEBUGERROR = paramBoolean;
  }
  
  /* Error */
  private static void startAppSession()
  {
    // Byte code:
    //   0: getstatic 744	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +255 -> 260
    //   8: getstatic 290	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +249 -> 260
    //   14: getstatic 338	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 53
    //   22: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1806
    //   29: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 330	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 748	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +184 -> 225
    //   44: ldc_w 1808
    //   47: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 755	java/util/Timer
    //   53: dup
    //   54: invokespecial 1272	java/util/Timer:<init>	()V
    //   57: putstatic 748	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 748	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 1810	com/kochava/android/tracker/Feature$10
    //   66: dup
    //   67: invokespecial 1811	com/kochava/android/tracker/Feature$10:<init>	()V
    //   70: getstatic 292	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 292	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1593	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 628	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 629
    //   87: ldiv
    //   88: putstatic 321	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 317	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 492	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 651	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1815	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1818	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +46 -> 161
    //   118: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -94
    //   123: ldc_w 284
    //   126: invokeinterface 663 3 0
    //   131: ldc_w 284
    //   134: invokevirtual 667	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +22 -> 161
    //   142: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   145: ldc -94
    //   147: ldc_w 284
    //   150: invokeinterface 663 3 0
    //   155: invokestatic 1823	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   158: invokestatic 1825	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   161: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   164: ifnull +96 -> 260
    //   167: getstatic 554	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   170: ldc -112
    //   172: ldc_w 284
    //   175: invokeinterface 663 3 0
    //   180: ldc -73
    //   182: invokevirtual 667	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   185: ifeq +75 -> 260
    //   188: getstatic 340	com/kochava/android/tracker/Feature:canSendSession	Z
    //   191: ifeq +63 -> 254
    //   194: ldc 122
    //   196: invokestatic 761	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   199: return
    //   200: astore_1
    //   201: new 622	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 623	java/lang/StringBuilder:<init>	()V
    //   208: ldc_w 1827
    //   211: invokevirtual 637	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: aload_1
    //   215: invokevirtual 678	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   218: invokevirtual 640	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   221: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   224: return
    //   225: ldc_w 1829
    //   228: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   231: goto -150 -> 81
    //   234: astore_1
    //   235: ldc_w 1831
    //   238: invokestatic 683	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   241: getstatic 845	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   244: ifeq -83 -> 161
    //   247: aload_1
    //   248: invokevirtual 848	org/json/JSONException:printStackTrace	()V
    //   251: goto -90 -> 161
    //   254: ldc_w 767
    //   257: invokestatic 739	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   260: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   137	2	0	bool	boolean
    //   200	15	1	localException	Exception
    //   234	14	1	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   0	25	200	java/lang/Exception
    //   26	81	200	java/lang/Exception
    //   81	112	200	java/lang/Exception
    //   112	138	200	java/lang/Exception
    //   142	161	200	java/lang/Exception
    //   161	199	200	java/lang/Exception
    //   225	231	200	java/lang/Exception
    //   235	251	200	java/lang/Exception
    //   254	260	200	java/lang/Exception
    //   142	161	234	org/json/JSONException
  }
  
  public void clearSuperProperties()
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    Logging.Log("clearSuperProperties");
    mSuperProperties = null;
  }
  
  public void deepLinkEvent(String paramString)
  {
    if (badInit) {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
    }
    for (;;)
    {
      return;
      try
      {
        Logging.Log("Got deep link event with uri" + paramString);
        JSONObject localJSONObject1 = new JSONObject();
        localJSONObject1.put("action", "deeplink");
        localJSONObject1.put("kochava_app_id", mAppId);
        localJSONObject1.put("kochava_device_id", getMyDeviceId());
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("uri", paramString);
        localJSONObject2.put("usertime", System.currentTimeMillis() / 1000L + "");
        localJSONObject1.put("data", localJSONObject2);
        int i = kDbAdapter.addEvent(localJSONObject1, false, false);
        Logging.Log("deep link event: " + localJSONObject1);
        if (i >= 50)
        {
          flush();
          return;
        }
      }
      catch (Exception paramString)
      {
        Logging.LogError("Error in deep link event call: " + paramString);
      }
    }
  }
  
  public void endSession()
  {
    for (;;)
    {
      try
      {
        if (!overrideAutomaticSessions) {
          return;
        }
        if (badInit)
        {
          Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
          return;
        }
        Logging.Log("Manual Session End");
        if (!should_flush_in_background)
        {
          if (mTimer != null)
          {
            Logging.Log("Session end, flush timer was on, canceling timer and flushing current events.");
            flush();
            mTimer.cancel();
            mTimer = null;
          }
        }
        else
        {
          if (!canSendSession) {
            break;
          }
          eventSession("exit");
          return;
        }
      }
      catch (Exception localException)
      {
        Logging.LogError("(Manual Session End) Exception occured during session tracking.\n" + localException);
        return;
      }
      Logging.Log("Session end, flush timer was already off.");
    }
    Logging.Log("Session events disabled by server.");
  }
  
  public void event(String paramString1, String paramString2)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      Logging.Log("Got event " + paramString1);
      HashMap localHashMap = new HashMap();
      localHashMap.put("event_name", paramString1);
      localHashMap.put("event_data", paramString2);
      fireEventBlacklist("event", localHashMap);
      return;
    }
    catch (Exception paramString1)
    {
      Logging.LogError("Error in event call: " + paramString1);
    }
  }
  
  public void eventSpatial(String paramString1, double paramDouble1, double paramDouble2, double paramDouble3, String paramString2)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      Logging.Log("Got event " + paramString1);
      HashMap localHashMap = new HashMap();
      localHashMap.put("event_name", paramString1);
      paramString1 = new DecimalFormat("#.##");
      localHashMap.put("x", Double.valueOf(paramString1.format(paramDouble1)).toString());
      localHashMap.put("y", Double.valueOf(paramString1.format(paramDouble2)).toString());
      localHashMap.put("z", Double.valueOf(paramString1.format(paramDouble3)).toString());
      localHashMap.put("event_data", paramString2);
      fireEventBlacklist("spatial", localHashMap);
      return;
    }
    catch (Exception paramString1)
    {
      Logging.LogError("Error in event call: " + paramString1);
    }
  }
  
  public void linkIdentity(Map<String, String> paramMap)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    Logging.Log("Mapping identity");
    fireEvent("identityLink", paramMap);
  }
  
  public void registerSuperProperties(Map<String, String> paramMap)
  {
    if (badInit) {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
    }
    for (;;)
    {
      return;
      Logging.Log("registerSuperProperties");
      if (mSuperProperties == null)
      {
        mSuperProperties = paramMap;
        return;
      }
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        mSuperProperties.put(localEntry.getKey(), localEntry.getValue());
      }
    }
  }
  
  public void setAppLimitTracking(boolean paramBoolean)
  {
    this.app_limit_tracking = paramBoolean;
    tryUpdate("setAppLimitTracking");
  }
  
  public void setLatlong(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
    {
      Logging.LogError("Trying to set lat/long, but one/both of the parameters where null.");
      return;
    }
    prefs = appContext.getSharedPreferences("initPrefs", 0);
    prefs.edit().putString("mylat", paramString1).apply();
    prefs.edit().putString("mylong", paramString2).apply();
  }
  
  public void startSession()
  {
    for (;;)
    {
      try
      {
        if (!overrideAutomaticSessions) {
          return;
        }
        if (badInit)
        {
          Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
          return;
        }
        Logging.Log("Manual Session Start");
        if (!should_flush_in_background)
        {
          if (mTimer == null)
          {
            Logging.Log("Session start, flush timer was off and is not first launch, starting periodic flush timer.");
            mTimer = new Timer();
            mTimer.schedule(new Feature.9(this), flush_rate, flush_rate);
          }
        }
        else
        {
          startTime = System.currentTimeMillis() / 1000L;
          if ((doGatherLocation) && (oldLocationTooStale())) {
            LocationDirector.getInstance(appContext).getLocation();
          }
          if ((prefs == null) || (!prefs.getString("initBool", "").equals("true"))) {
            return;
          }
          if (!canSendSession) {
            break;
          }
          eventSession("launch");
          return;
        }
      }
      catch (Exception localException)
      {
        Logging.LogError("(Manual Session Start) Exception occured during session tracking.\n" + localException);
        return;
      }
      Logging.Log("Session start, flush timer was already on.");
    }
    Logging.Log("Session events disabled by server.");
  }
  
  protected void tryUpdate(String paramString)
  {
    paramString = new Feature.8(this, paramString);
    worker.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
  
  public final class INPUTITEMS
  {
    public static final String APP_LIMIT_TRACKING = "app_limit_tracking";
    public static final String BIDDER_HOST = "bidder_host";
    public static final String CONTROL_HOST = "control_host";
    public static final String CURRENCY = "currency";
    public static final String DEBUG_ON = "debug";
    public static final String FLUSH_IN_BACKGROUND = "flush_in_background";
    public static final String IDENTITY_LINK = "identity_link";
    public static final String KOCHAVA_APP_ID = "kochava_app_id";
    public static final String PARTNER_NAME = "partner_name";
    public static final String REQUEST_ATTRIBUTION = "request_attribution";
    
    private INPUTITEMS() {}
  }
}
