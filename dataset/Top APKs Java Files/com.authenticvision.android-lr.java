import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.authenticvision.android.sdk.ui.views.webview.AdvancedWebView;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class lr
{
  private static String a;
  
  public lr() {}
  
  public static void a(Activity paramActivity, String paramString)
  {
    a(paramActivity, paramString, false);
  }
  
  public static void a(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage(b(paramActivity));
    paramString.addFlags(268435456);
    paramActivity.startActivity(paramString);
    if (paramBoolean) {
      paramActivity.overridePendingTransition(0, 0);
    }
  }
  
  public static boolean a(Context paramContext)
  {
    return b(paramContext) != null;
  }
  
  public static String b(Context paramContext)
  {
    if (a != null) {
      return a;
    }
    List localList = Arrays.asList(AdvancedWebView.e);
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if ((localApplicationInfo.enabled) && (localList.contains(localApplicationInfo.packageName)))
      {
        a = localApplicationInfo.packageName;
        return localApplicationInfo.packageName;
      }
    }
    return null;
  }
}
