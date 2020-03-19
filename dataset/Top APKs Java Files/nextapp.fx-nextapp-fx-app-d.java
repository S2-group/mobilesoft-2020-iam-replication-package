package nextapp.fx.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import nextapp.maui.k.c;

public class d
{
  private int a = 0;
  private int b = 0;
  
  public d(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (nextapp.maui.k.d.c()) {
        throw new c();
      }
      if ((localApplicationInfo.flags & 0x1) == 0) {
        this.b += 1;
      } else {
        this.a += 1;
      }
    }
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
}
