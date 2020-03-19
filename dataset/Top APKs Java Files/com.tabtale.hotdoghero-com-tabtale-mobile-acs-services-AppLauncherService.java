package com.tabtale.mobile.acs.services;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class AppLauncherService
{
  private static final String AMAZON = "amazon";
  private static final String AMAZON_PREFIX = "http://www.amazon.com/gp/mas/dl/android?p=";
  private static final String GOOGLE = "google";
  private static final String GOOGLE_PREFIX = "https://play.google.com/store/apps/details?id=";
  private static final String SPECIFIC_CONFIG_PATH = "specificConfigPath";
  private static final String STORE = "store";
  @Inject
  ConfigurationService configurationService;
  List<PackageInfo> mPacks;
  Set<String> mPacksSet;
  BaseActivity mainActivity;
  
  public AppLauncherService() {}
  
  private String getStoreURL()
  {
    String str1 = "";
    String str2 = str1;
    String str3;
    if (this.configurationService != null)
    {
      str2 = this.configurationService.get("specificConfigPath");
      str3 = this.configurationService.get("store");
      if (!str3.equals("google")) {
        break label65;
      }
      str1 = "https://play.google.com/store/apps/details?id=";
    }
    for (;;)
    {
      str2 = str1 + str2;
      return str2;
      label65:
      if (str3.equals("amazon")) {
        str1 = "http://www.amazon.com/gp/mas/dl/android?p=";
      }
    }
  }
  
  private void openStoreURL(String paramString)
  {
    if ((paramString.startsWith("http")) || (paramString.startsWith("amzn")) || (paramString.startsWith("play")))
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setFlags(268435456);
      this.mainActivity.startActivity(paramString);
    }
    while (!this.mainActivity.isLogEnable()) {
      return;
    }
    System.out.println("OpenLocalApp did not receive a valid address");
  }
  
  public void OpenApp(String paramString1, String paramString2)
  {
    OpenAppImpl(paramString1, paramString2);
  }
  
  public void OpenAppImpl(String paramString1, String paramString2)
  {
    if (this.mainActivity.isLogEnable()) {
      System.out.println("java OpenApp appName, appURL: ---> " + paramString1 + " " + paramString2);
    }
    if (isLocalApp(paramString1) == true)
    {
      Intent localIntent = this.mainActivity.getPackageManager().getLaunchIntentForPackage(paramString1);
      this.mainActivity.startActivity(localIntent);
    }
    for (;;)
    {
      if (this.mainActivity.isLogEnable()) {
        System.out.println("java OpenApp appName, appURL: <--- " + paramString1 + " " + paramString2);
      }
      return;
      openStoreURL(paramString2);
    }
  }
  
  public String getAppVersion()
  {
    String str = "";
    if (this.mainActivity != null) {}
    try
    {
      str = this.mainActivity.getPackageManager().getPackageInfo(this.mainActivity.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public boolean isLocalApp(String paramString)
  {
    if (this.mainActivity.isLogEnable()) {
      System.out.println("java isLocalApp appName: ---> " + paramString);
    }
    if (paramString.equals("")) {}
    do
    {
      return false;
      if (this.mPacksSet.contains(paramString))
      {
        if (this.mainActivity.isLogEnable()) {
          System.out.println("java isLocalApp <--- true " + paramString);
        }
        return true;
      }
    } while (!this.mainActivity.isLogEnable());
    System.out.println("java isLocalApp <--- false " + paramString);
    return false;
  }
  
  public void openStoreApp()
  {
    openStoreURL(getStoreURL());
  }
  
  public void refreshInstalledPackages()
  {
    PackageManager localPackageManager = this.mainActivity.getPackageManager();
    try
    {
      this.mPacks = localPackageManager.getInstalledPackages(0);
      if (this.mPacks == null) {
        return;
      }
    }
    catch (Exception localException)
    {
      do
      {
        for (;;)
        {
          System.out.println("refreshInstalledPackages Exception: " + localException.getMessage());
        }
        this.mPacksSet = new HashSet();
        int i = 0;
        while (i < this.mPacks.size())
        {
          String str = ((PackageInfo)this.mPacks.get(i)).packageName;
          if (str.contains("com.tabtale"))
          {
            if (this.mainActivity.isLogEnable()) {
              System.out.println("java " + str + " true");
            }
            this.mPacksSet.add(str);
          }
          i += 1;
        }
      } while (!this.mainActivity.isLogEnable());
      System.out.println("java setMainActivity: prepared tabtale apps list");
    }
  }
  
  public void setMainActivity(BaseActivity paramBaseActivity)
  {
    this.mainActivity = paramBaseActivity;
    refreshInstalledPackages();
  }
}
