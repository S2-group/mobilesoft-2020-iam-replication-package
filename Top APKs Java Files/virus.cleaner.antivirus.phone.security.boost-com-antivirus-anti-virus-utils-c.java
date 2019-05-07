package com.antivirus.anti.virus.utils;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.antivirus.anti.virus.entity.AppInfo;
import com.antivirus.anti.virus.module.b.a.b;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class c
{
  private a a;
  private boolean b;
  
  public c() {}
  
  public static AppInfo a(Context paramContext, AppInfo paramAppInfo)
  {
    Object localObject1;
    Object localObject2;
    if (Build.VERSION.SDK_INT < 26) {
      try
      {
        localObject1 = paramContext.getPackageManager().getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
        if (localObject1 != null)
        {
          localObject2 = new CountDownLatch(1);
          ((Method)localObject1).invoke(paramContext.getPackageManager(), new Object[] { paramAppInfo.b(), new IPackageStatsObserver.Stub()
          {
            /* Error */
            public void onGetStatsCompleted(android.content.pm.PackageStats arg1, boolean paramAnonymousBoolean)
            {
              // Byte code:
              //   0: aload_0
              //   1: getfield 16	com/antivirus/anti/virus/utils/c$1:a	Lcom/antivirus/anti/virus/entity/AppInfo;
              //   4: astore 19
              //   6: aload 19
              //   8: monitorenter
              //   9: iload_2
              //   10: ifeq +79 -> 89
              //   13: aload_1
              //   14: getfield 30	android/content/pm/PackageStats:codeSize	J
              //   17: lstore_3
              //   18: aload_1
              //   19: getfield 33	android/content/pm/PackageStats:externalCodeSize	J
              //   22: lstore 5
              //   24: aload_1
              //   25: getfield 36	android/content/pm/PackageStats:dataSize	J
              //   28: lstore 7
              //   30: aload_1
              //   31: getfield 39	android/content/pm/PackageStats:externalDataSize	J
              //   34: lstore 9
              //   36: aload_1
              //   37: getfield 42	android/content/pm/PackageStats:cacheSize	J
              //   40: lstore 11
              //   42: aload_1
              //   43: getfield 45	android/content/pm/PackageStats:externalCacheSize	J
              //   46: lstore 13
              //   48: aload_1
              //   49: getfield 48	android/content/pm/PackageStats:externalMediaSize	J
              //   52: lstore 15
              //   54: aload_1
              //   55: getfield 51	android/content/pm/PackageStats:externalObbSize	J
              //   58: lstore 17
              //   60: aload_0
              //   61: getfield 16	com/antivirus/anti/virus/utils/c$1:a	Lcom/antivirus/anti/virus/entity/AppInfo;
              //   64: lload_3
              //   65: lload 5
              //   67: ladd
              //   68: lload 7
              //   70: lload 9
              //   72: ladd
              //   73: ladd
              //   74: lload 11
              //   76: lload 13
              //   78: ladd
              //   79: ladd
              //   80: lload 15
              //   82: ladd
              //   83: lload 17
              //   85: ladd
              //   86: invokevirtual 56	com/antivirus/anti/virus/entity/AppInfo:a	(J)V
              //   89: aload 19
              //   91: monitorexit
              //   92: aload_0
              //   93: getfield 18	com/antivirus/anti/virus/utils/c$1:b	Ljava/util/concurrent/CountDownLatch;
              //   96: astore_1
              //   97: aload_1
              //   98: monitorenter
              //   99: aload_0
              //   100: getfield 18	com/antivirus/anti/virus/utils/c$1:b	Ljava/util/concurrent/CountDownLatch;
              //   103: invokevirtual 61	java/util/concurrent/CountDownLatch:countDown	()V
              //   106: aload_1
              //   107: monitorexit
              //   108: return
              //   109: astore_1
              //   110: aload 19
              //   112: monitorexit
              //   113: aload_1
              //   114: athrow
              //   115: astore 19
              //   117: aload_1
              //   118: monitorexit
              //   119: aload 19
              //   121: athrow
              // Local variable table:
              //   start	length	slot	name	signature
              //   0	122	0	this	1
              //   0	122	2	paramAnonymousBoolean	boolean
              //   17	48	3	l1	long
              //   22	44	5	l2	long
              //   28	41	7	l3	long
              //   34	37	9	l4	long
              //   40	35	11	l5	long
              //   46	31	13	l6	long
              //   52	29	15	l7	long
              //   58	26	17	l8	long
              //   4	107	19	localAppInfo	AppInfo
              //   115	5	19	localObject	Object
              // Exception table:
              //   from	to	target	type
              //   13	89	109	finally
              //   89	92	109	finally
              //   110	113	109	finally
              //   99	108	115	finally
              //   117	119	115	finally
            }
          } });
          ((CountDownLatch)localObject2).await();
        }
        return paramAppInfo;
      }
      catch (Exception paramContext)
      {
        Log.e("AppManager", paramContext.toString());
        return paramAppInfo;
      }
    }
    for (;;)
    {
      try
      {
        if (!q.b(paramContext)) {
          break;
        }
        localObject1 = (StorageManager)paramContext.getSystemService("storage");
        if (localObject1 == null) {
          break;
        }
        localObject1 = ((StorageManager)localObject1).getStorageVolumes();
        if ((((List)localObject1).isEmpty()) || (((List)localObject1).get(0) == null)) {
          break;
        }
        localObject1 = ((StorageVolume)((List)localObject1).get(0)).getUuid();
        if (localObject1 == null)
        {
          localObject1 = StorageManager.UUID_DEFAULT;
          localObject2 = paramContext.getPackageManager().getApplicationInfo(paramAppInfo.b(), 0);
          paramContext = (StorageStatsManager)paramContext.getSystemService("storagestats");
          if (paramContext == null) {
            break;
          }
          paramContext = paramContext.queryStatsForUid((UUID)localObject1, ((ApplicationInfo)localObject2).uid);
          long l1 = paramContext.getCacheBytes();
          long l2 = paramContext.getDataBytes();
          paramAppInfo.a(paramContext.getAppBytes() + (l1 + l2));
          return paramAppInfo;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return paramAppInfo;
      }
      localObject1 = UUID.fromString((String)localObject1);
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    AppInfo localAppInfo = new AppInfo();
    localAppInfo.b(paramString);
    if (!b(localAppInfo))
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      for (;;)
      {
        try
        {
          paramString = localPackageManager.getPackageInfo(paramString, 0);
          int i = paramString.applicationInfo.flags;
          localAppInfo.a(paramString.applicationInfo.uid);
          if ((i & 0x1) != 0)
          {
            localAppInfo.b(false);
            if ((i & 0x40000) != 0)
            {
              localAppInfo.a(false);
              localAppInfo.a(paramString.applicationInfo.loadLabel(localPackageManager).toString());
              localAppInfo.c(paramString.versionName);
              localAppInfo.b(paramString.firstInstallTime);
              a(paramContext, localAppInfo);
              b.a().a(localAppInfo);
            }
          }
          else
          {
            localAppInfo.b(true);
            continue;
          }
          localAppInfo.a(true);
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
    }
  }
  
  public static void a(AppInfo paramAppInfo)
  {
    if (paramAppInfo == null) {}
    while (!b(paramAppInfo)) {
      return;
    }
    b.a().b(paramAppInfo);
  }
  
  public static void a(List<AppInfo> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {}
    Object localObject;
    ArrayList localArrayList;
    do
    {
      do
      {
        return;
        localObject = b();
      } while ((localObject == null) || (((List)localObject).isEmpty()));
      localArrayList = new ArrayList();
      localArrayList.addAll(paramList);
    } while (!((List)localObject).containsAll(localArrayList));
    paramList = localArrayList.iterator();
    while (paramList.hasNext())
    {
      localObject = (AppInfo)paramList.next();
      b.a().b((AppInfo)localObject);
    }
    b.a().a(localArrayList);
  }
  
  public static List<AppInfo> b()
  {
    return b.a().b();
  }
  
  public static boolean b(AppInfo paramAppInfo)
  {
    return b.a().c(paramAppInfo);
  }
  
  public void a()
  {
    this.b = true;
    if (this.a != null) {
      this.a.removeCallbacksAndMessages(null);
    }
  }
  
  public void a(Context paramContext, c paramC)
  {
    this.b = false;
    this.a = new a(paramContext, paramC);
    new Thread(new b(paramContext)).start();
  }
  
  private static class a
    extends Handler
  {
    private c.c a;
    private WeakReference<Context> b;
    
    public a(Context paramContext, c.c paramC)
    {
      this.b = new WeakReference(paramContext);
      this.a = paramC;
    }
    
    public void handleMessage(Message paramMessage)
    {
      Context localContext = (Context)this.b.get();
      if ((localContext != null) && (this.a != null)) {}
      switch (paramMessage.what)
      {
      default: 
        return;
      case 0: 
        this.a.a(localContext);
        return;
      case 1: 
        this.a.a(localContext, paramMessage.arg1, paramMessage.arg2, (AppInfo)paramMessage.obj);
        return;
      }
      this.a.a(localContext, (List)paramMessage.obj);
    }
  }
  
  private class b
    implements Runnable
  {
    private int b = 0;
    private WeakReference<Context> c;
    
    public b(Context paramContext)
    {
      this.c = new WeakReference(paramContext);
    }
    
    public void run()
    {
      Process.setThreadPriority(10);
      Context localContext = (Context)this.c.get();
      if ((localContext == null) || (c.a(c.this))) {
        return;
      }
      if (c.b(c.this) != null) {
        c.b(c.this).sendEmptyMessage(0);
      }
      ArrayList localArrayList = new ArrayList();
      for (;;)
      {
        AppInfo localAppInfo;
        try
        {
          PackageManager localPackageManager = localContext.getPackageManager();
          List localList = localPackageManager.getInstalledPackages(0);
          if (c.b(c.this) != null) {
            c.b(c.this).sendMessage(c.b(c.this).obtainMessage(1, 0, localList.size()));
          }
          Iterator localIterator = localList.iterator();
          if (localIterator.hasNext())
          {
            Object localObject = (PackageInfo)localIterator.next();
            localAppInfo = new AppInfo();
            int i = ((PackageInfo)localObject).applicationInfo.flags;
            localAppInfo.a(((PackageInfo)localObject).applicationInfo.uid);
            if ((i & 0x1) == 0) {
              break label433;
            }
            localAppInfo.b(false);
            if ((i & 0x40000) == 0) {
              break label442;
            }
            localAppInfo.a(false);
            localAppInfo.a(((PackageInfo)localObject).applicationInfo.loadLabel(localPackageManager).toString());
            localAppInfo.b(((PackageInfo)localObject).packageName);
            localAppInfo.c(((PackageInfo)localObject).versionName);
            localAppInfo.b(((PackageInfo)localObject).firstInstallTime);
            c.a(localContext, localAppInfo);
            if (c.b(c.this) != null)
            {
              localObject = c.b(c.this);
              c.a localA = c.b(c.this);
              i = this.b + 1;
              this.b = i;
              ((c.a)localObject).sendMessage(localA.obtainMessage(1, i, localList.size(), localAppInfo));
            }
            localArrayList.add(localAppInfo);
            continue;
          }
          if (TextUtils.isEmpty(PreferenceManager.getDefaultSharedPreferences(localContext).getString("installed_app_list", ""))) {
            break;
          }
        }
        catch (Exception localException)
        {
          Log.e("AppManager", localException.toString());
          b.a().c();
          b.a().a(localArrayList);
          if (c.b(c.this) != null) {
            c.b(c.this).sendMessage(c.b(c.this).obtainMessage(2, localArrayList));
          }
        }
        PreferenceManager.getDefaultSharedPreferences(localContext).edit().remove("installed_app_list").apply();
        return;
        label433:
        localAppInfo.b(true);
        continue;
        label442:
        localAppInfo.a(true);
      }
    }
  }
  
  public static abstract interface c
  {
    public abstract void a(Context paramContext);
    
    public abstract void a(Context paramContext, int paramInt1, int paramInt2, AppInfo paramAppInfo);
    
    public abstract void a(Context paramContext, List<AppInfo> paramList);
  }
}
