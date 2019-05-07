package fortytwo.android.lib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class h
{
  static final Comparator a = new h.1();
  
  private static long a(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l = paramString.getBlockSize();
      l = paramString.getAvailableBlocks() * l / 1024L;
      return l;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return -1L;
  }
  
  static PackageInfo a(Context paramContext)
  {
    return a(paramContext.getPackageManager(), paramContext.getPackageName());
  }
  
  private static PackageInfo a(PackageManager paramPackageManager, String paramString)
  {
    Object localObject = null;
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 4294);
      if (paramPackageManager == null) {
        throw new NullPointerException("Cannot get the package info of " + paramString);
      }
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;)
      {
        paramPackageManager = localObject;
      }
    }
    return paramPackageManager;
  }
  
  static JSONObject b(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = d(paramContext);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        Object localObject2 = Locale.getDefault();
        localJSONObject.put("language", ((Locale)localObject2).getLanguage());
        localJSONObject.put("country", ((Locale)localObject2).getCountry());
        paramContext = paramContext.getResources().getDisplayMetrics();
        localJSONObject.put("density", paramContext.density);
        localJSONObject.put("widthPixels", paramContext.widthPixels);
        localJSONObject.put("heightPixels", paramContext.heightPixels);
        long l = a(Environment.getRootDirectory().getAbsolutePath());
        if (l >= 0L) {
          localJSONObject.put("internalAvailableKB", l);
        }
        l = a(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (l >= 0L) {
          localJSONObject.put("externalAvailableKB", l);
        }
        localJSONObject.put("device", Build.DEVICE);
        localJSONObject.put("model", Build.MODEL);
        localJSONObject.put("sdk", Build.VERSION.SDK_INT);
        paramContext = new JSONArray();
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          Object localObject3 = (PackageInfo)((Iterator)localObject1).next();
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("pn", ((PackageInfo)localObject3).packageName);
          ((JSONObject)localObject2).put("t", ((PackageInfo)localObject3).applicationInfo.loadLabel(localPackageManager));
          if ((((PackageInfo)localObject3).applicationInfo.flags & 0x80) == 0) {
            break label402;
          }
          bool = true;
          ((JSONObject)localObject2).put("usa", bool);
          ((JSONObject)localObject2).put("vc", ((PackageInfo)localObject3).versionCode);
          ((JSONObject)localObject2).put("vn", ((PackageInfo)localObject3).versionName);
          ((JSONObject)localObject2).put("fit", ((PackageInfo)localObject3).firstInstallTime);
          ((JSONObject)localObject2).put("lut", ((PackageInfo)localObject3).lastUpdateTime);
          localObject3 = localPackageManager.getInstallerPackageName(((PackageInfo)localObject3).packageName);
          if (!TextUtils.isEmpty((CharSequence)localObject3)) {
            ((JSONObject)localObject2).put("inst", localObject3);
          }
          paramContext.put(localObject2);
          continue;
        }
        localJSONObject.put("apps", paramContext);
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        continue;
      }
      catch (JSONException paramContext)
      {
        return null;
      }
      return localJSONObject;
      label402:
      boolean bool = false;
    }
  }
  
  static void c(Context paramContext)
  {
    PackageInfo localPackageInfo = a(paramContext);
    if (localPackageInfo == null) {
      throw new NullPointerException("Package info missing for " + paramContext.getPackageName());
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager.checkPermission("android.permission.INTERNET", paramContext.getPackageName()) != 0) {
      throw new SecurityException("No android.permission.INTERNET granted");
    }
    if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0) {
      throw new SecurityException("No android.permission.ACCESS_NETWORK_STATE granted");
    }
    paramContext = localPackageInfo.applicationInfo.metaData;
    if (paramContext == null) {}
    for (paramContext = null; paramContext == null; paramContext = paramContext.getString("42:appid")) {
      throw new AppIdMissingException(localPackageInfo.packageName);
    }
  }
  
  private static List d(Context paramContext)
  {
    localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(0).iterator();
      if (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        for (;;)
        {
          try
          {
            ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
            if ((localApplicationInfo.flags & 0x1) != 0) {
              break label100;
            }
            i = 1;
            if ((localApplicationInfo.flags & 0x80) == 0) {
              break label105;
            }
            j = 1;
            if ((j | i) == 0) {
              break;
            }
            localArrayList.add(localPackageInfo);
          }
          catch (Exception localException) {}
          break;
          label100:
          int i = 0;
          continue;
          label105:
          int j = 0;
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      Collections.sort(localArrayList, a);
    }
  }
}
