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

public class Us
{
  private static final String a = Us.class.getSimpleName();
  private static int b = 0;
  
  private Us() {}
  
  private static int a(Context paramContext)
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
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, UD.a);
    paramContext.setMessage(paramInt1).setTitle(UC.d).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(UC.a, new Uv());
    paramContext.create().show();
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    return a(paramActivity, paramBoolean, 1);
  }
  
  private static boolean a(Activity paramActivity, boolean paramBoolean, int paramInt)
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
      b();
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
      StringBuilder localStringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 23);
      localStringBuilder1.append("Failed to set VR mode: ");
      localStringBuilder1.append(str1);
      Log.w(paramActivity, localStringBuilder1.toString());
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str1 = a;
      String str2 = String.valueOf(localNameNotFoundException);
      StringBuilder localStringBuilder2 = new StringBuilder(String.valueOf(str2).length() + 25);
      localStringBuilder2.append("No VR service component: ");
      localStringBuilder2.append(str2);
      Log.w(str1, localStringBuilder2.toString());
      if ((paramInt & 0x1) != 0)
      {
        if ((b()) && (paramActivity.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"))) {
          paramInt = 1;
        } else {
          paramInt = 0;
        }
        if (paramInt != 0)
        {
          i = a(paramActivity);
          if ((!"goldfish".equals(Build.HARDWARE)) && (!"ranchu".equals(Build.HARDWARE))) {
            paramInt = 0;
          } else {
            paramInt = 1;
          }
          if (paramInt == 0)
          {
            if (i == -1) {
              a(paramActivity, UC.f, UC.g, new Ut(paramActivity));
            }
            for (;;)
            {
              paramInt = 0;
              break label320;
              if (i != -2) {
                break;
              }
              a(paramActivity, UC.e, UC.h, new Uu(paramActivity));
            }
          }
          paramInt = j;
          label320:
          if (paramInt != 0) {
            Log.w(a, "Failed to handle missing VrCore package.");
          }
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
