package com.cmb.pboc.device;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.cmb.pboc.context.ContextHolder;
import com.cmb.pboc.logger.PbocLog;
import java.util.List;

public class DeviceInfo
{
  private static final String a = DeviceInfo.class.getSimpleName();
  private static volatile DeviceInfo b;
  private String A = "";
  private String B = "";
  private String C = "";
  private String D = "";
  private String E = "com.richhouse.android.tsm2.service";
  private String F = "";
  private String G = "com.snowballtech.walletservice";
  private String H = "";
  private String I = "";
  private String J = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "Android";
  private String j = "";
  private String k = "";
  private String l = "";
  private String m = "";
  private String n = "";
  private String o = "";
  private String p = "";
  private String q = "";
  private String r = "";
  private String s = "";
  private String t = "";
  private String u = "";
  private String v = "";
  private String w = "";
  private String x = "";
  private String y = "";
  private String z = "";
  
  private DeviceInfo()
  {
    PbocLog.a(a, "Building DeviceInfo.");
    Object localObject1 = new StringBuffer();
    ((StringBuffer)localObject1).append("其它信息获取：");
    ((StringBuffer)localObject1).append("\n*************************\n");
    Object localObject4 = ContextHolder.a().b();
    if (localObject4 == null) {
      PbocLog.d(a, "context obj isn't ok!");
    }
    for (;;)
    {
      localObject1 = ContextHolder.a().b();
      if (localObject1 != null) {
        break label2338;
      }
      PbocLog.d(a, "context obj isn't ok!");
      localObject1 = ContextHolder.a().b();
      if (localObject1 != null) {
        break label3126;
      }
      PbocLog.d(a, "context obj isn't ok!");
      localObject1 = ContextHolder.a().b();
      if (localObject1 != null) {
        break label3238;
      }
      PbocLog.d(a, "context obj isn't ok!");
      o();
      return;
      try
      {
        localObject4 = (TelephonyManager)((Context)localObject4).getSystemService("phone");
        if (localObject4 != null) {
          break;
        }
        Log.e(a, "TelephonyManager is null, please check the reason, maybe no privilege!");
      }
      catch (Exception localException1)
      {
        Log.e(a, "Get TelephonyManager Failed! Msg:" + localException1.getMessage());
      }
    }
    this.c = Build.MANUFACTURER;
    if (this.c == null)
    {
      PbocLog.d(a, "Get Terminal brand failed");
      this.c = "";
    }
    this.d = Build.MODEL;
    if (this.d == null)
    {
      PbocLog.d(a, "Get Terminal model failed");
      this.d = "";
    }
    this.e = Build.SERIAL;
    if (this.e == null)
    {
      PbocLog.d(a, "Get Terminal serial failed");
      this.e = "";
    }
    this.f = ((TelephonyManager)localObject4).getDeviceId();
    if (this.f == null)
    {
      PbocLog.d(a, "Get Terminal imei failed");
      this.f = "";
    }
    this.g = ((TelephonyManager)localObject4).getDeviceSoftwareVersion();
    if (this.g == null)
    {
      PbocLog.d(a, "Get Terminal Software Version failed");
      this.g = "";
    }
    this.h = Build.DISPLAY;
    if (this.h == null)
    {
      PbocLog.d(a, "Get Terminal Rom Version failed");
      this.h = "";
    }
    this.j = Build.VERSION.RELEASE;
    if (this.j == null)
    {
      PbocLog.d(a, "Get OS Version failed");
      this.j = "";
    }
    this.k = String.valueOf(Build.VERSION.SDK_INT);
    if (this.k == null)
    {
      PbocLog.d(a, "Get OS API Code failed");
      this.k = "";
    }
    this.l = Build.USER;
    if (this.l == null)
    {
      PbocLog.d(a, "Get OS User failed");
      this.l = "";
    }
    Object localObject5 = new StringBuffer();
    ((StringBuffer)localObject5).append("BOARD:" + Build.BOARD);
    ((StringBuffer)localObject5).append("\nBOOTLOADER:" + Build.BOOTLOADER);
    ((StringBuffer)localObject5).append("\nBRAND:" + Build.BRAND);
    ((StringBuffer)localObject5).append("\nCPU_ABI:" + Build.CPU_ABI);
    ((StringBuffer)localObject5).append("\nCPU_ABI2:" + Build.CPU_ABI2);
    ((StringBuffer)localObject5).append("\nDEVICE:" + Build.DEVICE);
    ((StringBuffer)localObject5).append("\nDISPLAY:" + Build.DISPLAY);
    ((StringBuffer)localObject5).append("\nFINGERPRINT:" + Build.FINGERPRINT);
    ((StringBuffer)localObject5).append("\nHARDWARE:" + Build.HARDWARE);
    ((StringBuffer)localObject5).append("\nHOST:" + Build.HOST);
    ((StringBuffer)localObject5).append("\nID:" + Build.ID);
    ((StringBuffer)localObject5).append("\nMANUFACTURER:" + Build.MANUFACTURER);
    ((StringBuffer)localObject5).append("\nMODEL:" + Build.MODEL);
    ((StringBuffer)localObject5).append("\nPRODUCT:" + Build.PRODUCT);
    ((StringBuffer)localObject5).append("\nRADIO:" + Build.getRadioVersion());
    ((StringBuffer)localObject5).append("\nSERIAL:" + Build.SERIAL);
    ((StringBuffer)localObject5).append("\nTAGS:" + Build.TAGS);
    ((StringBuffer)localObject5).append("\nTIME:" + Build.TIME);
    ((StringBuffer)localObject5).append("\nTYPE:" + Build.TYPE);
    ((StringBuffer)localObject5).append("\nUNKNOWN:unknown");
    ((StringBuffer)localObject5).append("\nUSER:" + Build.USER);
    ((StringBuffer)localObject5).append("\nVERSION.CODENAME:" + Build.VERSION.CODENAME);
    ((StringBuffer)localObject5).append("\nVERSION.INCREMENTAL:" + Build.VERSION.INCREMENTAL);
    ((StringBuffer)localObject5).append("\nVERSION.RELEASE:" + Build.VERSION.RELEASE);
    ((StringBuffer)localObject5).append("\nVERSION.SDK_INT:" + Build.VERSION.SDK_INT);
    this.m = Build.HOST;
    if (this.m == null)
    {
      PbocLog.d(a, "Get OS Addition failed");
      this.m = "";
    }
    this.n = ((TelephonyManager)localObject4).getSubscriberId();
    if (this.n == null)
    {
      PbocLog.d(a, "Get SIM Imsi failed");
      this.n = "";
    }
    this.o = ((TelephonyManager)localObject4).getLine1Number();
    label1352:
    label1440:
    int i1;
    if ((this.o == null) || ("".equals(this.o)))
    {
      PbocLog.c(a, "Fail to get mobile phone number");
      this.o = "15015015015";
      this.p = ((TelephonyManager)localObject4).getNetworkOperatorName();
      if (this.p == null)
      {
        PbocLog.d(a, "Get SIM operator failed");
        this.p = "";
      }
      switch (((TelephonyManager)localObject4).getSimState())
      {
      default: 
        PbocLog.d(a, "Get SIM state Failed! Other value:");
        this.q = "ERROR";
        this.r = ((TelephonyManager)localObject4).getSimSerialNumber();
        if (this.r == null)
        {
          PbocLog.d(a, "Get SIM Iccid failed");
          this.r = "";
        }
        i1 = ((TelephonyManager)localObject4).getNetworkType();
        switch (i1)
        {
        default: 
          PbocLog.d(a, "Get Network type failed! Value:" + i1);
          this.s = "ERROR";
          label1586:
          if (((TelephonyManager)localObject4).isNetworkRoaming())
          {
            this.t = "Y";
            label1600:
            localException1.append("移动通信类型:");
            i1 = ((TelephonyManager)localObject4).getPhoneType();
            if (i1 != 1) {
              break label2251;
            }
            localException1.append("GSM");
            label1626:
            localException1.append("\n*************************\n");
            localException1.append("电信网络国别:");
            localObject5 = ((TelephonyManager)localObject4).getNetworkCountryIso();
            if ((!((String)localObject5).equals("")) && (localObject5 != null)) {
              break label2309;
            }
            localException1.append("null");
            label1670:
            localException1.append("\n*************************\n");
            localException1.append("电信公司代码:");
            localObject5 = ((TelephonyManager)localObject4).getNetworkOperator();
            if ((!((String)localObject5).equals("")) && (localObject5 != null)) {
              break label2319;
            }
            localException1.append("null");
            label1714:
            localException1.append("\n*************************\n");
            localException1.append("SIM卡国别:");
            localObject4 = ((TelephonyManager)localObject4).getSimCountryIso();
            if ((!((String)localObject4).equals("")) && (localObject4 != null)) {
              break label2329;
            }
            localException1.append("null");
          }
          break;
        }
        break;
      }
    }
    for (;;)
    {
      localException1.append("\n*************************\n");
      break;
      PbocLog.b(a, "Get mobile phone number successfully! Value: " + this.o);
      i1 = this.o.length();
      if (i1 <= 11) {
        break label1352;
      }
      this.o = this.o.substring(i1 - 11);
      break label1352;
      PbocLog.b(a, "Get SIM state: SIM_STATE_READY");
      this.q = "SIM_STATE_READY";
      break label1440;
      PbocLog.c(a, "Get SIM state: SIM_STATE_ABSENT");
      this.q = "SIM_STATE_ABSENT";
      break label1440;
      PbocLog.c(a, "Get SIM state: SIM_STATE_PIN_REQUIRED");
      this.q = "SIM_STATE_PIN_REQUIRED";
      break label1440;
      PbocLog.c(a, "Get SIM state: SIM_STATE_PUK_REQUIRED");
      this.q = "SIM_STATE_PUK_REQUIRED";
      break label1440;
      PbocLog.c(a, "Get SIM state: SIM_STATE_NETWORK_LOCKED");
      this.q = "SIM_STATE_NETWORK_LOCKED";
      break label1440;
      PbocLog.c(a, "Get SIM state: SIM_STATE_UNKNOWN");
      this.q = "SIM_STATE_ABSENT";
      break label1440;
      PbocLog.c(a, "Get Network type successfully! NETWORK_TYPE_UNKNOWN");
      this.s = "UNKNOWN";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_GPRS");
      this.s = "GPRS";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_EDGE");
      this.s = "EDGE";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_UMTS");
      this.s = "UMTS";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_CDMA");
      this.s = "CDMA";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_EVDO_0");
      this.s = "EVDO_0";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_EVDO_A");
      this.s = "EVDO_A";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_1xRTT");
      this.s = "1xRTT";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_HSDPA");
      this.s = "HSDPA";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_HSUPA");
      this.s = "HSUPA";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_HSPA");
      this.s = "HSPA";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_IDEN");
      this.s = "IDEN";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_EVDO_B");
      this.s = "EVDO_B";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_LTE");
      this.s = "LTE";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_EHRPD");
      this.s = "EHRPD";
      break label1586;
      PbocLog.b(a, "Get Network type successfully! NETWORK_TYPE_HSPAP");
      this.s = "HSPAP";
      break label1586;
      this.t = "N";
      break label1600;
      label2251:
      if (i1 == 2)
      {
        localException1.append("CDMA");
        break label1626;
      }
      if (i1 == 3)
      {
        localException1.append("SIP");
        break label1626;
      }
      if (i1 == 0)
      {
        localException1.append("NONE");
        break label1626;
      }
      localException1.append("null");
      break label1626;
      label2309:
      localException1.append((String)localObject5);
      break label1670;
      label2319:
      localException1.append((String)localObject5);
      break label1714;
      label2329:
      localException1.append((String)localObject4);
    }
    for (;;)
    {
      try
      {
        label2338:
        localObject4 = (WifiManager)localException1.getSystemService("wifi");
        if (localObject4 != null) {
          break label2803;
        }
        PbocLog.d(a, "Do not support Wifi!");
        this.u = "N";
        WifiInfo localWifiInfo = ((WifiManager)localObject4).getConnectionInfo();
        switch (((WifiManager)localObject4).getWifiState())
        {
        default: 
          PbocLog.d(a, "Get wifi state error!");
          this.v = "ERROR";
          this.w = localWifiInfo.getBSSID();
          if (this.w == null) {
            break label2917;
          }
          PbocLog.b(a, "Wifi router is:" + this.w);
          this.x = localWifiInfo.getMacAddress();
          if (this.x == null) {
            break label2952;
          }
          PbocLog.b(a, "The mac is:" + this.x);
          i1 = localWifiInfo.getIpAddress();
          if (i1 == 0) {
            break label2987;
          }
          this.y = ((i1 & 0xFF) + "." + (i1 >> 8 & 0xFF) + "." + (i1 >> 16 & 0xFF) + "." + (i1 >> 24 & 0xFF));
          PbocLog.b(a, "The IP Address is:" + this.y);
          this.z = localWifiInfo.getSSID();
          if (this.z != null) {
            break label3013;
          }
          PbocLog.d(a, "Get ssid wrong! Value:" + this.z);
          this.z = "";
          localObject4 = new StringBuffer();
          ((StringBuffer)localObject4).append("其它wifi信息获取：");
          ((StringBuffer)localObject4).append("\n*************************\n");
          ((StringBuffer)localObject4).append("\n Network Id = " + localWifiInfo.getNetworkId());
          ((StringBuffer)localObject4).append("\n Link Speed = " + localWifiInfo.getLinkSpeed());
          ((StringBuffer)localObject4).append("\n Rssi = " + localWifiInfo.getRssi());
          ((StringBuffer)localObject4).append("\n*************************\n");
        }
      }
      catch (Exception localException2)
      {
        PbocLog.d(a, "Get WifiManager Failed! Msg:" + localException2.getMessage());
      }
      break;
      label2803:
      PbocLog.b(a, "Support Wifi!");
      this.u = "Y";
      continue;
      PbocLog.b(a, "Wifi state is WIFI_STATE_ENABLED");
      this.v = "ENABLED";
      continue;
      PbocLog.b(a, "Wifi state is WIFI_STATE_DISABLED");
      this.v = "DISABLED";
      continue;
      PbocLog.b(a, "Wifi state is WIFI_STATE_ENABLING");
      this.v = "ENABLING";
      continue;
      PbocLog.b(a, "Wifi state is WIFI_STATE_DISABLING");
      this.v = "DISABLING";
      continue;
      PbocLog.c(a, "Wifi state is WIFI_STATE_UNKNOWN");
      this.v = "UNKNOWN";
      continue;
      label2917:
      PbocLog.d(a, "No Wifi router value:" + this.w);
      this.w = "";
      continue;
      label2952:
      PbocLog.d(a, "Get mac wrong! Value:" + this.x);
      this.x = "";
      continue;
      label2987:
      PbocLog.d(a, "No IP Address! Value:" + i1);
      continue;
      label3013:
      if (this.z.equalsIgnoreCase("<unknown ssid>")) {
        PbocLog.d(a, "No ssid! Value:" + this.z);
      } else if (this.z.equalsIgnoreCase("0x")) {
        PbocLog.d(a, "No ssid! Value:" + this.z);
      } else {
        PbocLog.b(a, "The ssid is:" + this.z);
      }
    }
    for (;;)
    {
      try
      {
        label3126:
        Object localObject2 = NfcAdapter.getDefaultAdapter(localException2);
        if (localObject2 != null) {
          break label3204;
        }
        this.C = "N";
        this.D = "";
        localObject2 = new StringBuffer();
        ((StringBuffer)localObject2).append("其它NFC信息获取：");
        ((StringBuffer)localObject2).append("\n*************************\n");
      }
      catch (Exception localException3)
      {
        PbocLog.d(a, "Get NfcAdapter Failed! Msg:" + localException3.getMessage());
      }
      break;
      label3204:
      this.C = "Y";
      if (localException3.isEnabled()) {
        this.D = "ON";
      } else {
        this.D = "OFF";
      }
    }
    for (;;)
    {
      try
      {
        label3238:
        Object localObject3 = (LocationManager)localException3.getSystemService("location");
        if (localObject3 != null) {
          break label3322;
        }
        this.A = "N";
        this.B = "";
        localObject3 = new StringBuffer();
        ((StringBuffer)localObject3).append("其它Location信息获取：");
        ((StringBuffer)localObject3).append("\n*************************\n");
      }
      catch (Exception localException4)
      {
        PbocLog.d(a, "Get LocationManager Failed! Msg:" + localException4.getMessage());
      }
      break;
      label3322:
      this.A = "Y";
      if (localException4.isProviderEnabled("gps")) {
        this.B = "ON";
      } else {
        this.B = "OFF";
      }
    }
  }
  
