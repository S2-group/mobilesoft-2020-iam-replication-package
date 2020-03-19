package o;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import com.badoo.mobile.AppServicesProvider;
import com.badoo.mobile.android.BadooActivity;
import java.util.Iterator;
import java.util.List;

public class bTK
{
  public bTK() {}
  
  private static String a(Context paramContext)
  {
    Object localObject1 = paramContext.getApplicationContext().getPackageManager();
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.HOME");
    ((Intent)localObject2).addCategory("android.intent.category.DEFAULT");
    localObject1 = ((PackageManager)localObject1).resolveActivity((Intent)localObject2, 0);
    if ((localObject1 != null) && (((ResolveInfo)localObject1).activityInfo != null) && (!bVN.e(((ResolveInfo)localObject1).activityInfo.packageName)))
    {
      localObject1 = ((ResolveInfo)localObject1).activityInfo.packageName;
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject2 = (PackageInfo)paramContext.next();
        if ((((String)localObject1).equals(((PackageInfo)localObject2).packageName)) && (((PackageInfo)localObject2).providers != null))
        {
          localObject2 = ((PackageInfo)localObject2).providers;
          int j = localObject2.length;
          int i = 0;
          while (i < j)
          {
            Object localObject3 = localObject2[i];
            String str1 = localObject3.readPermission;
            String str2 = localObject3.writePermission;
            if ((str1 != null) && (str2 != null))
            {
              boolean bool1 = str1.endsWith(".permission.READ_SETTINGS");
              boolean bool2 = str2.endsWith(".permission.WRITE_SETTINGS");
              if ((bool1) && (bool2)) {
                return localObject3.authority;
              }
            }
            i += 1;
          }
        }
      }
    }
    return null;
  }
  
  public static void c(Context paramContext)
  {
    Object localObject = (WK)AppServicesProvider.d(PT.e);
    if ((((WK)localObject).a("app_shortcut_creation_triggered", false)) || (!d(paramContext))) {
      return;
    }
    ((WK)localObject).e("app_shortcut_creation_triggered", true);
    localObject = paramContext.getResources().getString(2131889608);
    if (!c(paramContext, (String)localObject))
    {
      Intent localIntent1 = new Intent(paramContext, BadooActivity.class);
      localIntent1.setAction("android.intent.action.MAIN");
      localIntent1.addCategory("android.intent.category.LAUNCHER");
      localIntent1.addFlags(268435456);
      localIntent1.addFlags(32768);
      Intent localIntent2 = new Intent();
      localIntent2.putExtra("duplicate", false);
      localIntent2.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
      localIntent2.putExtra("android.intent.extra.shortcut.NAME", (String)localObject);
      localIntent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, 2131689472));
      localIntent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
      paramContext.sendBroadcast(localIntent2);
    }
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool2 = bool3;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getContentResolver().query(Uri.parse("content://" + a(paramContext) + "/favorites?notify=false"), new String[] { "title" }, "title=?", new String[] { paramString }, null);
        if (paramContext != null)
        {
          bool2 = bool3;
          if (paramContext.getCount() > 0)
          {
            bool1 = true;
            bool2 = bool1;
            paramContext.close();
          }
        }
        else
        {
          return bool1;
        }
      }
      catch (Exception paramContext)
      {
        return bool2;
      }
      bool1 = false;
    }
  }
  
  private static boolean d(Context paramContext)
  {
    return WV.p(paramContext).equals("com.sec.android.app.launcher");
  }
}
