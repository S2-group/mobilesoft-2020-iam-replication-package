package com.blesh.sdk.model;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.util.Pair;
import android.telephony.TelephonyManager;
import com.blesh.sdk.di.component.BaseComponent;
import com.blesh.sdk.di.component.BleshSdkComponent;
import com.blesh.sdk.util.BleshConfig;
import com.blesh.sdk.util.BleshInstance;
import com.blesh.sdk.util.BleshRecordUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;

@TargetApi(18)
public class BleshAPIParams
{
  private static final String TAG = "BleshAPIParams";
  private String APIKey = null;
  private String APIUser = null;
  private String integrationId = null;
  private String integrationType = null;
  @Inject
  SharedPreferences mSharedPreferences;
  private String notificationColor = null;
  private String notificationSmallIcon = null;
  private String optionalKey = null;
  private String pushToken = null;
  
  public BleshAPIParams() {}
  
  private String getAPIKey()
  {
    return this.APIKey;
  }
  
  private String getBundleList()
  {
    localHashMap = new HashMap();
    try
    {
      PackageManager localPackageManager = BleshInstance.sharedInstance().getContext().getPackageManager();
      if (localPackageManager == null) {
        return null;
      }
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        String str1 = localApplicationInfo.packageName;
        String str2 = localPackageManager.getApplicationLabel(localApplicationInfo).toString();
        if (((localApplicationInfo.flags & 0x80) == 0) && ((localApplicationInfo.flags & 0x1) == 0)) {
          localHashMap.put(str2, str1);
        }
      }
      return new GsonBuilder().create().toJson(localHashMap);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private String getIdfb(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0)
    {
      if (Build.VERSION.SDK_INT >= 23) {
        return Settings.Secure.getString(paramContext.getContentResolver(), "bluetooth_address");
      }
      paramContext = BluetoothAdapter.getDefaultAdapter();
      if (paramContext != null) {
        return paramContext.getAddress();
      }
      return "";
    }
    return null;
  }
  
  private String getIntegrationType()
  {
    return this.integrationType;
  }
  
  private String getOptionalKey()
  {
    return this.optionalKey;
  }
  
  private String getPushToken()
  {
    return this.pushToken;
  }
  
  public static BleshAPIParams loadParams(Intent paramIntent, Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if ((paramIntent != null) && (paramIntent.getStringExtra("APIUser") != null)) {
      saveInitialParams(paramIntent, paramContext);
    }
    paramIntent = BleshRecordUtil.readFromRecordStoreStr(paramContext, "APIUser");
    String str1 = BleshRecordUtil.readFromRecordStoreStr(paramContext, "APIKey");
    String str2 = BleshRecordUtil.readFromRecordStoreStr(paramContext, "integrationType");
    String str3 = BleshRecordUtil.readFromRecordStoreStr(paramContext, "integrationId");
    String str4 = BleshRecordUtil.readFromRecordStoreStr(paramContext, "pushToken");
    String str5 = BleshRecordUtil.readFromRecordStoreStr(paramContext, "optionalKey");
    String str6 = BleshRecordUtil.readFromRecordStoreStr(paramContext, "notificationSmallIcon");
    paramContext = BleshRecordUtil.readFromRecordStoreStr(paramContext, "notificationColor");
    if (paramIntent == null) {
      return null;
    }
    BleshAPIParams localBleshAPIParams = new BleshAPIParams();
    localBleshAPIParams.setAPIUser(paramIntent);
    localBleshAPIParams.setAPIKey(str1);
    localBleshAPIParams.setIntegrationId(str3);
    localBleshAPIParams.setIntegrationType(str2);
    localBleshAPIParams.setPushToken(str4);
    localBleshAPIParams.setOptionalKey(str5);
    localBleshAPIParams.setNotificationSmallIcon(str6);
    localBleshAPIParams.setNotificationColor(paramContext);
    return localBleshAPIParams;
  }
  
