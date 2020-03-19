import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

public final class aax
{
  private static int A;
  private static int B;
  private static boolean D;
  private static boolean E;
  private static boolean F;
  private static boolean G;
  private static boolean H;
  private static long I;
  private static long J;
  private static List<aaz> K;
  private static aav N;
  private static Timer P;
  private static boolean S;
  private static boolean T;
  private static boolean U;
  private static long W;
  private static boolean X = true;
  private static boolean Y = false;
  private static boolean Z = false;
  protected static String a;
  private static SharedPreferences ac;
  private static SharedPreferences ad;
  private static final ExecutorService ae = Executors.newFixedThreadPool(1);
  private static final ScheduledExecutorService af = Executors.newSingleThreadScheduledExecutor();
  private static final Uri ag = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static HashMap<String, Boolean> ah = new HashMap() {};
  private static JSONArray ai = new JSONArray();
  private static Runnable ak = new Runnable()
  {
    /* Error */
    public final void run()
    {
      // Byte code:
      //   0: bipush 60
      //   2: istore_2
      //   3: iload_2
      //   4: istore_1
      //   5: invokestatic 26	aax:d	()Ljava/lang/String;
      //   8: ifnull +17 -> 25
      //   11: iload_2
      //   12: istore_1
      //   13: invokestatic 26	aax:d	()Ljava/lang/String;
      //   16: invokevirtual 31	java/lang/String:trim	()Ljava/lang/String;
      //   19: invokevirtual 35	java/lang/String:isEmpty	()Z
      //   22: ifeq +16 -> 38
      //   25: iload_2
      //   26: istore_1
      //   27: invokestatic 40	abh:a	()V
      //   30: iload_2
      //   31: istore_1
      //   32: ldc 42
      //   34: invokestatic 46	aax:b	(Ljava/lang/String;)Ljava/lang/String;
      //   37: pop
      //   38: iload_2
      //   39: istore_1
      //   40: new 48	java/lang/StringBuilder
      //   43: dup
      //   44: ldc 50
      //   46: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   49: invokestatic 26	aax:d	()Ljava/lang/String;
      //   52: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   55: ldc 59
      //   57: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   60: pop
      //   61: iload_2
      //   62: istore_1
      //   63: invokestatic 40	abh:a	()V
      //   66: iload_2
      //   67: istore_1
      //   68: new 61	java/net/URL
      //   71: dup
      //   72: new 48	java/lang/StringBuilder
      //   75: dup
      //   76: ldc 63
      //   78: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   81: invokestatic 26	aax:d	()Ljava/lang/String;
      //   84: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: ldc 59
      //   89: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   92: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   95: invokespecial 67	java/net/URL:<init>	(Ljava/lang/String;)V
      //   98: invokevirtual 71	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   101: checkcast 73	javax/net/ssl/HttpsURLConnection
      //   104: astore 6
      //   106: iload_2
      //   107: istore_1
      //   108: aload 6
      //   110: ldc 75
      //   112: invokestatic 79	aax:c	()Landroid/content/SharedPreferences;
      //   115: ldc 81
      //   117: ldc 83
      //   119: invokeinterface 89 3 0
      //   124: invokevirtual 93	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   127: iload_2
      //   128: istore_1
      //   129: aload 6
      //   131: ldc 95
      //   133: ldc 97
      //   135: invokevirtual 93	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   138: iload_2
      //   139: istore_1
      //   140: aload 6
      //   142: ldc 99
      //   144: invokevirtual 102	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   147: iload_2
      //   148: istore_1
      //   149: aload 6
      //   151: sipush 30000
      //   154: invokevirtual 106	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   157: iload_2
      //   158: istore_1
      //   159: aload 6
      //   161: sipush 30000
      //   164: invokevirtual 109	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   167: iload_2
      //   168: istore_1
      //   169: aload 6
      //   171: iconst_1
      //   172: invokevirtual 113	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   175: iload_2
      //   176: istore_1
      //   177: aload 6
      //   179: iconst_1
      //   180: invokevirtual 116	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   183: iload_2
      //   184: istore_1
      //   185: aload 6
      //   187: invokevirtual 119	javax/net/ssl/HttpsURLConnection:connect	()V
      //   190: iload_2
      //   191: istore_1
      //   192: new 121	org/json/JSONObject
      //   195: dup
      //   196: invokespecial 122	org/json/JSONObject:<init>	()V
      //   199: astore 7
      //   201: iload_2
      //   202: istore_1
      //   203: aload 7
      //   205: ldc 124
      //   207: ldc 126
      //   209: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   212: pop
      //   213: iload_2
      //   214: istore_1
      //   215: aload 7
      //   217: ldc -124
      //   219: invokestatic 135	aax:v	()Ljava/lang/String;
      //   222: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   225: pop
      //   226: iload_2
      //   227: istore_1
      //   228: aload 7
      //   230: ldc -119
      //   232: invokestatic 140	aax:w	()Ljava/lang/String;
      //   235: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   238: pop
      //   239: iload_2
      //   240: istore_1
      //   241: aload 7
      //   243: ldc -114
      //   245: new 48	java/lang/StringBuilder
      //   248: dup
      //   249: ldc -112
      //   251: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   254: getstatic 147	aax:a	Ljava/lang/String;
      //   257: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   260: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   263: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   266: pop
      //   267: iload_2
      //   268: istore_1
      //   269: aload 7
      //   271: ldc -107
      //   273: ldc -105
      //   275: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   278: pop
      //   279: iload_2
      //   280: istore_1
      //   281: aload 7
      //   283: invokevirtual 152	org/json/JSONObject:toString	()Ljava/lang/String;
      //   286: astore 7
      //   288: iload_2
      //   289: istore_1
      //   290: invokestatic 40	abh:a	()V
      //   293: iload_2
      //   294: istore_1
      //   295: invokestatic 40	abh:a	()V
      //   298: iload_2
      //   299: istore_1
      //   300: new 154	java/io/OutputStreamWriter
      //   303: dup
      //   304: aload 6
      //   306: invokevirtual 158	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   309: invokespecial 161	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   312: astore 8
      //   314: iload_2
      //   315: istore_1
      //   316: aload 8
      //   318: aload 7
      //   320: invokevirtual 164	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   323: iload_2
      //   324: istore_1
      //   325: aload 8
      //   327: invokevirtual 167	java/io/OutputStreamWriter:close	()V
      //   330: iload_2
      //   331: istore_3
      //   332: iload_2
      //   333: istore 4
      //   335: iload_2
      //   336: istore_1
      //   337: invokestatic 40	abh:a	()V
      //   340: iload_2
      //   341: istore_3
      //   342: iload_2
      //   343: istore 4
      //   345: iload_2
      //   346: istore_1
      //   347: new 169	java/lang/StringBuffer
      //   350: dup
      //   351: ldc 83
      //   353: invokespecial 170	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   356: astore 7
      //   358: iload_2
      //   359: istore_3
      //   360: iload_2
      //   361: istore 4
      //   363: iload_2
      //   364: istore_1
      //   365: new 172	java/io/BufferedReader
      //   368: dup
      //   369: new 174	java/io/InputStreamReader
      //   372: dup
      //   373: aload 6
      //   375: invokevirtual 178	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   378: invokespecial 181	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   381: invokespecial 184	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   384: astore 6
      //   386: iload_2
      //   387: istore_3
      //   388: iload_2
      //   389: istore 4
      //   391: iload_2
      //   392: istore_1
      //   393: aload 6
      //   395: invokevirtual 187	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   398: astore 8
      //   400: aload 8
      //   402: ifnull +68 -> 470
      //   405: iload_2
      //   406: istore_3
      //   407: iload_2
      //   408: istore 4
      //   410: iload_2
      //   411: istore_1
      //   412: aload 7
      //   414: aload 8
      //   416: invokevirtual 190	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   419: pop
      //   420: goto -34 -> 386
      //   423: astore 6
      //   425: iload_3
      //   426: istore_1
      //   427: aload 6
      //   429: invokevirtual 194	java/lang/Object:getClass	()Ljava/lang/Class;
      //   432: ldc -60
      //   434: invokevirtual 200	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   437: ifeq +454 -> 891
      //   440: iload_3
      //   441: istore_1
      //   442: new 48	java/lang/StringBuilder
      //   445: dup
      //   446: ldc -54
      //   448: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   451: aload 6
      //   453: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   456: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   459: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   462: iload_3
      //   463: istore_1
      //   464: aload 6
      //   466: invokestatic 210	aax:a	(Ljava/lang/Exception;)V
      //   469: return
      //   470: iload_2
      //   471: istore_3
      //   472: iload_2
      //   473: istore 4
      //   475: iload_2
      //   476: istore_1
      //   477: aload 7
      //   479: invokevirtual 211	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   482: astore 6
      //   484: iload_2
      //   485: istore_3
      //   486: iload_2
      //   487: istore 4
      //   489: iload_2
      //   490: istore_1
      //   491: invokestatic 40	abh:a	()V
      //   494: iload_2
      //   495: istore_3
      //   496: iload_2
      //   497: istore 4
      //   499: iload_2
      //   500: istore_1
      //   501: new 121	org/json/JSONObject
      //   504: dup
      //   505: aload 6
      //   507: invokespecial 212	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   510: astore 6
      //   512: aload 6
      //   514: ifnull +445 -> 959
      //   517: iload_2
      //   518: istore_3
      //   519: iload_2
      //   520: istore 4
      //   522: iload_2
      //   523: istore_1
      //   524: new 48	java/lang/StringBuilder
      //   527: dup
      //   528: ldc -42
      //   530: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   533: aload 6
      //   535: invokevirtual 152	org/json/JSONObject:toString	()Ljava/lang/String;
      //   538: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   541: pop
      //   542: iload_2
      //   543: istore_3
      //   544: iload_2
      //   545: istore 4
      //   547: iload_2
      //   548: istore_1
      //   549: invokestatic 40	abh:a	()V
      //   552: iload_2
      //   553: istore_3
      //   554: iload_2
      //   555: istore 4
      //   557: iload_2
      //   558: istore_1
      //   559: aload 6
      //   561: ldc -40
      //   563: invokevirtual 220	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   566: astore 6
      //   568: iload_2
      //   569: istore_3
      //   570: iload_2
      //   571: istore 4
      //   573: iload_2
      //   574: istore_1
      //   575: new 48	java/lang/StringBuilder
      //   578: dup
      //   579: ldc -34
      //   581: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   584: aload 6
      //   586: invokevirtual 152	org/json/JSONObject:toString	()Ljava/lang/String;
      //   589: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   592: pop
      //   593: iload_2
      //   594: istore_3
      //   595: iload_2
      //   596: istore 4
      //   598: iload_2
      //   599: istore_1
      //   600: invokestatic 40	abh:a	()V
      //   603: aload 6
      //   605: ifnull +354 -> 959
      //   608: ldc 83
      //   610: astore 7
      //   612: iload_2
      //   613: istore_3
      //   614: iload_2
      //   615: istore 4
      //   617: iload_2
      //   618: istore_1
      //   619: aload 6
      //   621: ldc -32
      //   623: invokevirtual 226	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   626: astore 8
      //   628: aload 8
      //   630: astore 7
      //   632: iload_2
      //   633: istore_3
      //   634: iload_2
      //   635: istore 4
      //   637: iload_2
      //   638: istore_1
      //   639: iload_2
      //   640: istore 5
      //   642: aload 6
      //   644: ldc -28
      //   646: invokevirtual 232	org/json/JSONObject:getInt	(Ljava/lang/String;)I
      //   649: istore_2
      //   650: iload_2
      //   651: istore_1
      //   652: iload_2
      //   653: ifge +51 -> 704
      //   656: iload_2
      //   657: istore_3
      //   658: iload_2
      //   659: istore 4
      //   661: iload_2
      //   662: istore_1
      //   663: iload_2
      //   664: istore 5
      //   666: invokestatic 235	aax:x	()Landroid/content/SharedPreferences;
      //   669: invokeinterface 239 1 0
      //   674: ldc -15
      //   676: aload 7
      //   678: invokeinterface 247 3 0
      //   683: invokeinterface 250 1 0
      //   688: iload_2
      //   689: istore_3
      //   690: iload_2
      //   691: istore 4
      //   693: iload_2
      //   694: istore_1
      //   695: iload_2
      //   696: istore 5
      //   698: invokestatic 254	aax:y	()Landroid/os/Handler;
      //   701: pop
      //   702: iload_2
      //   703: istore_1
      //   704: iload_1
      //   705: ifle -236 -> 469
      //   708: iload_1
      //   709: invokestatic 256	aax:a	(I)V
      //   712: return
      //   713: astore 6
      //   715: iload_2
      //   716: istore_3
      //   717: iload_2
      //   718: istore 4
      //   720: iload_2
      //   721: istore_1
      //   722: new 48	java/lang/StringBuilder
      //   725: dup
      //   726: ldc_w 258
      //   729: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   732: aload 6
      //   734: invokevirtual 259	org/json/JSONException:toString	()Ljava/lang/String;
      //   737: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   740: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   743: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   746: aconst_null
      //   747: astore 6
      //   749: goto -237 -> 512
      //   752: iload_2
      //   753: istore_3
      //   754: iload_2
      //   755: istore 4
      //   757: iload_2
      //   758: istore_1
      //   759: invokestatic 40	abh:a	()V
      //   762: goto -159 -> 603
      //   765: astore 6
      //   767: iload 4
      //   769: istore_1
      //   770: new 48	java/lang/StringBuilder
      //   773: dup
      //   774: ldc_w 261
      //   777: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   780: aload 6
      //   782: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   785: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   788: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   791: return
      //   792: astore 6
      //   794: aload 6
      //   796: invokevirtual 194	java/lang/Object:getClass	()Ljava/lang/Class;
      //   799: ldc -60
      //   801: invokevirtual 200	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   804: ifeq +111 -> 915
      //   807: new 48	java/lang/StringBuilder
      //   810: dup
      //   811: ldc -54
      //   813: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   816: aload 6
      //   818: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   821: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   824: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   827: aload 6
      //   829: invokestatic 210	aax:a	(Ljava/lang/Exception;)V
      //   832: return
      //   833: astore 8
      //   835: iload_2
      //   836: istore_3
      //   837: iload_2
      //   838: istore 4
      //   840: iload_2
      //   841: istore_1
      //   842: ldc_w 263
      //   845: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   848: goto -216 -> 632
      //   851: astore 6
      //   853: new 48	java/lang/StringBuilder
      //   856: dup
      //   857: ldc_w 265
      //   860: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   863: aload 6
      //   865: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   868: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   871: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   874: goto -170 -> 704
      //   877: astore 6
      //   879: iload 5
      //   881: istore_1
      //   882: ldc_w 267
      //   885: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   888: goto -184 -> 704
      //   891: iload_3
      //   892: istore_1
      //   893: new 48	java/lang/StringBuilder
      //   896: dup
      //   897: ldc_w 269
      //   900: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   903: aload 6
      //   905: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   908: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   911: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   914: return
      //   915: new 48	java/lang/StringBuilder
      //   918: dup
      //   919: ldc_w 269
      //   922: invokespecial 53	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   925: aload 6
      //   927: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   930: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   933: invokestatic 207	abh:a	(Ljava/lang/String;)V
      //   936: return
      //   937: astore 6
      //   939: goto -86 -> 853
      //   942: astore 6
      //   944: goto -174 -> 770
      //   947: astore 6
      //   949: iload_1
      //   950: istore_3
      //   951: goto -526 -> 425
      //   954: astore 7
      //   956: goto -204 -> 752
      //   959: bipush 60
      //   961: istore_1
      //   962: goto -74 -> 888
      //   965: astore 6
      //   967: aconst_null
      //   968: astore 6
      //   970: goto -218 -> 752
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	973	0	this	17
      //   4	958	1	i	int
      //   2	839	2	j	int
      //   331	620	3	k	int
      //   333	506	4	m	int
      //   640	240	5	n	int
      //   104	290	6	localObject1	Object
      //   423	42	6	localIOException1	IOException
      //   482	161	6	localObject2	Object
      //   713	20	6	localJSONException1	JSONException
      //   747	1	6	localObject3	Object
      //   765	16	6	localOutOfMemoryError1	OutOfMemoryError
      //   792	36	6	localIOException2	IOException
      //   851	13	6	localException1	Exception
      //   877	49	6	localJSONException2	JSONException
      //   937	1	6	localException2	Exception
      //   942	1	6	localOutOfMemoryError2	OutOfMemoryError
      //   947	1	6	localIOException3	IOException
      //   965	1	6	localJSONException3	JSONException
      //   968	1	6	localObject4	Object
      //   199	478	7	localObject5	Object
      //   954	1	7	localJSONException4	JSONException
      //   312	317	8	localObject6	Object
      //   833	1	8	localJSONException5	JSONException
      // Exception table:
      //   from	to	target	type
      //   337	340	423	java/io/IOException
      //   347	358	423	java/io/IOException
      //   365	386	423	java/io/IOException
      //   393	400	423	java/io/IOException
      //   412	420	423	java/io/IOException
      //   477	484	423	java/io/IOException
      //   491	494	423	java/io/IOException
      //   501	512	423	java/io/IOException
      //   524	542	423	java/io/IOException
      //   549	552	423	java/io/IOException
      //   559	568	423	java/io/IOException
      //   575	593	423	java/io/IOException
      //   600	603	423	java/io/IOException
      //   619	628	423	java/io/IOException
      //   642	650	423	java/io/IOException
      //   666	688	423	java/io/IOException
      //   698	702	423	java/io/IOException
      //   722	746	423	java/io/IOException
      //   759	762	423	java/io/IOException
      //   842	848	423	java/io/IOException
      //   501	512	713	org/json/JSONException
      //   337	340	765	java/lang/OutOfMemoryError
      //   347	358	765	java/lang/OutOfMemoryError
      //   365	386	765	java/lang/OutOfMemoryError
      //   393	400	765	java/lang/OutOfMemoryError
      //   412	420	765	java/lang/OutOfMemoryError
      //   477	484	765	java/lang/OutOfMemoryError
      //   491	494	765	java/lang/OutOfMemoryError
      //   501	512	765	java/lang/OutOfMemoryError
      //   524	542	765	java/lang/OutOfMemoryError
      //   549	552	765	java/lang/OutOfMemoryError
      //   559	568	765	java/lang/OutOfMemoryError
      //   575	593	765	java/lang/OutOfMemoryError
      //   600	603	765	java/lang/OutOfMemoryError
      //   619	628	765	java/lang/OutOfMemoryError
      //   642	650	765	java/lang/OutOfMemoryError
      //   666	688	765	java/lang/OutOfMemoryError
      //   698	702	765	java/lang/OutOfMemoryError
      //   722	746	765	java/lang/OutOfMemoryError
      //   759	762	765	java/lang/OutOfMemoryError
      //   842	848	765	java/lang/OutOfMemoryError
      //   5	11	792	java/io/IOException
      //   13	25	792	java/io/IOException
      //   27	30	792	java/io/IOException
      //   32	38	792	java/io/IOException
      //   40	61	792	java/io/IOException
      //   63	66	792	java/io/IOException
      //   68	106	792	java/io/IOException
      //   108	127	792	java/io/IOException
      //   129	138	792	java/io/IOException
      //   140	147	792	java/io/IOException
      //   149	157	792	java/io/IOException
      //   159	167	792	java/io/IOException
      //   169	175	792	java/io/IOException
      //   177	183	792	java/io/IOException
      //   185	190	792	java/io/IOException
      //   192	201	792	java/io/IOException
      //   203	213	792	java/io/IOException
      //   215	226	792	java/io/IOException
      //   228	239	792	java/io/IOException
      //   241	267	792	java/io/IOException
      //   269	279	792	java/io/IOException
      //   281	288	792	java/io/IOException
      //   290	293	792	java/io/IOException
      //   295	298	792	java/io/IOException
      //   300	314	792	java/io/IOException
      //   316	323	792	java/io/IOException
      //   325	330	792	java/io/IOException
      //   427	440	792	java/io/IOException
      //   442	462	792	java/io/IOException
      //   464	469	792	java/io/IOException
      //   770	791	792	java/io/IOException
      //   893	914	792	java/io/IOException
      //   619	628	833	org/json/JSONException
      //   5	11	851	java/lang/Exception
      //   13	25	851	java/lang/Exception
      //   27	30	851	java/lang/Exception
      //   32	38	851	java/lang/Exception
      //   40	61	851	java/lang/Exception
      //   63	66	851	java/lang/Exception
      //   68	106	851	java/lang/Exception
      //   108	127	851	java/lang/Exception
      //   129	138	851	java/lang/Exception
      //   140	147	851	java/lang/Exception
      //   149	157	851	java/lang/Exception
      //   159	167	851	java/lang/Exception
      //   169	175	851	java/lang/Exception
      //   177	183	851	java/lang/Exception
      //   185	190	851	java/lang/Exception
      //   192	201	851	java/lang/Exception
      //   203	213	851	java/lang/Exception
      //   215	226	851	java/lang/Exception
      //   228	239	851	java/lang/Exception
      //   241	267	851	java/lang/Exception
      //   269	279	851	java/lang/Exception
      //   281	288	851	java/lang/Exception
      //   290	293	851	java/lang/Exception
      //   295	298	851	java/lang/Exception
      //   300	314	851	java/lang/Exception
      //   316	323	851	java/lang/Exception
      //   325	330	851	java/lang/Exception
      //   337	340	851	java/lang/Exception
      //   347	358	851	java/lang/Exception
      //   365	386	851	java/lang/Exception
      //   393	400	851	java/lang/Exception
      //   412	420	851	java/lang/Exception
      //   427	440	851	java/lang/Exception
      //   442	462	851	java/lang/Exception
      //   464	469	851	java/lang/Exception
      //   477	484	851	java/lang/Exception
      //   491	494	851	java/lang/Exception
      //   501	512	851	java/lang/Exception
      //   524	542	851	java/lang/Exception
      //   549	552	851	java/lang/Exception
      //   559	568	851	java/lang/Exception
      //   575	593	851	java/lang/Exception
      //   600	603	851	java/lang/Exception
      //   619	628	851	java/lang/Exception
      //   642	650	851	java/lang/Exception
      //   666	688	851	java/lang/Exception
      //   698	702	851	java/lang/Exception
      //   722	746	851	java/lang/Exception
      //   759	762	851	java/lang/Exception
      //   770	791	851	java/lang/Exception
      //   842	848	851	java/lang/Exception
      //   893	914	851	java/lang/Exception
      //   642	650	877	org/json/JSONException
      //   666	688	877	org/json/JSONException
      //   698	702	877	org/json/JSONException
      //   882	888	937	java/lang/Exception
      //   882	888	942	java/lang/OutOfMemoryError
      //   882	888	947	java/io/IOException
      //   575	593	954	org/json/JSONException
      //   600	603	954	org/json/JSONException
      //   559	568	965	org/json/JSONException
    }
  };
  protected static Context b;
  public static boolean d;
  protected static Handler e = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      if (aax.Q()) {
        abh.a("The library was not initialized properly or we cannot connect to our servers. Cannot send location udpate");
      }
      do
      {
        for (;;)
        {
          return;
          abh.a();
          try
          {
            paramAnonymousMessage = new JSONObject();
            JSONObject localJSONObject1 = new JSONObject();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("lat", aax.c().getString("kochava_lat", ""));
            localJSONObject2.put("lon", aax.c().getString("kochava_lon", ""));
            localJSONObject2.put("acc", aax.c().getString("kochava_accuracy", ""));
            localJSONObject1.put("location", localJSONObject2);
            paramAnonymousMessage.put("action", "update");
            paramAnonymousMessage.put("kochava_device_id", aax.w());
            paramAnonymousMessage.put("kochava_app_id", aax.v());
            paramAnonymousMessage.put("sdk_version", "Android20160427" + aax.a);
            paramAnonymousMessage.put("sdk_protocol", "4");
            paramAnonymousMessage.put("data", localJSONObject1);
            new StringBuilder("Location update: ").append(paramAnonymousMessage);
            abh.a();
            int i = aax.D().a(paramAnonymousMessage, false, true);
            aax.a(System.currentTimeMillis());
            long l = aax.c().getLong("kochava_loc_timestamp", 0L);
            aax.c().edit().putLong("kochava_old_loc_timestamp", l).apply();
            if (i >= 50)
            {
              aax.a();
              return;
            }
          }
          catch (JSONException paramAnonymousMessage) {}
        }
      } while (!abd.a);
      paramAnonymousMessage.printStackTrace();
    }
  };
  private static Map<String, String> f;
  private static JSONObject g;
  private static String h = "";
  private static boolean i;
  private static int j;
  private static boolean k;
  private static boolean l;
  private static final HashMap<Integer, Integer> m;
  private static String s;
  private static String x;
  private static String y;
  private static String z;
  private boolean C = false;
  private JSONObject L;
  private JSONObject M;
  private boolean O = true;
  private Timer Q;
  private Timer R;
  private Timer V;
  private JSONObject aa;
  private JSONObject ab;
  private Runnable aj = new Runnable()
  {
    /* Error */
    @SuppressLint({"NewApi"})
    public final void run()
    {
      // Byte code:
      //   0: invokestatic 36	abh:a	()V
      //   3: invokestatic 41	android/os/Looper:prepare	()V
      //   6: lconst_0
      //   7: lstore 5
      //   9: invokestatic 47	java/lang/System:currentTimeMillis	()J
      //   12: lstore 7
      //   14: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   17: ldc 53
      //   19: invokestatic 47	java/lang/System:currentTimeMillis	()J
      //   22: invokeinterface 59 4 0
      //   27: lstore 9
      //   29: lload 7
      //   31: lload 9
      //   33: lsub
      //   34: lstore 5
      //   36: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   39: ldc 61
      //   41: invokeinterface 65 2 0
      //   46: ifeq +3770 -> 3816
      //   49: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   52: ldc 61
      //   54: ldc 67
      //   56: invokeinterface 71 3 0
      //   61: aload_0
      //   62: getfield 14	aax$16:a	Laax;
      //   65: invokestatic 74	aax:a	(Laax;)Ljava/lang/String;
      //   68: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   71: ifne +3745 -> 3816
      //   74: new 82	java/lang/StringBuilder
      //   77: dup
      //   78: ldc 84
      //   80: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   83: aload_0
      //   84: getfield 14	aax$16:a	Laax;
      //   87: invokestatic 74	aax:a	(Laax;)Ljava/lang/String;
      //   90: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   93: ldc 93
      //   95: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   98: pop
      //   99: invokestatic 36	abh:a	()V
      //   102: iconst_1
      //   103: istore_1
      //   104: iload_1
      //   105: ifne +29 -> 134
      //   108: lload 5
      //   110: lconst_0
      //   111: lcmp
      //   112: ifle +22 -> 134
      //   115: lload 5
      //   117: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   120: ldc 95
      //   122: ldc2_w 96
      //   125: invokeinterface 59 4 0
      //   130: lcmp
      //   131: ifle +3309 -> 3440
      //   134: aconst_null
      //   135: astore 12
      //   137: iconst_0
      //   138: istore_1
      //   139: new 82	java/lang/StringBuilder
      //   142: dup
      //   143: ldc 99
      //   145: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   148: aload_0
      //   149: getfield 14	aax$16:a	Laax;
      //   152: getfield 102	aax:c	Lorg/json/JSONObject;
      //   155: invokevirtual 108	org/json/JSONObject:toString	()Ljava/lang/String;
      //   158: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   161: pop
      //   162: invokestatic 36	abh:a	()V
      //   165: invokestatic 111	aax:d	()Ljava/lang/String;
      //   168: ifnull +15 -> 183
      //   171: invokestatic 111	aax:d	()Ljava/lang/String;
      //   174: invokevirtual 114	java/lang/String:trim	()Ljava/lang/String;
      //   177: invokevirtual 118	java/lang/String:isEmpty	()Z
      //   180: ifeq +12 -> 192
      //   183: invokestatic 36	abh:a	()V
      //   186: ldc 120
      //   188: invokestatic 124	aax:b	(Ljava/lang/String;)Ljava/lang/String;
      //   191: pop
      //   192: new 82	java/lang/StringBuilder
      //   195: dup
      //   196: ldc 126
      //   198: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   201: invokestatic 111	aax:d	()Ljava/lang/String;
      //   204: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   207: ldc -128
      //   209: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   212: pop
      //   213: invokestatic 36	abh:a	()V
      //   216: new 130	java/net/URL
      //   219: dup
      //   220: new 82	java/lang/StringBuilder
      //   223: dup
      //   224: ldc -124
      //   226: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   229: invokestatic 111	aax:d	()Ljava/lang/String;
      //   232: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   235: ldc -128
      //   237: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   240: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   243: invokespecial 134	java/net/URL:<init>	(Ljava/lang/String;)V
      //   246: invokevirtual 138	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   249: checkcast 140	javax/net/ssl/HttpsURLConnection
      //   252: astore 13
      //   254: aload 13
      //   256: ldc -114
      //   258: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   261: ldc -112
      //   263: ldc 67
      //   265: invokeinterface 71 3 0
      //   270: invokevirtual 148	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   273: aload 13
      //   275: ldc -106
      //   277: ldc -104
      //   279: invokevirtual 148	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   282: aload 13
      //   284: ldc -102
      //   286: invokevirtual 157	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   289: aload 13
      //   291: sipush 30000
      //   294: invokevirtual 161	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
      //   297: aload 13
      //   299: sipush 30000
      //   302: invokevirtual 164	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
      //   305: aload 13
      //   307: iconst_1
      //   308: invokevirtual 168	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
      //   311: aload 13
      //   313: iconst_1
      //   314: invokevirtual 171	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
      //   317: aload 13
      //   319: invokevirtual 174	javax/net/ssl/HttpsURLConnection:connect	()V
      //   322: aload_0
      //   323: getfield 14	aax$16:a	Laax;
      //   326: getfield 102	aax:c	Lorg/json/JSONObject;
      //   329: invokevirtual 108	org/json/JSONObject:toString	()Ljava/lang/String;
      //   332: astore 14
      //   334: invokestatic 36	abh:a	()V
      //   337: invokestatic 36	abh:a	()V
      //   340: new 176	java/io/OutputStreamWriter
      //   343: dup
      //   344: aload 13
      //   346: invokevirtual 180	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   349: invokespecial 183	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
      //   352: astore 15
      //   354: aload 15
      //   356: aload 14
      //   358: invokevirtual 186	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
      //   361: aload 15
      //   363: invokevirtual 189	java/io/OutputStreamWriter:close	()V
      //   366: invokestatic 36	abh:a	()V
      //   369: new 191	java/lang/StringBuffer
      //   372: dup
      //   373: ldc 67
      //   375: invokespecial 192	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
      //   378: astore 14
      //   380: new 194	java/io/BufferedReader
      //   383: dup
      //   384: new 196	java/io/InputStreamReader
      //   387: dup
      //   388: aload 13
      //   390: invokevirtual 200	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
      //   393: invokespecial 203	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   396: invokespecial 206	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   399: astore 13
      //   401: aload 13
      //   403: invokevirtual 209	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   406: astore 15
      //   408: aload 15
      //   410: ifnull +81 -> 491
      //   413: aload 14
      //   415: aload 15
      //   417: invokevirtual 212	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   420: pop
      //   421: goto -20 -> 401
      //   424: astore 12
      //   426: ldc -42
      //   428: aload 12
      //   430: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   433: invokevirtual 224	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
      //   436: ifeq +2982 -> 3418
      //   439: new 82	java/lang/StringBuilder
      //   442: dup
      //   443: ldc -30
      //   445: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   448: aload 12
      //   450: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   453: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   456: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   459: aload 12
      //   461: invokestatic 234	aax:a	(Ljava/lang/Exception;)V
      //   464: return
      //   465: astore 12
      //   467: new 82	java/lang/StringBuilder
      //   470: dup
      //   471: ldc -20
      //   473: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   476: aload 12
      //   478: invokevirtual 237	java/lang/Exception:toString	()Ljava/lang/String;
      //   481: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   484: pop
      //   485: invokestatic 36	abh:a	()V
      //   488: goto -452 -> 36
      //   491: aload 14
      //   493: invokevirtual 238	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   496: astore 13
      //   498: invokestatic 36	abh:a	()V
      //   501: new 104	org/json/JSONObject
      //   504: dup
      //   505: aload 13
      //   507: invokespecial 239	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   510: astore 13
      //   512: aload 13
      //   514: astore 12
      //   516: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   519: ldc -15
      //   521: ldc 67
      //   523: invokeinterface 71 3 0
      //   528: ldc -13
      //   530: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   533: istore 11
      //   535: iload 11
      //   537: ifne +3276 -> 3813
      //   540: aload 12
      //   542: ifnull +3271 -> 3813
      //   545: aload 12
      //   547: ldc -11
      //   549: invokevirtual 249	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   552: astore 13
      //   554: iconst_0
      //   555: istore_2
      //   556: iload_2
      //   557: aload 13
      //   559: invokevirtual 255	org/json/JSONArray:length	()I
      //   562: if_icmpge +3246 -> 3808
      //   565: ldc_w 257
      //   568: aload 13
      //   570: iload_2
      //   571: invokevirtual 260	org/json/JSONArray:getString	(I)Ljava/lang/String;
      //   574: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   577: ifeq +1062 -> 1639
      //   580: iconst_1
      //   581: istore_3
      //   582: iload_1
      //   583: istore_2
      //   584: iload_1
      //   585: iconst_4
      //   586: if_icmpge +7 -> 593
      //   589: iload_1
      //   590: iconst_1
      //   591: iadd
      //   592: istore_2
      //   593: new 82	java/lang/StringBuilder
      //   596: dup
      //   597: ldc_w 262
      //   600: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   603: invokestatic 266	aax:e	()Ljava/util/HashMap;
      //   606: iload_2
      //   607: invokestatic 272	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   610: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   613: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   616: ldc_w 280
      //   619: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   622: pop
      //   623: invokestatic 36	abh:a	()V
      //   626: invokestatic 266	aax:e	()Ljava/util/HashMap;
      //   629: iload_2
      //   630: invokestatic 272	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   633: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   636: checkcast 268	java/lang/Integer
      //   639: invokevirtual 283	java/lang/Integer:intValue	()I
      //   642: i2l
      //   643: invokestatic 289	java/lang/Thread:sleep	(J)V
      //   646: iload_2
      //   647: istore_1
      //   648: iload_3
      //   649: istore_2
      //   650: iload_2
      //   651: ifne +5 -> 656
      //   654: iconst_0
      //   655: istore_1
      //   656: iload_1
      //   657: ifne +3148 -> 3805
      //   660: aload 12
      //   662: ifnull +828 -> 1490
      //   665: new 82	java/lang/StringBuilder
      //   668: dup
      //   669: ldc_w 291
      //   672: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   675: aload 12
      //   677: invokevirtual 108	org/json/JSONObject:toString	()Ljava/lang/String;
      //   680: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   683: pop
      //   684: invokestatic 36	abh:a	()V
      //   687: aconst_null
      //   688: astore 13
      //   690: aload 12
      //   692: ldc_w 293
      //   695: invokevirtual 297	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   698: astore 14
      //   700: aload 14
      //   702: astore 13
      //   704: new 82	java/lang/StringBuilder
      //   707: dup
      //   708: ldc_w 299
      //   711: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   714: aload 14
      //   716: invokevirtual 108	org/json/JSONObject:toString	()Ljava/lang/String;
      //   719: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   722: pop
      //   723: aload 14
      //   725: astore 13
      //   727: invokestatic 36	abh:a	()V
      //   730: aload 14
      //   732: astore 13
      //   734: aload 13
      //   736: ifnull +616 -> 1352
      //   739: aload 13
      //   741: ldc_w 301
      //   744: invokevirtual 303	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   747: astore 14
      //   749: new 82	java/lang/StringBuilder
      //   752: dup
      //   753: ldc_w 305
      //   756: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   759: aload 14
      //   761: invokevirtual 306	java/lang/String:toString	()Ljava/lang/String;
      //   764: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   767: pop
      //   768: invokestatic 36	abh:a	()V
      //   771: aload 14
      //   773: invokestatic 308	aax:c	(Ljava/lang/String;)Ljava/lang/String;
      //   776: pop
      //   777: aload 13
      //   779: ldc_w 310
      //   782: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   785: ldc_w 315
      //   788: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   791: ifeq +7 -> 798
      //   794: invokestatic 319	aax:f	()Z
      //   797: pop
      //   798: aload 13
      //   800: ldc_w 321
      //   803: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   806: checkcast 76	java/lang/String
      //   809: invokevirtual 324	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   812: astore 14
      //   814: invokestatic 36	abh:a	()V
      //   817: aload 14
      //   819: invokestatic 326	aax:d	(Ljava/lang/String;)V
      //   822: aload 13
      //   824: ldc_w 328
      //   827: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   830: ldc -13
      //   832: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   835: ifeq +43 -> 878
      //   838: invokestatic 36	abh:a	()V
      //   841: getstatic 331	aax:b	Landroid/content/Context;
      //   844: ldc_w 333
      //   847: iconst_0
      //   848: invokevirtual 339	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   851: invokestatic 342	aax:a	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
      //   854: pop
      //   855: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   858: invokeinterface 346 1 0
      //   863: ldc -15
      //   865: ldc_w 348
      //   868: invokeinterface 354 3 0
      //   873: invokeinterface 357 1 0
      //   878: aload 13
      //   880: ldc_w 359
      //   883: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   886: checkcast 268	java/lang/Integer
      //   889: invokevirtual 283	java/lang/Integer:intValue	()I
      //   892: invokestatic 362	aax:b	(I)I
      //   895: pop
      //   896: invokestatic 365	aax:g	()I
      //   899: ifge +825 -> 1724
      //   902: new 82	java/lang/StringBuilder
      //   905: dup
      //   906: ldc_w 367
      //   909: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   912: invokestatic 365	aax:g	()I
      //   915: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   918: ldc_w 372
      //   921: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   924: pop
      //   925: invokestatic 36	abh:a	()V
      //   928: iconst_0
      //   929: invokestatic 362	aax:b	(I)I
      //   932: pop
      //   933: invokestatic 375	aax:h	()Z
      //   936: ifne +83 -> 1019
      //   939: aload 13
      //   941: ldc_w 377
      //   944: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   947: ifnull +72 -> 1019
      //   950: aload 13
      //   952: ldc_w 377
      //   955: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   958: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   961: ldc_w 268
      //   964: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   967: ifeq +52 -> 1019
      //   970: aload 13
      //   972: ldc_w 377
      //   975: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   978: checkcast 268	java/lang/Integer
      //   981: invokevirtual 283	java/lang/Integer:intValue	()I
      //   984: istore_1
      //   985: iload_1
      //   986: bipush 60
      //   988: if_icmpge +802 -> 1790
      //   991: new 82	java/lang/StringBuilder
      //   994: dup
      //   995: ldc_w 379
      //   998: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1001: iload_1
      //   1002: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1005: ldc_w 381
      //   1008: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1011: pop
      //   1012: invokestatic 36	abh:a	()V
      //   1015: invokestatic 384	aax:i	()Z
      //   1018: pop
      //   1019: aload 13
      //   1021: ldc 95
      //   1023: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1026: ifnull +87 -> 1113
      //   1029: aload 13
      //   1031: ldc 95
      //   1033: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1036: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1039: ldc_w 268
      //   1042: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1045: ifeq +68 -> 1113
      //   1048: aload 13
      //   1050: ldc 95
      //   1052: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1055: checkcast 268	java/lang/Integer
      //   1058: invokevirtual 283	java/lang/Integer:intValue	()I
      //   1061: istore_1
      //   1062: iload_1
      //   1063: ifge +804 -> 1867
      //   1066: new 82	java/lang/StringBuilder
      //   1069: dup
      //   1070: ldc_w 386
      //   1073: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1076: iload_1
      //   1077: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1080: ldc_w 388
      //   1083: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1086: pop
      //   1087: invokestatic 36	abh:a	()V
      //   1090: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   1093: invokeinterface 346 1 0
      //   1098: ldc 95
      //   1100: ldc2_w 96
      //   1103: invokeinterface 392 4 0
      //   1108: invokeinterface 357 1 0
      //   1113: aload 13
      //   1115: ldc_w 394
      //   1118: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1121: ifnull +71 -> 1192
      //   1124: aload 13
      //   1126: ldc_w 394
      //   1129: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1132: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1135: ldc_w 268
      //   1138: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1141: ifeq +51 -> 1192
      //   1144: aload 13
      //   1146: ldc_w 394
      //   1149: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1152: checkcast 268	java/lang/Integer
      //   1155: invokevirtual 283	java/lang/Integer:intValue	()I
      //   1158: istore_1
      //   1159: iload_1
      //   1160: ifgt +817 -> 1977
      //   1163: new 82	java/lang/StringBuilder
      //   1166: dup
      //   1167: ldc_w 396
      //   1170: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1173: iload_1
      //   1174: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1177: ldc_w 398
      //   1180: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1183: pop
      //   1184: invokestatic 36	abh:a	()V
      //   1187: iconst_1
      //   1188: invokestatic 400	aax:d	(I)I
      //   1191: pop
      //   1192: aload 13
      //   1194: ldc_w 402
      //   1197: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1200: checkcast 268	java/lang/Integer
      //   1203: invokevirtual 283	java/lang/Integer:intValue	()I
      //   1206: istore_2
      //   1207: iload_2
      //   1208: bipush 10
      //   1210: if_icmpge +838 -> 2048
      //   1213: new 82	java/lang/StringBuilder
      //   1216: dup
      //   1217: ldc_w 404
      //   1220: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1223: iload_2
      //   1224: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1227: ldc_w 406
      //   1230: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1233: pop
      //   1234: invokestatic 36	abh:a	()V
      //   1237: bipush 10
      //   1239: istore_1
      //   1240: invokestatic 36	abh:a	()V
      //   1243: iload_1
      //   1244: putstatic 411	abe:a	I
      //   1247: aload 13
      //   1249: ldc_w 413
      //   1252: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1255: checkcast 268	java/lang/Integer
      //   1258: invokevirtual 283	java/lang/Integer:intValue	()I
      //   1261: istore_2
      //   1262: iload_2
      //   1263: iconst_3
      //   1264: if_icmpge +824 -> 2088
      //   1267: new 82	java/lang/StringBuilder
      //   1270: dup
      //   1271: ldc_w 415
      //   1274: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1277: iload_2
      //   1278: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1281: ldc_w 417
      //   1284: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1287: pop
      //   1288: invokestatic 36	abh:a	()V
      //   1291: iconst_3
      //   1292: istore_1
      //   1293: invokestatic 36	abh:a	()V
      //   1296: iload_1
      //   1297: putstatic 419	abe:c	I
      //   1300: aload 13
      //   1302: ldc_w 421
      //   1305: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   1308: checkcast 268	java/lang/Integer
      //   1311: invokevirtual 283	java/lang/Integer:intValue	()I
      //   1314: istore_2
      //   1315: iload_2
      //   1316: ifgt +810 -> 2126
      //   1319: new 82	java/lang/StringBuilder
      //   1322: dup
      //   1323: ldc_w 423
      //   1326: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1329: iload_2
      //   1330: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1333: ldc_w 425
      //   1336: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1339: pop
      //   1340: invokestatic 36	abh:a	()V
      //   1343: iconst_1
      //   1344: istore_1
      //   1345: invokestatic 36	abh:a	()V
      //   1348: iload_1
      //   1349: putstatic 427	abe:b	I
      //   1352: aload 12
      //   1354: ldc_w 429
      //   1357: invokevirtual 249	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   1360: astore 14
      //   1362: new 82	java/lang/StringBuilder
      //   1365: dup
      //   1366: ldc_w 431
      //   1369: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1372: aload 14
      //   1374: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   1377: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1380: pop
      //   1381: invokestatic 36	abh:a	()V
      //   1384: iconst_0
      //   1385: istore_1
      //   1386: iload_1
      //   1387: aload 14
      //   1389: invokevirtual 255	org/json/JSONArray:length	()I
      //   1392: if_icmpge +831 -> 2223
      //   1395: aload 14
      //   1397: iload_1
      //   1398: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1401: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   1404: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   1407: ldc_w 441
      //   1410: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1413: ifeq +753 -> 2166
      //   1416: invokestatic 36	abh:a	()V
      //   1419: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   1422: ldc_w 441
      //   1425: iconst_0
      //   1426: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   1429: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   1432: pop
      //   1433: iload_1
      //   1434: iconst_1
      //   1435: iadd
      //   1436: istore_1
      //   1437: goto -51 -> 1386
      //   1440: astore 13
      //   1442: new 82	java/lang/StringBuilder
      //   1445: dup
      //   1446: ldc_w 455
      //   1449: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1452: aload 13
      //   1454: invokevirtual 456	org/json/JSONException:toString	()Ljava/lang/String;
      //   1457: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1460: pop
      //   1461: invokestatic 36	abh:a	()V
      //   1464: goto -948 -> 516
      //   1467: astore 12
      //   1469: new 82	java/lang/StringBuilder
      //   1472: dup
      //   1473: ldc_w 458
      //   1476: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1479: aload 12
      //   1481: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1484: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1487: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   1490: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   1493: invokeinterface 346 1 0
      //   1498: ldc 53
      //   1500: invokestatic 47	java/lang/System:currentTimeMillis	()J
      //   1503: invokeinterface 392 4 0
      //   1508: invokeinterface 357 1 0
      //   1513: invokestatic 36	abh:a	()V
      //   1516: iconst_0
      //   1517: istore_1
      //   1518: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   1521: ldc -15
      //   1523: ldc 67
      //   1525: invokeinterface 71 3 0
      //   1530: ldc -13
      //   1532: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1535: ifne +2168 -> 3703
      //   1538: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   1541: ldc_w 460
      //   1544: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   1547: checkcast 446	java/lang/Boolean
      //   1550: invokevirtual 463	java/lang/Boolean:booleanValue	()Z
      //   1553: ifne +1919 -> 3472
      //   1556: iconst_1
      //   1557: istore_2
      //   1558: invokestatic 466	aax:s	()Z
      //   1561: istore 11
      //   1563: iload_1
      //   1564: invokestatic 365	aax:g	()I
      //   1567: if_icmpge +1947 -> 3514
      //   1570: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   1573: ldc_w 468
      //   1576: ldc_w 470
      //   1579: invokeinterface 71 3 0
      //   1584: ldc_w 470
      //   1587: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1590: ifne +1887 -> 3477
      //   1593: iconst_1
      //   1594: istore_3
      //   1595: invokestatic 473	aax:t	()Ljava/lang/String;
      //   1598: ifnull +1884 -> 3482
      //   1601: iconst_1
      //   1602: istore 4
      //   1604: iload_3
      //   1605: ifeq +12 -> 1617
      //   1608: iload_2
      //   1609: ifne +1905 -> 3514
      //   1612: iload 11
      //   1614: ifne +1900 -> 3514
      //   1617: iload_3
      //   1618: ifeq +8 -> 1626
      //   1621: iload 4
      //   1623: ifne +1891 -> 3514
      //   1626: ldc2_w 474
      //   1629: invokestatic 289	java/lang/Thread:sleep	(J)V
      //   1632: iload_1
      //   1633: iconst_1
      //   1634: iadd
      //   1635: istore_1
      //   1636: goto -73 -> 1563
      //   1639: iload_2
      //   1640: iconst_1
      //   1641: iadd
      //   1642: istore_2
      //   1643: goto -1087 -> 556
      //   1646: astore 13
      //   1648: iconst_0
      //   1649: istore_1
      //   1650: goto -994 -> 656
      //   1653: astore 14
      //   1655: invokestatic 36	abh:a	()V
      //   1658: goto -924 -> 734
      //   1661: astore 12
      //   1663: aload 12
      //   1665: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   1668: ldc -42
      //   1670: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   1673: ifeq +1723 -> 3396
      //   1676: new 82	java/lang/StringBuilder
      //   1679: dup
      //   1680: ldc -30
      //   1682: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1685: aload 12
      //   1687: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   1690: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1693: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   1696: aload 12
      //   1698: invokestatic 234	aax:a	(Ljava/lang/Exception;)V
      //   1701: return
      //   1702: astore 14
      //   1704: ldc_w 477
      //   1707: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   1710: goto -933 -> 777
      //   1713: astore 14
      //   1715: ldc_w 479
      //   1718: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   1721: goto -923 -> 798
      //   1724: invokestatic 365	aax:g	()I
      //   1727: bipush 120
      //   1729: if_icmple +38 -> 1767
      //   1732: new 82	java/lang/StringBuilder
      //   1735: dup
      //   1736: ldc_w 481
      //   1739: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1742: invokestatic 365	aax:g	()I
      //   1745: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1748: ldc_w 483
      //   1751: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1754: pop
      //   1755: invokestatic 36	abh:a	()V
      //   1758: bipush 120
      //   1760: invokestatic 362	aax:b	(I)I
      //   1763: pop
      //   1764: goto -831 -> 933
      //   1767: new 82	java/lang/StringBuilder
      //   1770: dup
      //   1771: ldc_w 485
      //   1774: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1777: invokestatic 365	aax:g	()I
      //   1780: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1783: pop
      //   1784: invokestatic 36	abh:a	()V
      //   1787: goto -854 -> 933
      //   1790: iload_1
      //   1791: ldc_w 486
      //   1794: if_icmple +37 -> 1831
      //   1797: new 82	java/lang/StringBuilder
      //   1800: dup
      //   1801: ldc_w 488
      //   1804: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1807: iload_1
      //   1808: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1811: ldc_w 490
      //   1814: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1817: pop
      //   1818: invokestatic 36	abh:a	()V
      //   1821: ldc_w 491
      //   1824: invokestatic 493	aax:c	(I)I
      //   1827: pop
      //   1828: goto -813 -> 1015
      //   1831: iload_1
      //   1832: sipush 1000
      //   1835: imul
      //   1836: invokestatic 493	aax:c	(I)I
      //   1839: pop
      //   1840: new 82	java/lang/StringBuilder
      //   1843: dup
      //   1844: ldc_w 495
      //   1847: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1850: iload_1
      //   1851: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1854: ldc_w 497
      //   1857: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1860: pop
      //   1861: invokestatic 36	abh:a	()V
      //   1864: goto -849 -> 1015
      //   1867: iload_1
      //   1868: ldc_w 486
      //   1871: if_icmple +53 -> 1924
      //   1874: new 82	java/lang/StringBuilder
      //   1877: dup
      //   1878: ldc_w 386
      //   1881: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1884: iload_1
      //   1885: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1888: ldc_w 499
      //   1891: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1894: pop
      //   1895: invokestatic 36	abh:a	()V
      //   1898: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   1901: invokeinterface 346 1 0
      //   1906: ldc 95
      //   1908: ldc2_w 500
      //   1911: invokeinterface 392 4 0
      //   1916: invokeinterface 357 1 0
      //   1921: goto -808 -> 1113
      //   1924: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   1927: invokeinterface 346 1 0
      //   1932: ldc 95
      //   1934: iload_1
      //   1935: sipush 1000
      //   1938: imul
      //   1939: i2l
      //   1940: invokeinterface 392 4 0
      //   1945: invokeinterface 357 1 0
      //   1950: new 82	java/lang/StringBuilder
      //   1953: dup
      //   1954: ldc_w 503
      //   1957: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1960: iload_1
      //   1961: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1964: ldc_w 497
      //   1967: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1970: pop
      //   1971: invokestatic 36	abh:a	()V
      //   1974: goto -861 -> 1113
      //   1977: iload_1
      //   1978: bipush 30
      //   1980: if_icmple +36 -> 2016
      //   1983: new 82	java/lang/StringBuilder
      //   1986: dup
      //   1987: ldc_w 396
      //   1990: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   1993: iload_1
      //   1994: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1997: ldc_w 505
      //   2000: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2003: pop
      //   2004: invokestatic 36	abh:a	()V
      //   2007: bipush 30
      //   2009: invokestatic 400	aax:d	(I)I
      //   2012: pop
      //   2013: goto -821 -> 1192
      //   2016: new 82	java/lang/StringBuilder
      //   2019: dup
      //   2020: ldc_w 507
      //   2023: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2026: iload_1
      //   2027: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2030: ldc_w 497
      //   2033: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2036: pop
      //   2037: invokestatic 36	abh:a	()V
      //   2040: iload_1
      //   2041: invokestatic 400	aax:d	(I)I
      //   2044: pop
      //   2045: goto -853 -> 1192
      //   2048: iload_2
      //   2049: istore_1
      //   2050: iload_2
      //   2051: sipush 5000
      //   2054: if_icmple -814 -> 1240
      //   2057: new 82	java/lang/StringBuilder
      //   2060: dup
      //   2061: ldc_w 404
      //   2064: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2067: iload_2
      //   2068: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2071: ldc_w 406
      //   2074: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2077: pop
      //   2078: invokestatic 36	abh:a	()V
      //   2081: sipush 5000
      //   2084: istore_1
      //   2085: goto -845 -> 1240
      //   2088: iload_2
      //   2089: istore_1
      //   2090: iload_2
      //   2091: bipush 60
      //   2093: if_icmple -800 -> 1293
      //   2096: new 82	java/lang/StringBuilder
      //   2099: dup
      //   2100: ldc_w 415
      //   2103: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2106: iload_2
      //   2107: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2110: ldc_w 417
      //   2113: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2116: pop
      //   2117: invokestatic 36	abh:a	()V
      //   2120: bipush 60
      //   2122: istore_1
      //   2123: goto -830 -> 1293
      //   2126: iload_2
      //   2127: istore_1
      //   2128: iload_2
      //   2129: sipush 10080
      //   2132: if_icmple -787 -> 1345
      //   2135: new 82	java/lang/StringBuilder
      //   2138: dup
      //   2139: ldc_w 423
      //   2142: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2145: iload_2
      //   2146: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2149: ldc_w 425
      //   2152: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2155: pop
      //   2156: invokestatic 36	abh:a	()V
      //   2159: sipush 10080
      //   2162: istore_1
      //   2163: goto -818 -> 1345
      //   2166: aload 14
      //   2168: iload_1
      //   2169: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2172: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2175: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2178: ldc_w 509
      //   2181: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2184: ifeq +493 -> 2677
      //   2187: invokestatic 36	abh:a	()V
      //   2190: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2193: ldc_w 509
      //   2196: iconst_0
      //   2197: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2200: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2203: pop
      //   2204: goto -771 -> 1433
      //   2207: astore 14
      //   2209: invokestatic 36	abh:a	()V
      //   2212: getstatic 514	abd:a	Z
      //   2215: ifeq +8 -> 2223
      //   2218: aload 14
      //   2220: invokevirtual 517	java/lang/Exception:printStackTrace	()V
      //   2223: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2226: ldc_w 519
      //   2229: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2232: checkcast 446	java/lang/Boolean
      //   2235: invokevirtual 463	java/lang/Boolean:booleanValue	()Z
      //   2238: istore 11
      //   2240: iload 11
      //   2242: ifeq +710 -> 2952
      //   2245: getstatic 331	aax:b	Landroid/content/Context;
      //   2248: ldc_w 521
      //   2251: invokevirtual 524	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2254: checkcast 526	android/telephony/TelephonyManager
      //   2257: invokevirtual 529	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
      //   2260: invokestatic 531	aax:e	(Ljava/lang/String;)Ljava/lang/String;
      //   2263: pop
      //   2264: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2267: ldc_w 533
      //   2270: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2273: checkcast 446	java/lang/Boolean
      //   2276: invokevirtual 463	java/lang/Boolean:booleanValue	()Z
      //   2279: istore 11
      //   2281: iload 11
      //   2283: ifeq +696 -> 2979
      //   2286: getstatic 331	aax:b	Landroid/content/Context;
      //   2289: ldc_w 535
      //   2292: invokevirtual 524	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2295: checkcast 537	android/net/wifi/WifiManager
      //   2298: invokevirtual 541	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
      //   2301: invokevirtual 546	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
      //   2304: invokestatic 548	aax:f	(Ljava/lang/String;)Ljava/lang/String;
      //   2307: pop
      //   2308: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2311: ldc_w 550
      //   2314: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2317: checkcast 446	java/lang/Boolean
      //   2320: invokevirtual 463	java/lang/Boolean:booleanValue	()Z
      //   2323: istore 11
      //   2325: iload 11
      //   2327: ifeq +704 -> 3031
      //   2330: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   2333: ldc -15
      //   2335: ldc 67
      //   2337: invokeinterface 71 3 0
      //   2342: ldc -13
      //   2344: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2347: ifne +140 -> 2487
      //   2350: getstatic 331	aax:b	Landroid/content/Context;
      //   2353: invokevirtual 554	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   2356: astore 15
      //   2358: aload 15
      //   2360: sipush 128
      //   2363: invokevirtual 560	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2366: invokeinterface 566 1 0
      //   2371: astore 16
      //   2373: aload 16
      //   2375: invokeinterface 571 1 0
      //   2380: ifeq +107 -> 2487
      //   2383: aload 16
      //   2385: invokeinterface 575 1 0
      //   2390: checkcast 577	android/content/pm/ApplicationInfo
      //   2393: astore 17
      //   2395: aload 17
      //   2397: invokestatic 580	aax:a	(Landroid/content/pm/ApplicationInfo;)Z
      //   2400: istore 11
      //   2402: iload 11
      //   2404: ifne -31 -> 2373
      //   2407: aload 15
      //   2409: aload 17
      //   2411: getfield 584	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2414: iconst_0
      //   2415: invokevirtual 588	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   2418: astore 14
      //   2420: aload 14
      //   2422: ifnull -49 -> 2373
      //   2425: invokestatic 592	aax:k	()Ljava/util/List;
      //   2428: new 594	aaz
      //   2431: dup
      //   2432: aload 14
      //   2434: getfield 597	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   2437: new 82	java/lang/StringBuilder
      //   2440: dup
      //   2441: invokespecial 598	java/lang/StringBuilder:<init>	()V
      //   2444: aload 14
      //   2446: getfield 602	android/content/pm/PackageInfo:firstInstallTime	J
      //   2449: invokevirtual 605	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2452: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2455: new 82	java/lang/StringBuilder
      //   2458: dup
      //   2459: invokespecial 598	java/lang/StringBuilder:<init>	()V
      //   2462: aload 14
      //   2464: getfield 608	android/content/pm/PackageInfo:lastUpdateTime	J
      //   2467: invokevirtual 605	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2470: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2473: invokespecial 611	aaz:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   2476: invokeinterface 614 2 0
      //   2481: pop
      //   2482: goto -109 -> 2373
      //   2485: astore 14
      //   2487: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2490: ldc_w 616
      //   2493: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   2496: checkcast 446	java/lang/Boolean
      //   2499: invokevirtual 463	java/lang/Boolean:booleanValue	()Z
      //   2502: istore 11
      //   2504: iload 11
      //   2506: ifeq +560 -> 3066
      //   2509: getstatic 331	aax:b	Landroid/content/Context;
      //   2512: ldc_w 618
      //   2515: invokevirtual 524	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   2518: checkcast 620	android/view/WindowManager
      //   2521: astore 14
      //   2523: aload_0
      //   2524: getfield 14	aax$16:a	Laax;
      //   2527: aload 14
      //   2529: invokeinterface 624 1 0
      //   2534: invokevirtual 629	android/view/Display:getHeight	()I
      //   2537: invokestatic 632	aax:a	(Laax;I)I
      //   2540: pop
      //   2541: aload_0
      //   2542: getfield 14	aax$16:a	Laax;
      //   2545: aload 14
      //   2547: invokeinterface 624 1 0
      //   2552: invokevirtual 635	android/view/Display:getWidth	()I
      //   2555: invokestatic 637	aax:b	(Laax;I)I
      //   2558: pop
      //   2559: new 82	java/lang/StringBuilder
      //   2562: dup
      //   2563: ldc_w 639
      //   2566: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2569: aload_0
      //   2570: getfield 14	aax$16:a	Laax;
      //   2573: invokestatic 642	aax:b	(Laax;)I
      //   2576: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2579: ldc_w 644
      //   2582: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2585: aload_0
      //   2586: getfield 14	aax$16:a	Laax;
      //   2589: invokestatic 646	aax:c	(Laax;)I
      //   2592: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2595: pop
      //   2596: invokestatic 36	abh:a	()V
      //   2599: aload 12
      //   2601: ldc_w 648
      //   2604: invokevirtual 249	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   2607: astore 14
      //   2609: new 82	java/lang/StringBuilder
      //   2612: dup
      //   2613: ldc_w 650
      //   2616: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2619: aload 14
      //   2621: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   2624: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2627: pop
      //   2628: invokestatic 36	abh:a	()V
      //   2631: iconst_0
      //   2632: istore_1
      //   2633: iload_1
      //   2634: aload 14
      //   2636: invokevirtual 255	org/json/JSONArray:length	()I
      //   2639: if_icmpge +490 -> 3129
      //   2642: aload 14
      //   2644: iload_1
      //   2645: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2648: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2651: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2654: ldc_w 652
      //   2657: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2660: ifeq +412 -> 3072
      //   2663: invokestatic 36	abh:a	()V
      //   2666: invokestatic 655	aax:l	()Z
      //   2669: pop
      //   2670: iload_1
      //   2671: iconst_1
      //   2672: iadd
      //   2673: istore_1
      //   2674: goto -41 -> 2633
      //   2677: aload 14
      //   2679: iload_1
      //   2680: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2683: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2686: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2689: ldc_w 460
      //   2692: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2695: ifeq +23 -> 2718
      //   2698: invokestatic 36	abh:a	()V
      //   2701: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2704: ldc_w 460
      //   2707: iconst_0
      //   2708: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2711: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2714: pop
      //   2715: goto -1282 -> 1433
      //   2718: aload 14
      //   2720: iload_1
      //   2721: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2724: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2727: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2730: ldc_w 533
      //   2733: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2736: ifeq +23 -> 2759
      //   2739: invokestatic 36	abh:a	()V
      //   2742: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2745: ldc_w 533
      //   2748: iconst_0
      //   2749: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2752: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2755: pop
      //   2756: goto -1323 -> 1433
      //   2759: aload 14
      //   2761: iload_1
      //   2762: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2765: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2768: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2771: ldc_w 519
      //   2774: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2777: ifeq +23 -> 2800
      //   2780: invokestatic 36	abh:a	()V
      //   2783: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2786: ldc_w 519
      //   2789: iconst_0
      //   2790: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2793: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2796: pop
      //   2797: goto -1364 -> 1433
      //   2800: aload 14
      //   2802: iload_1
      //   2803: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2806: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2809: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2812: ldc_w 616
      //   2815: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2818: ifeq +23 -> 2841
      //   2821: invokestatic 36	abh:a	()V
      //   2824: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2827: ldc_w 616
      //   2830: iconst_0
      //   2831: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2834: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2837: pop
      //   2838: goto -1405 -> 1433
      //   2841: aload 14
      //   2843: iload_1
      //   2844: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2847: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2850: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2853: ldc_w 550
      //   2856: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2859: ifeq +23 -> 2882
      //   2862: invokestatic 36	abh:a	()V
      //   2865: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2868: ldc_w 550
      //   2871: iconst_0
      //   2872: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2875: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2878: pop
      //   2879: goto -1446 -> 1433
      //   2882: aload 14
      //   2884: iload_1
      //   2885: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   2888: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   2891: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   2894: ldc_w 657
      //   2897: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2900: ifeq -1467 -> 1433
      //   2903: invokestatic 36	abh:a	()V
      //   2906: invokestatic 444	aax:j	()Ljava/util/HashMap;
      //   2909: ldc_w 657
      //   2912: iconst_0
      //   2913: invokestatic 449	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   2916: invokevirtual 453	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   2919: pop
      //   2920: goto -1487 -> 1433
      //   2923: astore 14
      //   2925: new 82	java/lang/StringBuilder
      //   2928: dup
      //   2929: ldc_w 659
      //   2932: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2935: aload 14
      //   2937: invokevirtual 237	java/lang/Exception:toString	()Ljava/lang/String;
      //   2940: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2943: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2946: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   2949: goto -685 -> 2264
      //   2952: invokestatic 36	abh:a	()V
      //   2955: goto -691 -> 2264
      //   2958: astore 14
      //   2960: new 82	java/lang/StringBuilder
      //   2963: dup
      //   2964: ldc_w 661
      //   2967: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2970: aload 14
      //   2972: invokevirtual 237	java/lang/Exception:toString	()Ljava/lang/String;
      //   2975: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2978: pop
      //   2979: invokestatic 36	abh:a	()V
      //   2982: goto -674 -> 2308
      //   2985: astore 14
      //   2987: new 82	java/lang/StringBuilder
      //   2990: dup
      //   2991: ldc_w 663
      //   2994: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   2997: aload 17
      //   2999: getfield 584	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   3002: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3005: ldc_w 665
      //   3008: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3011: aload 14
      //   3013: invokevirtual 666	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
      //   3016: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3019: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3022: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3025: aconst_null
      //   3026: astore 14
      //   3028: goto -608 -> 2420
      //   3031: invokestatic 36	abh:a	()V
      //   3034: goto -547 -> 2487
      //   3037: astore 14
      //   3039: new 82	java/lang/StringBuilder
      //   3042: dup
      //   3043: ldc_w 668
      //   3046: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3049: aload 14
      //   3051: invokevirtual 237	java/lang/Exception:toString	()Ljava/lang/String;
      //   3054: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3057: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3060: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3063: goto -464 -> 2599
      //   3066: invokestatic 36	abh:a	()V
      //   3069: goto -470 -> 2599
      //   3072: aload 14
      //   3074: iload_1
      //   3075: invokevirtual 435	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   3078: invokevirtual 436	java/lang/Object:toString	()Ljava/lang/String;
      //   3081: invokevirtual 439	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   3084: ldc_w 670
      //   3087: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3090: ifeq -420 -> 2670
      //   3093: invokestatic 36	abh:a	()V
      //   3096: invokestatic 673	aax:m	()Z
      //   3099: pop
      //   3100: goto -430 -> 2670
      //   3103: astore 14
      //   3105: new 82	java/lang/StringBuilder
      //   3108: dup
      //   3109: ldc_w 675
      //   3112: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3115: aload 14
      //   3117: invokevirtual 237	java/lang/Exception:toString	()Ljava/lang/String;
      //   3120: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3123: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3126: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3129: aload 12
      //   3131: ldc_w 677
      //   3134: invokevirtual 297	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3137: ldc_w 679
      //   3140: invokevirtual 297	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3143: invokestatic 682	aax:a	(Lorg/json/JSONObject;)V
      //   3146: invokestatic 685	aax:n	()Z
      //   3149: ifeq +18 -> 3167
      //   3152: invokestatic 688	aax:o	()Z
      //   3155: ifeq +12 -> 3167
      //   3158: getstatic 331	aax:b	Landroid/content/Context;
      //   3161: invokestatic 691	abe:a	(Landroid/content/Context;)Labe;
      //   3164: invokevirtual 692	abe:a	()V
      //   3167: aload 12
      //   3169: ldc_w 694
      //   3172: invokevirtual 249	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   3175: invokestatic 697	aax:a	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
      //   3178: pop
      //   3179: new 82	java/lang/StringBuilder
      //   3182: dup
      //   3183: ldc_w 699
      //   3186: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3189: invokestatic 703	aax:p	()Lorg/json/JSONArray;
      //   3192: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3195: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3198: pop
      //   3199: invokestatic 36	abh:a	()V
      //   3202: invokestatic 51	aax:c	()Landroid/content/SharedPreferences;
      //   3205: invokeinterface 346 1 0
      //   3210: ldc_w 705
      //   3213: invokestatic 703	aax:p	()Lorg/json/JSONArray;
      //   3216: invokevirtual 432	org/json/JSONArray:toString	()Ljava/lang/String;
      //   3219: invokeinterface 354 3 0
      //   3224: invokeinterface 357 1 0
      //   3229: aload 13
      //   3231: ldc_w 707
      //   3234: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3237: ifnull +86 -> 3323
      //   3240: aload 13
      //   3242: ldc_w 707
      //   3245: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3248: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3251: ldc_w 446
      //   3254: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3257: ifeq +129 -> 3386
      //   3260: aload 13
      //   3262: ldc_w 707
      //   3265: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3268: checkcast 446	java/lang/Boolean
      //   3271: invokevirtual 463	java/lang/Boolean:booleanValue	()Z
      //   3274: ifeq +112 -> 3386
      //   3277: iconst_1
      //   3278: istore_1
      //   3279: aload 13
      //   3281: ldc_w 707
      //   3284: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3287: invokevirtual 218	java/lang/Object:getClass	()Ljava/lang/Class;
      //   3290: ldc 76
      //   3292: invokevirtual 316	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   3295: ifeq +96 -> 3391
      //   3298: ldc -13
      //   3300: aload 13
      //   3302: ldc_w 707
      //   3305: invokevirtual 313	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   3308: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3311: ifeq +80 -> 3391
      //   3314: iconst_1
      //   3315: istore_2
      //   3316: goto +525 -> 3841
      //   3319: invokestatic 710	aax:q	()Z
      //   3322: pop
      //   3323: aload 12
      //   3325: ldc_w 712
      //   3328: invokevirtual 303	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   3331: astore 12
      //   3333: new 82	java/lang/StringBuilder
      //   3336: dup
      //   3337: ldc_w 714
      //   3340: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3343: aload 12
      //   3345: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3348: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3351: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3354: aload 12
      //   3356: ldc_w 716
      //   3359: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3362: ifeq -1872 -> 1490
      //   3365: invokestatic 719	aax:r	()Z
      //   3368: pop
      //   3369: return
      //   3370: astore 12
      //   3372: invokestatic 36	abh:a	()V
      //   3375: goto -1885 -> 1490
      //   3378: astore 14
      //   3380: invokestatic 36	abh:a	()V
      //   3383: goto -154 -> 3229
      //   3386: iconst_0
      //   3387: istore_1
      //   3388: goto -109 -> 3279
      //   3391: iconst_0
      //   3392: istore_2
      //   3393: goto +448 -> 3841
      //   3396: new 82	java/lang/StringBuilder
      //   3399: dup
      //   3400: ldc_w 721
      //   3403: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3406: aload 12
      //   3408: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3411: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3414: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3417: return
      //   3418: new 82	java/lang/StringBuilder
      //   3421: dup
      //   3422: ldc_w 721
      //   3425: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3428: aload 12
      //   3430: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3433: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3436: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3439: return
      //   3440: new 82	java/lang/StringBuilder
      //   3443: dup
      //   3444: ldc_w 723
      //   3447: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3450: lload 5
      //   3452: ldc2_w 474
      //   3455: ldiv
      //   3456: invokevirtual 605	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   3459: ldc_w 725
      //   3462: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3465: pop
      //   3466: invokestatic 36	abh:a	()V
      //   3469: goto -1956 -> 1513
      //   3472: iconst_0
      //   3473: istore_2
      //   3474: goto -1916 -> 1558
      //   3477: iconst_0
      //   3478: istore_3
      //   3479: goto -1884 -> 1595
      //   3482: iconst_0
      //   3483: istore 4
      //   3485: goto -1881 -> 1604
      //   3488: astore 12
      //   3490: new 82	java/lang/StringBuilder
      //   3493: dup
      //   3494: ldc_w 727
      //   3497: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3500: aload 12
      //   3502: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3505: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3508: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3511: goto -1879 -> 1632
      //   3514: new 82	java/lang/StringBuilder
      //   3517: dup
      //   3518: ldc_w 729
      //   3521: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3524: iload_1
      //   3525: invokevirtual 370	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   3528: ldc_w 497
      //   3531: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   3534: pop
      //   3535: invokestatic 36	abh:a	()V
      //   3538: iload_1
      //   3539: istore_2
      //   3540: iload_1
      //   3541: iconst_2
      //   3542: if_icmpge +96 -> 3638
      //   3545: aload_0
      //   3546: getfield 14	aax$16:a	Laax;
      //   3549: invokestatic 731	aax:d	(Laax;)Ljava/lang/String;
      //   3552: ifnull +49 -> 3601
      //   3555: iconst_1
      //   3556: istore_3
      //   3557: invokestatic 473	aax:t	()Ljava/lang/String;
      //   3560: ifnull +46 -> 3606
      //   3563: invokestatic 473	aax:t	()Ljava/lang/String;
      //   3566: invokevirtual 118	java/lang/String:isEmpty	()Z
      //   3569: ifne +37 -> 3606
      //   3572: iconst_1
      //   3573: istore 4
      //   3575: iload_1
      //   3576: istore_2
      //   3577: iload 4
      //   3579: ifne +59 -> 3638
      //   3582: iload_1
      //   3583: istore_2
      //   3584: iload_3
      //   3585: ifne +53 -> 3638
      //   3588: ldc2_w 474
      //   3591: invokestatic 289	java/lang/Thread:sleep	(J)V
      //   3594: iload_1
      //   3595: iconst_1
      //   3596: iadd
      //   3597: istore_1
      //   3598: goto -60 -> 3538
      //   3601: iconst_0
      //   3602: istore_3
      //   3603: goto -46 -> 3557
      //   3606: iconst_0
      //   3607: istore 4
      //   3609: goto -34 -> 3575
      //   3612: astore 12
      //   3614: new 82	java/lang/StringBuilder
      //   3617: dup
      //   3618: ldc_w 727
      //   3621: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3624: aload 12
      //   3626: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3629: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3632: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3635: goto -41 -> 3594
      //   3638: iload_2
      //   3639: iconst_2
      //   3640: if_icmpge +66 -> 3706
      //   3643: aload_0
      //   3644: getfield 14	aax$16:a	Laax;
      //   3647: invokestatic 733	aax:e	(Laax;)Ljava/lang/String;
      //   3650: ifnull +22 -> 3672
      //   3653: iconst_1
      //   3654: istore_1
      //   3655: iload_1
      //   3656: ifne +50 -> 3706
      //   3659: ldc2_w 474
      //   3662: invokestatic 289	java/lang/Thread:sleep	(J)V
      //   3665: iload_2
      //   3666: iconst_1
      //   3667: iadd
      //   3668: istore_2
      //   3669: goto -31 -> 3638
      //   3672: iconst_0
      //   3673: istore_1
      //   3674: goto -19 -> 3655
      //   3677: astore 12
      //   3679: new 82	java/lang/StringBuilder
      //   3682: dup
      //   3683: ldc_w 727
      //   3686: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   3689: aload 12
      //   3691: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   3694: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   3697: invokestatic 231	abh:a	(Ljava/lang/String;)V
      //   3700: goto -35 -> 3665
      //   3703: invokestatic 36	abh:a	()V
      //   3706: invokestatic 739	android/os/Message:obtain	()Landroid/os/Message;
      //   3709: astore 12
      //   3711: new 741	android/os/Bundle
      //   3714: dup
      //   3715: invokespecial 742	android/os/Bundle:<init>	()V
      //   3718: astore 13
      //   3720: aload 13
      //   3722: ldc_w 744
      //   3725: invokestatic 747	aax:u	()Z
      //   3728: invokevirtual 751	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
      //   3731: aload 12
      //   3733: aload 13
      //   3735: invokevirtual 755	android/os/Message:setData	(Landroid/os/Bundle;)V
      //   3738: aload_0
      //   3739: getfield 14	aax$16:a	Laax;
      //   3742: invokestatic 758	aax:f	(Laax;)Landroid/os/Handler;
      //   3745: ifnull -3281 -> 464
      //   3748: invokestatic 36	abh:a	()V
      //   3751: aload_0
      //   3752: getfield 14	aax$16:a	Laax;
      //   3755: invokestatic 758	aax:f	(Laax;)Landroid/os/Handler;
      //   3758: aload 12
      //   3760: invokevirtual 764	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   3763: pop
      //   3764: return
      //   3765: astore 13
      //   3767: goto -444 -> 3323
      //   3770: astore 14
      //   3772: goto -543 -> 3229
      //   3775: astore 14
      //   3777: goto -631 -> 3146
      //   3780: astore 14
      //   3782: goto -2430 -> 1352
      //   3785: astore 14
      //   3787: goto -2487 -> 1300
      //   3790: astore 14
      //   3792: goto -2545 -> 1247
      //   3795: astore 14
      //   3797: goto -2919 -> 878
      //   3800: astore 14
      //   3802: goto -2980 -> 822
      //   3805: goto -3666 -> 139
      //   3808: iconst_0
      //   3809: istore_2
      //   3810: goto -3160 -> 650
      //   3813: goto -3157 -> 656
      //   3816: iconst_0
      //   3817: istore_1
      //   3818: goto -3714 -> 104
      //   3821: astore 14
      //   3823: goto -2890 -> 933
      //   3826: astore 14
      //   3828: goto -2809 -> 1019
      //   3831: astore 14
      //   3833: goto -2720 -> 1113
      //   3836: astore 14
      //   3838: goto -2646 -> 1192
      //   3841: iload_1
      //   3842: ifne -523 -> 3319
      //   3845: iload_2
      //   3846: ifeq -523 -> 3323
      //   3849: goto -530 -> 3319
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3852	0	this	16
      //   103	3739	1	i	int
      //   555	3291	2	j	int
      //   581	3022	3	k	int
      //   1602	2006	4	m	int
      //   7	3444	5	l1	long
      //   12	18	7	l2	long
      //   27	5	9	l3	long
      //   533	1972	11	bool	boolean
      //   135	1	12	localObject1	Object
      //   424	36	12	localIOException	IOException
      //   465	12	12	localException1	Exception
      //   514	839	12	localObject2	Object
      //   1467	13	12	localException2	Exception
      //   1661	1663	12	localException3	Exception
      //   3331	24	12	str	String
      //   3370	59	12	localException4	Exception
      //   3488	13	12	localInterruptedException1	InterruptedException
      //   3612	13	12	localInterruptedException2	InterruptedException
      //   3677	13	12	localInterruptedException3	InterruptedException
      //   3709	50	12	localMessage	Message
      //   252	1049	13	localObject3	Object
      //   1440	13	13	localJSONException1	JSONException
      //   1646	1655	13	localJSONException2	JSONException
      //   3718	16	13	localBundle	Bundle
      //   3765	1	13	localException5	Exception
      //   332	1064	14	localObject4	Object
      //   1653	1	14	localJSONException3	JSONException
      //   1702	1	14	localJSONException4	JSONException
      //   1713	454	14	localJSONException5	JSONException
      //   2207	12	14	localException6	Exception
      //   2418	45	14	localPackageInfo	android.content.pm.PackageInfo
      //   2485	1	14	localException7	Exception
      //   2521	362	14	localObject5	Object
      //   2923	13	14	localException8	Exception
      //   2958	13	14	localException9	Exception
      //   2985	27	14	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   3026	1	14	localObject6	Object
      //   3037	36	14	localException10	Exception
      //   3103	13	14	localException11	Exception
      //   3378	1	14	localException12	Exception
      //   3770	1	14	localJSONException6	JSONException
      //   3775	1	14	localException13	Exception
      //   3780	1	14	localException14	Exception
      //   3785	1	14	localException15	Exception
      //   3790	1	14	localException16	Exception
      //   3795	1	14	localException17	Exception
      //   3800	1	14	localException18	Exception
      //   3821	1	14	localException19	Exception
      //   3826	1	14	localException20	Exception
      //   3831	1	14	localException21	Exception
      //   3836	1	14	localException22	Exception
      //   352	2056	15	localObject7	Object
      //   2371	13	16	localIterator	Iterator
      //   2393	605	17	localApplicationInfo	android.content.pm.ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   139	183	424	java/io/IOException
      //   183	192	424	java/io/IOException
      //   192	401	424	java/io/IOException
      //   401	408	424	java/io/IOException
      //   413	421	424	java/io/IOException
      //   491	501	424	java/io/IOException
      //   501	512	424	java/io/IOException
      //   516	535	424	java/io/IOException
      //   545	554	424	java/io/IOException
      //   556	580	424	java/io/IOException
      //   593	646	424	java/io/IOException
      //   665	687	424	java/io/IOException
      //   690	700	424	java/io/IOException
      //   704	723	424	java/io/IOException
      //   727	730	424	java/io/IOException
      //   739	777	424	java/io/IOException
      //   777	798	424	java/io/IOException
      //   798	822	424	java/io/IOException
      //   822	878	424	java/io/IOException
      //   878	933	424	java/io/IOException
      //   933	985	424	java/io/IOException
      //   991	1015	424	java/io/IOException
      //   1015	1019	424	java/io/IOException
      //   1019	1062	424	java/io/IOException
      //   1066	1113	424	java/io/IOException
      //   1113	1159	424	java/io/IOException
      //   1163	1192	424	java/io/IOException
      //   1192	1207	424	java/io/IOException
      //   1213	1237	424	java/io/IOException
      //   1240	1247	424	java/io/IOException
      //   1247	1262	424	java/io/IOException
      //   1267	1291	424	java/io/IOException
      //   1293	1300	424	java/io/IOException
      //   1300	1315	424	java/io/IOException
      //   1319	1343	424	java/io/IOException
      //   1345	1352	424	java/io/IOException
      //   1352	1384	424	java/io/IOException
      //   1386	1433	424	java/io/IOException
      //   1442	1461	424	java/io/IOException
      //   1461	1464	424	java/io/IOException
      //   1655	1658	424	java/io/IOException
      //   1663	1701	424	java/io/IOException
      //   1704	1710	424	java/io/IOException
      //   1715	1721	424	java/io/IOException
      //   1724	1764	424	java/io/IOException
      //   1767	1787	424	java/io/IOException
      //   1797	1828	424	java/io/IOException
      //   1831	1864	424	java/io/IOException
      //   1874	1921	424	java/io/IOException
      //   1924	1974	424	java/io/IOException
      //   1983	2013	424	java/io/IOException
      //   2016	2045	424	java/io/IOException
      //   2057	2081	424	java/io/IOException
      //   2096	2120	424	java/io/IOException
      //   2135	2159	424	java/io/IOException
      //   2166	2204	424	java/io/IOException
      //   2209	2223	424	java/io/IOException
      //   2223	2240	424	java/io/IOException
      //   2245	2264	424	java/io/IOException
      //   2264	2281	424	java/io/IOException
      //   2286	2308	424	java/io/IOException
      //   2308	2325	424	java/io/IOException
      //   2330	2373	424	java/io/IOException
      //   2373	2402	424	java/io/IOException
      //   2407	2420	424	java/io/IOException
      //   2425	2482	424	java/io/IOException
      //   2487	2504	424	java/io/IOException
      //   2509	2599	424	java/io/IOException
      //   2599	2631	424	java/io/IOException
      //   2633	2670	424	java/io/IOException
      //   2677	2715	424	java/io/IOException
      //   2718	2756	424	java/io/IOException
      //   2759	2797	424	java/io/IOException
      //   2800	2838	424	java/io/IOException
      //   2841	2879	424	java/io/IOException
      //   2882	2920	424	java/io/IOException
      //   2925	2949	424	java/io/IOException
      //   2952	2955	424	java/io/IOException
      //   2960	2979	424	java/io/IOException
      //   2979	2982	424	java/io/IOException
      //   2987	3025	424	java/io/IOException
      //   3031	3034	424	java/io/IOException
      //   3039	3063	424	java/io/IOException
      //   3066	3069	424	java/io/IOException
      //   3072	3100	424	java/io/IOException
      //   3105	3129	424	java/io/IOException
      //   3129	3146	424	java/io/IOException
      //   3146	3167	424	java/io/IOException
      //   3167	3229	424	java/io/IOException
      //   3229	3277	424	java/io/IOException
      //   3279	3314	424	java/io/IOException
      //   3319	3323	424	java/io/IOException
      //   3323	3369	424	java/io/IOException
      //   3372	3375	424	java/io/IOException
      //   3380	3383	424	java/io/IOException
      //   3396	3417	424	java/io/IOException
      //   9	29	465	java/lang/Exception
      //   501	512	1440	org/json/JSONException
      //   139	183	1467	java/lang/Exception
      //   183	192	1467	java/lang/Exception
      //   192	401	1467	java/lang/Exception
      //   401	408	1467	java/lang/Exception
      //   413	421	1467	java/lang/Exception
      //   491	501	1467	java/lang/Exception
      //   501	512	1467	java/lang/Exception
      //   516	535	1467	java/lang/Exception
      //   545	554	1467	java/lang/Exception
      //   556	580	1467	java/lang/Exception
      //   593	646	1467	java/lang/Exception
      //   1442	1461	1467	java/lang/Exception
      //   1461	1464	1467	java/lang/Exception
      //   1663	1701	1467	java/lang/Exception
      //   3396	3417	1467	java/lang/Exception
      //   545	554	1646	org/json/JSONException
      //   556	580	1646	org/json/JSONException
      //   593	646	1646	org/json/JSONException
      //   690	700	1653	org/json/JSONException
      //   704	723	1653	org/json/JSONException
      //   727	730	1653	org/json/JSONException
      //   665	687	1661	java/lang/Exception
      //   690	700	1661	java/lang/Exception
      //   704	723	1661	java/lang/Exception
      //   727	730	1661	java/lang/Exception
      //   739	777	1661	java/lang/Exception
      //   777	798	1661	java/lang/Exception
      //   1655	1658	1661	java/lang/Exception
      //   1704	1710	1661	java/lang/Exception
      //   1715	1721	1661	java/lang/Exception
      //   2209	2223	1661	java/lang/Exception
      //   2223	2240	1661	java/lang/Exception
      //   2264	2281	1661	java/lang/Exception
      //   2308	2325	1661	java/lang/Exception
      //   2487	2504	1661	java/lang/Exception
      //   2925	2949	1661	java/lang/Exception
      //   2952	2955	1661	java/lang/Exception
      //   2960	2979	1661	java/lang/Exception
      //   2979	2982	1661	java/lang/Exception
      //   3031	3034	1661	java/lang/Exception
      //   3039	3063	1661	java/lang/Exception
      //   3066	3069	1661	java/lang/Exception
      //   3105	3129	1661	java/lang/Exception
      //   3146	3167	1661	java/lang/Exception
      //   3372	3375	1661	java/lang/Exception
      //   3380	3383	1661	java/lang/Exception
      //   739	777	1702	org/json/JSONException
      //   777	798	1713	org/json/JSONException
      //   1352	1384	2207	java/lang/Exception
      //   1386	1433	2207	java/lang/Exception
      //   2166	2204	2207	java/lang/Exception
      //   2677	2715	2207	java/lang/Exception
      //   2718	2756	2207	java/lang/Exception
      //   2759	2797	2207	java/lang/Exception
      //   2800	2838	2207	java/lang/Exception
      //   2841	2879	2207	java/lang/Exception
      //   2882	2920	2207	java/lang/Exception
      //   2330	2373	2485	java/lang/Exception
      //   2373	2402	2485	java/lang/Exception
      //   2407	2420	2485	java/lang/Exception
      //   2425	2482	2485	java/lang/Exception
      //   2987	3025	2485	java/lang/Exception
      //   2245	2264	2923	java/lang/Exception
      //   2286	2308	2958	java/lang/Exception
      //   2407	2420	2985	android/content/pm/PackageManager$NameNotFoundException
      //   2509	2599	3037	java/lang/Exception
      //   2599	2631	3103	java/lang/Exception
      //   2633	2670	3103	java/lang/Exception
      //   3072	3100	3103	java/lang/Exception
      //   3323	3369	3370	java/lang/Exception
      //   3167	3229	3378	java/lang/Exception
      //   1626	1632	3488	java/lang/InterruptedException
      //   3588	3594	3612	java/lang/InterruptedException
      //   3659	3665	3677	java/lang/InterruptedException
      //   3229	3277	3765	java/lang/Exception
      //   3279	3314	3765	java/lang/Exception
      //   3319	3323	3765	java/lang/Exception
      //   3167	3229	3770	org/json/JSONException
      //   3129	3146	3775	java/lang/Exception
      //   1300	1315	3780	java/lang/Exception
      //   1319	1343	3780	java/lang/Exception
      //   1345	1352	3780	java/lang/Exception
      //   2135	2159	3780	java/lang/Exception
      //   1247	1262	3785	java/lang/Exception
      //   1267	1291	3785	java/lang/Exception
      //   1293	1300	3785	java/lang/Exception
      //   2096	2120	3785	java/lang/Exception
      //   1192	1207	3790	java/lang/Exception
      //   1213	1237	3790	java/lang/Exception
      //   1240	1247	3790	java/lang/Exception
      //   2057	2081	3790	java/lang/Exception
      //   822	878	3795	java/lang/Exception
      //   798	822	3800	java/lang/Exception
      //   878	933	3821	java/lang/Exception
      //   1724	1764	3821	java/lang/Exception
      //   1767	1787	3821	java/lang/Exception
      //   933	985	3826	java/lang/Exception
      //   991	1015	3826	java/lang/Exception
      //   1015	1019	3826	java/lang/Exception
      //   1797	1828	3826	java/lang/Exception
      //   1831	1864	3826	java/lang/Exception
      //   1019	1062	3831	java/lang/Exception
      //   1066	1113	3831	java/lang/Exception
      //   1874	1921	3831	java/lang/Exception
      //   1924	1974	3831	java/lang/Exception
      //   1113	1159	3836	java/lang/Exception
      //   1163	1192	3836	java/lang/Exception
      //   1983	2013	3836	java/lang/Exception
      //   2016	2045	3836	java/lang/Exception
    }
  };
  private Handler al = null;
  protected JSONObject c;
  private String n;
  private String o;
  private String p;
  private String q;
  private String r;
  private String t;
  private String u;
  private int v;
  private int w;
  
  static
  {
    a = "";
    i = false;
    j = 60000;
    k = false;
    l = false;
    m = new HashMap() {};
    A = 7;
    B = 60;
    D = false;
    E = false;
    F = false;
    G = true;
    H = false;
    I = 0L;
    J = 0L;
    K = new ArrayList();
    S = true;
    T = false;
    U = false;
    W = 0L;
    d = false;
  }
  
  public aax(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    a(paramContext, paramHashMap);
  }
  
  private static String R()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  private String S()
  {
    return this.n + " " + this.o;
  }
  
  private String T()
  {
    if (this.q != null) {
      return this.q;
    }
    return "Unknown";
  }
  
  private static String U()
  {
    if ((ac.contains("kochava_app_id_generated")) && (!ac.getString("kochava_app_id_generated", "").equals(""))) {
      return ac.getString("kochava_app_id_generated", "");
    }
    String str = UUID.randomUUID().toString().replaceAll("-", "");
    str = "KA" + str;
    ac.edit().putString("kochava_app_id_generated", str).apply();
    return str;
  }
  
  private static String V()
  {
    int i2 = 0;
    if (!ac.getString("initBool", "").equals("true"))
    {
      abh.a();
      localObject1 = "";
      return localObject1;
    }
    Object localObject1 = N.a();
    if (localObject1 == null) {
      return "";
    }
    localObject1 = ((String)localObject1).split("=", 2);
    long l1 = Long.parseLong(localObject1[0]);
    Object localObject3 = localObject1[1];
    abh.a();
    if (((String)localObject3).contains("\"action\":\"initial\"")) {
      abh.a("Post Data: Event is initial, look at response");
    }
    for (int i1 = 1;; i1 = 0)
    {
      if ((h == null) || (h.trim().isEmpty()))
      {
        abh.a();
        h = "control.kochava.com";
      }
      try
      {
        new StringBuilder("postEvent - posting to https://").append(h).append("/track/kvTracker.php");
        abh.a();
        localObject1 = (HttpsURLConnection)new URL("https://" + h + "/track/kvTracker.php").openConnection();
        ((HttpsURLConnection)localObject1).setRequestProperty("User-Agent", ac.getString("useragent", ""));
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
        for (;;)
        {
          Object localObject4;
          abh.a("TrackTask " + localException);
          return "";
          abh.a("TrackTask " + localException);
        }
      }
      catch (IOException localIOException2)
      {
        label324:
        if (!localIOException2.getClass().equals(SSLException.class)) {
          break label639;
        }
      }
      try
      {
        abh.a();
        localObject3 = new StringBuffer("");
        localObject1 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject1).getInputStream()));
        localObject4 = ((BufferedReader)localObject1).readLine();
        if (localObject4 != null)
        {
          ((StringBuffer)localObject3).append((String)localObject4);
          break label324;
        }
      }
      catch (IOException localIOException1)
      {
        if (!localIOException1.getClass().equals(SSLException.class)) {
          break label570;
        }
        abh.a("SSLException! Shutting down SDK and sending report." + localIOException1);
        b(localIOException1);
        return "";
        localObject3 = ((StringBuffer)localObject3).toString();
        abh.a();
        if (i1 != 0)
        {
          if (((String)localObject3).contains("\"success\":\"1\""))
          {
            abh.a();
            N.a(l1);
          }
          i1 = i2;
          if (ad != null)
          {
            i1 = i2;
            if (!ad.getAll().isEmpty()) {
              i1 = 1;
            }
          }
          Object localObject2 = localObject3;
          if (i1 != 0) {
            break;
          }
          localObject2 = localObject3;
          if (!Z) {
            break;
          }
          new StringBuilder("Requesting attribution data in ").append(A).append(" seconds...");
          abh.a();
          a(A);
          return localObject3;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        abh.a("TrackTask " + localOutOfMemoryError);
        return "";
      }
      N.a(l1);
      return localObject3;
      label570:
      abh.a("SSLException! Shutting down SDK and sending report." + localIOException2);
      b(localIOException2);
      for (;;)
      {
        return "";
        label639:
        abh.a("TrackTask " + localIOException2);
      }
    }
  }
  
  /* Error */
  private static String W()
  {
    // Byte code:
    //   0: ldc -123
    //   2: astore_3
    //   3: ldc -123
    //   5: astore_2
    //   6: new 343	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   13: ldc -123
    //   15: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: ldc_w 591
    //   21: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: astore 4
    //   29: ldc_w 593
    //   32: invokestatic 597	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   35: astore_2
    //   36: aload_2
    //   37: invokevirtual 460	java/lang/String:trim	()Ljava/lang/String;
    //   40: invokevirtual 463	java/lang/String:isEmpty	()Z
    //   43: ifeq +579 -> 622
    //   46: iconst_0
    //   47: istore_0
    //   48: aload 4
    //   50: astore_3
    //   51: aload_2
    //   52: astore_1
    //   53: iload_0
    //   54: ifne +73 -> 127
    //   57: new 343	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   64: aload 4
    //   66: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 599
    //   72: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore_3
    //   79: aload_2
    //   80: astore_1
    //   81: new 601	android/webkit/WebView
    //   84: dup
    //   85: getstatic 299	aax:b	Landroid/content/Context;
    //   88: invokespecial 604	android/webkit/WebView:<init>	(Landroid/content/Context;)V
    //   91: invokevirtual 608	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   94: invokevirtual 613	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   97: astore_2
    //   98: aload_2
    //   99: astore_1
    //   100: new 343	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   107: aload_3
    //   108: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: ldc_w 615
    //   114: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: astore 4
    //   122: aload 4
    //   124: astore_3
    //   125: aload_2
    //   126: astore_1
    //   127: aload_1
    //   128: invokevirtual 460	java/lang/String:trim	()Ljava/lang/String;
    //   131: invokevirtual 463	java/lang/String:isEmpty	()Z
    //   134: ifeq +483 -> 617
    //   137: iconst_0
    //   138: istore_0
    //   139: aload_1
    //   140: astore 4
    //   142: iload_0
    //   143: ifne +127 -> 270
    //   146: new 343	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   153: aload_3
    //   154: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: ldc_w 617
    //   160: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   166: astore 4
    //   168: ldc_w 610
    //   171: iconst_2
    //   172: anewarray 619	java/lang/Class
    //   175: dup
    //   176: iconst_0
    //   177: ldc_w 621
    //   180: aastore
    //   181: dup
    //   182: iconst_1
    //   183: ldc_w 601
    //   186: aastore
    //   187: invokevirtual 625	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   190: astore_2
    //   191: aload_2
    //   192: astore_3
    //   193: aload_1
    //   194: astore 5
    //   196: aload_2
    //   197: iconst_1
    //   198: invokevirtual 630	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   201: aload_2
    //   202: astore_3
    //   203: aload_1
    //   204: astore 5
    //   206: aload_2
    //   207: iconst_2
    //   208: anewarray 4	java/lang/Object
    //   211: dup
    //   212: iconst_0
    //   213: getstatic 299	aax:b	Landroid/content/Context;
    //   216: aastore
    //   217: dup
    //   218: iconst_1
    //   219: aconst_null
    //   220: aastore
    //   221: invokevirtual 634	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   224: checkcast 610	android/webkit/WebSettings
    //   227: invokevirtual 613	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   230: astore_1
    //   231: aload_2
    //   232: astore_3
    //   233: aload_1
    //   234: astore 5
    //   236: new 343	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   243: aload 4
    //   245: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: ldc_w 636
    //   251: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: pop
    //   255: aload_1
    //   256: astore 4
    //   258: aload_2
    //   259: ifnull +11 -> 270
    //   262: aload_2
    //   263: iconst_0
    //   264: invokevirtual 630	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   267: aload_1
    //   268: astore 4
    //   270: invokestatic 278	abh:a	()V
    //   273: aload 4
    //   275: areturn
    //   276: astore_1
    //   277: new 638	java/io/StringWriter
    //   280: dup
    //   281: invokespecial 639	java/io/StringWriter:<init>	()V
    //   284: astore 4
    //   286: aload_1
    //   287: new 641	java/io/PrintWriter
    //   290: dup
    //   291: aload 4
    //   293: invokespecial 644	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   296: invokevirtual 647	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   299: aload 4
    //   301: invokevirtual 648	java/io/StringWriter:toString	()Ljava/lang/String;
    //   304: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   307: new 343	java/lang/StringBuilder
    //   310: dup
    //   311: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   314: aload_2
    //   315: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: ldc_w 650
    //   321: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: aload_1
    //   325: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   328: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: ldc_w 653
    //   334: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: aload 4
    //   339: invokevirtual 648	java/io/StringWriter:toString	()Ljava/lang/String;
    //   342: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   348: astore 4
    //   350: aload_3
    //   351: astore_2
    //   352: goto -316 -> 36
    //   355: astore_1
    //   356: aload 4
    //   358: astore_3
    //   359: aload_1
    //   360: astore 4
    //   362: aload_2
    //   363: astore_1
    //   364: aload 4
    //   366: astore_2
    //   367: new 638	java/io/StringWriter
    //   370: dup
    //   371: invokespecial 639	java/io/StringWriter:<init>	()V
    //   374: astore 4
    //   376: aload_2
    //   377: new 641	java/io/PrintWriter
    //   380: dup
    //   381: aload 4
    //   383: invokespecial 644	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   386: invokevirtual 647	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   389: aload 4
    //   391: invokevirtual 648	java/io/StringWriter:toString	()Ljava/lang/String;
    //   394: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   397: new 343	java/lang/StringBuilder
    //   400: dup
    //   401: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   404: aload_3
    //   405: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: ldc_w 655
    //   411: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: aload_2
    //   415: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   418: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: ldc_w 653
    //   424: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: aload 4
    //   429: invokevirtual 648	java/io/StringWriter:toString	()Ljava/lang/String;
    //   432: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: ldc_w 657
    //   438: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: astore_3
    //   445: goto -318 -> 127
    //   448: astore 4
    //   450: aconst_null
    //   451: astore_2
    //   452: aload_3
    //   453: astore 5
    //   455: aload_2
    //   456: astore_3
    //   457: new 638	java/io/StringWriter
    //   460: dup
    //   461: invokespecial 639	java/io/StringWriter:<init>	()V
    //   464: astore 6
    //   466: aload_2
    //   467: astore_3
    //   468: aload 4
    //   470: new 641	java/io/PrintWriter
    //   473: dup
    //   474: aload 6
    //   476: invokespecial 644	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   479: invokevirtual 647	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   482: aload_2
    //   483: astore_3
    //   484: aload 6
    //   486: invokevirtual 648	java/io/StringWriter:toString	()Ljava/lang/String;
    //   489: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   492: aload_2
    //   493: astore_3
    //   494: new 343	java/lang/StringBuilder
    //   497: dup
    //   498: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   501: aload 5
    //   503: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: ldc_w 659
    //   509: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: aload 4
    //   514: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   517: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: ldc_w 653
    //   523: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: aload 6
    //   528: invokevirtual 648	java/io/StringWriter:toString	()Ljava/lang/String;
    //   531: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: ldc_w 657
    //   537: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   540: pop
    //   541: aload_1
    //   542: astore 4
    //   544: aload_2
    //   545: ifnull -275 -> 270
    //   548: aload_2
    //   549: iconst_0
    //   550: invokevirtual 630	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   553: aload_1
    //   554: astore 4
    //   556: goto -286 -> 270
    //   559: astore_1
    //   560: aconst_null
    //   561: astore_3
    //   562: aload_3
    //   563: ifnull +8 -> 571
    //   566: aload_3
    //   567: iconst_0
    //   568: invokevirtual 630	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   571: aload_1
    //   572: athrow
    //   573: astore_1
    //   574: goto -12 -> 562
    //   577: astore_3
    //   578: aconst_null
    //   579: astore_2
    //   580: aload 4
    //   582: astore 5
    //   584: aload_3
    //   585: astore 4
    //   587: goto -132 -> 455
    //   590: astore_1
    //   591: aload 4
    //   593: astore_3
    //   594: aload_1
    //   595: astore 4
    //   597: aload 5
    //   599: astore_1
    //   600: aload_3
    //   601: astore 5
    //   603: goto -148 -> 455
    //   606: astore_2
    //   607: goto -240 -> 367
    //   610: astore_1
    //   611: aload 4
    //   613: astore_2
    //   614: goto -337 -> 277
    //   617: iconst_1
    //   618: istore_0
    //   619: goto -480 -> 139
    //   622: iconst_1
    //   623: istore_0
    //   624: goto -576 -> 48
    // Local variable table:
    //   start	length	slot	name	signature
    //   47	577	0	i1	int
    //   52	216	1	localObject1	Object
    //   276	49	1	localException1	Exception
    //   355	5	1	localException2	Exception
    //   363	191	1	localObject2	Object
    //   559	13	1	localObject3	Object
    //   573	1	1	localObject4	Object
    //   590	5	1	localException3	Exception
    //   599	1	1	localObject5	Object
    //   610	1	1	localException4	Exception
    //   5	575	2	localObject6	Object
    //   606	1	2	localException5	Exception
    //   613	1	2	localObject7	Object
    //   2	565	3	localObject8	Object
    //   577	8	3	localException6	Exception
    //   593	8	3	localObject9	Object
    //   27	401	4	localObject10	Object
    //   448	65	4	localException7	Exception
    //   542	70	4	localObject11	Object
    //   194	408	5	localObject12	Object
    //   464	63	6	localStringWriter	java.io.StringWriter
    // Exception table:
    //   from	to	target	type
    //   6	29	276	java/lang/Exception
    //   57	79	355	java/lang/Exception
    //   146	168	448	java/lang/Exception
    //   146	168	559	finally
    //   168	191	559	finally
    //   196	201	573	finally
    //   206	231	573	finally
    //   236	255	573	finally
    //   457	466	573	finally
    //   468	482	573	finally
    //   484	492	573	finally
    //   494	541	573	finally
    //   168	191	577	java/lang/Exception
    //   196	201	590	java/lang/Exception
    //   206	231	590	java/lang/Exception
    //   236	255	590	java/lang/Exception
    //   81	98	606	java/lang/Exception
    //   100	122	606	java/lang/Exception
    //   29	36	610	java/lang/Exception
  }
  
  private static String X()
  {
    try
    {
      Object localObject = (AudioManager)b.getSystemService("audio");
      localObject = ((AudioManager)localObject).getStreamVolume(3);
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static String Y()
  {
    for (;;)
    {
      int i1;
      try
      {
        i1 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(b);
        if (i1 != 0) {}
        switch (i1)
        {
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
          new StringBuilder("Google Play Services check returned unknown error code (").append(i1).append(").");
          abh.a();
          abh.a("Problem getting Advertising ID " + GooglePlayServicesUtil.getErrorString(i1));
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(b);
          String str = localInfo.getId();
          D = localInfo.isLimitAdTrackingEnabled();
          return str;
        }
      }
      catch (Exception localException)
      {
        abh.a("Problem getting Advertising ID (catch): " + localException.toString());
        return "";
      }
      new StringBuilder("Google Play Services check returned ConnectionResult.SUCCESS (").append(i1).append(").");
      abh.a();
      continue;
      new StringBuilder("Google Play Services check returned ConnectionResult.SERVICE_MISSING (").append(i1).append(").");
      abh.a();
      continue;
      new StringBuilder("Google Play Services check returned ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED (").append(i1).append(").");
      abh.a();
      continue;
      new StringBuilder("Google Play Services check returned ConnectionResult.SERVICE_DISABLED (").append(i1).append(").");
      abh.a();
      continue;
      new StringBuilder("Google Play Services check returned ConnectionResult.SERVICE_INVALID (").append(i1).append(").");
      abh.a();
    }
  }
  
  private static String Z()
  {
    Object localObject1 = "";
    int i1;
    Account localAccount;
    Object localObject2;
    if (b.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0)
    {
      Account[] arrayOfAccount = AccountManager.get(b).getAccounts();
      int i3 = arrayOfAccount.length;
      i1 = 0;
      if (i1 < i3)
      {
        localAccount = arrayOfAccount[i1];
        localObject2 = localAccount.name;
        if (!Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher((CharSequence)localObject2).matches()) {
          break label182;
        }
      }
    }
    label182:
    for (int i2 = 1;; i2 = 0)
    {
      localObject2 = localObject1;
      if (i2 != 0)
      {
        localObject2 = localAccount.name.toLowerCase();
        localObject2 = (String)localObject1 + (String)localObject2 + ",";
      }
      i1 += 1;
      localObject1 = localObject2;
      break;
      if (((String)localObject1).length() > 0) {
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
      for (;;)
      {
        return "[" + (String)localObject1 + "]";
        localObject1 = "";
        continue;
        localObject1 = "";
        abh.a();
      }
    }
  }
  
  /* Error */
  protected static String a(android.content.ContentResolver paramContentResolver)
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 214	aax:ag	Landroid/net/Uri;
    //   4: iconst_1
    //   5: anewarray 317	java/lang/String
    //   8: dup
    //   9: iconst_0
    //   10: ldc_w 781
    //   13: aastore
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: invokevirtual 787	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   20: astore_0
    //   21: aload_0
    //   22: ifnull +16 -> 38
    //   25: aload_0
    //   26: astore_2
    //   27: aload_0
    //   28: invokeinterface 792 1 0
    //   33: istore_1
    //   34: iload_1
    //   35: ifne +24 -> 59
    //   38: aload_0
    //   39: ifnull +18 -> 57
    //   42: aload_0
    //   43: invokeinterface 795 1 0
    //   48: ifne +9 -> 57
    //   51: aload_0
    //   52: invokeinterface 796 1 0
    //   57: aconst_null
    //   58: areturn
    //   59: aload_0
    //   60: astore_2
    //   61: aload_0
    //   62: aload_0
    //   63: ldc_w 781
    //   66: invokeinterface 799 2 0
    //   71: invokeinterface 801 2 0
    //   76: astore_3
    //   77: aload_3
    //   78: astore_2
    //   79: aload_0
    //   80: ifnull +22 -> 102
    //   83: aload_3
    //   84: astore_2
    //   85: aload_0
    //   86: invokeinterface 795 1 0
    //   91: ifne +11 -> 102
    //   94: aload_0
    //   95: invokeinterface 796 1 0
    //   100: aload_3
    //   101: astore_2
    //   102: aload_2
    //   103: areturn
    //   104: astore_3
    //   105: aconst_null
    //   106: astore_0
    //   107: aload_0
    //   108: astore_2
    //   109: new 343	java/lang/StringBuilder
    //   112: dup
    //   113: ldc_w 803
    //   116: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   119: aload_3
    //   120: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   123: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: aload_0
    //   128: astore_2
    //   129: invokestatic 278	abh:a	()V
    //   132: aload_0
    //   133: ifnull +18 -> 151
    //   136: aload_0
    //   137: invokeinterface 795 1 0
    //   142: ifne +9 -> 151
    //   145: aload_0
    //   146: invokeinterface 796 1 0
    //   151: aconst_null
    //   152: astore_2
    //   153: goto -51 -> 102
    //   156: astore_0
    //   157: aconst_null
    //   158: astore_2
    //   159: goto -57 -> 102
    //   162: astore_0
    //   163: aconst_null
    //   164: astore_2
    //   165: aload_2
    //   166: ifnull +18 -> 184
    //   169: aload_2
    //   170: invokeinterface 795 1 0
    //   175: ifne +9 -> 184
    //   178: aload_2
    //   179: invokeinterface 796 1 0
    //   184: aload_0
    //   185: athrow
    //   186: astore_2
    //   187: goto -3 -> 184
    //   190: astore_0
    //   191: goto -26 -> 165
    //   194: astore_3
    //   195: goto -88 -> 107
    //   198: astore_0
    //   199: aload_3
    //   200: astore_2
    //   201: goto -99 -> 102
    //   204: astore_0
    //   205: aconst_null
    //   206: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	paramContentResolver	android.content.ContentResolver
    //   33	2	1	bool	boolean
    //   26	153	2	localObject1	Object
    //   186	1	2	localException1	Exception
    //   200	1	2	localObject2	Object
    //   76	25	3	str	String
    //   104	16	3	localException2	Exception
    //   194	6	3	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   0	21	104	java/lang/Exception
    //   136	151	156	java/lang/Exception
    //   0	21	162	finally
    //   169	184	186	java/lang/Exception
    //   27	34	190	finally
    //   61	77	190	finally
    //   109	127	190	finally
    //   129	132	190	finally
    //   27	34	194	java/lang/Exception
    //   61	77	194	java/lang/Exception
    //   85	100	198	java/lang/Exception
    //   42	57	204	java/lang/Exception
  }
  
  public static void a()
  {
    if (d)
    {
      abh.a("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    abh.a();
    ae.submit(new abc((byte)0));
  }
  
  protected static void a(int paramInt)
  {
    af.schedule(ak, paramInt, TimeUnit.SECONDS);
  }
  
  /* Error */
  @android.annotation.TargetApi(14)
  private void a(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 15
    //   3: iconst_1
    //   4: istore 4
    //   6: aload_1
    //   7: ifnull +2024 -> 2031
    //   10: aload_1
    //   11: invokevirtual 857	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   14: putstatic 299	aax:b	Landroid/content/Context;
    //   17: iconst_1
    //   18: putstatic 163	aax:G	Z
    //   21: new 343	java/lang/StringBuilder
    //   24: dup
    //   25: ldc_w 859
    //   28: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   31: getstatic 137	aax:a	Ljava/lang/String;
    //   34: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: pop
    //   38: aload_0
    //   39: new 282	java/util/Timer
    //   42: dup
    //   43: invokespecial 283	java/util/Timer:<init>	()V
    //   46: putfield 861	aax:V	Ljava/util/Timer;
    //   49: getstatic 299	aax:b	Landroid/content/Context;
    //   52: ldc_w 863
    //   55: iconst_0
    //   56: invokevirtual 867	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   59: putstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   62: new 436	aav
    //   65: dup
    //   66: getstatic 299	aax:b	Landroid/content/Context;
    //   69: invokespecial 868	aav:<init>	(Landroid/content/Context;)V
    //   72: putstatic 255	aax:N	Laav;
    //   75: aload_2
    //   76: ifnull +235 -> 311
    //   79: aload_2
    //   80: ldc_w 870
    //   83: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   86: ifnull +48 -> 134
    //   89: aload_2
    //   90: ldc_w 870
    //   93: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   96: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   99: ldc_w 875
    //   102: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   105: ifeq +29 -> 134
    //   108: aload_2
    //   109: ldc_w 870
    //   112: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   115: checkcast 875	java/lang/Boolean
    //   118: invokevirtual 878	java/lang/Boolean:booleanValue	()Z
    //   121: istore 5
    //   123: invokestatic 278	abh:a	()V
    //   126: invokestatic 278	abh:a	()V
    //   129: iload 5
    //   131: putstatic 359	abd:a	Z
    //   134: aload_2
    //   135: ldc_w 880
    //   138: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   141: ifnull +35 -> 176
    //   144: aload_2
    //   145: ldc_w 880
    //   148: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: ldc_w 317
    //   157: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   160: ifeq +16 -> 176
    //   163: aload_2
    //   164: ldc_w 880
    //   167: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 317	java/lang/String
    //   173: putstatic 137	aax:a	Ljava/lang/String;
    //   176: aload_2
    //   177: ldc_w 882
    //   180: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   183: ifnull +38 -> 221
    //   186: aload_2
    //   187: ldc_w 882
    //   190: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   196: ldc_w 875
    //   199: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   202: ifeq +19 -> 221
    //   205: aload_2
    //   206: ldc_w 882
    //   209: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   212: checkcast 875	java/lang/Boolean
    //   215: invokevirtual 878	java/lang/Boolean:booleanValue	()Z
    //   218: putstatic 139	aax:i	Z
    //   221: aload_2
    //   222: ldc_w 884
    //   225: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   228: ifnull +38 -> 266
    //   231: aload_2
    //   232: ldc_w 884
    //   235: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: ldc_w 875
    //   244: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   247: ifeq +19 -> 266
    //   250: aload_2
    //   251: ldc_w 884
    //   254: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   257: checkcast 875	java/lang/Boolean
    //   260: invokevirtual 878	java/lang/Boolean:booleanValue	()Z
    //   263: putstatic 161	aax:F	Z
    //   266: aload_2
    //   267: ldc_w 886
    //   270: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   273: ifnull +38 -> 311
    //   276: aload_2
    //   277: ldc_w 886
    //   280: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   283: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   286: ldc_w 875
    //   289: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +19 -> 311
    //   295: aload_2
    //   296: ldc_w 886
    //   299: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   302: checkcast 875	java/lang/Boolean
    //   305: invokevirtual 878	java/lang/Boolean:booleanValue	()Z
    //   308: putstatic 176	aax:S	Z
    //   311: aload_0
    //   312: getfield 242	aax:al	Landroid/os/Handler;
    //   315: ifnonnull +15 -> 330
    //   318: aload_0
    //   319: new 34	aax$6
    //   322: dup
    //   323: aload_0
    //   324: invokespecial 887	aax$6:<init>	(Laax;)V
    //   327: putfield 242	aax:al	Landroid/os/Handler;
    //   330: new 343	java/lang/StringBuilder
    //   333: dup
    //   334: ldc_w 889
    //   337: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   340: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   343: ldc_w 891
    //   346: ldc -123
    //   348: invokeinterface 315 3 0
    //   353: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: pop
    //   357: invokestatic 278	abh:a	()V
    //   360: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   363: ldc_w 891
    //   366: ldc -123
    //   368: invokeinterface 315 3 0
    //   373: ldc -123
    //   375: invokevirtual 321	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   378: ifne +26 -> 404
    //   381: new 219	org/json/JSONArray
    //   384: dup
    //   385: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   388: ldc_w 891
    //   391: ldc -123
    //   393: invokeinterface 315 3 0
    //   398: invokespecial 892	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   401: putstatic 222	aax:ai	Lorg/json/JSONArray;
    //   404: getstatic 269	android/os/Build$VERSION:SDK_INT	I
    //   407: bipush 14
    //   409: if_icmplt +1658 -> 2067
    //   412: getstatic 139	aax:i	Z
    //   415: ifne +1652 -> 2067
    //   418: new 343	java/lang/StringBuilder
    //   421: dup
    //   422: ldc_w 894
    //   425: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   428: getstatic 139	aax:i	Z
    //   431: invokevirtual 897	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   434: pop
    //   435: invokestatic 278	abh:a	()V
    //   438: getstatic 299	aax:b	Landroid/content/Context;
    //   441: checkcast 899	android/app/Application
    //   444: new 901	aba
    //   447: dup
    //   448: invokespecial 902	aba:<init>	()V
    //   451: invokevirtual 906	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   454: getstatic 299	aax:b	Landroid/content/Context;
    //   457: new 908	abb
    //   460: dup
    //   461: invokespecial 909	abb:<init>	()V
    //   464: invokevirtual 913	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   467: iconst_1
    //   468: putstatic 916	aay:a	Z
    //   471: iconst_1
    //   472: putstatic 918	aay:b	Z
    //   475: new 14	aax$13
    //   478: dup
    //   479: aload_0
    //   480: invokespecial 919	aax$13:<init>	(Laax;)V
    //   483: invokevirtual 333	java/lang/Thread:start	()V
    //   486: getstatic 161	aax:F	Z
    //   489: ifne +13 -> 502
    //   492: new 16	aax$14
    //   495: dup
    //   496: invokespecial 920	aax$14:<init>	()V
    //   499: invokevirtual 333	java/lang/Thread:start	()V
    //   502: aload_0
    //   503: getstatic 299	aax:b	Landroid/content/Context;
    //   506: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   509: getstatic 299	aax:b	Landroid/content/Context;
    //   512: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   515: iconst_0
    //   516: invokevirtual 933	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   519: getfield 938	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   522: putfield 395	aax:q	Ljava/lang/String;
    //   525: aload_0
    //   526: new 323	org/json/JSONObject
    //   529: dup
    //   530: invokespecial 939	org/json/JSONObject:<init>	()V
    //   533: putfield 941	aax:L	Lorg/json/JSONObject;
    //   536: aload_0
    //   537: new 323	org/json/JSONObject
    //   540: dup
    //   541: invokespecial 939	org/json/JSONObject:<init>	()V
    //   544: putfield 943	aax:c	Lorg/json/JSONObject;
    //   547: aload_0
    //   548: new 323	org/json/JSONObject
    //   551: dup
    //   552: invokespecial 939	org/json/JSONObject:<init>	()V
    //   555: putfield 945	aax:M	Lorg/json/JSONObject;
    //   558: aload_2
    //   559: ifnull +2034 -> 2593
    //   562: aload_2
    //   563: ldc_w 947
    //   566: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   569: ifnull +2018 -> 2587
    //   572: aload_2
    //   573: ldc_w 947
    //   576: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   579: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   582: ldc_w 317
    //   585: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   588: ifeq +1999 -> 2587
    //   591: aload_2
    //   592: ldc_w 947
    //   595: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   598: checkcast 317	java/lang/String
    //   601: astore 6
    //   603: aload_2
    //   604: ldc_w 949
    //   607: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   610: ifnull +1971 -> 2581
    //   613: aload_2
    //   614: ldc_w 949
    //   617: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   620: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   623: ldc_w 317
    //   626: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   629: ifeq +1952 -> 2581
    //   632: aload_2
    //   633: ldc_w 949
    //   636: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   639: checkcast 317	java/lang/String
    //   642: astore 7
    //   644: aload_2
    //   645: ldc_w 951
    //   648: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   651: ifnull +1922 -> 2573
    //   654: aload_2
    //   655: ldc_w 951
    //   658: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   661: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   664: ldc_w 317
    //   667: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   670: ifeq +1903 -> 2573
    //   673: aload_2
    //   674: ldc_w 951
    //   677: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   680: checkcast 317	java/lang/String
    //   683: astore 8
    //   685: aload_2
    //   686: ldc_w 953
    //   689: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   692: ifnull +1876 -> 2568
    //   695: aload_2
    //   696: ldc_w 953
    //   699: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   702: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   705: ldc_w 317
    //   708: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   711: ifeq +1379 -> 2090
    //   714: aload_2
    //   715: ldc_w 953
    //   718: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   721: checkcast 317	java/lang/String
    //   724: astore_1
    //   725: aload_2
    //   726: ldc_w 955
    //   729: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   732: ifnull +30 -> 762
    //   735: aload_2
    //   736: ldc_w 955
    //   739: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   742: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   745: ldc_w 317
    //   748: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   751: ifeq +11 -> 762
    //   754: aload_2
    //   755: ldc_w 955
    //   758: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   761: pop
    //   762: aload_2
    //   763: ldc_w 957
    //   766: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   769: ifnull +1793 -> 2562
    //   772: aload_2
    //   773: ldc_w 957
    //   776: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   779: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   782: ldc_w 317
    //   785: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   788: ifeq +1774 -> 2562
    //   791: aload_2
    //   792: ldc_w 957
    //   795: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   798: checkcast 317	java/lang/String
    //   801: astore 9
    //   803: aload_2
    //   804: ldc_w 959
    //   807: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   810: ifnull +39 -> 849
    //   813: aload_2
    //   814: ldc_w 959
    //   817: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   820: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   823: ldc_w 875
    //   826: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   829: ifeq +20 -> 849
    //   832: aload_0
    //   833: aload_2
    //   834: ldc_w 959
    //   837: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: checkcast 875	java/lang/Boolean
    //   843: invokevirtual 878	java/lang/Boolean:booleanValue	()Z
    //   846: putfield 233	aax:C	Z
    //   849: aload_2
    //   850: ldc_w 961
    //   853: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   856: ifnull +115 -> 971
    //   859: aload_2
    //   860: ldc_w 961
    //   863: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   866: instanceof 872
    //   869: ifeq +102 -> 971
    //   872: aload_2
    //   873: ldc_w 961
    //   876: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   879: checkcast 872	java/util/HashMap
    //   882: putstatic 963	aax:f	Ljava/util/Map;
    //   885: new 323	org/json/JSONObject
    //   888: dup
    //   889: invokespecial 939	org/json/JSONObject:<init>	()V
    //   892: putstatic 965	aax:g	Lorg/json/JSONObject;
    //   895: getstatic 963	aax:f	Ljava/util/Map;
    //   898: invokeinterface 969 1 0
    //   903: invokeinterface 975 1 0
    //   908: astore 10
    //   910: aload 10
    //   912: invokeinterface 980 1 0
    //   917: ifeq +54 -> 971
    //   920: aload 10
    //   922: invokeinterface 984 1 0
    //   927: checkcast 986	java/util/Map$Entry
    //   930: astore 11
    //   932: getstatic 965	aax:g	Lorg/json/JSONObject;
    //   935: aload 11
    //   937: invokeinterface 989 1 0
    //   942: checkcast 317	java/lang/String
    //   945: aload 11
    //   947: invokeinterface 992 1 0
    //   952: checkcast 317	java/lang/String
    //   955: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   958: pop
    //   959: aload 10
    //   961: invokeinterface 999 1 0
    //   966: goto -56 -> 910
    //   969: astore 10
    //   971: aload_2
    //   972: ldc_w 1001
    //   975: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   978: ifnull +36 -> 1014
    //   981: aload_2
    //   982: ldc_w 1001
    //   985: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   988: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   991: ldc_w 317
    //   994: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   997: ifeq +17 -> 1014
    //   1000: aload_0
    //   1001: aload_2
    //   1002: ldc_w 1001
    //   1005: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1008: checkcast 317	java/lang/String
    //   1011: putfield 1003	aax:r	Ljava/lang/String;
    //   1014: aload 6
    //   1016: astore 10
    //   1018: aload 7
    //   1020: astore 11
    //   1022: aload 8
    //   1024: astore 12
    //   1026: aload_1
    //   1027: astore 13
    //   1029: aload 9
    //   1031: astore 14
    //   1033: aload_2
    //   1034: ldc_w 1005
    //   1037: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1040: ifnull +106 -> 1146
    //   1043: aload 6
    //   1045: astore 10
    //   1047: aload 7
    //   1049: astore 11
    //   1051: aload 8
    //   1053: astore 12
    //   1055: aload_1
    //   1056: astore 13
    //   1058: aload 9
    //   1060: astore 14
    //   1062: aload_2
    //   1063: ldc_w 1005
    //   1066: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1069: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1072: ldc_w 1007
    //   1075: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1078: ifeq +68 -> 1146
    //   1081: aload_2
    //   1082: ldc_w 1005
    //   1085: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1088: checkcast 1007	java/lang/Integer
    //   1091: invokevirtual 1010	java/lang/Integer:intValue	()I
    //   1094: istore_3
    //   1095: iload_3
    //   1096: ifgt +1036 -> 2132
    //   1099: new 343	java/lang/StringBuilder
    //   1102: dup
    //   1103: ldc_w 1012
    //   1106: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1109: iload_3
    //   1110: invokevirtual 583	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1113: ldc_w 1014
    //   1116: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1119: pop
    //   1120: invokestatic 278	abh:a	()V
    //   1123: iconst_1
    //   1124: putstatic 144	aax:k	Z
    //   1127: aload 9
    //   1129: astore 14
    //   1131: aload_1
    //   1132: astore 13
    //   1134: aload 8
    //   1136: astore 12
    //   1138: aload 7
    //   1140: astore 11
    //   1142: aload 6
    //   1144: astore 10
    //   1146: aload 14
    //   1148: ifnull +19 -> 1167
    //   1151: aload 14
    //   1153: invokevirtual 460	java/lang/String:trim	()Ljava/lang/String;
    //   1156: invokevirtual 759	java/lang/String:length	()I
    //   1159: ifeq +8 -> 1167
    //   1162: aload 14
    //   1164: putstatic 135	aax:h	Ljava/lang/String;
    //   1167: aload 13
    //   1169: ifnull +18 -> 1187
    //   1172: aload 13
    //   1174: ldc_w 337
    //   1177: invokevirtual 1017	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1180: ifeq +7 -> 1187
    //   1183: iconst_1
    //   1184: putstatic 190	aax:Z	Z
    //   1187: getstatic 299	aax:b	Landroid/content/Context;
    //   1190: ldc_w 1019
    //   1193: iconst_0
    //   1194: invokevirtual 867	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1197: putstatic 572	aax:ad	Landroid/content/SharedPreferences;
    //   1200: aload 11
    //   1202: ifnull +1008 -> 2210
    //   1205: aload 11
    //   1207: invokevirtual 460	java/lang/String:trim	()Ljava/lang/String;
    //   1210: invokevirtual 759	java/lang/String:length	()I
    //   1213: ifeq +997 -> 2210
    //   1216: aload 11
    //   1218: putstatic 1021	aax:s	Ljava/lang/String;
    //   1221: aload_0
    //   1222: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1225: ldc_w 949
    //   1228: aload 11
    //   1230: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1233: pop
    //   1234: aload_0
    //   1235: getfield 945	aax:M	Lorg/json/JSONObject;
    //   1238: ldc_w 949
    //   1241: aload 11
    //   1243: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1246: pop
    //   1247: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1250: ldc_w 1023
    //   1253: ldc -123
    //   1255: invokeinterface 315 3 0
    //   1260: ldc -123
    //   1262: invokevirtual 321	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1265: ifeq +26 -> 1291
    //   1268: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1271: invokeinterface 421 1 0
    //   1276: ldc_w 1023
    //   1279: aload 11
    //   1281: invokeinterface 427 3 0
    //   1286: invokeinterface 430 1 0
    //   1291: aload 12
    //   1293: invokestatic 1025	aax:g	(Ljava/lang/String;)V
    //   1296: aload_0
    //   1297: getfield 941	aax:L	Lorg/json/JSONObject;
    //   1300: ldc_w 1027
    //   1303: aload_0
    //   1304: invokespecial 1029	aax:T	()Ljava/lang/String;
    //   1307: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1310: pop
    //   1311: aload_0
    //   1312: getfield 941	aax:L	Lorg/json/JSONObject;
    //   1315: ldc_w 1031
    //   1318: ldc_w 1033
    //   1321: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1324: pop
    //   1325: aload_0
    //   1326: getfield 941	aax:L	Lorg/json/JSONObject;
    //   1329: ldc_w 1035
    //   1332: ldc_w 1037
    //   1335: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1338: pop
    //   1339: aload_0
    //   1340: getfield 941	aax:L	Lorg/json/JSONObject;
    //   1343: ldc_w 951
    //   1346: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1349: ldc_w 951
    //   1352: ldc_w 1039
    //   1355: invokeinterface 315 3 0
    //   1360: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1363: pop
    //   1364: aload_0
    //   1365: getfield 945	aax:M	Lorg/json/JSONObject;
    //   1368: ldc_w 951
    //   1371: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1374: ldc_w 951
    //   1377: ldc_w 1039
    //   1380: invokeinterface 315 3 0
    //   1385: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1388: pop
    //   1389: aload_0
    //   1390: getfield 945	aax:M	Lorg/json/JSONObject;
    //   1393: ldc_w 1035
    //   1396: ldc_w 1037
    //   1399: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload_0
    //   1404: getfield 945	aax:M	Lorg/json/JSONObject;
    //   1407: ldc_w 951
    //   1410: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1413: ldc_w 951
    //   1416: ldc_w 1039
    //   1419: invokeinterface 315 3 0
    //   1424: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1427: pop
    //   1428: aload_0
    //   1429: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1432: ldc_w 1041
    //   1435: new 343	java/lang/StringBuilder
    //   1438: dup
    //   1439: ldc_w 1043
    //   1442: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1445: getstatic 137	aax:a	Ljava/lang/String;
    //   1448: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1451: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1454: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1457: pop
    //   1458: aload_0
    //   1459: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1462: ldc_w 1045
    //   1465: ldc_w 1047
    //   1468: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1471: pop
    //   1472: aload_0
    //   1473: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1476: ldc_w 1049
    //   1479: aload_0
    //   1480: getfield 941	aax:L	Lorg/json/JSONObject;
    //   1483: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1486: pop
    //   1487: aload_0
    //   1488: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1491: ldc_w 1051
    //   1494: aload_0
    //   1495: getfield 945	aax:M	Lorg/json/JSONObject;
    //   1498: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1501: pop
    //   1502: aload_0
    //   1503: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1506: ldc_w 1053
    //   1509: ldc_w 1055
    //   1512: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1515: pop
    //   1516: invokestatic 293	java/lang/System:currentTimeMillis	()J
    //   1519: ldc2_w 294
    //   1522: ldiv
    //   1523: putstatic 169	aax:J	J
    //   1526: ldc -123
    //   1528: astore_2
    //   1529: new 1057	android/content/ComponentName
    //   1532: dup
    //   1533: getstatic 299	aax:b	Landroid/content/Context;
    //   1536: ldc_w 1059
    //   1539: invokespecial 1062	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1542: astore_1
    //   1543: getstatic 299	aax:b	Landroid/content/Context;
    //   1546: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1549: aload_1
    //   1550: iconst_0
    //   1551: invokevirtual 1066	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1554: pop
    //   1555: invokestatic 278	abh:a	()V
    //   1558: iconst_0
    //   1559: istore_3
    //   1560: aload_2
    //   1561: astore_1
    //   1562: getstatic 299	aax:b	Landroid/content/Context;
    //   1565: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1568: ldc_w 1068
    //   1571: getstatic 299	aax:b	Landroid/content/Context;
    //   1574: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1577: invokevirtual 1072	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1580: ifge +26 -> 1606
    //   1583: new 343	java/lang/StringBuilder
    //   1586: dup
    //   1587: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   1590: aload_2
    //   1591: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1594: ldc_w 1074
    //   1597: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1600: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1603: astore_1
    //   1604: iconst_1
    //   1605: istore_3
    //   1606: aload_1
    //   1607: astore_2
    //   1608: getstatic 299	aax:b	Landroid/content/Context;
    //   1611: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1614: ldc_w 1076
    //   1617: getstatic 299	aax:b	Landroid/content/Context;
    //   1620: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1623: invokevirtual 1072	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1626: ifge +26 -> 1652
    //   1629: new 343	java/lang/StringBuilder
    //   1632: dup
    //   1633: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   1636: aload_1
    //   1637: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1640: ldc_w 1078
    //   1643: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1646: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1649: astore_2
    //   1650: iconst_1
    //   1651: istore_3
    //   1652: getstatic 299	aax:b	Landroid/content/Context;
    //   1655: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1658: ldc_w 1080
    //   1661: getstatic 299	aax:b	Landroid/content/Context;
    //   1664: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1667: invokevirtual 1072	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1670: ifge +889 -> 2559
    //   1673: new 343	java/lang/StringBuilder
    //   1676: dup
    //   1677: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   1680: aload_2
    //   1681: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1684: ldc_w 1082
    //   1687: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1690: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1693: astore_2
    //   1694: iload 4
    //   1696: istore_3
    //   1697: iload_3
    //   1698: ifeq +13 -> 1711
    //   1701: ldc_w 1084
    //   1704: invokestatic 1086	abh:b	(Ljava/lang/String;)V
    //   1707: aload_2
    //   1708: invokestatic 1086	abh:b	(Ljava/lang/String;)V
    //   1711: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1714: ldc_w 484
    //   1717: ldc -123
    //   1719: invokeinterface 315 3 0
    //   1724: ldc -123
    //   1726: invokevirtual 321	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1729: ifeq +27 -> 1756
    //   1732: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   1735: invokeinterface 421 1 0
    //   1740: ldc_w 484
    //   1743: invokestatic 252	aax:W	()Ljava/lang/String;
    //   1746: invokeinterface 427 3 0
    //   1751: invokeinterface 430 1 0
    //   1756: aload_0
    //   1757: ldc_w 1088
    //   1760: putfield 389	aax:n	Ljava/lang/String;
    //   1763: aload_0
    //   1764: ldc_w 1090
    //   1767: putfield 393	aax:o	Ljava/lang/String;
    //   1770: aload_0
    //   1771: ldc -123
    //   1773: putfield 1092	aax:p	Ljava/lang/String;
    //   1776: getstatic 299	aax:b	Landroid/content/Context;
    //   1779: invokevirtual 857	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1782: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1785: astore_2
    //   1786: aload_2
    //   1787: getstatic 299	aax:b	Landroid/content/Context;
    //   1790: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1793: iconst_0
    //   1794: invokevirtual 1096	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1797: astore_1
    //   1798: aload_1
    //   1799: ifnull +681 -> 2480
    //   1802: aload_2
    //   1803: aload_1
    //   1804: invokevirtual 1100	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1807: astore_1
    //   1808: aload_0
    //   1809: aload_1
    //   1810: checkcast 317	java/lang/String
    //   1813: putfield 389	aax:n	Ljava/lang/String;
    //   1816: new 343	java/lang/StringBuilder
    //   1819: dup
    //   1820: ldc_w 1102
    //   1823: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1826: aload_0
    //   1827: getfield 389	aax:n	Ljava/lang/String;
    //   1830: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1833: pop
    //   1834: invokestatic 278	abh:a	()V
    //   1837: aload_0
    //   1838: new 343	java/lang/StringBuilder
    //   1841: dup
    //   1842: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   1845: getstatic 299	aax:b	Landroid/content/Context;
    //   1848: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1851: getstatic 299	aax:b	Landroid/content/Context;
    //   1854: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1857: iconst_0
    //   1858: invokevirtual 933	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1861: getfield 1105	android/content/pm/PackageInfo:versionCode	I
    //   1864: invokevirtual 583	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1867: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1870: putfield 393	aax:o	Ljava/lang/String;
    //   1873: new 343	java/lang/StringBuilder
    //   1876: dup
    //   1877: ldc_w 1107
    //   1880: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1883: aload_0
    //   1884: getfield 393	aax:o	Ljava/lang/String;
    //   1887: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1890: pop
    //   1891: invokestatic 278	abh:a	()V
    //   1894: aload_0
    //   1895: getstatic 299	aax:b	Landroid/content/Context;
    //   1898: invokevirtual 924	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1901: getstatic 299	aax:b	Landroid/content/Context;
    //   1904: invokevirtual 927	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1907: iconst_0
    //   1908: invokevirtual 933	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1911: getfield 1110	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   1914: putfield 1092	aax:p	Ljava/lang/String;
    //   1917: new 343	java/lang/StringBuilder
    //   1920: dup
    //   1921: ldc_w 1112
    //   1924: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1927: aload_0
    //   1928: getfield 1092	aax:p	Ljava/lang/String;
    //   1931: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1934: pop
    //   1935: invokestatic 278	abh:a	()V
    //   1938: new 18	aax$15
    //   1941: dup
    //   1942: aload_0
    //   1943: invokespecial 1113	aax$15:<init>	(Laax;)V
    //   1946: invokevirtual 333	java/lang/Thread:start	()V
    //   1949: invokestatic 1115	aax:U	()Ljava/lang/String;
    //   1952: pop
    //   1953: aload_0
    //   1954: getfield 943	aax:c	Lorg/json/JSONObject;
    //   1957: ldc_w 1117
    //   1960: invokestatic 1115	aax:U	()Ljava/lang/String;
    //   1963: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1966: pop
    //   1967: getstatic 204	aax:af	Ljava/util/concurrent/ScheduledExecutorService;
    //   1970: aload_0
    //   1971: getfield 240	aax:aj	Ljava/lang/Runnable;
    //   1974: ldc2_w 1118
    //   1977: getstatic 826	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   1980: invokeinterface 831 5 0
    //   1985: pop
    //   1986: new 24	aax$18
    //   1989: dup
    //   1990: aload_0
    //   1991: ldc_w 1055
    //   1994: invokespecial 1122	aax$18:<init>	(Laax;Ljava/lang/String;)V
    //   1997: astore_1
    //   1998: getstatic 204	aax:af	Ljava/util/concurrent/ScheduledExecutorService;
    //   2001: aload_1
    //   2002: ldc2_w 1123
    //   2005: getstatic 826	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2008: invokeinterface 831 5 0
    //   2013: pop
    //   2014: return
    //   2015: astore_1
    //   2016: ldc_w 1126
    //   2019: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2022: aload_1
    //   2023: invokevirtual 1127	java/lang/Exception:printStackTrace	()V
    //   2026: iconst_1
    //   2027: putstatic 184	aax:d	Z
    //   2030: return
    //   2031: ldc_w 1129
    //   2034: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2037: iconst_1
    //   2038: putstatic 184	aax:d	Z
    //   2041: return
    //   2042: astore_1
    //   2043: new 343	java/lang/StringBuilder
    //   2046: dup
    //   2047: ldc_w 1131
    //   2050: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2053: aload_1
    //   2054: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   2057: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2060: pop
    //   2061: invokestatic 278	abh:a	()V
    //   2064: goto -1660 -> 404
    //   2067: new 343	java/lang/StringBuilder
    //   2070: dup
    //   2071: ldc_w 1133
    //   2074: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2077: getstatic 139	aax:i	Z
    //   2080: invokevirtual 897	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2083: pop
    //   2084: invokestatic 278	abh:a	()V
    //   2087: goto -1612 -> 475
    //   2090: aload_2
    //   2091: ldc_w 953
    //   2094: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2097: invokevirtual 556	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2100: ldc_w 875
    //   2103: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2106: ifeq +462 -> 2568
    //   2109: aload_2
    //   2110: ldc_w 953
    //   2113: invokevirtual 873	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2116: checkcast 875	java/lang/Boolean
    //   2119: invokevirtual 878	java/lang/Boolean:booleanValue	()Z
    //   2122: ifeq +446 -> 2568
    //   2125: ldc_w 337
    //   2128: astore_1
    //   2129: goto -1404 -> 725
    //   2132: iload_3
    //   2133: sipush 360
    //   2136: if_icmple +36 -> 2172
    //   2139: new 343	java/lang/StringBuilder
    //   2142: dup
    //   2143: ldc_w 1012
    //   2146: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2149: iload_3
    //   2150: invokevirtual 583	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2153: ldc_w 1135
    //   2156: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2159: pop
    //   2160: invokestatic 278	abh:a	()V
    //   2163: ldc_w 1136
    //   2166: putstatic 142	aax:j	I
    //   2169: goto -1046 -> 1123
    //   2172: iload_3
    //   2173: bipush 60
    //   2175: imul
    //   2176: sipush 1000
    //   2179: imul
    //   2180: putstatic 142	aax:j	I
    //   2183: new 343	java/lang/StringBuilder
    //   2186: dup
    //   2187: ldc_w 1138
    //   2190: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2193: iload_3
    //   2194: invokevirtual 583	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2197: ldc_w 1140
    //   2200: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2203: pop
    //   2204: invokestatic 278	abh:a	()V
    //   2207: goto -1084 -> 1123
    //   2210: aload 10
    //   2212: ifnull +172 -> 2384
    //   2215: aload 10
    //   2217: invokevirtual 460	java/lang/String:trim	()Ljava/lang/String;
    //   2220: invokevirtual 759	java/lang/String:length	()I
    //   2223: ifeq +161 -> 2384
    //   2226: aload_0
    //   2227: getfield 941	aax:L	Lorg/json/JSONObject;
    //   2230: ldc_w 947
    //   2233: aload 10
    //   2235: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2238: pop
    //   2239: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   2242: ldc_w 1023
    //   2245: ldc -123
    //   2247: invokeinterface 315 3 0
    //   2252: ldc -123
    //   2254: invokevirtual 321	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2257: ifeq +26 -> 2283
    //   2260: getstatic 307	aax:ac	Landroid/content/SharedPreferences;
    //   2263: invokeinterface 421 1 0
    //   2268: ldc_w 1023
    //   2271: aload 10
    //   2273: invokeinterface 427 3 0
    //   2278: invokeinterface 430 1 0
    //   2283: aload 11
    //   2285: ifnull +75 -> 2360
    //   2288: aload 11
    //   2290: invokevirtual 460	java/lang/String:trim	()Ljava/lang/String;
    //   2293: invokevirtual 759	java/lang/String:length	()I
    //   2296: ifeq +64 -> 2360
    //   2299: aload 11
    //   2301: putstatic 1021	aax:s	Ljava/lang/String;
    //   2304: aload_0
    //   2305: getfield 943	aax:c	Lorg/json/JSONObject;
    //   2308: ldc_w 949
    //   2311: aload 11
    //   2313: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2316: pop
    //   2317: aload_0
    //   2318: getfield 945	aax:M	Lorg/json/JSONObject;
    //   2321: ldc_w 949
    //   2324: aload 11
    //   2326: invokevirtual 996	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2329: pop
    //   2330: goto -1039 -> 1291
    //   2333: astore_1
    //   2334: new 343	java/lang/StringBuilder
    //   2337: dup
    //   2338: ldc_w 1142
    //   2341: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2344: aload_1
    //   2345: invokevirtual 1143	org/json/JSONException:toString	()Ljava/lang/String;
    //   2348: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2351: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2354: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2357: goto -841 -> 1516
    //   2360: new 343	java/lang/StringBuilder
    //   2363: dup
    //   2364: ldc_w 1145
    //   2367: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2370: aload 10
    //   2372: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2375: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2378: putstatic 1021	aax:s	Ljava/lang/String;
    //   2381: goto -1090 -> 1291
    //   2384: ldc_w 1147
    //   2387: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2390: iconst_1
    //   2391: putstatic 184	aax:d	Z
    //   2394: return
    //   2395: astore_1
    //   2396: new 343	java/lang/StringBuilder
    //   2399: dup
    //   2400: invokespecial 387	java/lang/StringBuilder:<init>	()V
    //   2403: ldc -123
    //   2405: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2408: ldc_w 1149
    //   2411: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2414: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2417: astore_2
    //   2418: iconst_1
    //   2419: istore_3
    //   2420: goto -860 -> 1560
    //   2423: astore_1
    //   2424: new 343	java/lang/StringBuilder
    //   2427: dup
    //   2428: ldc_w 1151
    //   2431: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2434: aload_1
    //   2435: invokevirtual 1152	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2438: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2441: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2444: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2447: aload 15
    //   2449: astore_1
    //   2450: goto -652 -> 1798
    //   2453: astore_1
    //   2454: new 343	java/lang/StringBuilder
    //   2457: dup
    //   2458: ldc_w 1151
    //   2461: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2464: aload_1
    //   2465: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   2468: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2471: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2474: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2477: goto -640 -> 1837
    //   2480: ldc_w 1154
    //   2483: astore_1
    //   2484: goto -676 -> 1808
    //   2487: astore_1
    //   2488: new 343	java/lang/StringBuilder
    //   2491: dup
    //   2492: ldc_w 1156
    //   2495: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2498: aload_1
    //   2499: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   2502: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2505: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2508: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2511: goto -617 -> 1894
    //   2514: astore_1
    //   2515: new 343	java/lang/StringBuilder
    //   2518: dup
    //   2519: ldc_w 1158
    //   2522: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2525: aload_1
    //   2526: invokevirtual 651	java/lang/Exception:toString	()Ljava/lang/String;
    //   2529: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2532: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2535: invokestatic 276	abh:a	(Ljava/lang/String;)V
    //   2538: goto -600 -> 1938
    //   2541: astore_1
    //   2542: getstatic 359	abd:a	Z
    //   2545: ifeq -578 -> 1967
    //   2548: aload_1
    //   2549: invokevirtual 362	org/json/JSONException:printStackTrace	()V
    //   2552: goto -585 -> 1967
    //   2555: astore_1
    //   2556: goto -2031 -> 525
    //   2559: goto -862 -> 1697
    //   2562: aconst_null
    //   2563: astore 9
    //   2565: goto -1762 -> 803
    //   2568: aconst_null
    //   2569: astore_1
    //   2570: goto -1845 -> 725
    //   2573: ldc_w 1039
    //   2576: astore 8
    //   2578: goto -1893 -> 685
    //   2581: aconst_null
    //   2582: astore 7
    //   2584: goto -1940 -> 644
    //   2587: aconst_null
    //   2588: astore 6
    //   2590: goto -1987 -> 603
    //   2593: aconst_null
    //   2594: astore 14
    //   2596: aconst_null
    //   2597: astore 13
    //   2599: ldc_w 1039
    //   2602: astore 12
    //   2604: aconst_null
    //   2605: astore 11
    //   2607: aconst_null
    //   2608: astore 10
    //   2610: goto -1464 -> 1146
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2613	0	this	aax
    //   0	2613	1	paramContext	Context
    //   0	2613	2	paramHashMap	HashMap<String, Object>
    //   1094	1326	3	i1	int
    //   4	1691	4	i2	int
    //   121	9	5	bool	boolean
    //   601	1988	6	str1	String
    //   642	1941	7	str2	String
    //   683	1894	8	str3	String
    //   801	1763	9	str4	String
    //   908	52	10	localIterator	Iterator
    //   969	1	10	localException	Exception
    //   1016	1593	10	str5	String
    //   930	1676	11	localObject1	Object
    //   1024	1579	12	str6	String
    //   1027	1571	13	localContext	Context
    //   1031	1564	14	str7	String
    //   1	2447	15	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   872	910	969	java/lang/Exception
    //   910	966	969	java/lang/Exception
    //   10	17	2015	java/lang/Exception
    //   381	404	2042	java/lang/Exception
    //   1205	1291	2333	org/json/JSONException
    //   1291	1516	2333	org/json/JSONException
    //   2215	2283	2333	org/json/JSONException
    //   2288	2330	2333	org/json/JSONException
    //   2360	2381	2333	org/json/JSONException
    //   2384	2394	2333	org/json/JSONException
    //   1543	1558	2395	android/content/pm/PackageManager$NameNotFoundException
    //   1786	1798	2423	android/content/pm/PackageManager$NameNotFoundException
    //   1776	1786	2453	java/lang/Exception
    //   1786	1798	2453	java/lang/Exception
    //   1802	1808	2453	java/lang/Exception
    //   1808	1837	2453	java/lang/Exception
    //   2424	2447	2453	java/lang/Exception
    //   1837	1894	2487	java/lang/Exception
    //   1894	1938	2514	java/lang/Exception
    //   1953	1967	2541	org/json/JSONException
    //   502	525	2555	java/lang/Exception
  }
  
  private void a(String paramString, Map<String, String> paramMap)
  {
    if ((!paramString.equals("initial")) && (!S) && (U) && (!T))
    {
      T = true;
      this.V.schedule(new TimerTask()
      {
        public final void run()
        {
          if (aax.G()) {
            aax.a();
          }
          aax.H();
        }
      }, 60000L);
    }
    abh.a();
    new StringBuilder("FIRE EVENT*** properties:").append(paramMap);
    abh.a();
    JSONObject localJSONObject1 = new JSONObject();
    label1032:
    int i1;
    do
    {
      JSONObject localJSONObject2;
      for (;;)
      {
        try
        {
          localJSONObject1.put("kochava_app_id", s);
          localJSONObject1.put("kochava_device_id", U());
          localJSONObject1.put("action", paramString);
          localJSONObject1.put("dev_id_strategy", null);
          localJSONObject1.put("last_post_time", 0);
          ac = b.getSharedPreferences("initPrefs", 0);
          localJSONObject1.put("currency", ac.getString("currency", "USD"));
          localJSONObject2 = c(new JSONObject());
          if (paramString.equals("initial"))
          {
            abh.a();
            try
            {
              Object localObject;
              if (((Boolean)ah.get("affinity_group")).booleanValue())
              {
                paramString = new JSONArray();
                localObject = K.iterator();
                if (((Iterator)localObject).hasNext())
                {
                  aaz localAaz = (aaz)((Iterator)localObject).next();
                  JSONObject localJSONObject3 = new JSONObject();
                  localJSONObject3.put("app_name", localAaz.a);
                  localJSONObject3.put("mtime", localAaz.b);
                  localJSONObject3.put("utime", localAaz.c);
                  paramString.put(localJSONObject3);
                  continue;
                }
              }
              if (paramMap == null) {
                break;
              }
            }
            catch (JSONException paramString)
            {
              if (abd.a) {
                paramString.printStackTrace();
              }
              abh.a("event " + paramString);
              return;
              localJSONObject2.put("affinity_group", paramString);
              localJSONObject1.put("sdk_version", "Android20160427" + a);
              if (((Boolean)ah.get("volume")).booleanValue()) {
                localJSONObject1.put("volume", X());
              }
              if (((Boolean)ah.get("bssid")).booleanValue()) {
                localJSONObject2.put("bssid", y);
              }
              if (((Boolean)ah.get("carrier_name")).booleanValue()) {
                localJSONObject2.put("carrier_name", z);
              }
              if (((Boolean)ah.get("adid")).booleanValue()) {
                localJSONObject2.put("adid", x);
              }
              localJSONObject2.put("device", Build.MODEL + "-" + Build.BRAND);
              if (((Boolean)ah.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_h", this.v);
              }
              if (((Boolean)ah.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_w", this.w);
              }
              localJSONObject2.put("package_name", T());
              localJSONObject2.put("app_version", S());
              if (!this.p.equals("")) {
                localJSONObject2.put("app_short_string", this.p);
              }
              if (((Boolean)ah.get("android_id")).booleanValue()) {
                localJSONObject2.put("android_id", this.t);
              }
              localJSONObject2.put("os_version", R());
              localJSONObject2.put("app_limit_tracking", this.C);
              localJSONObject2.put("device_limit_tracking", D);
              paramString = new JSONObject();
              if (Y)
              {
                localObject = Z();
                if (!((String)localObject).equals("[]")) {
                  paramString.put("email", localObject);
                }
              }
              if (paramString.length() > 0) {
                localJSONObject2.put("ids", paramString);
              }
              if (g != null) {
                localJSONObject2.put("identity_link", g);
              }
              if ((this.r != null) && (!this.r.isEmpty())) {
                localJSONObject2.put("clickData", this.r);
              }
              if (((Boolean)ah.get("fb_attribution_id")).booleanValue())
              {
                this.u = a(b.getContentResolver());
                if (this.u != null) {
                  break label1032;
                }
                localJSONObject2.put("fb_attribution_id", "");
              }
              paramString = (WindowManager)b.getSystemService("window");
              localObject = new DisplayMetrics();
              paramString.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
              this.ab = localJSONObject2;
              this.aa = localJSONObject1;
              abh.a();
              return;
            }
            catch (Exception paramString)
            {
              abh.a("Error during fireEvent - Please review stack trace");
              if (abd.a) {
                paramString.printStackTrace();
              }
            }
          }
          paramString = paramMap.entrySet().iterator();
          if (!paramString.hasNext()) {
            break;
          }
          paramMap = (Map.Entry)paramString.next();
          localJSONObject2.put((String)paramMap.getKey(), paramMap.getValue());
          continue;
          localJSONObject2.put("fb_attribution_id", this.u);
        }
        catch (JSONException paramString)
        {
          if (abd.a) {
            paramString.printStackTrace();
          }
          abh.a("event " + paramString);
          return;
        }
      }
      localJSONObject1.put("data", localJSONObject2);
      new StringBuilder("fireEvent with properties: ").append(localJSONObject1);
      abh.a();
      b(true);
      i1 = N.a(localJSONObject1, false, false);
      W = System.currentTimeMillis();
    } while (i1 < 50);
    a();
  }
  
  private static boolean aa()
  {
    long l2 = abe.b * 60 * 1000;
    if ((ac != null) && (ac.getLong("kochava_old_loc_timestamp", 0L) != 0L)) {}
    for (long l1 = ac.getLong("kochava_old_loc_timestamp", 0L);; l1 = 0L)
    {
      if (l1 == 0L) {}
      while (System.currentTimeMillis() - l1 >= l2) {
        return true;
      }
      return false;
    }
  }
  
  private static void b(Exception paramException)
  {
    new Thread()
    {
      public final void run()
      {
        try
        {
          aax.r();
          Object localObject2 = aax.this.getMessage();
          Object localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("message", localObject2);
          ((JSONObject)localObject1).put("os_version", aax.B());
          ((JSONObject)localObject1).put("device", aax.O() + "-" + aax.P());
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("kochava_device_id", aax.w());
          ((JSONObject)localObject2).put("action", "error");
          ((JSONObject)localObject2).put("data", localObject1);
          ((JSONObject)localObject2).put("kochava_app_id", aax.v());
          ((JSONObject)localObject2).put("sdk_version", "Android20160427" + aax.a);
          ((JSONObject)localObject2).put("sdk_protocol", "4");
          new StringBuilder("https log - posting to http://").append(aax.d()).append("/track/kvTracker.php");
          abh.a();
          Object localObject3 = new URL("http://" + aax.d() + "/track/kvTracker.php");
          localObject1 = ((JSONObject)localObject2).toString();
          abh.a();
          localObject2 = (HttpURLConnection)((URL)localObject3).openConnection();
          ((HttpURLConnection)localObject2).setRequestProperty("User-Agent", aax.c().getString("useragent", ""));
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
          abh.a();
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
          abh.a();
        }
        catch (Exception localException)
        {
          abh.a("httpsFail " + localException);
          return;
        }
      }
    }.start();
  }
  
  private void b(boolean paramBoolean)
  {
    if ((ac.getString("initBool", "").equals("false")) && (this.ab != null) && (this.aa != null)) {
      try
      {
        new StringBuilder("Initial properties: ").append(this.ab);
        abh.a();
        new StringBuilder("Initital Oject: ").append(this.aa);
        abh.a();
        if (!ac.getString("initData", "noData").equals("noData"))
        {
          this.ab.put("conversion_type", "gplay");
          this.ab.put("conversion_data", ac.getString("initData", ""));
          new StringBuilder("Got referral, attaching: ").append(ac.getString("initData", ""));
          abh.a();
        }
        for (;;)
        {
          this.aa.put("data", this.ab);
          N.a(this.aa, true, false);
          abh.a();
          ac.edit().putString("initBool", "true").apply();
          if (!paramBoolean) {
            break;
          }
          this.R.cancel();
          return;
          abh.a("Did not get referral data.");
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        abh.a("An error occured during que initial. " + localJSONException);
        if (abd.a) {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  private static JSONObject c(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        paramJSONObject.put("usertime", System.currentTimeMillis() / 1000L);
        paramJSONObject.put("uptime", System.currentTimeMillis() / 1000L - J);
        if (I != 0L) {
          paramJSONObject.put("updelta", System.currentTimeMillis() / 1000L - I);
        }
        for (;;)
        {
          I = System.currentTimeMillis() / 1000L;
          SharedPreferences localSharedPreferences = b.getSharedPreferences("initPrefs", 0);
          ac = localSharedPreferences;
          if (localSharedPreferences.getString("mylat", "").equals("")) {
            break;
          }
          paramJSONObject.put("geo_lat", ac.getString("mylat", ""));
          paramJSONObject.put("geo_lon", ac.getString("mylong", ""));
          return paramJSONObject;
          paramJSONObject.put("updelta", "0");
        }
        return paramJSONObject;
      }
      catch (Exception localException)
      {
        abh.a("Error adding time properties to a JSON object " + localException);
      }
    }
  }
  
  private static void g(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      SharedPreferences localSharedPreferences = b.getSharedPreferences("initPrefs", 0);
      ac = localSharedPreferences;
      localSharedPreferences.edit().putString("currency", paramString).apply();
    }
  }
  
  private static void h(String paramString)
  {
    new Thread()
    {
      public final void run()
      {
        new StringBuilder("Got event ").append(aax.this);
        abh.a();
        Object localObject = new HashMap();
        ((HashMap)localObject).put("state", aax.this);
        abh.a();
        new StringBuilder("FIRE EVENT*** properties:").append(localObject);
        abh.a();
        JSONObject localJSONObject1 = new JSONObject();
        do
        {
          try
          {
            localJSONObject1.put("kochava_app_id", aax.v());
            localJSONObject1.put("kochava_device_id", aax.w());
            localJSONObject1.put("action", "session");
            localJSONObject1.put("dev_id_strategy", aax.E());
            JSONObject localJSONObject2 = aax.b(new JSONObject());
            aax.F();
            localObject = ((HashMap)localObject).entrySet().iterator();
            while (((Iterator)localObject).hasNext())
            {
              Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
              localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
            }
            localJSONException.put("data", localJSONObject2);
          }
          catch (JSONException localJSONException)
          {
            if (abd.a) {
              localJSONException.printStackTrace();
            }
            abh.a("event " + localJSONException);
            return;
          }
          new StringBuilder("fireEvent with properties: ").append(localJSONException);
          abh.a();
        } while (aax.D().a(localJSONException, false, false) < 50);
        aax.a();
      }
    }.start();
  }
}
