package com.zjlib.thirtydaylib.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.Iterator;
import java.util.List;

public class k
{
  private static k a;
  
  private k() {}
  
  public static k a()
  {
    try
    {
      if (a == null) {
        a = new k();
      }
      k localK = a;
      return localK;
    }
    finally {}
  }
  
  public static Intent b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramContext.setFlags(268435456);
      paramContext.setPackage("com.android.vending");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramContext.setFlags(268435456);
    }
    return paramContext;
  }
  
  public static Intent c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramContext.setFlags(268435456);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramContext.setFlags(268435456);
    }
    return paramContext;
  }
  
  public void a(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      localIntent.setFlags(268435456);
      if (a(paramContext)) {
        localIntent.setPackage("com.android.vending");
      }
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public boolean a(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            boolean bool = localPackageInfo.packageName.equals("com.android.vending");
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      localRuntimeException.printStackTrace();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
          if (i != 0) {
            break;
          }
          return true;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
        localException = localException;
        localException.printStackTrace();
      }
    }
    return false;
  }
}
