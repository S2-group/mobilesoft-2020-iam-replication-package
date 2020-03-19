package de.etroop.droid;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import de.etroop.droid.g.i;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"DefaultLocale"})
public class m
{
  private static boolean b;
  private static boolean c;
  private b a;
  
  public m(b paramB)
  {
    this.a = paramB;
    n();
  }
  
  public static int i()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String j()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String k()
  {
    return Build.MODEL;
  }
  
  public static String l()
  {
    return Build.MANUFACTURER;
  }
  
  public static String m()
  {
    return Build.BRAND;
  }
  
  private void n()
  {
    c = false;
    b = false;
    Iterator localIterator = this.a.getPackageManager().getInstalledPackages(8192).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (localIterator.hasNext())
      {
        localPackageInfo = (PackageInfo)localIterator.next();
        if (("com.android.vending".equals(localPackageInfo.packageName)) || ("com.google.market".equals(localPackageInfo.packageName))) {
          b = true;
        }
      }
      else
      {
        return;
      }
    } while (!"com.amazon.venezia".equals(localPackageInfo.packageName));
    c = true;
  }
  
  public void a(b paramB)
  {
    this.a = paramB;
  }
  
  public boolean a()
  {
    return (this.a.getResources().getConfiguration().screenLayout & 0xF) == 1;
  }
  
  public boolean a(int paramInt)
  {
    return c() > paramInt;
  }
  
  public boolean b()
  {
    return (this.a.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public boolean b(int paramInt)
  {
    return e() > paramInt;
  }
  
  public int c()
  {
    DisplayMetrics localDisplayMetrics = this.a.getResources().getDisplayMetrics();
    return (int)x.c.b(localDisplayMetrics.heightPixels);
  }
  
  public int d()
  {
    return this.a.getResources().getDisplayMetrics().widthPixels;
  }
  
  public int e()
  {
    return (int)x.c.b(d());
  }
  
  public boolean f()
  {
    DisplayMetrics localDisplayMetrics = this.a.getResources().getDisplayMetrics();
    return localDisplayMetrics.widthPixels > localDisplayMetrics.heightPixels;
  }
  
  public boolean g()
  {
    return Build.MANUFACTURER.toLowerCase().equals("amazon");
  }
  
  public boolean h()
  {
    return c;
  }
}
