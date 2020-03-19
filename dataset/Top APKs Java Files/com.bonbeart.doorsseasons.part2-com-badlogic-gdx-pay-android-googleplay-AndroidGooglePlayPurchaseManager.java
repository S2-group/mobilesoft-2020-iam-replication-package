package com.badlogic.gdx.pay.android.googleplay;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.pay.android.googleplay.a.a.b;
import com.badlogic.gdx.pay.android.googleplay.a.c.a;
import com.badlogic.gdx.pay.android.googleplay.a.c.b;
import com.badlogic.gdx.pay.f;
import com.badlogic.gdx.pay.g;
import com.badlogic.gdx.pay.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AndroidGooglePlayPurchaseManager
  implements com.badlogic.gdx.pay.e
{
  public static final String GOOGLE_MARKET_NAME = "com.google.market";
  public static final String GOOGLE_PLAY_STORE_NAME = "com.android.vending";
  public static final String LOG_TAG = "GdxPay/AndroidPlay";
  private final com.badlogic.gdx.pay.android.googleplay.a.c a;
  private final Map<String, com.badlogic.gdx.pay.a> b = new ConcurrentHashMap();
  private g c;
  private f d;
  
  public AndroidGooglePlayPurchaseManager(Activity paramActivity, int paramInt)
  {
    if (!(paramActivity instanceof AndroidApplication)) {
      throw new IllegalArgumentException("Bootstrapping gdx-pay only supported with AndroidApplication activity.");
    }
    this.a = new com.badlogic.gdx.pay.android.googleplay.a.e((AndroidApplication)paramActivity, paramInt, new com.badlogic.gdx.pay.android.googleplay.a.a.e(this), new com.badlogic.gdx.pay.android.googleplay.a.d());
  }
  
  public AndroidGooglePlayPurchaseManager(Activity paramActivity, com.badlogic.gdx.backends.android.h paramH, int paramInt)
  {
    com.badlogic.gdx.pay.android.googleplay.a.a.e localE = new com.badlogic.gdx.pay.android.googleplay.a.a.e(this);
    com.badlogic.gdx.pay.android.googleplay.a.d localD = new com.badlogic.gdx.pay.android.googleplay.a.d();
    this.a = new com.badlogic.gdx.pay.android.googleplay.a.e(new a.b(paramActivity, paramH), paramInt, localE, localD);
    com.badlogic.gdx.pay.h.a(this);
  }
  
  public AndroidGooglePlayPurchaseManager(com.badlogic.gdx.pay.android.googleplay.a.c paramC)
  {
    this.a = paramC;
  }
  
  private com.badlogic.gdx.pay.d a(String paramString)
  {
    com.badlogic.gdx.pay.c localC = this.d.a(paramString);
    if ((localC == null) || (localC.a() == null)) {
      throw new IllegalStateException("No offer or offerType configured for identifier: " + paramString + ", offer: " + localC);
    }
    return localC.a();
  }
  
  private void a()
  {
    Object localObject = b();
    localObject = this.a.a((List)localObject);
    this.b.clear();
    this.b.putAll((Map)localObject);
  }
  
  private void a(f paramF)
  {
    int i = 0;
    while (i < paramF.a())
    {
      com.badlogic.gdx.pay.c localC = paramF.a(i);
      if (localC.a() == com.badlogic.gdx.pay.d.c) {
        throw new IllegalArgumentException("Unsupported offer: " + localC);
      }
      i += 1;
    }
  }
  
  private void a(final g paramG, final boolean paramBoolean)
  {
    runAsync(new Runnable()
    {
      public void run()
      {
        try
        {
          if (paramBoolean) {
            AndroidGooglePlayPurchaseManager.c(AndroidGooglePlayPurchaseManager.this);
          }
          paramG.a();
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Log.e("GdxPay/AndroidPlay", "Failed to load skus in onServiceConnected()", localException);
          }
        }
      }
    });
  }
  
  private List<String> b()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.d.a())
    {
      localArrayList.add(this.d.a(i).b());
      i += 1;
    }
    return localArrayList;
  }
  
  private void c()
  {
    this.b.clear();
  }
  
  private void d()
  {
    if (!installed()) {
      throw new b("Payment system must be installed to perform this action.");
    }
  }
  
  public static boolean isRunningViaGooglePlay(Activity paramActivity)
  {
    paramActivity = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    while (paramActivity.hasNext())
    {
      String str = ((PackageInfo)paramActivity.next()).packageName;
      if ((str.equals("com.google.market")) || (str.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  public void cancelTestPurchases()
  {
    this.a.a();
  }
  
  public void dispose()
  {
    this.a.e();
    c();
    this.c = null;
  }
  
  public com.badlogic.gdx.pay.a getInformation(String paramString)
  {
    com.badlogic.gdx.pay.a localA = (com.badlogic.gdx.pay.a)this.b.get(paramString);
    paramString = localA;
    if (localA == null) {
      paramString = com.badlogic.gdx.pay.a.a;
    }
    return paramString;
  }
  
  public void install(final g paramG, f paramF, final boolean paramBoolean)
  {
    a(paramF);
    this.c = paramG;
    this.d = paramF;
    if (this.a.c()) {
      this.a.b();
    }
    this.a.a(new c.a()
    {
      public void a()
      {
        AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this, paramG, paramBoolean);
      }
      
      public void a(b paramAnonymousB)
      {
        paramG.a(new b("Failed to bind to service", paramAnonymousB));
      }
    });
  }
  
  public boolean installed()
  {
    return this.a.c();
  }
  
  public void purchase(final String paramString)
  {
    d();
    final com.badlogic.gdx.pay.d localD = a(paramString);
    this.a.a(paramString, new c.b()
    {
      public void a()
      {
        if (AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this) != null) {
          AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this).b();
        }
      }
      
      public void a(b paramAnonymousB)
      {
        if (AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this) != null) {
          AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this).c(paramAnonymousB);
        }
      }
      
      public void a(i paramAnonymousI)
      {
        if (AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this) != null) {}
        switch (AndroidGooglePlayPurchaseManager.4.a[localD.ordinal()])
        {
        default: 
          throw new b("Unsupported OfferType=" + AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this, paramString) + " for identifier=" + paramString);
        case 1: 
          AndroidGooglePlayPurchaseManager.b(AndroidGooglePlayPurchaseManager.this).a(paramAnonymousI, AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this));
          return;
        }
        AndroidGooglePlayPurchaseManager.a(AndroidGooglePlayPurchaseManager.this).a(paramAnonymousI);
      }
    });
  }
  
  public void purchaseRestore()
  {
    for (;;)
    {
      com.badlogic.gdx.utils.a localA;
      int i;
      try
      {
        List localList = this.a.d();
        localA = new com.badlogic.gdx.utils.a(i.class);
        i = 0;
        if (i < localList.size())
        {
          i localI = (i)localList.get(i);
          if (com.badlogic.gdx.pay.d.a == a(localI.a())) {
            this.a.a(localI, this.c);
          } else {
            localA.a(localI);
          }
        }
      }
      catch (b localB)
      {
        if (this.c != null) {
          this.c.b(localB);
        }
        return;
      }
      if (this.c != null)
      {
        this.c.a((i[])localA.e());
        return;
        i += 1;
      }
    }
  }
  
  protected void runAsync(Runnable paramRunnable)
  {
    new Thread(paramRunnable).start();
  }
  
  public String storeName()
  {
    return "GooglePlay";
  }
}
