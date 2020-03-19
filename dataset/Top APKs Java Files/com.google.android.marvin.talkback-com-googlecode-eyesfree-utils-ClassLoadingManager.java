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
  private final HashSet<String> mInstalledPackagesSet = new HashSet();
  private final HashMap<String, HashSet<String>> mNotFoundClassesMap = new HashMap();
  private final BasePackageMonitor mPackageMonitor = new BasePackageMonitor()
  {
    protected void onPackageAdded(String paramAnonymousString)
    {
      ClassLoadingManager.this.addInstalledPackageToCache(paramAnonymousString);
    }
    
    protected void onPackageChanged(String paramAnonymousString) {}
    
    protected void onPackageRemoved(String paramAnonymousString)
    {
      ClassLoadingManager.this.removeInstalledPackageFromCache(paramAnonymousString);
    }
  };
  
  public ClassLoadingManager() {}
  
  private void addInstalledPackageToCache(String paramString)
  {
    synchronized (this.mInstalledPackagesSet)
    {
      this.mInstalledPackagesSet.add(paramString);
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
  
  private void clearInstalledPackagesCache()
  {
    synchronized (this.mInstalledPackagesSet)
    {
      this.mInstalledPackagesSet.clear();
      return;
    }
  }
  
  public static ClassLoadingManager getInstance()
  {
    if (sInstance == null) {
      sInstance = new ClassLoadingManager();
    }
    return sInstance;
  }
  
  private void removeInstalledPackageFromCache(String paramString)
  {
    synchronized (this.mInstalledPackagesSet)
    {
      this.mInstalledPackagesSet.remove(paramString);
      return;
    }
  }
  
  public boolean checkInstanceOf(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3)
  {
    if ((paramCharSequence1 == null) || (paramCharSequence3 == null)) {}
    do
    {
      return false;
      if (TextUtils.equals(paramCharSequence1, paramCharSequence3)) {
        return true;
      }
      paramCharSequence3 = loadOrGetCachedClass(paramContext, paramCharSequence3, paramCharSequence2);
    } while (paramCharSequence3 == null);
    return checkInstanceOf(paramContext, paramCharSequence1, paramCharSequence2, paramCharSequence3);
  }
  
  public boolean checkInstanceOf(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, Class<?> paramClass)
  {
    if ((paramCharSequence1 == null) || (paramClass == null)) {}
    do
    {
      return false;
      paramContext = loadOrGetCachedClass(paramContext, paramCharSequence1, paramCharSequence2);
    } while (paramContext == null);
    return paramClass.isAssignableFrom(paramContext);
  }
  
  public void init(Context paramContext)
  {
    buildInstalledPackagesCache(paramContext);
    this.mPackageMonitor.register(paramContext);
  }
  
  public Class<?> loadOrGetCachedClass(Context paramContext, CharSequence arg2, CharSequence paramCharSequence2)
  {
    if (TextUtils.isEmpty(???))
    {
      LogUtils.log(this, 3, "Missing class name. Failed to load class.", new Object[0]);
      ??? = null;
    }
    Object localObject;
    String str1;
    String str2;
    do
    {
      return ???;
      localObject = paramCharSequence2;
      if (TextUtils.isEmpty(paramCharSequence2))
      {
        int i = TextUtils.lastIndexOf(???, '.');
        if (i < 0)
        {
          LogUtils.log(this, 3, "Missing package name. Failed to load class: %s", new Object[] { ??? });
          return null;
        }
        localObject = TextUtils.substring(???, 0, i);
      }
      str1 = ???.toString();
      str2 = localObject.toString();
      synchronized (this.mInstalledPackagesSet)
      {
        paramCharSequence2 = (HashSet)this.mNotFoundClassesMap.get(str2);
        if ((paramCharSequence2 != null) && (paramCharSequence2.contains(str1))) {
          return null;
        }
      }
      localObject = (Class)this.mClassNameToClassMap.get(str1);
      ??? = (CharSequence)localObject;
    } while (localObject != null);
    try
    {
      ??? = getClass().getClassLoader().loadClass(str1);
      if (??? != null)
      {
        this.mClassNameToClassMap.put(str1, ???);
        return ???;
      }
    }
    catch (ClassNotFoundException ???)
    {
      if (paramContext == null) {
        return null;
      }
      try
      {
        paramContext = paramContext.createPackageContext(str2, 3).getClassLoader().loadClass(str1);
        if (paramContext != null)
        {
          this.mClassNameToClassMap.put(str1, paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        LogUtils.log(this, 6, "Error encountered. Failed to load outside class: %s", new Object[] { str1 });
        paramContext = paramCharSequence2;
        if (paramCharSequence2 == null)
        {
          paramContext = new HashSet();
          this.mNotFoundClassesMap.put(str2, paramContext);
        }
        paramContext.add(str1);
        LogUtils.log(3, "Failed to load class: %s", new Object[] { str1 });
      }
    }
    return null;
  }
  
  public void shutdown()
  {
    clearInstalledPackagesCache();
    this.mClassNameToClassMap.clear();
    this.mPackageMonitor.unregister();
  }
}
