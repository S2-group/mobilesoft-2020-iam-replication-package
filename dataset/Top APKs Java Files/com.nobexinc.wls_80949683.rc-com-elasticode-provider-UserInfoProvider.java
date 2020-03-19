package com.elasticode.provider;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.elasticode.utils.Utils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public class UserInfoProvider
{
  private static final String FIRST_LAUNCH_PREFS_KEY = "FirstLaunchPrefsKey";
  private static final String OLD_VERSION_PREFS_KEY = "OldVersionPrefsKey";
  private static final String PREFS = "ElasticodeUserInfoPrefs";
  private static final String TAG = UserInfoProvider.class.getSimpleName();
  private Activity context;
  
  public UserInfoProvider(Activity paramActivity)
  {
    this.context = paramActivity;
  }
  
  public String getAppBundleIdentifier()
  {
    try
    {
      String str = this.context.getPackageName();
      return str;
    }
    catch (Exception localException)
    {
      Log.d(TAG, "Failed to get package name");
    }
    return null;
  }
  
  public int getBatteryLevel()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    return this.context.registerReceiver(null, localIntentFilter).getIntExtra("level", -1);
  }
  
  public String getDeviceBrandName()
  {
    return Build.MANUFACTURER;
  }
  
  public String getDeviceId()
  {
    SharedPreferences localSharedPreferences = this.context.getSharedPreferences("device_id.xml", 0);
    String str1 = localSharedPreferences.getString("device_id", null);
    String str2;
    if (str1 == null)
    {
      str1 = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
      try
      {
        if (!"9774d56d682e549c".equals(str1)) {}
        for (str1 = UUID.nameUUIDFromBytes(str1.getBytes("utf8")).toString();; str1 = UUID.randomUUID().toString())
        {
          localSharedPreferences.edit().putString("device_id", str1).commit();
          return str1;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        for (;;)
        {
          Log.e(TAG, "Cannot generate deviceId from bytes. Creating random UUID");
          str2 = UUID.randomUUID().toString();
        }
      }
    }
    return str2;
  }
  
  public String getDeviceLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public String getDeviceModel()
  {
    return Build.MODEL;
  }
  
  public Map<String, String> getEc3PA(Map<String, ElasticodeBlock> paramMap)
  {
    HashMap localHashMap2 = null;
    HashMap localHashMap1 = localHashMap2;
    if (paramMap != null)
    {
      localHashMap1 = localHashMap2;
      if (!paramMap.isEmpty())
      {
        localHashMap2 = new HashMap();
        paramMap = paramMap.keySet().iterator();
        for (;;)
        {
          localHashMap1 = localHashMap2;
          if (!paramMap.hasNext()) {
            break;
          }
          localHashMap2.put((String)paramMap.next(), "");
        }
      }
    }
    return localHashMap1;
  }
  
  public int getFreeInternalStorage()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    if (Build.VERSION.SDK_INT < 18)
    {
      float f1 = localStatFs.getBlockSize();
      float f2 = localStatFs.getBlockCount();
      return (int)(localStatFs.getFreeBlocks() * f1 / (f2 * f1) * 100.0F);
    }
    return (int)((float)localStatFs.getFreeBytes() / (float)localStatFs.getTotalBytes() * 100.0F);
  }
  
  public List<String> getInstalledAppsList()
  {
    Object localObject = this.context.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    if (localObject != null)
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(128);
      int i = 0;
      while (i < ((List)localObject).size())
      {
        if ((((ApplicationInfo)((List)localObject).get(i)).flags & 0x1) == 0) {
          localArrayList.add(((ApplicationInfo)((List)localObject).get(i)).packageName);
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public String getNetworkType()
  {
    Object localObject2 = null;
    try
    {
      Object localObject3 = (ConnectivityManager)this.context.getSystemService("connectivity");
      Object localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject3 = ((ConnectivityManager)localObject3).getActiveNetworkInfo();
        localObject1 = localObject2;
        if (localObject3 != null) {
          localObject1 = ((NetworkInfo)localObject3).getTypeName();
        }
      }
      return localObject1;
    }
    catch (Exception localException)
    {
      Log.e(TAG, "Failed to get network type");
    }
    return null;
  }
  
  public String getOldAppVersion()
  {
    return this.context.getSharedPreferences("ElasticodeUserInfoPrefs", 0).getString("OldVersionPrefsKey", null);
  }
  
  public String getResolution()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    Display localDisplay = this.context.getWindowManager().getDefaultDisplay();
    new Point();
    if (Build.VERSION.SDK_INT >= 17) {
      localDisplay.getRealMetrics(localDisplayMetrics);
    }
    for (;;)
    {
      return String.valueOf(localDisplayMetrics.widthPixels) + "x" + String.valueOf(localDisplayMetrics.heightPixels);
      localDisplay.getMetrics(localDisplayMetrics);
    }
  }
  
  public int getScreenBrightness()
  {
    int i = -1;
    try
    {
      int j = Settings.System.getInt(this.context.getContentResolver(), "screen_brightness");
      i = j;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      for (;;)
      {
        Log.e(TAG, "Failed to get screen brightness");
      }
    }
    return (int)(i / 255.0F) * 100;
  }
  
  public String getSystemVersion()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public String getTimezone()
  {
    TimeZone localTimeZone = TimeZone.getDefault();
    return Integer.toString(localTimeZone.getOffset(GregorianCalendar.getInstance(localTimeZone).getTimeInMillis()) / 3600000);
  }
  
  public String getTotalStorageSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    long l;
    if (Build.VERSION.SDK_INT < 18) {
      l = localStatFs.getBlockSize();
    }
    for (int i = (int)(localStatFs.getBlockCount() * l);; i = (int)((float)localStatFs.getTotalBytes() / 1024.0F)) {
      return String.valueOf(i / 1024 / 1024) + "GB";
    }
  }
  
  public String getVersionCode()
  {
    int i = -1;
    try
    {
      int j = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
      i = j;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Log.d(TAG, "Failed to get version code");
      }
    }
    return String.valueOf(i);
  }
  
  public String getVersionName()
  {
    try
    {
      String str = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e(TAG, "Failed to get version name");
    }
    return null;
  }
  
  public boolean isDeviceRooted()
  {
    return Utils.findBinary("su");
  }
  
  public boolean isFirstLaunch()
  {
    SharedPreferences localSharedPreferences = this.context.getSharedPreferences("ElasticodeUserInfoPrefs", 0);
    if (localSharedPreferences.getBoolean("FirstLaunchPrefsKey", true))
    {
      localSharedPreferences.edit().putBoolean("FirstLaunchPrefsKey", false).commit();
      return true;
    }
    return false;
  }
  
  public boolean isHeadphonesPluggedIn()
  {
    AudioManager localAudioManager = (AudioManager)this.context.getSystemService("audio");
    return (localAudioManager.isBluetoothA2dpOn()) || (localAudioManager.isWiredHeadsetOn());
  }
  
  public boolean isInstalledFromGooglePlay(String paramString)
  {
    return "com.android.vending".equals(this.context.getPackageManager().getInstallerPackageName(paramString));
  }
  
  public boolean isPowerCableConnected()
  {
    try
    {
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
      int i = this.context.registerReceiver(null, localIntentFilter).getIntExtra("status", -1);
      return i == 2;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public boolean isVersionChanged()
  {
    String str1 = this.context.getSharedPreferences("ElasticodeUserInfoPrefs", 0).getString("OldVersionPrefsKey", null);
    String str2 = getVersionName();
    if (str1 == null) {}
    while (!str1.equals(str2)) {
      return true;
    }
    return false;
  }
  
  public void saveOldVersion()
  {
    SharedPreferences localSharedPreferences = this.context.getSharedPreferences("ElasticodeUserInfoPrefs", 0);
    String str = getVersionName();
    localSharedPreferences.edit().putString("OldVersionPrefsKey", str).commit();
  }
}
