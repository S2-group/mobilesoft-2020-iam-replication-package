package com.hikvision.hikconnect.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.BitmapDrawable;

public class ShortcutUtils
{
  private static String a = "ShortcutUtils";
  
  public ShortcutUtils() {}
  
  public static void a(Context paramContext, String paramString)
  {
    String str1 = paramContext.getPackageName();
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    Object localObject = paramContext.getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(str1, 129);
      String str2 = ((PackageManager)localObject).getApplicationLabel(localApplicationInfo).toString();
      localObject = (BitmapDrawable)((PackageManager)localObject).getApplicationIcon(localApplicationInfo);
      localIntent.putExtra("android.intent.extra.shortcut.NAME", str2);
      localIntent.putExtra("android.intent.extra.shortcut.ICON", ((BitmapDrawable)localObject).getBitmap());
      localIntent.putExtra("duplicate", false);
      paramString = new ComponentName(str1, paramString);
      paramString = new Intent("android.intent.action.MAIN").setComponent(paramString);
      paramString.addCategory("android.intent.category.LAUNCHER");
      localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString);
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
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    String str2 = paramContext.getPackageName();
    Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    String str1 = paramString1;
    if (paramString1 == null) {
      paramString1 = paramContext.getPackageManager();
    }
    try
    {
      str1 = paramString1.getApplicationLabel(paramString1.getApplicationInfo(str2, 8320)).toString();
      localIntent.putExtra("android.intent.extra.shortcut.NAME", str1);
      paramString1 = new ComponentName(str2, paramString2);
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
  
  /* Error */
  public static boolean b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_1
    //   4: astore 6
    //   6: aload_1
    //   7: ifnonnull +30 -> 37
    //   10: aload_0
    //   11: invokevirtual 38	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore_1
    //   15: aload_1
    //   16: aload_1
    //   17: aload_0
    //   18: invokevirtual 27	android/content/Context:getPackageName	()Ljava/lang/String;
    //   21: sipush 8320
    //   24: invokevirtual 44	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   27: invokevirtual 48	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   30: invokeinterface 53 1 0
    //   35: astore 6
    //   37: aload_0
    //   38: invokevirtual 117	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   41: astore_1
    //   42: aload_0
    //   43: invokevirtual 38	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: bipush 8
    //   48: invokevirtual 121	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   51: astore_0
    //   52: aload_0
    //   53: ifnull +206 -> 259
    //   56: aload_0
    //   57: invokeinterface 127 1 0
    //   62: astore_0
    //   63: aload_0
    //   64: invokeinterface 133 1 0
    //   69: ifeq +190 -> 259
    //   72: aload_0
    //   73: invokeinterface 137 1 0
    //   78: checkcast 139	android/content/pm/PackageInfo
    //   81: getfield 143	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   84: astore 8
    //   86: aload 8
    //   88: ifnull -25 -> 63
    //   91: aload 8
    //   93: arraylength
    //   94: istore_3
    //   95: iconst_0
    //   96: istore_2
    //   97: iload_2
    //   98: iload_3
    //   99: if_icmpge -36 -> 63
    //   102: aload 8
    //   104: iload_2
    //   105: aaload
    //   106: astore 9
    //   108: ldc -111
    //   110: aload 9
    //   112: getfield 150	android/content/pm/ProviderInfo:readPermission	Ljava/lang/String;
    //   115: invokevirtual 156	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   118: ifeq +119 -> 237
    //   121: aload 9
    //   123: getfield 159	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   126: astore_0
    //   127: aload_1
    //   128: new 161	java/lang/StringBuilder
    //   131: dup
    //   132: ldc -93
    //   134: invokespecial 164	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   137: aload_0
    //   138: invokevirtual 168	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: ldc -86
    //   143: invokevirtual 168	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokestatic 177	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   152: iconst_2
    //   153: anewarray 152	java/lang/String
    //   156: dup
    //   157: iconst_0
    //   158: ldc -77
    //   160: aastore
    //   161: dup
    //   162: iconst_1
    //   163: ldc -75
    //   165: aastore
    //   166: ldc -73
    //   168: iconst_1
    //   169: anewarray 152	java/lang/String
    //   172: dup
    //   173: iconst_0
    //   174: aload 6
    //   176: aastore
    //   177: aconst_null
    //   178: invokevirtual 189	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   181: astore_1
    //   182: aload_1
    //   183: ifnull +146 -> 329
    //   186: aload_1
    //   187: astore_0
    //   188: aload_1
    //   189: invokeinterface 195 1 0
    //   194: istore_2
    //   195: iload_2
    //   196: ifle +133 -> 329
    //   199: iconst_1
    //   200: istore 4
    //   202: iload 4
    //   204: istore 5
    //   206: aload_1
    //   207: ifnull +13 -> 220
    //   210: aload_1
    //   211: invokeinterface 198 1 0
    //   216: iload 4
    //   218: istore 5
    //   220: iload 5
    //   222: ireturn
    //   223: astore_0
    //   224: aload_0
    //   225: invokevirtual 105	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   228: iconst_0
    //   229: ireturn
    //   230: astore_0
    //   231: aload_0
    //   232: invokevirtual 106	android/content/res/Resources$NotFoundException:printStackTrace	()V
    //   235: iconst_0
    //   236: ireturn
    //   237: ldc -111
    //   239: aload 9
    //   241: getfield 201	android/content/pm/ProviderInfo:writePermission	Ljava/lang/String;
    //   244: invokevirtual 156	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   247: ifeq +88 -> 335
    //   250: aload 9
    //   252: getfield 159	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   255: astore_0
    //   256: goto -129 -> 127
    //   259: ldc -53
    //   261: astore_0
    //   262: goto -135 -> 127
    //   265: astore 6
    //   267: aconst_null
    //   268: astore_1
    //   269: aload_1
    //   270: astore_0
    //   271: aload 6
    //   273: invokevirtual 204	java/lang/Exception:printStackTrace	()V
    //   276: aload_1
    //   277: ifnull +46 -> 323
    //   280: aload_1
    //   281: invokeinterface 198 1 0
    //   286: iconst_0
    //   287: istore 5
    //   289: goto -69 -> 220
    //   292: astore_0
    //   293: aload 7
    //   295: astore_1
    //   296: aload_1
    //   297: ifnull +9 -> 306
    //   300: aload_1
    //   301: invokeinterface 198 1 0
    //   306: aload_0
    //   307: athrow
    //   308: astore 6
    //   310: aload_0
    //   311: astore_1
    //   312: aload 6
    //   314: astore_0
    //   315: goto -19 -> 296
    //   318: astore 6
    //   320: goto -51 -> 269
    //   323: iconst_0
    //   324: istore 5
    //   326: goto -106 -> 220
    //   329: iconst_0
    //   330: istore 4
    //   332: goto -130 -> 202
    //   335: iload_2
    //   336: iconst_1
    //   337: iadd
    //   338: istore_2
    //   339: goto -242 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	342	0	paramContext	Context
    //   0	342	1	paramString	String
    //   96	243	2	i	int
    //   94	6	3	j	int
    //   200	131	4	bool1	boolean
    //   204	121	5	bool2	boolean
    //   4	171	6	str	String
    //   265	7	6	localException1	Exception
    //   308	5	6	localObject1	Object
    //   318	1	6	localException2	Exception
    //   1	293	7	localObject2	Object
    //   84	19	8	arrayOfProviderInfo	android.content.pm.ProviderInfo[]
    //   106	145	9	localProviderInfo	android.content.pm.ProviderInfo
    // Exception table:
    //   from	to	target	type
    //   15	37	223	android/content/pm/PackageManager$NameNotFoundException
    //   15	37	230	android/content/res/Resources$NotFoundException
    //   37	52	265	java/lang/Exception
    //   56	63	265	java/lang/Exception
    //   63	86	265	java/lang/Exception
    //   91	95	265	java/lang/Exception
    //   108	127	265	java/lang/Exception
    //   127	182	265	java/lang/Exception
    //   237	256	265	java/lang/Exception
    //   37	52	292	finally
    //   56	63	292	finally
    //   63	86	292	finally
    //   91	95	292	finally
    //   108	127	292	finally
    //   127	182	292	finally
    //   237	256	292	finally
    //   188	195	308	finally
    //   271	276	308	finally
    //   188	195	318	java/lang/Exception
  }
}
