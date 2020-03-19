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
import android.util.Log;
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
          paramAnonymousMessage.put("sdk_version", "Android20151109" + Feature.versionExtension);
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
        catch (JSONException paramAnonymousMessage)
        {
          paramAnonymousMessage.printStackTrace();
        }
      }
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
      //   144: ifle +2991 -> 3135
      //   147: new 87	java/lang/StringBuilder
      //   150: dup
      //   151: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   154: ldc 106
      //   156: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   159: aload_0
      //   160: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   163: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   166: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   169: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   172: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   175: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   178: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   181: ifnull +15 -> 196
      //   184: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   187: invokevirtual 119	java/lang/String:trim	()Ljava/lang/String;
      //   190: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   193: ifeq +14 -> 207
      //   196: ldc 125
      //   198: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   201: ldc 127
      //   203: invokestatic 131	com/kochava/android/tracker/Feature:access$402	(Ljava/lang/String;)Ljava/lang/String;
      //   206: pop
      //   207: new 87	java/lang/StringBuilder
      //   210: dup
      //   211: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   214: ldc -123
      //   216: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   219: ldc -121
      //   221: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   224: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   227: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   230: ldc -119
      //   232: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   235: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   238: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   241: new 139	java/net/URL
      //   244: dup
      //   245: new 87	java/lang/StringBuilder
      //   248: dup
      //   249: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   252: ldc -121
      //   254: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   257: invokestatic 116	com/kochava/android/tracker/Feature:access$400	()Ljava/lang/String;
      //   260: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   263: ldc -119
      //   265: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   268: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   271: invokespecial 141	java/net/URL:<init>	(Ljava/lang/String;)V
      //   274: invokevirtual 145	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   277: checkcast 147	javax/net/ssl/HttpsURLConnection
      //   280: astore 12
      //   282: aload 12
      //   284: ldc -107
      //   286: invokestatic 152	com/kochava/android/tracker/Feature:access$500	()Ljava/lang/String;
      //   289: invokevirtual 156	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   292: aload 12
      //   294: ldc -98
      //   296: ldc -96
      //   298: invokevirtual 156	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   301: aload 12
      //   303: ldc -94
      //   305: invokevirtual 165	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   308: aload 12
      //   310: sipush 30000
      //   313: invokevirtual 169	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   316: aload 12
      //   318: sipush 30000
      //   321: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   324: aload 12
      //   326: iconst_1
      //   327: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   330: aload 12
      //   332: iconst_1
      //   333: invokevirtual 179	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   336: aload 12
      //   338: invokevirtual 182	javax/net/ssl/HttpsURLConnection:connect	()V
      //   341: aload_0
      //   342: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   345: getfield 110	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
      //   348: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   351: astore 13
      //   353: new 87	java/lang/StringBuilder
      //   356: dup
      //   357: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   360: ldc -72
      //   362: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   365: aload 13
      //   367: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   370: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   373: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   376: ldc -70
      //   378: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   381: new 188	java/io/OutputStreamWriter
      //   384: dup
      //   385: aload 12
      //   387: invokevirtual 192	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   390: invokespecial 195	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   393: astore 14
      //   395: aload 14
      //   397: aload 13
      //   399: invokevirtual 198	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   402: aload 14
      //   404: invokevirtual 201	java/io/OutputStreamWriter:close	()V
      //   407: ldc -53
      //   409: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   412: new 205	java/lang/StringBuffer
      //   415: dup
      //   416: ldc 71
      //   418: invokespecial 206	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   421: astore 13
      //   423: new 208	java/io/BufferedReader
      //   426: dup
      //   427: new 210	java/io/InputStreamReader
      //   430: dup
      //   431: aload 12
      //   433: invokevirtual 214	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   436: invokespecial 217	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   439: invokespecial 220	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   442: astore 12
      //   444: aload 12
      //   446: invokevirtual 223	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   449: astore 14
      //   451: aload 14
      //   453: ifnull +89 -> 542
      //   456: aload 13
      //   458: aload 14
      //   460: invokevirtual 226	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   463: pop
      //   464: goto -20 -> 444
      //   467: astore 12
      //   469: aload 12
      //   471: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   474: ldc -24
      //   476: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   479: ifeq +2606 -> 3085
      //   482: new 87	java/lang/StringBuilder
      //   485: dup
      //   486: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   489: ldc -21
      //   491: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   494: aload 12
      //   496: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   499: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   502: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   505: aload 12
      //   507: invokestatic 245	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
      //   510: return
      //   511: astore 12
      //   513: new 87	java/lang/StringBuilder
      //   516: dup
      //   517: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   520: ldc -9
      //   522: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   525: aload 12
      //   527: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
      //   530: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   533: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   536: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   539: goto -499 -> 40
      //   542: aload 13
      //   544: invokevirtual 249	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   547: astore 12
      //   549: new 87	java/lang/StringBuilder
      //   552: dup
      //   553: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   556: ldc -5
      //   558: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   561: aload 12
      //   563: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   566: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   569: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   572: aconst_null
      //   573: astore 13
      //   575: new 112	org/json/JSONObject
      //   578: dup
      //   579: aload 12
      //   581: invokespecial 252	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   584: astore 12
      //   586: aload 12
      //   588: astore 13
      //   590: aload 13
      //   592: ifnull +1071 -> 1663
      //   595: new 87	java/lang/StringBuilder
      //   598: dup
      //   599: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   602: ldc -2
      //   604: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   607: aload 13
      //   609: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   612: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   615: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   618: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   621: aconst_null
      //   622: astore 12
      //   624: aload 13
      //   626: ldc_w 256
      //   629: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   632: astore 14
      //   634: aload 14
      //   636: astore 12
      //   638: new 87	java/lang/StringBuilder
      //   641: dup
      //   642: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   645: ldc_w 262
      //   648: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   651: aload 14
      //   653: invokevirtual 113	org/json/JSONObject:toString	()Ljava/lang/String;
      //   656: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   659: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   662: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   665: aload 14
      //   667: astore 12
      //   669: aload 12
      //   671: ifnull +756 -> 1427
      //   674: aload 12
      //   676: ldc_w 264
      //   679: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   682: astore 14
      //   684: new 87	java/lang/StringBuilder
      //   687: dup
      //   688: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   691: ldc_w 268
      //   694: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   697: aload 14
      //   699: invokevirtual 269	java/lang/String:toString	()Ljava/lang/String;
      //   702: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   705: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   708: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   711: aload 14
      //   713: invokestatic 272	com/kochava/android/tracker/Feature:access$602	(Ljava/lang/String;)Ljava/lang/String;
      //   716: pop
      //   717: aload 12
      //   719: ldc_w 274
      //   722: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   725: ldc_w 280
      //   728: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   731: ifeq +8 -> 739
      //   734: iconst_0
      //   735: invokestatic 284	com/kochava/android/tracker/Feature:access$702	(Z)Z
      //   738: pop
      //   739: aload 12
      //   741: ldc_w 286
      //   744: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   747: checkcast 81	java/lang/String
      //   750: invokevirtual 289	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   753: astore 14
      //   755: new 87	java/lang/StringBuilder
      //   758: dup
      //   759: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   762: ldc_w 291
      //   765: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   768: aload 14
      //   770: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   773: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   776: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   779: aload_0
      //   780: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   783: aload 14
      //   785: invokestatic 295	com/kochava/android/tracker/Feature:access$800	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;)V
      //   788: aload 12
      //   790: ldc_w 297
      //   793: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   796: ldc_w 299
      //   799: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   802: ifeq +52 -> 854
      //   805: ldc_w 301
      //   808: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   811: aload_0
      //   812: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   815: invokestatic 305	com/kochava/android/tracker/Feature:access$900	(Lcom/kochava/android/tracker/Feature;)Landroid/content/Context;
      //   818: ldc_w 307
      //   821: iconst_0
      //   822: invokevirtual 313	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   825: invokestatic 317	com/kochava/android/tracker/Feature:access$202	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   828: pop
      //   829: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   832: invokeinterface 321 1 0
      //   837: ldc_w 323
      //   840: ldc_w 325
      //   843: invokeinterface 331 3 0
      //   848: invokeinterface 334 1 0
      //   853: pop
      //   854: aload 12
      //   856: ldc_w 336
      //   859: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   862: checkcast 338	java/lang/Integer
      //   865: invokevirtual 342	java/lang/Integer:intValue	()I
      //   868: invokestatic 346	com/kochava/android/tracker/Feature:access$1002	(I)I
      //   871: pop
      //   872: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	()I
      //   875: ifge +1034 -> 1909
      //   878: new 87	java/lang/StringBuilder
      //   881: dup
      //   882: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   885: ldc_w 351
      //   888: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   891: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	()I
      //   894: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   897: ldc_w 356
      //   900: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   903: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   906: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   909: iconst_0
      //   910: invokestatic 346	com/kochava/android/tracker/Feature:access$1002	(I)I
      //   913: pop
      //   914: invokestatic 359	com/kochava/android/tracker/Feature:access$1100	()Z
      //   917: ifne +89 -> 1006
      //   920: aload 12
      //   922: ldc_w 361
      //   925: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   928: ifnull +78 -> 1006
      //   931: aload 12
      //   933: ldc_w 361
      //   936: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   939: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   942: ldc_w 338
      //   945: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   948: ifeq +58 -> 1006
      //   951: aload 12
      //   953: ldc_w 361
      //   956: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   959: checkcast 338	java/lang/Integer
      //   962: invokevirtual 342	java/lang/Integer:intValue	()I
      //   965: istore_1
      //   966: iload_1
      //   967: bipush 60
      //   969: if_icmpge +1016 -> 1985
      //   972: new 87	java/lang/StringBuilder
      //   975: dup
      //   976: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   979: ldc_w 363
      //   982: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   985: iload_1
      //   986: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   989: ldc_w 365
      //   992: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   995: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   998: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1001: iconst_1
      //   1002: invokestatic 368	com/kochava/android/tracker/Feature:access$1302	(Z)Z
      //   1005: pop
      //   1006: aload 12
      //   1008: ldc 102
      //   1010: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1013: ifnull +93 -> 1106
      //   1016: aload 12
      //   1018: ldc 102
      //   1020: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1023: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1026: ldc_w 338
      //   1029: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1032: ifeq +74 -> 1106
      //   1035: aload 12
      //   1037: ldc 102
      //   1039: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1042: checkcast 338	java/lang/Integer
      //   1045: invokevirtual 342	java/lang/Integer:intValue	()I
      //   1048: istore_1
      //   1049: iload_1
      //   1050: ifge +1022 -> 2072
      //   1053: new 87	java/lang/StringBuilder
      //   1056: dup
      //   1057: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1060: ldc_w 370
      //   1063: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1066: iload_1
      //   1067: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1070: ldc_w 372
      //   1073: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1076: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1079: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1082: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1085: invokeinterface 321 1 0
      //   1090: ldc 102
      //   1092: ldc2_w 103
      //   1095: invokeinterface 376 4 0
      //   1100: invokeinterface 334 1 0
      //   1105: pop
      //   1106: aload 12
      //   1108: ldc_w 378
      //   1111: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1114: ifnull +77 -> 1191
      //   1117: aload 12
      //   1119: ldc_w 378
      //   1122: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1125: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1128: ldc_w 338
      //   1131: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1134: ifeq +57 -> 1191
      //   1137: aload 12
      //   1139: ldc_w 378
      //   1142: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1145: checkcast 338	java/lang/Integer
      //   1148: invokevirtual 342	java/lang/Integer:intValue	()I
      //   1151: istore_1
      //   1152: iload_1
      //   1153: iconst_1
      //   1154: if_icmpge +1040 -> 2194
      //   1157: new 87	java/lang/StringBuilder
      //   1160: dup
      //   1161: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1164: ldc_w 380
      //   1167: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1170: iload_1
      //   1171: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1174: ldc_w 382
      //   1177: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1180: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1183: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1186: iconst_1
      //   1187: invokestatic 385	com/kochava/android/tracker/Feature:access$1402	(I)I
      //   1190: pop
      //   1191: aload 12
      //   1193: ldc_w 387
      //   1196: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1199: checkcast 338	java/lang/Integer
      //   1202: invokevirtual 342	java/lang/Integer:intValue	()I
      //   1205: istore_2
      //   1206: iload_2
      //   1207: bipush 10
      //   1209: if_icmpge +1066 -> 2275
      //   1212: new 87	java/lang/StringBuilder
      //   1215: dup
      //   1216: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1219: ldc_w 389
      //   1222: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1225: iload_2
      //   1226: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1229: ldc_w 391
      //   1232: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1235: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1238: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1241: bipush 10
      //   1243: istore_1
      //   1244: new 87	java/lang/StringBuilder
      //   1247: dup
      //   1248: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1251: ldc_w 393
      //   1254: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1257: iload_1
      //   1258: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1261: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1264: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1267: iload_1
      //   1268: putstatic 399	com/kochava/android/tracker/LocationDirector:desiredAccuracy	I
      //   1271: aload 12
      //   1273: ldc_w 401
      //   1276: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1279: checkcast 338	java/lang/Integer
      //   1282: invokevirtual 342	java/lang/Integer:intValue	()I
      //   1285: istore_2
      //   1286: iload_2
      //   1287: iconst_3
      //   1288: if_icmpge +1032 -> 2320
      //   1291: new 87	java/lang/StringBuilder
      //   1294: dup
      //   1295: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1298: ldc_w 403
      //   1301: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1304: iload_2
      //   1305: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1308: ldc_w 405
      //   1311: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1314: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1317: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1320: iconst_3
      //   1321: istore_1
      //   1322: new 87	java/lang/StringBuilder
      //   1325: dup
      //   1326: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1329: ldc_w 407
      //   1332: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1335: iload_1
      //   1336: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1339: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1342: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1345: iload_1
      //   1346: putstatic 410	com/kochava/android/tracker/LocationDirector:timeout	I
      //   1349: aload 12
      //   1351: ldc_w 412
      //   1354: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1357: checkcast 338	java/lang/Integer
      //   1360: invokevirtual 342	java/lang/Integer:intValue	()I
      //   1363: istore_2
      //   1364: iload_2
      //   1365: iconst_1
      //   1366: if_icmpge +997 -> 2363
      //   1369: new 87	java/lang/StringBuilder
      //   1372: dup
      //   1373: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1376: ldc_w 414
      //   1379: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1382: iload_2
      //   1383: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1386: ldc_w 416
      //   1389: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1392: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1395: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1398: iconst_1
      //   1399: istore_1
      //   1400: new 87	java/lang/StringBuilder
      //   1403: dup
      //   1404: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1407: ldc_w 418
      //   1410: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1413: iload_1
      //   1414: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1417: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1420: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1423: iload_1
      //   1424: putstatic 421	com/kochava/android/tracker/LocationDirector:staleness	I
      //   1427: aload 13
      //   1429: ldc_w 423
      //   1432: invokevirtual 427	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1435: astore 14
      //   1437: new 87	java/lang/StringBuilder
      //   1440: dup
      //   1441: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1444: ldc_w 429
      //   1447: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1450: aload 14
      //   1452: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1455: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1458: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1461: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1464: iconst_0
      //   1465: istore_1
      //   1466: iload_1
      //   1467: aload 14
      //   1469: invokevirtual 435	org/json/JSONArray:length	()I
      //   1472: if_icmpge +993 -> 2465
      //   1475: aload 14
      //   1477: iload_1
      //   1478: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1481: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   1484: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1487: ldc_w 444
      //   1490: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1493: ifeq +915 -> 2408
      //   1496: ldc_w 446
      //   1499: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1502: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   1505: ldc_w 444
      //   1508: iconst_0
      //   1509: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1512: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1515: pop
      //   1516: iload_1
      //   1517: iconst_1
      //   1518: iadd
      //   1519: istore_1
      //   1520: goto -54 -> 1466
      //   1523: astore 12
      //   1525: new 87	java/lang/StringBuilder
      //   1528: dup
      //   1529: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1532: ldc_w 464
      //   1535: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1538: aload 12
      //   1540: invokevirtual 465	org/json/JSONException:toString	()Ljava/lang/String;
      //   1543: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1546: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1549: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1552: goto -962 -> 590
      //   1555: astore 12
      //   1557: new 87	java/lang/StringBuilder
      //   1560: dup
      //   1561: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1564: ldc_w 467
      //   1567: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1570: aload 12
      //   1572: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1575: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1578: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1581: return
      //   1582: astore 12
      //   1584: ldc -24
      //   1586: aload 12
      //   1588: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1591: invokevirtual 473	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   1594: ifeq +1516 -> 3110
      //   1597: new 87	java/lang/StringBuilder
      //   1600: dup
      //   1601: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1604: ldc -21
      //   1606: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1609: aload 12
      //   1611: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1614: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1617: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1620: aload 12
      //   1622: invokestatic 245	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
      //   1625: return
      //   1626: astore 14
      //   1628: ldc_w 475
      //   1631: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1634: goto -965 -> 669
      //   1637: astore 12
      //   1639: new 87	java/lang/StringBuilder
      //   1642: dup
      //   1643: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1646: ldc_w 477
      //   1649: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1652: aload 12
      //   1654: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1657: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1660: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1663: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1666: invokeinterface 321 1 0
      //   1671: ldc 57
      //   1673: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   1676: invokeinterface 376 4 0
      //   1681: invokeinterface 334 1 0
      //   1686: pop
      //   1687: ldc_w 479
      //   1690: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1693: iconst_0
      //   1694: istore_2
      //   1695: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1698: ldc_w 323
      //   1701: ldc 71
      //   1703: invokeinterface 75 3 0
      //   1708: ldc_w 299
      //   1711: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1714: ifne +1525 -> 3239
      //   1717: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   1720: ldc_w 481
      //   1723: invokevirtual 484	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1726: checkcast 452	java/lang/Boolean
      //   1729: invokevirtual 487	java/lang/Boolean:booleanValue	()Z
      //   1732: ifne +1440 -> 3172
      //   1735: iconst_1
      //   1736: istore_1
      //   1737: invokestatic 490	com/kochava/android/tracker/Feature:access$2400	()Z
      //   1740: istore 11
      //   1742: iload_2
      //   1743: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	()I
      //   1746: if_icmpge +50 -> 1796
      //   1749: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   1752: ldc_w 492
      //   1755: ldc_w 494
      //   1758: invokeinterface 75 3 0
      //   1763: ldc_w 494
      //   1766: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1769: ifne +1408 -> 3177
      //   1772: iconst_1
      //   1773: istore_3
      //   1774: invokestatic 497	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   1777: ifnull +1405 -> 3182
      //   1780: iconst_1
      //   1781: istore 4
      //   1783: iload_3
      //   1784: ifeq +1404 -> 3188
      //   1787: iload_1
      //   1788: ifne +8 -> 1796
      //   1791: iload 11
      //   1793: ifeq +1395 -> 3188
      //   1796: new 87	java/lang/StringBuilder
      //   1799: dup
      //   1800: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1803: ldc_w 499
      //   1806: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1809: iload_2
      //   1810: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1813: ldc_w 501
      //   1816: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1819: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1822: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1825: invokestatic 507	android/os/Message:obtain	()Landroid/os/Message;
      //   1828: astore 12
      //   1830: new 509	android/os/Bundle
      //   1833: dup
      //   1834: invokespecial 510	android/os/Bundle:<init>	()V
      //   1837: astore 13
      //   1839: aload 13
      //   1841: ldc_w 512
      //   1844: invokestatic 515	com/kochava/android/tracker/Feature:access$2500	()Z
      //   1847: invokevirtual 519	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   1850: aload 12
      //   1852: aload 13
      //   1854: invokevirtual 523	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   1857: aload_0
      //   1858: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   1861: invokestatic 527	com/kochava/android/tracker/Feature:access$2600	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   1864: ifnull -1354 -> 510
      //   1867: ldc_w 529
      //   1870: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1873: aload_0
      //   1874: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   1877: invokestatic 527	com/kochava/android/tracker/Feature:access$2600	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   1880: aload 12
      //   1882: invokevirtual 535	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   1885: pop
      //   1886: return
      //   1887: astore 14
      //   1889: ldc_w 537
      //   1892: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1895: goto -1178 -> 717
      //   1898: astore 14
      //   1900: ldc_w 475
      //   1903: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   1906: goto -1167 -> 739
      //   1909: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	()I
      //   1912: bipush 120
      //   1914: if_icmple +43 -> 1957
      //   1917: new 87	java/lang/StringBuilder
      //   1920: dup
      //   1921: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1924: ldc_w 539
      //   1927: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1930: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	()I
      //   1933: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1936: ldc_w 541
      //   1939: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1942: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1945: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1948: bipush 120
      //   1950: invokestatic 346	com/kochava/android/tracker/Feature:access$1002	(I)I
      //   1953: pop
      //   1954: goto -1040 -> 914
      //   1957: new 87	java/lang/StringBuilder
      //   1960: dup
      //   1961: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1964: ldc_w 543
      //   1967: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1970: invokestatic 349	com/kochava/android/tracker/Feature:access$1000	()I
      //   1973: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1976: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1979: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   1982: goto -1068 -> 914
      //   1985: iload_1
      //   1986: ldc_w 544
      //   1989: if_icmple +42 -> 2031
      //   1992: new 87	java/lang/StringBuilder
      //   1995: dup
      //   1996: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   1999: ldc_w 546
      //   2002: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2005: iload_1
      //   2006: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2009: ldc_w 548
      //   2012: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2015: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2018: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2021: ldc_w 549
      //   2024: invokestatic 552	com/kochava/android/tracker/Feature:access$1202	(I)I
      //   2027: pop
      //   2028: goto -1027 -> 1001
      //   2031: iload_1
      //   2032: sipush 1000
      //   2035: imul
      //   2036: invokestatic 552	com/kochava/android/tracker/Feature:access$1202	(I)I
      //   2039: pop
      //   2040: new 87	java/lang/StringBuilder
      //   2043: dup
      //   2044: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2047: ldc_w 554
      //   2050: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2053: iload_1
      //   2054: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2057: ldc_w 501
      //   2060: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2063: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2066: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2069: goto -1068 -> 1001
      //   2072: iload_1
      //   2073: ldc_w 544
      //   2076: if_icmple +59 -> 2135
      //   2079: new 87	java/lang/StringBuilder
      //   2082: dup
      //   2083: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2086: ldc_w 370
      //   2089: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2092: iload_1
      //   2093: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2096: ldc_w 556
      //   2099: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2102: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2105: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2108: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   2111: invokeinterface 321 1 0
      //   2116: ldc 102
      //   2118: ldc2_w 557
      //   2121: invokeinterface 376 4 0
      //   2126: invokeinterface 334 1 0
      //   2131: pop
      //   2132: goto -1026 -> 1106
      //   2135: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   2138: invokeinterface 321 1 0
      //   2143: ldc 102
      //   2145: iload_1
      //   2146: sipush 1000
      //   2149: imul
      //   2150: i2l
      //   2151: invokeinterface 376 4 0
      //   2156: invokeinterface 334 1 0
      //   2161: pop
      //   2162: new 87	java/lang/StringBuilder
      //   2165: dup
      //   2166: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2169: ldc_w 560
      //   2172: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2175: iload_1
      //   2176: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2179: ldc_w 501
      //   2182: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2185: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2188: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2191: goto -1085 -> 1106
      //   2194: iload_1
      //   2195: bipush 30
      //   2197: if_icmple +41 -> 2238
      //   2200: new 87	java/lang/StringBuilder
      //   2203: dup
      //   2204: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2207: ldc_w 380
      //   2210: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2213: iload_1
      //   2214: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2217: ldc_w 562
      //   2220: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2223: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2226: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2229: bipush 30
      //   2231: invokestatic 385	com/kochava/android/tracker/Feature:access$1402	(I)I
      //   2234: pop
      //   2235: goto -1044 -> 1191
      //   2238: new 87	java/lang/StringBuilder
      //   2241: dup
      //   2242: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2245: ldc_w 564
      //   2248: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2251: iload_1
      //   2252: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2255: ldc_w 501
      //   2258: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2261: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2264: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2267: iload_1
      //   2268: invokestatic 385	com/kochava/android/tracker/Feature:access$1402	(I)I
      //   2271: pop
      //   2272: goto -1081 -> 1191
      //   2275: iload_2
      //   2276: istore_1
      //   2277: iload_2
      //   2278: sipush 5000
      //   2281: if_icmple -1037 -> 1244
      //   2284: new 87	java/lang/StringBuilder
      //   2287: dup
      //   2288: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2291: ldc_w 389
      //   2294: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2297: iload_2
      //   2298: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2301: ldc_w 391
      //   2304: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2307: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2310: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2313: sipush 5000
      //   2316: istore_1
      //   2317: goto -1073 -> 1244
      //   2320: iload_2
      //   2321: istore_1
      //   2322: iload_2
      //   2323: bipush 60
      //   2325: if_icmple -1003 -> 1322
      //   2328: new 87	java/lang/StringBuilder
      //   2331: dup
      //   2332: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2335: ldc_w 403
      //   2338: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2341: iload_2
      //   2342: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2345: ldc_w 405
      //   2348: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2351: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2354: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2357: bipush 60
      //   2359: istore_1
      //   2360: goto -1038 -> 1322
      //   2363: iload_2
      //   2364: istore_1
      //   2365: iload_2
      //   2366: sipush 10080
      //   2369: if_icmple -969 -> 1400
      //   2372: new 87	java/lang/StringBuilder
      //   2375: dup
      //   2376: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2379: ldc_w 414
      //   2382: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2385: iload_2
      //   2386: invokevirtual 354	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2389: ldc_w 416
      //   2392: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2395: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2398: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2401: sipush 10080
      //   2404: istore_1
      //   2405: goto -1005 -> 1400
      //   2408: aload 14
      //   2410: iload_1
      //   2411: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2414: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2417: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2420: ldc_w 566
      //   2423: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2426: ifeq +126 -> 2552
      //   2429: ldc_w 568
      //   2432: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2435: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2438: ldc_w 566
      //   2441: iconst_0
      //   2442: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2445: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2448: pop
      //   2449: goto -933 -> 1516
      //   2452: astore 14
      //   2454: ldc_w 570
      //   2457: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2460: aload 14
      //   2462: invokevirtual 573	java/lang/Exception:printStackTrace	()V
      //   2465: aload 13
      //   2467: ldc_w 575
      //   2470: invokevirtual 427	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2473: astore 14
      //   2475: new 87	java/lang/StringBuilder
      //   2478: dup
      //   2479: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2482: ldc_w 577
      //   2485: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2488: aload 14
      //   2490: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2493: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2496: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2499: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2502: iconst_0
      //   2503: istore_1
      //   2504: iload_1
      //   2505: aload 14
      //   2507: invokevirtual 435	org/json/JSONArray:length	()I
      //   2510: if_icmpge +282 -> 2792
      //   2513: aload 14
      //   2515: iload_1
      //   2516: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2519: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2522: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2525: ldc_w 579
      //   2528: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2531: ifeq +197 -> 2728
      //   2534: ldc_w 581
      //   2537: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2540: iconst_1
      //   2541: invokestatic 584	com/kochava/android/tracker/Feature:access$1602	(Z)Z
      //   2544: pop
      //   2545: iload_1
      //   2546: iconst_1
      //   2547: iadd
      //   2548: istore_1
      //   2549: goto -45 -> 2504
      //   2552: aload 14
      //   2554: iload_1
      //   2555: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2558: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2561: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2564: ldc_w 481
      //   2567: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2570: ifeq +26 -> 2596
      //   2573: ldc_w 586
      //   2576: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2579: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2582: ldc_w 481
      //   2585: iconst_0
      //   2586: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2589: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2592: pop
      //   2593: goto -1077 -> 1516
      //   2596: aload 14
      //   2598: iload_1
      //   2599: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2602: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2605: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2608: ldc_w 588
      //   2611: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2614: ifeq +26 -> 2640
      //   2617: ldc_w 590
      //   2620: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2623: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2626: ldc_w 588
      //   2629: iconst_0
      //   2630: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2633: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2636: pop
      //   2637: goto -1121 -> 1516
      //   2640: aload 14
      //   2642: iload_1
      //   2643: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2646: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2649: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2652: ldc_w 592
      //   2655: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2658: ifeq +26 -> 2684
      //   2661: ldc_w 594
      //   2664: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2667: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2670: ldc_w 592
      //   2673: iconst_0
      //   2674: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2677: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2680: pop
      //   2681: goto -1165 -> 1516
      //   2684: aload 14
      //   2686: iload_1
      //   2687: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2690: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2693: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2696: ldc_w 596
      //   2699: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2702: ifeq -1186 -> 1516
      //   2705: ldc_w 598
      //   2708: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2711: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2714: ldc_w 596
      //   2717: iconst_0
      //   2718: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2721: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2724: pop
      //   2725: goto -1209 -> 1516
      //   2728: aload 14
      //   2730: iload_1
      //   2731: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2734: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2737: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2740: ldc_w 600
      //   2743: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2746: ifeq -201 -> 2545
      //   2749: ldc_w 602
      //   2752: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2755: iconst_1
      //   2756: invokestatic 605	com/kochava/android/tracker/Feature:access$1702	(Z)Z
      //   2759: pop
      //   2760: goto -215 -> 2545
      //   2763: astore 14
      //   2765: new 87	java/lang/StringBuilder
      //   2768: dup
      //   2769: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2772: ldc_w 607
      //   2775: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2778: aload 14
      //   2780: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
      //   2783: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2786: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2789: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2792: aload 13
      //   2794: ldc_w 609
      //   2797: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2800: ldc_w 611
      //   2803: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2806: astore 14
      //   2808: aload_0
      //   2809: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   2812: aload 14
      //   2814: invokestatic 615	com/kochava/android/tracker/Feature:access$1800	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   2817: invokestatic 618	com/kochava/android/tracker/Feature:access$1700	()Z
      //   2820: ifeq +18 -> 2838
      //   2823: invokestatic 621	com/kochava/android/tracker/Feature:access$1900	()Z
      //   2826: ifeq +12 -> 2838
      //   2829: getstatic 625	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2832: invokestatic 629	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   2835: invokevirtual 632	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   2838: aload 13
      //   2840: ldc_w 634
      //   2843: invokevirtual 427	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2846: invokestatic 638	com/kochava/android/tracker/Feature:access$2002	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   2849: pop
      //   2850: new 87	java/lang/StringBuilder
      //   2853: dup
      //   2854: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2857: ldc_w 640
      //   2860: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2863: invokestatic 644	com/kochava/android/tracker/Feature:access$2000	()Lorg/json/JSONArray;
      //   2866: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2869: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2872: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2875: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2878: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   2881: invokeinterface 321 1 0
      //   2886: ldc_w 646
      //   2889: invokestatic 644	com/kochava/android/tracker/Feature:access$2000	()Lorg/json/JSONArray;
      //   2892: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2895: invokeinterface 331 3 0
      //   2900: invokeinterface 334 1 0
      //   2905: pop
      //   2906: aload 12
      //   2908: ldc_w 648
      //   2911: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2914: ifnull +88 -> 3002
      //   2917: aload 12
      //   2919: ldc_w 648
      //   2922: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2925: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2928: ldc_w 452
      //   2931: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2934: ifeq +141 -> 3075
      //   2937: aload 12
      //   2939: ldc_w 648
      //   2942: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2945: checkcast 452	java/lang/Boolean
      //   2948: invokevirtual 487	java/lang/Boolean:booleanValue	()Z
      //   2951: ifeq +124 -> 3075
      //   2954: iconst_1
      //   2955: istore_1
      //   2956: aload 12
      //   2958: ldc_w 648
      //   2961: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2964: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2967: ldc 81
      //   2969: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2972: ifeq +108 -> 3080
      //   2975: ldc_w 299
      //   2978: aload 12
      //   2980: ldc_w 648
      //   2983: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2986: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2989: ifeq +91 -> 3080
      //   2992: iconst_1
      //   2993: istore_2
      //   2994: goto +314 -> 3308
      //   2997: iconst_1
      //   2998: invokestatic 651	com/kochava/android/tracker/Feature:access$2102	(Z)Z
      //   3001: pop
      //   3002: aload 13
      //   3004: ldc_w 653
      //   3007: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3010: astore 12
      //   3012: new 87	java/lang/StringBuilder
      //   3015: dup
      //   3016: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3019: ldc_w 655
      //   3022: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3025: aload 12
      //   3027: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3030: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3033: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3036: aload 12
      //   3038: ldc_w 657
      //   3041: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3044: ifeq -1381 -> 1663
      //   3047: iconst_1
      //   3048: invokestatic 660	com/kochava/android/tracker/Feature:access$2202	(Z)Z
      //   3051: pop
      //   3052: return
      //   3053: astore 12
      //   3055: ldc_w 662
      //   3058: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3061: goto -1398 -> 1663
      //   3064: astore 14
      //   3066: ldc_w 664
      //   3069: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3072: goto -166 -> 2906
      //   3075: iconst_0
      //   3076: istore_1
      //   3077: goto -121 -> 2956
      //   3080: iconst_0
      //   3081: istore_2
      //   3082: goto +226 -> 3308
      //   3085: new 87	java/lang/StringBuilder
      //   3088: dup
      //   3089: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3092: ldc_w 467
      //   3095: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3098: aload 12
      //   3100: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3103: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3106: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3109: return
      //   3110: new 87	java/lang/StringBuilder
      //   3113: dup
      //   3114: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3117: ldc_w 467
      //   3120: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3123: aload 12
      //   3125: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3128: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3131: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3134: return
      //   3135: new 87	java/lang/StringBuilder
      //   3138: dup
      //   3139: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3142: ldc_w 666
      //   3145: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3148: lload 5
      //   3150: ldc2_w 667
      //   3153: ldiv
      //   3154: invokevirtual 671	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3157: ldc_w 673
      //   3160: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3163: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3166: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3169: goto -1482 -> 1687
      //   3172: iconst_0
      //   3173: istore_1
      //   3174: goto -1437 -> 1737
      //   3177: iconst_0
      //   3178: istore_3
      //   3179: goto -1405 -> 1774
      //   3182: iconst_0
      //   3183: istore 4
      //   3185: goto -1402 -> 1783
      //   3188: iload_3
      //   3189: ifeq +8 -> 3197
      //   3192: iload 4
      //   3194: ifne -1398 -> 1796
      //   3197: ldc2_w 667
      //   3200: invokestatic 679	java/lang/Thread:sleep	(J)V
      //   3203: iload_2
      //   3204: iconst_1
      //   3205: iadd
      //   3206: istore_2
      //   3207: goto -1465 -> 1742
      //   3210: astore 12
      //   3212: new 87	java/lang/StringBuilder
      //   3215: dup
      //   3216: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3219: ldc_w 681
      //   3222: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3225: aload 12
      //   3227: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3230: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3233: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3236: goto -33 -> 3203
      //   3239: ldc_w 683
      //   3242: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3245: goto -1420 -> 1825
      //   3248: astore 12
      //   3250: goto -248 -> 3002
      //   3253: astore 14
      //   3255: goto -349 -> 2906
      //   3258: astore 14
      //   3260: goto -443 -> 2817
      //   3263: astore 14
      //   3265: goto -1838 -> 1427
      //   3268: astore 14
      //   3270: goto -1921 -> 1349
      //   3273: astore 14
      //   3275: goto -2004 -> 1271
      //   3278: astore 14
      //   3280: goto -2426 -> 854
      //   3283: astore 14
      //   3285: goto -2497 -> 788
      //   3288: astore 14
      //   3290: goto -2376 -> 914
      //   3293: astore 14
      //   3295: goto -2289 -> 1006
      //   3298: astore 14
      //   3300: goto -2194 -> 1106
      //   3303: astore 14
      //   3305: goto -2114 -> 1191
      //   3308: iload_1
      //   3309: ifne -312 -> 2997
      //   3312: iload_2
      //   3313: ifeq -311 -> 3002
      //   3316: goto -319 -> 2997
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3319	0	this	3
      //   41	3268	1	i	int
      //   12	3301	2	j	int
      //   1773	1416	3	k	int
      //   1781	1412	4	m	int
      //   9	3140	5	l1	long
      //   16	18	7	l2	long
      //   31	5	9	l3	long
      //   1740	52	11	bool	boolean
      //   280	165	12	localObject1	Object
      //   467	39	12	localIOException1	IOException
      //   511	15	12	localException1	Exception
      //   547	803	12	localObject2	Object
      //   1523	16	12	localJSONException1	JSONException
      //   1555	16	12	localOutOfMemoryError	OutOfMemoryError
      //   1582	39	12	localIOException2	IOException
      //   1637	16	12	localException2	Exception
      //   1828	1209	12	localObject3	Object
      //   3053	71	12	localException3	Exception
      //   3210	16	12	localInterruptedException	InterruptedException
      //   3248	1	12	localException4	Exception
      //   351	2652	13	localObject4	Object
      //   393	1083	14	localObject5	Object
      //   1626	1	14	localJSONException2	JSONException
      //   1887	1	14	localJSONException3	JSONException
      //   1898	511	14	localJSONException4	JSONException
      //   2452	9	14	localException5	Exception
      //   2473	256	14	localJSONArray	JSONArray
      //   2763	16	14	localException6	Exception
      //   2806	7	14	localJSONObject	JSONObject
      //   3064	1	14	localException7	Exception
      //   3253	1	14	localJSONException5	JSONException
      //   3258	1	14	localException8	Exception
      //   3263	1	14	localException9	Exception
      //   3268	1	14	localException10	Exception
      //   3273	1	14	localException11	Exception
      //   3278	1	14	localException12	Exception
      //   3283	1	14	localException13	Exception
      //   3288	1	14	localException14	Exception
      //   3293	1	14	localException15	Exception
      //   3298	1	14	localException16	Exception
      //   3303	1	14	localException17	Exception
      // Exception table:
      //   from	to	target	type
      //   407	444	467	java/io/IOException
      //   444	451	467	java/io/IOException
      //   456	464	467	java/io/IOException
      //   542	572	467	java/io/IOException
      //   575	586	467	java/io/IOException
      //   595	621	467	java/io/IOException
      //   624	634	467	java/io/IOException
      //   638	665	467	java/io/IOException
      //   674	717	467	java/io/IOException
      //   717	739	467	java/io/IOException
      //   739	788	467	java/io/IOException
      //   788	854	467	java/io/IOException
      //   854	914	467	java/io/IOException
      //   914	966	467	java/io/IOException
      //   972	1001	467	java/io/IOException
      //   1001	1006	467	java/io/IOException
      //   1006	1049	467	java/io/IOException
      //   1053	1106	467	java/io/IOException
      //   1106	1152	467	java/io/IOException
      //   1157	1191	467	java/io/IOException
      //   1191	1206	467	java/io/IOException
      //   1212	1241	467	java/io/IOException
      //   1244	1271	467	java/io/IOException
      //   1271	1286	467	java/io/IOException
      //   1291	1320	467	java/io/IOException
      //   1322	1349	467	java/io/IOException
      //   1349	1364	467	java/io/IOException
      //   1369	1398	467	java/io/IOException
      //   1400	1427	467	java/io/IOException
      //   1427	1464	467	java/io/IOException
      //   1466	1516	467	java/io/IOException
      //   1525	1552	467	java/io/IOException
      //   1628	1634	467	java/io/IOException
      //   1889	1895	467	java/io/IOException
      //   1900	1906	467	java/io/IOException
      //   1909	1954	467	java/io/IOException
      //   1957	1982	467	java/io/IOException
      //   1992	2028	467	java/io/IOException
      //   2031	2069	467	java/io/IOException
      //   2079	2132	467	java/io/IOException
      //   2135	2191	467	java/io/IOException
      //   2200	2235	467	java/io/IOException
      //   2238	2272	467	java/io/IOException
      //   2284	2313	467	java/io/IOException
      //   2328	2357	467	java/io/IOException
      //   2372	2401	467	java/io/IOException
      //   2408	2449	467	java/io/IOException
      //   2454	2465	467	java/io/IOException
      //   2465	2502	467	java/io/IOException
      //   2504	2545	467	java/io/IOException
      //   2552	2593	467	java/io/IOException
      //   2596	2637	467	java/io/IOException
      //   2640	2681	467	java/io/IOException
      //   2684	2725	467	java/io/IOException
      //   2728	2760	467	java/io/IOException
      //   2765	2792	467	java/io/IOException
      //   2792	2817	467	java/io/IOException
      //   2817	2838	467	java/io/IOException
      //   2838	2906	467	java/io/IOException
      //   2906	2954	467	java/io/IOException
      //   2956	2992	467	java/io/IOException
      //   2997	3002	467	java/io/IOException
      //   3002	3052	467	java/io/IOException
      //   3055	3061	467	java/io/IOException
      //   3066	3072	467	java/io/IOException
      //   13	33	511	java/lang/Exception
      //   575	586	1523	org/json/JSONException
      //   407	444	1555	java/lang/OutOfMemoryError
      //   444	451	1555	java/lang/OutOfMemoryError
      //   456	464	1555	java/lang/OutOfMemoryError
      //   542	572	1555	java/lang/OutOfMemoryError
      //   575	586	1555	java/lang/OutOfMemoryError
      //   595	621	1555	java/lang/OutOfMemoryError
      //   624	634	1555	java/lang/OutOfMemoryError
      //   638	665	1555	java/lang/OutOfMemoryError
      //   674	717	1555	java/lang/OutOfMemoryError
      //   717	739	1555	java/lang/OutOfMemoryError
      //   739	788	1555	java/lang/OutOfMemoryError
      //   788	854	1555	java/lang/OutOfMemoryError
      //   854	914	1555	java/lang/OutOfMemoryError
      //   914	966	1555	java/lang/OutOfMemoryError
      //   972	1001	1555	java/lang/OutOfMemoryError
      //   1001	1006	1555	java/lang/OutOfMemoryError
      //   1006	1049	1555	java/lang/OutOfMemoryError
      //   1053	1106	1555	java/lang/OutOfMemoryError
      //   1106	1152	1555	java/lang/OutOfMemoryError
      //   1157	1191	1555	java/lang/OutOfMemoryError
      //   1191	1206	1555	java/lang/OutOfMemoryError
      //   1212	1241	1555	java/lang/OutOfMemoryError
      //   1244	1271	1555	java/lang/OutOfMemoryError
      //   1271	1286	1555	java/lang/OutOfMemoryError
      //   1291	1320	1555	java/lang/OutOfMemoryError
      //   1322	1349	1555	java/lang/OutOfMemoryError
      //   1349	1364	1555	java/lang/OutOfMemoryError
      //   1369	1398	1555	java/lang/OutOfMemoryError
      //   1400	1427	1555	java/lang/OutOfMemoryError
      //   1427	1464	1555	java/lang/OutOfMemoryError
      //   1466	1516	1555	java/lang/OutOfMemoryError
      //   1525	1552	1555	java/lang/OutOfMemoryError
      //   1628	1634	1555	java/lang/OutOfMemoryError
      //   1889	1895	1555	java/lang/OutOfMemoryError
      //   1900	1906	1555	java/lang/OutOfMemoryError
      //   1909	1954	1555	java/lang/OutOfMemoryError
      //   1957	1982	1555	java/lang/OutOfMemoryError
      //   1992	2028	1555	java/lang/OutOfMemoryError
      //   2031	2069	1555	java/lang/OutOfMemoryError
      //   2079	2132	1555	java/lang/OutOfMemoryError
      //   2135	2191	1555	java/lang/OutOfMemoryError
      //   2200	2235	1555	java/lang/OutOfMemoryError
      //   2238	2272	1555	java/lang/OutOfMemoryError
      //   2284	2313	1555	java/lang/OutOfMemoryError
      //   2328	2357	1555	java/lang/OutOfMemoryError
      //   2372	2401	1555	java/lang/OutOfMemoryError
      //   2408	2449	1555	java/lang/OutOfMemoryError
      //   2454	2465	1555	java/lang/OutOfMemoryError
      //   2465	2502	1555	java/lang/OutOfMemoryError
      //   2504	2545	1555	java/lang/OutOfMemoryError
      //   2552	2593	1555	java/lang/OutOfMemoryError
      //   2596	2637	1555	java/lang/OutOfMemoryError
      //   2640	2681	1555	java/lang/OutOfMemoryError
      //   2684	2725	1555	java/lang/OutOfMemoryError
      //   2728	2760	1555	java/lang/OutOfMemoryError
      //   2765	2792	1555	java/lang/OutOfMemoryError
      //   2792	2817	1555	java/lang/OutOfMemoryError
      //   2817	2838	1555	java/lang/OutOfMemoryError
      //   2838	2906	1555	java/lang/OutOfMemoryError
      //   2906	2954	1555	java/lang/OutOfMemoryError
      //   2956	2992	1555	java/lang/OutOfMemoryError
      //   2997	3002	1555	java/lang/OutOfMemoryError
      //   3002	3052	1555	java/lang/OutOfMemoryError
      //   3055	3061	1555	java/lang/OutOfMemoryError
      //   3066	3072	1555	java/lang/OutOfMemoryError
      //   147	196	1582	java/io/IOException
      //   196	207	1582	java/io/IOException
      //   207	407	1582	java/io/IOException
      //   469	510	1582	java/io/IOException
      //   1557	1581	1582	java/io/IOException
      //   3085	3109	1582	java/io/IOException
      //   624	634	1626	org/json/JSONException
      //   638	665	1626	org/json/JSONException
      //   147	196	1637	java/lang/Exception
      //   196	207	1637	java/lang/Exception
      //   207	407	1637	java/lang/Exception
      //   407	444	1637	java/lang/Exception
      //   444	451	1637	java/lang/Exception
      //   456	464	1637	java/lang/Exception
      //   469	510	1637	java/lang/Exception
      //   542	572	1637	java/lang/Exception
      //   575	586	1637	java/lang/Exception
      //   595	621	1637	java/lang/Exception
      //   624	634	1637	java/lang/Exception
      //   638	665	1637	java/lang/Exception
      //   674	717	1637	java/lang/Exception
      //   717	739	1637	java/lang/Exception
      //   1525	1552	1637	java/lang/Exception
      //   1557	1581	1637	java/lang/Exception
      //   1628	1634	1637	java/lang/Exception
      //   1889	1895	1637	java/lang/Exception
      //   1900	1906	1637	java/lang/Exception
      //   2454	2465	1637	java/lang/Exception
      //   2765	2792	1637	java/lang/Exception
      //   2817	2838	1637	java/lang/Exception
      //   3055	3061	1637	java/lang/Exception
      //   3066	3072	1637	java/lang/Exception
      //   3085	3109	1637	java/lang/Exception
      //   674	717	1887	org/json/JSONException
      //   717	739	1898	org/json/JSONException
      //   1427	1464	2452	java/lang/Exception
      //   1466	1516	2452	java/lang/Exception
      //   2408	2449	2452	java/lang/Exception
      //   2552	2593	2452	java/lang/Exception
      //   2596	2637	2452	java/lang/Exception
      //   2640	2681	2452	java/lang/Exception
      //   2684	2725	2452	java/lang/Exception
      //   2465	2502	2763	java/lang/Exception
      //   2504	2545	2763	java/lang/Exception
      //   2728	2760	2763	java/lang/Exception
      //   3002	3052	3053	java/lang/Exception
      //   2838	2906	3064	java/lang/Exception
      //   3197	3203	3210	java/lang/InterruptedException
      //   2906	2954	3248	java/lang/Exception
      //   2956	2992	3248	java/lang/Exception
      //   2997	3002	3248	java/lang/Exception
      //   2838	2906	3253	org/json/JSONException
      //   2792	2817	3258	java/lang/Exception
      //   1349	1364	3263	java/lang/Exception
      //   1369	1398	3263	java/lang/Exception
      //   1400	1427	3263	java/lang/Exception
      //   2372	2401	3263	java/lang/Exception
      //   1271	1286	3268	java/lang/Exception
      //   1291	1320	3268	java/lang/Exception
      //   1322	1349	3268	java/lang/Exception
      //   2328	2357	3268	java/lang/Exception
      //   1191	1206	3273	java/lang/Exception
      //   1212	1241	3273	java/lang/Exception
      //   1244	1271	3273	java/lang/Exception
      //   2284	2313	3273	java/lang/Exception
      //   788	854	3278	java/lang/Exception
      //   739	788	3283	java/lang/Exception
      //   854	914	3288	java/lang/Exception
      //   1909	1954	3288	java/lang/Exception
      //   1957	1982	3288	java/lang/Exception
      //   914	966	3293	java/lang/Exception
      //   972	1001	3293	java/lang/Exception
      //   1001	1006	3293	java/lang/Exception
      //   1992	2028	3293	java/lang/Exception
      //   2031	2069	3293	java/lang/Exception
      //   1006	1049	3298	java/lang/Exception
      //   1053	1106	3298	java/lang/Exception
      //   2079	2132	3298	java/lang/Exception
      //   2135	2191	3298	java/lang/Exception
      //   1106	1152	3303	java/lang/Exception
      //   1157	1191	3303	java/lang/Exception
      //   2200	2235	3303	java/lang/Exception
      //   2238	2272	3303	java/lang/Exception
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
        //   114: checkcast 75	javax/net/ssl/HttpsURLConnection
        //   117: astore 7
        //   119: iload_2
        //   120: istore_1
        //   121: aload 7
        //   123: ldc 77
        //   125: invokestatic 80	com/kochava/android/tracker/Feature:access$500	()Ljava/lang/String;
        //   128: invokevirtual 84	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   131: iload_2
        //   132: istore_1
        //   133: aload 7
        //   135: ldc 86
        //   137: ldc 88
        //   139: invokevirtual 84	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   142: iload_2
        //   143: istore_1
        //   144: aload 7
        //   146: ldc 90
        //   148: invokevirtual 93	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   151: iload_2
        //   152: istore_1
        //   153: aload 7
        //   155: sipush 30000
        //   158: invokevirtual 97	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   161: iload_2
        //   162: istore_1
        //   163: aload 7
        //   165: sipush 30000
        //   168: invokevirtual 100	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   171: iload_2
        //   172: istore_1
        //   173: aload 7
        //   175: iconst_1
        //   176: invokevirtual 104	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   179: iload_2
        //   180: istore_1
        //   181: aload 7
        //   183: iconst_1
        //   184: invokevirtual 107	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   187: iload_2
        //   188: istore_1
        //   189: aload 7
        //   191: invokevirtual 110	javax/net/ssl/HttpsURLConnection:connect	()V
        //   194: iload_2
        //   195: istore_1
        //   196: new 112	org/json/JSONObject
        //   199: dup
        //   200: invokespecial 113	org/json/JSONObject:<init>	()V
        //   203: astore 8
        //   205: iload_2
        //   206: istore_1
        //   207: aload 8
        //   209: ldc 115
        //   211: ldc 117
        //   213: invokevirtual 121	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   216: pop
        //   217: iload_2
        //   218: istore_1
        //   219: aload 8
        //   221: ldc 123
        //   223: invokestatic 126	com/kochava/android/tracker/Feature:access$600	()Ljava/lang/String;
        //   226: invokevirtual 121	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   229: pop
        //   230: iload_2
        //   231: istore_1
        //   232: aload 8
        //   234: ldc -128
        //   236: invokestatic 131	com/kochava/android/tracker/Feature:access$2700	()Ljava/lang/String;
        //   239: invokevirtual 121	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   242: pop
        //   243: iload_2
        //   244: istore_1
        //   245: aload 8
        //   247: ldc -123
        //   249: new 51	java/lang/StringBuilder
        //   252: dup
        //   253: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   256: ldc -121
        //   258: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: getstatic 139	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
        //   264: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   270: invokevirtual 121	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   273: pop
        //   274: iload_2
        //   275: istore_1
        //   276: aload 8
        //   278: ldc -115
        //   280: ldc -113
        //   282: invokevirtual 121	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   285: pop
        //   286: iload_2
        //   287: istore_1
        //   288: aload 8
        //   290: invokevirtual 144	org/json/JSONObject:toString	()Ljava/lang/String;
        //   293: astore 8
        //   295: iload_2
        //   296: istore_1
        //   297: new 51	java/lang/StringBuilder
        //   300: dup
        //   301: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   304: ldc -110
        //   306: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: aload 8
        //   311: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   317: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   320: iload_2
        //   321: istore_1
        //   322: ldc -108
        //   324: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   327: iload_2
        //   328: istore_1
        //   329: new 150	java/io/OutputStreamWriter
        //   332: dup
        //   333: aload 7
        //   335: invokevirtual 154	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   338: invokespecial 157	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   341: astore 9
        //   343: iload_2
        //   344: istore_1
        //   345: aload 9
        //   347: aload 8
        //   349: invokevirtual 160	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   352: iload_2
        //   353: istore_1
        //   354: aload 9
        //   356: invokevirtual 163	java/io/OutputStreamWriter:close	()V
        //   359: iload_2
        //   360: istore 4
        //   362: iload_2
        //   363: istore 6
        //   365: iload_2
        //   366: istore_1
        //   367: ldc -91
        //   369: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   372: iload_2
        //   373: istore 4
        //   375: iload_2
        //   376: istore 6
        //   378: iload_2
        //   379: istore_1
        //   380: new 167	java/lang/StringBuffer
        //   383: dup
        //   384: ldc -87
        //   386: invokespecial 170	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   389: astore 8
        //   391: iload_2
        //   392: istore 4
        //   394: iload_2
        //   395: istore 6
        //   397: iload_2
        //   398: istore_1
        //   399: new 172	java/io/BufferedReader
        //   402: dup
        //   403: new 174	java/io/InputStreamReader
        //   406: dup
        //   407: aload 7
        //   409: invokevirtual 178	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
        //   412: invokespecial 181	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   415: invokespecial 184	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   418: astore 7
        //   420: iload_2
        //   421: istore 4
        //   423: iload_2
        //   424: istore 6
        //   426: iload_2
        //   427: istore_1
        //   428: aload 7
        //   430: invokevirtual 187	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   433: astore 9
        //   435: aload 9
        //   437: ifnull +75 -> 512
        //   440: iload_2
        //   441: istore 4
        //   443: iload_2
        //   444: istore 6
        //   446: iload_2
        //   447: istore_1
        //   448: aload 8
        //   450: aload 9
        //   452: invokevirtual 190	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   455: pop
        //   456: goto -36 -> 420
        //   459: astore 7
        //   461: iload 4
        //   463: istore_1
        //   464: aload 7
        //   466: invokevirtual 194	java/lang/Object:getClass	()Ljava/lang/Class;
        //   469: ldc -60
        //   471: invokevirtual 200	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   474: ifeq +634 -> 1108
        //   477: iload 4
        //   479: istore_1
        //   480: new 51	java/lang/StringBuilder
        //   483: dup
        //   484: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   487: ldc -54
        //   489: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: aload 7
        //   494: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   497: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   500: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   503: iload 4
        //   505: istore_1
        //   506: aload 7
        //   508: invokestatic 212	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
        //   511: return
        //   512: iload_2
        //   513: istore 4
        //   515: iload_2
        //   516: istore 6
        //   518: iload_2
        //   519: istore_1
        //   520: aload 8
        //   522: invokevirtual 213	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   525: astore 7
        //   527: iload_2
        //   528: istore 4
        //   530: iload_2
        //   531: istore 6
        //   533: iload_2
        //   534: istore_1
        //   535: new 51	java/lang/StringBuilder
        //   538: dup
        //   539: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   542: ldc -41
        //   544: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   547: aload 7
        //   549: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   552: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   555: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   558: aconst_null
        //   559: astore 8
        //   561: iload_2
        //   562: istore 4
        //   564: iload_2
        //   565: istore 6
        //   567: iload_2
        //   568: istore_1
        //   569: new 112	org/json/JSONObject
        //   572: dup
        //   573: aload 7
        //   575: invokespecial 216	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   578: astore 7
        //   580: aload 7
        //   582: astore 8
        //   584: iload_2
        //   585: istore 5
        //   587: aload 8
        //   589: ifnull +302 -> 891
        //   592: iload_2
        //   593: istore 4
        //   595: iload_2
        //   596: istore 6
        //   598: iload_2
        //   599: istore_1
        //   600: new 51	java/lang/StringBuilder
        //   603: dup
        //   604: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   607: ldc -38
        //   609: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   612: aload 8
        //   614: invokevirtual 144	org/json/JSONObject:toString	()Ljava/lang/String;
        //   617: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   620: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   623: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   626: aconst_null
        //   627: astore 7
        //   629: iload_2
        //   630: istore 4
        //   632: iload_2
        //   633: istore 6
        //   635: iload_2
        //   636: istore_1
        //   637: aload 8
        //   639: ldc -36
        //   641: invokevirtual 224	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   644: astore 8
        //   646: iload_2
        //   647: istore 4
        //   649: iload_2
        //   650: istore 6
        //   652: aload 8
        //   654: astore 7
        //   656: iload_2
        //   657: istore_1
        //   658: new 51	java/lang/StringBuilder
        //   661: dup
        //   662: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   665: ldc -30
        //   667: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   670: aload 8
        //   672: invokevirtual 144	org/json/JSONObject:toString	()Ljava/lang/String;
        //   675: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   678: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   681: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   684: aload 8
        //   686: astore 7
        //   688: iload_2
        //   689: istore 5
        //   691: aload 7
        //   693: ifnull +198 -> 891
        //   696: ldc -87
        //   698: astore 8
        //   700: iload_2
        //   701: istore 4
        //   703: iload_2
        //   704: istore 6
        //   706: iload_2
        //   707: istore_1
        //   708: aload 7
        //   710: ldc -28
        //   712: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   715: astore 9
        //   717: aload 9
        //   719: astore 8
        //   721: iload_2
        //   722: istore 4
        //   724: iload_2
        //   725: istore 6
        //   727: iload_2
        //   728: istore_1
        //   729: aload 7
        //   731: ldc -23
        //   733: invokevirtual 237	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   736: istore_3
        //   737: iload_3
        //   738: istore 5
        //   740: iload_3
        //   741: ifge +150 -> 891
        //   744: iload_3
        //   745: istore 4
        //   747: iload_3
        //   748: istore 6
        //   750: iload_3
        //   751: istore_1
        //   752: iload_3
        //   753: istore_2
        //   754: invokestatic 241	com/kochava/android/tracker/Feature:access$2800	()Landroid/content/SharedPreferences;
        //   757: invokeinterface 247 1 0
        //   762: ldc -7
        //   764: aload 8
        //   766: invokeinterface 255 3 0
        //   771: invokeinterface 258 1 0
        //   776: pop
        //   777: iload_3
        //   778: istore 4
        //   780: iload_3
        //   781: istore 5
        //   783: iload_3
        //   784: istore 6
        //   786: iload_3
        //   787: istore_1
        //   788: iload_3
        //   789: istore_2
        //   790: invokestatic 262	com/kochava/android/tracker/Feature:access$2900	()Landroid/os/Handler;
        //   793: ifnull +98 -> 891
        //   796: iload_3
        //   797: istore 4
        //   799: iload_3
        //   800: istore 6
        //   802: iload_3
        //   803: istore_1
        //   804: iload_3
        //   805: istore_2
        //   806: invokestatic 268	android/os/Message:obtain	()Landroid/os/Message;
        //   809: astore 7
        //   811: iload_3
        //   812: istore 4
        //   814: iload_3
        //   815: istore 6
        //   817: iload_3
        //   818: istore_1
        //   819: iload_3
        //   820: istore_2
        //   821: new 270	android/os/Bundle
        //   824: dup
        //   825: invokespecial 271	android/os/Bundle:<init>	()V
        //   828: astore 9
        //   830: iload_3
        //   831: istore 4
        //   833: iload_3
        //   834: istore 6
        //   836: iload_3
        //   837: istore_1
        //   838: iload_3
        //   839: istore_2
        //   840: aload 9
        //   842: ldc -7
        //   844: aload 8
        //   846: invokevirtual 272	java/lang/String:toString	()Ljava/lang/String;
        //   849: invokevirtual 274	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   852: iload_3
        //   853: istore 4
        //   855: iload_3
        //   856: istore 6
        //   858: iload_3
        //   859: istore_1
        //   860: iload_3
        //   861: istore_2
        //   862: aload 7
        //   864: aload 9
        //   866: invokevirtual 278	android/os/Message:setData	(Landroid/os/Bundle;)V
        //   869: iload_3
        //   870: istore 4
        //   872: iload_3
        //   873: istore 6
        //   875: iload_3
        //   876: istore_1
        //   877: iload_3
        //   878: istore_2
        //   879: invokestatic 262	com/kochava/android/tracker/Feature:access$2900	()Landroid/os/Handler;
        //   882: aload 7
        //   884: invokevirtual 284	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   887: pop
        //   888: iload_3
        //   889: istore 5
        //   891: iload 5
        //   893: ifle -382 -> 511
        //   896: iload 5
        //   898: invokestatic 287	com/kochava/android/tracker/Feature:sendKVQuery	(I)V
        //   901: return
        //   902: astore 7
        //   904: iload_2
        //   905: istore 4
        //   907: iload_2
        //   908: istore 6
        //   910: iload_2
        //   911: istore_1
        //   912: new 51	java/lang/StringBuilder
        //   915: dup
        //   916: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   919: ldc_w 289
        //   922: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   925: aload 7
        //   927: invokevirtual 290	org/json/JSONException:toString	()Ljava/lang/String;
        //   930: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   933: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   936: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   939: goto -355 -> 584
        //   942: astore 7
        //   944: iload 6
        //   946: istore_1
        //   947: new 51	java/lang/StringBuilder
        //   950: dup
        //   951: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   954: ldc_w 292
        //   957: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   960: aload 7
        //   962: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   965: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   968: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   971: return
        //   972: astore 7
        //   974: aload 7
        //   976: invokevirtual 194	java/lang/Object:getClass	()Ljava/lang/Class;
        //   979: ldc -60
        //   981: invokevirtual 200	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   984: ifeq +152 -> 1136
        //   987: new 51	java/lang/StringBuilder
        //   990: dup
        //   991: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   994: ldc -54
        //   996: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: aload 7
        //   1001: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1004: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1007: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1010: aload 7
        //   1012: invokestatic 212	com/kochava/android/tracker/Feature:access$2300	(Ljava/lang/Exception;)V
        //   1015: return
        //   1016: astore 8
        //   1018: iload_2
        //   1019: istore 4
        //   1021: iload_2
        //   1022: istore 6
        //   1024: iload_2
        //   1025: istore_1
        //   1026: ldc_w 294
        //   1029: invokestatic 43	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
        //   1032: goto -344 -> 688
        //   1035: astore 7
        //   1037: new 51	java/lang/StringBuilder
        //   1040: dup
        //   1041: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1044: ldc_w 296
        //   1047: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1050: aload 7
        //   1052: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1055: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1058: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1061: iload_1
        //   1062: istore 5
        //   1064: goto -173 -> 891
        //   1067: astore 9
        //   1069: iload_2
        //   1070: istore 4
        //   1072: iload_2
        //   1073: istore 6
        //   1075: iload_2
        //   1076: istore_1
        //   1077: ldc_w 298
        //   1080: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1083: goto -362 -> 721
        //   1086: astore 7
        //   1088: iload_2
        //   1089: istore 4
        //   1091: iload_2
        //   1092: istore 6
        //   1094: iload_2
        //   1095: istore_1
        //   1096: ldc_w 300
        //   1099: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1102: iload_2
        //   1103: istore 5
        //   1105: goto -214 -> 891
        //   1108: iload 4
        //   1110: istore_1
        //   1111: new 51	java/lang/StringBuilder
        //   1114: dup
        //   1115: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1118: ldc_w 302
        //   1121: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1124: aload 7
        //   1126: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1129: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1132: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1135: return
        //   1136: new 51	java/lang/StringBuilder
        //   1139: dup
        //   1140: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   1143: ldc_w 302
        //   1146: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1149: aload 7
        //   1151: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   1154: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1157: invokestatic 208	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
        //   1160: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1161	0	this	4
        //   4	1107	1	i	int
        //   2	1101	2	j	int
        //   736	153	3	k	int
        //   360	749	4	m	int
        //   585	519	5	n	int
        //   363	730	6	i1	int
        //   117	312	7	localObject1	Object
        //   459	48	7	localIOException1	IOException
        //   525	358	7	localObject2	Object
        //   902	24	7	localJSONException1	JSONException
        //   942	19	7	localOutOfMemoryError	OutOfMemoryError
        //   972	39	7	localIOException2	IOException
        //   1035	16	7	localException	Exception
        //   1086	64	7	localJSONException2	JSONException
        //   203	642	8	localObject3	Object
        //   1016	1	8	localJSONException3	JSONException
        //   341	524	9	localObject4	Object
        //   1067	1	9	localJSONException4	JSONException
        // Exception table:
        //   from	to	target	type
        //   367	372	459	java/io/IOException
        //   380	391	459	java/io/IOException
        //   399	420	459	java/io/IOException
        //   428	435	459	java/io/IOException
        //   448	456	459	java/io/IOException
        //   520	527	459	java/io/IOException
        //   535	558	459	java/io/IOException
        //   569	580	459	java/io/IOException
        //   600	626	459	java/io/IOException
        //   637	646	459	java/io/IOException
        //   658	684	459	java/io/IOException
        //   708	717	459	java/io/IOException
        //   729	737	459	java/io/IOException
        //   754	777	459	java/io/IOException
        //   790	796	459	java/io/IOException
        //   806	811	459	java/io/IOException
        //   821	830	459	java/io/IOException
        //   840	852	459	java/io/IOException
        //   862	869	459	java/io/IOException
        //   879	888	459	java/io/IOException
        //   912	939	459	java/io/IOException
        //   1026	1032	459	java/io/IOException
        //   1077	1083	459	java/io/IOException
        //   1096	1102	459	java/io/IOException
        //   569	580	902	org/json/JSONException
        //   367	372	942	java/lang/OutOfMemoryError
        //   380	391	942	java/lang/OutOfMemoryError
        //   399	420	942	java/lang/OutOfMemoryError
        //   428	435	942	java/lang/OutOfMemoryError
        //   448	456	942	java/lang/OutOfMemoryError
        //   520	527	942	java/lang/OutOfMemoryError
        //   535	558	942	java/lang/OutOfMemoryError
        //   569	580	942	java/lang/OutOfMemoryError
        //   600	626	942	java/lang/OutOfMemoryError
        //   637	646	942	java/lang/OutOfMemoryError
        //   658	684	942	java/lang/OutOfMemoryError
        //   708	717	942	java/lang/OutOfMemoryError
        //   729	737	942	java/lang/OutOfMemoryError
        //   754	777	942	java/lang/OutOfMemoryError
        //   790	796	942	java/lang/OutOfMemoryError
        //   806	811	942	java/lang/OutOfMemoryError
        //   821	830	942	java/lang/OutOfMemoryError
        //   840	852	942	java/lang/OutOfMemoryError
        //   862	869	942	java/lang/OutOfMemoryError
        //   879	888	942	java/lang/OutOfMemoryError
        //   912	939	942	java/lang/OutOfMemoryError
        //   1026	1032	942	java/lang/OutOfMemoryError
        //   1077	1083	942	java/lang/OutOfMemoryError
        //   1096	1102	942	java/lang/OutOfMemoryError
        //   5	11	972	java/io/IOException
        //   13	25	972	java/io/IOException
        //   27	32	972	java/io/IOException
        //   34	40	972	java/io/IOException
        //   42	76	972	java/io/IOException
        //   78	119	972	java/io/IOException
        //   121	131	972	java/io/IOException
        //   133	142	972	java/io/IOException
        //   144	151	972	java/io/IOException
        //   153	161	972	java/io/IOException
        //   163	171	972	java/io/IOException
        //   173	179	972	java/io/IOException
        //   181	187	972	java/io/IOException
        //   189	194	972	java/io/IOException
        //   196	205	972	java/io/IOException
        //   207	217	972	java/io/IOException
        //   219	230	972	java/io/IOException
        //   232	243	972	java/io/IOException
        //   245	274	972	java/io/IOException
        //   276	286	972	java/io/IOException
        //   288	295	972	java/io/IOException
        //   297	320	972	java/io/IOException
        //   322	327	972	java/io/IOException
        //   329	343	972	java/io/IOException
        //   345	352	972	java/io/IOException
        //   354	359	972	java/io/IOException
        //   464	477	972	java/io/IOException
        //   480	503	972	java/io/IOException
        //   506	511	972	java/io/IOException
        //   947	971	972	java/io/IOException
        //   1111	1135	972	java/io/IOException
        //   637	646	1016	org/json/JSONException
        //   658	684	1016	org/json/JSONException
        //   5	11	1035	java/lang/Exception
        //   13	25	1035	java/lang/Exception
        //   27	32	1035	java/lang/Exception
        //   34	40	1035	java/lang/Exception
        //   42	76	1035	java/lang/Exception
        //   78	119	1035	java/lang/Exception
        //   121	131	1035	java/lang/Exception
        //   133	142	1035	java/lang/Exception
        //   144	151	1035	java/lang/Exception
        //   153	161	1035	java/lang/Exception
        //   163	171	1035	java/lang/Exception
        //   173	179	1035	java/lang/Exception
        //   181	187	1035	java/lang/Exception
        //   189	194	1035	java/lang/Exception
        //   196	205	1035	java/lang/Exception
        //   207	217	1035	java/lang/Exception
        //   219	230	1035	java/lang/Exception
        //   232	243	1035	java/lang/Exception
        //   245	274	1035	java/lang/Exception
        //   276	286	1035	java/lang/Exception
        //   288	295	1035	java/lang/Exception
        //   297	320	1035	java/lang/Exception
        //   322	327	1035	java/lang/Exception
        //   329	343	1035	java/lang/Exception
        //   345	352	1035	java/lang/Exception
        //   354	359	1035	java/lang/Exception
        //   367	372	1035	java/lang/Exception
        //   380	391	1035	java/lang/Exception
        //   399	420	1035	java/lang/Exception
        //   428	435	1035	java/lang/Exception
        //   448	456	1035	java/lang/Exception
        //   464	477	1035	java/lang/Exception
        //   480	503	1035	java/lang/Exception
        //   506	511	1035	java/lang/Exception
        //   520	527	1035	java/lang/Exception
        //   535	558	1035	java/lang/Exception
        //   569	580	1035	java/lang/Exception
        //   600	626	1035	java/lang/Exception
        //   637	646	1035	java/lang/Exception
        //   658	684	1035	java/lang/Exception
        //   708	717	1035	java/lang/Exception
        //   729	737	1035	java/lang/Exception
        //   754	777	1035	java/lang/Exception
        //   790	796	1035	java/lang/Exception
        //   806	811	1035	java/lang/Exception
        //   821	830	1035	java/lang/Exception
        //   840	852	1035	java/lang/Exception
        //   862	869	1035	java/lang/Exception
        //   879	888	1035	java/lang/Exception
        //   912	939	1035	java/lang/Exception
        //   947	971	1035	java/lang/Exception
        //   1026	1032	1035	java/lang/Exception
        //   1077	1083	1035	java/lang/Exception
        //   1096	1102	1035	java/lang/Exception
        //   1111	1135	1035	java/lang/Exception
        //   708	717	1067	org/json/JSONException
        //   729	737	1086	org/json/JSONException
        //   754	777	1086	org/json/JSONException
        //   790	796	1086	org/json/JSONException
        //   806	811	1086	org/json/JSONException
        //   821	830	1086	org/json/JSONException
        //   840	852	1086	org/json/JSONException
        //   862	869	1086	org/json/JSONException
        //   879	888	1086	org/json/JSONException
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
    String str = "" + getDeviceId() + ":::";
    str = str + getModel() + ":::";
    str = str + getBrand() + ":::";
    str = str + getAppVersion() + ":::";
    return str + getOSVersion();
  }
  
  public static void enableDebug(boolean paramBoolean)
  {
    Log.i("KochavaTracker", "enableDebug to " + paramBoolean);
    Global.DEBUG = paramBoolean;
  }
  
  private static void endAppSession()
  {
    if ((Build.VERSION.SDK_INT >= 14) && (!overrideAutomaticSessions))
    {
      if (badInit) {
        Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      }
    }
    else {
      return;
    }
    Logging.Log("Automatic Session End");
    if (!should_flush_in_background)
    {
      if (mTimer == null) {
        break label75;
      }
      Logging.Log("Session end, flush timer was on, canceling timer and flushing current events.");
      flush();
      mTimer.cancel();
      mTimer = null;
    }
    while (canSendSession)
    {
      eventSession("exit");
      return;
      label75:
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
        label302:
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
              break label302;
            }
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
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
    label1066:
    int i;
    do
    {
      JSONObject localJSONObject2;
      for (;;)
      {
        try
        {
          localJSONObject1.put("kochava_app_id", mAppId);
          localJSONObject1.put("kochava_device_id", getDeviceId());
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
              paramString.printStackTrace();
              Logging.LogError("event " + paramString);
              return;
              localJSONObject2.put("affinity_group", paramString);
              localJSONObject1.put("sdk_version", "Android20151109" + versionExtension);
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
                  break label1066;
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
              paramString.printStackTrace();
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
          paramString.printStackTrace();
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
    //   8: getstatic 398	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 443	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 89
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 1090	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload_0
    //   36: invokeinterface 1095 1 0
    //   41: istore_1
    //   42: iload_1
    //   43: ifne +24 -> 67
    //   46: aload_0
    //   47: ifnull +18 -> 65
    //   50: aload_0
    //   51: invokeinterface 1098 1 0
    //   56: ifne +9 -> 65
    //   59: aload_0
    //   60: invokeinterface 1101 1 0
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: astore_2
    //   69: aload_0
    //   70: astore_3
    //   71: aload_0
    //   72: aload_0
    //   73: ldc 89
    //   75: invokeinterface 1105 2 0
    //   80: invokeinterface 1106 2 0
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
    //   99: invokeinterface 1098 1 0
    //   104: ifne +11 -> 115
    //   107: aload_0
    //   108: invokeinterface 1101 1 0
    //   113: aload_2
    //   114: astore_3
    //   115: aload_3
    //   116: areturn
    //   117: astore_0
    //   118: aload_2
    //   119: astore_3
    //   120: new 638	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 1108
    //   130: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_0
    //   134: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   137: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   146: aload 4
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull -35 -> 115
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokeinterface 1098 1 0
    //   162: ifne -47 -> 115
    //   165: aload_2
    //   166: invokeinterface 1101 1 0
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
    //   190: invokeinterface 1098 1 0
    //   195: ifne +9 -> 204
    //   198: aload_3
    //   199: invokeinterface 1101 1 0
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
  
  private static String getDeviceId()
  {
    if ((prefs.contains("kochava_app_id_generated")) && (!prefs.getString("kochava_app_id_generated", "").equals(""))) {
      return prefs.getString("kochava_app_id_generated", "");
    }
    String str = UUID.randomUUID().toString().replaceAll("-", "");
    str = "KA" + str;
    prefs.edit().putString("kochava_app_id_generated", str).commit();
    return str;
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
      String str = getDeviceId();
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
        try
        {
          Feature.access$2202(true);
          Object localObject2 = this.val$e.getMessage();
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("message", localObject2);
          ((JSONObject)localObject1).put("os_version", Feature.access$3300());
          ((JSONObject)localObject1).put("device", Feature.access$4900() + "-" + Feature.access$5000());
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$2700());
          ((JSONObject)localObject2).put("action", "error");
          ((JSONObject)localObject2).put("data", localObject1);
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20151109" + Feature.versionExtension);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          Logging.Log("https log - posting to " + "http://" + Feature.hostControl + "/track/kvTracker.php");
          Object localObject3 = new URL("http://" + Feature.hostControl + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject2).toString();
          Logging.Log("https failure data:" + (String)localObject1);
          localObject2 = (HttpURLConnection)((URL)localObject3).openConnection();
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
    //   1: ifnull +2390 -> 2391
    //   4: aload_0
    //   5: aload_1
    //   6: putfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   9: iload_2
    //   10: putstatic 343	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   13: new 638	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   20: ldc_w 1276
    //   23: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: getstatic 323	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   29: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   38: getstatic 667	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   41: ifnonnull +10 -> 51
    //   44: aload_1
    //   45: invokevirtual 1280	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   48: putstatic 667	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   51: aload_0
    //   52: new 777	java/util/Timer
    //   55: dup
    //   56: invokespecial 1281	java/util/Timer:<init>	()V
    //   59: putfield 802	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   62: aload_0
    //   63: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   66: ldc -58
    //   68: iconst_0
    //   69: invokevirtual 673	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   72: putstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   75: new 999	com/kochava/android/tracker/DbAdapter
    //   78: dup
    //   79: aload_0
    //   80: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   83: invokespecial 1282	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   86: putstatic 566	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   89: aload_3
    //   90: ifnull +231 -> 321
    //   93: aload_3
    //   94: ldc_w 1284
    //   97: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   100: ifnull +44 -> 144
    //   103: aload_3
    //   104: ldc_w 1284
    //   107: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   110: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   113: ldc_w 835
    //   116: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   119: ifeq +25 -> 144
    //   122: aload_3
    //   123: ldc_w 1284
    //   126: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   129: checkcast 835	java/lang/Boolean
    //   132: invokevirtual 838	java/lang/Boolean:booleanValue	()Z
    //   135: istore_2
    //   136: iload_2
    //   137: invokestatic 1290	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   140: iload_2
    //   141: invokestatic 1293	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   144: aload_3
    //   145: ldc_w 1295
    //   148: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: ifnull +35 -> 186
    //   154: aload_3
    //   155: ldc_w 1295
    //   158: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   161: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   164: ldc_w 443
    //   167: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   170: ifeq +16 -> 186
    //   173: aload_3
    //   174: ldc_w 1295
    //   177: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   180: checkcast 443	java/lang/String
    //   183: putstatic 323	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   186: aload_3
    //   187: ldc_w 1297
    //   190: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: ifnull +38 -> 231
    //   196: aload_3
    //   197: ldc_w 1297
    //   200: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   203: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   206: ldc_w 835
    //   209: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   212: ifeq +19 -> 231
    //   215: aload_3
    //   216: ldc_w 1297
    //   219: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   222: checkcast 835	java/lang/Boolean
    //   225: invokevirtual 838	java/lang/Boolean:booleanValue	()Z
    //   228: putstatic 325	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   231: aload_3
    //   232: ldc_w 1299
    //   235: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: ifnull +38 -> 276
    //   241: aload_3
    //   242: ldc_w 1299
    //   245: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   248: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   251: ldc_w 835
    //   254: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   257: ifeq +19 -> 276
    //   260: aload_3
    //   261: ldc_w 1299
    //   264: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   267: checkcast 835	java/lang/Boolean
    //   270: invokevirtual 838	java/lang/Boolean:booleanValue	()Z
    //   273: putstatic 341	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   276: aload_3
    //   277: ldc_w 1301
    //   280: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   283: ifnull +38 -> 321
    //   286: aload_3
    //   287: ldc_w 1301
    //   290: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   293: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   296: ldc_w 835
    //   299: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   302: ifeq +19 -> 321
    //   305: aload_3
    //   306: ldc_w 1301
    //   309: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   312: checkcast 835	java/lang/Boolean
    //   315: invokevirtual 838	java/lang/Boolean:booleanValue	()Z
    //   318: putstatic 360	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   321: aload_0
    //   322: invokespecial 1303	com/kochava/android/tracker/Feature:initHandler	()V
    //   325: new 638	java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   332: ldc_w 1305
    //   335: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   341: ldc 109
    //   343: ldc_w 319
    //   346: invokeinterface 679 3 0
    //   351: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   354: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   357: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   360: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   363: ldc 109
    //   365: ldc_w 319
    //   368: invokeinterface 679 3 0
    //   373: ldc_w 319
    //   376: invokevirtual 683	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   379: ifne +26 -> 405
    //   382: new 403	org/json/JSONArray
    //   385: dup
    //   386: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   389: ldc 109
    //   391: ldc_w 319
    //   394: invokeinterface 679 3 0
    //   399: invokespecial 1306	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   402: putstatic 406	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   405: getstatic 763	android/os/Build$VERSION:SDK_INT	I
    //   408: bipush 14
    //   410: if_icmplt +2022 -> 2432
    //   413: getstatic 325	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   416: ifne +2016 -> 2432
    //   419: ldc -86
    //   421: new 638	java/lang/StringBuilder
    //   424: dup
    //   425: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   428: ldc_w 1308
    //   431: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: getstatic 325	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   437: invokevirtual 747	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   440: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   443: invokestatic 753	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   446: pop
    //   447: getstatic 667	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   450: checkcast 1310	android/app/Application
    //   453: new 53	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   456: dup
    //   457: aload_0
    //   458: invokespecial 1311	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   461: invokevirtual 1315	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   464: getstatic 667	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   467: new 56	com/kochava/android/tracker/Feature$MemoryBoss
    //   470: dup
    //   471: aload_0
    //   472: invokespecial 1316	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   475: invokevirtual 1320	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   478: iconst_1
    //   479: putstatic 1323	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   482: iconst_1
    //   483: putstatic 1326	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   486: aload_0
    //   487: aload_0
    //   488: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   491: invokevirtual 940	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   494: invokestatic 944	com/kochava/android/tracker/Feature:getAttributionId	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   497: putfield 946	com/kochava/android/tracker/Feature:mFbId	Ljava/lang/String;
    //   500: getstatic 341	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   503: ifne +19 -> 522
    //   506: new 26	com/kochava/android/tracker/Feature$2
    //   509: dup
    //   510: aload_0
    //   511: invokespecial 1327	com/kochava/android/tracker/Feature$2:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   514: iconst_0
    //   515: anewarray 1329	java/lang/Void
    //   518: invokevirtual 1335	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   521: pop
    //   522: aload_0
    //   523: aload_0
    //   524: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   527: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   530: aload_0
    //   531: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   534: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   537: iconst_0
    //   538: invokevirtual 1348	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   541: getfield 1353	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   544: putfield 1075	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   547: aload_0
    //   548: new 658	org/json/JSONObject
    //   551: dup
    //   552: invokespecial 812	org/json/JSONObject:<init>	()V
    //   555: putfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   558: aload_0
    //   559: new 658	org/json/JSONObject
    //   562: dup
    //   563: invokespecial 812	org/json/JSONObject:<init>	()V
    //   566: putfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   569: aload_0
    //   570: new 658	org/json/JSONObject
    //   573: dup
    //   574: invokespecial 812	org/json/JSONObject:<init>	()V
    //   577: putfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   580: aconst_null
    //   581: astore 13
    //   583: aconst_null
    //   584: astore 6
    //   586: aconst_null
    //   587: astore 9
    //   589: aconst_null
    //   590: astore 7
    //   592: ldc_w 825
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
    //   620: ldc_w 1361
    //   623: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   626: ifnull +38 -> 664
    //   629: aload 6
    //   631: astore 5
    //   633: aload_3
    //   634: ldc_w 1361
    //   637: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   640: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   643: ldc_w 443
    //   646: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   649: ifeq +15 -> 664
    //   652: aload_3
    //   653: ldc_w 1361
    //   656: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   659: checkcast 443	java/lang/String
    //   662: astore 5
    //   664: aload 7
    //   666: astore 6
    //   668: aload_3
    //   669: ldc -116
    //   671: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   674: ifnull +36 -> 710
    //   677: aload 7
    //   679: astore 6
    //   681: aload_3
    //   682: ldc -116
    //   684: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   687: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   690: ldc_w 443
    //   693: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   696: ifeq +14 -> 710
    //   699: aload_3
    //   700: ldc -116
    //   702: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   705: checkcast 443	java/lang/String
    //   708: astore 6
    //   710: aload_1
    //   711: astore 7
    //   713: aload_3
    //   714: ldc -73
    //   716: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   719: ifnull +35 -> 754
    //   722: aload_1
    //   723: astore 7
    //   725: aload_3
    //   726: ldc -73
    //   728: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   731: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   734: ldc_w 443
    //   737: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   740: ifeq +14 -> 754
    //   743: aload_3
    //   744: ldc -73
    //   746: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   749: checkcast 443	java/lang/String
    //   752: astore 7
    //   754: aload 8
    //   756: astore_1
    //   757: aload_3
    //   758: ldc_w 1363
    //   761: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   764: ifnull +33 -> 797
    //   767: aload_3
    //   768: ldc_w 1363
    //   771: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   774: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   777: ldc_w 443
    //   780: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   783: ifeq +1680 -> 2463
    //   786: aload_3
    //   787: ldc_w 1363
    //   790: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   793: checkcast 443	java/lang/String
    //   796: astore_1
    //   797: aload_3
    //   798: ldc_w 1365
    //   801: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   804: ifnull +34 -> 838
    //   807: aload_3
    //   808: ldc_w 1365
    //   811: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   814: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   817: ldc_w 443
    //   820: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   823: ifeq +15 -> 838
    //   826: aload_3
    //   827: ldc_w 1365
    //   830: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   833: checkcast 443	java/lang/String
    //   836: astore 8
    //   838: aload 14
    //   840: astore 8
    //   842: aload_3
    //   843: ldc_w 1367
    //   846: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   849: ifnull +38 -> 887
    //   852: aload 14
    //   854: astore 8
    //   856: aload_3
    //   857: ldc_w 1367
    //   860: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   863: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   866: ldc_w 443
    //   869: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   872: ifeq +15 -> 887
    //   875: aload_3
    //   876: ldc_w 1367
    //   879: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   882: checkcast 443	java/lang/String
    //   885: astore 8
    //   887: aload_3
    //   888: ldc_w 910
    //   891: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   894: ifnull +39 -> 933
    //   897: aload_3
    //   898: ldc_w 910
    //   901: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   904: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   907: ldc_w 835
    //   910: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   913: ifeq +20 -> 933
    //   916: aload_0
    //   917: aload_3
    //   918: ldc_w 910
    //   921: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   924: checkcast 835	java/lang/Boolean
    //   927: invokevirtual 838	java/lang/Boolean:booleanValue	()Z
    //   930: putfield 417	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   933: aload_3
    //   934: ldc_w 928
    //   937: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   940: ifnull +115 -> 1055
    //   943: aload_3
    //   944: ldc_w 928
    //   947: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   950: instanceof 440
    //   953: ifeq +102 -> 1055
    //   956: aload_3
    //   957: ldc_w 928
    //   960: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   963: checkcast 440	java/util/HashMap
    //   966: putstatic 1369	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   969: new 658	org/json/JSONObject
    //   972: dup
    //   973: invokespecial 812	org/json/JSONObject:<init>	()V
    //   976: putstatic 926	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   979: getstatic 1369	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   982: invokeinterface 982 1 0
    //   987: invokeinterface 985 1 0
    //   992: astore 9
    //   994: aload 9
    //   996: invokeinterface 708 1 0
    //   1001: ifeq +54 -> 1055
    //   1004: aload 9
    //   1006: invokeinterface 712 1 0
    //   1011: checkcast 987	java/util/Map$Entry
    //   1014: astore 10
    //   1016: getstatic 926	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   1019: aload 10
    //   1021: invokeinterface 990 1 0
    //   1026: checkcast 443	java/lang/String
    //   1029: aload 10
    //   1031: invokeinterface 993 1 0
    //   1036: checkcast 443	java/lang/String
    //   1039: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1042: pop
    //   1043: aload 9
    //   1045: invokeinterface 1372 1 0
    //   1050: goto -56 -> 994
    //   1053: astore 9
    //   1055: aload_3
    //   1056: ldc_w 934
    //   1059: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1062: ifnull +36 -> 1098
    //   1065: aload_3
    //   1066: ldc_w 934
    //   1069: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1072: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1075: ldc_w 443
    //   1078: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1081: ifeq +17 -> 1098
    //   1084: aload_0
    //   1085: aload_3
    //   1086: ldc_w 934
    //   1089: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1092: checkcast 443	java/lang/String
    //   1095: putfield 930	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
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
    //   1118: ldc_w 1373
    //   1121: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1147: ldc_w 1373
    //   1150: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1153: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1156: ldc_w 1375
    //   1159: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1162: ifeq +77 -> 1239
    //   1165: aload_3
    //   1166: ldc_w 1373
    //   1169: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1172: checkcast 1375	java/lang/Integer
    //   1175: invokevirtual 1378	java/lang/Integer:intValue	()I
    //   1178: istore 4
    //   1180: iload 4
    //   1182: iconst_1
    //   1183: if_icmpge +1327 -> 2510
    //   1186: new 638	java/lang/StringBuilder
    //   1189: dup
    //   1190: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1193: ldc_w 1380
    //   1196: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1199: iload 4
    //   1201: invokevirtual 1039	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1204: ldc_w 1382
    //   1207: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1213: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1216: iconst_1
    //   1217: putstatic 329	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
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
    //   1246: invokevirtual 447	java/lang/String:trim	()Ljava/lang/String;
    //   1249: invokevirtual 451	java/lang/String:length	()I
    //   1252: ifeq +8 -> 1260
    //   1255: aload 11
    //   1257: putstatic 321	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1260: aload 10
    //   1262: ifnull +17 -> 1279
    //   1265: aload 10
    //   1267: ldc -37
    //   1269: invokevirtual 1385	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1272: ifeq +7 -> 1279
    //   1275: iconst_1
    //   1276: putstatic 374	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1279: aload_0
    //   1280: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1283: ldc 95
    //   1285: iconst_0
    //   1286: invokevirtual 673	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1289: putstatic 532	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1292: aload 9
    //   1294: ifnull +1308 -> 2602
    //   1297: aload 9
    //   1299: invokevirtual 447	java/lang/String:trim	()Ljava/lang/String;
    //   1302: invokevirtual 451	java/lang/String:length	()I
    //   1305: ifeq +1297 -> 2602
    //   1308: aload 9
    //   1310: putstatic 620	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1313: aload_0
    //   1314: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1317: ldc -116
    //   1319: aload 9
    //   1321: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1324: pop
    //   1325: aload_0
    //   1326: getfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1329: ldc -116
    //   1331: aload 9
    //   1333: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1336: pop
    //   1337: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1340: ldc -76
    //   1342: ldc_w 319
    //   1345: invokeinterface 679 3 0
    //   1350: ldc_w 319
    //   1353: invokevirtual 683	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1356: ifeq +26 -> 1382
    //   1359: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1362: invokeinterface 1133 1 0
    //   1367: ldc -76
    //   1369: aload 9
    //   1371: invokeinterface 1139 3 0
    //   1376: invokeinterface 1142 1 0
    //   1381: pop
    //   1382: aload_0
    //   1383: aload 12
    //   1385: invokespecial 628	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1388: aload_0
    //   1389: getfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1392: ldc_w 1387
    //   1395: aload_0
    //   1396: invokespecial 899	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1399: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload_0
    //   1404: getfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1407: ldc_w 1389
    //   1410: ldc_w 1391
    //   1413: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1416: pop
    //   1417: aload_0
    //   1418: getfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1421: ldc_w 1393
    //   1424: ldc_w 1395
    //   1427: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1430: pop
    //   1431: aload_0
    //   1432: getfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1435: ldc -73
    //   1437: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1440: ldc -73
    //   1442: ldc_w 825
    //   1445: invokeinterface 679 3 0
    //   1450: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1453: pop
    //   1454: aload_0
    //   1455: getfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1458: ldc -73
    //   1460: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1463: ldc -73
    //   1465: ldc_w 825
    //   1468: invokeinterface 679 3 0
    //   1473: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1476: pop
    //   1477: aload_0
    //   1478: getfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1481: ldc_w 1393
    //   1484: ldc_w 1395
    //   1487: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1490: pop
    //   1491: aload_0
    //   1492: getfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1495: ldc -73
    //   1497: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1500: ldc -73
    //   1502: ldc_w 825
    //   1505: invokeinterface 679 3 0
    //   1510: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1513: pop
    //   1514: aload_0
    //   1515: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1518: ldc_w 865
    //   1521: new 638	java/lang/StringBuilder
    //   1524: dup
    //   1525: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1528: ldc_w 867
    //   1531: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1534: getstatic 323	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1537: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1543: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1546: pop
    //   1547: aload_0
    //   1548: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1551: ldc_w 1397
    //   1554: ldc_w 1399
    //   1557: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1560: pop
    //   1561: aload_0
    //   1562: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1565: ldc_w 995
    //   1568: aload_0
    //   1569: getfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1572: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1575: pop
    //   1576: aload_0
    //   1577: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1580: ldc_w 1401
    //   1583: aload_0
    //   1584: getfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1587: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1590: pop
    //   1591: invokestatic 644	java/lang/System:currentTimeMillis	()J
    //   1594: ldc2_w 645
    //   1597: ldiv
    //   1598: putstatic 349	com/kochava/android/tracker/Feature:startTime	J
    //   1601: iconst_0
    //   1602: istore 4
    //   1604: ldc_w 319
    //   1607: astore_3
    //   1608: new 719	android/content/ComponentName
    //   1611: dup
    //   1612: aload_0
    //   1613: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1616: ldc_w 1403
    //   1619: invokespecial 731	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1622: astore_1
    //   1623: aload_0
    //   1624: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1627: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1630: aload_1
    //   1631: iconst_0
    //   1632: invokevirtual 1407	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1635: pop
    //   1636: ldc_w 1409
    //   1639: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1642: aload_3
    //   1643: astore_1
    //   1644: aload_0
    //   1645: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1648: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1651: ldc_w 1411
    //   1654: aload_0
    //   1655: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1658: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1661: invokevirtual 1414	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1664: ifge +27 -> 1691
    //   1667: iconst_1
    //   1668: istore 4
    //   1670: new 638	java/lang/StringBuilder
    //   1673: dup
    //   1674: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1677: aload_3
    //   1678: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1681: ldc_w 1416
    //   1684: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1687: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1690: astore_1
    //   1691: aload_1
    //   1692: astore_3
    //   1693: aload_0
    //   1694: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1697: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1700: ldc_w 1418
    //   1703: aload_0
    //   1704: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1707: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1710: invokevirtual 1414	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1713: ifge +27 -> 1740
    //   1716: iconst_1
    //   1717: istore 4
    //   1719: new 638	java/lang/StringBuilder
    //   1722: dup
    //   1723: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1726: aload_1
    //   1727: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1730: ldc_w 1420
    //   1733: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1736: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1739: astore_3
    //   1740: aload_3
    //   1741: astore_1
    //   1742: aload_0
    //   1743: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1746: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1749: ldc_w 1422
    //   1752: aload_0
    //   1753: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1756: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1759: invokevirtual 1414	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1762: ifge +27 -> 1789
    //   1765: iconst_1
    //   1766: istore 4
    //   1768: new 638	java/lang/StringBuilder
    //   1771: dup
    //   1772: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1775: aload_3
    //   1776: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1779: ldc_w 1424
    //   1782: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1785: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1788: astore_1
    //   1789: iload 4
    //   1791: ifeq +13 -> 1804
    //   1794: ldc_w 1426
    //   1797: invokestatic 1429	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1800: aload_1
    //   1801: invokestatic 1429	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1804: aload_0
    //   1805: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1808: ldc_w 1431
    //   1811: invokevirtual 952	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1814: checkcast 1433	android/telephony/TelephonyManager
    //   1817: invokevirtual 1436	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1820: putstatic 879	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1823: aload_0
    //   1824: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1827: ldc_w 1438
    //   1830: invokevirtual 952	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1833: checkcast 1440	android/net/wifi/WifiManager
    //   1836: invokevirtual 1444	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1839: invokevirtual 1449	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1842: putstatic 875	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1845: aload_0
    //   1846: invokespecial 1451	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1849: putstatic 613	com/kochava/android/tracker/Feature:mUserAgent	Ljava/lang/String;
    //   1852: aload_0
    //   1853: invokestatic 617	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1856: putfield 1453	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1859: aload_0
    //   1860: invokestatic 610	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1863: putfield 1455	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1866: aload_0
    //   1867: ldc_w 1457
    //   1870: putfield 1079	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1873: aload_0
    //   1874: ldc_w 1459
    //   1877: putfield 1083	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1880: aload_0
    //   1881: ldc_w 319
    //   1884: putfield 543	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1887: aload_0
    //   1888: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1891: invokevirtual 1280	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1894: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1897: astore_3
    //   1898: aload_3
    //   1899: aload_0
    //   1900: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1903: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1906: iconst_0
    //   1907: invokevirtual 1463	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1910: astore_1
    //   1911: aload_1
    //   1912: ifnull +1034 -> 2946
    //   1915: aload_3
    //   1916: aload_1
    //   1917: invokevirtual 1467	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1920: astore_1
    //   1921: aload_0
    //   1922: aload_1
    //   1923: checkcast 443	java/lang/String
    //   1926: checkcast 443	java/lang/String
    //   1929: putfield 1079	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1932: new 638	java/lang/StringBuilder
    //   1935: dup
    //   1936: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1939: ldc_w 1469
    //   1942: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1945: aload_0
    //   1946: getfield 1079	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1949: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1952: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1955: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1958: aload_0
    //   1959: new 638	java/lang/StringBuilder
    //   1962: dup
    //   1963: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   1966: aload_0
    //   1967: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1970: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1973: aload_0
    //   1974: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1977: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1980: iconst_0
    //   1981: invokevirtual 1348	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1984: getfield 1472	android/content/pm/PackageInfo:versionCode	I
    //   1987: invokevirtual 1039	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1990: ldc_w 319
    //   1993: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1996: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1999: putfield 1083	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2002: new 638	java/lang/StringBuilder
    //   2005: dup
    //   2006: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2009: ldc_w 1474
    //   2012: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2015: aload_0
    //   2016: getfield 1083	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2019: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2022: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2025: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2028: aload_0
    //   2029: aload_0
    //   2030: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2033: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2036: aload_0
    //   2037: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2040: invokevirtual 1342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2043: iconst_0
    //   2044: invokevirtual 1348	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2047: getfield 1477	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2050: putfield 543	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2053: new 638	java/lang/StringBuilder
    //   2056: dup
    //   2057: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2060: ldc_w 1479
    //   2063: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2066: aload_0
    //   2067: getfield 543	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2070: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2073: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2076: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2079: aload_0
    //   2080: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2083: ldc_w 948
    //   2086: invokevirtual 952	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2089: checkcast 954	android/view/WindowManager
    //   2092: astore_1
    //   2093: aload_0
    //   2094: aload_1
    //   2095: invokeinterface 961 1 0
    //   2100: invokevirtual 1482	android/view/Display:getHeight	()I
    //   2103: putfield 890	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2106: aload_0
    //   2107: aload_1
    //   2108: invokeinterface 961 1 0
    //   2113: invokevirtual 1485	android/view/Display:getWidth	()I
    //   2116: putfield 894	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2119: new 638	java/lang/StringBuilder
    //   2122: dup
    //   2123: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2126: ldc_w 1487
    //   2129: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2132: aload_0
    //   2133: getfield 890	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2136: invokevirtual 1039	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2139: ldc_w 1489
    //   2142: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2145: aload_0
    //   2146: getfield 894	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2149: invokevirtual 1039	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2152: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2155: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2158: aload_0
    //   2159: aload_0
    //   2160: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2163: invokevirtual 940	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   2166: ldc_w 905
    //   2169: invokestatic 1494	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   2172: putfield 907	com/kochava/android/tracker/Feature:mAndroidID	Ljava/lang/String;
    //   2175: aload_0
    //   2176: invokestatic 529	com/kochava/android/tracker/Feature:getDeviceId	()Ljava/lang/String;
    //   2179: putfield 1496	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2182: aload_0
    //   2183: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2186: ldc_w 814
    //   2189: invokestatic 529	com/kochava/android/tracker/Feature:getDeviceId	()Ljava/lang/String;
    //   2192: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2195: pop
    //   2196: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2199: ldc -70
    //   2201: ldc_w 319
    //   2204: invokeinterface 679 3 0
    //   2209: ldc -37
    //   2211: invokevirtual 683	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2214: ifne +150 -> 2364
    //   2217: aload_0
    //   2218: getfield 632	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2221: invokevirtual 1339	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2224: astore 5
    //   2226: aload 5
    //   2228: sipush 128
    //   2231: invokevirtual 1500	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2234: invokeinterface 843 1 0
    //   2239: astore 6
    //   2241: aload 6
    //   2243: invokeinterface 708 1 0
    //   2248: ifeq +116 -> 2364
    //   2251: aload 6
    //   2253: invokeinterface 712 1 0
    //   2258: checkcast 1502	android/content/pm/ApplicationInfo
    //   2261: astore 7
    //   2263: aload 7
    //   2265: invokestatic 1506	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2268: istore_2
    //   2269: iload_2
    //   2270: ifne -29 -> 2241
    //   2273: aconst_null
    //   2274: astore_1
    //   2275: aload 5
    //   2277: aload 7
    //   2279: getfield 1507	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2282: iconst_0
    //   2283: invokevirtual 1348	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2286: astore_3
    //   2287: aload_3
    //   2288: astore_1
    //   2289: aload_1
    //   2290: ifnull -49 -> 2241
    //   2293: getstatic 358	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2296: new 47	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2299: dup
    //   2300: aload_0
    //   2301: aload_1
    //   2302: getfield 1353	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2305: new 638	java/lang/StringBuilder
    //   2308: dup
    //   2309: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2312: aload_1
    //   2313: getfield 1510	android/content/pm/PackageInfo:firstInstallTime	J
    //   2316: invokevirtual 650	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2319: ldc_w 319
    //   2322: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2325: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2328: new 638	java/lang/StringBuilder
    //   2331: dup
    //   2332: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2335: aload_1
    //   2336: getfield 1513	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2339: invokevirtual 650	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2342: ldc_w 319
    //   2345: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2348: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2351: invokespecial 1516	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2354: invokeinterface 1519 2 0
    //   2359: pop
    //   2360: goto -119 -> 2241
    //   2363: astore_1
    //   2364: getstatic 388	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2367: aload_0
    //   2368: getfield 424	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2371: ldc2_w 1520
    //   2374: getstatic 1527	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2377: invokeinterface 1532 5 0
    //   2382: pop
    //   2383: aload_0
    //   2384: ldc_w 1533
    //   2387: invokevirtual 1536	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2390: return
    //   2391: ldc_w 1538
    //   2394: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2397: iconst_1
    //   2398: putstatic 368	com/kochava/android/tracker/Feature:badInit	Z
    //   2401: return
    //   2402: astore_1
    //   2403: new 638	java/lang/StringBuilder
    //   2406: dup
    //   2407: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2410: ldc_w 1540
    //   2413: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2416: aload_1
    //   2417: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   2420: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2423: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2426: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2429: goto -2024 -> 405
    //   2432: ldc -86
    //   2434: new 638	java/lang/StringBuilder
    //   2437: dup
    //   2438: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2441: ldc_w 1542
    //   2444: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2447: getstatic 325	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2450: invokevirtual 747	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2453: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2456: invokestatic 753	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   2459: pop
    //   2460: goto -1974 -> 486
    //   2463: aload 8
    //   2465: astore_1
    //   2466: aload_3
    //   2467: ldc_w 1363
    //   2470: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2473: invokevirtual 1288	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2476: ldc_w 835
    //   2479: invokevirtual 1012	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2482: ifeq -1685 -> 797
    //   2485: aload 8
    //   2487: astore_1
    //   2488: aload_3
    //   2489: ldc_w 1363
    //   2492: invokevirtual 833	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2495: checkcast 835	java/lang/Boolean
    //   2498: invokevirtual 838	java/lang/Boolean:booleanValue	()Z
    //   2501: ifeq -1704 -> 797
    //   2504: ldc -37
    //   2506: astore_1
    //   2507: goto -1710 -> 797
    //   2510: iload 4
    //   2512: sipush 360
    //   2515: if_icmple +42 -> 2557
    //   2518: new 638	java/lang/StringBuilder
    //   2521: dup
    //   2522: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2525: ldc_w 1380
    //   2528: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2531: iload 4
    //   2533: invokevirtual 1039	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2536: ldc_w 1544
    //   2539: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2542: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2545: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2548: ldc_w 1545
    //   2551: putstatic 327	com/kochava/android/tracker/Feature:flush_rate	I
    //   2554: goto -1338 -> 1216
    //   2557: iload 4
    //   2559: bipush 60
    //   2561: imul
    //   2562: sipush 1000
    //   2565: imul
    //   2566: putstatic 327	com/kochava/android/tracker/Feature:flush_rate	I
    //   2569: new 638	java/lang/StringBuilder
    //   2572: dup
    //   2573: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2576: ldc_w 1547
    //   2579: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2582: iload 4
    //   2584: invokevirtual 1039	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2587: ldc_w 1549
    //   2590: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2593: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2596: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2599: goto -1383 -> 1216
    //   2602: aload 13
    //   2604: ifnull +177 -> 2781
    //   2607: aload 13
    //   2609: invokevirtual 447	java/lang/String:trim	()Ljava/lang/String;
    //   2612: invokevirtual 451	java/lang/String:length	()I
    //   2615: ifeq +166 -> 2781
    //   2618: aload_0
    //   2619: getfield 1355	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2622: ldc_w 1361
    //   2625: aload 13
    //   2627: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2630: pop
    //   2631: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2634: ldc -76
    //   2636: ldc_w 319
    //   2639: invokeinterface 679 3 0
    //   2644: ldc_w 319
    //   2647: invokevirtual 683	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2650: ifeq +26 -> 2676
    //   2653: getstatic 506	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2656: invokeinterface 1133 1 0
    //   2661: ldc -76
    //   2663: aload 13
    //   2665: invokeinterface 1139 3 0
    //   2670: invokeinterface 1142 1 0
    //   2675: pop
    //   2676: aload 9
    //   2678: ifnull +76 -> 2754
    //   2681: aload 9
    //   2683: invokevirtual 447	java/lang/String:trim	()Ljava/lang/String;
    //   2686: invokevirtual 451	java/lang/String:length	()I
    //   2689: ifeq +65 -> 2754
    //   2692: aload 9
    //   2694: putstatic 620	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2697: aload_0
    //   2698: getfield 1357	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2701: ldc -116
    //   2703: aload 9
    //   2705: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2708: pop
    //   2709: aload_0
    //   2710: getfield 1359	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2713: ldc -116
    //   2715: aload 9
    //   2717: invokevirtual 661	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2720: pop
    //   2721: goto -1339 -> 1382
    //   2724: astore_1
    //   2725: new 638	java/lang/StringBuilder
    //   2728: dup
    //   2729: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2732: ldc_w 1551
    //   2735: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2738: aload_1
    //   2739: invokevirtual 1552	org/json/JSONException:toString	()Ljava/lang/String;
    //   2742: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2745: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2748: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2751: goto -1160 -> 1591
    //   2754: new 638	java/lang/StringBuilder
    //   2757: dup
    //   2758: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2761: ldc_w 1554
    //   2764: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2767: aload 13
    //   2769: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2772: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2775: putstatic 620	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2778: goto -1396 -> 1382
    //   2781: ldc_w 1556
    //   2784: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2787: iconst_1
    //   2788: putstatic 368	com/kochava/android/tracker/Feature:badInit	Z
    //   2791: return
    //   2792: astore_1
    //   2793: iconst_1
    //   2794: istore 4
    //   2796: new 638	java/lang/StringBuilder
    //   2799: dup
    //   2800: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2803: ldc_w 319
    //   2806: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2809: ldc_w 1558
    //   2812: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2815: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2818: astore_3
    //   2819: goto -1177 -> 1642
    //   2822: astore_1
    //   2823: new 638	java/lang/StringBuilder
    //   2826: dup
    //   2827: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2830: ldc_w 1560
    //   2833: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2836: aload_1
    //   2837: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   2840: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2843: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2846: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2849: goto -1026 -> 1823
    //   2852: astore_1
    //   2853: new 638	java/lang/StringBuilder
    //   2856: dup
    //   2857: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2860: ldc_w 1562
    //   2863: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2866: aload_1
    //   2867: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   2870: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2873: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2876: invokestatic 768	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2879: goto -1034 -> 1845
    //   2882: astore 5
    //   2884: aconst_null
    //   2885: astore_1
    //   2886: new 638	java/lang/StringBuilder
    //   2889: dup
    //   2890: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2893: ldc_w 1564
    //   2896: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2899: aload 5
    //   2901: invokevirtual 1565	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2904: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2907: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2910: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2913: goto -1002 -> 1911
    //   2916: astore_1
    //   2917: new 638	java/lang/StringBuilder
    //   2920: dup
    //   2921: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2924: ldc_w 1564
    //   2927: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2930: aload_1
    //   2931: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   2934: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2937: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2940: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2943: goto -985 -> 1958
    //   2946: ldc_w 1567
    //   2949: astore_1
    //   2950: goto -1029 -> 1921
    //   2953: astore_1
    //   2954: new 638	java/lang/StringBuilder
    //   2957: dup
    //   2958: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2961: ldc_w 1569
    //   2964: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2967: aload_1
    //   2968: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   2971: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2974: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2977: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2980: goto -952 -> 2028
    //   2983: astore_1
    //   2984: new 638	java/lang/StringBuilder
    //   2987: dup
    //   2988: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   2991: ldc_w 1571
    //   2994: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2997: aload_1
    //   2998: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   3001: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3004: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3007: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3010: goto -931 -> 2079
    //   3013: astore_1
    //   3014: new 638	java/lang/StringBuilder
    //   3017: dup
    //   3018: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   3021: ldc_w 1573
    //   3024: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3027: aload_1
    //   3028: invokevirtual 1065	java/lang/Exception:toString	()Ljava/lang/String;
    //   3031: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3034: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3037: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3040: goto -882 -> 2158
    //   3043: astore_1
    //   3044: aload_1
    //   3045: invokevirtual 861	org/json/JSONException:printStackTrace	()V
    //   3048: goto -852 -> 2196
    //   3051: astore_3
    //   3052: new 638	java/lang/StringBuilder
    //   3055: dup
    //   3056: invokespecial 639	java/lang/StringBuilder:<init>	()V
    //   3059: ldc_w 1575
    //   3062: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3065: aload 7
    //   3067: getfield 1507	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3070: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3073: ldc_w 1577
    //   3076: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3079: aload_3
    //   3080: invokevirtual 1565	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3083: invokevirtual 653	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3086: invokevirtual 656	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3089: invokestatic 699	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3092: goto -803 -> 2289
    //   3095: astore_1
    //   3096: goto -2549 -> 547
    //   3099: astore_1
    //   3100: goto -2600 -> 500
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3103	0	this	Feature
    //   0	3103	1	paramContext	Context
    //   0	3103	2	paramBoolean	boolean
    //   0	3103	3	paramHashMap	HashMap<String, Object>
    //   1178	1617	4	i	int
    //   617	1659	5	localObject1	Object
    //   2882	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   584	1668	6	localObject2	Object
    //   590	2476	7	localObject3	Object
    //   600	1886	8	localObject4	Object
    //   587	457	9	localIterator	Iterator
    //   1053	1	9	localException	Exception
    //   1100	1616	9	localObject5	Object
    //   597	669	10	localObject6	Object
    //   603	653	11	localObject7	Object
    //   609	775	12	localObject8	Object
    //   581	2187	13	localObject9	Object
    //   606	247	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   956	994	1053	java/lang/Exception
    //   994	1050	1053	java/lang/Exception
    //   2196	2241	2363	java/lang/Exception
    //   2241	2269	2363	java/lang/Exception
    //   2275	2287	2363	java/lang/Exception
    //   2293	2360	2363	java/lang/Exception
    //   3052	3092	2363	java/lang/Exception
    //   382	405	2402	java/lang/Exception
    //   1297	1382	2724	org/json/JSONException
    //   1382	1591	2724	org/json/JSONException
    //   2607	2676	2724	org/json/JSONException
    //   2681	2721	2724	org/json/JSONException
    //   2754	2778	2724	org/json/JSONException
    //   2781	2791	2724	org/json/JSONException
    //   1623	1642	2792	android/content/pm/PackageManager$NameNotFoundException
    //   1804	1823	2822	java/lang/Exception
    //   1823	1845	2852	java/lang/Exception
    //   1898	1911	2882	android/content/pm/PackageManager$NameNotFoundException
    //   1887	1898	2916	java/lang/Exception
    //   1898	1911	2916	java/lang/Exception
    //   1915	1921	2916	java/lang/Exception
    //   1921	1958	2916	java/lang/Exception
    //   2886	2913	2916	java/lang/Exception
    //   1958	2028	2953	java/lang/Exception
    //   2028	2079	2983	java/lang/Exception
    //   2079	2158	3013	java/lang/Exception
    //   2182	2196	3043	org/json/JSONException
    //   2275	2287	3051	android/content/pm/PackageManager$NameNotFoundException
    //   522	547	3095	java/lang/Exception
    //   486	500	3099	java/lang/Exception
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
        Logging.Log("An error occured during que initial. " + localJSONException);
        localJSONException.printStackTrace();
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
          ((JSONObject)localObject1).put("kochava_device_id", Feature.access$2700());
          ((JSONObject)localObject1).put("sdk_version", "Android20151109" + Feature.versionExtension);
          ((JSONObject)localObject1).put("sdk_protocol", "4");
          if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty()))
          {
            Logging.Log("Push token - hostControl was empty, using default");
            Feature.access$402("control.kochava.com");
          }
          Logging.Log("kvPush - posting to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject3 = new URL("https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject1).toString();
          Logging.Log("kvPush data:" + (String)localObject1);
          localObject3 = (HttpsURLConnection)((URL)localObject3).openConnection();
          ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", Feature.mUserAgent);
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
          Feature.prefs.edit().putString("register_remove_token", this.val$pushCommand.toString()).commit();
          localException.printStackTrace();
          return;
        }
        Logging.Log("Result: " + (String)localObject2);
        Object localObject2 = new JSONObject((String)localObject2);
        if ((localObject2 != null) && (((JSONObject)localObject2).get("success").toString().equals("1")))
        {
          Feature.prefs.edit().remove("register_remove_token").commit();
          return;
        }
        Logging.LogError("Did not get a good response, saving push command: " + this.val$pushCommand.toString());
        Feature.prefs.edit().putString("register_remove_token", this.val$pushCommand.toString()).commit();
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
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$2700());
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20151109" + Feature.versionExtension);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("outside_services", this.val$services_found);
          ((JSONObject)localObject2).put("data", localObject1);
          if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
            Feature.access$402("control.kochava.com");
          }
          Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
          localObject1 = (HttpsURLConnection)new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection();
          ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", Feature.mUserAgent);
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
      prefs = this.mContext.getSharedPreferences("initPrefs", 0);
      prefs.edit().putString("currency", paramString).commit();
    }
  }
  
  public static void setErrorDebug(boolean paramBoolean)
  {
    Log.i("KochavaTracker", "setErrorDebug to " + paramBoolean);
    Global.DEBUGERROR = paramBoolean;
  }
  
  private static void startAppSession()
  {
    if ((Build.VERSION.SDK_INT >= 14) && (!overrideAutomaticSessions))
    {
      if (!badInit) {
        break label26;
      }
      Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
    }
    for (;;)
    {
      return;
      label26:
      Logging.Log("Automatic Session start");
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
        if ((prefs == null) || (prefs.getString("register_remove_token", "").equals(""))) {}
      }
      try
      {
        registerRemoveToken(new JSONObject(prefs.getString("register_remove_token", "")));
        if ((prefs != null) && (prefs.getString("initBool", "").equals("true"))) {
          if (canSendSession)
          {
            eventSession("launch");
            return;
            Logging.Log("Session start, flush timer was already on.");
          }
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Logging.Log("Problem getting token from shared prefs.");
          localJSONException.printStackTrace();
        }
        Logging.Log("Session events disabled by server.");
      }
    }
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
        localJSONObject1.put("kochava_device_id", getDeviceId());
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
    if (overrideAutomaticSessions)
    {
      if (badInit) {
        Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      }
    }
    else {
      return;
    }
    Logging.Log("Manual Session End");
    if (!should_flush_in_background)
    {
      if (mTimer == null) {
        break label67;
      }
      Logging.Log("Session end, flush timer was on, canceling timer and flushing current events.");
      flush();
      mTimer.cancel();
      mTimer = null;
    }
    while (canSendSession)
    {
      eventSession("exit");
      return;
      label67:
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
    if (overrideAutomaticSessions)
    {
      if (badInit) {
        Logging.LogError("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      }
    }
    else {
      return;
    }
    Logging.Log("Manual Session Start");
    if (!should_flush_in_background)
    {
      if (mTimer != null) {
        break label144;
      }
      Logging.Log("Session start, flush timer was off and is not first launch, starting periodic flush timer.");
      mTimer = new Timer();
      mTimer.schedule(new TimerTask()
      {
        public void run() {}
      }, flush_rate, flush_rate);
    }
    for (;;)
    {
      startTime = System.currentTimeMillis() / 1000L;
      if ((doGatherLocation) && (oldLocationTooStale())) {
        LocationDirector.getInstance(appContext).getLocation();
      }
      if ((prefs == null) || (!prefs.getString("initBool", "").equals("true"))) {
        break;
      }
      if (!canSendSession) {
        break label153;
      }
      eventSession("launch");
      return;
      label144:
      Logging.Log("Session start, flush timer was already on.");
    }
    label153:
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
            ((HashMap)localObject2).put("adid", Feature.advertisingID);
          }
          label383:
          if (Feature.prefs.contains("os_version")) {
            break label1136;
          }
          Logging.Log("No previous os_version in watchlist, adding " + Feature.access$3300());
          Feature.prefs.edit().putString("os_version", Feature.access$3300()).commit();
        }
        for (;;)
        {
          if (!((HashMap)localObject2).keySet().isEmpty()) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("action", "update");
              localJSONObject.put("kochava_device_id", Feature.access$2700());
              localJSONObject.put("kochava_app_id", Feature.mAppId);
              localJSONObject.put("sdk_version", "Android20151109" + Feature.versionExtension);
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
          Feature.prefs.edit().putString("app_short_string", Feature.this.mAppVersionName).commit();
          break;
          label730:
          if (Feature.prefs.getBoolean("app_limit_tracking", false) == Feature.this.app_limit_tracking) {
            break label176;
          }
          Logging.Log("app_limit_tracking changed! Is now " + Feature.this.app_limit_tracking);
          ((HashMap)localObject2).put("app_limit_tracking", Feature.this.app_limit_tracking + "");
          Feature.prefs.edit().putBoolean("app_limit_tracking", Feature.this.app_limit_tracking).commit();
          break label176;
          label842:
          if (Feature.prefs.getString("app_version", "").equals(Feature.this.getAppVersion())) {
            break label245;
          }
          Logging.Log("app_version changed! Is now " + Feature.this.getAppVersion());
          ((HashMap)localObject2).put("app_version", Feature.this.getAppVersion() + "");
          Feature.prefs.edit().putString("app_version", Feature.this.getAppVersion()).commit();
          break label245;
          label958:
          if (Feature.prefs.getBoolean("device_limit_tracking", false) == Feature.device_limit_tracking) {
            break label306;
          }
          Logging.Log("device_limit_tracking changed! Is now " + Feature.device_limit_tracking);
          ((HashMap)localObject2).put("device_limit_tracking", Feature.device_limit_tracking + "");
          Feature.prefs.edit().putBoolean("device_limit_tracking", Feature.device_limit_tracking).commit();
          break label306;
          label1054:
          if (Feature.prefs.getString("adid", "").equals(Feature.advertisingID)) {
            break label383;
          }
          Logging.Log("adid changed! Is now " + Feature.advertisingID);
          ((HashMap)localObject2).put("adid", Feature.advertisingID);
          Feature.prefs.edit().putString("adid", Feature.advertisingID).commit();
          break label383;
          label1136:
          if (!Feature.prefs.getString("os_version", "").equals(Feature.access$3300()))
          {
            Logging.Log("os_version changed! Is now " + Feature.access$3300());
            ((HashMap)localObject2).put("os_version", Feature.access$3300());
            Feature.prefs.edit().putString("os_version", Feature.access$3300()).commit();
          }
        }
        localException.put("data", localObject3);
        if ((Feature.hostControl == null) || (Feature.hostControl.trim().isEmpty())) {
          Feature.access$402("control.kochava.com");
        }
        Logging.Log("posting update to " + "https://" + Feature.hostControl + "/track/kvTracker.php");
        localObject2 = (HttpsURLConnection)new URL("https://" + Feature.hostControl + "/track/kvTracker.php").openConnection();
        ((HttpsURLConnection)localObject2).setRequestProperty("User-Agent", Feature.mUserAgent);
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
            Log.e("KochavaTracker", "AppLifeCycleStatusManager - not active");
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
