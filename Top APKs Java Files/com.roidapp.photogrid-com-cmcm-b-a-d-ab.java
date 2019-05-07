package com.cmcm.b.a.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.b.a.a.u;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ab
  extends a
{
  public ab() {}
  
  protected final void c()
  {
    for (;;)
    {
      try
      {
        List localList = u.c().getPackageManager().getInstalledPackages(0);
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
  
  public final String d_()
  {
    return "inst_app_list";
  }
}
