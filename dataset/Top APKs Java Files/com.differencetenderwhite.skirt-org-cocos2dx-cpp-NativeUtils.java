package org.cocos2dx.cpp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.c.a.b;
import com.c.a.t;
import com.c.d.i;
import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;

public class NativeUtils
{
  private static final String UMENG_CHANNEL = "UMENG_CHANNEL";
  private static AppActivity app = null;
  
  public NativeUtils() {}
  
  private static native void CPPJsNotiFun(String paramString1, String paramString2);
  
  public static String GetUMConfig(String paramString)
  {
    return t.g(paramString);
  }
  
  public static void SetBannerOffsetForLastPosition() {}
  
  public static void SetBannerOffsetForPosition(String paramString)
  {
    t.a(paramString);
  }
  
  public static void SetIsTempVip(boolean paramBoolean)
  {
    i.a();
    app.runOnUiThread(new NativeUtils.13(paramBoolean));
  }
  
  public static void ShowShare(String paramString)
  {
    app.runOnUiThread(new NativeUtils.15(paramString));
  }
  
  public static void askAlert(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    app.runOnUiThread(new NativeUtils.9(paramInt, paramString1, paramString2, paramString4, paramString3));
  }
  
  public static void btnsAlert(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    app.runOnUiThread(new NativeUtils.11(paramString1, paramString3, paramInt1, paramInt2));
  }
  
  public static void checkCanShowFlashAds() {}
  
  public static void ctrlFbGameAd(String paramString, int paramInt1, int paramInt2)
  {
    com.red.ad.c.a(paramString, paramInt1);
  }
  
  public static void ctrlFbIconGameAd(int paramInt1, int paramInt2, int paramInt3)
  {
    com.red.ad.c.a(paramInt1, paramInt2, paramInt3);
  }
  
  public static void fbFlashIconShow(int paramInt)
  {
    com.red.ad.c.a(paramInt);
  }
  
  public static String getAppName()
  {
    Object localObject2 = null;
    try
    {
      localPackageManager = app.getApplicationContext().getPackageManager();
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException1)
    {
      for (;;)
      {
        PackageManager localPackageManager;
        ApplicationInfo localApplicationInfo;
        label26:
        Object localObject1 = null;
      }
    }
    try
    {
      localApplicationInfo = localPackageManager.getApplicationInfo(app.getPackageName(), 0);
      localObject2 = localApplicationInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      break label26;
    }
    return (String)localPackageManager.getApplicationLabel(localObject2);
  }
  
