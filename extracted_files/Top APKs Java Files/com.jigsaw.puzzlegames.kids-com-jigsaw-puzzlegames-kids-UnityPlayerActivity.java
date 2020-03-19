package com.jigsaw.puzzlegames.kids;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.HeyzapAds.OnIncentiveResultListener;
import com.heyzap.sdk.ads.IncentivizedAd;
import com.unity3d.player.UnityPlayer;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class UnityPlayerActivity
  extends Activity
{
  public static com.google.android.gms.ads.InterstitialAd ADM_interstitialAd;
  public static com.facebook.ads.InterstitialAd FB_interstitialAd;
  static final int GALLERY_KITKAT_INTENT_CALLED = 3;
  static final int REQ_CODE_PICK_IMAGE = 150;
  static final int RINGTONE_CONTACT = 7;
  public static long lasttime;
  String ADM_IDINTERSTITIAL;
  String ADM_banner;
  private com.facebook.ads.AdView FB_mAdView;
  public String MarketJe = "googleplay";
  private Activity _activity;
  AlarmManager am;
  Context appCon;
  private String app_id;
  private String author_id;
  Bitmap bit;
  boolean f_AdmobBan = false;
  boolean f_FacebookBan = false;
  boolean f_ShowBanners = false;
  public String filepath2 = "";
  RelativeLayout layout_fadeInOut;
  private com.google.android.gms.ads.AdView mAdView;
  AnimatorSet mAnimationSet_In;
  AnimatorSet mAnimationSet_Out;
  Context mContext;
  protected UnityPlayer mUnityPlayer;
  public boolean nett = false;
  boolean postavljeno;
  public String putanja;
  public Bitmap slika = null;
  public Bitmap slikaaa = null;
  int slucaj;
  boolean testNotifikacija = false;
  String videobroj = "";
  
  public UnityPlayerActivity() {}
  
  private void CreateBanners()
  {
    Log.e("Kreiranje banera", "KREIRANJE");
    if (this.mAdView == null)
    {
      Log.e("Ad mob banner", "if");
      this.mAdView = new com.google.android.gms.ads.AdView(this);
      this.mAdView.setAdUnitId(this.ADM_banner);
      this.mAdView.setAdSize(AdSize.SMART_BANNER);
      this.mAdView.setAdListener(new AdListener()
      {
        public void onAdFailedToLoad(int paramAnonymousInt)
        {
          super.onAdFailedToLoad(paramAnonymousInt);
          Log.e("admob", "Ad Failed to Load");
          UnityPlayerActivity.this.f_AdmobBan = false;
        }
        
        public void onAdLeftApplication() {}
        
        public void onAdLoaded()
        {
          super.onAdLoaded();
          UnityPlayerActivity.this.f_AdmobBan = true;
          Log.e("admob", "Ad Load");
        }
      });
      RelativeLayout localRelativeLayout = new RelativeLayout(this);
      localRelativeLayout.setGravity(17);
      addContentView(localRelativeLayout, new ViewGroup.LayoutParams(-1, -1));
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      this.mAdView.loadAd(new AdRequest.Builder().build());
      localRelativeLayout.addView(this.mAdView, localLayoutParams);
      this.mAdView.setVisibility(4);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Log.e("Handler BAN", "TIMER");
          UnityPlayerActivity.this.ShowBanner("loc");
        }
      }, 3000L);
    }
  }
  
  private void SrediSliku(String paramString, boolean paramBoolean)
    throws FileNotFoundException
  {
    this.filepath2 = paramString;
    this.nett = paramBoolean;
    paramString = new BitmapFactory.Options();
    paramString.inPurgeable = true;
    paramString.inSampleSize = 2;
    this.slikaaa = BitmapFactory.decodeFile(this.filepath2, paramString);
    int j = 0;
    int m;
    for (;;)
    {
      try
      {
        k = new ExifInterface(this.filepath2).getAttributeInt("Orientation", 1);
        i = j;
        switch (k)
        {
        default: 
          i = j;
        }
      }
      catch (IOException paramString)
      {
        float f;
        paramString.printStackTrace();
        i = j;
        continue;
        paramString.postRotate(j);
        paramString = Bitmap.createBitmap(this.slikaaa, 0, 0, m, i, paramString, true);
        if (m != 716) {
          break;
        }
      }
      m = this.slikaaa.getWidth();
      k = this.slikaaa.getHeight();
      paramString = new Matrix();
      Log.i("UFFF", m + " " + k);
      j = i;
      if (m > k) {
        j = 90;
      }
      f = m / k;
      i = k;
      if (f < 0.69D)
      {
        i -= 1;
        f = m / i;
        Log.i("UFFF", String.valueOf(f));
        continue;
        i = 270;
        continue;
        i = 180;
        continue;
        i = 90;
      }
      else
      {
        if (i == 1024) {
          break label555;
        }
      }
    }
    Log.i("UFFF", "skalira okrenutu1");
    this.slika = Bitmap.createScaledBitmap(paramString, 716, 1024, true);
    Log.i("UFFF", "brise okrenutu");
    int k = i;
    int i = m;
    for (;;)
    {
      Log.i("UFFF", i + " " + k + " " + j);
      Object localObject = new File(Environment.getExternalStorageDirectory() + "/myimage.jpg");
      try
      {
        localObject = new FileOutputStream((File)localObject);
        Log.i("UFFF", "Da nije ovde3");
        this.slika.compress(Bitmap.CompressFormat.JPEG, 90, (OutputStream)localObject);
        paramString.recycle();
        this.slikaaa.recycle();
        Log.i("UFFF", "Da nije ovde4");
        ((FileOutputStream)localObject).flush();
        ((FileOutputStream)localObject).close();
        UnityPlayer.UnitySendMessage("AndroidSlika", "PostaviSliku", Environment.getExternalStorageDirectory() + "/myimage.jpg");
        Log.i("UFFF", Environment.getExternalStorageDirectory() + "/myimage.jpg");
        return;
        label555:
        Log.i("UFFF", "skalira okrenutu2");
        i = paramString.getWidth();
        k = paramString.getHeight();
        Log.i("UFFF", "Da nije ovde3 sirina okrenute : " + Integer.toString(i));
        this.slika = Bitmap.createBitmap(paramString, 0, 0, i, k);
        Log.i("UFFF", "Da nije ovde3 sirina slike za Unity : " + Integer.toString(this.slika.getHeight()));
      }
      catch (FileNotFoundException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
    }
  }
  
  private Boolean checkConnection()
  {
    if (((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  private Uri getUri()
  {
    if (!Environment.getExternalStorageState().equalsIgnoreCase("mounted")) {
      return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    }
    return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  }
  
  public void CheckFaceShare(String paramString1, String paramString2)
  {
    Log.i("Face", "Face");
    if (checkConnection().booleanValue())
    {
      makeToast(paramString1);
      return;
    }
    makeToast(paramString2);
  }
  
  public void CheckWallpaper(String paramString)
  {
    Log.i("Wall", "Wall");
    makeToast(paramString);
  }
  
  public void DetaktujMarket()
  {
    String str3 = "googleplay";
    String str4 = getPackageManager().getInstallerPackageName(this.app_id);
    String str2;
    String str1;
    if (str4 == null)
    {
      str4 = "<x>";
      Iterator localIterator = getPackageManager().getInstalledPackages(1).iterator();
      do
      {
        str2 = str4;
        str1 = str3;
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!((PackageInfo)localIterator.next()).packageName.contains("samsungapps"));
      str1 = "samsung";
      str2 = str4;
    }
    for (;;)
    {
      this.MarketJe = str1;
      try
      {
        UnityPlayer.UnitySendMessage("GlavniGameObject", "MarketJe", this.MarketJe);
        callFlurry("Started -> Market:***" + this.MarketJe);
        callFlurry("Started -> Market_RAW:***" + str2);
        return;
        if (str4.contains("amazon"))
        {
          str1 = "amazon";
          str2 = str4;
          continue;
        }
        str2 = str4;
        str1 = str3;
        if (!str4.contains("samsung")) {
          continue;
        }
        str1 = "samsung";
        str2 = str4;
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        for (;;)
        {
          localNotFoundException.printStackTrace();
        }
      }
    }
  }
  
  public void Eclipse_Reklame(String paramString)
  {
    Log.e("Eclipse_Reklame", "Eclipse_Reklame");
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.e("layout_fadeInOut", "PRIKAZI!");
        UnityPlayerActivity.this.layout_fadeInOut.setVisibility(0);
        UnityPlayerActivity.this.mUnityPlayer.pause();
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(UnityPlayerActivity.this.layout_fadeInOut, "alpha", new float[] { 0.1F, 1.0F });
        localObjectAnimator1.setDuration(1250L);
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(UnityPlayerActivity.this.layout_fadeInOut, "alpha", new float[] { 1.0F, 0.1F });
        localObjectAnimator2.setDuration(1250L);
        UnityPlayerActivity.this.mAnimationSet_In = new AnimatorSet();
        UnityPlayerActivity.this.mAnimationSet_Out = new AnimatorSet();
        UnityPlayerActivity.this.mAnimationSet_In.play(localObjectAnimator1);
        UnityPlayerActivity.this.mAnimationSet_Out.play(localObjectAnimator2);
        UnityPlayerActivity.this.mAnimationSet_In.addListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymous2Animator)
          {
            super.onAnimationEnd(paramAnonymous2Animator);
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                if (UnityPlayerActivity.this.reklame_nono_timer()) {
                  UnityPlayerActivity.this.ZoviOglas();
                }
              }
            }, 0L);
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                UnityPlayerActivity.this.mUnityPlayer.resume();
                UnityPlayerActivity.this.mAnimationSet_Out.start();
              }
            }, 1250L);
          }
        });
        UnityPlayerActivity.this.mAnimationSet_Out.addListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymous2Animator)
          {
            super.onAnimationEnd(paramAnonymous2Animator);
            UnityPlayerActivity.this.layout_fadeInOut.setVisibility(8);
          }
        });
        UnityPlayerActivity.this.mAnimationSet_In.start();
      }
    });
  }
  
  public void HideBanner(String paramString)
  {
    Log.e("HideBanner", "HideBanner");
    if (!paramString.equals("loc")) {
      this.f_ShowBanners = false;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        UnityPlayerActivity.this.mAdView.setVisibility(4);
      }
    });
  }
  
  public void ImaLiVideoReward(String paramString)
  {
    Log.e("ImaLiVideoReward", "ImaLiVideoReward");
    if (IncentivizedAd.isAvailable().booleanValue())
    {
      Log.e("ImaLiVideoReward", "ima!!");
      try
      {
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            UnityPlayer localUnityPlayer = UnityPlayerActivity.this.mUnityPlayer;
            UnityPlayer.UnitySendMessage("Ads", "IZ_AS_DaLiImaVideo", "ima");
          }
        });
        return;
      }
      catch (Resources.NotFoundException paramString)
      {
        paramString.printStackTrace();
        return;
      }
    }
    Log.e("NemaVideoReward", "Nema");
    paramString = this.mUnityPlayer;
    UnityPlayer.UnitySendMessage("Ads", "IZ_AS_DaLiImaVideo", "nema");
    IncentivizedAd.fetch();
  }
  
  public void Init_Interstitial_ADS()
  {
    this.appCon = this;
    ADM_interstitialAd = new com.google.android.gms.ads.InterstitialAd(this.appCon);
    ADM_interstitialAd.setAdUnitId(this.ADM_IDINTERSTITIAL);
    ADM_interstitialAd.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        UnityPlayerActivity.ADM_interstitialAd.loadAd(new AdRequest.Builder().build());
      }
      
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        super.onAdFailedToLoad(paramAnonymousInt);
      }
      
      public void onAdLeftApplication() {}
      
      public void onAdLoaded()
      {
        super.onAdLoaded();
      }
    });
    ADM_interstitialAd.loadAd(new AdRequest.Builder().setIsDesignedForFamilies(true).tagForChildDirectedTreatment(true).build());
  }
  
  public void Share(String paramString)
  {
    Log.e("filter", "Share");
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        UnityPlayerActivity.this.shareAPP(UnityPlayerActivity.this.getResources().getString(2131034151), "https://play.google.com/store/apps/details?id=" + UnityPlayerActivity.this.app_id);
        Log.i("Share", "Share pozvan");
      }
    });
  }
  
  public void Share2(String paramString)
  {
    Log.e("filter", "Share");
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        UnityPlayerActivity.this.shareAPP(UnityPlayerActivity.this.getResources().getString(2131034151), "https://play.google.com/store/apps/details?id=" + UnityPlayerActivity.this.app_id);
        Log.i("Share", "Share pozvan");
      }
    });
  }
  
  public void ShowBanner(String paramString)
  {
    Log.e("ShowBanner", "odakle:" + paramString);
    if (!paramString.equals("loc")) {
      this.f_ShowBanners = true;
    }
    if (!this.f_ShowBanners) {
      HideBanner("loc");
    }
    for (;;)
    {
      if (paramString.equals("loc")) {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Log.e("Handler BAN", "TIMER");
            UnityPlayerActivity.this.ShowBanner("loc");
          }
        }, 15000L);
      }
      return;
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (UnityPlayerActivity.this.f_AdmobBan)
          {
            Log.e("admob BAN", "ad -> VISIBLE");
            UnityPlayerActivity.this.mAdView.setVisibility(0);
            return;
          }
          UnityPlayerActivity.this.mAdView.setVisibility(4);
        }
      });
    }
  }
  
  public void ShowRewardVide0(String paramString)
  {
    Log.e("ShowRewardVide0", "ShowRewardVide0");
    this.videobroj = paramString;
    if (IncentivizedAd.isAvailable().booleanValue())
    {
      lasttime = System.currentTimeMillis();
      IncentivizedAd.display(this);
    }
  }
  
  public Calendar VratiDatumZaNotif()
  {
    Object localObject = Calendar.getInstance();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(7, 7);
    localCalendar.set(11, 19);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    if (localCalendar.getTimeInMillis() - ((Calendar)localObject).getTimeInMillis() <= 0L)
    {
      localCalendar.add(5, 7);
      Log.i("datumlog", "subota je prosla zakazi za sledecu subotu");
    }
    for (;;)
    {
      localObject = new SimpleDateFormat("EEE yyyy/MM/dd HH:mm:ss");
      Log.i("datumlog", "Vreme za zakazivanje");
      Log.i("datumlog", ((DateFormat)localObject).format(localCalendar.getTime()));
      return localCalendar;
      if (localCalendar.getTimeInMillis() - ((Calendar)localObject).getTimeInMillis() < 86400000L)
      {
        localCalendar.add(5, 7);
        Log.i("datumlog", "nije prosla subota ali je vreme manje od 24h zakazi za sledecu subotu");
      }
      else
      {
        Log.i("datumlog", "nije prosla subota vreme je odgovarajuce zakazi ga tada");
      }
    }
  }
  
  public void ZakaziNotifikaciju(Calendar paramCalendar, String paramString1, String paramString2, String paramString3)
  {
    write(paramString1, "tickerText");
    write(paramString2, "contentTitle");
    write(paramString3, "contentText");
    paramString1 = new Intent(this, NotifReceiver.class);
    paramString1 = PendingIntent.getBroadcast(getApplicationContext(), Integer.valueOf(getResources().getString(2131034113)).intValue(), paramString1, 268435456);
    paramString2 = (AlarmManager)getSystemService("alarm");
    if (this.testNotifikacija)
    {
      paramString2.setRepeating(0, Calendar.getInstance().getTimeInMillis() + 60000L, 60000L, paramString1);
      return;
    }
    paramString2.setRepeating(0, paramCalendar.getTimeInMillis(), 604800000L, paramString1);
  }
  
  public void ZoviOglas()
  {
    Log.e("zovi oglas", "1");
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (UnityPlayerActivity.ADM_interstitialAd.isLoaded())
        {
          Log.e("Eclipse_Reklame", "ADM_reklame");
          UnityPlayerActivity.ADM_interstitialAd.show();
          return;
        }
        UnityPlayerActivity.ADM_interstitialAd.loadAd(new AdRequest.Builder().build());
        if (com.heyzap.sdk.ads.InterstitialAd.isAvailable().booleanValue())
        {
          com.heyzap.sdk.ads.InterstitialAd.display(UnityPlayerActivity.this);
          return;
        }
        com.heyzap.sdk.ads.InterstitialAd.fetch();
      }
    });
  }
  
  public void callFlurry(String paramString)
  {
    Log.e("filter", "flurry" + paramString + "!!!!");
    Object localObject = new StringTokenizer(paramString, "***");
    paramString = ((StringTokenizer)localObject).nextToken();
    localObject = ((StringTokenizer)localObject).nextToken();
    Log.e("part1", paramString);
    Log.e("part2", (String)localObject);
    HashMap localHashMap = new HashMap();
    localHashMap.put("click", localObject);
    FlurryAgent.logEvent(paramString, localHashMap);
  }
  
  public void callFlurry_single(String paramString)
  {
    Log.e("filter", "callFlurry_single" + paramString + "!!!!");
    FlurryAgent.logEvent(paramString);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 2) {
      return this.mUnityPlayer.injectEvent(paramKeyEvent);
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public String getRealPathFromURI(Uri paramUri)
  {
    paramUri = getApplicationContext().getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
    int i = paramUri.getColumnIndexOrThrow("_data");
    paramUri.moveToFirst();
    return paramUri.getString(i);
  }
  
  public void makeToast(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(UnityPlayerActivity.this._activity, paramString, 0).show();
      }
    });
  }
  
  @TargetApi(19)
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Log.i("filter", Integer.toString(paramInt1));
    if (paramInt1 == 150)
    {
      Log.i("activityResult", "Pick Image");
      if (paramInt2 == -1)
      {
        Log.i("filter", "onactivity");
        paramIntent = paramIntent.getData();
        Log.i("UFFF", String.valueOf(paramIntent));
        localObject1 = new String[1];
        localObject1[0] = "_data";
        localObject2 = getApplicationContext().getContentResolver().query(paramIntent, (String[])localObject1, null, null, null);
        if (localObject2 != null) {
          break label142;
        }
        paramIntent = paramIntent.getPath();
        Log.i("UFFF", paramIntent);
        Log.i("UFFF", "Doso do slanja poruke iz pickera");
        Log.i("UFFF", "Doso do SrediSliku(filePath);");
      }
    }
    label142:
    do
    {
      do
      {
        try
        {
          SrediSliku(paramIntent, false);
          return;
        }
        catch (FileNotFoundException paramIntent)
        {
          paramIntent.printStackTrace();
          return;
        }
        ((Cursor)localObject2).moveToFirst();
        paramInt1 = ((Cursor)localObject2).getColumnIndex(localObject1[0]);
        Log.i("UFFF", Integer.toString(paramInt1));
        localObject1 = ((Cursor)localObject2).getString(paramInt1);
        if (localObject1 != null)
        {
          paramIntent = (Intent)localObject1;
          if (localObject1 != " ") {}
        }
        else
        {
          paramIntent = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndexOrThrow("_data"));
        }
        ((Cursor)localObject2).close();
        break;
      } while (paramInt1 != 3);
      Log.i("activityResult", "KIT-KAT");
    } while (paramInt2 != -1);
    Log.i("filter", "onactivity");
    Object localObject1 = paramIntent.getData();
    paramInt1 = paramIntent.getFlags();
    getContentResolver().takePersistableUriPermission((Uri)localObject1, paramInt1 & 0x3);
    String str = localObject1.getLastPathSegment().split(":")[1];
    Object localObject2 = getUri();
    paramIntent = "path";
    str = "_id=" + str;
    localObject2 = managedQuery((Uri)localObject2, new String[] { "_data" }, str, null, null);
    if (((Cursor)localObject2).moveToFirst()) {
      paramIntent = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("_data"));
    }
    Log.e("path", paramIntent);
    Log.i("UFFF ***", String.valueOf(localObject1));
    localObject2 = new String[1];
    localObject2[0] = "_data";
    localObject1 = getApplicationContext().getContentResolver().query((Uri)localObject1, (String[])localObject2, null, null, null);
    if (localObject1 == null) {}
    for (;;)
    {
      Log.i("UFFF file path", paramIntent);
      Log.i("UFFF", "Doso do slanja poruke iz pickera");
      Log.i("UFFF", "Doso do SrediSliku(filePath);");
      try
      {
        SrediSliku(paramIntent, false);
        return;
      }
      catch (FileNotFoundException paramIntent)
      {
        paramIntent.printStackTrace();
        return;
      }
      ((Cursor)localObject1).moveToFirst();
      Log.i("UFFF", Integer.toString(((Cursor)localObject1).getColumnIndex(localObject2[0])));
      if ((paramIntent == null) || (paramIntent == " ")) {
        ((Cursor)localObject1).getColumnIndexOrThrow("_data");
      }
      ((Cursor)localObject1).close();
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.mUnityPlayer.configurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    int i = 1;
    Fabric.with(this, new Kit[] { new Crashlytics() });
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    this.app_id = getApplicationContext().getPackageName();
    this.ADM_IDINTERSTITIAL = getApplicationContext().getResources().getString(2131034184);
    this.ADM_banner = getApplicationContext().getResources().getString(2131034185);
    Log.e("app_id", this.app_id);
    Init_Interstitial_ADS();
    CreateBanners();
    HeyzapAds.start("d59d39519d73f40ff957c084b7ccbe61", this);
    IncentivizedAd.setOnIncentiveResultListener(new HeyzapAds.OnIncentiveResultListener()
    {
      public void onComplete(String paramAnonymousString)
      {
        Log.e("Result:video", "odgledao");
        try
        {
          UnityPlayerActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              UnityPlayer.UnitySendMessage("Ads", "IZ_AS_OdgledanVideo", "1");
            }
          });
          return;
        }
        catch (Resources.NotFoundException paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
      }
      
      public void onIncomplete(String paramAnonymousString)
      {
        Log.e("Result:video", "PREKINUO");
      }
    });
    IncentivizedAd.fetch();
    onNewIntent(getIntent());
    getWindow().setFormat(2);
    this.mUnityPlayer = new UnityPlayer(this);
    setContentView(this.mUnityPlayer);
    this.layout_fadeInOut = new RelativeLayout(getApplicationContext());
    this.layout_fadeInOut.setVisibility(8);
    addContentView(this.layout_fadeInOut, new ViewGroup.LayoutParams(-1, -1));
    if (Build.VERSION.SDK_INT >= 14)
    {
      paramBundle = (RelativeLayout)getLayoutInflater().inflate(2130903055, null);
      this.layout_fadeInOut.addView(paramBundle, new ViewGroup.LayoutParams(-1, -1));
    }
    this.layout_fadeInOut.setBackgroundColor(Color.parseColor("#000000"));
    this.layout_fadeInOut.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UnityPlayerActivity.this.layout_fadeInOut.setVisibility(8);
      }
    });
    this.mUnityPlayer.requestFocus();
    this._activity = UnityPlayer.currentActivity;
    try
    {
      UnityPlayer.UnitySendMessage("GlavniGameObject", "PodesiJezik", getResources().getString(2131034152));
      if (PendingIntent.getBroadcast(getApplicationContext(), Integer.valueOf(getResources().getString(2131034113)).intValue(), new Intent(this, NotifReceiver.class), 536870912) != null)
      {
        if (i == 0) {
          break label376;
        }
        Log.d("myTag", "Alarm is already active");
      }
    }
    catch (Resources.NotFoundException paramBundle)
    {
      for (;;)
      {
        UnityPlayer.UnitySendMessage("GlavniGameObject", "PodesiJezik", "local_en");
        paramBundle.printStackTrace();
        continue;
        i = 0;
      }
      label376:
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          UnityPlayerActivity.this.ZakaziNotifikaciju(UnityPlayerActivity.this.VratiDatumZaNotif(), UnityPlayerActivity.this.getResources().getString(2131034153), UnityPlayerActivity.this.getResources().getString(2131034151), UnityPlayerActivity.this.getResources().getString(2131034151));
        }
      }, 3000L);
    }
  }
  
  protected void onDestroy()
  {
    this.mUnityPlayer.quit();
    super.onDestroy();
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mUnityPlayer.injectEvent(paramMotionEvent);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mUnityPlayer.injectEvent(paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mUnityPlayer.injectEvent(paramKeyEvent);
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if ((paramIntent != null) && (paramIntent.containsKey("notifikacija_id")))
    {
      int i = paramIntent.getInt("notifikacija_id");
      callFlurry_single("notifikacija_id:" + i);
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.mUnityPlayer.pause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mUnityPlayer.resume();
  }
  
  protected void onStart()
  {
    super.onStart();
    FlurryAgent.init(this, getResources().getString(2131034112));
    FlurryAgent.onStartSession(this, getResources().getString(2131034112));
    DetaktujMarket();
  }
  
  protected void onStop()
  {
    super.onStop();
    FlurryAgent.onEndSession(this);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mUnityPlayer.injectEvent(paramMotionEvent);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.mUnityPlayer.windowFocusChanged(paramBoolean);
  }
  
  @TargetApi(19)
  public void pickImage()
  {
    if (Build.VERSION.SDK_INT < 19)
    {
      localIntent = new Intent();
      localIntent.setType("image/*");
      localIntent.setAction("android.intent.action.GET_CONTENT");
      startActivityForResult(Intent.createChooser(localIntent, "Select Picture"), 150);
      return;
    }
    Intent localIntent = new Intent("android.intent.action.OPEN_DOCUMENT");
    localIntent.addCategory("android.intent.category.OPENABLE");
    localIntent.setType("image/*");
    startActivityForResult(localIntent, 3);
  }
  
  public String read(String paramString)
  {
    try
    {
      paramString = openFileInput(paramString);
      byte[] arrayOfByte = new byte[paramString.available()];
      while (paramString.read(arrayOfByte) != -1) {}
      paramString = "" + new String(arrayOfByte);
      return paramString;
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
      return "";
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public boolean reklame_nono_timer()
  {
    Log.e("reklame_nono_timer", "Ecur" + System.currentTimeMillis());
    Log.e("reklame_nono_timer", "Elast" + lasttime);
    Log.e("reklame_nono_timer", "E=" + (System.currentTimeMillis() - lasttime));
    if (lasttime == 0L)
    {
      lasttime = System.currentTimeMillis();
      return true;
    }
    if (System.currentTimeMillis() - lasttime > 60000L)
    {
      lasttime = System.currentTimeMillis();
      Log.e("reklame_nono_timer", " interstitial is ok to go" + (System.currentTimeMillis() - lasttime));
      return true;
    }
    Log.e("reklame_nono_timer", " interstitial timer still counting" + (System.currentTimeMillis() - lasttime));
    return false;
  }
  
  public void shareAPP(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    startActivity(Intent.createChooser(localIntent, "Share"));
  }
  
  public void shareAPP2(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    startActivity(Intent.createChooser(localIntent, "Share"));
  }
  
  public void wallpaper(String paramString)
  {
    Log.e("filter", "Wallpaper");
    WallpaperManager localWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
    paramString = new File(paramString);
    if (paramString.exists()) {
      paramString = BitmapFactory.decodeFile(paramString.getAbsolutePath());
    }
    try
    {
      localWallpaperManager.setBitmap(paramString);
      return;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void write(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = openFileOutput(paramString2, 0);
      paramString2.write(paramString1.getBytes());
      paramString2.close();
      return;
    }
    catch (FileNotFoundException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}
