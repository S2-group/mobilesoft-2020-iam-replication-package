package jp.co.a.a;

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

final class av
  implements bn, x
{
  private static final au m = new aw();
  private final int a;
  private final int b;
  private final long c;
  private final long d;
  private final ce e;
  private final List f;
  private final ArrayList g = new ArrayList();
  private final int h;
  private final boolean i;
  private final ax j;
  private final List k;
  private final AtomicBoolean l;
  
  private av(ay paramAy)
  {
    this.a = ay.a(paramAy);
    this.b = ay.b(paramAy);
    this.c = ay.c(paramAy);
    this.d = (Calendar.getInstance().getTimeInMillis() + this.c);
    this.e = ay.d(paramAy);
    this.h = ay.e(paramAy);
    this.i = ay.f(paramAy);
    if (ay.g(paramAy) == null)
    {
      this.f = Collections.unmodifiableList(Collections.emptyList());
      ch.c("house ad deliver type", this, new String[] { ", random:", String.valueOf(ch.c()) });
      if (!ch.c()) {
        break label190;
      }
    }
    label190:
    for (this.j = new az(this);; this.j = new ba(this))
    {
      if (ay.h(paramAy) != null) {
        break label205;
      }
      this.k = Collections.unmodifiableList(Collections.emptyList());
      this.l = new AtomicBoolean(false);
      return;
      this.f = Collections.unmodifiableList(new CopyOnWriteArrayList(ay.g(paramAy)));
      break;
    }
    label205:
    this.k = Collections.unmodifiableList(new CopyOnWriteArrayList(ay.h(paramAy)));
    if (as.a(this.k)) {}
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
      int i1 = localJSONArray.length();
      int n = 0;
      for (;;)
      {
        paramJSONObject = localArrayList;
        if (n >= i1) {
          break;
        }
        paramJSONObject = bz.a(localJSONArray.getJSONObject(n));
        if (!as.a(paramJSONObject.b())) {
          localArrayList.add(paramJSONObject);
        }
        n += 1;
      }
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject = new ArrayList();
    }
  }
  
  static av a(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    try
    {
      da.b(paramString);
      paramString = new JSONObject(paramString).getJSONObject("result");
      ay localAy = new ay();
      ay.b(ay.a(ay.a(localAy, paramString.getLong("expiration") * 1000L), paramInt1), paramInt2);
      ay.a(localAy, paramString.optBoolean("enableHouseAd", false));
      ay.c(localAy, paramString.optInt("houseAdSelectRate", 0));
      ay.a(localAy, b(paramString));
      JSONObject localJSONObject = paramString.getJSONArray("environments").getJSONObject(0);
      cf localCf = new cf();
      localCf.a(paramInt3);
      localJSONObject = localJSONObject.getJSONObject("spotInfo");
      localCf.a(localJSONObject.optLong("duration", 20L) * 1000L);
      paramInt1 = localJSONObject.optInt("animationType", al.b.a().intValue());
      localCf.a((al)as.a(al.e(), Integer.valueOf(paramInt1), al.b));
      ay.a(localAy, new ce(localCf));
      ay.b(localAy, a(paramString));
      paramString = new av(localAy);
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new dd(d.a, "json deserialize error", paramString);
    }
  }
  
  private static List b(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.optJSONArray("houseAds");
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i1 = paramJSONObject.length();
    int n = 0;
    label31:
    JSONObject localJSONObject;
    bl localBl;
    if (n < i1)
    {
      localJSONObject = paramJSONObject.getJSONObject(n);
      localBl = new bl();
      if (!localJSONObject.get("adType").equals("icon")) {
        break label173;
      }
      localBl.a(j.e);
    }
    for (;;)
    {
      localBl.a(z.b);
      int i2 = localJSONObject.getInt("houseAdvertisementID");
      localBl.a(localJSONObject.optString("applicationIdentifier", "")).a(i2).b(localJSONObject.optString("landingURL", "")).a(da.a(localJSONObject, i2)).b(da.b(localJSONObject, i2));
      localArrayList.add(new bj(localBl));
      n += 1;
      break label31;
      break;
      label173:
      localBl.a(j.d);
    }
  }
  
  private List k()
  {
    List localList = as.a(this.f, m);
    Iterator localIterator = this.g.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localList;
      }
      localList.remove(localIterator.next());
    }
  }
  
  /* Error */
  private bj l()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 104	jp/co/a/a/av:f	Ljava/util/List;
    //   6: invokestatic 153	jp/co/a/a/as:a	(Ljava/util/Collection;)Z
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
    //   21: getfield 129	jp/co/a/a/av:j	Ljp/co/a/a/ax;
    //   24: invokeinterface 417 1 0
    //   29: astore_2
    //   30: goto -14 -> 16
    //   33: astore_2
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_2
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	av
    //   9	2	1	bool	boolean
    //   15	15	2	localBj	bj
    //   33	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	33	finally
    //   20	30	33	finally
  }
  
  final cc a(PackageManager paramPackageManager, ah paramAh)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(128);
    HashMap localHashMap = new HashMap();
    Object localObject1 = br.a();
    int i1 = localObject1.length;
    int n = 0;
    ArrayList localArrayList;
    Object localObject2;
    if (n >= i1)
    {
      localObject1 = new cd();
      ((cd)localObject1).a(paramAh.m());
      localArrayList = new ArrayList();
      localObject2 = this.k.iterator();
      if (!((Iterator)localObject2).hasNext())
      {
        ((cd)localObject1).a(localArrayList);
        return new cc((cd)localObject1);
      }
    }
    else
    {
      localArrayList = localObject1[n];
      localObject2 = new HashSet();
      localObject3 = paramPackageManager.iterator();
      for (;;)
      {
        if (!((Iterator)localObject3).hasNext())
        {
          localHashMap.put(localArrayList, localObject2);
          n += 1;
          break;
        }
        ((HashSet)localObject2).add(localArrayList.a(((ApplicationInfo)((Iterator)localObject3).next()).packageName));
      }
    }
    Object localObject4 = (bz)((Iterator)localObject2).next();
    paramAh = cb.a;
    paramPackageManager = by.b;
    Object localObject3 = new bx();
    ((bx)localObject3).a(((bz)localObject4).a());
    Iterator localIterator = ((bz)localObject4).b().iterator();
    label231:
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      ((bx)localObject3).a(paramAh);
      ((bx)localObject3).a(paramPackageManager);
      localArrayList.add(new bw((bx)localObject3));
      break;
      localObject4 = (bu)localIterator.next();
      if (((bu)localObject4).c() != by.b) {
        break label231;
      }
      HashSet localHashSet = (HashSet)localHashMap.get(((bu)localObject4).b());
      if (localHashSet == null)
      {
        ((bx)localObject3).a(((bu)localObject4).c());
        break label231;
      }
      if (!localHashSet.contains(((bu)localObject4).a())) {
        break label231;
      }
      paramAh = cb.b;
      paramPackageManager = ((bu)localObject4).c();
    }
  }
  
  public final w a()
  {
    return l();
  }
  
  final void a(PackageManager paramPackageManager)
  {
    if (!this.i) {
      return;
    }
    paramPackageManager = paramPackageManager.getInstalledApplications(128);
    Iterator localIterator1 = this.f.iterator();
    label27:
    bj localBj;
    String str;
    int n;
    label73:
    Iterator localIterator2;
    if (localIterator1.hasNext())
    {
      localBj = (bj)localIterator1.next();
      str = localBj.f();
      if ((str == null) || (str.length() == 0)) {
        break label106;
      }
      n = 0;
      if (n != 0) {
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
      localBj.a(bool);
      break label27;
      break;
      label106:
      n = 1;
      break label73;
      label109:
      break label27;
      label111:
      if (!str.equals(((ApplicationInfo)localIterator2.next()).packageName)) {
        break label85;
      }
      ch.b("house ad installed package ", this, new String[] { ",mediaId:", String.valueOf(this.b), ",houseAdId:", String.valueOf(localBj.b()), ",package:", str });
    }
  }
  
  final int b()
  {
    return this.f.size();
  }
  
  final void c()
  {
    this.g.clear();
  }
  
  public final int d()
  {
    if (!this.i) {}
    while (as.a(this.f)) {
      return 0;
    }
    return k().size();
  }
  
  public final boolean e()
  {
    return d() > 0;
  }
  
  final boolean f()
  {
    if (Calendar.getInstance().getTimeInMillis() > this.d)
    {
      ch.c("expiration spot environment data", this, new String[] { ", mediaId:", String.valueOf(this.b) });
      return true;
    }
    return false;
  }
  
  final ce g()
  {
    return this.e;
  }
  
  public final String getLogContents()
  {
    if (this.e == null) {
      return ",mediaId:" + String.valueOf(this.b);
    }
    return ",spotId:" + String.valueOf(this.e.a());
  }
  
  public final String getLogTag()
  {
    return "(IM)Environment:";
  }
  
  final boolean h()
  {
    return this.i;
  }
  
  final int i()
  {
    return this.h;
  }
  
  protected final AtomicBoolean j()
  {
    return this.l;
  }
}
