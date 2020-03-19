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
import org.onepf.oms.AppstoreInAppBillingService;
import org.onepf.oms.DefaultAppstore;
import org.onepf.oms.appstore.nokiaUtils.NokiaSkuFormatException;
import org.onepf.oms.appstore.nokiaUtils.NokiaStoreHelper;
import org.onepf.oms.util.Logger;

public class NokiaStore
  extends DefaultAppstore
{
  private static final byte[] $ = { 85, -106, -100, -22, -2, 57, -31, 5, 8, 11, 3, 15, 46, -45, 29, 0, 5, 19, 7, -2, 15, 23, 6, 0, 18, 2, 10, 31, -11, -4, 16, 77, -21, 37, 43, -9, 6, 0, 18, 2, 10, 36, -29, 3, 7, 27, -3, 8, 15, -5, 64, 34, -72, 23, 6, 0, 18, 2, 10, 31, -11, -4, 16, 77, -21, 37, -4, 10, 71, -56, 7, 12, 10, 16, 59, -58, 23, -16, 20, 16, -1, 2, 78, -51, 16, -7, 19, -1, 21, 7, -2, 15, -5, 76, -19, -29, 53, -39, 8, 54, -31, 5, 8, 11, 3, 15, 28, -10, -5, 4, 21, 14, 6, 63, -12, 1, 3, 18, -25, 12, 10, 16, 22, -25, 13, 5, 21, 63, -24, -25, 12, 10, 16, 22, -25, 13, 5, 21, -4, 10, 71, -56, 7, 12, 10, 16, 59, -56, 3, 7, 13, 5, 21, 23, 5, 9, -3, 18, -5, 21, 14, -10, 23, -7, 9, 17, 15, 4, 10, 11, -8, 19, 5, 8, -2, 26, -13, 29, -10, 10, 6, 18, 14, -8, 10, 17, 15, -12, 29, 5, 2, 11, 10, -7, 44, -9, 6, 0, 18, 2, 10, 23, -7, -5, 7, 18, 2, 9, 60, 34, -72, 23, 6, 0, 18, 2, 10, 31, -11, -4, 16, 77, -21, 37, -4, 10, 71, -56, 7, 12, 10, 16, 59, -58, 23, -16, 20, 16, -1, 2, 78, -12, 1, 5, 8, 11, 3, 15, 3, 3, 7, 27, -3, 8, 15, -5, 42, -9, 6, 0, 18, 2, 10, 31, -11, -4, 16, 77, -21, 37, 13, 16, -7, 56, -75, 18, 10, 1, 21, -11, 7, 11, 21, 77, -78, 25, -5, 17, 11, 5, 12, 9, -4, 10, 71, -56, 7, 12, 10, 16, 59, -58, 23, -16, 20, 16, -1, 2, 78, -51, 16, -7, 19, -1, 21, 7, -2, 15, -5, -25, 12, 10, 16, 22, -25, 13, 5, 21, 63, -51, -2, 57, -31, 5, 8, 11, 3, 15, 46, -45, 29, 0, 5, 19, 7, -2, 15, 19, 15, 24 };
  private static int $$ = 85;
  private static final String EXPECTED_SHA1_FINGERPRINT = "C476A7D71C4CB92641A699C1F1CAC93CA81E0396";
  public static final String NOKIA_BILLING_PERMISSION = "com.nokia.payment.BILLING";
  public static final String NOKIA_INSTALLER = "com.nokia.payment.iapenabler";
  public static final String VENDING_ACTION = "com.nokia.payment.iapenabler.InAppBillingService.BIND";
  private NokiaStoreHelper billingService = null;
  private final Context context;
  
  private static String $(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 346 - paramInt2;
    paramInt2 = 53 - paramInt3;
    paramInt3 = 0;
    int m = 0;
    paramInt1 += 67;
    byte[] arrayOfByte1 = $;
    byte[] arrayOfByte2 = new byte[paramInt2];
    int k = paramInt2 - 1;
    paramInt2 = paramInt1;
    int j = i;
    if (arrayOfByte1 == null)
    {
      paramInt3 = k;
      j = paramInt1;
      paramInt2 = i;
      paramInt1 = m;
      i = j;
    }
    for (;;)
    {
      paramInt3 = paramInt3 + -i + 8;
      j = paramInt2;
      paramInt2 = paramInt3;
      paramInt3 = paramInt1;
      arrayOfByte2[paramInt3] = ((byte)paramInt2);
      j += 1;
      paramInt1 = paramInt3 + 1;
      if (paramInt3 == k) {
        return new String(arrayOfByte2, 0).intern();
      }
      i = arrayOfByte1[j];
      paramInt3 = paramInt2;
      paramInt2 = j;
    }
  }
  
  public NokiaStore(Context paramContext)
  {
    Logger.i($($[9], 229, 32));
    this.context = paramContext;
  }
  
  public static void checkSku(String paramString)
  {
    if (!TextUtils.isDigitsOnly(paramString)) {
      throw new NokiaSkuFormatException();
    }
  }
  
  private static byte[] hexStringToByteArray(String paramString)
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
      Object localObject = this.context.getPackageManager();
      int i = $[27] + 1;
      localObject = ((PackageManager)localObject).getPackageInfo($(i, i | 0x17, $['ĝ']), 64);
      if (((PackageInfo)localObject).signatures.length == 1)
      {
        localObject = localObject.signatures[0].toByteArray();
        i = $[30];
        int j = $[15];
        localObject = MessageDigest.getInstance($(i, j, j | 0x31)).digest((byte[])localObject);
        i = $[15];
        if (Arrays.equals((byte[])localObject, hexStringToByteArray($(i, i | 0xC2, $[124]))))
        {
          Logger.i(new Object[] { $($[33] + 1, $$ | 0x102, $[41] - 1), $($[9], $[31], $[27] - 1) });
          return true;
        }
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public String getAppstoreName()
  {
    return $($[27] + 1, 209, $[33]);
  }
  
  public AppstoreInAppBillingService getInAppBillingService()
  {
    if (this.billingService == null) {
      this.billingService = new NokiaStoreHelper(this.context, this);
    }
    return this.billingService;
  }
  
  public int getPackageVersion(String paramString)
  {
    Logger.d($($[41], 155, $[78]) + paramString);
    return -1;
  }
  
  public boolean isBillingAvailable(String paramString)
  {
    Logger.i($($[9], $[106], $['ŝ']));
    Logger.d(new Object[] { $(-$[13], 326, -$[97]), paramString });
    paramString = this.context.getPackageManager().getInstalledPackages(0).iterator();
    while (paramString.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramString.next();
      int i = $[27] + 1;
      if ($(i, i | 0x17, $['ĝ']).equals(localPackageInfo.packageName)) {
        return verifyFingreprint();
      }
    }
    return false;
  }
  
  public boolean isPackageInstaller(String paramString)
  {
    Logger.d(new Object[] { $(48, 313, $[78]), paramString });
    paramString = this.context.getPackageManager().getInstallerPackageName(paramString);
    Logger.d(new Object[] { $($[33] + 1, 99, $[27] - 1), paramString });
    int i = $[27] + 1;
    return $(i, i | 0x17, $['ĝ']).equals(paramString);
  }
}
