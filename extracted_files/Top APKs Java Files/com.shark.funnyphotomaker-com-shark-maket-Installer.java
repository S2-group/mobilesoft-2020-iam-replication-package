package com.shark.maket;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Installer
{
  private Context mActivity;
  
  public Installer(Context paramContext)
  {
    this.mActivity = paramContext;
  }
  
  public static boolean isInstalled(Activity paramActivity, String paramString)
  {
    paramString = paramString.replace("-focus", "");
    paramActivity = paramActivity.getPackageManager();
    try
    {
      paramActivity.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramActivity) {}
    return false;
  }
  
  public static boolean isYouTubeInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.google.android.youtube", 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public void addShortcut(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if (!isInstalled(paramString2))
    {
      paramString2 = new Intent();
      paramString2.setAction("android.intent.action.VIEW");
      paramString2.setData(Uri.parse(paramString1));
      paramString1 = new Intent();
      paramString1.putExtra("android.intent.extra.shortcut.INTENT", paramString2);
      paramString1.putExtra("android.intent.extra.shortcut.NAME", this.mActivity.getResources().getString(paramInt2));
      paramString1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this.mActivity.getApplicationContext(), paramInt1));
      paramString1.putExtra("duplicate", false);
      paramString1.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
      this.mActivity.getApplicationContext().sendBroadcast(paramString1);
    }
  }
  
  public void addShortcut(String paramString1, Bitmap paramBitmap, String paramString2, String paramString3)
  {
    if (!isInstalled(paramString3))
    {
      paramString3 = new Intent();
      paramString3.setAction("android.intent.action.VIEW");
      paramString3.setData(Uri.parse(paramString1));
      paramString1 = new Intent();
      paramString1.putExtra("android.intent.extra.shortcut.INTENT", paramString3);
      paramString1.putExtra("android.intent.extra.shortcut.NAME", paramString2);
      paramString1.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
      paramString1.putExtra("duplicate", false);
      paramString1.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
      this.mActivity.getApplicationContext().sendBroadcast(paramString1);
    }
  }
  
  public void goTOApp(String paramString1, String paramString2)
  {
    Object localObject1 = this.mActivity.getPackageManager();
    String str = paramString1.replace("-focus", "");
    paramString2 = paramString2.replace("-focus", "");
    try
    {
      ((PackageManager)localObject1).getPackageInfo(str, 1);
      paramString1 = new Intent("android.intent.action.MAIN", null);
      paramString1.addCategory("android.intent.category.LAUNCHER");
      Object localObject2 = ((PackageManager)localObject1).queryIntentActivities(paramString1, 0);
      Collections.sort((List)localObject2, new ResolveInfo.DisplayNameComparator((PackageManager)localObject1));
      paramString1 = "";
      localObject1 = ((List)localObject2).iterator();
      if (!((Iterator)localObject1).hasNext()) {}
      for (;;)
      {
        if (paramString1.equals("")) {
          return;
        }
        localObject1 = new Intent();
        ((Intent)localObject1).setClassName(str, paramString1);
        this.mActivity.startActivity((Intent)localObject1);
        return;
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        if (!((ResolveInfo)localObject2).activityInfo.packageName.equals(str)) {
          break;
        }
        paramString1 = ((ResolveInfo)localObject2).activityInfo.name;
      }
      return;
    }
    catch (Exception paramString1)
    {
      if ((!paramString2.equals("")) && (paramString2.contains("http"))) {
        paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      }
      for (;;)
      {
        try
        {
          this.mActivity.startActivity(paramString1);
          return;
        }
        catch (ActivityNotFoundException paramString1)
        {
          return;
        }
        if ((!paramString2.equals("")) && (!paramString2.contains("http"))) {
          paramString1 = new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + paramString2));
        } else {
          paramString1 = new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + str));
        }
      }
    }
  }
  
  public void goTOAppGP(String paramString1, String paramString2)
  {
    this.mActivity.getPackageManager();
    paramString1 = paramString1.replace("-focus", "");
    paramString2 = paramString2.replace("-focus", "");
    try
    {
      if ((!paramString2.equals("")) && (paramString2.contains("http"))) {
        paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      }
      for (;;)
      {
        this.mActivity.startActivity(paramString1);
        return;
        if ((!paramString2.equals("")) && (!paramString2.contains("http"))) {
          paramString1 = new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + paramString2));
        } else {
          paramString1 = new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + paramString1));
        }
      }
      return;
    }
    catch (ActivityNotFoundException paramString1) {}
  }
  
  public boolean isInstalled(String paramString)
  {
    paramString = paramString.replace("-focus", "");
    PackageManager localPackageManager = this.mActivity.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public ArrayList<String> listAppInstalled()
  {
    Iterator localIterator = null;
    List localList = this.mActivity.getPackageManager().getInstalledApplications(128);
    Object localObject = localIterator;
    if (localList != null)
    {
      localObject = localIterator;
      if (localList.size() > 0)
      {
        localObject = new ArrayList();
        localIterator = localList.iterator();
      }
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localObject;
      }
      ((ArrayList)localObject).add(((ApplicationInfo)localIterator.next()).packageName);
    }
  }
}
