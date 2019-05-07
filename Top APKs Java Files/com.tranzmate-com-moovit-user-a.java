package com.moovit.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;
import com.moovit.aws.kinesis.f;
import com.moovit.aws.kinesis.i;
import com.moovit.commons.utils.collections.h;
import com.moovit.commons.utils.e.g;
import com.moovit.commons.utils.e.g.f;
import com.tranzmate.moovit.protocol.datacollection.MVApplicationInfo;
import com.tranzmate.moovit.protocol.datacollection.MVApplicationInfos;
import com.tranzmate.moovit.protocol.kinesis.MVServerMessage;
import java.util.concurrent.TimeUnit;

public class a
  extends i
{
  private static final String a = "a";
  private static final long b = TimeUnit.DAYS.toMillis(7L);
  private static final g<Long> c = new g.f("last_updated", -1L);
  private static final com.moovit.commons.utils.collections.o<PackageInfo, MVApplicationInfo> d = new a.1();
  
  private a(@NonNull Context paramContext)
  {
    super(paramContext);
  }
  
  public static void a(@NonNull Context paramContext)
  {
    SharedPreferences localSharedPreferences = c(paramContext);
    long l1 = System.currentTimeMillis();
    long l2 = ((Long)c.b(localSharedPreferences)).longValue();
    if ((l2 > 0L) && (l1 - l2 < b))
    {
      Log.d(a, "No need to update application infos snapshot.");
      return;
    }
    b(paramContext);
  }
  
  public static void b(@NonNull Context paramContext)
  {
    if (com.moovit.aws.kinesis.e.a(paramContext) == null)
    {
      Log.w(a, "Application infos snapshot ignored since no user partition key exist.");
      return;
    }
    com.moovit.o.a(paramContext).b().a(new a(paramContext), false);
    c.a(c(paramContext), Long.valueOf(System.currentTimeMillis()));
    Log.d(a, "Application infos snapshot sent.");
  }
  
  @NonNull
  private static SharedPreferences c(@NonNull Context paramContext)
  {
    return paramContext.getSharedPreferences("app_infos", 0);
  }
  
  @NonNull
  private static MVApplicationInfo c(@NonNull PackageInfo paramPackageInfo)
  {
    return new MVApplicationInfo(paramPackageInfo.packageName, paramPackageInfo.versionName, d(paramPackageInfo));
  }
  
  private static boolean d(@NonNull PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  protected MVServerMessage a()
  {
    Object localObject = f();
    String str = ((Context)localObject).getPackageName();
    localObject = new MVApplicationInfos(com.moovit.commons.utils.collections.e.a(h.a(((Context)localObject).getPackageManager().getInstalledPackages(0), new a.2(this, str)), d));
    ((MVApplicationInfos)localObject).a(System.currentTimeMillis());
    return MVServerMessage.a((MVApplicationInfos)localObject);
  }
}
