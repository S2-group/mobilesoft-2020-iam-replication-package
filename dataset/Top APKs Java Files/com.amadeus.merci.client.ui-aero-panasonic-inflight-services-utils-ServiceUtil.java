package aero.panasonic.inflight.services.utils;

import aero.panasonic.inflight.services.ifeaodservice.IfeAodService;
import aero.panasonic.inflight.services.ifeservice.IfeService;
import aero.panasonic.inflight.services.service.IfeDataService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

public class ServiceUtil
{
  public static final int INVALID_VERSION_NAME = 0;
  private static final String SEATBACK_PACKAGE_NAME = "aero.panasonic.inflight.app.seatback";
  public static final String SERVICE_VARIANT_VERSION = "service_variant_version";
  public static final String SERVICE_VERSION = "service_version";
  private static final String TAG = ServiceUtil.class.getSimpleName();
  public static boolean isOnSeatback;
  private Context mContext;
  private PackageManager mPackageManager;
  
  public ServiceUtil(Context paramContext)
  {
    this.mContext = paramContext;
    this.mPackageManager = paramContext.getPackageManager();
  }
  
  private boolean bindToSeatbackService(String paramString, ServiceConnection paramServiceConnection, int paramInt)
  {
    if (isOnSeatback(this.mContext))
    {
      Intent localIntent = new Intent(paramString);
      localIntent.setAction(paramString);
      localIntent.setPackage("aero.panasonic.inflight.app.seatback");
      Log.v(TAG, "trying to bind seatback with action = " + paramString);
      return this.mContext.bindService(localIntent, paramServiceConnection, paramInt);
    }
    return false;
  }
  
  private int getServiceVariantVersion(String paramString, Class<?> paramClass)
  {
    int k = 0;
    try
    {
      paramString = this.mPackageManager.getPackageInfo(paramString, 132);
      int i = 0;
      for (;;)
      {
        int j = k;
        if (i < paramString.services.length)
        {
          if ((paramClass.getName().equals(paramString.services[i].name)) && (paramString.services[i].metaData != null)) {
            j = paramString.services[i].metaData.getInt("service_variant_version", 0);
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
      paramString.printStackTrace();
    }
  }
  
  private int getServiceVersion(String paramString, Class<?> paramClass)
  {
    int k = 0;
    try
    {
      paramString = this.mPackageManager.getPackageInfo(paramString, 132);
      int i = 0;
      for (;;)
      {
        int j = k;
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
      paramString.printStackTrace();
    }
  }
  
  public static boolean isOnSeatback(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equals("aero.panasonic.inflight.app.seatback"))
      {
        isOnSeatback = true;
        return true;
      }
    }
    isOnSeatback = false;
    return false;
  }
  
  public void bindLatestService(String paramString, ServiceConnection paramServiceConnection, int paramInt)
  {
    if (bindToSeatbackService(paramString, paramServiceConnection, paramInt)) {
      return;
    }
    Intent localIntent = new Intent(paramString);
    List localList = this.mPackageManager.queryIntentServices(localIntent, 0);
    if (localList.size() <= 0)
    {
      Log.e(TAG, "cannot resolve service: " + paramString);
      return;
    }
    int j = -1;
    paramString = "";
    int i = 0;
    while (i < localList.size())
    {
      str = ((ResolveInfo)localList.get(i)).serviceInfo.packageName;
      int m = getServiceVersion(str, IfeService.class);
      Log.i(TAG, "found service in " + str + " with version " + m);
      int k = j;
      if (m > j)
      {
        k = m;
        paramString = str;
      }
      i += 1;
      j = k;
    }
    String str = paramString;
    if (paramString.equals("")) {
      str = this.mContext.getPackageName();
    }
    Log.i(TAG, "try bindService with package: " + str);
    localIntent.setPackage(str);
    if (this.mContext.bindService(localIntent, paramServiceConnection, paramInt))
    {
      Log.i(TAG, "bind to remote service with package " + str + " succeed");
      return;
    }
    Log.i(TAG, "bind to remote service with package " + str + " unsucceed");
  }
  
  public void bindToIfeAodService(String paramString, ServiceConnection paramServiceConnection, int paramInt)
  {
    Intent localIntent = new Intent(this.mContext, IfeAodService.class);
    localIntent.putExtra("data", paramString);
    if (this.mContext.bindService(localIntent, paramServiceConnection, paramInt))
    {
      Log.v(TAG, "bind to local service");
      return;
    }
    Log.e(TAG, "bind error");
  }
  
  public void bindToIfeDataService(String paramString, ServiceConnection paramServiceConnection)
  {
    Intent localIntent1 = new Intent();
    localIntent1.putExtra("data", paramString);
    localIntent1.setComponent(new ComponentName("aero.panasonic.inflight.app.seatback", "aero.panasonic.inflight.services.service.IfeDataService"));
    Intent localIntent2 = new Intent(this.mContext, IfeDataService.class);
    localIntent2.putExtra("data", paramString);
    if (this.mContext.bindService(localIntent1, paramServiceConnection, 73))
    {
      Log.v(TAG, "bind to remote service");
      return;
    }
    if (this.mContext.bindService(localIntent2, paramServiceConnection, 73))
    {
      Log.v(TAG, "bind to local service");
      return;
    }
    Log.e(TAG, "bind error");
  }
}
