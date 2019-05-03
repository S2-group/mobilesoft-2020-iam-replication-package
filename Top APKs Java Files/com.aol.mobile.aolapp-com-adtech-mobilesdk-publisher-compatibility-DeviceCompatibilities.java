package com.adtech.mobilesdk.publisher.compatibility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.adtech.mobilesdk.commons.log.SDKLogger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class DeviceCompatibilities
{
  private static final SDKLogger LOGGER = SDKLogger.getInstance(DeviceCompatibilities.class);
  private static final String MAJOR_VERSION_DELIMITER = ".";
  private static final String PACKAGE_COM_ADOBE_FLASHPLAYER = "com.adobe.flashplayer";
  public static final List<String> SUPPORTED_IMAGE_EXTENSIONS = Arrays.asList(new String[] { "jpg", "jpeg", "png", "gif", "bmp", "webp" });
  private static String flashVersion = null;
  private static boolean isFlashVersionFetched = false;
  
  private DeviceCompatibilities() {}
  
  public static String getFlashPlayerVersion(Context paramContext)
  {
    if (isFlashVersionFetched) {
      return flashVersion;
    }
    try
    {
      LOGGER.v("Trying to obtain flash player version...");
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (localPackageInfo.packageName.toLowerCase(Locale.ENGLISH).contains("com.adobe.flashplayer"))
        {
          LOGGER.v("Found version name: " + localPackageInfo.versionName);
          flashVersion = getMajorVersion(localPackageInfo.versionName);
          isFlashVersionFetched = true;
          return flashVersion;
        }
      }
      isFlashVersionFetched = true;
      paramContext = flashVersion;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LOGGER.d("Failed to read flash player version.", paramContext);
    }
    return null;
  }
  
  private static String getMajorVersion(String paramString)
  {
    try
    {
      String str = new StringTokenizer(paramString, ".").nextToken();
      LOGGER.d("Will return flash version: " + str);
      return str;
    }
    catch (Exception localException)
    {
      LOGGER.w("Error parsing flash version: " + paramString);
    }
    return null;
  }
  
  public static boolean isFlashVersionFetched()
  {
    return isFlashVersionFetched;
  }
  
  public static boolean isHardwareAccelerationSupported()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static void setFlashVersionFetched(boolean paramBoolean)
  {
    isFlashVersionFetched = paramBoolean;
  }
}
