package com.lantern.core.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.bluefay.b.g;
import com.lantern.core.h;
import com.lantern.core.i;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c
  extends AsyncTask<Void, Void, Void>
{
  private Context a;
  
  public c(Context paramContext)
  {
    this.a = paramContext;
  }
  
  private Void a()
  {
    for (;;)
    {
      try
      {
        if (this.a != null)
        {
          Object localObject2 = this.a.getPackageManager();
          JSONArray localJSONArray = new JSONArray();
          List localList = ((PackageManager)localObject2).getInstalledPackages(0);
          int i = 0;
          if (i < localList.size())
          {
            localObject2 = (PackageInfo)localList.get(i);
            JSONObject localJSONObject = new JSONObject();
            try
            {
              if (com.lantern.core.c.k())
              {
                localJSONObject.put("pkgName", g.a(((PackageInfo)localObject2).packageName));
                localJSONObject.put("versionName", ((PackageInfo)localObject2).versionName);
                localJSONObject.put("versionCode", String.valueOf(((PackageInfo)localObject2).versionCode));
                if (Build.VERSION.SDK_INT >= 9)
                {
                  localJSONObject.put("installTime", String.valueOf(((PackageInfo)localObject2).firstInstallTime));
                  localJSONObject.put("updateTime", String.valueOf(((PackageInfo)localObject2).lastUpdateTime));
                }
                localJSONObject.put("an", "");
                if ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) != 0) {
                  break label350;
                }
                if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) != 0) {
                  break label335;
                }
                break label350;
                localJSONObject.put("appType", localObject2);
                localJSONArray.put(localJSONObject);
                if (((i + 1) % 5 != 0) && (i + 1 != localList.size())) {
                  break label347;
                }
                localObject2 = com.lantern.core.a.j();
                localObject2 = h.a(Uri.encode(localJSONArray.toString().trim(), "UTF-8"), ((i)localObject2).j(), ((i)localObject2).k());
                localJSONObject = new JSONObject();
                localJSONObject.put("ED", localObject2);
                com.lantern.analytics.a.e().b("005022", localJSONObject);
                if ((i + 1) % 5 != 0) {
                  break label347;
                }
              }
            }
            catch (Exception localException2) {}
          }
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
      try
      {
        localJSONArray = new JSONArray();
        i += 1;
      }
      catch (Exception localException3)
      {
        String str1;
        Object localObject1 = null;
        continue;
      }
      localJSONObject.put("pkgName", ((PackageInfo)localObject2).packageName);
      continue;
      localException2.printStackTrace();
      continue;
      return null;
      label335:
      str1 = "system";
      continue;
      label347:
      continue;
      label350:
      String str2 = "user";
    }
  }
}
