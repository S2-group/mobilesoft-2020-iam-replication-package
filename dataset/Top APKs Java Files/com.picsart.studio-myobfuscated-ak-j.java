package myobfuscated.ak;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.paypal.android.MEP.e;
import com.paypal.android.MEP.l;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import myobfuscated.al.a;
import myobfuscated.al.g;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class j
{
  public static int a(String paramString)
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
          break label112;
        }
        paramString = localNodeList;
        if (localNodeList.getLength() == 0) {
          break label112;
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
    label112:
    return 200;
  }
  
  public static String a()
  {
    localObject21 = "";
    String str3 = "";
    String str6 = "";
    String str4 = "";
    String str5 = "";
    localObject17 = "";
    localObject20 = "";
    localObject2 = "";
    localObject3 = "";
    String str2 = "";
    localObject4 = "";
    localObject5 = localObject4;
    localObject1 = localObject3;
    localObject6 = localObject2;
    localObject14 = localObject20;
    localObject15 = localObject17;
    localObject16 = str5;
    localObject7 = str4;
    localObject18 = str6;
    localObject19 = str3;
    for (;;)
    {
      Object localObject13;
      String str1;
      try
      {
        localObject10 = URLEncoder.encode(e.a().f(), "utf-8");
        localObject5 = localObject4;
        localObject1 = localObject3;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = str5;
        localObject7 = str4;
        localObject18 = str6;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject11 = (TelephonyManager)e.a().e().getSystemService("phone");
        localObject5 = localObject4;
        localObject1 = localObject3;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = str5;
        localObject7 = str4;
        localObject18 = str6;
        localObject19 = str3;
        localObject21 = localObject10;
        i = ((TelephonyManager)localObject11).getPhoneType();
        localObject9 = "MEID";
        localObject8 = "AndroidCDMA";
        if (i == 1)
        {
          localObject9 = "IMEI";
          localObject8 = "AndroidGSM";
        }
        localObject5 = localObject8;
        localObject1 = localObject9;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = str5;
        localObject7 = str4;
        localObject18 = str6;
        localObject19 = str3;
        localObject21 = localObject10;
        String str7 = ((TelephonyManager)localObject11).getDeviceId();
        localObject5 = localObject8;
        localObject1 = localObject9;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = str5;
        localObject7 = str4;
        localObject18 = str6;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject11 = URLEncoder.encode("Phone", "utf-8");
        localObject12 = str7;
        localObject3 = localObject8;
        localObject4 = localObject9;
        localObject2 = localObject11;
        if (str7 == null)
        {
          localObject5 = localObject8;
          localObject1 = localObject9;
          localObject6 = localObject11;
          localObject14 = localObject20;
          localObject15 = localObject17;
          localObject16 = str5;
          localObject7 = str4;
          localObject18 = str6;
          localObject19 = str3;
          localObject21 = localObject10;
          localObject12 = ((WifiManager)e.a().e().getSystemService("wifi")).getConnectionInfo().getMacAddress();
          localObject5 = localObject8;
          localObject1 = localObject9;
          localObject6 = localObject11;
          localObject14 = localObject20;
          localObject15 = localObject17;
          localObject16 = str5;
          localObject7 = str4;
          localObject18 = str6;
          localObject19 = str3;
          localObject21 = localObject10;
          localObject4 = URLEncoder.encode("MAC", "utf-8");
          localObject3 = "AndroidGSM";
          localObject5 = localObject3;
          localObject1 = localObject4;
          localObject6 = localObject11;
          localObject14 = localObject20;
          localObject15 = localObject17;
          localObject16 = str5;
          localObject7 = str4;
          localObject18 = str6;
          localObject19 = str3;
          localObject21 = localObject10;
          localObject2 = URLEncoder.encode("Tablet", "utf-8");
        }
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = str5;
        localObject7 = str4;
        localObject18 = str6;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject8 = URLEncoder.encode((String)localObject12, "utf-8");
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = str5;
        localObject7 = str4;
        localObject18 = localObject8;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject11 = URLEncoder.encode(Build.DEVICE);
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = localObject11;
        localObject7 = str4;
        localObject18 = localObject8;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject9 = Build.MODEL;
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = localObject11;
        localObject7 = localObject9;
        localObject18 = localObject8;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject12 = ((String)localObject9).replaceAll(" ", "%20");
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = localObject11;
        localObject7 = localObject12;
        localObject18 = localObject8;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject17 = URLEncoder.encode("Android", "utf-8");
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = localObject11;
        localObject7 = localObject12;
        localObject18 = localObject8;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject20 = URLEncoder.encode(Build.VERSION.SDK, "utf-8");
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = localObject11;
        localObject7 = localObject12;
        localObject18 = localObject8;
        localObject19 = str3;
        localObject21 = localObject10;
        localObject9 = URLEncoder.encode(e.x(), "utf-8");
        localObject5 = localObject3;
        localObject1 = localObject4;
        localObject6 = localObject2;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject16 = localObject11;
        localObject7 = localObject12;
        localObject18 = localObject8;
        localObject19 = localObject9;
        localObject21 = localObject10;
        str3 = e.a().e().getPackageName();
        localObject1 = str3;
        localObject6 = str2;
      }
      catch (Exception localException2)
      {
        try
        {
          int i;
          Object localObject12;
          localObject1 = e.a().e().openFileInput("DeviceReferenceToken");
          localObject3 = new byte[((FileInputStream)localObject1).available()];
          ((FileInputStream)localObject1).read((byte[])localObject3);
          ((FileInputStream)localObject1).close();
          localObject1 = new String((byte[])localObject3);
          localObject1 = (String)localObject2 + "<DeviceReferenceToken xsi:type=\"xs:string\">" + (String)localObject1 + "</DeviceReferenceToken>";
          return (String)localObject1 + "</Request></MEPDeviceInterrogationReq></SOAP-ENV:Body></SOAP-ENV:Envelope>";
          i += 1;
          continue;
          localObject6 = null;
          break label1546;
          localException2 = localException2;
          localObject3 = localObject5;
          localObject5 = "";
          localObject17 = "";
          localObject20 = "";
          Object localObject10 = localObject21;
          Object localObject9 = localObject19;
          Object localObject8 = localObject18;
          Object localObject11 = localObject16;
          localObject2 = localObject6;
          localObject4 = localObject1;
          localObject1 = localObject20;
          localObject6 = localObject17;
          localException2.printStackTrace();
          localObject17 = localObject6;
          localObject6 = localObject2;
          localObject13 = localObject7;
          localObject16 = localObject8;
          localObject18 = "false";
          localObject2 = localObject17;
          localObject20 = localObject14;
          localObject7 = localObject15;
          localObject8 = localObject11;
          localObject11 = localObject13;
          localObject13 = localObject16;
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
        localObject5 = e.a().e().getPackageManager();
        localObject6 = str2;
        localObject7 = ((PackageManager)localObject5).getInstalledApplications(0);
        localObject6 = str2;
        Collections.sort((List)localObject7, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject5));
        i = 0;
        localObject6 = str2;
        if (i >= ((List)localObject7).size()) {
          continue;
        }
        localObject6 = str2;
        localObject14 = (ApplicationInfo)((List)localObject7).get(i);
        localObject6 = str2;
        if (!((ApplicationInfo)localObject14).packageName.equals(localObject1)) {
          continue;
        }
        localObject6 = str2;
        localObject5 = ((ApplicationInfo)localObject14).loadLabel((PackageManager)localObject5).toString();
        localObject6 = localObject5;
      }
      catch (Exception localException3)
      {
        localObject5 = localObject6;
        localObject6 = str1;
        localObject14 = localObject20;
        localObject15 = localObject17;
        localObject7 = localObject13;
        localObject13 = localException3;
        continue;
        localObject6 = "false";
        continue;
        localObject5 = localObject6;
        if (localObject6 != null) {
          continue;
        }
        localObject5 = str1;
      }
    }
    localObject6 = localObject5;
    if (((String)localObject8).equals("000000000000000"))
    {
      localObject6 = "true";
      localObject7 = localObject17;
      localObject16 = localObject11;
      localObject11 = localObject12;
      localObject15 = localObject1;
      localObject14 = localObject6;
      localObject12 = localObject8;
      localObject8 = localObject16;
      localObject6 = localObject2;
      localObject2 = localObject15;
      localObject2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:cc=\"urn:ebay:apis:CoreComponentTypes\" xmlns:ed=\"urn:ebay:apis:EnhancedDataTypes\" xmlns:wsu=\"http://schemas.xmlsoap.org/ws/2002/07/utility\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ebl=\"urn:ebay:apis:eBLBaseComponents\" xmlns:ns=\"urn:ebay:api:PayPalAPI\"><SOAP-ENV:Header><Security xmlns=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xsi:type=\"wsse:SecurityType\"></Security><RequesterCredentials xmlns=\"urn:ebay:api:PayPalAPI\" xsi:type=\"ebl:CustomSecurityHeaderType\"><Credentials xmlns=\"urn:ebay:apis:eBLBaseComponents\" xsi:type=\"ebl:UserIdPasswordType\"><Username xsi:type=\"xs:string\">gmapi_client</Username><Password xsi:type=\"xs:string\">11111111</Password></Credentials></RequesterCredentials></SOAP-ENV:Header><SOAP-ENV:Body id=\"_0\"><MEPDeviceInterrogationReq xmlns=\"urn:ebay:api:PayPalAPI\"><Request><Version xmlns=\"urn:ebay:apis:eBLBaseComponents\">1.0</Version><PayPalAppID xsi:type=\"xs:string\">" + (String)localObject10 + "</PayPalAppID>" + "<DeviceDetails xsi:type=\"ns:MEPDeviceDetailsType\">" + "<deviceID xsi:type=\"ns:MEPDeviceIDType\">" + "<deviceIdentifier xsi:type=\"xs:string\">" + (String)localObject12 + "</deviceIdentifier>" + "<deviceIdType xsi:type=\"ns:MEPDeviceIdentifierType\">" + (String)localObject4 + "</deviceIdType>" + "</deviceID>" + "<deviceName xsi:type=\"xs:string\">" + (String)localObject8 + "</deviceName>" + "<deviceModel xsi:type=\"xs:string\">" + (String)localObject11 + "</deviceModel>" + "<systemName xsi:type=\"xs:string\">" + (String)localObject7 + "</systemName>" + "<systemVersion xsi:type=\"xs:string\">" + (String)localObject20 + "</systemVersion>" + "<deviceCategory xsi:type=\"ns:MEPDeviceCategoryType\">" + (String)localObject6 + "</deviceCategory>" + "<isDeviceSimulator xsi:type=\"xs:boolean\">" + (String)localObject14 + "</isDeviceSimulator>" + "</DeviceDetails>" + "<ApplicationDetails xsi:type=\"ns:MEPApplicationDetailsType\">" + "<appID xsi:type=\"ns:MEPAppIDType\">" + "<deviceAppID xsi:type=\"xs:string\">" + (String)localObject1 + "</deviceAppID>" + "</appID>" + "<appName xsi:type=\"xs:string\">" + (String)localObject2 + "</appName>" + "<appDisplayName xsi:type=\"xs:string\">" + (String)localObject5 + "</appDisplayName>" + "<clientPlatform xsi:type=\"xs:string\">" + (String)localObject3 + "</clientPlatform>" + "</ApplicationDetails>" + "<MEPVersion xsi:type=\"xs:string\">" + (String)localObject9 + "</MEPVersion>";
    }
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
  
  public static boolean a(String paramString, Hashtable<String, Object> paramHashtable)
  {
    int i;
    int j;
    label62:
    Object localObject1;
    int k;
    label174:
    Object localObject2;
    Object localObject3;
    try
    {
      NodeList localNodeList1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8"))).getElementsByTagName("MEPDeviceInterrogationResponseType");
      i = 0;
      if (i >= localNodeList1.getLength()) {
        break label691;
      }
      NodeList localNodeList2 = localNodeList1.item(i).getChildNodes();
      j = 0;
      if (j >= localNodeList2.getLength()) {
        break label684;
      }
      localObject1 = localNodeList2.item(j);
      paramString = ((Node)localObject1).getNodeName();
      if (paramString.compareTo("PayButtonEnable") == 0)
      {
        localObject1 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
        paramHashtable.put(paramString, localObject1);
        if (((String)localObject1).compareToIgnoreCase("true") == 0) {
          break label694;
        }
        Log.e("Error", "Authentication failed, button not enabled.");
        break label694;
      }
      if (paramString.compareTo("EncryptionDetails") == 0)
      {
        localObject1 = ((Node)localObject1).getChildNodes();
        k = 0;
        if (k >= ((NodeList)localObject1).getLength()) {
          break label694;
        }
        localObject2 = ((NodeList)localObject1).item(k);
        localObject3 = ((Node)localObject2).getNodeName();
        if ((((Node)localObject2).getChildNodes().getLength() <= 0) || (((String)localObject3).compareTo("Type") != 0)) {
          break label701;
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
        paramString = e.a().e().openFileOutput("DeviceReferenceToken", 2);
        paramString.write(((String)localObject1).getBytes());
        paramString.flush();
        paramString.close();
      }
      catch (FileNotFoundException paramString)
      {
        paramString.printStackTrace();
        break label694;
        if (paramString.compareTo("DeviceAuthDetails") == 0)
        {
          localObject2 = ((Node)localObject1).getChildNodes();
          k = 0;
          if (k < ((NodeList)localObject2).getLength())
          {
            localObject1 = ((NodeList)localObject2).item(k);
            paramString = ((Node)localObject1).getNodeName();
            if (((Node)localObject1).getChildNodes().getLength() <= 0) {
              break label710;
            }
            localObject3 = ((Node)localObject1).getChildNodes().item(0).getNodeValue();
            if (paramString.compareTo("UserName") == 0)
            {
              e.a().d((String)localObject3);
              break label710;
            }
            if (paramString.compareTo("Email") == 0)
            {
              e.a().e((String)localObject3);
              break label710;
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
                  break label719;
                }
                localObject1 = paramString + ((NodeList)localObject3).item(m).getChildNodes().item(0).getNodeValue();
                break label719;
              }
              if (paramString.length() == 0) {
                break label710;
              }
              e.a().f(paramString);
              break label710;
            }
            if (paramString.compareTo("AuthMethod") == 0)
            {
              e.a().b(Integer.parseInt((String)localObject3));
              break label710;
            }
            if (paramString.compareTo("AuthSettings") != 0) {
              break label710;
            }
            e.a().c(Integer.parseInt((String)localObject3));
            break label710;
          }
          if ((e.a().t() == 0) && (e.a().s() == 2))
          {
            e.a().b(0);
            break label694;
            label684:
            i += 1;
            break;
            label691:
            return true;
          }
        }
      }
      catch (IOException paramString) {}
      label694:
      j += 1;
      break label62;
      label701:
      k += 1;
      break label174;
      label710:
      k += 1;
      continue;
      label719:
      m += 1;
      paramString = (String)localObject1;
    }
  }
  
  public static String b()
  {
    try
    {
      Object localObject = e.a().e().openFileInput("DeviceReferenceToken");
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
  
  public static boolean b(String paramString, Hashtable<String, Object> paramHashtable)
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
        paramHashtable.put("DefaultFundingPlan", g.a((Element)((NodeList)localObject).item(0)));
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
      if (paramString != null)
      {
        if (paramString.getLength() == 0) {
          return null;
        }
        paramString = paramString.item(0).getChildNodes().item(0).getNodeValue();
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean c(String paramString, Hashtable<String, Object> paramHashtable)
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
    int i = 0;
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
      String str1 = paramString;
      for (;;)
      {
        str2 = str1;
        if (i >= arrayOfString.length) {
          break;
        }
        if (paramString.indexOf(arrayOfString[i]) >= 0) {
          str1 = paramString.replace(arrayOfString[i], "");
        }
        i += 1;
      }
    }
    String str2 = paramString;
    return str2;
  }
  
  public static boolean d(String paramString, Hashtable<String, Object> paramHashtable)
  {
    try
    {
      Object localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
      Object localObject2 = e.a();
      paramString = ((e)localObject2).d();
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
      ((e)localObject2).b((String)localObject3);
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
        paramString.c(paramString.g((String)localObject3));
        paramHashtable.put("DayOfWeek", localObject3);
      }
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
      if (((NodeList)localObject3).getLength() == 0) {
        return false;
      }
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
        paramString.b(paramString.f((String)localObject3));
        paramHashtable.put("PaymentPeriod", localObject3);
      }
      localObject3 = ((Document)localObject1).getElementsByTagName("pinType");
      if (((NodeList)localObject3).getLength() > 0)
      {
        localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
        paramString.b(((String)localObject3).equals("REQUIRED"));
        paramHashtable.put("PinType", localObject3);
      }
      localObject3 = ((Document)localObject1).getElementsByTagName("returnUrl");
      if (((NodeList)localObject3).getLength() == 0) {
        return false;
      }
      localObject3 = a(((Element)((NodeList)localObject3).item(0)).getChildNodes());
      ((e)localObject2).c((String)localObject3);
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
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static String e(String paramString)
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
      String str1 = "";
      int i = 0;
      for (;;)
      {
        str2 = str1;
        if (i >= arrayOfString.length) {
          break;
        }
        if (paramString.indexOf(arrayOfString[i]) >= 0) {
          str1 = arrayOfString[i].substring(1);
        }
        i += 1;
      }
    }
    String str2 = "";
    return str2;
  }
  
  public static boolean e(String paramString, Hashtable<String, Object> paramHashtable)
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
  
  public static boolean f(String paramString, Hashtable<String, Object> paramHashtable)
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
        ((Vector)localObject).add(g.a((Element)paramString.item(i)));
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
  
  public static boolean g(String paramString, Hashtable<String, Object> paramHashtable)
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
    catch (Exception paramString) {}
    return false;
  }
  
  /* Error */
  public static boolean h(String paramString, Hashtable<String, Object> paramHashtable)
  {
    // Byte code:
    //   0: invokestatic 14	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   3: invokevirtual 18	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   6: new 20	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: ldc 22
    //   13: invokevirtual 28	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   16: invokespecial 32	java/io/ByteArrayInputStream:<init>	([B)V
    //   19: invokevirtual 38	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   22: ldc_w 746
    //   25: invokeinterface 46 2 0
    //   30: astore_0
    //   31: iconst_0
    //   32: istore_2
    //   33: iload_2
    //   34: aload_0
    //   35: invokeinterface 52 1 0
    //   40: if_icmpge +151 -> 191
    //   43: aload_0
    //   44: iload_2
    //   45: invokeinterface 58 2 0
    //   50: invokeinterface 64 1 0
    //   55: astore 4
    //   57: iconst_0
    //   58: istore_3
    //   59: iload_3
    //   60: aload 4
    //   62: invokeinterface 52 1 0
    //   67: if_icmpge +117 -> 184
    //   70: aload 4
    //   72: iload_3
    //   73: invokeinterface 58 2 0
    //   78: astore 6
    //   80: aload 6
    //   82: invokeinterface 354 1 0
    //   87: astore 5
    //   89: aload 5
    //   91: ldc_w 308
    //   94: invokevirtual 359	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   97: ifne +67 -> 164
    //   100: aload 6
    //   102: invokeinterface 64 1 0
    //   107: iconst_0
    //   108: invokeinterface 58 2 0
    //   113: invokeinterface 68 1 0
    //   118: astore 6
    //   120: aload_1
    //   121: aload 5
    //   123: aload 6
    //   125: invokevirtual 365	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   128: pop
    //   129: invokestatic 85	com/paypal/android/MEP/e:a	()Lcom/paypal/android/MEP/e;
    //   132: invokevirtual 100	com/paypal/android/MEP/e:e	()Landroid/content/Context;
    //   135: ldc_w 308
    //   138: iconst_2
    //   139: invokevirtual 385	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   142: astore 5
    //   144: aload 5
    //   146: aload 6
    //   148: invokevirtual 388	java/lang/String:getBytes	()[B
    //   151: invokevirtual 393	java/io/FileOutputStream:write	([B)V
    //   154: aload 5
    //   156: invokevirtual 396	java/io/FileOutputStream:flush	()V
    //   159: aload 5
    //   161: invokevirtual 397	java/io/FileOutputStream:close	()V
    //   164: iload_3
    //   165: iconst_1
    //   166: iadd
    //   167: istore_3
    //   168: goto -109 -> 59
    //   171: astore 5
    //   173: aload 5
    //   175: invokevirtual 398	java/io/FileNotFoundException:printStackTrace	()V
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
  
  public static boolean i(String paramString, Hashtable<String, Object> paramHashtable)
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
          localObject2 = new a();
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
            ((a)localObject2).d(a(((Element)localNodeList.item(0)).getChildNodes()));
            localNodeList = ((Element)localObject4).getElementsByTagName("line2");
            if (localNodeList.getLength() > 0) {
              ((a)localObject2).e(a(((Element)localNodeList.item(0)).getChildNodes()));
            }
            localNodeList = ((Element)localObject4).getElementsByTagName("city");
            if (localNodeList.getLength() == 0)
            {
              i = 0;
              continue;
            }
            ((a)localObject2).b(a(((Element)localNodeList.item(0)).getChildNodes()));
            localNodeList = ((Element)localObject4).getElementsByTagName("state");
            if (localNodeList.getLength() > 0) {
              ((a)localObject2).g(a(((Element)localNodeList.item(0)).getChildNodes()));
            }
            localNodeList = ((Element)localObject4).getElementsByTagName("postalCode");
            if (localNodeList.getLength() > 0) {
              ((a)localObject2).f(a(((Element)localNodeList.item(0)).getChildNodes()));
            }
            localNodeList = ((Element)localObject4).getElementsByTagName("countryCode");
            if (localNodeList.getLength() == 0)
            {
              i = 0;
              continue;
            }
            ((a)localObject2).c(a(((Element)localNodeList.item(0)).getChildNodes()));
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
            ((a)localObject2).h(a(((Element)((NodeList)localObject4).item(0)).getChildNodes()));
            localObject3 = ((Element)localObject3).getElementsByTagName("addresseeName");
            if (((NodeList)localObject3).getLength() <= 0) {
              break label837;
            }
            ((a)localObject2).a(a(((Element)((NodeList)localObject3).item(0)).getChildNodes()));
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
