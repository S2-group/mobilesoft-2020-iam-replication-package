package com.onelouder.adlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.xmlpull.v1.XmlSerializer;

@SuppressLint({"InlinedApi"})
class Utils
{
  public static final Date MinimumDate = new Date(0L);
  private static final String TAG = "Utils";
  private static final SimpleDateFormat gFormatDate;
  private static final SimpleDateFormat gFormatTS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
  private static String mAndroidId;
  private static Integer mDensityDPI;
  private static String mDeviceId;
  private static String mGUID;
  private static Boolean mIsDefaultDensity;
  private static Boolean mIsHighDensity = null;
  private static Boolean mIsLarge;
  private static Boolean mIsLowDensity;
  private static Boolean mIsMediumDensity;
  private static Boolean mIsTvDensity;
  private static Boolean mIsXHighDensity = null;
  private static Boolean mIsXLarge;
  private static Boolean mIsXXHighDensity = null;
  private static String mPhoneNumber;
  private static Double mScale;
  private static Integer mYDPI;
  
  static
  {
    gFormatDate = new SimpleDateFormat("yyyy-MM-dd");
    mAndroidId = null;
    mScale = null;
    mIsLarge = null;
    mIsXLarge = null;
    mYDPI = null;
    mDensityDPI = null;
    mIsDefaultDensity = null;
    mIsLowDensity = null;
    mIsMediumDensity = null;
    mIsTvDensity = null;
  }
  
  Utils() {}
  
  public static int ParseInteger(String paramString)
  {
    int j = 0;
    int i = j;
    if (paramString != null) {
      i = j;
    }
    try
    {
      if (paramString.length() > 0) {
        i = Integer.parseInt(paramString);
      }
      return i;
    }
    catch (NumberFormatException paramString)
    {
      Diagnostics.w("Utils", paramString);
    }
    return 0;
  }
  
  public static Date ParseTimestamp(String paramString)
  {
    Date localDate = MinimumDate;
    if (paramString != null) {
      try
      {
        if (paramString.length() > 0)
        {
          if (paramString.length() == 10) {
            return gFormatDate.parse(paramString);
          }
          paramString = gFormatTS.parse(paramString);
          return paramString;
        }
      }
      catch (ParseException paramString)
      {
        Diagnostics.w("Utils", paramString);
        return localDate;
      }
      catch (RuntimeException paramString)
      {
        Diagnostics.w("Utils", paramString);
      }
    }
    return localDate;
  }
  
  private static boolean checkAccessNetworStatePermission(Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0;
  }
  
