package com.disney.andi.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import com.disney.andi.context.AndiContentProviderStorageContext;
import com.disney.andi.context.AndiSystemContext;
import com.disney.andi.context.IAndiContentProviderStorageContext;
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
  public IAndiContentProviderStorageContext andiContentProviderStorageContext;
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
  
  private AndiContentProviderStorageContext generateContextForPackage(String paramString)
    throws PackageManager.NameNotFoundException, AndiSystemUnavailableException
  {
    return new AndiContentProviderStorageContext(new AndiSystemContext(this.andiSystemContext.getOSContext().createPackageContext(paramString, 2), this.andiSystemContext.getGson()));
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
    throws AndiStorageUnavailableException, AndiRegistryNotFoundException, AndiSystemUnavailableException, AndiStorageObjectNotFoundException, PackageManager.NameNotFoundException
  {
    localObject1 = (AndiRegistry)get(paramString);
    if (localObject1 != null) {
      return localObject1;
    }
    Object localObject2 = generateContextForPackage(paramString);
    for (;;)
    {
      try
      {
        localObject2 = new AndiRegistryStore((IAndiContentProviderStorageContext)localObject2, this);
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
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
  
  @SuppressLint({"WrongConstant"})
  public void updateFromPackageManager(IAndiPackageCacheStore<IAndiPackageCacheStorageContext> paramIAndiPackageCacheStore)
    throws AndiStorageUnavailableException, AndiSystemUnavailableException
  {
    long l1 = System.currentTimeMillis();
    this.packagesOpened = 0L;
    this.packagesSkippedDueToCache = 0L;
    this.systemPackagesSkipped = 0L;
    clear();
    Object localObject1 = this.andiSystemContext.getOSContext().getPackageManager();
    long l2 = System.currentTimeMillis();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(8);
    this.packageManagerPullTime = (System.currentTimeMillis() - l2);
    Iterator localIterator = ((List)localObject1).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label388;
      }
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int k = 0;
      String str;
      int j;
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 0)
      {
        this.systemPackagesSkipped += 1L;
      }
      else
      {
        str = localPackageInfo.packageName;
        if (!str.equals(this.andiSystemContext.getOSContext().getPackageName()))
        {
          localObject1 = localPackageInfo.providers;
          if (localObject1 != null)
          {
            int m = localObject1.length;
            int i = 0;
            for (;;)
            {
              j = k;
              if (i < m)
              {
                Object localObject2 = localObject1[i];
                if ((localObject2 != null) && (localObject2.authority != null) && (localObject2.authority.contains(".andi-provider"))) {
                  j = 1;
                }
              }
              else
              {
                localObject1 = paramIAndiPackageCacheStore.getAndiPackageCacheEntry(str);
                l2 = new File(localPackageInfo.applicationInfo.sourceDir).lastModified();
                if ((localObject1 == null) || (((AndiPackageCacheEntry)localObject1).getIsAndiApp()) || (((AndiPackageCacheEntry)localObject1).getAppLastUpdated() != l2)) {
                  break label286;
                }
                this.packagesSkippedDueToCache += 1L;
                break;
              }
              i += 1;
            }
          }
        }
      }
      try
      {
        label286:
        this.packagesOpened += 1L;
        if (j != 0) {
          put(str, getRegistryForPackage(str));
        }
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
          bool = false;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          boolean bool = false;
          continue;
          ((AndiPackageCacheEntry)localObject1).setAppLastUpdated(l2);
          ((AndiPackageCacheEntry)localObject1).setIsAndiApp(bool);
        }
      }
      if (localObject1 != null) {
        break;
      }
      localObject1 = new AndiPackageCacheEntry(l2, bool);
      paramIAndiPackageCacheStore.setAndiPackageCacheEntry(str, (AndiPackageCacheEntry)localObject1);
    }
    label388:
    paramIAndiPackageCacheStore.save();
    this.cacheBuildTime = (System.currentTimeMillis() - l1);
  }
}
