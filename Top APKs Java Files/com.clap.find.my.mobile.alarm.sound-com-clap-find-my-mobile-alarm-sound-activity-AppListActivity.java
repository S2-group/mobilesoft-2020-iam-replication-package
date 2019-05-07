package com.clap.find.my.mobile.alarm.sound.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.clap.find.my.mobile.alarm.sound.ClapToFindPhoneApplication;
import com.clap.find.my.mobile.alarm.sound.adapter.AppListAdapter;
import com.clap.find.my.mobile.alarm.sound.common.Share;
import com.clap.find.my.mobile.alarm.sound.model.ApplicationModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppListActivity
  extends AppCompatActivity
{
  private ArrayList<ApplicationModel> al_all_apps = new ArrayList();
  private List<ApplicationInfo> applist;
  private AppListAdapter appsAdapter;
  private FirebaseAnalytics mFirebaseAnalytics;
  Activity n;
  ImageView o;
  ImageView p;
  private PackageManager packageManager = null;
  Boolean q = Boolean.valueOf(true);
  AdView r;
  RecyclerView s;
  
  public AppListActivity() {}
  
  private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramList.next();
      try
      {
        if ((this.packageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) && (!isSystemApp(localApplicationInfo.packageName)) && (!localApplicationInfo.packageName.equals("com.android.chrome")) && (!localApplicationInfo.packageName.equals("com.sec.android.app.popupcalculator")) && (!localApplicationInfo.packageName.equals("com.google.android.androidforwork"))) {
          localArrayList.add(localApplicationInfo);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  private void findViews()
  {
    this.s = ((RecyclerView)findViewById(2131296703));
  }
  
  private void initView()
  {
    this.packageManager = getPackageManager();
    this.s.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    this.s.setItemAnimator(new DefaultItemAnimator());
    new LoadApplications(null).execute(new Void[0]);
  }
  
  private boolean isSystemPackage(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private void loadAdsBanner()
  {
    try
    {
      this.r = ((AdView)findViewById(2131296298));
      AdRequest localAdRequest = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").addTestDevice("67F459F428BA080AC0E5D48751A42AD7").addTestDevice("03E2207FBED3E8811B414918D8077E25").addTestDevice("74527FD0DD7B0489CFB68BAED192733D").addTestDevice("7D27AE6CC478DBF13BAEFEB9F873562B").addTestDevice("E65765D74A642A5F0993F9107AE0B307").addTestDevice("86021572C8EFA2DD0DB69DB2BA2CA050").addTestDevice("EC0086E4DD57398BD70018389A92BB9A").addTestDevice("790037035108AEA31323422EBA149D03").addTestDevice("3A9619098ED320FC729B6ED2972C7536").addTestDevice("EC45B6A428CFB26E69ED771307C929D3").addTestDevice("65B441DD003840F64A6DD2C4AB4911DC").addTestDevice("7377159F8453DCC60F4109F19FA52FFE").addTestDevice("6951A7DFDC016130A7C94F5568794431").addTestDevice("F2AA27AFF8597B00E7124AC5F4BA0DDA").addTestDevice("AE74B0C567C2121A613144D22D3B0554").addTestDevice("2E4C00EE9959AC67D3372504F44CC93D").addTestDevice("657179176DE7AB836E2FEBEE00545FD4").build();
      this.r.loadAd(localAdRequest);
      this.r.setAdListener(new AdListener()
      {
        public void onAdClosed()
        {
          super.onAdClosed();
        }
        
        public void onAdFailedToLoad(int paramAnonymousInt)
        {
          super.onAdFailedToLoad(paramAnonymousInt);
          AppListActivity.this.r.setVisibility(8);
        }
        
        public void onAdLeftApplication()
        {
          super.onAdLeftApplication();
        }
        
        public void onAdLoaded()
        {
          super.onAdLoaded();
          AppListActivity.this.r.setVisibility(0);
        }
        
        public void onAdOpened()
        {
          super.onAdOpened();
        }
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void loadInterstialAd()
  {
    if (ClapToFindPhoneApplication.getInstance().mInterstitialAd.isLoaded())
    {
      Log.e("if", "if");
      this.o.setVisibility(0);
      return;
    }
    ClapToFindPhoneApplication.getInstance().mInterstitialAd.setAdListener(null);
    ClapToFindPhoneApplication.getInstance().mInterstitialAd = null;
    ClapToFindPhoneApplication.getInstance().ins_adRequest = null;
    ClapToFindPhoneApplication.getInstance().LoadAds();
    ClapToFindPhoneApplication.getInstance().mInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        super.onAdFailedToLoad(paramAnonymousInt);
        Log.e("fail", "fail");
        AppListActivity.this.o.setVisibility(8);
        AppListActivity.a(AppListActivity.this);
      }
      
      public void onAdLoaded()
      {
        super.onAdLoaded();
        Log.e("load", "load");
        AppListActivity.this.o.setVisibility(0);
      }
    });
  }
  
  private void setActionBar()
  {
    try
    {
      ((ImageView)findViewById(2131296526)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AppListActivity.this.onBackPressed();
        }
      });
      this.o = ((ImageView)findViewById(2131296542));
      this.p = ((ImageView)findViewById(2131296527));
      this.o.setVisibility(8);
      this.o.setBackgroundResource(2131230811);
      ((AnimationDrawable)this.o.getBackground()).start();
      loadInterstialAd();
      this.o.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AppListActivity.this.q = Boolean.valueOf(false);
          AppListActivity.this.o.setVisibility(8);
          AppListActivity.this.p.setVisibility(0);
          ((AnimationDrawable)AppListActivity.this.p.getBackground()).start();
          if (ClapToFindPhoneApplication.getInstance().requestNewInterstitial())
          {
            ClapToFindPhoneApplication.getInstance().mInterstitialAd.setAdListener(new AdListener()
            {
              public void onAdClosed()
              {
                super.onAdClosed();
                Log.e("ad cloced", "ad closed");
                AppListActivity.this.p.setVisibility(8);
                AppListActivity.this.o.setVisibility(8);
                AppListActivity.this.q = Boolean.valueOf(true);
                AppListActivity.a(AppListActivity.this);
              }
              
              public void onAdFailedToLoad(int paramAnonymous2Int)
              {
                super.onAdFailedToLoad(paramAnonymous2Int);
                Log.e("fail", "fail");
                AppListActivity.this.p.setVisibility(8);
                AppListActivity.this.o.setVisibility(8);
              }
              
              public void onAdLoaded()
              {
                super.onAdLoaded();
                Log.e("loaded", "loaded");
                AppListActivity.this.q = Boolean.valueOf(false);
                AppListActivity.this.p.setVisibility(8);
                AppListActivity.this.o.setVisibility(8);
              }
            });
            return;
          }
          Log.e("else", "else");
          AppListActivity.this.p.setVisibility(8);
          AppListActivity.this.o.setVisibility(8);
        }
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean isSystemApp(String paramString)
  {
    boolean bool2 = false;
    try
    {
      Object localObject = getPackageManager();
      paramString = ((PackageManager)localObject).getPackageInfo(paramString, 64);
      localObject = ((PackageManager)localObject).getPackageInfo("android", 64);
      boolean bool1 = bool2;
      if (paramString != null)
      {
        bool1 = bool2;
        if (paramString.signatures != null)
        {
          boolean bool3 = localObject.signatures[0].equals(paramString.signatures[0]);
          bool1 = bool2;
          if (bool3) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public void onBackPressed()
  {
    this.p.setVisibility(8);
    this.o.setVisibility(8);
    finish();
    overridePendingTransition(2130771985, 2130771988);
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131492892);
    this.n = this;
    this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.n);
    findViews();
    setActionBar();
    Share.loadAdsBanner(this, this.r);
    initView();
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.q.booleanValue()) {
      loadInterstialAd();
    }
  }
  
  private class LoadApplications
    extends AsyncTask<Void, Void, Void>
  {
    Dialog a;
    
    private LoadApplications() {}
    
    protected Void a(Void... paramVarArgs)
    {
      AppListActivity.a(AppListActivity.this, AppListActivity.b(AppListActivity.this, AppListActivity.b(AppListActivity.this).getInstalledApplications(128)));
      Log.e("applist", AppListActivity.c(AppListActivity.this).size() + "");
      int i = 0;
      while (i < AppListActivity.c(AppListActivity.this).size())
      {
        if (!((ApplicationInfo)AppListActivity.c(AppListActivity.this).get(i)).packageName.equals(AppListActivity.this.getPackageName()))
        {
          paramVarArgs = new ApplicationModel();
          paramVarArgs.setApp_name(((ApplicationInfo)AppListActivity.c(AppListActivity.this).get(i)).loadLabel(AppListActivity.b(AppListActivity.this)));
          paramVarArgs.setPackage_name(((ApplicationInfo)AppListActivity.c(AppListActivity.this).get(i)).packageName);
          paramVarArgs.setApp_icon(((ApplicationInfo)AppListActivity.c(AppListActivity.this).get(i)).loadIcon(AppListActivity.b(AppListActivity.this)));
          AppListActivity.d(AppListActivity.this).add(paramVarArgs);
        }
        i += 1;
      }
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      Log.e("TAG", "app list:==>" + AppListActivity.c(AppListActivity.this));
      if (this.a.isShowing()) {
        this.a.dismiss();
      }
      AppListActivity.a(AppListActivity.this, new AppListAdapter(AppListActivity.this, AppListActivity.d(AppListActivity.this), AppListActivity.b(AppListActivity.this)));
      AppListActivity.this.s.setAdapter(AppListActivity.e(AppListActivity.this));
      AppListActivity.e(AppListActivity.this).notifyDataSetChanged();
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      this.a = Share.showProgress(AppListActivity.this.n, "");
      this.a.show();
    }
  }
}
