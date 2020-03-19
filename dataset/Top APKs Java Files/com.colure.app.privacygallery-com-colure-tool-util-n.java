package com.colure.tool.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.colure.tool.a.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class n
{
  private static String b = "PackageUtil";
  @RootContext
  Context a;
  
  public n() {}
  
  private boolean a(String paramString, String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      String str = paramArrayOfString[i];
      if ((paramString != null) && (paramString.toLowerCase(Locale.ENGLISH).contains(str))) {
        return true;
      }
      i += 1;
    }
  }
  
  private boolean b(String paramString, String[] paramArrayOfString)
  {
    boolean bool2 = false;
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1;
      if (i >= j) {
        bool1 = true;
      }
      String str;
      do
      {
        return bool1;
        str = paramArrayOfString[i];
        if (str == null) {
          break;
        }
        bool1 = bool2;
      } while (str.equalsIgnoreCase(paramString));
      i += 1;
    }
  }
  
  public ArrayList<o> a(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    c.a(b, "searchApps");
    ArrayList localArrayList = new ArrayList();
    List localList = this.a.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i >= localList.size()) {
      return localArrayList;
    }
    PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
    if ((!a(localPackageInfo.packageName, paramArrayOfString1)) && (b(localPackageInfo.packageName, paramArrayOfString2))) {}
    for (;;)
    {
      i += 1;
      break;
      c.a(b, "Found app installed: " + localPackageInfo.packageName);
      o localO = new o();
      localO.a = localPackageInfo.applicationInfo.loadLabel(this.a.getPackageManager()).toString();
      localO.b = localPackageInfo.packageName;
      localO.c = localPackageInfo.versionName;
      localO.d = localPackageInfo.versionCode;
      localO.e = localPackageInfo.applicationInfo.loadIcon(this.a.getPackageManager());
      localArrayList.add(localO);
    }
  }
}
