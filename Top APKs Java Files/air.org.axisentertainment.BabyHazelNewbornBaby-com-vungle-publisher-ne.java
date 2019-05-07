package com.vungle.publisher;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.vungle.log.Logger;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
public final class ne
{
  @Inject
  ra a;
  @Inject
  ahf b;
  @Inject
  nd.a c;
  @Inject
  yb d;
  @Inject
  cz e;
  @Inject
  hs.a f;
  
  @Inject
  ne() {}
  
  public final void a()
  {
    try
    {
      long l1 = this.a.g;
      if (l1 > 0L)
      {
        Logger.v("VungleData", "app fingerprinting allowed by server");
        long l2 = System.currentTimeMillis();
        if (l1 + this.a.f < l2)
        {
          this.e.a(new Runnable()
          {
            public final void run()
            {
              nd.a localA;
              Object localObject;
              JSONObject localJSONObject;
              try
              {
                Logger.d("VungleData", "creating and sending app fingerprint");
                yb localYb = ne.this.d;
                localA = ne.this.c;
                localObject = localA.c.getPackageManager().getInstalledPackages(0);
                localJSONObject = new JSONObject();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                  PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
                  if (localPackageInfo != null) {
                    localJSONObject.put(localPackageInfo.packageName, true);
                  }
                }
                localObject = (nd)localA.d.get();
              }
              catch (Exception localException)
              {
                ne.this.f.a("VungleData", "exception while creating/ sending app fingerprint", localException);
                return;
              }
              ((nd)localObject).a = localJSONObject;
              ((nd)localObject).b = System.currentTimeMillis();
              ((nd)localObject).c = localA.a.a();
              ((nd)localObject).d = localA.a.c();
              ((nd)localObject).e = localA.a.i();
              localException.a((nd)localObject);
            }
          }, cz.b.s);
          return;
        }
        Logger.d("VungleData", "throttled fingerprint request");
        return;
      }
    }
    catch (Exception localException)
    {
      this.f.a("VungleData", "exception while throttling app fingerprint", localException);
      return;
    }
    Logger.v("VungleData", "app fingerprinting not allowed by server");
  }
}
