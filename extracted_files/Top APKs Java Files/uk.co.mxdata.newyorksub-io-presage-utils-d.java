package io.presage.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.presage.Presage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class d
{
  private static d a = null;
  private Context b;
  private ArrayList<String> c;
  
  private d()
  {
    b();
  }
  
  public static d a()
  {
    if (a == null) {
      a = new d();
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
