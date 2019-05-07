package com.cmcm.swiper;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.cleanmaster.configmanager.f;
import com.keniu.security.c;
import com.keniu.security.curlmonitor.MonitorManager;
import com.keniu.security.curlmonitor.MonitorManager.a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class a
{
  private static a f;
  Context a = c.a();
  Set<String> b = null;
  Set<String> c = null;
  Set<String> d = null;
  Set<String> e = null;
  private ArrayList<String> g = null;
  private boolean h = false;
  private MonitorManager.a i = new a.1(this);
  
  private a() {}
  
  public static a a()
  {
    try
    {
      if (f == null) {
        f = new a();
      }
      a localA = f;
      return localA;
    }
    finally {}
  }
  
  private Set<String> d(String paramString)
  {
    HashSet localHashSet = new HashSet();
    PackageManager localPackageManager = this.a.getPackageManager();
    if (localPackageManager == null) {
      return localHashSet;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.HOME");
    if (!TextUtils.isEmpty(paramString)) {
      localIntent.setPackage(paramString);
    }
    paramString = localPackageManager.queryIntentActivities(localIntent, 1).iterator();
    while (paramString.hasNext()) {
      localHashSet.add(((ResolveInfo)paramString.next()).activityInfo.packageName);
    }
    return localHashSet;
  }
  
  private ArrayList<String> e()
  {
    localArrayList = new ArrayList();
    Object localObject2 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
    Object localObject1 = this.a.getPackageManager();
    if (localObject1 == null) {
      return localArrayList;
    }
    try
    {
      String str = this.a.getPackageName();
      localObject2 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 32).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject2).next();
        if (((TextUtils.isEmpty(str)) || (!str.equals(localResolveInfo.activityInfo.packageName))) && (!localArrayList.contains(localResolveInfo.activityInfo.packageName))) {
          localArrayList.add(localResolveInfo.activityInfo.packageName);
        }
      }
      PackageInfo localPackageInfo;
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
        if (!localArrayList.contains(localPackageInfo.packageName)) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
  }
  
  private Set<String> e(String paramString)
  {
    HashSet localHashSet = new HashSet();
    Object localObject = this.a.getPackageManager();
    if (localObject == null) {
      return localHashSet;
    }
    Intent localIntent = new Intent();
    localIntent.setAction("android.view.InputMethod");
    if (!TextUtils.isEmpty(paramString)) {
      localIntent.setPackage(paramString);
    }
    paramString = ((PackageManager)localObject).queryIntentServices(localIntent, 4);
    if ((paramString != null) && (paramString.size() > 0))
    {
      paramString = paramString.iterator();
      while (paramString.hasNext())
      {
        localObject = (ResolveInfo)paramString.next();
        if ((((ResolveInfo)localObject).serviceInfo != null) && (!TextUtils.isEmpty(((ResolveInfo)localObject).serviceInfo.packageName)) && (!"keepass2android.keepass2android".equals(((ResolveInfo)localObject).serviceInfo.packageName))) {
          localHashSet.add(((ResolveInfo)localObject).serviceInfo.packageName);
        }
      }
    }
    return localHashSet;
  }
  
  public final boolean a(String paramString)
  {
    boolean bool2 = true;
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    boolean bool3;
    boolean bool1;
    if (this.d != null)
    {
      bool3 = this.d.contains(paramString);
      if (!bool3) {
        break label127;
      }
      if (this.b == null) {
        break label124;
      }
      if (!this.b.contains(paramString))
      {
        bool1 = true;
        label57:
        bool3 = bool1 & bool3;
        label63:
        bool4 = bool3;
        if (this.c != null)
        {
          if (this.c.contains(paramString)) {
            break label103;
          }
          bool1 = bool2;
        }
      }
    }
    label89:
    label103:
    label124:
    label127:
    for (boolean bool4 = bool3 & bool1;; bool4 = bool3)
    {
      return bool4;
      bool1 = false;
      break label57;
      bool1 = false;
      break label89;
      if ((!this.h) || (this.d != null)) {
        break;
      }
      return true;
      break label63;
    }
  }
  
  final Set<String> b(String paramString)
  {
    localHashSet = new HashSet();
    PackageManager localPackageManager = this.a.getPackageManager();
    if (localPackageManager == null) {
      return localHashSet;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    if (!TextUtils.isEmpty(paramString)) {
      localIntent.setPackage(paramString);
    }
    try
    {
      paramString = localPackageManager.queryIntentActivities(localIntent, 32).iterator();
      while (paramString.hasNext()) {
        localHashSet.add(((ResolveInfo)paramString.next()).activityInfo.packageName);
      }
      return localHashSet;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      paramString = localPackageManager.getInstalledPackages(0).iterator();
      while (paramString.hasNext()) {
        localHashSet.add(((PackageInfo)paramString.next()).packageName);
      }
      localHashSet.add("APPLICATION_CLEAN_APP_PKG");
      localHashSet.add("APPLICATION_ALL_APP_PKG");
      localHashSet.add("APPLICATION_NEWS_APP_PKG");
    }
  }
  
  /* Error */
  public final void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 37	com/cmcm/swiper/a:h	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: aload_0
    //   16: ldc -57
    //   18: invokespecial 60	com/cmcm/swiper/a:d	(Ljava/lang/String;)Ljava/util/Set;
    //   21: putfield 27	com/cmcm/swiper/a:b	Ljava/util/Set;
    //   24: aload_0
    //   25: aload_0
    //   26: ldc -57
    //   28: invokespecial 62	com/cmcm/swiper/a:e	(Ljava/lang/String;)Ljava/util/Set;
    //   31: putfield 29	com/cmcm/swiper/a:c	Ljava/util/Set;
    //   34: aload_0
    //   35: aload_0
    //   36: ldc -57
    //   38: invokevirtual 201	com/cmcm/swiper/a:b	(Ljava/lang/String;)Ljava/util/Set;
    //   41: putfield 31	com/cmcm/swiper/a:d	Ljava/util/Set;
    //   44: aload_0
    //   45: aload_0
    //   46: ldc -57
    //   48: invokevirtual 203	com/cmcm/swiper/a:c	(Ljava/lang/String;)Ljava/util/Set;
    //   51: putfield 33	com/cmcm/swiper/a:e	Ljava/util/Set;
    //   54: aload_0
    //   55: aload_0
    //   56: invokespecial 205	com/cmcm/swiper/a:e	()Ljava/util/ArrayList;
    //   59: putfield 35	com/cmcm/swiper/a:g	Ljava/util/ArrayList;
    //   62: invokestatic 210	com/keniu/security/curlmonitor/MonitorManager:a	()Lcom/keniu/security/curlmonitor/MonitorManager;
    //   65: getstatic 213	com/keniu/security/curlmonitor/MonitorManager:c	I
    //   68: aload_0
    //   69: getfield 44	com/cmcm/swiper/a:i	Lcom/keniu/security/curlmonitor/MonitorManager$a;
    //   72: invokevirtual 216	com/keniu/security/curlmonitor/MonitorManager:a	(ILcom/keniu/security/curlmonitor/MonitorManager$a;)Z
    //   75: pop
    //   76: invokestatic 210	com/keniu/security/curlmonitor/MonitorManager:a	()Lcom/keniu/security/curlmonitor/MonitorManager;
    //   79: getstatic 218	com/keniu/security/curlmonitor/MonitorManager:b	I
    //   82: aload_0
    //   83: getfield 44	com/cmcm/swiper/a:i	Lcom/keniu/security/curlmonitor/MonitorManager$a;
    //   86: invokevirtual 216	com/keniu/security/curlmonitor/MonitorManager:a	(ILcom/keniu/security/curlmonitor/MonitorManager$a;)Z
    //   89: pop
    //   90: aload_0
    //   91: iconst_1
    //   92: putfield 37	com/cmcm/swiper/a:h	Z
    //   95: goto -84 -> 11
    //   98: astore_2
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_2
    //   102: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	a
    //   6	2	1	bool	boolean
    //   98	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	98	finally
    //   14	95	98	finally
  }
  
  final Set<String> c(String paramString)
  {
    HashSet localHashSet = new HashSet();
    Object localObject = this.a.getApplicationContext();
    ComponentName localComponentName;
    label98:
    do
    {
      try
      {
        localObject = ((DevicePolicyManager)((Context)localObject).getSystemService("device_policy")).getActiveAdmins();
        if ((localObject != null) && (!((List)localObject).isEmpty()))
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            localComponentName = (ComponentName)((Iterator)localObject).next();
            if (!TextUtils.isEmpty(paramString)) {
              break label98;
            }
            localHashSet.add(localComponentName.getPackageName());
          }
        }
        return localHashSet;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    } while (!paramString.equals(localComponentName.getPackageName()));
    localHashSet.add(localComponentName.getPackageName());
    return localHashSet;
  }
  
  public final void c()
  {
    MonitorManager.a().b(MonitorManager.c, this.i);
    MonitorManager.a().b(MonitorManager.b, this.i);
    this.h = false;
    if (this.b != null)
    {
      this.b.clear();
      this.b = null;
    }
    if (this.c != null)
    {
      this.c.clear();
      this.c = null;
    }
    if (this.d != null)
    {
      this.d.clear();
      this.d = null;
    }
    if (this.e != null)
    {
      this.e.clear();
      this.e = null;
    }
    if (this.g != null)
    {
      this.g.clear();
      this.g = null;
    }
  }
  
  public final ArrayList<String> d()
  {
    ArrayList localArrayList = new ArrayList();
    int k;
    Object localObject2;
    int j;
    if ((this.g != null) && (!this.g.isEmpty()))
    {
      k = this.g.size();
      localObject2 = new Random();
      j = 0;
    }
    for (;;)
    {
      if (j < k) {}
      label144:
      do
      {
        try
        {
          int m = ((Random)localObject2).nextInt(k - 1) + 1;
          if ((m >= k) || (this.g.get(m) == null) || (localArrayList.contains(this.g.get(m)))) {
            break;
          }
          localArrayList.add(this.g.get(m));
          if (j < 4) {
            break;
          }
          if (localArrayList.isEmpty()) {
            break label144;
          }
          j = localArrayList.size();
          if (j < 4) {
            break label144;
          }
        }
        catch (Exception localException)
        {
          do
          {
            localException.printStackTrace();
            localObject2 = com.cleanmaster.configmanager.a.a().a.N();
            localObject1 = localObject2;
          } while (!((ArrayList)localObject2).isEmpty());
          return e();
        }
        return localArrayList;
        localObject2 = com.cleanmaster.configmanager.a.a().a.N();
        Object localObject1 = localObject2;
      } while (!((ArrayList)localObject2).isEmpty());
      return e();
      j += 1;
    }
  }
}
