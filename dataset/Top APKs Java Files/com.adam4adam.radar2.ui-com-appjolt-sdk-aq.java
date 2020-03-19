package com.appjolt.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.appjolt.sdk.utils.d;
import java.util.Iterator;
import java.util.List;

class aq
  extends dc<Void, Void, Void>
{
  private aq(AppjoltService paramAppjoltService) {}
  
  private void a()
  {
    if (!AppjoltService.c(this.a).a("apps_registry_first_save"))
    {
      d.c(AppjoltService.a(), "Populating Installed Apps to Registry");
      PackageManager localPackageManager = AppjoltService.b(this.a).getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      if (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo != null) {}
        for (Object localObject = localPackageManager.getApplicationLabel(localApplicationInfo);; localObject = "NA")
        {
          localObject = (String)localObject;
          d.c(AppjoltService.a(), "ADDING APP TO DICTIONARY: %s", new Object[] { localObject });
          AppjoltService.e(this.a).a(localApplicationInfo.packageName, localObject);
          break;
        }
      }
      AppjoltService.e(this.a).a();
      AppjoltService.c(this.a).a("apps_registry_first_save", Boolean.valueOf(true)).a();
      return;
    }
    d.c(AppjoltService.a(), "Installed Apps Registry already exists");
  }
  
  protected Void a(Void... paramVarArgs)
  {
    a();
    return null;
  }
}
