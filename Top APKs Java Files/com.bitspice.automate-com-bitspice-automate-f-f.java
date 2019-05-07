package com.bitspice.automate.f;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.bitspice.automate.AutoMateApplication;
import com.bitspice.automate.a;
import com.bitspice.automate.menus.c;
import com.bitspice.automate.settings.b;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class f
{
  public static String a(Bitmap paramBitmap, String paramString)
  {
    if (paramBitmap != null) {
      try
      {
        paramString = new File(new ContextWrapper(AutoMateApplication.b()).getDir("appData", 0), paramString);
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
        localFileOutputStream.close();
        paramBitmap = paramString.getAbsolutePath();
        return paramBitmap;
      }
      catch (Exception paramBitmap)
      {
        a.a(paramBitmap, "ShortcutsUtils", "Exception in ShortcutsUtils.saveBitmap()");
      }
    }
    return null;
  }
  
  public static ArrayList<c> a(List<ApplicationInfo> paramList)
  {
    PackageManager localPackageManager = AutoMateApplication.b().getPackageManager();
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramList.next();
      c localC = new c();
      localC.c(localApplicationInfo.packageName);
      localC.a(localApplicationInfo.packageName + ".jpg");
      localC.b(localApplicationInfo.loadLabel(localPackageManager).toString());
      localArrayList.add(localC);
    }
    return localArrayList;
  }
  
  public static List<ApplicationInfo> a()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = AutoMateApplication.b().getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(0);
    Log.i("ShortcutsUtils", "Loading applications - Found " + ((List)localObject).size() + " installed apps on device.");
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      try
      {
        if ((!"com.bitspice.automate".equals(localPackageInfo.packageName)) && (localPackageManager.getLaunchIntentForPackage(localPackageInfo.packageName) != null))
        {
          localArrayList.add(localPackageInfo.applicationInfo);
          a(a.a(localPackageInfo.applicationInfo.loadIcon(localPackageManager)), localPackageInfo.packageName + ".jpg");
        }
      }
      catch (Exception localException2)
      {
        Log.e("ShortcutsUtils", "Problem adding app: " + localException2.getLocalizedMessage());
      }
    }
    try
    {
      Collections.sort(localArrayList, new ApplicationInfo.DisplayNameComparator(localPackageManager));
      return localArrayList;
    }
    catch (Exception localException1) {}
    return localArrayList;
  }
  
  public static void a(Activity paramActivity)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setTitle(2131165895);
    paramActivity = paramActivity.getLayoutInflater().inflate(2130903092, null);
    localBuilder.setView(paramActivity);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        paramAnonymousDialogInterface = this.a.getText().toString();
        final String str = this.b.getText().toString();
        str.replace(";", "").replace(",", "");
        final c localC = new c();
        localC.c("am-shortcut:com.bitspice.automate.shortcut.BROWSER;" + paramAnonymousDialogInterface + ";" + str);
        localC.b(paramAnonymousDialogInterface);
        paramAnonymousInt = AutoMateApplication.b().getResources().getDimensionPixelSize(2131230800);
        Glide.with(AutoMateApplication.b()).load("http://www.google.com/s2/favicons?domain=" + str).asBitmap().into(new SimpleTarget(paramAnonymousInt, paramAnonymousInt)
        {
          public void a(Bitmap paramAnonymous2Bitmap, GlideAnimation paramAnonymous2GlideAnimation)
          {
            paramAnonymous2GlideAnimation = a.c(str);
            localC.a(paramAnonymous2GlideAnimation);
            f.a(paramAnonymous2Bitmap, paramAnonymous2GlideAnimation + ".jpg");
          }
        });
        f.a(localC);
      }
    });
    localBuilder.setNegativeButton(17039360, null);
    localBuilder.show();
  }
  
  public static void a(Intent paramIntent, Context paramContext)
  {
    try
    {
      Intent.ShortcutIconResource localShortcutIconResource = (Intent.ShortcutIconResource)paramIntent.getParcelableExtra("android.intent.extra.shortcut.ICON_RESOURCE");
      Object localObject2 = (Bitmap)paramIntent.getParcelableExtra("android.intent.extra.shortcut.ICON");
      String str = paramIntent.getStringExtra("android.intent.extra.shortcut.NAME");
      Intent localIntent = (Intent)paramIntent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
      paramIntent = (Intent)localObject2;
      if (localObject2 == null)
      {
        paramIntent = (Intent)localObject2;
        if (localShortcutIconResource == null) {}
      }
      try
      {
        localObject1 = AutoMateApplication.b().getPackageManager().getResourcesForApplication(localShortcutIconResource.packageName);
        paramIntent = (Intent)localObject2;
        if (localObject1 != null) {
          paramIntent = a.a(((Resources)localObject1).getDrawable(((Resources)localObject1).getIdentifier(localShortcutIconResource.resourceName, null, null)));
        }
        if ((str != null) && (localIntent != null) && (paramIntent != null))
        {
          localObject2 = localIntent.toUri(1);
          localObject1 = a.c((String)localObject2);
          paramIntent = a(paramIntent, (String)localObject1 + ".jpg");
          Log.d("ShortcutsUtils", "INTENT:" + (String)localObject2);
          Log.d("ShortcutsUtils", "INTENT_HASH:" + (String)localObject1);
          Log.d("ShortcutsUtils", "INTENT_LABEL:" + str);
          Log.d("ShortcutsUtils", "INTENT_ICON:" + paramIntent);
          b.a((String)localObject1 + ".intent", (String)localObject2);
          b.a((String)localObject1 + ".iconPath", paramIntent);
          b.a((String)localObject1 + ".label", str);
          paramIntent = b.b("SHORTCUT_DATA", c());
          b.a("SHORTCUT_DATA", paramIntent + "shortcut:" + (String)localObject1 + ",");
          a.a(paramContext, paramContext.getString(2131165562, new Object[] { str }));
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramIntent)
      {
        for (;;)
        {
          paramIntent.printStackTrace();
          Object localObject1 = null;
        }
      }
      return;
    }
    catch (Exception paramIntent)
    {
      a.a(paramIntent, "ShortcutsUtils", "Exception in ShortcutUtils.createShortcut()");
    }
  }
  
  public static void a(c paramC)
  {
    String str = b.b("SHORTCUT_DATA", c());
    b.a("SHORTCUT_DATA", str + paramC.c() + ",");
    a.a(AutoMateApplication.b(), a.a(2131165562, new String[] { paramC.b() }));
  }
  
  public static void a(String paramString)
  {
    try
    {
      new File(paramString).delete();
      return;
    }
    catch (Exception paramString)
    {
      Log.e("ShortcutsUtils", paramString.getMessage());
    }
  }
  
  public static ArrayList<c> b(List<ActivityInfo> paramList)
  {
    PackageManager localPackageManager = AutoMateApplication.b().getPackageManager();
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ActivityInfo localActivityInfo = (ActivityInfo)paramList.next();
      c localC = new c();
      localC.c(localActivityInfo.packageName);
      localC.a(localActivityInfo.name + ".jpg");
      localC.b(localActivityInfo.loadLabel(localPackageManager).toString());
      localC.a(localActivityInfo);
      localArrayList.add(localC);
      a(a.a(localActivityInfo.loadIcon(localPackageManager)), localC.a());
    }
    return localArrayList;
  }
  
  public static List<ActivityInfo> b()
  {
    Object localObject1 = new Intent("android.intent.action.CREATE_SHORTCUT");
    ArrayList localArrayList = new ArrayList();
    localObject1 = AutoMateApplication.b().getPackageManager().queryIntentActivities((Intent)localObject1, 128).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject1).next();
      Bundle localBundle = localResolveInfo.activityInfo.metaData;
      if (localBundle != null)
      {
        Iterator localIterator = localBundle.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject2 = localBundle.get(str);
          Log.d("ShortcutsUtils", String.format("KEY:%s VALUE:%s CLASS:(%s)", new Object[] { str, localObject2.toString(), localObject2.getClass().getName() }));
        }
      }
      localArrayList.add(localResolveInfo.activityInfo);
    }
    return localArrayList;
  }
  
  public static String c()
  {
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString2 = new String[3];
    arrayOfString2[0] = "com.google.android.calendar";
    arrayOfString2[1] = "com.google.android.music";
    arrayOfString2[2] = "com.google.android.apps.maps";
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "com.bitspice.automate.shortcut.APPS";
    arrayOfString1[1] = "com.bitspice.automate.shortcut.EXIT";
    int k = arrayOfString2.length;
    int i = 0;
    while (i < k)
    {
      String str = arrayOfString2[i];
      if (a.f(str)) {
        localStringBuilder.append("package:" + str + ",");
      }
      i += 1;
    }
    k = arrayOfString1.length;
    i = j;
    while (i < k)
    {
      arrayOfString2 = arrayOfString1[i];
      localStringBuilder.append("am-shortcut:" + arrayOfString2 + ",");
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
