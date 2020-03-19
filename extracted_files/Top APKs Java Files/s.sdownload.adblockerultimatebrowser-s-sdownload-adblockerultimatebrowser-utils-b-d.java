package s.sdownload.adblockerultimatebrowser.utils.b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class d
  extends android.support.v4.a.a<ArrayList<a>>
{
  private PackageManager o;
  private Intent p;
  private boolean q = false;
  
  public d(Context paramContext, Intent paramIntent, boolean paramBoolean)
  {
    super(paramContext);
    this.o = paramContext.getPackageManager();
    this.p = paramIntent;
    this.q = paramBoolean;
  }
  
  protected void j()
  {
    if (this.p == null) {
      this.q = true;
    }
    l();
  }
  
  public ArrayList<a> v()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject;
    a localA;
    if (this.q)
    {
      localIterator = this.o.getInstalledPackages(128).iterator();
      while (localIterator.hasNext())
      {
        localObject = (PackageInfo)localIterator.next();
        localA = new a();
        localA.a(((PackageInfo)localObject).applicationInfo.loadLabel(this.o).toString());
        localA.b(((PackageInfo)localObject).packageName);
        localA.a(((PackageInfo)localObject).applicationInfo.loadIcon(this.o));
        localA.c(((PackageInfo)localObject).applicationInfo.className);
        localArrayList.add(localA);
      }
    }
    int i = 0;
    if (Build.VERSION.SDK_INT >= 23) {
      i = 131072;
    }
    Iterator localIterator = this.o.queryIntentActivities(this.p, i).iterator();
    while (localIterator.hasNext())
    {
      localObject = (ResolveInfo)localIterator.next();
      localA = new a();
      localA.a(((ResolveInfo)localObject).loadLabel(this.o).toString());
      localA.b(((ResolveInfo)localObject).activityInfo.packageName);
      localA.a(((ResolveInfo)localObject).loadIcon(this.o));
      localA.c(((ResolveInfo)localObject).activityInfo.name);
      localArrayList.add(localA);
    }
    Collections.sort(localArrayList, new b());
    return localArrayList;
  }
}
