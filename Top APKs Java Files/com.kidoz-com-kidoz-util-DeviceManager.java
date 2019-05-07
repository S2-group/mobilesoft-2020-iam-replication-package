package com.kidoz.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import com.appattach.tracking.appAttachTracking;
import com.kidoz.logic.log.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.conn.util.InetAddressUtils;

public final class DeviceManager
{
  public DeviceManager() {}
  
  private boolean checkIfCameraHardwareExist(Context paramContext)
  {
    boolean bool = false;
    if (paramContext.getPackageManager().hasSystemFeature("android.hardware.camera")) {
      bool = true;
    }
    return bool;
  }
  
  public static String encryptWithMD5(String paramString1, String paramString2)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update((paramString1 + paramString2).getBytes());
      for (paramString1 = new BigInteger(1, localMessageDigest.digest()).toString(16);; paramString1 = "0" + paramString1) {
        if (paramString1.length() >= 32) {
          return paramString1;
        }
      }
      return null;
    }
    catch (Exception paramString1)
    {
      Logger.e(paramString1, "Can't encrypt text: " + paramString1.getMessage());
    }
  }
  
  public static String getBuiltInCameraAppPackageName(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(new Intent("android.media.action.IMAGE_CAPTURE"), 65536);
    if ((paramContext != null) && (paramContext.size() > 0)) {
      return ((ResolveInfo)paramContext.get(0)).activityInfo.packageName;
    }
    return null;
  }
  
  public static String getBuiltInGooglePlayAppPackageName(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.kidoz")), 65536);
    if ((paramContext != null) && (paramContext.size() > 0)) {
      return ((ResolveInfo)paramContext.get(0)).activityInfo.packageName;
    }
    return null;
  }
  
  public static String getBuiltInImagesGalleryAppPackageName(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setType("image/*");
    localIntent.setAction("android.intent.action.GET_CONTENT");
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536);
    if ((paramContext != null) && (paramContext.size() > 0)) {
      return ((ResolveInfo)paramContext.get(0)).activityInfo.packageName;
    }
    return null;
  }
  
  private static String[] getConfigFileParameters()
  {
    String[] arrayOfString = new String[2];
    Object localObject1 = new File(Environment.getRootDirectory().getAbsolutePath() + File.separator + "etc" + File.separator + "kidozlicence.cfg");
    String str;
    try
    {
      if ((((File)localObject1).exists()) && (((File)localObject1).canRead()))
      {
        localObject1 = new BufferedReader(new FileReader((File)localObject1));
        for (;;)
        {
          str = ((BufferedReader)localObject1).readLine();
          if (str == null)
          {
            ((BufferedReader)localObject1).close();
            return arrayOfString;
          }
          if (!str.startsWith("Referral=")) {
            break;
          }
          arrayOfString[0] = str.substring("Referral=".length(), str.length());
        }
        Logger.e(localException1, "Can't read config file: " + localException1.getMessage());
      }
    }
    catch (Exception localException1) {}
    for (;;)
    {
      return arrayOfString;
      if (!str.startsWith("LicenceKey=")) {
        break;
      }
      arrayOfString[1] = str.substring("LicenceKey=".length(), str.length());
      break;
      Object localObject2 = new File(Environment.getRootDirectory().getParentFile().getAbsolutePath() + "etc" + File.separator + "kidozlicence.cfg");
      try
      {
        if ((((File)localObject2).exists()) && (((File)localObject2).canRead()))
        {
          localObject2 = new BufferedReader(new FileReader((File)localObject2));
          for (;;)
          {
            str = ((BufferedReader)localObject2).readLine();
            if (str == null)
            {
              ((BufferedReader)localObject2).close();
              return arrayOfString;
            }
            if (str.startsWith("Referral=")) {
              arrayOfString[0] = str.substring("Referral=".length(), str.length());
            } else if (str.startsWith("LicenceKey=")) {
              arrayOfString[1] = str.substring("LicenceKey=".length(), str.length());
            }
          }
        }
        return arrayOfString;
      }
      catch (Exception localException2) {}
    }
  }
  
  public static String getCurrentDefaultLauncherPackageName(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = paramContext.getPackageManager().resolveActivity((Intent)localObject, 65536).activityInfo.packageName;
    if ((localObject != null) && (!((String)localObject).equals("android")))
    {
      paramContext = (Context)localObject;
      if (!((String)localObject).equals("com.kidoz")) {}
    }
    else
    {
      paramContext = null;
    }
    return paramContext;
  }
  
  public static int getDeviceCurrentRotation(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getRotation();
  }
  
  public static double getDeviceDPIFactor(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    switch (localDisplayMetrics.densityDpi)
    {
    default: 
      return 1.0D;
    case 120: 
      return 0.75D;
    case 160: 
      return 1.0D;
    case 240: 
      return 1.5D;
    case 320: 
      return 2.0D;
    }
    return 3.0D;
  }
  
  public static String getDeviceFirstEmailAccounts(Context paramContext)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    try
    {
      Pattern localPattern = Patterns.EMAIL_ADDRESS;
      paramContext = AccountManager.get(paramContext).getAccounts();
      int j = paramContext.length;
      for (;;)
      {
        if (i >= j)
        {
          if (localArrayList.size() <= 0) {
            break;
          }
          return (String)localArrayList.get(0);
        }
        Object localObject = paramContext[i];
        if (localPattern.matcher(localObject.name).matches()) {
          localArrayList.add(localObject.name);
        }
        i += 1;
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String getDeviceUniqueID(Context paramContext)
  {
    return ((TelephonyManager)((ContextWrapper)paramContext).getBaseContext().getSystemService("phone")).getDeviceId();
  }
  
  public static String getIPAddress(boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject3 = null;
    for (;;)
    {
      try
      {
        Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        localObject2 = localObject3;
        if (!localIterator1.hasNext()) {
          return localObject3;
        }
        localObject2 = localObject3;
        Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
        Object localObject1 = localObject3;
        localObject3 = localObject1;
        localObject2 = localObject1;
        if (localIterator2.hasNext())
        {
          localObject2 = localObject1;
          localObject3 = (InetAddress)localIterator2.next();
          localObject2 = localObject1;
          if (!((InetAddress)localObject3).isLoopbackAddress())
          {
            localObject2 = localObject1;
            localObject3 = ((InetAddress)localObject3).getHostAddress().toUpperCase();
            localObject2 = localObject1;
            boolean bool = InetAddressUtils.isIPv4Address((String)localObject3);
            if (paramBoolean)
            {
              if (bool) {
                localObject1 = localObject3;
              }
            }
            else if (!bool)
            {
              localObject2 = localObject1;
              int i = ((String)localObject3).indexOf('%');
              if (i < 0)
              {
                localObject1 = localObject3;
              }
              else
              {
                localObject2 = localObject1;
                localObject1 = ((String)localObject3).substring(0, i);
              }
            }
          }
        }
      }
      catch (Exception localException)
      {
        return localObject2;
      }
    }
  }
  
  public static boolean getIsKidozSetAsDefaultLauncher(Context paramContext)
  {
    boolean bool2 = false;
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536).activityInfo.packageName;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.equals("com.kidoz")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean getIsTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static Intent getLaunchIntentForPackageName(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
  }
  
  public static String getMACAddress(String paramString)
  {
    localObject1 = null;
    String str = null;
    try
    {
      Iterator localIterator = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      Object localObject2;
      do
      {
        do
        {
          localObject1 = str;
          if (!localIterator.hasNext()) {
            return str;
          }
          localObject1 = str;
          localObject2 = (NetworkInterface)localIterator.next();
          if (paramString == null) {
            break;
          }
          localObject1 = str;
        } while (!((NetworkInterface)localObject2).getName().equalsIgnoreCase(paramString));
        localObject1 = str;
        localObject2 = ((NetworkInterface)localObject2).getHardwareAddress();
      } while (localObject2 == null);
      localObject1 = str;
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      for (;;)
      {
        localObject1 = str;
        if (i >= localObject2.length)
        {
          localObject1 = str;
          if (localStringBuilder.length() > 0)
          {
            localObject1 = str;
            localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
          }
          localObject1 = str;
          str = localStringBuilder.toString();
          break;
        }
        localObject1 = str;
        localStringBuilder.append(String.format("%02X:", new Object[] { Byte.valueOf(localObject2[i]) }));
        i += 1;
      }
      return localObject1;
    }
    catch (Exception paramString) {}
  }
  
  public static String getReferralAndSaveToSharedPreferences(Context paramContext, boolean paramBoolean)
  {
    Object localObject2 = null;
    if (!paramBoolean) {
      localObject2 = getReferralFromSharedPreferences(paramContext);
    }
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = getReferralByAppattachID(paramContext);
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "ANDROID_MARKET";
    }
    ApplicationSharedPreferences.saveSharedPreferencesData(paramContext, "config key", (String)localObject2);
    return localObject2;
  }
  
  private static String getReferralByAppattachID(Context paramContext)
  {
    Object localObject = null;
    appAttachTracking.init(paramContext);
    String str = appAttachTracking.getAppAttachID();
    paramContext = localObject;
    if (str != null)
    {
      paramContext = localObject;
      if (!str.isEmpty())
      {
        paramContext = localObject;
        if (str.length() == 6)
        {
          paramContext = localObject;
          if (str.matches("[0-9]+")) {
            paramContext = "ANDROID_APPATTACH";
          }
        }
      }
    }
    return paramContext;
  }
  
  private static String getReferralFromLicenceConfigFile()
  {
    Object localObject2 = null;
    String[] arrayOfString = getConfigFileParameters();
    Object localObject1 = localObject2;
    if (arrayOfString != null)
    {
      localObject1 = localObject2;
      if (arrayOfString[0] != null)
      {
        localObject1 = localObject2;
        if (arrayOfString[1] != null)
        {
          String str = encryptWithMD5("KIDOZ", arrayOfString[0]);
          localObject1 = localObject2;
          if (arrayOfString[1].equals(str)) {
            localObject1 = arrayOfString[0];
          }
        }
      }
    }
    return localObject1;
  }
  
  private static String getReferralFromSharedPreferences(Context paramContext)
  {
    return ApplicationSharedPreferences.loadSharedPreferencesData(paramContext, "config key");
  }
  
  public static int getScreenSize(Context paramContext, boolean paramBoolean)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint;
    if (paramBoolean)
    {
      if (Build.VERSION.SDK_INT >= 19)
      {
        localPoint = new Point();
        paramContext.getRealSize(localPoint);
        return localPoint.x;
      }
      return paramContext.getWidth();
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      localPoint = new Point();
      paramContext.getRealSize(localPoint);
      return localPoint.y;
    }
    return paramContext.getHeight();
  }
  
  public static String getUserSelectedDefaultLauncherClassName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = paramContext.queryIntentActivities((Intent)localObject, 65536);
    Collections.sort((List)localObject, new ResolveInfo.DisplayNameComparator(paramContext));
    paramContext = ((List)localObject).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localObject = (ResolveInfo)paramContext.next();
    } while (!paramString.equals(((ResolveInfo)localObject).activityInfo.packageName));
    return ((ResolveInfo)localObject).activityInfo.name;
  }
  
  public static String getVideoPlayerPackageName(Context paramContext)
  {
    Object localObject1 = null;
    Object localObject2 = paramContext.getPackageManager().getInstalledApplications(0);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= ((List)localObject2).size())
      {
        paramContext = paramContext.getPackageManager();
        localObject2 = new Intent("android.intent.action.VIEW");
        ((Intent)localObject2).setType("video/*");
        localObject2 = paramContext.queryIntentActivities((Intent)localObject2, 1);
        paramContext = localObject1;
        if (localObject2 != null)
        {
          paramContext = localObject1;
          if (((List)localObject2).size() > 0)
          {
            paramContext = localObject1;
            if (!localArrayList.contains(((ResolveInfo)((List)localObject2).get(0)).activityInfo.packageName)) {
              paramContext = ((ResolveInfo)((List)localObject2).get(0)).activityInfo.packageName;
            }
          }
        }
        return paramContext;
      }
      if (paramContext.getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)((List)localObject2).get(i)).packageName) != null) {
        localArrayList.add(((ApplicationInfo)((List)localObject2).get(i)).packageName);
      }
      i += 1;
    }
  }
  
  public static String getWiFiActivityClassName(Context paramContext)
  {
    Intent localIntent = new Intent("android.settings.WIRELESS_SETTINGS");
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536);
    if ((paramContext != null) && (paramContext.size() > 0) && (((ResolveInfo)paramContext.get(0)).activityInfo != null)) {
      return ((ResolveInfo)paramContext.get(0)).activityInfo.name;
    }
    return null;
  }
  
  public static boolean isFlashInstalled(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo("com.adobe.flashplayer", 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isReferralValidationSuccessful()
  {
    return true;
  }
  
  public static boolean isWorkAroundJellyBeanDefaultLauncherNeeded(Context paramContext)
  {
    Object localObject;
    Intent localIntent;
    int i;
    if (Build.VERSION.SDK_INT > 15)
    {
      localObject = paramContext.getPackageManager();
      localIntent = new Intent("android.intent.action.MAIN", null);
      localIntent.addCategory("android.intent.category.HOME");
      localObject = ((PackageManager)localObject).queryIntentActivities(localIntent, 65536);
      i = 0;
      localObject = ((List)localObject).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if ((i > 1) && (!paramContext.getPackageManager().resolveActivity(localIntent, 0).activityInfo.packageName.equals("android"))) {
          break;
        }
        return false;
      }
      if (!((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.equals("com.kidoz")) {
        i += 1;
      }
    }
    return true;
  }
  
  public static boolean setOrientation(Context paramContext, Activity paramActivity)
  {
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 3)
    {
      paramActivity.setRequestedOrientation(0);
      return true;
    }
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 2)
    {
      paramActivity.setRequestedOrientation(1);
      return false;
    }
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 1)
    {
      paramActivity.setRequestedOrientation(1);
      return false;
    }
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 4)
    {
      paramActivity.setRequestedOrientation(0);
      return true;
    }
    paramActivity.setRequestedOrientation(1);
    return false;
  }
}
