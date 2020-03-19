package jp.co.imobile.android;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class be
  implements aj
{
  private static final bd a = new bf();
  private final int b;
  private final int c;
  private final long d;
  private final long e;
  private final bv f;
  private final List g;
  private final int h;
  private final boolean i;
  private final bg j;
  
  private be(bh paramBh)
  {
    this.b = bh.a(paramBh);
    this.c = bh.b(paramBh);
    this.d = bh.c(paramBh);
    this.e = (Calendar.getInstance().getTimeInMillis() + this.d);
    this.f = bh.d(paramBh);
    this.h = bh.e(paramBh);
    this.i = bh.f(paramBh);
    if (bh.g(paramBh) == null) {}
    for (this.g = Collections.unmodifiableList(Collections.emptyList());; this.g = Collections.unmodifiableList(new CopyOnWriteArrayList(bh.g(paramBh))))
    {
      b("house ad deliver type", new String[] { ", random:", String.valueOf(by.b()) });
      if (!by.b()) {
        break;
      }
      this.j = new bi(this);
      return;
    }
    this.j = new bj(this);
  }
  
  private static List a(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.optJSONArray("houseAds");
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int m = paramJSONObject.length();
    int k = 0;
    for (;;)
    {
      if (k >= m) {
        return localArrayList;
      }
      JSONObject localJSONObject = paramJSONObject.getJSONObject(k);
      bq localBq = new bq();
      localBq.a(u.c);
      localBq.a(al.b);
      int n = localJSONObject.getInt("houseAdvertisementID");
      localBq.a(localJSONObject.optString("applicationIdentifier", "")).a(n).b(localJSONObject.optString("landingURL", "")).a(n.a(localJSONObject, n));
      localArrayList.add(new bp(localBq));
      k += 1;
    }
  }
  
  static be a(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    try
    {
      n.c(paramString);
      Object localObject2 = new JSONObject(paramString).getJSONObject("result");
      Object localObject1 = new bh();
      bh.b(bh.a(bh.a((bh)localObject1, ((JSONObject)localObject2).getLong("expiration") * 1000L), paramInt1), paramInt2);
      bh.a((bh)localObject1, ((JSONObject)localObject2).optBoolean("enableHouseAd", false));
      bh.c((bh)localObject1, ((JSONObject)localObject2).optInt("houseAdSelectRate", 0));
      bh.a((bh)localObject1, a((JSONObject)localObject2));
      JSONObject localJSONObject = ((JSONObject)localObject2).getJSONArray("environments").getJSONObject(0);
      localObject2 = new bw();
      ((bw)localObject2).a(paramInt3);
      localJSONObject = localJSONObject.getJSONObject("spotInfo");
      ((bw)localObject2).a(localJSONObject.optLong("duration", 20L) * 1000L);
      paramInt1 = localJSONObject.optInt("animationType", au.b.a().intValue());
      ((bw)localObject2).a((au)bb.a(au.d(), Integer.valueOf(paramInt1), au.b));
      bh.a((bh)localObject1, new bv((bw)localObject2));
      localObject1 = new be((bh)localObject1);
      return localObject1;
    }
    catch (JSONException localJSONException)
    {
      throw new q(AdRequestResultType.UNKNOWN_ERROR, d("json deserialize error", new String[] { ",spotId:", String.valueOf(paramInt3), ", target:", paramString }), localJSONException);
    }
  }
  
  private static final void b(String paramString, String... paramVarArgs)
  {
    by.c().b("i-mobile SDK", d(paramString, paramVarArgs) + ", by SDK InternalVersion:" + "1.2.1");
  }
  
  private static final void c(String paramString, String... paramVarArgs)
  {
    by.c().a("i-mobile SDK", d(paramString, paramVarArgs));
  }
  
  private static final String d(String paramString, String... paramVarArgs)
  {
    paramString = new StringBuilder().append("(IM)Environment:").append(paramString).append("[params]");
    int m = paramVarArgs.length;
    int k = 0;
    for (;;)
    {
      if (k >= m) {
        return paramString.toString();
      }
      paramString.append(paramVarArgs[k]);
      k += 1;
    }
  }
  
  /* Error */
  private bp i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 92	jp/co/imobile/android/be:g	Ljava/util/List;
    //   6: invokestatic 390	jp/co/imobile/android/bb:a	(Ljava/util/Collection;)Z
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
    //   21: getfield 117	jp/co/imobile/android/be:j	Ljp/co/imobile/android/bg;
    //   24: invokeinterface 394 1 0
    //   29: astore_2
    //   30: goto -14 -> 16
    //   33: astore_2
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_2
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	be
    //   9	2	1	bool	boolean
    //   15	15	2	localBp	bp
    //   33	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	33	finally
    //   20	30	33	finally
  }
  
  public final ai a()
  {
    return i();
  }
  
  final void a(PackageManager paramPackageManager)
  {
    if (!this.i) {
      return;
    }
    paramPackageManager = paramPackageManager.getInstalledApplications(128);
    Iterator localIterator1 = this.g.iterator();
    label26:
    bp localBp;
    String str;
    Iterator localIterator2;
    while (localIterator1.hasNext())
    {
      localBp = (bp)localIterator1.next();
      str = localBp.e();
      if (!bx.a(str))
      {
        localIterator2 = paramPackageManager.iterator();
        label69:
        if (localIterator2.hasNext()) {
          break label90;
        }
      }
    }
    for (boolean bool = false;; bool = true)
    {
      localBp.a(bool);
      break label26;
      break;
      label90:
      if (!str.equals(((ApplicationInfo)localIterator2.next()).packageName)) {
        break label69;
      }
      c("house ad installed package ", new String[] { ",mediaId:", String.valueOf(this.c), ",houseAdId:", String.valueOf(localBp.a()), ",package:", str });
    }
  }
  
  public final int b()
  {
    int k = 0;
    if (!this.i) {
      return 0;
    }
    if (bb.a(this.g)) {
      return 0;
    }
    List localList = this.g;
    bd localBd = a;
    int i1 = localList.size();
    int n;
    for (int m = 0;; m = n)
    {
      if (k >= i1) {
        return m;
      }
      n = m;
      if (localBd.a(localList.get(k))) {
        n = m + 1;
      }
      k += 1;
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
      b("expiration spot environment data", new String[] { ", mediaId:", String.valueOf(this.c) });
      return true;
    }
    return false;
  }
  
  final bv e()
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
}
