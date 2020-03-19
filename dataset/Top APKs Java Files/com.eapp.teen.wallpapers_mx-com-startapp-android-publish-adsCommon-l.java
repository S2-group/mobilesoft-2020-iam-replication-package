package com.startapp.android.publish.adsCommon;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.h;
import com.startapp.common.Constants;
import com.startapp.common.a.c;
import com.startapp.common.g.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class l
{
  private static List<PackageInfo> a;
  private static List<PackageInfo> b;
  private static long c;
  private static volatile Pair<a, String> d = null;
  private static volatile Pair<a, String> e = null;
  private static boolean f = true;
  private static boolean g = false;
  private static a h = a.c;
  
  public static long a()
  {
    return c;
  }
  
  @VisibleForTesting
  static Pair<String, String> a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    for (;;)
    {
      Pair localPair3;
      Pair localPair1;
      try
      {
        com.startapp.common.a.g.a("SimpleToken", 3, "getSimpleToken entered");
        localPair3 = new Pair(a.a, "");
        if (!paramBoolean1) {
          break label213;
        }
        try
        {
          if (h != a.c) {
            continue;
          }
          paramBoolean1 = f;
          if ((g) && (!f)) {
            continue;
          }
          localPair2 = e(paramContext);
        }
        catch (Exception paramContext)
        {
          Pair localPair2;
          com.startapp.common.a.g.a("SimpleToken", 6, "failed to get simpleToken ", paramContext);
          localPair1 = localPair3;
          continue;
        }
        g = paramBoolean1;
        if (paramBoolean2)
        {
          localPair1 = localPair2;
          paramContext = new Pair(((a)localPair1.first).toString(), localPair1.second);
          return paramContext;
          localPair2 = f(paramContext);
          break label201;
          if (g) {
            break label208;
          }
          paramBoolean1 = true;
          continue;
        }
        localPair1 = localPair2;
        if (!k.a(paramContext, "shared_prefs_simple_token", "").equals(localPair2.second)) {
          continue;
        }
      }
      finally {}
      if (h == a.a)
      {
        localPair1 = e(paramContext);
      }
      else
      {
        localPair1 = f(paramContext);
        continue;
        label201:
        if (paramBoolean3)
        {
          continue;
          label208:
          paramBoolean1 = false;
          continue;
          label213:
          localPair1 = localPair3;
        }
      }
    }
  }
  
  private static List<String> a(List<PackageInfo> paramList)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "getPackagesNames entered");
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(((PackageInfo)paramList.next()).packageName);
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext)
  {
    c(paramContext);
    f = true;
    g = false;
    h = a.c;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    paramContext.getApplicationContext().registerReceiver(new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        l.b();
        l.c(paramAnonymousContext);
      }
    }, localIntentFilter);
    MetaData.getInstance().addMetaDataListener(new com.startapp.android.publish.common.metaData.d()
    {
      public void a()
      {
        MetaData.getInstance().addMetaDataListener(this);
      }
      
      public void a(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          l.b();
          l.c(this.a);
        }
        MetaData.getInstance().addMetaDataListener(this);
      }
    });
  }
  
  /* Error */
  @VisibleForTesting
  static void a(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc 53
    //   5: iconst_3
    //   6: ldc -76
    //   8: invokestatic 60	com/startapp/common/a/g:a	(Ljava/lang/String;ILjava/lang/String;)V
    //   11: getstatic 33	com/startapp/android/publish/adsCommon/l:d	Landroid/util/Pair;
    //   14: ifnull +11 -> 25
    //   17: getstatic 35	com/startapp/android/publish/adsCommon/l:e	Landroid/util/Pair;
    //   20: astore_2
    //   21: aload_2
    //   22: ifnonnull +119 -> 141
    //   25: iload_1
    //   26: ifeq +115 -> 141
    //   29: aload_0
    //   30: invokestatic 182	com/startapp/android/publish/adsCommon/l:g	(Landroid/content/Context;)V
    //   33: new 62	android/util/Pair
    //   36: dup
    //   37: getstatic 64	com/startapp/android/publish/adsCommon/l$a:a	Lcom/startapp/android/publish/adsCommon/l$a;
    //   40: getstatic 184	com/startapp/android/publish/adsCommon/l:a	Ljava/util/List;
    //   43: invokestatic 186	com/startapp/android/publish/adsCommon/l:a	(Ljava/util/List;)Ljava/util/List;
    //   46: invokestatic 191	com/startapp/common/f:a	(Ljava/util/List;)Ljava/lang/String;
    //   49: invokespecial 70	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   52: putstatic 33	com/startapp/android/publish/adsCommon/l:d	Landroid/util/Pair;
    //   55: new 62	android/util/Pair
    //   58: dup
    //   59: getstatic 193	com/startapp/android/publish/adsCommon/l$a:b	Lcom/startapp/android/publish/adsCommon/l$a;
    //   62: getstatic 195	com/startapp/android/publish/adsCommon/l:b	Ljava/util/List;
    //   65: invokestatic 186	com/startapp/android/publish/adsCommon/l:a	(Ljava/util/List;)Ljava/util/List;
    //   68: invokestatic 191	com/startapp/common/f:a	(Ljava/util/List;)Ljava/lang/String;
    //   71: invokespecial 70	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   74: putstatic 35	com/startapp/android/publish/adsCommon/l:e	Landroid/util/Pair;
    //   77: ldc 53
    //   79: iconst_3
    //   80: new 197	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   87: ldc -56
    //   89: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: getstatic 33	com/startapp/android/publish/adsCommon/l:d	Landroid/util/Pair;
    //   95: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   98: ldc -47
    //   100: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: invokestatic 60	com/startapp/common/a/g:a	(Ljava/lang/String;ILjava/lang/String;)V
    //   109: ldc 53
    //   111: iconst_3
    //   112: new 197	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   119: ldc -44
    //   121: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: getstatic 35	com/startapp/android/publish/adsCommon/l:e	Landroid/util/Pair;
    //   127: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   130: ldc -47
    //   132: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   138: invokestatic 60	com/startapp/common/a/g:a	(Ljava/lang/String;ILjava/lang/String;)V
    //   141: ldc 2
    //   143: monitorexit
    //   144: return
    //   145: astore_2
    //   146: ldc 53
    //   148: bipush 6
    //   150: ldc -42
    //   152: aload_2
    //   153: invokestatic 104	com/startapp/common/a/g:a	(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Throwable;)V
    //   156: aload_0
    //   157: getstatic 219	com/startapp/android/publish/adsCommon/f/d:b	Lcom/startapp/android/publish/adsCommon/f/d;
    //   160: ldc -35
    //   162: aload_2
    //   163: invokevirtual 224	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   166: ldc 66
    //   168: invokestatic 229	com/startapp/android/publish/adsCommon/f/f:a	(Landroid/content/Context;Lcom/startapp/android/publish/adsCommon/f/d;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   171: goto -30 -> 141
    //   174: astore_0
    //   175: ldc 2
    //   177: monitorexit
    //   178: aload_0
    //   179: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	180	0	paramContext	Context
    //   0	180	1	paramBoolean	boolean
    //   20	2	2	localPair	Pair
    //   145	18	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   29	141	145	java/lang/Exception
    //   3	21	174	finally
    //   29	141	174	finally
    //   146	171	174	finally
  }
  
  private static void a(PackageInfo paramPackageInfo, PackageManager paramPackageManager, Set<String> paramSet)
  {
    try
    {
      paramPackageManager = paramPackageManager.getInstallerPackageName(paramPackageInfo.packageName);
      if ((paramSet != null) && (paramSet.contains(paramPackageManager))) {
        b.add(paramPackageInfo);
      }
      return;
    }
    catch (Exception paramPackageInfo)
    {
      com.startapp.common.a.g.a("SimpleToken", 6, "addToPackagesFromInstallers - can't add app to list " + paramPackageInfo.getMessage());
    }
  }
  
  static void a(Pair<String, String> paramPair)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "errorSendingToken entered");
    h = a.valueOf((String)paramPair.first);
  }
  
  private static List<PackageInfo> b(List<PackageInfo> paramList)
  {
    if (paramList.size() <= 100) {
      return paramList;
    }
    paramList = new ArrayList(paramList);
    c(paramList);
    return paramList.subList(0, 100);
  }
  
  public static void b()
  {
    d = null;
    e = null;
  }
  
  public static void b(Context paramContext)
  {
    a(paramContext, MetaData.getInstance().getSimpleTokenConfig().b(paramContext));
  }
  
  public static Pair<String, String> c()
  {
    if (d != null) {
      return new Pair(((a)d.first).toString(), d.second);
    }
    return new Pair(a.a.toString(), "");
  }
  
  public static void c(Context paramContext)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "initSimpleTokenAsync entered");
    try
    {
      if (((d == null) || (e == null)) && (MetaData.getInstance().getSimpleTokenConfig().b(paramContext))) {
        com.startapp.common.g.a(g.a.b, new Runnable()
        {
          public void run()
          {
            l.b(this.a);
          }
        });
      }
      return;
    }
    catch (Exception localException)
    {
      com.startapp.common.a.g.a("SimpleToken", 6, "initSimpleTokenAsync failed", localException);
      f.a(paramContext, com.startapp.android.publish.adsCommon.f.d.b, "initSimpleTokenAsync", localException.getMessage(), "");
    }
  }
  
  private static void c(List<PackageInfo> paramList)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new Comparator()
      {
        @SuppressLint({"InlinedApi"})
        public int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
        {
          long l1 = paramAnonymousPackageInfo1.firstInstallTime;
          long l2 = paramAnonymousPackageInfo2.firstInstallTime;
          if (l1 > l2) {
            return -1;
          }
          if (l1 == l2) {
            return 0;
          }
          return 1;
        }
      });
    }
  }
  
  public static Pair<String, String> d()
  {
    if (e != null) {
      return new Pair(((a)e.first).toString(), e.second);
    }
    return new Pair(a.b.toString(), "");
  }
  
  static Pair<String, String> d(Context paramContext)
  {
    return a(paramContext, MetaData.getInstance().getSimpleTokenConfig().b(paramContext), MetaData.getInstance().isAlwaysSendToken(), MetaData.getInstance().isToken1Mandatory());
  }
  
  private static Pair<a, String> e(Context paramContext)
  {
    if (d == null) {
      b(paramContext);
    }
    k.b(paramContext, "shared_prefs_simple_token", (String)d.second);
    f = false;
    h = a.c;
    return new Pair(a.a, d.second);
  }
  
  private static Pair<a, String> f(Context paramContext)
  {
    if (e == null) {
      b(paramContext);
    }
    k.b(paramContext, "shared_prefs_simple_token2", (String)e.second);
    f = false;
    h = a.c;
    return new Pair(a.b, e.second);
  }
  
  private static void g(Context paramContext)
  {
    for (;;)
    {
      Object localObject;
      try
      {
        com.startapp.common.a.g.a("SimpleToken", 3, "getPackages entered");
        PackageManager localPackageManager = paramContext.getPackageManager();
        Set localSet1 = MetaData.getInstance().getInstallersList();
        Set localSet2 = MetaData.getInstance().getPreInstalledPackages();
        a = new CopyOnWriteArrayList();
        b = new CopyOnWriteArrayList();
        PackageInfo localPackageInfo;
        try
        {
          localObject = c.a(localPackageManager);
          if (Build.VERSION.SDK_INT >= 9)
          {
            l = Long.MAX_VALUE;
            c = l;
            paramContext = null;
            Iterator localIterator = ((List)localObject).iterator();
            if (!localIterator.hasNext()) {
              continue;
            }
            localPackageInfo = (PackageInfo)localIterator.next();
            if ((Build.VERSION.SDK_INT >= 9) && (c > localPackageInfo.firstInstallTime)) {
              c = localPackageInfo.firstInstallTime;
            }
            if (c.a(localPackageInfo)) {
              continue;
            }
            a.add(localPackageInfo);
            a(localPackageInfo, localPackageManager, localSet1);
            localObject = paramContext;
          }
        }
        catch (Exception paramContext)
        {
          com.startapp.common.a.g.a("SimpleToken", 6, "Could not complete getInstalledPackages", paramContext);
          return;
        }
        long l = 0L;
        continue;
        if (localSet2.contains(localPackageInfo.packageName))
        {
          a.add(localPackageInfo);
          localObject = paramContext;
        }
        else
        {
          localObject = localPackageInfo;
          if (!localPackageInfo.packageName.equals(Constants.a)) {
            localObject = paramContext;
          }
        }
      }
      finally {}
      a = b(a);
      b = b(b);
      if (paramContext != null)
      {
        a.add(0, paramContext);
        continue;
        paramContext = (Context)localObject;
      }
    }
  }
  
  private static enum a
  {
    private final String text;
    
    private a(String paramString)
    {
      this.text = paramString;
    }
    
    public String toString()
    {
      return this.text;
    }
  }
}
