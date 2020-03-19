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
import com.facebook.n.b;
import com.facebook.n.h;
import com.facebook.n.i;
import com.facebook.n.j;
import com.facebook.n.k;
import com.facebook.n.l;
import com.facebook.n.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class e
  implements k, com.instagram.common.r.b.a
{
  long a;
  private final long b;
  private final long c;
  private h d;
  private SharedPreferences e;
  
  public e(Context paramContext, String paramString)
  {
    this(paramContext, paramString, (byte)0);
  }
  
  private e(Context paramContext, String paramString, byte paramByte)
  {
    this.e = paramContext.getSharedPreferences("analyticsprefs", 0);
    this.b = 604800000L;
    this.c = 604800000L;
    paramString = new f(this, paramString);
    this.d = new h(paramContext, a.a(com.instagram.common.g.a.a), paramString, a.a(com.instagram.common.g.a.a), new c(paramContext), this);
  }
  
  public final void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    com.instagram.common.j.c.b(paramString1, paramString2, paramThrowable);
  }
  
  public final void c()
  {
    long l2 = this.e.getLong("analytics_phoneid_last_sync_timestamp", 0L);
    long l3 = System.currentTimeMillis();
    long l1;
    if (this.e.getBoolean("analytics_is_phoneid_fully_synced", true)) {
      l1 = this.b;
    } else {
      l1 = this.c;
    }
    if ((l3 - l2 >= l1) || (l3 < l2))
    {
      this.a = SystemClock.elapsedRealtime();
      this.e.edit().putLong("analytics_phoneid_last_sync_timestamp", l3).putBoolean("analytics_is_phoneid_fully_synced", true).apply();
      h localH = this.d;
      Object localObject3 = localH.a.getPackageManager().getInstalledPackages(0);
      Object localObject1 = new ArrayList();
      Object localObject2 = localH.a.getPackageName();
      localObject3 = ((List)localObject3).iterator();
      Object localObject4;
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (PackageInfo)((Iterator)localObject3).next();
        if ((!((PackageInfo)localObject4).packageName.equals(localObject2)) && (b.a(localH.a, ((PackageInfo)localObject4).packageName))) {
          ((List)localObject1).add(((PackageInfo)localObject4).packageName);
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
  
  public final void d() {}
}
