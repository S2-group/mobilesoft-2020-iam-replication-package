package com.lookout.stub;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.text.TextUtils;
import com.lookout.androidsecurity.i.e;
import com.lookout.services.s;
import java.util.Iterator;
import java.util.List;

public class StubAccountRegistrationService
  extends IntentService
  implements s
{
  private static final org.a.b a = org.a.c.a(StubAccountRegistrationService.class);
  private b b = new f(this);
  
  public StubAccountRegistrationService()
  {
    super("StubAccountRegistrationService");
  }
  
  private boolean a(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    paramContext = paramContext.getPackageManager();
    localObject = paramContext.getInstalledPackages(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!"com.lookout".equals(localPackageInfo.packageName)) && (!"com.kddi.android.easysettingwizard".equals(localPackageInfo.packageName)) && (paramContext.checkPermission("com.lookout.permission.REGISTER_ACCOUNT", localPackageInfo.packageName) == 0)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean a(String paramString, List paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (paramString.contains((String)paramList.next())) {
        return true;
      }
    }
    return false;
  }
  
  public Context a()
  {
    return this;
  }
  
  public void b()
  {
    Context localContext = a();
    if ((e.a().a(localContext.getPackageManager(), "com.lookout", "com.lookout.permission.REGISTER_ACCOUNT")) && (a(localContext)) && (c.a().a("com.kddi.android.easysettingwizard"))) {
      c.a().b();
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.b;
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    paramIntent = paramIntent.getAction();
    if ((!TextUtils.isEmpty(paramIntent)) && (paramIntent.equals("registerStubAccount"))) {
      b();
    }
  }
}
