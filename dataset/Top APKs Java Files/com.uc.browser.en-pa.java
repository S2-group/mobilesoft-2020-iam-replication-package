import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.uc.browser.ActivityBrowser;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public final class pa
{
  private static pa a;
  
  public pa() {}
  
  public static pa a()
  {
    if (a == null) {
      a = new pa();
    }
    return a;
  }
  
  public static void a(Object paramObject)
  {
    int j = 0;
    String[] arrayOfString = b();
    Object localObject1 = new Vector(2);
    ((Vector)localObject1).clear();
    int i = 0;
    if (i < arrayOfString.length)
    {
      oz localOz = new oz();
      localOz.a = arrayOfString[i];
      localOz.b = arrayOfString[(i + 1)];
      localOz.c = arrayOfString[(i + 2)];
      if (arrayOfString[(i + 4)] != null) {
        if (!"S".equalsIgnoreCase(arrayOfString[(i + 4)])) {
          break label112;
        }
      }
      label112:
      for (localOz.d = 2;; localOz.d = 1)
      {
        ((Vector)localObject1).add(localOz);
        i += 5;
        break;
      }
    }
    paramObject = new pb(paramObject);
    try
    {
      localObject1 = pu.b((Vector)localObject1);
      arrayOfString = ql.b(ql.a(new ql(pb.b)));
      i = j;
      if (paramObject.a != null) {
        i = paramObject.a.a(arrayOfString[0], arrayOfString[1], (byte[])localObject1);
      }
      if (i == 1) {
        paramObject.a();
      }
      return;
    }
    catch (Exception localException) {}finally
    {
      if (paramObject.a != null) {
        paramObject.a.g();
      }
    }
  }
  
  private static String[] b()
  {
    String[] arrayOfString;
    int i;
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = ActivityBrowser.a().getPackageManager();
        Object localObject = localPackageManager.getInstalledPackages(0);
        if (localObject == null) {
          break;
        }
        arrayOfString = new String[((List)localObject).size() * 5];
        Iterator localIterator = ((List)localObject).iterator();
        i = 0;
        if (localIterator.hasNext())
        {
          localObject = (PackageInfo)localIterator.next();
          if ((((PackageInfo)localObject).packageName == null) || (((PackageInfo)localObject).packageName.trim().length() == 0) || (((PackageInfo)localObject).packageName.trim().startsWith("com.UCMobile"))) {
            continue;
          }
          if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) != 0)
          {
            j = 1;
            arrayOfString[i] = ((PackageInfo)localObject).packageName;
            arrayOfString[(i + 1)] = String.valueOf(((PackageInfo)localObject).versionCode);
            arrayOfString[(i + 2)] = ((PackageInfo)localObject).versionName;
            arrayOfString[(i + 3)] = ((PackageInfo)localObject).applicationInfo.loadLabel(localPackageManager).toString();
            if (j == 0) {
              break label204;
            }
            localObject = "S";
            break label191;
          }
        }
        else
        {
          localObject = new String[i];
          System.arraycopy(arrayOfString, 0, localObject, 0, i);
          return localObject;
        }
      }
      catch (Exception localException)
      {
        return null;
      }
      int j = 0;
    }
    return null;
    for (;;)
    {
      label191:
      arrayOfString[(i + 4)] = localException;
      i += 5;
      break;
      label204:
      String str = "U";
    }
  }
}
