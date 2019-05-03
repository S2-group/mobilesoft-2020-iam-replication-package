package com.popularapp.sevenmins.utils;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class h
{
  private static h a;
  
  private h() {}
  
  public static h a()
  {
    try
    {
      if (a == null) {
        a = new h();
      }
      h localH = a;
      return localH;
    }
    finally {}
  }
  
  public boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.google.android.gm");
        } while (!bool);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Error paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.android.email");
        } while (!bool);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Error paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public void c(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getString(2131231051));
      localIntent.putExtra("android.intent.extra.TEXT", paramContext.getString(2131230914) + "http://play.google.com/store/apps/details?id=com.popularapp.sevenmins");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      o.a(paramContext, "EmailUtils-4", localActivityNotFoundException, false);
      localActivityNotFoundException.printStackTrace();
    }
  }
}
