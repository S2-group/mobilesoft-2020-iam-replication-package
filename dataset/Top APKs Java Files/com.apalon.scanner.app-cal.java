import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.List;

public final class cal
{
  private static int jdField_do = 50;
  
  public static boolean jdMethod_do(Context paramContext, byh paramByh, cag paramCag)
  {
    long l;
    if (cai.do) {
      l = System.currentTimeMillis();
    } else {
      l = 0L;
    }
    boolean bool = false;
    if ((paramByh != null) && (paramByh.jdField_for != 0) && (paramByh.jdField_if != null))
    {
      if (paramByh.jdField_if.size() == 0) {
        return false;
      }
      if ((paramByh.jdField_do) && (paramCag.jdMethod_for()) && (paramCag.jdMethod_if())) {
        return true;
      }
      paramCag = paramContext.getPackageManager();
      Object localObject = paramByh.jdField_if;
      paramContext = null;
      if (((List)localObject).size() > do) {
        paramContext = paramCag.getInstalledPackages(128);
      }
      localObject = ((List)localObject).iterator();
      int i = 0;
      int j;
      do
      {
        j = i;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        j = i;
        if (jdMethod_do((List)((Iterator)localObject).next(), paramContext, paramCag)) {
          j = i + 1;
        }
        i = j;
      } while (j < paramByh.jdField_for);
      if (cai.do) {
        System.currentTimeMillis();
      }
      if (j < paramByh.jdField_for) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  private static boolean jdMethod_do(List<String> paramList, List<PackageInfo> paramList1, PackageManager paramPackageManager)
  {
    if (paramList != null)
    {
      if (paramList.isEmpty()) {
        return false;
      }
      if (paramList1 == null) {
        paramList = paramList.iterator();
      }
    }
    for (;;)
    {
      if (paramList.hasNext()) {
        paramList1 = (String)paramList.next();
      }
      try
      {
        paramPackageManager.getPackageInfo(paramList1, 128);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramList1) {}
      return false;
      paramList1 = paramList1.iterator();
      while (paramList1.hasNext()) {
        if (paramList.contains(((PackageInfo)paramList1.next()).packageName)) {
          return true;
        }
      }
      return false;
      return false;
    }
  }
}
