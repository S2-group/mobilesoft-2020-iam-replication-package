package com.droid.developer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import jp.line.android.sdk.activity.WebLoginActivity;

public final class ckl
  implements clf, cli
{
  public final ExecutorService ˇ = Executors.newCachedThreadPool();
  public ckn ˇˇ;
  public List<clj> ˇˇˇ;
  
  public ckl() {}
  
  private final clg ˇ(Activity paramActivity, Locale paramLocale, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        ckn localCkn = this.ˇˇ;
        i = 1;
        int j = 1;
        int k;
        if (localCkn != null) {
          if (this.ˇˇ.ˇ(paramBoolean, paramLocale))
          {
            k = ckl.1.ˇ[this.ˇˇ.ˇˇ.ordinal()];
            i = j;
            if (k == 4) {
              continue;
            }
            i = j;
          }
        }
        switch (k)
        {
        case 8: 
        case 9: 
        case 10: 
          this.ˇˇ = null;
          i = j;
        case 7: 
          if (this.ˇˇ == null)
          {
            this.ˇˇ = new ckn(paramBoolean, paramLocale);
            this.ˇˇ.ˇ(this);
          }
          continue;
          localCkn = ckq.ˇ().ˇˇ();
          if ((localCkn != null) && (localCkn.ˇ(paramBoolean, paramLocale))) {}
          switch (ckl.1.ˇ[localCkn.ˇˇ.ordinal()])
          {
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
          case 7: 
            this.ˇˇ = localCkn;
            this.ˇˇ.ˇ(this);
            if (this.ˇˇ == null)
            {
              this.ˇˇ = new ckn(paramBoolean, paramLocale);
              this.ˇˇ.ˇ(this);
            }
            paramLocale = this.ˇˇ;
            if (i != 0) {
              this.ˇ.execute(new ˉˉ(paramActivity, paramLocale));
            }
            return paramLocale;
          }
          break;
        }
      }
      finally {}
      int i = 0;
    }
  }
  
  static void ˇ(ckn paramCkn, Activity paramActivity)
  {
    int i;
    try
    {
      switch (ckl.1.ˇ[paramCkn.ˇˇ.ordinal()])
      {
      case 5: 
      case 6: 
        paramCkn.ˇ(clg.ˇ.ˉˉˉ);
        ckq.ˇ().ˇ(paramCkn);
        cjg.ˇ().ˇˇ().ˇ(paramCkn.ˉˉ.ˇ, paramCkn.ˉ.ˇˇ, new ˇˇˇ(paramCkn));
        return;
      }
    }
    catch (Throwable paramActivity)
    {
      Object localObject1;
      Object localObject2;
      paramCkn.ˇ(paramActivity);
      return;
    }
    ckq.ˇ().ˇ(paramCkn);
    if (paramActivity == null)
    {
      paramCkn.ˇ(new cle(cld.ˇ));
      return;
    }
    if (!paramCkn.ˇˇˇ)
    {
      localObject1 = cjg.ˇ().ˇ();
      localObject2 = ((Context)localObject1).getPackageManager().getInstalledApplications(128).iterator();
      for (;;)
      {
        boolean bool = ((Iterator)localObject2).hasNext();
        int j = 1;
        if (!bool) {
          break label418;
        }
        if ("jp.naver.line.android".equals(((ApplicationInfo)((Iterator)localObject2).next()).packageName))
        {
          i = 1;
          if (i == 0) {
            break label423;
          }
          localObject2 = new Intent("jp.naver.line.android.intent.action.APPAUTH", null);
          if (((Context)localObject1).getPackageManager().queryIntentActivities((Intent)localObject2, 0).isEmpty()) {
            break label423;
          }
          i = j;
        }
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        paramCkn.ˇ(clg.ˇ.ˇˇˇˇ);
        localObject1 = new Intent("jp.naver.line.android.intent.action.APPAUTH");
        ((Intent)localObject1).putExtra("channelId", String.valueOf(cjg.ˇ().ˇˇˇˇ()));
        ((Intent)localObject1).putExtra("otpId", paramCkn.ˉ.ˇ);
        ((Intent)localObject1).putExtra("appPackage", paramActivity.getPackageName());
        ((Intent)localObject1).putExtra("authScheme", cjg.ˇ().ˉ());
        ((Intent)localObject1).addFlags(65536);
        try
        {
          paramActivity.startActivity((Intent)localObject1);
          return;
        }
        catch (Throwable paramActivity)
        {
          paramCkn.ˇ(paramActivity);
          return;
        }
      }
      paramCkn.ˇ(clg.ˇ.ˉ);
      WebLoginActivity.ˇ(paramActivity, paramCkn);
      return;
      paramCkn.ˇ(clg.ˇ.ˇˇ);
      ckq.ˇ().ˇ(paramCkn);
      cjg.ˇ().ˇˇ().ˇ(new ˇˇˇˇ(paramActivity, paramCkn));
      return;
      return;
      label418:
      i = 0;
      break;
      label423:
      i = 0;
    }
  }
  
  private void ˇ(clk paramClk)
  {
    for (;;)
    {
      try
      {
        if (this.ˇˇˇ != null)
        {
          localArrayList = new ArrayList(this.ˇˇˇ);
          if ((localArrayList != null) && (localArrayList.size() > 0)) {
            this.ˇ.execute(new ˇ(localArrayList, paramClk));
          }
          return;
        }
      }
      finally {}
      ArrayList localArrayList = null;
    }
  }
  
  private boolean ˉ()
  {
    return this.ˇˇ != null;
  }
  
  public final clg ˇ()
  {
    ckn localCkn3 = this.ˇˇ;
    int i = 0;
    int j = 0;
    ckn localCkn1 = localCkn3;
    if (localCkn3 == null) {
      i = j;
    }
    for (;;)
    {
      try
      {
        if (this.ˇˇ == null)
        {
          this.ˇˇ = ckq.ˇ().ˇˇ();
          i = j;
          if (this.ˇˇ != null) {
            this.ˇˇ.ˇ(this);
          }
        }
        switch (ckl.1.ˇ[this.ˇˇ.ˇˇ.ordinal()])
        {
        default: 
          localCkn1 = this.ˇˇ;
        }
      }
      finally {}
      if (i != 0) {
        this.ˇ.execute(new ckm(this, localCkn2));
      }
      return localCkn2;
      i = j;
      continue;
      i = 1;
    }
  }
  
  public final clg ˇ(Activity paramActivity)
  {
    return ˇ(paramActivity, Locale.getDefault(), false);
  }
  
  public final clg ˇ(Activity paramActivity, Locale paramLocale)
  {
    return ˇ(paramActivity, paramLocale, false);
  }
  
  public final void ˇ(clg paramClg)
  {
    paramClg = (ckn)paramClg;
    int m = 0;
    int k = 0;
    int j = 1;
    int i = 1;
    for (;;)
    {
      try
      {
        int n = ckl.1.ˇ[paramClg.ˇˇ.ordinal()];
        if (n != 3) {
          if (n != 5)
          {
            i = m;
            switch (n)
            {
            default: 
              i = k;
            }
          }
        }
      }
      catch (Throwable localThrowable1)
      {
        continue;
      }
      try
      {
        ckq.ˇ().ˇ(paramClg);
        i = 1;
      }
      catch (Throwable localThrowable2) {}
    }
    ckq.ˇ().ˇ(paramClg);
    i = k;
    break label105;
    i = 0;
    label105:
    j = 0;
    if (i != 0) {
      ˇ(paramClg, null);
    }
    if (j != 0)
    {
      ckq.ˇ();
      ckq.ˇˇˇ();
    }
  }
  
  public final boolean ˇ(clj paramClj)
  {
    try
    {
      if (this.ˇˇˇ == null) {
        this.ˇˇˇ = new ArrayList();
      }
      if (!this.ˇˇˇ.contains(paramClj))
      {
        boolean bool = this.ˇˇˇ.add(paramClj);
        return bool;
      }
      return false;
    }
    finally {}
  }
  
  public final clg ˇˇ(Activity paramActivity)
  {
    return ˇ(paramActivity, Locale.getDefault(), true);
  }
  
  public final clg ˇˇ(Activity paramActivity, Locale paramLocale)
  {
    return ˇ(paramActivity, paramLocale, true);
  }
  
  public final Future<?> ˇˇ()
  {
    ˇˇ localˇˇ = new ˇˇ();
    this.ˇ.execute(localˇˇ);
    return localˇˇ;
  }
  
  public final boolean ˇˇ(clj paramClj)
  {
    try
    {
      if ((this.ˇˇˇ != null) && (this.ˇˇˇ.contains(paramClj)))
      {
        boolean bool = this.ˇˇˇ.remove(paramClj);
        return bool;
      }
      return false;
    }
    finally {}
  }
  
  public final Future<?> ˇˇˇ()
  {
    ˉ localˉ = new ˉ();
    this.ˇ.execute(localˉ);
    return localˉ;
  }
  
  public final clk ˇˇˇˇ()
  {
    return cjj.ˇ().ˇˇ();
  }
  
  public static final class ˇ
    implements Runnable
  {
    private List<clj> ˇ;
    private clk ˇˇ;
    
    public ˇ(List<clj> paramList, clk paramClk)
    {
      this.ˇ = paramList;
      this.ˇˇ = paramClk;
    }
    
    public final void run()
    {
      Iterator localIterator = this.ˇ.iterator();
      while (localIterator.hasNext()) {
        localIterator.next();
      }
    }
  }
  
  static class ˇˇ
    implements Runnable, Future<Object>
  {
    private final CountDownLatch ˇ = new CountDownLatch(1);
    private boolean ˇˇ;
    private ExecutionException ˇˇˇ;
    
    public ˇˇ() {}
    
    static clk ˇˇ()
    {
      clk localClk = cjj.ˇ().ˇˇ();
      if (localClk != null) {
        cjj.ˇ().ˇˇˇ();
      }
      ckq.ˇ();
      ckq.ˇˇˇ();
      return localClk;
    }
    
    public boolean cancel(boolean paramBoolean)
    {
      return false;
    }
    
    public Object get()
      throws InterruptedException, ExecutionException
    {
      this.ˇ.await();
      if (this.ˇˇˇ == null) {
        return null;
      }
      throw this.ˇˇˇ;
    }
    
    public Object get(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException, ExecutionException, TimeoutException
    {
      this.ˇ.await(paramLong, paramTimeUnit);
      if (this.ˇˇˇ == null) {
        return null;
      }
      throw this.ˇˇˇ;
    }
    
    public boolean isCancelled()
    {
      return false;
    }
    
    public boolean isDone()
    {
      return this.ˇˇ;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 76	com/droid/developer/ckl$ˇˇ:ˇ	()V
      //   4: aload_0
      //   5: iconst_1
      //   6: putfield 71	com/droid/developer/ckl$ˇˇ:ˇˇ	Z
      //   9: aload_0
      //   10: getfield 28	com/droid/developer/ckl$ˇˇ:ˇ	Ljava/util/concurrent/CountDownLatch;
      //   13: invokevirtual 79	java/util/concurrent/CountDownLatch:countDown	()V
      //   16: return
      //   17: astore_1
      //   18: goto +19 -> 37
      //   21: astore_1
      //   22: aload_0
      //   23: new 55	java/util/concurrent/ExecutionException
      //   26: dup
      //   27: aload_1
      //   28: invokespecial 82	java/util/concurrent/ExecutionException:<init>	(Ljava/lang/Throwable;)V
      //   31: putfield 60	com/droid/developer/ckl$ˇˇ:ˇˇˇ	Ljava/util/concurrent/ExecutionException;
      //   34: goto -30 -> 4
      //   37: aload_0
      //   38: iconst_1
      //   39: putfield 71	com/droid/developer/ckl$ˇˇ:ˇˇ	Z
      //   42: aload_0
      //   43: getfield 28	com/droid/developer/ckl$ˇˇ:ˇ	Ljava/util/concurrent/CountDownLatch;
      //   46: invokevirtual 79	java/util/concurrent/CountDownLatch:countDown	()V
      //   49: aload_1
      //   50: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	51	0	this	ˇˇ
      //   17	1	1	localObject	Object
      //   21	29	1	localThrowable	Throwable
      // Exception table:
      //   from	to	target	type
      //   0	4	17	finally
      //   22	34	17	finally
      //   0	4	21	java/lang/Throwable
    }
    
    void ˇ()
      throws Exception
    {
      ˇˇ();
    }
  }
  
  static final class ˇˇˇ
    implements cku<clk>
  {
    private final ckn ˇ;
    
    ˇˇˇ(ckn paramCkn)
    {
      this.ˇ = paramCkn;
    }
    
    public final void ˇ(ckt<clk> paramCkt)
    {
      for (;;)
      {
        try
        {
          ckn localCkn;
          switch (ckl.1.ˇˇ[paramCkt.ˇˇ().ordinal()])
          {
          case 3: 
            localCkn = this.ˇ;
            continue;
            this.ˇ.ˇ(paramCkt.ˇˇˇˇ());
            return;
          case 2: 
            this.ˇ.ˊˊ();
            return;
          case 1: 
            this.ˇ.ˇ((clk)paramCkt.ˇˇˇ());
            return;
            cld localCld = cld.ˇˇˇˇ;
            StringBuilder localStringBuilder = new StringBuilder("Unknown ApiReqeustFuture.status. status = ");
            localStringBuilder.append(paramCkt.ˇˇ());
            localCkn.ˇ(new cle(localCld, localStringBuilder.toString()));
            return;
          }
        }
        catch (Throwable paramCkt)
        {
          this.ˇ.ˇ(paramCkt);
          return;
        }
      }
    }
  }
  
  static final class ˇˇˇˇ
    implements cku<clp>
  {
    private final WeakReference<Activity> ˇ;
    private final ckn ˇˇ;
    
    ˇˇˇˇ(Activity paramActivity, ckn paramCkn)
    {
      this.ˇ = new WeakReference(paramActivity);
      this.ˇˇ = paramCkn;
    }
    
    public final void ˇ(ckt<clp> paramCkt)
    {
      for (;;)
      {
        try
        {
          ckn localCkn;
          switch (ckl.1.ˇˇ[paramCkt.ˇˇ().ordinal()])
          {
          case 3: 
            localCkn = this.ˇˇ;
            continue;
            this.ˇˇ.ˇ(paramCkt.ˇˇˇˇ());
            return;
          case 2: 
            this.ˇˇ.ˊˊ();
            return;
          case 1: 
            this.ˇˇ.ˇ((clp)paramCkt.ˇˇˇ());
            ckl.ˇ(this.ˇˇ, (Activity)this.ˇ.get());
            return;
            cld localCld = cld.ˇˇˇˇ;
            StringBuilder localStringBuilder = new StringBuilder("Unknown ApiReqeustFuture.status. status = ");
            localStringBuilder.append(paramCkt.ˇˇ());
            localCkn.ˇ(new cle(localCld, localStringBuilder.toString()));
            return;
          }
        }
        catch (Throwable paramCkt)
        {
          this.ˇˇ.ˇ(paramCkt);
          return;
        }
      }
    }
  }
  
  static final class ˉ
    extends ckl.ˇˇ
  {
    ˉ() {}
    
    public final void ˇ()
    {
      clk localClk = ckl.ˇˇ.ˇˇ();
      if (localClk != null) {}
      try
      {
        cjg.ˇ().ˇˇ().ˇ(localClk.ˇˇ);
        return;
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  static final class ˉˉ
    implements Runnable
  {
    private final Activity ˇ;
    private final ckn ˇˇ;
    
    ˉˉ(Activity paramActivity, ckn paramCkn)
    {
      this.ˇ = paramActivity;
      this.ˇˇ = paramCkn;
    }
    
    public final void run()
    {
      if (!this.ˇˇ.ˇˇˇ)
      {
        clk localClk = cjj.ˇ().ˇˇ();
        if (localClk != null)
        {
          this.ˇˇ.ˇ(localClk);
          return;
        }
      }
      if ((this.ˇ == null) && (this.ˇˇ.ˇˇ.ˋ < clg.ˇ.ˉˉ.ˋ))
      {
        this.ˇˇ.ˇ(new cle(cld.ˇ, "activity is null"));
        return;
      }
      ckl.ˇ(this.ˇˇ, this.ˇ);
    }
  }
}
