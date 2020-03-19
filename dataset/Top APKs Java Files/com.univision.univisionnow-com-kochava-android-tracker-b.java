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

public class b
{
  private static String A;
  private static String B;
  private static String C;
  private static int D = 7;
  private static int E = 60;
  private static boolean G = false;
  private static boolean H = false;
  private static boolean I = false;
  private static boolean J = true;
  private static boolean K = false;
  private static long L = 0L;
  private static long M = 0L;
  private static String N = "";
  private static Map<String, String> O;
  private static List<b> P = new ArrayList();
  private static a Q;
  private static Timer S;
  private static boolean V = true;
  private static boolean W = false;
  private static boolean X = false;
  private static long Z = 0L;
  protected static String a;
  private static boolean aa = false;
  private static boolean ab = true;
  private static boolean ac = false;
  private static boolean ad = false;
  private static SharedPreferences ag;
  private static SharedPreferences ah;
  private static final ExecutorService ai = Executors.newFixedThreadPool(1);
  private static Handler aj;
  private static final ScheduledExecutorService ak = Executors.newSingleThreadScheduledExecutor();
  private static final Uri al = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static HashMap<String, Boolean> am = new HashMap() {};
  private static JSONArray an = new JSONArray();
  private static Runnable ap = new Runnable()
  {
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: bipush 60
      //   2: istore_2
      //   3: iload_2
      //   4: istore_1
      //   5: invokestatic 26	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   8: ifnull +17 -> 25
      //   11: iload_2
      //   12: istore_1
      //   13: invokestatic 26	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   16: invokevirtual 31	java/lang/String:trim	()Ljava/lang/String;
      //   19: invokevirtual 35	java/lang/String:isEmpty	()Z
      //   22: ifeq +18 -> 40
      //   25: iload_2
      //   26: istore_1
      //   27: ldc 37
      //   29: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   32: iload_2
      //   33: istore_1
      //   34: ldc 45
      //   36: invokestatic 49	com/kochava/android/tracker/b:d	(Ljava/lang/String;)Ljava/lang/String;
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
      //   59: invokestatic 26	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   62: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   65: ldc 62
      //   67: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   70: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   73: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   76: iload_2
      //   77: istore_1
      //   78: new 67	java/net/URL
      //   81: dup
      //   82: new 51	java/lang/StringBuilder
      //   85: dup
      //   86: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   89: ldc 60
      //   91: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   94: invokestatic 26	com/kochava/android/tracker/b:e	()Ljava/lang/String;
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
      //   125: invokestatic 80	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   128: ldc 82
      //   130: ldc 84
      //   132: invokeinterface 90 3 0
      //   137: invokevirtual 94	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   140: iload_2
      //   141: istore_1
      //   142: aload 7
      //   144: ldc 96
      //   146: ldc 98
      //   148: invokevirtual 94	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   151: iload_2
      //   152: istore_1
      //   153: aload 7
      //   155: ldc 100
      //   157: invokevirtual 103	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   160: iload_2
      //   161: istore_1
      //   162: aload 7
      //   164: sipush 30000
      //   167: invokevirtual 107	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   170: iload_2
      //   171: istore_1
      //   172: aload 7
      //   174: sipush 30000
      //   177: invokevirtual 110	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   180: iload_2
      //   181: istore_1
      //   182: aload 7
      //   184: iconst_1
      //   185: invokevirtual 114	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   188: iload_2
      //   189: istore_1
      //   190: aload 7
      //   192: iconst_1
      //   193: invokevirtual 117	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   196: iload_2
      //   197: istore_1
      //   198: aload 7
      //   200: invokevirtual 120	javax/net/ssl/HttpsURLConnection:connect	()V
      //   203: iload_2
      //   204: istore_1
      //   205: new 122	org/json/JSONObject
      //   208: dup
      //   209: invokespecial 123	org/json/JSONObject:<init>	()V
      //   212: astore 8
      //   214: iload_2
      //   215: istore_1
      //   216: aload 8
      //   218: ldc 125
      //   220: ldc 127
      //   222: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   225: pop
      //   226: iload_2
      //   227: istore_1
      //   228: aload 8
      //   230: ldc -123
      //   232: invokestatic 136	com/kochava/android/tracker/b:q	()Ljava/lang/String;
      //   235: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   238: pop
      //   239: iload_2
      //   240: istore_1
      //   241: aload 8
      //   243: ldc -118
      //   245: invokestatic 141	com/kochava/android/tracker/b:r	()Ljava/lang/String;
      //   248: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   251: pop
      //   252: iload_2
      //   253: istore_1
      //   254: aload 8
      //   256: ldc -113
      //   258: new 51	java/lang/StringBuilder
      //   261: dup
      //   262: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   265: ldc -111
      //   267: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   270: getstatic 148	com/kochava/android/tracker/b:a	Ljava/lang/String;
      //   273: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   276: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   279: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   282: pop
      //   283: iload_2
      //   284: istore_1
      //   285: aload 8
      //   287: ldc -106
      //   289: ldc -104
      //   291: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   294: pop
      //   295: iload_2
      //   296: istore_1
      //   297: aload 8
      //   299: invokevirtual 153	org/json/JSONObject:toString	()Ljava/lang/String;
      //   302: astore 8
      //   304: iload_2
      //   305: istore_1
      //   306: new 51	java/lang/StringBuilder
      //   309: dup
      //   310: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   313: ldc -101
      //   315: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   318: aload 8
      //   320: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   323: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   326: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   329: iload_2
      //   330: istore_1
      //   331: ldc -99
      //   333: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   336: iload_2
      //   337: istore_1
      //   338: new 159	java/io/OutputStreamWriter
      //   341: dup
      //   342: aload 7
      //   344: invokevirtual 163	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   347: invokespecial 166	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   350: astore 9
      //   352: iload_2
      //   353: istore_1
      //   354: aload 9
      //   356: aload 8
      //   358: invokevirtual 169	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   361: iload_2
      //   362: istore_1
      //   363: aload 9
      //   365: invokevirtual 172	java/io/OutputStreamWriter:close	()V
      //   368: iload_2
      //   369: istore 4
      //   371: iload_2
      //   372: istore 6
      //   374: iload_2
      //   375: istore_1
      //   376: ldc -82
      //   378: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   381: iload_2
      //   382: istore 4
      //   384: iload_2
      //   385: istore 6
      //   387: iload_2
      //   388: istore_1
      //   389: new 176	java/lang/StringBuffer
      //   392: dup
      //   393: ldc 84
      //   395: invokespecial 177	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   398: astore 8
      //   400: iload_2
      //   401: istore 4
      //   403: iload_2
      //   404: istore 6
      //   406: iload_2
      //   407: istore_1
      //   408: new 179	java/io/BufferedReader
      //   411: dup
      //   412: new 181	java/io/InputStreamReader
      //   415: dup
      //   416: aload 7
      //   418: invokevirtual 185	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   421: invokespecial 188	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   424: invokespecial 191	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   427: astore 7
      //   429: iload_2
      //   430: istore 4
      //   432: iload_2
      //   433: istore 6
      //   435: iload_2
      //   436: istore_1
      //   437: aload 7
      //   439: invokevirtual 194	java/io/BufferedReader:readLine	()Ljava/lang/String;
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
      //   461: invokevirtual 197	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   464: pop
      //   465: goto -36 -> 429
      //   468: astore 7
      //   470: iload 4
      //   472: istore_1
      //   473: aload 7
      //   475: invokevirtual 201	java/lang/Object:getClass	()Ljava/lang/Class;
      //   478: ldc -53
      //   480: invokevirtual 207	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   483: ifeq +633 -> 1116
      //   486: iload 4
      //   488: istore_1
      //   489: new 51	java/lang/StringBuilder
      //   492: dup
      //   493: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   496: ldc -47
      //   498: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   501: aload 7
      //   503: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   506: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   509: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   512: iload 4
      //   514: istore_1
      //   515: aload 7
      //   517: invokestatic 218	com/kochava/android/tracker/b:a	(Ljava/lang/Exception;)V
      //   520: return
      //   521: iload_2
      //   522: istore 4
      //   524: iload_2
      //   525: istore 6
      //   527: iload_2
      //   528: istore_1
      //   529: aload 8
      //   531: invokevirtual 219	java/lang/StringBuffer:toString	()Ljava/lang/String;
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
      //   551: ldc -35
      //   553: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   556: aload 7
      //   558: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   561: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   564: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   567: aconst_null
      //   568: astore 8
      //   570: iload_2
      //   571: istore 4
      //   573: iload_2
      //   574: istore 6
      //   576: iload_2
      //   577: istore_1
      //   578: new 122	org/json/JSONObject
      //   581: dup
      //   582: aload 7
      //   584: invokespecial 222	org/json/JSONObject:<init>	(Ljava/lang/String;)V
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
      //   616: ldc -32
      //   618: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   621: aload 8
      //   623: invokevirtual 153	org/json/JSONObject:toString	()Ljava/lang/String;
      //   626: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   629: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   632: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   635: aconst_null
      //   636: astore 7
      //   638: iload_2
      //   639: istore 4
      //   641: iload_2
      //   642: istore 6
      //   644: iload_2
      //   645: istore_1
      //   646: aload 8
      //   648: ldc -30
      //   650: invokevirtual 230	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
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
      //   674: ldc -24
      //   676: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   679: aload 8
      //   681: invokevirtual 153	org/json/JSONObject:toString	()Ljava/lang/String;
      //   684: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   687: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   690: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   693: aload 8
      //   695: astore 7
      //   697: iload_2
      //   698: istore 5
      //   700: aload 7
      //   702: ifnull +197 -> 899
      //   705: ldc 84
      //   707: astore 8
      //   709: iload_2
      //   710: istore 4
      //   712: iload_2
      //   713: istore 6
      //   715: iload_2
      //   716: istore_1
      //   717: aload 7
      //   719: ldc -22
      //   721: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
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
      //   740: ldc -18
      //   742: invokevirtual 242	org/json/JSONObject:getInt	(Ljava/lang/String;)I
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
      //   763: invokestatic 245	com/kochava/android/tracker/b:s	()Landroid/content/SharedPreferences;
      //   766: invokeinterface 249 1 0
      //   771: ldc -5
      //   773: aload 8
      //   775: invokeinterface 257 3 0
      //   780: invokeinterface 260 1 0
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
      //   798: invokestatic 264	com/kochava/android/tracker/b:t	()Landroid/os/Handler;
      //   801: ifnull +98 -> 899
      //   804: iload_3
      //   805: istore 4
      //   807: iload_3
      //   808: istore 6
      //   810: iload_3
      //   811: istore_1
      //   812: iload_3
      //   813: istore_2
      //   814: invokestatic 270	android/os/Message:obtain	()Landroid/os/Message;
      //   817: astore 7
      //   819: iload_3
      //   820: istore 4
      //   822: iload_3
      //   823: istore 6
      //   825: iload_3
      //   826: istore_1
      //   827: iload_3
      //   828: istore_2
      //   829: new 272	android/os/Bundle
      //   832: dup
      //   833: invokespecial 273	android/os/Bundle:<init>	()V
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
      //   850: ldc -5
      //   852: aload 8
      //   854: invokevirtual 274	java/lang/String:toString	()Ljava/lang/String;
      //   857: invokevirtual 276	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
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
      //   874: invokevirtual 280	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   877: iload_3
      //   878: istore 4
      //   880: iload_3
      //   881: istore 6
      //   883: iload_3
      //   884: istore_1
      //   885: iload_3
      //   886: istore_2
      //   887: invokestatic 264	com/kochava/android/tracker/b:t	()Landroid/os/Handler;
      //   890: aload 7
      //   892: invokevirtual 286	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   895: pop
      //   896: iload_3
      //   897: istore 5
      //   899: iload 5
      //   901: ifle -381 -> 520
      //   904: iload 5
      //   906: invokestatic 288	com/kochava/android/tracker/b:a	(I)V
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
      //   927: ldc_w 290
      //   930: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   933: aload 7
      //   935: invokevirtual 291	org/json/JSONException:toString	()Ljava/lang/String;
      //   938: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   941: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   944: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   947: goto -354 -> 593
      //   950: astore 7
      //   952: iload 6
      //   954: istore_1
      //   955: new 51	java/lang/StringBuilder
      //   958: dup
      //   959: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   962: ldc_w 293
      //   965: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   968: aload 7
      //   970: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   973: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   976: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   979: return
      //   980: astore 7
      //   982: aload 7
      //   984: invokevirtual 201	java/lang/Object:getClass	()Ljava/lang/Class;
      //   987: ldc -53
      //   989: invokevirtual 207	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   992: ifeq +152 -> 1144
      //   995: new 51	java/lang/StringBuilder
      //   998: dup
      //   999: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   1002: ldc -47
      //   1004: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1007: aload 7
      //   1009: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1012: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1015: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1018: aload 7
      //   1020: invokestatic 218	com/kochava/android/tracker/b:a	(Ljava/lang/Exception;)V
      //   1023: return
      //   1024: astore 8
      //   1026: iload_2
      //   1027: istore 4
      //   1029: iload_2
      //   1030: istore 6
      //   1032: iload_2
      //   1033: istore_1
      //   1034: ldc_w 295
      //   1037: invokestatic 43	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1040: goto -343 -> 697
      //   1043: astore 7
      //   1045: new 51	java/lang/StringBuilder
      //   1048: dup
      //   1049: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   1052: ldc_w 297
      //   1055: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1058: aload 7
      //   1060: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1063: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1066: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
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
      //   1085: ldc_w 299
      //   1088: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1091: goto -361 -> 730
      //   1094: astore 7
      //   1096: iload_2
      //   1097: istore 4
      //   1099: iload_2
      //   1100: istore 6
      //   1102: iload_2
      //   1103: istore_1
      //   1104: ldc_w 301
      //   1107: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1110: iload_2
      //   1111: istore 5
      //   1113: goto -214 -> 899
      //   1116: iload 4
      //   1118: istore_1
      //   1119: new 51	java/lang/StringBuilder
      //   1122: dup
      //   1123: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   1126: ldc_w 303
      //   1129: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1132: aload 7
      //   1134: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1137: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1140: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1143: return
      //   1144: new 51	java/lang/StringBuilder
      //   1147: dup
      //   1148: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   1151: ldc_w 303
      //   1154: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1157: aload 7
      //   1159: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1162: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1165: invokestatic 215	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1168: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1169	0	this	21
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
  protected static boolean b;
  protected static Context c;
  protected static Handler g = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (b.J()) {
        com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Cannot send location udpate");
      }
      do
      {
        for (;;)
        {
          return;
          com.kochava.android.a.b.a("Location Handler got message, queueing location update.");
          try
          {
            paramAnonymousMessage = new JSONObject();
            JSONObject localJSONObject1 = new JSONObject();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("lat", b.d().getString("kochava_lat", ""));
            localJSONObject2.put("lon", b.d().getString("kochava_lon", ""));
            localJSONObject2.put("acc", b.d().getString("kochava_accuracy", ""));
            localJSONObject1.put("location", localJSONObject2);
            paramAnonymousMessage.put("action", "update");
            paramAnonymousMessage.put("kochava_device_id", b.r());
            paramAnonymousMessage.put("kochava_app_id", b.q());
            paramAnonymousMessage.put("sdk_version", "Android20160615" + b.a);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            com.kochava.android.a.b.a("Location update: " + paramAnonymousMessage);
            int i = b.x().a(paramAnonymousMessage, false, true);
            b.a(System.currentTimeMillis());
            long l = b.d().getLong("kochava_loc_timestamp", 0L);
            b.d().edit().putLong("kochava_old_loc_timestamp", l).apply();
            if (i >= 50)
            {
              b.c();
              return;
            }
          }
          catch (JSONException paramAnonymousMessage) {}
        }
      } while (!c.b);
      paramAnonymousMessage.printStackTrace();
    }
  };
  private static Map<String, String> h;
  private static JSONObject i;
  private static String j = "";
  private static int k;
  private static boolean l;
  private static boolean m;
  private static final HashMap<Integer, Integer> n;
  private static String u;
  private static String w;
  private boolean F = false;
  private boolean R = true;
  private Timer T;
  private Timer U;
  private Timer Y;
  private JSONObject ae;
  private JSONObject af;
  private Runnable ao = new Runnable()
  {
    /* Error */
    @SuppressLint({"NewApi"})
    public void run()
    {
      // Byte code:
      //   0: ldc 34
      //   2: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   5: invokestatic 44	android/os/Looper:prepare	()V
      //   8: lconst_0
      //   9: lstore 6
      //   11: iconst_0
      //   12: istore_3
      //   13: iconst_0
      //   14: istore_2
      //   15: invokestatic 50	java/lang/System:currentTimeMillis	()J
      //   18: lstore 8
      //   20: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   23: ldc 56
      //   25: invokestatic 50	java/lang/System:currentTimeMillis	()J
      //   28: invokeinterface 62 4 0
      //   33: lstore 10
      //   35: lload 8
      //   37: lload 10
      //   39: lsub
      //   40: lstore 6
      //   42: iload_3
      //   43: istore_1
      //   44: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   47: ldc 64
      //   49: invokeinterface 68 2 0
      //   54: ifeq +65 -> 119
      //   57: iload_3
      //   58: istore_1
      //   59: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   62: ldc 64
      //   64: ldc 70
      //   66: invokeinterface 74 3 0
      //   71: aload_0
      //   72: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   75: invokestatic 78	com/kochava/android/tracker/b:b	(Lcom/kochava/android/tracker/b;)Ljava/lang/String;
      //   78: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   81: ifne +38 -> 119
      //   84: new 86	java/lang/StringBuilder
      //   87: dup
      //   88: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   91: ldc 89
      //   93: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: aload_0
      //   97: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   100: invokestatic 78	com/kochava/android/tracker/b:b	(Lcom/kochava/android/tracker/b;)Ljava/lang/String;
      //   103: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: ldc 95
      //   108: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   114: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   117: iconst_1
      //   118: istore_1
      //   119: iload_1
      //   120: ifne +29 -> 149
      //   123: lload 6
      //   125: lconst_0
      //   126: lcmp
      //   127: ifle +22 -> 149
      //   130: lload 6
      //   132: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   135: ldc 101
      //   137: ldc2_w 102
      //   140: invokeinterface 62 4 0
      //   145: lcmp
      //   146: ifle +3888 -> 4034
      //   149: aconst_null
      //   150: astore 13
      //   152: iload_2
      //   153: istore_1
      //   154: new 86	java/lang/StringBuilder
      //   157: dup
      //   158: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   161: ldc 105
      //   163: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   166: aload_0
      //   167: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   170: getfield 109	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
      //   173: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
      //   176: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   179: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   182: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   185: invokestatic 114	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   188: ifnull +15 -> 203
      //   191: invokestatic 114	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   194: invokevirtual 117	java/lang/String:trim	()Ljava/lang/String;
      //   197: invokevirtual 121	java/lang/String:isEmpty	()Z
      //   200: ifeq +14 -> 214
      //   203: ldc 123
      //   205: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   208: ldc 125
      //   210: invokestatic 128	com/kochava/android/tracker/b:d	(Ljava/lang/String;)Ljava/lang/String;
      //   213: pop
      //   214: new 86	java/lang/StringBuilder
      //   217: dup
      //   218: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   221: ldc -126
      //   223: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   226: ldc -124
      //   228: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   231: invokestatic 114	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   234: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   237: ldc -122
      //   239: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   242: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   245: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   248: new 136	java/net/URL
      //   251: dup
      //   252: new 86	java/lang/StringBuilder
      //   255: dup
      //   256: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   259: ldc -124
      //   261: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   264: invokestatic 114	com/kochava/android/tracker/b:e	()Ljava/lang/String;
      //   267: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   270: ldc -122
      //   272: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   275: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   278: invokespecial 138	java/net/URL:<init>	(Ljava/lang/String;)V
      //   281: invokevirtual 142	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   284: checkcast 144	javax/net/ssl/HttpsURLConnection
      //   287: astore 14
      //   289: aload 14
      //   291: ldc -110
      //   293: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   296: ldc -108
      //   298: ldc 70
      //   300: invokeinterface 74 3 0
      //   305: invokevirtual 152	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   308: aload 14
      //   310: ldc -102
      //   312: ldc -100
      //   314: invokevirtual 152	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   317: aload 14
      //   319: ldc -98
      //   321: invokevirtual 161	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   324: aload 14
      //   326: sipush 30000
      //   329: invokevirtual 165	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   332: aload 14
      //   334: sipush 30000
      //   337: invokevirtual 168	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   340: aload 14
      //   342: iconst_1
      //   343: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   346: aload 14
      //   348: iconst_1
      //   349: invokevirtual 175	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   352: aload 14
      //   354: invokevirtual 178	javax/net/ssl/HttpsURLConnection:connect	()V
      //   357: aload_0
      //   358: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   361: getfield 109	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
      //   364: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
      //   367: astore 15
      //   369: new 86	java/lang/StringBuilder
      //   372: dup
      //   373: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   376: ldc -76
      //   378: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   381: aload 15
      //   383: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   386: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   389: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   392: ldc -74
      //   394: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   397: new 184	java/io/OutputStreamWriter
      //   400: dup
      //   401: aload 14
      //   403: invokevirtual 188	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   406: invokespecial 191	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   409: astore 16
      //   411: aload 16
      //   413: aload 15
      //   415: invokevirtual 194	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   418: aload 16
      //   420: invokevirtual 197	java/io/OutputStreamWriter:close	()V
      //   423: ldc -57
      //   425: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   428: new 201	java/lang/StringBuffer
      //   431: dup
      //   432: ldc 70
      //   434: invokespecial 202	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   437: astore 15
      //   439: new 204	java/io/BufferedReader
      //   442: dup
      //   443: new 206	java/io/InputStreamReader
      //   446: dup
      //   447: aload 14
      //   449: invokevirtual 210	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   452: invokespecial 213	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   455: invokespecial 216	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   458: astore 14
      //   460: aload 14
      //   462: invokevirtual 219	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   465: astore 16
      //   467: aload 16
      //   469: ifnull +89 -> 558
      //   472: aload 15
      //   474: aload 16
      //   476: invokevirtual 222	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   479: pop
      //   480: goto -20 -> 460
      //   483: astore 13
      //   485: ldc -32
      //   487: aload 13
      //   489: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   492: invokevirtual 234	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   495: ifeq +3514 -> 4009
      //   498: new 86	java/lang/StringBuilder
      //   501: dup
      //   502: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   505: ldc -20
      //   507: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   510: aload 13
      //   512: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   515: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   518: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   521: aload 13
      //   523: invokestatic 244	com/kochava/android/tracker/b:a	(Ljava/lang/Exception;)V
      //   526: return
      //   527: astore 13
      //   529: new 86	java/lang/StringBuilder
      //   532: dup
      //   533: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   536: ldc -10
      //   538: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   541: aload 13
      //   543: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
      //   546: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   549: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   552: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   555: goto -513 -> 42
      //   558: aload 15
      //   560: invokevirtual 248	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   563: astore 14
      //   565: new 86	java/lang/StringBuilder
      //   568: dup
      //   569: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   572: ldc -6
      //   574: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   577: aload 14
      //   579: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   582: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   585: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   588: new 111	org/json/JSONObject
      //   591: dup
      //   592: aload 14
      //   594: invokespecial 251	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   597: astore 14
      //   599: aload 14
      //   601: astore 13
      //   603: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   606: ldc -3
      //   608: ldc 70
      //   610: invokeinterface 74 3 0
      //   615: ldc -1
      //   617: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   620: istore 12
      //   622: iload_1
      //   623: istore_2
      //   624: iload 12
      //   626: ifne +134 -> 760
      //   629: iload_1
      //   630: istore_2
      //   631: aload 13
      //   633: ifnull +127 -> 760
      //   636: aload 13
      //   638: ldc_w 257
      //   641: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   644: astore 14
      //   646: iconst_0
      //   647: istore 5
      //   649: iconst_0
      //   650: istore 4
      //   652: iload_1
      //   653: istore_2
      //   654: iload 5
      //   656: istore_3
      //   657: iload 4
      //   659: aload 14
      //   661: invokevirtual 267	org/json/JSONArray:length	()I
      //   664: if_icmpge +90 -> 754
      //   667: ldc_w 269
      //   670: aload 14
      //   672: iload 4
      //   674: invokevirtual 272	org/json/JSONArray:getString	(I)Ljava/lang/String;
      //   677: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   680: ifeq +1042 -> 1722
      //   683: iconst_1
      //   684: istore_3
      //   685: iload_1
      //   686: istore_2
      //   687: iload_1
      //   688: iconst_4
      //   689: if_icmpge +7 -> 696
      //   692: iload_1
      //   693: iconst_1
      //   694: iadd
      //   695: istore_2
      //   696: new 86	java/lang/StringBuilder
      //   699: dup
      //   700: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   703: ldc_w 274
      //   706: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   709: invokestatic 278	com/kochava/android/tracker/b:f	()Ljava/util/HashMap;
      //   712: iload_2
      //   713: invokestatic 284	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   716: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   719: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   722: ldc_w 292
      //   725: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   728: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   731: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   734: invokestatic 278	com/kochava/android/tracker/b:f	()Ljava/util/HashMap;
      //   737: iload_2
      //   738: invokestatic 284	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   741: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   744: checkcast 280	java/lang/Integer
      //   747: invokevirtual 295	java/lang/Integer:intValue	()I
      //   750: i2l
      //   751: invokestatic 301	java/lang/Thread:sleep	(J)V
      //   754: iload_3
      //   755: ifne +5 -> 760
      //   758: iconst_0
      //   759: istore_2
      //   760: iload_2
      //   761: ifne +3531 -> 4292
      //   764: aload 13
      //   766: ifnull +2876 -> 3642
      //   769: new 86	java/lang/StringBuilder
      //   772: dup
      //   773: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   776: ldc_w 303
      //   779: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   782: aload 13
      //   784: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
      //   787: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   790: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   793: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   796: aconst_null
      //   797: astore 14
      //   799: aload 13
      //   801: ldc_w 305
      //   804: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   807: astore 15
      //   809: aload 15
      //   811: astore 14
      //   813: new 86	java/lang/StringBuilder
      //   816: dup
      //   817: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   820: ldc_w 311
      //   823: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   826: aload 15
      //   828: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
      //   831: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   834: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   837: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   840: aload 15
      //   842: astore 14
      //   844: aload 14
      //   846: ifnull +748 -> 1594
      //   849: aload 14
      //   851: ldc_w 313
      //   854: invokevirtual 315	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   857: astore 15
      //   859: new 86	java/lang/StringBuilder
      //   862: dup
      //   863: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   866: ldc_w 317
      //   869: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   872: aload 15
      //   874: invokevirtual 318	java/lang/String:toString	()Ljava/lang/String;
      //   877: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   880: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   883: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   886: aload 15
      //   888: invokestatic 320	com/kochava/android/tracker/b:e	(Ljava/lang/String;)Ljava/lang/String;
      //   891: pop
      //   892: aload 14
      //   894: ldc_w 322
      //   897: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   900: ldc_w 327
      //   903: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   906: ifeq +8 -> 914
      //   909: iconst_0
      //   910: invokestatic 332	com/kochava/android/tracker/b:c	(Z)Z
      //   913: pop
      //   914: aload 14
      //   916: ldc_w 334
      //   919: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   922: checkcast 80	java/lang/String
      //   925: invokevirtual 337	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   928: astore 15
      //   930: new 86	java/lang/StringBuilder
      //   933: dup
      //   934: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   937: ldc_w 339
      //   940: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   943: aload 15
      //   945: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   948: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   951: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   954: aload_0
      //   955: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   958: aload 15
      //   960: invokestatic 342	com/kochava/android/tracker/b:c	(Lcom/kochava/android/tracker/b;Ljava/lang/String;)V
      //   963: aload 14
      //   965: ldc_w 344
      //   968: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   971: ldc -1
      //   973: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   976: ifeq +46 -> 1022
      //   979: ldc_w 346
      //   982: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   985: getstatic 349	com/kochava/android/tracker/b:c	Landroid/content/Context;
      //   988: ldc_w 351
      //   991: iconst_0
      //   992: invokevirtual 357	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   995: invokestatic 360	com/kochava/android/tracker/b:a	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   998: pop
      //   999: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   1002: invokeinterface 364 1 0
      //   1007: ldc -3
      //   1009: ldc_w 366
      //   1012: invokeinterface 372 3 0
      //   1017: invokeinterface 375 1 0
      //   1022: aload 14
      //   1024: ldc_w 377
      //   1027: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1030: checkcast 280	java/lang/Integer
      //   1033: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1036: invokestatic 380	com/kochava/android/tracker/b:b	(I)I
      //   1039: pop
      //   1040: invokestatic 383	com/kochava/android/tracker/b:g	()I
      //   1043: ifge +777 -> 1820
      //   1046: new 86	java/lang/StringBuilder
      //   1049: dup
      //   1050: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1053: ldc_w 385
      //   1056: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1059: invokestatic 383	com/kochava/android/tracker/b:g	()I
      //   1062: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1065: ldc_w 390
      //   1068: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1071: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1074: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1077: iconst_0
      //   1078: invokestatic 380	com/kochava/android/tracker/b:b	(I)I
      //   1081: pop
      //   1082: invokestatic 393	com/kochava/android/tracker/b:h	()Z
      //   1085: ifne +89 -> 1174
      //   1088: aload 14
      //   1090: ldc_w 395
      //   1093: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1096: ifnull +78 -> 1174
      //   1099: aload 14
      //   1101: ldc_w 395
      //   1104: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1107: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1110: ldc_w 280
      //   1113: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1116: ifeq +58 -> 1174
      //   1119: aload 14
      //   1121: ldc_w 395
      //   1124: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1127: checkcast 280	java/lang/Integer
      //   1130: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1133: istore_1
      //   1134: iload_1
      //   1135: bipush 60
      //   1137: if_icmpge +759 -> 1896
      //   1140: new 86	java/lang/StringBuilder
      //   1143: dup
      //   1144: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1147: ldc_w 397
      //   1150: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1153: iload_1
      //   1154: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1157: ldc_w 399
      //   1160: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1163: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1166: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1169: iconst_1
      //   1170: invokestatic 401	com/kochava/android/tracker/b:d	(Z)Z
      //   1173: pop
      //   1174: aload 14
      //   1176: ldc 101
      //   1178: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1181: ifnull +92 -> 1273
      //   1184: aload 14
      //   1186: ldc 101
      //   1188: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1191: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1194: ldc_w 280
      //   1197: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1200: ifeq +73 -> 1273
      //   1203: aload 14
      //   1205: ldc 101
      //   1207: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1210: checkcast 280	java/lang/Integer
      //   1213: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1216: istore_1
      //   1217: iload_1
      //   1218: ifge +765 -> 1983
      //   1221: new 86	java/lang/StringBuilder
      //   1224: dup
      //   1225: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1228: ldc_w 403
      //   1231: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1234: iload_1
      //   1235: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1238: ldc_w 405
      //   1241: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1244: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1247: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1250: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   1253: invokeinterface 364 1 0
      //   1258: ldc 101
      //   1260: ldc2_w 102
      //   1263: invokeinterface 409 4 0
      //   1268: invokeinterface 375 1 0
      //   1273: aload 14
      //   1275: ldc_w 411
      //   1278: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1281: ifnull +77 -> 1358
      //   1284: aload 14
      //   1286: ldc_w 411
      //   1289: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1292: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1295: ldc_w 280
      //   1298: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1301: ifeq +57 -> 1358
      //   1304: aload 14
      //   1306: ldc_w 411
      //   1309: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1312: checkcast 280	java/lang/Integer
      //   1315: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1318: istore_1
      //   1319: iload_1
      //   1320: iconst_1
      //   1321: if_icmpge +782 -> 2103
      //   1324: new 86	java/lang/StringBuilder
      //   1327: dup
      //   1328: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1331: ldc_w 413
      //   1334: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1337: iload_1
      //   1338: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1341: ldc_w 415
      //   1344: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1347: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1350: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1353: iconst_1
      //   1354: invokestatic 417	com/kochava/android/tracker/b:d	(I)I
      //   1357: pop
      //   1358: aload 14
      //   1360: ldc_w 419
      //   1363: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1366: checkcast 280	java/lang/Integer
      //   1369: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1372: istore_2
      //   1373: iload_2
      //   1374: bipush 10
      //   1376: if_icmpge +808 -> 2184
      //   1379: new 86	java/lang/StringBuilder
      //   1382: dup
      //   1383: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1386: ldc_w 421
      //   1389: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1392: iload_2
      //   1393: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1396: ldc_w 423
      //   1399: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1402: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1405: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1408: bipush 10
      //   1410: istore_1
      //   1411: new 86	java/lang/StringBuilder
      //   1414: dup
      //   1415: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1418: ldc_w 425
      //   1421: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1424: iload_1
      //   1425: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1428: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1431: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1434: iload_1
      //   1435: putstatic 430	com/kochava/android/tracker/d:a	I
      //   1438: aload 14
      //   1440: ldc_w 432
      //   1443: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1446: checkcast 280	java/lang/Integer
      //   1449: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1452: istore_2
      //   1453: iload_2
      //   1454: iconst_3
      //   1455: if_icmpge +774 -> 2229
      //   1458: new 86	java/lang/StringBuilder
      //   1461: dup
      //   1462: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1465: ldc_w 434
      //   1468: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1471: iload_2
      //   1472: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1475: ldc_w 436
      //   1478: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1481: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1484: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1487: iconst_3
      //   1488: istore_1
      //   1489: new 86	java/lang/StringBuilder
      //   1492: dup
      //   1493: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1496: ldc_w 438
      //   1499: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1502: iload_1
      //   1503: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1506: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1509: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1512: iload_1
      //   1513: putstatic 440	com/kochava/android/tracker/d:c	I
      //   1516: aload 14
      //   1518: ldc_w 442
      //   1521: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1524: checkcast 280	java/lang/Integer
      //   1527: invokevirtual 295	java/lang/Integer:intValue	()I
      //   1530: istore_2
      //   1531: iload_2
      //   1532: iconst_1
      //   1533: if_icmpge +739 -> 2272
      //   1536: new 86	java/lang/StringBuilder
      //   1539: dup
      //   1540: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1543: ldc_w 444
      //   1546: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1549: iload_2
      //   1550: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1553: ldc_w 446
      //   1556: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1559: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1562: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1565: iconst_1
      //   1566: istore_1
      //   1567: new 86	java/lang/StringBuilder
      //   1570: dup
      //   1571: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1574: ldc_w 448
      //   1577: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1580: iload_1
      //   1581: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1584: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1587: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1590: iload_1
      //   1591: putstatic 450	com/kochava/android/tracker/d:b	I
      //   1594: aload 13
      //   1596: ldc_w 452
      //   1599: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1602: astore 15
      //   1604: new 86	java/lang/StringBuilder
      //   1607: dup
      //   1608: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1611: ldc_w 454
      //   1614: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1617: aload 15
      //   1619: invokevirtual 455	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1622: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1625: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1628: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1631: iconst_0
      //   1632: istore_1
      //   1633: iload_1
      //   1634: aload 15
      //   1636: invokevirtual 267	org/json/JSONArray:length	()I
      //   1639: if_icmpge +741 -> 2380
      //   1642: aload 15
      //   1644: iload_1
      //   1645: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1648: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   1651: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1654: ldc_w 464
      //   1657: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1660: ifeq +657 -> 2317
      //   1663: ldc_w 466
      //   1666: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1669: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   1672: ldc_w 464
      //   1675: iconst_0
      //   1676: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1679: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1682: pop
      //   1683: iload_1
      //   1684: iconst_1
      //   1685: iadd
      //   1686: istore_1
      //   1687: goto -54 -> 1633
      //   1690: astore 14
      //   1692: new 86	java/lang/StringBuilder
      //   1695: dup
      //   1696: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1699: ldc_w 480
      //   1702: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1705: aload 14
      //   1707: invokevirtual 481	org/json/JSONException:toString	()Ljava/lang/String;
      //   1710: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1713: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1716: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1719: goto -1116 -> 603
      //   1722: iload 4
      //   1724: iconst_1
      //   1725: iadd
      //   1726: istore 4
      //   1728: goto -1076 -> 652
      //   1731: astore 14
      //   1733: iconst_0
      //   1734: istore_2
      //   1735: goto -975 -> 760
      //   1738: astore 15
      //   1740: ldc_w 483
      //   1743: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1746: goto -902 -> 844
      //   1749: astore 13
      //   1751: aload 13
      //   1753: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1756: ldc -32
      //   1758: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1761: ifeq +2194 -> 3955
      //   1764: new 86	java/lang/StringBuilder
      //   1767: dup
      //   1768: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1771: ldc -20
      //   1773: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1776: aload 13
      //   1778: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1781: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1784: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1787: aload 13
      //   1789: invokestatic 244	com/kochava/android/tracker/b:a	(Ljava/lang/Exception;)V
      //   1792: return
      //   1793: astore 13
      //   1795: goto -1310 -> 485
      //   1798: astore 15
      //   1800: ldc_w 485
      //   1803: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1806: goto -914 -> 892
      //   1809: astore 15
      //   1811: ldc_w 483
      //   1814: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   1817: goto -903 -> 914
      //   1820: invokestatic 383	com/kochava/android/tracker/b:g	()I
      //   1823: bipush 120
      //   1825: if_icmple +43 -> 1868
      //   1828: new 86	java/lang/StringBuilder
      //   1831: dup
      //   1832: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1835: ldc_w 487
      //   1838: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1841: invokestatic 383	com/kochava/android/tracker/b:g	()I
      //   1844: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1847: ldc_w 489
      //   1850: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1853: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1856: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1859: bipush 120
      //   1861: invokestatic 380	com/kochava/android/tracker/b:b	(I)I
      //   1864: pop
      //   1865: goto -783 -> 1082
      //   1868: new 86	java/lang/StringBuilder
      //   1871: dup
      //   1872: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1875: ldc_w 491
      //   1878: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1881: invokestatic 383	com/kochava/android/tracker/b:g	()I
      //   1884: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1887: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1890: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1893: goto -811 -> 1082
      //   1896: iload_1
      //   1897: ldc_w 492
      //   1900: if_icmple +42 -> 1942
      //   1903: new 86	java/lang/StringBuilder
      //   1906: dup
      //   1907: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1910: ldc_w 494
      //   1913: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1916: iload_1
      //   1917: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1920: ldc_w 496
      //   1923: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1926: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1929: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1932: ldc_w 497
      //   1935: invokestatic 499	com/kochava/android/tracker/b:c	(I)I
      //   1938: pop
      //   1939: goto -770 -> 1169
      //   1942: iload_1
      //   1943: sipush 1000
      //   1946: imul
      //   1947: invokestatic 499	com/kochava/android/tracker/b:c	(I)I
      //   1950: pop
      //   1951: new 86	java/lang/StringBuilder
      //   1954: dup
      //   1955: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1958: ldc_w 501
      //   1961: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1964: iload_1
      //   1965: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1968: ldc_w 503
      //   1971: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1974: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1977: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   1980: goto -811 -> 1169
      //   1983: iload_1
      //   1984: ldc_w 492
      //   1987: if_icmple +58 -> 2045
      //   1990: new 86	java/lang/StringBuilder
      //   1993: dup
      //   1994: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   1997: ldc_w 403
      //   2000: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2003: iload_1
      //   2004: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2007: ldc_w 505
      //   2010: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2013: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2016: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2019: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   2022: invokeinterface 364 1 0
      //   2027: ldc 101
      //   2029: ldc2_w 506
      //   2032: invokeinterface 409 4 0
      //   2037: invokeinterface 375 1 0
      //   2042: goto -769 -> 1273
      //   2045: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   2048: invokeinterface 364 1 0
      //   2053: ldc 101
      //   2055: iload_1
      //   2056: sipush 1000
      //   2059: imul
      //   2060: i2l
      //   2061: invokeinterface 409 4 0
      //   2066: invokeinterface 375 1 0
      //   2071: new 86	java/lang/StringBuilder
      //   2074: dup
      //   2075: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2078: ldc_w 509
      //   2081: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2084: iload_1
      //   2085: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2088: ldc_w 503
      //   2091: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2094: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2097: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2100: goto -827 -> 1273
      //   2103: iload_1
      //   2104: bipush 30
      //   2106: if_icmple +41 -> 2147
      //   2109: new 86	java/lang/StringBuilder
      //   2112: dup
      //   2113: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2116: ldc_w 413
      //   2119: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2122: iload_1
      //   2123: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2126: ldc_w 511
      //   2129: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2132: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2135: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2138: bipush 30
      //   2140: invokestatic 417	com/kochava/android/tracker/b:d	(I)I
      //   2143: pop
      //   2144: goto -786 -> 1358
      //   2147: new 86	java/lang/StringBuilder
      //   2150: dup
      //   2151: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2154: ldc_w 513
      //   2157: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2160: iload_1
      //   2161: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2164: ldc_w 503
      //   2167: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2170: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2173: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2176: iload_1
      //   2177: invokestatic 417	com/kochava/android/tracker/b:d	(I)I
      //   2180: pop
      //   2181: goto -823 -> 1358
      //   2184: iload_2
      //   2185: istore_1
      //   2186: iload_2
      //   2187: sipush 5000
      //   2190: if_icmple -779 -> 1411
      //   2193: new 86	java/lang/StringBuilder
      //   2196: dup
      //   2197: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2200: ldc_w 421
      //   2203: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2206: iload_2
      //   2207: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2210: ldc_w 423
      //   2213: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2216: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2219: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2222: sipush 5000
      //   2225: istore_1
      //   2226: goto -815 -> 1411
      //   2229: iload_2
      //   2230: istore_1
      //   2231: iload_2
      //   2232: bipush 60
      //   2234: if_icmple -745 -> 1489
      //   2237: new 86	java/lang/StringBuilder
      //   2240: dup
      //   2241: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2244: ldc_w 434
      //   2247: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2250: iload_2
      //   2251: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2254: ldc_w 436
      //   2257: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2260: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2263: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2266: bipush 60
      //   2268: istore_1
      //   2269: goto -780 -> 1489
      //   2272: iload_2
      //   2273: istore_1
      //   2274: iload_2
      //   2275: sipush 10080
      //   2278: if_icmple -711 -> 1567
      //   2281: new 86	java/lang/StringBuilder
      //   2284: dup
      //   2285: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2288: ldc_w 444
      //   2291: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2294: iload_2
      //   2295: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2298: ldc_w 446
      //   2301: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2304: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2307: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2310: sipush 10080
      //   2313: istore_1
      //   2314: goto -747 -> 1567
      //   2317: aload 15
      //   2319: iload_1
      //   2320: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2323: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   2326: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2329: ldc_w 515
      //   2332: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2335: ifeq +534 -> 2869
      //   2338: ldc_w 517
      //   2341: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2344: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2347: ldc_w 515
      //   2350: iconst_0
      //   2351: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2354: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2357: pop
      //   2358: goto -675 -> 1683
      //   2361: astore 15
      //   2363: ldc_w 519
      //   2366: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2369: getstatic 524	com/kochava/android/tracker/c:b	Z
      //   2372: ifeq +8 -> 2380
      //   2375: aload 15
      //   2377: invokevirtual 527	java/lang/Exception:printStackTrace	()V
      //   2380: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2383: ldc_w 529
      //   2386: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2389: checkcast 471	java/lang/Boolean
      //   2392: invokevirtual 532	java/lang/Boolean:booleanValue	()Z
      //   2395: istore 12
      //   2397: iload 12
      //   2399: ifeq +766 -> 3165
      //   2402: getstatic 349	com/kochava/android/tracker/b:c	Landroid/content/Context;
      //   2405: ldc_w 534
      //   2408: invokevirtual 537	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2411: checkcast 539	android/telephony/TelephonyManager
      //   2414: invokevirtual 542	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
      //   2417: invokestatic 544	com/kochava/android/tracker/b:f	(Ljava/lang/String;)Ljava/lang/String;
      //   2420: pop
      //   2421: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2424: ldc_w 546
      //   2427: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2430: checkcast 471	java/lang/Boolean
      //   2433: invokevirtual 532	java/lang/Boolean:booleanValue	()Z
      //   2436: istore 12
      //   2438: iload 12
      //   2440: ifeq +766 -> 3206
      //   2443: getstatic 349	com/kochava/android/tracker/b:c	Landroid/content/Context;
      //   2446: ldc_w 548
      //   2449: invokevirtual 537	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2452: checkcast 550	android/net/wifi/WifiManager
      //   2455: invokevirtual 554	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
      //   2458: invokevirtual 559	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
      //   2461: invokestatic 561	com/kochava/android/tracker/b:g	(Ljava/lang/String;)Ljava/lang/String;
      //   2464: pop
      //   2465: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2468: ldc_w 563
      //   2471: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2474: checkcast 471	java/lang/Boolean
      //   2477: invokevirtual 532	java/lang/Boolean:booleanValue	()Z
      //   2480: istore 12
      //   2482: iload 12
      //   2484: ifeq +777 -> 3261
      //   2487: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   2490: ldc -3
      //   2492: ldc 70
      //   2494: invokeinterface 74 3 0
      //   2499: ldc -1
      //   2501: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2504: ifne +161 -> 2665
      //   2507: getstatic 349	com/kochava/android/tracker/b:c	Landroid/content/Context;
      //   2510: invokevirtual 567	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   2513: astore 17
      //   2515: aload 17
      //   2517: sipush 128
      //   2520: invokevirtual 573	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2523: invokeinterface 579 1 0
      //   2528: astore 18
      //   2530: aload 18
      //   2532: invokeinterface 584 1 0
      //   2537: ifeq +128 -> 2665
      //   2540: aload 18
      //   2542: invokeinterface 588 1 0
      //   2547: checkcast 590	android/content/pm/ApplicationInfo
      //   2550: astore 19
      //   2552: aload 19
      //   2554: invokestatic 593	com/kochava/android/tracker/b:a	(Landroid/content/pm/ApplicationInfo;)Z
      //   2557: istore 12
      //   2559: iload 12
      //   2561: ifne -31 -> 2530
      //   2564: aconst_null
      //   2565: astore 15
      //   2567: aload 17
      //   2569: aload 19
      //   2571: getfield 597	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2574: iconst_0
      //   2575: invokevirtual 601	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   2578: astore 16
      //   2580: aload 16
      //   2582: astore 15
      //   2584: aload 15
      //   2586: ifnull -56 -> 2530
      //   2589: invokestatic 605	com/kochava/android/tracker/b:j	()Ljava/util/List;
      //   2592: new 607	com/kochava/android/tracker/b$b
      //   2595: dup
      //   2596: aload_0
      //   2597: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   2600: aload 15
      //   2602: getfield 610	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   2605: new 86	java/lang/StringBuilder
      //   2608: dup
      //   2609: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2612: aload 15
      //   2614: getfield 614	android/content/pm/PackageInfo:firstInstallTime	J
      //   2617: invokevirtual 617	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2620: ldc 70
      //   2622: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2625: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2628: new 86	java/lang/StringBuilder
      //   2631: dup
      //   2632: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2635: aload 15
      //   2637: getfield 620	android/content/pm/PackageInfo:lastUpdateTime	J
      //   2640: invokevirtual 617	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2643: ldc 70
      //   2645: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2648: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2651: invokespecial 623	com/kochava/android/tracker/b$b:<init>	(Lcom/kochava/android/tracker/b;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   2654: invokeinterface 626 2 0
      //   2659: pop
      //   2660: goto -130 -> 2530
      //   2663: astore 15
      //   2665: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2668: ldc_w 628
      //   2671: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2674: checkcast 471	java/lang/Boolean
      //   2677: invokevirtual 532	java/lang/Boolean:booleanValue	()Z
      //   2680: istore 12
      //   2682: iload 12
      //   2684: ifeq +618 -> 3302
      //   2687: getstatic 349	com/kochava/android/tracker/b:c	Landroid/content/Context;
      //   2690: ldc_w 630
      //   2693: invokevirtual 537	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2696: checkcast 632	android/view/WindowManager
      //   2699: astore 15
      //   2701: aload_0
      //   2702: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   2705: aload 15
      //   2707: invokeinterface 636 1 0
      //   2712: invokevirtual 641	android/view/Display:getHeight	()I
      //   2715: invokestatic 644	com/kochava/android/tracker/b:a	(Lcom/kochava/android/tracker/b;I)I
      //   2718: pop
      //   2719: aload_0
      //   2720: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   2723: aload 15
      //   2725: invokeinterface 636 1 0
      //   2730: invokevirtual 647	android/view/Display:getWidth	()I
      //   2733: invokestatic 649	com/kochava/android/tracker/b:b	(Lcom/kochava/android/tracker/b;I)I
      //   2736: pop
      //   2737: new 86	java/lang/StringBuilder
      //   2740: dup
      //   2741: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2744: ldc_w 651
      //   2747: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2750: aload_0
      //   2751: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   2754: invokestatic 654	com/kochava/android/tracker/b:c	(Lcom/kochava/android/tracker/b;)I
      //   2757: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2760: ldc_w 656
      //   2763: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2766: aload_0
      //   2767: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   2770: invokestatic 658	com/kochava/android/tracker/b:d	(Lcom/kochava/android/tracker/b;)I
      //   2773: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2776: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2779: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2782: aload 13
      //   2784: ldc_w 660
      //   2787: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2790: astore 15
      //   2792: new 86	java/lang/StringBuilder
      //   2795: dup
      //   2796: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   2799: ldc_w 662
      //   2802: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2805: aload 15
      //   2807: invokevirtual 455	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2810: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2813: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2816: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2819: iconst_0
      //   2820: istore_1
      //   2821: iload_1
      //   2822: aload 15
      //   2824: invokevirtual 267	org/json/JSONArray:length	()I
      //   2827: if_icmpge +548 -> 3375
      //   2830: aload 15
      //   2832: iload_1
      //   2833: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2836: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   2839: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2842: ldc_w 664
      //   2845: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2848: ifeq +463 -> 3311
      //   2851: ldc_w 666
      //   2854: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2857: iconst_1
      //   2858: invokestatic 668	com/kochava/android/tracker/b:e	(Z)Z
      //   2861: pop
      //   2862: iload_1
      //   2863: iconst_1
      //   2864: iadd
      //   2865: istore_1
      //   2866: goto -45 -> 2821
      //   2869: aload 15
      //   2871: iload_1
      //   2872: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2875: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   2878: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2881: ldc_w 670
      //   2884: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2887: ifeq +26 -> 2913
      //   2890: ldc_w 672
      //   2893: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2896: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2899: ldc_w 670
      //   2902: iconst_0
      //   2903: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2906: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2909: pop
      //   2910: goto -1227 -> 1683
      //   2913: aload 15
      //   2915: iload_1
      //   2916: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2919: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   2922: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2925: ldc_w 546
      //   2928: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2931: ifeq +26 -> 2957
      //   2934: ldc_w 674
      //   2937: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2940: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2943: ldc_w 546
      //   2946: iconst_0
      //   2947: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2950: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2953: pop
      //   2954: goto -1271 -> 1683
      //   2957: aload 15
      //   2959: iload_1
      //   2960: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2963: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   2966: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2969: ldc_w 529
      //   2972: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2975: ifeq +26 -> 3001
      //   2978: ldc_w 676
      //   2981: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   2984: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   2987: ldc_w 529
      //   2990: iconst_0
      //   2991: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2994: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2997: pop
      //   2998: goto -1315 -> 1683
      //   3001: aload 15
      //   3003: iload_1
      //   3004: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3007: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   3010: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3013: ldc_w 628
      //   3016: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3019: ifeq +26 -> 3045
      //   3022: ldc_w 678
      //   3025: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3028: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   3031: ldc_w 628
      //   3034: iconst_0
      //   3035: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3038: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3041: pop
      //   3042: goto -1359 -> 1683
      //   3045: aload 15
      //   3047: iload_1
      //   3048: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3051: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   3054: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3057: ldc_w 563
      //   3060: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3063: ifeq +26 -> 3089
      //   3066: ldc_w 680
      //   3069: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3072: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   3075: ldc_w 563
      //   3078: iconst_0
      //   3079: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3082: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3085: pop
      //   3086: goto -1403 -> 1683
      //   3089: aload 15
      //   3091: iload_1
      //   3092: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3095: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   3098: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3101: ldc_w 682
      //   3104: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3107: ifeq -1424 -> 1683
      //   3110: ldc_w 684
      //   3113: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3116: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   3119: ldc_w 682
      //   3122: iconst_0
      //   3123: invokestatic 474	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3126: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3129: pop
      //   3130: goto -1447 -> 1683
      //   3133: astore 15
      //   3135: new 86	java/lang/StringBuilder
      //   3138: dup
      //   3139: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3142: ldc_w 686
      //   3145: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3148: aload 15
      //   3150: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
      //   3153: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3156: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3159: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   3162: goto -741 -> 2421
      //   3165: ldc_w 688
      //   3168: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3171: goto -750 -> 2421
      //   3174: astore 15
      //   3176: new 86	java/lang/StringBuilder
      //   3179: dup
      //   3180: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3183: ldc_w 690
      //   3186: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3189: aload 15
      //   3191: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
      //   3194: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3197: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3200: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3203: goto -738 -> 2465
      //   3206: ldc_w 692
      //   3209: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3212: goto -747 -> 2465
      //   3215: astore 16
      //   3217: new 86	java/lang/StringBuilder
      //   3220: dup
      //   3221: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3224: ldc_w 694
      //   3227: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3230: aload 19
      //   3232: getfield 597	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   3235: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3238: ldc_w 696
      //   3241: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3244: aload 16
      //   3246: invokevirtual 697	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
      //   3249: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3252: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3255: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   3258: goto -674 -> 2584
      //   3261: ldc_w 699
      //   3264: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3267: goto -602 -> 2665
      //   3270: astore 15
      //   3272: new 86	java/lang/StringBuilder
      //   3275: dup
      //   3276: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3279: ldc_w 701
      //   3282: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3285: aload 15
      //   3287: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
      //   3290: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3293: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3296: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   3299: goto -517 -> 2782
      //   3302: ldc_w 703
      //   3305: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3308: goto -526 -> 2782
      //   3311: aload 15
      //   3313: iload_1
      //   3314: invokevirtual 458	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3317: invokevirtual 459	java/lang/Object:toString	()Ljava/lang/String;
      //   3320: invokevirtual 462	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3323: ldc_w 705
      //   3326: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3329: ifeq -467 -> 2862
      //   3332: ldc_w 707
      //   3335: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3338: iconst_1
      //   3339: invokestatic 709	com/kochava/android/tracker/b:f	(Z)Z
      //   3342: pop
      //   3343: goto -481 -> 2862
      //   3346: astore 15
      //   3348: new 86	java/lang/StringBuilder
      //   3351: dup
      //   3352: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3355: ldc_w 711
      //   3358: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3361: aload 15
      //   3363: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
      //   3366: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3369: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3372: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   3375: aload 13
      //   3377: ldc_w 713
      //   3380: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3383: ldc_w 715
      //   3386: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3389: astore 15
      //   3391: aload_0
      //   3392: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   3395: aload 15
      //   3397: invokestatic 718	com/kochava/android/tracker/b:a	(Lcom/kochava/android/tracker/b;Lorg/json/JSONObject;)V
      //   3400: invokestatic 721	com/kochava/android/tracker/b:k	()Z
      //   3403: ifeq +18 -> 3421
      //   3406: invokestatic 724	com/kochava/android/tracker/b:l	()Z
      //   3409: ifeq +12 -> 3421
      //   3412: getstatic 349	com/kochava/android/tracker/b:c	Landroid/content/Context;
      //   3415: invokestatic 727	com/kochava/android/tracker/d:a	(Landroid/content/Context;)Lcom/kochava/android/tracker/d;
      //   3418: invokevirtual 729	com/kochava/android/tracker/d:a	()V
      //   3421: aload 13
      //   3423: ldc_w 731
      //   3426: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3429: invokestatic 734	com/kochava/android/tracker/b:a	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3432: pop
      //   3433: new 86	java/lang/StringBuilder
      //   3436: dup
      //   3437: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3440: ldc_w 736
      //   3443: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3446: invokestatic 740	com/kochava/android/tracker/b:m	()Lorg/json/JSONArray;
      //   3449: invokevirtual 455	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3452: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3455: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3458: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3461: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   3464: invokeinterface 364 1 0
      //   3469: ldc_w 742
      //   3472: invokestatic 740	com/kochava/android/tracker/b:m	()Lorg/json/JSONArray;
      //   3475: invokevirtual 455	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3478: invokeinterface 372 3 0
      //   3483: invokeinterface 375 1 0
      //   3488: aload 14
      //   3490: ldc_w 744
      //   3493: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3496: ifnull +87 -> 3583
      //   3499: aload 14
      //   3501: ldc_w 744
      //   3504: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3507: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3510: ldc_w 471
      //   3513: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3516: ifeq +429 -> 3945
      //   3519: aload 14
      //   3521: ldc_w 744
      //   3524: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3527: checkcast 471	java/lang/Boolean
      //   3530: invokevirtual 532	java/lang/Boolean:booleanValue	()Z
      //   3533: ifeq +412 -> 3945
      //   3536: iconst_1
      //   3537: istore_1
      //   3538: aload 14
      //   3540: ldc_w 744
      //   3543: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3546: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3549: ldc 80
      //   3551: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3554: ifeq +396 -> 3950
      //   3557: ldc -1
      //   3559: aload 14
      //   3561: ldc_w 744
      //   3564: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3567: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3570: ifeq +380 -> 3950
      //   3573: iconst_1
      //   3574: istore_2
      //   3575: goto +742 -> 4317
      //   3578: iconst_1
      //   3579: invokestatic 746	com/kochava/android/tracker/b:g	(Z)Z
      //   3582: pop
      //   3583: aload 13
      //   3585: ldc_w 748
      //   3588: invokevirtual 315	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3591: astore 13
      //   3593: new 86	java/lang/StringBuilder
      //   3596: dup
      //   3597: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3600: ldc_w 750
      //   3603: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3606: aload 13
      //   3608: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3611: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3614: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   3617: aload 13
      //   3619: ldc_w 752
      //   3622: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3625: ifeq +17 -> 3642
      //   3628: iconst_1
      //   3629: invokestatic 754	com/kochava/android/tracker/b:h	(Z)Z
      //   3632: pop
      //   3633: return
      //   3634: astore 13
      //   3636: ldc_w 756
      //   3639: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3642: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   3645: invokeinterface 364 1 0
      //   3650: ldc 56
      //   3652: invokestatic 50	java/lang/System:currentTimeMillis	()J
      //   3655: invokeinterface 409 4 0
      //   3660: invokeinterface 375 1 0
      //   3665: ldc_w 758
      //   3668: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3671: iconst_0
      //   3672: istore_1
      //   3673: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   3676: ldc -3
      //   3678: ldc 70
      //   3680: invokeinterface 74 3 0
      //   3685: ldc -1
      //   3687: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3690: ifne +548 -> 4238
      //   3693: invokestatic 469	com/kochava/android/tracker/b:i	()Ljava/util/HashMap;
      //   3696: ldc_w 670
      //   3699: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   3702: checkcast 471	java/lang/Boolean
      //   3705: invokevirtual 532	java/lang/Boolean:booleanValue	()Z
      //   3708: ifne +363 -> 4071
      //   3711: iconst_1
      //   3712: istore_2
      //   3713: invokestatic 761	com/kochava/android/tracker/b:n	()Z
      //   3716: istore 12
      //   3718: iload_1
      //   3719: invokestatic 383	com/kochava/android/tracker/b:g	()I
      //   3722: if_icmpge +50 -> 3772
      //   3725: invokestatic 54	com/kochava/android/tracker/b:d	()Landroid/content/SharedPreferences;
      //   3728: ldc_w 763
      //   3731: ldc_w 765
      //   3734: invokeinterface 74 3 0
      //   3739: ldc_w 765
      //   3742: invokevirtual 84	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3745: ifne +331 -> 4076
      //   3748: iconst_1
      //   3749: istore_3
      //   3750: invokestatic 768	com/kochava/android/tracker/b:o	()Ljava/lang/String;
      //   3753: ifnull +328 -> 4081
      //   3756: iconst_1
      //   3757: istore 4
      //   3759: iload_3
      //   3760: ifeq +327 -> 4087
      //   3763: iload_2
      //   3764: ifne +8 -> 3772
      //   3767: iload 12
      //   3769: ifeq +318 -> 4087
      //   3772: new 86	java/lang/StringBuilder
      //   3775: dup
      //   3776: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3779: ldc_w 770
      //   3782: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3785: iload_1
      //   3786: invokevirtual 388	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3789: ldc_w 503
      //   3792: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3795: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3798: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3801: iload_1
      //   3802: istore_2
      //   3803: iload_1
      //   3804: iconst_2
      //   3805: if_icmpge +46 -> 3851
      //   3808: aload_0
      //   3809: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   3812: invokestatic 772	com/kochava/android/tracker/b:e	(Lcom/kochava/android/tracker/b;)Ljava/lang/String;
      //   3815: ifnull +323 -> 4138
      //   3818: iconst_1
      //   3819: istore_3
      //   3820: invokestatic 768	com/kochava/android/tracker/b:o	()Ljava/lang/String;
      //   3823: ifnull +320 -> 4143
      //   3826: invokestatic 768	com/kochava/android/tracker/b:o	()Ljava/lang/String;
      //   3829: invokevirtual 121	java/lang/String:isEmpty	()Z
      //   3832: ifne +311 -> 4143
      //   3835: iconst_1
      //   3836: istore 4
      //   3838: iload_1
      //   3839: istore_2
      //   3840: iload 4
      //   3842: ifne +9 -> 3851
      //   3845: iload_3
      //   3846: ifeq +303 -> 4149
      //   3849: iload_1
      //   3850: istore_2
      //   3851: iload_2
      //   3852: iconst_2
      //   3853: if_icmpge +19 -> 3872
      //   3856: aload_0
      //   3857: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   3860: invokestatic 774	com/kochava/android/tracker/b:f	(Lcom/kochava/android/tracker/b;)Ljava/lang/String;
      //   3863: ifnull +328 -> 4191
      //   3866: iconst_1
      //   3867: istore_1
      //   3868: iload_1
      //   3869: ifeq +327 -> 4196
      //   3872: invokestatic 780	android/os/Message:obtain	()Landroid/os/Message;
      //   3875: astore 13
      //   3877: new 782	android/os/Bundle
      //   3880: dup
      //   3881: invokespecial 783	android/os/Bundle:<init>	()V
      //   3884: astore 14
      //   3886: aload 14
      //   3888: ldc_w 785
      //   3891: invokestatic 788	com/kochava/android/tracker/b:p	()Z
      //   3894: invokevirtual 792	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   3897: aload 13
      //   3899: aload 14
      //   3901: invokevirtual 796	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   3904: aload_0
      //   3905: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   3908: invokestatic 799	com/kochava/android/tracker/b:g	(Lcom/kochava/android/tracker/b;)Landroid/os/Handler;
      //   3911: ifnull -3385 -> 526
      //   3914: ldc_w 801
      //   3917: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3920: aload_0
      //   3921: getfield 14	com/kochava/android/tracker/b$20:a	Lcom/kochava/android/tracker/b;
      //   3924: invokestatic 799	com/kochava/android/tracker/b:g	(Lcom/kochava/android/tracker/b;)Landroid/os/Handler;
      //   3927: aload 13
      //   3929: invokevirtual 807	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   3932: pop
      //   3933: return
      //   3934: astore 15
      //   3936: ldc_w 809
      //   3939: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   3942: goto -454 -> 3488
      //   3945: iconst_0
      //   3946: istore_1
      //   3947: goto -409 -> 3538
      //   3950: iconst_0
      //   3951: istore_2
      //   3952: goto +365 -> 4317
      //   3955: new 86	java/lang/StringBuilder
      //   3958: dup
      //   3959: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3962: ldc_w 811
      //   3965: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3968: aload 13
      //   3970: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3973: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3976: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   3979: return
      //   3980: astore 13
      //   3982: new 86	java/lang/StringBuilder
      //   3985: dup
      //   3986: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   3989: ldc_w 813
      //   3992: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3995: aload 13
      //   3997: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4000: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4003: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   4006: goto -364 -> 3642
      //   4009: new 86	java/lang/StringBuilder
      //   4012: dup
      //   4013: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   4016: ldc_w 811
      //   4019: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4022: aload 13
      //   4024: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4027: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4030: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   4033: return
      //   4034: new 86	java/lang/StringBuilder
      //   4037: dup
      //   4038: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   4041: ldc_w 815
      //   4044: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4047: lload 6
      //   4049: ldc2_w 816
      //   4052: ldiv
      //   4053: invokevirtual 617	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   4056: ldc_w 819
      //   4059: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4062: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4065: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   4068: goto -403 -> 3665
      //   4071: iconst_0
      //   4072: istore_2
      //   4073: goto -360 -> 3713
      //   4076: iconst_0
      //   4077: istore_3
      //   4078: goto -328 -> 3750
      //   4081: iconst_0
      //   4082: istore 4
      //   4084: goto -325 -> 3759
      //   4087: iload_3
      //   4088: ifeq +8 -> 4096
      //   4091: iload 4
      //   4093: ifne -321 -> 3772
      //   4096: ldc2_w 816
      //   4099: invokestatic 301	java/lang/Thread:sleep	(J)V
      //   4102: iload_1
      //   4103: iconst_1
      //   4104: iadd
      //   4105: istore_1
      //   4106: goto -388 -> 3718
      //   4109: astore 13
      //   4111: new 86	java/lang/StringBuilder
      //   4114: dup
      //   4115: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   4118: ldc_w 821
      //   4121: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4124: aload 13
      //   4126: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4129: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4132: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   4135: goto -33 -> 4102
      //   4138: iconst_0
      //   4139: istore_3
      //   4140: goto -320 -> 3820
      //   4143: iconst_0
      //   4144: istore 4
      //   4146: goto -308 -> 3838
      //   4149: ldc2_w 816
      //   4152: invokestatic 301	java/lang/Thread:sleep	(J)V
      //   4155: iload_1
      //   4156: iconst_1
      //   4157: iadd
      //   4158: istore_1
      //   4159: goto -358 -> 3801
      //   4162: astore 13
      //   4164: new 86	java/lang/StringBuilder
      //   4167: dup
      //   4168: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   4171: ldc_w 821
      //   4174: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4177: aload 13
      //   4179: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4182: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4185: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   4188: goto -33 -> 4155
      //   4191: iconst_0
      //   4192: istore_1
      //   4193: goto -325 -> 3868
      //   4196: ldc2_w 816
      //   4199: invokestatic 301	java/lang/Thread:sleep	(J)V
      //   4202: iload_2
      //   4203: iconst_1
      //   4204: iadd
      //   4205: istore_2
      //   4206: goto -355 -> 3851
      //   4209: astore 13
      //   4211: new 86	java/lang/StringBuilder
      //   4214: dup
      //   4215: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   4218: ldc_w 821
      //   4221: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4224: aload 13
      //   4226: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4229: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4232: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
      //   4235: goto -33 -> 4202
      //   4238: ldc_w 823
      //   4241: invokestatic 39	com/kochava/android/a/b:a	(Ljava/lang/String;)V
      //   4244: goto -372 -> 3872
      //   4247: astore 13
      //   4249: goto -267 -> 3982
      //   4252: astore 14
      //   4254: goto -671 -> 3583
      //   4257: astore 15
      //   4259: goto -771 -> 3488
      //   4262: astore 15
      //   4264: goto -864 -> 3400
      //   4267: astore 15
      //   4269: goto -2675 -> 1594
      //   4272: astore 15
      //   4274: goto -2758 -> 1516
      //   4277: astore 15
      //   4279: goto -2841 -> 1438
      //   4282: astore 15
      //   4284: goto -3262 -> 1022
      //   4287: astore 15
      //   4289: goto -3326 -> 963
      //   4292: iload_2
      //   4293: istore_1
      //   4294: goto -4140 -> 154
      //   4297: astore 15
      //   4299: goto -3217 -> 1082
      //   4302: astore 15
      //   4304: goto -3130 -> 1174
      //   4307: astore 15
      //   4309: goto -3036 -> 1273
      //   4312: astore 15
      //   4314: goto -2956 -> 1358
      //   4317: iload_1
      //   4318: ifne -740 -> 3578
      //   4321: iload_2
      //   4322: ifeq -739 -> 3583
      //   4325: goto -747 -> 3578
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4328	0	this	20
      //   43	4275	1	i	int
      //   14	4308	2	j	int
      //   12	4128	3	k	int
      //   650	3495	4	m	int
      //   647	8	5	n	int
      //   9	4039	6	l1	long
      //   18	18	8	l2	long
      //   33	5	10	l3	long
      //   620	3148	12	bool	boolean
      //   150	1	13	localObject1	Object
      //   483	39	13	localIOException1	IOException
      //   527	15	13	localException1	Exception
      //   601	994	13	localObject2	Object
      //   1749	39	13	localException2	Exception
      //   1793	1791	13	localIOException2	IOException
      //   3591	27	13	str	String
      //   3634	1	13	localException3	Exception
      //   3875	94	13	localMessage	Message
      //   3980	43	13	localException4	Exception
      //   4109	16	13	localInterruptedException1	InterruptedException
      //   4162	16	13	localInterruptedException2	InterruptedException
      //   4209	16	13	localInterruptedException3	InterruptedException
      //   4247	1	13	localException5	Exception
      //   287	1230	14	localObject3	Object
      //   1690	16	14	localJSONException1	JSONException
      //   1731	1829	14	localJSONException2	JSONException
      //   3884	16	14	localBundle	Bundle
      //   4252	1	14	localException6	Exception
      //   367	1276	15	localObject4	Object
      //   1738	1	15	localJSONException3	JSONException
      //   1798	1	15	localJSONException4	JSONException
      //   1809	509	15	localJSONException5	JSONException
      //   2361	15	15	localException7	Exception
      //   2565	71	15	localObject5	Object
      //   2663	1	15	localException8	Exception
      //   2699	391	15	localObject6	Object
      //   3133	16	15	localException9	Exception
      //   3174	16	15	localException10	Exception
      //   3270	42	15	localException11	Exception
      //   3346	16	15	localException12	Exception
      //   3389	7	15	localJSONObject	JSONObject
      //   3934	1	15	localException13	Exception
      //   4257	1	15	localJSONException6	JSONException
      //   4262	1	15	localException14	Exception
      //   4267	1	15	localException15	Exception
      //   4272	1	15	localException16	Exception
      //   4277	1	15	localException17	Exception
      //   4282	1	15	localException18	Exception
      //   4287	1	15	localException19	Exception
      //   4297	1	15	localException20	Exception
      //   4302	1	15	localException21	Exception
      //   4307	1	15	localException22	Exception
      //   4312	1	15	localException23	Exception
      //   409	2172	16	localObject7	Object
      //   3215	30	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   2513	55	17	localPackageManager	android.content.pm.PackageManager
      //   2528	13	18	localIterator	Iterator
      //   2550	681	19	localApplicationInfo	ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   154	203	483	java/io/IOException
      //   203	214	483	java/io/IOException
      //   214	460	483	java/io/IOException
      //   460	467	483	java/io/IOException
      //   472	480	483	java/io/IOException
      //   558	588	483	java/io/IOException
      //   588	599	483	java/io/IOException
      //   1692	1719	483	java/io/IOException
      //   15	35	527	java/lang/Exception
      //   588	599	1690	org/json/JSONException
      //   636	646	1731	org/json/JSONException
      //   657	683	1731	org/json/JSONException
      //   696	754	1731	org/json/JSONException
      //   799	809	1738	org/json/JSONException
      //   813	840	1738	org/json/JSONException
      //   769	796	1749	java/lang/Exception
      //   799	809	1749	java/lang/Exception
      //   813	840	1749	java/lang/Exception
      //   849	892	1749	java/lang/Exception
      //   892	914	1749	java/lang/Exception
      //   1740	1746	1749	java/lang/Exception
      //   1800	1806	1749	java/lang/Exception
      //   1811	1817	1749	java/lang/Exception
      //   2363	2380	1749	java/lang/Exception
      //   2380	2397	1749	java/lang/Exception
      //   2421	2438	1749	java/lang/Exception
      //   2465	2482	1749	java/lang/Exception
      //   2665	2682	1749	java/lang/Exception
      //   3135	3162	1749	java/lang/Exception
      //   3165	3171	1749	java/lang/Exception
      //   3176	3203	1749	java/lang/Exception
      //   3206	3212	1749	java/lang/Exception
      //   3261	3267	1749	java/lang/Exception
      //   3272	3299	1749	java/lang/Exception
      //   3302	3308	1749	java/lang/Exception
      //   3348	3375	1749	java/lang/Exception
      //   3400	3421	1749	java/lang/Exception
      //   3636	3642	1749	java/lang/Exception
      //   3936	3942	1749	java/lang/Exception
      //   603	622	1793	java/io/IOException
      //   636	646	1793	java/io/IOException
      //   657	683	1793	java/io/IOException
      //   696	754	1793	java/io/IOException
      //   769	796	1793	java/io/IOException
      //   799	809	1793	java/io/IOException
      //   813	840	1793	java/io/IOException
      //   849	892	1793	java/io/IOException
      //   892	914	1793	java/io/IOException
      //   914	963	1793	java/io/IOException
      //   963	1022	1793	java/io/IOException
      //   1022	1082	1793	java/io/IOException
      //   1082	1134	1793	java/io/IOException
      //   1140	1169	1793	java/io/IOException
      //   1169	1174	1793	java/io/IOException
      //   1174	1217	1793	java/io/IOException
      //   1221	1273	1793	java/io/IOException
      //   1273	1319	1793	java/io/IOException
      //   1324	1358	1793	java/io/IOException
      //   1358	1373	1793	java/io/IOException
      //   1379	1408	1793	java/io/IOException
      //   1411	1438	1793	java/io/IOException
      //   1438	1453	1793	java/io/IOException
      //   1458	1487	1793	java/io/IOException
      //   1489	1516	1793	java/io/IOException
      //   1516	1531	1793	java/io/IOException
      //   1536	1565	1793	java/io/IOException
      //   1567	1594	1793	java/io/IOException
      //   1594	1631	1793	java/io/IOException
      //   1633	1683	1793	java/io/IOException
      //   1740	1746	1793	java/io/IOException
      //   1751	1792	1793	java/io/IOException
      //   1800	1806	1793	java/io/IOException
      //   1811	1817	1793	java/io/IOException
      //   1820	1865	1793	java/io/IOException
      //   1868	1893	1793	java/io/IOException
      //   1903	1939	1793	java/io/IOException
      //   1942	1980	1793	java/io/IOException
      //   1990	2042	1793	java/io/IOException
      //   2045	2100	1793	java/io/IOException
      //   2109	2144	1793	java/io/IOException
      //   2147	2181	1793	java/io/IOException
      //   2193	2222	1793	java/io/IOException
      //   2237	2266	1793	java/io/IOException
      //   2281	2310	1793	java/io/IOException
      //   2317	2358	1793	java/io/IOException
      //   2363	2380	1793	java/io/IOException
      //   2380	2397	1793	java/io/IOException
      //   2402	2421	1793	java/io/IOException
      //   2421	2438	1793	java/io/IOException
      //   2443	2465	1793	java/io/IOException
      //   2465	2482	1793	java/io/IOException
      //   2487	2530	1793	java/io/IOException
      //   2530	2559	1793	java/io/IOException
      //   2567	2580	1793	java/io/IOException
      //   2589	2660	1793	java/io/IOException
      //   2665	2682	1793	java/io/IOException
      //   2687	2782	1793	java/io/IOException
      //   2782	2819	1793	java/io/IOException
      //   2821	2862	1793	java/io/IOException
      //   2869	2910	1793	java/io/IOException
      //   2913	2954	1793	java/io/IOException
      //   2957	2998	1793	java/io/IOException
      //   3001	3042	1793	java/io/IOException
      //   3045	3086	1793	java/io/IOException
      //   3089	3130	1793	java/io/IOException
      //   3135	3162	1793	java/io/IOException
      //   3165	3171	1793	java/io/IOException
      //   3176	3203	1793	java/io/IOException
      //   3206	3212	1793	java/io/IOException
      //   3217	3258	1793	java/io/IOException
      //   3261	3267	1793	java/io/IOException
      //   3272	3299	1793	java/io/IOException
      //   3302	3308	1793	java/io/IOException
      //   3311	3343	1793	java/io/IOException
      //   3348	3375	1793	java/io/IOException
      //   3375	3400	1793	java/io/IOException
      //   3400	3421	1793	java/io/IOException
      //   3421	3488	1793	java/io/IOException
      //   3488	3536	1793	java/io/IOException
      //   3538	3573	1793	java/io/IOException
      //   3578	3583	1793	java/io/IOException
      //   3583	3633	1793	java/io/IOException
      //   3636	3642	1793	java/io/IOException
      //   3936	3942	1793	java/io/IOException
      //   3955	3979	1793	java/io/IOException
      //   849	892	1798	org/json/JSONException
      //   892	914	1809	org/json/JSONException
      //   1594	1631	2361	java/lang/Exception
      //   1633	1683	2361	java/lang/Exception
      //   2317	2358	2361	java/lang/Exception
      //   2869	2910	2361	java/lang/Exception
      //   2913	2954	2361	java/lang/Exception
      //   2957	2998	2361	java/lang/Exception
      //   3001	3042	2361	java/lang/Exception
      //   3045	3086	2361	java/lang/Exception
      //   3089	3130	2361	java/lang/Exception
      //   2487	2530	2663	java/lang/Exception
      //   2530	2559	2663	java/lang/Exception
      //   2567	2580	2663	java/lang/Exception
      //   2589	2660	2663	java/lang/Exception
      //   3217	3258	2663	java/lang/Exception
      //   2402	2421	3133	java/lang/Exception
      //   2443	2465	3174	java/lang/Exception
      //   2567	2580	3215	android/content/pm/PackageManager$NameNotFoundException
      //   2687	2782	3270	java/lang/Exception
      //   2782	2819	3346	java/lang/Exception
      //   2821	2862	3346	java/lang/Exception
      //   3311	3343	3346	java/lang/Exception
      //   3583	3633	3634	java/lang/Exception
      //   3421	3488	3934	java/lang/Exception
      //   603	622	3980	java/lang/Exception
      //   636	646	3980	java/lang/Exception
      //   657	683	3980	java/lang/Exception
      //   696	754	3980	java/lang/Exception
      //   1751	1792	3980	java/lang/Exception
      //   3955	3979	3980	java/lang/Exception
      //   4096	4102	4109	java/lang/InterruptedException
      //   4149	4155	4162	java/lang/InterruptedException
      //   4196	4202	4209	java/lang/InterruptedException
      //   154	203	4247	java/lang/Exception
      //   203	214	4247	java/lang/Exception
      //   214	460	4247	java/lang/Exception
      //   460	467	4247	java/lang/Exception
      //   472	480	4247	java/lang/Exception
      //   558	588	4247	java/lang/Exception
      //   588	599	4247	java/lang/Exception
      //   1692	1719	4247	java/lang/Exception
      //   3488	3536	4252	java/lang/Exception
      //   3538	3573	4252	java/lang/Exception
      //   3578	3583	4252	java/lang/Exception
      //   3421	3488	4257	org/json/JSONException
      //   3375	3400	4262	java/lang/Exception
      //   1516	1531	4267	java/lang/Exception
      //   1536	1565	4267	java/lang/Exception
      //   1567	1594	4267	java/lang/Exception
      //   2281	2310	4267	java/lang/Exception
      //   1438	1453	4272	java/lang/Exception
      //   1458	1487	4272	java/lang/Exception
      //   1489	1516	4272	java/lang/Exception
      //   2237	2266	4272	java/lang/Exception
      //   1358	1373	4277	java/lang/Exception
      //   1379	1408	4277	java/lang/Exception
      //   1411	1438	4277	java/lang/Exception
      //   2193	2222	4277	java/lang/Exception
      //   963	1022	4282	java/lang/Exception
      //   914	963	4287	java/lang/Exception
      //   1022	1082	4297	java/lang/Exception
      //   1820	1865	4297	java/lang/Exception
      //   1868	1893	4297	java/lang/Exception
      //   1082	1134	4302	java/lang/Exception
      //   1140	1169	4302	java/lang/Exception
      //   1169	1174	4302	java/lang/Exception
      //   1903	1939	4302	java/lang/Exception
      //   1942	1980	4302	java/lang/Exception
      //   1174	1217	4307	java/lang/Exception
      //   1221	1273	4307	java/lang/Exception
      //   1990	2042	4307	java/lang/Exception
      //   2045	2100	4307	java/lang/Exception
      //   1273	1319	4312	java/lang/Exception
      //   1324	1358	4312	java/lang/Exception
      //   2109	2144	4312	java/lang/Exception
      //   2147	2181	4312	java/lang/Exception
    }
  };
  private Handler aq = null;
  private final String ar = "location_accuracy";
  private final String as = "location_timeout";
  private final String at = "location_staleness";
  protected JSONObject d;
  protected JSONObject e;
  protected JSONObject f;
  private String o;
  private String p;
  private String q;
  private String r;
  private String s;
  private String t;
  private String v;
  private String x;
  private int y;
  private int z;
  
  static
  {
    a = "";
    b = false;
    k = 60000;
    l = false;
    m = false;
    n = new HashMap() {};
  }
  
  public b(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    a(paramContext, true, paramHashMap);
  }
  
  private String K()
  {
    String str = "" + T() + ":::";
    str = str + O() + ":::";
    str = str + N() + ":::";
    str = str + R() + ":::";
    return str + P();
  }
  
  /* Error */
  private static void L()
  {
    // Byte code:
    //   0: getstatic 330	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +261 -> 266
    //   8: getstatic 167	com/kochava/android/tracker/b:b	Z
    //   11: ifne +255 -> 266
    //   14: getstatic 214	com/kochava/android/tracker/b:aa	Z
    //   17: ifeq +10 -> 27
    //   20: ldc_w 332
    //   23: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   26: return
    //   27: ldc_w 339
    //   30: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   33: getstatic 206	com/kochava/android/tracker/b:V	Z
    //   36: ifne +46 -> 82
    //   39: getstatic 343	com/kochava/android/tracker/b:S	Ljava/util/Timer;
    //   42: ifnonnull +189 -> 231
    //   45: ldc_w 345
    //   48: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   51: new 347	java/util/Timer
    //   54: dup
    //   55: invokespecial 348	java/util/Timer:<init>	()V
    //   58: putstatic 343	com/kochava/android/tracker/b:S	Ljava/util/Timer;
    //   61: getstatic 343	com/kochava/android/tracker/b:S	Ljava/util/Timer;
    //   64: new 34	com/kochava/android/tracker/b$2
    //   67: dup
    //   68: invokespecial 349	com/kochava/android/tracker/b$2:<init>	()V
    //   71: getstatic 170	com/kochava/android/tracker/b:k	I
    //   74: i2l
    //   75: getstatic 170	com/kochava/android/tracker/b:k	I
    //   78: i2l
    //   79: invokevirtual 353	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   82: invokestatic 358	java/lang/System:currentTimeMillis	()J
    //   85: ldc2_w 359
    //   88: ldiv
    //   89: putstatic 197	com/kochava/android/tracker/b:M	J
    //   92: getstatic 193	com/kochava/android/tracker/b:K	Z
    //   95: ifeq +18 -> 113
    //   98: invokestatic 362	com/kochava/android/tracker/b:ab	()Z
    //   101: ifeq +12 -> 113
    //   104: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   107: invokestatic 369	com/kochava/android/tracker/d:a	(Landroid/content/Context;)Lcom/kochava/android/tracker/d;
    //   110: invokevirtual 371	com/kochava/android/tracker/d:a	()V
    //   113: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   116: ifnull +49 -> 165
    //   119: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   122: ldc_w 375
    //   125: ldc -95
    //   127: invokeinterface 381 3 0
    //   132: ldc -95
    //   134: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 389	org/json/JSONObject
    //   145: dup
    //   146: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   149: ldc_w 375
    //   152: ldc -95
    //   154: invokeinterface 381 3 0
    //   159: invokespecial 391	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 394	com/kochava/android/tracker/b:c	(Lorg/json/JSONObject;)V
    //   165: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   168: ifnull +98 -> 266
    //   171: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   174: ldc_w 396
    //   177: ldc -95
    //   179: invokeinterface 381 3 0
    //   184: ldc_w 398
    //   187: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifeq +76 -> 266
    //   193: getstatic 216	com/kochava/android/tracker/b:ab	Z
    //   196: ifeq +64 -> 260
    //   199: ldc_w 400
    //   202: invokestatic 402	com/kochava/android/tracker/b:i	(Ljava/lang/String;)V
    //   205: return
    //   206: astore_1
    //   207: new 305	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 404
    //   217: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   230: return
    //   231: ldc_w 409
    //   234: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   237: goto -155 -> 82
    //   240: astore_1
    //   241: ldc_w 411
    //   244: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   247: getstatic 414	com/kochava/android/tracker/c:b	Z
    //   250: ifeq -85 -> 165
    //   253: aload_1
    //   254: invokevirtual 417	org/json/JSONException:printStackTrace	()V
    //   257: goto -92 -> 165
    //   260: ldc_w 419
    //   263: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
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
  
  private static void M()
  {
    for (;;)
    {
      try
      {
        if ((Build.VERSION.SDK_INT < 14) || (b)) {
          return;
        }
        if (aa)
        {
          com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
          return;
        }
        com.kochava.android.a.b.a("Automatic Session End");
        if (!V)
        {
          if (S != null)
          {
            com.kochava.android.a.b.a("Session end, flush timer was on, canceling timer and flushing current events.");
            c();
            S.cancel();
            S = null;
          }
        }
        else
        {
          if (!ab) {
            break;
          }
          i("exit");
          return;
        }
      }
      catch (Exception localException)
      {
        com.kochava.android.a.b.b("(Automatic Session End) Exception occured during session tracking.\n" + localException);
        return;
      }
      com.kochava.android.a.b.a("Session end, flush timer was already off.");
    }
    com.kochava.android.a.b.a("Session events disabled by server.");
  }
  
  private static String N()
  {
    return Build.BRAND;
  }
  
  private static String O()
  {
    return Build.MODEL;
  }
  
  private static String P()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  private static boolean Q()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (ah != null)
    {
      bool1 = bool2;
      if (!ah.getAll().isEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private String R()
  {
    return this.o + " " + this.p;
  }
  
  private String S()
  {
    if (this.r != null) {
      return this.r;
    }
    return "Unknown";
  }
  
  private static String T()
  {
    if ((ag.contains("kochava_app_id_generated")) && (!ag.getString("kochava_app_id_generated", "").equals(""))) {
      return ag.getString("kochava_app_id_generated", "");
    }
    String str = UUID.randomUUID().toString().replaceAll("-", "");
    str = "KA" + str;
    ag.edit().putString("kochava_app_id_generated", str).apply();
    return str;
  }
  
  private void U()
  {
    if (this.aq == null) {
      this.aq = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          boolean bool = paramAnonymousMessage.getData().getBoolean("sendonstart");
          b.k(b.this);
          if (!bool)
          {
            b.l(b.this).schedule(new TimerTask()
            {
              public void run()
              {
                com.kochava.android.a.b.a("Reached 10 min mark w/o sending initial, sending now.");
                b.a(b.this, false);
              }
            }, 600000L);
            return;
          }
          b.l(b.this).schedule(new TimerTask()
          {
            public void run()
            {
              com.kochava.android.a.b.a("Scheduling timer to que initial event if needed.");
              b.a(b.this, false);
            }
          }, 2000L);
          b.a(b.this, new Timer());
          b.m(b.this).schedule(new TimerTask()
          {
            public void run() {}
          }, 4000L);
        }
      };
    }
  }
  
  private void V()
  {
    int i2 = 0;
    ag = c.getSharedPreferences("initPrefs", 0);
    if (ag.getString("initBool", "").equals("")) {
      ag.edit().putString("initBool", "false").apply();
    }
    String str1;
    String str2;
    if (ag.getString("kochavaappdata", null) != null)
    {
      str1 = Q.a(ag.getString("kochavaappdata", null));
      str2 = K();
      com.kochava.android.a.b.a("Stored Data: " + str1);
      com.kochava.android.a.b.a("Created Data: " + str2);
      if (str1 == null) {
        Q.b(ag.getString("kochavaappdata", null), str2);
      }
    }
    for (;;)
    {
      if ((this.R) || ((!ag.getString("initBool", "").equals("")) && (ag.getString("initBool", "").equals("false"))))
      {
        com.kochava.android.a.b.a("Initial event has not yet been qued in the database, making initial call");
        b("initial", null, null);
      }
      int i1;
      if (!V)
      {
        i1 = i2;
        if (!V)
        {
          i1 = i2;
          if (X) {}
        }
      }
      else
      {
        i1 = 1;
      }
      if ((i1 != 0) && (S == null))
      {
        S = new Timer();
        S.schedule(new TimerTask()
        {
          public void run()
          {
            if ((b.B()) && (System.currentTimeMillis() - b.C() < b.D()))
            {
              com.kochava.android.a.b.a("Too soon since last event, flush timer waiting for next flush attempt.");
              return;
            }
            b.c();
          }
        }, 0L, k);
      }
      this.U = new Timer();
      return;
      if (!str1.equals(str2))
      {
        Q.a(ag.getString("kochavaappdata", null), str2);
      }
      else
      {
        com.kochava.android.a.b.a("Set start of life to false");
        this.R = false;
        continue;
        this.R = false;
      }
    }
  }
  
  private static String W()
  {
    Object localObject1;
    if (!ag.getString("initBool", "").equals("true"))
    {
      com.kochava.android.a.b.a("PREF_INIT not true, waiting for initial to be queued");
      localObject1 = "";
    }
    Object localObject3;
    long l1;
    for (;;)
    {
      return localObject1;
      localObject1 = Q.a();
      if (localObject1 == null) {
        return "";
      }
      localObject3 = new String[2];
      localObject1 = ((String)localObject1).split("=", 2);
      l1 = Long.parseLong(localObject1[0]);
      localObject3 = localObject1[1];
      com.kochava.android.a.b.a("Post The Data 3>>>>>>" + (String)localObject3);
      int i1 = 0;
      if (((String)localObject3).contains("\"action\":\"initial\""))
      {
        com.kochava.android.a.b.b("Post Data: Event is initial, look at response");
        i1 = 1;
      }
      if ((j == null) || (j.trim().isEmpty()))
      {
        com.kochava.android.a.b.a("postEvent - hostControl was empty, using default");
        j = "control.kochava.com";
      }
      for (;;)
      {
        try
        {
          com.kochava.android.a.b.a("postEvent - posting to " + "https://" + j + "/track/kvTracker.php");
          localObject1 = (HttpsURLConnection)new URL("https://" + j + "/track/kvTracker.php").openConnection();
          ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", ag.getString("useragent", ""));
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
          com.kochava.android.a.b.b("TrackTask " + localException);
          return "";
          com.kochava.android.a.b.b("TrackTask " + localException);
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
          com.kochava.android.a.b.a("Grabbing Result...");
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
          com.kochava.android.a.b.b("SSLException! Shutting down SDK and sending report." + localIOException1);
          b(localIOException1);
          return "";
          localObject3 = ((StringBuffer)localObject3).toString();
          com.kochava.android.a.b.a("Result: " + (String)localObject3);
          if (i1 != 0)
          {
            if (((String)localObject3).contains("\"success\":\"1\""))
            {
              com.kochava.android.a.b.a("Got success response, cleaning database.");
              Q.a(l1);
            }
            Object localObject2 = localObject3;
            if (Q()) {
              break;
            }
            localObject2 = localObject3;
            if (!ad) {
              break;
            }
            com.kochava.android.a.b.a("Requesting attribution data in " + D + " seconds...");
            a(D);
            return localObject3;
          }
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          com.kochava.android.a.b.b("TrackTask " + localOutOfMemoryError);
          return "";
        }
      }
    }
    Q.a(l1);
    return localObject3;
    com.kochava.android.a.b.b("SSLException! Shutting down SDK and sending report." + localIOException2);
    b(localIOException2);
    for (;;)
    {
      return "";
      label671:
      com.kochava.android.a.b.b("TrackTask " + localIOException2);
    }
  }
  
  private String X()
  {
    Object localObject5 = "";
    localObject1 = "";
    int i1 = 1;
    int i2 = 1;
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
        com.kochava.android.a.b.b(((StringWriter)localObject6).toString());
        localObject6 = (String)localObject1 + "\nError with user agent first method: " + localException2.toString() + "\n" + ((StringWriter)localObject6).toString();
      }
    }
    if (((String)localObject5).trim().isEmpty()) {
      i1 = 0;
    }
    localObject1 = localObject5;
    localObject3 = localObject6;
    if (i1 == 0)
    {
      localObject1 = localObject5;
      localObject3 = localObject6;
    }
    try
    {
      localObject6 = (String)localObject6 + "\nTrying user agent method 2";
      localObject1 = localObject5;
      localObject3 = localObject6;
      localObject5 = new WebView(c).getSettings().getUserAgentString();
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
        com.kochava.android.a.b.b(((StringWriter)localObject6).toString());
        localObject4 = localException2 + "\nError with user agent second method: " + localException3.toString() + "\n" + ((StringWriter)localObject6).toString() + "\n userAgent = error.";
      }
    }
    i1 = i2;
    if (((String)localObject1).trim().isEmpty()) {
      i1 = 0;
    }
    localObject7 = localObject1;
    localObject8 = localObject3;
    if (i1 == 0)
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
      localObject1 = ((WebSettings)((Constructor)localObject3).newInstance(new Object[] { c, null })).getUserAgentString();
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
        com.kochava.android.a.b.b(((StringWriter)localObject4).toString());
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
    com.kochava.android.a.b.a("user agent result: " + (String)localObject8);
    return localObject7;
  }
  
  private static String Y()
  {
    try
    {
      Object localObject = (AudioManager)c.getSystemService("audio");
      localObject = ((AudioManager)localObject).getStreamVolume(3) + "";
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private String Z()
  {
    for (;;)
    {
      int i1;
      try
      {
        i1 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(c);
        if (i1 != 0) {}
        switch (i1)
        {
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
          com.kochava.android.a.b.a("Google Play Services check returned unknown error code (" + i1 + ").");
          com.kochava.android.a.b.b("Problem getting Advertising ID " + GooglePlayServicesUtil.getErrorString(i1));
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(c);
          String str = localInfo.getId();
          G = localInfo.isLimitAdTrackingEnabled();
          return str;
        }
      }
      catch (Exception localException)
      {
        com.kochava.android.a.b.b("Problem getting Advertising ID (catch): " + localException.toString());
        return "";
      }
      com.kochava.android.a.b.a("Google Play Services check returned ConnectionResult.SUCCESS (" + i1 + ").");
      continue;
      com.kochava.android.a.b.a("Google Play Services check returned ConnectionResult.SERVICE_MISSING (" + i1 + ").");
      continue;
      com.kochava.android.a.b.a("Google Play Services check returned ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED (" + i1 + ").");
      continue;
      com.kochava.android.a.b.a("Google Play Services check returned ConnectionResult.SERVICE_DISABLED (" + i1 + ").");
      continue;
      com.kochava.android.a.b.a("Google Play Services check returned ConnectionResult.SERVICE_INVALID (" + i1 + ").");
    }
  }
  
  public static String a()
  {
    if (ah != null) {
      return ah.getString("attributionData", "");
    }
    return "";
  }
  
  /* Error */
  protected static String a(android.content.ContentResolver paramContentResolver)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_2
    //   7: aload_0
    //   8: getstatic 244	com/kochava/android/tracker/b:al	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 383	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc_w 837
    //   20: aastore
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 843	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +18 -> 47
    //   32: aload_0
    //   33: astore_2
    //   34: aload_0
    //   35: astore_3
    //   36: aload_0
    //   37: invokeinterface 848 1 0
    //   42: istore_1
    //   43: iload_1
    //   44: ifne +24 -> 68
    //   47: aload_0
    //   48: ifnull +18 -> 66
    //   51: aload_0
    //   52: invokeinterface 851 1 0
    //   57: ifne +9 -> 66
    //   60: aload_0
    //   61: invokeinterface 852 1 0
    //   66: aconst_null
    //   67: areturn
    //   68: aload_0
    //   69: astore_2
    //   70: aload_0
    //   71: astore_3
    //   72: aload_0
    //   73: aload_0
    //   74: ldc_w 837
    //   77: invokeinterface 856 2 0
    //   82: invokeinterface 858 2 0
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
    //   101: invokeinterface 851 1 0
    //   106: ifne +11 -> 117
    //   109: aload_0
    //   110: invokeinterface 852 1 0
    //   115: aload_2
    //   116: astore_3
    //   117: aload_3
    //   118: areturn
    //   119: astore_0
    //   120: aload_2
    //   121: astore_3
    //   122: new 305	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 860
    //   132: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_0
    //   136: invokevirtual 765	java/lang/Exception:toString	()Ljava/lang/String;
    //   139: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   148: aload 4
    //   150: astore_3
    //   151: aload_2
    //   152: ifnull -35 -> 117
    //   155: aload 4
    //   157: astore_3
    //   158: aload_2
    //   159: invokeinterface 851 1 0
    //   164: ifne -47 -> 117
    //   167: aload_2
    //   168: invokeinterface 852 1 0
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
    //   192: invokeinterface 851 1 0
    //   197: ifne +9 -> 206
    //   200: aload_3
    //   201: invokeinterface 852 1 0
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
  
  protected static void a(int paramInt)
  {
    ak.schedule(ap, paramInt, TimeUnit.SECONDS);
  }
  
  /* Error */
  @TargetApi(14)
  private void a(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +2126 -> 2127
    //   4: aload_1
    //   5: invokevirtual 893	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 191	com/kochava/android/tracker/b:J	Z
    //   15: ldc_w 895
    //   18: new 305	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 897
    //   28: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: getstatic 165	com/kochava/android/tracker/b:a	Ljava/lang/String;
    //   34: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 902	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   43: pop
    //   44: aload_0
    //   45: new 347	java/util/Timer
    //   48: dup
    //   49: invokespecial 348	java/util/Timer:<init>	()V
    //   52: putfield 904	com/kochava/android/tracker/b:Y	Ljava/util/Timer;
    //   55: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   58: ldc_w 504
    //   61: iconst_0
    //   62: invokevirtual 510	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   65: putstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   68: new 518	com/kochava/android/tracker/a
    //   71: dup
    //   72: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   75: invokespecial 905	com/kochava/android/tracker/a:<init>	(Landroid/content/Context;)V
    //   78: putstatic 516	com/kochava/android/tracker/b:Q	Lcom/kochava/android/tracker/a;
    //   81: aload_3
    //   82: ifnull +231 -> 313
    //   85: aload_3
    //   86: ldc_w 907
    //   89: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   92: ifnull +44 -> 136
    //   95: aload_3
    //   96: ldc_w 907
    //   99: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   105: ldc_w 915
    //   108: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   111: ifeq +25 -> 136
    //   114: aload_3
    //   115: ldc_w 907
    //   118: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   121: checkcast 915	java/lang/Boolean
    //   124: invokevirtual 918	java/lang/Boolean:booleanValue	()Z
    //   127: istore_2
    //   128: iload_2
    //   129: invokestatic 920	com/kochava/android/tracker/b:a	(Z)V
    //   132: iload_2
    //   133: invokestatic 922	com/kochava/android/tracker/b:b	(Z)V
    //   136: aload_3
    //   137: ldc_w 924
    //   140: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   143: ifnull +35 -> 178
    //   146: aload_3
    //   147: ldc_w 924
    //   150: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   153: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   156: ldc_w 383
    //   159: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   162: ifeq +16 -> 178
    //   165: aload_3
    //   166: ldc_w 924
    //   169: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   172: checkcast 383	java/lang/String
    //   175: putstatic 165	com/kochava/android/tracker/b:a	Ljava/lang/String;
    //   178: aload_3
    //   179: ldc_w 926
    //   182: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   185: ifnull +38 -> 223
    //   188: aload_3
    //   189: ldc_w 926
    //   192: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   195: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   198: ldc_w 915
    //   201: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   204: ifeq +19 -> 223
    //   207: aload_3
    //   208: ldc_w 926
    //   211: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   214: checkcast 915	java/lang/Boolean
    //   217: invokevirtual 918	java/lang/Boolean:booleanValue	()Z
    //   220: putstatic 167	com/kochava/android/tracker/b:b	Z
    //   223: aload_3
    //   224: ldc_w 928
    //   227: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   230: ifnull +38 -> 268
    //   233: aload_3
    //   234: ldc_w 928
    //   237: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   240: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   243: ldc_w 915
    //   246: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   249: ifeq +19 -> 268
    //   252: aload_3
    //   253: ldc_w 928
    //   256: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   259: checkcast 915	java/lang/Boolean
    //   262: invokevirtual 918	java/lang/Boolean:booleanValue	()Z
    //   265: putstatic 189	com/kochava/android/tracker/b:I	Z
    //   268: aload_3
    //   269: ldc_w 930
    //   272: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   275: ifnull +38 -> 313
    //   278: aload_3
    //   279: ldc_w 930
    //   282: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   285: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   288: ldc_w 915
    //   291: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   294: ifeq +19 -> 313
    //   297: aload_3
    //   298: ldc_w 930
    //   301: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   304: checkcast 915	java/lang/Boolean
    //   307: invokevirtual 918	java/lang/Boolean:booleanValue	()Z
    //   310: putstatic 206	com/kochava/android/tracker/b:V	Z
    //   313: aload_0
    //   314: invokespecial 932	com/kochava/android/tracker/b:U	()V
    //   317: new 305	java/lang/StringBuilder
    //   320: dup
    //   321: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   324: ldc_w 934
    //   327: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   333: ldc_w 936
    //   336: ldc -95
    //   338: invokeinterface 381 3 0
    //   343: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   352: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   355: ldc_w 936
    //   358: ldc -95
    //   360: invokeinterface 381 3 0
    //   365: ldc -95
    //   367: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   370: ifne +26 -> 396
    //   373: new 249	org/json/JSONArray
    //   376: dup
    //   377: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   380: ldc_w 936
    //   383: ldc -95
    //   385: invokeinterface 381 3 0
    //   390: invokespecial 937	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   393: putstatic 252	com/kochava/android/tracker/b:an	Lorg/json/JSONArray;
    //   396: getstatic 330	android/os/Build$VERSION:SDK_INT	I
    //   399: bipush 14
    //   401: if_icmplt +1767 -> 2168
    //   404: getstatic 167	com/kochava/android/tracker/b:b	Z
    //   407: ifne +1761 -> 2168
    //   410: new 305	java/lang/StringBuilder
    //   413: dup
    //   414: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   417: ldc_w 939
    //   420: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: getstatic 167	com/kochava/android/tracker/b:b	Z
    //   426: invokevirtual 942	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   429: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   432: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   435: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   438: checkcast 944	android/app/Application
    //   441: new 64	com/kochava/android/tracker/b$c
    //   444: dup
    //   445: aload_0
    //   446: invokespecial 945	com/kochava/android/tracker/b$c:<init>	(Lcom/kochava/android/tracker/b;)V
    //   449: invokevirtual 949	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   452: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   455: new 67	com/kochava/android/tracker/b$d
    //   458: dup
    //   459: aload_0
    //   460: invokespecial 950	com/kochava/android/tracker/b$d:<init>	(Lcom/kochava/android/tracker/b;)V
    //   463: invokevirtual 954	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   466: iconst_1
    //   467: putstatic 956	com/kochava/android/tracker/b$a:a	Z
    //   470: iconst_1
    //   471: putstatic 957	com/kochava/android/tracker/b$a:b	Z
    //   474: new 28	com/kochava/android/tracker/b$17
    //   477: dup
    //   478: aload_0
    //   479: invokespecial 958	com/kochava/android/tracker/b$17:<init>	(Lcom/kochava/android/tracker/b;)V
    //   482: invokevirtual 963	java/lang/Thread:start	()V
    //   485: getstatic 189	com/kochava/android/tracker/b:I	Z
    //   488: ifne +14 -> 502
    //   491: new 30	com/kochava/android/tracker/b$18
    //   494: dup
    //   495: aload_0
    //   496: invokespecial 964	com/kochava/android/tracker/b$18:<init>	(Lcom/kochava/android/tracker/b;)V
    //   499: invokevirtual 963	java/lang/Thread:start	()V
    //   502: aload_0
    //   503: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   506: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   509: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   512: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   515: iconst_0
    //   516: invokevirtual 977	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   519: getfield 982	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   522: putfield 466	com/kochava/android/tracker/b:r	Ljava/lang/String;
    //   525: aload_0
    //   526: new 389	org/json/JSONObject
    //   529: dup
    //   530: invokespecial 983	org/json/JSONObject:<init>	()V
    //   533: putfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   536: aload_0
    //   537: new 389	org/json/JSONObject
    //   540: dup
    //   541: invokespecial 983	org/json/JSONObject:<init>	()V
    //   544: putfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   547: aload_0
    //   548: new 389	org/json/JSONObject
    //   551: dup
    //   552: invokespecial 983	org/json/JSONObject:<init>	()V
    //   555: putfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   558: aconst_null
    //   559: astore 13
    //   561: aconst_null
    //   562: astore 6
    //   564: aconst_null
    //   565: astore 9
    //   567: aconst_null
    //   568: astore 7
    //   570: ldc_w 991
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
    //   598: ldc_w 993
    //   601: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   604: ifnull +38 -> 642
    //   607: aload 6
    //   609: astore 5
    //   611: aload_3
    //   612: ldc_w 993
    //   615: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   618: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   621: ldc_w 383
    //   624: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   627: ifeq +15 -> 642
    //   630: aload_3
    //   631: ldc_w 993
    //   634: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   637: checkcast 383	java/lang/String
    //   640: astore 5
    //   642: aload 7
    //   644: astore 6
    //   646: aload_3
    //   647: ldc_w 995
    //   650: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   653: ifnull +38 -> 691
    //   656: aload 7
    //   658: astore 6
    //   660: aload_3
    //   661: ldc_w 995
    //   664: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   667: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   670: ldc_w 383
    //   673: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   676: ifeq +15 -> 691
    //   679: aload_3
    //   680: ldc_w 995
    //   683: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   686: checkcast 383	java/lang/String
    //   689: astore 6
    //   691: aload_1
    //   692: astore 7
    //   694: aload_3
    //   695: ldc_w 997
    //   698: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   701: ifnull +37 -> 738
    //   704: aload_1
    //   705: astore 7
    //   707: aload_3
    //   708: ldc_w 997
    //   711: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   714: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   717: ldc_w 383
    //   720: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   723: ifeq +15 -> 738
    //   726: aload_3
    //   727: ldc_w 997
    //   730: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   733: checkcast 383	java/lang/String
    //   736: astore 7
    //   738: aload 8
    //   740: astore_1
    //   741: aload_3
    //   742: ldc_w 999
    //   745: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   748: ifnull +33 -> 781
    //   751: aload_3
    //   752: ldc_w 999
    //   755: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   758: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   761: ldc_w 383
    //   764: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   767: ifeq +1429 -> 2196
    //   770: aload_3
    //   771: ldc_w 999
    //   774: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   777: checkcast 383	java/lang/String
    //   780: astore_1
    //   781: aload_3
    //   782: ldc_w 1001
    //   785: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   788: ifnull +34 -> 822
    //   791: aload_3
    //   792: ldc_w 1001
    //   795: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   798: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   801: ldc_w 383
    //   804: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   807: ifeq +15 -> 822
    //   810: aload_3
    //   811: ldc_w 1001
    //   814: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   817: checkcast 383	java/lang/String
    //   820: astore 8
    //   822: aload 14
    //   824: astore 8
    //   826: aload_3
    //   827: ldc_w 1003
    //   830: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   833: ifnull +38 -> 871
    //   836: aload 14
    //   838: astore 8
    //   840: aload_3
    //   841: ldc_w 1003
    //   844: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   847: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   850: ldc_w 383
    //   853: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   856: ifeq +15 -> 871
    //   859: aload_3
    //   860: ldc_w 1003
    //   863: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   866: checkcast 383	java/lang/String
    //   869: astore 8
    //   871: aload_3
    //   872: ldc_w 1005
    //   875: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   878: ifnull +39 -> 917
    //   881: aload_3
    //   882: ldc_w 1005
    //   885: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   888: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   891: ldc_w 915
    //   894: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   897: ifeq +20 -> 917
    //   900: aload_0
    //   901: aload_3
    //   902: ldc_w 1005
    //   905: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   908: checkcast 915	java/lang/Boolean
    //   911: invokevirtual 918	java/lang/Boolean:booleanValue	()Z
    //   914: putfield 263	com/kochava/android/tracker/b:F	Z
    //   917: aload_3
    //   918: ldc_w 1007
    //   921: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   924: ifnull +115 -> 1039
    //   927: aload_3
    //   928: ldc_w 1007
    //   931: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   934: instanceof 909
    //   937: ifeq +102 -> 1039
    //   940: aload_3
    //   941: ldc_w 1007
    //   944: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   947: checkcast 909	java/util/HashMap
    //   950: putstatic 1009	com/kochava/android/tracker/b:h	Ljava/util/Map;
    //   953: new 389	org/json/JSONObject
    //   956: dup
    //   957: invokespecial 983	org/json/JSONObject:<init>	()V
    //   960: putstatic 1011	com/kochava/android/tracker/b:i	Lorg/json/JSONObject;
    //   963: getstatic 1009	com/kochava/android/tracker/b:h	Ljava/util/Map;
    //   966: invokeinterface 1015 1 0
    //   971: invokeinterface 1021 1 0
    //   976: astore 9
    //   978: aload 9
    //   980: invokeinterface 1026 1 0
    //   985: ifeq +54 -> 1039
    //   988: aload 9
    //   990: invokeinterface 1030 1 0
    //   995: checkcast 1032	java/util/Map$Entry
    //   998: astore 10
    //   1000: getstatic 1011	com/kochava/android/tracker/b:i	Lorg/json/JSONObject;
    //   1003: aload 10
    //   1005: invokeinterface 1035 1 0
    //   1010: checkcast 383	java/lang/String
    //   1013: aload 10
    //   1015: invokeinterface 1038 1 0
    //   1020: checkcast 383	java/lang/String
    //   1023: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1026: pop
    //   1027: aload 9
    //   1029: invokeinterface 1045 1 0
    //   1034: goto -56 -> 978
    //   1037: astore 9
    //   1039: aload_3
    //   1040: ldc_w 1047
    //   1043: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1046: ifnull +36 -> 1082
    //   1049: aload_3
    //   1050: ldc_w 1047
    //   1053: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1056: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1059: ldc_w 383
    //   1062: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1065: ifeq +17 -> 1082
    //   1068: aload_0
    //   1069: aload_3
    //   1070: ldc_w 1047
    //   1073: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1076: checkcast 383	java/lang/String
    //   1079: putfield 1049	com/kochava/android/tracker/b:t	Ljava/lang/String;
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
    //   1102: ldc_w 1051
    //   1105: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1131: ldc_w 1051
    //   1134: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1137: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1140: ldc_w 1053
    //   1143: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1146: ifeq +77 -> 1223
    //   1149: aload_3
    //   1150: ldc_w 1051
    //   1153: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1156: checkcast 1053	java/lang/Integer
    //   1159: invokevirtual 1056	java/lang/Integer:intValue	()I
    //   1162: istore 4
    //   1164: iload 4
    //   1166: iconst_1
    //   1167: if_icmpge +1077 -> 2244
    //   1170: new 305	java/lang/StringBuilder
    //   1173: dup
    //   1174: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1177: ldc_w 1058
    //   1180: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: iload 4
    //   1185: invokevirtual 698	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1188: ldc_w 1060
    //   1191: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1197: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1200: iconst_1
    //   1201: putstatic 172	com/kochava/android/tracker/b:l	Z
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
    //   1230: invokevirtual 576	java/lang/String:trim	()Ljava/lang/String;
    //   1233: invokevirtual 1063	java/lang/String:length	()I
    //   1236: ifeq +8 -> 1244
    //   1239: aload 11
    //   1241: putstatic 163	com/kochava/android/tracker/b:j	Ljava/lang/String;
    //   1244: aload 10
    //   1246: ifnull +18 -> 1264
    //   1249: aload 10
    //   1251: ldc_w 398
    //   1254: invokevirtual 1066	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1257: ifeq +7 -> 1264
    //   1260: iconst_1
    //   1261: putstatic 220	com/kochava/android/tracker/b:ad	Z
    //   1264: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1267: ldc_w 1068
    //   1270: iconst_0
    //   1271: invokevirtual 510	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1274: putstatic 449	com/kochava/android/tracker/b:ah	Landroid/content/SharedPreferences;
    //   1277: aload 9
    //   1279: ifnull +1057 -> 2336
    //   1282: aload 9
    //   1284: invokevirtual 576	java/lang/String:trim	()Ljava/lang/String;
    //   1287: invokevirtual 1063	java/lang/String:length	()I
    //   1290: ifeq +1046 -> 2336
    //   1293: aload 9
    //   1295: putstatic 1070	com/kochava/android/tracker/b:u	Ljava/lang/String;
    //   1298: aload_0
    //   1299: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   1302: ldc_w 995
    //   1305: aload 9
    //   1307: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1310: pop
    //   1311: aload_0
    //   1312: getfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   1315: ldc_w 995
    //   1318: aload 9
    //   1320: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1323: pop
    //   1324: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1327: ldc_w 514
    //   1330: ldc -95
    //   1332: invokeinterface 381 3 0
    //   1337: ldc -95
    //   1339: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1342: ifeq +26 -> 1368
    //   1345: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1348: invokeinterface 492 1 0
    //   1353: ldc_w 514
    //   1356: aload 9
    //   1358: invokeinterface 498 3 0
    //   1363: invokeinterface 501 1 0
    //   1368: aload_0
    //   1369: aload 12
    //   1371: invokespecial 1072	com/kochava/android/tracker/b:h	(Ljava/lang/String;)V
    //   1374: aload_0
    //   1375: getfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   1378: ldc_w 1074
    //   1381: aload_0
    //   1382: invokespecial 1076	com/kochava/android/tracker/b:S	()Ljava/lang/String;
    //   1385: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1388: pop
    //   1389: aload_0
    //   1390: getfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   1393: ldc_w 1078
    //   1396: ldc_w 1080
    //   1399: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload_0
    //   1404: getfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   1407: ldc_w 1082
    //   1410: ldc_w 1084
    //   1413: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1416: pop
    //   1417: aload_0
    //   1418: getfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   1421: ldc_w 997
    //   1424: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1427: ldc_w 997
    //   1430: ldc_w 991
    //   1433: invokeinterface 381 3 0
    //   1438: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1441: pop
    //   1442: aload_0
    //   1443: getfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   1446: ldc_w 997
    //   1449: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1452: ldc_w 997
    //   1455: ldc_w 991
    //   1458: invokeinterface 381 3 0
    //   1463: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1466: pop
    //   1467: aload_0
    //   1468: getfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   1471: ldc_w 1082
    //   1474: ldc_w 1084
    //   1477: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1480: pop
    //   1481: aload_0
    //   1482: getfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   1485: ldc_w 997
    //   1488: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1491: ldc_w 997
    //   1494: ldc_w 991
    //   1497: invokeinterface 381 3 0
    //   1502: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1505: pop
    //   1506: aload_0
    //   1507: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   1510: ldc_w 1086
    //   1513: new 305	java/lang/StringBuilder
    //   1516: dup
    //   1517: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1520: ldc_w 1088
    //   1523: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1526: getstatic 165	com/kochava/android/tracker/b:a	Ljava/lang/String;
    //   1529: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1532: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1535: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1538: pop
    //   1539: aload_0
    //   1540: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   1543: ldc_w 1090
    //   1546: ldc_w 1092
    //   1549: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1552: pop
    //   1553: aload_0
    //   1554: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   1557: ldc_w 1094
    //   1560: aload_0
    //   1561: getfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   1564: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1567: pop
    //   1568: aload_0
    //   1569: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   1572: ldc_w 1096
    //   1575: aload_0
    //   1576: getfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   1579: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1582: pop
    //   1583: aload_0
    //   1584: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   1587: ldc_w 1098
    //   1590: ldc_w 1100
    //   1593: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1596: pop
    //   1597: invokestatic 358	java/lang/System:currentTimeMillis	()J
    //   1600: ldc2_w 359
    //   1603: ldiv
    //   1604: putstatic 197	com/kochava/android/tracker/b:M	J
    //   1607: iconst_0
    //   1608: istore 4
    //   1610: ldc -95
    //   1612: astore_3
    //   1613: new 1102	android/content/ComponentName
    //   1616: dup
    //   1617: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1620: ldc_w 1104
    //   1623: invokespecial 1107	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1626: astore_1
    //   1627: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1630: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1633: aload_1
    //   1634: iconst_0
    //   1635: invokevirtual 1111	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1638: pop
    //   1639: ldc_w 1113
    //   1642: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1645: aload_3
    //   1646: astore_1
    //   1647: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1650: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1653: ldc_w 1115
    //   1656: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1659: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1662: invokevirtual 1118	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1665: ifge +27 -> 1692
    //   1668: iconst_1
    //   1669: istore 4
    //   1671: new 305	java/lang/StringBuilder
    //   1674: dup
    //   1675: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1678: aload_3
    //   1679: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1682: ldc_w 1120
    //   1685: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1688: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1691: astore_1
    //   1692: aload_1
    //   1693: astore_3
    //   1694: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1697: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1700: ldc_w 1122
    //   1703: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1706: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1709: invokevirtual 1118	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1712: ifge +27 -> 1739
    //   1715: iconst_1
    //   1716: istore 4
    //   1718: new 305	java/lang/StringBuilder
    //   1721: dup
    //   1722: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1725: aload_1
    //   1726: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1729: ldc_w 1124
    //   1732: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1735: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1738: astore_3
    //   1739: aload_3
    //   1740: astore_1
    //   1741: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1744: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1747: ldc_w 1126
    //   1750: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1753: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1756: invokevirtual 1118	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1759: ifge +27 -> 1786
    //   1762: iconst_1
    //   1763: istore 4
    //   1765: new 305	java/lang/StringBuilder
    //   1768: dup
    //   1769: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1772: aload_3
    //   1773: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1776: ldc_w 1128
    //   1779: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1782: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1785: astore_1
    //   1786: iload 4
    //   1788: ifeq +13 -> 1801
    //   1791: ldc_w 1130
    //   1794: invokestatic 1132	com/kochava/android/a/b:c	(Ljava/lang/String;)V
    //   1797: aload_1
    //   1798: invokestatic 1132	com/kochava/android/a/b:c	(Ljava/lang/String;)V
    //   1801: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1804: ldc_w 600
    //   1807: ldc -95
    //   1809: invokeinterface 381 3 0
    //   1814: ldc -95
    //   1816: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1819: ifeq +28 -> 1847
    //   1822: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   1825: invokeinterface 492 1 0
    //   1830: ldc_w 600
    //   1833: aload_0
    //   1834: invokespecial 1134	com/kochava/android/tracker/b:X	()Ljava/lang/String;
    //   1837: invokeinterface 498 3 0
    //   1842: invokeinterface 501 1 0
    //   1847: aload_0
    //   1848: ldc_w 1136
    //   1851: putfield 460	com/kochava/android/tracker/b:o	Ljava/lang/String;
    //   1854: aload_0
    //   1855: ldc_w 1138
    //   1858: putfield 464	com/kochava/android/tracker/b:p	Ljava/lang/String;
    //   1861: aload_0
    //   1862: ldc -95
    //   1864: putfield 1140	com/kochava/android/tracker/b:q	Ljava/lang/String;
    //   1867: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1870: invokevirtual 893	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1873: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1876: astore_3
    //   1877: aload_3
    //   1878: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1881: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1884: iconst_0
    //   1885: invokevirtual 1144	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1888: astore_1
    //   1889: aload_1
    //   1890: ifnull +730 -> 2620
    //   1893: aload_3
    //   1894: aload_1
    //   1895: invokevirtual 1148	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1898: astore_1
    //   1899: aload_0
    //   1900: aload_1
    //   1901: checkcast 383	java/lang/String
    //   1904: checkcast 383	java/lang/String
    //   1907: putfield 460	com/kochava/android/tracker/b:o	Ljava/lang/String;
    //   1910: new 305	java/lang/StringBuilder
    //   1913: dup
    //   1914: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1917: ldc_w 1150
    //   1920: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1923: aload_0
    //   1924: getfield 460	com/kochava/android/tracker/b:o	Ljava/lang/String;
    //   1927: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1930: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1933: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1936: aload_0
    //   1937: new 305	java/lang/StringBuilder
    //   1940: dup
    //   1941: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1944: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1947: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1950: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   1953: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1956: iconst_0
    //   1957: invokevirtual 977	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1960: getfield 1153	android/content/pm/PackageInfo:versionCode	I
    //   1963: invokevirtual 698	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1966: ldc -95
    //   1968: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1971: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1974: putfield 464	com/kochava/android/tracker/b:p	Ljava/lang/String;
    //   1977: new 305	java/lang/StringBuilder
    //   1980: dup
    //   1981: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   1984: ldc_w 1155
    //   1987: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1990: aload_0
    //   1991: getfield 464	com/kochava/android/tracker/b:p	Ljava/lang/String;
    //   1994: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1997: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2000: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2003: aload_0
    //   2004: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   2007: invokevirtual 968	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2010: getstatic 364	com/kochava/android/tracker/b:c	Landroid/content/Context;
    //   2013: invokevirtual 971	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2016: iconst_0
    //   2017: invokevirtual 977	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2020: getfield 1158	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2023: putfield 1140	com/kochava/android/tracker/b:q	Ljava/lang/String;
    //   2026: new 305	java/lang/StringBuilder
    //   2029: dup
    //   2030: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2033: ldc_w 1160
    //   2036: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2039: aload_0
    //   2040: getfield 1140	com/kochava/android/tracker/b:q	Ljava/lang/String;
    //   2043: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2046: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2049: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2052: new 32	com/kochava/android/tracker/b$19
    //   2055: dup
    //   2056: aload_0
    //   2057: invokespecial 1161	com/kochava/android/tracker/b$19:<init>	(Lcom/kochava/android/tracker/b;)V
    //   2060: invokevirtual 963	java/lang/Thread:start	()V
    //   2063: aload_0
    //   2064: invokestatic 312	com/kochava/android/tracker/b:T	()Ljava/lang/String;
    //   2067: putfield 1163	com/kochava/android/tracker/b:s	Ljava/lang/String;
    //   2070: aload_0
    //   2071: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   2074: ldc_w 1165
    //   2077: invokestatic 312	com/kochava/android/tracker/b:T	()Ljava/lang/String;
    //   2080: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2083: pop
    //   2084: getstatic 234	com/kochava/android/tracker/b:ak	Ljava/util/concurrent/ScheduledExecutorService;
    //   2087: aload_0
    //   2088: getfield 270	com/kochava/android/tracker/b:ao	Ljava/lang/Runnable;
    //   2091: ldc2_w 1166
    //   2094: getstatic 879	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2097: invokeinterface 884 5 0
    //   2102: pop
    //   2103: aload_0
    //   2104: ldc_w 1100
    //   2107: invokevirtual 1168	com/kochava/android/tracker/b:a	(Ljava/lang/String;)V
    //   2110: return
    //   2111: astore_1
    //   2112: ldc_w 1170
    //   2115: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2118: aload_1
    //   2119: invokevirtual 1171	java/lang/Exception:printStackTrace	()V
    //   2122: iconst_1
    //   2123: putstatic 214	com/kochava/android/tracker/b:aa	Z
    //   2126: return
    //   2127: ldc_w 1173
    //   2130: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2133: iconst_1
    //   2134: putstatic 214	com/kochava/android/tracker/b:aa	Z
    //   2137: return
    //   2138: astore_1
    //   2139: new 305	java/lang/StringBuilder
    //   2142: dup
    //   2143: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2146: ldc_w 1175
    //   2149: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2152: aload_1
    //   2153: invokevirtual 765	java/lang/Exception:toString	()Ljava/lang/String;
    //   2156: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2159: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2162: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2165: goto -1769 -> 396
    //   2168: new 305	java/lang/StringBuilder
    //   2171: dup
    //   2172: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2175: ldc_w 1177
    //   2178: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2181: getstatic 167	com/kochava/android/tracker/b:b	Z
    //   2184: invokevirtual 942	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2187: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2190: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2193: goto -1719 -> 474
    //   2196: aload 8
    //   2198: astore_1
    //   2199: aload_3
    //   2200: ldc_w 999
    //   2203: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2206: invokevirtual 673	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2209: ldc_w 915
    //   2212: invokevirtual 676	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2215: ifeq -1434 -> 781
    //   2218: aload 8
    //   2220: astore_1
    //   2221: aload_3
    //   2222: ldc_w 999
    //   2225: invokevirtual 913	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2228: checkcast 915	java/lang/Boolean
    //   2231: invokevirtual 918	java/lang/Boolean:booleanValue	()Z
    //   2234: ifeq -1453 -> 781
    //   2237: ldc_w 398
    //   2240: astore_1
    //   2241: goto -1460 -> 781
    //   2244: iload 4
    //   2246: sipush 360
    //   2249: if_icmple +42 -> 2291
    //   2252: new 305	java/lang/StringBuilder
    //   2255: dup
    //   2256: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2259: ldc_w 1058
    //   2262: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2265: iload 4
    //   2267: invokevirtual 698	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2270: ldc_w 1179
    //   2273: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2276: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2279: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2282: ldc_w 1180
    //   2285: putstatic 170	com/kochava/android/tracker/b:k	I
    //   2288: goto -1088 -> 1200
    //   2291: iload 4
    //   2293: bipush 60
    //   2295: imul
    //   2296: sipush 1000
    //   2299: imul
    //   2300: putstatic 170	com/kochava/android/tracker/b:k	I
    //   2303: new 305	java/lang/StringBuilder
    //   2306: dup
    //   2307: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2310: ldc_w 1182
    //   2313: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2316: iload 4
    //   2318: invokevirtual 698	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2321: ldc_w 1184
    //   2324: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2327: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2330: invokestatic 341	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2333: goto -1133 -> 1200
    //   2336: aload 13
    //   2338: ifnull +178 -> 2516
    //   2341: aload 13
    //   2343: invokevirtual 576	java/lang/String:trim	()Ljava/lang/String;
    //   2346: invokevirtual 1063	java/lang/String:length	()I
    //   2349: ifeq +167 -> 2516
    //   2352: aload_0
    //   2353: getfield 985	com/kochava/android/tracker/b:d	Lorg/json/JSONObject;
    //   2356: ldc_w 993
    //   2359: aload 13
    //   2361: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2364: pop
    //   2365: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   2368: ldc_w 514
    //   2371: ldc -95
    //   2373: invokeinterface 381 3 0
    //   2378: ldc -95
    //   2380: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2383: ifeq +26 -> 2409
    //   2386: getstatic 373	com/kochava/android/tracker/b:ag	Landroid/content/SharedPreferences;
    //   2389: invokeinterface 492 1 0
    //   2394: ldc_w 514
    //   2397: aload 13
    //   2399: invokeinterface 498 3 0
    //   2404: invokeinterface 501 1 0
    //   2409: aload 9
    //   2411: ifnull +78 -> 2489
    //   2414: aload 9
    //   2416: invokevirtual 576	java/lang/String:trim	()Ljava/lang/String;
    //   2419: invokevirtual 1063	java/lang/String:length	()I
    //   2422: ifeq +67 -> 2489
    //   2425: aload 9
    //   2427: putstatic 1070	com/kochava/android/tracker/b:u	Ljava/lang/String;
    //   2430: aload_0
    //   2431: getfield 987	com/kochava/android/tracker/b:e	Lorg/json/JSONObject;
    //   2434: ldc_w 995
    //   2437: aload 9
    //   2439: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2442: pop
    //   2443: aload_0
    //   2444: getfield 989	com/kochava/android/tracker/b:f	Lorg/json/JSONObject;
    //   2447: ldc_w 995
    //   2450: aload 9
    //   2452: invokevirtual 1042	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2455: pop
    //   2456: goto -1088 -> 1368
    //   2459: astore_1
    //   2460: new 305	java/lang/StringBuilder
    //   2463: dup
    //   2464: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2467: ldc_w 1186
    //   2470: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2473: aload_1
    //   2474: invokevirtual 1187	org/json/JSONException:toString	()Ljava/lang/String;
    //   2477: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2480: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2483: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2486: goto -889 -> 1597
    //   2489: new 305	java/lang/StringBuilder
    //   2492: dup
    //   2493: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2496: ldc_w 1189
    //   2499: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2502: aload 13
    //   2504: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2507: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2510: putstatic 1070	com/kochava/android/tracker/b:u	Ljava/lang/String;
    //   2513: goto -1145 -> 1368
    //   2516: ldc_w 1191
    //   2519: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2522: iconst_1
    //   2523: putstatic 214	com/kochava/android/tracker/b:aa	Z
    //   2526: return
    //   2527: astore_1
    //   2528: iconst_1
    //   2529: istore 4
    //   2531: new 305	java/lang/StringBuilder
    //   2534: dup
    //   2535: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2538: ldc -95
    //   2540: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2543: ldc_w 1193
    //   2546: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2549: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2552: astore_3
    //   2553: goto -908 -> 1645
    //   2556: astore 5
    //   2558: aconst_null
    //   2559: astore_1
    //   2560: new 305	java/lang/StringBuilder
    //   2563: dup
    //   2564: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2567: ldc_w 1195
    //   2570: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2573: aload 5
    //   2575: invokevirtual 1196	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2578: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2581: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2584: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2587: goto -698 -> 1889
    //   2590: astore_1
    //   2591: new 305	java/lang/StringBuilder
    //   2594: dup
    //   2595: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2598: ldc_w 1195
    //   2601: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2604: aload_1
    //   2605: invokevirtual 765	java/lang/Exception:toString	()Ljava/lang/String;
    //   2608: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2611: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2614: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2617: goto -681 -> 1936
    //   2620: ldc_w 1198
    //   2623: astore_1
    //   2624: goto -725 -> 1899
    //   2627: astore_1
    //   2628: new 305	java/lang/StringBuilder
    //   2631: dup
    //   2632: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2635: ldc_w 1200
    //   2638: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2641: aload_1
    //   2642: invokevirtual 765	java/lang/Exception:toString	()Ljava/lang/String;
    //   2645: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2648: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2651: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2654: goto -651 -> 2003
    //   2657: astore_1
    //   2658: new 305	java/lang/StringBuilder
    //   2661: dup
    //   2662: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   2665: ldc_w 1202
    //   2668: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2671: aload_1
    //   2672: invokevirtual 765	java/lang/Exception:toString	()Ljava/lang/String;
    //   2675: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2678: invokevirtual 317	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2681: invokestatic 337	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2684: goto -632 -> 2052
    //   2687: astore_1
    //   2688: getstatic 414	com/kochava/android/tracker/c:b	Z
    //   2691: ifeq -607 -> 2084
    //   2694: aload_1
    //   2695: invokevirtual 417	org/json/JSONException:printStackTrace	()V
    //   2698: goto -614 -> 2084
    //   2701: astore_1
    //   2702: goto -2177 -> 525
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2705	0	this	b
    //   0	2705	1	paramContext	Context
    //   0	2705	2	paramBoolean	boolean
    //   0	2705	3	paramHashMap	HashMap<String, Object>
    //   1162	1368	4	i1	int
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
  
  public static void a(Handler paramHandler)
  {
    aj = paramHandler;
  }
  
  private void a(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    String str = (String)paramMap1.get("event_name");
    if (str != null)
    {
      int i3 = 0;
      int i1 = 0;
      for (;;)
      {
        int i2 = i3;
        if (i1 < an.length()) {}
        try
        {
          boolean bool = an.get(i1).equals(paramMap1.get("event_name"));
          if (bool)
          {
            i2 = 1;
            if (i2 == 0) {
              break;
            }
            com.kochava.android.a.b.a("fireEvent - Events under the key \"" + str + "\" are blocked!");
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          i1 += 1;
        }
      }
      b(paramString, paramMap1, paramMap2);
      return;
    }
    b(paramString, paramMap1, paramMap2);
  }
  
  public static void a(boolean paramBoolean)
  {
    c.a = paramBoolean;
    com.kochava.android.a.b.a("enableDebug to " + paramBoolean);
  }
  
  private String aa()
  {
    Object localObject1 = "";
    if (c.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0)
    {
      Account[] arrayOfAccount = AccountManager.get(c).getAccounts();
      int i2 = arrayOfAccount.length;
      int i1 = 0;
      while (i1 < i2)
      {
        Account localAccount = arrayOfAccount[i1];
        Object localObject2 = localObject1;
        if (j(localAccount.name))
        {
          localObject2 = localAccount.name.toLowerCase();
          localObject2 = (String)localObject1 + (String)localObject2 + ",";
        }
        i1 += 1;
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
      com.kochava.android.a.b.a("****NOTICE**** Gathering of emails was whitelisted, but android.permission.GET_ACCOUNTS declaration was missing from manifest.");
    }
  }
  
  private static boolean ab()
  {
    long l3 = d.b * 60 * 1000;
    long l2 = 0L;
    long l1 = l2;
    if (ag != null)
    {
      l1 = l2;
      if (ag.getLong("kochava_old_loc_timestamp", 0L) != 0L) {
        l1 = ag.getLong("kochava_old_loc_timestamp", 0L);
      }
    }
    if (l1 == 0L) {}
    while (System.currentTimeMillis() - l1 >= l3) {
      return true;
    }
    return false;
  }
  
  public static String b()
  {
    try
    {
      String str = T();
      if (str != null) {
        return str;
      }
    }
    catch (Exception localException)
    {
      com.kochava.android.a.b.b(localException.toString());
    }
    return "";
  }
  
  private static JSONObject b(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        paramJSONObject.put("usertime", System.currentTimeMillis() / 1000L + "");
        paramJSONObject.put("uptime", System.currentTimeMillis() / 1000L - M + "");
        if (L != 0L) {
          paramJSONObject.put("updelta", System.currentTimeMillis() / 1000L - L + "");
        }
        for (;;)
        {
          L = System.currentTimeMillis() / 1000L;
          ag = c.getSharedPreferences("initPrefs", 0);
          if (ag.getString("mylat", "").equals("")) {
            break;
          }
          paramJSONObject.put("geo_lat", ag.getString("mylat", ""));
          paramJSONObject.put("geo_lon", ag.getString("mylong", ""));
          return paramJSONObject;
          paramJSONObject.put("updelta", "0");
        }
        return paramJSONObject;
      }
      catch (Exception localException)
      {
        com.kochava.android.a.b.b("Error adding time properties to a JSON object " + localException);
      }
    }
  }
  
  private static void b(Exception paramException)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          b.h(true);
          Object localObject2 = this.a.getMessage();
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("message", localObject2);
          ((JSONObject)localObject1).put("os_version", b.w());
          ((JSONObject)localObject1).put("device", b.H() + "-" + b.I());
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("kochava_device_id", b.r());
          ((JSONObject)localObject2).put("action", "error");
          ((JSONObject)localObject2).put("data", localObject1);
          ((JSONObject)localObject2).put("kochava_app_id", b.q());
          ((JSONObject)localObject2).put("sdk_version", "Android20160615" + b.a);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          com.kochava.android.a.b.a("https log - posting to " + "http://" + b.e() + "/track/kvTracker.php");
          Object localObject3 = new URL("http://" + b.e() + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject2).toString();
          com.kochava.android.a.b.a("https failure data:" + (String)localObject1);
          localObject2 = (HttpURLConnection)((URL)localObject3).openConnection();
          ((HttpURLConnection)localObject2).setRequestProperty("User-Agent", b.d().getString("useragent", ""));
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
          com.kochava.android.a.b.a("Grabbing Result...");
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
          com.kochava.android.a.b.b("httpsFail " + localException);
          return;
        }
        String str;
        com.kochava.android.a.b.a("Result: " + str);
      }
    }.start();
  }
  
  private void b(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    if ((!paramString.equals("initial")) && (!V) && (X) && (!W))
    {
      W = true;
      this.Y.schedule(new TimerTask()
      {
        public void run()
        {
          if (b.A()) {
            b.c();
          }
          b.i(false);
        }
      }, 60000L);
    }
    com.kochava.android.a.b.a("FIRE EVENT*** action:" + paramString);
    com.kochava.android.a.b.a("FIRE EVENT*** properties:" + paramMap1);
    JSONObject localJSONObject1 = new JSONObject();
    label1083:
    label1120:
    int i1;
    do
    {
      JSONObject localJSONObject2;
      for (;;)
      {
        try
        {
          localJSONObject1.put("kochava_app_id", u);
          localJSONObject1.put("kochava_device_id", T());
          localJSONObject1.put("action", paramString);
          localJSONObject1.put("dev_id_strategy", w);
          localJSONObject1.put("last_post_time", 0);
          ag = c.getSharedPreferences("initPrefs", 0);
          localJSONObject1.put("currency", ag.getString("currency", "USD"));
          localJSONObject2 = b(new JSONObject());
          if (paramString.equals("initial"))
          {
            com.kochava.android.a.b.a("Event is initial, or initial did not get que'd on first load");
            try
            {
              if (((Boolean)am.get("affinity_group")).booleanValue())
              {
                paramString = new JSONArray();
                localObject = P.iterator();
                if (((Iterator)localObject).hasNext())
                {
                  b localB = (b)((Iterator)localObject).next();
                  JSONObject localJSONObject3 = new JSONObject();
                  localJSONObject3.put("app_name", localB.a);
                  localJSONObject3.put("mtime", localB.b);
                  localJSONObject3.put("utime", localB.c);
                  paramString.put(localJSONObject3);
                  continue;
                }
              }
              if (O == null) {
                break label1120;
              }
            }
            catch (JSONException paramString)
            {
              if (c.b) {
                paramString.printStackTrace();
              }
              com.kochava.android.a.b.b("event " + paramString);
              return;
              localJSONObject2.put("affinity_group", paramString);
              localJSONObject1.put("sdk_version", "Android20160615" + a);
              if (((Boolean)am.get("volume")).booleanValue()) {
                localJSONObject2.put("volume", Y());
              }
              if (((Boolean)am.get("bssid")).booleanValue()) {
                localJSONObject2.put("bssid", B);
              }
              if (((Boolean)am.get("carrier_name")).booleanValue()) {
                localJSONObject2.put("carrier_name", C);
              }
              if (((Boolean)am.get("adid")).booleanValue()) {
                localJSONObject2.put("adid", A);
              }
              localJSONObject2.put("device", O() + "-" + N());
              if (((Boolean)am.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_h", this.y);
              }
              if (((Boolean)am.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_w", this.z);
              }
              localJSONObject2.put("package_name", S());
              localJSONObject2.put("app_version", R());
              if (!this.q.equals("")) {
                localJSONObject2.put("app_short_string", this.q);
              }
              if (((Boolean)am.get("android_id")).booleanValue()) {
                localJSONObject2.put("android_id", this.v);
              }
              localJSONObject2.put("os_version", P());
              localJSONObject2.put("app_limit_tracking", this.F);
              localJSONObject2.put("device_limit_tracking", G);
              paramString = new JSONObject();
              if (ac)
              {
                localObject = aa();
                if (!((String)localObject).equals("[]")) {
                  paramString.put("email", localObject);
                }
              }
              if (paramString.length() > 0) {
                localJSONObject2.put("ids", paramString);
              }
              if (i != null) {
                localJSONObject2.put("identity_link", i);
              }
              if ((this.t != null) && (!this.t.isEmpty())) {
                localJSONObject2.put("clickData", this.t);
              }
              if (((Boolean)am.get("fb_attribution_id")).booleanValue())
              {
                this.x = a(c.getContentResolver());
                if (this.x != null) {
                  break label1083;
                }
                localJSONObject2.put("fb_attribution_id", "");
              }
              paramString = (WindowManager)c.getSystemService("window");
              localObject = new DisplayMetrics();
              paramString.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
              this.af = localJSONObject2;
              this.ae = localJSONObject1;
              com.kochava.android.a.b.a("Initial Event, saving until next event. ");
              return;
            }
            catch (Exception paramString)
            {
              com.kochava.android.a.b.b("Error during fireEvent - Please review stack trace");
              if (c.b) {
                paramString.printStackTrace();
              }
            }
          }
          paramString = O.entrySet().iterator();
          if (!paramString.hasNext()) {
            break;
          }
          Object localObject = (Map.Entry)paramString.next();
          localJSONObject2.put((String)((Map.Entry)localObject).getKey(), ((Map.Entry)localObject).getValue());
          continue;
          localJSONObject2.put("fb_attribution_id", this.x);
        }
        catch (JSONException paramString)
        {
          if (c.b) {
            paramString.printStackTrace();
          }
          com.kochava.android.a.b.b("event " + paramString);
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
      com.kochava.android.a.b.a("fireEvent with properties: " + localJSONObject1);
      k(true);
      i1 = Q.a(localJSONObject1, false, false);
      Z = System.currentTimeMillis();
    } while (i1 < 50);
    c();
  }
  
  private static void b(JSONArray paramJSONArray)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          Object localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("action", "update");
          ((JSONObject)localObject2).put("kochava_device_id", b.r());
          ((JSONObject)localObject2).put("kochava_app_id", b.q());
          ((JSONObject)localObject2).put("sdk_version", "Android20160615" + b.a);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("outside_services", this.a);
          ((JSONObject)localObject2).put("data", localObject1);
          if ((b.e() == null) || (b.e().trim().isEmpty())) {
            b.d("control.kochava.com");
          }
          com.kochava.android.a.b.a("posting update to " + "https://" + b.e() + "/track/kvTracker.php");
          localObject1 = (HttpsURLConnection)new URL("https://" + b.e() + "/track/kvTracker.php").openConnection();
          ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", b.d().getString("useragent", ""));
          ((HttpsURLConnection)localObject1).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpsURLConnection)localObject1).setRequestMethod("POST");
          ((HttpsURLConnection)localObject1).setConnectTimeout(30000);
          ((HttpsURLConnection)localObject1).setReadTimeout(30000);
          ((HttpsURLConnection)localObject1).setDoInput(true);
          ((HttpsURLConnection)localObject1).setDoOutput(true);
          ((HttpsURLConnection)localObject1).connect();
          String str2 = ((JSONObject)localObject2).toString();
          com.kochava.android.a.b.a("Trying to post an update: " + ((JSONObject)localObject2).toString());
          localObject2 = new OutputStreamWriter(((HttpsURLConnection)localObject1).getOutputStream());
          ((OutputStreamWriter)localObject2).write(str2);
          ((OutputStreamWriter)localObject2).close();
          com.kochava.android.a.b.a("(Update) Grabbing Result...");
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
          com.kochava.android.a.b.b("Update error: " + localException.toString());
          return;
        }
        String str1;
        com.kochava.android.a.b.a("Update Result: " + str1);
      }
    }.start();
  }
  
  public static void b(boolean paramBoolean)
  {
    com.kochava.android.a.b.a("setErrorDebug to " + paramBoolean);
    c.b = paramBoolean;
  }
  
  private static boolean b(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  public static void c()
  {
    if (aa)
    {
      com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    com.kochava.android.a.b.a("flush");
    ai.submit(new e(null));
  }
  
  private static void c(JSONObject paramJSONObject)
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
          b.a((JSONObject)localObject3);
          if (this.a.has("register"))
          {
            ((JSONObject)localObject3).put("token", this.a.get("register").toString());
            ((JSONObject)localObject1).put("push_action", "register_token");
          }
          if (this.a.has("remove")) {
            ((JSONObject)localObject1).put("push_action", "remove_token");
          }
          ((JSONObject)localObject1).put("action", "push");
          ((JSONObject)localObject1).put("data", localObject3);
          ((JSONObject)localObject1).put("kochava_app_id", b.q());
          ((JSONObject)localObject1).put("kochava_device_id", b.r());
          ((JSONObject)localObject1).put("sdk_version", "Android20160615" + b.a);
          ((JSONObject)localObject1).put("sdk_protocol", "4");
          if ((b.e() == null) || (b.e().trim().isEmpty()))
          {
            com.kochava.android.a.b.a("Push token - hostControl was empty, using default");
            b.d("control.kochava.com");
          }
          com.kochava.android.a.b.a("kvPush - posting to " + "https://" + b.e() + "/track/kvTracker.php");
          localObject3 = new URL("https://" + b.e() + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject1).toString();
          com.kochava.android.a.b.a("kvPush data:" + (String)localObject1);
          localObject3 = (HttpsURLConnection)((URL)localObject3).openConnection();
          ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", b.d().getString("useragent", ""));
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
          com.kochava.android.a.b.a("Grabbing Result...");
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
          com.kochava.android.a.b.b("Problem sending register/remove token, saving push command: " + this.a.toString());
          b.d().edit().putString("register_remove_token", this.a.toString()).apply();
          if (c.b) {
            localException.printStackTrace();
          }
          return;
        }
        com.kochava.android.a.b.a("Result: " + (String)localObject2);
        Object localObject2 = new JSONObject((String)localObject2);
        if ((localObject2 != null) && (((JSONObject)localObject2).get("success").toString().equals("1")))
        {
          b.d().edit().remove("register_remove_token").apply();
          return;
        }
        com.kochava.android.a.b.b("Did not get a good response, saving push command: " + this.a.toString());
        b.d().edit().putString("register_remove_token", this.a.toString()).apply();
      }
    }.start();
  }
  
  private void d(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray1 = new JSONArray();
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        JSONArray localJSONArray2 = paramJSONObject.getJSONArray(str);
        int i1 = 0;
        for (;;)
        {
          int i2 = localJSONArray2.length();
          if (i1 >= i2) {
            break;
          }
          try
          {
            new ComponentName(c, Class.forName(localJSONArray2.getString(i1)));
            localJSONArray1.put(str);
          }
          catch (Exception localException)
          {
            i1 += 1;
          }
        }
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      if (localJSONArray1.length() > 0) {
        b(localJSONArray1);
      }
    }
  }
  
  private void h(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      ag = c.getSharedPreferences("initPrefs", 0);
      ag.edit().putString("currency", paramString).apply();
    }
  }
  
  private static void i(String paramString)
  {
    new Thread()
    {
      public void run()
      {
        com.kochava.android.a.b.a("Got event " + this.a);
        Object localObject1 = new HashMap();
        ((HashMap)localObject1).put("state", this.a);
        com.kochava.android.a.b.a("FIRE EVENT*** action:" + "session");
        com.kochava.android.a.b.a("FIRE EVENT*** properties:" + localObject1);
        JSONObject localJSONObject1 = new JSONObject();
        label308:
        do
        {
          JSONObject localJSONObject2;
          Object localObject2;
          try
          {
            localJSONObject1.put("kochava_app_id", b.q());
            localJSONObject1.put("kochava_device_id", b.r());
            localJSONObject1.put("action", "session");
            localJSONObject1.put("dev_id_strategy", b.y());
            localJSONObject2 = b.a(new JSONObject());
            if (b.z() != null)
            {
              localObject2 = b.z().entrySet().iterator();
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
            if (c.b) {
              localJSONException.printStackTrace();
            }
            com.kochava.android.a.b.b("event " + localJSONException);
            return;
          }
          localObject1 = ((HashMap)localObject1).entrySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (Map.Entry)((Iterator)localObject1).next();
            localJSONObject2.put((String)((Map.Entry)localObject2).getKey(), ((Map.Entry)localObject2).getValue());
          }
          localJSONException.put("data", localJSONObject2);
          com.kochava.android.a.b.a("fireEvent with properties: " + localJSONException);
        } while (b.x().a(localJSONException, false, false) < 50);
        b.c();
      }
    }.start();
  }
  
  private static boolean j(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  private void k(boolean paramBoolean)
  {
    if ((ag.getString("initBool", "").equals("false")) && (this.af != null) && (this.ae != null)) {
      try
      {
        com.kochava.android.a.b.a("Initial properties: " + this.af);
        com.kochava.android.a.b.a("Initital Oject: " + this.ae);
        if (!ag.getString("initData", "noData").equals("noData"))
        {
          this.af.put("conversion_type", "gplay");
          this.af.put("conversion_data", ag.getString("initData", ""));
          com.kochava.android.a.b.a("Got referral, attaching: " + ag.getString("initData", ""));
        }
        for (;;)
        {
          this.ae.put("data", this.af);
          Q.a(this.ae, true, false);
          com.kochava.android.a.b.a("Sending Initial");
          ag.edit().putString("initBool", "true").apply();
          if (!paramBoolean) {
            break;
          }
          this.U.cancel();
          return;
          com.kochava.android.a.b.b("Did not get referral data.");
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        com.kochava.android.a.b.b("An error occured during que initial. " + localJSONException);
        if (c.b) {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  protected void a(final String paramString)
  {
    paramString = new Runnable()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        com.kochava.android.a.b.a("Checking watchlist from " + paramString + "...");
        Object localObject2 = new HashMap();
        if (!b.d().contains("app_short_string"))
        {
          com.kochava.android.a.b.a("No previous app_short_string in watchlist, adding " + b.h(b.this));
          b.d().edit().putString("app_short_string", b.h(b.this)).apply();
          if (b.d().contains("app_limit_tracking")) {
            break label723;
          }
          com.kochava.android.a.b.a("No previous app_limit_tracking in watchlist, adding " + b.i(b.this));
          b.d().edit().putBoolean("app_limit_tracking", b.i(b.this)).apply();
          label174:
          if (b.d().contains("app_version")) {
            break label834;
          }
          com.kochava.android.a.b.a("No previous app_version in watchlist, adding " + b.b(b.this));
          b.d().edit().putString("app_version", b.b(b.this)).apply();
          label242:
          if (b.d().contains("device_limit_tracking")) {
            break label949;
          }
          com.kochava.android.a.b.a("No previous device_limit_tracking in watchlist, adding " + b.u());
          b.d().edit().putBoolean("device_limit_tracking", b.u()).apply();
          label302:
          if (b.v())
          {
            if (b.d().contains("adid")) {
              break label1044;
            }
            com.kochava.android.a.b.a("No previous adid in watchlist, adding " + b.o());
            b.d().edit().putString("adid", b.o()).apply();
            ((HashMap)localObject2).put("adid", b.o());
          }
          label378:
          if (b.d().contains("os_version")) {
            break label1125;
          }
          com.kochava.android.a.b.a("No previous os_version in watchlist, adding " + b.w());
          b.d().edit().putString("os_version", b.w()).apply();
        }
        for (;;)
        {
          if (!((HashMap)localObject2).keySet().isEmpty()) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("action", "update");
              localJSONObject.put("kochava_device_id", b.r());
              localJSONObject.put("kochava_app_id", b.q());
              localJSONObject.put("sdk_version", "Android20160615" + b.a);
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
              com.kochava.android.a.b.b("Update error: " + localException.toString());
            }
          }
          if (b.d().getString("app_short_string", "").equals(b.h(b.this))) {
            break;
          }
          com.kochava.android.a.b.a("app_short_string changed! Is now " + b.h(b.this));
          ((HashMap)localObject2).put("app_short_string", b.h(b.this) + "");
          b.d().edit().putString("app_short_string", b.h(b.this)).apply();
          break;
          label723:
          if (b.d().getBoolean("app_limit_tracking", false) == b.i(b.this)) {
            break label174;
          }
          com.kochava.android.a.b.a("app_limit_tracking changed! Is now " + b.i(b.this));
          ((HashMap)localObject2).put("app_limit_tracking", b.i(b.this) + "");
          b.d().edit().putBoolean("app_limit_tracking", b.i(b.this)).apply();
          break label174;
          label834:
          if (b.d().getString("app_version", "").equals(b.b(b.this))) {
            break label242;
          }
          com.kochava.android.a.b.a("app_version changed! Is now " + b.b(b.this));
          ((HashMap)localObject2).put("app_version", b.b(b.this) + "");
          b.d().edit().putString("app_version", b.b(b.this)).apply();
          break label242;
          label949:
          if (b.d().getBoolean("device_limit_tracking", false) == b.u()) {
            break label302;
          }
          com.kochava.android.a.b.a("device_limit_tracking changed! Is now " + b.u());
          ((HashMap)localObject2).put("device_limit_tracking", b.u() + "");
          b.d().edit().putBoolean("device_limit_tracking", b.u()).apply();
          break label302;
          label1044:
          if (b.d().getString("adid", "").equals(b.o())) {
            break label378;
          }
          com.kochava.android.a.b.a("adid changed! Is now " + b.o());
          ((HashMap)localObject2).put("adid", b.o());
          b.d().edit().putString("adid", b.o()).apply();
          break label378;
          label1125:
          if (!b.d().getString("os_version", "").equals(b.w()))
          {
            com.kochava.android.a.b.a("os_version changed! Is now " + b.w());
            ((HashMap)localObject2).put("os_version", b.w());
            b.d().edit().putString("os_version", b.w()).apply();
            b.d().edit().putString("useragent", b.j(b.this)).apply();
          }
        }
        localException.put("data", localObject3);
        if ((b.e() == null) || (b.e().trim().isEmpty())) {
          b.d("control.kochava.com");
        }
        com.kochava.android.a.b.a("posting update to " + "https://" + b.e() + "/track/kvTracker.php");
        localObject2 = (HttpsURLConnection)new URL("https://" + b.e() + "/track/kvTracker.php").openConnection();
        ((HttpsURLConnection)localObject2).setRequestProperty("User-Agent", b.d().getString("useragent", ""));
        ((HttpsURLConnection)localObject2).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        ((HttpsURLConnection)localObject2).setRequestMethod("POST");
        ((HttpsURLConnection)localObject2).setConnectTimeout(30000);
        ((HttpsURLConnection)localObject2).setReadTimeout(30000);
        ((HttpsURLConnection)localObject2).setDoInput(true);
        ((HttpsURLConnection)localObject2).setDoOutput(true);
        ((HttpsURLConnection)localObject2).connect();
        Object localObject3 = localException.toString();
        com.kochava.android.a.b.a("Trying to post an update: " + localException.toString());
        Object localObject1 = new OutputStreamWriter(((HttpsURLConnection)localObject2).getOutputStream());
        ((OutputStreamWriter)localObject1).write((String)localObject3);
        ((OutputStreamWriter)localObject1).close();
        com.kochava.android.a.b.a("(Update) Grabbing Result...");
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
        com.kochava.android.a.b.a("Update Result: " + (String)localObject1);
      }
    };
    ak.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
  
  public void a(final String paramString1, final double paramDouble1, double paramDouble2, final double paramDouble3, String paramString2)
  {
    if (aa)
    {
      com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          com.kochava.android.a.b.a("Got spatial event " + paramString1);
          HashMap localHashMap = new HashMap();
          localHashMap.put("event_name", paramString1);
          DecimalFormat localDecimalFormat = new DecimalFormat("#.##");
          localHashMap.put("x", Double.valueOf(localDecimalFormat.format(paramDouble1)).toString());
          localHashMap.put("y", Double.valueOf(localDecimalFormat.format(paramDouble3)).toString());
          localHashMap.put("z", Double.valueOf(localDecimalFormat.format(this.d)).toString());
          localHashMap.put("event_data", this.e);
          b.b(b.this, "spatial", localHashMap, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      com.kochava.android.a.b.b("Error in spatial event call: " + paramString1);
    }
  }
  
  public void a(final String paramString1, final String paramString2)
  {
    if (aa)
    {
      com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          com.kochava.android.a.b.a("Got event " + paramString1);
          HashMap localHashMap = new HashMap();
          localHashMap.put("event_name", paramString1);
          localHashMap.put("event_data", paramString2);
          b.b(b.this, "event", localHashMap, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      com.kochava.android.a.b.b("Error in event call: " + paramString1);
    }
  }
  
  public void a(final String paramString1, final String paramString2, final Map<String, String> paramMap)
  {
    if (aa)
    {
      com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          com.kochava.android.a.b.a("Got event with receipt " + paramString1);
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
              b.b(b.this, "event", localHashMap1, localHashMap2);
              return;
            }
            com.kochava.android.a.b.a("eventWithReceipt - receipt did not contain basic objects - sending event w/o receipt");
            b.b(b.this, "event", localHashMap1, null);
            return;
          }
          com.kochava.android.a.b.a("eventWithReceipt - receipt is null - sending event w/o receipt");
          b.b(b.this, "event", localHashMap1, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      com.kochava.android.a.b.b("Error in event with receipt call: " + paramString1);
    }
  }
  
  public void a(final Map<String, String> paramMap)
  {
    if (aa)
    {
      com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    new Thread()
    {
      public void run()
      {
        com.kochava.android.a.b.a("Mapping identity");
        b.a(b.this, "identityLink", paramMap, null);
      }
    }.start();
  }
  
  public void b(final String paramString)
  {
    if (aa)
    {
      com.kochava.android.a.b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    new Thread()
    {
      public void run()
      {
        try
        {
          com.kochava.android.a.b.a("Got deep link event with uri" + paramString);
          JSONObject localJSONObject1 = new JSONObject();
          localJSONObject1.put("action", "deeplink");
          localJSONObject1.put("kochava_app_id", b.q());
          localJSONObject1.put("kochava_device_id", b.r());
          JSONObject localJSONObject2 = new JSONObject();
          localJSONObject2.put("uri", paramString);
          localJSONObject2.put("usertime", System.currentTimeMillis() / 1000L + "");
          localJSONObject1.put("data", localJSONObject2);
          int i = b.x().a(localJSONObject1, false, false);
          com.kochava.android.a.b.a("deep link event: " + localJSONObject1);
          if (i >= 50) {
            b.c();
          }
          return;
        }
        catch (Exception localException)
        {
          com.kochava.android.a.b.b("Error in deep link event call: " + localException);
        }
      }
    }.start();
  }
  
  protected static class a
  {
    protected static boolean a = false;
    protected static boolean b = false;
    
    protected static void a(String paramString)
    {
      new Thread()
      {
        public void run()
        {
          if (!b.a.a) {
            com.kochava.android.a.b.a("AppLifeCycleStatusManager - not active");
          }
          do
          {
            return;
            if (this.a.equals("is_focused"))
            {
              if (!b.a.b)
              {
                com.kochava.android.a.b.a("AppLifeCycleStatusManager - not already resumed, starting session...");
                b.F();
                b.a.b = true;
                return;
              }
              com.kochava.android.a.b.a("AppLifeCycleStatusManager - IS_FOCUSED received, App is already in focused state.");
              return;
            }
          } while (!this.a.equals("is_in_background"));
          if (b.a.b)
          {
            com.kochava.android.a.b.a("AppLifeCycleStatusManager - going to background from app, ending session");
            b.G();
            b.a.b = false;
            return;
          }
          com.kochava.android.a.b.a("AppLifeCycleStatusManager - IS_IN_BACKGROUND received, App is already in background state.");
        }
      }.start();
    }
  }
  
  protected class b
  {
    public String a = "";
    public String b = "";
    public String c = "";
    
    public b(String paramString1, String paramString2, String paramString3)
    {
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramString3;
    }
  }
  
  @TargetApi(14)
  protected class c
    implements Application.ActivityLifecycleCallbacks
  {
    protected c() {}
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity)
    {
      com.kochava.android.a.b.a("LifeCycleTracker - Tracking Activity lost focus");
      b.a.a("is_in_background");
      b.j(true);
    }
    
    public void onActivityResumed(Activity paramActivity)
    {
      com.kochava.android.a.b.a("LifeCycleTracker - Tracking Activity Resumed");
      b.a.a("is_focused");
      b.j(false);
    }
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity) {}
    
    public void onActivityStopped(Activity paramActivity) {}
  }
  
  @TargetApi(14)
  protected class d
    implements ComponentCallbacks2
  {
    protected d() {}
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory() {}
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt == 20)
      {
        com.kochava.android.a.b.a("MemoryBoss - Tracking Activity lost focus");
        b.a.a("is_in_background");
        b.j(true);
      }
    }
  }
  
  private static class e
    implements Runnable
  {
    private e() {}
    
    public void run()
    {
      b.E();
    }
  }
}
