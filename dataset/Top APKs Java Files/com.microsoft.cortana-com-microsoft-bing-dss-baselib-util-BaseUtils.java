package com.microsoft.bing.dss.baselib.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View.OnClickListener;
import android.view.WindowManager.BadTokenException;
import com.microsoft.bing.dss.baselib.deviceInfo.PackageUtil;
import com.microsoft.bing.dss.baselib.networking.HttpUtil;
import com.microsoft.bing.dss.baselib.networking.HttpUtil.HttpMethod;
import com.microsoft.bing.dss.baselib.networking.methods.HttpRequest;
import com.microsoft.bing.dss.baselib.security.CryptographyUtil;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class BaseUtils
{
  private static final String LOG_TAG = BaseUtils.class.getName();
  private static final int LOWER_BOUND_TOAST_ID_LENGTH = 15;
  private static final String MOCK_SCHEME = "mockScheme";
  private static final int UPPER_BOUND_TOAST_ID_LENGTH = 32;
  private static Context _appContext;
  private static Boolean _isDebugMode;
  
  public BaseUtils() {}
  
  public static byte[] bmpToByteArray(Bitmap paramBitmap, boolean paramBoolean, Bitmap.CompressFormat paramCompressFormat, int paramInt)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      paramBitmap.compress(paramCompressFormat, paramInt, localByteArrayOutputStream);
      if (paramBoolean) {
        paramBitmap.recycle();
      }
      paramBitmap = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return paramBitmap;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return null;
  }
  
  private static String capitalize(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0)) {
      str = "";
    }
    char c;
    do
    {
      return str;
      c = paramString.charAt(0);
      str = paramString;
    } while (Character.isUpperCase(c));
    return Character.toUpperCase(c) + paramString.substring(1);
  }
  
  public static String createFriendlyGuid(String paramString)
  {
    String str = "";
    if (!isNullOrwhitespaces(paramString)) {
      if (paramString.length() > 5) {
        break label104;
      }
    }
    for (;;)
    {
      str = paramString + "#";
      paramString = Calendar.getInstance();
      SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      return str + localSimpleDateFormat2.format(paramString.getTime()) + "##" + localSimpleDateFormat1.format(paramString.getTime());
      label104:
      paramString = paramString.substring(paramString.length() - 6);
    }
  }
  
  public static String createGuid()
  {
    String str = UUID.randomUUID().toString();
    String.format("created guid: %s", new Object[] { str });
    return str;
  }
  
  public static String extractUriPath(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramString = Uri.parse(paramString).getPath();
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String formatDateTime(String paramString, Calendar paramCalendar)
  {
    return new SimpleDateFormat(paramString).format(paramCalendar.getTime());
  }
  
  public static String generateToastId(String paramString)
  {
    paramString = String.valueOf(paramString.hashCode());
    int i = paramString.length();
    String str;
    if ((i < 32) && (i > 15))
    {
      str = paramString;
      return str;
    }
    if (i <= 15) {
      for (;;)
      {
        str = paramString;
        if (paramString.length() >= 15) {
          break;
        }
        paramString = paramString + paramString;
      }
    }
    paramString = paramString.toCharArray();
    paramString[0] = ((char)(paramString[0] + paramString[31]));
    return Arrays.copyOfRange(paramString, 0, 30).toString();
  }
  
  public static Context getAppContext()
  {
    return _appContext;
  }
  
  public static int getBuildNumber(Context paramContext)
  {
    int i = 0;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (paramContext != null) {
        i = paramContext.getInt("buildNumber", 0);
      }
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int getDeveloperState()
  {
    return PreferenceHelper.getPreferences().getInt("IsDeveloper", 0);
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return capitalize(str2);
    }
    return capitalize(str1) + " " + str2;
  }
  
  public static List<PackageInfo> getInstalledApps(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
    if (localObject1 == null) {
      localObject1 = new ArrayList();
    }
    for (;;)
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = ((List)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        if ((paramContext.getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName) != null) && (!localPackageInfo.packageName.equals(paramContext.getPackageName()))) {
          localArrayList.add(localPackageInfo);
        }
      }
      if (!paramBoolean)
      {
        ((List)localObject1).clear();
        ((List)localObject1).addAll(localArrayList);
        localArrayList.clear();
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (PackageInfo)((Iterator)localObject1).next();
          if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) {
            localArrayList.add(localObject2);
          }
        }
      }
      Collections.sort(localArrayList, new BaseUtils.1(paramContext));
      return localArrayList;
    }
  }
  
  public static String getMockSnrFlight()
  {
    String str = PreferenceHelper.getPreferences().getString("mock_snr_flight", null);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return str;
  }
  
  public static String getMockSnrFlightParam()
  {
    Object localObject = getMockSnrFlight();
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      localObject = null;
    }
    Uri localUri;
    String str;
    do
    {
      return localObject;
      localUri = Uri.parse(String.format("%s://?%s", new Object[] { "mockScheme", localObject }));
      str = localUri.getQueryParameter("sf");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return localUri.getQueryParameter("setflight");
  }
  
  public static Object getServerConfig(String paramString, Object paramObject)
  {
    if (!PreferenceHelper.getPreferences().contains(paramString)) {
      return paramObject;
    }
    if ((paramObject instanceof String)) {
      paramString = PreferenceHelper.getPreferences().getString(paramString, (String)paramObject);
    }
    for (;;)
    {
      return paramString;
      if ((paramObject instanceof Boolean))
      {
        paramString = Boolean.valueOf(PreferenceHelper.getPreferences().getBoolean(paramString, ((Boolean)paramObject).booleanValue()));
      }
      else
      {
        if (!(paramObject instanceof Integer)) {
          break;
        }
        paramString = Integer.valueOf(PreferenceHelper.getPreferences().getInt(paramString, ((Integer)paramObject).intValue()));
      }
    }
  }
  
  public static String getSharedPreferences(String paramString)
  {
    return PreferenceHelper.getPreferences().getString(paramString, null);
  }
  
  public static String getUserANIDMD5()
  {
    SharedPreferences localSharedPreferences = PreferenceHelper.getPreferences();
    String str2 = localSharedPreferences.getString("UserIdForCrashReportKey", null);
    String str1 = str2;
    if (str2 == null)
    {
      String str3 = localSharedPreferences.getString("CacheAnidCookieKey", null);
      str1 = str2;
      if (str3 != null)
      {
        str1 = CryptographyUtil.md5Hash(str3);
        localSharedPreferences.edit().putString("UserIdForCrashReportKey", str1).apply();
      }
    }
    return str1;
  }
  
  public static boolean hasKITKAT()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  public static boolean hasKITKAT_WATCH()
  {
    return Build.VERSION.SDK_INT >= 20;
  }
  
  public static boolean hasLOLLIPOP()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static Boolean isDebugMode()
  {
    if (_isDebugMode != null) {
      return _isDebugMode;
    }
    if (_appContext == null) {
      throw new IllegalStateException("No cached context value. Please call BaseUtils.setContext first");
    }
    if ((_appContext.getApplicationInfo().flags & 0x2) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      Boolean localBoolean = Boolean.valueOf(bool);
      _isDebugMode = localBoolean;
      return localBoolean;
    }
  }
  
  public static boolean isDeviceConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean isDeviceConnectedWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean isNewSession(String paramString)
  {
    String str = getSharedPreferences("SessionID");
    return (isNullOrwhitespaces(str)) || (!str.equals(paramString));
  }
  
  public static boolean isNullOrwhitespaces(String paramString)
  {
    return (paramString == null) || (paramString.trim().isEmpty());
  }
  
  public static boolean isStagingServiceOverriding()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (PreferenceHelper.getPreferences().getBoolean("staging_service_overriding", false))
    {
      bool1 = bool2;
      if (PreferenceHelper.getPreferences().getString("staging_service_rps_token", null) != null) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isXiaomiStoreBuild(Context paramContext)
  {
    return PackageUtil.getAppVersionName(paramContext).contains("xiaomi");
  }
  
  public static boolean packageIsInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (((PackageInfo)paramContext.next()).packageName.equalsIgnoreCase(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static <T> T safeTypecast(Object paramObject, Class<T> paramClass)
  {
    try
    {
      paramObject = paramClass.cast(paramObject);
      return paramObject;
    }
    catch (ClassCastException paramObject) {}
    return null;
  }
  
  public static void setAppContext(Context paramContext)
  {
    _appContext = paramContext;
  }
  
  public static void setSharedPreferences(String paramString1, String paramString2)
  {
    if (isNullOrwhitespaces(paramString1)) {
      return;
    }
    SharedPreferences.Editor localEditor = PreferenceHelper.getPreferences().edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static Dialog showAlertDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    return showDialogSafe(paramActivity, new AlertDialog.Builder(paramActivity).setTitle(paramString1).setMessage(paramString2).setCancelable(false).setPositiveButton(paramString3, new BaseUtils.2(paramBoolean, paramActivity)).setIcon(17301543).create());
  }
  
  public static Dialog showDialogSafe(Activity paramActivity, Dialog paramDialog)
  {
    if (paramActivity.isFinishing()) {
      return null;
    }
    try
    {
      paramDialog.show();
      return paramDialog;
    }
    catch (WindowManager.BadTokenException paramActivity) {}
    return null;
  }
  
  public static Dialog showProgressDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean1, boolean paramBoolean2, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    return showDialogSafe(paramActivity, new ProgressBarDialog(paramActivity, paramString1, paramString2, paramString3, paramString4, paramBoolean1, paramBoolean2, paramOnClickListener1, paramOnClickListener2));
  }
  
  public static Dialog showPromptDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, View.OnClickListener paramOnClickListener)
  {
    return showPromptDialog(paramActivity, paramString1, paramString2, paramString3, paramString4, null, paramOnClickListener);
  }
  
  public static Dialog showPromptDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    return showDialogSafe(paramActivity, new CustomPromptDialog(paramActivity, paramString1, paramString2, paramString4, paramString3, paramOnClickListener1, paramOnClickListener2));
  }
  
  public static int time33(String paramString)
  {
    if (paramString == null) {
      return -1;
    }
    int j = 5381;
    int i = 0;
    int k = paramString.length();
    while (i < k)
    {
      j += paramString.charAt(i) + (j << 5);
      i += 1;
    }
    return 0x7FFFFFFF & j;
  }
  
  public static SpannableString underlineString(String paramString)
  {
    paramString = new SpannableString(paramString);
    paramString.setSpan(new UnderlineSpan(), 0, paramString.length(), 0);
    return paramString;
  }
  
  public static void verifyDeveloper()
  {
    String str = getUserANIDMD5();
    if (isNullOrwhitespaces(str)) {}
    while (getDeveloperState() != 0) {
      return;
    }
    str = String.format("https://cortanaserviceea.cloudapp.net/api/diagnostics/alias?userid=%s&cox=CoA", new Object[] { str });
    HttpUtil.executeHttpRequest(new HttpRequest(HttpUtil.HttpMethod.GET, str), new BaseUtils.3());
  }
}
