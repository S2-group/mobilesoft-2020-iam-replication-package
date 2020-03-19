package com.google.vr.cardboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class AndroidNCompat
{
  private static final String ACTION_VR_LISTENER_SETTINGS = "android.settings.VR_LISTENER_SETTINGS";
  private static final boolean DEBUG = false;
  private static final String DEFAULT_VR_MODE_CLASS = "com.google.vr.vrcore.common.VrCoreListenerService";
  private static final String DEFAULT_VR_MODE_PACKAGE = "com.google.vr.vrcore";
  private static final String ENABLED_VR_LISTENERS = "enabled_vr_listeners";
  public static final int FLAG_VR_MODE_ENABLE_FALLBACK = 1;
  private static final int PACKAGE_NOT_ENABLED = -2;
  private static final int PACKAGE_NOT_PRESENT = -1;
  private static final int SUCCESS = 0;
  private static final String TAG = "AndroidNCompat";
  private static boolean sIsAtLeastNOverride = false;
  private static boolean sIsVrReadyOverride = false;
  
  private AndroidNCompat() {}
  
  private static int checkForVrCorePresence(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.google.vr.vrcore"))
      {
        i = 1;
        break label50;
      }
    }
    int i = 0;
    label50:
    if (i == 0) {
      return -1;
    }
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_vr_listeners");
    localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
    if ((paramContext != null) && (paramContext.contains(((ComponentName)localObject).flattenToString()))) {
      return 0;
    }
    return -2;
  }
  
  private static boolean handleVrCoreAbsence(Context paramContext, int paramInt)
  {
    int i;
    if (paramInt == -1)
    {
      i = R.string.dialog_vr_core_not_installed;
      paramInt = R.string.go_to_playstore_button;
    }
    for (Object localObject = new AndroidNCompat.1(paramContext);; localObject = new AndroidNCompat.2(paramContext))
    {
      showWarningDialog(paramContext, i, paramInt, (DialogInterface.OnClickListener)localObject);
      return false;
      if (paramInt != -2) {
        break;
      }
      i = R.string.dialog_vr_core_not_enabled;
      paramInt = R.string.go_to_vr_listeners_settings_button;
    }
    return true;
  }
  
  private static boolean isAtLeastN()
  {
    return (sIsAtLeastNOverride) || ("N".equals(Build.VERSION.CODENAME)) || (Build.VERSION.SDK_INT > 23);
  }
  
  public static boolean isVrReady(Context paramContext)
  {
    if (!isAtLeastN()) {
      return false;
    }
    if (sIsVrReadyOverride) {
      return true;
    }
    if ((Build.MANUFACTURER.equals("Huawei")) && (Build.DEVICE.equals("angler")) && (Build.MODEL.equals("Nexus 6P")))
    {
      if (!Build.HARDWARE.equals("angler")) {
        return false;
      }
      try
      {
        Object localObject = PackageManager.class.getDeclaredField("FEATURE_VR_MODE_HIGH_PERFORMANCE");
        StringBuilder localStringBuilder;
        try
        {
          localObject = (String)((Field)localObject).get(null);
          boolean bool = paramContext.getPackageManager().hasSystemFeature((String)localObject);
          return bool;
        }
        catch (IllegalAccessException|IllegalArgumentException|ExceptionInInitializerError localIllegalAccessException)
        {
          paramContext = TAG;
          String str1 = String.valueOf(localIllegalAccessException);
          localStringBuilder = new StringBuilder(32 + String.valueOf(str1).length());
          localStringBuilder.append("Failed to check system feature: ");
          localStringBuilder.append(str1);
          Log.w(paramContext, localStringBuilder.toString());
          return false;
        }
        String str2;
        return false;
      }
      catch (NoSuchFieldException|SecurityException localNoSuchFieldException)
      {
        paramContext = TAG;
        str2 = String.valueOf(localNoSuchFieldException);
        localStringBuilder = new StringBuilder(55 + String.valueOf(str2).length());
        localStringBuilder.append("Failed to load FEATURE_VR_MODE_HIGH_PERFORMANCE field: ");
        localStringBuilder.append(str2);
        Log.e(paramContext, localStringBuilder.toString());
      }
    }
  }
  
  public static void setIsAtLeastNForTesting(boolean paramBoolean)
  {
    sIsAtLeastNOverride = paramBoolean;
  }
  
  public static void setIsVrReadyForTesting(boolean paramBoolean)
  {
    sIsVrReadyOverride = paramBoolean;
  }
  
  public static void setSustainedPerformanceMode(Activity paramActivity, boolean paramBoolean)
  {
    if (!isAtLeastN()) {
      return;
    }
    paramActivity = paramActivity.getWindow();
    if (paramActivity == null)
    {
      Log.e(TAG, "Activity does not have a window");
      return;
    }
    try
    {
      Method localMethod = Window.class.getMethod("setSustainedPerformanceMode", new Class[] { Boolean.TYPE });
      Object localObject;
      try
      {
        localMethod.invoke(paramActivity, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (IllegalAccessException|RuntimeException localIllegalAccessException)
      {
        paramActivity = TAG;
        String str1 = String.valueOf(localIllegalAccessException);
        localObject = new StringBuilder(42 + String.valueOf(str1).length());
        ((StringBuilder)localObject).append("Failed to set sustained performance mode: ");
        ((StringBuilder)localObject).append(str1);
        Log.w(paramActivity, ((StringBuilder)localObject).toString());
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localObject = TAG;
        paramActivity = localInvocationTargetException;
        if (localInvocationTargetException.getCause() != null) {
          paramActivity = localInvocationTargetException.getCause();
        }
        paramActivity = String.valueOf(paramActivity);
        StringBuilder localStringBuilder = new StringBuilder(42 + String.valueOf(paramActivity).length());
        localStringBuilder.append("Failed to set sustained performance mode: ");
        localStringBuilder.append(paramActivity);
        Log.w((String)localObject, localStringBuilder.toString());
        return;
      }
      String str2;
      return;
    }
    catch (NoSuchMethodException|RuntimeException localNoSuchMethodException)
    {
      paramActivity = TAG;
      str2 = String.valueOf(localNoSuchMethodException);
      localObject = new StringBuilder(58 + String.valueOf(str2).length());
      ((StringBuilder)localObject).append("Failed to load Window.setSustainedPerformanceMode method: ");
      ((StringBuilder)localObject).append(str2);
      Log.e(paramActivity, ((StringBuilder)localObject).toString());
    }
  }
  
  public static boolean setVrModeEnabled(Activity paramActivity, boolean paramBoolean)
  {
    return setVrModeEnabled(paramActivity, paramBoolean, 1);
  }
  
  public static boolean setVrModeEnabled(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    if (!isAtLeastN()) {
      return false;
    }
    try
    {
      Method localMethod = Activity.class.getMethod("setVrModeEnabled", new Class[] { Boolean.TYPE, ComponentName.class });
      Object localObject2;
      try
      {
        localMethod.invoke(paramActivity, new Object[] { Boolean.valueOf(paramBoolean), new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService") });
        return true;
      }
      catch (IllegalAccessException|RuntimeException localIllegalAccessException)
      {
        paramActivity = TAG;
        localObject1 = String.valueOf(localIllegalAccessException);
        StringBuilder localStringBuilder1 = new StringBuilder(23 + String.valueOf(localObject1).length());
        localStringBuilder1.append("Failed to set VR mode: ");
        localStringBuilder1.append((String)localObject1);
        Log.w(paramActivity, localStringBuilder1.toString());
        return false;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Object localObject1;
        if ((localInvocationTargetException.getCause() instanceof PackageManager.NameNotFoundException))
        {
          localObject1 = TAG;
          localObject2 = String.valueOf(localInvocationTargetException.getCause());
          StringBuilder localStringBuilder2 = new StringBuilder(25 + String.valueOf(localObject2).length());
          localStringBuilder2.append("No VR service component: ");
          localStringBuilder2.append((String)localObject2);
          Log.w((String)localObject1, localStringBuilder2.toString());
          if (((paramInt & 0x1) != 0) && (handleVrCoreAbsence(paramActivity, checkForVrCorePresence(paramActivity)))) {
            paramActivity = TAG;
          }
        }
        else
        {
          for (localObject1 = "Failed to handle missing VrCore package.";; localObject1 = localObject2)
          {
            Log.w(paramActivity, (String)localObject1);
            return false;
            localObject1 = TAG;
            paramActivity = (Activity)localObject2;
            if (((InvocationTargetException)localObject2).getCause() != null) {
              paramActivity = ((InvocationTargetException)localObject2).getCause();
            }
            paramActivity = String.valueOf(paramActivity);
            localObject2 = new StringBuilder(23 + String.valueOf(paramActivity).length());
            ((StringBuilder)localObject2).append("Failed to set VR mode: ");
            ((StringBuilder)localObject2).append(paramActivity);
            localObject2 = ((StringBuilder)localObject2).toString();
            paramActivity = (Activity)localObject1;
          }
        }
        return false;
      }
      String str;
      return false;
    }
    catch (NoSuchMethodException|RuntimeException localNoSuchMethodException)
    {
      paramActivity = TAG;
      str = String.valueOf(localNoSuchMethodException);
      localObject2 = new StringBuilder(49 + String.valueOf(str).length());
      ((StringBuilder)localObject2).append("Failed to load Activity.setVrModeEnabled method: ");
      ((StringBuilder)localObject2).append(str);
      Log.e(paramActivity, ((StringBuilder)localObject2).toString());
    }
  }
  
  private static void showWarningDialog(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(paramInt1).setTitle(R.string.dialog_title_warning).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(R.string.cancel_button, new AndroidNCompat.3());
    paramContext.create().show();
  }
}
