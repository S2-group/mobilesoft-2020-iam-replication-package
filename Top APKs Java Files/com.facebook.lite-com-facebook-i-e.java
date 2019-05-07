package com.facebook.i;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class e
{
  private final Context a;
  private final f b;
  private final g c;
  private final h d;
  
  public e(Context paramContext, f paramF, g paramG)
  {
    this.a = paramContext;
    this.b = paramF;
    this.c = paramG;
    this.d = null;
  }
  
  private static Intent a(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.facebook.GET_PHONE_ID");
    localIntent.setPackage(paramString);
    return localIntent;
  }
  
  private boolean b()
  {
    synchronized (this.b)
    {
      if (this.b.a() == null)
      {
        this.b.a(new b(UUID.randomUUID().toString(), System.currentTimeMillis()));
        return true;
      }
      return false;
    }
  }
  
  private List<String> c()
  {
    Object localObject = this.a.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    String str = this.a.getPackageName();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      PackageInfo localPackageInfo;
      if (((Iterator)localObject).hasNext())
      {
        localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (localPackageInfo.packageName.equals(str)) {}
      }
      else
      {
        try
        {
          localPackageInfo = this.a.getPackageManager().getPackageInfo(localPackageInfo.packageName, 64);
          if (!c.a(localPackageInfo)) {
            continue;
          }
          localArrayList.add(localPackageInfo.packageName);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        return localArrayList;
      }
    }
  }
  
  private Bundle d()
  {
    PendingIntent localPendingIntent = PendingIntent.getActivity(this.a, 0, new Intent(), 134217728);
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("auth", localPendingIntent);
    return localBundle;
  }
  
  public final void a()
  {
    b();
    Iterator localIterator = c().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      Intent localIntent = a((String)localObject);
      Bundle localBundle = d();
      localObject = new j((String)localObject);
      this.a.sendOrderedBroadcast(localIntent, null, new k(this.b, this.c, this.d, (j)localObject), null, 1, null, localBundle);
    }
  }
}
