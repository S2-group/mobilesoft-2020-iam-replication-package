package tv.twitch.android.d;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import tv.twitch.android.app.core.TwitchApplication;

public class c
{
  private boolean a = false;
  
  private c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals("com.nvidia.nvgamecast")) {
        this.a = true;
      }
    }
  }
  
  public static c a()
  {
    return a.a();
  }
  
  public void a(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName("com.nvidia.nvgamecast", "com.nvidia.nvgamecast.GamecastService");
    localIntent.putExtra("showSettings", true);
    paramContext.startService(localIntent);
  }
  
  public boolean b()
  {
    return this.a;
  }
  
  private static final class a
  {
    private static final c a = new c(TwitchApplication.a(), null);
  }
}
