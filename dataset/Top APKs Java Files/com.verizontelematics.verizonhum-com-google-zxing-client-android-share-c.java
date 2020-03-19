package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import com.google.zxing.client.android.ac;
import com.google.zxing.client.android.ad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class c
  extends AsyncTask<Object, Object, List<a>>
{
  private static final String[] TA = { "com.google.android.apps." };
  private static final String[] TB = { "com.android.", "android", "com.google.android.", "com.htc" };
  private final ListActivity TC;
  
  c(ListActivity paramListActivity)
  {
    this.TC = paramListActivity;
  }
  
  private static boolean cT(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    String[] arrayOfString = TA;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramString.startsWith(arrayOfString[i])) {
        return false;
      }
      i += 1;
    }
    arrayOfString = TB;
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
  
  protected List<a> a(Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList();
    PackageManager localPackageManager = this.TC.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageItemInfo)localIterator.next();
      String str = ((PackageItemInfo)localObject).packageName;
      if (!cT(str))
      {
        CharSequence localCharSequence = ((PackageItemInfo)localObject).loadLabel(localPackageManager);
        localObject = ((PackageItemInfo)localObject).loadIcon(localPackageManager);
        if (localCharSequence != null) {
          paramVarArgs.add(new a(str, localCharSequence.toString(), (Drawable)localObject));
        }
      }
    }
    Collections.sort(paramVarArgs);
    return paramVarArgs;
  }
  
  protected void n(List<a> paramList)
  {
    paramList = new d(this, this.TC, ad.app_picker_list_item, ac.app_picker_list_item_label, paramList, paramList);
    this.TC.setListAdapter(paramList);
  }
}
