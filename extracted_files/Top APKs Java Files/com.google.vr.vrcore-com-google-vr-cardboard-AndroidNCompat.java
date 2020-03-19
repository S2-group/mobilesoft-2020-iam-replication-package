package com.google.vr.cardboard;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Window;
import cxl;
import cxm;
import cxn;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class AndroidNCompat
{
  public static final String TAG = AndroidNCompat.class.getSimpleName();
  public static int sSdkLevelOverride = 0;
  
  private AndroidNCompat() {}
  
  private static int checkForVrCorePresence(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.google.vr.vrcore"))
      {
        i = 1;
        break label53;
      }
    }
    int i = 0;
    label53:
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
    if (IsEmulator.isEmulator()) {
      return true;
    }
    if (paramInt == -1)
    {
      showWarningDialog(paramContext, R.string.dialog_vr_core_not_installed, R.string.go_to_playstore_button, new cxl(paramContext));
      return false;
    }
    if (paramInt == -2)
    {
      showWarningDialog(paramContext, R.string.dialog_vr_core_not_enabled, R.string.go_to_vr_listeners_settings_button, new cxm(paramContext));
      return false;
    }
    return true;
  }
  
  private static boolean isAtLeastN()
  {
    return (sSdkLevelOverride >= 24) || (Build.VERSION.SDK_INT >= 24);
  }
  
  private static boolean isAtLeastNMR1()
  {
    return (sSdkLevelOverride >= 25) || ("NMR1".equals(Build.VERSION.CODENAME)) || (Build.VERSION.SDK_INT >= 25);
  }
  
  private static boolean isVrModeHighPerformanceSupported(Context paramContext)
  {
    return (isAtLeastN()) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"));
  }
  
  public static boolean isVrModeSupported(Context paramContext)
  {
    return (isAtLeastN()) && (paramContext.getPackageManager().hasSystemFeature("android.software.vr.mode"));
  }
  
  public static boolean setSustainedPerformanceMode(Activity paramActivity, boolean paramBoolean)
  {
    if (!isAtLeastN()) {
      return false;
    }
    if (!((PowerManager)paramActivity.getSystemService("power")).isSustainedPerformanceModeSupported()) {
      return false;
    }
    paramActivity = paramActivity.getWindow();
    if (paramActivity == null)
    {
      Log.e(TAG, "Activity does not have a window");
      return false;
    }
    paramActivity.setSustainedPerformanceMode(paramBoolean);
    return true;
  }
  
  public static boolean setVrModeEnabled(Activity paramActivity, boolean paramBoolean)
  {
    return setVrModeEnabled(paramActivity, paramBoolean, 1);
  }
  
  public static boolean setVrModeEnabled(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    if (!isVrModeSupported(paramActivity))
    {
      isAtLeastN();
      return false;
    }
    ComponentName localComponentName = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
    try
    {
      paramActivity.setVrModeEnabled(paramBoolean, localComponentName);
      return true;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      paramActivity = TAG;
      str1 = String.valueOf(localUnsupportedOperationException);
      StringBuilder localStringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 23);
      localStringBuilder1.append("Failed to set VR mode: ");
      localStringBuilder1.append(str1);
      Log.w(paramActivity, localStringBuilder1.toString());
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str1 = TAG;
      String str2 = String.valueOf(localNameNotFoundException);
      StringBuilder localStringBuilder2 = new StringBuilder(String.valueOf(str2).length() + 25);
      localStringBuilder2.append("No VR service component: ");
      localStringBuilder2.append(str2);
      Log.w(str1, localStringBuilder2.toString());
      if (((paramInt & 0x1) != 0) && (isVrModeHighPerformanceSupported(paramActivity)) && (handleVrCoreAbsence(paramActivity, checkForVrCorePresence(paramActivity)))) {
        Log.w(TAG, "Failed to handle missing VrCore package.");
      }
    }
    return false;
  }
  
  public static void setVrThread(int paramInt)
  {
    if (!isAtLeastN()) {
      return;
    }
    try
    {
      Method localMethod = ActivityManager.class.getMethod("setVrThread", new Class[] { Integer.TYPE });
      try
      {
        localMethod.invoke(null, new Object[] { Integer.valueOf(paramInt) });
        return;
      }
      catch (RuntimeException localRuntimeException1) {}catch (IllegalAccessException localIllegalAccessException) {}catch (InvocationTargetException localInvocationTargetException) {}
      str3 = TAG;
      String str1 = String.valueOf(localInvocationTargetException);
      localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 30);
      localStringBuilder.append("Failed to invoke setVrThread: ");
      localStringBuilder.append(str1);
      Log.e(str3, localStringBuilder.toString());
      return;
    }
    catch (RuntimeException localRuntimeException2) {}catch (NoSuchMethodException localNoSuchMethodException) {}
    if (isAtLeastNMR1())
    {
      str3 = TAG;
      str2 = String.valueOf(localNoSuchMethodException);
      localStringBuilder = new StringBuilder(String.valueOf(str2).length() + 38);
      localStringBuilder.append("Failed to acquire setVrThread method: ");
      localStringBuilder.append(str2);
      Log.e(str3, localStringBuilder.toString());
      return;
    }
    String str3 = TAG;
    String str2 = String.valueOf(str2);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str2).length() + 38);
    localStringBuilder.append("Failed to acquire setVrThread method: ");
    localStringBuilder.append(str2);
    Log.w(str3, localStringBuilder.toString());
  }
  
  private static void showWarningDialog(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, R.style.GvrDialogTheme);
    paramContext.setMessage(paramInt1).setTitle(R.string.dialog_title_warning).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(R.string.cancel_button, new cxn());
    paramContext.create().show();
  }
}
