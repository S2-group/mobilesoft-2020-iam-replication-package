package com.zoho.accounts.zohoaccounts;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.zoho.accounts.zohoaccounts.constants.ResourceType;
import java.util.Iterator;
import java.util.List;

class Util
{
  private static final String a = "iamlib.properties";
  
  Util() {}
  
  static float a(Context paramContext, float paramFloat)
  {
    return paramFloat * paramContext.getResources().getDisplayMetrics().density;
  }
  
  static int a(Context paramContext)
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
  
  static int a(ResourceType paramResourceType, String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, paramResourceType.name(), paramContext.getPackageName());
  }
  
  static IAMErrorCodes a(Exception paramException)
  {
    IAMErrorCodes localIAMErrorCodes = b(paramException.getMessage());
    localIAMErrorCodes.a(paramException);
    return localIAMErrorCodes;
  }
  
  static IAMErrorCodes a(Exception paramException, IAMErrorCodes paramIAMErrorCodes)
  {
    paramIAMErrorCodes.a(paramException);
    return paramIAMErrorCodes;
  }
  
  static String a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("iamlib.properties", 0).getString(paramString, null);
  }
  
  static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("iamlib.properties", 0).edit().putString(paramString1, paramString2).commit();
  }
  
  static void a(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    a(paramContext, paramString, Base64.encodeToString(paramArrayOfByte, 0));
  }
  
  static boolean a(String paramString)
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
  
  static IAMErrorCodes b(String paramString)
  {
    IAMErrorCodes[] arrayOfIAMErrorCodes = IAMErrorCodes.values();
    int j = arrayOfIAMErrorCodes.length;
    int i = 0;
    while (i < j)
    {
      IAMErrorCodes localIAMErrorCodes = arrayOfIAMErrorCodes[i];
      if (localIAMErrorCodes.b().equalsIgnoreCase(paramString)) {
        return localIAMErrorCodes;
      }
      i += 1;
    }
    return IAMErrorCodes.o;
  }
  
  static String b(Context paramContext)
  {
    int i = a(ResourceType.b, "c_id", paramContext);
    if (i != 0) {
      return paramContext.getResources().getString(i);
    }
    Log.c("c_id not present in strings.xml");
    return null;
  }
  
  static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("iamlib.properties", 0).getString(paramString1, paramString2);
  }
  
  static byte[] b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Base64.decode(a(paramContext, paramString), 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
}
