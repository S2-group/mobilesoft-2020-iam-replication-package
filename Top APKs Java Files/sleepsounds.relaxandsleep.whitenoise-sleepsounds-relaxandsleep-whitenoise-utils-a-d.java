package sleepsounds.relaxandsleep.whitenoise.utils.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class d
{
  public static void a(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      localIntent.setFlags(268435456);
      if (a(paramContext)) {
        localIntent.setPackage("com.android.vending");
      }
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            boolean bool = localPackageInfo.packageName.equals("com.android.vending");
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (RuntimeException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
