package com.es.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.bingo.tour.party.crazy.free.AppActivity;
import com.es.analytis.CustomInstallReceiver;
import com.es.billing.BillingManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxHelper;

public class MetaInfo
{
  private static final String TAG = "MetaInfo";
  private static final String UUID_REFERRER = "elestormid";
  
  public MetaInfo() {}
  
  private static String generateUUID()
  {
    String str3 = IOUtils.md5(getDeviceInfo());
    try
    {
      str1 = UUID.nameUUIDFromBytes((getAndroidID() + str3).getBytes("utf-8")).toString();
      str2 = str1;
      if (str1.isEmpty()) {
        str2 = str3;
      }
      Cocos2dxHelper.setStringForKey("elestormid", str2);
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        try
        {
          String str2;
          IOUtils.writeBytesToFile(Cocos2dxActivity.getContext().getFileStreamPath("elestormid"), str2.getBytes("UTF-8"));
          return str2;
        }
        catch (Exception localException1)
        {
          String str1;
          Log.e("MetaInfo", "Failed to save referrer in file " + localException1);
        }
        localException2 = localException2;
        str1 = "";
        Log.e("MetaInfo", "generate uuid error:" + localException2);
      }
      return localException2;
    }
  }
  
  public static String getAdsID()
  {
    String str2 = getGoogleAdvertisingId();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return str1;
  }
  
  public static String getAndroidID()
  {
    return Settings.Secure.getString(Cocos2dxActivity.getContext().getContentResolver(), "android_id");
  }
  
  public static void getAppList()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = Cocos2dxHelper.getActivity().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localStringBuffer.append(localPackageInfo.packageName + ";");
      }
    }
    Cocos2dxHelper.setStringForKey("ANDROID_APPLIST", localStringBuffer.toString());
  }
  
  private static String getDeviceInfo()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(Build.BOARD);
    localStringBuffer.append(Build.BOOTLOADER);
    localStringBuffer.append(Build.BRAND);
    localStringBuffer.append(Build.DEVICE);
    localStringBuffer.append(Build.DISPLAY);
    localStringBuffer.append(Build.getRadioVersion());
    localStringBuffer.append(Build.FINGERPRINT);
    localStringBuffer.append(Build.HARDWARE);
    localStringBuffer.append(Build.HOST);
    localStringBuffer.append(Build.ID);
    localStringBuffer.append(Build.MANUFACTURER);
    localStringBuffer.append(Build.MODEL);
    localStringBuffer.append(Build.SERIAL);
    localStringBuffer.append(Build.PRODUCT);
    localStringBuffer.append(Build.TAGS);
    localStringBuffer.append(Build.TIME);
    localStringBuffer.append(Build.TYPE);
    localStringBuffer.append(Build.USER);
    return localStringBuffer.toString();
  }
  
  public static String getGoogleAdvertisingId()
  {
    Object localObject = null;
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(Cocos2dxActivity.getContext());
      localObject = localInfo;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("Google", localException.toString());
      }
    }
    if (localObject != null) {
      return localObject.getId();
    }
    return null;
  }
  
  public static String getIMEI()
  {
    Object localObject;
    if (ContextCompat.checkSelfPermission(AppActivity.getContext(), "android.permission.READ_PHONE_STATE") == 0) {
      localObject = ((TelephonyManager)Cocos2dxActivity.getContext().getSystemService("phone")).getDeviceId();
    }
    String str;
    do
    {
      return localObject;
      str = getGoogleAdvertisingId();
      localObject = str;
    } while (str != null);
    return getNewUUID();
  }
  
  public static String getModel()
  {
    if (BillingManager.getInstance().isAmazon()) {
      return "amazon";
    }
    return "android";
  }
  
  public static String getNewUUID()
  {
    String str1 = IOUtils.md5(getAndroidID() + getDeviceInfo());
    try
    {
      String str2 = UUID.nameUUIDFromBytes(str1.getBytes("utf-8")).toString();
      return str2;
    }
    catch (Exception localException)
    {
      Log.e("MetaInfo", "generate uuid error:" + localException);
    }
    return str1;
  }
  
  public static String getPackageLang()
  {
    return Cocos2dxActivity.getContext().getString(2131230816);
  }
  
  public static String getPackageName()
  {
    return Cocos2dxActivity.getContext().getPackageName();
  }
  
  public static String getSource()
  {
    return CustomInstallReceiver.getReferrerValue(Cocos2dxActivity.getContext(), "utm_source", "");
  }
  
  public static String getSystemLocale()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getSystemModel()
  {
    return Build.MODEL;
  }
  
  public static String getSystemNetCountryIso()
  {
    return ((TelephonyManager)Cocos2dxActivity.getContext().getSystemService("phone")).getNetworkCountryIso();
  }
  
  public static String getSystemVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String getUUID()
  {
    Object localObject = Cocos2dxHelper.getStringForKey("elestormid", "unknown");
    if (!((String)localObject).equals("unknown")) {}
    for (;;)
    {
      return localObject;
      try
      {
        str = IOUtils.stringFromStream(Cocos2dxActivity.getContext().openFileInput("elestormid"));
        localObject = str;
        if (!str.equals("unknown")) {
          continue;
        }
        return generateUUID();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          String str = "unknown";
          Log.i("MetaInfo", "Failed to read referrer from file: " + localException);
        }
      }
    }
  }
}
