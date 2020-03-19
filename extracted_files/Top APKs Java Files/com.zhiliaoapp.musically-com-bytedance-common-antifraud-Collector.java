package com.bytedance.common.antifraud;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.bytedance.common.antifraud.functionlality.AntiEmulatorChecker;
import com.bytedance.common.antifraud.functionlality.AntiTamper;
import com.bytedance.common.antifraud.functionlality.AppInfo;
import com.bytedance.common.antifraud.functionlality.Cpu;
import com.bytedance.common.antifraud.functionlality.Emulator;
import com.bytedance.common.antifraud.functionlality.Gps;
import com.bytedance.common.antifraud.functionlality.Network;
import com.bytedance.common.antifraud.functionlality.Sensor;
import com.bytedance.common.antifraud.functionlality.Settings;
import com.bytedance.common.antifraud.functionlality.SystemProperties;
import com.bytedance.common.antifraud.functionlality.Telephony;
import com.bytedance.common.antifraud.model.RiskApp;
import com.bytedance.common.antifraud.model.RiskDir;
import com.bytedance.common.antifraud.util.Utils;
import com.bytedance.common.utility.Logger;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class Collector
{
  static final int AINFO_BIT = 2;
  private static final String F_ADID = "adid";
  private static final String F_ANTITAMPER_ALL = "ainfo";
  private static final String F_ANTITAMPER_BTMAC = "abtmac";
  private static final String F_ANTITAMPER_XPOSED = "axposed";
  private static final String F_APP_CHANNEL = "apputm";
  private static final String F_APP_DISPLAY_NAME = "name";
  private static final String F_APP_LIST = "apps";
  private static final String F_APP_NAME = "appname";
  private static final String F_APP_PROCESS_NAME = "proc";
  private static final String F_APP_VERSION = "appver";
  private static final String F_BAND = "band";
  private static final String F_BLUETOOTH_MAC = "btmac";
  private static final String F_BOOT_TIME = "boot";
  private static final String F_BRAND = "brand";
  private static final String F_BRIGHTNESS = "brightness";
  private static final String F_BSSID = "bssid";
  private static final String F_BUILD_INFO = "sys";
  private static final String F_CELL = "cell";
  private static final String F_CLIENT_STATUS = "client_status";
  private static final String F_CPU_COUNT = "cpuCount";
  private static final String F_CPU_FREQUENCY = "cpuFreq";
  private static final String F_CPU_MODEL = "cpuModel";
  private static final String F_CPU_VENDOR = "cpuVendor";
  private static final String F_EMU = "emu";
  private static final String F_GOOGLE_AID = "google_aid";
  private static final String F_GPS = "gps";
  private static final String F_ICCID = "iccid";
  private static final String F_IMEI = "imei";
  private static final String F_IMEI1 = "imei1";
  private static final String F_IMEI2 = "imei2";
  private static final String F_IMSI = "imsi";
  private static final String F_MAC = "mac";
  private static final String F_MODEL = "model";
  private static final String F_NETWORK_LIST = "net";
  private static final String F_NET_OPERATOR = "operator";
  private static final String F_NET_TYPE = "network";
  private static final String F_ORT = "orientation";
  private static final String F_OS = "os";
  private static final String F_OS_VERSION = "osver";
  private static final String F_PRIVACY = "privacy";
  private static final String F_PROXY_IP = "proxy";
  private static final String F_PSUEDO_UNIQUE_ID = "psuedo_unique_id";
  private static final String F_RISK_APPS = "riskapp";
  private static final String F_RISK_DIRS = "riskdir";
  private static final String F_RTYPE = "rtype";
  private static final String F_SCREEN = "screen";
  private static final String F_SENSOR_LIST = "sensor";
  private static final String F_SIM_STATUES = "sim";
  private static final String F_SN = "sn";
  private static final String F_SSID = "ssid";
  private static final String F_SYSTEM_PROPERTIES = "props";
  private static final String F_SYS_APP_CNT = "syscnt";
  private static final String F_TARGET_SDK = "target_sdk";
  private static final String F_TIME = "t";
  private static final String F_TOTAL_MEMORY = "mem";
  private static final String F_USER_AGENT = "ua";
  private static final String F_USR_APP_CNT = "usrcnt";
  private static final String F_WHITE_APPS = "whiteapp";
  private static final String F_WIFI_IP = "wifiip";
  private static final String F_WIFI_LIST = "aps";
  static final int MD5_BIT = 1;
  private JSONObject mCollectedData;
  private String mGoogleAid;
  private final Object sLock = new Object();
  
  public Collector() {}
  
  private Map<String, Object> getRiskApps(Context paramContext, Map<String, RiskApp> paramMap)
  {
    HashMap localHashMap1 = new HashMap();
    if (paramMap != null) {}
    do
    {
      try
      {
        if (paramMap.size() > 0) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
      }
      HashMap localHashMap2 = new HashMap();
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (paramMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramMap.next();
          String str = (String)localEntry.getKey();
          localHashMap2.put(((RiskApp)localEntry.getValue()).getPackageName(), str);
        }
        else
        {
          for (;;)
          {
            return localHashMap1;
            try
            {
              paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
              while (paramContext.hasNext())
              {
                paramMap = (PackageInfo)paramContext.next();
                if (localHashMap2.containsKey(paramMap.packageName)) {
                  localHashMap1.put(localHashMap2.get(paramMap.packageName), Integer.valueOf(1));
                }
              }
            }
            catch (Exception paramContext)
            {
              Logger.e("AntiFraudManager", "Get app info error", paramContext);
            }
          }
        }
      }
      return localHashMap1;
    } while (paramContext != null);
    return localHashMap1;
  }
  
  private Map<String, Object> getRiskDirs(Map<String, RiskDir> paramMap)
  {
    HashMap localHashMap = new HashMap();
    if (paramMap != null) {
      for (;;)
      {
        Object localObject;
        try
        {
          if (paramMap.size() == 0) {
            break;
          }
          paramMap = paramMap.entrySet().iterator();
          if (paramMap.hasNext())
          {
            localObject = (Map.Entry)paramMap.next();
            try
            {
              String str = (String)((Map.Entry)localObject).getKey();
              localObject = (RiskDir)((Map.Entry)localObject).getValue();
              if (((RiskDir)localObject).getType() != 0) {
                break label128;
              }
              if (!Utils.isSdcardFileExist(((RiskDir)localObject).getDir())) {
                continue;
              }
              localHashMap.put(str, Integer.valueOf(1));
            }
            catch (Exception localException)
            {
              ThrowableExtension.printStackTrace(localException);
            }
            continue;
          }
          return localHashMap;
        }
        catch (Exception paramMap)
        {
          ThrowableExtension.printStackTrace(paramMap);
        }
        label128:
        if ((1 == ((RiskDir)localObject).getType()) && (Utils.isAbsoluteFileExist(((RiskDir)localObject).getDir()))) {
          localHashMap.put(localException, Integer.valueOf(1));
        }
      }
    }
    return localHashMap;
  }
  
  public JSONObject collect(Context paramContext, IAntiFraudConfig paramIAntiFraudConfig, int paramInt)
  {
    for (;;)
    {
      HashMap localHashMap;
      Object localObject3;
      synchronized (this.sLock)
      {
        localObject1 = this.mCollectedData;
        if (localObject1 != null) {
          try
          {
            paramContext = new JSONObject(this.mCollectedData.toString());
            return paramContext;
          }
          catch (JSONException paramContext)
          {
            ThrowableExtension.printStackTrace(paramContext);
            paramContext = null;
            continue;
          }
        }
        localHashMap = new HashMap();
        if ((paramInt & 0x1) == 1)
        {
          localHashMap.put("sn", Utils.md5(SystemProperties.getInstance().getSystemProp("ro.serialno")));
          localHashMap.put("rtype", "all");
          if ((paramInt & 0x1) != 1) {
            break label1437;
          }
          localObject1 = "md5";
          localHashMap.put("privacy", localObject1);
          localHashMap.put("os", "android");
          localHashMap.put("apputm", paramIAntiFraudConfig.getChannel());
          localHashMap.put("t", Long.valueOf(System.currentTimeMillis()));
          localHashMap.put("osver", Build.VERSION.RELEASE);
          localHashMap.put("emu", Emulator.getInstance(paramContext).detect());
          localHashMap.put("target_sdk", Integer.valueOf(AppInfo.getInstance(paramContext).getSelfTargetSdkVer()));
          localHashMap.put("abtmac", AntiTamper.getInstance(paramContext).getBtAddressViaReflection());
          localHashMap.put("axposed", "" + AntiTamper.getInstance(paramContext).checkXposed());
          AntiTamper.getInstance(paramContext).putAntiTamperAll(localHashMap, "ainfo", true);
          localObject3 = Network.getInstance(paramContext).getAllNetwork();
          if (localObject3 == null) {
            break label1303;
          }
          if ((paramInt & 0x1) != 1) {
            break label1288;
          }
          localObject1 = new ArrayList(((List)localObject3).size());
          localObject3 = ((List)localObject3).iterator();
          if (!((Iterator)localObject3).hasNext()) {
            break label393;
          }
          ((List)localObject1).add(Utils.md5((String)((Iterator)localObject3).next()));
        }
      }
      localHashMap.put("sn", SystemProperties.getInstance().getSystemProp("ro.serialno"));
      continue;
      label393:
      localHashMap.put("net", localObject1);
      for (;;)
      {
        localObject1 = SystemProperties.getInstance().getSystemProps();
        if (localObject1 != null)
        {
          if ((paramInt & 0x1) == 1)
          {
            localObject3 = (String)((Map)localObject1).get("ro.serialno");
            if (localObject3 != null) {
              ((Map)localObject1).put("ro.serialno", Utils.md5((String)localObject3));
            }
          }
          localHashMap.put("props", localObject1);
        }
        if ((paramInt & 0x1) == 1)
        {
          localHashMap.put("bssid", Utils.md5(Network.getInstance(paramContext).getBSSID()));
          label500:
          if ((paramInt & 0x1) != 1) {
            break label1339;
          }
          localHashMap.put("adid", Utils.md5(Settings.getInstance(paramContext).getAndroidId()));
          label527:
          if ((paramInt & 0x1) != 1) {
            break label1359;
          }
          localHashMap.put("btmac", Utils.md5(Network.getInstance(paramContext).getBluetoothAddress()));
          label554:
          if ((paramInt & 0x1) != 1) {
            break label1379;
          }
          localHashMap.put("imsi", Utils.md5(Telephony.getInstance(paramContext).getImsi()));
          label581:
          if ((paramInt & 0x1) != 1) {
            break label1399;
          }
          localHashMap.put("mac", Utils.md5(Network.getInstance(paramContext).getWifiMac()));
          label608:
          localHashMap.put("band", android.os.Build.getRadioVersion());
          localHashMap.put("ssid", Network.getInstance(paramContext).getSSID());
          localHashMap.put("wifiip", Network.getInstance(paramContext).getWifiIp());
          localHashMap.put("cpuCount", Integer.valueOf(Cpu.getInstance(paramContext).getNumberOfCPUCores()));
          localHashMap.put("cpuModel", Cpu.getInstance(paramContext).getCpuModel());
          localHashMap.put("cpuFreq", Integer.valueOf(Cpu.getInstance(paramContext).getCPUMaxFreqKHz()));
          localHashMap.put("cpuVendor", Cpu.getInstance(paramContext).getCpuVendor());
          localHashMap.put("model", android.os.Build.MODEL);
          localHashMap.put("screen", Settings.getInstance(paramContext).getScreenRes());
          localHashMap.put("brightness", Integer.valueOf(Settings.getInstance(paramContext).getBrightness()));
          localHashMap.put("boot", Long.valueOf(Settings.getInstance(paramContext).getBootTime()));
          localHashMap.put("appver", AppInfo.getInstance(paramContext).getAppVersion());
          localHashMap.put("appname", AppInfo.getInstance(paramContext).getAppName());
          localHashMap.put("name", AppInfo.getInstance(paramContext).getDisplayAppName());
          localHashMap.put("proc", Settings.getInstance(paramContext).getProcessName());
          localHashMap.put("brand", android.os.Build.BRAND);
          localHashMap.put("network", Network.getInstance(paramContext).getNetworkType());
          localHashMap.put("operator", Telephony.getInstance(paramContext).getNetworkOperator());
          localHashMap.put("proxy", SystemProperties.getInstance().getSystemProp("http.proxy"));
          localHashMap.put("ua", SystemProperties.getUserAgent(paramContext));
          localHashMap.put("psuedo_unique_id", com.bytedance.common.antifraud.functionlality.Build.getInstance().getPseudoUniqueID());
          localHashMap.put("sys", com.bytedance.common.antifraud.functionlality.Build.getInstance().getBuildInfo());
          localHashMap.put("sensor", Sensor.getInstance(paramContext).getSensorList());
          localHashMap.put("mem", Long.valueOf(Cpu.getInstance(paramContext).getTotalMemory()));
          localHashMap.put("sim", Integer.valueOf(Telephony.getInstance(paramContext).getSimStatus()));
          localHashMap.put("gps", Gps.getInstance(paramContext).getGpsInfo());
          localHashMap.put("iccid", Telephony.getInstance(paramContext).getIccId());
          localHashMap.put("cell", Telephony.getInstance(paramContext).getCellInfo());
          localHashMap.put("aps", Network.getInstance(paramContext).getWifiList());
          localHashMap.put("google_aid", this.mGoogleAid);
          localHashMap.put("apps", new ArrayList());
          localHashMap.put("whiteapp", new HashMap());
          localHashMap.put("usrcnt", Integer.valueOf(0));
          localHashMap.put("syscnt", Integer.valueOf(0));
          localHashMap.put("riskapp", new HashMap());
          localHashMap.put("riskdir", getRiskDirs(paramIAntiFraudConfig.getRiskDirs()));
          localHashMap.put("client_status", Network.getInstance(paramContext).getSupplicantState());
          this.mCollectedData = Utils.mapToJson(localHashMap);
        }
        try
        {
          paramIAntiFraudConfig = new JSONObject();
          AntiEmulatorChecker.check(paramContext, paramIAntiFraudConfig);
          this.mCollectedData.put("anti_emulator", paramIAntiFraudConfig);
        }
        catch (Exception paramContext)
        {
          try
          {
            for (;;)
            {
              paramContext = new JSONObject(this.mCollectedData.toString());
              return paramContext;
              label1288:
              localHashMap.put("net", localObject3);
              break;
              label1303:
              localHashMap.put("net", "null");
              break;
              localHashMap.put("bssid", Network.getInstance(paramContext).getBSSID());
              break label500;
              label1339:
              localHashMap.put("adid", Settings.getInstance(paramContext).getAndroidId());
              break label527;
              label1359:
              localHashMap.put("btmac", Network.getInstance(paramContext).getBluetoothAddress());
              break label554;
              label1379:
              localHashMap.put("imsi", Telephony.getInstance(paramContext).getImsi());
              break label581;
              label1399:
              localHashMap.put("mac", Network.getInstance(paramContext).getWifiMac());
              break label608;
              paramContext = paramContext;
              ThrowableExtension.printStackTrace(paramContext);
            }
          }
          catch (JSONException paramContext)
          {
            for (;;)
            {
              ThrowableExtension.printStackTrace(paramContext);
              paramContext = null;
            }
          }
        }
      }
      label1437:
      Object localObject1 = "none";
    }
  }
  
  public void setGoogleAid(String paramString)
  {
    this.mGoogleAid = paramString;
  }
}
