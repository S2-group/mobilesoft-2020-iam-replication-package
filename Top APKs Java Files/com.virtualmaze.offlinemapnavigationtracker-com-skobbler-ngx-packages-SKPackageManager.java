package com.skobbler.ngx.packages;

import android.net.Uri;
import android.os.Build.VERSION;
import com.skobbler.ngx.SKMaps;
import com.skobbler.ngx.SKMapsInitSettings;
import com.skobbler.ngx.SKMapsInitializationException;
import com.skobbler.ngx.util.SKLogging;
import com.skobbler.ngx.versioning.SKVersioningManager;

public final class SKPackageManager
{
  public static final int ADD_PACKAGE_CANNOT_ERASE_FILE_RESULT = 10;
  public static final int ADD_PACKAGE_MISSING_NGI_DAT_RESULT = 8;
  public static final int ADD_PACKAGE_MISSING_NGI_RESULT = 4;
  public static final int ADD_PACKAGE_MISSING_SKM_RESULT = 2;
  public static final int ADD_PACKAGE_MISSING_TXG_RESULT = 1;
  public static final int ADD_PACKAGE_SUCCESS_RESULT = 0;
  private static volatile SKPackageManager a = null;
  
  static
  {
    System.loadLibrary("ngnative");
  }
  
  public SKPackageManager() {}
  
  private String a(int paramInt, String paramString)
  {
    if (SKMaps.getInstance().isSKMapsInitialized())
    {
      String str1 = getmapbaseurl();
      Object localObject = SKMaps.getInstance().getMapInitSettings();
      String str2 = ((SKMapsInitSettings)localObject).getAppName();
      localObject = ((SKMapsInitSettings)localObject).getAppVersion();
      String str3 = Uri.encode(Build.VERSION.RELEASE);
      return str1 + paramInt + "/" + paramString + "?appName=" + Uri.encode(str2) + "&appVer=" + (String)localObject + "&osName=android&osVersion=" + str3 + "&uid=" + SKMaps.getInstance().getUserId();
    }
    throw new SKMapsInitializationException();
  }
  
  private native int addpackage(String paramString1, String paramString2, int paramInt);
  
  private native boolean checkPackage(String paramString1, String paramString2);
  
  private native void deleteallpackages(int paramInt);
  
  private native boolean deletepackage(String paramString);
  
