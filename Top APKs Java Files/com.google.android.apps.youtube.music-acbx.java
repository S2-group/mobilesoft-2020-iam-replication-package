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

public class acbx
{
  public static final String a = acbx.class.getSimpleName();
  
  private acbx() {}
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext, 2132017406);
    paramContext.setMessage(paramInt1).setTitle(2131952050).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(2131951851, new acca());
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
    boolean bool = false;
    Object localObject1;
    if ((a()) && (paramActivity.getPackageManager().hasSystemFeature("android.software.vr.mode"))) {
      localObject1 = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
    }
    try
    {
      paramActivity.setVrModeEnabled(paramBoolean, (ComponentName)localObject1);
      paramBoolean = true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      do
      {
        do
        {
          localObject1 = a;
          localObject2 = String.valueOf(localNameNotFoundException);
          StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject2).length() + 25);
          localStringBuilder.append("No VR service component: ");
          localStringBuilder.append((String)localObject2);
          Log.w((String)localObject1, localStringBuilder.toString());
          paramBoolean = bool;
        } while (!a());
        paramBoolean = bool;
      } while (!paramActivity.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"));
      localObject1 = paramActivity.getPackageManager().getInstalledApplications(0).iterator();
      int i;
      for (;;)
      {
        if (((Iterator)localObject1).hasNext()) {
          if (((ApplicationInfo)((Iterator)localObject1).next()).packageName.equals("com.google.vr.vrcore"))
          {
            localObject1 = Settings.Secure.getString(paramActivity.getContentResolver(), "enabled_vr_listeners");
            localObject2 = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
            if (localObject1 != null) {
              if (((String)localObject1).contains(((ComponentName)localObject2).flattenToString())) {
                i = 0;
              }
            }
          }
        }
      }
      for (;;)
      {
        if (acda.a()) {}
        do
        {
          Log.w(a, "Failed to handle missing VrCore package.");
          return false;
          if (i == -1)
          {
            a(paramActivity, 2131952052, 2131952106, new acby(paramActivity));
            return false;
          }
        } while (i != -2);
        a(paramActivity, 2131952051, 2131952107, new acbz(paramActivity));
        return false;
        i = -2;
        continue;
        i = -2;
        continue;
        i = -1;
      }
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      paramActivity = a;
      String str = String.valueOf(localUnsupportedOperationException);
      Object localObject2 = new StringBuilder(String.valueOf(str).length() + 23);
      ((StringBuilder)localObject2).append("Failed to set VR mode: ");
      ((StringBuilder)localObject2).append(str);
      Log.w(paramActivity, ((StringBuilder)localObject2).toString());
    }
    return paramBoolean;
    a();
    return false;
    return false;
  }
}
