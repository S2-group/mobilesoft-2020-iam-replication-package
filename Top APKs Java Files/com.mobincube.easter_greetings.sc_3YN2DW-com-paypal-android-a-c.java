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
    //   0: iconst_0
    //   1: istore 5
    //   3: aload_1
    //   4: invokestatic 44	com/paypal/android/a/c:c	(Ljava/lang/String;)V
    //   7: aload_0
    //   8: getfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   11: ifnonnull +14 -> 25
    //   14: aload_0
    //   15: new 46	org/apache/http/impl/client/DefaultHttpClient
    //   18: dup
    //   19: invokespecial 47	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   22: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   25: aload_0
    //   26: new 49	org/apache/http/client/methods/HttpPost
    //   29: dup
    //   30: aload_2
    //   31: invokespecial 51	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   34: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   37: aload_2
    //   38: invokestatic 56	com/paypal/android/a/c:b	()Ljava/lang/String;
    //   41: invokevirtual 62	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   44: ifeq +119 -> 163
    //   47: new 64	java/util/ArrayList
    //   50: dup
    //   51: invokespecial 65	java/util/ArrayList:<init>	()V
    //   54: astore_2
    //   55: aload_2
    //   56: new 67	org/apache/http/message/BasicNameValuePair
    //   59: dup
    //   60: ldc 69
    //   62: aload_1
    //   63: invokespecial 72	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: invokevirtual 76	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   69: pop
    //   70: aload_0
    //   71: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   74: new 78	org/apache/http/client/entity/UrlEncodedFormEntity
    //   77: dup
    //   78: aload_2
    //   79: ldc 80
    //   81: invokespecial 83	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   84: invokevirtual 87	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   87: aload_0
    //   88: getfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   91: aload_0
    //   92: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   95: invokevirtual 91	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   98: astore_1
    //   99: aload_1
    //   100: invokeinterface 97 1 0
    //   105: astore 9
    //   107: aload_1
    //   108: invokeinterface 101 1 0
    //   113: astore_1
    //   114: aload_1
    //   115: ifnull +399 -> 514
    //   118: iconst_0
    //   119: istore_3
    //   120: iload_3
    //   121: istore 4
    //   123: iload 5
    //   125: aload_1
    //   126: arraylength
    //   127: if_icmpge +390 -> 517
    //   130: aload_1
    //   131: iload 5
    //   133: aaload
    //   134: invokeinterface 106 1 0
    //   139: ldc 108
    //   141: invokevirtual 112	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   144: ifne +667 -> 811
    //   147: aload_1
    //   148: iload 5
    //   150: aaload
    //   151: invokeinterface 115 1 0
    //   156: invokestatic 120	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   159: istore_3
    //   160: goto +651 -> 811
    //   163: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   166: invokevirtual 130	com/paypal/android/MECL/PayPal:getServer	()I
    //   169: tableswitch	default:+651->820, 0:+253->422, 1:+651->820, 2:+658->827
    //   196: aload_2
    //   197: aload 6
    //   199: invokevirtual 62	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   202: ifeq +299 -> 501
    //   205: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   208: invokevirtual 130	com/paypal/android/MECL/PayPal:getServer	()I
    //   211: iconst_3
    //   212: if_icmpne +217 -> 429
    //   215: aload_0
    //   216: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   219: ldc -124
    //   221: ldc -122
    //   223: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   226: aload_0
    //   227: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   230: ldc -117
    //   232: ldc -115
    //   234: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: aload_0
    //   238: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   241: ldc -113
    //   243: invokestatic 145	com/paypal/android/a/c:d	()Ljava/lang/String;
    //   246: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   249: aload_0
    //   250: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   253: ldc -109
    //   255: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   258: invokevirtual 150	com/paypal/android/MECL/PayPal:getAppID	()Ljava/lang/String;
    //   261: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   264: aload_0
    //   265: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   268: ldc -104
    //   270: aload_0
    //   271: getfield 154	com/paypal/android/a/c:e	Ljava/util/Hashtable;
    //   274: ldc -100
    //   276: invokevirtual 162	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   279: checkcast 58	java/lang/String
    //   282: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   285: aload_0
    //   286: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   289: ldc -92
    //   291: new 166	java/lang/StringBuilder
    //   294: dup
    //   295: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   298: ldc -87
    //   300: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: invokestatic 176	com/paypal/android/MECL/PayPal:getVersion	()Ljava/lang/String;
    //   306: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   312: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   315: aload_0
    //   316: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   319: ldc -75
    //   321: ldc -73
    //   323: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   326: aload_0
    //   327: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   330: ldc -71
    //   332: ldc -73
    //   334: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   337: aload_0
    //   338: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   341: ldc -69
    //   343: ldc -67
    //   345: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   348: aload_0
    //   349: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   352: ldc -65
    //   354: ldc -63
    //   356: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   359: aload_0
    //   360: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   363: new 195	org/apache/http/entity/StringEntity
    //   366: dup
    //   367: aload_1
    //   368: invokespecial 196	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
    //   371: invokevirtual 87	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   374: goto -287 -> 87
    //   377: astore 8
    //   379: aconst_null
    //   380: astore_2
    //   381: aconst_null
    //   382: astore_1
    //   383: aload_2
    //   384: astore 7
    //   386: aload_1
    //   387: astore 6
    //   389: aload 8
    //   391: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   394: aload_2
    //   395: ifnull +7 -> 402
    //   398: aload_2
    //   399: invokevirtual 204	java/io/DataInputStream:close	()V
    //   402: aload_1
    //   403: ifnull +7 -> 410
    //   406: aload_1
    //   407: invokevirtual 207	java/io/InputStream:close	()V
    //   410: aload_0
    //   411: aconst_null
    //   412: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   415: aload_0
    //   416: aconst_null
    //   417: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   420: aconst_null
    //   421: areturn
    //   422: ldc -47
    //   424: astore 6
    //   426: goto -230 -> 196
    //   429: aload_0
    //   430: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   433: ldc -45
    //   435: ldc -43
    //   437: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   440: aload_0
    //   441: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   444: ldc -41
    //   446: ldc -43
    //   448: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   451: aload_0
    //   452: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   455: ldc -39
    //   457: ldc -43
    //   459: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   462: goto -236 -> 226
    //   465: astore_2
    //   466: aconst_null
    //   467: astore 7
    //   469: aconst_null
    //   470: astore_1
    //   471: aload 7
    //   473: ifnull +8 -> 481
    //   476: aload 7
    //   478: invokevirtual 204	java/io/DataInputStream:close	()V
    //   481: aload_1
    //   482: ifnull +7 -> 489
    //   485: aload_1
    //   486: invokevirtual 207	java/io/InputStream:close	()V
    //   489: aload_0
    //   490: aconst_null
    //   491: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   494: aload_0
    //   495: aconst_null
    //   496: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   499: aload_2
    //   500: athrow
    //   501: aload_0
    //   502: aconst_null
    //   503: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   506: aload_0
    //   507: aconst_null
    //   508: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   511: ldc -37
    //   513: areturn
    //   514: iconst_0
    //   515: istore 4
    //   517: aload 9
    //   519: invokeinterface 225 1 0
    //   524: astore_1
    //   525: new 201	java/io/DataInputStream
    //   528: dup
    //   529: aload_1
    //   530: invokespecial 228	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   533: astore_2
    //   534: iload 4
    //   536: istore_3
    //   537: aload_2
    //   538: astore 7
    //   540: aload_1
    //   541: astore 6
    //   543: aload_1
    //   544: invokevirtual 231	java/io/InputStream:available	()I
    //   547: iload 4
    //   549: if_icmple +14 -> 563
    //   552: aload_2
    //   553: astore 7
    //   555: aload_1
    //   556: astore 6
    //   558: aload_1
    //   559: invokevirtual 231	java/io/InputStream:available	()I
    //   562: istore_3
    //   563: iload_3
    //   564: istore 4
    //   566: aload_2
    //   567: astore 7
    //   569: aload_1
    //   570: astore 6
    //   572: aload_2
    //   573: invokevirtual 232	java/io/DataInputStream:available	()I
    //   576: iload_3
    //   577: if_icmple +15 -> 592
    //   580: aload_2
    //   581: astore 7
    //   583: aload_1
    //   584: astore 6
    //   586: aload_2
    //   587: invokevirtual 232	java/io/DataInputStream:available	()I
    //   590: istore 4
    //   592: iload 4
    //   594: ifeq +142 -> 736
    //   597: aload_2
    //   598: astore 7
    //   600: aload_1
    //   601: astore 6
    //   603: iload 4
    //   605: newarray byte
    //   607: astore 8
    //   609: aload_2
    //   610: astore 7
    //   612: aload_1
    //   613: astore 6
    //   615: aload_2
    //   616: aload 8
    //   618: invokevirtual 236	java/io/DataInputStream:readFully	([B)V
    //   621: aload_2
    //   622: astore 7
    //   624: aload_1
    //   625: astore 6
    //   627: new 58	java/lang/String
    //   630: dup
    //   631: aload 8
    //   633: ldc 80
    //   635: invokespecial 239	java/lang/String:<init>	([BLjava/lang/String;)V
    //   638: astore 8
    //   640: aload 9
    //   642: ifnull +16 -> 658
    //   645: aload_2
    //   646: astore 7
    //   648: aload_1
    //   649: astore 6
    //   651: aload 9
    //   653: invokeinterface 242 1 0
    //   658: aload_2
    //   659: astore 7
    //   661: aload_1
    //   662: astore 6
    //   664: aload_2
    //   665: invokevirtual 204	java/io/DataInputStream:close	()V
    //   668: aload_2
    //   669: astore 7
    //   671: aload_1
    //   672: astore 6
    //   674: aload_1
    //   675: invokevirtual 207	java/io/InputStream:close	()V
    //   678: aload_2
    //   679: astore 7
    //   681: aload_1
    //   682: astore 6
    //   684: aload_0
    //   685: aconst_null
    //   686: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   689: aload_2
    //   690: astore 7
    //   692: aload_1
    //   693: astore 6
    //   695: aload_0
    //   696: aconst_null
    //   697: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   700: aload_2
    //   701: astore 7
    //   703: aload_1
    //   704: astore 6
    //   706: aload 8
    //   708: invokestatic 44	com/paypal/android/a/c:c	(Ljava/lang/String;)V
    //   711: aload_2
    //   712: invokevirtual 204	java/io/DataInputStream:close	()V
    //   715: aload_1
    //   716: ifnull +7 -> 723
    //   719: aload_1
    //   720: invokevirtual 207	java/io/InputStream:close	()V
    //   723: aload_0
    //   724: aconst_null
    //   725: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   728: aload_0
    //   729: aconst_null
    //   730: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   733: aload 8
    //   735: areturn
    //   736: aload_2
    //   737: astore 7
    //   739: aload_1
    //   740: astore 6
    //   742: new 58	java/lang/String
    //   745: dup
    //   746: aload_2
    //   747: invokestatic 245	com/paypal/android/a/c:a	(Ljava/io/InputStream;)[B
    //   750: ldc 80
    //   752: invokespecial 239	java/lang/String:<init>	([BLjava/lang/String;)V
    //   755: astore 8
    //   757: goto -117 -> 640
    //   760: astore 8
    //   762: goto -379 -> 383
    //   765: astore_2
    //   766: goto -51 -> 715
    //   769: astore_1
    //   770: goto -47 -> 723
    //   773: astore_2
    //   774: goto -372 -> 402
    //   777: astore_1
    //   778: goto -368 -> 410
    //   781: astore 6
    //   783: goto -302 -> 481
    //   786: astore_1
    //   787: goto -298 -> 489
    //   790: astore_2
    //   791: aconst_null
    //   792: astore 7
    //   794: goto -323 -> 471
    //   797: astore_2
    //   798: aload 6
    //   800: astore_1
    //   801: goto -330 -> 471
    //   804: astore 8
    //   806: aconst_null
    //   807: astore_2
    //   808: goto -425 -> 383
    //   811: iload 5
    //   813: iconst_1
    //   814: iadd
    //   815: istore 5
    //   817: goto -697 -> 120
    //   820: ldc -9
    //   822: astore 6
    //   824: goto -628 -> 196
    //   827: ldc -7
    //   829: astore 6
    //   831: goto -635 -> 196
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	834	0	this	c
    //   0	834	1	paramString1	String
    //   0	834	2	paramString2	String
    //   119	459	3	i	int
    //   121	483	4	j	int
    //   1	815	5	k	int
    //   197	544	6	localObject1	Object
    //   781	18	6	localException	Exception
    //   822	8	6	str1	String
    //   384	409	7	str2	String
    //   377	13	8	localThrowable1	Throwable
    //   607	149	8	localObject2	Object
    //   760	1	8	localThrowable2	Throwable
    //   804	1	8	localThrowable3	Throwable
    //   105	547	9	localHttpEntity	org.apache.http.HttpEntity
    // Exception table:
    //   from	to	target	type
    //   7	25	377	java/lang/Throwable
    //   25	87	377	java/lang/Throwable
    //   87	114	377	java/lang/Throwable
    //   123	130	377	java/lang/Throwable
    //   130	160	377	java/lang/Throwable
    //   163	196	377	java/lang/Throwable
    //   196	226	377	java/lang/Throwable
    //   226	374	377	java/lang/Throwable
    //   429	462	377	java/lang/Throwable
    //   517	525	377	java/lang/Throwable
    //   7	25	465	finally
    //   25	87	465	finally
    //   87	114	465	finally
    //   123	130	465	finally
    //   130	160	465	finally
    //   163	196	465	finally
    //   196	226	465	finally
    //   226	374	465	finally
    //   429	462	465	finally
    //   517	525	465	finally
    //   543	552	760	java/lang/Throwable
    //   558	563	760	java/lang/Throwable
    //   572	580	760	java/lang/Throwable
    //   586	592	760	java/lang/Throwable
    //   603	609	760	java/lang/Throwable
    //   615	621	760	java/lang/Throwable
    //   627	640	760	java/lang/Throwable
    //   651	658	760	java/lang/Throwable
    //   664	668	760	java/lang/Throwable
    //   674	678	760	java/lang/Throwable
    //   684	689	760	java/lang/Throwable
    //   695	700	760	java/lang/Throwable
    //   706	711	760	java/lang/Throwable
    //   742	757	760	java/lang/Throwable
    //   711	715	765	java/lang/Exception
    //   719	723	769	java/lang/Exception
    //   398	402	773	java/lang/Exception
    //   406	410	777	java/lang/Exception
    //   476	481	781	java/lang/Exception
    //   485	489	786	java/lang/Exception
    //   525	534	790	finally
    //   389	394	797	finally
    //   543	552	797	finally
    //   558	563	797	finally
    //   572	580	797	finally
    //   586	592	797	finally
    //   603	609	797	finally
    //   615	621	797	finally
    //   627	640	797	finally
    //   651	658	797	finally
    //   664	668	797	finally
    //   674	678	797	finally
    //   684	689	797	finally
    //   695	700	797	finally
    //   706	711	797	finally
    //   742	757	797	finally
    //   525	534	804	java/lang/Throwable
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
    boolean bool2 = false;
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "560027";
    arrayOfString[1] = "580022";
    arrayOfString[2] = "580023";
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < arrayOfString.length)
      {
        if (arrayOfString[i].equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private static boolean a(String paramString, Hashtable<String, Object> paramHashtable)
  {
    int i;
    int j;
    label62:
    Object localObject1;
    int k;
    label175:
    Object localObject2;
    Object localObject3;
    try
    {
      NodeList localNodeList1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("MEPDeviceInterrogationResponseType");
      i = 0;
      if (i >= localNodeList1.getLength()) {
        break label693;
      }
      NodeList localNodeList2 = localNodeList1.item(i).getChildNodes();
      j = 0;
      if (j >= localNodeList2.getLength()) {
        break label686;
      }
      localObject1 = localNodeList2.item(j);
      paramString = ((Node)localObject1).getNodeName();
      if (paramString.compareTo("PayButtonEnable") == 0)
      {
        localObject1 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
        paramHashtable.put(paramString, localObject1);
        if (((String)localObject1).compareToIgnoreCase("true") == 0) {
          break label696;
        }
        Log.e("Error", "Authentication failed, button not enabled.");
        break label696;
      }
      if (paramString.compareTo("EncryptionDetails") == 0)
      {
        localObject1 = ((Node)localObject1).getChildNodes();
        k = 0;
        if (k >= ((NodeList)localObject1).getLength()) {
          break label696;
        }
        localObject2 = ((NodeList)localObject1).item(k);
        localObject3 = ((Node)localObject2).getNodeName();
        if ((((Node)localObject2).getChildNodes().getLength() <= 0) || (((String)localObject3).compareTo("Type") != 0)) {
          break label703;
        }
        paramHashtable.put(paramString, ((Node)localObject2).getChildNodes().item(0).getNodeValue());
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    if (paramString.compareTo("DeviceReferenceToken") == 0)
    {
      localObject1 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
      paramHashtable.put(paramString, localObject1);
    }
    for (;;)
    {
      int m;
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
        break label696;
        if (paramString.compareTo("DeviceAuthDetails") == 0)
        {
          localObject2 = ((Node)localObject1).getChildNodes();
          k = 0;
          if (k < ((NodeList)localObject2).getLength())
          {
            localObject1 = ((NodeList)localObject2).item(k);
            paramString = ((Node)localObject1).getNodeName();
            if (((Node)localObject1).getChildNodes().getLength() <= 0) {
              break label712;
            }
            localObject3 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
            if (paramString.compareTo("UserName") == 0)
            {
              PayPal.getInstance().setAccountName((String)localObject3);
              break label712;
            }
            if (paramString.compareTo("Email") == 0)
            {
              PayPal.getInstance().setAccountEmail((String)localObject3);
              break label712;
            }
            if (paramString.compareTo("Phone") == 0)
            {
              paramString = "";
              localObject3 = ((Node)localObject1).getChildNodes();
              m = 0;
              if (m < ((NodeList)localObject3).getLength())
              {
                localObject1 = paramString;
                if (((NodeList)localObject3).item(m).getChildNodes().getLength() <= 0) {
                  break label721;
                }
                localObject1 = paramString + ((NodeList)localObject3).item(m).getChildNodes().item(0).getNodeValue();
                break label721;
              }
              if (paramString.length() == 0) {
                break label712;
              }
              PayPal.getInstance().setAccountPhone(paramString);
              break label712;
            }
            if (paramString.compareTo("AuthMethod") == 0)
            {
              PayPal.getInstance().setLoginType(Integer.parseInt((String)localObject3));
              break label712;
            }
            if (paramString.compareTo("AuthSettings") != 0) {
              break label712;
            }
            PayPal.getInstance().setAuthSettings(Integer.parseInt((String)localObject3));
            break label712;
          }
          if ((PayPal.getInstance().getAuthSettings() == 0) && (PayPal.getInstance().getLoginType() == 2))
          {
            PayPal.getInstance().setLoginType(0);
            break label696;
            label686:
            i += 1;
            break;
            label693:
            return true;
          }
        }
      }
      catch (IOException paramString) {}
      label696:
      j += 1;
      break label62;
      label703:
      k += 1;
      break label175;
      label712:
      k += 1;
      continue;
      label721:
      m += 1;
      paramString = (String)localObject1;
    }
  }
  
  private static byte[] a(InputStream paramInputStream)
  {
    int m = 0;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    for (;;)
    {
      int k;
      try
      {
        n = paramInputStream.read();
        if (n == -1) {
          break label159;
        }
        Object localObject2 = localObject3;
        if (localObject3 == null)
        {
          localObject1 = localObject3;
          localObject2 = new byte['Ѐ'];
        }
        localObject3 = localObject2;
        k = j;
        if (j == 1024)
        {
          localObject1 = localObject2;
          localByteArrayOutputStream.write((byte[])localObject2);
        }
      }
      catch (IOException paramInputStream)
      {
        int n;
        int i;
        return localObject1;
      }
      try
      {
        localObject3 = new byte['Ѐ'];
        k = 0;
        i = (byte)n;
        localObject3[k] = i;
        j = k + 1;
      }
      catch (IOException paramInputStream)
      {
        return null;
      }
      if (k < j)
      {
        localObject1 = localObject3;
        localByteArrayOutputStream.write(localObject3[k]);
        k += 1;
      }
      else
      {
        localObject1 = new byte[localByteArrayOutputStream.size()];
        paramInputStream = localByteArrayOutputStream.toByteArray();
        localObject1 = paramInputStream;
        localByteArrayOutputStream.close();
        return paramInputStream;
        label159:
        if (j != 0) {
          k = m;
        }
      }
    }
  }
  
  private static String b()
  {
    switch (PayPal.getInstance().getServer())
    {
    case 1: 
    default: 
      return "https://mobileclient.paypal.com/mepadapter/";
    case 0: 
      return "https://mobileclient.sandbox.paypal.com/mepadapter/";
    }
    return "";
  }
  
  private boolean b(String paramString)
  {
    int i = 0;
    boolean bool = false;
    this.c = -1;
    label31:
    int j;
    if ((paramString == null) || (paramString.length() <= 0))
    {
      this.c = 408;
      bool = true;
      break label87;
      return bool;
    }
    else
    {
      j = d(paramString);
      if (paramString == null)
      {
        this.c = 408;
        return true;
      }
      if (!paramString.contains("ErrorId=")) {
        break label228;
      }
      this.c = Integer.parseInt(paramString.substring(paramString.indexOf("ErrorId=") + "ErrorId=".length()));
    }
    label87:
    Object localObject;
    if (this.c != -1)
    {
      localObject = e.a("ANDROID_" + this.c);
      if (!a("" + this.c)) {
        break label272;
      }
      String[] arrayOfString = e(paramString);
      if (arrayOfString == null) {
        break label272;
      }
      paramString = (String)localObject;
      label165:
      if (i >= arrayOfString.length) {
        break label272;
      }
      localObject = new StringBuilder().append(paramString).append(" ").append(arrayOfString[i]);
      if (i + 1 != arrayOfString.length) {
        break label265;
      }
    }
    label228:
    label265:
    for (paramString = ".";; paramString = ",")
    {
      paramString = paramString;
      i += 1;
      break label165;
      if (j != 200)
      {
        this.c = j;
        break label87;
        break label31;
      }
      if ((!paramString.contains("<SOAP-ENV:Body")) || (paramString.contains("</SOAP-ENV:Body"))) {
        break;
      }
      return true;
    }
    label272:
    return true;
  }
  
  /* Error */
  private static boolean b(String paramString, Hashtable<String, Object> paramHashtable)
  {
    // Byte code:
    //   0: invokestatic 297	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   3: invokevirtual 301	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   6: new 303	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: ldc 80
    //   13: invokevirtual 307	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   16: invokespecial 309	java/io/ByteArrayInputStream:<init>	([B)V
    //   19: invokevirtual 315	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   22: ldc_w 486
    //   25: invokeinterface 323 2 0
    //   30: astore_0
    //   31: iconst_0
    //   32: istore_2
    //   33: iload_2
    //   34: aload_0
    //   35: invokeinterface 328 1 0
    //   40: if_icmpge +151 -> 191
    //   43: aload_0
    //   44: iload_2
    //   45: invokeinterface 332 2 0
    //   50: invokeinterface 338 1 0
    //   55: astore 4
    //   57: iconst_0
    //   58: istore_3
    //   59: iload_3
    //   60: aload 4
    //   62: invokeinterface 328 1 0
    //   67: if_icmpge +117 -> 184
    //   70: aload 4
    //   72: iload_3
    //   73: invokeinterface 332 2 0
    //   78: astore 6
    //   80: aload 6
    //   82: invokeinterface 341 1 0
    //   87: astore 5
    //   89: aload 5
    //   91: ldc_w 363
    //   94: invokevirtual 112	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   97: ifne +67 -> 164
    //   100: aload 6
    //   102: invokeinterface 338 1 0
    //   107: iconst_0
    //   108: invokeinterface 332 2 0
    //   113: invokeinterface 344 1 0
    //   118: astore 6
    //   120: aload_1
    //   121: aload 5
    //   123: aload 6
    //   125: invokevirtual 263	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   128: pop
    //   129: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   132: invokevirtual 367	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   135: ldc_w 363
    //   138: iconst_2
    //   139: invokevirtual 373	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   142: astore 5
    //   144: aload 5
    //   146: aload 6
    //   148: invokevirtual 376	java/lang/String:getBytes	()[B
    //   151: invokevirtual 381	java/io/FileOutputStream:write	([B)V
    //   154: aload 5
    //   156: invokevirtual 384	java/io/FileOutputStream:flush	()V
    //   159: aload 5
    //   161: invokevirtual 385	java/io/FileOutputStream:close	()V
    //   164: iload_3
    //   165: iconst_1
    //   166: iadd
    //   167: istore_3
    //   168: goto -109 -> 59
    //   171: astore 5
    //   173: aload 5
    //   175: invokevirtual 386	java/io/FileNotFoundException:printStackTrace	()V
    //   178: goto -14 -> 164
    //   181: astore_0
    //   182: iconst_0
    //   183: ireturn
    //   184: iload_2
    //   185: iconst_1
    //   186: iadd
    //   187: istore_2
    //   188: goto -155 -> 33
    //   191: iconst_1
    //   192: ireturn
    //   193: astore 5
    //   195: goto -31 -> 164
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	198	0	paramString	String
    //   0	198	1	paramHashtable	Hashtable<String, Object>
    //   32	156	2	i	int
    //   58	110	3	j	int
    //   55	16	4	localNodeList	NodeList
    //   87	73	5	localObject1	Object
    //   171	3	5	localFileNotFoundException	FileNotFoundException
    //   193	1	5	localIOException	IOException
    //   78	69	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   129	164	171	java/io/FileNotFoundException
    //   0	31	181	java/lang/Exception
    //   33	57	181	java/lang/Exception
    //   59	129	181	java/lang/Exception
    //   129	164	181	java/lang/Exception
    //   173	178	181	java/lang/Exception
    //   129	164	193	java/io/IOException
  }
  
  private static String c()
  {
    switch (PayPal.getInstance().getServer())
    {
    default: 
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
          break label114;
        }
        paramString = localNodeList;
        if (localNodeList.getLength() == 0) {
          break label114;
        }
      }
      int i = Integer.parseInt(paramString.item(0).getChildNodes().item(0).getNodeValue());
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return 10004;
    }
    label114:
    return 200;
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
    String str = a(g(), b() + c());
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
    for (;;)
    {
      int i;
      try
      {
        paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("parameter");
        if (paramString == null) {
          break label143;
        }
        if (paramString.getLength() != 0) {
          break label145;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        paramString = new String[localVector.size()];
        i = 0;
        if (i >= localVector.size()) {
          continue;
        }
        paramString[i] = ((String)localVector.get(i));
        i += 1;
        continue;
        return paramString;
      }
      if (i < paramString.getLength())
      {
        localVector.add(paramString.item(i).getChildNodes().item(0).getNodeValue());
        i += 1;
      }
      else
      {
        label143:
        return null;
        label145:
        i = 0;
      }
    }
  }
  
  private boolean f()
  {
    String str = a(h(), b() + c());
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
    localObject18 = "";
    localObject7 = "";
    localObject6 = "";
    localObject9 = "";
    localObject8 = "";
    localObject10 = "";
    localObject11 = "";
    localObject12 = "";
    localObject2 = "";
    String str2 = "";
    str3 = "";
    localObject4 = localObject2;
    localObject13 = localObject12;
    localObject14 = localObject11;
    localObject15 = localObject10;
    localObject1 = localObject8;
    localObject3 = localObject9;
    localObject16 = localObject6;
    localObject17 = localObject7;
    for (;;)
    {
      int j;
      String str1;
      try
      {
        localObject5 = URLEncoder.encode(PayPal.getInstance().getAppID(), "utf-8");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject20 = (TelephonyManager)PayPal.getInstance().getParentContext().getSystemService("phone");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        j = ((TelephonyManager)localObject20).getPhoneType();
        localObject2 = "MEID";
        if (j == 1) {
          localObject2 = "IMEI";
        }
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject6 = URLEncoder.encode(((TelephonyManager)localObject20).getDeviceId(), "utf-8");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject8 = Build.DEVICE;
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject8 = ((String)localObject8).replaceAll(" ", "%20");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject9 = Build.MODEL;
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject9 = ((String)localObject9).replaceAll(" ", "%20");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject10 = URLEncoder.encode("Android", "utf-8");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject11 = URLEncoder.encode(Build.VERSION.SDK, "utf-8");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject12 = URLEncoder.encode("Phone", "utf-8");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject7 = URLEncoder.encode(PayPal.getVersion(), "utf-8");
        localObject4 = localObject2;
        localObject13 = localObject12;
        localObject14 = localObject11;
        localObject15 = localObject10;
        localObject1 = localObject8;
        localObject3 = localObject9;
        localObject16 = localObject6;
        localObject17 = localObject7;
        localObject18 = localObject5;
        localObject20 = PayPal.getInstance().getParentContext().getPackageName();
        localObject1 = localObject20;
        localObject14 = str3;
        localObject13 = str2;
      }
      catch (Exception localException3)
      {
        try
        {
          int i;
          localObject1 = PayPal.getInstance().getParentContext().openFileInput("DeviceReferenceToken");
          localObject3 = new byte[((FileInputStream)localObject1).available()];
          ((FileInputStream)localObject1).read((byte[])localObject3);
          ((FileInputStream)localObject1).close();
          localObject1 = new String((byte[])localObject3);
          localObject1 = (String)localObject2 + "<DeviceReferenceToken xsi:type=\"xs:string\">" + (String)localObject1 + "</DeviceReferenceToken>";
          return (String)localObject1 + "</Request></MEPDeviceInterrogationReq></SOAP-ENV:Body></SOAP-ENV:Envelope>";
          i += 1;
          continue;
          localObject3 = null;
          break label1398;
          localException3 = localException3;
          str3 = "";
          Object localObject20 = "";
          String str4 = "";
          String str5 = "";
          Object localObject5 = localObject18;
          localObject7 = localObject17;
          localObject6 = localObject16;
          localObject9 = localObject3;
          localObject8 = localObject1;
          localObject10 = localObject15;
          localObject11 = localObject14;
          localObject12 = localObject13;
          localObject2 = localObject4;
          localObject1 = str5;
          localObject13 = str4;
          localObject4 = localObject20;
          localObject3 = str3;
          localException3.printStackTrace();
          localObject16 = localObject13;
          localObject17 = localObject2;
          localObject13 = localObject8;
          localObject14 = localObject9;
          localObject15 = localObject6;
          localObject18 = "false";
          localObject2 = localObject16;
          localObject6 = localObject17;
          localObject8 = localObject10;
          localObject9 = localObject13;
          localObject10 = localObject14;
          localObject13 = localObject15;
          localObject14 = localObject18;
        }
        catch (Exception localException1)
        {
          str1 = "";
          continue;
        }
      }
      try
      {
        localObject3 = PayPal.getInstance().getParentContext().getPackageManager();
        localObject14 = str3;
        localObject13 = str2;
        localObject4 = ((PackageManager)localObject3).getInstalledApplications(0);
        localObject14 = str3;
        localObject13 = str2;
        Collections.sort((List)localObject4, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject3));
        i = 0;
        localObject14 = str3;
        localObject13 = str2;
        if (i >= ((List)localObject4).size()) {
          continue;
        }
        localObject14 = str3;
        localObject13 = str2;
        localObject15 = (ApplicationInfo)((List)localObject4).get(i);
        localObject14 = str3;
        localObject13 = str2;
        if (!((ApplicationInfo)localObject15).packageName.equals(localObject1)) {
          continue;
        }
        localObject14 = str3;
        localObject13 = str2;
        localObject3 = ((ApplicationInfo)localObject15).loadLabel((PackageManager)localObject3).toString();
      }
      catch (Exception localException2)
      {
        localObject3 = localObject14;
        localObject4 = localObject13;
        localObject13 = str1;
        Object localObject19 = localException2;
        continue;
        localObject13 = "false";
        continue;
        localObject4 = localObject3;
        if (localObject3 != null) {
          break label1410;
        }
        localObject4 = str1;
        localObject3 = "AndroidCDMA";
        if (j != 1) {
          continue;
        }
        localObject3 = "AndroidGSM";
      }
    }
    localObject14 = localObject3;
    localObject13 = localObject4;
    if (((String)localObject6).equals("000000000000000"))
    {
      localObject13 = "true";
      localObject15 = localObject10;
      localObject16 = localObject1;
      localObject14 = localObject13;
      localObject13 = localObject6;
      localObject10 = localObject9;
      localObject9 = localObject8;
      localObject8 = localObject15;
      localObject6 = localObject2;
      localObject2 = localObject16;
      localObject2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPDeviceInterrogationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1.0</Version><PayPalAppID xsi:type=\"xs:string\">" + (String)localObject5 + "</PayPalAppID>" + "<DeviceDetails xsi:type=\"ns:MEPDeviceDetailsType\">" + "<deviceID xsi:type=\"ns:MEPDeviceIDType\">" + "<deviceIdentifier xsi:type=\"xs:string\">" + (String)localObject13 + "</deviceIdentifier>" + "<deviceIdType xsi:type=\"ns:MEPDeviceIdentifierType\">" + (String)localObject6 + "</deviceIdType>" + "</deviceID>" + "<deviceName xsi:type=\"xs:string\">" + (String)localObject9 + "</deviceName>" + "<deviceModel xsi:type=\"xs:string\">" + (String)localObject10 + "</deviceModel>" + "<systemName xsi:type=\"xs:string\">" + (String)localObject8 + "</systemName>" + "<systemVersion xsi:type=\"xs:string\">" + (String)localObject11 + "</systemVersion>" + "<deviceCategory xsi:type=\"ns:MEPDeviceCategoryType\">" + (String)localObject12 + "</deviceCategory>" + "<isDeviceSimulator xsi:type=\"xs:boolean\">" + (String)localObject14 + "</isDeviceSimulator>" + "</DeviceDetails>" + "<ApplicationDetails xsi:type=\"ns:MEPApplicationDetailsType\">" + "<appID xsi:type=\"ns:MEPAppIDType\">" + "<deviceAppID xsi:type=\"xs:string\">" + (String)localObject1 + "</deviceAppID>" + "</appID>" + "<appName xsi:type=\"xs:string\">" + (String)localObject2 + "</appName>" + "<appDisplayName xsi:type=\"xs:string\">" + (String)localObject4 + "</appDisplayName>" + "<clientPlatform xsi:type=\"xs:string\">" + (String)localObject3 + "</clientPlatform>" + "</ApplicationDetails>" + "<MEPVersion xsi:type=\"xs:string\">" + (String)localObject7 + "</MEPVersion>";
    }
  }
  
  private static String h()
  {
    try
    {
      Object localObject = PayPal.getInstance().getParentContext().openFileInput("DeviceReferenceToken");
      byte[] arrayOfByte = new byte[((FileInputStream)localObject).available()];
      ((FileInputStream)localObject).read(arrayOfByte);
      ((FileInputStream)localObject).close();
      localObject = new String(arrayOfByte);
      return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPRemoveDeviceAuthorizationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1</Version><SessionToken xsi:type=\"ns:MEPSessionToken\">" + "" + "</SessionToken>" + "<DeviceReferenceToken xsi:type=\"xs:string\">" + (String)localObject + "</DeviceReferenceToken>" + "</Request>" + "</MEPRemoveDeviceAuthorizationReq>" + "</SOAP-ENV:Body>" + "</SOAP-ENV:Envelope>";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str = "";
      }
    }
  }
  
  public final void a(int paramInt)
  {
    this.d = paramInt;
  }
}
