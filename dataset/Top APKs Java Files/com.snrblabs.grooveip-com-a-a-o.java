package com.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import java.util.Iterator;
import java.util.List;

final class o
  implements Runnable
{
  o(n paramN, Context paramContext, int paramInt) {}
  
  public final void run()
  {
    for (;;)
    {
      try
      {
        n.a(n.a());
        localObject = this.b.getPackageManager().getInstalledApplications(128);
        n.h().execSQL("DELETE FROM installapplist");
        localObject = ((List)localObject).iterator();
        boolean bool = ((Iterator)localObject).hasNext();
        if (bool) {}
      }
      catch (Throwable localThrowable1)
      {
        Object localObject;
        ApplicationInfo localApplicationInfo;
        ContentValues localContentValues;
        ak.a("===== Background Thread " + ak.a(localThrowable1));
        continue;
      }
      try
      {
        this.a.a(this.c);
        return;
      }
      catch (Throwable localThrowable2)
      {
        ak.a("===== Background Thread " + ak.a(localThrowable2));
      }
      localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (n.h() != null)
      {
        localContentValues = new ContentValues();
        localContentValues.put("packagename", localApplicationInfo.packageName);
        n.h().insert("installapplist", null, localContentValues);
      }
    }
  }
}
