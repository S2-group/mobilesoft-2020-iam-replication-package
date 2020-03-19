package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.layout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class LoadPackagesAsyncTask
  extends AsyncTask<Object, Object, List<AppInfo>>
{
  private static final String[] PKG_PREFIX_BLACKLIST = { "com.android.", "android", "com.google.android.", "com.htc" };
  private static final String[] PKG_PREFIX_WHITELIST = { "com.google.android.apps." };
  private final ListActivity activity;
  
  LoadPackagesAsyncTask(ListActivity paramListActivity)
  {
    this.activity = paramListActivity;
  }
  
  private static boolean isHidden(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    String[] arrayOfString = PKG_PREFIX_WHITELIST;
    int j = arrayOfString.length;
    int i = 0;
    label15:
    if (i >= j)
    {
      arrayOfString = PKG_PREFIX_BLACKLIST;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        return false;
        if (paramString.startsWith(arrayOfString[i])) {
          return false;
        }
        i += 1;
        break label15;
      }
      if (paramString.startsWith(arrayOfString[i])) {
        break;
      }
      i += 1;
    }
  }
  
  protected List<AppInfo> doInBackground(Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList();
    PackageManager localPackageManager = this.activity.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        Collections.sort(paramVarArgs);
        return paramVarArgs;
      }
      Object localObject = (PackageItemInfo)localIterator.next();
      String str = ((PackageItemInfo)localObject).packageName;
      if (!isHidden(str))
      {
        CharSequence localCharSequence = ((PackageItemInfo)localObject).loadLabel(localPackageManager);
        localObject = ((PackageItemInfo)localObject).loadIcon(localPackageManager);
        if (localCharSequence != null) {
          paramVarArgs.add(new AppInfo(str, localCharSequence.toString(), (Drawable)localObject));
        }
      }
    }
  }
  
  protected void onPostExecute(final List<AppInfo> paramList)
  {
    paramList = new ArrayAdapter(this.activity, R.layout.app_picker_list_item, R.id.app_picker_list_item_label, paramList)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        paramAnonymousView = super.getView(paramAnonymousInt, paramAnonymousView, paramAnonymousViewGroup);
        paramAnonymousViewGroup = ((AppInfo)paramList.get(paramAnonymousInt)).getIcon();
        if (paramAnonymousViewGroup != null) {
          ((ImageView)paramAnonymousView.findViewById(R.id.app_picker_list_item_icon)).setImageDrawable(paramAnonymousViewGroup);
        }
        return paramAnonymousView;
      }
    };
    this.activity.setListAdapter(paramList);
  }
}
