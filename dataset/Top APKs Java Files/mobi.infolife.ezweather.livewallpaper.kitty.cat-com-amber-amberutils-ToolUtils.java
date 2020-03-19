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
    paramString6 = new StringBuilder();
    paramString6.append("&referrer=utm_source%3D");
    paramString6.append(str);
    paramString6.append("%26utm_campaign%3D");
    paramString6.append(paramString2);
    paramString6.append("<>");
    paramString6.append(paramString3);
    paramString6.append("<>");
    paramString6.append(paramString4);
    paramString6.append("<>");
    paramString6.append(paramString5);
    paramString2 = paramString6.toString();
    try
    {
      paramString3 = new StringBuilder();
      paramString3.append("market://details?id=");
      paramString3.append(paramString1);
      paramString3.append(paramString2);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString3.toString())));
      return;
    }
    catch (Exception paramString3)
    {
      label246:
      for (;;) {}
    }
    try
    {
      paramString3 = new StringBuilder();
      paramString3.append("https://play.google.com/store/apps/details?id=");
      paramString3.append(paramString1);
      paramString3.append(paramString2);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString3.toString())));
      return;
    }
    catch (Exception paramString1)
    {
      break label246;
    }
    Toast.makeText(paramContext, "Error !", 1).show();
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
    paramString6 = new StringBuilder();
    paramString6.append("&referrer=utm_source%3D");
    paramString6.append(paramContext);
    paramString6.append("%26utm_campaign%3D");
    paramString6.append(paramString2);
    paramString6.append("<>");
    paramString6.append(paramString3);
    paramString6.append("<>");
    paramString6.append(paramString4);
    paramString6.append("<>");
    paramString6.append(paramString5);
    paramContext = paramString6.toString();
    paramString2 = new StringBuilder();
    paramString2.append("market://details?id=");
    paramString2.append(paramString1);
    paramString2.append(paramContext);
    return new Intent("android.intent.action.VIEW", Uri.parse(paramString2.toString()));
  }
  
  public static String getApplicationName(Context paramContext)
  {
    for (;;)
    {
      Object localObject;
      try
      {
        localObject = paramContext.getApplicationContext().getPackageManager();
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        ApplicationInfo localApplicationInfo;
        continue;
      }
      try
      {
        localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(paramContext.getPackageName(), 0);
        paramContext = (Context)localObject;
        localObject = localApplicationInfo;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext = (Context)localObject;
      }
    }
    paramContext = null;
    localObject = null;
    return (String)paramContext.getApplicationLabel((ApplicationInfo)localObject);
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
    catch (Exception localException)
    {
      paramContext = Typeface.DEFAULT;
      localException.printStackTrace();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("====");
      localStringBuilder.append(paramContext.toString());
      Log.d("TYPE FACE", localStringBuilder.toString());
    }
    return paramContext;
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
    int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (i > 0) {
      return paramContext.getResources().getDimensionPixelSize(i);
    }
    return 0;
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
    catch (Exception localException)
    {
      paramContext = Typeface.DEFAULT;
      localException.printStackTrace();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("====");
      localStringBuilder.append(paramContext.toString());
      Log.d("TYPE FACE", localStringBuilder.toString());
    }
    return paramContext;
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
          paramContext = new StringBuilder();
          paramContext.append("耗时: ");
          paramContext.append(System.currentTimeMillis() - l);
          Log.d("xzq", paramContext.toString());
          paramContext = new StringBuilder();
          paramContext.append("pkgName: ");
          paramContext.append(str);
          Log.d("xzq", paramContext.toString());
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    paramContext = new StringBuilder();
    paramContext.append("耗时: ");
    paramContext.append(System.currentTimeMillis() - l);
    Log.d("xzq", paramContext.toString());
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
    if (paramString != null) {
      if ("".equals(paramString)) {
        return false;
      }
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
    return false;
  }
  
  public static boolean isNewDay(long paramLong)
  {
    long l = System.currentTimeMillis();
    boolean bool = false;
    if (l > paramLong)
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(localCalendar.get(1), localCalendar.get(2), localCalendar.get(5), 23, 59, 59);
      if (localCalendar.getTimeInMillis() - paramLong > 86400000L) {
        bool = true;
      }
      return bool;
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
    if (!paramContext.getPackageName().equals(paramString1)) {
      try
      {
        paramContext = paramContext.createPackageContext(paramString1, 2);
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getResources();
    int i = paramContext.getIdentifier(paramString2, "drawable", paramString1);
    if (i != 0) {
      try
      {
        paramContext = paramContext.getDrawable(i);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
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
    localStringBuffer = new StringBuffer();
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      paramArrayOfByte = localMessageDigest.digest();
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        if (Integer.toHexString(paramArrayOfByte[i] & 0xFF).length() == 1)
        {
          localStringBuffer.append("0");
          localStringBuffer.append(Integer.toHexString(paramArrayOfByte[i] & 0xFF));
        }
        else
        {
          localStringBuffer.append(Integer.toHexString(paramArrayOfByte[i] & 0xFF));
        }
        i += 1;
      }
      return localStringBuffer.toString();
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
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
        localLayoutParams.flags = (0x4000000 | localLayoutParams.flags);
        paramActivity.setAttributes(localLayoutParams);
      }
      if (paramView != null) {
        paramView.setVisibility(0);
      }
    }
    else if (paramView != null)
    {
      paramView.setVisibility(8);
    }
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
