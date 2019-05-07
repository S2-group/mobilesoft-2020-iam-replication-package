package com.android.blue.commons.theme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ThemeManager
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  public static final String ACTION_THEME_CHANGED = "com.android.blue_THEME_CHANGED";
  private static final String[] THEME_PACKAGE_PREFIX_LIST = { "com.kkkeyboard.emoji.keyboard.theme.", "free.slots.machine.casino." };
  private static ThemeManager sInstance = null;
  int[] THEMES_IDS;
  String[] THEMES_NAME;
  final int[] THEMES_PREVIEW = { 2130837910 };
  private Context mCtx = null;
  
  private ThemeManager(Context paramContext)
  {
    this.mCtx = paramContext;
    PreferenceManager.getDefaultSharedPreferences(this.mCtx).registerOnSharedPreferenceChangeListener(this);
  }
  
  private LinkedHashMap<String, LocalThemeInfo> getInternalThemeList(Context paramContext)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    this.THEMES_NAME = paramContext.getResources().getStringArray(2131296257);
    paramContext = new LocalThemeInfo();
    paramContext.setName(this.THEMES_NAME[0]);
    paramContext.setPreview(this.THEMES_PREVIEW[0]);
    localLinkedHashMap.clear();
    localLinkedHashMap.put(paramContext.getName(), paramContext);
    return localLinkedHashMap;
  }
  
  public static ThemeManager getsInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new ThemeManager(paramContext.getApplicationContext());
      }
      paramContext = sInstance;
      return paramContext;
    }
    finally {}
  }
  
  public int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = paramOptions.outHeight;
    int j = paramOptions.outWidth;
    int i = 1;
    if ((k > paramInt2) || (j > paramInt1))
    {
      i = Math.round(k / paramInt2);
      paramInt1 = Math.round(j / paramInt1);
      if (i >= paramInt1) {}
    }
    else
    {
      return i;
    }
    return paramInt1;
  }
  
  public Bitmap decodeSampledBitmapFromResource(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt2, paramInt3);
    localOptions.inJustDecodeBounds = false;
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    return BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
  }
  
  public String getApplicationName(String paramString)
  {
    Object localObject = null;
    try
    {
      PackageManager localPackageManager = this.mCtx.getPackageManager();
      return "";
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      try
      {
        for (paramString = localPackageManager.getApplicationInfo(paramString, 0); paramString != null; paramString = localObject)
        {
          return (String)localPackageManager.getApplicationLabel(paramString);
          paramString = paramString;
          localPackageManager = null;
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;)
        {
          paramString = localObject;
        }
      }
    }
  }
  
  public LinkedHashMap<String, LocalThemeInfo> getChangedTheme(Intent paramIntent, Activity paramActivity, LinkedHashMap<String, LocalThemeInfo> paramLinkedHashMap)
  {
    new ArrayList();
    paramIntent = paramIntent.getExtras().getString("theme_pkg");
    Object localObject = PreferenceManager.getDefaultSharedPreferences(paramActivity).getString("theme_pkg", "");
    if (((paramIntent == null) || (!paramIntent.equals(localObject))) || (getsInstance(paramActivity).isInstalled(paramIntent)))
    {
      localObject = new LocalThemeInfo();
      ((LocalThemeInfo)localObject).setName(getsInstance(paramActivity).getApplicationName(paramIntent));
      ((LocalThemeInfo)localObject).setPkg(paramIntent);
      ((LocalThemeInfo)localObject).setPrime(false);
      ((LocalThemeInfo)localObject).setPreview(0);
      if (!paramLinkedHashMap.containsKey(((LocalThemeInfo)localObject).getPkg())) {
        paramLinkedHashMap.put(((LocalThemeInfo)localObject).getPkg(), localObject);
      }
    }
    while (!paramLinkedHashMap.containsKey(paramIntent)) {
      return paramLinkedHashMap;
    }
    paramLinkedHashMap.remove(paramIntent);
    return paramLinkedHashMap;
  }
  
  public int getColor(String paramString1, String paramString2)
  {
    try
    {
      Resources localResources = this.mCtx.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":color/" + paramString2, null, null);
      if (i != 0)
      {
        i = localResources.getColor(i);
        return i;
      }
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0;
  }
  
  public Bitmap getDrawable(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    try
    {
      Resources localResources = this.mCtx.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":drawable/" + paramString2, null, null);
      paramString1 = localObject;
      if (i != 0) {
        paramString1 = decodeSampledBitmapFromResource(localResources, i, paramInt1, paramInt2);
      }
      return paramString1;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (OutOfMemoryError paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public Bitmap getDrawable(String paramString1, String paramString2, BitmapFactory.Options paramOptions)
  {
    Object localObject = null;
    try
    {
      Resources localResources = this.mCtx.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":drawable/" + paramString2, null, null);
      paramString1 = localObject;
      if (i != 0)
      {
        BitmapFactory.decodeStream(this.mCtx.getResources().openRawResource(i), null, paramOptions);
        paramString1 = BitmapFactory.decodeResource(localResources, i, paramOptions);
      }
      return paramString1;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public Drawable getDrawable(String paramString1, String paramString2)
  {
    Object localObject = null;
    try
    {
      Resources localResources = this.mCtx.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":drawable/" + paramString2, null, null);
      paramString1 = localObject;
      if (i != 0) {
        paramString1 = localResources.getDrawable(i);
      }
      return paramString1;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (OutOfMemoryError paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public int getDrawableId(String paramString1, String paramString2)
  {
    try
    {
      int i = this.mCtx.getPackageManager().getResourcesForApplication(paramString1).getIdentifier(paramString1 + ":drawable/" + paramString2, null, null);
      return i;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0;
  }
  
  public float getFraction(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    float f = 0.0F;
    try
    {
      Resources localResources = this.mCtx.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":fraction/" + paramString2, null, null);
      if (i != 0) {
        f = localResources.getFraction(i, paramInt1, paramInt2);
      }
      return f;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0.0F;
  }
  
  public LinkedHashMap<String, LocalThemeInfo> getLocalThemeList(Context paramContext, String paramString)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Object localObject1 = getInternalThemeList(paramContext);
    localLinkedHashMap.clear();
    localLinkedHashMap.putAll((Map)localObject1);
    Object localObject2 = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (!((SharedPreferences)localObject2).getBoolean("theme_scanned", false))
    {
      ((SharedPreferences)localObject2).edit().putString("theme_pkgs_installed", "").apply();
      localObject1 = paramContext.getPackageManager();
    }
    label425:
    for (;;)
    {
      try
      {
        localObject3 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject1 = "";
        localObject3 = ((List)localObject3).iterator();
        if (((Iterator)localObject3).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
          if ((!getsInstance(paramContext).isSupportedThemePkg(localPackageInfo.packageName)) || (getsInstance(paramContext).getDrawable(localPackageInfo.packageName, "callerid_preview_img") == null)) {
            break label425;
          }
          localObject1 = (String)localObject1 + localPackageInfo.packageName + ",";
          break label425;
        }
        ((SharedPreferences)localObject2).edit().putString("theme_pkgs_installed", (String)localObject1).apply();
      }
      catch (NullPointerException localNullPointerException)
      {
        Object localObject3;
        int i;
        localNullPointerException.printStackTrace();
        continue;
      }
      ((SharedPreferences)localObject2).edit().putBoolean("theme_scanned", true).apply();
      localObject1 = ((SharedPreferences)localObject2).getString("theme_pkgs_installed", "");
      if (!((String)localObject1).isEmpty())
      {
        localObject1 = ((String)localObject1).split(",", -1);
        i = localObject1.length - 1;
        if (i >= 0)
        {
          if (getsInstance(paramContext).isSupportedThemePkg(localObject1[i]))
          {
            localObject2 = new LocalThemeInfo();
            ((LocalThemeInfo)localObject2).setName(getsInstance(paramContext).getApplicationName(localObject1[i]));
            localObject3 = localObject1[i].split("\\.");
            if ((localObject3.length > 0) && (paramString.equals(localObject3[(localObject3.length - 1)]))) {
              PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("theme_name", ((LocalThemeInfo)localObject2).getName()).apply();
            }
            ((LocalThemeInfo)localObject2).setPkg(localObject1[i]);
            ((LocalThemeInfo)localObject2).setPreview(0);
            ((LocalThemeInfo)localObject2).setPrime(false);
            localLinkedHashMap.put(((LocalThemeInfo)localObject2).getPkg(), localObject2);
          }
          i -= 1;
          continue;
        }
      }
      return localLinkedHashMap;
    }
  }
  
  public List<OnlineThemeInfo> getOnlineThemeList()
  {
    return null;
  }
  
  public String[] getStringArray(String paramString1, String paramString2)
  {
    Object localObject = null;
    try
    {
      Resources localResources = this.mCtx.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":array/" + paramString2, null, null);
      paramString1 = localObject;
      if (i != 0) {
        paramString1 = localResources.getStringArray(i);
      }
      return paramString1;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public String getThemePkg()
  {
    return PreferenceManager.getDefaultSharedPreferences(this.mCtx).getString("theme_pkg", "");
  }
  
  public boolean isExternalTheme()
  {
    return !PreferenceManager.getDefaultSharedPreferences(this.mCtx).getString("theme_pkg", "").equals("");
  }
  
  public boolean isInstalled(String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = this.mCtx.getPackageManager().getPackageInfo(paramString, 0);
      if (paramString != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
  }
  
  public boolean isSupportedThemePkg(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = THEME_PACKAGE_PREFIX_LIST;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString.contains(arrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if ("theme package".equals(paramString)) {}
  }
  
  public List<LocalThemeInfo> removeTheme(List<LocalThemeInfo> paramList, String paramString)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      if (((LocalThemeInfo)localIterator.next()).getPkg().equals(paramString)) {
        localIterator.remove();
      }
    }
    return paramList;
  }
}
