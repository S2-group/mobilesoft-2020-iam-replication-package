package com.vungle.publisher.device.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.ek;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.gu;
import com.vungle.publisher.gu.2;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
public class AppFingerprintManager
{
  @Inject
  public SdkConfig a;
  @Inject
  public AppFingerprint.Factory b;
  @Inject
  public gu c;
  @Inject
  public ScheduledPriorityExecutor d;
  @Inject
  public LoggedException.Factory e;
  
  @Inject
  AppFingerprintManager() {}
  
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
          this.d.a(new Runnable()
          {
            public final void run()
            {
              AppFingerprint.Factory localFactory;
              Object localObject;
              JSONObject localJSONObject;
              try
              {
                Logger.d("VungleData", "creating and sending app fingerprint");
                gu localGu = AppFingerprintManager.this.c;
                localFactory = AppFingerprintManager.this.b;
                localObject = localFactory.b.getPackageManager().getInstalledPackages(0);
                localJSONObject = new JSONObject();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                  PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
                  if (localPackageInfo != null) {
                    localJSONObject.put(localPackageInfo.packageName, true);
                  }
                }
                localObject = (AppFingerprint)localFactory.c.get();
              }
              catch (Exception localException)
              {
                AppFingerprintManager.this.e.a("VungleData", "exception while creating/ sending app fingerprint", localException);
                return;
              }
              ((AppFingerprint)localObject).a = localJSONObject;
              ((AppFingerprint)localObject).b = System.currentTimeMillis();
              ((AppFingerprint)localObject).c = localFactory.a.a();
              ((AppFingerprint)localObject).d = localFactory.a.c();
              ((AppFingerprint)localObject).e = localFactory.a.i();
              localException.e.a(new gu.2(localException, (AppFingerprint)localObject), ScheduledPriorityExecutor.b.s);
            }
          }, ScheduledPriorityExecutor.b.s);
          return;
        }
        Logger.d("VungleData", "throttled fingerprint request");
        return;
      }
    }
    catch (Exception localException)
    {
      this.e.a("VungleData", "exception while throttling app fingerprint", localException);
      return;
    }
    Logger.v("VungleData", "app fingerprinting not allowed by server");
  }
}
