package speedy.lovestickers.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import java.util.Iterator;
import java.util.List;
import speedy.lovestickers.util.e;

public class b
  extends BroadcastReceiver
{
  private static a a;
  
  private void a()
  {
    if (a != null) {
      a.b();
    }
  }
  
  public static void a(a paramA)
  {
    a = paramA;
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent = PreferenceManager.getDefaultSharedPreferences(paramContext);
    e localE = e.a();
    if (a(paramContext, localE.b()))
    {
      SharedPreferences.Editor localEditor = paramIntent.edit();
      localEditor.putBoolean("FIRST_APP_INSTALLED", true);
      localEditor.commit();
      a();
    }
    if (a(paramContext, localE.c()))
    {
      paramContext = paramIntent.edit();
      paramContext.putBoolean("SEC_APP_INSTALLED", true);
      paramContext.commit();
      a();
    }
  }
  
  public IBinder peekService(Context paramContext, Intent paramIntent)
  {
    return super.peekService(paramContext, paramIntent);
  }
}