  public static DeviceInfo a()
  {
    if (b == null) {}
    try
    {
      if (b == null) {
        b = new DeviceInfo();
      }
      return b;
    }
    finally {}
  }
  
  private void o()
  {
    Object localObject1 = new StringBuffer();
    ((StringBuffer)localObject1).append("其它服务信息获取：");
    ((StringBuffer)localObject1).append("\n*************************\n");
    localObject1 = ContextHolder.a().b();
    if (localObject1 == null) {
      PbocLog.d(a, "context obj isn't ok!");
    }
    int i1;
    for (;;)
    {
      return;
      try
      {
        Object localObject2 = ((Context)localObject1).getPackageManager();
        if (localObject2 != null)
        {
          localObject2 = ((PackageManager)localObject2).getInstalledPackages(0);
          i1 = 0;
          if (i1 >= ((List)localObject2).size())
          {
            this.I = ((Context)localObject1).getPackageName();
            localObject2 = ((Context)localObject1).getPackageManager();
            try
            {
              this.J = ((PackageManager)localObject2).getPackageInfo(((Context)localObject1).getPackageName(), 0).versionName;
              return;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              PbocLog.d(a, localNameNotFoundException.getMessage());
              this.J = "";
              return;
            }
          }
          localPackageInfo = (PackageInfo)((List)localObject2).get(i1);
        }
      }
      catch (Exception localException)
      {
        PbocLog.d(a, "Get PackageManager Failed! Msg:" + localException.getMessage());
        return;
      }
    }
    PackageInfo localPackageInfo;
    if (this.E.equalsIgnoreCase(localPackageInfo.packageName)) {
      this.F = localPackageInfo.versionName;
    }
    for (;;)
    {
      i1 += 1;
      break;
      if (this.G.equalsIgnoreCase(localPackageInfo.packageName)) {
        this.H = localPackageInfo.versionName;
      }
    }
  }
  
  public final String b()
  {
    return this.c;
  }
  
  public final String c()
  {
    return this.d;
  }
  
  public final String d()
  {
    return this.e;
  }
  
  public final String e()
  {
    return this.f;
  }
  
  public final String f()
  {
    return this.h;
  }
  
  public final String g()
  {
    return this.i;
  }
  
  public final String h()
  {
    return this.j;
  }
  
  public final String i()
  {
    return this.m;
  }
  
  public final String j()
  {
    return this.n;
  }
  
  public final String k()
  {
    return this.o;
  }
  
  public final String l()
  {
    return this.p;
  }
  
  public final String m()
  {
    return this.I;
  }
  
  public final String n()
  {
    return this.J;
  }
}
