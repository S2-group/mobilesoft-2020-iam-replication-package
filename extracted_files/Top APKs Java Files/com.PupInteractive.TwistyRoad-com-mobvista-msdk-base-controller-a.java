package com.mobvista.msdk.base.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.b.b;
import com.mobvista.msdk.base.entity.e;
import com.mobvista.msdk.base.utils.c;
import com.mobvista.msdk.base.utils.g;
import com.mobvista.msdk.base.utils.h;
import com.mobvista.msdk.base.utils.l;
import com.mobvista.msdk.base.utils.m;
import com.mobvista.msdk.base.utils.q;
import com.mobvista.msdk.base.utils.r;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class a
{
  public static final String a = a.class.getSimpleName();
  public static List<String> b = new ArrayList();
  private static a c;
  private static CopyOnWriteArraySet<e> j = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private List<String> i = null;
  private String k;
  private Location l;
  
  private a() {}
  
  public static Set<e> c()
  {
    return j;
  }
  
  public static a d()
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new a();
      }
      return c;
    }
    finally {}
  }
  
  /* Error */
  public static void e()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 217	com/mobvista/msdk/base/controller/a:d	()Lcom/mobvista/msdk/base/controller/a;
    //   6: invokevirtual 219	com/mobvista/msdk/base/controller/a:k	()Ljava/lang/String;
    //   9: invokestatic 155	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   12: ifne +105 -> 117
    //   15: invokestatic 224	com/mobvista/msdk/b/b:a	()Lcom/mobvista/msdk/b/b;
    //   18: pop
    //   19: invokestatic 217	com/mobvista/msdk/base/controller/a:d	()Lcom/mobvista/msdk/base/controller/a;
    //   22: invokevirtual 219	com/mobvista/msdk/base/controller/a:k	()Ljava/lang/String;
    //   25: invokestatic 227	com/mobvista/msdk/b/b:b	(Ljava/lang/String;)Lcom/mobvista/msdk/b/a;
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull +87 -> 117
    //   33: aload_1
    //   34: invokevirtual 232	com/mobvista/msdk/b/a:aE	()Ljava/lang/String;
    //   37: astore_1
    //   38: aload_1
    //   39: invokestatic 155	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   42: ifne +75 -> 117
    //   45: aload_1
    //   46: invokestatic 236	com/mobvista/msdk/base/utils/a:c	(Ljava/lang/String;)Ljava/lang/String;
    //   49: astore_1
    //   50: aload_1
    //   51: invokestatic 155	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   54: ifne +63 -> 117
    //   57: new 238	org/json/JSONArray
    //   60: dup
    //   61: aload_1
    //   62: invokespecial 239	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   65: astore_1
    //   66: aload_1
    //   67: invokevirtual 242	org/json/JSONArray:length	()I
    //   70: ifle +47 -> 117
    //   73: new 49	java/util/ArrayList
    //   76: dup
    //   77: invokespecial 52	java/util/ArrayList:<init>	()V
    //   80: astore_2
    //   81: iconst_0
    //   82: istore_0
    //   83: iload_0
    //   84: aload_1
    //   85: invokevirtual 242	org/json/JSONArray:length	()I
    //   88: if_icmpge +22 -> 110
    //   91: aload_2
    //   92: aload_1
    //   93: iload_0
    //   94: invokevirtual 246	org/json/JSONArray:optString	(I)Ljava/lang/String;
    //   97: invokeinterface 178 2 0
    //   102: pop
    //   103: iload_0
    //   104: iconst_1
    //   105: iadd
    //   106: istore_0
    //   107: goto -24 -> 83
    //   110: invokestatic 217	com/mobvista/msdk/base/controller/a:d	()Lcom/mobvista/msdk/base/controller/a;
    //   113: aload_2
    //   114: putfield 65	com/mobvista/msdk/base/controller/a:i	Ljava/util/List;
    //   117: ldc 2
    //   119: monitorexit
    //   120: return
    //   121: astore_1
    //   122: ldc 2
    //   124: monitorexit
    //   125: aload_1
    //   126: athrow
    //   127: astore_1
    //   128: goto -11 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   82	25	0	m	int
    //   28	65	1	localObject1	Object
    //   121	5	1	localObject2	Object
    //   127	1	1	localException	Exception
    //   80	34	2	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   3	29	121	finally
    //   33	81	121	finally
    //   83	103	121	finally
    //   110	117	121	finally
    //   3	29	127	java/lang/Exception
    //   33	81	127	java/lang/Exception
    //   83	103	127	java/lang/Exception
    //   110	117	127	java/lang/Exception
  }
  
  public static List<Long> g()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((j != null) && (j.size() > 0))
      {
        localIterator = j.iterator();
        localArrayList = new ArrayList();
      }
      while (localIterator.hasNext())
      {
        e localE = (e)localIterator.next();
        boolean bool = localArrayList.contains(localE.a());
        if (!bool)
        {
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(localE.a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
          continue;
          return null;
        }
      }
    }
    catch (Throwable localThrowable) {}
    return localArrayList;
  }
  
  public final String a()
  {
    try
    {
      if (this.d != null)
      {
        String str = this.d.getPackageName();
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public final void a(Context paramContext)
  {
    this.d = paramContext;
  }
  
  /* Error */
  public final void a(b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 68	com/mobvista/msdk/base/controller/a:d	Landroid/content/Context;
    //   6: invokestatic 280	com/mobvista/msdk/base/utils/m:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/m;
    //   9: aload_0
    //   10: getfield 111	com/mobvista/msdk/base/controller/a:f	Ljava/lang/String;
    //   13: invokevirtual 283	com/mobvista/msdk/base/utils/m:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 4
    //   18: aload 4
    //   20: putstatic 59	com/mobvista/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 4
    //   25: invokevirtual 251	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   28: ifne +16 -> 44
    //   31: aload_1
    //   32: ifnull +9 -> 41
    //   35: aload_1
    //   36: invokeinterface 285 1 0
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: new 56	java/util/concurrent/CopyOnWriteArraySet
    //   47: dup
    //   48: invokespecial 57	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   51: astore 4
    //   53: getstatic 59	com/mobvista/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   56: invokevirtual 252	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   59: astore 5
    //   61: aload 5
    //   63: ifnull +144 -> 207
    //   66: aload 5
    //   68: invokeinterface 143 1 0
    //   73: istore_3
    //   74: iload_3
    //   75: ifeq +132 -> 207
    //   78: aload 5
    //   80: invokeinterface 147 1 0
    //   85: checkcast 254	com/mobvista/msdk/base/entity/e
    //   88: astore 6
    //   90: getstatic 54	com/mobvista/msdk/base/controller/a:b	Ljava/util/List;
    //   93: ifnull -27 -> 66
    //   96: getstatic 54	com/mobvista/msdk/base/controller/a:b	Ljava/util/List;
    //   99: invokeinterface 127 1 0
    //   104: ifle -38 -> 66
    //   107: aload 6
    //   109: ifnull -43 -> 66
    //   112: iconst_0
    //   113: istore_2
    //   114: iload_2
    //   115: getstatic 54	com/mobvista/msdk/base/controller/a:b	Ljava/util/List;
    //   118: invokeinterface 127 1 0
    //   123: if_icmpge -57 -> 66
    //   126: getstatic 54	com/mobvista/msdk/base/controller/a:b	Ljava/util/List;
    //   129: iload_2
    //   130: invokeinterface 289 2 0
    //   135: checkcast 149	java/lang/String
    //   138: astore 7
    //   140: aload 6
    //   142: invokevirtual 291	com/mobvista/msdk/base/entity/e:b	()Ljava/lang/String;
    //   145: astore 8
    //   147: aload 7
    //   149: invokestatic 155	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   152: ifne +29 -> 181
    //   155: aload 8
    //   157: invokestatic 155	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   160: ifne +21 -> 181
    //   163: aload 7
    //   165: aload 8
    //   167: invokevirtual 204	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   170: ifeq +11 -> 181
    //   173: aload 4
    //   175: aload 6
    //   177: invokevirtual 292	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   180: pop
    //   181: iload_2
    //   182: iconst_1
    //   183: iadd
    //   184: istore_2
    //   185: goto -71 -> 114
    //   188: astore 6
    //   190: aload 6
    //   192: invokevirtual 108	java/lang/Exception:printStackTrace	()V
    //   195: getstatic 47	com/mobvista/msdk/base/controller/a:a	Ljava/lang/String;
    //   198: ldc_w 294
    //   201: invokestatic 296	com/mobvista/msdk/base/utils/h:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   204: goto -138 -> 66
    //   207: getstatic 59	com/mobvista/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   210: ifnull +9 -> 219
    //   213: getstatic 59	com/mobvista/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   216: invokevirtual 299	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   219: aload 4
    //   221: invokevirtual 251	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   224: ifle +12 -> 236
    //   227: getstatic 59	com/mobvista/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   230: aload 4
    //   232: invokevirtual 303	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   235: pop
    //   236: aload_0
    //   237: getfield 68	com/mobvista/msdk/base/controller/a:d	Landroid/content/Context;
    //   240: invokestatic 280	com/mobvista/msdk/base/utils/m:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/m;
    //   243: getstatic 59	com/mobvista/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   246: invokevirtual 306	com/mobvista/msdk/base/utils/m:a	(Ljava/util/Set;)V
    //   249: aload_1
    //   250: ifnull -209 -> 41
    //   253: aload_1
    //   254: invokeinterface 285 1 0
    //   259: goto -218 -> 41
    //   262: astore_1
    //   263: aload_0
    //   264: monitorexit
    //   265: aload_1
    //   266: athrow
    //   267: astore_1
    //   268: goto -227 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	271	0	this	a
    //   0	271	1	paramB	b
    //   113	72	2	m	int
    //   73	2	3	bool	boolean
    //   16	215	4	localCopyOnWriteArraySet	CopyOnWriteArraySet
    //   59	20	5	localIterator	Iterator
    //   88	88	6	localE	e
    //   188	3	6	localException	Exception
    //   138	26	7	str1	String
    //   145	21	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   78	107	188	java/lang/Exception
    //   114	181	188	java/lang/Exception
    //   2	31	262	finally
    //   35	41	262	finally
    //   44	61	262	finally
    //   66	74	262	finally
    //   78	107	262	finally
    //   114	181	262	finally
    //   190	204	262	finally
    //   207	219	262	finally
    //   219	236	262	finally
    //   236	249	262	finally
    //   253	259	262	finally
    //   2	31	267	java/lang/Throwable
    //   35	41	267	java/lang/Throwable
    //   44	61	267	java/lang/Throwable
    //   66	74	267	java/lang/Throwable
    //   78	107	267	java/lang/Throwable
    //   114	181	267	java/lang/Throwable
    //   190	204	267	java/lang/Throwable
    //   207	219	267	java/lang/Throwable
    //   219	236	267	java/lang/Throwable
    //   236	249	267	java/lang/Throwable
    //   253	259	267	java/lang/Throwable
  }
  
  public final void a(final b paramB, final Handler paramHandler)
  {
    if (this.h == true) {
      return;
    }
    try
    {
      Object localObject = q.b(this.d, "ga_id", "-1");
      if ((localObject != null) && ((localObject instanceof String)))
      {
        localObject = (String)localObject;
        if ((r.b((String)localObject)) && (!"-1".equals(localObject)))
        {
          h.b(a, "sp init gaid:" + (String)localObject);
          com.mobvista.msdk.base.controller.authoritycontroller.a.a();
          if (com.mobvista.msdk.base.controller.authoritycontroller.a.a("authority_device_id")) {
            c.a((String)localObject);
          }
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    new Thread(new Runnable()
    {
      public final void run()
      {
        com.mobvista.msdk.base.controller.authoritycontroller.a.a();
        if (com.mobvista.msdk.base.controller.authoritycontroller.a.a("authority_device_id")) {}
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this));
          c.a(((AdvertisingIdClient.Info)localObject1).getId());
          a.a(a.this, ((AdvertisingIdClient.Info)localObject1).getId());
        }
        catch (Exception localException3)
        {
          try
          {
            b.a();
            Object localObject2 = b.b(a.d().k());
            Object localObject1 = localObject2;
            if (localObject2 == null)
            {
              b.a();
              localObject1 = b.b();
            }
            localObject2 = Message.obtain();
            ((Message)localObject2).obj = localObject1;
            ((Message)localObject2).what = 9;
            paramHandler.sendMessage((Message)localObject2);
          }
          catch (Exception localException3)
          {
            try
            {
              label99:
              c.a(a.a(a.this));
              a.e();
              l.c(a.a(a.this));
              b.a(a.a(a.this), a.b(a.this));
              a.this.m();
              a.c(a.this);
              a.this.a(paramB);
              a.a(a.this, g.a().b());
              if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName()) == 0)
              {
                bool = true;
                label209:
                g.a = bool;
                if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) != 0) {
                  break label325;
                }
              }
              label325:
              for (boolean bool = true;; bool = false)
              {
                g.b = bool;
                return;
                localException1 = localException1;
                h.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
                try
                {
                  a.a.a localA = new a.a(a.this).a(a.a(a.this));
                  c.a(localA.a());
                  a.a(a.this, localA.a());
                }
                catch (Exception localException2)
                {
                  h.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
                }
                break;
                localException3 = localException3;
                localException3.printStackTrace();
                break label99;
                bool = false;
                break label209;
              }
              return;
            }
            catch (Exception localException4) {}
          }
        }
      }
    }).start();
  }
  
  public final void a(String paramString)
  {
    try
    {
      this.k = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        q.a(this.d, "applicationIds", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final List<String> b()
  {
    return this.i;
  }
  
  public final void b(String paramString)
  {
    this.e = paramString;
  }
  
  public final void c(String paramString)
  {
    try
    {
      this.f = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        q.a(this.d, "sp_appId", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void d(String paramString)
  {
    try
    {
      this.g = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        q.a(this.d, "sp_appKey", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void f()
  {
    try
    {
      if ((j != null) && (j.size() > 0)) {
        m.a(this.d).a(j);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final Location h()
  {
    return this.l;
  }
  
  public final Context i()
  {
    return this.d;
  }
  
  public final String j()
  {
    return this.e;
  }
  
  public final String k()
  {
    try
    {
      if (!TextUtils.isEmpty(this.f)) {
        return this.f;
      }
      if (this.d != null)
      {
        String str = (String)q.b(this.d, "sp_appId", "");
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public final String l()
  {
    if (!TextUtils.isEmpty(this.g)) {
      return this.g;
    }
    if (this.d != null) {
      return (String)q.b(this.d, "sp_appKey", "");
    }
    return null;
  }
  
  public final List<String> m()
  {
    for (;;)
    {
      int m;
      try
      {
        List localList1 = d().i;
        if (b != null) {
          return b;
        }
        List localList3 = this.d.getPackageManager().getInstalledPackages(0);
        m = 0;
        if (m < localList3.size()) {
          if ((((PackageInfo)localList3.get(m)).applicationInfo.flags & 0x1) <= 0) {
            b.add(((PackageInfo)localList3.get(m)).packageName);
          } else if ((localList1 != null) && (localList1.size() > 0) && (localList1.contains(((PackageInfo)localList3.get(m)).packageName))) {
            b.add(((PackageInfo)localList3.get(m)).packageName);
          }
        }
      }
      catch (Exception localException)
      {
        h.d(a, "get package info list error");
        return null;
      }
      List localList2 = b;
      return localList2;
      m += 1;
    }
  }
  
  public final class a
  {
    public a() {}
    
    public final a a(Context paramContext)
      throws Exception
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new IllegalStateException("Cannot be called from the main thread");
      }
      try
      {
        paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
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
        throws InterruptedException
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
        throws RemoteException
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
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 34	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 34	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 36
        //   14: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_3
        //   18: iconst_1
        //   19: invokevirtual 63	android/os/Parcel:writeInt	(I)V
        //   22: aload_0
        //   23: getfield 24	com/mobvista/msdk/base/controller/a$a$c:b	Landroid/os/IBinder;
        //   26: iconst_2
        //   27: aload_3
        //   28: aload 4
        //   30: iconst_0
        //   31: invokeinterface 46 5 0
        //   36: pop
        //   37: aload 4
        //   39: invokevirtual 49	android/os/Parcel:readException	()V
        //   42: aload 4
        //   44: invokevirtual 67	android/os/Parcel:readInt	()I
        //   47: istore_1
        //   48: iload_1
        //   49: ifeq +14 -> 63
        //   52: aload 4
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: iload_2
        //   62: ireturn
        //   63: iconst_0
        //   64: istore_2
        //   65: goto -13 -> 52
        //   68: astore 5
        //   70: aload 4
        //   72: invokevirtual 55	android/os/Parcel:recycle	()V
        //   75: aload_3
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
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
  
  public static abstract interface b
  {
    public abstract void a();
  }
}
