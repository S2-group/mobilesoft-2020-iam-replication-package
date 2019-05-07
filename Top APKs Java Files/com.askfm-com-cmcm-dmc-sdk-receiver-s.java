package com.cmcm.dmc.sdk.receiver;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class s
  extends a
{
  public s() {}
  
  public String a_()
  {
    return "inst_app_list";
  }
  
  protected void c()
  {
    for (;;)
    {
      try
      {
        List localList = com.cmcm.dmc.sdk.base.s.c().getPackageManager().getInstalledPackages(0);
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
            localJSONObject.put("installDate", ((PackageInfo)localList.get(i)).firstInstallTime / 1000L);
            localArrayList.add(localJSONObject);
            i += 1;
          }
        }
        else
        {
          c(localArrayList.toString());
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
}
