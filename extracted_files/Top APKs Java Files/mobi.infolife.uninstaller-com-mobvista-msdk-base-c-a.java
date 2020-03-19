package com.mobvista.msdk.base.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.base.entity.c;
import com.mobvista.msdk.base.utils.d;
import com.mobvista.msdk.base.utils.e;
import com.mobvista.msdk.base.utils.h;
import com.mobvista.msdk.base.utils.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class a
{
  public static final String a = "a";
  public static List<String> b = new ArrayList();
  private static a c;
  private static CopyOnWriteArraySet<c> i = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private String j;
  private Location k;
  
  private a() {}
  
  public static Set<c> b()
  {
    return i;
  }
  
  public static a c()
  {
    if (c == null) {
      try
      {
        if (c == null) {
          c = new a();
        }
      }
      finally {}
    }
    return c;
  }
  
  public static List<Long> g()
  {
    try
    {
      if ((i != null) && (i.size() > 0))
      {
        Iterator localIterator = i.iterator();
        ArrayList localArrayList = new ArrayList();
        while (localIterator.hasNext())
        {
          c localC = (c)localIterator.next();
          boolean bool = localArrayList.contains(localC.a());
          if (!bool) {
            try
            {
              localArrayList.add(Long.valueOf(Long.parseLong(localC.a())));
            }
            catch (NumberFormatException localNumberFormatException)
            {
              localNumberFormatException.printStackTrace();
            }
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
  
  public final String a()
  {
    return this.j;
  }
  
  public final void a(Context paramContext)
  {
    this.d = paramContext;
  }
  
  public final void a(String paramString)
  {
    this.j = paramString;
  }
  
  public final void b(String paramString)
  {
    this.e = paramString;
  }
  
  public final void c(String paramString)
  {
    this.f = paramString;
  }
  
  public final void d()
  {
    if (this.h == true) {
      return;
    }
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient.Info");
          com.mobvista.msdk.base.utils.b.a(AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this)).getId());
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            try
            {
              i = a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName());
              bool2 = false;
              if (i != 0) {
                break;
              }
              bool1 = true;
              d.a = bool1;
              bool1 = bool2;
              if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) == 0) {
                bool1 = true;
              }
              d.b = bool1;
              return;
            }
            catch (Exception localException3)
            {
              return;
            }
            localException1 = localException1;
          }
        }
        e.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
        try
        {
          com.mobvista.msdk.base.utils.b.a(new a.a(a.this).a(a.a(a.this)).a());
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
        e.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
        com.mobvista.msdk.base.utils.b.a(a.a(a.this));
        h.b(a.a(a.this));
        com.mobvista.msdk.b.b.a(a.a(a.this), a.b(a.this));
        a.this.m();
        a.this.e();
        a.a(a.this, d.a().b());
        for (;;)
        {
          int i;
          boolean bool2;
          boolean bool1 = false;
        }
      }
    }).start();
    this.h = true;
  }
  
  public final void d(String paramString)
  {
    this.g = paramString;
  }
  
  /* Error */
  public final void e()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   6: invokestatic 146	com/mobvista/msdk/base/utils/i:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/i;
    //   9: aload_0
    //   10: getfield 62	com/mobvista/msdk/base/c/a:f	Ljava/lang/String;
    //   13: invokevirtual 149	com/mobvista/msdk/base/utils/i:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 5
    //   18: aload 5
    //   20: putstatic 49	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 5
    //   25: ifnull +158 -> 183
    //   28: getstatic 49	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   31: invokevirtual 78	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   34: ifne +6 -> 40
    //   37: goto +146 -> 183
    //   40: getstatic 49	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   43: invokevirtual 82	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   46: astore 6
    //   48: iconst_0
    //   49: istore_1
    //   50: aload 6
    //   52: invokeinterface 88 1 0
    //   57: istore 4
    //   59: iload 4
    //   61: ifeq +102 -> 163
    //   64: aload 6
    //   66: invokeinterface 92 1 0
    //   71: checkcast 94	com/mobvista/msdk/base/entity/c
    //   74: astore 5
    //   76: iconst_0
    //   77: istore_3
    //   78: iload_1
    //   79: istore_2
    //   80: iload_3
    //   81: getstatic 44	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   84: invokeinterface 150 1 0
    //   89: if_icmpge +112 -> 201
    //   92: iload_1
    //   93: istore_2
    //   94: getstatic 44	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   97: iload_3
    //   98: invokeinterface 154 2 0
    //   103: checkcast 156	java/lang/String
    //   106: aload 5
    //   108: invokevirtual 158	com/mobvista/msdk/base/entity/c:b	()Ljava/lang/String;
    //   111: invokevirtual 161	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   114: ifeq +14 -> 128
    //   117: getstatic 49	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   120: aload 5
    //   122: invokevirtual 164	java/util/concurrent/CopyOnWriteArraySet:remove	(Ljava/lang/Object;)Z
    //   125: pop
    //   126: iconst_1
    //   127: istore_2
    //   128: iload_3
    //   129: iconst_1
    //   130: iadd
    //   131: istore_3
    //   132: iload_2
    //   133: istore_1
    //   134: goto -56 -> 78
    //   137: astore 5
    //   139: goto +5 -> 144
    //   142: astore 5
    //   144: aload 5
    //   146: invokevirtual 165	java/lang/Exception:printStackTrace	()V
    //   149: getstatic 167	com/mobvista/msdk/base/c/a:a	Ljava/lang/String;
    //   152: ldc -87
    //   154: invokestatic 174	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   157: pop
    //   158: iload_1
    //   159: istore_2
    //   160: goto +41 -> 201
    //   163: iload_1
    //   164: ifeq +16 -> 180
    //   167: aload_0
    //   168: getfield 56	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   171: invokestatic 146	com/mobvista/msdk/base/utils/i:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/i;
    //   174: getstatic 49	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   177: invokevirtual 177	com/mobvista/msdk/base/utils/i:a	(Ljava/util/Set;)V
    //   180: aload_0
    //   181: monitorexit
    //   182: return
    //   183: aload_0
    //   184: monitorexit
    //   185: return
    //   186: astore 5
    //   188: aload_0
    //   189: monitorexit
    //   190: aload 5
    //   192: athrow
    //   193: aload_0
    //   194: monitorexit
    //   195: return
    //   196: astore 5
    //   198: goto -5 -> 193
    //   201: iload_2
    //   202: istore_1
    //   203: goto -153 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	a
    //   49	154	1	m	int
    //   79	123	2	n	int
    //   77	55	3	i1	int
    //   57	3	4	bool	boolean
    //   16	105	5	localObject1	Object
    //   137	1	5	localException1	Exception
    //   142	3	5	localException2	Exception
    //   186	5	5	localObject2	Object
    //   196	1	5	localThrowable	Throwable
    //   46	19	6	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   80	92	137	java/lang/Exception
    //   94	126	137	java/lang/Exception
    //   64	76	142	java/lang/Exception
    //   2	23	186	finally
    //   28	37	186	finally
    //   40	48	186	finally
    //   50	59	186	finally
    //   64	76	186	finally
    //   80	92	186	finally
    //   94	126	186	finally
    //   144	158	186	finally
    //   167	180	186	finally
    //   2	23	196	java/lang/Throwable
    //   28	37	196	java/lang/Throwable
    //   40	48	196	java/lang/Throwable
    //   50	59	196	java/lang/Throwable
    //   64	76	196	java/lang/Throwable
    //   80	92	196	java/lang/Throwable
    //   94	126	196	java/lang/Throwable
    //   144	158	196	java/lang/Throwable
    //   167	180	196	java/lang/Throwable
  }
  
  public final void f()
  {
    try
    {
      if ((i != null) && (i.size() > 0)) {
        i.a(this.d).a(i);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final Location h()
  {
    return this.k;
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
    return this.f;
  }
  
  public final String l()
  {
    return this.g;
  }
  
  public final List<String> m()
  {
    try
    {
      if (b != null) {
        return b;
      }
      Object localObject = this.d.getPackageManager();
      int m = 0;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      while (m < ((List)localObject).size())
      {
        b.add(((PackageInfo)((List)localObject).get(m)).packageName);
        m += 1;
      }
      localObject = b;
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    e.d(a, "get package info list error");
    return null;
  }
  
  public final class a
  {
    public a() {}
    
    /* Error */
    public final a a(Context paramContext)
      throws Exception
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
      //   23: ldc 51
      //   25: iconst_0
      //   26: invokevirtual 57	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   29: pop
      //   30: new 11	com/mobvista/msdk/base/c/a$a$b
      //   33: dup
      //   34: aload_0
      //   35: iconst_0
      //   36: invokespecial 60	com/mobvista/msdk/base/c/a$a$b:<init>	(Lcom/mobvista/msdk/base/c/a$a;B)V
      //   39: astore_2
      //   40: new 62	android/content/Intent
      //   43: dup
      //   44: ldc 64
      //   46: invokespecial 65	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   49: astore_3
      //   50: aload_3
      //   51: ldc 67
      //   53: invokevirtual 71	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   56: pop
      //   57: aload_1
      //   58: aload_3
      //   59: aload_2
      //   60: iconst_1
      //   61: invokevirtual 75	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
      //   64: ifeq +54 -> 118
      //   67: new 14	com/mobvista/msdk/base/c/a$a$c
      //   70: dup
      //   71: aload_0
      //   72: aload_2
      //   73: invokevirtual 78	com/mobvista/msdk/base/c/a$a$b:a	()Landroid/os/IBinder;
      //   76: invokespecial 81	com/mobvista/msdk/base/c/a$a$c:<init>	(Lcom/mobvista/msdk/base/c/a$a;Landroid/os/IBinder;)V
      //   79: astore_3
      //   80: new 9	com/mobvista/msdk/base/c/a$a$a
      //   83: dup
      //   84: aload_0
      //   85: aload_3
      //   86: invokevirtual 84	com/mobvista/msdk/base/c/a$a$c:a	()Ljava/lang/String;
      //   89: aload_3
      //   90: invokevirtual 87	com/mobvista/msdk/base/c/a$a$c:b	()Z
      //   93: invokespecial 90	com/mobvista/msdk/base/c/a$a$a:<init>	(Lcom/mobvista/msdk/base/c/a$a;Ljava/lang/String;Z)V
      //   96: astore_3
      //   97: aload_1
      //   98: aload_2
      //   99: invokevirtual 94	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   102: aload_3
      //   103: areturn
      //   104: astore_3
      //   105: goto +6 -> 111
      //   108: astore_3
      //   109: aload_3
      //   110: athrow
      //   111: aload_1
      //   112: aload_2
      //   113: invokevirtual 94	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   116: aload_3
      //   117: athrow
      //   118: new 96	java/io/IOException
      //   121: dup
      //   122: ldc 98
      //   124: invokespecial 99	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   127: athrow
      //   128: astore_1
      //   129: aload_1
      //   130: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	131	0	this	a
      //   0	131	1	paramContext	Context
      //   39	74	2	localB	b
      //   49	54	3	localObject1	Object
      //   104	1	3	localObject2	Object
      //   108	9	3	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   67	97	104	finally
      //   109	111	104	finally
      //   67	97	108	java/lang/Exception
      //   19	30	128	java/lang/Exception
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
      
      public final boolean b()
        throws RemoteException
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
