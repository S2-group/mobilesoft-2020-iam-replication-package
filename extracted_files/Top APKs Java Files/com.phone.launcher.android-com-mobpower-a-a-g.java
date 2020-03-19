package com.mobpower.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.mobpower.a.f.e;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class g
{
  public static ConcurrentHashMap<String, com.mobpower.a.d.h> a = new ConcurrentHashMap();
  public static int b = c.e;
  public static int c = c.e;
  public static HashMap<String, com.mobpower.a.d.g> d = new HashMap();
  private static g e;
  private static int f = c.f;
  private Context g;
  private String h;
  private String i;
  private Handler j = new Handler(Looper.getMainLooper());
  
  public g()
  {
    com.mobpower.a.g.b.a.a().a(new Runnable()
    {
      public final void run()
      {
        try
        {
          com.mobpower.a.g.d.c("SDKContext", "initGlobalCommonPara");
          g.d = f.a(g.a(g.this)).c(g.b(g.this));
          new Thread(new Runnable()
          {
            public final void run()
            {
              try
              {
                Object localObject2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                Object localObject1 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                localObject2 = ((Class)localObject2).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { g.a(g.this) });
                localObject1 = (String)((Class)localObject1).getMethod("getId", new Class[0]).invoke(localObject2, new Object[0]);
                com.mobpower.a.g.d.c("SDKContext", "adid:" + (String)localObject1);
                com.mobpower.a.g.c.a((String)localObject1);
                return;
              }
              catch (Exception localException1)
              {
                com.mobpower.a.g.d.d("MPSDK", "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
                try
                {
                  com.mobpower.a.g.c.a(new h(g.this).a(g.a(g.this)).a());
                  return;
                }
                catch (Exception localException2)
                {
                  com.mobpower.a.g.d.d("MPSDK", "GET ADID FROM GOOGLE PLAY APP ERROR");
                }
              }
            }
          }).start();
          com.mobpower.a.g.c.a(g.a(g.this));
          g.this.a(true);
          g.this.g();
          return;
        }
        catch (Exception localException) {}
      }
    }, 500L);
  }
  
  public static g a()
  {
    if (e == null) {}
    try
    {
      e = new g();
      return e;
    }
    finally {}
  }
  
  public static void a(com.mobpower.a.d.g paramG)
  {
    if ((d != null) && (paramG != null))
    {
      com.mobpower.a.g.d.c("probe", "click:" + paramG.b() + ", campid:" + paramG.a());
      d.put(paramG.b(), paramG);
    }
  }
  
  private static void a(final String paramString, boolean paramBoolean)
  {
    try
    {
      com.mobpower.a.g.b.a.a().a(new Runnable()
      {
        public final void run()
        {
          ArrayList localArrayList;
          JSONObject localJSONObject2;
          for (;;)
          {
            try
            {
              Object localObject1 = com.mobpower.a.g.i.b(g.a().b(), c.a, "sys_package_name_removed", "");
              if (TextUtils.isEmpty((CharSequence)localObject1))
              {
                localObject1 = new JSONObject();
                if (!this.a) {
                  break label175;
                }
                ((JSONObject)localObject1).put(paramString, System.currentTimeMillis());
                localObject2 = com.mobpower.a.f.c.a(g.a().b()).a(g.a().c());
                if (localObject2 == null) {
                  break label232;
                }
                Iterator localIterator = ((JSONObject)localObject1).keys();
                localArrayList = new ArrayList();
                if (!localIterator.hasNext()) {
                  break;
                }
                String str = (String)localIterator.next();
                if (((Long)((JSONObject)localObject1).get(str)).longValue() >= System.currentTimeMillis() - ((com.mobpower.a.f.a)localObject2).ao()) {
                  continue;
                }
                localArrayList.add(str);
                continue;
              }
              try
              {
                JSONObject localJSONObject1 = new JSONObject(localThrowable);
              }
              catch (JSONException localJSONException)
              {
                localJSONException.printStackTrace();
                localJSONObject2 = new JSONObject();
              }
            }
            catch (Throwable localThrowable)
            {
              return;
            }
            continue;
            label175:
            if (localJSONObject2.has(paramString)) {
              localJSONObject2.remove(paramString);
            }
          }
          Object localObject2 = localArrayList.iterator();
          while (((Iterator)localObject2).hasNext()) {
            localJSONObject2.remove((String)((Iterator)localObject2).next());
          }
          label232:
          com.mobpower.a.g.i.a(g.a().b(), c.a, "sys_package_name_removed", localJSONObject2.toString());
        }
      });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static void b(String paramString)
  {
    if (a == null) {
      a = new ConcurrentHashMap();
    }
    if (!a.containsKey(paramString))
    {
      localH = new com.mobpower.a.d.h();
      localH.a = paramString;
      localH.b = false;
      localH.c = System.currentTimeMillis();
      a.put(paramString, localH);
      return;
    }
    com.mobpower.a.d.h localH = (com.mobpower.a.d.h)a.get(paramString);
    localH.b = false;
    localH.c = System.currentTimeMillis();
    a.put(paramString, localH);
    a(paramString, false);
  }
  
  public static void c(String paramString)
  {
    if (a == null) {
      return;
    }
    if (a.containsKey(paramString))
    {
      Object localObject = (com.mobpower.a.d.h)a.get(paramString);
      ((com.mobpower.a.d.h)localObject).b = true;
      ((com.mobpower.a.d.h)localObject).c = System.currentTimeMillis();
      a.put(paramString, localObject);
      if ((d != null) && (d.containsKey(paramString)))
      {
        localObject = (com.mobpower.a.d.g)d.get(paramString);
        ((com.mobpower.a.d.g)localObject).a(System.currentTimeMillis());
        a((com.mobpower.a.d.g)localObject);
        a().h();
      }
    }
    a(paramString, true);
  }
  
  public static boolean d(String paramString)
  {
    if ((a != null) && (a.containsKey(paramString))) {}
    while ((!TextUtils.isEmpty(paramString)) && (d != null) && (d.containsKey(paramString))) {
      return true;
    }
    return false;
  }
  
  public static List<Long> f()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((d != null) && (d.size() > 0))
      {
        localIterator = d.keySet().iterator();
        localArrayList = new ArrayList();
      }
      while (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        localObject = (com.mobpower.a.d.g)d.get(localObject);
        try
        {
          localArrayList.add(Long.valueOf(Long.parseLong(((com.mobpower.a.d.g)localObject).a())));
        }
        catch (NumberFormatException localNumberFormatException)
        {
          localNumberFormatException.printStackTrace();
        }
        continue;
        return null;
      }
    }
    catch (Throwable localThrowable) {}
    return localArrayList;
  }
  
  public static boolean i()
  {
    return f == c.e;
  }
  
  public static HashMap<String, com.mobpower.a.d.g> j()
  {
    return d;
  }
  
  private ConcurrentHashMap<String, com.mobpower.a.d.h> k()
  {
    if (a == null) {
      a = new ConcurrentHashMap();
    }
    try
    {
      Iterator localIterator = this.g.getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0)
        {
          com.mobpower.a.d.h localH = new com.mobpower.a.d.h();
          localH.a = localPackageInfo.packageName;
          localH.b = false;
          localH.c = System.currentTimeMillis();
          a.put(localPackageInfo.packageName, localH);
        }
      }
      return a;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private static List<com.mobpower.a.d.h> l()
  {
    for (;;)
    {
      try
      {
        Object localObject1 = com.mobpower.a.g.i.b(a().g, c.a, "sys_package_name_removed", "");
        boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
        if (!bool) {
          try
          {
            localObject1 = new JSONObject((String)localObject1);
            localObject2 = com.mobpower.a.f.c.a(a().g).a(a().c());
            if (localObject2 != null)
            {
              Iterator localIterator = ((JSONObject)localObject1).keys();
              localArrayList2 = new ArrayList();
              localArrayList1 = new ArrayList();
              if (!localIterator.hasNext()) {
                continue;
              }
              str = (String)localIterator.next();
              l = ((Long)((JSONObject)localObject1).get(str)).longValue();
              if (l < System.currentTimeMillis() - ((com.mobpower.a.f.a)localObject2).ao())
              {
                localArrayList2.add(str);
                continue;
              }
            }
            else
            {
              return null;
            }
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        ArrayList localArrayList2;
        ArrayList localArrayList1;
        String str;
        long l;
        com.mobpower.a.d.h localH;
        continue;
      }
      localH = new com.mobpower.a.d.h();
      localH.a = str;
      localH.c = l;
      localH.b = true;
      localArrayList1.add(localH);
    }
    if (localArrayList2.size() > 0)
    {
      localObject2 = localArrayList2.iterator();
      while (((Iterator)localObject2).hasNext()) {
        localJSONException.remove((String)((Iterator)localObject2).next());
      }
      com.mobpower.a.g.i.a(a().g, c.a, "sys_package_name_removed", localJSONException.toString());
    }
    return localArrayList1;
  }
  
  public final ConcurrentHashMap<String, com.mobpower.a.d.h> a(boolean paramBoolean)
  {
    if ((!paramBoolean) && (a != null) && (a.size() > 0)) {
      return a;
    }
    ConcurrentHashMap localConcurrentHashMap = k();
    Object localObject = l();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        com.mobpower.a.d.h localH = (com.mobpower.a.d.h)((Iterator)localObject).next();
        if (!localConcurrentHashMap.containsKey(localH.a)) {
          localConcurrentHashMap.put(localH.a, localH);
        } else {
          a(localH.a, false);
        }
      }
      a = localConcurrentHashMap;
    }
    return a;
  }
  
  public final void a(Context paramContext)
  {
    this.g = paramContext;
  }
  
  public final void a(final Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      com.mobpower.a.g.d.c("SDKContext", "init");
      this.g = paramContext.getApplicationContext();
      this.h = paramString1;
      com.mobpower.a.g.i.a(this.g, c.a, "appid", paramString1);
      this.i = paramString2;
      com.mobpower.a.g.i.a(this.g, c.a, "appkey", paramString2);
      f = c.e;
      a(new Runnable()
      {
        public final void run()
        {
          if (!com.mobpower.api.d.b) {
            com.mobpower.a.g.c.o(paramContext);
          }
        }
      }, 5000L);
      a(new Runnable()
      {
        public final void run()
        {
          com.mobpower.a.f.c.a(g.a(g.this)).a();
          com.mobpower.a.g.a.a.a(g.a(g.this), g.b(g.this));
        }
      }, 2000L);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public final void a(Runnable paramRunnable)
  {
    this.j.post(paramRunnable);
  }
  
  public final void a(Runnable paramRunnable, long paramLong)
  {
    this.j.postDelayed(paramRunnable, paramLong);
  }
  
  public final void a(String paramString)
  {
    com.mobpower.a.f.c.a(this.g).a();
    e.a(this.g).b(paramString);
  }
  
  public final Context b()
  {
    return this.g;
  }
  
  public final String c()
  {
    if (TextUtils.isEmpty(this.h)) {
      this.h = com.mobpower.a.g.i.b(this.g, c.a, "appid", "");
    }
    return this.h;
  }
  
  public final String d()
  {
    if (TextUtils.isEmpty(this.i)) {
      this.i = com.mobpower.a.g.i.b(this.g, c.a, "appkey", "");
    }
    return this.i;
  }
  
  public final List<String> e()
  {
    try
    {
      ConcurrentHashMap localConcurrentHashMap = a(false);
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localConcurrentHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (!((com.mobpower.a.d.h)localConcurrentHashMap.get(str)).b) {
          localArrayList.add(str);
        }
      }
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      return null;
    }
  }
  
  /* Error */
  public final void g()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   5: ifnull +12 -> 17
    //   8: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   11: invokevirtual 193	java/util/HashMap:size	()I
    //   14: ifgt +20 -> 34
    //   17: aload_0
    //   18: getfield 89	com/mobpower/a/a/g:g	Landroid/content/Context;
    //   21: invokestatic 381	com/mobpower/a/a/f:a	(Landroid/content/Context;)Lcom/mobpower/a/a/f;
    //   24: aload_0
    //   25: getfield 138	com/mobpower/a/a/g:h	Ljava/lang/String;
    //   28: invokevirtual 384	com/mobpower/a/a/f:c	(Ljava/lang/String;)Ljava/util/HashMap;
    //   31: putstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   34: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   37: ifnull +14 -> 51
    //   40: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   43: invokevirtual 193	java/util/HashMap:size	()I
    //   46: istore_1
    //   47: iload_1
    //   48: ifne +6 -> 54
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: new 55	java/util/HashMap
    //   57: dup
    //   58: invokespecial 56	java/util/HashMap:<init>	()V
    //   61: astore_3
    //   62: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   65: invokevirtual 197	java/util/HashMap:keySet	()Ljava/util/Set;
    //   68: invokeinterface 203 1 0
    //   73: astore 4
    //   75: aload 4
    //   77: invokeinterface 212 1 0
    //   82: istore_2
    //   83: iload_2
    //   84: ifeq +101 -> 185
    //   87: aload 4
    //   89: invokeinterface 216 1 0
    //   94: checkcast 218	java/lang/String
    //   97: astore 5
    //   99: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   102: aload 5
    //   104: invokevirtual 168	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   107: checkcast 105	com/mobpower/a/d/g
    //   110: astore 6
    //   112: getstatic 42	com/mobpower/a/a/g:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   115: ifnull -40 -> 75
    //   118: getstatic 42	com/mobpower/a/a/g:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   121: aload 5
    //   123: invokevirtual 142	java/util/concurrent/ConcurrentHashMap:containsKey	(Ljava/lang/Object;)Z
    //   126: ifeq -51 -> 75
    //   129: getstatic 42	com/mobpower/a/a/g:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   132: aload 5
    //   134: invokevirtual 164	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   137: checkcast 144	com/mobpower/a/d/h
    //   140: getfield 150	com/mobpower/a/d/h:b	Z
    //   143: ifne -68 -> 75
    //   146: aload 6
    //   148: invokestatic 156	java/lang/System:currentTimeMillis	()J
    //   151: invokevirtual 171	com/mobpower/a/d/g:a	(J)V
    //   154: aload_3
    //   155: aload 5
    //   157: aload 6
    //   159: invokevirtual 128	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   162: pop
    //   163: goto -88 -> 75
    //   166: astore 5
    //   168: aload 5
    //   170: invokevirtual 385	java/lang/Exception:printStackTrace	()V
    //   173: ldc_w 334
    //   176: ldc_w 387
    //   179: invokestatic 389	com/mobpower/a/g/d:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: goto -107 -> 75
    //   185: aload_0
    //   186: getfield 89	com/mobpower/a/a/g:g	Landroid/content/Context;
    //   189: invokestatic 294	com/mobpower/a/f/c:a	(Landroid/content/Context;)Lcom/mobpower/a/f/c;
    //   192: aload_0
    //   193: getfield 138	com/mobpower/a/a/g:h	Ljava/lang/String;
    //   196: invokevirtual 299	com/mobpower/a/f/c:a	(Ljava/lang/String;)Lcom/mobpower/a/f/a;
    //   199: astore 4
    //   201: aload 4
    //   203: ifnull +101 -> 304
    //   206: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   209: ifnull +95 -> 304
    //   212: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   215: invokevirtual 197	java/util/HashMap:keySet	()Ljava/util/Set;
    //   218: invokeinterface 203 1 0
    //   223: astore 5
    //   225: aload 5
    //   227: invokeinterface 212 1 0
    //   232: ifeq +72 -> 304
    //   235: aload 5
    //   237: invokeinterface 216 1 0
    //   242: checkcast 218	java/lang/String
    //   245: astore 6
    //   247: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   250: aload 6
    //   252: invokevirtual 168	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   255: checkcast 105	com/mobpower/a/d/g
    //   258: astore 7
    //   260: aload_3
    //   261: aload 6
    //   263: invokevirtual 167	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   266: ifne -41 -> 225
    //   269: aload 7
    //   271: invokevirtual 391	com/mobpower/a/d/g:c	()J
    //   274: invokestatic 156	java/lang/System:currentTimeMillis	()J
    //   277: aload 4
    //   279: invokevirtual 394	com/mobpower/a/f/a:aq	()J
    //   282: lsub
    //   283: lcmp
    //   284: ifle -59 -> 225
    //   287: aload_3
    //   288: aload 6
    //   290: aload 7
    //   292: invokevirtual 128	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   295: pop
    //   296: goto -71 -> 225
    //   299: astore_3
    //   300: aload_0
    //   301: monitorexit
    //   302: aload_3
    //   303: athrow
    //   304: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   307: ifnull +9 -> 316
    //   310: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   313: invokevirtual 397	java/util/HashMap:clear	()V
    //   316: aload_3
    //   317: invokevirtual 193	java/util/HashMap:size	()I
    //   320: ifle +10 -> 330
    //   323: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   326: aload_3
    //   327: invokevirtual 401	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   330: aload_0
    //   331: getfield 89	com/mobpower/a/a/g:g	Landroid/content/Context;
    //   334: invokestatic 381	com/mobpower/a/a/f:a	(Landroid/content/Context;)Lcom/mobpower/a/a/f;
    //   337: getstatic 58	com/mobpower/a/a/g:d	Ljava/util/HashMap;
    //   340: invokevirtual 405	java/util/HashMap:values	()Ljava/util/Collection;
    //   343: invokevirtual 408	com/mobpower/a/a/f:a	(Ljava/util/Collection;)V
    //   346: goto -295 -> 51
    //   349: astore_3
    //   350: goto -299 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	353	0	this	g
    //   46	2	1	k	int
    //   82	2	2	bool	boolean
    //   61	227	3	localHashMap	HashMap
    //   299	28	3	localMap	java.util.Map
    //   349	1	3	localThrowable	Throwable
    //   73	205	4	localObject1	Object
    //   97	59	5	str	String
    //   166	3	5	localException	Exception
    //   223	13	5	localIterator	Iterator
    //   110	179	6	localObject2	Object
    //   258	33	7	localG	com.mobpower.a.d.g
    // Exception table:
    //   from	to	target	type
    //   87	163	166	java/lang/Exception
    //   2	17	299	finally
    //   17	34	299	finally
    //   34	47	299	finally
    //   54	75	299	finally
    //   75	83	299	finally
    //   87	163	299	finally
    //   168	182	299	finally
    //   185	201	299	finally
    //   206	225	299	finally
    //   225	296	299	finally
    //   304	316	299	finally
    //   316	330	299	finally
    //   330	346	299	finally
    //   2	17	349	java/lang/Throwable
    //   17	34	349	java/lang/Throwable
    //   34	47	349	java/lang/Throwable
    //   54	75	349	java/lang/Throwable
    //   75	83	349	java/lang/Throwable
    //   87	163	349	java/lang/Throwable
    //   168	182	349	java/lang/Throwable
    //   185	201	349	java/lang/Throwable
    //   206	225	349	java/lang/Throwable
    //   225	296	349	java/lang/Throwable
    //   304	316	349	java/lang/Throwable
    //   316	330	349	java/lang/Throwable
    //   330	346	349	java/lang/Throwable
  }
  
  public final void h()
  {
    com.mobpower.a.g.d.c("saveAppInfo-----------------addInstallApp---------------->", " " + d.size());
    try
    {
      if ((d != null) && (d.size() > 0)) {
        f.a(this.g).a(d.values());
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
}
