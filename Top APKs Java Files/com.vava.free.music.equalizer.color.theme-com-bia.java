package com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;
import java.util.List;

public final class bia
{
  public static boolean a(Context paramContext, String paramString)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        localArrayList.add(((PackageInfo)localList.get(i)).packageName);
        i += 1;
      }
    }
    if (localArrayList.contains(paramString)) {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    }
    try
    {
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
    return false;
  }
}
