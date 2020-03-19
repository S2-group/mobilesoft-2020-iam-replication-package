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

final class br
  implements Runnable
{
  br() {}
  
  public void run()
  {
    cy localCy = cp.b();
    Object localObject2 = OneSignal.b.getPackageName();
    Object localObject1 = OneSignal.b.getPackageManager();
    localCy.a("app_id", OneSignal.a);
    localCy.a("identifier", OneSignal.t());
    Object localObject3 = OneSignal.u().a(OneSignal.b);
    if (localObject3 != null) {
      localCy.a("ad_id", localObject3);
    }
    localCy.a("device_os", Build.VERSION.RELEASE);
    localCy.a("timezone", Integer.valueOf(OneSignal.v()));
    localCy.a("language", bf.d());
    localCy.a("sdk", "030605");
    localCy.a("sdk_type", OneSignal.g);
    localCy.a("android_package", localObject2);
    localCy.a("device_model", Build.MODEL);
    localCy.a("device_type", Integer.valueOf(OneSignal.w()));
    localCy.b("subscribableStatus", Integer.valueOf(OneSignal.o()));
    localCy.b("androidPermission", Boolean.valueOf(OneSignal.m()));
    try
    {
      localCy.a("game_version", Integer.valueOf(((PackageManager)localObject1).getPackageInfo((String)localObject2, 0).versionCode));
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
          if (!OneSignal.x().has(str)) {
            break label370;
          }
          ((JSONArray)localObject2).put(str);
          break label370;
        }
        localCy.a("pkgs", localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localCy.a("net_type", OneSignal.y().b());
      localCy.a("carrier", OneSignal.y().c());
      localCy.a("rooted", Boolean.valueOf(ds.a()));
      if ((OneSignal.h) && (OneSignal.z() != null)) {
        localCy.a(OneSignal.z());
      }
      cp.a(localCy, OneSignal.A());
      OneSignal.g(false);
      by.a(OneSignal.b, OneSignal.a, OneSignal.B(), g.a());
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int i;
        continue;
        label370:
        i += 1;
      }
    }
  }
}
