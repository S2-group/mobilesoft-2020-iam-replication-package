import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.lm.powersecurity.app.ApplicationEx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class vt
{
  private static final List<String> b = Arrays.asList(new String[] { "com.android.settings" });
  private static vt c;
  public List<String> a;
  private final Object d = new Object();
  private Context e = ApplicationEx.getInstance().getApplicationContext();
  private AtomicBoolean f = new AtomicBoolean(false);
  private AtomicBoolean g = new AtomicBoolean(false);
  private HashMap<String, vt.a> h;
  private Object i = new Object();
  private boolean j = false;
  private Set<String> k = new HashSet();
  private AtomicBoolean l = new AtomicBoolean(false);
  private String m = "";
  private AtomicBoolean n = new AtomicBoolean(false);
  private String o;
  private AtomicBoolean p = new AtomicBoolean(false);
  private ArrayList<aaf> q = new ArrayList();
  private HashMap<String, Long> r = new HashMap();
  private vt.e s = new vt.e();
  private AtomicBoolean t = new AtomicBoolean(false);
  private int u = 5;
  private AtomicBoolean v = new AtomicBoolean(false);
  private wv w = wu.getInstance().register();
  
  private vt()
  {
    this.w.register(yc.class, new wu.b()
    {
      public void onEventAsync(yc paramAnonymousYc)
      {
        vt.this.onEventAsync(paramAnonymousYc);
      }
    });
    this.w.register(yx.class, new wu.b()
    {
      public void onEventBackgroundThread(yx paramAnonymousYx)
      {
        vt.this.onEventBackgroundThread(paramAnonymousYx);
      }
    });
    r();
  }
  
  private int a(String paramString)
  {
    if (abt.isPackageStopped(paramString)) {
      return -1;
    }
    return 1;
  }
  
  private void a()
  {
    ArrayList localArrayList1 = new ArrayList();
    synchronized (this.i)
    {
      Iterator localIterator = this.h.entrySet().iterator();
      if (localIterator.hasNext())
      {
        vt.a localA = (vt.a)((Map.Entry)localIterator.next()).getValue();
        localArrayList1.add(new aaf(localA.a, localA.d.d, localA.e.d));
      }
    }
    c(localArrayList2);
    k();
    j();
    synchronized (this.q)
    {
      if (this.v.get())
      {
        this.q.clear();
        this.v.set(false);
      }
      return;
    }
  }
  
  private void a(long paramLong1, long paramLong2, long paramLong3)
  {
    synchronized (this.d)
    {
      this.s.updateRxBytes(paramLong1, paramLong3);
      this.s.updateTxBytes(paramLong2, paramLong3);
      return;
    }
  }
  
  private void a(String paramString, long paramLong1, long paramLong2, long paramLong3)
  {
    synchronized (this.i)
    {
      vt.a localA2 = (vt.a)this.h.get(paramString);
      vt.a localA1 = localA2;
      if (localA2 == null) {
        localA1 = new vt.a(paramString);
      }
      localA1.updateRxBytes(paramLong1, paramLong3);
      localA1.updateTxBytes(paramLong2, paramLong3);
      this.h.put(paramString, localA1);
      return;
    }
  }
  
  private void a(ArrayList<aaf> paramArrayList)
  {
    ??? = new ArrayList();
    if (this.a == null) {}
    for (;;)
    {
      aaf localAaf1;
      int i1;
      synchronized (this.q)
      {
        paramArrayList = paramArrayList.iterator();
        if (!paramArrayList.hasNext()) {
          break label190;
        }
        localAaf1 = (aaf)paramArrayList.next();
        Iterator localIterator = this.q.iterator();
        if (!localIterator.hasNext()) {
          break label193;
        }
        aaf localAaf2 = (aaf)localIterator.next();
        if (!localAaf2.a.equals(localAaf1.a)) {
          continue;
        }
        localAaf2.b = localAaf1.b;
        localAaf2.c = localAaf1.c;
        i1 = 1;
        if (i1 != 0) {
          continue;
        }
        this.q.add(localAaf1);
      }
      paramArrayList = paramArrayList.iterator();
      for (;;)
      {
        if (paramArrayList.hasNext())
        {
          localAaf1 = (aaf)paramArrayList.next();
          if (!this.a.contains(localAaf1.a))
          {
            ???.add(localAaf1);
            continue;
            label190:
            return;
            label193:
            i1 = 0;
            break;
          }
        }
      }
      paramArrayList = ???;
    }
  }
  
  private void b()
  {
    ArrayList localArrayList2 = new ArrayList();
    Object localObject2;
    synchronized (this.q)
    {
      localObject2 = this.q.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        aaf localAaf = (aaf)((Iterator)localObject2).next();
        if ((abt.isPackageStopped(localAaf.a)) || (this.r.containsKey(localAaf.a))) {
          localArrayList2.add(localAaf);
        }
      }
    }
    Iterator localIterator = localObject1.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localObject2 = (aaf)localIterator.next();
        if (this.q.size() > this.u) {}
      }
      else
      {
        return;
      }
      this.q.remove(localObject2);
      if (((aaf)localObject2).a.equals(this.o)) {
        this.n.set(true);
      }
    }
  }
  
  private void b(String paramString) {}
  
  private void b(ArrayList<aaf> paramArrayList)
  {
    a(paramArrayList);
    c();
    d();
    b();
    g();
    p();
    f();
  }
  
  private void c()
  {
    for (;;)
    {
      String str;
      synchronized (this.q)
      {
        if (this.j)
        {
          Iterator localIterator1 = this.k.iterator();
          if (localIterator1.hasNext())
          {
            str = (String)localIterator1.next();
            Iterator localIterator3 = this.q.iterator();
            if (!localIterator3.hasNext()) {
              break label214;
            }
            aaf localAaf = (aaf)localIterator3.next();
            if (!localAaf.a.equals(str)) {
              continue;
            }
            i1 = this.q.indexOf(localAaf);
            if (i1 < 0) {
              continue;
            }
            this.q.remove(i1);
          }
        }
      }
      synchronized (this.i)
      {
        if (this.j)
        {
          Iterator localIterator2 = this.k.iterator();
          if (localIterator2.hasNext())
          {
            str = (String)localIterator2.next();
            this.h.remove(str);
          }
        }
      }
      this.j = false;
      synchronized (this.k)
      {
        this.k.clear();
        return;
      }
      label214:
      int i1 = -1;
    }
  }
  
  private void c(ArrayList<aaf> arg1)
  {
    if (???.isEmpty()) {
      return;
    }
    h();
    b(???);
    synchronized (this.q)
    {
      if ((!this.q.isEmpty()) && (this.f.get())) {
        aij.getDefault().post(new yy(this.q));
      }
      return;
    }
  }
  
  private void d()
  {
    synchronized (this.q)
    {
      vt.d localD = new vt.d();
      Collections.sort(this.q, localD);
      Collections.reverse(this.q);
      return;
    }
  }
  
  private void e()
  {
    synchronized (this.q)
    {
      if (this.q.size() > this.u) {
        this.q.subList(this.u, this.q.size()).clear();
      }
      return;
    }
  }
  
  private void f()
  {
    d();
    e();
  }
  
  private void g()
  {
    synchronized (this.q)
    {
      if (this.q.size() == 0)
      {
        this.q.addAll(q());
        this.v.set(true);
      }
      return;
    }
  }
  
  private void h()
  {
    synchronized (this.r)
    {
      Object localObject1 = this.r.keySet();
      long l1 = System.currentTimeMillis();
      localObject1 = ((Set)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        String str = (String)((Iterator)localObject1).next();
        if (l1 - ((Long)this.r.get(str)).longValue() > 120000L) {
          this.r.remove(str);
        }
      }
    }
  }
  
  private long[] i()
  {
    Iterator localIterator = this.q.iterator();
    long l1 = 0L;
    long l2 = 0L;
    while (localIterator.hasNext())
    {
      aaf localAaf = (aaf)localIterator.next();
      l2 += localAaf.b;
      l1 = localAaf.c + l1;
    }
    return new long[] { l2, l1 };
  }
  
  public static vt instance()
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new vt();
      }
      return c;
    }
    finally {}
  }
  
  private void j() {}
  
  private void k()
  {
    this.m = "";
    if (this.s.rxSpeed() == 0L) {
      return;
    }
    for (;;)
    {
      synchronized (this.q)
      {
        if (this.q.isEmpty())
        {
          String str1 = "";
          this.m = str1;
          return;
        }
      }
      String str2 = ((aaf)this.q.get(0)).a;
    }
  }
  
  private void l()
  {
    vs.instance().stop();
  }
  
  private void m()
  {
    if (this.g.get()) {
      return;
    }
    this.h = new HashMap();
    vs.instance().start();
    this.g.set(true);
  }
  
  private void n()
  {
    if (!this.g.get()) {
      return;
    }
    l();
    if (this.h != null)
    {
      this.h.clear();
      this.h = null;
    }
    this.g.set(false);
  }
  
  private void o()
  {
    if ((!this.f.get()) && (!this.l.get())) {
      n();
    }
  }
  
  private void p()
  {
    synchronized (this.q)
    {
      long[] arrayOfLong = i();
      if (this.p.get()) {
        return;
      }
      if (!ach.isConnected(this.e)) {
        return;
      }
    }
    long l3 = this.s.rxSpeed() - localObject1[0];
    long l4 = this.s.txSpeed() - localObject1[1];
    Object localObject2 = null;
    Object localObject3 = null;
    if (this.n.get())
    {
      i2 = (int)(this.q.size() * Math.random());
      this.n.set(false);
      if (this.q.size() <= i2) {
        break label399;
      }
      localObject2 = (aaf)this.q.get(i2);
      if (localObject2 == null) {
        break label386;
      }
      if (!abt.isPackageStopped(((aaf)localObject2).a)) {
        break label392;
      }
    }
    label160:
    while (this.q.size() <= 0)
    {
      int i2;
      if ((!this.t.get()) && (localObject3 != null))
      {
        this.o = ((aaf)localObject3).a;
        if (l3 > 0L) {
          ((aaf)localObject3).b += l3;
        }
        if (l4 > 0L) {
          ((aaf)localObject3).c += l4;
        }
      }
      return;
      int i3 = this.q.size();
      for (i1 = (i2 + 1) % i3; i1 != i2; i1 = (i1 + 1) % i3)
      {
        localObject3 = (aaf)this.q.get(i1);
        localObject2 = localObject3;
        if (!abt.isPackageStopped(((aaf)localObject3).a)) {
          break label392;
        }
      }
    }
    long l1 = -1L;
    int i1 = 0;
    for (;;)
    {
      localObject3 = localObject2;
      if (i1 >= this.q.size()) {
        break label160;
      }
      localObject3 = (aaf)this.q.get(i1);
      if (!abt.isPackageStopped(((aaf)localObject3).a))
      {
        long l2 = ((aaf)localObject3).b;
        long l5 = ((aaf)localObject3).c;
        l2 += l5;
        if (l2 > l1)
        {
          localObject2 = localObject3;
          l1 = l2;
        }
        else
        {
          break label405;
          label386:
          localObject2 = null;
          label392:
          localObject3 = localObject2;
          break label160;
          label399:
          localObject2 = null;
          break;
        }
      }
      label405:
      i1 += 1;
    }
  }
  
  private List<aaf> q()
  {
    Object localObject3 = this.e.getPackageManager();
    Object localObject2 = new ArrayList();
    Object localObject1 = new ArrayList();
    for (;;)
    {
      try
      {
        localObject3 = ((PackageManager)localObject3).getInstalledPackages(4096);
        localObject2 = ((List)localObject3).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if ((((PackageInfo)localObject3).applicationInfo.flags & 0x800000) > 0)
          {
            i1 = 1;
            if ((i1 != 0) && (!abt.isPackageStopped(((PackageInfo)localObject3).packageName)))
            {
              arrayOfString = ((PackageInfo)localObject3).requestedPermissions;
              if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
                break label872;
              }
              i2 = arrayOfString.length;
              i1 = 0;
              if (i1 >= i2) {
                break label872;
              }
              if (!"android.permission.INTERNET".equals(arrayOfString[i1])) {
                continue;
              }
              i1 = 1;
              if (i1 == 0) {
                continue;
              }
              ((List)localObject1).add(((PackageInfo)localObject3).packageName);
            }
          }
          else
          {
            i1 = 0;
            continue;
          }
          continue;
          i1 += 1;
          continue;
        }
        if (((List)localObject1).size() == 0) {
          ((List)localObject1).addAll(uy.getInstance().getPkgNameOfInstalledApp(true));
        }
        localObject2 = this.a.iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          if (!((List)localObject1).contains(localObject3)) {
            continue;
          }
          ((List)localObject1).remove(localObject3);
          continue;
        }
        localObject2 = new ArrayList();
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          ((List)localObject2).add(new aaf((String)((Iterator)localObject1).next(), 0L, 0L));
          continue;
        }
        return localObject2;
      }
      catch (Exception localException)
      {
        localException = localException;
        localObject2 = ((List)localObject2).iterator();
        Object localObject4;
        if (((Iterator)localObject2).hasNext())
        {
          localObject4 = (PackageInfo)((Iterator)localObject2).next();
          if ((((PackageInfo)localObject4).applicationInfo.flags & 0x800000) > 0)
          {
            i1 = 1;
            if ((i1 != 0) && (!abt.isPackageStopped(((PackageInfo)localObject4).packageName)))
            {
              arrayOfString = ((PackageInfo)localObject4).requestedPermissions;
              if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
                break label867;
              }
              i2 = arrayOfString.length;
              i1 = 0;
              if (i1 >= i2) {
                break label867;
              }
              if (!"android.permission.INTERNET".equals(arrayOfString[i1])) {
                continue;
              }
              i1 = 1;
              if (i1 == 0) {
                continue;
              }
              ((List)localObject1).add(((PackageInfo)localObject4).packageName);
            }
          }
          else
          {
            i1 = 0;
            continue;
          }
          continue;
          i1 += 1;
          continue;
        }
        if (((List)localObject1).size() == 0) {
          ((List)localObject1).addAll(uy.getInstance().getPkgNameOfInstalledApp(true));
        }
        localObject2 = this.a.iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject4 = (String)((Iterator)localObject2).next();
          if (!((List)localObject1).contains(localObject4)) {
            continue;
          }
          ((List)localObject1).remove(localObject4);
          continue;
        }
        localObject2 = new ArrayList();
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          ((List)localObject2).add(new aaf((String)((Iterator)localObject1).next(), 0L, 0L));
          continue;
        }
        return localObject2;
      }
      finally
      {
        String[] arrayOfString;
        int i2;
        localObject5 = finally;
        localObject2 = ((List)localObject2).iterator();
        Object localObject6;
        if (((Iterator)localObject2).hasNext())
        {
          localObject6 = (PackageInfo)((Iterator)localObject2).next();
          if ((((PackageInfo)localObject6).applicationInfo.flags & 0x800000) > 0)
          {
            i1 = 1;
            if ((i1 != 0) && (!abt.isPackageStopped(((PackageInfo)localObject6).packageName)))
            {
              arrayOfString = ((PackageInfo)localObject6).requestedPermissions;
              if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
                break label862;
              }
              i2 = arrayOfString.length;
              i1 = 0;
              if (i1 >= i2) {
                break label862;
              }
              if (!"android.permission.INTERNET".equals(arrayOfString[i1])) {
                continue;
              }
              i1 = 1;
              if (i1 == 0) {
                continue;
              }
              ((List)localObject1).add(((PackageInfo)localObject6).packageName);
            }
          }
          else
          {
            i1 = 0;
            continue;
          }
          continue;
          i1 += 1;
          continue;
        }
        else
        {
          if (((List)localObject1).size() == 0) {
            ((List)localObject1).addAll(uy.getInstance().getPkgNameOfInstalledApp(true));
          }
          localObject2 = this.a.iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localObject6 = (String)((Iterator)localObject2).next();
            if (!((List)localObject1).contains(localObject6)) {
              continue;
            }
            ((List)localObject1).remove(localObject6);
            continue;
          }
          localObject2 = new ArrayList();
          localObject1 = ((List)localObject1).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            ((List)localObject2).add(new aaf((String)((Iterator)localObject1).next(), 0L, 0L));
            continue;
          }
          return localObject2;
        }
      }
      label862:
      int i1 = 0;
      continue;
      label867:
      i1 = 0;
      continue;
      label872:
      i1 = 0;
    }
  }
  
  private void r()
  {
    try
    {
      if (this.a == null) {
        this.a = new ArrayList();
      }
      this.a.addAll(sw.getFullFilterList());
      this.a.removeAll(sv.a);
      this.a.addAll(b);
      return;
    }
    finally {}
  }
  
  protected void finalize()
    throws Throwable
  {
    super.finalize();
    if (this.w != null) {
      this.w.onClose();
    }
  }
  
  public String getNetConsumerToShow()
  {
    return this.m;
  }
  
  public ArrayList<aaf> getShowList()
  {
    synchronized (this.q)
    {
      ArrayList localArrayList1 = new ArrayList();
      if (this.q != null)
      {
        localArrayList1 = new ArrayList(Arrays.asList(new Object[this.q.size()]));
        Collections.copy(localArrayList1, this.q);
      }
      return localArrayList1;
    }
  }
  
  public long[] getTotalNetworkSpeedLong()
  {
    if (this.p.get()) {}
    for (;;)
    {
      synchronized (this.q)
      {
        long[] arrayOfLong = i();
        l2 = arrayOfLong[0];
        l1 = arrayOfLong[1];
        return new long[] { l2, l1 };
      }
      long l2 = this.s.rxSpeed();
      long l1 = this.s.txSpeed();
    }
  }
  
  public void onEventAsync(yc paramYc)
  {
    if (TextUtils.isEmpty(paramYc.a)) {
      return;
    }
    synchronized (this.k)
    {
      this.k.add(paramYc.a);
      this.j = true;
      b("remove: " + paramYc.a);
      return;
    }
  }
  
  public void onEventBackgroundThread(yx paramYx)
  {
    if (!this.g.get()) {
      return;
    }
    paramYx = paramYx.a;
    a(paramYx.a, paramYx.b, paramYx.d);
    Iterator localIterator = paramYx.c.iterator();
    while (localIterator.hasNext())
    {
      aad localAad = (aad)localIterator.next();
      a(localAad.a, localAad.b, localAad.c, paramYx.d);
    }
    a();
  }
  
  public void startMonitor()
  {
    if (!wi.getInstance().isFlowStatisticsEnable()) {
      return;
    }
    synchronized (this.f)
    {
      if (this.f.get()) {
        return;
      }
    }
    m();
    this.f.set(true);
  }
  
  public void stopMonitor()
  {
    ud.d("network_sample", "NetworkSpeedMonitor - stopMonitor.");
    synchronized (this.f)
    {
      if (!this.f.get()) {
        return;
      }
      this.f.set(false);
      o();
      return;
    }
  }
  
  class a
    extends vt.b
  {
    String a;
    int b;
    
    public a(String paramString)
    {
      super();
      this.a = paramString;
      this.b = 0;
    }
  }
  
  class b
  {
    vt.c d = new vt.c(vt.this);
    vt.c e = new vt.c(vt.this);
    
    public b() {}
    
    public long rxSpeed()
    {
      return this.d.d;
    }
    
    public long txSpeed()
    {
      return this.e.d;
    }
    
    public void updateRxBytes(long paramLong1, long paramLong2)
    {
      this.d.a = this.d.b;
      this.d.b = paramLong1;
      if (this.d.a == 0L) {
        this.d.a = this.d.b;
      }
      this.d.c = (this.d.b - this.d.a);
      this.d.d = adq.bytesPerSecondSpeed(this.d.c, paramLong2);
    }
    
    public void updateTxBytes(long paramLong1, long paramLong2)
    {
      this.e.a = this.e.b;
      this.e.b = paramLong1;
      if (this.e.a == 0L) {
        this.e.a = this.e.b;
      }
      this.e.c = (this.e.b - this.e.a);
      this.e.d = adq.bytesPerSecondSpeed(this.e.c, paramLong2);
    }
  }
  
  class c
  {
    long a = 0L;
    long b = 0L;
    long c = 0L;
    long d = 0L;
    
    public c() {}
  }
  
  class d
    implements Comparator<aaf>
  {
    d() {}
    
    public int compare(aaf paramAaf1, aaf paramAaf2)
    {
      long l1 = paramAaf1.b + paramAaf1.c;
      long l2 = paramAaf2.b + paramAaf2.c;
      int i = vt.a(vt.this, paramAaf1.a);
      int j = vt.a(vt.this, paramAaf2.a);
      if (i != j) {
        if (i <= j) {}
      }
      while (l1 > l2)
      {
        return 1;
        return -1;
      }
      if (l1 < l2) {
        return -1;
      }
      paramAaf1 = abt.getNameByPackage(paramAaf1.a);
      paramAaf2 = abt.getNameByPackage(paramAaf2.a);
      if ((!TextUtils.isEmpty(paramAaf1)) && (!TextUtils.isEmpty(paramAaf2))) {
        return paramAaf2.compareTo(paramAaf1);
      }
      return 0;
    }
  }
  
  class e
    extends vt.b
  {
    public e()
    {
      super();
    }
  }
}
