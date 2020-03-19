package jp.co.imobile.android;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class bb
  implements ag, bp
{
  private static final ba a = new bc();
  private final int b;
  private final int c;
  private final long d;
  private final long e;
  private final cg f;
  private final List g;
  private final int h;
  private final boolean i;
  private final bd j;
  private final List k;
  private final AtomicBoolean l;
  
  private bb(be paramBe)
  {
    this.b = be.a(paramBe);
    this.c = be.b(paramBe);
    this.d = be.c(paramBe);
    this.e = (Calendar.getInstance().getTimeInMillis() + this.d);
    this.f = be.d(paramBe);
    this.h = be.e(paramBe);
    this.i = be.f(paramBe);
    if (be.g(paramBe) == null)
    {
      this.g = Collections.unmodifiableList(Collections.emptyList());
      cj.c("house ad deliver type", this, new String[] { ", random:", String.valueOf(cj.c()) });
      if (!cj.c()) {
        break label179;
      }
    }
    label179:
    for (this.j = new bf(this);; this.j = new bg(this))
    {
      if (be.h(paramBe) != null) {
        break label194;
      }
      this.k = Collections.unmodifiableList(Collections.emptyList());
      this.l = new AtomicBoolean(false);
      return;
      this.g = Collections.unmodifiableList(new CopyOnWriteArrayList(be.g(paramBe)));
      break;
    }
    label194:
    this.k = Collections.unmodifiableList(new CopyOnWriteArrayList(be.h(paramBe)));
    if (ay.a(this.k)) {}
    for (;;)
    {
      this.l = new AtomicBoolean(bool);
      return;
      bool = true;
    }
  }
  
  private static List a(JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("pi");
      ArrayList localArrayList = new ArrayList();
      if (localJSONArray == null) {
        return localArrayList;
      }
      int n = localJSONArray.length();
      int m = 0;
      for (;;)
      {
        paramJSONObject = localArrayList;
        if (m >= n) {
          break;
        }
        paramJSONObject = cb.a(localJSONArray.getJSONObject(m));
        if (!ay.a(paramJSONObject.b())) {
          localArrayList.add(paramJSONObject);
        }
        m += 1;
      }
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject = new ArrayList();
    }
  }
  
  static bb a(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    try
    {
      n.b(paramString);
      paramString = new JSONObject(paramString).getJSONObject("result");
      be localBe = new be();
      be.b(be.a(be.a(localBe, paramString.getLong("expiration") * 1000L), paramInt1), paramInt2);
      be.a(localBe, paramString.optBoolean("enableHouseAd", false));
      be.c(localBe, paramString.optInt("houseAdSelectRate", 0));
      be.a(localBe, b(paramString));
      JSONObject localJSONObject = paramString.getJSONArray("environments").getJSONObject(0);
      ch localCh = new ch();
      localCh.a(paramInt3);
      localJSONObject = localJSONObject.getJSONObject("spotInfo");
      localCh.a(localJSONObject.optLong("duration", 20L) * 1000L);
      paramInt1 = localJSONObject.optInt("animationType", ar.b.a().intValue());
      localCh.a((ar)ay.a(ar.d(), Integer.valueOf(paramInt1), ar.b));
      be.a(localBe, new cg(localCh));
      be.b(localBe, a(paramString));
      paramString = new bb(localBe);
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new p(AdRequestResultType.UNKNOWN_ERROR, "json deserialize error", paramString);
    }
  }
  
  private static List b(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.optJSONArray("houseAds");
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {}
    for (;;)
    {
      return localArrayList;
      int n = paramJSONObject.length();
      int m = 0;
      while (m < n)
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject(m);
        bn localBn = new bn();
        localBn.a(s.c);
        localBn.a(ai.b);
        int i1 = localJSONObject.getInt("houseAdvertisementID");
        localBn.a(localJSONObject.optString("applicationIdentifier", "")).a(i1).b(localJSONObject.optString("landingURL", "")).a(n.a(localJSONObject, i1));
        localArrayList.add(new bm(localBn));
        m += 1;
      }
    }
  }
  
  /* Error */
  private bm j()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 97	jp/co/imobile/android/bb:g	Ljava/util/List;
    //   6: invokestatic 146	jp/co/imobile/android/ay:a	(Ljava/util/Collection;)Z
    //   9: istore_1
    //   10: iload_1
    //   11: ifeq +9 -> 20
    //   14: aconst_null
    //   15: astore_2
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_2
    //   19: areturn
    //   20: aload_0
    //   21: getfield 122	jp/co/imobile/android/bb:j	Ljp/co/imobile/android/bd;
    //   24: invokeinterface 376 1 0
    //   29: astore_2
    //   30: goto -14 -> 16
    //   33: astore_2
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_2
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	bb
    //   9	2	1	bool	boolean
    //   15	15	2	localBm	bm
    //   33	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	33	finally
    //   20	30	33	finally
  }
  
  public final af a()
  {
    return j();
  }
  
  final ce a(PackageManager paramPackageManager, ao paramAo)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(128);
    HashMap localHashMap = new HashMap();
    Object localObject1 = bt.a();
    int n = localObject1.length;
    int m = 0;
    ArrayList localArrayList;
    Object localObject2;
    if (m >= n)
    {
      localObject1 = new cf();
      ((cf)localObject1).a(paramAo.m());
      localArrayList = new ArrayList();
      localObject2 = this.k.iterator();
      if (!((Iterator)localObject2).hasNext())
      {
        ((cf)localObject1).a(localArrayList);
        return new ce((cf)localObject1);
      }
    }
    else
    {
      localArrayList = localObject1[m];
      localObject2 = new HashSet();
      localObject3 = paramPackageManager.iterator();
      for (;;)
      {
        if (!((Iterator)localObject3).hasNext())
        {
          localHashMap.put(localArrayList, localObject2);
          m += 1;
          break;
        }
        ((HashSet)localObject2).add(localArrayList.a(((ApplicationInfo)((Iterator)localObject3).next()).packageName));
      }
    }
    Object localObject4 = (cb)((Iterator)localObject2).next();
    paramAo = cd.a;
    paramPackageManager = ca.b;
    Object localObject3 = new bz();
    ((bz)localObject3).a(((cb)localObject4).a());
    Iterator localIterator = ((cb)localObject4).b().iterator();
    label231:
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      ((bz)localObject3).a(paramAo);
      ((bz)localObject3).a(paramPackageManager);
      localArrayList.add(new by((bz)localObject3));
      break;
      localObject4 = (bw)localIterator.next();
      if (((bw)localObject4).c() != ca.b) {
        break label231;
      }
      HashSet localHashSet = (HashSet)localHashMap.get(((bw)localObject4).b());
      if (localHashSet == null)
      {
        ((bz)localObject3).a(((bw)localObject4).c());
        break label231;
      }
      if (!localHashSet.contains(((bw)localObject4).a())) {
        break label231;
      }
      paramAo = cd.b;
      paramPackageManager = ((bw)localObject4).c();
    }
  }
  
  final void a(PackageManager paramPackageManager)
  {
    if (!this.i) {
      return;
    }
    paramPackageManager = paramPackageManager.getInstalledApplications(128);
    Iterator localIterator1 = this.g.iterator();
    label27:
    bm localBm;
    String str;
    int m;
    label73:
    Iterator localIterator2;
    if (localIterator1.hasNext())
    {
      localBm = (bm)localIterator1.next();
      str = localBm.e();
      if ((str == null) || (str.length() == 0)) {
        break label106;
      }
      m = 0;
      if (m != 0) {
        break label109;
      }
      localIterator2 = paramPackageManager.iterator();
      label85:
      if (localIterator2.hasNext()) {
        break label111;
      }
    }
    for (boolean bool = false;; bool = true)
    {
      localBm.a(bool);
      break label27;
      break;
      label106:
      m = 1;
      break label73;
      label109:
      break label27;
      label111:
      if (!str.equals(((ApplicationInfo)localIterator2.next()).packageName)) {
        break label85;
      }
      cj.b("house ad installed package ", this, new String[] { ",mediaId:", String.valueOf(this.c), ",houseAdId:", String.valueOf(localBm.a()), ",package:", str });
    }
  }
  
  public final int b()
  {
    int m = 0;
    int i1 = 0;
    if (!this.i) {}
    while (ay.a(this.g)) {
      return i1;
    }
    List localList = this.g;
    ba localBa = a;
    int i2 = localList.size();
    int n = 0;
    for (;;)
    {
      i1 = m;
      if (n >= i2) {
        break;
      }
      i1 = m;
      if (localBa.a(localList.get(n))) {
        i1 = m + 1;
      }
      n += 1;
      m = i1;
    }
  }
  
  public final boolean c()
  {
    return b() > 0;
  }
  
  final boolean d()
  {
    if (Calendar.getInstance().getTimeInMillis() > this.e)
    {
      cj.c("expiration spot environment data", this, new String[] { ", mediaId:", String.valueOf(this.c) });
      return true;
    }
    return false;
  }
  
  final cg e()
  {
    return this.f;
  }
  
  final boolean f()
  {
    return this.i;
  }
  
  final int g()
  {
    return this.h;
  }
  
  public final String getLogContents()
  {
    if (this.f == null) {
      return ",mediaId:" + String.valueOf(this.c);
    }
    return ",spotId:" + String.valueOf(this.f.a());
  }
  
  public final String getLogTag()
  {
    return "(IM)Environment:";
  }
  
  protected final AtomicBoolean h()
  {
    return this.l;
  }
}
