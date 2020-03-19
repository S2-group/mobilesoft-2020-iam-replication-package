package pl.solidexplorer.common.plugin;

import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pl.solidexplorer.SEApp;
import pl.solidexplorer.common.plugin.interfaces.PluginInterface;
import pl.solidexplorer.common.plugin.ipc.IPCPlugin;
import pl.solidexplorer.filesystem.FileSystemDescriptor;
import pl.solidexplorer.filesystem.archive.ArchivePlugin;
import pl.solidexplorer.plugins.appfolderthumb.AppFolderThumbPlugin;
import pl.solidexplorer.plugins.apps.AppsPlugin;
import pl.solidexplorer.plugins.archiver.ArchiverPlugin;
import pl.solidexplorer.plugins.blacklist.BlackList;
import pl.solidexplorer.plugins.cloud.box.BoxPlugin;
import pl.solidexplorer.plugins.cloud.copy.CopyPlugin;
import pl.solidexplorer.plugins.cloud.drive.DrivePlugin;
import pl.solidexplorer.plugins.cloud.dropbox.DropboxPlugin;
import pl.solidexplorer.plugins.cloud.mediafire.MediafirePlugin;
import pl.solidexplorer.plugins.cloud.onedrive.OneDrivePlugin;
import pl.solidexplorer.plugins.cloud.sugarsync.SugarsyncPlugin;
import pl.solidexplorer.plugins.gifthumb.GifPlugin;
import pl.solidexplorer.plugins.media.MediaBrowser;
import pl.solidexplorer.plugins.network.dav.WebDavPlugin;
import pl.solidexplorer.plugins.network.ftp.FTPPlugin;
import pl.solidexplorer.plugins.network.smb.SMBPlugin;
import pl.solidexplorer.plugins.recents.Recents;
import pl.solidexplorer.thumbs.iconset.DefaultIconSets;
import pl.solidexplorer.util.BackgroundLooper;
import pl.solidexplorer.util.SELog;
import pl.solidexplorer.util.Utils;

public class PluginRegistry
{
  public static final String PLUGIN_PACKAGE = "pl.solidexplorer.plugin";
  private Handler mBackgroundHandler;
  private Map<String, Plugin> mInternalMap = new LinkedHashMap();
  private boolean mNeedsUpdate = true;
  private List<PluginRegistry.RegistryListener> mRegistryListeners = new ArrayList();
  private Handler mUIHandler;
  
  public PluginRegistry()
  {
    registerInternalPlugins();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
    localIntentFilter.addDataScheme("package");
    SEApp.get().registerReceiver(new PluginRegistry.1(this), localIntentFilter);
    this.mBackgroundHandler = new Handler(BackgroundLooper.looper());
    this.mUIHandler = new Handler(Looper.getMainLooper());
  }
  
  public static FileSystemDescriptor buildDescriptor(Identifier paramIdentifier)
  {
    return new FileSystemDescriptor().setPluginIdentifier(paramIdentifier).setId(paramIdentifier.asInt());
  }
  
  public static PluginRegistry getInstance()
  {
    return PluginRegistry.Holder.access$000();
  }
  
