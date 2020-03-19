package com.tav.screen.Tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import com.tav.screen.Views.FileManagerActivity;
import com.tav.screen.Views.MiniBrowser;
import com.tav.screen.Views.SettingsActivity;
import com.tav.screen.Views.SettingsActivity.MainPreferenceFragment;
import java.util.List;

public class MenuProvider
{
  public MenuProvider() {}
  
  private static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext == null) {
      return false;
    }
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static void openBrowser(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, MiniBrowser.class);
    localIntent.putExtra("MiniBrowser.UrlString", paramString);
    paramContext.startActivity(localIntent);
  }
  
  public static void openOfficialSite(Context paramContext)
  {
    openBrowser(paramContext, paramContext.getResources().getString(2131230765));
  }
  
  public static void openSampleVideo(Context paramContext)
  {
    openBrowser(paramContext, paramContext.getResources().getString(2131230766));
  }
  
  public static void rateApp(Context paramContext)
  {
    String str1 = paramContext.getResources().getString(2131230743);
    String str2 = paramContext.getPackageName();
    if (isAppInstalled(paramContext, "com.coolapk.market"))
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str1)));
      return;
    }
    if (isAppInstalled(paramContext, "com.android.vending"))
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str2)));
      return;
    }
    openBrowser(paramContext, str1);
  }
  
  public static void sendDownloadAddr(Context paramContext)
  {
    String str1 = paramContext.getString(2131230752);
    String str2 = paramContext.getString(2131230742);
    sendTo(paramContext, str1, paramContext.getString(2131230765), str2);
  }
  
  private static void sendTo(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.addFlags(268435456);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString3);
    paramContext.startActivity(Intent.createChooser(localIntent, paramString1));
  }
  
  public static void shareApp(Context paramContext)
  {
    String str1 = paramContext.getString(2131230760);
    String str2 = paramContext.getString(2131230742);
    sendTo(paramContext, str1, paramContext.getString(2131230743), str2);
  }
  
  public static void startFileTransferActivity(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, FileManagerActivity.class));
  }
  
  public static void startSettingsActivity(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, SettingsActivity.class);
    localIntent.putExtra(":android:show_fragment", SettingsActivity.MainPreferenceFragment.class.getName());
    localIntent.putExtra(":android:no_headers", true);
    paramContext.startActivity(localIntent);
  }
}
