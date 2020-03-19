package com.pushwoosh.internal.platform.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.pushwoosh.internal.utils.PWLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class b
  implements a
{
  private final WeakReference<Context> a;
  
  public b(@Nullable Context paramContext)
  {
    this.a = new WeakReference(paramContext);
  }
  
  @Nullable
  private Context k()
  {
    return (Context)this.a.get();
  }
  
  @Nullable
  public ApplicationInfo a()
  {
    try
    {
      if (k() == null) {
        return null;
      }
      ApplicationInfo localApplicationInfo = k().getPackageManager().getApplicationInfo(k().getPackageName(), 128);
      return localApplicationInfo;
    }
    catch (Exception localException)
    {
      PWLog.exception(localException);
    }
    return null;
  }
  
  public String b()
  {
    if (k() == null) {
      return "";
    }
    return k().getPackageName();
  }
  
  public String c()
  {
    if (k() == null) {
      return "";
    }
    return Settings.Secure.getString(k().getContentResolver(), "android_id");
  }
  
  @Nullable
  public String d()
  {
    try
    {
      String str = AdvertisingIdClient.getAdvertisingIdInfo(k()).getId();
      return str;
    }
    catch (Throwable localThrowable)
    {
      PWLog.noise("Failed to get advertising id: " + localThrowable.getMessage());
    }
    return null;
  }
  
  @Nullable
  public String e()
  {
    try
    {
      if (k() == null) {
        return null;
      }
      String str = k().getPackageManager().getPackageInfo(b(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      PWLog.exception(localNameNotFoundException);
    }
    return null;
  }
  
  public String f()
  {
    if (k() == null) {
      return null;
    }
    return k().getPackageManager().getInstallerPackageName(b());
  }
  
  public int g()
  {
    try
    {
      if (k() == null) {
        return 0;
      }
      int i = k().getPackageManager().getPackageInfo(b(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      PWLog.exception(localNameNotFoundException);
    }
    return 0;
  }
  
  public List<ApplicationInfo> h()
  {
    if (k() == null) {
      return Collections.emptyList();
    }
    return k().getPackageManager().getInstalledApplications(128);
  }
  
  public File i()
  {
    if (k() == null) {
      return null;
    }
    return k().getExternalCacheDir();
  }
  
  public CharSequence j()
  {
    if (k() == null) {
      return null;
    }
    return k().getPackageManager().getApplicationLabel(k().getApplicationInfo());
  }
}
