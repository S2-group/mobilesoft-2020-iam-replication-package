import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import com.google.inject.Inject;
import com.lmi.rescue.app.RescueApplication;
import java.util.Iterator;
import java.util.List;

public class aql
{
  private Context a;
  
  @Inject
  public aql(RescueApplication paramRescueApplication)
  {
    this.a = paramRescueApplication;
  }
  
  @SuppressLint({"InlinedApi"})
  public static boolean b()
  {
    int i = Build.VERSION.SDK_INT;
    return Settings.Secure.getInt(RescueApplication.b().getContentResolver(), "install_non_market_apps", 0) == 1;
  }
  
  public final String a()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
    Object localObject1 = localSharedPreferences.getString("device_platform_key", null);
    if (localObject1 != null) {
      return localObject1;
    }
    localObject1 = this.a.getPackageManager();
    Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(0);
    if (localObject2 != null)
    {
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
        if (((ApplicationInfo)localObject3).uid == 1000) {
          for (;;)
          {
            int i;
            try
            {
              localObject3 = ((PackageManager)localObject1).getPackageInfo(((ApplicationInfo)localObject3).packageName, 4160);
              if ((((PackageInfo)localObject3).signatures == null) || (((PackageInfo)localObject3).permissions == null)) {
                break;
              }
              Object localObject4 = ((PackageInfo)localObject3).permissions;
              int j = localObject4.length;
              i = 0;
              if (i >= j) {
                break;
              }
              Object localObject5 = localObject4[i];
              if ((localObject5.protectionLevel != 2) || (!localObject5.name.matches("^android.permission.*"))) {
                break label236;
              }
              localObject3 = Integer.toHexString(localObject3.signatures[0].hashCode());
              localObject4 = localSharedPreferences.edit();
              ((SharedPreferences.Editor)localObject4).putString("device_platform_key", (String)localObject3);
              ((SharedPreferences.Editor)localObject4).commit();
              return localObject3;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              aps.f.a("Failed to determine device platform key: %s", new Object[] { localNameNotFoundException });
            }
            break;
            label236:
            i += 1;
          }
        }
      }
    }
    return null;
  }
}
