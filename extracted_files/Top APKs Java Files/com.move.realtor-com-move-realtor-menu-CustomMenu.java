package com.move.realtor.menu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.move.realtor.app.ServerConfig;
import com.move.realtor.service.ServerConfigService.Data;
import com.move.realtor.service.ServerConfigService.Data.Link;
import com.move.realtor.service.ServerConfigService.Data.Link.LinkData;
import com.move.realtor.service.ServerConfigService.Data.Link.SlidingMenu;
import com.move.realtor.util.RealtorLog;
import com.move.realtor.util.Strings;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

class CustomMenu
  extends MenuSectionHandler
{
  private static final String GOOGLE_PLAY_STORE_PACKAGE_NAME_NEW = "com.android.vending";
  private static final String GOOGLE_PLAY_STORE_PACKAGE_NAME_OLD = "com.android.market";
  static final String LOG_TAG = CustomMenu.class.getSimpleName();
  private static final String RENTAL_APP_PACKAGE_NAME = "com.move.rentals";
  boolean hidden;
  int[] menuItems = { 2131100153, 2131100154, 2131100155, 2131100156, 2131100157 };
  
  CustomMenu(MenuDrawerController paramMenuDrawerController)
  {
    super(paramMenuDrawerController, 2131100151);
  }
  
  static Intent createGooglePlayLaunchIntent(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    paramString1.setPackage(paramString2);
    localArrayList.add(paramString1);
    paramString1 = Intent.createChooser((Intent)localArrayList.remove(0), "Open via");
    paramString1.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localArrayList.toArray(new Parcelable[0]));
    return paramString1;
  }
  
  static String getAppPackageUri(String paramString)
  {
    try
    {
      Object localObject = URLEncodedUtils.parse(new URI(paramString), "UTF-8").iterator();
      while (((Iterator)localObject).hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)((Iterator)localObject).next();
        if (localNameValuePair.getName().compareToIgnoreCase("id") == 0)
        {
          localObject = localNameValuePair.getValue();
          return localObject;
        }
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      RealtorLog.e(LOG_TAG, "error in parsing " + paramString, localURISyntaxException);
    }
    return null;
  }
  
  static String getGooglePlayStorePackageName(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals("com.android.vending")) {
        return "com.android.vending";
      }
      if (localPackageInfo.packageName.equals("com.android.market")) {
        return "com.android.market";
      }
    }
    return null;
  }
  
  static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  static boolean isGooglePlayStoreUri(String paramString)
  {
    return (paramString.contains("play.google.com/store/apps/details")) || (paramString.startsWith("market://details?"));
  }
  
  public void onCreate(Set<MenuItemHandler> paramSet)
  {
    this.hidden = true;
    final Context localContext = getSectionView().getContext();
    Object localObject1 = ServerConfig.getInstance().getConfigData();
    if ((localObject1 != null) && (((ServerConfigService.Data)localObject1).link != null) && (((ServerConfigService.Data)localObject1).link.sliding_menu != null) && (((ServerConfigService.Data)localObject1).link.sliding_menu.links != null) && (((ServerConfigService.Data)localObject1).link.sliding_menu.links.size() > 0)) {
      this.hidden = false;
    }
    if (this.hidden) {
      return;
    }
    if (Strings.isNonEmpty(((ServerConfigService.Data)localObject1).link.sliding_menu.title)) {
      ((TextView)getSectionView().findViewById(2131100152)).setText(((ServerConfigService.Data)localObject1).link.sliding_menu.title);
    }
    int i = 0;
    Iterator localIterator = ((ServerConfigService.Data)localObject1).link.sliding_menu.links.iterator();
    label156:
    Object localObject2;
    label218:
    final boolean bool1;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localObject2 = (ServerConfigService.Data.Link.LinkData)localIterator.next();
        final String str = ((ServerConfigService.Data.Link.LinkData)localObject2).getUrl();
        if ((i < this.menuItems.length) && (str != null))
        {
          final boolean bool2 = isGooglePlayStoreUri(str);
          if (!bool2) {
            break label353;
          }
          localObject1 = getAppPackageUri(str);
          if (localObject1 == null) {
            break label359;
          }
          bool1 = isAppInstalled(localContext, (String)localObject1);
          label232:
          MenuItem localMenuItem = (MenuItem)getSectionView().findViewById(this.menuItems[i]);
          localMenuItem.setMenuItemTitle(((ServerConfigService.Data.Link.LinkData)localObject2).text);
          localMenuItem.setVisibility(0);
          localObject2 = new MenuItemHandler.BadgeHandler(this, this.menuItems[i])
          {
            public void onClick(View paramAnonymousView)
            {
              Object localObject = null;
              paramAnonymousView = localObject;
              if (bool2)
              {
                paramAnonymousView = localObject;
                if (this.val$appPackageUri != null)
                {
                  if (!bool1) {
                    break label76;
                  }
                  paramAnonymousView = localContext.getPackageManager().getLaunchIntentForPackage(this.val$appPackageUri);
                }
              }
              while (paramAnonymousView == null)
              {
                paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(str));
                CustomMenu.this.getController().startMenuActivity(paramAnonymousView, this);
                return;
                label76:
                String str = CustomMenu.getGooglePlayStorePackageName(localContext);
                paramAnonymousView = localObject;
                if (str != null) {
                  paramAnonymousView = CustomMenu.createGooglePlayLaunchIntent(str, str);
                }
              }
              CustomMenu.this.getController().startMenuActivity(paramAnonymousView, null);
            }
          };
          paramSet.add(localObject2);
          if ("com.move.rentals".equalsIgnoreCase((String)localObject1))
          {
            localObject1 = localContext.getResources();
            if (!bool1) {
              break label365;
            }
          }
        }
      }
    }
    label353:
    label359:
    label365:
    for (int j = 2131165992;; j = 2131165991)
    {
      ((MenuItemHandler.BadgeHandler)localObject2).setBadge(((Resources)localObject1).getString(j)).setBadgeBackground(2130837846);
      i += 1;
      break label156;
      break;
      localObject1 = null;
      break label218;
      bool1 = false;
      break label232;
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.hidden) {
      getSectionView().setVisibility(8);
    }
  }
}
