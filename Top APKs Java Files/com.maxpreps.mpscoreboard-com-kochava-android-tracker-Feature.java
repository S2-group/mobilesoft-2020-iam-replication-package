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
  private static long lastCallTime;
  private static long lastEventTimestamp;
  protected static Handler locationHandler = new Feature.14();
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
  private Runnable getKVinit = new Feature.3(this);
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
  private Context mContext;
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
    paramRestrictions = new Feature.1();
    eventnameBlacklist = new JSONArray();
    sendKVQuery = new Feature.4();
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
            new ComponentName(this.mContext, Class.forName(localJSONArray2.getString(i)));
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
    new Feature.8(paramString).start();
  }
  
  private void fireEvent(String paramString, Map<String, String> paramMap)
  {
    if ((!paramString.equals("initial")) && (!should_flush_in_background) && (is_in_background) && (!event_flush_triggered))
    {
      event_flush_triggered = true;
      this.eventFlushTimer.schedule(new Feature.9(this), 60000L);
    }
    Logging.Log("FIRE EVENT*** action:" + paramString);
    Logging.Log("FIRE EVENT*** properties:" + paramMap);
    JSONObject localJSONObject1 = new JSONObject();
    label1084:
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
          prefs = this.mContext.getSharedPreferences("initPrefs", 0);
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
              localJSONObject1.put("sdk_version", "Android20160105" + versionExtension);
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
                this.mFbId = getAttributionId(this.mContext.getContentResolver());
                if (this.mFbId != null) {
                  break label1084;
                }
                localJSONObject2.put("fb_attribution_id", "");
              }
              paramString = (WindowManager)this.mContext.getSystemService("window");
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
        i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
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
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
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
    //   8: getstatic 353	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 406	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 41
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1056	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1061 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1064 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1067 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 41
    //   75: invokeinterface 1071 2 0
    //   80: invokeinterface 1072 2 0
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
    //   99: invokeinterface 1064 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1067 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 601	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1074
    //   130: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1064 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1067 1 0
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
    //   190: invokeinterface 1064 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1067 1 0
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
    if (this.mContext.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0)
    {
      Account[] arrayOfAccount = AccountManager.get(this.mContext).getAccounts();
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
    prefs.edit().putString("kochava_app_id_generated", str).commit();
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
      localObject5 = new WebView(this.mContext).getSettings().getUserAgentString();
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
      localObject1 = ((WebSettings)((Constructor)localObject3).newInstance(new Object[] { this.mContext, null })).getUserAgentString();
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
        break label737;
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
    new Feature.13(paramException).start();
  }
  
  /* Error */
  @android.annotation.TargetApi(14)
  private void init(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +2390 -> 2391
    //   4: aload_0
    //   5: aload_1
    //   6: putfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   9: iload_2
    //   10: putstatic 298	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   13: ldc 122
    //   15: new 601	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   22: ldc_w 1244
    //   25: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: getstatic 278	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   31: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 1250	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: getstatic 630	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   44: ifnonnull +10 -> 54
    //   47: aload_1
    //   48: invokevirtual 1254	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   51: putstatic 630	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   54: aload_0
    //   55: new 734	java/util/Timer
    //   58: dup
    //   59: invokespecial 1255	java/util/Timer:<init>	()V
    //   62: putfield 763	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   65: aload_0
    //   66: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   69: ldc -106
    //   71: iconst_0
    //   72: invokevirtual 636	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   75: putstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   78: new 965	com/kochava/android/tracker/DbAdapter
    //   81: dup
    //   82: aload_0
    //   83: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   86: invokespecial 1256	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   89: putstatic 529	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   92: aload_3
    //   93: ifnull +231 -> 324
    //   96: aload_3
    //   97: ldc_w 1258
    //   100: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   103: ifnull +44 -> 147
    //   106: aload_3
    //   107: ldc_w 1258
    //   110: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   113: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   116: ldc_w 798
    //   119: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   122: ifeq +25 -> 147
    //   125: aload_3
    //   126: ldc_w 1258
    //   129: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   132: checkcast 798	java/lang/Boolean
    //   135: invokevirtual 801	java/lang/Boolean:booleanValue	()Z
    //   138: istore_2
    //   139: iload_2
    //   140: invokestatic 1264	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   143: iload_2
    //   144: invokestatic 1267	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   147: aload_3
    //   148: ldc_w 1269
    //   151: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   154: ifnull +35 -> 189
    //   157: aload_3
    //   158: ldc_w 1269
    //   161: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   164: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   167: ldc_w 406
    //   170: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   173: ifeq +16 -> 189
    //   176: aload_3
    //   177: ldc_w 1269
    //   180: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: checkcast 406	java/lang/String
    //   186: putstatic 278	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   189: aload_3
    //   190: ldc_w 1271
    //   193: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   196: ifnull +38 -> 234
    //   199: aload_3
    //   200: ldc_w 1271
    //   203: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   206: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   209: ldc_w 798
    //   212: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   215: ifeq +19 -> 234
    //   218: aload_3
    //   219: ldc_w 1271
    //   222: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   225: checkcast 798	java/lang/Boolean
    //   228: invokevirtual 801	java/lang/Boolean:booleanValue	()Z
    //   231: putstatic 280	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   234: aload_3
    //   235: ldc_w 1273
    //   238: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   241: ifnull +38 -> 279
    //   244: aload_3
    //   245: ldc_w 1273
    //   248: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   251: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   254: ldc_w 798
    //   257: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   260: ifeq +19 -> 279
    //   263: aload_3
    //   264: ldc_w 1273
    //   267: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   270: checkcast 798	java/lang/Boolean
    //   273: invokevirtual 801	java/lang/Boolean:booleanValue	()Z
    //   276: putstatic 296	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   279: aload_3
    //   280: ldc_w 1275
    //   283: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   286: ifnull +38 -> 324
    //   289: aload_3
    //   290: ldc_w 1275
    //   293: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   296: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   299: ldc_w 798
    //   302: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   305: ifeq +19 -> 324
    //   308: aload_3
    //   309: ldc_w 1275
    //   312: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   315: checkcast 798	java/lang/Boolean
    //   318: invokevirtual 801	java/lang/Boolean:booleanValue	()Z
    //   321: putstatic 315	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   324: aload_0
    //   325: invokespecial 1277	com/kochava/android/tracker/Feature:initHandler	()V
    //   328: new 601	java/lang/StringBuilder
    //   331: dup
    //   332: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   335: ldc_w 1279
    //   338: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   344: ldc 61
    //   346: ldc_w 274
    //   349: invokeinterface 642 3 0
    //   354: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   363: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   366: ldc 61
    //   368: ldc_w 274
    //   371: invokeinterface 642 3 0
    //   376: ldc_w 274
    //   379: invokevirtual 646	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   382: ifne +26 -> 408
    //   385: new 360	org/json/JSONArray
    //   388: dup
    //   389: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   392: ldc 61
    //   394: ldc_w 274
    //   397: invokeinterface 642 3 0
    //   402: invokespecial 1280	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   405: putstatic 363	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   408: getstatic 723	android/os/Build$VERSION:SDK_INT	I
    //   411: bipush 14
    //   413: if_icmplt +2019 -> 2432
    //   416: getstatic 280	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   419: ifne +2013 -> 2432
    //   422: new 601	java/lang/StringBuilder
    //   425: dup
    //   426: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   429: ldc_w 1282
    //   432: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: getstatic 280	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   438: invokevirtual 715	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   441: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   447: getstatic 630	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   450: checkcast 1284	android/app/Application
    //   453: new 12	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   456: dup
    //   457: aload_0
    //   458: invokespecial 1285	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   461: invokevirtual 1289	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   464: getstatic 630	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   467: new 14	com/kochava/android/tracker/Feature$MemoryBoss
    //   470: dup
    //   471: aload_0
    //   472: invokespecial 1290	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   475: invokevirtual 1294	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   478: iconst_1
    //   479: putstatic 1297	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   482: iconst_1
    //   483: putstatic 1300	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   486: aload_0
    //   487: aload_0
    //   488: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   491: invokevirtual 906	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   494: invokestatic 910	com/kochava/android/tracker/Feature:getAttributionId	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   497: putfield 912	com/kochava/android/tracker/Feature:mFbId	Ljava/lang/String;
    //   500: getstatic 296	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   503: ifne +19 -> 522
    //   506: new 1302	com/kochava/android/tracker/Feature$2
    //   509: dup
    //   510: aload_0
    //   511: invokespecial 1303	com/kochava/android/tracker/Feature$2:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   514: iconst_0
    //   515: anewarray 1305	java/lang/Void
    //   518: invokevirtual 1311	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   521: pop
    //   522: aload_0
    //   523: aload_0
    //   524: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   527: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   530: aload_0
    //   531: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   534: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   537: iconst_0
    //   538: invokevirtual 1324	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   541: getfield 1329	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   544: putfield 1041	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   547: aload_0
    //   548: new 621	org/json/JSONObject
    //   551: dup
    //   552: invokespecial 775	org/json/JSONObject:<init>	()V
    //   555: putfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   558: aload_0
    //   559: new 621	org/json/JSONObject
    //   562: dup
    //   563: invokespecial 775	org/json/JSONObject:<init>	()V
    //   566: putfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   569: aload_0
    //   570: new 621	org/json/JSONObject
    //   573: dup
    //   574: invokespecial 775	org/json/JSONObject:<init>	()V
    //   577: putfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   580: aconst_null
    //   581: astore 13
    //   583: aconst_null
    //   584: astore 6
    //   586: aconst_null
    //   587: astore 9
    //   589: aconst_null
    //   590: astore 7
    //   592: ldc_w 788
    //   595: astore_1
    //   596: aconst_null
    //   597: astore 10
    //   599: aconst_null
    //   600: astore 8
    //   602: aconst_null
    //   603: astore 11
    //   605: aconst_null
    //   606: astore 14
    //   608: aload_1
    //   609: astore 12
    //   611: aload_3
    //   612: ifnull +627 -> 1239
    //   615: aload 6
    //   617: astore 5
    //   619: aload_3
    //   620: ldc_w 1337
    //   623: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   626: ifnull +38 -> 664
    //   629: aload 6
    //   631: astore 5
    //   633: aload_3
    //   634: ldc_w 1337
    //   637: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   640: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   643: ldc_w 406
    //   646: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   649: ifeq +15 -> 664
    //   652: aload_3
    //   653: ldc_w 1337
    //   656: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   659: checkcast 406	java/lang/String
    //   662: astore 5
    //   664: aload 7
    //   666: astore 6
    //   668: aload_3
    //   669: ldc 92
    //   671: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   674: ifnull +36 -> 710
    //   677: aload 7
    //   679: astore 6
    //   681: aload_3
    //   682: ldc 92
    //   684: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   687: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   690: ldc_w 406
    //   693: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   696: ifeq +14 -> 710
    //   699: aload_3
    //   700: ldc 92
    //   702: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   705: checkcast 406	java/lang/String
    //   708: astore 6
    //   710: aload_1
    //   711: astore 7
    //   713: aload_3
    //   714: ldc -121
    //   716: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   719: ifnull +35 -> 754
    //   722: aload_1
    //   723: astore 7
    //   725: aload_3
    //   726: ldc -121
    //   728: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   731: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   734: ldc_w 406
    //   737: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   740: ifeq +14 -> 754
    //   743: aload_3
    //   744: ldc -121
    //   746: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   749: checkcast 406	java/lang/String
    //   752: astore 7
    //   754: aload 8
    //   756: astore_1
    //   757: aload_3
    //   758: ldc_w 1339
    //   761: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   764: ifnull +33 -> 797
    //   767: aload_3
    //   768: ldc_w 1339
    //   771: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   774: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   777: ldc_w 406
    //   780: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   783: ifeq +1677 -> 2460
    //   786: aload_3
    //   787: ldc_w 1339
    //   790: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   793: checkcast 406	java/lang/String
    //   796: astore_1
    //   797: aload_3
    //   798: ldc_w 1341
    //   801: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   804: ifnull +34 -> 838
    //   807: aload_3
    //   808: ldc_w 1341
    //   811: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   814: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   817: ldc_w 406
    //   820: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   823: ifeq +15 -> 838
    //   826: aload_3
    //   827: ldc_w 1341
    //   830: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   833: checkcast 406	java/lang/String
    //   836: astore 8
    //   838: aload 14
    //   840: astore 8
    //   842: aload_3
    //   843: ldc_w 1343
    //   846: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   849: ifnull +38 -> 887
    //   852: aload 14
    //   854: astore 8
    //   856: aload_3
    //   857: ldc_w 1343
    //   860: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   863: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   866: ldc_w 406
    //   869: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   872: ifeq +15 -> 887
    //   875: aload_3
    //   876: ldc_w 1343
    //   879: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   882: checkcast 406	java/lang/String
    //   885: astore 8
    //   887: aload_3
    //   888: ldc_w 876
    //   891: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   894: ifnull +39 -> 933
    //   897: aload_3
    //   898: ldc_w 876
    //   901: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   904: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   907: ldc_w 798
    //   910: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   913: ifeq +20 -> 933
    //   916: aload_0
    //   917: aload_3
    //   918: ldc_w 876
    //   921: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   924: checkcast 798	java/lang/Boolean
    //   927: invokevirtual 801	java/lang/Boolean:booleanValue	()Z
    //   930: putfield 378	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   933: aload_3
    //   934: ldc_w 894
    //   937: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   940: ifnull +115 -> 1055
    //   943: aload_3
    //   944: ldc_w 894
    //   947: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   950: instanceof 403
    //   953: ifeq +102 -> 1055
    //   956: aload_3
    //   957: ldc_w 894
    //   960: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   963: checkcast 403	java/util/HashMap
    //   966: putstatic 1345	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   969: new 621	org/json/JSONObject
    //   972: dup
    //   973: invokespecial 775	org/json/JSONObject:<init>	()V
    //   976: putstatic 892	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   979: getstatic 1345	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   982: invokeinterface 948 1 0
    //   987: invokeinterface 951 1 0
    //   992: astore 9
    //   994: aload 9
    //   996: invokeinterface 671 1 0
    //   1001: ifeq +54 -> 1055
    //   1004: aload 9
    //   1006: invokeinterface 675 1 0
    //   1011: checkcast 953	java/util/Map$Entry
    //   1014: astore 10
    //   1016: getstatic 892	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   1019: aload 10
    //   1021: invokeinterface 956 1 0
    //   1026: checkcast 406	java/lang/String
    //   1029: aload 10
    //   1031: invokeinterface 959 1 0
    //   1036: checkcast 406	java/lang/String
    //   1039: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1042: pop
    //   1043: aload 9
    //   1045: invokeinterface 1348 1 0
    //   1050: goto -56 -> 994
    //   1053: astore 9
    //   1055: aload_3
    //   1056: ldc_w 900
    //   1059: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1062: ifnull +36 -> 1098
    //   1065: aload_3
    //   1066: ldc_w 900
    //   1069: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1072: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1075: ldc_w 406
    //   1078: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1081: ifeq +17 -> 1098
    //   1084: aload_0
    //   1085: aload_3
    //   1086: ldc_w 900
    //   1089: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1092: checkcast 406	java/lang/String
    //   1095: putfield 896	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
    //   1098: aload 6
    //   1100: astore 9
    //   1102: aload_1
    //   1103: astore 10
    //   1105: aload 8
    //   1107: astore 11
    //   1109: aload 7
    //   1111: astore 12
    //   1113: aload 5
    //   1115: astore 13
    //   1117: aload_3
    //   1118: ldc_w 1349
    //   1121: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1124: ifnull +115 -> 1239
    //   1127: aload 6
    //   1129: astore 9
    //   1131: aload_1
    //   1132: astore 10
    //   1134: aload 8
    //   1136: astore 11
    //   1138: aload 7
    //   1140: astore 12
    //   1142: aload 5
    //   1144: astore 13
    //   1146: aload_3
    //   1147: ldc_w 1349
    //   1150: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1153: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1156: ldc_w 1351
    //   1159: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1162: ifeq +77 -> 1239
    //   1165: aload_3
    //   1166: ldc_w 1349
    //   1169: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1172: checkcast 1351	java/lang/Integer
    //   1175: invokevirtual 1354	java/lang/Integer:intValue	()I
    //   1178: istore 4
    //   1180: iload 4
    //   1182: iconst_1
    //   1183: if_icmpge +1324 -> 2507
    //   1186: new 601	java/lang/StringBuilder
    //   1189: dup
    //   1190: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1193: ldc_w 1356
    //   1196: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1199: iload 4
    //   1201: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1204: ldc_w 1358
    //   1207: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1213: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1216: iconst_1
    //   1217: putstatic 284	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
    //   1220: aload 5
    //   1222: astore 13
    //   1224: aload 7
    //   1226: astore 12
    //   1228: aload 8
    //   1230: astore 11
    //   1232: aload_1
    //   1233: astore 10
    //   1235: aload 6
    //   1237: astore 9
    //   1239: aload 11
    //   1241: ifnull +19 -> 1260
    //   1244: aload 11
    //   1246: invokevirtual 410	java/lang/String:trim	()Ljava/lang/String;
    //   1249: invokevirtual 414	java/lang/String:length	()I
    //   1252: ifeq +8 -> 1260
    //   1255: aload 11
    //   1257: putstatic 276	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1260: aload 10
    //   1262: ifnull +17 -> 1279
    //   1265: aload 10
    //   1267: ldc -82
    //   1269: invokevirtual 1361	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1272: ifeq +7 -> 1279
    //   1275: iconst_1
    //   1276: putstatic 329	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1279: aload_0
    //   1280: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1283: ldc 47
    //   1285: iconst_0
    //   1286: invokevirtual 636	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1289: putstatic 495	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1292: aload 9
    //   1294: ifnull +1305 -> 2599
    //   1297: aload 9
    //   1299: invokevirtual 410	java/lang/String:trim	()Ljava/lang/String;
    //   1302: invokevirtual 414	java/lang/String:length	()I
    //   1305: ifeq +1294 -> 2599
    //   1308: aload 9
    //   1310: putstatic 583	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1313: aload_0
    //   1314: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1317: ldc 92
    //   1319: aload 9
    //   1321: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1324: pop
    //   1325: aload_0
    //   1326: getfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1329: ldc 92
    //   1331: aload 9
    //   1333: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1336: pop
    //   1337: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1340: ldc -124
    //   1342: ldc_w 274
    //   1345: invokeinterface 642 3 0
    //   1350: ldc_w 274
    //   1353: invokevirtual 646	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1356: ifeq +26 -> 1382
    //   1359: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1362: invokeinterface 1140 1 0
    //   1367: ldc -124
    //   1369: aload 9
    //   1371: invokeinterface 1146 3 0
    //   1376: invokeinterface 1149 1 0
    //   1381: pop
    //   1382: aload_0
    //   1383: aload 12
    //   1385: invokespecial 591	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1388: aload_0
    //   1389: getfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1392: ldc_w 1363
    //   1395: aload_0
    //   1396: invokespecial 865	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1399: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload_0
    //   1404: getfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1407: ldc_w 1365
    //   1410: ldc_w 1367
    //   1413: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1416: pop
    //   1417: aload_0
    //   1418: getfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1421: ldc_w 1369
    //   1424: ldc_w 1371
    //   1427: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1430: pop
    //   1431: aload_0
    //   1432: getfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1435: ldc -121
    //   1437: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1440: ldc -121
    //   1442: ldc_w 788
    //   1445: invokeinterface 642 3 0
    //   1450: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1453: pop
    //   1454: aload_0
    //   1455: getfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1458: ldc -121
    //   1460: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1463: ldc -121
    //   1465: ldc_w 788
    //   1468: invokeinterface 642 3 0
    //   1473: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1476: pop
    //   1477: aload_0
    //   1478: getfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1481: ldc_w 1369
    //   1484: ldc_w 1371
    //   1487: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1490: pop
    //   1491: aload_0
    //   1492: getfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1495: ldc -121
    //   1497: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1500: ldc -121
    //   1502: ldc_w 788
    //   1505: invokeinterface 642 3 0
    //   1510: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1513: pop
    //   1514: aload_0
    //   1515: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1518: ldc_w 831
    //   1521: new 601	java/lang/StringBuilder
    //   1524: dup
    //   1525: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1528: ldc_w 833
    //   1531: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1534: getstatic 278	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1537: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1543: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1546: pop
    //   1547: aload_0
    //   1548: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1551: ldc_w 1373
    //   1554: ldc_w 1375
    //   1557: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1560: pop
    //   1561: aload_0
    //   1562: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1565: ldc_w 961
    //   1568: aload_0
    //   1569: getfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1572: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1575: pop
    //   1576: aload_0
    //   1577: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1580: ldc_w 1377
    //   1583: aload_0
    //   1584: getfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1587: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1590: pop
    //   1591: invokestatic 607	java/lang/System:currentTimeMillis	()J
    //   1594: ldc2_w 608
    //   1597: ldiv
    //   1598: putstatic 304	com/kochava/android/tracker/Feature:startTime	J
    //   1601: iconst_0
    //   1602: istore 4
    //   1604: ldc_w 274
    //   1607: astore_3
    //   1608: new 682	android/content/ComponentName
    //   1611: dup
    //   1612: aload_0
    //   1613: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1616: ldc_w 1379
    //   1619: invokespecial 694	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1622: astore_1
    //   1623: aload_0
    //   1624: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1627: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1630: aload_1
    //   1631: iconst_0
    //   1632: invokevirtual 1383	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1635: pop
    //   1636: ldc_w 1385
    //   1639: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1642: aload_3
    //   1643: astore_1
    //   1644: aload_0
    //   1645: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1648: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1651: ldc_w 1387
    //   1654: aload_0
    //   1655: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1658: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1661: invokevirtual 1390	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1664: ifge +27 -> 1691
    //   1667: iconst_1
    //   1668: istore 4
    //   1670: new 601	java/lang/StringBuilder
    //   1673: dup
    //   1674: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1677: aload_3
    //   1678: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1681: ldc_w 1392
    //   1684: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1687: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1690: astore_1
    //   1691: aload_1
    //   1692: astore_3
    //   1693: aload_0
    //   1694: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1697: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1700: ldc_w 1394
    //   1703: aload_0
    //   1704: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1707: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1710: invokevirtual 1390	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1713: ifge +27 -> 1740
    //   1716: iconst_1
    //   1717: istore 4
    //   1719: new 601	java/lang/StringBuilder
    //   1722: dup
    //   1723: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1726: aload_1
    //   1727: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1730: ldc_w 1396
    //   1733: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1736: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1739: astore_3
    //   1740: aload_3
    //   1741: astore_1
    //   1742: aload_0
    //   1743: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1746: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1749: ldc_w 1398
    //   1752: aload_0
    //   1753: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1756: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1759: invokevirtual 1390	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1762: ifge +27 -> 1789
    //   1765: iconst_1
    //   1766: istore 4
    //   1768: new 601	java/lang/StringBuilder
    //   1771: dup
    //   1772: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1775: aload_3
    //   1776: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1779: ldc_w 1400
    //   1782: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1785: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1788: astore_1
    //   1789: iload 4
    //   1791: ifeq +13 -> 1804
    //   1794: ldc_w 1402
    //   1797: invokestatic 1405	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1800: aload_1
    //   1801: invokestatic 1405	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1804: aload_0
    //   1805: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1808: ldc_w 1407
    //   1811: invokevirtual 918	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1814: checkcast 1409	android/telephony/TelephonyManager
    //   1817: invokevirtual 1412	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1820: putstatic 845	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1823: aload_0
    //   1824: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1827: ldc_w 1414
    //   1830: invokevirtual 918	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1833: checkcast 1416	android/net/wifi/WifiManager
    //   1836: invokevirtual 1420	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1839: invokevirtual 1425	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1842: putstatic 841	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1845: aload_0
    //   1846: invokespecial 1427	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1849: putstatic 576	com/kochava/android/tracker/Feature:mUserAgent	Ljava/lang/String;
    //   1852: aload_0
    //   1853: invokestatic 580	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1856: putfield 1429	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1859: aload_0
    //   1860: invokestatic 573	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1863: putfield 1431	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1866: aload_0
    //   1867: ldc_w 1433
    //   1870: putfield 1045	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1873: aload_0
    //   1874: ldc_w 1435
    //   1877: putfield 1049	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1880: aload_0
    //   1881: ldc_w 274
    //   1884: putfield 506	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1887: aload_0
    //   1888: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1891: invokevirtual 1254	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1894: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1897: astore_3
    //   1898: aload_3
    //   1899: aload_0
    //   1900: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1903: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1906: iconst_0
    //   1907: invokevirtual 1439	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1910: astore_1
    //   1911: aload_1
    //   1912: ifnull +1031 -> 2943
    //   1915: aload_3
    //   1916: aload_1
    //   1917: invokevirtual 1443	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1920: astore_1
    //   1921: aload_0
    //   1922: aload_1
    //   1923: checkcast 406	java/lang/String
    //   1926: checkcast 406	java/lang/String
    //   1929: putfield 1045	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1932: new 601	java/lang/StringBuilder
    //   1935: dup
    //   1936: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1939: ldc_w 1445
    //   1942: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1945: aload_0
    //   1946: getfield 1045	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1949: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1952: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1955: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1958: aload_0
    //   1959: new 601	java/lang/StringBuilder
    //   1962: dup
    //   1963: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   1966: aload_0
    //   1967: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1970: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1973: aload_0
    //   1974: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1977: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1980: iconst_0
    //   1981: invokevirtual 1324	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1984: getfield 1448	android/content/pm/PackageInfo:versionCode	I
    //   1987: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1990: ldc_w 274
    //   1993: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1996: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1999: putfield 1049	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2002: new 601	java/lang/StringBuilder
    //   2005: dup
    //   2006: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2009: ldc_w 1450
    //   2012: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2015: aload_0
    //   2016: getfield 1049	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2019: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2022: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2025: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2028: aload_0
    //   2029: aload_0
    //   2030: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2033: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2036: aload_0
    //   2037: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2040: invokevirtual 1318	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2043: iconst_0
    //   2044: invokevirtual 1324	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2047: getfield 1453	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2050: putfield 506	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2053: new 601	java/lang/StringBuilder
    //   2056: dup
    //   2057: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2060: ldc_w 1455
    //   2063: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2066: aload_0
    //   2067: getfield 506	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2070: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2073: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2076: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2079: aload_0
    //   2080: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2083: ldc_w 914
    //   2086: invokevirtual 918	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2089: checkcast 920	android/view/WindowManager
    //   2092: astore_1
    //   2093: aload_0
    //   2094: aload_1
    //   2095: invokeinterface 927 1 0
    //   2100: invokevirtual 1458	android/view/Display:getHeight	()I
    //   2103: putfield 856	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2106: aload_0
    //   2107: aload_1
    //   2108: invokeinterface 927 1 0
    //   2113: invokevirtual 1461	android/view/Display:getWidth	()I
    //   2116: putfield 860	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2119: new 601	java/lang/StringBuilder
    //   2122: dup
    //   2123: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2126: ldc_w 1463
    //   2129: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2132: aload_0
    //   2133: getfield 856	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2136: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2139: ldc_w 1465
    //   2142: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2145: aload_0
    //   2146: getfield 860	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2149: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2152: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2155: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2158: aload_0
    //   2159: aload_0
    //   2160: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2163: invokevirtual 906	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   2166: ldc_w 871
    //   2169: invokestatic 1470	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   2172: putfield 873	com/kochava/android/tracker/Feature:mAndroidID	Ljava/lang/String;
    //   2175: aload_0
    //   2176: invokestatic 492	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2179: putfield 1472	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2182: aload_0
    //   2183: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2186: ldc_w 777
    //   2189: invokestatic 492	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2192: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2195: pop
    //   2196: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2199: ldc -118
    //   2201: ldc_w 274
    //   2204: invokeinterface 642 3 0
    //   2209: ldc -82
    //   2211: invokevirtual 646	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2214: ifne +150 -> 2364
    //   2217: aload_0
    //   2218: getfield 595	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2221: invokevirtual 1315	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2224: astore 5
    //   2226: aload 5
    //   2228: sipush 128
    //   2231: invokevirtual 1476	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2234: invokeinterface 806 1 0
    //   2239: astore 6
    //   2241: aload 6
    //   2243: invokeinterface 671 1 0
    //   2248: ifeq +116 -> 2364
    //   2251: aload 6
    //   2253: invokeinterface 675 1 0
    //   2258: checkcast 1478	android/content/pm/ApplicationInfo
    //   2261: astore 7
    //   2263: aload 7
    //   2265: invokestatic 1482	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2268: istore_2
    //   2269: iload_2
    //   2270: ifne -29 -> 2241
    //   2273: aconst_null
    //   2274: astore_1
    //   2275: aload 5
    //   2277: aload 7
    //   2279: getfield 1483	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2282: iconst_0
    //   2283: invokevirtual 1324	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2286: astore_3
    //   2287: aload_3
    //   2288: astore_1
    //   2289: aload_1
    //   2290: ifnull -49 -> 2241
    //   2293: getstatic 313	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2296: new 8	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2299: dup
    //   2300: aload_0
    //   2301: aload_1
    //   2302: getfield 1329	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2305: new 601	java/lang/StringBuilder
    //   2308: dup
    //   2309: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2312: aload_1
    //   2313: getfield 1486	android/content/pm/PackageInfo:firstInstallTime	J
    //   2316: invokevirtual 613	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2319: ldc_w 274
    //   2322: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2325: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2328: new 601	java/lang/StringBuilder
    //   2331: dup
    //   2332: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2335: aload_1
    //   2336: getfield 1489	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2339: invokevirtual 613	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2342: ldc_w 274
    //   2345: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2348: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2351: invokespecial 1492	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2354: invokeinterface 1495 2 0
    //   2359: pop
    //   2360: goto -119 -> 2241
    //   2363: astore_1
    //   2364: getstatic 343	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2367: aload_0
    //   2368: getfield 387	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2371: ldc2_w 1496
    //   2374: getstatic 1503	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2377: invokeinterface 1508 5 0
    //   2382: pop
    //   2383: aload_0
    //   2384: ldc_w 1509
    //   2387: invokevirtual 1512	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2390: return
    //   2391: ldc_w 1514
    //   2394: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2397: iconst_1
    //   2398: putstatic 323	com/kochava/android/tracker/Feature:badInit	Z
    //   2401: return
    //   2402: astore_1
    //   2403: new 601	java/lang/StringBuilder
    //   2406: dup
    //   2407: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2410: ldc_w 1516
    //   2413: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2416: aload_1
    //   2417: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   2420: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2423: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2426: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2429: goto -2021 -> 408
    //   2432: new 601	java/lang/StringBuilder
    //   2435: dup
    //   2436: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2439: ldc_w 1518
    //   2442: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2445: getstatic 280	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2448: invokevirtual 715	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2451: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2454: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2457: goto -1971 -> 486
    //   2460: aload 8
    //   2462: astore_1
    //   2463: aload_3
    //   2464: ldc_w 1339
    //   2467: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2470: invokevirtual 1262	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2473: ldc_w 798
    //   2476: invokevirtual 978	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2479: ifeq -1682 -> 797
    //   2482: aload 8
    //   2484: astore_1
    //   2485: aload_3
    //   2486: ldc_w 1339
    //   2489: invokevirtual 796	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2492: checkcast 798	java/lang/Boolean
    //   2495: invokevirtual 801	java/lang/Boolean:booleanValue	()Z
    //   2498: ifeq -1701 -> 797
    //   2501: ldc -82
    //   2503: astore_1
    //   2504: goto -1707 -> 797
    //   2507: iload 4
    //   2509: sipush 360
    //   2512: if_icmple +42 -> 2554
    //   2515: new 601	java/lang/StringBuilder
    //   2518: dup
    //   2519: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2522: ldc_w 1356
    //   2525: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2528: iload 4
    //   2530: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2533: ldc_w 1520
    //   2536: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2539: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2542: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2545: ldc_w 1521
    //   2548: putstatic 282	com/kochava/android/tracker/Feature:flush_rate	I
    //   2551: goto -1335 -> 1216
    //   2554: iload 4
    //   2556: bipush 60
    //   2558: imul
    //   2559: sipush 1000
    //   2562: imul
    //   2563: putstatic 282	com/kochava/android/tracker/Feature:flush_rate	I
    //   2566: new 601	java/lang/StringBuilder
    //   2569: dup
    //   2570: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2573: ldc_w 1523
    //   2576: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2579: iload 4
    //   2581: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2584: ldc_w 1525
    //   2587: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2590: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2593: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2596: goto -1380 -> 1216
    //   2599: aload 13
    //   2601: ifnull +177 -> 2778
    //   2604: aload 13
    //   2606: invokevirtual 410	java/lang/String:trim	()Ljava/lang/String;
    //   2609: invokevirtual 414	java/lang/String:length	()I
    //   2612: ifeq +166 -> 2778
    //   2615: aload_0
    //   2616: getfield 1331	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2619: ldc_w 1337
    //   2622: aload 13
    //   2624: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2627: pop
    //   2628: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2631: ldc -124
    //   2633: ldc_w 274
    //   2636: invokeinterface 642 3 0
    //   2641: ldc_w 274
    //   2644: invokevirtual 646	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2647: ifeq +26 -> 2673
    //   2650: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2653: invokeinterface 1140 1 0
    //   2658: ldc -124
    //   2660: aload 13
    //   2662: invokeinterface 1146 3 0
    //   2667: invokeinterface 1149 1 0
    //   2672: pop
    //   2673: aload 9
    //   2675: ifnull +76 -> 2751
    //   2678: aload 9
    //   2680: invokevirtual 410	java/lang/String:trim	()Ljava/lang/String;
    //   2683: invokevirtual 414	java/lang/String:length	()I
    //   2686: ifeq +65 -> 2751
    //   2689: aload 9
    //   2691: putstatic 583	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2694: aload_0
    //   2695: getfield 1333	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2698: ldc 92
    //   2700: aload 9
    //   2702: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2705: pop
    //   2706: aload_0
    //   2707: getfield 1335	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2710: ldc 92
    //   2712: aload 9
    //   2714: invokevirtual 624	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2717: pop
    //   2718: goto -1336 -> 1382
    //   2721: astore_1
    //   2722: new 601	java/lang/StringBuilder
    //   2725: dup
    //   2726: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2729: ldc_w 1527
    //   2732: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2735: aload_1
    //   2736: invokevirtual 1528	org/json/JSONException:toString	()Ljava/lang/String;
    //   2739: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2742: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2745: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2748: goto -1157 -> 1591
    //   2751: new 601	java/lang/StringBuilder
    //   2754: dup
    //   2755: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2758: ldc_w 1530
    //   2761: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2764: aload 13
    //   2766: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2769: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2772: putstatic 583	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2775: goto -1393 -> 1382
    //   2778: ldc_w 1532
    //   2781: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2784: iconst_1
    //   2785: putstatic 323	com/kochava/android/tracker/Feature:badInit	Z
    //   2788: return
    //   2789: astore_1
    //   2790: iconst_1
    //   2791: istore 4
    //   2793: new 601	java/lang/StringBuilder
    //   2796: dup
    //   2797: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2800: ldc_w 274
    //   2803: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2806: ldc_w 1534
    //   2809: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2812: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2815: astore_3
    //   2816: goto -1174 -> 1642
    //   2819: astore_1
    //   2820: new 601	java/lang/StringBuilder
    //   2823: dup
    //   2824: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2827: ldc_w 1536
    //   2830: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2833: aload_1
    //   2834: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   2837: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2840: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2843: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2846: goto -1023 -> 1823
    //   2849: astore_1
    //   2850: new 601	java/lang/StringBuilder
    //   2853: dup
    //   2854: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2857: ldc_w 1538
    //   2860: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2863: aload_1
    //   2864: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   2867: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2870: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2873: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2876: goto -1031 -> 1845
    //   2879: astore 5
    //   2881: aconst_null
    //   2882: astore_1
    //   2883: new 601	java/lang/StringBuilder
    //   2886: dup
    //   2887: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2890: ldc_w 1540
    //   2893: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2896: aload 5
    //   2898: invokevirtual 1541	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2901: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2904: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2907: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2910: goto -999 -> 1911
    //   2913: astore_1
    //   2914: new 601	java/lang/StringBuilder
    //   2917: dup
    //   2918: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2921: ldc_w 1540
    //   2924: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2927: aload_1
    //   2928: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   2931: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2934: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2937: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2940: goto -982 -> 1958
    //   2943: ldc_w 1543
    //   2946: astore_1
    //   2947: goto -1026 -> 1921
    //   2950: astore_1
    //   2951: new 601	java/lang/StringBuilder
    //   2954: dup
    //   2955: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2958: ldc_w 1545
    //   2961: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2964: aload_1
    //   2965: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   2968: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2971: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2974: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2977: goto -949 -> 2028
    //   2980: astore_1
    //   2981: new 601	java/lang/StringBuilder
    //   2984: dup
    //   2985: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   2988: ldc_w 1547
    //   2991: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2994: aload_1
    //   2995: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   2998: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3001: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3004: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3007: goto -928 -> 2079
    //   3010: astore_1
    //   3011: new 601	java/lang/StringBuilder
    //   3014: dup
    //   3015: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   3018: ldc_w 1549
    //   3021: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3024: aload_1
    //   3025: invokevirtual 1031	java/lang/Exception:toString	()Ljava/lang/String;
    //   3028: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3031: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3034: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3037: goto -879 -> 2158
    //   3040: astore_1
    //   3041: getstatic 824	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3044: ifeq -848 -> 2196
    //   3047: aload_1
    //   3048: invokevirtual 827	org/json/JSONException:printStackTrace	()V
    //   3051: goto -855 -> 2196
    //   3054: astore_3
    //   3055: new 601	java/lang/StringBuilder
    //   3058: dup
    //   3059: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   3062: ldc_w 1551
    //   3065: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3068: aload 7
    //   3070: getfield 1483	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3073: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3076: ldc_w 1553
    //   3079: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3082: aload_3
    //   3083: invokevirtual 1541	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3086: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3089: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3092: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3095: goto -806 -> 2289
    //   3098: astore_1
    //   3099: goto -2552 -> 547
    //   3102: astore_1
    //   3103: goto -2603 -> 500
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3106	0	this	Feature
    //   0	3106	1	paramContext	Context
    //   0	3106	2	paramBoolean	boolean
    //   0	3106	3	paramHashMap	HashMap<String, Object>
    //   1178	1614	4	i	int
    //   617	1659	5	localObject1	Object
    //   2879	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   584	1668	6	localObject2	Object
    //   590	2479	7	localObject3	Object
    //   600	1883	8	localObject4	Object
    //   587	457	9	localIterator	Iterator
    //   1053	1	9	localException	Exception
    //   1100	1613	9	localObject5	Object
    //   597	669	10	localObject6	Object
    //   603	653	11	localObject7	Object
    //   609	775	12	localObject8	Object
    //   581	2184	13	localObject9	Object
    //   606	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   956	994	1053	java/lang/Exception
    //   994	1050	1053	java/lang/Exception
    //   2196	2241	2363	java/lang/Exception
    //   2241	2269	2363	java/lang/Exception
    //   2275	2287	2363	java/lang/Exception
    //   2293	2360	2363	java/lang/Exception
    //   3055	3095	2363	java/lang/Exception
    //   385	408	2402	java/lang/Exception
    //   1297	1382	2721	org/json/JSONException
    //   1382	1591	2721	org/json/JSONException
    //   2604	2673	2721	org/json/JSONException
    //   2678	2718	2721	org/json/JSONException
    //   2751	2775	2721	org/json/JSONException
    //   2778	2788	2721	org/json/JSONException
    //   1623	1642	2789	android/content/pm/PackageManager$NameNotFoundException
    //   1804	1823	2819	java/lang/Exception
    //   1823	1845	2849	java/lang/Exception
    //   1898	1911	2879	android/content/pm/PackageManager$NameNotFoundException
    //   1887	1898	2913	java/lang/Exception
    //   1898	1911	2913	java/lang/Exception
    //   1915	1921	2913	java/lang/Exception
    //   1921	1958	2913	java/lang/Exception
    //   2883	2910	2913	java/lang/Exception
    //   1958	2028	2950	java/lang/Exception
    //   2028	2079	2980	java/lang/Exception
    //   2079	2158	3010	java/lang/Exception
    //   2182	2196	3040	org/json/JSONException
    //   2275	2287	3054	android/content/pm/PackageManager$NameNotFoundException
    //   522	547	3098	java/lang/Exception
    //   486	500	3102	java/lang/Exception
  }
  
  private void initHandler()
  {
    if (this.initHandler == null) {
      this.initHandler = new Feature.10(this);
    }
  }
  
  private void initialHandlerActions()
  {
    int j = 0;
    prefs = this.mContext.getSharedPreferences("initPrefs", 0);
    if (prefs.getString("initBool", "").equals("")) {
      prefs.edit().putString("initBool", "false").commit();
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
        mTimer.schedule(new Feature.11(this), 0L, flush_rate);
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
      localObject1 = localObject3;
      if (((String)localObject3).startsWith("[")) {
        localObject1 = ((String)localObject3).substring(1);
      }
      localObject3 = localObject1;
      if (((String)localObject1).endsWith("]")) {
        localObject3 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
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
            break label709;
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
      label709:
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
          prefs.edit().putString("initBool", "true").commit();
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
    new Feature.12(paramJSONObject).start();
  }
  
  protected static void sendKVQuery(int paramInt)
  {
    worker.schedule(sendKVQuery, paramInt, TimeUnit.SECONDS);
  }
  
  private static void sendOutsideServicesUpdate(JSONArray paramJSONArray)
  {
    new Feature.15(paramJSONArray).start();
  }
  
  public static void setAttributionHandler(Handler paramHandler)
  {
    attributionDataHandler = paramHandler;
  }
  
  private void setCurrency(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      prefs = this.mContext.getSharedPreferences("initPrefs", 0);
      prefs.edit().putString("currency", paramString).commit();
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
    //   0: getstatic 723	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +259 -> 264
    //   8: getstatic 280	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +253 -> 264
    //   14: getstatic 323	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 50
    //   22: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1794
    //   29: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 315	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 727	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +188 -> 229
    //   44: ldc_w 1796
    //   47: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 734	java/util/Timer
    //   53: dup
    //   54: invokespecial 1255	java/util/Timer:<init>	()V
    //   57: putstatic 727	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 727	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 1798	com/kochava/android/tracker/Feature$7
    //   66: dup
    //   67: invokespecial 1799	com/kochava/android/tracker/Feature$7:<init>	()V
    //   70: getstatic 282	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 282	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1578	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 607	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 608
    //   87: ldiv
    //   88: putstatic 304	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 300	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 465	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 630	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1803	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1806	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +50 -> 165
    //   118: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -100
    //   123: ldc_w 274
    //   126: invokeinterface 642 3 0
    //   131: ldc_w 274
    //   134: invokevirtual 646	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 621	org/json/JSONObject
    //   145: dup
    //   146: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc -100
    //   151: ldc_w 274
    //   154: invokeinterface 642 3 0
    //   159: invokespecial 1807	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1809	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +96 -> 264
    //   171: getstatic 469	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc -118
    //   176: ldc_w 274
    //   179: invokeinterface 642 3 0
    //   184: ldc -82
    //   186: invokevirtual 646	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   189: ifeq +75 -> 264
    //   192: getstatic 325	com/kochava/android/tracker/Feature:canSendSession	Z
    //   195: ifeq +63 -> 258
    //   198: ldc 116
    //   200: invokestatic 740	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   203: return
    //   204: astore_1
    //   205: new 601	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 602	java/lang/StringBuilder:<init>	()V
    //   212: ldc_w 1811
    //   215: invokevirtual 616	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 619	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   228: return
    //   229: ldc_w 1813
    //   232: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   235: goto -154 -> 81
    //   238: astore_1
    //   239: ldc_w 1815
    //   242: invokestatic 662	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   245: getstatic 824	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   248: ifeq -83 -> 165
    //   251: aload_1
    //   252: invokevirtual 827	org/json/JSONException:printStackTrace	()V
    //   255: goto -90 -> 165
    //   258: ldc_w 746
    //   261: invokestatic 718	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
    prefs = this.mContext.getSharedPreferences("initPrefs", 0);
    prefs.edit().putString("mylat", paramString1).commit();
    prefs.edit().putString("mylong", paramString2).commit();
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
            mTimer.schedule(new Feature.6(this), flush_rate, flush_rate);
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
    paramString = new Feature.5(this, paramString);
    worker.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
}
