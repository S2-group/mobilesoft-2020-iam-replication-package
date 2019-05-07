package org.hola;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ba
  extends cj
{
  ListView a;
  bd b;
  
  public ba(app paramApp, Activity paramActivity)
  {
    super(paramApp, paramActivity, false, true);
    this.A = LayoutInflater.from(app.f(paramApp)).inflate(2130903065, new RelativeLayout(app.f(paramApp)));
    this.a = ((ListView)this.A.findViewById(2131099787));
    this.a.setOnItemClickListener(new bb(this, paramApp));
  }
  
  private void a(PackageManager paramPackageManager, List paramList1, List paramList2, String paramString1, String paramString2)
  {
    paramList1 = paramList1.iterator();
    while (paramList1.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramList1.next();
      if (localApplicationInfo.packageName.equals(paramString1))
      {
        paramList2.add(new az(this.c, localApplicationInfo.uid, localApplicationInfo.loadLabel(paramPackageManager).toString(), localApplicationInfo.packageName));
        return;
      }
    }
    paramList2.add(new az(this.c, -1, paramString2, paramString1));
  }
  
  public void b()
  {
    PackageManager localPackageManager = app.f(this.c).getPackageManager();
    List localList = localPackageManager.getInstalledApplications(128);
    if (this.b == null) {}
    for (Object localObject = new ArrayList();; localObject = this.b.a)
    {
      ((List)localObject).clear();
      Iterator localIterator = svc.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        a(localPackageManager, localList, (List)localObject, (String)localEntry.getKey(), (String)localEntry.getValue());
      }
    }
    Collections.sort((List)localObject, new bc(this));
    if (this.b == null)
    {
      this.b = new bd(this.c, (List)localObject);
      this.a.setAdapter(this.b);
      return;
    }
    this.b.notifyDataSetChanged();
  }
}
