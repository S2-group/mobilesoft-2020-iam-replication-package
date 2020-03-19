package com.instagram.common.analytics.phoneid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import com.facebook.h.h;
import com.facebook.h.i;
import com.facebook.h.j;
import com.facebook.h.k;
import com.instagram.common.q.b.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class g
  implements k, a
{
  public long a;
  private final long b;
  private final long c;
  private h d;
  private SharedPreferences e;
  
  public g(Context paramContext, String paramString)
  {
    this(paramContext, paramString, 604800L, false);
  }
  
  public g(Context paramContext, String paramString, long paramLong, boolean paramBoolean)
  {
    this.e = paramContext.getSharedPreferences("analyticsprefs", 0);
    this.b = 604800000L;
    this.c = (1000L * paramLong);
    paramString = new e(this, paramString);
    this.d = new h(paramContext, b.e(), paramString, b.e(), new d(paramContext, paramBoolean), this);
  }
  
  public final void a()
  {
    long l2 = this.e.getLong("analytics_phoneid_last_sync_timestamp", 0L);
    long l3 = System.currentTimeMillis();
    long l1;
    if (this.e.getBoolean("analytics_is_phoneid_fully_synced", true)) {
      l1 = this.b;
    }
    while ((l3 - l2 >= l1) || (l3 < l2))
    {
      this.a = SystemClock.elapsedRealtime();
      this.e.edit().putLong("analytics_phoneid_last_sync_timestamp", l3).putBoolean("analytics_is_phoneid_fully_synced", true).apply();
      h localH = this.d;
      Object localObject3 = localH.a.getPackageManager().getInstalledPackages(0);
      Object localObject1 = new ArrayList();
      Object localObject2 = localH.a.getPackageName();
      localObject3 = ((List)localObject3).iterator();
      Object localObject4;
      for (;;)
      {
        if (((Iterator)localObject3).hasNext())
        {
          localObject4 = (PackageInfo)((Iterator)localObject3).next();
          if ((!((PackageInfo)localObject4).packageName.equals(localObject2)) && (com.facebook.h.c.a(localH.a, ((PackageInfo)localObject4).packageName)))
          {
            ((List)localObject1).add(((PackageInfo)localObject4).packageName);
            continue;
            l1 = this.c;
            break;
          }
        }
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject4 = (String)((Iterator)localObject1).next();
        if (!localH.a((String)localObject4))
        {
          localObject2 = new Intent();
          ((Intent)localObject2).setAction("com.facebook.GET_PHONE_ID");
          ((Intent)localObject2).setPackage((String)localObject4);
          PendingIntent localPendingIntent = PendingIntent.getActivity(localH.a, 0, new Intent(), 134217728);
          localObject3 = new Bundle();
          ((Bundle)localObject3).putParcelable("auth", localPendingIntent);
          localObject4 = new i((String)localObject4, localH.b.a(), "broadcasts");
          localH.a.sendOrderedBroadcast((Intent)localObject2, null, new j(localH.c, localH.e, (i)localObject4), null, 1, null, (Bundle)localObject3);
        }
      }
      if (localH.d != null) {
        localH.d.d();
      }
    }
  }
  
  public final void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    com.instagram.common.c.c.a().a(paramString1, paramString2, paramThrowable, false);
  }
  
  public final void onAppBackgrounded()
  {
    a();
  }
  
  public final void onAppForegrounded() {}
}
