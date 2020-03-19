package com.optimizer.test.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a
{
  final PackageManager a = com.ihs.app.framework.a.a().getPackageManager();
  final Map<String, ApplicationInfo> b = new ConcurrentHashMap();
  final Set<String> c = new CopyOnWriteArraySet();
  final Map<String, String> d = new ConcurrentHashMap();
  public final Object e = new Object();
  final AtomicBoolean f = new AtomicBoolean(false);
  
  private a()
  {
    Object localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject).addDataScheme("package");
    com.ihs.app.framework.a.a().registerReceiver(new BroadcastReceiver()
    {
      public final void onReceive(Context paramAnonymousContext, final Intent paramAnonymousIntent)
      {
        new Thread(new Runnable()
        {
          public final void run()
          {
            a localA;
            Object localObject2;
            if ("android.intent.action.PACKAGE_ADDED".equals(paramAnonymousIntent.getAction())) {
              if (paramAnonymousIntent.getData() != null)
              {
                localA = a.this;
                localObject2 = paramAnonymousIntent.getData().getSchemeSpecificPart();
                if (TextUtils.isEmpty((CharSequence)localObject2)) {}
              }
            }
            String str;
            do
            {
              do
              {
                try
                {
                  localA.b(localA.a.getApplicationInfo((String)localObject2, 128));
                  return;
                }
                catch (Exception localException)
                {
                  localException.printStackTrace();
                  return;
                }
              } while ((!"android.intent.action.PACKAGE_REMOVED".equals(paramAnonymousIntent.getAction())) || (paramAnonymousIntent.getData() == null));
              localObject2 = a.this;
              str = paramAnonymousIntent.getData().getSchemeSpecificPart();
            } while (TextUtils.isEmpty(str));
            synchronized (((a)localObject2).e)
            {
              ((a)localObject2).d.remove(str);
              ((a)localObject2).c.remove(str);
              ((a)localObject2).b.remove(str);
              return;
            }
          }
        }).start();
      }
    }, (IntentFilter)localObject);
    localObject = new Thread(new Runnable()
    {
      public final void run()
      {
        long l = System.currentTimeMillis();
        Iterator localIterator = a.this.b().iterator();
        while (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          a.this.b(localApplicationInfo);
        }
        a.this.f.set(true);
        new StringBuilder("init finished with: ").append(System.currentTimeMillis() - l).append("ms");
      }
    });
    ((Thread)localObject).setPriority(1);
    ((Thread)localObject).start();
  }
  
  private boolean c(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    for (;;)
    {
      return false;
      paramApplicationInfo = paramApplicationInfo.packageName;
      try
      {
        paramApplicationInfo = this.a.getLaunchIntentForPackage(paramApplicationInfo);
        if (paramApplicationInfo != null) {
          return true;
        }
      }
      catch (RuntimeException paramApplicationInfo)
      {
        paramApplicationInfo.printStackTrace();
      }
    }
    return false;
  }
  
  public final ApplicationInfo a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (this.f.get()) {
      synchronized (this.e)
      {
        paramString = (ApplicationInfo)this.b.get(paramString);
        return paramString;
      }
    }
    try
    {
      paramString = this.a.getApplicationInfo(paramString, 128);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public final String a(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {
      return null;
    }
    return b(paramApplicationInfo.packageName);
  }
  
  public final List<ApplicationInfo> a()
  {
    Object localObject2;
    if (this.f.get())
    {
      synchronized (this.e)
      {
        ArrayList localArrayList = new ArrayList();
        localObject2 = this.b.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          if ((!TextUtils.equals(str, com.ihs.app.framework.a.a().getPackageName())) && (this.c.contains(str))) {
            localArrayList.add(this.b.get(str));
          }
        }
      }
      return localList;
    }
    ??? = new ArrayList();
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (ApplicationInfo)localIterator.next();
      if ((!TextUtils.equals(((ApplicationInfo)localObject2).packageName, com.ihs.app.framework.a.a().getPackageName())) && (c((ApplicationInfo)localObject2))) {
        ((List)???).add(localObject2);
      }
    }
    return ???;
  }
  
  public final String b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      if (this.f.get()) {
        synchronized (this.e)
        {
          paramString = (String)this.d.get(paramString);
          return paramString;
        }
      }
      try
      {
        paramString = this.a.getApplicationInfo(paramString, 128);
        if (paramString != null)
        {
          paramString = this.a.getApplicationLabel(paramString).toString();
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  final List<ApplicationInfo> b()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      List localList = this.a.getInstalledApplications(128);
      return localList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localArrayList;
  }
  
  final void b(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {
      return;
    }
    try
    {
      synchronized (this.e)
      {
        String str = this.a.getApplicationLabel(paramApplicationInfo).toString();
        this.b.put(paramApplicationInfo.packageName, paramApplicationInfo);
        if (c(paramApplicationInfo)) {
          this.c.add(paramApplicationInfo.packageName);
        }
        this.d.put(paramApplicationInfo.packageName, str);
        return;
      }
      return;
    }
    catch (Exception paramApplicationInfo)
    {
      paramApplicationInfo.printStackTrace();
    }
  }
  
  private static final class a
  {
    private static final a a = new a((byte)0);
  }
}
