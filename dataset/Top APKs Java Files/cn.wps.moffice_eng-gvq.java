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

public final class gvq
{
  private static String AUTHORITY = null;
  
  public gvq() {}
  
  public static boolean aT(Context paramContext, String paramString)
  {
    if (aU(paramContext, paramString)) {
      return true;
    }
    return aW(paramContext, paramString);
  }
  
  private static boolean aU(Context paramContext, String paramString)
  {
    boolean bool2;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString)))
    {
      bool2 = false;
      return bool2;
    }
    Object localObject1;
    label129:
    int i;
    if (TextUtils.isEmpty(AUTHORITY))
    {
      Object localObject2 = aV(paramContext, "com.android.launcher.permission.READ_SETTINGS");
      localObject1 = localObject2;
      if ("".equals(((String)localObject2).trim())) {
        localObject1 = aV(paramContext, "com.google.android.launcher.permission.READ_SETTINGS");
      }
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (!((String)localObject1).trim().equals("")) {}
      }
      else
      {
        localObject1 = new Intent("android.intent.action.MAIN");
        ((Intent)localObject1).addCategory("android.intent.category.HOME");
        localObject1 = paramContext.getPackageManager().resolveActivity((Intent)localObject1, 0);
        if ((localObject1 != null) && (((ResolveInfo)localObject1).activityInfo != null)) {
          break label303;
        }
        localObject1 = "";
        localObject2 = aV(paramContext, (String)localObject1 + ".permission.READ_SETTINGS");
      }
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        i = Build.VERSION.SDK_INT;
        if (i >= 8) {
          break label339;
        }
        localObject1 = "com.android.launcher.settings";
      }
      label181:
      AUTHORITY = "content://" + (String)localObject1 + "/favorites?notify=true";
    }
    else
    {
      paramContext = paramContext.getContentResolver();
      if (TextUtils.isEmpty(AUTHORITY)) {
        break label367;
      }
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.query(Uri.parse(AUTHORITY), new String[] { "title", "iconResource" }, "title=?", new String[] { paramString }, null);
        if (paramContext == null) {
          break label362;
        }
        i = paramContext.getCount();
        if (i <= 0) {
          break label362;
        }
        bool1 = true;
        bool2 = bool1;
        if (paramContext == null) {
          break;
        }
        bool2 = bool1;
        try
        {
          if (paramContext.isClosed()) {
            break;
          }
          paramContext.close();
          return bool1;
        }
        catch (Exception paramContext)
        {
          return bool1;
        }
        label303:
        if (((ResolveInfo)localObject1).activityInfo.packageName.equals("android"))
        {
          localObject1 = "";
          break label129;
        }
        localObject1 = ((ResolveInfo)localObject1).activityInfo.packageName;
      }
      catch (Exception paramContext)
      {
        label339:
        return false;
      }
      if (i < 19)
      {
        localObject1 = "com.android.launcher2.settings";
        break label181;
      }
      localObject1 = "com.android.launcher3.settings";
      break label181;
      label362:
      boolean bool1 = false;
    }
    label367:
    return false;
  }
  
  private static String aV(Context paramContext, String paramString)
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
  
  private static boolean aW(Context paramContext, String paramString)
  {
    try
    {
      Uri localUri = Uri.parse("content://com.lenovo.launcher2.settings/favorites?notify=true");
      paramContext = paramContext.getContentResolver().query(localUri, null, " title= ? ", new String[] { paramString }, null);
      if ((paramContext != null) && (paramContext.moveToNext())) {
        return true;
      }
      if ((paramContext != null) && (!paramContext.isClosed())) {
        paramContext.close();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
}
