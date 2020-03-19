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
import java.util.Iterator;
import java.util.List;

public class aoh
{
  public static final String a = "aoh";
  
  private aoh() {}
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, 2132017384);
    paramContext.setMessage(paramInt1).setTitle(2131952304).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(2131952070, new aok());
    paramContext.create().show();
  }
  
  public static boolean a()
  {
    return Build.VERSION.SDK_INT >= 24;
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    return b(paramActivity, paramBoolean);
  }
  
  private static boolean b(Activity paramActivity, boolean paramBoolean)
  {
    boolean bool = a();
    int m = 1;
    int i;
    if ((bool) && (paramActivity.getPackageManager().hasSystemFeature("android.software.vr.mode"))) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      a();
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
      localObject1 = String.valueOf(localUnsupportedOperationException);
      StringBuilder localStringBuilder1 = new StringBuilder(String.valueOf(localObject1).length() + 23);
      localStringBuilder1.append("Failed to set VR mode: ");
      localStringBuilder1.append((String)localObject1);
      Log.w(paramActivity, localStringBuilder1.toString());
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject1 = a;
      Object localObject2 = String.valueOf(localNameNotFoundException);
      StringBuilder localStringBuilder2 = new StringBuilder(String.valueOf(localObject2).length() + 25);
      localStringBuilder2.append("No VR service component: ");
      localStringBuilder2.append((String)localObject2);
      Log.w((String)localObject1, localStringBuilder2.toString());
      if ((a()) && (paramActivity.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0)
      {
        localObject1 = paramActivity.getPackageManager().getInstalledApplications(0).iterator();
        while (((Iterator)localObject1).hasNext()) {
          if (((ApplicationInfo)((Iterator)localObject1).next()).packageName.equals("com.google.vr.vrcore"))
          {
            i = 1;
            break label268;
          }
        }
        i = 0;
        label268:
        if (i == 0)
        {
          i = -1;
        }
        else
        {
          localObject1 = Settings.Secure.getString(paramActivity.getContentResolver(), "enabled_vr_listeners");
          localObject2 = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
          if ((localObject1 != null) && (((String)localObject1).contains(((ComponentName)localObject2).flattenToString()))) {
            i = 0;
          } else {
            i = -2;
          }
        }
        int j;
        if ((!"goldfish".equals(Build.HARDWARE)) && (!"ranchu".equals(Build.HARDWARE))) {
          j = 0;
        } else {
          j = 1;
        }
        int k = m;
        if (j == 0)
        {
          if (i == -1) {
            a(paramActivity, 2131952306, 2131952449, new aoi(paramActivity));
          }
          for (;;)
          {
            k = 0;
            break;
            k = m;
            if (i != -2) {
              break;
            }
            a(paramActivity, 2131952305, 2131952450, new aoj(paramActivity));
          }
        }
        if (k != 0) {
          Log.w(a, "Failed to handle missing VrCore package.");
        }
      }
    }
    return false;
  }
}
