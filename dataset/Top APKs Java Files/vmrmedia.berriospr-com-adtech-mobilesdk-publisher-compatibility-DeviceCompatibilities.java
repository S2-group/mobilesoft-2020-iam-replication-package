package com.adtech.mobilesdk.publisher.compatibility;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.adtech.mobilesdk.publisher.log.SDKLogger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class DeviceCompatibilities
{
  private static final SDKLogger LOGGER = SDKLogger.getInstance(DeviceCompatibilities.class);
  public static final List<String> SUPPORTED_IMAGE_EXTENSIONS = Arrays.asList(new String[] { "jpg", "jpeg", "png", "gif", "bmp", "webp" });
  private static String flashVersion = null;
  private static boolean isFlashVersionFetched = false;
  
  private DeviceCompatibilities() {}
  
  public static String getFlashPlayerVersion(PackageManager paramPackageManager)
  {
    if (isFlashVersionFetched) {
      return flashVersion;
    }
    try
    {
      LOGGER.v("Trying to obtain flash player version...");
      paramPackageManager = paramPackageManager.getInstalledPackages(8192).iterator();
      while (paramPackageManager.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramPackageManager.next();
        if (localPackageInfo.packageName.toLowerCase(Locale.ENGLISH).contains("com.adobe.flashplayer"))
        {
          LOGGER.v("Found version name: " + localPackageInfo.versionName);
          flashVersion = getMajorVersion(localPackageInfo.versionName);
          isFlashVersionFetched = true;
          return flashVersion;
        }
      }
      isFlashVersionFetched = true;
      paramPackageManager = flashVersion;
      return paramPackageManager;
    }
    catch (Exception paramPackageManager)
    {
      LOGGER.d("Failed to read flash player version.", paramPackageManager);
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
  
  public static boolean isHardwareAccelerationSupported()
  {
    return true;
  }
}
