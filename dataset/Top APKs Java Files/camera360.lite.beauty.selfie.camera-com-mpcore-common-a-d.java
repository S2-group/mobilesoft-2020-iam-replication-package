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
import com.mpcore.common.i.e;
import com.mpcore.common.i.h.a;
import com.mpcore.common.i.j;
import com.power.PowerService;
import java.io.IOException;
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
          e.c("SDKContext", "initGlobalCommonPara");
          d.d = c.a(d.a(d.this)).c(d.b(d.this));
          new Thread(new Runnable()
          {
            public final void run()
            {
              try
              {
                Object localObject2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                Object localObject1 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                localObject2 = ((Class)localObject2).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { d.a(d.this) });
                localObject1 = (String)((Class)localObject1).getMethod("getId", new Class[0]).invoke(localObject2, new Object[0]);
                e.c("SDKContext", "adid:" + (String)localObject1);
                com.mpcore.common.i.c.a((String)localObject1);
                return;
              }
              catch (Exception localException1)
              {
                e.d("MPSDK", "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
                try
                {
                  com.mpcore.common.i.c.a(new d.a(d.this).a(d.a(d.this)).a());
                  return;
                }
                catch (Exception localException2)
                {
                  e.d("MPSDK", "GET ADID FROM GOOGLE PLAY APP ERROR");
                }
              }
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
    if (e == null) {}
    try
    {
      e = new d();
      return e;
    }
    finally {}
  }
  
  private static void a(Context paramContext, com.mpcore.common.e.a paramA, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Intent localIntent = new Intent(paramContext, PowerService.class);
    localIntent.putExtra("CMD", "GO_DOWNLOAD");
    localIntent.putExtra("cid", paramA.a());
    localIntent.putExtra("ctitle", paramA.c());
    localIntent.putExtra("cpackage", paramA.b());
    localIntent.putExtra("cicon", paramA.g());
    localIntent.putExtra("tracking_download_start", paramA.H());
    localIntent.putExtra("tracking_download_finish", paramA.I());
    localIntent.putExtra("tracking_download_install", paramA.J());
    localIntent.putExtra("curl", paramString);
    if (paramBoolean1)
    {
      paramA = "1";
      localIntent.putExtra("needNotify", paramA);
      if (!paramBoolean2) {
        break label179;
      }
      paramA = "1";
      label139:
      localIntent.putExtra("isIgnoreNetwork", paramA);
      if (!paramBoolean3) {
        break label185;
      }
    }
    label179:
    label185:
    for (paramA = "1";; paramA = "0")
    {
      localIntent.putExtra("showInstall", paramA);
      paramContext.startService(localIntent);
      return;
      paramA = "0";
      break;
      paramA = "0";
      break label139;
    }
  }
  
  public static void a(g paramG)
  {
    if ((d != null) && (paramG != null))
    {
      e.c("probe", "click:" + paramG.b() + ", campid:" + paramG.a());
      d.put(paramG.b(), paramG);
    }
  }
  
  private static void a(final String paramString, boolean paramBoolean)
  {
    try
    {
      com.mpcore.common.i.b.a.a().b(new Runnable()
      {
        public final void run()
        {
          ArrayList localArrayList;
          JSONObject localJSONObject2;
          for (;;)
          {
            try
            {
              Object localObject1 = j.b(d.a().b(), b.a, "sys_package_name_removed", "");
              if (TextUtils.isEmpty((CharSequence)localObject1))
              {
                localObject1 = new JSONObject();
                if (!this.a) {
                  break label175;
                }
                ((JSONObject)localObject1).put(paramString, System.currentTimeMillis());
                localObject2 = com.mpcore.common.g.b.a(d.a().b()).a(d.a().c());
                if (localObject2 == null) {
                  break label232;
                }
                Iterator localIterator = ((JSONObject)localObject1).keys();
                localArrayList = new ArrayList();
                if (!localIterator.hasNext()) {
                  break;
                }
                String str = (String)localIterator.next();
                if (((Long)((JSONObject)localObject1).get(str)).longValue() >= System.currentTimeMillis() - ((com.mpcore.common.g.a)localObject2).ao()) {
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
          j.a(d.a().b(), b.a, "sys_package_name_removed", localJSONObject2.toString());
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
  
  public static void c(String paramString)
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
  
  public static void d(String paramString)
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
  
  public static boolean e(String paramString)
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
        localObject = (g)d.get(localObject);
        try
        {
          localArrayList.add(Long.valueOf(Long.parseLong(((g)localObject).a())));
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
        localThrowable.printStackTrace();
      }
      try
      {
        localH.d = localPackageManager.getInstallerPackageName(localH.a);
        if ((localH.d == null) || (localH.d.length() <= 0)) {
          localH.d = "unknown_installer";
        }
      }
      catch (Exception localException)
      {
        localH.d = "unknown_installer";
        continue;
      }
      localH.b = false;
      localH.c = System.currentTimeMillis();
      a.put(localPackageInfo.packageName, localH);
    }
    return a;
  }
  
  private static List<com.mpcore.common.e.h> l()
  {
    for (;;)
    {
      try
      {
        Object localObject1 = j.b(a().g, b.a, "sys_package_name_removed", "");
        boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
        if (!bool) {
          try
          {
            localObject1 = new JSONObject((String)localObject1);
            localObject2 = com.mpcore.common.g.b.a(a().g).a(a().c());
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
              if (l < System.currentTimeMillis() - ((com.mpcore.common.g.a)localObject2).ao())
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
        com.mpcore.common.e.h localH;
        continue;
      }
      localH = new com.mpcore.common.e.h();
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
      j.a(a().g, b.a, "sys_package_name_removed", localJSONException.toString());
    }
    return localArrayList1;
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
  
  public final void a(final Context paramContext, final String paramString1, final String paramString2)
  {
    if (paramContext == null) {}
    while (i()) {
      return;
    }
    com.mpcore.common.i.b.a.a().b(new Runnable()
    {
      public final void run()
      {
        try
        {
          e.c("SDKContext", "init");
          d.this.a(paramContext.getApplicationContext());
          d.this.a(paramString1);
          d.this.b(paramString2);
          d.a(b.e);
          d.this.a(new Runnable()
          {
            public final void run()
            {
              if (!com.mobpower.core.a.d.b) {
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
    if ((paramA == null) || (TextUtils.isEmpty(paramString))) {
      return;
    }
    if ((com.mobpower.core.a.d.a) && (com.mpcore.a.d.a.a(this.g)))
    {
      e.b("startDonwload-->", "开始下载");
      a(this.g, paramA, paramString, true, false, true);
      return;
    }
    e.b("startDonwload-->", "浏览器下载");
    com.mpcore.common.i.h.a(this.g, paramString);
  }
  
  public final void a(com.mpcore.common.e.a paramA, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramA == null) || (TextUtils.isEmpty(paramString))) {
      return;
    }
    if ((com.mobpower.core.a.d.a) && (com.mpcore.a.d.a.a(this.g)))
    {
      e.b("startDonwload-->", "开始下载");
      a(this.g, paramA, paramString, false, paramBoolean1, paramBoolean2);
      return;
    }
    e.b("startDonwload-->", "浏览器下载");
    com.mpcore.common.i.h.a(this.g, paramString);
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
    this.h = paramString;
    j.a(this.g, b.a, "appid", paramString);
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
  
  public final void b(String paramString)
  {
    this.i = paramString;
    j.a(this.g, b.a, "appkey", paramString);
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
      return null;
    }
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
    //   11: invokevirtual 267	java/util/HashMap:size	()I
    //   14: ifgt +20 -> 34
    //   17: aload_0
    //   18: getfield 100	com/mpcore/common/a/d:g	Landroid/content/Context;
    //   21: invokestatic 483	com/mpcore/common/a/c:a	(Landroid/content/Context;)Lcom/mpcore/common/a/c;
    //   24: aload_0
    //   25: getfield 213	com/mpcore/common/a/d:h	Ljava/lang/String;
    //   28: invokevirtual 486	com/mpcore/common/a/c:c	(Ljava/lang/String;)Ljava/util/HashMap;
    //   31: putstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   34: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   37: ifnull +14 -> 51
    //   40: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   43: invokevirtual 267	java/util/HashMap:size	()I
    //   46: istore_1
    //   47: iload_1
    //   48: ifne +6 -> 54
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: new 65	java/util/HashMap
    //   57: dup
    //   58: invokespecial 66	java/util/HashMap:<init>	()V
    //   61: astore_3
    //   62: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   65: invokevirtual 271	java/util/HashMap:keySet	()Ljava/util/Set;
    //   68: invokeinterface 277 1 0
    //   73: astore 4
    //   75: aload 4
    //   77: invokeinterface 286 1 0
    //   82: istore_2
    //   83: iload_2
    //   84: ifeq +101 -> 185
    //   87: aload 4
    //   89: invokeinterface 290 1 0
    //   94: checkcast 292	java/lang/String
    //   97: astore 5
    //   99: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   102: aload 5
    //   104: invokevirtual 242	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   107: checkcast 183	com/mpcore/common/e/g
    //   110: astore 6
    //   112: getstatic 52	com/mpcore/common/a/d:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   115: ifnull -40 -> 75
    //   118: getstatic 52	com/mpcore/common/a/d:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   121: aload 5
    //   123: invokevirtual 217	java/util/concurrent/ConcurrentHashMap:containsKey	(Ljava/lang/Object;)Z
    //   126: ifeq -51 -> 75
    //   129: getstatic 52	com/mpcore/common/a/d:a	Ljava/util/concurrent/ConcurrentHashMap;
    //   132: aload 5
    //   134: invokevirtual 238	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   137: checkcast 219	com/mpcore/common/e/h
    //   140: getfield 225	com/mpcore/common/e/h:b	Z
    //   143: ifne -68 -> 75
    //   146: aload 6
    //   148: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   151: invokevirtual 245	com/mpcore/common/e/g:a	(J)V
    //   154: aload_3
    //   155: aload 5
    //   157: aload 6
    //   159: invokevirtual 203	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   162: pop
    //   163: goto -88 -> 75
    //   166: astore 5
    //   168: aload 5
    //   170: invokevirtual 487	java/lang/Exception:printStackTrace	()V
    //   173: ldc_w 489
    //   176: ldc_w 491
    //   179: invokestatic 493	com/mpcore/common/i/e:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: goto -107 -> 75
    //   185: aload_0
    //   186: getfield 100	com/mpcore/common/a/d:g	Landroid/content/Context;
    //   189: invokestatic 377	com/mpcore/common/g/b:a	(Landroid/content/Context;)Lcom/mpcore/common/g/b;
    //   192: aload_0
    //   193: getfield 213	com/mpcore/common/a/d:h	Ljava/lang/String;
    //   196: invokevirtual 381	com/mpcore/common/g/b:a	(Ljava/lang/String;)Lcom/mpcore/common/g/a;
    //   199: astore 4
    //   201: aload 4
    //   203: ifnull +101 -> 304
    //   206: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   209: ifnull +95 -> 304
    //   212: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   215: invokevirtual 271	java/util/HashMap:keySet	()Ljava/util/Set;
    //   218: invokeinterface 277 1 0
    //   223: astore 5
    //   225: aload 5
    //   227: invokeinterface 286 1 0
    //   232: ifeq +72 -> 304
    //   235: aload 5
    //   237: invokeinterface 290 1 0
    //   242: checkcast 292	java/lang/String
    //   245: astore 6
    //   247: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   250: aload 6
    //   252: invokevirtual 242	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   255: checkcast 183	com/mpcore/common/e/g
    //   258: astore 7
    //   260: aload_3
    //   261: aload 6
    //   263: invokevirtual 241	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   266: ifne -41 -> 225
    //   269: aload 7
    //   271: invokevirtual 495	com/mpcore/common/e/g:c	()J
    //   274: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   277: aload 4
    //   279: invokevirtual 498	com/mpcore/common/g/a:aq	()J
    //   282: lsub
    //   283: lcmp
    //   284: ifle -59 -> 225
    //   287: aload_3
    //   288: aload 6
    //   290: aload 7
    //   292: invokevirtual 203	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   295: pop
    //   296: goto -71 -> 225
    //   299: astore_3
    //   300: aload_0
    //   301: monitorexit
    //   302: aload_3
    //   303: athrow
    //   304: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   307: ifnull +9 -> 316
    //   310: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   313: invokevirtual 501	java/util/HashMap:clear	()V
    //   316: aload_3
    //   317: invokevirtual 267	java/util/HashMap:size	()I
    //   320: ifle +10 -> 330
    //   323: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   326: aload_3
    //   327: invokevirtual 505	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   330: aload_0
    //   331: getfield 100	com/mpcore/common/a/d:g	Landroid/content/Context;
    //   334: invokestatic 483	com/mpcore/common/a/c:a	(Landroid/content/Context;)Lcom/mpcore/common/a/c;
    //   337: getstatic 68	com/mpcore/common/a/d:d	Ljava/util/HashMap;
    //   340: invokevirtual 509	java/util/HashMap:values	()Ljava/util/Collection;
    //   343: invokevirtual 512	com/mpcore/common/a/c:a	(Ljava/util/Collection;)V
    //   346: goto -295 -> 51
    //   349: astore_3
    //   350: goto -299 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	353	0	this	d
    //   46	2	1	k	int
    //   82	2	2	bool	boolean
    //   61	227	3	localHashMap	HashMap
    //   299	28	3	localMap	Map
    //   349	1	3	localThrowable	Throwable
    //   73	205	4	localObject1	Object
    //   97	59	5	str	String
    //   166	3	5	localException	Exception
    //   223	13	5	localIterator	Iterator
    //   110	179	6	localObject2	Object
    //   258	33	7	localG	g
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
    e.c("saveAppInfo-----------------addInstallApp---------------->", " " + d.size());
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
    
    public final a a(Context paramContext)
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new IllegalStateException("Cannot be called from the main thread");
      }
      try
      {
        paramContext.getPackageManager().getPackageInfo(h.a.a, 0);
        b localB = new b((byte)0);
        Object localObject1 = new Intent("com.google.android.gms.ads.identifier.service.START");
        ((Intent)localObject1).setPackage("com.google.android.gms");
        if (paramContext.bindService((Intent)localObject1, localB, 1)) {}
        throw new IOException("Google Play connection failed");
      }
      catch (Exception paramContext)
      {
        try
        {
          localObject1 = new c(localB.a());
          localObject1 = new a(((c)localObject1).a(), ((c)localObject1).b());
          return localObject1;
        }
        catch (Exception localException)
        {
          throw localException;
        }
        finally
        {
          paramContext.unbindService(localB);
        }
        paramContext = paramContext;
        throw paramContext;
      }
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
      
      /* Error */
      public final boolean b()
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_3
        //   18: iconst_1
        //   19: invokevirtual 60	android/os/Parcel:writeInt	(I)V
        //   22: aload_0
        //   23: getfield 24	com/mpcore/common/a/d$a$c:b	Landroid/os/IBinder;
        //   26: iconst_2
        //   27: aload_3
        //   28: aload 4
        //   30: iconst_0
        //   31: invokeinterface 44 5 0
        //   36: pop
        //   37: aload 4
        //   39: invokevirtual 47	android/os/Parcel:readException	()V
        //   42: aload 4
        //   44: invokevirtual 64	android/os/Parcel:readInt	()I
        //   47: istore_1
        //   48: iload_1
        //   49: ifeq +14 -> 63
        //   52: aload 4
        //   54: invokevirtual 53	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 53	android/os/Parcel:recycle	()V
        //   61: iload_2
        //   62: ireturn
        //   63: iconst_0
        //   64: istore_2
        //   65: goto -13 -> 52
        //   68: astore 5
        //   70: aload 4
        //   72: invokevirtual 53	android/os/Parcel:recycle	()V
        //   75: aload_3
        //   76: invokevirtual 53	android/os/Parcel:recycle	()V
        //   79: aload 5
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	c
        //   47	2	1	i	int
        //   1	64	2	bool	boolean
        //   5	71	3	localParcel1	Parcel
        //   9	62	4	localParcel2	Parcel
        //   68	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   11	48	68	finally
      }
    }
  }
}
