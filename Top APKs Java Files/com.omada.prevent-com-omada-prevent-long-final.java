package com.omada.prevent.long;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.omada.prevent.application.PreventApp;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import java.util.Iterator;
import java.util.List;

public final class final
{
  private static final String jdField_do = ;
  
  private final() {}
  
  public static boolean jdMethod_do(Context paramContext)
  {
    String str = do;
    if ((paramContext != null) && (str != null))
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext != null)
      {
        paramContext = paramContext.getInstalledApplications(0);
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
          } while (!str.equals(((ApplicationInfo)paramContext.next()).packageName));
        }
      }
    }
    for (int i = 1; (i != 0) && (PreventApp.const() >= 19); i = 0) {
      return true;
    }
    return false;
  }
  
  private static boolean jdMethod_do(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null))
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext != null)
      {
        paramContext = paramContext.getInstalledApplications(0);
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          while (paramContext.hasNext()) {
            if (paramString.equals(((ApplicationInfo)paramContext.next()).packageName)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
