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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
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
  private static List<ApplicationInfoHolder> installedApps;
  private static boolean is_in_background;
  private static DbAdapter kDbAdapter;
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
            paramAnonymousMessage.put("kochava_device_id", Feature.access$2700());
            paramAnonymousMessage.put("kochava_app_id", Feature.mAppId);
            paramAnonymousMessage.put("sdk_version", "Android20160105" + Feature.versionExtension);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            Logging.Log("Location update: " + paramAnonymousMessage);
            int i = Feature.kDbAdapter.addEvent(paramAnonymousMessage, false, true);
            Feature.access$4502(System.currentTimeMillis());
            long l = Feature.prefs.getLong("kochava_loc_timestamp", 0L);
            Feature.prefs.edit().putLong("kochava_old_loc_timestamp", l).commit();
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
      //   9: lstore 5
      //   11: iconst_0
      //   12: istore_2
      //   13: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   16: lstore 7
      //   18: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   21: ldc 57
      //   23: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   26: invokeinterface 63 4 0
      //   31: lstore 9
      //   33: lload 7
      //   35: lload 9
      //   37: lsub
      //   38: lstore 5
      //   40: iload_2
      //   41: istore_1
      //   42: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   45: ldc 65
      //   47: invokeinterface 69 2 0
      //   52: ifeq +65 -> 117
      //   55: iload_2
      //   56: istore_1
      //   57: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   60: ldc 65
      //   62: ldc 71
      //   64: invokeinterface 75 3 0
      //   69: aload_0
      //   70: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   73: invokestatic 79	com/kochava/android/tracker/Feature:access$300	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   76: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   79: ifne +38 -> 117
      //   82: new 87	java/lang/StringBuilder
      //   85: dup
      //   86: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   89: ldc 90
      //   91: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   94: aload_0
      //   95: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   98: invokestatic 79	com/kochava/android/tracker/Feature:access$300	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   101: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   104: ldc 96
      //   106: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   109: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   112: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   115: iconst_1
      //   116: istore_1
      //   117: iload_1
      //   118: ifne +29 -> 147
      //   121: lload 5
      //   123: lconst_0
      //   124: lcmp
      //   125: ifle +22 -> 147
      //   128: lload 5
      //   130: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   133: ldc 102
      //   135: ldc2_w 103
      //   138: invokeinterface 63 4 0
      //   143: lcmp
      //   144: ifle +3254 -> 3398
      //   147: new 87	java/lang/StringBuilder
      //   150: dup
      //   151: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   154: ldc 106
      //   156: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   159: astore 13
      //   161: aload_0
      //   162: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   165: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   168: astore 12
      //   170: aload 12
      //   172: instanceof 112
      //   175: ifne +402 -> 577
      //   178: aload 12
      //   180: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   183: astore 12
      //   185: aload 13
      //   187: aload 12
      //   189: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   192: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   195: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   198: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   201: ifnull +15 -> 216
      //   204: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   207: invokevirtual 119	java/lang/String:trim	()Ljava/lang/String;
      //   210: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   213: ifeq +14 -> 227
      //   216: ldc 125
      //   218: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   221: ldc 127
      //   223: invokestatic 131	com/kochava/android/tracker/Feature:access$402	(Ljava/lang/String;)Ljava/lang/String;
      //   226: pop
      //   227: new 87	java/lang/StringBuilder
      //   230: dup
      //   231: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   234: ldc -123
      //   236: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   239: ldc -121
      //   241: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   244: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   247: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   250: ldc -119
      //   252: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   255: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   258: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   261: new 139	java/net/URL
      //   264: dup
      //   265: new 87	java/lang/StringBuilder
      //   268: dup
      //   269: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   272: ldc -121
      //   274: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   277: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   280: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   283: ldc -119
      //   285: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   288: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   291: invokespecial 141	java/net/URL:<init>	(Ljava/lang/String;)V
      //   294: invokevirtual 145	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   297: invokestatic 150	com/newrelic/agent/android/instrumentation/HttpInstrumentation:openConnection	(Ljava/net/URLConnection;)Ljava/net/URLConnection;
      //   300: checkcast 152	javax/net/ssl/HttpsURLConnection
      //   303: astore 13
      //   305: aload 13
      //   307: ldc -102
      //   309: invokestatic 157	com/kochava/android/tracker/Feature:access$500	()Ljava/lang/String;
      //   312: invokevirtual 161	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   315: aload 13
      //   317: ldc -93
      //   319: ldc -91
      //   321: invokevirtual 161	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   324: aload 13
      //   326: ldc -89
      //   328: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   331: aload 13
      //   333: sipush 30000
      //   336: invokevirtual 174	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   339: aload 13
      //   341: sipush 30000
      //   344: invokevirtual 177	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   347: aload 13
      //   349: iconst_1
      //   350: invokevirtual 181	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   353: aload 13
      //   355: iconst_1
      //   356: invokevirtual 184	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   359: aload 13
      //   361: invokevirtual 187	javax/net/ssl/HttpsURLConnection:connect	()V
      //   364: aload_0
      //   365: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   368: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   371: astore 12
      //   373: aload 12
      //   375: instanceof 112
      //   378: ifne +212 -> 590
      //   381: aload 12
      //   383: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   386: astore 12
      //   388: new 87	java/lang/StringBuilder
      //   391: dup
      //   392: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   395: ldc -67
      //   397: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   400: aload 12
      //   402: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   405: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   408: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   411: ldc -65
      //   413: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   416: new 193	java/io/OutputStreamWriter
      //   419: dup
      //   420: aload 13
      //   422: invokevirtual 197	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   425: invokespecial 200	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   428: astore 14
      //   430: aload 14
      //   432: aload 12
      //   434: invokevirtual 203	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   437: aload 14
      //   439: invokevirtual 206	java/io/OutputStreamWriter:close	()V
      //   442: ldc -48
      //   444: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   447: new 210	java/lang/StringBuffer
      //   450: dup
      //   451: ldc 71
      //   453: invokespecial 211	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   456: astore 12
      //   458: new 213	java/io/BufferedReader
      //   461: dup
      //   462: new 215	java/io/InputStreamReader
      //   465: dup
      //   466: aload 13
      //   468: invokevirtual 219	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   471: invokespecial 222	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   474: invokespecial 225	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   477: astore 13
      //   479: aload 13
      //   481: invokevirtual 228	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   484: astore 14
      //   486: aload 14
      //   488: ifnull +115 -> 603
      //   491: aload 12
      //   493: aload 14
      //   495: invokevirtual 231	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   498: pop
      //   499: goto -20 -> 479
      //   502: astore 12
      //   504: aload 12
      //   506: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   509: ldc -19
      //   511: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   514: ifeq +2834 -> 3348
      //   517: new 87	java/lang/StringBuilder
      //   520: dup
      //   521: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   524: ldc -16
      //   526: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   529: aload 12
      //   531: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   534: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   537: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   540: aload 12
      //   542: invokestatic 250	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
      //   545: return
      //   546: astore 12
      //   548: new 87	java/lang/StringBuilder
      //   551: dup
      //   552: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   555: ldc -4
      //   557: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   560: aload 12
      //   562: invokevirtual 253	java/lang/Exception:toString	()Ljava/lang/String;
      //   565: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   568: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   571: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   574: goto -534 -> 40
      //   577: aload 12
      //   579: checkcast 112	org/json/JSONObject
      //   582: invokestatic 258	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   585: astore 12
      //   587: goto -402 -> 185
      //   590: aload 12
      //   592: checkcast 112	org/json/JSONObject
      //   595: invokestatic 258	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   598: astore 12
      //   600: goto -212 -> 388
      //   603: aload 12
      //   605: invokevirtual 259	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   608: astore 12
      //   610: new 87	java/lang/StringBuilder
      //   613: dup
      //   614: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   617: ldc_w 261
      //   620: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   623: aload 12
      //   625: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   628: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   631: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   634: aconst_null
      //   635: astore 13
      //   637: aload 12
      //   639: invokestatic 265	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   642: astore 12
      //   644: aload 12
      //   646: astore 13
      //   648: aload 13
      //   650: ifnull +1162 -> 1812
      //   653: new 87	java/lang/StringBuilder
      //   656: dup
      //   657: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   660: ldc_w 267
      //   663: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   666: astore 14
      //   668: aload 13
      //   670: instanceof 112
      //   673: ifne +1072 -> 1745
      //   676: aload 13
      //   678: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   681: astore 12
      //   683: aload 14
      //   685: aload 12
      //   687: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   690: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   693: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   696: aconst_null
      //   697: astore 12
      //   699: aload 13
      //   701: ldc_w 269
      //   704: invokevirtual 272	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   707: astore 14
      //   709: aload 14
      //   711: astore 12
      //   713: new 87	java/lang/StringBuilder
      //   716: dup
      //   717: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   720: ldc_w 274
      //   723: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   726: astore 16
      //   728: aload 14
      //   730: astore 12
      //   732: aload 14
      //   734: instanceof 112
      //   737: ifne +1021 -> 1758
      //   740: aload 14
      //   742: astore 12
      //   744: aload 14
      //   746: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   749: astore 15
      //   751: aload 14
      //   753: astore 12
      //   755: aload 16
      //   757: aload 15
      //   759: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   762: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   765: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   768: aload 14
      //   770: astore 12
      //   772: aload 12
      //   774: ifnull +756 -> 1530
      //   777: aload 12
      //   779: ldc_w 276
      //   782: invokevirtual 278	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   785: astore 14
      //   787: new 87	java/lang/StringBuilder
      //   790: dup
      //   791: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   794: ldc_w 280
      //   797: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   800: aload 14
      //   802: invokevirtual 281	java/lang/String:toString	()Ljava/lang/String;
      //   805: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   808: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   811: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   814: aload 14
      //   816: invokestatic 284	com/kochava/android/tracker/Feature:access$602	(Ljava/lang/String;)Ljava/lang/String;
      //   819: pop
      //   820: aload 12
      //   822: ldc_w 286
      //   825: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   828: ldc_w 292
      //   831: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   834: ifeq +8 -> 842
      //   837: iconst_0
      //   838: invokestatic 296	com/kochava/android/tracker/Feature:access$702	(Z)Z
      //   841: pop
      //   842: aload 12
      //   844: ldc_w 298
      //   847: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   850: checkcast 81	java/lang/String
      //   853: invokevirtual 301	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   856: astore 14
      //   858: new 87	java/lang/StringBuilder
      //   861: dup
      //   862: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   865: ldc_w 303
      //   868: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   871: aload 14
      //   873: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   876: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   879: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   882: aload_0
      //   883: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   886: aload 14
      //   888: invokestatic 307	com/kochava/android/tracker/Feature:access$800	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;)V
      //   891: aload 12
      //   893: ldc_w 309
      //   896: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   899: ldc_w 311
      //   902: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   905: ifeq +52 -> 957
      //   908: ldc_w 313
      //   911: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   914: aload_0
      //   915: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   918: invokestatic 317	com/kochava/android/tracker/Feature:access$900	(Lcom/kochava/android/tracker/Feature;)Landroid/content/Context;
      //   921: ldc_w 319
      //   924: iconst_0
      //   925: invokevirtual 325	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   928: invokestatic 329	com/kochava/android/tracker/Feature:access$202	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   931: pop
      //   932: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   935: invokeinterface 333 1 0
      //   940: ldc_w 335
      //   943: ldc_w 337
      //   946: invokeinterface 343 3 0
      //   951: invokeinterface 346 1 0
      //   956: pop
      //   957: aload 12
      //   959: ldc_w 348
      //   962: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   965: checkcast 350	java/lang/Integer
      //   968: invokevirtual 354	java/lang/Integer:intValue	()I
      //   971: invokestatic 358	com/kochava/android/tracker/Feature:access$1002	(I)I
      //   974: pop
      //   975: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	()I
      //   978: ifge +1080 -> 2058
      //   981: new 87	java/lang/StringBuilder
      //   984: dup
      //   985: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   988: ldc_w 363
      //   991: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   994: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	()I
      //   997: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1000: ldc_w 368
      //   1003: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1006: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1009: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1012: iconst_0
      //   1013: invokestatic 358	com/kochava/android/tracker/Feature:access$1002	(I)I
      //   1016: pop
      //   1017: invokestatic 371	com/kochava/android/tracker/Feature:access$1100	()Z
      //   1020: ifne +89 -> 1109
      //   1023: aload 12
      //   1025: ldc_w 373
      //   1028: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1031: ifnull +78 -> 1109
      //   1034: aload 12
      //   1036: ldc_w 373
      //   1039: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1042: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1045: ldc_w 350
      //   1048: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1051: ifeq +58 -> 1109
      //   1054: aload 12
      //   1056: ldc_w 373
      //   1059: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1062: checkcast 350	java/lang/Integer
      //   1065: invokevirtual 354	java/lang/Integer:intValue	()I
      //   1068: istore_1
      //   1069: iload_1
      //   1070: bipush 60
      //   1072: if_icmpge +1062 -> 2134
      //   1075: new 87	java/lang/StringBuilder
      //   1078: dup
      //   1079: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1082: ldc_w 375
      //   1085: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1088: iload_1
      //   1089: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1092: ldc_w 377
      //   1095: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1098: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1101: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1104: iconst_1
      //   1105: invokestatic 380	com/kochava/android/tracker/Feature:access$1302	(Z)Z
      //   1108: pop
      //   1109: aload 12
      //   1111: ldc 102
      //   1113: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1116: ifnull +93 -> 1209
      //   1119: aload 12
      //   1121: ldc 102
      //   1123: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1126: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1129: ldc_w 350
      //   1132: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1135: ifeq +74 -> 1209
      //   1138: aload 12
      //   1140: ldc 102
      //   1142: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1145: checkcast 350	java/lang/Integer
      //   1148: invokevirtual 354	java/lang/Integer:intValue	()I
      //   1151: istore_1
      //   1152: iload_1
      //   1153: ifge +1068 -> 2221
      //   1156: new 87	java/lang/StringBuilder
      //   1159: dup
      //   1160: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1163: ldc_w 382
      //   1166: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1169: iload_1
      //   1170: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1173: ldc_w 384
      //   1176: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1179: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1182: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1185: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1188: invokeinterface 333 1 0
      //   1193: ldc 102
      //   1195: ldc2_w 103
      //   1198: invokeinterface 388 4 0
      //   1203: invokeinterface 346 1 0
      //   1208: pop
      //   1209: aload 12
      //   1211: ldc_w 390
      //   1214: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1217: ifnull +77 -> 1294
      //   1220: aload 12
      //   1222: ldc_w 390
      //   1225: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1228: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1231: ldc_w 350
      //   1234: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1237: ifeq +57 -> 1294
      //   1240: aload 12
      //   1242: ldc_w 390
      //   1245: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1248: checkcast 350	java/lang/Integer
      //   1251: invokevirtual 354	java/lang/Integer:intValue	()I
      //   1254: istore_1
      //   1255: iload_1
      //   1256: iconst_1
      //   1257: if_icmpge +1086 -> 2343
      //   1260: new 87	java/lang/StringBuilder
      //   1263: dup
      //   1264: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1267: ldc_w 392
      //   1270: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1273: iload_1
      //   1274: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1277: ldc_w 394
      //   1280: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1283: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1286: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1289: iconst_1
      //   1290: invokestatic 397	com/kochava/android/tracker/Feature:access$1402	(I)I
      //   1293: pop
      //   1294: aload 12
      //   1296: ldc_w 399
      //   1299: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1302: checkcast 350	java/lang/Integer
      //   1305: invokevirtual 354	java/lang/Integer:intValue	()I
      //   1308: istore_2
      //   1309: iload_2
      //   1310: bipush 10
      //   1312: if_icmpge +1112 -> 2424
      //   1315: new 87	java/lang/StringBuilder
      //   1318: dup
      //   1319: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1322: ldc_w 401
      //   1325: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1328: iload_2
      //   1329: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1332: ldc_w 403
      //   1335: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1338: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1341: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1344: bipush 10
      //   1346: istore_1
      //   1347: new 87	java/lang/StringBuilder
      //   1350: dup
      //   1351: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1354: ldc_w 405
      //   1357: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1360: iload_1
      //   1361: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1364: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1367: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1370: iload_1
      //   1371: putstatic 411	com/kochava/android/tracker/LocationDirector:desiredAccuracy	I
      //   1374: aload 12
      //   1376: ldc_w 413
      //   1379: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1382: checkcast 350	java/lang/Integer
      //   1385: invokevirtual 354	java/lang/Integer:intValue	()I
      //   1388: istore_2
      //   1389: iload_2
      //   1390: iconst_3
      //   1391: if_icmpge +1078 -> 2469
      //   1394: new 87	java/lang/StringBuilder
      //   1397: dup
      //   1398: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1401: ldc_w 415
      //   1404: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1407: iload_2
      //   1408: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1411: ldc_w 417
      //   1414: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1417: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1420: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1423: iconst_3
      //   1424: istore_1
      //   1425: new 87	java/lang/StringBuilder
      //   1428: dup
      //   1429: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1432: ldc_w 419
      //   1435: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1438: iload_1
      //   1439: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1442: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1445: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1448: iload_1
      //   1449: putstatic 422	com/kochava/android/tracker/LocationDirector:timeout	I
      //   1452: aload 12
      //   1454: ldc_w 424
      //   1457: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1460: checkcast 350	java/lang/Integer
      //   1463: invokevirtual 354	java/lang/Integer:intValue	()I
      //   1466: istore_2
      //   1467: iload_2
      //   1468: iconst_1
      //   1469: if_icmpge +1043 -> 2512
      //   1472: new 87	java/lang/StringBuilder
      //   1475: dup
      //   1476: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1479: ldc_w 426
      //   1482: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1485: iload_2
      //   1486: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1489: ldc_w 428
      //   1492: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1495: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1498: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1501: iconst_1
      //   1502: istore_1
      //   1503: new 87	java/lang/StringBuilder
      //   1506: dup
      //   1507: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1510: ldc_w 430
      //   1513: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1516: iload_1
      //   1517: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1520: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1523: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1526: iload_1
      //   1527: putstatic 433	com/kochava/android/tracker/LocationDirector:staleness	I
      //   1530: aload 13
      //   1532: ldc_w 435
      //   1535: invokevirtual 439	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1538: astore 15
      //   1540: new 87	java/lang/StringBuilder
      //   1543: dup
      //   1544: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1547: ldc_w 441
      //   1550: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1553: astore 16
      //   1555: aload 15
      //   1557: instanceof 443
      //   1560: ifne +997 -> 2557
      //   1563: aload 15
      //   1565: invokevirtual 444	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1568: astore 14
      //   1570: aload 16
      //   1572: aload 14
      //   1574: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1577: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1580: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1583: iconst_0
      //   1584: istore_1
      //   1585: iload_1
      //   1586: aload 15
      //   1588: invokevirtual 447	org/json/JSONArray:length	()I
      //   1591: if_icmpge +1042 -> 2633
      //   1594: aload 15
      //   1596: iload_1
      //   1597: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1600: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   1603: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1606: ldc_w 456
      //   1609: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1612: ifeq +958 -> 2570
      //   1615: ldc_w 458
      //   1618: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1621: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   1624: ldc_w 456
      //   1627: iconst_0
      //   1628: invokestatic 468	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1631: invokevirtual 474	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1634: pop
      //   1635: iload_1
      //   1636: iconst_1
      //   1637: iadd
      //   1638: istore_1
      //   1639: goto -54 -> 1585
      //   1642: astore 12
      //   1644: new 87	java/lang/StringBuilder
      //   1647: dup
      //   1648: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1651: ldc_w 476
      //   1654: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1657: aload 12
      //   1659: invokevirtual 477	org/json/JSONException:toString	()Ljava/lang/String;
      //   1662: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1665: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1668: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1671: goto -1023 -> 648
      //   1674: astore 12
      //   1676: new 87	java/lang/StringBuilder
      //   1679: dup
      //   1680: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1683: ldc_w 479
      //   1686: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1689: aload 12
      //   1691: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1694: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1697: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1700: return
      //   1701: astore 12
      //   1703: ldc -19
      //   1705: aload 12
      //   1707: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1710: invokevirtual 485	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   1713: ifeq +1660 -> 3373
      //   1716: new 87	java/lang/StringBuilder
      //   1719: dup
      //   1720: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1723: ldc -16
      //   1725: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1728: aload 12
      //   1730: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1733: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1736: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1739: aload 12
      //   1741: invokestatic 250	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
      //   1744: return
      //   1745: aload 13
      //   1747: checkcast 112	org/json/JSONObject
      //   1750: invokestatic 258	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   1753: astore 12
      //   1755: goto -1072 -> 683
      //   1758: aload 14
      //   1760: astore 12
      //   1762: aload 14
      //   1764: checkcast 112	org/json/JSONObject
      //   1767: invokestatic 258	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   1770: astore 15
      //   1772: goto -1021 -> 751
      //   1775: astore 14
      //   1777: ldc_w 487
      //   1780: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1783: goto -1011 -> 772
      //   1786: astore 12
      //   1788: new 87	java/lang/StringBuilder
      //   1791: dup
      //   1792: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1795: ldc_w 489
      //   1798: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1801: aload 12
      //   1803: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1806: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1809: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1812: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1815: invokeinterface 333 1 0
      //   1820: ldc 57
      //   1822: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   1825: invokeinterface 388 4 0
      //   1830: invokeinterface 346 1 0
      //   1835: pop
      //   1836: ldc_w 491
      //   1839: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1842: iconst_0
      //   1843: istore_2
      //   1844: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1847: ldc_w 335
      //   1850: ldc 71
      //   1852: invokeinterface 75 3 0
      //   1857: ldc_w 311
      //   1860: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1863: ifne +1639 -> 3502
      //   1866: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   1869: ldc_w 493
      //   1872: invokevirtual 496	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1875: checkcast 464	java/lang/Boolean
      //   1878: invokevirtual 499	java/lang/Boolean:booleanValue	()Z
      //   1881: ifne +1554 -> 3435
      //   1884: iconst_1
      //   1885: istore_1
      //   1886: invokestatic 502	com/kochava/android/tracker/Feature:access$2400	()Z
      //   1889: istore 11
      //   1891: iload_2
      //   1892: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	()I
      //   1895: if_icmpge +50 -> 1945
      //   1898: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1901: ldc_w 504
      //   1904: ldc_w 506
      //   1907: invokeinterface 75 3 0
      //   1912: ldc_w 506
      //   1915: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1918: ifne +1522 -> 3440
      //   1921: iconst_1
      //   1922: istore_3
      //   1923: invokestatic 509	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   1926: ifnull +1519 -> 3445
      //   1929: iconst_1
      //   1930: istore 4
      //   1932: iload_3
      //   1933: ifeq +1518 -> 3451
      //   1936: iload_1
      //   1937: ifne +8 -> 1945
      //   1940: iload 11
      //   1942: ifeq +1509 -> 3451
      //   1945: new 87	java/lang/StringBuilder
      //   1948: dup
      //   1949: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1952: ldc_w 511
      //   1955: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1958: iload_2
      //   1959: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1962: ldc_w 513
      //   1965: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1968: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1971: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1974: invokestatic 519	android/os/Message:obtain	()Landroid/os/Message;
      //   1977: astore 12
      //   1979: new 521	android/os/Bundle
      //   1982: dup
      //   1983: invokespecial 522	android/os/Bundle:<init>	()V
      //   1986: astore 13
      //   1988: aload 13
      //   1990: ldc_w 524
      //   1993: invokestatic 527	com/kochava/android/tracker/Feature:access$2500	()Z
      //   1996: invokevirtual 531	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   1999: aload 12
      //   2001: aload 13
      //   2003: invokevirtual 535	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   2006: aload_0
      //   2007: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   2010: invokestatic 539	com/kochava/android/tracker/Feature:access$2600	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   2013: ifnull -1468 -> 545
      //   2016: ldc_w 541
      //   2019: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2022: aload_0
      //   2023: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   2026: invokestatic 539	com/kochava/android/tracker/Feature:access$2600	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   2029: aload 12
      //   2031: invokevirtual 547	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   2034: pop
      //   2035: return
      //   2036: astore 14
      //   2038: ldc_w 549
      //   2041: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2044: goto -1224 -> 820
      //   2047: astore 14
      //   2049: ldc_w 487
      //   2052: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2055: goto -1213 -> 842
      //   2058: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	()I
      //   2061: bipush 120
      //   2063: if_icmple +43 -> 2106
      //   2066: new 87	java/lang/StringBuilder
      //   2069: dup
      //   2070: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2073: ldc_w 551
      //   2076: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2079: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	()I
      //   2082: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2085: ldc_w 553
      //   2088: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2091: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2094: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2097: bipush 120
      //   2099: invokestatic 358	com/kochava/android/tracker/Feature:access$1002	(I)I
      //   2102: pop
      //   2103: goto -1086 -> 1017
      //   2106: new 87	java/lang/StringBuilder
      //   2109: dup
      //   2110: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2113: ldc_w 555
      //   2116: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2119: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	()I
      //   2122: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2125: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2128: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2131: goto -1114 -> 1017
      //   2134: iload_1
      //   2135: ldc_w 556
      //   2138: if_icmple +42 -> 2180
      //   2141: new 87	java/lang/StringBuilder
      //   2144: dup
      //   2145: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2148: ldc_w 558
      //   2151: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2154: iload_1
      //   2155: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2158: ldc_w 560
      //   2161: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2164: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2167: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2170: ldc_w 561
      //   2173: invokestatic 564	com/kochava/android/tracker/Feature:access$1202	(I)I
      //   2176: pop
      //   2177: goto -1073 -> 1104
      //   2180: iload_1
      //   2181: sipush 1000
      //   2184: imul
      //   2185: invokestatic 564	com/kochava/android/tracker/Feature:access$1202	(I)I
      //   2188: pop
      //   2189: new 87	java/lang/StringBuilder
      //   2192: dup
      //   2193: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2196: ldc_w 566
      //   2199: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2202: iload_1
      //   2203: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2206: ldc_w 513
      //   2209: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2212: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2215: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2218: goto -1114 -> 1104
      //   2221: iload_1
      //   2222: ldc_w 556
      //   2225: if_icmple +59 -> 2284
      //   2228: new 87	java/lang/StringBuilder
      //   2231: dup
      //   2232: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2235: ldc_w 382
      //   2238: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2241: iload_1
      //   2242: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2245: ldc_w 568
      //   2248: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2251: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2254: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2257: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   2260: invokeinterface 333 1 0
      //   2265: ldc 102
      //   2267: ldc2_w 569
      //   2270: invokeinterface 388 4 0
      //   2275: invokeinterface 346 1 0
      //   2280: pop
      //   2281: goto -1072 -> 1209
      //   2284: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   2287: invokeinterface 333 1 0
      //   2292: ldc 102
      //   2294: iload_1
      //   2295: sipush 1000
      //   2298: imul
      //   2299: i2l
      //   2300: invokeinterface 388 4 0
      //   2305: invokeinterface 346 1 0
      //   2310: pop
      //   2311: new 87	java/lang/StringBuilder
      //   2314: dup
      //   2315: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2318: ldc_w 572
      //   2321: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2324: iload_1
      //   2325: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2328: ldc_w 513
      //   2331: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2334: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2337: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2340: goto -1131 -> 1209
      //   2343: iload_1
      //   2344: bipush 30
      //   2346: if_icmple +41 -> 2387
      //   2349: new 87	java/lang/StringBuilder
      //   2352: dup
      //   2353: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2356: ldc_w 392
      //   2359: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2362: iload_1
      //   2363: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2366: ldc_w 574
      //   2369: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2372: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2375: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2378: bipush 30
      //   2380: invokestatic 397	com/kochava/android/tracker/Feature:access$1402	(I)I
      //   2383: pop
      //   2384: goto -1090 -> 1294
      //   2387: new 87	java/lang/StringBuilder
      //   2390: dup
      //   2391: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2394: ldc_w 576
      //   2397: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2400: iload_1
      //   2401: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2404: ldc_w 513
      //   2407: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2410: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2413: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2416: iload_1
      //   2417: invokestatic 397	com/kochava/android/tracker/Feature:access$1402	(I)I
      //   2420: pop
      //   2421: goto -1127 -> 1294
      //   2424: iload_2
      //   2425: istore_1
      //   2426: iload_2
      //   2427: sipush 5000
      //   2430: if_icmple -1083 -> 1347
      //   2433: new 87	java/lang/StringBuilder
      //   2436: dup
      //   2437: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2440: ldc_w 401
      //   2443: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2446: iload_2
      //   2447: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2450: ldc_w 403
      //   2453: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2456: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2459: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2462: sipush 5000
      //   2465: istore_1
      //   2466: goto -1119 -> 1347
      //   2469: iload_2
      //   2470: istore_1
      //   2471: iload_2
      //   2472: bipush 60
      //   2474: if_icmple -1049 -> 1425
      //   2477: new 87	java/lang/StringBuilder
      //   2480: dup
      //   2481: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2484: ldc_w 415
      //   2487: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2490: iload_2
      //   2491: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2494: ldc_w 417
      //   2497: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2500: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2503: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2506: bipush 60
      //   2508: istore_1
      //   2509: goto -1084 -> 1425
      //   2512: iload_2
      //   2513: istore_1
      //   2514: iload_2
      //   2515: sipush 10080
      //   2518: if_icmple -1015 -> 1503
      //   2521: new 87	java/lang/StringBuilder
      //   2524: dup
      //   2525: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2528: ldc_w 426
      //   2531: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2534: iload_2
      //   2535: invokevirtual 366	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2538: ldc_w 428
      //   2541: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2544: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2547: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2550: sipush 10080
      //   2553: istore_1
      //   2554: goto -1051 -> 1503
      //   2557: aload 15
      //   2559: checkcast 443	org/json/JSONArray
      //   2562: invokestatic 581	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   2565: astore 14
      //   2567: goto -997 -> 1570
      //   2570: aload 15
      //   2572: iload_1
      //   2573: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2576: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2579: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2582: ldc_w 583
      //   2585: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2588: ifeq +148 -> 2736
      //   2591: ldc_w 585
      //   2594: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2597: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2600: ldc_w 583
      //   2603: iconst_0
      //   2604: invokestatic 468	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2607: invokevirtual 474	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2610: pop
      //   2611: goto -976 -> 1635
      //   2614: astore 14
      //   2616: ldc_w 587
      //   2619: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2622: getstatic 593	com/kochava/android/tracker/Global:DEBUGERROR	Z
      //   2625: ifeq +8 -> 2633
      //   2628: aload 14
      //   2630: invokevirtual 596	java/lang/Exception:printStackTrace	()V
      //   2633: aload 13
      //   2635: ldc_w 598
      //   2638: invokevirtual 439	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2641: astore 15
      //   2643: new 87	java/lang/StringBuilder
      //   2646: dup
      //   2647: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2650: ldc_w 600
      //   2653: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2656: astore 16
      //   2658: aload 15
      //   2660: instanceof 443
      //   2663: ifne +249 -> 2912
      //   2666: aload 15
      //   2668: invokevirtual 444	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2671: astore 14
      //   2673: aload 16
      //   2675: aload 14
      //   2677: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2680: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2683: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2686: iconst_0
      //   2687: istore_1
      //   2688: iload_1
      //   2689: aload 15
      //   2691: invokevirtual 447	org/json/JSONArray:length	()I
      //   2694: if_icmpge +295 -> 2989
      //   2697: aload 15
      //   2699: iload_1
      //   2700: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2703: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2706: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2709: ldc_w 602
      //   2712: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2715: ifeq +210 -> 2925
      //   2718: ldc_w 604
      //   2721: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2724: iconst_1
      //   2725: invokestatic 607	com/kochava/android/tracker/Feature:access$1602	(Z)Z
      //   2728: pop
      //   2729: iload_1
      //   2730: iconst_1
      //   2731: iadd
      //   2732: istore_1
      //   2733: goto -45 -> 2688
      //   2736: aload 15
      //   2738: iload_1
      //   2739: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2742: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2745: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2748: ldc_w 493
      //   2751: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2754: ifeq +26 -> 2780
      //   2757: ldc_w 609
      //   2760: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2763: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2766: ldc_w 493
      //   2769: iconst_0
      //   2770: invokestatic 468	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2773: invokevirtual 474	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2776: pop
      //   2777: goto -1142 -> 1635
      //   2780: aload 15
      //   2782: iload_1
      //   2783: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2786: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2789: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2792: ldc_w 611
      //   2795: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2798: ifeq +26 -> 2824
      //   2801: ldc_w 613
      //   2804: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2807: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2810: ldc_w 611
      //   2813: iconst_0
      //   2814: invokestatic 468	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2817: invokevirtual 474	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2820: pop
      //   2821: goto -1186 -> 1635
      //   2824: aload 15
      //   2826: iload_1
      //   2827: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2830: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2833: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2836: ldc_w 615
      //   2839: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2842: ifeq +26 -> 2868
      //   2845: ldc_w 617
      //   2848: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2851: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2854: ldc_w 615
      //   2857: iconst_0
      //   2858: invokestatic 468	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2861: invokevirtual 474	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2864: pop
      //   2865: goto -1230 -> 1635
      //   2868: aload 15
      //   2870: iload_1
      //   2871: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2874: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2877: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2880: ldc_w 619
      //   2883: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2886: ifeq -1251 -> 1635
      //   2889: ldc_w 621
      //   2892: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2895: invokestatic 462	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2898: ldc_w 619
      //   2901: iconst_0
      //   2902: invokestatic 468	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2905: invokevirtual 474	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2908: pop
      //   2909: goto -1274 -> 1635
      //   2912: aload 15
      //   2914: checkcast 443	org/json/JSONArray
      //   2917: invokestatic 581	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   2920: astore 14
      //   2922: goto -249 -> 2673
      //   2925: aload 15
      //   2927: iload_1
      //   2928: invokevirtual 450	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2931: invokevirtual 451	java/lang/Object:toString	()Ljava/lang/String;
      //   2934: invokevirtual 454	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2937: ldc_w 623
      //   2940: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2943: ifeq -214 -> 2729
      //   2946: ldc_w 625
      //   2949: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2952: iconst_1
      //   2953: invokestatic 628	com/kochava/android/tracker/Feature:access$1702	(Z)Z
      //   2956: pop
      //   2957: goto -228 -> 2729
      //   2960: astore 14
      //   2962: new 87	java/lang/StringBuilder
      //   2965: dup
      //   2966: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2969: ldc_w 630
      //   2972: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2975: aload 14
      //   2977: invokevirtual 253	java/lang/Exception:toString	()Ljava/lang/String;
      //   2980: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2983: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2986: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2989: aload 13
      //   2991: ldc_w 632
      //   2994: invokevirtual 272	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2997: ldc_w 634
      //   3000: invokevirtual 272	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3003: astore 14
      //   3005: aload_0
      //   3006: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   3009: aload 14
      //   3011: invokestatic 638	com/kochava/android/tracker/Feature:access$1800	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   3014: invokestatic 641	com/kochava/android/tracker/Feature:access$1700	()Z
      //   3017: ifeq +18 -> 3035
      //   3020: invokestatic 644	com/kochava/android/tracker/Feature:access$1900	()Z
      //   3023: ifeq +12 -> 3035
      //   3026: getstatic 648	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   3029: invokestatic 652	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   3032: invokevirtual 655	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   3035: aload 13
      //   3037: ldc_w 657
      //   3040: invokevirtual 439	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3043: invokestatic 661	com/kochava/android/tracker/Feature:access$2002	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3046: pop
      //   3047: new 87	java/lang/StringBuilder
      //   3050: dup
      //   3051: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3054: ldc_w 663
      //   3057: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3060: astore 15
      //   3062: invokestatic 667	com/kochava/android/tracker/Feature:access$2000	()Lorg/json/JSONArray;
      //   3065: astore 14
      //   3067: aload 14
      //   3069: instanceof 443
      //   3072: ifne +229 -> 3301
      //   3075: aload 14
      //   3077: invokevirtual 444	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3080: astore 14
      //   3082: aload 15
      //   3084: aload 14
      //   3086: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3089: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3092: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3095: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   3098: invokeinterface 333 1 0
      //   3103: astore 15
      //   3105: invokestatic 667	com/kochava/android/tracker/Feature:access$2000	()Lorg/json/JSONArray;
      //   3108: astore 14
      //   3110: aload 14
      //   3112: instanceof 443
      //   3115: ifne +199 -> 3314
      //   3118: aload 14
      //   3120: invokevirtual 444	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3123: astore 14
      //   3125: aload 15
      //   3127: ldc_w 669
      //   3130: aload 14
      //   3132: invokeinterface 343 3 0
      //   3137: invokeinterface 346 1 0
      //   3142: pop
      //   3143: aload 12
      //   3145: ldc_w 671
      //   3148: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3151: ifnull +88 -> 3239
      //   3154: aload 12
      //   3156: ldc_w 671
      //   3159: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3162: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3165: ldc_w 464
      //   3168: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3171: ifeq +167 -> 3338
      //   3174: aload 12
      //   3176: ldc_w 671
      //   3179: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3182: checkcast 464	java/lang/Boolean
      //   3185: invokevirtual 499	java/lang/Boolean:booleanValue	()Z
      //   3188: ifeq +150 -> 3338
      //   3191: iconst_1
      //   3192: istore_1
      //   3193: aload 12
      //   3195: ldc_w 671
      //   3198: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3201: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3204: ldc 81
      //   3206: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3209: ifeq +134 -> 3343
      //   3212: ldc_w 311
      //   3215: aload 12
      //   3217: ldc_w 671
      //   3220: invokevirtual 290	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3223: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3226: ifeq +117 -> 3343
      //   3229: iconst_1
      //   3230: istore_2
      //   3231: goto +340 -> 3571
      //   3234: iconst_1
      //   3235: invokestatic 674	com/kochava/android/tracker/Feature:access$2102	(Z)Z
      //   3238: pop
      //   3239: aload 13
      //   3241: ldc_w 676
      //   3244: invokevirtual 278	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3247: astore 12
      //   3249: new 87	java/lang/StringBuilder
      //   3252: dup
      //   3253: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3256: ldc_w 678
      //   3259: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3262: aload 12
      //   3264: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3267: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3270: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3273: aload 12
      //   3275: ldc_w 680
      //   3278: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3281: ifeq -1469 -> 1812
      //   3284: iconst_1
      //   3285: invokestatic 683	com/kochava/android/tracker/Feature:access$2202	(Z)Z
      //   3288: pop
      //   3289: return
      //   3290: astore 12
      //   3292: ldc_w 685
      //   3295: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3298: goto -1486 -> 1812
      //   3301: aload 14
      //   3303: checkcast 443	org/json/JSONArray
      //   3306: invokestatic 581	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   3309: astore 14
      //   3311: goto -229 -> 3082
      //   3314: aload 14
      //   3316: checkcast 443	org/json/JSONArray
      //   3319: invokestatic 581	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   3322: astore 14
      //   3324: goto -199 -> 3125
      //   3327: astore 14
      //   3329: ldc_w 687
      //   3332: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3335: goto -192 -> 3143
      //   3338: iconst_0
      //   3339: istore_1
      //   3340: goto -147 -> 3193
      //   3343: iconst_0
      //   3344: istore_2
      //   3345: goto +226 -> 3571
      //   3348: new 87	java/lang/StringBuilder
      //   3351: dup
      //   3352: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3355: ldc_w 479
      //   3358: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3361: aload 12
      //   3363: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3366: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3369: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3372: return
      //   3373: new 87	java/lang/StringBuilder
      //   3376: dup
      //   3377: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3380: ldc_w 479
      //   3383: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3386: aload 12
      //   3388: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3391: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3394: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3397: return
      //   3398: new 87	java/lang/StringBuilder
      //   3401: dup
      //   3402: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3405: ldc_w 689
      //   3408: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3411: lload 5
      //   3413: ldc2_w 690
      //   3416: ldiv
      //   3417: invokevirtual 694	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3420: ldc_w 696
      //   3423: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3426: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3429: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3432: goto -1596 -> 1836
      //   3435: iconst_0
      //   3436: istore_1
      //   3437: goto -1551 -> 1886
      //   3440: iconst_0
      //   3441: istore_3
      //   3442: goto -1519 -> 1923
      //   3445: iconst_0
      //   3446: istore 4
      //   3448: goto -1516 -> 1932
      //   3451: iload_3
      //   3452: ifeq +8 -> 3460
      //   3455: iload 4
      //   3457: ifne -1512 -> 1945
      //   3460: ldc2_w 690
      //   3463: invokestatic 702	java/lang/Thread:sleep	(J)V
      //   3466: iload_2
      //   3467: iconst_1
      //   3468: iadd
      //   3469: istore_2
      //   3470: goto -1579 -> 1891
      //   3473: astore 12
      //   3475: new 87	java/lang/StringBuilder
      //   3478: dup
      //   3479: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3482: ldc_w 704
      //   3485: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3488: aload 12
      //   3490: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3493: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3496: invokestatic 246	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3499: goto -33 -> 3466
      //   3502: ldc_w 706
      //   3505: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3508: goto -1534 -> 1974
      //   3511: astore 12
      //   3513: goto -274 -> 3239
      //   3516: astore 14
      //   3518: goto -375 -> 3143
      //   3521: astore 14
      //   3523: goto -509 -> 3014
      //   3526: astore 14
      //   3528: goto -1998 -> 1530
      //   3531: astore 14
      //   3533: goto -2081 -> 1452
      //   3536: astore 14
      //   3538: goto -2164 -> 1374
      //   3541: astore 14
      //   3543: goto -2586 -> 957
      //   3546: astore 14
      //   3548: goto -2657 -> 891
      //   3551: astore 14
      //   3553: goto -2536 -> 1017
      //   3556: astore 14
      //   3558: goto -2449 -> 1109
      //   3561: astore 14
      //   3563: goto -2354 -> 1209
      //   3566: astore 14
      //   3568: goto -2274 -> 1294
      //   3571: iload_1
      //   3572: ifne -338 -> 3234
      //   3575: iload_2
      //   3576: ifeq -337 -> 3239
      //   3579: goto -345 -> 3234
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3582	0	this	3
      //   41	3531	1	i	int
      //   12	3564	2	j	int
      //   1922	1530	3	k	int
      //   1930	1526	4	m	int
      //   9	3403	5	l1	long
      //   16	18	7	l2	long
      //   31	5	9	l3	long
      //   1889	52	11	bool	boolean
      //   168	324	12	localObject1	Object
      //   502	39	12	localIOException1	IOException
      //   546	32	12	localException1	Exception
      //   585	868	12	localObject2	Object
      //   1642	16	12	localJSONException1	JSONException
      //   1674	16	12	localOutOfMemoryError	OutOfMemoryError
      //   1701	39	12	localIOException2	IOException
      //   1753	8	12	localObject3	Object
      //   1786	16	12	localException2	Exception
      //   1977	1297	12	localObject4	Object
      //   3290	97	12	localException3	Exception
      //   3473	16	12	localInterruptedException	InterruptedException
      //   3511	1	12	localException4	Exception
      //   159	3081	13	localObject5	Object
      //   428	1335	14	localObject6	Object
      //   1775	1	14	localJSONException2	JSONException
      //   2036	1	14	localJSONException3	JSONException
      //   2047	1	14	localJSONException4	JSONException
      //   2565	1	14	str1	String
      //   2614	15	14	localException5	Exception
      //   2671	250	14	str2	String
      //   2960	16	14	localException6	Exception
      //   3003	320	14	localObject7	Object
      //   3327	1	14	localException7	Exception
      //   3516	1	14	localJSONException5	JSONException
      //   3521	1	14	localException8	Exception
      //   3526	1	14	localException9	Exception
      //   3531	1	14	localException10	Exception
      //   3536	1	14	localException11	Exception
      //   3541	1	14	localException12	Exception
      //   3546	1	14	localException13	Exception
      //   3551	1	14	localException14	Exception
      //   3556	1	14	localException15	Exception
      //   3561	1	14	localException16	Exception
      //   3566	1	14	localException17	Exception
      //   749	2377	15	localObject8	Object
      //   726	1948	16	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   442	479	502	java/io/IOException
      //   479	486	502	java/io/IOException
      //   491	499	502	java/io/IOException
      //   603	634	502	java/io/IOException
      //   637	644	502	java/io/IOException
      //   653	683	502	java/io/IOException
      //   683	696	502	java/io/IOException
      //   699	709	502	java/io/IOException
      //   713	728	502	java/io/IOException
      //   732	740	502	java/io/IOException
      //   744	751	502	java/io/IOException
      //   755	768	502	java/io/IOException
      //   777	820	502	java/io/IOException
      //   820	842	502	java/io/IOException
      //   842	891	502	java/io/IOException
      //   891	957	502	java/io/IOException
      //   957	1017	502	java/io/IOException
      //   1017	1069	502	java/io/IOException
      //   1075	1104	502	java/io/IOException
      //   1104	1109	502	java/io/IOException
      //   1109	1152	502	java/io/IOException
      //   1156	1209	502	java/io/IOException
      //   1209	1255	502	java/io/IOException
      //   1260	1294	502	java/io/IOException
      //   1294	1309	502	java/io/IOException
      //   1315	1344	502	java/io/IOException
      //   1347	1374	502	java/io/IOException
      //   1374	1389	502	java/io/IOException
      //   1394	1423	502	java/io/IOException
      //   1425	1452	502	java/io/IOException
      //   1452	1467	502	java/io/IOException
      //   1472	1501	502	java/io/IOException
      //   1503	1530	502	java/io/IOException
      //   1530	1570	502	java/io/IOException
      //   1570	1583	502	java/io/IOException
      //   1585	1635	502	java/io/IOException
      //   1644	1671	502	java/io/IOException
      //   1745	1755	502	java/io/IOException
      //   1762	1772	502	java/io/IOException
      //   1777	1783	502	java/io/IOException
      //   2038	2044	502	java/io/IOException
      //   2049	2055	502	java/io/IOException
      //   2058	2103	502	java/io/IOException
      //   2106	2131	502	java/io/IOException
      //   2141	2177	502	java/io/IOException
      //   2180	2218	502	java/io/IOException
      //   2228	2281	502	java/io/IOException
      //   2284	2340	502	java/io/IOException
      //   2349	2384	502	java/io/IOException
      //   2387	2421	502	java/io/IOException
      //   2433	2462	502	java/io/IOException
      //   2477	2506	502	java/io/IOException
      //   2521	2550	502	java/io/IOException
      //   2557	2567	502	java/io/IOException
      //   2570	2611	502	java/io/IOException
      //   2616	2633	502	java/io/IOException
      //   2633	2673	502	java/io/IOException
      //   2673	2686	502	java/io/IOException
      //   2688	2729	502	java/io/IOException
      //   2736	2777	502	java/io/IOException
      //   2780	2821	502	java/io/IOException
      //   2824	2865	502	java/io/IOException
      //   2868	2909	502	java/io/IOException
      //   2912	2922	502	java/io/IOException
      //   2925	2957	502	java/io/IOException
      //   2962	2989	502	java/io/IOException
      //   2989	3014	502	java/io/IOException
      //   3014	3035	502	java/io/IOException
      //   3035	3082	502	java/io/IOException
      //   3082	3125	502	java/io/IOException
      //   3125	3143	502	java/io/IOException
      //   3143	3191	502	java/io/IOException
      //   3193	3229	502	java/io/IOException
      //   3234	3239	502	java/io/IOException
      //   3239	3289	502	java/io/IOException
      //   3292	3298	502	java/io/IOException
      //   3301	3311	502	java/io/IOException
      //   3314	3324	502	java/io/IOException
      //   3329	3335	502	java/io/IOException
      //   13	33	546	java/lang/Exception
      //   637	644	1642	org/json/JSONException
      //   442	479	1674	java/lang/OutOfMemoryError
      //   479	486	1674	java/lang/OutOfMemoryError
      //   491	499	1674	java/lang/OutOfMemoryError
      //   603	634	1674	java/lang/OutOfMemoryError
      //   637	644	1674	java/lang/OutOfMemoryError
      //   653	683	1674	java/lang/OutOfMemoryError
      //   683	696	1674	java/lang/OutOfMemoryError
      //   699	709	1674	java/lang/OutOfMemoryError
      //   713	728	1674	java/lang/OutOfMemoryError
      //   732	740	1674	java/lang/OutOfMemoryError
      //   744	751	1674	java/lang/OutOfMemoryError
      //   755	768	1674	java/lang/OutOfMemoryError
      //   777	820	1674	java/lang/OutOfMemoryError
      //   820	842	1674	java/lang/OutOfMemoryError
      //   842	891	1674	java/lang/OutOfMemoryError
      //   891	957	1674	java/lang/OutOfMemoryError
      //   957	1017	1674	java/lang/OutOfMemoryError
      //   1017	1069	1674	java/lang/OutOfMemoryError
      //   1075	1104	1674	java/lang/OutOfMemoryError
      //   1104	1109	1674	java/lang/OutOfMemoryError
      //   1109	1152	1674	java/lang/OutOfMemoryError
      //   1156	1209	1674	java/lang/OutOfMemoryError
      //   1209	1255	1674	java/lang/OutOfMemoryError
      //   1260	1294	1674	java/lang/OutOfMemoryError
      //   1294	1309	1674	java/lang/OutOfMemoryError
      //   1315	1344	1674	java/lang/OutOfMemoryError
      //   1347	1374	1674	java/lang/OutOfMemoryError
      //   1374	1389	1674	java/lang/OutOfMemoryError
      //   1394	1423	1674	java/lang/OutOfMemoryError
      //   1425	1452	1674	java/lang/OutOfMemoryError
      //   1452	1467	1674	java/lang/OutOfMemoryError
      //   1472	1501	1674	java/lang/OutOfMemoryError
      //   1503	1530	1674	java/lang/OutOfMemoryError
      //   1530	1570	1674	java/lang/OutOfMemoryError
      //   1570	1583	1674	java/lang/OutOfMemoryError
      //   1585	1635	1674	java/lang/OutOfMemoryError
      //   1644	1671	1674	java/lang/OutOfMemoryError
      //   1745	1755	1674	java/lang/OutOfMemoryError
      //   1762	1772	1674	java/lang/OutOfMemoryError
      //   1777	1783	1674	java/lang/OutOfMemoryError
      //   2038	2044	1674	java/lang/OutOfMemoryError
      //   2049	2055	1674	java/lang/OutOfMemoryError
      //   2058	2103	1674	java/lang/OutOfMemoryError
      //   2106	2131	1674	java/lang/OutOfMemoryError
      //   2141	2177	1674	java/lang/OutOfMemoryError
      //   2180	2218	1674	java/lang/OutOfMemoryError
      //   2228	2281	1674	java/lang/OutOfMemoryError
      //   2284	2340	1674	java/lang/OutOfMemoryError
      //   2349	2384	1674	java/lang/OutOfMemoryError
      //   2387	2421	1674	java/lang/OutOfMemoryError
      //   2433	2462	1674	java/lang/OutOfMemoryError
      //   2477	2506	1674	java/lang/OutOfMemoryError
      //   2521	2550	1674	java/lang/OutOfMemoryError
      //   2557	2567	1674	java/lang/OutOfMemoryError
      //   2570	2611	1674	java/lang/OutOfMemoryError
      //   2616	2633	1674	java/lang/OutOfMemoryError
      //   2633	2673	1674	java/lang/OutOfMemoryError
      //   2673	2686	1674	java/lang/OutOfMemoryError
      //   2688	2729	1674	java/lang/OutOfMemoryError
      //   2736	2777	1674	java/lang/OutOfMemoryError
      //   2780	2821	1674	java/lang/OutOfMemoryError
      //   2824	2865	1674	java/lang/OutOfMemoryError
      //   2868	2909	1674	java/lang/OutOfMemoryError
      //   2912	2922	1674	java/lang/OutOfMemoryError
      //   2925	2957	1674	java/lang/OutOfMemoryError
      //   2962	2989	1674	java/lang/OutOfMemoryError
      //   2989	3014	1674	java/lang/OutOfMemoryError
      //   3014	3035	1674	java/lang/OutOfMemoryError
      //   3035	3082	1674	java/lang/OutOfMemoryError
      //   3082	3125	1674	java/lang/OutOfMemoryError
      //   3125	3143	1674	java/lang/OutOfMemoryError
      //   3143	3191	1674	java/lang/OutOfMemoryError
      //   3193	3229	1674	java/lang/OutOfMemoryError
      //   3234	3239	1674	java/lang/OutOfMemoryError
      //   3239	3289	1674	java/lang/OutOfMemoryError
      //   3292	3298	1674	java/lang/OutOfMemoryError
      //   3301	3311	1674	java/lang/OutOfMemoryError
      //   3314	3324	1674	java/lang/OutOfMemoryError
      //   3329	3335	1674	java/lang/OutOfMemoryError
      //   147	185	1701	java/io/IOException
      //   185	216	1701	java/io/IOException
      //   216	227	1701	java/io/IOException
      //   227	388	1701	java/io/IOException
      //   388	442	1701	java/io/IOException
      //   504	545	1701	java/io/IOException
      //   577	587	1701	java/io/IOException
      //   590	600	1701	java/io/IOException
      //   1676	1700	1701	java/io/IOException
      //   3348	3372	1701	java/io/IOException
      //   699	709	1775	org/json/JSONException
      //   713	728	1775	org/json/JSONException
      //   732	740	1775	org/json/JSONException
      //   744	751	1775	org/json/JSONException
      //   755	768	1775	org/json/JSONException
      //   1762	1772	1775	org/json/JSONException
      //   147	185	1786	java/lang/Exception
      //   185	216	1786	java/lang/Exception
      //   216	227	1786	java/lang/Exception
      //   227	388	1786	java/lang/Exception
      //   388	442	1786	java/lang/Exception
      //   442	479	1786	java/lang/Exception
      //   479	486	1786	java/lang/Exception
      //   491	499	1786	java/lang/Exception
      //   504	545	1786	java/lang/Exception
      //   577	587	1786	java/lang/Exception
      //   590	600	1786	java/lang/Exception
      //   603	634	1786	java/lang/Exception
      //   637	644	1786	java/lang/Exception
      //   653	683	1786	java/lang/Exception
      //   683	696	1786	java/lang/Exception
      //   699	709	1786	java/lang/Exception
      //   713	728	1786	java/lang/Exception
      //   732	740	1786	java/lang/Exception
      //   744	751	1786	java/lang/Exception
      //   755	768	1786	java/lang/Exception
      //   777	820	1786	java/lang/Exception
      //   820	842	1786	java/lang/Exception
      //   1644	1671	1786	java/lang/Exception
      //   1676	1700	1786	java/lang/Exception
      //   1745	1755	1786	java/lang/Exception
      //   1762	1772	1786	java/lang/Exception
      //   1777	1783	1786	java/lang/Exception
      //   2038	2044	1786	java/lang/Exception
      //   2049	2055	1786	java/lang/Exception
      //   2616	2633	1786	java/lang/Exception
      //   2962	2989	1786	java/lang/Exception
      //   3014	3035	1786	java/lang/Exception
      //   3292	3298	1786	java/lang/Exception
      //   3329	3335	1786	java/lang/Exception
      //   3348	3372	1786	java/lang/Exception
      //   777	820	2036	org/json/JSONException
      //   820	842	2047	org/json/JSONException
      //   1530	1570	2614	java/lang/Exception
      //   1570	1583	2614	java/lang/Exception
      //   1585	1635	2614	java/lang/Exception
      //   2557	2567	2614	java/lang/Exception
      //   2570	2611	2614	java/lang/Exception
      //   2736	2777	2614	java/lang/Exception
      //   2780	2821	2614	java/lang/Exception
      //   2824	2865	2614	java/lang/Exception
      //   2868	2909	2614	java/lang/Exception
      //   2633	2673	2960	java/lang/Exception
      //   2673	2686	2960	java/lang/Exception
      //   2688	2729	2960	java/lang/Exception
      //   2912	2922	2960	java/lang/Exception
      //   2925	2957	2960	java/lang/Exception
      //   3239	3289	3290	java/lang/Exception
      //   3035	3082	3327	java/lang/Exception
      //   3082	3125	3327	java/lang/Exception
      //   3125	3143	3327	java/lang/Exception
      //   3301	3311	3327	java/lang/Exception
      //   3314	3324	3327	java/lang/Exception
      //   3460	3466	3473	java/lang/InterruptedException
      //   3143	3191	3511	java/lang/Exception
      //   3193	3229	3511	java/lang/Exception
      //   3234	3239	3511	java/lang/Exception
      //   3035	3082	3516	org/json/JSONException
      //   3082	3125	3516	org/json/JSONException
      //   3125	3143	3516	org/json/JSONException
      //   3301	3311	3516	org/json/JSONException
      //   3314	3324	3516	org/json/JSONException
      //   2989	3014	3521	java/lang/Exception
      //   1452	1467	3526	java/lang/Exception
      //   1472	1501	3526	java/lang/Exception
      //   1503	1530	3526	java/lang/Exception
      //   2521	2550	3526	java/lang/Exception
      //   1374	1389	3531	java/lang/Exception
      //   1394	1423	3531	java/lang/Exception
      //   1425	1452	3531	java/lang/Exception
      //   2477	2506	3531	java/lang/Exception
      //   1294	1309	3536	java/lang/Exception
      //   1315	1344	3536	java/lang/Exception
      //   1347	1374	3536	java/lang/Exception
      //   2433	2462	3536	java/lang/Exception
      //   891	957	3541	java/lang/Exception
      //   842	891	3546	java/lang/Exception
      //   957	1017	3551	java/lang/Exception
      //   2058	2103	3551	java/lang/Exception
      //   2106	2131	3551	java/lang/Exception
      //   1017	1069	3556	java/lang/Exception
      //   1075	1104	3556	java/lang/Exception
      //   1104	1109	3556	java/lang/Exception
      //   2141	2177	3556	java/lang/Exception
      //   2180	2218	3556	java/lang/Exception
      //   1109	1152	3561	java/lang/Exception
      //   1156	1209	3561	java/lang/Exception
      //   2228	2281	3561	java/lang/Exception
      //   2284	2340	3561	java/lang/Exception
      //   1209	1255	3566	java/lang/Exception
      //   1260	1294	3566	java/lang/Exception
      //   2349	2384	3566	java/lang/Exception
      //   2387	2421	3566	java/lang/Exception
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
        //   5: invokestatic 26	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
        //   8: ifnull +17 -> 25
        //   11: iload_2
        //   12: istore_1
        //   13: invokestatic 26	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
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
        //   36: invokestatic 49	com/kochava/android/tracker/Feature:access$402	(Ljava/lang/String;)Ljava/lang/String;
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
        //   59: invokestatic 26	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
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
        //   94: invokestatic 26	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
        //   97: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: ldc 62
        //   102: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   108: invokespecial 69	java/net/URL:<init>	(Ljava/lang/String;)V
        //   111: invokevirtual 73	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   114: invokestatic 78	com/newrelic/agent/android/instrumentation/HttpInstrumentation:openConnection	(Ljava/net/URLConnection;)Ljava/net/URLConnection;
        //   117: checkcast 80	javax/net/ssl/HttpsURLConnection
        //   120: astore 8
        //   122: iload_2
        //   123: istore_1
        //   124: aload 8
        //   126: ldc 82
        //   128: invokestatic 85	com/kochava/android/tracker/Feature:access$500	()Ljava/lang/String;
        //   131: invokevirtual 89	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   134: iload_2
        //   135: istore_1
        //   136: aload 8
        //   138: ldc 91
        //   140: ldc 93
        //   142: invokevirtual 89	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   145: iload_2
        //   146: istore_1
        //   147: aload 8
        //   149: ldc 95
        //   151: invokevirtual 98	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   154: iload_2
        //   155: istore_1
        //   156: aload 8
        //   158: sipush 30000
        //   161: invokevirtual 102	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   164: iload_2
        //   165: istore_1
        //   166: aload 8
        //   168: sipush 30000
        //   171: invokevirtual 105	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   174: iload_2
        //   175: istore_1
        //   176: aload 8
        //   178: iconst_1
        //   179: invokevirtual 109	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   182: iload_2
        //   183: istore_1
        //   184: aload 8
        //   186: iconst_1
        //   187: invokevirtual 112	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   190: iload_2
        //   191: istore_1
        //   192: aload 8
        //   194: invokevirtual 115	javax/net/ssl/HttpsURLConnection:connect	()V
        //   197: iload_2
        //   198: istore_1
        //   199: new 117	org/json/JSONObject
        //   202: dup
        //   203: invokespecial 118	org/json/JSONObject:<init>	()V
        //   206: astore 7
        //   208: iload_2
        //   209: istore_1
        //   210: aload 7
        //   212: ldc 120
        //   214: ldc 122
        //   216: invokevirtual 126	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   219: pop
        //   220: iload_2
        //   221: istore_1
        //   222: aload 7
        //   224: ldc -128
        //   226: invokestatic 131	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
        //   229: invokevirtual 126	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   232: pop
        //   233: iload_2
        //   234: istore_1
        //   235: aload 7
        //   237: ldc -123
        //   239: invokestatic 136	com/kochava/android/tracker/Feature:access$2700	()Ljava/lang/String;
        //   242: invokevirtual 126	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   245: pop
        //   246: iload_2
        //   247: istore_1
        //   248: aload 7
        //   250: ldc -118
        //   252: new 51	java/lang/StringBuilder
        //   255: dup
        //   256: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   259: ldc -116
        //   261: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: getstatic 144	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
        //   267: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   273: invokevirtual 126	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   276: pop
        //   277: iload_2
        //   278: istore_1
        //   279: aload 7
        //   281: ldc -110
        //   283: ldc -108
        //   285: invokevirtual 126	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   288: pop
        //   289: iload_2
        //   290: istore_1
        //   291: aload 7
        //   293: instanceof 117
        //   296: ifne +229 -> 525
        //   299: iload_2
        //   300: istore_1
        //   301: aload 7
        //   303: invokevirtual 149	org/json/JSONObject:toString	()Ljava/lang/String;
        //   306: astore 7
        //   308: iload_2
        //   309: istore_1
        //   310: new 51	java/lang/StringBuilder
        //   313: dup
        //   314: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   317: ldc -105
        //   319: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: aload 7
        //   324: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   327: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   330: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   333: iload_2
        //   334: istore_1
        //   335: ldc -103
        //   337: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   340: iload_2
        //   341: istore_1
        //   342: new 155	java/io/OutputStreamWriter
        //   345: dup
        //   346: aload 8
        //   348: invokevirtual 159	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   351: invokespecial 162	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   354: astore 9
        //   356: iload_2
        //   357: istore_1
        //   358: aload 9
        //   360: aload 7
        //   362: invokevirtual 165	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   365: iload_2
        //   366: istore_1
        //   367: aload 9
        //   369: invokevirtual 168	java/io/OutputStreamWriter:close	()V
        //   372: iload_2
        //   373: istore 4
        //   375: iload_2
        //   376: istore 6
        //   378: iload_2
        //   379: istore_1
        //   380: ldc -86
        //   382: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   385: iload_2
        //   386: istore 4
        //   388: iload_2
        //   389: istore 6
        //   391: iload_2
        //   392: istore_1
        //   393: new 172	java/lang/StringBuffer
        //   396: dup
        //   397: ldc -82
        //   399: invokespecial 175	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   402: astore 7
        //   404: iload_2
        //   405: istore 4
        //   407: iload_2
        //   408: istore 6
        //   410: iload_2
        //   411: istore_1
        //   412: new 177	java/io/BufferedReader
        //   415: dup
        //   416: new 179	java/io/InputStreamReader
        //   419: dup
        //   420: aload 8
        //   422: invokevirtual 183	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
        //   425: invokespecial 186	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   428: invokespecial 189	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   431: astore 8
        //   433: iload_2
        //   434: istore 4
        //   436: iload_2
        //   437: istore 6
        //   439: iload_2
        //   440: istore_1
        //   441: aload 8
        //   443: invokevirtual 192	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   446: astore 9
        //   448: aload 9
        //   450: ifnull +90 -> 540
        //   453: iload_2
        //   454: istore 4
        //   456: iload_2
        //   457: istore 6
        //   459: iload_2
        //   460: istore_1
        //   461: aload 7
        //   463: aload 9
        //   465: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   468: pop
        //   469: goto -36 -> 433
        //   472: astore 7
        //   474: iload 4
        //   476: istore_1
        //   477: aload 7
        //   479: invokevirtual 199	java/lang/Object:getClass	()Ljava/lang/Class;
        //   482: ldc -55
        //   484: invokevirtual 205	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   487: ifeq +785 -> 1272
        //   490: iload 4
        //   492: istore_1
        //   493: new 51	java/lang/StringBuilder
        //   496: dup
        //   497: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   500: ldc -49
        //   502: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   505: aload 7
        //   507: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   510: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   513: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   516: iload 4
        //   518: istore_1
        //   519: aload 7
        //   521: invokestatic 217	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
        //   524: return
        //   525: iload_2
        //   526: istore_1
        //   527: aload 7
        //   529: checkcast 117	org/json/JSONObject
        //   532: invokestatic 222	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   535: astore 7
        //   537: goto -229 -> 308
        //   540: iload_2
        //   541: istore 4
        //   543: iload_2
        //   544: istore 6
        //   546: iload_2
        //   547: istore_1
        //   548: aload 7
        //   550: invokevirtual 223	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   553: astore 7
        //   555: iload_2
        //   556: istore 4
        //   558: iload_2
        //   559: istore 6
        //   561: iload_2
        //   562: istore_1
        //   563: new 51	java/lang/StringBuilder
        //   566: dup
        //   567: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   570: ldc -31
        //   572: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   575: aload 7
        //   577: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   580: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   583: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   586: aconst_null
        //   587: astore 8
        //   589: iload_2
        //   590: istore 4
        //   592: iload_2
        //   593: istore 6
        //   595: iload_2
        //   596: istore_1
        //   597: aload 7
        //   599: invokestatic 229	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   602: astore 7
        //   604: aload 7
        //   606: astore 8
        //   608: iload_2
        //   609: istore 5
        //   611: aload 8
        //   613: ifnull +396 -> 1009
        //   616: iload_2
        //   617: istore 4
        //   619: iload_2
        //   620: istore 6
        //   622: iload_2
        //   623: istore_1
        //   624: new 51	java/lang/StringBuilder
        //   627: dup
        //   628: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   631: ldc -25
        //   633: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   636: astore 9
        //   638: iload_2
        //   639: istore 4
        //   641: iload_2
        //   642: istore 6
        //   644: iload_2
        //   645: istore_1
        //   646: aload 8
        //   648: instanceof 117
        //   651: ifne +483 -> 1134
        //   654: iload_2
        //   655: istore 4
        //   657: iload_2
        //   658: istore 6
        //   660: iload_2
        //   661: istore_1
        //   662: aload 8
        //   664: invokevirtual 149	org/json/JSONObject:toString	()Ljava/lang/String;
        //   667: astore 7
        //   669: iload_2
        //   670: istore 4
        //   672: iload_2
        //   673: istore 6
        //   675: iload_2
        //   676: istore_1
        //   677: aload 9
        //   679: aload 7
        //   681: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   684: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   687: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   690: aconst_null
        //   691: astore 7
        //   693: iload_2
        //   694: istore 4
        //   696: iload_2
        //   697: istore 6
        //   699: iload_2
        //   700: istore_1
        //   701: aload 8
        //   703: ldc -23
        //   705: invokevirtual 236	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   708: astore 8
        //   710: iload_2
        //   711: istore 4
        //   713: iload_2
        //   714: istore 6
        //   716: aload 8
        //   718: astore 7
        //   720: iload_2
        //   721: istore_1
        //   722: new 51	java/lang/StringBuilder
        //   725: dup
        //   726: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   729: ldc -18
        //   731: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   734: astore 10
        //   736: iload_2
        //   737: istore 4
        //   739: iload_2
        //   740: istore 6
        //   742: aload 8
        //   744: astore 7
        //   746: iload_2
        //   747: istore_1
        //   748: aload 8
        //   750: instanceof 117
        //   753: ifne +402 -> 1155
        //   756: iload_2
        //   757: istore 4
        //   759: iload_2
        //   760: istore 6
        //   762: aload 8
        //   764: astore 7
        //   766: iload_2
        //   767: istore_1
        //   768: aload 8
        //   770: invokevirtual 149	org/json/JSONObject:toString	()Ljava/lang/String;
        //   773: astore 9
        //   775: iload_2
        //   776: istore 4
        //   778: iload_2
        //   779: istore 6
        //   781: aload 8
        //   783: astore 7
        //   785: iload_2
        //   786: istore_1
        //   787: aload 10
        //   789: aload 9
        //   791: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   794: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   797: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   800: aload 8
        //   802: astore 7
        //   804: iload_2
        //   805: istore 5
        //   807: aload 7
        //   809: ifnull +200 -> 1009
        //   812: ldc -82
        //   814: astore 8
        //   816: iload_2
        //   817: istore 4
        //   819: iload_2
        //   820: istore 6
        //   822: iload_2
        //   823: istore_1
        //   824: aload 7
        //   826: ldc -16
        //   828: invokevirtual 243	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   831: astore 9
        //   833: aload 9
        //   835: astore 8
        //   837: iload_2
        //   838: istore 4
        //   840: iload_2
        //   841: istore 6
        //   843: iload_2
        //   844: istore_1
        //   845: aload 7
        //   847: ldc -11
        //   849: invokevirtual 249	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   852: istore_3
        //   853: iload_3
        //   854: istore 5
        //   856: iload_3
        //   857: ifge +152 -> 1009
        //   860: iload_3
        //   861: istore 4
        //   863: iload_3
        //   864: istore 6
        //   866: iload_3
        //   867: istore_1
        //   868: iload_3
        //   869: istore_2
        //   870: invokestatic 253	com/kochava/android/tracker/Feature:access$2800	()Landroid/content/SharedPreferences;
        //   873: invokeinterface 259 1 0
        //   878: ldc_w 261
        //   881: aload 8
        //   883: invokeinterface 267 3 0
        //   888: invokeinterface 270 1 0
        //   893: pop
        //   894: iload_3
        //   895: istore 4
        //   897: iload_3
        //   898: istore 5
        //   900: iload_3
        //   901: istore 6
        //   903: iload_3
        //   904: istore_1
        //   905: iload_3
        //   906: istore_2
        //   907: invokestatic 274	com/kochava/android/tracker/Feature:access$2900	()Landroid/os/Handler;
        //   910: ifnull +99 -> 1009
        //   913: iload_3
        //   914: istore 4
        //   916: iload_3
        //   917: istore 6
        //   919: iload_3
        //   920: istore_1
        //   921: iload_3
        //   922: istore_2
        //   923: invokestatic 280	android/os/Message:obtain	()Landroid/os/Message;
        //   926: astore 7
        //   928: iload_3
        //   929: istore 4
        //   931: iload_3
        //   932: istore 6
        //   934: iload_3
        //   935: istore_1
        //   936: iload_3
        //   937: istore_2
        //   938: new 282	android/os/Bundle
        //   941: dup
        //   942: invokespecial 283	android/os/Bundle:<init>	()V
        //   945: astore 9
        //   947: iload_3
        //   948: istore 4
        //   950: iload_3
        //   951: istore 6
        //   953: iload_3
        //   954: istore_1
        //   955: iload_3
        //   956: istore_2
        //   957: aload 9
        //   959: ldc_w 261
        //   962: aload 8
        //   964: invokevirtual 284	java/lang/String:toString	()Ljava/lang/String;
        //   967: invokevirtual 286	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   970: iload_3
        //   971: istore 4
        //   973: iload_3
        //   974: istore 6
        //   976: iload_3
        //   977: istore_1
        //   978: iload_3
        //   979: istore_2
        //   980: aload 7
        //   982: aload 9
        //   984: invokevirtual 290	android/os/Message:setData	(Landroid/os/Bundle;)V
        //   987: iload_3
        //   988: istore 4
        //   990: iload_3
        //   991: istore 6
        //   993: iload_3
        //   994: istore_1
        //   995: iload_3
        //   996: istore_2
        //   997: invokestatic 274	com/kochava/android/tracker/Feature:access$2900	()Landroid/os/Handler;
        //   1000: aload 7
        //   1002: invokevirtual 296	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1005: pop
        //   1006: iload_3
        //   1007: istore 5
        //   1009: iload 5
        //   1011: ifle +313 -> 1324
        //   1014: iload 5
        //   1016: invokestatic 299	com/kochava/android/tracker/Feature:sendKVQuery	(I)V
        //   1019: return
        //   1020: astore 7
        //   1022: iload_2
        //   1023: istore 4
        //   1025: iload_2
        //   1026: istore 6
        //   1028: iload_2
        //   1029: istore_1
        //   1030: new 51	java/lang/StringBuilder
        //   1033: dup
        //   1034: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1037: ldc_w 301
        //   1040: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1043: aload 7
        //   1045: invokevirtual 302	org/json/JSONException:toString	()Ljava/lang/String;
        //   1048: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1051: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1054: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1057: goto -449 -> 608
        //   1060: astore 7
        //   1062: iload 6
        //   1064: istore_1
        //   1065: new 51	java/lang/StringBuilder
        //   1068: dup
        //   1069: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1072: ldc_w 304
        //   1075: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1078: aload 7
        //   1080: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1083: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1086: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1089: return
        //   1090: astore 7
        //   1092: aload 7
        //   1094: invokevirtual 199	java/lang/Object:getClass	()Ljava/lang/Class;
        //   1097: ldc -55
        //   1099: invokevirtual 205	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   1102: ifeq +198 -> 1300
        //   1105: new 51	java/lang/StringBuilder
        //   1108: dup
        //   1109: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1112: ldc -49
        //   1114: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1117: aload 7
        //   1119: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1122: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1125: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1128: aload 7
        //   1130: invokestatic 217	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
        //   1133: return
        //   1134: iload_2
        //   1135: istore 4
        //   1137: iload_2
        //   1138: istore 6
        //   1140: iload_2
        //   1141: istore_1
        //   1142: aload 8
        //   1144: checkcast 117	org/json/JSONObject
        //   1147: invokestatic 222	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   1150: astore 7
        //   1152: goto -483 -> 669
        //   1155: iload_2
        //   1156: istore 4
        //   1158: iload_2
        //   1159: istore 6
        //   1161: aload 8
        //   1163: astore 7
        //   1165: iload_2
        //   1166: istore_1
        //   1167: aload 8
        //   1169: checkcast 117	org/json/JSONObject
        //   1172: invokestatic 222	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   1175: astore 9
        //   1177: goto -402 -> 775
        //   1180: astore 8
        //   1182: iload_2
        //   1183: istore 4
        //   1185: iload_2
        //   1186: istore 6
        //   1188: iload_2
        //   1189: istore_1
        //   1190: ldc_w 306
        //   1193: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   1196: goto -392 -> 804
        //   1199: astore 7
        //   1201: new 51	java/lang/StringBuilder
        //   1204: dup
        //   1205: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1208: ldc_w 308
        //   1211: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1214: aload 7
        //   1216: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1219: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1222: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1225: iload_1
        //   1226: istore 5
        //   1228: goto -219 -> 1009
        //   1231: astore 9
        //   1233: iload_2
        //   1234: istore 4
        //   1236: iload_2
        //   1237: istore 6
        //   1239: iload_2
        //   1240: istore_1
        //   1241: ldc_w 310
        //   1244: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1247: goto -410 -> 837
        //   1250: astore 7
        //   1252: iload_2
        //   1253: istore 4
        //   1255: iload_2
        //   1256: istore 6
        //   1258: iload_2
        //   1259: istore_1
        //   1260: ldc_w 312
        //   1263: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1266: iload_2
        //   1267: istore 5
        //   1269: goto -260 -> 1009
        //   1272: iload 4
        //   1274: istore_1
        //   1275: new 51	java/lang/StringBuilder
        //   1278: dup
        //   1279: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1282: ldc_w 314
        //   1285: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1288: aload 7
        //   1290: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1293: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1296: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1299: return
        //   1300: new 51	java/lang/StringBuilder
        //   1303: dup
        //   1304: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1307: ldc_w 314
        //   1310: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1313: aload 7
        //   1315: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1318: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1321: invokestatic 213	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1324: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1325	0	this	4
        //   4	1271	1	i	int
        //   2	1265	2	j	int
        //   852	155	3	k	int
        //   373	900	4	m	int
        //   609	659	5	n	int
        //   376	881	6	i1	int
        //   206	256	7	localObject1	Object
        //   472	56	7	localIOException1	IOException
        //   535	466	7	localObject2	Object
        //   1020	24	7	localJSONException1	JSONException
        //   1060	19	7	localOutOfMemoryError	OutOfMemoryError
        //   1090	39	7	localIOException2	IOException
        //   1150	14	7	localObject3	Object
        //   1199	16	7	localException	Exception
        //   1250	64	7	localJSONException2	JSONException
        //   120	1048	8	localObject4	Object
        //   1180	1	8	localJSONException3	JSONException
        //   354	822	9	localObject5	Object
        //   1231	1	9	localJSONException4	JSONException
        //   734	54	10	localStringBuilder	StringBuilder
        // Exception table:
        //   from	to	target	type
        //   380	385	472	java/io/IOException
        //   393	404	472	java/io/IOException
        //   412	433	472	java/io/IOException
        //   441	448	472	java/io/IOException
        //   461	469	472	java/io/IOException
        //   548	555	472	java/io/IOException
        //   563	586	472	java/io/IOException
        //   597	604	472	java/io/IOException
        //   624	638	472	java/io/IOException
        //   646	654	472	java/io/IOException
        //   662	669	472	java/io/IOException
        //   677	690	472	java/io/IOException
        //   701	710	472	java/io/IOException
        //   722	736	472	java/io/IOException
        //   748	756	472	java/io/IOException
        //   768	775	472	java/io/IOException
        //   787	800	472	java/io/IOException
        //   824	833	472	java/io/IOException
        //   845	853	472	java/io/IOException
        //   870	894	472	java/io/IOException
        //   907	913	472	java/io/IOException
        //   923	928	472	java/io/IOException
        //   938	947	472	java/io/IOException
        //   957	970	472	java/io/IOException
        //   980	987	472	java/io/IOException
        //   997	1006	472	java/io/IOException
        //   1030	1057	472	java/io/IOException
        //   1142	1152	472	java/io/IOException
        //   1167	1177	472	java/io/IOException
        //   1190	1196	472	java/io/IOException
        //   1241	1247	472	java/io/IOException
        //   1260	1266	472	java/io/IOException
        //   597	604	1020	org/json/JSONException
        //   380	385	1060	java/lang/OutOfMemoryError
        //   393	404	1060	java/lang/OutOfMemoryError
        //   412	433	1060	java/lang/OutOfMemoryError
        //   441	448	1060	java/lang/OutOfMemoryError
        //   461	469	1060	java/lang/OutOfMemoryError
        //   548	555	1060	java/lang/OutOfMemoryError
        //   563	586	1060	java/lang/OutOfMemoryError
        //   597	604	1060	java/lang/OutOfMemoryError
        //   624	638	1060	java/lang/OutOfMemoryError
        //   646	654	1060	java/lang/OutOfMemoryError
        //   662	669	1060	java/lang/OutOfMemoryError
        //   677	690	1060	java/lang/OutOfMemoryError
        //   701	710	1060	java/lang/OutOfMemoryError
        //   722	736	1060	java/lang/OutOfMemoryError
        //   748	756	1060	java/lang/OutOfMemoryError
        //   768	775	1060	java/lang/OutOfMemoryError
        //   787	800	1060	java/lang/OutOfMemoryError
        //   824	833	1060	java/lang/OutOfMemoryError
        //   845	853	1060	java/lang/OutOfMemoryError
        //   870	894	1060	java/lang/OutOfMemoryError
        //   907	913	1060	java/lang/OutOfMemoryError
        //   923	928	1060	java/lang/OutOfMemoryError
        //   938	947	1060	java/lang/OutOfMemoryError
        //   957	970	1060	java/lang/OutOfMemoryError
        //   980	987	1060	java/lang/OutOfMemoryError
        //   997	1006	1060	java/lang/OutOfMemoryError
        //   1030	1057	1060	java/lang/OutOfMemoryError
        //   1142	1152	1060	java/lang/OutOfMemoryError
        //   1167	1177	1060	java/lang/OutOfMemoryError
        //   1190	1196	1060	java/lang/OutOfMemoryError
        //   1241	1247	1060	java/lang/OutOfMemoryError
        //   1260	1266	1060	java/lang/OutOfMemoryError
        //   5	11	1090	java/io/IOException
        //   13	25	1090	java/io/IOException
        //   27	32	1090	java/io/IOException
        //   34	40	1090	java/io/IOException
        //   42	76	1090	java/io/IOException
        //   78	122	1090	java/io/IOException
        //   124	134	1090	java/io/IOException
        //   136	145	1090	java/io/IOException
        //   147	154	1090	java/io/IOException
        //   156	164	1090	java/io/IOException
        //   166	174	1090	java/io/IOException
        //   176	182	1090	java/io/IOException
        //   184	190	1090	java/io/IOException
        //   192	197	1090	java/io/IOException
        //   199	208	1090	java/io/IOException
        //   210	220	1090	java/io/IOException
        //   222	233	1090	java/io/IOException
        //   235	246	1090	java/io/IOException
        //   248	277	1090	java/io/IOException
        //   279	289	1090	java/io/IOException
        //   291	299	1090	java/io/IOException
        //   301	308	1090	java/io/IOException
        //   310	333	1090	java/io/IOException
        //   335	340	1090	java/io/IOException
        //   342	356	1090	java/io/IOException
        //   358	365	1090	java/io/IOException
        //   367	372	1090	java/io/IOException
        //   477	490	1090	java/io/IOException
        //   493	516	1090	java/io/IOException
        //   519	524	1090	java/io/IOException
        //   527	537	1090	java/io/IOException
        //   1065	1089	1090	java/io/IOException
        //   1275	1299	1090	java/io/IOException
        //   701	710	1180	org/json/JSONException
        //   722	736	1180	org/json/JSONException
        //   748	756	1180	org/json/JSONException
        //   768	775	1180	org/json/JSONException
        //   787	800	1180	org/json/JSONException
        //   1167	1177	1180	org/json/JSONException
        //   5	11	1199	java/lang/Exception
        //   13	25	1199	java/lang/Exception
        //   27	32	1199	java/lang/Exception
        //   34	40	1199	java/lang/Exception
        //   42	76	1199	java/lang/Exception
        //   78	122	1199	java/lang/Exception
        //   124	134	1199	java/lang/Exception
        //   136	145	1199	java/lang/Exception
        //   147	154	1199	java/lang/Exception
        //   156	164	1199	java/lang/Exception
        //   166	174	1199	java/lang/Exception
        //   176	182	1199	java/lang/Exception
        //   184	190	1199	java/lang/Exception
        //   192	197	1199	java/lang/Exception
        //   199	208	1199	java/lang/Exception
        //   210	220	1199	java/lang/Exception
        //   222	233	1199	java/lang/Exception
        //   235	246	1199	java/lang/Exception
        //   248	277	1199	java/lang/Exception
        //   279	289	1199	java/lang/Exception
        //   291	299	1199	java/lang/Exception
        //   301	308	1199	java/lang/Exception
        //   310	333	1199	java/lang/Exception
        //   335	340	1199	java/lang/Exception
        //   342	356	1199	java/lang/Exception
        //   358	365	1199	java/lang/Exception
        //   367	372	1199	java/lang/Exception
        //   380	385	1199	java/lang/Exception
        //   393	404	1199	java/lang/Exception
        //   412	433	1199	java/lang/Exception
        //   441	448	1199	java/lang/Exception
        //   461	469	1199	java/lang/Exception
        //   477	490	1199	java/lang/Exception
        //   493	516	1199	java/lang/Exception
        //   519	524	1199	java/lang/Exception
        //   527	537	1199	java/lang/Exception
        //   548	555	1199	java/lang/Exception
        //   563	586	1199	java/lang/Exception
        //   597	604	1199	java/lang/Exception
        //   624	638	1199	java/lang/Exception
        //   646	654	1199	java/lang/Exception
        //   662	669	1199	java/lang/Exception
        //   677	690	1199	java/lang/Exception
        //   701	710	1199	java/lang/Exception
        //   722	736	1199	java/lang/Exception
        //   748	756	1199	java/lang/Exception
        //   768	775	1199	java/lang/Exception
        //   787	800	1199	java/lang/Exception
        //   824	833	1199	java/lang/Exception
        //   845	853	1199	java/lang/Exception
        //   870	894	1199	java/lang/Exception
        //   907	913	1199	java/lang/Exception
        //   923	928	1199	java/lang/Exception
        //   938	947	1199	java/lang/Exception
        //   957	970	1199	java/lang/Exception
        //   980	987	1199	java/lang/Exception
        //   997	1006	1199	java/lang/Exception
        //   1030	1057	1199	java/lang/Exception
        //   1065	1089	1199	java/lang/Exception
        //   1142	1152	1199	java/lang/Exception
        //   1167	1177	1199	java/lang/Exception
        //   1190	1196	1199	java/lang/Exception
        //   1241	1247	1199	java/lang/Exception
        //   1260	1266	1199	java/lang/Exception
        //   1275	1299	1199	java/lang/Exception
        //   824	833	1231	org/json/JSONException
        //   845	853	1250	org/json/JSONException
        //   870	894	1250	org/json/JSONException
        //   907	913	1250	org/json/JSONException
        //   923	928	1250	org/json/JSONException
        //   938	947	1250	org/json/JSONException
        //   957	970	1250	org/json/JSONException
        //   980	987	1250	org/json/JSONException
        //   997	1006	1250	org/json/JSONException
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
            localJSONObject1.put("kochava_device_id", Feature.access$2700());
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
  
  private void fireEvent(String paramString, Map<String, String> paramMap)
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
          Feature.access$3902(false);
        }
      }, 60000L);
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
    executor.submit(new TrackTask(null));
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
    //   8: getstatic 401	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 446	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 89
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1092	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1097 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1100 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1103 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 89
    //   75: invokeinterface 1107 2 0
    //   80: invokeinterface 1108 2 0
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
    //   99: invokeinterface 1100 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1103 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 641	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1110
    //   130: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1100 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1103 1 0
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
    //   190: invokeinterface 1100 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1103 1 0
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
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            Feature.access$2202(true);
            Object localObject2 = this.val$e.getMessage();
            Object localObject1 = new JSONObject();
            ((JSONObject)localObject1).put("message", localObject2);
            ((JSONObject)localObject1).put("os_version", Feature.access$3300());
            ((JSONObject)localObject1).put("device", Feature.access$4900() + "-" + Feature.access$5000());
            Object localObject3 = new JSONObject();
            ((JSONObject)localObject3).put("kochava_device_id", Feature.access$2700());
            ((JSONObject)localObject3).put("action", "error");
            ((JSONObject)localObject3).put("data", localObject1);
            ((JSONObject)localObject3).put("kochava_app_id", Feature.mAppId);
            ((JSONObject)localObject3).put("sdk_version", "Android20160105" + Feature.versionExtension);
            ((JSONObject)localObject3).put("sdk_protocol", "4");
            Logging.Log("https log - posting to " + "http://" + Feature.hostControl + "/track/kvTracker.php");
            localObject2 = new URL("http://" + Feature.hostControl + "/track/kvTracker.php");
            if (!(localObject3 instanceof JSONObject))
            {
              localObject1 = ((JSONObject)localObject3).toString();
              Logging.Log("https failure data:" + (String)localObject1);
              localObject2 = (HttpURLConnection)HttpInstrumentation.openConnection(((URL)localObject2).openConnection());
              ((HttpURLConnection)localObject2).setRequestProperty("User-Agent", Feature.mUserAgent);
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
              localObject3 = ((BufferedReader)localObject2).readLine();
              if (localObject3 == null) {
                break;
              }
              ((StringBuffer)localObject1).append((String)localObject3);
              continue;
            }
            str = JSONObjectInstrumentation.toString((JSONObject)localObject3);
          }
          catch (Exception localException)
          {
            Logging.LogError("httpsFail " + localException);
            return;
          }
        }
        String str = str.toString();
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
    //   1: ifnull +2399 -> 2400
    //   4: aload_0
    //   5: aload_1
    //   6: putfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   9: iload_2
    //   10: putstatic 346	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   13: ldc -86
    //   15: new 641	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   22: ldc_w 1278
    //   25: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: getstatic 326	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   31: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 1284	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: getstatic 670	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   44: ifnonnull +10 -> 54
    //   47: aload_1
    //   48: invokevirtual 1288	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   51: putstatic 670	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   54: aload_0
    //   55: new 774	java/util/Timer
    //   58: dup
    //   59: invokespecial 1289	java/util/Timer:<init>	()V
    //   62: putfield 801	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   65: aload_0
    //   66: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   69: ldc -58
    //   71: iconst_0
    //   72: invokevirtual 676	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   75: putstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   78: new 1001	com/kochava/android/tracker/DbAdapter
    //   81: dup
    //   82: aload_0
    //   83: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   86: invokespecial 1290	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   89: putstatic 569	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   92: aload_3
    //   93: ifnull +231 -> 324
    //   96: aload_3
    //   97: ldc_w 1292
    //   100: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   103: ifnull +44 -> 147
    //   106: aload_3
    //   107: ldc_w 1292
    //   110: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   113: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   116: ldc_w 834
    //   119: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   122: ifeq +25 -> 147
    //   125: aload_3
    //   126: ldc_w 1292
    //   129: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   132: checkcast 834	java/lang/Boolean
    //   135: invokevirtual 837	java/lang/Boolean:booleanValue	()Z
    //   138: istore_2
    //   139: iload_2
    //   140: invokestatic 1298	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   143: iload_2
    //   144: invokestatic 1301	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   147: aload_3
    //   148: ldc_w 1303
    //   151: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   154: ifnull +35 -> 189
    //   157: aload_3
    //   158: ldc_w 1303
    //   161: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   164: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   167: ldc_w 446
    //   170: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   173: ifeq +16 -> 189
    //   176: aload_3
    //   177: ldc_w 1303
    //   180: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: checkcast 446	java/lang/String
    //   186: putstatic 326	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   189: aload_3
    //   190: ldc_w 1305
    //   193: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   196: ifnull +38 -> 234
    //   199: aload_3
    //   200: ldc_w 1305
    //   203: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   206: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   209: ldc_w 834
    //   212: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   215: ifeq +19 -> 234
    //   218: aload_3
    //   219: ldc_w 1305
    //   222: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   225: checkcast 834	java/lang/Boolean
    //   228: invokevirtual 837	java/lang/Boolean:booleanValue	()Z
    //   231: putstatic 328	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   234: aload_3
    //   235: ldc_w 1307
    //   238: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   241: ifnull +38 -> 279
    //   244: aload_3
    //   245: ldc_w 1307
    //   248: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   251: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   254: ldc_w 834
    //   257: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   260: ifeq +19 -> 279
    //   263: aload_3
    //   264: ldc_w 1307
    //   267: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   270: checkcast 834	java/lang/Boolean
    //   273: invokevirtual 837	java/lang/Boolean:booleanValue	()Z
    //   276: putstatic 344	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   279: aload_3
    //   280: ldc_w 1309
    //   283: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   286: ifnull +38 -> 324
    //   289: aload_3
    //   290: ldc_w 1309
    //   293: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   296: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   299: ldc_w 834
    //   302: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   305: ifeq +19 -> 324
    //   308: aload_3
    //   309: ldc_w 1309
    //   312: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   315: checkcast 834	java/lang/Boolean
    //   318: invokevirtual 837	java/lang/Boolean:booleanValue	()Z
    //   321: putstatic 363	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   324: aload_0
    //   325: invokespecial 1311	com/kochava/android/tracker/Feature:initHandler	()V
    //   328: new 641	java/lang/StringBuilder
    //   331: dup
    //   332: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   335: ldc_w 1313
    //   338: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   344: ldc 109
    //   346: ldc_w 322
    //   349: invokeinterface 682 3 0
    //   354: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   363: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   366: ldc 109
    //   368: ldc_w 322
    //   371: invokeinterface 682 3 0
    //   376: ldc_w 322
    //   379: invokevirtual 686	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   382: ifne +22 -> 404
    //   385: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   388: ldc 109
    //   390: ldc_w 322
    //   393: invokeinterface 682 3 0
    //   398: invokestatic 1317	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   401: putstatic 409	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   404: getstatic 763	android/os/Build$VERSION:SDK_INT	I
    //   407: bipush 14
    //   409: if_icmplt +2032 -> 2441
    //   412: getstatic 328	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   415: ifne +2026 -> 2441
    //   418: new 641	java/lang/StringBuilder
    //   421: dup
    //   422: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   425: ldc_w 1319
    //   428: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   431: getstatic 328	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   434: invokevirtual 755	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   437: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   440: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   443: getstatic 670	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   446: checkcast 1321	android/app/Application
    //   449: new 53	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   452: dup
    //   453: aload_0
    //   454: invokespecial 1322	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   457: invokevirtual 1326	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   460: getstatic 670	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   463: new 56	com/kochava/android/tracker/Feature$MemoryBoss
    //   466: dup
    //   467: aload_0
    //   468: invokespecial 1327	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   471: invokevirtual 1331	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   474: iconst_1
    //   475: putstatic 1334	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   478: iconst_1
    //   479: putstatic 1337	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   482: aload_0
    //   483: aload_0
    //   484: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   487: invokevirtual 942	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   490: invokestatic 946	com/kochava/android/tracker/Feature:getAttributionId	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   493: putfield 948	com/kochava/android/tracker/Feature:mFbId	Ljava/lang/String;
    //   496: getstatic 344	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   499: ifne +32 -> 531
    //   502: new 26	com/kochava/android/tracker/Feature$2
    //   505: dup
    //   506: aload_0
    //   507: invokespecial 1338	com/kochava/android/tracker/Feature$2:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   510: astore_1
    //   511: iconst_0
    //   512: anewarray 1340	java/lang/Void
    //   515: astore 5
    //   517: aload_1
    //   518: instanceof 1342
    //   521: ifne +1948 -> 2469
    //   524: aload_1
    //   525: aload 5
    //   527: invokevirtual 1346	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   530: pop
    //   531: aload_0
    //   532: aload_0
    //   533: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   536: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   539: aload_0
    //   540: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   543: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   546: iconst_0
    //   547: invokevirtual 1359	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   550: getfield 1364	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   553: putfield 1077	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   556: aload_0
    //   557: new 661	org/json/JSONObject
    //   560: dup
    //   561: invokespecial 811	org/json/JSONObject:<init>	()V
    //   564: putfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   567: aload_0
    //   568: new 661	org/json/JSONObject
    //   571: dup
    //   572: invokespecial 811	org/json/JSONObject:<init>	()V
    //   575: putfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   578: aload_0
    //   579: new 661	org/json/JSONObject
    //   582: dup
    //   583: invokespecial 811	org/json/JSONObject:<init>	()V
    //   586: putfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   589: aconst_null
    //   590: astore 13
    //   592: aconst_null
    //   593: astore 6
    //   595: aconst_null
    //   596: astore 9
    //   598: aconst_null
    //   599: astore 7
    //   601: ldc_w 824
    //   604: astore_1
    //   605: aconst_null
    //   606: astore 10
    //   608: aconst_null
    //   609: astore 8
    //   611: aconst_null
    //   612: astore 11
    //   614: aconst_null
    //   615: astore 14
    //   617: aload_1
    //   618: astore 12
    //   620: aload_3
    //   621: ifnull +627 -> 1248
    //   624: aload 6
    //   626: astore 5
    //   628: aload_3
    //   629: ldc_w 1372
    //   632: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   635: ifnull +38 -> 673
    //   638: aload 6
    //   640: astore 5
    //   642: aload_3
    //   643: ldc_w 1372
    //   646: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   649: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   652: ldc_w 446
    //   655: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   658: ifeq +15 -> 673
    //   661: aload_3
    //   662: ldc_w 1372
    //   665: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   668: checkcast 446	java/lang/String
    //   671: astore 5
    //   673: aload 7
    //   675: astore 6
    //   677: aload_3
    //   678: ldc -116
    //   680: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   683: ifnull +36 -> 719
    //   686: aload 7
    //   688: astore 6
    //   690: aload_3
    //   691: ldc -116
    //   693: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   696: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   699: ldc_w 446
    //   702: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   705: ifeq +14 -> 719
    //   708: aload_3
    //   709: ldc -116
    //   711: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   714: checkcast 446	java/lang/String
    //   717: astore 6
    //   719: aload_1
    //   720: astore 7
    //   722: aload_3
    //   723: ldc -73
    //   725: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   728: ifnull +35 -> 763
    //   731: aload_1
    //   732: astore 7
    //   734: aload_3
    //   735: ldc -73
    //   737: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   740: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   743: ldc_w 446
    //   746: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   749: ifeq +14 -> 763
    //   752: aload_3
    //   753: ldc -73
    //   755: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   758: checkcast 446	java/lang/String
    //   761: astore 7
    //   763: aload 8
    //   765: astore_1
    //   766: aload_3
    //   767: ldc_w 1374
    //   770: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   773: ifnull +33 -> 806
    //   776: aload_3
    //   777: ldc_w 1374
    //   780: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   783: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   786: ldc_w 446
    //   789: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   792: ifeq +1690 -> 2482
    //   795: aload_3
    //   796: ldc_w 1374
    //   799: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   802: checkcast 446	java/lang/String
    //   805: astore_1
    //   806: aload_3
    //   807: ldc_w 1376
    //   810: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   813: ifnull +34 -> 847
    //   816: aload_3
    //   817: ldc_w 1376
    //   820: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   823: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   826: ldc_w 446
    //   829: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   832: ifeq +15 -> 847
    //   835: aload_3
    //   836: ldc_w 1376
    //   839: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   842: checkcast 446	java/lang/String
    //   845: astore 8
    //   847: aload 14
    //   849: astore 8
    //   851: aload_3
    //   852: ldc_w 1378
    //   855: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   858: ifnull +38 -> 896
    //   861: aload 14
    //   863: astore 8
    //   865: aload_3
    //   866: ldc_w 1378
    //   869: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   872: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   875: ldc_w 446
    //   878: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   881: ifeq +15 -> 896
    //   884: aload_3
    //   885: ldc_w 1378
    //   888: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   891: checkcast 446	java/lang/String
    //   894: astore 8
    //   896: aload_3
    //   897: ldc_w 912
    //   900: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   903: ifnull +39 -> 942
    //   906: aload_3
    //   907: ldc_w 912
    //   910: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   913: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   916: ldc_w 834
    //   919: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   922: ifeq +20 -> 942
    //   925: aload_0
    //   926: aload_3
    //   927: ldc_w 912
    //   930: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   933: checkcast 834	java/lang/Boolean
    //   936: invokevirtual 837	java/lang/Boolean:booleanValue	()Z
    //   939: putfield 420	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   942: aload_3
    //   943: ldc_w 930
    //   946: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   949: ifnull +115 -> 1064
    //   952: aload_3
    //   953: ldc_w 930
    //   956: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   959: instanceof 443
    //   962: ifeq +102 -> 1064
    //   965: aload_3
    //   966: ldc_w 930
    //   969: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   972: checkcast 443	java/util/HashMap
    //   975: putstatic 1380	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   978: new 661	org/json/JSONObject
    //   981: dup
    //   982: invokespecial 811	org/json/JSONObject:<init>	()V
    //   985: putstatic 928	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   988: getstatic 1380	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   991: invokeinterface 984 1 0
    //   996: invokeinterface 987 1 0
    //   1001: astore 9
    //   1003: aload 9
    //   1005: invokeinterface 711 1 0
    //   1010: ifeq +54 -> 1064
    //   1013: aload 9
    //   1015: invokeinterface 715 1 0
    //   1020: checkcast 989	java/util/Map$Entry
    //   1023: astore 10
    //   1025: getstatic 928	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   1028: aload 10
    //   1030: invokeinterface 992 1 0
    //   1035: checkcast 446	java/lang/String
    //   1038: aload 10
    //   1040: invokeinterface 995 1 0
    //   1045: checkcast 446	java/lang/String
    //   1048: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1051: pop
    //   1052: aload 9
    //   1054: invokeinterface 1383 1 0
    //   1059: goto -56 -> 1003
    //   1062: astore 9
    //   1064: aload_3
    //   1065: ldc_w 936
    //   1068: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1071: ifnull +36 -> 1107
    //   1074: aload_3
    //   1075: ldc_w 936
    //   1078: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1081: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1084: ldc_w 446
    //   1087: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1090: ifeq +17 -> 1107
    //   1093: aload_0
    //   1094: aload_3
    //   1095: ldc_w 936
    //   1098: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1101: checkcast 446	java/lang/String
    //   1104: putfield 932	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
    //   1107: aload 6
    //   1109: astore 9
    //   1111: aload_1
    //   1112: astore 10
    //   1114: aload 8
    //   1116: astore 11
    //   1118: aload 7
    //   1120: astore 12
    //   1122: aload 5
    //   1124: astore 13
    //   1126: aload_3
    //   1127: ldc_w 1384
    //   1130: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1133: ifnull +115 -> 1248
    //   1136: aload 6
    //   1138: astore 9
    //   1140: aload_1
    //   1141: astore 10
    //   1143: aload 8
    //   1145: astore 11
    //   1147: aload 7
    //   1149: astore 12
    //   1151: aload 5
    //   1153: astore 13
    //   1155: aload_3
    //   1156: ldc_w 1384
    //   1159: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1162: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1165: ldc_w 1386
    //   1168: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1171: ifeq +77 -> 1248
    //   1174: aload_3
    //   1175: ldc_w 1384
    //   1178: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1181: checkcast 1386	java/lang/Integer
    //   1184: invokevirtual 1389	java/lang/Integer:intValue	()I
    //   1187: istore 4
    //   1189: iload 4
    //   1191: iconst_1
    //   1192: if_icmpge +1337 -> 2529
    //   1195: new 641	java/lang/StringBuilder
    //   1198: dup
    //   1199: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1202: ldc_w 1391
    //   1205: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1208: iload 4
    //   1210: invokevirtual 1041	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1213: ldc_w 1393
    //   1216: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1219: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1222: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1225: iconst_1
    //   1226: putstatic 332	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
    //   1229: aload 5
    //   1231: astore 13
    //   1233: aload 7
    //   1235: astore 12
    //   1237: aload 8
    //   1239: astore 11
    //   1241: aload_1
    //   1242: astore 10
    //   1244: aload 6
    //   1246: astore 9
    //   1248: aload 11
    //   1250: ifnull +19 -> 1269
    //   1253: aload 11
    //   1255: invokevirtual 450	java/lang/String:trim	()Ljava/lang/String;
    //   1258: invokevirtual 454	java/lang/String:length	()I
    //   1261: ifeq +8 -> 1269
    //   1264: aload 11
    //   1266: putstatic 324	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1269: aload 10
    //   1271: ifnull +17 -> 1288
    //   1274: aload 10
    //   1276: ldc -34
    //   1278: invokevirtual 1396	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1281: ifeq +7 -> 1288
    //   1284: iconst_1
    //   1285: putstatic 377	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1288: aload_0
    //   1289: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1292: ldc 95
    //   1294: iconst_0
    //   1295: invokevirtual 676	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1298: putstatic 535	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1301: aload 9
    //   1303: ifnull +1318 -> 2621
    //   1306: aload 9
    //   1308: invokevirtual 450	java/lang/String:trim	()Ljava/lang/String;
    //   1311: invokevirtual 454	java/lang/String:length	()I
    //   1314: ifeq +1307 -> 2621
    //   1317: aload 9
    //   1319: putstatic 623	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1322: aload_0
    //   1323: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1326: ldc -116
    //   1328: aload 9
    //   1330: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1333: pop
    //   1334: aload_0
    //   1335: getfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1338: ldc -116
    //   1340: aload 9
    //   1342: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1345: pop
    //   1346: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1349: ldc -76
    //   1351: ldc_w 322
    //   1354: invokeinterface 682 3 0
    //   1359: ldc_w 322
    //   1362: invokevirtual 686	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1365: ifeq +26 -> 1391
    //   1368: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1371: invokeinterface 1176 1 0
    //   1376: ldc -76
    //   1378: aload 9
    //   1380: invokeinterface 1182 3 0
    //   1385: invokeinterface 1185 1 0
    //   1390: pop
    //   1391: aload_0
    //   1392: aload 12
    //   1394: invokespecial 631	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1397: aload_0
    //   1398: getfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1401: ldc_w 1398
    //   1404: aload_0
    //   1405: invokespecial 901	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1408: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1411: pop
    //   1412: aload_0
    //   1413: getfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1416: ldc_w 1400
    //   1419: ldc_w 1402
    //   1422: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1425: pop
    //   1426: aload_0
    //   1427: getfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1430: ldc_w 1404
    //   1433: ldc_w 1406
    //   1436: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1439: pop
    //   1440: aload_0
    //   1441: getfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1444: ldc -73
    //   1446: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1449: ldc -73
    //   1451: ldc_w 824
    //   1454: invokeinterface 682 3 0
    //   1459: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1462: pop
    //   1463: aload_0
    //   1464: getfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1467: ldc -73
    //   1469: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1472: ldc -73
    //   1474: ldc_w 824
    //   1477: invokeinterface 682 3 0
    //   1482: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1485: pop
    //   1486: aload_0
    //   1487: getfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1490: ldc_w 1404
    //   1493: ldc_w 1406
    //   1496: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1499: pop
    //   1500: aload_0
    //   1501: getfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1504: ldc -73
    //   1506: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1509: ldc -73
    //   1511: ldc_w 824
    //   1514: invokeinterface 682 3 0
    //   1519: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1522: pop
    //   1523: aload_0
    //   1524: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1527: ldc_w 867
    //   1530: new 641	java/lang/StringBuilder
    //   1533: dup
    //   1534: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1537: ldc_w 869
    //   1540: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1543: getstatic 326	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1546: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1549: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1552: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1555: pop
    //   1556: aload_0
    //   1557: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1560: ldc_w 1408
    //   1563: ldc_w 1410
    //   1566: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1569: pop
    //   1570: aload_0
    //   1571: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1574: ldc_w 997
    //   1577: aload_0
    //   1578: getfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1581: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1584: pop
    //   1585: aload_0
    //   1586: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1589: ldc_w 1412
    //   1592: aload_0
    //   1593: getfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1596: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1599: pop
    //   1600: invokestatic 647	java/lang/System:currentTimeMillis	()J
    //   1603: ldc2_w 648
    //   1606: ldiv
    //   1607: putstatic 352	com/kochava/android/tracker/Feature:startTime	J
    //   1610: iconst_0
    //   1611: istore 4
    //   1613: ldc_w 322
    //   1616: astore_3
    //   1617: new 722	android/content/ComponentName
    //   1620: dup
    //   1621: aload_0
    //   1622: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1625: ldc_w 1414
    //   1628: invokespecial 734	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1631: astore_1
    //   1632: aload_0
    //   1633: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1636: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1639: aload_1
    //   1640: iconst_0
    //   1641: invokevirtual 1418	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1644: pop
    //   1645: ldc_w 1420
    //   1648: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1651: aload_3
    //   1652: astore_1
    //   1653: aload_0
    //   1654: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1657: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1660: ldc_w 1422
    //   1663: aload_0
    //   1664: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1667: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1670: invokevirtual 1425	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1673: ifge +27 -> 1700
    //   1676: iconst_1
    //   1677: istore 4
    //   1679: new 641	java/lang/StringBuilder
    //   1682: dup
    //   1683: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1686: aload_3
    //   1687: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1690: ldc_w 1427
    //   1693: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1696: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1699: astore_1
    //   1700: aload_1
    //   1701: astore_3
    //   1702: aload_0
    //   1703: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1706: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1709: ldc_w 1429
    //   1712: aload_0
    //   1713: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1716: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1719: invokevirtual 1425	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1722: ifge +27 -> 1749
    //   1725: iconst_1
    //   1726: istore 4
    //   1728: new 641	java/lang/StringBuilder
    //   1731: dup
    //   1732: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1735: aload_1
    //   1736: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1739: ldc_w 1431
    //   1742: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1745: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1748: astore_3
    //   1749: aload_3
    //   1750: astore_1
    //   1751: aload_0
    //   1752: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1755: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1758: ldc_w 1433
    //   1761: aload_0
    //   1762: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1765: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1768: invokevirtual 1425	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1771: ifge +27 -> 1798
    //   1774: iconst_1
    //   1775: istore 4
    //   1777: new 641	java/lang/StringBuilder
    //   1780: dup
    //   1781: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1784: aload_3
    //   1785: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1788: ldc_w 1435
    //   1791: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1794: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1797: astore_1
    //   1798: iload 4
    //   1800: ifeq +13 -> 1813
    //   1803: ldc_w 1437
    //   1806: invokestatic 1440	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1809: aload_1
    //   1810: invokestatic 1440	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1813: aload_0
    //   1814: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1817: ldc_w 1442
    //   1820: invokevirtual 954	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1823: checkcast 1444	android/telephony/TelephonyManager
    //   1826: invokevirtual 1447	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1829: putstatic 881	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1832: aload_0
    //   1833: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1836: ldc_w 1449
    //   1839: invokevirtual 954	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1842: checkcast 1451	android/net/wifi/WifiManager
    //   1845: invokevirtual 1455	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1848: invokevirtual 1460	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1851: putstatic 877	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1854: aload_0
    //   1855: invokespecial 1462	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1858: putstatic 616	com/kochava/android/tracker/Feature:mUserAgent	Ljava/lang/String;
    //   1861: aload_0
    //   1862: invokestatic 620	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1865: putfield 1464	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1868: aload_0
    //   1869: invokestatic 613	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1872: putfield 1466	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1875: aload_0
    //   1876: ldc_w 1468
    //   1879: putfield 1081	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1882: aload_0
    //   1883: ldc_w 1470
    //   1886: putfield 1085	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1889: aload_0
    //   1890: ldc_w 322
    //   1893: putfield 546	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1896: aload_0
    //   1897: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1900: invokevirtual 1288	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1903: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1906: astore_3
    //   1907: aload_3
    //   1908: aload_0
    //   1909: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1912: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1915: iconst_0
    //   1916: invokevirtual 1474	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1919: astore_1
    //   1920: aload_1
    //   1921: ifnull +1044 -> 2965
    //   1924: aload_3
    //   1925: aload_1
    //   1926: invokevirtual 1478	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1929: astore_1
    //   1930: aload_0
    //   1931: aload_1
    //   1932: checkcast 446	java/lang/String
    //   1935: checkcast 446	java/lang/String
    //   1938: putfield 1081	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1941: new 641	java/lang/StringBuilder
    //   1944: dup
    //   1945: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1948: ldc_w 1480
    //   1951: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1954: aload_0
    //   1955: getfield 1081	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1958: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1961: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1964: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1967: aload_0
    //   1968: new 641	java/lang/StringBuilder
    //   1971: dup
    //   1972: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   1975: aload_0
    //   1976: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1979: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1982: aload_0
    //   1983: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1986: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1989: iconst_0
    //   1990: invokevirtual 1359	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1993: getfield 1483	android/content/pm/PackageInfo:versionCode	I
    //   1996: invokevirtual 1041	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1999: ldc_w 322
    //   2002: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2005: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2008: putfield 1085	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2011: new 641	java/lang/StringBuilder
    //   2014: dup
    //   2015: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2018: ldc_w 1485
    //   2021: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2024: aload_0
    //   2025: getfield 1085	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2028: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2031: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2034: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2037: aload_0
    //   2038: aload_0
    //   2039: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2042: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2045: aload_0
    //   2046: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2049: invokevirtual 1353	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2052: iconst_0
    //   2053: invokevirtual 1359	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2056: getfield 1488	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2059: putfield 546	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2062: new 641	java/lang/StringBuilder
    //   2065: dup
    //   2066: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2069: ldc_w 1490
    //   2072: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2075: aload_0
    //   2076: getfield 546	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2079: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2082: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2085: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2088: aload_0
    //   2089: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2092: ldc_w 950
    //   2095: invokevirtual 954	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2098: checkcast 956	android/view/WindowManager
    //   2101: astore_1
    //   2102: aload_0
    //   2103: aload_1
    //   2104: invokeinterface 963 1 0
    //   2109: invokevirtual 1493	android/view/Display:getHeight	()I
    //   2112: putfield 892	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2115: aload_0
    //   2116: aload_1
    //   2117: invokeinterface 963 1 0
    //   2122: invokevirtual 1496	android/view/Display:getWidth	()I
    //   2125: putfield 896	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2128: new 641	java/lang/StringBuilder
    //   2131: dup
    //   2132: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2135: ldc_w 1498
    //   2138: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2141: aload_0
    //   2142: getfield 892	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2145: invokevirtual 1041	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2148: ldc_w 1500
    //   2151: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2154: aload_0
    //   2155: getfield 896	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2158: invokevirtual 1041	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2161: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2164: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2167: aload_0
    //   2168: aload_0
    //   2169: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2172: invokevirtual 942	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   2175: ldc_w 907
    //   2178: invokestatic 1505	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   2181: putfield 909	com/kochava/android/tracker/Feature:mAndroidID	Ljava/lang/String;
    //   2184: aload_0
    //   2185: invokestatic 532	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2188: putfield 1507	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2191: aload_0
    //   2192: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2195: ldc_w 813
    //   2198: invokestatic 532	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2201: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2204: pop
    //   2205: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2208: ldc -70
    //   2210: ldc_w 322
    //   2213: invokeinterface 682 3 0
    //   2218: ldc -34
    //   2220: invokevirtual 686	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2223: ifne +150 -> 2373
    //   2226: aload_0
    //   2227: getfield 635	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2230: invokevirtual 1350	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2233: astore 5
    //   2235: aload 5
    //   2237: sipush 128
    //   2240: invokevirtual 1511	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2243: invokeinterface 842 1 0
    //   2248: astore 6
    //   2250: aload 6
    //   2252: invokeinterface 711 1 0
    //   2257: ifeq +116 -> 2373
    //   2260: aload 6
    //   2262: invokeinterface 715 1 0
    //   2267: checkcast 1513	android/content/pm/ApplicationInfo
    //   2270: astore 7
    //   2272: aload 7
    //   2274: invokestatic 1517	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2277: istore_2
    //   2278: iload_2
    //   2279: ifne -29 -> 2250
    //   2282: aconst_null
    //   2283: astore_1
    //   2284: aload 5
    //   2286: aload 7
    //   2288: getfield 1518	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2291: iconst_0
    //   2292: invokevirtual 1359	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2295: astore_3
    //   2296: aload_3
    //   2297: astore_1
    //   2298: aload_1
    //   2299: ifnull -49 -> 2250
    //   2302: getstatic 361	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2305: new 47	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2308: dup
    //   2309: aload_0
    //   2310: aload_1
    //   2311: getfield 1364	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2314: new 641	java/lang/StringBuilder
    //   2317: dup
    //   2318: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2321: aload_1
    //   2322: getfield 1521	android/content/pm/PackageInfo:firstInstallTime	J
    //   2325: invokevirtual 653	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2328: ldc_w 322
    //   2331: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2334: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2337: new 641	java/lang/StringBuilder
    //   2340: dup
    //   2341: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2344: aload_1
    //   2345: getfield 1524	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2348: invokevirtual 653	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2351: ldc_w 322
    //   2354: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2357: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2360: invokespecial 1527	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2363: invokeinterface 1530 2 0
    //   2368: pop
    //   2369: goto -119 -> 2250
    //   2372: astore_1
    //   2373: getstatic 391	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2376: aload_0
    //   2377: getfield 427	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2380: ldc2_w 1531
    //   2383: getstatic 1538	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2386: invokeinterface 1543 5 0
    //   2391: pop
    //   2392: aload_0
    //   2393: ldc_w 1544
    //   2396: invokevirtual 1547	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2399: return
    //   2400: ldc_w 1549
    //   2403: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2406: iconst_1
    //   2407: putstatic 371	com/kochava/android/tracker/Feature:badInit	Z
    //   2410: return
    //   2411: astore_1
    //   2412: new 641	java/lang/StringBuilder
    //   2415: dup
    //   2416: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2419: ldc_w 1551
    //   2422: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2425: aload_1
    //   2426: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   2429: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2432: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2435: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2438: goto -2034 -> 404
    //   2441: new 641	java/lang/StringBuilder
    //   2444: dup
    //   2445: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2448: ldc_w 1553
    //   2451: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2454: getstatic 328	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2457: invokevirtual 755	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2460: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2463: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2466: goto -1984 -> 482
    //   2469: aload_1
    //   2470: checkcast 1342	android/os/AsyncTask
    //   2473: aload 5
    //   2475: invokestatic 1558	com/newrelic/agent/android/instrumentation/AsyncTaskInstrumentation:execute	(Landroid/os/AsyncTask;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   2478: pop
    //   2479: goto -1948 -> 531
    //   2482: aload 8
    //   2484: astore_1
    //   2485: aload_3
    //   2486: ldc_w 1374
    //   2489: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2492: invokevirtual 1296	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2495: ldc_w 834
    //   2498: invokevirtual 1014	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2501: ifeq -1695 -> 806
    //   2504: aload 8
    //   2506: astore_1
    //   2507: aload_3
    //   2508: ldc_w 1374
    //   2511: invokevirtual 832	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2514: checkcast 834	java/lang/Boolean
    //   2517: invokevirtual 837	java/lang/Boolean:booleanValue	()Z
    //   2520: ifeq -1714 -> 806
    //   2523: ldc -34
    //   2525: astore_1
    //   2526: goto -1720 -> 806
    //   2529: iload 4
    //   2531: sipush 360
    //   2534: if_icmple +42 -> 2576
    //   2537: new 641	java/lang/StringBuilder
    //   2540: dup
    //   2541: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2544: ldc_w 1391
    //   2547: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2550: iload 4
    //   2552: invokevirtual 1041	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2555: ldc_w 1560
    //   2558: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2561: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2564: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2567: ldc_w 1561
    //   2570: putstatic 330	com/kochava/android/tracker/Feature:flush_rate	I
    //   2573: goto -1348 -> 1225
    //   2576: iload 4
    //   2578: bipush 60
    //   2580: imul
    //   2581: sipush 1000
    //   2584: imul
    //   2585: putstatic 330	com/kochava/android/tracker/Feature:flush_rate	I
    //   2588: new 641	java/lang/StringBuilder
    //   2591: dup
    //   2592: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2595: ldc_w 1563
    //   2598: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2601: iload 4
    //   2603: invokevirtual 1041	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2606: ldc_w 1565
    //   2609: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2612: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2615: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2618: goto -1393 -> 1225
    //   2621: aload 13
    //   2623: ifnull +177 -> 2800
    //   2626: aload 13
    //   2628: invokevirtual 450	java/lang/String:trim	()Ljava/lang/String;
    //   2631: invokevirtual 454	java/lang/String:length	()I
    //   2634: ifeq +166 -> 2800
    //   2637: aload_0
    //   2638: getfield 1366	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2641: ldc_w 1372
    //   2644: aload 13
    //   2646: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2649: pop
    //   2650: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2653: ldc -76
    //   2655: ldc_w 322
    //   2658: invokeinterface 682 3 0
    //   2663: ldc_w 322
    //   2666: invokevirtual 686	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2669: ifeq +26 -> 2695
    //   2672: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2675: invokeinterface 1176 1 0
    //   2680: ldc -76
    //   2682: aload 13
    //   2684: invokeinterface 1182 3 0
    //   2689: invokeinterface 1185 1 0
    //   2694: pop
    //   2695: aload 9
    //   2697: ifnull +76 -> 2773
    //   2700: aload 9
    //   2702: invokevirtual 450	java/lang/String:trim	()Ljava/lang/String;
    //   2705: invokevirtual 454	java/lang/String:length	()I
    //   2708: ifeq +65 -> 2773
    //   2711: aload 9
    //   2713: putstatic 623	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2716: aload_0
    //   2717: getfield 1368	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2720: ldc -116
    //   2722: aload 9
    //   2724: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2727: pop
    //   2728: aload_0
    //   2729: getfield 1370	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2732: ldc -116
    //   2734: aload 9
    //   2736: invokevirtual 664	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2739: pop
    //   2740: goto -1349 -> 1391
    //   2743: astore_1
    //   2744: new 641	java/lang/StringBuilder
    //   2747: dup
    //   2748: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2751: ldc_w 1567
    //   2754: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2757: aload_1
    //   2758: invokevirtual 1568	org/json/JSONException:toString	()Ljava/lang/String;
    //   2761: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2764: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2767: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2770: goto -1170 -> 1600
    //   2773: new 641	java/lang/StringBuilder
    //   2776: dup
    //   2777: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2780: ldc_w 1570
    //   2783: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2786: aload 13
    //   2788: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2791: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2794: putstatic 623	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2797: goto -1406 -> 1391
    //   2800: ldc_w 1572
    //   2803: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2806: iconst_1
    //   2807: putstatic 371	com/kochava/android/tracker/Feature:badInit	Z
    //   2810: return
    //   2811: astore_1
    //   2812: iconst_1
    //   2813: istore 4
    //   2815: new 641	java/lang/StringBuilder
    //   2818: dup
    //   2819: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2822: ldc_w 322
    //   2825: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2828: ldc_w 1574
    //   2831: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2834: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2837: astore_3
    //   2838: goto -1187 -> 1651
    //   2841: astore_1
    //   2842: new 641	java/lang/StringBuilder
    //   2845: dup
    //   2846: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2849: ldc_w 1576
    //   2852: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2855: aload_1
    //   2856: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   2859: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2862: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2865: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2868: goto -1036 -> 1832
    //   2871: astore_1
    //   2872: new 641	java/lang/StringBuilder
    //   2875: dup
    //   2876: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2879: ldc_w 1578
    //   2882: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2885: aload_1
    //   2886: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   2889: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2892: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2895: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2898: goto -1044 -> 1854
    //   2901: astore 5
    //   2903: aconst_null
    //   2904: astore_1
    //   2905: new 641	java/lang/StringBuilder
    //   2908: dup
    //   2909: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2912: ldc_w 1580
    //   2915: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2918: aload 5
    //   2920: invokevirtual 1581	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2923: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2926: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2929: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2932: goto -1012 -> 1920
    //   2935: astore_1
    //   2936: new 641	java/lang/StringBuilder
    //   2939: dup
    //   2940: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2943: ldc_w 1580
    //   2946: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2949: aload_1
    //   2950: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   2953: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2956: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2959: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2962: goto -995 -> 1967
    //   2965: ldc_w 1583
    //   2968: astore_1
    //   2969: goto -1039 -> 1930
    //   2972: astore_1
    //   2973: new 641	java/lang/StringBuilder
    //   2976: dup
    //   2977: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   2980: ldc_w 1585
    //   2983: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2986: aload_1
    //   2987: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   2990: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2993: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2996: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2999: goto -962 -> 2037
    //   3002: astore_1
    //   3003: new 641	java/lang/StringBuilder
    //   3006: dup
    //   3007: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   3010: ldc_w 1587
    //   3013: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3016: aload_1
    //   3017: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   3020: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3023: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3026: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3029: goto -941 -> 2088
    //   3032: astore_1
    //   3033: new 641	java/lang/StringBuilder
    //   3036: dup
    //   3037: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   3040: ldc_w 1589
    //   3043: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3046: aload_1
    //   3047: invokevirtual 1067	java/lang/Exception:toString	()Ljava/lang/String;
    //   3050: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3053: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3056: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3059: goto -892 -> 2167
    //   3062: astore_1
    //   3063: getstatic 860	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3066: ifeq -861 -> 2205
    //   3069: aload_1
    //   3070: invokevirtual 863	org/json/JSONException:printStackTrace	()V
    //   3073: goto -868 -> 2205
    //   3076: astore_3
    //   3077: new 641	java/lang/StringBuilder
    //   3080: dup
    //   3081: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   3084: ldc_w 1591
    //   3087: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3090: aload 7
    //   3092: getfield 1518	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3095: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3098: ldc_w 1593
    //   3101: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3104: aload_3
    //   3105: invokevirtual 1581	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3108: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3111: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3114: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3117: goto -819 -> 2298
    //   3120: astore_1
    //   3121: goto -2565 -> 556
    //   3124: astore_1
    //   3125: goto -2629 -> 496
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3128	0	this	Feature
    //   0	3128	1	paramContext	Context
    //   0	3128	2	paramBoolean	boolean
    //   0	3128	3	paramHashMap	HashMap<String, Object>
    //   1187	1627	4	i	int
    //   515	1959	5	localObject1	Object
    //   2901	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   593	1668	6	localObject2	Object
    //   599	2492	7	localObject3	Object
    //   609	1896	8	localObject4	Object
    //   596	457	9	localIterator	Iterator
    //   1062	1	9	localException	Exception
    //   1109	1626	9	localObject5	Object
    //   606	669	10	localObject6	Object
    //   612	653	11	localObject7	Object
    //   618	775	12	localObject8	Object
    //   590	2197	13	localObject9	Object
    //   615	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   965	1003	1062	java/lang/Exception
    //   1003	1059	1062	java/lang/Exception
    //   2205	2250	2372	java/lang/Exception
    //   2250	2278	2372	java/lang/Exception
    //   2284	2296	2372	java/lang/Exception
    //   2302	2369	2372	java/lang/Exception
    //   3077	3117	2372	java/lang/Exception
    //   385	404	2411	java/lang/Exception
    //   1306	1391	2743	org/json/JSONException
    //   1391	1600	2743	org/json/JSONException
    //   2626	2695	2743	org/json/JSONException
    //   2700	2740	2743	org/json/JSONException
    //   2773	2797	2743	org/json/JSONException
    //   2800	2810	2743	org/json/JSONException
    //   1632	1651	2811	android/content/pm/PackageManager$NameNotFoundException
    //   1813	1832	2841	java/lang/Exception
    //   1832	1854	2871	java/lang/Exception
    //   1907	1920	2901	android/content/pm/PackageManager$NameNotFoundException
    //   1896	1907	2935	java/lang/Exception
    //   1907	1920	2935	java/lang/Exception
    //   1924	1930	2935	java/lang/Exception
    //   1930	1967	2935	java/lang/Exception
    //   2905	2932	2935	java/lang/Exception
    //   1967	2037	2972	java/lang/Exception
    //   2037	2088	3002	java/lang/Exception
    //   2088	2167	3032	java/lang/Exception
    //   2191	2205	3062	org/json/JSONException
    //   2284	2296	3076	android/content/pm/PackageManager$NameNotFoundException
    //   531	556	3120	java/lang/Exception
    //   482	496	3124	java/lang/Exception
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
          Feature.access$4402(Feature.this, new Timer());
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
            break label712;
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
      label712:
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
    new Thread()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        try
        {
          localObject1 = new JSONObject();
          Object localObject2 = new JSONObject();
          Feature.addGlobalProperties((JSONObject)localObject2);
          if (this.val$pushCommand.has("register"))
          {
            ((JSONObject)localObject2).put("token", this.val$pushCommand.get("register").toString());
            ((JSONObject)localObject1).put("push_action", "register_token");
          }
          if (this.val$pushCommand.has("remove")) {
            ((JSONObject)localObject1).put("push_action", "remove_token");
          }
          ((JSONObject)localObject1).put("action", "push");
          ((JSONObject)localObject1).put("data", localObject2);
          ((JSONObject)localObject1).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject1).put("kochava_device_id", Feature.access$2700());
          ((JSONObject)localObject1).put("sdk_version", "Android20160105" + Feature.versionExtension);
          ((JSONObject)localObject1).put("sdk_protocol", "4");
          if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty()))
          {
            Logging.Log("Push token - hostControl was empty, using default");
            Feature.access$402("control.kochava.com");
          }
          Logging.Log("kvPush - posting to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject2 = new URL("https://" + Feature.hostControl + "/track/kvTracker.php");
          if ((localObject1 instanceof JSONObject)) {
            break label516;
          }
          localObject1 = ((JSONObject)localObject1).toString();
          Logging.Log("kvPush data:" + (String)localObject1);
          localObject2 = (HttpsURLConnection)HttpInstrumentation.openConnection(((URL)localObject2).openConnection());
          ((HttpsURLConnection)localObject2).setRequestProperty("User-Agent", Feature.mUserAgent);
          ((HttpsURLConnection)localObject2).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpsURLConnection)localObject2).setRequestMethod("POST");
          ((HttpsURLConnection)localObject2).setConnectTimeout(30000);
          ((HttpsURLConnection)localObject2).setReadTimeout(30000);
          ((HttpsURLConnection)localObject2).setDoInput(true);
          ((HttpsURLConnection)localObject2).setDoOutput(true);
          ((HttpsURLConnection)localObject2).connect();
          localObject4 = new OutputStreamWriter(((HttpsURLConnection)localObject2).getOutputStream());
          ((OutputStreamWriter)localObject4).write((String)localObject1);
          ((OutputStreamWriter)localObject4).close();
          Logging.Log("Grabbing Result...");
          localObject1 = new StringBuffer("");
          localObject2 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject2).getInputStream()));
          for (;;)
          {
            localObject4 = ((BufferedReader)localObject2).readLine();
            if (localObject4 == null) {
              break;
            }
            ((StringBuffer)localObject1).append((String)localObject4);
          }
          localObject1 = ((JSONObject)localObject1).toString();
        }
        catch (Exception localException)
        {
          localObject4 = new StringBuilder().append("Problem sending register/remove token, saving push command: ");
          localObject1 = this.val$pushCommand;
          if ((localObject1 instanceof JSONObject)) {
            break label711;
          }
        }
        label453:
        Logging.LogError((String)localObject1);
        Object localObject4 = Feature.prefs.edit();
        Object localObject1 = this.val$pushCommand;
        if (!(localObject1 instanceof JSONObject)) {}
        for (localObject1 = ((JSONObject)localObject1).toString();; localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1))
        {
          ((SharedPreferences.Editor)localObject4).putString("register_remove_token", (String)localObject1).commit();
          if (Global.DEBUGERROR) {
            localException.printStackTrace();
          }
          return;
          label516:
          localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1);
          break;
          localObject1 = ((StringBuffer)localObject1).toString();
          Logging.Log("Result: " + (String)localObject1);
          localObject1 = JSONObjectInstrumentation.init((String)localObject1);
          if ((localObject1 != null) && (((JSONObject)localObject1).get("success").toString().equals("1")))
          {
            Feature.prefs.edit().remove("register_remove_token").commit();
            return;
          }
          Object localObject3 = new StringBuilder().append("Did not get a good response, saving push command: ");
          localObject1 = this.val$pushCommand;
          if (!(localObject1 instanceof JSONObject))
          {
            localObject1 = ((JSONObject)localObject1).toString();
            Logging.LogError((String)localObject1);
            localObject3 = Feature.prefs.edit();
            localObject1 = this.val$pushCommand;
            if ((localObject1 instanceof JSONObject)) {
              break label700;
            }
          }
          label700:
          for (localObject1 = ((JSONObject)localObject1).toString();; localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1))
          {
            ((SharedPreferences.Editor)localObject3).putString("register_remove_token", (String)localObject1).commit();
            return;
            localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1);
            break;
          }
          label711:
          localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1);
          break label453;
        }
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
        for (;;)
        {
          try
          {
            localObject2 = new JSONObject();
            ((JSONObject)localObject2).put("action", "update");
            ((JSONObject)localObject2).put("kochava_device_id", Feature.access$2700());
            ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
            ((JSONObject)localObject2).put("sdk_version", "Android20160105" + Feature.versionExtension);
            ((JSONObject)localObject2).put("sdk_protocol", "4");
            Object localObject1 = new JSONObject();
            ((JSONObject)localObject1).put("outside_services", this.val$services_found);
            ((JSONObject)localObject2).put("data", localObject1);
            if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
              Feature.access$402("control.kochava.com");
            }
            Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
            Object localObject3 = (HttpsURLConnection)HttpInstrumentation.openConnection(new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection());
            ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", Feature.mUserAgent);
            ((HttpsURLConnection)localObject3).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            ((HttpsURLConnection)localObject3).setRequestMethod("POST");
            ((HttpsURLConnection)localObject3).setConnectTimeout(30000);
            ((HttpsURLConnection)localObject3).setReadTimeout(30000);
            ((HttpsURLConnection)localObject3).setDoInput(true);
            ((HttpsURLConnection)localObject3).setDoOutput(true);
            ((HttpsURLConnection)localObject3).connect();
            if (!(localObject2 instanceof JSONObject))
            {
              localObject1 = ((JSONObject)localObject2).toString();
              StringBuilder localStringBuilder = new StringBuilder().append("Trying to post an update: ");
              if ((localObject2 instanceof JSONObject)) {
                break label414;
              }
              localObject2 = ((JSONObject)localObject2).toString();
              Logging.Log((String)localObject2);
              localObject2 = new OutputStreamWriter(((HttpsURLConnection)localObject3).getOutputStream());
              ((OutputStreamWriter)localObject2).write((String)localObject1);
              ((OutputStreamWriter)localObject2).close();
              Logging.Log("(Update) Grabbing Result...");
              localObject1 = new StringBuffer("");
              localObject2 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject3).getInputStream()));
              localObject3 = ((BufferedReader)localObject2).readLine();
              if (localObject3 == null) {
                break;
              }
              ((StringBuffer)localObject1).append((String)localObject3);
              continue;
            }
            str = JSONObjectInstrumentation.toString((JSONObject)localObject2);
          }
          catch (Exception localException)
          {
            Logging.LogError("Update error: " + localException.toString());
            return;
          }
          continue;
          label414:
          Object localObject2 = JSONObjectInstrumentation.toString((JSONObject)localObject2);
        }
        String str = str.toString();
        Logging.Log("Update Result: " + str);
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
    //   0: getstatic 763	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +255 -> 260
    //   8: getstatic 328	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +249 -> 260
    //   14: getstatic 371	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 98
    //   22: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1831
    //   29: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 363	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 767	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +184 -> 225
    //   44: ldc_w 1833
    //   47: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 774	java/util/Timer
    //   53: dup
    //   54: invokespecial 1289	java/util/Timer:<init>	()V
    //   57: putstatic 767	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 767	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 36	com/kochava/android/tracker/Feature$7
    //   66: dup
    //   67: invokespecial 1834	com/kochava/android/tracker/Feature$7:<init>	()V
    //   70: getstatic 330	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 330	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1614	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 647	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 648
    //   87: ldiv
    //   88: putstatic 352	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 348	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 505	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 670	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1838	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1841	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +46 -> 161
    //   118: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -52
    //   123: ldc_w 322
    //   126: invokeinterface 682 3 0
    //   131: ldc_w 322
    //   134: invokevirtual 686	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +22 -> 161
    //   142: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   145: ldc -52
    //   147: ldc_w 322
    //   150: invokeinterface 682 3 0
    //   155: invokestatic 1846	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   158: invokestatic 1848	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   161: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   164: ifnull +96 -> 260
    //   167: getstatic 509	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   170: ldc -70
    //   172: ldc_w 322
    //   175: invokeinterface 682 3 0
    //   180: ldc -34
    //   182: invokevirtual 686	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   185: ifeq +75 -> 260
    //   188: getstatic 373	com/kochava/android/tracker/Feature:canSendSession	Z
    //   191: ifeq +63 -> 254
    //   194: ldc -92
    //   196: invokestatic 780	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   199: return
    //   200: astore_1
    //   201: new 641	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 642	java/lang/StringBuilder:<init>	()V
    //   208: ldc_w 1850
    //   211: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: aload_1
    //   215: invokevirtual 697	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   218: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   221: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   224: return
    //   225: ldc_w 1852
    //   228: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   231: goto -150 -> 81
    //   234: astore_1
    //   235: ldc_w 1854
    //   238: invokestatic 702	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   241: getstatic 860	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   244: ifeq -83 -> 161
    //   247: aload_1
    //   248: invokevirtual 863	org/json/JSONException:printStackTrace	()V
    //   251: goto -90 -> 161
    //   254: ldc_w 786
    //   257: invokestatic 758	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
        HashMap localHashMap = new HashMap();
        if (!Feature.prefs.contains("app_short_string"))
        {
          Logging.Log("No previous app_short_string in watchlist, adding " + Feature.this.mAppVersionName);
          Feature.prefs.edit().putString("app_short_string", Feature.this.mAppVersionName).commit();
          if (Feature.prefs.contains("app_limit_tracking")) {
            break label730;
          }
          Logging.Log("No previous app_limit_tracking in watchlist, adding " + Feature.this.app_limit_tracking);
          Feature.prefs.edit().putBoolean("app_limit_tracking", Feature.this.app_limit_tracking).commit();
          label176:
          if (Feature.prefs.contains("app_version")) {
            break label842;
          }
          Logging.Log("No previous app_version in watchlist, adding " + Feature.this.getAppVersion());
          Feature.prefs.edit().putString("app_version", Feature.this.getAppVersion()).commit();
          label245:
          if (Feature.prefs.contains("device_limit_tracking")) {
            break label958;
          }
          Logging.Log("No previous device_limit_tracking in watchlist, adding " + Feature.device_limit_tracking);
          Feature.prefs.edit().putBoolean("device_limit_tracking", Feature.device_limit_tracking).commit();
          label306:
          if (Feature.send_id_updates)
          {
            if (Feature.prefs.contains("adid")) {
              break label1054;
            }
            Logging.Log("No previous adid in watchlist, adding " + Feature.advertisingID);
            Feature.prefs.edit().putString("adid", Feature.advertisingID).commit();
            localHashMap.put("adid", Feature.advertisingID);
          }
          label383:
          if (Feature.prefs.contains("os_version")) {
            break label1136;
          }
          Logging.Log("No previous os_version in watchlist, adding " + Feature.access$3300());
          Feature.prefs.edit().putString("os_version", Feature.access$3300()).commit();
        }
        Object localObject4;
        for (;;)
        {
          if (!localHashMap.keySet().isEmpty()) {
            try
            {
              localObject2 = new JSONObject();
              ((JSONObject)localObject2).put("action", "update");
              ((JSONObject)localObject2).put("kochava_device_id", Feature.access$2700());
              ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
              ((JSONObject)localObject2).put("sdk_version", "Android20160105" + Feature.versionExtension);
              ((JSONObject)localObject2).put("sdk_protocol", "4");
              localObject3 = new JSONObject();
              localObject4 = localHashMap.keySet().iterator();
              while (((Iterator)localObject4).hasNext())
              {
                String str = (String)((Iterator)localObject4).next();
                ((JSONObject)localObject3).put(str, localHashMap.get(str));
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
          localException.put("app_short_string", Feature.this.mAppVersionName + "");
          Feature.prefs.edit().putString("app_short_string", Feature.this.mAppVersionName).commit();
          break;
          label730:
          if (Feature.prefs.getBoolean("app_limit_tracking", false) == Feature.this.app_limit_tracking) {
            break label176;
          }
          Logging.Log("app_limit_tracking changed! Is now " + Feature.this.app_limit_tracking);
          localException.put("app_limit_tracking", Feature.this.app_limit_tracking + "");
          Feature.prefs.edit().putBoolean("app_limit_tracking", Feature.this.app_limit_tracking).commit();
          break label176;
          label842:
          if (Feature.prefs.getString("app_version", "").equals(Feature.this.getAppVersion())) {
            break label245;
          }
          Logging.Log("app_version changed! Is now " + Feature.this.getAppVersion());
          localException.put("app_version", Feature.this.getAppVersion() + "");
          Feature.prefs.edit().putString("app_version", Feature.this.getAppVersion()).commit();
          break label245;
          label958:
          if (Feature.prefs.getBoolean("device_limit_tracking", false) == Feature.device_limit_tracking) {
            break label306;
          }
          Logging.Log("device_limit_tracking changed! Is now " + Feature.device_limit_tracking);
          localException.put("device_limit_tracking", Feature.device_limit_tracking + "");
          Feature.prefs.edit().putBoolean("device_limit_tracking", Feature.device_limit_tracking).commit();
          break label306;
          label1054:
          if (Feature.prefs.getString("adid", "").equals(Feature.advertisingID)) {
            break label383;
          }
          Logging.Log("adid changed! Is now " + Feature.advertisingID);
          localException.put("adid", Feature.advertisingID);
          Feature.prefs.edit().putString("adid", Feature.advertisingID).commit();
          break label383;
          label1136:
          if (!Feature.prefs.getString("os_version", "").equals(Feature.access$3300()))
          {
            Logging.Log("os_version changed! Is now " + Feature.access$3300());
            localException.put("os_version", Feature.access$3300());
            Feature.prefs.edit().putString("os_version", Feature.access$3300()).commit();
          }
        }
        ((JSONObject)localObject2).put("data", localObject3);
        if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
          Feature.access$402("control.kochava.com");
        }
        Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
        Object localObject3 = (HttpsURLConnection)HttpInstrumentation.openConnection(new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection());
        ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", Feature.mUserAgent);
        ((HttpsURLConnection)localObject3).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        ((HttpsURLConnection)localObject3).setRequestMethod("POST");
        ((HttpsURLConnection)localObject3).setConnectTimeout(30000);
        ((HttpsURLConnection)localObject3).setReadTimeout(30000);
        ((HttpsURLConnection)localObject3).setDoInput(true);
        ((HttpsURLConnection)localObject3).setDoOutput(true);
        ((HttpsURLConnection)localObject3).connect();
        if (!(localObject2 instanceof JSONObject))
        {
          localObject1 = ((JSONObject)localObject2).toString();
          localObject4 = new StringBuilder().append("Trying to post an update: ");
          if ((localObject2 instanceof JSONObject)) {
            break label1518;
          }
        }
        label1518:
        for (Object localObject2 = ((JSONObject)localObject2).toString();; localObject2 = JSONObjectInstrumentation.toString((JSONObject)localObject2))
        {
          Logging.Log((String)localObject2);
          localObject2 = new OutputStreamWriter(((HttpsURLConnection)localObject3).getOutputStream());
          ((OutputStreamWriter)localObject2).write((String)localObject1);
          ((OutputStreamWriter)localObject2).close();
          Logging.Log("(Update) Grabbing Result...");
          localObject1 = new StringBuffer("");
          localObject2 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject3).getInputStream()));
          for (;;)
          {
            localObject3 = ((BufferedReader)localObject2).readLine();
            if (localObject3 == null) {
              break;
            }
            ((StringBuffer)localObject1).append((String)localObject3);
          }
          localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject2);
          break;
        }
        Object localObject1 = ((StringBuffer)localObject1).toString();
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
                Feature.access$4700();
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
            Feature.access$4800();
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
      Feature.access$3802(true);
    }
    
    public void onActivityResumed(Activity paramActivity)
    {
      Logging.Log("LifeCycleTracker - Tracking Activity Resumed");
      Feature.AppLifeCycleStatusManager.changeStatus("is_focused");
      Feature.access$3802(false);
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
        Feature.access$3802(true);
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
      Feature.access$4600();
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
