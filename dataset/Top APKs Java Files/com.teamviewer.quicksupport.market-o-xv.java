package o;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.TransactionTooLargeException;
import android.util.SparseArray;
import com.teamviewer.teamviewerlib.event.EventHub;
import com.teamviewer.teamviewerlib.event.EventHub.a;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

class xv
  extends agw<agu.a>
{
  private static final String a = afz.a().getPackageName();
  private Map<String, PackageStats> g = new ConcurrentHashMap();
  private final PackageManager h;
  private final List<b> i = new LinkedList();
  private final AtomicInteger j = new AtomicInteger(1);
  private final SparseArray<b> k = new SparseArray();
  private final int l = hashCode();
  private wj m = new wj()
  {
    public void a(int paramAnonymousInt, wg paramAnonymousWg, xn paramAnonymousXn)
    {
      if (paramAnonymousWg != wg.a)
      {
        qv.d("ModuleApps", "onMonitorData(): invalid type: " + paramAnonymousWg);
        return;
      }
      paramAnonymousWg = (xk)paramAnonymousXn.a();
      xv.a(xv.this, paramAnonymousWg.a(), paramAnonymousWg.b());
    }
  };
  private aed n = new aed()
  {
    public void a(EventHub.a paramAnonymousA, aef paramAnonymousAef)
    {
      int i = paramAnonymousAef.c(aee.al);
      paramAnonymousA = (agt)paramAnonymousAef.f(aee.an);
      if (!agt.b.equals(paramAnonymousA)) {
        xv.a(xv.this, i, paramAnonymousA);
      }
    }
  };
  
  public xv(Context paramContext)
  {
    super(ags.k, 3L, e(), agu.a.class);
    this.h = paramContext.getPackageManager();
  }
  
  private PackageStats a(String paramString)
  {
    PackageStats localPackageStats = (PackageStats)this.g.get(paramString);
    if (localPackageStats != null) {
      return localPackageStats;
    }
    return new a().a(paramString);
  }
  
  private static String a(List<b> paramList, String paramString)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      b localB = (b)paramList.next();
      if (paramString.equals(b.b(localB))) {
        return b.a(localB);
      }
    }
    return null;
  }
  
  private xu a(boolean paramBoolean)
  {
    Object localObject = this.h.getInstalledPackages(0);
    xy localXy = new xy();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localXy.a(a(this.h, localPackageInfo, paramBoolean));
        }
      }
    }
    qv.d("ModuleApps", "getInstalledApps(): packageList is null");
    return localXy;
  }
  
  private static b a(SparseArray<b> paramSparseArray, String paramString)
  {
    int i1 = 0;
    while (i1 < paramSparseArray.size())
    {
      if (paramString.equals(b.b((b)paramSparseArray.valueAt(i1)))) {
        return (b)paramSparseArray.valueAt(i1);
      }
      i1 += 1;
    }
    return null;
  }
  
  private xz a(PackageManager paramPackageManager, PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    xz localXz = new xz(paramPackageInfo.packageName);
    if (a(agu.a.d)) {
      localXz.a(agu.a.d, Integer.valueOf(paramPackageInfo.versionCode));
    }
    if (a(agu.a.e)) {
      localXz.a(agu.a.e, paramPackageInfo.versionName);
    }
    if (a(agu.a.b)) {
      localXz.a(agu.a.b, Long.valueOf(paramPackageInfo.firstInstallTime));
    }
    if (paramPackageInfo.applicationInfo != null)
    {
      if (a(agu.a.a))
      {
        paramPackageManager = paramPackageManager.getApplicationLabel(paramPackageInfo.applicationInfo);
        if (paramPackageManager != null) {
          localXz.a(agu.a.a, paramPackageManager.toString());
        }
      }
      if (a(agu.a.c))
      {
        paramPackageManager = new File(paramPackageInfo.applicationInfo.sourceDir);
        if ((paramPackageManager.exists()) && (paramPackageManager.isFile())) {
          localXz.a(agu.a.c, Long.valueOf(paramPackageManager.lastModified()));
        }
      }
    }
    if (paramBoolean)
    {
      paramPackageManager = a(paramPackageInfo.packageName);
      if (paramPackageManager == null) {
        break label344;
      }
      long l1 = paramPackageManager.cacheSize + paramPackageManager.externalCacheSize;
      long l2 = paramPackageManager.codeSize + paramPackageManager.externalCodeSize;
      long l3 = paramPackageManager.dataSize + paramPackageManager.externalDataSize + paramPackageManager.externalMediaSize + paramPackageManager.externalObbSize;
      if (a(agu.a.i)) {
        localXz.a(agu.a.i, Long.valueOf(l1));
      }
      if (a(agu.a.g)) {
        localXz.a(agu.a.g, Long.valueOf(l2));
      }
      if (a(agu.a.h)) {
        localXz.a(agu.a.h, Long.valueOf(l3));
      }
      if (a(agu.a.f)) {
        localXz.a(agu.a.f, Long.valueOf(l1 + l2 + l3));
      }
    }
    return localXz;
    label344:
    qv.d("ModuleApps", "getAppInfo: could not get package stats ");
    return localXz;
  }
  
  private void a(int paramInt, agt paramAgt)
  {
    for (;;)
    {
      synchronized (this.k)
      {
        b localB = (b)this.k.get(paramInt);
        if (localB == null) {
          break;
        }
        if (agt.c.equals(paramAgt))
        {
          paramAgt = acn.c.h;
          b(acn.b.c, paramAgt, null, b.b(localB), b.a(localB));
          return;
        }
      }
      paramAgt = acn.c.a;
    }
    qv.d("ModuleApps", "Cannot process app remove canceled: No pending removals.");
  }
  
  private void a(String paramString, xk.a paramA)
  {
    xy localXy = new xy();
    switch (4.b[paramA.ordinal()])
    {
    default: 
      qv.c("ModuleApps", "Unknown enum value!");
    }
    for (;;)
    {
      try
      {
        a(paramA, localXy.a().toString());
        return;
      }
      catch (JSONException paramString)
      {
        qv.d("ModuleApps", "onAppEvent(): cannot get JSON string: " + paramString.getMessage());
      }
      ??? = a(this.i, paramString);
      if (??? != null)
      {
        a(agw.a.a, vn.c.tv_rs_event_app_installed, new Object[] { paramString });
        a(acn.b.b, null, null, c(paramString), (String)???);
        this.i.remove(paramString);
      }
      paramString = b(paramString);
      if (paramString == null)
      {
        qv.d("ModuleApps", "onAppEvent(): appInfo is null");
        return;
      }
      localXy.a(paramString);
      continue;
      synchronized (this.k)
      {
        b localB = a(this.k, paramString);
        if (localB != null)
        {
          a(agw.a.a, vn.c.tv_rs_event_app_removed, new Object[] { paramString });
          b(acn.b.b, null, null, paramString, b.a(localB));
          this.k.remove(this.k.indexOfValue(localB));
          localXy.a(new xz(paramString, 1));
        }
        else
        {
          qv.c("ModuleApps", "Removal repsonse not sent. No request pending.");
        }
      }
    }
  }
  
  private void a(acn.b paramB, String paramString)
  {
    ack localAck = acl.a(acn.A);
    localAck.a(acn.p.a, paramB.a());
    if (paramString != null) {
      localAck.b(acn.p.d, paramString);
    }
    a(localAck, n());
  }
  
  private void a(acn.b paramB, acn.c paramC, String paramString1, String paramString2, String paramString3)
  {
    ack localAck = acl.a(acn.E);
    localAck.a(acn.u.a, paramB.a());
    if (paramC != null) {
      localAck.a(acn.u.b, paramC.a());
    }
    if (paramString1 != null) {
      localAck.a(acn.u.c, paramString1);
    }
    if (paramString2 != null) {
      localAck.b(acn.u.d, paramString2);
    }
    if (paramString3 != null) {
      localAck.a(acn.u.e, paramString3);
    }
    a(localAck, n());
  }
  
  private void a(acn.b paramB, acn.c paramC, String paramString1, String paramString2, acn.a paramA, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    ack localAck = acl.a(acn.C);
    localAck.a(acn.o.a, paramB.a());
    if (paramC != null) {
      localAck.a(acn.o.b, paramC.a());
    }
    if (paramString1 != null) {
      localAck.a(acn.o.c, paramString1);
    }
    if (paramString2 != null) {
      localAck.a(acn.o.d, paramString2);
    }
    if (paramA != null) {
      localAck.a(acn.o.e, paramA.a());
    }
    if (paramArrayOfByte != null) {
      localAck.a(acn.o.f, paramArrayOfByte);
    }
    if (paramInt1 > 0) {
      localAck.a(acn.o.g, paramInt1);
    }
    if (paramInt2 > 0) {
      localAck.a(acn.o.h, paramInt2);
    }
    a(localAck, n());
  }
  
  private void a(xk.a paramA, String paramString)
  {
    ack localAck = acl.a(acn.y);
    switch (4.b[paramA.ordinal()])
    {
    default: 
      localAck.b(acn.d.d, paramString);
    }
    for (;;)
    {
      a(localAck, n());
      return;
      localAck.b(acn.d.a, paramString);
      continue;
      localAck.b(acn.d.b, paramString);
      continue;
      localAck.b(acn.d.c, paramString);
    }
  }
  
  private xz b(String paramString)
  {
    try
    {
      paramString = this.h.getPackageInfo(paramString, 0);
      paramString = a(this.h, paramString, f());
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      qv.d("ModuleApps", "getAppInfo(): package not found");
      return null;
    }
    catch (RuntimeException paramString)
    {
      for (;;)
      {
        if ((paramString.getCause() instanceof TransactionTooLargeException)) {
          qv.c("ModuleApps", "PackageInfo query result was over the IPC transaction limit! (1MB)");
        } else {
          qv.d("ModuleApps", paramString.getMessage());
        }
      }
    }
  }
  
  private void b(ack paramAck)
  {
    if (!a(agu.a.j))
    {
      qv.d("ModuleApps", "handleRsCmdGetIcon(): received command but feature not negotiated");
      a(acn.b.c, acn.c.c, "feature not negotiated", null, null, null, -1, -1);
      return;
    }
    paramAck = paramAck.e(acn.n.a);
    if (paramAck.b <= 0)
    {
      qv.d("ModuleApps", "handleRsCmdGetIcon(): key param missing");
      a(acn.b.c, acn.c.b, "missing parameter", null, null, null, -1, -1);
      return;
    }
    paramAck = (String)paramAck.c;
    yb.a localA = yb.a(this.h, paramAck, 36, 36);
    if (localA != null)
    {
      a(acn.b.b, null, null, paramAck, acn.a.c, localA.c, localA.a, localA.b);
      return;
    }
    a(acn.b.c, acn.c.e, "package not found", paramAck, null, null, -1, -1);
  }
  
  private void b(acn.b paramB, acn.c paramC, String paramString1, String paramString2, String paramString3)
  {
    ack localAck = acl.a(acn.G);
    localAck.a(acn.z.a, paramB.a());
    if (paramC != null) {
      localAck.a(acn.z.b, paramC.a());
    }
    if (paramString1 != null) {
      localAck.a(acn.z.c, paramString1);
    }
    if (paramString2 != null) {
      localAck.a(acn.z.d, paramString2);
    }
    if (paramString3 != null) {
      localAck.a(acn.z.e, paramString3);
    }
    a(localAck, n());
  }
  
  private String c(String paramString)
  {
    xy localXy = new xy();
    localXy.a(new xz(paramString, 1));
    try
    {
      paramString = localXy.a().toString();
      return paramString;
    }
    catch (JSONException paramString)
    {
      qv.d("ModuleApps", "getAppInfoJsonString(): cannot get app info JSON string: " + paramString);
    }
    return null;
  }
  
  private void c(ack paramAck)
  {
    if (!a(agu.a.k))
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): received command but feature not negotiated");
      a(acn.b.c, acn.c.c, "feature not negotiated", null, null);
      return;
    }
    Object localObject1 = paramAck.e(acn.t.b);
    if (((adi)localObject1).b <= 0)
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): uuid param missing");
      a(acn.b.c, acn.c.b, "missing parameter", null, null);
      return;
    }
    localObject1 = (String)((adi)localObject1).c;
    paramAck = paramAck.e(acn.t.a);
    if (paramAck.b <= 0)
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): uri param missing");
      a(acn.b.c, acn.c.b, "missing parameter", null, (String)localObject1);
      return;
    }
    paramAck = Uri.parse((String)paramAck.c);
    if (paramAck == null)
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): invalid uri param");
      a(acn.b.c, acn.c.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    Object localObject2 = paramAck.getScheme();
    if (localObject2 == null)
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): uri scheme is null");
      a(acn.b.c, acn.c.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    if (!((String)localObject2).equals("file"))
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): invalid uri scheme: " + (String)localObject2);
      a(acn.b.c, acn.c.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    localObject2 = paramAck.getSchemeSpecificPart();
    if (localObject2 == null)
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): path to apk is null");
      a(acn.b.c, acn.c.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    if (!new File((String)localObject2).exists())
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): apk file does not exist: " + (String)localObject2);
      a(acn.b.c, acn.c.d, "file not found", null, (String)localObject1);
      return;
    }
    try
    {
      localObject2 = this.h.getPackageInfo((String)localObject2, 0);
      if (localObject2 != null)
      {
        localObject2 = ((PackageInfo)localObject2).packageName;
        this.i.add(new b((String)localObject2, (String)localObject1));
      }
      localObject2 = new Intent("android.intent.action.VIEW");
      ((Intent)localObject2).setDataAndType(paramAck, "application/vnd.android.package-archive");
      ((Intent)localObject2).setFlags(268435456);
      afz.a((Intent)localObject2);
      return;
    }
    catch (PackageManager.NameNotFoundException paramAck)
    {
      qv.d("ModuleApps", "handleRsCmdInstallApp(): package not found");
      a(acn.b.c, acn.c.e, "package not found", null, (String)localObject1);
      return;
    }
    catch (RuntimeException paramAck)
    {
      if ((paramAck.getCause() instanceof TransactionTooLargeException))
      {
        qv.c("ModuleApps", "PackageInfo query result was over the IPC transaction limit! (1MB)");
        return;
      }
      qv.d("ModuleApps", paramAck.getMessage());
    }
  }
  
  private void d(ack paramAck)
  {
    if (!a(agu.a.l))
    {
      qv.d("ModuleApps", "handleRsCmdRemoveApp(): received command but feature not negotiated");
      b(acn.b.c, acn.c.c, "feature not negotiated", null, null);
      return;
    }
    Object localObject = paramAck.e(acn.ai.b);
    if (((adi)localObject).b <= 0)
    {
      qv.d("ModuleApps", "handleRsCmdRemoveApp(): uuid param missing");
      a(acn.b.c, acn.c.b, "missing parameter", null, null);
      return;
    }
    localObject = (String)((adi)localObject).c;
    paramAck = paramAck.e(acn.y.a);
    if (paramAck.b <= 0)
    {
      qv.d("ModuleApps", "handleRsCmdRemoveApp(): key param missing");
      b(acn.b.c, acn.c.b, "missing parameter", null, (String)localObject);
      return;
    }
    paramAck = (String)paramAck.c;
    if (paramAck.equals(a))
    {
      qv.c("ModuleApps", "We don't want to remove ourselves...");
      b(acn.b.c, acn.c.g, null, paramAck, (String)localObject);
      return;
    }
    int i1 = this.j.getAndIncrement();
    synchronized (this.k)
    {
      this.k.put(i1, new b(paramAck, (String)localObject));
      localObject = new aef();
      ((aef)localObject).a(aee.al, i1);
      ((aef)localObject).a(aee.am, paramAck);
      EventHub.a().a(EventHub.a.aW, (aef)localObject);
      return;
    }
  }
  
  private static ArrayList<agu.a> e()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(agu.a.a);
    localArrayList.add(agu.a.c);
    localArrayList.add(agu.a.d);
    localArrayList.add(agu.a.e);
    localArrayList.add(agu.a.j);
    localArrayList.add(agu.a.k);
    localArrayList.add(agu.a.l);
    localArrayList.add(agu.a.b);
    if (f())
    {
      localArrayList.add(agu.a.f);
      localArrayList.add(agu.a.g);
      localArrayList.add(agu.a.h);
      localArrayList.add(agu.a.i);
    }
    return localArrayList;
  }
  
  private static boolean f()
  {
    return Build.VERSION.SDK_INT <= 25;
  }
  
  private void g()
  {
    try
    {
      Object localObject = a(false);
      if (localObject == null)
      {
        qv.d("ModuleApps", "handleRsCmdGetInstalledApps(): installedApps is null");
        a(acn.b.c, null);
        return;
      }
      localObject = ((xu)localObject).a();
      a(acn.b.b, ((JSONObject)localObject).toString());
      if (f())
      {
        afn.b.a(new Runnable()
        {
          public void run()
          {
            try
            {
              JSONObject localJSONObject = xv.a(xv.this, true).a();
              xv.a(xv.this, acn.b.b, localJSONObject.toString());
              return;
            }
            catch (JSONException localJSONException)
            {
              qv.d("ModuleApps", "handleRsCmdGetInstalledApps(): cannot get installed apps JSON string on second! attempt: " + localJSONException.getMessage());
              xv.a(xv.this, acn.b.c, null);
            }
          }
        });
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      qv.d("ModuleApps", "handleRsCmdGetInstalledApps(): cannot get installed apps JSON string: " + localJSONException.getMessage());
      a(acn.b.c, null);
    }
  }
  
  protected boolean a()
  {
    if (this.h == null)
    {
      qv.d("ModuleApps", "Cannot initialize apps module without PackageManager access");
      return false;
    }
    a(agh.c.r);
    return true;
  }
  
  public boolean a(ack paramAck)
  {
    if (super.a(paramAck)) {
      return true;
    }
    switch (4.a[paramAck.i().ordinal()])
    {
    default: 
      return false;
    case 1: 
      g();
      return true;
    case 2: 
      b(paramAck);
      return true;
    case 3: 
      c(paramAck);
      return true;
    }
    d(paramAck);
    return true;
  }
  
  public boolean a(acs paramAcs)
  {
    return false;
  }
  
  protected boolean b()
  {
    this.i.clear();
    synchronized (this.k)
    {
      this.k.clear();
      EventHub.a().a(this.n, EventHub.a.aX);
      return xf.e().a(wg.a, this.l, this.m);
    }
  }
  
  protected boolean c()
  {
    xf.e().a(this.l);
    EventHub.a().a(this.n);
    b localB;
    if (this.i.size() > 0)
    {
      localObject1 = this.i.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localB = (b)((Iterator)localObject1).next();
        a(acn.b.c, acn.c.f, null, null, b.a(localB));
      }
      this.i.clear();
    }
    Object localObject1 = this.k;
    int i1 = 0;
    try
    {
      while (i1 < this.k.size())
      {
        localB = (b)this.k.valueAt(i1);
        b(acn.b.c, acn.c.f, null, b.b(localB), b.a(localB));
        i1 += 1;
      }
      this.k.clear();
      this.g.clear();
      return true;
    }
    finally {}
  }
  
  class a
  {
    private Method b;
    private PackageStats c = null;
    private boolean d = false;
    
    a()
    {
      try
      {
        this.b = PackageManager.class.getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
        return;
      }
      catch (NoSuchMethodException this$1)
      {
        qv.d("ModuleApps", "cannot get PackageStats: " + xv.this.getMessage());
        return;
      }
      catch (IllegalArgumentException this$1)
      {
        qv.d("ModuleApps", "cannot get PackageStats: " + xv.this.getMessage());
      }
    }
    
    /* Error */
    PackageStats a(final String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 47	o/xv$a:b	Ljava/lang/reflect/Method;
      //   4: aload_0
      //   5: getfield 24	o/xv$a:a	Lo/xv;
      //   8: invokestatic 85	o/xv:a	(Lo/xv;)Landroid/content/pm/PackageManager;
      //   11: iconst_2
      //   12: anewarray 4	java/lang/Object
      //   15: dup
      //   16: iconst_0
      //   17: aload_1
      //   18: aastore
      //   19: dup
      //   20: iconst_1
      //   21: new 9	o/xv$a$1
      //   24: dup
      //   25: aload_0
      //   26: aload_1
      //   27: invokespecial 88	o/xv$a$1:<init>	(Lo/xv$a;Ljava/lang/String;)V
      //   30: aastore
      //   31: invokevirtual 94	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   34: pop
      //   35: aload_0
      //   36: monitorenter
      //   37: aload_0
      //   38: getfield 31	o/xv$a:d	Z
      //   41: istore_2
      //   42: iload_2
      //   43: ifne +7 -> 50
      //   46: aload_0
      //   47: invokevirtual 97	java/lang/Object:wait	()V
      //   50: aload_0
      //   51: monitorexit
      //   52: aload_0
      //   53: getfield 29	o/xv$a:c	Landroid/content/pm/PackageStats;
      //   56: areturn
      //   57: astore_1
      //   58: ldc 49
      //   60: new 51	java/lang/StringBuilder
      //   63: dup
      //   64: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   67: ldc 54
      //   69: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   72: aload_1
      //   73: invokevirtual 98	java/lang/IllegalAccessException:getMessage	()Ljava/lang/String;
      //   76: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   79: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   82: invokestatic 70	o/qv:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   85: goto -50 -> 35
      //   88: astore_1
      //   89: ldc 49
      //   91: new 51	java/lang/StringBuilder
      //   94: dup
      //   95: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   98: ldc 54
      //   100: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   103: aload_1
      //   104: invokevirtual 99	java/lang/reflect/InvocationTargetException:getMessage	()Ljava/lang/String;
      //   107: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   110: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   113: invokestatic 70	o/qv:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   116: goto -81 -> 35
      //   119: astore_1
      //   120: aload_1
      //   121: invokevirtual 102	java/lang/InterruptedException:printStackTrace	()V
      //   124: goto -74 -> 50
      //   127: astore_1
      //   128: aload_0
      //   129: monitorexit
      //   130: aload_1
      //   131: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	132	0	this	a
      //   0	132	1	paramString	String
      //   41	2	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   0	35	57	java/lang/IllegalAccessException
      //   0	35	88	java/lang/reflect/InvocationTargetException
      //   46	50	119	java/lang/InterruptedException
      //   37	42	127	finally
      //   46	50	127	finally
      //   50	52	127	finally
      //   120	124	127	finally
      //   128	130	127	finally
    }
  }
  
  static class b
  {
    private final String a;
    private final String b;
    
    b(String paramString1, String paramString2)
    {
      this.a = paramString1;
      this.b = paramString2;
    }
  }
}
