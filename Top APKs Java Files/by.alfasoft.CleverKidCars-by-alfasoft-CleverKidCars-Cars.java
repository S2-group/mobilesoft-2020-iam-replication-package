package by.alfasoft.CleverKidCars;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import by.alfasoft.CleverKidCars.Billing.IabException;
import by.alfasoft.CleverKidCars.Billing.IabHelper;
import by.alfasoft.CleverKidCars.Billing.IabHelper.OnConsumeFinishedListener;
import by.alfasoft.CleverKidCars.Billing.IabHelper.OnIabPurchaseFinishedListener;
import by.alfasoft.CleverKidCars.Billing.IabHelper.OnIabSetupFinishedListener;
import by.alfasoft.CleverKidCars.Billing.IabHelper.QueryInventoryFinishedListener;
import by.alfasoft.CleverKidCars.Billing.IabResult;
import by.alfasoft.CleverKidCars.Billing.Inventory;
import by.alfasoft.CleverKidCars.Billing.Purchase;
import by.alfasoft.CleverKidCars.Billing.SkuDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.Tracker;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxHelper;

public class Cars
  extends Cocos2dxActivity
{
  private static final int CAMERA_REQUEST_ID = 1001;
  static AdView adView;
  static InterstitialAd fullAdView;
  static IabHelper iAbHelper = null;
  private static Boolean isInventoryQuerying;
  static IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener()
  {
    public void onConsumeFinished(Purchase paramAnonymousPurchase, IabResult paramAnonymousIabResult)
    {
      Log.d("CleverKidCars", "Consumption finished. Purchase: " + paramAnonymousPurchase + ", result: " + paramAnonymousIabResult);
      if (paramAnonymousIabResult.isSuccess()) {
        Log.d("CleverKidCars", "Consumption successful. Provisioning.");
      }
      for (;;)
      {
        Log.d("CleverKidCars", "End consumption flow.");
        return;
        Cars.complain("Error while consuming: " + paramAnonymousIabResult);
      }
    }
  };
  static Context mContext;
  static IabHelper.QueryInventoryFinishedListener mGotInventoryListener;
  static IabHelper.OnIabSetupFinishedListener mIabSetupFinishedListener;
  private static boolean mIsSurfaceCreated;
  static IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
  static Runnable mResumeRunner;
  static SurfaceHolder.Callback mSurfaceHolderCallback;
  static boolean needResumeRunner;
  private static SharedPreferences preferences;
  private static int purchaseProceedStatus;
  private static int queryInventoryStatus = 2;
  static Activity self;
  private static Boolean startQueryAsync;
  private RelativeLayout mRelativeBannerContainer;
  
  static
  {
    purchaseProceedStatus = 2;
    isInventoryQuerying = Boolean.valueOf(false);
    startQueryAsync = Boolean.valueOf(false);
    mResumeRunner = null;
    needResumeRunner = false;
    mIsSurfaceCreated = false;
    mSurfaceHolderCallback = new SurfaceHolder.Callback()
    {
      public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        Log.d("surfaceholder", "changed");
      }
      
      public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
      {
        Log.d("surfaceholder", "created");
        Cars.mIsSurfaceCreated = true;
        if ((Cars.mResumeRunner != null) && (Cars.needResumeRunner)) {
          Cars.mResumeRunner.run();
        }
      }
      
      public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
      {
        Log.d("surfaceholder", "destroyed");
        Cars.mIsSurfaceCreated = false;
      }
    };
    mIabSetupFinishedListener = new IabHelper.OnIabSetupFinishedListener()
    {
      public void onIabSetupFinished(IabResult arg1)
      {
        Log.d("CleverKidCars", "Setup finished.");
        if (!???.isSuccess())
        {
          Cars.complain("Problem setting up in-app billing: " + ???);
          Cars.isInventoryQuerying = Boolean.valueOf(false);
          return;
        }
        if (Cars.iAbHelper == null)
        {
          Cars.isInventoryQuerying = Boolean.valueOf(false);
          return;
        }
        synchronized (Cars.startQueryAsync)
        {
          Cars.queryInventoryStatus = 2;
          if (Cars.startQueryAsync.booleanValue()) {
            return;
          }
        }
        Cars.startQueryAsync = Boolean.valueOf(true);
        Log.d("CleverKidCars", "Setup successful. Querying inventory.");
        Cars.iAbHelper.queryInventoryAsync(Cars.mGotInventoryListener);
      }
    };
    mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener()
    {
      public void onQueryInventoryFinished(IabResult paramAnonymousIabResult, Inventory paramAnonymousInventory)
      {
        Log.d("CleverKidCars", "Query inventory finished.");
        Cars.startQueryAsync = Boolean.valueOf(false);
        if (Cars.iAbHelper == null)
        {
          Cars.queryInventoryStatus = 1;
          Cars.isInventoryQuerying = Boolean.valueOf(false);
          return;
        }
        if (paramAnonymousIabResult.isFailure())
        {
          Cars.queryInventoryStatus = 1;
          Cars.complain("Failed to query inventory: " + paramAnonymousIabResult);
          Cars.isInventoryQuerying = Boolean.valueOf(false);
          return;
        }
        if (Cars.preferences == null)
        {
          Cars.queryInventoryStatus = 1;
          Cars.isInventoryQuerying = Boolean.valueOf(false);
          return;
        }
        paramAnonymousIabResult = Cars.preferences.edit();
        Log.d("CleverKidCars", "Query inventory was successful.");
        Object localObject = (HashMap)ResourceUtil.getHashMapResource(Cars.mContext, 2130903040);
        if (localObject == null)
        {
          Cars.queryInventoryStatus = 1;
          Cars.isInventoryQuerying = Boolean.valueOf(false);
          return;
        }
        localObject = ((HashMap)localObject).entrySet().iterator();
        for (;;)
        {
          if (!((Iterator)localObject).hasNext())
          {
            paramAnonymousIabResult.commit();
            Log.d("CleverKidCars", "Initial inventory query finished; enabling main UI.");
            Cars.queryInventoryStatus = 0;
            Cars.isInventoryQuerying = Boolean.valueOf(false);
            return;
          }
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
          Log.d("CleverKidCars", "Checking Purchase is " + (String)localEntry.getKey() + ".");
          Purchase localPurchase = paramAnonymousInventory.getPurchase((String)localEntry.getKey());
          if ((localPurchase != null) && (localPurchase.getPurchaseState() == 0)) {
            if ((localPurchase.getSku().equals("brandcorporative")) || (localPurchase.getSku().equals("brandpersonal")))
            {
              Cars.iAbHelper.consumeAsync(localPurchase, Cars.mConsumeFinishedListener);
            }
            else
            {
              Log.d("CleverKidCars", "User already have " + (String)localEntry.getKey() + ".");
              paramAnonymousIabResult.putBoolean((String)localEntry.getValue(), true);
            }
          }
        }
      }
    };
    mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener()
    {
      public void onIabPurchaseFinished(IabResult paramAnonymousIabResult, Purchase paramAnonymousPurchase)
      {
        Log.d("CleverKidCars", "Purchase finished: " + paramAnonymousIabResult + ", purchase: " + paramAnonymousPurchase);
        if (Cars.iAbHelper == null) {
          Cars.purchaseProceedStatus = 1;
        }
        do
        {
          return;
          if (paramAnonymousIabResult.isFailure())
          {
            Cars.purchaseProceedStatus = 1;
            Cars.complain("Error purchasing: " + paramAnonymousIabResult);
            return;
          }
          if (!BillingHelper.verifyDeveloperPayload(paramAnonymousPurchase))
          {
            Cars.purchaseProceedStatus = 1;
            Cars.complain("Error purchasing. Authenticity verification failed.");
            return;
          }
          paramAnonymousIabResult = Cars.preferences.edit();
          localObject = (HashMap)ResourceUtil.getHashMapResource(Cars.mContext, 2130903040);
        } while (localObject == null);
        Object localObject = ((HashMap)localObject).entrySet().iterator();
        if (!((Iterator)localObject).hasNext()) {}
        for (;;)
        {
          paramAnonymousIabResult.commit();
          Cars.purchaseProceedStatus = 0;
          return;
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
          if ((!((String)localEntry.getKey()).equals(paramAnonymousPurchase.getSku())) || (paramAnonymousPurchase.getPurchaseState() != 0)) {
            break;
          }
          Log.d("CleverKidCars", "Purchase successful.");
          Log.d("CleverKidCars", "Purchase is " + (String)localEntry.getValue() + ". Congratulating user.");
          paramAnonymousIabResult.putBoolean((String)localEntry.getValue(), true);
          if ((paramAnonymousPurchase.getSku().equals("brandcorporative")) || (paramAnonymousPurchase.getSku().equals("brandpersonal"))) {
            Cars.iAbHelper.consumeAsync(paramAnonymousPurchase, Cars.mConsumeFinishedListener);
          }
        }
      }
    };
  }
  
  public Cars() {}
  
  static void alert(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(mContext);
    localBuilder.setMessage(paramString);
    localBuilder.setNeutralButton("OK", null);
    Log.d("CleverKidCars", "Showing alert dialog: " + paramString);
    localBuilder.create().show();
  }
  
  static void buyCar(String paramString)
  {
    purchaseProceedStatus = 2;
    Log.d("CleverKidCars", "Upgrade button clicked; launching purchase flow for upgrade.");
    String str2 = BillingHelper.generateDeveloperPayload();
    String str1 = "";
    Object localObject = (HashMap)ResourceUtil.getHashMapResource(mContext, 2130903040);
    if (localObject == null)
    {
      purchaseProceedStatus = 1;
      return;
    }
    localObject = ((HashMap)localObject).entrySet().iterator();
    label50:
    if (!((Iterator)localObject).hasNext()) {}
    Map.Entry localEntry;
    for (paramString = str1;; paramString = (String)localEntry.getKey())
    {
      Log.d("CleverKidCars", "Searching SKU is " + paramString + ".");
      if ((paramString.isEmpty()) || (iAbHelper.isAsyncInProgress())) {
        break;
      }
      iAbHelper.launchPurchaseFlow(self, paramString, 10001, mPurchaseFinishedListener, str2);
      return;
      localEntry = (Map.Entry)((Iterator)localObject).next();
      if (!((String)localEntry.getValue()).equals(paramString)) {
        break label50;
      }
    }
  }
  
  public static void callCamera()
  {
    Log.d("CAMERA", "Call camera");
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    ((Cars)self).startActivityForResult(localIntent, 1001);
  }
  
  static void callPhone(String paramString)
  {
    Intent localIntent;
    if ((mContext != null) && (checkCallPhonePermission())) {
      localIntent = new Intent("android.intent.action.DIAL");
    }
    try
    {
      localIntent.setData(Uri.parse("tel:" + paramString));
      mContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramString) {}
  }
  
  static boolean checkCallPhonePermission()
  {
    return getContext().checkCallingOrSelfPermission("android.permission.CALL_PHONE") == 0;
  }
  
  /* Error */
  private static boolean checkFullAdStatus()
  {
    // Byte code:
    //   0: getstatic 310	by/alfasoft/CleverKidCars/Cars:fullAdView	Lcom/google/android/gms/ads/InterstitialAd;
    //   3: ifnull +41 -> 44
    //   6: new 32	by/alfasoft/CleverKidCars/Cars$6
    //   9: dup
    //   10: invokespecial 311	by/alfasoft/CleverKidCars/Cars$6:<init>	()V
    //   13: astore_0
    //   14: new 34	by/alfasoft/CleverKidCars/Cars$7
    //   17: dup
    //   18: invokespecial 312	by/alfasoft/CleverKidCars/Cars$7:<init>	()V
    //   21: astore_1
    //   22: aload_0
    //   23: monitorenter
    //   24: getstatic 235	by/alfasoft/CleverKidCars/Cars:self	Landroid/app/Activity;
    //   27: aload_0
    //   28: invokevirtual 318	android/app/Activity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   31: aload_0
    //   32: invokevirtual 323	java/lang/Object:wait	()V
    //   35: aload_0
    //   36: monitorexit
    //   37: getstatic 235	by/alfasoft/CleverKidCars/Cars:self	Landroid/app/Activity;
    //   40: aload_1
    //   41: invokevirtual 318	android/app/Activity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   44: iconst_0
    //   45: ireturn
    //   46: astore_2
    //   47: ldc_w 325
    //   50: aload_2
    //   51: invokevirtual 328	java/lang/InterruptedException:getMessage	()Ljava/lang/String;
    //   54: invokestatic 175	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   57: pop
    //   58: goto -23 -> 35
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   13	50	0	local6	6
    //   21	20	1	local7	7
    //   61	4	1	localObject	Object
    //   46	5	2	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   31	35	46	java/lang/InterruptedException
    //   24	31	61	finally
    //   31	35	61	finally
    //   35	37	61	finally
    //   47	58	61	finally
    //   62	64	61	finally
  }
  
  public static native void closeAdButtonClick();
  
  static void complain(String paramString)
  {
    Log.e("CleverKidCars", "**** CleverKidCars Error: " + paramString);
  }
  
  static int getDifferenceBetweenTimeZones()
  {
    return -(TimeZone.getDefault().getRawOffset() / 1000);
  }
  
  public static String getImei()
  {
    return "";
  }
  
  public static String getPurchasePrice(String paramString)
  {
    str2 = "";
    Object localObject1 = str2;
    ArrayList localArrayList;
    Object localObject2;
    if (iAbHelper != null)
    {
      localObject1 = str2;
      if (mContext != null)
      {
        localArrayList = new ArrayList();
        localObject2 = "";
        localObject1 = (HashMap)ResourceUtil.getHashMapResource(mContext, 2130903040);
        if (localObject1 == null) {
          return "";
        }
        localObject1 = ((HashMap)localObject1).entrySet().iterator();
        if (((Iterator)localObject1).hasNext()) {
          break label171;
        }
      }
    }
    for (;;)
    {
      localObject1 = str2;
      if (!((String)localObject2).isEmpty()) {
        localArrayList.add(localObject2);
      }
      try
      {
        localObject2 = iAbHelper.queryInventory(true, localArrayList).getSkuDetails((String)localObject2);
        localObject1 = str2;
        if (localObject2 != null) {
          localObject1 = ((SkuDetails)localObject2).getPrice();
        }
      }
      catch (IabException localIabException)
      {
        for (;;)
        {
          Map.Entry localEntry;
          localIabException.printStackTrace();
          String str1 = str2;
        }
      }
      if ((!((String)localObject1).isEmpty()) && (preferences != null))
      {
        localObject2 = preferences.edit();
        ((SharedPreferences.Editor)localObject2).putString(paramString + "_price", (String)localObject1);
        ((SharedPreferences.Editor)localObject2).commit();
      }
      return localObject1;
      label171:
      localEntry = (Map.Entry)((Iterator)localObject1).next();
      if (!((String)localEntry.getValue()).equals(paramString)) {
        break;
      }
      localObject2 = (String)localEntry.getKey();
    }
  }
  
  public static int getPurchaseProceedStatus()
  {
    return purchaseProceedStatus;
  }
  
  public static int getQueryInventoryStatus()
  {
    return queryInventoryStatus;
  }
  
  static String getStringResource(int paramInt)
  {
    return "";
  }
  
  static void hideAdMob()
  {
    Log.d("CleverKidCars", "AdMob hided");
    self.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Cars.adView.setVisibility(4);
        Cars.adView.loadAd(AdMobHelper.getAdRequest());
      }
    });
  }
  
  static void hidePlusOne()
  {
    self.runOnUiThread(new Runnable()
    {
      public void run() {}
    });
  }
  
  public static void hideVideoBanner() {}
  
  private void initActivity()
  {
    self = this;
    mContext = this;
    initAdWithCloseButton();
    preferences = getSharedPreferences("Cocos2dxPrefsFile", 0);
    SharedPreferences.Editor localEditor = preferences.edit();
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      localEditor.putString("app_version", "version " + str);
      localEditor.putString("device_imei", getImei());
      localEditor.commit();
      hideAdMob();
      setupIabHelper();
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  private void initAdWithCloseButton()
  {
    adView = AdMobHelper.getAdBanner(self, 48);
    this.mRelativeBannerContainer = new RelativeLayout(this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    this.mRelativeBannerContainer.setGravity(48);
    this.mRelativeBannerContainer.setLayoutParams(localLayoutParams);
    this.mRelativeBannerContainer.addView(adView);
    this.mRelativeBannerContainer.setClickable(false);
    int i = (int)getResources().getDimension(2131361792);
    new RelativeLayout.LayoutParams(i, i);
    adView.setAdListener(new AdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        super.onAdFailedToLoad(paramAnonymousInt);
      }
      
      public void onAdLoaded()
      {
        super.onAdLoaded();
      }
    });
    addContentView(this.mRelativeBannerContainer, new FrameLayout.LayoutParams(-2, -2, 49));
  }
  
  private void initFullAd()
  {
    fullAdView = AdMobHelper.getAdFullBanner(self);
  }
  
  public static boolean isCameraExist()
  {
    return ((Cars)self).getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public static boolean isNetworkAvailable()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)mContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
      bool1 = bool2;
      if (localNetworkInfo != null)
      {
        bool1 = bool2;
        if (localNetworkInfo.isConnected()) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static native void nativeReorderPhotoSticker(String paramString);
  
  public static native void nativeSetPhotoSticker(String paramString);
  
  public static native void nativeSetTouchEnabledInModificationScene();
  
  public static void openLinkOrPackage(String paramString)
  {
    List localList = mContext.getPackageManager().getInstalledPackages(0);
    int k = 0;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (localList != null) {
        if (i < localList.size()) {
          break label74;
        }
      }
      for (j = k;; j = 1)
      {
        if (j == 0) {
          break label172;
        }
        try
        {
          sendTracker(String.format("StartApp %s", new Object[] { paramString }));
          mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(paramString));
          return;
        }
        catch (Exception localException)
        {
          label74:
          sendTracker(String.format("OpenMarket %s", new Object[] { paramString }));
          paramString = String.format("market://details?id=%s", new Object[] { paramString });
          localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setData(Uri.parse(paramString));
          self.startActivity(localIntent);
          return;
        }
        if (!((PackageInfo)localList.get(i)).packageName.equals(paramString)) {
          break;
        }
      }
      i += 1;
    }
    label172:
    sendTracker(String.format("OpenMarket %s", new Object[] { paramString }));
    paramString = String.format("market://details?id=%s", new Object[] { paramString });
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    self.startActivity(localIntent);
  }
  
  public static void openMoreGameInMarket(int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    switch (paramInt)
    {
    default: 
      localIntent.setData(Uri.parse("market://details?id=by.alfasoft.CleverKidLearnNumbers"));
    }
    for (;;)
    {
      self.startActivity(localIntent);
      return;
      localIntent.setData(Uri.parse("market://details?id=by.alfasoft.CleverKidPuzzle"));
      continue;
      localIntent.setData(Uri.parse("market://details?id=by.alfasoft.CleverKidCars"));
      continue;
      localIntent.setData(Uri.parse("market://details?id=by.alfasoft.CleverKidYumYum"));
      continue;
      localIntent.setData(Uri.parse("market://details?id=by.alfasoft.CleverKidLearnNumbers"));
    }
  }
  
  public static void openUrl(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    self.startActivity(localIntent);
  }
  
  public static void queryInventory() {}
  
  public static void reorderPhotoSticker(String paramString)
  {
    Cocos2dxGLSurfaceView.getInstance().queueEvent(new Runnable()
    {
      public void run()
      {
        Cars.nativeReorderPhotoSticker(Cars.this);
      }
    });
  }
  
  public static void sendTracker(String paramString)
  {
    Tracker localTracker = GoogleAnalytics.getInstance(getContext()).newTracker("UA-60727748-10");
    localTracker.setScreenName(paramString);
    localTracker.send(new HitBuilders.AppViewBuilder().build());
    Log.d("Analytics", paramString);
  }
  
  public static void setPhotoSticker(String paramString)
  {
    Cocos2dxGLSurfaceView.getInstance().queueEvent(new Runnable()
    {
      public void run()
      {
        Cars.nativeSetPhotoSticker(Cars.this);
      }
    });
  }
  
  public static void setTouchEnabledInModificationScene()
  {
    Cocos2dxGLSurfaceView.getInstance().queueEvent(new Runnable()
    {
      public void run() {}
    });
  }
  
  private static void setupIabHelper()
  {
    synchronized (isInventoryQuerying)
    {
      if (isInventoryQuerying.booleanValue()) {
        return;
      }
      isInventoryQuerying = Boolean.valueOf(true);
      Log.d("CleverKidCars", "Creating IAB helper.");
      iAbHelper = new IabHelper(mContext, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkIEwkCA7pJompRk888zotfK+R0hxKx9nb5nmshVjjlQT9RRjLkIh/qWsxCFdoHKLrQ22hrOcFtypuFPUA1RpPvOgF+L5sMjkaef5NZuvlwx8W0550BaYCMVc1k4FFnwWVujgy9Zr8cFymXfrk4VBoZMM/4XnvBiG28Wfg9THO5hNLQZ1Fy9xu9kOqHUIukwRH+uHAmjNiyyxJYM8YYWkXx+ZyAcQvz8r7ASawORWSZYsbAjyg4s1M7Nk6Mfyi4cgWNKP6Qq06kpWXaZ+Z2P/Y1lkCofo3KYryssvxMp+T9i9PWSoiqNpFIzzE6EtjEdP72tn1kCFK9gVffjNIuVf7QIDAQAB");
      iAbHelper.enableDebugLogging(false);
      Log.d("CleverKidCars", "Starting setup.");
      iAbHelper.startSetup(mIabSetupFinishedListener);
      return;
    }
  }
  
  static void showAdMob()
  {
    self.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Cars.adView.setVisibility(0);
        Cars.adView.loadAd(AdMobHelper.getAdRequest());
      }
    });
  }
  
  static void showFullAdMob()
  {
    if (fullAdView == null) {
      return;
    }
    self.runOnUiThread(new Runnable()
    {
      public void run()
      {
        SharedPreferences.Editor localEditor = Cars.preferences.edit();
        try
        {
          if (Cars.fullAdView.isLoaded())
          {
            localEditor.putBoolean("isFullAdMobShownAlready", true);
            Cars.fullAdView.show();
          }
          for (;;)
          {
            localEditor.commit();
            return;
            localEditor.putBoolean("isFullAdMobShownAlready", false);
            Cars.fullAdView.loadAd(AdMobHelper.requestNewInterstitial());
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localEditor.putBoolean("isFullAdMobShownAlready", false);
            Log.d("FullAd", localException.getMessage());
          }
        }
      }
    });
  }
  
  static void showPlusOne()
  {
    self.runOnUiThread(new Runnable()
    {
      public void run() {}
    });
  }
  
  public static void showVideoBanner() {}
  
  static boolean verifyUserPurchase(String paramString)
  {
    Log.d("CleverKidCars", "verifyUserPurchase: " + paramString);
    boolean bool = false;
    Object localObject = (HashMap)ResourceUtil.getHashMapResource(mContext, 2130903040);
    if (localObject == null) {
      return false;
    }
    localObject = ((HashMap)localObject).entrySet().iterator();
    Map.Entry localEntry;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return bool;
      }
      localEntry = (Map.Entry)((Iterator)localObject).next();
    } while (!((String)localEntry.getValue()).equals(paramString));
    bool = preferences.getBoolean((String)localEntry.getValue(), false);
    localObject = new StringBuilder("Purchase ").append(paramString).append(" is ");
    if (bool) {}
    for (paramString = "purchased";; paramString = "not purchased")
    {
      Log.d("CleverKidCars", paramString);
      break;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
  {
    Log.d("CleverKidCars", "onActivityResult(" + paramInt1 + "," + paramInt2 + "," + paramIntent);
    if ((iAbHelper != null) && (paramInt1 == 10001))
    {
      iAbHelper.handleActivityResult(paramInt1, paramInt2, paramIntent);
      Log.d("CleverKidCars", "onActivityResult handled by IABUtil.");
    }
    label248:
    label256:
    label487:
    label607:
    do
    {
      return;
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      if ((paramInt1 == 1001) && (paramInt2 == -1) && (paramIntent != null))
      {
        Object localObject = null;
        try
        {
          paramIntent = (Bitmap)paramIntent.getExtras().get("data");
          localObject = paramIntent;
          if (paramIntent == null)
          {
            localObject = paramIntent;
            setTouchEnabledInModificationScene();
            return;
          }
        }
        catch (Exception paramIntent)
        {
          setTouchEnabledInModificationScene();
        }
        ByteArrayOutputStream localByteArrayOutputStream;
        File localFile;
        if (((Bitmap)localObject).getWidth() > ((Bitmap)localObject).getHeight())
        {
          paramInt1 = 133;
          paramInt2 = (int)('' * (((Bitmap)localObject).getWidth() / ((Bitmap)localObject).getHeight()));
          paramIntent = Bitmap.createScaledBitmap((Bitmap)localObject, paramInt2, paramInt1, false);
          localByteArrayOutputStream = new ByteArrayOutputStream();
          paramIntent.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
          localFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Cars" + File.separator);
          paramInt2 = -1;
          paramInt1 = 1;
          if (paramInt1 <= 7) {
            break label487;
          }
          paramInt1 = paramInt2;
          if (paramInt1 <= 0) {
            break label607;
          }
          localObject = new File(Environment.getExternalStorageDirectory() + File.separator + "Cars" + File.separator + "image" + String.valueOf(paramInt1) + ".png");
          paramIntent = preferences.edit();
          paramIntent.putString("photo" + String.valueOf(paramInt1), ((File)localObject).getAbsolutePath());
          paramIntent.commit();
        }
        for (paramIntent = "photo" + String.valueOf(paramInt1);; paramIntent = "photo7")
        {
          try
          {
            localFile.mkdirs();
            if (((File)localObject).exists()) {
              ((File)localObject).delete();
            }
            ((File)localObject).createNewFile();
            localObject = new FileOutputStream((File)localObject);
            ((FileOutputStream)localObject).write(localByteArrayOutputStream.toByteArray());
            ((FileOutputStream)localObject).close();
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              SharedPreferences.Editor localEditor;
              localIOException.printStackTrace();
            }
          }
          mResumeRunner = new Runnable()
          {
            public void run()
            {
              ((Cars)Cars.self).runOnGLThread(new Runnable()
              {
                public void run()
                {
                  Cars.reorderPhotoSticker(this.val$fKey);
                  Cars.setPhotoSticker("photo1");
                  Cars.needResumeRunner = false;
                }
              });
            }
          };
          needResumeRunner = true;
          return;
          paramInt2 = 133;
          paramInt1 = (int)('' * (((Bitmap)localObject).getHeight() / ((Bitmap)localObject).getWidth()));
          break;
          paramIntent = new File(Environment.getExternalStorageDirectory() + File.separator + "Cars" + File.separator + "image" + String.valueOf(paramInt1) + ".png");
          localObject = preferences.getString("photo" + String.valueOf(paramInt1), "");
          if ((paramIntent.exists()) && (!((String)localObject).equals("")))
          {
            paramInt1 += 1;
            break label248;
          }
          break label256;
          paramIntent = preferences.getString("photo7", "");
          localObject = new File(paramIntent);
          localEditor = preferences.edit();
          localEditor.putString("photo7", paramIntent);
          localEditor.commit();
        }
      }
    } while (paramInt1 != 1001);
    setTouchEnabledInModificationScene();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initActivity();
    GoogleAnalytics.getInstance(this).reportActivityStart(this);
    initFullAd();
    paramBundle = GoogleAnalytics.getInstance(this).newTracker("UA-60727748-10");
    paramBundle.setScreenName("Car racing started");
    paramBundle.send(new HitBuilders.AppViewBuilder().build());
  }
  
  public Cocos2dxGLSurfaceView onCreateView()
  {
    Cocos2dxGLSurfaceView localCocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
    localCocos2dxGLSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
    localCocos2dxGLSurfaceView.getHolder().removeCallback(mSurfaceHolderCallback);
    localCocos2dxGLSurfaceView.getHolder().addCallback(mSurfaceHolderCallback);
    return localCocos2dxGLSurfaceView;
  }
  
  protected void onDestroy()
  {
    if (iAbHelper != null) {
      iAbHelper.dispose();
    }
    iAbHelper = null;
    if (adView != null) {
      adView.destroy();
    }
    adView = null;
    super.onDestroy();
  }
  
  protected void onPause()
  {
    if (adView != null) {
      adView.pause();
    }
    if (Cocos2dxHelper.isBackgroundMusicPlaying())
    {
      Cocos2dxHelper.pauseBackgroundMusic();
      Cocos2dxHelper.pauseAllEffects();
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    if (adView != null) {
      adView.resume();
    }
    if (!Cocos2dxHelper.isBackgroundMusicPlaying())
    {
      Cocos2dxHelper.resumeBackgroundMusic();
      Cocos2dxHelper.resumeAllEffects();
    }
    super.onResume();
    if ((mIsSurfaceCreated) && (needResumeRunner) && (mResumeRunner != null)) {
      mResumeRunner.run();
    }
  }
}
