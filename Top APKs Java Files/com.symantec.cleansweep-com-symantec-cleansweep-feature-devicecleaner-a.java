package com.symantec.cleansweep.feature.devicecleaner;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import java.util.Iterator;
import java.util.List;

public class a
  extends AsyncTask<Object, Object, Cursor>
{
  private Context a;
  
  public a(Context paramContext)
  {
    this.a = paramContext;
  }
  
  protected Cursor a(Object... paramVarArgs)
  {
    paramVarArgs = this.a.getPackageManager();
    Iterator localIterator = paramVarArgs.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((localApplicationInfo.flags & 0x81) == 0) {
        new AppInfoCache(this.a).a(localApplicationInfo.packageName, paramVarArgs.getApplicationLabel(localApplicationInfo).toString());
      }
    }
    return null;
  }
}
