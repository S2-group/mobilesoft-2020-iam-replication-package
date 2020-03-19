package com.mobvista.msdk.base.controller;

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
import com.mobvista.msdk.base.entity.InstallApp;
import com.mobvista.msdk.base.utils.CommonDeviceUtil;
import com.mobvista.msdk.base.utils.CommonLogUtil;
import com.mobvista.msdk.base.utils.SharedPreferencesUtils;
import com.mobvista.msdk.base.utils.StringUtils;
import com.mobvista.msdk.base.utils.b;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class MVSDKContext
{
  public static final String a = MVSDKContext.class.getSimpleName();
  private static MVSDKContext b;
  private static CopyOnWriteArraySet<InstallApp> h = new CopyOnWriteArraySet();
  public static List<String> packageInfoList = new ArrayList();
  private Context c;
  private String d;
  private String e;
  private String f;
  private boolean g = false;
  private boolean i;
  private String j;
  private Location k;
  
  private MVSDKContext() {}
  
  private void a()
  {
    try
    {
      Object localObject = SharedPreferencesUtils.getParam(this.c, "ga_id", "-1");
      if ((localObject != null) && ((localObject instanceof String)))
      {
        localObject = (String)localObject;
        if ((StringUtils.notNull((String)localObject)) && (!"-1".equals(localObject)))
        {
          CommonLogUtil.i(a, "sp init gaid:" + (String)localObject);
          CommonDeviceUtil.a((String)localObject);
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void a(String paramString)
  {
    try
    {
      if (StringUtils.notNull(paramString))
      {
        CommonLogUtil.i(a, "saveGAID gaid:" + paramString);
        SharedPreferencesUtils.setParam(this.c, "ga_id", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static Set<InstallApp> getInstalledCampaignList()
  {
    return h;
  }
  
  public static MVSDKContext getInstance()
  {
    if (b == null) {}
    try
    {
      if (b == null) {
        b = new MVSDKContext();
      }
      return b;
    }
    finally {}
  }
  
  public static void setInstalledCampaignList(CopyOnWriteArraySet<InstallApp> paramCopyOnWriteArraySet)
  {
    h = paramCopyOnWriteArraySet;
  }
  
  public void addInstallApp()
  {
    try
    {
      if ((h != null) && (h.size() > 0)) {
        b.a(this.c).a(h);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  /* Error */
  public void contrastDiff(b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 64	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   6: invokestatic 152	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   9: aload_0
    //   10: getfield 128	com/mobvista/msdk/base/controller/MVSDKContext:e	Ljava/lang/String;
    //   13: invokevirtual 160	com/mobvista/msdk/base/utils/b:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 4
    //   18: aload 4
    //   20: putstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 4
    //   25: ifnull +12 -> 37
    //   28: getstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   31: invokevirtual 147	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   34: ifne +16 -> 50
    //   37: aload_1
    //   38: ifnull +9 -> 47
    //   41: aload_1
    //   42: invokeinterface 162 1 0
    //   47: aload_0
    //   48: monitorexit
    //   49: return
    //   50: new 54	java/util/concurrent/CopyOnWriteArraySet
    //   53: dup
    //   54: invokespecial 55	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   57: astore 4
    //   59: getstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   62: invokevirtual 166	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   65: astore 5
    //   67: aload 5
    //   69: ifnull +143 -> 212
    //   72: aload 5
    //   74: invokeinterface 172 1 0
    //   79: istore_3
    //   80: iload_3
    //   81: ifeq +131 -> 212
    //   84: aload 5
    //   86: invokeinterface 176 1 0
    //   91: checkcast 178	com/mobvista/msdk/base/entity/InstallApp
    //   94: astore 6
    //   96: getstatic 52	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   99: ifnull -27 -> 72
    //   102: getstatic 52	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   105: invokeinterface 181 1 0
    //   110: ifle -38 -> 72
    //   113: aload 6
    //   115: ifnull -43 -> 72
    //   118: iconst_0
    //   119: istore_2
    //   120: iload_2
    //   121: getstatic 52	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   124: invokeinterface 181 1 0
    //   129: if_icmpge -57 -> 72
    //   132: getstatic 52	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   135: iload_2
    //   136: invokeinterface 185 2 0
    //   141: checkcast 81	java/lang/String
    //   144: astore 7
    //   146: aload 6
    //   148: invokevirtual 188	com/mobvista/msdk/base/entity/InstallApp:getPackageName	()Ljava/lang/String;
    //   151: astore 8
    //   153: aload 7
    //   155: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   158: ifne +29 -> 187
    //   161: aload 8
    //   163: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   166: ifne +21 -> 187
    //   169: aload 7
    //   171: aload 8
    //   173: invokevirtual 91	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   176: ifeq +11 -> 187
    //   179: aload 4
    //   181: aload 6
    //   183: invokevirtual 197	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   186: pop
    //   187: iload_2
    //   188: iconst_1
    //   189: iadd
    //   190: istore_2
    //   191: goto -71 -> 120
    //   194: astore 6
    //   196: aload 6
    //   198: invokevirtual 117	java/lang/Exception:printStackTrace	()V
    //   201: getstatic 45	com/mobvista/msdk/base/controller/MVSDKContext:a	Ljava/lang/String;
    //   204: ldc -57
    //   206: invokestatic 201	com/mobvista/msdk/base/utils/CommonLogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: goto -137 -> 72
    //   212: getstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   215: ifnull +9 -> 224
    //   218: getstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   221: invokevirtual 204	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   224: aload 4
    //   226: invokevirtual 147	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   229: ifle +12 -> 241
    //   232: getstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   235: aload 4
    //   237: invokevirtual 208	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   240: pop
    //   241: aload_0
    //   242: getfield 64	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   245: invokestatic 152	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   248: getstatic 57	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   251: invokevirtual 155	com/mobvista/msdk/base/utils/b:a	(Ljava/util/Set;)V
    //   254: aload_1
    //   255: ifnull -208 -> 47
    //   258: aload_1
    //   259: invokeinterface 162 1 0
    //   264: goto -217 -> 47
    //   267: astore_1
    //   268: aload_0
    //   269: monitorexit
    //   270: aload_1
    //   271: athrow
    //   272: astore_1
    //   273: goto -226 -> 47
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	this	MVSDKContext
    //   0	276	1	paramB	b
    //   119	72	2	m	int
    //   79	2	3	bool	boolean
    //   16	220	4	localCopyOnWriteArraySet	CopyOnWriteArraySet
    //   65	20	5	localIterator	Iterator
    //   94	88	6	localInstallApp	InstallApp
    //   194	3	6	localException	Exception
    //   144	26	7	str1	String
    //   151	21	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   84	113	194	java/lang/Exception
    //   120	187	194	java/lang/Exception
    //   2	23	267	finally
    //   28	37	267	finally
    //   41	47	267	finally
    //   50	67	267	finally
    //   72	80	267	finally
    //   84	113	267	finally
    //   120	187	267	finally
    //   196	209	267	finally
    //   212	224	267	finally
    //   224	241	267	finally
    //   241	254	267	finally
    //   258	264	267	finally
    //   2	23	272	java/lang/Throwable
    //   28	37	272	java/lang/Throwable
    //   41	47	272	java/lang/Throwable
    //   50	67	272	java/lang/Throwable
    //   72	80	272	java/lang/Throwable
    //   84	113	272	java/lang/Throwable
    //   120	187	272	java/lang/Throwable
    //   196	209	272	java/lang/Throwable
    //   212	224	272	java/lang/Throwable
    //   224	241	272	java/lang/Throwable
    //   241	254	272	java/lang/Throwable
    //   258	264	272	java/lang/Throwable
  }
  
  public String getAppId()
  {
    try
    {
      if (!TextUtils.isEmpty(this.e)) {
        return this.e;
      }
      if (this.c != null)
      {
        String str = (String)SharedPreferencesUtils.getParam(this.c, "sp_appId", "");
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public String getAppKey()
  {
    if (!TextUtils.isEmpty(this.f)) {
      return this.f;
    }
    if (this.c != null) {
      return (String)SharedPreferencesUtils.getParam(this.c, "sp_appKey", "");
    }
    return null;
  }
  
  public List<String> getAppList(boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      localList = this.c.getPackageManager().getInstalledPackages(0);
      m = 0;
      while (m < localList.size())
      {
        packageInfoList.add(((PackageInfo)localList.get(m)).packageName);
        m += 1;
      }
      return packageInfoList;
    }
    catch (Exception localException)
    {
      List localList;
      int m;
      CommonLogUtil.e(a, "get package info list error");
    }
    if (packageInfoList != null) {
      return packageInfoList;
    }
    localList = this.c.getPackageManager().getInstalledPackages(0);
    m = 0;
    while (m < localList.size())
    {
      packageInfoList.add(((PackageInfo)localList.get(m)).packageName);
      m += 1;
    }
    localList = packageInfoList;
    return localList;
    return null;
  }
  
  public List<Long> getCampaignIds()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((h != null) && (h.size() > 0))
      {
        localIterator = h.iterator();
        localArrayList = new ArrayList();
      }
      while (localIterator.hasNext())
      {
        InstallApp localInstallApp = (InstallApp)localIterator.next();
        boolean bool = localArrayList.contains(localInstallApp.getCampaignId());
        if (!bool)
        {
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(localInstallApp.getCampaignId())));
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
  
  public Context getContext()
  {
    return this.c;
  }
  
  public String getFacebookPlacementId()
  {
    return this.d;
  }
  
  public Location getLocation()
  {
    return this.k;
  }
  
  public String getPackageName()
  {
    try
    {
      if (this.c != null)
      {
        String str = this.c.getPackageName();
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public void init(b paramB)
  {
    if (this.g == true) {
      return;
    }
    a();
    initGlobalCommonPara(paramB);
  }
  
  public void initGlobalCommonPara(b paramB)
  {
    new Thread(new MVSDKContext.1(this, paramB)).start();
  }
  
  public boolean isPreloadImage()
  {
    return this.i;
  }
  
  public void setApplicationID(String paramString)
  {
    try
    {
      this.j = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.c != null)) {
        SharedPreferencesUtils.setParam(this.c, "applicationIds", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setPreloadImage(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }
  
  public void setmAppId(String paramString)
  {
    try
    {
      this.e = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.c != null)) {
        SharedPreferencesUtils.setParam(this.c, "sp_appId", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setmAppKey(String paramString)
  {
    try
    {
      this.f = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.c != null)) {
        SharedPreferencesUtils.setParam(this.c, "sp_appKey", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setmContext(Context paramContext)
  {
    this.c = paramContext;
  }
  
  public void setmFacebookPlacementId(String paramString)
  {
    this.d = paramString;
  }
  
  public class a
  {
    public a() {}
    
    public a a(Context paramContext)
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new IllegalStateException("Cannot be called from the main thread");
      }
      try
      {
        paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
        b localB = new b(null);
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
          localObject1 = new a(((c)localObject1).a(), ((c)localObject1).a(true));
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
      
      /* Error */
      public final boolean a(boolean paramBoolean)
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 34
        //   16: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +56 -> 76
        //   23: iconst_1
        //   24: istore_2
        //   25: aload 4
        //   27: iload_2
        //   28: invokevirtual 58	android/os/Parcel:writeInt	(I)V
        //   31: aload_0
        //   32: getfield 24	com/mobvista/msdk/base/controller/MVSDKContext$a$c:b	Landroid/os/IBinder;
        //   35: iconst_2
        //   36: aload 4
        //   38: aload 5
        //   40: iconst_0
        //   41: invokeinterface 44 5 0
        //   46: pop
        //   47: aload 5
        //   49: invokevirtual 47	android/os/Parcel:readException	()V
        //   52: aload 5
        //   54: invokevirtual 62	android/os/Parcel:readInt	()I
        //   57: istore_2
        //   58: iload_2
        //   59: ifeq +22 -> 81
        //   62: iload_3
        //   63: istore_1
        //   64: aload 5
        //   66: invokevirtual 53	android/os/Parcel:recycle	()V
        //   69: aload 4
        //   71: invokevirtual 53	android/os/Parcel:recycle	()V
        //   74: iload_1
        //   75: ireturn
        //   76: iconst_0
        //   77: istore_2
        //   78: goto -53 -> 25
        //   81: iconst_0
        //   82: istore_1
        //   83: goto -19 -> 64
        //   86: astore 6
        //   88: aload 5
        //   90: invokevirtual 53	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 53	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	c
        //   0	101	1	paramBoolean	boolean
        //   24	54	2	i	int
        //   1	62	3	bool	boolean
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        //   86	13	6	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   12	19	86	finally
        //   25	58	86	finally
      }
      
      public final IBinder asBinder()
      {
        return this.b;
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract void a();
  }
}
