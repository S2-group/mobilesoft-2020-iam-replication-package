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
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Window;
import java.util.Iterator;
import java.util.List;

public class Sj
{
  private static final String a = Sj.class.getSimpleName();
  private static int b = 0;
  
  private Sj() {}
  
  private static int a(Context paramContext)
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
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, Su.a);
    paramContext.setMessage(paramInt1).setTitle(St.d).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(St.a, new Sm());
    paramContext.create().show();
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    return a(paramActivity, paramBoolean, 1);
  }
  
  private static boolean a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    int j = 1;
    int i;
    if ((b()) && (paramActivity.getPackageManager().hasSystemFeature("android.software.vr.mode")))
    {
      i = 1;
      if (i != 0) {
        break label38;
      }
      b();
    }
    for (;;)
    {
      return false;
      i = 0;
      break;
      label38:
      Object localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
      try
      {
        paramActivity.setVrModeEnabled(paramBoolean, (ComponentName)localObject);
        return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = a;
        String str2 = String.valueOf(localNameNotFoundException);
        Log.w((String)localObject, String.valueOf(str2).length() + 25 + "No VR service component: " + str2);
        if ((paramInt & 0x1) != 0)
        {
          int k;
          if ((b()) && (paramActivity.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")))
          {
            paramInt = 1;
            if (paramInt == 0) {
              continue;
            }
            k = a(paramActivity);
            if ((!"goldfish".equals(Build.HARDWARE)) && (!"ranchu".equals(Build.HARDWARE))) {
              break label224;
            }
            i = 1;
            paramInt = j;
            if (i == 0)
            {
              if (k != -1) {
                break label229;
              }
              a(paramActivity, St.f, St.g, new Sk(paramActivity));
              paramInt = 0;
            }
          }
          while (paramInt != 0)
          {
            Log.w(a, "Failed to handle missing VrCore package.");
            return false;
            paramInt = 0;
            break label137;
            i = 0;
            break label171;
            paramInt = j;
            if (k == -2)
            {
              a(paramActivity, St.e, St.h, new Sl(paramActivity));
              paramInt = 0;
            }
          }
        }
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        label137:
        label171:
        label224:
        label229:
        paramActivity = a;
        String str1 = String.valueOf(localUnsupportedOperationException);
        Log.w(paramActivity, String.valueOf(str1).length() + 23 + "Failed to set VR mode: " + str1);
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
    if (!((PowerManager)paramActivity.getSystemService("power")).isSustainedPerformanceModeSupported()) {
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