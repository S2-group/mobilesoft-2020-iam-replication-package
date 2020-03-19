package com.hyperspeed.rocket.applock.free;

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

public final class cal
{
  final PackageManager as = bdr.as().getPackageManager();
  final Map<String, ApplicationInfo> er = new ConcurrentHashMap();
  public final Object hv = new Object();
  final AtomicBoolean jd = new AtomicBoolean(false);
  final Map<String, String> td = new ConcurrentHashMap();
  final Set<String> xv = new CopyOnWriteArraySet();
  
  private cal()
  {
    Object localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject).addDataScheme("package");
    bdr.as().registerReceiver(new BroadcastReceiver()
    {
      public final void onReceive(Context paramAnonymousContext, final Intent paramAnonymousIntent)
      {
        new Thread(new Runnable()
        {
          public final void run()
          {
            cal localCal;
            Object localObject2;
            if ("android.intent.action.PACKAGE_ADDED".equals(paramAnonymousIntent.getAction())) {
              if (paramAnonymousIntent.getData() != null)
              {
                localCal = cal.this;
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
                  localCal.er(localCal.as.getApplicationInfo((String)localObject2, 128));
                  return;
                }
                catch (Exception localException)
                {
                  localException.printStackTrace();
                  return;
                }
              } while ((!"android.intent.action.PACKAGE_REMOVED".equals(paramAnonymousIntent.getAction())) || (paramAnonymousIntent.getData() == null));
              localObject2 = cal.this;
              str = paramAnonymousIntent.getData().getSchemeSpecificPart();
            } while (TextUtils.isEmpty(str));
            synchronized (((cal)localObject2).hv)
            {
              ((cal)localObject2).td.remove(str);
              ((cal)localObject2).xv.remove(str);
              ((cal)localObject2).er.remove(str);
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
        Iterator localIterator = cal.this.er().iterator();
        while (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          cal.this.er(localApplicationInfo);
        }
        cal.this.jd.set(true);
        new StringBuilder("init finished with: ").append(System.currentTimeMillis() - l).append("ms");
      }
    });
    ((Thread)localObject).setPriority(1);
    ((Thread)localObject).start();
  }
  
  private boolean xv(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    for (;;)
    {
      return false;
      paramApplicationInfo = paramApplicationInfo.packageName;
      try
      {
        paramApplicationInfo = this.as.getLaunchIntentForPackage(paramApplicationInfo);
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
  
  public final ApplicationInfo as(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (this.jd.get()) {
      synchronized (this.hv)
      {
        paramString = (ApplicationInfo)this.er.get(paramString);
        return paramString;
      }
    }
    try
    {
      paramString = this.as.getApplicationInfo(paramString, 128);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public final String as(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {
      return null;
    }
    return er(paramApplicationInfo.packageName);
  }
  
  public final List<ApplicationInfo> as()
  {
    Object localObject2;
    if (this.jd.get())
    {
      synchronized (this.hv)
      {
        ArrayList localArrayList = new ArrayList();
        localObject2 = this.er.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          if ((!TextUtils.equals(str, bdr.as().getPackageName())) && (this.xv.contains(str))) {
            localArrayList.add(this.er.get(str));
          }
        }
      }
      return localList;
    }
    ??? = new ArrayList();
    Iterator localIterator = er().iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (ApplicationInfo)localIterator.next();
      if ((!TextUtils.equals(((ApplicationInfo)localObject2).packageName, bdr.as().getPackageName())) && (xv((ApplicationInfo)localObject2))) {
        ((List)???).add(localObject2);
      }
    }
    return ???;
  }
  
  public final String er(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      if (this.jd.get()) {
        synchronized (this.hv)
        {
          paramString = (String)this.td.get(paramString);
          return paramString;
        }
      }
      try
      {
        paramString = this.as.getApplicationInfo(paramString, 128);
        if (paramString != null)
        {
          paramString = this.as.getApplicationLabel(paramString).toString();
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
  
  final List<ApplicationInfo> er()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      List localList = this.as.getInstalledApplications(128);
      return localList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localArrayList;
  }
  
  final void er(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {
      return;
    }
    try
    {
      synchronized (this.hv)
      {
        String str = this.as.getApplicationLabel(paramApplicationInfo).toString();
        this.er.put(paramApplicationInfo.packageName, paramApplicationInfo);
        if (xv(paramApplicationInfo)) {
          this.xv.add(paramApplicationInfo.packageName);
        }
        this.td.put(paramApplicationInfo.packageName, str);
        return;
      }
      return;
    }
    catch (Exception paramApplicationInfo)
    {
      paramApplicationInfo.printStackTrace();
    }
  }
  
  public static final class a
  {
    private static final cal as = new cal((byte)0);
  }
}
