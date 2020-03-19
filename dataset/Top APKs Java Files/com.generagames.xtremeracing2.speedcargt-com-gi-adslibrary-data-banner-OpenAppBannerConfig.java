package com.gi.adslibrary.data.banner;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class OpenAppBannerConfig
  extends DefaultBannerConfig
{
  private static final String TAG = OpenAppBannerConfig.class.getSimpleName();
  private String appLauncherActivityName;
  private String appMarketUrl;
  private String appPackage;
  private List<String> apps;
  
  protected OpenAppBannerConfig(String paramString1, String paramString2, String paramString3)
  {
    super("BannerTypeLaunchApp");
    this.appPackage = paramString1;
    this.appLauncherActivityName = paramString2;
    this.appMarketUrl = paramString3;
  }
  
  public abstract Drawable getImage();
  
  public List<String> getInstalledApps(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((!paramBoolean) && (localPackageInfo.versionName == null)) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public View.OnClickListener getListener(final Context paramContext)
  {
    new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (OpenAppBannerConfig.this.isInstalled(paramContext, OpenAppBannerConfig.this.appPackage))
        {
          paramAnonymousView = paramContext.getPackageName();
          if (OpenAppBannerConfig.this.appPackage.equals(paramAnonymousView)) {}
        }
        while ((OpenAppBannerConfig.this.appMarketUrl == null) || (OpenAppBannerConfig.this.appMarketUrl.length() <= 0)) {
          try
          {
            paramAnonymousView = new Intent("android.intent.action.MAIN");
            paramAnonymousView.setComponent(new ComponentName(OpenAppBannerConfig.this.appPackage, OpenAppBannerConfig.this.appLauncherActivityName));
            paramContext.startActivity(paramAnonymousView);
            System.exit(1);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
            return;
          }
        }
        try
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(OpenAppBannerConfig.this.appMarketUrl));
          paramContext.startActivity(paramAnonymousView);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    };
  }
  
  public boolean isInstalled(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    if (this.apps == null) {
      this.apps = getInstalledApps(paramContext, false);
    }
    paramContext = this.apps.iterator();
    String str;
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
      str = (String)paramContext.next();
    } while ((paramString == null) || (!paramString.equals(str)));
    boolean bool1 = true;
    return bool1;
  }
}
