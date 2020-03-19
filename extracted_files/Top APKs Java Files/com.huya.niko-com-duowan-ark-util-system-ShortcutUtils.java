package com.duowan.ark.util.system;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.duowan.ark.util.KLog;
import com.duowan.ark.util.StringUtils;
import java.util.Iterator;
import java.util.List;

public class ShortcutUtils
{
  private static final String TAG = "ShortcutUtils";
  
  public ShortcutUtils() {}
  
  public static void createShortcut(Activity paramActivity, String paramString, int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramActivity, paramActivity.getClass().getName());
    createShortcut(paramActivity.getApplicationContext(), localIntent, paramString, paramInt);
  }
  
  public static void createShortcut(Context paramContext, Intent paramIntent, String paramString, int paramInt)
  {
    KLog.info("ShortcutUtils", "create shortcut %s", new Object[] { paramString });
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, paramInt));
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void createShortcut(Context paramContext, Intent paramIntent, String paramString, Bitmap paramBitmap)
  {
    KLog.info("ShortcutUtils", "create shortcut %s", new Object[] { paramString });
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    paramContext.sendBroadcast(localIntent);
  }
  
  private static String getAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
      if (arrayOfProviderInfo != null)
      {
        int j = arrayOfProviderInfo.length;
        int i = 0;
        ProviderInfo localProviderInfo;
        while (i < j)
        {
          localProviderInfo = arrayOfProviderInfo[i];
          if ((localProviderInfo != null) && ((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission)))) {
            break label119;
          }
          i += 1;
        }
        continue;
        label119:
        return localProviderInfo.authority;
      }
    }
    return null;
  }
  
  public static boolean hasShortcut(Context paramContext, String paramString)
  {
    Object localObject2 = getAuthorityFromPermission(paramContext.getApplicationContext(), "com.android.launcher.permission.READ_SETTINGS");
    Object localObject1 = localObject2;
    if (StringUtils.isNullOrEmpty((String)localObject2)) {
      localObject1 = getAuthorityFromPermission(paramContext.getApplicationContext(), "com.android.launcher.permission.WRITE_SETTINGS");
    }
    KLog.debug("ShortcutUtils", "authority: %s", new Object[] { localObject1 });
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("content://");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("/favorites?notify=true");
    localObject1 = Uri.parse(((StringBuilder)localObject2).toString());
    try
    {
      paramContext = paramContext.getContentResolver().query((Uri)localObject1, new String[] { "title" }, "title=?", new String[] { paramString }, null);
      if (paramContext != null)
      {
        KLog.debug("ShortcutUtils", "cursor cnt: %d", new Object[] { Integer.valueOf(paramContext.getCount()) });
        if ((paramContext.getCount() > 0) && (paramContext.moveToNext()))
        {
          paramContext.close();
          return true;
        }
        paramContext.close();
        return false;
      }
      KLog.debug("ShortcutUtils", "cursor is null");
      return false;
    }
    catch (Exception paramContext)
    {
      KLog.error("ShortcutUtils", paramContext);
    }
    return false;
  }
  
  public static boolean isSupportShortcut()
  {
    if ((!Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) && (!Build.MANUFACTURER.equalsIgnoreCase("meizu")) && (!Build.MANUFACTURER.equalsIgnoreCase("huawei"))) {
      return Build.MANUFACTURER.equalsIgnoreCase("lge");
    }
    return false;
  }
}
