package com.cleanmaster.security.pbsdk.interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import java.util.List;

public abstract interface IPackageInfoLoader
{
  public abstract Drawable getApplicationIcon(String paramString);
  
  public abstract ApplicationInfo getApplicationInfo(String paramString, int paramInt);
  
  public abstract String getApplicationLabel(String paramString);
  
  public abstract String getApplicationLabelName(ResolveInfo paramResolveInfo);
  
  public abstract List<PackageInfo> getInstalledPackages();
  
  public abstract PackageInfo getPackageInfo(String paramString, int paramInt);
  
  public abstract boolean isAppInstalled(String paramString);
  
  public abstract List<ResolveInfo> queryIntentActivities(Context paramContext, Intent paramIntent, int paramInt);
}
