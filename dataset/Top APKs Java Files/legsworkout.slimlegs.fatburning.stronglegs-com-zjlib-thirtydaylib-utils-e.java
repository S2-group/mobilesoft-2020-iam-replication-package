package com.zjlib.thirtydaylib.utils;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.zjlib.thirtydaylib.a.i;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class e
{
  private static e a;
  
  private e() {}
  
  public static e a()
  {
    try
    {
      if (a == null) {
        a = new e();
      }
      e localE = a;
      return localE;
    }
    finally {}
  }
  
  public void a(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.SEND");
      paramString.setType("text/plain");
      paramString.putExtra("android.intent.extra.SUBJECT", String.format(paramContext.getString(a.i.td_share_title), new Object[] { paramContext.getString(a.i.app_name) }));
      paramString.putExtra("android.intent.extra.TEXT", String.format(paramContext.getString(a.i.td_share_text), new Object[] { paramContext.getString(a.i.app_name) }) + "https://legsworkout.page.link/share");
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      j.a(paramContext, "EmailUtils-4", paramString, false);
      paramString.printStackTrace();
    }
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
}
