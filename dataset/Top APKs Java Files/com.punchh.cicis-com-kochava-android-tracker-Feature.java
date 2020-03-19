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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.URL;
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
  private static final String ACTION = "action";
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
  private static final String INIT = "init";
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
  protected static final String PREF_USERAGENT = "useragent";
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
  protected static Handler locationHandler = new Feature.21();
  private static String mAppId;
  private static String mEnvDeviceID;
  private static String mKochDevIDStrategy;
  private static Map<String, String> mSuperProperties;
  private static Timer mTimer;
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
    new Feature.15(paramString).start();
  }
  
  private void fireEvent(String paramString, Map<String, String> paramMap)
  {
    if ((!paramString.equals("initial")) && (!should_flush_in_background) && (is_in_background) && (!event_flush_triggered))
    {
      event_flush_triggered = true;
      this.eventFlushTimer.schedule(new Feature.16(this), 60000L);
    }
    Logging.Log("FIRE EVENT*** action:" + paramString);
    Logging.Log("FIRE EVENT*** properties:" + paramMap);
    JSONObject localJSONObject1 = new JSONObject();
    label1080:
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
              localJSONObject1.put("sdk_version", "Android20160222" + versionExtension);
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
                  break label1080;
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
    //   8: getstatic 375	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 428	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 46
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1083	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1088 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1091 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1094 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 46
    //   75: invokeinterface 1098 2 0
    //   80: invokeinterface 1099 2 0
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
    //   99: invokeinterface 1091 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1094 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 639	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1101
    //   130: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1091 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1094 1 0
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
    //   190: invokeinterface 1091 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1094 1 0
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
    new Feature.20(paramException).start();
  }
  
  /* Error */
  @android.annotation.TargetApi(14)
  private void init(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +2407 -> 2408
    //   4: aload_1
    //   5: invokevirtual 1272	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 322	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   15: ldc -123
    //   17: new 639	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 1274
    //   27: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: getstatic 295	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   33: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokestatic 1280	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   42: pop
    //   43: aload_0
    //   44: new 772	java/util/Timer
    //   47: dup
    //   48: invokespecial 1281	java/util/Timer:<init>	()V
    //   51: putfield 799	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   54: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   57: ldc -95
    //   59: iconst_0
    //   60: invokevirtual 674	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   63: putstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   66: new 995	com/kochava/android/tracker/DbAdapter
    //   69: dup
    //   70: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   73: invokespecial 1282	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   76: putstatic 558	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   79: aload_3
    //   80: ifnull +231 -> 311
    //   83: aload_3
    //   84: ldc_w 1284
    //   87: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   90: ifnull +44 -> 134
    //   93: aload_3
    //   94: ldc_w 1284
    //   97: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   100: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   103: ldc_w 832
    //   106: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   109: ifeq +25 -> 134
    //   112: aload_3
    //   113: ldc_w 1284
    //   116: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   119: checkcast 832	java/lang/Boolean
    //   122: invokevirtual 835	java/lang/Boolean:booleanValue	()Z
    //   125: istore_2
    //   126: iload_2
    //   127: invokestatic 1290	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   130: iload_2
    //   131: invokestatic 1293	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   134: aload_3
    //   135: ldc_w 1295
    //   138: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   141: ifnull +35 -> 176
    //   144: aload_3
    //   145: ldc_w 1295
    //   148: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: ldc_w 428
    //   157: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   160: ifeq +16 -> 176
    //   163: aload_3
    //   164: ldc_w 1295
    //   167: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 428	java/lang/String
    //   173: putstatic 295	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   176: aload_3
    //   177: ldc_w 1297
    //   180: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: ifnull +38 -> 221
    //   186: aload_3
    //   187: ldc_w 1297
    //   190: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   196: ldc_w 832
    //   199: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   202: ifeq +19 -> 221
    //   205: aload_3
    //   206: ldc_w 1297
    //   209: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   212: checkcast 832	java/lang/Boolean
    //   215: invokevirtual 835	java/lang/Boolean:booleanValue	()Z
    //   218: putstatic 297	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   221: aload_3
    //   222: ldc_w 1299
    //   225: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   228: ifnull +38 -> 266
    //   231: aload_3
    //   232: ldc_w 1299
    //   235: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: ldc_w 832
    //   244: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   247: ifeq +19 -> 266
    //   250: aload_3
    //   251: ldc_w 1299
    //   254: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   257: checkcast 832	java/lang/Boolean
    //   260: invokevirtual 835	java/lang/Boolean:booleanValue	()Z
    //   263: putstatic 320	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   266: aload_3
    //   267: ldc_w 1301
    //   270: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   273: ifnull +38 -> 311
    //   276: aload_3
    //   277: ldc_w 1301
    //   280: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   283: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   286: ldc_w 832
    //   289: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +19 -> 311
    //   295: aload_3
    //   296: ldc_w 1301
    //   299: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   302: checkcast 832	java/lang/Boolean
    //   305: invokevirtual 835	java/lang/Boolean:booleanValue	()Z
    //   308: putstatic 337	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   311: aload_0
    //   312: invokespecial 1303	com/kochava/android/tracker/Feature:initHandler	()V
    //   315: new 639	java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   322: ldc_w 1305
    //   325: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   331: ldc 66
    //   333: ldc_w 291
    //   336: invokeinterface 680 3 0
    //   341: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   350: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   353: ldc 66
    //   355: ldc_w 291
    //   358: invokeinterface 680 3 0
    //   363: ldc_w 291
    //   366: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   369: ifne +26 -> 395
    //   372: new 382	org/json/JSONArray
    //   375: dup
    //   376: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   379: ldc 66
    //   381: ldc_w 291
    //   384: invokeinterface 680 3 0
    //   389: invokespecial 1306	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   392: putstatic 385	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   395: getstatic 761	android/os/Build$VERSION:SDK_INT	I
    //   398: bipush 14
    //   400: if_icmplt +2049 -> 2449
    //   403: getstatic 297	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   406: ifne +2043 -> 2449
    //   409: new 639	java/lang/StringBuilder
    //   412: dup
    //   413: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   416: ldc_w 1308
    //   419: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   422: getstatic 297	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   425: invokevirtual 753	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   428: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   431: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   434: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   437: checkcast 1310	android/app/Application
    //   440: new 12	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   443: dup
    //   444: aload_0
    //   445: invokespecial 1311	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   448: invokevirtual 1315	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   451: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   454: new 14	com/kochava/android/tracker/Feature$MemoryBoss
    //   457: dup
    //   458: aload_0
    //   459: invokespecial 1316	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   462: invokevirtual 1320	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   465: iconst_1
    //   466: putstatic 1323	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   469: iconst_1
    //   470: putstatic 1326	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   473: new 1328	com/kochava/android/tracker/Feature$3
    //   476: dup
    //   477: aload_0
    //   478: invokespecial 1329	com/kochava/android/tracker/Feature$3:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   481: invokevirtual 793	java/lang/Thread:start	()V
    //   484: getstatic 320	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   487: ifne +14 -> 501
    //   490: new 1331	com/kochava/android/tracker/Feature$4
    //   493: dup
    //   494: aload_0
    //   495: invokespecial 1332	com/kochava/android/tracker/Feature$4:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   498: invokevirtual 793	java/lang/Thread:start	()V
    //   501: aload_0
    //   502: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   505: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   508: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   511: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   514: iconst_0
    //   515: invokevirtual 1345	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   518: getfield 1350	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   521: putfield 1068	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   524: aload_0
    //   525: new 659	org/json/JSONObject
    //   528: dup
    //   529: invokespecial 811	org/json/JSONObject:<init>	()V
    //   532: putfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   535: aload_0
    //   536: new 659	org/json/JSONObject
    //   539: dup
    //   540: invokespecial 811	org/json/JSONObject:<init>	()V
    //   543: putfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   546: aload_0
    //   547: new 659	org/json/JSONObject
    //   550: dup
    //   551: invokespecial 811	org/json/JSONObject:<init>	()V
    //   554: putfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   557: aconst_null
    //   558: astore 13
    //   560: aconst_null
    //   561: astore 6
    //   563: aconst_null
    //   564: astore 9
    //   566: aconst_null
    //   567: astore 7
    //   569: ldc_w 822
    //   572: astore_1
    //   573: aconst_null
    //   574: astore 10
    //   576: aconst_null
    //   577: astore 8
    //   579: aconst_null
    //   580: astore 11
    //   582: aconst_null
    //   583: astore 14
    //   585: aload_1
    //   586: astore 12
    //   588: aload_3
    //   589: ifnull +627 -> 1216
    //   592: aload 6
    //   594: astore 5
    //   596: aload_3
    //   597: ldc_w 1358
    //   600: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   603: ifnull +38 -> 641
    //   606: aload 6
    //   608: astore 5
    //   610: aload_3
    //   611: ldc_w 1358
    //   614: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   617: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   620: ldc_w 428
    //   623: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   626: ifeq +15 -> 641
    //   629: aload_3
    //   630: ldc_w 1358
    //   633: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   636: checkcast 428	java/lang/String
    //   639: astore 5
    //   641: aload 7
    //   643: astore 6
    //   645: aload_3
    //   646: ldc 101
    //   648: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   651: ifnull +36 -> 687
    //   654: aload 7
    //   656: astore 6
    //   658: aload_3
    //   659: ldc 101
    //   661: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   664: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   667: ldc_w 428
    //   670: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   673: ifeq +14 -> 687
    //   676: aload_3
    //   677: ldc 101
    //   679: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   682: checkcast 428	java/lang/String
    //   685: astore 6
    //   687: aload_1
    //   688: astore 7
    //   690: aload_3
    //   691: ldc -110
    //   693: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   696: ifnull +35 -> 731
    //   699: aload_1
    //   700: astore 7
    //   702: aload_3
    //   703: ldc -110
    //   705: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   708: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   711: ldc_w 428
    //   714: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   717: ifeq +14 -> 731
    //   720: aload_3
    //   721: ldc -110
    //   723: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   726: checkcast 428	java/lang/String
    //   729: astore 7
    //   731: aload 8
    //   733: astore_1
    //   734: aload_3
    //   735: ldc_w 1360
    //   738: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   741: ifnull +33 -> 774
    //   744: aload_3
    //   745: ldc_w 1360
    //   748: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   751: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   754: ldc_w 428
    //   757: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   760: ifeq +1717 -> 2477
    //   763: aload_3
    //   764: ldc_w 1360
    //   767: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   770: checkcast 428	java/lang/String
    //   773: astore_1
    //   774: aload_3
    //   775: ldc_w 1362
    //   778: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   781: ifnull +34 -> 815
    //   784: aload_3
    //   785: ldc_w 1362
    //   788: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   791: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   794: ldc_w 428
    //   797: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   800: ifeq +15 -> 815
    //   803: aload_3
    //   804: ldc_w 1362
    //   807: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   810: checkcast 428	java/lang/String
    //   813: astore 8
    //   815: aload 14
    //   817: astore 8
    //   819: aload_3
    //   820: ldc_w 1364
    //   823: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   826: ifnull +38 -> 864
    //   829: aload 14
    //   831: astore 8
    //   833: aload_3
    //   834: ldc_w 1364
    //   837: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   843: ldc_w 428
    //   846: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   849: ifeq +15 -> 864
    //   852: aload_3
    //   853: ldc_w 1364
    //   856: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   859: checkcast 428	java/lang/String
    //   862: astore 8
    //   864: aload_3
    //   865: ldc_w 908
    //   868: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   871: ifnull +39 -> 910
    //   874: aload_3
    //   875: ldc_w 908
    //   878: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   881: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   884: ldc_w 832
    //   887: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   890: ifeq +20 -> 910
    //   893: aload_0
    //   894: aload_3
    //   895: ldc_w 908
    //   898: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   901: checkcast 832	java/lang/Boolean
    //   904: invokevirtual 835	java/lang/Boolean:booleanValue	()Z
    //   907: putfield 400	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   910: aload_3
    //   911: ldc_w 926
    //   914: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   917: ifnull +115 -> 1032
    //   920: aload_3
    //   921: ldc_w 926
    //   924: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   927: instanceof 425
    //   930: ifeq +102 -> 1032
    //   933: aload_3
    //   934: ldc_w 926
    //   937: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   940: checkcast 425	java/util/HashMap
    //   943: putstatic 1366	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   946: new 659	org/json/JSONObject
    //   949: dup
    //   950: invokespecial 811	org/json/JSONObject:<init>	()V
    //   953: putstatic 924	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   956: getstatic 1366	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   959: invokeinterface 978 1 0
    //   964: invokeinterface 981 1 0
    //   969: astore 9
    //   971: aload 9
    //   973: invokeinterface 709 1 0
    //   978: ifeq +54 -> 1032
    //   981: aload 9
    //   983: invokeinterface 713 1 0
    //   988: checkcast 983	java/util/Map$Entry
    //   991: astore 10
    //   993: getstatic 924	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   996: aload 10
    //   998: invokeinterface 986 1 0
    //   1003: checkcast 428	java/lang/String
    //   1006: aload 10
    //   1008: invokeinterface 989 1 0
    //   1013: checkcast 428	java/lang/String
    //   1016: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1019: pop
    //   1020: aload 9
    //   1022: invokeinterface 1369 1 0
    //   1027: goto -56 -> 971
    //   1030: astore 9
    //   1032: aload_3
    //   1033: ldc_w 932
    //   1036: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1039: ifnull +36 -> 1075
    //   1042: aload_3
    //   1043: ldc_w 932
    //   1046: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1049: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1052: ldc_w 428
    //   1055: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1058: ifeq +17 -> 1075
    //   1061: aload_0
    //   1062: aload_3
    //   1063: ldc_w 932
    //   1066: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1069: checkcast 428	java/lang/String
    //   1072: putfield 928	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
    //   1075: aload 6
    //   1077: astore 9
    //   1079: aload_1
    //   1080: astore 10
    //   1082: aload 8
    //   1084: astore 11
    //   1086: aload 7
    //   1088: astore 12
    //   1090: aload 5
    //   1092: astore 13
    //   1094: aload_3
    //   1095: ldc_w 1370
    //   1098: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1101: ifnull +115 -> 1216
    //   1104: aload 6
    //   1106: astore 9
    //   1108: aload_1
    //   1109: astore 10
    //   1111: aload 8
    //   1113: astore 11
    //   1115: aload 7
    //   1117: astore 12
    //   1119: aload 5
    //   1121: astore 13
    //   1123: aload_3
    //   1124: ldc_w 1370
    //   1127: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1130: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1133: ldc_w 1372
    //   1136: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1139: ifeq +77 -> 1216
    //   1142: aload_3
    //   1143: ldc_w 1370
    //   1146: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1149: checkcast 1372	java/lang/Integer
    //   1152: invokevirtual 1375	java/lang/Integer:intValue	()I
    //   1155: istore 4
    //   1157: iload 4
    //   1159: iconst_1
    //   1160: if_icmpge +1364 -> 2524
    //   1163: new 639	java/lang/StringBuilder
    //   1166: dup
    //   1167: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1170: ldc_w 1377
    //   1173: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: iload 4
    //   1178: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1181: ldc_w 1379
    //   1184: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1187: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1190: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1193: iconst_1
    //   1194: putstatic 301	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
    //   1197: aload 5
    //   1199: astore 13
    //   1201: aload 7
    //   1203: astore 12
    //   1205: aload 8
    //   1207: astore 11
    //   1209: aload_1
    //   1210: astore 10
    //   1212: aload 6
    //   1214: astore 9
    //   1216: aload 11
    //   1218: ifnull +19 -> 1237
    //   1221: aload 11
    //   1223: invokevirtual 432	java/lang/String:trim	()Ljava/lang/String;
    //   1226: invokevirtual 436	java/lang/String:length	()I
    //   1229: ifeq +8 -> 1237
    //   1232: aload 11
    //   1234: putstatic 293	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1237: aload 10
    //   1239: ifnull +17 -> 1256
    //   1242: aload 10
    //   1244: ldc -65
    //   1246: invokevirtual 1382	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1249: ifeq +7 -> 1256
    //   1252: iconst_1
    //   1253: putstatic 351	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1256: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1259: ldc 52
    //   1261: iconst_0
    //   1262: invokevirtual 674	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1265: putstatic 522	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1268: aload 9
    //   1270: ifnull +1346 -> 2616
    //   1273: aload 9
    //   1275: invokevirtual 432	java/lang/String:trim	()Ljava/lang/String;
    //   1278: invokevirtual 436	java/lang/String:length	()I
    //   1281: ifeq +1335 -> 2616
    //   1284: aload 9
    //   1286: putstatic 631	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1289: aload_0
    //   1290: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1293: ldc 101
    //   1295: aload 9
    //   1297: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1300: pop
    //   1301: aload_0
    //   1302: getfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1305: ldc 101
    //   1307: aload 9
    //   1309: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1312: pop
    //   1313: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1316: ldc -113
    //   1318: ldc_w 291
    //   1321: invokeinterface 680 3 0
    //   1326: ldc_w 291
    //   1329: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1332: ifeq +25 -> 1357
    //   1335: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1338: invokeinterface 1167 1 0
    //   1343: ldc -113
    //   1345: aload 9
    //   1347: invokeinterface 1173 3 0
    //   1352: invokeinterface 1176 1 0
    //   1357: aload_0
    //   1358: aload 12
    //   1360: invokespecial 465	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1363: aload_0
    //   1364: getfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1367: ldc_w 1384
    //   1370: aload_0
    //   1371: invokespecial 899	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1374: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1377: pop
    //   1378: aload_0
    //   1379: getfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1382: ldc_w 1386
    //   1385: ldc_w 1388
    //   1388: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1391: pop
    //   1392: aload_0
    //   1393: getfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1396: ldc_w 1390
    //   1399: ldc_w 1392
    //   1402: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1405: pop
    //   1406: aload_0
    //   1407: getfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1410: ldc -110
    //   1412: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1415: ldc -110
    //   1417: ldc_w 822
    //   1420: invokeinterface 680 3 0
    //   1425: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1428: pop
    //   1429: aload_0
    //   1430: getfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1433: ldc -110
    //   1435: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1438: ldc -110
    //   1440: ldc_w 822
    //   1443: invokeinterface 680 3 0
    //   1448: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1451: pop
    //   1452: aload_0
    //   1453: getfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1456: ldc_w 1390
    //   1459: ldc_w 1392
    //   1462: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1465: pop
    //   1466: aload_0
    //   1467: getfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1470: ldc -110
    //   1472: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1475: ldc -110
    //   1477: ldc_w 822
    //   1480: invokeinterface 680 3 0
    //   1485: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1488: pop
    //   1489: aload_0
    //   1490: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1493: ldc_w 865
    //   1496: new 639	java/lang/StringBuilder
    //   1499: dup
    //   1500: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1503: ldc_w 867
    //   1506: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: getstatic 295	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1512: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1515: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1518: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1521: pop
    //   1522: aload_0
    //   1523: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1526: ldc_w 1394
    //   1529: ldc_w 1396
    //   1532: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1535: pop
    //   1536: aload_0
    //   1537: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1540: ldc_w 991
    //   1543: aload_0
    //   1544: getfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1547: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1550: pop
    //   1551: aload_0
    //   1552: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1555: ldc_w 1398
    //   1558: aload_0
    //   1559: getfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1562: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1565: pop
    //   1566: aload_0
    //   1567: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1570: ldc 28
    //   1572: ldc 93
    //   1574: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1577: pop
    //   1578: invokestatic 645	java/lang/System:currentTimeMillis	()J
    //   1581: ldc2_w 646
    //   1584: ldiv
    //   1585: putstatic 328	com/kochava/android/tracker/Feature:startTime	J
    //   1588: iconst_0
    //   1589: istore 4
    //   1591: ldc_w 291
    //   1594: astore_3
    //   1595: new 720	android/content/ComponentName
    //   1598: dup
    //   1599: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1602: ldc_w 1400
    //   1605: invokespecial 732	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1608: astore_1
    //   1609: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1612: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1615: aload_1
    //   1616: iconst_0
    //   1617: invokevirtual 1404	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1620: pop
    //   1621: ldc_w 1406
    //   1624: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1627: aload_3
    //   1628: astore_1
    //   1629: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1632: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1635: ldc_w 1408
    //   1638: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1641: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1644: invokevirtual 1411	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1647: ifge +27 -> 1674
    //   1650: iconst_1
    //   1651: istore 4
    //   1653: new 639	java/lang/StringBuilder
    //   1656: dup
    //   1657: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1660: aload_3
    //   1661: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1664: ldc_w 1413
    //   1667: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1670: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1673: astore_1
    //   1674: aload_1
    //   1675: astore_3
    //   1676: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1679: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1682: ldc_w 1415
    //   1685: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1688: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1691: invokevirtual 1411	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1694: ifge +27 -> 1721
    //   1697: iconst_1
    //   1698: istore 4
    //   1700: new 639	java/lang/StringBuilder
    //   1703: dup
    //   1704: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1707: aload_1
    //   1708: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1711: ldc_w 1417
    //   1714: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1717: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1720: astore_3
    //   1721: aload_3
    //   1722: astore_1
    //   1723: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1726: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1729: ldc_w 1419
    //   1732: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1735: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1738: invokevirtual 1411	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1741: ifge +27 -> 1768
    //   1744: iconst_1
    //   1745: istore 4
    //   1747: new 639	java/lang/StringBuilder
    //   1750: dup
    //   1751: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1754: aload_3
    //   1755: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1758: ldc_w 1421
    //   1761: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1764: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1767: astore_1
    //   1768: iload 4
    //   1770: ifeq +13 -> 1783
    //   1773: ldc_w 1423
    //   1776: invokestatic 1426	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1779: aload_1
    //   1780: invokestatic 1426	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1783: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1786: ldc_w 1428
    //   1789: invokevirtual 948	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1792: checkcast 1430	android/telephony/TelephonyManager
    //   1795: invokevirtual 1433	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1798: putstatic 879	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1801: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1804: ldc_w 1435
    //   1807: invokevirtual 948	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1810: checkcast 1437	android/net/wifi/WifiManager
    //   1813: invokevirtual 1441	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1816: invokevirtual 1446	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1819: putstatic 875	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1822: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1825: ldc -89
    //   1827: ldc_w 291
    //   1830: invokeinterface 680 3 0
    //   1835: ldc_w 291
    //   1838: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1841: ifeq +27 -> 1868
    //   1844: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1847: invokeinterface 1167 1 0
    //   1852: ldc -89
    //   1854: aload_0
    //   1855: invokespecial 544	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1858: invokeinterface 1173 3 0
    //   1863: invokeinterface 1176 1 0
    //   1868: aload_0
    //   1869: invokestatic 625	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1872: putfield 1448	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1875: aload_0
    //   1876: invokestatic 621	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1879: putfield 1450	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1882: aload_0
    //   1883: ldc_w 1452
    //   1886: putfield 1072	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1889: aload_0
    //   1890: ldc_w 1454
    //   1893: putfield 1076	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1896: aload_0
    //   1897: ldc_w 291
    //   1900: putfield 533	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1903: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1906: invokevirtual 1272	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1909: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1912: astore_3
    //   1913: aload_3
    //   1914: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1917: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1920: iconst_0
    //   1921: invokevirtual 1458	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1924: astore_1
    //   1925: aload_1
    //   1926: ifnull +1033 -> 2959
    //   1929: aload_3
    //   1930: aload_1
    //   1931: invokevirtual 1462	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1934: astore_1
    //   1935: aload_0
    //   1936: aload_1
    //   1937: checkcast 428	java/lang/String
    //   1940: checkcast 428	java/lang/String
    //   1943: putfield 1072	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1946: new 639	java/lang/StringBuilder
    //   1949: dup
    //   1950: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1953: ldc_w 1464
    //   1956: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1959: aload_0
    //   1960: getfield 1072	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1963: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1966: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1969: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1972: aload_0
    //   1973: new 639	java/lang/StringBuilder
    //   1976: dup
    //   1977: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   1980: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1983: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1986: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1989: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1992: iconst_0
    //   1993: invokevirtual 1345	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1996: getfield 1467	android/content/pm/PackageInfo:versionCode	I
    //   1999: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2002: ldc_w 291
    //   2005: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2008: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2011: putfield 1076	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2014: new 639	java/lang/StringBuilder
    //   2017: dup
    //   2018: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2021: ldc_w 1469
    //   2024: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2027: aload_0
    //   2028: getfield 1076	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2031: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2034: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2037: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2040: aload_0
    //   2041: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2044: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2047: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2050: invokevirtual 1339	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2053: iconst_0
    //   2054: invokevirtual 1345	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2057: getfield 1472	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2060: putfield 533	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2063: new 639	java/lang/StringBuilder
    //   2066: dup
    //   2067: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2070: ldc_w 1474
    //   2073: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2076: aload_0
    //   2077: getfield 533	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2080: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2083: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2086: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2089: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2092: ldc_w 944
    //   2095: invokevirtual 948	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2098: checkcast 950	android/view/WindowManager
    //   2101: astore_1
    //   2102: aload_0
    //   2103: aload_1
    //   2104: invokeinterface 957 1 0
    //   2109: invokevirtual 1477	android/view/Display:getHeight	()I
    //   2112: putfield 890	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2115: aload_0
    //   2116: aload_1
    //   2117: invokeinterface 957 1 0
    //   2122: invokevirtual 1480	android/view/Display:getWidth	()I
    //   2125: putfield 894	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2128: new 639	java/lang/StringBuilder
    //   2131: dup
    //   2132: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2135: ldc_w 1482
    //   2138: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2141: aload_0
    //   2142: getfield 890	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2145: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2148: ldc_w 1484
    //   2151: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2154: aload_0
    //   2155: getfield 894	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2158: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2161: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2164: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2167: new 1486	com/kochava/android/tracker/Feature$5
    //   2170: dup
    //   2171: aload_0
    //   2172: invokespecial 1487	com/kochava/android/tracker/Feature$5:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   2175: invokevirtual 793	java/lang/Thread:start	()V
    //   2178: aload_0
    //   2179: invokestatic 518	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2182: putfield 1489	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2185: aload_0
    //   2186: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2189: ldc_w 813
    //   2192: invokestatic 518	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2195: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2198: pop
    //   2199: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2202: ldc -107
    //   2204: ldc_w 291
    //   2207: invokeinterface 680 3 0
    //   2212: ldc -65
    //   2214: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2217: ifne +149 -> 2366
    //   2220: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2223: invokevirtual 1336	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2226: astore 5
    //   2228: aload 5
    //   2230: sipush 128
    //   2233: invokevirtual 1493	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2236: invokeinterface 840 1 0
    //   2241: astore 6
    //   2243: aload 6
    //   2245: invokeinterface 709 1 0
    //   2250: ifeq +116 -> 2366
    //   2253: aload 6
    //   2255: invokeinterface 713 1 0
    //   2260: checkcast 1495	android/content/pm/ApplicationInfo
    //   2263: astore 7
    //   2265: aload 7
    //   2267: invokestatic 1499	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2270: istore_2
    //   2271: iload_2
    //   2272: ifne -29 -> 2243
    //   2275: aconst_null
    //   2276: astore_1
    //   2277: aload 5
    //   2279: aload 7
    //   2281: getfield 1500	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2284: iconst_0
    //   2285: invokevirtual 1345	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2288: astore_3
    //   2289: aload_3
    //   2290: astore_1
    //   2291: aload_1
    //   2292: ifnull -49 -> 2243
    //   2295: getstatic 335	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2298: new 8	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2301: dup
    //   2302: aload_0
    //   2303: aload_1
    //   2304: getfield 1350	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2307: new 639	java/lang/StringBuilder
    //   2310: dup
    //   2311: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2314: aload_1
    //   2315: getfield 1503	android/content/pm/PackageInfo:firstInstallTime	J
    //   2318: invokevirtual 651	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2321: ldc_w 291
    //   2324: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2327: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2330: new 639	java/lang/StringBuilder
    //   2333: dup
    //   2334: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2337: aload_1
    //   2338: getfield 1506	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2341: invokevirtual 651	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2344: ldc_w 291
    //   2347: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2350: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2353: invokespecial 1509	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2356: invokeinterface 1512 2 0
    //   2361: pop
    //   2362: goto -119 -> 2243
    //   2365: astore_1
    //   2366: getstatic 365	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2369: aload_0
    //   2370: getfield 409	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2373: ldc2_w 1513
    //   2376: getstatic 1520	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2379: invokeinterface 1525 5 0
    //   2384: pop
    //   2385: aload_0
    //   2386: ldc 93
    //   2388: invokevirtual 1528	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2391: return
    //   2392: astore_1
    //   2393: ldc_w 1530
    //   2396: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2399: aload_1
    //   2400: invokevirtual 972	java/lang/Exception:printStackTrace	()V
    //   2403: iconst_1
    //   2404: putstatic 345	com/kochava/android/tracker/Feature:badInit	Z
    //   2407: return
    //   2408: ldc_w 1532
    //   2411: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2414: iconst_1
    //   2415: putstatic 345	com/kochava/android/tracker/Feature:badInit	Z
    //   2418: return
    //   2419: astore_1
    //   2420: new 639	java/lang/StringBuilder
    //   2423: dup
    //   2424: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2427: ldc_w 1534
    //   2430: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2433: aload_1
    //   2434: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   2437: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2440: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2443: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2446: goto -2051 -> 395
    //   2449: new 639	java/lang/StringBuilder
    //   2452: dup
    //   2453: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2456: ldc_w 1536
    //   2459: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2462: getstatic 297	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2465: invokevirtual 753	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2468: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2471: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2474: goto -2001 -> 473
    //   2477: aload 8
    //   2479: astore_1
    //   2480: aload_3
    //   2481: ldc_w 1360
    //   2484: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2487: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2490: ldc_w 832
    //   2493: invokevirtual 1007	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2496: ifeq -1722 -> 774
    //   2499: aload 8
    //   2501: astore_1
    //   2502: aload_3
    //   2503: ldc_w 1360
    //   2506: invokevirtual 830	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2509: checkcast 832	java/lang/Boolean
    //   2512: invokevirtual 835	java/lang/Boolean:booleanValue	()Z
    //   2515: ifeq -1741 -> 774
    //   2518: ldc -65
    //   2520: astore_1
    //   2521: goto -1747 -> 774
    //   2524: iload 4
    //   2526: sipush 360
    //   2529: if_icmple +42 -> 2571
    //   2532: new 639	java/lang/StringBuilder
    //   2535: dup
    //   2536: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2539: ldc_w 1377
    //   2542: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2545: iload 4
    //   2547: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2550: ldc_w 1538
    //   2553: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2556: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2559: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2562: ldc_w 1539
    //   2565: putstatic 299	com/kochava/android/tracker/Feature:flush_rate	I
    //   2568: goto -1375 -> 1193
    //   2571: iload 4
    //   2573: bipush 60
    //   2575: imul
    //   2576: sipush 1000
    //   2579: imul
    //   2580: putstatic 299	com/kochava/android/tracker/Feature:flush_rate	I
    //   2583: new 639	java/lang/StringBuilder
    //   2586: dup
    //   2587: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2590: ldc_w 1541
    //   2593: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2596: iload 4
    //   2598: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2601: ldc_w 1543
    //   2604: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2607: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2610: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2613: goto -1420 -> 1193
    //   2616: aload 13
    //   2618: ifnull +176 -> 2794
    //   2621: aload 13
    //   2623: invokevirtual 432	java/lang/String:trim	()Ljava/lang/String;
    //   2626: invokevirtual 436	java/lang/String:length	()I
    //   2629: ifeq +165 -> 2794
    //   2632: aload_0
    //   2633: getfield 1352	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2636: ldc_w 1358
    //   2639: aload 13
    //   2641: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2644: pop
    //   2645: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2648: ldc -113
    //   2650: ldc_w 291
    //   2653: invokeinterface 680 3 0
    //   2658: ldc_w 291
    //   2661: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2664: ifeq +25 -> 2689
    //   2667: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2670: invokeinterface 1167 1 0
    //   2675: ldc -113
    //   2677: aload 13
    //   2679: invokeinterface 1173 3 0
    //   2684: invokeinterface 1176 1 0
    //   2689: aload 9
    //   2691: ifnull +76 -> 2767
    //   2694: aload 9
    //   2696: invokevirtual 432	java/lang/String:trim	()Ljava/lang/String;
    //   2699: invokevirtual 436	java/lang/String:length	()I
    //   2702: ifeq +65 -> 2767
    //   2705: aload 9
    //   2707: putstatic 631	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2710: aload_0
    //   2711: getfield 1354	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2714: ldc 101
    //   2716: aload 9
    //   2718: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2721: pop
    //   2722: aload_0
    //   2723: getfield 1356	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2726: ldc 101
    //   2728: aload 9
    //   2730: invokevirtual 662	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2733: pop
    //   2734: goto -1377 -> 1357
    //   2737: astore_1
    //   2738: new 639	java/lang/StringBuilder
    //   2741: dup
    //   2742: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2745: ldc_w 1545
    //   2748: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2751: aload_1
    //   2752: invokevirtual 1546	org/json/JSONException:toString	()Ljava/lang/String;
    //   2755: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2758: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2761: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2764: goto -1186 -> 1578
    //   2767: new 639	java/lang/StringBuilder
    //   2770: dup
    //   2771: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2774: ldc_w 1548
    //   2777: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2780: aload 13
    //   2782: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2785: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2788: putstatic 631	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2791: goto -1434 -> 1357
    //   2794: ldc_w 1550
    //   2797: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2800: iconst_1
    //   2801: putstatic 345	com/kochava/android/tracker/Feature:badInit	Z
    //   2804: return
    //   2805: astore_1
    //   2806: iconst_1
    //   2807: istore 4
    //   2809: new 639	java/lang/StringBuilder
    //   2812: dup
    //   2813: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2816: ldc_w 291
    //   2819: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2822: ldc_w 1552
    //   2825: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2828: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2831: astore_3
    //   2832: goto -1205 -> 1627
    //   2835: astore_1
    //   2836: new 639	java/lang/StringBuilder
    //   2839: dup
    //   2840: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2843: ldc_w 1554
    //   2846: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2849: aload_1
    //   2850: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   2853: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2856: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2859: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2862: goto -1061 -> 1801
    //   2865: astore_1
    //   2866: new 639	java/lang/StringBuilder
    //   2869: dup
    //   2870: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2873: ldc_w 1556
    //   2876: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2879: aload_1
    //   2880: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   2883: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2886: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2889: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2892: goto -1070 -> 1822
    //   2895: astore 5
    //   2897: aconst_null
    //   2898: astore_1
    //   2899: new 639	java/lang/StringBuilder
    //   2902: dup
    //   2903: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2906: ldc_w 1558
    //   2909: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2912: aload 5
    //   2914: invokevirtual 1559	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2917: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2920: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2923: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2926: goto -1001 -> 1925
    //   2929: astore_1
    //   2930: new 639	java/lang/StringBuilder
    //   2933: dup
    //   2934: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2937: ldc_w 1558
    //   2940: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2943: aload_1
    //   2944: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   2947: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2950: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2953: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2956: goto -984 -> 1972
    //   2959: ldc_w 1561
    //   2962: astore_1
    //   2963: goto -1028 -> 1935
    //   2966: astore_1
    //   2967: new 639	java/lang/StringBuilder
    //   2970: dup
    //   2971: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   2974: ldc_w 1563
    //   2977: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2980: aload_1
    //   2981: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   2984: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2987: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2990: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2993: goto -953 -> 2040
    //   2996: astore_1
    //   2997: new 639	java/lang/StringBuilder
    //   3000: dup
    //   3001: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   3004: ldc_w 1565
    //   3007: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3010: aload_1
    //   3011: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   3014: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3017: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3020: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3023: goto -934 -> 2089
    //   3026: astore_1
    //   3027: new 639	java/lang/StringBuilder
    //   3030: dup
    //   3031: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   3034: ldc_w 1567
    //   3037: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3040: aload_1
    //   3041: invokevirtual 1058	java/lang/Exception:toString	()Ljava/lang/String;
    //   3044: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3047: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3050: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3053: goto -886 -> 2167
    //   3056: astore_1
    //   3057: getstatic 858	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3060: ifeq -861 -> 2199
    //   3063: aload_1
    //   3064: invokevirtual 861	org/json/JSONException:printStackTrace	()V
    //   3067: goto -868 -> 2199
    //   3070: astore_3
    //   3071: new 639	java/lang/StringBuilder
    //   3074: dup
    //   3075: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   3078: ldc_w 1569
    //   3081: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3084: aload 7
    //   3086: getfield 1500	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3089: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3092: ldc_w 1571
    //   3095: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3098: aload_3
    //   3099: invokevirtual 1559	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3102: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3105: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3108: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3111: goto -820 -> 2291
    //   3114: astore_1
    //   3115: goto -2591 -> 524
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3118	0	this	Feature
    //   0	3118	1	paramContext	Context
    //   0	3118	2	paramBoolean	boolean
    //   0	3118	3	paramHashMap	HashMap<String, Object>
    //   1155	1653	4	i	int
    //   594	1684	5	localObject1	Object
    //   2895	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   561	1693	6	localObject2	Object
    //   567	2518	7	localObject3	Object
    //   577	1923	8	localObject4	Object
    //   564	457	9	localIterator	Iterator
    //   1030	1	9	localException	Exception
    //   1077	1652	9	localObject5	Object
    //   574	669	10	localObject6	Object
    //   580	653	11	localObject7	Object
    //   586	773	12	localObject8	Object
    //   558	2223	13	localObject9	Object
    //   583	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   933	971	1030	java/lang/Exception
    //   971	1027	1030	java/lang/Exception
    //   2199	2243	2365	java/lang/Exception
    //   2243	2271	2365	java/lang/Exception
    //   2277	2289	2365	java/lang/Exception
    //   2295	2362	2365	java/lang/Exception
    //   3071	3111	2365	java/lang/Exception
    //   4	11	2392	java/lang/Exception
    //   372	395	2419	java/lang/Exception
    //   1273	1357	2737	org/json/JSONException
    //   1357	1578	2737	org/json/JSONException
    //   2621	2689	2737	org/json/JSONException
    //   2694	2734	2737	org/json/JSONException
    //   2767	2791	2737	org/json/JSONException
    //   2794	2804	2737	org/json/JSONException
    //   1609	1627	2805	android/content/pm/PackageManager$NameNotFoundException
    //   1783	1801	2835	java/lang/Exception
    //   1801	1822	2865	java/lang/Exception
    //   1913	1925	2895	android/content/pm/PackageManager$NameNotFoundException
    //   1903	1913	2929	java/lang/Exception
    //   1913	1925	2929	java/lang/Exception
    //   1929	1935	2929	java/lang/Exception
    //   1935	1972	2929	java/lang/Exception
    //   2899	2926	2929	java/lang/Exception
    //   1972	2040	2966	java/lang/Exception
    //   2040	2089	2996	java/lang/Exception
    //   2089	2167	3026	java/lang/Exception
    //   2185	2199	3056	org/json/JSONException
    //   2277	2289	3070	android/content/pm/PackageManager$NameNotFoundException
    //   501	524	3114	java/lang/Exception
  }
  
  private void initHandler()
  {
    if (this.initHandler == null) {
      this.initHandler = new Feature.17(this);
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
        mTimer.schedule(new Feature.18(this), 0L, flush_rate);
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
          localObject1 = (HttpsURLConnection)new URL("https://" + hostControl + "/track/kvTracker.php").openConnection();
          ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", prefs.getString("useragent", ""));
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
            break label672;
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
      label672:
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
    new Feature.19(paramJSONObject).start();
  }
  
  protected static void sendKVQuery(int paramInt)
  {
    worker.schedule(sendKVQuery, paramInt, TimeUnit.SECONDS);
  }
  
  private static void sendOutsideServicesUpdate(JSONArray paramJSONArray)
  {
    new Feature.22(paramJSONArray).start();
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
    //   0: getstatic 761	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +259 -> 264
    //   8: getstatic 297	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +253 -> 264
    //   14: getstatic 345	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 55
    //   22: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1804
    //   29: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 337	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 765	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +188 -> 229
    //   44: ldc_w 1806
    //   47: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 772	java/util/Timer
    //   53: dup
    //   54: invokespecial 1281	java/util/Timer:<init>	()V
    //   57: putstatic 765	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 765	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 1808	com/kochava/android/tracker/Feature$10
    //   66: dup
    //   67: invokespecial 1809	com/kochava/android/tracker/Feature$10:<init>	()V
    //   70: getstatic 299	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 299	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1596	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 645	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 646
    //   87: ldiv
    //   88: putstatic 328	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 324	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 497	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 668	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1813	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1816	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +50 -> 165
    //   118: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -86
    //   123: ldc_w 291
    //   126: invokeinterface 680 3 0
    //   131: ldc_w 291
    //   134: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 659	org/json/JSONObject
    //   145: dup
    //   146: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc -86
    //   151: ldc_w 291
    //   154: invokeinterface 680 3 0
    //   159: invokespecial 1817	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1819	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +96 -> 264
    //   171: getstatic 564	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc -107
    //   176: ldc_w 291
    //   179: invokeinterface 680 3 0
    //   184: ldc -65
    //   186: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   189: ifeq +75 -> 264
    //   192: getstatic 347	com/kochava/android/tracker/Feature:canSendSession	Z
    //   195: ifeq +63 -> 258
    //   198: ldc 127
    //   200: invokestatic 778	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   203: return
    //   204: astore_1
    //   205: new 639	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 640	java/lang/StringBuilder:<init>	()V
    //   212: ldc_w 1821
    //   215: invokevirtual 654	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual 695	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 657	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   228: return
    //   229: ldc_w 1823
    //   232: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   235: goto -154 -> 81
    //   238: astore_1
    //   239: ldc_w 1825
    //   242: invokestatic 700	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   245: getstatic 858	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   248: ifeq -83 -> 165
    //   251: aload_1
    //   252: invokevirtual 861	org/json/JSONException:printStackTrace	()V
    //   255: goto -90 -> 165
    //   258: ldc_w 784
    //   261: invokestatic 756	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   264: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   137	2	0	bool	boolean
    //   204	15	1	localException	Exception
    //   238	14	1	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   0	25	204	java/lang/Exception
    //   26	81	204	java/lang/Exception
    //   81	112	204	java/lang/Exception
    //   112	138	204	java/lang/Exception
    //   142	165	204	java/lang/Exception
    //   165	203	204	java/lang/Exception
    //   229	235	204	java/lang/Exception
    //   239	255	204	java/lang/Exception
    //   258	264	204	java/lang/Exception
    //   142	165	238	org/json/JSONException
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
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    new Feature.14(this, paramString).start();
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
      new Feature.12(this, paramString1, paramString2).start();
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
      new Feature.13(this, paramString1, paramDouble1, paramDouble2, paramDouble3, paramString2).start();
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
    new Feature.11(this, paramMap).start();
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
}
