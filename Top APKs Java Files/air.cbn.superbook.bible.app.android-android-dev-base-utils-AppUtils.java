package android.dev.base.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppUtils
{
  private AppUtils()
  {
    throw new UnsupportedOperationException("cannot be instantiated");
  }
  
  public static Drawable getAPKIconDrawable(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getPackageArchiveInfo(paramString, 0);
    if (localObject != null)
    {
      localObject = ((PackageInfo)localObject).applicationInfo;
      ((ApplicationInfo)localObject).sourceDir = paramString;
      ((ApplicationInfo)localObject).publicSourceDir = paramString;
      return ((ApplicationInfo)localObject).loadIcon(paramContext);
    }
    return null;
  }
  
  public static PackageInfo getAPKPackageInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 0);
    if (paramContext != null)
    {
      paramContext.applicationInfo.sourceDir = paramString;
      paramContext.applicationInfo.publicSourceDir = paramString;
    }
    return paramContext;
  }
  
  public static List<PackageInfo> getAllPackageInfo(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static Drawable getAppIconDrawable(Context paramContext, PackageInfo paramPackageInfo)
  {
    paramContext = paramContext.getPackageManager();
    if (paramPackageInfo != null) {
      return paramPackageInfo.applicationInfo.loadIcon(paramContext);
    }
    return null;
  }
  
  public static Drawable getAppIconDrawable(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).applicationInfo.loadIcon(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String getAppName(Context paramContext, PackageInfo paramPackageInfo)
  {
    return paramPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
  }
  
  public static String getAppName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).applicationInfo.loadLabel(paramContext).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String getAppSignature(Context paramContext, String paramString)
  {
    try
    {
      paramContext = hexDigest(paramContext.getPackageManager().getPackageInfo(paramString, 0).signatures[0].toByteArray());
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).applicationInfo;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  @RequiresPermission("android.permission.READ_PHONE_STATE")
  public static String getDeviceId(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }
  
  public static List<PackageInfo> getInstallPackageInfo(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
        paramContext.add(localPackageInfo);
      }
    }
    return paramContext;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace(System.err);
    }
    return null;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String getPhoneType()
  {
    return Build.MODEL;
  }
  
  public static List<PackageInfo> getSystemPackageInfo(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) > 0) {
        paramContext.add(localPackageInfo);
      }
    }
    return paramContext;
  }
  
  private static String hexDigest(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[16];
    char[] tmp8_6 = arrayOfChar;
    tmp8_6[0] = 48;
    char[] tmp13_8 = tmp8_6;
    tmp13_8[1] = 49;
    char[] tmp18_13 = tmp13_8;
    tmp18_13[2] = 50;
    char[] tmp23_18 = tmp18_13;
    tmp23_18[3] = 51;
    char[] tmp28_23 = tmp23_18;
    tmp28_23[4] = 52;
    char[] tmp33_28 = tmp28_23;
    tmp33_28[5] = 53;
    char[] tmp38_33 = tmp33_28;
    tmp38_33[6] = 54;
    char[] tmp44_38 = tmp38_33;
    tmp44_38[7] = 55;
    char[] tmp50_44 = tmp44_38;
    tmp50_44[8] = 56;
    char[] tmp56_50 = tmp50_44;
    tmp56_50[9] = 57;
    char[] tmp62_56 = tmp56_50;
    tmp62_56[10] = 97;
    char[] tmp68_62 = tmp62_56;
    tmp68_62[11] = 98;
    char[] tmp74_68 = tmp68_62;
    tmp74_68[12] = 99;
    char[] tmp80_74 = tmp74_68;
    tmp80_74[13] = 100;
    char[] tmp86_80 = tmp80_74;
    tmp86_80[14] = 101;
    char[] tmp92_86 = tmp86_80;
    tmp92_86[15] = 102;
    tmp92_86;
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramArrayOfByte);
      paramArrayOfByte = ((MessageDigest)localObject).digest();
      localObject = new char[32];
      int i = 0;
      int j = 0;
      for (;;)
      {
        if (i >= 16) {
          return new String((char[])localObject);
        }
        int k = paramArrayOfByte[i];
        localObject[j] = arrayOfChar[(k >>> 4 & 0xF)];
        j += 1;
        localObject[j] = arrayOfChar[(k & 0xF)];
        i += 1;
        j += 1;
      }
      return "";
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
  }
  
  public static void installApk(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(paramUri, "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
  
  public static void installApk(Context paramContext, String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.exists())
    {
      Toast.makeText(paramContext, "apk file is not exist", 0).show();
      return;
    }
    installApk(paramContext, Uri.fromFile(paramString));
  }
  
  public static boolean isAllowInstallUnknownApp(Context paramContext)
  {
    try
    {
      int i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
      return i == 1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString, int paramInt)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        int i = paramContext.versionCode;
        bool1 = bool2;
        if (i == paramInt) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static void uninstallApk(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    localIntent.setData(Uri.parse("package:" + paramString));
    paramContext.startActivity(localIntent);
  }
}
