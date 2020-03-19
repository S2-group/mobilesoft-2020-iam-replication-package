package ir.tapsell.sdk.network.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import ir.tapsell.sdk.b.a;
import ir.tapsell.sdk.network.requestmodels.b;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class d<T extends e>
  implements Serializable
{
  @ir.tapsell.sdk.gson.a.c(a="suggestionId")
  private UUID a;
  @ir.tapsell.sdk.gson.a.c(a="callToActionId")
  private UUID b;
  @ir.tapsell.sdk.gson.a.c(a="creative")
  private T c;
  @ir.tapsell.sdk.gson.a.c(a="suggestionValidationRule")
  private o d;
  @ir.tapsell.sdk.gson.a.c(a="tracker")
  private p e;
  @ir.tapsell.sdk.gson.a.c(a="expirationTimeInMillis")
  private Long f;
  @ir.tapsell.sdk.gson.a.c(a="filledIsReported")
  private boolean g = false;
  @ir.tapsell.sdk.gson.a.c(a="doingIsReported")
  private boolean h = false;
  @ir.tapsell.sdk.gson.a.c(a="doneIsReported")
  private boolean i = false;
  
  public d() {}
  
  public final b a(Integer paramInteger)
  {
    b localB = new b();
    localB.a(paramInteger);
    localB.a(this.a);
    localB.a(new HashMap());
    return localB;
  }
  
  public final UUID a()
  {
    return this.a;
  }
  
  protected abstract void a(Context paramContext);
  
  protected abstract void a(Context paramContext, ir.tapsell.sdk.network.requestmodels.c paramC);
  
  public final UUID b()
  {
    return this.b;
  }
  
  protected abstract void b(Context paramContext);
  
  public final void b(Context paramContext, ir.tapsell.sdk.network.requestmodels.c paramC)
  {
    if (!this.i)
    {
      this.i = true;
      a(paramContext, paramC);
    }
  }
  
  public final p c()
  {
    return this.e;
  }
  
  public final boolean c(Context paramContext)
  {
    if ((this.d != null) && (this.d.a() != null)) {
      switch (this.d.a().intValue())
      {
      }
    }
    ApplicationInfo localApplicationInfo;
    do
    {
      Object localObject;
      while (!((Iterator)localObject).hasNext())
      {
        do
        {
          do
          {
            return true;
          } while (this.d.b() == null);
          paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        } while (!paramContext.hasNext());
        localObject = (ApplicationInfo)paramContext.next();
        if (!this.d.b().equals(((ApplicationInfo)localObject).packageName)) {
          break;
        }
        return false;
        paramContext = paramContext.getPackageManager();
        localObject = paramContext.getInstalledApplications(128).iterator();
      }
      localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
    } while (!this.d.b().equals(localApplicationInfo.packageName));
    for (;;)
    {
      try
      {
        if ((this.d.c() != null) && (this.d.c().intValue() != -1))
        {
          int j = paramContext.getPackageInfo(localApplicationInfo.packageName, 0).versionCode;
          int k = this.d.c().intValue();
          if (j < k) {}
        }
        else
        {
          bool = false;
          return bool;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        a.a("AppSuggestion", Log.getStackTraceString(paramContext));
        return true;
      }
      boolean bool = true;
    }
  }
  
  public final Long d()
  {
    return this.f;
  }
  
  public final void d(Context paramContext)
  {
    if (!this.g)
    {
      this.g = true;
      a(paramContext);
    }
  }
  
  public final T e()
  {
    return this.c;
  }
  
  public final void e(Context paramContext)
  {
    if (!this.h)
    {
      this.h = true;
      b(paramContext);
    }
  }
}
