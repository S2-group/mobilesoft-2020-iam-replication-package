package com.cyou.cma.clauncher.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.cyou.cma.clauncher.download.c;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class e
{
  public static File a(Context paramContext, String paramString1, String paramString2)
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {}
    for (paramContext = new File(Environment.getExternalStorageDirectory(), paramString1); (paramContext.isDirectory()) || (paramContext.mkdirs()); paramContext = paramContext.getDir(paramString1, 0)) {
      return new File(paramContext, paramString2);
    }
    return null;
  }
  
  public static void a(Context paramContext, File paramFile)
  {
    int i;
    if (!paramFile.exists()) {
      i = 0;
    }
    for (;;)
    {
      Object localObject;
      if (i == 0)
      {
        return;
        localObject = paramFile.getParent();
        String str = paramFile.getPath();
        if ((str.startsWith("/data/data")) || (str.startsWith("/data/user/0")))
        {
          int j = 2;
          label48:
          if ((c.a("777", str)) && (c.a("777", (String)localObject))) {}
          for (i = 1;; i = 0)
          {
            int k = j - 1;
            if (i == 0)
            {
              j = k;
              if (k >= 0) {
                break label48;
              }
            }
            break;
          }
        }
      }
      else
      {
        localObject = new Intent();
        ((Intent)localObject).setAction("android.intent.action.VIEW");
        ((Intent)localObject).addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 24)
        {
          ((Intent)localObject).setDataAndType(FileProvider.getUriForFile(paramContext, paramContext.getPackageName() + ".provider", paramFile), "application/vnd.android.package-archive");
          ((Intent)localObject).setFlags(268435456);
          ((Intent)localObject).addFlags(1);
        }
        for (;;)
        {
          paramContext.startActivity((Intent)localObject);
          return;
          ((Intent)localObject).setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
        }
      }
      i = 1;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static String[] a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getAssets().list("preview");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int b(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject = new Point();
      paramContext.getDefaultDisplay().getRealSize((Point)localObject);
      return ((Point)localObject).x;
    }
    Object localObject = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    return ((DisplayMetrics)localObject).widthPixels;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    return a(paramContext, paramString);
  }
  
  public static int c(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject = new Point();
      paramContext.getDefaultDisplay().getRealSize((Point)localObject);
      return ((Point)localObject).y;
    }
    Object localObject = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    return ((DisplayMetrics)localObject).heightPixels;
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="));
    localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
    paramContext = paramContext.queryIntentActivities(localIntent, 0).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
    } while (!((ResolveInfo)paramContext.next()).activityInfo.packageName.equals("com.android.vending"));
    return true;
  }
  
  public static int e(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    paramContext = (WindowManager)paramContext.getSystemService("window");
    Display localDisplay = paramContext.getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(localDisplay, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.heightPixels;
      int j = paramContext.getDefaultDisplay().getHeight();
      return i - j;
    }
    catch (Exception paramContext) {}
    return 0;
  }
}
