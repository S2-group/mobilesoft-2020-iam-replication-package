package com.sec.spp.push.update;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.sec.spp.push.PushClientApplication;
import com.sec.spp.push.t;
import com.sec.spp.push.util.i;
import com.sec.spp.push.util.p;
import com.sec.spp.push.util.q;
import java.util.List;

public class a
{
  private static final String b = a.class.getSimpleName();
  private static a c;
  private static final Object d = new Object();
  String a = "";
  private final String e = PushClientApplication.b().getString(2131099695);
  private final String f = PushClientApplication.b().getString(2131099695);
  private final String g = PushClientApplication.b().getString(2131099679);
  private final int h = 1;
  private final int i = 30;
  private final int j = 11;
  private final int k = 16;
  private final String l = "";
  private final String m = "";
  private BroadcastReceiver n = null;
  private int o = t.a().y();
  private int p = t.a().z();
  
  private a() {}
  
  public static a a()
  {
    if (c == null) {}
    synchronized (d)
    {
      if (c == null) {
        c = new a();
      }
      return c;
    }
  }
  
  private boolean a(int paramInt)
  {
    if (i.m() < paramInt)
    {
      q.b(b, "fv is higer version. fv : " + paramInt + ", Now Version : " + i.m());
      return true;
    }
    q.b(b, "now version is higer. fv : " + paramInt + ", Now Version : " + i.m());
    return false;
  }
  
  public static Intent d()
  {
    if (i()) {
      return new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + "com.sec.spp.push"));
    }
    String str = t.a().E();
    if (!TextUtils.isEmpty(str)) {
      return new Intent("android.intent.action.VIEW").setData(Uri.parse(str));
    }
    return null;
  }
  
  private void e()
  {
    if ((this.o == 0) || (this.p == 0))
    {
      q.e(b, "FV and ED is invalid. fv : " + this.o + ", ed : " + this.p);
      return;
    }
    q.c(b, "Do Force Update. fv : " + this.o + ", ed : " + this.p);
    g();
    f();
    Context localContext = PushClientApplication.b();
    Object localObject = (AlarmManager)localContext.getSystemService("alarm");
    localObject = PendingIntent.getBroadcast(localContext, 0, new Intent("com.sec.spp.push.receiver.ForceUpdateAlarmReceiver"), 0);
    com.sec.spp.push.util.b.a(localContext, 0, System.currentTimeMillis() + 86400000L, (PendingIntent)localObject);
    q.e(b, "Alarm Setting. After one day, it will alram.");
  }
  
  private void f()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.SCREEN_ON");
    this.n = new b(this);
    PushClientApplication.b().registerReceiver(this.n, localIntentFilter);
  }
  
  private void g()
  {
    t localT = t.a();
    localT.h(true);
    localT.j(this.o);
    localT.k(this.p);
    localT.d(System.currentTimeMillis() + this.p * 86400000L);
    q.c(b, "Save Preferences. fv : " + this.o + ", ed : " + this.p + ", expired date : " + localT.A());
    q.e(b, "system : " + System.currentTimeMillis());
    q.e(b, "ed : " + this.p);
    q.e(b, "oneday : 86400000");
    q.e(b, "sum : " + System.currentTimeMillis() + this.p * 86400000L);
  }
  
  private void h()
  {
    q.b(b, "Display Popup Message.");
    Context localContext = PushClientApplication.b();
    localContext.unregisterReceiver(this.n);
    Intent localIntent = new Intent(localContext, PopupMessage.class);
    localIntent.setFlags(268435456);
    localContext.startActivity(localIntent);
  }
  
  private static boolean i()
  {
    boolean bool2 = false;
    List localList = PushClientApplication.b().getPackageManager().getInstalledPackages(0);
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < localList.size())
      {
        if ("com.android.vending".equals(((PackageInfo)localList.get(i1)).packageName))
        {
          q.b(b, "There is Google play store.");
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  private boolean j()
  {
    if (this.p > 30)
    {
      q.b(b, "ED value is too big. ED : " + this.p);
      return false;
    }
    if (this.p < 1)
    {
      q.b(b, "ED value is minus value. ED : " + this.p);
      return false;
    }
    return true;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    this.o = paramInt1;
    this.p = paramInt2;
  }
  
  public void a(p paramP)
  {
    int i1 = paramP.p();
    int i2 = paramP.q();
    q.b(b, "Check Prov. fv : " + i1 + ", ed : " + i2);
    b(i1, i2);
  }
  
  public void b()
  {
    t localT = t.a();
    if (!a(localT.y()))
    {
      localT.h(false);
      localT.d(0L);
      q.b(b, "Already Updated.");
      return;
    }
    q.b(b, "Display Notification. Expired Time(millis) : " + localT.A());
    q.b(b, "Display Notification. Display Time : " + System.currentTimeMillis());
    if (localT.A() < System.currentTimeMillis())
    {
      localT.h(false);
      localT.d(0L);
      q.b(b, "Force Update Expired.");
      return;
    }
    c();
  }
  
  public void b(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 0) && (paramInt1 != t.a().y()))
    {
      a localA = a();
      localA.a(paramInt1, paramInt2);
      if ((localA.j()) && (localA.a(paramInt1)))
      {
        q.e(b, "Force Update start.");
        localA.e();
      }
    }
  }
  
  public void c()
  {
    q.b(b, "Display Force Update Notification");
    Object localObject1 = PushClientApplication.b();
    Object localObject2 = d();
    if (localObject2 == null) {
      q.e(b, "Force Update Intent : empty");
    }
    NotificationManager localNotificationManager;
    do
    {
      return;
      localObject2 = PendingIntent.getActivity((Context)localObject1, 0, (Intent)localObject2, 134217728);
      localNotificationManager = (NotificationManager)((Context)localObject1).getSystemService("notification");
    } while (Build.VERSION.SDK_INT < 11);
    localObject1 = new Notification.Builder(((Context)localObject1).getApplicationContext()).setOngoing(false).setTicker(this.e).setContentTitle(this.f).setContentText(this.g).setSmallIcon(2130837512);
    if (Build.VERSION.SDK_INT < 16) {}
    for (localObject1 = ((Notification.Builder)localObject1).getNotification();; localObject1 = ((Notification.Builder)localObject1).build())
    {
      ((Notification)localObject1).contentIntent = ((PendingIntent)localObject2);
      ((Notification)localObject1).flags = 16;
      localNotificationManager.notify(123456, (Notification)localObject1);
      return;
      if (Build.VERSION.SDK_INT >= 19) {
        ((Notification.Builder)localObject1).setSmallIcon(2130837519);
      }
    }
  }
}
