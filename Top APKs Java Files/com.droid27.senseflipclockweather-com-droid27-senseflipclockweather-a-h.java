package com.droid27.senseflipclockweather.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.Iterator;
import java.util.List;

public final class h
{
  public h() {}
  
  public static Drawable a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString2);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.getDrawable(paramContext.getIdentifier(paramString1, "drawable", paramString2));
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String a(Resources paramResources, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramResources = paramResources.getString(paramResources.getIdentifier(paramString1, paramString2, paramString3));
      return paramResources;
    }
    catch (Exception paramResources) {}
    return "";
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    return true;
  }
  
  public static int b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString2);
      if (paramContext == null) {
        return 0;
      }
      int i = paramContext.getIdentifier(paramString1, "drawable", paramString2);
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static String[] b(Resources paramResources, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramResources = paramResources.getStringArray(paramResources.getIdentifier(paramString1, paramString2, paramString3));
      return paramResources;
    }
    catch (Exception paramResources) {}
    return tmp19_16;
  }
}
