package com.t11.skyview.view.store;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.t11.skyview.library.R.id;
import com.t11.skyview.library.R.layout;
import com.t11.skyview.library.R.string;
import java.util.Iterator;
import java.util.List;

public class AppStoreSelector
{
  private static final AppStoreSelector INSTANCE = new AppStoreSelector();
  
  private AppStoreSelector() {}
  
  public static AppStoreSelector getInstance()
  {
    return INSTANCE;
  }
  
  public AlertDialog.Builder buildStoreChooserDialogBuilder(Activity paramActivity)
  {
    View localView = paramActivity.getLayoutInflater().inflate(R.layout.layout_toast_accessories_error, (ViewGroup)paramActivity.findViewById(R.id.custom_toast_layout_accessories_error_root));
    ((TextView)localView.findViewById(R.id.toastAccessoriesErrorTitleTextView)).setText(paramActivity.getResources().getString(R.string.store_choose_store_title));
    ((TextView)localView.findViewById(R.id.toastAccessoriesErrorTextView)).setText(paramActivity.getResources().getString(R.string.store_choose_store));
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setCancelable(true);
    paramActivity.setView(localView);
    return paramActivity;
  }
  
  public boolean isAmazonStoreInstalled(Activity paramActivity)
  {
    paramActivity = paramActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    do
    {
      if (!paramActivity.hasNext()) {
        return false;
      }
    } while (!((PackageInfo)paramActivity.next()).packageName.startsWith("com.amazon"));
    return true;
  }
  
  public boolean isGooglePlayStoreInstalled(Activity paramActivity)
  {
    paramActivity = paramActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramActivity.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramActivity.next();
    } while ((!localPackageInfo.packageName.equals("com.google.market")) && (!localPackageInfo.packageName.equals("com.android.vending")));
    return true;
  }
}
