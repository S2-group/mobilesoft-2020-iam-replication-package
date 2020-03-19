package com.paypal.android.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.paypal.android.MECL.PayPal;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class c
{
  private HttpPost a;
  private DefaultHttpClient b = null;
  private int c = -1;
  private int d = -1;
  private Hashtable<String, Object> e;
  private Thread f = new d(this);
  
  public c() {}
  
  /* Error */
  private String a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 44	com/paypal/android/a/c:c	(Ljava/lang/String;)V
    //   4: aload_0
    //   5: getfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   8: ifnonnull +14 -> 22
    //   11: aload_0
    //   12: new 46	org/apache/http/impl/client/DefaultHttpClient
    //   15: dup
    //   16: invokespecial 47	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   19: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   22: aload_0
    //   23: new 49	org/apache/http/client/methods/HttpPost
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 51	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   31: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   34: aload_2
    //   35: invokestatic 56	com/paypal/android/a/c:b	()Ljava/lang/String;
    //   38: invokevirtual 62	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   41: ifeq +46 -> 87
    //   44: new 64	java/util/ArrayList
    //   47: dup
    //   48: invokespecial 65	java/util/ArrayList:<init>	()V
    //   51: astore_2
    //   52: aload_2
    //   53: new 67	org/apache/http/message/BasicNameValuePair
    //   56: dup
    //   57: ldc 69
    //   59: aload_1
    //   60: invokespecial 72	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   63: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   66: pop
    //   67: aload_0
    //   68: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   71: new 78	org/apache/http/client/entity/UrlEncodedFormEntity
    //   74: dup
    //   75: aload_2
    //   76: ldc 80
    //   78: invokespecial 83	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   81: invokevirtual 87	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   84: goto +267 -> 351
    //   87: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   90: invokevirtual 97	com/paypal/android/MECL/PayPal:getServer	()I
    //   93: istore_3
    //   94: iload_3
    //   95: ifeq +648 -> 743
    //   98: iload_3
    //   99: iconst_2
    //   100: if_icmpeq +636 -> 736
    //   103: ldc 99
    //   105: astore 5
    //   107: goto +3 -> 110
    //   110: aload_2
    //   111: aload 5
    //   113: invokevirtual 62	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   116: ifeq +496 -> 612
    //   119: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   122: invokevirtual 97	com/paypal/android/MECL/PayPal:getServer	()I
    //   125: iconst_3
    //   126: if_icmpne +27 -> 153
    //   129: aload_0
    //   130: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   133: astore 6
    //   135: ldc 101
    //   137: astore_2
    //   138: ldc 103
    //   140: astore 5
    //   142: aload 6
    //   144: aload_2
    //   145: aload 5
    //   147: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: goto +41 -> 191
    //   153: aload_0
    //   154: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   157: ldc 108
    //   159: ldc 110
    //   161: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   164: aload_0
    //   165: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   168: ldc 112
    //   170: ldc 110
    //   172: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   175: aload_0
    //   176: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   179: astore 6
    //   181: ldc 114
    //   183: astore_2
    //   184: ldc 110
    //   186: astore 5
    //   188: goto -46 -> 142
    //   191: aload_0
    //   192: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   195: ldc 116
    //   197: ldc 118
    //   199: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   202: aload_0
    //   203: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   206: ldc 120
    //   208: invokestatic 122	com/paypal/android/a/c:d	()Ljava/lang/String;
    //   211: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   214: aload_0
    //   215: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   218: ldc 124
    //   220: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   223: invokevirtual 127	com/paypal/android/MECL/PayPal:getAppID	()Ljava/lang/String;
    //   226: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   229: aload_0
    //   230: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   233: ldc -127
    //   235: aload_0
    //   236: getfield 131	com/paypal/android/a/c:e	Ljava/util/Hashtable;
    //   239: ldc -123
    //   241: invokevirtual 139	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   244: checkcast 58	java/lang/String
    //   247: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   250: aload_0
    //   251: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   254: astore_2
    //   255: new 141	java/lang/StringBuilder
    //   258: dup
    //   259: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   262: astore 5
    //   264: aload 5
    //   266: ldc -112
    //   268: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: aload 5
    //   274: invokestatic 151	com/paypal/android/MECL/PayPal:getVersion	()Ljava/lang/String;
    //   277: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: pop
    //   281: aload_2
    //   282: ldc -103
    //   284: aload 5
    //   286: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   292: aload_0
    //   293: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   296: ldc -98
    //   298: ldc -96
    //   300: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   303: aload_0
    //   304: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   307: ldc -94
    //   309: ldc -96
    //   311: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   314: aload_0
    //   315: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   318: ldc -92
    //   320: ldc -90
    //   322: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: aload_0
    //   326: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   329: ldc -88
    //   331: ldc -86
    //   333: invokevirtual 106	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: aload_0
    //   337: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   340: new 172	org/apache/http/entity/StringEntity
    //   343: dup
    //   344: aload_1
    //   345: invokespecial 173	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
    //   348: invokevirtual 87	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   351: aload_0
    //   352: getfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   355: aload_0
    //   356: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   359: invokevirtual 177	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   362: astore_1
    //   363: aload_1
    //   364: invokeinterface 183 1 0
    //   369: astore 6
    //   371: aload_1
    //   372: invokeinterface 187 1 0
    //   377: astore_1
    //   378: iconst_0
    //   379: istore_3
    //   380: iconst_0
    //   381: istore 4
    //   383: aload_1
    //   384: ifnull +45 -> 429
    //   387: iconst_0
    //   388: istore_3
    //   389: iload 4
    //   391: aload_1
    //   392: arraylength
    //   393: if_icmpge +366 -> 759
    //   396: aload_1
    //   397: iload 4
    //   399: aaload
    //   400: invokeinterface 192 1 0
    //   405: ldc -62
    //   407: invokevirtual 198	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   410: ifne +340 -> 750
    //   413: aload_1
    //   414: iload 4
    //   416: aaload
    //   417: invokeinterface 201 1 0
    //   422: invokestatic 206	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   425: istore_3
    //   426: goto +324 -> 750
    //   429: aload 6
    //   431: invokeinterface 212 1 0
    //   436: astore 5
    //   438: new 214	java/io/DataInputStream
    //   441: dup
    //   442: aload 5
    //   444: invokespecial 217	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   447: astore_1
    //   448: iload_3
    //   449: istore 4
    //   451: aload 5
    //   453: invokevirtual 222	java/io/InputStream:available	()I
    //   456: iload_3
    //   457: if_icmple +10 -> 467
    //   460: aload 5
    //   462: invokevirtual 222	java/io/InputStream:available	()I
    //   465: istore 4
    //   467: iload 4
    //   469: istore_3
    //   470: aload_1
    //   471: invokevirtual 223	java/io/DataInputStream:available	()I
    //   474: iload 4
    //   476: if_icmple +8 -> 484
    //   479: aload_1
    //   480: invokevirtual 223	java/io/DataInputStream:available	()I
    //   483: istore_3
    //   484: iload_3
    //   485: ifeq +26 -> 511
    //   488: iload_3
    //   489: newarray byte
    //   491: astore_2
    //   492: aload_1
    //   493: aload_2
    //   494: invokevirtual 227	java/io/DataInputStream:readFully	([B)V
    //   497: new 58	java/lang/String
    //   500: dup
    //   501: aload_2
    //   502: ldc 80
    //   504: invokespecial 230	java/lang/String:<init>	([BLjava/lang/String;)V
    //   507: astore_2
    //   508: goto +17 -> 525
    //   511: new 58	java/lang/String
    //   514: dup
    //   515: aload_1
    //   516: invokestatic 233	com/paypal/android/a/c:a	(Ljava/io/InputStream;)[B
    //   519: ldc 80
    //   521: invokespecial 230	java/lang/String:<init>	([BLjava/lang/String;)V
    //   524: astore_2
    //   525: aload 6
    //   527: ifnull +10 -> 537
    //   530: aload 6
    //   532: invokeinterface 236 1 0
    //   537: aload_1
    //   538: invokevirtual 239	java/io/DataInputStream:close	()V
    //   541: aload 5
    //   543: invokevirtual 240	java/io/InputStream:close	()V
    //   546: aload_0
    //   547: aconst_null
    //   548: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   551: aload_0
    //   552: aconst_null
    //   553: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   556: aload_2
    //   557: invokestatic 44	com/paypal/android/a/c:c	(Ljava/lang/String;)V
    //   560: aload_1
    //   561: invokevirtual 239	java/io/DataInputStream:close	()V
    //   564: aload 5
    //   566: ifnull +8 -> 574
    //   569: aload 5
    //   571: invokevirtual 240	java/io/InputStream:close	()V
    //   574: aload_0
    //   575: aconst_null
    //   576: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   579: aload_0
    //   580: aconst_null
    //   581: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   584: aload_2
    //   585: areturn
    //   586: astore_2
    //   587: goto +10 -> 597
    //   590: astore_2
    //   591: goto +12 -> 603
    //   594: astore_2
    //   595: aconst_null
    //   596: astore_1
    //   597: goto +85 -> 682
    //   600: astore_2
    //   601: aconst_null
    //   602: astore_1
    //   603: aload_2
    //   604: astore 6
    //   606: aload 5
    //   608: astore_2
    //   609: goto +32 -> 641
    //   612: aload_0
    //   613: aconst_null
    //   614: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   617: aload_0
    //   618: aconst_null
    //   619: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   622: ldc -14
    //   624: areturn
    //   625: astore_2
    //   626: aconst_null
    //   627: astore 5
    //   629: aload 5
    //   631: astore_1
    //   632: goto +50 -> 682
    //   635: astore 6
    //   637: aconst_null
    //   638: astore_2
    //   639: aload_2
    //   640: astore_1
    //   641: aload 6
    //   643: invokevirtual 245	java/lang/Throwable:printStackTrace	()V
    //   646: aload_1
    //   647: ifnull +7 -> 654
    //   650: aload_1
    //   651: invokevirtual 239	java/io/DataInputStream:close	()V
    //   654: aload_2
    //   655: ifnull +7 -> 662
    //   658: aload_2
    //   659: invokevirtual 240	java/io/InputStream:close	()V
    //   662: aload_0
    //   663: aconst_null
    //   664: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   667: aload_0
    //   668: aconst_null
    //   669: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   672: aconst_null
    //   673: areturn
    //   674: astore 6
    //   676: aload_2
    //   677: astore 5
    //   679: aload 6
    //   681: astore_2
    //   682: aload_1
    //   683: ifnull +7 -> 690
    //   686: aload_1
    //   687: invokevirtual 239	java/io/DataInputStream:close	()V
    //   690: aload 5
    //   692: ifnull +8 -> 700
    //   695: aload 5
    //   697: invokevirtual 240	java/io/InputStream:close	()V
    //   700: aload_0
    //   701: aconst_null
    //   702: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   705: aload_0
    //   706: aconst_null
    //   707: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   710: aload_2
    //   711: athrow
    //   712: astore_1
    //   713: goto -149 -> 564
    //   716: astore_1
    //   717: goto -143 -> 574
    //   720: astore_1
    //   721: goto -67 -> 654
    //   724: astore_1
    //   725: goto -63 -> 662
    //   728: astore_1
    //   729: goto -39 -> 690
    //   732: astore_1
    //   733: goto -33 -> 700
    //   736: ldc -9
    //   738: astore 5
    //   740: goto -630 -> 110
    //   743: ldc -7
    //   745: astore 5
    //   747: goto -637 -> 110
    //   750: iload 4
    //   752: iconst_1
    //   753: iadd
    //   754: istore 4
    //   756: goto -367 -> 389
    //   759: goto -330 -> 429
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	762	0	this	c
    //   0	762	1	paramString1	String
    //   0	762	2	paramString2	String
    //   93	396	3	i	int
    //   381	374	4	j	int
    //   105	641	5	localObject1	Object
    //   133	472	6	localObject2	Object
    //   635	7	6	localThrowable	Throwable
    //   674	6	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   451	467	586	finally
    //   470	484	586	finally
    //   488	508	586	finally
    //   511	525	586	finally
    //   530	537	586	finally
    //   537	560	586	finally
    //   451	467	590	java/lang/Throwable
    //   470	484	590	java/lang/Throwable
    //   488	508	590	java/lang/Throwable
    //   511	525	590	java/lang/Throwable
    //   530	537	590	java/lang/Throwable
    //   537	560	590	java/lang/Throwable
    //   438	448	594	finally
    //   438	448	600	java/lang/Throwable
    //   4	22	625	finally
    //   22	84	625	finally
    //   87	94	625	finally
    //   110	135	625	finally
    //   142	150	625	finally
    //   153	181	625	finally
    //   191	351	625	finally
    //   351	378	625	finally
    //   389	396	625	finally
    //   396	426	625	finally
    //   429	438	625	finally
    //   4	22	635	java/lang/Throwable
    //   22	84	635	java/lang/Throwable
    //   87	94	635	java/lang/Throwable
    //   110	135	635	java/lang/Throwable
    //   142	150	635	java/lang/Throwable
    //   153	181	635	java/lang/Throwable
    //   191	351	635	java/lang/Throwable
    //   351	378	635	java/lang/Throwable
    //   389	396	635	java/lang/Throwable
    //   396	426	635	java/lang/Throwable
    //   429	438	635	java/lang/Throwable
    //   641	646	674	finally
    //   560	564	712	java/lang/Exception
    //   569	574	716	java/lang/Exception
    //   650	654	720	java/lang/Exception
    //   658	662	724	java/lang/Exception
    //   686	690	728	java/lang/Exception
    //   695	700	732	java/lang/Exception
  }
  
  public static void a()
  {
    if (PayPal._networkHandler == null) {
      PayPal._networkHandler = new c();
    }
    if (PayPal._networkHandler.e == null) {
      PayPal._networkHandler.e = new Hashtable();
    }
    if (PayPal.getInstance().getServer() == 2)
    {
      PayPal._networkHandler.e.put("PayButtonEnable", "true");
      PayPal.getInstance().setLibraryInitialized(true);
    }
    if (!PayPal._networkHandler.f.isAlive()) {
      PayPal._networkHandler.f.start();
    }
  }
  
  private static boolean a(String paramString)
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "560027";
    arrayOfString[1] = "580022";
    arrayOfString[2] = "580023";
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (arrayOfString[i].equals(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static boolean a(String paramString, Hashtable<String, Object> paramHashtable)
  {
    for (;;)
    {
      Object localObject1;
      int k;
      int m;
      try
      {
        NodeList localNodeList1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("MEPDeviceInterrogationResponseType");
        i = 0;
        if (i < localNodeList1.getLength())
        {
          NodeList localNodeList2 = localNodeList1.item(i).getChildNodes();
          j = 0;
          if (j < localNodeList2.getLength())
          {
            localObject1 = localNodeList2.item(j);
            paramString = ((Node)localObject1).getNodeName();
            if (paramString.compareTo("PayButtonEnable") == 0)
            {
              localObject1 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
              paramHashtable.put(paramString, localObject1);
              if (((String)localObject1).compareToIgnoreCase("true") != 0) {
                Log.e("Error", "Authentication failed, button not enabled.");
              }
            }
            else if (paramString.compareTo("EncryptionDetails") == 0)
            {
              localObject1 = ((Node)localObject1).getChildNodes();
              k = 0;
              if (k < ((NodeList)localObject1).getLength())
              {
                localObject2 = ((NodeList)localObject1).item(k);
                localObject3 = ((Node)localObject2).getNodeName();
                if ((((Node)localObject2).getChildNodes().getLength() <= 0) || (((String)localObject3).compareTo("Type") != 0)) {
                  break label714;
                }
                paramHashtable.put(paramString, ((Node)localObject2).getChildNodes().item(0).getNodeValue());
                break label714;
              }
            }
            else if (paramString.compareTo("DeviceReferenceToken") == 0)
            {
              localObject1 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
              paramHashtable.put(paramString, localObject1);
            }
          }
        }
      }
      catch (Exception paramString)
      {
        int i;
        int j;
        Object localObject2;
        Object localObject3;
        paramString.printStackTrace();
        return false;
      }
      try
      {
        try
        {
          paramString = PayPal.getInstance().getParentContext().openFileOutput("DeviceReferenceToken", 2);
          paramString.write(((String)localObject1).getBytes());
          paramString.flush();
          paramString.close();
        }
        catch (FileNotFoundException paramString)
        {
          paramString.printStackTrace();
        }
        if (paramString.compareTo("DeviceAuthDetails") == 0)
        {
          localObject2 = ((Node)localObject1).getChildNodes();
          k = 0;
          if (k < ((NodeList)localObject2).getLength())
          {
            paramString = ((NodeList)localObject2).item(k);
            localObject1 = paramString.getNodeName();
            if (paramString.getChildNodes().getLength() <= 0) {
              break label735;
            }
            localObject3 = paramString.getChildNodes().item(0).getNodeValue();
            if (((String)localObject1).compareTo("UserName") == 0)
            {
              PayPal.getInstance().setAccountName((String)localObject3);
              break label735;
            }
            if (((String)localObject1).compareTo("Email") == 0)
            {
              PayPal.getInstance().setAccountEmail((String)localObject3);
              break label735;
            }
            if (((String)localObject1).compareTo("Phone") == 0)
            {
              localObject3 = paramString.getChildNodes();
              paramString = "";
              m = 0;
              if (m < ((NodeList)localObject3).getLength())
              {
                localObject1 = paramString;
                if (((NodeList)localObject3).item(m).getChildNodes().getLength() <= 0) {
                  break label723;
                }
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append(paramString);
                ((StringBuilder)localObject1).append(((NodeList)localObject3).item(m).getChildNodes().item(0).getNodeValue());
                localObject1 = ((StringBuilder)localObject1).toString();
                break label723;
              }
              if (paramString.length() == 0) {
                break label735;
              }
              PayPal.getInstance().setAccountPhone(paramString);
              break label735;
            }
            if (((String)localObject1).compareTo("AuthMethod") == 0)
            {
              PayPal.getInstance().setLoginType(Integer.parseInt((String)localObject3));
              break label735;
            }
            if (((String)localObject1).compareTo("AuthSettings") != 0) {
              break label735;
            }
            PayPal.getInstance().setAuthSettings(Integer.parseInt((String)localObject3));
            break label735;
          }
          if ((PayPal.getInstance().getAuthSettings() == 0) && (PayPal.getInstance().getLoginType() == 2)) {
            PayPal.getInstance().setLoginType(0);
          }
        }
      }
      catch (IOException paramString)
      {
        continue;
      }
      j += 1;
      continue;
      i += 1;
      continue;
      return true;
      label714:
      k += 1;
      continue;
      label723:
      m += 1;
      paramString = (String)localObject1;
      continue;
      label735:
      k += 1;
    }
  }
  
  private static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Object localObject4 = null;
    int m = 0;
    Object localObject2 = null;
    int j = 0;
    Object localObject3 = localObject2;
    for (;;)
    {
      Object localObject1;
      int k;
      try
      {
        n = paramInputStream.read();
        if (n == -1) {
          break label181;
        }
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject3 = localObject2;
          localObject1 = new byte['Ѐ'];
        }
        localObject2 = localObject1;
        k = j;
        if (j == 1024)
        {
          localObject3 = localObject1;
          localByteArrayOutputStream.write((byte[])localObject1);
          localObject1 = localObject4;
        }
      }
      catch (IOException paramInputStream)
      {
        int n;
        int i;
        continue;
      }
      try
      {
        localObject2 = new byte['Ѐ'];
        k = 0;
        i = (byte)n;
        localObject2[k] = i;
        j = k + 1;
      }
      catch (IOException paramInputStream)
      {
        return localObject1;
      }
      if (k < j)
      {
        localObject3 = localObject2;
        localByteArrayOutputStream.write(localObject2[k]);
        k += 1;
      }
      else
      {
        localObject1 = localObject4;
        localObject2 = new byte[localByteArrayOutputStream.size()];
        try
        {
          paramInputStream = localByteArrayOutputStream.toByteArray();
          localObject1 = paramInputStream;
          localByteArrayOutputStream.close();
          return paramInputStream;
        }
        catch (IOException paramInputStream)
        {
          continue;
        }
        return localObject2;
        return localObject3;
        label181:
        if (j != 0) {
          k = m;
        }
      }
    }
  }
  
  private static String b()
  {
    int i = PayPal.getInstance().getServer();
    if (i != 0)
    {
      if (i != 2) {
        return "https://mobileclient.paypal.com/mepadapter/";
      }
      return "";
    }
    return "https://mobileclient.sandbox.paypal.com/mepadapter/";
  }
  
  private boolean b(String paramString)
  {
    this.c = -1;
    if ((paramString != null) && (paramString.length() > 0))
    {
      int i = d(paramString);
      if (paramString == null)
      {
        this.c = 408;
        return true;
      }
      if (paramString.contains("ErrorId=")) {
        this.c = Integer.parseInt(paramString.substring(paramString.indexOf("ErrorId=") + "ErrorId=".length()));
      } else if (i != 200) {
        this.c = i;
      } else if ((paramString.contains("<SOAP-ENV:Body")) && (!paramString.contains("</SOAP-ENV:Body"))) {
        return true;
      }
      int j = this.c;
      i = 0;
      if (j == -1) {
        return false;
      }
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("ANDROID_");
      ((StringBuilder)localObject1).append(this.c);
      localObject1 = e.a(((StringBuilder)localObject1).toString());
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("");
      ((StringBuilder)localObject2).append(this.c);
      if (a(((StringBuilder)localObject2).toString()))
      {
        localObject2 = e(paramString);
        if (localObject2 != null) {
          for (paramString = (String)localObject1; i < localObject2.length; paramString = ((StringBuilder)localObject1).toString())
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(paramString);
            ((StringBuilder)localObject1).append(" ");
            ((StringBuilder)localObject1).append(localObject2[i]);
            i += 1;
            if (i == localObject2.length) {
              paramString = ".";
            } else {
              paramString = ",";
            }
            ((StringBuilder)localObject1).append(paramString);
          }
        }
      }
      return true;
    }
    this.c = 408;
    return true;
  }
  
  private static boolean b(String paramString, Hashtable<String, Object> paramHashtable)
  {
    for (;;)
    {
      try
      {
        paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("MEPRemoveDeviceAuthorizationResponseType");
        i = 0;
        if (i < paramString.getLength())
        {
          NodeList localNodeList = paramString.item(i).getChildNodes();
          j = 0;
          if (j < localNodeList.getLength())
          {
            localObject2 = localNodeList.item(j);
            localObject1 = ((Node)localObject2).getNodeName();
            if (((String)localObject1).compareTo("DeviceReferenceToken") == 0)
            {
              localObject2 = ((Node)localObject2).getChildNodes().item(0).getNodeValue();
              paramHashtable.put(localObject1, localObject2);
            }
          }
        }
      }
      catch (Exception paramString)
      {
        int i;
        int j;
        Object localObject2;
        Object localObject1;
        return false;
      }
      try
      {
        try
        {
          localObject1 = PayPal.getInstance().getParentContext().openFileOutput("DeviceReferenceToken", 2);
          ((FileOutputStream)localObject1).write(((String)localObject2).getBytes());
          ((FileOutputStream)localObject1).flush();
          ((FileOutputStream)localObject1).close();
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localFileNotFoundException.printStackTrace();
        }
      }
      catch (IOException localIOException)
      {
        continue;
      }
      j += 1;
      continue;
      i += 1;
    }
    return true;
  }
  
  private static String c()
  {
    if (PayPal.getInstance().getServer() != 2) {
      return "MEPAdapter";
    }
    return "";
  }
  
  private static void c(String paramString)
  {
    paramString.replaceAll("<", "\n<").replaceAll(">", ">\n").replaceAll("\n\n", "\n");
  }
  
  private static int d(String paramString)
  {
    try
    {
      Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
      NodeList localNodeList = localDocument.getElementsByTagName("ErrorCode");
      if (localNodeList != null)
      {
        paramString = localNodeList;
        if (localNodeList.getLength() != 0) {}
      }
      else
      {
        localNodeList = localDocument.getElementsByTagName("errorId");
        if (localNodeList == null) {
          break label105;
        }
        paramString = localNodeList;
        if (localNodeList.getLength() == 0) {
          break label105;
        }
      }
      int i = Integer.parseInt(paramString.item(0).getChildNodes().item(0).getNodeValue());
      return i;
      label105:
      return 200;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 10004;
  }
  
  private static String d()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      Log.e("Exception", localSocketException.toString());
    }
    return null;
  }
  
  private boolean e()
  {
    String str = g();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b());
    localStringBuilder.append(c());
    str = a(str, localStringBuilder.toString());
    if (b(str)) {}
    try
    {
      a(str, this.e);
      return false;
    }
    catch (Throwable localThrowable) {}
    if (a(str, this.e))
    {
      PayPal.getInstance().setLibraryInitialized(true);
      return true;
    }
    this.c = -1;
    return false;
    return false;
  }
  
  private static String[] e(String paramString)
  {
    Vector localVector = new Vector();
    int j = 0;
    for (;;)
    {
      try
      {
        paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("parameter");
        if (paramString != null)
        {
          if (paramString.getLength() != 0) {
            break label147;
          }
          continue;
          if (i >= paramString.getLength()) {
            continue;
          }
          localVector.add(paramString.item(i).getChildNodes().item(0).getNodeValue());
          i += 1;
          continue;
        }
        return null;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        paramString = new String[localVector.size()];
        i = j;
        if (i < localVector.size())
        {
          paramString[i] = ((String)localVector.get(i));
          i += 1;
          continue;
        }
        return paramString;
      }
      label147:
      int i = 0;
    }
  }
  
  private boolean f()
  {
    String str = h();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b());
    localStringBuilder.append(c());
    str = a(str, localStringBuilder.toString());
    if (b(str)) {}
    try
    {
      b(str, this.e);
      return false;
    }
    catch (Throwable localThrowable) {}
    if (b(str, this.e)) {
      return true;
    }
    this.c = -1;
    return false;
    return false;
  }
  
  private static String g()
  {
    Object localObject6 = "";
    Object localObject18 = "";
    Object localObject9 = "";
    Object localObject1 = "";
    Object localObject20 = "";
    localObject14 = "";
    localObject15 = "";
    for (;;)
    {
      try
      {
        Object localObject2 = PayPal.getInstance().getAppID();
        try
        {
          localObject6 = URLEncoder.encode((String)localObject2, "utf-8");
          try
          {
            localObject2 = PayPal.getInstance().getParentContext();
            try
            {
              localObject4 = (TelephonyManager)((Context)localObject2).getSystemService("phone");
              j = ((TelephonyManager)localObject4).getPhoneType();
              localObject2 = "MEID";
              if (j == 1) {
                localObject2 = "IMEI";
              }
            }
            catch (Exception localException5)
            {
              Object localObject4;
              int j;
              Object localObject11;
              String str1;
            }
          }
          catch (Exception localException2) {}
        }
        catch (Exception localException3)
        {
          localObject6 = "";
        }
      }
      catch (Exception localException4) {}
      try
      {
        localObject4 = URLEncoder.encode(((TelephonyManager)localObject4).getDeviceId(), "utf-8");
        localObject11 = localObject9;
        localObject16 = localObject1;
      }
      catch (Exception localException9)
      {
        Object localObject13;
        for (;;)
        {
          Object localObject7;
          Object localObject3;
          Object localObject26;
          String str3;
          Object localObject5 = "";
          str2 = "";
          localObject17 = localObject10;
          localObject10 = localException9;
          localObject16 = "";
          localObject13 = localObject5;
          Object localObject8 = localObject17;
          localObject5 = localObject10;
          localObject23 = localObject3;
        }
        i += 1;
        continue;
        localObject14 = null;
        localObject22 = localObject13;
        if (localObject14 != null) {
          break label1317;
        }
        localObject14 = localObject10;
        continue;
        continue;
      }
      try
      {
        str1 = Build.DEVICE;
        try
        {
          localObject1 = str1.replaceAll(" ", "%20");
          localObject11 = localObject9;
          localObject16 = localObject1;
          localObject9 = Build.MODEL;
          try
          {
            str1 = ((String)localObject9).replaceAll(" ", "%20");
            localObject11 = str1;
            localObject16 = localObject1;
            str2 = URLEncoder.encode("Android", "utf-8");
            try
            {
              localObject16 = URLEncoder.encode(Build.VERSION.SDK, "utf-8");
              try
              {
                localObject24 = URLEncoder.encode("Phone", "utf-8");
                try
                {
                  localObject17 = URLEncoder.encode(PayPal.getVersion(), "utf-8");
                  try
                  {
                    localObject9 = PayPal.getInstance().getParentContext().getPackageName();
                    try
                    {
                      localObject22 = PayPal.getInstance().getParentContext().getPackageManager();
                      localObject23 = ((PackageManager)localObject22).getInstalledApplications(0);
                      Collections.sort((List)localObject23, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject22));
                      i = 0;
                      localObject11 = localObject18;
                      try
                      {
                        if (i >= ((List)localObject23).size()) {
                          break label1298;
                        }
                        localObject25 = (ApplicationInfo)((List)localObject23).get(i);
                        localObject20 = localObject14;
                        localObject18 = localObject15;
                      }
                      catch (Exception localException15) {}
                    }
                    catch (Exception localException16)
                    {
                      localObject11 = localException15;
                      localObject19 = localException16;
                    }
                  }
                  catch (Exception localException12)
                  {
                    localObject9 = localObject17;
                  }
                }
                catch (Exception localException13)
                {
                  localObject9 = "";
                }
              }
              catch (Exception localException10)
              {
                Object localObject12 = localObject4;
              }
            }
            catch (Exception localException11)
            {
              localObject17 = str1;
            }
          }
          catch (Exception localException6) {}
        }
        catch (Exception localException14)
        {
          localObject1 = localException6;
          Exception localException7 = localException14;
        }
      }
      catch (Exception localException8)
      {
        localObject10 = localException14;
        localObject1 = localObject16;
      }
    }
    try
    {
      if (!((ApplicationInfo)localObject25).packageName.equals(localObject9)) {
        break label1278;
      }
      localObject20 = localObject14;
      localObject18 = localObject15;
      localObject14 = ((ApplicationInfo)localObject25).loadLabel((PackageManager)localObject22).toString();
    }
    catch (Exception localException17)
    {
      localObject22 = localObject21;
      localObject15 = localObject19;
      break label412;
    }
    if (j == 1) {
      localObject15 = "AndroidGSM";
    } else {
      localObject15 = "AndroidCDMA";
    }
    localObject20 = localObject14;
    localObject18 = localObject15;
    if (((String)localObject4).equals("000000000000000")) {
      localObject11 = "true";
    } else {
      localObject11 = "false";
    }
    localObject20 = localObject9;
    localObject18 = localObject6;
    localObject6 = localObject2;
    localObject23 = localObject14;
    Object localObject25 = localObject15;
    localObject2 = localObject22;
    break label667;
    localObject22 = localObject14;
    localObject23 = localObject19;
    label412:
    localObject21 = localObject9;
    localObject14 = localObject17;
    localObject19 = localObject15;
    localObject17 = localObject11;
    break label474;
    localObject17 = "";
    localObject23 = "";
    localObject19 = localObject15;
    localObject22 = localObject14;
    localObject14 = localObject9;
    localObject9 = localObject23;
    localObject23 = localException13;
    label474:
    localObject25 = localObject23;
    localObject12 = localObject24;
    localObject15 = localObject4;
    localObject23 = localObject2;
    localObject2 = localObject17;
    break label622;
    localObject5 = localException4;
    localObject23 = "";
    localObject13 = "";
    str2 = "";
    localObject16 = "";
    localObject7 = localObject10;
    localObject3 = "";
    localObject17 = "";
    Object localObject24 = "";
    localObject10 = "";
    localObject19 = localObject15;
    localObject22 = localObject14;
    localObject15 = localObject13;
    localObject14 = localObject24;
    localObject13 = localObject17;
    localObject25 = localObject5;
    label622:
    ((Exception)localObject25).printStackTrace();
    localObject26 = localObject23;
    str3 = "false";
    localObject25 = localObject19;
    localObject23 = localObject22;
    localObject5 = localObject15;
    localObject17 = localObject14;
    localObject24 = localObject13;
    localObject13 = str3;
    localObject19 = localObject6;
    localObject6 = localObject26;
    label667:
    localObject14 = new StringBuilder();
    ((StringBuilder)localObject14).append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPDeviceInterrogationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1.0</Version><PayPalAppID xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append(localObject19);
    ((StringBuilder)localObject14).append("</PayPalAppID>");
    ((StringBuilder)localObject14).append("<DeviceDetails xsi:type=\"ns:MEPDeviceDetailsType\">");
    ((StringBuilder)localObject14).append("<deviceID xsi:type=\"ns:MEPDeviceIDType\">");
    ((StringBuilder)localObject14).append("<deviceIdentifier xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject5);
    ((StringBuilder)localObject14).append("</deviceIdentifier>");
    ((StringBuilder)localObject14).append("<deviceIdType xsi:type=\"ns:MEPDeviceIdentifierType\">");
    ((StringBuilder)localObject14).append((String)localObject6);
    ((StringBuilder)localObject14).append("</deviceIdType>");
    ((StringBuilder)localObject14).append("</deviceID>");
    ((StringBuilder)localObject14).append("<deviceName xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject1);
    ((StringBuilder)localObject14).append("</deviceName>");
    ((StringBuilder)localObject14).append("<deviceModel xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append(localObject7);
    ((StringBuilder)localObject14).append("</deviceModel>");
    ((StringBuilder)localObject14).append("<systemName xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append(str2);
    ((StringBuilder)localObject14).append("</systemName>");
    ((StringBuilder)localObject14).append("<systemVersion xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject16);
    ((StringBuilder)localObject14).append("</systemVersion>");
    ((StringBuilder)localObject14).append("<deviceCategory xsi:type=\"ns:MEPDeviceCategoryType\">");
    ((StringBuilder)localObject14).append((String)localObject24);
    ((StringBuilder)localObject14).append("</deviceCategory>");
    ((StringBuilder)localObject14).append("<isDeviceSimulator xsi:type=\"xs:boolean\">");
    ((StringBuilder)localObject14).append((String)localObject13);
    ((StringBuilder)localObject14).append("</isDeviceSimulator>");
    ((StringBuilder)localObject14).append("</DeviceDetails>");
    ((StringBuilder)localObject14).append("<ApplicationDetails xsi:type=\"ns:MEPApplicationDetailsType\">");
    ((StringBuilder)localObject14).append("<appID xsi:type=\"ns:MEPAppIDType\">");
    ((StringBuilder)localObject14).append("<deviceAppID xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject10);
    ((StringBuilder)localObject14).append("</deviceAppID>");
    ((StringBuilder)localObject14).append("</appID>");
    ((StringBuilder)localObject14).append("<appName xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append(localObject21);
    ((StringBuilder)localObject14).append("</appName>");
    ((StringBuilder)localObject14).append("<appDisplayName xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject23);
    ((StringBuilder)localObject14).append("</appDisplayName>");
    ((StringBuilder)localObject14).append("<clientPlatform xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject25);
    ((StringBuilder)localObject14).append("</clientPlatform>");
    ((StringBuilder)localObject14).append("</ApplicationDetails>");
    ((StringBuilder)localObject14).append("<MEPVersion xsi:type=\"xs:string\">");
    ((StringBuilder)localObject14).append((String)localObject17);
    ((StringBuilder)localObject14).append("</MEPVersion>");
    localObject5 = ((StringBuilder)localObject14).toString();
    try
    {
      localObject1 = PayPal.getInstance().getParentContext().openFileInput("DeviceReferenceToken");
      localObject6 = new byte[((FileInputStream)localObject1).available()];
      ((FileInputStream)localObject1).read((byte[])localObject6);
      ((FileInputStream)localObject1).close();
      localObject1 = new String((byte[])localObject6);
    }
    catch (Exception localException1)
    {
      for (;;) {}
    }
    localObject1 = localObject3;
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append((String)localObject5);
    ((StringBuilder)localObject3).append("<DeviceReferenceToken xsi:type=\"xs:string\">");
    ((StringBuilder)localObject3).append((String)localObject1);
    ((StringBuilder)localObject3).append("</DeviceReferenceToken>");
    localObject1 = ((StringBuilder)localObject3).toString();
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append((String)localObject1);
    ((StringBuilder)localObject3).append("</Request></MEPDeviceInterrogationReq></SOAP-ENV:Body></SOAP-ENV:Envelope>");
    return ((StringBuilder)localObject3).toString();
  }
  
  private static String h()
  {
    Object localObject1 = "";
    try
    {
      localObject2 = PayPal.getInstance().getParentContext().openFileInput("DeviceReferenceToken");
      byte[] arrayOfByte = new byte[((FileInputStream)localObject2).available()];
      ((FileInputStream)localObject2).read(arrayOfByte);
      ((FileInputStream)localObject2).close();
      localObject2 = new String(arrayOfByte);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPRemoveDeviceAuthorizationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1</Version><SessionToken xsi:type=\"ns:MEPSessionToken\">");
    ((StringBuilder)localObject2).append("");
    ((StringBuilder)localObject2).append("</SessionToken>");
    ((StringBuilder)localObject2).append("<DeviceReferenceToken xsi:type=\"xs:string\">");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("</DeviceReferenceToken>");
    ((StringBuilder)localObject2).append("</Request>");
    ((StringBuilder)localObject2).append("</MEPRemoveDeviceAuthorizationReq>");
    ((StringBuilder)localObject2).append("</SOAP-ENV:Body>");
    ((StringBuilder)localObject2).append("</SOAP-ENV:Envelope>");
    return ((StringBuilder)localObject2).toString();
  }
  
  public final void a(int paramInt)
  {
    this.d = paramInt;
  }
}
