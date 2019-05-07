package com.cleanmaster.boost.autostarts.core;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import com.cleanmaster.base.ipc.b;
import com.cleanmaster.base.util.system.h;
import com.cleanmaster.base.util.system.m;
import com.cleanmaster.boost.abnormal.abnormalnotify.AbnormalIgnoreManager;
import com.cleanmaster.boost.abnormal.abnormalnotify.AbnormalScenePhoneListener;
import com.cleanmaster.boost.abnormal.abnormalnotify.AbnormalScenePhoneListener.NotifyCacheType;
import com.cleanmaster.boost.abnormal.abnormalnotify.AbnormalScenePhoneListener.b;
import com.cleanmaster.boost.abnormal.abnormalnotify.AbnormalScenePhoneListener.c;
import com.cleanmaster.boost.abnormal.abnormalnotify.o;
import com.cleanmaster.boost.abnormal.abnormalnotify.r.1;
import com.cleanmaster.boost.abnormal.abnormalnotify.r.a;
import com.cleanmaster.boost.abnormal.abnormalnotify.r.b;
import com.cleanmaster.boost.abnormal.abnormalnotify.s;
import com.cleanmaster.boost.boostengine.autostart.AutostartDefine;
import com.cleanmaster.boost.d.an;
import com.cleanmaster.boost.d.ar;
import com.cleanmaster.boost.d.z;
import com.cleanmaster.boost.util.BoostBGThread;
import com.cleanmaster.synipc.IAutostartService.Stub;
import com.cleanmaster.synipc.IProcessCpuManager;
import com.cleanmaster.util.Process;
import com.cleanmaster.util.ao;
import com.cleanmaster.watcher.IAppLaunchNotify;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class AutostartService
  extends IAutostartService.Stub
  implements com.cleanmaster.synipc.a
{
  private boolean a = false;
  private Context b;
  private final ArrayList<FreqStartApp> c = new ArrayList();
  private String d;
  private Map<String, Long> e = new HashMap();
  private o f;
  private Object g = new Object();
  private BroadcastReceiver h = new BroadcastReceiver()
  {
    public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      if (paramAnonymousContext.equals(AutostartDefine.c[5])) {
        com.cleanmaster.base.c.a(AutostartService.c(AutostartService.this), 400);
      }
      int i = 0;
      paramAnonymousIntent = AutostartDefine.c;
      if (i < 20) {
        if (!AutostartDefine.c[i].equals(paramAnonymousContext)) {}
      }
      for (;;)
      {
        AutostartService.b(AutostartService.this, i);
        return;
        i += 1;
        break;
        i = -1;
      }
    }
  };
  private IAppLaunchNotify i = new IAppLaunchNotify()
  {
    public final void a(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2) {}
    
    public final void a(String paramAnonymousString1, long paramAnonymousLong, String paramAnonymousString2)
    {
      Log.d("AutostartService", "set fg pkgname:" + paramAnonymousString1);
      AutostartService.a(AutostartService.this, paramAnonymousString1);
      AutostartService.b(AutostartService.this, paramAnonymousString1);
    }
    
    public final IBinder asBinder()
    {
      return null;
    }
  };
  
  public AutostartService() {}
  
  private native ArrayList<FreqStartApp> getAllRecords();
  
  private int getFreqThreshold()
  {
    return com.cleanmaster.cloudconfig.a.b();
  }
  
  private long getProcCpuTime(int paramInt)
  {
    String[] arrayOfString = new String[6];
    long[] arrayOfLong = new long[6];
    if (!Process.readProcFile("/proc/" + paramInt + "/stat", ao.a, arrayOfString, arrayOfLong, null)) {
      return 0L;
    }
    long l = arrayOfLong[3];
    return arrayOfLong[4] + l;
  }
  
  private native void setFgPkgName(String paramString);
  
  private native void setInterval(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
  
  private native void setThreshold(int paramInt);
  
  private native void startMonitor();
  
  private native void stopMonitor();
  
  public final byte a(byte paramByte1, byte paramByte2, String paramString)
  {
    return AbnormalIgnoreManager.a(paramByte1, paramByte2, paramString);
  }
  
  public final void a()
  {
    Log.d("AutostartService", "initialize AutostartService");
  }
  
  public final void a(String arg1)
  {
    FreqStartApp localFreqStartApp = new FreqStartApp();
    localFreqStartApp.pkgName = ???;
    synchronized (this.c)
    {
      int j = this.c.indexOf(localFreqStartApp);
      if (j != -1) {
        this.c.remove(j);
      }
      boolean bool = this.c.isEmpty();
      if (bool)
      {
        com.cleanmaster.notification.e.a();
        com.cleanmaster.notification.e.a(514);
      }
      return;
    }
  }
  
  public final void b()
  {
    if (this.a) {}
    while (Build.VERSION.SDK_INT >= 23) {
      return;
    }
    if (com.cleanmaster.base.f.a.a())
    {
      Log.d("AutostartService", "start AutostartService");
      this.b = com.keniu.security.d.a().getApplicationContext();
      new Handler(Looper.getMainLooper());
      this.f = new o();
      Thread local1 = new Thread("AutostartService")
      {
        public final void run()
        {
          synchronized (AutostartService.a(AutostartService.this))
          {
            AutostartService.f();
            AutostartService.g();
            com.cleanmaster.service.watcher.e.a(AutostartService.c(AutostartService.this)).a(AutostartService.b(AutostartService.this));
            AutostartService.d(AutostartService.this);
            if (LibcoreWrapper.a.T())
            {
              AutostartService.e(AutostartService.this).a();
              AutostartService.e(AutostartService.this).b();
            }
            AutostartService.a(AutostartService.this, AutostartService.f(AutostartService.this));
            AutostartService.g(AutostartService.this);
            return;
          }
        }
      };
      local1.setName("AutostartService startMonitor");
      local1.start();
      this.a = true;
      return;
    }
    Log.e("AutostartService", "start AutostartService fail.......");
  }
  
  public final List<FreqStartApp> c()
  {
    ArrayList localArrayList2 = new ArrayList();
    synchronized (this.c)
    {
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        FreqStartApp localFreqStartApp = (FreqStartApp)localIterator.next();
        if ((localFreqStartApp.isUserApp) && (!localFreqStartApp.isWhiteApp)) {
          localArrayList2.add(localFreqStartApp);
        }
      }
    }
    return localList;
  }
  
  public void checkNotifyUserCallback()
  {
    Object localObject8 = com.cleanmaster.func.cache.e.a().b();
    Object localObject9 = LibcoreWrapper.a.R();
    Object localObject10 = LibcoreWrapper.a.p(LibcoreWrapper.a.d("abnormal_detection_notify_key", "abnormal_detection_notify_freqstart_white_list", null));
    Object localObject11;
    FreqStartApp localFreqStartApp;
    synchronized (this.c)
    {
      localObject11 = this.c.iterator();
      while (((Iterator)localObject11).hasNext())
      {
        localFreqStartApp = (FreqStartApp)((Iterator)localObject11).next();
        if (localFreqStartApp.newUpdate)
        {
          localFreqStartApp.newUpdate = false;
          localFreqStartApp.isUserApp = ((Map)localObject8).containsKey(localFreqStartApp.pkgName);
          localFreqStartApp.isWhiteApp = ((Set)localObject9).contains(localFreqStartApp.pkgName);
          if ((!localFreqStartApp.isWhiteApp) && (!TextUtils.isEmpty(localFreqStartApp.pkgName)))
          {
            localObject5 = (Long)this.e.get(localFreqStartApp.pkgName);
            Object localObject1 = localObject5;
            if (localObject5 == null)
            {
              localObject1 = Long.valueOf(LibcoreWrapper.a.o(localFreqStartApp.pkgName));
              this.e.put(localFreqStartApp.pkgName, localObject1);
            }
            localFreqStartApp.isWhiteApp = ((List)localObject10).contains(localObject1);
          }
        }
      }
    }
    if (!LibcoreWrapper.a.U())
    {
      new an().a(1).b(1).a();
      return;
    }
    Object localObject5 = new ArrayList();
    synchronized (this.c)
    {
      ??? = this.c.iterator();
      while (((Iterator)???).hasNext())
      {
        localObject8 = (FreqStartApp)((Iterator)???).next();
        if ((((FreqStartApp)localObject8).isUserApp) && (!((FreqStartApp)localObject8).isWhiteApp)) {
          ((List)localObject5).add(localObject8);
        }
      }
    }
    if (localList.isEmpty())
    {
      com.cleanmaster.notification.e.a();
      com.cleanmaster.notification.e.a(514);
      new an().a(1).b(2).a();
      return;
    }
    if (!LibcoreWrapper.a.V())
    {
      new an().a(1).b(3).a();
      return;
    }
    if (!com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).aF())
    {
      new an().a(1).b(4).a();
      return;
    }
    long l;
    if (!com.cm.root.d.a().g())
    {
      ??? = com.keniu.security.d.a();
      ??? = com.cleanmaster.configmanager.d.a((Context)???);
      l = System.currentTimeMillis() - ((com.cleanmaster.configmanager.d)???).bk();
      if (l < 0L)
      {
        com.cleanmaster.configmanager.d.a((Context)???).d(System.currentTimeMillis());
        j = 1;
      }
      while (j != 0)
      {
        new an().a(1).b(5).a();
        return;
        if (l <= LibcoreWrapper.a.Z()) {
          j = 1;
        } else {
          j = 0;
        }
      }
    }
    if (LibcoreWrapper.a.Y())
    {
      new an().a(1).b(6).a();
      return;
    }
    com.cleanmaster.boost.abnormal.abnormalnotify.e.a(localList);
    if (localList.isEmpty())
    {
      new an().a(1).b(7).a();
      return;
    }
    ??? = null;
    ??? = localList.iterator();
    int j = 0;
    label599:
    int k;
    if (((Iterator)???).hasNext())
    {
      localObject8 = (FreqStartApp)((Iterator)???).next();
      j += ((FreqStartApp)localObject8).newCount;
      if ((??? != null) || (((FreqStartApp)localObject8).envId < 0)) {
        break label2228;
      }
      k = ((FreqStartApp)localObject8).envId;
      localObject9 = AutostartDefine.c;
      if (k >= 20) {
        break label2228;
      }
      ??? = AutostartDefine.c[localObject8.envId];
    }
    label709:
    label890:
    label923:
    label928:
    label933:
    label1414:
    label1420:
    label1564:
    label1692:
    label1854:
    label1914:
    label2140:
    label2175:
    label2183:
    label2228:
    for (;;)
    {
      break label599;
      boolean bool;
      short s1;
      short s2;
      Object localObject6;
      if (j >= com.cleanmaster.cloudconfig.a.c())
      {
        bool = true;
        if (TextUtils.isEmpty((CharSequence)???)) {
          break label923;
        }
        if (!((String)???).equals(AutostartDefine.c[11])) {
          break label890;
        }
        s1 = 1;
        ??? = new r.b();
        ((r.b)???).b = localList;
        s2 = s1;
        if (s1 == 0)
        {
          s2 = s1;
          if (!TextUtils.isEmpty(this.d))
          {
            s2 = s1;
            if (!m.a(this.b, m.c(this.b, this.d)))
            {
              ((r.b)???).a = this.d;
              s2 = 3;
            }
          }
        }
        com.cleanmaster.a.a.a().a((byte)2);
        localObject9 = r.a.a;
        ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a = new z();
        ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.a(false);
        ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.b(0);
        ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.f(1);
        ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.h(s2);
        localObject6 = ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a;
        if (!bool) {
          break label928;
        }
      }
      for (j = 1;; j = 2)
      {
        ((z)localObject6).d(j);
        if (LibcoreWrapper.a.a(s2)) {
          break label933;
        }
        new an().a(1).b(8).a();
        return;
        bool = false;
        break;
        if ((((String)???).equals(AutostartDefine.c[9])) || (((String)???).equals(AutostartDefine.c[10])))
        {
          s1 = 2;
          break label709;
        }
        s1 = 0;
        break label709;
      }
      if (com.cm.root.d.a().g())
      {
        localObject6 = com.keniu.security.d.a();
        ??? = com.cleanmaster.configmanager.d.a((Context)localObject6);
        l = System.currentTimeMillis() - ((com.cleanmaster.configmanager.d)???).bk();
        if (l < 0L)
        {
          com.cleanmaster.configmanager.d.a((Context)localObject6).d(System.currentTimeMillis());
          j = 1;
        }
        while (j != 0)
        {
          new an().a(1).b(9).a();
          return;
          if (l <= LibcoreWrapper.a.a(bool)) {
            j = 1;
          } else {
            j = 0;
          }
        }
      }
      try
      {
        localObject6 = (r.b)???;
        if (localObject6 == null)
        {
          new an().a(1).b(10).a();
          return;
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localObject6 = null;
        }
        if ((localObject6 == null) || (((r.b)localObject6).b == null) || (((r.b)localObject6).b.isEmpty()))
        {
          new an().a(1).b(11).a();
          return;
        }
        localObject10 = com.keniu.security.d.a();
        if (localObject10 == null)
        {
          new an().a(1).b(12).a();
          return;
        }
        k = com.cleanmaster.configmanager.d.a((Context)localObject10).a("abnormal_freqstart_notification_index", 0);
        j = k;
        if (k < 0) {
          j = 0;
        }
        int m = (j + 1) % 3;
        k = ((r.b)localObject6).b.size();
        localObject11 = LibcoreWrapper.a.c(k, m);
        Object localObject4;
        if (localObject10 != null)
        {
          localObject4 = null;
          ??? = com.cleanmaster.configmanager.d.a((Context)localObject10).b((Context)localObject10).a();
          j = com.cleanmaster.boost.abnormal.abnormalnotify.r.a((Context)localObject10);
          if (j > 11)
          {
            localObject4 = LibcoreWrapper.a.d("process_settings", com.cleanmaster.cloudconfig.f.a("abnormal_notify_normal_freqstart_sub_title_23", (String)???), null);
            ??? = localObject4;
            if (TextUtils.isEmpty((CharSequence)localObject4))
            {
              ??? = localObject4;
              if (m >= 0)
              {
                ??? = localObject4;
                if (m <= 3) {
                  ??? = ((Context)localObject10).getString(new int[] { 2131296809, 2131296810, 2131296811 }[m]);
                }
              }
            }
            if (TextUtils.isEmpty((CharSequence)???)) {
              break label1414;
            }
          }
        }
        for (??? = Html.fromHtml((String)???);; ??? = null)
        {
          if ((!TextUtils.isEmpty((CharSequence)localObject11)) && (!TextUtils.isEmpty((CharSequence)???))) {
            break label1420;
          }
          new an().a(1).b(13).a();
          return;
          if (j > 7)
          {
            localObject4 = LibcoreWrapper.a.d("process_settings", com.cleanmaster.cloudconfig.f.a("abnormal_notify_normal_freqstart_sub_title_22", (String)???), null);
            break;
          }
          if (j <= 3) {
            break;
          }
          localObject4 = LibcoreWrapper.a.d("process_settings", com.cleanmaster.cloudconfig.f.a("abnormal_notify_normal_freqstart_sub_title_21", (String)???), null);
          break;
        }
        localFreqStartApp = (FreqStartApp)((r.b)localObject6).b.get(0);
        if (localFreqStartApp != null) {
          com.cleanmaster.func.cache.c.b().b(localFreqStartApp.pkgName, null);
        }
        if (3 == s2)
        {
          localObject8 = com.cleanmaster.func.cache.c.b().b(((r.b)localObject6).a, null);
          localObject4 = localObject8;
          if (TextUtils.isEmpty((CharSequence)localObject8))
          {
            localObject4 = localObject8;
            if (!TextUtils.isEmpty(((r.b)localObject6).a)) {
              localObject4 = ((r.b)localObject6).a;
            }
          }
        }
        for (localObject8 = ((r.b)localObject6).a;; localObject8 = null)
        {
          s1 = s2;
          if (s2 != 0)
          {
            if (LibcoreWrapper.a.b(s2)) {
              break label1564;
            }
            s1 = 0;
          }
          Intent localIntent;
          for (;;)
          {
            localIntent = LibcoreWrapper.a.a(bool, s1, localObject4);
            if (localIntent != null) {
              break;
            }
            new an().a(1).b(14).a();
            return;
            s1 = s2;
            if (3 == s2) {
              if (TextUtils.isEmpty((CharSequence)localObject4))
              {
                s1 = 0;
              }
              else
              {
                s1 = s2;
                if (localObject6 != null)
                {
                  s1 = s2;
                  if (localObject10 != null)
                  {
                    s1 = s2;
                    if (!TextUtils.isEmpty(((r.b)localObject6).a)) {
                      if (LibcoreWrapper.a.R().contains(((r.b)localObject6).a))
                      {
                        s1 = 0;
                      }
                      else
                      {
                        s1 = s2;
                        if (com.cleanmaster.base.c.b(m.e((Context)localObject10, ((r.b)localObject6).a))) {
                          s1 = 0;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (bool) {
            j = 20;
          }
          for (;;)
          {
            j += m + 1;
            int n = com.cleanmaster.boost.abnormal.abnormalnotify.r.a((Context)localObject10);
            if (n >= 4)
            {
              if (n > 11) {
                j = 3;
              }
            }
            else
            {
              localIntent.putExtra("key_textid", j);
              localObject4 = new s();
              ((s)localObject4).a = ((Context)localObject10);
              ((s)localObject4).h = localIntent;
              ((s)localObject4).i = ((CharSequence)localObject11);
              ((s)localObject4).j = ((CharSequence)???);
              ((s)localObject4).b = 1;
              ((s)localObject4).c = s1;
              ((s)localObject4).g = ((String)localObject8);
              ((s)localObject4).f = null;
              ((s)localObject4).d = k;
              ((s)localObject4).k = LibcoreWrapper.a.b((s)localObject4);
              if ((2 != s1) || (AbnormalScenePhoneListener.a())) {
                break label2140;
              }
              localObject6 = new AbnormalScenePhoneListener.b();
              ((AbnormalScenePhoneListener.b)localObject6).a = AbnormalScenePhoneListener.NotifyCacheType.FREQSTART;
              localObject8 = new AbnormalScenePhoneListener.c();
              ((AbnormalScenePhoneListener.c)localObject8).a = localIntent;
              ((AbnormalScenePhoneListener.c)localObject8).b = ((CharSequence)localObject11);
              ((AbnormalScenePhoneListener.c)localObject8).c = ((CharSequence)???);
              ((AbnormalScenePhoneListener.b)localObject6).b = localObject8;
              AbnormalScenePhoneListener.a((AbnormalScenePhoneListener.b)localObject6);
              bool = true;
              BoostBGThread.a(new r.1());
              if (!bool) {
                break;
              }
              com.cleanmaster.configmanager.d.a((Context)localObject10).b("abnormal_freqstart_notification_index", m);
              if (localObject10 != null)
              {
                localObject6 = com.cleanmaster.configmanager.d.a((Context)localObject10);
                if (!com.cm.root.d.a().g()) {
                  break label2183;
                }
                if (!((com.cleanmaster.configmanager.d)localObject6).bl()) {
                  break label2175;
                }
                LibcoreWrapper.a.W();
                ((com.cleanmaster.configmanager.d)localObject6).d(System.currentTimeMillis());
              }
              if (localFreqStartApp != null)
              {
                ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.a(localFreqStartApp.pkgName);
                ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.c(localFreqStartApp.envId);
              }
            }
            try
            {
              localObject6 = ((Context)localObject10).getPackageManager().getPackageInfo(localFreqStartApp.pkgName, 0);
              ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.b(String.valueOf(((PackageInfo)localObject6).versionCode));
              ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.c(((PackageInfo)localObject6).versionName);
              ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.g(j);
              localObject6 = (IProcessCpuManager)com.cleanmaster.base.ipc.c.a().a(b.a);
              try
              {
                j = Math.round(((IProcessCpuManager)localObject6).d() * 100.0F);
                ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.a(j);
                ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.e(k);
                if (k != 1) {
                  ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.a("");
                }
                ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.a();
                if (((s)localObject4).k)
                {
                  localObject4 = ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.b();
                  if (localObject4 != null) {
                    ((z)localObject4).report();
                  }
                }
                ((com.cleanmaster.boost.abnormal.abnormalnotify.r)localObject9).a.report();
                return;
                j = 10;
                continue;
                if (n > 7)
                {
                  j = 2;
                  break label1692;
                }
                j = 1;
                break label1692;
                if (com.cleanmaster.boost.lowbatterymode.d.k())
                {
                  com.cleanmaster.boost.lowbatterymode.d.a(com.keniu.security.d.a()).a(5, localObject6, null);
                  bool = true;
                  break label1854;
                }
                bool = LibcoreWrapper.a.a((s)localObject4);
                break label1854;
                ((com.cleanmaster.configmanager.d)localObject6).bm();
                break label1914;
                if (((com.cleanmaster.configmanager.d)localObject6).bn()) {
                  LibcoreWrapper.a.X();
                }
                ((com.cleanmaster.configmanager.d)localObject6).bo();
              }
              catch (RemoteException localRemoteException)
              {
                for (;;)
                {
                  localRemoteException.printStackTrace();
                  j = -1;
                }
              }
            }
            catch (Exception localException2)
            {
              for (;;) {}
            }
          }
          localObject4 = null;
        }
      }
    }
  }
  
  public final List<FreqStartApp> d()
  {
    if (com.cleanmaster.base.f.a.a()) {
      return getAllRecords();
    }
    return null;
  }
  
  public final void e()
  {
    long l1 = System.currentTimeMillis();
    for (;;)
    {
      int j;
      synchronized (this.c)
      {
        j = this.c.size() - 1;
        if (j >= 0)
        {
          FreqStartApp localFreqStartApp = (FreqStartApp)this.c.get(j);
          if (localFreqStartApp == null)
          {
            this.c.remove(j);
          }
          else
          {
            long l2 = l1 - localFreqStartApp.firstTime;
            if ((l2 < 0L) || (l2 > 86400000L)) {
              this.c.remove(j);
            }
          }
        }
      }
      boolean bool = this.c.isEmpty();
      if (bool)
      {
        com.cleanmaster.notification.e.a();
        com.cleanmaster.notification.e.a(514);
      }
      return;
      j -= 1;
    }
  }
  
  public void freqStartAppCallback(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    Log.d("AutostartService", "app:" + paramString + " count:" + paramInt1 + " action:" + paramInt2 + " cpu:" + paramInt3);
    ??? = new StringTokenizer(paramString, ":");
    paramString = new FreqStartApp();
    paramString.pkgName = ((StringTokenizer)???).nextToken();
    if (TextUtils.isEmpty(paramString.pkgName)) {}
    for (;;)
    {
      return;
      Object localObject2;
      int j;
      if (LibcoreWrapper.a.T())
      {
        localObject2 = FreqStartDatabase.a();
        ??? = paramString.pkgName;
        localObject2 = ((FreqStartDatabase)localObject2).c();
        if (localObject2 != null)
        {
          Object localObject3 = Calendar.getInstance();
          j = ((Calendar)localObject3).get(1) * 10000 + (((Calendar)localObject3).get(2) + 1) * 100 + ((Calendar)localObject3).get(5);
          localObject3 = Integer.toString(j);
          localObject3 = ((com.cleanmaster.dao.r)localObject2).a("freqstart_history", new String[] { "id", "package", "fs_count" }, "package=? and fs_date=?", new String[] { ???, localObject3 }, null);
          if ((localObject3 == null) || (((Cursor)localObject3).getCount() <= 0) || (!((Cursor)localObject3).moveToFirst())) {
            break label466;
          }
          j = ((Cursor)localObject3).getInt(0);
          int k = ((Cursor)localObject3).getInt(2);
          ??? = new ContentValues();
          ((ContentValues)???).put("fs_count", Integer.valueOf(k + paramInt1));
          ((com.cleanmaster.dao.r)localObject2).a("freqstart_history", (ContentValues)???, "id=?", new String[] { Integer.toString(j) });
          label311:
          if (localObject3 != null) {
            ((Cursor)localObject3).close();
          }
        }
      }
      if (paramInt3 < com.cleanmaster.cloudconfig.a.a()) {
        continue;
      }
      long l2;
      long l1;
      synchronized (this.c)
      {
        j = this.c.indexOf(paramString);
        if (j != -1)
        {
          paramString = (FreqStartApp)this.c.get(j);
          paramString.totalCount += paramInt1;
          paramString.newUpdate = true;
          paramString.envId = paramInt2;
          paramString.newCount = paramInt1;
          paramString.cpuUsage = paramInt3;
          paramString.lastTime = System.currentTimeMillis();
          if (paramString.totalCount <= 5) {
            continue;
          }
          ??? = com.cleanmaster.configmanager.d.a(this.b);
          l2 = ((com.cleanmaster.configmanager.d)???).a("abnormal_freqstart_report_time", 0L);
          l1 = System.currentTimeMillis();
          l2 = l1 - l2;
          if (l2 < 0L)
          {
            ((com.cleanmaster.configmanager.d)???).f(l1);
            return;
            label466:
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("package", (String)???);
            localContentValues.put("fs_date", Integer.valueOf(j));
            localContentValues.put("fs_count", Integer.valueOf(paramInt1));
            ((com.cleanmaster.dao.r)localObject2).a("freqstart_history", localContentValues);
            break label311;
          }
        }
        else
        {
          paramString.totalCount = paramInt1;
          paramString.firstTime = System.currentTimeMillis();
          this.c.add(paramString);
        }
      }
      paramInt1 = ((com.cleanmaster.configmanager.d)???).a("abnormal_freqstart_report_count", 0);
      if (l2 > 86400000L)
      {
        paramInt1 = 1;
        label574:
        ((com.cleanmaster.configmanager.d)???).f(l1);
        ((com.cleanmaster.configmanager.d)???).b("abnormal_freqstart_report_count", paramInt1);
        ??? = new ar();
        ((ar)???).set("pn", paramString.pkgName);
        localObject2 = com.cleanmaster.func.cache.e.a().b();
        if (localObject2 != null)
        {
          if (((Map)localObject2).containsKey(paramString.pkgName)) {
            break label900;
          }
          paramInt1 = 1;
          label640:
          if (paramInt1 == 0) {
            break label905;
          }
          paramInt1 = 1;
          ((ar)???).set("apptype", paramInt1);
        }
        ((ar)???).set("env", paramString.envId);
        ((ar)???).set("restartnum", paramString.totalCount);
        l2 = paramString.lastTime - paramString.firstTime;
        l1 = l2;
        if (l2 < 0L) {
          l1 = 0L;
        }
        ((ar)???).set("lasttime", (int)(l1 / 1000L / 60L));
        ((ar)???).set("cputime", paramInt3);
        paramString = com.keniu.security.d.a();
        localObject2 = (IProcessCpuManager)com.cleanmaster.base.ipc.c.a().a(b.a);
      }
      try
      {
        paramInt1 = Math.round(((IProcessCpuManager)localObject2).d() * 100.0F);
        ((ar)???).set("syscpu", paramInt1);
        localObject2 = paramString.getPackageManager().getInstalledPackages(0);
        if (localObject2 != null) {
          ((ar)???).set("appnum", ((List)localObject2).size());
        }
        localObject2 = new com.cleanmaster.activitymanagerhelper.a();
        ((com.cleanmaster.activitymanagerhelper.a)localObject2).a = com.cmcm.rtstub.a.a();
        ((ar)???).set("activenum", ((com.cleanmaster.activitymanagerhelper.a)localObject2).a(paramString).size());
        ((ar)???).set("idleram", (int)(com.cleanmaster.boost.process.util.f.a() / 1024L / 1024L));
        ((ar)???).set("ram", (int)(com.cleanmaster.boost.process.util.f.b() / 1024L / 1024L));
        ((ar)???).report();
        return;
        if (paramInt1 >= 6) {
          continue;
        }
        paramInt1 += 1;
        break label574;
        label900:
        paramInt1 = 0;
        break label640;
        label905:
        paramInt1 = 2;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          paramInt1 = 0;
        }
      }
    }
  }
}
