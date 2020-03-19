package com.geocomply.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.geocomply.RootTools.RootTools;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Rooted
{
  private static boolean sCheckRootMethod6 = false;
  private static boolean sCheckRootMethod8 = false;
  private static boolean sIs6checked = false;
  private static boolean sIs8checked = false;
  
  public Rooted() {}
  
  public static boolean checkRootMethod1()
  {
    GCLogger.info("Try to check a root by using method #1.");
    String str = Build.TAGS;
    return (str != null) && (str.contains("test-keys"));
  }
  
  public static boolean checkRootMethod2()
  {
    GCLogger.info("Try to check a root by using method #2.");
    PackageManager localPackageManager = WeakContext.getContext().getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(0);
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "com.koushikdutta.superuser";
    arrayOfString[1] = "com.noshufou.android.su";
    arrayOfString[2] = "eu.chainfire.supersu";
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        try
        {
          localObject2 = localPackageManager.getApplicationInfo(((PackageInfo)localObject2).packageName, 0);
          int j = arrayOfString.length;
          int i = 0;
          while (i < j)
          {
            String str = arrayOfString[i];
            if (((ApplicationInfo)localObject2).packageName.contains(str))
            {
              GCLogger.info("Device contains: " + ((ApplicationInfo)localObject2).packageName);
              return true;
            }
            i += 1;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
      }
    }
    return false;
  }
  
  public static boolean checkRootMethod3()
  {
    GCLogger.info("Try to check a root by using method #3.");
    GCLogger.warn("The method #3 is not supported.");
    return false;
  }
  
  public static boolean checkRootMethod4()
  {
    GCLogger.info("Try to check a root by using method #4.");
    return Build.BRAND.equalsIgnoreCase("generic");
  }
  
  @SuppressLint({"NewApi"})
  public static boolean checkRootMethod5()
  {
    GCLogger.info("Try to check a root by using method #5.");
    return (Build.PRODUCT.equalsIgnoreCase("sdk")) || (Build.FINGERPRINT.contains("sdk")) || (Build.PRODUCT.equalsIgnoreCase("vbox86p")) || (Build.HARDWARE.contains("golfdish"));
  }
  
  private static boolean checkRootMethod6()
  {
    GCLogger.info("Try to check a root by using method #6.");
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "/system/xbin/which su";
    arrayOfString[1] = "/system/bin/which su";
    arrayOfString[2] = "which su";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (isExecutable(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static boolean checkRootMethod7()
  {
    GCLogger.info("Try to check a root by using method #7.");
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "/sbin/";
    arrayOfString[1] = "/system/bin/";
    arrayOfString[2] = "/system/xbin/";
    arrayOfString[3] = "/data/local/xbin/";
    arrayOfString[4] = "/data/local/bin/";
    arrayOfString[5] = "/system/sd/xbin/";
    arrayOfString[6] = "/system/bin/failsafe/";
    arrayOfString[7] = "/data/local/";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (new File(str + "su").exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static boolean checkRootMethod8()
  {
    GCLogger.info("Try to check a root by using method #8.");
    return RootTools.isRootAvailable();
  }
  
  public static List<String> detectRooted()
  {
    ArrayList localArrayList = new ArrayList();
    if (!sIs8checked)
    {
      sCheckRootMethod8 = checkRootMethod8();
      sIs8checked = true;
    }
    if (checkRootMethod1()) {
      localArrayList.add("1");
    }
    if (checkRootMethod2()) {
      localArrayList.add("2");
    }
    if (checkRootMethod3()) {
      localArrayList.add("3");
    }
    if (checkRootMethod4()) {
      localArrayList.add("4");
    }
    if (checkRootMethod5()) {
      localArrayList.add("5");
    }
    if (sCheckRootMethod8) {
      localArrayList.add("8");
    }
    return localArrayList;
  }
  
  public static List<String> getIsBlueStacksEnvironment()
  {
    LinkedList localLinkedList = new LinkedList();
    if (new File(Environment.getExternalStorageDirectory(), "blue.utils").exists()) {
      localLinkedList.add("blue.utils");
    }
    if (new File(Environment.getExternalStorageDirectory(), "bstfolder").exists()) {
      localLinkedList.add("bstfolder");
    }
    return localLinkedList;
  }
  
  public static boolean isAllowedMockLocation()
  {
    return Settings.Secure.getString(WeakContext.getContext().getContentResolver(), "mock_location").equals("1");
  }
  
  @TargetApi(17)
  public static boolean isDeveloperOptionOn(Context paramContext)
  {
    if (HWUtils.hasJellyBeanMR1()) {
      if (Settings.Global.getInt(paramContext.getContentResolver(), "development_settings_enabled", 0) != 1) {}
    }
    while ((!HWUtils.isJellyBean()) || (Settings.Secure.getInt(paramContext.getContentResolver(), "development_settings_enabled", 0) == 1))
    {
      return true;
      return false;
    }
    return false;
  }
  
  private static boolean isExecutable(String paramString)
  {
    try
    {
      Runtime.getRuntime().exec(paramString);
      return true;
    }
    catch (IOException paramString) {}
    return false;
  }
}
