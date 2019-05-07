package com.sdkbox.plugin;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.sdkbox.services.TrackingLocalStorage;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrackingInfoAndroid
{
  public static final String TAG = "TrackingInfo";
  private static String _cachedAppName = null;
  private static String _cachedFingerPrint_AndroidId;
  private static String _cachedFingerPrint_Build = null;
  private static String _cachedFingerPrint_MACAddress;
  protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
  public static TrackingLocalStorage tls;
  
  static
  {
    _cachedFingerPrint_AndroidId = null;
    _cachedFingerPrint_MACAddress = null;
    tls = new TrackingLocalStorage();
  }
  
  public TrackingInfoAndroid() {}
  
  public static boolean IsNetworkAvailable()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)SDKBox.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        boolean bool = localNetworkInfo.isConnectedOrConnecting();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception localException) {}
    return true;
  }
  
  public static String bytesToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      arrayOfChar[(i * 2)] = hexArray[(j >>> 4)];
      arrayOfChar[(i * 2 + 1)] = hexArray[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  private static boolean canAccessWifi()
  {
    return SDKBox.getContext().checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0;
  }
  
  private static String capitalize(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    int i = 1;
    paramString = "";
    int k = arrayOfChar.length;
    int j = 0;
    if (j < k)
    {
      char c = arrayOfChar[j];
      if ((i != 0) && (Character.isLetter(c)))
      {
        paramString = paramString + Character.toUpperCase(c);
        i = 0;
      }
      for (;;)
      {
        j += 1;
        break;
        if (Character.isWhitespace(c)) {
          i = 1;
        }
        paramString = paramString + c;
      }
    }
    return paramString;
  }
  
  public static String generateHash(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = MessageDigest.getInstance(paramString2);
      paramString1 = paramString1.getBytes("UTF-8");
      paramString2.update(paramString1, 0, paramString1.length);
      paramString1 = bytesToHex(paramString2.digest());
      return paramString1;
    }
    catch (Exception paramString1)
    {
      SdkboxLog.d("TrackingInfo", "GenerateHash " + paramString1.getMessage(), new Object[0]);
    }
    return null;
  }
  
  public static String getAndroidId()
  {
    try
    {
      String str = Settings.Secure.getString(SDKBox.getContext().getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      SdkboxLog.e("TrackingInfo", "Exception: " + localException.toString(), new Object[0]);
    }
    return "unknown";
  }
  
  public static String getAppName()
  {
    if (_cachedAppName != null) {
      return _cachedAppName;
    }
    Object localObject1 = "unknown";
    try
    {
      Object localObject2 = SDKBox.getContext();
      PackageManager localPackageManager = ((Context)localObject2).getPackageManager();
      localObject2 = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(((Context)localObject2).getApplicationInfo().packageName, 0)).toString();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        SdkboxLog.e("TrackingInfo", "Exception: " + localException.toString(), new Object[0]);
      }
    }
    _cachedAppName = (String)localObject1;
    return _cachedAppName;
  }
  
  public static String getAppPackageId()
  {
    return SDKBox.getContext().getPackageName();
  }
  
  private static String getCachedFingerPrintBuild()
  {
    if (_cachedFingerPrint_Build != null) {
      return _cachedFingerPrint_Build;
    }
    _cachedFingerPrint_Build = "0" + (char)(Build.BOARD.length() % 10) + "0" + (char)(Build.BRAND.length() % 10) + "0" + (char)(Build.CPU_ABI.length() % 10) + "0" + (char)(Build.DEVICE.length() % 10) + "0" + (char)(Build.MANUFACTURER.length() % 10) + "0" + (char)(Build.MODEL.length() % 10) + "0" + (char)(Build.PRODUCT.length() % 10) + "0" + (char)(Build.TAGS.length() % 10) + "0" + (char)(Build.TYPE.length() % 10) + "0" + (char)(Build.USER.length() % 10);
    return _cachedFingerPrint_Build;
  }
  
  public static String getChannel()
  {
    try
    {
      Object localObject = SDKBox.getContext();
      localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128).metaData.getString("store");
      if (localObject != null)
      {
        localObject = ((String)localObject).toLowerCase();
        if (((String)localObject).contains("playphone")) {
          return "playphone";
        }
        if (((String)localObject).contains("amazon")) {
          return "amazon";
        }
        return "googleplay";
      }
    }
    catch (Exception localException) {}
    return "googleplay";
  }
  
  public static String getCountryCode()
  {
    return SDKBox.getContext().getResources().getConfiguration().locale.getCountry();
  }
  
  public static int getDefaultTrackingMask()
  {
    return SDKBox._sContext.getSharedPreferences("tracking_mask", 0).getInt("mask", 3);
  }
  
  public static String getDeviceFingerprint()
  {
    Context localContext = SDKBox.getContext();
    String str1 = "mac-address-unavailable";
    String str2 = getCachedFingerPrintBuild();
    String str3 = Settings.Secure.getString(localContext.getContentResolver(), "android_id");
    if (canAccessWifi()) {
      str1 = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    for (;;)
    {
      return str2 + str3 + str1;
      SdkboxLog.e("TrackingInfo", "Failed to access wifi, need ACCESS_WIFI_STATE perms?", new Object[0]);
    }
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return capitalize(str2);
    }
    return capitalize(str1) + " " + str2;
  }
  
  public static String getFingerprintString(String paramString)
  {
    Object localObject = SDKBox.getContext();
    if (paramString.equals("build")) {
      return getCachedFingerPrintBuild();
    }
    if (paramString.equals("androidid"))
    {
      if (_cachedFingerPrint_AndroidId != null) {
        return _cachedFingerPrint_AndroidId;
      }
      _cachedFingerPrint_AndroidId = Settings.Secure.getString(((Context)localObject).getContentResolver(), "android_id");
      return _cachedFingerPrint_AndroidId;
    }
    if (paramString.equals("macaddress"))
    {
      if (_cachedFingerPrint_MACAddress != null) {
        return _cachedFingerPrint_MACAddress;
      }
      paramString = "mac-address-unavailable";
      if (canAccessWifi())
      {
        localObject = ((WifiManager)((Context)localObject).getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (localObject != null) {
          paramString = (String)localObject;
        }
      }
      for (;;)
      {
        _cachedFingerPrint_MACAddress = paramString;
        return _cachedFingerPrint_MACAddress;
        SdkboxLog.e("TrackingInfo", "getMacAddress returned null", new Object[0]);
        continue;
        SdkboxLog.e("TrackingInfo", "Failed to access wifi, need ACCESS_WIFI_STATE perms?", new Object[0]);
      }
    }
    SdkboxLog.e("TrackingInfo", "Requested string unknown " + paramString, new Object[0]);
    return "";
  }
  
  public static String getInstalledApplications()
  {
    String str = "";
    Object localObject1 = str;
    try
    {
      Object localObject2 = new Intent("android.intent.action.MAIN");
      localObject1 = str;
      ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
      localObject1 = str;
      Iterator localIterator = SDKBox.getContext().getPackageManager().queryIntentActivities((Intent)localObject2, 0).iterator();
      for (;;)
      {
        localObject1 = str;
        localObject2 = str;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = str;
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        localObject1 = str;
        if (localResolveInfo.activityInfo != null)
        {
          localObject1 = str;
          if (localResolveInfo.activityInfo.packageName != null)
          {
            localObject2 = str;
            localObject1 = str;
            if (str.length() != 0)
            {
              localObject1 = str;
              localObject2 = str + ",";
            }
            localObject1 = localObject2;
            str = (String)localObject2 + localResolveInfo.activityInfo.packageName;
          }
        }
      }
      return localObject2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      localObject2 = localObject1;
    }
  }
  
  public static String getLatitude()
  {
    return getLongitudeAndLatitude()[1];
  }
  
  public static String getLongitude()
  {
    return getLongitudeAndLatitude()[0];
  }
  
  public static String[] getLongitudeAndLatitude()
  {
    try
    {
      Object localObject = (LocationManager)SDKBox.getContext().getSystemService("location");
      localObject = ((LocationManager)localObject).getLastKnownLocation((String)((LocationManager)localObject).getProviders(true).get(0));
      double d1 = ((Location)localObject).getLongitude();
      double d2 = ((Location)localObject).getLatitude();
      return new String[] { String.valueOf(d1), String.valueOf(d2) };
    }
    catch (Exception localException) {}
    return tmp79_73;
  }
  
  public static String getMetadata(String paramString)
  {
    try
    {
      Context localContext = SDKBox.getContext();
      paramString = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128).metaData.getString(paramString);
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static String getSystemVersion()
  {
    try
    {
      String str = Build.VERSION.CODENAME + ", version=" + Build.VERSION.RELEASE + ", SDK=" + Build.VERSION.SDK_INT;
      return str;
    }
    catch (Exception localException)
    {
      SdkboxLog.e("TrackingInfo", "getSystemVersion Exception: " + localException.toString(), new Object[0]);
    }
    return "unknown";
  }
  
  public static String getTimestamp()
  {
    return String.valueOf(System.currentTimeMillis());
  }
  
  public static boolean hasPermission(String paramString)
  {
    return SDKBox.getContext().checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static boolean isValidEmail(CharSequence paramCharSequence)
  {
    return (!TextUtils.isEmpty(paramCharSequence)) && (Patterns.EMAIL_ADDRESS.matcher(paramCharSequence).matches());
  }
  
  public static native void onAdvertisingIdInfo(String paramString, boolean paramBoolean);
  
  public static void reqAdvertisingIdentifier()
  {
    SDKBox.executeInBackground(new Runnable()
    {
      public void run()
      {
        try
        {
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(SDKBox.getContext());
          SDKBox.runOnGLThread(new Runnable()
          {
            public void run()
            {
              TrackingInfoAndroid.onAdvertisingIdInfo(String.valueOf(this.val$id), this.val$track);
            }
          });
          return;
        }
        catch (Exception localException)
        {
          SdkboxLog.e("TrackingInfo", "reqAdvertisingIdentifier Exception:" + localException.toString(), new Object[0]);
          return;
        }
        catch (Error localError)
        {
          SdkboxLog.e("TrackingInfo", "reqAdvertisingIdentifier Error:" + localError.toString(), new Object[0]);
        }
      }
    });
  }
  
  public static void setFlag(int paramInt, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = SDKBox._sContext.getSharedPreferences("tracking_mask", 0);
    int i = localSharedPreferences.getInt("mask", 3);
    if (paramBoolean) {}
    for (paramInt = i | paramInt;; paramInt = i & (paramInt ^ 0xFFFFFFFF))
    {
      localSharedPreferences.edit().putInt("mask", paramInt).apply();
      return;
    }
  }
  
  public static void tr_acct()
  {
    int i = 0;
    if ((getDefaultTrackingMask() & 0x2) != 2) {
      SdkboxLog.d("TrackingInfo", "tr_acct disabled.", new Object[0]);
    }
    while (!hasPermission("android.permission.GET_ACCOUNTS")) {
      return;
    }
    String str2 = "";
    String str1 = "";
    Account[] arrayOfAccount = AccountManager.get(SDKBox.getContext()).getAccounts();
    int j = arrayOfAccount.length;
    if (i < j)
    {
      Account localAccount = arrayOfAccount[i];
      if (isValidEmail(localAccount.name))
      {
        localObject = str1;
        if (str1.length() != 0) {
          localObject = str1 + ",";
        }
        str1 = (String)localObject + generateHash(localAccount.name.toLowerCase(), "SHA-1") + "," + generateHash(localAccount.name.toLowerCase(), "MD5");
      }
      for (;;)
      {
        i += 1;
        break;
        localObject = str2;
        if (str2.length() != 0) {
          localObject = str2 + ",";
        }
        str2 = (String)localObject + localAccount.name;
      }
    }
    Object localObject = new JSON();
    ((JSON)localObject).put("accounts", new JSON(str2));
    ((JSON)localObject).put("email", new JSON(str1));
    SdkboxLog.trace("SDKBOX_CORE", "tracking", ((JSON)localObject).toString());
  }
  
  public static void tr_apps()
  {
    if ((getDefaultTrackingMask() & 0x1) != 1)
    {
      SdkboxLog.d("TrackingInfo", "tr_APPS disabled.", new Object[0]);
      return;
    }
    String str = getInstalledApplications();
    JSON localJSON = new JSON();
    localJSON.put("installed_applications", new JSON(str));
    SdkboxLog.trace("SDKBOX_CORE", "tracking", localJSON.toString());
  }
  
  public static void trackRequest(String paramString)
  {
    tls.add(paramString);
  }
}
