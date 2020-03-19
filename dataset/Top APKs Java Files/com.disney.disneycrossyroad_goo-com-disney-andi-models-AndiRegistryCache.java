package com.disney.andi.models;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.disney.andi.context.AndiRegistryStorageContext;
import com.disney.andi.context.AndiSystemContext;
import com.disney.andi.context.IAndiPackageCacheStorageContext;
import com.disney.andi.context.IAndiSystemContext;
import com.disney.andi.exceptions.AndiDeviceNotFoundException;
import com.disney.andi.exceptions.AndiInvalidAndiUserException;
import com.disney.andi.exceptions.AndiInvalidProfileIdException;
import com.disney.andi.exceptions.AndiLastActiveProfileNotFoundException;
import com.disney.andi.exceptions.AndiLastActiveUserNotFoundException;
import com.disney.andi.exceptions.AndiRegistryNotFoundException;
import com.disney.andi.exceptions.AndiStorageObjectNotFoundException;
import com.disney.andi.exceptions.AndiStorageUnavailableException;
import com.disney.andi.exceptions.AndiSystemUnavailableException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class AndiRegistryCache
  extends HashMap<String, AndiRegistry>
{
  public IAndiSystemContext andiSystemContext;
  public long cacheBuildTime = 0L;
  public long packageManagerPullTime = 0L;
  public long packagesOpened = 0L;
  public long packagesSkippedDueToCache = 0L;
  public long systemPackagesSkipped = 0L;
  
  public AndiRegistryCache(IAndiSystemContext paramIAndiSystemContext)
  {
    this.andiSystemContext = paramIAndiSystemContext;
  }
  
  private AndiRegistryStorageContext generateContextForPackage(String paramString)
    throws PackageManager.NameNotFoundException, AndiSystemUnavailableException
  {
    return new AndiRegistryStorageContext(new AndiSystemContext(this.andiSystemContext.getOSContext().createPackageContext(paramString, 2), this.andiSystemContext.getGson()));
  }
  
  private void scanAllPackages(IAndiPackageCacheStore<IAndiPackageCacheStorageContext> paramIAndiPackageCacheStore)
    throws AndiStorageUnavailableException, AndiSystemUnavailableException
  {
    long l1 = System.currentTimeMillis();
    this.packagesOpened = 0L;
    this.packagesSkippedDueToCache = 0L;
    this.systemPackagesSkipped = 0L;
    clear();
    Object localObject = this.andiSystemContext.getOSContext().getPackageManager();
    long l2 = System.currentTimeMillis();
    localObject = ((PackageManager)localObject).getInstalledPackages(8192);
    this.packageManagerPullTime = (System.currentTimeMillis() - l2);
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label270;
      }
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      String str;
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 0)
      {
        this.systemPackagesSkipped += 1L;
      }
      else
      {
        str = localPackageInfo.packageName;
        localObject = paramIAndiPackageCacheStore.getAndiPackageCacheEntry(str);
        l2 = localPackageInfo.lastUpdateTime;
        if ((localObject != null) && (!((AndiPackageCacheEntry)localObject).getIsAndiApp()) && (((AndiPackageCacheEntry)localObject).getAppLastUpdated() == l2)) {
          this.packagesSkippedDueToCache += 1L;
        }
      }
      try
      {
        this.packagesOpened += 1L;
        put(str, getRegistryForPackage(str));
        bool = true;
      }
      catch (AndiRegistryNotFoundException localAndiRegistryNotFoundException)
      {
        for (;;)
        {
          bool = false;
        }
      }
      catch (AndiStorageObjectNotFoundException localAndiStorageObjectNotFoundException)
      {
        for (;;)
        {
          boolean bool = false;
          continue;
          ((AndiPackageCacheEntry)localObject).setAppLastUpdated(l2);
          ((AndiPackageCacheEntry)localObject).setIsAndiApp(bool);
        }
      }
      if (localObject != null) {
        break;
      }
      localObject = new AndiPackageCacheEntry(l2, bool);
      paramIAndiPackageCacheStore.setAndiPackageCacheEntry(str, (AndiPackageCacheEntry)localObject);
    }
    label270:
    paramIAndiPackageCacheStore.save();
    this.cacheBuildTime = (System.currentTimeMillis() - l1);
  }
  
  public AndiDevice findDevice()
    throws AndiDeviceNotFoundException
  {
    Iterator localIterator = entrySet().iterator();
    for (;;)
    {
      Object localObject;
      if (localIterator.hasNext()) {
        localObject = (Map.Entry)localIterator.next();
      }
      try
      {
        localObject = ((AndiRegistry)((Map.Entry)localObject).getValue()).getDevice();
        return localObject;
      }
      catch (AndiDeviceNotFoundException localAndiDeviceNotFoundException) {}
      throw new AndiDeviceNotFoundException();
    }
  }
  
  public List<AppStatus> getAppStatuses()
  {
    ArrayList localArrayList = new ArrayList(size());
    Iterator localIterator = entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localArrayList.add(AppStatus.of((String)localEntry.getKey(), ((AndiRegistry)localEntry.getValue()).getLastUpdatedTimestamp()));
    }
    return localArrayList;
  }
  
  public AndiProfile getMostRecentAndiProfile()
    throws AndiLastActiveProfileNotFoundException, AndiLastActiveUserNotFoundException
  {
    AndiProfile localAndiProfile = null;
    Iterator localIterator = entrySet().iterator();
    while (localIterator.hasNext())
    {
      AndiRegistry localAndiRegistry = (AndiRegistry)((Map.Entry)localIterator.next()).getValue();
      if (localAndiRegistry.getLastUpdatedTimestamp() > 0L) {
        localAndiProfile = localAndiRegistry.getLastActiveAndiProfile();
      }
    }
    if (localAndiProfile == null) {
      throw new AndiLastActiveProfileNotFoundException();
    }
    return localAndiProfile;
  }
  
  public AndiUser getMostRecentAndiUser()
    throws AndiLastActiveUserNotFoundException
  {
    AndiUser localAndiUser = null;
    if (isEmpty()) {
      throw new AndiLastActiveUserNotFoundException();
    }
    Iterator localIterator = entrySet().iterator();
    while (localIterator.hasNext())
    {
      AndiRegistry localAndiRegistry = (AndiRegistry)((Map.Entry)localIterator.next()).getValue();
      if (localAndiRegistry.getLastUpdatedTimestamp() > 0L) {
        localAndiUser = localAndiRegistry.getLastActiveAndiUser();
      }
    }
    if (localAndiUser == null) {
      throw new AndiLastActiveUserNotFoundException();
    }
    return localAndiUser;
  }
  
  public AndiRegistry getRegistryForPackage(String paramString)
    throws AndiStorageUnavailableException, AndiRegistryNotFoundException, AndiSystemUnavailableException, AndiStorageObjectNotFoundException
  {
    localObject1 = (AndiRegistry)get(paramString);
    if (localObject1 != null) {
      return localObject1;
    }
    for (;;)
    {
      try
      {
        localObject2 = new AndiRegistryStore(generateContextForPackage(paramString), this);
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        Object localObject2;
        paramString.printStackTrace();
        throw new AndiRegistryNotFoundException(paramString);
      }
      catch (AndiInvalidAndiUserException paramString)
      {
        paramString.printStackTrace();
        throw new AndiRegistryNotFoundException(paramString);
        put(paramString, localObject1);
        return localObject1;
      }
      catch (AndiInvalidProfileIdException paramString)
      {
        paramString.printStackTrace();
        throw new AndiRegistryNotFoundException(paramString);
      }
      try
      {
        localObject2 = (AndiRegistry)((AndiRegistryStore)localObject2).getData(false);
        localObject1 = localObject2;
      }
      catch (AndiStorageUnavailableException localAndiStorageUnavailableException)
      {
        if (localAndiStorageUnavailableException.getCause().getClass() == AndiStorageObjectNotFoundException.class) {
          continue;
        }
        throw localAndiStorageUnavailableException;
      }
    }
    if (localObject1 == null) {
      throw new AndiRegistryNotFoundException();
    }
  }
  
  public void updateFromPackageManager(IAndiPackageCacheStore<IAndiPackageCacheStorageContext> paramIAndiPackageCacheStore)
    throws AndiStorageUnavailableException, AndiSystemUnavailableException
  {
    long l1 = System.currentTimeMillis();
    this.packagesOpened = 0L;
    this.packagesSkippedDueToCache = 0L;
    this.systemPackagesSkipped = 0L;
    clear();
    Object localObject = this.andiSystemContext.getOSContext().getPackageManager();
    long l2 = System.currentTimeMillis();
    localObject = ((PackageManager)localObject).getInstalledApplications(8192);
    this.packageManagerPullTime = (System.currentTimeMillis() - l2);
    if (localObject == null)
    {
      scanAllPackages(paramIAndiPackageCacheStore);
      return;
    }
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label308;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      String str;
      if ((localApplicationInfo.flags & 0x1) != 0)
      {
        this.systemPackagesSkipped += 1L;
      }
      else
      {
        str = localApplicationInfo.packageName;
        if (!str.equals(this.andiSystemContext.getOSContext().getPackageName()))
        {
          localObject = paramIAndiPackageCacheStore.getAndiPackageCacheEntry(str);
          l2 = new File(localApplicationInfo.sourceDir).lastModified();
          if ((localObject != null) && (!((AndiPackageCacheEntry)localObject).getIsAndiApp()) && (((AndiPackageCacheEntry)localObject).getAppLastUpdated() == l2)) {
            this.packagesSkippedDueToCache += 1L;
          }
        }
      }
      try
      {
        this.packagesOpened += 1L;
        put(str, getRegistryForPackage(str));
        bool = true;
      }
      catch (AndiRegistryNotFoundException localAndiRegistryNotFoundException)
      {
        for (;;)
        {
          bool = false;
        }
      }
      catch (AndiStorageObjectNotFoundException localAndiStorageObjectNotFoundException)
      {
        for (;;)
        {
          boolean bool = false;
          continue;
          ((AndiPackageCacheEntry)localObject).setAppLastUpdated(l2);
          ((AndiPackageCacheEntry)localObject).setIsAndiApp(bool);
        }
      }
      if (localObject != null) {
        break;
      }
      localObject = new AndiPackageCacheEntry(l2, bool);
      paramIAndiPackageCacheStore.setAndiPackageCacheEntry(str, (AndiPackageCacheEntry)localObject);
    }
    label308:
    paramIAndiPackageCacheStore.save();
    this.cacheBuildTime = (System.currentTimeMillis() - l1);
  }
}
