package com.cmcm.dmc.sdk.receiver;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.dmc.sdk.base.k;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class u
  extends a
{
  public u() {}
  
  protected void c()
  {
    for (;;)
    {
      try
      {
        List localList = k.d().getPackageManager().getInstalledPackages(0);
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        if (i < localList.size())
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", ((PackageInfo)localList.get(i)).packageName);
          int j = ((PackageInfo)localList.get(i)).applicationInfo.flags;
          bool = true;
          if ((j & 0x1) != 0)
          {
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
  
  public String f()
  {
    return "inst_app_list";
  }
}
