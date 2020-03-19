package com.google.android.apps.enterprise.dmagent.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.List;

public class t
  implements l
{
  private PackageManager a;
  
  public t(Context paramContext)
  {
    this(paramContext.getPackageManager());
  }
  
  private t(PackageManager paramPackageManager)
  {
    this.a = paramPackageManager;
  }
  
  public final int a(String paramString)
  {
    try
    {
      int i = this.a.getApplicationEnabledSetting(paramString);
      return i;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      String str = String.valueOf(localIllegalArgumentException);
      Log.e("DMAgent", String.valueOf(paramString).length() + 47 + String.valueOf(str).length() + "Failed to get disabled app status: " + paramString + " Exception: " + str);
    }
    return 2;
  }
  
  public final PackageInfo a(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.a.getPackageInfo(paramString, paramInt);
  }
  
  public final List<PackageInfo> a(int paramInt)
  {
    return this.a.getInstalledPackages(4160);
  }
  
  public final void a(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      this.a.setComponentEnabledSetting(paramComponentName, paramInt1, 1);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      paramComponentName = String.valueOf(paramComponentName);
      Log.e("DMAgent", String.valueOf(paramComponentName).length() + 40 + "Failed to change " + paramComponentName + "'s state to " + paramInt1, localIllegalArgumentException);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
  }
  
  public final void a(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      this.a.setApplicationEnabledSetting(paramString, paramInt1, 0);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      String str1 = String.valueOf(localIllegalArgumentException);
      Log.e("DMAgent", String.valueOf(paramString).length() + 34 + String.valueOf(str1).length() + "Failed to disable app " + paramString + " Exception: " + str1);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      String str2 = String.valueOf(localSecurityException);
      Log.e("DMAgent", String.valueOf(paramString).length() + 34 + String.valueOf(str2).length() + "Failed to disable app " + paramString + " Exception: " + str2);
    }
  }
  
  public final boolean a(String paramString, UserHandle paramUserHandle)
  {
    try
    {
      boolean bool = ((Boolean)PackageManager.class.getMethod("getApplicationBlockedSettingAsUser", new Class[] { String.class, ClassLoader.getSystemClassLoader().loadClass("android.os.UserHandle") }).invoke(this.a, new Object[] { paramString, paramUserHandle })).booleanValue();
      return bool;
    }
    catch (Exception paramUserHandle)
    {
      paramUserHandle = String.valueOf(paramUserHandle);
      Log.e("DMAgent", String.valueOf(paramString).length() + 47 + String.valueOf(paramUserHandle).length() + "Failed to get disabled app status: " + paramString + " Exception: " + paramUserHandle);
    }
    return true;
  }
  
  public final boolean a(String paramString, boolean paramBoolean, UserHandle paramUserHandle)
  {
    try
    {
      Class localClass = ClassLoader.getSystemClassLoader().loadClass("android.os.UserHandle");
      paramBoolean = ((Boolean)PackageManager.class.getMethod("setApplicationBlockedSettingAsUser", new Class[] { String.class, Boolean.TYPE, localClass }).invoke(this.a, new Object[] { paramString, Boolean.valueOf(paramBoolean), paramUserHandle })).booleanValue();
      return paramBoolean;
    }
    catch (Exception paramUserHandle)
    {
      paramUserHandle = String.valueOf(paramUserHandle);
      Log.e("DMAgent", String.valueOf(paramString).length() + 34 + String.valueOf(paramUserHandle).length() + "Failed to disable app " + paramString + " Exception: " + paramUserHandle);
    }
    return false;
  }
  
  public final Intent b(String paramString)
  {
    try
    {
      Intent localIntent = this.a.getLaunchIntentForPackage(paramString);
      return localIntent;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {}
    }
    for (paramString = "Failed to get launch intent for package: ".concat(paramString);; paramString = new String("Failed to get launch intent for package: "))
    {
      Log.e("DMAgent", paramString, localIllegalArgumentException);
      return null;
    }
  }
}
