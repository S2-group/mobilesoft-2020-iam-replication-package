package com.til.colombia.dmp.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Utils
{
  public static final String DMP_INIT = "dmpInit";
  public static final String DMP_INTERESTS = "interests";
  public static final String DMP_PREF = "ColombiaDMPPref";
  public static final String FEED_JSON = "dmpFeedJson";
  public static final String FEED_PREF = "dmpFeeds";
  public static final int HTTP_CONNECTION_TIMEOUT = 15000;
  public static final int HTTP_SOCKET_TIMEOUT = 15000;
  public static final String INSTALLED_APPS = "installed";
  public static final String IS_FIRST_PERSONA_EVENT_REPORTED = "fPersona";
  public static final String LOG_TAG_VER = "colombia-dmp-aos:1.0.7";
  public static final String SDK_VERSION = "aos:1.0.7";
  public static final String UNINSTALLED_APPS = "uninstalled";
  private static String aaid;
  private static int lite = 0;
  
  public Utils() {}
  
  public static boolean checkNetworkAvailibility(Context paramContext)
  {
    if (paramContext == null)
    {
      Log.d("colombia-dmp-aos:1.0.7", "Context is null. Can not check network.");
      return false;
    }
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext.getActiveNetworkInfo() != null)
      {
        boolean bool = paramContext.getActiveNetworkInfo().isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      Log.d("colombia-dmp-aos:1.0.7", "Cannot find network state", paramContext);
    }
    return false;
  }
  
  public static void clearPref(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences(paramString, 0).edit().clear().apply();
  }
  
  public static String getAAID(Context paramContext)
  {
    if (TextUtils.isEmpty(aaid)) {
      AdvertisingIDUtil.retrieveAndSetAAID(paramContext);
    }
    return aaid;
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static List<String> getInstalledApps(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return paramContext;
  }
  
  public static int getIntPreferences(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || ("".equals(paramString1.trim())) || ("".equals(paramString2.trim())))
    {
      Log.d("colombia-dmp-aos:1.0.7", "Failed to get preferences..App context NULL");
      return 0;
    }
    return paramContext.getSharedPreferences(paramString1, 0).getInt(paramString2, 0);
  }
  
  public static Integer getLite()
  {
    return Integer.valueOf(lite);
  }
  
  public static String getPreferences(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || ("".equals(paramString1.trim())) || ("".equals(paramString2.trim())))
    {
      Log.d("colombia-dmp-aos:1.0.7", "Failed to get preferences..App context NULL");
      return null;
    }
    return paramContext.getSharedPreferences(paramString1, 0).getString(paramString2, null);
  }
  
  public static String join(Collection<String> paramCollection, String paramString)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return "";
    }
    Object localObject = paramCollection.iterator();
    while (((Iterator)localObject).hasNext()) {
      if (TextUtils.isEmpty((String)((Iterator)localObject).next())) {
        ((Iterator)localObject).remove();
      }
    }
    paramCollection = paramCollection.iterator();
    localObject = new StringBuilder(paramCollection.next().toString());
    while (paramCollection.hasNext())
    {
      String str = (String)paramCollection.next();
      if (str != null) {
        ((StringBuilder)localObject).append(paramString).append(str);
      }
    }
    return ((StringBuilder)localObject).toString();
  }
  
  public static void setAAID(String paramString, Boolean paramBoolean)
  {
    aaid = paramString;
    if (paramBoolean.booleanValue()) {
      lite = 1;
    }
  }
  
  public static void setPreferences(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || ("".equals(paramString1.trim())) || ("".equals(paramString2.trim())))
    {
      Log.d("colombia-dmp-aos:1.0.7", "Failed to set preferences..App context NULL");
      return;
    }
    paramContext = paramContext.getSharedPreferences(paramString1, 0).edit();
    paramContext.putInt(paramString2, paramInt);
    paramContext.apply();
  }
  
  public static boolean setPreferences(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || ("".equals(paramString1.trim())) || ("".equals(paramString2.trim())))
    {
      Log.d("colombia-dmp-aos:1.0.7", "Failed to set preferences..App context NULL");
      return false;
    }
    paramContext = paramContext.getSharedPreferences(paramString1, 0).edit();
    paramContext.putString(paramString2, paramString3);
    paramContext.apply();
    return true;
  }
}
