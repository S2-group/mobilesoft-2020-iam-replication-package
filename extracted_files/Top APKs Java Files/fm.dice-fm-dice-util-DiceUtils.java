package fm.dice.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DiceUtils
{
  public static double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    Double localDouble1 = Double.valueOf(Math.toRadians(paramDouble2 - paramDouble1));
    Double localDouble2 = Double.valueOf(Math.toRadians(paramDouble4 - paramDouble3));
    paramDouble3 = Math.sin(localDouble1.doubleValue() / 2.0D);
    paramDouble4 = Math.sin(localDouble1.doubleValue() / 2.0D);
    paramDouble1 = Math.cos(Math.toRadians(paramDouble1));
    paramDouble2 = Math.cos(Math.toRadians(paramDouble2));
    double d = Math.sin(localDouble2.doubleValue() / 2.0D);
    localDouble1 = Double.valueOf(Math.sin(localDouble2.doubleValue() / 2.0D) * (paramDouble1 * paramDouble2 * d) + paramDouble3 * paramDouble4);
    return Math.sqrt(Math.pow(Double.valueOf(Math.atan2(Math.sqrt(localDouble1.doubleValue()), Math.sqrt(1.0D - localDouble1.doubleValue())) * 2.0D).doubleValue() * 6371.0D * 1000.0D, 2.0D));
  }
  
  public static String a(double paramDouble1, double paramDouble2)
  {
    String str1 = String.format(Locale.UK, "%.5f", new Object[] { Double.valueOf(paramDouble1) });
    String str2 = String.format(Locale.UK, "%.5f", new Object[] { Double.valueOf(paramDouble2) });
    return str1 + "," + str2;
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso().toUpperCase();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
}
