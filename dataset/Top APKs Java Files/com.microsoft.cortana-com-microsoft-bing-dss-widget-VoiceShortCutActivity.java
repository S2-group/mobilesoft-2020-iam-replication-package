package com.microsoft.bing.dss.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

public class VoiceShortCutActivity
  extends Activity
{
  public static final String a = "android.intent.action.widget.start";
  private static final String b = VoiceShortCutActivity.class.getName();
  private static final String c = "com.android.launcher.permission.READ_SETTINGS";
  private static final String d = "content://%s/favorites?notify=true";
  
  public VoiceShortCutActivity() {}
  
  private static String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      paramString = ((PackageInfo)paramContext.next()).providers;
      if (paramString != null)
      {
        int m = paramString.length;
        int i = 0;
        Object localObject;
        String str1;
        String str2;
        int j;
        int k;
        while (i < m)
        {
          localObject = paramString[i];
          if (localObject == null) {
            return null;
          }
          str1 = localObject.readPermission;
          str2 = localObject.writePermission;
          if ((str1 != null) && (str2 != null))
          {
            if ((str1.startsWith("com.android.")) && (str1.endsWith(".permission.READ_SETTINGS")))
            {
              j = 1;
              if ((!str2.startsWith("com.android.")) || (!str2.endsWith(".permission.WRITE_SETTINGS"))) {
                break label162;
              }
            }
            label162:
            for (k = 1;; k = 0)
            {
              if ((j == 0) || (k == 0)) {
                break label168;
              }
              return localObject.authority;
              j = 0;
              break;
            }
          }
          label168:
          i += 1;
        }
        m = paramString.length;
        i = 0;
        while (i < m)
        {
          localObject = paramString[i];
          if (localObject == null) {
            return null;
          }
          str1 = localObject.readPermission;
          str2 = localObject.writePermission;
          if ((str1 != null) && (str2 != null))
          {
            if ((str1.startsWith("com.")) && (str1.endsWith(".permission.READ_SETTINGS")))
            {
              j = 1;
              if ((!str2.startsWith("com.")) || (!str2.endsWith(".permission.WRITE_SETTINGS"))) {
                break label288;
              }
            }
            label288:
            for (k = 1;; k = 0)
            {
              if ((j == 0) || (k == 0)) {
                break label294;
              }
              return localObject.authority;
              j = 0;
              break;
            }
          }
          label294:
          i += 1;
        }
      }
    }
    return null;
  }
  
  public static void a(Context paramContext)
  {
    Intent localIntent = c(paramContext);
    localIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    paramContext.sendBroadcast(localIntent);
  }
  
  /* Error */
  public static boolean b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 7
    //   3: iconst_0
    //   4: istore 6
    //   6: aload_0
    //   7: invokevirtual 36	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: bipush 8
    //   12: invokevirtual 42	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   15: astore 8
    //   17: aload 8
    //   19: ifnull +301 -> 320
    //   22: aload 8
    //   24: invokeinterface 48 1 0
    //   29: astore 8
    //   31: aload 8
    //   33: invokeinterface 54 1 0
    //   38: ifeq +282 -> 320
    //   41: aload 8
    //   43: invokeinterface 58 1 0
    //   48: checkcast 60	android/content/pm/PackageInfo
    //   51: getfield 64	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   54: astore 9
    //   56: aload 9
    //   58: ifnull -27 -> 31
    //   61: aload 9
    //   63: arraylength
    //   64: istore 4
    //   66: iconst_0
    //   67: istore_1
    //   68: iload_1
    //   69: iload 4
    //   71: if_icmpge +122 -> 193
    //   74: aload 9
    //   76: iload_1
    //   77: aaload
    //   78: astore 10
    //   80: aload 10
    //   82: ifnull +238 -> 320
    //   85: aload 10
    //   87: getfield 69	android/content/pm/ProviderInfo:readPermission	Ljava/lang/String;
    //   90: astore 11
    //   92: aload 10
    //   94: getfield 72	android/content/pm/ProviderInfo:writePermission	Ljava/lang/String;
    //   97: astore 12
    //   99: aload 11
    //   101: ifnull +85 -> 186
    //   104: aload 12
    //   106: ifnull +80 -> 186
    //   109: aload 11
    //   111: ldc 74
    //   113: invokevirtual 80	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   116: ifeq +60 -> 176
    //   119: aload 11
    //   121: ldc 82
    //   123: invokevirtual 85	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   126: ifeq +50 -> 176
    //   129: iconst_1
    //   130: istore_2
    //   131: aload 12
    //   133: ldc 74
    //   135: invokevirtual 80	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   138: ifeq +43 -> 181
    //   141: aload 12
    //   143: ldc 87
    //   145: invokevirtual 85	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   148: ifeq +33 -> 181
    //   151: iconst_1
    //   152: istore_3
    //   153: iload_2
    //   154: ifeq +32 -> 186
    //   157: iload_3
    //   158: ifeq +28 -> 186
    //   161: aload 10
    //   163: getfield 90	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   166: astore 8
    //   168: aload 8
    //   170: ifnonnull +156 -> 326
    //   173: iload 6
    //   175: ireturn
    //   176: iconst_0
    //   177: istore_2
    //   178: goto -47 -> 131
    //   181: iconst_0
    //   182: istore_3
    //   183: goto -30 -> 153
    //   186: iload_1
    //   187: iconst_1
    //   188: iadd
    //   189: istore_1
    //   190: goto -122 -> 68
    //   193: aload 9
    //   195: arraylength
    //   196: istore 4
    //   198: iconst_0
    //   199: istore_1
    //   200: iload_1
    //   201: iload 4
    //   203: if_icmpge -172 -> 31
    //   206: aload 9
    //   208: iload_1
    //   209: aaload
    //   210: astore 10
    //   212: aload 10
    //   214: ifnull +106 -> 320
    //   217: aload 10
    //   219: getfield 69	android/content/pm/ProviderInfo:readPermission	Ljava/lang/String;
    //   222: astore 11
    //   224: aload 10
    //   226: getfield 72	android/content/pm/ProviderInfo:writePermission	Ljava/lang/String;
    //   229: astore 12
    //   231: aload 11
    //   233: ifnull +80 -> 313
    //   236: aload 12
    //   238: ifnull +75 -> 313
    //   241: aload 11
    //   243: ldc 92
    //   245: invokevirtual 80	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   248: ifeq +55 -> 303
    //   251: aload 11
    //   253: ldc 82
    //   255: invokevirtual 85	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   258: ifeq +45 -> 303
    //   261: iconst_1
    //   262: istore_2
    //   263: aload 12
    //   265: ldc 92
    //   267: invokevirtual 80	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   270: ifeq +38 -> 308
    //   273: aload 12
    //   275: ldc 87
    //   277: invokevirtual 85	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   280: ifeq +28 -> 308
    //   283: iconst_1
    //   284: istore_3
    //   285: iload_2
    //   286: ifeq +27 -> 313
    //   289: iload_3
    //   290: ifeq +23 -> 313
    //   293: aload 10
    //   295: getfield 90	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   298: astore 8
    //   300: goto -132 -> 168
    //   303: iconst_0
    //   304: istore_2
    //   305: goto -42 -> 263
    //   308: iconst_0
    //   309: istore_3
    //   310: goto -25 -> 285
    //   313: iload_1
    //   314: iconst_1
    //   315: iadd
    //   316: istore_1
    //   317: goto -117 -> 200
    //   320: aconst_null
    //   321: astore 8
    //   323: goto -155 -> 168
    //   326: ldc 15
    //   328: iconst_1
    //   329: anewarray 113	java/lang/Object
    //   332: dup
    //   333: iconst_0
    //   334: aload 8
    //   336: aastore
    //   337: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   340: astore 8
    //   342: aload_0
    //   343: invokevirtual 121	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   346: astore_0
    //   347: aload_0
    //   348: aload 8
    //   350: invokestatic 127	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   353: iconst_1
    //   354: anewarray 76	java/lang/String
    //   357: dup
    //   358: iconst_0
    //   359: ldc -127
    //   361: aastore
    //   362: aconst_null
    //   363: aconst_null
    //   364: aconst_null
    //   365: invokevirtual 135	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   368: astore 8
    //   370: iload 7
    //   372: istore 5
    //   374: aload 8
    //   376: ifnull +58 -> 434
    //   379: iload 7
    //   381: istore 5
    //   383: aload 8
    //   385: astore_0
    //   386: aload 8
    //   388: invokeinterface 140 1 0
    //   393: ifeq +41 -> 434
    //   396: aload 8
    //   398: astore_0
    //   399: aload 8
    //   401: iconst_0
    //   402: invokeinterface 144 2 0
    //   407: astore 9
    //   409: aload 9
    //   411: ifnull +51 -> 462
    //   414: aload 8
    //   416: astore_0
    //   417: aload 9
    //   419: ldc 8
    //   421: invokevirtual 148	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   424: istore 5
    //   426: iload 5
    //   428: ifeq +34 -> 462
    //   431: iconst_1
    //   432: istore 5
    //   434: iload 5
    //   436: istore 6
    //   438: aload 8
    //   440: ifnull -267 -> 173
    //   443: aload 8
    //   445: invokeinterface 151 1 0
    //   450: iload 5
    //   452: ireturn
    //   453: astore_0
    //   454: aload_0
    //   455: invokevirtual 154	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   458: pop
    //   459: iload 5
    //   461: ireturn
    //   462: aload 8
    //   464: astore_0
    //   465: aload 8
    //   467: invokeinterface 157 1 0
    //   472: istore 5
    //   474: iload 5
    //   476: ifne -80 -> 396
    //   479: iload 7
    //   481: istore 5
    //   483: goto -49 -> 434
    //   486: astore 9
    //   488: aconst_null
    //   489: astore 8
    //   491: aload 8
    //   493: astore_0
    //   494: aload 9
    //   496: invokevirtual 154	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   499: pop
    //   500: aload 8
    //   502: ifnull -329 -> 173
    //   505: aload 8
    //   507: invokeinterface 151 1 0
    //   512: iconst_0
    //   513: ireturn
    //   514: astore_0
    //   515: aload_0
    //   516: invokevirtual 154	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   519: pop
    //   520: iconst_0
    //   521: ireturn
    //   522: astore 8
    //   524: aconst_null
    //   525: astore_0
    //   526: aload_0
    //   527: ifnull +9 -> 536
    //   530: aload_0
    //   531: invokeinterface 151 1 0
    //   536: aload 8
    //   538: athrow
    //   539: astore_0
    //   540: aload_0
    //   541: invokevirtual 154	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   544: pop
    //   545: goto -9 -> 536
    //   548: astore 8
    //   550: goto -24 -> 526
    //   553: astore 9
    //   555: goto -64 -> 491
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	558	0	paramContext	Context
    //   67	250	1	i	int
    //   130	175	2	j	int
    //   152	158	3	k	int
    //   64	140	4	m	int
    //   372	110	5	bool1	boolean
    //   4	433	6	bool2	boolean
    //   1	479	7	bool3	boolean
    //   15	491	8	localObject1	Object
    //   522	15	8	localObject2	Object
    //   548	1	8	localObject3	Object
    //   54	364	9	localObject4	Object
    //   486	9	9	localException1	Exception
    //   553	1	9	localException2	Exception
    //   78	216	10	localObject5	Object
    //   90	162	11	str1	String
    //   97	177	12	str2	String
    // Exception table:
    //   from	to	target	type
    //   443	450	453	java/lang/Exception
    //   347	370	486	java/lang/Exception
    //   505	512	514	java/lang/Exception
    //   347	370	522	finally
    //   530	536	539	java/lang/Exception
    //   386	396	548	finally
    //   399	409	548	finally
    //   417	426	548	finally
    //   465	474	548	finally
    //   494	500	548	finally
    //   386	396	553	java/lang/Exception
    //   399	409	553	java/lang/Exception
    //   417	426	553	java/lang/Exception
    //   465	474	553	java/lang/Exception
  }
  
  private static Intent c(Context paramContext)
  {
    Intent localIntent1 = new Intent(paramContext.getApplicationContext(), CortanaWidgetActivity.class);
    localIntent1.setAction("android.intent.action.widget.start");
    localIntent1.addFlags(335593472);
    Intent localIntent2 = new Intent();
    localIntent2.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
    localIntent2.putExtra("android.intent.extra.shortcut.NAME", paramContext.getResources().getString(2131165920));
    localIntent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, 2130837953));
    localIntent2.putExtra("duplicate", false);
    return localIntent2;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setResult(-1, c(getApplicationContext()));
    finish();
  }
}
