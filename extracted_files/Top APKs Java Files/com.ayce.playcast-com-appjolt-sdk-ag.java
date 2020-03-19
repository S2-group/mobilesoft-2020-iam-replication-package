package com.appjolt.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.appjolt.sdk.utils.d;
import com.appjolt.sdk.utils.lang.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ag
  extends dc<Void, Void, Void>
{
  private ag(AppjoltService paramAppjoltService) {}
  
  private void a()
  {
    d.c(AppjoltService.a(), "Populating Installed Apps to configuration request");
    PackageManager localPackageManager = AppjoltService.b(this.a).getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Object localObject = localPackageManager.getInstalledApplications(128);
    Map localMap = h.a(AppjoltService.c(this.a).d("config_events"), "install", null);
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      label208:
      String str;
      for (;;)
      {
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 0);
          if (localApplicationInfo == null) {
            break label208;
          }
          localObject = localPackageManager.getApplicationLabel(localApplicationInfo);
          localObject = (String)localObject;
          d.c(AppjoltService.a(), "APP NAME: %s", new Object[] { localObject });
          localObject = new bg("install", (String)localObject, localApplicationInfo.packageName, localPackageInfo.versionCode, localPackageInfo.firstInstallTime);
          if (!a(localApplicationInfo.packageName, localMap)) {
            break label214;
          }
          localArrayList.add(((bg)localObject).a());
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          d.e(AppjoltService.a(), "Installed App error", new Object[] { localNameNotFoundException.toString() });
        }
        break;
        str = "NA";
      }
      label214:
      d.c(AppjoltService.a(), "Ignoring App: %s", new Object[] { str.a() });
    }
    AppjoltService.c(this.a).a("device_events", localArrayList).a();
  }
  
  private boolean a(String paramString, Map<String, Object> paramMap)
  {
    d.c(AppjoltService.a(), "Searching Global install Configuration");
    boolean bool1;
    boolean bool2;
    if (paramMap != null)
    {
      paramMap = h.a(paramMap, "analytics", null);
      d.c(AppjoltService.a(), "install WHITELIST/BLACKLIST CHECK");
      Object localObject = h.a(paramMap, "white_list", new ArrayList());
      bool1 = ((ArrayList)localObject).contains("*");
      if (((ArrayList)localObject).contains("*"))
      {
        d.c(AppjoltService.a(), "WHITELIST HAS AN ASTRIK IN IT, ADDING ALL");
        bool1 = true;
      }
      if (!bool1)
      {
        localObject = ((ArrayList)localObject).iterator();
        for (;;)
        {
          if (((Iterator)localObject).hasNext())
          {
            String str = (String)((Iterator)localObject).next();
            if ((!str.isEmpty()) && (paramString.contains(str)))
            {
              d.c(AppjoltService.a(), "WHITELISTED: %s", new Object[] { str });
              bool1 = true;
              d.c(AppjoltService.a(), "WHITELIST Result: %s", new Object[] { Boolean.toString(bool1) });
              bool2 = bool1;
              if (bool1)
              {
                paramMap = h.a(paramMap, "black_list", new ArrayList());
                if (!paramMap.contains("*")) {
                  break label294;
                }
                d.e(AppjoltService.a(), "BLACKLIST HAS AN ASTRIK IN IT");
                bool1 = false;
                label199:
                if (!bool1) {
                  break label288;
                }
                paramMap = paramMap.iterator();
                while (paramMap.hasNext())
                {
                  localObject = (String)paramMap.next();
                  if ((!((String)localObject).isEmpty()) && (paramString.contains((CharSequence)localObject)))
                  {
                    d.c(AppjoltService.a(), "BLACKLISTED: %s", new Object[] { localObject });
                    bool2 = false;
                  }
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      d.c(AppjoltService.a(), "Should ADD TO INSTALL LIST Final Result: %s", new Object[] { Boolean.toString(bool2) });
      return bool2;
      label288:
      bool2 = bool1;
      continue;
      label294:
      break label199;
      break;
      bool2 = false;
    }
  }
  
  protected Void a(Void... paramVarArgs)
  {
    if (AppjoltService.c(this.a).a("snapshot_sent"))
    {
      d.c(AppjoltService.a(), "No client ID.. Ignoring");
      return null;
    }
    if (AppjoltService.c(this.a).a("snapshot_sent"))
    {
      d.c(AppjoltService.a(), "Snapshot already sent.. Ignoring");
      return null;
    }
    a();
    AppjoltService.c(this.a).a("snapshot_sent", Boolean.valueOf(true)).a();
    AppjoltService.c(this.a).a("last_updated", Long.valueOf(0L)).a();
    return null;
  }
  
  protected void a(Void paramVoid)
  {
    super.a(paramVoid);
    AppjoltService.a(AppjoltService.b(this.a), "");
  }
}
