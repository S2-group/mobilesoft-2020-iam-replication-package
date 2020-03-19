import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;

public class ym
{
  private static final String a = ym.class.getSimpleName();
  
  public ym() {}
  
  public static Intent a(String paramString)
  {
    return new Intent("android.intent.action.VIEW", Uri.parse(paramString));
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    if (paramString2 != null) {
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
    }
    if (paramString1 != null) {
      localIntent.putExtra("android.intent.extra.EMAIL", paramString1);
    }
    localIntent.setType("text/plain");
    if (paramString3 != null) {
      localIntent.putExtra("android.intent.extra.TEXT", paramString3);
    }
    paramContext.startActivity(Intent.createChooser(localIntent, paramContext.getResources().getString(2131230806)));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return true;
    }
    catch (Exception paramContext)
    {
      Timber.c(paramContext, "Error while launching app...", new Object[0]);
    }
    return false;
  }
  
  public static boolean a(PackageManager paramPackageManager, String paramString)
  {
    int j = 1;
    int i;
    if (paramPackageManager == null)
    {
      i = 1;
      if (paramString != null) {
        break label25;
      }
    }
    for (;;)
    {
      if ((j | i) == 0) {
        break label30;
      }
      return false;
      i = 0;
      break;
      label25:
      j = 0;
    }
    label30:
    if (abs.g(paramString)) {
      return false;
    }
    try
    {
      paramPackageManager = paramPackageManager.getInstalledApplications(0).iterator();
      while (paramPackageManager.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramPackageManager.next();
        if (localApplicationInfo.packageName.equals(paramString))
        {
          boolean bool = localApplicationInfo.enabled;
          return bool;
        }
      }
    }
    catch (Exception paramPackageManager) {}
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    int j = 1;
    int i;
    if (paramContext == null)
    {
      i = 1;
      if (paramString != null) {
        break label25;
      }
    }
    for (;;)
    {
      if ((j | i) == 0) {
        break label30;
      }
      return false;
      i = 0;
      break;
      label25:
      j = 0;
    }
    label30:
    if (abs.g(paramString)) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if (localApplicationInfo.packageName.equals(paramString))
        {
          boolean bool = localApplicationInfo.enabled;
          return bool;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
}
