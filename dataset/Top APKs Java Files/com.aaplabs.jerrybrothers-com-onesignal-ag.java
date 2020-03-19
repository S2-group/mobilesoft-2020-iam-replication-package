package com.onesignal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import java.security.MessageDigest;
import java.util.List;
import org.json.JSONArray;

final class ag
  implements Runnable
{
  ag() {}
  
  public void run()
  {
    int i = 0;
    ao.c localC = ao.b();
    String str = aa.c.getPackageName();
    Object localObject3 = aa.c.getPackageManager();
    localC.a("app_id", aa.a);
    localC.a("identifier", aa.i());
    Object localObject2 = aa.j().a(aa.c);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new f().a(aa.c);
    }
    localC.a("ad_id", localObject1);
    localC.a("device_os", Build.VERSION.RELEASE);
    localC.a("timezone", Integer.valueOf(aa.k()));
    localC.a("language", z.d());
    localC.a("sdk", "020602");
    localC.a("sdk_type", aa.f);
    localC.a("android_package", str);
    localC.a("device_model", Build.MODEL);
    localC.a("device_type", Integer.valueOf(aa.l()));
    localC.b("subscribableStatus", Integer.valueOf(aa.d));
    try
    {
      localC.a("game_version", Integer.valueOf(((PackageManager)localObject3).getPackageInfo(str, 0).versionCode));
      try
      {
        localObject1 = ((PackageManager)localObject3).getInstalledPackages(0);
        localObject2 = new JSONArray();
        localObject3 = MessageDigest.getInstance("SHA-256");
        if (i < ((List)localObject1).size())
        {
          if (((((PackageInfo)((List)localObject1).get(i)).applicationInfo.flags & 0x1) != 0) || (str.equals(((PackageInfo)((List)localObject1).get(i)).packageName))) {
            break label442;
          }
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
          ((JSONArray)localObject2).put(Base64.encodeToString(((MessageDigest)localObject3).digest(), 2));
          break label442;
        }
        localC.a("pkgs", localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localObject1 = aa.f(aa.c).getString("OS_USER_EMAIL", null);
      if (localObject1 != null) {
        localC.a("email", localObject1);
      }
      localC.a("net_type", aa.m().b());
      localC.a("carrier", aa.m().c());
      localC.a("rooted", Boolean.valueOf(be.a()));
      localC.a("lat", aa.n());
      localC.a("long", aa.o());
      localC.a("loc_acc", aa.p());
      localC.a("loc_type", aa.q());
      ao.a(localC);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        continue;
        label442:
        i += 1;
      }
    }
  }
}
