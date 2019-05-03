package com.ihs.device.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.ihs.app.framework.b;
import com.ihs.commons.e.e;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class a
{
  private final Map<String, a> a;
  private ReentrantReadWriteLock b;
  
  /* Error */
  private a()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 23	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: new 25	java/util/HashMap
    //   8: dup
    //   9: invokespecial 26	java/util/HashMap:<init>	()V
    //   12: putfield 28	com/ihs/device/common/a:a	Ljava/util/Map;
    //   15: aload_0
    //   16: new 30	java/util/concurrent/locks/ReentrantReadWriteLock
    //   19: dup
    //   20: invokespecial 31	java/util/concurrent/locks/ReentrantReadWriteLock:<init>	()V
    //   23: putfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   26: aload_0
    //   27: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   30: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   33: invokevirtual 42	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:lock	()V
    //   36: new 44	android/content/IntentFilter
    //   39: dup
    //   40: invokespecial 45	android/content/IntentFilter:<init>	()V
    //   43: astore_1
    //   44: aload_1
    //   45: ldc 47
    //   47: invokevirtual 51	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   50: aload_1
    //   51: ldc 53
    //   53: invokevirtual 51	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   56: aload_1
    //   57: ldc 55
    //   59: invokevirtual 58	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   62: invokestatic 63	com/ihs/app/framework/b:b	()Landroid/content/Context;
    //   65: new 6	com/ihs/device/common/a$1
    //   68: dup
    //   69: aload_0
    //   70: invokespecial 66	com/ihs/device/common/a$1:<init>	(Lcom/ihs/device/common/a;)V
    //   73: aload_1
    //   74: invokevirtual 72	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   77: pop
    //   78: invokestatic 63	com/ihs/app/framework/b:b	()Landroid/content/Context;
    //   81: invokevirtual 76	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   84: astore_1
    //   85: new 78	java/util/HashSet
    //   88: dup
    //   89: invokestatic 83	com/ihs/device/common/a/a:a	()Ljava/util/List;
    //   92: invokespecial 86	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   95: astore_2
    //   96: aload_1
    //   97: sipush 128
    //   100: invokevirtual 92	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   103: invokeinterface 98 1 0
    //   108: astore_3
    //   109: aload_3
    //   110: invokeinterface 104 1 0
    //   115: ifeq +80 -> 195
    //   118: aload_3
    //   119: invokeinterface 108 1 0
    //   124: checkcast 110	android/content/pm/ApplicationInfo
    //   127: astore 4
    //   129: aload_0
    //   130: aload 4
    //   132: aload_1
    //   133: invokespecial 113	com/ihs/device/common/a:a	(Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageManager;)Z
    //   136: ifeq -27 -> 109
    //   139: aload_0
    //   140: getfield 28	com/ihs/device/common/a:a	Ljava/util/Map;
    //   143: aload 4
    //   145: getfield 117	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   148: new 10	com/ihs/device/common/a$a
    //   151: dup
    //   152: aload 4
    //   154: aload_1
    //   155: aload_2
    //   156: aload 4
    //   158: getfield 117	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   161: invokeinterface 123 2 0
    //   166: aconst_null
    //   167: invokespecial 126	com/ihs/device/common/a$a:<init>	(Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageManager;ZLcom/ihs/device/common/a$1;)V
    //   170: invokeinterface 132 3 0
    //   175: pop
    //   176: goto -67 -> 109
    //   179: astore_1
    //   180: aload_1
    //   181: invokevirtual 135	java/lang/Exception:printStackTrace	()V
    //   184: aload_0
    //   185: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   188: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   191: invokevirtual 138	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   194: return
    //   195: ldc -116
    //   197: new 142	java/lang/StringBuilder
    //   200: dup
    //   201: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   204: ldc -111
    //   206: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: aload_0
    //   210: getfield 28	com/ihs/device/common/a:a	Ljava/util/Map;
    //   213: invokeinterface 153 1 0
    //   218: invokevirtual 156	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   221: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokestatic 166	com/ihs/commons/e/e:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   227: aload_0
    //   228: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   231: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   234: invokevirtual 138	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   237: return
    //   238: astore_1
    //   239: aload_0
    //   240: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   243: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   246: invokevirtual 138	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   249: aload_1
    //   250: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	251	0	this	a
    //   43	112	1	localObject1	Object
    //   179	2	1	localException	Exception
    //   238	12	1	localObject2	Object
    //   95	61	2	localHashSet	java.util.HashSet
    //   108	11	3	localIterator	Iterator
    //   127	30	4	localApplicationInfo	ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   36	109	179	java/lang/Exception
    //   109	176	179	java/lang/Exception
    //   195	227	179	java/lang/Exception
    //   36	109	238	finally
    //   109	176	238	finally
    //   180	184	238	finally
    //   195	227	238	finally
  }
  
  public static a a()
  {
    return b.a();
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
  {
    if ((paramApplicationInfo == null) || (paramPackageManager == null)) {}
    while (TextUtils.isEmpty(paramApplicationInfo.packageName)) {
      return false;
    }
    return true;
  }
  
  private List<a> b()
  {
    this.b.readLock().lock();
    try
    {
      ArrayList localArrayList = new ArrayList(this.a.values());
      return localArrayList;
    }
    finally
    {
      this.b.readLock().unlock();
    }
  }
  
  <T extends HSAppInfo> T a(Class<T> paramClass, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    this.b.readLock().lock();
    try
    {
      paramString = (a)this.a.get(paramString);
      if (paramString != null)
      {
        paramClass = a.a(paramString, paramClass);
        return paramClass;
      }
      return null;
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    finally
    {
      this.b.readLock().unlock();
    }
  }
  
  <T extends HSAppInfo> ArrayList<T> a(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    HSAppFilter localHSAppFilter = paramHSAppFilter;
    if (paramHSAppFilter == null) {
      localHSAppFilter = new HSAppFilter();
    }
    Object localObject1 = b();
    paramHSAppFilter = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (a)((Iterator)localObject1).next();
      if (localHSAppFilter.a((HSAppFilter.a)localObject2))
      {
        localObject2 = a.a((a)localObject2, paramClass);
        if (localObject2 != null) {
          paramHSAppFilter.add(localObject2);
        }
      }
    }
    return paramHSAppFilter;
  }
  
  /* Error */
  boolean a(String paramString, HSAppFilter paramHSAppFilter)
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 4
    //   3: aload_2
    //   4: ifnonnull +12 -> 16
    //   7: new 210	com/ihs/device/common/HSAppFilter
    //   10: dup
    //   11: invokespecial 211	com/ihs/device/common/HSAppFilter:<init>	()V
    //   14: astore 4
    //   16: aload_1
    //   17: invokestatic 179	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   20: ifeq +5 -> 25
    //   23: iconst_0
    //   24: ireturn
    //   25: aload_0
    //   26: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   29: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   32: invokevirtual 187	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   35: aload_0
    //   36: getfield 28	com/ihs/device/common/a:a	Ljava/util/Map;
    //   39: aload_1
    //   40: invokeinterface 203 2 0
    //   45: checkcast 10	com/ihs/device/common/a$a
    //   48: astore_1
    //   49: aload_1
    //   50: ifnull +28 -> 78
    //   53: aload 4
    //   55: aload_1
    //   56: invokevirtual 217	com/ihs/device/common/HSAppFilter:a	(Lcom/ihs/device/common/HSAppFilter$a;)Z
    //   59: istore_3
    //   60: iload_3
    //   61: ifeq +17 -> 78
    //   64: iconst_1
    //   65: istore_3
    //   66: aload_0
    //   67: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   70: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   73: invokevirtual 195	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   76: iload_3
    //   77: ireturn
    //   78: iconst_0
    //   79: istore_3
    //   80: goto -14 -> 66
    //   83: astore_1
    //   84: aload_0
    //   85: getfield 33	com/ihs/device/common/a:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   88: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   91: invokevirtual 195	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   94: aload_1
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	a
    //   0	96	1	paramString	String
    //   0	96	2	paramHSAppFilter	HSAppFilter
    //   59	21	3	bool	boolean
    //   1	53	4	localHSAppFilter	HSAppFilter
    // Exception table:
    //   from	to	target	type
    //   35	49	83	finally
    //   53	60	83	finally
  }
  
  static class a
    implements HSAppFilter.a
  {
    private final String a;
    private final String b;
    private final int c;
    private final String d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final int j;
    
    private a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
    {
      this(paramApplicationInfo, paramPackageManager, com.ihs.device.common.a.a.c(paramApplicationInfo.packageName));
    }
    
    private a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager, boolean paramBoolean)
    {
      this.a = paramApplicationInfo.packageName;
      paramPackageManager = paramPackageManager.getApplicationLabel(paramApplicationInfo);
      if (paramPackageManager == null) {}
      for (paramPackageManager = "";; paramPackageManager = paramPackageManager.toString().trim())
      {
        this.b = paramPackageManager;
        this.c = paramApplicationInfo.flags;
        this.d = paramApplicationInfo.publicSourceDir;
        this.e = com.ihs.device.common.a.a.a(paramApplicationInfo.packageName);
        this.f = com.ihs.device.common.a.a.a(paramApplicationInfo);
        this.g = com.ihs.device.common.a.a.b(paramApplicationInfo.packageName);
        this.h = paramBoolean;
        this.i = com.ihs.device.common.a.a.d(paramApplicationInfo.packageName);
        this.j = paramApplicationInfo.uid;
        return;
      }
    }
    
    private <T extends HSAppInfo> T a(Class<T> paramClass)
    {
      try
      {
        paramClass = (HSAppInfo)paramClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { this.a });
        paramClass.b(this.b);
        paramClass.a(this.c);
        paramClass.a(this.d);
        paramClass.c(this.e);
        paramClass.a(this.f);
        paramClass.b(this.g);
        paramClass.d(this.h);
        paramClass.e(this.i);
        paramClass.b(this.j);
        return paramClass;
      }
      catch (Exception paramClass)
      {
        e.e("AppInfoManager", "error getConstructor");
      }
      return null;
    }
    
    public String a()
    {
      return this.a;
    }
    
    public boolean b()
    {
      return this.e;
    }
    
    public boolean c()
    {
      return this.f;
    }
    
    public boolean d()
    {
      return this.g;
    }
    
    public boolean e()
    {
      return this.h;
    }
    
    public boolean f()
    {
      return this.i;
    }
    
    public boolean g()
    {
      return false;
    }
    
    public boolean h()
    {
      return false;
    }
  }
  
  private static class b
  {
    private static final a a = new a(null);
  }
}
