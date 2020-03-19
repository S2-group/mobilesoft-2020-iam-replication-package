package com.a.b.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import com.a.a.d.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class d
{
  private static Context a;
  
  public static List<g> a()
  {
    ArrayList localArrayList = new ArrayList();
    if (a == null) {
      return localArrayList;
    }
    JSONArray localJSONArray = new JSONArray();
    String str1 = com.a.a.h.d.a();
    Iterator localIterator = a.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (PackageInfo)localIterator.next();
      String str2 = ((PackageInfo)localObject1).packageName;
      String str3 = String.format("%d", new Object[] { Integer.valueOf(((PackageInfo)localObject1).versionCode) });
      String str4 = String.format("%d", new Object[] { Integer.valueOf(((PackageInfo)localObject1).applicationInfo.targetSdkVersion) });
      String str5 = ((PackageInfo)localObject1).versionName;
      String str6 = ((PackageInfo)localObject1).applicationInfo.className;
      String str7 = String.format("%d", new Object[] { Long.valueOf(((PackageInfo)localObject1).firstInstallTime) });
      String str8 = String.format("%d", new Object[] { Long.valueOf(((PackageInfo)localObject1).lastUpdateTime) });
      Object localObject2 = ((PackageInfo)localObject1).permissions;
      localObject1 = new ArrayList();
      if (localObject2 != null)
      {
        int j = localObject2.length;
        int i = 0;
        while (i < j)
        {
          ((List)localObject1).add(localObject2[i].name);
          i += 1;
        }
      }
      localObject2 = new g();
      ((g)localObject2).a(str1);
      if (str2 != null) {
        ((g)localObject2).b(str2);
      }
      if (str3 != null) {
        ((g)localObject2).c(str3);
      }
      if (str4 != null) {
        ((g)localObject2).d(str4);
      }
      if (str5 != null) {
        ((g)localObject2).e(str5);
      }
      if (str6 != null) {
        ((g)localObject2).f(str6);
      }
      ((g)localObject2).g("");
      ((g)localObject2).h("");
      if (str7 != null) {
        ((g)localObject2).i(str7);
      }
      if (str8 != null) {
        ((g)localObject2).j(str8);
      }
      ((g)localObject2).a((List)localObject1);
      localArrayList.add(localObject2);
      localJSONArray.put(((g)localObject2).l());
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext)
  {
    a = paramContext;
  }
}
