package com.abtnprojects.ambatana.domain.interactor.authentication.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public final class g
{
  private final Context a;
  
  public g(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public final boolean a(String paramString)
  {
    Iterator localIterator = this.a.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
}
