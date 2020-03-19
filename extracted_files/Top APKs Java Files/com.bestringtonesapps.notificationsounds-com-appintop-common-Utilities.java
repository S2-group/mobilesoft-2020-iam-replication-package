package com.appintop.common;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.appintop.logger.AdsATALog;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Utilities
{
  static boolean onResumeCalled = false;
  static SharedPreferences.Editor sed;
  static SharedPreferences sp;
  
  public Utilities() {}
  
  public static boolean checkActivityLifeCycleSync()
  {
    if (!onResumeCalled) {
      AdsATALog.i("==========\n\tThe AdToApp SDK needs to be notified of changes in the activity lifecycle so it can keep track of the current activity. This can be easily achieved by calling the onPause(Activity), onResume(Activity) and onDestroy(Activity) methods.\n==========");
    }
    return onResumeCalled;
  }
  
  public static void findAndRemoveInHouseClickId(Context paramContext)
  {
    Uri localUri = Uri.parse(String.format("content://%s.%s/%s/%s", new Object[] { paramContext.getPackageName(), "com.appintop.inhouse.tracking", "apps", paramContext.getPackageName() }));
    Cursor localCursor = paramContext.getContentResolver().query(localUri, null, null, null, null);
    if ((localCursor != null) && (localCursor.getCount() != 0))
    {
      localCursor.close();
      paramContext.getContentResolver().delete(localUri, null, null);
    }
  }
  
  public static String getInHouseClickId(Context paramContext)
  {
    try
    {
      Object localObject = Uri.parse(String.format("content://%s.%s/%s/%s", new Object[] { paramContext.getPackageName(), "com.appintop.inhouse.tracking", "apps", paramContext.getPackageName() }));
      paramContext = paramContext.getContentResolver().query((Uri)localObject, null, null, null, null);
      if ((paramContext != null) && (paramContext.moveToFirst()))
      {
        localObject = paramContext.getString(paramContext.getColumnIndex("click_id"));
        paramContext.close();
        return localObject;
      }
    }
    catch (SecurityException paramContext)
    {
      AdsATALog.e("Unnable to getInHouseClickId", paramContext);
      return "";
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        AdsATALog.e("Unnable to getInHouseClickId", paramContext);
      }
    }
  }
  
  public static String getInstalledProvidersJson(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    HashMap localHashMap = new HashMap();
    localHashMap.put(Integer.valueOf(1), "com.jirbo.adcolony.AdColony");
    localHashMap.put(Integer.valueOf(2), "com.chartboost.sdk.Chartboost");
    localHashMap.put(Integer.valueOf(3), "com.smaato.soma.AdSettings");
    localHashMap.put(Integer.valueOf(5), "com.startapp.android.publish.StartAppSDK");
    localHashMap.put(Integer.valueOf(6), "com.inmobi.sdk.InMobiSdk");
    localHashMap.put(Integer.valueOf(7), "com.google.android.gms.ads.MobileAds");
    localHashMap.put(Integer.valueOf(8), "com.applovin.sdk.AppLovinSdk");
    localHashMap.put(Integer.valueOf(16), "com.amazon.device.ads.AdRegistration");
    localHashMap.put(Integer.valueOf(17), "com.vungle.publisher.VunglePub");
    localHashMap.put(Integer.valueOf(21), "com.my.target.SDKVersion");
    localHashMap.put(Integer.valueOf(22), "com.unity3d.ads.UnityAds");
    localHashMap.put(Integer.valueOf(23), "com.avocarrot.androidsdk.CustomModel");
    localHashMap.put(Integer.valueOf(24), "com.nativex.monetization.MonetizationManager");
    localHashMap.put(Integer.valueOf(25), "com.tapsense.android.publisher.TapSenseAds");
    localHashMap.put(Integer.valueOf(26), "com.yandex.mobile.ads.MobileAds");
    localHashMap.put(Integer.valueOf(27), "com.supersonic.mediationsdk.sdk.SupersonicFactory");
    localHashMap.put(Integer.valueOf(28), "com.woobi.Woobi");
    localHashMap.put(Integer.valueOf(29), "com.instreamatic.adman.AdmanId");
    localHashMap.put(Integer.valueOf(15), "com.tapjoy.Tapjoy");
    localHashMap.put(Integer.valueOf(31), "com.facebook.FacebookSdk");
    localHashMap.put(Integer.valueOf(11), "com.revmob.RevMob");
    localHashMap.put(Integer.valueOf(32), "com.flurry.android.ads.FlurryAdTargeting");
    localHashMap.put(Integer.valueOf(30), "com.appintop.inhouse.InHouseAdsManager");
    Object localObject1 = null;
    try
    {
      arrayOfString = paramContext.getAssets().list("dex");
      localObject1 = arrayOfString;
    }
    catch (IOException localIOException)
    {
      int j;
      try
      {
        for (;;)
        {
          String[] arrayOfString;
          DexLibLoader.loadFromAssets(paramContext, "dex", arrayOfString);
          i += 1;
        }
        localIOException = localIOException;
        localIOException.printStackTrace();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          AdsATALog.i("Unable to load DEX file " + localIOException);
        }
      }
      localIterator = localHashMap.keySet().iterator();
      do
      {
        do
        {
          for (;;)
          {
            if (!localIterator.hasNext()) {
              break label881;
            }
            j = ((Integer)localIterator.next()).intValue();
            paramContext = (String)localHashMap.get(Integer.valueOf(j));
            try
            {
              Class.forName(paramContext);
              localJSONArray.put(j);
            }
            catch (ClassNotFoundException paramContext) {}
          }
        } while ((localObject1 == null) || (localObject1.length <= 0));
        localObject2 = null;
        paramContext = localObject2;
        switch (j)
        {
        default: 
          paramContext = localObject2;
        }
      } while (paramContext == null);
      k = localObject1.length;
      int i = 0;
      while (i < k)
      {
        if (localObject1[i].equals(paramContext))
        {
          localJSONArray.put(j);
          break;
          paramContext = "adcolony.dex";
          break label699;
          paramContext = "chartboost.dex";
          break label699;
          paramContext = "smaato.dex";
          break label699;
          paramContext = "startapp.dex";
          break label699;
          paramContext = "inmobi.dex";
          break label699;
          paramContext = "applovin.dex";
          break label699;
          paramContext = "tapjoy.dex";
          break label699;
          paramContext = "amazon.dex";
          break label699;
          paramContext = "vungle.dex";
          break label699;
          paramContext = "mytarget.dex";
          break label699;
          paramContext = "unity.dex";
          break label699;
          paramContext = "avocarrot.dex";
          break label699;
          paramContext = "nativex.dex";
          break label699;
          paramContext = "tapsense.dex";
          break label699;
          paramContext = "yandex.dex";
          break label699;
          paramContext = "supersonic.dex";
          break label699;
          paramContext = "woobi.dex";
          break label699;
          paramContext = "instreamatic.dex";
          break label699;
          paramContext = "revmob.dex";
          break label699;
          paramContext = "flurry.dex";
          break label699;
        }
        i += 1;
      }
    }
    if ((localObject1 != null) && (localObject1.length > 0))
    {
      j = localObject1.length;
      i = 0;
      if (i < j) {
        arrayOfString = localObject1[i];
      }
    }
    try
    {
      Iterator localIterator;
      Object localObject2;
      label699:
      int k;
      label881:
      localJSONObject.put("providers", localJSONArray);
      return localJSONObject.toString();
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static SharedPreferences getSharedPreferences(Context paramContext)
  {
    if (sp == null) {
      sp = paramContext.getApplicationContext().getSharedPreferences("provider_params_data", 0);
    }
    return sp;
  }
  
  public static boolean isConnectedMobile(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected()) && (paramContext.getType() == 0);
  }
  
  public static boolean isConnectedWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected()) && (paramContext.getType() == 1);
  }
  
  private static Boolean isInstallAppWithBundleId(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((ApplicationInfo)paramContext.next()).packageName)) {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static boolean isNetworkConnected(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    bool2 = false;
    try
    {
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        bool2 = bool1;
        AdsATALog.i(String.format("Check device screen size configuration. IsTablet: %s", new Object[] { Boolean.valueOf(bool1) }));
        return bool1;
      }
      return bool2;
    }
    catch (Exception paramContext)
    {
      AdsATALog.i("Error check device screen size configuration!");
      paramContext.printStackTrace();
    }
  }
  
  public static void onResume()
  {
    onResumeCalled = true;
  }
  
  public static String readStringLocalData(Context paramContext, String paramString)
  {
    return getSharedPreferences(paramContext).getString(paramString, "");
  }
  
  public static void saveInHouseClickId(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = Uri.parse(String.format("content://%s.%s/%s/%s", new Object[] { paramContext.getPackageName(), "com.appintop.inhouse.tracking", "apps", paramString1 }));
    Cursor localCursor = paramContext.getContentResolver().query((Uri)localObject, null, null, null, null);
    if ((localCursor != null) && (localCursor.getCount() != 0))
    {
      localCursor.close();
      paramContext.getContentResolver().delete((Uri)localObject, null, null);
    }
    if (!isInstallAppWithBundleId(paramContext, paramString1).booleanValue())
    {
      localObject = new ContentValues();
      ((ContentValues)localObject).put("store_id", paramString1);
      ((ContentValues)localObject).put("click_id", paramString2);
      paramContext.getContentResolver().insert(Uri.parse(String.format("content://%s.%s/%s", new Object[] { paramContext.getPackageName(), "com.appintop.inhouse.tracking", "apps" })), (ContentValues)localObject);
    }
  }
  
  public static void saveStringLocalData(Context paramContext, String paramString1, String paramString2)
  {
    if (sed == null) {
      sed = getSharedPreferences(paramContext).edit();
    }
    sed.putString(paramString1, paramString2);
    sed.commit();
  }
  
  public static boolean tryParseInt(String paramString)
  {
    try
    {
      Integer.parseInt(paramString);
      return true;
    }
    catch (NumberFormatException paramString) {}
    return false;
  }
  
  public static class TestAdsData
  {
    public static final String COLOR_SDKINITIALIZED_MODERATE = "#00FF00";
    public static final String COLOR_SDKINITIALIZED_NONMODERATE = "#FFFF00";
    public static final String COLOR_SDKNONINITIALIZED = "#FF0000";
    public static final String STATE_SDKINITIALIZED_MODERATE = "AdToApp SDK successfully initialized. Your application has been successfully moderated. You can disable the test mode.";
    public static final String STATE_SDKINITIALIZED_NONMODERATE = "AdToApp SDK successfully initialized. Your application to moderation.";
    public static final String STATE_SDKNONINITIALIZED = "AdToApp SDK is not initialized.";
    
    public TestAdsData() {}
  }
}
