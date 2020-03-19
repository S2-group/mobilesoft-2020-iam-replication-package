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
  private static final Uri ATTRIBUTION_ID_CONTENT_URI;
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
  
  public Feature(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    init(paramContext, true, paramHashMap);
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
    label1086:
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
                  break label1086;
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
    //   8: getstatic 194	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 470	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc_w 881
    //   20: aastore
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 887	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +18 -> 47
    //   32: aload_0
    //   33: astore_2
    //   34: aload_0
    //   35: astore_3
    //   36: aload_0
    //   37: invokeinterface 892 1 0
    //   42: istore_1
    //   43: iload_1
    //   44: ifne +24 -> 68
    //   47: aload_0
    //   48: ifnull +18 -> 66
    //   51: aload_0
    //   52: invokeinterface 895 1 0
    //   57: ifne +9 -> 66
    //   60: aload_0
    //   61: invokeinterface 898 1 0
    //   66: aconst_null
    //   67: areturn
    //   68: aload_0
    //   69: astore_2
    //   70: aload_0
    //   71: astore_3
    //   72: aload_0
    //   73: aload_0
    //   74: ldc_w 881
    //   77: invokeinterface 902 2 0
    //   82: invokeinterface 903 2 0
    //   87: astore 5
    //   89: aload 5
    //   91: astore_2
    //   92: aload_2
    //   93: astore_3
    //   94: aload_0
    //   95: ifnull +22 -> 117
    //   98: aload_2
    //   99: astore_3
    //   100: aload_0
    //   101: invokeinterface 895 1 0
    //   106: ifne +11 -> 117
    //   109: aload_0
    //   110: invokeinterface 898 1 0
    //   115: aload_2
    //   116: astore_3
    //   117: aload_3
    //   118: areturn
    //   119: astore_0
    //   120: aload_2
    //   121: astore_3
    //   122: new 422	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 905
    //   132: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_0
    //   136: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   139: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   148: aload 4
    //   150: astore_3
    //   151: aload_2
    //   152: ifnull -35 -> 117
    //   155: aload 4
    //   157: astore_3
    //   158: aload_2
    //   159: invokeinterface 895 1 0
    //   164: ifne -47 -> 117
    //   167: aload_2
    //   168: invokeinterface 898 1 0
    //   173: aload 4
    //   175: astore_3
    //   176: goto -59 -> 117
    //   179: astore_0
    //   180: aload 4
    //   182: astore_3
    //   183: goto -66 -> 117
    //   186: astore_0
    //   187: aload_3
    //   188: ifnull +18 -> 206
    //   191: aload_3
    //   192: invokeinterface 895 1 0
    //   197: ifne +9 -> 206
    //   200: aload_3
    //   201: invokeinterface 898 1 0
    //   206: aload_0
    //   207: athrow
    //   208: astore_2
    //   209: goto -3 -> 206
    //   212: astore_0
    //   213: aload_2
    //   214: astore_3
    //   215: goto -98 -> 117
    //   218: astore_0
    //   219: goto -153 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	paramContentResolver	android.content.ContentResolver
    //   42	2	1	bool	boolean
    //   6	162	2	localObject1	Object
    //   208	6	2	localException	Exception
    //   4	211	3	localObject2	Object
    //   1	180	4	localObject3	Object
    //   87	3	5	str	String
    // Exception table:
    //   from	to	target	type
    //   7	28	119	java/lang/Exception
    //   36	43	119	java/lang/Exception
    //   72	89	119	java/lang/Exception
    //   158	173	179	java/lang/Exception
    //   7	28	186	finally
    //   36	43	186	finally
    //   72	89	186	finally
    //   122	148	186	finally
    //   191	206	208	java/lang/Exception
    //   100	115	212	java/lang/Exception
    //   51	66	218	java/lang/Exception
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
        break label734;
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
    //   1: ifnull +2403 -> 2404
    //   4: aload_0
    //   5: aload_1
    //   6: putfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   9: iload_2
    //   10: putstatic 139	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   13: ldc_w 1080
    //   16: new 422	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   23: ldc_w 1082
    //   26: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: getstatic 118	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   32: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 1088	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: getstatic 452	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   45: ifnonnull +10 -> 55
    //   48: aload_1
    //   49: invokevirtual 1092	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   52: putstatic 452	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   55: aload_0
    //   56: new 568	java/util/Timer
    //   59: dup
    //   60: invokespecial 1093	java/util/Timer:<init>	()V
    //   63: putfield 599	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   66: aload_0
    //   67: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   70: ldc_w 454
    //   73: iconst_0
    //   74: invokevirtual 460	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   77: putstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   80: new 809	com/kochava/android/tracker/DbAdapter
    //   83: dup
    //   84: aload_0
    //   85: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   88: invokespecial 1094	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   91: putstatic 350	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   94: aload_3
    //   95: ifnull +231 -> 326
    //   98: aload_3
    //   99: ldc_w 1096
    //   102: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   105: ifnull +44 -> 149
    //   108: aload_3
    //   109: ldc_w 1096
    //   112: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   115: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   118: ldc_w 642
    //   121: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +25 -> 149
    //   127: aload_3
    //   128: ldc_w 1096
    //   131: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   134: checkcast 642	java/lang/Boolean
    //   137: invokevirtual 645	java/lang/Boolean:booleanValue	()Z
    //   140: istore_2
    //   141: iload_2
    //   142: invokestatic 1103	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   145: iload_2
    //   146: invokestatic 1106	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   149: aload_3
    //   150: ldc_w 1108
    //   153: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   156: ifnull +35 -> 191
    //   159: aload_3
    //   160: ldc_w 1108
    //   163: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   166: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   169: ldc_w 470
    //   172: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   175: ifeq +16 -> 191
    //   178: aload_3
    //   179: ldc_w 1108
    //   182: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   185: checkcast 470	java/lang/String
    //   188: putstatic 118	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   191: aload_3
    //   192: ldc_w 1110
    //   195: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   198: ifnull +38 -> 236
    //   201: aload_3
    //   202: ldc_w 1110
    //   205: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   208: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   211: ldc_w 642
    //   214: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   217: ifeq +19 -> 236
    //   220: aload_3
    //   221: ldc_w 1110
    //   224: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   227: checkcast 642	java/lang/Boolean
    //   230: invokevirtual 645	java/lang/Boolean:booleanValue	()Z
    //   233: putstatic 120	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   236: aload_3
    //   237: ldc_w 1112
    //   240: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   243: ifnull +38 -> 281
    //   246: aload_3
    //   247: ldc_w 1112
    //   250: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   253: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   256: ldc_w 642
    //   259: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   262: ifeq +19 -> 281
    //   265: aload_3
    //   266: ldc_w 1112
    //   269: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   272: checkcast 642	java/lang/Boolean
    //   275: invokevirtual 645	java/lang/Boolean:booleanValue	()Z
    //   278: putstatic 137	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   281: aload_3
    //   282: ldc_w 1114
    //   285: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   288: ifnull +38 -> 326
    //   291: aload_3
    //   292: ldc_w 1114
    //   295: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   298: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   301: ldc_w 642
    //   304: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   307: ifeq +19 -> 326
    //   310: aload_3
    //   311: ldc_w 1114
    //   314: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   317: checkcast 642	java/lang/Boolean
    //   320: invokevirtual 645	java/lang/Boolean:booleanValue	()Z
    //   323: putstatic 156	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   326: aload_0
    //   327: invokespecial 1116	com/kochava/android/tracker/Feature:initHandler	()V
    //   330: new 422	java/lang/StringBuilder
    //   333: dup
    //   334: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   337: ldc_w 1118
    //   340: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   346: ldc_w 1120
    //   349: ldc 114
    //   351: invokeinterface 468 3 0
    //   356: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   365: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   368: ldc_w 1120
    //   371: ldc 114
    //   373: invokeinterface 468 3 0
    //   378: ldc 114
    //   380: invokevirtual 474	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   383: ifne +26 -> 409
    //   386: new 201	org/json/JSONArray
    //   389: dup
    //   390: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   393: ldc_w 1120
    //   396: ldc 114
    //   398: invokeinterface 468 3 0
    //   403: invokespecial 1121	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   406: putstatic 204	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   409: getstatic 555	android/os/Build$VERSION:SDK_INT	I
    //   412: bipush 14
    //   414: if_icmplt +2031 -> 2445
    //   417: getstatic 120	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   420: ifne +2025 -> 2445
    //   423: new 422	java/lang/StringBuilder
    //   426: dup
    //   427: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   430: ldc_w 1123
    //   433: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: getstatic 120	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   439: invokevirtual 547	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   442: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   445: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   448: getstatic 452	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   451: checkcast 1125	android/app/Application
    //   454: new 10	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   457: dup
    //   458: aload_0
    //   459: invokespecial 1126	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   462: invokevirtual 1130	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   465: getstatic 452	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   468: new 12	com/kochava/android/tracker/Feature$MemoryBoss
    //   471: dup
    //   472: aload_0
    //   473: invokespecial 1131	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   476: invokevirtual 1135	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   479: iconst_1
    //   480: putstatic 1138	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   483: iconst_1
    //   484: putstatic 1141	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   487: aload_0
    //   488: aload_0
    //   489: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   492: invokevirtual 750	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   495: invokestatic 754	com/kochava/android/tracker/Feature:getAttributionId	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   498: putfield 756	com/kochava/android/tracker/Feature:mFbId	Ljava/lang/String;
    //   501: getstatic 137	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   504: ifne +19 -> 523
    //   507: new 1143	com/kochava/android/tracker/Feature$2
    //   510: dup
    //   511: aload_0
    //   512: invokespecial 1144	com/kochava/android/tracker/Feature$2:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   515: iconst_0
    //   516: anewarray 1146	java/lang/Void
    //   519: invokevirtual 1152	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   522: pop
    //   523: aload_0
    //   524: aload_0
    //   525: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   528: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   531: aload_0
    //   532: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   535: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   538: iconst_0
    //   539: invokevirtual 1165	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   542: getfield 1170	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   545: putfield 871	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   548: aload_0
    //   549: new 442	org/json/JSONObject
    //   552: dup
    //   553: invokespecial 613	org/json/JSONObject:<init>	()V
    //   556: putfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   559: aload_0
    //   560: new 442	org/json/JSONObject
    //   563: dup
    //   564: invokespecial 613	org/json/JSONObject:<init>	()V
    //   567: putfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   570: aload_0
    //   571: new 442	org/json/JSONObject
    //   574: dup
    //   575: invokespecial 613	org/json/JSONObject:<init>	()V
    //   578: putfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   581: aconst_null
    //   582: astore 13
    //   584: aconst_null
    //   585: astore 6
    //   587: aconst_null
    //   588: astore 9
    //   590: aconst_null
    //   591: astore 7
    //   593: ldc_w 630
    //   596: astore_1
    //   597: aconst_null
    //   598: astore 10
    //   600: aconst_null
    //   601: astore 8
    //   603: aconst_null
    //   604: astore 11
    //   606: aconst_null
    //   607: astore 14
    //   609: aload_1
    //   610: astore 12
    //   612: aload_3
    //   613: ifnull +633 -> 1246
    //   616: aload 6
    //   618: astore 5
    //   620: aload_3
    //   621: ldc_w 1178
    //   624: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   627: ifnull +38 -> 665
    //   630: aload 6
    //   632: astore 5
    //   634: aload_3
    //   635: ldc_w 1178
    //   638: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   641: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   644: ldc_w 470
    //   647: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   650: ifeq +15 -> 665
    //   653: aload_3
    //   654: ldc_w 1178
    //   657: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   660: checkcast 470	java/lang/String
    //   663: astore 5
    //   665: aload 7
    //   667: astore 6
    //   669: aload_3
    //   670: ldc_w 615
    //   673: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   676: ifnull +38 -> 714
    //   679: aload 7
    //   681: astore 6
    //   683: aload_3
    //   684: ldc_w 615
    //   687: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   690: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   693: ldc_w 470
    //   696: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   699: ifeq +15 -> 714
    //   702: aload_3
    //   703: ldc_w 615
    //   706: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   709: checkcast 470	java/lang/String
    //   712: astore 6
    //   714: aload_1
    //   715: astore 7
    //   717: aload_3
    //   718: ldc_w 628
    //   721: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   724: ifnull +37 -> 761
    //   727: aload_1
    //   728: astore 7
    //   730: aload_3
    //   731: ldc_w 628
    //   734: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   737: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   740: ldc_w 470
    //   743: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   746: ifeq +15 -> 761
    //   749: aload_3
    //   750: ldc_w 628
    //   753: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   756: checkcast 470	java/lang/String
    //   759: astore 7
    //   761: aload 8
    //   763: astore_1
    //   764: aload_3
    //   765: ldc_w 1180
    //   768: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   771: ifnull +33 -> 804
    //   774: aload_3
    //   775: ldc_w 1180
    //   778: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   781: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   784: ldc_w 470
    //   787: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   790: ifeq +1683 -> 2473
    //   793: aload_3
    //   794: ldc_w 1180
    //   797: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   800: checkcast 470	java/lang/String
    //   803: astore_1
    //   804: aload_3
    //   805: ldc_w 1182
    //   808: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   811: ifnull +34 -> 845
    //   814: aload_3
    //   815: ldc_w 1182
    //   818: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   821: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   824: ldc_w 470
    //   827: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   830: ifeq +15 -> 845
    //   833: aload_3
    //   834: ldc_w 1182
    //   837: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: checkcast 470	java/lang/String
    //   843: astore 8
    //   845: aload 14
    //   847: astore 8
    //   849: aload_3
    //   850: ldc_w 1184
    //   853: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   856: ifnull +38 -> 894
    //   859: aload 14
    //   861: astore 8
    //   863: aload_3
    //   864: ldc_w 1184
    //   867: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   870: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   873: ldc_w 470
    //   876: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   879: ifeq +15 -> 894
    //   882: aload_3
    //   883: ldc_w 1184
    //   886: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   889: checkcast 470	java/lang/String
    //   892: astore 8
    //   894: aload_3
    //   895: ldc_w 720
    //   898: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   901: ifnull +39 -> 940
    //   904: aload_3
    //   905: ldc_w 720
    //   908: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   911: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   914: ldc_w 642
    //   917: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   920: ifeq +20 -> 940
    //   923: aload_0
    //   924: aload_3
    //   925: ldc_w 720
    //   928: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   931: checkcast 642	java/lang/Boolean
    //   934: invokevirtual 645	java/lang/Boolean:booleanValue	()Z
    //   937: putfield 219	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   940: aload_3
    //   941: ldc_w 738
    //   944: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   947: ifnull +115 -> 1062
    //   950: aload_3
    //   951: ldc_w 738
    //   954: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   957: instanceof 636
    //   960: ifeq +102 -> 1062
    //   963: aload_3
    //   964: ldc_w 738
    //   967: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   970: checkcast 636	java/util/HashMap
    //   973: putstatic 1186	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   976: new 442	org/json/JSONObject
    //   979: dup
    //   980: invokespecial 613	org/json/JSONObject:<init>	()V
    //   983: putstatic 736	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   986: getstatic 1186	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   989: invokeinterface 792 1 0
    //   994: invokeinterface 795 1 0
    //   999: astore 9
    //   1001: aload 9
    //   1003: invokeinterface 501 1 0
    //   1008: ifeq +54 -> 1062
    //   1011: aload 9
    //   1013: invokeinterface 505 1 0
    //   1018: checkcast 797	java/util/Map$Entry
    //   1021: astore 10
    //   1023: getstatic 736	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   1026: aload 10
    //   1028: invokeinterface 800 1 0
    //   1033: checkcast 470	java/lang/String
    //   1036: aload 10
    //   1038: invokeinterface 803 1 0
    //   1043: checkcast 470	java/lang/String
    //   1046: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1049: pop
    //   1050: aload 9
    //   1052: invokeinterface 1189 1 0
    //   1057: goto -56 -> 1001
    //   1060: astore 9
    //   1062: aload_3
    //   1063: ldc_w 744
    //   1066: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1069: ifnull +36 -> 1105
    //   1072: aload_3
    //   1073: ldc_w 744
    //   1076: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1079: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1082: ldc_w 470
    //   1085: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1088: ifeq +17 -> 1105
    //   1091: aload_0
    //   1092: aload_3
    //   1093: ldc_w 744
    //   1096: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: checkcast 470	java/lang/String
    //   1102: putfield 740	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
    //   1105: aload 6
    //   1107: astore 9
    //   1109: aload_1
    //   1110: astore 10
    //   1112: aload 8
    //   1114: astore 11
    //   1116: aload 7
    //   1118: astore 12
    //   1120: aload 5
    //   1122: astore 13
    //   1124: aload_3
    //   1125: ldc_w 1190
    //   1128: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1131: ifnull +115 -> 1246
    //   1134: aload 6
    //   1136: astore 9
    //   1138: aload_1
    //   1139: astore 10
    //   1141: aload 8
    //   1143: astore 11
    //   1145: aload 7
    //   1147: astore 12
    //   1149: aload 5
    //   1151: astore 13
    //   1153: aload_3
    //   1154: ldc_w 1190
    //   1157: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1160: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1163: ldc_w 1192
    //   1166: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1169: ifeq +77 -> 1246
    //   1172: aload_3
    //   1173: ldc_w 1190
    //   1176: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1179: checkcast 1192	java/lang/Integer
    //   1182: invokevirtual 1195	java/lang/Integer:intValue	()I
    //   1185: istore 4
    //   1187: iload 4
    //   1189: iconst_1
    //   1190: if_icmpge +1331 -> 2521
    //   1193: new 422	java/lang/StringBuilder
    //   1196: dup
    //   1197: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1200: ldc_w 1197
    //   1203: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1206: iload 4
    //   1208: invokevirtual 835	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1211: ldc_w 1199
    //   1214: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1217: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1220: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1223: iconst_1
    //   1224: putstatic 125	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
    //   1227: aload 5
    //   1229: astore 13
    //   1231: aload 7
    //   1233: astore 12
    //   1235: aload 8
    //   1237: astore 11
    //   1239: aload_1
    //   1240: astore 10
    //   1242: aload 6
    //   1244: astore 9
    //   1246: aload 11
    //   1248: ifnull +19 -> 1267
    //   1251: aload 11
    //   1253: invokevirtual 998	java/lang/String:trim	()Ljava/lang/String;
    //   1256: invokevirtual 939	java/lang/String:length	()I
    //   1259: ifeq +8 -> 1267
    //   1262: aload 11
    //   1264: putstatic 116	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1267: aload 10
    //   1269: ifnull +18 -> 1287
    //   1272: aload 10
    //   1274: ldc_w 1201
    //   1277: invokevirtual 1204	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1280: ifeq +7 -> 1287
    //   1283: iconst_1
    //   1284: putstatic 170	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1287: aload_0
    //   1288: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1291: ldc_w 1206
    //   1294: iconst_0
    //   1295: invokevirtual 460	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1298: putstatic 316	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1301: aload 9
    //   1303: ifnull +1310 -> 2613
    //   1306: aload 9
    //   1308: invokevirtual 998	java/lang/String:trim	()Ljava/lang/String;
    //   1311: invokevirtual 939	java/lang/String:length	()I
    //   1314: ifeq +1299 -> 2613
    //   1317: aload 9
    //   1319: putstatic 404	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1322: aload_0
    //   1323: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1326: ldc_w 615
    //   1329: aload 9
    //   1331: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1334: pop
    //   1335: aload_0
    //   1336: getfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1339: ldc_w 615
    //   1342: aload 9
    //   1344: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1347: pop
    //   1348: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1351: ldc_w 1208
    //   1354: ldc 114
    //   1356: invokeinterface 468 3 0
    //   1361: ldc 114
    //   1363: invokevirtual 474	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1366: ifeq +27 -> 1393
    //   1369: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1372: invokeinterface 973 1 0
    //   1377: ldc_w 1208
    //   1380: aload 9
    //   1382: invokeinterface 979 3 0
    //   1387: invokeinterface 982 1 0
    //   1392: pop
    //   1393: aload_0
    //   1394: aload 12
    //   1396: invokespecial 412	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1399: aload_0
    //   1400: getfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1403: ldc_w 1210
    //   1406: aload_0
    //   1407: invokespecial 709	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1410: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1413: pop
    //   1414: aload_0
    //   1415: getfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1418: ldc_w 1212
    //   1421: ldc_w 1214
    //   1424: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1427: pop
    //   1428: aload_0
    //   1429: getfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1432: ldc_w 1216
    //   1435: ldc_w 1218
    //   1438: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1441: pop
    //   1442: aload_0
    //   1443: getfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1446: ldc_w 628
    //   1449: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1452: ldc_w 628
    //   1455: ldc_w 630
    //   1458: invokeinterface 468 3 0
    //   1463: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1466: pop
    //   1467: aload_0
    //   1468: getfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1471: ldc_w 628
    //   1474: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1477: ldc_w 628
    //   1480: ldc_w 630
    //   1483: invokeinterface 468 3 0
    //   1488: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1491: pop
    //   1492: aload_0
    //   1493: getfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1496: ldc_w 1216
    //   1499: ldc_w 1218
    //   1502: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1505: pop
    //   1506: aload_0
    //   1507: getfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1510: ldc_w 628
    //   1513: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1516: ldc_w 628
    //   1519: ldc_w 630
    //   1522: invokeinterface 468 3 0
    //   1527: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1530: pop
    //   1531: aload_0
    //   1532: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1535: ldc_w 675
    //   1538: new 422	java/lang/StringBuilder
    //   1541: dup
    //   1542: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1545: ldc_w 677
    //   1548: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1551: getstatic 118	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1554: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1557: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1560: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1563: pop
    //   1564: aload_0
    //   1565: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1568: ldc_w 1220
    //   1571: ldc_w 1222
    //   1574: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1577: pop
    //   1578: aload_0
    //   1579: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1582: ldc_w 805
    //   1585: aload_0
    //   1586: getfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1589: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1592: pop
    //   1593: aload_0
    //   1594: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1597: ldc_w 1224
    //   1600: aload_0
    //   1601: getfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1604: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1607: pop
    //   1608: invokestatic 428	java/lang/System:currentTimeMillis	()J
    //   1611: ldc2_w 429
    //   1614: ldiv
    //   1615: putstatic 145	com/kochava/android/tracker/Feature:startTime	J
    //   1618: iconst_0
    //   1619: istore 4
    //   1621: ldc 114
    //   1623: astore_3
    //   1624: new 514	android/content/ComponentName
    //   1627: dup
    //   1628: aload_0
    //   1629: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1632: ldc_w 1226
    //   1635: invokespecial 526	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1638: astore_1
    //   1639: aload_0
    //   1640: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1643: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1646: aload_1
    //   1647: iconst_0
    //   1648: invokevirtual 1230	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1651: pop
    //   1652: ldc_w 1232
    //   1655: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1658: aload_3
    //   1659: astore_1
    //   1660: aload_0
    //   1661: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1664: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1667: ldc_w 1234
    //   1670: aload_0
    //   1671: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1674: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1677: invokevirtual 1237	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1680: ifge +27 -> 1707
    //   1683: iconst_1
    //   1684: istore 4
    //   1686: new 422	java/lang/StringBuilder
    //   1689: dup
    //   1690: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1693: aload_3
    //   1694: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1697: ldc_w 1239
    //   1700: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1703: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1706: astore_1
    //   1707: aload_1
    //   1708: astore_3
    //   1709: aload_0
    //   1710: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1713: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1716: ldc_w 1241
    //   1719: aload_0
    //   1720: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1723: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1726: invokevirtual 1237	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1729: ifge +27 -> 1756
    //   1732: iconst_1
    //   1733: istore 4
    //   1735: new 422	java/lang/StringBuilder
    //   1738: dup
    //   1739: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1742: aload_1
    //   1743: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1746: ldc_w 1243
    //   1749: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1752: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1755: astore_3
    //   1756: aload_3
    //   1757: astore_1
    //   1758: aload_0
    //   1759: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1762: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1765: ldc_w 1245
    //   1768: aload_0
    //   1769: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1772: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1775: invokevirtual 1237	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1778: ifge +27 -> 1805
    //   1781: iconst_1
    //   1782: istore 4
    //   1784: new 422	java/lang/StringBuilder
    //   1787: dup
    //   1788: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1791: aload_3
    //   1792: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1795: ldc_w 1247
    //   1798: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1801: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1804: astore_1
    //   1805: iload 4
    //   1807: ifeq +13 -> 1820
    //   1810: ldc_w 1249
    //   1813: invokestatic 1252	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1816: aload_1
    //   1817: invokestatic 1252	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1820: aload_0
    //   1821: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1824: ldc_w 1254
    //   1827: invokevirtual 762	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1830: checkcast 1256	android/telephony/TelephonyManager
    //   1833: invokevirtual 1259	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1836: putstatic 689	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1839: aload_0
    //   1840: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1843: ldc_w 1261
    //   1846: invokevirtual 762	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1849: checkcast 1263	android/net/wifi/WifiManager
    //   1852: invokevirtual 1267	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1855: invokevirtual 1272	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1858: putstatic 685	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1861: aload_0
    //   1862: invokespecial 1274	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1865: putstatic 397	com/kochava/android/tracker/Feature:mUserAgent	Ljava/lang/String;
    //   1868: aload_0
    //   1869: invokestatic 401	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1872: putfield 1276	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1875: aload_0
    //   1876: invokestatic 394	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1879: putfield 1278	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1882: aload_0
    //   1883: ldc_w 1280
    //   1886: putfield 875	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1889: aload_0
    //   1890: ldc_w 1282
    //   1893: putfield 879	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1896: aload_0
    //   1897: ldc 114
    //   1899: putfield 327	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1902: aload_0
    //   1903: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1906: invokevirtual 1092	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1909: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1912: astore_3
    //   1913: aload_3
    //   1914: aload_0
    //   1915: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1918: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1921: iconst_0
    //   1922: invokevirtual 1286	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1925: astore_1
    //   1926: aload_1
    //   1927: ifnull +1031 -> 2958
    //   1930: aload_3
    //   1931: aload_1
    //   1932: invokevirtual 1290	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1935: astore_1
    //   1936: aload_0
    //   1937: aload_1
    //   1938: checkcast 470	java/lang/String
    //   1941: checkcast 470	java/lang/String
    //   1944: putfield 875	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1947: new 422	java/lang/StringBuilder
    //   1950: dup
    //   1951: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1954: ldc_w 1292
    //   1957: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1960: aload_0
    //   1961: getfield 875	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1964: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1967: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1970: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1973: aload_0
    //   1974: new 422	java/lang/StringBuilder
    //   1977: dup
    //   1978: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   1981: aload_0
    //   1982: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1985: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1988: aload_0
    //   1989: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1992: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1995: iconst_0
    //   1996: invokevirtual 1165	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1999: getfield 1295	android/content/pm/PackageInfo:versionCode	I
    //   2002: invokevirtual 835	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2005: ldc 114
    //   2007: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2010: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2013: putfield 879	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2016: new 422	java/lang/StringBuilder
    //   2019: dup
    //   2020: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2023: ldc_w 1297
    //   2026: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2029: aload_0
    //   2030: getfield 879	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2033: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2036: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2039: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2042: aload_0
    //   2043: aload_0
    //   2044: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2047: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2050: aload_0
    //   2051: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2054: invokevirtual 1159	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2057: iconst_0
    //   2058: invokevirtual 1165	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2061: getfield 1300	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2064: putfield 327	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2067: new 422	java/lang/StringBuilder
    //   2070: dup
    //   2071: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2074: ldc_w 1302
    //   2077: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2080: aload_0
    //   2081: getfield 327	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2084: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2087: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2090: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2093: aload_0
    //   2094: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2097: ldc_w 758
    //   2100: invokevirtual 762	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2103: checkcast 764	android/view/WindowManager
    //   2106: astore_1
    //   2107: aload_0
    //   2108: aload_1
    //   2109: invokeinterface 771 1 0
    //   2114: invokevirtual 1305	android/view/Display:getHeight	()I
    //   2117: putfield 700	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2120: aload_0
    //   2121: aload_1
    //   2122: invokeinterface 771 1 0
    //   2127: invokevirtual 1308	android/view/Display:getWidth	()I
    //   2130: putfield 704	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2133: new 422	java/lang/StringBuilder
    //   2136: dup
    //   2137: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2140: ldc_w 1310
    //   2143: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2146: aload_0
    //   2147: getfield 700	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2150: invokevirtual 835	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2153: ldc_w 1312
    //   2156: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2159: aload_0
    //   2160: getfield 704	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2163: invokevirtual 835	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2166: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2169: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2172: aload_0
    //   2173: aload_0
    //   2174: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2177: invokevirtual 750	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   2180: ldc_w 715
    //   2183: invokestatic 1317	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   2186: putfield 717	com/kochava/android/tracker/Feature:mAndroidID	Ljava/lang/String;
    //   2189: aload_0
    //   2190: invokestatic 313	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2193: putfield 1319	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2196: aload_0
    //   2197: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2200: ldc_w 617
    //   2203: invokestatic 313	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2206: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2209: pop
    //   2210: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2213: ldc_w 1321
    //   2216: ldc 114
    //   2218: invokeinterface 468 3 0
    //   2223: ldc_w 1201
    //   2226: invokevirtual 474	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2229: ifne +148 -> 2377
    //   2232: aload_0
    //   2233: getfield 416	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2236: invokevirtual 1156	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2239: astore 5
    //   2241: aload 5
    //   2243: sipush 128
    //   2246: invokevirtual 1325	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2249: invokeinterface 650 1 0
    //   2254: astore 6
    //   2256: aload 6
    //   2258: invokeinterface 501 1 0
    //   2263: ifeq +114 -> 2377
    //   2266: aload 6
    //   2268: invokeinterface 505 1 0
    //   2273: checkcast 1327	android/content/pm/ApplicationInfo
    //   2276: astore 7
    //   2278: aload 7
    //   2280: invokestatic 1331	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2283: istore_2
    //   2284: iload_2
    //   2285: ifne -29 -> 2256
    //   2288: aconst_null
    //   2289: astore_1
    //   2290: aload 5
    //   2292: aload 7
    //   2294: getfield 1332	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2297: iconst_0
    //   2298: invokevirtual 1165	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2301: astore_3
    //   2302: aload_3
    //   2303: astore_1
    //   2304: aload_1
    //   2305: ifnull -49 -> 2256
    //   2308: getstatic 154	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2311: new 8	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2314: dup
    //   2315: aload_0
    //   2316: aload_1
    //   2317: getfield 1170	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2320: new 422	java/lang/StringBuilder
    //   2323: dup
    //   2324: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2327: aload_1
    //   2328: getfield 1335	android/content/pm/PackageInfo:firstInstallTime	J
    //   2331: invokevirtual 434	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2334: ldc 114
    //   2336: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2339: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2342: new 422	java/lang/StringBuilder
    //   2345: dup
    //   2346: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2349: aload_1
    //   2350: getfield 1338	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2353: invokevirtual 434	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2356: ldc 114
    //   2358: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2361: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2364: invokespecial 1341	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2367: invokeinterface 1344 2 0
    //   2372: pop
    //   2373: goto -117 -> 2256
    //   2376: astore_1
    //   2377: getstatic 184	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2380: aload_0
    //   2381: getfield 228	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2384: ldc2_w 1345
    //   2387: getstatic 1352	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2390: invokeinterface 1357 5 0
    //   2395: pop
    //   2396: aload_0
    //   2397: ldc_w 1358
    //   2400: invokevirtual 1361	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2403: return
    //   2404: ldc_w 1363
    //   2407: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2410: iconst_1
    //   2411: putstatic 164	com/kochava/android/tracker/Feature:badInit	Z
    //   2414: return
    //   2415: astore_1
    //   2416: new 422	java/lang/StringBuilder
    //   2419: dup
    //   2420: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2423: ldc_w 1365
    //   2426: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2429: aload_1
    //   2430: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   2433: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2436: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2439: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2442: goto -2033 -> 409
    //   2445: new 422	java/lang/StringBuilder
    //   2448: dup
    //   2449: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2452: ldc_w 1367
    //   2455: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2458: getstatic 120	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2461: invokevirtual 547	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2464: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2467: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2470: goto -1983 -> 487
    //   2473: aload 8
    //   2475: astore_1
    //   2476: aload_3
    //   2477: ldc_w 1180
    //   2480: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2483: invokevirtual 1100	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2486: ldc_w 642
    //   2489: invokevirtual 1101	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2492: ifeq -1688 -> 804
    //   2495: aload 8
    //   2497: astore_1
    //   2498: aload_3
    //   2499: ldc_w 1180
    //   2502: invokevirtual 640	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2505: checkcast 642	java/lang/Boolean
    //   2508: invokevirtual 645	java/lang/Boolean:booleanValue	()Z
    //   2511: ifeq -1707 -> 804
    //   2514: ldc_w 1201
    //   2517: astore_1
    //   2518: goto -1714 -> 804
    //   2521: iload 4
    //   2523: sipush 360
    //   2526: if_icmple +42 -> 2568
    //   2529: new 422	java/lang/StringBuilder
    //   2532: dup
    //   2533: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2536: ldc_w 1197
    //   2539: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2542: iload 4
    //   2544: invokevirtual 835	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2547: ldc_w 1369
    //   2550: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2553: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2556: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2559: ldc_w 1370
    //   2562: putstatic 123	com/kochava/android/tracker/Feature:flush_rate	I
    //   2565: goto -1342 -> 1223
    //   2568: iload 4
    //   2570: bipush 60
    //   2572: imul
    //   2573: sipush 1000
    //   2576: imul
    //   2577: putstatic 123	com/kochava/android/tracker/Feature:flush_rate	I
    //   2580: new 422	java/lang/StringBuilder
    //   2583: dup
    //   2584: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2587: ldc_w 1372
    //   2590: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2593: iload 4
    //   2595: invokevirtual 835	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2598: ldc_w 1374
    //   2601: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2604: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2607: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2610: goto -1387 -> 1223
    //   2613: aload 13
    //   2615: ifnull +179 -> 2794
    //   2618: aload 13
    //   2620: invokevirtual 998	java/lang/String:trim	()Ljava/lang/String;
    //   2623: invokevirtual 939	java/lang/String:length	()I
    //   2626: ifeq +168 -> 2794
    //   2629: aload_0
    //   2630: getfield 1172	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2633: ldc_w 1178
    //   2636: aload 13
    //   2638: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2641: pop
    //   2642: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2645: ldc_w 1208
    //   2648: ldc 114
    //   2650: invokeinterface 468 3 0
    //   2655: ldc 114
    //   2657: invokevirtual 474	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2660: ifeq +27 -> 2687
    //   2663: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2666: invokeinterface 973 1 0
    //   2671: ldc_w 1208
    //   2674: aload 13
    //   2676: invokeinterface 979 3 0
    //   2681: invokeinterface 982 1 0
    //   2686: pop
    //   2687: aload 9
    //   2689: ifnull +78 -> 2767
    //   2692: aload 9
    //   2694: invokevirtual 998	java/lang/String:trim	()Ljava/lang/String;
    //   2697: invokevirtual 939	java/lang/String:length	()I
    //   2700: ifeq +67 -> 2767
    //   2703: aload 9
    //   2705: putstatic 404	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2708: aload_0
    //   2709: getfield 1174	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2712: ldc_w 615
    //   2715: aload 9
    //   2717: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2720: pop
    //   2721: aload_0
    //   2722: getfield 1176	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2725: ldc_w 615
    //   2728: aload 9
    //   2730: invokevirtual 446	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2733: pop
    //   2734: goto -1341 -> 1393
    //   2737: astore_1
    //   2738: new 422	java/lang/StringBuilder
    //   2741: dup
    //   2742: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2745: ldc_w 1376
    //   2748: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2751: aload_1
    //   2752: invokevirtual 1377	org/json/JSONException:toString	()Ljava/lang/String;
    //   2755: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2758: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2761: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2764: goto -1156 -> 1608
    //   2767: new 422	java/lang/StringBuilder
    //   2770: dup
    //   2771: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2774: ldc_w 1379
    //   2777: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2780: aload 13
    //   2782: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2785: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2788: putstatic 404	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2791: goto -1398 -> 1393
    //   2794: ldc_w 1381
    //   2797: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2800: iconst_1
    //   2801: putstatic 164	com/kochava/android/tracker/Feature:badInit	Z
    //   2804: return
    //   2805: astore_1
    //   2806: iconst_1
    //   2807: istore 4
    //   2809: new 422	java/lang/StringBuilder
    //   2812: dup
    //   2813: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2816: ldc 114
    //   2818: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2821: ldc_w 1383
    //   2824: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2827: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2830: astore_3
    //   2831: goto -1173 -> 1658
    //   2834: astore_1
    //   2835: new 422	java/lang/StringBuilder
    //   2838: dup
    //   2839: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2842: ldc_w 1385
    //   2845: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2848: aload_1
    //   2849: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   2852: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2855: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2858: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2861: goto -1022 -> 1839
    //   2864: astore_1
    //   2865: new 422	java/lang/StringBuilder
    //   2868: dup
    //   2869: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2872: ldc_w 1387
    //   2875: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2878: aload_1
    //   2879: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   2882: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2885: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2888: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2891: goto -1030 -> 1861
    //   2894: astore 5
    //   2896: aconst_null
    //   2897: astore_1
    //   2898: new 422	java/lang/StringBuilder
    //   2901: dup
    //   2902: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2905: ldc_w 1389
    //   2908: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2911: aload 5
    //   2913: invokevirtual 1390	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2916: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2919: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2922: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2925: goto -999 -> 1926
    //   2928: astore_1
    //   2929: new 422	java/lang/StringBuilder
    //   2932: dup
    //   2933: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2936: ldc_w 1389
    //   2939: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2942: aload_1
    //   2943: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   2946: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2949: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2952: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2955: goto -982 -> 1973
    //   2958: ldc_w 1392
    //   2961: astore_1
    //   2962: goto -1026 -> 1936
    //   2965: astore_1
    //   2966: new 422	java/lang/StringBuilder
    //   2969: dup
    //   2970: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   2973: ldc_w 1394
    //   2976: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2979: aload_1
    //   2980: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   2983: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2986: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2989: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2992: goto -950 -> 2042
    //   2995: astore_1
    //   2996: new 422	java/lang/StringBuilder
    //   2999: dup
    //   3000: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   3003: ldc_w 1396
    //   3006: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3009: aload_1
    //   3010: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   3013: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3016: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3019: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3022: goto -929 -> 2093
    //   3025: astore_1
    //   3026: new 422	java/lang/StringBuilder
    //   3029: dup
    //   3030: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   3033: ldc_w 1398
    //   3036: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3039: aload_1
    //   3040: invokevirtual 861	java/lang/Exception:toString	()Ljava/lang/String;
    //   3043: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3046: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3049: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3052: goto -880 -> 2172
    //   3055: astore_1
    //   3056: getstatic 668	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3059: ifeq -849 -> 2210
    //   3062: aload_1
    //   3063: invokevirtual 671	org/json/JSONException:printStackTrace	()V
    //   3066: goto -856 -> 2210
    //   3069: astore_3
    //   3070: new 422	java/lang/StringBuilder
    //   3073: dup
    //   3074: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   3077: ldc_w 1400
    //   3080: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3083: aload 7
    //   3085: getfield 1332	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3088: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3091: ldc_w 1402
    //   3094: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3097: aload_3
    //   3098: invokevirtual 1390	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3101: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3104: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3107: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3110: goto -806 -> 2304
    //   3113: astore_1
    //   3114: goto -2566 -> 548
    //   3117: astore_1
    //   3118: goto -2617 -> 501
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3121	0	this	Feature
    //   0	3121	1	paramContext	Context
    //   0	3121	2	paramBoolean	boolean
    //   0	3121	3	paramHashMap	HashMap<String, Object>
    //   1185	1623	4	i	int
    //   618	1673	5	localObject1	Object
    //   2894	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   585	1682	6	localObject2	Object
    //   591	2493	7	localObject3	Object
    //   601	1895	8	localObject4	Object
    //   588	463	9	localIterator	Iterator
    //   1060	1	9	localException	Exception
    //   1107	1622	9	localObject5	Object
    //   598	675	10	localObject6	Object
    //   604	659	11	localObject7	Object
    //   610	785	12	localObject8	Object
    //   582	2199	13	localObject9	Object
    //   607	253	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   963	1001	1060	java/lang/Exception
    //   1001	1057	1060	java/lang/Exception
    //   2210	2256	2376	java/lang/Exception
    //   2256	2284	2376	java/lang/Exception
    //   2290	2302	2376	java/lang/Exception
    //   2308	2373	2376	java/lang/Exception
    //   3070	3110	2376	java/lang/Exception
    //   386	409	2415	java/lang/Exception
    //   1306	1393	2737	org/json/JSONException
    //   1393	1608	2737	org/json/JSONException
    //   2618	2687	2737	org/json/JSONException
    //   2692	2734	2737	org/json/JSONException
    //   2767	2791	2737	org/json/JSONException
    //   2794	2804	2737	org/json/JSONException
    //   1639	1658	2805	android/content/pm/PackageManager$NameNotFoundException
    //   1820	1839	2834	java/lang/Exception
    //   1839	1861	2864	java/lang/Exception
    //   1913	1926	2894	android/content/pm/PackageManager$NameNotFoundException
    //   1902	1913	2928	java/lang/Exception
    //   1913	1926	2928	java/lang/Exception
    //   1930	1936	2928	java/lang/Exception
    //   1936	1973	2928	java/lang/Exception
    //   2898	2925	2928	java/lang/Exception
    //   1973	2042	2965	java/lang/Exception
    //   2042	2093	2995	java/lang/Exception
    //   2093	2172	3025	java/lang/Exception
    //   2196	2210	3055	org/json/JSONException
    //   2290	2302	3069	android/content/pm/PackageManager$NameNotFoundException
    //   523	548	3113	java/lang/Exception
    //   487	501	3117	java/lang/Exception
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
            break label708;
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
      label708:
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
    //   0: getstatic 555	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +261 -> 266
    //   8: getstatic 120	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +255 -> 266
    //   14: getstatic 164	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +10 -> 27
    //   20: ldc_w 557
    //   23: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   26: return
    //   27: ldc_w 1654
    //   30: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   33: getstatic 156	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   36: ifne +46 -> 82
    //   39: getstatic 561	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   42: ifnonnull +189 -> 231
    //   45: ldc_w 1656
    //   48: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   51: new 568	java/util/Timer
    //   54: dup
    //   55: invokespecial 1093	java/util/Timer:<init>	()V
    //   58: putstatic 561	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   61: getstatic 561	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   64: new 1658	com/kochava/android/tracker/Feature$7
    //   67: dup
    //   68: invokespecial 1659	com/kochava/android/tracker/Feature$7:<init>	()V
    //   71: getstatic 123	com/kochava/android/tracker/Feature:flush_rate	I
    //   74: i2l
    //   75: getstatic 123	com/kochava/android/tracker/Feature:flush_rate	I
    //   78: i2l
    //   79: invokevirtual 1432	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   82: invokestatic 428	java/lang/System:currentTimeMillis	()J
    //   85: ldc2_w 429
    //   88: ldiv
    //   89: putstatic 145	com/kochava/android/tracker/Feature:startTime	J
    //   92: getstatic 141	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   95: ifeq +18 -> 113
    //   98: invokestatic 286	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   101: ifeq +12 -> 113
    //   104: getstatic 452	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   107: invokestatic 1663	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   110: invokevirtual 1666	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   113: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   116: ifnull +49 -> 165
    //   119: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   122: ldc_w 1668
    //   125: ldc 114
    //   127: invokeinterface 468 3 0
    //   132: ldc 114
    //   134: invokevirtual 474	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 442	org/json/JSONObject
    //   145: dup
    //   146: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc_w 1668
    //   152: ldc 114
    //   154: invokeinterface 468 3 0
    //   159: invokespecial 1669	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1671	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +98 -> 266
    //   171: getstatic 290	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc_w 1321
    //   177: ldc 114
    //   179: invokeinterface 468 3 0
    //   184: ldc_w 1201
    //   187: invokevirtual 474	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifeq +76 -> 266
    //   193: getstatic 166	com/kochava/android/tracker/Feature:canSendSession	Z
    //   196: ifeq +64 -> 260
    //   199: ldc_w 1673
    //   202: invokestatic 576	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   205: return
    //   206: astore_1
    //   207: new 422	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 423	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 1675
    //   217: invokevirtual 437	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 487	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   230: return
    //   231: ldc_w 1677
    //   234: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   237: goto -155 -> 82
    //   240: astore_1
    //   241: ldc_w 1679
    //   244: invokestatic 492	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   247: getstatic 668	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   250: ifeq -85 -> 165
    //   253: aload_1
    //   254: invokevirtual 671	org/json/JSONException:printStackTrace	()V
    //   257: goto -92 -> 165
    //   260: ldc_w 582
    //   263: invokestatic 550	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   266: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   137	2	0	bool	boolean
    //   206	15	1	localException	Exception
    //   240	14	1	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   0	26	206	java/lang/Exception
    //   27	82	206	java/lang/Exception
    //   82	113	206	java/lang/Exception
    //   113	138	206	java/lang/Exception
    //   142	165	206	java/lang/Exception
    //   165	205	206	java/lang/Exception
    //   231	237	206	java/lang/Exception
    //   241	257	206	java/lang/Exception
    //   260	266	206	java/lang/Exception
    //   142	165	240	org/json/JSONException
  }
  
  protected void tryUpdate(String paramString)
  {
    paramString = new Feature.5(this, paramString);
    worker.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
}
