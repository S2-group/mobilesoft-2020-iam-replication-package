package com.oyo.consumer.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.JobIntentService;
import android.util.Base64;
import awd;
import bpu;
import bpx;
import bqf;
import bqh;
import bqi;
import bqn;
import cif;
import cjb;
import com.crashlytics.android.Crashlytics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import pm;

public class FetchApplicationService
  extends JobIntentService
{
  private static final String j = "FetchApplicationService";
  
  public FetchApplicationService() {}
  
  public static void a(Context paramContext)
  {
    String str = j;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(j);
    localStringBuilder.append(" work enqueued.");
    cif.b(str, localStringBuilder.toString());
    a(paramContext, FetchApplicationService.class, 1000, new Intent());
  }
  
  private boolean e()
  {
    long l = bpu.K();
    return System.currentTimeMillis() - l > 1728000000L;
  }
  
  protected void a(Intent paramIntent)
  {
    paramIntent = j;
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(j);
    ((StringBuilder)localObject1).append(" work executed.");
    cif.b(paramIntent, ((StringBuilder)localObject1).toString());
    if (bpx.b())
    {
      if (!e()) {
        return;
      }
      localObject1 = getPackageManager();
      paramIntent = new StringBuilder();
      try
      {
        Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(0).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
          if (a(localApplicationInfo))
          {
            paramIntent.append(localApplicationInfo.loadLabel((PackageManager)localObject1));
            paramIntent.append(',');
          }
        }
        try
        {
          pm localPm;
          paramIntent = cjb.l(cjb.m(paramIntent.toString()));
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("ls", new String(Base64.encode(paramIntent.getBytes(), 0)).replace("\n", ""));
          paramIntent = new bqh(awd.class, 1, bqi.C(), bqf.a(), ((JSONObject)localObject2).toString(), null, localPm, localPm);
          bqn.a().a(paramIntent);
          if ((awd)localPm.get(20L, TimeUnit.SECONDS) != null) {
            bpu.c(System.currentTimeMillis());
          }
          return;
        }
        catch (Exception paramIntent) {}
      }
      catch (Exception localException)
      {
        Crashlytics.logException(localException);
        if (paramIntent.length() > 0)
        {
          paramIntent.deleteCharAt(paramIntent.length() - 1);
          localPm = pm.a();
        }
      }
    }
    else {}
  }
  
  boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x81) == 0;
  }
}
