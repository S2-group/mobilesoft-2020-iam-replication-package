package com.rookiestudio.perfectviewer.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import dalvik.system.PathClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TPluginList
{
  private ArrayList<TPluginSource> FSourcePluginList = null;
  private Context context;
  
  public TPluginList(Context paramContext)
  {
    this.context = paramContext;
    this.FSourcePluginList = new ArrayList();
    paramContext = this.context.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.startsWith("com.rookiestudio.perfectviewer.plugin")) {
        try
        {
          CreatePlugin(paramContext.getPackageInfo(localApplicationInfo.packageName, 0));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public TPluginBase CreatePlugin(PackageInfo paramPackageInfo)
  {
    PathClassLoader localPathClassLoader = new PathClassLoader(paramPackageInfo.applicationInfo.sourceDir, paramPackageInfo.applicationInfo.nativeLibraryDir, getClass().getClassLoader());
    int i = 1;
    Object localObject1 = null;
    if (i < 10) {}
    Object localObject2;
    label194:
    for (;;)
    {
      try
      {
        localObject2 = localPathClassLoader.loadClass("com.rookiestudio.perfectviewer.plugin.source.PluginCore" + i);
        localClass = localPathClassLoader.loadClass("com.rookiestudio.perfectviewer.plugin.source.RemoteFile" + i);
        if (!paramPackageInfo.packageName.startsWith("com.rookiestudio.perfectviewer.plugin.source")) {
          break label194;
        }
        localObject2 = new TPluginSource((Class)localObject2, this.context);
      }
      catch (Exception paramPackageInfo)
      {
        Class localClass;
        paramPackageInfo.printStackTrace();
        return localObject1;
      }
      try
      {
        ((TPluginSource)localObject2).PluginVersion = paramPackageInfo.versionCode;
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          ((TPluginSource)localObject2).LocationID = (this.FSourcePluginList.size() + 10);
          ((TPluginSource)localObject2).RemoteFileClazz = localClass;
          this.FSourcePluginList.add(localObject2);
          localObject1 = localObject2;
        }
        i += 1;
      }
      catch (Exception paramPackageInfo)
      {
        localObject1 = localObject2;
        continue;
      }
      return localObject1;
    }
  }
  
  public TPluginSource FindPlugin(int paramInt)
  {
    Iterator localIterator = this.FSourcePluginList.iterator();
    while (localIterator.hasNext())
    {
      TPluginSource localTPluginSource = (TPluginSource)localIterator.next();
      if (localTPluginSource.LocationID == paramInt) {
        return localTPluginSource;
      }
    }
    return null;
  }
  
  public TPluginSource FindPlugin(String paramString)
  {
    Iterator localIterator = this.FSourcePluginList.iterator();
    while (localIterator.hasNext())
    {
      TPluginSource localTPluginSource = (TPluginSource)localIterator.next();
      if (paramString.startsWith(localTPluginSource.URIPrefix)) {
        return localTPluginSource;
      }
    }
    return null;
  }
  
  public TPluginSource GetSourcePlugin(int paramInt)
  {
    return (TPluginSource)this.FSourcePluginList.get(paramInt);
  }
  
  public int SourcePluginCount()
  {
    return this.FSourcePluginList.size();
  }
}
