package com.zjlib.thirtydaylib.f;

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
      paramString = new Intent("android.intent.action.SEND");
      paramString.setType("text/plain");
      paramString.putExtra("android.intent.extra.SUBJECT", String.format(paramContext.getString(R.string.td_share_title), new Object[] { paramContext.getString(R.string.app_name) }));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.format(paramContext.getString(R.string.td_share_text), new Object[] { paramContext.getString(R.string.app_name) }));
      localStringBuilder.append("https://goo.gl/25McUk");
      paramString.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      k.a(paramContext, "EmailUtils-4", paramString, false);
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
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            boolean bool = localPackageInfo.packageName.equals("com.google.android.gm");
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (Error paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            boolean bool = localPackageInfo.packageName.equals("com.android.email");
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (Error paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
