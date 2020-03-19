package com.asus.filemanager.adapter.grouper.categoryparser;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import com.asus.filemanager.provider.GameAppDbHelper;
import com.asus.filemanager.provider.GameAppDbHelper.CategoryStatus;
import java.util.Iterator;
import java.util.List;

public class f
{
  private static long a = 14400000L;
  
  public static void a(Context paramContext)
  {
    long l1 = System.currentTimeMillis();
    long l2 = b(paramContext);
    if (l1 - l2 < a)
    {
      Log.w("GameUtils", "skip parse request, current: " + l1 + ", last: " + l2);
      return;
    }
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    int j = 1;
    int i = 0;
    label81:
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((localApplicationInfo.flags & 0x1) == 1) {
        break label207;
      }
      if (GameAppDbHelper.a(paramContext, localApplicationInfo.packageName))
      {
        Log.w("GameUtils", "skip Insert package :" + localApplicationInfo.packageName);
      }
      else
      {
        if (!a(paramContext, localApplicationInfo.packageName)) {
          break label198;
        }
        i = 0;
        j = 1;
      }
    }
    for (;;)
    {
      int k = j;
      j = i;
      i = k;
      break label81;
      if ((i == 0) && (j == 0)) {
        break;
      }
      a(paramContext, l1);
      return;
      label198:
      k = 0;
      j = i;
      i = k;
      continue;
      label207:
      k = i;
      i = j;
      j = k;
    }
  }
  
  private static void a(Context paramContext, long paramLong)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong("last_installed_app_parsed_time", paramLong).commit();
  }
  
  public static void a(Context paramContext, Class<?> paramClass, boolean paramBoolean)
  {
    paramClass = new ComponentName(paramContext, paramClass);
    paramContext = paramContext.getPackageManager();
    if (paramBoolean) {}
    for (int i = 1;; i = 2)
    {
      paramContext.setComponentEnabledSetting(paramClass, i, 1);
      return;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    Object localObject = b.a(paramContext, new String[] { paramString }, false, true, true, false).a();
    if (((List)localObject).size() > 0)
    {
      localObject = (a)((List)localObject).get(0);
      if (((a)localObject).c())
      {
        localObject = ((a)localObject).b();
        bool = true;
      }
    }
    for (;;)
    {
      GameAppDbHelper.CategoryStatus.UNKNOWN.ordinal();
      int i;
      if (bool) {
        if ((localObject != null) && (((String)localObject).contains("GAME"))) {
          i = GameAppDbHelper.CategoryStatus.GAME_APP.ordinal();
        }
      }
      for (;;)
      {
        Log.d("GameUtils", "Insert package:" + paramString + ", categoryID: " + (String)localObject);
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("_package_name", paramString);
        localContentValues.put("_category", (String)localObject);
        localContentValues.put("_is_game", Integer.valueOf(i));
        GameAppDbHelper.a(paramContext, localContentValues);
        return bool;
        i = GameAppDbHelper.CategoryStatus.NOT_GAME_APP.ordinal();
        continue;
        i = GameAppDbHelper.CategoryStatus.UNKNOWN.ordinal();
      }
      localObject = null;
    }
  }
  
  private static long b(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getLong("last_installed_app_parsed_time", 0L);
  }
}
