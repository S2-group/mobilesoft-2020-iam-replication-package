package org.onepf.oms.appstore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import org.onepf.oms.AppstoreInAppBillingService;
import org.onepf.oms.DefaultAppstore;
import org.onepf.oms.appstore.googleUtils.IabHelper;

public class GooglePlay
  extends DefaultAppstore
{
  private static final String ANDROID_INSTALLER = "com.android.vending";
  private static final String GOOGLE_INSTALLER = "com.google.vending";
  private static final String TAG = GooglePlay.class.getSimpleName();
  public static final String VENDING_ACTION = "com.android.vending.billing.InAppBillingService.BIND";
  private final boolean isDebugMode = false;
  private IabHelper mBillingService;
  private Context mContext;
  private String mPublicKey;
  
  public GooglePlay(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.mPublicKey = paramString;
  }
  
  public String getAppstoreName()
  {
    return "com.google.play";
  }
  
  public AppstoreInAppBillingService getInAppBillingService()
  {
    if (this.mBillingService == null) {
      this.mBillingService = new IabHelper(this.mContext, this.mPublicKey, this);
    }
    return this.mBillingService;
  }
  
  public int getPackageVersion(String paramString)
  {
    return -1;
  }
  
  public boolean isBillingAvailable(String paramString)
  {
    boolean bool2 = false;
    Log.d(TAG, "isBillingAvailable() packageName: " + paramString);
    paramString = this.mContext.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      bool1 = bool2;
      if (!paramString.hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)paramString.next();
    } while ((!localPackageInfo.packageName.equals("com.google.vending")) && (!localPackageInfo.packageName.equals("com.android.vending")));
    Log.d(TAG, "Google supports billing");
    boolean bool1 = true;
    return bool1;
  }
  
  public boolean isPackageInstaller(String paramString)
  {
    paramString = this.mContext.getPackageManager().getInstallerPackageName(paramString);
    return (paramString != null) && (paramString.equals("com.android.vending"));
  }
}
