package de.olbu.android.moviecollection.j;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public final class a
{
  public static final class a
  {
    public static final boolean a(Context paramContext, Class<?> paramClass)
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext()) {
        if (((ApplicationInfo)paramContext.next()).packageName.equals(paramClass.getPackage().getName())) {
          return true;
        }
      }
      return false;
    }
  }
}
