package com.wisesharksoftware.app_photoeditor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.flurry.android.FlurryAgent;
import com.wisesharksoftware.billing.v3.BillingActivity;
import com.wisesharksoftware.core.CopyAssetsAsyncTask;
import com.wisesharksoftware.core.opencv.OpenCVLoader;
import com.wisesharksoftware.lib.ExceptionHandler;
import com.wisesharksoftware.lib.MarketingHelper;
import com.wisesharksoftware.lib.SettingsHelper;
import com.wisesharksoftware.lib.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SplashActivity
  extends BillingActivity
{
  public static boolean anyPackPurchased = false;
  
  static
  {
    if (!OpenCVLoader.initDebug()) {
      Utils.reportFlurryEvent("OpenCVLoader", "ERROR");
    } else {
      Utils.reportFlurryEvent("OpenCVLoader", "OK");
    }
    try
    {
      System.loadLibrary("processing");
      return;
    }
    catch (Error localError)
    {
      localError.printStackTrace();
      new ExceptionHandler(localError, "LoadLibrary");
    }
  }
  
  public SplashActivity() {}
  
  public static boolean checkAppOfTheDayUnlocked(Context paramContext)
  {
    if (getAppOfTheDayUnlocked(paramContext) == true) {
      return true;
    }
    if (SettingsHelper.getBoolean(paramContext, "APP_ALREADY_INSTALLED", Boolean.valueOf(false)).booleanValue()) {
      return false;
    }
    SettingsHelper.setBoolean(paramContext, "APP_ALREADY_INSTALLED", Boolean.valueOf(true));
    if (paramContext.getResources().getBoolean(2130968581))
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")))
        {
          setAppOfTheDayUnlocked(paramContext, true);
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public static boolean getAppOfTheDayUnlocked(Context paramContext)
  {
    return paramContext.getSharedPreferences("appoftheday", 0).getBoolean("appoftheday_result", false);
  }
  
  public static Class getHomeScreenClass(Context paramContext)
  {
    paramContext = paramContext.getResources().getString(2131493028);
    if (paramContext.equals("promoteapps")) {
      return ShareHomeScreenActivity.class;
    }
    if (paramContext.equals("hdr")) {
      return HomeScreenActivity.class;
    }
    return GallerySplashActivity.class;
  }
  
  private boolean isPromoAppAlreadyChecked()
  {
    return getSharedPreferences("promo_app", 0).getBoolean("promo_app_installed", false);
  }
  
  private boolean isPromoAppInstalled()
  {
    if (isPromoAppAlreadyChecked()) {
      return true;
    }
    int i = getResources().getIdentifier("promo_app_packagename", "string", getPackageName());
    if (i == 0) {
      return false;
    }
    Object localObject = getResources().getString(i);
    if (localObject != null)
    {
      if (((String)localObject).equals("")) {
        return false;
      }
      boolean bool;
      if (getPackageManager().getLaunchIntentForPackage((String)localObject) != null) {
        bool = true;
      } else {
        bool = false;
      }
      if (bool)
      {
        localObject = getSharedPreferences("promo_app", 0).edit();
        ((SharedPreferences.Editor)localObject).putBoolean("promo_app_installed", true);
        ((SharedPreferences.Editor)localObject).commit();
      }
      return bool;
    }
    return false;
  }
  
  public static void setAppOfTheDayUnlocked(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("appoftheday", 0).edit();
    paramContext.putBoolean("appoftheday_result", paramBoolean);
    paramContext.commit();
  }
  
  private void startMainActivity()
  {
    startActivity(new Intent(this, getHomeScreenClass(getApplicationContext())));
  }
  
  public boolean IsAdsHidden()
  {
    boolean bool1 = getAppOfTheDayUnlocked(getApplicationContext());
    boolean bool2 = true;
    if (bool1) {
      return true;
    }
    if (!getResources().getBoolean(2130968588)) {
      return true;
    }
    bool1 = bool2;
    if (!isFullVersion())
    {
      bool1 = bool2;
      if (!ChooseProcessingActivity.isItemPurchased(this, "remove_ads"))
      {
        bool1 = bool2;
        if (!MarketingHelper.isTrialPeriod(this))
        {
          bool1 = bool2;
          if (Build.VERSION.SDK_INT >= 11)
          {
            bool1 = bool2;
            if (!isPromoAppInstalled())
            {
              bool1 = bool2;
              if (!isFacebookPosted())
              {
                bool1 = bool2;
                if (getProductIds().size() <= 0)
                {
                  if (anyPackPurchased) {
                    return true;
                  }
                  bool1 = false;
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public boolean getAppOfTheDaySplashShowed(Context paramContext)
  {
    return paramContext.getSharedPreferences("appoftheday", 0).getBoolean("appoftheday_splash", false);
  }
  
  public boolean getCopyFilesResult()
  {
    return getSharedPreferences("copy_files", 0).getBoolean("copy_files_result", true);
  }
  
  protected String getFlurryKey()
  {
    return getString(2131493021);
  }
  
  protected String getItemPurchasedNotification(String paramString, boolean paramBoolean)
  {
    return null;
  }
  
  protected int getLandscapeLayout()
  {
    return 2131361862;
  }
  
  public int getLayoutResource()
  {
    if ((!isPromoAppAlreadyChecked()) && (isPromoAppInstalled())) {
      return 2131361861;
    }
    if ((getAppOfTheDayUnlocked(getApplicationContext())) && (!getAppOfTheDaySplashShowed(getApplicationContext()))) {
      return 2131361860;
    }
    return 2131361862;
  }
  
  protected int getPortraitLayout()
  {
    return 2131361862;
  }
  
  public List<String> getProductIds()
  {
    int i = 0;
    SharedPreferences localSharedPreferences = getSharedPreferences("productIds", 0);
    ArrayList localArrayList = new ArrayList();
    int j = localSharedPreferences.getInt("ProductIdsCount", 0);
    while (i < j)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("productId");
      localStringBuilder.append(i);
      localArrayList.add(localSharedPreferences.getString(localStringBuilder.toString(), ""));
      i += 1;
    }
    return localArrayList;
  }
  
  protected View getRemoveAdsButton()
  {
    return null;
  }
  
  protected String getRemoveAdsPurchaseId()
  {
    return "remove_ads";
  }
  
  protected int getRootLayout()
  {
    return 2131361862;
  }
  
  public boolean isFacebookPosted()
  {
    boolean bool = getSharedPreferences("facebook", 0).getBoolean("facebook_posted", false);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("facebook_posted = ");
    localStringBuilder.append(bool);
    Log.d("facebook", localStringBuilder.toString());
    return bool;
  }
  
  protected boolean isFullVersion()
  {
    return getPackageName().contains("full");
  }
  
  public boolean isNewVersion()
  {
    String str1 = SettingsHelper.getString(this, "last_version", "");
    try
    {
      String str2 = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      if (str1.equals(str2)) {
        return false;
      }
      SettingsHelper.setString(this, "last_version", str2);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return true;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    checkAppOfTheDayUnlocked(getApplicationContext());
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    super.onCreate(paramBundle);
    setContentView(getLayoutResource());
    FlurryAgent.logEvent("Splash");
    int i = getResources().getIdentifier("progress_anim", "anim", getPackageName());
    if (i != 0)
    {
      paramBundle = AnimationUtils.loadAnimation(this, i);
      paramBundle.setDuration(1000L);
      ((ImageView)findViewById(2131230947)).startAnimation(paramBundle);
    }
    if (getApplicationContext().getExternalFilesDir(null) == null) {
      FlurryAgent.logEvent("ExternalFilesDirNotExist");
    }
    if ((!isNewVersion()) && (getCopyFilesResult()))
    {
      FlurryAgent.endTimedEvent("Splash");
      FlurryAgent.logEvent("NoNeedCopyAssets");
      startMainActivity();
      finish();
      return;
    }
    new CopyAssetsAsyncTask(getApplicationContext())
    {
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        super.onPostExecute(paramAnonymousBoolean);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("postexecute result = ");
        localStringBuilder.append(paramAnonymousBoolean);
        Log.d("AssetsUtils", localStringBuilder.toString());
        FlurryAgent.endTimedEvent("Splash");
        Utils.reportFlurryEvent("CopyAssetsResult", paramAnonymousBoolean.toString());
        SplashActivity.this.setCopyFilesResult(paramAnonymousBoolean.booleanValue());
        if ((SplashActivity.getAppOfTheDayUnlocked(SplashActivity.this.getApplicationContext())) && (!SplashActivity.this.getAppOfTheDaySplashShowed(SplashActivity.this.getApplicationContext()))) {
          SplashActivity.this.setAppOfTheDaySplashShowed(SplashActivity.this.getApplicationContext());
        }
        if (paramAnonymousBoolean.booleanValue())
        {
          SplashActivity.this.startMainActivity();
          SplashActivity.this.finish();
          return;
        }
        Toast.makeText(SplashActivity.this.getApplicationContext(), "free disk space, please!", 1).show();
        SplashActivity.this.startMainActivity();
        SplashActivity.this.finish();
      }
    }.execute(new Void[0]);
  }
  
  protected void onItemPurchased(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {
      anyPackPurchased = true;
    }
  }
  
  public void onPause()
  {
    Utils.reportFlurryEvent("onPause", toString());
    super.onPause();
  }
  
  protected void onRestoreFinished() {}
  
  public void onResume()
  {
    Utils.reportFlurryEvent("onResume", toString());
    super.onResume();
  }
  
  public void onStart()
  {
    super.onStart();
    FlurryAgent.onStartSession(getApplicationContext(), getString(2131493021));
    MarketingHelper.reportRetantion(this, null);
  }
  
  public void onStop()
  {
    super.onStop();
    FlurryAgent.onEndSession(getApplicationContext());
  }
  
  public void setAppOfTheDaySplashShowed(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("appoftheday", 0).edit();
    paramContext.putBoolean("appoftheday_splash", true);
    paramContext.commit();
  }
  
  public void setCopyFilesResult(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("copy_files", 0).edit();
    localEditor.putBoolean("copy_files_result", paramBoolean);
    localEditor.commit();
  }
}
