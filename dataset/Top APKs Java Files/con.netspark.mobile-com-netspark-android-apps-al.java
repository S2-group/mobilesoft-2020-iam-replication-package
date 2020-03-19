package com.netspark.android.apps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.netspark.android.custom_rom.manufacturers.lg.b;
import com.netspark.android.netsvpn.NetSparkApplication;
import com.netspark.android.netsvpn.bx;
import com.netspark.android.netsvpn.by;
import com.netspark.android.netsvpn.dx;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SuppressLint({"UseSparseArrays"})
public class al
{
  public static String b = "com.sec.android.app.launcher";
  static String c = "com.android.vending";
  public static String e = "";
  public static int i;
  @SuppressLint({"StaticFieldLeak"})
  private static Context p;
  private static SharedPreferences q;
  private static SharedPreferences.Editor r;
  private static Long s;
  public HashMap<String, ak> a;
  long d = 0L;
  public long f = 0L;
  final HashMap<Integer, an> g = new HashMap();
  ab h;
  String j;
  public ao k;
  private String l;
  private String m;
  private String n;
  private String o;
  
  al(Context paramContext, String paramString)
  {
    try
    {
      this.j = paramString;
      paramString = new StringBuilder();
      paramString.append("apps_");
      paramString.append(this.j);
      this.l = paramString.toString();
      paramString = new StringBuilder();
      paramString.append(NetSparkApplication.q);
      paramString.append(this.l);
      this.n = paramString.toString();
      paramString = new StringBuilder();
      paramString.append("replacements_");
      paramString.append(this.j);
      this.m = paramString.toString();
      paramString = new StringBuilder();
      paramString.append(NetSparkApplication.q);
      paramString.append(this.m);
      this.o = paramString.toString();
      p = paramContext;
      c(this.j);
      this.k = new ao(this);
      paramContext = new StringBuilder();
      paramContext.append("Content_");
      paramContext.append(this.j);
      this.h = new ab(paramContext.toString());
      if (!h())
      {
        a(null);
        return;
      }
    }
    catch (Throwable paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("on MyApplicationList MyApplicationList got exception ");
      paramString.append(paramContext);
      dx.k(paramString.toString());
    }
  }
  
  public static int a(int paramInt)
  {
    boolean bool = NetSparkApplication.t;
    bool = NetSparkApplication.t;
    return paramInt | 0x200 | 0x2000;
  }
  
