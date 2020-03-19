package jp.line.android.sdk.a.c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jp.line.android.sdk.activity.WebLoginActivity;
import jp.line.android.sdk.d.d;
import jp.line.android.sdk.e.b;

public final class c
  implements jp.line.android.sdk.e.a, jp.line.android.sdk.e.e
{
  j a;
  private final ExecutorService b = Executors.newCachedThreadPool();
  private List<jp.line.android.sdk.e.f> c;
  
  public c() {}
  
  private final b a(Activity paramActivity, boolean paramBoolean)
  {
    int i = 1;
    int j = 1;
    for (;;)
    {
      try
      {
        if (this.a == null) {
          break;
        }
        if (this.a.isForceEmailLogin() != paramBoolean) {
          break label168;
        }
        i = j;
        switch (d.a[this.a.getProgress().ordinal()])
        {
        case 4: 
        case 7: 
          if (this.a != null) {
            break label179;
          }
          this.a = new j(paramBoolean);
          this.a.addProgressListener(this);
          localJ = this.a;
          label118:
          localJ = this.a;
          if (i != 0) {
            this.b.execute(new h(paramActivity, localJ));
          }
          return localJ;
        }
      }
      finally {}
      this.a = null;
      i = j;
      continue;
      label168:
      this.a = null;
      i = j;
      continue;
      label179:
      localJ = this.a;
    }
    j localJ = n.a().b();
    if ((localJ != null) && (localJ.isForceEmailLogin() == paramBoolean)) {
      switch (d.a[localJ.getProgress().ordinal()])
      {
      }
    }
    for (;;)
    {
      if (this.a == null)
      {
        this.a = new j(paramBoolean);
        this.a.addProgressListener(this);
        localJ = this.a;
        break label118;
        this.a = localJ;
        this.a.addProgressListener(this);
        continue;
      }
      localJ = this.a;
      break label118;
      i = 0;
      break;
    }
  }
  
  static void a(j paramJ, Activity paramActivity)
  {
    try
    {
      switch (d.a[paramJ.getProgress().ordinal()])
      {
      case 1: 
      case 2: 
        paramJ.a(jp.line.android.sdk.e.c.b);
        n.a().a(paramJ);
        jp.line.android.sdk.c.getSdkContext().getApiClient().getOtp(new g(paramActivity, paramJ));
        return;
      }
    }
    catch (Throwable paramActivity)
    {
      paramJ.a(paramActivity);
      return;
    }
    n.a().a(paramJ);
    if (paramActivity == null)
    {
      paramJ.a(new jp.line.android.sdk.d.e(d.a));
      return;
    }
    Object localObject1;
    if (!paramJ.isForceEmailLogin())
    {
      localObject1 = jp.line.android.sdk.c.getSdkContext().getApplicationContext();
      Object localObject2 = ((Context)localObject1).getPackageManager().getInstalledApplications(128).iterator();
      while (((Iterator)localObject2).hasNext()) {
        if ("jp.naver.line.android".equals(((ApplicationInfo)((Iterator)localObject2).next()).packageName))
        {
          i = 1;
          if (i == 0) {
            break label401;
          }
          localObject2 = new Intent("jp.naver.line.android.intent.action.APPAUTH", null);
          if (((Context)localObject1).getPackageManager().queryIntentActivities((Intent)localObject2, 0).isEmpty()) {
            break label401;
          }
        }
      }
    }
    label401:
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        paramJ.a(jp.line.android.sdk.e.c.d);
        localObject1 = new Intent("jp.naver.line.android.intent.action.APPAUTH");
        ((Intent)localObject1).putExtra("channelId", String.valueOf(jp.line.android.sdk.c.getSdkContext().getChannelId()));
        ((Intent)localObject1).putExtra("otpId", paramJ.getOtp().a);
        ((Intent)localObject1).putExtra("appPackage", paramActivity.getPackageName());
        ((Intent)localObject1).putExtra("authScheme", jp.line.android.sdk.c.getSdkContext().getAuthScheme());
        ((Intent)localObject1).addFlags(65536);
        try
        {
          paramActivity.startActivity((Intent)localObject1);
          return;
        }
        catch (Throwable paramActivity)
        {
          paramJ.a(paramActivity);
          return;
        }
      }
      paramJ.a(jp.line.android.sdk.e.c.e);
      WebLoginActivity.a(paramActivity, paramJ);
      return;
      paramJ.a(jp.line.android.sdk.e.c.g);
      n.a().a(paramJ);
      jp.line.android.sdk.c.getSdkContext().getApiClient().getAccessToken(paramJ.getRequestToken().a, paramJ.getOtp().b, new f(paramJ));
      return;
      return;
      i = 0;
      break;
    }
  }
  
  public final void a(jp.line.android.sdk.f.a paramA)
  {
    ArrayList localArrayList = null;
    try
    {
      if (this.c != null) {
        localArrayList = new ArrayList(this.c);
      }
      if ((localArrayList != null) && (localArrayList.size() > 0)) {
        this.b.execute(new e(localArrayList, paramA));
      }
      return;
    }
    finally {}
  }
  
  public final boolean a()
  {
    return this.a != null;
  }
  
  public final b b()
  {
    int i = 0;
    int j = 0;
    j localJ2 = this.a;
    j localJ1 = localJ2;
    if (localJ2 == null) {
      i = j;
    }
    for (;;)
    {
      try
      {
        if (this.a == null)
        {
          this.a = n.a().b();
          i = j;
          if (this.a != null) {
            this.a.addProgressListener(this);
          }
        }
        switch (d.a[this.a.getProgress().ordinal()])
        {
        case 1: 
          localJ1 = this.a;
          if (i != 0) {
            this.b.execute(new i(this, localJ1));
          }
          return localJ1;
        }
      }
      finally {}
      i = 1;
      continue;
      i = j;
    }
  }
  
  public final b login(Activity paramActivity)
  {
    return a(paramActivity, false);
  }
  
  public final void onUpdateProgress(b paramB)
  {
    int j = 0;
    int k = 1;
    i = 1;
    paramB = (j)paramB;
    for (;;)
    {
      try
      {
        int m = d.a[paramB.getProgress().ordinal()];
        switch (m)
        {
        case 4: 
        case 6: 
        case 7: 
        default: 
          i = 0;
        case 9: 
        case 10: 
          k = 0;
          j = i;
          i = k;
        }
      }
      catch (Throwable localThrowable2)
      {
        i = 0;
        continue;
      }
      if (i != 0) {
        a(paramB, null);
      }
      if (j != 0)
      {
        n.a();
        n.c();
      }
      return;
      n.a().a(paramB);
      i = 0;
      continue;
      try
      {
        n.a().a(paramB);
        i = k;
      }
      catch (Throwable localThrowable1)
      {
        i = k;
      }
      continue;
      i = 0;
      j = 1;
    }
  }
}
