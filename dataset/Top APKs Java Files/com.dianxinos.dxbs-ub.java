import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.System;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ub
{
  private static Context a;
  private static PackageManager b;
  private static ContentResolver c;
  
  public static List a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = b.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(a(((ApplicationInfo)localIterator.next()).packageName, paramString));
    }
    return localArrayList;
  }
  
  public static sr a(String paramString1, String paramString2)
  {
    return new sr(paramString1, a, paramString2);
  }
  
  public static void a(Context paramContext)
  {
    a = paramContext.getApplicationContext();
    b = a.getPackageManager();
    c = a.getContentResolver();
  }
  
  public static boolean a(sr paramSr)
  {
    Object localObject = Settings.System.getString(c, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}");
    if (localObject == null) {}
    for (;;)
    {
      return false;
      localObject = ((String)localObject).split(",");
      paramSr = ud.a(paramSr.a());
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        if (localObject[i].equals(paramSr)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static void b(sr paramSr)
  {
    paramSr = ud.a(paramSr.a());
    String str = Settings.System.getString(c, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}");
    if (str == null)
    {
      Settings.System.putString(c, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", paramSr);
      return;
    }
    paramSr = str + "," + paramSr;
    Settings.System.putString(c, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", paramSr);
  }
}
