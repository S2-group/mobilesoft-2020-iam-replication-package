package ir.tapsell.sdk.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import ir.tapsell.sdk.h;
import ir.tapsell.sdk.network.a.a.p;
import ir.tapsell.sdk.network.remote.f;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class g
  extends Thread
{
  private static Long b = Long.valueOf(0L);
  private Context a;
  
  private g(Context paramContext)
  {
    this.a = paramContext;
  }
  
  private void a(int paramInt, String paramString1, final String paramString2, String paramString3)
  {
    Iterator localIterator = this.a.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.equals(paramString1)) && (localPackageInfo.versionCode >= paramInt))
      {
        paramString1 = new HashMap();
        paramString1.put("suggestionId", paramString2);
        paramString1.put("callToActionId", paramString3);
        f.d(this.a, new ir.tapsell.sdk.network.remote.g()
        {
          public final void a() {}
          
          public final void a(int paramAnonymousInt, Throwable paramAnonymousThrowable) {}
        }, paramString1);
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    h.a();
    Long localLong = h.h(paramContext);
    if ((localLong != null) && (localLong.longValue() + 86400000L > new Date().getTime())) {}
    while (b.longValue() + 300000L > new Date().getTime()) {
      return;
    }
    new g(paramContext).start();
  }
  
  public final void run()
  {
    super.run();
    for (;;)
    {
      int i;
      try
      {
        b = Long.valueOf(new Date().getTime());
        h.a();
        ir.tapsell.sdk.d.a[] arrayOfA = h.k(this.a);
        if (arrayOfA != null)
        {
          if (arrayOfA.length == 0) {
            return;
          }
          int k = arrayOfA.length;
          i = 0;
          if (i < k)
          {
            ir.tapsell.sdk.d.a localA = arrayOfA[i];
            if ((localA == null) || (localA.c() == null) || (localA.c().c() == null) || (localA.b() == null) || (localA.a() == null) || (localA.c().c().intValue() != 1)) {
              break label213;
            }
            if (localA.c().b() == null)
            {
              j = 0;
              a(j, localA.c().a(), localA.b().toString(), localA.a().toString());
              break label213;
            }
            int j = localA.c().b().intValue();
            continue;
          }
          h.a();
          h.a(this.a, Long.valueOf(new Date().getTime()));
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        ir.tapsell.sdk.b.a.b(Log.getStackTraceString(localThrowable));
      }
      return;
      label213:
      i += 1;
    }
  }
}
