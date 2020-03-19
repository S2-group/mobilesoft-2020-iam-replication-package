package com.kochava.android.tracker;

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
            paramAnonymousMessage.put("kochava_device_id", Feature.access$3300());
            paramAnonymousMessage.put("kochava_app_id", Feature.mAppId);
            paramAnonymousMessage.put("sdk_version", "Android20160615-NOEMAIL" + Feature.versionExtension);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            Logging.Log("Location update: " + paramAnonymousMessage);
            int i = Feature.kDbAdapter.addEvent(paramAnonymousMessage, false, true);
            Feature.access$5402(System.currentTimeMillis());
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
  private static String mKochDevIDStrategy;
  private static Map<String, String> mSuperProperties;
  private static Timer mTimer;
  protected static boolean overrideAutomaticSessions;
  private static HashMap<String, Boolean> paramRestrictions;
  private static SharedPreferences prefs;
  private static int referrerDelayFromInit;
  private static boolean requestAttributionData;
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
      //   146: ifle +3862 -> 4008
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
      //   495: ifeq +3488 -> 3983
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
      //   523: invokestatic 249	com/kochava/android/tracker/Feature:access$2900	(Ljava/lang/Exception;)V
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
      //   763: ifne +3503 -> 4266
      //   766: aload 13
      //   768: ifnull +2846 -> 3614
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
      //   1765: ifeq +2164 -> 3929
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
      //   1793: invokestatic 249	com/kochava/android/tracker/Feature:access$2900	(Ljava/lang/Exception;)V
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
      //   2833: if_icmpge +513 -> 3346
      //   2836: aload 15
      //   2838: iload_1
      //   2839: invokevirtual 473	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2842: invokevirtual 474	java/lang/Object:toString	()Ljava/lang/String;
      //   2845: invokevirtual 477	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2848: ldc_w 688
      //   2851: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2854: ifeq +14 -> 2868
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
      //   3317: astore 15
      //   3319: new 87	java/lang/StringBuilder
      //   3322: dup
      //   3323: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3326: ldc_w 730
      //   3329: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3332: aload 15
      //   3334: invokevirtual 252	java/lang/Exception:toString	()Ljava/lang/String;
      //   3337: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3340: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3343: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3346: aload 13
      //   3348: ldc_w 732
      //   3351: invokevirtual 314	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3354: ldc_w 734
      //   3357: invokevirtual 314	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3360: astore 15
      //   3362: aload_0
      //   3363: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3366: aload 15
      //   3368: invokestatic 738	com/kochava/android/tracker/Feature:access$2400	(Lcom/kochava/android/tracker/Feature;Lorg/json/JSONObject;)V
      //   3371: invokestatic 741	com/kochava/android/tracker/Feature:access$2300	()Z
      //   3374: ifeq +18 -> 3392
      //   3377: invokestatic 744	com/kochava/android/tracker/Feature:access$2500	()Z
      //   3380: ifeq +12 -> 3392
      //   3383: getstatic 357	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
      //   3386: invokestatic 748	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
      //   3389: invokevirtual 751	com/kochava/android/tracker/LocationDirector:getLocation	()V
      //   3392: aload 13
      //   3394: ldc_w 753
      //   3397: invokevirtual 266	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3400: invokestatic 757	com/kochava/android/tracker/Feature:access$2602	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3403: pop
      //   3404: new 87	java/lang/StringBuilder
      //   3407: dup
      //   3408: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3411: ldc_w 759
      //   3414: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3417: invokestatic 763	com/kochava/android/tracker/Feature:access$2600	()Lorg/json/JSONArray;
      //   3420: invokevirtual 470	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3423: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3426: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3429: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3432: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3435: invokeinterface 373 1 0
      //   3440: ldc_w 765
      //   3443: invokestatic 763	com/kochava/android/tracker/Feature:access$2600	()Lorg/json/JSONArray;
      //   3446: invokevirtual 470	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3449: invokeinterface 381 3 0
      //   3454: invokeinterface 384 1 0
      //   3459: aload 14
      //   3461: ldc_w 767
      //   3464: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3467: ifnull +88 -> 3555
      //   3470: aload 14
      //   3472: ldc_w 767
      //   3475: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3478: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3481: ldc_w 486
      //   3484: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3487: ifeq +432 -> 3919
      //   3490: aload 14
      //   3492: ldc_w 767
      //   3495: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3498: checkcast 486	java/lang/Boolean
      //   3501: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   3504: ifeq +415 -> 3919
      //   3507: iconst_1
      //   3508: istore_1
      //   3509: aload 14
      //   3511: ldc_w 767
      //   3514: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3517: invokevirtual 231	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3520: ldc 81
      //   3522: invokevirtual 334	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3525: ifeq +399 -> 3924
      //   3528: ldc_w 260
      //   3531: aload 14
      //   3533: ldc_w 767
      //   3536: invokevirtual 331	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3539: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3542: ifeq +382 -> 3924
      //   3545: iconst_1
      //   3546: istore_2
      //   3547: goto +744 -> 4291
      //   3550: iconst_1
      //   3551: invokestatic 770	com/kochava/android/tracker/Feature:access$2702	(Z)Z
      //   3554: pop
      //   3555: aload 13
      //   3557: ldc_w 772
      //   3560: invokevirtual 320	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3563: astore 13
      //   3565: new 87	java/lang/StringBuilder
      //   3568: dup
      //   3569: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3572: ldc_w 774
      //   3575: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3578: aload 13
      //   3580: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3583: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3586: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3589: aload 13
      //   3591: ldc_w 776
      //   3594: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3597: ifeq +17 -> 3614
      //   3600: iconst_1
      //   3601: invokestatic 779	com/kochava/android/tracker/Feature:access$2802	(Z)Z
      //   3604: pop
      //   3605: return
      //   3606: astore 13
      //   3608: ldc_w 781
      //   3611: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3614: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3617: invokeinterface 373 1 0
      //   3622: ldc 57
      //   3624: invokestatic 51	java/lang/System:currentTimeMillis	()J
      //   3627: invokeinterface 420 4 0
      //   3632: invokeinterface 384 1 0
      //   3637: ldc_w 783
      //   3640: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3643: iconst_0
      //   3644: istore_1
      //   3645: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3648: ldc_w 258
      //   3651: ldc 71
      //   3653: invokeinterface 75 3 0
      //   3658: ldc_w 260
      //   3661: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3664: ifne +548 -> 4212
      //   3667: invokestatic 484	com/kochava/android/tracker/Feature:access$1600	()Ljava/util/HashMap;
      //   3670: ldc_w 695
      //   3673: invokevirtual 295	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   3676: checkcast 486	java/lang/Boolean
      //   3679: invokevirtual 549	java/lang/Boolean:booleanValue	()Z
      //   3682: ifne +363 -> 4045
      //   3685: iconst_1
      //   3686: istore_2
      //   3687: invokestatic 786	com/kochava/android/tracker/Feature:access$3000	()Z
      //   3690: istore 12
      //   3692: iload_1
      //   3693: invokestatic 393	com/kochava/android/tracker/Feature:access$1100	()I
      //   3696: if_icmpge +50 -> 3746
      //   3699: invokestatic 55	com/kochava/android/tracker/Feature:access$400	()Landroid/content/SharedPreferences;
      //   3702: ldc_w 788
      //   3705: ldc_w 790
      //   3708: invokeinterface 75 3 0
      //   3713: ldc_w 790
      //   3716: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3719: ifne +331 -> 4050
      //   3722: iconst_1
      //   3723: istore_3
      //   3724: invokestatic 793	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3727: ifnull +328 -> 4055
      //   3730: iconst_1
      //   3731: istore 4
      //   3733: iload_3
      //   3734: ifeq +327 -> 4061
      //   3737: iload_2
      //   3738: ifne +8 -> 3746
      //   3741: iload 12
      //   3743: ifeq +318 -> 4061
      //   3746: new 87	java/lang/StringBuilder
      //   3749: dup
      //   3750: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3753: ldc_w 795
      //   3756: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3759: iload_1
      //   3760: invokevirtual 398	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3763: ldc_w 519
      //   3766: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3769: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3772: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3775: iload_1
      //   3776: istore_2
      //   3777: iload_1
      //   3778: iconst_2
      //   3779: if_icmpge +46 -> 3825
      //   3782: aload_0
      //   3783: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3786: invokestatic 798	com/kochava/android/tracker/Feature:access$000	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   3789: ifnull +323 -> 4112
      //   3792: iconst_1
      //   3793: istore_3
      //   3794: invokestatic 793	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3797: ifnull +320 -> 4117
      //   3800: invokestatic 793	com/kochava/android/tracker/Feature:access$100	()Ljava/lang/String;
      //   3803: invokevirtual 123	java/lang/String:isEmpty	()Z
      //   3806: ifne +311 -> 4117
      //   3809: iconst_1
      //   3810: istore 4
      //   3812: iload_1
      //   3813: istore_2
      //   3814: iload 4
      //   3816: ifne +9 -> 3825
      //   3819: iload_3
      //   3820: ifeq +303 -> 4123
      //   3823: iload_1
      //   3824: istore_2
      //   3825: iload_2
      //   3826: iconst_2
      //   3827: if_icmpge +19 -> 3846
      //   3830: aload_0
      //   3831: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3834: invokestatic 801	com/kochava/android/tracker/Feature:access$300	(Lcom/kochava/android/tracker/Feature;)Ljava/lang/String;
      //   3837: ifnull +328 -> 4165
      //   3840: iconst_1
      //   3841: istore_1
      //   3842: iload_1
      //   3843: ifeq +327 -> 4170
      //   3846: invokestatic 807	android/os/Message:obtain	()Landroid/os/Message;
      //   3849: astore 13
      //   3851: new 809	android/os/Bundle
      //   3854: dup
      //   3855: invokespecial 810	android/os/Bundle:<init>	()V
      //   3858: astore 14
      //   3860: aload 14
      //   3862: ldc_w 812
      //   3865: invokestatic 815	com/kochava/android/tracker/Feature:access$3100	()Z
      //   3868: invokevirtual 819	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   3871: aload 13
      //   3873: aload 14
      //   3875: invokevirtual 823	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   3878: aload_0
      //   3879: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3882: invokestatic 827	com/kochava/android/tracker/Feature:access$3200	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   3885: ifnull -3359 -> 526
      //   3888: ldc_w 829
      //   3891: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3894: aload_0
      //   3895: getfield 14	com/kochava/android/tracker/Feature$6:this$0	Lcom/kochava/android/tracker/Feature;
      //   3898: invokestatic 827	com/kochava/android/tracker/Feature:access$3200	(Lcom/kochava/android/tracker/Feature;)Landroid/os/Handler;
      //   3901: aload 13
      //   3903: invokevirtual 835	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   3906: pop
      //   3907: return
      //   3908: astore 15
      //   3910: ldc_w 837
      //   3913: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   3916: goto -457 -> 3459
      //   3919: iconst_0
      //   3920: istore_1
      //   3921: goto -412 -> 3509
      //   3924: iconst_0
      //   3925: istore_2
      //   3926: goto +365 -> 4291
      //   3929: new 87	java/lang/StringBuilder
      //   3932: dup
      //   3933: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3936: ldc_w 839
      //   3939: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3942: aload 13
      //   3944: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3947: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3950: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3953: return
      //   3954: astore 13
      //   3956: new 87	java/lang/StringBuilder
      //   3959: dup
      //   3960: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3963: ldc_w 841
      //   3966: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3969: aload 13
      //   3971: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3974: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3977: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   3980: goto -366 -> 3614
      //   3983: new 87	java/lang/StringBuilder
      //   3986: dup
      //   3987: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   3990: ldc_w 839
      //   3993: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3996: aload 13
      //   3998: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4001: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4004: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4007: return
      //   4008: new 87	java/lang/StringBuilder
      //   4011: dup
      //   4012: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4015: ldc_w 843
      //   4018: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4021: lload 6
      //   4023: ldc2_w 844
      //   4026: ldiv
      //   4027: invokevirtual 637	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   4030: ldc_w 847
      //   4033: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4036: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4039: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4042: goto -405 -> 3637
      //   4045: iconst_0
      //   4046: istore_2
      //   4047: goto -360 -> 3687
      //   4050: iconst_0
      //   4051: istore_3
      //   4052: goto -328 -> 3724
      //   4055: iconst_0
      //   4056: istore 4
      //   4058: goto -325 -> 3733
      //   4061: iload_3
      //   4062: ifeq +8 -> 4070
      //   4065: iload 4
      //   4067: ifne -321 -> 3746
      //   4070: ldc2_w 844
      //   4073: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   4076: iload_1
      //   4077: iconst_1
      //   4078: iadd
      //   4079: istore_1
      //   4080: goto -388 -> 3692
      //   4083: astore 13
      //   4085: new 87	java/lang/StringBuilder
      //   4088: dup
      //   4089: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4092: ldc_w 849
      //   4095: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4098: aload 13
      //   4100: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4103: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4106: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4109: goto -33 -> 4076
      //   4112: iconst_0
      //   4113: istore_3
      //   4114: goto -320 -> 3794
      //   4117: iconst_0
      //   4118: istore 4
      //   4120: goto -308 -> 3812
      //   4123: ldc2_w 844
      //   4126: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   4129: iload_1
      //   4130: iconst_1
      //   4131: iadd
      //   4132: istore_1
      //   4133: goto -358 -> 3775
      //   4136: astore 13
      //   4138: new 87	java/lang/StringBuilder
      //   4141: dup
      //   4142: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4145: ldc_w 849
      //   4148: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4151: aload 13
      //   4153: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4156: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4159: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4162: goto -33 -> 4129
      //   4165: iconst_0
      //   4166: istore_1
      //   4167: goto -325 -> 3842
      //   4170: ldc2_w 844
      //   4173: invokestatic 306	java/lang/Thread:sleep	(J)V
      //   4176: iload_2
      //   4177: iconst_1
      //   4178: iadd
      //   4179: istore_2
      //   4180: goto -355 -> 3825
      //   4183: astore 13
      //   4185: new 87	java/lang/StringBuilder
      //   4188: dup
      //   4189: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   4192: ldc_w 849
      //   4195: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4198: aload 13
      //   4200: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4203: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4206: invokestatic 245	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
      //   4209: goto -33 -> 4176
      //   4212: ldc_w 851
      //   4215: invokestatic 40	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
      //   4218: goto -372 -> 3846
      //   4221: astore 13
      //   4223: goto -267 -> 3956
      //   4226: astore 14
      //   4228: goto -673 -> 3555
      //   4231: astore 15
      //   4233: goto -774 -> 3459
      //   4236: astore 15
      //   4238: goto -867 -> 3371
      //   4241: astore 15
      //   4243: goto -2645 -> 1598
      //   4246: astore 15
      //   4248: goto -2728 -> 1520
      //   4251: astore 15
      //   4253: goto -2811 -> 1442
      //   4256: astore 15
      //   4258: goto -3232 -> 1026
      //   4261: astore 15
      //   4263: goto -3298 -> 965
      //   4266: iload_2
      //   4267: istore_1
      //   4268: goto -4114 -> 154
      //   4271: astore 15
      //   4273: goto -3187 -> 1086
      //   4276: astore 15
      //   4278: goto -3100 -> 1178
      //   4281: astore 15
      //   4283: goto -3006 -> 1277
      //   4286: astore 15
      //   4288: goto -2926 -> 1362
      //   4291: iload_1
      //   4292: ifne -742 -> 3550
      //   4295: iload_2
      //   4296: ifeq -741 -> 3555
      //   4299: goto -749 -> 3550
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4302	0	this	6
      //   43	4249	1	i	int
      //   14	4282	2	j	int
      //   12	4102	3	k	int
      //   652	3467	4	m	int
      //   649	8	5	n	int
      //   9	4013	6	l1	long
      //   18	18	8	l2	long
      //   33	5	10	l3	long
      //   622	3120	12	bool	boolean
      //   150	1	13	localObject1	Object
      //   483	39	13	localIOException1	IOException
      //   527	15	13	localException1	Exception
      //   601	998	13	localObject2	Object
      //   1753	39	13	localException2	Exception
      //   1797	1759	13	localIOException2	IOException
      //   3563	27	13	str	String
      //   3606	1	13	localException3	Exception
      //   3849	94	13	localMessage	Message
      //   3954	43	13	localException4	Exception
      //   4083	16	13	localInterruptedException1	InterruptedException
      //   4136	16	13	localInterruptedException2	InterruptedException
      //   4183	16	13	localInterruptedException3	InterruptedException
      //   4221	1	13	localException5	Exception
      //   287	1234	14	localObject3	Object
      //   1694	16	14	localJSONException1	JSONException
      //   1735	1797	14	localJSONException2	JSONException
      //   3858	16	14	localBundle	Bundle
      //   4226	1	14	localException6	Exception
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
      //   3276	16	15	localException11	Exception
      //   3317	16	15	localException12	Exception
      //   3360	7	15	localJSONObject	JSONObject
      //   3908	1	15	localException13	Exception
      //   4231	1	15	localJSONException6	JSONException
      //   4236	1	15	localException14	Exception
      //   4241	1	15	localException15	Exception
      //   4246	1	15	localException16	Exception
      //   4251	1	15	localException17	Exception
      //   4256	1	15	localException18	Exception
      //   4261	1	15	localException19	Exception
      //   4271	1	15	localException20	Exception
      //   4276	1	15	localException21	Exception
      //   4281	1	15	localException22	Exception
      //   4286	1	15	localException23	Exception
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
      //   3319	3346	1753	java/lang/Exception
      //   3371	3392	1753	java/lang/Exception
      //   3608	3614	1753	java/lang/Exception
      //   3910	3916	1753	java/lang/Exception
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
      //   3319	3346	1797	java/io/IOException
      //   3346	3371	1797	java/io/IOException
      //   3371	3392	1797	java/io/IOException
      //   3392	3459	1797	java/io/IOException
      //   3459	3507	1797	java/io/IOException
      //   3509	3545	1797	java/io/IOException
      //   3550	3555	1797	java/io/IOException
      //   3555	3605	1797	java/io/IOException
      //   3608	3614	1797	java/io/IOException
      //   3910	3916	1797	java/io/IOException
      //   3929	3953	1797	java/io/IOException
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
      //   2788	2825	3317	java/lang/Exception
      //   2827	2868	3317	java/lang/Exception
      //   3555	3605	3606	java/lang/Exception
      //   3392	3459	3908	java/lang/Exception
      //   603	624	3954	java/lang/Exception
      //   638	648	3954	java/lang/Exception
      //   659	685	3954	java/lang/Exception
      //   698	756	3954	java/lang/Exception
      //   1755	1796	3954	java/lang/Exception
      //   3929	3953	3954	java/lang/Exception
      //   4070	4076	4083	java/lang/InterruptedException
      //   4123	4129	4136	java/lang/InterruptedException
      //   4170	4176	4183	java/lang/InterruptedException
      //   154	203	4221	java/lang/Exception
      //   203	214	4221	java/lang/Exception
      //   214	460	4221	java/lang/Exception
      //   460	467	4221	java/lang/Exception
      //   472	480	4221	java/lang/Exception
      //   558	588	4221	java/lang/Exception
      //   588	599	4221	java/lang/Exception
      //   1696	1723	4221	java/lang/Exception
      //   3459	3507	4226	java/lang/Exception
      //   3509	3545	4226	java/lang/Exception
      //   3550	3555	4226	java/lang/Exception
      //   3392	3459	4231	org/json/JSONException
      //   3346	3371	4236	java/lang/Exception
      //   1520	1535	4241	java/lang/Exception
      //   1540	1569	4241	java/lang/Exception
      //   1571	1598	4241	java/lang/Exception
      //   2285	2314	4241	java/lang/Exception
      //   1442	1457	4246	java/lang/Exception
      //   1462	1491	4246	java/lang/Exception
      //   1493	1520	4246	java/lang/Exception
      //   2241	2270	4246	java/lang/Exception
      //   1362	1377	4251	java/lang/Exception
      //   1383	1412	4251	java/lang/Exception
      //   1415	1442	4251	java/lang/Exception
      //   2197	2226	4251	java/lang/Exception
      //   965	1026	4256	java/lang/Exception
      //   916	965	4261	java/lang/Exception
      //   1026	1086	4271	java/lang/Exception
      //   1824	1869	4271	java/lang/Exception
      //   1872	1897	4271	java/lang/Exception
      //   1086	1138	4276	java/lang/Exception
      //   1144	1173	4276	java/lang/Exception
      //   1173	1178	4276	java/lang/Exception
      //   1907	1943	4276	java/lang/Exception
      //   1946	1984	4276	java/lang/Exception
      //   1178	1221	4281	java/lang/Exception
      //   1225	1277	4281	java/lang/Exception
      //   1994	2046	4281	java/lang/Exception
      //   2049	2104	4281	java/lang/Exception
      //   1277	1323	4286	java/lang/Exception
      //   1328	1362	4286	java/lang/Exception
      //   2113	2148	4286	java/lang/Exception
      //   2151	2185	4286	java/lang/Exception
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
        //   245: invokestatic 142	com/kochava/android/tracker/Feature:access$3300	()Ljava/lang/String;
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
        //   517: invokestatic 221	com/kochava/android/tracker/Feature:access$2900	(Ljava/lang/Exception;)V
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
        //   763: invokestatic 248	com/kochava/android/tracker/Feature:access$3400	()Landroid/content/SharedPreferences;
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
        //   798: invokestatic 267	com/kochava/android/tracker/Feature:access$3500	()Landroid/os/Handler;
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
        //   887: invokestatic 267	com/kochava/android/tracker/Feature:access$3500	()Landroid/os/Handler;
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
        //   1020: invokestatic 221	com/kochava/android/tracker/Feature:access$2900	(Ljava/lang/Exception;)V
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
            localJSONObject1.put("kochava_device_id", Feature.access$3300());
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
          Feature.access$4802(false);
        }
      }, 60000L);
    }
    Logging.Log("FIRE EVENT*** action:" + paramString);
    Logging.Log("FIRE EVENT*** properties:" + paramMap1);
    JSONObject localJSONObject1 = new JSONObject();
    label1050:
    label1087:
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
                break label1087;
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
              localJSONObject1.put("sdk_version", "Android20160615-NOEMAIL" + versionExtension);
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
                  break label1050;
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
    //   8: getstatic 239	com/kochava/android/tracker/Feature:ATTRIBUTION_ID_CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 536	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc_w 929
    //   20: aastore
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 935	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +18 -> 47
    //   32: aload_0
    //   33: astore_2
    //   34: aload_0
    //   35: astore_3
    //   36: aload_0
    //   37: invokeinterface 940 1 0
    //   42: istore_1
    //   43: iload_1
    //   44: ifne +24 -> 68
    //   47: aload_0
    //   48: ifnull +18 -> 66
    //   51: aload_0
    //   52: invokeinterface 943 1 0
    //   57: ifne +9 -> 66
    //   60: aload_0
    //   61: invokeinterface 946 1 0
    //   66: aconst_null
    //   67: areturn
    //   68: aload_0
    //   69: astore_2
    //   70: aload_0
    //   71: astore_3
    //   72: aload_0
    //   73: aload_0
    //   74: ldc_w 929
    //   77: invokeinterface 950 2 0
    //   82: invokeinterface 951 2 0
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
    //   101: invokeinterface 943 1 0
    //   106: ifne +11 -> 117
    //   109: aload_0
    //   110: invokeinterface 946 1 0
    //   115: aload_2
    //   116: astore_3
    //   117: aload_3
    //   118: areturn
    //   119: astore_0
    //   120: aload_2
    //   121: astore_3
    //   122: new 488	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 953
    //   132: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_0
    //   136: invokevirtual 909	java/lang/Exception:toString	()Ljava/lang/String;
    //   139: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   148: aload 4
    //   150: astore_3
    //   151: aload_2
    //   152: ifnull -35 -> 117
    //   155: aload 4
    //   157: astore_3
    //   158: aload_2
    //   159: invokeinterface 943 1 0
    //   164: ifne -47 -> 117
    //   167: aload_2
    //   168: invokeinterface 946 1 0
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
    //   192: invokeinterface 943 1 0
    //   197: ifne +9 -> 206
    //   200: aload_3
    //   201: invokeinterface 946 1 0
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
        break label732;
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
          Feature.access$2802(true);
          Object localObject2 = this.val$e.getMessage();
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("message", localObject2);
          ((JSONObject)localObject1).put("os_version", Feature.access$3900());
          ((JSONObject)localObject1).put("device", Feature.access$5800() + "-" + Feature.access$5900());
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$3300());
          ((JSONObject)localObject2).put("action", "error");
          ((JSONObject)localObject2).put("data", localObject1);
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20160615-NOEMAIL" + Feature.versionExtension);
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
    //   1: ifnull +2126 -> 2127
    //   4: aload_1
    //   5: invokevirtual 1089	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 188	com/kochava/android/tracker/Feature:sendOnStart	Z
    //   15: ldc_w 1091
    //   18: new 488	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 1093
    //   28: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: getstatic 162	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   34: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 1099	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   43: pop
    //   44: aload_0
    //   45: new 634	java/util/Timer
    //   48: dup
    //   49: invokespecial 1100	java/util/Timer:<init>	()V
    //   52: putfield 663	com/kochava/android/tracker/Feature:eventFlushTimer	Ljava/util/Timer;
    //   55: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   58: ldc_w 520
    //   61: iconst_0
    //   62: invokevirtual 526	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   65: putstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   68: new 857	com/kochava/android/tracker/DbAdapter
    //   71: dup
    //   72: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   75: invokespecial 1101	com/kochava/android/tracker/DbAdapter:<init>	(Landroid/content/Context;)V
    //   78: putstatic 412	com/kochava/android/tracker/Feature:kDbAdapter	Lcom/kochava/android/tracker/DbAdapter;
    //   81: aload_3
    //   82: ifnull +231 -> 313
    //   85: aload_3
    //   86: ldc_w 1103
    //   89: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   92: ifnull +44 -> 136
    //   95: aload_3
    //   96: ldc_w 1103
    //   99: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   105: ldc_w 704
    //   108: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   111: ifeq +25 -> 136
    //   114: aload_3
    //   115: ldc_w 1103
    //   118: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   121: checkcast 704	java/lang/Boolean
    //   124: invokevirtual 707	java/lang/Boolean:booleanValue	()Z
    //   127: istore_2
    //   128: iload_2
    //   129: invokestatic 1110	com/kochava/android/tracker/Feature:enableDebug	(Z)V
    //   132: iload_2
    //   133: invokestatic 1113	com/kochava/android/tracker/Feature:setErrorDebug	(Z)V
    //   136: aload_3
    //   137: ldc_w 1115
    //   140: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   143: ifnull +35 -> 178
    //   146: aload_3
    //   147: ldc_w 1115
    //   150: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   153: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   156: ldc_w 536
    //   159: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   162: ifeq +16 -> 178
    //   165: aload_3
    //   166: ldc_w 1115
    //   169: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   172: checkcast 536	java/lang/String
    //   175: putstatic 162	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   178: aload_3
    //   179: ldc_w 1117
    //   182: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   185: ifnull +38 -> 223
    //   188: aload_3
    //   189: ldc_w 1117
    //   192: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   195: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   198: ldc_w 704
    //   201: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   204: ifeq +19 -> 223
    //   207: aload_3
    //   208: ldc_w 1117
    //   211: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   214: checkcast 704	java/lang/Boolean
    //   217: invokevirtual 707	java/lang/Boolean:booleanValue	()Z
    //   220: putstatic 164	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   223: aload_3
    //   224: ldc_w 1119
    //   227: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   230: ifnull +38 -> 268
    //   233: aload_3
    //   234: ldc_w 1119
    //   237: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   240: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   243: ldc_w 704
    //   246: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   249: ifeq +19 -> 268
    //   252: aload_3
    //   253: ldc_w 1119
    //   256: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   259: checkcast 704	java/lang/Boolean
    //   262: invokevirtual 707	java/lang/Boolean:booleanValue	()Z
    //   265: putstatic 186	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   268: aload_3
    //   269: ldc_w 1121
    //   272: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   275: ifnull +38 -> 313
    //   278: aload_3
    //   279: ldc_w 1121
    //   282: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   285: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   288: ldc_w 704
    //   291: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   294: ifeq +19 -> 313
    //   297: aload_3
    //   298: ldc_w 1121
    //   301: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   304: checkcast 704	java/lang/Boolean
    //   307: invokevirtual 707	java/lang/Boolean:booleanValue	()Z
    //   310: putstatic 203	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   313: aload_0
    //   314: invokespecial 1123	com/kochava/android/tracker/Feature:initHandler	()V
    //   317: new 488	java/lang/StringBuilder
    //   320: dup
    //   321: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   324: ldc_w 1125
    //   327: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   333: ldc_w 1127
    //   336: ldc -98
    //   338: invokeinterface 534 3 0
    //   343: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   352: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   355: ldc_w 1127
    //   358: ldc -98
    //   360: invokeinterface 534 3 0
    //   365: ldc -98
    //   367: invokevirtual 540	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   370: ifne +26 -> 396
    //   373: new 244	org/json/JSONArray
    //   376: dup
    //   377: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   380: ldc_w 1127
    //   383: ldc -98
    //   385: invokeinterface 534 3 0
    //   390: invokespecial 1128	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   393: putstatic 247	com/kochava/android/tracker/Feature:eventnameBlacklist	Lorg/json/JSONArray;
    //   396: getstatic 621	android/os/Build$VERSION:SDK_INT	I
    //   399: bipush 14
    //   401: if_icmplt +1767 -> 2168
    //   404: getstatic 164	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   407: ifne +1761 -> 2168
    //   410: new 488	java/lang/StringBuilder
    //   413: dup
    //   414: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   417: ldc_w 1130
    //   420: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: getstatic 164	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   426: invokevirtual 613	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   429: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   432: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   435: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   438: checkcast 1132	android/app/Application
    //   441: new 54	com/kochava/android/tracker/Feature$LifeCycleTracker
    //   444: dup
    //   445: aload_0
    //   446: invokespecial 1133	com/kochava/android/tracker/Feature$LifeCycleTracker:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   449: invokevirtual 1137	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   452: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   455: new 57	com/kochava/android/tracker/Feature$MemoryBoss
    //   458: dup
    //   459: aload_0
    //   460: invokespecial 1138	com/kochava/android/tracker/Feature$MemoryBoss:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   463: invokevirtual 1142	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   466: iconst_1
    //   467: putstatic 1145	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:active	Z
    //   470: iconst_1
    //   471: putstatic 1148	com/kochava/android/tracker/Feature$AppLifeCycleStatusManager:resumed	Z
    //   474: new 34	com/kochava/android/tracker/Feature$3
    //   477: dup
    //   478: aload_0
    //   479: invokespecial 1149	com/kochava/android/tracker/Feature$3:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   482: invokevirtual 655	java/lang/Thread:start	()V
    //   485: getstatic 186	com/kochava/android/tracker/Feature:suppress_adid	Z
    //   488: ifne +14 -> 502
    //   491: new 36	com/kochava/android/tracker/Feature$4
    //   494: dup
    //   495: aload_0
    //   496: invokespecial 1150	com/kochava/android/tracker/Feature$4:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   499: invokevirtual 655	java/lang/Thread:start	()V
    //   502: aload_0
    //   503: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   506: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   509: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   512: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   515: iconst_0
    //   516: invokevirtual 1163	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   519: getfield 1168	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   522: putfield 919	com/kochava/android/tracker/Feature:mAppPackageName	Ljava/lang/String;
    //   525: aload_0
    //   526: new 508	org/json/JSONObject
    //   529: dup
    //   530: invokespecial 675	org/json/JSONObject:<init>	()V
    //   533: putfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   536: aload_0
    //   537: new 508	org/json/JSONObject
    //   540: dup
    //   541: invokespecial 675	org/json/JSONObject:<init>	()V
    //   544: putfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   547: aload_0
    //   548: new 508	org/json/JSONObject
    //   551: dup
    //   552: invokespecial 675	org/json/JSONObject:<init>	()V
    //   555: putfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   558: aconst_null
    //   559: astore 13
    //   561: aconst_null
    //   562: astore 6
    //   564: aconst_null
    //   565: astore 9
    //   567: aconst_null
    //   568: astore 7
    //   570: ldc_w 692
    //   573: astore_1
    //   574: aconst_null
    //   575: astore 10
    //   577: aconst_null
    //   578: astore 8
    //   580: aconst_null
    //   581: astore 11
    //   583: aconst_null
    //   584: astore 14
    //   586: aload_1
    //   587: astore 12
    //   589: aload_3
    //   590: ifnull +633 -> 1223
    //   593: aload 6
    //   595: astore 5
    //   597: aload_3
    //   598: ldc_w 1176
    //   601: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   604: ifnull +38 -> 642
    //   607: aload 6
    //   609: astore 5
    //   611: aload_3
    //   612: ldc_w 1176
    //   615: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   618: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   621: ldc_w 536
    //   624: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   627: ifeq +15 -> 642
    //   630: aload_3
    //   631: ldc_w 1176
    //   634: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   637: checkcast 536	java/lang/String
    //   640: astore 5
    //   642: aload 7
    //   644: astore 6
    //   646: aload_3
    //   647: ldc_w 677
    //   650: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   653: ifnull +38 -> 691
    //   656: aload 7
    //   658: astore 6
    //   660: aload_3
    //   661: ldc_w 677
    //   664: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   667: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   670: ldc_w 536
    //   673: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   676: ifeq +15 -> 691
    //   679: aload_3
    //   680: ldc_w 677
    //   683: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   686: checkcast 536	java/lang/String
    //   689: astore 6
    //   691: aload_1
    //   692: astore 7
    //   694: aload_3
    //   695: ldc_w 690
    //   698: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   701: ifnull +37 -> 738
    //   704: aload_1
    //   705: astore 7
    //   707: aload_3
    //   708: ldc_w 690
    //   711: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   714: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   717: ldc_w 536
    //   720: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   723: ifeq +15 -> 738
    //   726: aload_3
    //   727: ldc_w 690
    //   730: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   733: checkcast 536	java/lang/String
    //   736: astore 7
    //   738: aload 8
    //   740: astore_1
    //   741: aload_3
    //   742: ldc_w 1178
    //   745: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   748: ifnull +33 -> 781
    //   751: aload_3
    //   752: ldc_w 1178
    //   755: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   758: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   761: ldc_w 536
    //   764: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   767: ifeq +1429 -> 2196
    //   770: aload_3
    //   771: ldc_w 1178
    //   774: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   777: checkcast 536	java/lang/String
    //   780: astore_1
    //   781: aload_3
    //   782: ldc_w 1180
    //   785: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   788: ifnull +34 -> 822
    //   791: aload_3
    //   792: ldc_w 1180
    //   795: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   798: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   801: ldc_w 536
    //   804: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   807: ifeq +15 -> 822
    //   810: aload_3
    //   811: ldc_w 1180
    //   814: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   817: checkcast 536	java/lang/String
    //   820: astore 8
    //   822: aload 14
    //   824: astore 8
    //   826: aload_3
    //   827: ldc_w 1182
    //   830: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   833: ifnull +38 -> 871
    //   836: aload 14
    //   838: astore 8
    //   840: aload_3
    //   841: ldc_w 1182
    //   844: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   847: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   850: ldc_w 536
    //   853: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   856: ifeq +15 -> 871
    //   859: aload_3
    //   860: ldc_w 1182
    //   863: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   866: checkcast 536	java/lang/String
    //   869: astore 8
    //   871: aload_3
    //   872: ldc_w 772
    //   875: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   878: ifnull +39 -> 917
    //   881: aload_3
    //   882: ldc_w 772
    //   885: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   888: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   891: ldc_w 704
    //   894: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   897: ifeq +20 -> 917
    //   900: aload_0
    //   901: aload_3
    //   902: ldc_w 772
    //   905: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   908: checkcast 704	java/lang/Boolean
    //   911: invokevirtual 707	java/lang/Boolean:booleanValue	()Z
    //   914: putfield 258	com/kochava/android/tracker/Feature:app_limit_tracking	Z
    //   917: aload_3
    //   918: ldc_w 783
    //   921: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   924: ifnull +115 -> 1039
    //   927: aload_3
    //   928: ldc_w 783
    //   931: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   934: instanceof 698
    //   937: ifeq +102 -> 1039
    //   940: aload_3
    //   941: ldc_w 783
    //   944: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   947: checkcast 698	java/util/HashMap
    //   950: putstatic 1184	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   953: new 508	org/json/JSONObject
    //   956: dup
    //   957: invokespecial 675	org/json/JSONObject:<init>	()V
    //   960: putstatic 781	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   963: getstatic 1184	com/kochava/android/tracker/Feature:identityLinkMap	Ljava/util/Map;
    //   966: invokeinterface 835 1 0
    //   971: invokeinterface 838 1 0
    //   976: astore 9
    //   978: aload 9
    //   980: invokeinterface 567 1 0
    //   985: ifeq +54 -> 1039
    //   988: aload 9
    //   990: invokeinterface 571 1 0
    //   995: checkcast 840	java/util/Map$Entry
    //   998: astore 10
    //   1000: getstatic 781	com/kochava/android/tracker/Feature:identityLinkMapJSON	Lorg/json/JSONObject;
    //   1003: aload 10
    //   1005: invokeinterface 843 1 0
    //   1010: checkcast 536	java/lang/String
    //   1013: aload 10
    //   1015: invokeinterface 846 1 0
    //   1020: checkcast 536	java/lang/String
    //   1023: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1026: pop
    //   1027: aload 9
    //   1029: invokeinterface 1187 1 0
    //   1034: goto -56 -> 978
    //   1037: astore 9
    //   1039: aload_3
    //   1040: ldc_w 789
    //   1043: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1046: ifnull +36 -> 1082
    //   1049: aload_3
    //   1050: ldc_w 789
    //   1053: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1056: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1059: ldc_w 536
    //   1062: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1065: ifeq +17 -> 1082
    //   1068: aload_0
    //   1069: aload_3
    //   1070: ldc_w 789
    //   1073: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1076: checkcast 536	java/lang/String
    //   1079: putfield 785	com/kochava/android/tracker/Feature:clickData	Ljava/lang/String;
    //   1082: aload 6
    //   1084: astore 9
    //   1086: aload_1
    //   1087: astore 10
    //   1089: aload 8
    //   1091: astore 11
    //   1093: aload 7
    //   1095: astore 12
    //   1097: aload 5
    //   1099: astore 13
    //   1101: aload_3
    //   1102: ldc_w 1188
    //   1105: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1108: ifnull +115 -> 1223
    //   1111: aload 6
    //   1113: astore 9
    //   1115: aload_1
    //   1116: astore 10
    //   1118: aload 8
    //   1120: astore 11
    //   1122: aload 7
    //   1124: astore 12
    //   1126: aload 5
    //   1128: astore 13
    //   1130: aload_3
    //   1131: ldc_w 1188
    //   1134: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1137: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1140: ldc_w 1190
    //   1143: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1146: ifeq +77 -> 1223
    //   1149: aload_3
    //   1150: ldc_w 1188
    //   1153: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1156: checkcast 1190	java/lang/Integer
    //   1159: invokevirtual 1193	java/lang/Integer:intValue	()I
    //   1162: istore 4
    //   1164: iload 4
    //   1166: iconst_1
    //   1167: if_icmpge +1077 -> 2244
    //   1170: new 488	java/lang/StringBuilder
    //   1173: dup
    //   1174: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1177: ldc_w 1195
    //   1180: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: iload 4
    //   1185: invokevirtual 883	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1188: ldc_w 1197
    //   1191: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1197: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1200: iconst_1
    //   1201: putstatic 169	com/kochava/android/tracker/Feature:flushTimerSetByInitializer	Z
    //   1204: aload 5
    //   1206: astore 13
    //   1208: aload 7
    //   1210: astore 12
    //   1212: aload 8
    //   1214: astore 11
    //   1216: aload_1
    //   1217: astore 10
    //   1219: aload 6
    //   1221: astore 9
    //   1223: aload 11
    //   1225: ifnull +19 -> 1244
    //   1228: aload 11
    //   1230: invokevirtual 1007	java/lang/String:trim	()Ljava/lang/String;
    //   1233: invokevirtual 1198	java/lang/String:length	()I
    //   1236: ifeq +8 -> 1244
    //   1239: aload 11
    //   1241: putstatic 160	com/kochava/android/tracker/Feature:hostControl	Ljava/lang/String;
    //   1244: aload 10
    //   1246: ifnull +18 -> 1264
    //   1249: aload 10
    //   1251: ldc_w 1200
    //   1254: invokevirtual 1203	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1257: ifeq +7 -> 1264
    //   1260: iconst_1
    //   1261: putstatic 215	com/kochava/android/tracker/Feature:requestAttributionData	Z
    //   1264: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1267: ldc_w 1205
    //   1270: iconst_0
    //   1271: invokevirtual 526	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1274: putstatic 385	com/kochava/android/tracker/Feature:attributionDataPrefs	Landroid/content/SharedPreferences;
    //   1277: aload 9
    //   1279: ifnull +1057 -> 2336
    //   1282: aload 9
    //   1284: invokevirtual 1007	java/lang/String:trim	()Ljava/lang/String;
    //   1287: invokevirtual 1198	java/lang/String:length	()I
    //   1290: ifeq +1046 -> 2336
    //   1293: aload 9
    //   1295: putstatic 480	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   1298: aload_0
    //   1299: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1302: ldc_w 677
    //   1305: aload 9
    //   1307: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1310: pop
    //   1311: aload_0
    //   1312: getfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1315: ldc_w 677
    //   1318: aload 9
    //   1320: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1323: pop
    //   1324: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1327: ldc_w 1207
    //   1330: ldc -98
    //   1332: invokeinterface 534 3 0
    //   1337: ldc -98
    //   1339: invokevirtual 540	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1342: ifeq +26 -> 1368
    //   1345: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1348: invokeinterface 983 1 0
    //   1353: ldc_w 1207
    //   1356: aload 9
    //   1358: invokeinterface 989 3 0
    //   1363: invokeinterface 992 1 0
    //   1368: aload_0
    //   1369: aload 12
    //   1371: invokespecial 301	com/kochava/android/tracker/Feature:setCurrency	(Ljava/lang/String;)V
    //   1374: aload_0
    //   1375: getfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1378: ldc_w 1209
    //   1381: aload_0
    //   1382: invokespecial 763	com/kochava/android/tracker/Feature:getAppPackageName	()Ljava/lang/String;
    //   1385: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1388: pop
    //   1389: aload_0
    //   1390: getfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1393: ldc_w 1211
    //   1396: ldc_w 1213
    //   1399: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload_0
    //   1404: getfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1407: ldc_w 1215
    //   1410: ldc_w 1217
    //   1413: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1416: pop
    //   1417: aload_0
    //   1418: getfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1421: ldc_w 690
    //   1424: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1427: ldc_w 690
    //   1430: ldc_w 692
    //   1433: invokeinterface 534 3 0
    //   1438: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1441: pop
    //   1442: aload_0
    //   1443: getfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1446: ldc_w 690
    //   1449: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1452: ldc_w 690
    //   1455: ldc_w 692
    //   1458: invokeinterface 534 3 0
    //   1463: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1466: pop
    //   1467: aload_0
    //   1468: getfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1471: ldc_w 1215
    //   1474: ldc_w 1217
    //   1477: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1480: pop
    //   1481: aload_0
    //   1482: getfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1485: ldc_w 690
    //   1488: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1491: ldc_w 690
    //   1494: ldc_w 692
    //   1497: invokeinterface 534 3 0
    //   1502: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1505: pop
    //   1506: aload_0
    //   1507: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1510: ldc_w 737
    //   1513: new 488	java/lang/StringBuilder
    //   1516: dup
    //   1517: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1520: ldc_w 739
    //   1523: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1526: getstatic 162	com/kochava/android/tracker/Feature:versionExtension	Ljava/lang/String;
    //   1529: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1532: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1535: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1538: pop
    //   1539: aload_0
    //   1540: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1543: ldc_w 1219
    //   1546: ldc_w 1221
    //   1549: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1552: pop
    //   1553: aload_0
    //   1554: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1557: ldc_w 853
    //   1560: aload_0
    //   1561: getfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   1564: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1567: pop
    //   1568: aload_0
    //   1569: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1572: ldc_w 1223
    //   1575: aload_0
    //   1576: getfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   1579: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1582: pop
    //   1583: aload_0
    //   1584: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   1587: ldc_w 681
    //   1590: ldc_w 1224
    //   1593: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1596: pop
    //   1597: invokestatic 494	java/lang/System:currentTimeMillis	()J
    //   1600: ldc2_w 495
    //   1603: ldiv
    //   1604: putstatic 194	com/kochava/android/tracker/Feature:startTime	J
    //   1607: iconst_0
    //   1608: istore 4
    //   1610: ldc -98
    //   1612: astore_3
    //   1613: new 580	android/content/ComponentName
    //   1616: dup
    //   1617: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1620: ldc_w 1226
    //   1623: invokespecial 592	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1626: astore_1
    //   1627: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1630: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1633: aload_1
    //   1634: iconst_0
    //   1635: invokevirtual 1230	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1638: pop
    //   1639: ldc_w 1232
    //   1642: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1645: aload_3
    //   1646: astore_1
    //   1647: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1650: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1653: ldc_w 1234
    //   1656: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1659: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1662: invokevirtual 1237	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1665: ifge +27 -> 1692
    //   1668: iconst_1
    //   1669: istore 4
    //   1671: new 488	java/lang/StringBuilder
    //   1674: dup
    //   1675: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1678: aload_3
    //   1679: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1682: ldc_w 1239
    //   1685: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1688: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1691: astore_1
    //   1692: aload_1
    //   1693: astore_3
    //   1694: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1697: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1700: ldc_w 1241
    //   1703: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1706: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1709: invokevirtual 1237	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1712: ifge +27 -> 1739
    //   1715: iconst_1
    //   1716: istore 4
    //   1718: new 488	java/lang/StringBuilder
    //   1721: dup
    //   1722: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1725: aload_1
    //   1726: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1729: ldc_w 1243
    //   1732: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1735: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1738: astore_3
    //   1739: aload_3
    //   1740: astore_1
    //   1741: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1744: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1747: ldc_w 1245
    //   1750: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1753: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1756: invokevirtual 1237	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1759: ifge +27 -> 1786
    //   1762: iconst_1
    //   1763: istore 4
    //   1765: new 488	java/lang/StringBuilder
    //   1768: dup
    //   1769: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1772: aload_3
    //   1773: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1776: ldc_w 1247
    //   1779: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1782: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1785: astore_1
    //   1786: iload 4
    //   1788: ifeq +13 -> 1801
    //   1791: ldc_w 1249
    //   1794: invokestatic 1252	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1797: aload_1
    //   1798: invokestatic 1252	com/kochava/android/util/Logging:LogRequirementsError	(Ljava/lang/String;)V
    //   1801: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1804: ldc_w 1254
    //   1807: ldc -98
    //   1809: invokeinterface 534 3 0
    //   1814: ldc -98
    //   1816: invokevirtual 540	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1819: ifeq +28 -> 1847
    //   1822: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   1825: invokeinterface 983 1 0
    //   1830: ldc_w 1254
    //   1833: aload_0
    //   1834: invokespecial 406	com/kochava/android/tracker/Feature:getUserAgent	()Ljava/lang/String;
    //   1837: invokeinterface 989 3 0
    //   1842: invokeinterface 992 1 0
    //   1847: aload_0
    //   1848: ldc_w 1256
    //   1851: putfield 923	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1854: aload_0
    //   1855: ldc_w 1258
    //   1858: putfield 927	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1861: aload_0
    //   1862: ldc -98
    //   1864: putfield 392	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   1867: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1870: invokevirtual 1089	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1873: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1876: astore_3
    //   1877: aload_3
    //   1878: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1881: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1884: iconst_0
    //   1885: invokevirtual 1262	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1888: astore_1
    //   1889: aload_1
    //   1890: ifnull +730 -> 2620
    //   1893: aload_3
    //   1894: aload_1
    //   1895: invokevirtual 1266	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1898: astore_1
    //   1899: aload_0
    //   1900: aload_1
    //   1901: checkcast 536	java/lang/String
    //   1904: checkcast 536	java/lang/String
    //   1907: putfield 923	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1910: new 488	java/lang/StringBuilder
    //   1913: dup
    //   1914: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1917: ldc_w 1268
    //   1920: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1923: aload_0
    //   1924: getfield 923	com/kochava/android/tracker/Feature:mAppName	Ljava/lang/String;
    //   1927: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1930: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1933: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   1936: aload_0
    //   1937: new 488	java/lang/StringBuilder
    //   1940: dup
    //   1941: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1944: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1947: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1950: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   1953: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1956: iconst_0
    //   1957: invokevirtual 1163	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1960: getfield 1271	android/content/pm/PackageInfo:versionCode	I
    //   1963: invokevirtual 883	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1966: ldc -98
    //   1968: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1971: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1974: putfield 927	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1977: new 488	java/lang/StringBuilder
    //   1980: dup
    //   1981: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   1984: ldc_w 1273
    //   1987: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1990: aload_0
    //   1991: getfield 927	com/kochava/android/tracker/Feature:mAppVersionCode	Ljava/lang/String;
    //   1994: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1997: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2000: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2003: aload_0
    //   2004: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2007: invokevirtual 1154	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2010: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   2013: invokevirtual 1157	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2016: iconst_0
    //   2017: invokevirtual 1163	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2020: getfield 1276	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2023: putfield 392	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2026: new 488	java/lang/StringBuilder
    //   2029: dup
    //   2030: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2033: ldc_w 1278
    //   2036: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2039: aload_0
    //   2040: getfield 392	com/kochava/android/tracker/Feature:mAppVersionName	Ljava/lang/String;
    //   2043: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2046: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2049: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2052: new 38	com/kochava/android/tracker/Feature$5
    //   2055: dup
    //   2056: aload_0
    //   2057: invokespecial 1279	com/kochava/android/tracker/Feature$5:<init>	(Lcom/kochava/android/tracker/Feature;)V
    //   2060: invokevirtual 655	java/lang/Thread:start	()V
    //   2063: aload_0
    //   2064: invokestatic 381	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2067: putfield 1281	com/kochava/android/tracker/Feature:mDeviceId	Ljava/lang/String;
    //   2070: aload_0
    //   2071: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2074: ldc_w 679
    //   2077: invokestatic 381	com/kochava/android/tracker/Feature:getMyDeviceId	()Ljava/lang/String;
    //   2080: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2083: pop
    //   2084: getstatic 229	com/kochava/android/tracker/Feature:worker	Ljava/util/concurrent/ScheduledExecutorService;
    //   2087: aload_0
    //   2088: getfield 265	com/kochava/android/tracker/Feature:getKVinit	Ljava/lang/Runnable;
    //   2091: ldc2_w 1282
    //   2094: getstatic 1289	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2097: invokeinterface 1294 5 0
    //   2102: pop
    //   2103: aload_0
    //   2104: ldc_w 1224
    //   2107: invokevirtual 1297	com/kochava/android/tracker/Feature:tryUpdate	(Ljava/lang/String;)V
    //   2110: return
    //   2111: astore_1
    //   2112: ldc_w 1299
    //   2115: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2118: aload_1
    //   2119: invokevirtual 829	java/lang/Exception:printStackTrace	()V
    //   2122: iconst_1
    //   2123: putstatic 211	com/kochava/android/tracker/Feature:badInit	Z
    //   2126: return
    //   2127: ldc_w 1301
    //   2130: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2133: iconst_1
    //   2134: putstatic 211	com/kochava/android/tracker/Feature:badInit	Z
    //   2137: return
    //   2138: astore_1
    //   2139: new 488	java/lang/StringBuilder
    //   2142: dup
    //   2143: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2146: ldc_w 1303
    //   2149: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2152: aload_1
    //   2153: invokevirtual 909	java/lang/Exception:toString	()Ljava/lang/String;
    //   2156: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2159: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2162: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2165: goto -1769 -> 396
    //   2168: new 488	java/lang/StringBuilder
    //   2171: dup
    //   2172: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2175: ldc_w 1305
    //   2178: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2181: getstatic 164	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   2184: invokevirtual 613	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2187: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2190: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2193: goto -1719 -> 474
    //   2196: aload 8
    //   2198: astore_1
    //   2199: aload_3
    //   2200: ldc_w 1178
    //   2203: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2206: invokevirtual 1107	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2209: ldc_w 704
    //   2212: invokevirtual 1108	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2215: ifeq -1434 -> 781
    //   2218: aload 8
    //   2220: astore_1
    //   2221: aload_3
    //   2222: ldc_w 1178
    //   2225: invokevirtual 702	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2228: checkcast 704	java/lang/Boolean
    //   2231: invokevirtual 707	java/lang/Boolean:booleanValue	()Z
    //   2234: ifeq -1453 -> 781
    //   2237: ldc_w 1200
    //   2240: astore_1
    //   2241: goto -1460 -> 781
    //   2244: iload 4
    //   2246: sipush 360
    //   2249: if_icmple +42 -> 2291
    //   2252: new 488	java/lang/StringBuilder
    //   2255: dup
    //   2256: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2259: ldc_w 1195
    //   2262: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2265: iload 4
    //   2267: invokevirtual 883	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2270: ldc_w 1307
    //   2273: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2276: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2279: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2282: ldc_w 1308
    //   2285: putstatic 167	com/kochava/android/tracker/Feature:flush_rate	I
    //   2288: goto -1088 -> 1200
    //   2291: iload 4
    //   2293: bipush 60
    //   2295: imul
    //   2296: sipush 1000
    //   2299: imul
    //   2300: putstatic 167	com/kochava/android/tracker/Feature:flush_rate	I
    //   2303: new 488	java/lang/StringBuilder
    //   2306: dup
    //   2307: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2310: ldc_w 1310
    //   2313: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2316: iload 4
    //   2318: invokevirtual 883	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2321: ldc_w 1312
    //   2324: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2327: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2330: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   2333: goto -1133 -> 1200
    //   2336: aload 13
    //   2338: ifnull +178 -> 2516
    //   2341: aload 13
    //   2343: invokevirtual 1007	java/lang/String:trim	()Ljava/lang/String;
    //   2346: invokevirtual 1198	java/lang/String:length	()I
    //   2349: ifeq +167 -> 2516
    //   2352: aload_0
    //   2353: getfield 1170	com/kochava/android/tracker/Feature:kvinitdata	Lorg/json/JSONObject;
    //   2356: ldc_w 1176
    //   2359: aload 13
    //   2361: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2364: pop
    //   2365: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2368: ldc_w 1207
    //   2371: ldc -98
    //   2373: invokeinterface 534 3 0
    //   2378: ldc -98
    //   2380: invokevirtual 540	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2383: ifeq +26 -> 2409
    //   2386: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   2389: invokeinterface 983 1 0
    //   2394: ldc_w 1207
    //   2397: aload 13
    //   2399: invokeinterface 989 3 0
    //   2404: invokeinterface 992 1 0
    //   2409: aload 9
    //   2411: ifnull +78 -> 2489
    //   2414: aload 9
    //   2416: invokevirtual 1007	java/lang/String:trim	()Ljava/lang/String;
    //   2419: invokevirtual 1198	java/lang/String:length	()I
    //   2422: ifeq +67 -> 2489
    //   2425: aload 9
    //   2427: putstatic 480	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2430: aload_0
    //   2431: getfield 1172	com/kochava/android/tracker/Feature:kvinitdataholder	Lorg/json/JSONObject;
    //   2434: ldc_w 677
    //   2437: aload 9
    //   2439: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2442: pop
    //   2443: aload_0
    //   2444: getfield 1174	com/kochava/android/tracker/Feature:kvinitorigdata	Lorg/json/JSONObject;
    //   2447: ldc_w 677
    //   2450: aload 9
    //   2452: invokevirtual 512	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2455: pop
    //   2456: goto -1088 -> 1368
    //   2459: astore_1
    //   2460: new 488	java/lang/StringBuilder
    //   2463: dup
    //   2464: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2467: ldc_w 1314
    //   2470: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2473: aload_1
    //   2474: invokevirtual 1315	org/json/JSONException:toString	()Ljava/lang/String;
    //   2477: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2480: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2483: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2486: goto -889 -> 1597
    //   2489: new 488	java/lang/StringBuilder
    //   2492: dup
    //   2493: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2496: ldc_w 1317
    //   2499: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2502: aload 13
    //   2504: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2507: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2510: putstatic 480	com/kochava/android/tracker/Feature:mAppId	Ljava/lang/String;
    //   2513: goto -1145 -> 1368
    //   2516: ldc_w 1319
    //   2519: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2522: iconst_1
    //   2523: putstatic 211	com/kochava/android/tracker/Feature:badInit	Z
    //   2526: return
    //   2527: astore_1
    //   2528: iconst_1
    //   2529: istore 4
    //   2531: new 488	java/lang/StringBuilder
    //   2534: dup
    //   2535: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2538: ldc -98
    //   2540: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2543: ldc_w 1321
    //   2546: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2549: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2552: astore_3
    //   2553: goto -908 -> 1645
    //   2556: astore 5
    //   2558: aconst_null
    //   2559: astore_1
    //   2560: new 488	java/lang/StringBuilder
    //   2563: dup
    //   2564: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2567: ldc_w 1323
    //   2570: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2573: aload 5
    //   2575: invokevirtual 1324	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2578: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2581: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2584: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2587: goto -698 -> 1889
    //   2590: astore_1
    //   2591: new 488	java/lang/StringBuilder
    //   2594: dup
    //   2595: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2598: ldc_w 1323
    //   2601: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2604: aload_1
    //   2605: invokevirtual 909	java/lang/Exception:toString	()Ljava/lang/String;
    //   2608: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2611: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2614: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2617: goto -681 -> 1936
    //   2620: ldc_w 1326
    //   2623: astore_1
    //   2624: goto -725 -> 1899
    //   2627: astore_1
    //   2628: new 488	java/lang/StringBuilder
    //   2631: dup
    //   2632: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2635: ldc_w 1328
    //   2638: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2641: aload_1
    //   2642: invokevirtual 909	java/lang/Exception:toString	()Ljava/lang/String;
    //   2645: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2648: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2651: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2654: goto -651 -> 2003
    //   2657: astore_1
    //   2658: new 488	java/lang/StringBuilder
    //   2661: dup
    //   2662: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   2665: ldc_w 1330
    //   2668: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2671: aload_1
    //   2672: invokevirtual 909	java/lang/Exception:toString	()Ljava/lang/String;
    //   2675: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2678: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2681: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   2684: goto -632 -> 2052
    //   2687: astore_1
    //   2688: getstatic 730	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   2691: ifeq -607 -> 2084
    //   2694: aload_1
    //   2695: invokevirtual 733	org/json/JSONException:printStackTrace	()V
    //   2698: goto -614 -> 2084
    //   2701: astore_1
    //   2702: goto -2177 -> 525
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2705	0	this	Feature
    //   0	2705	1	paramContext	Context
    //   0	2705	2	paramBoolean	boolean
    //   0	2705	3	paramHashMap	HashMap<String, Object>
    //   1162	1368	4	i	int
    //   595	610	5	localObject1	Object
    //   2556	18	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   562	658	6	localObject2	Object
    //   568	641	7	localObject3	Object
    //   578	1641	8	localObject4	Object
    //   565	463	9	localIterator	Iterator
    //   1037	1	9	localException	Exception
    //   1084	1367	9	localObject5	Object
    //   575	675	10	localObject6	Object
    //   581	659	11	localObject7	Object
    //   587	783	12	localObject8	Object
    //   559	1944	13	localObject9	Object
    //   584	253	14	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   940	978	1037	java/lang/Exception
    //   978	1034	1037	java/lang/Exception
    //   4	11	2111	java/lang/Exception
    //   373	396	2138	java/lang/Exception
    //   1282	1368	2459	org/json/JSONException
    //   1368	1597	2459	org/json/JSONException
    //   2341	2409	2459	org/json/JSONException
    //   2414	2456	2459	org/json/JSONException
    //   2489	2513	2459	org/json/JSONException
    //   2516	2526	2459	org/json/JSONException
    //   1627	1645	2527	android/content/pm/PackageManager$NameNotFoundException
    //   1877	1889	2556	android/content/pm/PackageManager$NameNotFoundException
    //   1867	1877	2590	java/lang/Exception
    //   1877	1889	2590	java/lang/Exception
    //   1893	1899	2590	java/lang/Exception
    //   1899	1936	2590	java/lang/Exception
    //   2560	2587	2590	java/lang/Exception
    //   1936	2003	2627	java/lang/Exception
    //   2003	2052	2657	java/lang/Exception
    //   2070	2084	2687	org/json/JSONException
    //   502	525	2701	java/lang/Exception
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
          Feature.access$5302(Feature.this, new Timer());
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
            break label671;
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
      label671:
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
          ((JSONObject)localObject1).put("kochava_device_id", Feature.access$3300());
          ((JSONObject)localObject1).put("sdk_version", "Android20160615-NOEMAIL" + Feature.versionExtension);
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
          ((JSONObject)localObject2).put("kochava_device_id", Feature.access$3300());
          ((JSONObject)localObject2).put("kochava_app_id", Feature.mAppId);
          ((JSONObject)localObject2).put("sdk_version", "Android20160615-NOEMAIL" + Feature.versionExtension);
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
    //   0: getstatic 621	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +261 -> 266
    //   8: getstatic 164	com/kochava/android/tracker/Feature:overrideAutomaticSessions	Z
    //   11: ifne +255 -> 266
    //   14: getstatic 211	com/kochava/android/tracker/Feature:badInit	Z
    //   17: ifeq +10 -> 27
    //   20: ldc_w 623
    //   23: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   26: return
    //   27: ldc_w 1553
    //   30: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   33: getstatic 203	com/kochava/android/tracker/Feature:should_flush_in_background	Z
    //   36: ifne +46 -> 82
    //   39: getstatic 627	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   42: ifnonnull +189 -> 231
    //   45: ldc_w 1555
    //   48: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   51: new 634	java/util/Timer
    //   54: dup
    //   55: invokespecial 1100	java/util/Timer:<init>	()V
    //   58: putstatic 627	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   61: getstatic 627	com/kochava/android/tracker/Feature:mTimer	Ljava/util/Timer;
    //   64: new 8	com/kochava/android/tracker/Feature$10
    //   67: dup
    //   68: invokespecial 1556	com/kochava/android/tracker/Feature$10:<init>	()V
    //   71: getstatic 167	com/kochava/android/tracker/Feature:flush_rate	I
    //   74: i2l
    //   75: getstatic 167	com/kochava/android/tracker/Feature:flush_rate	I
    //   78: i2l
    //   79: invokevirtual 1358	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   82: invokestatic 494	java/lang/System:currentTimeMillis	()J
    //   85: ldc2_w 495
    //   88: ldiv
    //   89: putstatic 194	com/kochava/android/tracker/Feature:startTime	J
    //   92: getstatic 190	com/kochava/android/tracker/Feature:doGatherLocation	Z
    //   95: ifeq +18 -> 113
    //   98: invokestatic 356	com/kochava/android/tracker/Feature:oldLocationTooStale	()Z
    //   101: ifeq +12 -> 113
    //   104: getstatic 518	com/kochava/android/tracker/Feature:appContext	Landroid/content/Context;
    //   107: invokestatic 1560	com/kochava/android/tracker/LocationDirector:getInstance	(Landroid/content/Context;)Lcom/kochava/android/tracker/LocationDirector;
    //   110: invokevirtual 1563	com/kochava/android/tracker/LocationDirector:getLocation	()V
    //   113: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   116: ifnull +49 -> 165
    //   119: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   122: ldc_w 1565
    //   125: ldc -98
    //   127: invokeinterface 534 3 0
    //   132: ldc -98
    //   134: invokevirtual 540	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 508	org/json/JSONObject
    //   145: dup
    //   146: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   149: ldc_w 1565
    //   152: ldc -98
    //   154: invokeinterface 534 3 0
    //   159: invokespecial 1566	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 1568	com/kochava/android/tracker/Feature:registerRemoveToken	(Lorg/json/JSONObject;)V
    //   165: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   168: ifnull +98 -> 266
    //   171: getstatic 402	com/kochava/android/tracker/Feature:prefs	Landroid/content/SharedPreferences;
    //   174: ldc_w 1335
    //   177: ldc -98
    //   179: invokeinterface 534 3 0
    //   184: ldc_w 1200
    //   187: invokevirtual 540	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifeq +76 -> 266
    //   193: getstatic 213	com/kochava/android/tracker/Feature:canSendSession	Z
    //   196: ifeq +64 -> 260
    //   199: ldc_w 1570
    //   202: invokestatic 642	com/kochava/android/tracker/Feature:eventSession	(Ljava/lang/String;)V
    //   205: return
    //   206: astore_1
    //   207: new 488	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 489	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 1572
    //   217: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 553	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   230: return
    //   231: ldc_w 1574
    //   234: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
    //   237: goto -155 -> 82
    //   240: astore_1
    //   241: ldc_w 1576
    //   244: invokestatic 558	com/kochava/android/util/Logging:LogError	(Ljava/lang/String;)V
    //   247: getstatic 730	com/kochava/android/tracker/Global:DEBUGERROR	Z
    //   250: ifeq -85 -> 165
    //   253: aload_1
    //   254: invokevirtual 733	org/json/JSONException:printStackTrace	()V
    //   257: goto -92 -> 165
    //   260: ldc_w 648
    //   263: invokestatic 616	com/kochava/android/util/Logging:Log	(Ljava/lang/String;)V
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
          Logging.Log("No previous os_version in watchlist, adding " + Feature.access$3900());
          Feature.prefs.edit().putString("os_version", Feature.access$3900()).apply();
        }
        for (;;)
        {
          if (!((HashMap)localObject2).keySet().isEmpty()) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("action", "update");
              localJSONObject.put("kochava_device_id", Feature.access$3300());
              localJSONObject.put("kochava_app_id", Feature.mAppId);
              localJSONObject.put("sdk_version", "Android20160615-NOEMAIL" + Feature.versionExtension);
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
          if (!Feature.prefs.getString("os_version", "").equals(Feature.access$3900()))
          {
            Logging.Log("os_version changed! Is now " + Feature.access$3900());
            ((HashMap)localObject2).put("os_version", Feature.access$3900());
            Feature.prefs.edit().putString("os_version", Feature.access$3900()).apply();
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
                Feature.access$5600();
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
            Feature.access$5700();
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
      Feature.access$4702(true);
    }
    
    public void onActivityResumed(Activity paramActivity)
    {
      Logging.Log("LifeCycleTracker - Tracking Activity Resumed");
      Feature.AppLifeCycleStatusManager.changeStatus("is_focused");
      Feature.access$4702(false);
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
        Feature.access$4702(true);
      }
    }
  }
  
  private static class TrackTask
    implements Runnable
  {
    private TrackTask() {}
    
    public void run()
    {
      Feature.access$5500();
    }
  }
}
