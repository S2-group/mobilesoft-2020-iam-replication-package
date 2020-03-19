package com.paypal.android.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.paypal.android.MEP.c;
import com.paypal.android.MEP.r;
import com.paypal.android.a.a.e;
import com.paypal.android.a.a.g;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class d
{
  public d() {}
  
  public static int a(String paramString)
  {
    label114:
    for (;;)
    {
      try
      {
        Object localObject = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
        paramString = ((Document)localObject).getElementsByTagName("ErrorCode");
        if ((paramString != null) && (paramString.getLength() != 0)) {
          break label114;
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
  
  public static String a()
  {
    localObject12 = "";
    localObject21 = "false";
    localObject14 = "";
    localObject9 = "";
    localObject22 = "";
    localObject11 = "";
    localObject10 = "";
    localObject8 = "";
    try
    {
      localObject17 = URLEncoder.encode(r.a().g(), "utf-8");
    }
    catch (Exception localException3)
    {
      for (;;)
      {
        try
        {
          label76:
          localObject8 = URLEncoder.encode((String)localObject5, "utf-8");
          localObject19 = localObject12;
          localObject14 = localObject8;
          localObject13 = localObject9;
          localObject15 = localObject22;
          localObject18 = localObject11;
          localObject20 = localObject10;
          localObject6 = URLEncoder.encode(Build.DEVICE);
          localObject19 = localObject12;
          localObject14 = localObject8;
          localObject13 = localObject9;
          localObject15 = localObject6;
          localObject18 = localObject11;
          localObject20 = localObject10;
          localObject5 = Build.MODEL;
        }
        catch (Exception localException2)
        {
          Object localObject17;
          Object localObject1;
          Object localObject6;
          localObject8 = localObject20;
          localObject9 = localObject4;
          localObject11 = localObject18;
          localObject5 = localObject2;
          localObject10 = localException5;
          localObject18 = "";
          localObject12 = localObject14;
          localObject4 = "";
          localObject14 = "";
          localException6 = localException2;
          localObject2 = localObject3;
          localObject3 = localObject14;
          localObject7 = localObject18;
          localObject14 = localObject19;
          continue;
        }
        try
        {
          localObject9 = ((String)localObject5).replaceAll(" ", "%20");
          localObject19 = localObject12;
          localObject14 = localObject8;
          localObject13 = localObject9;
          localObject15 = localObject6;
          localObject18 = localObject11;
          localObject20 = localObject10;
          localObject11 = URLEncoder.encode("Android", "utf-8");
          localObject19 = localObject12;
          localObject14 = localObject8;
          localObject13 = localObject9;
          localObject15 = localObject6;
          localObject18 = localObject11;
          localObject20 = localObject10;
          localObject10 = URLEncoder.encode(Build.VERSION.SDK, "utf-8");
          localObject19 = localObject12;
          localObject14 = localObject8;
          localObject13 = localObject9;
          localObject15 = localObject6;
          localObject18 = localObject11;
          localObject20 = localObject10;
          localObject12 = URLEncoder.encode(r.z(), "utf-8");
          localObject19 = localObject12;
          localObject14 = localObject8;
          localObject13 = localObject9;
          localObject15 = localObject6;
          localObject18 = localObject11;
          localObject20 = localObject10;
          localObject5 = r.a().f().getPackageName();
        }
        catch (Exception localException7)
        {
          localObject18 = "";
          localObject13 = localObject5;
          localObject12 = localObject8;
          localObject19 = "";
          localObject11 = "";
          localObject5 = localObject2;
          localObject9 = localObject4;
          localObject14 = "";
          localObject2 = localObject3;
          localObject8 = "";
          localObject10 = localObject7;
          localObject7 = "";
          localObject4 = localObject19;
          localObject3 = localObject18;
          continue;
        }
        try
        {
          localObject13 = r.a().f().getPackageManager();
          localObject14 = ((PackageManager)localObject13).getInstalledApplications(0);
          Collections.sort((List)localObject14, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject13));
          i = 0;
          if (i < ((List)localObject14).size())
          {
            localObject15 = (ApplicationInfo)((List)localObject14).get(i);
            if (((ApplicationInfo)localObject15).packageName.equals(localObject5))
            {
              localObject14 = ((ApplicationInfo)localObject15).loadLabel((PackageManager)localObject13).toString();
              localObject13 = localObject14;
              if (localObject14 == null) {
                localObject13 = localObject5;
              }
              localObject14 = localObject21;
            }
          }
        }
        catch (Exception localException8)
        {
          localObject13 = localObject9;
          localObject9 = localObject5;
          localObject14 = localObject8;
          localObject20 = "";
          localObject8 = localObject2;
          localObject19 = localObject4;
          localObject18 = localObject12;
          localObject2 = localObject3;
          localObject12 = localObject10;
          localObject10 = localObject7;
          localObject4 = localObject20;
          localObject3 = localObject9;
          localObject7 = localObject5;
          localObject5 = localObject8;
          localObject9 = localObject19;
          localObject8 = localObject12;
          localObject12 = localObject14;
          localObject14 = localObject18;
          continue;
        }
        try
        {
          if (((String)localObject8).equals("000000000000000")) {
            localObject14 = "true";
          }
          localObject15 = localObject12;
          localObject12 = localObject5;
          localObject19 = localObject10;
          localObject18 = localObject17;
          localObject17 = localObject15;
          localObject15 = localObject14;
          localObject14 = localObject8;
          localObject10 = localObject9;
          localObject8 = localObject6;
          localObject6 = localObject19;
          localObject9 = localObject4;
          localObject4 = localObject1;
          localObject1 = localObject13;
          localObject3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPDeviceInterrogationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1.0</Version><PayPalAppID xsi:type=\"xs:string\">" + (String)localObject18 + "</PayPalAppID>" + "<DeviceDetails xsi:type=\"ns:MEPDeviceDetailsType\">" + "<deviceID xsi:type=\"ns:MEPDeviceIDType\">" + "<deviceIdentifier xsi:type=\"xs:string\">" + (String)localObject14 + "</deviceIdentifier>" + "<deviceIdType xsi:type=\"ns:MEPDeviceIdentifierType\">" + (String)localObject4 + "</deviceIdType>" + "</deviceID>" + "<deviceName xsi:type=\"xs:string\">" + (String)localObject8 + "</deviceName>" + "<deviceModel xsi:type=\"xs:string\">" + (String)localObject10 + "</deviceModel>" + "<systemName xsi:type=\"xs:string\">" + (String)localObject11 + "</systemName>" + "<systemVersion xsi:type=\"xs:string\">" + (String)localObject6 + "</systemVersion>" + "<deviceCategory xsi:type=\"ns:MEPDeviceCategoryType\">" + (String)localObject9 + "</deviceCategory>" + "<isDeviceSimulator xsi:type=\"xs:boolean\">" + (String)localObject15 + "</isDeviceSimulator>" + "</DeviceDetails>" + "<ApplicationDetails xsi:type=\"ns:MEPApplicationDetailsType\">" + "<appID xsi:type=\"ns:MEPAppIDType\">" + "<deviceAppID xsi:type=\"xs:string\">" + (String)localObject5 + "</deviceAppID>" + "</appID>" + "<appName xsi:type=\"xs:string\">" + (String)localObject12 + "</appName>" + "<appDisplayName xsi:type=\"xs:string\">" + (String)localObject1 + "</appDisplayName>" + "<clientPlatform xsi:type=\"xs:string\">" + (String)localObject3 + "</clientPlatform>" + "</ApplicationDetails>" + "<MEPVersion xsi:type=\"xs:string\">" + (String)localObject17 + "</MEPVersion>";
        }
        catch (Exception localException9)
        {
          localObject14 = localObject8;
          localObject8 = localObject13;
          localObject13 = localObject9;
          localObject9 = localObject5;
          localObject19 = localObject2;
          localObject20 = localObject4;
          localObject18 = localObject12;
          localObject2 = localObject3;
          localObject12 = localObject7;
          localObject4 = localObject8;
          localObject3 = localObject9;
          localObject7 = localObject5;
          localObject5 = localObject19;
          localObject9 = localObject20;
          localObject8 = localObject10;
          localObject10 = localObject12;
          localObject12 = localObject14;
          localObject14 = localObject18;
          continue;
        }
        try
        {
          localObject1 = r.a().f().openFileInput("DeviceReferenceToken");
          localObject4 = new byte[((FileInputStream)localObject1).available()];
          ((FileInputStream)localObject1).read((byte[])localObject4);
          ((FileInputStream)localObject1).close();
          localObject1 = new String((byte[])localObject4);
          localObject1 = (String)localObject3 + "<DeviceReferenceToken xsi:type=\"xs:string\">" + (String)localObject1 + "</DeviceReferenceToken>";
          return (String)localObject1 + "</Request></MEPDeviceInterrogationReq></SOAP-ENV:Body></SOAP-ENV:Envelope>";
          i += 1;
          continue;
          localObject14 = null;
          continue;
          localException3 = localException3;
          localObject17 = "";
          localObject12 = "";
          localObject4 = "";
          localObject10 = "";
          localObject6 = "";
          localObject8 = "";
          localObject9 = "";
          localObject5 = "";
          localObject11 = "";
          localObject3 = "";
          localObject13 = "";
          localObject1 = "";
          localObject14 = "";
          localException3.printStackTrace();
          str = "false";
          localObject22 = localObject3;
          localObject21 = localObject5;
          localObject20 = localObject8;
          localObject8 = localObject10;
          localObject16 = localObject12;
          localObject18 = localObject14;
          localObject19 = localObject17;
          localObject3 = localObject1;
          localObject1 = localObject4;
          localObject12 = localObject22;
          localObject5 = localObject6;
          localObject4 = localObject21;
          localObject6 = localObject20;
          localObject10 = localObject13;
          localObject14 = localObject16;
          localObject16 = str;
          localObject17 = localObject18;
          localObject18 = localObject19;
        }
        catch (Exception localException1)
        {
          localObject2 = "";
        }
      }
    }
    try
    {
      localObject13 = (TelephonyManager)r.a().f().getSystemService("phone");
      if (((TelephonyManager)localObject13).getPhoneType() != 1) {
        break label1494;
      }
      localObject3 = "IMEI";
      localObject1 = "AndroidGSM";
    }
    catch (Exception localException4)
    {
      localObject14 = "";
      localObject2 = "";
      localObject13 = "";
      localObject3 = "";
      localObject11 = "";
      localObject5 = "";
      localObject9 = "";
      localObject8 = "";
      localObject6 = "";
      localObject10 = "";
      localObject4 = "";
      localObject12 = "";
      break label1028;
      localObject3 = "MEID";
      localObject2 = "AndroidCDMA";
      break label76;
    }
    localObject4 = localObject8;
    localObject5 = localObject1;
    localObject6 = localObject3;
    try
    {
      localObject13 = ((TelephonyManager)localObject13).getDeviceId();
      localObject4 = localObject8;
      localObject5 = localObject1;
      localObject6 = localObject3;
      localObject8 = URLEncoder.encode("Phone", "utf-8");
      if (localObject13 != null) {
        break label1478;
      }
      localObject4 = localObject8;
      localObject5 = localObject1;
      localObject6 = localObject3;
      localObject13 = ((WifiManager)r.a().f().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      localObject4 = localObject8;
      localObject5 = localObject1;
      localObject6 = localObject3;
      localObject1 = URLEncoder.encode("MAC", "utf-8");
      localObject5 = "AndroidGSM";
      localObject4 = localObject8;
      localObject6 = localObject1;
      localObject3 = URLEncoder.encode("Tablet", "utf-8");
      localObject4 = localObject3;
      localObject3 = "AndroidGSM";
      localObject5 = localObject13;
    }
    catch (Exception localException5)
    {
      for (;;)
      {
        Object localObject19;
        Object localObject15;
        Object localObject20;
        int i;
        String str;
        Object localObject16;
        localObject11 = "";
        localObject8 = localObject6;
        localObject14 = "";
        localObject2 = localObject5;
        localObject12 = "";
        localObject3 = "";
        localObject9 = localObject4;
        Object localObject18 = "";
        localObject6 = "";
        localObject10 = "";
        localObject5 = "";
        localObject13 = "";
        localObject4 = localObject3;
        localObject3 = localObject5;
        localObject5 = localObject8;
        localObject8 = localObject18;
        continue;
        Exception localException6;
        Object localObject7;
        localObject4 = localObject3;
        localObject5 = localObject13;
        localObject3 = localObject2;
        localObject2 = localObject4;
        localObject4 = localObject8;
      }
    }
    localObject19 = localObject12;
    localObject13 = localObject9;
    localObject15 = localObject22;
    localObject18 = localObject11;
    localObject20 = localObject10;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return null;
    }
    if ((paramString1.indexOf("<") == -1) || (paramString1.indexOf(">") == -1)) {
      return paramString1;
    }
    try
    {
      paramString1 = URLEncoder.encode(paramString1, paramString2);
      return paramString1;
    }
    catch (Throwable paramString1) {}
    return null;
  }
  
  public static String a(NodeList paramNodeList)
  {
    String str = "";
    int i = 0;
    while (i < paramNodeList.getLength())
    {
      str = str + paramNodeList.item(i).getNodeValue();
      i += 1;
    }
    return str;
  }
  
  /* Error */
  public static boolean a(String paramString, Hashtable paramHashtable)
  {
    // Byte code:
    //   0: invokestatic 19	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   3: invokevirtual 23	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   6: new 25	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: ldc 27
    //   13: invokevirtual 33	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   16: invokespecial 36	java/io/ByteArrayInputStream:<init>	([B)V
    //   19: invokevirtual 42	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   22: ldc_w 351
    //   25: invokeinterface 50 2 0
    //   30: astore 7
    //   32: iconst_0
    //   33: istore_2
    //   34: iload_2
    //   35: aload 7
    //   37: invokeinterface 56 1 0
    //   42: if_icmpge +643 -> 685
    //   45: aload 7
    //   47: iload_2
    //   48: invokeinterface 62 2 0
    //   53: invokeinterface 68 1 0
    //   58: astore 8
    //   60: iconst_0
    //   61: istore_3
    //   62: iload_3
    //   63: aload 8
    //   65: invokeinterface 56 1 0
    //   70: if_icmpge +608 -> 678
    //   73: aload 8
    //   75: iload_3
    //   76: invokeinterface 62 2 0
    //   81: astore 6
    //   83: aload 6
    //   85: invokeinterface 354 1 0
    //   90: astore_0
    //   91: aload_0
    //   92: ldc_w 356
    //   95: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   98: ifne +54 -> 152
    //   101: aload 6
    //   103: invokeinterface 68 1 0
    //   108: iconst_0
    //   109: invokeinterface 62 2 0
    //   114: invokeinterface 72 1 0
    //   119: astore 6
    //   121: aload_1
    //   122: aload_0
    //   123: aload 6
    //   125: invokevirtual 365	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   128: pop
    //   129: aload 6
    //   131: ldc -31
    //   133: invokevirtual 368	java/lang/String:compareToIgnoreCase	(Ljava/lang/String;)I
    //   136: ifeq +552 -> 688
    //   139: ldc_w 370
    //   142: ldc_w 372
    //   145: invokestatic 378	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   148: pop
    //   149: goto +539 -> 688
    //   152: aload_0
    //   153: ldc_w 380
    //   156: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   159: ifne +100 -> 259
    //   162: aload 6
    //   164: invokeinterface 68 1 0
    //   169: astore 6
    //   171: iconst_0
    //   172: istore 4
    //   174: iload 4
    //   176: aload 6
    //   178: invokeinterface 56 1 0
    //   183: if_icmpge +505 -> 688
    //   186: aload 6
    //   188: iload 4
    //   190: invokeinterface 62 2 0
    //   195: astore 9
    //   197: aload 9
    //   199: invokeinterface 354 1 0
    //   204: astore 10
    //   206: aload 9
    //   208: invokeinterface 68 1 0
    //   213: invokeinterface 56 1 0
    //   218: ifle +477 -> 695
    //   221: aload 10
    //   223: ldc_w 382
    //   226: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   229: ifne +466 -> 695
    //   232: aload_1
    //   233: aload_0
    //   234: aload 9
    //   236: invokeinterface 68 1 0
    //   241: iconst_0
    //   242: invokeinterface 62 2 0
    //   247: invokeinterface 72 1 0
    //   252: invokevirtual 365	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   255: pop
    //   256: goto +439 -> 695
    //   259: aload_0
    //   260: ldc_w 307
    //   263: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   266: ifne +80 -> 346
    //   269: aload 6
    //   271: invokeinterface 68 1 0
    //   276: iconst_0
    //   277: invokeinterface 62 2 0
    //   282: invokeinterface 72 1 0
    //   287: astore 6
    //   289: aload_1
    //   290: aload_0
    //   291: aload 6
    //   293: invokevirtual 365	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   296: pop
    //   297: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   300: invokevirtual 104	com/paypal/android/MEP/r:f	()Landroid/content/Context;
    //   303: ldc_w 307
    //   306: iconst_2
    //   307: invokevirtual 386	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   310: astore_0
    //   311: aload_0
    //   312: aload 6
    //   314: invokevirtual 389	java/lang/String:getBytes	()[B
    //   317: invokevirtual 394	java/io/FileOutputStream:write	([B)V
    //   320: aload_0
    //   321: invokevirtual 397	java/io/FileOutputStream:flush	()V
    //   324: aload_0
    //   325: invokevirtual 398	java/io/FileOutputStream:close	()V
    //   328: goto +360 -> 688
    //   331: astore_0
    //   332: aload_0
    //   333: invokevirtual 399	java/io/FileNotFoundException:printStackTrace	()V
    //   336: goto +352 -> 688
    //   339: astore_0
    //   340: aload_0
    //   341: invokevirtual 80	java/lang/Exception:printStackTrace	()V
    //   344: iconst_0
    //   345: ireturn
    //   346: aload_0
    //   347: ldc_w 401
    //   350: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   353: ifne +335 -> 688
    //   356: aload 6
    //   358: invokeinterface 68 1 0
    //   363: astore 9
    //   365: iconst_0
    //   366: istore 4
    //   368: iload 4
    //   370: aload 9
    //   372: invokeinterface 56 1 0
    //   377: if_icmpge +272 -> 649
    //   380: aload 9
    //   382: iload 4
    //   384: invokeinterface 62 2 0
    //   389: astore 6
    //   391: aload 6
    //   393: invokeinterface 354 1 0
    //   398: astore_0
    //   399: aload 6
    //   401: invokeinterface 68 1 0
    //   406: invokeinterface 56 1 0
    //   411: ifle +293 -> 704
    //   414: aload 6
    //   416: invokeinterface 68 1 0
    //   421: iconst_0
    //   422: invokeinterface 62 2 0
    //   427: invokeinterface 72 1 0
    //   432: astore 10
    //   434: aload_0
    //   435: ldc_w 403
    //   438: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   441: ifne +14 -> 455
    //   444: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   447: aload 10
    //   449: invokevirtual 407	com/paypal/android/MEP/r:c	(Ljava/lang/String;)V
    //   452: goto +252 -> 704
    //   455: aload_0
    //   456: ldc_w 409
    //   459: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   462: ifne +14 -> 476
    //   465: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   468: aload 10
    //   470: invokevirtual 412	com/paypal/android/MEP/r:d	(Ljava/lang/String;)V
    //   473: goto +231 -> 704
    //   476: aload_0
    //   477: ldc 126
    //   479: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   482: ifne +119 -> 601
    //   485: ldc 82
    //   487: astore_0
    //   488: aload 6
    //   490: invokeinterface 68 1 0
    //   495: astore 10
    //   497: iconst_0
    //   498: istore 5
    //   500: iload 5
    //   502: aload 10
    //   504: invokeinterface 56 1 0
    //   509: if_icmpge +75 -> 584
    //   512: aload_0
    //   513: astore 6
    //   515: aload 10
    //   517: iload 5
    //   519: invokeinterface 62 2 0
    //   524: invokeinterface 68 1 0
    //   529: invokeinterface 56 1 0
    //   534: ifle +179 -> 713
    //   537: new 227	java/lang/StringBuilder
    //   540: dup
    //   541: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   544: aload_0
    //   545: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: aload 10
    //   550: iload 5
    //   552: invokeinterface 62 2 0
    //   557: invokeinterface 68 1 0
    //   562: iconst_0
    //   563: invokeinterface 62 2 0
    //   568: invokeinterface 72 1 0
    //   573: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: invokevirtual 305	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   579: astore 6
    //   581: goto +132 -> 713
    //   584: aload_0
    //   585: invokevirtual 415	java/lang/String:length	()I
    //   588: ifeq +116 -> 704
    //   591: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   594: aload_0
    //   595: invokevirtual 417	com/paypal/android/MEP/r:e	(Ljava/lang/String;)V
    //   598: goto +106 -> 704
    //   601: aload_0
    //   602: ldc_w 419
    //   605: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   608: ifne +17 -> 625
    //   611: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   614: aload 10
    //   616: invokestatic 77	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   619: invokevirtual 422	com/paypal/android/MEP/r:a	(I)V
    //   622: goto +82 -> 704
    //   625: aload_0
    //   626: ldc_w 424
    //   629: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   632: ifne +72 -> 704
    //   635: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   638: aload 10
    //   640: invokestatic 77	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   643: invokevirtual 427	com/paypal/android/MEP/r:b	(I)V
    //   646: goto +58 -> 704
    //   649: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   652: invokevirtual 430	com/paypal/android/MEP/r:v	()I
    //   655: ifne +33 -> 688
    //   658: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   661: invokevirtual 433	com/paypal/android/MEP/r:u	()I
    //   664: iconst_2
    //   665: if_icmpne +23 -> 688
    //   668: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   671: iconst_0
    //   672: invokevirtual 422	com/paypal/android/MEP/r:a	(I)V
    //   675: goto +13 -> 688
    //   678: iload_2
    //   679: iconst_1
    //   680: iadd
    //   681: istore_2
    //   682: goto -648 -> 34
    //   685: iconst_1
    //   686: ireturn
    //   687: astore_0
    //   688: iload_3
    //   689: iconst_1
    //   690: iadd
    //   691: istore_3
    //   692: goto -630 -> 62
    //   695: iload 4
    //   697: iconst_1
    //   698: iadd
    //   699: istore 4
    //   701: goto -527 -> 174
    //   704: iload 4
    //   706: iconst_1
    //   707: iadd
    //   708: istore 4
    //   710: goto -342 -> 368
    //   713: iload 5
    //   715: iconst_1
    //   716: iadd
    //   717: istore 5
    //   719: aload 6
    //   721: astore_0
    //   722: goto -222 -> 500
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	725	0	paramString	String
    //   0	725	1	paramHashtable	Hashtable
    //   33	649	2	i	int
    //   61	631	3	j	int
    //   172	537	4	k	int
    //   498	220	5	m	int
    //   81	639	6	localObject1	Object
    //   30	16	7	localNodeList1	NodeList
    //   58	16	8	localNodeList2	NodeList
    //   195	186	9	localObject2	Object
    //   204	435	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   297	328	331	java/io/FileNotFoundException
    //   0	32	339	java/lang/Exception
    //   34	60	339	java/lang/Exception
    //   62	149	339	java/lang/Exception
    //   152	171	339	java/lang/Exception
    //   174	256	339	java/lang/Exception
    //   259	297	339	java/lang/Exception
    //   297	328	339	java/lang/Exception
    //   332	336	339	java/lang/Exception
    //   346	365	339	java/lang/Exception
    //   368	452	339	java/lang/Exception
    //   455	473	339	java/lang/Exception
    //   476	485	339	java/lang/Exception
    //   488	497	339	java/lang/Exception
    //   500	512	339	java/lang/Exception
    //   515	581	339	java/lang/Exception
    //   584	598	339	java/lang/Exception
    //   601	622	339	java/lang/Exception
    //   625	646	339	java/lang/Exception
    //   649	675	339	java/lang/Exception
    //   297	328	687	java/io/IOException
  }
  
  public static String b()
  {
    Object localObject1 = "";
    try
    {
      Object localObject2 = r.a().f().openFileInput("DeviceReferenceToken");
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
  
  public static boolean b(String paramString, Hashtable paramHashtable)
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
      Object localObject = paramString.getElementsByTagName("responseEnvelope");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      localObject = (Element)((NodeList)localObject).item(0);
      NodeList localNodeList = ((Element)localObject).getElementsByTagName("timestamp");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("TimeStamp", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = ((Element)localObject).getElementsByTagName("ack");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("Ack", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = ((Element)localObject).getElementsByTagName("correlationId");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("CorrelationId", a(((Element)localNodeList.item(0)).getChildNodes()));
      localObject = ((Element)localObject).getElementsByTagName("build");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      paramHashtable.put("Build", a(((Element)((NodeList)localObject).item(0)).getChildNodes()));
      localObject = paramString.getElementsByTagName("payKey");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      paramHashtable.put("PayKey", a(((Element)((NodeList)localObject).item(0)).getChildNodes()));
      localObject = paramString.getElementsByTagName("paymentExecStatus");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      paramHashtable.put("PaymentExecStatus", a(((Element)((NodeList)localObject).item(0)).getChildNodes()));
      localObject = paramString.getElementsByTagName("defaultFundingPlan");
      if (((NodeList)localObject).getLength() != 0) {
        paramHashtable.put("DefaultFundingPlan", e.a((Element)((NodeList)localObject).item(0)));
      }
      paramString = paramString.getElementsByTagName("payErrorList");
      if (paramString.getLength() > 0)
      {
        int i = ((Element)paramString.item(0)).getElementsByTagName("payError").getLength();
        if (i == 0) {
          return false;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    return true;
  }
  
  public static String[] b(String paramString)
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
  
  public static String c(String paramString)
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("TransactionID");
      if ((paramString != null) && (paramString.getLength() != 0))
      {
        paramString = paramString.item(0).getChildNodes().item(0).getNodeValue();
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    return null;
  }
  
  public static boolean c(String paramString, Hashtable paramHashtable)
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
      Object localObject = paramString.getElementsByTagName("responseEnvelope");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      localObject = (Element)((NodeList)localObject).item(0);
      NodeList localNodeList = ((Element)localObject).getElementsByTagName("timestamp");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("TimeStamp", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = ((Element)localObject).getElementsByTagName("ack");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("Ack", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = ((Element)localObject).getElementsByTagName("correlationId");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("CorrelationId", a(((Element)localNodeList.item(0)).getChildNodes()));
      localObject = ((Element)localObject).getElementsByTagName("build");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      paramHashtable.put("Build", a(((Element)((NodeList)localObject).item(0)).getChildNodes()));
      paramString = paramString.getElementsByTagName("preapprovalKey");
      if (paramString.getLength() == 0) {
        return false;
      }
      paramHashtable.put("PreapprovalKey", a(((Element)paramString.item(0)).getChildNodes()));
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static String d(String paramString)
  {
    String[] arrayOfString = new String[52];
    arrayOfString[0] = "+1";
    arrayOfString[1] = "+44";
    arrayOfString[2] = "+61";
    arrayOfString[3] = "+54";
    arrayOfString[4] = "+43";
    arrayOfString[5] = "+32";
    arrayOfString[6] = "+55";
    arrayOfString[7] = "+41";
    arrayOfString[8] = "+56";
    arrayOfString[9] = "+86";
    arrayOfString[10] = "+506";
    arrayOfString[11] = "+357";
    arrayOfString[12] = "+420";
    arrayOfString[13] = "+49";
    arrayOfString[14] = "+45";
    arrayOfString[15] = "+593";
    arrayOfString[16] = "+372";
    arrayOfString[17] = "+34";
    arrayOfString[18] = "+358";
    arrayOfString[19] = "+33";
    arrayOfString[20] = "+30";
    arrayOfString[21] = "+852";
    arrayOfString[22] = "+36";
    arrayOfString[23] = "+353";
    arrayOfString[24] = "+972";
    arrayOfString[25] = "+91";
    arrayOfString[26] = "+354";
    arrayOfString[27] = "+39";
    arrayOfString[28] = "+81";
    arrayOfString[29] = "+82";
    arrayOfString[30] = "+370";
    arrayOfString[31] = "+352";
    arrayOfString[32] = "+371";
    arrayOfString[33] = "+377";
    arrayOfString[34] = "+356";
    arrayOfString[35] = "+52";
    arrayOfString[36] = "+60";
    arrayOfString[37] = "+31";
    arrayOfString[38] = "+47";
    arrayOfString[39] = "+64";
    arrayOfString[40] = "+48";
    arrayOfString[41] = "+351";
    arrayOfString[42] = "+46";
    arrayOfString[43] = "+65";
    arrayOfString[44] = "+386";
    arrayOfString[45] = "+421";
    arrayOfString[46] = "+66";
    arrayOfString[47] = "+90";
    arrayOfString[48] = "+886";
    arrayOfString[49] = "+598";
    arrayOfString[50] = "+58";
    arrayOfString[51] = "+27";
    if (paramString.indexOf("+") == 0)
    {
      int i = 0;
      String str = paramString;
      while (i < arrayOfString.length)
      {
        if (paramString.indexOf(arrayOfString[i]) >= 0) {
          str = paramString.replace(arrayOfString[i], "");
        }
        i += 1;
      }
      return str;
    }
    return paramString;
  }
  
  public static boolean d(String paramString, Hashtable paramHashtable)
  {
    for (;;)
    {
      try
      {
        Object localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
        Object localObject2 = r.a();
        paramString = ((r)localObject2).e();
        Object localObject3 = ((Document)localObject1).getElementsByTagName("responseEnvelope");
        if (((NodeList)localObject3).getLength() == 0) {
          return false;
        }
        localObject3 = (Element)((NodeList)localObject3).item(0);
        NodeList localNodeList = ((Element)localObject3).getElementsByTagName("timestamp");
        if (localNodeList.getLength() == 0) {
          return false;
        }
        paramHashtable.put("TimeStamp", a(((Element)localNodeList.item(0)).getChildNodes()));
        localNodeList = ((Element)localObject3).getElementsByTagName("ack");
        if (localNodeList.getLength() == 0) {
          return false;
        }
        paramHashtable.put("Ack", a(((Element)localNodeList.item(0)).getChildNodes()));
        localNodeList = ((Element)localObject3).getElementsByTagName("correlationId");
        if (localNodeList.getLength() == 0) {
          return false;
        }
        paramHashtable.put("CorrelationId", a(((Element)localNodeList.item(0)).getChildNodes()));
        localObject3 = ((Element)localObject3).getElementsByTagName("build");
        if (((NodeList)localObject3).getLength() == 0) {
          return false;
        }
        paramHashtable.put("Build", a(((Element)((NodeList)localObject3).item(0)).getChildNodes()));
        localObject3 = ((Document)localObject1).getElementsByTagName("approved");
        if (((NodeList)localObject3).getLength() == 0) {
          return false;
        }
        localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
        paramString.a(((String)localObject3).equals("true"));
        paramHashtable.put("Approved", localObject3);
        localObject3 = ((Document)localObject1).getElementsByTagName("cancelUrl");
        if (((NodeList)localObject3).getLength() == 0) {
          return false;
        }
        localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
        ((r)localObject2).a((String)localObject3);
        paramHashtable.put("CancelUrl", localObject3);
        localObject3 = ((Document)localObject1).getElementsByTagName("currencyCode");
        if (((NodeList)localObject3).getLength() == 0) {
          return false;
        }
        localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
        paramString.a((String)localObject3);
        paramHashtable.put("CurrencyCode", localObject3);
        localObject3 = ((Document)localObject1).getElementsByTagName("dateOfMonth");
        if (((NodeList)localObject3).getLength() > 0)
        {
          localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
          paramString.d(Integer.parseInt((String)localObject3));
          paramHashtable.put("DateOfMonth", localObject3);
        }
        localObject3 = ((Document)localObject1).getElementsByTagName("dayOfWeek");
        if (((NodeList)localObject3).getLength() > 0)
        {
          localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
          if (((String)localObject3).equals("SUNDAY"))
          {
            i = 0;
            paramString.c(i);
            paramHashtable.put("DayOfWeek", localObject3);
          }
        }
        else
        {
          localObject3 = ((Document)localObject1).getElementsByTagName("endingDate");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            paramString.c((String)localObject3);
            paramHashtable.put("EndingDate", localObject3);
          }
          localObject3 = ((Document)localObject1).getElementsByTagName("ipnNotificationUrl");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            paramString.d((String)localObject3);
            paramHashtable.put("IpnNotificationUrl", localObject3);
          }
          localObject3 = ((Document)localObject1).getElementsByTagName("maxAmountPerPayment");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            paramString.a(new BigDecimal((String)localObject3));
            paramHashtable.put("MaxAmountPerPayment", localObject3);
          }
          localObject3 = ((Document)localObject1).getElementsByTagName("maxNumberOfPayments");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            paramString.a(Integer.parseInt((String)localObject3));
            paramHashtable.put("MaxNumberOfPayments", localObject3);
          }
          localObject3 = ((Document)localObject1).getElementsByTagName("maxNumberOfPaymentsPerPeriod");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            paramString.e(Integer.parseInt((String)localObject3));
            paramHashtable.put("MaxNumberOfPaymentsPerPeriod", localObject3);
          }
          localObject3 = ((Document)localObject1).getElementsByTagName("maxTotalAmountOfAllPayments");
          if (((NodeList)localObject3).getLength() != 0) {
            continue;
          }
          return false;
        }
        if (((String)localObject3).equals("MONDAY"))
        {
          i = 1;
          continue;
        }
        if (((String)localObject3).equals("TUESDAY"))
        {
          i = 2;
          continue;
        }
        if (((String)localObject3).equals("WEDNESDAY"))
        {
          i = 3;
          continue;
        }
        if (((String)localObject3).equals("THURSDAY"))
        {
          i = 4;
          continue;
        }
        if (((String)localObject3).equals("FRIDAY"))
        {
          i = 5;
          continue;
        }
        if (((String)localObject3).equals("SATURDAY"))
        {
          i = 6;
          continue;
          localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
          paramString.b(new BigDecimal((String)localObject3));
          paramHashtable.put("MaxTotalAmountOfAllPayments", localObject3);
          localObject3 = ((Document)localObject1).getElementsByTagName("memo");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            paramString.e((String)localObject3);
            paramHashtable.put("Memo", localObject3);
          }
          localObject3 = ((Document)localObject1).getElementsByTagName("paymentPeriod");
          if (((NodeList)localObject3).getLength() > 0)
          {
            localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
            if (((String)localObject3).equals("DAILY"))
            {
              i = 0;
              paramString.b(i);
              paramHashtable.put("PaymentPeriod", localObject3);
            }
          }
          else
          {
            localObject3 = ((Document)localObject1).getElementsByTagName("pinType");
            if (((NodeList)localObject3).getLength() > 0)
            {
              localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
              paramString.b(((String)localObject3).equals("REQUIRED"));
              paramHashtable.put("PinType", localObject3);
            }
            localObject3 = ((Document)localObject1).getElementsByTagName("returnUrl");
            if (((NodeList)localObject3).getLength() != 0) {
              continue;
            }
            return false;
          }
          if (((String)localObject3).equals("WEEKLY"))
          {
            i = 1;
            continue;
          }
          if (((String)localObject3).equals("BIWEEKLY"))
          {
            i = 2;
            continue;
          }
          if (((String)localObject3).equals("SEMIMONTHLY"))
          {
            i = 3;
            continue;
          }
          if (((String)localObject3).equals("MONTHLY"))
          {
            i = 4;
            continue;
          }
          if (!((String)localObject3).equals("ANNUALLY")) {
            break label1517;
          }
          i = 5;
          continue;
          localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
          ((r)localObject2).b((String)localObject3);
          paramHashtable.put("ReturnUrl", localObject3);
          localObject2 = ((Document)localObject1).getElementsByTagName("senderEmail");
          if (((NodeList)localObject2).getLength() > 0) {
            paramHashtable.put("SenderEmail", a(((Element)((NodeList)localObject2).item(0)).getChildNodes()));
          }
          localObject1 = ((Document)localObject1).getElementsByTagName("startingDate");
          if (((NodeList)localObject1).getLength() == 0) {
            return false;
          }
          localObject1 = a(((Element)((NodeList)localObject1).item(0)).getChildNodes());
          paramString.b((String)localObject1);
          paramHashtable.put("StartingDate", localObject1);
          paramHashtable.put("PreapprovalDetails", paramString);
          return true;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return false;
      }
      int i = 7;
      continue;
      label1517:
      i = 6;
    }
  }
  
  public static String e(String paramString)
  {
    String str = "";
    String[] arrayOfString = new String[52];
    arrayOfString[0] = "+1";
    arrayOfString[1] = "+44";
    arrayOfString[2] = "+61";
    arrayOfString[3] = "+54";
    arrayOfString[4] = "+43";
    arrayOfString[5] = "+32";
    arrayOfString[6] = "+55";
    arrayOfString[7] = "+41";
    arrayOfString[8] = "+56";
    arrayOfString[9] = "+86";
    arrayOfString[10] = "+506";
    arrayOfString[11] = "+357";
    arrayOfString[12] = "+420";
    arrayOfString[13] = "+49";
    arrayOfString[14] = "+45";
    arrayOfString[15] = "+593";
    arrayOfString[16] = "+372";
    arrayOfString[17] = "+34";
    arrayOfString[18] = "+358";
    arrayOfString[19] = "+33";
    arrayOfString[20] = "+30";
    arrayOfString[21] = "+852";
    arrayOfString[22] = "+36";
    arrayOfString[23] = "+353";
    arrayOfString[24] = "+972";
    arrayOfString[25] = "+91";
    arrayOfString[26] = "+354";
    arrayOfString[27] = "+39";
    arrayOfString[28] = "+81";
    arrayOfString[29] = "+82";
    arrayOfString[30] = "+370";
    arrayOfString[31] = "+352";
    arrayOfString[32] = "+371";
    arrayOfString[33] = "+377";
    arrayOfString[34] = "+356";
    arrayOfString[35] = "+52";
    arrayOfString[36] = "+60";
    arrayOfString[37] = "+31";
    arrayOfString[38] = "+47";
    arrayOfString[39] = "+64";
    arrayOfString[40] = "+48";
    arrayOfString[41] = "+351";
    arrayOfString[42] = "+46";
    arrayOfString[43] = "+65";
    arrayOfString[44] = "+386";
    arrayOfString[45] = "+421";
    arrayOfString[46] = "+66";
    arrayOfString[47] = "+90";
    arrayOfString[48] = "+886";
    arrayOfString[49] = "+598";
    arrayOfString[50] = "+58";
    arrayOfString[51] = "+27";
    if (paramString.indexOf("+") == 0)
    {
      str = "";
      int i = 0;
      while (i < arrayOfString.length)
      {
        if (paramString.indexOf(arrayOfString[i]) >= 0) {
          str = arrayOfString[i].substring(1);
        }
        i += 1;
      }
    }
    return str;
  }
  
  public static boolean e(String paramString, Hashtable paramHashtable)
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("responseEnvelope");
      if (paramString.getLength() == 0) {
        return false;
      }
      paramString = (Element)paramString.item(0);
      NodeList localNodeList = paramString.getElementsByTagName("timestamp");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("TimeStamp", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = paramString.getElementsByTagName("ack");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("Ack", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = paramString.getElementsByTagName("correlationId");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("CorrelationId", a(((Element)localNodeList.item(0)).getChildNodes()));
      paramString = paramString.getElementsByTagName("build");
      if (paramString.getLength() == 0) {
        return false;
      }
      paramHashtable.put("Build", a(((Element)paramString.item(0)).getChildNodes()));
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean f(String paramString, Hashtable paramHashtable)
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
      Object localObject = paramString.getElementsByTagName("responseEnvelope");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      localObject = (Element)((NodeList)localObject).item(0);
      NodeList localNodeList = ((Element)localObject).getElementsByTagName("timestamp");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("TimeStamp", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = ((Element)localObject).getElementsByTagName("ack");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("Ack", a(((Element)localNodeList.item(0)).getChildNodes()));
      localNodeList = ((Element)localObject).getElementsByTagName("correlationId");
      if (localNodeList.getLength() == 0) {
        return false;
      }
      paramHashtable.put("CorrelationId", a(((Element)localNodeList.item(0)).getChildNodes()));
      localObject = ((Element)localObject).getElementsByTagName("build");
      if (((NodeList)localObject).getLength() == 0) {
        return false;
      }
      paramHashtable.put("Build", a(((Element)((NodeList)localObject).item(0)).getChildNodes()));
      localObject = new Vector();
      paramString = paramString.getElementsByTagName("fundingPlan");
      int i = 0;
      while (i < paramString.getLength())
      {
        ((Vector)localObject).add(e.a((Element)paramString.item(i)));
        i += 1;
      }
      paramHashtable.put("FundingPlans", localObject);
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean g(String paramString, Hashtable paramHashtable)
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("MEPCreateDevicePinResponseType");
      int i = 0;
      while (i < paramString.getLength())
      {
        NodeList localNodeList = paramString.item(i).getChildNodes();
        int j = 0;
        while (j < localNodeList.getLength())
        {
          Node localNode = localNodeList.item(j);
          String str = localNode.getNodeName();
          if (str.compareTo("SessionToken") == 0) {
            paramHashtable.put(str, localNode.getChildNodes().item(0).getNodeValue());
          }
          j += 1;
        }
        i += 1;
      }
      return true;
    }
    catch (Exception paramString)
    {
      return false;
    }
  }
  
  /* Error */
  public static boolean h(String paramString, Hashtable paramHashtable)
  {
    // Byte code:
    //   0: invokestatic 19	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   3: invokevirtual 23	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   6: new 25	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: ldc 27
    //   13: invokevirtual 33	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   16: invokespecial 36	java/io/ByteArrayInputStream:<init>	([B)V
    //   19: invokevirtual 42	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   22: ldc_w 766
    //   25: invokeinterface 50 2 0
    //   30: astore_0
    //   31: iconst_0
    //   32: istore_2
    //   33: iload_2
    //   34: aload_0
    //   35: invokeinterface 56 1 0
    //   40: if_icmpge +151 -> 191
    //   43: aload_0
    //   44: iload_2
    //   45: invokeinterface 62 2 0
    //   50: invokeinterface 68 1 0
    //   55: astore 4
    //   57: iconst_0
    //   58: istore_3
    //   59: iload_3
    //   60: aload 4
    //   62: invokeinterface 56 1 0
    //   67: if_icmpge +117 -> 184
    //   70: aload 4
    //   72: iload_3
    //   73: invokeinterface 62 2 0
    //   78: astore 6
    //   80: aload 6
    //   82: invokeinterface 354 1 0
    //   87: astore 5
    //   89: aload 5
    //   91: ldc_w 307
    //   94: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   97: ifne +67 -> 164
    //   100: aload 6
    //   102: invokeinterface 68 1 0
    //   107: iconst_0
    //   108: invokeinterface 62 2 0
    //   113: invokeinterface 72 1 0
    //   118: astore 6
    //   120: aload_1
    //   121: aload 5
    //   123: aload 6
    //   125: invokevirtual 365	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   128: pop
    //   129: invokestatic 89	com/paypal/android/MEP/r:a	()Lcom/paypal/android/MEP/r;
    //   132: invokevirtual 104	com/paypal/android/MEP/r:f	()Landroid/content/Context;
    //   135: ldc_w 307
    //   138: iconst_2
    //   139: invokevirtual 386	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   142: astore 5
    //   144: aload 5
    //   146: aload 6
    //   148: invokevirtual 389	java/lang/String:getBytes	()[B
    //   151: invokevirtual 394	java/io/FileOutputStream:write	([B)V
    //   154: aload 5
    //   156: invokevirtual 397	java/io/FileOutputStream:flush	()V
    //   159: aload 5
    //   161: invokevirtual 398	java/io/FileOutputStream:close	()V
    //   164: iload_3
    //   165: iconst_1
    //   166: iadd
    //   167: istore_3
    //   168: goto -109 -> 59
    //   171: astore 5
    //   173: aload 5
    //   175: invokevirtual 399	java/io/FileNotFoundException:printStackTrace	()V
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
    //   0	198	1	paramHashtable	Hashtable
    //   32	156	2	i	int
    //   58	110	3	j	int
    //   55	16	4	localNodeList	NodeList
    //   87	73	5	localObject1	Object
    //   171	3	5	localFileNotFoundException	java.io.FileNotFoundException
    //   193	1	5	localIOException	java.io.IOException
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
  
  public static boolean i(String paramString, Hashtable paramHashtable)
  {
    for (;;)
    {
      int j;
      try
      {
        paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
        Object localObject1 = paramString.getElementsByTagName("responseEnvelope");
        if (((NodeList)localObject1).getLength() == 0) {
          return false;
        }
        localObject1 = (Element)((NodeList)localObject1).item(0);
        Object localObject2 = ((Element)localObject1).getElementsByTagName("timestamp");
        if (((NodeList)localObject2).getLength() == 0) {
          return false;
        }
        paramHashtable.put("TimeStamp", a(((Element)((NodeList)localObject2).item(0)).getChildNodes()));
        localObject2 = ((Element)localObject1).getElementsByTagName("ack");
        if (((NodeList)localObject2).getLength() == 0) {
          return false;
        }
        paramHashtable.put("Ack", a(((Element)((NodeList)localObject2).item(0)).getChildNodes()));
        localObject2 = ((Element)localObject1).getElementsByTagName("correlationId");
        if (((NodeList)localObject2).getLength() == 0) {
          return false;
        }
        paramHashtable.put("CorrelationId", a(((Element)((NodeList)localObject2).item(0)).getChildNodes()));
        localObject1 = ((Element)localObject1).getElementsByTagName("build");
        if (((NodeList)localObject1).getLength() == 0) {
          return false;
        }
        paramHashtable.put("Build", a(((Element)((NodeList)localObject1).item(0)).getChildNodes()));
        paramString = paramString.getElementsByTagName("availableAddress");
        if (paramString.getLength() == 0) {
          return false;
        }
        localObject1 = new Vector();
        j = 0;
        if (j < paramString.getLength())
        {
          localObject2 = new g();
          Object localObject3 = (Element)paramString.item(j);
          Object localObject4 = ((Element)localObject3).getElementsByTagName("baseAddress");
          if (((NodeList)localObject4).getLength() == 0)
          {
            i = 0;
            if (i != 0) {
              ((Vector)localObject1).add(localObject2);
            }
          }
          else
          {
            localObject4 = (Element)((NodeList)localObject4).item(0);
            NodeList localNodeList = ((Element)localObject4).getElementsByTagName("line1");
            if (localNodeList.getLength() == 0)
            {
              i = 0;
              continue;
            }
            ((g)localObject2).d(a(((Element)localNodeList.item(0)).getChildNodes()));
            localNodeList = ((Element)localObject4).getElementsByTagName("line2");
            if (localNodeList.getLength() > 0) {
              ((g)localObject2).e(a(((Element)localNodeList.item(0)).getChildNodes()));
            }
            localNodeList = ((Element)localObject4).getElementsByTagName("city");
            if (localNodeList.getLength() == 0)
            {
              i = 0;
              continue;
            }
            ((g)localObject2).b(a(((Element)localNodeList.item(0)).getChildNodes()));
            localNodeList = ((Element)localObject4).getElementsByTagName("state");
            if (localNodeList.getLength() > 0) {
              ((g)localObject2).g(a(((Element)localNodeList.item(0)).getChildNodes()));
            }
            localNodeList = ((Element)localObject4).getElementsByTagName("postalCode");
            if (localNodeList.getLength() > 0) {
              ((g)localObject2).f(a(((Element)localNodeList.item(0)).getChildNodes()));
            }
            localNodeList = ((Element)localObject4).getElementsByTagName("countryCode");
            if (localNodeList.getLength() == 0)
            {
              i = 0;
              continue;
            }
            ((g)localObject2).c(a(((Element)localNodeList.item(0)).getChildNodes()));
            localObject4 = ((Element)localObject4).getElementsByTagName("type");
            if (((NodeList)localObject4).getLength() > 0) {
              a(((Element)((NodeList)localObject4).item(0)).getChildNodes());
            }
            localObject4 = ((Element)localObject3).getElementsByTagName("addressId");
            if (((NodeList)localObject4).getLength() == 0)
            {
              i = 0;
              continue;
            }
            ((g)localObject2).h(a(((Element)((NodeList)localObject4).item(0)).getChildNodes()));
            localObject3 = ((Element)localObject3).getElementsByTagName("addresseeName");
            if (((NodeList)localObject3).getLength() <= 0) {
              break label837;
            }
            ((g)localObject2).a(a(((Element)((NodeList)localObject3).item(0)).getChildNodes()));
            break label837;
          }
        }
        else
        {
          paramHashtable.put("AvailableAddresses", localObject1);
          return true;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return false;
      }
      j += 1;
      continue;
      label837:
      int i = 1;
    }
  }
}
