package hundred.five.tools.ads;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.super.speaker.volume.booster.AppRater;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class HfAds
{
  private static Activity activity = null;
  private static InterstitialAd admobInterstitialAd;
  private static boolean admobLoaded = false;
  private static boolean network;
  private static boolean showAdmob = true;
  private HfHomeAds ads = null;
  
  static
  {
    network = false;
  }
  
  public HfAds(Activity paramActivity, OnSplashClose paramOnSplashClose)
  {
    activity = paramActivity;
    this.ads = new HfHomeAds(activity, 5);
  }
  
  private void downloadImageBitmap(final Ad paramAd)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        final Bitmap localBitmap = HfAds.this.getBitmap(paramAd.getImageUrl());
        if (localBitmap != null) {
          HfAds.activity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              try
              {
                AppOfTheDay.show(HfAds.activity, HfAds.2.this.val$ad, localBitmap);
                return;
              }
              catch (Exception localException) {}
            }
          });
        }
      }
    }).start();
  }
  
  private Bitmap getBitmap(String paramString)
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setDoInput(true);
      paramString.connect();
      paramString = BitmapFactory.decodeStream(paramString.getInputStream());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void init(Activity paramActivity, OnAdmob paramOnAdmob)
  {
    activity = paramActivity;
    network = isNetworkAvailable();
    if (network)
    {
      admobInterstitialAd = new InterstitialAd(activity);
      admobInterstitialAd.setAdUnitId("ca-app-pub-9652313455575349/1063240475");
      paramActivity = new AdRequest.Builder();
      admobInterstitialAd.setAdListener(new AdListener()
      {
        public void onAdClosed()
        {
          if (this.val$oam != null) {
            this.val$oam.onClose();
          }
        }
        
        public void onAdFailedToLoad(int paramAnonymousInt)
        {
          super.onAdFailedToLoad(paramAnonymousInt);
          if (this.val$oam != null) {
            this.val$oam.onClose();
          }
        }
        
        public void onAdLoaded()
        {
          if (HfAds.showAdmob)
          {
            HfAds.admobInterstitialAd.show();
            if (this.val$oam != null) {
              this.val$oam.onLoad();
            }
            return;
          }
          HfAds.access$202(true);
        }
      });
    }
    while (paramOnAdmob == null) {
      try
      {
        admobInterstitialAd.loadAd(paramActivity.build());
        return;
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
    }
    paramOnAdmob.onNetwork();
  }
  
  private static boolean isNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)activity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static void resumeAdmob()
  {
    if (admobLoaded)
    {
      admobInterstitialAd.show();
      return;
    }
    showAdmob = true;
  }
  
  public static void stopAdmob()
  {
    showAdmob = false;
  }
  
  boolean checkPackageInstalled(String paramString)
  {
    Iterator localIterator = activity.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (paramString.equals(((ApplicationInfo)localIterator.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public Ad getNextAd()
  {
    Ad localAd;
    do
    {
      localAd = this.ads.getNextAd();
    } while ((localAd != null) && (checkPackageInstalled(localAd.getPackacgeName())));
    return localAd;
  }
  
  public void loadAppNext() {}
  
  public boolean onBackPressed(AppRater paramAppRater)
  {
    return paramAppRater.doRate().booleanValue();
  }
  
  public void onCreate() {}
  
  public void onPause() {}
  
  public void onResume() {}
  
  public void onStop() {}
  
  public void showAppList() {}
  
  public boolean showAppOfTheDay()
  {
    Ad localAd = getNextAd();
    if (localAd != null)
    {
      if (localAd.getImageBitmap() != null) {
        AppOfTheDay.show(activity, localAd, localAd.getImageBitmap());
      }
      for (;;)
      {
        return true;
        downloadImageBitmap(localAd);
      }
    }
    return false;
  }
  
  public void showFirstAd() {}
  
  public boolean showGPDirect()
  {
    Ad localAd = getNextAd();
    if (localAd != null)
    {
      localAd.onShow();
      localAd.onClick();
      return true;
    }
    return false;
  }
  
  public static abstract interface OnAdmob
  {
    public abstract void onClose();
    
    public abstract void onLoad();
    
    public abstract void onNetwork();
  }
  
  public static abstract interface OnSplashClose
  {
    public abstract void onSplashClose();
  }
}
