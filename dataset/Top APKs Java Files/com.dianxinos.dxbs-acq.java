import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class acq
{
  private static acq a;
  private static Drawable b;
  private Context c = wh.a();
  private PackageManager d = this.c.getPackageManager();
  private ConcurrentHashMap e = new ConcurrentHashMap();
  private ConcurrentHashMap f = new ConcurrentHashMap();
  private ConcurrentHashMap g = new ConcurrentHashMap();
  private String h;
  private Object i = new Object();
  private ArrayList j = new ArrayList();
  private BroadcastReceiver k = new acr(this);
  
  private acq()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
    localIntentFilter.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
    this.c.registerReceiver(this.k, localIntentFilter);
    localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter.addDataScheme("package");
    this.c.registerReceiver(this.k, localIntentFilter);
  }
  
  public static acn a(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (acn localAcn1 = a().d(paramString);; localAcn1 = a().c(paramString))
    {
      acn localAcn2 = localAcn1;
      if (localAcn1 == null) {
        localAcn2 = new acn(paramString);
      }
      return localAcn2;
    }
  }
  
  public static acq a()
  {
    if (a == null)
    {
      a = new acq();
      b = wh.a().getResources().getDrawable(17301651);
    }
    return a;
  }
  
  private static acs a(acs paramAcs, String paramString1, String paramString2, Drawable paramDrawable)
  {
    acs localAcs = paramAcs;
    if (paramAcs == null) {
      localAcs = a().k(paramString1);
    }
    if (paramString2 != null) {
      localAcs.a = paramString2;
    }
    if (paramDrawable != null)
    {
      localAcs.b = new WeakReference(paramDrawable);
      localAcs.c = true;
    }
    return localAcs;
  }
  
  public static String a(acn paramAcn)
  {
    String str = paramAcn.a;
    acs localAcs = a().l(str);
    paramAcn = null;
    if (localAcs != null) {
      paramAcn = localAcs.a;
    }
    if (paramAcn == null) {
      return str;
    }
    return paramAcn;
  }
  
  private void a(Context paramContext, Intent arg2)
  {
    paramContext = ???.getStringArrayExtra("android.intent.extra.changed_package_list");
    if ((paramContext == null) || (paramContext.length == 0)) {
      return;
    }
    d();
    boolean bool = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(???.getAction());
    for (;;)
    {
      int m;
      String str;
      synchronized (this.i)
      {
        int n = paramContext.length;
        m = 0;
        if (m >= n) {
          break label115;
        }
        str = paramContext[m];
        acn localAcn = c(str);
        if (localAcn == null) {
          break label124;
        }
        localAcn.g();
        if (bool) {
          this.f.put(str, localAcn);
        }
      }
      this.f.remove(str);
      break label124;
      label115:
      a(0, null);
      return;
      label124:
      m += 1;
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    a(a().l(paramString1), paramString1, paramString2, null);
  }
  
  public static int b(String paramString)
  {
    try
    {
      int m = a().b().getApplicationEnabledSetting(paramString);
      return m;
    }
    catch (Exception paramString) {}
    return 2;
  }
  
  public static Drawable b(acn paramAcn)
  {
    String str = paramAcn.a;
    acs localAcs = a().l(str);
    Drawable localDrawable;
    Object localObject2;
    if ((localAcs != null) && (localAcs.b != null))
    {
      localDrawable = (Drawable)localAcs.b.get();
      localObject1 = localDrawable;
      if (localDrawable != null)
      {
        localObject2 = localDrawable;
        if (localAcs.c) {
          break label113;
        }
        localObject2 = localDrawable;
        if (!paramAcn.a()) {
          break label113;
        }
      }
    }
    for (Object localObject1 = localDrawable;; localObject1 = null)
    {
      paramAcn = paramAcn.c();
      localObject2 = localObject1;
      if (paramAcn != null) {}
      try
      {
        paramAcn = paramAcn.applicationInfo.loadIcon(a().b());
        localObject1 = paramAcn;
      }
      catch (Exception paramAcn)
      {
        for (;;) {}
      }
      localObject2 = localObject1;
      if (localObject1 != null)
      {
        a(localAcs, str, null, (Drawable)localObject1);
        localObject2 = localObject1;
      }
      label113:
      paramAcn = (acn)localObject2;
      if (localObject2 == null) {
        paramAcn = b;
      }
      return paramAcn;
    }
  }
  
  public static ArrayList c()
  {
    return a().a(true);
  }
  
  private void d()
  {
    try
    {
      if (this.e.size() == 0)
      {
        this.h = wg.a(this.c).toString();
        e();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static acn e(String paramString)
  {
    acn localAcn = a().c(paramString);
    if (localAcn == null) {
      throw new PackageManager.NameNotFoundException(paramString + " not found!");
    }
    return localAcn;
  }
  
  private void e()
  {
    Object localObject3 = this.d.getInstalledPackages(512);
    Object localObject2 = this.d.getInstalledPackages(8704);
    ConcurrentHashMap localConcurrentHashMap1 = new ConcurrentHashMap();
    ConcurrentHashMap localConcurrentHashMap2 = new ConcurrentHashMap();
    synchronized (this.i)
    {
      localObject3 = ((List)localObject3).iterator();
      if (((Iterator)localObject3).hasNext())
      {
        acn localAcn = new acn((PackageInfo)((Iterator)localObject3).next());
        localConcurrentHashMap1.put(localAcn.a, localAcn);
      }
    }
    localConcurrentHashMap2.putAll(localMap);
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (PackageInfo)((Iterator)localObject2).next();
      if (!localMap.containsKey(((PackageInfo)localObject3).packageName))
      {
        localObject3 = new acn((PackageInfo)localObject3);
        localMap.put(((acn)localObject3).a, localObject3);
      }
    }
    this.e = localMap;
    this.f = localConcurrentHashMap2;
  }
  
  public static acn f(String paramString)
  {
    acn localAcn = a().d(paramString);
    if (localAcn == null) {
      throw new PackageManager.NameNotFoundException(paramString + " not found!");
    }
    return localAcn;
  }
  
  private void f()
  {
    Object localObject3 = this.d.getInstalledPackages(512);
    ConcurrentHashMap localConcurrentHashMap = new ConcurrentHashMap();
    synchronized (this.i)
    {
      localObject3 = ((List)localObject3).iterator();
      if (((Iterator)localObject3).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
        localConcurrentHashMap.put(localPackageInfo.packageName, this.e.get(localPackageInfo.packageName));
      }
    }
    this.f = localObject2;
    a(0, null);
  }
  
  public static acn g(String paramString)
  {
    return a(paramString, false);
  }
  
  private acs k(String paramString)
  {
    acs localAcs = new acs(null);
    this.g.put(paramString, localAcs);
    return localAcs;
  }
  
  private acs l(String paramString)
  {
    return (acs)this.g.get(paramString);
  }
  
  private void m(String paramString)
  {
    paramString = (acs)this.g.get(paramString);
    if (paramString != null) {
      paramString.c = false;
    }
  }
  
  PackageInfo a(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = this.d.getPackageInfo(paramString, 576);
      return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      try
      {
        paramString = this.d.getPackageInfo(paramString, 8768);
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString) {}
    }
    return null;
  }
  
  public ArrayList a(boolean paramBoolean)
  {
    d();
    Object localObject = this.i;
    if (paramBoolean) {}
    try
    {
      localArrayList1 = new ArrayList(this.e.values());
      return localArrayList1;
    }
    finally {}
    ArrayList localArrayList1 = new ArrayList();
    Iterator localIterator = this.e.values().iterator();
    while (localIterator.hasNext())
    {
      acn localAcn = (acn)localIterator.next();
      if (localAcn.b()) {
        localArrayList1.add(localAcn);
      }
    }
    return localArrayList2;
  }
  
  public void a(int paramInt, String paramString)
  {
    synchronized (this.j)
    {
      ArrayList localArrayList = new ArrayList(this.j);
      ??? = localArrayList.iterator();
      if (((Iterator)???).hasNext()) {
        ((act)((Iterator)???).next()).a(paramInt, paramString);
      }
    }
  }
  
  public PackageManager b()
  {
    return this.d;
  }
  
  public acn c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    d();
    return (acn)this.e.get(paramString);
  }
  
  public acn d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    d();
    return (acn)this.f.get(paramString);
  }
  
  public void h(String paramString)
  {
    d();
    Object localObject2 = a(paramString);
    if (localObject2 == null) {
      return;
    }
    synchronized (this.i)
    {
      localObject2 = new acn((PackageInfo)localObject2);
      this.e.put(((acn)localObject2).a, localObject2);
      if (((acn)localObject2).f() > 0L) {
        this.f.put(((acn)localObject2).a, localObject2);
      }
      a(1, paramString);
      return;
    }
  }
  
  public void i(String paramString)
  {
    d();
    synchronized (this.i)
    {
      this.e.remove(paramString);
      this.f.remove(paramString);
      m(paramString);
      a(2, paramString);
      return;
    }
  }
  
  public void j(String paramString)
  {
    d();
    synchronized (this.i)
    {
      this.e.remove(paramString);
      this.f.remove(paramString);
      m(paramString);
      Object localObject2 = a(paramString);
      if (localObject2 == null) {
        return;
      }
      localObject2 = new acn((PackageInfo)localObject2);
      boolean bool = ((acn)localObject2).c;
      this.e.put(((acn)localObject2).a, localObject2);
      if (((acn)localObject2).f() > 0L) {
        this.f.put(((acn)localObject2).a, localObject2);
      }
      a(3, paramString);
      if (bool)
      {
        a(4, paramString);
        return;
      }
    }
  }
}
