import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public final class egd
{
  private static final Uri a = Uri.parse("market://search?q=com.android.youtube");
  
  public static Intent a(PackageManager paramPackageManager, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("com.google.android.apps.plus.VIEW_DEEP_LINK");
    localIntent.setPackage(paramString1);
    localIntent.setData(lz.g(paramString2, paramString3));
    localIntent.addFlags(335544320);
    if (a(paramPackageManager, localIntent)) {
      return localIntent;
    }
    return null;
  }
  
  public static Intent a(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://details?id=%1$s", new Object[] { paramString })));
    paramString.putExtra("use_direct_purchase", true);
    paramString.addFlags(524288);
    return paramString;
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramString4 = a(localPackageManager, paramString3, paramString4, paramString5);
    paramString5 = paramString4;
    if (paramString4 == null)
    {
      paramString4 = localPackageManager.getLaunchIntentForPackage(paramString3);
      paramString4.addFlags(335544320);
      if (!a(localPackageManager, paramString4)) {
        break label247;
      }
    }
    for (;;)
    {
      paramString5 = paramString4;
      if (Log.isLoggable("DeepLinking", 3))
      {
        paramString5 = String.valueOf(paramString4);
        new StringBuilder(String.valueOf(paramString3).length() + 62 + String.valueOf(paramString5).length()).append("Could not resolve continue Intent for ").append(paramString3).append(" falling back to launch ").append(paramString5);
        paramString5 = paramString4;
      }
      int i = (int)System.currentTimeMillis();
      paramString1 = paramContext.getString(2131757586, new Object[] { paramString2, paramString1 });
      paramString2 = PendingIntent.getActivity(paramContext, i, paramString5, 0);
      paramString1 = new Notification.Builder(paramContext).setSmallIcon(2130838754).setContentTitle(paramContext.getString(2131755230)).setContentText(paramString1).setTicker(paramString1).setWhen(System.currentTimeMillis()).setContentIntent(paramString2).setAutoCancel(true).setDefaults(4).getNotification();
      ((NotificationManager)paramContext.getSystemService("notification")).notify(String.format("%s:notifications:%s", new Object[] { paramContext.getPackageName(), paramString3 }), 1000, paramString1);
      return;
      label247:
      paramString4 = null;
    }
  }
  
  public static boolean a(PackageManager paramPackageManager)
  {
    try
    {
      ApplicationInfo localApplicationInfo = paramPackageManager.getApplicationInfo("com.android.vending", 0);
      if (localApplicationInfo != null) {
        break label64;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (!Log.isLoggable("DeepLinking", 3)) {}
      paramPackageManager = String.valueOf("com.android.vending not found: ");
      String str = String.valueOf(localNameNotFoundException.getMessage());
      if (str.length() == 0) {
        break label53;
      }
      paramPackageManager.concat(str);
      return false;
      label53:
      new String(paramPackageManager);
      return false;
    }
    return false;
    label64:
    return a(paramPackageManager, new Intent("android.intent.action.VIEW", a));
  }
  
  private static boolean a(PackageManager paramPackageManager, Intent paramIntent)
  {
    if (paramIntent == null) {}
    do
    {
      return false;
      paramPackageManager = paramPackageManager.queryIntentActivities(paramIntent, 0);
    } while ((paramPackageManager == null) || (paramPackageManager.isEmpty()));
    return true;
  }
  
  public static boolean a(PackageManager paramPackageManager, String paramString)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(128).iterator();
    while (paramPackageManager.hasNext()) {
      if (((ApplicationInfo)paramPackageManager.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
}
