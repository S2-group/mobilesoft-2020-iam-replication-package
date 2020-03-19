package tw.mobileapp.qrcode.barcode.ultra;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class LoadPackages2AsyncTask
  extends AsyncTask<Object, Object, List<AppInfo>>
{
  private static final String[] PKG_PREFIX_BLACKLIST = { "com.android.", "android", "com.google.android.", "com.htc" };
  private static final String[] PKG_PREFIX_WHITELIST = { "com.google.android.apps." };
  private final ListActivity activity;
  
  LoadPackages2AsyncTask(ListActivity paramListActivity)
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
    while (i < j)
    {
      if (paramString.startsWith(arrayOfString[i])) {
        return false;
      }
      i += 1;
    }
    arrayOfString = PKG_PREFIX_BLACKLIST;
    j = arrayOfString.length;
    i = 0;
    while (i < j)
    {
      if (paramString.startsWith(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  protected List<AppInfo> doInBackground(Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList();
    PackageManager localPackageManager = this.activity.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
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
    Collections.sort(paramVarArgs);
    return paramVarArgs;
  }
  
  protected void onPostExecute(final List<AppInfo> paramList)
  {
    paramList = new ArrayAdapter(this.activity, 2131296256, 2131165198, paramList)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        paramAnonymousView = super.getView(paramAnonymousInt, paramAnonymousView, paramAnonymousViewGroup);
        paramAnonymousViewGroup = ((AppInfo)paramList.get(paramAnonymousInt)).getIcon();
        if (paramAnonymousViewGroup != null) {
          ((ImageView)paramAnonymousView.findViewById(2131165197)).setImageDrawable(paramAnonymousViewGroup);
        }
        return paramAnonymousView;
      }
    };
    this.activity.setListAdapter(paramList);
  }
}
