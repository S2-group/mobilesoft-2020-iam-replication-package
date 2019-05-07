package io.presage.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.presage.Presage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class g
{
  private static g a = null;
  private Context b;
  private ArrayList c;
  
  private g()
  {
    b();
  }
  
  public static g a()
  {
    if (a == null) {
      a = new g();
    }
    return a;
  }
  
  private void b()
  {
    this.c = new ArrayList();
    if (this.b == null) {
      this.b = Presage.getInstance().getContext();
    }
    Iterator localIterator = this.b.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      String str = ((PackageInfo)localIterator.next()).packageName;
      if (!this.c.contains(str)) {
        this.c.add(str);
      }
    }
  }
  
  public final boolean a(String paramString)
  {
    return this.c.contains(paramString);
  }
}
