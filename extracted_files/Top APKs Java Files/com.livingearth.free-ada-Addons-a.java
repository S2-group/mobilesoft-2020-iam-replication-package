package ada.Addons;

import ada.Info.InfoLib;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import app.JsInterface;
import app.RootActivity;
import app.Screens.Items.BarButtons;
import app.Screens.Items.BarCities;
import app.Screens.Items.BarInfo;
import app.Screens.ScreenCities;
import app.Screens.ScreenForecast;
import app.Screens.ScreenHome;
import app.Screens.ScreenMap;
import app.Screens.ScreenSettings;
import app.WeatherApp;
import app.b.a;
import app.b.b;
import app.d;
import app.e.h.b;
import app.f.a;
import com.beaconsinspace.android.beacon.detector.BISDetector;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.woxthebox.draglistview.DragListView;
import io.huq.sourcekit.HISourceKit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class a
{
  public static boolean a = false;
  static boolean b = false;
  public static boolean c = false;
  public static boolean d = false;
  public static boolean e = false;
  public static boolean f = true;
  public static int g = 1;
  public static boolean h = false;
  public static long i = 20L;
  public static long j = 10L;
  public static boolean k = true;
  public static int l = 5;
  public static int m = 5;
  public static boolean n = false;
  public static boolean o = false;
  public static boolean p = false;
  public static a q = new a();
  a r = null;
  public b s = null;
  c t = null;
  j u = null;
  
  public a() {}
  
  public static void a(Activity paramActivity)
  {
    if (paramActivity == null) {
      return;
    }
    if (q == null)
    {
      q = new a();
      g.a(paramActivity);
      a.d(paramActivity);
      c.a(paramActivity, false);
      j.d(paramActivity);
    }
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    if (q == null) {
      q = new a();
    }
    b.d();
    c(paramActivity);
    g.a(paramActivity);
    a.d(paramActivity);
    c.a(paramActivity, paramBoolean);
    j.d(paramActivity);
    if (!paramBoolean)
    {
      ada.f.a.a(1.0F);
      ada.f.a.b(true);
    }
  }
  
  public static void a(RootActivity paramRootActivity)
  {
    b(paramRootActivity);
  }
  
  public static void a(b.b paramB)
  {
    a(paramB, true);
  }
  
  public static void a(b.b paramB, boolean paramBoolean)
  {
    try
    {
      localA = q.r;
      if (WeatherApp.activity() == null) {
        return;
      }
      if (localA.b == null) {
        return;
      }
      if (app.g.a == b.b.d) {
        a.b();
      }
      if (paramB == b.b.c)
      {
        if (paramBoolean) {
          c.a();
        }
        localA.e = false;
        a.b();
      }
      switch (1.b[paramB.ordinal()])
      {
      case 9: 
        if (ScreenMap.c())
        {
          localA.e = false;
          a.b();
          return;
        }
      case 10: 
      case 11: 
        localA.e = true;
        return;
      }
    }
    catch (Exception|OutOfMemoryError paramB)
    {
      a localA;
      return;
    }
    localA.e = false;
    a.b();
    return;
    return;
  }
  
  public static void a(boolean paramBoolean)
  {
    try
    {
      q.t.f = paramBoolean;
      return;
    }
    catch (Exception localException) {}
  }
  
  public static boolean a()
  {
    try
    {
      boolean bool = q.t.f;
      return bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void b(Activity paramActivity)
  {
    if (!WeatherApp.d(paramActivity))
    {
      f.a.e();
      return;
    }
    if (b) {
      b.a(paramActivity);
    }
    if (b.c(paramActivity))
    {
      a(paramActivity, true);
      return;
    }
    b.d(paramActivity);
  }
  
  public static void c(Activity paramActivity)
  {
    MobileAds.initialize(paramActivity.getApplicationContext(), "ca-app-pub-1308649246551782~3217006153");
  }
  
  public static void d(Activity paramActivity)
  {
    if (q == null) {
      return;
    }
    a.a(paramActivity);
    b.a(paramActivity);
    c.a(paramActivity);
    j.a(paramActivity);
  }
  
  public static void e(Activity paramActivity)
  {
    if (q == null) {
      return;
    }
    a.b(paramActivity);
    b.b(paramActivity);
    c.b(paramActivity);
    j.b(paramActivity);
  }
  
  public static void f(Activity paramActivity)
  {
    if (q == null) {
      return;
    }
    a.c(paramActivity);
    b.c(paramActivity);
    c.c(paramActivity);
    j.c(paramActivity);
    q.r = null;
    q.s = null;
    q.t = null;
    q.u = null;
    q = null;
  }
  
  public static class a
  {
    static AdListener g = new AdListener()
    {
      public void onAdClosed()
      {
        a.q.r.e = false;
      }
      
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        a.a localA = a.q.r;
        localA.e = false;
        localA.f = true;
      }
      
      public void onAdLeftApplication()
      {
        a.q.r.e = false;
        a.k.a(WeatherApp.activity());
      }
      
      public void onAdLoaded()
      {
        a.a localA = a.q.r;
        localA.e = true;
        localA.f = false;
        a.a(app.g.a);
      }
      
      public void onAdOpened()
      {
        a.a localA = a.q.r;
      }
    };
    volatile boolean a = false;
    AdView b = null;
    RelativeLayout c = null;
    RelativeLayout d = null;
    boolean e = false;
    boolean f = false;
    
    public a() {}
    
    public static int a()
    {
      a localA = a.q.r;
      if (localA.b != null) {
        return localA.c.getHeight();
      }
      return 0;
    }
    
    public static AdView a(Activity paramActivity, int paramInt, AdListener paramAdListener, boolean paramBoolean)
    {
      if (!WeatherApp.d(paramActivity)) {
        return null;
      }
      try
      {
        a.c(paramActivity);
        Object localObject = (RelativeLayout)paramActivity.findViewById(paramInt);
        if (localObject == null) {
          return null;
        }
        paramActivity = new AdView(paramActivity);
        paramActivity.setAdSize(AdSize.SMART_BANNER);
        if (paramBoolean) {
          paramActivity.setAdUnitId("ca-app-pub-1308649246551782/3077405354");
        } else {
          paramActivity.setAdUnitId("ca-app-pub-1308649246551782/6030871757");
        }
        ((RelativeLayout)localObject).addView(paramActivity);
        localObject = a.k.b();
        paramActivity.setAdListener(paramAdListener);
        paramActivity.loadAd((AdRequest)localObject);
        return paramActivity;
      }
      catch (Exception paramActivity)
      {
        return null;
      }
      catch (OutOfMemoryError paramActivity) {}
      return null;
    }
    
    public static void a(Activity paramActivity)
    {
      try
      {
        paramActivity = a.q.r;
        if (paramActivity.b != null) {
          paramActivity.b.pause();
        }
        return;
      }
      catch (Exception|OutOfMemoryError paramActivity) {}
    }
    
    public static void a(b.b paramB)
    {
      Object localObject = WeatherApp.activity();
      if ((localObject != null) && (a.q != null) && (a.q.r != null) && (a.q.r.b != null))
      {
        if (!WeatherApp.d((Context)localObject)) {
          return;
        }
        a localA = a.q.r;
        localObject = localA.c;
        if (localObject != null) {
          ((RelativeLayout)localObject).bringToFront();
        }
        int i = WeatherApp.activity().b;
        if (paramB == b.b.l)
        {
          paramB = (RelativeLayout.LayoutParams)((RelativeLayout)localObject).getLayoutParams();
          i += 0;
          paramB.bottomMargin = i;
          paramB.setMargins(0, 0, 0, i);
          ((RelativeLayout)localObject).setLayoutParams(paramB);
          c();
          if (localA.d != null) {
            localA.d.setVisibility(8);
          }
          return;
        }
        if (paramB == b.b.m)
        {
          localA.e = false;
          b();
          return;
        }
        int j = app.e.c.e();
        if (paramB == b.b.d)
        {
          if (ScreenMap.get() != null)
          {
            int k = app.e.c.f();
            int m = app.e.c.g();
            paramB = (RelativeLayout.LayoutParams)((RelativeLayout)localObject).getLayoutParams();
            i = j + k + m + i;
            paramB.bottomMargin = i;
            paramB.setMargins(0, 0, 0, i);
            ((RelativeLayout)localObject).setLayoutParams(paramB);
          }
          if (ScreenMap.c())
          {
            b();
            return;
          }
          c();
          return;
        }
        if (paramB == b.b.e)
        {
          paramB = ScreenCities.get();
          if (paramB != null)
          {
            paramB = (DragListView)paramB.findViewById(2131231363);
            paramB = (RelativeLayout.LayoutParams)((RelativeLayout)localObject).getLayoutParams();
            paramB.setMargins(0, 0, 0, j + i + app.e.c.f());
            ((RelativeLayout)localObject).setLayoutParams(paramB);
          }
          c();
          return;
        }
        if (paramB == b.b.g)
        {
          paramB = ScreenSettings.get();
          if (paramB != null)
          {
            paramB = (ScrollView)paramB.findViewById(2131231390);
            paramB = (RelativeLayout.LayoutParams)((RelativeLayout)localObject).getLayoutParams();
            i = j + i;
            paramB.bottomMargin = i;
            paramB.setMargins(0, 0, 0, i);
            ((RelativeLayout)localObject).setLayoutParams(paramB);
          }
          c();
          return;
        }
        return;
      }
    }
    
    public static void b()
    {
      try
      {
        if (a.q == null) {
          return;
        }
        a localA = a.q.r;
        if (localA == null) {
          return;
        }
        if (localA.b != null) {
          localA.b.setVisibility(8);
        }
        if (localA.d != null) {
          localA.d.setVisibility(8);
        }
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
    
    public static void b(Activity paramActivity)
    {
      try
      {
        paramActivity = a.q.r;
        if (paramActivity.b != null) {
          paramActivity.b.resume();
        }
        return;
      }
      catch (Exception|OutOfMemoryError paramActivity) {}
    }
    
    public static void c()
    {
      a localA = a.q.r;
      try
      {
        RootActivity localRootActivity = WeatherApp.activity();
        if ((localRootActivity != null) && (!WeatherApp.d(localRootActivity))) {
          return;
        }
        if (localA.b != null)
        {
          localA.b.setVisibility(0);
          localA.b.resume();
        }
        if (localA.d != null) {
          localA.d.setVisibility(0);
        }
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
    
    public static void c(Activity paramActivity)
    {
      try
      {
        paramActivity = a.q.r;
        if (paramActivity.b != null) {
          paramActivity.b.destroy();
        }
        paramActivity.b = null;
        paramActivity.a = false;
        return;
      }
      catch (Exception|OutOfMemoryError paramActivity) {}
    }
    
    public static void d(Activity paramActivity)
    {
      a localA = a.q.r;
      if ((paramActivity != null) && (!localA.a) && (WeatherApp.d(paramActivity)) && (!InfoLib.isVersion(paramActivity)))
      {
        if (!a.b.c(paramActivity)) {
          return;
        }
        localA.a = true;
        localA.c = ((RelativeLayout)paramActivity.findViewById(2131230784));
        localA.d = ((RelativeLayout)paramActivity.findViewById(2131230785));
        RelativeLayout localRelativeLayout = (RelativeLayout)paramActivity.findViewById(2131230786);
        if (localRelativeLayout != null) {
          if (app.e.h.a("com.livingearth.pro")) {
            localRelativeLayout.setVisibility(8);
          } else {
            localRelativeLayout.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                app.e.h.a(WeatherApp.activity(), "com.livingearth.pro", true);
                MyFabric.send("Select screen", "Goto update", "");
              }
            });
          }
        }
        localA.b = new AdView(paramActivity);
        localA.b.setAdSize(AdSize.SMART_BANNER);
        localA.b.setAdUnitId("ca-app-pub-1308649246551782/9123938954");
        localA.c.addView(localA.b);
        localA.e = false;
        localA.b.setAdListener(g);
        localA.d();
        b();
        return;
      }
    }
    
    public void d()
    {
      if (this.b == null) {
        return;
      }
      try
      {
        AdRequest localAdRequest = a.k.b();
        this.b.loadAd(localAdRequest);
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
  }
  
  public static class b
  {
    RelativeLayout a = null;
    boolean b = false;
    WebView c = null;
    Runnable d = new Runnable()
    {
      public void run()
      {
        int i = 0;
        int j;
        do
        {
          localObject = ada.f.a.getInstance();
          if ((localObject == null) || (((ada.f.a)localObject).c == null) || (i < 6)) {
            SystemClock.sleep(500L);
          }
          j = i + 1;
          i = j;
        } while (j <= 6);
        Object localObject = WeatherApp.activity();
        if (localObject == null) {
          return;
        }
        ((RootActivity)localObject).runOnUiThread(new Runnable()
        {
          public void run()
          {
            RootActivity localRootActivity = WeatherApp.activity();
            RelativeLayout localRelativeLayout = (RelativeLayout)localRootActivity.findViewById(d.b(localRootActivity, "root_container"));
            if (a.b.this.a != null)
            {
              a.b.this.a.bringToFront();
            }
            else
            {
              a.b.this.a = new RelativeLayout(localRootActivity);
              a.b.this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
              a.b.this.c = new WebView(localRootActivity);
              a.b.this.c.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
              a.b.this.c.setBackgroundColor(-1);
              WebSettings localWebSettings = a.b.this.c.getSettings();
              localWebSettings.setTextZoom(100);
              localWebSettings.setJavaScriptEnabled(true);
              localWebSettings.setAllowUniversalAccessFromFileURLs(true);
              a.b.this.c.setWebViewClient(new a.h(null));
              a.b.this.c.setWebChromeClient(new WebChromeClient());
              a.b.this.c.addJavascriptInterface(new JsInterface(localRootActivity), "AndroidInterface");
              if (!Locale.getDefault().getLanguage().equalsIgnoreCase("ru")) {
                a.b.this.c.loadUrl("file:///android_asset/consentform2.html");
              } else {
                a.b.this.c.loadUrl("file:///android_asset/consentform2 _rus.html");
              }
              a.b.this.a.addView(a.b.this.c);
              localRelativeLayout.addView(a.b.this.a);
              ada.f.a.a(0.0F);
              ada.f.a.b(true);
            }
            a.b.this.b = true;
          }
        });
      }
    };
    Thread e = new Thread(this.d);
    
    public b() {}
    
    public static void a()
    {
      Object localObject = a.q.s;
      ((b)localObject).b = false;
      if (((b)localObject).a == null) {
        return;
      }
      localObject = WeatherApp.activity();
      b((Context)localObject);
      ((RootActivity)localObject).runOnUiThread(new Runnable()
      {
        public void run()
        {
          a.a(WeatherApp.activity(), false);
        }
      });
    }
    
    public static void a(Activity paramActivity) {}
    
    public static void a(Context paramContext)
    {
      j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_consent2");
    }
    
    public static void b()
    {
      app.e.h.a(WeatherApp.activity(), "com.livingearth.pro", true);
      MyFabric.send("Select screen", "Goto update", "");
    }
    
    public static void b(Activity paramActivity) {}
    
    public static void b(Context paramContext)
    {
      j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_consent2", 1);
    }
    
    public static void c(Activity paramActivity) {}
    
    public static boolean c()
    {
      try
      {
        b localB = a.q.s;
        if ((localB.c != null) && (localB.b) && (localB.c.canGoBack()))
        {
          localB.c.goBack();
          return true;
        }
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      return false;
    }
    
    public static boolean c(Context paramContext)
    {
      try
      {
        int i = j.e(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_consent2");
        return i != -1;
      }
      catch (Exception paramContext) {}
      return false;
    }
    
    public static void d()
    {
      try
      {
        if (a.q.s.a == null) {
          return;
        }
        RelativeLayout localRelativeLayout = (RelativeLayout)a.q.s.a.getParent();
        if (localRelativeLayout == null) {
          return;
        }
        localRelativeLayout.removeView(a.q.s.a);
        a.q.s.a = null;
        return;
      }
      catch (Exception localException) {}
    }
    
    public static void d(Activity paramActivity)
    {
      ada.f.a.a(0.0F);
      try
      {
        Thread.State localState;
        if (a.q != null)
        {
          if (a.q.s == null) {
            return;
          }
          paramActivity = a.q.s;
          localState = paramActivity.e.getState();
        }
        switch (a.1.c[localState.ordinal()])
        {
        case 2: 
          if (paramActivity.a != null)
          {
            d();
            paramActivity.b = false;
          }
          paramActivity.e = new Thread(paramActivity.d);
          paramActivity.e.start();
          return;
        case 1: 
          paramActivity.e.start();
          return;
          return;
        }
      }
      catch (Exception paramActivity) {}
    }
  }
  
  public static class c
  {
    volatile boolean a = false;
    volatile boolean b = false;
    volatile boolean c = true;
    volatile boolean d = true;
    volatile boolean e = false;
    volatile boolean f = false;
    volatile boolean g = true;
    volatile long h = 0L;
    volatile long i = 50L;
    volatile long j = 50L;
    volatile InterstitialAd k = null;
    Runnable l = new Runnable()
    {
      public void run()
      {
        try
        {
          SystemClock.sleep(1000L);
          if (a.c.this.a)
          {
            a.c.this.c = false;
            a.c.this.g();
            return;
          }
          while (app.e.i.a() - a.c.this.h < a.c.this.j)
          {
            SystemClock.sleep(500L);
            if (a.c.this.a)
            {
              a.c.this.g();
              return;
            }
          }
          return;
        }
        catch (Exception localException) {}
      }
    };
    Runnable m = new Runnable()
    {
      public void run()
      {
        if (a.a)
        {
          SystemClock.sleep(1500L);
          a.c.this.c = false;
          a.c.this.f();
          o.k(WeatherApp.activity());
          return;
        }
        while (app.e.i.a() - a.c.this.h < a.c.this.i)
        {
          SystemClock.sleep(500L);
          if (a.c.this.a)
          {
            a.c.this.g();
            a.c.this.f();
            return;
          }
        }
        a.c.this.c = false;
        a.c.this.f();
        o.k(WeatherApp.activity());
      }
    };
    Thread n = null;
    AdListener o = new AdListener()
    {
      public void onAdClosed()
      {
        a.n = false;
        a.o = true;
        a.p = false;
        a.c.this.e = false;
        a.c.this.f = false;
        if (a.c.this.c)
        {
          a.c.this.f();
          BarInfo.f();
        }
        try
        {
          app.d.g.c();
          o.k(WeatherApp.activity());
          a.c.this.c = false;
          a.c.this.d = true;
          a.c.d(WeatherApp.activity());
          return;
          a.c.this.d = true;
          a.c.d(WeatherApp.activity());
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
      
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        a.c.this.b = false;
        a.c.this.a = false;
        if (a.c.this.c)
        {
          if (a.c.this.d)
          {
            a.c.this.d = false;
            a.c.d(WeatherApp.activity());
            return;
          }
          a.c.this.f();
          o.k(WeatherApp.activity());
          return;
        }
        if (a.c.this.d)
        {
          a.c.this.d = false;
          a.c.d(WeatherApp.activity());
          return;
        }
        a.c.d();
      }
      
      public void onAdLeftApplication()
      {
        a.n = false;
        a.o = false;
        a.p = true;
        a.c.this.e = false;
        a.c.this.f = false;
        if (a.c.this.c)
        {
          a.k.a(WeatherApp.activity());
          return;
        }
        a.k.a(WeatherApp.activity());
      }
      
      public void onAdLoaded()
      {
        a.c.this.b = false;
        a.c.this.a = true;
        a.c.this.e = false;
        RootActivity localRootActivity = WeatherApp.activity();
        if (localRootActivity == null) {
          return;
        }
        if ((a.c.this.c) && (app.a.i.s(localRootActivity))) {
          return;
        }
        if (a.k.a(localRootActivity)) {}
        try
        {
          app.d.g.c();
          return;
        }
        catch (Exception localException) {}
        return;
      }
      
      public void onAdOpened()
      {
        a.c.this.b = false;
        a.c.this.a = false;
        a.n = true;
        a.o = false;
        a.p = false;
        a.c.this.e = false;
        a.c.this.f = true;
        if (a.c.this.c)
        {
          a.c.this.f();
          return;
        }
        a.c.d();
      }
    };
    
    public c() {}
    
    public static void a()
    {
      try
      {
        RootActivity localRootActivity = WeatherApp.activity();
        if (localRootActivity == null) {
          return;
        }
        if (a.k.a(localRootActivity)) {
          return;
        }
        Object localObject1 = (RelativeLayout)localRootActivity.findViewById(d.b("container"));
        if (localObject1 == null) {
          return;
        }
        if (!WeatherApp.d(localRootActivity)) {
          return;
        }
        if (InfoLib.isVersion(localRootActivity)) {
          return;
        }
        if (!a.b.c(localRootActivity)) {
          return;
        }
        Object localObject2 = app.a.g.d(localRootActivity);
        if (localObject2 != null)
        {
          if (((ArrayList)localObject2).size() < 1) {
            return;
          }
          if ((RelativeLayout)((RelativeLayout)localObject1).findViewById(d.b("wait_root")) == null)
          {
            localObject2 = (RelativeLayout)((LayoutInflater)localRootActivity.getSystemService("layout_inflater")).inflate(d.d(WeatherApp.activity(), "wait_root"), (ViewGroup)localObject1, false);
            ((RelativeLayout)localObject2).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView) {}
            });
            ((RelativeLayout)localObject1).addView((View)localObject2);
          }
          a.a.b();
          localObject1 = a.q.t;
          ((c)localObject1).n = new Thread(((c)localObject1).l);
          ((c)localObject1).h = app.e.i.a();
          ((c)localObject1).n.start();
          if ((!((c)localObject1).a) && (!((c)localObject1).b))
          {
            ((c)localObject1).c = false;
            ((c)localObject1).d = true;
            d(localRootActivity);
          }
        }
        else
        {
          return;
        }
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
    
    public static void a(Activity paramActivity)
    {
      try
      {
        paramActivity = a.q.t;
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void a(Activity paramActivity, boolean paramBoolean)
    {
      c localC = a.q.t;
      localC.g = true;
      localC.c = paramBoolean;
      localC.d = true;
      d(paramActivity);
      localC.h = app.e.i.a();
      localC.n = new Thread(localC.m);
      if (paramBoolean) {
        localC.n.start();
      }
    }
    
    public static RelativeLayout b()
    {
      try
      {
        Object localObject = WeatherApp.activity();
        if (localObject == null) {
          return null;
        }
        localObject = (RelativeLayout)((RootActivity)localObject).findViewById(d.b("container"));
        if (localObject == null) {
          return null;
        }
        return localObject;
      }
      catch (Exception localException) {}
      return null;
    }
    
    public static void b(Activity paramActivity)
    {
      try
      {
        paramActivity = a.q.t;
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static RelativeLayout c()
    {
      try
      {
        RelativeLayout localRelativeLayout = b();
        if (localRelativeLayout == null) {
          return null;
        }
        localRelativeLayout = (RelativeLayout)localRelativeLayout.findViewById(d.b("wait_root"));
        return localRelativeLayout;
      }
      catch (Exception localException) {}
      return null;
    }
    
    public static void c(Activity paramActivity)
    {
      try
      {
        paramActivity = a.q.t;
        if (!paramActivity.n.isInterrupted()) {
          paramActivity.n.interrupt();
        }
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void d()
    {
      try
      {
        RelativeLayout localRelativeLayout1 = b();
        final RelativeLayout localRelativeLayout2 = c();
        if ((localRelativeLayout2 != null) && (localRelativeLayout1 != null)) {
          new Thread(new Runnable()
          {
            public void run()
            {
              SystemClock.sleep(50L);
              WeatherApp.activity().runOnUiThread(new Runnable()
              {
                public void run()
                {
                  if (app.g.a == b.b.b)
                  {
                    app.d.g.a(false);
                    app.d.g.a(0);
                  }
                  a.c.2.this.a.removeView(a.c.2.this.b);
                  BarInfo.a(b.a.a, true);
                  BarCities.a(b.a.a, true);
                  if (app.g.b == b.b.b) {
                    ScreenForecast.a(b.a.b, true);
                  } else {
                    ScreenForecast.a(b.a.a, true);
                  }
                  ScreenSettings.b(b.a.a, true);
                  ScreenHome.b(b.a.b, true);
                  ScreenCities.b(b.a.a, true);
                  ScreenMap.b(b.a.a, true);
                  app.g.a = b.b.c;
                  app.i.a = false;
                  BarButtons.a(app.g.a, app.g.b, true);
                  app.i.a();
                }
              });
            }
          }).start();
        }
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
    
    public static void d(Activity paramActivity)
    {
      if (paramActivity == null) {
        return;
      }
      try
      {
        if (a.q != null)
        {
          if (a.q.t == null) {
            return;
          }
          localObject = a.q.t;
          if ((((c)localObject).k != null) && (((c)localObject).b)) {
            return;
          }
          if (((c)localObject).k != null) {
            ((c)localObject).k.setAdListener(null);
          }
          ((c)localObject).k = new InterstitialAd(paramActivity);
          if (((c)localObject).c)
          {
            if (((c)localObject).d) {
              ((c)localObject).k.setAdUnitId("ca-app-pub-1308649246551782/4550704165");
            } else {
              ((c)localObject).k.setAdUnitId("ca-app-pub-1308649246551782/5641986064");
            }
          }
          else if (((c)localObject).d) {
            ((c)localObject).k.setAdUnitId("ca-app-pub-1308649246551782/3754189328");
          } else {
            ((c)localObject).k.setAdUnitId("ca-app-pub-1308649246551782/7887313947");
          }
          ((c)localObject).k.setAdListener(((c)localObject).o);
          ((c)localObject).e();
          return;
        }
        return;
      }
      catch (Exception paramActivity)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("e:");
        ((StringBuilder)localObject).append(paramActivity.getLocalizedMessage());
        ada.e.a.a(((StringBuilder)localObject).toString());
      }
    }
    
    void e()
    {
      this.b = true;
      this.a = false;
      AdRequest localAdRequest = a.k.a();
      this.k.loadAd(localAdRequest);
    }
    
    public void f()
    {
      if (!this.g) {
        return;
      }
      try
      {
        RootActivity localRootActivity = WeatherApp.activity();
        if (localRootActivity == null) {
          return;
        }
        this.g = false;
        localRootActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              app.d.g.c();
              f.a.f();
              f.a.g();
              return;
            }
            catch (Exception|OutOfMemoryError localException) {}
          }
        });
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
    
    public void g()
    {
      RootActivity localRootActivity = WeatherApp.activity();
      if (localRootActivity == null) {
        return;
      }
      if (app.e.h.c(localRootActivity)) {
        return;
      }
      try
      {
        localRootActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            a.c.this.k.show();
          }
        });
        return;
      }
      catch (Exception|OutOfMemoryError localException) {}
    }
  }
  
  public static class d
  {
    public static void a(Activity paramActivity)
    {
      try
      {
        if (e(paramActivity)) {
          a.a(true);
        }
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void a(Activity paramActivity, boolean paramBoolean)
    {
      try
      {
        if ((app.g.a != b.b.g) && (paramBoolean) && (!a.q.s.b) && (!c.a)) {
          return;
        }
        if ((e(paramActivity)) && (a.n) && (a.a())) {
          a.n = false;
        }
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void b(Activity paramActivity)
    {
      try
      {
        if (e(paramActivity)) {
          a.a(false);
        }
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void c(Activity paramActivity)
    {
      try
      {
        if (ScreenSettings.a) {
          return;
        }
        if (Build.VERSION.SDK_INT < 24)
        {
          if (l.a)
          {
            l.a = false;
            return;
          }
          if (h.a) {
            return;
          }
        }
        if ((!a.a()) && (!a.q.s.b) && (!c.a)) {
          a.k.a(paramActivity);
        }
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void d(Activity paramActivity)
    {
      try
      {
        if (e(paramActivity)) {
          a.a(true);
        }
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    private static boolean e(Activity paramActivity)
    {
      return (paramActivity.getLocalClassName().equalsIgnoreCase("com.google.android.gms.ads.AdActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.applovin.adview.AppLovinInterstitialActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.applovin.adview.AppLovinConfirmationActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.adcolony.sdk.AdColonyInterstitialActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.adcolony.sdk.AdColonyAdViewActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.mopub.mobileads.MoPubActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.mopub.mobileads.MraidActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.mopub.common.MoPubBrowser")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.mopub.mobileads.MraidVideoPlayerActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.mopub.mobileads.RewardedMraidActivity")) || (paramActivity.getLocalClassName().equalsIgnoreCase("com.google.ads.mediation.admob.AdMobAdapter"));
    }
  }
  
  public static class e
  {
    public static void a(Context paramContext)
    {
      try
      {
        BISDetector.a("92D7F18111324C0BADD88783A5F6FCEC57E8EFF00D920490192436", paramContext.getApplicationContext());
        return;
      }
      catch (Exception|OutOfMemoryError paramContext) {}
    }
  }
  
  public static class f
  {
    public static void a(Activity paramActivity)
    {
      try
      {
        HISourceKit.getInstance().recordWithAPIKey("6612ea48-548a-4e5b-bfbc-f8ec01aa598a", paramActivity.getApplication());
        return;
      }
      catch (Exception|OutOfMemoryError paramActivity) {}
    }
  }
  
  public static class g
  {
    public static void a(Activity paramActivity)
    {
      if (paramActivity == null) {}
    }
  }
  
  private static class h
    extends WebViewClient
  {
    private h() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      try
      {
        f.a.a(false);
      }
      catch (Exception paramWebView)
      {
        for (;;)
        {
          try
          {
            f.a.f();
            return;
          }
          catch (Exception paramWebView) {}
          paramWebView = paramWebView;
        }
      }
    }
  }
  
  public static class i
  {
    public static int a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
    {
      if (!a.k) {
        return 0;
      }
      return 0;
    }
    
    static Uri a(String paramString)
    {
      if (paramString == null) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://play.google.com/store/apps/details?id=");
      localStringBuilder.append(paramString);
      return Uri.parse(localStringBuilder.toString());
    }
    
    public static boolean a(Context paramContext)
    {
      return f(paramContext) == a.e;
    }
    
    static boolean a(Context paramContext, String paramString)
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext()) {
        if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
          return true;
        }
      }
      return false;
    }
    
    static Uri b(String paramString)
    {
      if (paramString == null) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("amzn://apps/android?p=");
      localStringBuilder.append(paramString);
      return Uri.parse(localStringBuilder.toString());
    }
    
    public static boolean b(Context paramContext)
    {
      if (!a.k) {
        return false;
      }
      a localA = f(paramContext);
      if (a.1.a[localA.ordinal()] != 2) {
        return false;
      }
      if (Looper.getMainLooper().equals(Looper.myLooper())) {
        h(paramContext);
      } else {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            a.i.h(this.a);
          }
        });
      }
      return true;
    }
    
    public static boolean c(Context paramContext)
    {
      return false;
    }
    
    public static void d(Context paramContext)
    {
      if (!a.k) {}
    }
    
    public static void e(Context paramContext)
    {
      if (!a.k) {
        return;
      }
      if (h.a) {
        return;
      }
      a localA = f(paramContext);
      long l = g(paramContext);
      switch (a.1.a[localA.ordinal()])
      {
      default: 
        
      case 4: 
        j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state", a.a(a.e));
        return;
      case 3: 
        if (l >= a.m - 2)
        {
          j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state", a.a(a.d));
          return;
        }
        j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter", l + 1L);
        return;
      case 1: 
        if (l >= a.l - 1)
        {
          j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state", a.a(a.b));
          return;
        }
        j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter", l + 1L);
      }
    }
    
    static a f(Context paramContext)
    {
      try
      {
        j = j.e(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state");
        i = j;
        if (j != -1) {}
      }
      catch (Exception paramContext)
      {
        label49:
        for (;;) {}
      }
      try
      {
        j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state", a.a(a.a));
        j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter", 0L);
        paramContext = a.a;
        return paramContext;
      }
      catch (Exception paramContext)
      {
        i = j;
        break label49;
      }
      i = 0;
      return a.a(i);
    }
    
    static long g(Context paramContext)
    {
      long l2;
      try
      {
        l2 = j.d(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter");
        l1 = l2;
        if (l2 != -1L) {}
      }
      catch (Exception paramContext)
      {
        long l1;
        for (;;) {}
      }
      try
      {
        j.a(paramContext, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter", 1L);
        return 1L;
      }
      catch (Exception paramContext) {}
      l1 = 0L;
      return l1;
      return l2;
    }
    
    static void h(Context paramContext)
    {
      try
      {
        Object localObject = paramContext.getResources();
        String str3 = ((Resources)localObject).getString(ScreenSettings.c("my_rate_title"));
        String str2 = ((Resources)localObject).getString(ScreenSettings.c("my_rate_text"));
        String str1 = ((Resources)localObject).getString(ScreenSettings.c("my_rate_no"));
        localObject = ((Resources)localObject).getString(ScreenSettings.c("my_rate_yes"));
        str3 = app.a.c.b(str3, paramContext);
        str2 = app.a.c.b(str2, paramContext);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext, b.a());
        localBuilder.setCancelable(false);
        localBuilder.setNegativeButton(str1, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.cancel();
            try
            {
              j.a(this.a, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state", a.i.a.a(a.i.a.d));
              j.a(this.a, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter", 0L);
              return;
            }
            catch (Exception paramAnonymousDialogInterface) {}
          }
        });
        localBuilder.setPositiveButton((CharSequence)localObject, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.cancel();
            paramAnonymousDialogInterface = WeatherApp.activity();
            try
            {
              j.a(this.a, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_state", a.i.a.a(a.i.a.c));
              j.a(this.a, "com.deluxeware.weathernow.datasettingsv6", "setting_key_rate_counter", 0L);
            }
            catch (Exception localException)
            {
              for (;;)
              {
                try
                {
                  paramAnonymousInt = n.a(paramAnonymousDialogInterface);
                  if (a.g != 0) {
                    paramAnonymousInt = a.g;
                  }
                  if (paramAnonymousInt == 1) {
                    paramAnonymousDialogInterface.startActivity(a.i.i(paramAnonymousDialogInterface));
                  }
                  if (paramAnonymousInt == 2) {
                    paramAnonymousDialogInterface.startActivity(a.i.j(paramAnonymousDialogInterface));
                  }
                  return;
                }
                catch (Exception paramAnonymousDialogInterface) {}
                localException = localException;
              }
            }
          }
        });
        localBuilder.setTitle(str3);
        localBuilder.setMessage(str2);
        paramContext = localBuilder.create();
        if (paramContext.isShowing()) {
          paramContext.dismiss();
        }
        paramContext.show();
        return;
      }
      catch (Exception paramContext) {}
    }
    
    static Intent i(Context paramContext)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", a(paramContext.getPackageName()));
      if (a(paramContext, "com.android.vending")) {
        localIntent.setPackage("com.android.vending");
      }
      return localIntent;
    }
    
    static Intent j(Context paramContext)
    {
      return new Intent("android.intent.action.VIEW", b(paramContext.getPackageName()));
    }
    
    public static enum a
    {
      private a() {}
      
      public static int a(a paramA)
      {
        switch (a.1.a[paramA.ordinal()])
        {
        default: 
          return 4;
        case 4: 
          return 3;
        case 3: 
          return 2;
        case 2: 
          return 1;
        }
        return 0;
      }
      
      public static a a(int paramInt)
      {
        switch (paramInt)
        {
        default: 
          return e;
        case 3: 
          return d;
        case 2: 
          return c;
        case 1: 
          return b;
        }
        return a;
      }
    }
  }
  
  public static class j
  {
    RewardedVideoAd a = null;
    int b = -1;
    RewardedVideoAdListener c = new RewardedVideoAdListener()
    {
      public void onRewarded(RewardItem paramAnonymousRewardItem)
      {
        a.j.this.a();
      }
      
      public void onRewardedVideoAdClosed()
      {
        a.n = false;
        a.o = true;
        a.p = false;
        a.j.this.c();
        BarInfo.f();
        try
        {
          app.d.g.c();
          a.j.this.b();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
      
      public void onRewardedVideoAdFailedToLoad(int paramAnonymousInt) {}
      
      public void onRewardedVideoAdLeftApplication()
      {
        a.n = false;
        a.o = false;
        a.p = true;
      }
      
      public void onRewardedVideoAdLoaded()
      {
        RootActivity localRootActivity = WeatherApp.activity();
        if ((localRootActivity != null) && (!a.k.a(localRootActivity))) {
          return;
        }
        try
        {
          app.d.g.c();
          return;
        }
        catch (Exception localException) {}
      }
      
      public void onRewardedVideoAdOpened()
      {
        a.n = true;
        a.o = false;
        a.p = false;
      }
      
      public void onRewardedVideoCompleted() {}
      
      public void onRewardedVideoStarted() {}
    };
    
    public j() {}
    
    public static void a(Activity paramActivity)
    {
      try
      {
        a.q.u.a.pause(paramActivity);
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static boolean a(int paramInt)
    {
      if (InfoLib.isVersion(WeatherApp.activity())) {
        return false;
      }
      a.a(WeatherApp.activity());
      Object localObject = a.q.u;
      if (((j)localObject).a == null)
      {
        d(WeatherApp.activity());
        return false;
      }
      if (!((j)localObject).a.isLoaded()) {}
      try
      {
        ((j)localObject).c();
        return false;
      }
      catch (Exception localException) {}
      localObject = WeatherApp.activity();
      Resources localResources = ((RootActivity)localObject).getResources();
      if (app.a.g.d((Context)localObject).size() >= 2)
      {
        if (!WeatherApp.d((Context)localObject)) {
          return false;
        }
        int i = 16974394;
        if (Build.VERSION.SDK_INT < 23) {
          i = 3;
        }
        new AlertDialog.Builder(WeatherApp.activity(), i).setMessage(localResources.getString(d.c("ad_text"))).setPositiveButton(localResources.getString(d.c("ad_true")), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            try
            {
              a.q.u.b = this.a;
              a.q.u.a.show();
              return;
            }
            catch (OutOfMemoryError paramAnonymousDialogInterface) {}
          }
        }).setNegativeButton(localResources.getString(d.c("ad_false")), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            try
            {
              paramAnonymousDialogInterface.dismiss();
              return;
            }
            catch (Exception|OutOfMemoryError paramAnonymousDialogInterface) {}
          }
        }).setCancelable(false).create().show();
        return true;
      }
      return false;
      return false;
    }
    
    public static void b(Activity paramActivity)
    {
      try
      {
        a.q.u.a.resume(paramActivity);
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void c(Activity paramActivity)
    {
      try
      {
        j localJ = a.q.u;
        localJ.a.destroy(paramActivity);
        localJ.a = null;
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static void d(Activity paramActivity)
    {
      j localJ = a.q.u;
      try
      {
        if (localJ.a != null) {
          return;
        }
        localJ.a = MobileAds.getRewardedVideoAdInstance(paramActivity);
        localJ.a.setRewardedVideoAdListener(localJ.c);
        localJ.c();
        return;
      }
      catch (Exception|OutOfMemoryError paramActivity) {}
    }
    
    public void a()
    {
      if (this.b == -1) {
        return;
      }
      app.a.a localA = (app.a.a)ScreenCities.l.get(this.b);
      if ((localA != null) && (app.a.g.a(localA, false, WeatherApp.activity()) > 0))
      {
        ScreenCities.b(false);
        ScreenCities.e();
        ScreenCities.a(false);
      }
      this.b = -1;
    }
    
    public void b()
    {
      ScreenCities.a(false);
    }
    
    public void c()
    {
      AdRequest localAdRequest = a.k.a();
      this.a.loadAd("ca-app-pub-1308649246551782/8431800933", localAdRequest);
    }
  }
  
  public static class k
  {
    public static AdRequest a()
    {
      return new AdRequest.Builder().build();
    }
    
    public static void a(Activity paramActivity)
    {
      if (paramActivity == null) {
        return;
      }
      if (a.a()) {
        return;
      }
      try
      {
        h.b.a(paramActivity, 3000);
        paramActivity.finishAffinity();
        System.exit(0);
        return;
      }
      catch (Exception paramActivity) {}
    }
    
    public static boolean a(Context paramContext)
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(10).iterator();
      while (paramContext.hasNext())
      {
        Object localObject = (ActivityManager.RunningTaskInfo)paramContext.next();
        int i = ((ActivityManager.RunningTaskInfo)localObject).id;
        CharSequence localCharSequence = ((ActivityManager.RunningTaskInfo)localObject).description;
        localObject = ((ActivityManager.RunningTaskInfo)localObject).topActivity.getShortClassName();
        if ((localObject != null) && (((String)localObject).equalsIgnoreCase("com.google.android.gms.ads.AdActivity"))) {
          return true;
        }
      }
      return false;
    }
    
    public static AdRequest b()
    {
      return new AdRequest.Builder().build();
    }
  }
}
