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
  
  /* Error */
  private static String g()
  {
    // Byte code:
    //   0: ldc -9
    //   2: astore 14
    //   4: ldc -9
    //   6: astore 13
    //   8: ldc -9
    //   10: astore 15
    //   12: ldc -9
    //   14: astore 12
    //   16: ldc -9
    //   18: astore 11
    //   20: ldc -9
    //   22: astore 5
    //   24: ldc -9
    //   26: astore_2
    //   27: ldc -9
    //   29: astore_3
    //   30: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   33: invokevirtual 127	com/paypal/android/MECL/PayPal:getAppID	()Ljava/lang/String;
    //   36: astore 4
    //   38: ldc -9
    //   40: astore 6
    //   42: aload 4
    //   44: ldc_w 569
    //   47: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   50: astore 9
    //   52: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   55: invokevirtual 366	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   58: astore 4
    //   60: aload 9
    //   62: astore 10
    //   64: aload 4
    //   66: ldc_w 576
    //   69: invokevirtual 580	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   72: checkcast 582	android/telephony/TelephonyManager
    //   75: astore 7
    //   77: aload 7
    //   79: invokevirtual 585	android/telephony/TelephonyManager:getPhoneType	()I
    //   82: istore_1
    //   83: ldc_w 587
    //   86: astore 4
    //   88: iload_1
    //   89: iconst_1
    //   90: if_icmpne +14 -> 104
    //   93: ldc_w 589
    //   96: astore 4
    //   98: goto +6 -> 104
    //   101: goto +537 -> 638
    //   104: aload 7
    //   106: invokevirtual 592	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   109: ldc_w 569
    //   112: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   115: astore 17
    //   117: aload 13
    //   119: astore 9
    //   121: getstatic 598	android/os/Build:DEVICE	Ljava/lang/String;
    //   124: astore 8
    //   126: aload 8
    //   128: ldc_w 480
    //   131: ldc_w 600
    //   134: invokevirtual 495	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   137: astore 7
    //   139: aload 13
    //   141: astore 9
    //   143: aload 7
    //   145: astore 15
    //   147: getstatic 603	android/os/Build:MODEL	Ljava/lang/String;
    //   150: astore 13
    //   152: aload 13
    //   154: ldc_w 480
    //   157: ldc_w 600
    //   160: invokevirtual 495	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   163: astore 8
    //   165: aload 8
    //   167: astore 9
    //   169: aload 7
    //   171: astore 15
    //   173: ldc_w 605
    //   176: ldc_w 569
    //   179: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   182: astore 13
    //   184: getstatic 610	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   187: ldc_w 569
    //   190: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   193: astore 15
    //   195: ldc_w 399
    //   198: ldc_w 569
    //   201: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   204: astore 14
    //   206: invokestatic 151	com/paypal/android/MECL/PayPal:getVersion	()Ljava/lang/String;
    //   209: ldc_w 569
    //   212: invokestatic 574	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   215: astore 12
    //   217: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   220: invokevirtual 366	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   223: invokevirtual 613	android/content/Context:getPackageName	()Ljava/lang/String;
    //   226: astore 6
    //   228: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   231: invokevirtual 366	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   234: invokevirtual 617	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   237: astore 16
    //   239: aload 16
    //   241: iconst_0
    //   242: invokevirtual 623	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   245: astore 11
    //   247: aload 11
    //   249: new 625	android/content/pm/ApplicationInfo$DisplayNameComparator
    //   252: dup
    //   253: aload 16
    //   255: invokespecial 628	android/content/pm/ApplicationInfo$DisplayNameComparator:<init>	(Landroid/content/pm/PackageManager;)V
    //   258: invokestatic 634	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   261: iconst_0
    //   262: istore_0
    //   263: aload_2
    //   264: astore 9
    //   266: aload_3
    //   267: astore 5
    //   269: iload_0
    //   270: aload 11
    //   272: invokeinterface 637 1 0
    //   277: if_icmpge +1038 -> 1315
    //   280: aload_2
    //   281: astore 9
    //   283: aload_3
    //   284: astore 5
    //   286: aload 11
    //   288: iload_0
    //   289: invokeinterface 638 2 0
    //   294: checkcast 640	android/content/pm/ApplicationInfo
    //   297: astore 18
    //   299: aload_2
    //   300: astore 9
    //   302: aload_3
    //   303: astore 5
    //   305: aload 18
    //   307: getfield 643	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   310: aload 6
    //   312: invokevirtual 286	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   315: ifeq +993 -> 1308
    //   318: aload_2
    //   319: astore 9
    //   321: aload_3
    //   322: astore 5
    //   324: aload 18
    //   326: aload 16
    //   328: invokevirtual 647	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   331: invokevirtual 648	java/lang/Object:toString	()Ljava/lang/String;
    //   334: astore_2
    //   335: goto +982 -> 1317
    //   338: iload_1
    //   339: iconst_1
    //   340: if_icmpne +10 -> 350
    //   343: ldc_w 650
    //   346: astore_3
    //   347: goto +7 -> 354
    //   350: ldc_w 652
    //   353: astore_3
    //   354: aload_2
    //   355: astore 9
    //   357: aload_3
    //   358: astore 5
    //   360: aload 17
    //   362: ldc_w 654
    //   365: invokevirtual 286	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   368: ifeq +11 -> 379
    //   371: ldc_w 259
    //   374: astore 5
    //   376: goto +7 -> 383
    //   379: ldc -86
    //   381: astore 5
    //   383: aload 5
    //   385: astore 11
    //   387: aload 6
    //   389: astore 5
    //   391: aload 4
    //   393: astore 9
    //   395: aload 10
    //   397: astore 4
    //   399: aload 14
    //   401: astore 10
    //   403: aload 17
    //   405: astore 14
    //   407: aload 13
    //   409: astore 16
    //   411: aload 15
    //   413: astore 17
    //   415: aload 12
    //   417: astore 18
    //   419: aload 6
    //   421: astore 19
    //   423: goto +310 -> 733
    //   426: astore 11
    //   428: aload 9
    //   430: astore_2
    //   431: aload 5
    //   433: astore_3
    //   434: goto +10 -> 444
    //   437: astore 11
    //   439: goto +5 -> 444
    //   442: astore 11
    //   444: aload 6
    //   446: astore 5
    //   448: aload 11
    //   450: astore 9
    //   452: goto +26 -> 478
    //   455: astore 9
    //   457: ldc -9
    //   459: astore 6
    //   461: goto +17 -> 478
    //   464: astore 9
    //   466: ldc -9
    //   468: astore 11
    //   470: aload 6
    //   472: astore 12
    //   474: aload 11
    //   476: astore 6
    //   478: aload 9
    //   480: astore 16
    //   482: aload 6
    //   484: astore 9
    //   486: goto +17 -> 503
    //   489: astore 16
    //   491: ldc -9
    //   493: astore 14
    //   495: ldc -9
    //   497: astore 9
    //   499: aload 6
    //   501: astore 12
    //   503: aload 15
    //   505: astore 11
    //   507: aload 7
    //   509: astore 15
    //   511: aload 12
    //   513: astore 6
    //   515: aload 9
    //   517: astore 7
    //   519: goto +55 -> 574
    //   522: astore 9
    //   524: aload 13
    //   526: astore 12
    //   528: aload 7
    //   530: astore 15
    //   532: goto +26 -> 558
    //   535: astore 9
    //   537: aload 13
    //   539: astore 8
    //   541: aload 7
    //   543: astore 15
    //   545: goto +13 -> 558
    //   548: astore 9
    //   550: aload 8
    //   552: astore 15
    //   554: aload 13
    //   556: astore 8
    //   558: ldc -9
    //   560: astore 14
    //   562: ldc -9
    //   564: astore 7
    //   566: aload 12
    //   568: astore 13
    //   570: aload 9
    //   572: astore 16
    //   574: aload 8
    //   576: astore 9
    //   578: aload 13
    //   580: astore 12
    //   582: aload 7
    //   584: astore 8
    //   586: goto +13 -> 599
    //   589: astore 16
    //   591: ldc -9
    //   593: astore 14
    //   595: ldc -9
    //   597: astore 8
    //   599: aload 14
    //   601: astore 7
    //   603: aload 17
    //   605: astore 14
    //   607: aload 9
    //   609: astore 13
    //   611: aload 10
    //   613: astore 9
    //   615: goto +65 -> 680
    //   618: astore 16
    //   620: goto -519 -> 101
    //   623: astore 4
    //   625: goto +5 -> 630
    //   628: astore 4
    //   630: aload 4
    //   632: astore 16
    //   634: ldc -9
    //   636: astore 4
    //   638: ldc -9
    //   640: astore 7
    //   642: ldc -9
    //   644: astore 8
    //   646: goto +34 -> 680
    //   649: astore 4
    //   651: goto +5 -> 656
    //   654: astore 4
    //   656: aload 4
    //   658: astore 16
    //   660: ldc -9
    //   662: astore 7
    //   664: ldc -9
    //   666: astore 9
    //   668: ldc -9
    //   670: astore 6
    //   672: ldc -9
    //   674: astore 4
    //   676: ldc -9
    //   678: astore 8
    //   680: aload 16
    //   682: invokevirtual 423	java/lang/Exception:printStackTrace	()V
    //   685: aload 7
    //   687: astore 10
    //   689: aload 9
    //   691: astore 20
    //   693: ldc -86
    //   695: astore 9
    //   697: aload 8
    //   699: astore 19
    //   701: aload 6
    //   703: astore 18
    //   705: aload 11
    //   707: astore 17
    //   709: aload 12
    //   711: astore 16
    //   713: aload 15
    //   715: astore 7
    //   717: aload 13
    //   719: astore 8
    //   721: aload 9
    //   723: astore 11
    //   725: aload 4
    //   727: astore 9
    //   729: aload 20
    //   731: astore 4
    //   733: new 141	java/lang/StringBuilder
    //   736: dup
    //   737: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   740: astore 6
    //   742: aload 6
    //   744: ldc_w 656
    //   747: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: pop
    //   751: aload 6
    //   753: aload 4
    //   755: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: pop
    //   759: aload 6
    //   761: ldc_w 658
    //   764: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   767: pop
    //   768: aload 6
    //   770: ldc_w 660
    //   773: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: pop
    //   777: aload 6
    //   779: ldc_w 662
    //   782: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   785: pop
    //   786: aload 6
    //   788: ldc_w 664
    //   791: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   794: pop
    //   795: aload 6
    //   797: aload 14
    //   799: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: pop
    //   803: aload 6
    //   805: ldc_w 666
    //   808: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   811: pop
    //   812: aload 6
    //   814: ldc_w 668
    //   817: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   820: pop
    //   821: aload 6
    //   823: aload 9
    //   825: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   828: pop
    //   829: aload 6
    //   831: ldc_w 670
    //   834: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: pop
    //   838: aload 6
    //   840: ldc_w 672
    //   843: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   846: pop
    //   847: aload 6
    //   849: ldc_w 674
    //   852: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: pop
    //   856: aload 6
    //   858: aload 7
    //   860: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   863: pop
    //   864: aload 6
    //   866: ldc_w 676
    //   869: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   872: pop
    //   873: aload 6
    //   875: ldc_w 678
    //   878: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   881: pop
    //   882: aload 6
    //   884: aload 8
    //   886: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   889: pop
    //   890: aload 6
    //   892: ldc_w 680
    //   895: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   898: pop
    //   899: aload 6
    //   901: ldc_w 682
    //   904: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   907: pop
    //   908: aload 6
    //   910: aload 16
    //   912: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   915: pop
    //   916: aload 6
    //   918: ldc_w 684
    //   921: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   924: pop
    //   925: aload 6
    //   927: ldc_w 686
    //   930: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   933: pop
    //   934: aload 6
    //   936: aload 17
    //   938: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   941: pop
    //   942: aload 6
    //   944: ldc_w 688
    //   947: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: pop
    //   951: aload 6
    //   953: ldc_w 690
    //   956: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: pop
    //   960: aload 6
    //   962: aload 10
    //   964: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   967: pop
    //   968: aload 6
    //   970: ldc_w 692
    //   973: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   976: pop
    //   977: aload 6
    //   979: ldc_w 694
    //   982: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: pop
    //   986: aload 6
    //   988: aload 11
    //   990: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: pop
    //   994: aload 6
    //   996: ldc_w 696
    //   999: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: pop
    //   1003: aload 6
    //   1005: ldc_w 698
    //   1008: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1011: pop
    //   1012: aload 6
    //   1014: ldc_w 700
    //   1017: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1020: pop
    //   1021: aload 6
    //   1023: ldc_w 702
    //   1026: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1029: pop
    //   1030: aload 6
    //   1032: ldc_w 704
    //   1035: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1038: pop
    //   1039: aload 6
    //   1041: aload 19
    //   1043: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1046: pop
    //   1047: aload 6
    //   1049: ldc_w 706
    //   1052: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1055: pop
    //   1056: aload 6
    //   1058: ldc_w 708
    //   1061: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1064: pop
    //   1065: aload 6
    //   1067: ldc_w 710
    //   1070: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1073: pop
    //   1074: aload 6
    //   1076: aload 5
    //   1078: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1081: pop
    //   1082: aload 6
    //   1084: ldc_w 712
    //   1087: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1090: pop
    //   1091: aload 6
    //   1093: ldc_w 714
    //   1096: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1099: pop
    //   1100: aload 6
    //   1102: aload_2
    //   1103: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1106: pop
    //   1107: aload 6
    //   1109: ldc_w 716
    //   1112: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1115: pop
    //   1116: aload 6
    //   1118: ldc_w 718
    //   1121: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1124: pop
    //   1125: aload 6
    //   1127: aload_3
    //   1128: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1131: pop
    //   1132: aload 6
    //   1134: ldc_w 720
    //   1137: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1140: pop
    //   1141: aload 6
    //   1143: ldc_w 722
    //   1146: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1149: pop
    //   1150: aload 6
    //   1152: ldc_w 724
    //   1155: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1158: pop
    //   1159: aload 6
    //   1161: aload 18
    //   1163: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1166: pop
    //   1167: aload 6
    //   1169: ldc_w 726
    //   1172: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1175: pop
    //   1176: aload 6
    //   1178: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1181: astore_3
    //   1182: invokestatic 93	com/paypal/android/MECL/PayPal:getInstance	()Lcom/paypal/android/MECL/PayPal;
    //   1185: invokevirtual 366	com/paypal/android/MECL/PayPal:getParentContext	()Landroid/content/Context;
    //   1188: ldc_w 362
    //   1191: invokevirtual 730	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   1194: astore_2
    //   1195: aload_2
    //   1196: invokevirtual 733	java/io/FileInputStream:available	()I
    //   1199: newarray byte
    //   1201: astore 4
    //   1203: aload_2
    //   1204: aload 4
    //   1206: invokevirtual 736	java/io/FileInputStream:read	([B)I
    //   1209: pop
    //   1210: aload_2
    //   1211: invokevirtual 737	java/io/FileInputStream:close	()V
    //   1214: new 58	java/lang/String
    //   1217: dup
    //   1218: aload 4
    //   1220: invokespecial 738	java/lang/String:<init>	([B)V
    //   1223: astore_2
    //   1224: goto +6 -> 1230
    //   1227: ldc -9
    //   1229: astore_2
    //   1230: new 141	java/lang/StringBuilder
    //   1233: dup
    //   1234: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   1237: astore 4
    //   1239: aload 4
    //   1241: aload_3
    //   1242: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: pop
    //   1246: aload 4
    //   1248: ldc_w 740
    //   1251: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1254: pop
    //   1255: aload 4
    //   1257: aload_2
    //   1258: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: pop
    //   1262: aload 4
    //   1264: ldc_w 742
    //   1267: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: pop
    //   1271: aload 4
    //   1273: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1276: astore_2
    //   1277: new 141	java/lang/StringBuilder
    //   1280: dup
    //   1281: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   1284: astore_3
    //   1285: aload_3
    //   1286: aload_2
    //   1287: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1290: pop
    //   1291: aload_3
    //   1292: ldc_w 744
    //   1295: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1298: pop
    //   1299: aload_3
    //   1300: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1303: areturn
    //   1304: astore_2
    //   1305: goto -78 -> 1227
    //   1308: iload_0
    //   1309: iconst_1
    //   1310: iadd
    //   1311: istore_0
    //   1312: goto -1049 -> 263
    //   1315: aconst_null
    //   1316: astore_2
    //   1317: aload_2
    //   1318: ifnonnull +9 -> 1327
    //   1321: aload 6
    //   1323: astore_2
    //   1324: goto -986 -> 338
    //   1327: goto -989 -> 338
    // Local variable table:
    //   start	length	slot	name	signature
    //   262	1050	0	i	int
    //   82	259	1	j	int
    //   26	1261	2	localObject1	Object
    //   1304	1	2	localException1	Exception
    //   1316	8	2	localObject2	Object
    //   29	1271	3	localObject3	Object
    //   36	362	4	localObject4	Object
    //   623	1	4	localException2	Exception
    //   628	3	4	localException3	Exception
    //   636	1	4	str	String
    //   649	1	4	localException4	Exception
    //   654	3	4	localException5	Exception
    //   674	598	4	localObject5	Object
    //   22	1055	5	localObject6	Object
    //   40	1282	6	localObject7	Object
    //   75	784	7	localObject8	Object
    //   124	761	8	localObject9	Object
    //   50	401	9	localObject10	Object
    //   455	1	9	localException6	Exception
    //   464	15	9	localException7	Exception
    //   484	32	9	localObject11	Object
    //   522	1	9	localException8	Exception
    //   535	1	9	localException9	Exception
    //   548	23	9	localException10	Exception
    //   576	248	9	localObject12	Object
    //   62	901	10	localObject13	Object
    //   18	368	11	localObject14	Object
    //   426	1	11	localException11	Exception
    //   437	1	11	localException12	Exception
    //   442	7	11	localException13	Exception
    //   468	521	11	localObject15	Object
    //   14	696	12	localObject16	Object
    //   6	712	13	localObject17	Object
    //   2	796	14	localObject18	Object
    //   10	704	15	localObject19	Object
    //   237	244	16	localObject20	Object
    //   489	1	16	localException14	Exception
    //   572	1	16	localException15	Exception
    //   589	1	16	localException16	Exception
    //   618	1	16	localException17	Exception
    //   632	279	16	localObject21	Object
    //   115	822	17	localObject22	Object
    //   297	865	18	localObject23	Object
    //   421	621	19	localObject24	Object
    //   691	39	20	localObject25	Object
    // Exception table:
    //   from	to	target	type
    //   269	280	426	java/lang/Exception
    //   286	299	426	java/lang/Exception
    //   305	318	426	java/lang/Exception
    //   324	335	426	java/lang/Exception
    //   360	371	426	java/lang/Exception
    //   239	261	437	java/lang/Exception
    //   228	239	442	java/lang/Exception
    //   217	228	455	java/lang/Exception
    //   206	217	464	java/lang/Exception
    //   195	206	489	java/lang/Exception
    //   184	195	522	java/lang/Exception
    //   152	165	535	java/lang/Exception
    //   126	139	548	java/lang/Exception
    //   121	126	589	java/lang/Exception
    //   147	152	589	java/lang/Exception
    //   173	184	589	java/lang/Exception
    //   104	117	618	java/lang/Exception
    //   64	83	623	java/lang/Exception
    //   52	60	628	java/lang/Exception
    //   42	52	649	java/lang/Exception
    //   30	38	654	java/lang/Exception
    //   1182	1224	1304	java/lang/Exception
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
