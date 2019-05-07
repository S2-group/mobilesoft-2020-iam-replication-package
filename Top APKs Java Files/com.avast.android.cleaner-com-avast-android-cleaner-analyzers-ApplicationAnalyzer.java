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
import eu.inmite.android.fw.DebugLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApplicationAnalyzer
{
  private static final List<String> a = Arrays.asList(new String[] { "ingramhi.antivirus", "ingramlow.antivirus", "com.avg.zen", "com.antivirus", "org.antivirus", "com.antivirus.tablet", "org.antivirus.tablet", "com.s.antivirus", "oem.antivirus", "store.antivirus", "org.premiumsecurity", "org.premiumsecurity.tablet", "avgmobile90.antivirus", "avgmobile365.antivirus", "avg.antivirus", "avgmobile.antivirus", "com.ingramhi.antivirus", "com.ingramlow.antivirus", "com.tcl.antivirus", "com.mpc.cleaner" });
  private static ApplicationAnalyzer b;
  private final Map<Analyzer.AnalysisType, BaseAnalyzer> c = new HashMap();
  private final ArrayList<AnalyzerListener> d;
  private final Object e = new Object();
  private volatile HashMap<String, com.avast.android.cleaner.analyzers.daodata.App> f;
  private boolean g = false;
  private HashMap<String, com.avast.android.cleaner.analyzers.daodata.App> h;
  private HashMap<String, Boolean> i = new HashMap();
  private boolean j;
  private AnalyzerPrefs k;
  
  private ApplicationAnalyzer()
  {
    Context localContext = ProjectApp.y().getApplicationContext();
    this.j = false;
    this.g = false;
    this.d = new ArrayList();
    this.h = new HashMap();
    this.f = new HashMap();
    this.k = new AnalyzerPrefs(localContext);
    j();
    this.c.put(Analyzer.AnalysisType.DATA, new DataUsageAnalyzer(localContext));
    this.c.put(Analyzer.AnalysisType.BATTERY, new BatteryUsageAnalyzer(localContext));
  }
  
  public static ApplicationAnalyzer a()
  {
    try
    {
      if (b == null) {
        b = new ApplicationAnalyzer();
      }
      ApplicationAnalyzer localApplicationAnalyzer = b;
      return localApplicationAnalyzer;
    }
    finally {}
  }
  
  private static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (!paramApplicationInfo.sourceDir.contains("/system")) && ((paramApplicationInfo.flags & 0x1) == 0) && ((paramApplicationInfo.flags & 0x80) == 0) && (!a.contains(paramApplicationInfo.packageName));
  }
  
  private boolean a(Object paramObject)
  {
    Iterator localIterator = this.d.iterator();
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
    DebugLog.c("ScannerLifecycleCallbackImpl.startAnalzyers()");
    Context localContext = ProjectApp.y().getApplicationContext();
    AnalyzerIntentService.b(localContext);
    a().c();
    AnalyzerIntentService.a(localContext);
  }
  
  private void i()
  {
    synchronized (this.d)
    {
      Iterator localIterator = this.d.iterator();
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
    this.i.put("com.avg.uninstaller", Boolean.valueOf(true));
    this.i.put("com.anglelabs.alarmclock", Boolean.valueOf(true));
    this.i.put("com.alarmclock.xtreme", Boolean.valueOf(true));
    this.i.put("com.alarmclock.xtreme.free", Boolean.valueOf(true));
    this.i.put("com.anglelabs.stopwatch", Boolean.valueOf(true));
    this.i.put("com.anglelabs.stopwatch.free", Boolean.valueOf(true));
    this.i.put("com.anglelabs.volumemanager.pro", Boolean.valueOf(true));
    this.i.put("com.anglelabs.volumemanager.free", Boolean.valueOf(true));
    this.i.put("com.avg.vault", Boolean.valueOf(true));
    this.i.put("com.avg.cleaner", Boolean.valueOf(true));
    this.i.put("dev.cleaner", Boolean.valueOf(true));
    this.i.put("com.oem.cleaner", Boolean.valueOf(true));
    this.i.put("com.store.cleaner", Boolean.valueOf(true));
    this.i.put("com.s.cleaner", Boolean.valueOf(true));
    this.i.put("com.mpc.cleaner", Boolean.valueOf(true));
    this.i.put("com.avg.shrinker", Boolean.valueOf(true));
    this.i.put("com.avg.tuneup", Boolean.valueOf(true));
    this.i.put("com.antivirus", Boolean.valueOf(true));
    this.i.put("org.antivirus", Boolean.valueOf(true));
    this.i.put("org.antivirus.trial", Boolean.valueOf(true));
    this.i.put("org.antivirus.tablet", Boolean.valueOf(true));
    this.i.put("com.antivirus.tablet", Boolean.valueOf(true));
    this.i.put("com.ingramhi.antivirus", Boolean.valueOf(true));
    this.i.put("com.ingramlow.antivirus ", Boolean.valueOf(true));
    this.i.put("com.tcl.antivirus", Boolean.valueOf(true));
  }
  
  private void k()
  {
    synchronized (this.e)
    {
      this.f = o();
      this.j = true;
      p();
      return;
    }
  }
  
  private void l()
  {
    for (;;)
    {
      try
      {
        Context localContext = ProjectApp.y().getApplicationContext();
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
              break label195;
            }
            localIntent = null;
            localApp.c = localIntent;
            this.h.put(localApp.c(), localApp);
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
      label195:
      String str = localException2.getComponent().getClassName();
    }
  }
  
  private void m()
  {
    Iterator localIterator = this.f.values().iterator();
    while (localIterator.hasNext())
    {
      com.avast.android.cleaner.analyzers.daodata.App localApp = (com.avast.android.cleaner.analyzers.daodata.App)localIterator.next();
      if (this.h.get(localApp.c()) != null)
      {
        ((com.avast.android.cleaner.analyzers.daodata.App)this.h.get(localApp.c())).a = ((com.avast.android.cleaner.analyzers.daodata.App)this.f.get(localApp.c())).a;
        ((com.avast.android.cleaner.analyzers.daodata.App)this.h.get(localApp.c())).d = ((com.avast.android.cleaner.analyzers.daodata.App)this.f.get(localApp.c())).d;
        ((com.avast.android.cleaner.analyzers.daodata.App)this.h.get(localApp.c())).e = ((com.avast.android.cleaner.analyzers.daodata.App)this.f.get(localApp.c())).e;
      }
    }
  }
  
  private boolean n()
  {
    DebugLog.b("ApplicationAnalyzer", "saveAnalyzedApplicationsToDb: called");
    AppDao localAppDao = AppsDBManager.a().b().a();
    localAppDao.f();
    localAppDao.a(this.f.values());
    return true;
  }
  
  private HashMap<String, com.avast.android.cleaner.analyzers.daodata.App> o()
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
  
  private void p()
  {
    DebugLog.b("ApplicationAnalyzer", "analyzeIfNeededAndNotifyListeners: called");
    if (this.f.isEmpty())
    {
      DebugLog.e("ApplicationAnalyzer", "analyzeIfNeededAndNotifyListeners: applicationDataMap is null - reAnalyzing");
      this.k.a(false);
      this.g = true;
      g();
      return;
    }
    i();
  }
  
  public void a(AnalyzerListener paramAnalyzerListener)
  {
    synchronized (this.d)
    {
      if (!a(paramAnalyzerListener)) {
        this.d.add(paramAnalyzerListener);
      }
      if (this.j) {
        paramAnalyzerListener.a(new ArrayList(this.f.values()));
      }
      return;
    }
  }
  
  public boolean a(String paramString)
  {
    return this.f.containsKey(paramString);
  }
  
  public com.avast.android.cleaner.analyzers.daodata.App b(String paramString)
  {
    if ((paramString != null) && (this.f.containsKey(paramString))) {
      return (com.avast.android.cleaner.analyzers.daodata.App)this.f.get(paramString);
    }
    return null;
  }
  
  public void c()
  {
    DebugLog.b("ApplicationAnalyzer", "scheduleBatteryUsageAnalysisAlarm: called.");
    Object localObject1 = ProjectApp.y().getApplicationContext();
    Object localObject2 = new Intent((Context)localObject1, AlarmReceiver.class);
    ((Intent)localObject2).putExtra("extra_key_action", 1001);
    localObject2 = PendingIntent.getBroadcast((Context)localObject1, 1001, (Intent)localObject2, 134217728);
    localObject1 = (AlarmManager)((Context)localObject1).getSystemService("alarm");
    ((AlarmManager)localObject1).cancel((PendingIntent)localObject2);
    ((AlarmManager)localObject1).set(1, System.currentTimeMillis() + 21600000L, (PendingIntent)localObject2);
  }
  
  public boolean d()
  {
    return (this.j) && (!this.f.isEmpty());
  }
  
  public List<com.avast.android.cleaner.analyzers.daodata.App> e()
  {
    return new ArrayList(this.f.values());
  }
  
  public void f()
  {
    DebugLog.b("ApplicationAnalyzer", "loadApplicationList: called");
    if (!this.f.isEmpty())
    {
      DebugLog.b("ApplicationAnalyzer", "loadApplicationList: empty!!");
      p();
      return;
    }
    if (this.k.a())
    {
      k();
      return;
    }
    this.g = true;
  }
  
  public void g()
  {
    try
    {
      DebugLog.b("ApplicationAnalyzer", "analyzeAndSaveAnalyzedApplications: called");
      DebugLog.b("ApplicationAnalyzer", "ApplicationAnalyzer Started - analyzing applications");
      this.h = new HashMap();
      l();
      m();
      Context localContext = ProjectApp.y().getApplicationContext();
      Iterator localIterator = this.c.values().iterator();
      while (localIterator.hasNext()) {
        ((BaseAnalyzer)localIterator.next()).a(localContext, this.h);
      }
      this.f = this.h;
    }
    finally {}
    this.j = true;
    synchronized (this.e)
    {
      if (n()) {
        this.k.a(true);
      }
      if (this.g)
      {
        p();
        this.g = false;
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
