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
      //   144: ifle +2997 -> 3141
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
      //   479: ifeq +2612 -> 3091
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
      //   1472: if_icmpge +999 -> 2471
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
      //   1594: ifeq +1522 -> 3116
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
      //   1714: ifne +1531 -> 3245
      //   1717: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   1720: ldc_w 481
      //   1723: invokevirtual 484	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1726: checkcast 452	java/lang/Boolean
      //   1729: invokevirtual 487	java/lang/Boolean:booleanValue	()Z
      //   1732: ifne +1446 -> 3178
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
      //   1769: ifne +1414 -> 3183
      //   1772: iconst_1
      //   1773: istore_3
      //   1774: invokestatic 497	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   1777: ifnull +1411 -> 3188
      //   1780: iconst_1
      //   1781: istore 4
      //   1783: iload_3
      //   1784: ifeq +1410 -> 3194
      //   1787: iload_1
      //   1788: ifne +8 -> 1796
      //   1791: iload 11
      //   1793: ifeq +1401 -> 3194
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
      //   2426: ifeq +132 -> 2558
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
      //   2460: getstatic 576	com/kochava/android/tracker/Global:DEBUGERROR	Z
      //   2463: ifeq +8 -> 2471
      //   2466: aload 14
      //   2468: invokevirtual 579	java/lang/Exception:printStackTrace	()V
      //   2471: aload 13
      //   2473: ldc_w 581
      //   2476: invokevirtual 427	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2479: astore 14
      //   2481: new 87	java/lang/StringBuilder
      //   2484: dup
      //   2485: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2488: ldc_w 583
      //   2491: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2494: aload 14
      //   2496: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2499: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2502: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2505: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2508: iconst_0
      //   2509: istore_1
      //   2510: iload_1
      //   2511: aload 14
      //   2513: invokevirtual 435	org/json/JSONArray:length	()I
      //   2516: if_icmpge +282 -> 2798
      //   2519: aload 14
      //   2521: iload_1
      //   2522: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2525: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2528: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2531: ldc_w 585
      //   2534: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2537: ifeq +197 -> 2734
      //   2540: ldc_w 587
      //   2543: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2546: iconst_1
      //   2547: invokestatic 590	com/kochava/android/tracker/Feature:access$1602	(Z)Z
      //   2550: pop
      //   2551: iload_1
      //   2552: iconst_1
      //   2553: iadd
      //   2554: istore_1
      //   2555: goto -45 -> 2510
      //   2558: aload 14
      //   2560: iload_1
      //   2561: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2564: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2567: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2570: ldc_w 481
      //   2573: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2576: ifeq +26 -> 2602
      //   2579: ldc_w 592
      //   2582: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2585: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2588: ldc_w 481
      //   2591: iconst_0
      //   2592: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2595: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2598: pop
      //   2599: goto -1083 -> 1516
      //   2602: aload 14
      //   2604: iload_1
      //   2605: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2608: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2611: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2614: ldc_w 594
      //   2617: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2620: ifeq +26 -> 2646
      //   2623: ldc_w 596
      //   2626: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2629: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2632: ldc_w 594
      //   2635: iconst_0
      //   2636: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2639: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2642: pop
      //   2643: goto -1127 -> 1516
      //   2646: aload 14
      //   2648: iload_1
      //   2649: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2652: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2655: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2658: ldc_w 598
      //   2661: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2664: ifeq +26 -> 2690
      //   2667: ldc_w 600
      //   2670: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2673: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2676: ldc_w 598
      //   2679: iconst_0
      //   2680: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2683: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2686: pop
      //   2687: goto -1171 -> 1516
      //   2690: aload 14
      //   2692: iload_1
      //   2693: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2696: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2699: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2702: ldc_w 602
      //   2705: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2708: ifeq -1192 -> 1516
      //   2711: ldc_w 604
      //   2714: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2717: invokestatic 450	com/kochava/android/tracker/Feature:access$1500	()Ljava/util/HashMap;
      //   2720: ldc_w 602
      //   2723: iconst_0
      //   2724: invokestatic 456	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2727: invokevirtual 462	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2730: pop
      //   2731: goto -1215 -> 1516
      //   2734: aload 14
      //   2736: iload_1
      //   2737: invokevirtual 438	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2740: invokevirtual 439	java/lang/Object:toString	()Ljava/lang/String;
      //   2743: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2746: ldc_w 606
      //   2749: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2752: ifeq -201 -> 2551
      //   2755: ldc_w 608
      //   2758: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2761: iconst_1
      //   2762: invokestatic 611	com/kochava/android/tracker/Feature:access$1702	(Z)Z
      //   2765: pop
      //   2766: goto -215 -> 2551
      //   2769: astore 14
      //   2771: new 87	java/lang/StringBuilder
      //   2774: dup
      //   2775: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2778: ldc_w 613
      //   2781: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2784: aload 14
      //   2786: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
      //   2789: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2792: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2795: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   2798: aload 13
      //   2800: ldc_w 615
      //   2803: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2806: ldc_w 617
      //   2809: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2812: astore 14
      //   2814: aload_0
      //   2815: getfield 14	com/kochava/android/tracker/Feature$3:this$0	Lcom/kochava/android/tracker/Feature;
      //   2818: aload 14
      //   2820: invokestatic 621	com/kochava/android/tracker/Feature:access$1800	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   2823: invokestatic 624	com/kochava/android/tracker/Feature:access$1700	()Z
      //   2826: ifeq +18 -> 2844
      //   2829: invokestatic 627	com/kochava/android/tracker/Feature:access$1900	()Z
      //   2832: ifeq +12 -> 2844
      //   2835: getstatic 631	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   2838: invokestatic 635	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   2841: invokevirtual 638	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   2844: aload 13
      //   2846: ldc_w 640
      //   2849: invokevirtual 427	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2852: invokestatic 644	com/kochava/android/tracker/Feature:access$2002	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   2855: pop
      //   2856: new 87	java/lang/StringBuilder
      //   2859: dup
      //   2860: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   2863: ldc_w 646
      //   2866: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2869: invokestatic 650	com/kochava/android/tracker/Feature:access$2000	()Lorg/json/JSONArray;
      //   2872: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2875: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2878: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2881: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   2884: invokestatic 55	com/kochava/android/tracker/Feature:access$200	()Landroid/content/SharedPreferences;
      //   2887: invokeinterface 321 1 0
      //   2892: ldc_w 652
      //   2895: invokestatic 650	com/kochava/android/tracker/Feature:access$2000	()Lorg/json/JSONArray;
      //   2898: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2901: invokeinterface 331 3 0
      //   2906: invokeinterface 334 1 0
      //   2911: pop
      //   2912: aload 12
      //   2914: ldc_w 654
      //   2917: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2920: ifnull +88 -> 3008
      //   2923: aload 12
      //   2925: ldc_w 654
      //   2928: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2931: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2934: ldc_w 452
      //   2937: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2940: ifeq +141 -> 3081
      //   2943: aload 12
      //   2945: ldc_w 654
      //   2948: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2951: checkcast 452	java/lang/Boolean
      //   2954: invokevirtual 487	java/lang/Boolean:booleanValue	()Z
      //   2957: ifeq +124 -> 3081
      //   2960: iconst_1
      //   2961: istore_1
      //   2962: aload 12
      //   2964: ldc_w 654
      //   2967: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2970: invokevirtual 230	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2973: ldc 81
      //   2975: invokevirtual 233	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2978: ifeq +108 -> 3086
      //   2981: ldc_w 299
      //   2984: aload 12
      //   2986: ldc_w 654
      //   2989: invokevirtual 278	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   2992: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2995: ifeq +91 -> 3086
      //   2998: iconst_1
      //   2999: istore_2
      //   3000: goto +314 -> 3314
      //   3003: iconst_1
      //   3004: invokestatic 657	com/kochava/android/tracker/Feature:access$2102	(Z)Z
      //   3007: pop
      //   3008: aload 13
      //   3010: ldc_w 659
      //   3013: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3016: astore 12
      //   3018: new 87	java/lang/StringBuilder
      //   3021: dup
      //   3022: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3025: ldc_w 661
      //   3028: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3031: aload 12
      //   3033: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3036: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3039: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3042: aload 12
      //   3044: ldc_w 663
      //   3047: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3050: ifeq -1387 -> 1663
      //   3053: iconst_1
      //   3054: invokestatic 666	com/kochava/android/tracker/Feature:access$2202	(Z)Z
      //   3057: pop
      //   3058: return
      //   3059: astore 12
      //   3061: ldc_w 668
      //   3064: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3067: goto -1404 -> 1663
      //   3070: astore 14
      //   3072: ldc_w 670
      //   3075: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3078: goto -166 -> 2912
      //   3081: iconst_0
      //   3082: istore_1
      //   3083: goto -121 -> 2962
      //   3086: iconst_0
      //   3087: istore_2
      //   3088: goto +226 -> 3314
      //   3091: new 87	java/lang/StringBuilder
      //   3094: dup
      //   3095: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3098: ldc_w 467
      //   3101: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3104: aload 12
      //   3106: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3109: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3112: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3115: return
      //   3116: new 87	java/lang/StringBuilder
      //   3119: dup
      //   3120: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3123: ldc_w 467
      //   3126: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3129: aload 12
      //   3131: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3134: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3137: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3140: return
      //   3141: new 87	java/lang/StringBuilder
      //   3144: dup
      //   3145: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3148: ldc_w 672
      //   3151: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3154: lload 5
      //   3156: ldc2_w 673
      //   3159: ldiv
      //   3160: invokevirtual 677	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3163: ldc_w 679
      //   3166: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3169: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3172: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3175: goto -1488 -> 1687
      //   3178: iconst_0
      //   3179: istore_1
      //   3180: goto -1443 -> 1737
      //   3183: iconst_0
      //   3184: istore_3
      //   3185: goto -1411 -> 1774
      //   3188: iconst_0
      //   3189: istore 4
      //   3191: goto -1408 -> 1783
      //   3194: iload_3
      //   3195: ifeq +8 -> 3203
      //   3198: iload 4
      //   3200: ifne -1404 -> 1796
      //   3203: ldc2_w 673
      //   3206: invokestatic 685	java/lang/Thread:sleep	(J)V
      //   3209: iload_2
      //   3210: iconst_1
      //   3211: iadd
      //   3212: istore_2
      //   3213: goto -1471 -> 1742
      //   3216: astore 12
      //   3218: new 87	java/lang/StringBuilder
      //   3221: dup
      //   3222: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3225: ldc_w 687
      //   3228: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3231: aload 12
      //   3233: invokevirtual 238	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3236: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3239: invokestatic 241	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3242: goto -33 -> 3209
      //   3245: ldc_w 689
      //   3248: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3251: goto -1426 -> 1825
      //   3254: astore 12
      //   3256: goto -248 -> 3008
      //   3259: astore 14
      //   3261: goto -349 -> 2912
      //   3264: astore 14
      //   3266: goto -443 -> 2823
      //   3269: astore 14
      //   3271: goto -1844 -> 1427
      //   3274: astore 14
      //   3276: goto -1927 -> 1349
      //   3279: astore 14
      //   3281: goto -2010 -> 1271
      //   3284: astore 14
      //   3286: goto -2432 -> 854
      //   3289: astore 14
      //   3291: goto -2503 -> 788
      //   3294: astore 14
      //   3296: goto -2382 -> 914
      //   3299: astore 14
      //   3301: goto -2295 -> 1006
      //   3304: astore 14
      //   3306: goto -2200 -> 1106
      //   3309: astore 14
      //   3311: goto -2120 -> 1191
      //   3314: iload_1
      //   3315: ifne -312 -> 3003
      //   3318: iload_2
      //   3319: ifeq -311 -> 3008
      //   3322: goto -319 -> 3003
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3325	0	this	3
      //   41	3274	1	i	int
      //   12	3307	2	j	int
      //   1773	1422	3	k	int
      //   1781	1418	4	m	int
      //   9	3146	5	l1	long
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
      //   1828	1215	12	localObject3	Object
      //   3059	71	12	localException3	Exception
      //   3216	16	12	localInterruptedException	InterruptedException
      //   3254	1	12	localException4	Exception
      //   351	2658	13	localObject4	Object
      //   393	1083	14	localObject5	Object
      //   1626	1	14	localJSONException2	JSONException
      //   1887	1	14	localJSONException3	JSONException
      //   1898	511	14	localJSONException4	JSONException
      //   2452	15	14	localException5	Exception
      //   2479	256	14	localJSONArray	JSONArray
      //   2769	16	14	localException6	Exception
      //   2812	7	14	localJSONObject	JSONObject
      //   3070	1	14	localException7	Exception
      //   3259	1	14	localJSONException5	JSONException
      //   3264	1	14	localException8	Exception
      //   3269	1	14	localException9	Exception
      //   3274	1	14	localException10	Exception
      //   3279	1	14	localException11	Exception
      //   3284	1	14	localException12	Exception
      //   3289	1	14	localException13	Exception
      //   3294	1	14	localException14	Exception
      //   3299	1	14	localException15	Exception
      //   3304	1	14	localException16	Exception
      //   3309	1	14	localException17	Exception
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
      //   2454	2471	467	java/io/IOException
      //   2471	2508	467	java/io/IOException
      //   2510	2551	467	java/io/IOException
      //   2558	2599	467	java/io/IOException
      //   2602	2643	467	java/io/IOException
      //   2646	2687	467	java/io/IOException
      //   2690	2731	467	java/io/IOException
      //   2734	2766	467	java/io/IOException
      //   2771	2798	467	java/io/IOException
      //   2798	2823	467	java/io/IOException
      //   2823	2844	467	java/io/IOException
      //   2844	2912	467	java/io/IOException
      //   2912	2960	467	java/io/IOException
      //   2962	2998	467	java/io/IOException
      //   3003	3008	467	java/io/IOException
      //   3008	3058	467	java/io/IOException
      //   3061	3067	467	java/io/IOException
      //   3072	3078	467	java/io/IOException
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
      //   2454	2471	1555	java/lang/OutOfMemoryError
      //   2471	2508	1555	java/lang/OutOfMemoryError
      //   2510	2551	1555	java/lang/OutOfMemoryError
      //   2558	2599	1555	java/lang/OutOfMemoryError
      //   2602	2643	1555	java/lang/OutOfMemoryError
      //   2646	2687	1555	java/lang/OutOfMemoryError
      //   2690	2731	1555	java/lang/OutOfMemoryError
      //   2734	2766	1555	java/lang/OutOfMemoryError
      //   2771	2798	1555	java/lang/OutOfMemoryError
      //   2798	2823	1555	java/lang/OutOfMemoryError
      //   2823	2844	1555	java/lang/OutOfMemoryError
      //   2844	2912	1555	java/lang/OutOfMemoryError
      //   2912	2960	1555	java/lang/OutOfMemoryError
      //   2962	2998	1555	java/lang/OutOfMemoryError
      //   3003	3008	1555	java/lang/OutOfMemoryError
      //   3008	3058	1555	java/lang/OutOfMemoryError
      //   3061	3067	1555	java/lang/OutOfMemoryError
      //   3072	3078	1555	java/lang/OutOfMemoryError
      //   147	196	1582	java/io/IOException
      //   196	207	1582	java/io/IOException
      //   207	407	1582	java/io/IOException
      //   469	510	1582	java/io/IOException
      //   1557	1581	1582	java/io/IOException
      //   3091	3115	1582	java/io/IOException
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
      //   2454	2471	1637	java/lang/Exception
      //   2771	2798	1637	java/lang/Exception
      //   2823	2844	1637	java/lang/Exception
      //   3061	3067	1637	java/lang/Exception
      //   3072	3078	1637	java/lang/Exception
      //   3091	3115	1637	java/lang/Exception
      //   674	717	1887	org/json/JSONException
      //   717	739	1898	org/json/JSONException
      //   1427	1464	2452	java/lang/Exception
      //   1466	1516	2452	java/lang/Exception
      //   2408	2449	2452	java/lang/Exception
      //   2558	2599	2452	java/lang/Exception
      //   2602	2643	2452	java/lang/Exception
      //   2646	2687	2452	java/lang/Exception
      //   2690	2731	2452	java/lang/Exception
      //   2471	2508	2769	java/lang/Exception
      //   2510	2551	2769	java/lang/Exception
      //   2734	2766	2769	java/lang/Exception
      //   3008	3058	3059	java/lang/Exception
      //   2844	2912	3070	java/lang/Exception
      //   3203	3209	3216	java/lang/InterruptedException
      //   2912	2960	3254	java/lang/Exception
      //   2962	2998	3254	java/lang/Exception
      //   3003	3008	3254	java/lang/Exception
      //   2844	2912	3259	org/json/JSONException
      //   2798	2823	3264	java/lang/Exception
      //   1349	1364	3269	java/lang/Exception
      //   1369	1398	3269	java/lang/Exception
      //   1400	1427	3269	java/lang/Exception
      //   2372	2401	3269	java/lang/Exception
      //   1271	1286	3274	java/lang/Exception
      //   1291	1320	3274	java/lang/Exception
      //   1322	1349	3274	java/lang/Exception
      //   2328	2357	3274	java/lang/Exception
      //   1191	1206	3279	java/lang/Exception
      //   1212	1241	3279	java/lang/Exception
      //   1244	1271	3279	java/lang/Exception
      //   2284	2313	3279	java/lang/Exception
      //   788	854	3284	java/lang/Exception
      //   739	788	3289	java/lang/Exception
      //   854	914	3294	java/lang/Exception
      //   1909	1954	3294	java/lang/Exception
      //   1957	1982	3294	java/lang/Exception
      //   914	966	3299	java/lang/Exception
      //   972	1001	3299	java/lang/Exception
      //   1001	1006	3299	java/lang/Exception
      //   1992	2028	3299	java/lang/Exception
      //   2031	2069	3299	java/lang/Exception
      //   1006	1049	3304	java/lang/Exception
      //   1053	1106	3304	java/lang/Exception
      //   2079	2132	3304	java/lang/Exception
      //   2135	2191	3304	java/lang/Exception
      //   1106	1152	3309	java/lang/Exception
      //   1157	1191	3309	java/lang/Exception
      //   2200	2235	3309	java/lang/Exception
      //   2238	2272	3309	java/lang/Exception
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
    //   8: getstatic 235	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 503	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc_w 910
    //   20: aastore
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 916	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +18 -> 47
    //   32: aload_0
    //   33: astore_2
    //   34: aload_0
    //   35: astore_3
    //   36: aload_0
    //   37: invokeinterface 921 1 0
    //   42: istore_1
    //   43: iload_1
    //   44: ifne +24 -> 68
    //   47: aload_0
    //   48: ifnull +18 -> 66
    //   51: aload_0
    //   52: invokeinterface 924 1 0
    //   57: ifne +9 -> 66
    //   60: aload_0
    //   61: invokeinterface 927 1 0
    //   66: aconst_null
    //   67: areturn
    //   68: aload_0
    //   69: astore_2
    //   70: aload_0
    //   71: astore_3
    //   72: aload_0
    //   73: aload_0
    //   74: ldc_w 910
    //   77: invokeinterface 931 2 0
    //   82: invokeinterface 932 2 0
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
    //   101: invokeinterface 924 1 0
    //   106: ifne +11 -> 117
    //   109: aload_0
    //   110: invokeinterface 927 1 0
    //   115: aload_2
    //   116: astore_3
    //   117: aload_3
    //   118: areturn
    //   119: astore_0
    //   120: aload_2
    //   121: astore_3
    //   122: new 455	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 934
    //   132: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_0
    //   136: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   139: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   148: aload 4
    //   150: astore_3
    //   151: aload_2
    //   152: ifnull -35 -> 117
    //   155: aload 4
    //   157: astore_3
    //   158: aload_2
    //   159: invokeinterface 924 1 0
    //   164: ifne -47 -> 117
    //   167: aload_2
    //   168: invokeinterface 927 1 0
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
    //   192: invokeinterface 924 1 0
    //   197: ifne +9 -> 206
    //   200: aload_3
    //   201: invokeinterface 927 1 0
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
          ((JSONObject)localObject2).put("sdk_version", "Android20160105" + Feature.versionExtension);
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
    //   1: ifnull +2403 -> 2404
    //   4: aload_0
    //   5: aload_1
    //   6: putfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   9: iload_2
    //   10: putstatic 180	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   13: ldc_w 1107
    //   16: new 455	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   23: ldc_w 1109
    //   26: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: getstatic 159	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   32: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 1115	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: getstatic 485	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   45: ifnonnull +10 -> 55
    //   48: aload_1
    //   49: invokevirtual 1119	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   52: putstatic 485	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   55: aload_0
    //   56: new 601	java/util/Timer
    //   59: dup
    //   60: invokespecial 1120	java/util/Timer:<init>	()V
    //   63: putfield 630	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   66: aload_0
    //   67: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   70: ldc_w 487
    //   73: iconst_0
    //   74: invokevirtual 493	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   77: putstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   80: new 838	com/kochava/android/tracker/DbAdapter
    //   83: dup
    //   84: aload_0
    //   85: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   88: invokespecial 1121	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   91: putstatic 383	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   94: aload_3
    //   95: ifnull +231 -> 326
    //   98: aload_3
    //   99: ldc_w 1123
    //   102: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   105: ifnull +44 -> 149
    //   108: aload_3
    //   109: ldc_w 1123
    //   112: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   115: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   118: ldc_w 671
    //   121: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +25 -> 149
    //   127: aload_3
    //   128: ldc_w 1123
    //   131: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   134: checkcast 671	java/lang/Boolean
    //   137: invokevirtual 674	java/lang/Boolean:booleanValue	()Z
    //   140: istore_2
    //   141: iload_2
    //   142: invokestatic 1130	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   145: iload_2
    //   146: invokestatic 1133	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   149: aload_3
    //   150: ldc_w 1135
    //   153: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   156: ifnull +35 -> 191
    //   159: aload_3
    //   160: ldc_w 1135
    //   163: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   166: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   169: ldc_w 503
    //   172: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   175: ifeq +16 -> 191
    //   178: aload_3
    //   179: ldc_w 1135
    //   182: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   185: checkcast 503	java/lang/String
    //   188: putstatic 159	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   191: aload_3
    //   192: ldc_w 1137
    //   195: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   198: ifnull +38 -> 236
    //   201: aload_3
    //   202: ldc_w 1137
    //   205: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   208: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   211: ldc_w 671
    //   214: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   217: ifeq +19 -> 236
    //   220: aload_3
    //   221: ldc_w 1137
    //   224: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   227: checkcast 671	java/lang/Boolean
    //   230: invokevirtual 674	java/lang/Boolean:booleanValue	()Z
    //   233: putstatic 161	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   236: aload_3
    //   237: ldc_w 1139
    //   240: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   243: ifnull +38 -> 281
    //   246: aload_3
    //   247: ldc_w 1139
    //   250: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   253: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   256: ldc_w 671
    //   259: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   262: ifeq +19 -> 281
    //   265: aload_3
    //   266: ldc_w 1139
    //   269: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   272: checkcast 671	java/lang/Boolean
    //   275: invokevirtual 674	java/lang/Boolean:booleanValue	()Z
    //   278: putstatic 178	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   281: aload_3
    //   282: ldc_w 1141
    //   285: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   288: ifnull +38 -> 326
    //   291: aload_3
    //   292: ldc_w 1141
    //   295: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   298: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   301: ldc_w 671
    //   304: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   307: ifeq +19 -> 326
    //   310: aload_3
    //   311: ldc_w 1141
    //   314: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   317: checkcast 671	java/lang/Boolean
    //   320: invokevirtual 674	java/lang/Boolean:booleanValue	()Z
    //   323: putstatic 197	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   326: aload_0
    //   327: invokespecial 1143	com/kochava/android/tracker/Feature:initHandler	()V
    //   330: new 455	java/lang/StringBuilder
    //   333: dup
    //   334: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   337: ldc_w 1145
    //   340: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   346: ldc_w 1147
    //   349: ldc -101
    //   351: invokeinterface 501 3 0
    //   356: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   365: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   368: ldc_w 1147
    //   371: ldc -101
    //   373: invokeinterface 501 3 0
    //   378: ldc -101
    //   380: invokevirtual 507	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   383: ifne +26 -> 409
    //   386: new 240	org/json/JSONArray
    //   389: dup
    //   390: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   393: ldc_w 1147
    //   396: ldc -101
    //   398: invokeinterface 501 3 0
    //   403: invokespecial 1148	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   406: putstatic 243	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   409: getstatic 588	android/os/Build$VERSION:SDK_INT	I
    //   412: bipush 14
    //   414: if_icmplt +2031 -> 2445
    //   417: getstatic 161	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   420: ifne +2025 -> 2445
    //   423: new 455	java/lang/StringBuilder
    //   426: dup
    //   427: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   430: ldc_w 1150
    //   433: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: getstatic 161	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   439: invokevirtual 580	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   442: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   445: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   448: getstatic 485	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   451: checkcast 1152	android/app/Application
    //   454: new 48	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   457: dup
    //   458: aload_0
    //   459: invokespecial 1153	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   462: invokevirtual 1157	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   465: getstatic 485	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   468: new 51	com/kochava/android/tracker/Feature$MemoryBoss
    //   471: dup
    //   472: aload_0
    //   473: invokespecial 1158	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   476: invokevirtual 1162	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   479: iconst_1
    //   480: putstatic 1165	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   483: iconst_1
    //   484: putstatic 1168	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   487: aload_0
    //   488: aload_0
    //   489: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   492: invokevirtual 779	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   495: invokestatic 783	com/kochava/android/tracker/Feature:getAttributionId	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   498: putfield 785	com/kochava/android/tracker/Feature:mFbId	Ljava/lang/String;
    //   501: getstatic 178	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   504: ifne +19 -> 523
    //   507: new 26	com/kochava/android/tracker/Feature$2
    //   510: dup
    //   511: aload_0
    //   512: invokespecial 1169	com/kochava/android/tracker/Feature$2:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   515: iconst_0
    //   516: anewarray 1171	java/lang/Void
    //   519: invokevirtual 1177	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   522: pop
    //   523: aload_0
    //   524: aload_0
    //   525: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   528: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   531: aload_0
    //   532: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   535: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   538: iconst_0
    //   539: invokevirtual 1190	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   542: getfield 1195	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   545: putfield 900	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   548: aload_0
    //   549: new 475	org/json/JSONObject
    //   552: dup
    //   553: invokespecial 642	org/json/JSONObject:<init>	()V
    //   556: putfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   559: aload_0
    //   560: new 475	org/json/JSONObject
    //   563: dup
    //   564: invokespecial 642	org/json/JSONObject:<init>	()V
    //   567: putfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   570: aload_0
    //   571: new 475	org/json/JSONObject
    //   574: dup
    //   575: invokespecial 642	org/json/JSONObject:<init>	()V
    //   578: putfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   581: aconst_null
    //   582: astore 13
    //   584: aconst_null
    //   585: astore 6
    //   587: aconst_null
    //   588: astore 9
    //   590: aconst_null
    //   591: astore 7
    //   593: ldc_w 659
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
    //   621: ldc_w 1203
    //   624: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   627: ifnull +38 -> 665
    //   630: aload 6
    //   632: astore 5
    //   634: aload_3
    //   635: ldc_w 1203
    //   638: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   641: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   644: ldc_w 503
    //   647: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   650: ifeq +15 -> 665
    //   653: aload_3
    //   654: ldc_w 1203
    //   657: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   660: checkcast 503	java/lang/String
    //   663: astore 5
    //   665: aload 7
    //   667: astore 6
    //   669: aload_3
    //   670: ldc_w 644
    //   673: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   676: ifnull +38 -> 714
    //   679: aload 7
    //   681: astore 6
    //   683: aload_3
    //   684: ldc_w 644
    //   687: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   690: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   693: ldc_w 503
    //   696: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   699: ifeq +15 -> 714
    //   702: aload_3
    //   703: ldc_w 644
    //   706: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   709: checkcast 503	java/lang/String
    //   712: astore 6
    //   714: aload_1
    //   715: astore 7
    //   717: aload_3
    //   718: ldc_w 657
    //   721: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   724: ifnull +37 -> 761
    //   727: aload_1
    //   728: astore 7
    //   730: aload_3
    //   731: ldc_w 657
    //   734: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   737: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   740: ldc_w 503
    //   743: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   746: ifeq +15 -> 761
    //   749: aload_3
    //   750: ldc_w 657
    //   753: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   756: checkcast 503	java/lang/String
    //   759: astore 7
    //   761: aload 8
    //   763: astore_1
    //   764: aload_3
    //   765: ldc_w 1205
    //   768: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   771: ifnull +33 -> 804
    //   774: aload_3
    //   775: ldc_w 1205
    //   778: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   781: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   784: ldc_w 503
    //   787: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   790: ifeq +1683 -> 2473
    //   793: aload_3
    //   794: ldc_w 1205
    //   797: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   800: checkcast 503	java/lang/String
    //   803: astore_1
    //   804: aload_3
    //   805: ldc_w 1207
    //   808: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   811: ifnull +34 -> 845
    //   814: aload_3
    //   815: ldc_w 1207
    //   818: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   821: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   824: ldc_w 503
    //   827: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   830: ifeq +15 -> 845
    //   833: aload_3
    //   834: ldc_w 1207
    //   837: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: checkcast 503	java/lang/String
    //   843: astore 8
    //   845: aload 14
    //   847: astore 8
    //   849: aload_3
    //   850: ldc_w 1209
    //   853: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   856: ifnull +38 -> 894
    //   859: aload 14
    //   861: astore 8
    //   863: aload_3
    //   864: ldc_w 1209
    //   867: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   870: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   873: ldc_w 503
    //   876: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   879: ifeq +15 -> 894
    //   882: aload_3
    //   883: ldc_w 1209
    //   886: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   889: checkcast 503	java/lang/String
    //   892: astore 8
    //   894: aload_3
    //   895: ldc_w 749
    //   898: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   901: ifnull +39 -> 940
    //   904: aload_3
    //   905: ldc_w 749
    //   908: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   911: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   914: ldc_w 671
    //   917: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   920: ifeq +20 -> 940
    //   923: aload_0
    //   924: aload_3
    //   925: ldc_w 749
    //   928: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   931: checkcast 671	java/lang/Boolean
    //   934: invokevirtual 674	java/lang/Boolean:booleanValue	()Z
    //   937: putfield 254	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   940: aload_3
    //   941: ldc_w 767
    //   944: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   947: ifnull +115 -> 1062
    //   950: aload_3
    //   951: ldc_w 767
    //   954: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   957: instanceof 665
    //   960: ifeq +102 -> 1062
    //   963: aload_3
    //   964: ldc_w 767
    //   967: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   970: checkcast 665	java/util/HashMap
    //   973: putstatic 1211	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   976: new 475	org/json/JSONObject
    //   979: dup
    //   980: invokespecial 642	org/json/JSONObject:<init>	()V
    //   983: putstatic 765	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   986: getstatic 1211	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   989: invokeinterface 821 1 0
    //   994: invokeinterface 824 1 0
    //   999: astore 9
    //   1001: aload 9
    //   1003: invokeinterface 534 1 0
    //   1008: ifeq +54 -> 1062
    //   1011: aload 9
    //   1013: invokeinterface 538 1 0
    //   1018: checkcast 826	java/util/Map$Entry
    //   1021: astore 10
    //   1023: getstatic 765	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   1026: aload 10
    //   1028: invokeinterface 829 1 0
    //   1033: checkcast 503	java/lang/String
    //   1036: aload 10
    //   1038: invokeinterface 832 1 0
    //   1043: checkcast 503	java/lang/String
    //   1046: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1049: pop
    //   1050: aload 9
    //   1052: invokeinterface 1214 1 0
    //   1057: goto -56 -> 1001
    //   1060: astore 9
    //   1062: aload_3
    //   1063: ldc_w 773
    //   1066: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1069: ifnull +36 -> 1105
    //   1072: aload_3
    //   1073: ldc_w 773
    //   1076: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1079: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1082: ldc_w 503
    //   1085: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1088: ifeq +17 -> 1105
    //   1091: aload_0
    //   1092: aload_3
    //   1093: ldc_w 773
    //   1096: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: checkcast 503	java/lang/String
    //   1102: putfield 769	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
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
    //   1125: ldc_w 1215
    //   1128: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1154: ldc_w 1215
    //   1157: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1160: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1163: ldc_w 1217
    //   1166: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1169: ifeq +77 -> 1246
    //   1172: aload_3
    //   1173: ldc_w 1215
    //   1176: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1179: checkcast 1217	java/lang/Integer
    //   1182: invokevirtual 1220	java/lang/Integer:intValue	()I
    //   1185: istore 4
    //   1187: iload 4
    //   1189: iconst_1
    //   1190: if_icmpge +1331 -> 2521
    //   1193: new 455	java/lang/StringBuilder
    //   1196: dup
    //   1197: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1200: ldc_w 1222
    //   1203: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1206: iload 4
    //   1208: invokevirtual 864	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1211: ldc_w 1224
    //   1214: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1217: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1220: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1223: iconst_1
    //   1224: putstatic 166	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
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
    //   1253: invokevirtual 1027	java/lang/String:trim	()Ljava/lang/String;
    //   1256: invokevirtual 968	java/lang/String:length	()I
    //   1259: ifeq +8 -> 1267
    //   1262: aload 11
    //   1264: putstatic 157	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1267: aload 10
    //   1269: ifnull +18 -> 1287
    //   1272: aload 10
    //   1274: ldc_w 1226
    //   1277: invokevirtual 1229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1280: ifeq +7 -> 1287
    //   1283: iconst_1
    //   1284: putstatic 211	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1287: aload_0
    //   1288: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1291: ldc_w 1231
    //   1294: iconst_0
    //   1295: invokevirtual 493	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1298: putstatic 349	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1301: aload 9
    //   1303: ifnull +1310 -> 2613
    //   1306: aload 9
    //   1308: invokevirtual 1027	java/lang/String:trim	()Ljava/lang/String;
    //   1311: invokevirtual 968	java/lang/String:length	()I
    //   1314: ifeq +1299 -> 2613
    //   1317: aload 9
    //   1319: putstatic 437	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1322: aload_0
    //   1323: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1326: ldc_w 644
    //   1329: aload 9
    //   1331: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1334: pop
    //   1335: aload_0
    //   1336: getfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1339: ldc_w 644
    //   1342: aload 9
    //   1344: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1347: pop
    //   1348: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1351: ldc_w 1233
    //   1354: ldc -101
    //   1356: invokeinterface 501 3 0
    //   1361: ldc -101
    //   1363: invokevirtual 507	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1366: ifeq +27 -> 1393
    //   1369: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1372: invokeinterface 1002 1 0
    //   1377: ldc_w 1233
    //   1380: aload 9
    //   1382: invokeinterface 1008 3 0
    //   1387: invokeinterface 1011 1 0
    //   1392: pop
    //   1393: aload_0
    //   1394: aload 12
    //   1396: invokespecial 445	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1399: aload_0
    //   1400: getfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1403: ldc_w 1235
    //   1406: aload_0
    //   1407: invokespecial 738	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1410: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1413: pop
    //   1414: aload_0
    //   1415: getfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1418: ldc_w 1237
    //   1421: ldc_w 1239
    //   1424: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1427: pop
    //   1428: aload_0
    //   1429: getfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1432: ldc_w 1241
    //   1435: ldc_w 1243
    //   1438: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1441: pop
    //   1442: aload_0
    //   1443: getfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1446: ldc_w 657
    //   1449: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1452: ldc_w 657
    //   1455: ldc_w 659
    //   1458: invokeinterface 501 3 0
    //   1463: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1466: pop
    //   1467: aload_0
    //   1468: getfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1471: ldc_w 657
    //   1474: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1477: ldc_w 657
    //   1480: ldc_w 659
    //   1483: invokeinterface 501 3 0
    //   1488: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1491: pop
    //   1492: aload_0
    //   1493: getfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1496: ldc_w 1241
    //   1499: ldc_w 1243
    //   1502: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1505: pop
    //   1506: aload_0
    //   1507: getfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1510: ldc_w 657
    //   1513: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1516: ldc_w 657
    //   1519: ldc_w 659
    //   1522: invokeinterface 501 3 0
    //   1527: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1530: pop
    //   1531: aload_0
    //   1532: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1535: ldc_w 704
    //   1538: new 455	java/lang/StringBuilder
    //   1541: dup
    //   1542: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1545: ldc_w 706
    //   1548: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1551: getstatic 159	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1554: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1557: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1560: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1563: pop
    //   1564: aload_0
    //   1565: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1568: ldc_w 1245
    //   1571: ldc_w 1247
    //   1574: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1577: pop
    //   1578: aload_0
    //   1579: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1582: ldc_w 834
    //   1585: aload_0
    //   1586: getfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1589: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1592: pop
    //   1593: aload_0
    //   1594: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1597: ldc_w 1249
    //   1600: aload_0
    //   1601: getfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1604: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1607: pop
    //   1608: invokestatic 461	java/lang/System:currentTimeMillis	()J
    //   1611: ldc2_w 462
    //   1614: ldiv
    //   1615: putstatic 186	com/kochava/android/tracker/Feature:startTime	J
    //   1618: iconst_0
    //   1619: istore 4
    //   1621: ldc -101
    //   1623: astore_3
    //   1624: new 547	android/content/ComponentName
    //   1627: dup
    //   1628: aload_0
    //   1629: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1632: ldc_w 1251
    //   1635: invokespecial 559	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1638: astore_1
    //   1639: aload_0
    //   1640: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1643: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1646: aload_1
    //   1647: iconst_0
    //   1648: invokevirtual 1255	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1651: pop
    //   1652: ldc_w 1257
    //   1655: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1658: aload_3
    //   1659: astore_1
    //   1660: aload_0
    //   1661: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1664: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1667: ldc_w 1259
    //   1670: aload_0
    //   1671: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1674: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1677: invokevirtual 1262	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1680: ifge +27 -> 1707
    //   1683: iconst_1
    //   1684: istore 4
    //   1686: new 455	java/lang/StringBuilder
    //   1689: dup
    //   1690: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1693: aload_3
    //   1694: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1697: ldc_w 1264
    //   1700: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1703: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1706: astore_1
    //   1707: aload_1
    //   1708: astore_3
    //   1709: aload_0
    //   1710: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1713: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1716: ldc_w 1266
    //   1719: aload_0
    //   1720: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1723: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1726: invokevirtual 1262	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1729: ifge +27 -> 1756
    //   1732: iconst_1
    //   1733: istore 4
    //   1735: new 455	java/lang/StringBuilder
    //   1738: dup
    //   1739: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1742: aload_1
    //   1743: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1746: ldc_w 1268
    //   1749: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1752: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1755: astore_3
    //   1756: aload_3
    //   1757: astore_1
    //   1758: aload_0
    //   1759: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1762: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1765: ldc_w 1270
    //   1768: aload_0
    //   1769: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1772: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1775: invokevirtual 1262	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1778: ifge +27 -> 1805
    //   1781: iconst_1
    //   1782: istore 4
    //   1784: new 455	java/lang/StringBuilder
    //   1787: dup
    //   1788: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1791: aload_3
    //   1792: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1795: ldc_w 1272
    //   1798: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1801: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1804: astore_1
    //   1805: iload 4
    //   1807: ifeq +13 -> 1820
    //   1810: ldc_w 1274
    //   1813: invokestatic 1277	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1816: aload_1
    //   1817: invokestatic 1277	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1820: aload_0
    //   1821: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1824: ldc_w 1279
    //   1827: invokevirtual 791	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1830: checkcast 1281	android/telephony/TelephonyManager
    //   1833: invokevirtual 1284	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1836: putstatic 718	com/kochava/android/tracker/Feature:carrier	Ljava/lang/String;
    //   1839: aload_0
    //   1840: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1843: ldc_w 1286
    //   1846: invokevirtual 791	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1849: checkcast 1288	android/net/wifi/WifiManager
    //   1852: invokevirtual 1292	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1855: invokevirtual 1297	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1858: putstatic 714	com/kochava/android/tracker/Feature:bssid	Ljava/lang/String;
    //   1861: aload_0
    //   1862: invokespecial 1299	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1865: putstatic 430	com/kochava/android/tracker/Feature:mUserAgent	Ljava/lang/String;
    //   1868: aload_0
    //   1869: invokestatic 434	com/kochava/android/tracker/Feature:getBrand	()Ljava/lang/String;
    //   1872: putfield 1301	com/kochava/android/tracker/Feature:mCarrier	Ljava/lang/String;
    //   1875: aload_0
    //   1876: invokestatic 427	com/kochava/android/tracker/Feature:getModel	()Ljava/lang/String;
    //   1879: putfield 1303	com/kochava/android/tracker/Feature:mModel	Ljava/lang/String;
    //   1882: aload_0
    //   1883: ldc_w 1305
    //   1886: putfield 904	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1889: aload_0
    //   1890: ldc_w 1307
    //   1893: putfield 908	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1896: aload_0
    //   1897: ldc -101
    //   1899: putfield 360	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1902: aload_0
    //   1903: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1906: invokevirtual 1119	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1909: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1912: astore_3
    //   1913: aload_3
    //   1914: aload_0
    //   1915: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1918: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1921: iconst_0
    //   1922: invokevirtual 1311	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1925: astore_1
    //   1926: aload_1
    //   1927: ifnull +1031 -> 2958
    //   1930: aload_3
    //   1931: aload_1
    //   1932: invokevirtual 1315	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1935: astore_1
    //   1936: aload_0
    //   1937: aload_1
    //   1938: checkcast 503	java/lang/String
    //   1941: checkcast 503	java/lang/String
    //   1944: putfield 904	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1947: new 455	java/lang/StringBuilder
    //   1950: dup
    //   1951: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1954: ldc_w 1317
    //   1957: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1960: aload_0
    //   1961: getfield 904	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1964: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1967: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1970: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1973: aload_0
    //   1974: new 455	java/lang/StringBuilder
    //   1977: dup
    //   1978: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   1981: aload_0
    //   1982: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1985: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1988: aload_0
    //   1989: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   1992: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1995: iconst_0
    //   1996: invokevirtual 1190	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1999: getfield 1320	android/content/pm/PackageInfo:versionCode	I
    //   2002: invokevirtual 864	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2005: ldc -101
    //   2007: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2010: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2013: putfield 908	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2016: new 455	java/lang/StringBuilder
    //   2019: dup
    //   2020: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2023: ldc_w 1322
    //   2026: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2029: aload_0
    //   2030: getfield 908	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   2033: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2036: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2039: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2042: aload_0
    //   2043: aload_0
    //   2044: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2047: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2050: aload_0
    //   2051: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2054: invokevirtual 1184	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2057: iconst_0
    //   2058: invokevirtual 1190	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2061: getfield 1325	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2064: putfield 360	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2067: new 455	java/lang/StringBuilder
    //   2070: dup
    //   2071: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2074: ldc_w 1327
    //   2077: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2080: aload_0
    //   2081: getfield 360	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2084: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2087: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2090: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2093: aload_0
    //   2094: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2097: ldc_w 787
    //   2100: invokevirtual 791	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2103: checkcast 793	android/view/WindowManager
    //   2106: astore_1
    //   2107: aload_0
    //   2108: aload_1
    //   2109: invokeinterface 800 1 0
    //   2114: invokevirtual 1330	android/view/Display:getHeight	()I
    //   2117: putfield 729	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2120: aload_0
    //   2121: aload_1
    //   2122: invokeinterface 800 1 0
    //   2127: invokevirtual 1333	android/view/Display:getWidth	()I
    //   2130: putfield 733	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2133: new 455	java/lang/StringBuilder
    //   2136: dup
    //   2137: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2140: ldc_w 1335
    //   2143: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2146: aload_0
    //   2147: getfield 729	com/kochava/android/tracker/Feature:mDisplayHeight	I
    //   2150: invokevirtual 864	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2153: ldc_w 1337
    //   2156: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2159: aload_0
    //   2160: getfield 733	com/kochava/android/tracker/Feature:mDisplayWidth	I
    //   2163: invokevirtual 864	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2166: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2169: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2172: aload_0
    //   2173: aload_0
    //   2174: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2177: invokevirtual 779	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   2180: ldc_w 744
    //   2183: invokestatic 1342	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   2186: putfield 746	com/kochava/android/tracker/Feature:mAndroidID	Ljava/lang/String;
    //   2189: aload_0
    //   2190: invokestatic 346	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2193: putfield 1344	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2196: aload_0
    //   2197: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2200: ldc_w 646
    //   2203: invokestatic 346	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2206: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2209: pop
    //   2210: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2213: ldc_w 1346
    //   2216: ldc -101
    //   2218: invokeinterface 501 3 0
    //   2223: ldc_w 1226
    //   2226: invokevirtual 507	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2229: ifne +148 -> 2377
    //   2232: aload_0
    //   2233: getfield 449	com/kochava/android/tracker/Feature:mContext	Landroid/content/Context;
    //   2236: invokevirtual 1181	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2239: astore 5
    //   2241: aload 5
    //   2243: sipush 128
    //   2246: invokevirtual 1350	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2249: invokeinterface 679 1 0
    //   2254: astore 6
    //   2256: aload 6
    //   2258: invokeinterface 534 1 0
    //   2263: ifeq +114 -> 2377
    //   2266: aload 6
    //   2268: invokeinterface 538 1 0
    //   2273: checkcast 1352	android/content/pm/ApplicationInfo
    //   2276: astore 7
    //   2278: aload 7
    //   2280: invokestatic 1356	com/kochava/android/tracker/Feature:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   2283: istore_2
    //   2284: iload_2
    //   2285: ifne -29 -> 2256
    //   2288: aconst_null
    //   2289: astore_1
    //   2290: aload 5
    //   2292: aload 7
    //   2294: getfield 1357	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2297: iconst_0
    //   2298: invokevirtual 1190	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2301: astore_3
    //   2302: aload_3
    //   2303: astore_1
    //   2304: aload_1
    //   2305: ifnull -49 -> 2256
    //   2308: getstatic 195	com/kochava/android/tracker/Feature:installedApps	Ljava/util/List;
    //   2311: new 45	com/kochava/android/tracker/Feature$ApplicationInfoHolder
    //   2314: dup
    //   2315: aload_0
    //   2316: aload_1
    //   2317: getfield 1195	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2320: new 455	java/lang/StringBuilder
    //   2323: dup
    //   2324: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2327: aload_1
    //   2328: getfield 1360	android/content/pm/PackageInfo:firstInstallTime	J
    //   2331: invokevirtual 467	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2334: ldc -101
    //   2336: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2339: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2342: new 455	java/lang/StringBuilder
    //   2345: dup
    //   2346: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2349: aload_1
    //   2350: getfield 1363	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2353: invokevirtual 467	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2356: ldc -101
    //   2358: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2361: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2364: invokespecial 1366	com/kochava/android/tracker/Feature$ApplicationInfoHolder:<init>	(Lcom/kochava/android/tracker/Feature;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2367: invokeinterface 1369 2 0
    //   2372: pop
    //   2373: goto -117 -> 2256
    //   2376: astore_1
    //   2377: getstatic 225	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2380: aload_0
    //   2381: getfield 261	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2384: ldc2_w 1370
    //   2387: getstatic 1377	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2390: invokeinterface 1382 5 0
    //   2395: pop
    //   2396: aload_0
    //   2397: ldc_w 1383
    //   2400: invokevirtual 1386	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2403: return
    //   2404: ldc_w 1388
    //   2407: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2410: iconst_1
    //   2411: putstatic 205	com/kochava/android/tracker/Feature:badInit	Z
    //   2414: return
    //   2415: astore_1
    //   2416: new 455	java/lang/StringBuilder
    //   2419: dup
    //   2420: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2423: ldc_w 1390
    //   2426: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2429: aload_1
    //   2430: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   2433: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2436: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2439: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2442: goto -2033 -> 409
    //   2445: new 455	java/lang/StringBuilder
    //   2448: dup
    //   2449: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2452: ldc_w 1392
    //   2455: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2458: getstatic 161	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2461: invokevirtual 580	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2464: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2467: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2470: goto -1983 -> 487
    //   2473: aload 8
    //   2475: astore_1
    //   2476: aload_3
    //   2477: ldc_w 1205
    //   2480: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2483: invokevirtual 1127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2486: ldc_w 671
    //   2489: invokevirtual 1128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2492: ifeq -1688 -> 804
    //   2495: aload 8
    //   2497: astore_1
    //   2498: aload_3
    //   2499: ldc_w 1205
    //   2502: invokevirtual 669	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2505: checkcast 671	java/lang/Boolean
    //   2508: invokevirtual 674	java/lang/Boolean:booleanValue	()Z
    //   2511: ifeq -1707 -> 804
    //   2514: ldc_w 1226
    //   2517: astore_1
    //   2518: goto -1714 -> 804
    //   2521: iload 4
    //   2523: sipush 360
    //   2526: if_icmple +42 -> 2568
    //   2529: new 455	java/lang/StringBuilder
    //   2532: dup
    //   2533: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2536: ldc_w 1222
    //   2539: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2542: iload 4
    //   2544: invokevirtual 864	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2547: ldc_w 1394
    //   2550: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2553: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2556: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2559: ldc_w 1395
    //   2562: putstatic 164	com/kochava/android/tracker/Feature:flush_rate	I
    //   2565: goto -1342 -> 1223
    //   2568: iload 4
    //   2570: bipush 60
    //   2572: imul
    //   2573: sipush 1000
    //   2576: imul
    //   2577: putstatic 164	com/kochava/android/tracker/Feature:flush_rate	I
    //   2580: new 455	java/lang/StringBuilder
    //   2583: dup
    //   2584: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2587: ldc_w 1397
    //   2590: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2593: iload 4
    //   2595: invokevirtual 864	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2598: ldc_w 1399
    //   2601: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2604: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2607: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2610: goto -1387 -> 1223
    //   2613: aload 13
    //   2615: ifnull +179 -> 2794
    //   2618: aload 13
    //   2620: invokevirtual 1027	java/lang/String:trim	()Ljava/lang/String;
    //   2623: invokevirtual 968	java/lang/String:length	()I
    //   2626: ifeq +168 -> 2794
    //   2629: aload_0
    //   2630: getfield 1197	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2633: ldc_w 1203
    //   2636: aload 13
    //   2638: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2641: pop
    //   2642: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2645: ldc_w 1233
    //   2648: ldc -101
    //   2650: invokeinterface 501 3 0
    //   2655: ldc -101
    //   2657: invokevirtual 507	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2660: ifeq +27 -> 2687
    //   2663: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2666: invokeinterface 1002 1 0
    //   2671: ldc_w 1233
    //   2674: aload 13
    //   2676: invokeinterface 1008 3 0
    //   2681: invokeinterface 1011 1 0
    //   2686: pop
    //   2687: aload 9
    //   2689: ifnull +78 -> 2767
    //   2692: aload 9
    //   2694: invokevirtual 1027	java/lang/String:trim	()Ljava/lang/String;
    //   2697: invokevirtual 968	java/lang/String:length	()I
    //   2700: ifeq +67 -> 2767
    //   2703: aload 9
    //   2705: putstatic 437	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2708: aload_0
    //   2709: getfield 1199	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2712: ldc_w 644
    //   2715: aload 9
    //   2717: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2720: pop
    //   2721: aload_0
    //   2722: getfield 1201	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2725: ldc_w 644
    //   2728: aload 9
    //   2730: invokevirtual 479	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2733: pop
    //   2734: goto -1341 -> 1393
    //   2737: astore_1
    //   2738: new 455	java/lang/StringBuilder
    //   2741: dup
    //   2742: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2745: ldc_w 1401
    //   2748: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2751: aload_1
    //   2752: invokevirtual 1402	org/json/JSONException:toString	()Ljava/lang/String;
    //   2755: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2758: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2761: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2764: goto -1156 -> 1608
    //   2767: new 455	java/lang/StringBuilder
    //   2770: dup
    //   2771: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2774: ldc_w 1404
    //   2777: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2780: aload 13
    //   2782: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2785: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2788: putstatic 437	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2791: goto -1398 -> 1393
    //   2794: ldc_w 1406
    //   2797: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2800: iconst_1
    //   2801: putstatic 205	com/kochava/android/tracker/Feature:badInit	Z
    //   2804: return
    //   2805: astore_1
    //   2806: iconst_1
    //   2807: istore 4
    //   2809: new 455	java/lang/StringBuilder
    //   2812: dup
    //   2813: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2816: ldc -101
    //   2818: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2821: ldc_w 1408
    //   2824: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2827: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2830: astore_3
    //   2831: goto -1173 -> 1658
    //   2834: astore_1
    //   2835: new 455	java/lang/StringBuilder
    //   2838: dup
    //   2839: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2842: ldc_w 1410
    //   2845: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2848: aload_1
    //   2849: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   2852: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2855: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2858: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2861: goto -1022 -> 1839
    //   2864: astore_1
    //   2865: new 455	java/lang/StringBuilder
    //   2868: dup
    //   2869: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2872: ldc_w 1412
    //   2875: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2878: aload_1
    //   2879: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   2882: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2885: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2888: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2891: goto -1030 -> 1861
    //   2894: astore 5
    //   2896: aconst_null
    //   2897: astore_1
    //   2898: new 455	java/lang/StringBuilder
    //   2901: dup
    //   2902: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2905: ldc_w 1414
    //   2908: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2911: aload 5
    //   2913: invokevirtual 1415	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2916: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2919: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2922: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2925: goto -999 -> 1926
    //   2928: astore_1
    //   2929: new 455	java/lang/StringBuilder
    //   2932: dup
    //   2933: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2936: ldc_w 1414
    //   2939: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2942: aload_1
    //   2943: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   2946: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2949: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2952: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2955: goto -982 -> 1973
    //   2958: ldc_w 1417
    //   2961: astore_1
    //   2962: goto -1026 -> 1936
    //   2965: astore_1
    //   2966: new 455	java/lang/StringBuilder
    //   2969: dup
    //   2970: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   2973: ldc_w 1419
    //   2976: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2979: aload_1
    //   2980: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   2983: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2986: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2989: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2992: goto -950 -> 2042
    //   2995: astore_1
    //   2996: new 455	java/lang/StringBuilder
    //   2999: dup
    //   3000: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   3003: ldc_w 1421
    //   3006: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3009: aload_1
    //   3010: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   3013: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3016: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3019: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3022: goto -929 -> 2093
    //   3025: astore_1
    //   3026: new 455	java/lang/StringBuilder
    //   3029: dup
    //   3030: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   3033: ldc_w 1423
    //   3036: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3039: aload_1
    //   3040: invokevirtual 890	java/lang/Exception:toString	()Ljava/lang/String;
    //   3043: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3046: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3049: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   3052: goto -880 -> 2172
    //   3055: astore_1
    //   3056: getstatic 697	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   3059: ifeq -849 -> 2210
    //   3062: aload_1
    //   3063: invokevirtual 700	org/json/JSONException:printStackTrace	()V
    //   3066: goto -856 -> 2210
    //   3069: astore_3
    //   3070: new 455	java/lang/StringBuilder
    //   3073: dup
    //   3074: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   3077: ldc_w 1425
    //   3080: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3083: aload 7
    //   3085: getfield 1357	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3088: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3091: ldc_w 1427
    //   3094: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3097: aload_3
    //   3098: invokevirtual 1415	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3101: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3104: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3107: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
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
          ((JSONObject)localObject1).put("sdk_version", "Android20160105" + Feature.versionExtension);
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
          if (Global.DEBUGERROR) {
            localException.printStackTrace();
          }
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
          ((JSONObject)localObject2).put("sdk_version", "Android20160105" + Feature.versionExtension);
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
    //   0: getstatic 588	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +261 -> 266
    //   8: getstatic 161	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +255 -> 266
    //   14: getstatic 205	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +10 -> 27
    //   20: ldc_w 590
    //   23: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   26: return
    //   27: ldc_w 1671
    //   30: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   33: getstatic 197	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   36: ifne +46 -> 82
    //   39: getstatic 594	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   42: ifnonnull +189 -> 231
    //   45: ldc_w 1673
    //   48: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   51: new 601	java/util/Timer
    //   54: dup
    //   55: invokespecial 1120	java/util/Timer:<init>	()V
    //   58: putstatic 594	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   61: getstatic 594	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   64: new 34	com/kochava/android/tracker/Feature$7
    //   67: dup
    //   68: invokespecial 1674	com/kochava/android/tracker/Feature$7:<init>	()V
    //   71: getstatic 164	com/kochava/android/tracker/Feature:flush_rate	I
    //   74: i2l
    //   75: getstatic 164	com/kochava/android/tracker/Feature:flush_rate	I
    //   78: i2l
    //   79: invokevirtual 1453	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   82: invokestatic 461	java/lang/System:currentTimeMillis	()J
    //   85: ldc2_w 462
    //   88: ldiv
    //   89: putstatic 186	com/kochava/android/tracker/Feature:startTime	J
    //   92: getstatic 182	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   95: ifeq +18 -> 113
    //   98: invokestatic 319	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   101: ifeq +12 -> 113
    //   104: getstatic 485	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   107: invokestatic 1678	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   110: invokevirtual 1681	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   113: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   116: ifnull +49 -> 165
    //   119: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   122: ldc_w 1683
    //   125: ldc -101
    //   127: invokeinterface 501 3 0
    //   132: ldc -101
    //   134: invokevirtual 507	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 475	org/json/JSONObject
    //   145: dup
    //   146: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc_w 1683
    //   152: ldc -101
    //   154: invokeinterface 501 3 0
    //   159: invokespecial 1684	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1686	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +98 -> 266
    //   171: getstatic 323	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc_w 1346
    //   177: ldc -101
    //   179: invokeinterface 501 3 0
    //   184: ldc_w 1226
    //   187: invokevirtual 507	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifeq +76 -> 266
    //   193: getstatic 207	com/kochava/android/tracker/Feature:canSendSession	Z
    //   196: ifeq +64 -> 260
    //   199: ldc_w 1688
    //   202: invokestatic 609	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   205: return
    //   206: astore_1
    //   207: new 455	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 456	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 1690
    //   217: invokevirtual 470	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 520	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 473	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   230: return
    //   231: ldc_w 1692
    //   234: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   237: goto -155 -> 82
    //   240: astore_1
    //   241: ldc_w 1694
    //   244: invokestatic 525	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   247: getstatic 697	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   250: ifeq -85 -> 165
    //   253: aload_1
    //   254: invokevirtual 700	org/json/JSONException:printStackTrace	()V
    //   257: goto -92 -> 165
    //   260: ldc_w 615
    //   263: invokestatic 583	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
              localJSONObject.put("sdk_version", "Android20160105" + Feature.versionExtension);
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
    protected static boolean active = false;
    protected static boolean resumed = false;
    
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
  
  private static class TrackTask
    implements Runnable
  {
    private TrackTask() {}
    
    public void run()
    {
      Feature.access$4600();
    }
  }
}
