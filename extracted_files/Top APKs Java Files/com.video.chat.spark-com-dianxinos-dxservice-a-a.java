package com.dianxinos.dxservice.a;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.dianxinos.dxservice.stat.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static Context a;
  private static PackageManager b;
  private static ContentResolver c;
  
  public static d a(String paramString1, String paramString2)
  {
    return new d(paramString1, a, paramString2);
  }
  
  public static List<d> a(String paramString)
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = b.getInstalledApplications(0).iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(a(((ApplicationInfo)localIterator.next()).packageName, paramString));
      }
      return localArrayList;
    }
    catch (Exception paramString)
    {
      if (c.e) {
        Log.e("stat.AppInfoManager", "Failed to get base app infos.", paramString);
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    a = paramContext.getApplicationContext();
    b = a.getPackageManager();
    c = a.getContentResolver();
  }
  
  public static boolean a(d paramD)
  {
    Object localObject = com.dianxinos.library.dxbase.c.a(a).b("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", "");
    if (localObject == null) {
      return false;
    }
    localObject = ((String)localObject).split(",");
    paramD = c.a(paramD.a());
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      if (localObject[i].equals(paramD)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static void b(d paramD)
  {
    com.dianxinos.library.dxbase.c localC = com.dianxinos.library.dxbase.c.a(a);
    paramD = c.a(paramD.a());
    String str = localC.b("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", "");
    if (str == null)
    {
      localC.a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", paramD);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(",");
    localStringBuilder.append(paramD);
    localC.a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", localStringBuilder.toString());
  }
}
