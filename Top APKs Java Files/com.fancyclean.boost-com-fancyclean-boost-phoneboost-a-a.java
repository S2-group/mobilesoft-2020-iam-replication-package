package com.fancyclean.boost.phoneboost.a;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.fancyclean.boost.common.i;
import com.fancyclean.boost.common.receiver.b.a;
import com.fancyclean.boost.phoneboost.model.RunningApp;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.thinkyeah.common.q;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class a
{
  private static final q a = q.a(a.class);
  @SuppressLint({"StaticFieldLeak"})
  private static a b;
  private static final Set<String> m = new HashSet() {};
  private static List<String> n = new ArrayList() {};
  private Context c;
  private PackageManager d;
  private ActivityManager e;
  private UsageStatsManager f;
  private String g;
  private com.fancyclean.boost.phoneboost.b.b h;
  private Set<String> i = null;
  private Random j;
  private volatile long k = 0L;
  private boolean l = false;
  
  private a(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    this.d = this.c.getPackageManager();
    this.e = ((ActivityManager)this.c.getSystemService("activity"));
    this.j = new Random();
    this.h = new com.fancyclean.boost.phoneboost.b.b(paramContext);
    if (i.b()) {
      this.f = ((UsageStatsManager)this.c.getSystemService("usagestats"));
    }
  }
  
  public static a a(Context paramContext)
  {
    if (b == null) {
      try
      {
        if (b == null) {
          b = new a(paramContext);
        }
      }
      finally {}
    }
    return b;
  }
  
  private com.fancyclean.boost.phoneboost.model.a a(b paramB, boolean paramBoolean)
  {
    a.h("==> getAppProcessesPreN");
    Object localObject1 = com.jaredrummler.android.processes.a.a();
    HashMap localHashMap = new HashMap(((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      AndroidAppProcess localAndroidAppProcess = (AndroidAppProcess)((Iterator)localObject1).next();
      Object localObject2 = localAndroidAppProcess.a();
      if (!a((String)localObject2, localAndroidAppProcess.b))
      {
        if (!localHashMap.containsKey(localObject2)) {
          localHashMap.put(localObject2, new HashSet());
        }
        localObject2 = (Set)localHashMap.get(localObject2);
        if (localObject2 != null) {
          ((Set)localObject2).add(Integer.valueOf(localAndroidAppProcess.d));
        }
      }
    }
    return a(localHashMap, paramB, paramBoolean);
  }
  
  private com.fancyclean.boost.phoneboost.model.a a(Map<String, Set<Integer>> paramMap, b paramB, boolean paramBoolean)
  {
    com.fancyclean.boost.phoneboost.model.a localA = new com.fancyclean.boost.phoneboost.model.a();
    Iterator localIterator = paramMap.keySet().iterator();
    long l1 = 0L;
    while (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      if ((paramB != null) && (paramB.a())) {
        break;
      }
      RunningApp localRunningApp = new RunningApp((String)localObject1);
      try
      {
        Object localObject2 = this.d.getApplicationInfo((String)localObject1, 0);
        localRunningApp.a(this.d.getApplicationLabel((ApplicationInfo)localObject2).toString());
        localObject2 = (Set)paramMap.get(localObject1);
        localObject1 = null;
        int i1;
        if (localObject2 != null)
        {
          localObject1 = new int[((Set)localObject2).size()];
          localObject2 = ((Set)localObject2).iterator();
          i1 = 0;
          while (((Iterator)localObject2).hasNext())
          {
            localObject1[i1] = ((Integer)((Iterator)localObject2).next()).intValue();
            i1 += 1;
          }
          localRunningApp.a((int[])localObject1);
        }
        if (paramBoolean)
        {
          localObject1 = this.e.getProcessMemoryInfo((int[])localObject1);
          i1 = localObject1.length;
          long l2 = 0L;
          int i2 = 0;
          while (i2 < i1)
          {
            long l3 = localObject1[i2].getTotalPss() * 1024;
            i2 += 1;
            l2 += l3;
          }
          if (l2 <= 0L) {
            continue;
          }
          localRunningApp.a(l2);
          l1 += l2;
          if (paramB != null) {
            paramB.a(l1, false, localRunningApp);
          }
        }
        localA.a(localRunningApp);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        a.a(localNameNotFoundException);
      }
    }
    if (paramBoolean)
    {
      localA.a(l1);
      return localA;
    }
    localA.a(m());
    return localA;
  }
  
  private boolean a(String paramString)
  {
    Object localObject2 = this.i;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = f();
      localObject1 = new HashSet();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((Set)localObject1).add(((com.fancyclean.boost.phoneboost.model.d)((Iterator)localObject2).next()).a());
      }
      this.i = ((Set)localObject1);
    }
    return ((Set)localObject1).contains(paramString);
  }
  
  private boolean a(String paramString, int paramInt)
  {
    return (TextUtils.isEmpty(paramString)) || (this.c.getPackageName().equals(paramString)) || (paramInt < 10000) || ((this.g != null) && (this.g.equals(paramString))) || (paramString.startsWith("com.google.android.")) || (m.contains(paramString)) || (a(paramString));
  }
  
  private com.fancyclean.boost.phoneboost.model.a b(b paramB, boolean paramBoolean)
  {
    a.h("==> getAppProcessesPreO");
    Object localObject1 = this.e.getRunningServices(Integer.MAX_VALUE);
    HashMap localHashMap = new HashMap(((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
      Object localObject2 = localRunningServiceInfo.service.getPackageName();
      if (!a((String)localObject2, localRunningServiceInfo.uid))
      {
        if (!localHashMap.containsKey(localObject2)) {
          localHashMap.put(localObject2, new HashSet());
        }
        localObject2 = (Set)localHashMap.get(localObject2);
        if (localObject2 != null) {
          ((Set)localObject2).add(Integer.valueOf(localRunningServiceInfo.pid));
        }
      }
    }
    return a(localHashMap, paramB, paramBoolean);
  }
  
  private boolean b(String paramString)
  {
    return this.c.getPackageName().equalsIgnoreCase(paramString);
  }
  
  private com.fancyclean.boost.phoneboost.model.a c(b paramB, boolean paramBoolean)
  {
    a.h("==> getAppProcessesO");
    com.fancyclean.boost.phoneboost.model.a localA = new com.fancyclean.boost.phoneboost.model.a();
    localA.a(true);
    localA.a(m());
    if (i.c(this.c)) {
      paramB = c(paramB);
    } else {
      paramB = d(paramB);
    }
    if ((paramB != null) && (!paramB.isEmpty()))
    {
      paramB = paramB.iterator();
      while (paramB.hasNext()) {
        localA.a((RunningApp)paramB.next());
      }
    }
    return localA;
  }
  
  private List<RunningApp> c(b paramB)
  {
    int i1 = new Random().nextInt(10);
    Object localObject2 = new HashSet();
    long l1 = System.currentTimeMillis();
    Object localObject1 = this.f.queryEvents(l1 - 10800000L, l1 + 2500L);
    Object localObject3;
    if (localObject1 != null)
    {
      localObject3 = new UsageEvents.Event();
      while (((UsageEvents)localObject1).hasNextEvent()) {
        if (((UsageEvents)localObject1).getNextEvent((UsageEvents.Event)localObject3)) {
          ((Set)localObject2).add(((UsageEvents.Event)localObject3).getPackageName());
        }
      }
    }
    localObject1 = new ArrayList(((Set)localObject2).size());
    localObject2 = ((Set)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject4 = (String)((Iterator)localObject2).next();
      if ((paramB != null) && (paramB.a())) {
        return localObject1;
      }
      if (((List)localObject1).size() >= i1 + 25) {
        return localObject1;
      }
      try
      {
        localObject3 = this.d.getApplicationInfo((String)localObject4, 0);
        if (!a((String)localObject4, ((ApplicationInfo)localObject3).uid))
        {
          localObject4 = new RunningApp((String)localObject4);
          ((RunningApp)localObject4).a(this.d.getApplicationLabel((ApplicationInfo)localObject3).toString());
          ((List)localObject1).add(localObject4);
          if (paramB != null) {
            paramB.a(0L, true, (RunningApp)localObject4);
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        a.a(localNameNotFoundException);
      }
    }
    return localObject1;
  }
  
  private List<RunningApp> d(b paramB)
  {
    int i1 = new Random().nextInt(10) + 25;
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet = new HashSet();
    Iterator localIterator = n.iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (((paramB != null) && (paramB.a())) || (localArrayList.size() >= i1)) {
        break;
      }
      try
      {
        localObject = this.d.getApplicationInfo(str, 0);
        if (!a(((ApplicationInfo)localObject).packageName, ((ApplicationInfo)localObject).uid))
        {
          RunningApp localRunningApp = new RunningApp(str);
          localRunningApp.a(this.d.getApplicationLabel((ApplicationInfo)localObject).toString());
          localArrayList.add(localRunningApp);
          localHashSet.add(str);
          if (paramB != null) {
            paramB.a(0L, true, localRunningApp);
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        a.a(localNameNotFoundException);
      }
    }
    localIterator = this.d.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((paramB != null) && (paramB.a())) {
        return localArrayList;
      }
      if (localArrayList.size() >= i1) {
        return localArrayList;
      }
      if ((!localHashSet.contains(localApplicationInfo.packageName)) && (!a(localApplicationInfo.packageName, localApplicationInfo.uid)))
      {
        localObject = new RunningApp(localApplicationInfo.packageName);
        ((RunningApp)localObject).a(this.d.getApplicationLabel(localApplicationInfo).toString());
        localArrayList.add(localObject);
        if (paramB != null) {
          paramB.a(0L, true, (RunningApp)localObject);
        }
      }
    }
    return localArrayList;
  }
  
  private boolean i()
  {
    long l1 = System.currentTimeMillis();
    long l2 = com.fancyclean.boost.phoneboost.a.a(this.c);
    long l3 = com.fancyclean.boost.common.d.e();
    return (l1 < l2) || (l1 - l2 > l3);
  }
  
  private com.fancyclean.boost.phoneboost.model.b j()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    this.e.getMemoryInfo(localMemoryInfo);
    if ((localMemoryInfo.totalMem > 0L) && (localMemoryInfo.availMem > 0L)) {
      return new com.fancyclean.boost.phoneboost.model.b(localMemoryInfo.totalMem, localMemoryInfo.availMem);
    }
    return null;
  }
  
  private void k()
  {
    a.h("startMonitorMemory");
    new a(this.k).start();
  }
  
  private void l()
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = this.d.resolveActivity((Intent)localObject, 65536);
    if ((localObject != null) && (((ResolveInfo)localObject).activityInfo != null)) {
      this.g = ((ResolveInfo)localObject).activityInfo.packageName;
    }
  }
  
  private long m()
  {
    com.fancyclean.boost.phoneboost.model.b localB = b();
    if (localB == null) {
      return (this.j.nextFloat() * (float)209715200L) + 314572800L;
    }
    if (localB.e() < 60) {
      return Math.min((this.j.nextFloat() * (float)209715200L) + 314572800L, (localB.d() * 0.6D));
    }
    int i1 = this.j.nextInt(5);
    return localB.d() - localB.c() * (i1 + 55) / 100L;
  }
  
  public long a(Collection<RunningApp> paramCollection, boolean paramBoolean)
  {
    if ((paramCollection != null) && (!paramCollection.isEmpty()))
    {
      paramCollection = paramCollection.iterator();
      for (l1 = 0L;; l1 += l2)
      {
        l2 = l1;
        if (!paramCollection.hasNext()) {
          break;
        }
        RunningApp localRunningApp = (RunningApp)paramCollection.next();
        l2 = localRunningApp.d();
        try
        {
          this.e.killBackgroundProcesses(localRunningApp.a());
        }
        catch (Exception localException)
        {
          a.a(localException);
          Crashlytics.logException(localException);
        }
      }
    }
    long l2 = 0L;
    long l1 = l2;
    if (l2 <= 0L) {
      l1 = m();
    }
    if (!paramBoolean)
    {
      com.fancyclean.boost.phoneboost.a.b(this.c, l1);
      com.fancyclean.boost.phoneboost.a.a(this.c, System.currentTimeMillis());
    }
    return l1;
  }
  
  public com.fancyclean.boost.phoneboost.model.a a(b paramB)
  {
    l();
    if (Build.VERSION.SDK_INT < 24) {
      return a(paramB, false);
    }
    if (Build.VERSION.SDK_INT < 26) {
      return b(paramB, false);
    }
    return c(paramB, false);
  }
  
  public boolean a()
  {
    return (i()) || (com.fancyclean.boost.common.b.x(this.c));
  }
  
  public boolean a(com.fancyclean.boost.phoneboost.model.d paramD)
  {
    paramD = paramD.a();
    boolean bool = this.h.a(paramD);
    if (bool) {
      this.i = null;
    }
    return bool;
  }
  
  public com.fancyclean.boost.phoneboost.model.a b(b paramB)
  {
    l();
    if (Build.VERSION.SDK_INT < 24) {
      return a(paramB, true);
    }
    if (Build.VERSION.SDK_INT < 26) {
      return b(paramB, true);
    }
    return c(paramB, true);
  }
  
  public com.fancyclean.boost.phoneboost.model.b b()
  {
    com.fancyclean.boost.phoneboost.model.b localB = j();
    if (localB == null) {
      return null;
    }
    if (!i())
    {
      long l1 = com.fancyclean.boost.phoneboost.a.b(this.c);
      localB.a(localB.b() + l1);
      localB.a(true);
    }
    return localB;
  }
  
  public boolean b(com.fancyclean.boost.phoneboost.model.d paramD)
  {
    boolean bool;
    if (this.h.a(paramD) != -1L) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool) {
      this.i = null;
    }
    return bool;
  }
  
  public void c()
  {
    if (this.l) {
      return;
    }
    this.l = true;
    this.k = SystemClock.elapsedRealtime();
    k();
    com.fancyclean.boost.common.receiver.b.a(this.c).a(new b.a()
    {
      public void a()
      {
        a.a(a.this, SystemClock.elapsedRealtime());
        a.a(a.this);
      }
      
      public void b()
      {
        a.a(a.this, 0L);
      }
    });
  }
  
  public boolean d()
  {
    return true;
  }
  
  public boolean e()
  {
    return (Build.VERSION.SDK_INT >= 26) && (!i.c(this.c));
  }
  
  /* Error */
  public List<com.fancyclean.boost.phoneboost.model.d> f()
  {
    // Byte code:
    //   0: new 377	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 384	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: new 556	com/fancyclean/boost/phoneboost/b/a
    //   12: dup
    //   13: aload_0
    //   14: getfield 105	com/fancyclean/boost/phoneboost/a/a:h	Lcom/fancyclean/boost/phoneboost/b/b;
    //   17: invokevirtual 559	com/fancyclean/boost/phoneboost/b/b:a	()Landroid/database/Cursor;
    //   20: invokespecial 562	com/fancyclean/boost/phoneboost/b/a:<init>	(Landroid/database/Cursor;)V
    //   23: astore 4
    //   25: aconst_null
    //   26: astore_3
    //   27: aload_3
    //   28: astore_2
    //   29: aload 4
    //   31: invokevirtual 564	com/fancyclean/boost/phoneboost/b/a:m	()Z
    //   34: ifeq +30 -> 64
    //   37: aload_3
    //   38: astore_2
    //   39: aload 5
    //   41: aload 4
    //   43: invokevirtual 567	com/fancyclean/boost/phoneboost/b/a:a	()Lcom/fancyclean/boost/phoneboost/model/d;
    //   46: invokeinterface 382 2 0
    //   51: pop
    //   52: aload_3
    //   53: astore_2
    //   54: aload 4
    //   56: invokevirtual 569	com/fancyclean/boost/phoneboost/b/a:l	()Z
    //   59: istore_1
    //   60: iload_1
    //   61: ifne -24 -> 37
    //   64: aload 4
    //   66: ifnull +8 -> 74
    //   69: aload 4
    //   71: invokevirtual 572	com/fancyclean/boost/phoneboost/b/a:close	()V
    //   74: aload 5
    //   76: areturn
    //   77: astore_3
    //   78: goto +8 -> 86
    //   81: astore_3
    //   82: aload_3
    //   83: astore_2
    //   84: aload_3
    //   85: athrow
    //   86: aload 4
    //   88: ifnull +31 -> 119
    //   91: aload_2
    //   92: ifnull +22 -> 114
    //   95: aload 4
    //   97: invokevirtual 572	com/fancyclean/boost/phoneboost/b/a:close	()V
    //   100: goto +19 -> 119
    //   103: astore 4
    //   105: aload_2
    //   106: aload 4
    //   108: invokevirtual 575	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   111: goto +8 -> 119
    //   114: aload 4
    //   116: invokevirtual 572	com/fancyclean/boost/phoneboost/b/a:close	()V
    //   119: aload_3
    //   120: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	this	a
    //   59	2	1	bool	boolean
    //   28	78	2	localObject1	Object
    //   26	27	3	localObject2	Object
    //   77	1	3	localObject3	Object
    //   81	39	3	localThrowable1	Throwable
    //   23	73	4	localA	com.fancyclean.boost.phoneboost.b.a
    //   103	12	4	localThrowable2	Throwable
    //   7	68	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   29	37	77	finally
    //   39	52	77	finally
    //   54	60	77	finally
    //   84	86	77	finally
    //   29	37	81	java/lang/Throwable
    //   39	52	81	java/lang/Throwable
    //   54	60	81	java/lang/Throwable
    //   95	100	103	java/lang/Throwable
  }
  
  public List<com.fancyclean.boost.phoneboost.model.d> g()
  {
    Object localObject1 = this.d.getInstalledApplications(0);
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
      Object localObject2 = localApplicationInfo.packageName;
      if (!b((String)localObject2))
      {
        localObject2 = new com.fancyclean.boost.phoneboost.model.d((String)localObject2);
        ((com.fancyclean.boost.phoneboost.model.d)localObject2).a(localApplicationInfo.loadLabel(this.d).toString());
        localArrayList.add(localObject2);
      }
    }
    localArrayList.removeAll(f());
    return localArrayList;
  }
  
  private class a
    extends Thread
  {
    private long b;
    
    a(long paramLong)
    {
      this.b = paramLong;
    }
    
    public void run()
    {
      for (;;)
      {
        if (this.b != a.b(a.this))
        {
          a.h().h("stopMonitorMemory");
          return;
        }
        try
        {
          Thread.sleep(3000L);
        }
        catch (InterruptedException localInterruptedException)
        {
          a.h().a(localInterruptedException);
        }
        org.greenrobot.eventbus.c.a().d(new com.fancyclean.boost.phoneboost.model.c(a.this.b()));
      }
    }
  }
}
