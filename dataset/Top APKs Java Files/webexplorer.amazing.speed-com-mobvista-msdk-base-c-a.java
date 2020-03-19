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
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.base.entity.c;
import com.mobvista.msdk.base.utils.d;
import com.mobvista.msdk.base.utils.e;
import com.mobvista.msdk.base.utils.h;
import com.mobvista.msdk.base.utils.i;
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
  
  public static List<Long> g()
  {
    ArrayList localArrayList;
    if ((i != null) && (i.size() > 0))
    {
      Iterator localIterator = i.iterator();
      localArrayList = new ArrayList();
      try
      {
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
      catch (Exception localException) {}
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
        boolean bool2 = true;
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient.Info");
          com.mobvista.msdk.base.utils.b.a(AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this)).getId());
          com.mobvista.msdk.base.utils.b.a(a.a(a.this));
          h.b(a.a(a.this));
          com.mobvista.msdk.b.b.a(a.a(a.this), a.b(a.this));
          a.this.m();
          a.this.e();
          a.a(a.this, d.a().b());
          if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName()) == 0)
          {
            bool1 = true;
            d.a = bool1;
            if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) != 0) {
              break label221;
            }
            bool1 = bool2;
            d.b = bool1;
          }
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            e.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
            try
            {
              com.mobvista.msdk.base.utils.b.a(new a.a(a.this).a(a.a(a.this)).a());
            }
            catch (Exception localException2)
            {
              e.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
            }
            continue;
            boolean bool1 = false;
            continue;
            label221:
            bool1 = false;
          }
        }
      }
    }).start();
    this.h = true;
  }
  
  public final void d(String paramString)
  {
    this.g = paramString;
  }
  
  public final void e()
  {
    label198:
    for (;;)
    {
      try
      {
        Object localObject1 = i.a(this.d).a(this.f);
        i = (CopyOnWriteArraySet)localObject1;
        int m;
        if (localObject1 != null)
        {
          m = i.size();
          if (m != 0) {}
        }
        else
        {
          return;
        }
        localObject1 = i.iterator();
        int n = 0;
        boolean bool = ((Iterator)localObject1).hasNext();
        if (bool)
        {
          int i2 = n;
          try
          {
            c localC = (c)((Iterator)localObject1).next();
            int i1 = 0;
            m = n;
            n = m;
            i2 = m;
            if (i1 >= b.size()) {
              continue;
            }
            i2 = m;
            if (!((String)b.get(i1)).equals(localC.b())) {
              break label198;
            }
            i2 = m;
            i.remove(localC);
            m = 1;
            i1 += 1;
            continue;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            Log.e(a, "remove list error");
            n = i2;
          }
        }
        if (n == 0) {
          continue;
        }
      }
      finally {}
      i.a(this.d).a(i);
    }
  }
  
  public final void f()
  {
    if ((i != null) && (i.size() > 0)) {
      i.a(this.d).a(i);
    }
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
      e.d(a, "get package info list error");
    }
    return null;
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
        //   23: getfield 24	com/mobvista/msdk/base/c/a$a$c:b	Landroid/os/IBinder;
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
}
