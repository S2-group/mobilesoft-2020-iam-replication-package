package com.my.tracker.providers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Looper;
import com.my.tracker.utils.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class b
  extends a
{
  private boolean a = true;
  private ArrayList<a> b;
  private String c;
  private boolean d = false;
  
  public b() {}
  
  private static ArrayList<a> c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    }
    catch (Throwable paramContext)
    {
      com.my.tracker.b.a(paramContext.toString());
      paramContext = null;
    }
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo.flags & 0x1) == 0)
        {
          long l = 0L;
          if (Build.VERSION.SDK_INT > 8) {
            l = localPackageInfo.firstInstallTime / 1000L;
          }
          localArrayList.add(new a(localApplicationInfo.packageName, l));
        }
      }
    }
    return localArrayList;
  }
  
  public final void a(Context paramContext)
  {
    super.a(paramContext);
    if (this.d)
    {
      d.a(paramContext).e(this.c);
      this.d = false;
    }
  }
  
  public final void a(com.my.tracker.builders.a paramA)
  {
    if (this.d) {
      paramA.a(this.b);
    }
  }
  
  public final void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }
  
  public final void b(Context paramContext)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      com.my.tracker.b.a("AppsDataProvider: You must not call collectData method from main thread");
      return;
    }
    if (!this.a) {
      return;
    }
    int i = 0;
    this.d = false;
    this.b = c(paramContext);
    Object localObject2 = this.b;
    Object localObject1 = new StringBuilder();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      a localA = (a)((Iterator)localObject2).next();
      if (i != 0) {
        ((StringBuilder)localObject1).append(",");
      } else {
        i = 1;
      }
      ((StringBuilder)localObject1).append(localA.a);
    }
    localObject1 = ((StringBuilder)localObject1).toString();
    if (!((String)localObject1).equals(""))
    {
      paramContext = d.a(paramContext).c();
      this.c = com.my.tracker.utils.a.a((String)localObject1);
      if (paramContext.equals(this.c))
      {
        com.my.tracker.b.a("Apps hash did not changed");
        return;
      }
      this.d = true;
      com.my.tracker.b.a("Apps hash changed");
    }
  }
  
  public static final class a
  {
    public final String a;
    public final long b;
    
    public a(String paramString, long paramLong)
    {
      this.a = paramString;
      this.b = paramLong;
    }
  }
}
