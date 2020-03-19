package com.viber.voip.billing;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import com.viber.common.dialogs.a.a;
import com.viber.common.dialogs.c.a;
import com.viber.dexshared.Logger;
import com.viber.voip.ViberApplication;
import com.viber.voip.contacts.c.d.f;
import com.viber.voip.m;
import com.viber.voip.m.d;
import com.viber.voip.process.a;
import com.viber.voip.settings.c.u;
import com.viber.voip.ui.dialogs.s;
import com.viber.voip.util.ba;
import com.viber.voip.util.bp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.zip.Adler32;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g
{
  private static final Logger a = b.a(g.class.getSimpleName());
  private static volatile g b;
  private Context c;
  private InAppBillingHelper d;
  private h e;
  private ArrayList<j> f = new ArrayList(2);
  private Handler g = m.a(m.d.a);
  private boolean h;
  private l i;
  private j j;
  private Runnable k = new g.13(this);
  private k l = new g.7(this);
  
  private g(Context paramContext)
  {
    this.c = paramContext;
    this.j = new j(this.l);
    this.f.add(new q(this.l));
    this.f.add(new n(this.l));
    this.e = new i();
    this.i = new l(this);
    this.i.a(new g.1(this));
  }
  
  private b.c a(Purchase paramPurchase, ProductDetails paramProductDetails)
  {
    SynchronousQueue localSynchronousQueue = new SynchronousQueue();
    b.a().a(paramPurchase, null, paramProductDetails, true, new g.2(this, localSynchronousQueue));
    try
    {
      paramPurchase = (b.c)localSynchronousQueue.take();
      return paramPurchase;
    }
    catch (InterruptedException paramPurchase)
    {
      paramPurchase.printStackTrace();
    }
    return null;
  }
  
  private g.b<IabInventory> a(ArrayList<IabProductId> paramArrayList)
  {
    SynchronousQueue localSynchronousQueue = new SynchronousQueue();
    a().c().queryInventoryAsync(true, paramArrayList, new g.16(this, localSynchronousQueue));
    try
    {
      paramArrayList = (g.b)localSynchronousQueue.take();
      return paramArrayList;
    }
    catch (InterruptedException paramArrayList)
    {
      paramArrayList.printStackTrace();
    }
    return null;
  }
  
  public static g a()
  {
    if (a.a() != a.a) {
      return null;
    }
    if (b == null) {
      b = new g(ViberApplication.getInstance());
    }
    return b;
  }
  
  private ArrayList<IabProductId> a(b.c paramC)
  {
    localArrayList = new ArrayList();
    if (paramC.c() != 1) {}
    for (;;)
    {
      return localArrayList;
      if (paramC.d() == null) {
        continue;
      }
      try
      {
        paramC = paramC.d().getJSONObject("products").getJSONArray("stickers");
        int m = 0;
        while (m < paramC.length())
        {
          IabProductId localIabProductId = IabProductId.fromString(paramC.getJSONObject(m).toString());
          try
          {
            localArrayList.add(localIabProductId);
            m += 1;
          }
          finally {}
        }
        return localArrayList;
      }
      catch (JSONException paramC) {}
    }
  }
  
  private Map<IabProductId, b.c> a(g.b<IabInventory> paramB)
  {
    ArrayMap localArrayMap = new ArrayMap();
    Iterator localIterator = ((IabInventory)paramB.a()).getAllOwnedProductIds().iterator();
    while (localIterator.hasNext())
    {
      IabProductId localIabProductId = (IabProductId)localIterator.next();
      localArrayMap.put(localIabProductId, a(((IabInventory)paramB.a()).getPurchase(localIabProductId), ((IabInventory)paramB.a()).getProductDetails(localIabProductId)));
    }
    return localArrayMap;
  }
  
  private void a(InAppBillingHelper paramInAppBillingHelper)
  {
    paramInAppBillingHelper.startSetup(new g.12(this, paramInAppBillingHelper));
  }
  
  public static void a(g.a paramA)
  {
    a(paramA, g.c.b);
  }
  
  private static void a(g.a paramA, g.c paramC)
  {
    m.a(m.d.h).post(new g.14(paramA, paramC));
  }
  
  private boolean a(g.c paramC)
  {
    Object localObject2 = new ArrayList();
    Object localObject1 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    Object localObject3 = o();
    ((ArrayList)localObject2).addAll(((IabInventory)((g.b)localObject3).a()).getAllOwnedProductIds());
    if (!((g.b)localObject3).a) {
      return false;
    }
    if (paramC == g.c.b)
    {
      paramC = p();
      if (paramC.c() == 1) {}
      for (int m = 1; m == 0; m = 0) {
        return false;
      }
      localObject1 = a(paramC);
      localObject3 = new ArrayList((Collection)localObject2).iterator();
      for (;;)
      {
        paramC = (g.c)localObject1;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        paramC = (IabProductId)((Iterator)localObject3).next();
        Iterator localIterator = ((ArrayList)localObject1).iterator();
        while (localIterator.hasNext())
        {
          IabProductId localIabProductId = (IabProductId)localIterator.next();
          if (paramC.getProductId().equals(localIabProductId.getProductId())) {
            ((ArrayList)localObject2).remove(paramC);
          }
        }
      }
    }
    paramC = (g.c)localObject1;
    if ((((ArrayList)localObject2).isEmpty()) && (paramC.isEmpty())) {
      return true;
    }
    if (!((ArrayList)localObject2).isEmpty())
    {
      localObject1 = a((ArrayList)localObject2);
      if (!((g.b)localObject1).a) {
        return false;
      }
      localObject1 = a((g.b)localObject1);
      localObject2 = ((Map)localObject1).keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (IabProductId)((Iterator)localObject2).next();
        if (((b.c)((Map)localObject1).get(localObject3)).a()) {
          localArrayList.add(localObject3);
        }
      }
    }
    localArrayList.addAll(paramC);
    paramC = localArrayList.iterator();
    while (paramC.hasNext())
    {
      localObject1 = (IabProductId)paramC.next();
      b((IabProductId)localObject1).c((IabProductId)localObject1);
    }
    return true;
  }
  
  private j b(IabProductId paramIabProductId)
  {
    Iterator localIterator = this.f.iterator();
    while (localIterator.hasNext())
    {
      j localJ = (j)localIterator.next();
      if (localJ.a(paramIabProductId)) {
        return localJ;
      }
    }
    return this.j;
  }
  
  private void b(Purchase paramPurchase)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramPurchase.getProductId());
    c().queryProductDetailsAsync(localArrayList, new g.6(this, paramPurchase));
  }
  
  public static void b(g.a paramA)
  {
    a(paramA, g.c.a);
  }
  
  private void b(g.a paramA, g.c paramC)
  {
    boolean bool;
    if (ba.b(this.c))
    {
      bool = a(paramC);
      c.u.a.a(true);
    }
    for (;;)
    {
      if (paramA != null) {
        this.g.post(new g.4(this, paramA, bool));
      }
      return;
      bool = false;
    }
  }
  
  private j c(Purchase paramPurchase)
  {
    return b(paramPurchase.getProductId());
  }
  
  public static String d()
  {
    try
    {
      String str = OpenIabHelperWrapper.getPreferredStore();
      boolean bool = bp.a(str);
      if (!bool) {
        return str;
      }
    }
    catch (Throwable localThrowable) {}
    return "google";
  }
  
  private InAppBillingHelper j()
  {
    try
    {
      localOpenIabHelperWrapper3 = new OpenIabHelperWrapper(this.c);
      OpenIabHelperWrapper localOpenIabHelperWrapper1;
      if (localE != null) {
        break label48;
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        a(localOpenIabHelperWrapper3);
        localOpenIabHelperWrapper1 = localOpenIabHelperWrapper3;
        if (localOpenIabHelperWrapper3 == null) {
          break label28;
        }
        return localOpenIabHelperWrapper3;
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          OpenIabHelperWrapper localOpenIabHelperWrapper3;
          e localE;
          OpenIabHelperWrapper localOpenIabHelperWrapper2 = localOpenIabHelperWrapper3;
        }
      }
      localThrowable1 = localThrowable1;
      localE = null;
    }
    label28:
    ViberApplication.getInstance().logToCrashlytics(new RuntimeException("OpenIAB inaccessible."));
    label48:
    localE = new e(this.c);
    a(localE);
    return localE;
  }
  
  private void k()
  {
    this.h = true;
    this.g.removeCallbacks(this.k);
  }
  
  private void l()
  {
    this.h = false;
    m();
  }
  
  private void m()
  {
    this.g.removeCallbacks(this.k);
    if (!this.h) {
      this.g.postDelayed(this.k, 30000L);
    }
  }
  
  private void n()
  {
    try
    {
      if (this.d != null)
      {
        this.d.setActivityListener(null);
        this.d.dispose();
        this.d = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private g.b<IabInventory> o()
  {
    Object localObject = new SynchronousQueue();
    a().c().queryInventoryAsync(true, null, new g.15(this, (SynchronousQueue)localObject));
    try
    {
      localObject = (g.b)((SynchronousQueue)localObject).take();
      return localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
    return null;
  }
  
  private b.c p()
  {
    Object localObject = new SynchronousQueue();
    b.a().a(new g.3(this, (SynchronousQueue)localObject));
    try
    {
      localObject = (b.c)((SynchronousQueue)localObject).take();
      return localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
    return null;
  }
  
  InAppBillingHelper.OnIabPurchaseFinishedListener a(IabProductId paramIabProductId, String paramString, Bundle paramBundle)
  {
    return new g.5(this, paramIabProductId, paramString, paramBundle);
  }
  
  public void a(IabProductId paramIabProductId)
  {
    a(paramIabProductId, null);
  }
  
  public void a(IabProductId paramIabProductId, String paramString)
  {
    a(paramIabProductId, paramString, null);
  }
  
  public void a(IabProductId paramIabProductId, String paramString1, String paramString2)
  {
    a(paramIabProductId, paramString1, paramString2, null);
  }
  
  public void a(IabProductId paramIabProductId, String paramString1, String paramString2, Bundle paramBundle)
  {
    k();
    PurchaseSupportActivity.a(paramIabProductId, paramString1, paramString2, paramBundle);
  }
  
  public void a(IabResult paramIabResult, String paramString)
  {
    switch (paramIabResult.getResponse())
    {
    default: 
      s.a(paramIabResult.toString()).c(true).a(PurchaseSupportActivity.class);
      return;
    }
    s.c().c(true).a(PurchaseSupportActivity.class);
  }
  
  void a(Purchase paramPurchase)
  {
    paramPurchase.setRetrying(true);
    c(paramPurchase).a(paramPurchase);
    if (!paramPurchase.isPending()) {
      e().b(paramPurchase);
    }
  }
  
  public void a(Runnable paramRunnable)
  {
    k();
    c().queryInventoryAsync(true, null, new g.10(this, paramRunnable));
  }
  
  public void b()
  {
    e();
    if (!c.u.a.d()) {
      ViberApplication.getInstance().getContactManager().e().a(new g.9(this));
    }
  }
  
  public InAppBillingHelper c()
  {
    try
    {
      m();
      if ((this.d == null) && (a.a() == a.a))
      {
        localInAppBillingHelper = j();
        this.d = localInAppBillingHelper;
        localInAppBillingHelper.setActivityListener(new g.11(this));
      }
      InAppBillingHelper localInAppBillingHelper = this.d;
      return localInAppBillingHelper;
    }
    finally {}
  }
  
  public l e()
  {
    return this.i;
  }
  
  public void f()
  {
    s.a().c(true).a(PurchaseSupportActivity.class);
  }
  
  public void g()
  {
    s.b(this.c.getString(2131231732)).c(true).a(PurchaseSupportActivity.class);
  }
  
  Purchase[] h()
  {
    return this.e.a();
  }
  
  public boolean i()
  {
    Iterator localIterator = this.c.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      Adler32 localAdler32 = new Adler32();
      localAdler32.update(localApplicationInfo.packageName.getBytes());
      long l1 = localAdler32.getValue();
      if ((l1 == 1419053039L) || (l1 == 1069942500L) || (l1 == 3379956861L) || (l1 == 207491948L)) {
        return false;
      }
    }
    return true;
  }
}
