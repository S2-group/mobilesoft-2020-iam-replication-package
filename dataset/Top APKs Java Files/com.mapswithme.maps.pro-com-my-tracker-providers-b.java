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
    ArrayList localArrayList = null;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        localArrayList = new ArrayList();
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          if (paramContext.hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
            ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
            if ((localApplicationInfo.flags & 0x1) != 0) {
              continue;
            }
            if (Build.VERSION.SDK_INT <= 8) {
              break label121;
            }
            l = localPackageInfo.firstInstallTime / 1000L;
            localArrayList.add(new a(localApplicationInfo.packageName, l));
            continue;
          }
        }
      }
      catch (Throwable paramContext)
      {
        com.my.tracker.a.a(paramContext.toString());
        paramContext = localArrayList;
        continue;
      }
      return localArrayList;
      label121:
      long l = 0L;
    }
  }
  
  public final void a(Context paramContext)
  {
    super.a(paramContext);
    if (this.d) {}
    try
    {
      e.a().a(paramContext).a(this.c);
      this.d = false;
      return;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        com.my.tracker.a.a("PreferencesManager error: " + paramContext);
      }
    }
  }
  
  public final void a(com.my.tracker.builders.a paramA)
  {
    if (this.d) {
      paramA.b(this.b);
    }
  }
  
  public final void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }
  
  public final void b(Context paramContext)
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      com.my.tracker.a.a("AppsDataProvider: You must not call collectData method from main thread");
    }
    for (;;)
    {
      return;
      if (this.a) {
        try
        {
          Object localObject = e.a().a(paramContext);
          this.d = false;
          this.b = c(paramContext);
          paramContext = c.a(this.b);
          if (!paramContext.equals(""))
          {
            localObject = ((e)localObject).b();
            this.c = com.my.tracker.utils.a.a(paramContext);
            if (((String)localObject).equals(this.c))
            {
              com.my.tracker.a.a("Apps hash did not changed");
              return;
            }
          }
        }
        catch (Throwable paramContext)
        {
          com.my.tracker.a.a("PreferencesManager error: " + paramContext);
          return;
        }
      }
    }
    this.d = true;
    com.my.tracker.a.a("Apps hash changed");
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
