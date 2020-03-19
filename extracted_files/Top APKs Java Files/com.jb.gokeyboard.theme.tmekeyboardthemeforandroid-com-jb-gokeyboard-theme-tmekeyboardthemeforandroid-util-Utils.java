package com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.gson.Gson;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.MainActivity;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.response.Experiment;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.response.PromotedThemesResponse;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.response.ServerResponse;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.batteryboost.SmartBatteryBoostReceiver;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.batteryboost.SmartBatteryBoostService101;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
  public static int ENABLE_GO_REQUEST = 721;
  public static int GO_KEYBOARD_BACKGROUND = 11;
  public static int GO_KEYBOARD_FONTS;
  public static int GO_KEYBOARD_SOUNDS;
  public static int GO_REQUEST_BACKGROUND;
  public static int GO_REQUEST_FONT = 106;
  public static int GO_REQUEST_SOUND = 108;
  
  static
  {
    GO_REQUEST_BACKGROUND = 111;
    GO_KEYBOARD_FONTS = 6;
    GO_KEYBOARD_SOUNDS = 8;
  }
  
  public Utils() {}
  
  public static void disableBatteryBooster(Context paramContext)
  {
    Object localObject = new ComponentName(paramContext, SmartBatteryBoostReceiver.class);
    paramContext.getPackageManager().setComponentEnabledSetting((ComponentName)localObject, 2, 1);
    localObject = new Intent();
    ((Intent)localObject).setPackage(paramContext.getPackageName());
    ((Intent)localObject).setAction("com.jb.gokeyboard.theme.disable_service");
    paramContext.sendBroadcast((Intent)localObject);
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    float f = paramInt;
    return Math.round(paramContext.xdpi / 160.0F * f);
  }
  
  public static void enableBatteryBooster(Context paramContext)
  {
    if (!isBatteryBoosterRunning(SmartBatteryBoostService101.class.getSimpleName(), paramContext))
    {
      ComponentName localComponentName = new ComponentName(paramContext, SmartBatteryBoostReceiver.class);
      paramContext.getPackageManager().setComponentEnabledSetting(localComponentName, 1, 1);
    }
  }
  
  public static List<Integer> generateRandomPositionsAds(int paramInt1, int paramInt2, int paramInt3)
  {
    ArrayList localArrayList = new ArrayList();
    Random localRandom = new Random();
    paramInt2 += paramInt3;
    int k = paramInt2 / paramInt3;
    paramInt2 -= paramInt2 - k;
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    while (paramInt1 < paramInt3)
    {
      localArrayList.add(Integer.valueOf(localRandom.nextInt(paramInt2 - i) + i));
      i = paramInt2 + 1;
      paramInt2 += k;
      paramInt1 += 1;
    }
    return localArrayList;
  }
  
  public static String getDeviceInfo(Activity paramActivity)
  {
    JSONArray localJSONArray = new JSONArray();
    JSONObject localJSONObject = new JSONObject();
    paramActivity = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
    while (paramActivity.hasNext()) {
      localJSONArray.put(((ApplicationInfo)paramActivity.next()).packageName);
    }
    try
    {
      localJSONObject.put("apps", localJSONArray);
      localJSONObject.put("t", getTotalMemory());
      localJSONObject.put("f", getFreeMemory());
      return localJSONObject.toString();
    }
    catch (JSONException paramActivity)
    {
      for (;;)
      {
        paramActivity.printStackTrace();
      }
    }
  }
  
  public static long getFreeMemory()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    if (Build.VERSION.SDK_INT >= 18) {
      return localStatFs.getAvailableBytes() / 1048576L;
    }
    long l = localStatFs.getBlockSizeLong();
    return localStatFs.getAvailableBlocksLong() * l / 1048576L;
  }
  
  public static long getTotalMemory()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    if (Build.VERSION.SDK_INT >= 18) {
      return localStatFs.getTotalBytes() / 1048576L;
    }
    long l = localStatFs.getBlockSizeLong();
    return localStatFs.getBlockCountLong() * l / 1048576L;
  }
  
  public static boolean haveNetworkConnection(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getAllNetworkInfo();
    int i1 = paramContext.length;
    int i = 0;
    int j = 0;
    int m = 0;
    while (i < i1)
    {
      Object localObject = paramContext[i];
      int k = m;
      if (localObject.getTypeName().equalsIgnoreCase("WIFI"))
      {
        k = m;
        if (localObject.isConnectedOrConnecting()) {
          k = 1;
        }
      }
      int n = j;
      if (localObject.getTypeName().equalsIgnoreCase("MOBILE"))
      {
        n = j;
        if (localObject.isConnectedOrConnecting()) {
          n = 1;
        }
      }
      i += 1;
      m = k;
      j = n;
    }
    return (m != 0) || (j != 0);
  }
  
  public static void hideSoftKeyboard(Activity paramActivity, View paramView)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  private static List<String> installedApps(Activity paramActivity)
  {
    ArrayList localArrayList = new ArrayList();
    paramActivity = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
    while (paramActivity.hasNext()) {
      localArrayList.add(((ApplicationInfo)paramActivity.next()).packageName);
    }
    return localArrayList;
  }
  
  public static boolean isBatteryBoosterRunning(String paramString, Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext()) {
      if (((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName().contains(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isFacebookInstalled(MainActivity paramMainActivity)
  {
    paramMainActivity = installedApps(paramMainActivity).iterator();
    while (paramMainActivity.hasNext())
    {
      String str = (String)paramMainActivity.next();
      if ((str.equals("com.facebook.katana")) || (str.equals("com.facebook.lite"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isPromotedAppInstalled(String paramString, Activity paramActivity)
  {
    paramActivity = installedApps(paramActivity).iterator();
    while (paramActivity.hasNext()) {
      if (((String)paramActivity.next()).equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static void moreApps(Activity paramActivity)
  {
    Object localObject = paramActivity.getResources().getString(2131296425);
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://developer?id=" + (String)localObject));
      localIntent.setPackage("com.android.vending");
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      localObject = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + (String)localObject));
      ((Intent)localObject).setPackage("com.android.vending");
      paramActivity.startActivity((Intent)localObject);
    }
  }
  
  public static List<PromotedThemesResponse> newPrt(MainActivity paramMainActivity, List<PromotedThemesResponse> paramList, Experiment paramExperiment)
  {
    ArrayList localArrayList = new ArrayList();
    if (!paramMainActivity.getServerResponse().isCrossPromo)
    {
      paramExperiment = paramList.iterator();
      for (;;)
      {
        paramMainActivity = paramList;
        if (!paramExperiment.hasNext()) {
          break;
        }
        if (((PromotedThemesResponse)paramExperiment.next()).package_name != null) {
          paramExperiment.remove();
        }
      }
    }
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      PromotedThemesResponse localPromotedThemesResponse = (PromotedThemesResponse)localIterator.next();
      if (localPromotedThemesResponse.package_name == null)
      {
        localArrayList.add(localPromotedThemesResponse);
      }
      else
      {
        Matcher localMatcher = Pattern.compile("([^&]+)").matcher(localPromotedThemesResponse.package_name);
        paramList = "";
        if (localMatcher.find()) {
          paramList = localMatcher.group(1);
        }
        if ((paramExperiment != null) && (localPromotedThemesResponse.package_name.equals(paramExperiment.getAttribute("package_name"))))
        {
          Log.d("Experiments", "Changed cover with experiment value");
          localPromotedThemesResponse.preview_image = paramExperiment.value;
        }
        if (!isPromotedAppInstalled(paramList, paramMainActivity)) {
          localArrayList.add(localPromotedThemesResponse);
        }
      }
    }
    paramMainActivity = localArrayList;
    return paramMainActivity;
  }
  
  public static void openGoKeyboard(Activity paramActivity, int paramInt1, int paramInt2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClassName("com.jb.emoji.gokeyboard", "com.jb.gokeyboard.gostore.LocalAppDetailActivity");
      localIntent.putExtra("shoptype", paramInt1);
      localIntent.putExtra("from_theme", true);
      localIntent.putExtra("packageName", paramActivity.getBaseContext().getPackageName());
      paramActivity.startActivityForResult(localIntent, paramInt2);
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void openGooglePlay(Activity paramActivity, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage("com.android.vending");
    paramActivity.startActivity(paramString);
  }
  
  public static void openStore(String paramString, Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
      localIntent.setPackage("com.android.vending");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString));
      paramString.setPackage("com.android.vending");
      paramContext.startActivity(paramString);
    }
  }
  
  public static void rateUs(Activity paramActivity)
  {
    Object localObject = paramActivity.getPackageName();
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + (String)localObject));
      localIntent.setPackage("com.android.vending");
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      localObject = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + (String)localObject));
      ((Intent)localObject).setPackage("com.android.vending");
      paramActivity.startActivity((Intent)localObject);
    }
  }
  
  public static ServerResponse readServerResponseFromSharedPref(Context paramContext)
  {
    Object localObject = null;
    String str = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("settings_json", null);
    paramContext = localObject;
    if (str != null) {
      paramContext = (ServerResponse)new Gson().fromJson(str, ServerResponse.class);
    }
    return paramContext;
  }
  
  public static void showSoftKeyboard(Activity paramActivity, View paramView)
  {
    if (paramView.requestFocus()) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(paramView, 1);
    }
  }
}
