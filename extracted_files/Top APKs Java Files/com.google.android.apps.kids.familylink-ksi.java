import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ksi
  implements ksf
{
  private final kqy a;
  private final kpo b;
  
  ksi(kqy paramKqy, kpo paramKpo)
  {
    this.a = paramKqy;
    this.b = paramKpo;
  }
  
  public final Map a(Context paramContext)
  {
    Object localObject1 = new HashSet();
    try
    {
      Set localSet = this.a.a().keySet();
      localObject1 = localSet;
    }
    catch (kst localKst)
    {
      kph.a.b("PermissionsInfoScanner", localKst, "readAllDeviceStates failed", new Object[0]);
    }
    paramContext = paramContext.getPackageManager();
    Iterator localIterator1 = paramContext.getInstalledPackages(4096).iterator();
    int i = 0;
    while (localIterator1.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator1.next();
      if (qfw.a(paramContext, localPackageInfo) != null)
      {
        Iterator localIterator2 = this.b.a(localPackageInfo).entrySet().iterator();
        int j = 0;
        while (localIterator2.hasNext())
        {
          Object localObject2 = (Map.Entry)localIterator2.next();
          qhy localQhy = qjw.a(qhz.t, String.format("%s - %s", new Object[] { localPackageInfo.packageName, (String)((Map.Entry)localObject2).getKey() }));
          ((Set)localObject1).remove(qjw.a(localQhy));
          boolean bool = ((Boolean)((Map.Entry)localObject2).getValue()).booleanValue();
          localObject2 = this.a;
          rsd localRsd = qib.d.createBuilder();
          localRsd.copyOnWrite();
          qib localQib = (qib)localRsd.instance;
          localQib.b = 1;
          localQib.c = Boolean.valueOf(bool);
          if (((kqy)localObject2).a(localQhy, (qib)localRsd.build())) {
            j += 1;
          }
        }
        i += j;
      }
    }
    paramContext = ((Set)localObject1).iterator();
    while (paramContext.hasNext())
    {
      localObject1 = (String)paramContext.next();
      if (qhz.t == qjw.b((String)localObject1))
      {
        this.a.a((String)localObject1);
        i += 1;
      }
    }
    return pld.a(kse.d, Integer.valueOf(i));
  }
  
  public final boolean a(int paramInt)
  {
    return (paramInt == 3) || (paramInt == 6) || (paramInt == 1);
  }
}