  private void a(ArrayList<String> paramArrayList)
  {
    for (;;)
    {
      try
      {
        c(false);
        localPackageManager = p.getPackageManager();
        localObject2 = localPackageManager.getInstalledPackages(a(64));
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new ArrayList();
        }
        localObject2 = new HashMap();
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject1).next();
          str = ((PackageInfo)localObject3).packageName;
          ((HashMap)localObject2).put(str, null);
          boolean bool = this.a.containsKey(str);
          if (bool) {}
        }
      }
      catch (Exception paramArrayList)
      {
        PackageManager localPackageManager;
        Object localObject2;
        Object localObject1;
        Object localObject3;
        String str;
        int i1;
        return;
      }
      try
      {
        i1 = ((PackageInfo)localObject3).applicationInfo.flags;
      }
      catch (Exception localException)
      {
        continue;
      }
      i1 = 0;
      localObject3 = ay.a(((PackageInfo)localObject3).signatures);
      this.a.put(str, new ak(str, i1, localPackageManager.getInstallerPackageName(str), (String)localObject3));
      if (paramArrayList != null) {
        paramArrayList.add(str);
      }
    }
    paramArrayList = this.a.keySet().iterator();
    while (paramArrayList.hasNext())
    {
      localObject1 = (String)paramArrayList.next();
      if (!((HashMap)localObject2).containsKey(localObject1)) {
        this.a.remove(localObject1);
      }
    }
    if (this.a.size() > 1)
    {
      AppsDetector.q.a(this.a);
      a(false, "List");
    }
  }
  
  private static void b(Long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("setInstallTimeOfFirstAppInstalledByUser");
    localStringBuilder.append(paramLong);
    dx.k(localStringBuilder.toString());
    s = paramLong;
    NetSparkApplication.b().putLong("stringinstallTimeOfFirstAppInstalledByUser", s.longValue()).apply();
  }
  
  @SuppressLint({"CommitPrefEdits"})
  private static void c(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("my_prefs_file_");
    localStringBuilder.append(paramString);
    paramString = localStringBuilder.toString();
    q = p.getSharedPreferences(paramString, 0);
    b.a(q, paramString, true);
    r = q.edit();
  }
  
  private void c(boolean paramBoolean)
  {
    HashMap localHashMap = this.a;
    if (localHashMap == null)
    {
      this.a = new HashMap();
      return;
    }
    if (paramBoolean) {
      localHashMap.clear();
    }
  }
  
  public static Long d()
  {
    if (s == null)
    {
      s = Long.valueOf(NetSparkApplication.c().getLong("stringinstallTimeOfFirstAppInstalledByUser", Long.MAX_VALUE));
      if (s.longValue() == Long.MAX_VALUE) {
        i();
      }
    }
    return s;
  }
  
  private boolean d(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      return bool;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  private String e()
  {
    try
    {
      try
      {
        this.k.a = q.getLong("WeekCounter", 0L);
        this.k.b = q.getLong("DayCounter", 0L);
        this.k.d = q.getLong("PermittedTimePerDay", 1000000000L);
        this.k.c = q.getLong("PermittedTimePerWeek", 1000000000L);
        this.k.e = q.getBoolean("DayTimeBlock", false);
        this.k.f = q.getBoolean("WeekTimeBlock", false);
        this.k.g = q.getBoolean("Active", false);
        if (bx.g)
        {
          this.f = 0L;
          NetSparkApplication.a(r.putLong("LastPrimaryFullAppsUpdate", 0L));
        }
        else
        {
          this.f = q.getLong("LastPrimaryFullAppsUpdate", 0L);
        }
      }
      finally
      {
        break label221;
      }
    }
    catch (Exception localException)
    {
      String str;
      label221:
      for (;;) {}
    }
    this.h.h();
    f(dx.d(this.o));
    str = dx.d(this.n);
    return str;
    throw str;
  }
  
  private boolean e(String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = paramString.split(";");
      int i2 = paramString.length;
      int i3 = paramString.length;
      int i1 = 0;
      while (i1 < i3)
      {
        String[] arrayOfString = paramString[i1].split(",");
        if (arrayOfString.length < 7) {
          return false;
        }
        this.a.put(arrayOfString[0], new ak(arrayOfString));
        i1 += 1;
      }
      i1 = this.a.size();
      if (i1 == i2) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private String f()
  {
    String str = "";
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext())
    {
      ak localAk = (ak)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(localAk.toString());
      localStringBuilder.append(";");
      str = localStringBuilder.toString();
    }
    return str;
  }
  
  private void f(String paramString)
  {
    try
    {
      paramString = paramString.split(";");
      int i2 = paramString.length;
      int i1 = 0;
      while (i1 < i2)
      {
        String[] arrayOfString = paramString[i1].split(",");
        this.g.put(Integer.valueOf(arrayOfString[0]), new an(arrayOfString));
        i1 += 1;
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  private String g()
  {
    String str = "";
    Iterator localIterator = this.g.values().iterator();
    while (localIterator.hasNext())
    {
      an localAn = (an)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(localAn.toString());
      localStringBuilder.append(";");
      str = localStringBuilder.toString();
    }
    return str;
  }
  
  private boolean h()
  {
    if (!d(this.n)) {
      return false;
    }
    String str = e();
    if (str.equals("")) {
      return false;
    }
    c(true);
    return e(str);
  }
  
  private static void i()
  {
    new am().start();
  }
  
  void a()
  {
    Object localObject = this.k;
    ((ao)localObject).b = 0L;
    ((ao)localObject).e = false;
    localObject = this.a;
    if (localObject != null) {}
    try
    {
      localObject = ((HashMap)localObject).values().iterator();
      while (((Iterator)localObject).hasNext()) {
        ((ak)((Iterator)localObject).next()).b = 0L;
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  /* Error */
  public void a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 183	com/netspark/android/apps/al:a	Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual 187	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifne +80 -> 90
    //   13: new 156	java/util/ArrayList
    //   16: dup
    //   17: invokespecial 157	java/util/ArrayList:<init>	()V
    //   20: astore_3
    //   21: aload_1
    //   22: iload_2
    //   23: invokestatic 418	com/netspark/android/apps/ak:a	(Ljava/lang/String;Z)Lcom/netspark/android/apps/ak;
    //   26: astore 4
    //   28: getstatic 239	com/netspark/android/apps/AppsDetector:q	Lcom/netspark/android/apps/h;
    //   31: aload 4
    //   33: invokevirtual 421	com/netspark/android/apps/h:a	(Lcom/netspark/android/apps/ak;)V
    //   36: aload_0
    //   37: getfield 183	com/netspark/android/apps/al:a	Ljava/util/HashMap;
    //   40: aload_1
    //   41: aload 4
    //   43: invokevirtual 181	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   46: pop
    //   47: aload_3
    //   48: aload_1
    //   49: invokevirtual 217	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   52: pop
    //   53: aload_0
    //   54: iconst_0
    //   55: ldc -10
    //   57: invokevirtual 249	com/netspark/android/apps/al:a	(ZLjava/lang/String;)V
    //   60: new 423	java/lang/Thread
    //   63: dup
    //   64: new 425	com/netspark/android/apps/ax
    //   67: dup
    //   68: aload_3
    //   69: aload_0
    //   70: invokespecial 428	com/netspark/android/apps/ax:<init>	(Ljava/util/ArrayList;Lcom/netspark/android/apps/al;)V
    //   73: ldc_w 430
    //   76: invokespecial 433	java/lang/Thread:<init>	(Ljava/lang/Runnable;Ljava/lang/String;)V
    //   79: invokevirtual 434	java/lang/Thread:start	()V
    //   82: goto +8 -> 90
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: astore_1
    //   94: goto -4 -> 90
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	al
    //   0	97	1	paramString	String
    //   0	97	2	paramBoolean	boolean
    //   20	49	3	localArrayList	ArrayList
    //   26	16	4	localAk	ak
    // Exception table:
    //   from	to	target	type
    //   2	82	85	finally
    //   2	82	93	java/lang/Exception
  }
  
  void a(boolean paramBoolean)
  {
    ao localAo = this.k;
    long l1 = localAo.b;
    long l2 = this.k.d;
    boolean bool2 = true;
    boolean bool1;
    if (l1 > l2) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    localAo.e = bool1;
    localAo = this.k;
    if (localAo.a > this.k.c) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    localAo.f = bool1;
    if (paramBoolean) {
      a(false, "Params");
    }
  }
  
  void a(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean) {}
    for (;;)
    {
      try
      {
        if (paramString.equals("List")) {
          dx.c(this.l, f());
        }
        if ((paramBoolean) || (paramString.equals("Replacements"))) {
          dx.c(this.m, g());
        }
        if ((paramBoolean) || (paramString.equals("TimeBuffer"))) {
          this.h.g();
        }
        if (!paramBoolean)
        {
          paramBoolean = paramString.equals("Params");
          if (!paramBoolean) {
            continue;
          }
        }
      }
      finally
      {
        continue;
      }
      try
      {
        c(this.j);
        r.putLong("WeekCounter", this.k.a).putLong("DayCounter", this.k.b).putLong("PermittedTimePerDay", this.k.d).putLong("PermittedTimePerWeek", this.k.c).putBoolean("DayTimeBlock", this.k.e).putBoolean("WeekTimeBlock", this.k.f).putBoolean("Active", this.k.g).putLong("LastPrimaryFullAppsUpdate", this.f);
        NetSparkApplication.a(r);
        NetSparkApplication.b().putInt("BlockUntrustedApps", by.i).apply();
        return;
        throw paramString;
      }
      catch (Exception paramString) {}
    }
  }
  
  public boolean a(String paramString)
  {
    Object localObject = paramString;
    for (;;)
    {
      boolean bool1;
      boolean bool2;
      try
      {
        if (paramString.startsWith("AtchDlg:")) {
          localObject = paramString.substring(8);
        }
        AppsDetector.l.a((String)localObject);
        if (BlockerPopupService.c((String)localObject)) {
          return false;
        }
        if (this.a.containsKey(localObject))
        {
          paramString = (ak)this.a.get(localObject);
          if ((paramString.b() < 2) && (paramString.d != -1))
          {
            bool1 = paramString.c();
            if (!bool1) {}
          }
        }
      }
      catch (Exception paramString)
      {
        an localAn;
        return false;
      }
      try
      {
        localObject = AppsDetector.l;
        localAn = (an)this.g.get(Integer.valueOf(paramString.d));
        ((d)localObject).k = localAn;
        if (localAn != null) {
          AppsDetector.l.j = true;
        }
      }
      catch (Exception localException)
      {
        continue;
        bool1 = false;
        continue;
        bool2 = false;
      }
    }
    localObject = AppsDetector.l;
    if ((this.k.e) && (!paramString.c) && (this.k.a()))
    {
      bool1 = true;
      if ((!this.k.f) || (paramString.c) || (!this.k.a())) {
        break label280;
      }
      bool2 = true;
      ((d)localObject).a(paramString, bool1, bool2, i, true);
      return AppsDetector.l.i;
      a((String)localObject, false);
      paramString = AppsDetector.l;
      bool1 = this.k.e;
      bool1 = this.k.f;
      paramString.a(null, false, false, i, true);
      bool1 = AppsDetector.l.i;
      return bool1;
    }
  }
  
  void b()
  {
    a();
    ao localAo = this.k;
    localAo.a = 0L;
    localAo.f = false;
  }
  
  /* Error */
  public void b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 183	com/netspark/android/apps/al:a	Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual 230	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   10: pop
    //   11: aload_0
    //   12: iconst_0
    //   13: ldc -10
    //   15: invokevirtual 249	com/netspark/android/apps/al:a	(ZLjava/lang/String;)V
    //   18: goto +8 -> 26
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: astore_1
    //   30: goto -4 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	33	0	this	al
    //   0	33	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
    //   2	18	29	java/lang/Exception
  }
  
  public void b(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    try
    {
      if (this.f + 43200000L >= dx.c()) {
        break label90;
      }
      localObject1 = new ArrayList();
      a((ArrayList)localObject1);
      new Thread(new ax((ArrayList)localObject1, this), "apps update").start();
      return;
    }
    catch (Throwable localThrowable)
    {
      Object localObject1;
      Object localObject2;
      for (;;) {}
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("CheckPrimaryUpdateNeeded - got Error: ");
    ((StringBuilder)localObject1).append(localObject2);
    dx.e("MyApplicationLis", ((StringBuilder)localObject1).toString());
    label90:
  }
  
  public ArrayList<String> c()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = this.a.values().iterator();
      while (localIterator.hasNext())
      {
        ak localAk = (ak)localIterator.next();
        if (localAk.b() == 0) {
          localArrayList.add(localAk.a);
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
}
