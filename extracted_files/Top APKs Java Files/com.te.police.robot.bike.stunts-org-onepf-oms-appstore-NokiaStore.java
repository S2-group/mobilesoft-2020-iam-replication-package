package org.onepf.oms.appstore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.onepf.oms.AppstoreInAppBillingService;
import org.onepf.oms.DefaultAppstore;
import org.onepf.oms.appstore.nokiaUtils.NokiaSkuFormatException;
import org.onepf.oms.appstore.nokiaUtils.NokiaStoreHelper;
import org.onepf.oms.util.Logger;

public class NokiaStore
  extends DefaultAppstore
{
  private static final String EXPECTED_SHA1_FINGERPRINT = "C476A7D71C4CB92641A699C1F1CAC93CA81E0396";
  public static final String NOKIA_BILLING_PERMISSION = "com.nokia.payment.BILLING";
  public static final String NOKIA_INSTALLER = "com.nokia.payment.iapenabler";
  public static final String VENDING_ACTION = "com.nokia.payment.iapenabler.InAppBillingService.BIND";
  @Nullable
  private NokiaStoreHelper billingService = null;
  private final Context context;
  
  public NokiaStore(Context paramContext)
  {
    Logger.i("NokiaStore.NokiaStore");
    this.context = paramContext;
  }
  
  public static void checkSku(@NotNull String paramString)
  {
    if (!TextUtils.isDigitsOnly(paramString)) {
      throw new NokiaSkuFormatException();
    }
  }
  
  @NotNull
  private static byte[] hexStringToByteArray(@NotNull String paramString)
  {
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[(i / 2)] = ((byte)((Character.digit(paramString.charAt(i), 16) << 4) + Character.digit(paramString.charAt(i + 1), 16)));
      i += 2;
    }
    return arrayOfByte;
  }
  
  private boolean verifyFingreprint()
  {
    try
    {
      Object localObject = this.context.getPackageManager().getPackageInfo("com.nokia.payment.iapenabler", 64);
      if (((PackageInfo)localObject).signatures.length == 1)
      {
        localObject = localObject.signatures[0].toByteArray();
        if (Arrays.equals(MessageDigest.getInstance("SHA1").digest((byte[])localObject), hexStringToByteArray("C476A7D71C4CB92641A699C1F1CAC93CA81E0396")))
        {
          Logger.i(new Object[] { "isBillingAvailable", "NIAP signature verified" });
          return true;
        }
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  public String getAppstoreName()
  {
    return "com.nokia.nstore";
  }
  
  @Nullable
  public AppstoreInAppBillingService getInAppBillingService()
  {
    if (this.billingService == null) {
      this.billingService = new NokiaStoreHelper(this.context, this);
    }
    return this.billingService;
  }
  
  public int getPackageVersion(String paramString)
  {
    Logger.d("getPackageVersion: packageName = " + paramString);
    return -1;
  }
  
  public boolean isBillingAvailable(String paramString)
  {
    boolean bool2 = false;
    Logger.i("NokiaStore.isBillingAvailable");
    Logger.d(new Object[] { "packageName = ", paramString });
    paramString = this.context.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramString.hasNext()) {
        break;
      }
    } while (!"com.nokia.payment.iapenabler".equals(((PackageInfo)paramString.next()).packageName));
    boolean bool1 = verifyFingreprint();
    return bool1;
  }
  
  public boolean isPackageInstaller(String paramString)
  {
    Logger.d(new Object[] { "sPackageInstaller: packageName = ", paramString });
    paramString = this.context.getPackageManager().getInstallerPackageName(paramString);
    Logger.d(new Object[] { "installerPackageName = ", paramString });
    return "com.nokia.payment.iapenabler".equals(paramString);
  }
}
