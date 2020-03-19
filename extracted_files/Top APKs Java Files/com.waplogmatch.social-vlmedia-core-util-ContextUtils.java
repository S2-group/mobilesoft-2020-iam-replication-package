package vlmedia.core.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import vlmedia.core.app.VLCoreApplication;

public class ContextUtils
{
  private static int sNavigationBarHeight = Integer.MIN_VALUE;
  private static int sStatusBarHeight = Integer.MIN_VALUE;
  
  public ContextUtils() {}
  
  @ColorInt
  public static int getColorByAttr(Context paramContext, @AttrRes int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(new int[] { paramInt });
    try
    {
      paramInt = paramContext.getColor(0, -16777216);
      return paramInt;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  public static Drawable getDrawableByAttr(Context paramContext, @AttrRes int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(new int[] { paramInt });
    try
    {
      Drawable localDrawable = paramContext.getDrawable(0);
      return localDrawable;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  public static String getGoogleAdvertisingId(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext).getId();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static JSONArray getInstalledAppList(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (((localApplicationInfo.flags & 0x80) == 0) && ((localApplicationInfo.flags & 0x1) == 0)) {
        localJSONArray.put(localApplicationInfo.packageName);
      }
    }
    return localJSONArray;
  }
  
  public static int getNavigationBarHeight(Context paramContext)
  {
    if (!hasNavigationBar(paramContext)) {
      return 0;
    }
    int i;
    if (sNavigationBarHeight == Integer.MIN_VALUE)
    {
      paramContext = paramContext.getResources();
      i = paramContext.getIdentifier("navigation_bar_height", "dimen", "android");
      if (i <= 0) {
        break label49;
      }
    }
    label49:
    for (sNavigationBarHeight = paramContext.getDimensionPixelSize(i);; sNavigationBarHeight = 0) {
      return sNavigationBarHeight;
    }
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    if (sStatusBarHeight == Integer.MIN_VALUE)
    {
      sStatusBarHeight = 0;
      if (Build.VERSION.SDK_INT >= 19)
      {
        int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (i > 0) {
          sStatusBarHeight = paramContext.getResources().getDimensionPixelSize(i);
        }
      }
    }
    return sStatusBarHeight;
  }
  
  public static String getStringFromMetadata(String paramString)
  {
    try
    {
      paramString = VLCoreApplication.getInstance().getPackageManager().getApplicationInfo(VLCoreApplication.getInstance().getPackageName(), 128).metaData.getString(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("Metadata", "Failed to load meta-data " + paramString.getMessage());
    }
    return null;
  }
  
  public static int getVersionCode()
  {
    try
    {
      int i = VLCoreApplication.getInstance().getPackageManager().getPackageInfo(VLCoreApplication.getInstance().getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
    return 0;
  }
  
  public static boolean hasNavigationBar(Context paramContext)
  {
    return ((Build.VERSION.SDK_INT < 14) || (!ViewConfiguration.get(paramContext).hasPermanentMenuKey())) && (!KeyCharacterMap.deviceHasKey(4));
  }
  
  public static void hideKeyboard(Activity paramActivity)
  {
    if (paramActivity == null) {}
    View localView;
    do
    {
      return;
      localView = paramActivity.getCurrentFocus();
    } while (localView == null);
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 2);
  }
  
  public static View inflateLayoutWithFallback(Activity paramActivity, @LayoutRes int paramInt, ViewGroup paramViewGroup, boolean paramBoolean)
  {
    try
    {
      paramActivity = LayoutInflater.from(paramActivity).inflate(paramInt, paramViewGroup, paramBoolean);
      return paramActivity;
    }
    catch (NullPointerException paramActivity)
    {
      paramViewGroup = LayoutInflater.from(VLCoreApplication.getInstance()).inflate(paramInt, paramViewGroup, paramBoolean);
      Crashlytics.logException(paramActivity);
    }
    return paramViewGroup;
  }
  
  public static boolean isPackageInstalled(String paramString)
  {
    PackageManager localPackageManager = VLCoreApplication.getInstance().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static void openApp(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (isPackageInstalled(paramString1))
      {
        paramString1 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString1);
        if (paramString1 != null) {
          paramContext.startActivity(paramString1);
        }
      }
      else
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1 + "&referrer=campaignid%3D" + paramString2)));
        return;
      }
    }
    catch (Exception paramContext)
    {
      Crashlytics.logException(paramContext);
    }
  }
  
  public static void openAppSettings(Context paramContext)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.fromParts("package", paramContext.getPackageName(), null));
    paramContext.startActivity(localIntent);
  }
  
  public static void showToast(Context paramContext, int paramInt)
  {
    if (paramContext != null)
    {
      paramContext = Toast.makeText(paramContext, paramInt, 0);
      paramContext.setGravity(17, 0, 0);
      paramContext.show();
    }
  }
  
  public static void showToast(Context paramContext, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (paramContext != null)
      {
        paramContext = Toast.makeText(paramContext, paramInt, 1);
        paramContext.setGravity(17, 0, 0);
        paramContext.show();
      }
      return;
    }
    showToast(paramContext, paramInt);
  }
  
  public static void showToast(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString)) && (!"null".equals(paramString)))
    {
      paramContext = Toast.makeText(paramContext, paramString, 0);
      paramContext.setGravity(17, 0, 0);
      paramContext.show();
    }
  }
  
  public static void showToast(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if ((paramContext != null) && (!TextUtils.isEmpty(paramString)) && (!"null".equals(paramString)))
      {
        paramContext = Toast.makeText(paramContext, paramString, 1);
        paramContext.setGravity(17, 0, 0);
        paramContext.show();
      }
      return;
    }
    showToast(paramContext, paramString);
  }
}