  public static Bitmap decodePurgeableByteArray(byte[] paramArrayOfByte)
  {
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject1 = null;
    Object localObject2;
    if (paramArrayOfByte != null)
    {
      localObject1 = localObject3;
      localObject2 = localObject4;
    }
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localObject1 = localObject3;
      localObject2 = localObject4;
      localOptions.inPurgeable = true;
      localObject1 = localObject3;
      localObject2 = localObject4;
      localOptions.inInputShareable = true;
      localObject1 = localObject3;
      localObject2 = localObject4;
      localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramArrayOfByte = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
      localObject1 = paramArrayOfByte;
      if (paramArrayOfByte == null)
      {
        localObject1 = paramArrayOfByte;
        localObject2 = paramArrayOfByte;
        Diagnostics.w("Utils", "BitmapFactory.decodeByteArray returned null");
        localObject1 = paramArrayOfByte;
      }
      return localObject1;
    }
    catch (OutOfMemoryError paramArrayOfByte)
    {
      Diagnostics.w("Utils", paramArrayOfByte);
      return localObject1;
    }
    catch (Exception paramArrayOfByte)
    {
      Diagnostics.e("Utils", paramArrayOfByte);
    }
    return localObject2;
  }
  
  public static void fadeIn(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong, int paramInt)
  {
    try
    {
      AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
      if (paramLong > 0L) {
        localAlphaAnimation.setStartOffset(paramLong);
      }
      localAlphaAnimation.setDuration(paramInt);
      if (paramAnimationListener != null) {
        localAlphaAnimation.setAnimationListener(paramAnimationListener);
      }
      paramView.setVisibility(0);
      paramView.startAnimation(localAlphaAnimation);
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
      return;
    }
    catch (OutOfMemoryError paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public static void fadeOut(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong, int paramInt)
  {
    try
    {
      AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
      if (paramLong > 0L) {
        localAlphaAnimation.setStartOffset(paramLong);
      }
      localAlphaAnimation.setDuration(150L);
      if (paramAnimationListener != null) {
        localAlphaAnimation.setAnimationListener(paramAnimationListener);
      }
      paramView.startAnimation(localAlphaAnimation);
      paramView.setVisibility(8);
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
      return;
    }
    catch (OutOfMemoryError paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public static String getAndroidId(Context paramContext)
  {
    if ((mAndroidId == null) && (paramContext != null)) {
      mAndroidId = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
    return mAndroidId;
  }
  
  /* Error */
  public static String getCarrier(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -37
    //   3: invokevirtual 223	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 225	android/telephony/TelephonyManager
    //   9: astore_1
    //   10: aload_1
    //   11: ifnull +27 -> 38
    //   14: aload_1
    //   15: invokevirtual 229	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   18: astore_0
    //   19: aload_0
    //   20: ifnonnull +16 -> 36
    //   23: aload_1
    //   24: invokevirtual 232	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   27: astore_1
    //   28: aload_1
    //   29: areturn
    //   30: astore_0
    //   31: aconst_null
    //   32: areturn
    //   33: astore_1
    //   34: aload_0
    //   35: areturn
    //   36: aload_0
    //   37: areturn
    //   38: aconst_null
    //   39: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	paramContext	Context
    //   9	20	1	localObject	Object
    //   33	1	1	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	10	30	java/lang/Exception
    //   14	19	30	java/lang/Exception
    //   23	28	33	java/lang/Exception
  }
  
  public static String getCountry(Context paramContext)
  {
    Object localObject = "";
    String str;
    try
    {
      str = paramContext.getResources().getConfiguration().locale.getCountry();
      if (str != null)
      {
        localObject = str;
        if (str.length() != 0) {}
      }
      else
      {
        localObject = str;
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          localObject = str;
          paramContext = paramContext.getNetworkCountryIso();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      return localObject;
    }
    return str;
  }
  
  public static int getDIP(double paramDouble)
  {
    return (int)Math.round(getScale() * paramDouble);
  }
  
  public static int getDensity(Context paramContext)
  {
    if (mDensityDPI == null)
    {
      if (paramContext == null) {
        return 0;
      }
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      mDensityDPI = Integer.valueOf(localDisplayMetrics.densityDpi);
    }
    return mDensityDPI.intValue();
  }
  
  public static String getDeviceId(Context paramContext)
  {
    if (mDeviceId == null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null) {
        mDeviceId = paramContext.getDeviceId();
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return mDeviceId;
  }
  
  public static String getGUID(Context paramContext)
  {
    if ((mGUID == null) && (paramContext != null))
    {
      mGUID = Preferences.getSimplePref(paramContext, "android-guid", null);
      if (mGUID == null)
      {
        mGUID = UUID.randomUUID().toString();
        Preferences.setSimplePref(paramContext, "android-guid", mGUID);
      }
    }
    return mGUID;
  }
  
  public static String getLanguage(Context paramContext)
  {
    try
    {
      paramContext = Locale.getDefault().getLanguage().toLowerCase();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String getPhoneNumber(Context paramContext)
  {
    if (mPhoneNumber == null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null) {
        mPhoneNumber = paramContext.getLine1Number();
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    if (mPhoneNumber == null) {
      mPhoneNumber = "";
    }
    return mPhoneNumber;
  }
  
  public static int getPhoneType(Context paramContext)
  {
    try
    {
      int i = ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType();
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static int getResourseIdByName(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Class[] arrayOfClass = Class.forName(paramString1 + ".R").getClasses();
      paramString1 = null;
      int i = 0;
      for (;;)
      {
        if (i >= arrayOfClass.length) {}
        for (;;)
        {
          if (paramString1 == null) {
            break label91;
          }
          return paramString1.getField(paramString3).getInt(paramString1);
          if (!arrayOfClass[i].getName().split("\\$")[1].equals(paramString2)) {
            break;
          }
          paramString1 = arrayOfClass[i];
        }
        i += 1;
      }
      return 0;
    }
    catch (Throwable paramString1) {}
  }
  
  private static double getScale()
  {
    if (mScale == null) {
      mScale = Double.valueOf(Resources.getSystem().getDisplayMetrics().density);
    }
    return mScale.doubleValue();
  }
  
  public static int getScreenHeight()
  {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }
  
  public static int getScreenWidth()
  {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }
  
  public static String getVersionName(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName);
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String getWifiMacAddress(Context paramContext)
  {
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static int getYDPI(Context paramContext)
  {
    if (mYDPI == null)
    {
      if (paramContext == null) {
        return 0;
      }
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      mYDPI = Integer.valueOf((int)localDisplayMetrics.ydpi);
    }
    return mYDPI.intValue();
  }
  
  public static boolean isDefaultDensity(Context paramContext)
  {
    if (mIsDefaultDensity == null) {
      if (getDensity(paramContext) != 160) {
        break label32;
      }
    }
    label32:
    for (boolean bool = true;; bool = false)
    {
      mIsDefaultDensity = Boolean.valueOf(bool);
      return mIsDefaultDensity.booleanValue();
    }
  }
  
  public static boolean isHighDensity(Context paramContext)
  {
    if (mIsHighDensity == null) {
      if (getDensity(paramContext) != 240) {
        break label32;
      }
    }
    label32:
    for (boolean bool = true;; bool = false)
    {
      mIsHighDensity = Boolean.valueOf(bool);
      return mIsHighDensity.booleanValue();
    }
  }
  
  public static boolean isLandscape()
  {
    DisplayMetrics localDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    return localDisplayMetrics.widthPixels > localDisplayMetrics.heightPixels;
  }
  
  public static boolean isLargeLayout(Context paramContext)
  {
    boolean bool = false;
    if (mIsLarge == null)
    {
      if (paramContext == null) {
        return false;
      }
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 3) {
        bool = true;
      }
      mIsLarge = Boolean.valueOf(bool);
    }
    return mIsLarge.booleanValue();
  }
  
  public static boolean isLowDensity(Context paramContext)
  {
    if (mIsLowDensity == null) {
      if (getDensity(paramContext) != 120) {
        break label31;
      }
    }
    label31:
    for (boolean bool = true;; bool = false)
    {
      mIsLowDensity = Boolean.valueOf(bool);
      return mIsLowDensity.booleanValue();
    }
  }
  
  public static boolean isMediumDensity(Context paramContext)
  {
    if (mIsMediumDensity == null) {
      if (getDensity(paramContext) != 160) {
        break label32;
      }
    }
    label32:
    for (boolean bool = true;; bool = false)
    {
      mIsMediumDensity = Boolean.valueOf(bool);
      return mIsMediumDensity.booleanValue();
    }
  }
  
  public static boolean isNetworkConnected(Context paramContext)
  {
    if (checkAccessNetworStatePermission(paramContext))
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return (paramContext != null) && (paramContext.isConnected());
    }
    return true;
  }
  
  public static boolean isPortrait()
  {
    DisplayMetrics localDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    return localDisplayMetrics.heightPixels > localDisplayMetrics.widthPixels;
  }
  
  public static boolean isTabletLayout(Context paramContext)
  {
    return (isLargeLayout(paramContext)) || (isXLargeLayout(paramContext));
  }
  
  public static boolean isTvDensity(Context paramContext)
  {
    if (mIsTvDensity == null) {
      if (getDensity(paramContext) != 213) {
        break label32;
      }
    }
    label32:
    for (boolean bool = true;; bool = false)
    {
      mIsTvDensity = Boolean.valueOf(bool);
      return mIsTvDensity.booleanValue();
    }
  }
  
  public static boolean isXHighDensity(Context paramContext)
  {
    if (mIsXHighDensity == null) {
      if (getDensity(paramContext) != 320) {
        break label32;
      }
    }
    label32:
    for (boolean bool = true;; bool = false)
    {
      mIsXHighDensity = Boolean.valueOf(bool);
      return mIsXHighDensity.booleanValue();
    }
  }
  
  public static boolean isXLargeLayout(Context paramContext)
  {
    boolean bool = false;
    if (mIsXLarge == null)
    {
      if (paramContext == null) {
        return false;
      }
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 4) {
        bool = true;
      }
      mIsXLarge = Boolean.valueOf(bool);
    }
    return mIsXLarge.booleanValue();
  }
  
  public static boolean isXXHighDensity(Context paramContext)
  {
    if (mIsXXHighDensity == null) {
      if (getDensity(paramContext) != 480) {
        break label32;
      }
    }
    label32:
    for (boolean bool = true;; bool = false)
    {
      mIsXXHighDensity = Boolean.valueOf(bool);
      return mIsXXHighDensity.booleanValue();
    }
  }
  
  public static void quickFadeIn(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong)
  {
    fadeIn(paramView, paramAnimationListener, paramLong, 150);
  }
  
  public static void quickFadeOut(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong)
  {
    fadeOut(paramView, paramAnimationListener, paramLong, 150);
  }
  
  public static void serializeInstalledApps(Context paramContext, XmlSerializer paramXmlSerializer, boolean paramBoolean)
  {
    paramContext = paramContext.getPackageManager();
    List localList = paramContext.getInstalledPackages(0);
    int i = 0;
    if (i >= localList.size()) {
      return;
    }
    PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
    if ((localPackageInfo == null) || ((!paramBoolean) && ((localPackageInfo.packageName.startsWith("com.android")) || (localPackageInfo.packageName.startsWith("com.google.android"))))) {}
    for (;;)
    {
      i += 1;
      break;
      paramXmlSerializer.startTag("", "app");
      paramXmlSerializer.attribute("", "internal", localPackageInfo.packageName);
      paramXmlSerializer.attribute("", "display", localPackageInfo.applicationInfo.loadLabel(paramContext).toString());
      if ((localPackageInfo.versionName != null) && (localPackageInfo.versionName.length() > 0)) {
        paramXmlSerializer.attribute("", "version", localPackageInfo.versionName);
      }
      paramXmlSerializer.endTag("", "app");
    }
  }
  
  public static void slideInDown(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
    if (paramLong > 0L) {
      localTranslateAnimation.setStartOffset(paramLong);
    }
    localTranslateAnimation.setInterpolator(paramView.getContext(), 17432581);
    localTranslateAnimation.setDuration(150L);
    if (paramAnimationListener != null) {
      localTranslateAnimation.setAnimationListener(paramAnimationListener);
    }
    paramView.startAnimation(localTranslateAnimation);
    paramView.setVisibility(0);
  }
  
  public static void slideInUp(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong)
  {
    if (paramView != null)
    {
      TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
      if (paramLong > 0L) {
        localTranslateAnimation.setStartOffset(paramLong);
      }
      localTranslateAnimation.setInterpolator(paramView.getContext(), 17432581);
      localTranslateAnimation.setDuration(150L);
      if (paramAnimationListener != null) {
        localTranslateAnimation.setAnimationListener(paramAnimationListener);
      }
      paramView.startAnimation(localTranslateAnimation);
      paramView.setVisibility(0);
    }
  }
  
  public static void slideOutDown(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong)
  {
    if (paramView != null)
    {
      TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
      if (paramLong > 0L) {
        localTranslateAnimation.setStartOffset(paramLong);
      }
      localTranslateAnimation.setInterpolator(paramView.getContext(), 17432581);
      localTranslateAnimation.setDuration(150L);
      if (paramAnimationListener != null) {
        localTranslateAnimation.setAnimationListener(paramAnimationListener);
      }
      paramView.startAnimation(localTranslateAnimation);
      paramView.setVisibility(8);
    }
  }
  
  public static void slideOutUp(View paramView, Animation.AnimationListener paramAnimationListener, long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -1.0F);
    if (paramLong > 0L) {
      localTranslateAnimation.setStartOffset(paramLong);
    }
    localTranslateAnimation.setInterpolator(paramView.getContext(), 17432581);
    localTranslateAnimation.setDuration(150L);
    if (paramAnimationListener != null) {
      localTranslateAnimation.setAnimationListener(paramAnimationListener);
    }
    paramView.startAnimation(localTranslateAnimation);
    paramView.setVisibility(8);
  }
}
