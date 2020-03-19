import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class se
{
  private static String a = null;
  
  private static String a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {
      return "";
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return "";
    }
    return paramContext.activityInfo.packageName;
  }
  
  private static String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if (((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) && (!TextUtils.isEmpty(localProviderInfo.authority)) && (localProviderInfo.authority.contains(".launcher.settings")))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      Object localObject1 = paramContext.getPackageManager();
      localObject1 = ((PackageManager)localObject1).getApplicationLabel(((PackageManager)localObject1).getApplicationInfo(paramContext.getPackageName(), 128)).toString();
      if ((paramContext == null) || (TextUtils.isEmpty((CharSequence)localObject1)))
      {
        bool2 = false;
        return bool2;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2 = null;
        continue;
        if (TextUtils.isEmpty(a)) {
          a = c(paramContext);
        }
        paramContext = paramContext.getContentResolver();
        if (TextUtils.isEmpty(a)) {
          break label186;
        }
        try
        {
          paramContext = paramContext.query(Uri.parse(a), new String[] { "title", "iconResource" }, "title=?", new String[] { localObject2 }, null);
          if (paramContext == null) {
            break label181;
          }
          int i = paramContext.getCount();
          if (i <= 0) {
            break label181;
          }
          bool1 = true;
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            boolean bool1 = false;
            continue;
            bool1 = false;
          }
        }
        boolean bool2 = bool1;
        if (paramContext != null)
        {
          bool2 = bool1;
          try
          {
            if (!paramContext.isClosed())
            {
              paramContext.close();
              return bool1;
            }
          }
          catch (Exception paramContext) {}
        }
      }
      rm.c("isShortCutExist", paramContext.getMessage());
      return bool1;
    }
    label181:
    label186:
    return false;
  }
  
  private static String b(Context paramContext)
  {
    return a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
  }
  
  private static String c(Context paramContext)
  {
    String str2 = b(paramContext);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.trim().equals("")) {}
    }
    else
    {
      str1 = a(paramContext);
      str1 = a(paramContext, str1 + ".permission.READ_SETTINGS");
    }
    paramContext = str1;
    int i;
    if (TextUtils.isEmpty(str1))
    {
      i = Build.VERSION.SDK_INT;
      if (i >= 8) {
        break label99;
      }
      paramContext = "com.android.launcher.settings";
    }
    for (;;)
    {
      return "content://" + paramContext + "/favorites?notify=true";
      label99:
      if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else if (i < 23) {
        paramContext = "com.android.launcher3.settings";
      } else {
        paramContext = "com.google.android.launcher.settings";
      }
    }
  }
}
