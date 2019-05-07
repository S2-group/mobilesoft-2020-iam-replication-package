package com.cleanmaster.boost.process.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.cleanmaster.base.c;
import com.cleanmaster.base.util.concurrent.BackgroundThread;
import com.cleanmaster.base.util.system.m;
import com.cleanmaster.base.util.system.o;
import com.cleanmaster.common_transition.report.r;
import com.cleanmaster.dao.l;
import com.cleanmaster.synipc.ISyncIpcService;
import com.cleanmaster.synipc.MemoryChangeParam;
import com.cleanmaster.synipc.b;
import com.cleanmaster.util.OpLog;
import com.keniu.security.d;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class f
{
  private static long a = -1L;
  private static Method b = null;
  private static Method c = null;
  
  private static int a(Debug.MemoryInfo paramMemoryInfo)
  {
    try
    {
      if (b == null) {
        b = paramMemoryInfo.getClass().getMethod("getTotalPss", new Class[0]);
      }
      int i = ((Integer)b.invoke(paramMemoryInfo, new Object[0])).intValue();
      return i;
    }
    catch (Exception paramMemoryInfo)
    {
      paramMemoryInfo.printStackTrace();
    }
    return 0;
  }
  
  public static int a(MemoryChangeParam paramMemoryChangeParam)
  {
    int i = 0;
    System.currentTimeMillis();
    ISyncIpcService localISyncIpcService;
    do
    {
      try
      {
        localISyncIpcService = b.a().c();
        if (localISyncIpcService != null) {
          try
          {
            i = localISyncIpcService.a(paramMemoryChangeParam);
            return i;
          }
          catch (RemoteException paramMemoryChangeParam)
          {
            paramMemoryChangeParam.printStackTrace();
            return 0;
          }
        }
        c.a(d.a().getApplicationContext(), 0);
      }
      finally {}
      SystemClock.sleep(100L);
      localISyncIpcService = b.a().c();
    } while (localISyncIpcService == null);
    try
    {
      i = localISyncIpcService.a(paramMemoryChangeParam);
      return i;
    }
    catch (RemoteException paramMemoryChangeParam)
    {
      paramMemoryChangeParam.printStackTrace();
    }
    return 0;
  }
  
  public static long a()
  {
    return d().b();
  }
  
  public static long a(ActivityManager paramActivityManager, ArrayList<Integer> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return 0L;
    }
    int j = paramArrayList.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = ((Integer)paramArrayList.get(i)).intValue();
      i += 1;
    }
    try
    {
      paramActivityManager = a(paramActivityManager, arrayOfInt);
      if ((paramActivityManager == null) || (paramActivityManager.length <= 0)) {
        break label130;
      }
      j = paramActivityManager.length;
      i = 0;
      l1 = 0L;
    }
    catch (Exception paramActivityManager)
    {
      try
      {
        int k = a(paramActivityManager[i]);
        l1 += k;
        i += 1;
      }
      catch (Exception paramActivityManager)
      {
        long l1;
        for (;;) {}
      }
      paramActivityManager = paramActivityManager;
      l1 = 0L;
    }
    long l2 = l1;
    if (i < j) {
      paramActivityManager.printStackTrace();
    }
    label130:
    for (l2 = l1;; l2 = 0L) {
      return l2 << 10;
    }
  }
  
  public static List<l> a(Context paramContext)
  {
    Comparator local2 = new Comparator() {};
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    if (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      l localL = new l();
      localL.a = localPackageInfo;
      if (Build.VERSION.SDK_INT >= 9) {
        long l = localPackageInfo.firstInstallTime;
      }
      for (localL.b = localPackageInfo.lastUpdateTime;; localL.b = m.e(localPackageInfo))
      {
        paramContext.add(localL);
        break;
        m.d(localPackageInfo);
      }
    }
    Collections.sort(paramContext, local2);
    return paramContext;
  }
  
  public static void a(String paramString)
  {
    String str1 = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str1 = "";
    }
    for (;;)
    {
      int i;
      try
      {
        paramString = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
        StringBuffer localStringBuffer = new StringBuffer();
        i = 0;
        String str2 = paramString.readLine();
        if ((str2 != null) && (i < 10))
        {
          if (!TextUtils.isEmpty(str2)) {
            localStringBuffer.append("\n" + str2);
          }
        }
        else
        {
          OpLog.d("Memory" + str1, localStringBuffer.toString());
          paramString.close();
          return;
        }
      }
      catch (Exception paramString)
      {
        OpLog.d("Memory" + str1, "MemoryInfo-->getTotalMem:" + paramString.toString());
        return;
      }
      i += 1;
    }
  }
  
  public static void a(List<ProcessModel> paramList, long paramLong, final int paramInt)
  {
    final long l1 = b();
    final long l2 = a();
    int i = c();
    if ((l1 < 0L) || (l2 < 0L) || (l1 <= l2) || (i <= 0) || (i >= 100))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      if ((paramList != null) && (paramList.size() > 0))
      {
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          ProcessModel localProcessModel = (ProcessModel)localIterator.next();
          if (localProcessModel != null)
          {
            String str2 = localProcessModel.c;
            String str1;
            if (TextUtils.isEmpty(str2)) {
              str1 = "";
            }
            for (;;)
            {
              localStringBuffer.append(str1 + ':' + localProcessModel.x + ';');
              break;
              str1 = str2;
              if (str2.contains("&")) {
                str1 = str2.replace("&", "_");
              }
            }
          }
        }
        paramList.clear();
      }
      BackgroundThread.a(new Runnable()
      {
        public final void run()
        {
          r localR = new r();
          localR.a = this.a;
          localR.b = l1;
          localR.d = l2;
          localR.e = g.c();
          localR.f = paramInt;
          localR.h = this.e;
          if (f.d().a() == 2) {}
          for (boolean bool = true;; bool = false)
          {
            localR.g = bool;
            localR.c = this.f;
            localR.a();
            return;
          }
        }
      });
    }
  }
  
  private static Debug.MemoryInfo[] a(ActivityManager paramActivityManager, int[] paramArrayOfInt)
  {
    try
    {
      if (c == null) {
        c = ActivityManager.class.getMethod("getProcessMemoryInfo", new Class[] { [I.class });
      }
      paramActivityManager = (Debug.MemoryInfo[])c.invoke(paramActivityManager, new Object[] { paramArrayOfInt });
      return paramActivityManager;
    }
    catch (Exception paramActivityManager)
    {
      paramActivityManager.printStackTrace();
    }
    return null;
  }
  
  public static long b()
  {
    if (a > 1L) {
      return a;
    }
    long l = d().c();
    a = l;
    return l;
  }
  
  public static int c()
  {
    int i = d().d();
    if ((i <= 0) || (i >= 100)) {
      a("HelperGet");
    }
    return i;
  }
  
  public static IPhoneMemoryInfo d()
  {
    Object localObject1;
    if (o.h())
    {
      localObject1 = g.a().b();
      return localObject1;
    }
    Object localObject6 = null;
    System.currentTimeMillis();
    for (;;)
    {
      try
      {
        localObject1 = b.a().c();
        if (localObject1 != null)
        {
          try
          {
            localObject1 = ((ISyncIpcService)localObject1).t();
            localObject6 = localObject1;
            localObject1 = localObject6;
            if (localObject6 != null) {
              break;
            }
            localObject1 = localObject6;
          }
          catch (RemoteException localRemoteException1)
          {
            localRemoteException1.printStackTrace();
            Object localObject2 = localObject6;
            continue;
          }
          localObject6 = localObject1;
          if (localObject1 == null) {
            localObject6 = new PhoneMemoryInfo(g.c(), g.d());
          }
          localObject1 = localObject6;
          if (a >= ((IPhoneMemoryInfo)localObject6).c()) {
            break;
          }
          localObject1 = localObject6;
          if (0L >= ((IPhoneMemoryInfo)localObject6).c()) {
            break;
          }
          a = ((IPhoneMemoryInfo)localObject6).c();
          return localObject6;
        }
      }
      finally {}
      c.a(d.a().getApplicationContext(), 0);
      SystemClock.sleep(100L);
      ISyncIpcService localISyncIpcService = b.a().c();
      Object localObject4 = localObject6;
      if (localISyncIpcService != null) {
        try
        {
          localObject4 = localISyncIpcService.t();
          localObject6 = localObject4;
          localObject4 = localObject6;
          if (localObject6 != null) {
            return localObject6;
          }
        }
        catch (RemoteException localRemoteException2)
        {
          localRemoteException2.printStackTrace();
          Object localObject5 = localObject6;
        }
      }
    }
  }
  
  public static long e()
  {
    if (a > 1L) {
      return a;
    }
    long l = g.d();
    a = l;
    return l;
  }
  
  public static long f()
  {
    long l2 = g.c();
    long l1 = l2;
    if (l2 <= 0L) {
      l1 = a();
    }
    return l1;
  }
  
  @TargetApi(11)
  public static List<a> g()
  {
    Object localObject1 = d.a();
    PackageManager localPackageManager = ((Context)localObject1).getPackageManager();
    localObject1 = (ActivityManager)((Context)localObject1).getSystemService("activity");
    if (Build.VERSION.SDK_INT >= 11) {}
    for (int i = 2;; i = 0)
    {
      localObject1 = ((ActivityManager)localObject1).getRecentTasks(16, i);
      ArrayList localArrayList = new ArrayList();
      ActivityInfo localActivityInfo = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").resolveActivityInfo(localPackageManager, 0);
      i = 0;
      while (i < ((List)localObject1).size())
      {
        Object localObject2 = (ActivityManager.RecentTaskInfo)((List)localObject1).get(i);
        Intent localIntent = new Intent(((ActivityManager.RecentTaskInfo)localObject2).baseIntent);
        if (((ActivityManager.RecentTaskInfo)localObject2).origActivity != null) {
          localIntent.setComponent(((ActivityManager.RecentTaskInfo)localObject2).origActivity);
        }
        if ((localActivityInfo == null) || (!localActivityInfo.packageName.equals(localIntent.getComponent().getPackageName())) || (!localActivityInfo.name.equals(localIntent.getComponent().getClassName())))
        {
          localIntent.setFlags(localIntent.getFlags() & 0xFFDFFFFF | 0x10000000);
          localObject2 = localPackageManager.resolveActivity(localIntent, 0);
          if (localObject2 != null)
          {
            localObject2 = ((ResolveInfo)localObject2).activityInfo;
            a localA = new a();
            localA.a = localIntent;
            localA.b = ((ActivityInfo)localObject2).packageName;
            localArrayList.add(localA);
          }
        }
        i += 1;
      }
      return localArrayList;
    }
  }
  
  public static final class a
  {
    public Intent a;
    public String b;
    
    public a() {}
  }
}
