package com.badlogic.gdx.pay.android.googleplay;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseManagerTestSupport;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.PurchaseSystem;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.pay.android.googleplay.billing.ApplicationProxy.FragmentProxy;
import com.badlogic.gdx.pay.android.googleplay.billing.GoogleInAppBillingService;
import com.badlogic.gdx.pay.android.googleplay.billing.GoogleInAppBillingService.ConnectionListener;
import com.badlogic.gdx.pay.android.googleplay.billing.GoogleInAppBillingService.PurchaseRequestCallback;
import com.badlogic.gdx.pay.android.googleplay.billing.NewThreadSleepAsyncExecutor;
import com.badlogic.gdx.pay.android.googleplay.billing.V3GoogleInAppBillingService;
import com.badlogic.gdx.pay.android.googleplay.billing.converter.OfferToInAppPurchaseConverter;
import com.badlogic.gdx.pay.android.googleplay.billing.converter.PurchaseResponseActivityResultConverter;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AndroidGooglePlayPurchaseManager
  implements PurchaseManager, PurchaseManagerTestSupport
{
  public static final String GOOGLE_MARKET_NAME = "com.google.market";
  public static final String GOOGLE_PLAY_STORE_NAME = "com.android.vending";
  public static final String LOG_TAG = "GdxPay/AndroidPlay";
  private final GoogleInAppBillingService googleInAppBillingService;
  private final Map<String, Information> informationMap = new ConcurrentHashMap();
  private PurchaseObserver observer;
  private PurchaseManagerConfig purchaseManagerConfig;
  
  public AndroidGooglePlayPurchaseManager(Activity paramActivity, int paramInt)
  {
    if ((paramActivity instanceof AndroidApplication))
    {
      this.googleInAppBillingService = new V3GoogleInAppBillingService((AndroidApplication)paramActivity, paramInt, new PurchaseResponseActivityResultConverter(this), new NewThreadSleepAsyncExecutor());
      return;
    }
    throw new IllegalArgumentException("Bootstrapping gdx-pay only supported with AndroidApplication activity.");
  }
  
  public AndroidGooglePlayPurchaseManager(Activity paramActivity, AndroidFragmentApplication paramAndroidFragmentApplication, int paramInt)
  {
    PurchaseResponseActivityResultConverter localPurchaseResponseActivityResultConverter = new PurchaseResponseActivityResultConverter(this);
    NewThreadSleepAsyncExecutor localNewThreadSleepAsyncExecutor = new NewThreadSleepAsyncExecutor();
    this.googleInAppBillingService = new V3GoogleInAppBillingService(new ApplicationProxy.FragmentProxy(paramActivity, paramAndroidFragmentApplication), paramInt, localPurchaseResponseActivityResultConverter, localNewThreadSleepAsyncExecutor);
    PurchaseSystem.setManager(this);
  }
  
  public AndroidGooglePlayPurchaseManager(GoogleInAppBillingService paramGoogleInAppBillingService)
  {
    this.googleInAppBillingService = paramGoogleInAppBillingService;
  }
  
  private void assertInstalled()
  {
    if (installed()) {
      return;
    }
    throw new GdxPayException("Payment system must be installed to perform this action.");
  }
  
  private void clearCaches()
  {
    this.informationMap.clear();
  }
  
  private OfferType getOfferType(String paramString)
  {
    Offer localOffer = this.purchaseManagerConfig.getOffer(paramString);
    if ((localOffer != null) && (localOffer.getType() != null)) {
      return localOffer.getType();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("No offer or offerType configured for identifier: ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(", offer: ");
    localStringBuilder.append(localOffer);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private void getPurchaseIdsByType(List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < this.purchaseManagerConfig.getOfferCount())
    {
      Offer localOffer = this.purchaseManagerConfig.getOffer(i);
      if (localOffer.getType().equals(OfferType.SUBSCRIPTION)) {
        paramList2.add(localOffer.getIdentifier());
      } else {
        paramList1.add(localOffer.getIdentifier());
      }
      i += 1;
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
  
  private void loadSkusAndFillPurchaseInformation()
  {
    Object localObject2 = new ArrayList();
    Object localObject1 = new ArrayList();
    getPurchaseIdsByType((List)localObject2, (List)localObject1);
    this.informationMap.clear();
    if (!((List)localObject2).isEmpty())
    {
      localObject2 = this.googleInAppBillingService.getProductsDetails((List)localObject2, "inapp");
      this.informationMap.putAll((Map)localObject2);
    }
    if (!((List)localObject1).isEmpty())
    {
      localObject1 = this.googleInAppBillingService.getProductsDetails((List)localObject1, "subs");
      this.informationMap.putAll((Map)localObject1);
    }
  }
  
  private void onServiceConnected(final PurchaseObserver paramPurchaseObserver, final boolean paramBoolean)
  {
    runAsync(new Runnable()
    {
      public void run()
      {
        try
        {
          if (paramBoolean) {
            AndroidGooglePlayPurchaseManager.this.loadSkusAndFillPurchaseInformation();
          }
        }
        catch (Exception localException)
        {
          Log.e("GdxPay/AndroidPlay", "Failed to load skus in onServiceConnected()", localException);
        }
        paramPurchaseObserver.handleInstall();
      }
    });
  }
  
  public void cancelTestPurchases()
  {
    this.googleInAppBillingService.cancelTestPurchases();
  }
  
  public void dispose()
  {
    this.googleInAppBillingService.dispose();
    clearCaches();
    this.observer = null;
  }
  
  public Information getInformation(String paramString)
  {
    paramString = (Information)this.informationMap.get(paramString);
    if (paramString == null) {
      return Information.UNAVAILABLE;
    }
    return paramString;
  }
  
  public void install(final PurchaseObserver paramPurchaseObserver, PurchaseManagerConfig paramPurchaseManagerConfig, final boolean paramBoolean)
  {
    this.observer = paramPurchaseObserver;
    this.purchaseManagerConfig = paramPurchaseManagerConfig;
    if (this.googleInAppBillingService.isListeningForConnections()) {
      this.googleInAppBillingService.disconnect();
    }
    this.googleInAppBillingService.requestConnect(new GoogleInAppBillingService.ConnectionListener()
    {
      public void connected()
      {
        AndroidGooglePlayPurchaseManager.this.onServiceConnected(paramPurchaseObserver, paramBoolean);
      }
      
      public void disconnected(GdxPayException paramAnonymousGdxPayException)
      {
        paramPurchaseObserver.handleInstallError(new GdxPayException("Failed to bind to service", paramAnonymousGdxPayException));
      }
    });
  }
  
  public boolean installed()
  {
    return this.googleInAppBillingService.isListeningForConnections();
  }
  
  public void purchase(final String paramString)
  {
    assertInstalled();
    final OfferType localOfferType = getOfferType(paramString);
    this.googleInAppBillingService.startPurchaseRequest(paramString, OfferToInAppPurchaseConverter.convertOfferType(localOfferType), new GoogleInAppBillingService.PurchaseRequestCallback()
    {
      public void purchaseCanceled()
      {
        if (AndroidGooglePlayPurchaseManager.this.observer != null) {
          AndroidGooglePlayPurchaseManager.this.observer.handlePurchaseCanceled();
        }
      }
      
      public void purchaseError(GdxPayException paramAnonymousGdxPayException)
      {
        if (AndroidGooglePlayPurchaseManager.this.observer != null) {
          AndroidGooglePlayPurchaseManager.this.observer.handlePurchaseError(paramAnonymousGdxPayException);
        }
      }
      
      public void purchaseSuccess(Transaction paramAnonymousTransaction)
      {
        if (AndroidGooglePlayPurchaseManager.this.observer != null)
        {
          switch (AndroidGooglePlayPurchaseManager.4.$SwitchMap$com$badlogic$gdx$pay$OfferType[localOfferType.ordinal()])
          {
          default: 
            paramAnonymousTransaction = new StringBuilder();
            paramAnonymousTransaction.append("Unsupported OfferType=");
            paramAnonymousTransaction.append(AndroidGooglePlayPurchaseManager.this.getOfferType(paramString));
            paramAnonymousTransaction.append(" for identifier=");
            paramAnonymousTransaction.append(paramString);
            throw new GdxPayException(paramAnonymousTransaction.toString());
          case 2: 
          case 3: 
            AndroidGooglePlayPurchaseManager.this.observer.handlePurchase(paramAnonymousTransaction);
            return;
          }
          AndroidGooglePlayPurchaseManager.this.googleInAppBillingService.consumePurchase(paramAnonymousTransaction, AndroidGooglePlayPurchaseManager.this.observer);
        }
      }
    });
  }
  
  public void purchaseRestore()
  {
    for (;;)
    {
      int i;
      try
      {
        List localList = this.googleInAppBillingService.getPurchases();
        localObject = new Array(Transaction.class);
        i = 0;
        if (i < localList.size())
        {
          Transaction localTransaction = (Transaction)localList.get(i);
          if (OfferType.CONSUMABLE == getOfferType(localTransaction.getIdentifier()))
          {
            this.googleInAppBillingService.consumePurchase(localTransaction, this.observer);
            break label129;
          }
          ((Array)localObject).add(localTransaction);
          break label129;
        }
        if (this.observer != null)
        {
          this.observer.handleRestore((Transaction[])((Array)localObject).toArray());
          return;
        }
      }
      catch (GdxPayException localGdxPayException)
      {
        Object localObject = this.observer;
        if (localObject != null) {
          ((PurchaseObserver)localObject).handleRestoreError(localGdxPayException);
        }
      }
      return;
      label129:
      i += 1;
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
