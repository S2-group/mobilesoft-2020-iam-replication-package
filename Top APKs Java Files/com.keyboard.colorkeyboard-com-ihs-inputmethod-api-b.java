package com.ihs.inputmethod.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import b.a.a.a.i;
import com.ihs.a.h.d;
import com.ihs.inputmethod.api.h.h;
import com.ihs.inputmethod.api.i.o;
import java.util.Iterator;
import java.util.List;
import net.appcloudbox.api.Data;

public class b
  extends com.ihs.inputmethod.b.a
{
  private com.ihs.a.g.c c = new com.ihs.a.g.c()
  {
    public void a(String paramAnonymousString, com.ihs.a.h.b paramAnonymousB)
    {
      if ("hs.app.session.SESSION_START".equals(paramAnonymousString))
      {
        com.ihs.app.alerts.a.a();
        b.this.f();
      }
      if ("hs.app.session.SESSION_END".equals(paramAnonymousString)) {
        com.ihs.a.e.a.c();
      }
    }
  };
  
  public b() {}
  
  private void g()
  {
    if (com.ihs.app.b.b.b() == 1) {
      o.a(new Runnable()
      {
        public void run()
        {
          List localList1 = com.ihs.app.b.a.a().getPackageManager().getInstalledPackages(0);
          List localList2 = com.ihs.a.b.b.d(new String[] { "Application", "PluginTheme", "PluginThemePkNamePrefix" });
          int i = 0;
          while (i < localList1.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)localList1.get(i);
            Iterator localIterator = localList2.iterator();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              if (localPackageInfo.packageName.startsWith(str))
              {
                com.ihs.inputmethod.api.a.a.a().b("app_first_open_apk_exist", "true");
                com.ihs.app.a.c.a("app_first_open_apk_exist", new String[] { "true" });
                return;
              }
            }
            i += 1;
          }
          com.ihs.inputmethod.api.a.a.a().b("app_first_open_apk_exist", "false");
          com.ihs.app.a.c.a("app_first_open_apk_exist", new String[] { "false" });
        }
      });
    }
  }
  
  protected void f()
  {
    com.ihs.a.e.a.b();
    com.ihs.keyboardutils.nativeads.b.a();
    g();
    com.ihs.inputmethod.api.a.a.a().a("app_opened");
    com.ihs.inputmethod.api.a.a.a().a("app_opened_customize_themes_num", h.c().size());
  }
  
  public void onCreate()
  {
    Log.e("time log", "time log application oncreated started");
    super.onCreate();
    o.a(new Runnable()
    {
      public void run()
      {
        h.q();
      }
    });
    h.p();
    com.ihs.inputmethod.uimodules.ui.theme.iap.b.a().b();
    if (!d.a()) {
      b.a.a.a.c.a(this, new i[] { new com.a.a.a() });
    }
    com.ihs.a.g.a.a("hs.app.session.SESSION_START", this.c);
    Log.e("time log", "time log application oncreated finished");
    if (com.ihs.app.e.c.b()) {
      com.ihs.inputmethod.uimodules.ui.theme.a.a.a().a(h.b().b);
    }
    Data.init(getApplicationContext(), com.ihs.a.b.b.a("", new String[] { "Application", "Analytics", "ProductID" }), com.ihs.a.b.b.a("", new String[] { "Application", "Analytics", "ChannelID" }));
    Data.setSdkClosed(com.ihs.app.b.a.a(), com.ihs.a.b.b.a(true, new String[] { "Application", "Analytics", "Closed" }));
    com.ihs.a.g.a.a("hs.commons.config.CONFIG_CHANGED", new com.ihs.a.g.c()
    {
      public void a(String paramAnonymousString, com.ihs.a.h.b paramAnonymousB)
      {
        Data.setSdkClosed(com.ihs.app.b.a.a(), com.ihs.a.b.b.a(true, new String[] { "Application", "Analytics", "Closed" }));
      }
    });
  }
  
  public void onTerminate()
  {
    com.ihs.a.g.a.a(this.c);
    super.onTerminate();
  }
}
