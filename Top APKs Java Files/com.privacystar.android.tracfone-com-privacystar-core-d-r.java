package com.privacystar.core.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public final class r
{
  public static Vector<String> a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new Vector();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return paramContext;
  }
}
