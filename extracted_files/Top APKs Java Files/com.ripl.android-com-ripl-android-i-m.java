package com.ripl.android.i;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.ripl.android.b;
import com.ripl.android.utils.y;
import java.util.Iterator;
import java.util.List;

public abstract class m
{
  public m() {}
  
  private void b(Context paramContext)
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(a());
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(a());
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
  }
  
  private boolean e()
  {
    Object localObject = b.b().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        boolean bool = ((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(a());
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      String str = c();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("threw trying to get package information: ");
      localStringBuilder.append(localException);
      y.a(str, localStringBuilder.toString());
    }
    return false;
  }
  
  protected abstract String a();
  
  public void a(Activity paramActivity)
  {
    new ab().a(paramActivity.getString(d()));
    b(paramActivity);
  }
  
  public boolean a(Context paramContext)
  {
    if (!e())
    {
      new ab().a(paramContext.getString(d()));
      b(paramContext);
      return false;
    }
    return true;
  }
  
  protected abstract int b();
  
  public void b(Activity paramActivity)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setPackage(a());
      localIntent.setType("application/octet-stream");
      paramActivity.startActivityForResult(localIntent, b());
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    a(paramActivity);
  }
  
  protected abstract String c();
  
  protected abstract int d();
}
