package com.paypal.android.a;

import android.content.Context;
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
import java.util.Enumeration;
import java.util.Hashtable;
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
    //   41: ifeq +119 -> 160
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
    //   84: aload_0
    //   85: getfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   88: aload_0
    //   89: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   92: invokevirtual 91	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   95: astore_1
    //   96: aload_1
    //   97: invokeinterface 97 1 0
    //   102: astore 6
    //   104: aload_1
    //   105: invokeinterface 101 1 0
    //   110: astore_1
    //   111: aload_1
    //   112: ifnull +615 -> 727
    //   115: iconst_0
    //   116: istore 4
    //   118: iconst_0
    //   119: istore_3
    //   120: iload 4
    //   122: aload_1
    //   123: arraylength
    //   124: if_icmpge +381 -> 505
    //   127: aload_1
    //   128: iload 4
    //   130: aaload
    //   131: invokeinterface 106 1 0
    //   136: ldc 108
    //   138: invokevirtual 112	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   141: ifne +592 -> 733
    //   144: aload_1
    //   145: iload 4
    //   147: aaload
    //   148: invokeinterface 115 1 0
    //   153: invokestatic 120	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   156: istore_3
    //   157: goto +576 -> 733
    //   160: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   163: invokevirtual 130	com/paypal/android/MECL/PayPal:getServer	()I
    //   166: tableswitch	default:+576->742, 0:+247->413, 1:+576->742, 2:+583->749
    //   192: aload_2
    //   193: aload 5
    //   195: invokevirtual 62	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   198: ifeq +294 -> 492
    //   201: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   204: invokevirtual 130	com/paypal/android/MECL/PayPal:getServer	()I
    //   207: iconst_3
    //   208: if_icmpne +212 -> 420
    //   211: aload_0
    //   212: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   215: ldc -124
    //   217: ldc -122
    //   219: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   222: aload_0
    //   223: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   226: ldc -117
    //   228: ldc -115
    //   230: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   233: aload_0
    //   234: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   237: ldc -113
    //   239: invokestatic 145	com/paypal/android/a/c:d	()Ljava/lang/String;
    //   242: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   245: aload_0
    //   246: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   249: ldc -109
    //   251: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   254: invokevirtual 150	com/paypal/android/MECL/PayPal:getAppID	()Ljava/lang/String;
    //   257: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   260: aload_0
    //   261: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   264: ldc -104
    //   266: aload_0
    //   267: getfield 154	com/paypal/android/a/c:e	Ljava/util/Hashtable;
    //   270: ldc -100
    //   272: invokevirtual 162	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   275: checkcast 58	java/lang/String
    //   278: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   281: aload_0
    //   282: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   285: ldc -92
    //   287: new 166	java/lang/StringBuilder
    //   290: dup
    //   291: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   294: ldc -87
    //   296: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: invokestatic 176	com/paypal/android/MECL/PayPal:getVersion	()Ljava/lang/String;
    //   302: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   308: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   311: aload_0
    //   312: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   315: ldc -75
    //   317: ldc -73
    //   319: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   322: aload_0
    //   323: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   326: ldc -71
    //   328: ldc -73
    //   330: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   333: aload_0
    //   334: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   337: ldc -69
    //   339: ldc -67
    //   341: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   348: ldc -65
    //   350: ldc -63
    //   352: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   355: aload_0
    //   356: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   359: new 195	org/apache/http/entity/StringEntity
    //   362: dup
    //   363: aload_1
    //   364: invokespecial 196	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
    //   367: invokevirtual 87	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   370: goto -286 -> 84
    //   373: astore_1
    //   374: aconst_null
    //   375: astore_2
    //   376: aconst_null
    //   377: astore 5
    //   379: aload_1
    //   380: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   383: aload_2
    //   384: ifnull +7 -> 391
    //   387: aload_2
    //   388: invokevirtual 204	java/io/DataInputStream:close	()V
    //   391: aload 5
    //   393: ifnull +8 -> 401
    //   396: aload 5
    //   398: invokevirtual 207	java/io/InputStream:close	()V
    //   401: aload_0
    //   402: aconst_null
    //   403: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   406: aload_0
    //   407: aconst_null
    //   408: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   411: aconst_null
    //   412: areturn
    //   413: ldc -47
    //   415: astore 5
    //   417: goto -225 -> 192
    //   420: aload_0
    //   421: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   424: ldc -45
    //   426: ldc -43
    //   428: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   431: aload_0
    //   432: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   435: ldc -41
    //   437: ldc -43
    //   439: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   442: aload_0
    //   443: getfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   446: ldc -39
    //   448: ldc -43
    //   450: invokevirtual 137	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   453: goto -231 -> 222
    //   456: astore_1
    //   457: aconst_null
    //   458: astore_2
    //   459: aconst_null
    //   460: astore 5
    //   462: aload_2
    //   463: ifnull +7 -> 470
    //   466: aload_2
    //   467: invokevirtual 204	java/io/DataInputStream:close	()V
    //   470: aload 5
    //   472: ifnull +8 -> 480
    //   475: aload 5
    //   477: invokevirtual 207	java/io/InputStream:close	()V
    //   480: aload_0
    //   481: aconst_null
    //   482: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   485: aload_0
    //   486: aconst_null
    //   487: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   490: aload_1
    //   491: athrow
    //   492: aload_0
    //   493: aconst_null
    //   494: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   497: aload_0
    //   498: aconst_null
    //   499: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   502: ldc -37
    //   504: areturn
    //   505: iload_3
    //   506: istore 4
    //   508: aload 6
    //   510: invokeinterface 225 1 0
    //   515: astore 5
    //   517: new 201	java/io/DataInputStream
    //   520: dup
    //   521: aload 5
    //   523: invokespecial 228	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   526: astore_1
    //   527: iload 4
    //   529: istore_3
    //   530: aload 5
    //   532: invokevirtual 231	java/io/InputStream:available	()I
    //   535: iload 4
    //   537: if_icmple +9 -> 546
    //   540: aload 5
    //   542: invokevirtual 231	java/io/InputStream:available	()I
    //   545: istore_3
    //   546: iload_3
    //   547: istore 4
    //   549: aload_1
    //   550: invokevirtual 232	java/io/DataInputStream:available	()I
    //   553: iload_3
    //   554: if_icmple +9 -> 563
    //   557: aload_1
    //   558: invokevirtual 232	java/io/DataInputStream:available	()I
    //   561: istore 4
    //   563: iload 4
    //   565: ifeq +85 -> 650
    //   568: iload 4
    //   570: newarray byte
    //   572: astore_2
    //   573: aload_1
    //   574: aload_2
    //   575: invokevirtual 236	java/io/DataInputStream:readFully	([B)V
    //   578: new 58	java/lang/String
    //   581: dup
    //   582: aload_2
    //   583: ldc 80
    //   585: invokespecial 239	java/lang/String:<init>	([BLjava/lang/String;)V
    //   588: astore_2
    //   589: aload 6
    //   591: ifnull +10 -> 601
    //   594: aload 6
    //   596: invokeinterface 242 1 0
    //   601: aload_1
    //   602: invokevirtual 204	java/io/DataInputStream:close	()V
    //   605: aload 5
    //   607: invokevirtual 207	java/io/InputStream:close	()V
    //   610: aload_0
    //   611: aconst_null
    //   612: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   615: aload_0
    //   616: aconst_null
    //   617: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   620: aload_2
    //   621: invokestatic 44	com/paypal/android/a/c:c	(Ljava/lang/String;)V
    //   624: aload_1
    //   625: invokevirtual 204	java/io/DataInputStream:close	()V
    //   628: aload 5
    //   630: ifnull +8 -> 638
    //   633: aload 5
    //   635: invokevirtual 207	java/io/InputStream:close	()V
    //   638: aload_0
    //   639: aconst_null
    //   640: putfield 53	com/paypal/android/a/c:a	Lorg/apache/http/client/methods/HttpPost;
    //   643: aload_0
    //   644: aconst_null
    //   645: putfield 24	com/paypal/android/a/c:b	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   648: aload_2
    //   649: areturn
    //   650: new 58	java/lang/String
    //   653: dup
    //   654: aload_1
    //   655: invokestatic 245	com/paypal/android/a/c:a	(Ljava/io/InputStream;)[B
    //   658: ldc 80
    //   660: invokespecial 239	java/lang/String:<init>	([BLjava/lang/String;)V
    //   663: astore_2
    //   664: goto -75 -> 589
    //   667: astore 6
    //   669: aload_1
    //   670: astore_2
    //   671: aload 6
    //   673: astore_1
    //   674: goto -295 -> 379
    //   677: astore_1
    //   678: goto -50 -> 628
    //   681: astore_1
    //   682: goto -44 -> 638
    //   685: astore_1
    //   686: goto -295 -> 391
    //   689: astore_1
    //   690: goto -289 -> 401
    //   693: astore_2
    //   694: goto -224 -> 470
    //   697: astore_2
    //   698: goto -218 -> 480
    //   701: astore_1
    //   702: aconst_null
    //   703: astore_2
    //   704: goto -242 -> 462
    //   707: astore 6
    //   709: aload_1
    //   710: astore_2
    //   711: aload 6
    //   713: astore_1
    //   714: goto -252 -> 462
    //   717: astore_1
    //   718: goto -256 -> 462
    //   721: astore_1
    //   722: aconst_null
    //   723: astore_2
    //   724: goto -345 -> 379
    //   727: iconst_0
    //   728: istore 4
    //   730: goto -222 -> 508
    //   733: iload 4
    //   735: iconst_1
    //   736: iadd
    //   737: istore 4
    //   739: goto -619 -> 120
    //   742: ldc -9
    //   744: astore 5
    //   746: goto -554 -> 192
    //   749: ldc -7
    //   751: astore 5
    //   753: goto -561 -> 192
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	756	0	this	c
    //   0	756	1	paramString1	String
    //   0	756	2	paramString2	String
    //   119	436	3	i	int
    //   116	622	4	j	int
    //   193	559	5	localObject1	Object
    //   102	493	6	localHttpEntity	org.apache.http.HttpEntity
    //   667	5	6	localThrowable	Throwable
    //   707	5	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   4	22	373	java/lang/Throwable
    //   22	84	373	java/lang/Throwable
    //   84	111	373	java/lang/Throwable
    //   120	127	373	java/lang/Throwable
    //   127	157	373	java/lang/Throwable
    //   160	192	373	java/lang/Throwable
    //   192	222	373	java/lang/Throwable
    //   222	370	373	java/lang/Throwable
    //   420	453	373	java/lang/Throwable
    //   508	517	373	java/lang/Throwable
    //   4	22	456	finally
    //   22	84	456	finally
    //   84	111	456	finally
    //   120	127	456	finally
    //   127	157	456	finally
    //   160	192	456	finally
    //   192	222	456	finally
    //   222	370	456	finally
    //   420	453	456	finally
    //   508	517	456	finally
    //   530	546	667	java/lang/Throwable
    //   549	563	667	java/lang/Throwable
    //   568	589	667	java/lang/Throwable
    //   594	601	667	java/lang/Throwable
    //   601	624	667	java/lang/Throwable
    //   650	664	667	java/lang/Throwable
    //   624	628	677	java/lang/Exception
    //   633	638	681	java/lang/Exception
    //   387	391	685	java/lang/Exception
    //   396	401	689	java/lang/Exception
    //   466	470	693	java/lang/Exception
    //   475	480	697	java/lang/Exception
    //   517	527	701	finally
    //   530	546	707	finally
    //   549	563	707	finally
    //   568	589	707	finally
    //   594	601	707	finally
    //   601	624	707	finally
    //   650	664	707	finally
    //   379	383	717	finally
    //   517	527	721	java/lang/Throwable
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
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    Object localObject2 = null;
    Object localObject3 = localObject2;
    for (;;)
    {
      int k;
      try
      {
        m = paramInputStream.read();
        if (m == -1) {
          break label161;
        }
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject3 = localObject2;
          localObject1 = new byte['Ѐ'];
        }
        k = j;
        localObject2 = localObject1;
        if (j == 1024)
        {
          localObject3 = localObject1;
          localByteArrayOutputStream.write((byte[])localObject1);
        }
      }
      catch (IOException paramInputStream)
      {
        try
        {
          int m;
          int i;
          Object localObject1 = localByteArrayOutputStream.toByteArray();
          paramInputStream = (InputStream)localObject1;
          localByteArrayOutputStream.close();
          return localObject1;
        }
        catch (IOException localIOException)
        {
          return paramInputStream;
        }
        paramInputStream = paramInputStream;
        return localObject3;
      }
      try
      {
        localObject2 = new byte['Ѐ'];
        k = 0;
        i = (byte)m;
        localObject2[k] = i;
        j = k + 1;
      }
      catch (IOException paramInputStream)
      {
        return null;
      }
      if (k < j)
      {
        localObject3 = localObject2;
        localByteArrayOutputStream.write(localObject2[k]);
        k += 1;
      }
      else
      {
        paramInputStream = new byte[localByteArrayOutputStream.size()];
        label161:
        if (j != 0) {
          k = 0;
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
    this.c = -1;
    if ((paramString == null) || (paramString.length() <= 0))
    {
      this.c = 408;
      return true;
    }
    int i = d(paramString);
    if (paramString == null)
    {
      this.c = 408;
      return true;
    }
    if (paramString.contains("ErrorId=")) {
      this.c = Integer.parseInt(paramString.substring(paramString.indexOf("ErrorId=") + "ErrorId=".length()));
    }
    while (this.c == -1)
    {
      return false;
      if (i != 200) {
        this.c = i;
      } else if ((paramString.contains("<SOAP-ENV:Body")) && (!paramString.contains("</SOAP-ENV:Body"))) {
        return true;
      }
    }
    Object localObject = e.a("ANDROID_" + this.c);
    if (a("" + this.c))
    {
      String[] arrayOfString = e(paramString);
      if (arrayOfString != null)
      {
        paramString = (String)localObject;
        i = 0;
        if (i < arrayOfString.length)
        {
          localObject = new StringBuilder().append(paramString).append(" ").append(arrayOfString[i]);
          if (i + 1 == arrayOfString.length) {}
          for (paramString = ".";; paramString = ",")
          {
            paramString = paramString;
            i += 1;
            break;
          }
        }
      }
    }
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
    label116:
    for (;;)
    {
      try
      {
        Object localObject = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
        paramString = ((Document)localObject).getElementsByTagName("ErrorCode");
        if ((paramString != null) && (paramString.getLength() != 0)) {
          break label116;
        }
        localObject = ((Document)localObject).getElementsByTagName("errorId");
        if (localObject != null)
        {
          paramString = (String)localObject;
          if (((NodeList)localObject).getLength() != 0)
          {
            int i = Integer.parseInt(paramString.item(0).getChildNodes().item(0).getNodeValue());
            return i;
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return 10004;
      }
      return 200;
    }
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
      if (a(str, this.e))
      {
        PayPal.getInstance().setLibraryInitialized(true);
        return true;
      }
      this.c = -1;
      return false;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
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
      if (b(str, this.e)) {
        return true;
      }
      this.c = -1;
      return false;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  private static String g()
  {
    // Byte code:
    //   0: ldc -7
    //   2: astore_3
    //   3: ldc -63
    //   5: astore 20
    //   7: ldc -7
    //   9: astore 4
    //   11: ldc -7
    //   13: astore 6
    //   15: ldc -7
    //   17: astore 5
    //   19: ldc -7
    //   21: astore 7
    //   23: ldc -7
    //   25: astore 8
    //   27: ldc -7
    //   29: astore 9
    //   31: ldc -7
    //   33: astore_2
    //   34: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   37: invokevirtual 150	com/paypal/android/MECL/PayPal:getAppID	()Ljava/lang/String;
    //   40: ldc_w 569
    //   43: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   46: astore 15
    //   48: aload_3
    //   49: astore 19
    //   51: aload 4
    //   53: astore 11
    //   55: aload 6
    //   57: astore 18
    //   59: aload 5
    //   61: astore 12
    //   63: aload 7
    //   65: astore 17
    //   67: aload 8
    //   69: astore 13
    //   71: aload 9
    //   73: astore 14
    //   75: aload_2
    //   76: astore 16
    //   78: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   81: invokevirtual 367	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   84: ldc_w 576
    //   87: invokevirtual 580	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   90: checkcast 582	android/telephony/TelephonyManager
    //   93: astore 10
    //   95: aload_3
    //   96: astore 19
    //   98: aload 4
    //   100: astore 11
    //   102: aload 6
    //   104: astore 18
    //   106: aload 5
    //   108: astore 12
    //   110: aload 7
    //   112: astore 17
    //   114: aload 8
    //   116: astore 13
    //   118: aload 9
    //   120: astore 14
    //   122: aload_2
    //   123: astore 16
    //   125: aload 10
    //   127: invokevirtual 585	android/telephony/TelephonyManager:getPhoneType	()I
    //   130: istore_1
    //   131: ldc_w 587
    //   134: astore_2
    //   135: iload_1
    //   136: iconst_1
    //   137: if_icmpne +7 -> 144
    //   140: ldc_w 589
    //   143: astore_2
    //   144: aload_3
    //   145: astore 19
    //   147: aload 4
    //   149: astore 11
    //   151: aload 6
    //   153: astore 18
    //   155: aload 5
    //   157: astore 12
    //   159: aload 7
    //   161: astore 17
    //   163: aload 8
    //   165: astore 13
    //   167: aload 9
    //   169: astore 14
    //   171: aload_2
    //   172: astore 16
    //   174: aload 10
    //   176: invokevirtual 592	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   179: ldc_w 569
    //   182: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   185: astore 4
    //   187: aload_3
    //   188: astore 19
    //   190: aload 4
    //   192: astore 11
    //   194: aload 6
    //   196: astore 18
    //   198: aload 5
    //   200: astore 12
    //   202: aload 7
    //   204: astore 17
    //   206: aload 8
    //   208: astore 13
    //   210: aload 9
    //   212: astore 14
    //   214: aload_2
    //   215: astore 16
    //   217: getstatic 598	android/os/Build:DEVICE	Ljava/lang/String;
    //   220: astore 5
    //   222: aload 5
    //   224: ldc_w 480
    //   227: ldc_w 600
    //   230: invokevirtual 495	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   233: astore 10
    //   235: aload 10
    //   237: astore 5
    //   239: aload_3
    //   240: astore 19
    //   242: aload 4
    //   244: astore 11
    //   246: aload 6
    //   248: astore 18
    //   250: aload 5
    //   252: astore 12
    //   254: aload 7
    //   256: astore 17
    //   258: aload 8
    //   260: astore 13
    //   262: aload 9
    //   264: astore 14
    //   266: aload_2
    //   267: astore 16
    //   269: getstatic 603	android/os/Build:MODEL	Ljava/lang/String;
    //   272: astore 6
    //   274: aload 6
    //   276: ldc_w 480
    //   279: ldc_w 600
    //   282: invokevirtual 495	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   285: astore 10
    //   287: aload 10
    //   289: astore 6
    //   291: aload_3
    //   292: astore 19
    //   294: aload 4
    //   296: astore 11
    //   298: aload 6
    //   300: astore 18
    //   302: aload 5
    //   304: astore 12
    //   306: aload 7
    //   308: astore 17
    //   310: aload 8
    //   312: astore 13
    //   314: aload 9
    //   316: astore 14
    //   318: aload_2
    //   319: astore 16
    //   321: ldc_w 605
    //   324: ldc_w 569
    //   327: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   330: astore 7
    //   332: aload_3
    //   333: astore 19
    //   335: aload 4
    //   337: astore 11
    //   339: aload 6
    //   341: astore 18
    //   343: aload 5
    //   345: astore 12
    //   347: aload 7
    //   349: astore 17
    //   351: aload 8
    //   353: astore 13
    //   355: aload 9
    //   357: astore 14
    //   359: aload_2
    //   360: astore 16
    //   362: getstatic 610	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   365: ldc_w 569
    //   368: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   371: astore 8
    //   373: aload_3
    //   374: astore 19
    //   376: aload 4
    //   378: astore 11
    //   380: aload 6
    //   382: astore 18
    //   384: aload 5
    //   386: astore 12
    //   388: aload 7
    //   390: astore 17
    //   392: aload 8
    //   394: astore 13
    //   396: aload 9
    //   398: astore 14
    //   400: aload_2
    //   401: astore 16
    //   403: ldc_w 400
    //   406: ldc_w 569
    //   409: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   412: astore 9
    //   414: aload_3
    //   415: astore 19
    //   417: aload 4
    //   419: astore 11
    //   421: aload 6
    //   423: astore 18
    //   425: aload 5
    //   427: astore 12
    //   429: aload 7
    //   431: astore 17
    //   433: aload 8
    //   435: astore 13
    //   437: aload 9
    //   439: astore 14
    //   441: aload_2
    //   442: astore 16
    //   444: invokestatic 176	com/paypal/android/MECL/PayPal:getVersion	()Ljava/lang/String;
    //   447: ldc_w 569
    //   450: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   453: astore 10
    //   455: aload 10
    //   457: astore 19
    //   459: aload 4
    //   461: astore 11
    //   463: aload 6
    //   465: astore 18
    //   467: aload 5
    //   469: astore 12
    //   471: aload 7
    //   473: astore 17
    //   475: aload 8
    //   477: astore 13
    //   479: aload 9
    //   481: astore 14
    //   483: aload_2
    //   484: astore 16
    //   486: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   489: invokevirtual 367	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   492: invokevirtual 613	android/content/Context:getPackageName	()Ljava/lang/String;
    //   495: astore_3
    //   496: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   499: invokevirtual 367	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   502: invokevirtual 617	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   505: astore 11
    //   507: aload 11
    //   509: iconst_0
    //   510: invokevirtual 623	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   513: astore 12
    //   515: aload 12
    //   517: new 625	android/content/pm/ApplicationInfo$DisplayNameComparator
    //   520: dup
    //   521: aload 11
    //   523: invokespecial 628	android/content/pm/ApplicationInfo$DisplayNameComparator:<init>	(Landroid/content/pm/PackageManager;)V
    //   526: invokestatic 634	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   529: iconst_0
    //   530: istore_0
    //   531: iload_0
    //   532: aload 12
    //   534: invokeinterface 637 1 0
    //   539: if_icmpge +534 -> 1073
    //   542: aload 12
    //   544: iload_0
    //   545: invokeinterface 638 2 0
    //   550: checkcast 640	android/content/pm/ApplicationInfo
    //   553: astore 13
    //   555: aload 13
    //   557: getfield 643	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   560: aload_3
    //   561: invokevirtual 286	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   564: ifeq +502 -> 1066
    //   567: aload 13
    //   569: aload 11
    //   571: invokevirtual 647	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   574: invokevirtual 648	java/lang/Object:toString	()Ljava/lang/String;
    //   577: astore 12
    //   579: aload 12
    //   581: astore 11
    //   583: aload 12
    //   585: ifnonnull +6 -> 591
    //   588: aload_3
    //   589: astore 11
    //   591: ldc_w 650
    //   594: astore 12
    //   596: iload_1
    //   597: iconst_1
    //   598: if_icmpne +8 -> 606
    //   601: ldc_w 652
    //   604: astore 12
    //   606: aload 20
    //   608: astore 13
    //   610: aload 4
    //   612: ldc_w 654
    //   615: invokevirtual 286	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   618: ifeq +8 -> 626
    //   621: ldc_w 259
    //   624: astore 13
    //   626: aload 10
    //   628: astore 14
    //   630: aload_3
    //   631: astore 10
    //   633: aload 15
    //   635: astore 18
    //   637: aload 14
    //   639: astore 17
    //   641: aload 13
    //   643: astore 16
    //   645: aload 4
    //   647: astore 15
    //   649: aload 6
    //   651: astore 14
    //   653: aload 5
    //   655: astore 13
    //   657: aload 8
    //   659: astore 6
    //   661: aload 9
    //   663: astore 8
    //   665: aload_2
    //   666: astore 5
    //   668: aload_3
    //   669: astore 4
    //   671: aload 10
    //   673: astore_3
    //   674: aload 12
    //   676: astore_2
    //   677: new 166	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   684: ldc_w 656
    //   687: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   690: aload 18
    //   692: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   695: ldc_w 658
    //   698: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: ldc_w 660
    //   704: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   707: ldc_w 662
    //   710: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   713: ldc_w 664
    //   716: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   719: aload 15
    //   721: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   724: ldc_w 666
    //   727: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   730: ldc_w 668
    //   733: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: aload 5
    //   738: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   741: ldc_w 670
    //   744: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   747: ldc_w 672
    //   750: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: ldc_w 674
    //   756: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: aload 13
    //   761: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   764: ldc_w 676
    //   767: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: ldc_w 678
    //   773: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: aload 14
    //   778: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   781: ldc_w 680
    //   784: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   787: ldc_w 682
    //   790: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   793: aload 7
    //   795: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   798: ldc_w 684
    //   801: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   804: ldc_w 686
    //   807: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: aload 6
    //   812: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   815: ldc_w 688
    //   818: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: ldc_w 690
    //   824: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   827: aload 8
    //   829: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   832: ldc_w 692
    //   835: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: ldc_w 694
    //   841: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   844: aload 16
    //   846: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: ldc_w 696
    //   852: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: ldc_w 698
    //   858: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: ldc_w 700
    //   864: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: ldc_w 702
    //   870: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: ldc_w 704
    //   876: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: aload 4
    //   881: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   884: ldc_w 706
    //   887: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   890: ldc_w 708
    //   893: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   896: ldc_w 710
    //   899: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   902: aload_3
    //   903: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   906: ldc_w 712
    //   909: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   912: ldc_w 714
    //   915: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   918: aload 11
    //   920: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   923: ldc_w 716
    //   926: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   929: ldc_w 718
    //   932: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   935: aload_2
    //   936: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   939: ldc_w 720
    //   942: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   945: ldc_w 722
    //   948: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   951: ldc_w 724
    //   954: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   957: aload 17
    //   959: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: ldc_w 726
    //   965: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   968: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   971: astore_3
    //   972: invokestatic 126	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   975: invokevirtual 367	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   978: ldc_w 363
    //   981: invokevirtual 730	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   984: astore_2
    //   985: aload_2
    //   986: invokevirtual 733	java/io/FileInputStream:available	()I
    //   989: newarray byte
    //   991: astore 4
    //   993: aload_2
    //   994: aload 4
    //   996: invokevirtual 736	java/io/FileInputStream:read	([B)I
    //   999: pop
    //   1000: aload_2
    //   1001: invokevirtual 737	java/io/FileInputStream:close	()V
    //   1004: new 58	java/lang/String
    //   1007: dup
    //   1008: aload 4
    //   1010: invokespecial 738	java/lang/String:<init>	([B)V
    //   1013: astore_2
    //   1014: new 166	java/lang/StringBuilder
    //   1017: dup
    //   1018: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   1021: aload_3
    //   1022: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1025: ldc_w 740
    //   1028: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: aload_2
    //   1032: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1035: ldc_w 742
    //   1038: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1041: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1044: astore_2
    //   1045: new 166	java/lang/StringBuilder
    //   1048: dup
    //   1049: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   1052: aload_2
    //   1053: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1056: ldc_w 744
    //   1059: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1062: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1065: areturn
    //   1066: iload_0
    //   1067: iconst_1
    //   1068: iadd
    //   1069: istore_0
    //   1070: goto -539 -> 531
    //   1073: aconst_null
    //   1074: astore 12
    //   1076: goto -497 -> 579
    //   1079: astore 14
    //   1081: ldc -7
    //   1083: astore 15
    //   1085: ldc -7
    //   1087: astore 11
    //   1089: ldc -7
    //   1091: astore 4
    //   1093: ldc -7
    //   1095: astore 9
    //   1097: ldc -7
    //   1099: astore 6
    //   1101: ldc -7
    //   1103: astore 7
    //   1105: ldc -7
    //   1107: astore 8
    //   1109: ldc -7
    //   1111: astore 5
    //   1113: ldc -7
    //   1115: astore 10
    //   1117: ldc -7
    //   1119: astore_3
    //   1120: ldc -7
    //   1122: astore 12
    //   1124: ldc -7
    //   1126: astore_2
    //   1127: ldc -7
    //   1129: astore 13
    //   1131: aload 14
    //   1133: invokevirtual 361	java/lang/Exception:printStackTrace	()V
    //   1136: aload 4
    //   1138: astore 14
    //   1140: aload 6
    //   1142: astore 4
    //   1144: ldc -63
    //   1146: astore 19
    //   1148: aload 7
    //   1150: astore 6
    //   1152: aload 9
    //   1154: astore 16
    //   1156: aload 11
    //   1158: astore 9
    //   1160: aload 13
    //   1162: astore 17
    //   1164: aload 15
    //   1166: astore 18
    //   1168: aload 14
    //   1170: astore 11
    //   1172: aload 10
    //   1174: astore 7
    //   1176: aload 16
    //   1178: astore 13
    //   1180: aload 12
    //   1182: astore 14
    //   1184: aload 9
    //   1186: astore 15
    //   1188: aload 19
    //   1190: astore 16
    //   1192: goto -515 -> 677
    //   1195: astore_2
    //   1196: ldc -7
    //   1198: astore_2
    //   1199: goto -185 -> 1014
    //   1202: astore_2
    //   1203: ldc -7
    //   1205: astore 4
    //   1207: ldc -7
    //   1209: astore_3
    //   1210: aload 17
    //   1212: astore 10
    //   1214: aload 16
    //   1216: astore 5
    //   1218: aload 14
    //   1220: astore 8
    //   1222: aload 13
    //   1224: astore 7
    //   1226: ldc -7
    //   1228: astore 6
    //   1230: aload 12
    //   1232: astore 9
    //   1234: ldc -7
    //   1236: astore 12
    //   1238: aload_2
    //   1239: astore 14
    //   1241: aload 4
    //   1243: astore_2
    //   1244: aload 12
    //   1246: astore 4
    //   1248: aload 18
    //   1250: astore 12
    //   1252: aload 19
    //   1254: astore 13
    //   1256: goto -125 -> 1131
    //   1259: astore 14
    //   1261: ldc -7
    //   1263: astore 6
    //   1265: aload 5
    //   1267: astore 9
    //   1269: ldc -7
    //   1271: astore 12
    //   1273: ldc -7
    //   1275: astore_3
    //   1276: ldc -7
    //   1278: astore 10
    //   1280: aload_2
    //   1281: astore 5
    //   1283: ldc -7
    //   1285: astore 8
    //   1287: ldc -7
    //   1289: astore 7
    //   1291: ldc -7
    //   1293: astore 13
    //   1295: ldc -7
    //   1297: astore_2
    //   1298: ldc -7
    //   1300: astore 16
    //   1302: aload 4
    //   1304: astore 11
    //   1306: aload 16
    //   1308: astore 4
    //   1310: goto -179 -> 1131
    //   1313: astore 14
    //   1315: ldc -7
    //   1317: astore_3
    //   1318: aload 6
    //   1320: astore 12
    //   1322: aload 4
    //   1324: astore 11
    //   1326: ldc -7
    //   1328: astore 4
    //   1330: ldc -7
    //   1332: astore 10
    //   1334: aload_2
    //   1335: astore 7
    //   1337: ldc -7
    //   1339: astore 8
    //   1341: ldc -7
    //   1343: astore 16
    //   1345: ldc -7
    //   1347: astore 6
    //   1349: aload 5
    //   1351: astore 9
    //   1353: ldc -7
    //   1355: astore 13
    //   1357: ldc -7
    //   1359: astore_2
    //   1360: aload 7
    //   1362: astore 5
    //   1364: aload 16
    //   1366: astore 7
    //   1368: goto -237 -> 1131
    //   1371: astore 14
    //   1373: aload 6
    //   1375: astore 12
    //   1377: aload_3
    //   1378: astore 6
    //   1380: aload 4
    //   1382: astore 11
    //   1384: ldc -7
    //   1386: astore 4
    //   1388: aload 7
    //   1390: astore 13
    //   1392: aload_2
    //   1393: astore 7
    //   1395: aload 8
    //   1397: astore 17
    //   1399: aload_3
    //   1400: astore 8
    //   1402: aload 5
    //   1404: astore 18
    //   1406: aload 10
    //   1408: astore 16
    //   1410: ldc -7
    //   1412: astore_2
    //   1413: aload 6
    //   1415: astore_3
    //   1416: aload 8
    //   1418: astore 6
    //   1420: aload 7
    //   1422: astore 5
    //   1424: aload 9
    //   1426: astore 8
    //   1428: aload 17
    //   1430: astore 7
    //   1432: aload 13
    //   1434: astore 10
    //   1436: aload 18
    //   1438: astore 9
    //   1440: aload 16
    //   1442: astore 13
    //   1444: goto -313 -> 1131
    //   1447: astore 14
    //   1449: aload 10
    //   1451: astore 13
    //   1453: aload 12
    //   1455: astore 18
    //   1457: aload 6
    //   1459: astore 12
    //   1461: aload_3
    //   1462: astore 6
    //   1464: aload 5
    //   1466: astore 16
    //   1468: aload_3
    //   1469: astore 5
    //   1471: aload 8
    //   1473: astore 10
    //   1475: aload 9
    //   1477: astore 8
    //   1479: aload_2
    //   1480: astore 19
    //   1482: aload 7
    //   1484: astore 9
    //   1486: aload 4
    //   1488: astore 17
    //   1490: aload 11
    //   1492: astore 4
    //   1494: aload 18
    //   1496: astore_2
    //   1497: aload 6
    //   1499: astore_3
    //   1500: aload 5
    //   1502: astore 6
    //   1504: aload 19
    //   1506: astore 5
    //   1508: aload 10
    //   1510: astore 7
    //   1512: aload 9
    //   1514: astore 10
    //   1516: aload 16
    //   1518: astore 9
    //   1520: aload 17
    //   1522: astore 11
    //   1524: goto -393 -> 1131
    // Local variable table:
    //   start	length	slot	name	signature
    //   530	540	0	i	int
    //   130	469	1	j	int
    //   33	1094	2	localObject1	Object
    //   1195	1	2	localException1	Exception
    //   1198	1	2	str1	String
    //   1202	37	2	localException2	Exception
    //   1243	254	2	localObject2	Object
    //   2	1498	3	localObject3	Object
    //   9	1484	4	localObject4	Object
    //   17	1490	5	localObject5	Object
    //   13	1490	6	localObject6	Object
    //   21	1490	7	localObject7	Object
    //   25	1453	8	localObject8	Object
    //   29	1490	9	localObject9	Object
    //   93	1422	10	localObject10	Object
    //   53	1470	11	localObject11	Object
    //   61	1399	12	localObject12	Object
    //   69	1383	13	localObject13	Object
    //   73	704	14	localObject14	Object
    //   1079	53	14	localException3	Exception
    //   1138	102	14	localObject15	Object
    //   1259	1	14	localException4	Exception
    //   1313	1	14	localException5	Exception
    //   1371	1	14	localException6	Exception
    //   1447	1	14	localException7	Exception
    //   46	1141	15	localObject16	Object
    //   76	1441	16	localObject17	Object
    //   65	1456	17	localObject18	Object
    //   57	1438	18	localObject19	Object
    //   49	1456	19	localObject20	Object
    //   5	602	20	str2	String
    // Exception table:
    //   from	to	target	type
    //   34	48	1079	java/lang/Exception
    //   972	1014	1195	java/lang/Exception
    //   78	95	1202	java/lang/Exception
    //   125	131	1202	java/lang/Exception
    //   174	187	1202	java/lang/Exception
    //   217	222	1202	java/lang/Exception
    //   269	274	1202	java/lang/Exception
    //   321	332	1202	java/lang/Exception
    //   362	373	1202	java/lang/Exception
    //   403	414	1202	java/lang/Exception
    //   444	455	1202	java/lang/Exception
    //   486	496	1202	java/lang/Exception
    //   222	235	1259	java/lang/Exception
    //   274	287	1313	java/lang/Exception
    //   496	529	1371	java/lang/Exception
    //   531	579	1371	java/lang/Exception
    //   610	621	1447	java/lang/Exception
  }
  
  private static String h()
  {
    Object localObject1 = "";
    try
    {
      Object localObject2 = PayPal.getInstance().getParentContext().openFileInput("DeviceReferenceToken");
      byte[] arrayOfByte = new byte[((FileInputStream)localObject2).available()];
      ((FileInputStream)localObject2).read(arrayOfByte);
      ((FileInputStream)localObject2).close();
      localObject2 = new String(arrayOfByte);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPRemoveDeviceAuthorizationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1</Version><SessionToken xsi:type=\"ns:MEPSessionToken\">" + "" + "</SessionToken>" + "<DeviceReferenceToken xsi:type=\"xs:string\">" + (String)localObject1 + "</DeviceReferenceToken>" + "</Request>" + "</MEPRemoveDeviceAuthorizationReq>" + "</SOAP-ENV:Body>" + "</SOAP-ENV:Envelope>";
  }
  
  public final void a(int paramInt)
  {
    this.d = paramInt;
  }
}
