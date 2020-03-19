package com.zjlib.thirtydaylib.e;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.zjlib.thirtydaylib.R.string;
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
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", String.format(paramContext.getString(R.string.td_share_title), new Object[] { paramContext.getString(R.string.app_name) }));
      localIntent.putExtra("android.intent.extra.TEXT", paramContext.getString(R.string.td_share_text, new Object[] { paramString, "https://play.google.com/store/apps/details?id=loseweight.weightloss.workout.fitness" }));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      m.a(paramContext, "EmailUtils-4", paramString, false);
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
