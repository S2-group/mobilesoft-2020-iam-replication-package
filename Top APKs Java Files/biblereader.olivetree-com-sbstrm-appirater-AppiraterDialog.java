package com.sbstrm.appirater;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AppiraterDialog
  extends DialogFragment
  implements View.OnClickListener
{
  private static final String AMAZON_STORE_PACKAGE_NAME = "com.amazon.venezia";
  private static final Map<String, String> EAN_MAP = new HashMap() {};
  public static final String NOOK_MARKET_EXTRA_NAME = "product_details_ean";
  public static final String NOOK_MARKET_INTENT = "com.bn.sdk.shop.details";
  private static final String PLAY_STORE_PACKAGE_NAME = "com.android.vending";
  private static final String PREF_APP_VERSION_CODE = "versioncode";
  private static final String PREF_DATE_FIRST_LAUNCHED = "date_firstlaunch";
  private static final String PREF_DATE_REMINDER_PRESSED = "date_reminder_pressed";
  private static final String PREF_DONT_SHOW = "dontshow";
  private static final String PREF_LAUNCH_COUNT = "launch_count";
  private static final String PREF_RATE_CLICKED = "rateclicked";
  private static boolean shownThisLaunch = false;
  
  public AppiraterDialog() {}
  
  public static void appLaunched(FragmentActivity paramFragmentActivity)
  {
    if (shownThisLaunch) {}
    Context localContext;
    boolean bool;
    SharedPreferences localSharedPreferences;
    do
    {
      return;
      shownThisLaunch = true;
      localContext = paramFragmentActivity.getApplicationContext();
      bool = localContext.getResources().getBoolean(2131361792);
      localSharedPreferences = localContext.getSharedPreferences(localContext.getPackageName() + ".apprater", 0);
    } while ((!bool) && ((localSharedPreferences.getBoolean("dontshow", false)) || (localSharedPreferences.getBoolean("rateclicked", false))));
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (bool)
    {
      showRateDialog(paramFragmentActivity);
      return;
    }
    long l3 = localSharedPreferences.getLong("launch_count", 0L);
    long l4 = localSharedPreferences.getLong("date_firstlaunch", 0L);
    long l5 = localSharedPreferences.getLong("date_reminder_pressed", 0L);
    l2 = l3;
    try
    {
      int i = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionCode;
      l1 = l3;
      l2 = l3;
      if (localSharedPreferences.getInt("versioncode", 0) != i) {
        l1 = 0L;
      }
      l2 = l1;
      localEditor.putInt("versioncode", i);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        long l1 = l2;
      }
    }
    l2 = l1 + 1L;
    localEditor.putLong("launch_count", l2);
    l1 = l4;
    if (l4 == 0L)
    {
      l1 = System.currentTimeMillis();
      localEditor.putLong("date_firstlaunch", l1);
    }
    if (l2 >= localContext.getResources().getInteger(2131296257))
    {
      l2 = localContext.getResources().getInteger(2131296256);
      if (System.currentTimeMillis() >= l1 + l2)
      {
        if (l5 != 0L) {
          break label305;
        }
        showRateDialog(paramFragmentActivity);
      }
    }
    for (;;)
    {
      localEditor.commit();
      return;
      label305:
      l1 = localContext.getResources().getInteger(2131296258);
      if (System.currentTimeMillis() >= l1 * 24L * 60L * 60L * 1000L + l5) {
        showRateDialog(paramFragmentActivity);
      }
    }
  }
  
  private static String getAppName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      if (localApplicationInfo != null)
      {
        paramContext = localPackageManager.getApplicationLabel(localApplicationInfo);
        return (String)paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject = null;
        continue;
        paramContext = paramContext.getString(2131230745);
      }
    }
  }
  
  private static Intent getMarketIntent(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8192);
    if (paramContext.getPackageName().startsWith("nook"))
    {
      localObject = new String[4];
      localObject[0] = "nook.kjv.";
      localObject[1] = "nook.niv.";
      localObject[2] = "nook.nkjv.";
      localObject[3] = "nook.esv.";
      paramContext = null;
      int j = localObject.length;
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          paramContext = localObject[i];
          paramContext = (String)EAN_MAP.get(paramContext);
          if (paramContext == null) {}
        }
        else
        {
          localObject = paramContext;
          if (paramContext == null) {
            localObject = (String)EAN_MAP.get("nook.");
          }
          paramContext = new Intent();
          paramContext.setAction("com.bn.sdk.shop.details");
          paramContext.putExtra("product_details_ean", (String)localObject);
          return paramContext;
        }
        i += 1;
      }
    }
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals("com.android.vending")) {
        return new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramContext.getString(2131230741), new Object[] { paramContext.getPackageName() })));
      }
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equals("com.amazon.venezia")) {
        return new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramContext.getString(2131230742), new Object[] { paramContext.getPackageName() })));
      }
    }
    return null;
  }
  
  private static void showRateDialog(FragmentActivity paramFragmentActivity)
  {
    new AppiraterDialog().show(paramFragmentActivity.getSupportFragmentManager().beginTransaction(), "dialog");
  }
  
  public void onClick(View paramView)
  {
    Context localContext = paramView.getContext();
    SharedPreferences.Editor localEditor = localContext.getSharedPreferences(localContext.getPackageName() + ".apprater", 0).edit();
    switch (paramView.getId())
    {
    }
    for (;;)
    {
      dismiss();
      return;
      if (localEditor != null)
      {
        localEditor.putBoolean("rateclicked", true);
        localEditor.commit();
      }
      localContext.startActivity(getMarketIntent(localContext));
      continue;
      if (localEditor != null)
      {
        localEditor.putLong("date_reminder_pressed", System.currentTimeMillis());
        localEditor.commit();
        continue;
        if (localEditor != null)
        {
          localEditor.putBoolean("dontshow", true);
          localEditor.commit();
        }
      }
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = getActivity();
    String str = getAppName(paramBundle);
    View localView = LayoutInflater.from(paramBundle).inflate(2130903071, null);
    ((TextView)localView.findViewById(2131165317)).setText(String.format(paramBundle.getString(2131230909), new Object[] { str }));
    Button localButton = (Button)localView.findViewById(2131165318);
    localButton.setText(String.format(paramBundle.getString(2131230910), new Object[] { str }));
    localButton.setOnClickListener(this);
    localButton = (Button)localView.findViewById(2131165319);
    localButton.setText(paramBundle.getString(2131230911));
    localButton.setOnClickListener(this);
    localButton = (Button)localView.findViewById(2131165213);
    localButton.setText(paramBundle.getString(2131230912));
    localButton.setOnClickListener(this);
    return new AlertDialog.Builder(getActivity()).setTitle(String.format(paramBundle.getString(2131230908), new Object[] { str })).setView(localView).create();
  }
  
  static abstract interface NookPrefix
  {
    public static final String BIBLE_PLUS = "nook.";
    public static final String ESV = "nook.esv.";
    public static final String KJV_STRONGS = "nook.kjv.";
    public static final String NIV = "nook.niv.";
    public static final String NKJV = "nook.nkjv.";
  }
}
