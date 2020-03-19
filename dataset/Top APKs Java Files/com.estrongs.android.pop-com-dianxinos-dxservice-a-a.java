package com.dianxinos.dxservice.a;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.System;
import com.dianxinos.dxservice.stat.j;
import com.dianxinos.library.dxbase.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static Context a;
  private static PackageManager b;
  private static ContentResolver c;
  
  public static j a(String paramString1, String paramString2)
  {
    return new j(paramString1, a, paramString2);
  }
  
  public static List<j> a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = b.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(a(((ApplicationInfo)localIterator.next()).packageName, paramString));
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext)
  {
    a = paramContext.getApplicationContext();
    b = a.getPackageManager();
    c = a.getContentResolver();
  }
  
  public static boolean a(j paramJ)
  {
    Object localObject = Settings.System.getString(c, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}");
    if (localObject == null) {}
    for (;;)
    {
      return false;
      localObject = ((String)localObject).split(",");
      paramJ = c.a(paramJ.a());
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        if (localObject[i].equals(paramJ)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static void b(j paramJ)
  {
    paramJ = c.a(paramJ.a());
    String str = Settings.System.getString(c, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}");
    if (str == null)
    {
      f.a(a).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", paramJ);
      return;
    }
    paramJ = str + "," + paramJ;
    f.a(a).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", paramJ);
  }
}
