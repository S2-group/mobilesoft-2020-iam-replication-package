import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

final class dus
{
  static Uri jdMethod_do(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    return Uri.parse(localStringBuilder.toString());
  }
  
  static boolean jdMethod_do(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  static Uri jdMethod_if(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("amzn://apps/android?p=");
    localStringBuilder.append(paramString);
    return Uri.parse(localStringBuilder.toString());
  }
}
