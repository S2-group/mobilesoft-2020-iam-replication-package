package com.zing.zalo.game.b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.q;
import com.zing.a.f.e;
import com.zing.zalo.app.MainApplication;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static final HashSet<String> byo;
  
  static
  {
    if (!a.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      pd = bool;
      byo = new HashSet();
      return;
    }
  }
  
  public a() {}
  
  public static void Qo()
  {
    synchronized (byo)
    {
      byo.clear();
      return;
    }
  }
  
  public static void Qp()
  {
    Qo();
    gr(MainApplication.uw());
  }
  
  public static boolean d(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (!paramBoolean) {}
    synchronized (byo)
    {
      if (byo.size() == 0) {
        gq(paramContext);
      }
      return byo.contains(paramString);
    }
  }
  
  private static void gq(Context paramContext)
  {
    long l1 = System.currentTimeMillis();
    Object localObject1 = paramContext.getPackageManager();
    if ((!pd) && (localObject1 == null)) {
      throw new AssertionError();
    }
    Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(64);
    if (localObject2 == null) {}
    do
    {
      return;
      localObject1 = ((List)localObject2).iterator();
      while (((Iterator)localObject1).hasNext()) {
        if (!((ApplicationInfo)((Iterator)localObject1).next()).enabled) {
          ((Iterator)localObject1).remove();
        }
      }
      long l2 = System.currentTimeMillis();
      e.d("ApplicationLoader", "query application done in " + (l2 - l1));
    } while (((List)localObject2).size() <= 0);
    byo.clear();
    localObject1 = new ArrayList(((List)localObject2).size());
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      ((List)localObject1).add(localApplicationInfo.packageName);
      byo.add(localApplicationInfo.packageName);
    }
    localObject1 = new Intent("com.zing.zalo.ACTION_APPLICATION_LOADED");
    q.b(paramContext).b((Intent)localObject1);
  }
  
  public static void gr(Context paramContext)
  {
    d(paramContext, "", false);
  }
  
  public static boolean iy(String paramString)
  {
    return byo.contains(paramString);
  }
}
