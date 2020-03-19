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
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.base.entity.InstallApp;
import com.mobvista.msdk.base.utils.CommonDeviceUtil;
import com.mobvista.msdk.base.utils.CommonLogUtil;
import com.mobvista.msdk.base.utils.CommonUtil;
import com.mobvista.msdk.base.utils.SharedPreferencesUtils;
import com.mobvista.msdk.base.utils.StringUtils;
import com.mobvista.msdk.base.utils.a;
import com.mobvista.msdk.base.utils.b;
import com.mobvista.msdk.setting.SettingManager;
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
    //   3: getfield 66	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   6: invokestatic 153	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   9: aload_0
    //   10: getfield 129	com/mobvista/msdk/base/controller/MVSDKContext:e	Ljava/lang/String;
    //   13: invokevirtual 161	com/mobvista/msdk/base/utils/b:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: putstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   19: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   22: ifnull +12 -> 34
    //   25: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   28: invokevirtual 148	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   31: ifne +16 -> 47
    //   34: aload_1
    //   35: ifnull +9 -> 44
    //   38: aload_1
    //   39: invokeinterface 163 1 0
    //   44: aload_0
    //   45: monitorexit
    //   46: return
    //   47: new 56	java/util/concurrent/CopyOnWriteArraySet
    //   50: dup
    //   51: invokespecial 57	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   54: astore 4
    //   56: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   59: invokevirtual 167	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   62: astore 5
    //   64: aload 5
    //   66: ifnull +143 -> 209
    //   69: aload 5
    //   71: invokeinterface 173 1 0
    //   76: istore_3
    //   77: iload_3
    //   78: ifeq +131 -> 209
    //   81: aload 5
    //   83: invokeinterface 177 1 0
    //   88: checkcast 179	com/mobvista/msdk/base/entity/InstallApp
    //   91: astore 6
    //   93: getstatic 54	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   96: ifnull -27 -> 69
    //   99: getstatic 54	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   102: invokeinterface 182 1 0
    //   107: ifle -38 -> 69
    //   110: aload 6
    //   112: ifnull -43 -> 69
    //   115: iconst_0
    //   116: istore_2
    //   117: iload_2
    //   118: getstatic 54	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   121: invokeinterface 182 1 0
    //   126: if_icmpge -57 -> 69
    //   129: getstatic 54	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   132: iload_2
    //   133: invokeinterface 186 2 0
    //   138: checkcast 83	java/lang/String
    //   141: astore 7
    //   143: aload 6
    //   145: invokevirtual 189	com/mobvista/msdk/base/entity/InstallApp:getPackageName	()Ljava/lang/String;
    //   148: astore 8
    //   150: aload 7
    //   152: invokestatic 195	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   155: ifne +29 -> 184
    //   158: aload 8
    //   160: invokestatic 195	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   163: ifne +21 -> 184
    //   166: aload 7
    //   168: aload 8
    //   170: invokevirtual 93	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   173: ifeq +11 -> 184
    //   176: aload 4
    //   178: aload 6
    //   180: invokevirtual 198	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   183: pop
    //   184: iload_2
    //   185: iconst_1
    //   186: iadd
    //   187: istore_2
    //   188: goto -71 -> 117
    //   191: astore 6
    //   193: aload 6
    //   195: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   198: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:a	Ljava/lang/String;
    //   201: ldc -56
    //   203: invokestatic 202	com/mobvista/msdk/base/utils/CommonLogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   206: goto -137 -> 69
    //   209: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   212: ifnull +9 -> 221
    //   215: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   218: invokevirtual 205	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   221: aload 4
    //   223: invokevirtual 148	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   226: ifle +12 -> 238
    //   229: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   232: aload 4
    //   234: invokevirtual 209	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   237: pop
    //   238: aload_0
    //   239: getfield 66	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   242: invokestatic 153	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   245: getstatic 59	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   248: invokevirtual 156	com/mobvista/msdk/base/utils/b:a	(Ljava/util/Set;)V
    //   251: aload_1
    //   252: ifnull -208 -> 44
    //   255: aload_1
    //   256: invokeinterface 163 1 0
    //   261: goto -217 -> 44
    //   264: astore_1
    //   265: aload_0
    //   266: monitorexit
    //   267: aload_1
    //   268: athrow
    //   269: astore_1
    //   270: goto -226 -> 44
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	273	0	this	MVSDKContext
    //   0	273	1	paramB	b
    //   116	72	2	m	int
    //   76	2	3	bool	boolean
    //   54	179	4	localCopyOnWriteArraySet	CopyOnWriteArraySet
    //   62	20	5	localIterator	Iterator
    //   91	88	6	localInstallApp	InstallApp
    //   191	3	6	localException	Exception
    //   141	26	7	str1	String
    //   148	21	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   81	110	191	java/lang/Exception
    //   117	184	191	java/lang/Exception
    //   2	34	264	finally
    //   38	44	264	finally
    //   47	64	264	finally
    //   69	77	264	finally
    //   81	110	264	finally
    //   117	184	264	finally
    //   193	206	264	finally
    //   209	221	264	finally
    //   221	238	264	finally
    //   238	251	264	finally
    //   255	261	264	finally
    //   2	34	269	java/lang/Throwable
    //   38	44	269	java/lang/Throwable
    //   47	64	269	java/lang/Throwable
    //   69	77	269	java/lang/Throwable
    //   81	110	269	java/lang/Throwable
    //   117	184	269	java/lang/Throwable
    //   193	206	269	java/lang/Throwable
    //   209	221	269	java/lang/Throwable
    //   221	238	269	java/lang/Throwable
    //   238	251	269	java/lang/Throwable
    //   255	261	269	java/lang/Throwable
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
  
  public void initGlobalCommonPara(final b paramB)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        boolean bool2 = true;
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(MVSDKContext.a(MVSDKContext.this));
          CommonLogUtil.i(MVSDKContext.a, "has class info id:" + localInfo.getId());
          CommonDeviceUtil.a(localInfo.getId());
          MVSDKContext.a(MVSDKContext.this, localInfo.getId());
        }
        catch (Exception localException1)
        {
          try
          {
            CommonDeviceUtil.a(MVSDKContext.a(MVSDKContext.this));
            CommonUtil.initFbInstalled(MVSDKContext.a(MVSDKContext.this));
            SettingManager.initUnitSetting(MVSDKContext.a(MVSDKContext.this), MVSDKContext.b(MVSDKContext.this));
            MVSDKContext.this.getAppList(false);
            MVSDKContext.this.contrastDiff(paramB);
            MVSDKContext.a(MVSDKContext.this, a.a().c());
            if (MVSDKContext.a(MVSDKContext.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", MVSDKContext.a(MVSDKContext.this).getPackageName()) == 0)
            {
              bool1 = true;
              label166:
              a.a = bool1;
              if (MVSDKContext.a(MVSDKContext.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", MVSDKContext.a(MVSDKContext.this).getPackageName()) != 0) {
                break label282;
              }
            }
            label282:
            for (boolean bool1 = bool2;; bool1 = false)
            {
              a.b = bool1;
              return;
              localException1 = localException1;
              CommonLogUtil.w(MVSDKContext.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
              try
              {
                CommonLogUtil.i(MVSDKContext.a, "no class info id:");
                MVSDKContext.a.a localA = new MVSDKContext.a(MVSDKContext.this).a(MVSDKContext.a(MVSDKContext.this));
                CommonDeviceUtil.a(localA.a());
                MVSDKContext.a(MVSDKContext.this, localA.a());
              }
              catch (Exception localException2)
              {
                CommonLogUtil.w(MVSDKContext.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
              }
              break;
              bool1 = false;
              break label166;
            }
            return;
          }
          catch (Exception localException3) {}
        }
      }
    }).start();
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
      throws Exception
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
      
      public String a()
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
      
      public IBinder a()
        throws InterruptedException
      {
        if (this.a) {
          throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder)this.c.take();
      }
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        try
        {
          this.c.put(paramIBinder);
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
      
      /* Error */
      public boolean a(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 34	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 34	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 36
        //   16: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +56 -> 76
        //   23: iconst_1
        //   24: istore_2
        //   25: aload 4
        //   27: iload_2
        //   28: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   31: aload_0
        //   32: getfield 24	com/mobvista/msdk/base/controller/MVSDKContext$a$c:b	Landroid/os/IBinder;
        //   35: iconst_2
        //   36: aload 4
        //   38: aload 5
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload 5
        //   49: invokevirtual 49	android/os/Parcel:readException	()V
        //   52: aload 5
        //   54: invokevirtual 65	android/os/Parcel:readInt	()I
        //   57: istore_2
        //   58: iload_2
        //   59: ifeq +22 -> 81
        //   62: iload_3
        //   63: istore_1
        //   64: aload 5
        //   66: invokevirtual 55	android/os/Parcel:recycle	()V
        //   69: aload 4
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
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
        //   90: invokevirtual 55	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 55	android/os/Parcel:recycle	()V
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
      
      public IBinder asBinder()
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
