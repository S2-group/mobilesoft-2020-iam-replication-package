package com.cyou.elegant.appmarket;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import com.cyou.elegant.model.ʼ;
import com.cyou.elegant.ˉ;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class ʽ
  extends AsyncTask<Void, Void, List<ʼ>>
{
  private ʽ(ManagerTabFragment paramManagerTabFragment) {}
  
  private List<ʼ> ʻ()
  {
    if (this.ʻ.getActivity() == null) {}
    Object localObject;
    do
    {
      return null;
      localObject = this.ʻ.getActivity().getPackageManager().getInstalledApplications(0);
    } while ((localObject == null) || (((List)localObject).isEmpty()));
    ArrayList localArrayList = new ArrayList();
    try
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((!ˉ.ʻ().ʽ(localApplicationInfo.packageName)) && ((localApplicationInfo.flags & 0x1) == 0)) {
          localArrayList.add(ʼ.ʻ(localApplicationInfo, this.ʻ.getActivity()));
        }
      }
      if (localArrayList.isEmpty()) {
        return localArrayList;
      }
      Collections.sort(localArrayList, new ʾ(this));
    }
    catch (Exception localException) {}
    return localArrayList;
  }
}
