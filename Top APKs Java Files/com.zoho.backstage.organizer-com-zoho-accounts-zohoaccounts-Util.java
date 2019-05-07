package com.zoho.accounts.zohoaccounts;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.zoho.accounts.zohoaccounts.constants.ResourceType;
import java.util.Iterator;
import java.util.List;

class Util
{
  private static final String SHARED_PREF = "iamlib.properties";
  
  Util() {}
  
  static float convertDpToPx(Context paramContext, float paramFloat)
  {
    return paramFloat * paramContext.getResources().getDisplayMetrics().density;
  }
  
  static String getClientID(Context paramContext)
  {
    int i = getResourseIdFromParentApp(ResourceType.string, "c_id", paramContext);
    if (i != 0) {
      return paramContext.getResources().getString(i);
    }
    Log.e("c_id not present in strings.xml");
    return null;
  }
  
  static IAMErrorCodes getErrorCode(String paramString)
  {
    IAMErrorCodes[] arrayOfIAMErrorCodes = IAMErrorCodes.values();
    int j = arrayOfIAMErrorCodes.length;
    int i = 0;
    while (i < j)
    {
      IAMErrorCodes localIAMErrorCodes = arrayOfIAMErrorCodes[i];
      if (localIAMErrorCodes.getName().equalsIgnoreCase(paramString)) {
        return localIAMErrorCodes;
      }
      i += 1;
    }
    return IAMErrorCodes.general_error;
  }
  
  static String getFromStoredPref(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("iamlib.properties", 0).getString(paramString, null);
  }
  
  static String getFromStoredPref(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("iamlib.properties", 0).getString(paramString1, paramString2);
  }
  
  static int getInstalledZOHOAppsCount(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.startsWith("com.zoho")) {
        i += 1;
      }
    }
    return i;
  }
  
  static int getResourseIdFromParentApp(ResourceType paramResourceType, String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, paramResourceType.name(), paramContext.getPackageName());
  }
  
  static boolean isClassPresent(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  static void setIntoStoredPref(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("iamlib.properties", 0).edit().putString(paramString1, paramString2).commit();
  }
}
