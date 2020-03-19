package it.medieval.blueftp.applications;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import it.medieval.blueftp.at;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class e
  extends AsyncTask<Boolean, Void, Void>
{
  private final PackageManager a;
  private final c b;
  private final a c;
  
  public e(Context paramContext, PackageManager paramPackageManager, c paramC, a paramA)
  {
    this.a = paramPackageManager;
    this.b = paramC;
    this.c = paramA;
    at.a(paramContext);
  }
  
  protected final Void a(Boolean... paramVarArgs)
  {
    for (;;)
    {
      try
      {
        at.d();
        if ((paramVarArgs != null) && (paramVarArgs.length > 0) && (paramVarArgs[0].booleanValue()))
        {
          i = 1;
          paramVarArgs = new ArrayList();
          Iterator localIterator = this.a.getInstalledApplications(0).iterator();
          if (localIterator.hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            if ((i != 0) && (b.a(localApplicationInfo))) {
              continue;
            }
            paramVarArgs.add(new b(this.a, localApplicationInfo));
            continue;
          }
          Collections.sort(paramVarArgs);
          this.c.a(paramVarArgs);
          return null;
        }
      }
      finally
      {
        at.e();
      }
      int i = 0;
    }
  }
  
  protected final void a(Void paramVoid)
  {
    super.onPostExecute(paramVoid);
    this.c.f();
    paramVoid = this.b;
    if (paramVoid != null) {
      paramVoid.b();
    }
  }
}
