package com.mobknowsdk.system;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.mobknowsdk.connection.cconst.CParams;
import com.mobknowsdk.log.ELog;
import com.mobknowsdk.log.consts.PhoneINFEL;
import com.mobknowsdk.sdk.MobKnowSdk;
import com.mobknowsdk.services.SConst;
import com.mobknowsdk.services.SLocalM;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PhoneInformation
{
  private Context c;
  public boolean overrideLog = false;
  private SLocalM sLocalM;
  private TelephonyManager tm;
  
  public PhoneInformation(Context paramContext)
  {
    this.c = paramContext;
    this.sLocalM = new SLocalM(paramContext);
    this.tm = ((TelephonyManager)this.c.getSystemService("phone"));
  }
  
  private String Ispr()
  {
    try
    {
      String str3 = this.sLocalM.getParam(CParams.ISPR, "");
      str1 = str3;
      if (!str3.equals("")) {
        break label47;
      }
      if (!ISR.isDeviceRooted()) {
        break label56;
      }
      str1 = "Yes";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str1;
        label47:
        continue;
        label56:
        String str2 = "No";
      }
    }
    this.sLocalM.setParam(CParams.ISPR, str1);
    return str1;
    return "No";
  }
  
  private String getAdsId()
  {
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      if (!this.sLocalM.getParam(SConst.GID).equals("")) {
        return this.sLocalM.getParam(SConst.GID);
      }
      if (Looper.getMainLooper().equals(Looper.myLooper())) {
        return "";
      }
      Object localObject1 = null;
      try
      {
        if (this.c.getApplicationContext() != null) {
          localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(this.c.getApplicationContext());
        }
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = AdvertisingIdClient.getAdvertisingIdInfo(this.c);
        }
        if (localObject2 != null)
        {
          localObject1 = ((AdvertisingIdClient.Info)localObject2).getId();
          this.sLocalM.setParam(SConst.GID, (String)localObject1);
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ADS_ID_4, localNullPointerException, this.overrideLog);
      }
      catch (IOException localIOException)
      {
        ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ADS_ID_3, localIOException, this.overrideLog);
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ADS_ID_2, localGooglePlayServicesRepairableException, this.overrideLog);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ADS_ID_1, localGooglePlayServicesNotAvailableException, this.overrideLog);
      }
      return this.sLocalM.getParam(SConst.GID, "");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ADS_ID_0, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.overrideLog, true);
    return "";
  }
  
  private String getAndroidId()
  {
    try
    {
      String str = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ANDROID_ID, localException, this.overrideLog);
    }
    return "";
  }
  
  private String getDefaultUA()
  {
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject1 = getUANewAPI();
      if (!((String)localObject1).equals("")) {
        return localObject1;
      }
    }
    try
    {
      localObject1 = WebSettings.class.getDeclaredConstructor(new Class[] { Context.class, WebView.class });
      ((Constructor)localObject1).setAccessible(true);
      try
      {
        String str = ((WebSettings)((Constructor)localObject1).newInstance(new Object[] { this.c, null })).getUserAgentString();
        return str;
      }
      finally
      {
        ((Constructor)localObject1).setAccessible(false);
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return System.getProperty("http.agent");
  }
  
  @SuppressLint({"MissingPermission"})
  private String getDeviceId()
  {
    if (this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      try
      {
        String str = this.tm.getDeviceId();
        if (str != null) {
          return str;
        }
      }
      catch (Exception localException)
      {
        ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_DEVICE_ID, localException, this.overrideLog);
        return "";
      }
    }
    return "";
  }
  
  private String getIAJ()
  {
    if (this.sLocalM.getParam("check_user_app_bundles", "0").equals("0")) {
      return "";
    }
    try
    {
      Object localObject2 = this.c.getPackageManager();
      Iterator localIterator = ((PackageManager)localObject2).getInstalledApplications(128).iterator();
      Object localObject1 = "{\"apps\":[";
      int i = 0;
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.sourceDir.startsWith("/data/app/"))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)localObject1);
          localStringBuilder.append("{\"title\":\"");
          localStringBuilder.append(localApplicationInfo.loadLabel((PackageManager)localObject2));
          localStringBuilder.append("\",");
          localObject1 = localStringBuilder.toString();
          localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)localObject1);
          localStringBuilder.append("\"pakeagename\":\"");
          localStringBuilder.append(localApplicationInfo.packageName);
          localStringBuilder.append("\"}, ");
          localObject1 = localStringBuilder.toString();
          i += 1;
        }
      }
      localObject2 = localObject1;
      if (i > 0) {
        localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 2);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("]}");
      localObject1 = ((StringBuilder)localObject1).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_IAJ, localException, this.overrideLog);
    }
    return "";
  }
  
  private String getITO(String paramString)
  {
    try
    {
      long l = this.c.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      paramString = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new Date(l));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_ITO, paramString, this.overrideLog);
    }
    return "";
  }
  
  @TargetApi(17)
  private String getUANewAPI()
  {
    try
    {
      String str = WebSettings.getDefaultUserAgent(this.c);
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "";
  }
  
  public String gAP()
  {
    if (this.sLocalM.getParam("check_user_permissions", "0").equals("0")) {
      return "";
    }
    if (Build.VERSION.SDK_INT >= 16) {}
    try
    {
      localPackageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 4096);
      localObject1 = "[";
      i = 0;
      j = 0;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PackageInfo localPackageInfo;
        Object localObject1;
        int i;
        int k;
        Object localObject3;
        continue;
        i += 1;
        int j = k;
        Object localObject2 = localObject3;
      }
    }
    if (i < localPackageInfo.requestedPermissions.length)
    {
      k = j;
      localObject3 = localObject1;
      if ((localPackageInfo.requestedPermissionsFlags[i] & 0x2) != 0)
      {
        k = j + 1;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append("\"");
        ((StringBuilder)localObject3).append(localPackageInfo.requestedPermissions[i]);
        ((StringBuilder)localObject3).append("\",");
        localObject3 = ((StringBuilder)localObject3).toString();
      }
    }
    else
    {
      localObject3 = localObject1;
      if (j > 0) {
        localObject3 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject3);
      ((StringBuilder)localObject1).append("]");
      localObject1 = ((StringBuilder)localObject1).toString();
      return localObject1;
      return null;
    }
  }
  
  public String getCAOS()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(Build.VERSION.SDK_INT);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_CURRENT_ANDROID_OS, localException, this.overrideLog);
    }
    return "";
  }
  
  public String getDM()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(Build.MODEL);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_DM, localException, this.overrideLog);
    }
    return "";
  }
  
  public String getDP()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(Build.PRODUCT);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_DP, localException, this.overrideLog);
    }
    return "";
  }
  
  public Map<Object, String> getDevInf(Map<Object, String> paramMap)
  {
    String str = getIfa();
    if (!str.equals("")) {
      paramMap.put(CParams.IFA, str);
    }
    str = getAdsId();
    if (!str.equals("")) {
      paramMap.put(CParams.IDFA, str);
    }
    if (!MobKnowSdk.isSdkLock())
    {
      str = getCAOS();
      if (!str.equals("")) {
        paramMap.put(CParams.AOS, str);
      }
      str = getDM();
      if (!str.equals("")) {
        paramMap.put(CParams.DM, str);
      }
      str = getM();
      if (!str.equals("")) {
        paramMap.put(CParams.M, str);
      }
      str = getDP();
      if (!str.equals("")) {
        paramMap.put(CParams.DP, str);
      }
      str = getAndroidId();
      if (!str.equals("")) {
        paramMap.put(CParams.ANDROID_ID, str);
      }
      str = getDeviceId();
      if (!str.equals("")) {
        paramMap.put(CParams.DEVICE_ID, str);
      }
    }
    return paramMap;
  }
  
  public String getIfa()
  {
    String str2 = getAdsId();
    String str1 = str2;
    if (!MobKnowSdk.isSdkLock())
    {
      str1 = str2;
      if (str2.equals(""))
      {
        str2 = getAndroidId();
        str1 = str2;
        if (str2.equals("")) {
          str1 = getDeviceId();
        }
      }
    }
    return str1;
  }
  
  public Map<Object, String> getInf(Map<Object, String> paramMap)
  {
    return getLAL(getDevInf(getPacInf(paramMap)));
  }
  
  public Map<CParams, String> getInsAppInf(Map<CParams, String> paramMap)
  {
    if (!MobKnowSdk.isSdkLock())
    {
      String str = getIAJ();
      if (!str.equals("")) {
        paramMap.put(CParams.APPS, str);
      }
      str = gAP();
      if (!str.equals("")) {
        paramMap.put(CParams.GAP, str);
      }
      str = Ispr();
      if (!str.equals("")) {
        paramMap.put(CParams.ISPR, str);
      }
    }
    return paramMap;
  }
  
  public Map<Object, String> getLAL(Map<Object, String> paramMap)
  {
    return paramMap;
  }
  
  public String getM()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(Build.MANUFACTURER);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_M, localException, this.overrideLog);
    }
    return "";
  }
  
  public Map<Object, String> getPacInf(Map<Object, String> paramMap)
  {
    String str = getSdkVersion();
    if (!str.equals("")) {
      paramMap.put(CParams.VERSION, str);
    }
    str = getSdkName();
    if (!str.equals("")) {
      paramMap.put(CParams.VERSION_NAME, str);
    }
    str = getPackage();
    if (!str.equals(""))
    {
      paramMap.put(CParams.OAPPV1, str);
      paramMap.put(CParams.OAPPV2, str);
    }
    if (!MobKnowSdk.isSdkLock())
    {
      str = getITO(str);
      if (!str.equals("")) {
        paramMap.put(CParams.T_DATA, str);
      }
    }
    return paramMap;
  }
  
  public String getPackage()
  {
    try
    {
      String str = this.c.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException)
    {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_PACKAGE, localException, this.overrideLog);
    }
    return "";
  }
  
  public String getSdkName()
  {
    return "1.7.23";
  }
  
  public String getSdkVersion()
  {
    return "20";
  }
  
  public String getUA()
  {
    String str = getDefaultUA();
    if (str.equals("")) {
      str = this.sLocalM.getParam(CParams.UA);
    } else {
      this.sLocalM.setParam(CParams.UA, str);
    }
    if (str.equals("")) {
      ELog.e(this.c, PhoneInformation.class, PhoneINFEL.GET_UA, "EMPTY USER AGENT", this.overrideLog);
    }
    return str;
  }
}
