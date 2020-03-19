import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class bqi
{
  public bqh a = null;
  public bqf b;
  
  public bqi(Context paramContext, bqf paramBqf)
  {
    this.b = paramBqf;
    paramBqf = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    label93:
    while (paramBqf.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramBqf.next();
      String str = localApplicationInfo.packageName;
      if (bqd.f) {}
      for (paramContext = "com.vaultmicro.camerafi2";; paramContext = "com.vaultmicro.camerafi.live")
      {
        if (!str.equals(paramContext)) {
          break label93;
        }
        this.a = new bqh(localApplicationInfo.uid);
        break;
      }
    }
  }
  
  public ArrayList<Long> a(boolean paramBoolean)
  {
    Object localObject2 = null;
    if (this.b == null) {}
    long l1;
    long l2;
    do
    {
      return null;
      l1 = this.b.R();
      l2 = this.b.S();
    } while (this.a == null);
    Object localObject1 = localObject2;
    if (l1 != 0L)
    {
      localObject1 = localObject2;
      if (l2 != 0L)
      {
        Log.d("bmwData", "previous_rx : " + String.valueOf(l1) + " previous_tx : " + String.valueOf(l2));
        Log.d("bmwData", "delta_rx : " + String.valueOf(this.a.b - l1) + " delta_tx : " + String.valueOf(this.a.a - l2));
        localObject1 = new ArrayList();
        ((ArrayList)localObject1).add(0, Long.valueOf(this.a.b - l1));
        ((ArrayList)localObject1).add(1, Long.valueOf(this.a.a - l2));
      }
    }
    Log.d("bmwData", "latest_rx : " + String.valueOf(this.a.b) + " latest_tx : " + String.valueOf(this.a.a));
    this.b.c(this.a.b);
    this.b.d(this.a.a);
    Log.d("bmwData", "\n");
    return localObject1;
  }
}
