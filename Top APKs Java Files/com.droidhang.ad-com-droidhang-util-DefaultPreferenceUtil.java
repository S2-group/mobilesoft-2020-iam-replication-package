package com.droidhang.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import java.util.List;

public class DefaultPreferenceUtil
{
  public static int INVALIDATE_INT_VALUE = -1;
  public static long INVALIDATE_Long_VALUE = -1L;
  
  public DefaultPreferenceUtil() {}
  
  public static void downloadApp(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    paramContext.startActivity(localIntent);
  }
  
  public static Boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramString, paramBoolean));
  }
  
  public static float getFloat(Context paramContext, String paramString, float paramFloat)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getFloat(paramString, paramFloat);
  }
  
  public static int getInt(Context paramContext, String paramString, int paramInt)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt(paramString, paramInt);
  }
  
  public static long getLong(Context paramContext, String paramString, long paramLong)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getLong(paramString, paramLong);
  }
  
  public static String getString(Context paramContext, String paramString1, String paramString2)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramString1, paramString2);
  }
  
  public static int isInstall(Context paramContext, String paramString)
  {
    int k = 0;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
          j = 1;
        }
      }
      else {
        return j;
      }
      i += 1;
    }
  }
  
  public static void setBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean(paramString, paramBoolean);
    paramContext.commit();
  }
  
  public static void setFloat(Context paramContext, String paramString, float paramFloat)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putFloat(paramString, paramFloat);
    paramContext.commit();
  }
  
  public static void setInt(Context paramContext, String paramString, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putInt(paramString, paramInt);
    paramContext.commit();
  }
  
  public static void setLong(Context paramContext, String paramString, long paramLong)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putLong(paramString, paramLong);
    paramContext.commit();
  }
  
  public static void setString(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }
}