  private Plugin instantiatePlugin(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    Object localObject = new PluginRegistry.PluginClassLoader(paramPackageInfo.applicationInfo.sourceDir, getClass().getClassLoader());
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramPackageInfo.packageName, 140);
      if (localPackageInfo.applicationInfo.metaData != null)
      {
        paramPackageInfo = localPackageInfo.applicationInfo.metaData.getString("entryPoint");
        ServiceInfo[] arrayOfServiceInfo = localPackageInfo.services;
        if ((paramPackageInfo == null) && (arrayOfServiceInfo == null))
        {
          SELog.e("No entry point or service! Plugin: " + localPackageInfo.packageName);
          return null;
        }
        if (paramPackageInfo != null)
        {
          localObject = (Plugin)((PluginRegistry.PluginClassLoader)localObject).loadClass(paramPackageInfo).getConstructor(new Class[0]).newInstance(new Object[0]);
          paramPackageInfo = (PackageInfo)localObject;
        }
        for (;;)
        {
          try
          {
            ((Plugin)localObject).setPackage(localPackageInfo.packageName);
            paramPackageInfo = (PackageInfo)localObject;
            ((Plugin)localObject).setPluginName((String)localPackageInfo.applicationInfo.loadLabel(paramPackageManager));
            paramPackageInfo = (PackageInfo)localObject;
            ((Plugin)localObject).setIcon(localPackageInfo.applicationInfo.loadIcon(paramPackageManager));
            paramPackageInfo = (PackageInfo)localObject;
            ((Plugin)localObject).setDexPath(localPackageInfo.applicationInfo.sourceDir);
            paramPackageInfo = (PackageInfo)localObject;
            ((Plugin)localObject).setVersion(localPackageInfo.versionCode);
            paramPackageManager = (PackageManager)localObject;
            paramPackageInfo = (PackageInfo)localObject;
            if (localPackageInfo.providers != null)
            {
              paramPackageManager = (PackageManager)localObject;
              paramPackageInfo = (PackageInfo)localObject;
              if (localPackageInfo.providers.length > 0)
              {
                paramPackageInfo = (PackageInfo)localObject;
                ((Plugin)localObject).setContentProvider(localPackageInfo.providers[0]);
                paramPackageManager = (PackageManager)localObject;
              }
            }
            paramPackageInfo = paramPackageManager;
            SELog.i(new Object[] { "Plugin instance created: ", paramPackageManager });
            return paramPackageManager;
          }
          catch (Throwable paramPackageManager) {}
          SELog.e(paramPackageManager);
          return paramPackageInfo;
          paramPackageManager = new IPCPlugin(localPackageInfo, arrayOfServiceInfo);
        }
      }
    }
    catch (Throwable paramPackageManager)
    {
      for (;;)
      {
        paramPackageInfo = null;
        continue;
        paramPackageInfo = null;
      }
    }
  }
  
  private void register(Plugin paramPlugin)
  {
    String str = Utils.getParentPath(paramPlugin.getClass().getName(), '.');
    this.mInternalMap.put(str, paramPlugin);
  }
  
  private void registerInternalPlugins()
  {
    register(new ArchivePlugin());
    register(new DrivePlugin());
    register(new DropboxPlugin());
    register(new OneDrivePlugin());
    register(new SMBPlugin());
    register(new FTPPlugin());
    register(new WebDavPlugin());
    register(new BoxPlugin());
    register(new CopyPlugin());
    register(new SugarsyncPlugin());
    register(new MediafirePlugin());
    register(new GifPlugin());
    register(new AppFolderThumbPlugin());
    register(new ArchiverPlugin());
    register(new DefaultIconSets());
    register(new MediaBrowser());
    register(new AppsPlugin());
    register(new BlackList());
    register(new Recents());
  }
  
  public void addListener(PluginRegistry.RegistryListener paramRegistryListener)
  {
    this.mRegistryListeners.add(paramRegistryListener);
  }
  
  List<Plugin> convertToList(Plugin paramPlugin)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramPlugin != null) {
      localArrayList.add(paramPlugin);
    }
    return localArrayList;
  }
  
  void dispatchPluginAdded(String paramString)
  {
    paramString = findPluginPackage(paramString);
    if (paramString != null)
    {
      this.mInternalMap.put(paramString.getPackage(), paramString);
      Iterator localIterator = this.mRegistryListeners.iterator();
      while (localIterator.hasNext())
      {
        PluginRegistry.RegistryListener localRegistryListener = (PluginRegistry.RegistryListener)localIterator.next();
        if ((localRegistryListener.getDesiredTypes() & paramString.getTypes()) == localRegistryListener.getDesiredTypes()) {
          localRegistryListener.onPluginAdded(paramString);
        }
      }
    }
  }
  
  void dispatchPluginRemoved(String paramString)
  {
    paramString = (Plugin)this.mInternalMap.remove(paramString);
    if (paramString != null)
    {
      Iterator localIterator = this.mRegistryListeners.iterator();
      while (localIterator.hasNext())
      {
        PluginRegistry.RegistryListener localRegistryListener = (PluginRegistry.RegistryListener)localIterator.next();
        if ((localRegistryListener.getDesiredTypes() & paramString.getTypes()) == localRegistryListener.getDesiredTypes()) {
          localRegistryListener.onPluginRemoved(paramString);
        }
      }
    }
  }
  
  void dispatchPluginUpdated(String paramString)
  {
    paramString = (Plugin)this.mInternalMap.get(paramString);
    if (paramString != null)
    {
      Iterator localIterator = this.mRegistryListeners.iterator();
      while (localIterator.hasNext())
      {
        PluginRegistry.RegistryListener localRegistryListener = (PluginRegistry.RegistryListener)localIterator.next();
        if ((localRegistryListener.getDesiredTypes() & paramString.getTypes()) == localRegistryListener.getDesiredTypes()) {
          localRegistryListener.onPluginUpdated(paramString);
        }
      }
    }
  }
  
  List<Plugin> findAvailablePluginPackages()
  {
    localArrayList = new ArrayList();
    try
    {
      PackageManager localPackageManager = SEApp.get().getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(136).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (PackageInfo)localIterator.next();
        if (((PackageInfo)localObject).packageName.startsWith("pl.solidexplorer.plugin"))
        {
          localObject = instantiatePlugin(localPackageManager, (PackageInfo)localObject);
          if (localObject != null) {
            localArrayList.add(localObject);
          }
        }
      }
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      SELog.e(localThrowable);
    }
  }
  
  Plugin findPluginPackage(String paramString)
  {
    PackageManager localPackageManager = SEApp.get().getPackageManager();
    Object localObject = null;
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 136);
      paramString = localObject;
      if (localPackageInfo.packageName.startsWith("pl.solidexplorer.plugin")) {
        paramString = instantiatePlugin(localPackageManager, localPackageInfo);
      }
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      SELog.i(paramString);
    }
    return null;
  }
  
  List<Plugin> getFromMap(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mInternalMap.values().iterator();
    while (localIterator.hasNext())
    {
      Plugin localPlugin = (Plugin)localIterator.next();
      if ((localPlugin.getTypes() & paramInt) > 0) {
        localArrayList.add(localPlugin);
      }
    }
    return localArrayList;
  }
  
  public Plugin getLocalPlugin(Class<?> paramClass)
  {
    return getPlugin(LocalPlugin.getPluginPackageForClass(paramClass));
  }
  
  public Plugin getPlugin(String paramString)
  {
    label110:
    for (;;)
    {
      try
      {
        Object localObject1 = (Plugin)this.mInternalMap.get(paramString);
        Object localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = localObject1;
          if (this.mNeedsUpdate)
          {
            Iterator localIterator = findAvailablePluginPackages().iterator();
            if (localIterator.hasNext())
            {
              localObject2 = (Plugin)localIterator.next();
              this.mInternalMap.put(((Plugin)localObject2).getPackage(), localObject2);
              if (!paramString.equals(((Plugin)localObject2).getPackage())) {
                break label110;
              }
              localObject1 = localObject2;
              break label110;
            }
            this.mNeedsUpdate = false;
            localObject2 = localObject1;
          }
        }
        return localObject2;
      }
      finally {}
    }
  }
  
  /* Error */
  public void getPlugin(String paramString, pl.solidexplorer.common.interfaces.AsyncReturn<Plugin> paramAsyncReturn)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 30	pl/solidexplorer/common/plugin/PluginRegistry:mInternalMap	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 393 2 0
    //   12: checkcast 208	pl/solidexplorer/common/plugin/Plugin
    //   15: astore_3
    //   16: aload_3
    //   17: ifnonnull +39 -> 56
    //   20: aload_0
    //   21: getfield 25	pl/solidexplorer/common/plugin/PluginRegistry:mNeedsUpdate	Z
    //   24: ifeq +32 -> 56
    //   27: aload_0
    //   28: getfield 84	pl/solidexplorer/common/plugin/PluginRegistry:mBackgroundHandler	Landroid/os/Handler;
    //   31: new 446	pl/solidexplorer/common/plugin/PluginRegistry$PluginLoader
    //   34: dup
    //   35: aload_0
    //   36: aload_2
    //   37: new 448	pl/solidexplorer/common/plugin/PluginRegistry$2
    //   40: dup
    //   41: aload_0
    //   42: aload_1
    //   43: invokespecial 451	pl/solidexplorer/common/plugin/PluginRegistry$2:<init>	(Lpl/solidexplorer/common/plugin/PluginRegistry;Ljava/lang/String;)V
    //   46: invokespecial 454	pl/solidexplorer/common/plugin/PluginRegistry$PluginLoader:<init>	(Lpl/solidexplorer/common/plugin/PluginRegistry;Lpl/solidexplorer/common/interfaces/AsyncReturn;Ljava/util/concurrent/Callable;)V
    //   49: invokevirtual 458	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   52: pop
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: aload_2
    //   57: aload_3
    //   58: invokeinterface 464 2 0
    //   63: goto -10 -> 53
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	this	PluginRegistry
    //   0	71	1	paramString	String
    //   0	71	2	paramAsyncReturn	pl.solidexplorer.common.interfaces.AsyncReturn<Plugin>
    //   15	43	3	localPlugin	Plugin
    // Exception table:
    //   from	to	target	type
    //   2	16	66	finally
    //   20	53	66	finally
    //   56	63	66	finally
  }
  
  public Plugin getPluginById(Identifier paramIdentifier)
  {
    try
    {
      paramIdentifier = getPlugin(paramIdentifier.packageName);
      return paramIdentifier;
    }
    finally
    {
      paramIdentifier = finally;
      throw paramIdentifier;
    }
  }
  
  /* Error */
  public void getPluginById(Identifier paramIdentifier, pl.solidexplorer.common.interfaces.AsyncReturn<Plugin> paramAsyncReturn)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 30	pl/solidexplorer/common/plugin/PluginRegistry:mInternalMap	Ljava/util/Map;
    //   6: aload_1
    //   7: getfield 468	pl/solidexplorer/common/plugin/Identifier:packageName	Ljava/lang/String;
    //   10: invokeinterface 393 2 0
    //   15: checkcast 208	pl/solidexplorer/common/plugin/Plugin
    //   18: astore_3
    //   19: aload_3
    //   20: ifnonnull +39 -> 59
    //   23: aload_0
    //   24: getfield 25	pl/solidexplorer/common/plugin/PluginRegistry:mNeedsUpdate	Z
    //   27: ifeq +32 -> 59
    //   30: aload_0
    //   31: getfield 84	pl/solidexplorer/common/plugin/PluginRegistry:mBackgroundHandler	Landroid/os/Handler;
    //   34: new 446	pl/solidexplorer/common/plugin/PluginRegistry$PluginLoader
    //   37: dup
    //   38: aload_0
    //   39: aload_2
    //   40: new 471	pl/solidexplorer/common/plugin/PluginRegistry$3
    //   43: dup
    //   44: aload_0
    //   45: aload_1
    //   46: invokespecial 474	pl/solidexplorer/common/plugin/PluginRegistry$3:<init>	(Lpl/solidexplorer/common/plugin/PluginRegistry;Lpl/solidexplorer/common/plugin/Identifier;)V
    //   49: invokespecial 454	pl/solidexplorer/common/plugin/PluginRegistry$PluginLoader:<init>	(Lpl/solidexplorer/common/plugin/PluginRegistry;Lpl/solidexplorer/common/interfaces/AsyncReturn;Ljava/util/concurrent/Callable;)V
    //   52: invokevirtual 458	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   55: pop
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: aload_2
    //   60: aload_3
    //   61: invokeinterface 464 2 0
    //   66: goto -10 -> 56
    //   69: astore_1
    //   70: aload_0
    //   71: monitorexit
    //   72: aload_1
    //   73: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	PluginRegistry
    //   0	74	1	paramIdentifier	Identifier
    //   0	74	2	paramAsyncReturn	pl.solidexplorer.common.interfaces.AsyncReturn<Plugin>
    //   18	43	3	localPlugin	Plugin
    // Exception table:
    //   from	to	target	type
    //   2	19	69	finally
    //   23	56	69	finally
    //   59	66	69	finally
  }
  
  public PluginInterface getPluginInterface(Identifier paramIdentifier)
  {
    Plugin localPlugin = getPluginById(paramIdentifier);
    PluginInterface localPluginInterface = null;
    if (localPlugin != null) {
      localPluginInterface = localPlugin.getInterface(paramIdentifier);
    }
    return localPluginInterface;
  }
  
  public List<Plugin> getPlugins(int paramInt)
  {
    try
    {
      List localList = getFromMap(paramInt);
      if (this.mNeedsUpdate)
      {
        Iterator localIterator = findAvailablePluginPackages().iterator();
        while (localIterator.hasNext())
        {
          Plugin localPlugin = (Plugin)localIterator.next();
          this.mInternalMap.put(localPlugin.getPackage(), localPlugin);
          if ((localPlugin.getTypes() & paramInt) > 0) {
            localList.add(localPlugin);
          }
        }
        this.mNeedsUpdate = false;
      }
    }
    finally {}
    return localList1;
  }
  
  /* Error */
  public void getPlugins(int paramInt, pl.solidexplorer.common.interfaces.AsyncReturn<List<Plugin>> paramAsyncReturn)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 25	pl/solidexplorer/common/plugin/PluginRegistry:mNeedsUpdate	Z
    //   6: ifeq +32 -> 38
    //   9: aload_0
    //   10: getfield 84	pl/solidexplorer/common/plugin/PluginRegistry:mBackgroundHandler	Landroid/os/Handler;
    //   13: new 446	pl/solidexplorer/common/plugin/PluginRegistry$PluginLoader
    //   16: dup
    //   17: aload_0
    //   18: aload_2
    //   19: new 488	pl/solidexplorer/common/plugin/PluginRegistry$4
    //   22: dup
    //   23: aload_0
    //   24: iload_1
    //   25: invokespecial 491	pl/solidexplorer/common/plugin/PluginRegistry$4:<init>	(Lpl/solidexplorer/common/plugin/PluginRegistry;I)V
    //   28: invokespecial 454	pl/solidexplorer/common/plugin/PluginRegistry$PluginLoader:<init>	(Lpl/solidexplorer/common/plugin/PluginRegistry;Lpl/solidexplorer/common/interfaces/AsyncReturn;Ljava/util/concurrent/Callable;)V
    //   31: invokevirtual 458	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   34: pop
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: aload_2
    //   39: aload_0
    //   40: iload_1
    //   41: invokevirtual 485	pl/solidexplorer/common/plugin/PluginRegistry:getFromMap	(I)Ljava/util/List;
    //   44: invokeinterface 464 2 0
    //   49: goto -14 -> 35
    //   52: astore_2
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_2
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	PluginRegistry
    //   0	57	1	paramInt	int
    //   0	57	2	paramAsyncReturn	pl.solidexplorer.common.interfaces.AsyncReturn<List<Plugin>>
    // Exception table:
    //   from	to	target	type
    //   2	35	52	finally
    //   38	49	52	finally
  }
  
  public void removeListener(PluginRegistry.RegistryListener paramRegistryListener)
  {
    this.mRegistryListeners.remove(paramRegistryListener);
  }
}
