package com.googlecode.eyesfree.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ClassLoadingManager
{
  private static ClassLoadingManager sInstance;
  private final HashMap<String, Class<?>> mClassNameToClassMap = new HashMap();
  private final HashMap<String, HashSet<String>> mNotFoundClassesMap = new HashMap();
  private final BasePackageMonitor mPackageMonitor = new BasePackageMonitor()
  {
    protected void onPackageAdded(String paramAnonymousString)
    {
      ClassLoadingManager.this.addInstalledPackageToCache(paramAnonymousString);
    }
    
    protected void onPackageChanged(String paramAnonymousString) {}
    
    protected void onPackageRemoved(String paramAnonymousString) {}
  };
  
  public ClassLoadingManager() {}
  
  private void addInstalledPackageToCache(String paramString)
  {
    synchronized (this.mNotFoundClassesMap)
    {
      this.mNotFoundClassesMap.remove(paramString);
      return;
    }
  }
  
  private void buildInstalledPackagesCache(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      addInstalledPackageToCache(((PackageInfo)paramContext.next()).packageName);
    }
  }
  
  public static ClassLoadingManager getInstance()
  {
    if (sInstance == null) {
      sInstance = new ClassLoadingManager();
    }
    return sInstance;
  }
  
  public boolean checkInstanceOf(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3)
  {
    if (paramCharSequence1 != null)
    {
      if (paramCharSequence3 == null) {
        return false;
      }
      if (TextUtils.equals(paramCharSequence1, paramCharSequence3)) {
        return true;
      }
      paramCharSequence3 = loadOrGetCachedClass(paramContext, paramCharSequence3, paramCharSequence2);
      if (paramCharSequence3 == null) {
        return false;
      }
      return checkInstanceOf(paramContext, paramCharSequence1, paramCharSequence2, paramCharSequence3);
    }
    return false;
  }
  
  public boolean checkInstanceOf(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, Class<?> paramClass)
  {
    if (paramCharSequence1 != null)
    {
      if (paramClass == null) {
        return false;
      }
      paramContext = loadOrGetCachedClass(paramContext, paramCharSequence1, paramCharSequence2);
      if (paramContext == null) {
        return false;
      }
      return paramClass.isAssignableFrom(paramContext);
    }
    return false;
  }
  
  public void init(Context paramContext)
  {
    if (!this.mNotFoundClassesMap.isEmpty()) {
      buildInstalledPackagesCache(paramContext);
    }
    this.mPackageMonitor.register(paramContext);
  }
  
  public Class<?> loadOrGetCachedClass(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    if (TextUtils.isEmpty(paramCharSequence1))
    {
      LogUtils.log(this, 3, "Missing class name. Failed to load class.", new Object[0]);
      return null;
    }
    Object localObject1 = paramCharSequence2;
    if (TextUtils.isEmpty(paramCharSequence2))
    {
      int i = TextUtils.lastIndexOf(paramCharSequence1, '.');
      if (i < 0)
      {
        LogUtils.log(this, 3, "Missing package name. Failed to load class: %s", new Object[] { paramCharSequence1 });
        return null;
      }
      localObject1 = TextUtils.substring(paramCharSequence1, 0, i);
    }
    paramCharSequence2 = paramCharSequence1.toString();
    localObject1 = ((CharSequence)localObject1).toString();
    synchronized (this.mNotFoundClassesMap)
    {
      paramCharSequence1 = (HashSet)this.mNotFoundClassesMap.get(localObject1);
      if ((paramCharSequence1 != null) && (paramCharSequence1.contains(paramCharSequence2))) {
        return null;
      }
      ??? = (Class)this.mClassNameToClassMap.get(paramCharSequence2);
      if (??? != null) {
        return ???;
      }
      try
      {
        ??? = getClass().getClassLoader().loadClass(paramCharSequence2);
        if (??? != null)
        {
          this.mClassNameToClassMap.put(paramCharSequence2, ???);
          return ???;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;) {}
      }
      if (paramContext == null) {
        return null;
      }
      try
      {
        paramContext = paramContext.createPackageContext((String)localObject1, 3).getClassLoader().loadClass(paramCharSequence2);
        if (paramContext == null) {
          break label233;
        }
        this.mClassNameToClassMap.put(paramCharSequence2, paramContext);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        label233:
        for (;;) {}
      }
      LogUtils.log(this, 6, "Error encountered. Failed to load outside class: %s", new Object[] { paramCharSequence2 });
      paramContext = paramCharSequence1;
      if (paramCharSequence1 == null)
      {
        paramContext = new HashSet();
        this.mNotFoundClassesMap.put(localObject1, paramContext);
      }
      paramContext.add(paramCharSequence2);
      LogUtils.log(3, "Failed to load class: %s", new Object[] { paramCharSequence2 });
      return null;
    }
  }
  
  public void shutdown()
  {
    this.mClassNameToClassMap.clear();
    this.mPackageMonitor.unregister();
  }
}
