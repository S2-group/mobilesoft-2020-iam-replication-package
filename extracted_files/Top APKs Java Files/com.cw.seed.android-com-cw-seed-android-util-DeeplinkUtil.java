package com.cw.seed.android.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.cw.seed.android.core.Common;
import com.cw.seed.android.core.CwApp;
import com.cw.seed.android.model.CwConfig;
import com.cw.seed.android.model.PromptText;
import java.util.Iterator;
import java.util.List;

public class DeeplinkUtil
{
  private static String NETWORK_APP_AMAZON_PACKAGE;
  private static String NETWORK_APP_MARKET_PACKAGE = "com.cw.fullepisodes.android";
  private static String NETWORK_APP_SCHEME = "cwtv://";
  private static String SEED_APP_AMAZON_PACKAGE;
  private static String SEED_APP_MARKET_PACKAGE;
  private static String SEED_APP_SCHEME = "cwseed://";
  
  static
  {
    NETWORK_APP_AMAZON_PACKAGE = "com.cw.fullepisodes.android.amazon";
    SEED_APP_MARKET_PACKAGE = "com.cw.seed.android";
    SEED_APP_AMAZON_PACKAGE = "com.cw.seed.android.amazon";
  }
  
  public DeeplinkUtil() {}
  
  public static boolean isCWNetworkAppInstalled(Context paramContext)
  {
    if (Common.isAmazonApp(paramContext)) {}
    for (String str = NETWORK_APP_AMAZON_PACKAGE;; str = NETWORK_APP_MARKET_PACKAGE) {
      return isPackageInstalled(paramContext, str);
    }
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean isSeedAppInstalled(Context paramContext)
  {
    if (Common.isAmazonApp(paramContext)) {}
    for (String str = SEED_APP_AMAZON_PACKAGE;; str = SEED_APP_MARKET_PACKAGE) {
      return isPackageInstalled(paramContext, str);
    }
  }
  
  public static void openAmazonStoreForPackage(Context paramContext, String paramString)
  {
    openStoreURL(paramContext, "amzn://apps/android?p=" + paramString);
  }
  
  public static void openMarketForPackage(Context paramContext, String paramString)
  {
    openStoreURL(paramContext, "market://details?id=" + paramString);
  }
  
  public static void openNetworkApp(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(NETWORK_APP_SCHEME)));
  }
  
  public static void openNetworkAppStorePage(Context paramContext)
  {
    if (Common.isAmazonApp(paramContext))
    {
      openAmazonStoreForPackage(paramContext, NETWORK_APP_AMAZON_PACKAGE);
      return;
    }
    openMarketForPackage(paramContext, NETWORK_APP_MARKET_PACKAGE);
  }
  
  public static void openSeedApp(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(SEED_APP_SCHEME)));
  }
  
  public static void openSeedAppStorePage(Context paramContext)
  {
    if (Common.isAmazonApp(paramContext))
    {
      openAmazonStoreForPackage(paramContext, SEED_APP_AMAZON_PACKAGE);
      return;
    }
    openMarketForPackage(paramContext, SEED_APP_MARKET_PACKAGE);
  }
  
  public static void openStoreURL(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Log.e("DeepLink", "Store not found.. Defaulting to Webpage", paramString);
      openStoreURL(paramContext, Common.getAppShareLink(paramContext));
    }
  }
  
  public static void promptToDownloadNetworkApp(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(2131165224);
    PromptText localPromptText = CwApp.getInstance().getCwConfigInstance().getInstallPrompt();
    if ((localPromptText != null) && (localPromptText.getTitle() != null)) {
      localBuilder.setMessage(localPromptText.getTitle());
    }
    for (;;)
    {
      localBuilder.setPositiveButton(2131165426, new DeeplinkUtil.1(paramContext));
      localBuilder.setNegativeButton(2131165425, new DeeplinkUtil.2());
      localBuilder.setCancelable(true);
      localBuilder.create().show();
      return;
      localBuilder.setMessage(2131165427);
    }
  }
  
  public static void promptToDownloadSeedApp(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(2131165224);
    PromptText localPromptText = CwApp.getInstance().getCwConfigInstance().getInstallPrompt();
    if ((localPromptText != null) && (localPromptText.getTitle() != null)) {
      localBuilder.setMessage(localPromptText.getTitle());
    }
    for (;;)
    {
      localBuilder.setPositiveButton(2131165426, new DeeplinkUtil.3(paramContext));
      localBuilder.setNegativeButton(2131165425, new DeeplinkUtil.4());
      localBuilder.setCancelable(true);
      localBuilder.create().show();
      return;
      localBuilder.setMessage(2131165427);
    }
  }
}
