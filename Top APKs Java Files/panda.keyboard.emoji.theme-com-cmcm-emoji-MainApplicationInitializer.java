package com.cmcm.emoji;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.support.annotation.Keep;
import cmcm.commercial.utils.ADCloudSwitcher;
import cmcm.wizard.f;
import com.android.inputmethod.keyboard.l;
import com.android.inputmethod.latin.ad.i;
import com.android.inputmethod.latin.ad.juhe.service.PermanentService;
import com.android.inputmethod.latin.ad.juhe.service.TimingAdService;
import com.android.inputmethod.latin.q;
import com.android.inputmethod.latin.utils.p;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.Const.Event;
import com.cmcm.adsdk.utils.ReportProxy;
import com.cmcm.emoji.alive.AliveReportService;
import com.cmcm.keyboard.theme.fcm.DeleteTokenService;
import com.crashlytics.android.a.a;
import com.crashlytics.android.core.k.a;
import com.ksmobile.common.cube.Cube;
import com.ksmobile.keyboard.commonutils.ac;
import com.ksmobile.keyboard.commonutils.r;
import com.mnt.MntLib;
import io.fabric.sdk.android.c.a;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import okio.k;
import panda.keyboard.emoji.sync.SyncManager;

class MainApplicationInitializer
{
  private int a;
  private String b;
  private final Application c;
  
  @Keep
  MainApplicationInitializer(Application paramApplication, int paramInt, String paramString)
  {
    this.c = paramApplication;
    this.a = paramInt;
    this.b = paramString;
    a();
  }
  