  private static void saveInitialParams(Intent paramIntent, Context paramContext)
  {
    String str1 = paramIntent.getStringExtra("APIUser");
    String str2 = paramIntent.getStringExtra("APIKey");
    String str3 = paramIntent.getStringExtra("integrationType");
    String str4 = paramIntent.getStringExtra("integrationId");
    String str5 = paramIntent.getStringExtra("pushToken");
    String str6 = paramIntent.getStringExtra("optionalKey");
    String str7 = paramIntent.getStringExtra("notificationSmallIcon");
    paramIntent = paramIntent.getStringExtra("notificationColor");
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "APIUser", str1);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "APIKey", str2);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "integrationType", str3);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "integrationId", str4);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "pushToken", str5);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "optionalKey", str6);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "notificationSmallIcon", str7);
    BleshRecordUtil.writeToRecordStoreStr(paramContext, "notificationColor", paramIntent);
  }
  
  private void setAPIKey(String paramString)
  {
    this.APIKey = paramString;
  }
  
  private void setAPIUser(String paramString)
  {
    this.APIUser = paramString;
  }
  
  private void setIntegrationId(String paramString)
  {
    this.integrationId = paramString;
  }
  
  private void setIntegrationType(String paramString)
  {
    this.integrationType = paramString;
  }
  
  private void setNotificationColor(String paramString)
  {
    this.notificationColor = paramString;
  }
  
  private void setNotificationSmallIcon(String paramString)
  {
    this.notificationSmallIcon = paramString;
  }
  
  private void setOptionalKey(String paramString)
  {
    this.optionalKey = paramString;
  }
  
  private void setPushToken(String paramString)
  {
    this.pushToken = paramString;
  }
  
  public String getAPIUser()
  {
    return this.APIUser;
  }
  
  public String getExitParams(String paramString, Location paramLocation)
  {
    Context localContext = BleshInstance.sharedInstance().getContext();
    HashMap localHashMap = new HashMap();
    localHashMap.put("accessToken", BleshInstance.sharedInstance().getInitParams().getAccessToken());
    if (localContext == null)
    {
      localHashMap.put("latitude", "0.0");
      localHashMap.put("longitude", "0.0");
    }
    else if (paramLocation != null)
    {
      localHashMap.put("latitude", Double.toString(paramLocation.getLatitude()));
      localHashMap.put("longitude", Double.toString(paramLocation.getLongitude()));
    }
    localHashMap.put("majorMinor", paramString);
    try
    {
      paramString = UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH);
      paramLocation = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
      localHashMap.put("ctid", paramString);
      localHashMap.put("timestamp", paramLocation);
      localHashMap.put("ostype", "2");
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return new GsonBuilder().create().toJson(localHashMap);
  }
  
  public String getIntegrationId()
  {
    return this.integrationId;
  }
  
  public String getNotificationColor()
  {
    return this.notificationColor;
  }
  
  public String getNotificationSmallIcon()
  {
    return this.notificationSmallIcon;
  }
  
  public String getParams(Location paramLocation, Pair<String, Boolean> paramPair, final BleshBatteryModel paramBleshBatteryModel)
  {
    if (BleshSdkComponent.getBaseComponent() != null) {
      BleshSdkComponent.getBaseComponent().inject(this);
    }
    Gson localGson = new GsonBuilder().create();
    final Context localContext = BleshInstance.sharedInstance().getContext();
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("apiUser", getAPIUser());
    localHashMap1.put("apiKey", getAPIKey());
    HashMap localHashMap2 = new HashMap();
    Object localObject2 = null;
    if (localContext != null) {
      localObject1 = localContext.getSystemService("phone");
    } else {
      localObject1 = null;
    }
    Object localObject1 = (TelephonyManager)localObject1;
    if (localObject1 != null)
    {
      String str = ((TelephonyManager)localObject1).getNetworkOperator();
      if ((str != null) && (!str.isEmpty()))
      {
        new StringBuilder("networkOperator:").append(str);
        try
        {
          localHashMap2.put("mcc", str.substring(0, 3));
          localHashMap2.put("mnc", str.substring(3));
          localHashMap2.put("name", ((TelephonyManager)localObject1).getNetworkOperatorName());
          localHashMap1.put("carrier", localHashMap2);
          localHashMap1.put("rat", BleshConfig.getNetworkType(localContext));
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    if ((paramPair != null) && (!((String)paramPair.first).equals("")))
    {
      localHashMap1.put("idfa", paramPair.first);
      localHashMap1.put("optout", paramPair.second);
    }
    else
    {
      localHashMap1.put("idfa", "");
      localHashMap1.put("optout", Boolean.valueOf(true));
    }
    try
    {
      localHashMap1.put("uuid", BleshInstance.getUniqueID());
    }
    catch (Exception paramPair)
    {
      paramPair.printStackTrace();
    }
    localHashMap1.put("integrationId", getIntegrationId());
    localHashMap1.put("integrationType", getIntegrationType());
    localHashMap1.put("handsetToken", getPushToken());
    localHashMap1.put("optionalKey", getOptionalKey());
    try
    {
      localHashMap1.put("resolution", BleshConfig.getRealScreenResolution());
    }
    catch (Exception paramPair)
    {
      paramPair.printStackTrace();
    }
    if (localContext == null)
    {
      localHashMap1.put("latitude", "0.0");
      localHashMap1.put("longitude", "0.0");
    }
    else if (paramLocation == null)
    {
      localHashMap1.put("latitude", "0.0");
      localHashMap1.put("longitude", "0.0");
    }
    else
    {
      localHashMap1.put("latitude", Double.toString(paramLocation.getLatitude()));
      localHashMap1.put("longitude", Double.toString(paramLocation.getLongitude()));
    }
    localHashMap1.put("handsetBrand", Build.BRAND);
    localHashMap1.put("handsetModel", Build.MODEL);
    localHashMap1.put("handsetOS", Build.VERSION.RELEASE);
    localHashMap1.put("sdkVersion", "3.4.10");
    paramLocation = localObject2;
    if (localContext != null) {
      try
      {
        paramLocation = localContext.getPackageName();
      }
      catch (Exception paramLocation)
      {
        paramLocation.printStackTrace();
        paramLocation = localObject2;
      }
    }
    localHashMap1.put("bundle", paramLocation);
    localHashMap1.put("batteryLevel", String.format(Locale.ENGLISH, "%.2f", new Object[] { Double.valueOf(paramBleshBatteryModel.getLevel()) }));
    localHashMap1.put("batteryState", paramBleshBatteryModel.getState());
    paramLocation = BluetoothAdapter.getDefaultAdapter();
    if (paramLocation == null) {
      localHashMap1.put("bluetoothStatus", "0");
    } else if (!paramLocation.isEnabled()) {
      localHashMap1.put("bluetoothStatus", "0");
    } else {
      localHashMap1.put("bluetoothStatus", "1");
    }
    localHashMap1.put("localNotificationStatus", "1");
    if (localContext != null)
    {
      paramLocation = localContext.getPackageManager();
      try
      {
        if (paramLocation.checkPermission("com.google.android.c2dm.permission.RECEIVE", localContext.getPackageName()) == 0) {
          localHashMap1.put("remoteNotificationStatus", "1");
        } else {
          localHashMap1.put("remoteNotificationStatus", "0");
        }
      }
      catch (Exception paramLocation)
      {
        paramLocation.printStackTrace();
      }
      paramLocation = ((ConnectivityManager)localContext.getSystemService("connectivity")).getActiveNetworkInfo().getTypeName();
      if ((paramLocation != null) && (paramLocation.equalsIgnoreCase("WIFI"))) {
        localHashMap1.put("networkStatus", "wifi");
      } else if ((paramLocation != null) && (paramLocation.equalsIgnoreCase("MOBILE"))) {
        localHashMap1.put("networkStatus", "data");
      }
    }
    paramLocation = UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH);
    paramPair = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    Thread localThread;
    if (this.mSharedPreferences.getInt("enableBundleList", 0) == 1) {
      try
      {
        paramBleshBatteryModel = new String[1];
        localThread = new Thread(new Runnable()
        {
          public void run()
          {
            paramBleshBatteryModel[0] = BleshAPIParams.this.getBundleList();
          }
        });
        localThread.start();
        localThread.join();
        if (paramBleshBatteryModel[0] != null) {
          localHashMap1.put("bundleList", paramBleshBatteryModel[0]);
        }
      }
      catch (InterruptedException paramBleshBatteryModel)
      {
        paramBleshBatteryModel.printStackTrace();
      }
    }
    try
    {
      paramBleshBatteryModel = new String[1];
      localThread = new Thread(new Runnable()
      {
        public void run()
        {
          paramBleshBatteryModel[0] = BleshAPIParams.this.getIdfb(localContext);
        }
      });
      localThread.start();
      localThread.join();
      if (paramBleshBatteryModel[0] != null) {
        localHashMap1.put("idfb", paramBleshBatteryModel[0]);
      }
    }
    catch (InterruptedException paramBleshBatteryModel)
    {
      paramBleshBatteryModel.printStackTrace();
    }
    localHashMap1.put("language", Locale.getDefault().toString());
    localHashMap1.put("ctid", paramLocation);
    localHashMap1.put("timestamp", paramPair);
    localHashMap1.put("ostype", "2");
    paramPair = "";
    paramLocation = paramPair;
    if (localContext != null) {
      try
      {
        paramLocation = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionName;
      }
      catch (PackageManager.NameNotFoundException paramLocation)
      {
        paramLocation.printStackTrace();
        paramLocation = paramPair;
      }
    }
    localHashMap1.put("appVersion", paramLocation);
    return localGson.toJson(localHashMap1);
  }
}
