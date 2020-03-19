package com.tinysolutionsllc.plugin;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ae.d;
import android.util.Log;
import com.alexvas.dvr.core.b;
import com.alexvas.dvr.u.ab;
import com.alexvas.dvr.u.ac;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PluginRegistry
{
  private static final boolean DEBUG = false;
  private static PluginRegistry INSTANCE = null;
  private static final String PLUGIN_MANIFEST_ENTRY_POINT = "entryPoint";
  private static final String PLUGIN_PACKAGE = "com.tinysolutionsllc.plugin";
  private static final String TAG = PluginRegistry.class.getSimpleName();
  private static final Object g_instanceLock = new Object();
  private final Map<String, Plugin> _internalMap;
  
  private PluginRegistry(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
    localIntentFilter.addDataScheme("package");
    paramContext.registerReceiver(new PluginRegistryBroadcastReceiver(null), localIntentFilter);
    this._internalMap = findAvailablePluginPackages(paramContext);
  }
  
  private Map<String, Plugin> findAvailablePluginPackages(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(136);
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (((PackageInfo)localObject2).packageName.startsWith("com.tinysolutionsllc.plugin"))
      {
        localObject2 = instantiatePlugin(paramContext, (PackageInfo)localObject2);
        if (localObject2 != null) {
          localLinkedHashMap.put(localObject2.getClass().getCanonicalName(), localObject2);
        }
      }
    }
    return localLinkedHashMap;
  }
  
  public static PluginRegistry getInstance(Context paramContext)
  {
    if (INSTANCE == null) {}
    synchronized (g_instanceLock)
    {
      if (INSTANCE == null)
      {
        INSTANCE = new PluginRegistry(paramContext.getApplicationContext());
        Log.i("DB", "Loaded plugin registry");
      }
      return INSTANCE;
    }
  }
  
  private Plugin instantiatePlugin(Context paramContext, PackageInfo paramPackageInfo)
  {
    try
    {
      localPlugin1 = (Plugin)Class.forName(paramPackageInfo.applicationInfo.metaData.getString("entryPoint")).newInstance();
      localPlugin1.init(paramContext);
      localPlugin2 = localPlugin1;
      if (localPlugin1 != null) {
        localPlugin2 = localPlugin1;
      }
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        try
        {
          Plugin localPlugin1;
          Plugin localPlugin2;
          if (localPlugin1.getAppMinVersionSupported() > ac.c(paramContext))
          {
            Log.e(TAG, "Plugin expects min app ver " + localPlugin1.getAppMinVersionSupported() + ", but app has " + ac.c(paramContext) + " version");
            showErrorNotification(paramContext, "Plugin " + paramPackageInfo.packageName + " failed", "Plugin expects newer version of app. Be sure both app and plugin have the latest version.");
            localPlugin2 = null;
          }
          return localPlugin2;
        }
        catch (Throwable localThrowable2)
        {
          Object localObject;
          showErrorNotification(paramContext, "Plugin " + paramPackageInfo.packageName + " failed", "Be sure both app and plugin have the latest version.");
          localThrowable2.printStackTrace();
        }
        localThrowable1 = localThrowable1;
        showErrorNotification(paramContext, "Plugin " + paramPackageInfo.packageName + " failed", "Be sure both app and plugin have the latest version.");
        localThrowable1.printStackTrace();
        localObject = null;
      }
    }
    return null;
  }
  
  private void showErrorNotification(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new ae.d(paramContext).a(paramString1).b(paramString2).a(2130838146).d(ab.f(paramContext));
    ((NotificationManager)paramContext.getSystemService("notification")).notify(b.n, paramString1.b());
  }
  
  void dispatchPluginAdded(String paramString) {}
  
  void dispatchPluginRemoved(String paramString) {}
  
  void dispatchPluginUpdated(String paramString) {}
  
  public Plugin getPluginByPackageName(String paramString)
  {
    return (Plugin)this._internalMap.get(paramString);
  }
  
  public List<Plugin> getPlugins()
  {
    return new ArrayList(this._internalMap.values());
  }
  
  private class PluginRegistryBroadcastReceiver
    extends BroadcastReceiver
  {
    private PluginRegistryBroadcastReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getDataString().split(":")[1];
      if (paramContext.startsWith("com.tinysolutionsllc.plugin"))
      {
        if ("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction())) {
          PluginRegistry.this.dispatchPluginAdded(paramContext);
        }
        if ("android.intent.action.PACKAGE_REPLACED".equals(paramIntent.getAction())) {
          PluginRegistry.this.dispatchPluginUpdated(paramContext);
        }
      }
      else
      {
        return;
      }
      PluginRegistry.this.dispatchPluginRemoved(paramContext);
    }
  }
}
