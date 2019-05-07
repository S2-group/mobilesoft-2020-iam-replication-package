package com.lookout.security.progressbar;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.AsyncTask;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.a.c;

class b
  extends AsyncTask
{
  private static final org.a.b d = c.a(b.class);
  private final a a;
  private final int b;
  private final PackageManager c;
  private final Drawable.ConstantState e;
  
  public b(a paramA, int paramInt)
  {
    this.a = paramA;
    this.b = paramInt;
    this.c = this.a.c().getPackageManager();
    this.e = this.c.getDefaultActivityIcon().getConstantState();
  }
  
  private boolean a(Drawable paramDrawable)
  {
    return this.e.equals(paramDrawable.getConstantState());
  }
  
  protected Collection a(Void... paramVarArgs)
  {
    paramVarArgs = new LinkedList();
    try
    {
      Object localObject = this.c.getInstalledPackages(0);
      int i = ((List)localObject).size();
      if (i < this.b) {}
      for (;;)
      {
        localObject = ((List)localObject).iterator();
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          } while ((localPackageInfo == null) || (a(this.c.getApplicationIcon(localPackageInfo.applicationInfo))));
          paramVarArgs.add(localPackageInfo.packageName);
        } while (paramVarArgs.size() != i);
        return paramVarArgs;
        i = this.b;
      }
      return paramVarArgs;
    }
    catch (RuntimeException localRuntimeException)
    {
      d.c("Failed to get installed packages", localRuntimeException);
    }
  }
  
  protected void a(Collection paramCollection)
  {
    this.a.a(paramCollection);
  }
}
