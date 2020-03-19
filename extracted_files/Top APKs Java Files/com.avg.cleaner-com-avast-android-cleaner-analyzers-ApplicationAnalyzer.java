package com.avast.android.cleaner.analyzers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.avast.android.cleaner.analyzers.battery.BatteryUsageAnalyzer;
import com.avast.android.cleaner.analyzers.broadcastreceivers.AlarmReceiver;
import com.avast.android.cleaner.analyzers.daodata.AppDao;
import com.avast.android.cleaner.analyzers.daodata.AppsDBManager;
import com.avast.android.cleaner.analyzers.daodata.AppsDaoHelper;
import com.avast.android.cleaner.analyzers.daodata.DaoSession;
import com.avast.android.cleaner.analyzers.data.AnalyzerPrefs;
import com.avast.android.cleaner.analyzers.data.DataUsageAnalyzer;
import com.avast.android.cleaner.core.ProjectApp;
import com.avast.android.cleanercore.device.DevicePackageManager;
import eu.inmite.android.fw.DebugLog;
import eu.inmite.android.fw.SL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApplicationAnalyzer
{
  private static ApplicationAnalyzer a;
  private final Map<Analyzer.AnalysisType, BaseAnalyzer> b = new HashMap();
  private final ArrayList<AnalyzerListener> c;
  private final Object d = new Object();
  private volatile HashMap<String, com.avast.android.cleaner.analyzers.daodata.App> e;
  private boolean f = false;
  private HashMap<String, com.avast.android.cleaner.analyzers.daodata.App> g;
  private boolean h;
  private AnalyzerPrefs i;
  private DevicePackageManager j;
  
  private ApplicationAnalyzer()
  {
    Context localContext = ProjectApp.B().getApplicationContext();
    this.h = false;
    this.f = false;
    this.c = new ArrayList();
    this.g = new HashMap();
    this.e = new HashMap();
    this.i = new AnalyzerPrefs(localContext);
    this.b.put(Analyzer.AnalysisType.a, new DataUsageAnalyzer(localContext));
    this.b.put(Analyzer.AnalysisType.b, new BatteryUsageAnalyzer(localContext));
    this.j = ((DevicePackageManager)SL.a(DevicePackageManager.class));
  }
  
  public static ApplicationAnalyzer a()
  {
    try
    {
      if (a == null) {
        a = new ApplicationAnalyzer();
      }
      ApplicationAnalyzer localApplicationAnalyzer = a;
      return localApplicationAnalyzer;
    }
    finally {}
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (!this.j.a(paramApplicationInfo)) || (this.j.f(paramApplicationInfo.packageName));
  }
  
  private boolean a(Object paramObject)
  {
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((localObject != null) && (paramObject != null) && (localObject.getClass().equals(paramObject.getClass()))) {
        return true;
      }
    }
    return false;
  }
  
  public static void b()
  {
    DebugLog.c("ScannerLifecycleCallbackImpl.startAnalyzers()");
    Context localContext = ProjectApp.B().getApplicationContext();
    AnalyzerIntentService.b(localContext);
    a().c();
    AnalyzerIntentService.a(localContext);
  }
  
  private void i()
  {
    synchronized (this.c)
    {
      Iterator localIterator = this.c.iterator();
      if (localIterator.hasNext())
      {
        final AnalyzerListener localAnalyzerListener = (AnalyzerListener)localIterator.next();
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            if (localAnalyzerListener != null) {
              localAnalyzerListener.a(new ArrayList(ApplicationAnalyzer.a(ApplicationAnalyzer.this).values()));
            }
          }
        });
      }
    }
  }
  
  private void j()
  {
    synchronized (this.d)
    {
      this.e = n();
      this.h = true;
      o();
      return;
    }
  }
  
  private void k()
  {
    for (;;)
    {
      try
      {
        Context localContext = ProjectApp.B().getApplicationContext();
        Iterator localIterator = localContext.getPackageManager().getInstalledApplications(0).iterator();
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          try
          {
            if (!a(localApplicationInfo)) {
              continue;
            }
            com.avast.android.cleaner.analyzers.daodata.App localApp = new com.avast.android.cleaner.analyzers.daodata.App();
            localApp.b(localApplicationInfo.packageName);
            localApp.a(String.valueOf(localContext.getPackageManager().getApplicationLabel(localApplicationInfo)));
            localApp.a(Integer.valueOf(localApplicationInfo.uid));
            Intent localIntent = localContext.getPackageManager().getLaunchIntentForPackage(localApp.c());
            if (localIntent != null) {
              break label192;
            }
            localIntent = null;
            localApp.c = localIntent;
            this.g.put(localApp.c(), localApp);
          }
          catch (Exception localException1)
          {
            DebugLog.d("ApplicationAnalyzer", "Can't process app: " + localApplicationInfo.packageName + ". Continue to next one.");
          }
          continue;
        }
        return;
      }
      catch (Exception localException2)
      {
        DebugLog.e("ApplicationAnalyzer", "Probably Package manager has died, if so nothing to be done...", localException2);
      }
      label192:
      String str = localException2.getComponent().getClassName();
    }
  }
  
  private void l()
  {
    Iterator localIterator = this.e.values().iterator();
    while (localIterator.hasNext())
    {
      com.avast.android.cleaner.analyzers.daodata.App localApp = (com.avast.android.cleaner.analyzers.daodata.App)localIterator.next();
      if (this.g.get(localApp.c()) != null)
      {
        ((com.avast.android.cleaner.analyzers.daodata.App)this.g.get(localApp.c())).a = ((com.avast.android.cleaner.analyzers.daodata.App)this.e.get(localApp.c())).a;
        ((com.avast.android.cleaner.analyzers.daodata.App)this.g.get(localApp.c())).d = ((com.avast.android.cleaner.analyzers.daodata.App)this.e.get(localApp.c())).d;
        ((com.avast.android.cleaner.analyzers.daodata.App)this.g.get(localApp.c())).e = ((com.avast.android.cleaner.analyzers.daodata.App)this.e.get(localApp.c())).e;
      }
    }
  }
  
  private boolean m()
  {
    DebugLog.b("ApplicationAnalyzer", "saveAnalyzedApplicationsToDb: called");
    AppDao localAppDao = AppsDBManager.a().b().a();
    localAppDao.f();
    localAppDao.a(this.e.values());
    return true;
  }
  
  private HashMap<String, com.avast.android.cleaner.analyzers.daodata.App> n()
  {
    DebugLog.b("ApplicationAnalyzer", "loadAnalyzedApplicationsFromDb: called");
    HashMap localHashMap1 = new HashMap();
    try
    {
      HashMap localHashMap2 = AppsDaoHelper.a();
      return localHashMap2;
    }
    catch (Exception localException)
    {
      DebugLog.e("ApplicationAnalyzer", localException.getMessage());
    }
    return localHashMap1;
  }
  
  private void o()
  {
    DebugLog.b("ApplicationAnalyzer", "analyzeIfNeededAndNotifyListeners: called");
    if (this.e.isEmpty())
    {
      DebugLog.e("ApplicationAnalyzer", "analyzeIfNeededAndNotifyListeners: applicationDataMap is null - reAnalyzing");
      this.i.a(false);
      this.f = true;
      g();
      return;
    }
    i();
  }
  
  public void a(AnalyzerListener paramAnalyzerListener)
  {
    synchronized (this.c)
    {
      if (!a(paramAnalyzerListener)) {
        this.c.add(paramAnalyzerListener);
      }
      if (this.h) {
        paramAnalyzerListener.a(new ArrayList(this.e.values()));
      }
      return;
    }
  }
  
  public boolean a(String paramString)
  {
    return this.e.containsKey(paramString);
  }
  
  public com.avast.android.cleaner.analyzers.daodata.App b(String paramString)
  {
    if ((paramString != null) && (this.e.containsKey(paramString))) {
      return (com.avast.android.cleaner.analyzers.daodata.App)this.e.get(paramString);
    }
    return null;
  }
  
  public void c()
  {
    DebugLog.b("ApplicationAnalyzer", "scheduleBatteryUsageAnalysisAlarm: called.");
    Object localObject1 = ProjectApp.B().getApplicationContext();
    Object localObject2 = new Intent((Context)localObject1, AlarmReceiver.class);
    ((Intent)localObject2).putExtra("extra_key_action", 1001);
    localObject2 = PendingIntent.getBroadcast((Context)localObject1, 1001, (Intent)localObject2, 134217728);
    localObject1 = (AlarmManager)((Context)localObject1).getSystemService("alarm");
    ((AlarmManager)localObject1).cancel((PendingIntent)localObject2);
    ((AlarmManager)localObject1).set(1, System.currentTimeMillis() + 21600000L, (PendingIntent)localObject2);
  }
  
  public boolean d()
  {
    return (this.h) && (!this.e.isEmpty());
  }
  
  public List<com.avast.android.cleaner.analyzers.daodata.App> e()
  {
    return new ArrayList(this.e.values());
  }
  
  public void f()
  {
    DebugLog.b("ApplicationAnalyzer", "loadApplicationList: called");
    if (!this.e.isEmpty())
    {
      DebugLog.b("ApplicationAnalyzer", "loadApplicationList: OK");
      o();
      return;
    }
    if (this.i.a())
    {
      j();
      return;
    }
    this.f = true;
  }
  
  public void g()
  {
    try
    {
      DebugLog.b("ApplicationAnalyzer", "analyzeAndSaveAnalyzedApplications: called");
      DebugLog.b("ApplicationAnalyzer", "ApplicationAnalyzer Started - analyzing applications");
      this.g = new HashMap();
      k();
      l();
      Context localContext = ProjectApp.B().getApplicationContext();
      Iterator localIterator = this.b.values().iterator();
      while (localIterator.hasNext()) {
        ((BaseAnalyzer)localIterator.next()).a(localContext, this.g);
      }
      this.e = this.g;
    }
    finally {}
    this.h = true;
    synchronized (this.d)
    {
      if (m()) {
        this.i.a(true);
      }
      if (this.f)
      {
        o();
        this.f = false;
      }
      DebugLog.b("ApplicationAnalyzer", "ApplicationAnalyzer Ended");
      return;
    }
  }
  
  public void h()
  {
    try
    {
      if (!d()) {
        g();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
