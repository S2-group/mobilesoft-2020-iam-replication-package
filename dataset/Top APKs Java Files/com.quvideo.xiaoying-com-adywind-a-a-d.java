package com.adywind.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.adywind.a.d.g;
import com.adywind.a.d.h;
import com.adywind.a.g.e;
import com.adywind.a.g.j;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  private static d aBt;
  public static ConcurrentHashMap<String, h> aBu = new ConcurrentHashMap();
  public static HashMap<String, g> aBv = new HashMap();
  public static int b;
  public static int c;
  private static int f = b.w;
  private Context g;
  private String h;
  private String i;
  private Handler j = new Handler(Looper.getMainLooper());
  
  static
  {
    b = b.v;
    c = b.v;
  }
  
  public d()
  {
    e();
  }
  
  public static void a(g paramG)
  {
    if ((aBv != null) && (paramG != null))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("click:");
      localStringBuilder.append(paramG.b());
      localStringBuilder.append(", campid:");
      localStringBuilder.append(paramG.a());
      e.c("probe", localStringBuilder.toString());
      aBv.put(paramG.b(), paramG);
    }
  }
  
  private static void a(final String paramString, boolean paramBoolean)
  {
    try
    {
      com.adywind.a.g.b.a.rY().a(new Runnable()
      {
        public void run()
        {
          try
          {
            Object localObject1 = j.b(d.rj().b(), b.f, "sys_package_name_removed", "");
            JSONObject localJSONObject;
            if (TextUtils.isEmpty((CharSequence)localObject1)) {
              localObject1 = new JSONObject();
            } else {
              try
              {
                localObject1 = new JSONObject((String)localObject1);
              }
              catch (JSONException localJSONException)
              {
                com.google.a.a.a.a.a.a.h(localJSONException);
                localJSONObject = new JSONObject();
              }
            }
            if (this.a)
            {
              localJSONObject.put(paramString, System.currentTimeMillis());
            }
            else
            {
              if (!localJSONObject.has(paramString)) {
                break label249;
              }
              localJSONObject.remove(paramString);
            }
            Object localObject2 = com.adywind.a.f.b.az(d.rj().b()).bi(d.rj().c());
            if (localObject2 != null)
            {
              Iterator localIterator = localJSONObject.keys();
              ArrayList localArrayList = new ArrayList();
              while (localIterator.hasNext())
              {
                String str = (String)localIterator.next();
                if (((Long)localJSONObject.get(str)).longValue() < System.currentTimeMillis() - ((com.adywind.a.f.a)localObject2).rF()) {
                  localArrayList.add(str);
                }
              }
              localObject2 = localArrayList.iterator();
              while (((Iterator)localObject2).hasNext()) {
                localJSONObject.remove((String)((Iterator)localObject2).next());
              }
            }
            j.a(d.rj().b(), b.f, "sys_package_name_removed", localJSONObject.toString());
            return;
            label249:
            return;
          }
          catch (Throwable localThrowable) {}
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
  
  public static boolean bb(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return (aBv != null) && (aBv.containsKey(paramString));
  }
  
  public static void c(String paramString)
  {
    if (aBu == null) {
      aBu = new ConcurrentHashMap();
    }
    if (!aBu.containsKey(paramString))
    {
      localH = new h();
      localH.a = paramString;
      localH.b = false;
      localH.c = System.currentTimeMillis();
      aBu.put(paramString, localH);
      return;
    }
    h localH = (h)aBu.get(paramString);
    localH.b = false;
    localH.c = System.currentTimeMillis();
    aBu.put(paramString, localH);
    a(paramString, false);
  }
  
  public static void d(String paramString)
  {
    if (aBu == null) {
      return;
    }
    if (aBu.containsKey(paramString))
    {
      Object localObject = (h)aBu.get(paramString);
      ((h)localObject).b = true;
      ((h)localObject).c = System.currentTimeMillis();
      aBu.put(paramString, localObject);
      if ((rm() != null) && (rm().containsKey(paramString)))
      {
        localObject = (g)rm().get(paramString);
        ((g)localObject).a(System.currentTimeMillis());
        a((g)localObject);
        rj().i();
      }
    }
    a(paramString, true);
  }
  
  public static boolean e(String paramString)
  {
    if ((aBu != null) && (aBu.containsKey(paramString))) {
      return true;
    }
    return bb(paramString);
  }
  
  public static boolean j()
  {
    return f == b.v;
  }
  
  public static d rj()
  {
    if (aBt == null) {
      try
      {
        aBt = new d();
      }
      finally {}
    }
    return aBt;
  }
  
  public static List<h> rl()
  {
    try
    {
      Object localObject1 = j.b(rj().b(), b.f, "sys_package_name_removed", "");
      boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
      if (!bool) {
        try
        {
          localObject1 = new JSONObject((String)localObject1);
          Object localObject2 = com.adywind.a.f.b.az(rj().b()).bi(rj().c());
          if (localObject2 != null)
          {
            Iterator localIterator = ((JSONObject)localObject1).keys();
            ArrayList localArrayList2 = new ArrayList();
            ArrayList localArrayList1 = new ArrayList();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              long l = ((Long)((JSONObject)localObject1).get(str)).longValue();
              if (l < System.currentTimeMillis() - ((com.adywind.a.f.a)localObject2).rF())
              {
                localArrayList2.add(str);
              }
              else
              {
                h localH = new h();
                localH.a = str;
                localH.c = l;
                localH.b = true;
                localArrayList1.add(localH);
              }
            }
            if (localArrayList2.size() > 0)
            {
              localObject2 = localArrayList2.iterator();
              while (((Iterator)localObject2).hasNext()) {
                ((JSONObject)localObject1).remove((String)((Iterator)localObject2).next());
              }
              j.a(rj().b(), b.f, "sys_package_name_removed", ((JSONObject)localObject1).toString());
            }
            return localArrayList1;
          }
        }
        catch (JSONException localJSONException)
        {
          com.google.a.a.a.a.a.a.h(localJSONException);
        }
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static HashMap<String, g> rm()
  {
    return aBv;
  }
  
  public void a(Context paramContext)
  {
    this.g = paramContext;
  }
  
  public void a(final Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null) {
      return;
    }
    if (j()) {
      return;
    }
    try
    {
      e.c("SDKContext", "init");
      a(paramContext.getApplicationContext());
      a(paramString1);
      b(paramString2);
      f = b.v;
      a(new Runnable()
      {
        public void run()
        {
          if (!com.adywind.api.d.aBo) {
            com.adywind.a.g.c.r(paramContext);
          }
        }
      }, 5000L);
      a(new Runnable()
      {
        public void run()
        {
          com.adywind.a.f.b.az(d.a(d.this)).a();
          com.adywind.a.g.a.a.a(d.a(d.this), d.b(d.this));
        }
      }, 2000L);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public void a(Runnable paramRunnable)
  {
    this.j.post(paramRunnable);
  }
  
  public void a(Runnable paramRunnable, long paramLong)
  {
    this.j.postDelayed(paramRunnable, paramLong);
  }
  
  public void a(String paramString)
  {
    this.h = paramString;
    j.a(this.g, b.f, "appid", paramString);
  }
  
  public void a(String paramString, int paramInt)
  {
    com.adywind.a.f.b.az(this.g).a();
    com.adywind.a.f.d.aA(this.g).i(paramString, paramInt);
  }
  
  public List<String> aA(boolean paramBoolean)
  {
    try
    {
      ConcurrentHashMap localConcurrentHashMap = aB(paramBoolean);
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localConcurrentHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (!((h)localConcurrentHashMap.get(str)).b) {
          localArrayList.add(str);
        }
      }
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  public ConcurrentHashMap<String, h> aB(boolean paramBoolean)
  {
    if ((!paramBoolean) && (aBu != null) && (aBu.size() > 0)) {
      return aBu;
    }
    ConcurrentHashMap localConcurrentHashMap = rk();
    Object localObject = rl();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        h localH = (h)((Iterator)localObject).next();
        if (!localConcurrentHashMap.containsKey(localH.a)) {
          localConcurrentHashMap.put(localH.a, localH);
        } else {
          a(localH.a, false);
        }
      }
      aBu = localConcurrentHashMap;
    }
    return aBu;
  }
  
  public Context b()
  {
    return this.g;
  }
  
  public void b(String paramString)
  {
    this.i = paramString;
    j.a(this.g, b.f, "appkey", paramString);
  }
  
  public String c()
  {
    if (TextUtils.isEmpty(this.h)) {
      this.h = j.b(this.g, b.f, "appid", "");
    }
    return this.h;
  }
  
  public String d()
  {
    if (TextUtils.isEmpty(this.i)) {
      this.i = j.b(this.g, b.f, "appkey", "");
    }
    return this.i;
  }
  
  public void e()
  {
    com.adywind.a.g.b.a.rY().a(new Runnable()
    {
      public void run()
      {
        try
        {
          e.c("SDKContext", "initGlobalCommonPara");
          d.aBv = c.av(d.a(d.this)).ba(d.b(d.this));
          new Thread(new Runnable()
          {
            public void run()
            {
              try
              {
                Object localObject2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                Object localObject1 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                localObject2 = ((Class)localObject2).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { d.a(d.this) });
                localObject1 = (String)((Class)localObject1).getMethod("getId", new Class[0]).invoke(localObject2, new Object[0]);
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append("adid:");
                ((StringBuilder)localObject2).append((String)localObject1);
                e.c("SDKContext", ((StringBuilder)localObject2).toString());
                com.adywind.a.g.c.a((String)localObject1);
                return;
              }
              catch (Exception localException1)
              {
                for (;;) {}
              }
              e.d("MPSDK", "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
              try
              {
                com.adywind.a.g.c.a(new d.a(d.this).aw(d.a(d.this)).a());
                return;
              }
              catch (Exception localException2)
              {
                for (;;) {}
              }
              e.d("MPSDK", "GET ADID FROM GOOGLE PLAY APP ERROR");
            }
          }).start();
          com.adywind.a.g.c.a(d.a(d.this));
          d.this.aB(true);
          d.this.h();
          return;
        }
        catch (Exception localException) {}
      }
    }, 500L);
  }
  
  public List<Long> g()
  {
    try
    {
      if ((aBv != null) && (aBv.size() > 0))
      {
        Iterator localIterator = aBv.keySet().iterator();
        ArrayList localArrayList = new ArrayList();
        while (localIterator.hasNext())
        {
          Object localObject = (String)localIterator.next();
          localObject = (g)aBv.get(localObject);
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(((g)localObject).a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            com.google.a.a.a.a.a.a.h(localNumberFormatException);
          }
        }
        return localArrayList;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public void h()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   5: ifnull +12 -> 17
    //   8: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   11: invokevirtual 382	java/util/HashMap:size	()I
    //   14: ifgt +20 -> 34
    //   17: aload_0
    //   18: getfield 91	com/adywind/a/a/d:g	Landroid/content/Context;
    //   21: invokestatic 398	com/adywind/a/a/c:av	(Landroid/content/Context;)Lcom/adywind/a/a/c;
    //   24: aload_0
    //   25: getfield 140	com/adywind/a/a/d:h	Ljava/lang/String;
    //   28: invokevirtual 402	com/adywind/a/a/c:ba	(Ljava/lang/String;)Ljava/util/HashMap;
    //   31: putstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   34: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   37: ifnull +299 -> 336
    //   40: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   43: invokevirtual 382	java/util/HashMap:size	()I
    //   46: ifne +6 -> 52
    //   49: goto +287 -> 336
    //   52: new 67	java/util/HashMap
    //   55: dup
    //   56: invokespecial 68	java/util/HashMap:<init>	()V
    //   59: astore_2
    //   60: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   63: invokevirtual 383	java/util/HashMap:keySet	()Ljava/util/Set;
    //   66: invokeinterface 362 1 0
    //   71: astore_3
    //   72: aload_3
    //   73: invokeinterface 249 1 0
    //   78: istore_1
    //   79: iload_1
    //   80: ifeq +100 -> 180
    //   83: aload_3
    //   84: invokeinterface 253 1 0
    //   89: checkcast 255	java/lang/String
    //   92: astore 4
    //   94: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   97: aload 4
    //   99: invokevirtual 184	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: checkcast 103	com/adywind/a/d/g
    //   105: astore 5
    //   107: getstatic 51	com/adywind/a/a/d:aBu	Ljava/util/concurrent/ConcurrentHashMap;
    //   110: ifnull -38 -> 72
    //   113: getstatic 51	com/adywind/a/a/d:aBu	Ljava/util/concurrent/ConcurrentHashMap;
    //   116: aload 4
    //   118: invokevirtual 154	java/util/concurrent/ConcurrentHashMap:containsKey	(Ljava/lang/Object;)Z
    //   121: ifeq -49 -> 72
    //   124: getstatic 51	com/adywind/a/a/d:aBu	Ljava/util/concurrent/ConcurrentHashMap;
    //   127: aload 4
    //   129: invokevirtual 176	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   132: checkcast 156	com/adywind/a/d/h
    //   135: getfield 162	com/adywind/a/d/h:b	Z
    //   138: ifne -66 -> 72
    //   141: aload 5
    //   143: invokestatic 168	java/lang/System:currentTimeMillis	()J
    //   146: invokevirtual 187	com/adywind/a/d/g:a	(J)V
    //   149: aload_2
    //   150: aload 4
    //   152: aload 5
    //   154: invokevirtual 124	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   157: pop
    //   158: goto -86 -> 72
    //   161: astore 4
    //   163: aload 4
    //   165: invokestatic 292	com/google/a/a/a/a/a/a:h	(Ljava/lang/Throwable;)V
    //   168: ldc_w 301
    //   171: ldc_w 404
    //   174: invokestatic 406	com/adywind/a/g/e:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   177: goto -105 -> 72
    //   180: aload_0
    //   181: getfield 91	com/adywind/a/a/d:g	Landroid/content/Context;
    //   184: invokestatic 231	com/adywind/a/f/b:az	(Landroid/content/Context;)Lcom/adywind/a/f/b;
    //   187: aload_0
    //   188: getfield 140	com/adywind/a/a/d:h	Ljava/lang/String;
    //   191: invokevirtual 237	com/adywind/a/f/b:bi	(Ljava/lang/String;)Lcom/adywind/a/f/a;
    //   194: astore_3
    //   195: aload_3
    //   196: ifnull +95 -> 291
    //   199: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   202: ifnull +89 -> 291
    //   205: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   208: invokevirtual 383	java/util/HashMap:keySet	()Ljava/util/Set;
    //   211: invokeinterface 362 1 0
    //   216: astore 4
    //   218: aload 4
    //   220: invokeinterface 249 1 0
    //   225: ifeq +66 -> 291
    //   228: aload 4
    //   230: invokeinterface 253 1 0
    //   235: checkcast 255	java/lang/String
    //   238: astore 5
    //   240: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   243: aload 5
    //   245: invokevirtual 184	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   248: checkcast 103	com/adywind/a/d/g
    //   251: astore 6
    //   253: aload_2
    //   254: aload 5
    //   256: invokevirtual 152	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   259: ifne -41 -> 218
    //   262: aload 6
    //   264: invokevirtual 408	com/adywind/a/d/g:c	()J
    //   267: invokestatic 168	java/lang/System:currentTimeMillis	()J
    //   270: aload_3
    //   271: invokevirtual 411	com/adywind/a/f/a:rG	()J
    //   274: lsub
    //   275: lcmp
    //   276: ifle -58 -> 218
    //   279: aload_2
    //   280: aload 5
    //   282: aload 6
    //   284: invokevirtual 124	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   287: pop
    //   288: goto -70 -> 218
    //   291: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   294: ifnull +9 -> 303
    //   297: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   300: invokevirtual 414	java/util/HashMap:clear	()V
    //   303: aload_2
    //   304: invokevirtual 382	java/util/HashMap:size	()I
    //   307: ifle +10 -> 317
    //   310: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   313: aload_2
    //   314: invokevirtual 418	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   317: aload_0
    //   318: getfield 91	com/adywind/a/a/d:g	Landroid/content/Context;
    //   321: invokestatic 398	com/adywind/a/a/c:av	(Landroid/content/Context;)Lcom/adywind/a/a/c;
    //   324: getstatic 70	com/adywind/a/a/d:aBv	Ljava/util/HashMap;
    //   327: invokevirtual 422	java/util/HashMap:values	()Ljava/util/Collection;
    //   330: invokevirtual 425	com/adywind/a/a/c:a	(Ljava/util/Collection;)V
    //   333: goto +11 -> 344
    //   336: aload_0
    //   337: monitorexit
    //   338: return
    //   339: astore_2
    //   340: aload_0
    //   341: monitorexit
    //   342: aload_2
    //   343: athrow
    //   344: aload_0
    //   345: monitorexit
    //   346: return
    //   347: astore_2
    //   348: goto -4 -> 344
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	351	0	this	d
    //   78	2	1	bool	boolean
    //   59	255	2	localHashMap	HashMap
    //   339	4	2	localObject1	Object
    //   347	1	2	localThrowable	Throwable
    //   71	200	3	localObject2	Object
    //   92	59	4	str	String
    //   161	3	4	localException	Exception
    //   216	13	4	localIterator	Iterator
    //   105	176	5	localObject3	Object
    //   251	32	6	localG	g
    // Exception table:
    //   from	to	target	type
    //   83	158	161	java/lang/Exception
    //   2	17	339	finally
    //   17	34	339	finally
    //   34	49	339	finally
    //   52	72	339	finally
    //   72	79	339	finally
    //   83	158	339	finally
    //   163	177	339	finally
    //   180	195	339	finally
    //   199	218	339	finally
    //   218	288	339	finally
    //   291	303	339	finally
    //   303	317	339	finally
    //   317	333	339	finally
    //   2	17	347	java/lang/Throwable
    //   17	34	347	java/lang/Throwable
    //   34	49	347	java/lang/Throwable
    //   52	72	347	java/lang/Throwable
    //   72	79	347	java/lang/Throwable
    //   83	158	347	java/lang/Throwable
    //   163	177	347	java/lang/Throwable
    //   180	195	347	java/lang/Throwable
    //   199	218	347	java/lang/Throwable
    //   218	288	347	java/lang/Throwable
    //   291	303	347	java/lang/Throwable
    //   303	317	347	java/lang/Throwable
    //   317	333	347	java/lang/Throwable
  }
  
  public void i()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" ");
    localStringBuilder.append(aBv.size());
    e.c("saveAppInfo-----------------addInstallApp---------------->", localStringBuilder.toString());
    try
    {
      if ((aBv != null) && (aBv.size() > 0)) {
        c.av(this.g).a(aBv.values());
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public ConcurrentHashMap<String, h> rk()
  {
    if (aBu == null) {
      aBu = new ConcurrentHashMap();
    }
    try
    {
      Iterator localIterator = this.g.getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0)
        {
          h localH = new h();
          localH.a = localPackageInfo.packageName;
          localH.b = false;
          localH.c = System.currentTimeMillis();
          aBu.put(localPackageInfo.packageName, localH);
        }
      }
      return aBu;
    }
    catch (Throwable localThrowable)
    {
      com.google.a.a.a.a.a.a.h(localThrowable);
    }
  }
  
  public class a
  {
    public a() {}
    
    /* Error */
    public a aw(Context paramContext)
    {
      // Byte code:
      //   0: invokestatic 35	android/os/Looper:myLooper	()Landroid/os/Looper;
      //   3: invokestatic 38	android/os/Looper:getMainLooper	()Landroid/os/Looper;
      //   6: if_acmpne +13 -> 19
      //   9: new 40	java/lang/IllegalStateException
      //   12: dup
      //   13: ldc 42
      //   15: invokespecial 45	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   18: athrow
      //   19: aload_1
      //   20: invokevirtual 51	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   23: getstatic 56	com/adywind/a/g/h$a:a	Ljava/lang/String;
      //   26: iconst_0
      //   27: invokevirtual 62	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   30: pop
      //   31: new 11	com/adywind/a/a/d$a$b
      //   34: dup
      //   35: aload_0
      //   36: aconst_null
      //   37: invokespecial 65	com/adywind/a/a/d$a$b:<init>	(Lcom/adywind/a/a/d$a;Lcom/adywind/a/a/d$1;)V
      //   40: astore_2
      //   41: new 67	android/content/Intent
      //   44: dup
      //   45: ldc 69
      //   47: invokespecial 70	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   50: astore_3
      //   51: aload_3
      //   52: ldc 72
      //   54: invokevirtual 76	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   57: pop
      //   58: aload_1
      //   59: aload_3
      //   60: aload_2
      //   61: iconst_1
      //   62: invokevirtual 80	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
      //   65: ifeq +55 -> 120
      //   68: new 14	com/adywind/a/a/d$a$c
      //   71: dup
      //   72: aload_0
      //   73: aload_2
      //   74: invokevirtual 83	com/adywind/a/a/d$a$b:a	()Landroid/os/IBinder;
      //   77: invokespecial 86	com/adywind/a/a/d$a$c:<init>	(Lcom/adywind/a/a/d$a;Landroid/os/IBinder;)V
      //   80: astore_3
      //   81: new 9	com/adywind/a/a/d$a$a
      //   84: dup
      //   85: aload_0
      //   86: aload_3
      //   87: invokevirtual 89	com/adywind/a/a/d$a$c:a	()Ljava/lang/String;
      //   90: aload_3
      //   91: iconst_1
      //   92: invokevirtual 92	com/adywind/a/a/d$a$c:a	(Z)Z
      //   95: invokespecial 95	com/adywind/a/a/d$a$a:<init>	(Lcom/adywind/a/a/d$a;Ljava/lang/String;Z)V
      //   98: astore_3
      //   99: aload_1
      //   100: aload_2
      //   101: invokevirtual 99	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   104: aload_3
      //   105: areturn
      //   106: astore_3
      //   107: goto +6 -> 113
      //   110: astore_3
      //   111: aload_3
      //   112: athrow
      //   113: aload_1
      //   114: aload_2
      //   115: invokevirtual 99	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   118: aload_3
      //   119: athrow
      //   120: new 101	java/io/IOException
      //   123: dup
      //   124: ldc 103
      //   126: invokespecial 104	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   129: athrow
      //   130: astore_1
      //   131: aload_1
      //   132: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	133	0	this	a
      //   0	133	1	paramContext	Context
      //   40	75	2	localB	b
      //   50	55	3	localObject1	Object
      //   106	1	3	localObject2	Object
      //   110	9	3	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   68	99	106	finally
      //   111	113	106	finally
      //   68	99	110	java/lang/Exception
      //   19	31	130	java/lang/Exception
    }
    
    public final class a
    {
      private final String b;
      private final boolean c;
      
      a(String paramString, boolean paramBoolean)
      {
        this.b = paramString;
        this.c = paramBoolean;
      }
      
      public String a()
      {
        return this.b;
      }
    }
    
    private final class b
      implements ServiceConnection
    {
      boolean a = false;
      private final LinkedBlockingQueue<IBinder> aBA = new LinkedBlockingQueue(1);
      
      private b() {}
      
      public IBinder a()
      {
        if (this.a) {
          throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder)this.aBA.take();
      }
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        try
        {
          this.aBA.put(paramIBinder);
          return;
        }
        catch (InterruptedException paramComponentName) {}
      }
      
      public void onServiceDisconnected(ComponentName paramComponentName) {}
    }
    
    private final class c
      implements IInterface
    {
      private IBinder b;
      
      public c(IBinder paramIBinder)
      {
        this.b = paramIBinder;
      }
      
      public String a()
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          this.b.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean a(boolean paramBoolean)
      {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
      }
      
      public IBinder asBinder()
      {
        return this.b;
      }
    }
  }
}
