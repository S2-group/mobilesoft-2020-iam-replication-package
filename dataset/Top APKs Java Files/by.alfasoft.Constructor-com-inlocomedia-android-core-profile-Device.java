package com.inlocomedia.android.core.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import com.inlocomedia.android.core.annotations.VisibleForTesting;
import com.inlocomedia.android.core.data.local.SharedPreferencesManager;
import com.inlocomedia.android.core.data.local.SharedPreferencesManager.Entry;
import com.inlocomedia.android.core.log.Logger;
import com.inlocomedia.android.core.util.GooglePlayServicesHelper;
import com.inlocomedia.android.core.util.IntentUtils;
import com.inlocomedia.android.core.util.MD5;
import com.inlocomedia.android.core.util.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class Device
{
  public static final int ANDROID_VERSION_CODE = Build.VERSION.SDK_INT;
  public static final String INDUSTRIAL_DESIGN_NAME;
  public static final String MANUFACTURER;
  public static final String MODEL = Build.MODEL;
  public static final String OS_NAME = "android";
  private static final String a = Logger.makeTag(Device.class);
  
  static
  {
    MANUFACTURER = Build.MANUFACTURER;
    INDUSTRIAL_DESIGN_NAME = Build.DEVICE;
  }
  
  private Device() {}
  
  @VisibleForTesting
  static String a()
  {
    return "ILM-ID-" + UUID.randomUUID().toString();
  }
  
  @SuppressLint({"HardwareIds"})
  @Nullable
  private static String a(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  static List<String> a(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getSimOperatorName());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager) {}
    return null;
  }
  
  @VisibleForTesting
  static List<String> a(String paramString)
  {
    if (Validator.isNullOrEmpty(paramString))
    {
      paramString = null;
      return paramString;
    }
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = paramString.split(",");
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      paramString = localArrayList;
      if (i >= j) {
        break;
      }
      paramString = arrayOfString[i];
      if ((!paramString.isEmpty()) && (!paramString.equals("null"))) {
        localArrayList.add(paramString);
      }
      i += 1;
    }
  }
  
  static List<String> b(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getSimCountryIso());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager) {}
    return null;
  }
  
  static List<String> c(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getNetworkCountryIso());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager) {}
    return null;
  }
  
  public static boolean canHandleIntent(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static boolean canHandleIntentForUri(Context paramContext, String paramString)
  {
    return (paramString != null) && (canHandleIntent(paramContext, IntentUtils.getIntentForUri(paramContext, paramString)));
  }
  
  public static String getAdOrDeviceId(Context paramContext)
  {
    String str2 = getGoogleAdvertisingId(paramContext);
    String str1 = str2;
    if (str2 == null) {
      str1 = getDeviceId(paramContext);
    }
    return str1;
  }
  
  @Nullable
  public static String getDevelopmentDeviceId(Context paramContext)
  {
    paramContext = MD5.calculateMD5(a(paramContext));
    if (paramContext != null) {
      return paramContext.toUpperCase(Locale.US);
    }
    return null;
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public static String getDeviceId(Context paramContext)
  {
    SharedPreferencesManager localSharedPreferencesManager = SharedPreferencesManager.getInstance(paramContext);
    String str = localSharedPreferencesManager.getEntry("7OLmdmz8vjKtQQKjEuy0").getString("zbAsYg5vOUMV3SWbst7A");
    paramContext = str;
    if (str == null)
    {
      paramContext = a();
      localSharedPreferencesManager.getEntry("7OLmdmz8vjKtQQKjEuy0").put("zbAsYg5vOUMV3SWbst7A", paramContext).commit();
    }
    return paramContext;
  }
  
  @Nullable
  public static String getGoogleAdvertisingId(@NonNull Context paramContext)
  {
    return GooglePlayServicesHelper.getAdvertisingId(paramContext);
  }
  
  @Nullable
  public static HashSet<String> getInstalledPackageNames(Context paramContext)
  {
    localHashSet = new HashSet();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.flags & 0x1) == 0) {
          localHashSet.add(localApplicationInfo.packageName);
        }
      }
      return localHashSet;
    }
    catch (RuntimeException paramContext)
    {
      return null;
    }
  }
  
  public static List<String> getNetworkCarrierName(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getNetworkOperatorName());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager) {}
    return null;
  }
  
  public static boolean isAdTrackingEnabled(@NonNull Context paramContext)
  {
    return !GooglePlayServicesHelper.isLimitAdTrackingEnabled(paramContext);
  }
}