  public static SKPackageManager getInstance()
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new SKPackageManager();
      }
      return a;
    }
    finally {}
  }
  
  private native String getmapbaseurl();
  
  private native SKPackage[] getpackagelist();
  
  public final int addOfflinePackage(String paramString1, String paramString2)
  {
    if (SKMaps.getInstance().isSKMapsInitialized())
    {
      if ((paramString1 != null) && (paramString2 != null))
      {
        SKLogging.writeLog("SKPackageManager", "@addOfflinePackage " + paramString1 + " " + paramString2 + ", getlocalmapversion() = " + SKVersioningManager.getInstance().getLocalMapVersion(), 0);
        int i = addpackage(paramString1, paramString2, SKVersioningManager.getInstance().getLocalMapVersion());
        SKLogging.writeLog("SKPackageManager", "@addOfflinePackage result  " + i, 0);
        return i;
      }
      return -1;
    }
    throw new SKMapsInitializationException();
  }
  
  public final void deleteAllOfflinePackages(int paramInt)
  {
    SKLogging.writeLog("SKPackageManager", "@deleteAllOfflinePackages versionNumber  " + paramInt, 0);
    if (SKMaps.getInstance().isSKMapsInitialized())
    {
      deleteallpackages(paramInt);
      return;
    }
    throw new SKMapsInitializationException();
  }
  
  public final boolean deleteOfflinePackage(String paramString)
  {
    SKLogging.writeLog("SKPackageManager", "@deleteOfflinePackage package=   " + paramString, 0);
    if (SKMaps.getInstance().isSKMapsInitialized()) {
      return deletepackage(paramString);
    }
    throw new SKMapsInitializationException();
  }
  
  public final SKPackage[] getInstalledPackages()
  {
    return getpackagelist();
  }
  
  public final String getMapsDownloadBasePath()
  {
    if (SKMaps.getInstance().isSKMapsInitialized())
    {
      String str = getmapbaseurl() + SKVersioningManager.getInstance().getLocalMapVersion();
      SKLogging.writeLog("SKPackageManager", "mapDownloadBasePath  " + str, 0);
      return str;
    }
    throw new SKMapsInitializationException();
  }
  
  public final String getMapsJSONPathForCurrentVersion()
  {
    String str = a(SKVersioningManager.getInstance().getLocalMapVersion(), "Maps.json");
    SKLogging.writeLog("SKPackageManager", "@getMapsXMLPathForCurrentVersion " + str, 0);
    return str;
  }
  
  public final String getMapsJSONPathForVersion(int paramInt)
  {
    return a(paramInt, "Maps.json");
  }
  
  public final String getMapsXMLPathForCurrentVersion()
  {
    String str = a(SKVersioningManager.getInstance().getLocalMapVersion(), "Maps.xml");
    SKLogging.writeLog("SKPackageManager", "@getMapsXMLPathForCurrentVersion " + str, 0);
    return str;
  }
  
  public final String getMapsXMLPathForVersion(int paramInt)
  {
    return a(paramInt, "Maps.xml");
  }
  
  public final SKPackageURLInfo getURLInfoForPackageWithCode(String paramString, Integer paramInteger, boolean paramBoolean)
  {
    if (SKMaps.getInstance().isSKMapsInitialized())
    {
      Object localObject = SKMaps.getInstance().getMapInitSettings();
      if (paramString != null)
      {
        SKPackageURLInfo localSKPackageURLInfo = new SKPackageURLInfo();
        String str1 = SKMaps.getInstance().getObfuscatedApiKey();
        Integer localInteger = paramInteger;
        if (paramInteger == null) {
          localInteger = Integer.valueOf(SKVersioningManager.getInstance().getLocalMapVersion());
        }
        String str2;
        if (((SKMapsInitSettings)localObject).getMapDetailLevel() != null)
        {
          paramInteger = ((SKMapsInitSettings)localObject).getMapDetailLevel();
          str2 = ((SKMapsInitSettings)localObject).getAppName();
          localObject = ((SKMapsInitSettings)localObject).getAppVersion();
          String str3 = Uri.encode(Build.VERSION.RELEASE);
          str2 = "?appName=" + Uri.encode(str2) + "&appVer=" + (String)localObject + "&osName=android&osVersion=" + str3 + "&uid=" + SKMaps.getInstance().getUserId();
          if (!paramBoolean) {
            break label510;
          }
        }
        label510:
        for (localObject = "/custom-packages/";; localObject = "/package/")
        {
          paramInteger = "http://" + str1 + "." + "http://cache.sko.fm/ngxmaps/versioned/".replaceAll("http://", "") + paramInteger + "v1/" + localInteger + (String)localObject;
          localSKPackageURLInfo.setTexturesURL(paramInteger + "textures/" + paramString + ".txg" + str2);
          localSKPackageURLInfo.setNameBrowserFilesURL(paramInteger + "nb_files/" + paramString + ".zip" + str2);
          localSKPackageURLInfo.setMapURL(paramInteger + paramString + ".skm" + str2);
          localSKPackageURLInfo.setNgiDatFileURL(paramInteger + "nb_files/" + paramString + ".ngi.dat" + str2);
          localSKPackageURLInfo.setNgiFileURL(paramInteger + "nb_files/" + paramString + ".ngi" + str2);
          localSKPackageURLInfo.setSynFileURL(paramInteger + "nb_files/" + paramString + ".syn" + str2);
          localSKPackageURLInfo.setElevationPackageURL(paramInteger + "world/" + paramString + "_elev.zip" + str2);
          SKLogging.writeLog("SKPackageManager", "@getURLInfoForPackageWithCode " + localSKPackageURLInfo.toString(), 0);
          return localSKPackageURLInfo;
          paramInteger = "full/";
          break;
        }
      }
      return null;
    }
    throw new SKMapsInitializationException();
  }
  
  public final boolean isValidMapPackage(String paramString1, String paramString2)
  {
    SKLogging.writeLog("SKPackageManager", "@isValidMapPackage containingFolderPath=   " + paramString1 + "   packageName=  " + paramString2, 0);
    if (SKMaps.getInstance().isSKMapsInitialized()) {
      return checkPackage(paramString1, paramString2 + ".skm");
    }
    throw new SKMapsInitializationException();
  }
}
