package com.exelate.sdk.data;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.exelate.sdk.ClientData;
import com.exelate.sdk.configuration.ConfigurationManager;
import com.exelate.sdk.configuration.DataComponent;
import com.exelate.sdk.exception.SDKErrorEnum;
import com.exelate.sdk.exception.SDKException;
import com.exelate.sdk.exception.SDKExceptionHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataCollector
{
  private ConfigurationManager configurationManager;
  private ClientData data;
  private SDKExceptionHandler sdkExceptionHandler;
  
  public DataCollector(ConfigurationManager paramConfigurationManager, ClientData paramClientData, SDKExceptionHandler paramSDKExceptionHandler)
  {
    this.configurationManager = paramConfigurationManager;
    this.data = paramClientData;
    this.sdkExceptionHandler = paramSDKExceptionHandler;
  }
  
  private void fetchAccounts(DeviceInfo paramDeviceInfo)
  {
    try
    {
      paramDeviceInfo.getAccounts().clear();
      if (isAccountDataEnabled())
      {
        Account[] arrayOfAccount = AccountManager.get(this.data.getContext()).getAccounts();
        int j = arrayOfAccount.length;
        int i = 0;
        while (i < j)
        {
          Account localAccount = arrayOfAccount[i];
          paramDeviceInfo.addAccount(localAccount.type, localAccount.name);
          i += 1;
        }
      }
      return;
    }
    catch (Exception paramDeviceInfo)
    {
      this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.ACCOUNTS_INFO_HARVEST_ERROR, null, paramDeviceInfo));
    }
  }
  
  private void fetchInstalledApps(DeviceInfo paramDeviceInfo)
  {
    try
    {
      paramDeviceInfo.getInstalledAppsList().clear();
      Object localObject = this.data.getContext().getPackageManager();
      int i = 0;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      while (i < ((List)localObject).size())
      {
        PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
        if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (localPackageInfo.versionName != null)) {
          paramDeviceInfo.addInstalledApp(localPackageInfo.applicationInfo.loadLabel(this.data.getContext().getPackageManager()).toString());
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramDeviceInfo)
    {
      this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.INSTALLED_APPS_HARVEST_ERROR, null, paramDeviceInfo));
    }
  }
  
  private void fetchLocationInfo(DeviceInfo paramDeviceInfo)
  {
    LocationInfo localLocationInfo = new LocationInfo();
    for (;;)
    {
      try
      {
        if (isLocationDataEnabled())
        {
          LocationManager localLocationManager = (LocationManager)this.data.getContext().getSystemService("location");
          String str = localLocationManager.getBestProvider(new Criteria(), false);
          localObject = str;
          if (str == null)
          {
            List localList = localLocationManager.getAllProviders();
            localObject = str;
            if (localList != null)
            {
              localObject = str;
              if (localList.size() > 0) {
                localObject = (String)localList.get(0);
              }
            }
          }
          if (localObject == null) {
            break label164;
          }
          localObject = localLocationManager.getLastKnownLocation((String)localObject);
          if (localObject != null)
          {
            localLocationInfo.setLoactionTimestamp(((Location)localObject).getTime());
            localLocationInfo.setLatitude(((Location)localObject).getLatitude());
            localLocationInfo.setLongitude(((Location)localObject).getLongitude());
          }
          paramDeviceInfo.setLocationInfo(localLocationInfo);
          return;
        }
      }
      catch (Exception paramDeviceInfo)
      {
        this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.LOCATION_INFO_HARVEST_ERROR, null, paramDeviceInfo));
      }
      return;
      label164:
      Object localObject = null;
    }
  }
  
  private void fetchNetworkInfo(DeviceInfo paramDeviceInfo)
  {
    for (;;)
    {
      try
      {
        if (isNetworkDataEnabled())
        {
          localObject = ((WifiManager)this.data.getContext().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
          if (!SupplicantState.COMPLETED.equals(((WifiInfo)localObject).getSupplicantState())) {
            break label97;
          }
          String str = ((WifiInfo)localObject).getSSID();
          if ("0X".equals(str)) {
            break label97;
          }
          localObject = str;
          if ("0x".equals(str)) {
            break label97;
          }
          paramDeviceInfo.setNetworkName((String)localObject);
          return;
        }
      }
      catch (Exception paramDeviceInfo)
      {
        this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.NETWORK_INFO_HARVEST_ERROR, null, paramDeviceInfo));
      }
      return;
      label97:
      Object localObject = null;
    }
  }
  
  private void fetchPhoneInfo(DeviceInfo paramDeviceInfo)
  {
    try
    {
      if (isPhoneDataEnabled())
      {
        Object localObject = (TelephonyManager)this.data.getContext().getSystemService("phone");
        paramDeviceInfo.setPhoneType(((TelephonyManager)localObject).getPhoneType());
        String str = ((TelephonyManager)localObject).getSimCountryIso();
        localObject = str;
        if (str != null) {
          localObject = str.toUpperCase();
        }
        paramDeviceInfo.setCountryCode((String)localObject);
        return;
      }
    }
    catch (Exception paramDeviceInfo)
    {
      this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.PHONE_INFO_HARVEST_ERROR, null, paramDeviceInfo));
    }
  }
  
  private DeviceInfo generateDeviceInfo()
  {
    DeviceInfo localDeviceInfo = new DeviceInfo();
    fetchPhoneInfo(localDeviceInfo);
    fetchNetworkInfo(localDeviceInfo);
    fetchLocationInfo(localDeviceInfo);
    fetchAccounts(localDeviceInfo);
    fetchInstalledApps(localDeviceInfo);
    return localDeviceInfo;
  }
  
  private DeviceIdentifiersInfo getIdentifiersData()
  {
    DeviceIdentifiersInfo localDeviceIdentifiersInfo = new DeviceIdentifiersInfo();
    try
    {
      if (this.data.getAdIdHandler().adIdServiceAvailable() == 0)
      {
        if (this.data.getAdIdHandler().isLimitAdTrackingEnabled()) {
          localDeviceIdentifiersInfo.setLimitedTrackingEnabled(true);
        } else {
          localDeviceIdentifiersInfo.setLimitedTrackingEnabled(false);
        }
        localDeviceIdentifiersInfo.setAdvertiserId(this.data.getAdIdHandler().getId());
        if (this.configurationManager.isDeliverAndroidId()) {
          try
          {
            localDeviceIdentifiersInfo.addAdditionalId(Settings.Secure.getString(this.data.getContext().getContentResolver(), "android_id"));
            return localDeviceIdentifiersInfo;
          }
          catch (Exception localException)
          {
            this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.DEVICE_ID_HARVEST_ERROR, null, localException));
          }
        }
        return localDeviceIdentifiersInfo;
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.ADVERTISER_ID_HARVEST_ERROR, null, localThrowable));
    }
    return null;
  }
  
  private String getOS(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration();
    try
    {
      int i = paramContext.getClass().getDeclaredField("screenLayout").getInt(paramContext);
      i &= 0xF;
      if (i == 1) {
        return "Android_smartPhone";
      }
      if (i == 2) {
        return "Android_smartPhone";
      }
      if (i == 3) {
        return "Android_tablet";
      }
      if (i == 4) {
        return "Android_tablet";
      }
      return "Android_other";
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "Android_smartPhone";
  }
  
  public ClientInfo harvest()
  {
    ClientInfo localClientInfo = new ClientInfo(this.configurationManager);
    try
    {
      localClientInfo.setApiKey(this.data.getApiKey());
      localClientInfo.setApplicationInfo(this.data.getAppData());
      localClientInfo.getApplicationInfo().put("APP", "1");
      localClientInfo.getApplicationInfo().put("xl8mobilesdk", "true");
      DeviceIdentifiersInfo localDeviceIdentifiersInfo = getIdentifiersData();
      if (localDeviceIdentifiersInfo != null)
      {
        if (localDeviceIdentifiersInfo.getAdvertiserId() == null) {
          return null;
        }
        localClientInfo.setIdentifiersData(localDeviceIdentifiersInfo);
        if (localClientInfo.getIdentifiersData().isLimitedTrackingEnabled()) {
          return localClientInfo;
        }
        localClientInfo.setApiKey(this.data.getApiKey());
        if (this.data.getDeviceInfo() != null) {
          localClientInfo.setDeviceInfo(this.data.getDeviceInfo());
        } else {
          localClientInfo.setDeviceInfo(generateDeviceInfo());
        }
        localClientInfo.setOs(getOS(this.data.getContext()));
        return localClientInfo;
      }
      return null;
    }
    catch (Exception localException)
    {
      this.sdkExceptionHandler.addError(new SDKException(SDKErrorEnum.GENERAL_DATA_COLLECTOR_ERROR, null, localException));
    }
    return localClientInfo;
  }
  
  public boolean isAccountDataEnabled()
  {
    boolean bool3 = this.configurationManager.isDataComponentPermitted(DataComponent.account);
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bool3)
    {
      bool1 = bool2;
      if (this.data.getContext().checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isLocationDataEnabled()
  {
    boolean bool3 = this.configurationManager.isDataComponentPermitted(DataComponent.location);
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bool3)
    {
      bool1 = bool2;
      if (this.data.getContext().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isNetworkDataEnabled()
  {
    boolean bool3 = this.configurationManager.isDataComponentPermitted(DataComponent.network);
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bool3)
    {
      bool1 = bool2;
      if (this.data.getContext().checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isPhoneDataEnabled()
  {
    boolean bool3 = this.configurationManager.isDataComponentPermitted(DataComponent.phone);
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bool3)
    {
      bool1 = bool2;
      if (this.data.getContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
}
