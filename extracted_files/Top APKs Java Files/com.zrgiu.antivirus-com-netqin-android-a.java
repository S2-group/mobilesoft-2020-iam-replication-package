package com.netqin.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;
import com.netqin.antivirus.util.k;
import java.util.ArrayList;
import java.util.List;

public class a
{
  public static List a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    if (!com.netqin.system.a.d(paramContext))
    {
      Toast.makeText(paramContext, 2131361865, 0).show();
      return;
    }
    for (;;)
    {
      int i;
      try
      {
        if (b(paramContext))
        {
          Uri localUri = Uri.parse("market://details?id=" + paramString1 + paramString2);
          com.netqin.antivirus.util.a.a("MoreUrl", "Url  :  market://details?id=" + paramString1 + paramString2);
          paramString1 = new Intent("android.intent.action.VIEW", localUri);
          paramString2 = paramContext.getPackageManager();
          new ArrayList();
          paramString2 = paramString2.queryIntentActivities(paramString1, 0);
          i = 0;
          if (i >= paramString2.size()) {
            break;
          }
          if (!((ResolveInfo)paramString2.get(i)).activityInfo.packageName.equals("com.android.vending")) {
            break label265;
          }
          paramString1.setComponent(new ComponentName("com.android.vending", ((ResolveInfo)paramString2.get(i)).activityInfo.name));
          paramString1.addFlags(268435456);
          paramContext.getApplicationContext().startActivity(paramString1);
          break label265;
        }
        k.a(paramContext, "http://play.google.com/store/apps/details?id=" + paramString1 + paramString2);
        com.netqin.antivirus.util.a.a("MoreUrl", "Url  :  http://play.google.com/store/apps/details?id=" + paramString1 + paramString2);
        return;
      }
      catch (Exception paramString1)
      {
        Toast.makeText(paramContext, 2131361794, 0).show();
        return;
      }
      label265:
      i += 1;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = paramContext.getPackageManager().getPackageInfo(paramString, 0).applicationInfo.enabled;
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = a(paramContext);
      int i = 0;
      while (i < paramContext.size())
      {
        String str = ((PackageInfo)paramContext.get(i)).packageName;
        boolean bool = str.contains(paramString);
        if (bool) {
          return str;
        }
        i += 1;
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean b(Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = paramContext.getPackageManager().getPackageInfo("com.android.vending", 0).applicationInfo.enabled;
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
