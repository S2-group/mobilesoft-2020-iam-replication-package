package net.hiddenscreen.k;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

public class b
{
  public static void a(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, "google play not installed", 0).show();
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, "ERROR " + paramString.getMessage(), 0).show();
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(1);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      do
      {
        if (!paramContext.hasNext()) {
          break;
        }
      } while (!((PackageInfo)paramContext.next()).packageName.equals(paramString));
    }
    for (boolean bool = true;; bool = false)
    {
      StringBuilder localStringBuilder = new StringBuilder().append("package ");
      if (bool) {}
      for (paramContext = "installed";; paramContext = "NOT installed ")
      {
        c.c("HD", paramContext + paramString);
        return bool;
      }
    }
  }
}
