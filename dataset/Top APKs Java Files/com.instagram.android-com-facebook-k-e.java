package com.facebook.k;

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

public class e
{
  private final Context a;
  private final g b;
  private final h c;
  private final k d;
  
  public e(Context paramContext, g paramG, h paramH, k paramK)
  {
    this.a = paramContext;
    this.b = paramG;
    this.c = paramH;
    this.d = null;
  }
  
  private Intent a(String paramString)
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
        this.b.a(new d(UUID.randomUUID().toString(), System.currentTimeMillis()));
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
          if (!b.a(localPackageInfo)) {
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
  
  public void a()
  {
    Object localObject1 = this.b;
    b();
    localObject1 = c().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (String)((Iterator)localObject1).next();
      Intent localIntent = a((String)localObject2);
      Bundle localBundle = d();
      localObject2 = new j((String)localObject2);
      this.a.sendOrderedBroadcast(localIntent, null, new f(this.b, this.c, this.d, (j)localObject2), null, 1, null, localBundle);
    }
  }
}
