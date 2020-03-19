import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class b
  extends boq
{
  private final Context b;
  
  public b(Context paramContext)
  {
    this.b = paramContext;
  }
  
  public final void a()
  {
    if (this.b != null)
    {
      Object localObject = this.b.getPackageManager();
      if (localObject != null)
      {
        localObject = ((PackageManager)localObject).getInstalledPackages(128);
        if (localObject != null)
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
            int i = 0;
            while (i < qt.x.length)
            {
              if (localPackageInfo.packageName.toLowerCase().contains(qt.x[i].toLowerCase())) {
                super.a(String.format(Locale.US, "%s", new Object[] { Integer.toString(i) }));
              }
              i += 1;
            }
          }
        }
      }
    }
  }
  
  public final boolean b()
  {
    return (this.a != null) && (this.a.size() > 0);
  }
}
