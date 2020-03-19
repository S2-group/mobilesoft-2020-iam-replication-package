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
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class z
{
  public static final String g = z.class.getSimpleName();
  
  private z() {}
  
  private static int D(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.google.vr.vrcore"));
    for (int i = 1;; i = 0)
    {
      if (i == 0) {
        return -1;
      }
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_vr_listeners");
      localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
      if ((paramContext == null) || (!paramContext.contains(((ComponentName)localObject).flattenToString()))) {
        return -2;
      }
      return 0;
    }
  }
  
  private static boolean f(Activity paramActivity, boolean paramBoolean)
  {
    int j = 1;
    int i;
    if ((p()) && (paramActivity.getPackageManager().hasSystemFeature("android.software.vr.mode")))
    {
      i = 1;
      if (i != 0) {
        break label37;
      }
      p();
    }
    for (;;)
    {
      return false;
      i = 0;
      break;
      label37:
      Object localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
      try
      {
        paramActivity.setVrModeEnabled(paramBoolean, (ComponentName)localObject);
        return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = g;
        String str2 = String.valueOf(localNameNotFoundException);
        Log.w((String)localObject, String.valueOf(str2).length() + 25 + "No VR service component: " + str2);
        int k = D(paramActivity);
        if (k == -1)
        {
          k(paramActivity, 2131428534, 2131428535, new Y(paramActivity));
          i = 0;
        }
        while (i != 0)
        {
          Log.w(g, "Failed to handle missing VrCore package.");
          return false;
          i = j;
          if (k == -2)
          {
            k(paramActivity, 2131428533, 2131428536, new o(paramActivity));
            i = 0;
          }
        }
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        paramActivity = g;
        String str1 = String.valueOf(localUnsupportedOperationException);
        Log.w(paramActivity, String.valueOf(str1).length() + 23 + "Failed to set VR mode: " + str1);
      }
    }
    return false;
  }
  
  private static void k(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, 2131558758);
    paramContext.setMessage(paramInt1).setTitle(2131428532).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(2131428523, new F());
    paramContext.create().show();
  }
  
  public static boolean p()
  {
    return Build.VERSION.SDK_INT >= 24;
  }
  
  public static boolean q(Activity paramActivity, boolean paramBoolean)
  {
    return f(paramActivity, paramBoolean);
  }
}
