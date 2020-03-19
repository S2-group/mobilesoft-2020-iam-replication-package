package com.mspy.lite.child.sensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import com.mspy.lite.ParentalApplication;
import com.mspy.lite.child.model.dao.ApplicationItemDao;
import com.mspy.lite.common.entity.DeviceType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstalledAppsSensor
  extends BroadcastReceiver
{
  private static final String c = InstalledAppsSensor.class.getSimpleName();
  dagger.a<ApplicationItemDao> a;
  dagger.a<Handler> b;
  
  public InstalledAppsSensor()
  {
    ParentalApplication.b().a(this);
  }
  
  private com.mspy.lite.child.model.a.a a(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    boolean bool = true;
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    com.mspy.lite.child.model.a.a localA = new com.mspy.lite.child.model.a.a();
    localA.a(localApplicationInfo.loadLabel(paramPackageManager).toString());
    localA.b(paramPackageInfo.packageName);
    localA.c(paramPackageInfo.versionName);
    if ((localApplicationInfo.flags & 0x1) == 1) {}
    for (;;)
    {
      localA.b(Boolean.valueOf(bool));
      return localA;
      bool = false;
    }
  }
  
  public static void a()
  {
    ParentalApplication.b().a().sendBroadcast(new Intent("com.mspy.lite.child.sensor.GATHER_ALL_APPS"));
  }
  
  private void a(final Context paramContext)
  {
    ((Handler)this.b.b()).post(new Runnable()
    {
      public void run()
      {
        List localList = InstalledAppsSensor.a(InstalledAppsSensor.this, paramContext);
        ((ApplicationItemDao)InstalledAppsSensor.this.a.b()).b(localList);
        com.mspy.lite.common.b.a.a(InstalledAppsSensor.b(), "Application Items saved to DB. Count: " + localList.size());
      }
    });
  }
  
  private List<com.mspy.lite.child.model.a.a> b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(a(paramContext, (PackageInfo)((Iterator)localObject).next()));
    }
    return localArrayList;
  }
  
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    Object localObject1 = ParentalApplication.a().a();
    Object localObject2 = ((com.mspy.lite.common.c.a)localObject1).e();
    boolean bool = ((com.mspy.lite.common.c.a)localObject1).m();
    if ((!DeviceType.CHILD.equals(localObject2)) || (!bool)) {
      com.mspy.lite.common.b.a.a(c, "Received app broadcast on not initialized device!");
    }
    do
    {
      return;
      localObject2 = paramIntent.getAction();
      if ("com.mspy.lite.child.sensor.GATHER_ALL_APPS".equals(localObject2))
      {
        com.mspy.lite.common.b.a.a(c, "Received GATHER_ALL_APPS broadcast!");
        a(paramContext);
        return;
      }
      localObject1 = paramIntent.getData();
    } while (localObject1 == null);
    localObject1 = ((Uri)localObject1).getSchemeSpecificPart();
    int i = -1;
    switch (((String)localObject2).hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        com.mspy.lite.common.b.a.b(c, "Unknown action: " + (String)localObject2);
        return;
        if (((String)localObject2).equals("android.intent.action.PACKAGE_REMOVED"))
        {
          i = 0;
          continue;
          if (((String)localObject2).equals("android.intent.action.PACKAGE_ADDED")) {
            i = 1;
          }
        }
        break;
      }
    }
    if (paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false))
    {
      com.mspy.lite.common.b.a.a(c, "Application update: " + (String)localObject1);
      return;
    }
    paramContext = new com.mspy.lite.child.model.a.a();
    paramContext.b((String)localObject1);
    paramContext.a(Boolean.valueOf(true));
    for (;;)
    {
      ((Handler)this.b.b()).post(new Runnable()
      {
        public void run()
        {
          ((ApplicationItemDao)InstalledAppsSensor.this.a.b()).d(paramContext);
          com.mspy.lite.common.b.a.a(InstalledAppsSensor.b(), "Application Item saved to DB: " + paramContext);
        }
      });
      return;
      paramContext = paramContext.getPackageManager();
      try
      {
        paramContext = a(paramContext, paramContext.getPackageInfo((String)localObject1, 0));
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        com.mspy.lite.common.b.a.b(c, "Can't find package " + (String)localObject1, paramContext);
      }
    }
  }
}
