package com.alipay.mobilesecuritysdk.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Base64;
import com.alipay.mobilesecuritysdk.constant.LocationNameEnum;
import com.alipay.mobilesecuritysdk.datainfo.AppInfo;
import com.alipay.mobilesecuritysdk.datainfo.LocationInfo;
import com.alipay.mobilesecuritysdk.datainfo.WifiCollectInfo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class c
{
  private final int a = 8;
  private d b = new d();
  
  public c() {}
  
  private String a(byte[] paramArrayOfByte)
  {
    for (;;)
    {
      int j;
      int k;
      int i;
      try
      {
        paramArrayOfByte = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfByte))).getPublicKey().toString();
        int m = paramArrayOfByte.indexOf("modulus");
        j = paramArrayOfByte.indexOf("\n", m + 8);
        k = paramArrayOfByte.indexOf(",", m + 8);
        if ((j < 0) && (k > 0))
        {
          i = k;
          if (i < 0)
          {
            paramArrayOfByte = paramArrayOfByte.substring(m + 8).trim();
            return com.alipay.mobilesecuritysdk.c.c.MD5(paramArrayOfByte);
          }
          paramArrayOfByte = paramArrayOfByte.substring(m + 8, i).trim();
          continue;
        }
        if (k >= 0) {
          break label132;
        }
      }
      catch (Exception paramArrayOfByte)
      {
        paramArrayOfByte.getMessage();
        return null;
      }
      if (j > 0) {
        i = j;
      } else {
        label132:
        if (j < k)
        {
          i = j;
        }
        else
        {
          i = k;
          if (k >= j) {
            i = -1;
          }
        }
      }
    }
  }
  
  private List<WifiCollectInfo> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (WifiManager)paramContext.getSystemService("wifi");
    try
    {
      if (((WifiManager)localObject1).isWifiEnabled())
      {
        paramContext = ((WifiManager)localObject1).getConnectionInfo();
        Object localObject2 = new WifiCollectInfo();
        ((WifiCollectInfo)localObject2).setMbssid(paramContext.getBSSID());
        ((WifiCollectInfo)localObject2).setMssid(Base64.encodeToString(paramContext.getSSID().getBytes(), 8));
        ((WifiCollectInfo)localObject2).setMlevel(paramContext.getRssi());
        ((WifiCollectInfo)localObject2).setMiscurrent(true);
        localArrayList.add(localObject2);
        localObject1 = ((WifiManager)localObject1).getScanResults().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ScanResult)((Iterator)localObject1).next();
          if ((!((ScanResult)localObject2).BSSID.equals(paramContext.getBSSID())) && (!((ScanResult)localObject2).SSID.equals(paramContext.getSSID())))
          {
            WifiCollectInfo localWifiCollectInfo = new WifiCollectInfo();
            localWifiCollectInfo.setMbssid(((ScanResult)localObject2).BSSID);
            localWifiCollectInfo.setMssid(Base64.encodeToString(((ScanResult)localObject2).SSID.getBytes(), 8));
            localWifiCollectInfo.setMlevel(((ScanResult)localObject2).level);
            localWifiCollectInfo.setMiscurrent(false);
            localArrayList.add(localWifiCollectInfo);
          }
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      new StringBuilder().append("").append(paramContext.getLocalizedMessage()).toString();
    }
    return localArrayList;
  }
  
  private void a(TelephonyManager paramTelephonyManager, LocationInfo paramLocationInfo, int paramInt)
  {
    String str3 = "";
    String str2 = "";
    String str1 = "";
    String str4 = "";
    Object localObject4;
    Object localObject1;
    Object localObject2;
    Object localObject3;
    if (paramInt == 2)
    {
      localObject4 = str4;
      localObject1 = str1;
      localObject2 = str2;
      localObject3 = str3;
    }
    for (;;)
    {
      Object localObject5;
      try
      {
        CdmaCellLocation localCdmaCellLocation = (CdmaCellLocation)paramTelephonyManager.getCellLocation();
        localObject5 = str4;
        String str5 = str1;
        String str6 = str2;
        String str7 = str3;
        if (localCdmaCellLocation != null)
        {
          localObject5 = str4;
          str5 = str1;
          str6 = str2;
          str7 = str3;
          localObject4 = str4;
          localObject1 = str1;
          localObject2 = str2;
          localObject3 = str3;
          if (com.alipay.mobilesecuritysdk.c.c.isBlank(paramLocationInfo.getLatitude()))
          {
            localObject5 = str4;
            str5 = str1;
            str6 = str2;
            str7 = str3;
            localObject4 = str4;
            localObject1 = str1;
            localObject2 = str2;
            localObject3 = str3;
            if (com.alipay.mobilesecuritysdk.c.c.isBlank(paramLocationInfo.getLongitude()))
            {
              localObject4 = str4;
              localObject1 = str1;
              localObject2 = str2;
              localObject3 = str3;
              localObject5 = String.valueOf(localCdmaCellLocation.getNetworkId());
              localObject4 = localObject5;
              localObject1 = str1;
              localObject2 = str2;
              localObject3 = str3;
              str7 = paramTelephonyManager.getNetworkOperator().substring(0, 3);
              localObject4 = localObject5;
              localObject1 = str1;
              localObject2 = str2;
              localObject3 = str7;
              str6 = String.valueOf(localCdmaCellLocation.getSystemId());
              localObject4 = localObject5;
              localObject1 = str1;
              localObject2 = str6;
              localObject3 = str7;
              str5 = String.valueOf(localCdmaCellLocation.getBaseStationId());
              localObject4 = localObject5;
              localObject1 = str5;
              localObject2 = str6;
              localObject3 = str7;
              paramLocationInfo.setLatitude(localCdmaCellLocation.getBaseStationLatitude());
              localObject4 = localObject5;
              localObject1 = str5;
              localObject2 = str6;
              localObject3 = str7;
              paramLocationInfo.setLongitude(localCdmaCellLocation.getBaseStationLongitude());
            }
          }
        }
        localObject3 = str7;
        localObject2 = str6;
        localObject1 = str5;
        localObject4 = localObject5;
      }
      catch (Exception paramTelephonyManager)
      {
        paramTelephonyManager.getLocalizedMessage();
        continue;
      }
      paramLocationInfo.setMcc((String)localObject3);
      paramLocationInfo.setMnc((String)localObject2);
      paramLocationInfo.setCid((String)localObject1);
      paramLocationInfo.setLac((String)localObject4);
      return;
      localObject1 = str1;
      localObject2 = str2;
      localObject3 = str3;
      try
      {
        localObject5 = (GsmCellLocation)paramTelephonyManager.getCellLocation();
        localObject4 = str4;
        localObject1 = str1;
        localObject2 = str2;
        localObject3 = str3;
        if (localObject5 != null)
        {
          localObject1 = str1;
          localObject2 = str2;
          localObject3 = str3;
          str3 = paramTelephonyManager.getNetworkOperator().substring(0, 3);
          localObject1 = str1;
          localObject2 = str2;
          localObject3 = str3;
          paramTelephonyManager = paramTelephonyManager.getNetworkOperator().substring(3, 5);
          localObject1 = str1;
          localObject2 = paramTelephonyManager;
          localObject3 = str3;
          str1 = String.valueOf(((GsmCellLocation)localObject5).getCid());
          localObject1 = str1;
          localObject2 = paramTelephonyManager;
          localObject3 = str3;
          paramInt = ((GsmCellLocation)localObject5).getLac();
          localObject4 = String.valueOf(paramInt);
          localObject1 = str1;
          localObject2 = paramTelephonyManager;
          localObject3 = str3;
        }
      }
      catch (Exception paramTelephonyManager)
      {
        paramTelephonyManager.getLocalizedMessage();
        localObject4 = str4;
      }
    }
  }
  
  public String GetLocationInfo(Context paramContext, List<String> paramList)
  {
    List localList = collectLocateInfos(paramContext);
    this.b.setTid(paramList);
    return this.b.LocationToString(paramContext.getFilesDir().getPath() + File.separator + "", localList);
  }
  
  public List<LocationInfo> collectLocateInfos(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        LocationInfo localLocationInfo = new LocationInfo();
        localLocationInfo.setTime(com.alipay.mobilesecuritysdk.c.c.convertDate2String(new Date()));
        localLocationInfo.setCid("");
        localLocationInfo.setLac("");
        localLocationInfo.setLatitude("");
        localLocationInfo.setLongitude("");
        localLocationInfo.setMcc("");
        localLocationInfo.setMnc("");
        localLocationInfo.setPhonetype("");
        Object localObject = (LocationManager)paramContext.getSystemService("location");
        if (((LocationManager)localObject).isProviderEnabled("network"))
        {
          e localE = new e();
          ((LocationManager)localObject).requestLocationUpdates("network", 300000L, 0.0F, localE, Looper.getMainLooper());
          ((LocationManager)localObject).removeUpdates(localE);
          localObject = ((LocationManager)localObject).getLastKnownLocation("network");
          if (localObject != null)
          {
            localLocationInfo.setLatitude(((Location)localObject).getLatitude());
            localLocationInfo.setLongitude(((Location)localObject).getLongitude());
            i = 1;
            localObject = (TelephonyManager)paramContext.getSystemService("phone");
            if (((TelephonyManager)localObject).getPhoneType() == 2)
            {
              localLocationInfo.setPhonetype(LocationNameEnum.CDMA.getValue());
              if (i == 0) {
                a((TelephonyManager)localObject, localLocationInfo, 2);
              }
              paramContext = a(paramContext);
              if ((paramContext != null) && (paramContext.size() > 0)) {
                localLocationInfo.setWifi(paramContext);
              }
              localArrayList.add(localLocationInfo);
              if (localArrayList.size() > 0) {
                return localArrayList;
              }
            }
            else
            {
              localLocationInfo.setPhonetype(LocationNameEnum.GSM.getValue());
              a((TelephonyManager)localObject, localLocationInfo, 1);
              continue;
            }
            return null;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.getMessage();
        return null;
      }
      int i = 0;
    }
  }
  
  public List<AppInfo> collectappInfos(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      paramContext = paramContext.getPackageManager();
      Iterator localIterator = paramContext.getInstalledPackages(4096).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (((paramContext.checkPermission("android.permission.READ_SMS", localPackageInfo.packageName) == 0) || (paramContext.checkPermission("android.permission.RECEIVE_SMS", localPackageInfo.packageName) == 0)) && ((paramContext.checkPermission("android.permission.SEND_SMS", localPackageInfo.packageName) == 0) || (paramContext.checkPermission("android.permission.INTERNET", localPackageInfo.packageName) == 0)))
        {
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.setPkgName(localPackageInfo.packageName);
          localAppInfo.setPkeyhash(a(paramContext.getPackageInfo(localPackageInfo.packageName, 64).signatures[0].toByteArray()));
          if (localAppInfo.validate()) {
            localArrayList.add(localAppInfo);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.getMessage();
      return null;
    }
  }
}
