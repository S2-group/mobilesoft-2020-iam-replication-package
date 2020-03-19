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
            paramAnonymousMessage.put("kochava_device_id", Feature.access$2800());
            paramAnonymousMessage.put("kochava_app_id", Feature.mAppId);
            paramAnonymousMessage.put("sdk_version", "Android20160222" + Feature.versionExtension);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            Logging.Log("Location update: " + paramAnonymousMessage);
            int i = Feature.kDbAdapter.addEvent(paramAnonymousMessage, false, true);
            Feature.access$4902(System.currentTimeMillis());
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
      //   0: ldc 32
      //   2: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   5: invokestatic 43	android/os/Looper:prepare	()V
      //   8: lconst_0
      //   9: lstore 6
      //   11: iconst_0
      //   12: istore_3
      //   13: iconst_0
      //   14: istore_2
      //   15: invokestatic 49	java/lang/System:currentTimeMillis	()J
      //   18: lstore 8
      //   20: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   23: ldc 55
      //   25: invokestatic 49	java/lang/System:currentTimeMillis	()J
      //   28: invokeinterface 61 4 0
      //   33: lstore 10
      //   35: lload 8
      //   37: lload 10
      //   39: lsub
      //   40: lstore 6
      //   42: iload_3
      //   43: istore_1
      //   44: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   47: ldc 63
      //   49: invokeinterface 67 2 0
      //   54: ifeq +65 -> 119
      //   57: iload_3
      //   58: istore_1
      //   59: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   62: ldc 63
      //   64: ldc 69
      //   66: invokeinterface 73 3 0
      //   71: aload_0
      //   72: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   75: invokestatic 77	com/kochava/android/tracker/Feature:access$500	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   78: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   81: ifne +38 -> 119
      //   84: new 85	java/lang/StringBuilder
      //   87: dup
      //   88: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   91: ldc 88
      //   93: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: aload_0
      //   97: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   100: invokestatic 77	com/kochava/android/tracker/Feature:access$500	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   103: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: ldc 94
      //   108: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   114: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   117: iconst_1
      //   118: istore_1
      //   119: iload_1
      //   120: ifne +29 -> 149
      //   123: lload 6
      //   125: lconst_0
      //   126: lcmp
      //   127: ifle +22 -> 149
      //   130: lload 6
      //   132: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   135: ldc 100
      //   137: ldc2_w 101
      //   140: invokeinterface 61 4 0
      //   145: lcmp
      //   146: ifle +3227 -> 3373
      //   149: aconst_null
      //   150: astore 13
      //   152: iload_2
      //   153: istore_1
      //   154: new 85	java/lang/StringBuilder
      //   157: dup
      //   158: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   161: ldc 104
      //   163: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   166: aload_0
      //   167: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   170: getfield 108	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   173: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   176: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   179: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   182: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   185: invokestatic 114	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   188: ifnull +15 -> 203
      //   191: invokestatic 114	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   194: invokevirtual 117	java/lang/String:trim	()Ljava/lang/String;
      //   197: invokevirtual 121	java/lang/String:isEmpty	()Z
      //   200: ifeq +14 -> 214
      //   203: ldc 123
      //   205: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   208: ldc 125
      //   210: invokestatic 129	com/kochava/android/tracker/Feature:access$602	(Ljava/lang/String;)Ljava/lang/String;
      //   213: pop
      //   214: new 85	java/lang/StringBuilder
      //   217: dup
      //   218: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   221: ldc -125
      //   223: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   226: ldc -123
      //   228: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   231: invokestatic 114	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   234: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   237: ldc -121
      //   239: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   242: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   245: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   248: new 137	java/net/URL
      //   251: dup
      //   252: new 85	java/lang/StringBuilder
      //   255: dup
      //   256: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   259: ldc -123
      //   261: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   264: invokestatic 114	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
      //   267: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   270: ldc -121
      //   272: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   275: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   278: invokespecial 139	java/net/URL:<init>	(Ljava/lang/String;)V
      //   281: invokevirtual 143	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   284: checkcast 145	javax/net/ssl/HttpsURLConnection
      //   287: astore 14
      //   289: aload 14
      //   291: ldc -109
      //   293: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   296: ldc -107
      //   298: ldc 69
      //   300: invokeinterface 73 3 0
      //   305: invokevirtual 153	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   308: aload 14
      //   310: ldc -101
      //   312: ldc -99
      //   314: invokevirtual 153	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   317: aload 14
      //   319: ldc -97
      //   321: invokevirtual 162	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   324: aload 14
      //   326: sipush 30000
      //   329: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   332: aload 14
      //   334: sipush 30000
      //   337: invokevirtual 169	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   340: aload 14
      //   342: iconst_1
      //   343: invokevirtual 173	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   346: aload 14
      //   348: iconst_1
      //   349: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   352: aload 14
      //   354: invokevirtual 179	javax/net/ssl/HttpsURLConnection:connect	()V
      //   357: aload_0
      //   358: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   361: getfield 108	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   364: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   367: astore 15
      //   369: new 85	java/lang/StringBuilder
      //   372: dup
      //   373: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   376: ldc -75
      //   378: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   381: aload 15
      //   383: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   386: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   389: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   392: ldc -73
      //   394: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   397: new 185	java/io/OutputStreamWriter
      //   400: dup
      //   401: aload 14
      //   403: invokevirtual 189	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   406: invokespecial 192	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   409: astore 16
      //   411: aload 16
      //   413: aload 15
      //   415: invokevirtual 195	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   418: aload 16
      //   420: invokevirtual 198	java/io/OutputStreamWriter:close	()V
      //   423: ldc -56
      //   425: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   428: new 202	java/lang/StringBuffer
      //   431: dup
      //   432: ldc 69
      //   434: invokespecial 203	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   437: astore 15
      //   439: new 205	java/io/BufferedReader
      //   442: dup
      //   443: new 207	java/io/InputStreamReader
      //   446: dup
      //   447: aload 14
      //   449: invokevirtual 211	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   452: invokespecial 214	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   455: invokespecial 217	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   458: astore 14
      //   460: aload 14
      //   462: invokevirtual 220	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   465: astore 16
      //   467: aload 16
      //   469: ifnull +89 -> 558
      //   472: aload 15
      //   474: aload 16
      //   476: invokevirtual 223	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   479: pop
      //   480: goto -20 -> 460
      //   483: astore 13
      //   485: ldc -31
      //   487: aload 13
      //   489: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   492: invokevirtual 235	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   495: ifeq +2853 -> 3348
      //   498: new 85	java/lang/StringBuilder
      //   501: dup
      //   502: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   505: ldc -19
      //   507: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   510: aload 13
      //   512: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   515: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   518: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   521: aload 13
      //   523: invokestatic 247	com/kochava/android/tracker/Feature:access$2400	(Ljava/lang/Exception;)V
      //   526: return
      //   527: astore 13
      //   529: new 85	java/lang/StringBuilder
      //   532: dup
      //   533: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   536: ldc -7
      //   538: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   541: aload 13
      //   543: invokevirtual 250	java/lang/Exception:toString	()Ljava/lang/String;
      //   546: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   549: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   552: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   555: goto -513 -> 42
      //   558: aload 15
      //   560: invokevirtual 251	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   563: astore 14
      //   565: new 85	java/lang/StringBuilder
      //   568: dup
      //   569: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   572: ldc -3
      //   574: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   577: aload 14
      //   579: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   582: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   585: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   588: new 110	org/json/JSONObject
      //   591: dup
      //   592: aload 14
      //   594: invokespecial 254	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   597: astore 14
      //   599: aload 14
      //   601: astore 13
      //   603: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   606: ldc_w 256
      //   609: ldc 69
      //   611: invokeinterface 73 3 0
      //   616: ldc_w 258
      //   619: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
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
      //   640: ldc_w 260
      //   643: invokevirtual 264	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
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
      //   663: invokevirtual 270	org/json/JSONArray:length	()I
      //   666: if_icmpge +90 -> 756
      //   669: ldc_w 272
      //   672: aload 14
      //   674: iload 4
      //   676: invokevirtual 275	org/json/JSONArray:getString	(I)Ljava/lang/String;
      //   679: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
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
      //   698: new 85	java/lang/StringBuilder
      //   701: dup
      //   702: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   705: ldc_w 277
      //   708: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   711: invokestatic 281	com/kochava/android/tracker/Feature:access$700	()Ljava/util/HashMap;
      //   714: iload_2
      //   715: invokestatic 287	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   718: invokevirtual 293	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   721: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   724: ldc_w 295
      //   727: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   730: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   733: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   736: invokestatic 281	com/kochava/android/tracker/Feature:access$700	()Ljava/util/HashMap;
      //   739: iload_2
      //   740: invokestatic 287	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   743: invokevirtual 293	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   746: checkcast 283	java/lang/Integer
      //   749: invokevirtual 298	java/lang/Integer:intValue	()I
      //   752: i2l
      //   753: invokestatic 304	java/lang/Thread:sleep	(J)V
      //   756: iload_3
      //   757: ifne +5 -> 762
      //   760: iconst_0
      //   761: istore_2
      //   762: iload_2
      //   763: ifne +2868 -> 3631
      //   766: aload 13
      //   768: ifnull +2211 -> 2979
      //   771: new 85	java/lang/StringBuilder
      //   774: dup
      //   775: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   778: ldc_w 306
      //   781: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   784: aload 13
      //   786: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   789: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   792: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   795: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   798: aconst_null
      //   799: astore 14
      //   801: aload 13
      //   803: ldc_w 308
      //   806: invokevirtual 312	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   809: astore 15
      //   811: aload 15
      //   813: astore 14
      //   815: new 85	java/lang/StringBuilder
      //   818: dup
      //   819: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   822: ldc_w 314
      //   825: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   828: aload 15
      //   830: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   833: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   836: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   839: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   842: aload 15
      //   844: astore 14
      //   846: aload 14
      //   848: ifnull +750 -> 1598
      //   851: aload 14
      //   853: ldc_w 316
      //   856: invokevirtual 318	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   859: astore 15
      //   861: new 85	java/lang/StringBuilder
      //   864: dup
      //   865: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   868: ldc_w 320
      //   871: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   874: aload 15
      //   876: invokevirtual 321	java/lang/String:toString	()Ljava/lang/String;
      //   879: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   882: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   885: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   888: aload 15
      //   890: invokestatic 324	com/kochava/android/tracker/Feature:access$802	(Ljava/lang/String;)Ljava/lang/String;
      //   893: pop
      //   894: aload 14
      //   896: ldc_w 326
      //   899: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   902: ldc_w 331
      //   905: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   908: ifeq +8 -> 916
      //   911: iconst_0
      //   912: invokestatic 336	com/kochava/android/tracker/Feature:access$902	(Z)Z
      //   915: pop
      //   916: aload 14
      //   918: ldc_w 338
      //   921: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   924: checkcast 79	java/lang/String
      //   927: invokevirtual 341	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   930: astore 15
      //   932: new 85	java/lang/StringBuilder
      //   935: dup
      //   936: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   939: ldc_w 343
      //   942: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   945: aload 15
      //   947: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   950: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   953: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   956: aload_0
      //   957: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   960: aload 15
      //   962: invokestatic 347	com/kochava/android/tracker/Feature:access$1000	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;)V
      //   965: aload 14
      //   967: ldc_w 349
      //   970: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   973: ldc_w 258
      //   976: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   979: ifeq +47 -> 1026
      //   982: ldc_w 351
      //   985: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   988: getstatic 355	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   991: ldc_w 357
      //   994: iconst_0
      //   995: invokevirtual 363	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   998: invokestatic 367	com/kochava/android/tracker/Feature:access$402	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   1001: pop
      //   1002: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1005: invokeinterface 371 1 0
      //   1010: ldc_w 256
      //   1013: ldc_w 373
      //   1016: invokeinterface 379 3 0
      //   1021: invokeinterface 382 1 0
      //   1026: aload 14
      //   1028: ldc_w 384
      //   1031: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1034: checkcast 283	java/lang/Integer
      //   1037: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1040: invokestatic 388	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1043: pop
      //   1044: invokestatic 391	com/kochava/android/tracker/Feature:access$1100	()I
      //   1047: ifge +777 -> 1824
      //   1050: new 85	java/lang/StringBuilder
      //   1053: dup
      //   1054: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1057: ldc_w 393
      //   1060: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1063: invokestatic 391	com/kochava/android/tracker/Feature:access$1100	()I
      //   1066: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1069: ldc_w 398
      //   1072: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1075: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1078: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1081: iconst_0
      //   1082: invokestatic 388	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1085: pop
      //   1086: invokestatic 401	com/kochava/android/tracker/Feature:access$1200	()Z
      //   1089: ifne +89 -> 1178
      //   1092: aload 14
      //   1094: ldc_w 403
      //   1097: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1100: ifnull +78 -> 1178
      //   1103: aload 14
      //   1105: ldc_w 403
      //   1108: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1111: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1114: ldc_w 283
      //   1117: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1120: ifeq +58 -> 1178
      //   1123: aload 14
      //   1125: ldc_w 403
      //   1128: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1131: checkcast 283	java/lang/Integer
      //   1134: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1137: istore_1
      //   1138: iload_1
      //   1139: bipush 60
      //   1141: if_icmpge +759 -> 1900
      //   1144: new 85	java/lang/StringBuilder
      //   1147: dup
      //   1148: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1151: ldc_w 405
      //   1154: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1157: iload_1
      //   1158: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1161: ldc_w 407
      //   1164: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1167: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1170: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1173: iconst_1
      //   1174: invokestatic 410	com/kochava/android/tracker/Feature:access$1402	(Z)Z
      //   1177: pop
      //   1178: aload 14
      //   1180: ldc 100
      //   1182: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1185: ifnull +92 -> 1277
      //   1188: aload 14
      //   1190: ldc 100
      //   1192: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1195: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1198: ldc_w 283
      //   1201: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1204: ifeq +73 -> 1277
      //   1207: aload 14
      //   1209: ldc 100
      //   1211: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1214: checkcast 283	java/lang/Integer
      //   1217: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1220: istore_1
      //   1221: iload_1
      //   1222: ifge +765 -> 1987
      //   1225: new 85	java/lang/StringBuilder
      //   1228: dup
      //   1229: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1232: ldc_w 412
      //   1235: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1238: iload_1
      //   1239: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1242: ldc_w 414
      //   1245: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1248: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1251: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1254: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   1257: invokeinterface 371 1 0
      //   1262: ldc 100
      //   1264: ldc2_w 101
      //   1267: invokeinterface 418 4 0
      //   1272: invokeinterface 382 1 0
      //   1277: aload 14
      //   1279: ldc_w 420
      //   1282: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1285: ifnull +77 -> 1362
      //   1288: aload 14
      //   1290: ldc_w 420
      //   1293: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1296: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1299: ldc_w 283
      //   1302: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1305: ifeq +57 -> 1362
      //   1308: aload 14
      //   1310: ldc_w 420
      //   1313: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1316: checkcast 283	java/lang/Integer
      //   1319: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1322: istore_1
      //   1323: iload_1
      //   1324: iconst_1
      //   1325: if_icmpge +782 -> 2107
      //   1328: new 85	java/lang/StringBuilder
      //   1331: dup
      //   1332: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1335: ldc_w 422
      //   1338: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1341: iload_1
      //   1342: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1345: ldc_w 424
      //   1348: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1351: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1354: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1357: iconst_1
      //   1358: invokestatic 427	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   1361: pop
      //   1362: aload 14
      //   1364: ldc_w 429
      //   1367: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1370: checkcast 283	java/lang/Integer
      //   1373: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1376: istore_2
      //   1377: iload_2
      //   1378: bipush 10
      //   1380: if_icmpge +808 -> 2188
      //   1383: new 85	java/lang/StringBuilder
      //   1386: dup
      //   1387: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1390: ldc_w 431
      //   1393: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1396: iload_2
      //   1397: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1400: ldc_w 433
      //   1403: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1406: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1409: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1412: bipush 10
      //   1414: istore_1
      //   1415: new 85	java/lang/StringBuilder
      //   1418: dup
      //   1419: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1422: ldc_w 435
      //   1425: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1428: iload_1
      //   1429: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1432: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1435: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1438: iload_1
      //   1439: putstatic 441	com/kochava/android/tracker/LocationDirector:desiredAccuracy	I
      //   1442: aload 14
      //   1444: ldc_w 443
      //   1447: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1450: checkcast 283	java/lang/Integer
      //   1453: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1456: istore_2
      //   1457: iload_2
      //   1458: iconst_3
      //   1459: if_icmpge +774 -> 2233
      //   1462: new 85	java/lang/StringBuilder
      //   1465: dup
      //   1466: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1469: ldc_w 445
      //   1472: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1475: iload_2
      //   1476: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1479: ldc_w 447
      //   1482: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1485: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1488: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1491: iconst_3
      //   1492: istore_1
      //   1493: new 85	java/lang/StringBuilder
      //   1496: dup
      //   1497: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1500: ldc_w 449
      //   1503: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1506: iload_1
      //   1507: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1510: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1513: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1516: iload_1
      //   1517: putstatic 452	com/kochava/android/tracker/LocationDirector:timeout	I
      //   1520: aload 14
      //   1522: ldc_w 454
      //   1525: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1528: checkcast 283	java/lang/Integer
      //   1531: invokevirtual 298	java/lang/Integer:intValue	()I
      //   1534: istore_2
      //   1535: iload_2
      //   1536: iconst_1
      //   1537: if_icmpge +739 -> 2276
      //   1540: new 85	java/lang/StringBuilder
      //   1543: dup
      //   1544: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1547: ldc_w 456
      //   1550: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1553: iload_2
      //   1554: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1557: ldc_w 458
      //   1560: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1563: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1566: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1569: iconst_1
      //   1570: istore_1
      //   1571: new 85	java/lang/StringBuilder
      //   1574: dup
      //   1575: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1578: ldc_w 460
      //   1581: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1584: iload_1
      //   1585: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1588: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1591: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1594: iload_1
      //   1595: putstatic 463	com/kochava/android/tracker/LocationDirector:staleness	I
      //   1598: aload 13
      //   1600: ldc_w 465
      //   1603: invokevirtual 264	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1606: astore 15
      //   1608: new 85	java/lang/StringBuilder
      //   1611: dup
      //   1612: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1615: ldc_w 467
      //   1618: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1621: aload 15
      //   1623: invokevirtual 468	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1626: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1629: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1632: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1635: iconst_0
      //   1636: istore_1
      //   1637: iload_1
      //   1638: aload 15
      //   1640: invokevirtual 270	org/json/JSONArray:length	()I
      //   1643: if_icmpge +741 -> 2384
      //   1646: aload 15
      //   1648: iload_1
      //   1649: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1652: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   1655: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1658: ldc_w 477
      //   1661: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1664: ifeq +657 -> 2321
      //   1667: ldc_w 479
      //   1670: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1673: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   1676: ldc_w 477
      //   1679: iconst_0
      //   1680: invokestatic 487	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1683: invokevirtual 491	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1686: pop
      //   1687: iload_1
      //   1688: iconst_1
      //   1689: iadd
      //   1690: istore_1
      //   1691: goto -54 -> 1637
      //   1694: astore 14
      //   1696: new 85	java/lang/StringBuilder
      //   1699: dup
      //   1700: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1703: ldc_w 493
      //   1706: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1709: aload 14
      //   1711: invokevirtual 494	org/json/JSONException:toString	()Ljava/lang/String;
      //   1714: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1717: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1720: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
      //   1744: ldc_w 496
      //   1747: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1750: goto -904 -> 846
      //   1753: astore 13
      //   1755: aload 13
      //   1757: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1760: ldc -31
      //   1762: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1765: ifeq +1529 -> 3294
      //   1768: new 85	java/lang/StringBuilder
      //   1771: dup
      //   1772: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1775: ldc -19
      //   1777: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1780: aload 13
      //   1782: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1785: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1788: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1791: aload 13
      //   1793: invokestatic 247	com/kochava/android/tracker/Feature:access$2400	(Ljava/lang/Exception;)V
      //   1796: return
      //   1797: astore 13
      //   1799: goto -1314 -> 485
      //   1802: astore 15
      //   1804: ldc_w 498
      //   1807: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1810: goto -916 -> 894
      //   1813: astore 15
      //   1815: ldc_w 496
      //   1818: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1821: goto -905 -> 916
      //   1824: invokestatic 391	com/kochava/android/tracker/Feature:access$1100	()I
      //   1827: bipush 120
      //   1829: if_icmple +43 -> 1872
      //   1832: new 85	java/lang/StringBuilder
      //   1835: dup
      //   1836: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1839: ldc_w 500
      //   1842: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1845: invokestatic 391	com/kochava/android/tracker/Feature:access$1100	()I
      //   1848: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1851: ldc_w 502
      //   1854: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1857: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1860: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1863: bipush 120
      //   1865: invokestatic 388	com/kochava/android/tracker/Feature:access$1102	(I)I
      //   1868: pop
      //   1869: goto -783 -> 1086
      //   1872: new 85	java/lang/StringBuilder
      //   1875: dup
      //   1876: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1879: ldc_w 504
      //   1882: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1885: invokestatic 391	com/kochava/android/tracker/Feature:access$1100	()I
      //   1888: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1891: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1894: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1897: goto -811 -> 1086
      //   1900: iload_1
      //   1901: ldc_w 505
      //   1904: if_icmple +42 -> 1946
      //   1907: new 85	java/lang/StringBuilder
      //   1910: dup
      //   1911: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1914: ldc_w 507
      //   1917: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1920: iload_1
      //   1921: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1924: ldc_w 509
      //   1927: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1930: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1933: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1936: ldc_w 510
      //   1939: invokestatic 513	com/kochava/android/tracker/Feature:access$1302	(I)I
      //   1942: pop
      //   1943: goto -770 -> 1173
      //   1946: iload_1
      //   1947: sipush 1000
      //   1950: imul
      //   1951: invokestatic 513	com/kochava/android/tracker/Feature:access$1302	(I)I
      //   1954: pop
      //   1955: new 85	java/lang/StringBuilder
      //   1958: dup
      //   1959: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1962: ldc_w 515
      //   1965: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1968: iload_1
      //   1969: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1972: ldc_w 517
      //   1975: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1978: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1981: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1984: goto -811 -> 1173
      //   1987: iload_1
      //   1988: ldc_w 505
      //   1991: if_icmple +58 -> 2049
      //   1994: new 85	java/lang/StringBuilder
      //   1997: dup
      //   1998: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2001: ldc_w 412
      //   2004: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2007: iload_1
      //   2008: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2011: ldc_w 519
      //   2014: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2017: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2020: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2023: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2026: invokeinterface 371 1 0
      //   2031: ldc 100
      //   2033: ldc2_w 520
      //   2036: invokeinterface 418 4 0
      //   2041: invokeinterface 382 1 0
      //   2046: goto -769 -> 1277
      //   2049: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2052: invokeinterface 371 1 0
      //   2057: ldc 100
      //   2059: iload_1
      //   2060: sipush 1000
      //   2063: imul
      //   2064: i2l
      //   2065: invokeinterface 418 4 0
      //   2070: invokeinterface 382 1 0
      //   2075: new 85	java/lang/StringBuilder
      //   2078: dup
      //   2079: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2082: ldc_w 523
      //   2085: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2088: iload_1
      //   2089: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2092: ldc_w 517
      //   2095: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2098: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2101: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2104: goto -827 -> 1277
      //   2107: iload_1
      //   2108: bipush 30
      //   2110: if_icmple +41 -> 2151
      //   2113: new 85	java/lang/StringBuilder
      //   2116: dup
      //   2117: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2120: ldc_w 422
      //   2123: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2126: iload_1
      //   2127: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2130: ldc_w 525
      //   2133: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2136: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2139: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2142: bipush 30
      //   2144: invokestatic 427	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   2147: pop
      //   2148: goto -786 -> 1362
      //   2151: new 85	java/lang/StringBuilder
      //   2154: dup
      //   2155: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2158: ldc_w 527
      //   2161: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2164: iload_1
      //   2165: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2168: ldc_w 517
      //   2171: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2174: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2177: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2180: iload_1
      //   2181: invokestatic 427	com/kochava/android/tracker/Feature:access$1502	(I)I
      //   2184: pop
      //   2185: goto -823 -> 1362
      //   2188: iload_2
      //   2189: istore_1
      //   2190: iload_2
      //   2191: sipush 5000
      //   2194: if_icmple -779 -> 1415
      //   2197: new 85	java/lang/StringBuilder
      //   2200: dup
      //   2201: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2204: ldc_w 431
      //   2207: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2210: iload_2
      //   2211: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2214: ldc_w 433
      //   2217: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2220: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2223: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2226: sipush 5000
      //   2229: istore_1
      //   2230: goto -815 -> 1415
      //   2233: iload_2
      //   2234: istore_1
      //   2235: iload_2
      //   2236: bipush 60
      //   2238: if_icmple -745 -> 1493
      //   2241: new 85	java/lang/StringBuilder
      //   2244: dup
      //   2245: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2248: ldc_w 445
      //   2251: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2254: iload_2
      //   2255: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2258: ldc_w 447
      //   2261: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2264: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2267: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2270: bipush 60
      //   2272: istore_1
      //   2273: goto -780 -> 1493
      //   2276: iload_2
      //   2277: istore_1
      //   2278: iload_2
      //   2279: sipush 10080
      //   2282: if_icmple -711 -> 1571
      //   2285: new 85	java/lang/StringBuilder
      //   2288: dup
      //   2289: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2292: ldc_w 456
      //   2295: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2298: iload_2
      //   2299: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2302: ldc_w 458
      //   2305: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2308: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2311: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2314: sipush 10080
      //   2317: istore_1
      //   2318: goto -747 -> 1571
      //   2321: aload 15
      //   2323: iload_1
      //   2324: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2327: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2330: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2333: ldc_w 529
      //   2336: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2339: ifeq +132 -> 2471
      //   2342: ldc_w 531
      //   2345: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2348: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2351: ldc_w 529
      //   2354: iconst_0
      //   2355: invokestatic 487	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2358: invokevirtual 491	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2361: pop
      //   2362: goto -675 -> 1687
      //   2365: astore 15
      //   2367: ldc_w 533
      //   2370: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2373: getstatic 539	com/kochava/android/tracker/Global:DEBUGERROR	Z
      //   2376: ifeq +8 -> 2384
      //   2379: aload 15
      //   2381: invokevirtual 542	java/lang/Exception:printStackTrace	()V
      //   2384: aload 13
      //   2386: ldc_w 544
      //   2389: invokevirtual 264	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2392: astore 15
      //   2394: new 85	java/lang/StringBuilder
      //   2397: dup
      //   2398: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2401: ldc_w 546
      //   2404: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2407: aload 15
      //   2409: invokevirtual 468	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2412: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2415: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2418: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2421: iconst_0
      //   2422: istore_1
      //   2423: iload_1
      //   2424: aload 15
      //   2426: invokevirtual 270	org/json/JSONArray:length	()I
      //   2429: if_icmpge +282 -> 2711
      //   2432: aload 15
      //   2434: iload_1
      //   2435: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2438: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2441: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2444: ldc_w 548
      //   2447: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2450: ifeq +197 -> 2647
      //   2453: ldc_w 550
      //   2456: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2459: iconst_1
      //   2460: invokestatic 553	com/kochava/android/tracker/Feature:access$1702	(Z)Z
      //   2463: pop
      //   2464: iload_1
      //   2465: iconst_1
      //   2466: iadd
      //   2467: istore_1
      //   2468: goto -45 -> 2423
      //   2471: aload 15
      //   2473: iload_1
      //   2474: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2477: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2480: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2483: ldc_w 555
      //   2486: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2489: ifeq +26 -> 2515
      //   2492: ldc_w 557
      //   2495: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2498: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2501: ldc_w 555
      //   2504: iconst_0
      //   2505: invokestatic 487	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2508: invokevirtual 491	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2511: pop
      //   2512: goto -825 -> 1687
      //   2515: aload 15
      //   2517: iload_1
      //   2518: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2521: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2524: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2527: ldc_w 559
      //   2530: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2533: ifeq +26 -> 2559
      //   2536: ldc_w 561
      //   2539: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2542: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2545: ldc_w 559
      //   2548: iconst_0
      //   2549: invokestatic 487	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2552: invokevirtual 491	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2555: pop
      //   2556: goto -869 -> 1687
      //   2559: aload 15
      //   2561: iload_1
      //   2562: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2565: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2568: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2571: ldc_w 563
      //   2574: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2577: ifeq +26 -> 2603
      //   2580: ldc_w 565
      //   2583: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2586: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2589: ldc_w 563
      //   2592: iconst_0
      //   2593: invokestatic 487	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2596: invokevirtual 491	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2599: pop
      //   2600: goto -913 -> 1687
      //   2603: aload 15
      //   2605: iload_1
      //   2606: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2609: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2612: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2615: ldc_w 567
      //   2618: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2621: ifeq -934 -> 1687
      //   2624: ldc_w 569
      //   2627: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2630: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   2633: ldc_w 567
      //   2636: iconst_0
      //   2637: invokestatic 487	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2640: invokevirtual 491	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2643: pop
      //   2644: goto -957 -> 1687
      //   2647: aload 15
      //   2649: iload_1
      //   2650: invokevirtual 471	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2653: invokevirtual 472	java/lang/Object:toString	()Ljava/lang/String;
      //   2656: invokevirtual 475	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2659: ldc_w 571
      //   2662: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2665: ifeq -201 -> 2464
      //   2668: ldc_w 573
      //   2671: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2674: iconst_1
      //   2675: invokestatic 576	com/kochava/android/tracker/Feature:access$1802	(Z)Z
      //   2678: pop
      //   2679: goto -215 -> 2464
      //   2682: astore 15
      //   2684: new 85	java/lang/StringBuilder
      //   2687: dup
      //   2688: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2691: ldc_w 578
      //   2694: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2697: aload 15
      //   2699: invokevirtual 250	java/lang/Exception:toString	()Ljava/lang/String;
      //   2702: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2705: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2708: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2711: aload 13
      //   2713: ldc_w 580
      //   2716: invokevirtual 312	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2719: ldc_w 582
      //   2722: invokevirtual 312	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2725: astore 15
      //   2727: aload_0
      //   2728: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   2731: aload 15
      //   2733: invokestatic 586	com/kochava/android/tracker/Feature:access$1900	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   2736: invokestatic 589	com/kochava/android/tracker/Feature:access$1800	()Z
      //   2739: ifeq +18 -> 2757
      //   2742: invokestatic 592	com/kochava/android/tracker/Feature:access$2000	()Z
      //   2745: ifeq +12 -> 2757
      //   2748: getstatic 355	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2751: invokestatic 596	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   2754: invokevirtual 599	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   2757: aload 13
      //   2759: ldc_w 601
      //   2762: invokevirtual 264	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2765: invokestatic 605	com/kochava/android/tracker/Feature:access$2102	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   2768: pop
      //   2769: new 85	java/lang/StringBuilder
      //   2772: dup
      //   2773: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2776: ldc_w 607
      //   2779: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2782: invokestatic 611	com/kochava/android/tracker/Feature:access$2100	()Lorg/json/JSONArray;
      //   2785: invokevirtual 468	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2788: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2791: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2794: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2797: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2800: invokeinterface 371 1 0
      //   2805: ldc_w 613
      //   2808: invokestatic 611	com/kochava/android/tracker/Feature:access$2100	()Lorg/json/JSONArray;
      //   2811: invokevirtual 468	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2814: invokeinterface 379 3 0
      //   2819: invokeinterface 382 1 0
      //   2824: aload 14
      //   2826: ldc_w 615
      //   2829: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2832: ifnull +88 -> 2920
      //   2835: aload 14
      //   2837: ldc_w 615
      //   2840: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2843: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2846: ldc_w 484
      //   2849: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2852: ifeq +432 -> 3284
      //   2855: aload 14
      //   2857: ldc_w 615
      //   2860: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2863: checkcast 484	java/lang/Boolean
      //   2866: invokevirtual 618	java/lang/Boolean:booleanValue	()Z
      //   2869: ifeq +415 -> 3284
      //   2872: iconst_1
      //   2873: istore_1
      //   2874: aload 14
      //   2876: ldc_w 615
      //   2879: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2882: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2885: ldc 79
      //   2887: invokevirtual 332	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2890: ifeq +399 -> 3289
      //   2893: ldc_w 258
      //   2896: aload 14
      //   2898: ldc_w 615
      //   2901: invokevirtual 329	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2904: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2907: ifeq +382 -> 3289
      //   2910: iconst_1
      //   2911: istore_2
      //   2912: goto +744 -> 3656
      //   2915: iconst_1
      //   2916: invokestatic 621	com/kochava/android/tracker/Feature:access$2202	(Z)Z
      //   2919: pop
      //   2920: aload 13
      //   2922: ldc_w 623
      //   2925: invokevirtual 318	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   2928: astore 13
      //   2930: new 85	java/lang/StringBuilder
      //   2933: dup
      //   2934: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2937: ldc_w 625
      //   2940: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2943: aload 13
      //   2945: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2948: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2951: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2954: aload 13
      //   2956: ldc_w 627
      //   2959: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2962: ifeq +17 -> 2979
      //   2965: iconst_1
      //   2966: invokestatic 630	com/kochava/android/tracker/Feature:access$2302	(Z)Z
      //   2969: pop
      //   2970: return
      //   2971: astore 13
      //   2973: ldc_w 632
      //   2976: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2979: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   2982: invokeinterface 371 1 0
      //   2987: ldc 55
      //   2989: invokestatic 49	java/lang/System:currentTimeMillis	()J
      //   2992: invokeinterface 418 4 0
      //   2997: invokeinterface 382 1 0
      //   3002: ldc_w 634
      //   3005: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3008: iconst_0
      //   3009: istore_1
      //   3010: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3013: ldc_w 256
      //   3016: ldc 69
      //   3018: invokeinterface 73 3 0
      //   3023: ldc_w 258
      //   3026: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3029: ifne +548 -> 3577
      //   3032: invokestatic 482	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3035: ldc_w 555
      //   3038: invokevirtual 293	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   3041: checkcast 484	java/lang/Boolean
      //   3044: invokevirtual 618	java/lang/Boolean:booleanValue	()Z
      //   3047: ifne +363 -> 3410
      //   3050: iconst_1
      //   3051: istore_2
      //   3052: invokestatic 637	com/kochava/android/tracker/Feature:access$2500	()Z
      //   3055: istore 12
      //   3057: iload_1
      //   3058: invokestatic 391	com/kochava/android/tracker/Feature:access$1100	()I
      //   3061: if_icmpge +50 -> 3111
      //   3064: invokestatic 53	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3067: ldc_w 639
      //   3070: ldc_w 641
      //   3073: invokeinterface 73 3 0
      //   3078: ldc_w 641
      //   3081: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3084: ifne +331 -> 3415
      //   3087: iconst_1
      //   3088: istore_3
      //   3089: invokestatic 644	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3092: ifnull +328 -> 3420
      //   3095: iconst_1
      //   3096: istore 4
      //   3098: iload_3
      //   3099: ifeq +327 -> 3426
      //   3102: iload_2
      //   3103: ifne +8 -> 3111
      //   3106: iload 12
      //   3108: ifeq +318 -> 3426
      //   3111: new 85	java/lang/StringBuilder
      //   3114: dup
      //   3115: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3118: ldc_w 646
      //   3121: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3124: iload_1
      //   3125: invokevirtual 396	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3128: ldc_w 517
      //   3131: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3134: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3137: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3140: iload_1
      //   3141: istore_2
      //   3142: iload_1
      //   3143: iconst_2
      //   3144: if_icmpge +46 -> 3190
      //   3147: aload_0
      //   3148: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3151: invokestatic 649	com/kochava/android/tracker/Feature:access$000	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   3154: ifnull +323 -> 3477
      //   3157: iconst_1
      //   3158: istore_3
      //   3159: invokestatic 644	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3162: ifnull +320 -> 3482
      //   3165: invokestatic 644	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3168: invokevirtual 121	java/lang/String:isEmpty	()Z
      //   3171: ifne +311 -> 3482
      //   3174: iconst_1
      //   3175: istore 4
      //   3177: iload_1
      //   3178: istore_2
      //   3179: iload 4
      //   3181: ifne +9 -> 3190
      //   3184: iload_3
      //   3185: ifeq +303 -> 3488
      //   3188: iload_1
      //   3189: istore_2
      //   3190: iload_2
      //   3191: iconst_2
      //   3192: if_icmpge +19 -> 3211
      //   3195: aload_0
      //   3196: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3199: invokestatic 652	com/kochava/android/tracker/Feature:access$300	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   3202: ifnull +328 -> 3530
      //   3205: iconst_1
      //   3206: istore_1
      //   3207: iload_1
      //   3208: ifeq +327 -> 3535
      //   3211: invokestatic 658	android/os/Message:obtain	()Landroid/os/Message;
      //   3214: astore 13
      //   3216: new 660	android/os/Bundle
      //   3219: dup
      //   3220: invokespecial 661	android/os/Bundle:<init>	()V
      //   3223: astore 14
      //   3225: aload 14
      //   3227: ldc_w 663
      //   3230: invokestatic 666	com/kochava/android/tracker/Feature:access$2600	()Z
      //   3233: invokevirtual 670	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   3236: aload 13
      //   3238: aload 14
      //   3240: invokevirtual 674	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   3243: aload_0
      //   3244: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3247: invokestatic 678	com/kochava/android/tracker/Feature:access$2700	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   3250: ifnull -2724 -> 526
      //   3253: ldc_w 680
      //   3256: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3259: aload_0
      //   3260: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3263: invokestatic 678	com/kochava/android/tracker/Feature:access$2700	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   3266: aload 13
      //   3268: invokevirtual 686	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   3271: pop
      //   3272: return
      //   3273: astore 15
      //   3275: ldc_w 688
      //   3278: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3281: goto -457 -> 2824
      //   3284: iconst_0
      //   3285: istore_1
      //   3286: goto -412 -> 2874
      //   3289: iconst_0
      //   3290: istore_2
      //   3291: goto +365 -> 3656
      //   3294: new 85	java/lang/StringBuilder
      //   3297: dup
      //   3298: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3301: ldc_w 690
      //   3304: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3307: aload 13
      //   3309: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3312: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3315: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3318: return
      //   3319: astore 13
      //   3321: new 85	java/lang/StringBuilder
      //   3324: dup
      //   3325: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3328: ldc_w 692
      //   3331: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3334: aload 13
      //   3336: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3339: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3342: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3345: goto -366 -> 2979
      //   3348: new 85	java/lang/StringBuilder
      //   3351: dup
      //   3352: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3355: ldc_w 690
      //   3358: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3361: aload 13
      //   3363: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3366: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3369: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3372: return
      //   3373: new 85	java/lang/StringBuilder
      //   3376: dup
      //   3377: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3380: ldc_w 694
      //   3383: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3386: lload 6
      //   3388: ldc2_w 695
      //   3391: ldiv
      //   3392: invokevirtual 699	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3395: ldc_w 701
      //   3398: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3401: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3404: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3407: goto -405 -> 3002
      //   3410: iconst_0
      //   3411: istore_2
      //   3412: goto -360 -> 3052
      //   3415: iconst_0
      //   3416: istore_3
      //   3417: goto -328 -> 3089
      //   3420: iconst_0
      //   3421: istore 4
      //   3423: goto -325 -> 3098
      //   3426: iload_3
      //   3427: ifeq +8 -> 3435
      //   3430: iload 4
      //   3432: ifne -321 -> 3111
      //   3435: ldc2_w 695
      //   3438: invokestatic 304	java/lang/Thread:sleep	(J)V
      //   3441: iload_1
      //   3442: iconst_1
      //   3443: iadd
      //   3444: istore_1
      //   3445: goto -388 -> 3057
      //   3448: astore 13
      //   3450: new 85	java/lang/StringBuilder
      //   3453: dup
      //   3454: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3457: ldc_w 703
      //   3460: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3463: aload 13
      //   3465: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3468: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3471: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3474: goto -33 -> 3441
      //   3477: iconst_0
      //   3478: istore_3
      //   3479: goto -320 -> 3159
      //   3482: iconst_0
      //   3483: istore 4
      //   3485: goto -308 -> 3177
      //   3488: ldc2_w 695
      //   3491: invokestatic 304	java/lang/Thread:sleep	(J)V
      //   3494: iload_1
      //   3495: iconst_1
      //   3496: iadd
      //   3497: istore_1
      //   3498: goto -358 -> 3140
      //   3501: astore 13
      //   3503: new 85	java/lang/StringBuilder
      //   3506: dup
      //   3507: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3510: ldc_w 703
      //   3513: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3516: aload 13
      //   3518: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3521: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3524: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3527: goto -33 -> 3494
      //   3530: iconst_0
      //   3531: istore_1
      //   3532: goto -325 -> 3207
      //   3535: ldc2_w 695
      //   3538: invokestatic 304	java/lang/Thread:sleep	(J)V
      //   3541: iload_2
      //   3542: iconst_1
      //   3543: iadd
      //   3544: istore_2
      //   3545: goto -355 -> 3190
      //   3548: astore 13
      //   3550: new 85	java/lang/StringBuilder
      //   3553: dup
      //   3554: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3557: ldc_w 703
      //   3560: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3563: aload 13
      //   3565: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3568: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3571: invokestatic 243	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3574: goto -33 -> 3541
      //   3577: ldc_w 705
      //   3580: invokestatic 38	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3583: goto -372 -> 3211
      //   3586: astore 13
      //   3588: goto -267 -> 3321
      //   3591: astore 14
      //   3593: goto -673 -> 2920
      //   3596: astore 15
      //   3598: goto -774 -> 2824
      //   3601: astore 15
      //   3603: goto -867 -> 2736
      //   3606: astore 15
      //   3608: goto -2010 -> 1598
      //   3611: astore 15
      //   3613: goto -2093 -> 1520
      //   3616: astore 15
      //   3618: goto -2176 -> 1442
      //   3621: astore 15
      //   3623: goto -2597 -> 1026
      //   3626: astore 15
      //   3628: goto -2663 -> 965
      //   3631: iload_2
      //   3632: istore_1
      //   3633: goto -3479 -> 154
      //   3636: astore 15
      //   3638: goto -2552 -> 1086
      //   3641: astore 15
      //   3643: goto -2465 -> 1178
      //   3646: astore 15
      //   3648: goto -2371 -> 1277
      //   3651: astore 15
      //   3653: goto -2291 -> 1362
      //   3656: iload_1
      //   3657: ifne -742 -> 2915
      //   3660: iload_2
      //   3661: ifeq -741 -> 2920
      //   3664: goto -749 -> 2915
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3667	0	this	6
      //   43	3614	1	i	int
      //   14	3647	2	j	int
      //   12	3467	3	k	int
      //   652	2832	4	m	int
      //   649	8	5	n	int
      //   9	3378	6	l1	long
      //   18	18	8	l2	long
      //   33	5	10	l3	long
      //   622	2485	12	bool	boolean
      //   150	1	13	localObject1	Object
      //   483	39	13	localIOException1	IOException
      //   527	15	13	localException1	Exception
      //   601	998	13	localObject2	Object
      //   1753	39	13	localException2	Exception
      //   1797	1124	13	localIOException2	IOException
      //   2928	27	13	str	String
      //   2971	1	13	localException3	Exception
      //   3214	94	13	localMessage	Message
      //   3319	43	13	localException4	Exception
      //   3448	16	13	localInterruptedException1	InterruptedException
      //   3501	16	13	localInterruptedException2	InterruptedException
      //   3548	16	13	localInterruptedException3	InterruptedException
      //   3586	1	13	localException5	Exception
      //   287	1234	14	localObject3	Object
      //   1694	16	14	localJSONException1	JSONException
      //   1735	1162	14	localJSONException2	JSONException
      //   3223	16	14	localBundle	Bundle
      //   3591	1	14	localException6	Exception
      //   367	1280	15	localObject4	Object
      //   1742	1	15	localJSONException3	JSONException
      //   1802	1	15	localJSONException4	JSONException
      //   1813	509	15	localJSONException5	JSONException
      //   2365	15	15	localException7	Exception
      //   2392	256	15	localJSONArray	JSONArray
      //   2682	16	15	localException8	Exception
      //   2725	7	15	localJSONObject	JSONObject
      //   3273	1	15	localException9	Exception
      //   3596	1	15	localJSONException6	JSONException
      //   3601	1	15	localException10	Exception
      //   3606	1	15	localException11	Exception
      //   3611	1	15	localException12	Exception
      //   3616	1	15	localException13	Exception
      //   3621	1	15	localException14	Exception
      //   3626	1	15	localException15	Exception
      //   3636	1	15	localException16	Exception
      //   3641	1	15	localException17	Exception
      //   3646	1	15	localException18	Exception
      //   3651	1	15	localException19	Exception
      //   409	66	16	localObject5	Object
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
      //   2684	2711	1753	java/lang/Exception
      //   2736	2757	1753	java/lang/Exception
      //   2973	2979	1753	java/lang/Exception
      //   3275	3281	1753	java/lang/Exception
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
      //   2384	2421	1797	java/io/IOException
      //   2423	2464	1797	java/io/IOException
      //   2471	2512	1797	java/io/IOException
      //   2515	2556	1797	java/io/IOException
      //   2559	2600	1797	java/io/IOException
      //   2603	2644	1797	java/io/IOException
      //   2647	2679	1797	java/io/IOException
      //   2684	2711	1797	java/io/IOException
      //   2711	2736	1797	java/io/IOException
      //   2736	2757	1797	java/io/IOException
      //   2757	2824	1797	java/io/IOException
      //   2824	2872	1797	java/io/IOException
      //   2874	2910	1797	java/io/IOException
      //   2915	2920	1797	java/io/IOException
      //   2920	2970	1797	java/io/IOException
      //   2973	2979	1797	java/io/IOException
      //   3275	3281	1797	java/io/IOException
      //   3294	3318	1797	java/io/IOException
      //   851	894	1802	org/json/JSONException
      //   894	916	1813	org/json/JSONException
      //   1598	1635	2365	java/lang/Exception
      //   1637	1687	2365	java/lang/Exception
      //   2321	2362	2365	java/lang/Exception
      //   2471	2512	2365	java/lang/Exception
      //   2515	2556	2365	java/lang/Exception
      //   2559	2600	2365	java/lang/Exception
      //   2603	2644	2365	java/lang/Exception
      //   2384	2421	2682	java/lang/Exception
      //   2423	2464	2682	java/lang/Exception
      //   2647	2679	2682	java/lang/Exception
      //   2920	2970	2971	java/lang/Exception
      //   2757	2824	3273	java/lang/Exception
      //   603	624	3319	java/lang/Exception
      //   638	648	3319	java/lang/Exception
      //   659	685	3319	java/lang/Exception
      //   698	756	3319	java/lang/Exception
      //   1755	1796	3319	java/lang/Exception
      //   3294	3318	3319	java/lang/Exception
      //   3435	3441	3448	java/lang/InterruptedException
      //   3488	3494	3501	java/lang/InterruptedException
      //   3535	3541	3548	java/lang/InterruptedException
      //   154	203	3586	java/lang/Exception
      //   203	214	3586	java/lang/Exception
      //   214	460	3586	java/lang/Exception
      //   460	467	3586	java/lang/Exception
      //   472	480	3586	java/lang/Exception
      //   558	588	3586	java/lang/Exception
      //   588	599	3586	java/lang/Exception
      //   1696	1723	3586	java/lang/Exception
      //   2824	2872	3591	java/lang/Exception
      //   2874	2910	3591	java/lang/Exception
      //   2915	2920	3591	java/lang/Exception
      //   2757	2824	3596	org/json/JSONException
      //   2711	2736	3601	java/lang/Exception
      //   1520	1535	3606	java/lang/Exception
      //   1540	1569	3606	java/lang/Exception
      //   1571	1598	3606	java/lang/Exception
      //   2285	2314	3606	java/lang/Exception
      //   1442	1457	3611	java/lang/Exception
      //   1462	1491	3611	java/lang/Exception
      //   1493	1520	3611	java/lang/Exception
      //   2241	2270	3611	java/lang/Exception
      //   1362	1377	3616	java/lang/Exception
      //   1383	1412	3616	java/lang/Exception
      //   1415	1442	3616	java/lang/Exception
      //   2197	2226	3616	java/lang/Exception
      //   965	1026	3621	java/lang/Exception
      //   916	965	3626	java/lang/Exception
      //   1026	1086	3636	java/lang/Exception
      //   1824	1869	3636	java/lang/Exception
      //   1872	1897	3636	java/lang/Exception
      //   1086	1138	3641	java/lang/Exception
      //   1144	1173	3641	java/lang/Exception
      //   1173	1178	3641	java/lang/Exception
      //   1907	1943	3641	java/lang/Exception
      //   1946	1984	3641	java/lang/Exception
      //   1178	1221	3646	java/lang/Exception
      //   1225	1277	3646	java/lang/Exception
      //   1994	2046	3646	java/lang/Exception
      //   2049	2104	3646	java/lang/Exception
      //   1277	1323	3651	java/lang/Exception
      //   1328	1362	3651	java/lang/Exception
      //   2113	2148	3651	java/lang/Exception
      //   2151	2185	3651	java/lang/Exception
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
        //   245: invokestatic 142	com/kochava/android/tracker/Feature:access$2800	()Ljava/lang/String;
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
        //   517: invokestatic 221	com/kochava/android/tracker/Feature:access$2400	(Ljava/lang/Exception;)V
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
        //   763: invokestatic 248	com/kochava/android/tracker/Feature:access$2900	()Landroid/content/SharedPreferences;
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
        //   798: invokestatic 267	com/kochava/android/tracker/Feature:access$3000	()Landroid/os/Handler;
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
        //   887: invokestatic 267	com/kochava/android/tracker/Feature:access$3000	()Landroid/os/Handler;
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
        //   1020: invokestatic 221	com/kochava/android/tracker/Feature:access$2400	(Ljava/lang/Exception;)V
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
            localJSONObject1.put("kochava_device_id", Feature.access$2800());
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
          Feature.access$4302(false);
        }
      }, 60000L);
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
    //   17: ldc 108
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1131	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1136 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1139 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1142 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 108
    //   75: invokeinterface 1146 2 0
    //   80: invokeinterface 1147 2 0
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
    //   99: invokeinterface 1139 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1142 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 691	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1149
    //   130: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1139 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1142 1 0
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
    //   190: invokeinterface 1139 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1142 1 0
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
          Feature.access$2302(true);
          Object localObject2 = this.val$e.getMessage();
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("message", localObject2);
          ((JSONObject)localObject1).put("os_version", Feature.access$3400());
          ((JSONObject)localObject1).put("device", Feature.access$5300() + "-" + Feature.access$5400());
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$2800());
          ((JSONObject)localObject2).put("action", "error");
          ((JSONObject)localObject2).put("data", localObject1);
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20160222" + Feature.versionExtension);
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
    //   1: ifnull +2407 -> 2408
    //   4: aload_1
    //   5: invokevirtual 1318	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 382	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   15: ldc -61
    //   17: new 691	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 1320
    //   27: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: getstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   33: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokestatic 1326	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   42: pop
    //   43: aload_0
    //   44: new 824	java/util/Timer
    //   47: dup
    //   48: invokespecial 1327	java/util/Timer:<init>	()V
    //   51: putfield 849	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   54: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   57: ldc -33
    //   59: iconst_0
    //   60: invokevirtual 726	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   63: putstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   66: new 1043	com/kochava/android/tracker/DbAdapter
    //   69: dup
    //   70: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   73: invokespecial 1328	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   76: putstatic 610	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   79: aload_3
    //   80: ifnull +231 -> 311
    //   83: aload_3
    //   84: ldc_w 1330
    //   87: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   90: ifnull +44 -> 134
    //   93: aload_3
    //   94: ldc_w 1330
    //   97: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   100: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   103: ldc_w 880
    //   106: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   109: ifeq +25 -> 134
    //   112: aload_3
    //   113: ldc_w 1330
    //   116: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   119: checkcast 880	java/lang/Boolean
    //   122: invokevirtual 883	java/lang/Boolean:booleanValue	()Z
    //   125: istore_2
    //   126: iload_2
    //   127: invokestatic 1336	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   130: iload_2
    //   131: invokestatic 1339	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   134: aload_3
    //   135: ldc_w 1341
    //   138: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   141: ifnull +35 -> 176
    //   144: aload_3
    //   145: ldc_w 1341
    //   148: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: ldc_w 480
    //   157: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   160: ifeq +16 -> 176
    //   163: aload_3
    //   164: ldc_w 1341
    //   167: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 480	java/lang/String
    //   173: putstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   176: aload_3
    //   177: ldc_w 1343
    //   180: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: ifnull +38 -> 221
    //   186: aload_3
    //   187: ldc_w 1343
    //   190: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   196: ldc_w 880
    //   199: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   202: ifeq +19 -> 221
    //   205: aload_3
    //   206: ldc_w 1343
    //   209: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   212: checkcast 880	java/lang/Boolean
    //   215: invokevirtual 883	java/lang/Boolean:booleanValue	()Z
    //   218: putstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   221: aload_3
    //   222: ldc_w 1345
    //   225: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   228: ifnull +38 -> 266
    //   231: aload_3
    //   232: ldc_w 1345
    //   235: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: ldc_w 880
    //   244: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   247: ifeq +19 -> 266
    //   250: aload_3
    //   251: ldc_w 1345
    //   254: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   257: checkcast 880	java/lang/Boolean
    //   260: invokevirtual 883	java/lang/Boolean:booleanValue	()Z
    //   263: putstatic 380	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   266: aload_3
    //   267: ldc_w 1347
    //   270: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   273: ifnull +38 -> 311
    //   276: aload_3
    //   277: ldc_w 1347
    //   280: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   283: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   286: ldc_w 880
    //   289: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +19 -> 311
    //   295: aload_3
    //   296: ldc_w 1347
    //   299: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   302: checkcast 880	java/lang/Boolean
    //   305: invokevirtual 883	java/lang/Boolean:booleanValue	()Z
    //   308: putstatic 397	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   311: aload_0
    //   312: invokespecial 1349	com/kochava/android/tracker/Feature:initHandler	()V
    //   315: new 691	java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   322: ldc_w 1351
    //   325: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   331: ldc -128
    //   333: ldc_w 353
    //   336: invokeinterface 732 3 0
    //   341: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   350: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   353: ldc -128
    //   355: ldc_w 353
    //   358: invokeinterface 732 3 0
    //   363: ldc_w 353
    //   366: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   369: ifne +26 -> 395
    //   372: new 440	org/json/JSONArray
    //   375: dup
    //   376: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   379: ldc -128
    //   381: ldc_w 353
    //   384: invokeinterface 732 3 0
    //   389: invokespecial 1352	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   392: putstatic 443	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   395: getstatic 813	android/os/Build$VERSION:SDK_INT	I
    //   398: bipush 14
    //   400: if_icmplt +2049 -> 2449
    //   403: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   406: ifne +2043 -> 2449
    //   409: new 691	java/lang/StringBuilder
    //   412: dup
    //   413: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   416: ldc_w 1354
    //   419: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   422: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   425: invokevirtual 805	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   428: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   431: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   434: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   437: checkcast 1356	android/app/Application
    //   440: new 67	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   443: dup
    //   444: aload_0
    //   445: invokespecial 1357	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   448: invokevirtual 1361	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   451: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   454: new 70	com/kochava/android/tracker/Feature$MemoryBoss
    //   457: dup
    //   458: aload_0
    //   459: invokespecial 1362	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   462: invokevirtual 1366	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   465: iconst_1
    //   466: putstatic 1369	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   469: iconst_1
    //   470: putstatic 1372	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   473: new 42	com/kochava/android/tracker/Feature$3
    //   476: dup
    //   477: aload_0
    //   478: invokespecial 1373	com/kochava/android/tracker/Feature$3:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   481: invokevirtual 843	java/lang/Thread:start	()V
    //   484: getstatic 380	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   487: ifne +14 -> 501
    //   490: new 44	com/kochava/android/tracker/Feature$4
    //   493: dup
    //   494: aload_0
    //   495: invokespecial 1374	com/kochava/android/tracker/Feature$4:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   498: invokevirtual 843	java/lang/Thread:start	()V
    //   501: aload_0
    //   502: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   505: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   508: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   511: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   514: iconst_0
    //   515: invokevirtual 1387	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   518: getfield 1392	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   521: putfield 1116	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   524: aload_0
    //   525: new 711	org/json/JSONObject
    //   528: dup
    //   529: invokespecial 859	org/json/JSONObject:<init>	()V
    //   532: putfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   535: aload_0
    //   536: new 711	org/json/JSONObject
    //   539: dup
    //   540: invokespecial 859	org/json/JSONObject:<init>	()V
    //   543: putfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   546: aload_0
    //   547: new 711	org/json/JSONObject
    //   550: dup
    //   551: invokespecial 859	org/json/JSONObject:<init>	()V
    //   554: putfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   557: aconst_null
    //   558: astore 13
    //   560: aconst_null
    //   561: astore 6
    //   563: aconst_null
    //   564: astore 9
    //   566: aconst_null
    //   567: astore 7
    //   569: ldc_w 870
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
    //   597: ldc_w 1400
    //   600: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   603: ifnull +38 -> 641
    //   606: aload 6
    //   608: astore 5
    //   610: aload_3
    //   611: ldc_w 1400
    //   614: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   617: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   620: ldc_w 480
    //   623: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   626: ifeq +15 -> 641
    //   629: aload_3
    //   630: ldc_w 1400
    //   633: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   636: checkcast 480	java/lang/String
    //   639: astore 5
    //   641: aload 7
    //   643: astore 6
    //   645: aload_3
    //   646: ldc -93
    //   648: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   651: ifnull +36 -> 687
    //   654: aload 7
    //   656: astore 6
    //   658: aload_3
    //   659: ldc -93
    //   661: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   664: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   667: ldc_w 480
    //   670: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   673: ifeq +14 -> 687
    //   676: aload_3
    //   677: ldc -93
    //   679: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   682: checkcast 480	java/lang/String
    //   685: astore 6
    //   687: aload_1
    //   688: astore 7
    //   690: aload_3
    //   691: ldc -48
    //   693: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   696: ifnull +35 -> 731
    //   699: aload_1
    //   700: astore 7
    //   702: aload_3
    //   703: ldc -48
    //   705: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   708: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   711: ldc_w 480
    //   714: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   717: ifeq +14 -> 731
    //   720: aload_3
    //   721: ldc -48
    //   723: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   726: checkcast 480	java/lang/String
    //   729: astore 7
    //   731: aload 8
    //   733: astore_1
    //   734: aload_3
    //   735: ldc_w 1402
    //   738: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   741: ifnull +33 -> 774
    //   744: aload_3
    //   745: ldc_w 1402
    //   748: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   751: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   754: ldc_w 480
    //   757: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   760: ifeq +1717 -> 2477
    //   763: aload_3
    //   764: ldc_w 1402
    //   767: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   770: checkcast 480	java/lang/String
    //   773: astore_1
    //   774: aload_3
    //   775: ldc_w 1404
    //   778: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   781: ifnull +34 -> 815
    //   784: aload_3
    //   785: ldc_w 1404
    //   788: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   791: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   794: ldc_w 480
    //   797: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   800: ifeq +15 -> 815
    //   803: aload_3
    //   804: ldc_w 1404
    //   807: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   810: checkcast 480	java/lang/String
    //   813: astore 8
    //   815: aload 14
    //   817: astore 8
    //   819: aload_3
    //   820: ldc_w 1406
    //   823: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   826: ifnull +38 -> 864
    //   829: aload 14
    //   831: astore 8
    //   833: aload_3
    //   834: ldc_w 1406
    //   837: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   843: ldc_w 480
    //   846: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   849: ifeq +15 -> 864
    //   852: aload_3
    //   853: ldc_w 1406
    //   856: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   859: checkcast 480	java/lang/String
    //   862: astore 8
    //   864: aload_3
    //   865: ldc_w 956
    //   868: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   871: ifnull +39 -> 910
    //   874: aload_3
    //   875: ldc_w 956
    //   878: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   881: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   884: ldc_w 880
    //   887: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   890: ifeq +20 -> 910
    //   893: aload_0
    //   894: aload_3
    //   895: ldc_w 956
    //   898: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   901: checkcast 880	java/lang/Boolean
    //   904: invokevirtual 883	java/lang/Boolean:booleanValue	()Z
    //   907: putfield 454	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   910: aload_3
    //   911: ldc_w 974
    //   914: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   917: ifnull +115 -> 1032
    //   920: aload_3
    //   921: ldc_w 974
    //   924: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   927: instanceof 477
    //   930: ifeq +102 -> 1032
    //   933: aload_3
    //   934: ldc_w 974
    //   937: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   940: checkcast 477	java/util/HashMap
    //   943: putstatic 1408	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   946: new 711	org/json/JSONObject
    //   949: dup
    //   950: invokespecial 859	org/json/JSONObject:<init>	()V
    //   953: putstatic 972	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   956: getstatic 1408	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   959: invokeinterface 1026 1 0
    //   964: invokeinterface 1029 1 0
    //   969: astore 9
    //   971: aload 9
    //   973: invokeinterface 761 1 0
    //   978: ifeq +54 -> 1032
    //   981: aload 9
    //   983: invokeinterface 765 1 0
    //   988: checkcast 1031	java/util/Map$Entry
    //   991: astore 10
    //   993: getstatic 972	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   996: aload 10
    //   998: invokeinterface 1034 1 0
    //   1003: checkcast 480	java/lang/String
    //   1006: aload 10
    //   1008: invokeinterface 1037 1 0
    //   1013: checkcast 480	java/lang/String
    //   1016: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1019: pop
    //   1020: aload 9
    //   1022: invokeinterface 1411 1 0
    //   1027: goto -56 -> 971
    //   1030: astore 9
    //   1032: aload_3
    //   1033: ldc_w 980
    //   1036: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1039: ifnull +36 -> 1075
    //   1042: aload_3
    //   1043: ldc_w 980
    //   1046: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1049: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1052: ldc_w 480
    //   1055: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1058: ifeq +17 -> 1075
    //   1061: aload_0
    //   1062: aload_3
    //   1063: ldc_w 980
    //   1066: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1069: checkcast 480	java/lang/String
    //   1072: putfield 976	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
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
    //   1095: ldc_w 1412
    //   1098: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1124: ldc_w 1412
    //   1127: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1130: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1133: ldc_w 1414
    //   1136: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1139: ifeq +77 -> 1216
    //   1142: aload_3
    //   1143: ldc_w 1412
    //   1146: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1149: checkcast 1414	java/lang/Integer
    //   1152: invokevirtual 1417	java/lang/Integer:intValue	()I
    //   1155: istore 4
    //   1157: iload 4
    //   1159: iconst_1
    //   1160: if_icmpge +1364 -> 2524
    //   1163: new 691	java/lang/StringBuilder
    //   1166: dup
    //   1167: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1170: ldc_w 1419
    //   1173: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: iload 4
    //   1178: invokevirtual 1080	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1181: ldc_w 1421
    //   1184: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1187: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1190: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
    //   1244: ldc -3
    //   1246: invokevirtual 1424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1249: ifeq +7 -> 1256
    //   1252: iconst_1
    //   1253: putstatic 411	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1256: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1259: ldc 114
    //   1261: iconst_0
    //   1262: invokevirtual 726	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1265: putstatic 574	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1268: aload 9
    //   1270: ifnull +1346 -> 2616
    //   1273: aload 9
    //   1275: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   1278: invokevirtual 488	java/lang/String:length	()I
    //   1281: ifeq +1335 -> 2616
    //   1284: aload 9
    //   1286: putstatic 683	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1289: aload_0
    //   1290: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1293: ldc -93
    //   1295: aload 9
    //   1297: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1300: pop
    //   1301: aload_0
    //   1302: getfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1305: ldc -93
    //   1307: aload 9
    //   1309: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1312: pop
    //   1313: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1316: ldc -51
    //   1318: ldc_w 353
    //   1321: invokeinterface 732 3 0
    //   1326: ldc_w 353
    //   1329: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1332: ifeq +25 -> 1357
    //   1335: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1338: invokeinterface 1215 1 0
    //   1343: ldc -51
    //   1345: aload 9
    //   1347: invokeinterface 1221 3 0
    //   1352: invokeinterface 1224 1 0
    //   1357: aload_0
    //   1358: aload 12
    //   1360: invokespecial 517	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1363: aload_0
    //   1364: getfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1367: ldc_w 1426
    //   1370: aload_0
    //   1371: invokespecial 947	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1374: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1377: pop
    //   1378: aload_0
    //   1379: getfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1382: ldc_w 1428
    //   1385: ldc_w 1430
    //   1388: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1391: pop
    //   1392: aload_0
    //   1393: getfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1396: ldc_w 1432
    //   1399: ldc_w 1434
    //   1402: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1405: pop
    //   1406: aload_0
    //   1407: getfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1410: ldc -48
    //   1412: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1415: ldc -48
    //   1417: ldc_w 870
    //   1420: invokeinterface 732 3 0
    //   1425: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1428: pop
    //   1429: aload_0
    //   1430: getfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1433: ldc -48
    //   1435: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1438: ldc -48
    //   1440: ldc_w 870
    //   1443: invokeinterface 732 3 0
    //   1448: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1451: pop
    //   1452: aload_0
    //   1453: getfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1456: ldc_w 1432
    //   1459: ldc_w 1434
    //   1462: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1465: pop
    //   1466: aload_0
    //   1467: getfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1470: ldc -48
    //   1472: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1475: ldc -48
    //   1477: ldc_w 870
    //   1480: invokeinterface 732 3 0
    //   1485: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1488: pop
    //   1489: aload_0
    //   1490: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1493: ldc_w 913
    //   1496: new 691	java/lang/StringBuilder
    //   1499: dup
    //   1500: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1503: ldc_w 915
    //   1506: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: getstatic 357	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1512: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1515: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1518: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1521: pop
    //   1522: aload_0
    //   1523: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1526: ldc_w 1436
    //   1529: ldc_w 1438
    //   1532: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1535: pop
    //   1536: aload_0
    //   1537: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1540: ldc_w 1039
    //   1543: aload_0
    //   1544: getfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1547: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1550: pop
    //   1551: aload_0
    //   1552: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1555: ldc_w 1440
    //   1558: aload_0
    //   1559: getfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1562: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1565: pop
    //   1566: aload_0
    //   1567: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1570: ldc 90
    //   1572: ldc -101
    //   1574: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1577: pop
    //   1578: invokestatic 697	java/lang/System:currentTimeMillis	()J
    //   1581: ldc2_w 698
    //   1584: ldiv
    //   1585: putstatic 388	com/kochava/android/tracker/Feature:startTime	J
    //   1588: iconst_0
    //   1589: istore 4
    //   1591: ldc_w 353
    //   1594: astore_3
    //   1595: new 772	android/content/ComponentName
    //   1598: dup
    //   1599: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1602: ldc_w 1442
    //   1605: invokespecial 784	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1608: astore_1
    //   1609: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1612: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1615: aload_1
    //   1616: iconst_0
    //   1617: invokevirtual 1446	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1620: pop
    //   1621: ldc_w 1448
    //   1624: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1627: aload_3
    //   1628: astore_1
    //   1629: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1632: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1635: ldc_w 1450
    //   1638: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1641: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1644: invokevirtual 1453	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1647: ifge +27 -> 1674
    //   1650: iconst_1
    //   1651: istore 4
    //   1653: new 691	java/lang/StringBuilder
    //   1656: dup
    //   1657: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1660: aload_3
    //   1661: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1664: ldc_w 1455
    //   1667: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1670: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1673: astore_1
    //   1674: aload_1
    //   1675: astore_3
    //   1676: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1679: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1682: ldc_w 1457
    //   1685: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1688: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1691: invokevirtual 1453	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1694: ifge +27 -> 1721
    //   1697: iconst_1
    //   1698: istore 4
    //   1700: new 691	java/lang/StringBuilder
    //   1703: dup
    //   1704: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1707: aload_1
    //   1708: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1711: ldc_w 1459
    //   1714: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1717: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1720: astore_3
    //   1721: aload_3
    //   1722: astore_1
    //   1723: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1726: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1729: ldc_w 1461
    //   1732: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1735: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1738: invokevirtual 1453	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1741: ifge +27 -> 1768
    //   1744: iconst_1
    //   1745: istore 4
    //   1747: new 691	java/lang/StringBuilder
    //   1750: dup
    //   1751: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1754: aload_3
    //   1755: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1758: ldc_w 1463
    //   1761: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1764: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1767: astore_1
    //   1768: iload 4
    //   1770: ifeq +13 -> 1783
    //   1773: ldc_w 1465
    //   1776: invokestatic 1468	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1779: aload_1
    //   1780: invokestatic 1468	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1783: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1786: ldc_w 1470
    //   1789: invokevirtual 996	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1792: checkcast 1472	android/telephony/TelephonyManager
    //   1795: invokevirtual 1475	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1798: putstatic 927	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1801: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1804: ldc_w 1477
    //   1807: invokevirtual 996	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1810: checkcast 1479	android/net/wifi/WifiManager
    //   1813: invokevirtual 1483	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1816: invokevirtual 1488	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1819: putstatic 923	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1822: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1825: ldc -27
    //   1827: ldc_w 353
    //   1830: invokeinterface 732 3 0
    //   1835: ldc_w 353
    //   1838: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1841: ifeq +27 -> 1868
    //   1844: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1847: invokeinterface 1215 1 0
    //   1852: ldc -27
    //   1854: aload_0
    //   1855: invokespecial 596	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1858: invokeinterface 1221 3 0
    //   1863: invokeinterface 1224 1 0
    //   1868: aload_0
    //   1869: invokestatic 677	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1872: putfield 1490	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1875: aload_0
    //   1876: invokestatic 673	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1879: putfield 1492	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1882: aload_0
    //   1883: ldc_w 1494
    //   1886: putfield 1120	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1889: aload_0
    //   1890: ldc_w 1496
    //   1893: putfield 1124	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1896: aload_0
    //   1897: ldc_w 353
    //   1900: putfield 585	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1903: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1906: invokevirtual 1318	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1909: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1912: astore_3
    //   1913: aload_3
    //   1914: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1917: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1920: iconst_0
    //   1921: invokevirtual 1500	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1924: astore_1
    //   1925: aload_1
    //   1926: ifnull +1033 -> 2959
    //   1929: aload_3
    //   1930: aload_1
    //   1931: invokevirtual 1504	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1934: astore_1
    //   1935: aload_0
    //   1936: aload_1
    //   1937: checkcast 480	java/lang/String
    //   1940: checkcast 480	java/lang/String
    //   1943: putfield 1120	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1946: new 691	java/lang/StringBuilder
    //   1949: dup
    //   1950: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1953: ldc_w 1506
    //   1956: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1959: aload_0
    //   1960: getfield 1120	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1963: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1966: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1969: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1972: aload_0
    //   1973: new 691	java/lang/StringBuilder
    //   1976: dup
    //   1977: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   1980: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1983: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1986: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1989: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1992: iconst_0
    //   1993: invokevirtual 1387	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1996: getfield 1509	android/content/pm/PackageInfo:versionCode	I
    //   1999: invokevirtual 1080	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2002: ldc_w 353
    //   2005: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2008: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2011: putfield 1124	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2014: new 691	java/lang/StringBuilder
    //   2017: dup
    //   2018: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2021: ldc_w 1511
    //   2024: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2027: aload_0
    //   2028: getfield 1124	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2031: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2034: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2037: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2040: aload_0
    //   2041: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2044: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2047: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2050: invokevirtual 1381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2053: iconst_0
    //   2054: invokevirtual 1387	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2057: getfield 1514	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2060: putfield 585	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2063: new 691	java/lang/StringBuilder
    //   2066: dup
    //   2067: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2070: ldc_w 1516
    //   2073: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2076: aload_0
    //   2077: getfield 585	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2080: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2083: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2086: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2089: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2092: ldc_w 992
    //   2095: invokevirtual 996	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2098: checkcast 998	android/view/WindowManager
    //   2101: astore_1
    //   2102: aload_0
    //   2103: aload_1
    //   2104: invokeinterface 1005 1 0
    //   2109: invokevirtual 1519	android/view/Display:getHeight	()I
    //   2112: putfield 938	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2115: aload_0
    //   2116: aload_1
    //   2117: invokeinterface 1005 1 0
    //   2122: invokevirtual 1522	android/view/Display:getWidth	()I
    //   2125: putfield 942	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2128: new 691	java/lang/StringBuilder
    //   2131: dup
    //   2132: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2135: ldc_w 1524
    //   2138: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2141: aload_0
    //   2142: getfield 938	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2145: invokevirtual 1080	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2148: ldc_w 1526
    //   2151: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2154: aload_0
    //   2155: getfield 942	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2158: invokevirtual 1080	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2161: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2164: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2167: new 46	com/kochava/android/tracker/Feature$5
    //   2170: dup
    //   2171: aload_0
    //   2172: invokespecial 1527	com/kochava/android/tracker/Feature$5:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   2175: invokevirtual 843	java/lang/Thread:start	()V
    //   2178: aload_0
    //   2179: invokestatic 570	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2182: putfield 1529	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2185: aload_0
    //   2186: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2189: ldc_w 861
    //   2192: invokestatic 570	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2195: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2198: pop
    //   2199: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2202: ldc -45
    //   2204: ldc_w 353
    //   2207: invokeinterface 732 3 0
    //   2212: ldc -3
    //   2214: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2217: ifne +149 -> 2366
    //   2220: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2223: invokevirtual 1378	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2226: astore 5
    //   2228: aload 5
    //   2230: sipush 128
    //   2233: invokevirtual 1533	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2236: invokeinterface 888 1 0
    //   2241: astore 6
    //   2243: aload 6
    //   2245: invokeinterface 761 1 0
    //   2250: ifeq +116 -> 2366
    //   2253: aload 6
    //   2255: invokeinterface 765 1 0
    //   2260: checkcast 1535	android/content/pm/ApplicationInfo
    //   2263: astore 7
    //   2265: aload 7
    //   2267: invokestatic 1539	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2270: istore_2
    //   2271: iload_2
    //   2272: ifne -29 -> 2243
    //   2275: aconst_null
    //   2276: astore_1
    //   2277: aload 5
    //   2279: aload 7
    //   2281: getfield 1540	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2284: iconst_0
    //   2285: invokevirtual 1387	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2288: astore_3
    //   2289: aload_3
    //   2290: astore_1
    //   2291: aload_1
    //   2292: ifnull -49 -> 2243
    //   2295: getstatic 395	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2298: new 61	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2301: dup
    //   2302: aload_0
    //   2303: aload_1
    //   2304: getfield 1392	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2307: new 691	java/lang/StringBuilder
    //   2310: dup
    //   2311: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2314: aload_1
    //   2315: getfield 1543	android/content/pm/PackageInfo:firstInstallTime	J
    //   2318: invokevirtual 703	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2321: ldc_w 353
    //   2324: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2327: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2330: new 691	java/lang/StringBuilder
    //   2333: dup
    //   2334: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2337: aload_1
    //   2338: getfield 1546	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2341: invokevirtual 703	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2344: ldc_w 353
    //   2347: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2350: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2353: invokespecial 1549	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2356: invokeinterface 1552 2 0
    //   2361: pop
    //   2362: goto -119 -> 2243
    //   2365: astore_1
    //   2366: getstatic 425	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2369: aload_0
    //   2370: getfield 461	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2373: ldc2_w 1553
    //   2376: getstatic 1560	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2379: invokeinterface 1565 5 0
    //   2384: pop
    //   2385: aload_0
    //   2386: ldc -101
    //   2388: invokevirtual 1568	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2391: return
    //   2392: astore_1
    //   2393: ldc_w 1570
    //   2396: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2399: aload_1
    //   2400: invokevirtual 1020	java/lang/Exception:printStackTrace	()V
    //   2403: iconst_1
    //   2404: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2407: return
    //   2408: ldc_w 1572
    //   2411: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2414: iconst_1
    //   2415: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2418: return
    //   2419: astore_1
    //   2420: new 691	java/lang/StringBuilder
    //   2423: dup
    //   2424: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2427: ldc_w 1574
    //   2430: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2433: aload_1
    //   2434: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   2437: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2440: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2443: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2446: goto -2051 -> 395
    //   2449: new 691	java/lang/StringBuilder
    //   2452: dup
    //   2453: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2456: ldc_w 1576
    //   2459: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2462: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2465: invokevirtual 805	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2468: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2471: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2474: goto -2001 -> 473
    //   2477: aload 8
    //   2479: astore_1
    //   2480: aload_3
    //   2481: ldc_w 1402
    //   2484: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2487: invokevirtual 1334	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2490: ldc_w 880
    //   2493: invokevirtual 1055	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2496: ifeq -1722 -> 774
    //   2499: aload 8
    //   2501: astore_1
    //   2502: aload_3
    //   2503: ldc_w 1402
    //   2506: invokevirtual 878	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2509: checkcast 880	java/lang/Boolean
    //   2512: invokevirtual 883	java/lang/Boolean:booleanValue	()Z
    //   2515: ifeq -1741 -> 774
    //   2518: ldc -3
    //   2520: astore_1
    //   2521: goto -1747 -> 774
    //   2524: iload 4
    //   2526: sipush 360
    //   2529: if_icmple +42 -> 2571
    //   2532: new 691	java/lang/StringBuilder
    //   2535: dup
    //   2536: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2539: ldc_w 1419
    //   2542: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2545: iload 4
    //   2547: invokevirtual 1080	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2550: ldc_w 1578
    //   2553: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2556: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2559: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2562: ldc_w 1579
    //   2565: putstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   2568: goto -1375 -> 1193
    //   2571: iload 4
    //   2573: bipush 60
    //   2575: imul
    //   2576: sipush 1000
    //   2579: imul
    //   2580: putstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   2583: new 691	java/lang/StringBuilder
    //   2586: dup
    //   2587: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2590: ldc_w 1581
    //   2593: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2596: iload 4
    //   2598: invokevirtual 1080	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2601: ldc_w 1583
    //   2604: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2607: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2610: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2613: goto -1420 -> 1193
    //   2616: aload 13
    //   2618: ifnull +176 -> 2794
    //   2621: aload 13
    //   2623: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   2626: invokevirtual 488	java/lang/String:length	()I
    //   2629: ifeq +165 -> 2794
    //   2632: aload_0
    //   2633: getfield 1394	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2636: ldc_w 1400
    //   2639: aload 13
    //   2641: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2644: pop
    //   2645: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2648: ldc -51
    //   2650: ldc_w 353
    //   2653: invokeinterface 732 3 0
    //   2658: ldc_w 353
    //   2661: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2664: ifeq +25 -> 2689
    //   2667: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2670: invokeinterface 1215 1 0
    //   2675: ldc -51
    //   2677: aload 13
    //   2679: invokeinterface 1221 3 0
    //   2684: invokeinterface 1224 1 0
    //   2689: aload 9
    //   2691: ifnull +76 -> 2767
    //   2694: aload 9
    //   2696: invokevirtual 484	java/lang/String:trim	()Ljava/lang/String;
    //   2699: invokevirtual 488	java/lang/String:length	()I
    //   2702: ifeq +65 -> 2767
    //   2705: aload 9
    //   2707: putstatic 683	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2710: aload_0
    //   2711: getfield 1396	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2714: ldc -93
    //   2716: aload 9
    //   2718: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2721: pop
    //   2722: aload_0
    //   2723: getfield 1398	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2726: ldc -93
    //   2728: aload 9
    //   2730: invokevirtual 714	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2733: pop
    //   2734: goto -1377 -> 1357
    //   2737: astore_1
    //   2738: new 691	java/lang/StringBuilder
    //   2741: dup
    //   2742: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2745: ldc_w 1585
    //   2748: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2751: aload_1
    //   2752: invokevirtual 1586	org/json/JSONException:toString	()Ljava/lang/String;
    //   2755: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2758: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2761: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2764: goto -1186 -> 1578
    //   2767: new 691	java/lang/StringBuilder
    //   2770: dup
    //   2771: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2774: ldc_w 1588
    //   2777: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2780: aload 13
    //   2782: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2785: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2788: putstatic 683	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2791: goto -1434 -> 1357
    //   2794: ldc_w 1590
    //   2797: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2800: iconst_1
    //   2801: putstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   2804: return
    //   2805: astore_1
    //   2806: iconst_1
    //   2807: istore 4
    //   2809: new 691	java/lang/StringBuilder
    //   2812: dup
    //   2813: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2816: ldc_w 353
    //   2819: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2822: ldc_w 1592
    //   2825: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2828: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2831: astore_3
    //   2832: goto -1205 -> 1627
    //   2835: astore_1
    //   2836: new 691	java/lang/StringBuilder
    //   2839: dup
    //   2840: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2843: ldc_w 1594
    //   2846: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2849: aload_1
    //   2850: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   2853: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2856: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2859: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2862: goto -1061 -> 1801
    //   2865: astore_1
    //   2866: new 691	java/lang/StringBuilder
    //   2869: dup
    //   2870: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2873: ldc_w 1596
    //   2876: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2879: aload_1
    //   2880: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   2883: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2886: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2889: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2892: goto -1070 -> 1822
    //   2895: astore 5
    //   2897: aconst_null
    //   2898: astore_1
    //   2899: new 691	java/lang/StringBuilder
    //   2902: dup
    //   2903: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2906: ldc_w 1598
    //   2909: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2912: aload 5
    //   2914: invokevirtual 1599	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2917: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2920: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2923: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2926: goto -1001 -> 1925
    //   2929: astore_1
    //   2930: new 691	java/lang/StringBuilder
    //   2933: dup
    //   2934: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2937: ldc_w 1598
    //   2940: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2943: aload_1
    //   2944: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   2947: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2950: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2953: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2956: goto -984 -> 1972
    //   2959: ldc_w 1601
    //   2962: astore_1
    //   2963: goto -1028 -> 1935
    //   2966: astore_1
    //   2967: new 691	java/lang/StringBuilder
    //   2970: dup
    //   2971: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   2974: ldc_w 1603
    //   2977: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2980: aload_1
    //   2981: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   2984: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2987: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2990: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2993: goto -953 -> 2040
    //   2996: astore_1
    //   2997: new 691	java/lang/StringBuilder
    //   3000: dup
    //   3001: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   3004: ldc_w 1605
    //   3007: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3010: aload_1
    //   3011: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   3014: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3017: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3020: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3023: goto -934 -> 2089
    //   3026: astore_1
    //   3027: new 691	java/lang/StringBuilder
    //   3030: dup
    //   3031: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   3034: ldc_w 1607
    //   3037: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3040: aload_1
    //   3041: invokevirtual 1106	java/lang/Exception:toString	()Ljava/lang/String;
    //   3044: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3047: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3050: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3053: goto -886 -> 2167
    //   3056: astore_1
    //   3057: getstatic 906	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3060: ifeq -861 -> 2199
    //   3063: aload_1
    //   3064: invokevirtual 909	org/json/JSONException:printStackTrace	()V
    //   3067: goto -868 -> 2199
    //   3070: astore_3
    //   3071: new 691	java/lang/StringBuilder
    //   3074: dup
    //   3075: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   3078: ldc_w 1609
    //   3081: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3084: aload 7
    //   3086: getfield 1540	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3089: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3092: ldc_w 1611
    //   3095: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3098: aload_3
    //   3099: invokevirtual 1599	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3102: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3105: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3108: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
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
          Feature.access$4802(Feature.this, new Timer());
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
          ((JSONObject)localObject1).put("kochava_device_id", Feature.access$2800());
          ((JSONObject)localObject1).put("sdk_version", "Android20160222" + Feature.versionExtension);
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
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$2800());
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20160222" + Feature.versionExtension);
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
    //   0: getstatic 813	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +259 -> 264
    //   8: getstatic 359	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +253 -> 264
    //   14: getstatic 405	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +9 -> 26
    //   20: ldc 117
    //   22: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   25: return
    //   26: ldc_w 1836
    //   29: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   32: getstatic 397	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   35: ifne +46 -> 81
    //   38: getstatic 817	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   41: ifnonnull +188 -> 229
    //   44: ldc_w 1838
    //   47: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   50: new 824	java/util/Timer
    //   53: dup
    //   54: invokespecial 1327	java/util/Timer:<init>	()V
    //   57: putstatic 817	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   60: getstatic 817	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   63: new 8	com/kochava/android/tracker/Feature$10
    //   66: dup
    //   67: invokespecial 1839	com/kochava/android/tracker/Feature$10:<init>	()V
    //   70: getstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   73: i2l
    //   74: getstatic 361	com/kochava/android/tracker/Feature:flush_rate	I
    //   77: i2l
    //   78: invokevirtual 1632	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   81: invokestatic 697	java/lang/System:currentTimeMillis	()J
    //   84: ldc2_w 698
    //   87: ldiv
    //   88: putstatic 388	com/kochava/android/tracker/Feature:startTime	J
    //   91: getstatic 384	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   94: ifeq +18 -> 112
    //   97: invokestatic 549	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   100: ifeq +12 -> 112
    //   103: getstatic 720	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   106: invokestatic 1843	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   109: invokevirtual 1846	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   112: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   115: ifnull +50 -> 165
    //   118: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   121: ldc -24
    //   123: ldc_w 353
    //   126: invokeinterface 732 3 0
    //   131: ldc_w 353
    //   134: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 711	org/json/JSONObject
    //   145: dup
    //   146: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc -24
    //   151: ldc_w 353
    //   154: invokeinterface 732 3 0
    //   159: invokespecial 1847	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1849	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +96 -> 264
    //   171: getstatic 616	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc -45
    //   176: ldc_w 353
    //   179: invokeinterface 732 3 0
    //   184: ldc -3
    //   186: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   189: ifeq +75 -> 264
    //   192: getstatic 407	com/kochava/android/tracker/Feature:canSendSession	Z
    //   195: ifeq +63 -> 258
    //   198: ldc -67
    //   200: invokestatic 830	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   203: return
    //   204: astore_1
    //   205: new 691	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 692	java/lang/StringBuilder:<init>	()V
    //   212: ldc_w 1851
    //   215: invokevirtual 706	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual 747	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 709	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   228: return
    //   229: ldc_w 1853
    //   232: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   235: goto -154 -> 81
    //   238: astore_1
    //   239: ldc_w 1855
    //   242: invokestatic 752	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   245: getstatic 906	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   248: ifeq -83 -> 165
    //   251: aload_1
    //   252: invokevirtual 909	org/json/JSONException:printStackTrace	()V
    //   255: goto -90 -> 165
    //   258: ldc_w 836
    //   261: invokestatic 808	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
          localJSONObject1.put("kochava_device_id", Feature.access$2800());
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
          Feature.this.fireEventBlacklist("event", localHashMap);
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
          Logging.Log("Got event " + paramString1);
          HashMap localHashMap = new HashMap();
          localHashMap.put("event_name", paramString1);
          DecimalFormat localDecimalFormat = new DecimalFormat("#.##");
          localHashMap.put("x", Double.valueOf(localDecimalFormat.format(paramDouble1)).toString());
          localHashMap.put("y", Double.valueOf(localDecimalFormat.format(paramDouble3)).toString());
          localHashMap.put("z", Double.valueOf(localDecimalFormat.format(this.val$z)).toString());
          localHashMap.put("event_data", this.val$eventData);
          Feature.this.fireEventBlacklist("spatial", localHashMap);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      Logging.LogError("Error in event call: " + paramString1);
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
        Feature.this.fireEvent("identityLink", paramMap);
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
          Logging.Log("No previous os_version in watchlist, adding " + Feature.access$3400());
          Feature.prefs.edit().putString("os_version", Feature.access$3400()).apply();
        }
        for (;;)
        {
          if (!((HashMap)localObject2).keySet().isEmpty()) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("action", "update");
              localJSONObject.put("kochava_device_id", Feature.access$2800());
              localJSONObject.put("kochava_app_id", Feature.mAppId);
              localJSONObject.put("sdk_version", "Android20160222" + Feature.versionExtension);
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
          if (!Feature.prefs.getString("os_version", "").equals(Feature.access$3400()))
          {
            Logging.Log("os_version changed! Is now " + Feature.access$3400());
            ((HashMap)localObject2).put("os_version", Feature.access$3400());
            Feature.prefs.edit().putString("os_version", Feature.access$3400()).apply();
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
                Feature.access$5100();
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
            Feature.access$5200();
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
      Feature.access$4202(true);
    }
    
    public void onActivityResumed(Activity paramActivity)
    {
      Logging.Log("LifeCycleTracker - Tracking Activity Resumed");
      Feature.AppLifeCycleStatusManager.changeStatus("is_focused");
      Feature.access$4202(false);
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
        Feature.access$4202(true);
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
      Feature.access$5000();
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
