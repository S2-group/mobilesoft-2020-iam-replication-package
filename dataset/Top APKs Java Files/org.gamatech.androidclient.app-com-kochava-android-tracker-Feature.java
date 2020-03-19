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
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
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
      //   146: ifle +4155 -> 4301
      //   149: aconst_null
      //   150: astore 13
      //   152: iload_2
      //   153: istore_1
      //   154: new 87	java/lang/StringBuilder
      //   157: dup
      //   158: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   161: ldc 106
      //   163: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   166: astore 15
      //   168: aload_0
      //   169: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   172: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   175: astore 14
      //   177: aload 14
      //   179: instanceof 112
      //   182: ifne +412 -> 594
      //   185: aload 14
      //   187: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   190: astore 14
      //   192: aload 15
      //   194: aload 14
      //   196: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   199: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   202: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   205: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   208: ifnull +15 -> 223
      //   211: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   214: invokevirtual 119	java/lang/String:trim	()Ljava/lang/String;
      //   217: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   220: ifeq +14 -> 234
      //   223: ldc 125
      //   225: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   228: ldc 127
      //   230: invokestatic 131	com/kochava/android/tracker/Feature:access$602	(Ljava/lang/String;)Ljava/lang/String;
      //   233: pop
      //   234: new 87	java/lang/StringBuilder
      //   237: dup
      //   238: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   241: ldc -123
      //   243: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   246: ldc -121
      //   248: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   251: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   254: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   257: ldc -119
      //   259: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   262: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   265: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   268: new 139	java/net/URL
      //   271: dup
      //   272: new 87	java/lang/StringBuilder
      //   275: dup
      //   276: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   279: ldc -121
      //   281: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   284: invokestatic 116	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   287: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   290: ldc -119
      //   292: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   295: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   298: invokespecial 141	java/net/URL:<init>	(Ljava/lang/String;)V
      //   301: invokevirtual 145	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   304: invokestatic 150	com/newrelic/agent/android/instrumentation/HttpInstrumentation:openConnection	(Ljava/net/URLConnection;)Ljava/net/URLConnection;
      //   307: checkcast 152	javax/net/ssl/HttpsURLConnection
      //   310: astore 15
      //   312: aload 15
      //   314: ldc -102
      //   316: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   319: ldc -100
      //   321: ldc 71
      //   323: invokeinterface 75 3 0
      //   328: invokevirtual 160	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   331: aload 15
      //   333: ldc -94
      //   335: ldc -92
      //   337: invokevirtual 160	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   340: aload 15
      //   342: ldc -90
      //   344: invokevirtual 169	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   347: aload 15
      //   349: sipush 30000
      //   352: invokevirtual 173	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   355: aload 15
      //   357: sipush 30000
      //   360: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   363: aload 15
      //   365: iconst_1
      //   366: invokevirtual 180	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   369: aload 15
      //   371: iconst_1
      //   372: invokevirtual 183	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   375: aload 15
      //   377: invokevirtual 186	javax/net/ssl/HttpsURLConnection:connect	()V
      //   380: aload_0
      //   381: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   384: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   387: astore 14
      //   389: aload 14
      //   391: instanceof 112
      //   394: ifne +213 -> 607
      //   397: aload 14
      //   399: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   402: astore 14
      //   404: new 87	java/lang/StringBuilder
      //   407: dup
      //   408: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   411: ldc -68
      //   413: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   416: aload 14
      //   418: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   421: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   424: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   427: ldc -66
      //   429: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   432: new 192	java/io/OutputStreamWriter
      //   435: dup
      //   436: aload 15
      //   438: invokevirtual 196	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   441: invokespecial 199	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   444: astore 16
      //   446: aload 16
      //   448: aload 14
      //   450: invokevirtual 202	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   453: aload 16
      //   455: invokevirtual 205	java/io/OutputStreamWriter:close	()V
      //   458: ldc -49
      //   460: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   463: new 209	java/lang/StringBuffer
      //   466: dup
      //   467: ldc 71
      //   469: invokespecial 210	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   472: astore 14
      //   474: new 212	java/io/BufferedReader
      //   477: dup
      //   478: new 214	java/io/InputStreamReader
      //   481: dup
      //   482: aload 15
      //   484: invokevirtual 218	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   487: invokespecial 221	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   490: invokespecial 224	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   493: astore 15
      //   495: aload 15
      //   497: invokevirtual 227	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   500: astore 16
      //   502: aload 16
      //   504: ifnull +116 -> 620
      //   507: aload 14
      //   509: aload 16
      //   511: invokevirtual 230	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   514: pop
      //   515: goto -20 -> 495
      //   518: astore 13
      //   520: ldc -24
      //   522: aload 13
      //   524: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   527: invokevirtual 242	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   530: ifeq +3746 -> 4276
      //   533: new 87	java/lang/StringBuilder
      //   536: dup
      //   537: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   540: ldc -12
      //   542: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   545: aload 13
      //   547: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   550: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   553: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   556: aload 13
      //   558: invokestatic 254	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
      //   561: return
      //   562: astore 13
      //   564: new 87	java/lang/StringBuilder
      //   567: dup
      //   568: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   571: ldc_w 256
      //   574: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   577: aload 13
      //   579: invokevirtual 257	java/lang/Exception:toString	()Ljava/lang/String;
      //   582: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   585: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   588: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   591: goto -549 -> 42
      //   594: aload 14
      //   596: checkcast 112	org/json/JSONObject
      //   599: invokestatic 262	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   602: astore 14
      //   604: goto -412 -> 192
      //   607: aload 14
      //   609: checkcast 112	org/json/JSONObject
      //   612: invokestatic 262	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   615: astore 14
      //   617: goto -213 -> 404
      //   620: aload 14
      //   622: invokevirtual 263	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   625: astore 14
      //   627: new 87	java/lang/StringBuilder
      //   630: dup
      //   631: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   634: ldc_w 265
      //   637: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   640: aload 14
      //   642: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   645: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   648: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   651: aload 14
      //   653: invokestatic 269	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   656: astore 14
      //   658: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   661: ldc_w 271
      //   664: ldc 71
      //   666: invokeinterface 75 3 0
      //   671: ldc_w 273
      //   674: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   677: istore 12
      //   679: iload_1
      //   680: istore_2
      //   681: iload 12
      //   683: ifne +134 -> 817
      //   686: iload_1
      //   687: istore_2
      //   688: aload 14
      //   690: ifnull +127 -> 817
      //   693: aload 14
      //   695: ldc_w 275
      //   698: invokevirtual 279	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   701: astore 13
      //   703: iconst_0
      //   704: istore 5
      //   706: iconst_0
      //   707: istore 4
      //   709: iload_1
      //   710: istore_2
      //   711: iload 5
      //   713: istore_3
      //   714: iload 4
      //   716: aload 13
      //   718: invokevirtual 285	org/json/JSONArray:length	()I
      //   721: if_icmpge +90 -> 811
      //   724: ldc_w 287
      //   727: aload 13
      //   729: iload 4
      //   731: invokevirtual 290	org/json/JSONArray:getString	(I)Ljava/lang/String;
      //   734: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   737: ifeq +1434 -> 2171
      //   740: iconst_1
      //   741: istore_3
      //   742: iload_1
      //   743: istore_2
      //   744: iload_1
      //   745: iconst_4
      //   746: if_icmpge +7 -> 753
      //   749: iload_1
      //   750: iconst_1
      //   751: iadd
      //   752: istore_2
      //   753: new 87	java/lang/StringBuilder
      //   756: dup
      //   757: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   760: ldc_w 292
      //   763: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   766: invokestatic 296	com/kochava/android/tracker/Feature:access$700	()Ljava/util/HashMap;
      //   769: iload_2
      //   770: invokestatic 302	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   773: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   776: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   779: ldc_w 310
      //   782: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   785: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   788: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   791: invokestatic 296	com/kochava/android/tracker/Feature:access$700	()Ljava/util/HashMap;
      //   794: iload_2
      //   795: invokestatic 302	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   798: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   801: checkcast 298	java/lang/Integer
      //   804: invokevirtual 313	java/lang/Integer:intValue	()I
      //   807: i2l
      //   808: invokestatic 319	java/lang/Thread:sleep	(J)V
      //   811: iload_3
      //   812: ifne +5 -> 817
      //   815: iconst_0
      //   816: istore_2
      //   817: iload_2
      //   818: istore_1
      //   819: aload 14
      //   821: astore 13
      //   823: iload_2
      //   824: ifne -670 -> 154
      //   827: aload 14
      //   829: ifnull +1048 -> 1877
      //   832: new 87	java/lang/StringBuilder
      //   835: dup
      //   836: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   839: ldc_w 321
      //   842: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   845: astore 15
      //   847: aload 14
      //   849: instanceof 112
      //   852: ifne +1335 -> 2187
      //   855: aload 14
      //   857: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   860: astore 13
      //   862: aload 15
      //   864: aload 13
      //   866: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   869: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   872: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   875: aconst_null
      //   876: astore 13
      //   878: aload 14
      //   880: ldc_w 323
      //   883: invokevirtual 326	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   886: astore 15
      //   888: aload 15
      //   890: astore 13
      //   892: new 87	java/lang/StringBuilder
      //   895: dup
      //   896: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   899: ldc_w 328
      //   902: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   905: astore 17
      //   907: aload 15
      //   909: astore 13
      //   911: aload 15
      //   913: instanceof 112
      //   916: ifne +1284 -> 2200
      //   919: aload 15
      //   921: astore 13
      //   923: aload 15
      //   925: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   928: astore 16
      //   930: aload 15
      //   932: astore 13
      //   934: aload 17
      //   936: aload 16
      //   938: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   941: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   944: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   947: aload 15
      //   949: astore 13
      //   951: aload 13
      //   953: ifnull +750 -> 1703
      //   956: aload 13
      //   958: ldc_w 330
      //   961: invokevirtual 332	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   964: astore 15
      //   966: new 87	java/lang/StringBuilder
      //   969: dup
      //   970: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   973: ldc_w 334
      //   976: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   979: aload 15
      //   981: invokevirtual 335	java/lang/String:toString	()Ljava/lang/String;
      //   984: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   987: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   990: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   993: aload 15
      //   995: invokestatic 338	com/kochava/android/tracker/Feature:access$802	(Ljava/lang/String;)Ljava/lang/String;
      //   998: pop
      //   999: aload 13
      //   1001: ldc_w 340
      //   1004: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1007: ldc_w 345
      //   1010: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1013: ifeq +8 -> 1021
      //   1016: iconst_0
      //   1017: invokestatic 350	com/kochava/android/tracker/Feature:access$902	(Z)Z
      //   1020: pop
      //   1021: aload 13
      //   1023: ldc_w 352
      //   1026: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1029: checkcast 81	java/lang/String
      //   1032: invokevirtual 355	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   1035: astore 15
      //   1037: new 87	java/lang/StringBuilder
      //   1040: dup
      //   1041: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1044: ldc_w 357
      //   1047: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1050: aload 15
      //   1052: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1055: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1058: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1061: aload_0
      //   1062: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   1065: aload 15
      //   1067: invokestatic 361	com/kochava/android/tracker/Feature:access$1000	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;)V
      //   1070: aload 13
      //   1072: ldc_w 363
      //   1075: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1078: ldc_w 273
      //   1081: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1084: ifeq +47 -> 1131
      //   1087: ldc_w 365
      //   1090: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1093: getstatic 369	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   1096: ldc_w 371
      //   1099: iconst_0
      //   1100: invokevirtual 377	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   1103: invokestatic 381	com/kochava/android/tracker/Feature:access$402	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   1106: pop
      //   1107: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1110: invokeinterface 385 1 0
      //   1115: ldc_w 271
      //   1118: ldc_w 387
      //   1121: invokeinterface 393 3 0
      //   1126: invokeinterface 396 1 0
      //   1131: aload 13
      //   1133: ldc_w 398
      //   1136: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1139: checkcast 298	java/lang/Integer
      //   1142: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1145: invokestatic 402	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1148: pop
      //   1149: invokestatic 405	com/kochava/android/tracker/Feature:access$1100	()I
      //   1152: ifge +1142 -> 2294
      //   1155: new 87	java/lang/StringBuilder
      //   1158: dup
      //   1159: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1162: ldc_w 407
      //   1165: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1168: invokestatic 405	com/kochava/android/tracker/Feature:access$1100	()I
      //   1171: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1174: ldc_w 412
      //   1177: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1180: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1183: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1186: iconst_0
      //   1187: invokestatic 402	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1190: pop
      //   1191: invokestatic 415	com/kochava/android/tracker/Feature:access$1200	()Z
      //   1194: ifne +89 -> 1283
      //   1197: aload 13
      //   1199: ldc_w 417
      //   1202: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1205: ifnull +78 -> 1283
      //   1208: aload 13
      //   1210: ldc_w 417
      //   1213: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1216: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1219: ldc_w 298
      //   1222: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1225: ifeq +58 -> 1283
      //   1228: aload 13
      //   1230: ldc_w 417
      //   1233: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1236: checkcast 298	java/lang/Integer
      //   1239: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1242: istore_1
      //   1243: iload_1
      //   1244: bipush 60
      //   1246: if_icmpge +1124 -> 2370
      //   1249: new 87	java/lang/StringBuilder
      //   1252: dup
      //   1253: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1256: ldc_w 419
      //   1259: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1262: iload_1
      //   1263: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1266: ldc_w 421
      //   1269: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1272: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1275: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1278: iconst_1
      //   1279: invokestatic 424	com/kochava/android/tracker/Feature:access$1402	(Z)Z
      //   1282: pop
      //   1283: aload 13
      //   1285: ldc 102
      //   1287: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1290: ifnull +92 -> 1382
      //   1293: aload 13
      //   1295: ldc 102
      //   1297: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1300: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1303: ldc_w 298
      //   1306: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1309: ifeq +73 -> 1382
      //   1312: aload 13
      //   1314: ldc 102
      //   1316: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1319: checkcast 298	java/lang/Integer
      //   1322: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1325: istore_1
      //   1326: iload_1
      //   1327: ifge +1130 -> 2457
      //   1330: new 87	java/lang/StringBuilder
      //   1333: dup
      //   1334: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1337: ldc_w 426
      //   1340: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1343: iload_1
      //   1344: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1347: ldc_w 428
      //   1350: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1353: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1356: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1359: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1362: invokeinterface 385 1 0
      //   1367: ldc 102
      //   1369: ldc2_w 103
      //   1372: invokeinterface 432 4 0
      //   1377: invokeinterface 396 1 0
      //   1382: aload 13
      //   1384: ldc_w 434
      //   1387: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1390: ifnull +77 -> 1467
      //   1393: aload 13
      //   1395: ldc_w 434
      //   1398: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1401: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1404: ldc_w 298
      //   1407: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1410: ifeq +57 -> 1467
      //   1413: aload 13
      //   1415: ldc_w 434
      //   1418: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1421: checkcast 298	java/lang/Integer
      //   1424: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1427: istore_1
      //   1428: iload_1
      //   1429: iconst_1
      //   1430: if_icmpge +1147 -> 2577
      //   1433: new 87	java/lang/StringBuilder
      //   1436: dup
      //   1437: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1440: ldc_w 436
      //   1443: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1446: iload_1
      //   1447: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1450: ldc_w 438
      //   1453: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1456: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1459: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1462: iconst_1
      //   1463: invokestatic 441	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   1466: pop
      //   1467: aload 13
      //   1469: ldc_w 443
      //   1472: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1475: checkcast 298	java/lang/Integer
      //   1478: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1481: istore_2
      //   1482: iload_2
      //   1483: bipush 10
      //   1485: if_icmpge +1173 -> 2658
      //   1488: new 87	java/lang/StringBuilder
      //   1491: dup
      //   1492: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1495: ldc_w 445
      //   1498: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1501: iload_2
      //   1502: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1505: ldc_w 447
      //   1508: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1511: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1514: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1517: bipush 10
      //   1519: istore_1
      //   1520: new 87	java/lang/StringBuilder
      //   1523: dup
      //   1524: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1527: ldc_w 449
      //   1530: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1533: iload_1
      //   1534: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1537: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1540: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1543: iload_1
      //   1544: putstatic 455	com/kochava/android/tracker/LocationDirector:desiredAccuracy	I
      //   1547: aload 13
      //   1549: ldc_w 457
      //   1552: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1555: checkcast 298	java/lang/Integer
      //   1558: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1561: istore_2
      //   1562: iload_2
      //   1563: iconst_3
      //   1564: if_icmpge +1139 -> 2703
      //   1567: new 87	java/lang/StringBuilder
      //   1570: dup
      //   1571: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1574: ldc_w 459
      //   1577: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1580: iload_2
      //   1581: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1584: ldc_w 461
      //   1587: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1590: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1593: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1596: iconst_3
      //   1597: istore_1
      //   1598: new 87	java/lang/StringBuilder
      //   1601: dup
      //   1602: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1605: ldc_w 463
      //   1608: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1611: iload_1
      //   1612: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1615: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1618: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1621: iload_1
      //   1622: putstatic 466	com/kochava/android/tracker/LocationDirector:timeout	I
      //   1625: aload 13
      //   1627: ldc_w 468
      //   1630: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1633: checkcast 298	java/lang/Integer
      //   1636: invokevirtual 313	java/lang/Integer:intValue	()I
      //   1639: istore_2
      //   1640: iload_2
      //   1641: iconst_1
      //   1642: if_icmpge +1104 -> 2746
      //   1645: new 87	java/lang/StringBuilder
      //   1648: dup
      //   1649: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1652: ldc_w 470
      //   1655: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1658: iload_2
      //   1659: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1662: ldc_w 472
      //   1665: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1668: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1671: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1674: iconst_1
      //   1675: istore_1
      //   1676: new 87	java/lang/StringBuilder
      //   1679: dup
      //   1680: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1683: ldc_w 474
      //   1686: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1689: iload_1
      //   1690: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1693: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1696: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1699: iload_1
      //   1700: putstatic 477	com/kochava/android/tracker/LocationDirector:staleness	I
      //   1703: aload 14
      //   1705: ldc_w 479
      //   1708: invokevirtual 279	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1711: astore 16
      //   1713: new 87	java/lang/StringBuilder
      //   1716: dup
      //   1717: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1720: ldc_w 481
      //   1723: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1726: astore 17
      //   1728: aload 16
      //   1730: instanceof 281
      //   1733: ifne +1058 -> 2791
      //   1736: aload 16
      //   1738: invokevirtual 482	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1741: astore 15
      //   1743: aload 17
      //   1745: aload 15
      //   1747: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1750: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1753: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1756: iconst_0
      //   1757: istore_1
      //   1758: iload_1
      //   1759: aload 16
      //   1761: invokevirtual 285	org/json/JSONArray:length	()I
      //   1764: if_icmpge +1103 -> 2867
      //   1767: aload 16
      //   1769: iload_1
      //   1770: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1773: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   1776: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1779: ldc_w 491
      //   1782: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1785: ifeq +1019 -> 2804
      //   1788: ldc_w 493
      //   1791: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1794: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   1797: ldc_w 491
      //   1800: iconst_0
      //   1801: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1804: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1807: pop
      //   1808: iload_1
      //   1809: iconst_1
      //   1810: iadd
      //   1811: istore_1
      //   1812: goto -54 -> 1758
      //   1815: astore 14
      //   1817: new 87	java/lang/StringBuilder
      //   1820: dup
      //   1821: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1824: ldc_w 507
      //   1827: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1830: aload 14
      //   1832: invokevirtual 508	org/json/JSONException:toString	()Ljava/lang/String;
      //   1835: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1838: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1841: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1844: aload 13
      //   1846: astore 14
      //   1848: goto -1190 -> 658
      //   1851: astore 13
      //   1853: new 87	java/lang/StringBuilder
      //   1856: dup
      //   1857: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1860: ldc_w 510
      //   1863: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1866: aload 13
      //   1868: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1871: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1874: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1877: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1880: invokeinterface 385 1 0
      //   1885: ldc 57
      //   1887: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   1890: invokeinterface 432 4 0
      //   1895: invokeinterface 396 1 0
      //   1900: ldc_w 512
      //   1903: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1906: iconst_0
      //   1907: istore_1
      //   1908: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1911: ldc_w 271
      //   1914: ldc 71
      //   1916: invokeinterface 75 3 0
      //   1921: ldc_w 273
      //   1924: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1927: ifne +2578 -> 4505
      //   1930: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   1933: ldc_w 514
      //   1936: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1939: checkcast 498	java/lang/Boolean
      //   1942: invokevirtual 517	java/lang/Boolean:booleanValue	()Z
      //   1945: ifne +2393 -> 4338
      //   1948: iconst_1
      //   1949: istore_2
      //   1950: invokestatic 520	com/kochava/android/tracker/Feature:access$3100	()Z
      //   1953: istore 12
      //   1955: iload_1
      //   1956: invokestatic 405	com/kochava/android/tracker/Feature:access$1100	()I
      //   1959: if_icmpge +50 -> 2009
      //   1962: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1965: ldc_w 522
      //   1968: ldc_w 524
      //   1971: invokeinterface 75 3 0
      //   1976: ldc_w 524
      //   1979: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1982: ifne +2361 -> 4343
      //   1985: iconst_1
      //   1986: istore_3
      //   1987: invokestatic 527	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   1990: ifnull +2358 -> 4348
      //   1993: iconst_1
      //   1994: istore 4
      //   1996: iload_3
      //   1997: ifeq +2357 -> 4354
      //   2000: iload_2
      //   2001: ifne +8 -> 2009
      //   2004: iload 12
      //   2006: ifeq +2348 -> 4354
      //   2009: new 87	java/lang/StringBuilder
      //   2012: dup
      //   2013: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2016: ldc_w 529
      //   2019: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2022: iload_1
      //   2023: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2026: ldc_w 531
      //   2029: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2032: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2035: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2038: iload_1
      //   2039: istore_2
      //   2040: iload_1
      //   2041: iconst_2
      //   2042: if_icmpge +46 -> 2088
      //   2045: aload_0
      //   2046: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2049: invokestatic 534	com/kochava/android/tracker/Feature:access$000	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   2052: ifnull +2353 -> 4405
      //   2055: iconst_1
      //   2056: istore_3
      //   2057: invokestatic 527	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   2060: ifnull +2350 -> 4410
      //   2063: invokestatic 527	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   2066: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   2069: ifne +2341 -> 4410
      //   2072: iconst_1
      //   2073: istore 4
      //   2075: iload_1
      //   2076: istore_2
      //   2077: iload 4
      //   2079: ifne +9 -> 2088
      //   2082: iload_3
      //   2083: ifeq +2333 -> 4416
      //   2086: iload_1
      //   2087: istore_2
      //   2088: iload_2
      //   2089: iconst_2
      //   2090: if_icmpge +19 -> 2109
      //   2093: aload_0
      //   2094: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2097: invokestatic 537	com/kochava/android/tracker/Feature:access$300	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   2100: ifnull +2358 -> 4458
      //   2103: iconst_1
      //   2104: istore_1
      //   2105: iload_1
      //   2106: ifeq +2357 -> 4463
      //   2109: invokestatic 543	android/os/Message:obtain	()Landroid/os/Message;
      //   2112: astore 13
      //   2114: new 545	android/os/Bundle
      //   2117: dup
      //   2118: invokespecial 546	android/os/Bundle:<init>	()V
      //   2121: astore 14
      //   2123: aload 14
      //   2125: ldc_w 548
      //   2128: invokestatic 551	com/kochava/android/tracker/Feature:access$3200	()Z
      //   2131: invokevirtual 555	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   2134: aload 13
      //   2136: aload 14
      //   2138: invokevirtual 559	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   2141: aload_0
      //   2142: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2145: invokestatic 563	com/kochava/android/tracker/Feature:access$3300	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   2148: ifnull -1587 -> 561
      //   2151: ldc_w 565
      //   2154: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2157: aload_0
      //   2158: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2161: invokestatic 563	com/kochava/android/tracker/Feature:access$3300	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   2164: aload 13
      //   2166: invokevirtual 571	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   2169: pop
      //   2170: return
      //   2171: iload 4
      //   2173: iconst_1
      //   2174: iadd
      //   2175: istore 4
      //   2177: goto -1468 -> 709
      //   2180: astore 13
      //   2182: iconst_0
      //   2183: istore_2
      //   2184: goto -1367 -> 817
      //   2187: aload 14
      //   2189: checkcast 112	org/json/JSONObject
      //   2192: invokestatic 262	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   2195: astore 13
      //   2197: goto -1335 -> 862
      //   2200: aload 15
      //   2202: astore 13
      //   2204: aload 15
      //   2206: checkcast 112	org/json/JSONObject
      //   2209: invokestatic 262	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
      //   2212: astore 16
      //   2214: goto -1284 -> 930
      //   2217: astore 15
      //   2219: ldc_w 573
      //   2222: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2225: goto -1274 -> 951
      //   2228: astore 13
      //   2230: aload 13
      //   2232: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2235: ldc -24
      //   2237: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2240: ifeq +2011 -> 4251
      //   2243: new 87	java/lang/StringBuilder
      //   2246: dup
      //   2247: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2250: ldc -12
      //   2252: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2255: aload 13
      //   2257: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   2260: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2263: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2266: aload 13
      //   2268: invokestatic 254	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
      //   2271: return
      //   2272: astore 15
      //   2274: ldc_w 575
      //   2277: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2280: goto -1281 -> 999
      //   2283: astore 15
      //   2285: ldc_w 573
      //   2288: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2291: goto -1270 -> 1021
      //   2294: invokestatic 405	com/kochava/android/tracker/Feature:access$1100	()I
      //   2297: bipush 120
      //   2299: if_icmple +43 -> 2342
      //   2302: new 87	java/lang/StringBuilder
      //   2305: dup
      //   2306: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2309: ldc_w 577
      //   2312: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2315: invokestatic 405	com/kochava/android/tracker/Feature:access$1100	()I
      //   2318: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2321: ldc_w 579
      //   2324: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2327: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2330: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2333: bipush 120
      //   2335: invokestatic 402	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   2338: pop
      //   2339: goto -1148 -> 1191
      //   2342: new 87	java/lang/StringBuilder
      //   2345: dup
      //   2346: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2349: ldc_w 581
      //   2352: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2355: invokestatic 405	com/kochava/android/tracker/Feature:access$1100	()I
      //   2358: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2361: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2364: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2367: goto -1176 -> 1191
      //   2370: iload_1
      //   2371: ldc_w 582
      //   2374: if_icmple +42 -> 2416
      //   2377: new 87	java/lang/StringBuilder
      //   2380: dup
      //   2381: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2384: ldc_w 584
      //   2387: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2390: iload_1
      //   2391: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2394: ldc_w 586
      //   2397: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2400: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2403: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2406: ldc_w 587
      //   2409: invokestatic 590	com/kochava/android/tracker/Feature:access$1302	(I)I
      //   2412: pop
      //   2413: goto -1135 -> 1278
      //   2416: iload_1
      //   2417: sipush 1000
      //   2420: imul
      //   2421: invokestatic 590	com/kochava/android/tracker/Feature:access$1302	(I)I
      //   2424: pop
      //   2425: new 87	java/lang/StringBuilder
      //   2428: dup
      //   2429: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2432: ldc_w 592
      //   2435: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2438: iload_1
      //   2439: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2442: ldc_w 531
      //   2445: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2448: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2451: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2454: goto -1176 -> 1278
      //   2457: iload_1
      //   2458: ldc_w 582
      //   2461: if_icmple +58 -> 2519
      //   2464: new 87	java/lang/StringBuilder
      //   2467: dup
      //   2468: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2471: ldc_w 426
      //   2474: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2477: iload_1
      //   2478: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2481: ldc_w 594
      //   2484: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2487: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2490: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2493: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2496: invokeinterface 385 1 0
      //   2501: ldc 102
      //   2503: ldc2_w 595
      //   2506: invokeinterface 432 4 0
      //   2511: invokeinterface 396 1 0
      //   2516: goto -1134 -> 1382
      //   2519: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2522: invokeinterface 385 1 0
      //   2527: ldc 102
      //   2529: iload_1
      //   2530: sipush 1000
      //   2533: imul
      //   2534: i2l
      //   2535: invokeinterface 432 4 0
      //   2540: invokeinterface 396 1 0
      //   2545: new 87	java/lang/StringBuilder
      //   2548: dup
      //   2549: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2552: ldc_w 598
      //   2555: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2558: iload_1
      //   2559: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2562: ldc_w 531
      //   2565: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2568: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2571: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2574: goto -1192 -> 1382
      //   2577: iload_1
      //   2578: bipush 30
      //   2580: if_icmple +41 -> 2621
      //   2583: new 87	java/lang/StringBuilder
      //   2586: dup
      //   2587: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2590: ldc_w 436
      //   2593: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2596: iload_1
      //   2597: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2600: ldc_w 600
      //   2603: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2606: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2609: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2612: bipush 30
      //   2614: invokestatic 441	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   2617: pop
      //   2618: goto -1151 -> 1467
      //   2621: new 87	java/lang/StringBuilder
      //   2624: dup
      //   2625: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2628: ldc_w 602
      //   2631: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2634: iload_1
      //   2635: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2638: ldc_w 531
      //   2641: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2644: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2647: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2650: iload_1
      //   2651: invokestatic 441	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   2654: pop
      //   2655: goto -1188 -> 1467
      //   2658: iload_2
      //   2659: istore_1
      //   2660: iload_2
      //   2661: sipush 5000
      //   2664: if_icmple -1144 -> 1520
      //   2667: new 87	java/lang/StringBuilder
      //   2670: dup
      //   2671: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2674: ldc_w 445
      //   2677: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2680: iload_2
      //   2681: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2684: ldc_w 447
      //   2687: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2690: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2693: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2696: sipush 5000
      //   2699: istore_1
      //   2700: goto -1180 -> 1520
      //   2703: iload_2
      //   2704: istore_1
      //   2705: iload_2
      //   2706: bipush 60
      //   2708: if_icmple -1110 -> 1598
      //   2711: new 87	java/lang/StringBuilder
      //   2714: dup
      //   2715: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2718: ldc_w 459
      //   2721: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2724: iload_2
      //   2725: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2728: ldc_w 461
      //   2731: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2734: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2737: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2740: bipush 60
      //   2742: istore_1
      //   2743: goto -1145 -> 1598
      //   2746: iload_2
      //   2747: istore_1
      //   2748: iload_2
      //   2749: sipush 10080
      //   2752: if_icmple -1076 -> 1676
      //   2755: new 87	java/lang/StringBuilder
      //   2758: dup
      //   2759: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2762: ldc_w 470
      //   2765: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2768: iload_2
      //   2769: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2772: ldc_w 472
      //   2775: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2778: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2781: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2784: sipush 10080
      //   2787: istore_1
      //   2788: goto -1112 -> 1676
      //   2791: aload 16
      //   2793: checkcast 281	org/json/JSONArray
      //   2796: invokestatic 607	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   2799: astore 15
      //   2801: goto -1058 -> 1743
      //   2804: aload 16
      //   2806: iload_1
      //   2807: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2810: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   2813: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2816: ldc_w 609
      //   2819: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2822: ifeq +552 -> 3374
      //   2825: ldc_w 611
      //   2828: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2831: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2834: ldc_w 609
      //   2837: iconst_0
      //   2838: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2841: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2844: pop
      //   2845: goto -1037 -> 1808
      //   2848: astore 15
      //   2850: ldc_w 613
      //   2853: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2856: getstatic 619	com/kochava/android/tracker/Global:DEBUGERROR	Z
      //   2859: ifeq +8 -> 2867
      //   2862: aload 15
      //   2864: invokevirtual 622	java/lang/Exception:printStackTrace	()V
      //   2867: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2870: ldc_w 624
      //   2873: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2876: checkcast 498	java/lang/Boolean
      //   2879: invokevirtual 517	java/lang/Boolean:booleanValue	()Z
      //   2882: istore 12
      //   2884: iload 12
      //   2886: ifeq +784 -> 3670
      //   2889: getstatic 369	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2892: ldc_w 626
      //   2895: invokevirtual 629	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2898: checkcast 631	android/telephony/TelephonyManager
      //   2901: invokevirtual 634	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
      //   2904: invokestatic 637	com/kochava/android/tracker/Feature:access$1702	(Ljava/lang/String;)Ljava/lang/String;
      //   2907: pop
      //   2908: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2911: ldc_w 639
      //   2914: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2917: checkcast 498	java/lang/Boolean
      //   2920: invokevirtual 517	java/lang/Boolean:booleanValue	()Z
      //   2923: istore 12
      //   2925: iload 12
      //   2927: ifeq +784 -> 3711
      //   2930: getstatic 369	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2933: ldc_w 641
      //   2936: invokevirtual 629	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2939: checkcast 643	android/net/wifi/WifiManager
      //   2942: invokevirtual 647	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
      //   2945: invokevirtual 652	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
      //   2948: invokestatic 655	com/kochava/android/tracker/Feature:access$1802	(Ljava/lang/String;)Ljava/lang/String;
      //   2951: pop
      //   2952: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2955: ldc_w 657
      //   2958: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2961: checkcast 498	java/lang/Boolean
      //   2964: invokevirtual 517	java/lang/Boolean:booleanValue	()Z
      //   2967: istore 12
      //   2969: iload 12
      //   2971: ifeq +795 -> 3766
      //   2974: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2977: ldc_w 271
      //   2980: ldc 71
      //   2982: invokeinterface 75 3 0
      //   2987: ldc_w 273
      //   2990: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2993: ifne +161 -> 3154
      //   2996: getstatic 369	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2999: invokevirtual 661	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   3002: astore 17
      //   3004: aload 17
      //   3006: sipush 128
      //   3009: invokevirtual 667	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   3012: invokeinterface 673 1 0
      //   3017: astore 18
      //   3019: aload 18
      //   3021: invokeinterface 678 1 0
      //   3026: ifeq +128 -> 3154
      //   3029: aload 18
      //   3031: invokeinterface 682 1 0
      //   3036: checkcast 684	android/content/pm/ApplicationInfo
      //   3039: astore 19
      //   3041: aload 19
      //   3043: invokestatic 688	com/kochava/android/tracker/Feature:access$1900	(Landroid/content/pm/ApplicationInfo;)Z
      //   3046: istore 12
      //   3048: iload 12
      //   3050: ifne -31 -> 3019
      //   3053: aconst_null
      //   3054: astore 15
      //   3056: aload 17
      //   3058: aload 19
      //   3060: getfield 692	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   3063: iconst_0
      //   3064: invokevirtual 696	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   3067: astore 16
      //   3069: aload 16
      //   3071: astore 15
      //   3073: aload 15
      //   3075: ifnull -56 -> 3019
      //   3078: invokestatic 700	com/kochava/android/tracker/Feature:access$2000	()Ljava/util/List;
      //   3081: new 702	com/kochava/android/tracker/Feature$ApplicationInfoHolder
      //   3084: dup
      //   3085: aload_0
      //   3086: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3089: aload 15
      //   3091: getfield 705	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   3094: new 87	java/lang/StringBuilder
      //   3097: dup
      //   3098: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3101: aload 15
      //   3103: getfield 709	android/content/pm/PackageInfo:firstInstallTime	J
      //   3106: invokevirtual 712	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3109: ldc 71
      //   3111: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3114: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3117: new 87	java/lang/StringBuilder
      //   3120: dup
      //   3121: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3124: aload 15
      //   3126: getfield 715	android/content/pm/PackageInfo:lastUpdateTime	J
      //   3129: invokevirtual 712	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3132: ldc 71
      //   3134: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3137: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3140: invokespecial 718	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   3143: invokeinterface 721 2 0
      //   3148: pop
      //   3149: goto -130 -> 3019
      //   3152: astore 15
      //   3154: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3157: ldc_w 723
      //   3160: invokevirtual 308	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   3163: checkcast 498	java/lang/Boolean
      //   3166: invokevirtual 517	java/lang/Boolean:booleanValue	()Z
      //   3169: istore 12
      //   3171: iload 12
      //   3173: ifeq +634 -> 3807
      //   3176: getstatic 369	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   3179: ldc_w 725
      //   3182: invokevirtual 629	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   3185: checkcast 727	android/view/WindowManager
      //   3188: astore 15
      //   3190: aload_0
      //   3191: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3194: aload 15
      //   3196: invokeinterface 731 1 0
      //   3201: invokevirtual 736	android/view/Display:getHeight	()I
      //   3204: invokestatic 740	com/kochava/android/tracker/Feature:access$2102	(Lcom/kochava/android/tracker/Feature;I)I
      //   3207: pop
      //   3208: aload_0
      //   3209: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3212: aload 15
      //   3214: invokeinterface 731 1 0
      //   3219: invokevirtual 743	android/view/Display:getWidth	()I
      //   3222: invokestatic 746	com/kochava/android/tracker/Feature:access$2202	(Lcom/kochava/android/tracker/Feature;I)I
      //   3225: pop
      //   3226: new 87	java/lang/StringBuilder
      //   3229: dup
      //   3230: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3233: ldc_w 748
      //   3236: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3239: aload_0
      //   3240: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3243: invokestatic 752	com/kochava/android/tracker/Feature:access$2100	(Lcom/kochava/android/tracker/Feature;)I
      //   3246: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3249: ldc_w 754
      //   3252: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3255: aload_0
      //   3256: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3259: invokestatic 757	com/kochava/android/tracker/Feature:access$2200	(Lcom/kochava/android/tracker/Feature;)I
      //   3262: invokevirtual 410	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3265: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3268: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3271: aload 14
      //   3273: ldc_w 759
      //   3276: invokevirtual 279	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3279: astore 16
      //   3281: new 87	java/lang/StringBuilder
      //   3284: dup
      //   3285: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3288: ldc_w 761
      //   3291: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3294: astore 17
      //   3296: aload 16
      //   3298: instanceof 281
      //   3301: ifne +515 -> 3816
      //   3304: aload 16
      //   3306: invokevirtual 482	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3309: astore 15
      //   3311: aload 17
      //   3313: aload 15
      //   3315: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3318: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3321: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3324: iconst_0
      //   3325: istore_1
      //   3326: iload_1
      //   3327: aload 16
      //   3329: invokevirtual 285	org/json/JSONArray:length	()I
      //   3332: if_icmpge +561 -> 3893
      //   3335: aload 16
      //   3337: iload_1
      //   3338: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3341: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3344: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3347: ldc_w 763
      //   3350: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3353: ifeq +476 -> 3829
      //   3356: ldc_w 765
      //   3359: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3362: iconst_1
      //   3363: invokestatic 768	com/kochava/android/tracker/Feature:access$2302	(Z)Z
      //   3366: pop
      //   3367: iload_1
      //   3368: iconst_1
      //   3369: iadd
      //   3370: istore_1
      //   3371: goto -45 -> 3326
      //   3374: aload 16
      //   3376: iload_1
      //   3377: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3380: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3383: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3386: ldc_w 514
      //   3389: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3392: ifeq +26 -> 3418
      //   3395: ldc_w 770
      //   3398: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3401: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3404: ldc_w 514
      //   3407: iconst_0
      //   3408: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3411: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3414: pop
      //   3415: goto -1607 -> 1808
      //   3418: aload 16
      //   3420: iload_1
      //   3421: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3424: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3427: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3430: ldc_w 639
      //   3433: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3436: ifeq +26 -> 3462
      //   3439: ldc_w 772
      //   3442: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3445: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3448: ldc_w 639
      //   3451: iconst_0
      //   3452: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3455: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3458: pop
      //   3459: goto -1651 -> 1808
      //   3462: aload 16
      //   3464: iload_1
      //   3465: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3468: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3471: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3474: ldc_w 624
      //   3477: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3480: ifeq +26 -> 3506
      //   3483: ldc_w 774
      //   3486: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3489: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3492: ldc_w 624
      //   3495: iconst_0
      //   3496: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3499: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3502: pop
      //   3503: goto -1695 -> 1808
      //   3506: aload 16
      //   3508: iload_1
      //   3509: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3512: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3515: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3518: ldc_w 723
      //   3521: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3524: ifeq +26 -> 3550
      //   3527: ldc_w 776
      //   3530: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3533: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3536: ldc_w 723
      //   3539: iconst_0
      //   3540: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3543: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3546: pop
      //   3547: goto -1739 -> 1808
      //   3550: aload 16
      //   3552: iload_1
      //   3553: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3556: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3559: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3562: ldc_w 657
      //   3565: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3568: ifeq +26 -> 3594
      //   3571: ldc_w 778
      //   3574: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3577: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3580: ldc_w 657
      //   3583: iconst_0
      //   3584: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3587: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3590: pop
      //   3591: goto -1783 -> 1808
      //   3594: aload 16
      //   3596: iload_1
      //   3597: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3600: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3603: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3606: ldc_w 780
      //   3609: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3612: ifeq -1804 -> 1808
      //   3615: ldc_w 782
      //   3618: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3621: invokestatic 496	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3624: ldc_w 780
      //   3627: iconst_0
      //   3628: invokestatic 501	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3631: invokevirtual 505	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3634: pop
      //   3635: goto -1827 -> 1808
      //   3638: astore 15
      //   3640: new 87	java/lang/StringBuilder
      //   3643: dup
      //   3644: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3647: ldc_w 784
      //   3650: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3653: aload 15
      //   3655: invokevirtual 257	java/lang/Exception:toString	()Ljava/lang/String;
      //   3658: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3661: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3664: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3667: goto -759 -> 2908
      //   3670: ldc_w 786
      //   3673: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3676: goto -768 -> 2908
      //   3679: astore 15
      //   3681: new 87	java/lang/StringBuilder
      //   3684: dup
      //   3685: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3688: ldc_w 788
      //   3691: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3694: aload 15
      //   3696: invokevirtual 257	java/lang/Exception:toString	()Ljava/lang/String;
      //   3699: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3702: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3705: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3708: goto -756 -> 2952
      //   3711: ldc_w 790
      //   3714: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3717: goto -765 -> 2952
      //   3720: astore 16
      //   3722: new 87	java/lang/StringBuilder
      //   3725: dup
      //   3726: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3729: ldc_w 792
      //   3732: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3735: aload 19
      //   3737: getfield 692	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   3740: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3743: ldc_w 794
      //   3746: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3749: aload 16
      //   3751: invokevirtual 795	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
      //   3754: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3757: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3760: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3763: goto -690 -> 3073
      //   3766: ldc_w 797
      //   3769: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3772: goto -618 -> 3154
      //   3775: astore 15
      //   3777: new 87	java/lang/StringBuilder
      //   3780: dup
      //   3781: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3784: ldc_w 799
      //   3787: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3790: aload 15
      //   3792: invokevirtual 257	java/lang/Exception:toString	()Ljava/lang/String;
      //   3795: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3798: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3801: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3804: goto -533 -> 3271
      //   3807: ldc_w 801
      //   3810: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3813: goto -542 -> 3271
      //   3816: aload 16
      //   3818: checkcast 281	org/json/JSONArray
      //   3821: invokestatic 607	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   3824: astore 15
      //   3826: goto -515 -> 3311
      //   3829: aload 16
      //   3831: iload_1
      //   3832: invokevirtual 485	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3835: invokevirtual 486	java/lang/Object:toString	()Ljava/lang/String;
      //   3838: invokevirtual 489	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3841: ldc_w 803
      //   3844: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3847: ifeq -480 -> 3367
      //   3850: ldc_w 805
      //   3853: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3856: iconst_1
      //   3857: invokestatic 808	com/kochava/android/tracker/Feature:access$2402	(Z)Z
      //   3860: pop
      //   3861: goto -494 -> 3367
      //   3864: astore 15
      //   3866: new 87	java/lang/StringBuilder
      //   3869: dup
      //   3870: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3873: ldc_w 810
      //   3876: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3879: aload 15
      //   3881: invokevirtual 257	java/lang/Exception:toString	()Ljava/lang/String;
      //   3884: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3887: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3890: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3893: aload 14
      //   3895: ldc_w 812
      //   3898: invokevirtual 326	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3901: ldc_w 814
      //   3904: invokevirtual 326	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3907: astore 15
      //   3909: aload_0
      //   3910: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3913: aload 15
      //   3915: invokestatic 818	com/kochava/android/tracker/Feature:access$2500	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   3918: invokestatic 821	com/kochava/android/tracker/Feature:access$2400	()Z
      //   3921: ifeq +18 -> 3939
      //   3924: invokestatic 824	com/kochava/android/tracker/Feature:access$2600	()Z
      //   3927: ifeq +12 -> 3939
      //   3930: getstatic 369	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   3933: invokestatic 828	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   3936: invokevirtual 831	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   3939: aload 14
      //   3941: ldc_w 833
      //   3944: invokevirtual 279	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3947: invokestatic 837	com/kochava/android/tracker/Feature:access$2702	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3950: pop
      //   3951: new 87	java/lang/StringBuilder
      //   3954: dup
      //   3955: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3958: ldc_w 839
      //   3961: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3964: astore 16
      //   3966: invokestatic 843	com/kochava/android/tracker/Feature:access$2700	()Lorg/json/JSONArray;
      //   3969: astore 15
      //   3971: aload 15
      //   3973: instanceof 281
      //   3976: ifne +228 -> 4204
      //   3979: aload 15
      //   3981: invokevirtual 482	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3984: astore 15
      //   3986: aload 16
      //   3988: aload 15
      //   3990: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3993: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3996: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3999: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   4002: invokeinterface 385 1 0
      //   4007: astore 16
      //   4009: invokestatic 843	com/kochava/android/tracker/Feature:access$2700	()Lorg/json/JSONArray;
      //   4012: astore 15
      //   4014: aload 15
      //   4016: instanceof 281
      //   4019: ifne +198 -> 4217
      //   4022: aload 15
      //   4024: invokevirtual 482	org/json/JSONArray:toString	()Ljava/lang/String;
      //   4027: astore 15
      //   4029: aload 16
      //   4031: ldc_w 845
      //   4034: aload 15
      //   4036: invokeinterface 393 3 0
      //   4041: invokeinterface 396 1 0
      //   4046: aload 13
      //   4048: ldc_w 847
      //   4051: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   4054: ifnull +88 -> 4142
      //   4057: aload 13
      //   4059: ldc_w 847
      //   4062: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   4065: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   4068: ldc_w 498
      //   4071: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   4074: ifeq +167 -> 4241
      //   4077: aload 13
      //   4079: ldc_w 847
      //   4082: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   4085: checkcast 498	java/lang/Boolean
      //   4088: invokevirtual 517	java/lang/Boolean:booleanValue	()Z
      //   4091: ifeq +150 -> 4241
      //   4094: iconst_1
      //   4095: istore_1
      //   4096: aload 13
      //   4098: ldc_w 847
      //   4101: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   4104: invokevirtual 236	java/lang/Object:getClass	()Ljava/lang/Class;
      //   4107: ldc 81
      //   4109: invokevirtual 346	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   4112: ifeq +134 -> 4246
      //   4115: ldc_w 273
      //   4118: aload 13
      //   4120: ldc_w 847
      //   4123: invokevirtual 343	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   4126: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4129: ifeq +117 -> 4246
      //   4132: iconst_1
      //   4133: istore_2
      //   4134: goto +440 -> 4574
      //   4137: iconst_1
      //   4138: invokestatic 850	com/kochava/android/tracker/Feature:access$2802	(Z)Z
      //   4141: pop
      //   4142: aload 14
      //   4144: ldc_w 852
      //   4147: invokevirtual 332	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   4150: astore 13
      //   4152: new 87	java/lang/StringBuilder
      //   4155: dup
      //   4156: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4159: ldc_w 854
      //   4162: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4165: aload 13
      //   4167: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4170: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4173: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4176: aload 13
      //   4178: ldc_w 856
      //   4181: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4184: ifeq -2307 -> 1877
      //   4187: iconst_1
      //   4188: invokestatic 859	com/kochava/android/tracker/Feature:access$2902	(Z)Z
      //   4191: pop
      //   4192: return
      //   4193: astore 13
      //   4195: ldc_w 861
      //   4198: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4201: goto -2324 -> 1877
      //   4204: aload 15
      //   4206: checkcast 281	org/json/JSONArray
      //   4209: invokestatic 607	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   4212: astore 15
      //   4214: goto -228 -> 3986
      //   4217: aload 15
      //   4219: checkcast 281	org/json/JSONArray
      //   4222: invokestatic 607	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:toString	(Lorg/json/JSONArray;)Ljava/lang/String;
      //   4225: astore 15
      //   4227: goto -198 -> 4029
      //   4230: astore 15
      //   4232: ldc_w 863
      //   4235: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4238: goto -192 -> 4046
      //   4241: iconst_0
      //   4242: istore_1
      //   4243: goto -147 -> 4096
      //   4246: iconst_0
      //   4247: istore_2
      //   4248: goto +326 -> 4574
      //   4251: new 87	java/lang/StringBuilder
      //   4254: dup
      //   4255: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4258: ldc_w 865
      //   4261: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4264: aload 13
      //   4266: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4269: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4272: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4275: return
      //   4276: new 87	java/lang/StringBuilder
      //   4279: dup
      //   4280: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4283: ldc_w 865
      //   4286: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4289: aload 13
      //   4291: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4294: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4297: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4300: return
      //   4301: new 87	java/lang/StringBuilder
      //   4304: dup
      //   4305: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4308: ldc_w 867
      //   4311: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4314: lload 6
      //   4316: ldc2_w 868
      //   4319: ldiv
      //   4320: invokevirtual 712	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   4323: ldc_w 871
      //   4326: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4329: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4332: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4335: goto -2435 -> 1900
      //   4338: iconst_0
      //   4339: istore_2
      //   4340: goto -2390 -> 1950
      //   4343: iconst_0
      //   4344: istore_3
      //   4345: goto -2358 -> 1987
      //   4348: iconst_0
      //   4349: istore 4
      //   4351: goto -2355 -> 1996
      //   4354: iload_3
      //   4355: ifeq +8 -> 4363
      //   4358: iload 4
      //   4360: ifne -2351 -> 2009
      //   4363: ldc2_w 868
      //   4366: invokestatic 319	java/lang/Thread:sleep	(J)V
      //   4369: iload_1
      //   4370: iconst_1
      //   4371: iadd
      //   4372: istore_1
      //   4373: goto -2418 -> 1955
      //   4376: astore 13
      //   4378: new 87	java/lang/StringBuilder
      //   4381: dup
      //   4382: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4385: ldc_w 873
      //   4388: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4391: aload 13
      //   4393: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4396: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4399: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4402: goto -33 -> 4369
      //   4405: iconst_0
      //   4406: istore_3
      //   4407: goto -2350 -> 2057
      //   4410: iconst_0
      //   4411: istore 4
      //   4413: goto -2338 -> 2075
      //   4416: ldc2_w 868
      //   4419: invokestatic 319	java/lang/Thread:sleep	(J)V
      //   4422: iload_1
      //   4423: iconst_1
      //   4424: iadd
      //   4425: istore_1
      //   4426: goto -2388 -> 2038
      //   4429: astore 13
      //   4431: new 87	java/lang/StringBuilder
      //   4434: dup
      //   4435: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4438: ldc_w 873
      //   4441: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4444: aload 13
      //   4446: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4449: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4452: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4455: goto -33 -> 4422
      //   4458: iconst_0
      //   4459: istore_1
      //   4460: goto -2355 -> 2105
      //   4463: ldc2_w 868
      //   4466: invokestatic 319	java/lang/Thread:sleep	(J)V
      //   4469: iload_2
      //   4470: iconst_1
      //   4471: iadd
      //   4472: istore_2
      //   4473: goto -2385 -> 2088
      //   4476: astore 13
      //   4478: new 87	java/lang/StringBuilder
      //   4481: dup
      //   4482: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4485: ldc_w 873
      //   4488: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4491: aload 13
      //   4493: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4496: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4499: invokestatic 250	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4502: goto -33 -> 4469
      //   4505: ldc_w 875
      //   4508: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4511: goto -2402 -> 2109
      //   4514: astore 13
      //   4516: goto -374 -> 4142
      //   4519: astore 15
      //   4521: goto -475 -> 4046
      //   4524: astore 15
      //   4526: goto -608 -> 3918
      //   4529: astore 15
      //   4531: goto -2828 -> 1703
      //   4534: astore 15
      //   4536: goto -2911 -> 1625
      //   4539: astore 15
      //   4541: goto -2994 -> 1547
      //   4544: astore 15
      //   4546: goto -3415 -> 1131
      //   4549: astore 15
      //   4551: goto -3481 -> 1070
      //   4554: astore 15
      //   4556: goto -3365 -> 1191
      //   4559: astore 15
      //   4561: goto -3278 -> 1283
      //   4564: astore 15
      //   4566: goto -3184 -> 1382
      //   4569: astore 15
      //   4571: goto -3104 -> 1467
      //   4574: iload_1
      //   4575: ifne -438 -> 4137
      //   4578: iload_2
      //   4579: ifeq -437 -> 4142
      //   4582: goto -445 -> 4137
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4585	0	this	6
      //   43	4532	1	i	int
      //   14	4565	2	j	int
      //   12	4395	3	k	int
      //   707	3705	4	m	int
      //   704	8	5	n	int
      //   9	4306	6	l1	long
      //   18	18	8	l2	long
      //   33	5	10	l3	long
      //   677	2495	12	bool	boolean
      //   150	1	13	localObject1	Object
      //   518	39	13	localIOException	IOException
      //   562	16	13	localException1	Exception
      //   701	1144	13	localObject2	Object
      //   1851	16	13	localException2	Exception
      //   2112	53	13	localMessage	Message
      //   2180	1	13	localJSONException1	JSONException
      //   2195	8	13	localObject3	Object
      //   2228	1891	13	localException3	Exception
      //   4150	27	13	str1	String
      //   4193	97	13	localException4	Exception
      //   4376	16	13	localInterruptedException1	InterruptedException
      //   4429	16	13	localInterruptedException2	InterruptedException
      //   4476	16	13	localInterruptedException3	InterruptedException
      //   4514	1	13	localException5	Exception
      //   175	1529	14	localObject4	Object
      //   1815	16	14	localJSONException2	JSONException
      //   1846	2297	14	localObject5	Object
      //   166	2039	15	localObject6	Object
      //   2217	1	15	localJSONException3	JSONException
      //   2272	1	15	localJSONException4	JSONException
      //   2283	1	15	localJSONException5	JSONException
      //   2799	1	15	str2	String
      //   2848	15	15	localException6	Exception
      //   3054	71	15	localObject7	Object
      //   3152	1	15	localException7	Exception
      //   3188	126	15	localObject8	Object
      //   3638	16	15	localException8	Exception
      //   3679	16	15	localException9	Exception
      //   3775	16	15	localException10	Exception
      //   3824	1	15	str3	String
      //   3864	16	15	localException11	Exception
      //   3907	319	15	localObject9	Object
      //   4230	1	15	localException12	Exception
      //   4519	1	15	localJSONException6	JSONException
      //   4524	1	15	localException13	Exception
      //   4529	1	15	localException14	Exception
      //   4534	1	15	localException15	Exception
      //   4539	1	15	localException16	Exception
      //   4544	1	15	localException17	Exception
      //   4549	1	15	localException18	Exception
      //   4554	1	15	localException19	Exception
      //   4559	1	15	localException20	Exception
      //   4564	1	15	localException21	Exception
      //   4569	1	15	localException22	Exception
      //   444	3151	16	localObject10	Object
      //   3720	110	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   3964	66	16	localObject11	Object
      //   905	2407	17	localObject12	Object
      //   3017	13	18	localIterator	Iterator
      //   3039	697	19	localApplicationInfo	ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   154	192	518	java/io/IOException
      //   192	223	518	java/io/IOException
      //   223	234	518	java/io/IOException
      //   234	404	518	java/io/IOException
      //   404	495	518	java/io/IOException
      //   495	502	518	java/io/IOException
      //   507	515	518	java/io/IOException
      //   594	604	518	java/io/IOException
      //   607	617	518	java/io/IOException
      //   620	651	518	java/io/IOException
      //   651	658	518	java/io/IOException
      //   658	679	518	java/io/IOException
      //   693	703	518	java/io/IOException
      //   714	740	518	java/io/IOException
      //   753	811	518	java/io/IOException
      //   832	862	518	java/io/IOException
      //   862	875	518	java/io/IOException
      //   878	888	518	java/io/IOException
      //   892	907	518	java/io/IOException
      //   911	919	518	java/io/IOException
      //   923	930	518	java/io/IOException
      //   934	947	518	java/io/IOException
      //   956	999	518	java/io/IOException
      //   999	1021	518	java/io/IOException
      //   1021	1070	518	java/io/IOException
      //   1070	1131	518	java/io/IOException
      //   1131	1191	518	java/io/IOException
      //   1191	1243	518	java/io/IOException
      //   1249	1278	518	java/io/IOException
      //   1278	1283	518	java/io/IOException
      //   1283	1326	518	java/io/IOException
      //   1330	1382	518	java/io/IOException
      //   1382	1428	518	java/io/IOException
      //   1433	1467	518	java/io/IOException
      //   1467	1482	518	java/io/IOException
      //   1488	1517	518	java/io/IOException
      //   1520	1547	518	java/io/IOException
      //   1547	1562	518	java/io/IOException
      //   1567	1596	518	java/io/IOException
      //   1598	1625	518	java/io/IOException
      //   1625	1640	518	java/io/IOException
      //   1645	1674	518	java/io/IOException
      //   1676	1703	518	java/io/IOException
      //   1703	1743	518	java/io/IOException
      //   1743	1756	518	java/io/IOException
      //   1758	1808	518	java/io/IOException
      //   1817	1844	518	java/io/IOException
      //   2187	2197	518	java/io/IOException
      //   2204	2214	518	java/io/IOException
      //   2219	2225	518	java/io/IOException
      //   2230	2271	518	java/io/IOException
      //   2274	2280	518	java/io/IOException
      //   2285	2291	518	java/io/IOException
      //   2294	2339	518	java/io/IOException
      //   2342	2367	518	java/io/IOException
      //   2377	2413	518	java/io/IOException
      //   2416	2454	518	java/io/IOException
      //   2464	2516	518	java/io/IOException
      //   2519	2574	518	java/io/IOException
      //   2583	2618	518	java/io/IOException
      //   2621	2655	518	java/io/IOException
      //   2667	2696	518	java/io/IOException
      //   2711	2740	518	java/io/IOException
      //   2755	2784	518	java/io/IOException
      //   2791	2801	518	java/io/IOException
      //   2804	2845	518	java/io/IOException
      //   2850	2867	518	java/io/IOException
      //   2867	2884	518	java/io/IOException
      //   2889	2908	518	java/io/IOException
      //   2908	2925	518	java/io/IOException
      //   2930	2952	518	java/io/IOException
      //   2952	2969	518	java/io/IOException
      //   2974	3019	518	java/io/IOException
      //   3019	3048	518	java/io/IOException
      //   3056	3069	518	java/io/IOException
      //   3078	3149	518	java/io/IOException
      //   3154	3171	518	java/io/IOException
      //   3176	3271	518	java/io/IOException
      //   3271	3311	518	java/io/IOException
      //   3311	3324	518	java/io/IOException
      //   3326	3367	518	java/io/IOException
      //   3374	3415	518	java/io/IOException
      //   3418	3459	518	java/io/IOException
      //   3462	3503	518	java/io/IOException
      //   3506	3547	518	java/io/IOException
      //   3550	3591	518	java/io/IOException
      //   3594	3635	518	java/io/IOException
      //   3640	3667	518	java/io/IOException
      //   3670	3676	518	java/io/IOException
      //   3681	3708	518	java/io/IOException
      //   3711	3717	518	java/io/IOException
      //   3722	3763	518	java/io/IOException
      //   3766	3772	518	java/io/IOException
      //   3777	3804	518	java/io/IOException
      //   3807	3813	518	java/io/IOException
      //   3816	3826	518	java/io/IOException
      //   3829	3861	518	java/io/IOException
      //   3866	3893	518	java/io/IOException
      //   3893	3918	518	java/io/IOException
      //   3918	3939	518	java/io/IOException
      //   3939	3986	518	java/io/IOException
      //   3986	4029	518	java/io/IOException
      //   4029	4046	518	java/io/IOException
      //   4046	4094	518	java/io/IOException
      //   4096	4132	518	java/io/IOException
      //   4137	4142	518	java/io/IOException
      //   4142	4192	518	java/io/IOException
      //   4195	4201	518	java/io/IOException
      //   4204	4214	518	java/io/IOException
      //   4217	4227	518	java/io/IOException
      //   4232	4238	518	java/io/IOException
      //   4251	4275	518	java/io/IOException
      //   15	35	562	java/lang/Exception
      //   651	658	1815	org/json/JSONException
      //   154	192	1851	java/lang/Exception
      //   192	223	1851	java/lang/Exception
      //   223	234	1851	java/lang/Exception
      //   234	404	1851	java/lang/Exception
      //   404	495	1851	java/lang/Exception
      //   495	502	1851	java/lang/Exception
      //   507	515	1851	java/lang/Exception
      //   594	604	1851	java/lang/Exception
      //   607	617	1851	java/lang/Exception
      //   620	651	1851	java/lang/Exception
      //   651	658	1851	java/lang/Exception
      //   658	679	1851	java/lang/Exception
      //   693	703	1851	java/lang/Exception
      //   714	740	1851	java/lang/Exception
      //   753	811	1851	java/lang/Exception
      //   1817	1844	1851	java/lang/Exception
      //   2230	2271	1851	java/lang/Exception
      //   4251	4275	1851	java/lang/Exception
      //   693	703	2180	org/json/JSONException
      //   714	740	2180	org/json/JSONException
      //   753	811	2180	org/json/JSONException
      //   878	888	2217	org/json/JSONException
      //   892	907	2217	org/json/JSONException
      //   911	919	2217	org/json/JSONException
      //   923	930	2217	org/json/JSONException
      //   934	947	2217	org/json/JSONException
      //   2204	2214	2217	org/json/JSONException
      //   832	862	2228	java/lang/Exception
      //   862	875	2228	java/lang/Exception
      //   878	888	2228	java/lang/Exception
      //   892	907	2228	java/lang/Exception
      //   911	919	2228	java/lang/Exception
      //   923	930	2228	java/lang/Exception
      //   934	947	2228	java/lang/Exception
      //   956	999	2228	java/lang/Exception
      //   999	1021	2228	java/lang/Exception
      //   2187	2197	2228	java/lang/Exception
      //   2204	2214	2228	java/lang/Exception
      //   2219	2225	2228	java/lang/Exception
      //   2274	2280	2228	java/lang/Exception
      //   2285	2291	2228	java/lang/Exception
      //   2850	2867	2228	java/lang/Exception
      //   2867	2884	2228	java/lang/Exception
      //   2908	2925	2228	java/lang/Exception
      //   2952	2969	2228	java/lang/Exception
      //   3154	3171	2228	java/lang/Exception
      //   3640	3667	2228	java/lang/Exception
      //   3670	3676	2228	java/lang/Exception
      //   3681	3708	2228	java/lang/Exception
      //   3711	3717	2228	java/lang/Exception
      //   3766	3772	2228	java/lang/Exception
      //   3777	3804	2228	java/lang/Exception
      //   3807	3813	2228	java/lang/Exception
      //   3866	3893	2228	java/lang/Exception
      //   3918	3939	2228	java/lang/Exception
      //   4195	4201	2228	java/lang/Exception
      //   4232	4238	2228	java/lang/Exception
      //   956	999	2272	org/json/JSONException
      //   999	1021	2283	org/json/JSONException
      //   1703	1743	2848	java/lang/Exception
      //   1743	1756	2848	java/lang/Exception
      //   1758	1808	2848	java/lang/Exception
      //   2791	2801	2848	java/lang/Exception
      //   2804	2845	2848	java/lang/Exception
      //   3374	3415	2848	java/lang/Exception
      //   3418	3459	2848	java/lang/Exception
      //   3462	3503	2848	java/lang/Exception
      //   3506	3547	2848	java/lang/Exception
      //   3550	3591	2848	java/lang/Exception
      //   3594	3635	2848	java/lang/Exception
      //   2974	3019	3152	java/lang/Exception
      //   3019	3048	3152	java/lang/Exception
      //   3056	3069	3152	java/lang/Exception
      //   3078	3149	3152	java/lang/Exception
      //   3722	3763	3152	java/lang/Exception
      //   2889	2908	3638	java/lang/Exception
      //   2930	2952	3679	java/lang/Exception
      //   3056	3069	3720	android/content/pm/PackageManager$NameNotFoundException
      //   3176	3271	3775	java/lang/Exception
      //   3271	3311	3864	java/lang/Exception
      //   3311	3324	3864	java/lang/Exception
      //   3326	3367	3864	java/lang/Exception
      //   3816	3826	3864	java/lang/Exception
      //   3829	3861	3864	java/lang/Exception
      //   4142	4192	4193	java/lang/Exception
      //   3939	3986	4230	java/lang/Exception
      //   3986	4029	4230	java/lang/Exception
      //   4029	4046	4230	java/lang/Exception
      //   4204	4214	4230	java/lang/Exception
      //   4217	4227	4230	java/lang/Exception
      //   4363	4369	4376	java/lang/InterruptedException
      //   4416	4422	4429	java/lang/InterruptedException
      //   4463	4469	4476	java/lang/InterruptedException
      //   4046	4094	4514	java/lang/Exception
      //   4096	4132	4514	java/lang/Exception
      //   4137	4142	4514	java/lang/Exception
      //   3939	3986	4519	org/json/JSONException
      //   3986	4029	4519	org/json/JSONException
      //   4029	4046	4519	org/json/JSONException
      //   4204	4214	4519	org/json/JSONException
      //   4217	4227	4519	org/json/JSONException
      //   3893	3918	4524	java/lang/Exception
      //   1625	1640	4529	java/lang/Exception
      //   1645	1674	4529	java/lang/Exception
      //   1676	1703	4529	java/lang/Exception
      //   2755	2784	4529	java/lang/Exception
      //   1547	1562	4534	java/lang/Exception
      //   1567	1596	4534	java/lang/Exception
      //   1598	1625	4534	java/lang/Exception
      //   2711	2740	4534	java/lang/Exception
      //   1467	1482	4539	java/lang/Exception
      //   1488	1517	4539	java/lang/Exception
      //   1520	1547	4539	java/lang/Exception
      //   2667	2696	4539	java/lang/Exception
      //   1070	1131	4544	java/lang/Exception
      //   1021	1070	4549	java/lang/Exception
      //   1131	1191	4554	java/lang/Exception
      //   2294	2339	4554	java/lang/Exception
      //   2342	2367	4554	java/lang/Exception
      //   1191	1243	4559	java/lang/Exception
      //   1249	1278	4559	java/lang/Exception
      //   1278	1283	4559	java/lang/Exception
      //   2377	2413	4559	java/lang/Exception
      //   2416	2454	4559	java/lang/Exception
      //   1283	1326	4564	java/lang/Exception
      //   1330	1382	4564	java/lang/Exception
      //   2464	2516	4564	java/lang/Exception
      //   2519	2574	4564	java/lang/Exception
      //   1382	1428	4569	java/lang/Exception
      //   1433	1467	4569	java/lang/Exception
      //   2583	2618	4569	java/lang/Exception
      //   2621	2655	4569	java/lang/Exception
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
        //   114: invokestatic 78	com/newrelic/agent/android/instrumentation/HttpInstrumentation:openConnection	(Ljava/net/URLConnection;)Ljava/net/URLConnection;
        //   117: checkcast 80	javax/net/ssl/HttpsURLConnection
        //   120: astore 8
        //   122: iload_2
        //   123: istore_1
        //   124: aload 8
        //   126: ldc 82
        //   128: invokestatic 86	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
        //   131: ldc 88
        //   133: ldc 90
        //   135: invokeinterface 96 3 0
        //   140: invokevirtual 100	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   143: iload_2
        //   144: istore_1
        //   145: aload 8
        //   147: ldc 102
        //   149: ldc 104
        //   151: invokevirtual 100	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   154: iload_2
        //   155: istore_1
        //   156: aload 8
        //   158: ldc 106
        //   160: invokevirtual 109	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   163: iload_2
        //   164: istore_1
        //   165: aload 8
        //   167: sipush 30000
        //   170: invokevirtual 113	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   173: iload_2
        //   174: istore_1
        //   175: aload 8
        //   177: sipush 30000
        //   180: invokevirtual 116	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   183: iload_2
        //   184: istore_1
        //   185: aload 8
        //   187: iconst_1
        //   188: invokevirtual 120	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   191: iload_2
        //   192: istore_1
        //   193: aload 8
        //   195: iconst_1
        //   196: invokevirtual 123	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   199: iload_2
        //   200: istore_1
        //   201: aload 8
        //   203: invokevirtual 126	javax/net/ssl/HttpsURLConnection:connect	()V
        //   206: iload_2
        //   207: istore_1
        //   208: new 128	org/json/JSONObject
        //   211: dup
        //   212: invokespecial 129	org/json/JSONObject:<init>	()V
        //   215: astore 7
        //   217: iload_2
        //   218: istore_1
        //   219: aload 7
        //   221: ldc -125
        //   223: ldc -123
        //   225: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   228: pop
        //   229: iload_2
        //   230: istore_1
        //   231: aload 7
        //   233: ldc -117
        //   235: invokestatic 142	com/kochava/android/tracker/Feature:access$800	()Ljava/lang/String;
        //   238: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   241: pop
        //   242: iload_2
        //   243: istore_1
        //   244: aload 7
        //   246: ldc -112
        //   248: invokestatic 147	com/kochava/android/tracker/Feature:access$3400	()Ljava/lang/String;
        //   251: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   254: pop
        //   255: iload_2
        //   256: istore_1
        //   257: aload 7
        //   259: ldc -107
        //   261: new 51	java/lang/StringBuilder
        //   264: dup
        //   265: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   268: ldc -105
        //   270: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: getstatic 155	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
        //   276: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   282: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   285: pop
        //   286: iload_2
        //   287: istore_1
        //   288: aload 7
        //   290: ldc -99
        //   292: ldc -97
        //   294: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   297: pop
        //   298: iload_2
        //   299: istore_1
        //   300: aload 7
        //   302: instanceof 128
        //   305: ifne +229 -> 534
        //   308: iload_2
        //   309: istore_1
        //   310: aload 7
        //   312: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
        //   315: astore 7
        //   317: iload_2
        //   318: istore_1
        //   319: new 51	java/lang/StringBuilder
        //   322: dup
        //   323: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   326: ldc -94
        //   328: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: aload 7
        //   333: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   339: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   342: iload_2
        //   343: istore_1
        //   344: ldc -92
        //   346: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   349: iload_2
        //   350: istore_1
        //   351: new 166	java/io/OutputStreamWriter
        //   354: dup
        //   355: aload 8
        //   357: invokevirtual 170	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   360: invokespecial 173	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   363: astore 9
        //   365: iload_2
        //   366: istore_1
        //   367: aload 9
        //   369: aload 7
        //   371: invokevirtual 176	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   374: iload_2
        //   375: istore_1
        //   376: aload 9
        //   378: invokevirtual 179	java/io/OutputStreamWriter:close	()V
        //   381: iload_2
        //   382: istore 4
        //   384: iload_2
        //   385: istore 6
        //   387: iload_2
        //   388: istore_1
        //   389: ldc -75
        //   391: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   394: iload_2
        //   395: istore 4
        //   397: iload_2
        //   398: istore 6
        //   400: iload_2
        //   401: istore_1
        //   402: new 183	java/lang/StringBuffer
        //   405: dup
        //   406: ldc 90
        //   408: invokespecial 184	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   411: astore 7
        //   413: iload_2
        //   414: istore 4
        //   416: iload_2
        //   417: istore 6
        //   419: iload_2
        //   420: istore_1
        //   421: new 186	java/io/BufferedReader
        //   424: dup
        //   425: new 188	java/io/InputStreamReader
        //   428: dup
        //   429: aload 8
        //   431: invokevirtual 192	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
        //   434: invokespecial 195	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   437: invokespecial 198	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   440: astore 8
        //   442: iload_2
        //   443: istore 4
        //   445: iload_2
        //   446: istore 6
        //   448: iload_2
        //   449: istore_1
        //   450: aload 8
        //   452: invokevirtual 201	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   455: astore 9
        //   457: aload 9
        //   459: ifnull +90 -> 549
        //   462: iload_2
        //   463: istore 4
        //   465: iload_2
        //   466: istore 6
        //   468: iload_2
        //   469: istore_1
        //   470: aload 7
        //   472: aload 9
        //   474: invokevirtual 204	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   477: pop
        //   478: goto -36 -> 442
        //   481: astore 7
        //   483: iload 4
        //   485: istore_1
        //   486: aload 7
        //   488: invokevirtual 208	java/lang/Object:getClass	()Ljava/lang/Class;
        //   491: ldc -46
        //   493: invokevirtual 214	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   496: ifeq +784 -> 1280
        //   499: iload 4
        //   501: istore_1
        //   502: new 51	java/lang/StringBuilder
        //   505: dup
        //   506: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   509: ldc -40
        //   511: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: aload 7
        //   516: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   519: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   522: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   525: iload 4
        //   527: istore_1
        //   528: aload 7
        //   530: invokestatic 226	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
        //   533: return
        //   534: iload_2
        //   535: istore_1
        //   536: aload 7
        //   538: checkcast 128	org/json/JSONObject
        //   541: invokestatic 231	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   544: astore 7
        //   546: goto -229 -> 317
        //   549: iload_2
        //   550: istore 4
        //   552: iload_2
        //   553: istore 6
        //   555: iload_2
        //   556: istore_1
        //   557: aload 7
        //   559: invokevirtual 232	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   562: astore 7
        //   564: iload_2
        //   565: istore 4
        //   567: iload_2
        //   568: istore 6
        //   570: iload_2
        //   571: istore_1
        //   572: new 51	java/lang/StringBuilder
        //   575: dup
        //   576: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   579: ldc -22
        //   581: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   584: aload 7
        //   586: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   589: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   592: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   595: aconst_null
        //   596: astore 8
        //   598: iload_2
        //   599: istore 4
        //   601: iload_2
        //   602: istore 6
        //   604: iload_2
        //   605: istore_1
        //   606: aload 7
        //   608: invokestatic 238	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   611: astore 7
        //   613: aload 7
        //   615: astore 8
        //   617: iload_2
        //   618: istore 5
        //   620: aload 8
        //   622: ifnull +395 -> 1017
        //   625: iload_2
        //   626: istore 4
        //   628: iload_2
        //   629: istore 6
        //   631: iload_2
        //   632: istore_1
        //   633: new 51	java/lang/StringBuilder
        //   636: dup
        //   637: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   640: ldc -16
        //   642: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   645: astore 9
        //   647: iload_2
        //   648: istore 4
        //   650: iload_2
        //   651: istore 6
        //   653: iload_2
        //   654: istore_1
        //   655: aload 8
        //   657: instanceof 128
        //   660: ifne +482 -> 1142
        //   663: iload_2
        //   664: istore 4
        //   666: iload_2
        //   667: istore 6
        //   669: iload_2
        //   670: istore_1
        //   671: aload 8
        //   673: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
        //   676: astore 7
        //   678: iload_2
        //   679: istore 4
        //   681: iload_2
        //   682: istore 6
        //   684: iload_2
        //   685: istore_1
        //   686: aload 9
        //   688: aload 7
        //   690: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   693: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   696: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   699: aconst_null
        //   700: astore 7
        //   702: iload_2
        //   703: istore 4
        //   705: iload_2
        //   706: istore 6
        //   708: iload_2
        //   709: istore_1
        //   710: aload 8
        //   712: ldc -14
        //   714: invokevirtual 245	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   717: astore 8
        //   719: iload_2
        //   720: istore 4
        //   722: iload_2
        //   723: istore 6
        //   725: aload 8
        //   727: astore 7
        //   729: iload_2
        //   730: istore_1
        //   731: new 51	java/lang/StringBuilder
        //   734: dup
        //   735: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   738: ldc -9
        //   740: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   743: astore 10
        //   745: iload_2
        //   746: istore 4
        //   748: iload_2
        //   749: istore 6
        //   751: aload 8
        //   753: astore 7
        //   755: iload_2
        //   756: istore_1
        //   757: aload 8
        //   759: instanceof 128
        //   762: ifne +401 -> 1163
        //   765: iload_2
        //   766: istore 4
        //   768: iload_2
        //   769: istore 6
        //   771: aload 8
        //   773: astore 7
        //   775: iload_2
        //   776: istore_1
        //   777: aload 8
        //   779: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
        //   782: astore 9
        //   784: iload_2
        //   785: istore 4
        //   787: iload_2
        //   788: istore 6
        //   790: aload 8
        //   792: astore 7
        //   794: iload_2
        //   795: istore_1
        //   796: aload 10
        //   798: aload 9
        //   800: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   803: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   806: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   809: aload 8
        //   811: astore 7
        //   813: iload_2
        //   814: istore 5
        //   816: aload 7
        //   818: ifnull +199 -> 1017
        //   821: ldc 90
        //   823: astore 8
        //   825: iload_2
        //   826: istore 4
        //   828: iload_2
        //   829: istore 6
        //   831: iload_2
        //   832: istore_1
        //   833: aload 7
        //   835: ldc -7
        //   837: invokevirtual 251	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   840: astore 9
        //   842: aload 9
        //   844: astore 8
        //   846: iload_2
        //   847: istore 4
        //   849: iload_2
        //   850: istore 6
        //   852: iload_2
        //   853: istore_1
        //   854: aload 7
        //   856: ldc -3
        //   858: invokevirtual 257	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   861: istore_3
        //   862: iload_3
        //   863: istore 5
        //   865: iload_3
        //   866: ifge +151 -> 1017
        //   869: iload_3
        //   870: istore 4
        //   872: iload_3
        //   873: istore 6
        //   875: iload_3
        //   876: istore_1
        //   877: iload_3
        //   878: istore_2
        //   879: invokestatic 260	com/kochava/android/tracker/Feature:access$3500	()Landroid/content/SharedPreferences;
        //   882: invokeinterface 264 1 0
        //   887: ldc_w 266
        //   890: aload 8
        //   892: invokeinterface 272 3 0
        //   897: invokeinterface 275 1 0
        //   902: iload_3
        //   903: istore 4
        //   905: iload_3
        //   906: istore 5
        //   908: iload_3
        //   909: istore 6
        //   911: iload_3
        //   912: istore_1
        //   913: iload_3
        //   914: istore_2
        //   915: invokestatic 279	com/kochava/android/tracker/Feature:access$3600	()Landroid/os/Handler;
        //   918: ifnull +99 -> 1017
        //   921: iload_3
        //   922: istore 4
        //   924: iload_3
        //   925: istore 6
        //   927: iload_3
        //   928: istore_1
        //   929: iload_3
        //   930: istore_2
        //   931: invokestatic 285	android/os/Message:obtain	()Landroid/os/Message;
        //   934: astore 7
        //   936: iload_3
        //   937: istore 4
        //   939: iload_3
        //   940: istore 6
        //   942: iload_3
        //   943: istore_1
        //   944: iload_3
        //   945: istore_2
        //   946: new 287	android/os/Bundle
        //   949: dup
        //   950: invokespecial 288	android/os/Bundle:<init>	()V
        //   953: astore 9
        //   955: iload_3
        //   956: istore 4
        //   958: iload_3
        //   959: istore 6
        //   961: iload_3
        //   962: istore_1
        //   963: iload_3
        //   964: istore_2
        //   965: aload 9
        //   967: ldc_w 266
        //   970: aload 8
        //   972: invokevirtual 289	java/lang/String:toString	()Ljava/lang/String;
        //   975: invokevirtual 291	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   978: iload_3
        //   979: istore 4
        //   981: iload_3
        //   982: istore 6
        //   984: iload_3
        //   985: istore_1
        //   986: iload_3
        //   987: istore_2
        //   988: aload 7
        //   990: aload 9
        //   992: invokevirtual 295	android/os/Message:setData	(Landroid/os/Bundle;)V
        //   995: iload_3
        //   996: istore 4
        //   998: iload_3
        //   999: istore 6
        //   1001: iload_3
        //   1002: istore_1
        //   1003: iload_3
        //   1004: istore_2
        //   1005: invokestatic 279	com/kochava/android/tracker/Feature:access$3600	()Landroid/os/Handler;
        //   1008: aload 7
        //   1010: invokevirtual 301	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1013: pop
        //   1014: iload_3
        //   1015: istore 5
        //   1017: iload 5
        //   1019: ifle +313 -> 1332
        //   1022: iload 5
        //   1024: invokestatic 304	com/kochava/android/tracker/Feature:sendKVQuery	(I)V
        //   1027: return
        //   1028: astore 7
        //   1030: iload_2
        //   1031: istore 4
        //   1033: iload_2
        //   1034: istore 6
        //   1036: iload_2
        //   1037: istore_1
        //   1038: new 51	java/lang/StringBuilder
        //   1041: dup
        //   1042: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1045: ldc_w 306
        //   1048: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1051: aload 7
        //   1053: invokevirtual 307	org/json/JSONException:toString	()Ljava/lang/String;
        //   1056: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1059: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1062: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1065: goto -448 -> 617
        //   1068: astore 7
        //   1070: iload 6
        //   1072: istore_1
        //   1073: new 51	java/lang/StringBuilder
        //   1076: dup
        //   1077: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1080: ldc_w 309
        //   1083: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1086: aload 7
        //   1088: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1091: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1094: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1097: return
        //   1098: astore 7
        //   1100: aload 7
        //   1102: invokevirtual 208	java/lang/Object:getClass	()Ljava/lang/Class;
        //   1105: ldc -46
        //   1107: invokevirtual 214	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   1110: ifeq +198 -> 1308
        //   1113: new 51	java/lang/StringBuilder
        //   1116: dup
        //   1117: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1120: ldc -40
        //   1122: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1125: aload 7
        //   1127: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1130: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1133: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1136: aload 7
        //   1138: invokestatic 226	com/kochava/android/tracker/Feature:access$3000	(Ljava/lang/Exception;)V
        //   1141: return
        //   1142: iload_2
        //   1143: istore 4
        //   1145: iload_2
        //   1146: istore 6
        //   1148: iload_2
        //   1149: istore_1
        //   1150: aload 8
        //   1152: checkcast 128	org/json/JSONObject
        //   1155: invokestatic 231	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   1158: astore 7
        //   1160: goto -482 -> 678
        //   1163: iload_2
        //   1164: istore 4
        //   1166: iload_2
        //   1167: istore 6
        //   1169: aload 8
        //   1171: astore 7
        //   1173: iload_2
        //   1174: istore_1
        //   1175: aload 8
        //   1177: checkcast 128	org/json/JSONObject
        //   1180: invokestatic 231	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   1183: astore 9
        //   1185: goto -401 -> 784
        //   1188: astore 8
        //   1190: iload_2
        //   1191: istore 4
        //   1193: iload_2
        //   1194: istore 6
        //   1196: iload_2
        //   1197: istore_1
        //   1198: ldc_w 311
        //   1201: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   1204: goto -391 -> 813
        //   1207: astore 7
        //   1209: new 51	java/lang/StringBuilder
        //   1212: dup
        //   1213: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1216: ldc_w 313
        //   1219: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1222: aload 7
        //   1224: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1227: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1230: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1233: iload_1
        //   1234: istore 5
        //   1236: goto -219 -> 1017
        //   1239: astore 9
        //   1241: iload_2
        //   1242: istore 4
        //   1244: iload_2
        //   1245: istore 6
        //   1247: iload_2
        //   1248: istore_1
        //   1249: ldc_w 315
        //   1252: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1255: goto -409 -> 846
        //   1258: astore 7
        //   1260: iload_2
        //   1261: istore 4
        //   1263: iload_2
        //   1264: istore 6
        //   1266: iload_2
        //   1267: istore_1
        //   1268: ldc_w 317
        //   1271: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1274: iload_2
        //   1275: istore 5
        //   1277: goto -260 -> 1017
        //   1280: iload 4
        //   1282: istore_1
        //   1283: new 51	java/lang/StringBuilder
        //   1286: dup
        //   1287: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1290: ldc_w 319
        //   1293: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1296: aload 7
        //   1298: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1301: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1304: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1307: return
        //   1308: new 51	java/lang/StringBuilder
        //   1311: dup
        //   1312: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1315: ldc_w 319
        //   1318: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1321: aload 7
        //   1323: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1326: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1329: invokestatic 222	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1332: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1333	0	this	7
        //   4	1279	1	i	int
        //   2	1273	2	j	int
        //   861	154	3	k	int
        //   382	899	4	m	int
        //   618	658	5	n	int
        //   385	880	6	i1	int
        //   215	256	7	localObject1	Object
        //   481	56	7	localIOException1	IOException
        //   544	465	7	localObject2	Object
        //   1028	24	7	localJSONException1	JSONException
        //   1068	19	7	localOutOfMemoryError	OutOfMemoryError
        //   1098	39	7	localIOException2	IOException
        //   1158	14	7	localObject3	Object
        //   1207	16	7	localException	Exception
        //   1258	64	7	localJSONException2	JSONException
        //   120	1056	8	localObject4	Object
        //   1188	1	8	localJSONException3	JSONException
        //   363	821	9	localObject5	Object
        //   1239	1	9	localJSONException4	JSONException
        //   743	54	10	localStringBuilder	StringBuilder
        // Exception table:
        //   from	to	target	type
        //   389	394	481	java/io/IOException
        //   402	413	481	java/io/IOException
        //   421	442	481	java/io/IOException
        //   450	457	481	java/io/IOException
        //   470	478	481	java/io/IOException
        //   557	564	481	java/io/IOException
        //   572	595	481	java/io/IOException
        //   606	613	481	java/io/IOException
        //   633	647	481	java/io/IOException
        //   655	663	481	java/io/IOException
        //   671	678	481	java/io/IOException
        //   686	699	481	java/io/IOException
        //   710	719	481	java/io/IOException
        //   731	745	481	java/io/IOException
        //   757	765	481	java/io/IOException
        //   777	784	481	java/io/IOException
        //   796	809	481	java/io/IOException
        //   833	842	481	java/io/IOException
        //   854	862	481	java/io/IOException
        //   879	902	481	java/io/IOException
        //   915	921	481	java/io/IOException
        //   931	936	481	java/io/IOException
        //   946	955	481	java/io/IOException
        //   965	978	481	java/io/IOException
        //   988	995	481	java/io/IOException
        //   1005	1014	481	java/io/IOException
        //   1038	1065	481	java/io/IOException
        //   1150	1160	481	java/io/IOException
        //   1175	1185	481	java/io/IOException
        //   1198	1204	481	java/io/IOException
        //   1249	1255	481	java/io/IOException
        //   1268	1274	481	java/io/IOException
        //   606	613	1028	org/json/JSONException
        //   389	394	1068	java/lang/OutOfMemoryError
        //   402	413	1068	java/lang/OutOfMemoryError
        //   421	442	1068	java/lang/OutOfMemoryError
        //   450	457	1068	java/lang/OutOfMemoryError
        //   470	478	1068	java/lang/OutOfMemoryError
        //   557	564	1068	java/lang/OutOfMemoryError
        //   572	595	1068	java/lang/OutOfMemoryError
        //   606	613	1068	java/lang/OutOfMemoryError
        //   633	647	1068	java/lang/OutOfMemoryError
        //   655	663	1068	java/lang/OutOfMemoryError
        //   671	678	1068	java/lang/OutOfMemoryError
        //   686	699	1068	java/lang/OutOfMemoryError
        //   710	719	1068	java/lang/OutOfMemoryError
        //   731	745	1068	java/lang/OutOfMemoryError
        //   757	765	1068	java/lang/OutOfMemoryError
        //   777	784	1068	java/lang/OutOfMemoryError
        //   796	809	1068	java/lang/OutOfMemoryError
        //   833	842	1068	java/lang/OutOfMemoryError
        //   854	862	1068	java/lang/OutOfMemoryError
        //   879	902	1068	java/lang/OutOfMemoryError
        //   915	921	1068	java/lang/OutOfMemoryError
        //   931	936	1068	java/lang/OutOfMemoryError
        //   946	955	1068	java/lang/OutOfMemoryError
        //   965	978	1068	java/lang/OutOfMemoryError
        //   988	995	1068	java/lang/OutOfMemoryError
        //   1005	1014	1068	java/lang/OutOfMemoryError
        //   1038	1065	1068	java/lang/OutOfMemoryError
        //   1150	1160	1068	java/lang/OutOfMemoryError
        //   1175	1185	1068	java/lang/OutOfMemoryError
        //   1198	1204	1068	java/lang/OutOfMemoryError
        //   1249	1255	1068	java/lang/OutOfMemoryError
        //   1268	1274	1068	java/lang/OutOfMemoryError
        //   5	11	1098	java/io/IOException
        //   13	25	1098	java/io/IOException
        //   27	32	1098	java/io/IOException
        //   34	40	1098	java/io/IOException
        //   42	76	1098	java/io/IOException
        //   78	122	1098	java/io/IOException
        //   124	143	1098	java/io/IOException
        //   145	154	1098	java/io/IOException
        //   156	163	1098	java/io/IOException
        //   165	173	1098	java/io/IOException
        //   175	183	1098	java/io/IOException
        //   185	191	1098	java/io/IOException
        //   193	199	1098	java/io/IOException
        //   201	206	1098	java/io/IOException
        //   208	217	1098	java/io/IOException
        //   219	229	1098	java/io/IOException
        //   231	242	1098	java/io/IOException
        //   244	255	1098	java/io/IOException
        //   257	286	1098	java/io/IOException
        //   288	298	1098	java/io/IOException
        //   300	308	1098	java/io/IOException
        //   310	317	1098	java/io/IOException
        //   319	342	1098	java/io/IOException
        //   344	349	1098	java/io/IOException
        //   351	365	1098	java/io/IOException
        //   367	374	1098	java/io/IOException
        //   376	381	1098	java/io/IOException
        //   486	499	1098	java/io/IOException
        //   502	525	1098	java/io/IOException
        //   528	533	1098	java/io/IOException
        //   536	546	1098	java/io/IOException
        //   1073	1097	1098	java/io/IOException
        //   1283	1307	1098	java/io/IOException
        //   710	719	1188	org/json/JSONException
        //   731	745	1188	org/json/JSONException
        //   757	765	1188	org/json/JSONException
        //   777	784	1188	org/json/JSONException
        //   796	809	1188	org/json/JSONException
        //   1175	1185	1188	org/json/JSONException
        //   5	11	1207	java/lang/Exception
        //   13	25	1207	java/lang/Exception
        //   27	32	1207	java/lang/Exception
        //   34	40	1207	java/lang/Exception
        //   42	76	1207	java/lang/Exception
        //   78	122	1207	java/lang/Exception
        //   124	143	1207	java/lang/Exception
        //   145	154	1207	java/lang/Exception
        //   156	163	1207	java/lang/Exception
        //   165	173	1207	java/lang/Exception
        //   175	183	1207	java/lang/Exception
        //   185	191	1207	java/lang/Exception
        //   193	199	1207	java/lang/Exception
        //   201	206	1207	java/lang/Exception
        //   208	217	1207	java/lang/Exception
        //   219	229	1207	java/lang/Exception
        //   231	242	1207	java/lang/Exception
        //   244	255	1207	java/lang/Exception
        //   257	286	1207	java/lang/Exception
        //   288	298	1207	java/lang/Exception
        //   300	308	1207	java/lang/Exception
        //   310	317	1207	java/lang/Exception
        //   319	342	1207	java/lang/Exception
        //   344	349	1207	java/lang/Exception
        //   351	365	1207	java/lang/Exception
        //   367	374	1207	java/lang/Exception
        //   376	381	1207	java/lang/Exception
        //   389	394	1207	java/lang/Exception
        //   402	413	1207	java/lang/Exception
        //   421	442	1207	java/lang/Exception
        //   450	457	1207	java/lang/Exception
        //   470	478	1207	java/lang/Exception
        //   486	499	1207	java/lang/Exception
        //   502	525	1207	java/lang/Exception
        //   528	533	1207	java/lang/Exception
        //   536	546	1207	java/lang/Exception
        //   557	564	1207	java/lang/Exception
        //   572	595	1207	java/lang/Exception
        //   606	613	1207	java/lang/Exception
        //   633	647	1207	java/lang/Exception
        //   655	663	1207	java/lang/Exception
        //   671	678	1207	java/lang/Exception
        //   686	699	1207	java/lang/Exception
        //   710	719	1207	java/lang/Exception
        //   731	745	1207	java/lang/Exception
        //   757	765	1207	java/lang/Exception
        //   777	784	1207	java/lang/Exception
        //   796	809	1207	java/lang/Exception
        //   833	842	1207	java/lang/Exception
        //   854	862	1207	java/lang/Exception
        //   879	902	1207	java/lang/Exception
        //   915	921	1207	java/lang/Exception
        //   931	936	1207	java/lang/Exception
        //   946	955	1207	java/lang/Exception
        //   965	978	1207	java/lang/Exception
        //   988	995	1207	java/lang/Exception
        //   1005	1014	1207	java/lang/Exception
        //   1038	1065	1207	java/lang/Exception
        //   1073	1097	1207	java/lang/Exception
        //   1150	1160	1207	java/lang/Exception
        //   1175	1185	1207	java/lang/Exception
        //   1198	1204	1207	java/lang/Exception
        //   1249	1255	1207	java/lang/Exception
        //   1268	1274	1207	java/lang/Exception
        //   1283	1307	1207	java/lang/Exception
        //   833	842	1239	org/json/JSONException
        //   854	862	1258	org/json/JSONException
        //   879	902	1258	org/json/JSONException
        //   915	921	1258	org/json/JSONException
        //   931	936	1258	org/json/JSONException
        //   946	955	1258	org/json/JSONException
        //   965	978	1258	org/json/JSONException
        //   988	995	1258	org/json/JSONException
        //   1005	1014	1258	org/json/JSONException
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
        for (;;)
        {
          try
          {
            Feature.access$2902(true);
            Object localObject2 = this.val$e.getMessage();
            Object localObject1 = new JSONObject();
            ((JSONObject)localObject1).put("message", localObject2);
            ((JSONObject)localObject1).put("os_version", Feature.access$4000());
            ((JSONObject)localObject1).put("device", Feature.access$5900() + "-" + Feature.access$6000());
            Object localObject3 = new JSONObject();
            ((JSONObject)localObject3).put("kochava_device_id", Feature.access$3400());
            ((JSONObject)localObject3).put("action", "error");
            ((JSONObject)localObject3).put("data", localObject1);
            ((JSONObject)localObject3).put("kochava_app_id", Feature.mAppId);
            ((JSONObject)localObject3).put("sdk_version", "Android20160615" + Feature.versionExtension);
            ((JSONObject)localObject3).put("sdk_protocol", "4");
            Logging.Log("https log - posting to " + "http://" + Feature.hostControl + "/track/kvTracker.php");
            localObject2 = new URL("http://" + Feature.hostControl + "/track/kvTracker.php");
            if (!(localObject3 instanceof JSONObject))
            {
              localObject1 = ((JSONObject)localObject3).toString();
              Logging.Log("https failure data:" + (String)localObject1);
              localObject2 = (HttpURLConnection)HttpInstrumentation.openConnection(((URL)localObject2).openConnection());
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
    //   1: ifnull +2105 -> 2106
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
    //   369: ifne +22 -> 391
    //   372: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   375: ldc -126
    //   377: ldc_w 353
    //   380: invokeinterface 755 3 0
    //   385: invokestatic 1375	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   388: putstatic 443	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   391: getstatic 836	android/os/Build$VERSION:SDK_INT	I
    //   394: bipush 14
    //   396: if_icmplt +1751 -> 2147
    //   399: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   402: ifne +1745 -> 2147
    //   405: new 714	java/lang/StringBuilder
    //   408: dup
    //   409: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   412: ldc_w 1377
    //   415: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   421: invokevirtual 828	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   424: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   430: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   433: checkcast 1379	android/app/Application
    //   436: new 69	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   439: dup
    //   440: aload_0
    //   441: invokespecial 1380	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   444: invokevirtual 1384	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   447: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   450: new 72	com/kochava/android/tracker/Feature$MemoryBoss
    //   453: dup
    //   454: aload_0
    //   455: invokespecial 1385	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   458: invokevirtual 1389	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   461: iconst_1
    //   462: putstatic 1392	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   465: iconst_1
    //   466: putstatic 1395	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   469: new 44	com/kochava/android/tracker/Feature$3
    //   472: dup
    //   473: aload_0
    //   474: invokespecial 1396	com/kochava/android/tracker/Feature$3:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   477: invokevirtual 866	java/lang/Thread:start	()V
    //   480: getstatic 380	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   483: ifne +14 -> 497
    //   486: new 46	com/kochava/android/tracker/Feature$4
    //   489: dup
    //   490: aload_0
    //   491: invokespecial 1397	com/kochava/android/tracker/Feature$4:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   494: invokevirtual 866	java/lang/Thread:start	()V
    //   497: aload_0
    //   498: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   501: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   504: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   507: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   510: iconst_0
    //   511: invokevirtual 1410	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   514: getfield 1415	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   517: putfield 1136	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   520: aload_0
    //   521: new 734	org/json/JSONObject
    //   524: dup
    //   525: invokespecial 882	org/json/JSONObject:<init>	()V
    //   528: putfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   531: aload_0
    //   532: new 734	org/json/JSONObject
    //   535: dup
    //   536: invokespecial 882	org/json/JSONObject:<init>	()V
    //   539: putfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   542: aload_0
    //   543: new 734	org/json/JSONObject
    //   546: dup
    //   547: invokespecial 882	org/json/JSONObject:<init>	()V
    //   550: putfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   553: aconst_null
    //   554: astore 13
    //   556: aconst_null
    //   557: astore 6
    //   559: aconst_null
    //   560: astore 9
    //   562: aconst_null
    //   563: astore 7
    //   565: ldc_w 893
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
    //   593: ldc_w 1423
    //   596: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   599: ifnull +38 -> 637
    //   602: aload 6
    //   604: astore 5
    //   606: aload_3
    //   607: ldc_w 1423
    //   610: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   613: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   616: ldc_w 480
    //   619: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   622: ifeq +15 -> 637
    //   625: aload_3
    //   626: ldc_w 1423
    //   629: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   632: checkcast 480	java/lang/String
    //   635: astore 5
    //   637: aload 7
    //   639: astore 6
    //   641: aload_3
    //   642: ldc -91
    //   644: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   647: ifnull +36 -> 683
    //   650: aload 7
    //   652: astore 6
    //   654: aload_3
    //   655: ldc -91
    //   657: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   660: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   663: ldc_w 480
    //   666: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   669: ifeq +14 -> 683
    //   672: aload_3
    //   673: ldc -91
    //   675: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   678: checkcast 480	java/lang/String
    //   681: astore 6
    //   683: aload_1
    //   684: astore 7
    //   686: aload_3
    //   687: ldc -46
    //   689: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   692: ifnull +35 -> 727
    //   695: aload_1
    //   696: astore 7
    //   698: aload_3
    //   699: ldc -46
    //   701: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   704: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   707: ldc_w 480
    //   710: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   713: ifeq +14 -> 727
    //   716: aload_3
    //   717: ldc -46
    //   719: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   722: checkcast 480	java/lang/String
    //   725: astore 7
    //   727: aload 8
    //   729: astore_1
    //   730: aload_3
    //   731: ldc_w 1425
    //   734: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   737: ifnull +33 -> 770
    //   740: aload_3
    //   741: ldc_w 1425
    //   744: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   747: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   750: ldc_w 480
    //   753: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   756: ifeq +1419 -> 2175
    //   759: aload_3
    //   760: ldc_w 1425
    //   763: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   766: checkcast 480	java/lang/String
    //   769: astore_1
    //   770: aload_3
    //   771: ldc_w 1427
    //   774: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   777: ifnull +34 -> 811
    //   780: aload_3
    //   781: ldc_w 1427
    //   784: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   787: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   790: ldc_w 480
    //   793: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   796: ifeq +15 -> 811
    //   799: aload_3
    //   800: ldc_w 1427
    //   803: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   806: checkcast 480	java/lang/String
    //   809: astore 8
    //   811: aload 14
    //   813: astore 8
    //   815: aload_3
    //   816: ldc_w 1429
    //   819: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   822: ifnull +38 -> 860
    //   825: aload 14
    //   827: astore 8
    //   829: aload_3
    //   830: ldc_w 1429
    //   833: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   836: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   839: ldc_w 480
    //   842: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   845: ifeq +15 -> 860
    //   848: aload_3
    //   849: ldc_w 1429
    //   852: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   855: checkcast 480	java/lang/String
    //   858: astore 8
    //   860: aload_3
    //   861: ldc_w 971
    //   864: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   867: ifnull +39 -> 906
    //   870: aload_3
    //   871: ldc_w 971
    //   874: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   877: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   880: ldc_w 903
    //   883: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   886: ifeq +20 -> 906
    //   889: aload_0
    //   890: aload_3
    //   891: ldc_w 971
    //   894: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   897: checkcast 903	java/lang/Boolean
    //   900: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   903: putfield 454	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   906: aload_3
    //   907: ldc_w 989
    //   910: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   913: ifnull +115 -> 1028
    //   916: aload_3
    //   917: ldc_w 989
    //   920: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   923: instanceof 477
    //   926: ifeq +102 -> 1028
    //   929: aload_3
    //   930: ldc_w 989
    //   933: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   936: checkcast 477	java/util/HashMap
    //   939: putstatic 1431	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   942: new 734	org/json/JSONObject
    //   945: dup
    //   946: invokespecial 882	org/json/JSONObject:<init>	()V
    //   949: putstatic 987	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   952: getstatic 1431	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   955: invokeinterface 1041 1 0
    //   960: invokeinterface 1044 1 0
    //   965: astore 9
    //   967: aload 9
    //   969: invokeinterface 784 1 0
    //   974: ifeq +54 -> 1028
    //   977: aload 9
    //   979: invokeinterface 788 1 0
    //   984: checkcast 1046	java/util/Map$Entry
    //   987: astore 10
    //   989: getstatic 987	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   992: aload 10
    //   994: invokeinterface 1049 1 0
    //   999: checkcast 480	java/lang/String
    //   1002: aload 10
    //   1004: invokeinterface 1052 1 0
    //   1009: checkcast 480	java/lang/String
    //   1012: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1015: pop
    //   1016: aload 9
    //   1018: invokeinterface 1434 1 0
    //   1023: goto -56 -> 967
    //   1026: astore 9
    //   1028: aload_3
    //   1029: ldc_w 995
    //   1032: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1035: ifnull +36 -> 1071
    //   1038: aload_3
    //   1039: ldc_w 995
    //   1042: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1045: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1048: ldc_w 480
    //   1051: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1054: ifeq +17 -> 1071
    //   1057: aload_0
    //   1058: aload_3
    //   1059: ldc_w 995
    //   1062: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1065: checkcast 480	java/lang/String
    //   1068: putfield 991	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
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
    //   1091: ldc_w 1435
    //   1094: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1120: ldc_w 1435
    //   1123: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1126: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1129: ldc_w 1437
    //   1132: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1135: ifeq +77 -> 1212
    //   1138: aload_3
    //   1139: ldc_w 1435
    //   1142: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1145: checkcast 1437	java/lang/Integer
    //   1148: invokevirtual 1440	java/lang/Integer:intValue	()I
    //   1151: istore 4
    //   1153: iload 4
    //   1155: iconst_1
    //   1156: if_icmpge +1066 -> 2222
    //   1159: new 714	java/lang/StringBuilder
    //   1162: dup
    //   1163: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1166: ldc_w 1442
    //   1169: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1172: iload 4
    //   1174: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1177: ldc_w 1444
    //   1180: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1186: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1189: iconst_1
    //   1190: putstatic 363	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
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
    //   1219: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   1222: invokevirtual 488	java/lang/String:length	()I
    //   1225: ifeq +8 -> 1233
    //   1228: aload 11
    //   1230: putstatic 355	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1233: aload 10
    //   1235: ifnull +17 -> 1252
    //   1238: aload 10
    //   1240: ldc -1
    //   1242: invokevirtual 1447	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1245: ifeq +7 -> 1252
    //   1248: iconst_1
    //   1249: putstatic 411	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1252: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1255: ldc 116
    //   1257: iconst_0
    //   1258: invokevirtual 749	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1261: putstatic 601	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1264: aload 9
    //   1266: ifnull +1048 -> 2314
    //   1269: aload 9
    //   1271: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   1274: invokevirtual 488	java/lang/String:length	()I
    //   1277: ifeq +1037 -> 2314
    //   1280: aload 9
    //   1282: putstatic 706	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1285: aload_0
    //   1286: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1289: ldc -91
    //   1291: aload 9
    //   1293: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1296: pop
    //   1297: aload_0
    //   1298: getfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1301: ldc -91
    //   1303: aload 9
    //   1305: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1308: pop
    //   1309: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1312: ldc -49
    //   1314: ldc_w 353
    //   1317: invokeinterface 755 3 0
    //   1322: ldc_w 353
    //   1325: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1328: ifeq +25 -> 1353
    //   1331: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1334: invokeinterface 1235 1 0
    //   1339: ldc -49
    //   1341: aload 9
    //   1343: invokeinterface 1241 3 0
    //   1348: invokeinterface 1244 1 0
    //   1353: aload_0
    //   1354: aload 12
    //   1356: invokespecial 517	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1359: aload_0
    //   1360: getfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1363: ldc_w 1449
    //   1366: aload_0
    //   1367: invokespecial 962	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1370: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1373: pop
    //   1374: aload_0
    //   1375: getfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1378: ldc_w 1451
    //   1381: ldc_w 1453
    //   1384: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1387: pop
    //   1388: aload_0
    //   1389: getfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1392: ldc_w 1455
    //   1395: ldc_w 1457
    //   1398: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1401: pop
    //   1402: aload_0
    //   1403: getfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1406: ldc -46
    //   1408: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1411: ldc -46
    //   1413: ldc_w 893
    //   1416: invokeinterface 755 3 0
    //   1421: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1424: pop
    //   1425: aload_0
    //   1426: getfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1429: ldc -46
    //   1431: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1434: ldc -46
    //   1436: ldc_w 893
    //   1439: invokeinterface 755 3 0
    //   1444: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1447: pop
    //   1448: aload_0
    //   1449: getfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1452: ldc_w 1455
    //   1455: ldc_w 1457
    //   1458: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1461: pop
    //   1462: aload_0
    //   1463: getfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1466: ldc -46
    //   1468: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1471: ldc -46
    //   1473: ldc_w 893
    //   1476: invokeinterface 755 3 0
    //   1481: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1484: pop
    //   1485: aload_0
    //   1486: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1489: ldc_w 936
    //   1492: new 714	java/lang/StringBuilder
    //   1495: dup
    //   1496: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1499: ldc_w 938
    //   1502: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1505: getstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1508: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1511: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1514: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1517: pop
    //   1518: aload_0
    //   1519: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1522: ldc_w 1459
    //   1525: ldc_w 1461
    //   1528: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1531: pop
    //   1532: aload_0
    //   1533: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1536: ldc_w 1059
    //   1539: aload_0
    //   1540: getfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1543: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1546: pop
    //   1547: aload_0
    //   1548: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1551: ldc_w 1463
    //   1554: aload_0
    //   1555: getfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1558: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1561: pop
    //   1562: aload_0
    //   1563: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1566: ldc 92
    //   1568: ldc -99
    //   1570: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1573: pop
    //   1574: invokestatic 720	java/lang/System:currentTimeMillis	()J
    //   1577: ldc2_w 721
    //   1580: ldiv
    //   1581: putstatic 388	com/kochava/android/tracker/Feature:startTime	J
    //   1584: iconst_0
    //   1585: istore 4
    //   1587: ldc_w 353
    //   1590: astore_3
    //   1591: new 795	android/content/ComponentName
    //   1594: dup
    //   1595: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1598: ldc_w 1465
    //   1601: invokespecial 807	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1604: astore_1
    //   1605: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1608: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1611: aload_1
    //   1612: iconst_0
    //   1613: invokevirtual 1469	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1616: pop
    //   1617: ldc_w 1471
    //   1620: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1623: aload_3
    //   1624: astore_1
    //   1625: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1628: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1631: ldc_w 1473
    //   1634: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1637: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1640: invokevirtual 1476	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1643: ifge +27 -> 1670
    //   1646: iconst_1
    //   1647: istore 4
    //   1649: new 714	java/lang/StringBuilder
    //   1652: dup
    //   1653: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1656: aload_3
    //   1657: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1660: ldc_w 1478
    //   1663: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1666: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1669: astore_1
    //   1670: aload_1
    //   1671: astore_3
    //   1672: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1675: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1678: ldc_w 1480
    //   1681: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1684: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1687: invokevirtual 1476	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1690: ifge +27 -> 1717
    //   1693: iconst_1
    //   1694: istore 4
    //   1696: new 714	java/lang/StringBuilder
    //   1699: dup
    //   1700: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1703: aload_1
    //   1704: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1707: ldc_w 1482
    //   1710: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1713: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1716: astore_3
    //   1717: aload_3
    //   1718: astore_1
    //   1719: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1722: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1725: ldc_w 1484
    //   1728: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1731: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1734: invokevirtual 1476	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1737: ifge +27 -> 1764
    //   1740: iconst_1
    //   1741: istore 4
    //   1743: new 714	java/lang/StringBuilder
    //   1746: dup
    //   1747: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1750: aload_3
    //   1751: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1754: ldc_w 1486
    //   1757: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1760: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1763: astore_1
    //   1764: iload 4
    //   1766: ifeq +13 -> 1779
    //   1769: ldc_w 1488
    //   1772: invokestatic 1491	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1775: aload_1
    //   1776: invokestatic 1491	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1779: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1782: ldc -25
    //   1784: ldc_w 353
    //   1787: invokeinterface 755 3 0
    //   1792: ldc_w 353
    //   1795: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1798: ifeq +27 -> 1825
    //   1801: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1804: invokeinterface 1235 1 0
    //   1809: ldc -25
    //   1811: aload_0
    //   1812: invokespecial 624	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1815: invokeinterface 1241 3 0
    //   1820: invokeinterface 1244 1 0
    //   1825: aload_0
    //   1826: ldc_w 1493
    //   1829: putfield 1140	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1832: aload_0
    //   1833: ldc_w 1495
    //   1836: putfield 1144	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1839: aload_0
    //   1840: ldc_w 353
    //   1843: putfield 608	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1846: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1849: invokevirtual 1338	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1852: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1855: astore_3
    //   1856: aload_3
    //   1857: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1860: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1863: iconst_0
    //   1864: invokevirtual 1499	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1867: astore_1
    //   1868: aload_1
    //   1869: ifnull +728 -> 2597
    //   1872: aload_3
    //   1873: aload_1
    //   1874: invokevirtual 1503	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1877: astore_1
    //   1878: aload_0
    //   1879: aload_1
    //   1880: checkcast 480	java/lang/String
    //   1883: checkcast 480	java/lang/String
    //   1886: putfield 1140	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1889: new 714	java/lang/StringBuilder
    //   1892: dup
    //   1893: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1896: ldc_w 1505
    //   1899: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1902: aload_0
    //   1903: getfield 1140	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1906: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1909: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1912: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1915: aload_0
    //   1916: new 714	java/lang/StringBuilder
    //   1919: dup
    //   1920: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1923: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1926: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1929: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1932: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1935: iconst_0
    //   1936: invokevirtual 1410	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1939: getfield 1508	android/content/pm/PackageInfo:versionCode	I
    //   1942: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1945: ldc_w 353
    //   1948: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1951: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1954: putfield 1144	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1957: new 714	java/lang/StringBuilder
    //   1960: dup
    //   1961: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   1964: ldc_w 1510
    //   1967: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1970: aload_0
    //   1971: getfield 1144	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1974: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1977: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1980: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1983: aload_0
    //   1984: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1987: invokevirtual 1401	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1990: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1993: invokevirtual 1404	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1996: iconst_0
    //   1997: invokevirtual 1410	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2000: getfield 1513	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2003: putfield 608	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2006: new 714	java/lang/StringBuilder
    //   2009: dup
    //   2010: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2013: ldc_w 1515
    //   2016: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2019: aload_0
    //   2020: getfield 608	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2023: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2026: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2029: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2032: new 48	com/kochava/android/tracker/Feature$5
    //   2035: dup
    //   2036: aload_0
    //   2037: invokespecial 1516	com/kochava/android/tracker/Feature$5:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   2040: invokevirtual 866	java/lang/Thread:start	()V
    //   2043: aload_0
    //   2044: invokestatic 597	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2047: putfield 1518	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2050: aload_0
    //   2051: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2054: ldc_w 884
    //   2057: invokestatic 597	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2060: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2063: pop
    //   2064: getstatic 425	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2067: aload_0
    //   2068: getfield 461	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2071: ldc2_w 1519
    //   2074: getstatic 1526	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2077: invokeinterface 1531 5 0
    //   2082: pop
    //   2083: aload_0
    //   2084: ldc -99
    //   2086: invokevirtual 1534	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2089: return
    //   2090: astore_1
    //   2091: ldc_w 1536
    //   2094: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2097: aload_1
    //   2098: invokevirtual 1035	java/lang/Exception:printStackTrace	()V
    //   2101: iconst_1
    //   2102: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2105: return
    //   2106: ldc_w 1538
    //   2109: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2112: iconst_1
    //   2113: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2116: return
    //   2117: astore_1
    //   2118: new 714	java/lang/StringBuilder
    //   2121: dup
    //   2122: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2125: ldc_w 1540
    //   2128: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2131: aload_1
    //   2132: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2135: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2138: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2141: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2144: goto -1753 -> 391
    //   2147: new 714	java/lang/StringBuilder
    //   2150: dup
    //   2151: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2154: ldc_w 1542
    //   2157: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2160: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2163: invokevirtual 828	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2166: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2169: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2172: goto -1703 -> 469
    //   2175: aload 8
    //   2177: astore_1
    //   2178: aload_3
    //   2179: ldc_w 1425
    //   2182: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2185: invokevirtual 1354	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2188: ldc_w 903
    //   2191: invokevirtual 1075	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2194: ifeq -1424 -> 770
    //   2197: aload 8
    //   2199: astore_1
    //   2200: aload_3
    //   2201: ldc_w 1425
    //   2204: invokevirtual 901	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2207: checkcast 903	java/lang/Boolean
    //   2210: invokevirtual 906	java/lang/Boolean:booleanValue	()Z
    //   2213: ifeq -1443 -> 770
    //   2216: ldc -1
    //   2218: astore_1
    //   2219: goto -1449 -> 770
    //   2222: iload 4
    //   2224: sipush 360
    //   2227: if_icmple +42 -> 2269
    //   2230: new 714	java/lang/StringBuilder
    //   2233: dup
    //   2234: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2237: ldc_w 1442
    //   2240: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2243: iload 4
    //   2245: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2248: ldc_w 1544
    //   2251: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2254: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2257: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2260: ldc_w 1545
    //   2263: putstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   2266: goto -1077 -> 1189
    //   2269: iload 4
    //   2271: bipush 60
    //   2273: imul
    //   2274: sipush 1000
    //   2277: imul
    //   2278: putstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   2281: new 714	java/lang/StringBuilder
    //   2284: dup
    //   2285: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2288: ldc_w 1547
    //   2291: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2294: iload 4
    //   2296: invokevirtual 1100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2299: ldc_w 1549
    //   2302: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2305: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2308: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2311: goto -1122 -> 1189
    //   2314: aload 13
    //   2316: ifnull +176 -> 2492
    //   2319: aload 13
    //   2321: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   2324: invokevirtual 488	java/lang/String:length	()I
    //   2327: ifeq +165 -> 2492
    //   2330: aload_0
    //   2331: getfield 1417	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2334: ldc_w 1423
    //   2337: aload 13
    //   2339: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2342: pop
    //   2343: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2346: ldc -49
    //   2348: ldc_w 353
    //   2351: invokeinterface 755 3 0
    //   2356: ldc_w 353
    //   2359: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2362: ifeq +25 -> 2387
    //   2365: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2368: invokeinterface 1235 1 0
    //   2373: ldc -49
    //   2375: aload 13
    //   2377: invokeinterface 1241 3 0
    //   2382: invokeinterface 1244 1 0
    //   2387: aload 9
    //   2389: ifnull +76 -> 2465
    //   2392: aload 9
    //   2394: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   2397: invokevirtual 488	java/lang/String:length	()I
    //   2400: ifeq +65 -> 2465
    //   2403: aload 9
    //   2405: putstatic 706	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2408: aload_0
    //   2409: getfield 1419	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2412: ldc -91
    //   2414: aload 9
    //   2416: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2419: pop
    //   2420: aload_0
    //   2421: getfield 1421	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2424: ldc -91
    //   2426: aload 9
    //   2428: invokevirtual 737	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2431: pop
    //   2432: goto -1079 -> 1353
    //   2435: astore_1
    //   2436: new 714	java/lang/StringBuilder
    //   2439: dup
    //   2440: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2443: ldc_w 1551
    //   2446: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2449: aload_1
    //   2450: invokevirtual 1552	org/json/JSONException:toString	()Ljava/lang/String;
    //   2453: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2456: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2459: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2462: goto -888 -> 1574
    //   2465: new 714	java/lang/StringBuilder
    //   2468: dup
    //   2469: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2472: ldc_w 1554
    //   2475: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2478: aload 13
    //   2480: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2483: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2486: putstatic 706	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2489: goto -1136 -> 1353
    //   2492: ldc_w 1556
    //   2495: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2498: iconst_1
    //   2499: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2502: return
    //   2503: astore_1
    //   2504: iconst_1
    //   2505: istore 4
    //   2507: new 714	java/lang/StringBuilder
    //   2510: dup
    //   2511: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2514: ldc_w 353
    //   2517: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2520: ldc_w 1558
    //   2523: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2526: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2529: astore_3
    //   2530: goto -907 -> 1623
    //   2533: astore 5
    //   2535: aconst_null
    //   2536: astore_1
    //   2537: new 714	java/lang/StringBuilder
    //   2540: dup
    //   2541: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2544: ldc_w 1560
    //   2547: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2550: aload 5
    //   2552: invokevirtual 1561	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2555: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2558: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2561: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2564: goto -696 -> 1868
    //   2567: astore_1
    //   2568: new 714	java/lang/StringBuilder
    //   2571: dup
    //   2572: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2575: ldc_w 1560
    //   2578: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2581: aload_1
    //   2582: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2585: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2588: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2591: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2594: goto -679 -> 1915
    //   2597: ldc_w 1563
    //   2600: astore_1
    //   2601: goto -723 -> 1878
    //   2604: astore_1
    //   2605: new 714	java/lang/StringBuilder
    //   2608: dup
    //   2609: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2612: ldc_w 1565
    //   2615: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2618: aload_1
    //   2619: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2622: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2625: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2628: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2631: goto -648 -> 1983
    //   2634: astore_1
    //   2635: new 714	java/lang/StringBuilder
    //   2638: dup
    //   2639: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   2642: ldc_w 1567
    //   2645: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2648: aload_1
    //   2649: invokevirtual 1126	java/lang/Exception:toString	()Ljava/lang/String;
    //   2652: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2655: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2658: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2661: goto -629 -> 2032
    //   2664: astore_1
    //   2665: getstatic 929	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   2668: ifeq -604 -> 2064
    //   2671: aload_1
    //   2672: invokevirtual 932	org/json/JSONException:printStackTrace	()V
    //   2675: goto -611 -> 2064
    //   2678: astore_1
    //   2679: goto -2159 -> 520
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2682	0	this	Feature
    //   0	2682	1	paramContext	Context
    //   0	2682	2	paramBoolean	boolean
    //   0	2682	3	paramHashMap	HashMap<String, Object>
    //   1151	1355	4	i	int
    //   590	604	5	localObject1	Object
    //   2533	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   557	652	6	localObject2	Object
    //   563	635	7	localObject3	Object
    //   573	1625	8	localObject4	Object
    //   560	457	9	localIterator	Iterator
    //   1026	1	9	localException	Exception
    //   1073	1354	9	localObject5	Object
    //   570	669	10	localObject6	Object
    //   576	653	11	localObject7	Object
    //   582	773	12	localObject8	Object
    //   554	1925	13	localObject9	Object
    //   579	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   929	967	1026	java/lang/Exception
    //   967	1023	1026	java/lang/Exception
    //   4	11	2090	java/lang/Exception
    //   372	391	2117	java/lang/Exception
    //   1269	1353	2435	org/json/JSONException
    //   1353	1574	2435	org/json/JSONException
    //   2319	2387	2435	org/json/JSONException
    //   2392	2432	2435	org/json/JSONException
    //   2465	2489	2435	org/json/JSONException
    //   2492	2502	2435	org/json/JSONException
    //   1605	1623	2503	android/content/pm/PackageManager$NameNotFoundException
    //   1856	1868	2533	android/content/pm/PackageManager$NameNotFoundException
    //   1846	1856	2567	java/lang/Exception
    //   1856	1868	2567	java/lang/Exception
    //   1872	1878	2567	java/lang/Exception
    //   1878	1915	2567	java/lang/Exception
    //   2537	2564	2567	java/lang/Exception
    //   1915	1983	2604	java/lang/Exception
    //   1983	2032	2634	java/lang/Exception
    //   2050	2064	2664	org/json/JSONException
    //   497	520	2678	java/lang/Exception
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
          localObject1 = (HttpsURLConnection)HttpInstrumentation.openConnection(new URL("https://" + hostControl + "/track/kvTracker.php").openConnection());
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
            break label677;
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
      label677:
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
          ((JSONObject)localObject1).put("kochava_device_id", Feature.access$3400());
          ((JSONObject)localObject1).put("sdk_version", "Android20160615" + Feature.versionExtension);
          ((JSONObject)localObject1).put("sdk_protocol", "4");
          if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty()))
          {
            Logging.Log("Push token - hostControl was empty, using default");
            Feature.access$602("control.kochava.com");
          }
          Logging.Log("kvPush - posting to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject2 = new URL("https://" + Feature.hostControl + "/track/kvTracker.php");
          if ((localObject1 instanceof JSONObject)) {
            break label524;
          }
          localObject1 = ((JSONObject)localObject1).toString();
          Logging.Log("kvPush data:" + (String)localObject1);
          localObject2 = (HttpsURLConnection)HttpInstrumentation.openConnection(((URL)localObject2).openConnection());
          ((HttpsURLConnection)localObject2).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
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
            break label717;
          }
        }
        label462:
        Logging.LogError((String)localObject1);
        Object localObject4 = Feature.prefs.edit();
        Object localObject1 = this.val$pushCommand;
        if (!(localObject1 instanceof JSONObject)) {}
        for (localObject1 = ((JSONObject)localObject1).toString();; localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1))
        {
          ((SharedPreferences.Editor)localObject4).putString("register_remove_token", (String)localObject1).apply();
          if (Global.DEBUGERROR) {
            localException.printStackTrace();
          }
          return;
          label524:
          localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1);
          break;
          localObject1 = ((StringBuffer)localObject1).toString();
          Logging.Log("Result: " + (String)localObject1);
          localObject1 = JSONObjectInstrumentation.init((String)localObject1);
          if ((localObject1 != null) && (((JSONObject)localObject1).get("success").toString().equals("1")))
          {
            Feature.prefs.edit().remove("register_remove_token").apply();
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
              break label706;
            }
          }
          label706:
          for (localObject1 = ((JSONObject)localObject1).toString();; localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1))
          {
            ((SharedPreferences.Editor)localObject3).putString("register_remove_token", (String)localObject1).apply();
            return;
            localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1);
            break;
          }
          label717:
          localObject1 = JSONObjectInstrumentation.toString((JSONObject)localObject1);
          break label462;
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
            Object localObject3 = (HttpsURLConnection)HttpInstrumentation.openConnection(new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection());
            ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
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
                break label423;
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
          label423:
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
    //   5: if_icmplt +255 -> 260
    //   8: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +249 -> 260
    //   14: getstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 119
    //   22: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1799
    //   29: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 397	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 840	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +184 -> 225
    //   44: ldc_w 1801
    //   47: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 847	java/util/Timer
    //   53: dup
    //   54: invokespecial 1347	java/util/Timer:<init>	()V
    //   57: putstatic 840	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 840	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 8	com/kochava/android/tracker/Feature$10
    //   66: dup
    //   67: invokespecial 1802	com/kochava/android/tracker/Feature$10:<init>	()V
    //   70: getstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1588	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 720	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 721
    //   87: ldiv
    //   88: putstatic 388	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 384	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 572	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 743	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1806	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1809	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +46 -> 161
    //   118: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -22
    //   123: ldc_w 353
    //   126: invokeinterface 755 3 0
    //   131: ldc_w 353
    //   134: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +22 -> 161
    //   142: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   145: ldc -22
    //   147: ldc_w 353
    //   150: invokeinterface 755 3 0
    //   155: invokestatic 1814	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   158: invokestatic 1816	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   161: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   164: ifnull +96 -> 260
    //   167: getstatic 614	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   170: ldc -43
    //   172: ldc_w 353
    //   175: invokeinterface 755 3 0
    //   180: ldc -1
    //   182: invokevirtual 759	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   185: ifeq +75 -> 260
    //   188: getstatic 407	com/kochava/android/tracker/Feature:canSendSession	Z
    //   191: ifeq +63 -> 254
    //   194: ldc -65
    //   196: invokestatic 853	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   199: return
    //   200: astore_1
    //   201: new 714	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 715	java/lang/StringBuilder:<init>	()V
    //   208: ldc_w 1818
    //   211: invokevirtual 729	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: aload_1
    //   215: invokevirtual 770	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   218: invokevirtual 732	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   221: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   224: return
    //   225: ldc_w 1820
    //   228: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   231: goto -150 -> 81
    //   234: astore_1
    //   235: ldc_w 1822
    //   238: invokestatic 775	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   241: getstatic 929	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   244: ifeq -83 -> 161
    //   247: aload_1
    //   248: invokevirtual 932	org/json/JSONException:printStackTrace	()V
    //   251: goto -90 -> 161
    //   254: ldc_w 859
    //   257: invokestatic 831	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
        HashMap localHashMap = new HashMap();
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
            localHashMap.put("adid", Feature.advertisingID);
          }
          label378:
          if (Feature.prefs.contains("os_version")) {
            break label1125;
          }
          Logging.Log("No previous os_version in watchlist, adding " + Feature.access$4000());
          Feature.prefs.edit().putString("os_version", Feature.access$4000()).apply();
        }
        Object localObject4;
        for (;;)
        {
          if (!localHashMap.keySet().isEmpty()) {
            try
            {
              localObject2 = new JSONObject();
              ((JSONObject)localObject2).put("action", "update");
              ((JSONObject)localObject2).put("kochava_device_id", Feature.access$3400());
              ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
              ((JSONObject)localObject2).put("sdk_version", "Android20160615" + Feature.versionExtension);
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
          Feature.prefs.edit().putString("app_short_string", Feature.this.mAppVersionName).apply();
          break;
          label723:
          if (Feature.prefs.getBoolean("app_limit_tracking", false) == Feature.this.app_limit_tracking) {
            break label174;
          }
          Logging.Log("app_limit_tracking changed! Is now " + Feature.this.app_limit_tracking);
          localException.put("app_limit_tracking", Feature.this.app_limit_tracking + "");
          Feature.prefs.edit().putBoolean("app_limit_tracking", Feature.this.app_limit_tracking).apply();
          break label174;
          label834:
          if (Feature.prefs.getString("app_version", "").equals(Feature.this.getAppVersion())) {
            break label242;
          }
          Logging.Log("app_version changed! Is now " + Feature.this.getAppVersion());
          localException.put("app_version", Feature.this.getAppVersion() + "");
          Feature.prefs.edit().putString("app_version", Feature.this.getAppVersion()).apply();
          break label242;
          label949:
          if (Feature.prefs.getBoolean("device_limit_tracking", false) == Feature.device_limit_tracking) {
            break label302;
          }
          Logging.Log("device_limit_tracking changed! Is now " + Feature.device_limit_tracking);
          localException.put("device_limit_tracking", Feature.device_limit_tracking + "");
          Feature.prefs.edit().putBoolean("device_limit_tracking", Feature.device_limit_tracking).apply();
          break label302;
          label1044:
          if (Feature.prefs.getString("adid", "").equals(Feature.advertisingID)) {
            break label378;
          }
          Logging.Log("adid changed! Is now " + Feature.advertisingID);
          localException.put("adid", Feature.advertisingID);
          Feature.prefs.edit().putString("adid", Feature.advertisingID).apply();
          break label378;
          label1125:
          if (!Feature.prefs.getString("os_version", "").equals(Feature.access$4000()))
          {
            Logging.Log("os_version changed! Is now " + Feature.access$4000());
            localException.put("os_version", Feature.access$4000());
            Feature.prefs.edit().putString("os_version", Feature.access$4000()).apply();
            Feature.prefs.edit().putString("useragent", Feature.this.getUserAgent()).apply();
          }
        }
        ((JSONObject)localObject2).put("data", localObject3);
        if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
          Feature.access$602("control.kochava.com");
        }
        Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
        Object localObject3 = (HttpsURLConnection)HttpInstrumentation.openConnection(new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection());
        ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", Feature.prefs.getString("useragent", ""));
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
            break label1542;
          }
        }
        label1542:
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
