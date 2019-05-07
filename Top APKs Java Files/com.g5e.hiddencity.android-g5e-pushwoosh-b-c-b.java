package g5e.pushwoosh.b.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b
{
  private static final h a = new f(null);
  private static final h b = new e(null);
  private static final h c = new g(null);
  private static final h d = new d(null);
  
  static
  {
    b.a(c);
    c.a(a);
    a.a(d);
  }
  
  public static String a(Context paramContext)
  {
    return b.b(paramContext);
  }
  
  private static String a(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0)) {
      str = "";
    }
    char c1;
    do
    {
      return str;
      c1 = paramString.charAt(0);
      str = paramString;
    } while (Character.isUpperCase(c1));
    return Character.toUpperCase(c1) + paramString.substring(1);
  }
  
  public static boolean a()
  {
    try
    {
      Class.forName("com.amazon.device.messaging.ADM");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return false;
  }
  
  public static long b()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getAvailableBlocks() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  @SuppressLint({"InlinedApi"})
  public static boolean b(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0x4) == 4;
  }
  
  public static float c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      int i = paramContext.getIntExtra("level", -1);
      int j = paramContext.getIntExtra("scale", -1);
      if ((i == -1) || (j == -1)) {
        return -1.0F;
      }
      return i / j * 100.0F;
    }
    catch (Exception paramContext) {}
    return -1.0F;
  }
  
  public static long c()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getBlockCount() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public static int d(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static long d()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getAvailableBlocks() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public static int e(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static long e()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getBlockCount() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public static String f()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return a(str2);
    }
    return a(str1) + " " + str2;
  }
  
  public static String f(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getPackageManager().getInstallerPackageName(str);
  }
  
  public static List g(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((ApplicationInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      return null;
    }
  }
}
