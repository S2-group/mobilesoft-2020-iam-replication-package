package com.mobvista.msdk.base.controller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
  public void contrastDiff(MVSDKContext.b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   6: invokestatic 144	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   9: aload_0
    //   10: getfield 120	com/mobvista/msdk/base/controller/MVSDKContext:e	Ljava/lang/String;
    //   13: invokevirtual 152	com/mobvista/msdk/base/utils/b:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 4
    //   18: aload 4
    //   20: putstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 4
    //   25: ifnull +12 -> 37
    //   28: getstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   31: invokevirtual 139	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   34: ifne +16 -> 50
    //   37: aload_1
    //   38: ifnull +9 -> 47
    //   41: aload_1
    //   42: invokeinterface 156 1 0
    //   47: aload_0
    //   48: monitorexit
    //   49: return
    //   50: new 46	java/util/concurrent/CopyOnWriteArraySet
    //   53: dup
    //   54: invokespecial 47	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   57: astore 4
    //   59: getstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   62: invokevirtual 160	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   65: astore 5
    //   67: aload 5
    //   69: ifnull +143 -> 212
    //   72: aload 5
    //   74: invokeinterface 166 1 0
    //   79: istore_3
    //   80: iload_3
    //   81: ifeq +131 -> 212
    //   84: aload 5
    //   86: invokeinterface 170 1 0
    //   91: checkcast 172	com/mobvista/msdk/base/entity/InstallApp
    //   94: astore 6
    //   96: getstatic 44	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   99: ifnull -27 -> 72
    //   102: getstatic 44	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   105: invokeinterface 175 1 0
    //   110: ifle -38 -> 72
    //   113: aload 6
    //   115: ifnull -43 -> 72
    //   118: iconst_0
    //   119: istore_2
    //   120: iload_2
    //   121: getstatic 44	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   124: invokeinterface 175 1 0
    //   129: if_icmpge -57 -> 72
    //   132: getstatic 44	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   135: iload_2
    //   136: invokeinterface 179 2 0
    //   141: checkcast 73	java/lang/String
    //   144: astore 7
    //   146: aload 6
    //   148: invokevirtual 182	com/mobvista/msdk/base/entity/InstallApp:getPackageName	()Ljava/lang/String;
    //   151: astore 8
    //   153: aload 7
    //   155: invokestatic 188	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   158: ifne +29 -> 187
    //   161: aload 8
    //   163: invokestatic 188	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   166: ifne +21 -> 187
    //   169: aload 7
    //   171: aload 8
    //   173: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   176: ifeq +11 -> 187
    //   179: aload 4
    //   181: aload 6
    //   183: invokevirtual 191	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   186: pop
    //   187: iload_2
    //   188: iconst_1
    //   189: iadd
    //   190: istore_2
    //   191: goto -71 -> 120
    //   194: astore 6
    //   196: aload 6
    //   198: invokevirtual 109	java/lang/Exception:printStackTrace	()V
    //   201: getstatic 37	com/mobvista/msdk/base/controller/MVSDKContext:a	Ljava/lang/String;
    //   204: ldc -63
    //   206: invokestatic 195	com/mobvista/msdk/base/utils/CommonLogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: goto -137 -> 72
    //   212: getstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   215: ifnull +9 -> 224
    //   218: getstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   221: invokevirtual 198	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   224: aload 4
    //   226: invokevirtual 139	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   229: ifle +12 -> 241
    //   232: getstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   235: aload 4
    //   237: invokevirtual 202	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   240: pop
    //   241: aload_0
    //   242: getfield 56	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   245: invokestatic 144	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   248: getstatic 49	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   251: invokevirtual 147	com/mobvista/msdk/base/utils/b:a	(Ljava/util/Set;)V
    //   254: aload_1
    //   255: ifnull -208 -> 47
    //   258: aload_1
    //   259: invokeinterface 156 1 0
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
    //   0	276	1	paramB	MVSDKContext.b
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
    return null;
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
  
  public void init(MVSDKContext.b paramB)
  {
    if (this.g == true) {
      return;
    }
    a();
    initGlobalCommonPara(paramB);
  }
  
  public void initGlobalCommonPara(final MVSDKContext.b paramB)
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
              label163:
              a.a = bool1;
              if (MVSDKContext.a(MVSDKContext.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", MVSDKContext.a(MVSDKContext.this).getPackageName()) != 0) {
                break label279;
              }
            }
            label279:
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
              break label163;
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
}
