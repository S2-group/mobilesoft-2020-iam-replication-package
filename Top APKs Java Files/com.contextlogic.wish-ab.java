import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class ab
  extends q
{
  private final Context b;
  
  public ab(Context paramContext)
  {
    this.b = paramContext;
  }
  
  public final boolean b()
  {
    return (this.a != null) && (this.a.size() > 0);
  }
  
  public final void c()
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
            while (i < c.z.length)
            {
              if (localPackageInfo.packageName.toLowerCase().contains(c.z[i].toLowerCase())) {
                super.a(String.format(Locale.US, "%s", new Object[] { Integer.toString(i) }));
              }
              i += 1;
            }
          }
        }
      }
    }
  }
}
