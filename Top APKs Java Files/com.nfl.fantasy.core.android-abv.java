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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class abv
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
  private static List<abv.b> P = new ArrayList();
  private static abu Q;
  private static Timer S;
  private static boolean V = true;
  private static boolean W = false;
  private static boolean X = false;
  private static long Z = 0L;
  protected static String a;
  private static boolean aa = false;
  private static boolean ab = true;
  private static boolean ac = false;
  private static SharedPreferences af;
  private static SharedPreferences ag;
  private static final ExecutorService ah = Executors.newFixedThreadPool(1);
  private static Handler ai;
  private static final ScheduledExecutorService aj = Executors.newSingleThreadScheduledExecutor();
  private static final Uri ak = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static HashMap<String, Boolean> al = new HashMap() {};
  private static JSONArray am = new JSONArray();
  private static Runnable ao = new Runnable()
  {
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: bipush 60
      //   2: istore_3
      //   3: iload_3
      //   4: istore_1
      //   5: invokestatic 26	abv:c	()Ljava/lang/String;
      //   8: ifnull +17 -> 25
      //   11: iload_3
      //   12: istore_1
      //   13: invokestatic 26	abv:c	()Ljava/lang/String;
      //   16: invokevirtual 31	java/lang/String:trim	()Ljava/lang/String;
      //   19: invokevirtual 35	java/lang/String:isEmpty	()Z
      //   22: ifeq +18 -> 40
      //   25: iload_3
      //   26: istore_1
      //   27: ldc 37
      //   29: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   32: iload_3
      //   33: istore_1
      //   34: ldc 45
      //   36: invokestatic 48	abv:c	(Ljava/lang/String;)Ljava/lang/String;
      //   39: pop
      //   40: iload_3
      //   41: istore_1
      //   42: new 50	java/lang/StringBuilder
      //   45: dup
      //   46: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   49: ldc 53
      //   51: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   54: ldc 59
      //   56: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   59: invokestatic 26	abv:c	()Ljava/lang/String;
      //   62: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   65: ldc 61
      //   67: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   70: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   73: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   76: iload_3
      //   77: istore_1
      //   78: new 66	java/net/URL
      //   81: dup
      //   82: new 50	java/lang/StringBuilder
      //   85: dup
      //   86: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   89: ldc 59
      //   91: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   94: invokestatic 26	abv:c	()Ljava/lang/String;
      //   97: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   100: ldc 61
      //   102: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   105: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   108: invokespecial 68	java/net/URL:<init>	(Ljava/lang/String;)V
      //   111: invokevirtual 72	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   114: checkcast 74	javax/net/ssl/HttpsURLConnection
      //   117: astore 7
      //   119: iload_3
      //   120: istore_1
      //   121: aload 7
      //   123: ldc 76
      //   125: invokestatic 80	abv:b	()Landroid/content/SharedPreferences;
      //   128: ldc 82
      //   130: ldc 84
      //   132: invokeinterface 90 3 0
      //   137: invokevirtual 94	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   140: iload_3
      //   141: istore_1
      //   142: aload 7
      //   144: ldc 96
      //   146: ldc 98
      //   148: invokevirtual 94	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   151: iload_3
      //   152: istore_1
      //   153: aload 7
      //   155: ldc 100
      //   157: invokevirtual 103	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   160: iload_3
      //   161: istore_1
      //   162: aload 7
      //   164: sipush 30000
      //   167: invokevirtual 107	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   170: iload_3
      //   171: istore_1
      //   172: aload 7
      //   174: sipush 30000
      //   177: invokevirtual 110	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   180: iload_3
      //   181: istore_1
      //   182: aload 7
      //   184: iconst_1
      //   185: invokevirtual 114	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   188: iload_3
      //   189: istore_1
      //   190: aload 7
      //   192: iconst_1
      //   193: invokevirtual 117	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   196: iload_3
      //   197: istore_1
      //   198: aload 7
      //   200: invokestatic 122	ax:a	(Ljava/net/URLConnection;)V
      //   203: iload_3
      //   204: istore_1
      //   205: aload 7
      //   207: invokevirtual 125	javax/net/ssl/HttpsURLConnection:connect	()V
      //   210: iload_3
      //   211: istore_1
      //   212: aload 7
      //   214: invokestatic 127	ax:b	(Ljava/net/URLConnection;)V
      //   217: iload_3
      //   218: istore_1
      //   219: new 129	org/json/JSONObject
      //   222: dup
      //   223: invokespecial 130	org/json/JSONObject:<init>	()V
      //   226: astore 8
      //   228: iload_3
      //   229: istore_1
      //   230: aload 8
      //   232: ldc -124
      //   234: ldc -122
      //   236: invokevirtual 138	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   239: pop
      //   240: iload_3
      //   241: istore_1
      //   242: aload 8
      //   244: ldc -116
      //   246: invokestatic 143	abv:o	()Ljava/lang/String;
      //   249: invokevirtual 138	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   252: pop
      //   253: iload_3
      //   254: istore_1
      //   255: aload 8
      //   257: ldc -111
      //   259: invokestatic 148	abv:p	()Ljava/lang/String;
      //   262: invokevirtual 138	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   265: pop
      //   266: iload_3
      //   267: istore_1
      //   268: aload 8
      //   270: ldc -106
      //   272: new 50	java/lang/StringBuilder
      //   275: dup
      //   276: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   279: ldc -104
      //   281: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   284: getstatic 155	abv:a	Ljava/lang/String;
      //   287: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   290: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   293: invokevirtual 138	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   296: pop
      //   297: iload_3
      //   298: istore_1
      //   299: aload 8
      //   301: ldc -99
      //   303: ldc -97
      //   305: invokevirtual 138	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   308: pop
      //   309: iload_3
      //   310: istore_1
      //   311: aload 8
      //   313: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
      //   316: astore 8
      //   318: iload_3
      //   319: istore_1
      //   320: new 50	java/lang/StringBuilder
      //   323: dup
      //   324: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   327: ldc -94
      //   329: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   332: aload 8
      //   334: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   337: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   340: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   343: iload_3
      //   344: istore_1
      //   345: ldc -92
      //   347: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   350: iload_3
      //   351: istore_1
      //   352: aload 7
      //   354: invokestatic 122	ax:a	(Ljava/net/URLConnection;)V
      //   357: iload_3
      //   358: istore_1
      //   359: aload 7
      //   361: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   364: astore 9
      //   366: iload_3
      //   367: istore_1
      //   368: aload 7
      //   370: invokestatic 127	ax:b	(Ljava/net/URLConnection;)V
      //   373: iload_3
      //   374: istore_1
      //   375: new 170	java/io/OutputStreamWriter
      //   378: dup
      //   379: aload 9
      //   381: invokespecial 173	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   384: astore 9
      //   386: iload_3
      //   387: istore_1
      //   388: aload 9
      //   390: aload 8
      //   392: invokevirtual 176	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   395: iload_3
      //   396: istore_1
      //   397: aload 9
      //   399: invokevirtual 179	java/io/OutputStreamWriter:close	()V
      //   402: iload_3
      //   403: istore 4
      //   405: iload_3
      //   406: istore_1
      //   407: iload_3
      //   408: istore 6
      //   410: ldc -75
      //   412: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   415: iload_3
      //   416: istore 4
      //   418: iload_3
      //   419: istore_1
      //   420: iload_3
      //   421: istore 6
      //   423: new 183	java/lang/StringBuffer
      //   426: dup
      //   427: ldc 84
      //   429: invokespecial 184	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   432: astore 8
      //   434: iload_3
      //   435: istore 4
      //   437: iload_3
      //   438: istore_1
      //   439: iload_3
      //   440: istore 6
      //   442: new 186	java/io/BufferedReader
      //   445: dup
      //   446: new 188	java/io/InputStreamReader
      //   449: dup
      //   450: aload 7
      //   452: invokestatic 192	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
      //   455: invokespecial 195	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   458: invokespecial 198	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   461: astore 7
      //   463: iload_3
      //   464: istore 4
      //   466: iload_3
      //   467: istore_1
      //   468: iload_3
      //   469: istore 6
      //   471: aload 7
      //   473: invokevirtual 201	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   476: astore 9
      //   478: aload 9
      //   480: ifnull +190 -> 670
      //   483: iload_3
      //   484: istore 4
      //   486: iload_3
      //   487: istore_1
      //   488: iload_3
      //   489: istore 6
      //   491: aload 8
      //   493: aload 9
      //   495: invokevirtual 204	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   498: pop
      //   499: goto -36 -> 463
      //   502: astore 7
      //   504: iload 4
      //   506: istore_1
      //   507: aload 7
      //   509: invokevirtual 208	java/lang/Object:getClass	()Ljava/lang/Class;
      //   512: ldc -46
      //   514: invokevirtual 214	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   517: ifeq +668 -> 1185
      //   520: iload 4
      //   522: istore_1
      //   523: new 50	java/lang/StringBuilder
      //   526: dup
      //   527: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   530: ldc -40
      //   532: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   535: aload 7
      //   537: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   540: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   543: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   546: iload 4
      //   548: istore_1
      //   549: aload 7
      //   551: invokestatic 224	abv:a	(Ljava/lang/Exception;)V
      //   554: return
      //   555: astore 8
      //   557: iload_3
      //   558: istore_1
      //   559: aload 7
      //   561: aload 8
      //   563: invokestatic 227	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
      //   566: iload_3
      //   567: istore_1
      //   568: aload 8
      //   570: athrow
      //   571: astore 7
      //   573: aload 7
      //   575: invokevirtual 208	java/lang/Object:getClass	()Ljava/lang/Class;
      //   578: ldc -46
      //   580: invokevirtual 214	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   583: ifeq +630 -> 1213
      //   586: new 50	java/lang/StringBuilder
      //   589: dup
      //   590: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   593: ldc -40
      //   595: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   598: aload 7
      //   600: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   603: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   606: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   609: aload 7
      //   611: invokestatic 224	abv:a	(Ljava/lang/Exception;)V
      //   614: return
      //   615: astore 8
      //   617: iload_3
      //   618: istore_1
      //   619: aload 7
      //   621: aload 8
      //   623: invokestatic 227	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
      //   626: iload_3
      //   627: istore_1
      //   628: aload 8
      //   630: athrow
      //   631: astore 7
      //   633: new 50	java/lang/StringBuilder
      //   636: dup
      //   637: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   640: ldc -27
      //   642: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   645: aload 7
      //   647: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   650: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   653: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   656: iload_1
      //   657: istore 5
      //   659: iload 5
      //   661: ifle +576 -> 1237
      //   664: iload 5
      //   666: invokestatic 231	abv:a	(I)V
      //   669: return
      //   670: iload_3
      //   671: istore 4
      //   673: iload_3
      //   674: istore_1
      //   675: iload_3
      //   676: istore 6
      //   678: aload 8
      //   680: invokevirtual 232	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   683: astore 7
      //   685: iload_3
      //   686: istore 4
      //   688: iload_3
      //   689: istore_1
      //   690: iload_3
      //   691: istore 6
      //   693: new 50	java/lang/StringBuilder
      //   696: dup
      //   697: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   700: ldc -22
      //   702: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   705: aload 7
      //   707: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   710: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   713: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   716: aconst_null
      //   717: astore 8
      //   719: iload_3
      //   720: istore 4
      //   722: iload_3
      //   723: istore_1
      //   724: iload_3
      //   725: istore 6
      //   727: new 129	org/json/JSONObject
      //   730: dup
      //   731: aload 7
      //   733: invokespecial 235	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   736: astore 7
      //   738: aload 7
      //   740: astore 8
      //   742: iload_3
      //   743: istore 5
      //   745: aload 8
      //   747: ifnull -88 -> 659
      //   750: iload_3
      //   751: istore 4
      //   753: iload_3
      //   754: istore_1
      //   755: iload_3
      //   756: istore 6
      //   758: new 50	java/lang/StringBuilder
      //   761: dup
      //   762: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   765: ldc -19
      //   767: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   770: aload 8
      //   772: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
      //   775: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   778: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   781: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   784: aconst_null
      //   785: astore 7
      //   787: iload_3
      //   788: istore 4
      //   790: iload_3
      //   791: istore_1
      //   792: iload_3
      //   793: istore 6
      //   795: aload 8
      //   797: ldc -17
      //   799: invokevirtual 243	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   802: astore 8
      //   804: iload_3
      //   805: istore 4
      //   807: iload_3
      //   808: istore_1
      //   809: iload_3
      //   810: istore 6
      //   812: aload 8
      //   814: astore 7
      //   816: new 50	java/lang/StringBuilder
      //   819: dup
      //   820: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   823: ldc -11
      //   825: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   828: aload 8
      //   830: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
      //   833: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   836: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   839: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   842: aload 8
      //   844: astore 7
      //   846: iload_3
      //   847: istore 5
      //   849: aload 7
      //   851: ifnull -192 -> 659
      //   854: ldc 84
      //   856: astore 8
      //   858: iload_3
      //   859: istore 4
      //   861: iload_3
      //   862: istore_1
      //   863: iload_3
      //   864: istore 6
      //   866: aload 7
      //   868: ldc -9
      //   870: invokevirtual 249	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   873: astore 9
      //   875: aload 9
      //   877: astore 8
      //   879: iload_3
      //   880: istore 4
      //   882: iload_3
      //   883: istore_1
      //   884: iload_3
      //   885: istore_2
      //   886: iload_3
      //   887: istore 6
      //   889: aload 7
      //   891: ldc -5
      //   893: invokevirtual 255	org/json/JSONObject:getInt	(Ljava/lang/String;)I
      //   896: istore_3
      //   897: iload_3
      //   898: istore 5
      //   900: iload_3
      //   901: ifge -242 -> 659
      //   904: iload_3
      //   905: istore 4
      //   907: iload_3
      //   908: istore_1
      //   909: iload_3
      //   910: istore_2
      //   911: iload_3
      //   912: istore 6
      //   914: invokestatic 258	abv:q	()Landroid/content/SharedPreferences;
      //   917: invokeinterface 262 1 0
      //   922: ldc_w 264
      //   925: aload 8
      //   927: invokeinterface 270 3 0
      //   932: invokeinterface 273 1 0
      //   937: iload_3
      //   938: istore 4
      //   940: iload_3
      //   941: istore_1
      //   942: iload_3
      //   943: istore 5
      //   945: iload_3
      //   946: istore_2
      //   947: iload_3
      //   948: istore 6
      //   950: invokestatic 277	abv:r	()Landroid/os/Handler;
      //   953: ifnull -294 -> 659
      //   956: iload_3
      //   957: istore 4
      //   959: iload_3
      //   960: istore_1
      //   961: iload_3
      //   962: istore_2
      //   963: iload_3
      //   964: istore 6
      //   966: invokestatic 283	android/os/Message:obtain	()Landroid/os/Message;
      //   969: astore 7
      //   971: iload_3
      //   972: istore 4
      //   974: iload_3
      //   975: istore_1
      //   976: iload_3
      //   977: istore_2
      //   978: iload_3
      //   979: istore 6
      //   981: new 285	android/os/Bundle
      //   984: dup
      //   985: invokespecial 286	android/os/Bundle:<init>	()V
      //   988: astore 9
      //   990: iload_3
      //   991: istore 4
      //   993: iload_3
      //   994: istore_1
      //   995: iload_3
      //   996: istore_2
      //   997: iload_3
      //   998: istore 6
      //   1000: aload 9
      //   1002: ldc_w 264
      //   1005: aload 8
      //   1007: invokevirtual 287	java/lang/String:toString	()Ljava/lang/String;
      //   1010: invokevirtual 289	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
      //   1013: iload_3
      //   1014: istore 4
      //   1016: iload_3
      //   1017: istore_1
      //   1018: iload_3
      //   1019: istore_2
      //   1020: iload_3
      //   1021: istore 6
      //   1023: aload 7
      //   1025: aload 9
      //   1027: invokevirtual 293	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   1030: iload_3
      //   1031: istore 4
      //   1033: iload_3
      //   1034: istore_1
      //   1035: iload_3
      //   1036: istore_2
      //   1037: iload_3
      //   1038: istore 6
      //   1040: invokestatic 277	abv:r	()Landroid/os/Handler;
      //   1043: aload 7
      //   1045: invokevirtual 299	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   1048: pop
      //   1049: iload_3
      //   1050: istore 5
      //   1052: goto -393 -> 659
      //   1055: astore 7
      //   1057: iload_2
      //   1058: istore 4
      //   1060: iload_2
      //   1061: istore_1
      //   1062: iload_2
      //   1063: istore 6
      //   1065: ldc_w 301
      //   1068: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   1071: iload_2
      //   1072: istore 5
      //   1074: goto -415 -> 659
      //   1077: astore 7
      //   1079: iload 6
      //   1081: istore_1
      //   1082: new 50	java/lang/StringBuilder
      //   1085: dup
      //   1086: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   1089: ldc_w 303
      //   1092: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1095: aload 7
      //   1097: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1100: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1103: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   1106: return
      //   1107: astore 7
      //   1109: iload_3
      //   1110: istore 4
      //   1112: iload_3
      //   1113: istore_1
      //   1114: iload_3
      //   1115: istore 6
      //   1117: new 50	java/lang/StringBuilder
      //   1120: dup
      //   1121: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   1124: ldc_w 305
      //   1127: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1130: aload 7
      //   1132: invokevirtual 306	org/json/JSONException:toString	()Ljava/lang/String;
      //   1135: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1138: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1141: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   1144: goto -402 -> 742
      //   1147: astore 8
      //   1149: iload_3
      //   1150: istore 4
      //   1152: iload_3
      //   1153: istore_1
      //   1154: iload_3
      //   1155: istore 6
      //   1157: ldc_w 308
      //   1160: invokestatic 43	aca:a	(Ljava/lang/String;)V
      //   1163: goto -317 -> 846
      //   1166: astore 9
      //   1168: iload_3
      //   1169: istore 4
      //   1171: iload_3
      //   1172: istore_1
      //   1173: iload_3
      //   1174: istore 6
      //   1176: ldc_w 310
      //   1179: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   1182: goto -303 -> 879
      //   1185: iload 4
      //   1187: istore_1
      //   1188: new 50	java/lang/StringBuilder
      //   1191: dup
      //   1192: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   1195: ldc_w 312
      //   1198: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1201: aload 7
      //   1203: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1206: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1209: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   1212: return
      //   1213: new 50	java/lang/StringBuilder
      //   1216: dup
      //   1217: invokespecial 51	java/lang/StringBuilder:<init>	()V
      //   1220: ldc_w 312
      //   1223: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1226: aload 7
      //   1228: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1231: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1234: invokestatic 221	aca:b	(Ljava/lang/String;)V
      //   1237: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1238	0	this	17
      //   4	1184	1	i	int
      //   885	187	2	j	int
      //   2	1172	3	k	int
      //   403	783	4	m	int
      //   657	416	5	n	int
      //   408	767	6	i1	int
      //   117	355	7	localObject1	Object
      //   502	58	7	localIOException1	java.io.IOException
      //   571	49	7	localIOException2	java.io.IOException
      //   631	15	7	localException	Exception
      //   683	361	7	localObject2	Object
      //   1055	1	7	localJSONException1	JSONException
      //   1077	19	7	localOutOfMemoryError	OutOfMemoryError
      //   1107	120	7	localJSONException2	JSONException
      //   226	266	8	localObject3	Object
      //   555	14	8	localIOException3	java.io.IOException
      //   615	64	8	localIOException4	java.io.IOException
      //   717	289	8	localObject4	Object
      //   1147	1	8	localJSONException3	JSONException
      //   364	662	9	localObject5	Object
      //   1166	1	9	localJSONException4	JSONException
      // Exception table:
      //   from	to	target	type
      //   410	415	502	java/io/IOException
      //   423	434	502	java/io/IOException
      //   442	463	502	java/io/IOException
      //   471	478	502	java/io/IOException
      //   491	499	502	java/io/IOException
      //   678	685	502	java/io/IOException
      //   693	716	502	java/io/IOException
      //   727	738	502	java/io/IOException
      //   758	784	502	java/io/IOException
      //   795	804	502	java/io/IOException
      //   816	842	502	java/io/IOException
      //   866	875	502	java/io/IOException
      //   889	897	502	java/io/IOException
      //   914	937	502	java/io/IOException
      //   950	956	502	java/io/IOException
      //   966	971	502	java/io/IOException
      //   981	990	502	java/io/IOException
      //   1000	1013	502	java/io/IOException
      //   1023	1030	502	java/io/IOException
      //   1040	1049	502	java/io/IOException
      //   1065	1071	502	java/io/IOException
      //   1117	1144	502	java/io/IOException
      //   1157	1163	502	java/io/IOException
      //   1176	1182	502	java/io/IOException
      //   205	210	555	java/io/IOException
      //   212	217	555	java/io/IOException
      //   5	11	571	java/io/IOException
      //   13	25	571	java/io/IOException
      //   27	32	571	java/io/IOException
      //   34	40	571	java/io/IOException
      //   42	76	571	java/io/IOException
      //   78	119	571	java/io/IOException
      //   121	140	571	java/io/IOException
      //   142	151	571	java/io/IOException
      //   153	160	571	java/io/IOException
      //   162	170	571	java/io/IOException
      //   172	180	571	java/io/IOException
      //   182	188	571	java/io/IOException
      //   190	196	571	java/io/IOException
      //   198	203	571	java/io/IOException
      //   219	228	571	java/io/IOException
      //   230	240	571	java/io/IOException
      //   242	253	571	java/io/IOException
      //   255	266	571	java/io/IOException
      //   268	297	571	java/io/IOException
      //   299	309	571	java/io/IOException
      //   311	318	571	java/io/IOException
      //   320	343	571	java/io/IOException
      //   345	350	571	java/io/IOException
      //   352	357	571	java/io/IOException
      //   375	386	571	java/io/IOException
      //   388	395	571	java/io/IOException
      //   397	402	571	java/io/IOException
      //   507	520	571	java/io/IOException
      //   523	546	571	java/io/IOException
      //   549	554	571	java/io/IOException
      //   559	566	571	java/io/IOException
      //   568	571	571	java/io/IOException
      //   619	626	571	java/io/IOException
      //   628	631	571	java/io/IOException
      //   1082	1106	571	java/io/IOException
      //   1188	1212	571	java/io/IOException
      //   359	366	615	java/io/IOException
      //   368	373	615	java/io/IOException
      //   5	11	631	java/lang/Exception
      //   13	25	631	java/lang/Exception
      //   27	32	631	java/lang/Exception
      //   34	40	631	java/lang/Exception
      //   42	76	631	java/lang/Exception
      //   78	119	631	java/lang/Exception
      //   121	140	631	java/lang/Exception
      //   142	151	631	java/lang/Exception
      //   153	160	631	java/lang/Exception
      //   162	170	631	java/lang/Exception
      //   172	180	631	java/lang/Exception
      //   182	188	631	java/lang/Exception
      //   190	196	631	java/lang/Exception
      //   198	203	631	java/lang/Exception
      //   205	210	631	java/lang/Exception
      //   212	217	631	java/lang/Exception
      //   219	228	631	java/lang/Exception
      //   230	240	631	java/lang/Exception
      //   242	253	631	java/lang/Exception
      //   255	266	631	java/lang/Exception
      //   268	297	631	java/lang/Exception
      //   299	309	631	java/lang/Exception
      //   311	318	631	java/lang/Exception
      //   320	343	631	java/lang/Exception
      //   345	350	631	java/lang/Exception
      //   352	357	631	java/lang/Exception
      //   359	366	631	java/lang/Exception
      //   368	373	631	java/lang/Exception
      //   375	386	631	java/lang/Exception
      //   388	395	631	java/lang/Exception
      //   397	402	631	java/lang/Exception
      //   410	415	631	java/lang/Exception
      //   423	434	631	java/lang/Exception
      //   442	463	631	java/lang/Exception
      //   471	478	631	java/lang/Exception
      //   491	499	631	java/lang/Exception
      //   507	520	631	java/lang/Exception
      //   523	546	631	java/lang/Exception
      //   549	554	631	java/lang/Exception
      //   559	566	631	java/lang/Exception
      //   568	571	631	java/lang/Exception
      //   619	626	631	java/lang/Exception
      //   628	631	631	java/lang/Exception
      //   678	685	631	java/lang/Exception
      //   693	716	631	java/lang/Exception
      //   727	738	631	java/lang/Exception
      //   758	784	631	java/lang/Exception
      //   795	804	631	java/lang/Exception
      //   816	842	631	java/lang/Exception
      //   866	875	631	java/lang/Exception
      //   889	897	631	java/lang/Exception
      //   914	937	631	java/lang/Exception
      //   950	956	631	java/lang/Exception
      //   966	971	631	java/lang/Exception
      //   981	990	631	java/lang/Exception
      //   1000	1013	631	java/lang/Exception
      //   1023	1030	631	java/lang/Exception
      //   1040	1049	631	java/lang/Exception
      //   1065	1071	631	java/lang/Exception
      //   1082	1106	631	java/lang/Exception
      //   1117	1144	631	java/lang/Exception
      //   1157	1163	631	java/lang/Exception
      //   1176	1182	631	java/lang/Exception
      //   1188	1212	631	java/lang/Exception
      //   889	897	1055	org/json/JSONException
      //   914	937	1055	org/json/JSONException
      //   950	956	1055	org/json/JSONException
      //   966	971	1055	org/json/JSONException
      //   981	990	1055	org/json/JSONException
      //   1000	1013	1055	org/json/JSONException
      //   1023	1030	1055	org/json/JSONException
      //   1040	1049	1055	org/json/JSONException
      //   410	415	1077	java/lang/OutOfMemoryError
      //   423	434	1077	java/lang/OutOfMemoryError
      //   442	463	1077	java/lang/OutOfMemoryError
      //   471	478	1077	java/lang/OutOfMemoryError
      //   491	499	1077	java/lang/OutOfMemoryError
      //   678	685	1077	java/lang/OutOfMemoryError
      //   693	716	1077	java/lang/OutOfMemoryError
      //   727	738	1077	java/lang/OutOfMemoryError
      //   758	784	1077	java/lang/OutOfMemoryError
      //   795	804	1077	java/lang/OutOfMemoryError
      //   816	842	1077	java/lang/OutOfMemoryError
      //   866	875	1077	java/lang/OutOfMemoryError
      //   889	897	1077	java/lang/OutOfMemoryError
      //   914	937	1077	java/lang/OutOfMemoryError
      //   950	956	1077	java/lang/OutOfMemoryError
      //   966	971	1077	java/lang/OutOfMemoryError
      //   981	990	1077	java/lang/OutOfMemoryError
      //   1000	1013	1077	java/lang/OutOfMemoryError
      //   1023	1030	1077	java/lang/OutOfMemoryError
      //   1040	1049	1077	java/lang/OutOfMemoryError
      //   1065	1071	1077	java/lang/OutOfMemoryError
      //   1117	1144	1077	java/lang/OutOfMemoryError
      //   1157	1163	1077	java/lang/OutOfMemoryError
      //   1176	1182	1077	java/lang/OutOfMemoryError
      //   727	738	1107	org/json/JSONException
      //   795	804	1147	org/json/JSONException
      //   816	842	1147	org/json/JSONException
      //   866	875	1166	org/json/JSONException
    }
  };
  protected static boolean b;
  protected static Context c;
  protected static Handler g = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (abv.H()) {
        aca.b("The library was not initialized properly or we cannot connect to our servers. Cannot send location udpate");
      }
      do
      {
        for (;;)
        {
          return;
          aca.a("Location Handler got message, queueing location update.");
          try
          {
            paramAnonymousMessage = new JSONObject();
            JSONObject localJSONObject1 = new JSONObject();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("lat", abv.b().getString("kochava_lat", ""));
            localJSONObject2.put("lon", abv.b().getString("kochava_lon", ""));
            localJSONObject2.put("acc", abv.b().getString("kochava_accuracy", ""));
            localJSONObject1.put("location", localJSONObject2);
            paramAnonymousMessage.put("action", "update");
            paramAnonymousMessage.put("kochava_device_id", abv.p());
            paramAnonymousMessage.put("kochava_app_id", abv.o());
            paramAnonymousMessage.put("sdk_version", "Android20160615-NOEMAIL" + abv.a);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            aca.a("Location update: " + paramAnonymousMessage);
            int i = abv.v().a(paramAnonymousMessage, false, true);
            abv.a(System.currentTimeMillis());
            long l = abv.b().getLong("kochava_loc_timestamp", 0L);
            abv.b().edit().putLong("kochava_old_loc_timestamp", l).apply();
            if (i >= 50)
            {
              abv.a();
              return;
            }
          }
          catch (JSONException paramAnonymousMessage) {}
        }
      } while (!abw.b);
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
  private JSONObject ad;
  private JSONObject ae;
  private Runnable an = new Runnable()
  {
    /* Error */
    @android.annotation.SuppressLint({"NewApi"})
    public void run()
    {
      // Byte code:
      //   0: ldc 34
      //   2: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   5: invokestatic 44	android/os/Looper:prepare	()V
      //   8: lconst_0
      //   9: lstore 6
      //   11: iconst_0
      //   12: istore_3
      //   13: iconst_0
      //   14: istore_2
      //   15: invokestatic 50	java/lang/System:currentTimeMillis	()J
      //   18: lstore 8
      //   20: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
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
      //   44: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   47: ldc 64
      //   49: invokeinterface 68 2 0
      //   54: ifeq +65 -> 119
      //   57: iload_3
      //   58: istore_1
      //   59: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   62: ldc 64
      //   64: ldc 70
      //   66: invokeinterface 74 3 0
      //   71: aload_0
      //   72: getfield 14	abv$16:a	Labv;
      //   75: invokestatic 77	abv:b	(Labv;)Ljava/lang/String;
      //   78: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   81: ifne +38 -> 119
      //   84: new 85	java/lang/StringBuilder
      //   87: dup
      //   88: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   91: ldc 88
      //   93: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: aload_0
      //   97: getfield 14	abv$16:a	Labv;
      //   100: invokestatic 77	abv:b	(Labv;)Ljava/lang/String;
      //   103: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: ldc 94
      //   108: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   114: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   117: iconst_1
      //   118: istore_1
      //   119: iload_1
      //   120: ifne +29 -> 149
      //   123: lload 6
      //   125: lconst_0
      //   126: lcmp
      //   127: ifle +22 -> 149
      //   130: lload 6
      //   132: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   135: ldc 100
      //   137: ldc2_w 101
      //   140: invokeinterface 62 4 0
      //   145: lcmp
      //   146: ifle +3916 -> 4062
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
      //   167: getfield 14	abv$16:a	Labv;
      //   170: getfield 108	abv:e	Lorg/json/JSONObject;
      //   173: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   176: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   179: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   182: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   185: invokestatic 114	abv:c	()Ljava/lang/String;
      //   188: ifnull +15 -> 203
      //   191: invokestatic 114	abv:c	()Ljava/lang/String;
      //   194: invokevirtual 117	java/lang/String:trim	()Ljava/lang/String;
      //   197: invokevirtual 121	java/lang/String:isEmpty	()Z
      //   200: ifeq +14 -> 214
      //   203: ldc 123
      //   205: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   208: ldc 125
      //   210: invokestatic 128	abv:c	(Ljava/lang/String;)Ljava/lang/String;
      //   213: pop
      //   214: new 85	java/lang/StringBuilder
      //   217: dup
      //   218: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   221: ldc -126
      //   223: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   226: ldc -124
      //   228: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   231: invokestatic 114	abv:c	()Ljava/lang/String;
      //   234: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   237: ldc -122
      //   239: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   242: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   245: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   248: new 136	java/net/URL
      //   251: dup
      //   252: new 85	java/lang/StringBuilder
      //   255: dup
      //   256: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   259: ldc -124
      //   261: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   264: invokestatic 114	abv:c	()Ljava/lang/String;
      //   267: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   270: ldc -122
      //   272: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   275: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   278: invokespecial 138	java/net/URL:<init>	(Ljava/lang/String;)V
      //   281: invokevirtual 142	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   284: checkcast 144	javax/net/ssl/HttpsURLConnection
      //   287: astore 14
      //   289: aload 14
      //   291: ldc -110
      //   293: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
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
      //   354: invokestatic 180	ax:a	(Ljava/net/URLConnection;)V
      //   357: aload 14
      //   359: invokevirtual 183	javax/net/ssl/HttpsURLConnection:connect	()V
      //   362: aload 14
      //   364: invokestatic 185	ax:b	(Ljava/net/URLConnection;)V
      //   367: aload_0
      //   368: getfield 14	abv$16:a	Labv;
      //   371: getfield 108	abv:e	Lorg/json/JSONObject;
      //   374: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   377: astore 15
      //   379: new 85	java/lang/StringBuilder
      //   382: dup
      //   383: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   386: ldc -69
      //   388: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   391: aload 15
      //   393: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   396: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   399: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   402: ldc -67
      //   404: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   407: aload 14
      //   409: invokestatic 180	ax:a	(Ljava/net/URLConnection;)V
      //   412: aload 14
      //   414: invokevirtual 193	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   417: astore 16
      //   419: aload 14
      //   421: invokestatic 185	ax:b	(Ljava/net/URLConnection;)V
      //   424: new 195	java/io/OutputStreamWriter
      //   427: dup
      //   428: aload 16
      //   430: invokespecial 198	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   433: astore 16
      //   435: aload 16
      //   437: aload 15
      //   439: invokevirtual 201	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   442: aload 16
      //   444: invokevirtual 204	java/io/OutputStreamWriter:close	()V
      //   447: ldc -50
      //   449: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   452: new 208	java/lang/StringBuffer
      //   455: dup
      //   456: ldc 70
      //   458: invokespecial 209	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   461: astore 15
      //   463: new 211	java/io/BufferedReader
      //   466: dup
      //   467: new 213	java/io/InputStreamReader
      //   470: dup
      //   471: aload 14
      //   473: invokestatic 217	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
      //   476: invokespecial 220	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   479: invokespecial 223	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   482: astore 14
      //   484: aload 14
      //   486: invokevirtual 226	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   489: astore 16
      //   491: aload 16
      //   493: ifnull +433 -> 926
      //   496: aload 15
      //   498: aload 16
      //   500: invokevirtual 229	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   503: pop
      //   504: goto -20 -> 484
      //   507: astore 13
      //   509: ldc -25
      //   511: aload 13
      //   513: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   516: invokevirtual 241	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   519: ifeq +3518 -> 4037
      //   522: new 85	java/lang/StringBuilder
      //   525: dup
      //   526: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   529: ldc -13
      //   531: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   534: aload 13
      //   536: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   539: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   542: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   545: aload 13
      //   547: invokestatic 251	abv:a	(Ljava/lang/Exception;)V
      //   550: return
      //   551: astore 13
      //   553: new 85	java/lang/StringBuilder
      //   556: dup
      //   557: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   560: ldc -3
      //   562: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   565: aload 13
      //   567: invokevirtual 254	java/lang/Exception:toString	()Ljava/lang/String;
      //   570: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   573: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   576: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   579: goto -537 -> 42
      //   582: astore 13
      //   584: aload 14
      //   586: aload 13
      //   588: invokestatic 257	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
      //   591: aload 13
      //   593: athrow
      //   594: astore 13
      //   596: new 85	java/lang/StringBuilder
      //   599: dup
      //   600: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   603: ldc_w 259
      //   606: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   609: aload 13
      //   611: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   614: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   617: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   620: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   623: invokeinterface 263 1 0
      //   628: ldc 56
      //   630: invokestatic 50	java/lang/System:currentTimeMillis	()J
      //   633: invokeinterface 269 4 0
      //   638: invokeinterface 272 1 0
      //   643: ldc_w 274
      //   646: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   649: iconst_0
      //   650: istore_1
      //   651: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   654: ldc_w 276
      //   657: ldc 70
      //   659: invokeinterface 74 3 0
      //   664: ldc_w 278
      //   667: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   670: ifne +3596 -> 4266
      //   673: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   676: ldc_w 284
      //   679: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   682: checkcast 292	java/lang/Boolean
      //   685: invokevirtual 295	java/lang/Boolean:booleanValue	()Z
      //   688: ifne +3411 -> 4099
      //   691: iconst_1
      //   692: istore_2
      //   693: invokestatic 298	abv:l	()Z
      //   696: istore 12
      //   698: iload_1
      //   699: invokestatic 301	abv:e	()I
      //   702: if_icmpge +50 -> 752
      //   705: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   708: ldc_w 303
      //   711: ldc_w 305
      //   714: invokeinterface 74 3 0
      //   719: ldc_w 305
      //   722: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   725: ifne +3379 -> 4104
      //   728: iconst_1
      //   729: istore_3
      //   730: invokestatic 308	abv:m	()Ljava/lang/String;
      //   733: ifnull +3376 -> 4109
      //   736: iconst_1
      //   737: istore 4
      //   739: iload_3
      //   740: ifeq +3375 -> 4115
      //   743: iload_2
      //   744: ifne +8 -> 752
      //   747: iload 12
      //   749: ifeq +3366 -> 4115
      //   752: new 85	java/lang/StringBuilder
      //   755: dup
      //   756: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   759: ldc_w 310
      //   762: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   765: iload_1
      //   766: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   769: ldc_w 315
      //   772: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   775: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   778: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   781: iload_1
      //   782: istore_2
      //   783: iload_1
      //   784: iconst_2
      //   785: if_icmpge +46 -> 831
      //   788: aload_0
      //   789: getfield 14	abv$16:a	Labv;
      //   792: invokestatic 317	abv:e	(Labv;)Ljava/lang/String;
      //   795: ifnull +3371 -> 4166
      //   798: iconst_1
      //   799: istore_3
      //   800: invokestatic 308	abv:m	()Ljava/lang/String;
      //   803: ifnull +3368 -> 4171
      //   806: invokestatic 308	abv:m	()Ljava/lang/String;
      //   809: invokevirtual 121	java/lang/String:isEmpty	()Z
      //   812: ifne +3359 -> 4171
      //   815: iconst_1
      //   816: istore 4
      //   818: iload_1
      //   819: istore_2
      //   820: iload 4
      //   822: ifne +9 -> 831
      //   825: iload_3
      //   826: ifeq +3351 -> 4177
      //   829: iload_1
      //   830: istore_2
      //   831: iload_2
      //   832: iconst_2
      //   833: if_icmpge +19 -> 852
      //   836: aload_0
      //   837: getfield 14	abv$16:a	Labv;
      //   840: invokestatic 320	abv:f	(Labv;)Ljava/lang/String;
      //   843: ifnull +3376 -> 4219
      //   846: iconst_1
      //   847: istore_1
      //   848: iload_1
      //   849: ifeq +3375 -> 4224
      //   852: invokestatic 326	android/os/Message:obtain	()Landroid/os/Message;
      //   855: astore 13
      //   857: new 328	android/os/Bundle
      //   860: dup
      //   861: invokespecial 329	android/os/Bundle:<init>	()V
      //   864: astore 14
      //   866: aload 14
      //   868: ldc_w 331
      //   871: invokestatic 334	abv:n	()Z
      //   874: invokevirtual 338	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   877: aload 13
      //   879: aload 14
      //   881: invokevirtual 342	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   884: aload_0
      //   885: getfield 14	abv$16:a	Labv;
      //   888: invokestatic 345	abv:g	(Labv;)Landroid/os/Handler;
      //   891: ifnull -341 -> 550
      //   894: ldc_w 347
      //   897: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   900: aload_0
      //   901: getfield 14	abv$16:a	Labv;
      //   904: invokestatic 345	abv:g	(Labv;)Landroid/os/Handler;
      //   907: aload 13
      //   909: invokevirtual 353	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   912: pop
      //   913: return
      //   914: astore 13
      //   916: aload 14
      //   918: aload 13
      //   920: invokestatic 257	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
      //   923: aload 13
      //   925: athrow
      //   926: aload 15
      //   928: invokevirtual 354	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   931: astore 14
      //   933: new 85	java/lang/StringBuilder
      //   936: dup
      //   937: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   940: ldc_w 356
      //   943: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   946: aload 14
      //   948: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   951: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   954: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   957: new 110	org/json/JSONObject
      //   960: dup
      //   961: aload 14
      //   963: invokespecial 357	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   966: astore 14
      //   968: aload 14
      //   970: astore 13
      //   972: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   975: ldc_w 276
      //   978: ldc 70
      //   980: invokeinterface 74 3 0
      //   985: ldc_w 278
      //   988: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   991: istore 12
      //   993: iload_1
      //   994: istore_2
      //   995: iload 12
      //   997: ifne +134 -> 1131
      //   1000: iload_1
      //   1001: istore_2
      //   1002: aload 13
      //   1004: ifnull +127 -> 1131
      //   1007: aload 13
      //   1009: ldc_w 359
      //   1012: invokevirtual 363	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1015: astore 14
      //   1017: iconst_0
      //   1018: istore 5
      //   1020: iconst_0
      //   1021: istore 4
      //   1023: iload_1
      //   1024: istore_2
      //   1025: iload 5
      //   1027: istore_3
      //   1028: iload 4
      //   1030: aload 14
      //   1032: invokevirtual 368	org/json/JSONArray:length	()I
      //   1035: if_icmpge +90 -> 1125
      //   1038: ldc_w 370
      //   1041: aload 14
      //   1043: iload 4
      //   1045: invokevirtual 373	org/json/JSONArray:getString	(I)Ljava/lang/String;
      //   1048: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1051: ifeq +1044 -> 2095
      //   1054: iconst_1
      //   1055: istore_3
      //   1056: iload_1
      //   1057: istore_2
      //   1058: iload_1
      //   1059: iconst_4
      //   1060: if_icmpge +7 -> 1067
      //   1063: iload_1
      //   1064: iconst_1
      //   1065: iadd
      //   1066: istore_2
      //   1067: new 85	java/lang/StringBuilder
      //   1070: dup
      //   1071: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1074: ldc_w 375
      //   1077: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1080: invokestatic 377	abv:d	()Ljava/util/HashMap;
      //   1083: iload_2
      //   1084: invokestatic 383	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1087: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1090: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1093: ldc_w 385
      //   1096: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1099: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1102: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1105: invokestatic 377	abv:d	()Ljava/util/HashMap;
      //   1108: iload_2
      //   1109: invokestatic 383	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1112: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1115: checkcast 379	java/lang/Integer
      //   1118: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1121: i2l
      //   1122: invokestatic 394	java/lang/Thread:sleep	(J)V
      //   1125: iload_3
      //   1126: ifne +5 -> 1131
      //   1129: iconst_0
      //   1130: istore_2
      //   1131: iload_2
      //   1132: ifne +3183 -> 4315
      //   1135: aload 13
      //   1137: ifnull -517 -> 620
      //   1140: new 85	java/lang/StringBuilder
      //   1143: dup
      //   1144: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1147: ldc_w 396
      //   1150: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1153: aload 13
      //   1155: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1158: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1161: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1164: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1167: aconst_null
      //   1168: astore 14
      //   1170: aload 13
      //   1172: ldc_w 398
      //   1175: invokevirtual 402	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   1178: astore 15
      //   1180: aload 15
      //   1182: astore 14
      //   1184: new 85	java/lang/StringBuilder
      //   1187: dup
      //   1188: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1191: ldc_w 404
      //   1194: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1197: aload 15
      //   1199: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1202: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1205: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1208: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1211: aload 15
      //   1213: astore 14
      //   1215: aload 14
      //   1217: ifnull +750 -> 1967
      //   1220: aload 14
      //   1222: ldc_w 406
      //   1225: invokevirtual 408	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1228: astore 15
      //   1230: new 85	java/lang/StringBuilder
      //   1233: dup
      //   1234: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1237: ldc_w 410
      //   1240: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1243: aload 15
      //   1245: invokevirtual 411	java/lang/String:toString	()Ljava/lang/String;
      //   1248: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1251: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1254: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1257: aload 15
      //   1259: invokestatic 413	abv:d	(Ljava/lang/String;)Ljava/lang/String;
      //   1262: pop
      //   1263: aload 14
      //   1265: ldc_w 415
      //   1268: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1271: ldc_w 420
      //   1274: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1277: ifeq +8 -> 1285
      //   1280: iconst_0
      //   1281: invokestatic 424	abv:c	(Z)Z
      //   1284: pop
      //   1285: aload 14
      //   1287: ldc_w 426
      //   1290: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1293: checkcast 79	java/lang/String
      //   1296: invokevirtual 429	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   1299: astore 15
      //   1301: new 85	java/lang/StringBuilder
      //   1304: dup
      //   1305: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1308: ldc_w 431
      //   1311: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1314: aload 15
      //   1316: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1319: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1322: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1325: aload_0
      //   1326: getfield 14	abv$16:a	Labv;
      //   1329: aload 15
      //   1331: invokestatic 434	abv:c	(Labv;Ljava/lang/String;)V
      //   1334: aload 14
      //   1336: ldc_w 436
      //   1339: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1342: ldc_w 278
      //   1345: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1348: ifeq +47 -> 1395
      //   1351: ldc_w 438
      //   1354: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1357: getstatic 441	abv:c	Landroid/content/Context;
      //   1360: ldc_w 443
      //   1363: iconst_0
      //   1364: invokevirtual 449	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   1367: invokestatic 452	abv:a	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   1370: pop
      //   1371: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   1374: invokeinterface 263 1 0
      //   1379: ldc_w 276
      //   1382: ldc_w 454
      //   1385: invokeinterface 458 3 0
      //   1390: invokeinterface 272 1 0
      //   1395: aload 14
      //   1397: ldc_w 460
      //   1400: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1403: checkcast 379	java/lang/Integer
      //   1406: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1409: invokestatic 463	abv:b	(I)I
      //   1412: pop
      //   1413: invokestatic 301	abv:e	()I
      //   1416: ifge +777 -> 2193
      //   1419: new 85	java/lang/StringBuilder
      //   1422: dup
      //   1423: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1426: ldc_w 465
      //   1429: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1432: invokestatic 301	abv:e	()I
      //   1435: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1438: ldc_w 467
      //   1441: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1444: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1447: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1450: iconst_0
      //   1451: invokestatic 463	abv:b	(I)I
      //   1454: pop
      //   1455: invokestatic 469	abv:f	()Z
      //   1458: ifne +89 -> 1547
      //   1461: aload 14
      //   1463: ldc_w 471
      //   1466: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1469: ifnull +78 -> 1547
      //   1472: aload 14
      //   1474: ldc_w 471
      //   1477: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1480: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1483: ldc_w 379
      //   1486: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1489: ifeq +58 -> 1547
      //   1492: aload 14
      //   1494: ldc_w 471
      //   1497: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1500: checkcast 379	java/lang/Integer
      //   1503: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1506: istore_1
      //   1507: iload_1
      //   1508: bipush 60
      //   1510: if_icmpge +759 -> 2269
      //   1513: new 85	java/lang/StringBuilder
      //   1516: dup
      //   1517: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1520: ldc_w 473
      //   1523: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1526: iload_1
      //   1527: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1530: ldc_w 475
      //   1533: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1536: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1539: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1542: iconst_1
      //   1543: invokestatic 477	abv:d	(Z)Z
      //   1546: pop
      //   1547: aload 14
      //   1549: ldc 100
      //   1551: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1554: ifnull +92 -> 1646
      //   1557: aload 14
      //   1559: ldc 100
      //   1561: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1564: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1567: ldc_w 379
      //   1570: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1573: ifeq +73 -> 1646
      //   1576: aload 14
      //   1578: ldc 100
      //   1580: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1583: checkcast 379	java/lang/Integer
      //   1586: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1589: istore_1
      //   1590: iload_1
      //   1591: ifge +765 -> 2356
      //   1594: new 85	java/lang/StringBuilder
      //   1597: dup
      //   1598: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1601: ldc_w 479
      //   1604: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1607: iload_1
      //   1608: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1611: ldc_w 481
      //   1614: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1617: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1620: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1623: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   1626: invokeinterface 263 1 0
      //   1631: ldc 100
      //   1633: ldc2_w 101
      //   1636: invokeinterface 269 4 0
      //   1641: invokeinterface 272 1 0
      //   1646: aload 14
      //   1648: ldc_w 483
      //   1651: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1654: ifnull +77 -> 1731
      //   1657: aload 14
      //   1659: ldc_w 483
      //   1662: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1665: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1668: ldc_w 379
      //   1671: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1674: ifeq +57 -> 1731
      //   1677: aload 14
      //   1679: ldc_w 483
      //   1682: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1685: checkcast 379	java/lang/Integer
      //   1688: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1691: istore_1
      //   1692: iload_1
      //   1693: iconst_1
      //   1694: if_icmpge +782 -> 2476
      //   1697: new 85	java/lang/StringBuilder
      //   1700: dup
      //   1701: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1704: ldc_w 485
      //   1707: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1710: iload_1
      //   1711: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1714: ldc_w 487
      //   1717: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1720: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1723: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1726: iconst_1
      //   1727: invokestatic 489	abv:d	(I)I
      //   1730: pop
      //   1731: aload 14
      //   1733: ldc_w 491
      //   1736: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1739: checkcast 379	java/lang/Integer
      //   1742: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1745: istore_2
      //   1746: iload_2
      //   1747: bipush 10
      //   1749: if_icmpge +808 -> 2557
      //   1752: new 85	java/lang/StringBuilder
      //   1755: dup
      //   1756: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1759: ldc_w 493
      //   1762: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1765: iload_2
      //   1766: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1769: ldc_w 495
      //   1772: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1775: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1778: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1781: bipush 10
      //   1783: istore_1
      //   1784: new 85	java/lang/StringBuilder
      //   1787: dup
      //   1788: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1791: ldc_w 497
      //   1794: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1797: iload_1
      //   1798: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1801: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1804: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1807: iload_1
      //   1808: putstatic 502	abx:a	I
      //   1811: aload 14
      //   1813: ldc_w 504
      //   1816: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1819: checkcast 379	java/lang/Integer
      //   1822: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1825: istore_2
      //   1826: iload_2
      //   1827: iconst_3
      //   1828: if_icmpge +774 -> 2602
      //   1831: new 85	java/lang/StringBuilder
      //   1834: dup
      //   1835: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1838: ldc_w 506
      //   1841: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1844: iload_2
      //   1845: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1848: ldc_w 508
      //   1851: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1854: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1857: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1860: iconst_3
      //   1861: istore_1
      //   1862: new 85	java/lang/StringBuilder
      //   1865: dup
      //   1866: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1869: ldc_w 510
      //   1872: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1875: iload_1
      //   1876: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1879: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1882: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1885: iload_1
      //   1886: putstatic 512	abx:c	I
      //   1889: aload 14
      //   1891: ldc_w 514
      //   1894: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1897: checkcast 379	java/lang/Integer
      //   1900: invokevirtual 388	java/lang/Integer:intValue	()I
      //   1903: istore_2
      //   1904: iload_2
      //   1905: iconst_1
      //   1906: if_icmpge +739 -> 2645
      //   1909: new 85	java/lang/StringBuilder
      //   1912: dup
      //   1913: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1916: ldc_w 516
      //   1919: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1922: iload_2
      //   1923: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1926: ldc_w 518
      //   1929: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1932: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1935: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1938: iconst_1
      //   1939: istore_1
      //   1940: new 85	java/lang/StringBuilder
      //   1943: dup
      //   1944: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1947: ldc_w 520
      //   1950: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1953: iload_1
      //   1954: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1957: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1960: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   1963: iload_1
      //   1964: putstatic 522	abx:b	I
      //   1967: aload 13
      //   1969: ldc_w 524
      //   1972: invokevirtual 363	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1975: astore 15
      //   1977: new 85	java/lang/StringBuilder
      //   1980: dup
      //   1981: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   1984: ldc_w 526
      //   1987: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1990: aload 15
      //   1992: invokevirtual 527	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1995: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1998: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2001: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2004: iconst_0
      //   2005: istore_1
      //   2006: iload_1
      //   2007: aload 15
      //   2009: invokevirtual 368	org/json/JSONArray:length	()I
      //   2012: if_icmpge +741 -> 2753
      //   2015: aload 15
      //   2017: iload_1
      //   2018: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2021: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   2024: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2027: ldc_w 536
      //   2030: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2033: ifeq +657 -> 2690
      //   2036: ldc_w 538
      //   2039: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2042: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   2045: ldc_w 536
      //   2048: iconst_0
      //   2049: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2052: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2055: pop
      //   2056: iload_1
      //   2057: iconst_1
      //   2058: iadd
      //   2059: istore_1
      //   2060: goto -54 -> 2006
      //   2063: astore 14
      //   2065: new 85	java/lang/StringBuilder
      //   2068: dup
      //   2069: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2072: ldc_w 547
      //   2075: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2078: aload 14
      //   2080: invokevirtual 548	org/json/JSONException:toString	()Ljava/lang/String;
      //   2083: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2086: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2089: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2092: goto -1120 -> 972
      //   2095: iload 4
      //   2097: iconst_1
      //   2098: iadd
      //   2099: istore 4
      //   2101: goto -1078 -> 1023
      //   2104: astore 14
      //   2106: iconst_0
      //   2107: istore_2
      //   2108: goto -977 -> 1131
      //   2111: astore 15
      //   2113: ldc_w 550
      //   2116: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2119: goto -904 -> 1215
      //   2122: astore 13
      //   2124: aload 13
      //   2126: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   2129: ldc -25
      //   2131: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   2134: ifeq +1873 -> 4007
      //   2137: new 85	java/lang/StringBuilder
      //   2140: dup
      //   2141: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2144: ldc -13
      //   2146: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2149: aload 13
      //   2151: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   2154: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2157: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   2160: aload 13
      //   2162: invokestatic 251	abv:a	(Ljava/lang/Exception;)V
      //   2165: return
      //   2166: astore 13
      //   2168: goto -1659 -> 509
      //   2171: astore 15
      //   2173: ldc_w 552
      //   2176: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   2179: goto -916 -> 1263
      //   2182: astore 15
      //   2184: ldc_w 550
      //   2187: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   2190: goto -905 -> 1285
      //   2193: invokestatic 301	abv:e	()I
      //   2196: bipush 120
      //   2198: if_icmple +43 -> 2241
      //   2201: new 85	java/lang/StringBuilder
      //   2204: dup
      //   2205: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2208: ldc_w 554
      //   2211: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2214: invokestatic 301	abv:e	()I
      //   2217: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2220: ldc_w 556
      //   2223: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2226: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2229: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2232: bipush 120
      //   2234: invokestatic 463	abv:b	(I)I
      //   2237: pop
      //   2238: goto -783 -> 1455
      //   2241: new 85	java/lang/StringBuilder
      //   2244: dup
      //   2245: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2248: ldc_w 558
      //   2251: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2254: invokestatic 301	abv:e	()I
      //   2257: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2260: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2263: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2266: goto -811 -> 1455
      //   2269: iload_1
      //   2270: ldc_w 559
      //   2273: if_icmple +42 -> 2315
      //   2276: new 85	java/lang/StringBuilder
      //   2279: dup
      //   2280: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2283: ldc_w 561
      //   2286: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2289: iload_1
      //   2290: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2293: ldc_w 563
      //   2296: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2299: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2302: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2305: ldc_w 564
      //   2308: invokestatic 566	abv:c	(I)I
      //   2311: pop
      //   2312: goto -770 -> 1542
      //   2315: iload_1
      //   2316: sipush 1000
      //   2319: imul
      //   2320: invokestatic 566	abv:c	(I)I
      //   2323: pop
      //   2324: new 85	java/lang/StringBuilder
      //   2327: dup
      //   2328: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2331: ldc_w 568
      //   2334: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2337: iload_1
      //   2338: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2341: ldc_w 315
      //   2344: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2347: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2350: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2353: goto -811 -> 1542
      //   2356: iload_1
      //   2357: ldc_w 559
      //   2360: if_icmple +58 -> 2418
      //   2363: new 85	java/lang/StringBuilder
      //   2366: dup
      //   2367: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2370: ldc_w 479
      //   2373: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2376: iload_1
      //   2377: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2380: ldc_w 570
      //   2383: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2386: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2389: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2392: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   2395: invokeinterface 263 1 0
      //   2400: ldc 100
      //   2402: ldc2_w 571
      //   2405: invokeinterface 269 4 0
      //   2410: invokeinterface 272 1 0
      //   2415: goto -769 -> 1646
      //   2418: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   2421: invokeinterface 263 1 0
      //   2426: ldc 100
      //   2428: iload_1
      //   2429: sipush 1000
      //   2432: imul
      //   2433: i2l
      //   2434: invokeinterface 269 4 0
      //   2439: invokeinterface 272 1 0
      //   2444: new 85	java/lang/StringBuilder
      //   2447: dup
      //   2448: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2451: ldc_w 574
      //   2454: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2457: iload_1
      //   2458: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2461: ldc_w 315
      //   2464: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2467: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2470: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2473: goto -827 -> 1646
      //   2476: iload_1
      //   2477: bipush 30
      //   2479: if_icmple +41 -> 2520
      //   2482: new 85	java/lang/StringBuilder
      //   2485: dup
      //   2486: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2489: ldc_w 485
      //   2492: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2495: iload_1
      //   2496: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2499: ldc_w 576
      //   2502: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2505: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2508: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2511: bipush 30
      //   2513: invokestatic 489	abv:d	(I)I
      //   2516: pop
      //   2517: goto -786 -> 1731
      //   2520: new 85	java/lang/StringBuilder
      //   2523: dup
      //   2524: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2527: ldc_w 578
      //   2530: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2533: iload_1
      //   2534: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2537: ldc_w 315
      //   2540: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2543: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2546: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2549: iload_1
      //   2550: invokestatic 489	abv:d	(I)I
      //   2553: pop
      //   2554: goto -823 -> 1731
      //   2557: iload_2
      //   2558: istore_1
      //   2559: iload_2
      //   2560: sipush 5000
      //   2563: if_icmple -779 -> 1784
      //   2566: new 85	java/lang/StringBuilder
      //   2569: dup
      //   2570: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2573: ldc_w 493
      //   2576: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2579: iload_2
      //   2580: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2583: ldc_w 495
      //   2586: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2589: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2592: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2595: sipush 5000
      //   2598: istore_1
      //   2599: goto -815 -> 1784
      //   2602: iload_2
      //   2603: istore_1
      //   2604: iload_2
      //   2605: bipush 60
      //   2607: if_icmple -745 -> 1862
      //   2610: new 85	java/lang/StringBuilder
      //   2613: dup
      //   2614: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2617: ldc_w 506
      //   2620: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2623: iload_2
      //   2624: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2627: ldc_w 508
      //   2630: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2633: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2636: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2639: bipush 60
      //   2641: istore_1
      //   2642: goto -780 -> 1862
      //   2645: iload_2
      //   2646: istore_1
      //   2647: iload_2
      //   2648: sipush 10080
      //   2651: if_icmple -711 -> 1940
      //   2654: new 85	java/lang/StringBuilder
      //   2657: dup
      //   2658: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2661: ldc_w 516
      //   2664: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2667: iload_2
      //   2668: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2671: ldc_w 518
      //   2674: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2677: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2680: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2683: sipush 10080
      //   2686: istore_1
      //   2687: goto -747 -> 1940
      //   2690: aload 15
      //   2692: iload_1
      //   2693: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2696: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   2699: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2702: ldc_w 580
      //   2705: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2708: ifeq +536 -> 3244
      //   2711: ldc_w 582
      //   2714: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2717: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   2720: ldc_w 580
      //   2723: iconst_0
      //   2724: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2727: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2730: pop
      //   2731: goto -675 -> 2056
      //   2734: astore 15
      //   2736: ldc_w 584
      //   2739: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   2742: getstatic 589	abw:b	Z
      //   2745: ifeq +8 -> 2753
      //   2748: aload 15
      //   2750: invokevirtual 592	java/lang/Exception:printStackTrace	()V
      //   2753: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   2756: ldc_w 594
      //   2759: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2762: checkcast 292	java/lang/Boolean
      //   2765: invokevirtual 295	java/lang/Boolean:booleanValue	()Z
      //   2768: istore 12
      //   2770: iload 12
      //   2772: ifeq +768 -> 3540
      //   2775: getstatic 441	abv:c	Landroid/content/Context;
      //   2778: ldc_w 596
      //   2781: invokevirtual 599	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2784: checkcast 601	android/telephony/TelephonyManager
      //   2787: invokevirtual 604	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
      //   2790: invokestatic 606	abv:e	(Ljava/lang/String;)Ljava/lang/String;
      //   2793: pop
      //   2794: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   2797: ldc_w 608
      //   2800: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2803: checkcast 292	java/lang/Boolean
      //   2806: invokevirtual 295	java/lang/Boolean:booleanValue	()Z
      //   2809: istore 12
      //   2811: iload 12
      //   2813: ifeq +768 -> 3581
      //   2816: getstatic 441	abv:c	Landroid/content/Context;
      //   2819: ldc_w 610
      //   2822: invokevirtual 599	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2825: checkcast 612	android/net/wifi/WifiManager
      //   2828: invokevirtual 616	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
      //   2831: invokevirtual 621	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
      //   2834: invokestatic 623	abv:f	(Ljava/lang/String;)Ljava/lang/String;
      //   2837: pop
      //   2838: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   2841: ldc_w 625
      //   2844: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2847: checkcast 292	java/lang/Boolean
      //   2850: invokevirtual 295	java/lang/Boolean:booleanValue	()Z
      //   2853: istore 12
      //   2855: iload 12
      //   2857: ifeq +779 -> 3636
      //   2860: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   2863: ldc_w 276
      //   2866: ldc 70
      //   2868: invokeinterface 74 3 0
      //   2873: ldc_w 278
      //   2876: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2879: ifne +161 -> 3040
      //   2882: getstatic 441	abv:c	Landroid/content/Context;
      //   2885: invokevirtual 629	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   2888: astore 17
      //   2890: aload 17
      //   2892: sipush 128
      //   2895: invokevirtual 635	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2898: invokeinterface 641 1 0
      //   2903: astore 18
      //   2905: aload 18
      //   2907: invokeinterface 646 1 0
      //   2912: ifeq +128 -> 3040
      //   2915: aload 18
      //   2917: invokeinterface 650 1 0
      //   2922: checkcast 652	android/content/pm/ApplicationInfo
      //   2925: astore 19
      //   2927: aload 19
      //   2929: invokestatic 655	abv:a	(Landroid/content/pm/ApplicationInfo;)Z
      //   2932: istore 12
      //   2934: iload 12
      //   2936: ifne -31 -> 2905
      //   2939: aconst_null
      //   2940: astore 15
      //   2942: aload 17
      //   2944: aload 19
      //   2946: getfield 659	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2949: iconst_0
      //   2950: invokevirtual 663	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   2953: astore 16
      //   2955: aload 16
      //   2957: astore 15
      //   2959: aload 15
      //   2961: ifnull -56 -> 2905
      //   2964: invokestatic 667	abv:h	()Ljava/util/List;
      //   2967: new 669	abv$b
      //   2970: dup
      //   2971: aload_0
      //   2972: getfield 14	abv$16:a	Labv;
      //   2975: aload 15
      //   2977: getfield 672	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   2980: new 85	java/lang/StringBuilder
      //   2983: dup
      //   2984: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   2987: aload 15
      //   2989: getfield 676	android/content/pm/PackageInfo:firstInstallTime	J
      //   2992: invokevirtual 679	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2995: ldc 70
      //   2997: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3000: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3003: new 85	java/lang/StringBuilder
      //   3006: dup
      //   3007: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3010: aload 15
      //   3012: getfield 682	android/content/pm/PackageInfo:lastUpdateTime	J
      //   3015: invokevirtual 679	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3018: ldc 70
      //   3020: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3023: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3026: invokespecial 685	abv$b:<init>	(Labv;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   3029: invokeinterface 688 2 0
      //   3034: pop
      //   3035: goto -130 -> 2905
      //   3038: astore 15
      //   3040: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3043: ldc_w 690
      //   3046: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   3049: checkcast 292	java/lang/Boolean
      //   3052: invokevirtual 295	java/lang/Boolean:booleanValue	()Z
      //   3055: istore 12
      //   3057: iload 12
      //   3059: ifeq +618 -> 3677
      //   3062: getstatic 441	abv:c	Landroid/content/Context;
      //   3065: ldc_w 692
      //   3068: invokevirtual 599	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   3071: checkcast 694	android/view/WindowManager
      //   3074: astore 15
      //   3076: aload_0
      //   3077: getfield 14	abv$16:a	Labv;
      //   3080: aload 15
      //   3082: invokeinterface 698 1 0
      //   3087: invokevirtual 703	android/view/Display:getHeight	()I
      //   3090: invokestatic 706	abv:a	(Labv;I)I
      //   3093: pop
      //   3094: aload_0
      //   3095: getfield 14	abv$16:a	Labv;
      //   3098: aload 15
      //   3100: invokeinterface 698 1 0
      //   3105: invokevirtual 709	android/view/Display:getWidth	()I
      //   3108: invokestatic 711	abv:b	(Labv;I)I
      //   3111: pop
      //   3112: new 85	java/lang/StringBuilder
      //   3115: dup
      //   3116: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3119: ldc_w 713
      //   3122: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3125: aload_0
      //   3126: getfield 14	abv$16:a	Labv;
      //   3129: invokestatic 716	abv:c	(Labv;)I
      //   3132: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3135: ldc_w 718
      //   3138: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3141: aload_0
      //   3142: getfield 14	abv$16:a	Labv;
      //   3145: invokestatic 720	abv:d	(Labv;)I
      //   3148: invokevirtual 313	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3151: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3154: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3157: aload 13
      //   3159: ldc_w 722
      //   3162: invokevirtual 363	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3165: astore 15
      //   3167: new 85	java/lang/StringBuilder
      //   3170: dup
      //   3171: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3174: ldc_w 724
      //   3177: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3180: aload 15
      //   3182: invokevirtual 527	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3185: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3188: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3191: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3194: iconst_0
      //   3195: istore_1
      //   3196: iload_1
      //   3197: aload 15
      //   3199: invokevirtual 368	org/json/JSONArray:length	()I
      //   3202: if_icmpge +513 -> 3715
      //   3205: aload 15
      //   3207: iload_1
      //   3208: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3211: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3214: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3217: ldc_w 726
      //   3220: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3223: ifeq +14 -> 3237
      //   3226: ldc_w 728
      //   3229: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3232: iconst_1
      //   3233: invokestatic 730	abv:e	(Z)Z
      //   3236: pop
      //   3237: iload_1
      //   3238: iconst_1
      //   3239: iadd
      //   3240: istore_1
      //   3241: goto -45 -> 3196
      //   3244: aload 15
      //   3246: iload_1
      //   3247: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3250: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3253: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3256: ldc_w 284
      //   3259: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3262: ifeq +26 -> 3288
      //   3265: ldc_w 732
      //   3268: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3271: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3274: ldc_w 284
      //   3277: iconst_0
      //   3278: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3281: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3284: pop
      //   3285: goto -1229 -> 2056
      //   3288: aload 15
      //   3290: iload_1
      //   3291: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3294: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3297: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3300: ldc_w 608
      //   3303: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3306: ifeq +26 -> 3332
      //   3309: ldc_w 734
      //   3312: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3315: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3318: ldc_w 608
      //   3321: iconst_0
      //   3322: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3325: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3328: pop
      //   3329: goto -1273 -> 2056
      //   3332: aload 15
      //   3334: iload_1
      //   3335: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3338: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3341: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3344: ldc_w 594
      //   3347: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3350: ifeq +26 -> 3376
      //   3353: ldc_w 736
      //   3356: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3359: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3362: ldc_w 594
      //   3365: iconst_0
      //   3366: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3369: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3372: pop
      //   3373: goto -1317 -> 2056
      //   3376: aload 15
      //   3378: iload_1
      //   3379: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3382: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3385: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3388: ldc_w 690
      //   3391: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3394: ifeq +26 -> 3420
      //   3397: ldc_w 738
      //   3400: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3403: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3406: ldc_w 690
      //   3409: iconst_0
      //   3410: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3413: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3416: pop
      //   3417: goto -1361 -> 2056
      //   3420: aload 15
      //   3422: iload_1
      //   3423: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3426: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3429: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3432: ldc_w 625
      //   3435: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3438: ifeq +26 -> 3464
      //   3441: ldc_w 740
      //   3444: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3447: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3450: ldc_w 625
      //   3453: iconst_0
      //   3454: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3457: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3460: pop
      //   3461: goto -1405 -> 2056
      //   3464: aload 15
      //   3466: iload_1
      //   3467: invokevirtual 530	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3470: invokevirtual 531	java/lang/Object:toString	()Ljava/lang/String;
      //   3473: invokevirtual 534	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3476: ldc_w 742
      //   3479: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3482: ifeq -1426 -> 2056
      //   3485: ldc_w 744
      //   3488: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3491: invokestatic 282	abv:g	()Ljava/util/HashMap;
      //   3494: ldc_w 742
      //   3497: iconst_0
      //   3498: invokestatic 541	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   3501: invokevirtual 545	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   3504: pop
      //   3505: goto -1449 -> 2056
      //   3508: astore 15
      //   3510: new 85	java/lang/StringBuilder
      //   3513: dup
      //   3514: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3517: ldc_w 746
      //   3520: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3523: aload 15
      //   3525: invokevirtual 254	java/lang/Exception:toString	()Ljava/lang/String;
      //   3528: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3531: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3534: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   3537: goto -743 -> 2794
      //   3540: ldc_w 748
      //   3543: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3546: goto -752 -> 2794
      //   3549: astore 15
      //   3551: new 85	java/lang/StringBuilder
      //   3554: dup
      //   3555: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3558: ldc_w 750
      //   3561: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3564: aload 15
      //   3566: invokevirtual 254	java/lang/Exception:toString	()Ljava/lang/String;
      //   3569: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3572: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3575: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3578: goto -740 -> 2838
      //   3581: ldc_w 752
      //   3584: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3587: goto -749 -> 2838
      //   3590: astore 16
      //   3592: new 85	java/lang/StringBuilder
      //   3595: dup
      //   3596: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3599: ldc_w 754
      //   3602: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3605: aload 19
      //   3607: getfield 659	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   3610: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3613: ldc_w 756
      //   3616: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3619: aload 16
      //   3621: invokevirtual 757	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
      //   3624: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3627: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3630: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   3633: goto -674 -> 2959
      //   3636: ldc_w 759
      //   3639: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3642: goto -602 -> 3040
      //   3645: astore 15
      //   3647: new 85	java/lang/StringBuilder
      //   3650: dup
      //   3651: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3654: ldc_w 761
      //   3657: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3660: aload 15
      //   3662: invokevirtual 254	java/lang/Exception:toString	()Ljava/lang/String;
      //   3665: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3668: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3671: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   3674: goto -517 -> 3157
      //   3677: ldc_w 763
      //   3680: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3683: goto -526 -> 3157
      //   3686: astore 15
      //   3688: new 85	java/lang/StringBuilder
      //   3691: dup
      //   3692: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3695: ldc_w 765
      //   3698: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3701: aload 15
      //   3703: invokevirtual 254	java/lang/Exception:toString	()Ljava/lang/String;
      //   3706: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3709: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3712: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   3715: aload 13
      //   3717: ldc_w 767
      //   3720: invokevirtual 402	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3723: ldc_w 769
      //   3726: invokevirtual 402	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3729: astore 15
      //   3731: aload_0
      //   3732: getfield 14	abv$16:a	Labv;
      //   3735: aload 15
      //   3737: invokestatic 772	abv:a	(Labv;Lorg/json/JSONObject;)V
      //   3740: invokestatic 775	abv:i	()Z
      //   3743: ifeq +18 -> 3761
      //   3746: invokestatic 778	abv:j	()Z
      //   3749: ifeq +12 -> 3761
      //   3752: getstatic 441	abv:c	Landroid/content/Context;
      //   3755: invokestatic 781	abx:a	(Landroid/content/Context;)Labx;
      //   3758: invokevirtual 783	abx:a	()V
      //   3761: aload 13
      //   3763: ldc_w 785
      //   3766: invokevirtual 363	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3769: invokestatic 788	abv:a	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3772: pop
      //   3773: new 85	java/lang/StringBuilder
      //   3776: dup
      //   3777: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3780: ldc_w 790
      //   3783: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3786: invokestatic 794	abv:k	()Lorg/json/JSONArray;
      //   3789: invokevirtual 527	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3792: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3795: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3798: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3801: invokestatic 54	abv:b	()Landroid/content/SharedPreferences;
      //   3804: invokeinterface 263 1 0
      //   3809: ldc_w 796
      //   3812: invokestatic 794	abv:k	()Lorg/json/JSONArray;
      //   3815: invokevirtual 527	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3818: invokeinterface 458 3 0
      //   3823: invokeinterface 272 1 0
      //   3828: aload 14
      //   3830: ldc_w 798
      //   3833: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3836: ifnull +88 -> 3924
      //   3839: aload 14
      //   3841: ldc_w 798
      //   3844: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3847: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3850: ldc_w 292
      //   3853: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3856: ifeq +141 -> 3997
      //   3859: aload 14
      //   3861: ldc_w 798
      //   3864: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3867: checkcast 292	java/lang/Boolean
      //   3870: invokevirtual 295	java/lang/Boolean:booleanValue	()Z
      //   3873: ifeq +124 -> 3997
      //   3876: iconst_1
      //   3877: istore_1
      //   3878: aload 14
      //   3880: ldc_w 798
      //   3883: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3886: invokevirtual 235	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3889: ldc 79
      //   3891: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3894: ifeq +108 -> 4002
      //   3897: ldc_w 278
      //   3900: aload 14
      //   3902: ldc_w 798
      //   3905: invokevirtual 418	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3908: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3911: ifeq +91 -> 4002
      //   3914: iconst_1
      //   3915: istore_2
      //   3916: goto +424 -> 4340
      //   3919: iconst_1
      //   3920: invokestatic 800	abv:f	(Z)Z
      //   3923: pop
      //   3924: aload 13
      //   3926: ldc_w 802
      //   3929: invokevirtual 408	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3932: astore 13
      //   3934: new 85	java/lang/StringBuilder
      //   3937: dup
      //   3938: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   3941: ldc_w 804
      //   3944: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3947: aload 13
      //   3949: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3952: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3955: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   3958: aload 13
      //   3960: ldc_w 806
      //   3963: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3966: ifeq -3346 -> 620
      //   3969: iconst_1
      //   3970: invokestatic 808	abv:g	(Z)Z
      //   3973: pop
      //   3974: return
      //   3975: astore 13
      //   3977: ldc_w 810
      //   3980: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3983: goto -3363 -> 620
      //   3986: astore 15
      //   3988: ldc_w 812
      //   3991: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   3994: goto -166 -> 3828
      //   3997: iconst_0
      //   3998: istore_1
      //   3999: goto -121 -> 3878
      //   4002: iconst_0
      //   4003: istore_2
      //   4004: goto +336 -> 4340
      //   4007: new 85	java/lang/StringBuilder
      //   4010: dup
      //   4011: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   4014: ldc_w 814
      //   4017: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4020: aload 13
      //   4022: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4025: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4028: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   4031: return
      //   4032: astore 13
      //   4034: goto -3438 -> 596
      //   4037: new 85	java/lang/StringBuilder
      //   4040: dup
      //   4041: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   4044: ldc_w 814
      //   4047: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4050: aload 13
      //   4052: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4055: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4058: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   4061: return
      //   4062: new 85	java/lang/StringBuilder
      //   4065: dup
      //   4066: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   4069: ldc_w 816
      //   4072: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4075: lload 6
      //   4077: ldc2_w 817
      //   4080: ldiv
      //   4081: invokevirtual 679	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   4084: ldc_w 820
      //   4087: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4090: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4093: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   4096: goto -3453 -> 643
      //   4099: iconst_0
      //   4100: istore_2
      //   4101: goto -3408 -> 693
      //   4104: iconst_0
      //   4105: istore_3
      //   4106: goto -3376 -> 730
      //   4109: iconst_0
      //   4110: istore 4
      //   4112: goto -3373 -> 739
      //   4115: iload_3
      //   4116: ifeq +8 -> 4124
      //   4119: iload 4
      //   4121: ifne -3369 -> 752
      //   4124: ldc2_w 817
      //   4127: invokestatic 394	java/lang/Thread:sleep	(J)V
      //   4130: iload_1
      //   4131: iconst_1
      //   4132: iadd
      //   4133: istore_1
      //   4134: goto -3436 -> 698
      //   4137: astore 13
      //   4139: new 85	java/lang/StringBuilder
      //   4142: dup
      //   4143: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   4146: ldc_w 822
      //   4149: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4152: aload 13
      //   4154: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4157: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4160: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   4163: goto -33 -> 4130
      //   4166: iconst_0
      //   4167: istore_3
      //   4168: goto -3368 -> 800
      //   4171: iconst_0
      //   4172: istore 4
      //   4174: goto -3356 -> 818
      //   4177: ldc2_w 817
      //   4180: invokestatic 394	java/lang/Thread:sleep	(J)V
      //   4183: iload_1
      //   4184: iconst_1
      //   4185: iadd
      //   4186: istore_1
      //   4187: goto -3406 -> 781
      //   4190: astore 13
      //   4192: new 85	java/lang/StringBuilder
      //   4195: dup
      //   4196: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   4199: ldc_w 822
      //   4202: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4205: aload 13
      //   4207: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4210: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4213: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   4216: goto -33 -> 4183
      //   4219: iconst_0
      //   4220: istore_1
      //   4221: goto -3373 -> 848
      //   4224: ldc2_w 817
      //   4227: invokestatic 394	java/lang/Thread:sleep	(J)V
      //   4230: iload_2
      //   4231: iconst_1
      //   4232: iadd
      //   4233: istore_2
      //   4234: goto -3403 -> 831
      //   4237: astore 13
      //   4239: new 85	java/lang/StringBuilder
      //   4242: dup
      //   4243: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   4246: ldc_w 822
      //   4249: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4252: aload 13
      //   4254: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4257: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4260: invokestatic 248	aca:b	(Ljava/lang/String;)V
      //   4263: goto -33 -> 4230
      //   4266: ldc_w 824
      //   4269: invokestatic 39	aca:a	(Ljava/lang/String;)V
      //   4272: goto -3420 -> 852
      //   4275: astore 14
      //   4277: goto -353 -> 3924
      //   4280: astore 15
      //   4282: goto -454 -> 3828
      //   4285: astore 15
      //   4287: goto -547 -> 3740
      //   4290: astore 15
      //   4292: goto -2325 -> 1967
      //   4295: astore 15
      //   4297: goto -2408 -> 1889
      //   4300: astore 15
      //   4302: goto -2491 -> 1811
      //   4305: astore 15
      //   4307: goto -2912 -> 1395
      //   4310: astore 15
      //   4312: goto -2978 -> 1334
      //   4315: iload_2
      //   4316: istore_1
      //   4317: goto -4163 -> 154
      //   4320: astore 15
      //   4322: goto -2867 -> 1455
      //   4325: astore 15
      //   4327: goto -2780 -> 1547
      //   4330: astore 15
      //   4332: goto -2686 -> 1646
      //   4335: astore 15
      //   4337: goto -2606 -> 1731
      //   4340: iload_1
      //   4341: ifne -422 -> 3919
      //   4344: iload_2
      //   4345: ifeq -421 -> 3924
      //   4348: goto -429 -> 3919
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4351	0	this	16
      //   43	4298	1	i	int
      //   14	4331	2	j	int
      //   12	4156	3	k	int
      //   737	3436	4	m	int
      //   1018	8	5	n	int
      //   9	4067	6	l1	long
      //   18	18	8	l2	long
      //   33	5	10	l3	long
      //   696	2362	12	bool	boolean
      //   150	1	13	localObject1	Object
      //   507	39	13	localIOException1	java.io.IOException
      //   551	15	13	localException1	Exception
      //   582	10	13	localIOException2	java.io.IOException
      //   594	16	13	localException2	Exception
      //   855	53	13	localMessage	Message
      //   914	10	13	localIOException3	java.io.IOException
      //   970	998	13	localObject2	Object
      //   2122	39	13	localException3	Exception
      //   2166	1759	13	localIOException4	java.io.IOException
      //   3932	27	13	str	String
      //   3975	46	13	localException4	Exception
      //   4032	19	13	localException5	Exception
      //   4137	16	13	localInterruptedException1	InterruptedException
      //   4190	16	13	localInterruptedException2	InterruptedException
      //   4237	16	13	localInterruptedException3	InterruptedException
      //   287	1603	14	localObject3	Object
      //   2063	16	14	localJSONException1	JSONException
      //   2104	1797	14	localJSONException2	JSONException
      //   4275	1	14	localException6	Exception
      //   377	1639	15	localObject4	Object
      //   2111	1	15	localJSONException3	JSONException
      //   2171	1	15	localJSONException4	JSONException
      //   2182	509	15	localJSONException5	JSONException
      //   2734	15	15	localException7	Exception
      //   2940	71	15	localObject5	Object
      //   3038	1	15	localException8	Exception
      //   3074	391	15	localObject6	Object
      //   3508	16	15	localException9	Exception
      //   3549	16	15	localException10	Exception
      //   3645	16	15	localException11	Exception
      //   3686	16	15	localException12	Exception
      //   3729	7	15	localJSONObject	JSONObject
      //   3986	1	15	localException13	Exception
      //   4280	1	15	localJSONException6	JSONException
      //   4285	1	15	localException14	Exception
      //   4290	1	15	localException15	Exception
      //   4295	1	15	localException16	Exception
      //   4300	1	15	localException17	Exception
      //   4305	1	15	localException18	Exception
      //   4310	1	15	localException19	Exception
      //   4320	1	15	localException20	Exception
      //   4325	1	15	localException21	Exception
      //   4330	1	15	localException22	Exception
      //   4335	1	15	localException23	Exception
      //   417	2539	16	localObject7	Object
      //   3590	30	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   2888	55	17	localPackageManager	android.content.pm.PackageManager
      //   2903	13	18	localIterator	Iterator
      //   2925	681	19	localApplicationInfo	ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   154	203	507	java/io/IOException
      //   203	214	507	java/io/IOException
      //   214	357	507	java/io/IOException
      //   367	412	507	java/io/IOException
      //   424	484	507	java/io/IOException
      //   484	491	507	java/io/IOException
      //   496	504	507	java/io/IOException
      //   584	594	507	java/io/IOException
      //   916	926	507	java/io/IOException
      //   926	957	507	java/io/IOException
      //   957	968	507	java/io/IOException
      //   2065	2092	507	java/io/IOException
      //   15	35	551	java/lang/Exception
      //   357	367	582	java/io/IOException
      //   154	203	594	java/lang/Exception
      //   203	214	594	java/lang/Exception
      //   214	357	594	java/lang/Exception
      //   357	367	594	java/lang/Exception
      //   367	412	594	java/lang/Exception
      //   412	424	594	java/lang/Exception
      //   424	484	594	java/lang/Exception
      //   484	491	594	java/lang/Exception
      //   496	504	594	java/lang/Exception
      //   584	594	594	java/lang/Exception
      //   916	926	594	java/lang/Exception
      //   926	957	594	java/lang/Exception
      //   957	968	594	java/lang/Exception
      //   2065	2092	594	java/lang/Exception
      //   412	424	914	java/io/IOException
      //   957	968	2063	org/json/JSONException
      //   1007	1017	2104	org/json/JSONException
      //   1028	1054	2104	org/json/JSONException
      //   1067	1125	2104	org/json/JSONException
      //   1170	1180	2111	org/json/JSONException
      //   1184	1211	2111	org/json/JSONException
      //   1140	1167	2122	java/lang/Exception
      //   1170	1180	2122	java/lang/Exception
      //   1184	1211	2122	java/lang/Exception
      //   1220	1263	2122	java/lang/Exception
      //   1263	1285	2122	java/lang/Exception
      //   2113	2119	2122	java/lang/Exception
      //   2173	2179	2122	java/lang/Exception
      //   2184	2190	2122	java/lang/Exception
      //   2736	2753	2122	java/lang/Exception
      //   2753	2770	2122	java/lang/Exception
      //   2794	2811	2122	java/lang/Exception
      //   2838	2855	2122	java/lang/Exception
      //   3040	3057	2122	java/lang/Exception
      //   3510	3537	2122	java/lang/Exception
      //   3540	3546	2122	java/lang/Exception
      //   3551	3578	2122	java/lang/Exception
      //   3581	3587	2122	java/lang/Exception
      //   3636	3642	2122	java/lang/Exception
      //   3647	3674	2122	java/lang/Exception
      //   3677	3683	2122	java/lang/Exception
      //   3688	3715	2122	java/lang/Exception
      //   3740	3761	2122	java/lang/Exception
      //   3977	3983	2122	java/lang/Exception
      //   3988	3994	2122	java/lang/Exception
      //   972	993	2166	java/io/IOException
      //   1007	1017	2166	java/io/IOException
      //   1028	1054	2166	java/io/IOException
      //   1067	1125	2166	java/io/IOException
      //   1140	1167	2166	java/io/IOException
      //   1170	1180	2166	java/io/IOException
      //   1184	1211	2166	java/io/IOException
      //   1220	1263	2166	java/io/IOException
      //   1263	1285	2166	java/io/IOException
      //   1285	1334	2166	java/io/IOException
      //   1334	1395	2166	java/io/IOException
      //   1395	1455	2166	java/io/IOException
      //   1455	1507	2166	java/io/IOException
      //   1513	1542	2166	java/io/IOException
      //   1542	1547	2166	java/io/IOException
      //   1547	1590	2166	java/io/IOException
      //   1594	1646	2166	java/io/IOException
      //   1646	1692	2166	java/io/IOException
      //   1697	1731	2166	java/io/IOException
      //   1731	1746	2166	java/io/IOException
      //   1752	1781	2166	java/io/IOException
      //   1784	1811	2166	java/io/IOException
      //   1811	1826	2166	java/io/IOException
      //   1831	1860	2166	java/io/IOException
      //   1862	1889	2166	java/io/IOException
      //   1889	1904	2166	java/io/IOException
      //   1909	1938	2166	java/io/IOException
      //   1940	1967	2166	java/io/IOException
      //   1967	2004	2166	java/io/IOException
      //   2006	2056	2166	java/io/IOException
      //   2113	2119	2166	java/io/IOException
      //   2124	2165	2166	java/io/IOException
      //   2173	2179	2166	java/io/IOException
      //   2184	2190	2166	java/io/IOException
      //   2193	2238	2166	java/io/IOException
      //   2241	2266	2166	java/io/IOException
      //   2276	2312	2166	java/io/IOException
      //   2315	2353	2166	java/io/IOException
      //   2363	2415	2166	java/io/IOException
      //   2418	2473	2166	java/io/IOException
      //   2482	2517	2166	java/io/IOException
      //   2520	2554	2166	java/io/IOException
      //   2566	2595	2166	java/io/IOException
      //   2610	2639	2166	java/io/IOException
      //   2654	2683	2166	java/io/IOException
      //   2690	2731	2166	java/io/IOException
      //   2736	2753	2166	java/io/IOException
      //   2753	2770	2166	java/io/IOException
      //   2775	2794	2166	java/io/IOException
      //   2794	2811	2166	java/io/IOException
      //   2816	2838	2166	java/io/IOException
      //   2838	2855	2166	java/io/IOException
      //   2860	2905	2166	java/io/IOException
      //   2905	2934	2166	java/io/IOException
      //   2942	2955	2166	java/io/IOException
      //   2964	3035	2166	java/io/IOException
      //   3040	3057	2166	java/io/IOException
      //   3062	3157	2166	java/io/IOException
      //   3157	3194	2166	java/io/IOException
      //   3196	3237	2166	java/io/IOException
      //   3244	3285	2166	java/io/IOException
      //   3288	3329	2166	java/io/IOException
      //   3332	3373	2166	java/io/IOException
      //   3376	3417	2166	java/io/IOException
      //   3420	3461	2166	java/io/IOException
      //   3464	3505	2166	java/io/IOException
      //   3510	3537	2166	java/io/IOException
      //   3540	3546	2166	java/io/IOException
      //   3551	3578	2166	java/io/IOException
      //   3581	3587	2166	java/io/IOException
      //   3592	3633	2166	java/io/IOException
      //   3636	3642	2166	java/io/IOException
      //   3647	3674	2166	java/io/IOException
      //   3677	3683	2166	java/io/IOException
      //   3688	3715	2166	java/io/IOException
      //   3715	3740	2166	java/io/IOException
      //   3740	3761	2166	java/io/IOException
      //   3761	3828	2166	java/io/IOException
      //   3828	3876	2166	java/io/IOException
      //   3878	3914	2166	java/io/IOException
      //   3919	3924	2166	java/io/IOException
      //   3924	3974	2166	java/io/IOException
      //   3977	3983	2166	java/io/IOException
      //   3988	3994	2166	java/io/IOException
      //   4007	4031	2166	java/io/IOException
      //   1220	1263	2171	org/json/JSONException
      //   1263	1285	2182	org/json/JSONException
      //   1967	2004	2734	java/lang/Exception
      //   2006	2056	2734	java/lang/Exception
      //   2690	2731	2734	java/lang/Exception
      //   3244	3285	2734	java/lang/Exception
      //   3288	3329	2734	java/lang/Exception
      //   3332	3373	2734	java/lang/Exception
      //   3376	3417	2734	java/lang/Exception
      //   3420	3461	2734	java/lang/Exception
      //   3464	3505	2734	java/lang/Exception
      //   2860	2905	3038	java/lang/Exception
      //   2905	2934	3038	java/lang/Exception
      //   2942	2955	3038	java/lang/Exception
      //   2964	3035	3038	java/lang/Exception
      //   3592	3633	3038	java/lang/Exception
      //   2775	2794	3508	java/lang/Exception
      //   2816	2838	3549	java/lang/Exception
      //   2942	2955	3590	android/content/pm/PackageManager$NameNotFoundException
      //   3062	3157	3645	java/lang/Exception
      //   3157	3194	3686	java/lang/Exception
      //   3196	3237	3686	java/lang/Exception
      //   3924	3974	3975	java/lang/Exception
      //   3761	3828	3986	java/lang/Exception
      //   972	993	4032	java/lang/Exception
      //   1007	1017	4032	java/lang/Exception
      //   1028	1054	4032	java/lang/Exception
      //   1067	1125	4032	java/lang/Exception
      //   2124	2165	4032	java/lang/Exception
      //   4007	4031	4032	java/lang/Exception
      //   4124	4130	4137	java/lang/InterruptedException
      //   4177	4183	4190	java/lang/InterruptedException
      //   4224	4230	4237	java/lang/InterruptedException
      //   3828	3876	4275	java/lang/Exception
      //   3878	3914	4275	java/lang/Exception
      //   3919	3924	4275	java/lang/Exception
      //   3761	3828	4280	org/json/JSONException
      //   3715	3740	4285	java/lang/Exception
      //   1889	1904	4290	java/lang/Exception
      //   1909	1938	4290	java/lang/Exception
      //   1940	1967	4290	java/lang/Exception
      //   2654	2683	4290	java/lang/Exception
      //   1811	1826	4295	java/lang/Exception
      //   1831	1860	4295	java/lang/Exception
      //   1862	1889	4295	java/lang/Exception
      //   2610	2639	4295	java/lang/Exception
      //   1731	1746	4300	java/lang/Exception
      //   1752	1781	4300	java/lang/Exception
      //   1784	1811	4300	java/lang/Exception
      //   2566	2595	4300	java/lang/Exception
      //   1334	1395	4305	java/lang/Exception
      //   1285	1334	4310	java/lang/Exception
      //   1395	1455	4320	java/lang/Exception
      //   2193	2238	4320	java/lang/Exception
      //   2241	2266	4320	java/lang/Exception
      //   1455	1507	4325	java/lang/Exception
      //   1513	1542	4325	java/lang/Exception
      //   1542	1547	4325	java/lang/Exception
      //   2276	2312	4325	java/lang/Exception
      //   2315	2353	4325	java/lang/Exception
      //   1547	1590	4330	java/lang/Exception
      //   1594	1646	4330	java/lang/Exception
      //   2363	2415	4330	java/lang/Exception
      //   2418	2473	4330	java/lang/Exception
      //   1646	1692	4335	java/lang/Exception
      //   1697	1731	4335	java/lang/Exception
      //   2482	2517	4335	java/lang/Exception
      //   2520	2554	4335	java/lang/Exception
    }
  };
  private Handler ap = null;
  private final String aq = "location_accuracy";
  private final String ar = "location_timeout";
  private final String as = "location_staleness";
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
  
  public abv(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    a(paramContext, true, paramHashMap);
  }
  
  private String I()
  {
    String str = "" + R() + ":::";
    str = str + M() + ":::";
    str = str + L() + ":::";
    str = str + P() + ":::";
    return str + N();
  }
  
  /* Error */
  private static void J()
  {
    // Byte code:
    //   0: getstatic 319	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +261 -> 266
    //   8: getstatic 158	abv:b	Z
    //   11: ifne +255 -> 266
    //   14: getstatic 205	abv:aa	Z
    //   17: ifeq +10 -> 27
    //   20: ldc_w 321
    //   23: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   26: return
    //   27: ldc_w 328
    //   30: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   33: getstatic 197	abv:V	Z
    //   36: ifne +46 -> 82
    //   39: getstatic 332	abv:S	Ljava/util/Timer;
    //   42: ifnonnull +189 -> 231
    //   45: ldc_w 334
    //   48: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   51: new 336	java/util/Timer
    //   54: dup
    //   55: invokespecial 337	java/util/Timer:<init>	()V
    //   58: putstatic 332	abv:S	Ljava/util/Timer;
    //   61: getstatic 332	abv:S	Ljava/util/Timer;
    //   64: new 26	abv$2
    //   67: dup
    //   68: invokespecial 338	abv$2:<init>	()V
    //   71: getstatic 161	abv:k	I
    //   74: i2l
    //   75: getstatic 161	abv:k	I
    //   78: i2l
    //   79: invokevirtual 342	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   82: invokestatic 347	java/lang/System:currentTimeMillis	()J
    //   85: ldc2_w 348
    //   88: ldiv
    //   89: putstatic 188	abv:M	J
    //   92: getstatic 184	abv:K	Z
    //   95: ifeq +18 -> 113
    //   98: invokestatic 351	abv:Y	()Z
    //   101: ifeq +12 -> 113
    //   104: getstatic 353	abv:c	Landroid/content/Context;
    //   107: invokestatic 358	abx:a	(Landroid/content/Context;)Labx;
    //   110: invokevirtual 360	abx:a	()V
    //   113: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   116: ifnull +49 -> 165
    //   119: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   122: ldc_w 364
    //   125: ldc -104
    //   127: invokeinterface 370 3 0
    //   132: ldc -104
    //   134: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 378	org/json/JSONObject
    //   145: dup
    //   146: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   149: ldc_w 364
    //   152: ldc -104
    //   154: invokeinterface 370 3 0
    //   159: invokespecial 380	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 383	abv:c	(Lorg/json/JSONObject;)V
    //   165: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   168: ifnull +98 -> 266
    //   171: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   174: ldc_w 385
    //   177: ldc -104
    //   179: invokeinterface 370 3 0
    //   184: ldc_w 387
    //   187: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifeq +76 -> 266
    //   193: getstatic 207	abv:ab	Z
    //   196: ifeq +64 -> 260
    //   199: ldc_w 389
    //   202: invokestatic 391	abv:h	(Ljava/lang/String;)V
    //   205: return
    //   206: astore_1
    //   207: new 294	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 393
    //   217: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   230: return
    //   231: ldc_w 398
    //   234: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   237: goto -155 -> 82
    //   240: astore_1
    //   241: ldc_w 400
    //   244: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   247: getstatic 403	abw:b	Z
    //   250: ifeq -85 -> 165
    //   253: aload_1
    //   254: invokevirtual 406	org/json/JSONException:printStackTrace	()V
    //   257: goto -92 -> 165
    //   260: ldc_w 408
    //   263: invokestatic 330	aca:a	(Ljava/lang/String;)V
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
  
  private static void K()
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
          aca.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
          return;
        }
        aca.a("Automatic Session End");
        if (!V)
        {
          if (S != null)
          {
            aca.a("Session end, flush timer was on, canceling timer and flushing current events.");
            a();
            S.cancel();
            S = null;
          }
        }
        else
        {
          if (!ab) {
            break;
          }
          h("exit");
          return;
        }
      }
      catch (Exception localException)
      {
        aca.b("(Automatic Session End) Exception occured during session tracking.\n" + localException);
        return;
      }
      aca.a("Session end, flush timer was already off.");
    }
    aca.a("Session events disabled by server.");
  }
  
  private static String L()
  {
    return Build.BRAND;
  }
  
  private static String M()
  {
    return Build.MODEL;
  }
  
  private static String N()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  private static boolean O()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (ag != null)
    {
      bool1 = bool2;
      if (!ag.getAll().isEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private String P()
  {
    return this.o + " " + this.p;
  }
  
  private String Q()
  {
    if (this.r != null) {
      return this.r;
    }
    return "Unknown";
  }
  
  private static String R()
  {
    if ((af.contains("kochava_app_id_generated")) && (!af.getString("kochava_app_id_generated", "").equals(""))) {
      return af.getString("kochava_app_id_generated", "");
    }
    String str = UUID.randomUUID().toString().replaceAll("-", "");
    str = "KA" + str;
    af.edit().putString("kochava_app_id_generated", str).apply();
    return str;
  }
  
  private void S()
  {
    if (this.ap == null) {
      this.ap = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          boolean bool = paramAnonymousMessage.getData().getBoolean("sendonstart");
          abv.k(abv.this);
          if (!bool)
          {
            abv.l(abv.this).schedule(new TimerTask()
            {
              public void run()
              {
                aca.a("Reached 10 min mark w/o sending initial, sending now.");
                abv.a(abv.this, false);
              }
            }, 600000L);
            return;
          }
          abv.l(abv.this).schedule(new TimerTask()
          {
            public void run()
            {
              aca.a("Scheduling timer to que initial event if needed.");
              abv.a(abv.this, false);
            }
          }, 2000L);
          abv.a(abv.this, new Timer());
          abv.m(abv.this).schedule(new TimerTask()
          {
            public void run() {}
          }, 4000L);
        }
      };
    }
  }
  
  private void T()
  {
    int i2 = 0;
    af = c.getSharedPreferences("initPrefs", 0);
    if (af.getString("initBool", "").equals("")) {
      af.edit().putString("initBool", "false").apply();
    }
    String str1;
    String str2;
    if (af.getString("kochavaappdata", null) != null)
    {
      str1 = Q.a(af.getString("kochavaappdata", null));
      str2 = I();
      aca.a("Stored Data: " + str1);
      aca.a("Created Data: " + str2);
      if (str1 == null) {
        Q.b(af.getString("kochavaappdata", null), str2);
      }
    }
    for (;;)
    {
      if ((this.R) || ((!af.getString("initBool", "").equals("")) && (af.getString("initBool", "").equals("false"))))
      {
        aca.a("Initial event has not yet been qued in the database, making initial call");
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
            if ((abv.z()) && (System.currentTimeMillis() - abv.A() < abv.B()))
            {
              aca.a("Too soon since last event, flush timer waiting for next flush attempt.");
              return;
            }
            abv.a();
          }
        }, 0L, k);
      }
      this.U = new Timer();
      return;
      if (!str1.equals(str2))
      {
        Q.a(af.getString("kochavaappdata", null), str2);
      }
      else
      {
        aca.a("Set start of life to false");
        this.R = false;
        continue;
        this.R = false;
      }
    }
  }
  
  /* Error */
  private static String U()
  {
    // Byte code:
    //   0: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   3: ldc_w 385
    //   6: ldc -104
    //   8: invokeinterface 370 3 0
    //   13: ldc_w 387
    //   16: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   19: ifne +14 -> 33
    //   22: ldc_w 538
    //   25: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   28: ldc -104
    //   30: astore_3
    //   31: aload_3
    //   32: areturn
    //   33: getstatic 504	abv:Q	Labu;
    //   36: invokevirtual 540	abu:a	()Ljava/lang/String;
    //   39: astore_3
    //   40: aload_3
    //   41: ifnonnull +6 -> 47
    //   44: ldc -104
    //   46: areturn
    //   47: iconst_2
    //   48: anewarray 372	java/lang/String
    //   51: astore 4
    //   53: aload_3
    //   54: ldc_w 542
    //   57: iconst_2
    //   58: invokevirtual 546	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   61: astore_3
    //   62: aload_3
    //   63: iconst_0
    //   64: aaload
    //   65: invokestatic 552	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   68: lstore_1
    //   69: aload_3
    //   70: iconst_1
    //   71: aaload
    //   72: astore 4
    //   74: new 294	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   81: ldc_w 554
    //   84: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload 4
    //   89: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   98: iconst_0
    //   99: istore_0
    //   100: aload 4
    //   102: ldc_w 556
    //   105: invokevirtual 559	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   108: ifeq +11 -> 119
    //   111: ldc_w 561
    //   114: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   117: iconst_1
    //   118: istore_0
    //   119: getstatic 154	abv:j	Ljava/lang/String;
    //   122: ifnull +15 -> 137
    //   125: getstatic 154	abv:j	Ljava/lang/String;
    //   128: invokevirtual 564	java/lang/String:trim	()Ljava/lang/String;
    //   131: invokevirtual 565	java/lang/String:isEmpty	()Z
    //   134: ifeq +15 -> 149
    //   137: ldc_w 567
    //   140: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   143: ldc_w 569
    //   146: putstatic 154	abv:j	Ljava/lang/String;
    //   149: new 294	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   156: ldc_w 571
    //   159: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: ldc_w 573
    //   165: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: getstatic 154	abv:j	Ljava/lang/String;
    //   171: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc_w 575
    //   177: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   186: new 577	java/net/URL
    //   189: dup
    //   190: new 294	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   197: ldc_w 573
    //   200: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: getstatic 154	abv:j	Ljava/lang/String;
    //   206: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: ldc_w 575
    //   212: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: invokespecial 578	java/net/URL:<init>	(Ljava/lang/String;)V
    //   221: invokevirtual 582	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   224: checkcast 584	javax/net/ssl/HttpsURLConnection
    //   227: astore_3
    //   228: aload_3
    //   229: ldc_w 586
    //   232: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   235: ldc_w 588
    //   238: ldc -104
    //   240: invokeinterface 370 3 0
    //   245: invokevirtual 591	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   248: aload_3
    //   249: ldc_w 593
    //   252: ldc_w 595
    //   255: invokevirtual 591	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   258: aload_3
    //   259: ldc_w 597
    //   262: invokevirtual 600	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   265: aload_3
    //   266: sipush 30000
    //   269: invokevirtual 604	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   272: aload_3
    //   273: sipush 30000
    //   276: invokevirtual 607	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
    //   279: aload_3
    //   280: iconst_1
    //   281: invokevirtual 611	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
    //   284: aload_3
    //   285: iconst_1
    //   286: invokevirtual 614	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
    //   289: aload_3
    //   290: invokestatic 619	ax:a	(Ljava/net/URLConnection;)V
    //   293: aload_3
    //   294: invokevirtual 622	javax/net/ssl/HttpsURLConnection:connect	()V
    //   297: aload_3
    //   298: invokestatic 624	ax:b	(Ljava/net/URLConnection;)V
    //   301: aload_3
    //   302: invokestatic 619	ax:a	(Ljava/net/URLConnection;)V
    //   305: aload_3
    //   306: invokevirtual 628	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   309: astore 5
    //   311: aload_3
    //   312: invokestatic 624	ax:b	(Ljava/net/URLConnection;)V
    //   315: new 630	java/io/OutputStreamWriter
    //   318: dup
    //   319: aload 5
    //   321: invokespecial 633	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   324: astore 5
    //   326: aload 5
    //   328: aload 4
    //   330: invokevirtual 636	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   333: aload 5
    //   335: invokevirtual 639	java/io/OutputStreamWriter:close	()V
    //   338: ldc_w 641
    //   341: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   344: new 643	java/lang/StringBuffer
    //   347: dup
    //   348: ldc -104
    //   350: invokespecial 644	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   353: astore 4
    //   355: new 646	java/io/BufferedReader
    //   358: dup
    //   359: new 648	java/io/InputStreamReader
    //   362: dup
    //   363: aload_3
    //   364: invokestatic 651	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
    //   367: invokespecial 654	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   370: invokespecial 657	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   373: astore_3
    //   374: aload_3
    //   375: invokevirtual 660	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   378: astore 5
    //   380: aload 5
    //   382: ifnull +151 -> 533
    //   385: aload 4
    //   387: aload 5
    //   389: invokevirtual 663	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   392: pop
    //   393: goto -19 -> 374
    //   396: astore_3
    //   397: aload_3
    //   398: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   401: ldc_w 669
    //   404: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   407: ifeq +280 -> 687
    //   410: new 294	java/lang/StringBuilder
    //   413: dup
    //   414: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   417: ldc_w 672
    //   420: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: aload_3
    //   424: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   427: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   430: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   433: aload_3
    //   434: invokestatic 675	abv:b	(Ljava/lang/Exception;)V
    //   437: goto +302 -> 739
    //   440: astore 4
    //   442: aload_3
    //   443: aload 4
    //   445: invokestatic 678	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
    //   448: aload 4
    //   450: athrow
    //   451: astore_3
    //   452: aload_3
    //   453: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   456: ldc_w 669
    //   459: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   462: ifeq +251 -> 713
    //   465: new 294	java/lang/StringBuilder
    //   468: dup
    //   469: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   472: ldc_w 672
    //   475: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: aload_3
    //   479: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   482: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   488: aload_3
    //   489: invokestatic 675	abv:b	(Ljava/lang/Exception;)V
    //   492: ldc -104
    //   494: areturn
    //   495: astore 4
    //   497: aload_3
    //   498: aload 4
    //   500: invokestatic 678	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
    //   503: aload 4
    //   505: athrow
    //   506: astore_3
    //   507: new 294	java/lang/StringBuilder
    //   510: dup
    //   511: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   514: ldc_w 680
    //   517: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: aload_3
    //   521: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   524: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   527: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   530: ldc -104
    //   532: areturn
    //   533: aload 4
    //   535: invokevirtual 681	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   538: astore 4
    //   540: new 294	java/lang/StringBuilder
    //   543: dup
    //   544: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   547: ldc_w 683
    //   550: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: aload 4
    //   555: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   564: iload_0
    //   565: ifeq +112 -> 677
    //   568: aload 4
    //   570: ldc_w 685
    //   573: invokevirtual 559	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   576: ifeq +16 -> 592
    //   579: ldc_w 687
    //   582: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   585: getstatic 504	abv:Q	Labu;
    //   588: lload_1
    //   589: invokevirtual 690	abu:a	(J)V
    //   592: aload 4
    //   594: astore_3
    //   595: invokestatic 692	abv:O	()Z
    //   598: ifne -567 -> 31
    //   601: aload 4
    //   603: astore_3
    //   604: getstatic 209	abv:ac	Z
    //   607: ifeq -576 -> 31
    //   610: new 294	java/lang/StringBuilder
    //   613: dup
    //   614: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   617: ldc_w 694
    //   620: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: getstatic 172	abv:D	I
    //   626: invokevirtual 697	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   629: ldc_w 699
    //   632: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   638: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   641: getstatic 172	abv:D	I
    //   644: invokestatic 701	abv:a	(I)V
    //   647: aload 4
    //   649: areturn
    //   650: astore_3
    //   651: new 294	java/lang/StringBuilder
    //   654: dup
    //   655: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   658: ldc_w 680
    //   661: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: aload_3
    //   665: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   668: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   671: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   674: ldc -104
    //   676: areturn
    //   677: getstatic 504	abv:Q	Labu;
    //   680: lload_1
    //   681: invokevirtual 690	abu:a	(J)V
    //   684: aload 4
    //   686: areturn
    //   687: new 294	java/lang/StringBuilder
    //   690: dup
    //   691: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   694: ldc_w 680
    //   697: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: aload_3
    //   701: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   704: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   707: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   710: goto +29 -> 739
    //   713: new 294	java/lang/StringBuilder
    //   716: dup
    //   717: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   720: ldc_w 680
    //   723: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   726: aload_3
    //   727: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   730: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   733: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   736: goto -244 -> 492
    //   739: ldc -104
    //   741: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   99	466	0	i1	int
    //   68	613	1	l1	long
    //   30	345	3	localObject1	Object
    //   396	47	3	localIOException1	java.io.IOException
    //   451	47	3	localIOException2	java.io.IOException
    //   506	15	3	localException	Exception
    //   594	10	3	localObject2	Object
    //   650	77	3	localOutOfMemoryError	OutOfMemoryError
    //   51	335	4	localObject3	Object
    //   440	9	4	localIOException3	java.io.IOException
    //   495	39	4	localIOException4	java.io.IOException
    //   538	147	4	str	String
    //   309	79	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   338	374	396	java/io/IOException
    //   374	380	396	java/io/IOException
    //   385	393	396	java/io/IOException
    //   533	564	396	java/io/IOException
    //   568	592	396	java/io/IOException
    //   595	601	396	java/io/IOException
    //   604	647	396	java/io/IOException
    //   677	684	396	java/io/IOException
    //   293	301	440	java/io/IOException
    //   149	293	451	java/io/IOException
    //   301	305	451	java/io/IOException
    //   315	338	451	java/io/IOException
    //   397	437	451	java/io/IOException
    //   442	451	451	java/io/IOException
    //   497	506	451	java/io/IOException
    //   651	674	451	java/io/IOException
    //   687	710	451	java/io/IOException
    //   305	315	495	java/io/IOException
    //   149	293	506	java/lang/Exception
    //   293	301	506	java/lang/Exception
    //   301	305	506	java/lang/Exception
    //   305	315	506	java/lang/Exception
    //   315	338	506	java/lang/Exception
    //   338	374	506	java/lang/Exception
    //   374	380	506	java/lang/Exception
    //   385	393	506	java/lang/Exception
    //   397	437	506	java/lang/Exception
    //   442	451	506	java/lang/Exception
    //   497	506	506	java/lang/Exception
    //   533	564	506	java/lang/Exception
    //   568	592	506	java/lang/Exception
    //   595	601	506	java/lang/Exception
    //   604	647	506	java/lang/Exception
    //   651	674	506	java/lang/Exception
    //   677	684	506	java/lang/Exception
    //   687	710	506	java/lang/Exception
    //   338	374	650	java/lang/OutOfMemoryError
    //   374	380	650	java/lang/OutOfMemoryError
    //   385	393	650	java/lang/OutOfMemoryError
    //   533	564	650	java/lang/OutOfMemoryError
    //   568	592	650	java/lang/OutOfMemoryError
    //   595	601	650	java/lang/OutOfMemoryError
    //   604	647	650	java/lang/OutOfMemoryError
    //   677	684	650	java/lang/OutOfMemoryError
  }
  
  private String V()
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
        aca.b(((StringWriter)localObject6).toString());
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
        aca.b(((StringWriter)localObject6).toString());
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
        aca.b(((StringWriter)localObject4).toString());
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
    aca.a("user agent result: " + (String)localObject8);
    return localObject7;
  }
  
  private static String W()
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
  
  private String X()
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
          aca.a("Google Play Services check returned unknown error code (" + i1 + ").");
          aca.b("Problem getting Advertising ID " + GooglePlayServicesUtil.getErrorString(i1));
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(c);
          String str = localInfo.getId();
          G = localInfo.isLimitAdTrackingEnabled();
          return str;
        }
      }
      catch (Exception localException)
      {
        aca.b("Problem getting Advertising ID (catch): " + localException.toString());
        return "";
      }
      aca.a("Google Play Services check returned ConnectionResult.SUCCESS (" + i1 + ").");
      continue;
      aca.a("Google Play Services check returned ConnectionResult.SERVICE_MISSING (" + i1 + ").");
      continue;
      aca.a("Google Play Services check returned ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED (" + i1 + ").");
      continue;
      aca.a("Google Play Services check returned ConnectionResult.SERVICE_DISABLED (" + i1 + ").");
      continue;
      aca.a("Google Play Services check returned ConnectionResult.SERVICE_INVALID (" + i1 + ").");
    }
  }
  
  private static boolean Y()
  {
    long l3 = abx.b * 60 * 1000;
    long l2 = 0L;
    long l1 = l2;
    if (af != null)
    {
      l1 = l2;
      if (af.getLong("kochava_old_loc_timestamp", 0L) != 0L) {
        l1 = af.getLong("kochava_old_loc_timestamp", 0L);
      }
    }
    if (l1 == 0L) {}
    while (System.currentTimeMillis() - l1 >= l3) {
      return true;
    }
    return false;
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
    //   8: getstatic 233	abv:ak	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 372	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc_w 846
    //   20: aastore
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 852	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +18 -> 47
    //   32: aload_0
    //   33: astore_2
    //   34: aload_0
    //   35: astore_3
    //   36: aload_0
    //   37: invokeinterface 857 1 0
    //   42: istore_1
    //   43: iload_1
    //   44: ifne +24 -> 68
    //   47: aload_0
    //   48: ifnull +18 -> 66
    //   51: aload_0
    //   52: invokeinterface 860 1 0
    //   57: ifne +9 -> 66
    //   60: aload_0
    //   61: invokeinterface 861 1 0
    //   66: aconst_null
    //   67: areturn
    //   68: aload_0
    //   69: astore_2
    //   70: aload_0
    //   71: astore_3
    //   72: aload_0
    //   73: aload_0
    //   74: ldc_w 846
    //   77: invokeinterface 865 2 0
    //   82: invokeinterface 867 2 0
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
    //   101: invokeinterface 860 1 0
    //   106: ifne +11 -> 117
    //   109: aload_0
    //   110: invokeinterface 861 1 0
    //   115: aload_2
    //   116: astore_3
    //   117: aload_3
    //   118: areturn
    //   119: astore_0
    //   120: aload_2
    //   121: astore_3
    //   122: new 294	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 869
    //   132: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_0
    //   136: invokevirtual 762	java/lang/Exception:toString	()Ljava/lang/String;
    //   139: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   148: aload 4
    //   150: astore_3
    //   151: aload_2
    //   152: ifnull -35 -> 117
    //   155: aload 4
    //   157: astore_3
    //   158: aload_2
    //   159: invokeinterface 860 1 0
    //   164: ifne -47 -> 117
    //   167: aload_2
    //   168: invokeinterface 861 1 0
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
    //   192: invokeinterface 860 1 0
    //   197: ifne +9 -> 206
    //   200: aload_3
    //   201: invokeinterface 861 1 0
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
  
  public static void a()
  {
    if (aa)
    {
      aca.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    aca.a("flush");
    ah.submit(new abv.e(null));
  }
  
  protected static void a(int paramInt)
  {
    aj.schedule(ao, paramInt, TimeUnit.SECONDS);
  }
  
  /* Error */
  @TargetApi(14)
  private void a(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +2126 -> 2127
    //   4: aload_1
    //   5: invokevirtual 916	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: putstatic 353	abv:c	Landroid/content/Context;
    //   11: iload_2
    //   12: putstatic 182	abv:J	Z
    //   15: ldc_w 918
    //   18: new 294	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 920
    //   28: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: getstatic 156	abv:a	Ljava/lang/String;
    //   34: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 925	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   43: pop
    //   44: aload_0
    //   45: new 336	java/util/Timer
    //   48: dup
    //   49: invokespecial 337	java/util/Timer:<init>	()V
    //   52: putfield 927	abv:Y	Ljava/util/Timer;
    //   55: getstatic 353	abv:c	Landroid/content/Context;
    //   58: ldc_w 492
    //   61: iconst_0
    //   62: invokevirtual 498	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   65: putstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   68: new 506	abu
    //   71: dup
    //   72: getstatic 353	abv:c	Landroid/content/Context;
    //   75: invokespecial 928	abu:<init>	(Landroid/content/Context;)V
    //   78: putstatic 504	abv:Q	Labu;
    //   81: aload_3
    //   82: ifnull +231 -> 313
    //   85: aload_3
    //   86: ldc_w 930
    //   89: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   92: ifnull +44 -> 136
    //   95: aload_3
    //   96: ldc_w 930
    //   99: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   105: ldc_w 938
    //   108: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   111: ifeq +25 -> 136
    //   114: aload_3
    //   115: ldc_w 930
    //   118: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   121: checkcast 938	java/lang/Boolean
    //   124: invokevirtual 941	java/lang/Boolean:booleanValue	()Z
    //   127: istore_2
    //   128: iload_2
    //   129: invokestatic 943	abv:a	(Z)V
    //   132: iload_2
    //   133: invokestatic 945	abv:b	(Z)V
    //   136: aload_3
    //   137: ldc_w 947
    //   140: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   143: ifnull +35 -> 178
    //   146: aload_3
    //   147: ldc_w 947
    //   150: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   153: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   156: ldc_w 372
    //   159: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   162: ifeq +16 -> 178
    //   165: aload_3
    //   166: ldc_w 947
    //   169: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   172: checkcast 372	java/lang/String
    //   175: putstatic 156	abv:a	Ljava/lang/String;
    //   178: aload_3
    //   179: ldc_w 949
    //   182: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   185: ifnull +38 -> 223
    //   188: aload_3
    //   189: ldc_w 949
    //   192: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   195: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   198: ldc_w 938
    //   201: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   204: ifeq +19 -> 223
    //   207: aload_3
    //   208: ldc_w 949
    //   211: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   214: checkcast 938	java/lang/Boolean
    //   217: invokevirtual 941	java/lang/Boolean:booleanValue	()Z
    //   220: putstatic 158	abv:b	Z
    //   223: aload_3
    //   224: ldc_w 951
    //   227: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   230: ifnull +38 -> 268
    //   233: aload_3
    //   234: ldc_w 951
    //   237: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   240: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   243: ldc_w 938
    //   246: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   249: ifeq +19 -> 268
    //   252: aload_3
    //   253: ldc_w 951
    //   256: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   259: checkcast 938	java/lang/Boolean
    //   262: invokevirtual 941	java/lang/Boolean:booleanValue	()Z
    //   265: putstatic 180	abv:I	Z
    //   268: aload_3
    //   269: ldc_w 953
    //   272: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   275: ifnull +38 -> 313
    //   278: aload_3
    //   279: ldc_w 953
    //   282: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   285: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   288: ldc_w 938
    //   291: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   294: ifeq +19 -> 313
    //   297: aload_3
    //   298: ldc_w 953
    //   301: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   304: checkcast 938	java/lang/Boolean
    //   307: invokevirtual 941	java/lang/Boolean:booleanValue	()Z
    //   310: putstatic 197	abv:V	Z
    //   313: aload_0
    //   314: invokespecial 955	abv:S	()V
    //   317: new 294	java/lang/StringBuilder
    //   320: dup
    //   321: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   324: ldc_w 957
    //   327: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   333: ldc_w 959
    //   336: ldc -104
    //   338: invokeinterface 370 3 0
    //   343: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   352: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   355: ldc_w 959
    //   358: ldc -104
    //   360: invokeinterface 370 3 0
    //   365: ldc -104
    //   367: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   370: ifne +26 -> 396
    //   373: new 238	org/json/JSONArray
    //   376: dup
    //   377: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   380: ldc_w 959
    //   383: ldc -104
    //   385: invokeinterface 370 3 0
    //   390: invokespecial 960	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   393: putstatic 241	abv:am	Lorg/json/JSONArray;
    //   396: getstatic 319	android/os/Build$VERSION:SDK_INT	I
    //   399: bipush 14
    //   401: if_icmplt +1767 -> 2168
    //   404: getstatic 158	abv:b	Z
    //   407: ifne +1761 -> 2168
    //   410: new 294	java/lang/StringBuilder
    //   413: dup
    //   414: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   417: ldc_w 962
    //   420: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: getstatic 158	abv:b	Z
    //   426: invokevirtual 965	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   429: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   432: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   435: getstatic 353	abv:c	Landroid/content/Context;
    //   438: checkcast 967	android/app/Application
    //   441: new 56	abv$c
    //   444: dup
    //   445: aload_0
    //   446: invokespecial 968	abv$c:<init>	(Labv;)V
    //   449: invokevirtual 972	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   452: getstatic 353	abv:c	Landroid/content/Context;
    //   455: new 59	abv$d
    //   458: dup
    //   459: aload_0
    //   460: invokespecial 973	abv$d:<init>	(Labv;)V
    //   463: invokevirtual 977	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   466: iconst_1
    //   467: putstatic 979	abv$a:a	Z
    //   470: iconst_1
    //   471: putstatic 980	abv$a:b	Z
    //   474: new 14	abv$13
    //   477: dup
    //   478: aload_0
    //   479: invokespecial 981	abv$13:<init>	(Labv;)V
    //   482: invokevirtual 986	java/lang/Thread:start	()V
    //   485: getstatic 180	abv:I	Z
    //   488: ifne +14 -> 502
    //   491: new 16	abv$14
    //   494: dup
    //   495: aload_0
    //   496: invokespecial 987	abv$14:<init>	(Labv;)V
    //   499: invokevirtual 986	java/lang/Thread:start	()V
    //   502: aload_0
    //   503: getstatic 353	abv:c	Landroid/content/Context;
    //   506: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   509: getstatic 353	abv:c	Landroid/content/Context;
    //   512: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   515: iconst_0
    //   516: invokevirtual 1000	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   519: getfield 1005	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   522: putfield 454	abv:r	Ljava/lang/String;
    //   525: aload_0
    //   526: new 378	org/json/JSONObject
    //   529: dup
    //   530: invokespecial 1006	org/json/JSONObject:<init>	()V
    //   533: putfield 1008	abv:d	Lorg/json/JSONObject;
    //   536: aload_0
    //   537: new 378	org/json/JSONObject
    //   540: dup
    //   541: invokespecial 1006	org/json/JSONObject:<init>	()V
    //   544: putfield 1010	abv:e	Lorg/json/JSONObject;
    //   547: aload_0
    //   548: new 378	org/json/JSONObject
    //   551: dup
    //   552: invokespecial 1006	org/json/JSONObject:<init>	()V
    //   555: putfield 1012	abv:f	Lorg/json/JSONObject;
    //   558: aconst_null
    //   559: astore 13
    //   561: aconst_null
    //   562: astore 6
    //   564: aconst_null
    //   565: astore 9
    //   567: aconst_null
    //   568: astore 7
    //   570: ldc_w 1014
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
    //   598: ldc_w 1016
    //   601: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   604: ifnull +38 -> 642
    //   607: aload 6
    //   609: astore 5
    //   611: aload_3
    //   612: ldc_w 1016
    //   615: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   618: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   621: ldc_w 372
    //   624: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   627: ifeq +15 -> 642
    //   630: aload_3
    //   631: ldc_w 1016
    //   634: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   637: checkcast 372	java/lang/String
    //   640: astore 5
    //   642: aload 7
    //   644: astore 6
    //   646: aload_3
    //   647: ldc_w 1018
    //   650: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   653: ifnull +38 -> 691
    //   656: aload 7
    //   658: astore 6
    //   660: aload_3
    //   661: ldc_w 1018
    //   664: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   667: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   670: ldc_w 372
    //   673: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   676: ifeq +15 -> 691
    //   679: aload_3
    //   680: ldc_w 1018
    //   683: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   686: checkcast 372	java/lang/String
    //   689: astore 6
    //   691: aload_1
    //   692: astore 7
    //   694: aload_3
    //   695: ldc_w 1020
    //   698: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   701: ifnull +37 -> 738
    //   704: aload_1
    //   705: astore 7
    //   707: aload_3
    //   708: ldc_w 1020
    //   711: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   714: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   717: ldc_w 372
    //   720: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   723: ifeq +15 -> 738
    //   726: aload_3
    //   727: ldc_w 1020
    //   730: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   733: checkcast 372	java/lang/String
    //   736: astore 7
    //   738: aload 8
    //   740: astore_1
    //   741: aload_3
    //   742: ldc_w 1022
    //   745: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   748: ifnull +33 -> 781
    //   751: aload_3
    //   752: ldc_w 1022
    //   755: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   758: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   761: ldc_w 372
    //   764: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   767: ifeq +1429 -> 2196
    //   770: aload_3
    //   771: ldc_w 1022
    //   774: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   777: checkcast 372	java/lang/String
    //   780: astore_1
    //   781: aload_3
    //   782: ldc_w 1024
    //   785: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   788: ifnull +34 -> 822
    //   791: aload_3
    //   792: ldc_w 1024
    //   795: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   798: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   801: ldc_w 372
    //   804: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   807: ifeq +15 -> 822
    //   810: aload_3
    //   811: ldc_w 1024
    //   814: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   817: checkcast 372	java/lang/String
    //   820: astore 8
    //   822: aload 14
    //   824: astore 8
    //   826: aload_3
    //   827: ldc_w 1026
    //   830: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   833: ifnull +38 -> 871
    //   836: aload 14
    //   838: astore 8
    //   840: aload_3
    //   841: ldc_w 1026
    //   844: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   847: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   850: ldc_w 372
    //   853: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   856: ifeq +15 -> 871
    //   859: aload_3
    //   860: ldc_w 1026
    //   863: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   866: checkcast 372	java/lang/String
    //   869: astore 8
    //   871: aload_3
    //   872: ldc_w 1028
    //   875: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   878: ifnull +39 -> 917
    //   881: aload_3
    //   882: ldc_w 1028
    //   885: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   888: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   891: ldc_w 938
    //   894: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   897: ifeq +20 -> 917
    //   900: aload_0
    //   901: aload_3
    //   902: ldc_w 1028
    //   905: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   908: checkcast 938	java/lang/Boolean
    //   911: invokevirtual 941	java/lang/Boolean:booleanValue	()Z
    //   914: putfield 252	abv:F	Z
    //   917: aload_3
    //   918: ldc_w 1030
    //   921: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   924: ifnull +115 -> 1039
    //   927: aload_3
    //   928: ldc_w 1030
    //   931: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   934: instanceof 932
    //   937: ifeq +102 -> 1039
    //   940: aload_3
    //   941: ldc_w 1030
    //   944: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   947: checkcast 932	java/util/HashMap
    //   950: putstatic 1032	abv:h	Ljava/util/Map;
    //   953: new 378	org/json/JSONObject
    //   956: dup
    //   957: invokespecial 1006	org/json/JSONObject:<init>	()V
    //   960: putstatic 1034	abv:i	Lorg/json/JSONObject;
    //   963: getstatic 1032	abv:h	Ljava/util/Map;
    //   966: invokeinterface 1038 1 0
    //   971: invokeinterface 1044 1 0
    //   976: astore 9
    //   978: aload 9
    //   980: invokeinterface 1049 1 0
    //   985: ifeq +54 -> 1039
    //   988: aload 9
    //   990: invokeinterface 1053 1 0
    //   995: checkcast 1055	java/util/Map$Entry
    //   998: astore 10
    //   1000: getstatic 1034	abv:i	Lorg/json/JSONObject;
    //   1003: aload 10
    //   1005: invokeinterface 1058 1 0
    //   1010: checkcast 372	java/lang/String
    //   1013: aload 10
    //   1015: invokeinterface 1061 1 0
    //   1020: checkcast 372	java/lang/String
    //   1023: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1026: pop
    //   1027: aload 9
    //   1029: invokeinterface 1068 1 0
    //   1034: goto -56 -> 978
    //   1037: astore 9
    //   1039: aload_3
    //   1040: ldc_w 1070
    //   1043: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1046: ifnull +36 -> 1082
    //   1049: aload_3
    //   1050: ldc_w 1070
    //   1053: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1056: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1059: ldc_w 372
    //   1062: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1065: ifeq +17 -> 1082
    //   1068: aload_0
    //   1069: aload_3
    //   1070: ldc_w 1070
    //   1073: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1076: checkcast 372	java/lang/String
    //   1079: putfield 1072	abv:t	Ljava/lang/String;
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
    //   1102: ldc_w 1074
    //   1105: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
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
    //   1131: ldc_w 1074
    //   1134: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1137: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1140: ldc_w 1076
    //   1143: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1146: ifeq +77 -> 1223
    //   1149: aload_3
    //   1150: ldc_w 1074
    //   1153: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1156: checkcast 1076	java/lang/Integer
    //   1159: invokevirtual 1079	java/lang/Integer:intValue	()I
    //   1162: istore 4
    //   1164: iload 4
    //   1166: iconst_1
    //   1167: if_icmpge +1077 -> 2244
    //   1170: new 294	java/lang/StringBuilder
    //   1173: dup
    //   1174: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1177: ldc_w 1081
    //   1180: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: iload 4
    //   1185: invokevirtual 697	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1188: ldc_w 1083
    //   1191: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1197: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   1200: iconst_1
    //   1201: putstatic 163	abv:l	Z
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
    //   1230: invokevirtual 564	java/lang/String:trim	()Ljava/lang/String;
    //   1233: invokevirtual 1086	java/lang/String:length	()I
    //   1236: ifeq +8 -> 1244
    //   1239: aload 11
    //   1241: putstatic 154	abv:j	Ljava/lang/String;
    //   1244: aload 10
    //   1246: ifnull +18 -> 1264
    //   1249: aload 10
    //   1251: ldc_w 387
    //   1254: invokevirtual 1089	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1257: ifeq +7 -> 1264
    //   1260: iconst_1
    //   1261: putstatic 209	abv:ac	Z
    //   1264: getstatic 353	abv:c	Landroid/content/Context;
    //   1267: ldc_w 1091
    //   1270: iconst_0
    //   1271: invokevirtual 498	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1274: putstatic 437	abv:ag	Landroid/content/SharedPreferences;
    //   1277: aload 9
    //   1279: ifnull +1057 -> 2336
    //   1282: aload 9
    //   1284: invokevirtual 564	java/lang/String:trim	()Ljava/lang/String;
    //   1287: invokevirtual 1086	java/lang/String:length	()I
    //   1290: ifeq +1046 -> 2336
    //   1293: aload 9
    //   1295: putstatic 1093	abv:u	Ljava/lang/String;
    //   1298: aload_0
    //   1299: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   1302: ldc_w 1018
    //   1305: aload 9
    //   1307: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1310: pop
    //   1311: aload_0
    //   1312: getfield 1012	abv:f	Lorg/json/JSONObject;
    //   1315: ldc_w 1018
    //   1318: aload 9
    //   1320: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1323: pop
    //   1324: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1327: ldc_w 502
    //   1330: ldc -104
    //   1332: invokeinterface 370 3 0
    //   1337: ldc -104
    //   1339: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1342: ifeq +26 -> 1368
    //   1345: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1348: invokeinterface 480 1 0
    //   1353: ldc_w 502
    //   1356: aload 9
    //   1358: invokeinterface 486 3 0
    //   1363: invokeinterface 489 1 0
    //   1368: aload_0
    //   1369: aload 12
    //   1371: invokespecial 1095	abv:g	(Ljava/lang/String;)V
    //   1374: aload_0
    //   1375: getfield 1008	abv:d	Lorg/json/JSONObject;
    //   1378: ldc_w 1097
    //   1381: aload_0
    //   1382: invokespecial 1099	abv:Q	()Ljava/lang/String;
    //   1385: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1388: pop
    //   1389: aload_0
    //   1390: getfield 1008	abv:d	Lorg/json/JSONObject;
    //   1393: ldc_w 1101
    //   1396: ldc_w 1103
    //   1399: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload_0
    //   1404: getfield 1008	abv:d	Lorg/json/JSONObject;
    //   1407: ldc_w 1105
    //   1410: ldc_w 1107
    //   1413: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1416: pop
    //   1417: aload_0
    //   1418: getfield 1008	abv:d	Lorg/json/JSONObject;
    //   1421: ldc_w 1020
    //   1424: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1427: ldc_w 1020
    //   1430: ldc_w 1014
    //   1433: invokeinterface 370 3 0
    //   1438: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1441: pop
    //   1442: aload_0
    //   1443: getfield 1012	abv:f	Lorg/json/JSONObject;
    //   1446: ldc_w 1020
    //   1449: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1452: ldc_w 1020
    //   1455: ldc_w 1014
    //   1458: invokeinterface 370 3 0
    //   1463: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1466: pop
    //   1467: aload_0
    //   1468: getfield 1012	abv:f	Lorg/json/JSONObject;
    //   1471: ldc_w 1105
    //   1474: ldc_w 1107
    //   1477: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1480: pop
    //   1481: aload_0
    //   1482: getfield 1012	abv:f	Lorg/json/JSONObject;
    //   1485: ldc_w 1020
    //   1488: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1491: ldc_w 1020
    //   1494: ldc_w 1014
    //   1497: invokeinterface 370 3 0
    //   1502: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1505: pop
    //   1506: aload_0
    //   1507: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   1510: ldc_w 1109
    //   1513: new 294	java/lang/StringBuilder
    //   1516: dup
    //   1517: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1520: ldc_w 1111
    //   1523: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1526: getstatic 156	abv:a	Ljava/lang/String;
    //   1529: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1532: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1535: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1538: pop
    //   1539: aload_0
    //   1540: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   1543: ldc_w 1113
    //   1546: ldc_w 1115
    //   1549: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1552: pop
    //   1553: aload_0
    //   1554: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   1557: ldc_w 1117
    //   1560: aload_0
    //   1561: getfield 1008	abv:d	Lorg/json/JSONObject;
    //   1564: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1567: pop
    //   1568: aload_0
    //   1569: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   1572: ldc_w 1119
    //   1575: aload_0
    //   1576: getfield 1012	abv:f	Lorg/json/JSONObject;
    //   1579: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1582: pop
    //   1583: aload_0
    //   1584: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   1587: ldc_w 1121
    //   1590: ldc_w 1123
    //   1593: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1596: pop
    //   1597: invokestatic 347	java/lang/System:currentTimeMillis	()J
    //   1600: ldc2_w 348
    //   1603: ldiv
    //   1604: putstatic 188	abv:M	J
    //   1607: iconst_0
    //   1608: istore 4
    //   1610: ldc -104
    //   1612: astore_3
    //   1613: new 1125	android/content/ComponentName
    //   1616: dup
    //   1617: getstatic 353	abv:c	Landroid/content/Context;
    //   1620: ldc_w 1127
    //   1623: invokespecial 1130	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1626: astore_1
    //   1627: getstatic 353	abv:c	Landroid/content/Context;
    //   1630: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1633: aload_1
    //   1634: iconst_0
    //   1635: invokevirtual 1134	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1638: pop
    //   1639: ldc_w 1136
    //   1642: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   1645: aload_3
    //   1646: astore_1
    //   1647: getstatic 353	abv:c	Landroid/content/Context;
    //   1650: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1653: ldc_w 1138
    //   1656: getstatic 353	abv:c	Landroid/content/Context;
    //   1659: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1662: invokevirtual 1141	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1665: ifge +27 -> 1692
    //   1668: iconst_1
    //   1669: istore 4
    //   1671: new 294	java/lang/StringBuilder
    //   1674: dup
    //   1675: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1678: aload_3
    //   1679: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1682: ldc_w 1143
    //   1685: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1688: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1691: astore_1
    //   1692: aload_1
    //   1693: astore_3
    //   1694: getstatic 353	abv:c	Landroid/content/Context;
    //   1697: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1700: ldc_w 1145
    //   1703: getstatic 353	abv:c	Landroid/content/Context;
    //   1706: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1709: invokevirtual 1141	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1712: ifge +27 -> 1739
    //   1715: iconst_1
    //   1716: istore 4
    //   1718: new 294	java/lang/StringBuilder
    //   1721: dup
    //   1722: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1725: aload_1
    //   1726: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1729: ldc_w 1147
    //   1732: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1735: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1738: astore_3
    //   1739: aload_3
    //   1740: astore_1
    //   1741: getstatic 353	abv:c	Landroid/content/Context;
    //   1744: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1747: ldc_w 1149
    //   1750: getstatic 353	abv:c	Landroid/content/Context;
    //   1753: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1756: invokevirtual 1141	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1759: ifge +27 -> 1786
    //   1762: iconst_1
    //   1763: istore 4
    //   1765: new 294	java/lang/StringBuilder
    //   1768: dup
    //   1769: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1772: aload_3
    //   1773: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1776: ldc_w 1151
    //   1779: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1782: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1785: astore_1
    //   1786: iload 4
    //   1788: ifeq +13 -> 1801
    //   1791: ldc_w 1153
    //   1794: invokestatic 1155	aca:c	(Ljava/lang/String;)V
    //   1797: aload_1
    //   1798: invokestatic 1155	aca:c	(Ljava/lang/String;)V
    //   1801: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1804: ldc_w 588
    //   1807: ldc -104
    //   1809: invokeinterface 370 3 0
    //   1814: ldc -104
    //   1816: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1819: ifeq +28 -> 1847
    //   1822: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   1825: invokeinterface 480 1 0
    //   1830: ldc_w 588
    //   1833: aload_0
    //   1834: invokespecial 1157	abv:V	()Ljava/lang/String;
    //   1837: invokeinterface 486 3 0
    //   1842: invokeinterface 489 1 0
    //   1847: aload_0
    //   1848: ldc_w 1159
    //   1851: putfield 448	abv:o	Ljava/lang/String;
    //   1854: aload_0
    //   1855: ldc_w 1161
    //   1858: putfield 452	abv:p	Ljava/lang/String;
    //   1861: aload_0
    //   1862: ldc -104
    //   1864: putfield 1163	abv:q	Ljava/lang/String;
    //   1867: getstatic 353	abv:c	Landroid/content/Context;
    //   1870: invokevirtual 916	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1873: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1876: astore_3
    //   1877: aload_3
    //   1878: getstatic 353	abv:c	Landroid/content/Context;
    //   1881: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1884: iconst_0
    //   1885: invokevirtual 1167	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1888: astore_1
    //   1889: aload_1
    //   1890: ifnull +730 -> 2620
    //   1893: aload_3
    //   1894: aload_1
    //   1895: invokevirtual 1171	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1898: astore_1
    //   1899: aload_0
    //   1900: aload_1
    //   1901: checkcast 372	java/lang/String
    //   1904: checkcast 372	java/lang/String
    //   1907: putfield 448	abv:o	Ljava/lang/String;
    //   1910: new 294	java/lang/StringBuilder
    //   1913: dup
    //   1914: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1917: ldc_w 1173
    //   1920: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1923: aload_0
    //   1924: getfield 448	abv:o	Ljava/lang/String;
    //   1927: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1930: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1933: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   1936: aload_0
    //   1937: new 294	java/lang/StringBuilder
    //   1940: dup
    //   1941: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1944: getstatic 353	abv:c	Landroid/content/Context;
    //   1947: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1950: getstatic 353	abv:c	Landroid/content/Context;
    //   1953: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1956: iconst_0
    //   1957: invokevirtual 1000	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1960: getfield 1176	android/content/pm/PackageInfo:versionCode	I
    //   1963: invokevirtual 697	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1966: ldc -104
    //   1968: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1971: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1974: putfield 452	abv:p	Ljava/lang/String;
    //   1977: new 294	java/lang/StringBuilder
    //   1980: dup
    //   1981: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   1984: ldc_w 1178
    //   1987: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1990: aload_0
    //   1991: getfield 452	abv:p	Ljava/lang/String;
    //   1994: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1997: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2000: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   2003: aload_0
    //   2004: getstatic 353	abv:c	Landroid/content/Context;
    //   2007: invokevirtual 991	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2010: getstatic 353	abv:c	Landroid/content/Context;
    //   2013: invokevirtual 994	android/content/Context:getPackageName	()Ljava/lang/String;
    //   2016: iconst_0
    //   2017: invokevirtual 1000	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2020: getfield 1181	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2023: putfield 1163	abv:q	Ljava/lang/String;
    //   2026: new 294	java/lang/StringBuilder
    //   2029: dup
    //   2030: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2033: ldc_w 1183
    //   2036: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2039: aload_0
    //   2040: getfield 1163	abv:q	Ljava/lang/String;
    //   2043: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2046: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2049: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   2052: new 18	abv$15
    //   2055: dup
    //   2056: aload_0
    //   2057: invokespecial 1184	abv$15:<init>	(Labv;)V
    //   2060: invokevirtual 986	java/lang/Thread:start	()V
    //   2063: aload_0
    //   2064: invokestatic 301	abv:R	()Ljava/lang/String;
    //   2067: putfield 1186	abv:s	Ljava/lang/String;
    //   2070: aload_0
    //   2071: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   2074: ldc_w 1188
    //   2077: invokestatic 301	abv:R	()Ljava/lang/String;
    //   2080: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2083: pop
    //   2084: getstatic 223	abv:aj	Ljava/util/concurrent/ScheduledExecutorService;
    //   2087: aload_0
    //   2088: getfield 259	abv:an	Ljava/lang/Runnable;
    //   2091: ldc2_w 1189
    //   2094: getstatic 893	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2097: invokeinterface 898 5 0
    //   2102: pop
    //   2103: aload_0
    //   2104: ldc_w 1123
    //   2107: invokevirtual 1191	abv:a	(Ljava/lang/String;)V
    //   2110: return
    //   2111: astore_1
    //   2112: ldc_w 1193
    //   2115: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2118: aload_1
    //   2119: invokevirtual 1194	java/lang/Exception:printStackTrace	()V
    //   2122: iconst_1
    //   2123: putstatic 205	abv:aa	Z
    //   2126: return
    //   2127: ldc_w 1196
    //   2130: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2133: iconst_1
    //   2134: putstatic 205	abv:aa	Z
    //   2137: return
    //   2138: astore_1
    //   2139: new 294	java/lang/StringBuilder
    //   2142: dup
    //   2143: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2146: ldc_w 1198
    //   2149: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2152: aload_1
    //   2153: invokevirtual 762	java/lang/Exception:toString	()Ljava/lang/String;
    //   2156: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2159: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2162: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   2165: goto -1769 -> 396
    //   2168: new 294	java/lang/StringBuilder
    //   2171: dup
    //   2172: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2175: ldc_w 1200
    //   2178: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2181: getstatic 158	abv:b	Z
    //   2184: invokevirtual 965	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2187: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2190: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   2193: goto -1719 -> 474
    //   2196: aload 8
    //   2198: astore_1
    //   2199: aload_3
    //   2200: ldc_w 1022
    //   2203: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2206: invokevirtual 667	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2209: ldc_w 938
    //   2212: invokevirtual 670	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2215: ifeq -1434 -> 781
    //   2218: aload 8
    //   2220: astore_1
    //   2221: aload_3
    //   2222: ldc_w 1022
    //   2225: invokevirtual 936	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2228: checkcast 938	java/lang/Boolean
    //   2231: invokevirtual 941	java/lang/Boolean:booleanValue	()Z
    //   2234: ifeq -1453 -> 781
    //   2237: ldc_w 387
    //   2240: astore_1
    //   2241: goto -1460 -> 781
    //   2244: iload 4
    //   2246: sipush 360
    //   2249: if_icmple +42 -> 2291
    //   2252: new 294	java/lang/StringBuilder
    //   2255: dup
    //   2256: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2259: ldc_w 1081
    //   2262: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2265: iload 4
    //   2267: invokevirtual 697	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2270: ldc_w 1202
    //   2273: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2276: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2279: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   2282: ldc_w 1203
    //   2285: putstatic 161	abv:k	I
    //   2288: goto -1088 -> 1200
    //   2291: iload 4
    //   2293: bipush 60
    //   2295: imul
    //   2296: sipush 1000
    //   2299: imul
    //   2300: putstatic 161	abv:k	I
    //   2303: new 294	java/lang/StringBuilder
    //   2306: dup
    //   2307: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2310: ldc_w 1205
    //   2313: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2316: iload 4
    //   2318: invokevirtual 697	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2321: ldc_w 1207
    //   2324: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2327: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2330: invokestatic 330	aca:a	(Ljava/lang/String;)V
    //   2333: goto -1133 -> 1200
    //   2336: aload 13
    //   2338: ifnull +178 -> 2516
    //   2341: aload 13
    //   2343: invokevirtual 564	java/lang/String:trim	()Ljava/lang/String;
    //   2346: invokevirtual 1086	java/lang/String:length	()I
    //   2349: ifeq +167 -> 2516
    //   2352: aload_0
    //   2353: getfield 1008	abv:d	Lorg/json/JSONObject;
    //   2356: ldc_w 1016
    //   2359: aload 13
    //   2361: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2364: pop
    //   2365: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   2368: ldc_w 502
    //   2371: ldc -104
    //   2373: invokeinterface 370 3 0
    //   2378: ldc -104
    //   2380: invokevirtual 376	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2383: ifeq +26 -> 2409
    //   2386: getstatic 362	abv:af	Landroid/content/SharedPreferences;
    //   2389: invokeinterface 480 1 0
    //   2394: ldc_w 502
    //   2397: aload 13
    //   2399: invokeinterface 486 3 0
    //   2404: invokeinterface 489 1 0
    //   2409: aload 9
    //   2411: ifnull +78 -> 2489
    //   2414: aload 9
    //   2416: invokevirtual 564	java/lang/String:trim	()Ljava/lang/String;
    //   2419: invokevirtual 1086	java/lang/String:length	()I
    //   2422: ifeq +67 -> 2489
    //   2425: aload 9
    //   2427: putstatic 1093	abv:u	Ljava/lang/String;
    //   2430: aload_0
    //   2431: getfield 1010	abv:e	Lorg/json/JSONObject;
    //   2434: ldc_w 1018
    //   2437: aload 9
    //   2439: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2442: pop
    //   2443: aload_0
    //   2444: getfield 1012	abv:f	Lorg/json/JSONObject;
    //   2447: ldc_w 1018
    //   2450: aload 9
    //   2452: invokevirtual 1065	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2455: pop
    //   2456: goto -1088 -> 1368
    //   2459: astore_1
    //   2460: new 294	java/lang/StringBuilder
    //   2463: dup
    //   2464: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2467: ldc_w 1209
    //   2470: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2473: aload_1
    //   2474: invokevirtual 1210	org/json/JSONException:toString	()Ljava/lang/String;
    //   2477: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2480: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2483: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2486: goto -889 -> 1597
    //   2489: new 294	java/lang/StringBuilder
    //   2492: dup
    //   2493: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2496: ldc_w 1212
    //   2499: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2502: aload 13
    //   2504: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2507: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2510: putstatic 1093	abv:u	Ljava/lang/String;
    //   2513: goto -1145 -> 1368
    //   2516: ldc_w 1214
    //   2519: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2522: iconst_1
    //   2523: putstatic 205	abv:aa	Z
    //   2526: return
    //   2527: astore_1
    //   2528: iconst_1
    //   2529: istore 4
    //   2531: new 294	java/lang/StringBuilder
    //   2534: dup
    //   2535: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2538: ldc -104
    //   2540: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2543: ldc_w 1216
    //   2546: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2549: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2552: astore_3
    //   2553: goto -908 -> 1645
    //   2556: astore 5
    //   2558: aconst_null
    //   2559: astore_1
    //   2560: new 294	java/lang/StringBuilder
    //   2563: dup
    //   2564: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2567: ldc_w 1218
    //   2570: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2573: aload 5
    //   2575: invokevirtual 1219	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2578: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2581: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2584: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2587: goto -698 -> 1889
    //   2590: astore_1
    //   2591: new 294	java/lang/StringBuilder
    //   2594: dup
    //   2595: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2598: ldc_w 1218
    //   2601: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2604: aload_1
    //   2605: invokevirtual 762	java/lang/Exception:toString	()Ljava/lang/String;
    //   2608: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2611: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2614: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2617: goto -681 -> 1936
    //   2620: ldc_w 1221
    //   2623: astore_1
    //   2624: goto -725 -> 1899
    //   2627: astore_1
    //   2628: new 294	java/lang/StringBuilder
    //   2631: dup
    //   2632: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2635: ldc_w 1223
    //   2638: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2641: aload_1
    //   2642: invokevirtual 762	java/lang/Exception:toString	()Ljava/lang/String;
    //   2645: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2648: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2651: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2654: goto -651 -> 2003
    //   2657: astore_1
    //   2658: new 294	java/lang/StringBuilder
    //   2661: dup
    //   2662: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   2665: ldc_w 1225
    //   2668: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2671: aload_1
    //   2672: invokevirtual 762	java/lang/Exception:toString	()Ljava/lang/String;
    //   2675: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2678: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2681: invokestatic 326	aca:b	(Ljava/lang/String;)V
    //   2684: goto -632 -> 2052
    //   2687: astore_1
    //   2688: getstatic 403	abw:b	Z
    //   2691: ifeq -607 -> 2084
    //   2694: aload_1
    //   2695: invokevirtual 406	org/json/JSONException:printStackTrace	()V
    //   2698: goto -614 -> 2084
    //   2701: astore_1
    //   2702: goto -2177 -> 525
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2705	0	this	abv
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
        if (i1 < am.length()) {}
        try
        {
          boolean bool = am.get(i1).equals(paramMap1.get("event_name"));
          if (bool)
          {
            i2 = 1;
            if (i2 == 0) {
              break;
            }
            aca.a("fireEvent - Events under the key \"" + str + "\" are blocked!");
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
    abw.a = paramBoolean;
    aca.a("enableDebug to " + paramBoolean);
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
          af = c.getSharedPreferences("initPrefs", 0);
          if (af.getString("mylat", "").equals("")) {
            break;
          }
          paramJSONObject.put("geo_lat", af.getString("mylat", ""));
          paramJSONObject.put("geo_lon", af.getString("mylong", ""));
          return paramJSONObject;
          paramJSONObject.put("updelta", "0");
        }
        return paramJSONObject;
      }
      catch (Exception localException)
      {
        aca.b("Error adding time properties to a JSON object " + localException);
      }
    }
  }
  
  private static void b(Exception paramException)
  {
    new Thread()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: iconst_1
        //   1: invokestatic 27	abv:g	(Z)Z
        //   4: pop
        //   5: aload_0
        //   6: getfield 14	abv$10:a	Ljava/lang/Exception;
        //   9: invokevirtual 31	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   12: astore_2
        //   13: new 33	org/json/JSONObject
        //   16: dup
        //   17: invokespecial 34	org/json/JSONObject:<init>	()V
        //   20: astore_1
        //   21: aload_1
        //   22: ldc 36
        //   24: aload_2
        //   25: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   28: pop
        //   29: aload_1
        //   30: ldc 42
        //   32: invokestatic 45	abv:u	()Ljava/lang/String;
        //   35: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   38: pop
        //   39: aload_1
        //   40: ldc 47
        //   42: new 49	java/lang/StringBuilder
        //   45: dup
        //   46: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   49: invokestatic 53	abv:F	()Ljava/lang/String;
        //   52: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   55: ldc 59
        //   57: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   60: invokestatic 62	abv:G	()Ljava/lang/String;
        //   63: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   66: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   69: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   72: pop
        //   73: new 33	org/json/JSONObject
        //   76: dup
        //   77: invokespecial 34	org/json/JSONObject:<init>	()V
        //   80: astore_2
        //   81: aload_2
        //   82: ldc 67
        //   84: invokestatic 70	abv:p	()Ljava/lang/String;
        //   87: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   90: pop
        //   91: aload_2
        //   92: ldc 72
        //   94: ldc 74
        //   96: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   99: pop
        //   100: aload_2
        //   101: ldc 76
        //   103: aload_1
        //   104: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   107: pop
        //   108: aload_2
        //   109: ldc 78
        //   111: invokestatic 81	abv:o	()Ljava/lang/String;
        //   114: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   117: pop
        //   118: aload_2
        //   119: ldc 83
        //   121: new 49	java/lang/StringBuilder
        //   124: dup
        //   125: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   128: ldc 85
        //   130: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: getstatic 88	abv:a	Ljava/lang/String;
        //   136: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   142: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   145: pop
        //   146: aload_2
        //   147: ldc 90
        //   149: ldc 92
        //   151: invokevirtual 40	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   154: pop
        //   155: new 49	java/lang/StringBuilder
        //   158: dup
        //   159: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   162: ldc 94
        //   164: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: ldc 96
        //   169: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: invokestatic 99	abv:c	()Ljava/lang/String;
        //   175: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: ldc 101
        //   180: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   186: invokestatic 106	aca:a	(Ljava/lang/String;)V
        //   189: new 108	java/net/URL
        //   192: dup
        //   193: new 49	java/lang/StringBuilder
        //   196: dup
        //   197: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   200: ldc 96
        //   202: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: invokestatic 99	abv:c	()Ljava/lang/String;
        //   208: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: ldc 101
        //   213: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   219: invokespecial 110	java/net/URL:<init>	(Ljava/lang/String;)V
        //   222: astore_1
        //   223: aload_2
        //   224: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
        //   227: astore_2
        //   228: new 49	java/lang/StringBuilder
        //   231: dup
        //   232: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   235: ldc 113
        //   237: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: aload_2
        //   241: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   247: invokestatic 106	aca:a	(Ljava/lang/String;)V
        //   250: aload_1
        //   251: invokevirtual 117	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   254: checkcast 119	java/net/HttpURLConnection
        //   257: astore_1
        //   258: aload_1
        //   259: ldc 121
        //   261: invokestatic 124	abv:b	()Landroid/content/SharedPreferences;
        //   264: ldc 126
        //   266: ldc -128
        //   268: invokeinterface 134 3 0
        //   273: invokevirtual 138	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   276: aload_1
        //   277: ldc -116
        //   279: ldc -114
        //   281: invokevirtual 138	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   284: aload_1
        //   285: ldc -112
        //   287: invokevirtual 147	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   290: aload_1
        //   291: sipush 30000
        //   294: invokevirtual 151	java/net/HttpURLConnection:setConnectTimeout	(I)V
        //   297: aload_1
        //   298: sipush 30000
        //   301: invokevirtual 154	java/net/HttpURLConnection:setReadTimeout	(I)V
        //   304: aload_1
        //   305: iconst_1
        //   306: invokevirtual 158	java/net/HttpURLConnection:setDoInput	(Z)V
        //   309: aload_1
        //   310: iconst_1
        //   311: invokevirtual 161	java/net/HttpURLConnection:setDoOutput	(Z)V
        //   314: aload_1
        //   315: invokestatic 166	ax:a	(Ljava/net/URLConnection;)V
        //   318: aload_1
        //   319: invokevirtual 169	java/net/HttpURLConnection:connect	()V
        //   322: aload_1
        //   323: invokestatic 171	ax:b	(Ljava/net/URLConnection;)V
        //   326: aload_1
        //   327: invokestatic 166	ax:a	(Ljava/net/URLConnection;)V
        //   330: aload_1
        //   331: invokevirtual 175	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   334: astore_3
        //   335: aload_1
        //   336: invokestatic 171	ax:b	(Ljava/net/URLConnection;)V
        //   339: new 177	java/io/OutputStreamWriter
        //   342: dup
        //   343: aload_3
        //   344: invokespecial 180	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   347: astore_3
        //   348: aload_3
        //   349: aload_2
        //   350: invokevirtual 183	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   353: aload_3
        //   354: invokevirtual 186	java/io/OutputStreamWriter:close	()V
        //   357: ldc -68
        //   359: invokestatic 106	aca:a	(Ljava/lang/String;)V
        //   362: new 190	java/lang/StringBuffer
        //   365: dup
        //   366: ldc -128
        //   368: invokespecial 191	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   371: astore_2
        //   372: new 193	java/io/BufferedReader
        //   375: dup
        //   376: new 195	java/io/InputStreamReader
        //   379: dup
        //   380: aload_1
        //   381: invokestatic 199	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
        //   384: invokespecial 202	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   387: invokespecial 205	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   390: astore_1
        //   391: aload_1
        //   392: invokevirtual 208	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   395: astore_3
        //   396: aload_3
        //   397: ifnull +52 -> 449
        //   400: aload_2
        //   401: aload_3
        //   402: invokevirtual 211	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   405: pop
        //   406: goto -15 -> 391
        //   409: astore_1
        //   410: new 49	java/lang/StringBuilder
        //   413: dup
        //   414: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   417: ldc -43
        //   419: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   422: aload_1
        //   423: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   426: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   429: invokestatic 218	aca:b	(Ljava/lang/String;)V
        //   432: return
        //   433: astore_2
        //   434: aload_1
        //   435: aload_2
        //   436: invokestatic 221	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   439: aload_2
        //   440: athrow
        //   441: astore_2
        //   442: aload_1
        //   443: aload_2
        //   444: invokestatic 221	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   447: aload_2
        //   448: athrow
        //   449: aload_2
        //   450: invokevirtual 222	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   453: astore_1
        //   454: new 49	java/lang/StringBuilder
        //   457: dup
        //   458: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   461: ldc -32
        //   463: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   466: aload_1
        //   467: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   470: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   473: invokestatic 106	aca:a	(Ljava/lang/String;)V
        //   476: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	477	0	this	10
        //   20	372	1	localObject1	Object
        //   409	34	1	localException	Exception
        //   453	14	1	str	String
        //   12	389	2	localObject2	Object
        //   433	7	2	localIOException1	java.io.IOException
        //   441	9	2	localIOException2	java.io.IOException
        //   334	68	3	localObject3	Object
        // Exception table:
        //   from	to	target	type
        //   0	318	409	java/lang/Exception
        //   318	326	409	java/lang/Exception
        //   326	330	409	java/lang/Exception
        //   330	339	409	java/lang/Exception
        //   339	391	409	java/lang/Exception
        //   391	396	409	java/lang/Exception
        //   400	406	409	java/lang/Exception
        //   434	441	409	java/lang/Exception
        //   442	449	409	java/lang/Exception
        //   449	476	409	java/lang/Exception
        //   318	326	433	java/io/IOException
        //   330	339	441	java/io/IOException
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
          if (abv.y()) {
            abv.a();
          }
          abv.h(false);
        }
      }, 60000L);
    }
    aca.a("FIRE EVENT*** action:" + paramString);
    aca.a("FIRE EVENT*** properties:" + paramMap1);
    JSONObject localJSONObject1 = new JSONObject();
    label1050:
    label1087:
    int i1;
    do
    {
      JSONObject localJSONObject2;
      for (;;)
      {
        try
        {
          localJSONObject1.put("kochava_app_id", u);
          localJSONObject1.put("kochava_device_id", R());
          localJSONObject1.put("action", paramString);
          localJSONObject1.put("dev_id_strategy", w);
          localJSONObject1.put("last_post_time", 0);
          af = c.getSharedPreferences("initPrefs", 0);
          localJSONObject1.put("currency", af.getString("currency", "USD"));
          localJSONObject2 = b(new JSONObject());
          if (paramString.equals("initial"))
          {
            aca.a("Event is initial, or initial did not get que'd on first load");
            try
            {
              if (((Boolean)al.get("affinity_group")).booleanValue())
              {
                paramString = new JSONArray();
                localObject = P.iterator();
                if (((Iterator)localObject).hasNext())
                {
                  abv.b localB = (abv.b)((Iterator)localObject).next();
                  JSONObject localJSONObject3 = new JSONObject();
                  localJSONObject3.put("app_name", localB.a);
                  localJSONObject3.put("mtime", localB.b);
                  localJSONObject3.put("utime", localB.c);
                  paramString.put(localJSONObject3);
                  continue;
                }
              }
              if (O == null) {
                break label1087;
              }
            }
            catch (JSONException paramString)
            {
              if (abw.b) {
                paramString.printStackTrace();
              }
              aca.b("event " + paramString);
              return;
              localJSONObject2.put("affinity_group", paramString);
              localJSONObject1.put("sdk_version", "Android20160615-NOEMAIL" + a);
              if (((Boolean)al.get("volume")).booleanValue()) {
                localJSONObject2.put("volume", W());
              }
              if (((Boolean)al.get("bssid")).booleanValue()) {
                localJSONObject2.put("bssid", B);
              }
              if (((Boolean)al.get("carrier_name")).booleanValue()) {
                localJSONObject2.put("carrier_name", C);
              }
              if (((Boolean)al.get("adid")).booleanValue()) {
                localJSONObject2.put("adid", A);
              }
              localJSONObject2.put("device", M() + "-" + L());
              if (((Boolean)al.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_h", this.y);
              }
              if (((Boolean)al.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_w", this.z);
              }
              localJSONObject2.put("package_name", Q());
              localJSONObject2.put("app_version", P());
              if (!this.q.equals("")) {
                localJSONObject2.put("app_short_string", this.q);
              }
              if (((Boolean)al.get("android_id")).booleanValue()) {
                localJSONObject2.put("android_id", this.v);
              }
              localJSONObject2.put("os_version", N());
              localJSONObject2.put("app_limit_tracking", this.F);
              localJSONObject2.put("device_limit_tracking", G);
              paramString = new JSONObject();
              if (paramString.length() > 0) {
                localJSONObject2.put("ids", paramString);
              }
              if (i != null) {
                localJSONObject2.put("identity_link", i);
              }
              if ((this.t != null) && (!this.t.isEmpty())) {
                localJSONObject2.put("clickData", this.t);
              }
              if (((Boolean)al.get("fb_attribution_id")).booleanValue())
              {
                this.x = a(c.getContentResolver());
                if (this.x != null) {
                  break label1050;
                }
                localJSONObject2.put("fb_attribution_id", "");
              }
              paramString = (WindowManager)c.getSystemService("window");
              localObject = new DisplayMetrics();
              paramString.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
              this.ae = localJSONObject2;
              this.ad = localJSONObject1;
              aca.a("Initial Event, saving until next event. ");
              return;
            }
            catch (Exception paramString)
            {
              aca.b("Error during fireEvent - Please review stack trace");
              if (abw.b) {
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
          if (abw.b) {
            paramString.printStackTrace();
          }
          aca.b("event " + paramString);
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
      aca.a("fireEvent with properties: " + localJSONObject1);
      j(true);
      i1 = Q.a(localJSONObject1, false, false);
      Z = System.currentTimeMillis();
    } while (i1 < 50);
    a();
  }
  
  private static void b(JSONArray paramJSONArray)
  {
    new Thread()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 25	org/json/JSONObject
        //   3: dup
        //   4: invokespecial 26	org/json/JSONObject:<init>	()V
        //   7: astore_3
        //   8: aload_3
        //   9: ldc 28
        //   11: ldc 30
        //   13: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   16: pop
        //   17: aload_3
        //   18: ldc 36
        //   20: invokestatic 40	abv:p	()Ljava/lang/String;
        //   23: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   26: pop
        //   27: aload_3
        //   28: ldc 42
        //   30: invokestatic 45	abv:o	()Ljava/lang/String;
        //   33: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   36: pop
        //   37: aload_3
        //   38: ldc 47
        //   40: new 49	java/lang/StringBuilder
        //   43: dup
        //   44: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   47: ldc 52
        //   49: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   52: getstatic 59	abv:a	Ljava/lang/String;
        //   55: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   58: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   61: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   64: pop
        //   65: aload_3
        //   66: ldc 64
        //   68: ldc 66
        //   70: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   73: pop
        //   74: new 25	org/json/JSONObject
        //   77: dup
        //   78: invokespecial 26	org/json/JSONObject:<init>	()V
        //   81: astore_1
        //   82: aload_1
        //   83: ldc 68
        //   85: aload_0
        //   86: getfield 14	abv$12:a	Lorg/json/JSONArray;
        //   89: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   92: pop
        //   93: aload_3
        //   94: ldc 70
        //   96: aload_1
        //   97: invokevirtual 34	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   100: pop
        //   101: invokestatic 73	abv:c	()Ljava/lang/String;
        //   104: ifnull +15 -> 119
        //   107: invokestatic 73	abv:c	()Ljava/lang/String;
        //   110: invokevirtual 78	java/lang/String:trim	()Ljava/lang/String;
        //   113: invokevirtual 82	java/lang/String:isEmpty	()Z
        //   116: ifeq +9 -> 125
        //   119: ldc 84
        //   121: invokestatic 87	abv:c	(Ljava/lang/String;)Ljava/lang/String;
        //   124: pop
        //   125: new 49	java/lang/StringBuilder
        //   128: dup
        //   129: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   132: ldc 89
        //   134: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: ldc 91
        //   139: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: invokestatic 73	abv:c	()Ljava/lang/String;
        //   145: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: ldc 93
        //   150: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   156: invokestatic 98	aca:a	(Ljava/lang/String;)V
        //   159: new 100	java/net/URL
        //   162: dup
        //   163: new 49	java/lang/StringBuilder
        //   166: dup
        //   167: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   170: ldc 91
        //   172: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: invokestatic 73	abv:c	()Ljava/lang/String;
        //   178: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: ldc 93
        //   183: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   189: invokespecial 102	java/net/URL:<init>	(Ljava/lang/String;)V
        //   192: invokevirtual 106	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   195: checkcast 108	javax/net/ssl/HttpsURLConnection
        //   198: astore_1
        //   199: aload_1
        //   200: ldc 110
        //   202: invokestatic 113	abv:b	()Landroid/content/SharedPreferences;
        //   205: ldc 115
        //   207: ldc 117
        //   209: invokeinterface 123 3 0
        //   214: invokevirtual 127	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   217: aload_1
        //   218: ldc -127
        //   220: ldc -125
        //   222: invokevirtual 127	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   225: aload_1
        //   226: ldc -123
        //   228: invokevirtual 136	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   231: aload_1
        //   232: sipush 30000
        //   235: invokevirtual 140	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   238: aload_1
        //   239: sipush 30000
        //   242: invokevirtual 143	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   245: aload_1
        //   246: iconst_1
        //   247: invokevirtual 147	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   250: aload_1
        //   251: iconst_1
        //   252: invokevirtual 150	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   255: aload_1
        //   256: invokestatic 155	ax:a	(Ljava/net/URLConnection;)V
        //   259: aload_1
        //   260: invokevirtual 158	javax/net/ssl/HttpsURLConnection:connect	()V
        //   263: aload_1
        //   264: invokestatic 160	ax:b	(Ljava/net/URLConnection;)V
        //   267: aload_3
        //   268: invokevirtual 161	org/json/JSONObject:toString	()Ljava/lang/String;
        //   271: astore_2
        //   272: new 49	java/lang/StringBuilder
        //   275: dup
        //   276: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   279: ldc -93
        //   281: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: aload_3
        //   285: invokevirtual 161	org/json/JSONObject:toString	()Ljava/lang/String;
        //   288: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   294: invokestatic 98	aca:a	(Ljava/lang/String;)V
        //   297: aload_1
        //   298: invokestatic 155	ax:a	(Ljava/net/URLConnection;)V
        //   301: aload_1
        //   302: invokevirtual 167	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   305: astore_3
        //   306: aload_1
        //   307: invokestatic 160	ax:b	(Ljava/net/URLConnection;)V
        //   310: new 169	java/io/OutputStreamWriter
        //   313: dup
        //   314: aload_3
        //   315: invokespecial 172	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   318: astore_3
        //   319: aload_3
        //   320: aload_2
        //   321: invokevirtual 175	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   324: aload_3
        //   325: invokevirtual 178	java/io/OutputStreamWriter:close	()V
        //   328: ldc -76
        //   330: invokestatic 98	aca:a	(Ljava/lang/String;)V
        //   333: new 182	java/lang/StringBuffer
        //   336: dup
        //   337: ldc 117
        //   339: invokespecial 183	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   342: astore_2
        //   343: new 185	java/io/BufferedReader
        //   346: dup
        //   347: new 187	java/io/InputStreamReader
        //   350: dup
        //   351: aload_1
        //   352: invokestatic 191	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
        //   355: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   358: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   361: astore_1
        //   362: aload_1
        //   363: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   366: astore_3
        //   367: aload_3
        //   368: ifnull +55 -> 423
        //   371: aload_2
        //   372: aload_3
        //   373: invokevirtual 203	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   376: pop
        //   377: goto -15 -> 362
        //   380: astore_1
        //   381: new 49	java/lang/StringBuilder
        //   384: dup
        //   385: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   388: ldc -51
        //   390: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: aload_1
        //   394: invokevirtual 206	java/lang/Exception:toString	()Ljava/lang/String;
        //   397: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   400: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   403: invokestatic 208	aca:b	(Ljava/lang/String;)V
        //   406: return
        //   407: astore_2
        //   408: aload_1
        //   409: aload_2
        //   410: invokestatic 211	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   413: aload_2
        //   414: athrow
        //   415: astore_2
        //   416: aload_1
        //   417: aload_2
        //   418: invokestatic 211	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   421: aload_2
        //   422: athrow
        //   423: aload_2
        //   424: invokevirtual 212	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   427: astore_1
        //   428: new 49	java/lang/StringBuilder
        //   431: dup
        //   432: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   435: ldc -42
        //   437: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   440: aload_1
        //   441: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   444: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   447: invokestatic 98	aca:a	(Ljava/lang/String;)V
        //   450: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	451	0	this	12
        //   81	282	1	localObject1	Object
        //   380	37	1	localException	Exception
        //   427	14	1	str	String
        //   271	101	2	localObject2	Object
        //   407	7	2	localIOException1	java.io.IOException
        //   415	9	2	localIOException2	java.io.IOException
        //   7	366	3	localObject3	Object
        // Exception table:
        //   from	to	target	type
        //   0	119	380	java/lang/Exception
        //   119	125	380	java/lang/Exception
        //   125	259	380	java/lang/Exception
        //   259	267	380	java/lang/Exception
        //   267	301	380	java/lang/Exception
        //   301	310	380	java/lang/Exception
        //   310	362	380	java/lang/Exception
        //   362	367	380	java/lang/Exception
        //   371	377	380	java/lang/Exception
        //   408	415	380	java/lang/Exception
        //   416	423	380	java/lang/Exception
        //   423	450	380	java/lang/Exception
        //   259	267	407	java/io/IOException
        //   301	310	415	java/io/IOException
      }
    }.start();
  }
  
  public static void b(boolean paramBoolean)
  {
    aca.a("setErrorDebug to " + paramBoolean);
    abw.b = paramBoolean;
  }
  
  private static boolean b(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private static void c(JSONObject paramJSONObject)
    throws JSONException
  {
    new Thread()
    {
      /* Error */
      @android.annotation.SuppressLint({"NewApi"})
      public void run()
      {
        // Byte code:
        //   0: new 28	org/json/JSONObject
        //   3: dup
        //   4: invokespecial 29	org/json/JSONObject:<init>	()V
        //   7: astore_1
        //   8: new 28	org/json/JSONObject
        //   11: dup
        //   12: invokespecial 29	org/json/JSONObject:<init>	()V
        //   15: astore_2
        //   16: aload_2
        //   17: invokestatic 32	abv:a	(Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //   20: pop
        //   21: aload_0
        //   22: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   25: ldc 34
        //   27: invokevirtual 38	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   30: ifeq +31 -> 61
        //   33: aload_2
        //   34: ldc 40
        //   36: aload_0
        //   37: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   40: ldc 34
        //   42: invokevirtual 44	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
        //   45: invokevirtual 50	java/lang/Object:toString	()Ljava/lang/String;
        //   48: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   51: pop
        //   52: aload_1
        //   53: ldc 56
        //   55: ldc 58
        //   57: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   60: pop
        //   61: aload_0
        //   62: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   65: ldc 60
        //   67: invokevirtual 38	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   70: ifeq +12 -> 82
        //   73: aload_1
        //   74: ldc 56
        //   76: ldc 62
        //   78: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   81: pop
        //   82: aload_1
        //   83: ldc 64
        //   85: ldc 66
        //   87: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   90: pop
        //   91: aload_1
        //   92: ldc 68
        //   94: aload_2
        //   95: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   98: pop
        //   99: aload_1
        //   100: ldc 70
        //   102: invokestatic 73	abv:o	()Ljava/lang/String;
        //   105: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   108: pop
        //   109: aload_1
        //   110: ldc 75
        //   112: invokestatic 78	abv:p	()Ljava/lang/String;
        //   115: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   118: pop
        //   119: aload_1
        //   120: ldc 80
        //   122: new 82	java/lang/StringBuilder
        //   125: dup
        //   126: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   129: ldc 85
        //   131: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   134: getstatic 92	abv:a	Ljava/lang/String;
        //   137: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   143: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   146: pop
        //   147: aload_1
        //   148: ldc 95
        //   150: ldc 97
        //   152: invokevirtual 54	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   155: pop
        //   156: invokestatic 99	abv:c	()Ljava/lang/String;
        //   159: ifnull +15 -> 174
        //   162: invokestatic 99	abv:c	()Ljava/lang/String;
        //   165: invokevirtual 104	java/lang/String:trim	()Ljava/lang/String;
        //   168: invokevirtual 108	java/lang/String:isEmpty	()Z
        //   171: ifeq +14 -> 185
        //   174: ldc 110
        //   176: invokestatic 115	aca:a	(Ljava/lang/String;)V
        //   179: ldc 117
        //   181: invokestatic 120	abv:c	(Ljava/lang/String;)Ljava/lang/String;
        //   184: pop
        //   185: new 82	java/lang/StringBuilder
        //   188: dup
        //   189: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   192: ldc 122
        //   194: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: ldc 124
        //   199: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: invokestatic 99	abv:c	()Ljava/lang/String;
        //   205: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: ldc 126
        //   210: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   213: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   216: invokestatic 115	aca:a	(Ljava/lang/String;)V
        //   219: new 128	java/net/URL
        //   222: dup
        //   223: new 82	java/lang/StringBuilder
        //   226: dup
        //   227: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   230: ldc 124
        //   232: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: invokestatic 99	abv:c	()Ljava/lang/String;
        //   238: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: ldc 126
        //   243: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   246: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   249: invokespecial 130	java/net/URL:<init>	(Ljava/lang/String;)V
        //   252: astore_3
        //   253: aload_1
        //   254: invokevirtual 131	org/json/JSONObject:toString	()Ljava/lang/String;
        //   257: astore_2
        //   258: new 82	java/lang/StringBuilder
        //   261: dup
        //   262: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   265: ldc -123
        //   267: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload_2
        //   271: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   277: invokestatic 115	aca:a	(Ljava/lang/String;)V
        //   280: aload_3
        //   281: invokevirtual 137	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   284: checkcast 139	javax/net/ssl/HttpsURLConnection
        //   287: astore_1
        //   288: aload_1
        //   289: ldc -115
        //   291: invokestatic 145	abv:b	()Landroid/content/SharedPreferences;
        //   294: ldc -109
        //   296: ldc -107
        //   298: invokeinterface 155 3 0
        //   303: invokevirtual 159	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   306: aload_1
        //   307: ldc -95
        //   309: ldc -93
        //   311: invokevirtual 159	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   314: aload_1
        //   315: ldc -91
        //   317: invokevirtual 168	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   320: aload_1
        //   321: sipush 30000
        //   324: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   327: aload_1
        //   328: sipush 30000
        //   331: invokevirtual 175	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   334: aload_1
        //   335: iconst_1
        //   336: invokevirtual 179	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   339: aload_1
        //   340: iconst_1
        //   341: invokevirtual 182	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   344: aload_1
        //   345: invokestatic 187	ax:a	(Ljava/net/URLConnection;)V
        //   348: aload_1
        //   349: invokevirtual 190	javax/net/ssl/HttpsURLConnection:connect	()V
        //   352: aload_1
        //   353: invokestatic 192	ax:b	(Ljava/net/URLConnection;)V
        //   356: aload_1
        //   357: invokestatic 187	ax:a	(Ljava/net/URLConnection;)V
        //   360: aload_1
        //   361: invokevirtual 196	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   364: astore_3
        //   365: aload_1
        //   366: invokestatic 192	ax:b	(Ljava/net/URLConnection;)V
        //   369: new 198	java/io/OutputStreamWriter
        //   372: dup
        //   373: aload_3
        //   374: invokespecial 201	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   377: astore_3
        //   378: aload_3
        //   379: aload_2
        //   380: invokevirtual 204	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   383: aload_3
        //   384: invokevirtual 207	java/io/OutputStreamWriter:close	()V
        //   387: ldc -47
        //   389: invokestatic 115	aca:a	(Ljava/lang/String;)V
        //   392: new 211	java/lang/StringBuffer
        //   395: dup
        //   396: ldc -107
        //   398: invokespecial 212	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   401: astore_2
        //   402: new 214	java/io/BufferedReader
        //   405: dup
        //   406: new 216	java/io/InputStreamReader
        //   409: dup
        //   410: aload_1
        //   411: invokestatic 220	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
        //   414: invokespecial 223	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   417: invokespecial 226	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   420: astore_1
        //   421: aload_1
        //   422: invokevirtual 229	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   425: astore_3
        //   426: aload_3
        //   427: ifnull +95 -> 522
        //   430: aload_2
        //   431: aload_3
        //   432: invokevirtual 232	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   435: pop
        //   436: goto -15 -> 421
        //   439: astore_1
        //   440: new 82	java/lang/StringBuilder
        //   443: dup
        //   444: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   447: ldc -22
        //   449: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   452: aload_0
        //   453: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   456: invokevirtual 131	org/json/JSONObject:toString	()Ljava/lang/String;
        //   459: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   462: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   465: invokestatic 236	aca:b	(Ljava/lang/String;)V
        //   468: invokestatic 145	abv:b	()Landroid/content/SharedPreferences;
        //   471: invokeinterface 240 1 0
        //   476: ldc -14
        //   478: aload_0
        //   479: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   482: invokevirtual 131	org/json/JSONObject:toString	()Ljava/lang/String;
        //   485: invokeinterface 248 3 0
        //   490: invokeinterface 251 1 0
        //   495: getstatic 256	abw:b	Z
        //   498: ifeq +7 -> 505
        //   501: aload_1
        //   502: invokevirtual 259	java/lang/Exception:printStackTrace	()V
        //   505: return
        //   506: astore_2
        //   507: aload_1
        //   508: aload_2
        //   509: invokestatic 262	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   512: aload_2
        //   513: athrow
        //   514: astore_2
        //   515: aload_1
        //   516: aload_2
        //   517: invokestatic 262	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   520: aload_2
        //   521: athrow
        //   522: aload_2
        //   523: invokevirtual 263	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   526: astore_1
        //   527: new 82	java/lang/StringBuilder
        //   530: dup
        //   531: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   534: ldc_w 265
        //   537: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   540: aload_1
        //   541: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   544: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   547: invokestatic 115	aca:a	(Ljava/lang/String;)V
        //   550: new 28	org/json/JSONObject
        //   553: dup
        //   554: aload_1
        //   555: invokespecial 266	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   558: astore_1
        //   559: aload_1
        //   560: ifnull +43 -> 603
        //   563: aload_1
        //   564: ldc_w 268
        //   567: invokevirtual 44	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
        //   570: invokevirtual 50	java/lang/Object:toString	()Ljava/lang/String;
        //   573: ldc_w 270
        //   576: invokevirtual 274	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   579: ifeq +24 -> 603
        //   582: invokestatic 145	abv:b	()Landroid/content/SharedPreferences;
        //   585: invokeinterface 240 1 0
        //   590: ldc -14
        //   592: invokeinterface 277 2 0
        //   597: invokeinterface 251 1 0
        //   602: return
        //   603: new 82	java/lang/StringBuilder
        //   606: dup
        //   607: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   610: ldc_w 279
        //   613: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   616: aload_0
        //   617: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   620: invokevirtual 131	org/json/JSONObject:toString	()Ljava/lang/String;
        //   623: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   626: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   629: invokestatic 236	aca:b	(Ljava/lang/String;)V
        //   632: invokestatic 145	abv:b	()Landroid/content/SharedPreferences;
        //   635: invokeinterface 240 1 0
        //   640: ldc -14
        //   642: aload_0
        //   643: getfield 14	abv$9:a	Lorg/json/JSONObject;
        //   646: invokevirtual 131	org/json/JSONObject:toString	()Ljava/lang/String;
        //   649: invokeinterface 248 3 0
        //   654: invokeinterface 251 1 0
        //   659: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	660	0	this	9
        //   7	415	1	localObject1	Object
        //   439	77	1	localException	Exception
        //   526	38	1	localObject2	Object
        //   15	416	2	localObject3	Object
        //   506	7	2	localIOException1	java.io.IOException
        //   514	9	2	localIOException2	java.io.IOException
        //   252	180	3	localObject4	Object
        // Exception table:
        //   from	to	target	type
        //   0	61	439	java/lang/Exception
        //   61	82	439	java/lang/Exception
        //   82	174	439	java/lang/Exception
        //   174	185	439	java/lang/Exception
        //   185	348	439	java/lang/Exception
        //   348	356	439	java/lang/Exception
        //   356	360	439	java/lang/Exception
        //   360	369	439	java/lang/Exception
        //   369	421	439	java/lang/Exception
        //   421	426	439	java/lang/Exception
        //   430	436	439	java/lang/Exception
        //   507	514	439	java/lang/Exception
        //   515	522	439	java/lang/Exception
        //   522	559	439	java/lang/Exception
        //   563	602	439	java/lang/Exception
        //   603	659	439	java/lang/Exception
        //   348	356	506	java/io/IOException
        //   360	369	514	java/io/IOException
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
  
  private void g(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      af = c.getSharedPreferences("initPrefs", 0);
      af.edit().putString("currency", paramString).apply();
    }
  }
  
  private static void h(String paramString)
  {
    new Thread()
    {
      public void run()
      {
        aca.a("Got event " + this.a);
        Object localObject1 = new HashMap();
        ((HashMap)localObject1).put("state", this.a);
        aca.a("FIRE EVENT*** action:" + "session");
        aca.a("FIRE EVENT*** properties:" + localObject1);
        JSONObject localJSONObject1 = new JSONObject();
        label308:
        do
        {
          JSONObject localJSONObject2;
          Object localObject2;
          try
          {
            localJSONObject1.put("kochava_app_id", abv.o());
            localJSONObject1.put("kochava_device_id", abv.p());
            localJSONObject1.put("action", "session");
            localJSONObject1.put("dev_id_strategy", abv.w());
            localJSONObject2 = abv.a(new JSONObject());
            if (abv.x() != null)
            {
              localObject2 = abv.x().entrySet().iterator();
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
            if (abw.b) {
              localJSONException.printStackTrace();
            }
            aca.b("event " + localJSONException);
            return;
          }
          localObject1 = ((HashMap)localObject1).entrySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (Map.Entry)((Iterator)localObject1).next();
            localJSONObject2.put((String)((Map.Entry)localObject2).getKey(), ((Map.Entry)localObject2).getValue());
          }
          localJSONException.put("data", localJSONObject2);
          aca.a("fireEvent with properties: " + localJSONException);
        } while (abv.v().a(localJSONException, false, false) < 50);
        abv.a();
      }
    }.start();
  }
  
  private void j(boolean paramBoolean)
  {
    if ((af.getString("initBool", "").equals("false")) && (this.ae != null) && (this.ad != null)) {
      try
      {
        aca.a("Initial properties: " + this.ae);
        aca.a("Initital Oject: " + this.ad);
        if (!af.getString("initData", "noData").equals("noData"))
        {
          this.ae.put("conversion_type", "gplay");
          this.ae.put("conversion_data", af.getString("initData", ""));
          aca.a("Got referral, attaching: " + af.getString("initData", ""));
        }
        for (;;)
        {
          this.ad.put("data", this.ae);
          Q.a(this.ad, true, false);
          aca.a("Sending Initial");
          af.edit().putString("initBool", "true").apply();
          if (!paramBoolean) {
            break;
          }
          this.U.cancel();
          return;
          aca.b("Did not get referral data.");
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        aca.b("An error occured during que initial. " + localJSONException);
        if (abw.b) {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  protected void a(final String paramString)
  {
    paramString = new Runnable()
    {
      /* Error */
      @android.annotation.SuppressLint({"NewApi"})
      public void run()
      {
        // Byte code:
        //   0: new 34	java/lang/StringBuilder
        //   3: dup
        //   4: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   7: ldc 37
        //   9: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12: aload_0
        //   13: getfield 20	abv$18:a	Ljava/lang/String;
        //   16: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   19: ldc 43
        //   21: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   24: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   27: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   30: new 52	java/util/HashMap
        //   33: dup
        //   34: invokespecial 53	java/util/HashMap:<init>	()V
        //   37: astore_1
        //   38: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   41: ldc 58
        //   43: invokeinterface 64 2 0
        //   48: ifne +560 -> 608
        //   51: new 34	java/lang/StringBuilder
        //   54: dup
        //   55: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   58: ldc 66
        //   60: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   63: aload_0
        //   64: getfield 18	abv$18:b	Labv;
        //   67: invokestatic 70	abv:h	(Labv;)Ljava/lang/String;
        //   70: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   73: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   76: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   79: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   82: invokeinterface 74 1 0
        //   87: ldc 58
        //   89: aload_0
        //   90: getfield 18	abv$18:b	Labv;
        //   93: invokestatic 70	abv:h	(Labv;)Ljava/lang/String;
        //   96: invokeinterface 80 3 0
        //   101: invokeinterface 83 1 0
        //   106: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   109: ldc 85
        //   111: invokeinterface 64 2 0
        //   116: ifne +607 -> 723
        //   119: new 34	java/lang/StringBuilder
        //   122: dup
        //   123: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   126: ldc 87
        //   128: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: aload_0
        //   132: getfield 18	abv$18:b	Labv;
        //   135: invokestatic 91	abv:i	(Labv;)Z
        //   138: invokevirtual 94	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   141: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   144: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   147: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   150: invokeinterface 74 1 0
        //   155: ldc 85
        //   157: aload_0
        //   158: getfield 18	abv$18:b	Labv;
        //   161: invokestatic 91	abv:i	(Labv;)Z
        //   164: invokeinterface 98 3 0
        //   169: invokeinterface 83 1 0
        //   174: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   177: ldc 100
        //   179: invokeinterface 64 2 0
        //   184: ifne +650 -> 834
        //   187: new 34	java/lang/StringBuilder
        //   190: dup
        //   191: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   194: ldc 102
        //   196: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: aload_0
        //   200: getfield 18	abv$18:b	Labv;
        //   203: invokestatic 104	abv:b	(Labv;)Ljava/lang/String;
        //   206: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   212: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   215: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   218: invokeinterface 74 1 0
        //   223: ldc 100
        //   225: aload_0
        //   226: getfield 18	abv$18:b	Labv;
        //   229: invokestatic 104	abv:b	(Labv;)Ljava/lang/String;
        //   232: invokeinterface 80 3 0
        //   237: invokeinterface 83 1 0
        //   242: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   245: ldc 106
        //   247: invokeinterface 64 2 0
        //   252: ifne +697 -> 949
        //   255: new 34	java/lang/StringBuilder
        //   258: dup
        //   259: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   262: ldc 108
        //   264: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: invokestatic 112	abv:s	()Z
        //   270: invokevirtual 94	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   273: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   276: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   279: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   282: invokeinterface 74 1 0
        //   287: ldc 106
        //   289: invokestatic 112	abv:s	()Z
        //   292: invokeinterface 98 3 0
        //   297: invokeinterface 83 1 0
        //   302: invokestatic 115	abv:t	()Z
        //   305: ifeq +73 -> 378
        //   308: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   311: ldc 117
        //   313: invokeinterface 64 2 0
        //   318: ifne +726 -> 1044
        //   321: new 34	java/lang/StringBuilder
        //   324: dup
        //   325: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   328: ldc 119
        //   330: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: invokestatic 122	abv:m	()Ljava/lang/String;
        //   336: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   342: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   345: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   348: invokeinterface 74 1 0
        //   353: ldc 117
        //   355: invokestatic 122	abv:m	()Ljava/lang/String;
        //   358: invokeinterface 80 3 0
        //   363: invokeinterface 83 1 0
        //   368: aload_1
        //   369: ldc 117
        //   371: invokestatic 122	abv:m	()Ljava/lang/String;
        //   374: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   377: pop
        //   378: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   381: ldc -128
        //   383: invokeinterface 64 2 0
        //   388: ifne +737 -> 1125
        //   391: new 34	java/lang/StringBuilder
        //   394: dup
        //   395: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   398: ldc -126
        //   400: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   403: invokestatic 133	abv:u	()Ljava/lang/String;
        //   406: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   409: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   412: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   415: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   418: invokeinterface 74 1 0
        //   423: ldc -128
        //   425: invokestatic 133	abv:u	()Ljava/lang/String;
        //   428: invokeinterface 80 3 0
        //   433: invokeinterface 83 1 0
        //   438: aload_1
        //   439: invokevirtual 137	java/util/HashMap:keySet	()Ljava/util/Set;
        //   442: invokeinterface 142 1 0
        //   447: ifne +160 -> 607
        //   450: new 144	org/json/JSONObject
        //   453: dup
        //   454: invokespecial 145	org/json/JSONObject:<init>	()V
        //   457: astore_2
        //   458: aload_2
        //   459: ldc -109
        //   461: ldc -107
        //   463: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   466: pop
        //   467: aload_2
        //   468: ldc -102
        //   470: invokestatic 157	abv:p	()Ljava/lang/String;
        //   473: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   476: pop
        //   477: aload_2
        //   478: ldc -97
        //   480: invokestatic 162	abv:o	()Ljava/lang/String;
        //   483: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   486: pop
        //   487: aload_2
        //   488: ldc -92
        //   490: new 34	java/lang/StringBuilder
        //   493: dup
        //   494: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   497: ldc -90
        //   499: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: getstatic 167	abv:a	Ljava/lang/String;
        //   505: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   508: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   511: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   514: pop
        //   515: aload_2
        //   516: ldc -87
        //   518: ldc -85
        //   520: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   523: pop
        //   524: new 144	org/json/JSONObject
        //   527: dup
        //   528: invokespecial 145	org/json/JSONObject:<init>	()V
        //   531: astore_3
        //   532: aload_1
        //   533: invokevirtual 137	java/util/HashMap:keySet	()Ljava/util/Set;
        //   536: invokeinterface 175 1 0
        //   541: astore 4
        //   543: aload 4
        //   545: invokeinterface 180 1 0
        //   550: ifeq +683 -> 1233
        //   553: aload 4
        //   555: invokeinterface 184 1 0
        //   560: checkcast 186	java/lang/String
        //   563: astore 5
        //   565: aload_3
        //   566: aload 5
        //   568: aload_1
        //   569: aload 5
        //   571: invokevirtual 190	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   574: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   577: pop
        //   578: goto -35 -> 543
        //   581: astore_1
        //   582: new 34	java/lang/StringBuilder
        //   585: dup
        //   586: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   589: ldc -64
        //   591: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   594: aload_1
        //   595: invokevirtual 193	java/lang/Exception:toString	()Ljava/lang/String;
        //   598: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   601: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   604: invokestatic 195	aca:b	(Ljava/lang/String;)V
        //   607: return
        //   608: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   611: ldc 58
        //   613: ldc -59
        //   615: invokeinterface 201 3 0
        //   620: aload_0
        //   621: getfield 18	abv$18:b	Labv;
        //   624: invokestatic 70	abv:h	(Labv;)Ljava/lang/String;
        //   627: invokevirtual 205	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   630: ifne -524 -> 106
        //   633: new 34	java/lang/StringBuilder
        //   636: dup
        //   637: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   640: ldc -49
        //   642: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   645: aload_0
        //   646: getfield 18	abv$18:b	Labv;
        //   649: invokestatic 70	abv:h	(Labv;)Ljava/lang/String;
        //   652: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   655: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   658: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   661: aload_1
        //   662: ldc 58
        //   664: new 34	java/lang/StringBuilder
        //   667: dup
        //   668: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   671: aload_0
        //   672: getfield 18	abv$18:b	Labv;
        //   675: invokestatic 70	abv:h	(Labv;)Ljava/lang/String;
        //   678: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   681: ldc -59
        //   683: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   689: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   692: pop
        //   693: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   696: invokeinterface 74 1 0
        //   701: ldc 58
        //   703: aload_0
        //   704: getfield 18	abv$18:b	Labv;
        //   707: invokestatic 70	abv:h	(Labv;)Ljava/lang/String;
        //   710: invokeinterface 80 3 0
        //   715: invokeinterface 83 1 0
        //   720: goto -614 -> 106
        //   723: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   726: ldc 85
        //   728: iconst_0
        //   729: invokeinterface 211 3 0
        //   734: aload_0
        //   735: getfield 18	abv$18:b	Labv;
        //   738: invokestatic 91	abv:i	(Labv;)Z
        //   741: if_icmpeq -567 -> 174
        //   744: new 34	java/lang/StringBuilder
        //   747: dup
        //   748: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   751: ldc -43
        //   753: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   756: aload_0
        //   757: getfield 18	abv$18:b	Labv;
        //   760: invokestatic 91	abv:i	(Labv;)Z
        //   763: invokevirtual 94	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   766: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   769: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   772: aload_1
        //   773: ldc 85
        //   775: new 34	java/lang/StringBuilder
        //   778: dup
        //   779: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   782: aload_0
        //   783: getfield 18	abv$18:b	Labv;
        //   786: invokestatic 91	abv:i	(Labv;)Z
        //   789: invokevirtual 94	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   792: ldc -59
        //   794: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   797: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   800: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   803: pop
        //   804: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   807: invokeinterface 74 1 0
        //   812: ldc 85
        //   814: aload_0
        //   815: getfield 18	abv$18:b	Labv;
        //   818: invokestatic 91	abv:i	(Labv;)Z
        //   821: invokeinterface 98 3 0
        //   826: invokeinterface 83 1 0
        //   831: goto -657 -> 174
        //   834: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   837: ldc 100
        //   839: ldc -59
        //   841: invokeinterface 201 3 0
        //   846: aload_0
        //   847: getfield 18	abv$18:b	Labv;
        //   850: invokestatic 104	abv:b	(Labv;)Ljava/lang/String;
        //   853: invokevirtual 205	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   856: ifne -614 -> 242
        //   859: new 34	java/lang/StringBuilder
        //   862: dup
        //   863: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   866: ldc -41
        //   868: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   871: aload_0
        //   872: getfield 18	abv$18:b	Labv;
        //   875: invokestatic 104	abv:b	(Labv;)Ljava/lang/String;
        //   878: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   881: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   884: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   887: aload_1
        //   888: ldc 100
        //   890: new 34	java/lang/StringBuilder
        //   893: dup
        //   894: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   897: aload_0
        //   898: getfield 18	abv$18:b	Labv;
        //   901: invokestatic 104	abv:b	(Labv;)Ljava/lang/String;
        //   904: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   907: ldc -59
        //   909: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   912: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   915: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   918: pop
        //   919: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   922: invokeinterface 74 1 0
        //   927: ldc 100
        //   929: aload_0
        //   930: getfield 18	abv$18:b	Labv;
        //   933: invokestatic 104	abv:b	(Labv;)Ljava/lang/String;
        //   936: invokeinterface 80 3 0
        //   941: invokeinterface 83 1 0
        //   946: goto -704 -> 242
        //   949: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   952: ldc 106
        //   954: iconst_0
        //   955: invokeinterface 211 3 0
        //   960: invokestatic 112	abv:s	()Z
        //   963: if_icmpeq -661 -> 302
        //   966: new 34	java/lang/StringBuilder
        //   969: dup
        //   970: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   973: ldc -39
        //   975: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   978: invokestatic 112	abv:s	()Z
        //   981: invokevirtual 94	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   984: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   987: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   990: aload_1
        //   991: ldc 106
        //   993: new 34	java/lang/StringBuilder
        //   996: dup
        //   997: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1000: invokestatic 112	abv:s	()Z
        //   1003: invokevirtual 94	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   1006: ldc -59
        //   1008: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1011: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1014: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   1017: pop
        //   1018: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1021: invokeinterface 74 1 0
        //   1026: ldc 106
        //   1028: invokestatic 112	abv:s	()Z
        //   1031: invokeinterface 98 3 0
        //   1036: invokeinterface 83 1 0
        //   1041: goto -739 -> 302
        //   1044: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1047: ldc 117
        //   1049: ldc -59
        //   1051: invokeinterface 201 3 0
        //   1056: invokestatic 122	abv:m	()Ljava/lang/String;
        //   1059: invokevirtual 205	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   1062: ifne -684 -> 378
        //   1065: new 34	java/lang/StringBuilder
        //   1068: dup
        //   1069: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1072: ldc -37
        //   1074: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1077: invokestatic 122	abv:m	()Ljava/lang/String;
        //   1080: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1083: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1086: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   1089: aload_1
        //   1090: ldc 117
        //   1092: invokestatic 122	abv:m	()Ljava/lang/String;
        //   1095: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   1098: pop
        //   1099: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1102: invokeinterface 74 1 0
        //   1107: ldc 117
        //   1109: invokestatic 122	abv:m	()Ljava/lang/String;
        //   1112: invokeinterface 80 3 0
        //   1117: invokeinterface 83 1 0
        //   1122: goto -744 -> 378
        //   1125: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1128: ldc -128
        //   1130: ldc -59
        //   1132: invokeinterface 201 3 0
        //   1137: invokestatic 133	abv:u	()Ljava/lang/String;
        //   1140: invokevirtual 205	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   1143: ifne -705 -> 438
        //   1146: new 34	java/lang/StringBuilder
        //   1149: dup
        //   1150: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1153: ldc -35
        //   1155: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1158: invokestatic 133	abv:u	()Ljava/lang/String;
        //   1161: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1164: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1167: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   1170: aload_1
        //   1171: ldc -128
        //   1173: invokestatic 133	abv:u	()Ljava/lang/String;
        //   1176: invokevirtual 126	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   1179: pop
        //   1180: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1183: invokeinterface 74 1 0
        //   1188: ldc -128
        //   1190: invokestatic 133	abv:u	()Ljava/lang/String;
        //   1193: invokeinterface 80 3 0
        //   1198: invokeinterface 83 1 0
        //   1203: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1206: invokeinterface 74 1 0
        //   1211: ldc -33
        //   1213: aload_0
        //   1214: getfield 18	abv$18:b	Labv;
        //   1217: invokestatic 226	abv:j	(Labv;)Ljava/lang/String;
        //   1220: invokeinterface 80 3 0
        //   1225: invokeinterface 83 1 0
        //   1230: goto -792 -> 438
        //   1233: aload_2
        //   1234: ldc -28
        //   1236: aload_3
        //   1237: invokevirtual 152	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1240: pop
        //   1241: invokestatic 231	abv:c	()Ljava/lang/String;
        //   1244: ifnull +15 -> 1259
        //   1247: invokestatic 231	abv:c	()Ljava/lang/String;
        //   1250: invokevirtual 234	java/lang/String:trim	()Ljava/lang/String;
        //   1253: invokevirtual 235	java/lang/String:isEmpty	()Z
        //   1256: ifeq +9 -> 1265
        //   1259: ldc -19
        //   1261: invokestatic 240	abv:c	(Ljava/lang/String;)Ljava/lang/String;
        //   1264: pop
        //   1265: new 34	java/lang/StringBuilder
        //   1268: dup
        //   1269: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1272: ldc -14
        //   1274: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1277: ldc -12
        //   1279: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1282: invokestatic 231	abv:c	()Ljava/lang/String;
        //   1285: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1288: ldc -10
        //   1290: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1293: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1296: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   1299: new 248	java/net/URL
        //   1302: dup
        //   1303: new 34	java/lang/StringBuilder
        //   1306: dup
        //   1307: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1310: ldc -12
        //   1312: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1315: invokestatic 231	abv:c	()Ljava/lang/String;
        //   1318: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1321: ldc -10
        //   1323: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1326: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1329: invokespecial 250	java/net/URL:<init>	(Ljava/lang/String;)V
        //   1332: invokevirtual 254	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   1335: checkcast 256	javax/net/ssl/HttpsURLConnection
        //   1338: astore_1
        //   1339: aload_1
        //   1340: ldc_w 258
        //   1343: invokestatic 56	abv:b	()Landroid/content/SharedPreferences;
        //   1346: ldc -33
        //   1348: ldc -59
        //   1350: invokeinterface 201 3 0
        //   1355: invokevirtual 262	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   1358: aload_1
        //   1359: ldc_w 264
        //   1362: ldc_w 266
        //   1365: invokevirtual 262	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   1368: aload_1
        //   1369: ldc_w 268
        //   1372: invokevirtual 271	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   1375: aload_1
        //   1376: sipush 30000
        //   1379: invokevirtual 275	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
        //   1382: aload_1
        //   1383: sipush 30000
        //   1386: invokevirtual 278	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
        //   1389: aload_1
        //   1390: iconst_1
        //   1391: invokevirtual 282	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
        //   1394: aload_1
        //   1395: iconst_1
        //   1396: invokevirtual 285	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
        //   1399: aload_1
        //   1400: invokestatic 290	ax:a	(Ljava/net/URLConnection;)V
        //   1403: aload_1
        //   1404: invokevirtual 293	javax/net/ssl/HttpsURLConnection:connect	()V
        //   1407: aload_1
        //   1408: invokestatic 295	ax:b	(Ljava/net/URLConnection;)V
        //   1411: aload_2
        //   1412: invokevirtual 296	org/json/JSONObject:toString	()Ljava/lang/String;
        //   1415: astore_3
        //   1416: new 34	java/lang/StringBuilder
        //   1419: dup
        //   1420: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1423: ldc_w 298
        //   1426: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1429: aload_2
        //   1430: invokevirtual 296	org/json/JSONObject:toString	()Ljava/lang/String;
        //   1433: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1436: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1439: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   1442: aload_1
        //   1443: invokestatic 290	ax:a	(Ljava/net/URLConnection;)V
        //   1446: aload_1
        //   1447: invokevirtual 302	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   1450: astore_2
        //   1451: aload_1
        //   1452: invokestatic 295	ax:b	(Ljava/net/URLConnection;)V
        //   1455: new 304	java/io/OutputStreamWriter
        //   1458: dup
        //   1459: aload_2
        //   1460: invokespecial 307	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
        //   1463: astore_2
        //   1464: aload_2
        //   1465: aload_3
        //   1466: invokevirtual 310	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
        //   1469: aload_2
        //   1470: invokevirtual 313	java/io/OutputStreamWriter:close	()V
        //   1473: ldc_w 315
        //   1476: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   1479: new 317	java/lang/StringBuffer
        //   1482: dup
        //   1483: ldc -59
        //   1485: invokespecial 318	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
        //   1488: astore_2
        //   1489: new 320	java/io/BufferedReader
        //   1492: dup
        //   1493: new 322	java/io/InputStreamReader
        //   1496: dup
        //   1497: aload_1
        //   1498: invokestatic 326	ax:d	(Ljava/net/URLConnection;)Ljava/io/InputStream;
        //   1501: invokespecial 329	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   1504: invokespecial 332	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   1507: astore_1
        //   1508: aload_1
        //   1509: invokevirtual 335	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   1512: astore_3
        //   1513: aload_3
        //   1514: ifnull +28 -> 1542
        //   1517: aload_2
        //   1518: aload_3
        //   1519: invokevirtual 338	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   1522: pop
        //   1523: goto -15 -> 1508
        //   1526: astore_2
        //   1527: aload_1
        //   1528: aload_2
        //   1529: invokestatic 341	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   1532: aload_2
        //   1533: athrow
        //   1534: astore_2
        //   1535: aload_1
        //   1536: aload_2
        //   1537: invokestatic 341	ax:a	(Ljava/net/URLConnection;Ljava/io/IOException;)V
        //   1540: aload_2
        //   1541: athrow
        //   1542: aload_2
        //   1543: invokevirtual 342	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   1546: astore_1
        //   1547: new 34	java/lang/StringBuilder
        //   1550: dup
        //   1551: invokespecial 35	java/lang/StringBuilder:<init>	()V
        //   1554: ldc_w 344
        //   1557: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1560: aload_1
        //   1561: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1564: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1567: invokestatic 50	aca:a	(Ljava/lang/String;)V
        //   1570: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1571	0	this	18
        //   37	532	1	localHashMap	HashMap
        //   581	590	1	localException	Exception
        //   1338	223	1	localObject1	Object
        //   457	1061	2	localObject2	Object
        //   1526	7	2	localIOException1	java.io.IOException
        //   1534	9	2	localIOException2	java.io.IOException
        //   531	988	3	localObject3	Object
        //   541	13	4	localIterator	Iterator
        //   563	7	5	str	String
        // Exception table:
        //   from	to	target	type
        //   450	543	581	java/lang/Exception
        //   543	578	581	java/lang/Exception
        //   1233	1259	581	java/lang/Exception
        //   1259	1265	581	java/lang/Exception
        //   1265	1403	581	java/lang/Exception
        //   1403	1411	581	java/lang/Exception
        //   1411	1446	581	java/lang/Exception
        //   1446	1455	581	java/lang/Exception
        //   1455	1508	581	java/lang/Exception
        //   1508	1513	581	java/lang/Exception
        //   1517	1523	581	java/lang/Exception
        //   1527	1534	581	java/lang/Exception
        //   1535	1542	581	java/lang/Exception
        //   1542	1570	581	java/lang/Exception
        //   1403	1411	1526	java/io/IOException
        //   1446	1455	1534	java/io/IOException
      }
    };
    aj.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
  
  public void a(final String paramString1, final String paramString2)
  {
    if (aa)
    {
      aca.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      new Thread()
      {
        public void run()
        {
          aca.a("Got event " + paramString1);
          HashMap localHashMap = new HashMap();
          localHashMap.put("event_name", paramString1);
          localHashMap.put("event_data", paramString2);
          abv.a(abv.this, "event", localHashMap, null);
        }
      }.start();
      return;
    }
    catch (Exception paramString1)
    {
      aca.b("Error in event call: " + paramString1);
    }
  }
  
  public static class a
  {
    protected static boolean a = false;
    protected static boolean b = false;
    
    protected static void a(String paramString)
    {
      new Thread()
      {
        public void run()
        {
          if (!abv.a.a) {
            aca.a("AppLifeCycleStatusManager - not active");
          }
          do
          {
            return;
            if (this.a.equals("is_focused"))
            {
              if (!abv.a.b)
              {
                aca.a("AppLifeCycleStatusManager - not already resumed, starting session...");
                abv.D();
                abv.a.b = true;
                return;
              }
              aca.a("AppLifeCycleStatusManager - IS_FOCUSED received, App is already in focused state.");
              return;
            }
          } while (!this.a.equals("is_in_background"));
          if (abv.a.b)
          {
            aca.a("AppLifeCycleStatusManager - going to background from app, ending session");
            abv.E();
            abv.a.b = false;
            return;
          }
          aca.a("AppLifeCycleStatusManager - IS_IN_BACKGROUND received, App is already in background state.");
        }
      }.start();
    }
  }
  
  public class b
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
  public class c
    implements Application.ActivityLifecycleCallbacks
  {
    protected c() {}
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity)
    {
      aca.a("LifeCycleTracker - Tracking Activity lost focus");
      abv.a.a("is_in_background");
      abv.i(true);
    }
    
    public void onActivityResumed(Activity paramActivity)
    {
      aca.a("LifeCycleTracker - Tracking Activity Resumed");
      abv.a.a("is_focused");
      abv.i(false);
    }
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity) {}
    
    public void onActivityStopped(Activity paramActivity) {}
  }
  
  @TargetApi(14)
  public class d
    implements ComponentCallbacks2
  {
    protected d() {}
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory() {}
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt == 20)
      {
        aca.a("MemoryBoss - Tracking Activity lost focus");
        abv.a.a("is_in_background");
        abv.i(true);
      }
    }
  }
  
  static class e
    implements Runnable
  {
    private e() {}
    
    public void run()
    {
      abv.C();
    }
  }
}
