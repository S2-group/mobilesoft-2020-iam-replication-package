package com.mpcore.common.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
import com.mpcore.common.e.g;
import com.mpcore.common.i.j;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public static ConcurrentHashMap<String, com.mpcore.common.e.h> a = new ConcurrentHashMap();
  public static int b = b.e;
  public static int c = b.e;
  public static HashMap<String, g> d = new HashMap();
  private static d e;
  private static int f = b.f;
  private Context g;
  private String h;
  private String i;
  private Handler j = new Handler(Looper.getMainLooper());
  
  public d()
  {
    com.mpcore.common.i.b.a.a().a(new Runnable()
    {
      public final void run()
      {
        try
        {
          com.mpcore.common.i.e.c("SDKContext", "initGlobalCommonPara");
          d.d = c.a(d.a(d.this)).c(d.b(d.this));
          new Thread(new Runnable()
          {
            public final void run()
            {
              try
              {
                Object localObject2 = Class.forName("com.google.android.gms.ads.a.a");
                Object localObject1 = Class.forName("com.google.android.gms.ads.a.a$a");
                localObject2 = ((Class)localObject2).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { d.a(d.this) });
                localObject1 = (String)((Class)localObject1).getMethod("getId", new Class[0]).invoke(localObject2, new Object[0]);
                localObject2 = new StringBuilder("adid:");
                ((StringBuilder)localObject2).append((String)localObject1);
                com.mpcore.common.i.e.c("SDKContext", ((StringBuilder)localObject2).toString());
                com.mpcore.common.i.c.a((String)localObject1);
                return;
              }
              catch (Exception localException1)
              {
                for (;;) {}
              }
              com.mpcore.common.i.e.d("MPSDK", "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
              try
              {
                com.mpcore.common.i.c.a(new d.a(d.this).a(d.a(d.this)).a());
                return;
              }
              catch (Exception localException2)
              {
                for (;;) {}
              }
              com.mpcore.common.i.e.d("MPSDK", "GET ADID FROM GOOGLE PLAY APP ERROR");
            }
          }).start();
          com.mpcore.common.i.c.a(d.a(d.this));
          d.this.a(true);
          d.this.g();
          return;
        }
        catch (Exception localException) {}
      }
    }, 500L);
  }
  
  public static d a()
  {
    if (e == null) {
      try
      {
        e = new d();
      }
      finally {}
    }
    return e;
  }
  
  private static void a(Context paramContext, com.mpcore.common.e.a paramA, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("CMD", "GO_DOWNLOAD");
    localIntent.putExtra("cid", paramA.a());
    localIntent.putExtra("ctitle", paramA.c());
    localIntent.putExtra("cpackage", paramA.b());
    localIntent.putExtra("cicon", paramA.g());
    localIntent.putExtra("tracking_download_start", paramA.K());
    localIntent.putExtra("tracking_download_finish", paramA.L());
    localIntent.putExtra("tracking_download_install", paramA.M());
    localIntent.putExtra("curl", paramString);
    if (paramBoolean1) {
      paramA = "1";
    } else {
      paramA = "0";
    }
    localIntent.putExtra("needNotify", paramA);
    if (paramBoolean2) {
      paramA = "1";
    } else {
      paramA = "0";
    }
    localIntent.putExtra("isIgnoreNetwork", paramA);
    if (paramBoolean3) {
      paramA = "1";
    } else {
      paramA = "0";
    }
    localIntent.putExtra("showInstall", paramA);
    com.mpcore.common.i.h.a(paramContext, "GO_DOWNLOAD", localIntent);
  }
  
  public static void a(g paramG)
  {
    if ((d != null) && (paramG != null))
    {
      StringBuilder localStringBuilder = new StringBuilder("click:");
      localStringBuilder.append(paramG.b());
      localStringBuilder.append(", campid:");
      localStringBuilder.append(paramG.a());
      com.mpcore.common.i.e.c("probe", localStringBuilder.toString());
      d.put(paramG.b(), paramG);
    }
  }
  
  public static void a(String paramString)
  {
    if (a == null) {
      a = new ConcurrentHashMap();
    }
    if (!a.containsKey(paramString))
    {
      localH = new com.mpcore.common.e.h();
      localH.a = paramString;
      localH.b = false;
      localH.c = System.currentTimeMillis();
      a.put(paramString, localH);
      return;
    }
    com.mpcore.common.e.h localH = (com.mpcore.common.e.h)a.get(paramString);
    localH.b = false;
    localH.c = System.currentTimeMillis();
    a.put(paramString, localH);
    a(paramString, false);
  }
  
  private static void a(final String paramString, boolean paramBoolean)
  {
    try
    {
      com.mpcore.common.i.b.a.a().b(new Runnable()
      {
        public final void run()
        {
          try
          {
            Object localObject1 = j.b(d.a().b(), b.a, "sys_package_name_removed", "");
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
                localJSONException.printStackTrace();
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
                break label248;
              }
              localJSONObject.remove(paramString);
            }
            Object localObject2 = com.mpcore.common.g.b.a(d.a().b()).a(d.a().c());
            if (localObject2 != null)
            {
              Iterator localIterator = localJSONObject.keys();
              ArrayList localArrayList = new ArrayList();
              while (localIterator.hasNext())
              {
                String str = (String)localIterator.next();
                if (((Long)localJSONObject.get(str)).longValue() < System.currentTimeMillis() - ((com.mpcore.common.g.a)localObject2).av()) {
                  localArrayList.add(str);
                }
              }
              localObject2 = localArrayList.iterator();
              while (((Iterator)localObject2).hasNext()) {
                localJSONObject.remove((String)((Iterator)localObject2).next());
              }
            }
            j.a(d.a().b(), b.a, "sys_package_name_removed", localJSONObject.toString());
            label248:
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
  
  public static void b(String paramString)
  {
    if (a == null) {
      return;
    }
    if (a.containsKey(paramString))
    {
      Object localObject = (com.mpcore.common.e.h)a.get(paramString);
      ((com.mpcore.common.e.h)localObject).b = true;
      ((com.mpcore.common.e.h)localObject).c = System.currentTimeMillis();
      a.put(paramString, localObject);
      if ((d != null) && (d.containsKey(paramString)))
      {
        localObject = (g)d.get(paramString);
        ((g)localObject).a(System.currentTimeMillis());
        a((g)localObject);
        a().h();
      }
    }
    a(paramString, true);
  }
  
  public static boolean c(String paramString)
  {
    if ((a != null) && (a.containsKey(paramString))) {
      return !((com.mpcore.common.e.h)a.get(paramString)).b;
    }
    return (!TextUtils.isEmpty(paramString)) && (d != null) && (d.containsKey(paramString));
  }
  
  public static List<Long> f()
  {
    try
    {
      if ((d != null) && (d.size() > 0))
      {
        Iterator localIterator = d.keySet().iterator();
        ArrayList localArrayList = new ArrayList();
        while (localIterator.hasNext())
        {
          Object localObject = (String)localIterator.next();
          localObject = (g)d.get(localObject);
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(((g)localObject).a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
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
  
  public static boolean i()
  {
    return f == b.e;
  }
  
  public static HashMap<String, g> j()
  {
    return d;
  }
  
  private ConcurrentHashMap<String, com.mpcore.common.e.h> k()
  {
    if (a == null) {
      a = new ConcurrentHashMap();
    }
    for (;;)
    {
      try
      {
        localPackageManager = this.g.getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
        if (localIterator.hasNext())
        {
          localPackageInfo = (PackageInfo)localIterator.next();
          if ((localPackageInfo.applicationInfo.flags & 0x1) > 0) {
            continue;
          }
          localH = new com.mpcore.common.e.h();
          localH.a = localPackageInfo.packageName;
        }
      }
      catch (Throwable localThrowable)
      {
        PackageManager localPackageManager;
        PackageInfo localPackageInfo;
        com.mpcore.common.e.h localH;
        localThrowable.printStackTrace();
      }
      try
      {
        localH.d = localPackageManager.getInstallerPackageName(localH.a);
        if ((localH.d != null) && (localH.d.length() > 0)) {
          continue;
        }
        localH.d = "unknown_installer";
      }
      catch (Exception localException)
      {
        continue;
      }
      localH.d = "unknown_installer";
      localH.b = false;
      localH.c = System.currentTimeMillis();
      a.put(localPackageInfo.packageName, localH);
    }
    return a;
  }
  
  private static List<com.mpcore.common.e.h> l()
  {
    try
    {
      Object localObject1 = j.b(a().g, b.a, "sys_package_name_removed", "");
      boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
      if (!bool) {
        try
        {
          localObject1 = new JSONObject((String)localObject1);
          Object localObject2 = com.mpcore.common.g.b.a(a().g).a(a().c());
          if (localObject2 != null)
          {
            Iterator localIterator = ((JSONObject)localObject1).keys();
            ArrayList localArrayList2 = new ArrayList();
            ArrayList localArrayList1 = new ArrayList();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              long l = ((Long)((JSONObject)localObject1).get(str)).longValue();
              if (l < System.currentTimeMillis() - ((com.mpcore.common.g.a)localObject2).av())
              {
                localArrayList2.add(str);
              }
              else
              {
                com.mpcore.common.e.h localH = new com.mpcore.common.e.h();
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
              j.a(a().g, b.a, "sys_package_name_removed", ((JSONObject)localObject1).toString());
            }
            return localArrayList1;
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
      for (;;) {}
    }
    return null;
  }
  
  public final ConcurrentHashMap<String, com.mpcore.common.e.h> a(boolean paramBoolean)
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
        com.mpcore.common.e.h localH = (com.mpcore.common.e.h)((Iterator)localObject).next();
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
    if (i()) {
      return;
    }
    this.g = paramContext.getApplicationContext();
    this.h = paramString1;
    j.a(this.g, b.a, "appid", paramString1);
    this.i = paramString2;
    j.a(this.g, b.a, "appkey", paramString2);
    f = b.e;
    com.mpcore.common.i.b.a.a().b(new Runnable()
    {
      public final void run()
      {
        try
        {
          com.mpcore.common.i.e.c("SDKContext", "init");
          d.this.a(new Runnable()
          {
            public final void run()
            {
              if (!com.mobpower.a.a.e.b) {
                com.mpcore.common.i.c.l(d.1.this.a);
              }
            }
          }, 5000L);
          d.this.a(new Runnable()
          {
            public final void run()
            {
              com.mpcore.common.g.b.a(d.a(d.this)).a();
              com.mpcore.common.i.a.a.a(d.a(d.this), d.b(d.this));
            }
          }, 2000L);
          return;
        }
        catch (Exception localException) {}
      }
    });
  }
  
  public final void a(com.mpcore.common.e.a paramA, String paramString)
  {
    if (paramA != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      if ((com.mobpower.a.a.e.a) && (com.mpcore.a.d.a.a(this.g)))
      {
        com.mpcore.common.i.e.b("startDonwload-->", "开始下载");
        a(this.g, paramA, paramString, true, false, true);
        return;
      }
      com.mpcore.common.i.e.b("startDonwload-->", "浏览器下载");
      com.mpcore.common.i.h.a(this.g, paramString);
    }
  }
  
  public final void a(com.mpcore.common.e.a paramA, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramA != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      if ((com.mobpower.a.a.e.a) && (com.mpcore.a.d.a.a(this.g)))
      {
        com.mpcore.common.i.e.b("startDonwload-->", "开始下载");
        a(this.g, paramA, paramString, false, paramBoolean1, paramBoolean2);
        return;
      }
      com.mpcore.common.i.e.b("startDonwload-->", "浏览器下载");
      com.mpcore.common.i.h.a(this.g, paramString);
    }
  }
  
  public final void a(Runnable paramRunnable)
  {
    this.j.post(paramRunnable);
  }
  
  public final void a(Runnable paramRunnable, long paramLong)
  {
    this.j.postDelayed(paramRunnable, paramLong);
  }
  
  public final void a(String paramString, int paramInt)
  {
    com.mpcore.common.g.b.a(this.g).a();
    com.mpcore.common.g.d.a(this.g).b(paramString, paramInt);
  }
  
  public final Context b()
  {
    return this.g;
  }
  
  public final String c()
  {
    if (TextUtils.isEmpty(this.h)) {
      this.h = j.b(this.g, b.a, "appid", "");
    }
    return this.h;
  }
  
  public final String d()
  {
    if (TextUtils.isEmpty(this.i)) {
      this.i = j.b(this.g, b.a, "appkey", "");
    }
    return this.i;
  }
  
  public final Map<String, com.mpcore.common.e.h> e()
  {
    try
    {
      ConcurrentHashMap localConcurrentHashMap = a(false);
      HashMap localHashMap = new HashMap();
      Iterator localIterator = localConcurrentHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        com.mpcore.common.e.h localH = (com.mpcore.common.e.h)localConcurrentHashMap.get(str);
        if (!localH.b) {
          localHashMap.put(str, localH);
        }
      }
      return localHashMap;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public final void g()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   5: ifnull +12 -> 17
    //   8: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   11: invokevirtual 263	java/util/HashMap:size	()I
    //   14: ifgt +20 -> 34
    //   17: aload_0
    //   18: getfield 99	com/mpcore/common/a/d:g	Landroid/content/Context;
    //   21: invokestatic 482	com/mpcore/common/a/c:a	(Landroid/content/Context;)Lcom/mpcore/common/a/c;
    //   24: aload_0
    //   25: getfield 236	com/mpcore/common/a/d:h	Ljava/lang/String;
    //   28: invokevirtual 485	com/mpcore/common/a/c:c	(Ljava/lang/String;)Ljava/util/HashMap;
    //   31: putstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   34: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   37: ifnull +299 -> 336
    //   40: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   43: invokevirtual 263	java/util/HashMap:size	()I
    //   46: ifne +6 -> 52
    //   49: goto +287 -> 336
    //   52: new 65	java/util/HashMap
    //   55: dup
    //   56: invokespecial 66	java/util/HashMap:<init>	()V
    //   59: astore_2
    //   60: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   63: invokevirtual 267	java/util/HashMap:keySet	()Ljava/util/Set;
    //   66: invokeinterface 273 1 0
    //   71: astore_3
    //   72: aload_3
    //   73: invokeinterface 282 1 0
    //   78: istore_1
    //   79: iload_1
    //   80: ifeq +100 -> 180
    //   83: aload_3
    //   84: invokeinterface 286 1 0
    //   89: checkcast 288	java/lang/String
    //   92: astore 4
    //   94: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   97: aload 4
    //   99: invokevirtual 238	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: checkcast 176	com/mpcore/common/e/g
    //   105: astore 5
    //   107: getstatic 52	com/mpcore/common/a/d:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   110: ifnull -38 -> 72
    //   113: getstatic 52	com/mpcore/common/a/d:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   116: aload 4
    //   118: invokevirtual 202	java/util/concurrent/ConcurrentHashMap:containsKey	(Ljava/lang/Object;)Z
    //   121: ifeq -49 -> 72
    //   124: getstatic 52	com/mpcore/common/a/d:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   127: aload 4
    //   129: invokevirtual 224	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   132: checkcast 204	com/mpcore/common/e/h
    //   135: getfield 210	com/mpcore/common/e/h:b	Z
    //   138: ifne -66 -> 72
    //   141: aload 5
    //   143: invokestatic 216	java/lang/System:currentTimeMillis	()J
    //   146: invokevirtual 241	com/mpcore/common/e/g:a	(J)V
    //   149: aload_2
    //   150: aload 4
    //   152: aload 5
    //   154: invokevirtual 198	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   157: pop
    //   158: goto -86 -> 72
    //   161: astore 4
    //   163: aload 4
    //   165: invokevirtual 486	java/lang/Exception:printStackTrace	()V
    //   168: ldc_w 488
    //   171: ldc_w 490
    //   174: invokestatic 492	com/mpcore/common/i/e:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   177: goto -105 -> 72
    //   180: aload_0
    //   181: getfield 99	com/mpcore/common/a/d:g	Landroid/content/Context;
    //   184: invokestatic 375	com/mpcore/common/g/b:a	(Landroid/content/Context;)Lcom/mpcore/common/g/b;
    //   187: aload_0
    //   188: getfield 236	com/mpcore/common/a/d:h	Ljava/lang/String;
    //   191: invokevirtual 379	com/mpcore/common/g/b:a	(Ljava/lang/String;)Lcom/mpcore/common/g/a;
    //   194: astore_3
    //   195: aload_3
    //   196: ifnull +95 -> 291
    //   199: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   202: ifnull +89 -> 291
    //   205: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   208: invokevirtual 267	java/util/HashMap:keySet	()Ljava/util/Set;
    //   211: invokeinterface 273 1 0
    //   216: astore 4
    //   218: aload 4
    //   220: invokeinterface 282 1 0
    //   225: ifeq +66 -> 291
    //   228: aload 4
    //   230: invokeinterface 286 1 0
    //   235: checkcast 288	java/lang/String
    //   238: astore 5
    //   240: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   243: aload 5
    //   245: invokevirtual 238	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   248: checkcast 176	com/mpcore/common/e/g
    //   251: astore 6
    //   253: aload_2
    //   254: aload 5
    //   256: invokevirtual 237	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   259: ifne -41 -> 218
    //   262: aload 6
    //   264: invokevirtual 494	com/mpcore/common/e/g:c	()J
    //   267: invokestatic 216	java/lang/System:currentTimeMillis	()J
    //   270: aload_3
    //   271: invokevirtual 497	com/mpcore/common/g/a:ax	()J
    //   274: lsub
    //   275: lcmp
    //   276: ifle -58 -> 218
    //   279: aload_2
    //   280: aload 5
    //   282: aload 6
    //   284: invokevirtual 198	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   287: pop
    //   288: goto -70 -> 218
    //   291: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   294: ifnull +9 -> 303
    //   297: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   300: invokevirtual 500	java/util/HashMap:clear	()V
    //   303: aload_2
    //   304: invokevirtual 263	java/util/HashMap:size	()I
    //   307: ifle +10 -> 317
    //   310: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   313: aload_2
    //   314: invokevirtual 504	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   317: aload_0
    //   318: getfield 99	com/mpcore/common/a/d:g	Landroid/content/Context;
    //   321: invokestatic 482	com/mpcore/common/a/c:a	(Landroid/content/Context;)Lcom/mpcore/common/a/c;
    //   324: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   327: invokevirtual 508	java/util/HashMap:values	()Ljava/util/Collection;
    //   330: invokevirtual 511	com/mpcore/common/a/c:a	(Ljava/util/Collection;)V
    //   333: aload_0
    //   334: monitorexit
    //   335: return
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
  
  public final void h()
  {
    StringBuilder localStringBuilder = new StringBuilder(" ");
    localStringBuilder.append(d.size());
    com.mpcore.common.i.e.c("saveAppInfo-----------------addInstallApp---------------->", localStringBuilder.toString());
    try
    {
      if ((d != null) && (d.size() > 0)) {
        c.a(this.g).a(d.values());
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final class a
  {
    public a() {}
    
    /* Error */
    public final a a(Context paramContext)
    {
      // Byte code:
      //   0: invokestatic 33	android/os/Looper:myLooper	()Landroid/os/Looper;
      //   3: invokestatic 36	android/os/Looper:getMainLooper	()Landroid/os/Looper;
      //   6: if_acmpne +13 -> 19
      //   9: new 38	java/lang/IllegalStateException
      //   12: dup
      //   13: ldc 40
      //   15: invokespecial 43	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   18: athrow
      //   19: aload_1
      //   20: invokevirtual 49	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   23: getstatic 54	com/mpcore/common/i/h$a:a	Ljava/lang/String;
      //   26: iconst_0
      //   27: invokevirtual 60	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   30: pop
      //   31: new 11	com/mpcore/common/a/d$a$b
      //   34: dup
      //   35: aload_0
      //   36: iconst_0
      //   37: invokespecial 63	com/mpcore/common/a/d$a$b:<init>	(Lcom/mpcore/common/a/d$a;B)V
      //   40: astore_2
      //   41: new 65	android/content/Intent
      //   44: dup
      //   45: ldc 67
      //   47: invokespecial 68	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   50: astore_3
      //   51: aload_3
      //   52: ldc 70
      //   54: invokevirtual 74	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   57: pop
      //   58: aload_1
      //   59: aload_3
      //   60: aload_2
      //   61: iconst_1
      //   62: invokevirtual 78	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
      //   65: ifeq +54 -> 119
      //   68: new 14	com/mpcore/common/a/d$a$c
      //   71: dup
      //   72: aload_0
      //   73: aload_2
      //   74: invokevirtual 81	com/mpcore/common/a/d$a$b:a	()Landroid/os/IBinder;
      //   77: invokespecial 84	com/mpcore/common/a/d$a$c:<init>	(Lcom/mpcore/common/a/d$a;Landroid/os/IBinder;)V
      //   80: astore_3
      //   81: new 9	com/mpcore/common/a/d$a$a
      //   84: dup
      //   85: aload_0
      //   86: aload_3
      //   87: invokevirtual 87	com/mpcore/common/a/d$a$c:a	()Ljava/lang/String;
      //   90: aload_3
      //   91: invokevirtual 90	com/mpcore/common/a/d$a$c:b	()Z
      //   94: invokespecial 93	com/mpcore/common/a/d$a$a:<init>	(Lcom/mpcore/common/a/d$a;Ljava/lang/String;Z)V
      //   97: astore_3
      //   98: aload_1
      //   99: aload_2
      //   100: invokevirtual 97	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   103: aload_3
      //   104: areturn
      //   105: astore_3
      //   106: goto +6 -> 112
      //   109: astore_3
      //   110: aload_3
      //   111: athrow
      //   112: aload_1
      //   113: aload_2
      //   114: invokevirtual 97	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   117: aload_3
      //   118: athrow
      //   119: new 99	java/io/IOException
      //   122: dup
      //   123: ldc 101
      //   125: invokespecial 102	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   128: athrow
      //   129: astore_1
      //   130: aload_1
      //   131: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	132	0	this	a
      //   0	132	1	paramContext	Context
      //   40	74	2	localB	b
      //   50	54	3	localObject1	Object
      //   105	1	3	localObject2	Object
      //   109	9	3	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   68	98	105	finally
      //   110	112	105	finally
      //   68	98	109	java/lang/Exception
      //   19	31	129	java/lang/Exception
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
      
      public final String a()
      {
        return this.b;
      }
    }
    
    private final class b
      implements ServiceConnection
    {
      boolean a = false;
      private final LinkedBlockingQueue<IBinder> c = new LinkedBlockingQueue(1);
      
      private b() {}
      
      public final IBinder a()
      {
        if (this.a) {
          throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder)this.c.take();
      }
      
      public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        try
        {
          this.c.put(paramIBinder);
          return;
        }
        catch (InterruptedException paramComponentName) {}
      }
      
      public final void onServiceDisconnected(ComponentName paramComponentName) {}
    }
    
    private final class c
      implements IInterface
    {
      private IBinder b;
      
      public c(IBinder paramIBinder)
      {
        this.b = paramIBinder;
      }
      
      public final String a()
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
      
      public final IBinder asBinder()
      {
        return this.b;
      }
      
      public final boolean b()
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          boolean bool = true;
          localParcel1.writeInt(1);
          this.b.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i == 0) {
            bool = false;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}
