package com.xinmei365.font;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import java.util.List;

public class aea
{
  public aea() {}
  
  public static void a(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("gh_8a5c4efbc271");
      localIntent.addFlags(1073741824);
      localIntent.addFlags(268435456);
      localIntent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
      localIntent.putExtra("LauncherUI_From_Biz_Shortcut", true);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Toast.makeText(paramContext, 2131230949, 1).show();
    }
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    paramUri = new Intent("android.intent.action.VIEW", paramUri);
    try
    {
      paramContext.startActivity(paramUri);
      return;
    }
    catch (Exception paramUri)
    {
      Toast.makeText(paramContext, 2131230941, 1).show();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new Intent(paramContext, Class.forName(paramString1));
      paramString1.putExtra("source", paramString2);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception
  {
    new Intent();
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString1));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        try
        {
          paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
        }
        finally {}
        if (paramContext == null) {}
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext = null;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
    return true;
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
    throws Exception
  {
    a(paramContext, paramString1, paramString2, null, null);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    paramString = ((PackageManager)localObject).getPackageArchiveInfo(paramString, 1);
    paramContext = null;
    if (paramString != null) {
      paramContext = paramString.applicationInfo.packageName;
    }
    paramString = ((PackageManager)localObject).getInstalledPackages(0);
    localObject = new PackageInfo[paramString.size()];
    paramString.toArray((Object[])localObject);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      if (localObject[i].packageName.equals(paramContext)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static void c(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void d(Context paramContext, String paramString)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (localIntent != null)
    {
      paramContext.startActivity(localIntent);
      return;
    }
    try
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("market://details?id=" + paramString));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, 2131230941, 0).show();
    }
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void f(@NonNull Context paramContext, @NonNull String paramString) {}
}
