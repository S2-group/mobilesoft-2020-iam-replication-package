package com.amber.amberutils;

import android.app.Activity;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.amber.amberutils.event.ChooseWallpaperEvent;
import com.amber.lib.gpmanager.DownloadAppManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

public class ToolUtils
{
  public static final String[] AMBER_PKG_PREVIEW_NAME = { "mobi.infolife.ezweather", "com.amber" };
  public static final String EVENT_STORE_TO_PLAY_FAILUED = "store_to_play_failued";
  public static final String EVENT_STORE_TO_PLAY_SUCCESS = "store_to_play_success";
  public static final String LWP_SOURCE = "amber_lwp";
  public static final String MUL_WIDGET_NEW_STORE = "mul_widget(new_store)";
  public static final String NEW_USER = "new_user";
  public static final String OLD_USER = "old_user";
  public static final int SET_WALL_PAPER_SUCCESS_CODE = 3;
  public static boolean isClickDialogOk = false;
  public static boolean isClickMainInterfaceDownloadButton = false;
  
  public ToolUtils() {}
  
  public static boolean checkAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean checkDeviceHasNavigationBar(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    int i = localResources.getIdentifier("config_showNavigationBar", "bool", "android");
    if (i > 0) {
      return localResources.getBoolean(i);
    }
    boolean bool1 = ViewConfiguration.get(paramContext).hasPermanentMenuKey();
    boolean bool2 = KeyCharacterMap.deviceHasKey(4);
    return (!bool1) && (!bool2);
  }
  
