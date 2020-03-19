package com.ksmobile.launcher.f;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.cleanmaster.applocklib.base.AppLockLib;
import com.ksmobile.launcher.CellLayout;
import com.ksmobile.launcher.Hotseat;
import com.ksmobile.launcher.Launcher;
import com.ksmobile.launcher.LauncherApplication;
import com.ksmobile.launcher.Workspace;
import com.ksmobile.launcher.cj;
import com.ksmobile.launcher.customitem.AppLockShortcutInfo;
import com.ksmobile.launcher.ds;
import com.ksmobile.launcher.dx;
import com.ksmobile.launcher.gd;
import com.ksmobile.launcher.userbehavior.h;
import com.ksmobile.support.app.r;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static a d = null;
  private AppLockShortcutInfo a;
  private boolean b;
  private boolean c;
  
  private a() {}
  
  public static a a()
  {
    try
    {
      if (d == null) {
        d = new a();
      }
      a localA = d;
      return localA;
    }
    finally {}
  }
  
  private void b(int paramInt)
  {
    Object localObject = LauncherApplication.e();
    String str = String.format(((Application)localObject).getResources().getString(2131296331), new Object[] { ((Application)localObject).getResources().getString(paramInt), ((Application)localObject).getResources().getString(2131297353) });
    NotificationManager localNotificationManager = (NotificationManager)((Application)localObject).getSystemService("notification");
    RemoteViews localRemoteViews = new RemoteViews(((Application)localObject).getPackageName(), 2130968607);
    localRemoteViews.setTextViewText(2131689783, str);
    localRemoteViews.setImageViewResource(2131689782, 2130837552);
    localObject = new r((Context)localObject);
    ((r)localObject).setContent(localRemoteViews);
    ((r)localObject).setSmallIcon(2130837552);
    ((r)localObject).setAutoCancel(true);
    localNotificationManager.notify(2131296340, ((r)localObject).build());
  }
  
  private void c(int paramInt)
  {
    h.b(false, "launcher_applock_uninstall", new String[] { "data", String.valueOf(paramInt) });
  }
  
  private void i()
  {
    int i;
    if (this.b) {
      i = 1;
    }
    for (;;)
    {
      h.b(false, "launcher_applock_install", new String[] { "data", String.valueOf(i) });
      return;
      if (this.c) {
        i = 2;
      } else {
        i = 0;
      }
    }
  }
  
  public void a(int paramInt)
  {
    if (this.a == null) {
      return;
    }
    Object localObject;
    if (this.a.m == -100L)
    {
      localObject = ds.a().h().ae();
      if (localObject != null)
      {
        localObject = ((Workspace)localObject).d(this.a.n);
        ((CellLayout)localObject).removeView(((CellLayout)localObject).B().a(this.a.o, this.a.p));
        break label174;
      }
    }
    for (;;)
    {
      com.ksmobile.launcher.util.i.Q().l(null);
      dx.b(LauncherApplication.e(), this.a);
      this.a = null;
      b(paramInt);
      return;
      if (this.a.m == -101L)
      {
        localObject = ds.a().h();
        if (localObject != null)
        {
          localObject = ((Launcher)localObject).ac().a();
          ((CellLayout)localObject).removeView(((CellLayout)localObject).B().a(this.a.o, this.a.p));
        }
      }
      else
      {
        localObject = com.ksmobile.launcher.folder.i.a().g().iterator();
        label174:
        if (((Iterator)localObject).hasNext())
        {
          cj localCj = (cj)((Iterator)localObject).next();
          if (localCj.i != this.a.m) {
            break;
          }
          localCj.b(this.a);
        }
      }
    }
  }
  
  public void a(AppLockShortcutInfo paramAppLockShortcutInfo)
  {
    this.a = paramAppLockShortcutInfo;
  }
  
  public boolean b()
  {
    try
    {
      Iterator localIterator = LauncherApplication.e().getPackageManager().getInstalledPackages(8192).iterator();
      while (localIterator.hasNext())
      {
        String str = ((PackageInfo)localIterator.next()).packageName;
        if (str.equals("com.cleanmaster.security")) {
          this.b = true;
        } else if (str.equals("com.cleanmaster.mguard")) {
          this.c = true;
        }
      }
      boolean bool;
      if (!this.b) {
        bool = this.c;
      }
      return bool;
    }
    catch (NullPointerException localNullPointerException) {}
    return true;
  }
  
  public boolean c()
  {
    String str = AppLockLib.getAvailableAppLockHost(LauncherApplication.e());
    if (!TextUtils.isEmpty(str))
    {
      com.ksmobile.launcher.util.i.Q().l(str);
      return true;
    }
    return false;
  }
  
  public void d()
  {
    if (com.ksmobile.launcher.locker.b.a.c(ds.a().c())) {}
    String str;
    do
    {
      return;
      str = com.ksmobile.launcher.util.i.Q().aT();
    } while ((TextUtils.isEmpty(str)) || (!str.equals("com.cleanmaster.mguard")));
    a(2131296386);
    c(2);
  }
  
  public void e()
  {
    if (com.ksmobile.launcher.locker.b.a.c(ds.a().c())) {}
    String str;
    do
    {
      return;
      str = com.ksmobile.launcher.util.i.Q().aT();
    } while ((TextUtils.isEmpty(str)) || (!str.equals("com.cleanmaster.security")));
    a(2131296422);
    c(1);
  }
  
  public boolean f()
  {
    if (com.ksmobile.launcher.locker.b.a.c(ds.a().c())) {}
    String str;
    do
    {
      return true;
      str = com.ksmobile.launcher.util.i.Q().aT();
      b();
    } while ((!TextUtils.isEmpty(str)) && (((str.equals("com.cleanmaster.security")) && (this.b)) || ((str.equals("com.cleanmaster.mguard")) && (this.c))));
    return false;
  }
  
  public boolean g()
  {
    if (com.ksmobile.launcher.locker.b.a.c(ds.a().c())) {
      return true;
    }
    if ((a().b()) && (a().c()))
    {
      i();
      return true;
    }
    return false;
  }
  
  public void h()
  {
    if (this.a != null) {
      this.a.o_();
    }
  }
}
