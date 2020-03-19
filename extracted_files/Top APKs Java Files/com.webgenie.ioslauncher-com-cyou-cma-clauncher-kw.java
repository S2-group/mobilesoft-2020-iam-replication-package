package com.cyou.cma.clauncher;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.cyou.cma.bg;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class kw
  implements Runnable
{
  boolean a;
  private Context c;
  private Thread d;
  private boolean e;
  private boolean f;
  private boolean g;
  private boolean h = false;
  private boolean i = false;
  
  kw(jz paramJz, Context paramContext, boolean paramBoolean)
  {
    this.c = paramContext;
    this.e = paramBoolean;
  }
  
  kw(jz paramJz, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this(paramJz, paramContext, paramBoolean1);
    this.a = paramBoolean4;
  }
  
  private void a(Context paramContext)
  {
    Object localObject = paramContext.getFileStreamPath("flag_all_app");
    boolean bool = ((File)localObject).exists();
    if (bool) {
      new lb(this, (File)localObject).start();
    }
    if (!bool) {
      return;
    }
    jz.p(this.b);
    localObject = paramContext.getContentResolver();
    ((ContentResolver)localObject).delete(lw.a, null, null);
    paramContext = new Intent("android.intent.action.MAIN", null);
    paramContext.addCategory("android.intent.category.LAUNCHER");
    PackageManager localPackageManager = this.c.getPackageManager();
    List localList = localPackageManager.queryIntentActivities(paramContext, 0);
    ContentValues[] arrayOfContentValues = new ContentValues[localList.size()];
    int k = localList.size();
    int j = 0;
    if (j < k)
    {
      paramContext = (ResolveInfo)localList.get(j);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("package_name", paramContext.activityInfo.packageName);
      localContentValues.put("class_name", paramContext.activityInfo.name);
      paramContext = paramContext.loadLabel(localPackageManager);
      if (paramContext == null) {}
      for (paramContext = "";; paramContext = paramContext.toString())
      {
        localContentValues.put("title", bg.a(paramContext));
        localContentValues.put("app_index", Integer.valueOf(j));
        localContentValues.put("item_type", Integer.valueOf(0));
        localContentValues.put("container", Integer.valueOf(65336));
        localContentValues.put("hide", Integer.valueOf(0));
        arrayOfContentValues[j] = localContentValues;
        j += 1;
        break;
      }
    }
    ((ContentResolver)localObject).bulkInsert(lw.a, arrayOfContentValues);
    jz.j(this.b).e.c();
  }
  
  private static boolean a(gi[][][] paramArrayOfGi, gi paramGi)
  {
    int m = paramGi.t;
    if (paramGi.s == -101L)
    {
      if (paramArrayOfGi[Launcher.b][paramGi.u][0] != null)
      {
        Log.e("Launcher.Model", "Error loading shortcut into hotseat " + paramGi + " into position (" + paramGi.t + ":" + paramGi.u + "," + paramGi.v + ") occupied by " + paramArrayOfGi[Launcher.b][paramGi.u][0]);
        return false;
      }
      paramArrayOfGi[Launcher.b][paramGi.u][0] = paramGi;
      return true;
    }
    if (paramGi.s != -100L) {
      return true;
    }
    int j = paramGi.u;
    int k;
    while (j < paramGi.u + paramGi.w)
    {
      k = paramGi.v;
      while (k < paramGi.v + paramGi.x)
      {
        if (paramArrayOfGi[m][j][k] != null)
        {
          Log.e("Launcher.Model", "Error loading shortcut " + paramGi + " into cell (" + m + "-" + paramGi.t + ":" + j + "," + k + ") occupied by " + paramArrayOfGi[m][j][k]);
          return false;
        }
        k += 1;
      }
      j += 1;
    }
    j = paramGi.u;
    while (j < paramGi.u + paramGi.w)
    {
      k = paramGi.v;
      while (k < paramGi.v + paramGi.x)
      {
        paramArrayOfGi[m][j][k] = paramGi;
        k += 1;
      }
      j += 1;
    }
    return true;
  }
  
  /* Error */
  private void d()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   4: invokestatic 279	com/cyou/cma/clauncher/jz:b	(Lcom/cyou/cma/clauncher/jz;)Z
    //   7: ifne +1634 -> 1641
    //   10: aload_0
    //   11: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   14: invokestatic 282	com/cyou/cma/clauncher/jz:i	(Lcom/cyou/cma/clauncher/jz;)Lcom/cyou/cma/clauncher/ga;
    //   17: getstatic 287	com/cyou/cma/a/a:a	Lcom/cyou/cma/a/a;
    //   20: invokevirtual 292	com/cyou/cma/clauncher/ga:a	(Lcom/cyou/cma/a/a;)V
    //   23: invokestatic 298	android/os/SystemClock:uptimeMillis	()J
    //   26: lstore 19
    //   28: aload_0
    //   29: getfield 32	com/cyou/cma/clauncher/kw:c	Landroid/content/Context;
    //   32: astore 29
    //   34: aload 29
    //   36: invokevirtual 73	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   39: astore 28
    //   41: aload 29
    //   43: invokevirtual 101	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: astore 30
    //   48: aload 29
    //   50: invokestatic 304	android/appwidget/AppWidgetManager:getInstance	(Landroid/content/Context;)Landroid/appwidget/AppWidgetManager;
    //   53: astore 31
    //   55: aload 30
    //   57: invokevirtual 307	android/content/pm/PackageManager:isSafeMode	()Z
    //   60: istore 23
    //   62: getstatic 310	com/cyou/cma/clauncher/jz:d	Ljava/util/ArrayList;
    //   65: invokevirtual 315	java/util/ArrayList:clear	()V
    //   68: getstatic 317	com/cyou/cma/clauncher/jz:e	Ljava/util/ArrayList;
    //   71: invokevirtual 315	java/util/ArrayList:clear	()V
    //   74: getstatic 320	com/cyou/cma/clauncher/jz:g	Ljava/util/HashMap;
    //   77: invokevirtual 323	java/util/HashMap:clear	()V
    //   80: getstatic 325	com/cyou/cma/clauncher/jz:c	Ljava/util/HashMap;
    //   83: invokevirtual 323	java/util/HashMap:clear	()V
    //   86: getstatic 327	com/cyou/cma/clauncher/jz:h	Ljava/util/HashMap;
    //   89: invokevirtual 323	java/util/HashMap:clear	()V
    //   92: new 312	java/util/ArrayList
    //   95: dup
    //   96: invokespecial 328	java/util/ArrayList:<init>	()V
    //   99: astore 26
    //   101: aload 28
    //   103: getstatic 331	com/cyou/cma/clauncher/ly:a	Landroid/net/Uri;
    //   106: aconst_null
    //   107: aconst_null
    //   108: aconst_null
    //   109: aconst_null
    //   110: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   113: astore 27
    //   115: ldc -54
    //   117: iconst_3
    //   118: newarray int
    //   120: dup
    //   121: iconst_0
    //   122: getstatic 216	com/cyou/cma/clauncher/Launcher:b	I
    //   125: iconst_1
    //   126: iadd
    //   127: iastore
    //   128: dup
    //   129: iconst_1
    //   130: invokestatic 338	com/cyou/cma/clauncher/jz:k	()I
    //   133: iconst_1
    //   134: iadd
    //   135: iastore
    //   136: dup
    //   137: iconst_2
    //   138: invokestatic 341	com/cyou/cma/clauncher/jz:l	()I
    //   141: iconst_1
    //   142: iadd
    //   143: iastore
    //   144: invokestatic 347	java/lang/reflect/Array:newInstance	(Ljava/lang/Class;[I)Ljava/lang/Object;
    //   147: checkcast 349	[[[Lcom/cyou/cma/clauncher/gi;
    //   150: astore 32
    //   152: aload 27
    //   154: ldc_w 351
    //   157: invokeinterface 357 2 0
    //   162: istore_1
    //   163: aload 27
    //   165: ldc_w 359
    //   168: invokeinterface 357 2 0
    //   173: istore_2
    //   174: aload 27
    //   176: ldc -105
    //   178: invokeinterface 357 2 0
    //   183: istore_3
    //   184: aload 27
    //   186: ldc_w 361
    //   189: invokeinterface 357 2 0
    //   194: istore 4
    //   196: aload 27
    //   198: ldc_w 363
    //   201: invokeinterface 357 2 0
    //   206: istore 5
    //   208: aload 27
    //   210: ldc_w 365
    //   213: invokeinterface 357 2 0
    //   218: istore 6
    //   220: aload 27
    //   222: ldc_w 367
    //   225: invokeinterface 357 2 0
    //   230: istore 7
    //   232: aload 27
    //   234: ldc -85
    //   236: invokeinterface 357 2 0
    //   241: istore 8
    //   243: aload 27
    //   245: ldc_w 369
    //   248: invokeinterface 357 2 0
    //   253: istore 9
    //   255: aload 27
    //   257: ldc_w 371
    //   260: invokeinterface 357 2 0
    //   265: istore 10
    //   267: aload 27
    //   269: ldc_w 373
    //   272: invokeinterface 357 2 0
    //   277: istore 11
    //   279: aload 27
    //   281: ldc_w 375
    //   284: invokeinterface 357 2 0
    //   289: istore 12
    //   291: aload 27
    //   293: ldc_w 377
    //   296: invokeinterface 357 2 0
    //   301: istore 13
    //   303: aload 27
    //   305: ldc_w 379
    //   308: invokeinterface 357 2 0
    //   313: istore 14
    //   315: aload 27
    //   317: ldc_w 381
    //   320: invokeinterface 357 2 0
    //   325: istore 15
    //   327: aload 27
    //   329: ldc_w 383
    //   332: invokeinterface 357 2 0
    //   337: pop
    //   338: aload 27
    //   340: ldc_w 385
    //   343: invokeinterface 357 2 0
    //   348: pop
    //   349: aload 27
    //   351: ldc_w 387
    //   354: invokeinterface 357 2 0
    //   359: istore 16
    //   361: aload 27
    //   363: ldc_w 389
    //   366: invokeinterface 357 2 0
    //   371: istore 17
    //   373: aload_0
    //   374: getfield 391	com/cyou/cma/clauncher/kw:f	Z
    //   377: ifne +1193 -> 1570
    //   380: aload 27
    //   382: invokeinterface 394 1 0
    //   387: istore 24
    //   389: iload 24
    //   391: ifeq +1179 -> 1570
    //   394: aload 27
    //   396: iload 9
    //   398: invokeinterface 398 2 0
    //   403: istore 18
    //   405: aload 27
    //   407: iload 17
    //   409: invokeinterface 402 2 0
    //   414: astore 33
    //   416: iload 18
    //   418: tableswitch	default:+1240->1658, 0:+38->456, 1:+38->456, 2:+526->944, 3:+1240->1658, 4:+723->1141, 5:+1023->1441
    //   456: aload 27
    //   458: iload_2
    //   459: invokeinterface 402 2 0
    //   464: astore 25
    //   466: aload 25
    //   468: iconst_0
    //   469: invokestatic 406	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   472: astore 34
    //   474: iload 18
    //   476: ifne +356 -> 832
    //   479: aload 33
    //   481: ifnull +326 -> 807
    //   484: aload_0
    //   485: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   488: aload 30
    //   490: aload 34
    //   492: aload 29
    //   494: aload 27
    //   496: iload 4
    //   498: iload 5
    //   500: iload_3
    //   501: aload 33
    //   503: invokevirtual 409	com/cyou/cma/clauncher/jz:a	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;IIILjava/lang/String;)Lcom/cyou/cma/clauncher/of;
    //   506: astore 25
    //   508: aload 25
    //   510: aload 34
    //   512: putfield 414	com/cyou/cma/clauncher/of:b	Landroid/content/Intent;
    //   515: aload 25
    //   517: ifnull +371 -> 888
    //   520: aload 25
    //   522: aload 27
    //   524: iload_1
    //   525: invokeinterface 418 2 0
    //   530: putfield 421	com/cyou/cma/clauncher/of:q	J
    //   533: aload 27
    //   535: iload 8
    //   537: invokeinterface 398 2 0
    //   542: istore 18
    //   544: aload 25
    //   546: iload 18
    //   548: i2l
    //   549: putfield 422	com/cyou/cma/clauncher/of:s	J
    //   552: aload 25
    //   554: aload 27
    //   556: iload 11
    //   558: invokeinterface 398 2 0
    //   563: putfield 423	com/cyou/cma/clauncher/of:t	I
    //   566: aload 25
    //   568: aload 27
    //   570: iload 12
    //   572: invokeinterface 398 2 0
    //   577: putfield 424	com/cyou/cma/clauncher/of:u	I
    //   580: aload 25
    //   582: aload 27
    //   584: iload 13
    //   586: invokeinterface 398 2 0
    //   591: putfield 425	com/cyou/cma/clauncher/of:v	I
    //   594: aload 25
    //   596: aload 33
    //   598: putfield 427	com/cyou/cma/clauncher/of:j	Ljava/lang/String;
    //   601: aload 32
    //   603: aload 25
    //   605: invokestatic 429	com/cyou/cma/clauncher/kw:a	([[[Lcom/cyou/cma/clauncher/gi;Lcom/cyou/cma/clauncher/gi;)Z
    //   608: ifeq -235 -> 373
    //   611: iload 18
    //   613: tableswitch	default:+1048->1661, -101:+263->876, -100:+263->876
    //   636: getstatic 320	com/cyou/cma/clauncher/jz:g	Ljava/util/HashMap;
    //   639: iload 18
    //   641: i2l
    //   642: invokestatic 432	com/cyou/cma/clauncher/jz:a	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/fh;
    //   645: aload 25
    //   647: invokevirtual 437	com/cyou/cma/clauncher/fh:d	(Lcom/cyou/cma/clauncher/gi;)V
    //   650: getstatic 325	com/cyou/cma/clauncher/jz:c	Ljava/util/HashMap;
    //   653: aload 25
    //   655: getfield 421	com/cyou/cma/clauncher/of:q	J
    //   658: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   661: aload 25
    //   663: invokevirtual 445	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   666: pop
    //   667: aload_0
    //   668: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   671: getstatic 327	com/cyou/cma/clauncher/jz:h	Ljava/util/HashMap;
    //   674: aload 25
    //   676: aload 27
    //   678: iload 5
    //   680: invokevirtual 448	com/cyou/cma/clauncher/jz:a	(Ljava/util/HashMap;Lcom/cyou/cma/clauncher/of;Landroid/database/Cursor;I)Z
    //   683: pop
    //   684: goto -311 -> 373
    //   687: astore 25
    //   689: ldc -35
    //   691: ldc_w 450
    //   694: aload 25
    //   696: invokestatic 453	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   699: pop
    //   700: goto -327 -> 373
    //   703: astore 25
    //   705: aload 27
    //   707: invokeinterface 456 1 0
    //   712: aload 26
    //   714: invokevirtual 457	java/util/ArrayList:size	()I
    //   717: ifle +863 -> 1580
    //   720: aload 28
    //   722: getstatic 331	com/cyou/cma/clauncher/ly:a	Landroid/net/Uri;
    //   725: invokevirtual 461	android/content/ContentResolver:acquireContentProviderClient	(Landroid/net/Uri;)Landroid/content/ContentProviderClient;
    //   728: astore 25
    //   730: aload 26
    //   732: invokevirtual 465	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   735: astore 26
    //   737: aload 26
    //   739: invokeinterface 470 1 0
    //   744: ifeq +836 -> 1580
    //   747: aload 26
    //   749: invokeinterface 474 1 0
    //   754: checkcast 439	java/lang/Long
    //   757: invokevirtual 477	java/lang/Long:longValue	()J
    //   760: lstore 21
    //   762: aload 25
    //   764: lload 21
    //   766: invokestatic 480	com/cyou/cma/clauncher/ly:a	(J)Landroid/net/Uri;
    //   769: aconst_null
    //   770: aconst_null
    //   771: invokevirtual 483	android/content/ContentProviderClient:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   774: pop
    //   775: goto -38 -> 737
    //   778: astore 27
    //   780: ldc -35
    //   782: new 223	java/lang/StringBuilder
    //   785: dup
    //   786: ldc_w 485
    //   789: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   792: lload 21
    //   794: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   797: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   800: invokestatic 490	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   803: pop
    //   804: goto -67 -> 737
    //   807: aload_0
    //   808: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   811: aload 30
    //   813: aload 34
    //   815: aload 29
    //   817: aload 27
    //   819: iload 4
    //   821: iload 5
    //   823: iload_3
    //   824: invokevirtual 493	com/cyou/cma/clauncher/jz:a	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;III)Lcom/cyou/cma/clauncher/of;
    //   827: astore 25
    //   829: goto -321 -> 508
    //   832: aload_0
    //   833: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   836: aload 27
    //   838: aload 29
    //   840: iload 4
    //   842: iload 6
    //   844: iload 7
    //   846: iload 5
    //   848: iload_3
    //   849: invokestatic 496	com/cyou/cma/clauncher/jz:a	(Lcom/cyou/cma/clauncher/jz;Landroid/database/Cursor;Landroid/content/Context;IIIII)Lcom/cyou/cma/clauncher/of;
    //   852: astore 25
    //   854: aload 25
    //   856: aload 34
    //   858: putfield 414	com/cyou/cma/clauncher/of:b	Landroid/content/Intent;
    //   861: goto -346 -> 515
    //   864: astore 25
    //   866: aload 27
    //   868: invokeinterface 456 1 0
    //   873: aload 25
    //   875: athrow
    //   876: getstatic 310	com/cyou/cma/clauncher/jz:d	Ljava/util/ArrayList;
    //   879: aload 25
    //   881: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   884: pop
    //   885: goto -235 -> 650
    //   888: aload 27
    //   890: iload_1
    //   891: invokeinterface 418 2 0
    //   896: lstore 21
    //   898: ldc -35
    //   900: new 223	java/lang/StringBuilder
    //   903: dup
    //   904: ldc_w 265
    //   907: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   910: lload 21
    //   912: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   915: ldc_w 502
    //   918: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   921: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   924: invokestatic 255	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   927: pop
    //   928: aload 28
    //   930: lload 21
    //   932: invokestatic 480	com/cyou/cma/clauncher/ly:a	(J)Landroid/net/Uri;
    //   935: aconst_null
    //   936: aconst_null
    //   937: invokevirtual 84	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   940: pop
    //   941: goto -568 -> 373
    //   944: aload 27
    //   946: iload_1
    //   947: invokeinterface 418 2 0
    //   952: lstore 21
    //   954: getstatic 320	com/cyou/cma/clauncher/jz:g	Ljava/util/HashMap;
    //   957: lload 21
    //   959: invokestatic 432	com/cyou/cma/clauncher/jz:a	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/fh;
    //   962: astore 25
    //   964: aload 25
    //   966: aload 27
    //   968: iload_3
    //   969: invokeinterface 402 2 0
    //   974: putfield 505	com/cyou/cma/clauncher/fh:l	Ljava/lang/CharSequence;
    //   977: aload 25
    //   979: lload 21
    //   981: putfield 506	com/cyou/cma/clauncher/fh:q	J
    //   984: aload 27
    //   986: iload 8
    //   988: invokeinterface 398 2 0
    //   993: istore 18
    //   995: aload 25
    //   997: iload 18
    //   999: i2l
    //   1000: putfield 507	com/cyou/cma/clauncher/fh:s	J
    //   1003: aload 25
    //   1005: aload 27
    //   1007: iload 11
    //   1009: invokeinterface 398 2 0
    //   1014: putfield 508	com/cyou/cma/clauncher/fh:t	I
    //   1017: aload 25
    //   1019: aload 27
    //   1021: iload 12
    //   1023: invokeinterface 398 2 0
    //   1028: putfield 509	com/cyou/cma/clauncher/fh:u	I
    //   1031: aload 25
    //   1033: aload 27
    //   1035: iload 13
    //   1037: invokeinterface 398 2 0
    //   1042: putfield 510	com/cyou/cma/clauncher/fh:v	I
    //   1045: aload 25
    //   1047: aload 27
    //   1049: iload 16
    //   1051: invokeinterface 402 2 0
    //   1056: putfield 513	com/cyou/cma/clauncher/fh:B	Ljava/lang/String;
    //   1059: aload 32
    //   1061: aload 25
    //   1063: invokestatic 429	com/cyou/cma/clauncher/kw:a	([[[Lcom/cyou/cma/clauncher/gi;Lcom/cyou/cma/clauncher/gi;)Z
    //   1066: ifeq -693 -> 373
    //   1069: iload 18
    //   1071: tableswitch	default:+593->1664, -101:+58->1129, -100:+58->1129
    //   1092: getstatic 325	com/cyou/cma/clauncher/jz:c	Ljava/util/HashMap;
    //   1095: aload 25
    //   1097: getfield 506	com/cyou/cma/clauncher/fh:q	J
    //   1100: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1103: aload 25
    //   1105: invokevirtual 445	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1108: pop
    //   1109: getstatic 320	com/cyou/cma/clauncher/jz:g	Ljava/util/HashMap;
    //   1112: aload 25
    //   1114: getfield 506	com/cyou/cma/clauncher/fh:q	J
    //   1117: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1120: aload 25
    //   1122: invokevirtual 445	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1125: pop
    //   1126: goto -753 -> 373
    //   1129: getstatic 310	com/cyou/cma/clauncher/jz:d	Ljava/util/ArrayList;
    //   1132: aload 25
    //   1134: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1137: pop
    //   1138: goto -46 -> 1092
    //   1141: aload 27
    //   1143: iload 10
    //   1145: invokeinterface 398 2 0
    //   1150: istore 18
    //   1152: aload 27
    //   1154: iload_1
    //   1155: invokeinterface 418 2 0
    //   1160: lstore 21
    //   1162: aload 31
    //   1164: iload 18
    //   1166: invokevirtual 517	android/appwidget/AppWidgetManager:getAppWidgetInfo	(I)Landroid/appwidget/AppWidgetProviderInfo;
    //   1169: astore 25
    //   1171: iload 23
    //   1173: ifne +89 -> 1262
    //   1176: aload 25
    //   1178: ifnull +22 -> 1200
    //   1181: aload 25
    //   1183: getfield 523	android/appwidget/AppWidgetProviderInfo:provider	Landroid/content/ComponentName;
    //   1186: ifnull +14 -> 1200
    //   1189: aload 25
    //   1191: getfield 523	android/appwidget/AppWidgetProviderInfo:provider	Landroid/content/ComponentName;
    //   1194: invokevirtual 528	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   1197: ifnonnull +65 -> 1262
    //   1200: new 223	java/lang/StringBuilder
    //   1203: dup
    //   1204: ldc_w 530
    //   1207: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1210: lload 21
    //   1212: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1215: ldc_w 532
    //   1218: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1221: iload 18
    //   1223: invokevirtual 240	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1226: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1229: astore 25
    //   1231: ldc -35
    //   1233: aload 25
    //   1235: invokestatic 255	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1238: pop
    //   1239: getstatic 535	com/cyou/cma/clauncher/Launcher:n	Ljava/util/ArrayList;
    //   1242: aload 25
    //   1244: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1247: pop
    //   1248: aload 26
    //   1250: lload 21
    //   1252: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1255: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1258: pop
    //   1259: goto -886 -> 373
    //   1262: new 537	com/cyou/cma/clauncher/js
    //   1265: dup
    //   1266: iload 18
    //   1268: invokespecial 540	com/cyou/cma/clauncher/js:<init>	(I)V
    //   1271: astore 25
    //   1273: aload 25
    //   1275: lload 21
    //   1277: putfield 541	com/cyou/cma/clauncher/js:q	J
    //   1280: aload 25
    //   1282: aload 27
    //   1284: iload 11
    //   1286: invokeinterface 398 2 0
    //   1291: putfield 542	com/cyou/cma/clauncher/js:t	I
    //   1294: aload 25
    //   1296: aload 27
    //   1298: iload 12
    //   1300: invokeinterface 398 2 0
    //   1305: putfield 543	com/cyou/cma/clauncher/js:u	I
    //   1308: aload 25
    //   1310: aload 27
    //   1312: iload 13
    //   1314: invokeinterface 398 2 0
    //   1319: putfield 544	com/cyou/cma/clauncher/js:v	I
    //   1322: aload 25
    //   1324: aload 27
    //   1326: iload 14
    //   1328: invokeinterface 398 2 0
    //   1333: putfield 545	com/cyou/cma/clauncher/js:w	I
    //   1336: aload 25
    //   1338: aload 27
    //   1340: iload 15
    //   1342: invokeinterface 398 2 0
    //   1347: putfield 546	com/cyou/cma/clauncher/js:x	I
    //   1350: aload 27
    //   1352: iload 8
    //   1354: invokeinterface 398 2 0
    //   1359: istore 18
    //   1361: iload 18
    //   1363: bipush -100
    //   1365: if_icmpeq +22 -> 1387
    //   1368: iload 18
    //   1370: bipush -101
    //   1372: if_icmpeq +15 -> 1387
    //   1375: ldc -35
    //   1377: ldc_w 548
    //   1380: invokestatic 255	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1383: pop
    //   1384: goto -1011 -> 373
    //   1387: aload 25
    //   1389: aload 27
    //   1391: iload 8
    //   1393: invokeinterface 398 2 0
    //   1398: i2l
    //   1399: putfield 549	com/cyou/cma/clauncher/js:s	J
    //   1402: aload 32
    //   1404: aload 25
    //   1406: invokestatic 429	com/cyou/cma/clauncher/kw:a	([[[Lcom/cyou/cma/clauncher/gi;Lcom/cyou/cma/clauncher/gi;)Z
    //   1409: ifeq -1036 -> 373
    //   1412: getstatic 325	com/cyou/cma/clauncher/jz:c	Ljava/util/HashMap;
    //   1415: aload 25
    //   1417: getfield 541	com/cyou/cma/clauncher/js:q	J
    //   1420: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1423: aload 25
    //   1425: invokevirtual 445	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1428: pop
    //   1429: getstatic 317	com/cyou/cma/clauncher/jz:e	Ljava/util/ArrayList;
    //   1432: aload 25
    //   1434: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1437: pop
    //   1438: goto -1065 -> 373
    //   1441: aload 27
    //   1443: iload_2
    //   1444: invokeinterface 402 2 0
    //   1449: invokestatic 555	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1452: iconst_0
    //   1453: anewarray 551	java/lang/Class
    //   1456: invokevirtual 559	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   1459: iconst_0
    //   1460: anewarray 4	java/lang/Object
    //   1463: invokevirtual 564	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   1466: checkcast 566	com/cyou/cma/clauncher/cl
    //   1469: astore 25
    //   1471: aload 25
    //   1473: aload 27
    //   1475: iload_1
    //   1476: invokeinterface 418 2 0
    //   1481: putfield 567	com/cyou/cma/clauncher/cl:q	J
    //   1484: aload 25
    //   1486: aload 27
    //   1488: iload 12
    //   1490: invokeinterface 398 2 0
    //   1495: putfield 568	com/cyou/cma/clauncher/cl:u	I
    //   1498: aload 25
    //   1500: aload 27
    //   1502: iload 13
    //   1504: invokeinterface 398 2 0
    //   1509: putfield 569	com/cyou/cma/clauncher/cl:v	I
    //   1512: aload 25
    //   1514: aload 27
    //   1516: iload 11
    //   1518: invokeinterface 398 2 0
    //   1523: putfield 570	com/cyou/cma/clauncher/cl:t	I
    //   1526: aload 25
    //   1528: aload 27
    //   1530: iload 8
    //   1532: invokeinterface 398 2 0
    //   1537: i2l
    //   1538: putfield 571	com/cyou/cma/clauncher/cl:s	J
    //   1541: getstatic 325	com/cyou/cma/clauncher/jz:c	Ljava/util/HashMap;
    //   1544: aload 25
    //   1546: getfield 567	com/cyou/cma/clauncher/cl:q	J
    //   1549: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1552: aload 25
    //   1554: invokevirtual 445	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1557: pop
    //   1558: getstatic 310	com/cyou/cma/clauncher/jz:d	Ljava/util/ArrayList;
    //   1561: aload 25
    //   1563: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1566: pop
    //   1567: goto -1194 -> 373
    //   1570: aload 27
    //   1572: invokeinterface 456 1 0
    //   1577: goto -865 -> 712
    //   1580: ldc_w 573
    //   1583: new 223	java/lang/StringBuilder
    //   1586: dup
    //   1587: ldc_w 575
    //   1590: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1593: invokestatic 298	android/os/SystemClock:uptimeMillis	()J
    //   1596: lload 19
    //   1598: lsub
    //   1599: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1602: ldc_w 577
    //   1605: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 579	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1614: pop
    //   1615: aload_0
    //   1616: monitorenter
    //   1617: aload_0
    //   1618: getfield 391	com/cyou/cma/clauncher/kw:f	Z
    //   1621: ifeq +10 -> 1631
    //   1624: aload_0
    //   1625: invokevirtual 582	java/lang/Object:notify	()V
    //   1628: aload_0
    //   1629: monitorexit
    //   1630: return
    //   1631: aload_0
    //   1632: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   1635: invokestatic 584	com/cyou/cma/clauncher/jz:c	(Lcom/cyou/cma/clauncher/jz;)Z
    //   1638: pop
    //   1639: aload_0
    //   1640: monitorexit
    //   1641: aload_0
    //   1642: invokespecial 199	com/cyou/cma/clauncher/kw:e	()V
    //   1645: return
    //   1646: astore 25
    //   1648: aload_0
    //   1649: monitorexit
    //   1650: aload 25
    //   1652: athrow
    //   1653: astore 25
    //   1655: goto -1282 -> 373
    //   1658: goto -1285 -> 373
    //   1661: goto -1025 -> 636
    //   1664: goto -572 -> 1092
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1667	0	this	kw
    //   162	1314	1	j	int
    //   173	1271	2	k	int
    //   183	786	3	m	int
    //   194	647	4	n	int
    //   206	641	5	i1	int
    //   218	625	6	i2	int
    //   230	615	7	i3	int
    //   241	1290	8	i4	int
    //   253	144	9	i5	int
    //   265	879	10	i6	int
    //   277	1240	11	i7	int
    //   289	1200	12	i8	int
    //   301	1202	13	i9	int
    //   313	1014	14	i10	int
    //   325	1016	15	i11	int
    //   359	691	16	i12	int
    //   371	37	17	i13	int
    //   403	970	18	i14	int
    //   26	1571	19	l1	long
    //   760	516	21	l2	long
    //   60	1112	23	bool1	boolean
    //   387	3	24	bool2	boolean
    //   464	211	25	localObject1	Object
    //   687	8	25	localException1	Exception
    //   703	1	25	localException2	Exception
    //   728	127	25	localObject2	Object
    //   864	16	25	localObject3	Object
    //   962	600	25	localObject4	Object
    //   1646	5	25	localObject5	Object
    //   1653	1	25	localURISyntaxException	java.net.URISyntaxException
    //   99	1150	26	localObject6	Object
    //   113	593	27	localCursor	android.database.Cursor
    //   778	793	27	localRemoteException	android.os.RemoteException
    //   39	890	28	localContentResolver	ContentResolver
    //   32	807	29	localContext	Context
    //   46	766	30	localPackageManager	PackageManager
    //   53	1110	31	localAppWidgetManager	android.appwidget.AppWidgetManager
    //   150	1253	32	arrayOfGi	gi[][][]
    //   414	183	33	str	String
    //   472	385	34	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   394	416	687	java/lang/Exception
    //   456	466	687	java/lang/Exception
    //   466	474	687	java/lang/Exception
    //   484	508	687	java/lang/Exception
    //   508	515	687	java/lang/Exception
    //   520	611	687	java/lang/Exception
    //   636	650	687	java/lang/Exception
    //   650	684	687	java/lang/Exception
    //   807	829	687	java/lang/Exception
    //   832	861	687	java/lang/Exception
    //   876	885	687	java/lang/Exception
    //   888	941	687	java/lang/Exception
    //   944	1069	687	java/lang/Exception
    //   1092	1126	687	java/lang/Exception
    //   1129	1138	687	java/lang/Exception
    //   1141	1171	687	java/lang/Exception
    //   1181	1200	687	java/lang/Exception
    //   1200	1259	687	java/lang/Exception
    //   1262	1361	687	java/lang/Exception
    //   1375	1384	687	java/lang/Exception
    //   1387	1438	687	java/lang/Exception
    //   1441	1567	687	java/lang/Exception
    //   152	373	703	java/lang/Exception
    //   373	389	703	java/lang/Exception
    //   689	700	703	java/lang/Exception
    //   762	775	778	android/os/RemoteException
    //   152	373	864	finally
    //   373	389	864	finally
    //   394	416	864	finally
    //   456	466	864	finally
    //   466	474	864	finally
    //   484	508	864	finally
    //   508	515	864	finally
    //   520	611	864	finally
    //   636	650	864	finally
    //   650	684	864	finally
    //   689	700	864	finally
    //   807	829	864	finally
    //   832	861	864	finally
    //   876	885	864	finally
    //   888	941	864	finally
    //   944	1069	864	finally
    //   1092	1126	864	finally
    //   1129	1138	864	finally
    //   1141	1171	864	finally
    //   1181	1200	864	finally
    //   1200	1259	864	finally
    //   1262	1361	864	finally
    //   1375	1384	864	finally
    //   1387	1438	864	finally
    //   1441	1567	864	finally
    //   1617	1630	1646	finally
    //   1631	1641	1646	finally
    //   1648	1650	1646	finally
    //   466	474	1653	java/net/URISyntaxException
  }
  
  private void e()
  {
    try
    {
      String str = jz.j(this.b).getPackageName();
      if ((str.contains("com.webgenie.")) && (str.endsWith(".ioslauncher")))
      {
        j = str.length();
        if (j == 24) {}
      }
      else
      {
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      long l = SystemClock.uptimeMillis();
      jz.a(this.b, false);
      ks localKs = (ks)jz.e(this.b).get();
      if (localKs == null)
      {
        Log.w("Launcher.Model", "LoaderTask running with no launcher");
        return;
      }
      if (localKs.e())
      {
        jz.k(this.b);
        return;
      }
      jz.d(this.b).a(new lc(this, localKs));
      int m = localKs.G();
      Object localObject3 = jz.a(this.b);
      Object localObject2 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.7D));
      Object localObject1 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.3D));
      ArrayList localArrayList = new ArrayList(5);
      localObject3 = ((ArrayList)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        gi localGi = (gi)((Iterator)localObject3).next();
        if (localGi.s == -101L) {
          localArrayList.add(localGi);
        } else if (localGi.t == m) {
          ((ArrayList)localObject2).add(localGi);
        } else {
          ((ArrayList)localObject1).add(localGi);
        }
      }
      jz.d(this.b).a(new ld(this, localKs, localArrayList));
      int n = ((ArrayList)localObject2).size();
      int j = 0;
      if (j < n)
      {
        if (j + 2 <= n) {}
        for (k = 2;; k = n - j)
        {
          jz.d(this.b).a(new le(this, localKs, (ArrayList)localObject2, j, k));
          j += 2;
          break;
        }
      }
      int k = jz.e.size();
      j = 0;
      while (j < k)
      {
        localObject2 = (js)jz.e.get(j);
        if (((js)localObject2).t == m) {
          jz.d(this.b).a(new lf(this, localKs, (js)localObject2));
        }
        j += 1;
      }
      jz.d(this.b).a(new lg(this));
      n = ((ArrayList)localObject1).size();
      j = 0;
      if (j < n)
      {
        if (j + 2 <= n) {}
        for (k = 2;; k = n - j)
        {
          jz.d(this.b).a(new lh(this, localKs, (ArrayList)localObject1, j, k));
          j += 2;
          break;
        }
      }
      k = jz.e.size();
      j = 0;
      while (j < k)
      {
        localObject1 = (js)jz.e.get(j);
        if (((js)localObject1).t != m) {
          jz.d(this.b).a(new li(this, localKs, (js)localObject1));
        }
        j += 1;
      }
      localObject1 = new HashMap(jz.g);
      jz.d(this.b).a(new lj(this, localKs, (HashMap)localObject1));
      jz.d(this.b).a(new kx(this, localKs));
      jz.d(this.b).a(new ky(this, l));
      jz.a(this.b, true);
    }
  }
  
  /* Error */
  private void f()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 30	com/cyou/cma/clauncher/kw:i	Z
    //   4: ifne +13 -> 17
    //   7: aload_0
    //   8: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   11: invokestatic 691	com/cyou/cma/clauncher/jz:l	(Lcom/cyou/cma/clauncher/jz;)Z
    //   14: ifne +960 -> 974
    //   17: invokestatic 298	android/os/SystemClock:uptimeMillis	()J
    //   20: lstore 4
    //   22: aload_0
    //   23: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   26: invokestatic 611	com/cyou/cma/clauncher/jz:e	(Lcom/cyou/cma/clauncher/jz;)Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 615	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 617	com/cyou/cma/clauncher/ks
    //   35: ifnull +900 -> 935
    //   38: new 86	android/content/Intent
    //   41: dup
    //   42: ldc 88
    //   44: aconst_null
    //   45: invokespecial 91	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   48: astore 12
    //   50: aload 12
    //   52: ldc 93
    //   54: invokevirtual 97	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   57: pop
    //   58: aload_0
    //   59: getfield 32	com/cyou/cma/clauncher/kw:c	Landroid/content/Context;
    //   62: invokevirtual 101	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   65: astore 13
    //   67: aload 13
    //   69: sipush 8192
    //   72: invokevirtual 695	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   75: astore 9
    //   77: aload_0
    //   78: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   81: aload 9
    //   83: invokeinterface 113 1 0
    //   88: invokestatic 698	com/cyou/cma/clauncher/jz:a	(Lcom/cyou/cma/clauncher/jz;I)I
    //   91: pop
    //   92: invokestatic 703	com/cyou/cma/clauncher/c:b	()Ljava/util/HashMap;
    //   95: astore 14
    //   97: invokestatic 706	com/cyou/cma/clauncher/c:a	()Ljava/util/Map;
    //   100: astore 15
    //   102: aload_0
    //   103: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   106: getfield 709	com/cyou/cma/clauncher/jz:b	Lcom/cyou/cma/clauncher/j;
    //   109: astore 9
    //   111: aload 9
    //   113: getfield 713	com/cyou/cma/clauncher/j:a	Ljava/util/ArrayList;
    //   116: invokevirtual 315	java/util/ArrayList:clear	()V
    //   119: aload 9
    //   121: getfield 715	com/cyou/cma/clauncher/j:b	Ljava/util/ArrayList;
    //   124: invokevirtual 315	java/util/ArrayList:clear	()V
    //   127: aload 9
    //   129: getfield 717	com/cyou/cma/clauncher/j:c	Ljava/util/ArrayList;
    //   132: invokevirtual 315	java/util/ArrayList:clear	()V
    //   135: aload 9
    //   137: getfield 718	com/cyou/cma/clauncher/j:d	Ljava/util/ArrayList;
    //   140: invokevirtual 315	java/util/ArrayList:clear	()V
    //   143: aload 14
    //   145: invokevirtual 323	java/util/HashMap:clear	()V
    //   148: aload 15
    //   150: invokeinterface 721 1 0
    //   155: new 312	java/util/ArrayList
    //   158: dup
    //   159: invokespecial 328	java/util/ArrayList:<init>	()V
    //   162: astore 16
    //   164: aload_0
    //   165: getfield 32	com/cyou/cma/clauncher/kw:c	Landroid/content/Context;
    //   168: invokevirtual 73	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   171: getstatic 78	com/cyou/cma/clauncher/lw:a	Landroid/net/Uri;
    //   174: bipush 11
    //   176: anewarray 589	java/lang/String
    //   179: dup
    //   180: iconst_0
    //   181: ldc_w 351
    //   184: aastore
    //   185: dup
    //   186: iconst_1
    //   187: ldc 124
    //   189: aastore
    //   190: dup
    //   191: iconst_2
    //   192: ldc -116
    //   194: aastore
    //   195: dup
    //   196: iconst_3
    //   197: ldc -83
    //   199: aastore
    //   200: dup
    //   201: iconst_4
    //   202: ldc -87
    //   204: aastore
    //   205: dup
    //   206: iconst_5
    //   207: ldc -105
    //   209: aastore
    //   210: dup
    //   211: bipush 6
    //   213: ldc -85
    //   215: aastore
    //   216: dup
    //   217: bipush 7
    //   219: ldc -98
    //   221: aastore
    //   222: dup
    //   223: bipush 8
    //   225: ldc_w 723
    //   228: aastore
    //   229: dup
    //   230: bipush 9
    //   232: ldc_w 725
    //   235: aastore
    //   236: dup
    //   237: bipush 10
    //   239: ldc_w 387
    //   242: aastore
    //   243: aconst_null
    //   244: aconst_null
    //   245: ldc_w 727
    //   248: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   251: astore 17
    //   253: aload 17
    //   255: invokeinterface 394 1 0
    //   260: ifeq +620 -> 880
    //   263: aload 17
    //   265: bipush 6
    //   267: invokeinterface 398 2 0
    //   272: istore_1
    //   273: aload 17
    //   275: iconst_4
    //   276: invokeinterface 398 2 0
    //   281: istore_2
    //   282: aload 17
    //   284: bipush 7
    //   286: invokeinterface 398 2 0
    //   291: istore_3
    //   292: aload 17
    //   294: iconst_0
    //   295: invokeinterface 418 2 0
    //   300: lstore 6
    //   302: aload 17
    //   304: bipush 10
    //   306: invokeinterface 402 2 0
    //   311: astore 11
    //   313: aload 17
    //   315: iconst_5
    //   316: invokeinterface 402 2 0
    //   321: astore 9
    //   323: iload_2
    //   324: ifne +414 -> 738
    //   327: aload 17
    //   329: iconst_1
    //   330: invokeinterface 402 2 0
    //   335: astore 10
    //   337: aload 17
    //   339: iconst_2
    //   340: invokeinterface 402 2 0
    //   345: astore 18
    //   347: aload 10
    //   349: aload 18
    //   351: invokestatic 730	com/cyou/cma/bg:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   354: ifne -101 -> 253
    //   357: aload 12
    //   359: new 525	android/content/ComponentName
    //   362: dup
    //   363: aload 10
    //   365: aload 18
    //   367: invokespecial 732	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   370: invokevirtual 736	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   373: pop
    //   374: aload 13
    //   376: aload 12
    //   378: iconst_0
    //   379: invokevirtual 740	android/content/pm/PackageManager:resolveActivity	(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   382: astore 11
    //   384: aload 11
    //   386: ifnonnull +234 -> 620
    //   389: new 742	com/cyou/cma/clauncher/o
    //   392: dup
    //   393: invokespecial 743	com/cyou/cma/clauncher/o:<init>	()V
    //   396: astore 11
    //   398: aload 11
    //   400: iconst_1
    //   401: putfield 744	com/cyou/cma/clauncher/o:h	Z
    //   404: aload 11
    //   406: iconst_1
    //   407: putfield 746	com/cyou/cma/clauncher/o:f	I
    //   410: aload 11
    //   412: new 525	android/content/ComponentName
    //   415: dup
    //   416: aload 10
    //   418: aload 18
    //   420: invokespecial 732	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   423: putfield 748	com/cyou/cma/clauncher/o:d	Landroid/content/ComponentName;
    //   426: aload 9
    //   428: astore 10
    //   430: aload 9
    //   432: ifnonnull +7 -> 439
    //   435: ldc -107
    //   437: astore 10
    //   439: aload 11
    //   441: aload 10
    //   443: putfield 749	com/cyou/cma/clauncher/o:l	Ljava/lang/CharSequence;
    //   446: aload 11
    //   448: aload 11
    //   450: getfield 748	com/cyou/cma/clauncher/o:d	Landroid/content/ComponentName;
    //   453: invokevirtual 752	com/cyou/cma/clauncher/o:a	(Landroid/content/ComponentName;)V
    //   456: aload 11
    //   458: aload_0
    //   459: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   462: invokestatic 282	com/cyou/cma/clauncher/jz:i	(Lcom/cyou/cma/clauncher/jz;)Lcom/cyou/cma/clauncher/ga;
    //   465: invokevirtual 755	com/cyou/cma/clauncher/ga:d	()Landroid/graphics/Bitmap;
    //   468: putfield 758	com/cyou/cma/clauncher/o:b	Landroid/graphics/Bitmap;
    //   471: aload 11
    //   473: astore 9
    //   475: aload 9
    //   477: lload 6
    //   479: putfield 759	com/cyou/cma/clauncher/o:q	J
    //   482: aload 17
    //   484: iconst_3
    //   485: invokeinterface 418 2 0
    //   490: lconst_1
    //   491: lcmp
    //   492: ifne +151 -> 643
    //   495: iconst_1
    //   496: istore 8
    //   498: aload 9
    //   500: iload 8
    //   502: putfield 762	com/cyou/cma/clauncher/o:m	Z
    //   505: aload 17
    //   507: bipush 9
    //   509: invokeinterface 418 2 0
    //   514: lconst_1
    //   515: lcmp
    //   516: ifne +133 -> 649
    //   519: iconst_1
    //   520: istore 8
    //   522: aload 9
    //   524: iload 8
    //   526: putfield 763	com/cyou/cma/clauncher/o:e	Z
    //   529: aload 9
    //   531: invokevirtual 765	com/cyou/cma/clauncher/o:a	()V
    //   534: aload 9
    //   536: iload_3
    //   537: putfield 768	com/cyou/cma/clauncher/o:F	I
    //   540: aload 9
    //   542: iload_2
    //   543: putfield 771	com/cyou/cma/clauncher/o:r	I
    //   546: aload 9
    //   548: iload_1
    //   549: i2l
    //   550: putfield 772	com/cyou/cma/clauncher/o:s	J
    //   553: aload 9
    //   555: iconst_1
    //   556: putfield 775	com/cyou/cma/clauncher/o:E	I
    //   559: aload_0
    //   560: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   563: getfield 709	com/cyou/cma/clauncher/jz:b	Lcom/cyou/cma/clauncher/j;
    //   566: aload 9
    //   568: invokevirtual 778	com/cyou/cma/clauncher/j:a	(Lcom/cyou/cma/clauncher/o;)Z
    //   571: ifeq +128 -> 699
    //   574: iload_1
    //   575: sipush 65336
    //   578: if_icmpne +77 -> 655
    //   581: aload 15
    //   583: aload 9
    //   585: getfield 759	com/cyou/cma/clauncher/o:q	J
    //   588: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   591: invokeinterface 781 2 0
    //   596: ifne -343 -> 253
    //   599: aload 15
    //   601: aload 9
    //   603: getfield 759	com/cyou/cma/clauncher/o:q	J
    //   606: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   609: aload 9
    //   611: invokeinterface 782 3 0
    //   616: pop
    //   617: goto -364 -> 253
    //   620: new 742	com/cyou/cma/clauncher/o
    //   623: dup
    //   624: aload 13
    //   626: aload 11
    //   628: aload_0
    //   629: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   632: invokestatic 282	com/cyou/cma/clauncher/jz:i	(Lcom/cyou/cma/clauncher/jz;)Lcom/cyou/cma/clauncher/ga;
    //   635: invokespecial 785	com/cyou/cma/clauncher/o:<init>	(Landroid/content/pm/PackageManager;Landroid/content/pm/ResolveInfo;Lcom/cyou/cma/clauncher/ga;)V
    //   638: astore 9
    //   640: goto -165 -> 475
    //   643: iconst_0
    //   644: istore 8
    //   646: goto -148 -> 498
    //   649: iconst_0
    //   650: istore 8
    //   652: goto -130 -> 522
    //   655: aload 14
    //   657: iload_1
    //   658: i2l
    //   659: invokestatic 787	com/cyou/cma/clauncher/jz:b	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/fh;
    //   662: astore 10
    //   664: aload 10
    //   666: iconst_1
    //   667: putfield 788	com/cyou/cma/clauncher/fh:E	I
    //   670: aload 10
    //   672: iconst_1
    //   673: putfield 789	com/cyou/cma/clauncher/fh:r	I
    //   676: aload 10
    //   678: ldc2_w 790
    //   681: putfield 507	com/cyou/cma/clauncher/fh:s	J
    //   684: aload 10
    //   686: invokevirtual 794	com/cyou/cma/clauncher/fh:a_	()V
    //   689: aload 10
    //   691: aload 9
    //   693: invokevirtual 437	com/cyou/cma/clauncher/fh:d	(Lcom/cyou/cma/clauncher/gi;)V
    //   696: goto -443 -> 253
    //   699: aload 16
    //   701: aload 9
    //   703: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   706: pop
    //   707: ldc_w 573
    //   710: new 223	java/lang/StringBuilder
    //   713: dup
    //   714: ldc_w 796
    //   717: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   720: aload 9
    //   722: getfield 749	com/cyou/cma/clauncher/o:l	Ljava/lang/CharSequence;
    //   725: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   728: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   731: invokestatic 798	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   734: pop
    //   735: goto -482 -> 253
    //   738: iload_2
    //   739: iconst_1
    //   740: if_icmpne -487 -> 253
    //   743: aload 14
    //   745: lload 6
    //   747: invokestatic 787	com/cyou/cma/clauncher/jz:b	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/fh;
    //   750: astore 18
    //   752: aload 9
    //   754: astore 10
    //   756: aload 9
    //   758: ifnonnull +7 -> 765
    //   761: ldc -107
    //   763: astore 10
    //   765: aload 18
    //   767: aload 10
    //   769: putfield 505	com/cyou/cma/clauncher/fh:l	Ljava/lang/CharSequence;
    //   772: aload 18
    //   774: ldc2_w 790
    //   777: putfield 507	com/cyou/cma/clauncher/fh:s	J
    //   780: aload 18
    //   782: lload 6
    //   784: putfield 506	com/cyou/cma/clauncher/fh:q	J
    //   787: aload 18
    //   789: iload_3
    //   790: putfield 799	com/cyou/cma/clauncher/fh:F	I
    //   793: aload 18
    //   795: iconst_1
    //   796: putfield 788	com/cyou/cma/clauncher/fh:E	I
    //   799: aload 18
    //   801: iconst_1
    //   802: putfield 789	com/cyou/cma/clauncher/fh:r	I
    //   805: aload 17
    //   807: bipush 8
    //   809: invokeinterface 398 2 0
    //   814: iconst_1
    //   815: if_icmpne +59 -> 874
    //   818: iconst_1
    //   819: istore 8
    //   821: aload 18
    //   823: iload 8
    //   825: putfield 800	com/cyou/cma/clauncher/fh:e	Z
    //   828: aload 18
    //   830: aload 11
    //   832: putfield 513	com/cyou/cma/clauncher/fh:B	Ljava/lang/String;
    //   835: aload 15
    //   837: aload 18
    //   839: getfield 506	com/cyou/cma/clauncher/fh:q	J
    //   842: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   845: invokeinterface 781 2 0
    //   850: ifne -597 -> 253
    //   853: aload 15
    //   855: aload 18
    //   857: getfield 506	com/cyou/cma/clauncher/fh:q	J
    //   860: invokestatic 442	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   863: aload 18
    //   865: invokeinterface 782 3 0
    //   870: pop
    //   871: goto -618 -> 253
    //   874: iconst_0
    //   875: istore 8
    //   877: goto -56 -> 821
    //   880: aload 17
    //   882: ifnull +10 -> 892
    //   885: aload 17
    //   887: invokeinterface 456 1 0
    //   892: aload_0
    //   893: getfield 32	com/cyou/cma/clauncher/kw:c	Landroid/content/Context;
    //   896: aload 16
    //   898: invokestatic 803	com/cyou/cma/clauncher/c:b	(Landroid/content/Context;Ljava/util/ArrayList;)V
    //   901: aload 16
    //   903: invokevirtual 315	java/util/ArrayList:clear	()V
    //   906: ldc_w 573
    //   909: new 223	java/lang/StringBuilder
    //   912: dup
    //   913: ldc_w 805
    //   916: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   919: invokestatic 298	android/os/SystemClock:uptimeMillis	()J
    //   922: lload 4
    //   924: lsub
    //   925: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   928: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   931: invokestatic 579	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   934: pop
    //   935: aload_0
    //   936: monitorenter
    //   937: aload_0
    //   938: getfield 391	com/cyou/cma/clauncher/kw:f	Z
    //   941: ifeq +16 -> 957
    //   944: aload_0
    //   945: monitorexit
    //   946: return
    //   947: astore 9
    //   949: aload 9
    //   951: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   954: goto -62 -> 892
    //   957: aload_0
    //   958: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   961: invokestatic 807	com/cyou/cma/clauncher/jz:m	(Lcom/cyou/cma/clauncher/jz;)Z
    //   964: pop
    //   965: aload_0
    //   966: getfield 23	com/cyou/cma/clauncher/kw:b	Lcom/cyou/cma/clauncher/jz;
    //   969: invokestatic 810	com/cyou/cma/clauncher/jz:n	(Lcom/cyou/cma/clauncher/jz;)V
    //   972: aload_0
    //   973: monitorexit
    //   974: aload_0
    //   975: invokespecial 271	com/cyou/cma/clauncher/kw:g	()V
    //   978: invokestatic 815	com/cyou/cma/a:a	()Lcom/cyou/cma/a;
    //   981: invokevirtual 817	com/cyou/cma/a:I	()Ljava/lang/String;
    //   984: invokestatic 822	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   987: ifeq -41 -> 946
    //   990: new 824	java/lang/Thread
    //   993: dup
    //   994: new 826	com/cyou/cma/clauncher/kz
    //   997: dup
    //   998: aload_0
    //   999: invokespecial 827	com/cyou/cma/clauncher/kz:<init>	(Lcom/cyou/cma/clauncher/kw;)V
    //   1002: invokespecial 829	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   1005: invokevirtual 830	java/lang/Thread:start	()V
    //   1008: return
    //   1009: astore 9
    //   1011: aload_0
    //   1012: monitorexit
    //   1013: aload 9
    //   1015: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1016	0	this	kw
    //   272	386	1	j	int
    //   281	460	2	k	int
    //   291	499	3	m	int
    //   20	903	4	l1	long
    //   300	483	6	l2	long
    //   496	380	8	bool	boolean
    //   75	682	9	localObject1	Object
    //   947	3	9	localException	Exception
    //   1009	5	9	localObject2	Object
    //   335	433	10	localObject3	Object
    //   311	520	11	localObject4	Object
    //   48	329	12	localIntent	Intent
    //   65	560	13	localPackageManager	PackageManager
    //   95	649	14	localHashMap	HashMap
    //   100	754	15	localMap	java.util.Map
    //   162	740	16	localArrayList	ArrayList
    //   251	635	17	localCursor	android.database.Cursor
    //   345	519	18	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   885	892	947	java/lang/Exception
    //   937	946	1009	finally
    //   957	974	1009	finally
    //   1011	1013	1009	finally
  }
  
  private void g()
  {
    if (this.a) {}
    do
    {
      return;
      localKs = (ks)jz.e(this.b).get();
    } while (localKs == null);
    if (localKs.e())
    {
      jz.o(this.b);
      return;
    }
    ks localKs = a(localKs);
    jz.d(this.b).a(new la(this, localKs));
  }
  
  final ks a(ks paramKs)
  {
    synchronized (jz.f(this.b))
    {
      if (this.f) {
        return null;
      }
      if (jz.e(this.b) == null) {
        return null;
      }
      ks localKs = (ks)jz.e(this.b).get();
      if (localKs != paramKs) {
        return null;
      }
      if (localKs == null)
      {
        Log.w("Launcher.Model", "no mCallbacks");
        return null;
      }
      return localKs;
    }
  }
  
  final boolean a()
  {
    return this.e;
  }
  
  public final void b()
  {
    try
    {
      this.f = true;
      notify();
      return;
    }
    finally {}
  }
  
  public final void c()
  {
    Log.d("Launcher.Model", "mLoaderTask.mContext=" + this.c);
    Log.d("Launcher.Model", "mLoaderTask.mWaitThread=" + this.d);
    Log.d("Launcher.Model", "mLoaderTask.mIsLaunching=" + this.e);
    Log.d("Launcher.Model", "mLoaderTask.mStopped=" + this.f);
    Log.d("Launcher.Model", "mLoaderTask.mLoadAndBindStepFinished=" + this.g);
    Log.d("Launcher.Model", "mItems size=" + jz.d.size());
  }
  
  public final void run()
  {
    int k = 0;
    ??? = (ks)jz.e(this.b).get();
    int j;
    if ((??? != null) && (((ks)???).C())) {
      j = 0;
    }
    for (;;)
    {
      synchronized (jz.f(this.b))
      {
        if (this.e)
        {
          label48:
          Process.setThreadPriority(k);
          if ((this.h) || (j == 0)) {
            break label216;
          }
          d();
          a(this.c);
          label77:
          if (!this.f) {
            synchronized (jz.f(this.b))
            {
              if (this.e) {
                Process.setThreadPriority(10);
              }
              if (!this.h)
              {
                if (j == 0) {
                  break label238;
                }
                f();
              }
            }
          }
        }
      }
      synchronized (jz.f(this.b))
      {
        Process.setThreadPriority(0);
        ??? = jz.h.keySet().iterator();
        for (;;)
        {
          if (((Iterator)???).hasNext())
          {
            Object localObject2 = ((Iterator)???).next();
            this.b.a(this.c, (of)localObject2, (byte[])jz.h.get(localObject2));
            continue;
            j = 1;
            break;
            k = 10;
            break label48;
            localObject3 = finally;
            throw localObject3;
            label216:
            a(this.c);
            f();
            break label77;
            localObject4 = finally;
            throw localObject4;
            label238:
            d();
          }
        }
      }
    }
    jz.h.clear();
    this.c = null;
    synchronized (jz.f(this.b))
    {
      if (jz.g(this.b) == this) {
        jz.h(this.b);
      }
      return;
    }
  }
}
