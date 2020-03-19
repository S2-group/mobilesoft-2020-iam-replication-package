package aero.panasonic.inflight.services.utils;

import aero.panasonic.inflight.services.ifeaodservice.IfeAodService;
import aero.panasonic.inflight.services.ifeservice.IfeService;
import aero.panasonic.inflight.services.service.IfeDataService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ServiceUtil
{
  public static final String IFEDATA_SERVICE = "aero.panasonic.inflight.services.service.IfeDataService";
  public static final String INVALID_FLAVOR_NAME = "";
  public static final int INVALID_VERSION_NAME = 0;
  public static final String PERMISSION_CHECK_SERVICE = "aero.panasonic.inflight.services.service.PermissionCheckService";
  public static final String SEATBACK_BLACK_BACKGROUND_SERVICE = "aero.panasonic.blackbackgroundservice.blackbackgroundService";
  public static final String SEATBACK_BLACK_BACKGROUND_SERVICE_PKG = "aero.panasonic.blackbackgroundservice";
  public static final String SEATBACK_EMULATOR_PACKAGE_NAME = "aero.panasonic.inflight.app.seatback.emulator";
  public static final String SEATBACK_ICORE_SERVICE = "aero.panasonic.inflight.services.seatcore.ifeicoreservice.IfeIcoreService";
  public static final String SEATBACK_ICORE_SERVICE_ACTION = "aero.panasonic.inflight.services.ifecoreservice";
  public static final String SEATBACK_PACKAGE_NAME = "aero.panasonic.inflight.app.seatback";
  public static final String SEATBACK_PRIVILAGE_BROKER_SERVICE = "aero.panasonic.privilegebrokerservice.PrivilegeBrokerService";
  public static final String SEATBACK_PRIVILAGE_BROKER_SERVICE_PKG = "aero.panasonic.privilegebrokerservice";
  public static final String SERVICE_VARIANT_VERSION = "service_variant_version";
  public static final String SERVICE_VERSION = "service_version";
  private static final String TAG = ServiceUtil.class.getSimpleName();
  private Context mContext;
  private PackageManager mPackageManager;
  
  public ServiceUtil(Context paramContext)
  {
    this.mContext = paramContext;
    if (paramContext != null) {
      this.mPackageManager = paramContext.getPackageManager();
    }
  }
  
  public static boolean isOnSeatback()
  {
    return (new File("/etc/lru/lru_type").exists()) || (new File("/etc/lru_type").exists());
  }
  
  public static boolean isOnSeatback(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return isOnSeatback();
  }
  
  public static boolean isSeatbackEmulator(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equals("aero.panasonic.inflight.app.seatback.emulator")) {
        return true;
      }
    }
    return false;
  }
  
  public boolean bindToBlackBackgroundService(ServiceConnection paramServiceConnection, int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    Intent localIntent;
    if (this.mContext != null)
    {
      bool1 = bool2;
      if (isOnSeatback(this.mContext))
      {
        ComponentName localComponentName = new ComponentName("aero.panasonic.blackbackgroundservice", "aero.panasonic.blackbackgroundservice.blackbackgroundService");
        localIntent = new Intent();
        localIntent.setComponent(localComponentName);
        Log.v(TAG, "trying to bind BlackBackground service");
      }
    }
    try
    {
      bool1 = this.mContext.bindService(localIntent, paramServiceConnection, paramInt);
      return bool1;
    }
    catch (SecurityException paramServiceConnection) {}
    return false;
  }
  
  public void bindToIfeAodService(String paramString, ServiceConnection paramServiceConnection, int paramInt)
  {
    if (this.mContext != null)
    {
      Intent localIntent = new Intent(this.mContext, IfeAodService.class);
      localIntent.putExtra("data", paramString);
      if (this.mContext.bindService(localIntent, paramServiceConnection, paramInt))
      {
        Log.v(TAG, new StringBuilder("bindToIfeAodService:").append(paramString).append(":bind to local service").toString());
        return;
      }
      Log.e(TAG, "bind error");
      return;
    }
    Log.e(TAG, "bind error. Context is null");
  }
  
  public void bindToIfeDataService(String paramString, ServiceConnection paramServiceConnection)
  {
    if (isOnSeatback())
    {
      localIntent = new Intent();
      localIntent.putExtra("data", paramString);
      localIntent.setComponent(new ComponentName("aero.panasonic.inflight.app.seatback", "aero.panasonic.inflight.services.service.IfeDataService"));
      if (this.mContext.bindService(localIntent, paramServiceConnection, 73))
      {
        Log.v(TAG, new StringBuilder("bindToIfeDataService:").append(paramString).append(" bind to remote IfeDataService").toString());
        return;
      }
    }
    Intent localIntent = new Intent(this.mContext, IfeDataService.class);
    localIntent.putExtra("data", paramString);
    if (this.mContext.bindService(localIntent, paramServiceConnection, 73))
    {
      Log.v(TAG, new StringBuilder("bindToIfeDataService:").append(paramString).append(" bind to local IfeDataService").toString());
      return;
    }
    Log.e(TAG, new StringBuilder("bindToIfeDataService:").append(paramString).append(" bind error").toString());
  }
  
  public boolean bindToIfeIcoreService(ServiceConnection paramServiceConnection, int paramInt)
  {
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = bool3;
    if (this.mContext != null)
    {
      bool1 = bool3;
      if (isOnSeatback())
      {
        localObject = new ComponentName("aero.panasonic.inflight.app.seatback", "aero.panasonic.inflight.services.seatcore.ifeicoreservice.IfeIcoreService");
        bool1 = bool2;
        if (this.mContext != null)
        {
          bool1 = bool2;
          if (isOnSeatback(this.mContext))
          {
            localIntent = new Intent();
            localIntent.setComponent((ComponentName)localObject);
            Log.v(TAG, new StringBuilder("trying to bind To Component: ").append(((ComponentName)localObject).getPackageName()).toString());
            bool1 = this.mContext.bindService(localIntent, paramServiceConnection, paramInt);
          }
        }
        if (!bool1) {
          break label124;
        }
        bool1 = true;
      }
    }
    return bool1;
    label124:
    Object localObject = this.mContext.getPackageName();
    Log.i(TAG, "try bindToIfeIcoreService with package: ".concat(String.valueOf(localObject)));
    Intent localIntent = new Intent("aero.panasonic.inflight.services.ifecoreservice");
    localIntent.setPackage((String)localObject);
    bool1 = this.mContext.bindService(localIntent, paramServiceConnection, paramInt);
    if (bool1)
    {
      Log.i(TAG, new StringBuilder("binding to IfeIcoreservice with package ").append((String)localObject).append(" succeeded.").toString());
      return bool1;
    }
    Log.i(TAG, new StringBuilder("binding to IfeIcoreservice with package ").append((String)localObject).append(" failed.").toString());
    return bool1;
  }
  
  public void bindToIfeService(ServiceConnection paramServiceConnection, int paramInt)
  {
    if (this.mContext == null) {
      return;
    }
    Object localObject1;
    if (isOnSeatback())
    {
      localObject1 = new Intent("aero.panasonic.inflight.remote.service");
      ((Intent)localObject1).setPackage("aero.panasonic.inflight.app.seatback");
      if (this.mContext.bindService((Intent)localObject1, paramServiceConnection, paramInt))
      {
        Log.v(TAG, "bindToIfeService: bind to  Ifeservice");
        return;
      }
    }
    Intent localIntent;
    int i;
    int k;
    String str1;
    boolean bool;
    Object localObject2;
    int j;
    if (this.mPackageManager != null)
    {
      localIntent = new Intent("aero.panasonic.inflight.remote.service");
      List localList = this.mPackageManager.queryIntentServices(localIntent, 0);
      if (localList.size() <= 0)
      {
        Log.e(TAG, "cannot resolve service: aero.panasonic.inflight.remote.service");
        return;
      }
      i = -1;
      localObject1 = "";
      k = 0;
      if (k < localList.size())
      {
        str1 = ((ResolveInfo)localList.get(k)).serviceInfo.packageName;
        bool = ((ResolveInfo)localList.get(k)).serviceInfo.exported;
        String str2 = getServiceFlavor(str1, IfeService.class);
        Log.i(TAG, new StringBuilder("bindToIfeService: found in ").append(str1).append(" with flavor ").append(str2).append(", is exported ? ").append(bool).toString());
        if (!str2.isEmpty())
        {
          localObject2 = localObject1;
          j = i;
          if (str2.equalsIgnoreCase("rack"))
          {
            j = getServiceVersion(str1, IfeService.class);
            Log.i(TAG, new StringBuilder("bindToIfeService: found in ").append(str1).append(" with version ").append(j).append(", is exported ? ").append(bool).toString());
            if ((j <= i) || (!bool)) {
              break label553;
            }
            i = j;
            localObject1 = str1;
          }
        }
      }
    }
    label553:
    for (;;)
    {
      j = i;
      localObject2 = localObject1;
      for (;;)
      {
        k += 1;
        localObject1 = localObject2;
        i = j;
        break;
        int m = getServiceVersion(str1, IfeService.class);
        Log.i(TAG, new StringBuilder("bindToIfeService: found in ").append(str1).append(" with version ").append(m).append(", is exported ? ").append(bool).toString());
        localObject2 = localObject1;
        j = i;
        if (m > i)
        {
          localObject2 = localObject1;
          j = i;
          if (bool)
          {
            localObject2 = str1;
            j = m;
          }
        }
      }
      if (localObject1.equals("")) {
        Log.e(TAG, "bindToIfeService:   bind to remote Ifeservice failed.");
      }
      localIntent.setPackage((String)localObject1);
      if (this.mContext.bindService(localIntent, paramServiceConnection, paramInt))
      {
        Log.i(TAG, new StringBuilder("bindToIfeService: bind to  Ifeservice with package ").append((String)localObject1).append(" succeed").toString());
        return;
      }
      Log.i(TAG, new StringBuilder("bindToIfeService: bind to  Ifeservice with package ").append((String)localObject1).append(" failed").toString());
      return;
      Log.e(TAG, "bindToIfeService:   bind to remote Ifeservice failed.");
      return;
    }
  }
  
  public void bindToPermissionCheckService(String paramString, ServiceConnection paramServiceConnection)
  {
    if (isOnSeatback())
    {
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName("aero.panasonic.inflight.app.seatback", "aero.panasonic.inflight.services.service.PermissionCheckService"));
      if (this.mContext.bindService(localIntent, paramServiceConnection, 73)) {
        Log.d(TAG, new StringBuilder("bindToPermissionCheckService:").append(paramString).append(" bind to remote PermissionCheckService").toString());
      }
    }
    else
    {
      return;
    }
    Log.d(TAG, new StringBuilder("bindToPermissionCheckService:").append(paramString).append(" bind error").toString());
  }
  
  public boolean bindToPrivilegeBrokerService(ServiceConnection paramServiceConnection, int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mContext != null)
    {
      bool1 = bool2;
      if (isOnSeatback(this.mContext))
      {
        ComponentName localComponentName = new ComponentName("aero.panasonic.privilegebrokerservice", "aero.panasonic.privilegebrokerservice.PrivilegeBrokerService");
        Intent localIntent = new Intent();
        localIntent.setComponent(localComponentName);
        Log.v(TAG, "trying to bind Privilage briker service");
        bool1 = this.mContext.bindService(localIntent, paramServiceConnection, paramInt);
      }
    }
    return bool1;
  }
  
  public String getServiceFlavor(String paramString, Class<?> paramClass)
  {
    if (this.mPackageManager != null) {
      try
      {
        paramString = this.mPackageManager.getPackageInfo(paramString, 132);
        int i = 0;
        while (i < paramString.services.length)
        {
          if ((paramClass.getName().equals(paramString.services[i].name)) && (paramString.services[i].metaData != null))
          {
            paramString = paramString.services[i].metaData.getString("service_flavor", "");
            return paramString;
          }
          i += 1;
        }
        return "";
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        Log.exception(paramString);
      }
    }
  }
  
  public int getServiceVersion(String paramString, Class<?> paramClass)
  {
    int k = 0;
    int j = k;
    if (this.mPackageManager != null) {}
    try
    {
      paramString = this.mPackageManager.getPackageInfo(paramString, 132);
      int i = 0;
      for (;;)
      {
        j = k;
        if (i < paramString.services.length)
        {
          if ((paramClass.getName().equals(paramString.services[i].name)) && (paramString.services[i].metaData != null)) {
            j = paramString.services[i].metaData.getInt("service_version", 0);
          }
        }
        else {
          return j;
        }
        i += 1;
      }
      return 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      Log.exception(paramString);
    }
  }
  
  public int getServiceVersion(String paramString1, String paramString2)
  {
    int k = 0;
    int j = k;
    if (this.mPackageManager != null) {}
    try
    {
      paramString1 = this.mPackageManager.getPackageInfo(paramString1, 132);
      int i = 0;
      for (;;)
      {
        j = k;
        if (i < paramString1.services.length)
        {
          Log.v(TAG, new StringBuilder("Services: ").append(paramString1.services[i].name).toString());
          if (("aero.panasonic.inflight.services.seatcore.ifeicoreservice.IfeIcoreService".equals(paramString1.services[i].name)) && (paramString1.services[i].metaData != null)) {
            j = paramString1.services[i].metaData.getInt("service_version", 0);
          }
        }
        else
        {
          return j;
        }
        i += 1;
      }
      return 0;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      Log.exception(paramString1);
    }
  }
}
