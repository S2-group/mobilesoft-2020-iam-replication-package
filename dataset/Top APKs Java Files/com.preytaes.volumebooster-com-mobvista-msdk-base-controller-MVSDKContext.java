package com.mobvista.msdk.base.controller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.text.TextUtils;
import com.mobvista.msdk.base.entity.InstallApp;
import com.mobvista.msdk.base.utils.CommonDeviceUtil;
import com.mobvista.msdk.base.utils.CommonLogUtil;
import com.mobvista.msdk.base.utils.SharedPreferencesUtils;
import com.mobvista.msdk.base.utils.StringUtils;
import com.mobvista.msdk.base.utils.b;
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
    //   3: getfield 54	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   6: invokestatic 141	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   9: aload_0
    //   10: getfield 117	com/mobvista/msdk/base/controller/MVSDKContext:e	Ljava/lang/String;
    //   13: invokevirtual 149	com/mobvista/msdk/base/utils/b:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: putstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   19: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   22: ifnull +12 -> 34
    //   25: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   28: invokevirtual 136	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   31: ifne +16 -> 47
    //   34: aload_1
    //   35: ifnull +9 -> 44
    //   38: aload_1
    //   39: invokeinterface 153 1 0
    //   44: aload_0
    //   45: monitorexit
    //   46: return
    //   47: new 44	java/util/concurrent/CopyOnWriteArraySet
    //   50: dup
    //   51: invokespecial 45	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   54: astore 4
    //   56: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   59: invokevirtual 157	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   62: astore 5
    //   64: aload 5
    //   66: ifnull +143 -> 209
    //   69: aload 5
    //   71: invokeinterface 163 1 0
    //   76: istore_3
    //   77: iload_3
    //   78: ifeq +131 -> 209
    //   81: aload 5
    //   83: invokeinterface 167 1 0
    //   88: checkcast 169	com/mobvista/msdk/base/entity/InstallApp
    //   91: astore 6
    //   93: getstatic 42	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   96: ifnull -27 -> 69
    //   99: getstatic 42	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   102: invokeinterface 172 1 0
    //   107: ifle -38 -> 69
    //   110: aload 6
    //   112: ifnull -43 -> 69
    //   115: iconst_0
    //   116: istore_2
    //   117: iload_2
    //   118: getstatic 42	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   121: invokeinterface 172 1 0
    //   126: if_icmpge -57 -> 69
    //   129: getstatic 42	com/mobvista/msdk/base/controller/MVSDKContext:packageInfoList	Ljava/util/List;
    //   132: iload_2
    //   133: invokeinterface 176 2 0
    //   138: checkcast 71	java/lang/String
    //   141: astore 7
    //   143: aload 6
    //   145: invokevirtual 179	com/mobvista/msdk/base/entity/InstallApp:getPackageName	()Ljava/lang/String;
    //   148: astore 8
    //   150: aload 7
    //   152: invokestatic 185	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   155: ifne +29 -> 184
    //   158: aload 8
    //   160: invokestatic 185	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   163: ifne +21 -> 184
    //   166: aload 7
    //   168: aload 8
    //   170: invokevirtual 81	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   173: ifeq +11 -> 184
    //   176: aload 4
    //   178: aload 6
    //   180: invokevirtual 188	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   183: pop
    //   184: iload_2
    //   185: iconst_1
    //   186: iadd
    //   187: istore_2
    //   188: goto -71 -> 117
    //   191: astore 6
    //   193: aload 6
    //   195: invokevirtual 106	java/lang/Exception:printStackTrace	()V
    //   198: getstatic 35	com/mobvista/msdk/base/controller/MVSDKContext:a	Ljava/lang/String;
    //   201: ldc -66
    //   203: invokestatic 192	com/mobvista/msdk/base/utils/CommonLogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   206: goto -137 -> 69
    //   209: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   212: ifnull +9 -> 221
    //   215: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   218: invokevirtual 195	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   221: aload 4
    //   223: invokevirtual 136	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   226: ifle +12 -> 238
    //   229: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   232: aload 4
    //   234: invokevirtual 199	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   237: pop
    //   238: aload_0
    //   239: getfield 54	com/mobvista/msdk/base/controller/MVSDKContext:c	Landroid/content/Context;
    //   242: invokestatic 141	com/mobvista/msdk/base/utils/b:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/b;
    //   245: getstatic 47	com/mobvista/msdk/base/controller/MVSDKContext:h	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   248: invokevirtual 144	com/mobvista/msdk/base/utils/b:a	(Ljava/util/Set;)V
    //   251: aload_1
    //   252: ifnull -208 -> 44
    //   255: aload_1
    //   256: invokeinterface 153 1 0
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
    //   0	273	1	paramB	MVSDKContext.b
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
  
  public void init(MVSDKContext.b paramB)
  {
    if (this.g == true) {
      return;
    }
    a();
    initGlobalCommonPara(paramB);
  }
  
  public void initGlobalCommonPara(MVSDKContext.b paramB)
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
}
