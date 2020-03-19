package com.noahmob.adhub.noahmob;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.noahmob.Sdk;
import com.noahmob.util.NetworkUtils;
import com.noahmob.util.d;
import com.noahmob.util.g;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.w;
import okhttp3.y;
import okhttp3.y.a;

class b
{
  private static ScheduledExecutorService d = Executors.newScheduledThreadPool(1);
  private static Gson e = new Gson();
  private int a;
  private String b;
  private String c;
  private Future f;
  private ScheduledFuture<?> g;
  private List<Ad> h;
  private Context i;
  private int j;
  private String k;
  private b l;
  
  b(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    this.j = paramInt;
    this.k = paramString2;
    this.i = paramContext;
    paramString2 = new StringBuilder();
    paramString2.append(paramContext.getCacheDir());
    paramString2.append("/ad");
    paramString2.append("/");
    paramString2.append(d.a(paramString1));
    this.b = paramString2.toString();
    this.c = paramString1;
    this.a = Math.abs(new Random().nextInt());
  }
  
  private String a(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso();
  }
  
  private void a(List<Ad> paramList)
  {
    if (paramList == null) {
      return;
    }
    Object localObject1 = a(this.i);
    Object localObject2;
    Object localObject3;
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject2 = paramList.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Ad)((Iterator)localObject2).next();
        if ((!((Ad)localObject3).ad_country.equals("all")) && (!((Ad)localObject3).ad_country.equals("null")) && (!((Ad)localObject3).ad_country.equals("")) && (!Arrays.asList(((Ad)localObject3).ad_country.split(",")).contains(localObject1))) {
          ((Iterator)localObject2).remove();
        }
      }
    }
    localObject1 = this.i.getPackageManager().getInstalledPackages(0);
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return;
      }
      localObject2 = (Ad)paramList.next();
      if ((this.l != null) && (this.l.a((Ad)localObject2)))
      {
        paramList.remove();
      }
      else
      {
        localObject3 = ((List)localObject1).iterator();
        if (((Iterator)localObject3).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
          if (!((Ad)localObject2).ad_package.equals(localPackageInfo.packageName)) {
            break;
          }
          paramList.remove();
        }
      }
    }
  }
  
  private boolean f()
  {
    return this.g.getDelay(TimeUnit.MILLISECONDS) < 0L;
  }
  
  private boolean g()
  {
    return (this.f != null) && (!this.f.isDone());
  }
  
  Ad a()
  {
    List localList = this.h;
    if ((localList != null) && (localList.size() != 0))
    {
      boolean bool = NetworkUtils.a();
      int m = this.j;
      String str = this.k;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ad list loader network ");
      localStringBuilder.append(bool);
      g.a(m, str, localStringBuilder.toString());
      if (!bool)
      {
        n = localList.size();
        m = 100;
        if (n <= 100) {
          m = localList.size();
        }
        m = (this.a + 1) % m;
      }
      else
      {
        m = (this.a + 1) % localList.size();
      }
      this.a = m;
      int n = this.j;
      str = this.k;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.c);
      localStringBuilder.append(" loading ad at index: ");
      localStringBuilder.append(this.a);
      g.a(n, str, localStringBuilder.toString());
      return (Ad)localList.get(m);
    }
    return null;
  }
  
  void a(b paramB)
  {
    this.l = paramB;
  }
  
  public int b()
  {
    if (this.h != null) {
      return this.h.size();
    }
    return 0;
  }
  
  Ad c()
  {
    if ((this.h != null) && (this.h.size() != 0)) {
      return a();
    }
    Object localObject;
    if (f())
    {
      try
      {
        this.g.get();
        if (this.h != null)
        {
          if (this.h.size() == 0) {
            return null;
          }
          Ad localAd = a();
          return localAd;
        }
        return null;
      }
      catch (ExecutionException localExecutionException1)
      {
        localExecutionException1.printStackTrace();
      }
      catch (InterruptedException localInterruptedException1)
      {
        localInterruptedException1.printStackTrace();
      }
      m = this.j;
      localObject = this.k;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.c);
      localStringBuilder.append(" fetch remote ad list fail");
      g.a(m, (String)localObject, localStringBuilder.toString());
      return null;
    }
    if (g())
    {
      try
      {
        this.f.get();
        if (this.h != null)
        {
          if (this.h.size() == 0) {
            return null;
          }
          localObject = a();
          return localObject;
        }
        return null;
      }
      catch (ExecutionException localExecutionException2)
      {
        localExecutionException2.printStackTrace();
      }
      catch (InterruptedException localInterruptedException2)
      {
        localInterruptedException2.printStackTrace();
      }
      m = this.j;
      str = this.k;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.c);
      localStringBuilder.append(" after load remote ad list fail,load local ad list success");
      g.a(m, str, localStringBuilder.toString());
      return null;
    }
    int m = this.j;
    String str = this.k;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.c);
    localStringBuilder.append(" fail to load ad list");
    g.a(m, str, localStringBuilder.toString());
    return null;
  }
  
  void d()
  {
    File localFile = new File(this.b);
    boolean bool = localFile.exists();
    long l2 = 0L;
    long l1;
    if (bool)
    {
      l1 = localFile.lastModified();
      this.f = d.submit(new Runnable()
      {
        public void run()
        {
          Object localObject = com.noahmob.util.e.a(b.a(b.this));
          localObject = ((AdResponse)new Gson().fromJson((String)localObject, AdResponse.class)).data;
          b.a(b.this, (List)localObject);
          b.b(b.this, (List)localObject);
        }
      });
    }
    else
    {
      l1 = 0L;
    }
    if (System.currentTimeMillis() - l1 < 60000000L) {
      l2 = 60000000L - (System.currentTimeMillis() - l1);
    }
    this.g = d.scheduleAtFixedRate(new a(null), l2, 60000000L, TimeUnit.MILLISECONDS);
  }
  
  private class a
    implements Runnable
  {
    private a() {}
    
    public void run()
    {
      if (Sdk.okHttpClient == null) {
        return;
      }
      Object localObject1 = Sdk.okHttpClient;
      if (localObject1 == null) {
        g.a(b.b(b.this), b.c(b.this), "okhttpclient is null");
      }
      Object localObject2 = new y.a().a(b.d(b.this)).a();
      try
      {
        localObject1 = ((w)localObject1).a((y)localObject2).a();
        if (((aa)localObject1).d())
        {
          localObject1 = ((aa)localObject1).h().e();
          localObject2 = (AdResponse)b.e().fromJson((String)localObject1, AdResponse.class);
          if ((((AdResponse)localObject2).code == 0) && (((AdResponse)localObject2).data.size() > 0))
          {
            i = b.b(b.this);
            String str = b.c(b.this);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(b.d(b.this));
            localStringBuilder.append(" fetch ad list success");
            g.a(i, str, localStringBuilder.toString());
            b.a(b.this, ((AdResponse)localObject2).data);
            if (((AdResponse)localObject2).data.size() == 0)
            {
              i = b.b(b.this);
              localObject1 = b.c(b.this);
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(b.d(b.this));
              ((StringBuilder)localObject2).append(" After filter, no ad are useful");
              g.a(i, (String)localObject1, ((StringBuilder)localObject2).toString());
              return;
            }
            b.b(b.this, ((AdResponse)localObject2).data);
            com.noahmob.util.e.a(b.a(b.this), (String)localObject1);
            return;
          }
          i = b.b(b.this);
          localObject1 = b.c(b.this);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(b.d(b.this));
          ((StringBuilder)localObject2).append(" fetch ad list api level response fail");
          g.a(i, (String)localObject1, ((StringBuilder)localObject2).toString());
          return;
        }
        int i = b.b(b.this);
        localObject1 = b.c(b.this);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(b.d(b.this));
        ((StringBuilder)localObject2).append(" fetch ad list http level response fail");
        g.a(i, (String)localObject1, ((StringBuilder)localObject2).toString());
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        g.a(b.b(b.this), b.c(b.this), localIOException.getMessage());
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract boolean a(Ad paramAd);
  }
}
