package jp.pp.android.obfuscated.z;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jp.pp.android.obfuscated.x.e;

public final class f
  extends a
{
  public f() {}
  
  public static ArrayList<String> a(Context paramContext, long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
        try
        {
          String str = localApplicationInfo.packageName;
          localArrayList.add(new e(paramContext, (String)localPackageManager.getApplicationLabel(localApplicationInfo), str, Integer.toString(localApplicationInfo.flags), paramLong).c());
        }
        catch (Exception localException) {}
      }
    }
    return localArrayList;
  }
  
  public final boolean a()
  {
    if (c())
    {
      new Thread(new Runnable()
      {
        public final void run()
        {
          ArrayList localArrayList = f.a(f.this.a, f.this.c.getTime());
          if ((localArrayList != null) && (localArrayList.size() > 0)) {
            jp.pp.android.obfuscated.w.f.a(f.this.a, localArrayList, f.this.c);
          }
        }
      }).start();
      return true;
    }
    return false;
  }
}
