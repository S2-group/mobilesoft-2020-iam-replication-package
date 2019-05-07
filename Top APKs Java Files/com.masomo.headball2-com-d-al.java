package com.d;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.aiming.mdt.sdk.util.ADLogger;
import java.util.Iterator;
import java.util.List;

public class al
{
  private static String a;
  
  private static String a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext != null) && (paramContext.activityInfo != null))
    {
      if (paramContext.activityInfo.packageName.equals("android")) {
        return "";
      }
      return paramContext.activityInfo.packageName;
    }
    return "";
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool5 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    if (paramContext != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      if (TextUtils.isEmpty(a)) {
        a = c(paramContext);
      }
      paramContext = paramContext.getContentResolver();
      if (!TextUtils.isEmpty(a))
      {
        boolean bool2 = bool5;
        try
        {
          paramContext = paramContext.query(Uri.parse(a), new String[] { "title", "iconResource" }, "title=?", new String[] { paramString }, null);
          boolean bool1 = bool4;
          if (paramContext != null)
          {
            bool1 = bool4;
            bool2 = bool5;
            if (paramContext.getCount() > 0) {
              bool1 = true;
            }
          }
          bool3 = bool1;
          if (paramContext != null)
          {
            bool2 = bool1;
            bool3 = bool1;
            if (!paramContext.isClosed())
            {
              bool2 = bool1;
              paramContext.close();
              return bool1;
            }
          }
        }
        catch (Exception paramContext)
        {
          paramString = new StringBuilder();
          paramString.append("isShortCutExist:");
          paramString.append(paramContext.getMessage());
          ADLogger.d(paramString.toString());
          bool3 = bool2;
        }
      }
      return bool3;
    }
    return false;
  }
  
  private static String b(Context paramContext)
  {
    return b(paramContext, "com.android.launcher.permission.READ_SETTINGS");
  }
  
  private static String b(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if (((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) && (!TextUtils.isEmpty(localProviderInfo.authority)) && (localProviderInfo.authority.contains(".launcher.settings")))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static String c(Context paramContext)
  {
    Object localObject2 = b(paramContext);
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!((String)localObject2).trim().equals("")) {}
    }
    else
    {
      localObject1 = a(paramContext);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(".permission.READ_SETTINGS");
      localObject1 = b(paramContext, ((StringBuilder)localObject2).toString());
    }
    paramContext = (Context)localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      int i = Build.VERSION.SDK_INT;
      if (i < 8) {
        paramContext = "com.android.launcher.settings";
      } else if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else {
        paramContext = "com.android.launcher3.settings";
      }
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("content://");
    ((StringBuilder)localObject1).append(paramContext);
    ((StringBuilder)localObject1).append("/favorites?notify=true");
    return ((StringBuilder)localObject1).toString();
  }
}
