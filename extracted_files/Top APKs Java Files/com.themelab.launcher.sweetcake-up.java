import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import java.io.File;
import java.util.List;

public final class up
{
  public static boolean a()
  {
    Object localObject = ud.a();
    PackageManager localPackageManager;
    if (Build.VERSION.SDK_INT > 7) {
      localPackageManager = ((Context)localObject).getPackageManager();
    }
    for (;;)
    {
      try
      {
        int i = localPackageManager.getPackageInfo(((Context)localObject).getPackageName(), 0).applicationInfo.flags;
        return (i & 0x40000) == 262144;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      try
      {
        localObject = ((Context)localObject).getFilesDir().getAbsolutePath();
        if (((String)localObject).startsWith("/data/")) {
          return false;
        }
        if (!((String)localObject).contains("/mnt/"))
        {
          boolean bool = ((String)localObject).contains(Environment.getExternalStorageDirectory().getPath());
          if (bool) {}
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    List localList = ud.a().getPackageManager().getInstalledPackages(8192);
    int j = localList.size();
    int i = 0;
    if (i < j) {
      if (!((PackageInfo)localList.get(i)).packageName.equalsIgnoreCase(paramString)) {}
    }
    for (boolean bool = true;; bool = false)
    {
      new StringBuilder("isAppInstalled(").append(paramString).append(") = ").append(bool);
      return bool;
      i += 1;
      break;
    }
  }
  
  public static boolean b()
  {
    Object localObject = ud.a().getPackageManager();
    try
    {
      localObject = (String)((PackageManager)localObject).getPackageInfo("com.android.vending", 1).applicationInfo.loadLabel((PackageManager)localObject);
      if (localObject != null)
      {
        boolean bool = ((String)localObject).equals("Market");
        if (!bool) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
}
