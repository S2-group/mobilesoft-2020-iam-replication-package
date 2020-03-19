package com.google.vr.cardboard;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Window;
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
  public static final int NMR1_SDK_LEVEL = 25;
  public static final int N_SDK_LEVEL = 24;
  private static final int PACKAGE_NOT_ENABLED = -2;
  private static final int PACKAGE_NOT_PRESENT = -1;
  private static final int SUCCESS = 0;
  private static final String TAG = "AndroidNCompat";
  private static int sSdkLevelOverride;
  
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
    if (paramInt == -1)
    {
      showWarningDialog(paramContext, R.string.dialog_vr_core_not_installed, R.string.go_to_playstore_button, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
          paramAnonymousDialogInterface.setData(Uri.parse("market://details?id=com.google.vr.vrcore"));
          paramAnonymousDialogInterface.setPackage("com.android.vending");
          try
          {
            AndroidNCompat.this.startActivity(paramAnonymousDialogInterface);
            return;
          }
          catch (ActivityNotFoundException paramAnonymousDialogInterface)
          {
            for (;;) {}
          }
          Log.e(AndroidNCompat.TAG, "Google Play Services is not installed, unable to download VrCore.");
        }
      });
      return false;
    }
    if (paramInt == -2)
    {
      showWarningDialog(paramContext, R.string.dialog_vr_core_not_enabled, R.string.go_to_vr_listeners_settings_button, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          AndroidNCompat.this.startActivity(new Intent("android.settings.VR_LISTENER_SETTINGS"));
        }
      });
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
  
  public static boolean isVrModeSupported(Context paramContext)
  {
    return (isAtLeastN()) && (paramContext.getPackageManager().hasSystemFeature("android.software.vr.mode"));
  }
  
  public static void setSdkLevelForTesting(int paramInt)
  {
    sSdkLevelOverride = paramInt;
  }
  
  public static boolean setSustainedPerformanceMode(Activity paramActivity, boolean paramBoolean)
  {
    if (!isAtLeastN()) {
      return false;
    }
    if (!((PowerManager)paramActivity.getSystemService("power")).isSustainedPerformanceModeSupported())
    {
      Log.d(TAG, "Sustained performance mode is not supported on this device.");
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
      if (isAtLeastN()) {
        Log.d(TAG, "VR mode is not supported on this N device.");
      }
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
      StringBuilder localStringBuilder1 = new StringBuilder(23 + String.valueOf(str1).length());
      localStringBuilder1.append("Failed to set VR mode: ");
      localStringBuilder1.append(str1);
      Log.w(paramActivity, localStringBuilder1.toString());
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str1 = TAG;
      String str2 = String.valueOf(localNameNotFoundException);
      StringBuilder localStringBuilder2 = new StringBuilder(25 + String.valueOf(str2).length());
      localStringBuilder2.append("No VR service component: ");
      localStringBuilder2.append(str2);
      Log.w(str1, localStringBuilder2.toString());
      if (((paramInt & 0x1) != 0) && (handleVrCoreAbsence(paramActivity, checkForVrCorePresence(paramActivity)))) {
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
      Object localObject = ActivityManager.class.getMethod("setVrThread", new Class[] { Integer.TYPE });
      StringBuilder localStringBuilder;
      try
      {
        ((Method)localObject).invoke(null, new Object[] { Integer.valueOf(paramInt) });
        return;
      }
      catch (InvocationTargetException|IllegalAccessException|RuntimeException localInvocationTargetException)
      {
        localObject = TAG;
        String str1 = String.valueOf(localInvocationTargetException);
        localStringBuilder = new StringBuilder(30 + String.valueOf(str1).length());
        localStringBuilder.append("Failed to invoke setVrThread: ");
        localStringBuilder.append(str1);
        Log.e((String)localObject, localStringBuilder.toString());
        return;
      }
      String str2;
      return;
    }
    catch (NoSuchMethodException|RuntimeException localNoSuchMethodException)
    {
      if (isAtLeastNMR1())
      {
        localObject = TAG;
        str2 = String.valueOf(localNoSuchMethodException);
        localStringBuilder = new StringBuilder(38 + String.valueOf(str2).length());
        localStringBuilder.append("Failed to acquire setVrThread method: ");
        localStringBuilder.append(str2);
        Log.e((String)localObject, localStringBuilder.toString());
        return;
      }
      localObject = TAG;
      str2 = String.valueOf(str2);
      localStringBuilder = new StringBuilder(38 + String.valueOf(str2).length());
      localStringBuilder.append("Failed to acquire setVrThread method: ");
      localStringBuilder.append(str2);
      Log.w((String)localObject, localStringBuilder.toString());
    }
  }
  
  private static void showWarningDialog(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, R.style.GvrDialogTheme);
    paramContext.setMessage(paramInt1).setTitle(R.string.dialog_title_warning).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramContext.create().show();
  }
}
