package com.google.zxing.client.android.share;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class LoadPackagesAsyncTask
  extends AsyncTask<List<String[]>, Object, List<String[]>>
{
  private static final String[] PKG_PREFIX_BLACKLIST = { "com.android.", "android", "com.google.android.", "com.htc" };
  private static final String[] PKG_PREFIX_WHITELIST = { "com.google.android.apps." };
  private final AppPickerActivity activity;
  
  LoadPackagesAsyncTask(AppPickerActivity paramAppPickerActivity)
  {
    this.activity = paramAppPickerActivity;
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
    for (;;)
    {
      if (i >= j) {
        break label70;
      }
      if (paramString.startsWith(arrayOfString[i])) {
        break;
      }
      i += 1;
    }
    label70:
    return false;
  }
  
  protected List<String[]> doInBackground(List<String[]>... paramVarArgs)
  {
    paramVarArgs = paramVarArgs[0];
    PackageManager localPackageManager = this.activity.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      CharSequence localCharSequence = ((ApplicationInfo)localObject).loadLabel(localPackageManager);
      if (localCharSequence != null)
      {
        localObject = ((ApplicationInfo)localObject).packageName;
        if (!isHidden((String)localObject)) {
          paramVarArgs.add(new String[] { localCharSequence.toString(), localObject });
        }
      }
    }
    Collections.sort(paramVarArgs, new LoadPackagesAsyncTask.ByFirstStringComparator(null));
    return paramVarArgs;
  }
  
  protected void onPostExecute(List<String[]> paramList)
  {
    try
    {
      ArrayList localArrayList = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localArrayList.add(((String[])paramList.next())[0]);
      }
      paramList = new ArrayAdapter(this.activity, 17367043, localArrayList);
    }
    finally {}
    this.activity.setListAdapter(paramList);
  }
}
