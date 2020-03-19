package com.cyou.elegant.appmarket;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import com.cyou.elegant.d;
import com.cyou.elegant.model.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

final class c
  extends AsyncTask<Void, Void, List<b>>
{
  private c(ManagerTabFragment paramManagerTabFragment) {}
  
  private List<b> a()
  {
    if (this.a.getActivity() == null) {}
    Object localObject;
    do
    {
      return null;
      localObject = this.a.getActivity().getPackageManager().getInstalledApplications(0);
    } while ((localObject == null) || (((List)localObject).isEmpty()));
    ArrayList localArrayList = new ArrayList();
    try
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((!d.a().c(localApplicationInfo.packageName)) && ((localApplicationInfo.flags & 0x1) == 0)) {
          localArrayList.add(b.a(localApplicationInfo, this.a.getActivity()));
        }
      }
      if (localArrayList.isEmpty()) {
        return localArrayList;
      }
      Collections.sort(localArrayList, new Comparator() {});
    }
    catch (Exception localException) {}
    return localArrayList;
  }
}
