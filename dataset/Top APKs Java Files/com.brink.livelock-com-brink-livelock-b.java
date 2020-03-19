package com.brink.livelock;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class b
  extends AsyncTask<Void, Void, List<c>>
{
  private InstalledAppsActivity a;
  private List<c> b;
  private PackageManager c;
  
  public b(InstalledAppsActivity paramInstalledAppsActivity, List<c> paramList, PackageManager paramPackageManager)
  {
    this.a = paramInstalledAppsActivity;
    this.b = paramList;
    this.c = paramPackageManager;
  }
  
  private List<c> b(List<c> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      c localC = (c)paramList.next();
      try
      {
        if ((this.c.getLaunchIntentForPackage(localC.a()) != null) && (!localC.a().trim().equals("com.brink.livelock"))) {
          localArrayList.add(localC);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  protected List<c> a(Void... paramVarArgs)
  {
    Object localObject = this.c.getInstalledApplications(128);
    paramVarArgs = new ArrayList(((List)localObject).size());
    HashMap localHashMap = new com.brink.livelock.b.b(this.a).a();
    localObject = ((List)localObject).iterator();
    if (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      Boolean localBoolean = (Boolean)localHashMap.get(localApplicationInfo.packageName);
      if ((localBoolean != null) && (localBoolean.booleanValue())) {}
      for (boolean bool = true;; bool = false)
      {
        paramVarArgs.add(new c(localApplicationInfo.packageName, localApplicationInfo.loadLabel(this.c).toString(), bool, localApplicationInfo, this.c, this.a));
        break;
      }
    }
    this.b = b(paramVarArgs);
    return this.b;
  }
  
  protected void a(List<c> paramList)
  {
    super.onPostExecute(paramList);
    this.a.a(paramList);
  }
}
