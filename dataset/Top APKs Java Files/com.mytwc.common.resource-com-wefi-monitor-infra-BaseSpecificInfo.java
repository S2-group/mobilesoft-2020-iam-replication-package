package com.wefi.monitor.infra;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.format.Formatter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public abstract class BaseSpecificInfo
  implements SpecificAppInfo
{
  private static final int CHECK_ALIVE_INTERVAL_IN_MINUTES = 5;
  private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
  private static final String EXTERNAL_MEMORY = "External";
  private static final String INTERNAL_MEMORY = "Internal";
  protected static final String LINE_SEPERATOR = "\n";
  private static final String LOG_DIRECTORY = "MonitorLogs";
  private static final int MAX_LOG_FILE_NUM = 4;
  private static final int MAX_ZIP_FILE_NUM = 20;
  private static final String WEFI_KNOWN_ACTIVITY = ".engine.ListViewActivity";
  private static final String WEFI_PREFIX_PACKAGE_NAME = "com.wefi";
  private static final String ZIP_DIRECTORY = "/wefi/log/";
  ContentResolver con = getContext().getContentResolver();
  
  public BaseSpecificInfo() {}
  
  private List<String> GetWeFiPackages(List<PackageInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList.next();
      if (localPackageInfo.applicationInfo.packageName.startsWith("com.wefi")) {
        localArrayList.add(localPackageInfo.applicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  private String getCurrentTime()
  {
    Calendar localCalendar = Calendar.getInstance();
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localCalendar.getTime());
  }
  
  private String getFreeMemory(String paramString)
  {
    if (paramString.equals("Internal")) {}
    for (Object localObject = Environment.getDataDirectory();; localObject = Environment.getExternalStorageDirectory())
    {
      localObject = new StatFs(((File)localObject).getPath());
      long l1 = ((StatFs)localObject).getAvailableBlocks();
      long l2 = ((StatFs)localObject).getBlockSize();
      localObject = Formatter.formatFileSize(getContext(), l1 * l2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString + " memory available: ").append((String)localObject).append("\n");
      return localStringBuilder.toString();
    }
  }
  
  private String getLocationProvidersAllowed()
  {
    return Settings.Secure.getString(getContext().getContentResolver(), "location_providers_allowed");
  }
  
  private boolean isExternalMemPresent()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public int checkAliveIntervalInMinutes()
  {
    return 5;
  }
  
  public String getAndroidID()
  {
    return Settings.Secure.getString(this.con, "android_id");
  }
  
  public boolean getBackgroundData()
  {
    return ((ConnectivityManager)getContext().getSystemService("connectivity")).getBackgroundDataSetting();
  }
  
  public File getLogDirectory()
  {
    return getContext().getDir("MonitorLogs", 1);
  }
  
  public String getMAC()
  {
    return ((WifiManager)getContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }
  
  public String getMailBody()
  {
    StringBuilder localStringBuilder = new StringBuilder(1200);
    localStringBuilder.append("Please tell us what happened:");
    localStringBuilder.append("\n\n\n\n\n");
    localStringBuilder.append("Some data about this device:");
    localStringBuilder.append("\n\n");
    localStringBuilder.append("Wefi Version: ");
    localStringBuilder.append(getWefiVersion()).append("\n");
    localStringBuilder.append("Created Time: ");
    localStringBuilder.append(getCurrentTime()).append("\n");
    localStringBuilder.append("Manufacture: ");
    localStringBuilder.append(Build.MANUFACTURER).append("\n");
    localStringBuilder.append("Device: ");
    localStringBuilder.append(Build.DEVICE).append("\n");
    localStringBuilder.append("Model: ");
    localStringBuilder.append(Build.MODEL).append("\n");
    localStringBuilder.append("Product: ");
    localStringBuilder.append(Build.PRODUCT).append("\n");
    localStringBuilder.append("TAGS: ");
    localStringBuilder.append(Build.TAGS).append("\n");
    localStringBuilder.append("Display: ");
    localStringBuilder.append(Build.DISPLAY).append("\n");
    localStringBuilder.append("Firmware Version: ");
    localStringBuilder.append(Build.VERSION.RELEASE).append("\n");
    localStringBuilder.append("SDK: ");
    localStringBuilder.append(Build.VERSION.SDK).append("\n");
    localStringBuilder.append("Wifi Sleep Policy: ");
    localStringBuilder.append(getWifiSleepPolicy()).append("\n");
    localStringBuilder.append("Location Providers Allowed: ");
    localStringBuilder.append(getLocationProvidersAllowed()).append("\n");
    localStringBuilder.append("ADB enabled: ");
    localStringBuilder.append(getSettings("adb_enabled")).append("\n");
    localStringBuilder.append("Background data: ");
    localStringBuilder.append(getBackgroundData()).append("\n");
    localStringBuilder.append("Bluetooth: ");
    localStringBuilder.append(getSettings("bluetooth_on")).append("\n");
    localStringBuilder.append("Data Roaming: ");
    localStringBuilder.append(getSettings("data_roaming")).append("\n");
    localStringBuilder.append(getFreeMemory("Internal"));
    localStringBuilder.append("External memory found: ");
    localStringBuilder.append(isExternalMemPresent()).append("\n");
    localStringBuilder.append(getFreeMemory("External"));
    return localStringBuilder.toString();
  }
  
  public int getMaxLogFiles()
  {
    return 4;
  }
  
  public int getMaxZipFiles()
  {
    return 20;
  }
  
  public String getSettings(String paramString)
  {
    paramString = Settings.Secure.getString(this.con, paramString);
    if (paramString == null) {
      return "Unknown";
    }
    if (paramString.equals("1")) {
      return "true";
    }
    return "false";
  }
  
  protected String getWefiVersion()
  {
    String str1 = "WeFi application is not installed";
    PackageManager localPackageManager = getContext().getPackageManager();
    Object localObject2 = GetWeFiPackages(localPackageManager.getInstalledPackages(0));
    Object localObject1 = null;
    Iterator localIterator = ((List)localObject2).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        try
        {
          localObject2 = localPackageManager.getPackageInfo(str2, 1);
          localObject1 = localObject2;
          if (((PackageInfo)localObject2).activities != null)
          {
            ActivityInfo[] arrayOfActivityInfo = ((PackageInfo)localObject2).activities;
            int j = arrayOfActivityInfo.length;
            int i = 0;
            for (;;)
            {
              localObject1 = localObject2;
              if (i >= j) {
                break;
              }
              localObject1 = arrayOfActivityInfo[i];
              if ((localObject1.packageName.equals(str2)) && (localObject1.name.indexOf(".engine.ListViewActivity") != -1))
              {
                str1 = ((PackageInfo)localObject2).versionName;
                localObject1 = localObject2;
                break;
              }
              i += 1;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            Object localObject3 = localObject1;
          }
        }
      }
    }
    return str1;
  }
  
  public String getWifiSleepPolicy()
  {
    try
    {
      int i = Settings.System.getInt(getContext().getContentResolver(), "wifi_sleep_policy");
      switch (i)
      {
      default: 
        return "";
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      return "Sleep Policy not found";
    }
    return "STAY_ON_WHILE_PLUGGED_IN";
    return "NEVER_WHILE_PLUGGED";
    return "NEVER";
  }
  
  public File getZipDirectory()
  {
    return new File(Environment.getExternalStorageDirectory(), "/wefi/log/");
  }
  
  public void sendLogMail(Handler paramHandler, File paramFile)
  {
    new Thread(new SendLogsTask(paramHandler, this, paramFile)).run();
  }
  
  public void sendLogMail(File paramFile)
  {
    new Thread(new SendLogsTask(this, paramFile)).run();
  }
}
