package com.citi.latam.ffimodules;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.konylabs.android.KonyMain;
import java.util.Iterator;
import java.util.List;

public class FFIFunctions
{
  private static KonyMain konyContext;
  
  public FFIFunctions() {}
  
  public static boolean callIsAppIDExists(String paramString)
  {
    konyContext = KonyMain.getActivityContext();
    return isAppIDExists(konyContext, paramString);
  }
  
  public static void callLaunchStore(String paramString1, String paramString2)
  {
    konyContext = KonyMain.getActivityContext();
    launchStore(konyContext, paramString1, paramString2);
  }
  
  public static void findAndLaunchApp(String paramString1, String paramString2)
  {
    konyContext = KonyMain.getActivityContext();
    KonyMain localKonyMain = konyContext;
    PackageManager localPackageManager = localKonyMain.getPackageManager();
    if (isPackageExists(localPackageManager, paramString1))
    {
      localKonyMain.startActivity(localPackageManager.getLaunchIntentForPackage(paramString1));
      return;
    }
    localKonyMain.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
  }
  
  public static boolean isAppIDExists(Object paramObject, String paramString)
  {
    return isPackageExists(((Activity)paramObject).getPackageManager(), paramString);
  }
  
  public static boolean isPackageExists(PackageManager paramPackageManager, String paramString)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(0).iterator();
    do
    {
      if (!paramPackageManager.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)paramPackageManager.next()).packageName.equals(paramString));
    return true;
  }
  
  public static void launchStore(Object paramObject, String paramString1, String paramString2)
  {
    paramObject = (Activity)paramObject;
    if (!isPackageExists(paramObject.getPackageManager(), paramString1)) {
      paramObject.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
    }
  }
}
