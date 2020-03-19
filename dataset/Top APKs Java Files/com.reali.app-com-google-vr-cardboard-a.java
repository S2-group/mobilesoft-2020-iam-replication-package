package com.google.vr.cardboard;

import android.app.Activity;
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
import java.util.Iterator;
import java.util.List;

public class a
{
  private static final String a = "a";
  private static int b;
  
  private a() {}
  
  private static int a(Context paramContext)
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
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, j.d.GvrDialogTheme);
    paramContext.setMessage(paramInt1).setTitle(j.c.dialog_title_warning).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(j.c.cancel_button, new DialogInterface.OnClickListener()
    {
      public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramContext.create().show();
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    return a(paramActivity, paramBoolean, 1);
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    boolean bool = b();
    int j = 1;
    int i;
    if ((bool) && (paramActivity.getPackageManager().hasSystemFeature("android.software.vr.mode"))) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      if (b()) {
        Log.d(a, "VR mode is not supported on this N device.");
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
      paramActivity = a;
      str1 = String.valueOf(localUnsupportedOperationException);
      StringBuilder localStringBuilder1 = new StringBuilder(23 + String.valueOf(str1).length());
      localStringBuilder1.append("Failed to set VR mode: ");
      localStringBuilder1.append(str1);
      Log.w(paramActivity, localStringBuilder1.toString());
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str1 = a;
      String str2 = String.valueOf(localNameNotFoundException);
      StringBuilder localStringBuilder2 = new StringBuilder(25 + String.valueOf(str2).length());
      localStringBuilder2.append("No VR service component: ");
      localStringBuilder2.append(str2);
      Log.w(str1, localStringBuilder2.toString());
      if ((paramInt & 0x1) != 0)
      {
        i = a(paramActivity);
        if (i == -1) {
          a(paramActivity, j.c.dialog_vr_core_not_installed, j.c.go_to_playstore_button, new DialogInterface.OnClickListener()
          {
            public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
              paramAnonymousDialogInterface.setData(Uri.parse("market://details?id=com.google.vr.vrcore"));
              paramAnonymousDialogInterface.setPackage("com.android.vending");
              try
              {
                a.this.startActivity(paramAnonymousDialogInterface);
                return;
              }
              catch (ActivityNotFoundException paramAnonymousDialogInterface)
              {
                for (;;) {}
              }
              Log.e(a.a(), "Google Play Services is not installed, unable to download VrCore.");
            }
          });
        }
        for (;;)
        {
          paramInt = 0;
          break;
          paramInt = j;
          if (i != -2) {
            break;
          }
          a(paramActivity, j.c.dialog_vr_core_not_enabled, j.c.go_to_vr_listeners_settings_button, new DialogInterface.OnClickListener()
          {
            public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              a.this.startActivity(new Intent("android.settings.VR_LISTENER_SETTINGS"));
            }
          });
        }
        if (paramInt != 0) {
          Log.w(a, "Failed to handle missing VrCore package.");
        }
      }
    }
    return false;
  }
  
  private static boolean b()
  {
    return (b >= 24) || (Build.VERSION.SDK_INT >= 24);
  }
  
  public static boolean b(Activity paramActivity, boolean paramBoolean)
  {
    if (!b()) {
      return false;
    }
    if (!((PowerManager)paramActivity.getSystemService("power")).isSustainedPerformanceModeSupported())
    {
      Log.d(a, "Sustained performance mode is not supported on this device.");
      return false;
    }
    paramActivity = paramActivity.getWindow();
    if (paramActivity == null)
    {
      Log.e(a, "Activity does not have a window");
      return false;
    }
    paramActivity.setSustainedPerformanceMode(paramBoolean);
    return true;
  }
}
