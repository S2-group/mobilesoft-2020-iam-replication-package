package com.tiawy.instafake;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import com.onesignal.OneSignal;
import java.security.MessageDigest;
import java.util.List;
import org.json.JSONArray;

public final class bpg
  implements Runnable
{
  public bpg() {}
  
  public void run()
  {
    int i = 0;
    bqb localBqb = bpt.a();
    String str = OneSignal.jdField_a_of_type_AndroidContentContext.getPackageName();
    Object localObject3 = OneSignal.jdField_a_of_type_AndroidContentContext.getPackageManager();
    localBqb.a("app_id", OneSignal.jdField_a_of_type_JavaLangString);
    localBqb.a("identifier", OneSignal.c());
    Object localObject2 = OneSignal.a().a(OneSignal.jdField_a_of_type_AndroidContentContext);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new bob().a(OneSignal.jdField_a_of_type_AndroidContentContext);
    }
    localBqb.a("ad_id", localObject1);
    localBqb.a("device_os", Build.VERSION.RELEASE);
    localBqb.a("timezone", Integer.valueOf(OneSignal.a()));
    localBqb.a("language", bpa.b());
    localBqb.a("sdk", "020602");
    localBqb.a("sdk_type", OneSignal.c);
    localBqb.a("android_package", str);
    localBqb.a("device_model", Build.MODEL);
    localBqb.a("device_type", Integer.valueOf(OneSignal.b()));
    localBqb.b("subscribableStatus", Integer.valueOf(OneSignal.jdField_a_of_type_Int));
    try
    {
      localBqb.a("game_version", Integer.valueOf(((PackageManager)localObject3).getPackageInfo(str, 0).versionCode));
      try
      {
        localObject1 = ((PackageManager)localObject3).getInstalledPackages(0);
        localObject2 = new JSONArray();
        localObject3 = MessageDigest.getInstance("SHA-256");
        if (i < ((List)localObject1).size())
        {
          if (((((PackageInfo)((List)localObject1).get(i)).applicationInfo.flags & 0x1) != 0) || (str.equals(((PackageInfo)((List)localObject1).get(i)).packageName))) {
            break label441;
          }
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
          ((JSONArray)localObject2).put(Base64.encodeToString(((MessageDigest)localObject3).digest(), 2));
          break label441;
        }
        localBqb.a("pkgs", localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localObject1 = OneSignal.a(OneSignal.jdField_a_of_type_AndroidContentContext).getString("OS_USER_EMAIL", null);
      if (localObject1 != null) {
        localBqb.a("email", localObject1);
      }
      localBqb.a("net_type", OneSignal.a().a());
      localBqb.a("carrier", OneSignal.a().a());
      localBqb.a("rooted", Boolean.valueOf(bqp.a()));
      localBqb.a("lat", OneSignal.a());
      localBqb.a("long", OneSignal.b());
      localBqb.a("loc_acc", OneSignal.a());
      localBqb.a("loc_type", OneSignal.a());
      bpt.a(localBqb);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        continue;
        label441:
        i += 1;
      }
    }
  }
}
