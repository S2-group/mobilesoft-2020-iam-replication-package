package com.pushwoosh.internal.platform.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
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
  private Context j()
  {
    return (Context)this.a.get();
  }
  
  @Nullable
  public ApplicationInfo a()
  {
    try
    {
      if (j() == null) {
        return null;
      }
      ApplicationInfo localApplicationInfo = j().getPackageManager().getApplicationInfo(j().getPackageName(), 128);
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
    if (j() == null) {
      return "";
    }
    return j().getPackageName();
  }
  
  public String c()
  {
    if (j() == null) {
      return "";
    }
    return Settings.Secure.getString(j().getContentResolver(), "android_id");
  }
  
  @Nullable
  public String d()
  {
    try
    {
      if (j() == null) {
        return null;
      }
      String str = j().getPackageManager().getPackageInfo(b(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      PWLog.exception(localNameNotFoundException);
    }
    return null;
  }
  
  public String e()
  {
    if (j() == null) {
      return null;
    }
    return j().getPackageManager().getInstallerPackageName(b());
  }
  
  public int f()
  {
    try
    {
      if (j() == null) {
        return 0;
      }
      int i = j().getPackageManager().getPackageInfo(b(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      PWLog.exception(localNameNotFoundException);
    }
    return 0;
  }
  
  public List<ApplicationInfo> g()
  {
    if (j() == null) {
      return Collections.emptyList();
    }
    return j().getPackageManager().getInstalledApplications(128);
  }
  
  public File h()
  {
    if (j() == null) {
      return null;
    }
    return j().getExternalCacheDir();
  }
  
  public CharSequence i()
  {
    if (j() == null) {
      return null;
    }
    return j().getPackageManager().getApplicationLabel(j().getApplicationInfo());
  }
}
