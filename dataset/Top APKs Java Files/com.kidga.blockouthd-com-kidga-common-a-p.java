package com.kidga.common.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.kidga.common.d;
import com.kidga.common.h.a;
import java.util.Iterator;
import java.util.List;

public final class p
{
  a a;
  d b;
  Handler c = new Handler();
  
  public p(a paramA, d paramD)
  {
    this.a = paramA;
    this.b = paramD;
  }
  
  private boolean a(String paramString)
  {
    try
    {
      Object localObject = this.b.e().getPackageManager().getInstalledPackages(0);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          boolean bool = ((PackageInfo)((Iterator)localObject).next()).packageName.equals(paramString);
          if (bool) {
            return true;
          }
        }
      }
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public final boolean a()
  {
    if ((this.a != null) && (this.a.d("removeaddownloadloaded")) && (this.a.d("removeaddownload_accepted")) && (!this.a.o()))
    {
      n localN = n.a(this.a);
      if (a(localN.b))
      {
        this.a.a(true);
        new Thread(new q(this, localN)).start();
        return true;
      }
    }
    return false;
  }
}