  static ArrayList<String> a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    int j = ((List)localObject).size();
    if (j == 0) {
      return null;
    }
    paramContext = new ArrayList();
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i = 0;
    while (i < j)
    {
      String str = ((PackageInfo)((List)localObject).get(i)).packageName;
      if (localStringBuilder1.length() + str.length() <= 3798)
      {
        localStringBuilder1.append(str);
        localStringBuilder1.append("|");
      }
      else
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("|");
        localStringBuilder2.append(localStringBuilder1);
        paramContext.add(localStringBuilder2.toString());
        localStringBuilder1.setLength(0);
        localStringBuilder1.append(str);
        localStringBuilder1.append("|");
      }
      i += 1;
    }
    if (localStringBuilder1.length() > 0)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("|");
      ((StringBuilder)localObject).append(localStringBuilder1);
      paramContext.add(((StringBuilder)localObject).toString());
    }
    return paramContext;
  }
  
  private void a()
  {
    r.a(new String[0]);
    b();
    c();
    d();
    r.a(new String[0]);
  }
  
  private void b()
  {
    io.reactivex.e.a.a(new io.reactivex.c.g()
    {
      public void a(Throwable paramAnonymousThrowable)
        throws Exception
      {}
    });
    panda.keyboard.emoji.gdpr.b.a(this.c);
    k();
    f();
    try
    {
      if (panda.keyboard.emoji.gdpr.b.c()) {
        com.google.firebase.a.a(this.c);
      }
      r.a(new String[0]);
      com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
      {
        public void run()
        {
          MainApplicationInitializer.a(MainApplicationInitializer.a(MainApplicationInitializer.this), MainApplicationInitializer.b(MainApplicationInitializer.this));
        }
      });
      if (this.a == 7) {
        return;
      }
      r.a(new String[0]);
      com.ksmobile.common.data.provider.b.a(this.c);
      r.a(new String[0]);
      return;
    }
    catch (Error localError)
    {
      for (;;) {}
    }
  }
  
  private void c()
  {
    int i = this.a;
    if (i != 0)
    {
      switch (i)
      {
      default: 
        return;
      case 7: 
        com.cmcm.b.a.b.a(new q());
        com.cm.kinfoc.userbehavior.b.a().a(this.c);
        panda.keyboard.emoji.badge.aidl.b.a().a(this.c);
        return;
      case 6: 
        com.cmcm.b.a.b.a(new q());
        com.cm.kinfoc.userbehavior.b.a().a(this.c);
        panda.keyboard.emoji.badge.aidl.b.a().a(this.c);
        return;
      case 5: 
        com.cmcm.emoji.a.a.a(this.c);
        return;
      case 4: 
      case 8: 
        com.cmcm.b.a.b.a(new q());
        try
        {
          com.cmcm.keyboard.theme.view.video.b.a();
          com.ksmobile.common.data.a.a().a(this.c);
          h();
          com.cm.kinfoc.userbehavior.b.a().a(this.c);
          i();
          panda.keyboard.emoji.sync.aidl.d.a().a(this.c);
          panda.keyboard.emoji.account.aidl.a.a().b();
          panda.keyboard.emoji.performance.a.a().a(this.c);
          com.android.inputmethod.latin.b.a.a(this.c);
          panda.keyboard.emoji.badge.aidl.b.a().a(this.c);
          panda.keyboard.emoji.commercial.earncoin.aidl.b.a().a(this.c);
          com.android.inputmethod.keyboard.emoji.d.a();
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
        cmcm.commercial.a.a();
        return;
      }
      com.cm.kinfoc.userbehavior.b.a().a(this.c);
      cmcm.commercial.a.a();
      return;
    }
    e();
    r.a(new String[0]);
  }
  
  private static void c(Context paramContext, int paramInt)
  {
    if (!panda.keyboard.emoji.gdpr.b.c()) {
      return;
    }
    if ((paramInt == 0) || (paramInt == 4) || (paramInt == 8))
    {
      r.a(new String[0]);
      i.a(paramContext);
      r.a(new String[0]);
      d(paramContext, paramInt);
    }
  }
  
  private void d()
  {
    ac.a(new Runnable()
    {
      public void run()
      {
        r.a(new String[] { "init idle" });
        a.a(MainApplicationInitializer.a(MainApplicationInitializer.this), MainApplicationInitializer.c(MainApplicationInitializer.this));
        MainApplicationInitializer.d(MainApplicationInitializer.this);
        MainApplicationInitializer.e(MainApplicationInitializer.this);
        MainApplicationInitializer.b(MainApplicationInitializer.a(MainApplicationInitializer.this), MainApplicationInitializer.b(MainApplicationInitializer.this));
        r.a(new String[] { "init idle end" });
      }
    });
  }
  
  private static void d(Context paramContext, int paramInt)
  {
    com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
    {
      public void run()
      {
        com.cmcm.orion.adsdk.a.a(this.a, true);
        CMAdManager.applicationInit(this.a, "2419", false, "99999", panda.keyboard.emoji.gdpr.b.b(), true);
        try
        {
          CMAdManagerFactory.setDefaultConfig(k.a(k.a(this.a.getAssets().open("ad_default_config.json"))).a(Charset.defaultCharset()), false);
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        CMAdManager.addLoaderClass("fb", "com.cmcm.adsdk.adapter.FacebookNativeAdapter");
        CMAdManager.addLoaderClass("ab", "com.cmcm.adsdk.adapter.AdmobNativeAdapter");
        CMAdManager.addLoaderClass("abi", "com.cmcm.adsdk.adapter.AdmobInterstitialAdapter");
        CMAdManager.addLoaderClass("fbi", "com.cmcm.adsdk.adapter.FacebookInterstitialAdapter");
        CMAdManager.addLoaderClass("bm", "com.cmcm.adsdk.adapter.BatMobiNativeAdapter");
        CMAdManager.addLoaderClass("cm", "com.cmcm.adsdk.adapter.PicksNativeAdapter");
        CMAdManagerFactory.setReportProxy(new ReportProxy()
        {
          public void doNativeReport(Const.Event paramAnonymous2Event, Map<String, String> paramAnonymous2Map) {}
          
          public void doNetworkingReport(Map<String, String> paramAnonymous2Map) {}
        });
        boolean bool1 = com.ksmobile.common.annotation.a.O();
        boolean bool2 = com.ksmobile.common.annotation.a.P();
        boolean bool3 = com.ksmobile.common.annotation.a.Q();
        boolean bool4 = ADCloudSwitcher.d();
        Intent localIntent;
        if ((!com.ksmobile.common.annotation.a.n()) && ((!bool4) || ((!bool1) && (!bool2) && (!bool3)))) {
          TimingAdService.b(this.a);
        } else {
          localIntent = new Intent(this.a, PermanentService.class);
        }
        try
        {
          this.a.startService(localIntent);
          MntLib.init(this.a, "6LXMZENY1JNCJ5MO7SUEBTF6");
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    });
  }
  
  private void e()
  {
    r.a(new String[0]);
    p.a();
    com.cmcm.b.a.b.a(new q());
    h();
    try
    {
      com.ksmobile.common.data.a.a().a(this.c);
      i();
      com.cm.kinfoc.channel.a.a(this.c.getApplicationContext());
      r.a(new String[0]);
      com.cm.kinfoc.userbehavior.d.a(new com.cm.kinfoc.base.d()
      {
        public boolean a()
        {
          return panda.keyboard.emoji.gdpr.b.c();
        }
      });
      com.android.inputmethod.latin.b.a.a(this.c);
      j();
      panda.keyboard.emoji.performance.a.a().a(this.c);
      r.a(new String[0]);
      com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
      {
        public void run()
        {
          if (panda.keyboard.emoji.gdpr.b.c())
          {
            com.cmcm.emoji.a.a.a(MainApplicationInitializer.a(MainApplicationInitializer.this));
            com.cmcm.emoji.b.a.a().a(MainApplicationInitializer.a(MainApplicationInitializer.this));
            Cube.a(MainApplicationInitializer.a(MainApplicationInitializer.this));
          }
          DeleteTokenService.a(MainApplicationInitializer.a(MainApplicationInitializer.this).getApplicationContext());
          com.cmcm.keyboard.theme.fcm.b.a().a(MainApplicationInitializer.a(MainApplicationInitializer.this).getApplicationContext(), 86400000L);
          l.c(MainApplicationInitializer.a(MainApplicationInitializer.this));
        }
      });
      com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
      {
        public void run()
        {
          f.a(MainApplicationInitializer.a(MainApplicationInitializer.this));
          a.a(MainApplicationInitializer.a(MainApplicationInitializer.this));
        }
      }, 3000L);
    }
    catch (Error localError1)
    {
      try
      {
        panda.keyboard.emoji.personalize.e.a().a(this.c.getApplicationContext());
        SyncManager.a().a(this.c);
        SyncManager.a().a(1, panda.keyboard.emoji.sync.b.f[1], panda.keyboard.emoji.sync.b.e[1]);
        panda.keyboard.emoji.badge.aidl.b.a().a(this.c);
        panda.keyboard.emoji.commercial.earncoin.aidl.b.a().a(this.c);
        r.a(new String[0]);
        b.a(this.c.getApplicationContext());
        cmcm.commercial.billing.a.a.a(this.c.getApplicationContext());
        try
        {
          cmcm.commercial.a.a();
          return;
        }
        catch (Exception localException) {}
        localError1 = localError1;
      }
      catch (Error localError2)
      {
        for (;;) {}
      }
    }
  }
  
  private static void e(Context paramContext, int paramInt)
  {
    if (5 == paramInt) {
      return;
    }
    if (!panda.keyboard.emoji.gdpr.b.c()) {
      return;
    }
    try
    {
      io.fabric.sdk.android.c.a(new c.a(paramContext).a(new io.fabric.sdk.android.h[] { new a.a().a(new k.a().a(false).a()).a(), new com.crashlytics.android.ndk.c() }).a(io.fabric.sdk.android.services.concurrency.g.a(1)).a());
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  private void f()
  {
    com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
    {
      public void run()
      {
        try
        {
          HttpsURLConnection.setDefaultSSLSocketFactory(com.ksmobile.common.http.k.c.a(com.ksmobile.common.http.k.c.a()));
          HttpsURLConnection.setDefaultHostnameVerifier(com.ksmobile.common.http.k.c.b());
          return;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
    });
  }
  
  private void g() {}
  
  private void h()
  {
    r.a(new String[0]);
    com.android.inputmethod.latin.settings.a.c.b(this.c);
    r.a(new String[0]);
    com.android.inputmethod.latin.settings.a.c.a(this.c);
    r.a(new String[0]);
  }
  
  private void i()
  {
    com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(MainApplicationInitializer.a(MainApplicationInitializer.this), AliveReportService.class);
        com.ksmobile.keyboard.commonutils.b.a(MainApplicationInitializer.a(MainApplicationInitializer.this), localIntent);
      }
    }, 2000L);
  }
  
  private void j()
  {
    long l1 = this.c.getSharedPreferences("alive", 0).getLong("report_all_packages", 0L);
    long l2 = System.currentTimeMillis();
    if ((l1 == 0L) || (l2 / 86400000L - l1 / 86400000L >= 1L)) {
      ac.a(0, new a(this.c, null), 120000L);
    }
  }
  
  private void k()
  {
    if (this.a == 0) {}
    try
    {
      com.android.inputmethod.latin.e.c.a().b();
      return;
    }
    catch (Error localError) {}
  }
  
  private void l()
  {
    com.ksmobile.keyboard.commonutils.job.e.b().a(new Runnable()
    {
      public void run()
      {
        com.ksmobile.common.http.k.d.a(MainApplicationInitializer.a(MainApplicationInitializer.this));
      }
    }, 2000L);
  }
  
  @Keep
  public static void onGDPRUpdate(boolean paramBoolean)
  {
    Context localContext = com.ksmobile.keyboard.commonutils.h.a().b();
    if (paramBoolean)
    {
      int i = com.ksmobile.keyboard.commonutils.h.a().c();
      com.google.firebase.a.a(localContext);
      e(localContext, i);
      if (i != 0)
      {
        if ((i == 5) && ((localContext instanceof Application))) {
          com.cmcm.emoji.a.a.a((Application)localContext);
        }
      }
      else
      {
        if ((localContext instanceof Application))
        {
          Application localApplication = (Application)localContext;
          com.cmcm.emoji.a.a.a(localApplication);
          com.cmcm.emoji.b.a.a().a(localApplication);
          Cube.a(localApplication);
        }
        DeleteTokenService.a(localContext);
        com.cmcm.keyboard.theme.fcm.b.a().a(localContext, 86400000L);
      }
      c(localContext, i);
      return;
    }
    com.cmcm.orion.adsdk.a.a(localContext, false);
    CMAdManager.setPersonalizationEnabled(localContext, false);
  }
  
  private static final class a
    implements Runnable
  {
    private WeakReference<Context> a;
    
    private a(Context paramContext)
    {
      this.a = new WeakReference(paramContext);
    }
    
    public void run()
    {
      Context localContext = (Context)this.a.get();
      if (localContext != null) {}
      try
      {
        ArrayList localArrayList = MainApplicationInitializer.a(localContext);
        if (localArrayList == null) {
          return;
        }
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          com.cm.kinfoc.userbehavior.d.a(true, "cminput_active_apps", new String[] { "pkglist", (String)localArrayList.get(i) });
          i += 1;
        }
        localContext.getSharedPreferences("alive", 0).edit().putLong("report_all_packages", System.currentTimeMillis()).apply();
        return;
      }
      catch (Exception localException) {}
      return;
    }
  }
}
