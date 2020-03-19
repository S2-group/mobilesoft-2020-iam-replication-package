package com.kochava.android.tracker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
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
import java.net.HttpURLConnection;
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
import java.util.TimerTask;
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
  private static List<ApplicationInfoHolder> installedApps;
  private static boolean is_in_background;
  private static DbAdapter kDbAdapter;
  private static final HashMap<Integer, Integer> kvinitRetrySteps;
  private static long lastCallTime;
  private static long lastEventTimestamp;
  protected static Handler locationHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (Feature.badInit) {
        Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Cannot send location udpate");
      }
      do
      {
        for (;;)
        {
          return;
          Logging.Log("Location Handler got message, queueing location update.");
          try
          {
            paramAnonymousMessage = new JSONObject();
            JSONObject localJSONObject1 = new JSONObject();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("lat", Feature.prefs.getString("kochava_lat", ""));
            localJSONObject2.put("lon", Feature.prefs.getString("kochava_lon", ""));
            localJSONObject2.put("acc", Feature.prefs.getString("kochava_accuracy", ""));
            localJSONObject1.put("location", localJSONObject2);
            paramAnonymousMessage.put("action", "update");
            paramAnonymousMessage.put("kochava_device_id", Feature.access$3400());
            paramAnonymousMessage.put("kochava_app_id", Feature.mAppId);
            paramAnonymousMessage.put("sdk_version", "Android20160615" + Feature.versionExtension);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            Logging.Log("Location update: " + paramAnonymousMessage);
            int i = Feature.kDbAdapter.addEvent(paramAnonymousMessage, false, true);
            Feature.access$5502(System.currentTimeMillis());
            long l = Feature.prefs.getLong("kochava_loc_timestamp", 0L);
            Feature.prefs.edit().putLong("kochava_old_loc_timestamp", l).apply();
            if (i >= 50)
            {
              Feature.flush();
              return;
            }
          }
          catch (JSONException paramAnonymousMessage) {}
        }
      } while (!Global.DEBUGERROR);
      paramAnonymousMessage.printStackTrace();
    }
  };
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
  private Runnable getKVinit = new Runnable()
  {
    /* Error */
    @SuppressLint({"NewApi"})
    public void run()
    {
      // Byte code:
      //   0: ldc 34
      //   2: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   5: invokestatic 45	android/os/Looper:prepare	()V
      //   8: lconst_0
      //   9: lstore 6
      //   11: iconst_0
      //   12: istore_3
      //   13: iconst_0
      //   14: istore_2
      //   15: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   18: lstore 8
      //   20: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   23: ldc 57
      //   25: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   28: invokeinterface 63 4 0
      //   33: lstore 10
      //   35: lload 8
      //   37: lload 10
      //   39: lsub
      //   40: lstore 6
      //   42: iload_3
      //   43: istore_1
      //   44: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   47: ldc 65
      //   49: invokeinterface 69 2 0
      //   54: ifeq +65 -> 119
      //   57: iload_3
      //   58: istore_1
      //   59: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   62: ldc 65
      //   64: ldc 71
      //   66: invokeinterface 75 3 0
      //   71: aload_0
      //   72: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   75: invokestatic 79	com/kochava/android/tracker/Feature:access$500	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   78: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   81: ifne +38 -> 119
      //   84: new 87	java/lang/StringBuilder
      //   87: dup
      //   88: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   91: ldc 90
      //   93: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: aload_0
      //   97: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   100: invokestatic 79	com/kochava/android/tracker/Feature:access$500	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   103: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: ldc 96
      //   108: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   114: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   117: iconst_1
      //   118: istore_1
      //   119: iload_1
      //   120: ifne +29 -> 149
      //   123: lload 6
      //   125: lconst_0
      //   126: lcmp
      //   127: ifle +22 -> 149
      //   130: lload 6
      //   132: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   135: ldc 102
      //   137: ldc2_w 103
      //   140: invokeinterface 63 4 0
      //   145: lcmp
      //   146: ifle +3897 -> 4043
      //   149: aconst_null
      //   150: astore 13
      //   152: iload_2
      //   153: istore_1
      //   154: new 87	java/lang/StringBuilder
      //   157: dup
      //   158: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   161: ldc 106
      //   163: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   166: aload_0
      //   167: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   170: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   173: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   176: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   179: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   182: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   185: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   188: ifnull +15 -> 203
      //   191: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   194: invokevirtual 119	java/lang/String:trim	()Ljava/lang/String;
      //   197: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   200: ifeq +14 -> 214
      //   203: ldc 125
      //   205: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   208: ldc 127
      //   210: invokestatic 131	com/kochava/android/tracker/Feature:access$602	(Ljava/lang/String;)Ljava/lang/String;
      //   213: pop
      //   214: new 87	java/lang/StringBuilder
      //   217: dup
      //   218: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   221: ldc -123
      //   223: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   226: ldc -121
      //   228: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   231: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   234: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   237: ldc -119
      //   239: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   242: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   245: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   248: new 139	java/net/URL
      //   251: dup
      //   252: new 87	java/lang/StringBuilder
      //   255: dup
      //   256: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   259: ldc -121
      //   261: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   264: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   267: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   270: ldc -119
      //   272: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   275: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   278: invokespecial 141	java/net/URL:<init>	(Ljava/lang/String;)V
      //   281: invokevirtual 145	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   284: checkcast 147	javax/net/ssl/HttpsURLConnection
      //   287: astore 14
      //   289: aload 14
      //   291: ldc -107
      //   293: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   296: ldc -105
      //   298: ldc 71
      //   300: invokeinterface 75 3 0
      //   305: invokevirtual 155	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   308: aload 14
      //   310: ldc -99
      //   312: ldc -97
      //   314: invokevirtual 155	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   317: aload 14
      //   319: ldc -95
      //   321: invokevirtual 164	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   324: aload 14
      //   326: sipush 30000
      //   329: invokevirtual 168	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   332: aload 14
      //   334: sipush 30000
      //   337: invokevirtual 171	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   340: aload 14
      //   342: iconst_1
      //   343: invokevirtual 175	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   346: aload 14
      //   348: iconst_1
      //   349: invokevirtual 178	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   352: aload 14
      //   354: invokevirtual 181	javax/net/ssl/HttpsURLConnection:connect	()V
      //   357: aload_0
      //   358: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   361: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   364: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   367: astore 15
      //   369: new 87	java/lang/StringBuilder
      //   372: dup
      //   373: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   376: ldc -73
      //   378: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   381: aload 15
      //   383: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   386: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   389: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   392: ldc -71
      //   394: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   397: new 187	java/io/OutputStreamWriter
      //   400: dup
      //   401: aload 14
      //   403: invokevirtual 191	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   406: invokespecial 194	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   409: astore 16
      //   411: aload 16
      //   413: aload 15
      //   415: invokevirtual 197	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   418: aload 16
      //   420: invokevirtual 200	java/io/OutputStreamWriter:close	()V
      //   423: ldc -54
      //   425: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   428: new 204	java/lang/StringBuffer
      //   431: dup
      //   432: ldc 71
      //   434: invokespecial 205	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   437: astore 15
      //   439: new 207	java/io/BufferedReader
      //   442: dup
      //   443: new 209	java/io/InputStreamReader
      //   446: dup
      //   447: aload 14
      //   449: invokevirtual 213	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   452: invokespecial 216	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   455: invokespecial 219	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   458: astore 14
      //   460: aload 14
      //   462: invokevirtual 222	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   465: astore 16
      //   467: aload 16
      //   469: ifnull +89 -> 558
      //   472: aload 15
      //   474: aload 16
      //   476: invokevirtual 225	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   479: pop
      //   480: goto -20 -> 460
      //   483: astore 13
      //   485: ldc -29
      //   487: aload 13
      //   489: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   492: invokevirtual 237	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   495: ifeq +3523 -> 4018
      //   498: new 87	java/lang/StringBuilder
      //   501: dup
      //   502: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   505: ldc -17
      //   507: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   510: aload 13
      //   512: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   515: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   518: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   521: aload 13
      //   523: invokestatic 249	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
      //   526: return
      //   527: astore 13
      //   529: new 87	java/lang/StringBuilder
      //   532: dup
      //   533: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   536: ldc -5
      //   538: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   541: aload 13
      //   543: invokevirtual 252	java/lang/Exception:toString	()Ljava/lang/String;
      //   546: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   549: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   552: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   555: goto -513 -> 42
      //   558: aload 15
      //   560: invokevirtual 253	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   563: astore 14
      //   565: new 87	java/lang/StringBuilder
      //   568: dup
      //   569: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   572: ldc -1
      //   574: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   577: aload 14
      //   579: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   582: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   585: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   588: new 112	org/json/JSONObject
      //   591: dup
      //   592: aload 14
      //   594: invokespecial 256	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   597: astore 14
      //   599: aload 14
      //   601: astore 13
      //   603: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   606: ldc_w 258
      //   609: ldc 71
      //   611: invokeinterface 75 3 0
      //   616: ldc_w 260
      //   619: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   622: istore 12
      //   624: iload_1
      //   625: istore_2
      //   626: iload 12
      //   628: ifne +134 -> 762
      //   631: iload_1
      //   632: istore_2
      //   633: aload 13
      //   635: ifnull +127 -> 762
      //   638: aload 13
      //   640: ldc_w 262
      //   643: invokevirtual 266	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   646: astore 14
      //   648: iconst_0
      //   649: istore 5
      //   651: iconst_0
      //   652: istore 4
      //   654: iload_1
      //   655: istore_2
      //   656: iload 5
      //   658: istore_3
      //   659: iload 4
      //   661: aload 14
      //   663: invokevirtual 272	org/json/JSONArray:length	()I
      //   666: if_icmpge +90 -> 756
      //   669: ldc_w 274
      //   672: aload 14
      //   674: iload 4
      //   676: invokevirtual 277	org/json/JSONArray:getString	(I)Ljava/lang/String;
      //   679: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   682: ifeq +1044 -> 1726
      //   685: iconst_1
      //   686: istore_3
      //   687: iload_1
      //   688: istore_2
      //   689: iload_1
      //   690: iconst_4
      //   691: if_icmpge +7 -> 698
      //   694: iload_1
      //   695: iconst_1
      //   696: iadd
      //   697: istore_2
      //   698: new 87	java/lang/StringBuilder
      //   701: dup
      //   702: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   705: ldc_w 279
      //   708: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   711: invokestatic 283	com/kochava/android/tracker/Feature:access$700	()Ljava/util/HashMap;
      //   714: iload_2
      //   715: invokestatic 289	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   718: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   721: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   724: ldc_w 297
      //   727: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   730: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   733: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   736: invokestatic 283	com/kochava/android/tracker/Feature:access$700	()Ljava/util/HashMap;
      //   739: iload_2
      //   740: invokestatic 289	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   743: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   746: checkcast 285	java/lang/Integer
      //   749: invokevirtual 300	java/lang/Integer:intValue	()I
      //   752: i2l
      //   753: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   756: iload_3
      //   757: ifne +5 -> 762
      //   760: iconst_0
      //   761: istore_2
      //   762: iload_2
      //   763: ifne +3538 -> 4301
      //   766: aload 13
      //   768: ifnull +2881 -> 3649
      //   771: new 87	java/lang/StringBuilder
      //   774: dup
      //   775: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   778: ldc_w 308
      //   781: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   784: aload 13
      //   786: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   789: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   792: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   795: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   798: aconst_null
      //   799: astore 14
      //   801: aload 13
      //   803: ldc_w 310
      //   806: invokevirtual 314	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   809: astore 15
      //   811: aload 15
      //   813: astore 14
      //   815: new 87	java/lang/StringBuilder
      //   818: dup
      //   819: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   822: ldc_w 316
      //   825: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   828: aload 15
      //   830: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   833: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   836: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   839: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   842: aload 15
      //   844: astore 14
      //   846: aload 14
      //   848: ifnull +750 -> 1598
      //   851: aload 14
      //   853: ldc_w 318
      //   856: invokevirtual 320	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   859: astore 15
      //   861: new 87	java/lang/StringBuilder
      //   864: dup
      //   865: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   868: ldc_w 322
      //   871: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   874: aload 15
      //   876: invokevirtual 323	java/lang/String:toString	()Ljava/lang/String;
      //   879: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   882: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   885: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   888: aload 15
      //   890: invokestatic 326	com/kochava/android/tracker/Feature:access$802	(Ljava/lang/String;)Ljava/lang/String;
      //   893: pop
      //   894: aload 14
      //   896: ldc_w 328
      //   899: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   902: ldc_w 333
      //   905: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   908: ifeq +8 -> 916
      //   911: iconst_0
      //   912: invokestatic 338	com/kochava/android/tracker/Feature:access$902	(Z)Z
      //   915: pop
      //   916: aload 14
      //   918: ldc_w 340
      //   921: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   924: checkcast 81	java/lang/String
      //   927: invokevirtual 343	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   930: astore 15
      //   932: new 87	java/lang/StringBuilder
      //   935: dup
      //   936: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   939: ldc_w 345
      //   942: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   945: aload 15
      //   947: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   950: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   953: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   956: aload_0
      //   957: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   960: aload 15
      //   962: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;)V
      //   965: aload 14
      //   967: ldc_w 351
      //   970: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   973: ldc_w 260
      //   976: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   979: ifeq +47 -> 1026
      //   982: ldc_w 353
      //   985: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   988: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   991: ldc_w 359
      //   994: iconst_0
      //   995: invokevirtual 365	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   998: invokestatic 369	com/kochava/android/tracker/Feature:access$402	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   1001: pop
      //   1002: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1005: invokeinterface 373 1 0
      //   1010: ldc_w 258
      //   1013: ldc_w 375
      //   1016: invokeinterface 381 3 0
      //   1021: invokeinterface 384 1 0
      //   1026: aload 14
      //   1028: ldc_w 386
      //   1031: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1034: checkcast 285	java/lang/Integer
      //   1037: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1040: invokestatic 390	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1043: pop
      //   1044: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   1047: ifge +777 -> 1824
      //   1050: new 87	java/lang/StringBuilder
      //   1053: dup
      //   1054: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1057: ldc_w 395
      //   1060: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1063: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   1066: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1069: ldc_w 400
      //   1072: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1075: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1078: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1081: iconst_0
      //   1082: invokestatic 390	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1085: pop
      //   1086: invokestatic 403	com/kochava/android/tracker/Feature:access$1200	()Z
      //   1089: ifne +89 -> 1178
      //   1092: aload 14
      //   1094: ldc_w 405
      //   1097: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1100: ifnull +78 -> 1178
      //   1103: aload 14
      //   1105: ldc_w 405
      //   1108: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1111: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1114: ldc_w 285
      //   1117: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1120: ifeq +58 -> 1178
      //   1123: aload 14
      //   1125: ldc_w 405
      //   1128: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1131: checkcast 285	java/lang/Integer
      //   1134: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1137: istore_1
      //   1138: iload_1
      //   1139: bipush 60
      //   1141: if_icmpge +759 -> 1900
      //   1144: new 87	java/lang/StringBuilder
      //   1147: dup
      //   1148: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1151: ldc_w 407
      //   1154: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1157: iload_1
      //   1158: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1161: ldc_w 409
      //   1164: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1167: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1170: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1173: iconst_1
      //   1174: invokestatic 412	com/kochava/android/tracker/Feature:access$1402	(Z)Z
      //   1177: pop
      //   1178: aload 14
      //   1180: ldc 102
      //   1182: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1185: ifnull +92 -> 1277
      //   1188: aload 14
      //   1190: ldc 102
      //   1192: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1195: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1198: ldc_w 285
      //   1201: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1204: ifeq +73 -> 1277
      //   1207: aload 14
      //   1209: ldc 102
      //   1211: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1214: checkcast 285	java/lang/Integer
      //   1217: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1220: istore_1
      //   1221: iload_1
      //   1222: ifge +765 -> 1987
      //   1225: new 87	java/lang/StringBuilder
      //   1228: dup
      //   1229: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1232: ldc_w 414
      //   1235: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1238: iload_1
      //   1239: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1242: ldc_w 416
      //   1245: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1248: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1251: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1254: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1257: invokeinterface 373 1 0
      //   1262: ldc 102
      //   1264: ldc2_w 103
      //   1267: invokeinterface 420 4 0
      //   1272: invokeinterface 384 1 0
      //   1277: aload 14
      //   1279: ldc_w 422
      //   1282: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1285: ifnull +77 -> 1362
      //   1288: aload 14
      //   1290: ldc_w 422
      //   1293: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1296: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1299: ldc_w 285
      //   1302: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1305: ifeq +57 -> 1362
      //   1308: aload 14
      //   1310: ldc_w 422
      //   1313: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1316: checkcast 285	java/lang/Integer
      //   1319: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1322: istore_1
      //   1323: iload_1
      //   1324: iconst_1
      //   1325: if_icmpge +782 -> 2107
      //   1328: new 87	java/lang/StringBuilder
      //   1331: dup
      //   1332: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1335: ldc_w 424
      //   1338: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1341: iload_1
      //   1342: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1345: ldc_w 426
      //   1348: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1351: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1354: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1357: iconst_1
      //   1358: invokestatic 429	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   1361: pop
      //   1362: aload 14
      //   1364: ldc_w 431
      //   1367: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1370: checkcast 285	java/lang/Integer
      //   1373: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1376: istore_2
      //   1377: iload_2
      //   1378: bipush 10
      //   1380: if_icmpge +808 -> 2188
      //   1383: new 87	java/lang/StringBuilder
      //   1386: dup
      //   1387: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1390: ldc_w 433
      //   1393: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1396: iload_2
      //   1397: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1400: ldc_w 435
      //   1403: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1406: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1409: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1412: bipush 10
      //   1414: istore_1
      //   1415: new 87	java/lang/StringBuilder
      //   1418: dup
      //   1419: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1422: ldc_w 437
      //   1425: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1428: iload_1
      //   1429: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1432: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1435: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1438: iload_1
      //   1439: putstatic 443	com/kochava/android/tracker/LocationDirector:desiredAccuracy	I
      //   1442: aload 14
      //   1444: ldc_w 445
      //   1447: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1450: checkcast 285	java/lang/Integer
      //   1453: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1456: istore_2
      //   1457: iload_2
      //   1458: iconst_3
      //   1459: if_icmpge +774 -> 2233
      //   1462: new 87	java/lang/StringBuilder
      //   1465: dup
      //   1466: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1469: ldc_w 447
      //   1472: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1475: iload_2
      //   1476: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1479: ldc_w 449
      //   1482: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1485: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1488: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1491: iconst_3
      //   1492: istore_1
      //   1493: new 87	java/lang/StringBuilder
      //   1496: dup
      //   1497: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1500: ldc_w 451
      //   1503: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1506: iload_1
      //   1507: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1510: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1513: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1516: iload_1
      //   1517: putstatic 454	com/kochava/android/tracker/LocationDirector:timeout	I
      //   1520: aload 14
      //   1522: ldc_w 456
      //   1525: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1528: checkcast 285	java/lang/Integer
      //   1531: invokevirtual 300	java/lang/Integer:intValue	()I
      //   1534: istore_2
      //   1535: iload_2
      //   1536: iconst_1
      //   1537: if_icmpge +739 -> 2276
      //   1540: new 87	java/lang/StringBuilder
      //   1543: dup
      //   1544: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1547: ldc_w 458
      //   1550: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1553: iload_2
      //   1554: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1557: ldc_w 460
      //   1560: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1563: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1566: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1569: iconst_1
      //   1570: istore_1
      //   1571: new 87	java/lang/StringBuilder
      //   1574: dup
      //   1575: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1578: ldc_w 462
      //   1581: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1584: iload_1
      //   1585: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1588: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1591: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1594: iload_1
      //   1595: putstatic 465	com/kochava/android/tracker/LocationDirector:staleness	I
      //   1598: aload 13
      //   1600: ldc_w 467
      //   1603: invokevirtual 266	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1606: astore 15
      //   1608: new 87	java/lang/StringBuilder
      //   1611: dup
      //   1612: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1615: ldc_w 469
      //   1618: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1621: aload 15
      //   1623: invokevirtual 470	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1626: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1629: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1632: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1635: iconst_0
      //   1636: istore_1
      //   1637: iload_1
      //   1638: aload 15
      //   1640: invokevirtual 272	org/json/JSONArray:length	()I
      //   1643: if_icmpge +741 -> 2384
      //   1646: aload 15
      //   1648: iload_1
      //   1649: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1652: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   1655: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1658: ldc_w 479
      //   1661: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1664: ifeq +657 -> 2321
      //   1667: ldc_w 481
      //   1670: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1673: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   1676: ldc_w 479
      //   1679: iconst_0
      //   1680: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1683: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1686: pop
      //   1687: iload_1
      //   1688: iconst_1
      //   1689: iadd
      //   1690: istore_1
      //   1691: goto -54 -> 1637
      //   1694: astore 14
      //   1696: new 87	java/lang/StringBuilder
      //   1699: dup
      //   1700: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1703: ldc_w 495
      //   1706: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1709: aload 14
      //   1711: invokevirtual 496	org/json/JSONException:toString	()Ljava/lang/String;
      //   1714: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1717: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1720: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1723: goto -1120 -> 603
      //   1726: iload 4
      //   1728: iconst_1
      //   1729: iadd
      //   1730: istore 4
      //   1732: goto -1078 -> 654
      //   1735: astore 14
      //   1737: iconst_0
      //   1738: istore_2
      //   1739: goto -977 -> 762
      //   1742: astore 15
      //   1744: ldc_w 498
      //   1747: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1750: goto -904 -> 846
      //   1753: astore 13
      //   1755: aload 13
      //   1757: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1760: ldc -29
      //   1762: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1765: ifeq +2199 -> 3964
      //   1768: new 87	java/lang/StringBuilder
      //   1771: dup
      //   1772: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1775: ldc -17
      //   1777: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1780: aload 13
      //   1782: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1785: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1788: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1791: aload 13
      //   1793: invokestatic 249	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
      //   1796: return
      //   1797: astore 13
      //   1799: goto -1314 -> 485
      //   1802: astore 15
      //   1804: ldc_w 500
      //   1807: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1810: goto -916 -> 894
      //   1813: astore 15
      //   1815: ldc_w 498
      //   1818: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1821: goto -905 -> 916
      //   1824: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   1827: bipush 120
      //   1829: if_icmple +43 -> 1872
      //   1832: new 87	java/lang/StringBuilder
      //   1835: dup
      //   1836: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1839: ldc_w 502
      //   1842: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1845: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   1848: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1851: ldc_w 504
      //   1854: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1857: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1860: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1863: bipush 120
      //   1865: invokestatic 390	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1868: pop
      //   1869: goto -783 -> 1086
      //   1872: new 87	java/lang/StringBuilder
      //   1875: dup
      //   1876: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1879: ldc_w 506
      //   1882: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1885: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   1888: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1891: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1894: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1897: goto -811 -> 1086
      //   1900: iload_1
      //   1901: ldc_w 507
      //   1904: if_icmple +42 -> 1946
      //   1907: new 87	java/lang/StringBuilder
      //   1910: dup
      //   1911: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1914: ldc_w 509
      //   1917: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1920: iload_1
      //   1921: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1924: ldc_w 511
      //   1927: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1930: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1933: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1936: ldc_w 512
      //   1939: invokestatic 515	com/kochava/android/tracker/Feature:access$1302	(I)I
      //   1942: pop
      //   1943: goto -770 -> 1173
      //   1946: iload_1
      //   1947: sipush 1000
      //   1950: imul
      //   1951: invokestatic 515	com/kochava/android/tracker/Feature:access$1302	(I)I
      //   1954: pop
      //   1955: new 87	java/lang/StringBuilder
      //   1958: dup
      //   1959: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1962: ldc_w 517
      //   1965: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1968: iload_1
      //   1969: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1972: ldc_w 519
      //   1975: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1978: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1981: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1984: goto -811 -> 1173
      //   1987: iload_1
      //   1988: ldc_w 507
      //   1991: if_icmple +58 -> 2049
      //   1994: new 87	java/lang/StringBuilder
      //   1997: dup
      //   1998: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2001: ldc_w 414
      //   2004: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2007: iload_1
      //   2008: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2011: ldc_w 521
      //   2014: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2017: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2020: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2023: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2026: invokeinterface 373 1 0
      //   2031: ldc 102
      //   2033: ldc2_w 522
      //   2036: invokeinterface 420 4 0
      //   2041: invokeinterface 384 1 0
      //   2046: goto -769 -> 1277
      //   2049: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2052: invokeinterface 373 1 0
      //   2057: ldc 102
      //   2059: iload_1
      //   2060: sipush 1000
      //   2063: imul
      //   2064: i2l
      //   2065: invokeinterface 420 4 0
      //   2070: invokeinterface 384 1 0
      //   2075: new 87	java/lang/StringBuilder
      //   2078: dup
      //   2079: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2082: ldc_w 525
      //   2085: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2088: iload_1
      //   2089: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2092: ldc_w 519
      //   2095: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2098: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2101: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2104: goto -827 -> 1277
      //   2107: iload_1
      //   2108: bipush 30
      //   2110: if_icmple +41 -> 2151
      //   2113: new 87	java/lang/StringBuilder
      //   2116: dup
      //   2117: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2120: ldc_w 424
      //   2123: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2126: iload_1
      //   2127: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2130: ldc_w 527
      //   2133: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2136: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2139: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2142: bipush 30
      //   2144: invokestatic 429	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   2147: pop
      //   2148: goto -786 -> 1362
      //   2151: new 87	java/lang/StringBuilder
      //   2154: dup
      //   2155: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2158: ldc_w 529
      //   2161: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2164: iload_1
      //   2165: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2168: ldc_w 519
      //   2171: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2174: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2177: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2180: iload_1
      //   2181: invokestatic 429	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   2184: pop
      //   2185: goto -823 -> 1362
      //   2188: iload_2
      //   2189: istore_1
      //   2190: iload_2
      //   2191: sipush 5000
      //   2194: if_icmple -779 -> 1415
      //   2197: new 87	java/lang/StringBuilder
      //   2200: dup
      //   2201: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2204: ldc_w 433
      //   2207: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2210: iload_2
      //   2211: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2214: ldc_w 435
      //   2217: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2220: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2223: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2226: sipush 5000
      //   2229: istore_1
      //   2230: goto -815 -> 1415
      //   2233: iload_2
      //   2234: istore_1
      //   2235: iload_2
      //   2236: bipush 60
      //   2238: if_icmple -745 -> 1493
      //   2241: new 87	java/lang/StringBuilder
      //   2244: dup
      //   2245: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2248: ldc_w 447
      //   2251: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2254: iload_2
      //   2255: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2258: ldc_w 449
      //   2261: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2264: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2267: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2270: bipush 60
      //   2272: istore_1
      //   2273: goto -780 -> 1493
      //   2276: iload_2
      //   2277: istore_1
      //   2278: iload_2
      //   2279: sipush 10080
      //   2282: if_icmple -711 -> 1571
      //   2285: new 87	java/lang/StringBuilder
      //   2288: dup
      //   2289: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2292: ldc_w 458
      //   2295: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2298: iload_2
      //   2299: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2302: ldc_w 460
      //   2305: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2308: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2311: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2314: sipush 10080
      //   2317: istore_1
      //   2318: goto -747 -> 1571
      //   2321: aload 15
      //   2323: iload_1
      //   2324: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2327: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   2330: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2333: ldc_w 531
      //   2336: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2339: ifeq +536 -> 2875
      //   2342: ldc_w 533
      //   2345: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2348: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2351: ldc_w 531
      //   2354: iconst_0
      //   2355: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2358: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2361: pop
      //   2362: goto -675 -> 1687
      //   2365: astore 15
      //   2367: ldc_w 535
      //   2370: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2373: getstatic 541	com/kochava/android/tracker/Global:DEBUGERROR	Z
      //   2376: ifeq +8 -> 2384
      //   2379: aload 15
      //   2381: invokevirtual 544	java/lang/Exception:printStackTrace	()V
      //   2384: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2387: ldc_w 546
      //   2390: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2393: checkcast 486	java/lang/Boolean
      //   2396: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   2399: istore 12
      //   2401: iload 12
      //   2403: ifeq +768 -> 3171
      //   2406: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2409: ldc_w 551
      //   2412: invokevirtual 554	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2415: checkcast 556	android/telephony/TelephonyManager
      //   2418: invokevirtual 559	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
      //   2421: invokestatic 562	com/kochava/android/tracker/Feature:access$1702	(Ljava/lang/String;)Ljava/lang/String;
      //   2424: pop
      //   2425: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2428: ldc_w 564
      //   2431: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2434: checkcast 486	java/lang/Boolean
      //   2437: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   2440: istore 12
      //   2442: iload 12
      //   2444: ifeq +768 -> 3212
      //   2447: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2450: ldc_w 566
      //   2453: invokevirtual 554	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2456: checkcast 568	android/net/wifi/WifiManager
      //   2459: invokevirtual 572	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
      //   2462: invokevirtual 577	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
      //   2465: invokestatic 580	com/kochava/android/tracker/Feature:access$1802	(Ljava/lang/String;)Ljava/lang/String;
      //   2468: pop
      //   2469: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2472: ldc_w 582
      //   2475: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2478: checkcast 486	java/lang/Boolean
      //   2481: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   2484: istore 12
      //   2486: iload 12
      //   2488: ifeq +779 -> 3267
      //   2491: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2494: ldc_w 258
      //   2497: ldc 71
      //   2499: invokeinterface 75 3 0
      //   2504: ldc_w 260
      //   2507: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2510: ifne +161 -> 2671
      //   2513: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2516: invokevirtual 586	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   2519: astore 17
      //   2521: aload 17
      //   2523: sipush 128
      //   2526: invokevirtual 592	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2529: invokeinterface 598 1 0
      //   2534: astore 18
      //   2536: aload 18
      //   2538: invokeinterface 603 1 0
      //   2543: ifeq +128 -> 2671
      //   2546: aload 18
      //   2548: invokeinterface 607 1 0
      //   2553: checkcast 609	android/content/pm/ApplicationInfo
      //   2556: astore 19
      //   2558: aload 19
      //   2560: invokestatic 613	com/kochava/android/tracker/Feature:access$1900	(Landroid/content/pm/ApplicationInfo;)Z
      //   2563: istore 12
      //   2565: iload 12
      //   2567: ifne -31 -> 2536
      //   2570: aconst_null
      //   2571: astore 15
      //   2573: aload 17
      //   2575: aload 19
      //   2577: getfield 617	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2580: iconst_0
      //   2581: invokevirtual 621	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   2584: astore 16
      //   2586: aload 16
      //   2588: astore 15
      //   2590: aload 15
      //   2592: ifnull -56 -> 2536
      //   2595: invokestatic 625	com/kochava/android/tracker/Feature:access$2000	()Ljava/util/List;
      //   2598: new 627	com/kochava/android/tracker/Feature$ApplicationInfoHolder
      //   2601: dup
      //   2602: aload_0
      //   2603: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2606: aload 15
      //   2608: getfield 630	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   2611: new 87	java/lang/StringBuilder
      //   2614: dup
      //   2615: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2618: aload 15
      //   2620: getfield 634	android/content/pm/PackageInfo:firstInstallTime	J
      //   2623: invokevirtual 637	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2626: ldc 71
      //   2628: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2631: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2634: new 87	java/lang/StringBuilder
      //   2637: dup
      //   2638: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2641: aload 15
      //   2643: getfield 640	android/content/pm/PackageInfo:lastUpdateTime	J
      //   2646: invokevirtual 637	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2649: ldc 71
      //   2651: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2654: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2657: invokespecial 643	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   2660: invokeinterface 646 2 0
      //   2665: pop
      //   2666: goto -130 -> 2536
      //   2669: astore 15
      //   2671: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2674: ldc_w 648
      //   2677: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2680: checkcast 486	java/lang/Boolean
      //   2683: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   2686: istore 12
      //   2688: iload 12
      //   2690: ifeq +618 -> 3308
      //   2693: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2696: ldc_w 650
      //   2699: invokevirtual 554	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2702: checkcast 652	android/view/WindowManager
      //   2705: astore 15
      //   2707: aload_0
      //   2708: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2711: aload 15
      //   2713: invokeinterface 656 1 0
      //   2718: invokevirtual 661	android/view/Display:getHeight	()I
      //   2721: invokestatic 665	com/kochava/android/tracker/Feature:access$2102	(Lcom/kochava/android/tracker/Feature;I)I
      //   2724: pop
      //   2725: aload_0
      //   2726: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2729: aload 15
      //   2731: invokeinterface 656 1 0
      //   2736: invokevirtual 668	android/view/Display:getWidth	()I
      //   2739: invokestatic 671	com/kochava/android/tracker/Feature:access$2202	(Lcom/kochava/android/tracker/Feature;I)I
      //   2742: pop
      //   2743: new 87	java/lang/StringBuilder
      //   2746: dup
      //   2747: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2750: ldc_w 673
      //   2753: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2756: aload_0
      //   2757: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2760: invokestatic 677	com/kochava/android/tracker/Feature:access$2100	(Lcom/kochava/android/tracker/Feature;)I
      //   2763: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2766: ldc_w 679
      //   2769: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2772: aload_0
      //   2773: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2776: invokestatic 682	com/kochava/android/tracker/Feature:access$2200	(Lcom/kochava/android/tracker/Feature;)I
      //   2779: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2782: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2785: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2788: aload 13
      //   2790: ldc_w 684
      //   2793: invokevirtual 266	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2796: astore 15
      //   2798: new 87	java/lang/StringBuilder
      //   2801: dup
      //   2802: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2805: ldc_w 686
      //   2808: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2811: aload 15
      //   2813: invokevirtual 470	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2816: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2819: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2822: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2825: iconst_0
      //   2826: istore_1
      //   2827: iload_1
      //   2828: aload 15
      //   2830: invokevirtual 272	org/json/JSONArray:length	()I
      //   2833: if_icmpge +548 -> 3381
      //   2836: aload 15
      //   2838: iload_1
      //   2839: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2842: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   2845: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2848: ldc_w 688
      //   2851: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2854: ifeq +463 -> 3317
      //   2857: ldc_w 690
      //   2860: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2863: iconst_1
      //   2864: invokestatic 693	com/kochava/android/tracker/Feature:access$2302	(Z)Z
      //   2867: pop
      //   2868: iload_1
      //   2869: iconst_1
      //   2870: iadd
      //   2871: istore_1
      //   2872: goto -45 -> 2827
      //   2875: aload 15
      //   2877: iload_1
      //   2878: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2881: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   2884: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2887: ldc_w 695
      //   2890: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2893: ifeq +26 -> 2919
      //   2896: ldc_w 697
      //   2899: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2902: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2905: ldc_w 695
      //   2908: iconst_0
      //   2909: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2912: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2915: pop
      //   2916: goto -1229 -> 1687
      //   2919: aload 15
      //   2921: iload_1
      //   2922: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2925: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   2928: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2931: ldc_w 564
      //   2934: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2937: ifeq +26 -> 2963
      //   2940: ldc_w 699
      //   2943: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2946: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2949: ldc_w 564
      //   2952: iconst_0
      //   2953: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2956: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2959: pop
      //   2960: goto -1273 -> 1687
      //   2963: aload 15
      //   2965: iload_1
      //   2966: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2969: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   2972: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2975: ldc_w 546
      //   2978: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2981: ifeq +26 -> 3007
      //   2984: ldc_w 701
      //   2987: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2990: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2993: ldc_w 546
      //   2996: iconst_0
      //   2997: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3000: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3003: pop
      //   3004: goto -1317 -> 1687
      //   3007: aload 15
      //   3009: iload_1
      //   3010: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3013: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   3016: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3019: ldc_w 648
      //   3022: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3025: ifeq +26 -> 3051
      //   3028: ldc_w 703
      //   3031: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3034: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3037: ldc_w 648
      //   3040: iconst_0
      //   3041: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3044: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3047: pop
      //   3048: goto -1361 -> 1687
      //   3051: aload 15
      //   3053: iload_1
      //   3054: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3057: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   3060: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3063: ldc_w 582
      //   3066: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3069: ifeq +26 -> 3095
      //   3072: ldc_w 705
      //   3075: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3078: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3081: ldc_w 582
      //   3084: iconst_0
      //   3085: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3088: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3091: pop
      //   3092: goto -1405 -> 1687
      //   3095: aload 15
      //   3097: iload_1
      //   3098: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3101: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   3104: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3107: ldc_w 707
      //   3110: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3113: ifeq -1426 -> 1687
      //   3116: ldc_w 709
      //   3119: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3122: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3125: ldc_w 707
      //   3128: iconst_0
      //   3129: invokestatic 489	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3132: invokevirtual 493	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3135: pop
      //   3136: goto -1449 -> 1687
      //   3139: astore 15
      //   3141: new 87	java/lang/StringBuilder
      //   3144: dup
      //   3145: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3148: ldc_w 711
      //   3151: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3154: aload 15
      //   3156: invokevirtual 252	java/lang/Exception:toString	()Ljava/lang/String;
      //   3159: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3162: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3165: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3168: goto -743 -> 2425
      //   3171: ldc_w 713
      //   3174: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3177: goto -752 -> 2425
      //   3180: astore 15
      //   3182: new 87	java/lang/StringBuilder
      //   3185: dup
      //   3186: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3189: ldc_w 715
      //   3192: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3195: aload 15
      //   3197: invokevirtual 252	java/lang/Exception:toString	()Ljava/lang/String;
      //   3200: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3203: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3206: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3209: goto -740 -> 2469
      //   3212: ldc_w 717
      //   3215: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3218: goto -749 -> 2469
      //   3221: astore 16
      //   3223: new 87	java/lang/StringBuilder
      //   3226: dup
      //   3227: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3230: ldc_w 719
      //   3233: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3236: aload 19
      //   3238: getfield 617	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   3241: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3244: ldc_w 721
      //   3247: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3250: aload 16
      //   3252: invokevirtual 722	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
      //   3255: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3258: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3261: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3264: goto -674 -> 2590
      //   3267: ldc_w 724
      //   3270: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3273: goto -602 -> 2671
      //   3276: astore 15
      //   3278: new 87	java/lang/StringBuilder
      //   3281: dup
      //   3282: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3285: ldc_w 726
      //   3288: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3291: aload 15
      //   3293: invokevirtual 252	java/lang/Exception:toString	()Ljava/lang/String;
      //   3296: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3299: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3302: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3305: goto -517 -> 2788
      //   3308: ldc_w 728
      //   3311: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3314: goto -526 -> 2788
      //   3317: aload 15
      //   3319: iload_1
      //   3320: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3323: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   3326: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3329: ldc_w 730
      //   3332: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3335: ifeq -467 -> 2868
      //   3338: ldc_w 732
      //   3341: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3344: iconst_1
      //   3345: invokestatic 735	com/kochava/android/tracker/Feature:access$2402	(Z)Z
      //   3348: pop
      //   3349: goto -481 -> 2868
      //   3352: astore 15
      //   3354: new 87	java/lang/StringBuilder
      //   3357: dup
      //   3358: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3361: ldc_w 737
      //   3364: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3367: aload 15
      //   3369: invokevirtual 252	java/lang/Exception:toString	()Ljava/lang/String;
      //   3372: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3375: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3378: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3381: aload 13
      //   3383: ldc_w 739
      //   3386: invokevirtual 314	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3389: ldc_w 741
      //   3392: invokevirtual 314	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3395: astore 15
      //   3397: aload_0
      //   3398: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3401: aload 15
      //   3403: invokestatic 745	com/kochava/android/tracker/Feature:access$2500	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   3406: invokestatic 748	com/kochava/android/tracker/Feature:access$2400	()Z
      //   3409: ifeq +18 -> 3427
      //   3412: invokestatic 751	com/kochava/android/tracker/Feature:access$2600	()Z
      //   3415: ifeq +12 -> 3427
      //   3418: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   3421: invokestatic 755	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   3424: invokevirtual 758	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   3427: aload 13
      //   3429: ldc_w 760
      //   3432: invokevirtual 266	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3435: invokestatic 764	com/kochava/android/tracker/Feature:access$2702	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3438: pop
      //   3439: new 87	java/lang/StringBuilder
      //   3442: dup
      //   3443: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3446: ldc_w 766
      //   3449: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3452: invokestatic 770	com/kochava/android/tracker/Feature:access$2700	()Lorg/json/JSONArray;
      //   3455: invokevirtual 470	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3458: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3461: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3464: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3467: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3470: invokeinterface 373 1 0
      //   3475: ldc_w 772
      //   3478: invokestatic 770	com/kochava/android/tracker/Feature:access$2700	()Lorg/json/JSONArray;
      //   3481: invokevirtual 470	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3484: invokeinterface 381 3 0
      //   3489: invokeinterface 384 1 0
      //   3494: aload 14
      //   3496: ldc_w 774
      //   3499: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3502: ifnull +88 -> 3590
      //   3505: aload 14
      //   3507: ldc_w 774
      //   3510: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3513: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3516: ldc_w 486
      //   3519: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3522: ifeq +432 -> 3954
      //   3525: aload 14
      //   3527: ldc_w 774
      //   3530: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3533: checkcast 486	java/lang/Boolean
      //   3536: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   3539: ifeq +415 -> 3954
      //   3542: iconst_1
      //   3543: istore_1
      //   3544: aload 14
      //   3546: ldc_w 774
      //   3549: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3552: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3555: ldc 81
      //   3557: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3560: ifeq +399 -> 3959
      //   3563: ldc_w 260
      //   3566: aload 14
      //   3568: ldc_w 774
      //   3571: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3574: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3577: ifeq +382 -> 3959
      //   3580: iconst_1
      //   3581: istore_2
      //   3582: goto +744 -> 4326
      //   3585: iconst_1
      //   3586: invokestatic 777	com/kochava/android/tracker/Feature:access$2802	(Z)Z
      //   3589: pop
      //   3590: aload 13
      //   3592: ldc_w 779
      //   3595: invokevirtual 320	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3598: astore 13
      //   3600: new 87	java/lang/StringBuilder
      //   3603: dup
      //   3604: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3607: ldc_w 781
      //   3610: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3613: aload 13
      //   3615: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3618: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3621: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3624: aload 13
      //   3626: ldc_w 783
      //   3629: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3632: ifeq +17 -> 3649
      //   3635: iconst_1
      //   3636: invokestatic 786	com/kochava/android/tracker/Feature:access$2902	(Z)Z
      //   3639: pop
      //   3640: return
      //   3641: astore 13
      //   3643: ldc_w 788
      //   3646: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3649: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3652: invokeinterface 373 1 0
      //   3657: ldc 57
      //   3659: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   3662: invokeinterface 420 4 0
      //   3667: invokeinterface 384 1 0
      //   3672: ldc_w 790
      //   3675: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3678: iconst_0
      //   3679: istore_1
      //   3680: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3683: ldc_w 258
      //   3686: ldc 71
      //   3688: invokeinterface 75 3 0
      //   3693: ldc_w 260
      //   3696: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3699: ifne +548 -> 4247
      //   3702: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3705: ldc_w 695
      //   3708: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   3711: checkcast 486	java/lang/Boolean
      //   3714: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   3717: ifne +363 -> 4080
      //   3720: iconst_1
      //   3721: istore_2
      //   3722: invokestatic 793	com/kochava/android/tracker/Feature:access$3100	()Z
      //   3725: istore 12
      //   3727: iload_1
      //   3728: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   3731: if_icmpge +50 -> 3781
      //   3734: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3737: ldc_w 795
      //   3740: ldc_w 797
      //   3743: invokeinterface 75 3 0
      //   3748: ldc_w 797
      //   3751: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3754: ifne +331 -> 4085
      //   3757: iconst_1
      //   3758: istore_3
      //   3759: invokestatic 800	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3762: ifnull +328 -> 4090
      //   3765: iconst_1
      //   3766: istore 4
      //   3768: iload_3
      //   3769: ifeq +327 -> 4096
      //   3772: iload_2
      //   3773: ifne +8 -> 3781
      //   3776: iload 12
      //   3778: ifeq +318 -> 4096
      //   3781: new 87	java/lang/StringBuilder
      //   3784: dup
      //   3785: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3788: ldc_w 802
      //   3791: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3794: iload_1
      //   3795: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3798: ldc_w 519
      //   3801: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3804: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3807: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3810: iload_1
      //   3811: istore_2
      //   3812: iload_1
      //   3813: iconst_2
      //   3814: if_icmpge +46 -> 3860
      //   3817: aload_0
      //   3818: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3821: invokestatic 805	com/kochava/android/tracker/Feature:access$000	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   3824: ifnull +323 -> 4147
      //   3827: iconst_1
      //   3828: istore_3
      //   3829: invokestatic 800	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3832: ifnull +320 -> 4152
      //   3835: invokestatic 800	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3838: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   3841: ifne +311 -> 4152
      //   3844: iconst_1
      //   3845: istore 4
      //   3847: iload_1
      //   3848: istore_2
      //   3849: iload 4
      //   3851: ifne +9 -> 3860
      //   3854: iload_3
      //   3855: ifeq +303 -> 4158
      //   3858: iload_1
      //   3859: istore_2
      //   3860: iload_2
      //   3861: iconst_2
      //   3862: if_icmpge +19 -> 3881
      //   3865: aload_0
      //   3866: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3869: invokestatic 808	com/kochava/android/tracker/Feature:access$300	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   3872: ifnull +328 -> 4200
      //   3875: iconst_1
      //   3876: istore_1
      //   3877: iload_1
      //   3878: ifeq +327 -> 4205
      //   3881: invokestatic 814	android/os/Message:obtain	()Landroid/os/Message;
      //   3884: astore 13
      //   3886: new 816	android/os/Bundle
      //   3889: dup
      //   3890: invokespecial 817	android/os/Bundle:<init>	()V
      //   3893: astore 14
      //   3895: aload 14
      //   3897: ldc_w 819
      //   3900: invokestatic 822	com/kochava/android/tracker/Feature:access$3200	()Z
      //   3903: invokevirtual 826	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   3906: aload 13
      //   3908: aload 14
      //   3910: invokevirtual 830	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   3913: aload_0
      //   3914: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3917: invokestatic 834	com/kochava/android/tracker/Feature:access$3300	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   3920: ifnull -3394 -> 526
      //   3923: ldc_w 836
      //   3926: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3929: aload_0
      //   3930: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3933: invokestatic 834	com/kochava/android/tracker/Feature:access$3300	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   3936: aload 13
      //   3938: invokevirtual 842	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   3941: pop
      //   3942: return
      //   3943: astore 15
      //   3945: ldc_w 844
      //   3948: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3951: goto -457 -> 3494
      //   3954: iconst_0
      //   3955: istore_1
      //   3956: goto -412 -> 3544
      //   3959: iconst_0
      //   3960: istore_2
      //   3961: goto +365 -> 4326
      //   3964: new 87	java/lang/StringBuilder
      //   3967: dup
      //   3968: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3971: ldc_w 846
      //   3974: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3977: aload 13
      //   3979: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3982: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3985: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3988: return
      //   3989: astore 13
      //   3991: new 87	java/lang/StringBuilder
      //   3994: dup
      //   3995: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3998: ldc_w 848
      //   4001: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4004: aload 13
      //   4006: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4009: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4012: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4015: goto -366 -> 3649
      //   4018: new 87	java/lang/StringBuilder
      //   4021: dup
      //   4022: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4025: ldc_w 846
      //   4028: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4031: aload 13
      //   4033: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4036: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4039: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4042: return
      //   4043: new 87	java/lang/StringBuilder
      //   4046: dup
      //   4047: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4050: ldc_w 850
      //   4053: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4056: lload 6
      //   4058: ldc2_w 851
      //   4061: ldiv
      //   4062: invokevirtual 637	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   4065: ldc_w 854
      //   4068: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4071: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4074: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4077: goto -405 -> 3672
      //   4080: iconst_0
      //   4081: istore_2
      //   4082: goto -360 -> 3722
      //   4085: iconst_0
      //   4086: istore_3
      //   4087: goto -328 -> 3759
      //   4090: iconst_0
      //   4091: istore 4
      //   4093: goto -325 -> 3768
      //   4096: iload_3
      //   4097: ifeq +8 -> 4105
      //   4100: iload 4
      //   4102: ifne -321 -> 3781
      //   4105: ldc2_w 851
      //   4108: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   4111: iload_1
      //   4112: iconst_1
      //   4113: iadd
      //   4114: istore_1
      //   4115: goto -388 -> 3727
      //   4118: astore 13
      //   4120: new 87	java/lang/StringBuilder
      //   4123: dup
      //   4124: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4127: ldc_w 856
      //   4130: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4133: aload 13
      //   4135: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4138: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4141: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4144: goto -33 -> 4111
      //   4147: iconst_0
      //   4148: istore_3
      //   4149: goto -320 -> 3829
      //   4152: iconst_0
      //   4153: istore 4
      //   4155: goto -308 -> 3847
      //   4158: ldc2_w 851
      //   4161: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   4164: iload_1
      //   4165: iconst_1
      //   4166: iadd
      //   4167: istore_1
      //   4168: goto -358 -> 3810
      //   4171: astore 13
      //   4173: new 87	java/lang/StringBuilder
      //   4176: dup
      //   4177: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4180: ldc_w 856
      //   4183: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4186: aload 13
      //   4188: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4191: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4194: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4197: goto -33 -> 4164
      //   4200: iconst_0
      //   4201: istore_1
      //   4202: goto -325 -> 3877
      //   4205: ldc2_w 851
      //   4208: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   4211: iload_2
      //   4212: iconst_1
      //   4213: iadd
      //   4214: istore_2
      //   4215: goto -355 -> 3860
      //   4218: astore 13
      //   4220: new 87	java/lang/StringBuilder
      //   4223: dup
      //   4224: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4227: ldc_w 856
      //   4230: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4233: aload 13
      //   4235: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4238: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4241: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4244: goto -33 -> 4211
      //   4247: ldc_w 858
      //   4250: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4253: goto -372 -> 3881
      //   4256: astore 13
      //   4258: goto -267 -> 3991
      //   4261: astore 14
      //   4263: goto -673 -> 3590
      //   4266: astore 15
      //   4268: goto -774 -> 3494
      //   4271: astore 15
      //   4273: goto -867 -> 3406
      //   4276: astore 15
      //   4278: goto -2680 -> 1598
      //   4281: astore 15
      //   4283: goto -2763 -> 1520
      //   4286: astore 15
      //   4288: goto -2846 -> 1442
      //   4291: astore 15
      //   4293: goto -3267 -> 1026
      //   4296: astore 15
      //   4298: goto -3333 -> 965
      //   4301: iload_2
      //   4302: istore_1
      //   4303: goto -4149 -> 154
      //   4306: astore 15
      //   4308: goto -3222 -> 1086
      //   4311: astore 15
      //   4313: goto -3135 -> 1178
      //   4316: astore 15
      //   4318: goto -3041 -> 1277
      //   4321: astore 15
      //   4323: goto -2961 -> 1362
      //   4326: iload_1
      //   4327: ifne -742 -> 3585
      //   4330: iload_2
      //   4331: ifeq -741 -> 3590
      //   4334: goto -749 -> 3585
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4337	0	this	6
      //   43	4284	1	i	int
      //   14	4317	2	j	int
      //   12	4137	3	k	int
      //   652	3502	4	m	int
      //   649	8	5	n	int
      //   9	4048	6	l1	long
      //   18	18	8	l2	long
      //   33	5	10	l3	long
      //   622	3155	12	bool	boolean
      //   150	1	13	localObject1	Object
      //   483	39	13	localIOException1	IOException
      //   527	15	13	localException1	Exception
      //   601	998	13	localObject2	Object
      //   1753	39	13	localException2	Exception
      //   1797	1794	13	localIOException2	IOException
      //   3598	27	13	str	String
      //   3641	1	13	localException3	Exception
      //   3884	94	13	localMessage	Message
      //   3989	43	13	localException4	Exception
      //   4118	16	13	localInterruptedException1	InterruptedException
      //   4171	16	13	localInterruptedException2	InterruptedException
      //   4218	16	13	localInterruptedException3	InterruptedException
      //   4256	1	13	localException5	Exception
      //   287	1234	14	localObject3	Object
      //   1694	16	14	localJSONException1	JSONException
      //   1735	1832	14	localJSONException2	JSONException
      //   3893	16	14	localBundle	Bundle
      //   4261	1	14	localException6	Exception
      //   367	1280	15	localObject4	Object
      //   1742	1	15	localJSONException3	JSONException
      //   1802	1	15	localJSONException4	JSONException
      //   1813	509	15	localJSONException5	JSONException
      //   2365	15	15	localException7	Exception
      //   2571	71	15	localObject5	Object
      //   2669	1	15	localException8	Exception
      //   2705	391	15	localObject6	Object
      //   3139	16	15	localException9	Exception
      //   3180	16	15	localException10	Exception
      //   3276	42	15	localException11	Exception
      //   3352	16	15	localException12	Exception
      //   3395	7	15	localJSONObject	JSONObject
      //   3943	1	15	localException13	Exception
      //   4266	1	15	localJSONException6	JSONException
      //   4271	1	15	localException14	Exception
      //   4276	1	15	localException15	Exception
      //   4281	1	15	localException16	Exception
      //   4286	1	15	localException17	Exception
      //   4291	1	15	localException18	Exception
      //   4296	1	15	localException19	Exception
      //   4306	1	15	localException20	Exception
      //   4311	1	15	localException21	Exception
      //   4316	1	15	localException22	Exception
      //   4321	1	15	localException23	Exception
      //   409	2178	16	localObject7	Object
      //   3221	30	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   2519	55	17	localPackageManager	android.content.pm.PackageManager
      //   2534	13	18	localIterator	Iterator
      //   2556	681	19	localApplicationInfo	ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   154	203	483	java/io/IOException
      //   203	214	483	java/io/IOException
      //   214	460	483	java/io/IOException
      //   460	467	483	java/io/IOException
      //   472	480	483	java/io/IOException
      //   558	588	483	java/io/IOException
      //   588	599	483	java/io/IOException
      //   1696	1723	483	java/io/IOException
      //   15	35	527	java/lang/Exception
      //   588	599	1694	org/json/JSONException
      //   638	648	1735	org/json/JSONException
      //   659	685	1735	org/json/JSONException
      //   698	756	1735	org/json/JSONException
      //   801	811	1742	org/json/JSONException
      //   815	842	1742	org/json/JSONException
      //   771	798	1753	java/lang/Exception
      //   801	811	1753	java/lang/Exception
      //   815	842	1753	java/lang/Exception
      //   851	894	1753	java/lang/Exception
      //   894	916	1753	java/lang/Exception
      //   1744	1750	1753	java/lang/Exception
      //   1804	1810	1753	java/lang/Exception
      //   1815	1821	1753	java/lang/Exception
      //   2367	2384	1753	java/lang/Exception
      //   2384	2401	1753	java/lang/Exception
      //   2425	2442	1753	java/lang/Exception
      //   2469	2486	1753	java/lang/Exception
      //   2671	2688	1753	java/lang/Exception
      //   3141	3168	1753	java/lang/Exception
      //   3171	3177	1753	java/lang/Exception
      //   3182	3209	1753	java/lang/Exception
      //   3212	3218	1753	java/lang/Exception
      //   3267	3273	1753	java/lang/Exception
      //   3278	3305	1753	java/lang/Exception
      //   3308	3314	1753	java/lang/Exception
      //   3354	3381	1753	java/lang/Exception
      //   3406	3427	1753	java/lang/Exception
      //   3643	3649	1753	java/lang/Exception
      //   3945	3951	1753	java/lang/Exception
      //   603	624	1797	java/io/IOException
      //   638	648	1797	java/io/IOException
      //   659	685	1797	java/io/IOException
      //   698	756	1797	java/io/IOException
      //   771	798	1797	java/io/IOException
      //   801	811	1797	java/io/IOException
      //   815	842	1797	java/io/IOException
      //   851	894	1797	java/io/IOException
      //   894	916	1797	java/io/IOException
      //   916	965	1797	java/io/IOException
      //   965	1026	1797	java/io/IOException
      //   1026	1086	1797	java/io/IOException
      //   1086	1138	1797	java/io/IOException
      //   1144	1173	1797	java/io/IOException
      //   1173	1178	1797	java/io/IOException
      //   1178	1221	1797	java/io/IOException
      //   1225	1277	1797	java/io/IOException
      //   1277	1323	1797	java/io/IOException
      //   1328	1362	1797	java/io/IOException
      //   1362	1377	1797	java/io/IOException
      //   1383	1412	1797	java/io/IOException
      //   1415	1442	1797	java/io/IOException
      //   1442	1457	1797	java/io/IOException
      //   1462	1491	1797	java/io/IOException
      //   1493	1520	1797	java/io/IOException
      //   1520	1535	1797	java/io/IOException
      //   1540	1569	1797	java/io/IOException
      //   1571	1598	1797	java/io/IOException
      //   1598	1635	1797	java/io/IOException
      //   1637	1687	1797	java/io/IOException
      //   1744	1750	1797	java/io/IOException
      //   1755	1796	1797	java/io/IOException
      //   1804	1810	1797	java/io/IOException
      //   1815	1821	1797	java/io/IOException
      //   1824	1869	1797	java/io/IOException
      //   1872	1897	1797	java/io/IOException
      //   1907	1943	1797	java/io/IOException
      //   1946	1984	1797	java/io/IOException
      //   1994	2046	1797	java/io/IOException
      //   2049	2104	1797	java/io/IOException
      //   2113	2148	1797	java/io/IOException
      //   2151	2185	1797	java/io/IOException
      //   2197	2226	1797	java/io/IOException
      //   2241	2270	1797	java/io/IOException
      //   2285	2314	1797	java/io/IOException
      //   2321	2362	1797	java/io/IOException
      //   2367	2384	1797	java/io/IOException
      //   2384	2401	1797	java/io/IOException
      //   2406	2425	1797	java/io/IOException
      //   2425	2442	1797	java/io/IOException
      //   2447	2469	1797	java/io/IOException
      //   2469	2486	1797	java/io/IOException
      //   2491	2536	1797	java/io/IOException
      //   2536	2565	1797	java/io/IOException
      //   2573	2586	1797	java/io/IOException
      //   2595	2666	1797	java/io/IOException
      //   2671	2688	1797	java/io/IOException
      //   2693	2788	1797	java/io/IOException
      //   2788	2825	1797	java/io/IOException
      //   2827	2868	1797	java/io/IOException
      //   2875	2916	1797	java/io/IOException
      //   2919	2960	1797	java/io/IOException
      //   2963	3004	1797	java/io/IOException
      //   3007	3048	1797	java/io/IOException
      //   3051	3092	1797	java/io/IOException
      //   3095	3136	1797	java/io/IOException
      //   3141	3168	1797	java/io/IOException
      //   3171	3177	1797	java/io/IOException
      //   3182	3209	1797	java/io/IOException
      //   3212	3218	1797	java/io/IOException
      //   3223	3264	1797	java/io/IOException
      //   3267	3273	1797	java/io/IOException
      //   3278	3305	1797	java/io/IOException
      //   3308	3314	1797	java/io/IOException
      //   3317	3349	1797	java/io/IOException
      //   3354	3381	1797	java/io/IOException
      //   3381	3406	1797	java/io/IOException
      //   3406	3427	1797	java/io/IOException
      //   3427	3494	1797	java/io/IOException
      //   3494	3542	1797	java/io/IOException
      //   3544	3580	1797	java/io/IOException
      //   3585	3590	1797	java/io/IOException
      //   3590	3640	1797	java/io/IOException
      //   3643	3649	1797	java/io/IOException
      //   3945	3951	1797	java/io/IOException
      //   3964	3988	1797	java/io/IOException
      //   851	894	1802	org/json/JSONException
      //   894	916	1813	org/json/JSONException
      //   1598	1635	2365	java/lang/Exception
      //   1637	1687	2365	java/lang/Exception
      //   2321	2362	2365	java/lang/Exception
      //   2875	2916	2365	java/lang/Exception
      //   2919	2960	2365	java/lang/Exception
      //   2963	3004	2365	java/lang/Exception
      //   3007	3048	2365	java/lang/Exception
      //   3051	3092	2365	java/lang/Exception
      //   3095	3136	2365	java/lang/Exception
      //   2491	2536	2669	java/lang/Exception
      //   2536	2565	2669	java/lang/Exception
      //   2573	2586	2669	java/lang/Exception
      //   2595	2666	2669	java/lang/Exception
      //   3223	3264	2669	java/lang/Exception
      //   2406	2425	3139	java/lang/Exception
      //   2447	2469	3180	java/lang/Exception
      //   2573	2586	3221	android/content/pm/PackageManager$NameNotFoundException
      //   2693	2788	3276	java/lang/Exception
      //   2788	2825	3352	java/lang/Exception
      //   2827	2868	3352	java/lang/Exception
      //   3317	3349	3352	java/lang/Exception
      //   3590	3640	3641	java/lang/Exception
      //   3427	3494	3943	java/lang/Exception
      //   603	624	3989	java/lang/Exception
      //   638	648	3989	java/lang/Exception
      //   659	685	3989	java/lang/Exception
      //   698	756	3989	java/lang/Exception
      //   1755	1796	3989	java/lang/Exception
      //   3964	3988	3989	java/lang/Exception
      //   4105	4111	4118	java/lang/InterruptedException
      //   4158	4164	4171	java/lang/InterruptedException
      //   4205	4211	4218	java/lang/InterruptedException
      //   154	203	4256	java/lang/Exception
      //   203	214	4256	java/lang/Exception
      //   214	460	4256	java/lang/Exception
      //   460	467	4256	java/lang/Exception
      //   472	480	4256	java/lang/Exception
      //   558	588	4256	java/lang/Exception
      //   588	599	4256	java/lang/Exception
      //   1696	1723	4256	java/lang/Exception
      //   3494	3542	4261	java/lang/Exception
      //   3544	3580	4261	java/lang/Exception
      //   3585	3590	4261	java/lang/Exception
      //   3427	3494	4266	org/json/JSONException
      //   3381	3406	4271	java/lang/Exception
      //   1520	1535	4276	java/lang/Exception
      //   1540	1569	4276	java/lang/Exception
      //   1571	1598	4276	java/lang/Exception
      //   2285	2314	4276	java/lang/Exception
      //   1442	1457	4281	java/lang/Exception
      //   1462	1491	4281	java/lang/Exception
      //   1493	1520	4281	java/lang/Exception
      //   2241	2270	4281	java/lang/Exception
      //   1362	1377	4286	java/lang/Exception
      //   1383	1412	4286	java/lang/Exception
      //   1415	1442	4286	java/lang/Exception
      //   2197	2226	4286	java/lang/Exception
      //   965	1026	4291	java/lang/Exception
      //   916	965	4296	java/lang/Exception
      //   1026	1086	4306	java/lang/Exception
      //   1824	1869	4306	java/lang/Exception
      //   1872	1897	4306	java/lang/Exception
      //   1086	1138	4311	java/lang/Exception
      //   1144	1173	4311	java/lang/Exception
      //   1173	1178	4311	java/lang/Exception
      //   1907	1943	4311	java/lang/Exception
      //   1946	1984	4311	java/lang/Exception
      //   1178	1221	4316	java/lang/Exception
      //   1225	1277	4316	java/lang/Exception
      //   1994	2046	4316	java/lang/Exception
      //   2049	2104	4316	java/lang/Exception
      //   1277	1323	4321	java/lang/Exception
      //   1328	1362	4321	java/lang/Exception
      //   2113	2148	4321	java/lang/Exception
      //   2151	2185	4321	java/lang/Exception
    }
  };
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
  private String mDeviceId;
  private int mDisplayHeight;
  private int mDisplayWidth;
  private String mFbId;
  private boolean mIsStartOfLife = true;
  private String mOSVersion;
  private Timer mTimerSendOnBegin;
  
  static
  {
    overrideAutomaticSessions = false;
    flush_rate = 60000;
    flushTimerSetByInitializer = false;
    flushTimerSetByKVinitResponse = false;
    kvinitRetrySteps = new HashMap() {};
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
    paramRestrictions = new HashMap() {};
    eventnameBlacklist = new JSONArray();
    sendKVQuery = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: bipush 60
        //   2: istore_2
        //   3: iload_2
        //   4: istore_1
        //   5: invokestatic 26	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
        //   8: ifnull +17 -> 25
        //   11: iload_2
        //   12: istore_1
        //   13: invokestatic 26	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
        //   16: invokevirtual 31	java/lang/String:trim	()Ljava/lang/String;
        //   19: invokevirtual 35	java/lang/String:isEmpty	()Z
        //   22: ifeq +18 -> 40
        //   25: iload_2
        //   26: istore_1
        //   27: ldc 37
        //   29: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   32: iload_2
        //   33: istore_1
        //   34: ldc 45
        //   36: invokestatic 49	com/kochava/android/tracker/Feature:access$602	(Ljava/lang/String;)Ljava/lang/String;
        //   39: pop
        //   40: iload_2
        //   41: istore_1
        //   42: new 51	java/lang/StringBuilder
        //   45: dup
        //   46: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   49: ldc 54
        //   51: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   54: ldc 60
        //   56: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   59: invokestatic 26	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
        //   62: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   65: ldc 62
        //   67: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   70: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   73: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   76: iload_2
        //   77: istore_1
        //   78: new 67	java/net/URL
        //   81: dup
        //   82: new 51	java/lang/StringBuilder
        //   85: dup
        //   86: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   89: ldc 60
        //   91: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   94: invokestatic 26	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
        //   97: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: ldc 62
        //   102: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   108: invokespecial 69	java/net/URL:<init>	(Ljava/lang/String;)V
        //   111: invokevirtual 73	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   114: checkcast 75	javax/net/ssl/HttpsURLConnection
        //   117: astore 7
        //   119: iload_2
        //   120: istore_1
        //   121: aload 7
        //   123: ldc 77
        //   125: invokestatic 81	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
        //   128: ldc 83
        //   130: ldc 85
        //   132: invokeinterface 91 3 0
        //   137: invokevirtual 95	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   140: iload_2
        //   141: istore_1
        //   142: aload 7
        //   144: ldc 97
        //   146: ldc 99
        //   148: invokevirtual 95	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   151: iload_2
        //   152: istore_1
        //   153: aload 7
        //   155: ldc 101
        //   157: invokevirtual 104	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   160: iload_2
        //   161: istore_1
        //   162: aload 7
        //   164: sipush 30000
        //   167: invokevirtual 108	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   170: iload_2
        //   171: istore_1
        //   172: aload 7
        //   174: sipush 30000
        //   177: invokevirtual 111	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   180: iload_2
        //   181: istore_1
        //   182: aload 7
        //   184: iconst_1
        //   185: invokevirtual 115	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   188: iload_2
        //   189: istore_1
        //   190: aload 7
        //   192: iconst_1
        //   193: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   196: iload_2
        //   197: istore_1
        //   198: aload 7
        //   200: invokevirtual 121	javax/net/ssl/HttpsURLConnection:connect	()V
        //   203: iload_2
        //   204: istore_1
        //   205: new 123	org/json/JSONObject
        //   208: dup
        //   209: invokespecial 124	org/json/JSONObject:<init>	()V
        //   212: astore 8
        //   214: iload_2
        //   215: istore_1
        //   216: aload 8
        //   218: ldc 126
        //   220: ldc -128
        //   222: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   225: pop
        //   226: iload_2
        //   227: istore_1
        //   228: aload 8
        //   230: ldc -122
        //   232: invokestatic 137	com/kochava/android/tracker/Feature:access$800	()Ljava/lang/String;
        //   235: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   238: pop
        //   239: iload_2
        //   240: istore_1
        //   241: aload 8
        //   243: ldc -117
        //   245: invokestatic 142	com/kochava/android/tracker/Feature:access$3400	()Ljava/lang/String;
        //   248: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   251: pop
        //   252: iload_2
        //   253: istore_1
        //   254: aload 8
        //   256: ldc -112
        //   258: new 51	java/lang/StringBuilder
        //   261: dup
        //   262: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   265: ldc -110
        //   267: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: getstatic 150	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
        //   273: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   279: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   282: pop
        //   283: iload_2
        //   284: istore_1
        //   285: aload 8
        //   287: ldc -104
        //   289: ldc -102
        //   291: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   294: pop
        //   295: iload_2
        //   296: istore_1
        //   297: aload 8
        //   299: invokevirtual 155	org/json/JSONObject:toString	()Ljava/lang/String;
        //   302: astore 8
        //   304: iload_2
        //   305: istore_1
        //   306: new 51	java/lang/StringBuilder
        //   309: dup
        //   310: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   313: ldc -99
        //   315: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   318: aload 8
        //   320: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   326: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   329: iload_2
        //   330: istore_1
        //   331: ldc -97
        //   333: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   336: iload_2
        //   337: istore_1
        //   338: new 161	java/io/OutputStreamWriter
        //   341: dup
        //   342: aload 7
        //   344: invokevirtual 165	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   347: invokespecial 168	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   350: astore 9
        //   352: iload_2
        //   353: istore_1
        //   354: aload 9
        //   356: aload 8
        //   358: invokevirtual 171	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   361: iload_2
        //   362: istore_1
        //   363: aload 9
        //   365: invokevirtual 174	java/io/OutputStreamWriter:close	()V
        //   368: iload_2
        //   369: istore 4
        //   371: iload_2
        //   372: istore 6
        //   374: iload_2
        //   375: istore_1
        //   376: ldc -80
        //   378: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   381: iload_2
        //   382: istore 4
        //   384: iload_2
        //   385: istore 6
        //   387: iload_2
        //   388: istore_1
        //   389: new 178	java/lang/StringBuffer
        //   392: dup
        //   393: ldc 85
        //   395: invokespecial 179	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   398: astore 8
        //   400: iload_2
        //   401: istore 4
        //   403: iload_2
        //   404: istore 6
        //   406: iload_2
        //   407: istore_1
        //   408: new 181	java/io/BufferedReader
        //   411: dup
        //   412: new 183	java/io/InputStreamReader
        //   415: dup
        //   416: aload 7
        //   418: invokevirtual 187	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
        //   421: invokespecial 190	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   424: invokespecial 193	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   427: astore 7
        //   429: iload_2
        //   430: istore 4
        //   432: iload_2
        //   433: istore 6
        //   435: iload_2
        //   436: istore_1
        //   437: aload 7
        //   439: invokevirtual 196	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   442: astore 9
        //   444: aload 9
        //   446: ifnull +75 -> 521
        //   449: iload_2
        //   450: istore 4
        //   452: iload_2
        //   453: istore 6
        //   455: iload_2
        //   456: istore_1
        //   457: aload 8
        //   459: aload 9
        //   461: invokevirtual 199	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   464: pop
        //   465: goto -36 -> 429
        //   468: astore 7
        //   470: iload 4
        //   472: istore_1
        //   473: aload 7
        //   475: invokevirtual 203	java/lang/Object:getClass	()Ljava/lang/Class;
        //   478: ldc -51
        //   480: invokevirtual 209	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   483: ifeq +633 -> 1116
        //   486: iload 4
        //   488: istore_1
        //   489: new 51	java/lang/StringBuilder
        //   492: dup
        //   493: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   496: ldc -45
        //   498: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   501: aload 7
        //   503: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   506: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   509: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   512: iload 4
        //   514: istore_1
        //   515: aload 7
        //   517: invokestatic 221	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
        //   520: return
        //   521: iload_2
        //   522: istore 4
        //   524: iload_2
        //   525: istore 6
        //   527: iload_2
        //   528: istore_1
        //   529: aload 8
        //   531: invokevirtual 222	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   534: astore 7
        //   536: iload_2
        //   537: istore 4
        //   539: iload_2
        //   540: istore 6
        //   542: iload_2
        //   543: istore_1
        //   544: new 51	java/lang/StringBuilder
        //   547: dup
        //   548: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   551: ldc -32
        //   553: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   556: aload 7
        //   558: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   561: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   564: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   567: aconst_null
        //   568: astore 8
        //   570: iload_2
        //   571: istore 4
        //   573: iload_2
        //   574: istore 6
        //   576: iload_2
        //   577: istore_1
        //   578: new 123	org/json/JSONObject
        //   581: dup
        //   582: aload 7
        //   584: invokespecial 225	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   587: astore 7
        //   589: aload 7
        //   591: astore 8
        //   593: iload_2
        //   594: istore 5
        //   596: aload 8
        //   598: ifnull +301 -> 899
        //   601: iload_2
        //   602: istore 4
        //   604: iload_2
        //   605: istore 6
        //   607: iload_2
        //   608: istore_1
        //   609: new 51	java/lang/StringBuilder
        //   612: dup
        //   613: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   616: ldc -29
        //   618: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   621: aload 8
        //   623: invokevirtual 155	org/json/JSONObject:toString	()Ljava/lang/String;
        //   626: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   629: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   632: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   635: aconst_null
        //   636: astore 7
        //   638: iload_2
        //   639: istore 4
        //   641: iload_2
        //   642: istore 6
        //   644: iload_2
        //   645: istore_1
        //   646: aload 8
        //   648: ldc -27
        //   650: invokevirtual 233	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   653: astore 8
        //   655: iload_2
        //   656: istore 4
        //   658: iload_2
        //   659: istore 6
        //   661: aload 8
        //   663: astore 7
        //   665: iload_2
        //   666: istore_1
        //   667: new 51	java/lang/StringBuilder
        //   670: dup
        //   671: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   674: ldc -21
        //   676: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: aload 8
        //   681: invokevirtual 155	org/json/JSONObject:toString	()Ljava/lang/String;
        //   684: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   687: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   690: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   693: aload 8
        //   695: astore 7
        //   697: iload_2
        //   698: istore 5
        //   700: aload 7
        //   702: ifnull +197 -> 899
        //   705: ldc 85
        //   707: astore 8
        //   709: iload_2
        //   710: istore 4
        //   712: iload_2
        //   713: istore 6
        //   715: iload_2
        //   716: istore_1
        //   717: aload 7
        //   719: ldc -19
        //   721: invokevirtual 239	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   724: astore 9
        //   726: aload 9
        //   728: astore 8
        //   730: iload_2
        //   731: istore 4
        //   733: iload_2
        //   734: istore 6
        //   736: iload_2
        //   737: istore_1
        //   738: aload 7
        //   740: ldc -15
        //   742: invokevirtual 245	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   745: istore_3
        //   746: iload_3
        //   747: istore 5
        //   749: iload_3
        //   750: ifge +149 -> 899
        //   753: iload_3
        //   754: istore 4
        //   756: iload_3
        //   757: istore 6
        //   759: iload_3
        //   760: istore_1
        //   761: iload_3
        //   762: istore_2
        //   763: invokestatic 248	com/kochava/android/tracker/Feature:access$3500	()Landroid/content/SharedPreferences;
        //   766: invokeinterface 252 1 0
        //   771: ldc -2
        //   773: aload 8
        //   775: invokeinterface 260 3 0
        //   780: invokeinterface 263 1 0
        //   785: iload_3
        //   786: istore 4
        //   788: iload_3
        //   789: istore 5
        //   791: iload_3
        //   792: istore 6
        //   794: iload_3
        //   795: istore_1
        //   796: iload_3
        //   797: istore_2
        //   798: invokestatic 267	com/kochava/android/tracker/Feature:access$3600	()Landroid/os/Handler;
        //   801: ifnull +98 -> 899
        //   804: iload_3
        //   805: istore 4
        //   807: iload_3
        //   808: istore 6
        //   810: iload_3
        //   811: istore_1
        //   812: iload_3
        //   813: istore_2
        //   814: invokestatic 273	android/os/Message:obtain	()Landroid/os/Message;
        //   817: astore 7
        //   819: iload_3
        //   820: istore 4
        //   822: iload_3
        //   823: istore 6
        //   825: iload_3
        //   826: istore_1
        //   827: iload_3
        //   828: istore_2
        //   829: new 275	android/os/Bundle
        //   832: dup
        //   833: invokespecial 276	android/os/Bundle:<init>	()V
        //   836: astore 9
        //   838: iload_3
        //   839: istore 4
        //   841: iload_3
        //   842: istore 6
        //   844: iload_3
        //   845: istore_1
        //   846: iload_3
        //   847: istore_2
        //   848: aload 9
        //   850: ldc -2
        //   852: aload 8
        //   854: invokevirtual 277	java/lang/String:toString	()Ljava/lang/String;
        //   857: invokevirtual 279	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   860: iload_3
        //   861: istore 4
        //   863: iload_3
        //   864: istore 6
        //   866: iload_3
        //   867: istore_1
        //   868: iload_3
        //   869: istore_2
        //   870: aload 7
        //   872: aload 9
        //   874: invokevirtual 283	android/os/Message:setData	(Landroid/os/Bundle;)V
        //   877: iload_3
        //   878: istore 4
        //   880: iload_3
        //   881: istore 6
        //   883: iload_3
        //   884: istore_1
        //   885: iload_3
        //   886: istore_2
        //   887: invokestatic 267	com/kochava/android/tracker/Feature:access$3600	()Landroid/os/Handler;
        //   890: aload 7
        //   892: invokevirtual 289	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   895: pop
        //   896: iload_3
        //   897: istore 5
        //   899: iload 5
        //   901: ifle -381 -> 520
        //   904: iload 5
        //   906: invokestatic 292	com/kochava/android/tracker/Feature:sendKVQuery	(I)V
        //   909: return
        //   910: astore 7
        //   912: iload_2
        //   913: istore 4
        //   915: iload_2
        //   916: istore 6
        //   918: iload_2
        //   919: istore_1
        //   920: new 51	java/lang/StringBuilder
        //   923: dup
        //   924: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   927: ldc_w 294
        //   930: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   933: aload 7
        //   935: invokevirtual 295	org/json/JSONException:toString	()Ljava/lang/String;
        //   938: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   941: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   944: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   947: goto -354 -> 593
        //   950: astore 7
        //   952: iload 6
        //   954: istore_1
        //   955: new 51	java/lang/StringBuilder
        //   958: dup
        //   959: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   962: ldc_w 297
        //   965: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   968: aload 7
        //   970: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   973: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   976: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   979: return
        //   980: astore 7
        //   982: aload 7
        //   984: invokevirtual 203	java/lang/Object:getClass	()Ljava/lang/Class;
        //   987: ldc -51
        //   989: invokevirtual 209	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   992: ifeq +152 -> 1144
        //   995: new 51	java/lang/StringBuilder
        //   998: dup
        //   999: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1002: ldc -45
        //   1004: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1007: aload 7
        //   1009: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1012: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1015: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1018: aload 7
        //   1020: invokestatic 221	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
        //   1023: return
        //   1024: astore 8
        //   1026: iload_2
        //   1027: istore 4
        //   1029: iload_2
        //   1030: istore 6
        //   1032: iload_2
        //   1033: istore_1
        //   1034: ldc_w 299
        //   1037: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   1040: goto -343 -> 697
        //   1043: astore 7
        //   1045: new 51	java/lang/StringBuilder
        //   1048: dup
        //   1049: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1052: ldc_w 301
        //   1055: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1058: aload 7
        //   1060: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1063: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1066: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1069: iload_1
        //   1070: istore 5
        //   1072: goto -173 -> 899
        //   1075: astore 9
        //   1077: iload_2
        //   1078: istore 4
        //   1080: iload_2
        //   1081: istore 6
        //   1083: iload_2
        //   1084: istore_1
        //   1085: ldc_w 303
        //   1088: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1091: goto -361 -> 730
        //   1094: astore 7
        //   1096: iload_2
        //   1097: istore 4
        //   1099: iload_2
        //   1100: istore 6
        //   1102: iload_2
        //   1103: istore_1
        //   1104: ldc_w 305
        //   1107: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1110: iload_2
        //   1111: istore 5
        //   1113: goto -214 -> 899
        //   1116: iload 4
        //   1118: istore_1
        //   1119: new 51	java/lang/StringBuilder
        //   1122: dup
        //   1123: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1126: ldc_w 307
        //   1129: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1132: aload 7
        //   1134: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1137: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1140: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1143: return
        //   1144: new 51	java/lang/StringBuilder
        //   1147: dup
        //   1148: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1151: ldc_w 307
        //   1154: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1157: aload 7
        //   1159: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1162: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1165: invokestatic 217	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1168: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1169	0	this	7
        //   4	1115	1	i	int
        //   2	1109	2	j	int
        //   745	152	3	k	int
        //   369	748	4	m	int
        //   594	518	5	n	int
        //   372	729	6	i1	int
        //   117	321	7	localObject1	Object
        //   468	48	7	localIOException1	IOException
        //   534	357	7	localObject2	Object
        //   910	24	7	localJSONException1	JSONException
        //   950	19	7	localOutOfMemoryError	OutOfMemoryError
        //   980	39	7	localIOException2	IOException
        //   1043	16	7	localException	Exception
        //   1094	64	7	localJSONException2	JSONException
        //   212	641	8	localObject3	Object
        //   1024	1	8	localJSONException3	JSONException
        //   350	523	9	localObject4	Object
        //   1075	1	9	localJSONException4	JSONException
        // Exception table:
        //   from	to	target	type
        //   376	381	468	java/io/IOException
        //   389	400	468	java/io/IOException
        //   408	429	468	java/io/IOException
        //   437	444	468	java/io/IOException
        //   457	465	468	java/io/IOException
        //   529	536	468	java/io/IOException
        //   544	567	468	java/io/IOException
        //   578	589	468	java/io/IOException
        //   609	635	468	java/io/IOException
        //   646	655	468	java/io/IOException
        //   667	693	468	java/io/IOException
        //   717	726	468	java/io/IOException
        //   738	746	468	java/io/IOException
        //   763	785	468	java/io/IOException
        //   798	804	468	java/io/IOException
        //   814	819	468	java/io/IOException
        //   829	838	468	java/io/IOException
        //   848	860	468	java/io/IOException
        //   870	877	468	java/io/IOException
        //   887	896	468	java/io/IOException
        //   920	947	468	java/io/IOException
        //   1034	1040	468	java/io/IOException
        //   1085	1091	468	java/io/IOException
        //   1104	1110	468	java/io/IOException
        //   578	589	910	org/json/JSONException
        //   376	381	950	java/lang/OutOfMemoryError
        //   389	400	950	java/lang/OutOfMemoryError
        //   408	429	950	java/lang/OutOfMemoryError
        //   437	444	950	java/lang/OutOfMemoryError
        //   457	465	950	java/lang/OutOfMemoryError
        //   529	536	950	java/lang/OutOfMemoryError
        //   544	567	950	java/lang/OutOfMemoryError
        //   578	589	950	java/lang/OutOfMemoryError
        //   609	635	950	java/lang/OutOfMemoryError
        //   646	655	950	java/lang/OutOfMemoryError
        //   667	693	950	java/lang/OutOfMemoryError
        //   717	726	950	java/lang/OutOfMemoryError
        //   738	746	950	java/lang/OutOfMemoryError
        //   763	785	950	java/lang/OutOfMemoryError
        //   798	804	950	java/lang/OutOfMemoryError
        //   814	819	950	java/lang/OutOfMemoryError
        //   829	838	950	java/lang/OutOfMemoryError
        //   848	860	950	java/lang/OutOfMemoryError
        //   870	877	950	java/lang/OutOfMemoryError
        //   887	896	950	java/lang/OutOfMemoryError
        //   920	947	950	java/lang/OutOfMemoryError
        //   1034	1040	950	java/lang/OutOfMemoryError
        //   1085	1091	950	java/lang/OutOfMemoryError
        //   1104	1110	950	java/lang/OutOfMemoryError
        //   5	11	980	java/io/IOException
        //   13	25	980	java/io/IOException
        //   27	32	980	java/io/IOException
        //   34	40	980	java/io/IOException
        //   42	76	980	java/io/IOException
        //   78	119	980	java/io/IOException
        //   121	140	980	java/io/IOException
        //   142	151	980	java/io/IOException
        //   153	160	980	java/io/IOException
        //   162	170	980	java/io/IOException
        //   172	180	980	java/io/IOException
        //   182	188	980	java/io/IOException
        //   190	196	980	java/io/IOException
        //   198	203	980	java/io/IOException
        //   205	214	980	java/io/IOException
        //   216	226	980	java/io/IOException
        //   228	239	980	java/io/IOException
        //   241	252	980	java/io/IOException
        //   254	283	980	java/io/IOException
        //   285	295	980	java/io/IOException
        //   297	304	980	java/io/IOException
        //   306	329	980	java/io/IOException
        //   331	336	980	java/io/IOException
        //   338	352	980	java/io/IOException
        //   354	361	980	java/io/IOException
        //   363	368	980	java/io/IOException
        //   473	486	980	java/io/IOException
        //   489	512	980	java/io/IOException
        //   515	520	980	java/io/IOException
        //   955	979	980	java/io/IOException
        //   1119	1143	980	java/io/IOException
        //   646	655	1024	org/json/JSONException
        //   667	693	1024	org/json/JSONException
        //   5	11	1043	java/lang/Exception
        //   13	25	1043	java/lang/Exception
        //   27	32	1043	java/lang/Exception
        //   34	40	1043	java/lang/Exception
        //   42	76	1043	java/lang/Exception
        //   78	119	1043	java/lang/Exception
        //   121	140	1043	java/lang/Exception
        //   142	151	1043	java/lang/Exception
        //   153	160	1043	java/lang/Exception
        //   162	170	1043	java/lang/Exception
        //   172	180	1043	java/lang/Exception
        //   182	188	1043	java/lang/Exception
        //   190	196	1043	java/lang/Exception
        //   198	203	1043	java/lang/Exception
        //   205	214	1043	java/lang/Exception
        //   216	226	1043	java/lang/Exception
        //   228	239	1043	java/lang/Exception
        //   241	252	1043	java/lang/Exception
        //   254	283	1043	java/lang/Exception
        //   285	295	1043	java/lang/Exception
        //   297	304	1043	java/lang/Exception
        //   306	329	1043	java/lang/Exception
        //   331	336	1043	java/lang/Exception
        //   338	352	1043	java/lang/Exception
        //   354	361	1043	java/lang/Exception
        //   363	368	1043	java/lang/Exception
        //   376	381	1043	java/lang/Exception
        //   389	400	1043	java/lang/Exception
        //   408	429	1043	java/lang/Exception
        //   437	444	1043	java/lang/Exception
        //   457	465	1043	java/lang/Exception
        //   473	486	1043	java/lang/Exception
        //   489	512	1043	java/lang/Exception
        //   515	520	1043	java/lang/Exception
        //   529	536	1043	java/lang/Exception
        //   544	567	1043	java/lang/Exception
        //   578	589	1043	java/lang/Exception
        //   609	635	1043	java/lang/Exception
        //   646	655	1043	java/lang/Exception
        //   667	693	1043	java/lang/Exception
        //   717	726	1043	java/lang/Exception
        //   738	746	1043	java/lang/Exception
        //   763	785	1043	java/lang/Exception
        //   798	804	1043	java/lang/Exception
        //   814	819	1043	java/lang/Exception
        //   829	838	1043	java/lang/Exception
        //   848	860	1043	java/lang/Exception
        //   870	877	1043	java/lang/Exception
        //   887	896	1043	java/lang/Exception
        //   920	947	1043	java/lang/Exception
        //   955	979	1043	java/lang/Exception
        //   1034	1040	1043	java/lang/Exception
        //   1085	1091	1043	java/lang/Exception
        //   1104	1110	1043	java/lang/Exception
        //   1119	1143	1043	java/lang/Exception
        //   717	726	1075	org/json/JSONException
        //   738	746	1094	org/json/JSONException
        //   763	785	1094	org/json/JSONException
        //   798	804	1094	org/json/JSONException
        //   814	819	1094	org/json/JSONException
        //   829	838	1094	org/json/JSONException
        //   848	860	1094	org/json/JSONException
        //   870	877	1094	org/json/JSONException
        //   887	896	1094	org/json/JSONException
      }
    };
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
    new Thread()
    {
      public void run()
      {
        Logging.Log("Got event " + this.val$state);
        Object localObject1 = new HashMap();
        ((HashMap)localObject1).put("state", this.val$state);
        Logging.Log("FIRE EVENT*** action:" + "session");
        Logging.Log("FIRE EVENT*** properties:" + localObject1);
        JSONObject localJSONObject1 = new JSONObject();
        label308:
        do
        {
          JSONObject localJSONObject2;
          Object localObject2;
          try
          {
            localJSONObject1.put("kochava_app_id", Feature.mAppId);
            localJSONObject1.put("kochava_device_id", Feature.access$3400());
            localJSONObject1.put("action", "session");
            localJSONObject1.put("dev_id_strategy", Feature.mKochDevIDStrategy);
            localJSONObject2 = Feature.addGlobalProperties(new JSONObject());
            if (Feature.mSuperProperties != null)
            {
              localObject2 = Feature.mSuperProperties.entrySet().iterator();
              while (((Iterator)localObject2).hasNext())
              {
                Map.Entry localEntry = (Map.Entry)((Iterator)localObject2).next();
                localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
              }
            }
            if (localObject1 == null) {
              break label308;
            }
          }
          catch (JSONException localJSONException)
          {
            if (Global.DEBUGERROR) {
              localJSONException.printStackTrace();
            }
            Logging.LogError("event " + localJSONException);
            return;
          }
          localObject1 = ((HashMap)localObject1).entrySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (Map.Entry)((Iterator)localObject1).next();
            localJSONObject2.put((String)((Map.Entry)localObject2).getKey(), ((Map.Entry)localObject2).getValue());
          }
          localJSONException.put("data", localJSONObject2);
          Logging.Log("fireEvent with properties: " + localJSONException);
        } while (Feature.kDbAdapter.addEvent(localJSONException, false, false) < 50);
        Feature.flush();
      }
    }.start();
  }
  
  private void fireEvent(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    if ((!paramString.equals("initial")) && (!should_flush_in_background) && (is_in_background) && (!event_flush_triggered))
    {
      event_flush_triggered = true;
      this.eventFlushTimer.schedule(new TimerTask()
      {
        public void run()
        {
          if (Feature.is_in_background) {
            Feature.flush();
          }
          Feature.access$4902(false);
        }
      }, 60000L);
    }
    Logging.Log("FIRE EVENT*** action:" + paramString);
    Logging.Log("FIRE EVENT*** properties:" + paramMap1);
    JSONObject localJSONObject1 = new JSONObject();
    label1080:
    label1117:
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
                  ApplicationInfoHolder localApplicationInfoHolder = (ApplicationInfoHolder)((Iterator)localObject).next();
                  JSONObject localJSONObject3 = new JSONObject();
                  localJSONObject3.put("app_name", localApplicationInfoHolder.appPackage);
                  localJSONObject3.put("mtime", localApplicationInfoHolder.appInstallTime);
                  localJSONObject3.put("utime", localApplicationInfoHolder.appUpdateTime);
                  paramString.put(localJSONObject3);
                  continue;
                }
              }
              if (mSuperProperties == null) {
                break label1117;
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
              localJSONObject1.put("sdk_version", "Android20160615" + versionExtension);
              if (((Boolean)paramRestrictions.get("volume")).booleanValue()) {
                localJSONObject2.put("volume", getVolume());
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
      if (paramMap2 != null) {
        localJSONObject2.put("receipt", new JSONObject(paramMap2));
      }
      if (paramMap1 != null)
      {
        paramString = paramMap1.entrySet().iterator();
        while (paramString.hasNext())
        {
          paramMap1 = (Map.Entry)paramString.next();
          localJSONObject2.put((String)paramMap1.getKey(), paramMap1.getValue());
        }
        if (paramMap2 != null) {
          localJSONObject2.put("receipt", new JSONObject(paramMap2));
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
  
  private void fireEventBlacklist(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    String str = (String)paramMap1.get("event_name");
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
          boolean bool = eventnameBlacklist.get(i).equals(paramMap1.get("event_name"));
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
      fireEvent(paramString, paramMap1, paramMap2);
      return;
    }
    fireEvent(paramString, paramMap1, paramMap2);
  }
  
  public static void flush()
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    Logging.Log("flush");
    executor.submit(new TrackTask(null));
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
    //   8: getstatic 435	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 480	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 110
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1151	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1156 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1159 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1162 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 110
    //   75: invokeinterface 1166 2 0
    //   80: invokeinterface 1167 2 0
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
    //   99: invokeinterface 1159 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1162 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 714	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1169
    //   130: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1159 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1162 1 0
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
    //   190: invokeinterface 1159 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1162 1 0
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
    new Thread()
    {
      public void run()
      {
        try
        {
          Feature.access$2902(true);
          Object localObject2 = this.val$e.getMessage();
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("message", localObject2);
          ((JSONObject)localObject1).put("os_version", Feature.access$4000());
          ((JSONObject)localObject1).put("device", Feature.access$5900() + "-" + Feature.access$6000());
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$3400());
          ((JSONObject)localObject2).put("action", "error");
          ((JSONObject)localObject2).put("data", localObject1);
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20160615" + Feature.versionExtension);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          Logging.Log("https log - posting to " + "http://" + Feature.hostControl + "/track/kvTracker.php");
          Object localObject3 = new URL("http://" + Feature.hostControl + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject2).toString();
          Logging.Log("https failure data:" + (String)localObject1);
          localObject2 = (HttpURLConnection)((URL)localObject3).openConnection();
          ((HttpURLConnection)localObject2).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
          ((HttpURLConnection)localObject2).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpURLConnection)localObject2).setRequestMethod("POST");
          ((HttpURLConnection)localObject2).setConnectTimeout(30000);
          ((HttpURLConnection)localObject2).setReadTimeout(30000);
          ((HttpURLConnection)localObject2).setDoInput(true);
          ((HttpURLConnection)localObject2).setDoOutput(true);
          ((HttpURLConnection)localObject2).connect();
          localObject3 = new OutputStreamWriter(((HttpURLConnection)localObject2).getOutputStream());
          ((OutputStreamWriter)localObject3).write((String)localObject1);
          ((OutputStreamWriter)localObject3).close();
          Logging.Log("Grabbing Result...");
          localObject1 = new StringBuffer("");
          localObject2 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject2).getInputStream()));
          for (;;)
          {
            localObject3 = ((BufferedReader)localObject2).readLine();
            if (localObject3 == null) {
              break;
            }
            ((StringBuffer)localObject1).append((String)localObject3);
          }
          str = localException.toString();
        }
        catch (Exception localException)
        {
          Logging.LogError("httpsFail " + localException);
          return;
        }
        String str;
        Logging.Log("Result: " + str);
      }
    }.start();
  }
  
  /* Error */
  @TargetApi(14)
  private void init(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +2109 -> 2110
    //   4: aload_1
    //   5: invokevirtual 1338	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 382	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   15: ldc -59
    //   17: new 714	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 1340
    //   27: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: getstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   33: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokestatic 1346	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   42: pop
    //   43: aload_0
    //   44: new 847	java/util/Timer
    //   47: dup
    //   48: invokespecial 1347	java/util/Timer:<init>	()V
    //   51: putfield 872	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   54: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   57: ldc -31
    //   59: iconst_0
    //   60: invokevirtual 749	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   63: putstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   66: new 1063	com/kochava/android/tracker/DbAdapter
    //   69: dup
    //   70: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   73: invokespecial 1348	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   76: putstatic 638	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   79: aload_3
    //   80: ifnull +231 -> 311
    //   83: aload_3
    //   84: ldc_w 1350
    //   87: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   90: ifnull +44 -> 134
    //   93: aload_3
    //   94: ldc_w 1350
    //   97: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   100: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   103: ldc_w 903
    //   106: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   109: ifeq +25 -> 134
    //   112: aload_3
    //   113: ldc_w 1350
    //   116: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   119: checkcast 903	java/lang/Boolean
    //   122: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   125: istore_2
    //   126: iload_2
    //   127: invokestatic 1356	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   130: iload_2
    //   131: invokestatic 1359	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   134: aload_3
    //   135: ldc_w 1361
    //   138: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   141: ifnull +35 -> 176
    //   144: aload_3
    //   145: ldc_w 1361
    //   148: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: ldc_w 480
    //   157: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   160: ifeq +16 -> 176
    //   163: aload_3
    //   164: ldc_w 1361
    //   167: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 480	java/lang/String
    //   173: putstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   176: aload_3
    //   177: ldc_w 1363
    //   180: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: ifnull +38 -> 221
    //   186: aload_3
    //   187: ldc_w 1363
    //   190: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   196: ldc_w 903
    //   199: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   202: ifeq +19 -> 221
    //   205: aload_3
    //   206: ldc_w 1363
    //   209: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   212: checkcast 903	java/lang/Boolean
    //   215: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   218: putstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   221: aload_3
    //   222: ldc_w 1365
    //   225: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   228: ifnull +38 -> 266
    //   231: aload_3
    //   232: ldc_w 1365
    //   235: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: ldc_w 903
    //   244: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   247: ifeq +19 -> 266
    //   250: aload_3
    //   251: ldc_w 1365
    //   254: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   257: checkcast 903	java/lang/Boolean
    //   260: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   263: putstatic 380	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   266: aload_3
    //   267: ldc_w 1367
    //   270: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   273: ifnull +38 -> 311
    //   276: aload_3
    //   277: ldc_w 1367
    //   280: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   283: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   286: ldc_w 903
    //   289: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +19 -> 311
    //   295: aload_3
    //   296: ldc_w 1367
    //   299: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   302: checkcast 903	java/lang/Boolean
    //   305: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   308: putstatic 397	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   311: aload_0
    //   312: invokespecial 1369	com/kochava/android/tracker/Feature:initHandler	()V
    //   315: new 714	java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   322: ldc_w 1371
    //   325: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   331: ldc -126
    //   333: ldc_w 353
    //   336: invokeinterface 755 3 0
    //   341: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   350: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   353: ldc -126
    //   355: ldc_w 353
    //   358: invokeinterface 755 3 0
    //   363: ldc_w 353
    //   366: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   369: ifne +26 -> 395
    //   372: new 440	org/json/JSONArray
    //   375: dup
    //   376: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   379: ldc -126
    //   381: ldc_w 353
    //   384: invokeinterface 755 3 0
    //   389: invokespecial 1372	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   392: putstatic 443	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   395: getstatic 836	android/os/Build$VERSION:SDK_INT	I
    //   398: bipush 14
    //   400: if_icmplt +1751 -> 2151
    //   403: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   406: ifne +1745 -> 2151
    //   409: new 714	java/lang/StringBuilder
    //   412: dup
    //   413: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   416: ldc_w 1374
    //   419: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   422: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   425: invokevirtual 828	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   428: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   431: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   434: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   437: checkcast 1376	android/app/Application
    //   440: new 69	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   443: dup
    //   444: aload_0
    //   445: invokespecial 1377	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   448: invokevirtual 1381	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   451: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   454: new 72	com/kochava/android/tracker/Feature$MemoryBoss
    //   457: dup
    //   458: aload_0
    //   459: invokespecial 1382	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   462: invokevirtual 1386	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   465: iconst_1
    //   466: putstatic 1389	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   469: iconst_1
    //   470: putstatic 1392	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   473: new 44	com/kochava/android/tracker/Feature$3
    //   476: dup
    //   477: aload_0
    //   478: invokespecial 1393	com/kochava/android/tracker/Feature$3:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   481: invokevirtual 866	java/lang/Thread:start	()V
    //   484: getstatic 380	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   487: ifne +14 -> 501
    //   490: new 46	com/kochava/android/tracker/Feature$4
    //   493: dup
    //   494: aload_0
    //   495: invokespecial 1394	com/kochava/android/tracker/Feature$4:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   498: invokevirtual 866	java/lang/Thread:start	()V
    //   501: aload_0
    //   502: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   505: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   508: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   511: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   514: iconst_0
    //   515: invokevirtual 1407	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   518: getfield 1412	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   521: putfield 1136	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   524: aload_0
    //   525: new 734	org/json/JSONObject
    //   528: dup
    //   529: invokespecial 882	org/json/JSONObject:<init>	()V
    //   532: putfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   535: aload_0
    //   536: new 734	org/json/JSONObject
    //   539: dup
    //   540: invokespecial 882	org/json/JSONObject:<init>	()V
    //   543: putfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   546: aload_0
    //   547: new 734	org/json/JSONObject
    //   550: dup
    //   551: invokespecial 882	org/json/JSONObject:<init>	()V
    //   554: putfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   557: aconst_null
    //   558: astore 13
    //   560: aconst_null
    //   561: astore 6
    //   563: aconst_null
    //   564: astore 9
    //   566: aconst_null
    //   567: astore 7
    //   569: ldc_w 893
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
    //   597: ldc_w 1420
    //   600: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   603: ifnull +38 -> 641
    //   606: aload 6
    //   608: astore 5
    //   610: aload_3
    //   611: ldc_w 1420
    //   614: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   617: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   620: ldc_w 480
    //   623: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   626: ifeq +15 -> 641
    //   629: aload_3
    //   630: ldc_w 1420
    //   633: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   636: checkcast 480	java/lang/String
    //   639: astore 5
    //   641: aload 7
    //   643: astore 6
    //   645: aload_3
    //   646: ldc -91
    //   648: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   651: ifnull +36 -> 687
    //   654: aload 7
    //   656: astore 6
    //   658: aload_3
    //   659: ldc -91
    //   661: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   664: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   667: ldc_w 480
    //   670: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   673: ifeq +14 -> 687
    //   676: aload_3
    //   677: ldc -91
    //   679: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   682: checkcast 480	java/lang/String
    //   685: astore 6
    //   687: aload_1
    //   688: astore 7
    //   690: aload_3
    //   691: ldc -46
    //   693: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   696: ifnull +35 -> 731
    //   699: aload_1
    //   700: astore 7
    //   702: aload_3
    //   703: ldc -46
    //   705: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   708: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   711: ldc_w 480
    //   714: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   717: ifeq +14 -> 731
    //   720: aload_3
    //   721: ldc -46
    //   723: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   726: checkcast 480	java/lang/String
    //   729: astore 7
    //   731: aload 8
    //   733: astore_1
    //   734: aload_3
    //   735: ldc_w 1422
    //   738: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   741: ifnull +33 -> 774
    //   744: aload_3
    //   745: ldc_w 1422
    //   748: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   751: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   754: ldc_w 480
    //   757: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   760: ifeq +1419 -> 2179
    //   763: aload_3
    //   764: ldc_w 1422
    //   767: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   770: checkcast 480	java/lang/String
    //   773: astore_1
    //   774: aload_3
    //   775: ldc_w 1424
    //   778: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   781: ifnull +34 -> 815
    //   784: aload_3
    //   785: ldc_w 1424
    //   788: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   791: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   794: ldc_w 480
    //   797: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   800: ifeq +15 -> 815
    //   803: aload_3
    //   804: ldc_w 1424
    //   807: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   810: checkcast 480	java/lang/String
    //   813: astore 8
    //   815: aload 14
    //   817: astore 8
    //   819: aload_3
    //   820: ldc_w 1426
    //   823: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   826: ifnull +38 -> 864
    //   829: aload 14
    //   831: astore 8
    //   833: aload_3
    //   834: ldc_w 1426
    //   837: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   843: ldc_w 480
    //   846: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   849: ifeq +15 -> 864
    //   852: aload_3
    //   853: ldc_w 1426
    //   856: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   859: checkcast 480	java/lang/String
    //   862: astore 8
    //   864: aload_3
    //   865: ldc_w 971
    //   868: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   871: ifnull +39 -> 910
    //   874: aload_3
    //   875: ldc_w 971
    //   878: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   881: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   884: ldc_w 903
    //   887: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   890: ifeq +20 -> 910
    //   893: aload_0
    //   894: aload_3
    //   895: ldc_w 971
    //   898: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   901: checkcast 903	java/lang/Boolean
    //   904: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   907: putfield 454	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   910: aload_3
    //   911: ldc_w 989
    //   914: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   917: ifnull +115 -> 1032
    //   920: aload_3
    //   921: ldc_w 989
    //   924: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   927: instanceof 477
    //   930: ifeq +102 -> 1032
    //   933: aload_3
    //   934: ldc_w 989
    //   937: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   940: checkcast 477	java/util/HashMap
    //   943: putstatic 1428	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   946: new 734	org/json/JSONObject
    //   949: dup
    //   950: invokespecial 882	org/json/JSONObject:<init>	()V
    //   953: putstatic 987	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   956: getstatic 1428	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   959: invokeinterface 1041 1 0
    //   964: invokeinterface 1044 1 0
    //   969: astore 9
    //   971: aload 9
    //   973: invokeinterface 784 1 0
    //   978: ifeq +54 -> 1032
    //   981: aload 9
    //   983: invokeinterface 788 1 0
    //   988: checkcast 1046	java/util/Map$Entry
    //   991: astore 10
    //   993: getstatic 987	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   996: aload 10
    //   998: invokeinterface 1049 1 0
    //   1003: checkcast 480	java/lang/String
    //   1006: aload 10
    //   1008: invokeinterface 1052 1 0
    //   1013: checkcast 480	java/lang/String
    //   1016: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1019: pop
    //   1020: aload 9
    //   1022: invokeinterface 1431 1 0
    //   1027: goto -56 -> 971
    //   1030: astore 9
    //   1032: aload_3
    //   1033: ldc_w 995
    //   1036: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1039: ifnull +36 -> 1075
    //   1042: aload_3
    //   1043: ldc_w 995
    //   1046: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1049: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1052: ldc_w 480
    //   1055: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1058: ifeq +17 -> 1075
    //   1061: aload_0
    //   1062: aload_3
    //   1063: ldc_w 995
    //   1066: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1069: checkcast 480	java/lang/String
    //   1072: putfield 991	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
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
    //   1095: ldc_w 1432
    //   1098: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1124: ldc_w 1432
    //   1127: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1130: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1133: ldc_w 1434
    //   1136: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1139: ifeq +77 -> 1216
    //   1142: aload_3
    //   1143: ldc_w 1432
    //   1146: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1149: checkcast 1434	java/lang/Integer
    //   1152: invokevirtual 1437	java/lang/Integer:intValue	()I
    //   1155: istore 4
    //   1157: iload 4
    //   1159: iconst_1
    //   1160: if_icmpge +1066 -> 2226
    //   1163: new 714	java/lang/StringBuilder
    //   1166: dup
    //   1167: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1170: ldc_w 1439
    //   1173: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: iload 4
    //   1178: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1181: ldc_w 1441
    //   1184: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1187: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1190: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1193: iconst_1
    //   1194: putstatic 363	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
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
    //   1223: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   1226: invokevirtual 488	java/lang/String:length	()I
    //   1229: ifeq +8 -> 1237
    //   1232: aload 11
    //   1234: putstatic 355	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1237: aload 10
    //   1239: ifnull +17 -> 1256
    //   1242: aload 10
    //   1244: ldc -1
    //   1246: invokevirtual 1444	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1249: ifeq +7 -> 1256
    //   1252: iconst_1
    //   1253: putstatic 411	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1256: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1259: ldc 116
    //   1261: iconst_0
    //   1262: invokevirtual 749	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1265: putstatic 601	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1268: aload 9
    //   1270: ifnull +1048 -> 2318
    //   1273: aload 9
    //   1275: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   1278: invokevirtual 488	java/lang/String:length	()I
    //   1281: ifeq +1037 -> 2318
    //   1284: aload 9
    //   1286: putstatic 706	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1289: aload_0
    //   1290: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1293: ldc -91
    //   1295: aload 9
    //   1297: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1300: pop
    //   1301: aload_0
    //   1302: getfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1305: ldc -91
    //   1307: aload 9
    //   1309: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1312: pop
    //   1313: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1316: ldc -49
    //   1318: ldc_w 353
    //   1321: invokeinterface 755 3 0
    //   1326: ldc_w 353
    //   1329: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1332: ifeq +25 -> 1357
    //   1335: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1338: invokeinterface 1235 1 0
    //   1343: ldc -49
    //   1345: aload 9
    //   1347: invokeinterface 1241 3 0
    //   1352: invokeinterface 1244 1 0
    //   1357: aload_0
    //   1358: aload 12
    //   1360: invokespecial 517	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1363: aload_0
    //   1364: getfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1367: ldc_w 1446
    //   1370: aload_0
    //   1371: invokespecial 962	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1374: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1377: pop
    //   1378: aload_0
    //   1379: getfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1382: ldc_w 1448
    //   1385: ldc_w 1450
    //   1388: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1391: pop
    //   1392: aload_0
    //   1393: getfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1396: ldc_w 1452
    //   1399: ldc_w 1454
    //   1402: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1405: pop
    //   1406: aload_0
    //   1407: getfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1410: ldc -46
    //   1412: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1415: ldc -46
    //   1417: ldc_w 893
    //   1420: invokeinterface 755 3 0
    //   1425: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1428: pop
    //   1429: aload_0
    //   1430: getfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1433: ldc -46
    //   1435: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1438: ldc -46
    //   1440: ldc_w 893
    //   1443: invokeinterface 755 3 0
    //   1448: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1451: pop
    //   1452: aload_0
    //   1453: getfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1456: ldc_w 1452
    //   1459: ldc_w 1454
    //   1462: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1465: pop
    //   1466: aload_0
    //   1467: getfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1470: ldc -46
    //   1472: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1475: ldc -46
    //   1477: ldc_w 893
    //   1480: invokeinterface 755 3 0
    //   1485: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1488: pop
    //   1489: aload_0
    //   1490: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1493: ldc_w 936
    //   1496: new 714	java/lang/StringBuilder
    //   1499: dup
    //   1500: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1503: ldc_w 938
    //   1506: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: getstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1512: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1515: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1518: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1521: pop
    //   1522: aload_0
    //   1523: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1526: ldc_w 1456
    //   1529: ldc_w 1458
    //   1532: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1535: pop
    //   1536: aload_0
    //   1537: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1540: ldc_w 1059
    //   1543: aload_0
    //   1544: getfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1547: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1550: pop
    //   1551: aload_0
    //   1552: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1555: ldc_w 1460
    //   1558: aload_0
    //   1559: getfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1562: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1565: pop
    //   1566: aload_0
    //   1567: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1570: ldc 92
    //   1572: ldc -99
    //   1574: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1577: pop
    //   1578: invokestatic 720	java/lang/System:currentTimeMillis	()J
    //   1581: ldc2_w 721
    //   1584: ldiv
    //   1585: putstatic 388	com/kochava/android/tracker/Feature:startTime	J
    //   1588: iconst_0
    //   1589: istore 4
    //   1591: ldc_w 353
    //   1594: astore_3
    //   1595: new 795	android/content/ComponentName
    //   1598: dup
    //   1599: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1602: ldc_w 1462
    //   1605: invokespecial 807	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1608: astore_1
    //   1609: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1612: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1615: aload_1
    //   1616: iconst_0
    //   1617: invokevirtual 1466	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1620: pop
    //   1621: ldc_w 1468
    //   1624: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1627: aload_3
    //   1628: astore_1
    //   1629: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1632: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1635: ldc_w 1470
    //   1638: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1641: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1644: invokevirtual 1473	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1647: ifge +27 -> 1674
    //   1650: iconst_1
    //   1651: istore 4
    //   1653: new 714	java/lang/StringBuilder
    //   1656: dup
    //   1657: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1660: aload_3
    //   1661: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1664: ldc_w 1475
    //   1667: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1670: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1673: astore_1
    //   1674: aload_1
    //   1675: astore_3
    //   1676: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1679: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1682: ldc_w 1477
    //   1685: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1688: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1691: invokevirtual 1473	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1694: ifge +27 -> 1721
    //   1697: iconst_1
    //   1698: istore 4
    //   1700: new 714	java/lang/StringBuilder
    //   1703: dup
    //   1704: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1707: aload_1
    //   1708: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1711: ldc_w 1479
    //   1714: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1717: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1720: astore_3
    //   1721: aload_3
    //   1722: astore_1
    //   1723: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1726: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1729: ldc_w 1481
    //   1732: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1735: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1738: invokevirtual 1473	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1741: ifge +27 -> 1768
    //   1744: iconst_1
    //   1745: istore 4
    //   1747: new 714	java/lang/StringBuilder
    //   1750: dup
    //   1751: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1754: aload_3
    //   1755: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1758: ldc_w 1483
    //   1761: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1764: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1767: astore_1
    //   1768: iload 4
    //   1770: ifeq +13 -> 1783
    //   1773: ldc_w 1485
    //   1776: invokestatic 1488	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1779: aload_1
    //   1780: invokestatic 1488	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1783: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1786: ldc -25
    //   1788: ldc_w 353
    //   1791: invokeinterface 755 3 0
    //   1796: ldc_w 353
    //   1799: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1802: ifeq +27 -> 1829
    //   1805: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1808: invokeinterface 1235 1 0
    //   1813: ldc -25
    //   1815: aload_0
    //   1816: invokespecial 624	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1819: invokeinterface 1241 3 0
    //   1824: invokeinterface 1244 1 0
    //   1829: aload_0
    //   1830: ldc_w 1490
    //   1833: putfield 1140	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1836: aload_0
    //   1837: ldc_w 1492
    //   1840: putfield 1144	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1843: aload_0
    //   1844: ldc_w 353
    //   1847: putfield 608	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1850: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1853: invokevirtual 1338	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1856: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1859: astore_3
    //   1860: aload_3
    //   1861: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1864: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1867: iconst_0
    //   1868: invokevirtual 1496	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1871: astore_1
    //   1872: aload_1
    //   1873: ifnull +728 -> 2601
    //   1876: aload_3
    //   1877: aload_1
    //   1878: invokevirtual 1500	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1881: astore_1
    //   1882: aload_0
    //   1883: aload_1
    //   1884: checkcast 480	java/lang/String
    //   1887: checkcast 480	java/lang/String
    //   1890: putfield 1140	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1893: new 714	java/lang/StringBuilder
    //   1896: dup
    //   1897: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1900: ldc_w 1502
    //   1903: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1906: aload_0
    //   1907: getfield 1140	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1910: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1913: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1916: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1919: aload_0
    //   1920: new 714	java/lang/StringBuilder
    //   1923: dup
    //   1924: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1927: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1930: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1933: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1936: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1939: iconst_0
    //   1940: invokevirtual 1407	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1943: getfield 1505	android/content/pm/PackageInfo:versionCode	I
    //   1946: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1949: ldc_w 353
    //   1952: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1955: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1958: putfield 1144	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1961: new 714	java/lang/StringBuilder
    //   1964: dup
    //   1965: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1968: ldc_w 1507
    //   1971: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1974: aload_0
    //   1975: getfield 1144	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1978: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1981: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1984: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1987: aload_0
    //   1988: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1991: invokevirtual 1398	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1994: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1997: invokevirtual 1401	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2000: iconst_0
    //   2001: invokevirtual 1407	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2004: getfield 1510	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2007: putfield 608	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2010: new 714	java/lang/StringBuilder
    //   2013: dup
    //   2014: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2017: ldc_w 1512
    //   2020: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2023: aload_0
    //   2024: getfield 608	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2027: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2030: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2033: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2036: new 48	com/kochava/android/tracker/Feature$5
    //   2039: dup
    //   2040: aload_0
    //   2041: invokespecial 1513	com/kochava/android/tracker/Feature$5:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   2044: invokevirtual 866	java/lang/Thread:start	()V
    //   2047: aload_0
    //   2048: invokestatic 597	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2051: putfield 1515	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2054: aload_0
    //   2055: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2058: ldc_w 884
    //   2061: invokestatic 597	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2064: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2067: pop
    //   2068: getstatic 425	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2071: aload_0
    //   2072: getfield 461	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2075: ldc2_w 1516
    //   2078: getstatic 1523	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2081: invokeinterface 1528 5 0
    //   2086: pop
    //   2087: aload_0
    //   2088: ldc -99
    //   2090: invokevirtual 1531	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2093: return
    //   2094: astore_1
    //   2095: ldc_w 1533
    //   2098: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2101: aload_1
    //   2102: invokevirtual 1035	java/lang/Exception:printStackTrace	()V
    //   2105: iconst_1
    //   2106: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2109: return
    //   2110: ldc_w 1535
    //   2113: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2116: iconst_1
    //   2117: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2120: return
    //   2121: astore_1
    //   2122: new 714	java/lang/StringBuilder
    //   2125: dup
    //   2126: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2129: ldc_w 1537
    //   2132: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2135: aload_1
    //   2136: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2139: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2142: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2145: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2148: goto -1753 -> 395
    //   2151: new 714	java/lang/StringBuilder
    //   2154: dup
    //   2155: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2158: ldc_w 1539
    //   2161: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2164: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2167: invokevirtual 828	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2170: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2173: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2176: goto -1703 -> 473
    //   2179: aload 8
    //   2181: astore_1
    //   2182: aload_3
    //   2183: ldc_w 1422
    //   2186: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2189: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2192: ldc_w 903
    //   2195: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2198: ifeq -1424 -> 774
    //   2201: aload 8
    //   2203: astore_1
    //   2204: aload_3
    //   2205: ldc_w 1422
    //   2208: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2211: checkcast 903	java/lang/Boolean
    //   2214: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   2217: ifeq -1443 -> 774
    //   2220: ldc -1
    //   2222: astore_1
    //   2223: goto -1449 -> 774
    //   2226: iload 4
    //   2228: sipush 360
    //   2231: if_icmple +42 -> 2273
    //   2234: new 714	java/lang/StringBuilder
    //   2237: dup
    //   2238: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2241: ldc_w 1439
    //   2244: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2247: iload 4
    //   2249: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2252: ldc_w 1541
    //   2255: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2258: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2261: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2264: ldc_w 1542
    //   2267: putstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   2270: goto -1077 -> 1193
    //   2273: iload 4
    //   2275: bipush 60
    //   2277: imul
    //   2278: sipush 1000
    //   2281: imul
    //   2282: putstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   2285: new 714	java/lang/StringBuilder
    //   2288: dup
    //   2289: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2292: ldc_w 1544
    //   2295: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2298: iload 4
    //   2300: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2303: ldc_w 1546
    //   2306: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2309: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2312: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2315: goto -1122 -> 1193
    //   2318: aload 13
    //   2320: ifnull +176 -> 2496
    //   2323: aload 13
    //   2325: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   2328: invokevirtual 488	java/lang/String:length	()I
    //   2331: ifeq +165 -> 2496
    //   2334: aload_0
    //   2335: getfield 1414	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2338: ldc_w 1420
    //   2341: aload 13
    //   2343: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2346: pop
    //   2347: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2350: ldc -49
    //   2352: ldc_w 353
    //   2355: invokeinterface 755 3 0
    //   2360: ldc_w 353
    //   2363: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2366: ifeq +25 -> 2391
    //   2369: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2372: invokeinterface 1235 1 0
    //   2377: ldc -49
    //   2379: aload 13
    //   2381: invokeinterface 1241 3 0
    //   2386: invokeinterface 1244 1 0
    //   2391: aload 9
    //   2393: ifnull +76 -> 2469
    //   2396: aload 9
    //   2398: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   2401: invokevirtual 488	java/lang/String:length	()I
    //   2404: ifeq +65 -> 2469
    //   2407: aload 9
    //   2409: putstatic 706	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2412: aload_0
    //   2413: getfield 1416	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2416: ldc -91
    //   2418: aload 9
    //   2420: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2423: pop
    //   2424: aload_0
    //   2425: getfield 1418	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2428: ldc -91
    //   2430: aload 9
    //   2432: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2435: pop
    //   2436: goto -1079 -> 1357
    //   2439: astore_1
    //   2440: new 714	java/lang/StringBuilder
    //   2443: dup
    //   2444: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2447: ldc_w 1548
    //   2450: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2453: aload_1
    //   2454: invokevirtual 1549	org/json/JSONException:toString	()Ljava/lang/String;
    //   2457: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2460: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2463: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2466: goto -888 -> 1578
    //   2469: new 714	java/lang/StringBuilder
    //   2472: dup
    //   2473: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2476: ldc_w 1551
    //   2479: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2482: aload 13
    //   2484: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2487: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2490: putstatic 706	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2493: goto -1136 -> 1357
    //   2496: ldc_w 1553
    //   2499: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2502: iconst_1
    //   2503: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2506: return
    //   2507: astore_1
    //   2508: iconst_1
    //   2509: istore 4
    //   2511: new 714	java/lang/StringBuilder
    //   2514: dup
    //   2515: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2518: ldc_w 353
    //   2521: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2524: ldc_w 1555
    //   2527: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2530: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2533: astore_3
    //   2534: goto -907 -> 1627
    //   2537: astore 5
    //   2539: aconst_null
    //   2540: astore_1
    //   2541: new 714	java/lang/StringBuilder
    //   2544: dup
    //   2545: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2548: ldc_w 1557
    //   2551: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2554: aload 5
    //   2556: invokevirtual 1558	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2559: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2562: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2565: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2568: goto -696 -> 1872
    //   2571: astore_1
    //   2572: new 714	java/lang/StringBuilder
    //   2575: dup
    //   2576: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2579: ldc_w 1557
    //   2582: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2585: aload_1
    //   2586: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2589: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2592: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2595: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2598: goto -679 -> 1919
    //   2601: ldc_w 1560
    //   2604: astore_1
    //   2605: goto -723 -> 1882
    //   2608: astore_1
    //   2609: new 714	java/lang/StringBuilder
    //   2612: dup
    //   2613: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2616: ldc_w 1562
    //   2619: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2622: aload_1
    //   2623: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2626: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2629: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2632: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2635: goto -648 -> 1987
    //   2638: astore_1
    //   2639: new 714	java/lang/StringBuilder
    //   2642: dup
    //   2643: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2646: ldc_w 1564
    //   2649: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2652: aload_1
    //   2653: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2656: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2659: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2662: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2665: goto -629 -> 2036
    //   2668: astore_1
    //   2669: getstatic 929	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   2672: ifeq -604 -> 2068
    //   2675: aload_1
    //   2676: invokevirtual 932	org/json/JSONException:printStackTrace	()V
    //   2679: goto -611 -> 2068
    //   2682: astore_1
    //   2683: goto -2159 -> 524
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2686	0	this	Feature
    //   0	2686	1	paramContext	Context
    //   0	2686	2	paramBoolean	boolean
    //   0	2686	3	paramHashMap	HashMap<String, Object>
    //   1155	1355	4	i	int
    //   594	604	5	localObject1	Object
    //   2537	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   561	652	6	localObject2	Object
    //   567	635	7	localObject3	Object
    //   577	1625	8	localObject4	Object
    //   564	457	9	localIterator	Iterator
    //   1030	1	9	localException	Exception
    //   1077	1354	9	localObject5	Object
    //   574	669	10	localObject6	Object
    //   580	653	11	localObject7	Object
    //   586	773	12	localObject8	Object
    //   558	1925	13	localObject9	Object
    //   583	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   933	971	1030	java/lang/Exception
    //   971	1027	1030	java/lang/Exception
    //   4	11	2094	java/lang/Exception
    //   372	395	2121	java/lang/Exception
    //   1273	1357	2439	org/json/JSONException
    //   1357	1578	2439	org/json/JSONException
    //   2323	2391	2439	org/json/JSONException
    //   2396	2436	2439	org/json/JSONException
    //   2469	2493	2439	org/json/JSONException
    //   2496	2506	2439	org/json/JSONException
    //   1609	1627	2507	android/content/pm/PackageManager$NameNotFoundException
    //   1860	1872	2537	android/content/pm/PackageManager$NameNotFoundException
    //   1850	1860	2571	java/lang/Exception
    //   1860	1872	2571	java/lang/Exception
    //   1876	1882	2571	java/lang/Exception
    //   1882	1919	2571	java/lang/Exception
    //   2541	2568	2571	java/lang/Exception
    //   1919	1987	2608	java/lang/Exception
    //   1987	2036	2638	java/lang/Exception
    //   2054	2068	2668	org/json/JSONException
    //   501	524	2682	java/lang/Exception
  }
  
  private void initHandler()
  {
    if (this.initHandler == null) {
      this.initHandler = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          boolean bool = paramAnonymousMessage.getData().getBoolean("sendonstart");
          Feature.this.initialHandlerActions();
          if (!bool)
          {
            Feature.this.initTimer.schedule(new TimerTask()
            {
              public void run()
              {
                Logging.Log("Reached 10 min mark w/o sending initial, sending now.");
                Feature.this.queInitial(false);
              }
            }, 600000L);
            return;
          }
          Feature.this.initTimer.schedule(new TimerTask()
          {
            public void run()
            {
              Logging.Log("Scheduling timer to que initial event if needed.");
              Feature.this.queInitial(false);
            }
          }, 2000L);
          Feature.access$5402(Feature.this, new Timer());
          Feature.this.mTimerSendOnBegin.schedule(new TimerTask()
          {
            public void run() {}
          }, 4000L);
        }
      };
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
        fireEvent("initial", null, null);
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
        mTimer.schedule(new TimerTask()
        {
          public void run()
          {
            if ((Feature.flushTimerSetByKVinitResponse) && (System.currentTimeMillis() - Feature.lastEventTimestamp < Feature.flush_rate))
            {
              Logging.Log("Too soon since last event, flush timer waiting for next flush attempt.");
              return;
            }
            Feature.flush();
          }
        }, 0L, flush_rate);
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
            break label674;
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
      label674:
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
    new Thread()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        try
        {
          Object localObject1 = new JSONObject();
          Object localObject3 = new JSONObject();
          Feature.addGlobalProperties((JSONObject)localObject3);
          if (this.val$pushCommand.has("register"))
          {
            ((JSONObject)localObject3).put("token", this.val$pushCommand.get("register").toString());
            ((JSONObject)localObject1).put("push_action", "register_token");
          }
          if (this.val$pushCommand.has("remove")) {
            ((JSONObject)localObject1).put("push_action", "remove_token");
          }
          ((JSONObject)localObject1).put("action", "push");
          ((JSONObject)localObject1).put("data", localObject3);
          ((JSONObject)localObject1).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject1).put("kochava_device_id", Feature.access$3400());
          ((JSONObject)localObject1).put("sdk_version", "Android20160615" + Feature.versionExtension);
          ((JSONObject)localObject1).put("sdk_protocol", "4");
          if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty()))
          {
            Logging.Log("Push token - hostControl was empty, using default");
            Feature.access$602("control.kochava.com");
          }
          Logging.Log("kvPush - posting to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject3 = new URL("https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject1).toString();
          Logging.Log("kvPush data:" + (String)localObject1);
          localObject3 = (HttpsURLConnection)((URL)localObject3).openConnection();
          ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
          ((HttpsURLConnection)localObject3).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpsURLConnection)localObject3).setRequestMethod("POST");
          ((HttpsURLConnection)localObject3).setConnectTimeout(30000);
          ((HttpsURLConnection)localObject3).setReadTimeout(30000);
          ((HttpsURLConnection)localObject3).setDoInput(true);
          ((HttpsURLConnection)localObject3).setDoOutput(true);
          ((HttpsURLConnection)localObject3).connect();
          Object localObject4 = new OutputStreamWriter(((HttpsURLConnection)localObject3).getOutputStream());
          ((OutputStreamWriter)localObject4).write((String)localObject1);
          ((OutputStreamWriter)localObject4).close();
          Logging.Log("Grabbing Result...");
          localObject1 = new StringBuffer("");
          localObject3 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject3).getInputStream()));
          for (;;)
          {
            localObject4 = ((BufferedReader)localObject3).readLine();
            if (localObject4 == null) {
              break;
            }
            ((StringBuffer)localObject1).append((String)localObject4);
          }
          localObject2 = localException.toString();
        }
        catch (Exception localException)
        {
          Logging.LogError("Problem sending register/remove token, saving push command: " + this.val$pushCommand.toString());
          Feature.prefs.edit().putString("register_remove_token", this.val$pushCommand.toString()).apply();
          if (Global.DEBUGERROR) {
            localException.printStackTrace();
          }
          return;
        }
        Logging.Log("Result: " + (String)localObject2);
        Object localObject2 = new JSONObject((String)localObject2);
        if ((localObject2 != null) && (((JSONObject)localObject2).get("success").toString().equals("1")))
        {
          Feature.prefs.edit().remove("register_remove_token").apply();
          return;
        }
        Logging.LogError("Did not get a good response, saving push command: " + this.val$pushCommand.toString());
        Feature.prefs.edit().putString("register_remove_token", this.val$pushCommand.toString()).apply();
      }
    }.start();
  }
  
  protected static void sendKVQuery(int paramInt)
  {
    worker.schedule(sendKVQuery, paramInt, TimeUnit.SECONDS);
  }
  
  private static void sendOutsideServicesUpdate(JSONArray paramJSONArray)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          Object localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("action", "update");
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$3400());
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20160615" + Feature.versionExtension);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("outside_services", this.val$services_found);
          ((JSONObject)localObject2).put("data", localObject1);
          if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
            Feature.access$602("control.kochava.com");
          }
          Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject1 = (HttpsURLConnection)new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection();
          ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
          ((HttpsURLConnection)localObject1).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpsURLConnection)localObject1).setRequestMethod("POST");
          ((HttpsURLConnection)localObject1).setConnectTimeout(30000);
          ((HttpsURLConnection)localObject1).setReadTimeout(30000);
          ((HttpsURLConnection)localObject1).setDoInput(true);
          ((HttpsURLConnection)localObject1).setDoOutput(true);
          ((HttpsURLConnection)localObject1).connect();
          String str2 = ((JSONObject)localObject2).toString();
          Logging.Log("Trying to post an update: " + ((JSONObject)localObject2).toString());
          localObject2 = new OutputStreamWriter(((HttpsURLConnection)localObject1).getOutputStream());
          ((OutputStreamWriter)localObject2).write(str2);
          ((OutputStreamWriter)localObject2).close();
          Logging.Log("(Update) Grabbing Result...");
          localObject2 = new StringBuffer("");
          localObject1 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject1).getInputStream()));
          for (;;)
          {
            str2 = ((BufferedReader)localObject1).readLine();
            if (str2 == null) {
              break;
            }
            ((StringBuffer)localObject2).append(str2);
          }
          str1 = ((StringBuffer)localObject2).toString();
        }
        catch (Exception localException)
        {
          Logging.LogError("Update error: " + localException.toString());
          return;
        }
        String str1;
        Logging.Log("Update Result: " + str1);
      }
    }.start();
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
    //   0: getstatic 836	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +259 -> 264
    //   8: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +253 -> 264
    //   14: getstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 119
    //   22: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1791
    //   29: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 397	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 840	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +188 -> 229
    //   44: ldc_w 1793
    //   47: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 847	java/util/Timer
    //   53: dup
    //   54: invokespecial 1347	java/util/Timer:<init>	()V
    //   57: putstatic 840	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 840	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 8	com/kochava/android/tracker/Feature$10
    //   66: dup
    //   67: invokespecial 1794	com/kochava/android/tracker/Feature$10:<init>	()V
    //   70: getstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1585	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 720	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 721
    //   87: ldiv
    //   88: putstatic 388	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 384	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 572	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1798	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1801	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +50 -> 165
    //   118: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -22
    //   123: ldc_w 353
    //   126: invokeinterface 755 3 0
    //   131: ldc_w 353
    //   134: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 734	org/json/JSONObject
    //   145: dup
    //   146: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc -22
    //   151: ldc_w 353
    //   154: invokeinterface 755 3 0
    //   159: invokespecial 1802	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1804	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +96 -> 264
    //   171: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc -43
    //   176: ldc_w 353
    //   179: invokeinterface 755 3 0
    //   184: ldc -1
    //   186: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   189: ifeq +75 -> 264
    //   192: getstatic 407	com/kochava/android/tracker/Feature:canSendSession	Z
    //   195: ifeq +63 -> 258
    //   198: ldc -65
    //   200: invokestatic 853	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   203: return
    //   204: astore_1
    //   205: new 714	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   212: ldc_w 1806
    //   215: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual 770	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   228: return
    //   229: ldc_w 1808
    //   232: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   235: goto -154 -> 81
    //   238: astore_1
    //   239: ldc_w 1810
    //   242: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   245: getstatic 929	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   248: ifeq -83 -> 165
    //   251: aload_1
    //   252: invokevirtual 932	org/json/JSONException:printStackTrace	()V
    //   255: goto -90 -> 165
    //   258: ldc_w 859
    //   261: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
  
  public void deepLinkEvent(final String paramString)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    new Thread()
    {
      public void run()
      {
        try
        {
          Logging.Log("Got deep link event with uri" + paramString);
          JSONObject localJSONObject1 = new JSONObject();
          localJSONObject1.put("action", "deeplink");
          localJSONObject1.put("kochava_app_id", Feature.mAppId);
          localJSONObject1.put("kochava_device_id", Feature.access$3400());
          JSONObject localJSONObject2 = new JSONObject();
          localJSONObject2.put("uri", paramString);
          localJSONObject2.put("usertime", System.currentTimeMillis() / 1000L + "");
          localJSONObject1.put("data", localJSONObject2);
          int i = Feature.kDbAdapter.addEvent(localJSONObject1, false, false);
          Logging.Log("deep link event: " + localJSONObject1);
          if (i >= 50) {
            Feature.flush();
          }
          return;
        }
        catch (Exception localException)
        {
          Logging.LogError("Error in deep link event call: " + localException);
        }
      }
    }.start();
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
  
  public void event(final String paramString1, final String paramString2)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          Logging.Log("Got event " + paramString1);
          HashMap localHashMap = new HashMap();
          localHashMap.put("event_name", paramString1);
          localHashMap.put("event_data", paramString2);
          Feature.this.fireEventBlacklist("event", localHashMap, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      Logging.LogError("Error in event call: " + paramString1);
    }
  }
  
  public void eventSpatial(final String paramString1, final double paramDouble1, double paramDouble2, final double paramDouble3, String paramString2)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          Logging.Log("Got spatial event " + paramString1);
          HashMap localHashMap = new HashMap();
          localHashMap.put("event_name", paramString1);
          DecimalFormat localDecimalFormat = new DecimalFormat("#.##");
          localHashMap.put("x", Double.valueOf(localDecimalFormat.format(paramDouble1)).toString());
          localHashMap.put("y", Double.valueOf(localDecimalFormat.format(paramDouble3)).toString());
          localHashMap.put("z", Double.valueOf(localDecimalFormat.format(this.val$z)).toString());
          localHashMap.put("event_data", this.val$eventData);
          Feature.this.fireEventBlacklist("spatial", localHashMap, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      Logging.LogError("Error in spatial event call: " + paramString1);
    }
  }
  
  public void eventWithReceipt(final String paramString1, final String paramString2, final Map<String, String> paramMap)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          Logging.Log("Got event with receipt " + paramString1);
          HashMap localHashMap1 = new HashMap();
          localHashMap1.put("event_name", paramString1);
          localHashMap1.put("event_data", paramString2);
          if (paramMap != null)
          {
            HashMap localHashMap2 = new HashMap();
            if (paramMap.get("purchaseData") != null) {
              localHashMap2.put("purchaseData", paramMap.get("purchaseData"));
            }
            if (paramMap.get("dataSignature") != null) {
              localHashMap2.put("dataSignature", paramMap.get("dataSignature"));
            }
            if (localHashMap2.size() > 0)
            {
              Feature.this.fireEventBlacklist("event", localHashMap1, localHashMap2);
              return;
            }
            Logging.Log("eventWithReceipt - receipt did not contain basic objects - sending event w/o receipt");
            Feature.this.fireEventBlacklist("event", localHashMap1, null);
            return;
          }
          Logging.Log("eventWithReceipt - receipt is null - sending event w/o receipt");
          Feature.this.fireEventBlacklist("event", localHashMap1, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      Logging.LogError("Error in event with receipt call: " + paramString1);
    }
  }
  
  public void linkIdentity(final Map<String, String> paramMap)
  {
    if (badInit)
    {
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    new Thread()
    {
      public void run()
      {
        Logging.Log("Mapping identity");
        Feature.this.fireEvent("identityLink", paramMap, null);
      }
    }.start();
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
            mTimer.schedule(new TimerTask()
            {
              public void run() {}
            }, flush_rate, flush_rate);
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
  
  protected void tryUpdate(final String paramString)
  {
    paramString = new Runnable()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        Logging.Log("Checking watchlist from " + paramString + "...");
        Object localObject2 = new HashMap();
        if (!Feature.prefs.contains("app_short_string"))
        {
          Logging.Log("No previous app_short_string in watchlist, adding " + Feature.this.mAppVersionName);
          Feature.prefs.edit().putString("app_short_string", Feature.this.mAppVersionName).apply();
          if (Feature.prefs.contains("app_limit_tracking")) {
            break label723;
          }
          Logging.Log("No previous app_limit_tracking in watchlist, adding " + Feature.this.app_limit_tracking);
          Feature.prefs.edit().putBoolean("app_limit_tracking", Feature.this.app_limit_tracking).apply();
          label174:
          if (Feature.prefs.contains("app_version")) {
            break label834;
          }
          Logging.Log("No previous app_version in watchlist, adding " + Feature.this.getAppVersion());
          Feature.prefs.edit().putString("app_version", Feature.this.getAppVersion()).apply();
          label242:
          if (Feature.prefs.contains("device_limit_tracking")) {
            break label949;
          }
          Logging.Log("No previous device_limit_tracking in watchlist, adding " + Feature.device_limit_tracking);
          Feature.prefs.edit().putBoolean("device_limit_tracking", Feature.device_limit_tracking).apply();
          label302:
          if (Feature.send_id_updates)
          {
            if (Feature.prefs.contains("adid")) {
              break label1044;
            }
            Logging.Log("No previous adid in watchlist, adding " + Feature.advertisingID);
            Feature.prefs.edit().putString("adid", Feature.advertisingID).apply();
            ((HashMap)localObject2).put("adid", Feature.advertisingID);
          }
          label378:
          if (Feature.prefs.contains("os_version")) {
            break label1125;
          }
          Logging.Log("No previous os_version in watchlist, adding " + Feature.access$4000());
          Feature.prefs.edit().putString("os_version", Feature.access$4000()).apply();
        }
        for (;;)
        {
          if (!((HashMap)localObject2).keySet().isEmpty()) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("action", "update");
              localJSONObject.put("kochava_device_id", Feature.access$3400());
              localJSONObject.put("kochava_app_id", Feature.mAppId);
              localJSONObject.put("sdk_version", "Android20160615" + Feature.versionExtension);
              localJSONObject.put("sdk_protocol", "4");
              localObject3 = new JSONObject();
              Iterator localIterator = ((HashMap)localObject2).keySet().iterator();
              while (localIterator.hasNext())
              {
                String str = (String)localIterator.next();
                ((JSONObject)localObject3).put(str, ((HashMap)localObject2).get(str));
              }
              return;
            }
            catch (Exception localException)
            {
              Logging.LogError("Update error: " + localException.toString());
            }
          }
          if (Feature.prefs.getString("app_short_string", "").equals(Feature.this.mAppVersionName)) {
            break;
          }
          Logging.Log("app_short_string changed! Is now " + Feature.this.mAppVersionName);
          ((HashMap)localObject2).put("app_short_string", Feature.this.mAppVersionName + "");
          Feature.prefs.edit().putString("app_short_string", Feature.this.mAppVersionName).apply();
          break;
          label723:
          if (Feature.prefs.getBoolean("app_limit_tracking", false) == Feature.this.app_limit_tracking) {
            break label174;
          }
          Logging.Log("app_limit_tracking changed! Is now " + Feature.this.app_limit_tracking);
          ((HashMap)localObject2).put("app_limit_tracking", Feature.this.app_limit_tracking + "");
          Feature.prefs.edit().putBoolean("app_limit_tracking", Feature.this.app_limit_tracking).apply();
          break label174;
          label834:
          if (Feature.prefs.getString("app_version", "").equals(Feature.this.getAppVersion())) {
            break label242;
          }
          Logging.Log("app_version changed! Is now " + Feature.this.getAppVersion());
          ((HashMap)localObject2).put("app_version", Feature.this.getAppVersion() + "");
          Feature.prefs.edit().putString("app_version", Feature.this.getAppVersion()).apply();
          break label242;
          label949:
          if (Feature.prefs.getBoolean("device_limit_tracking", false) == Feature.device_limit_tracking) {
            break label302;
          }
          Logging.Log("device_limit_tracking changed! Is now " + Feature.device_limit_tracking);
          ((HashMap)localObject2).put("device_limit_tracking", Feature.device_limit_tracking + "");
          Feature.prefs.edit().putBoolean("device_limit_tracking", Feature.device_limit_tracking).apply();
          break label302;
          label1044:
          if (Feature.prefs.getString("adid", "").equals(Feature.advertisingID)) {
            break label378;
          }
          Logging.Log("adid changed! Is now " + Feature.advertisingID);
          ((HashMap)localObject2).put("adid", Feature.advertisingID);
          Feature.prefs.edit().putString("adid", Feature.advertisingID).apply();
          break label378;
          label1125:
          if (!Feature.prefs.getString("os_version", "").equals(Feature.access$4000()))
          {
            Logging.Log("os_version changed! Is now " + Feature.access$4000());
            ((HashMap)localObject2).put("os_version", Feature.access$4000());
            Feature.prefs.edit().putString("os_version", Feature.access$4000()).apply();
            Feature.prefs.edit().putString("useragent", Feature.this.getUserAgent()).apply();
          }
        }
        localException.put("data", localObject3);
        if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
          Feature.access$602("control.kochava.com");
        }
        Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
        localObject2 = (HttpsURLConnection)new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection();
        ((HttpsURLConnection)localObject2).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
        ((HttpsURLConnection)localObject2).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        ((HttpsURLConnection)localObject2).setRequestMethod("POST");
        ((HttpsURLConnection)localObject2).setConnectTimeout(30000);
        ((HttpsURLConnection)localObject2).setReadTimeout(30000);
        ((HttpsURLConnection)localObject2).setDoInput(true);
        ((HttpsURLConnection)localObject2).setDoOutput(true);
        ((HttpsURLConnection)localObject2).connect();
        Object localObject3 = localException.toString();
        Logging.Log("Trying to post an update: " + localException.toString());
        Object localObject1 = new OutputStreamWriter(((HttpsURLConnection)localObject2).getOutputStream());
        ((OutputStreamWriter)localObject1).write((String)localObject3);
        ((OutputStreamWriter)localObject1).close();
        Logging.Log("(Update) Grabbing Result...");
        localObject1 = new StringBuffer("");
        localObject2 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject2).getInputStream()));
        for (;;)
        {
          localObject3 = ((BufferedReader)localObject2).readLine();
          if (localObject3 == null) {
            break;
          }
          ((StringBuffer)localObject1).append((String)localObject3);
        }
        localObject1 = ((StringBuffer)localObject1).toString();
        Logging.Log("Update Result: " + (String)localObject1);
      }
    };
    worker.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
  
  protected static class AppLifeCycleStatusManager
  {
    protected static final String IS_FOCUSED = "is_focused";
    protected static final String IS_IN_BACKGROUND = "is_in_background";
    protected static boolean active = false;
    protected static boolean resumed = false;
    
    protected AppLifeCycleStatusManager() {}
    
    protected static void changeStatus(String paramString)
    {
      new Thread()
      {
        public void run()
        {
          if (!Feature.AppLifeCycleStatusManager.active) {
            Logging.Log("AppLifeCycleStatusManager - not active");
          }
          do
          {
            return;
            if (this.val$status.equals("is_focused"))
            {
              if (!Feature.AppLifeCycleStatusManager.resumed)
              {
                Logging.Log("AppLifeCycleStatusManager - not already resumed, starting session...");
                Feature.access$5700();
                Feature.AppLifeCycleStatusManager.resumed = true;
                return;
              }
              Logging.Log("AppLifeCycleStatusManager - IS_FOCUSED received, App is already in focused state.");
              return;
            }
          } while (!this.val$status.equals("is_in_background"));
          if (Feature.AppLifeCycleStatusManager.resumed)
          {
            Logging.Log("AppLifeCycleStatusManager - going to background from app, ending session");
            Feature.access$5800();
            Feature.AppLifeCycleStatusManager.resumed = false;
            return;
          }
          Logging.Log("AppLifeCycleStatusManager - IS_IN_BACKGROUND received, App is already in background state.");
        }
      }.start();
    }
  }
  
  protected class ApplicationInfoHolder
  {
    public String appInstallTime = "";
    public String appPackage = "";
    public String appUpdateTime = "";
    
    public ApplicationInfoHolder(String paramString1, String paramString2, String paramString3)
    {
      this.appPackage = paramString1;
      this.appInstallTime = paramString2;
      this.appUpdateTime = paramString3;
    }
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
  
  @TargetApi(14)
  protected class LifeCycleTracker
    implements Application.ActivityLifecycleCallbacks
  {
    protected LifeCycleTracker() {}
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity)
    {
      Logging.Log("LifeCycleTracker - Tracking Activity lost focus");
      Feature.AppLifeCycleStatusManager.changeStatus("is_in_background");
      Feature.access$4802(true);
    }
    
    public void onActivityResumed(Activity paramActivity)
    {
      Logging.Log("LifeCycleTracker - Tracking Activity Resumed");
      Feature.AppLifeCycleStatusManager.changeStatus("is_focused");
      Feature.access$4802(false);
    }
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity) {}
    
    public void onActivityStopped(Activity paramActivity) {}
  }
  
  @TargetApi(14)
  protected class MemoryBoss
    implements ComponentCallbacks2
  {
    protected MemoryBoss() {}
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory() {}
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt == 20)
      {
        Logging.Log("MemoryBoss - Tracking Activity lost focus");
        Feature.AppLifeCycleStatusManager.changeStatus("is_in_background");
        Feature.access$4802(true);
      }
    }
  }
  
  public final class PARAMS
  {
    public static final String ADID = "adid";
    public static final String AFFINITY_GROUP = "affinity_group";
    public static final String ANDROID_ID = "android_id";
    public static final String BSSID = "bssid";
    public static final String CARRIER_NAME = "carrier_name";
    public static final String FB_ATTRIBUTION_ID = "fb_attribution_id";
    public static final String OPEN_UDID = "open_udid";
    public static final String SCREEN_SIZE = "screen_size";
    public static final String VOLUME = "volume";
    
    private PARAMS() {}
  }
  
  private final class PRIVATEINPUTITEMS
  {
    public static final String CLICK_DATA = "clickData";
    public static final String FLUSH_RATE = "flush_rate";
    public static final String OVERRIDE_AUTOMATIC_SESSIONS = "override_automatic_sessions";
    public static final String SUPPRESS_ADID_GATHER = "suppress_adid_gather";
    public static final String VERSION_EXTENSION = "version_extension";
    
    private PRIVATEINPUTITEMS() {}
  }
  
  private static class TrackTask
    implements Runnable
  {
    private TrackTask() {}
    
    public void run()
    {
      Feature.access$5600();
    }
  }
  
  public final class WATCHLIST
  {
    public static final String ADID = "adid";
    public static final String APP_LIMIT_TRACKING = "app_limit_tracking";
    public static final String APP_SHORT_STRING = "app_short_string";
    public static final String APP_VERSION = "app_version";
    public static final String DEVICE_LIMIT_TRACKING = "device_limit_tracking";
    public static final String OS_VERSION = "os_version";
    
    private WATCHLIST() {}
  }
  
  private final class WHITELISTITEMS
  {
    public static final String EMAIL = "email";
    public static final String LOCATION = "location";
    
    private WHITELISTITEMS() {}
  }
}
