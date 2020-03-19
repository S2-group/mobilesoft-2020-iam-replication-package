package com.d.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class a
{
  public static String a = "AppLogic";
  private Context b;
  
  public a(Context paramContext)
  {
    this.b = paramContext;
  }
  
  public b a(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {
      return null;
    }
    b localB = new b();
    try
    {
      PackageInfo localPackageInfo = this.b.getPackageManager().getPackageInfo(paramApplicationInfo.packageName, 0);
      localB.e = localPackageInfo.versionName;
      localB.g = localPackageInfo.versionCode;
      localB.b = new File(paramApplicationInfo.publicSourceDir).length();
      localB.a = new Date(new File(paramApplicationInfo.publicSourceDir).lastModified()).getTime();
      localB.c = paramApplicationInfo.loadLabel(this.b.getPackageManager()).toString();
      localB.d = paramApplicationInfo.packageName;
      localB.f = paramApplicationInfo.sourceDir;
      if ((paramApplicationInfo.flags & 0x1) > 0) {
        localB.h = 1;
      } else {
        localB.h = 2;
      }
      if ((paramApplicationInfo.flags & 0x40000) != 0)
      {
        localB.i = 1;
        return localB;
      }
      localB.i = 0;
      return localB;
    }
    catch (Exception paramApplicationInfo)
    {
      paramApplicationInfo.printStackTrace();
    }
    return null;
  }
  
  public b a(String paramString)
  {
    try
    {
      paramString = a(this.b.getPackageManager().getApplicationInfo(paramString, 0));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public List<b> a()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = this.b.getPackageManager().getInstalledApplications(128);
    int i = 0;
    while (i < localList.size())
    {
      b localB = a((ApplicationInfo)localList.get(i));
      if (localB != null) {
        localArrayList.add(localB);
      }
      i += 1;
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int a(b paramAnonymousB1, b paramAnonymousB2)
      {
        return paramAnonymousB1.c.compareToIgnoreCase(paramAnonymousB2.c);
      }
    });
    return localArrayList;
  }
}
