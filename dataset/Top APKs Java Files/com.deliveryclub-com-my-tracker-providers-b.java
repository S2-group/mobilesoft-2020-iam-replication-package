package com.my.tracker.providers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Looper;
import com.my.tracker.utils.c;
import com.my.tracker.utils.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b
  extends a
{
  private boolean a = true;
  private ArrayList<a> b;
  private String c;
  private boolean d = false;
  
  public b() {}
  
  private ArrayList<a> c(Context paramContext)
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
  
  public void a(Context paramContext)
  {
    super.a(paramContext);
    if (this.d)
    {
      e.a(paramContext).e(this.c);
      this.d = false;
    }
  }
  
  public void a(com.my.tracker.builders.a paramA)
  {
    if (this.d) {
      paramA.a(this.b);
    }
  }
  
  public void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }
  
  public void b(Context paramContext)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      com.my.tracker.b.a("AppsDataProvider: You must not call collectData method from main thread");
      return;
    }
    if (!this.a) {
      return;
    }
    this.d = false;
    this.b = c(paramContext);
    String str = c.a(this.b);
    if (!str.equals(""))
    {
      paramContext = e.a(paramContext).c();
      this.c = com.my.tracker.utils.a.a(str);
      if (paramContext.equals(this.c))
      {
        com.my.tracker.b.a("Apps hash did not changed");
        return;
      }
      this.d = true;
      com.my.tracker.b.a("Apps hash changed");
    }
  }
  
  public static class a
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
