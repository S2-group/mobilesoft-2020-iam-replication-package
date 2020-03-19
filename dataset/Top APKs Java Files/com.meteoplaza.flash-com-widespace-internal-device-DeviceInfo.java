package com.widespace.internal.device;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Rect;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.widespace.adframework.device.ConnectionType;
import com.widespace.internal.capability.CapabilityManager;
import com.widespace.internal.capability.PermissionResultCallBlock;
import com.widespace.internal.util.IOUtils;
import com.widespace.internal.util.OpenUDIDUtil;
import com.widespace.internal.util.StringUtils;
import com.widespace.internal.util.TelephonyUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DeviceInfo
{
  private static final int CONNECTION_STRING_LENTGH = 3;
  public static final String WIDESPACE_SDK_VERSION = "4.8.7";
  private String availableCellInfo;
  private String availableWifiInfo;
  private String deviceAppId;
  private String deviceAppVersion;
  private String deviceCountryCode;
  private String deviceHeight;
  private Context deviceInfoContext = null;
  private String deviceLocale;
  private String deviceManufacturer;
  private String deviceModel;
  private String deviceOSPlatfrom;
  private String deviceOSVersion;
  private String deviceOpenUUID;
  private String deviceOperator;
  private int deviceOrientation = 0;
  private String devicePlatformId;
  private String deviceUUID;
  private String deviceUserAgent;
  private String deviceVersion;
  private String deviceWidth;
  private boolean isLocationPermissionRequested;
  private boolean isRegulatedModeEnabled = false;
  private WifiManager wifiManager;
  
  public DeviceInfo(Context paramContext)
  {
    this.deviceInfoContext = paramContext;
  }
  
  private int getTitleBarHeight()
  {
    int i;
    if ((this.deviceInfoContext instanceof Activity)) {
      i = ((Activity)this.deviceInfoContext).getWindow().findViewById(16908290).getTop() - getStatusBarHeight();
    }
    TypedValue localTypedValue;
    do
    {
      return i;
      localTypedValue = new TypedValue();
      i = 0;
    } while (!this.deviceInfoContext.getTheme().resolveAttribute(16843499, localTypedValue, true));
    return TypedValue.complexToDimensionPixelSize(localTypedValue.data, this.deviceInfoContext.getResources().getDisplayMetrics());
  }
  
  private void initDeviceOperatorAndCountryCode()
  {
    Object localObject = (TelephonyManager)this.deviceInfoContext.getSystemService("phone");
    if (localObject != null)
    {
      String str = ((TelephonyManager)localObject).getSimOperator();
      if ((str == null) || (str.length() <= 3)) {
        break label54;
      }
      this.deviceCountryCode = str.substring(0, 3);
      this.deviceOperator = str.substring(3);
    }
    label54:
    do
    {
      return;
      localObject = ((TelephonyManager)localObject).getNetworkOperator();
    } while ((localObject == null) || (((String)localObject).length() <= 3));
    this.deviceCountryCode = ((String)localObject).substring(0, 3);
    this.deviceOperator = ((String)localObject).substring(3);
  }
  
  private void initDeviceWidthAndHeight()
  {
    Object localObject = (WindowManager)this.deviceInfoContext.getSystemService("window");
    if (localObject != null)
    {
      localObject = ((WindowManager)localObject).getDefaultDisplay();
      this.deviceOrientation = this.deviceInfoContext.getResources().getConfiguration().orientation;
      this.deviceWidth = String.valueOf(((Display)localObject).getWidth());
      this.deviceHeight = String.valueOf(((Display)localObject).getHeight());
    }
  }
  
  private boolean isSystemPackage(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private void processWifiInfo()
  {
    if ((CapabilityManager.isWifiCapabilityPermitted(this.deviceInfoContext)) && (StringUtils.isBlank(this.availableWifiInfo)))
    {
      if (this.wifiManager == null) {
        this.wifiManager = ((WifiManager)this.deviceInfoContext.getSystemService("wifi"));
      }
      Object localObject = this.wifiManager.getScanResults();
      String str = this.wifiManager.getConnectionInfo().getBSSID();
      if (localObject != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = ((List)localObject).iterator();
        while (localIterator.hasNext())
        {
          ScanResult localScanResult = (ScanResult)localIterator.next();
          if (!StringUtils.isBlank(localScanResult.BSSID))
          {
            if (localStringBuilder.length() > 1)
            {
              localObject = ",";
              label126:
              localStringBuilder.append((String)localObject);
              localStringBuilder.append("bssid:" + localScanResult.BSSID.replace(":", "").replace("_", "").replace(".", "") + ";");
              if (StringUtils.isBlank(localScanResult.SSID)) {
                break label337;
              }
            }
            label337:
            for (localObject = "ssid:" + localScanResult.SSID.replace(":", "").replace(";", "").replace(",", "") + ";";; localObject = "")
            {
              localStringBuilder.append((String)localObject);
              localStringBuilder.append("lvl:" + localScanResult.level + ";");
              if ((str == null) || (!str.equals(localScanResult.BSSID))) {
                break label344;
              }
              localStringBuilder.append("act:Y");
              break;
              localObject = "";
              break label126;
            }
            label344:
            localStringBuilder.append("act:N");
          }
        }
        if (localStringBuilder.length() > 1) {
          this.availableWifiInfo = localStringBuilder.toString();
        }
      }
    }
  }
  
  public void clearAvailableCellInfo()
  {
    this.availableCellInfo = "";
  }
  
  public void clearAvailableWifiInfo()
  {
    this.availableWifiInfo = "";
  }
  
  public String getAdvertisingId()
  {
    String str = null;
    int i = 0;
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.deviceInfoContext);
      if (i == 0) {
        str = localInfo.getId();
      }
      return str;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        i = 1;
        Object localObject1 = null;
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        i = 1;
        Object localObject2 = null;
      }
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      for (;;)
      {
        i = 1;
        Object localObject3 = null;
      }
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      for (;;)
      {
        i = 1;
        Object localObject4 = null;
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public String getAvailableCellInfo()
  {
    StringBuilder localStringBuilder;
    int i;
    Object localObject3;
    Object localObject2;
    label402:
    label420:
    do
    {
      do
      {
        for (;;)
        {
          try
          {
            if (StringUtils.isBlank(this.availableCellInfo))
            {
              Object localObject1 = ((TelephonyManager)this.deviceInfoContext.getSystemService("phone")).getAllCellInfo();
              localStringBuilder = new StringBuilder();
              i = getDeviceCellId();
              if (localObject1 == null) {
                break label836;
              }
              Iterator localIterator = ((List)localObject1).iterator();
              if (!localIterator.hasNext()) {
                break label836;
              }
              localObject1 = (CellInfo)localIterator.next();
              if (!(localObject1 instanceof CellInfoGsm)) {
                break;
              }
              localObject3 = (CellInfoGsm)localObject1;
              localObject2 = ((CellInfoGsm)localObject3).getCellIdentity();
              if (((((CellIdentityGsm)localObject2).getMcc() <= 0) || (((CellIdentityGsm)localObject2).getMcc() >= Integer.MAX_VALUE)) && ((((CellIdentityGsm)localObject2).getMnc() <= 0) || (((CellIdentityGsm)localObject2).getMnc() >= Integer.MAX_VALUE))) {
                continue;
              }
              if (localStringBuilder.length() > 1)
              {
                localObject1 = ",";
                localStringBuilder.append((String)localObject1);
                localStringBuilder.append("mcc:" + String.valueOf(((CellIdentityGsm)localObject2).getMcc()) + ";");
                localStringBuilder.append("mnc:" + String.valueOf(((CellIdentityGsm)localObject2).getMnc()) + ";");
                localStringBuilder.append("lac:" + String.valueOf(((CellIdentityGsm)localObject2).getLac()) + ";");
                localStringBuilder.append("cid:" + String.valueOf(((CellIdentityGsm)localObject2).getCid()) + ";");
                localStringBuilder.append("lvl:" + String.valueOf(((CellInfoGsm)localObject3).getCellSignalStrength().getLevel()) + ";");
                localStringBuilder.append("type:" + getDeviceNetworkType() + ";");
                localObject3 = new StringBuilder().append("act:");
                if (i != ((CellIdentityGsm)localObject2).getCid()) {
                  break label855;
                }
                localObject1 = "Y";
                localStringBuilder.append((String)localObject1);
                continue;
              }
            }
            else
            {
              return this.availableCellInfo;
            }
          }
          catch (Exception localException) {}
          str = "";
        }
      } while ((Build.VERSION.SDK_INT <= 18) || (!(str instanceof CellInfoWcdma)));
      localObject3 = (CellInfoWcdma)str;
      localObject2 = ((CellInfoWcdma)localObject3).getCellIdentity();
    } while (((((CellIdentityWcdma)localObject2).getMcc() <= 0) || (((CellIdentityWcdma)localObject2).getMcc() >= Integer.MAX_VALUE)) && ((((CellIdentityWcdma)localObject2).getMnc() <= 0) || (((CellIdentityWcdma)localObject2).getMnc() >= Integer.MAX_VALUE)));
    if (localStringBuilder.length() > 1)
    {
      str = ",";
      label509:
      localStringBuilder.append(str);
      localStringBuilder.append("mcc:" + String.valueOf(((CellIdentityWcdma)localObject2).getMcc()) + ";");
      localStringBuilder.append("mnc:" + String.valueOf(((CellIdentityWcdma)localObject2).getMnc()) + ";");
      localStringBuilder.append("lac:" + String.valueOf(((CellIdentityWcdma)localObject2).getLac()) + ";");
      localStringBuilder.append("cid:" + String.valueOf(((CellIdentityWcdma)localObject2).getCid()) + ";");
      if (((CellIdentityWcdma)localObject2).getPsc() != Integer.MAX_VALUE) {
        localStringBuilder.append("psc:" + String.valueOf(((CellIdentityWcdma)localObject2).getPsc()) + ";");
      }
      localStringBuilder.append("lvl:" + String.valueOf(((CellInfoWcdma)localObject3).getCellSignalStrength().getLevel()) + ";");
      localStringBuilder.append("type:" + getDeviceNetworkType() + ";");
      localObject3 = new StringBuilder().append("act:");
      if (i != ((CellIdentityWcdma)localObject2).getCid()) {
        break label869;
      }
    }
    label836:
    label855:
    label869:
    for (String str = "Y";; str = "N")
    {
      localStringBuilder.append(str);
      break;
      if (localStringBuilder.length() <= 1) {
        break label420;
      }
      this.availableCellInfo = localStringBuilder.toString();
      break label420;
      str = "N";
      break label402;
      str = "";
      break label509;
    }
  }
  
  public String getAvailableWifiInfo()
  {
    if (Build.VERSION.SDK_INT >= 23) {
      if (CapabilityManager.isCoarseLocationCapabilityPermitted(this.deviceInfoContext, new PermissionResultCallBlock()
      {
        public void onPermissionRequested()
        {
          DeviceInfo.access$002(DeviceInfo.this, true);
        }
        
        public void perform(int paramAnonymousInt, String[] paramAnonymousArrayOfString, int[] paramAnonymousArrayOfInt)
        {
          DeviceInfo.access$002(DeviceInfo.this, false);
          if (paramAnonymousInt == 1)
          {
            paramAnonymousInt = paramAnonymousArrayOfInt[0];
            DeviceInfo.this.deviceInfoContext.getPackageManager();
            if (paramAnonymousInt == 0)
            {
              DeviceInfo.this.processWifiInfo();
              return;
            }
          }
          DeviceInfo.access$302(DeviceInfo.this, "");
        }
      }, true)) {
        processWifiInfo();
      }
    }
    for (;;)
    {
      return this.availableWifiInfo;
      if (!this.isLocationPermissionRequested)
      {
        this.availableCellInfo = "";
        continue;
        processWifiInfo();
      }
    }
  }
  
  public int getContentDisplayHeight()
  {
    return Integer.parseInt(getDeviceHeight()) - getStatusBarHeight() - getTitleBarHeight();
  }
  
  public int getContentDisplayWidth()
  {
    return Integer.parseInt(getDeviceWidth());
  }
  
  public String getDeviceAppId()
  {
    if (StringUtils.isBlank(this.deviceAppId)) {
      this.deviceAppId = this.deviceInfoContext.getPackageName();
    }
    return this.deviceAppId;
  }
  
  public String getDeviceAppVersion()
  {
    if (StringUtils.isBlank(this.deviceAppVersion)) {}
    try
    {
      this.deviceAppVersion = this.deviceInfoContext.getPackageManager().getPackageInfo(getDeviceAppId(), 0).versionName;
      return this.deviceAppVersion;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        this.deviceAppVersion = null;
      }
    }
  }
  
  public String getDeviceBSSID()
  {
    try
    {
      if (this.wifiManager == null) {
        this.wifiManager = ((WifiManager)this.deviceInfoContext.getSystemService("wifi"));
      }
      if (this.wifiManager != null)
      {
        String str = this.wifiManager.getConnectionInfo().getBSSID();
        return str;
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  public int getDeviceCellId()
  {
    try
    {
      int i = ((GsmCellLocation)((TelephonyManager)this.deviceInfoContext.getSystemService("phone")).getCellLocation()).getCid();
      return i;
    }
    catch (Exception localException) {}
    return -1;
  }
  
  public int getDeviceCellLac()
  {
    try
    {
      int i = ((GsmCellLocation)((TelephonyManager)this.deviceInfoContext.getSystemService("phone")).getCellLocation()).getLac();
      return i;
    }
    catch (Exception localException) {}
    return -1;
  }
  
  public int getDeviceCellPsc()
  {
    try
    {
      int i = ((GsmCellLocation)((TelephonyManager)this.deviceInfoContext.getSystemService("phone")).getCellLocation()).getPsc();
      return i;
    }
    catch (Exception localException) {}
    return -1;
  }
  
  public String getDeviceConnectionType()
  {
    return ConnectionType.getConnectionTypeName(this.deviceInfoContext);
  }
  
  public String getDeviceCountryCode()
  {
    if (StringUtils.isBlank(this.deviceCountryCode)) {
      initDeviceOperatorAndCountryCode();
    }
    return this.deviceCountryCode;
  }
  
  public String getDeviceHeight()
  {
    initDeviceWidthAndHeight();
    if (this.deviceInfoContext.getResources().getConfiguration().orientation == this.deviceOrientation) {
      return this.deviceHeight;
    }
    return this.deviceWidth;
  }
  
  public int getDeviceHeightInInteger()
  {
    return Integer.parseInt(getDeviceHeight());
  }
  
  public String getDeviceLocale()
  {
    if (StringUtils.isBlank(this.deviceLocale))
    {
      Locale localLocale = Locale.getDefault();
      if (localLocale == null) {
        break label68;
      }
      this.deviceLocale = (localLocale.getLanguage() + "-" + localLocale.getCountry());
    }
    label68:
    for (this.deviceLocale = this.deviceLocale.toLowerCase();; this.deviceLocale = "") {
      return this.deviceLocale;
    }
  }
  
  public String getDeviceManufacturer()
  {
    if (StringUtils.isBlank(this.deviceManufacturer)) {
      if (Build.MANUFACTURER == null) {
        break label30;
      }
    }
    label30:
    for (String str = Build.MANUFACTURER;; str = "")
    {
      this.deviceManufacturer = str;
      return this.deviceManufacturer;
    }
  }
  
  public String getDeviceModel()
  {
    if (StringUtils.isBlank(this.deviceModel)) {
      if (Build.MODEL == null) {
        break label30;
      }
    }
    label30:
    for (String str = Build.MODEL;; str = "")
    {
      this.deviceModel = str;
      return this.deviceModel;
    }
  }
  
  public String getDeviceNetworkType()
  {
    return TelephonyUtil.getTelephoneNetworkConnectionString(this.deviceInfoContext);
  }
  
  public String getDeviceOSPlatfrom()
  {
    if (StringUtils.isBlank(this.deviceOSPlatfrom)) {
      this.deviceOSPlatfrom = "Android";
    }
    return this.deviceOSPlatfrom;
  }
  
  public String getDeviceOpenUDIDEncryptedByShaOne()
  {
    return new OpenUDIDUtil(this.deviceInfoContext, true).getOpenUDIDInContext();
  }
  
  public String getDeviceOpenUUID()
  {
    if (StringUtils.isBlank(this.deviceOpenUUID)) {
      this.deviceOpenUUID = new OpenUDIDUtil(this.deviceInfoContext, this.isRegulatedModeEnabled).getOpenUDIDInContext();
    }
    return this.deviceOpenUUID;
  }
  
  public String getDeviceOperator()
  {
    if (StringUtils.isBlank(this.deviceOperator)) {
      initDeviceOperatorAndCountryCode();
    }
    return this.deviceOperator;
  }
  
  public String getDevicePlatformID()
  {
    if (StringUtils.isBlank(this.devicePlatformId))
    {
      String str = Settings.Secure.getString(this.deviceInfoContext.getContentResolver(), "android_id");
      if (str != null) {
        this.devicePlatformId = str.toLowerCase();
      }
    }
    return this.devicePlatformId;
  }
  
  public String getDeviceSSID()
  {
    try
    {
      if (this.wifiManager == null) {
        this.wifiManager = ((WifiManager)this.deviceInfoContext.getSystemService("wifi"));
      }
      if (this.wifiManager != null)
      {
        String str = this.wifiManager.getConnectionInfo().getSSID();
        return str;
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  public float getDeviceScalingFactorForDIP()
  {
    return this.deviceInfoContext.getResources().getDisplayMetrics().density;
  }
  
  public String getDeviceUUID()
  {
    if (StringUtils.isBlank(this.deviceUUID)) {}
    try
    {
      String str = ((TelephonyManager)this.deviceInfoContext.getSystemService("phone")).getDeviceId();
      if ((str != null) && (!str.substring(0, 3).equals("000"))) {
        this.deviceUUID = ("IMEI:" + str);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.deviceUUID = "";
      }
    }
    return this.deviceUUID;
  }
  
  public String getDeviceUserAgent()
  {
    if (StringUtils.isBlank(this.deviceUserAgent)) {
      this.deviceUserAgent = new WebView(this.deviceInfoContext).getSettings().getUserAgentString();
    }
    return this.deviceUserAgent;
  }
  
  public String getDeviceVersion()
  {
    if (StringUtils.isBlank(this.deviceVersion)) {
      if (Build.VERSION.RELEASE == null) {
        break label30;
      }
    }
    label30:
    for (String str = Build.VERSION.RELEASE;; str = "")
    {
      this.deviceVersion = str;
      return this.deviceVersion;
    }
  }
  
  public String getDeviceWidth()
  {
    initDeviceWidthAndHeight();
    if (this.deviceInfoContext.getResources().getConfiguration().orientation == this.deviceOrientation) {
      return this.deviceWidth;
    }
    return this.deviceHeight;
  }
  
  public int getDeviceWidthInInteger()
  {
    return Integer.parseInt(getDeviceWidth());
  }
  
  public List<Application> getInstalledApplication()
  {
    PackageManager localPackageManager = this.deviceInfoContext.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (((PackageInfo)localObject2).versionName != null) {
        try
        {
          localObject2 = localPackageManager.getApplicationInfo(((PackageInfo)localObject2).packageName, 0);
          if ((localObject2 != null) && (!isSystemPackage((ApplicationInfo)localObject2)))
          {
            Application localApplication = new Application();
            localApplication.setPackageName(((ApplicationInfo)localObject2).packageName);
            if (((ApplicationInfo)localObject2).loadLabel(localPackageManager) != null) {
              localApplication.setName(((ApplicationInfo)localObject2).loadLabel(localPackageManager).toString());
            }
            if (((ApplicationInfo)localObject2).loadDescription(localPackageManager) != null) {
              localApplication.setDescription(((ApplicationInfo)localObject2).loadDescription(localPackageManager).toString());
            }
            localArrayList.add(localApplication);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      }
    }
    return localArrayList;
  }
  
  public String getOSVersion()
  {
    if (StringUtils.isBlank(this.deviceOSVersion)) {
      this.deviceOSVersion = String.valueOf(Build.VERSION.SDK_INT);
    }
    return this.deviceOSVersion;
  }
  
  public int getStatusBarHeight()
  {
    Rect localRect = new Rect();
    int i;
    if ((this.deviceInfoContext instanceof Activity))
    {
      ((Activity)this.deviceInfoContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
      i = localRect.top;
    }
    int j;
    do
    {
      return i;
      i = 0;
      j = this.deviceInfoContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    } while (j <= 0);
    return this.deviceInfoContext.getResources().getDimensionPixelSize(j);
  }
  
  public HashMap<String, Integer> getViewPortSizeInDIP()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("width", Integer.valueOf(IOUtils.convertDevicePixelToMraidPoint(getContentDisplayWidth(), this.deviceInfoContext)));
    localHashMap.put("height", Integer.valueOf(IOUtils.convertDevicePixelToMraidPoint(getContentDisplayHeight(), this.deviceInfoContext)));
    return localHashMap;
  }
  
  public boolean isGooglePlayAvailable()
  {
    return GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.deviceInfoContext) == 0;
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    boolean bool = false;
    int i = 1;
    Object localObject = null;
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.deviceInfoContext);
      localObject = localInfo;
      i = 0;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      for (;;) {}
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      for (;;) {}
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    if (i == 0) {
      bool = localObject.isLimitAdTrackingEnabled();
    }
    return bool;
  }
  
  public void setRegulatedMode(boolean paramBoolean)
  {
    this.isRegulatedModeEnabled = paramBoolean;
    this.deviceOpenUUID = null;
  }
}