  public static Bitmap compressImage(Bitmap paramBitmap, int paramInt)
  {
    try
    {
      Object localObject = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject);
      int i = 90;
      while (((ByteArrayOutputStream)localObject).toByteArray().length / 1024 > paramInt)
      {
        ((ByteArrayOutputStream)localObject).reset();
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, i, (OutputStream)localObject);
        i -= 10;
      }
      localObject = BitmapFactory.decodeStream(new ByteArrayInputStream(((ByteArrayOutputStream)localObject).toByteArray()), null, null);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramBitmap;
  }
  
  public static int dip2px(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  private static void downloadApp(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = "";
    }
    paramString2 = paramString3;
    if (paramString3 == null) {
      paramString2 = "";
    }
    paramString3 = paramString4;
    if (paramString4 == null) {
      paramString3 = "";
    }
    paramString4 = paramString5;
    if (paramString5 == null) {
      paramString4 = "";
    }
    paramString5 = paramString6;
    if (paramString6 == null) {
      paramString5 = "";
    }
    paramString2 = "&referrer=utm_source%3D" + str + "%26utm_campaign%3D" + paramString2 + "<>" + paramString3 + "<>" + paramString4 + "<>" + paramString5;
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1 + paramString2)));
      return;
    }
    catch (Exception paramString3)
    {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString1 + paramString2)));
        return;
      }
      catch (Exception paramString1)
      {
        Toast.makeText(paramContext, "Error !", 1).show();
      }
    }
  }
  
  public static void downloadAppByPackageName(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    DownloadAppManager.getInstance().downloadApp(paramContext, paramString1, paramString2);
  }
  
  public static Intent downloadAppByPackageNameIntent(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    return DownloadAppManager.getInstance().downloadAppIntent(paramString1, paramString2);
  }
  
  private static Intent downloadAppIntent(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    paramContext = paramString2;
    if (paramString2 == null) {
      paramContext = "";
    }
    paramString2 = paramString3;
    if (paramString3 == null) {
      paramString2 = "";
    }
    paramString3 = paramString4;
    if (paramString4 == null) {
      paramString3 = "";
    }
    paramString4 = paramString5;
    if (paramString5 == null) {
      paramString4 = "";
    }
    paramString5 = paramString6;
    if (paramString6 == null) {
      paramString5 = "";
    }
    paramContext = "&referrer=utm_source%3D" + paramContext + "%26utm_campaign%3D" + paramString2 + "<>" + paramString3 + "<>" + paramString4 + "<>" + paramString5;
    return new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1 + paramContext));
  }
  
  public static String getApplicationName(Context paramContext)
  {
    Object localObject = null;
    try
    {
      PackageManager localPackageManager = paramContext.getApplicationContext().getPackageManager();
      localObject = localPackageManager;
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      localObject = localPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return (String)localObject.getApplicationLabel(paramContext);
  }
  
  public static String getCountryName()
  {
    try
    {
      String str = Locale.getDefault().getCountry();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static Typeface getLockerTypeface(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Typeface.createFromAsset(ReflectUtil.createContextByPkgName(paramContext, LockScreenSetting.getSkinLocker(paramContext)).getAssets(), paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Typeface localTypeface = Typeface.DEFAULT;
      paramContext.printStackTrace();
      Log.d("TYPE FACE", paramString + "====" + localTypeface.toString());
      return localTypeface;
    }
  }
  
  public static ApplicationInfo getMetaDataApplicationInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 128);
      if (paramContext != null)
      {
        paramString = paramContext.metaData;
        if (paramString != null) {
          return paramContext;
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getMetaDataByKey(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString1, 128);
      if ((paramContext != null) && (paramContext.metaData != null))
      {
        paramContext = paramContext.metaData.getString(paramString2);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    return getScreenSize(paramContext)[1];
  }
  
  private static int[] getScreenSize(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    return new int[] { paramContext.widthPixels, paramContext.heightPixels };
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return getScreenSize(paramContext)[0];
  }
  
  public static String getSdkVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    int i = 0;
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static String getSystemModel()
  {
    return Build.MODEL;
  }
  
  public static Typeface getTypeface(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Typeface localTypeface = Typeface.DEFAULT;
      paramContext.printStackTrace();
      Log.d("TYPE FACE", paramString + "====" + localTypeface.toString());
      return localTypeface;
    }
  }
  
  public static String getVersionName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static int getWeatherVersionCode(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static boolean haveAmberAppInstalled(Context paramContext)
  {
    long l = System.currentTimeMillis();
    new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    if (localList == null) {
      return false;
    }
    int i = 0;
    while (i < localList.size())
    {
      String str = ((PackageInfo)localList.get(i)).packageName;
      String[] arrayOfString = AMBER_PKG_PREVIEW_NAME;
      int k = arrayOfString.length;
      int j = 0;
      while (j < k)
      {
        if ((str.startsWith(arrayOfString[j])) && (!str.equals(paramContext.getPackageName())))
        {
          Log.d("xzq", "耗时: " + (System.currentTimeMillis() - l));
          Log.d("xzq", "pkgName: " + str);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    Log.d("xzq", "耗时: " + (System.currentTimeMillis() - l));
    return false;
  }
  
  public static void hideIcon(Context paramContext, Class<?> paramClass)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = new ComponentName(paramContext, paramClass);
    int i = localPackageManager.getComponentEnabledSetting(paramContext);
    if ((i == 0) || (i == 1)) {
      localPackageManager.setComponentEnabledSetting(paramContext, 2, 1);
    }
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isNewDay(long paramLong)
  {
    if (System.currentTimeMillis() > paramLong)
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(localCalendar.get(1), localCalendar.get(2), localCalendar.get(5), 23, 59, 59);
      return localCalendar.getTimeInMillis() - paramLong > 86400000L;
    }
    return false;
  }
  
  public static boolean isNewUser(Context paramContext)
  {
    paramContext = new LockerPreferences(paramContext);
    return (paramContext.getOpenCount() <= 2) && (System.currentTimeMillis() - paramContext.readLockerFirstOpenTime() < 43200000L);
  }
  
  public static void launchWallpaperChooserOld(Fragment paramFragment)
  {
    EventBus.getDefault().post(new ChooseWallpaperEvent(paramFragment, paramFragment.getActivity().getPackageName()));
  }
  
  public static Drawable loadApkResources(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    if (paramContext.getPackageName().equals(paramString1)) {
      if (paramContext != null) {
        break label41;
      }
    }
    label41:
    int i;
    do
    {
      return null;
      try
      {
        paramContext = paramContext.createPackageContext(paramString1, 2);
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
      break;
      paramContext = paramContext.getResources();
      i = paramContext.getIdentifier(paramString2, "drawable", paramString1);
    } while (i == 0);
    try
    {
      paramContext = paramContext.getDrawable(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 0;
      while (i < localObject.length)
      {
        localStringBuffer.append(Integer.toHexString(localObject[i] & 0xFF));
        i += 1;
      }
      localObject = localStringBuffer.toString();
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return paramString;
  }
  
  public static String md5(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      int i;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.reset();
        localMessageDigest.update(paramArrayOfByte);
        paramArrayOfByte = localMessageDigest.digest();
        i = 0;
        if (i < paramArrayOfByte.length) {
          if (Integer.toHexString(paramArrayOfByte[i] & 0xFF).length() == 1) {
            localStringBuffer.append("0").append(Integer.toHexString(paramArrayOfByte[i] & 0xFF));
          } else {
            localStringBuffer.append(Integer.toHexString(paramArrayOfByte[i] & 0xFF));
          }
        }
      }
      catch (NoSuchAlgorithmException paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
      }
      return localStringBuffer.toString();
      i += 1;
    }
  }
  
  public static void setTransparentBar(Activity paramActivity, View paramView)
  {
    int i = Build.VERSION.SDK_INT;
    paramActivity = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = paramActivity.getAttributes();
    if (i >= 19)
    {
      if ((localLayoutParams.flags & 0x4000000) == 0)
      {
        localLayoutParams.flags |= 0x4000000;
        paramActivity.setAttributes(localLayoutParams);
      }
      if (paramView != null) {
        paramView.setVisibility(0);
      }
    }
    while (paramView == null) {
      return;
    }
    paramView.setVisibility(8);
  }
  
  public static boolean wallpaperUsed(Context paramContext)
  {
    try
    {
      Object localObject = WallpaperManager.getInstance(paramContext).getWallpaperInfo();
      if (localObject != null)
      {
        localObject = ((WallpaperInfo)localObject).getPackageName();
        paramContext = paramContext.getPackageName();
        if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!TextUtils.isEmpty(paramContext)))
        {
          boolean bool = ((String)localObject).equals(paramContext);
          return bool;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
