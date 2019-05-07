package hotchemi.android.rate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

final class UriHelper
{
  static Uri getGooglePlay(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://play.google.com/store/apps/details?id=" + paramString);
  }
  
  static boolean isPackageExists(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
}
