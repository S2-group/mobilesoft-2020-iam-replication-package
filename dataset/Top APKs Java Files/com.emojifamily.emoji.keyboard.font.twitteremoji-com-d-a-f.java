package com.d.a;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.d.a.b.a.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

final class f
  extends j<Void, Void, Boolean>
{
  private String b;
  
  public f(ak paramAk, String paramString)
  {
    this.b = paramString;
  }
  
  private boolean b()
  {
    ak.a();
    Object localObject3 = ak.c(this.a).getPackageManager();
    Object localObject1 = ((PackageManager)localObject3).getInstalledApplications(128);
    TreeSet localTreeSet = new TreeSet(ak.d(this.a).a());
    Iterator localIterator = ((List)localObject1).iterator();
    boolean bool = false;
    while (localIterator.hasNext())
    {
      Object localObject4 = (ApplicationInfo)localIterator.next();
      if ((localObject4 != null) && (!((ApplicationInfo)localObject4).packageName.equalsIgnoreCase("com.android.keyguard")))
      {
        if (localObject4 != null) {}
        for (;;)
        {
          try
          {
            localObject1 = ((PackageManager)localObject3).getApplicationLabel((ApplicationInfo)localObject4);
            localObject1 = (String)localObject1;
          }
          catch (Exception localException)
          {
            b localB;
            String str;
            ak.a();
            localObject2 = "NA";
            continue;
            int i = 0;
            continue;
            localB.a.a(localObject4);
            continue;
          }
          if (((ApplicationInfo)localObject4).packageName == null) {
            break label250;
          }
          localB = ak.d(this.a);
          str = ((ApplicationInfo)localObject4).packageName;
          if ((str == null) || (localB.a.a.get(str) == null)) {
            continue;
          }
          i = 1;
          if (i != 0) {
            break label250;
          }
          ak.a();
          localB = ak.d(this.a);
          localObject4 = ((ApplicationInfo)localObject4).packageName;
          if (localObject1 == null) {
            continue;
          }
          localB.a.a.put(localObject4, localObject1);
          bool = true;
          break;
          localObject1 = "NA";
        }
        label250:
        if (((ApplicationInfo)localObject4).packageName != null) {
          localTreeSet.remove(((ApplicationInfo)localObject4).packageName);
        }
      }
    }
    Object localObject2 = localTreeSet.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (String)((Iterator)localObject2).next();
      if (!ak.d(this.a).a.a(localObject3)) {
        ak.a();
      }
      bool = true;
    }
    localObject2 = ak.d(this.a).a;
    ((a)localObject2).c.a(((a)localObject2).a, ((a)localObject2).b);
    ak.b(this.a).edit().putBoolean("apps_registry_first_save", true).commit();
    return bool;
  }
}
