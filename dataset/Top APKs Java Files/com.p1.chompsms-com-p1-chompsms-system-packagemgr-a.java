package com.p1.chompsms.system.packagemgr;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import com.p1.chompsms.ChompSms;
import com.p1.chompsms.util.Util;
import com.p1.chompsms.util.bw;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class a
{
  public static a a;
  private ArrayList<String> b;
  private final ArrayList<WeakReference<e>> c = new ArrayList();
  private final ArrayList<WeakReference<a>> d = new ArrayList();
  private final ArrayList<WeakReference<d>> e = new ArrayList();
  private final ArrayList<WeakReference<c>> f = new ArrayList();
  private Handler g;
  private Context h;
  
  public a(Context paramContext)
  {
    this.h = paramContext;
    a(paramContext);
  }
  
  private void a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    this.b = new ArrayList();
    paramContext = paramContext.getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      this.b.add(localApplicationInfo.packageName);
    }
    this.g = new Handler();
  }
  
  private <T> void a(ArrayList<WeakReference<T>> paramArrayList, final b<T> paramB)
  {
    for (;;)
    {
      try
      {
        Iterator localIterator = paramArrayList.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        final Object localObject = ((WeakReference)localIterator.next()).get();
        if (localObject == null) {
          localIterator.remove();
        } else {
          this.g.post(new Runnable()
          {
            public final void run()
            {
              paramB.a(localObject);
            }
          });
        }
      }
      finally {}
    }
  }
  
  private static <T> boolean a(ArrayList<WeakReference<T>> paramArrayList, T paramT)
  {
    try
    {
      Iterator localIterator = paramArrayList.iterator();
      Object localObject;
      do
      {
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label56;
          }
          localObject = ((WeakReference)localIterator.next()).get();
          if (localObject != null) {
            break;
          }
          localIterator.remove();
        }
      } while (localObject != paramT);
    }
    finally {}
    return true;
    label56:
    return false;
  }
  
  private void b()
  {
    a(this.c, new b() {});
  }
  
  public static Uri d(String paramString)
  {
    if (ChompSms.a().r()) {
      return Uri.parse(String.format("http://www.amazon.com/gp/mas/dl/android?p=%s", new Object[] { paramString }));
    }
    return Uri.parse(String.format("https://play.google.com/store/apps/details?id=%s", new Object[] { paramString }));
  }
  
  private static String d(Uri paramUri)
  {
    return paramUri.toString().substring(8);
  }
  
  public final int a(String paramString)
  {
    try
    {
      int i = this.h.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return -1;
  }
  
  public final ArrayList<String> a()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.b);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void a(final Uri paramUri)
  {
    try
    {
      paramUri = d(paramUri);
      if (!this.b.contains(paramUri)) {
        this.b.add(paramUri);
      }
      b();
      a(this.d, new b() {});
      return;
    }
    finally {}
  }
  
  public final void a(Object arg1)
  {
    Object localObject3;
    if ((??? instanceof e)) {
      localObject3 = (e)???;
    }
    for (;;)
    {
      synchronized (this.c)
      {
        if (a(this.c, localObject3))
        {
          if ((??? instanceof a)) {
            localObject3 = (a)???;
          }
          synchronized (this.d)
          {
            if (a(this.d, localObject3))
            {
              if ((??? instanceof d)) {
                localObject3 = (d)???;
              }
              synchronized (this.e)
              {
                if (a(this.e, localObject3)) {
                  if ((??? instanceof c)) {
                    ??? = (c)???;
                  }
                }
              }
            }
          }
        }
      }
      synchronized (this.f)
      {
        if (a(this.f, ???))
        {
          return;
          this.c.add(bw.a(localObject3));
          continue;
          ??? = finally;
          throw ???;
          this.d.add(bw.a(localObject3));
          continue;
          ??? = finally;
          throw ???;
          this.e.add(bw.a(localObject3));
          continue;
          ??? = finally;
          throw ???;
        }
        this.f.add(bw.a(???));
        return;
      }
    }
  }
  
  public final boolean a(Collection<String> paramCollection)
  {
    return Util.a(a(), paramCollection);
  }
  
  public final Resources b(String paramString)
  {
    try
    {
      paramString = this.h.getPackageManager().getResourcesForApplication(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public final void b(final Uri paramUri)
  {
    try
    {
      paramUri = d(paramUri);
      b();
      a(this.e, new b() {});
      return;
    }
    finally
    {
      paramUri = finally;
      throw paramUri;
    }
  }
  
  public final void c(final Uri paramUri)
  {
    try
    {
      paramUri = d(paramUri);
      this.b.remove(paramUri);
      b();
      a(this.f, new b() {});
      return;
    }
    finally
    {
      paramUri = finally;
      throw paramUri;
    }
  }
  
  public final boolean c(String paramString)
  {
    return a().contains(paramString);
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString);
  }
  
  public static abstract interface b<T>
  {
    public abstract void a(T paramT);
  }
  
  public static abstract interface c
  {
    public abstract void c(String paramString);
  }
  
  public static abstract interface d
  {
    public abstract void b(String paramString);
  }
  
  public static abstract interface e
  {
    public abstract void l_();
  }
}
