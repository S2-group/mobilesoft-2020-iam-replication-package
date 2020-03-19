package com.mobvista.msdk.base.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.base.entity.e;
import com.mobvista.msdk.base.utils.f;
import com.mobvista.msdk.base.utils.g;
import com.mobvista.msdk.base.utils.j;
import com.mobvista.msdk.base.utils.k;
import com.mobvista.msdk.base.utils.o;
import com.mobvista.msdk.base.utils.p;
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
  private static CopyOnWriteArraySet<e> i = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private String j;
  private Location k;
  
  private a() {}
  
  public static Set<e> b()
  {
    return i;
  }
  
  public static a c()
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
  
  public static List<Long> e()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((i != null) && (i.size() > 0))
      {
        localIterator = i.iterator();
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
  
  public final void a(final b paramB)
  {
    if (this.h == true) {
      return;
    }
    try
    {
      Object localObject = o.b(this.d, "ga_id", "-1");
      if ((localObject != null) && ((localObject instanceof String)))
      {
        localObject = (String)localObject;
        if ((p.b((String)localObject)) && (!"-1".equals(localObject)))
        {
          g.b(a, "sp init gaid:" + (String)localObject);
          com.mobvista.msdk.base.utils.b.a((String)localObject);
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
        boolean bool2 = true;
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient.Info");
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this));
          com.mobvista.msdk.base.utils.b.a(localInfo.getId());
          a.a(a.this, localInfo.getId());
        }
        catch (Exception localException1)
        {
          try
          {
            com.mobvista.msdk.base.utils.b.a(a.a(a.this));
            j.c(a.a(a.this));
            com.mobvista.msdk.b.b.a(a.a(a.this), a.b(a.this));
            a.this.k();
            a.this.b(paramB);
            a.a(a.this, f.a().b());
            if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName()) == 0)
            {
              bool1 = true;
              label143:
              f.a = bool1;
              if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) != 0) {
                break label251;
              }
            }
            label251:
            for (boolean bool1 = bool2;; bool1 = false)
            {
              f.b = bool1;
              return;
              localException1 = localException1;
              g.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
              try
              {
                a.a.a localA = new a.a(a.this).a(a.a(a.this));
                com.mobvista.msdk.base.utils.b.a(localA.a());
                a.a(a.this, localA.a());
              }
              catch (Exception localException2)
              {
                g.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
              }
              break;
              bool1 = false;
              break label143;
            }
            return;
          }
          catch (Exception localException3) {}
        }
      }
    }).start();
  }
  
  public final void a(String paramString)
  {
    try
    {
      this.j = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        o.a(this.d, "applicationIds", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public final void b(b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 65	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   6: invokestatic 212	com/mobvista/msdk/base/utils/k:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/k;
    //   9: aload_0
    //   10: getfield 108	com/mobvista/msdk/base/c/a:f	Ljava/lang/String;
    //   13: invokevirtual 215	com/mobvista/msdk/base/utils/k:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 4
    //   18: aload 4
    //   20: putstatic 58	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 4
    //   25: invokevirtual 124	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   28: ifne +16 -> 44
    //   31: aload_1
    //   32: ifnull +9 -> 41
    //   35: aload_1
    //   36: invokeinterface 217 1 0
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: new 55	java/util/concurrent/CopyOnWriteArraySet
    //   47: dup
    //   48: invokespecial 56	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   51: astore 4
    //   53: getstatic 58	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   56: invokevirtual 128	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   59: astore 5
    //   61: aload 5
    //   63: ifnull +143 -> 206
    //   66: aload 5
    //   68: invokeinterface 134 1 0
    //   73: istore_3
    //   74: iload_3
    //   75: ifeq +131 -> 206
    //   78: aload 5
    //   80: invokeinterface 138 1 0
    //   85: checkcast 140	com/mobvista/msdk/base/entity/e
    //   88: astore 6
    //   90: getstatic 53	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   93: ifnull -27 -> 66
    //   96: getstatic 53	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   99: invokeinterface 218 1 0
    //   104: ifle -38 -> 66
    //   107: aload 6
    //   109: ifnull -43 -> 66
    //   112: iconst_0
    //   113: istore_2
    //   114: iload_2
    //   115: getstatic 53	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   118: invokeinterface 218 1 0
    //   123: if_icmpge -57 -> 66
    //   126: getstatic 53	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   129: iload_2
    //   130: invokeinterface 222 2 0
    //   135: checkcast 177	java/lang/String
    //   138: astore 7
    //   140: aload 6
    //   142: invokevirtual 224	com/mobvista/msdk/base/entity/e:b	()Ljava/lang/String;
    //   145: astore 8
    //   147: aload 7
    //   149: invokestatic 205	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   152: ifne +29 -> 181
    //   155: aload 8
    //   157: invokestatic 205	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   160: ifne +21 -> 181
    //   163: aload 7
    //   165: aload 8
    //   167: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   170: ifeq +11 -> 181
    //   173: aload 4
    //   175: aload 6
    //   177: invokevirtual 225	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   180: pop
    //   181: iload_2
    //   182: iconst_1
    //   183: iadd
    //   184: istore_2
    //   185: goto -71 -> 114
    //   188: astore 6
    //   190: aload 6
    //   192: invokevirtual 105	java/lang/Exception:printStackTrace	()V
    //   195: getstatic 46	com/mobvista/msdk/base/c/a:a	Ljava/lang/String;
    //   198: ldc -29
    //   200: invokestatic 229	com/mobvista/msdk/base/utils/g:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: goto -137 -> 66
    //   206: getstatic 58	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   209: ifnull +9 -> 218
    //   212: getstatic 58	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   215: invokevirtual 232	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   218: aload 4
    //   220: invokevirtual 124	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   223: ifle +12 -> 235
    //   226: getstatic 58	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   229: aload 4
    //   231: invokevirtual 236	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   234: pop
    //   235: aload_0
    //   236: getfield 65	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   239: invokestatic 212	com/mobvista/msdk/base/utils/k:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/k;
    //   242: getstatic 58	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   245: invokevirtual 239	com/mobvista/msdk/base/utils/k:a	(Ljava/util/Set;)V
    //   248: aload_1
    //   249: ifnull -208 -> 41
    //   252: aload_1
    //   253: invokeinterface 217 1 0
    //   258: goto -217 -> 41
    //   261: astore_1
    //   262: aload_0
    //   263: monitorexit
    //   264: aload_1
    //   265: athrow
    //   266: astore_1
    //   267: goto -226 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	270	0	this	a
    //   0	270	1	paramB	b
    //   113	72	2	m	int
    //   73	2	3	bool	boolean
    //   16	214	4	localCopyOnWriteArraySet	CopyOnWriteArraySet
    //   59	20	5	localIterator	Iterator
    //   88	88	6	localE	e
    //   188	3	6	localException	Exception
    //   138	26	7	str1	String
    //   145	21	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   78	107	188	java/lang/Exception
    //   114	181	188	java/lang/Exception
    //   2	31	261	finally
    //   35	41	261	finally
    //   44	61	261	finally
    //   66	74	261	finally
    //   78	107	261	finally
    //   114	181	261	finally
    //   190	203	261	finally
    //   206	218	261	finally
    //   218	235	261	finally
    //   235	248	261	finally
    //   252	258	261	finally
    //   2	31	266	java/lang/Throwable
    //   35	41	266	java/lang/Throwable
    //   44	61	266	java/lang/Throwable
    //   66	74	266	java/lang/Throwable
    //   78	107	266	java/lang/Throwable
    //   114	181	266	java/lang/Throwable
    //   190	203	266	java/lang/Throwable
    //   206	218	266	java/lang/Throwable
    //   218	235	266	java/lang/Throwable
    //   235	248	266	java/lang/Throwable
    //   252	258	266	java/lang/Throwable
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
        o.a(this.d, "sp_appId", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void d()
  {
    try
    {
      if ((i != null) && (i.size() > 0)) {
        k.a(this.d).a(i);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final void d(String paramString)
  {
    try
    {
      this.g = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        o.a(this.d, "sp_appKey", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final Location f()
  {
    return this.k;
  }
  
  public final Context g()
  {
    return this.d;
  }
  
  public final String h()
  {
    return this.e;
  }
  
  public final String i()
  {
    try
    {
      if (!TextUtils.isEmpty(this.f)) {
        return this.f;
      }
      if (this.d != null)
      {
        String str = (String)o.b(this.d, "sp_appId", "");
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public final String j()
  {
    if (!TextUtils.isEmpty(this.g)) {
      return this.g;
    }
    if (this.d != null) {
      return (String)o.b(this.d, "sp_appKey", "");
    }
    return null;
  }
  
  public final List<String> k()
  {
    try
    {
      if (b != null) {
        return b;
      }
      List localList = this.d.getPackageManager().getInstalledPackages(0);
      int m = 0;
      while (m < localList.size())
      {
        b.add(((PackageInfo)localList.get(m)).packageName);
        m += 1;
      }
      localList = b;
      return localList;
    }
    catch (Exception localException)
    {
      g.d(a, "get package info list error");
    }
    return null;
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
        //   23: getfield 24	com/mobvista/msdk/base/c/a$a$c:b	Landroid/os/IBinder;
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
  
  public static abstract interface b
  {
    public abstract void a();
  }
}
