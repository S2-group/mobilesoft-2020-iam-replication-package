package com.samsung.android.sdk.cup;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import java.util.Iterator;
import java.util.List;

public class Scup
  implements SsdkInterface
{
  private static final String a = Scup.class.getSimpleName();
  private static boolean b = false;
  
  public Scup() {}
  
  static boolean a()
  {
    return b;
  }
  
  int SCUPSDKVERSION104_L(int paramInt)
  {
    return paramInt * paramInt + paramInt;
  }
  
  public int getVersionCode()
  {
    return 1;
  }
  
  public String getVersionName()
  {
    return "1.0.4";
  }
  
  public void initialize(Context paramContext)
    throws SsdkUnsupportedException
  {
    Log.d("Scup", "Scup jar version = " + getVersionCode());
    if (paramContext == null) {
      throw new IllegalArgumentException("context is invalid.");
    }
    Object localObject1 = Build.BRAND;
    Object localObject2 = Build.MANUFACTURER;
    if ((localObject1 == null) || (localObject2 == null)) {
      throw new SsdkUnsupportedException("Vendor is not supported", 0);
    }
    if ((((String)localObject1).compareToIgnoreCase("Samsung") != 0) && (((String)localObject2).compareToIgnoreCase("Samsung") != 0)) {
      throw new SsdkUnsupportedException("Vendor is not supported", 0);
    }
    localObject1 = paramContext.getPackageManager();
    localObject2 = ((PackageManager)localObject1).getInstalledApplications(128).iterator();
    int i = 0;
    for (;;)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        if (i != 0) {
          break;
        }
        try
        {
          ((PackageManager)localObject1).getPackageInfo("com.samsung.android.wms", 0);
          Log.d(a, "Version of the manager is low.. Please update the manager.");
          throw new SsdkUnsupportedException("Version of the manager is low.", 3);
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Log.d(a, "You SHOULD INSTALL Scup service apk file !!!!");
          throw new SsdkUnsupportedException("The manager is not installed.", 2);
        }
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      Object localObject3 = localApplicationInfo.metaData;
      if ((localObject3 != null) && (((Bundle)localObject3).getBoolean("SAMSUNG_CUP_SERVICE", false)))
      {
        try
        {
          localObject3 = paramContext.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 64);
          if ((localObject3 == null) || (((PackageInfo)localObject3).signatures == null))
          {
            Log.d(a, "You SHOULD INSTALL the manager!!!! (1)");
            throw new SsdkUnsupportedException("The manager is not installed. Cannot find localPackageInfo.", 2);
          }
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Log.d(a, "You SHOULD INSTALL the manager!!!! (2)");
          throw new SsdkUnsupportedException("The manager is not installed. Cannot find localName.", 2);
        }
        localObject3 = ((PackageInfo)localObject3).signatures;
        if ((localObject3.length <= 0) || (localObject3[0].hashCode() != -1283921572))
        {
          Log.d(a, "Your manager is not signed!!!!");
          throw new SsdkUnsupportedException("The manager is not signed.", 2);
        }
        try
        {
          i = ((PackageManager)localObject1).getPackageInfo(localApplicationInfo.packageName, 0).versionCode;
          if (i <= 1045)
          {
            Log.d(a, "Version of the manager is low.. Please update the manager.");
            throw new SsdkUnsupportedException("Version of the manager is low.", 3);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            i = 0;
          }
          try
          {
            System.load("/data/data/" + localApplicationInfo.packageName + "/lib/libSpiCodec.so");
            i = 1;
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
            try
            {
              System.loadLibrary("SpiCodec");
              i = 1;
            }
            catch (Exception localException)
            {
              i = 1;
            }
          }
        }
      }
    }
    b = true;
  }
  
  public boolean isFeatureEnabled(int paramInt)
  {
    return true;
  }
}
