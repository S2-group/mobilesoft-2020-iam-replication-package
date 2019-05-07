package com.miniclip.nativeJNI;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;

public class infoTransmitter
{
  static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier()
  {
    public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
    {
      return true;
    }
  };
  private Context mContext;
  private String mDeviceID;
  private JSONObject mSendJSON;
  
  public infoTransmitter(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.mDeviceID = paramString;
    if (((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo() == null) {}
    do
    {
      return;
      paramContext = this.mContext.getSharedPreferences("INFO_TRANSMITTER", 0);
    } while (!paramContext.getBoolean("FIRST_RUN", true));
    paramContext = paramContext.edit();
    paramContext.putBoolean("FIRST_RUN", false);
    paramContext.commit();
    try
    {
      generateJSON();
      send();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void trustAllHosts()
  {
    X509TrustManager local3 = new X509TrustManager()
    {
      public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
        throws CertificateException
      {}
      
      public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
        throws CertificateException
      {}
      
      public X509Certificate[] getAcceptedIssuers()
      {
        return new X509Certificate[0];
      }
    };
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      SecureRandom localSecureRandom = new SecureRandom();
      localSSLContext.init(null, new TrustManager[] { local3 }, localSecureRandom);
      HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  /* Error */
  public void generateJSON()
  {
    // Byte code:
    //   0: aload_0
    //   1: new 129	org/json/JSONObject
    //   4: dup
    //   5: invokespecial 130	org/json/JSONObject:<init>	()V
    //   8: putfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   11: ldc -124
    //   13: astore 24
    //   15: ldc -124
    //   17: astore 25
    //   19: aload 24
    //   21: astore 26
    //   23: aload_0
    //   24: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   27: invokevirtual 136	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   30: aload_0
    //   31: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   34: invokevirtual 140	android/content/Context:getPackageName	()Ljava/lang/String;
    //   37: iconst_0
    //   38: invokevirtual 146	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   41: astore 27
    //   43: aload 25
    //   45: astore 28
    //   47: aload 24
    //   49: astore 26
    //   51: aload 27
    //   53: ifnull +36 -> 89
    //   56: aload 24
    //   58: astore 26
    //   60: aload 27
    //   62: getfield 151	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   65: astore 24
    //   67: aload 24
    //   69: astore 26
    //   71: aload 27
    //   73: getfield 155	android/content/pm/PackageInfo:versionCode	I
    //   76: istore 4
    //   78: iload 4
    //   80: invokestatic 161	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   83: astore 28
    //   85: aload 24
    //   87: astore 26
    //   89: new 163	android/app/ActivityManager$MemoryInfo
    //   92: dup
    //   93: invokespecial 164	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   96: astore 24
    //   98: aload_0
    //   99: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   102: ldc -90
    //   104: invokevirtual 42	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   107: checkcast 168	android/app/ActivityManager
    //   110: aload 24
    //   112: invokevirtual 172	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   115: aload 24
    //   117: getfield 176	android/app/ActivityManager$MemoryInfo:availMem	J
    //   120: ldc2_w 177
    //   123: ldiv
    //   124: lstore 16
    //   126: lconst_0
    //   127: lstore 12
    //   129: lload 12
    //   131: lstore 10
    //   133: new 180	java/io/BufferedReader
    //   136: dup
    //   137: new 182	java/io/InputStreamReader
    //   140: dup
    //   141: new 184	java/io/FileInputStream
    //   144: dup
    //   145: ldc -70
    //   147: invokespecial 189	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   150: invokespecial 192	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   153: sipush 1000
    //   156: invokespecial 195	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   159: astore 24
    //   161: lload 12
    //   163: lstore 10
    //   165: new 197	java/lang/StringBuffer
    //   168: dup
    //   169: invokespecial 198	java/lang/StringBuffer:<init>	()V
    //   172: astore 25
    //   174: lload 12
    //   176: lstore 10
    //   178: aload 24
    //   180: invokevirtual 201	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   183: astore 27
    //   185: aload 27
    //   187: ifnull +1339 -> 1526
    //   190: lload 12
    //   192: lstore 14
    //   194: lload 12
    //   196: lstore 10
    //   198: aload 27
    //   200: invokevirtual 205	java/lang/String:length	()I
    //   203: bipush 9
    //   205: if_icmple +88 -> 293
    //   208: lload 12
    //   210: lstore 14
    //   212: lload 12
    //   214: lstore 10
    //   216: aload 27
    //   218: iconst_0
    //   219: bipush 8
    //   221: invokevirtual 209	java/lang/String:substring	(II)Ljava/lang/String;
    //   224: ldc -45
    //   226: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   229: ifne +64 -> 293
    //   232: lload 12
    //   234: lstore 10
    //   236: aload 27
    //   238: ldc -39
    //   240: invokevirtual 221	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   243: astore 29
    //   245: lload 12
    //   247: lstore 14
    //   249: lload 12
    //   251: lstore 10
    //   253: aload 29
    //   255: arraylength
    //   256: iconst_1
    //   257: if_icmple +36 -> 293
    //   260: lload 12
    //   262: lstore 10
    //   264: aload 29
    //   266: iconst_1
    //   267: aaload
    //   268: ldc -33
    //   270: ldc -124
    //   272: invokevirtual 227	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   275: invokestatic 232	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   278: i2l
    //   279: lstore 12
    //   281: lload 12
    //   283: lstore 10
    //   285: lload 12
    //   287: ldc2_w 233
    //   290: ldiv
    //   291: lstore 14
    //   293: lload 14
    //   295: lstore 10
    //   297: aload 25
    //   299: new 236	java/lang/StringBuilder
    //   302: dup
    //   303: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   306: aload 27
    //   308: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: ldc -13
    //   313: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokevirtual 249	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   322: pop
    //   323: lload 14
    //   325: lstore 12
    //   327: goto -153 -> 174
    //   330: astore 24
    //   332: aload 24
    //   334: invokevirtual 250	java/io/IOException:printStackTrace	()V
    //   337: aload_0
    //   338: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   341: invokevirtual 254	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   344: invokevirtual 260	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   347: getfield 265	android/util/DisplayMetrics:widthPixels	I
    //   350: istore 8
    //   352: aload_0
    //   353: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   356: invokevirtual 254	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   359: invokevirtual 260	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   362: getfield 268	android/util/DisplayMetrics:heightPixels	I
    //   365: istore 9
    //   367: aload_0
    //   368: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   371: invokevirtual 254	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   374: invokevirtual 260	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   377: getfield 272	android/util/DisplayMetrics:density	F
    //   380: fstore_1
    //   381: aload_0
    //   382: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   385: invokevirtual 254	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   388: invokevirtual 260	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   391: getfield 275	android/util/DisplayMetrics:xdpi	F
    //   394: fstore_2
    //   395: aload_0
    //   396: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   399: invokevirtual 254	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   402: invokevirtual 260	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   405: getfield 275	android/util/DisplayMetrics:xdpi	F
    //   408: fstore_3
    //   409: ldc -124
    //   411: astore 25
    //   413: iconst_0
    //   414: istore 6
    //   416: iconst_0
    //   417: istore 5
    //   419: iload 6
    //   421: istore 4
    //   423: aload 25
    //   425: astore 24
    //   427: new 180	java/io/BufferedReader
    //   430: dup
    //   431: new 182	java/io/InputStreamReader
    //   434: dup
    //   435: new 184	java/io/FileInputStream
    //   438: dup
    //   439: ldc_w 277
    //   442: invokespecial 189	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   445: invokespecial 192	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   448: sipush 1000
    //   451: invokespecial 195	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   454: astore 29
    //   456: iload 6
    //   458: istore 4
    //   460: aload 25
    //   462: astore 24
    //   464: new 197	java/lang/StringBuffer
    //   467: dup
    //   468: invokespecial 198	java/lang/StringBuffer:<init>	()V
    //   471: astore 30
    //   473: iload 5
    //   475: istore 4
    //   477: aload 25
    //   479: astore 24
    //   481: aload 29
    //   483: invokevirtual 201	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   486: astore 31
    //   488: aload 31
    //   490: ifnull +1052 -> 1542
    //   493: iload 5
    //   495: istore 7
    //   497: aload 25
    //   499: astore 27
    //   501: iload 5
    //   503: istore 4
    //   505: aload 25
    //   507: astore 24
    //   509: aload 31
    //   511: invokevirtual 205	java/lang/String:length	()I
    //   514: bipush 9
    //   516: if_icmple +127 -> 643
    //   519: iload 5
    //   521: istore 4
    //   523: aload 25
    //   525: astore 24
    //   527: aload 31
    //   529: iconst_0
    //   530: bipush 9
    //   532: invokevirtual 209	java/lang/String:substring	(II)Ljava/lang/String;
    //   535: astore 32
    //   537: iload 5
    //   539: istore 6
    //   541: iload 5
    //   543: istore 4
    //   545: aload 25
    //   547: astore 24
    //   549: aload 32
    //   551: ldc_w 279
    //   554: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   557: ifne +9 -> 566
    //   560: iload 5
    //   562: iconst_1
    //   563: iadd
    //   564: istore 6
    //   566: iload 6
    //   568: istore 7
    //   570: aload 25
    //   572: astore 27
    //   574: iload 6
    //   576: istore 4
    //   578: aload 25
    //   580: astore 24
    //   582: aload 32
    //   584: ldc_w 281
    //   587: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   590: ifne +53 -> 643
    //   593: iload 6
    //   595: istore 4
    //   597: aload 25
    //   599: astore 24
    //   601: aload 31
    //   603: ldc -39
    //   605: invokevirtual 221	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   608: astore 32
    //   610: iload 6
    //   612: istore 7
    //   614: aload 25
    //   616: astore 27
    //   618: iload 6
    //   620: istore 4
    //   622: aload 25
    //   624: astore 24
    //   626: aload 32
    //   628: arraylength
    //   629: iconst_1
    //   630: if_icmple +13 -> 643
    //   633: aload 32
    //   635: iconst_1
    //   636: aaload
    //   637: astore 27
    //   639: iload 6
    //   641: istore 7
    //   643: iload 7
    //   645: istore 4
    //   647: aload 27
    //   649: astore 24
    //   651: aload 30
    //   653: new 236	java/lang/StringBuilder
    //   656: dup
    //   657: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   660: aload 31
    //   662: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   665: ldc -13
    //   667: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: invokevirtual 246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   673: invokevirtual 249	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   676: pop
    //   677: iload 7
    //   679: istore 5
    //   681: aload 27
    //   683: astore 25
    //   685: goto -212 -> 473
    //   688: astore 25
    //   690: aload 25
    //   692: invokevirtual 250	java/io/IOException:printStackTrace	()V
    //   695: aload 24
    //   697: astore 25
    //   699: iload 4
    //   701: istore 5
    //   703: iload 4
    //   705: ifne +6 -> 711
    //   708: iconst_1
    //   709: istore 5
    //   711: iconst_0
    //   712: istore 6
    //   714: iload 6
    //   716: istore 4
    //   718: new 180	java/io/BufferedReader
    //   721: dup
    //   722: new 182	java/io/InputStreamReader
    //   725: dup
    //   726: new 184	java/io/FileInputStream
    //   729: dup
    //   730: ldc_w 283
    //   733: invokespecial 189	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   736: invokespecial 192	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   739: sipush 1000
    //   742: invokespecial 195	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   745: astore 24
    //   747: iload 6
    //   749: istore 4
    //   751: aload 24
    //   753: invokevirtual 201	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   756: astore 27
    //   758: iload 6
    //   760: istore 4
    //   762: aload 24
    //   764: invokevirtual 286	java/io/BufferedReader:close	()V
    //   767: iload 6
    //   769: istore 4
    //   771: aload 27
    //   773: invokestatic 232	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   776: istore 6
    //   778: iload 6
    //   780: istore 4
    //   782: iload 6
    //   784: sipush 1000
    //   787: idiv
    //   788: istore 6
    //   790: iload 6
    //   792: istore 4
    //   794: new 288	android/os/StatFs
    //   797: dup
    //   798: aload_0
    //   799: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   802: invokevirtual 292	android/content/Context:getFilesDir	()Ljava/io/File;
    //   805: invokevirtual 297	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   808: invokespecial 298	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   811: astore 24
    //   813: aload 24
    //   815: invokevirtual 301	android/os/StatFs:getBlockSize	()I
    //   818: i2l
    //   819: lstore 14
    //   821: aload 24
    //   823: invokevirtual 304	android/os/StatFs:getBlockCount	()I
    //   826: i2l
    //   827: lstore 12
    //   829: aload 24
    //   831: invokevirtual 307	android/os/StatFs:getAvailableBlocks	()I
    //   834: i2l
    //   835: lstore 18
    //   837: lload 12
    //   839: lload 14
    //   841: lmul
    //   842: ldc2_w 177
    //   845: ldiv
    //   846: lstore 12
    //   848: lload 18
    //   850: lload 14
    //   852: lmul
    //   853: ldc2_w 177
    //   856: ldiv
    //   857: lstore 14
    //   859: new 288	android/os/StatFs
    //   862: dup
    //   863: invokestatic 312	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   866: invokevirtual 297	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   869: invokespecial 298	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   872: astore 24
    //   874: aload 24
    //   876: invokevirtual 301	android/os/StatFs:getBlockSize	()I
    //   879: i2l
    //   880: lstore 18
    //   882: aload 24
    //   884: invokevirtual 304	android/os/StatFs:getBlockCount	()I
    //   887: i2l
    //   888: lstore 22
    //   890: aload 24
    //   892: invokevirtual 307	android/os/StatFs:getAvailableBlocks	()I
    //   895: i2l
    //   896: lstore 20
    //   898: lload 22
    //   900: lload 18
    //   902: lmul
    //   903: ldc2_w 177
    //   906: ldiv
    //   907: lstore 22
    //   909: lload 20
    //   911: lload 18
    //   913: lmul
    //   914: ldc2_w 177
    //   917: ldiv
    //   918: lstore 18
    //   920: aload_0
    //   921: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   924: ldc 36
    //   926: invokevirtual 42	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   929: checkcast 44	android/net/ConnectivityManager
    //   932: invokevirtual 48	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   935: astore 24
    //   937: iconst_0
    //   938: istore 6
    //   940: ldc -124
    //   942: astore 27
    //   944: aload 24
    //   946: ifnull +13 -> 959
    //   949: iconst_1
    //   950: istore 6
    //   952: aload 24
    //   954: invokevirtual 317	android/net/NetworkInfo:getTypeName	()Ljava/lang/String;
    //   957: astore 27
    //   959: aload_0
    //   960: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   963: ldc_w 319
    //   966: invokevirtual 42	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   969: checkcast 321	android/telephony/TelephonyManager
    //   972: astore 29
    //   974: ldc -124
    //   976: astore 24
    //   978: aload 29
    //   980: ifnull +10 -> 990
    //   983: aload 29
    //   985: invokevirtual 324	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   988: astore 24
    //   990: aload 24
    //   992: astore 29
    //   994: aload 24
    //   996: ldc -124
    //   998: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   1001: ifne +11 -> 1012
    //   1004: invokestatic 330	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1007: invokevirtual 333	java/util/Locale:getCountry	()Ljava/lang/String;
    //   1010: astore 29
    //   1012: aload_0
    //   1013: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1016: ldc_w 335
    //   1019: aload_0
    //   1020: getfield 34	com/miniclip/nativeJNI/infoTransmitter:mDeviceID	Ljava/lang/String;
    //   1023: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1026: pop
    //   1027: aload_0
    //   1028: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1031: ldc_w 341
    //   1034: aload 26
    //   1036: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1039: pop
    //   1040: aload_0
    //   1041: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1044: ldc_w 343
    //   1047: aload 28
    //   1049: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1052: pop
    //   1053: aload_0
    //   1054: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1057: ldc_w 345
    //   1060: aload_0
    //   1061: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   1064: invokevirtual 140	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1067: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1070: pop
    //   1071: aload_0
    //   1072: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1075: ldc_w 347
    //   1078: getstatic 352	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   1081: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1084: pop
    //   1085: aload_0
    //   1086: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1089: ldc_w 354
    //   1092: getstatic 359	android/os/Build:DISPLAY	Ljava/lang/String;
    //   1095: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1098: pop
    //   1099: aload_0
    //   1100: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1103: ldc_w 361
    //   1106: getstatic 364	android/os/Build:MODEL	Ljava/lang/String;
    //   1109: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1112: pop
    //   1113: aload_0
    //   1114: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1117: ldc_w 366
    //   1120: lload 10
    //   1122: invokestatic 369	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1125: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1128: pop
    //   1129: aload_0
    //   1130: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1133: ldc_w 371
    //   1136: lload 16
    //   1138: invokestatic 369	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1141: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1144: pop
    //   1145: aload_0
    //   1146: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1149: ldc_w 373
    //   1152: iload 8
    //   1154: invokestatic 161	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   1157: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1160: pop
    //   1161: aload_0
    //   1162: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1165: ldc_w 375
    //   1168: iload 9
    //   1170: invokestatic 161	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   1173: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1176: pop
    //   1177: aload_0
    //   1178: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1181: ldc_w 377
    //   1184: fload_1
    //   1185: invokestatic 380	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   1188: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1191: pop
    //   1192: aload_0
    //   1193: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1196: ldc_w 382
    //   1199: fload_2
    //   1200: invokestatic 380	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   1203: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1206: pop
    //   1207: aload_0
    //   1208: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1211: ldc_w 384
    //   1214: fload_3
    //   1215: invokestatic 380	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   1218: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1221: pop
    //   1222: aload_0
    //   1223: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1226: ldc_w 386
    //   1229: iload 4
    //   1231: invokestatic 161	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   1234: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1237: pop
    //   1238: aload_0
    //   1239: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1242: ldc_w 388
    //   1245: aload 25
    //   1247: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1250: pop
    //   1251: aload_0
    //   1252: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1255: ldc_w 390
    //   1258: iload 5
    //   1260: invokestatic 161	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   1263: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1266: pop
    //   1267: aload_0
    //   1268: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1271: ldc_w 392
    //   1274: lload 12
    //   1276: invokestatic 369	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1279: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1282: pop
    //   1283: aload_0
    //   1284: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1287: ldc_w 394
    //   1290: lload 14
    //   1292: invokestatic 369	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1295: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1298: pop
    //   1299: aload_0
    //   1300: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1303: ldc_w 396
    //   1306: lload 22
    //   1308: invokestatic 369	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1311: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1314: pop
    //   1315: aload_0
    //   1316: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1319: ldc_w 398
    //   1322: lload 18
    //   1324: invokestatic 369	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1327: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1330: pop
    //   1331: aload_0
    //   1332: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1335: ldc_w 400
    //   1338: iload 6
    //   1340: invokestatic 161	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   1343: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1346: pop
    //   1347: aload_0
    //   1348: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1351: ldc_w 402
    //   1354: aload 27
    //   1356: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1359: pop
    //   1360: aload_0
    //   1361: getfield 89	com/miniclip/nativeJNI/infoTransmitter:mSendJSON	Lorg/json/JSONObject;
    //   1364: ldc_w 404
    //   1367: aload 29
    //   1369: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1372: pop
    //   1373: new 406	org/json/JSONArray
    //   1376: dup
    //   1377: invokespecial 407	org/json/JSONArray:<init>	()V
    //   1380: astore 24
    //   1382: aload_0
    //   1383: getfield 32	com/miniclip/nativeJNI/infoTransmitter:mContext	Landroid/content/Context;
    //   1386: invokevirtual 136	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1389: iconst_0
    //   1390: invokevirtual 411	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   1393: invokeinterface 417 1 0
    //   1398: astore 25
    //   1400: aload 25
    //   1402: invokeinterface 422 1 0
    //   1407: ifeq +118 -> 1525
    //   1410: aload 25
    //   1412: invokeinterface 426 1 0
    //   1417: checkcast 148	android/content/pm/PackageInfo
    //   1420: astore 26
    //   1422: aload 26
    //   1424: getfield 429	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1427: ldc_w 431
    //   1430: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   1433: ifeq -33 -> 1400
    //   1436: aload 26
    //   1438: getfield 429	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1441: invokevirtual 205	java/lang/String:length	()I
    //   1444: bipush 14
    //   1446: if_icmple -46 -> 1400
    //   1449: aload 26
    //   1451: getfield 429	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1454: iconst_0
    //   1455: bipush 12
    //   1457: invokevirtual 209	java/lang/String:substring	(II)Ljava/lang/String;
    //   1460: ldc_w 433
    //   1463: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   1466: ifeq -66 -> 1400
    //   1469: aload 26
    //   1471: getfield 429	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1474: iconst_0
    //   1475: bipush 11
    //   1477: invokevirtual 209	java/lang/String:substring	(II)Ljava/lang/String;
    //   1480: ldc_w 435
    //   1483: invokevirtual 215	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   1486: ifeq -86 -> 1400
    //   1489: new 129	org/json/JSONObject
    //   1492: dup
    //   1493: invokespecial 130	org/json/JSONObject:<init>	()V
    //   1496: astore 27
    //   1498: aload 27
    //   1500: ldc_w 437
    //   1503: aload 26
    //   1505: getfield 429	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1508: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1511: pop
    //   1512: aload 24
    //   1514: aload 27
    //   1516: invokevirtual 440	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1519: pop
    //   1520: goto -120 -> 1400
    //   1523: astore 24
    //   1525: return
    //   1526: lload 12
    //   1528: lstore 10
    //   1530: aload 24
    //   1532: invokevirtual 286	java/io/BufferedReader:close	()V
    //   1535: lload 12
    //   1537: lstore 10
    //   1539: goto -1202 -> 337
    //   1542: iload 5
    //   1544: istore 4
    //   1546: aload 25
    //   1548: astore 24
    //   1550: aload 29
    //   1552: invokevirtual 286	java/io/BufferedReader:close	()V
    //   1555: iload 5
    //   1557: istore 4
    //   1559: goto -860 -> 699
    //   1562: astore 24
    //   1564: aload 24
    //   1566: invokevirtual 250	java/io/IOException:printStackTrace	()V
    //   1569: goto -775 -> 794
    //   1572: astore 24
    //   1574: aload 25
    //   1576: astore 28
    //   1578: goto -1489 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1581	0	this	infoTransmitter
    //   380	805	1	f1	float
    //   394	806	2	f2	float
    //   408	807	3	f3	float
    //   76	1482	4	i	int
    //   417	1139	5	j	int
    //   414	925	6	k	int
    //   495	183	7	m	int
    //   350	803	8	n	int
    //   365	804	9	i1	int
    //   131	1407	10	l1	long
    //   127	1409	12	l2	long
    //   192	1099	14	l3	long
    //   124	1013	16	l4	long
    //   835	488	18	l5	long
    //   896	14	20	l6	long
    //   888	419	22	l7	long
    //   13	166	24	localObject1	Object
    //   330	3	24	localIOException1	IOException
    //   425	1088	24	localObject2	Object
    //   1523	8	24	localJSONException	org.json.JSONException
    //   1548	1	24	localObject3	Object
    //   1562	3	24	localIOException2	IOException
    //   1572	1	24	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   17	667	25	localObject4	Object
    //   688	3	25	localIOException3	IOException
    //   697	878	25	localObject5	Object
    //   21	1483	26	localObject6	Object
    //   41	1474	27	localObject7	Object
    //   45	1532	28	localObject8	Object
    //   243	1308	29	localObject9	Object
    //   471	181	30	localStringBuffer	StringBuffer
    //   486	175	31	str	String
    //   535	99	32	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   133	161	330	java/io/IOException
    //   165	174	330	java/io/IOException
    //   178	185	330	java/io/IOException
    //   198	208	330	java/io/IOException
    //   216	232	330	java/io/IOException
    //   236	245	330	java/io/IOException
    //   253	260	330	java/io/IOException
    //   264	281	330	java/io/IOException
    //   285	293	330	java/io/IOException
    //   297	323	330	java/io/IOException
    //   1530	1535	330	java/io/IOException
    //   427	456	688	java/io/IOException
    //   464	473	688	java/io/IOException
    //   481	488	688	java/io/IOException
    //   509	519	688	java/io/IOException
    //   527	537	688	java/io/IOException
    //   549	560	688	java/io/IOException
    //   582	593	688	java/io/IOException
    //   601	610	688	java/io/IOException
    //   626	633	688	java/io/IOException
    //   651	677	688	java/io/IOException
    //   1550	1555	688	java/io/IOException
    //   23	43	1523	org/json/JSONException
    //   60	67	1523	org/json/JSONException
    //   71	78	1523	org/json/JSONException
    //   89	126	1523	org/json/JSONException
    //   133	161	1523	org/json/JSONException
    //   165	174	1523	org/json/JSONException
    //   178	185	1523	org/json/JSONException
    //   198	208	1523	org/json/JSONException
    //   216	232	1523	org/json/JSONException
    //   236	245	1523	org/json/JSONException
    //   253	260	1523	org/json/JSONException
    //   264	281	1523	org/json/JSONException
    //   285	293	1523	org/json/JSONException
    //   297	323	1523	org/json/JSONException
    //   332	337	1523	org/json/JSONException
    //   337	409	1523	org/json/JSONException
    //   427	456	1523	org/json/JSONException
    //   464	473	1523	org/json/JSONException
    //   481	488	1523	org/json/JSONException
    //   509	519	1523	org/json/JSONException
    //   527	537	1523	org/json/JSONException
    //   549	560	1523	org/json/JSONException
    //   582	593	1523	org/json/JSONException
    //   601	610	1523	org/json/JSONException
    //   626	633	1523	org/json/JSONException
    //   651	677	1523	org/json/JSONException
    //   690	695	1523	org/json/JSONException
    //   718	747	1523	org/json/JSONException
    //   751	758	1523	org/json/JSONException
    //   762	767	1523	org/json/JSONException
    //   771	778	1523	org/json/JSONException
    //   782	790	1523	org/json/JSONException
    //   794	937	1523	org/json/JSONException
    //   952	959	1523	org/json/JSONException
    //   959	974	1523	org/json/JSONException
    //   983	990	1523	org/json/JSONException
    //   994	1012	1523	org/json/JSONException
    //   1012	1400	1523	org/json/JSONException
    //   1400	1520	1523	org/json/JSONException
    //   1530	1535	1523	org/json/JSONException
    //   1550	1555	1523	org/json/JSONException
    //   1564	1569	1523	org/json/JSONException
    //   718	747	1562	java/io/IOException
    //   751	758	1562	java/io/IOException
    //   762	767	1562	java/io/IOException
    //   771	778	1562	java/io/IOException
    //   782	790	1562	java/io/IOException
    //   23	43	1572	android/content/pm/PackageManager$NameNotFoundException
    //   60	67	1572	android/content/pm/PackageManager$NameNotFoundException
    //   71	78	1572	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public void send()
  {
    new Thread()
    {
      public void run()
      {
        Object localObject2 = infoTransmitter.this.mSendJSON.toString();
        try
        {
          Object localObject1 = new URL("https://ftp.miniclippt.com/submit_stats.php");
          infoTransmitter.access$100();
          Object localObject3 = (HttpsURLConnection)((URL)localObject1).openConnection();
          ((HttpsURLConnection)localObject3).setHostnameVerifier(infoTransmitter.DO_NOT_VERIFY);
          ((HttpsURLConnection)localObject3).setConnectTimeout(2000);
          ((HttpsURLConnection)localObject3).setDoOutput(true);
          localObject1 = new OutputStreamWriter(((HttpsURLConnection)localObject3).getOutputStream());
          ((OutputStreamWriter)localObject1).write((String)localObject2);
          ((OutputStreamWriter)localObject1).flush();
          localObject2 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject3).getInputStream()));
          localObject3 = new StringBuffer();
          for (;;)
          {
            String str = ((BufferedReader)localObject2).readLine();
            if (str == null) {
              break;
            }
            ((StringBuffer)localObject3).append(str);
          }
          localIOException.close();
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          return;
        }
        ((BufferedReader)localObject2).close();
      }
    }.start();
  }
}
