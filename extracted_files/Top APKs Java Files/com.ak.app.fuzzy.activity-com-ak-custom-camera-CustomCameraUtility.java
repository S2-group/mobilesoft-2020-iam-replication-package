package com.ak.custom.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CustomCameraUtility
{
  public CustomCameraUtility() {}
  
  public static String getAppAlbumPath()
  {
    return new File(Environment.getExternalStorageDirectory().toString() + "/CustomCameraTag/").getAbsolutePath();
  }
  
  public static final Uri getContentUri(String paramString)
  {
    return Uri.parse("content://" + paramString);
  }
  
  public static int getDeviceSize(Context paramContext)
  {
    int i = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getHeight();
    if (i >= 840) {
      return CustomCameraConstants.DEVICE_XHDPI;
    }
    if ((i > 480) && (i <= 840)) {
      return CustomCameraConstants.DEVICE_HDPI;
    }
    if ((i > 300) && (i <= 480)) {
      return CustomCameraConstants.DEVICE__MDPI;
    }
    return CustomCameraConstants.DEVICE_HDPI;
  }
  
  public static String getDirPathFromFilePath(String paramString)
  {
    return paramString.substring(0, paramString.lastIndexOf("/"));
  }
  
  public static String getFileNameFromPath(String paramString)
  {
    return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.length());
  }
  
  public static String getFolderNameFromFilePath(String paramString)
  {
    paramString = paramString.substring(0, paramString.lastIndexOf("/"));
    return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.length());
  }
  
  public static boolean getSharedPrefBooleanData(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("custom_camera_app_data", 0).getBoolean(paramString, false);
  }
  
  public static float getSharedPrefFloatData(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("custom_camera_app_data", 0).getFloat(paramString, 0.0F);
  }
  
  public static boolean getSharedPrefGeoTagBooleanData(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("custom_camera_app_data", 0).getBoolean(paramString, true);
  }
  
  public static int getSharedPrefIntData(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("custom_camera_app_data", 0).getInt(paramString, 0);
  }
  
  public static long getSharedPrefLongData(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("custom_camera_app_data", 0).getLong(paramString, 0L);
  }
  
  public static String getSharedPrefStringData(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("custom_camera_app_data", 0).getString(paramString, "");
  }
  
  public static String getUniqueSDCardId()
  {
    return String.valueOf(UUID.randomUUID());
  }
  
  public static void hideKeyboard(Context paramContext, EditText paramEditText)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    boolean bool = false;
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if ((paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) || (paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING)) {
      bool = true;
    }
    while ((paramContext.getNetworkInfo(0).getState() != NetworkInfo.State.CONNECTED) && (paramContext.getNetworkInfo(0).getState() != NetworkInfo.State.CONNECTING)) {
      return bool;
    }
    return true;
  }
  
  public static boolean isNumeric(String paramString)
  {
    return paramString.matches("-?\\d+(\\.\\d+)?");
  }
  
  public static String isPackageExists(String paramString1, String paramString2, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localApplicationInfo = (ApplicationInfo)paramContext.next();
    } while ((!localApplicationInfo.packageName.contains(paramString2)) || (!localApplicationInfo.packageName.contains(paramString1)));
    return localApplicationInfo.packageName;
  }
  
  public static boolean isSDCardPresent()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static void setSharedPreStringData(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("custom_camera_app_data", 0).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }
  
  public static void setSharedPrefBooleanData(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("custom_camera_app_data", 0).edit();
    paramContext.putBoolean(paramString, paramBoolean);
    paramContext.commit();
  }
  
  public static void setSharedPrefFloatData(Context paramContext, String paramString, float paramFloat)
  {
    paramContext = paramContext.getSharedPreferences("custom_camera_app_data", 0).edit();
    paramContext.putFloat(paramString, paramFloat);
    paramContext.commit();
  }
  
  public static void setSharedPrefIntData(Context paramContext, String paramString, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("custom_camera_app_data", 0).edit();
    paramContext.putInt(paramString, paramInt);
    paramContext.commit();
  }
  
  public static void setSharedPrefLongData(Context paramContext, String paramString, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("custom_camera_app_data", 0).edit();
    paramContext.putLong(paramString, paramLong);
    paramContext.commit();
  }
  
  public static void showKeyboard(Context paramContext)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(2, 1);
  }
}
