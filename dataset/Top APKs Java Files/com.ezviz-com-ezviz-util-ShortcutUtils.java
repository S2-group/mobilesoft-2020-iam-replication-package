package com.ezviz.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.videogo.util.LogUtil;
import java.util.Iterator;
import java.util.List;

public class ShortcutUtils
{
  static final String ACTION_INSTALL = "com.android.launcher.action.INSTALL_SHORTCUT";
  static final String ACTION_UNINSTALL = "com.android.launcher.action.UNINSTALL_SHORTCUT";
  private static String TAG = "ShortcutUtils";
  
  public ShortcutUtils() {}
  
  public static void addShortcut(Context paramContext, String paramString1, String paramString2)
  {
    addShortcutToDesktop(paramContext, paramContext.getPackageName(), paramString2, paramString1, null, false);
  }
  
  public static void addShortcutToDesktop(Context paramContext, String paramString1, String paramString2, String paramString3, Drawable paramDrawable, boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)paramDrawable;
    Object localObject;
    if (paramString3 != null)
    {
      paramDrawable = paramString3;
      localObject = localBitmapDrawable;
      if (localBitmapDrawable != null) {}
    }
    else
    {
      localObject = paramContext.getPackageManager();
    }
    for (;;)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(paramString1, 129);
        paramDrawable = paramString3;
        if (paramString3 == null) {
          paramDrawable = ((PackageManager)localObject).getApplicationLabel(localApplicationInfo).toString();
        }
        if (localBitmapDrawable == null)
        {
          paramString3 = (BitmapDrawable)((PackageManager)localObject).getApplicationIcon(localApplicationInfo);
          localObject = paramString3;
          localIntent.putExtra("android.intent.extra.shortcut.NAME", paramDrawable);
          localIntent.putExtra("android.intent.extra.shortcut.ICON", ((BitmapDrawable)localObject).getBitmap());
          localIntent.putExtra("duplicate", paramBoolean);
          paramString1 = new ComponentName(paramString1, paramString2);
          paramString1 = new Intent("android.intent.action.MAIN").setComponent(paramString1);
          paramString1.addCategory("android.intent.category.LAUNCHER");
          localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString1);
          paramContext.sendBroadcast(localIntent);
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      catch (Resources.NotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      paramString3 = localBitmapDrawable;
    }
  }
  
  public static void addShortcutToOptions(Activity paramActivity, String paramString1, String paramString2, String paramString3, Drawable paramDrawable, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)paramDrawable;
    PackageManager localPackageManager = paramActivity.getPackageManager();
    for (;;)
    {
      try
      {
        ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramString1, 8320);
        paramDrawable = paramString3;
        if (paramString3 == null) {
          paramDrawable = localPackageManager.getApplicationLabel(localApplicationInfo).toString();
        }
        if (localBitmapDrawable == null)
        {
          paramString3 = (BitmapDrawable)localPackageManager.getApplicationIcon(localApplicationInfo);
          localIntent.putExtra("android.intent.extra.shortcut.NAME", paramDrawable);
          localIntent.putExtra("android.intent.extra.shortcut.ICON", paramString3.getBitmap());
          paramString1 = new ComponentName(paramString1, paramString2);
          paramString1 = new Intent("android.intent.action.MAIN").setComponent(paramString1);
          paramString1.addCategory("android.intent.category.LAUNCHER");
          localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString1);
          paramActivity.setResult(-1, localIntent);
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
      catch (Resources.NotFoundException paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
      paramString3 = localBitmapDrawable;
    }
  }
  
  public static void delShortcut(Context paramContext, String paramString1, String paramString2)
  {
    delShortcutFromDesktop(paramContext, paramContext.getPackageName(), paramString2, paramString1);
  }
  
  public static void delShortcutFromDesktop(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    String str = paramString3;
    if (paramString3 == null) {
      paramString3 = paramContext.getPackageManager();
    }
    try
    {
      str = paramString3.getApplicationLabel(paramString3.getApplicationInfo(paramString1, 8320)).toString();
      localIntent.putExtra("android.intent.extra.shortcut.NAME", str);
      paramString1 = new ComponentName(paramString1, paramString2);
      paramString1 = new Intent("android.intent.action.MAIN").setComponent(paramString1);
      paramString1.addCategory("android.intent.category.LAUNCHER");
      localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString1);
      paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (Resources.NotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String getAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if (paramContext != null)
    {
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
            if (paramString.equals(localProviderInfo.readPermission)) {
              return localProviderInfo.authority;
            }
            if (paramString.equals(localProviderInfo.writePermission)) {
              return localProviderInfo.authority;
            }
            i += 1;
          }
        }
      }
    }
    return "com.android.launcher2.settings";
  }
  
  /* Error */
  public static boolean hasShortcut(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_1
    //   4: astore 5
    //   6: aload_1
    //   7: ifnonnull +30 -> 37
    //   10: aload_0
    //   11: invokevirtual 49	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore_1
    //   15: aload_1
    //   16: aload_1
    //   17: aload_0
    //   18: invokevirtual 30	android/content/Context:getPackageName	()Ljava/lang/String;
    //   21: sipush 8320
    //   24: invokevirtual 55	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   27: invokevirtual 59	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   30: invokeinterface 64 1 0
    //   35: astore 5
    //   37: aload_0
    //   38: invokevirtual 185	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   41: astore_1
    //   42: aload_0
    //   43: ldc -69
    //   45: invokestatic 189	com/ezviz/util/ShortcutUtils:getAuthorityFromPermission	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   48: astore_0
    //   49: aload_1
    //   50: new 191	java/lang/StringBuilder
    //   53: dup
    //   54: ldc -63
    //   56: invokespecial 194	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: aload_0
    //   60: invokevirtual 198	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc -56
    //   65: invokevirtual 198	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 201	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokestatic 207	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   74: iconst_2
    //   75: anewarray 165	java/lang/String
    //   78: dup
    //   79: iconst_0
    //   80: ldc -47
    //   82: aastore
    //   83: dup
    //   84: iconst_1
    //   85: ldc -45
    //   87: aastore
    //   88: ldc -43
    //   90: iconst_1
    //   91: anewarray 165	java/lang/String
    //   94: dup
    //   95: iconst_0
    //   96: aload 5
    //   98: aastore
    //   99: aconst_null
    //   100: invokevirtual 219	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   103: astore_1
    //   104: aload_1
    //   105: ifnull +115 -> 220
    //   108: aload_1
    //   109: astore_0
    //   110: aload_1
    //   111: invokeinterface 225 1 0
    //   116: istore_2
    //   117: iload_2
    //   118: ifle +102 -> 220
    //   121: iconst_1
    //   122: istore_3
    //   123: iload_3
    //   124: istore 4
    //   126: aload_1
    //   127: ifnull +12 -> 139
    //   130: aload_1
    //   131: invokeinterface 228 1 0
    //   136: iload_3
    //   137: istore 4
    //   139: iload 4
    //   141: ireturn
    //   142: astore_0
    //   143: aload_0
    //   144: invokevirtual 114	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   147: iconst_0
    //   148: ireturn
    //   149: astore_0
    //   150: aload_0
    //   151: invokevirtual 115	android/content/res/Resources$NotFoundException:printStackTrace	()V
    //   154: iconst_0
    //   155: ireturn
    //   156: astore 5
    //   158: aconst_null
    //   159: astore_1
    //   160: aload_1
    //   161: astore_0
    //   162: aload 5
    //   164: invokevirtual 229	java/lang/Exception:printStackTrace	()V
    //   167: aload_1
    //   168: ifnull +46 -> 214
    //   171: aload_1
    //   172: invokeinterface 228 1 0
    //   177: iconst_0
    //   178: istore 4
    //   180: goto -41 -> 139
    //   183: astore_0
    //   184: aload 6
    //   186: astore_1
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 228 1 0
    //   197: aload_0
    //   198: athrow
    //   199: astore 5
    //   201: aload_0
    //   202: astore_1
    //   203: aload 5
    //   205: astore_0
    //   206: goto -19 -> 187
    //   209: astore 5
    //   211: goto -51 -> 160
    //   214: iconst_0
    //   215: istore 4
    //   217: goto -78 -> 139
    //   220: iconst_0
    //   221: istore_3
    //   222: goto -99 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	paramContext	Context
    //   0	225	1	paramString	String
    //   116	2	2	i	int
    //   122	100	3	bool1	boolean
    //   124	92	4	bool2	boolean
    //   4	93	5	str	String
    //   156	7	5	localException1	Exception
    //   199	5	5	localObject1	Object
    //   209	1	5	localException2	Exception
    //   1	184	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	37	142	android/content/pm/PackageManager$NameNotFoundException
    //   15	37	149	android/content/res/Resources$NotFoundException
    //   37	104	156	java/lang/Exception
    //   37	104	183	finally
    //   110	117	199	finally
    //   162	167	199	finally
    //   110	117	209	java/lang/Exception
  }
  
  public static void updateShortcutIcon(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1 == null) {
      paramString1 = paramContext.getPackageManager();
    }
    try
    {
      str = paramString1.getApplicationLabel(paramString1.getApplicationInfo(paramContext.getPackageName(), 8320)).toString();
      Object localObject;
      ContentValues localContentValues;
      int i;
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        paramString1 = paramContext.getContentResolver();
        localObject = getAuthorityFromPermission(paramContext, "com.android.launcher.permission.WRITE_SETTINGS");
        localObject = Uri.parse("content://" + (String)localObject + "/favorites?notify=true");
        localContentValues = new ContentValues();
        localContentValues.put("iconResource", paramContext.getPackageName() + ":drawable/" + paramString2);
        i = paramString1.update((Uri)localObject, localContentValues, "title=?", new String[] { str });
        LogUtil.f(TAG, "updateShortcut: " + i);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramContext = paramContext;
      paramContext.printStackTrace();
      return;
    }
    catch (Resources.NotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
  }
  
  public static void updateShortcutTitle(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1 == null) {
      paramString1 = paramContext.getPackageManager();
    }
    try
    {
      str = paramString1.getApplicationLabel(paramString1.getApplicationInfo(paramContext.getPackageName(), 8320)).toString();
      ContentValues localContentValues;
      int i;
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        paramString1 = paramContext.getContentResolver();
        paramContext = getAuthorityFromPermission(paramContext, "com.android.launcher.permission.WRITE_SETTINGS");
        paramContext = Uri.parse("content://" + paramContext + "/favorites?notify=true");
        localContentValues = new ContentValues();
        localContentValues.put("title", str);
        i = paramString1.update(paramContext, localContentValues, "title=?", new String[] { paramString2 });
        LogUtil.f(TAG, "updateShortcut: " + i);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramContext = paramContext;
      paramContext.printStackTrace();
      return;
    }
    catch (Resources.NotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
  }
  
  public static void updateUserMicroportalShortcut() {}
}
