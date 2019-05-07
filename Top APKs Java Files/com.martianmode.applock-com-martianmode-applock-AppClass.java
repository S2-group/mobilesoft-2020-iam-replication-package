package com.martianmode.applock;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.applovin.c.n;
import com.applovin.c.o;
import com.applovin.mediation.ApplovinAdapter;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.formats.b.b;
import com.google.android.gms.ads.formats.j;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.k;
import com.google.android.gms.ads.k.a;
import com.google.android.gms.ads.l.a;
import com.martianmode.applock.c.d;
import com.martianmode.applock.engine.api.APIBroadcast;
import com.martianmode.applock.engine.lock.engine2.ForegroundServiceController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class AppClass
  extends androidx.i.b
{
  com.google.android.gms.ads.b a;
  private Thread.UncaughtExceptionHandler b;
  private long c;
  private j d;
  private com.google.android.gms.ads.h e;
  private final Object f = new Object();
  private boolean g = false;
  private boolean h = false;
  private boolean i = false;
  private boolean j = false;
  private FrameLayout k;
  private ImageView l;
  private TextView m;
  private Intent n;
  
  public AppClass() {}
  
  private void a(FrameLayout paramFrameLayout, ImageView paramImageView, TextView paramTextView, boolean paramBoolean)
  {
    if (!d.a()) {
      d.a(getApplicationContext());
    }
    if (com.martianmode.applock.c.b.D()) {
      return;
    }
    this.i = paramBoolean;
    if (paramFrameLayout != null) {
      paramFrameLayout.setVisibility(8);
    }
    if (paramImageView != null) {
      paramImageView.setVisibility(0);
    }
    if (paramTextView != null) {
      paramTextView.setVisibility(0);
    }
    this.k = paramFrameLayout;
    this.l = paramImageView;
    this.m = paramTextView;
    if ((!this.g) && (!this.a.a()))
    {
      this.a.a(new com.google.android.gms.ads.c.a().a(ApplovinAdapter.class, new com.applovin.mediation.b.a().a(true).a()).b("B3EEABB8EE11C2BE770B684D95219ECB").a());
      return;
    }
    if ((this.g) && (paramBoolean) && (paramFrameLayout != null) && (paramImageView != null) && (paramTextView != null)) {
      b(paramFrameLayout, paramImageView, paramTextView);
    }
  }
  
  private void a(j paramJ, UnifiedNativeAdView paramUnifiedNativeAdView)
  {
    paramUnifiedNativeAdView.setMediaView((MediaView)paramUnifiedNativeAdView.findViewById(2131296304));
    paramUnifiedNativeAdView.setHeadlineView(paramUnifiedNativeAdView.findViewById(2131296303));
    paramUnifiedNativeAdView.setBodyView(paramUnifiedNativeAdView.findViewById(2131296300));
    paramUnifiedNativeAdView.setCallToActionView(paramUnifiedNativeAdView.findViewById(2131296301));
    paramUnifiedNativeAdView.setIconView(paramUnifiedNativeAdView.findViewById(2131296294));
    paramUnifiedNativeAdView.setPriceView(paramUnifiedNativeAdView.findViewById(2131296305));
    paramUnifiedNativeAdView.setStarRatingView(paramUnifiedNativeAdView.findViewById(2131296306));
    paramUnifiedNativeAdView.setStoreView(paramUnifiedNativeAdView.findViewById(2131296307));
    paramUnifiedNativeAdView.setAdvertiserView(paramUnifiedNativeAdView.findViewById(2131296292));
    ((TextView)paramUnifiedNativeAdView.getHeadlineView()).setText(paramJ.a());
    if (paramJ.c() == null)
    {
      paramUnifiedNativeAdView.getBodyView().setVisibility(4);
    }
    else
    {
      paramUnifiedNativeAdView.getBodyView().setVisibility(0);
      ((TextView)paramUnifiedNativeAdView.getBodyView()).setText(paramJ.c());
    }
    if (paramJ.e() == null)
    {
      paramUnifiedNativeAdView.getCallToActionView().setVisibility(4);
    }
    else
    {
      paramUnifiedNativeAdView.getCallToActionView().setVisibility(0);
      ((Button)paramUnifiedNativeAdView.getCallToActionView()).setText(paramJ.e());
    }
    if (paramJ.d() == null)
    {
      paramUnifiedNativeAdView.getIconView().setVisibility(8);
    }
    else
    {
      ((ImageView)paramUnifiedNativeAdView.getIconView()).setImageDrawable(paramJ.d().a());
      paramUnifiedNativeAdView.getIconView().setVisibility(0);
    }
    if (paramJ.i() == null)
    {
      paramUnifiedNativeAdView.getPriceView().setVisibility(4);
    }
    else
    {
      paramUnifiedNativeAdView.getPriceView().setVisibility(0);
      ((TextView)paramUnifiedNativeAdView.getPriceView()).setText(paramJ.i());
    }
    if (paramJ.h() == null)
    {
      paramUnifiedNativeAdView.getStoreView().setVisibility(4);
    }
    else
    {
      paramUnifiedNativeAdView.getStoreView().setVisibility(0);
      ((TextView)paramUnifiedNativeAdView.getStoreView()).setText(paramJ.h());
    }
    if (paramJ.g() == null)
    {
      paramUnifiedNativeAdView.getStarRatingView().setVisibility(4);
    }
    else
    {
      ((RatingBar)paramUnifiedNativeAdView.getStarRatingView()).setRating(paramJ.g().floatValue());
      paramUnifiedNativeAdView.getStarRatingView().setVisibility(0);
    }
    if (paramJ.f() == null)
    {
      paramUnifiedNativeAdView.getAdvertiserView().setVisibility(4);
    }
    else
    {
      ((TextView)paramUnifiedNativeAdView.getAdvertiserView()).setText(paramJ.f());
      paramUnifiedNativeAdView.getAdvertiserView().setVisibility(0);
    }
    paramUnifiedNativeAdView.setNativeAd(paramJ);
    paramJ = paramJ.j();
    if (paramJ.b()) {
      paramJ.a(new k.a()
      {
        public void d()
        {
          super.d();
        }
      });
    }
    this.h = true;
  }
  
  private void b()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        synchronized (AppClass.a(AppClass.this))
        {
          PackageManager localPackageManager = AppClass.this.getPackageManager();
          ArrayList localArrayList1 = new ArrayList();
          ArrayList localArrayList2 = new ArrayList();
          List localList = localPackageManager.getInstalledApplications(0);
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            if (localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
            {
              localArrayList1.add(localApplicationInfo.packageName);
              localArrayList2.add(localPackageManager.getApplicationLabel(localApplicationInfo).toString());
            }
          }
          if (!com.martianmode.applock.c.b.a()) {
            com.martianmode.applock.c.b.a(AppClass.this);
          }
          com.martianmode.applock.c.b.a(localArrayList1, localArrayList2);
          com.martianmode.applock.c.b.a(true);
          com.martianmode.applock.c.b.a(localList.size());
          return;
        }
      }
    }).start();
  }
  
  private void b(FrameLayout paramFrameLayout, ImageView paramImageView, TextView paramTextView)
  {
    if ((this.d != null) && (!this.j))
    {
      this.j = true;
      UnifiedNativeAdView localUnifiedNativeAdView = (UnifiedNativeAdView)LayoutInflater.from(getBaseContext()).inflate(2131492977, new UnifiedNativeAdView(getBaseContext()), false);
      a(this.d, localUnifiedNativeAdView);
      paramFrameLayout.removeAllViews();
      paramFrameLayout.setVisibility(0);
      paramFrameLayout.addView(localUnifiedNativeAdView);
      paramImageView.setVisibility(8);
      paramTextView.setVisibility(8);
      this.i = false;
      this.j = false;
    }
  }
  
  private void c()
  {
    if (!com.martianmode.applock.c.b.a()) {
      com.martianmode.applock.c.b.a(getApplicationContext());
    }
    if (!d.a()) {
      d.a(getApplicationContext());
    }
    if (com.martianmode.applock.c.b.D()) {
      return;
    }
    a();
    a(false, null);
    d();
  }
  
  private void d()
  {
    this.a = new com.google.android.gms.ads.b.a(getApplicationContext(), com.martianmode.applock.engine.ads.a.b).a(new -..Lambda.AppClass.PSqfXN9RPwPyHXiI7CDUJAk-xi0(this)).a(new com.google.android.gms.ads.a()
    {
      public void a()
      {
        super.a();
        AppClass.a(AppClass.this, true);
        if ((AppClass.b(AppClass.this)) && (AppClass.c(AppClass.this) != null) && (AppClass.d(AppClass.this) != null) && (AppClass.e(AppClass.this) != null)) {
          AppClass.a(AppClass.this, AppClass.c(AppClass.this), AppClass.d(AppClass.this), AppClass.e(AppClass.this));
        }
      }
      
      public void a(int paramAnonymousInt)
      {
        super.a(paramAnonymousInt);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Ad failed to load, error code: ");
        localStringBuilder.append(paramAnonymousInt);
        Log.e("AppClass", localStringBuilder.toString());
      }
      
      public void c()
      {
        super.c();
        AppClass.this.a.a(new com.google.android.gms.ads.c.a().a());
      }
      
      public void d()
      {
        super.d();
        AppClass.this.a(AppClass.c(AppClass.this));
      }
    }).a(new com.google.android.gms.ads.formats.c.a().a(new l.a().a(true).a()).a()).a();
    g();
  }
  
  private void e()
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("launcher.request");
      localIntentFilter.addAction("launcher.request.lock");
      getApplicationContext().registerReceiver(new APIBroadcast(), localIntentFilter);
    }
  }
  
  private void f()
  {
    a.a.a.b.a.a().b();
  }
  
  private void g()
  {
    a(null, null, null, false);
  }
  
  private void h()
  {
    this.b = Thread.getDefaultUncaughtExceptionHandler();
    this.c = Thread.currentThread().getId();
    Thread.setDefaultUncaughtExceptionHandler(new -..Lambda.AppClass.VcqL4pSGigiAksZMRu_VFsGsoZ0(this));
  }
  
  public void a()
  {
    this.g = false;
    this.h = false;
    this.i = false;
    this.k = null;
    this.l = null;
    this.m = null;
    this.d = null;
    d();
  }
  
  public void a(FrameLayout paramFrameLayout)
  {
    if (this.d != null) {
      this.d.k();
    }
    if (paramFrameLayout != null) {
      paramFrameLayout.removeAllViews();
    }
    a();
  }
  
  public void a(FrameLayout paramFrameLayout, ImageView paramImageView, TextView paramTextView)
  {
    if (this.h)
    {
      a(paramFrameLayout);
      return;
    }
    a(paramFrameLayout, paramImageView, paramTextView, true);
  }
  
  public void a(com.martianmode.applock.activities.a paramA)
  {
    if ((this.e != null) && (this.e.a()) && (paramA != null) && (paramA.j()))
    {
      this.e.b();
      return;
    }
    a(true, paramA);
  }
  
  public void a(final boolean paramBoolean, final com.martianmode.applock.activities.a paramA)
  {
    if (!d.a()) {
      d.a(getApplicationContext());
    }
    if (com.martianmode.applock.c.b.D()) {
      return;
    }
    if (this.e == null)
    {
      this.e = new com.google.android.gms.ads.h(getApplicationContext());
      this.e.a(com.martianmode.applock.engine.ads.a.a);
    }
    this.e.a(new com.google.android.gms.ads.a()
    {
      public void a()
      {
        super.a();
        if ((paramBoolean) && (paramA != null) && (paramA.j())) {
          AppClass.f(AppClass.this).b();
        }
      }
      
      public void c()
      {
        super.c();
        AppClass.this.a(false, paramA);
      }
    });
    this.e.a(new com.google.android.gms.ads.c.a().a());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (!d.a()) {
      d.a(this);
    }
    paramConfiguration = com.martianmode.applock.f.b.a(paramConfiguration).getDisplayLanguage();
    if (!paramConfiguration.equalsIgnoreCase(d.b("com.martianmode.applock.APP_LANGUAGE", "")))
    {
      d.a("com.martianmode.applock.APP_LANGUAGE", paramConfiguration);
      b();
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (com.a.a.a.a(this)) {
      return;
    }
    o.b(this);
    n.a(true, this);
    this.n = new Intent(this, com.martianmode.applock.engine.lock.c.class);
    com.a.a.a.a(this);
    io.fabric.sdk.android.c.a(this, new io.fabric.sdk.android.h[] { new Crashlytics() });
    com.github.ajalt.reprint.a.c.a(getApplicationContext());
    h();
    com.google.firebase.b.a(getApplicationContext());
    d.a(getApplicationContext());
    com.martianmode.applock.c.b.a(getApplicationContext());
    com.martianmode.applock.engine.b.b.a(getApplicationContext());
    com.martianmode.applock.engine.onupdate.a.a();
    com.martianmode.applock.engine.lock.b.a(getApplicationContext());
    ForegroundServiceController.d(this);
    e();
    if ((com.martianmode.applock.engine.ads.b.a()) && (!com.martianmode.applock.c.b.E()))
    {
      com.martianmode.applock.c.b.C();
      com.martianmode.applock.c.b.c(true);
    }
    i.a(this, "ca-app-pub-5301053235421044~6260178412");
    f();
    c();
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    Log.d("AppClass", "onTrimMemory called");
    if (this.e == null)
    {
      Log.d("AppClass", "onTrimMemory: loading interstitial");
      a(false, null);
    }
    if (this.d == null)
    {
      Log.d("AppClass", "onTrimMemory: loading native");
      a();
      g();
    }
  }
  
  public void onTerminate()
  {
    super.onTerminate();
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    Log.d("AppClass", "onTrimMemory called");
    if (this.e == null)
    {
      Log.d("AppClass", "onTrimMemory: loading interstitial");
      a(false, null);
    }
    if (this.d == null)
    {
      Log.d("AppClass", "onTrimMemory: loading native");
      a();
      g();
    }
  }
}
