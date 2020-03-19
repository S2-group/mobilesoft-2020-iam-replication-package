package com.onesignal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import java.security.MessageDigest;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

final class bl
  implements Runnable
{
  bl() {}
  
  public void run()
  {
    cr localCr = ci.b();
    Object localObject2 = bc.b.getPackageName();
    Object localObject1 = bc.b.getPackageManager();
    localCr.a("app_id", bc.a);
    localCr.a("identifier", bc.t());
    Object localObject3 = bc.u().a(bc.b);
    if (localObject3 != null) {
      localCr.a("ad_id", localObject3);
    }
    localCr.a("device_os", Build.VERSION.RELEASE);
    localCr.a("timezone", Integer.valueOf(bc.v()));
    localCr.a("language", bb.d());
    localCr.a("sdk", "030508");
    localCr.a("sdk_type", bc.d);
    localCr.a("android_package", localObject2);
    localCr.a("device_model", Build.MODEL);
    localCr.a("device_type", Integer.valueOf(bc.w()));
    localCr.b("subscribableStatus", Integer.valueOf(bc.o()));
    localCr.b("androidPermission", Boolean.valueOf(bc.m()));
    try
    {
      localCr.a("game_version", Integer.valueOf(((PackageManager)localObject1).getPackageInfo((String)localObject2, 0).versionCode));
      try
      {
        localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject2 = new JSONArray();
        localObject3 = MessageDigest.getInstance("SHA-256");
        i = 0;
        if (i < ((List)localObject1).size())
        {
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
          String str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
          if (!bc.x().has(str)) {
            break label364;
          }
          ((JSONArray)localObject2).put(str);
          break label364;
        }
        localCr.a("pkgs", localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localCr.a("net_type", bc.y().b());
      localCr.a("carrier", bc.y().c());
      localCr.a("rooted", Boolean.valueOf(dc.a()));
      if (bc.z() != null) {
        localCr.a(bc.z());
      }
      ci.a(localCr, bc.A());
      bc.g(false);
      bw.a(bc.b, bc.a, bc.B(), g.a());
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int i;
        continue;
        label364:
        i += 1;
      }
    }
  }
}
