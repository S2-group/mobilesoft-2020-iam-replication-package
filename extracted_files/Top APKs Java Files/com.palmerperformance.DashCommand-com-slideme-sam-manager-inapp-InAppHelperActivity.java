package com.slideme.sam.manager.inapp;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class InAppHelperActivity
  extends FragmentActivity
  implements LoaderManager.LoaderCallbacks<Bundle>
{
  private static final int API_VERSION = 1;
  private static final int LOADER_ID_CONSUME = 3;
  private static final int LOADER_ID_LIST = 1;
  private static final int LOADER_ID_PURCHASES = 2;
  private static final String LOADER_PARAM_IAP_IDS = "iap_ids";
  private static final String LOADER_PARAM_PURCHASES_TYPE = "purchases_type";
  private static final String LOADER_PARAM_TID = "tid";
  private static final int REQUEST_CODE_BUY = 1;
  private static final String SAM_PACKAGE_INFO = "SAM_PACKAGE_INFO";
  private boolean mIapAvailable = false;
  private boolean mIapServiceBound = false;
  private Bundle mListResult;
  private Bundle mPurchasesListResult;
  private PackageInfo mSamPackageInfo;
  private IInAppService mService;
  private ServiceConnection mServiceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      InAppHelperActivity.this.mService = IInAppService.Stub.asInterface(paramAnonymousIBinder);
      InAppHelperActivity.this.mIapServiceBound = true;
      InAppHelperActivity.this.onIapReady();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      InAppHelperActivity.this.mIapServiceBound = false;
      InAppHelperActivity.this.mService = null;
    }
  };
  
  public InAppHelperActivity() {}
  
  private void initOrRestartLoader(int paramInt, Bundle paramBundle)
  {
    Loader localLoader = getSupportLoaderManager().getLoader(paramInt);
    if (localLoader == null)
    {
      getSupportLoaderManager().initLoader(paramInt, paramBundle, this).forceLoad();
      return;
    }
    if (!localLoader.isAbandoned()) {
      throw new IllegalStateException("Only one asynchronous call per type is permitted. Call again only after having returned.");
    }
    getSupportLoaderManager().restartLoader(paramInt, paramBundle, this).forceLoad();
  }
  
  private boolean isIapAvailable()
  {
    return (this.mSamPackageInfo != null) && (Float.parseFloat(this.mSamPackageInfo.versionName) > 6.0F);
  }
  
  public void consumePurchase(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("tid", paramString);
    initOrRestartLoader(3, localBundle);
  }
  
  public ListResult getListResult()
  {
    if (this.mListResult == null) {
      return null;
    }
    return new ListResult(this.mListResult);
  }
  
  public PurchasesListResult getPurchasesListResult()
  {
    if (this.mPurchasesListResult == null) {
      return null;
    }
    return new PurchasesListResult(this.mPurchasesListResult);
  }
  
  protected boolean isIapReady()
  {
    return this.mService != null;
  }
  
  public void loadList(List<String> paramList)
  {
    Bundle localBundle = new Bundle();
    localBundle.putStringArrayList("iap_ids", new ArrayList(paramList));
    initOrRestartLoader(1, localBundle);
  }
  
  public void loadPurchases(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("purchases_type", paramString);
    initOrRestartLoader(2, localBundle);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
      return;
    }
    if ((paramIntent == null) || (paramIntent.getExtras() == null))
    {
      paramIntent = new Bundle();
      paramIntent.putInt("com.slideme.sam.manager.inapp.BUNDLE_STATUS", 4);
    }
    for (;;)
    {
      onPurchaseFinished(new PurchaseResult(paramIntent));
      return;
      paramIntent = paramIntent.getBundleExtra("com.slideme.sam.manager.inapp.extra.RESPONSE");
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      this.mSamPackageInfo = ((PackageInfo)paramBundle.getParcelable("SAM_PACKAGE_INFO"));
    }
    Object localObject;
    if (this.mSamPackageInfo == null)
    {
      localObject = getPackageManager().getInstalledPackages(0);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext()) {
          break label118;
        }
      }
    }
    label69:
    boolean bool;
    if (!isIapAvailable()) {
      if (this.mSamPackageInfo != null)
      {
        bool = true;
        label85:
        MissingSAMDialog.createDialog(this, bool).show();
      }
    }
    for (;;)
    {
      if (paramBundle != null)
      {
        this.mListResult = paramBundle.getBundle("list_result");
        this.mPurchasesListResult = paramBundle.getBundle("purchases_list_result");
      }
      return;
      label118:
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (!localPackageInfo.packageName.equals("com.slideme.sam.manager")) {
        break;
      }
      this.mSamPackageInfo = localPackageInfo;
      break label69;
      bool = false;
      break label85;
      bindService(new Intent("com.slideme.sam.manager.inapp.InAppService.BIND"), this.mServiceConnection, 1);
    }
  }
  
  public Loader<Bundle> onCreateLoader(int paramInt, final Bundle paramBundle)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 1: 
      new AsyncTaskLoader(this)
      {
        public Bundle loadInBackground()
        {
          if (InAppHelperActivity.this.mService != null) {
            try
            {
              localBundle = InAppHelperActivity.this.mService.getIapDetails(1, paramBundle.getStringArrayList("iap_ids"), InAppHelperActivity.this.getPackageName());
              return localBundle;
            }
            catch (RemoteException localRemoteException)
            {
              if (Utils.debug) {
                localRemoteException.printStackTrace();
              }
            }
          }
          while (!Utils.debug)
          {
            Bundle localBundle;
            return null;
          }
          throw new IllegalStateException("loadList() called before onIapReady()");
        }
      };
    case 2: 
      new AsyncTaskLoader(this)
      {
        public Bundle loadInBackground()
        {
          if (InAppHelperActivity.this.mService != null) {
            try
            {
              localBundle = InAppHelperActivity.this.mService.getPurchases(1, paramBundle.getString("purchases_type"), InAppHelperActivity.this.getPackageName());
              return localBundle;
            }
            catch (RemoteException localRemoteException)
            {
              if (Utils.debug) {
                localRemoteException.printStackTrace();
              }
            }
          }
          while (!Utils.debug)
          {
            Bundle localBundle;
            return null;
          }
          throw new IllegalStateException("loadPurchases() called before onIapReady()");
        }
      };
    }
    new AsyncTaskLoader(this)
    {
      public Bundle loadInBackground()
      {
        if (InAppHelperActivity.this.mService != null) {
          try
          {
            localBundle = InAppHelperActivity.this.mService.consume(1, paramBundle.getString("tid"), InAppHelperActivity.this.getPackageName());
            return localBundle;
          }
          catch (RemoteException localRemoteException)
          {
            if (Utils.debug) {
              localRemoteException.printStackTrace();
            }
          }
        }
        while (!Utils.debug)
        {
          Bundle localBundle;
          return null;
        }
        throw new IllegalStateException("consumePurchase() called before onIapReady()");
      }
    };
  }
  
  protected void onDestroy()
  {
    if (this.mIapServiceBound) {
      unbindService(this.mServiceConnection);
    }
    super.onDestroy();
  }
  
  protected abstract void onIapReady();
  
  protected void onListLoaded(ListResult paramListResult) {}
  
  public void onLoadFinished(Loader<Bundle> paramLoader, Bundle paramBundle)
  {
    switch (paramLoader.getId())
    {
    }
    for (;;)
    {
      paramLoader.abandon();
      return;
      this.mListResult = paramBundle;
      onListLoaded(getListResult());
      continue;
      this.mPurchasesListResult = paramBundle;
      onPurchasesLoaded(getPurchasesListResult());
      continue;
      onPurchaseConsumed(paramBundle.getInt("com.slideme.sam.manager.inapp.BUNDLE_STATUS"));
    }
  }
  
  public void onLoaderReset(Loader<Bundle> paramLoader) {}
  
  protected void onPurchaseConsumed(int paramInt) {}
  
  protected void onPurchaseFinished(PurchaseResult paramPurchaseResult) {}
  
  protected void onPurchasesLoaded(PurchasesListResult paramPurchasesListResult) {}
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBundle("list_result", this.mListResult);
    paramBundle.putBundle("purchases_list_state", this.mPurchasesListResult);
    paramBundle.putParcelable("SAM_PACKAGE_INFO", this.mSamPackageInfo);
  }
  
  /* Error */
  public void purchase(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 68	com/slideme/sam/manager/inapp/InAppHelperActivity:mService	Lcom/slideme/sam/manager/inapp/IInAppService;
    //   4: iconst_1
    //   5: aload_1
    //   6: aload_0
    //   7: invokevirtual 350	com/slideme/sam/manager/inapp/InAppHelperActivity:getPackageName	()Ljava/lang/String;
    //   10: aload_2
    //   11: invokeinterface 356 5 0
    //   16: ldc_w 358
    //   19: invokevirtual 213	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   22: checkcast 360	android/app/PendingIntent
    //   25: astore_1
    //   26: aload_1
    //   27: ifnull +22 -> 49
    //   30: aload_0
    //   31: aload_1
    //   32: invokevirtual 364	android/app/PendingIntent:getIntentSender	()Landroid/content/IntentSender;
    //   35: iconst_1
    //   36: new 167	android/content/Intent
    //   39: dup
    //   40: invokespecial 365	android/content/Intent:<init>	()V
    //   43: iconst_0
    //   44: iconst_0
    //   45: iconst_0
    //   46: invokevirtual 369	com/slideme/sam/manager/inapp/InAppHelperActivity:startIntentSenderForResult	(Landroid/content/IntentSender;ILandroid/content/Intent;III)V
    //   49: return
    //   50: astore_1
    //   51: getstatic 374	com/slideme/sam/manager/inapp/Utils:debug	Z
    //   54: ifeq -5 -> 49
    //   57: aload_1
    //   58: invokevirtual 377	android/os/RemoteException:printStackTrace	()V
    //   61: return
    //   62: astore_1
    //   63: getstatic 374	com/slideme/sam/manager/inapp/Utils:debug	Z
    //   66: ifeq -17 -> 49
    //   69: aload_1
    //   70: invokevirtual 378	android/content/IntentSender$SendIntentException:printStackTrace	()V
    //   73: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	InAppHelperActivity
    //   0	74	1	paramString1	String
    //   0	74	2	paramString2	String
    // Exception table:
    //   from	to	target	type
    //   0	26	50	android/os/RemoteException
    //   30	49	62	android/content/IntentSender$SendIntentException
  }
}
