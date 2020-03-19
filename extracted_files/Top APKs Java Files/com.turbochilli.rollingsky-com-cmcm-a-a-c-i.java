package com.cmcm.a.a.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.cmcm.a.a.a.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class i
  extends a
{
  public i() {}
  
  protected final void c()
  {
    for (;;)
    {
      try
      {
        List localList = c.b().getPackageManager().getInstalledPackages(0);
        PackageManager localPackageManager = c.b().getPackageManager();
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        if (i < localList.size())
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", ((PackageInfo)localList.get(i)).packageName);
          if ((((PackageInfo)localList.get(i)).applicationInfo.flags & 0x1) != 0)
          {
            bool = true;
            localJSONObject.put("isSystemApp", bool);
            localJSONObject.put("versionCode", ((PackageInfo)localList.get(i)).versionCode);
            localJSONObject.put("versionName", ((PackageInfo)localList.get(i)).versionName);
            if (TextUtils.isEmpty(localPackageManager.getInstallerPackageName(((PackageInfo)localList.get(i)).packageName)))
            {
              str = "";
              localJSONObject.put("appSource", str);
              localJSONObject.put("installDate", ((PackageInfo)localList.get(i)).firstInstallTime / 1000L);
              localArrayList.add(localJSONObject);
              i += 1;
              continue;
            }
            String str = localPackageManager.getInstallerPackageName(((PackageInfo)localList.get(i)).packageName);
            continue;
          }
        }
        else
        {
          a(localArrayList.toString());
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      boolean bool = false;
    }
  }
  
  public final String e()
  {
    return "inst_app_list";
  }
}
