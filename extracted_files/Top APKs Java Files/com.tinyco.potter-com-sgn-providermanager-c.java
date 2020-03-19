package com.sgn.providermanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class c
{
  private String a;
  
  public c() {}
  
  public final String a(Context paramContext)
  {
    if (this.a != null) {
      return this.a;
    }
    Object localObject1 = new d(paramContext);
    Object localObject2 = ((d)localObject1).a();
    if (localObject2 == null)
    {
      localObject2 = new a(paramContext);
      Iterator localIterator = ((a)localObject2).a.getPackageManager().getInstalledApplications(0).iterator();
      int i;
      label93:
      label116:
      do
      {
        if (!localIterator.hasNext()) {
          break label213;
        }
        paramContext = (ApplicationInfo)localIterator.next();
        String str = paramContext.packageName.toLowerCase();
        i = 0;
        if (i >= e.a.length) {
          break label208;
        }
        if (!str.contains(e.a[i])) {
          break;
        }
        i = 1;
        if (i == 0) {
          break label211;
        }
        paramContext = ((a)localObject2).a(paramContext.packageName);
      } while (paramContext == null);
      for (;;)
      {
        this.a = paramContext;
        if (this.a == null) {
          this.a = UUID.randomUUID().toString().toLowerCase();
        }
        paramContext = this.a;
        localObject1 = ((d)localObject1).a.getSharedPreferences("JAMCITY_UNIQUE_DEVICE_IDENTIFIER", 0).edit();
        ((SharedPreferences.Editor)localObject1).putString("judi", paramContext);
        ((SharedPreferences.Editor)localObject1).apply();
        return this.a;
        i += 1;
        break label93;
        label208:
        i = 0;
        break label116;
        label211:
        break;
        label213:
        paramContext = null;
      }
    }
    this.a = ((String)localObject2);
    return this.a;
  }
}
