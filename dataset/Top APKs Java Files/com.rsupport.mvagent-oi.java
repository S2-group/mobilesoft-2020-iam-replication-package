import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.rsupport.common.log.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class oi
  extends oj
{
  protected Context Zc = null;
  private boolean adk = false;
  
  public oi(Context paramContext)
  {
    this.Zc = paramContext;
  }
  
  private static boolean dZ(String paramString)
  {
    return new File(paramString).canRead();
  }
  
  public final void a(ArrayList<nz> paramArrayList, int paramInt)
  {
    b(paramArrayList, paramInt, -1);
  }
  
  public final void b(ArrayList<nz> paramArrayList, int paramInt1, int paramInt2)
  {
    PackageManager localPackageManager = this.Zc.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {}
      ApplicationInfo localApplicationInfo;
      do
      {
        return;
        localApplicationInfo = (ApplicationInfo)localIterator.next();
      } while (this.adk);
      if (this.Zc.getPackageName().equals(localApplicationInfo.packageName)) {
        continue;
      }
      label92:
      Object localObject;
      if (new File(localApplicationInfo.sourceDir).canRead())
      {
        paramInt1 = 1;
        if (paramInt1 == 0) {
          continue;
        }
        localObject = localApplicationInfo.sourceDir;
        paramInt1 = ((String)localObject).indexOf(File.separator) + 1;
        if (!((String)localObject).substring(paramInt1, ((String)localObject).indexOf(File.separator, paramInt1)).trim().toLowerCase().equals("system")) {
          break label246;
        }
        paramInt1 = 1;
        if (paramInt1 != 0) {
          continue;
        }
        localObject = new oh();
        ((oh)localObject).path = localApplicationInfo.sourceDir;
        ((oh)localObject).packageName = localApplicationInfo.packageName;
        File localFile = new File(((oh)localObject).path);
        if (!localFile.exists()) {
          continue;
        }
        ((oh)localObject).size = localFile.length();
      }
      try
      {
        ((oh)localObject).versionCode = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 128).versionCode;
        paramArrayList.add(localObject);
        continue;
        paramInt1 = 0;
        break label92;
        label246:
        paramInt1 = 0;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          a.d(localNameNotFoundException);
        }
      }
    }
  }
  
  public final void cancel()
  {
    this.adk = true;
  }
  
  public final void destroy()
  {
    this.Zc = null;
  }
}
