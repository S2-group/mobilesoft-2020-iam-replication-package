package com.pcvirt.appchooser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.byteexperts.SelfAdView;
import com.byteexperts.appsupport.helper.AH;
import com.byteexperts.helpers.Helper;
import com.pcvirt.analytics.A;
import com.pcvirt.debug.D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AppChooser
{
  public static final int INSTALL_EXTENSION = 1002;
  public static final int LIST_EXTENTIONS = 1003;
  public static final int OPEN_WITH = 1001;
  public static Activity activity;
  public static Thumb_BaseAdapter extensions_adapter = null;
  public static ListView extensions_apps;
  public static AlertDialog extensions_dialog;
  public static List<Thumb> extensions_items;
  public static int forResultCode = -1;
  public static App mInstallExtensionApp;
  public static Intent mOpenWithIntent;
  public static List<Thumb> openwithItems = null;
  public static Thumb_BaseAdapter openwith_adapter = null;
  public static ListView openwith_apps;
  public static AlertDialog openwith_dialog;
  
  static
  {
    mOpenWithIntent = null;
    mInstallExtensionApp = null;
    extensions_items = null;
  }
  
  public AppChooser() {}
  
  public static List<App> getCompatibleApps(@NonNull List<App> paramList, Intent paramIntent)
  {
    ArrayList localArrayList = new ArrayList();
    String str = paramIntent.getAction();
    D.w("action=" + str);
    paramIntent = paramIntent.getType();
    D.w("mimeType=" + paramIntent);
    paramList = paramList.iterator();
    label193:
    while (paramList.hasNext())
    {
      App localApp = (App)paramList.next();
      if (localApp.intents != null)
      {
        IFilter[] arrayOfIFilter = localApp.intents;
        int j = arrayOfIFilter.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label193;
          }
          IFilter localIFilter = arrayOfIFilter[i];
          if ((localIFilter.action.equals(str)) && ((paramIntent == null) || (paramIntent.equals("*.*")) || (localIFilter.mimeType.equals("*/*")) || (localIFilter.mimeType.equals(paramIntent))))
          {
            localArrayList.add(localApp);
            break;
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static PackageInfo getExtensionPackage(List<PackageInfo> paramList, App paramApp)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList.next();
      if (paramApp.packageName.equals(localPackageInfo.packageName)) {
        return localPackageInfo;
      }
    }
    return null;
  }
  
  public static AlertDialog getExtentionsListDialog(Activity paramActivity)
  {
    activity = paramActivity;
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    View localView = LayoutInflater.from(paramActivity).inflate(R.layout.open_with, null);
    extensions_apps = (ListView)localView.findViewById(R.id.openwith_apps);
    extensions_items = new ArrayList();
    extensions_adapter = new Thumb_BaseAdapter(paramActivity, extensions_items);
    extensions_apps.setAdapter(extensions_adapter);
    extensions_apps.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        D.i("onItemClick()");
        AppChooser.extensions_dialog.cancel();
        paramAnonymousAdapterView = (Thumb)AppChooser.extensions_apps.getItemAtPosition(paramAnonymousInt);
        if ((paramAnonymousAdapterView.tag instanceof App))
        {
          paramAnonymousAdapterView = (App)paramAnonymousAdapterView.tag;
          AppChooser.openApp(AppChooser.activity, paramAnonymousAdapterView);
        }
        AppChooser.mOpenWithIntent = null;
      }
    });
    localBuilder.setTitle(R.string.dialog_extensions_title);
    localBuilder.setView(localView);
    localBuilder.setPositiveButton(R.string.button_text_close, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    extensions_dialog = localBuilder.create();
    return extensions_dialog;
  }
  
  public static AlertDialog getInstallExtentionDialog(Activity paramActivity)
  {
    D.i("");
    if (paramActivity == null)
    {
      D.i("");
      msg("Err: activity=" + paramActivity);
      return null;
    }
    activity = paramActivity;
    if (mInstallExtensionApp == null)
    {
      D.i("");
      msg("Err: mInstallExtensionApp=" + mInstallExtensionApp);
      return null;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    LinearLayout localLinearLayout = new LinearLayout(paramActivity);
    localLinearLayout.setBackgroundColor(AH.getCurrentThemeAttrColor(paramActivity, R.attr.dialogBackgroundColor));
    TextView localTextView = new TextView(paramActivity);
    localTextView.setText(R.string.extension_not_installed);
    int i = AH.px(paramActivity, 18.0F);
    localTextView.setPadding(i, i, i, i);
    localLinearLayout.addView(localTextView);
    localBuilder.setTitle(R.string.dialog_install_ext_title);
    localBuilder.setView(localLinearLayout);
    localBuilder.setPositiveButton(R.string.dialog_install_ext_button, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        D.i("");
        paramAnonymousDialogInterface.cancel();
        AppChooser.openMarket(this.val$activity, AppChooser.mInstallExtensionApp);
        AppChooser.mInstallExtensionApp = null;
      }
    });
    return localBuilder.create();
  }
  
  public static AlertDialog getOpenWithDialog(Activity paramActivity)
  {
    D.i("");
    activity = paramActivity;
    if (mOpenWithIntent == null)
    {
      D.e("Err: mOpenWithIntent=" + mOpenWithIntent);
      return null;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    View localView = LayoutInflater.from(paramActivity).inflate(R.layout.open_with, null);
    openwith_apps = (ListView)localView.findViewById(R.id.openwith_apps);
    openwith_adapter = new Thumb_BaseAdapter(paramActivity, openwithItems);
    openwith_apps.setAdapter(openwith_adapter);
    openwith_apps.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        D.i("()");
        AppChooser.openwith_dialog.cancel();
        paramAnonymousAdapterView = (Thumb)AppChooser.openwith_apps.getItemAtPosition(paramAnonymousInt);
        if ((paramAnonymousAdapterView.tag instanceof ResolveInfo))
        {
          paramAnonymousAdapterView = (ResolveInfo)paramAnonymousAdapterView.tag;
          AppChooser.openIntentWithApp(AppChooser.activity, AppChooser.mOpenWithIntent, paramAnonymousAdapterView.activityInfo.packageName, paramAnonymousAdapterView.activityInfo.name);
        }
        for (;;)
        {
          AppChooser.mOpenWithIntent = null;
          return;
          if (!(paramAnonymousAdapterView.tag instanceof App)) {
            break;
          }
          paramAnonymousAdapterView = (App)paramAnonymousAdapterView.tag;
          AppChooser.showInstallExtension(AppChooser.activity, paramAnonymousAdapterView);
        }
        throw new IllegalArgumentException("unknown instance of item.tag: " + paramAnonymousAdapterView.tag.getClass());
      }
    });
    localBuilder.setTitle(R.string.dialog_open_with_title);
    localBuilder.setView(localView);
    D.w("#b#");
    openwith_dialog = localBuilder.create();
    D.w("#a#");
    return openwith_dialog;
  }
  
  public static List<ResolveInfo> getOpenWithList(PackageManager paramPackageManager, Intent paramIntent)
  {
    return paramPackageManager.queryIntentActivities(paramIntent, 65600);
  }
  
  private static boolean hasApp(List<ResolveInfo> paramList, App paramApp)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramList.next();
      if (paramApp.packageName.equals(localResolveInfo.activityInfo.packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean hasDefault(Intent paramIntent)
  {
    boolean bool2 = false;
    PackageManager localPackageManager = activity.getPackageManager();
    List localList = localPackageManager.queryIntentActivities(paramIntent, 0);
    Object localObject = new IntentFilter(paramIntent.getAction());
    if (paramIntent.getCategories() != null)
    {
      paramIntent = paramIntent.getCategories().iterator();
      while (paramIntent.hasNext()) {
        ((IntentFilter)localObject).addCategory((String)paramIntent.next());
      }
    }
    paramIntent = new ArrayList();
    paramIntent.add(localObject);
    localObject = new ArrayList();
    localPackageManager.getPreferredActivities(paramIntent, (List)localObject, null);
    paramIntent = ((List)localObject).iterator();
    for (;;)
    {
      boolean bool1 = bool2;
      Iterator localIterator;
      if (paramIntent.hasNext())
      {
        localObject = (ComponentName)paramIntent.next();
        localIterator = localList.iterator();
      }
      while (localIterator.hasNext()) {
        if (((ResolveInfo)localIterator.next()).activityInfo.applicationInfo.packageName.equals(((ComponentName)localObject).getPackageName())) {
          try
          {
            PackageInfo localPackageInfo = localPackageManager.getPackageInfo(((ComponentName)localObject).getPackageName(), 0);
            D.e("default found: " + localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo));
            bool1 = true;
            return bool1;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
      }
    }
  }
  
  public static boolean isExtensionInstalled(App paramApp)
  {
    boolean bool = false;
    if (getExtensionPackage(activity.getPackageManager().getInstalledPackages(0), paramApp) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static void msg(String paramString)
  {
    D.e(">>> message=" + paramString);
    AH.msg(activity, paramString);
  }
  
  public static void openApp(Activity paramActivity, App paramApp)
  {
    openApp(paramActivity, paramApp, true);
  }
  
  public static void openApp(Activity paramActivity, App paramApp, boolean paramBoolean)
  {
    activity = paramActivity;
    if (SelfAdView.isAppInstalled(paramActivity, paramApp.packageName))
    {
      if (paramApp.className != null)
      {
        paramActivity = new Intent();
        paramActivity.addFlags(268435456);
        paramActivity.setAction("android.intent.action.VIEW");
        paramActivity.setClassName(paramApp.packageName, paramApp.className);
        try
        {
          runStartActivity(paramActivity);
          return;
        }
        catch (Exception paramActivity)
        {
          msg("Error opening this app");
          return;
        }
      }
      Helper.openApp(paramActivity, paramApp.packageName);
      return;
    }
    if (paramBoolean)
    {
      showInstallExtension(paramActivity, paramApp);
      return;
    }
    openMarket(paramActivity, paramApp);
  }
  
  public static void openExtension(Activity paramActivity, App paramApp)
  {
    activity = paramActivity;
    if (isExtensionInstalled(paramApp))
    {
      paramActivity = new Intent();
      paramActivity.addFlags(268435456);
      paramActivity.setAction("android.intent.action.VIEW");
      paramActivity.setClassName(paramApp.packageName, paramApp.className);
      try
      {
        runStartActivity(paramActivity);
        return;
      }
      catch (Exception paramActivity)
      {
        msg("Error opening this app");
        return;
      }
    }
    showInstallExtension(activity, paramApp);
  }
  
  private static void openIntent(@NonNull Activity paramActivity, List<App> paramList, Intent paramIntent)
  {
    D.i("");
    activity = paramActivity;
    mOpenWithIntent = paramIntent;
    refreshOpenWith(paramList, paramActivity.getPackageManager());
    D.w("AppChooser.openwithItems.size()=" + openwithItems.size());
    if (openwithItems.size() > 1)
    {
      showOpenWith(paramActivity, paramIntent);
      return;
    }
    if (openwithItems.size() == 1)
    {
      paramList = (Thumb)openwithItems.get(0);
      if ((paramList.tag instanceof ResolveInfo))
      {
        paramList = (ResolveInfo)paramList.tag;
        openIntentWithApp(paramActivity, mOpenWithIntent, paramList.activityInfo.packageName, paramList.activityInfo.name);
      }
      for (;;)
      {
        mOpenWithIntent = null;
        return;
        if (!(paramList.tag instanceof App)) {
          break;
        }
        showInstallExtension(paramActivity, (App)paramList.tag);
      }
      throw new IllegalArgumentException("unknown instance of item.tag: " + paramList.tag.getClass());
    }
    msg("There is no app installed to handle what you intend to do");
  }
  
  public static void openIntentWithApp(Context paramContext, Intent paramIntent, String paramString1, String paramString2)
  {
    D.i("openIntentWithApp()");
    if (paramIntent == null)
    {
      msg("Error: intent is null");
      return;
    }
    paramIntent.setClassName(paramString1, paramString2);
    try
    {
      runStartActivity(paramIntent);
      return;
    }
    catch (Exception paramContext)
    {
      msg("Error opening this app");
    }
  }
  
  public static void openMarket(Context paramContext, App paramApp)
  {
    paramContext = new Intent("android.intent.action.VIEW", Uri.parse(paramContext.getResources().getString(R.string.market_app_marketurl, new Object[] { paramApp.packageName })));
    paramContext.addFlags(1074266112);
    runStartActivity(paramContext);
  }
  
  public static void refreshExtensions(Activity paramActivity, List<App> paramList)
  {
    activity = paramActivity;
    D.w("mOpenWithIntent=" + mOpenWithIntent);
    extensions_items.clear();
    List localList = paramActivity.getPackageManager().getInstalledPackages(0);
    paramList = paramList.iterator();
    if (paramList.hasNext())
    {
      App localApp = (App)paramList.next();
      Thumb localThumb = new Thumb();
      localThumb.name = localApp.label;
      D.w("App: thumb.name=" + localThumb.name);
      localThumb.iconResource = localApp.iconResource;
      if (localApp.iconBitmap != null) {
        localThumb.icon = new BitmapDrawable(activity.getResources(), localApp.iconBitmap);
      }
      if (getExtensionPackage(localList, localApp) != null) {}
      for (paramActivity = "Installed";; paramActivity = "")
      {
        localThumb.comment = paramActivity;
        localThumb.tag = localApp;
        extensions_items.add(localThumb);
        break;
      }
    }
    Collections.sort(extensions_items, new ThumbComparator());
    extensions_adapter.notifyDataSetChanged();
  }
  
  public static void refreshOpenWith(@NonNull List<App> paramList, PackageManager paramPackageManager)
  {
    D.i("() mOpenWithIntent=" + mOpenWithIntent);
    if (openwithItems == null) {
      openwithItems = new ArrayList();
    }
    openwithItems.clear();
    List localList = getOpenWithList(paramPackageManager, mOpenWithIntent);
    if (localList == null) {
      A.sendDebugEvent("AppChooser.refreshOpenWith() pkgAppsList null", "mOpenWithIntent=" + mOpenWithIntent);
    }
    D.w("pkgAppsList.size()=" + localList.size());
    Object localObject = localList.iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      Thumb localThumb = new Thumb();
      localThumb.name = localResolveInfo.loadLabel(paramPackageManager).toString();
      D.w("ResolveInfo: thumb.name=" + localThumb.name);
      localThumb.icon = localResolveInfo.loadIcon(paramPackageManager);
      localThumb.tag = localResolveInfo;
      openwithItems.add(localThumb);
    }
    paramList = getCompatibleApps(paramList, mOpenWithIntent);
    D.w("notInstalled.size()=" + paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      paramPackageManager = (App)paramList.next();
      if (!hasApp(localList, paramPackageManager))
      {
        localObject = new Thumb();
        ((Thumb)localObject).name = paramPackageManager.label;
        D.w("App: thumb.name=" + ((Thumb)localObject).name);
        ((Thumb)localObject).iconResource = paramPackageManager.iconResource;
        if (paramPackageManager.iconBitmap != null) {
          ((Thumb)localObject).icon = new BitmapDrawable(activity.getResources(), paramPackageManager.iconBitmap);
        }
        ((Thumb)localObject).tag = paramPackageManager;
        openwithItems.add(localObject);
      }
    }
    Collections.sort(openwithItems, new ThumbComparator());
    if (openwith_adapter != null) {
      openwith_adapter.notifyDataSetChanged();
    }
  }
  
  private static boolean runStartActivity(Intent paramIntent)
  {
    try
    {
      if (forResultCode > -1)
      {
        D.e("#startActivityForResult#");
        activity.startActivityForResult(paramIntent, forResultCode);
        return false;
      }
      D.e("#startActivity#");
      activity.startActivity(paramIntent);
      return true;
    }
    catch (ActivityNotFoundException paramIntent)
    {
      Toast.makeText(activity, "Activity not found", 0).show();
      return false;
    }
    catch (Throwable paramIntent)
    {
      Toast.makeText(activity, "ERROR: " + paramIntent.getMessage(), 0).show();
    }
    return false;
  }
  
  public static void showExtensionsList(Activity paramActivity, List<App> paramList)
  {
    D.i("()");
    activity = paramActivity;
    getExtentionsListDialog(paramActivity).show();
    refreshExtensions(paramActivity, paramList);
  }
  
  public static void showInstallExtension(Activity paramActivity, App paramApp)
  {
    D.i("(" + paramApp + ")");
    activity = paramActivity;
    mInstallExtensionApp = paramApp;
    getInstallExtentionDialog(paramActivity).show();
  }
  
  public static void showOpenWith(Activity paramActivity, Intent paramIntent)
  {
    D.i("(" + paramIntent + ")");
    if (paramIntent == null)
    {
      msg("Err: showOpenWith() called with null intent!");
      return;
    }
    activity = paramActivity;
    mOpenWithIntent = paramIntent;
    getOpenWithDialog(paramActivity).show();
  }
  
  public static void startActivity(Activity paramActivity, @NonNull List<App> paramList, Intent paramIntent)
  {
    startActivity(paramActivity, paramList, paramIntent, -1, false, false);
  }
  
  public static void startActivity(Activity paramActivity, @NonNull List<App> paramList, Intent paramIntent, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramList == null) {
      throw new Error("invalid apps=" + paramList);
    }
    activity = paramActivity;
    mOpenWithIntent = paramIntent;
    forResultCode = paramInt;
    boolean bool = hasDefault(paramIntent);
    if (paramBoolean2)
    {
      if (bool)
      {
        paramIntent.setDataAndType(paramIntent.getData(), "*/*");
        runStartActivity(paramIntent);
      }
      do
      {
        return;
        if (getOpenWithList(paramActivity.getPackageManager(), paramIntent).size() > 0)
        {
          runStartActivity(paramIntent);
          return;
        }
      } while (paramList.size() <= 0);
      openIntent(activity, paramList, paramIntent);
      return;
    }
    if ((bool) && (!paramBoolean1))
    {
      runStartActivity(paramIntent);
      return;
    }
    openIntent(activity, paramList, paramIntent);
  }
}
