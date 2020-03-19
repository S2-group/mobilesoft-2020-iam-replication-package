package o;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.net.Uri;
import android.os.TransactionTooLargeException;
import android.util.SparseArray;
import com.teamviewer.corelib.logging.Logging;
import com.teamviewer.teamviewerlib.event.EventHub;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

class bmj
  extends ced<cdt>
{
  public static final String a = cbt.a().getPackageName();
  long b = 0L;
  long c = 0L;
  long d = 0L;
  long e = 0L;
  long f = 0L;
  long g = 0L;
  long h = 0L;
  long i = 0L;
  long j = 0L;
  long k = 0L;
  AtomicBoolean l = new AtomicBoolean(false);
  private Map<String, PackageStats> r = new ConcurrentHashMap();
  private final List<bmq> s = new LinkedList();
  private final AtomicInteger t = new AtomicInteger(1);
  private final SparseArray<bmq> u = new SparseArray();
  private final int v = hashCode();
  private bjp w = new bml(this);
  private byr x = new bmm(this);
  
  public bmj()
  {
    super(cdq.k, 3L, g(), cdt.class);
  }
  
  private PackageStats a(PackageManager paramPackageManager, String paramString)
  {
    PackageStats localPackageStats = (PackageStats)this.r.get(paramString);
    if (localPackageStats != null) {
      return localPackageStats;
    }
    return new bmo(this, paramPackageManager).a(paramString);
  }
  
  private static String a(List<bmq> paramList, String paramString)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      bmq localBmq = (bmq)paramList.next();
      if (paramString.equals(bmq.b(localBmq))) {
        return bmq.a(localBmq);
      }
    }
    return null;
  }
  
  private static bmq a(SparseArray<bmq> paramSparseArray, String paramString)
  {
    int m = 0;
    while (m < paramSparseArray.size())
    {
      if (paramString.equals(bmq.b((bmq)paramSparseArray.valueAt(m)))) {
        return (bmq)paramSparseArray.valueAt(m);
      }
      m += 1;
    }
    return null;
  }
  
  private bmw a(PackageManager paramPackageManager, PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    bmw localBmw = new bmw(paramPackageInfo.packageName);
    if (a(cdt.d)) {
      localBmw.a(cdt.d, Integer.valueOf(paramPackageInfo.versionCode));
    }
    if (a(cdt.e)) {
      localBmw.a(cdt.e, paramPackageInfo.versionName);
    }
    if (a(cdt.b)) {
      localBmw.a(cdt.b, Long.valueOf(paramPackageInfo.firstInstallTime));
    }
    if (paramPackageInfo.applicationInfo != null)
    {
      Object localObject;
      if (a(cdt.a))
      {
        localObject = paramPackageManager.getApplicationLabel(paramPackageInfo.applicationInfo).toString();
        if (localObject != null) {
          localBmw.a(cdt.a, localObject);
        }
      }
      if (a(cdt.c))
      {
        localObject = new File(paramPackageInfo.applicationInfo.sourceDir);
        if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
          localBmw.a(cdt.c, Long.valueOf(((File)localObject).lastModified()));
        }
      }
    }
    if (paramBoolean)
    {
      paramPackageManager = a(paramPackageManager, paramPackageInfo.packageName);
      if (paramPackageManager == null) {
        break label352;
      }
      long l1 = paramPackageManager.cacheSize + paramPackageManager.externalCacheSize;
      long l2 = paramPackageManager.codeSize + paramPackageManager.externalCodeSize;
      long l3 = paramPackageManager.dataSize + paramPackageManager.externalDataSize + paramPackageManager.externalMediaSize + paramPackageManager.externalObbSize;
      if (a(cdt.i)) {
        localBmw.a(cdt.i, Long.valueOf(l1));
      }
      if (a(cdt.g)) {
        localBmw.a(cdt.g, Long.valueOf(l2));
      }
      if (a(cdt.h)) {
        localBmw.a(cdt.h, Long.valueOf(l3));
      }
      if (a(cdt.f)) {
        localBmw.a(cdt.f, Long.valueOf(l1 + l2 + l3));
      }
    }
    return localBmw;
    label352:
    Logging.d("ModuleApps", "getAppInfo: could not get package stats ");
    return localBmw;
  }
  
  private bmw a(String paramString)
  {
    PackageManager localPackageManager = cbt.a().getPackageManager();
    if (localPackageManager == null)
    {
      Logging.d("ModuleApps", "getAppInfo(): PackageManager is null");
      return null;
    }
    try
    {
      paramString = a(localPackageManager, localPackageManager.getPackageInfo(paramString, 0), true);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      Logging.d("ModuleApps", "getAppInfo(): package not found");
      return null;
    }
    catch (RuntimeException paramString)
    {
      if ((paramString.getCause() instanceof TransactionTooLargeException))
      {
        Logging.c("ModuleApps", "PackageInfo query result was over the IPC transaction limit! (1MB)");
        return null;
      }
      Logging.d("ModuleApps", paramString.getMessage());
    }
    return null;
  }
  
  private void a(int paramInt, cdr paramCdr)
  {
    for (;;)
    {
      synchronized (this.u)
      {
        bmq localBmq = (bmq)this.u.get(paramInt);
        if (localBmq == null) {
          break;
        }
        if (cdr.c.equals(paramCdr))
        {
          paramCdr = btz.h;
          b(bty.c, paramCdr, null, bmq.b(localBmq), bmq.a(localBmq));
          return;
        }
      }
      paramCdr = btz.a;
    }
    Logging.d("ModuleApps", "Cannot process app remove canceled: No pending removals.");
  }
  
  private void a(String paramString, blz paramBlz)
  {
    bmv localBmv = new bmv();
    switch (bmn.b[paramBlz.ordinal()])
    {
    default: 
      Logging.c("ModuleApps", "Unknown enum value!");
    }
    for (;;)
    {
      try
      {
        a(paramBlz, localBmv.c().toString());
        return;
      }
      catch (JSONException paramString)
      {
        Logging.d("ModuleApps", "onAppEvent(): cannot get JSON string: " + paramString.getMessage());
      }
      ??? = a(this.s, paramString);
      if (??? != null)
      {
        a(cef.a, bii.tv_rs_event_app_installed, new Object[] { paramString });
        a(bty.b, null, null, b(paramString), (String)???);
        this.s.remove(paramString);
      }
      paramString = a(paramString);
      if (paramString == null)
      {
        Logging.d("ModuleApps", "onAppEvent(): appInfo is null");
        return;
      }
      localBmv.a(paramString);
      continue;
      synchronized (this.u)
      {
        bmq localBmq = a(this.u, paramString);
        if (localBmq != null)
        {
          a(cef.a, bii.tv_rs_event_app_removed, new Object[] { paramString });
          b(bty.b, null, null, paramString, bmq.a(localBmq));
          this.u.remove(this.u.indexOfValue(localBmq));
          localBmv.a(new bmw(paramString, 1));
        }
        else
        {
          Logging.c("ModuleApps", "Removal repsonse not sent. No request pending.");
        }
      }
    }
  }
  
  private void a(blz paramBlz, String paramString)
  {
    btt localBtt = btu.a(btw.y);
    switch (bmn.b[paramBlz.ordinal()])
    {
    default: 
      localBtt.b(bua.d, paramString);
    }
    for (;;)
    {
      a(localBtt, n());
      return;
      localBtt.b(bua.a, paramString);
      continue;
      localBtt.b(bua.b, paramString);
      continue;
      localBtt.b(bua.c, paramString);
    }
  }
  
  private void a(bty paramBty, btz paramBtz, String paramString1, String paramString2)
  {
    btt localBtt = btu.a(btw.A);
    localBtt.a(bum.a, paramBty.a());
    if (paramBtz != null) {
      localBtt.a(bum.b, paramBtz.a());
    }
    if (paramString1 != null) {
      localBtt.a(bum.c, paramString1);
    }
    if (paramString2 != null) {
      localBtt.b(bum.d, paramString2);
    }
    a(localBtt, n());
  }
  
  private void a(bty paramBty, btz paramBtz, String paramString1, String paramString2, String paramString3)
  {
    btt localBtt = btu.a(btw.E);
    localBtt.a(bus.a, paramBty.a());
    if (paramBtz != null) {
      localBtt.a(bus.b, paramBtz.a());
    }
    if (paramString1 != null) {
      localBtt.a(bus.c, paramString1);
    }
    if (paramString2 != null) {
      localBtt.b(bus.d, paramString2);
    }
    if (paramString3 != null) {
      localBtt.a(bus.e, paramString3);
    }
    a(localBtt, n());
  }
  
  private void a(bty paramBty, btz paramBtz, String paramString1, String paramString2, btx paramBtx, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    btt localBtt = btu.a(btw.C);
    localBtt.a(bul.a, paramBty.a());
    if (paramBtz != null) {
      localBtt.a(bul.b, paramBtz.a());
    }
    if (paramString1 != null) {
      localBtt.a(bul.c, paramString1);
    }
    if (paramString2 != null) {
      localBtt.a(bul.d, paramString2);
    }
    if (paramBtx != null) {
      localBtt.a(bul.e, paramBtx.a());
    }
    if (paramArrayOfByte != null) {
      localBtt.a(bul.f, paramArrayOfByte);
    }
    if (paramInt1 > 0) {
      localBtt.a(bul.g, paramInt1);
    }
    if (paramInt2 > 0) {
      localBtt.a(bul.h, paramInt2);
    }
    a(localBtt, n());
  }
  
  private String b(String paramString)
  {
    bmv localBmv = new bmv();
    localBmv.a(new bmw(paramString, 1));
    try
    {
      paramString = localBmv.c().toString();
      return paramString;
    }
    catch (JSONException paramString)
    {
      Logging.d("ModuleApps", "getAppInfoJsonString(): cannot get app info JSON string: " + paramString);
    }
    return null;
  }
  
  private void b(btt paramBtt)
  {
    if (!a(cdt.j))
    {
      Logging.d("ModuleApps", "handleRsCmdGetIcon(): received command but feature not negotiated");
      a(bty.c, btz.c, "feature not negotiated", null, null, null, -1, -1);
      return;
    }
    paramBtt = paramBtt.e(buk.a);
    if (paramBtt.b <= 0)
    {
      Logging.d("ModuleApps", "handleRsCmdGetIcon(): key param missing");
      a(bty.c, btz.b, "missing parameter", null, null, null, -1, -1);
      return;
    }
    paramBtt = (String)paramBtt.c;
    Object localObject = cbt.a().getPackageManager();
    if (localObject == null)
    {
      Logging.d("ModuleApps", "handleRsCmdGetIcon(): PackageManager is null");
      a(bty.c, null, null, paramBtt, null, null, -1, -1);
      return;
    }
    localObject = bne.a((PackageManager)localObject, paramBtt, 36, 36);
    if (localObject != null)
    {
      a(bty.b, null, null, paramBtt, btx.c, ((bnf)localObject).c, ((bnf)localObject).a, ((bnf)localObject).b);
      return;
    }
    a(bty.c, btz.e, "package not found", paramBtt, null, null, -1, -1);
  }
  
  private void b(bty paramBty, btz paramBtz, String paramString1, String paramString2, String paramString3)
  {
    btt localBtt = btu.a(btw.G);
    localBtt.a(bux.a, paramBty.a());
    if (paramBtz != null) {
      localBtt.a(bux.b, paramBtz.a());
    }
    if (paramString1 != null) {
      localBtt.a(bux.c, paramString1);
    }
    if (paramString2 != null) {
      localBtt.a(bux.d, paramString2);
    }
    if (paramString3 != null) {
      localBtt.a(bux.e, paramString3);
    }
    a(localBtt, n());
  }
  
  private void c(btt paramBtt)
  {
    if (!a(cdt.k))
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): received command but feature not negotiated");
      a(bty.c, btz.c, "feature not negotiated", null, null);
      return;
    }
    Object localObject1 = paramBtt.e(bur.b);
    if (((bxp)localObject1).b <= 0)
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): uuid param missing");
      a(bty.c, btz.b, "missing parameter", null, null);
      return;
    }
    localObject1 = (String)((bxp)localObject1).c;
    paramBtt = paramBtt.e(bur.a);
    if (paramBtt.b <= 0)
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): uri param missing");
      a(bty.c, btz.b, "missing parameter", null, (String)localObject1);
      return;
    }
    paramBtt = Uri.parse((String)paramBtt.c);
    if (paramBtt == null)
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): invalid uri param");
      a(bty.c, btz.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    Object localObject2 = paramBtt.getScheme();
    if (localObject2 == null)
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): uri scheme is null");
      a(bty.c, btz.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    if (!((String)localObject2).equals("file"))
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): invalid uri scheme: " + (String)localObject2);
      a(bty.c, btz.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    localObject2 = paramBtt.getSchemeSpecificPart();
    if (localObject2 == null)
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): path to apk is null");
      a(bty.c, btz.c, "invalid parameter", null, (String)localObject1);
      return;
    }
    if (!new File((String)localObject2).exists())
    {
      Logging.d("ModuleApps", "handleRsCmdInstallApp(): apk file does not exist: " + (String)localObject2);
      a(bty.c, btz.d, "file not found", null, (String)localObject1);
      return;
    }
    try
    {
      PackageManager localPackageManager = cbt.a().getPackageManager();
      if (localPackageManager == null) {
        break label451;
      }
      localObject2 = localPackageManager.getPackageInfo((String)localObject2, 0);
      if (localObject2 != null)
      {
        localObject2 = ((PackageInfo)localObject2).packageName;
        this.s.add(new bmq((String)localObject2, (String)localObject1));
      }
    }
    catch (PackageManager.NameNotFoundException paramBtt)
    {
      for (;;)
      {
        Logging.d("ModuleApps", "handleRsCmdInstallApp(): package not found");
        a(bty.c, btz.e, "package not found", null, (String)localObject1);
        return;
        Logging.d("ModuleApps", "handleRsCmdInstallApp(): PackageManager is null");
      }
    }
    catch (RuntimeException paramBtt)
    {
      if (!(paramBtt.getCause() instanceof TransactionTooLargeException)) {
        break label484;
      }
      Logging.c("ModuleApps", "PackageInfo query result was over the IPC transaction limit! (1MB)");
      return;
      Logging.d("ModuleApps", paramBtt.getMessage());
    }
    localObject2 = new Intent("android.intent.action.VIEW");
    ((Intent)localObject2).setDataAndType(paramBtt, "application/vnd.android.package-archive");
    ((Intent)localObject2).setFlags(268435456);
    cbt.a((Intent)localObject2);
    return;
    label451:
    label484:
    return;
  }
  
  private void d(btt paramBtt)
  {
    if (!a(cdt.l))
    {
      Logging.d("ModuleApps", "handleRsCmdRemoveApp(): received command but feature not negotiated");
      b(bty.c, btz.c, "feature not negotiated", null, null);
      return;
    }
    Object localObject = paramBtt.e(bvg.b);
    if (((bxp)localObject).b <= 0)
    {
      Logging.d("ModuleApps", "handleRsCmdRemoveApp(): uuid param missing");
      a(bty.c, btz.b, "missing parameter", null, null);
      return;
    }
    localObject = (String)((bxp)localObject).c;
    paramBtt = paramBtt.e(buw.a);
    if (paramBtt.b <= 0)
    {
      Logging.d("ModuleApps", "handleRsCmdRemoveApp(): key param missing");
      b(bty.c, btz.b, "missing parameter", null, (String)localObject);
      return;
    }
    paramBtt = (String)paramBtt.c;
    if (paramBtt.equals(a))
    {
      Logging.c("ModuleApps", "We don't want to remove ourselves...");
      b(bty.c, btz.g, null, paramBtt, (String)localObject);
      return;
    }
    int m = this.t.getAndIncrement();
    synchronized (this.u)
    {
      this.u.put(m, new bmq(paramBtt, (String)localObject));
      localObject = new byu();
      ((byu)localObject).a(byt.am, m);
      ((byu)localObject).a(byt.an, paramBtt);
      EventHub.a().a(bys.aW, (byu)localObject);
      return;
    }
  }
  
  private void e()
  {
    try
    {
      Object localObject = f();
      if (localObject == null)
      {
        Logging.d("ModuleApps", "handleRsCmdGetInstalledApps(): installedApps is null");
        a(bty.c, null, null, null);
        return;
      }
      localObject = ((bmv)localObject).c();
      a(bty.b, null, null, ((JSONObject)localObject).toString());
      caz.b.a(new bmk(this));
      return;
    }
    catch (JSONException localJSONException)
    {
      Logging.d("ModuleApps", "handleRsCmdGetInstalledApps(): cannot get installed apps JSON string: " + localJSONException.getMessage());
      a(bty.c, null, null, null);
    }
  }
  
  private bmv f()
    throws JSONException
  {
    PackageManager localPackageManager = cbt.a().getPackageManager();
    if (localPackageManager == null)
    {
      Logging.d("ModuleApps", "getInstalledApps(): PackageManager is null");
      return null;
    }
    Object localObject = localPackageManager.getInstalledPackages(0);
    AtomicInteger localAtomicInteger = new AtomicInteger(0);
    bmv localBmv = new bmv();
    if (this.l.getAndSet(true) == true) {}
    for (boolean bool = true; localObject != null; bool = false)
    {
      localObject = ((List)localObject).iterator();
      for (int m = 0;; m = n)
      {
        n = m;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        n = m;
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          n = m + 1;
          localBmv.a(a(localPackageManager, localPackageInfo, bool));
          localAtomicInteger.incrementAndGet();
        }
      }
    }
    Logging.d("ModuleApps", "getInstalledApps(): packageList is null");
    int n = 0;
    while (localAtomicInteger.get() != n) {
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
    return localBmv;
  }
  
  private static ArrayList<cdt> g()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(cdt.a);
    localArrayList.add(cdt.c);
    localArrayList.add(cdt.d);
    localArrayList.add(cdt.e);
    localArrayList.add(cdt.f);
    localArrayList.add(cdt.g);
    localArrayList.add(cdt.h);
    localArrayList.add(cdt.i);
    localArrayList.add(cdt.j);
    localArrayList.add(cdt.k);
    localArrayList.add(cdt.l);
    localArrayList.add(cdt.b);
    return localArrayList;
  }
  
  protected boolean a()
  {
    a(ccq.r);
    return true;
  }
  
  public boolean a(btt paramBtt)
  {
    if (super.a(paramBtt)) {
      return true;
    }
    switch (bmn.a[paramBtt.i().ordinal()])
    {
    default: 
      return false;
    case 1: 
      e();
      return true;
    case 2: 
      b(paramBtt);
      return true;
    case 3: 
      c(paramBtt);
      return true;
    }
    d(paramBtt);
    return true;
  }
  
  public boolean a(bwb paramBwb)
  {
    return false;
  }
  
  protected boolean b()
  {
    this.s.clear();
    synchronized (this.u)
    {
      this.u.clear();
      EventHub.a().a(this.x, bys.aX);
      return bli.e().a(bjm.a, this.v, this.w);
    }
  }
  
  protected boolean c()
  {
    bli.e().a(this.v);
    EventHub.a().a(this.x);
    bmq localBmq;
    if (this.s.size() > 0)
    {
      localObject1 = this.s.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localBmq = (bmq)((Iterator)localObject1).next();
        a(bty.c, btz.f, null, null, bmq.a(localBmq));
      }
      this.s.clear();
    }
    Object localObject1 = this.u;
    int m = 0;
    try
    {
      while (m < this.u.size())
      {
        localBmq = (bmq)this.u.valueAt(m);
        b(bty.c, btz.f, null, bmq.b(localBmq), bmq.a(localBmq));
        m += 1;
      }
      this.u.clear();
      this.r.clear();
      return true;
    }
    finally {}
  }
}