  public static String getChannelStr()
  {
    try
    {
      String str = app.getPackageManager().getApplicationInfo(app.getPackageName(), 128).metaData.get("UMENG_CHANNEL").toString();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static String getCrashKey()
  {
    return app.getResources().getString(2131099727);
  }
  
  private static String getCurVersion()
  {
    try
    {
      String str = app.getPackageManager().getPackageInfo(app.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  private static String getDeviceInfo()
  {
    return getInstallationUUID() + "_" + Build.MODEL + "_" + Build.VERSION.RELEASE;
  }
  
  public static String getFacebookGameAdContent(String paramString)
  {
    return com.red.ad.c.f(paramString);
  }
  
  public static int getFbGameAdABTestId()
  {
    return b.b();
  }
  
  public static String getInstallationUUID()
  {
    return Installation.id(app);
  }
  
  public static String getPackNameStr()
  {
    return app.getPackageName();
  }
  
  public static String getPackageName()
  {
    return Cocos2dxActivity.getContext().getPackageName();
  }
  
  public static String getUmAppKey()
  {
    return t.i();
  }
  
  public static void goMoreApp(String paramString) {}
  
  public static void goRating()
  {
    goToAppStore(app.getPackageName());
  }
  
  public static void goSocialActivity(String paramString)
  {
    app.runOnUiThread(new NativeUtils.4(paramString));
  }
  
  public static void goToAppStore(String paramString)
  {
    app.runOnUiThread(new NativeUtils.2(paramString));
  }
  
  public static void inputAlert(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt)
  {
    app.runOnUiThread(new NativeUtils.10(paramString5, paramInt, paramString1, paramString2, paramString4, paramString3));
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    List localList = app.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        localArrayList.add(((PackageInfo)localList.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isOnline()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)app.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean isSocialAvailable()
  {
    return true;
  }
  
  public static boolean isWifiConnected()
  {
    Object localObject = app.getApplicationContext();
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)((Context)localObject).getSystemService("connectivity")).getNetworkInfo(1);
      if (localObject != null) {
        return ((NetworkInfo)localObject).isAvailable();
      }
    }
    return false;
  }
  
  public static void okAlert(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    app.runOnUiThread(new NativeUtils.8(paramInt, paramString1, paramString2, paramString3));
  }
  
  public static void onEvent(String paramString)
  {
    i.a();
    t.h(paramString);
  }
  
  public static void onEvent_failLevel(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    new StringBuilder("failLevel_").append(paramInt1).append("_").append(paramInt2).append("_").append(paramString1).append("\n").append(paramString2);
    i.a();
    t.e(paramInt1 + "_" + paramInt2);
    t.a(paramInt1, paramInt2, paramString2);
  }
  
  public static void onEvent_finishLevel(int paramInt1, int paramInt2, String paramString)
  {
    new StringBuilder("finishLevel_").append(paramInt1).append("_").append(paramInt2).append("_").append(paramString);
    i.a();
    t.f(paramInt1 + "_" + paramInt2);
    t.b(paramInt1, paramInt2, "");
  }
  
  public static void onEvent_startLevel(int paramInt1, int paramInt2, String paramString)
  {
    new StringBuilder("startLevel_").append(paramInt1).append("_").append(paramInt2).append("_").append(paramString);
    i.a();
    t.d(paramInt1 + "_" + paramInt2);
    t.b(paramInt1, paramInt2);
  }
  
  public static void onOverPageStat(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      t.k();
      return;
    }
    t.j();
  }
  
  public static void openInstalledApp(String paramString1, String paramString2)
  {
    if (isAppInstalled(paramString1))
    {
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      app.startActivityForResult(localIntent, -1);
    }
  }
  
  public static void quitApp()
  {
    app.runOnUiThread(new NativeUtils.6());
  }
  
  public static void return_setGroup1_10(int paramInt)
  {
    com.red.f.c.a(paramInt);
  }
  
  public static void return_setIntSpareListForIndex(int paramInt1, int paramInt2)
  {
    com.red.f.c.a(paramInt1, paramInt2);
  }
  
  public static void setApp(AppActivity paramAppActivity)
  {
    app = paramAppActivity;
  }
  
  public static void setBananaADViewBottomOffset(int paramInt1, int paramInt2, int paramInt3)
  {
    t.a(paramInt1, paramInt2);
  }
  
  public static void setBannerShow(int paramInt)
  {
    app.runOnUiThread(new NativeUtils.16(paramInt));
  }
  
  public static void setFbGameAdABTestCount(int paramInt)
  {
    b.b(paramInt);
  }
  
  public static void setGameOverPos(String paramString)
  {
    t.c(paramString);
  }
  
  public static void setUpApplovinAd(int paramInt)
  {
    app.runOnUiThread(new NativeUtils.17(paramInt));
  }
  
  public static void setUpVungleAd(String paramString1, String paramString2)
  {
    app.runOnUiThread(new NativeUtils.18(paramString1, paramString2));
  }
  
  public static boolean showAdReturnIsButtonValid(String paramString)
  {
    return t.b(paramString);
  }
  
  public static void showEvaluateDialogForDefault() {}
  
  public static boolean showFacebookGameAd(String paramString)
  {
    return com.red.ad.c.e(paramString);
  }
  
  public static void showHighScore(int paramInt, String paramString)
  {
    app.runOnUiThread(new NativeUtils.14(paramInt, paramString));
  }
  
  public static void showModalWebView(String paramString)
  {
    app.runOnUiThread(new NativeUtils.5(paramString));
  }
  
  public static void showQuitAppDialog()
  {
    app.runOnUiThread(new NativeUtils.7());
  }
  
  public static void showTest()
  {
    app.runOnUiThread(new NativeUtils.1());
  }
  
  public static void suggestByMail(String paramString1, String paramString2, String paramString3)
  {
    app.runOnUiThread(new NativeUtils.3(paramString1, paramString2, paramString3));
  }
  
  public static void toastMessage(String paramString)
  {
    app.runOnUiThread(new NativeUtils.12(paramString));
  }
  
  public static void um_bonusItem(String paramString, int paramInt1, double paramDouble, int paramInt2)
  {
    t.a(paramDouble, paramString, paramInt1, paramInt2);
  }
  
  public static void um_useItem(String paramString, int paramInt, double paramDouble)
  {
    t.a(paramDouble, paramString, paramInt);
  }
}
