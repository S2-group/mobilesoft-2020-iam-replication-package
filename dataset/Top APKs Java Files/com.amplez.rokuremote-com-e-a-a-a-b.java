package com.e.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.e.a.a.c.a;
import com.e.a.d.c;
import java.util.Iterator;
import java.util.List;

public class b
  implements com.e.a.a.b
{
  public b() {}
  
  private boolean a(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.contains(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public c a()
  {
    return c.d;
  }
  
  public boolean a(c.a paramA)
  {
    try
    {
      boolean bool = a("com.htc.cirmodule", paramA.a);
      com.e.a.b.b localB = paramA.b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Check HTC IR interface: ");
      localStringBuilder.append(bool);
      localB.a(localStringBuilder.toString());
      return bool;
    }
    catch (Exception localException)
    {
      paramA.b.a("On HTC ir error", localException);
    }
    return false;
  }
}
