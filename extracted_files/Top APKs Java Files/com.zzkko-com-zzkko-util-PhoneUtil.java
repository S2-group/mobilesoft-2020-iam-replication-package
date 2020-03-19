package com.zzkko.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.support.v4.text.TextUtilsCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.zzkko.app.ZzkkoApplication;
import com.zzkko.bussiness.login.domain.UserInfo;
import io.reactivex.Observable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nonnull;

public class PhoneUtil
{
  private static String APPVERSION_NAME;
  private static final String KEY_DEVICE_ID = "key_FOR_DEVICE_ID";
  public static final String TAG = "PhoneUtil";
  
  public PhoneUtil() {}
  
  public static void clearWebCookies(Context paramContext)
  {
    CookieSyncManager.createInstance(paramContext);
    CookieManager.getInstance().removeAllCookie();
  }
  
  public static boolean copyTxtToClipboard(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramContext = (ClipboardManager)paramContext.getSystemService("clipboard");
      if (paramContext != null)
      {
        paramContext.setPrimaryClip(ClipData.newPlainText("", paramString));
        return true;
      }
    }
    return false;
  }
  
  public static void darkWindow(Activity paramActivity, float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    paramActivity.getWindow().setAttributes(localLayoutParams);
  }
  
  public static void disMissKeyBoard(View paramView)
  {
    if (paramView == null) {
      return;
    }
    InputMethodManager localInputMethodManager = (InputMethodManager)paramView.getContext().getSystemService("input_method");
    if (localInputMethodManager == null) {
      return;
    }
    localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void dismissDialog(Dialog paramDialog)
  {
    if (paramDialog != null) {
      try
      {
        paramDialog.dismiss();
        return;
      }
      catch (Exception paramDialog)
      {
        ThrowableExtension.printStackTrace(paramDialog);
      }
    }
  }
  
  public static void fixGestureBoostLeak(Context paramContext)
  {
    if (!RomUtil.isEmui())
    {
      Log.w("PhoneUtil", "not emui");
      return;
    }
    try
    {
      Object localObject1 = Class.forName("android.gestureboost.GestureBoostManager");
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("clazz ");
      ((StringBuilder)localObject2).append(localObject1);
      Log.w("PhoneUtil", ((StringBuilder)localObject2).toString());
      localObject2 = ((Class)localObject1).getDeclaredField("sGestureBoostManager");
      ((Field)localObject2).setAccessible(true);
      localObject1 = ((Class)localObject1).getDeclaredField("mContext");
      ((Field)localObject1).setAccessible(true);
      localObject2 = ((Field)localObject2).get(null);
      if (localObject2 != null)
      {
        ((Field)localObject1).set(localObject2, paramContext.getApplicationContext());
        return;
      }
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
  }
  
  private static String generateDeviceId()
  {
    String str = UUID.randomUUID().toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shein_");
    localStringBuilder.append(str);
    return localStringBuilder.toString();
  }
  
  public static int getAPIVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  @Nullable
  public static Activity getActivityFromContext(@Nonnull Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return (Activity)paramContext;
    }
    if ((paramContext instanceof ContextWrapper)) {
      return getActivityFromContext(((ContextWrapper)paramContext).getBaseContext());
    }
    return null;
  }
  
  public static String getAppChannelValue()
  {
    Object localObject3 = ZzkkoApplication.getContext();
    Object localObject1 = null;
    String str = null;
    if (localObject3 != null) {
      try
      {
        localObject3 = ((Context)localObject3).getPackageManager().getApplicationInfo(((Context)localObject3).getPackageName(), 128).metaData;
        localObject1 = ((Bundle)localObject3).getString("CHANNEL");
        try
        {
          if (TextUtils.isEmpty((CharSequence)localObject1))
          {
            str = ((Bundle)localObject3).getString("AF_PRE_INSTALL_NAME");
            localObject1 = str;
            break label72;
          }
        }
        catch (Exception localException1) {}
        ThrowableExtension.printStackTrace((Throwable)localObject2);
      }
      catch (Exception localException2)
      {
        localObject1 = localException1;
        localObject2 = localException2;
      }
    }
    label72:
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  public static String getAppSupperLanguage()
  {
    String str2 = Locale.getDefault().getLanguage();
    String str1 = getSiteCountry();
    if (str2 == null)
    {
      str1 = "en";
    }
    else if ("zh".equals(str2))
    {
      if ((!"TW".equals(str1)) && (!"HK".equals(str1))) {
        str1 = "en";
      } else {
        str1 = "zh-tw";
      }
    }
    else if ("pt".equals(str2))
    {
      str1 = "pt-br";
    }
    else if ("in".equals(str2))
    {
      str1 = "id";
    }
    else
    {
      str1 = str2;
      if ("iw".equals(str2)) {
        str1 = "he";
      }
    }
    str2 = str1.trim();
    int i = -1;
    switch (str2.hashCode())
    {
    default: 
      break;
    case 115814786: 
      if (str2.equals("zh-tw")) {
        i = 6;
      }
      break;
    case 115813378: 
      if (str2.equals("zh-HK")) {
        i = 7;
      }
      break;
    case 106936505: 
      if (str2.equals("pt-br")) {
        i = 10;
      }
      break;
    case 3763: 
      if (str2.equals("vi")) {
        i = 13;
      }
      break;
    case 3710: 
      if (str2.equals("tr")) {
        i = 12;
      }
      break;
    case 3700: 
      if (str2.equals("th")) {
        i = 11;
      }
      break;
    case 3683: 
      if (str2.equals("sv")) {
        i = 15;
      }
      break;
    case 3651: 
      if (str2.equals("ru")) {
        i = 5;
      }
      break;
    case 3518: 
      if (str2.equals("nl")) {
        i = 9;
      }
      break;
    case 3371: 
      if (str2.equals("it")) {
        i = 4;
      }
      break;
    case 3355: 
      if (str2.equals("id")) {
        i = 8;
      }
      break;
    case 3325: 
      if (str2.equals("he")) {
        i = 14;
      }
      break;
    case 3276: 
      if (str2.equals("fr")) {
        i = 3;
      }
      break;
    case 3246: 
      if (str2.equals("es")) {
        i = 2;
      }
      break;
    case 3201: 
      if (str2.equals("de")) {
        i = 1;
      }
      break;
    case 3121: 
      if (str2.equals("ar")) {
        i = 0;
      }
      break;
    }
    switch (i)
    {
    default: 
      return "en";
    }
    return str1;
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo("com.zzkko", 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    if (TextUtils.isEmpty(APPVERSION_NAME)) {
      try
      {
        APPVERSION_NAME = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      }
      catch (Exception paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
      }
    }
    if (!TextUtils.isEmpty(APPVERSION_NAME)) {
      return APPVERSION_NAME;
    }
    return "version unknown";
  }
  
  private static String getDepartment(String paramString)
  {
    paramString = paramString.trim();
    int i = paramString.hashCode();
    if (i != 3121)
    {
      if (i != 3201)
      {
        if (i != 3241)
        {
          if (i != 3246)
          {
            if (i != 3276)
            {
              if (i != 3371)
              {
                if ((i == 3651) && (paramString.equals("ru")))
                {
                  i = 1;
                  break label170;
                }
              }
              else if (paramString.equals("it"))
              {
                i = 2;
                break label170;
              }
            }
            else if (paramString.equals("fr"))
            {
              i = 5;
              break label170;
            }
          }
          else if (paramString.equals("es"))
          {
            i = 4;
            break label170;
          }
        }
        else if (paramString.equals("en"))
        {
          i = 6;
          break label170;
        }
      }
      else if (paramString.equals("de"))
      {
        i = 3;
        break label170;
      }
    }
    else if (paramString.equals("ar"))
    {
      i = 0;
      break label170;
    }
    i = -1;
    switch (i)
    {
    default: 
      break;
    case 6: 
      paramString = getIpCountry();
      if ("US".equalsIgnoreCase(paramString)) {
        return "US";
      }
      if ("IN".equalsIgnoreCase(paramString)) {
        return "IN";
      }
      break;
    case 5: 
      return "French";
    case 4: 
      return "Spanish";
    case 3: 
      return "German";
    case 2: 
      return "Italian";
    case 1: 
      return "Russian";
    case 0: 
      label170:
      return "Arabic";
    }
    return "English";
  }
  
  public static String getDeviceId(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("key_FOR_DEVICE_ID", null);
    if (paramContext == null) {
      return "";
    }
    return paramContext;
  }
  
  public static String getDeviceModel()
  {
    return Build.MODEL;
  }
  
  public static String getFAQSiteLanguage()
  {
    if ("andsheur".equalsIgnoreCase(SPUtil.getAppSite())) {
      return "en-us";
    }
    String str1 = getAppSupperLanguage();
    String str2 = getSiteCountry();
    boolean bool = "en".equals(str1);
    int j = 0;
    int i = 0;
    if (bool)
    {
      str1 = str2.trim();
      j = str1.hashCode();
      if (j != 2100)
      {
        if (j != 2142)
        {
          if (j != 2267)
          {
            if (j != 2341)
            {
              if (j != 2642)
              {
                if (j != 2710)
                {
                  if ((j == 2718) && (str1.equals("US"))) {
                    break label210;
                  }
                }
                else if (str1.equals("UK"))
                {
                  i = 1;
                  break label210;
                }
              }
              else if (str1.equals("SE"))
              {
                i = 5;
                break label210;
              }
            }
            else if (str1.equals("IN"))
            {
              i = 4;
              break label210;
            }
          }
          else if (str1.equals("GB"))
          {
            i = 2;
            break label210;
          }
        }
        else if (str1.equals("CA"))
        {
          i = 6;
          break label210;
        }
      }
      else if (str1.equals("AU"))
      {
        i = 3;
        break label210;
      }
      i = -1;
      switch (i)
      {
      default: 
        return "en-us";
      case 6: 
        return "en-ca";
      case 5: 
        return "en-ie";
      case 4: 
        return "en-in";
      case 3: 
        return "en-au";
      case 2: 
        return "en-gb";
      case 1: 
        label210:
        return "en-gb";
      }
      return "en-nz";
    }
    if ("zh-tw".equals(str1))
    {
      if ("HK".equals(str2)) {
        return "zh-cn";
      }
      return str1;
    }
    if ("fr".equals(str1))
    {
      str2 = str2.trim();
      if ((str2.hashCode() == 2142) && (str2.equals("CA"))) {
        i = j;
      } else {
        i = -1;
      }
      if (i != 0) {
        return str1;
      }
      return "fr-ca";
    }
    return str1;
  }
  
  public static String getIpCountry()
  {
    String str = SPUtil.getSavedIpCountryCode(ZzkkoApplication.getContext());
    if (TextUtils.isEmpty(str)) {
      return getLocaleCountry();
    }
    return str.toUpperCase();
  }
  
  public static String getLocaleCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  private static String getLocaleLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  @Deprecated
  public static String getMWebSite(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "http://m.shein.com";
    }
    Object localObject = paramString;
    if (paramString.toUpperCase().matches("SA|KW|AE|QA|OM|BH")) {
      localObject = "ar";
    }
    boolean bool = "https://api-service.shein.com".contains("api-service.shein");
    int i = 1;
    paramString = ((String)localObject).toLowerCase();
    if ((bool ^ true))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://m.shein.com/");
      ((StringBuilder)localObject).append(paramString);
      return ((StringBuilder)localObject).toString();
    }
    localObject = paramString.trim();
    switch (((String)localObject).hashCode())
    {
    default: 
      break;
    case 3768: 
      if (((String)localObject).equals("vn")) {
        i = 15;
      }
      break;
    case 3742: 
      if (((String)localObject).equals("us")) {
        i = 0;
      }
      break;
    case 3715: 
      if (((String)localObject).equals("tw")) {
        i = 17;
      }
      break;
    case 3710: 
      if (((String)localObject).equals("tr")) {
        i = 12;
      }
      break;
    case 3700: 
      if (((String)localObject).equals("th")) {
        i = 7;
      }
      break;
    case 3651: 
      if (((String)localObject).equals("ru")) {
        i = 9;
      }
      break;
    case 3518: 
      if (((String)localObject).equals("nl")) {
        i = 11;
      }
      break;
    case 3499: 
      if (((String)localObject).equals("mx")) {
        i = 16;
      }
      break;
    case 3371: 
      if (((String)localObject).equals("it")) {
        i = 8;
      }
      break;
    case 3365: 
      if (((String)localObject).equals("in")) {
        i = 18;
      }
      break;
    case 3363: 
      if (((String)localObject).equals("il")) {
        i = 6;
      }
      break;
    case 3355: 
      if (((String)localObject).equals("id")) {
        i = 10;
      }
      break;
    case 3331: 
      if (((String)localObject).equals("hk")) {
        i = 14;
      }
      break;
    case 3291: 
      if (((String)localObject).equals("gb")) {
        i = 19;
      }
      break;
    case 3276: 
      if (((String)localObject).equals("fr")) {
        i = 2;
      }
      break;
    case 3246: 
      if (!((String)localObject).equals("es")) {
        break;
      }
      break;
    case 3201: 
      if (((String)localObject).equals("de")) {
        i = 3;
      }
      break;
    case 3152: 
      if (((String)localObject).equals("br")) {
        i = 13;
      }
      break;
    case 3124: 
      if (((String)localObject).equals("au")) {
        i = 4;
      }
      break;
    case 3121: 
      if (((String)localObject).equals("ar")) {
        i = 5;
      }
      break;
    }
    i = -1;
    switch (i)
    {
    default: 
      return "http://m.shein.com";
    case 19: 
      return "http://m.shein.co.uk";
    case 17: 
    case 18: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://m.shein.");
      ((StringBuilder)localObject).append(paramString);
      return ((StringBuilder)localObject).toString();
    case 14: 
    case 15: 
    case 16: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://m.shein.com.");
      ((StringBuilder)localObject).append(paramString);
      return ((StringBuilder)localObject).toString();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("http://m.shein.com/");
    ((StringBuilder)localObject).append(paramString);
    return ((StringBuilder)localObject).toString();
  }
  
  public static String getMWebSiteLanguage(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && ("in".equalsIgnoreCase(paramString))) {
      return "in";
    }
    if ((!TextUtils.isEmpty(paramString)) && ("gb".equalsIgnoreCase(paramString))) {
      return "uk";
    }
    paramString = getAppSupperLanguage();
    String str = paramString.trim();
    int i = -1;
    int j = str.hashCode();
    if (j != 3763)
    {
      if (j != 106936505)
      {
        if ((j == 115814786) && (str.equals("zh-tw"))) {
          i = 0;
        }
      }
      else if (str.equals("pt-br")) {
        i = 2;
      }
    }
    else if (str.equals("vi")) {
      i = 1;
    }
    switch (i)
    {
    default: 
      return paramString;
    case 2: 
      return "br";
    case 1: 
      return "vn";
    }
    return "tw";
  }
  
  public static String getMacAddress(Context paramContext)
  {
    try
    {
      paramContext = (WifiManager)paramContext.getApplicationContext().getSystemService("wifi");
      if (paramContext == null) {
        paramContext = null;
      } else {
        paramContext = paramContext.getConnectionInfo();
      }
      if (paramContext != null) {
        return paramContext.getMacAddress();
      }
      paramContext = new StringBuilder();
      paramContext.append(System.currentTimeMillis());
      paramContext.append("");
      paramContext = paramContext.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
      paramContext = new StringBuilder();
      paramContext.append(System.currentTimeMillis());
      paramContext.append("");
    }
    return paramContext.toString();
  }
  
  public static String getMemberLevel()
  {
    UserInfo localUserInfo = ((ZzkkoApplication)ZzkkoApplication.getContext()).getUserInfo();
    if ((localUserInfo != null) && (!TextUtils.isEmpty(localUserInfo.getLevelName()))) {
      return localUserInfo.getLevelName();
    }
    return "";
  }
  
  public static int getNavigationBarHeight(Activity paramActivity)
  {
    paramActivity = paramActivity.getResources();
    int i = paramActivity.getIdentifier("navigation_bar_height", "dimen", "android");
    if (i > 0) {
      return paramActivity.getDimensionPixelSize(i);
    }
    return 0;
  }
  
  public static int getNavigationBarHeithtLandscape(Activity paramActivity)
  {
    paramActivity = paramActivity.getResources();
    int i = paramActivity.getIdentifier("navigation_bar_height_landscape", "dimen", "android");
    if (i > 0) {
      return paramActivity.getDimensionPixelSize(i);
    }
    return 0;
  }
  
  public static int getNavigationBarWidth(Activity paramActivity)
  {
    paramActivity = paramActivity.getResources();
    int i = paramActivity.getIdentifier("navigation_bar_width", "dimen", "android");
    if (i > 0) {
      return paramActivity.getDimensionPixelSize(i);
    }
    return 0;
  }
  
  public static String getNetworkType(Context paramContext)
  {
    if (isWifi(paramContext)) {
      return "WIFI";
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    int i = 0;
    if (paramContext != null) {
      i = paramContext.getNetworkType();
    }
    switch (i)
    {
    default: 
      return "UNKNOWN";
    case 13: 
      return "4G";
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
    case 15: 
      return "3G";
    }
    return "2G";
  }
  
  public static String getOs()
  {
    return "android";
  }
  
  public static String getOsver()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String getPhoneTopPackageName(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (Build.VERSION.SDK_INT == 21) {
      try
      {
        paramContext = ((ActivityManager.RunningAppProcessInfo)paramContext.getRunningAppProcesses().get(0)).processName;
        return paramContext;
      }
      catch (Exception paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
        return null;
      }
    }
    return ((ActivityManager.RunningTaskInfo)paramContext.getRunningTasks(1).get(0)).topActivity.getPackageName();
  }
  
  public static String getSign()
  {
    try
    {
      Object localObject = ZzkkoApplication.getContext();
      localObject = MD5Util.MD5(localObject.getPackageManager().getPackageInfo(localObject.getPackageName(), 64).signatures[0].toCharsString());
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      ThrowableExtension.printStackTrace(localNameNotFoundException);
    }
    return "";
  }
  
  public static String getSiteCountry()
  {
    String str2 = SPUtil.getSavedHeadCountryCode();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = getIpCountry();
    }
    return str1;
  }
  
  public static String getVendor()
  {
    return Build.MANUFACTURER;
  }
  
  public static String[] getZendeskTags()
  {
    String str2 = getLocaleLanguage();
    String str1;
    if (("TW".equals(getLocaleCountry())) && ("zh".equals(str2))) {
      str1 = "zh-tw";
    } else {
      str1 = str2;
    }
    String[] arrayOfString = new String[5];
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("site_android_");
    localStringBuilder.append(str1);
    arrayOfString[0] = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("lan_");
    localStringBuilder.append(str1);
    arrayOfString[1] = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("user_");
    localStringBuilder.append(str1);
    arrayOfString[2] = localStringBuilder.toString();
    arrayOfString[3] = getDepartment(str2);
    return arrayOfString;
  }
  
  public static boolean hasLocationPermission(Context paramContext)
  {
    boolean bool1;
    if ((PermissionChecker.checkSelfPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) && (PermissionChecker.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION") != 0)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    boolean bool2 = bool1;
    if (!bool1)
    {
      bool2 = bool1;
      if (Build.VERSION.SDK_INT >= 18)
      {
        if (PermissionChecker.checkSelfPermission(paramContext, "android.permission.LOCATION_HARDWARE") == 0) {
          return true;
        }
        bool2 = false;
      }
    }
    return bool2;
  }
  
  public static boolean isAdCollectionEnabled()
  {
    String str = SPUtil.getAppSite();
    boolean bool2 = TextUtils.isEmpty(str);
    boolean bool1 = false;
    if (!bool2)
    {
      str = str.toLowerCase();
      if ((str.equals("andshgb")) || (str.equals("andshfr")) || (str.equals("andshde")) || (str.equals("andshes")) || (str.equals("andshit")) || (str.equals("andshnl")) || (str.equals("andshse")) || (str.equals("andshother"))) {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  public static boolean isAppOnForeground(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getApplicationContext().getSystemService("activity");
    paramContext = paramContext.getApplicationContext().getPackageName();
    if (localObject == null) {
      return false;
    }
    localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    if (localObject == null) {
      return false;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.processName.equals(paramContext)) && (localRunningAppProcessInfo.importance == 100)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isArabian(Context paramContext)
  {
    return "ar".equalsIgnoreCase(getAppSupperLanguage());
  }
  
  public static boolean isBackground(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = null;
    Object localObject1 = localRunningAppProcessInfo;
    if (localActivityManager != null) {
      try
      {
        localObject1 = localActivityManager.getRunningAppProcesses();
      }
      catch (Exception localException)
      {
        ThrowableExtension.printStackTrace(localException);
        localObject2 = localRunningAppProcessInfo;
      }
    }
    if (localObject2 == null) {
      return true;
    }
    Object localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
      if (localRunningAppProcessInfo.processName.equals(paramContext.getPackageName())) {
        return localRunningAppProcessInfo.importance == 400;
      }
    }
    return false;
  }
  
  public static boolean isBeforeKitkat()
  {
    return Build.VERSION.SDK_INT < 19;
  }
  
  public static boolean isBeforeLOLLIPOP()
  {
    return Build.VERSION.SDK_INT < 21;
  }
  
  public static boolean isEnglish(Context paramContext)
  {
    return "en".equalsIgnoreCase(paramContext.getResources().getConfiguration().locale.getLanguage());
  }
  
  public static boolean isHighThanOrEqual4point0()
  {
    return getAPIVersion() >= 14;
  }
  
  public static boolean isIntentAvailable(Context paramContext, String paramString)
  {
    paramString = new Intent(paramString);
    paramContext = paramContext.getPackageManager();
    boolean bool = false;
    if (paramContext.resolveActivity(paramString, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isLanguageGermany()
  {
    return Locale.getDefault().getLanguage().equals(new Locale("de").getLanguage());
  }
  
  public static boolean isMiddleEastCountry()
  {
    return getIpCountry().matches("SA|KW|AE|QA|OM|BH");
  }
  
  public static boolean isMiddleEastCountryAndEnLanguage(Context paramContext)
  {
    return "en-us".equalsIgnoreCase(getAppSupperLanguage()) & isMiddleEastCountry();
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected()) && (paramContext.getState() == NetworkInfo.State.CONNECTED)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isNetworkConnected(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null) {
      paramContext = paramContext.getActiveNetworkInfo();
    } else {
      paramContext = null;
    }
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }
  
  public static boolean isNewLOLLIPOP()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getInstalledPackages(0);
      ArrayList localArrayList = new ArrayList();
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.size())
        {
          localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
          i += 1;
        }
      }
      return localArrayList.contains(paramString);
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return false;
  }
  
  public static boolean isRussiaLanguage()
  {
    return "RU".equalsIgnoreCase(ZzkkoApplication.getContext().getResources().getConfiguration().locale.getLanguage());
  }
  
  public static boolean isVirtualKeyShow(Activity paramActivity)
  {
    if (isHighThanOrEqual4point0())
    {
      paramActivity = paramActivity.getResources();
      int i = paramActivity.getIdentifier("config_showNavigationBar", "bool", "android");
      if (i > 0) {
        return paramActivity.getBoolean(i);
      }
    }
    return false;
  }
  
  public static boolean isWifi(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      if (PermissionChecker.checkSelfPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE") == 0)
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        NetworkInfo localNetworkInfo = null;
        if (localConnectivityManager != null) {
          localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        }
        boolean bool1 = bool2;
        if (localNetworkInfo != null)
        {
          bool1 = bool2;
          if (isNetworkConnected(paramContext))
          {
            int i = localNetworkInfo.getType();
            bool1 = bool2;
            if (i == 1) {
              bool1 = true;
            }
          }
        }
        return bool1;
      }
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return false;
  }
  
  public static boolean minSdkCheck(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  public static void removeActivityFromTransitionManager(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return;
    }
    try
    {
      Object localObject = TransitionManager.class.getDeclaredField("sRunningTransitions");
      ((Field)localObject).setAccessible(true);
      localObject = (ThreadLocal)((Field)localObject).get(TransitionManager.class);
      if (((ThreadLocal)localObject).get() != null)
      {
        if (((WeakReference)((ThreadLocal)localObject).get()).get() == null) {
          return;
        }
        localObject = (ArrayMap)((WeakReference)((ThreadLocal)localObject).get()).get();
        paramActivity = paramActivity.getWindow().getDecorView();
        if ((localObject != null) && (((ArrayMap)localObject).containsKey(paramActivity))) {
          ((ArrayMap)localObject).remove(paramActivity);
        }
      }
      else {}
    }
    catch (Exception paramActivity)
    {
      ThrowableExtension.printStackTrace(paramActivity);
      return;
    }
    catch (NoSuchFieldException paramActivity)
    {
      ThrowableExtension.printStackTrace(paramActivity);
    }
  }
  
  public static void saveDeviceId(Context paramContext)
  {
    if (!TextUtils.isEmpty(getDeviceId(paramContext))) {
      return;
    }
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    Observable.create(PhoneUtil..Lambda.0.$instance).subscribe(new PhoneUtil..Lambda.1(paramContext), new PhoneUtil..Lambda.2(paramContext));
  }
  
  public static boolean shouldReversLayout()
  {
    Context localContext = ZzkkoApplication.getContext();
    boolean bool = false;
    if (localContext != null) {
      try
      {
        i = ZzkkoApplication.getContext().getResources().getConfiguration().getLayoutDirection();
      }
      catch (Exception localException1)
      {
        ThrowableExtension.printStackTrace(localException1);
        break label49;
      }
    } else {
      try
      {
        i = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
      }
      catch (Exception localException2)
      {
        ThrowableExtension.printStackTrace(localException2);
      }
    }
    label49:
    int i = 0;
    if (i != 0) {
      bool = true;
    }
    return bool;
  }
  
  public static void showDialog(Dialog paramDialog)
  {
    if (paramDialog != null) {
      try
      {
        paramDialog.show();
        return;
      }
      catch (Exception paramDialog)
      {
        ThrowableExtension.printStackTrace(paramDialog);
      }
    }
  }
  
  public static void showKeyBoard(EditText paramEditText)
  {
    try
    {
      ((InputMethodManager)paramEditText.getContext().getSystemService("input_method")).showSoftInput(paramEditText, 1);
      return;
    }
    catch (Exception paramEditText)
    {
      ThrowableExtension.printStackTrace(paramEditText);
      return;
    }
    catch (NullPointerException paramEditText)
    {
      ThrowableExtension.printStackTrace(paramEditText);
    }
  }
}
