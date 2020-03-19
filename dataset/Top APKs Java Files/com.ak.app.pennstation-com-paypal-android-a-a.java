package com.paypal.android.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalActivity;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public final class a
{
  private HttpPost a;
  private HttpGet b;
  private DefaultHttpClient c = null;
  private int d = -1;
  private int e = -1;
  private Hashtable<String, Object> f;
  private Thread g = new f(this);
  
  public a() {}
  
  public static String a(String paramString)
  {
    PackageManager localPackageManager = PayPal.getInstance().get_parentContext().getPackageManager();
    List localList = localPackageManager.getInstalledApplications(0);
    Collections.sort(localList, new ApplicationInfo.DisplayNameComparator(localPackageManager));
    int i = 0;
    while (i < localList.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      if (localApplicationInfo.packageName.equals(paramString)) {
        return localApplicationInfo.loadLabel(localPackageManager).toString();
      }
      i += 1;
    }
    return null;
  }
  
  private String a(ArrayList<BasicNameValuePair> paramArrayList1, ArrayList<BasicNameValuePair> paramArrayList2)
  {
    String str = "";
    Object localObject1 = str;
    try
    {
      if (this.c == null)
      {
        localObject1 = str;
        this.c = new DefaultHttpClient();
      }
      localObject1 = str;
      Object localObject2 = e();
      localObject1 = str;
      this.f.put("SessionToken", "");
      localObject1 = str;
      this.a = new HttpPost((String)localObject2 + "MEPAuthenticateUser?");
      localObject1 = str;
      localObject2 = new ArrayList();
      localObject1 = str;
      ((ArrayList)localObject2).addAll(paramArrayList1);
      localObject1 = str;
      ((ArrayList)localObject2).addAll(paramArrayList2);
      localObject1 = str;
      this.a.setEntity(new UrlEncodedFormEntity((List)localObject2, "UTF-8"));
      localObject1 = str;
      paramArrayList2 = this.c.execute(this.a).getEntity();
      localObject1 = str;
      localObject2 = paramArrayList2.getContent();
      localObject1 = str;
      paramArrayList1 = new StringBuffer();
      localObject1 = str;
      for (int i = ((InputStream)localObject2).read(); i != -1; i = ((InputStream)localObject2).read())
      {
        localObject1 = str;
        paramArrayList1.append((char)i);
        localObject1 = str;
      }
      localObject1 = str;
      paramArrayList1 = paramArrayList1.toString();
      if (paramArrayList2 != null)
      {
        localObject1 = paramArrayList1;
        paramArrayList2.consumeContent();
      }
      localObject1 = paramArrayList1;
      ((InputStream)localObject2).close();
      localObject1 = paramArrayList1;
      this.a = null;
      localObject1 = paramArrayList1;
      this.c = null;
      return paramArrayList1;
    }
    catch (Exception paramArrayList1) {}
    return localObject1;
  }
  
  public static final boolean a()
  {
    try
    {
      int i = ((String)PayPalActivity._networkHandler.f.get("PayButtonEnable")).compareToIgnoreCase("true");
      if (i == 0) {
        return true;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    boolean bool = false;
    for (;;)
    {
      ArrayList localArrayList2;
      ArrayList localArrayList1;
      Object localObject;
      try
      {
        localArrayList2 = new ArrayList();
        localArrayList1 = new ArrayList();
        if (paramString1.indexOf("@") > 0)
        {
          i = 1;
          if (i != 0)
          {
            localArrayList2.add(new BasicNameValuePair("Email", paramString1));
            localArrayList2.add(new BasicNameValuePair("Password", paramString2));
            if (URLEncoder.encode(((TelephonyManager)PayPal.getInstance().get_parentContext().getSystemService("phone")).getDeviceId(), "utf-8").equals("000000000000000")) {
              localArrayList2.add(new BasicNameValuePair("isSimulator", "1"));
            }
            localArrayList1.clear();
            localArrayList1.addAll(i());
          }
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return false;
      }
      try
      {
        paramString1 = PayPalActivity.getInstance().getBaseContext().openFileInput("save_login_successful");
        paramString2 = new byte[paramString1.available()];
        paramString1.read(paramString2);
        paramString1.close();
        i = new String(paramString2).indexOf((String)this.f.get("usernameOrPhone"));
        if (i < 0) {
          break label928;
        }
        i = 1;
      }
      catch (Exception paramString1)
      {
        i = 0;
        continue;
      }
      if (i != 0)
      {
        paramString1 = a(localArrayList2, localArrayList1);
        paramString2 = localArrayList1;
        localObject = paramString1;
        if (paramString1.contains("ErrorId=10869"))
        {
          paramString2.clear();
          localObject = a(localArrayList2, h());
        }
        if (f((String)localObject))
        {
          return false;
          PayPalActivity.getInstance().getSystemService("phone");
          String str = Locale.getDefault().getCountry();
          if (paramString1.indexOf("+") != -1)
          {
            paramString1 = paramString1.substring(1);
            localObject = new String[52];
            localObject[0] = "1";
            localObject[1] = "44";
            localObject[2] = "61";
            localObject[3] = "54";
            localObject[4] = "43";
            localObject[5] = "32";
            localObject[6] = "55";
            localObject[7] = "41";
            localObject[8] = "56";
            localObject[9] = "86";
            localObject[10] = "506";
            localObject[11] = "357";
            localObject[12] = "420";
            localObject[13] = "49";
            localObject[14] = "45";
            localObject[15] = "593";
            localObject[16] = "372";
            localObject[17] = "34";
            localObject[18] = "358";
            localObject[19] = "33";
            localObject[20] = "30";
            localObject[21] = "852";
            localObject[22] = "36";
            localObject[23] = "353";
            localObject[24] = "972";
            localObject[25] = "91";
            localObject[26] = "354";
            localObject[27] = "39";
            localObject[28] = "81";
            localObject[29] = "82";
            localObject[30] = "370";
            localObject[31] = "352";
            localObject[32] = "371";
            localObject[33] = "377";
            localObject[34] = "356";
            localObject[35] = "52";
            localObject[36] = "60";
            localObject[37] = "31";
            localObject[38] = "47";
            localObject[39] = "64";
            localObject[40] = "48";
            localObject[41] = "351";
            localObject[42] = "46";
            localObject[43] = "65";
            localObject[44] = "386";
            localObject[45] = "421";
            localObject[46] = "66";
            localObject[47] = "90";
            localObject[48] = "886";
            localObject[49] = "598";
            localObject[50] = "58";
            localObject[51] = "27";
            i = 0;
            if ((i < localObject.length) && (!paramString1.startsWith(localObject[i])))
            {
              i += 1;
              continue;
            }
            localObject = localObject[i] + paramString1;
            localArrayList2.add(new BasicNameValuePair("Phone", (String)localObject));
            localArrayList2.add(new BasicNameValuePair("PIN", paramString2));
            continue;
          }
          localObject = paramString1;
          if (paramString1.startsWith(e(str))) {
            continue;
          }
          localObject = e(str) + paramString1;
          continue;
        }
      }
      else
      {
        localArrayList1.clear();
        paramString2 = h();
        paramString1 = a(localArrayList2, paramString2);
        continue;
      }
      if (((String)localObject).contains("SessionToken="))
      {
        paramString1 = ((String)localObject).substring(((String)localObject).indexOf("SessionToken=") + "SessionToken=".length());
        this.f.put("SessionToken", paramString1);
      }
      i = ((String)this.f.get("SessionToken")).length();
      if (i > 0) {
        bool = true;
      }
      return bool;
      i = 0;
      continue;
      label928:
      i = 0;
    }
  }
  
  public static void b()
  {
    if (PayPalActivity._networkHandler == null) {
      PayPalActivity._networkHandler = new a();
    }
    if (PayPalActivity._networkHandler.f == null) {
      PayPalActivity._networkHandler.f = new Hashtable();
    }
    Hashtable localHashtable = PayPalActivity._networkHandler.f;
    if (PayPal.getInstance().receiverPaysFee()) {}
    for (String str = "ApplyFeeToReceiver";; str = "ApplyFeeToSender")
    {
      localHashtable.put("FeeBearer", str);
      if (PayPal.getInstance().get_server() == 2) {
        PayPalActivity._networkHandler.f.put("PayButtonEnable", "true");
      }
      if (!PayPalActivity._networkHandler.g.isAlive()) {
        PayPalActivity._networkHandler.g.start();
      }
      return;
    }
  }
  
  private static boolean b(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "10818";
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
  
  private static boolean c(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = new String[29];
    arrayOfString[0] = "10800";
    arrayOfString[1] = "10801";
    arrayOfString[2] = "10802";
    arrayOfString[3] = "10804";
    arrayOfString[4] = "10806";
    arrayOfString[5] = "10808";
    arrayOfString[6] = "10809";
    arrayOfString[7] = "10810";
    arrayOfString[8] = "10811";
    arrayOfString[9] = "10812";
    arrayOfString[10] = "10813";
    arrayOfString[11] = "10815";
    arrayOfString[12] = "10820";
    arrayOfString[13] = "10825";
    arrayOfString[14] = "10849";
    arrayOfString[15] = "10004";
    arrayOfString[16] = "10805";
    arrayOfString[17] = "10819";
    arrayOfString[18] = "10821";
    arrayOfString[19] = "10822";
    arrayOfString[20] = "10823";
    arrayOfString[21] = "10824";
    arrayOfString[22] = "10001";
    arrayOfString[23] = "10850";
    arrayOfString[24] = "99999";
    arrayOfString[25] = "10858";
    arrayOfString[26] = "10859";
    arrayOfString[27] = "10860";
    arrayOfString[28] = "10861";
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
  
  /* Error */
  private String d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   4: ifnonnull +14 -> 18
    //   7: aload_0
    //   8: new 108	org/apache/http/impl/client/DefaultHttpClient
    //   11: dup
    //   12: invokespecial 109	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   15: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   18: aload_0
    //   19: new 123	org/apache/http/client/methods/HttpPost
    //   22: dup
    //   23: new 125	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   30: invokestatic 111	com/paypal/android/a/a:e	()Ljava/lang/String;
    //   33: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: ldc_w 529
    //   39: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokespecial 136	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   48: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   51: new 140	java/util/ArrayList
    //   54: dup
    //   55: invokespecial 141	java/util/ArrayList:<init>	()V
    //   58: astore 5
    //   60: aload 5
    //   62: new 222	org/apache/http/message/BasicNameValuePair
    //   65: dup
    //   66: ldc_w 531
    //   69: aload_1
    //   70: invokespecial 227	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   73: invokevirtual 230	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   76: pop
    //   77: aload_0
    //   78: getfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   81: new 147	org/apache/http/client/entity/UrlEncodedFormEntity
    //   84: dup
    //   85: aload 5
    //   87: ldc -107
    //   89: invokespecial 152	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   92: invokevirtual 156	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   95: aload_0
    //   96: getfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   99: aload_0
    //   100: getfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   103: invokevirtual 160	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   106: astore_1
    //   107: aload_1
    //   108: invokeinterface 166 1 0
    //   113: astore 8
    //   115: aload_1
    //   116: invokeinterface 535 1 0
    //   121: astore_1
    //   122: aload_1
    //   123: ifnull +606 -> 729
    //   126: iconst_0
    //   127: istore 4
    //   129: iconst_0
    //   130: istore_2
    //   131: iload_2
    //   132: istore_3
    //   133: iload 4
    //   135: aload_1
    //   136: arraylength
    //   137: if_icmpge +37 -> 174
    //   140: aload_1
    //   141: iload 4
    //   143: aaload
    //   144: invokeinterface 540 1 0
    //   149: ldc_w 542
    //   152: invokevirtual 545	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   155: ifne +565 -> 720
    //   158: aload_1
    //   159: iload 4
    //   161: aaload
    //   162: invokeinterface 548 1 0
    //   167: invokestatic 553	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   170: istore_2
    //   171: goto +549 -> 720
    //   174: aload 8
    //   176: invokeinterface 172 1 0
    //   181: astore_1
    //   182: new 555	java/io/DataInputStream
    //   185: dup
    //   186: aload_1
    //   187: invokespecial 558	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   190: astore 5
    //   192: iload_3
    //   193: istore_2
    //   194: aload 5
    //   196: astore 7
    //   198: aload_1
    //   199: astore 6
    //   201: aload_1
    //   202: invokevirtual 559	java/io/InputStream:available	()I
    //   205: iload_3
    //   206: if_icmple +15 -> 221
    //   209: aload 5
    //   211: astore 7
    //   213: aload_1
    //   214: astore 6
    //   216: aload_1
    //   217: invokevirtual 559	java/io/InputStream:available	()I
    //   220: istore_2
    //   221: iload_2
    //   222: istore_3
    //   223: aload 5
    //   225: astore 7
    //   227: aload_1
    //   228: astore 6
    //   230: aload 5
    //   232: invokevirtual 560	java/io/DataInputStream:available	()I
    //   235: iload_2
    //   236: if_icmple +16 -> 252
    //   239: aload 5
    //   241: astore 7
    //   243: aload_1
    //   244: astore 6
    //   246: aload 5
    //   248: invokevirtual 560	java/io/DataInputStream:available	()I
    //   251: istore_3
    //   252: iload_3
    //   253: ifeq +139 -> 392
    //   256: aload 5
    //   258: astore 7
    //   260: aload_1
    //   261: astore 6
    //   263: iload_3
    //   264: newarray byte
    //   266: astore 9
    //   268: aload 5
    //   270: astore 7
    //   272: aload_1
    //   273: astore 6
    //   275: aload 5
    //   277: aload 9
    //   279: invokevirtual 563	java/io/DataInputStream:readFully	([B)V
    //   282: aload 5
    //   284: astore 7
    //   286: aload_1
    //   287: astore 6
    //   289: new 89	java/lang/String
    //   292: dup
    //   293: aload 9
    //   295: invokespecial 288	java/lang/String:<init>	([B)V
    //   298: astore 9
    //   300: aload 8
    //   302: ifnull +17 -> 319
    //   305: aload 5
    //   307: astore 7
    //   309: aload_1
    //   310: astore 6
    //   312: aload 8
    //   314: invokeinterface 187 1 0
    //   319: aload 5
    //   321: astore 7
    //   323: aload_1
    //   324: astore 6
    //   326: aload 5
    //   328: invokevirtual 564	java/io/DataInputStream:close	()V
    //   331: aload 5
    //   333: astore 7
    //   335: aload_1
    //   336: astore 6
    //   338: aload_1
    //   339: invokevirtual 190	java/io/InputStream:close	()V
    //   342: aload 5
    //   344: astore 7
    //   346: aload_1
    //   347: astore 6
    //   349: aload_0
    //   350: aconst_null
    //   351: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   354: aload 5
    //   356: astore 7
    //   358: aload_1
    //   359: astore 6
    //   361: aload_0
    //   362: aconst_null
    //   363: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   366: aload 5
    //   368: invokevirtual 564	java/io/DataInputStream:close	()V
    //   371: aload_1
    //   372: ifnull +7 -> 379
    //   375: aload_1
    //   376: invokevirtual 190	java/io/InputStream:close	()V
    //   379: aload_0
    //   380: aconst_null
    //   381: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   384: aload_0
    //   385: aconst_null
    //   386: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   389: aload 9
    //   391: areturn
    //   392: aload 5
    //   394: astore 7
    //   396: aload_1
    //   397: astore 6
    //   399: new 174	java/lang/StringBuffer
    //   402: dup
    //   403: invokespecial 175	java/lang/StringBuffer:<init>	()V
    //   406: astore 9
    //   408: aload 5
    //   410: astore 7
    //   412: aload_1
    //   413: astore 6
    //   415: aload 5
    //   417: invokevirtual 565	java/io/DataInputStream:read	()I
    //   420: istore_2
    //   421: iload_2
    //   422: iconst_m1
    //   423: if_icmpeq +34 -> 457
    //   426: aload 5
    //   428: astore 7
    //   430: aload_1
    //   431: astore 6
    //   433: aload 9
    //   435: iload_2
    //   436: i2c
    //   437: invokevirtual 183	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   440: pop
    //   441: aload 5
    //   443: astore 7
    //   445: aload_1
    //   446: astore 6
    //   448: aload 5
    //   450: invokevirtual 565	java/io/DataInputStream:read	()I
    //   453: istore_2
    //   454: goto -33 -> 421
    //   457: aload 5
    //   459: astore 7
    //   461: aload_1
    //   462: astore 6
    //   464: aload 9
    //   466: invokevirtual 184	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   469: astore 9
    //   471: aload 8
    //   473: ifnull +17 -> 490
    //   476: aload 5
    //   478: astore 7
    //   480: aload_1
    //   481: astore 6
    //   483: aload 8
    //   485: invokeinterface 187 1 0
    //   490: aload 5
    //   492: astore 7
    //   494: aload_1
    //   495: astore 6
    //   497: aload 5
    //   499: invokevirtual 564	java/io/DataInputStream:close	()V
    //   502: aload 5
    //   504: astore 7
    //   506: aload_1
    //   507: astore 6
    //   509: aload_1
    //   510: invokevirtual 190	java/io/InputStream:close	()V
    //   513: aload 5
    //   515: astore 7
    //   517: aload_1
    //   518: astore 6
    //   520: aload_0
    //   521: aconst_null
    //   522: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   525: aload 5
    //   527: astore 7
    //   529: aload_1
    //   530: astore 6
    //   532: aload_0
    //   533: aconst_null
    //   534: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   537: aload 5
    //   539: invokevirtual 564	java/io/DataInputStream:close	()V
    //   542: aload_1
    //   543: ifnull +7 -> 550
    //   546: aload_1
    //   547: invokevirtual 190	java/io/InputStream:close	()V
    //   550: aload_0
    //   551: aconst_null
    //   552: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   555: aload_0
    //   556: aconst_null
    //   557: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   560: aload 9
    //   562: areturn
    //   563: astore 8
    //   565: aconst_null
    //   566: astore 5
    //   568: aconst_null
    //   569: astore_1
    //   570: aload 5
    //   572: astore 7
    //   574: aload_1
    //   575: astore 6
    //   577: aload 8
    //   579: invokevirtual 566	java/lang/Throwable:printStackTrace	()V
    //   582: aload 5
    //   584: ifnull +8 -> 592
    //   587: aload 5
    //   589: invokevirtual 564	java/io/DataInputStream:close	()V
    //   592: aload_1
    //   593: ifnull +7 -> 600
    //   596: aload_1
    //   597: invokevirtual 190	java/io/InputStream:close	()V
    //   600: aload_0
    //   601: aconst_null
    //   602: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   605: aload_0
    //   606: aconst_null
    //   607: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   610: aload_0
    //   611: iconst_0
    //   612: putfield 28	com/paypal/android/a/a:d	I
    //   615: aconst_null
    //   616: areturn
    //   617: astore 5
    //   619: aconst_null
    //   620: astore 7
    //   622: aconst_null
    //   623: astore_1
    //   624: aload 7
    //   626: ifnull +8 -> 634
    //   629: aload 7
    //   631: invokevirtual 564	java/io/DataInputStream:close	()V
    //   634: aload_1
    //   635: ifnull +7 -> 642
    //   638: aload_1
    //   639: invokevirtual 190	java/io/InputStream:close	()V
    //   642: aload_0
    //   643: aconst_null
    //   644: putfield 138	com/paypal/android/a/a:a	Lorg/apache/http/client/methods/HttpPost;
    //   647: aload_0
    //   648: aconst_null
    //   649: putfield 26	com/paypal/android/a/a:c	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   652: aload 5
    //   654: athrow
    //   655: astore 5
    //   657: goto -286 -> 371
    //   660: astore_1
    //   661: goto -282 -> 379
    //   664: astore 5
    //   666: goto -124 -> 542
    //   669: astore_1
    //   670: goto -120 -> 550
    //   673: astore 5
    //   675: goto -83 -> 592
    //   678: astore_1
    //   679: goto -79 -> 600
    //   682: astore 6
    //   684: goto -50 -> 634
    //   687: astore_1
    //   688: goto -46 -> 642
    //   691: astore 5
    //   693: aconst_null
    //   694: astore 7
    //   696: goto -72 -> 624
    //   699: astore 5
    //   701: aload 6
    //   703: astore_1
    //   704: goto -80 -> 624
    //   707: astore 8
    //   709: aconst_null
    //   710: astore 5
    //   712: goto -142 -> 570
    //   715: astore 8
    //   717: goto -147 -> 570
    //   720: iload 4
    //   722: iconst_1
    //   723: iadd
    //   724: istore 4
    //   726: goto -595 -> 131
    //   729: iconst_0
    //   730: istore_3
    //   731: goto -557 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	734	0	this	a
    //   0	734	1	paramString	String
    //   130	324	2	i	int
    //   132	599	3	j	int
    //   127	598	4	k	int
    //   58	530	5	localObject1	Object
    //   617	36	5	localObject2	Object
    //   655	1	5	localException1	Exception
    //   664	1	5	localException2	Exception
    //   673	1	5	localException3	Exception
    //   691	1	5	localObject3	Object
    //   699	1	5	localObject4	Object
    //   710	1	5	localObject5	Object
    //   199	377	6	str	String
    //   682	20	6	localException4	Exception
    //   196	499	7	localObject6	Object
    //   113	371	8	localHttpEntity	HttpEntity
    //   563	15	8	localThrowable1	Throwable
    //   707	1	8	localThrowable2	Throwable
    //   715	1	8	localThrowable3	Throwable
    //   266	295	9	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   0	18	563	java/lang/Throwable
    //   18	122	563	java/lang/Throwable
    //   133	140	563	java/lang/Throwable
    //   140	171	563	java/lang/Throwable
    //   174	182	563	java/lang/Throwable
    //   0	18	617	finally
    //   18	122	617	finally
    //   133	140	617	finally
    //   140	171	617	finally
    //   174	182	617	finally
    //   366	371	655	java/lang/Exception
    //   375	379	660	java/lang/Exception
    //   537	542	664	java/lang/Exception
    //   546	550	669	java/lang/Exception
    //   587	592	673	java/lang/Exception
    //   596	600	678	java/lang/Exception
    //   629	634	682	java/lang/Exception
    //   638	642	687	java/lang/Exception
    //   182	192	691	finally
    //   201	209	699	finally
    //   216	221	699	finally
    //   230	239	699	finally
    //   246	252	699	finally
    //   263	268	699	finally
    //   275	282	699	finally
    //   289	300	699	finally
    //   312	319	699	finally
    //   326	331	699	finally
    //   338	342	699	finally
    //   349	354	699	finally
    //   361	366	699	finally
    //   399	408	699	finally
    //   415	421	699	finally
    //   433	441	699	finally
    //   448	454	699	finally
    //   464	471	699	finally
    //   483	490	699	finally
    //   497	502	699	finally
    //   509	513	699	finally
    //   520	525	699	finally
    //   532	537	699	finally
    //   577	582	699	finally
    //   182	192	707	java/lang/Throwable
    //   201	209	715	java/lang/Throwable
    //   216	221	715	java/lang/Throwable
    //   230	239	715	java/lang/Throwable
    //   246	252	715	java/lang/Throwable
    //   263	268	715	java/lang/Throwable
    //   275	282	715	java/lang/Throwable
    //   289	300	715	java/lang/Throwable
    //   312	319	715	java/lang/Throwable
    //   326	331	715	java/lang/Throwable
    //   338	342	715	java/lang/Throwable
    //   349	354	715	java/lang/Throwable
    //   361	366	715	java/lang/Throwable
    //   399	408	715	java/lang/Throwable
    //   415	421	715	java/lang/Throwable
    //   433	441	715	java/lang/Throwable
    //   448	454	715	java/lang/Throwable
    //   464	471	715	java/lang/Throwable
    //   483	490	715	java/lang/Throwable
    //   497	502	715	java/lang/Throwable
    //   509	513	715	java/lang/Throwable
    //   520	525	715	java/lang/Throwable
    //   532	537	715	java/lang/Throwable
  }
  
  private static String e()
  {
    switch (PayPal.getInstance().get_server())
    {
    case 1: 
    default: 
      return "https://mobileclient.paypal.com/mepadapter/";
    case 0: 
      return "https://mobileclient.sandbox.paypal.com/mepadapter/";
    case 2: 
      return "";
    }
    return "http://stage2mobile03.paypal.com:443/mepAdapter.mobile08/";
  }
  
  private static String e(String paramString)
  {
    if (paramString == null) {
      return "1";
    }
    paramString = paramString.toUpperCase();
    if (paramString.compareTo("US") == 0) {
      return "1";
    }
    if (paramString.compareTo("CA") == 0) {
      return "1";
    }
    if (paramString.compareTo("GB") == 0) {
      return "44";
    }
    if (paramString.compareTo("AU") == 0) {
      return "61";
    }
    if (paramString.compareTo("FR") == 0) {
      return "33";
    }
    if (paramString.compareTo("ES") == 0) {
      return "34";
    }
    if (paramString.compareTo("IT") == 0) {
      return "39";
    }
    return "1";
  }
  
  private boolean f()
  {
    Hashtable localHashtable = this.f;
    Object localObject = (String)localHashtable.get("SessionToken");
    String str5 = (String)localHashtable.get("FlowContext");
    String str4 = (String)localHashtable.get("ItemName");
    String str3 = (String)localHashtable.get("PrimaryFSID");
    String str2 = (String)localHashtable.get("BackupFSID");
    String str1 = (String)localHashtable.get("AddressID");
    str5 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">mfoundry_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPUpdatePaymentReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1</Version><RequestorID xsi:type=\"ns:IVRRequestorID\">eding-1</RequestorID><SessionToken xsi:type=\"ns:IVRSessionToken\">" + (String)localObject + "</SessionToken>" + "<FlowContext xsi:type=\"ns:IVRFlowContext\">" + str5 + "</FlowContext>";
    localObject = str3;
    if (str3 == null) {
      localObject = "";
    }
    str3 = str5 + "<PrimaryFSID xsi:type=\"ns:IVRFundingSourceID\">" + (String)localObject + "</PrimaryFSID>";
    localObject = str2;
    if (str2 == null) {
      localObject = "";
    }
    str2 = str3 + "<BackupFSID xsi:type=\"ns:IVRFundingSourceID\">" + (String)localObject + "</BackupFSID>";
    localObject = str1;
    if (str1 == null) {
      localObject = "";
    }
    str2 = str2 + "<AddressID xsi:type=\"xs:string\">" + (String)localObject + "</AddressID>";
    if (str4 == null) {
      localObject = " ";
    }
    for (;;)
    {
      localObject = str2 + "<ItemName xsi:type=\"ns:string\">" + (String)localObject + "</ItemName>";
      localObject = (String)localObject + "<FeeBearer>" + (String)localHashtable.get("FeeBearer") + "</FeeBearer>\n";
      localObject = d((String)localObject + "</Request></MEPUpdatePaymentReq></SOAP-ENV:Body></SOAP-ENV:Envelope>");
      try
      {
        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(((String)localObject).getBytes("UTF-8")));
        if (f((String)localObject))
        {
          return false;
          str1 = str4.trim();
          localObject = str1;
          if (str1.length() != 0) {
            continue;
          }
          localObject = " ";
          continue;
        }
        h.b((String)localObject, this.f);
        return true;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private boolean f(String paramString)
  {
    if (paramString == null) {
      this.d = 408;
    }
    do
    {
      do
      {
        return true;
        if (!paramString.contains("ErrorId=")) {
          break;
        }
        this.d = Integer.parseInt(paramString.substring(paramString.indexOf("ErrorId=") + "ErrorId=".length()));
        if (c("" + this.d))
        {
          paramString = new Intent(PayPalActivity.FATAL_ERROR).putExtra("FATAL_ERROR_ID", "" + this.d).putExtra("FATAL_ERROR_MESSAGE", e.a("ANDROID_" + this.d));
          PayPalActivity.getInstance().sendBroadcast(paramString);
          return true;
        }
      } while (!b("" + this.d));
      paramString = new Intent(PayPalActivity.LOGIN_FAIL).putExtra("FATAL_ERROR_ID", "" + this.d).putExtra("ERROR_TIMEOUT", e.a("ANDROID_" + this.d));
      PayPalActivity.getInstance().sendBroadcast(paramString);
      return true;
      int i = h.a(paramString);
      if (i == 200) {
        break;
      }
      this.d = i;
      if (c("" + this.d))
      {
        paramString = new Intent(PayPalActivity.FATAL_ERROR).putExtra("FATAL_ERROR_ID", "" + this.d).putExtra("FATAL_ERROR_MESSAGE", e.a("ANDROID_" + this.d));
        try
        {
          PayPalActivity.getInstance().sendBroadcast(paramString);
          return true;
        }
        catch (Exception paramString)
        {
          return true;
        }
      }
    } while (!b("" + this.d));
    paramString = new Intent(PayPalActivity.LOGIN_FAIL).putExtra("FATAL_ERROR_ID", "" + this.d).putExtra("ERROR_TIMEOUT", e.a("ANDROID_" + this.d));
    PayPalActivity.getInstance().sendBroadcast(paramString);
    return true;
    if ((paramString.contains("<SOAP-ENV:Body")) && (!paramString.contains("</SOAP-ENV:Body")))
    {
      this.d = 0;
      return true;
    }
    return false;
  }
  
  private static String g()
  {
    switch (PayPal.getInstance().get_server())
    {
    case 1: 
    default: 
      return PayPal.getInstance().getAppID();
    case 0: 
      return PayPal.getInstance().getAppID();
    case 2: 
      return "";
    }
    return PayPal.getInstance().getAppID();
  }
  
  private static ArrayList<BasicNameValuePair> h()
  {
    Object localObject = (TelephonyManager)PayPalActivity.getInstance().getSystemService("phone");
    String str4 = ((TelephonyManager)localObject).getDeviceId();
    int i = ((TelephonyManager)localObject).getPhoneType();
    ArrayList localArrayList = new ArrayList();
    String str3 = PayPal.getInstance().get_parentContext().getPackageName();
    String str1 = a(str3);
    localObject = str1;
    if (str1 == null) {
      localObject = str3;
    }
    String str5 = g();
    str1 = "AndroidCDMA";
    if (i == 1) {
      str1 = "AndroidGSM";
    }
    String str2 = "MEID";
    if (i == 1) {
      str2 = "IMEI";
    }
    String str6 = PayPal.getInstance().getVersion();
    String str7 = Build.DEVICE + " " + Build.MODEL;
    String str8 = Build.DEVICE + " " + Build.MODEL;
    localArrayList.add(new BasicNameValuePair("PayPalApplicationID", str5));
    localArrayList.add(new BasicNameValuePair("DeviceIDType", str2));
    localArrayList.add(new BasicNameValuePair("DeviceIDTypeValue", str4));
    localArrayList.add(new BasicNameValuePair("BundleIdentifier", str3));
    localArrayList.add(new BasicNameValuePair("BundleName", str3));
    localArrayList.add(new BasicNameValuePair("BundleDisplayName", (String)localObject));
    localArrayList.add(new BasicNameValuePair("ClientPlatform", str1));
    localArrayList.add(new BasicNameValuePair("LibraryName", "Android"));
    localArrayList.add(new BasicNameValuePair("LibraryVersion", str6));
    localArrayList.add(new BasicNameValuePair("DeviceCategory", "Phone"));
    localArrayList.add(new BasicNameValuePair("DeviceModel", str7));
    localArrayList.add(new BasicNameValuePair("DeviceName", str8));
    return localArrayList;
  }
  
  private static ArrayList<BasicNameValuePair> i()
  {
    Object localObject1 = ((TelephonyManager)PayPalActivity.getInstance().getSystemService("phone")).getDeviceId();
    Object localObject2 = Build.DEVICE + " " + Build.MODEL;
    ArrayList localArrayList = new ArrayList();
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update((g() + "|" + (String)localObject1 + "|" + (String)localObject2).getBytes());
      localObject1 = localMessageDigest.digest();
      localObject2 = new StringBuffer();
      int i = 0;
      while (i < localObject1.length)
      {
        ((StringBuffer)localObject2).append(Integer.toHexString(localObject1[i] & 0xFF));
        i += 1;
      }
      localArrayList.add(new BasicNameValuePair("DeviceCompositeIdHash", ((StringBuffer)localObject2).toString()));
      localArrayList.add(new BasicNameValuePair("PayPalApplicationID", g()));
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
  
  public final void a(int paramInt)
  {
    this.e = paramInt;
  }
  
  public final void a(String paramString, Object paramObject)
  {
    this.f.put(paramString, paramObject);
  }
  
  public final String c()
  {
    return e.a("ANDROID_" + this.d);
  }
  
  public final Hashtable<String, Object> d()
  {
    return this.f;
  }
}
