package com.igexin.getuiext.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import java.io.File;
import java.util.List;

public class e
{
  public static final String a = Environment.getExternalStorageDirectory().getAbsolutePath() + "/libs/tmp/";
  
  public static String a(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int j = paramContext.size();
    int i = 0;
    while (i < j)
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (paramString.equals(localApplicationInfo.packageName)) {
        return localApplicationInfo.sourceDir;
      }
      i += 1;
    }
    return null;
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (new File(paramString1).exists())
    {
      paramString3 = a(paramContext, paramString3);
      if ((paramString3 != null) && (new BsPatchUtil().a(paramString3, paramString2, paramString1) == 0)) {
        b(paramContext, paramString2);
      }
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
}
