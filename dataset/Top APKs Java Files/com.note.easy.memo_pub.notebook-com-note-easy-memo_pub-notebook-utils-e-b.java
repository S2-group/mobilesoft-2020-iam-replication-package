package com.note.easy.memo_pub.notebook.utils.e;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.note.easy.memo_pub.notebook.components.broadcast.ShortcutReceiver;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class b
{
  private static Uri a(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = getAuthorityFromPermissionDefault(paramContext);
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (!str.trim().equals("")) {}
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(getCurrentLauncherPackageName(paramContext));
      ((StringBuilder)localObject).append(".permission.READ_SETTINGS");
      localObject = getAuthorityFromPermission(paramContext, ((StringBuilder)localObject).toString());
    }
    localStringBuilder.append("content://");
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return Uri.parse(localStringBuilder.toString());
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("/favorites?notify=true");
    return Uri.parse(localStringBuilder.toString());
  }
  
  private static void a(Context paramContext, Class<?> paramClass, int paramInt, String paramString1, String paramString2)
  {
    a(paramContext, paramClass, paramInt, paramString1, paramString2, null);
  }
  
  private static void a(Context paramContext, Class<?> paramClass, int paramInt, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    ShortcutManager localShortcutManager = (ShortcutManager)paramContext.getSystemService("shortcut");
    if (localShortcutManager.isRequestPinShortcutSupported())
    {
      paramClass = new Intent(paramContext, paramClass);
      paramClass.setFlags(268435456);
      paramClass.setAction("android.intent.action.MAIN");
      if ((paramMap != null) && (!paramMap.isEmpty()))
      {
        paramMap = paramMap.entrySet().iterator();
        while (paramMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramMap.next();
          paramClass.putExtra((String)localEntry.getKey(), (String)localEntry.getValue());
        }
      }
      localShortcutManager.requestPinShortcut(new ShortcutInfo.Builder(paramContext, paramString2).setIcon(Icon.createWithResource(paramContext, paramInt)).setShortLabel(paramString1).setIntent(paramClass).build(), PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, ShortcutReceiver.class), 134217728).getIntentSender());
    }
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      paramContext = ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).getPinnedShortcuts().iterator();
      while (paramContext.hasNext()) {
        if (TextUtils.equals(((ShortcutInfo)paramContext.next()).getId(), paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  /* Error */
  private static boolean b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: istore 4
    //   5: iconst_0
    //   6: istore_3
    //   7: aconst_null
    //   8: astore 6
    //   10: aconst_null
    //   11: astore 5
    //   13: aload_0
    //   14: invokevirtual 209	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   17: aload_0
    //   18: invokestatic 211	com/note/easy/memo_pub/notebook/utils/e/b:a	(Landroid/content/Context;)Landroid/net/Uri;
    //   21: iconst_1
    //   22: anewarray 18	java/lang/String
    //   25: dup
    //   26: iconst_0
    //   27: ldc -43
    //   29: aastore
    //   30: ldc -41
    //   32: iconst_1
    //   33: anewarray 18	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: aload_1
    //   39: aastore
    //   40: aconst_null
    //   41: invokevirtual 221	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   44: astore_0
    //   45: iload_3
    //   46: istore_2
    //   47: aload_0
    //   48: ifnull +22 -> 70
    //   51: iload_3
    //   52: istore_2
    //   53: iload 4
    //   55: istore_3
    //   56: aload_0
    //   57: invokeinterface 227 1 0
    //   62: ifle +8 -> 70
    //   65: iconst_1
    //   66: istore_2
    //   67: goto +3 -> 70
    //   70: aload_0
    //   71: ifnull +22 -> 93
    //   74: iload_2
    //   75: istore_3
    //   76: aload_0
    //   77: invokeinterface 230 1 0
    //   82: ifne +11 -> 93
    //   85: iload_2
    //   86: istore_3
    //   87: aload_0
    //   88: invokeinterface 233 1 0
    //   93: iload_2
    //   94: istore_3
    //   95: aload_0
    //   96: ifnull +41 -> 137
    //   99: aload_0
    //   100: invokeinterface 233 1 0
    //   105: iload_2
    //   106: ireturn
    //   107: astore_1
    //   108: aload 5
    //   110: astore_0
    //   111: aload_0
    //   112: ifnull +9 -> 121
    //   115: aload_0
    //   116: invokeinterface 233 1 0
    //   121: aload_1
    //   122: athrow
    //   123: iload_2
    //   124: istore_3
    //   125: aload_0
    //   126: ifnull +11 -> 137
    //   129: aload_0
    //   130: invokeinterface 233 1 0
    //   135: iload_2
    //   136: istore_3
    //   137: iload_3
    //   138: ireturn
    //   139: astore_0
    //   140: aload 6
    //   142: astore_0
    //   143: goto -20 -> 123
    //   146: astore_1
    //   147: goto +7 -> 154
    //   150: astore_1
    //   151: goto -40 -> 111
    //   154: iload_3
    //   155: istore_2
    //   156: goto -33 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	paramContext	Context
    //   0	159	1	paramString	String
    //   1	155	2	bool1	boolean
    //   6	149	3	bool2	boolean
    //   3	51	4	bool3	boolean
    //   11	98	5	localObject1	Object
    //   8	133	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   13	45	107	finally
    //   13	45	139	java/lang/RuntimeException
    //   56	65	146	java/lang/RuntimeException
    //   76	85	146	java/lang/RuntimeException
    //   87	93	146	java/lang/RuntimeException
    //   56	65	150	finally
    //   76	85	150	finally
    //   87	93	150	finally
  }
  
  public static String getAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    for (;;)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8);
        if (paramContext == null) {
          return "";
        }
        paramContext = paramContext.iterator();
        if (paramContext.hasNext())
        {
          ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
          if (arrayOfProviderInfo == null) {
            continue;
          }
          int j = arrayOfProviderInfo.length;
          i = 0;
          if (i >= j) {
            continue;
          }
          ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
          if (!paramString.equals(localProviderInfo.readPermission)) {
            if (!paramString.equals(localProviderInfo.writePermission)) {
              break label122;
            }
          }
          paramContext = localProviderInfo.authority;
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return "";
      label122:
      i += 1;
    }
  }
  
  public static String getAuthorityFromPermissionDefault(Context paramContext)
  {
    return getAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
  }
  
  public static String getCurrentLauncherPackageName(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext != null) && (paramContext.activityInfo != null))
    {
      if (paramContext.activityInfo.packageName.equals("android")) {
        return "";
      }
      return paramContext.activityInfo.packageName;
    }
    return "";
  }
  
  public static Intent getShortcutIntent(Context paramContext, int paramInt, String paramString, Class<?> paramClass)
  {
    return getShortcutIntent(paramContext, paramInt, paramString, paramClass, null);
  }
  
  public static Intent getShortcutIntent(Context paramContext, int paramInt, String paramString, Class<?> paramClass, Map<String, String> paramMap)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, paramInt));
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("duplicate", false);
    paramContext = new Intent(paramContext, paramClass);
    paramContext.setAction("android.intent.action.MAIN");
    paramContext.setFlags(268435456);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramString = paramMap.entrySet().iterator();
      while (paramString.hasNext())
      {
        paramClass = (Map.Entry)paramString.next();
        paramContext.putExtra((String)paramClass.getKey(), (String)paramClass.getValue());
      }
    }
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramContext);
    return localIntent;
  }
  
  public static void installShortcut(Context paramContext, Class<?> paramClass, int paramInt, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      a(paramContext, paramClass, paramInt, paramString, paramString);
      return;
    }
    paramContext.sendBroadcast(getShortcutIntent(paramContext, paramInt, paramString, paramClass));
  }
  
  public static void installShortcut(Context paramContext, Class<?> paramClass, int paramInt, String paramString, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      a(paramContext, paramClass, paramInt, paramString, paramString, paramMap);
      return;
    }
    paramContext.sendBroadcast(getShortcutIntent(paramContext, paramInt, paramString, paramClass, paramMap));
  }
  
  public static boolean isShortcutExist(Context paramContext, String paramString)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 26) {
        return a(paramContext, paramString);
      }
      boolean bool = b(paramContext, paramString);
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
}
