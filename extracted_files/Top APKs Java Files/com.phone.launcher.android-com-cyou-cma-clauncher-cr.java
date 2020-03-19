package com.cyou.cma.clauncher;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.cyou.cma.as;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class cr
  implements Runnable
{
  boolean a;
  private Context c;
  private Thread d;
  private boolean e;
  private boolean f;
  private boolean g;
  private HashMap<Object, CharSequence> h;
  private boolean i = false;
  private boolean j = false;
  
  cr(LauncherModel paramLauncherModel, Context paramContext, boolean paramBoolean)
  {
    this.c = paramContext;
    this.e = paramBoolean;
    this.h = new HashMap();
  }
  
  cr(LauncherModel paramLauncherModel, Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramLauncherModel, paramContext, paramBoolean1);
    this.a = paramBoolean2;
  }
  
  private void a(Context paramContext)
  {
    Object localObject = paramContext.getFileStreamPath("flag_all_app");
    boolean bool = ((File)localObject).exists();
    if (bool) {
      new Thread()
      {
        public final void run()
        {
          int i = 0;
          for (;;)
          {
            if (!this.a.delete()) {
              try
              {
                Thread.currentThread();
                Thread.sleep(200L);
                if (i < 5) {
                  i += 1;
                }
              }
              catch (InterruptedException localInterruptedException)
              {
                for (;;)
                {
                  localInterruptedException.printStackTrace();
                }
              }
            }
          }
        }
      }.start();
    }
    if (!bool) {
      return;
    }
    LauncherModel.q(this.b);
    localObject = paramContext.getContentResolver();
    ((ContentResolver)localObject).delete(cx.a, null, null);
    paramContext = new Intent("android.intent.action.MAIN", null);
    paramContext.addCategory("android.intent.category.LAUNCHER");
    PackageManager localPackageManager = this.c.getPackageManager();
    List localList = localPackageManager.queryIntentActivities(paramContext, 0);
    ContentValues[] arrayOfContentValues = new ContentValues[localList.size()];
    int m = localList.size();
    int k = 0;
    if (k < m)
    {
      paramContext = (ResolveInfo)localList.get(k);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("package_name", paramContext.activityInfo.packageName);
      localContentValues.put("class_name", paramContext.activityInfo.name);
      paramContext = paramContext.loadLabel(localPackageManager);
      if (paramContext == null) {}
      for (paramContext = "";; paramContext = paramContext.toString())
      {
        localContentValues.put("title", as.a(paramContext));
        localContentValues.put("app_index", Integer.valueOf(k));
        localContentValues.put("item_type", Integer.valueOf(0));
        localContentValues.put("container", Integer.valueOf(65336));
        localContentValues.put("hide", Integer.valueOf(0));
        arrayOfContentValues[k] = localContentValues;
        k += 1;
        break;
      }
    }
    ((ContentResolver)localObject).bulkInsert(cx.a, arrayOfContentValues);
    LauncherModel.k(this.b).e.d();
  }
  
  private static boolean a(by[][][] paramArrayOfBy, by paramBy)
  {
    int n = paramBy.t;
    if (paramBy.s == -101L)
    {
      if (paramArrayOfBy[Launcher.a][paramBy.u][0] != null)
      {
        Log.e("Launcher.Model", "Error loading shortcut into hotseat " + paramBy + " into position (" + paramBy.t + ":" + paramBy.u + "," + paramBy.v + ") occupied by " + paramArrayOfBy[Launcher.a][paramBy.u][0]);
        return false;
      }
      paramArrayOfBy[Launcher.a][paramBy.u][0] = paramBy;
      return true;
    }
    if (paramBy.s != -100L) {
      return true;
    }
    int k = paramBy.u;
    int m;
    while (k < paramBy.u + paramBy.w)
    {
      m = paramBy.v;
      while (m < paramBy.v + paramBy.x)
      {
        if (paramArrayOfBy[n][k][m] != null)
        {
          Log.e("Launcher.Model", "Error loading shortcut " + paramBy + " into cell (" + n + "-" + paramBy.t + ":" + k + "," + m + ") occupied by " + paramArrayOfBy[n][k][m]);
          return false;
        }
        m += 1;
      }
      k += 1;
    }
    k = paramBy.u;
    while (k < paramBy.u + paramBy.w)
    {
      m = paramBy.v;
      while (m < paramBy.v + paramBy.x)
      {
        paramArrayOfBy[n][k][m] = paramBy;
        m += 1;
      }
      k += 1;
    }
    return true;
  }
  
  /* Error */
  private void d()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   4: invokestatic 311	com/cyou/cma/clauncher/LauncherModel:b	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   7: ifne +2250 -> 2257
    //   10: aload_0
    //   11: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   14: invokestatic 314	com/cyou/cma/clauncher/LauncherModel:i	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/bs;
    //   17: getstatic 319	com/cyou/cma/a/a:a	Lcom/cyou/cma/a/a;
    //   20: invokevirtual 324	com/cyou/cma/clauncher/bs:a	(Lcom/cyou/cma/a/a;)V
    //   23: invokestatic 330	android/os/SystemClock:uptimeMillis	()J
    //   26: lstore 27
    //   28: aload_0
    //   29: getfield 61	com/cyou/cma/clauncher/cr:c	Landroid/content/Context;
    //   32: astore 40
    //   34: aload 40
    //   36: invokevirtual 105	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   39: astore 39
    //   41: aload 40
    //   43: invokevirtual 133	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: astore 41
    //   48: aload 40
    //   50: invokestatic 336	android/appwidget/AppWidgetManager:getInstance	(Landroid/content/Context;)Landroid/appwidget/AppWidgetManager;
    //   53: astore 42
    //   55: aload 41
    //   57: invokevirtual 339	android/content/pm/PackageManager:isSafeMode	()Z
    //   60: istore 31
    //   62: getstatic 342	com/cyou/cma/clauncher/LauncherModel:e	Ljava/util/ArrayList;
    //   65: invokevirtual 347	java/util/ArrayList:clear	()V
    //   68: getstatic 349	com/cyou/cma/clauncher/LauncherModel:f	Ljava/util/ArrayList;
    //   71: invokevirtual 347	java/util/ArrayList:clear	()V
    //   74: getstatic 350	com/cyou/cma/clauncher/LauncherModel:h	Ljava/util/HashMap;
    //   77: invokevirtual 351	java/util/HashMap:clear	()V
    //   80: getstatic 353	com/cyou/cma/clauncher/LauncherModel:d	Ljava/util/HashMap;
    //   83: invokevirtual 351	java/util/HashMap:clear	()V
    //   86: getstatic 355	com/cyou/cma/clauncher/LauncherModel:i	Ljava/util/HashMap;
    //   89: invokevirtual 351	java/util/HashMap:clear	()V
    //   92: new 344	java/util/ArrayList
    //   95: dup
    //   96: invokespecial 356	java/util/ArrayList:<init>	()V
    //   99: astore 37
    //   101: aload 39
    //   103: getstatic 359	com/cyou/cma/clauncher/cz:a	Landroid/net/Uri;
    //   106: aconst_null
    //   107: aconst_null
    //   108: aconst_null
    //   109: aconst_null
    //   110: invokevirtual 363	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   113: astore 38
    //   115: ldc -22
    //   117: iconst_3
    //   118: newarray int
    //   120: dup
    //   121: iconst_0
    //   122: getstatic 248	com/cyou/cma/clauncher/Launcher:a	I
    //   125: iconst_1
    //   126: iadd
    //   127: iastore
    //   128: dup
    //   129: iconst_1
    //   130: invokestatic 365	com/cyou/cma/clauncher/LauncherModel:k	()I
    //   133: iconst_1
    //   134: iadd
    //   135: iastore
    //   136: dup
    //   137: iconst_2
    //   138: invokestatic 368	com/cyou/cma/clauncher/LauncherModel:l	()I
    //   141: iconst_1
    //   142: iadd
    //   143: iastore
    //   144: invokestatic 374	java/lang/reflect/Array:newInstance	(Ljava/lang/Class;[I)Ljava/lang/Object;
    //   147: checkcast 376	[[[Lcom/cyou/cma/clauncher/by;
    //   150: astore 43
    //   152: aload 38
    //   154: ldc_w 378
    //   157: invokeinterface 384 2 0
    //   162: istore_1
    //   163: aload 38
    //   165: ldc_w 386
    //   168: invokeinterface 384 2 0
    //   173: istore_2
    //   174: aload 38
    //   176: ldc -73
    //   178: invokeinterface 384 2 0
    //   183: istore_3
    //   184: aload 38
    //   186: ldc_w 388
    //   189: invokeinterface 384 2 0
    //   194: istore 4
    //   196: aload 38
    //   198: ldc_w 390
    //   201: invokeinterface 384 2 0
    //   206: istore 5
    //   208: aload 38
    //   210: ldc_w 392
    //   213: invokeinterface 384 2 0
    //   218: istore 6
    //   220: aload 38
    //   222: ldc_w 394
    //   225: invokeinterface 384 2 0
    //   230: istore 7
    //   232: aload 38
    //   234: ldc -53
    //   236: invokeinterface 384 2 0
    //   241: istore 8
    //   243: aload 38
    //   245: ldc_w 396
    //   248: invokeinterface 384 2 0
    //   253: istore 9
    //   255: aload 38
    //   257: ldc_w 398
    //   260: invokeinterface 384 2 0
    //   265: istore 10
    //   267: aload 38
    //   269: ldc_w 400
    //   272: invokeinterface 384 2 0
    //   277: istore 11
    //   279: aload 38
    //   281: ldc_w 402
    //   284: invokeinterface 384 2 0
    //   289: istore 12
    //   291: aload 38
    //   293: ldc_w 404
    //   296: invokeinterface 384 2 0
    //   301: istore 13
    //   303: aload 38
    //   305: ldc_w 406
    //   308: invokeinterface 384 2 0
    //   313: istore 14
    //   315: aload 38
    //   317: ldc_w 408
    //   320: invokeinterface 384 2 0
    //   325: istore 15
    //   327: aload 38
    //   329: ldc_w 410
    //   332: invokeinterface 384 2 0
    //   337: pop
    //   338: aload 38
    //   340: ldc_w 412
    //   343: invokeinterface 384 2 0
    //   348: pop
    //   349: aload 38
    //   351: ldc_w 414
    //   354: invokeinterface 384 2 0
    //   359: istore 16
    //   361: aload 38
    //   363: ldc_w 416
    //   366: invokeinterface 384 2 0
    //   371: istore 17
    //   373: aload 38
    //   375: ldc_w 418
    //   378: invokeinterface 384 2 0
    //   383: istore 18
    //   385: aload 38
    //   387: ldc_w 420
    //   390: invokeinterface 384 2 0
    //   395: istore 19
    //   397: aload 38
    //   399: ldc_w 422
    //   402: invokeinterface 384 2 0
    //   407: istore 20
    //   409: aload 38
    //   411: ldc_w 424
    //   414: invokeinterface 384 2 0
    //   419: istore 21
    //   421: aload 38
    //   423: ldc_w 425
    //   426: invokeinterface 384 2 0
    //   431: istore 22
    //   433: aload 38
    //   435: ldc_w 427
    //   438: invokeinterface 384 2 0
    //   443: istore 23
    //   445: aload 38
    //   447: ldc_w 429
    //   450: invokeinterface 384 2 0
    //   455: istore 24
    //   457: aload_0
    //   458: getfield 431	com/cyou/cma/clauncher/cr:f	Z
    //   461: ifne +1725 -> 2186
    //   464: aload 38
    //   466: invokeinterface 434 1 0
    //   471: istore 32
    //   473: iload 32
    //   475: ifeq +1711 -> 2186
    //   478: aload 38
    //   480: iload 9
    //   482: invokeinterface 438 2 0
    //   487: istore 26
    //   489: aconst_null
    //   490: astore 34
    //   492: aconst_null
    //   493: astore 36
    //   495: aload 38
    //   497: iload 19
    //   499: invokeinterface 442 2 0
    //   504: astore 44
    //   506: ldc_w 444
    //   509: aload 44
    //   511: invokevirtual 450	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   514: pop
    //   515: iload 26
    //   517: tableswitch	default:+1768->2285, 0:+39->556, 1:+39->556, 2:+1016->1533, 3:+1768->2285, 4:+1240->1757, 5:+1540->2057
    //   556: aload 38
    //   558: iload 20
    //   560: invokeinterface 442 2 0
    //   565: astore 45
    //   567: aload 38
    //   569: iload 21
    //   571: invokeinterface 442 2 0
    //   576: astore 46
    //   578: aload 38
    //   580: iload 22
    //   582: invokeinterface 438 2 0
    //   587: istore 25
    //   589: aload 38
    //   591: iload 23
    //   593: invokeinterface 442 2 0
    //   598: astore 47
    //   600: aload 38
    //   602: iload 24
    //   604: invokeinterface 442 2 0
    //   609: astore 48
    //   611: aload 38
    //   613: iload_2
    //   614: invokeinterface 442 2 0
    //   619: astore 33
    //   621: aload 33
    //   623: iconst_0
    //   624: invokestatic 454	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   627: astore 49
    //   629: iload 26
    //   631: ifne +536 -> 1167
    //   634: aload 44
    //   636: ifnull +504 -> 1140
    //   639: aload_0
    //   640: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   643: aload 41
    //   645: aload 49
    //   647: aload_0
    //   648: getfield 68	com/cyou/cma/clauncher/cr:h	Ljava/util/HashMap;
    //   651: aload 44
    //   653: invokevirtual 457	com/cyou/cma/clauncher/LauncherModel:a	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Ljava/util/HashMap;Ljava/lang/String;)Lcom/cyou/cma/clauncher/ef;
    //   656: astore 33
    //   658: aload 33
    //   660: aload 49
    //   662: putfield 462	com/cyou/cma/clauncher/ef:b	Landroid/content/Intent;
    //   665: ldc_w 444
    //   668: aload 44
    //   670: invokevirtual 450	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   673: ifeq +24 -> 697
    //   676: aload 40
    //   678: ldc_w 464
    //   681: iconst_0
    //   682: invokevirtual 468	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   685: ldc_w 470
    //   688: iconst_1
    //   689: invokeinterface 476 3 0
    //   694: ifeq -237 -> 457
    //   697: aload 33
    //   699: ifnull +778 -> 1477
    //   702: aload 33
    //   704: aload 38
    //   706: iload_1
    //   707: invokeinterface 480 2 0
    //   712: putfield 482	com/cyou/cma/clauncher/ef:q	J
    //   715: aload 38
    //   717: iload 8
    //   719: invokeinterface 438 2 0
    //   724: istore 26
    //   726: aload 33
    //   728: iload 26
    //   730: i2l
    //   731: putfield 483	com/cyou/cma/clauncher/ef:s	J
    //   734: aload 33
    //   736: aload 38
    //   738: iload 11
    //   740: invokeinterface 438 2 0
    //   745: putfield 484	com/cyou/cma/clauncher/ef:t	I
    //   748: aload 33
    //   750: aload 38
    //   752: iload 12
    //   754: invokeinterface 438 2 0
    //   759: putfield 485	com/cyou/cma/clauncher/ef:u	I
    //   762: aload 33
    //   764: aload 38
    //   766: iload 13
    //   768: invokeinterface 438 2 0
    //   773: putfield 486	com/cyou/cma/clauncher/ef:v	I
    //   776: aload 33
    //   778: aload 44
    //   780: putfield 488	com/cyou/cma/clauncher/ef:j	Ljava/lang/String;
    //   783: aload 33
    //   785: aload 45
    //   787: putfield 491	com/cyou/cma/clauncher/ef:n	Ljava/lang/String;
    //   790: aload 33
    //   792: aload 46
    //   794: putfield 494	com/cyou/cma/clauncher/ef:m	Ljava/lang/String;
    //   797: aload 33
    //   799: iload 25
    //   801: putfield 497	com/cyou/cma/clauncher/ef:p	I
    //   804: aload 33
    //   806: aload 47
    //   808: putfield 499	com/cyou/cma/clauncher/ef:J	Ljava/lang/String;
    //   811: aload 33
    //   813: aload 48
    //   815: putfield 502	com/cyou/cma/clauncher/ef:K	Ljava/lang/String;
    //   818: ldc_w 444
    //   821: aload 33
    //   823: getfield 488	com/cyou/cma/clauncher/ef:j	Ljava/lang/String;
    //   826: invokevirtual 450	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   829: ifne +13 -> 842
    //   832: aload 43
    //   834: aload 33
    //   836: invokestatic 504	com/cyou/cma/clauncher/cr:a	([[[Lcom/cyou/cma/clauncher/by;Lcom/cyou/cma/clauncher/by;)Z
    //   839: ifeq -382 -> 457
    //   842: ldc_w 444
    //   845: aload 44
    //   847: invokevirtual 450	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   850: ifeq +1438 -> 2288
    //   853: aload 43
    //   855: aload 33
    //   857: invokestatic 504	com/cyou/cma/clauncher/cr:a	([[[Lcom/cyou/cma/clauncher/by;Lcom/cyou/cma/clauncher/by;)Z
    //   860: ifne +1428 -> 2288
    //   863: aload 43
    //   865: iload 26
    //   867: aaload
    //   868: aload 33
    //   870: getfield 485	com/cyou/cma/clauncher/ef:u	I
    //   873: aaload
    //   874: aload 33
    //   876: getfield 486	com/cyou/cma/clauncher/ef:v	I
    //   879: aload 33
    //   881: aastore
    //   882: getstatic 342	com/cyou/cma/clauncher/LauncherModel:e	Ljava/util/ArrayList;
    //   885: invokevirtual 508	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   888: astore 35
    //   890: aload 35
    //   892: invokeinterface 513 1 0
    //   897: ifeq +1382 -> 2279
    //   900: aload 35
    //   902: invokeinterface 517 1 0
    //   907: checkcast 234	com/cyou/cma/clauncher/by
    //   910: astore 34
    //   912: aload 34
    //   914: getfield 242	com/cyou/cma/clauncher/by:s	J
    //   917: aload 33
    //   919: getfield 483	com/cyou/cma/clauncher/ef:s	J
    //   922: lcmp
    //   923: ifne -33 -> 890
    //   926: aload 34
    //   928: getfield 251	com/cyou/cma/clauncher/by:u	I
    //   931: aload 33
    //   933: getfield 485	com/cyou/cma/clauncher/ef:u	I
    //   936: if_icmpne -46 -> 890
    //   939: aload 34
    //   941: getfield 279	com/cyou/cma/clauncher/by:v	I
    //   944: aload 33
    //   946: getfield 486	com/cyou/cma/clauncher/ef:v	I
    //   949: if_icmpne -59 -> 890
    //   952: aload 34
    //   954: ifnull +1334 -> 2288
    //   957: getstatic 342	com/cyou/cma/clauncher/LauncherModel:e	Ljava/util/ArrayList;
    //   960: aload 34
    //   962: invokevirtual 520	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   965: pop
    //   966: goto +1322 -> 2288
    //   969: getstatic 350	com/cyou/cma/clauncher/LauncherModel:h	Ljava/util/HashMap;
    //   972: iload 26
    //   974: i2l
    //   975: invokestatic 523	com/cyou/cma/clauncher/LauncherModel:a	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/bc;
    //   978: aload 33
    //   980: invokevirtual 528	com/cyou/cma/clauncher/bc:d	(Lcom/cyou/cma/clauncher/by;)V
    //   983: getstatic 353	com/cyou/cma/clauncher/LauncherModel:d	Ljava/util/HashMap;
    //   986: aload 33
    //   988: getfield 482	com/cyou/cma/clauncher/ef:q	J
    //   991: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   994: aload 33
    //   996: invokevirtual 536	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   999: pop
    //   1000: aload_0
    //   1001: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1004: getstatic 355	com/cyou/cma/clauncher/LauncherModel:i	Ljava/util/HashMap;
    //   1007: aload 33
    //   1009: aload 38
    //   1011: iload 5
    //   1013: invokevirtual 539	com/cyou/cma/clauncher/LauncherModel:a	(Ljava/util/HashMap;Lcom/cyou/cma/clauncher/ef;Landroid/database/Cursor;I)Z
    //   1016: pop
    //   1017: goto -560 -> 457
    //   1020: astore 33
    //   1022: ldc -3
    //   1024: ldc_w 541
    //   1027: aload 33
    //   1029: invokestatic 544	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1032: pop
    //   1033: goto -576 -> 457
    //   1036: astore 33
    //   1038: aload 38
    //   1040: invokeinterface 547 1 0
    //   1045: aload 37
    //   1047: invokevirtual 548	java/util/ArrayList:size	()I
    //   1050: ifle +1146 -> 2196
    //   1053: aload 39
    //   1055: getstatic 359	com/cyou/cma/clauncher/cz:a	Landroid/net/Uri;
    //   1058: invokevirtual 552	android/content/ContentResolver:acquireContentProviderClient	(Landroid/net/Uri;)Landroid/content/ContentProviderClient;
    //   1061: astore 33
    //   1063: aload 37
    //   1065: invokevirtual 508	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1068: astore 34
    //   1070: aload 34
    //   1072: invokeinterface 513 1 0
    //   1077: ifeq +1119 -> 2196
    //   1080: aload 34
    //   1082: invokeinterface 517 1 0
    //   1087: checkcast 530	java/lang/Long
    //   1090: invokevirtual 555	java/lang/Long:longValue	()J
    //   1093: lstore 29
    //   1095: aload 33
    //   1097: lload 29
    //   1099: invokestatic 558	com/cyou/cma/clauncher/cz:a	(J)Landroid/net/Uri;
    //   1102: aconst_null
    //   1103: aconst_null
    //   1104: invokevirtual 561	android/content/ContentProviderClient:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   1107: pop
    //   1108: goto -38 -> 1070
    //   1111: astore 35
    //   1113: ldc -3
    //   1115: new 255	java/lang/StringBuilder
    //   1118: dup
    //   1119: ldc_w 563
    //   1122: invokespecial 260	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1125: lload 29
    //   1127: invokevirtual 566	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1130: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1133: invokestatic 568	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1136: pop
    //   1137: goto -67 -> 1070
    //   1140: aload_0
    //   1141: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1144: aload 41
    //   1146: aload 49
    //   1148: aload 40
    //   1150: aload 38
    //   1152: iload 5
    //   1154: iload_3
    //   1155: aload_0
    //   1156: getfield 68	com/cyou/cma/clauncher/cr:h	Ljava/util/HashMap;
    //   1159: invokevirtual 571	com/cyou/cma/clauncher/LauncherModel:a	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;IILjava/util/HashMap;)Lcom/cyou/cma/clauncher/ef;
    //   1162: astore 33
    //   1164: goto -506 -> 658
    //   1167: aload 33
    //   1169: invokestatic 577	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1172: ifne +73 -> 1245
    //   1175: invokestatic 582	com/cyou/cma/SwitchService:a	()Lcom/cyou/cma/SwitchService;
    //   1178: ldc_w 584
    //   1181: iconst_0
    //   1182: invokevirtual 586	com/cyou/cma/SwitchService:a	(Ljava/lang/String;Z)Z
    //   1185: ifne +25 -> 1210
    //   1188: aload 33
    //   1190: ldc_w 588
    //   1193: invokevirtual 591	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1196: ifeq +14 -> 1210
    //   1199: aload 33
    //   1201: ldc_w 593
    //   1204: invokevirtual 591	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1207: ifne -750 -> 457
    //   1210: invokestatic 582	com/cyou/cma/SwitchService:a	()Lcom/cyou/cma/SwitchService;
    //   1213: ldc_w 595
    //   1216: iconst_0
    //   1217: invokevirtual 586	com/cyou/cma/SwitchService:a	(Ljava/lang/String;Z)Z
    //   1220: ifne +25 -> 1245
    //   1223: aload 33
    //   1225: ldc_w 588
    //   1228: invokevirtual 591	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1231: ifeq +14 -> 1245
    //   1234: aload 33
    //   1236: ldc_w 597
    //   1239: invokevirtual 591	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1242: ifne -785 -> 457
    //   1245: aload 38
    //   1247: iload 16
    //   1249: invokeinterface 442 2 0
    //   1254: astore 50
    //   1256: aconst_null
    //   1257: astore 33
    //   1259: aload 50
    //   1261: invokestatic 577	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1264: istore 32
    //   1266: aload 33
    //   1268: astore 35
    //   1270: iload 32
    //   1272: ifne +11 -> 1283
    //   1275: aload 50
    //   1277: iconst_0
    //   1278: invokestatic 454	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   1281: astore 35
    //   1283: aload 36
    //   1285: astore 33
    //   1287: aload 35
    //   1289: ifnull +100 -> 1389
    //   1292: aload 35
    //   1294: invokevirtual 601	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   1297: astore 50
    //   1299: aload 36
    //   1301: astore 33
    //   1303: aload 50
    //   1305: ifnull +84 -> 1389
    //   1308: aload 34
    //   1310: astore 33
    //   1312: aload 41
    //   1314: aload 35
    //   1316: invokevirtual 601	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   1319: invokevirtual 605	android/content/pm/PackageManager:getActivityIcon	(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;
    //   1322: pop
    //   1323: aload 34
    //   1325: astore 33
    //   1327: aload_0
    //   1328: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1331: aload 41
    //   1333: aload 35
    //   1335: aload 40
    //   1337: aload 38
    //   1339: iload 5
    //   1341: iload_3
    //   1342: aload_0
    //   1343: getfield 68	com/cyou/cma/clauncher/cr:h	Ljava/util/HashMap;
    //   1346: invokevirtual 571	com/cyou/cma/clauncher/LauncherModel:a	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;IILjava/util/HashMap;)Lcom/cyou/cma/clauncher/ef;
    //   1349: astore 34
    //   1351: aload 34
    //   1353: astore 33
    //   1355: aload 35
    //   1357: ldc 120
    //   1359: invokevirtual 608	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   1362: pop
    //   1363: aload 34
    //   1365: astore 33
    //   1367: aload 34
    //   1369: aload 35
    //   1371: putfield 462	com/cyou/cma/clauncher/ef:b	Landroid/content/Intent;
    //   1374: aload 34
    //   1376: astore 33
    //   1378: aload 34
    //   1380: aload 35
    //   1382: putfield 611	com/cyou/cma/clauncher/ef:A	Landroid/content/Intent;
    //   1385: aload 34
    //   1387: astore 33
    //   1389: aload 33
    //   1391: astore 34
    //   1393: aload 33
    //   1395: ifnonnull +920 -> 2315
    //   1398: aload_0
    //   1399: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1402: aload 38
    //   1404: aload 40
    //   1406: iload 4
    //   1408: iload 6
    //   1410: iload 7
    //   1412: iload 5
    //   1414: iload_3
    //   1415: aload 49
    //   1417: invokestatic 614	com/cyou/cma/clauncher/LauncherModel:a	(Lcom/cyou/cma/clauncher/LauncherModel;Landroid/database/Cursor;Landroid/content/Context;IIIIILandroid/content/Intent;)Lcom/cyou/cma/clauncher/ef;
    //   1420: astore 34
    //   1422: aload 34
    //   1424: aload 49
    //   1426: putfield 462	com/cyou/cma/clauncher/ef:b	Landroid/content/Intent;
    //   1429: aload 34
    //   1431: aload 35
    //   1433: putfield 611	com/cyou/cma/clauncher/ef:A	Landroid/content/Intent;
    //   1436: goto +879 -> 2315
    //   1439: astore 35
    //   1441: aload 35
    //   1443: invokevirtual 617	java/net/URISyntaxException:printStackTrace	()V
    //   1446: aload 33
    //   1448: astore 35
    //   1450: goto -167 -> 1283
    //   1453: astore 33
    //   1455: aload 38
    //   1457: invokeinterface 547 1 0
    //   1462: aload 33
    //   1464: athrow
    //   1465: getstatic 342	com/cyou/cma/clauncher/LauncherModel:e	Ljava/util/ArrayList;
    //   1468: aload 33
    //   1470: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1473: pop
    //   1474: goto -491 -> 983
    //   1477: aload 38
    //   1479: iload_1
    //   1480: invokeinterface 480 2 0
    //   1485: lstore 29
    //   1487: ldc -3
    //   1489: new 255	java/lang/StringBuilder
    //   1492: dup
    //   1493: ldc_w 297
    //   1496: invokespecial 260	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1499: lload 29
    //   1501: invokevirtual 566	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1504: ldc_w 622
    //   1507: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1510: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1513: invokestatic 287	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1516: pop
    //   1517: aload 39
    //   1519: lload 29
    //   1521: invokestatic 558	com/cyou/cma/clauncher/cz:a	(J)Landroid/net/Uri;
    //   1524: aconst_null
    //   1525: aconst_null
    //   1526: invokevirtual 116	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   1529: pop
    //   1530: goto -1073 -> 457
    //   1533: aload 38
    //   1535: iload_1
    //   1536: invokeinterface 480 2 0
    //   1541: lstore 29
    //   1543: getstatic 350	com/cyou/cma/clauncher/LauncherModel:h	Ljava/util/HashMap;
    //   1546: lload 29
    //   1548: invokestatic 523	com/cyou/cma/clauncher/LauncherModel:a	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/bc;
    //   1551: astore 33
    //   1553: aload 33
    //   1555: aload 38
    //   1557: iload_3
    //   1558: invokeinterface 442 2 0
    //   1563: putfield 625	com/cyou/cma/clauncher/bc:l	Ljava/lang/CharSequence;
    //   1566: aload 33
    //   1568: lload 29
    //   1570: putfield 626	com/cyou/cma/clauncher/bc:q	J
    //   1573: aload 38
    //   1575: iload 8
    //   1577: invokeinterface 438 2 0
    //   1582: istore 25
    //   1584: aload 33
    //   1586: iload 25
    //   1588: i2l
    //   1589: putfield 627	com/cyou/cma/clauncher/bc:s	J
    //   1592: aload 33
    //   1594: aload 38
    //   1596: iload 11
    //   1598: invokeinterface 438 2 0
    //   1603: putfield 628	com/cyou/cma/clauncher/bc:t	I
    //   1606: aload 33
    //   1608: aload 38
    //   1610: iload 12
    //   1612: invokeinterface 438 2 0
    //   1617: putfield 629	com/cyou/cma/clauncher/bc:u	I
    //   1620: aload 33
    //   1622: aload 38
    //   1624: iload 13
    //   1626: invokeinterface 438 2 0
    //   1631: putfield 630	com/cyou/cma/clauncher/bc:v	I
    //   1634: aload 33
    //   1636: aload 38
    //   1638: iload 18
    //   1640: invokeinterface 442 2 0
    //   1645: putfield 633	com/cyou/cma/clauncher/bc:D	Ljava/lang/String;
    //   1648: aload 38
    //   1650: iload 17
    //   1652: invokeinterface 442 2 0
    //   1657: astore 34
    //   1659: aload 34
    //   1661: invokestatic 577	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1664: ifne +10 -> 1674
    //   1667: aload 33
    //   1669: aload 34
    //   1671: putfield 636	com/cyou/cma/clauncher/bc:C	Ljava/lang/String;
    //   1674: aload 43
    //   1676: aload 33
    //   1678: invokestatic 504	com/cyou/cma/clauncher/cr:a	([[[Lcom/cyou/cma/clauncher/by;Lcom/cyou/cma/clauncher/by;)Z
    //   1681: ifeq -1224 -> 457
    //   1684: iload 25
    //   1686: tableswitch	default:+636->2322, -101:+59->1745, -100:+59->1745
    //   1708: getstatic 353	com/cyou/cma/clauncher/LauncherModel:d	Ljava/util/HashMap;
    //   1711: aload 33
    //   1713: getfield 626	com/cyou/cma/clauncher/bc:q	J
    //   1716: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1719: aload 33
    //   1721: invokevirtual 536	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1724: pop
    //   1725: getstatic 350	com/cyou/cma/clauncher/LauncherModel:h	Ljava/util/HashMap;
    //   1728: aload 33
    //   1730: getfield 626	com/cyou/cma/clauncher/bc:q	J
    //   1733: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1736: aload 33
    //   1738: invokevirtual 536	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1741: pop
    //   1742: goto -1285 -> 457
    //   1745: getstatic 342	com/cyou/cma/clauncher/LauncherModel:e	Ljava/util/ArrayList;
    //   1748: aload 33
    //   1750: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1753: pop
    //   1754: goto -46 -> 1708
    //   1757: aload 38
    //   1759: iload 10
    //   1761: invokeinterface 438 2 0
    //   1766: istore 25
    //   1768: aload 38
    //   1770: iload_1
    //   1771: invokeinterface 480 2 0
    //   1776: lstore 29
    //   1778: aload 42
    //   1780: iload 25
    //   1782: invokevirtual 640	android/appwidget/AppWidgetManager:getAppWidgetInfo	(I)Landroid/appwidget/AppWidgetProviderInfo;
    //   1785: astore 33
    //   1787: iload 31
    //   1789: ifne +89 -> 1878
    //   1792: aload 33
    //   1794: ifnull +22 -> 1816
    //   1797: aload 33
    //   1799: getfield 646	android/appwidget/AppWidgetProviderInfo:provider	Landroid/content/ComponentName;
    //   1802: ifnull +14 -> 1816
    //   1805: aload 33
    //   1807: getfield 646	android/appwidget/AppWidgetProviderInfo:provider	Landroid/content/ComponentName;
    //   1810: invokevirtual 651	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   1813: ifnonnull +65 -> 1878
    //   1816: new 255	java/lang/StringBuilder
    //   1819: dup
    //   1820: ldc_w 653
    //   1823: invokespecial 260	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1826: lload 29
    //   1828: invokevirtual 566	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1831: ldc_w 655
    //   1834: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1837: iload 25
    //   1839: invokevirtual 272	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1842: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1845: astore 33
    //   1847: ldc -3
    //   1849: aload 33
    //   1851: invokestatic 287	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1854: pop
    //   1855: getstatic 657	com/cyou/cma/clauncher/Launcher:p	Ljava/util/ArrayList;
    //   1858: aload 33
    //   1860: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1863: pop
    //   1864: aload 37
    //   1866: lload 29
    //   1868: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1871: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1874: pop
    //   1875: goto -1418 -> 457
    //   1878: new 659	com/cyou/cma/clauncher/ck
    //   1881: dup
    //   1882: iload 25
    //   1884: invokespecial 662	com/cyou/cma/clauncher/ck:<init>	(I)V
    //   1887: astore 33
    //   1889: aload 33
    //   1891: lload 29
    //   1893: putfield 663	com/cyou/cma/clauncher/ck:q	J
    //   1896: aload 33
    //   1898: aload 38
    //   1900: iload 11
    //   1902: invokeinterface 438 2 0
    //   1907: putfield 664	com/cyou/cma/clauncher/ck:t	I
    //   1910: aload 33
    //   1912: aload 38
    //   1914: iload 12
    //   1916: invokeinterface 438 2 0
    //   1921: putfield 665	com/cyou/cma/clauncher/ck:u	I
    //   1924: aload 33
    //   1926: aload 38
    //   1928: iload 13
    //   1930: invokeinterface 438 2 0
    //   1935: putfield 666	com/cyou/cma/clauncher/ck:v	I
    //   1938: aload 33
    //   1940: aload 38
    //   1942: iload 14
    //   1944: invokeinterface 438 2 0
    //   1949: putfield 667	com/cyou/cma/clauncher/ck:w	I
    //   1952: aload 33
    //   1954: aload 38
    //   1956: iload 15
    //   1958: invokeinterface 438 2 0
    //   1963: putfield 668	com/cyou/cma/clauncher/ck:x	I
    //   1966: aload 38
    //   1968: iload 8
    //   1970: invokeinterface 438 2 0
    //   1975: istore 25
    //   1977: iload 25
    //   1979: bipush -100
    //   1981: if_icmpeq +22 -> 2003
    //   1984: iload 25
    //   1986: bipush -101
    //   1988: if_icmpeq +15 -> 2003
    //   1991: ldc -3
    //   1993: ldc_w 670
    //   1996: invokestatic 287	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1999: pop
    //   2000: goto -1543 -> 457
    //   2003: aload 33
    //   2005: aload 38
    //   2007: iload 8
    //   2009: invokeinterface 438 2 0
    //   2014: i2l
    //   2015: putfield 671	com/cyou/cma/clauncher/ck:s	J
    //   2018: aload 43
    //   2020: aload 33
    //   2022: invokestatic 504	com/cyou/cma/clauncher/cr:a	([[[Lcom/cyou/cma/clauncher/by;Lcom/cyou/cma/clauncher/by;)Z
    //   2025: ifeq -1568 -> 457
    //   2028: getstatic 353	com/cyou/cma/clauncher/LauncherModel:d	Ljava/util/HashMap;
    //   2031: aload 33
    //   2033: getfield 663	com/cyou/cma/clauncher/ck:q	J
    //   2036: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2039: aload 33
    //   2041: invokevirtual 536	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2044: pop
    //   2045: getstatic 349	com/cyou/cma/clauncher/LauncherModel:f	Ljava/util/ArrayList;
    //   2048: aload 33
    //   2050: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2053: pop
    //   2054: goto -1597 -> 457
    //   2057: aload 38
    //   2059: iload_2
    //   2060: invokeinterface 442 2 0
    //   2065: invokestatic 677	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   2068: iconst_0
    //   2069: anewarray 673	java/lang/Class
    //   2072: invokevirtual 681	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   2075: iconst_0
    //   2076: anewarray 4	java/lang/Object
    //   2079: invokevirtual 686	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   2082: checkcast 688	com/cyou/cma/clauncher/ab
    //   2085: astore 33
    //   2087: aload 33
    //   2089: aload 38
    //   2091: iload_1
    //   2092: invokeinterface 480 2 0
    //   2097: putfield 689	com/cyou/cma/clauncher/ab:q	J
    //   2100: aload 33
    //   2102: aload 38
    //   2104: iload 12
    //   2106: invokeinterface 438 2 0
    //   2111: putfield 690	com/cyou/cma/clauncher/ab:u	I
    //   2114: aload 33
    //   2116: aload 38
    //   2118: iload 13
    //   2120: invokeinterface 438 2 0
    //   2125: putfield 691	com/cyou/cma/clauncher/ab:v	I
    //   2128: aload 33
    //   2130: aload 38
    //   2132: iload 11
    //   2134: invokeinterface 438 2 0
    //   2139: putfield 692	com/cyou/cma/clauncher/ab:t	I
    //   2142: aload 33
    //   2144: aload 38
    //   2146: iload 8
    //   2148: invokeinterface 438 2 0
    //   2153: i2l
    //   2154: putfield 693	com/cyou/cma/clauncher/ab:s	J
    //   2157: getstatic 353	com/cyou/cma/clauncher/LauncherModel:d	Ljava/util/HashMap;
    //   2160: aload 33
    //   2162: getfield 689	com/cyou/cma/clauncher/ab:q	J
    //   2165: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2168: aload 33
    //   2170: invokevirtual 536	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2173: pop
    //   2174: getstatic 342	com/cyou/cma/clauncher/LauncherModel:e	Ljava/util/ArrayList;
    //   2177: aload 33
    //   2179: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2182: pop
    //   2183: goto -1726 -> 457
    //   2186: aload 38
    //   2188: invokeinterface 547 1 0
    //   2193: goto -1148 -> 1045
    //   2196: ldc_w 695
    //   2199: new 255	java/lang/StringBuilder
    //   2202: dup
    //   2203: ldc_w 697
    //   2206: invokespecial 260	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2209: invokestatic 330	android/os/SystemClock:uptimeMillis	()J
    //   2212: lload 27
    //   2214: lsub
    //   2215: invokevirtual 566	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2218: ldc_w 699
    //   2221: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2224: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2227: invokestatic 701	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2230: pop
    //   2231: aload_0
    //   2232: monitorenter
    //   2233: aload_0
    //   2234: getfield 431	com/cyou/cma/clauncher/cr:f	Z
    //   2237: ifeq +10 -> 2247
    //   2240: aload_0
    //   2241: invokevirtual 704	java/lang/Object:notify	()V
    //   2244: aload_0
    //   2245: monitorexit
    //   2246: return
    //   2247: aload_0
    //   2248: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   2251: invokestatic 706	com/cyou/cma/clauncher/LauncherModel:c	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   2254: pop
    //   2255: aload_0
    //   2256: monitorexit
    //   2257: aload_0
    //   2258: invokespecial 231	com/cyou/cma/clauncher/cr:e	()V
    //   2261: return
    //   2262: astore 33
    //   2264: aload_0
    //   2265: monitorexit
    //   2266: aload 33
    //   2268: athrow
    //   2269: astore 33
    //   2271: goto -1814 -> 457
    //   2274: astore 34
    //   2276: goto -887 -> 1389
    //   2279: aconst_null
    //   2280: astore 34
    //   2282: goto -1330 -> 952
    //   2285: goto -1828 -> 457
    //   2288: iload 26
    //   2290: tableswitch	default:+22->2312, -101:+-825->1465, -100:+-825->1465
    //   2312: goto -1343 -> 969
    //   2315: aload 34
    //   2317: astore 33
    //   2319: goto -1654 -> 665
    //   2322: goto -614 -> 1708
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2325	0	this	cr
    //   162	1930	1	k	int
    //   173	1887	2	m	int
    //   183	1375	3	n	int
    //   194	1213	4	i1	int
    //   206	1207	5	i2	int
    //   218	1191	6	i3	int
    //   230	1181	7	i4	int
    //   241	1906	8	i5	int
    //   253	228	9	i6	int
    //   265	1495	10	i7	int
    //   277	1856	11	i8	int
    //   289	1816	12	i9	int
    //   301	1818	13	i10	int
    //   313	1630	14	i11	int
    //   325	1632	15	i12	int
    //   359	889	16	i13	int
    //   371	1280	17	i14	int
    //   383	1256	18	i15	int
    //   395	103	19	i16	int
    //   407	152	20	i17	int
    //   419	151	21	i18	int
    //   431	150	22	i19	int
    //   443	149	23	i20	int
    //   455	148	24	i21	int
    //   587	1402	25	i22	int
    //   487	1802	26	i23	int
    //   26	2187	27	l1	long
    //   1093	799	29	l2	long
    //   60	1728	31	bool1	boolean
    //   471	800	32	bool2	boolean
    //   619	389	33	localObject1	Object
    //   1020	8	33	localException1	Exception
    //   1036	1	33	localException2	Exception
    //   1061	386	33	localObject2	Object
    //   1453	16	33	localObject3	Object
    //   1551	627	33	localObject4	Object
    //   2262	5	33	localObject5	Object
    //   2269	1	33	localURISyntaxException1	java.net.URISyntaxException
    //   2317	1	33	localObject6	Object
    //   490	1180	34	localObject7	Object
    //   2274	1	34	localException3	Exception
    //   2280	36	34	localObject8	Object
    //   888	13	35	localIterator	Iterator
    //   1111	1	35	localRemoteException	android.os.RemoteException
    //   1268	164	35	localObject9	Object
    //   1439	3	35	localURISyntaxException2	java.net.URISyntaxException
    //   1448	1	35	localObject10	Object
    //   493	807	36	localObject11	Object
    //   99	1766	37	localArrayList	ArrayList
    //   113	2074	38	localCursor	android.database.Cursor
    //   39	1479	39	localContentResolver	ContentResolver
    //   32	1373	40	localContext	Context
    //   46	1286	41	localPackageManager	PackageManager
    //   53	1726	42	localAppWidgetManager	android.appwidget.AppWidgetManager
    //   150	1869	43	arrayOfBy	by[][][]
    //   504	342	44	str1	String
    //   565	221	45	str2	String
    //   576	217	46	str3	String
    //   598	209	47	str4	String
    //   609	205	48	str5	String
    //   627	798	49	localIntent	Intent
    //   1254	50	50	localObject12	Object
    // Exception table:
    //   from	to	target	type
    //   478	489	1020	java/lang/Exception
    //   495	515	1020	java/lang/Exception
    //   556	621	1020	java/lang/Exception
    //   621	629	1020	java/lang/Exception
    //   639	658	1020	java/lang/Exception
    //   658	665	1020	java/lang/Exception
    //   665	697	1020	java/lang/Exception
    //   702	842	1020	java/lang/Exception
    //   842	890	1020	java/lang/Exception
    //   890	952	1020	java/lang/Exception
    //   957	966	1020	java/lang/Exception
    //   969	983	1020	java/lang/Exception
    //   983	1017	1020	java/lang/Exception
    //   1140	1164	1020	java/lang/Exception
    //   1167	1210	1020	java/lang/Exception
    //   1210	1245	1020	java/lang/Exception
    //   1245	1256	1020	java/lang/Exception
    //   1259	1266	1020	java/lang/Exception
    //   1275	1283	1020	java/lang/Exception
    //   1292	1299	1020	java/lang/Exception
    //   1398	1436	1020	java/lang/Exception
    //   1441	1446	1020	java/lang/Exception
    //   1465	1474	1020	java/lang/Exception
    //   1477	1530	1020	java/lang/Exception
    //   1533	1674	1020	java/lang/Exception
    //   1674	1684	1020	java/lang/Exception
    //   1708	1742	1020	java/lang/Exception
    //   1745	1754	1020	java/lang/Exception
    //   1757	1787	1020	java/lang/Exception
    //   1797	1816	1020	java/lang/Exception
    //   1816	1875	1020	java/lang/Exception
    //   1878	1977	1020	java/lang/Exception
    //   1991	2000	1020	java/lang/Exception
    //   2003	2054	1020	java/lang/Exception
    //   2057	2183	1020	java/lang/Exception
    //   152	457	1036	java/lang/Exception
    //   457	473	1036	java/lang/Exception
    //   1022	1033	1036	java/lang/Exception
    //   1095	1108	1111	android/os/RemoteException
    //   1275	1283	1439	java/net/URISyntaxException
    //   152	457	1453	finally
    //   457	473	1453	finally
    //   478	489	1453	finally
    //   495	515	1453	finally
    //   556	621	1453	finally
    //   621	629	1453	finally
    //   639	658	1453	finally
    //   658	665	1453	finally
    //   665	697	1453	finally
    //   702	842	1453	finally
    //   842	890	1453	finally
    //   890	952	1453	finally
    //   957	966	1453	finally
    //   969	983	1453	finally
    //   983	1017	1453	finally
    //   1022	1033	1453	finally
    //   1140	1164	1453	finally
    //   1167	1210	1453	finally
    //   1210	1245	1453	finally
    //   1245	1256	1453	finally
    //   1259	1266	1453	finally
    //   1275	1283	1453	finally
    //   1292	1299	1453	finally
    //   1312	1323	1453	finally
    //   1327	1351	1453	finally
    //   1355	1363	1453	finally
    //   1367	1374	1453	finally
    //   1378	1385	1453	finally
    //   1398	1436	1453	finally
    //   1441	1446	1453	finally
    //   1465	1474	1453	finally
    //   1477	1530	1453	finally
    //   1533	1674	1453	finally
    //   1674	1684	1453	finally
    //   1708	1742	1453	finally
    //   1745	1754	1453	finally
    //   1757	1787	1453	finally
    //   1797	1816	1453	finally
    //   1816	1875	1453	finally
    //   1878	1977	1453	finally
    //   1991	2000	1453	finally
    //   2003	2054	1453	finally
    //   2057	2183	1453	finally
    //   2233	2246	2262	finally
    //   2247	2257	2262	finally
    //   2264	2266	2262	finally
    //   621	629	2269	java/net/URISyntaxException
    //   1312	1323	2274	java/lang/Exception
    //   1327	1351	2274	java/lang/Exception
    //   1355	1363	2274	java/lang/Exception
    //   1367	1374	2274	java/lang/Exception
    //   1378	1385	2274	java/lang/Exception
  }
  
  private void e()
  {
    final long l = SystemClock.uptimeMillis();
    LauncherModel.a(this.b, false);
    final cp localCp = (cp)LauncherModel.e(this.b).get();
    if (localCp == null)
    {
      Log.w("Launcher.Model", "LoaderTask running with no launcher");
      return;
    }
    if (localCp.l())
    {
      LauncherModel.j(this.b);
      return;
    }
    LauncherModel.d(this.b).a(new Runnable()
    {
      public final void run()
      {
        cp localCp = cr.this.a(localCp);
        if (localCp != null) {
          localCp.X();
        }
      }
    });
    int n = localCp.W();
    Object localObject3 = LauncherModel.a(this.b);
    Object localObject2 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.7D));
    Object localObject1 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.3D));
    final ArrayList localArrayList = new ArrayList(5);
    localObject3 = ((ArrayList)localObject3).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      by localBy = (by)((Iterator)localObject3).next();
      if (localBy.s == -101L) {
        localArrayList.add(localBy);
      } else if (localBy.t == n) {
        ((ArrayList)localObject2).add(localBy);
      } else {
        ((ArrayList)localObject1).add(localBy);
      }
    }
    LauncherModel.d(this.b).a(new Runnable()
    {
      public final void run()
      {
        cp localCp = cr.this.a(localCp);
        if (localCp != null) {
          localCp.a(localArrayList, 0, localArrayList.size());
        }
      }
    });
    int i1 = ((ArrayList)localObject2).size();
    final int k = 0;
    if (k < i1)
    {
      if (k + 2 <= i1) {}
      for (m = 2;; m = i1 - k)
      {
        LauncherModel.d(this.b).a(new Runnable()
        {
          public final void run()
          {
            cp localCp = cr.this.a(localCp);
            if (localCp != null) {
              localCp.a(this.b, k, k + m);
            }
          }
        });
        k += 2;
        break;
      }
    }
    final int m = LauncherModel.f.size();
    k = 0;
    while (k < m)
    {
      localObject2 = (ck)LauncherModel.f.get(k);
      if (((ck)localObject2).t == n) {
        LauncherModel.d(this.b).a(new Runnable()
        {
          public final void run()
          {
            cp localCp = cr.this.a(localCp);
            if (localCp != null) {
              localCp.a(this.b);
            }
          }
        });
      }
      k += 1;
    }
    LauncherModel.d(this.b).a(new Runnable()
    {
      public final void run() {}
    });
    i1 = ((ArrayList)localObject1).size();
    k = 0;
    if (k < i1)
    {
      if (k + 2 <= i1) {}
      for (m = 2;; m = i1 - k)
      {
        LauncherModel.d(this.b).a(new Runnable()
        {
          public final void run()
          {
            cp localCp = cr.this.a(localCp);
            if (localCp != null) {
              localCp.a(this.b, k, k + m);
            }
          }
        });
        k += 2;
        break;
      }
    }
    m = LauncherModel.f.size();
    k = 0;
    while (k < m)
    {
      localObject1 = (ck)LauncherModel.f.get(k);
      if (((ck)localObject1).t != n) {
        LauncherModel.d(this.b).a(new Runnable()
        {
          public final void run()
          {
            cp localCp = cr.this.a(localCp);
            if (localCp != null) {
              localCp.a(this.b);
            }
          }
        });
      }
      k += 1;
    }
    localObject1 = new HashMap(LauncherModel.h);
    LauncherModel.d(this.b).a(new Runnable()
    {
      public final void run()
      {
        cp localCp = cr.this.a(localCp);
        if (localCp != null) {
          localCp.a(this.b);
        }
      }
    });
    LauncherModel.d(this.b).a(new Runnable()
    {
      public final void run()
      {
        cp localCp = cr.this.a(localCp);
        if (localCp != null) {
          localCp.Y();
        }
      }
    });
    LauncherModel.d(this.b).a(new Runnable()
    {
      public final void run() {}
    });
    if ((a(localCp) != null) && (this.b.b != null))
    {
      this.b.b.a(LauncherModel.k(this.b), LauncherModel.d(this.b), localCp);
      this.b.b = null;
    }
    LauncherModel.a(this.b, true);
  }
  
  /* Error */
  private void f()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 59	com/cyou/cma/clauncher/cr:j	Z
    //   4: ifne +13 -> 17
    //   7: aload_0
    //   8: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   11: invokestatic 783	com/cyou/cma/clauncher/LauncherModel:l	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   14: ifne +1029 -> 1043
    //   17: invokestatic 330	android/os/SystemClock:uptimeMillis	()J
    //   20: lstore 4
    //   22: aload_0
    //   23: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   26: invokestatic 712	com/cyou/cma/clauncher/LauncherModel:e	(Lcom/cyou/cma/clauncher/LauncherModel;)Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 716	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 718	com/cyou/cma/clauncher/cp
    //   35: ifnull +969 -> 1004
    //   38: new 118	android/content/Intent
    //   41: dup
    //   42: ldc 120
    //   44: aconst_null
    //   45: invokespecial 123	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   48: astore 12
    //   50: aload 12
    //   52: ldc 125
    //   54: invokevirtual 129	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   57: pop
    //   58: aload_0
    //   59: getfield 61	com/cyou/cma/clauncher/cr:c	Landroid/content/Context;
    //   62: invokevirtual 133	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   65: astore 13
    //   67: aload 13
    //   69: sipush 8192
    //   72: invokevirtual 787	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   75: astore 9
    //   77: aload_0
    //   78: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   81: aload 9
    //   83: invokeinterface 145 1 0
    //   88: invokestatic 790	com/cyou/cma/clauncher/LauncherModel:a	(Lcom/cyou/cma/clauncher/LauncherModel;I)I
    //   91: pop
    //   92: invokestatic 795	com/cyou/cma/clauncher/b:b	()Ljava/util/HashMap;
    //   95: astore 14
    //   97: invokestatic 798	com/cyou/cma/clauncher/b:a	()Ljava/util/Map;
    //   100: astore 15
    //   102: aload_0
    //   103: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   106: getfield 801	com/cyou/cma/clauncher/LauncherModel:c	Lcom/cyou/cma/clauncher/c;
    //   109: astore 9
    //   111: aload 9
    //   113: getfield 805	com/cyou/cma/clauncher/c:a	Ljava/util/ArrayList;
    //   116: invokevirtual 347	java/util/ArrayList:clear	()V
    //   119: aload 9
    //   121: getfield 807	com/cyou/cma/clauncher/c:b	Ljava/util/ArrayList;
    //   124: invokevirtual 347	java/util/ArrayList:clear	()V
    //   127: aload 9
    //   129: getfield 809	com/cyou/cma/clauncher/c:c	Ljava/util/ArrayList;
    //   132: invokevirtual 347	java/util/ArrayList:clear	()V
    //   135: aload 9
    //   137: getfield 811	com/cyou/cma/clauncher/c:d	Ljava/util/ArrayList;
    //   140: invokevirtual 347	java/util/ArrayList:clear	()V
    //   143: aload 14
    //   145: invokevirtual 351	java/util/HashMap:clear	()V
    //   148: aload 15
    //   150: invokeinterface 814 1 0
    //   155: new 344	java/util/ArrayList
    //   158: dup
    //   159: invokespecial 356	java/util/ArrayList:<init>	()V
    //   162: astore 16
    //   164: aload_0
    //   165: getfield 61	com/cyou/cma/clauncher/cr:c	Landroid/content/Context;
    //   168: invokevirtual 105	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   171: getstatic 110	com/cyou/cma/clauncher/cx:a	Landroid/net/Uri;
    //   174: bipush 11
    //   176: anewarray 446	java/lang/String
    //   179: dup
    //   180: iconst_0
    //   181: ldc_w 378
    //   184: aastore
    //   185: dup
    //   186: iconst_1
    //   187: ldc -100
    //   189: aastore
    //   190: dup
    //   191: iconst_2
    //   192: ldc -84
    //   194: aastore
    //   195: dup
    //   196: iconst_3
    //   197: ldc -51
    //   199: aastore
    //   200: dup
    //   201: iconst_4
    //   202: ldc -55
    //   204: aastore
    //   205: dup
    //   206: iconst_5
    //   207: ldc -73
    //   209: aastore
    //   210: dup
    //   211: bipush 6
    //   213: ldc -53
    //   215: aastore
    //   216: dup
    //   217: bipush 7
    //   219: ldc -66
    //   221: aastore
    //   222: dup
    //   223: bipush 8
    //   225: ldc_w 816
    //   228: aastore
    //   229: dup
    //   230: bipush 9
    //   232: ldc_w 818
    //   235: aastore
    //   236: dup
    //   237: bipush 10
    //   239: ldc_w 418
    //   242: aastore
    //   243: aconst_null
    //   244: aconst_null
    //   245: ldc_w 820
    //   248: invokevirtual 363	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   251: astore 17
    //   253: aload 17
    //   255: invokeinterface 434 1 0
    //   260: ifeq +638 -> 898
    //   263: aload 17
    //   265: bipush 6
    //   267: invokeinterface 438 2 0
    //   272: istore_1
    //   273: aload 17
    //   275: iconst_4
    //   276: invokeinterface 438 2 0
    //   281: istore_2
    //   282: aload 17
    //   284: bipush 7
    //   286: invokeinterface 438 2 0
    //   291: istore_3
    //   292: aload 17
    //   294: iconst_0
    //   295: invokeinterface 480 2 0
    //   300: lstore 6
    //   302: aload 17
    //   304: bipush 10
    //   306: invokeinterface 442 2 0
    //   311: astore 11
    //   313: aload 17
    //   315: iconst_5
    //   316: invokeinterface 442 2 0
    //   321: astore 9
    //   323: iload_2
    //   324: ifne +432 -> 756
    //   327: aload 17
    //   329: iconst_1
    //   330: invokeinterface 442 2 0
    //   335: astore 10
    //   337: aload 17
    //   339: iconst_2
    //   340: invokeinterface 442 2 0
    //   345: astore 18
    //   347: aload 10
    //   349: aload 18
    //   351: invokestatic 823	com/cyou/cma/as:b	(Ljava/lang/String;Ljava/lang/String;)Z
    //   354: ifne -101 -> 253
    //   357: aload 12
    //   359: new 648	android/content/ComponentName
    //   362: dup
    //   363: aload 10
    //   365: aload 18
    //   367: invokespecial 825	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   370: invokevirtual 829	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   373: pop
    //   374: aload 13
    //   376: aload 12
    //   378: iconst_0
    //   379: invokevirtual 833	android/content/pm/PackageManager:resolveActivity	(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   382: astore 11
    //   384: aload 11
    //   386: ifnonnull +248 -> 634
    //   389: new 835	com/cyou/cma/clauncher/f
    //   392: dup
    //   393: invokespecial 836	com/cyou/cma/clauncher/f:<init>	()V
    //   396: astore 11
    //   398: aload 11
    //   400: iconst_1
    //   401: putfield 838	com/cyou/cma/clauncher/f:h	Z
    //   404: aload 11
    //   406: iconst_1
    //   407: putfield 840	com/cyou/cma/clauncher/f:f	I
    //   410: aload 11
    //   412: new 648	android/content/ComponentName
    //   415: dup
    //   416: aload 10
    //   418: aload 18
    //   420: invokespecial 825	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   423: putfield 842	com/cyou/cma/clauncher/f:d	Landroid/content/ComponentName;
    //   426: aload 9
    //   428: astore 10
    //   430: aload 9
    //   432: ifnonnull +7 -> 439
    //   435: ldc -75
    //   437: astore 10
    //   439: aload 11
    //   441: aload 10
    //   443: putfield 843	com/cyou/cma/clauncher/f:l	Ljava/lang/CharSequence;
    //   446: aload 11
    //   448: aload 11
    //   450: getfield 842	com/cyou/cma/clauncher/f:d	Landroid/content/ComponentName;
    //   453: invokevirtual 846	com/cyou/cma/clauncher/f:a	(Landroid/content/ComponentName;)V
    //   456: aload 11
    //   458: aload_0
    //   459: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   462: invokestatic 314	com/cyou/cma/clauncher/LauncherModel:i	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/bs;
    //   465: invokevirtual 849	com/cyou/cma/clauncher/bs:d	()Landroid/graphics/Bitmap;
    //   468: putfield 852	com/cyou/cma/clauncher/f:b	Landroid/graphics/Bitmap;
    //   471: aload 11
    //   473: astore 9
    //   475: aload 9
    //   477: lload 6
    //   479: putfield 853	com/cyou/cma/clauncher/f:q	J
    //   482: aload 17
    //   484: iconst_3
    //   485: invokeinterface 480 2 0
    //   490: lconst_1
    //   491: lcmp
    //   492: ifne +169 -> 661
    //   495: iconst_1
    //   496: istore 8
    //   498: aload 9
    //   500: iload 8
    //   502: putfield 855	com/cyou/cma/clauncher/f:m	Z
    //   505: aload 17
    //   507: bipush 9
    //   509: invokeinterface 480 2 0
    //   514: lconst_1
    //   515: lcmp
    //   516: ifne +151 -> 667
    //   519: iconst_1
    //   520: istore 8
    //   522: aload 9
    //   524: iload 8
    //   526: putfield 856	com/cyou/cma/clauncher/f:e	Z
    //   529: aload 9
    //   531: invokevirtual 858	com/cyou/cma/clauncher/f:a	()V
    //   534: aload 9
    //   536: iload_3
    //   537: putfield 861	com/cyou/cma/clauncher/f:H	I
    //   540: aload 9
    //   542: iload_2
    //   543: putfield 864	com/cyou/cma/clauncher/f:r	I
    //   546: aload 9
    //   548: iload_1
    //   549: i2l
    //   550: putfield 865	com/cyou/cma/clauncher/f:s	J
    //   553: aload 9
    //   555: iconst_1
    //   556: putfield 868	com/cyou/cma/clauncher/f:G	I
    //   559: aload_0
    //   560: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   563: getfield 801	com/cyou/cma/clauncher/LauncherModel:c	Lcom/cyou/cma/clauncher/c;
    //   566: aload 9
    //   568: invokevirtual 871	com/cyou/cma/clauncher/c:a	(Lcom/cyou/cma/clauncher/f;)Z
    //   571: ifeq +146 -> 717
    //   574: iload_1
    //   575: sipush 65336
    //   578: if_icmpne +95 -> 673
    //   581: aload 15
    //   583: aload 9
    //   585: getfield 853	com/cyou/cma/clauncher/f:q	J
    //   588: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   591: invokeinterface 874 2 0
    //   596: ifne -343 -> 253
    //   599: aload 15
    //   601: aload 9
    //   603: getfield 853	com/cyou/cma/clauncher/f:q	J
    //   606: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   609: aload 9
    //   611: invokeinterface 875 3 0
    //   616: pop
    //   617: goto -364 -> 253
    //   620: astore 9
    //   622: aload_0
    //   623: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   626: iconst_0
    //   627: invokestatic 790	com/cyou/cma/clauncher/LauncherModel:a	(Lcom/cyou/cma/clauncher/LauncherModel;I)I
    //   630: pop
    //   631: goto -539 -> 92
    //   634: new 835	com/cyou/cma/clauncher/f
    //   637: dup
    //   638: aload 13
    //   640: aload 11
    //   642: aload_0
    //   643: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   646: invokestatic 314	com/cyou/cma/clauncher/LauncherModel:i	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/bs;
    //   649: aload_0
    //   650: getfield 68	com/cyou/cma/clauncher/cr:h	Ljava/util/HashMap;
    //   653: invokespecial 878	com/cyou/cma/clauncher/f:<init>	(Landroid/content/pm/PackageManager;Landroid/content/pm/ResolveInfo;Lcom/cyou/cma/clauncher/bs;Ljava/util/HashMap;)V
    //   656: astore 9
    //   658: goto -183 -> 475
    //   661: iconst_0
    //   662: istore 8
    //   664: goto -166 -> 498
    //   667: iconst_0
    //   668: istore 8
    //   670: goto -148 -> 522
    //   673: aload 14
    //   675: iload_1
    //   676: i2l
    //   677: invokestatic 880	com/cyou/cma/clauncher/LauncherModel:b	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/bc;
    //   680: astore 10
    //   682: aload 10
    //   684: iconst_1
    //   685: putfield 881	com/cyou/cma/clauncher/bc:G	I
    //   688: aload 10
    //   690: iconst_1
    //   691: putfield 882	com/cyou/cma/clauncher/bc:r	I
    //   694: aload 10
    //   696: ldc2_w 883
    //   699: putfield 627	com/cyou/cma/clauncher/bc:s	J
    //   702: aload 10
    //   704: invokevirtual 887	com/cyou/cma/clauncher/bc:b_	()V
    //   707: aload 10
    //   709: aload 9
    //   711: invokevirtual 528	com/cyou/cma/clauncher/bc:d	(Lcom/cyou/cma/clauncher/by;)V
    //   714: goto -461 -> 253
    //   717: aload 16
    //   719: aload 9
    //   721: invokevirtual 620	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   724: pop
    //   725: ldc_w 695
    //   728: new 255	java/lang/StringBuilder
    //   731: dup
    //   732: ldc_w 889
    //   735: invokespecial 260	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   738: aload 9
    //   740: getfield 843	com/cyou/cma/clauncher/f:l	Ljava/lang/CharSequence;
    //   743: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   746: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   749: invokestatic 891	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   752: pop
    //   753: goto -500 -> 253
    //   756: iload_2
    //   757: iconst_1
    //   758: if_icmpne -505 -> 253
    //   761: aload 14
    //   763: lload 6
    //   765: invokestatic 880	com/cyou/cma/clauncher/LauncherModel:b	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/bc;
    //   768: astore 18
    //   770: aload 9
    //   772: astore 10
    //   774: aload 9
    //   776: ifnonnull +7 -> 783
    //   779: ldc -75
    //   781: astore 10
    //   783: aload 18
    //   785: aload 10
    //   787: putfield 625	com/cyou/cma/clauncher/bc:l	Ljava/lang/CharSequence;
    //   790: aload 18
    //   792: ldc2_w 883
    //   795: putfield 627	com/cyou/cma/clauncher/bc:s	J
    //   798: aload 18
    //   800: lload 6
    //   802: putfield 626	com/cyou/cma/clauncher/bc:q	J
    //   805: aload 18
    //   807: iload_3
    //   808: putfield 892	com/cyou/cma/clauncher/bc:H	I
    //   811: aload 18
    //   813: iconst_1
    //   814: putfield 881	com/cyou/cma/clauncher/bc:G	I
    //   817: aload 18
    //   819: iconst_1
    //   820: putfield 882	com/cyou/cma/clauncher/bc:r	I
    //   823: aload 17
    //   825: bipush 8
    //   827: invokeinterface 438 2 0
    //   832: iconst_1
    //   833: if_icmpne +59 -> 892
    //   836: iconst_1
    //   837: istore 8
    //   839: aload 18
    //   841: iload 8
    //   843: putfield 893	com/cyou/cma/clauncher/bc:e	Z
    //   846: aload 18
    //   848: aload 11
    //   850: putfield 633	com/cyou/cma/clauncher/bc:D	Ljava/lang/String;
    //   853: aload 15
    //   855: aload 18
    //   857: getfield 626	com/cyou/cma/clauncher/bc:q	J
    //   860: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   863: invokeinterface 874 2 0
    //   868: ifne -615 -> 253
    //   871: aload 15
    //   873: aload 18
    //   875: getfield 626	com/cyou/cma/clauncher/bc:q	J
    //   878: invokestatic 533	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   881: aload 18
    //   883: invokeinterface 875 3 0
    //   888: pop
    //   889: goto -636 -> 253
    //   892: iconst_0
    //   893: istore 8
    //   895: goto -56 -> 839
    //   898: aload 17
    //   900: ifnull +10 -> 910
    //   903: aload 17
    //   905: invokeinterface 547 1 0
    //   910: aload_0
    //   911: getfield 61	com/cyou/cma/clauncher/cr:c	Landroid/content/Context;
    //   914: aload 16
    //   916: invokestatic 896	com/cyou/cma/clauncher/b:b	(Landroid/content/Context;Ljava/util/ArrayList;)V
    //   919: aload 16
    //   921: invokevirtual 347	java/util/ArrayList:clear	()V
    //   924: ldc_w 695
    //   927: new 255	java/lang/StringBuilder
    //   930: dup
    //   931: ldc_w 898
    //   934: invokespecial 260	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   937: invokestatic 330	android/os/SystemClock:uptimeMillis	()J
    //   940: lload 4
    //   942: lsub
    //   943: invokevirtual 566	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   946: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   949: invokestatic 701	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   952: pop
    //   953: aload_0
    //   954: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   957: getfield 801	com/cyou/cma/clauncher/LauncherModel:c	Lcom/cyou/cma/clauncher/c;
    //   960: getfield 805	com/cyou/cma/clauncher/c:a	Ljava/util/ArrayList;
    //   963: astore 9
    //   965: new 778	com/cyou/cma/clauncher/aw
    //   968: dup
    //   969: invokespecial 899	com/cyou/cma/clauncher/aw:<init>	()V
    //   972: astore 10
    //   974: aload 10
    //   976: aload_0
    //   977: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   980: invokestatic 219	com/cyou/cma/clauncher/LauncherModel:k	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/LauncherApplication;
    //   983: aload 9
    //   985: aload_0
    //   986: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   989: invokestatic 901	com/cyou/cma/clauncher/LauncherModel:r	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   992: invokevirtual 904	com/cyou/cma/clauncher/aw:a	(Landroid/content/Context;Ljava/util/ArrayList;Z)V
    //   995: aload_0
    //   996: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   999: aload 10
    //   1001: putfield 776	com/cyou/cma/clauncher/LauncherModel:b	Lcom/cyou/cma/clauncher/aw;
    //   1004: aload_0
    //   1005: monitorenter
    //   1006: aload_0
    //   1007: getfield 431	com/cyou/cma/clauncher/cr:f	Z
    //   1010: ifeq +16 -> 1026
    //   1013: aload_0
    //   1014: monitorexit
    //   1015: return
    //   1016: astore 9
    //   1018: aload 9
    //   1020: invokevirtual 905	java/lang/Exception:printStackTrace	()V
    //   1023: goto -113 -> 910
    //   1026: aload_0
    //   1027: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1030: invokestatic 907	com/cyou/cma/clauncher/LauncherModel:m	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   1033: pop
    //   1034: aload_0
    //   1035: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1038: invokestatic 910	com/cyou/cma/clauncher/LauncherModel:n	(Lcom/cyou/cma/clauncher/LauncherModel;)V
    //   1041: aload_0
    //   1042: monitorexit
    //   1043: aload_0
    //   1044: invokespecial 303	com/cyou/cma/clauncher/cr:g	()V
    //   1047: new 255	java/lang/StringBuilder
    //   1050: dup
    //   1051: invokespecial 911	java/lang/StringBuilder:<init>	()V
    //   1054: aload_0
    //   1055: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1058: invokestatic 219	com/cyou/cma/clauncher/LauncherModel:k	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/LauncherApplication;
    //   1061: invokevirtual 912	com/cyou/cma/clauncher/LauncherApplication:getPackageName	()Ljava/lang/String;
    //   1064: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1067: ldc_w 914
    //   1070: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1073: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1076: astore 9
    //   1078: aload_0
    //   1079: getfield 52	com/cyou/cma/clauncher/cr:b	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1082: invokestatic 219	com/cyou/cma/clauncher/LauncherModel:k	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/LauncherApplication;
    //   1085: aload 9
    //   1087: iconst_4
    //   1088: invokevirtual 915	com/cyou/cma/clauncher/LauncherApplication:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1091: astore 9
    //   1093: aload 9
    //   1095: ldc_w 917
    //   1098: iconst_0
    //   1099: invokeinterface 476 3 0
    //   1104: ifne -89 -> 1015
    //   1107: new 919	java/lang/Thread
    //   1110: dup
    //   1111: new 20	com/cyou/cma/clauncher/cr$3
    //   1114: dup
    //   1115: aload_0
    //   1116: aload 9
    //   1118: invokespecial 922	com/cyou/cma/clauncher/cr$3:<init>	(Lcom/cyou/cma/clauncher/cr;Landroid/content/SharedPreferences;)V
    //   1121: invokespecial 924	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   1124: invokevirtual 925	java/lang/Thread:start	()V
    //   1127: return
    //   1128: astore 9
    //   1130: aload_0
    //   1131: monitorexit
    //   1132: aload 9
    //   1134: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1135	0	this	cr
    //   272	404	1	k	int
    //   281	478	2	m	int
    //   291	517	3	n	int
    //   20	921	4	l1	long
    //   300	501	6	l2	long
    //   496	398	8	bool	boolean
    //   75	535	9	localObject1	Object
    //   620	1	9	localException1	Exception
    //   656	328	9	localObject2	Object
    //   1016	3	9	localException2	Exception
    //   1076	41	9	localObject3	Object
    //   1128	5	9	localObject4	Object
    //   335	665	10	localObject5	Object
    //   311	538	11	localObject6	Object
    //   48	329	12	localIntent	Intent
    //   65	574	13	localPackageManager	PackageManager
    //   95	667	14	localHashMap	HashMap
    //   100	772	15	localMap	java.util.Map
    //   162	758	16	localArrayList	ArrayList
    //   251	653	17	localCursor	android.database.Cursor
    //   345	537	18	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   67	92	620	java/lang/Exception
    //   903	910	1016	java/lang/Exception
    //   1006	1015	1128	finally
    //   1026	1043	1128	finally
    //   1130	1132	1128	finally
  }
  
  private void g()
  {
    if (this.a) {}
    do
    {
      return;
      localCp = (cp)LauncherModel.e(this.b).get();
    } while (localCp == null);
    if (localCp.l())
    {
      LauncherModel.o(this.b);
      return;
    }
    final cp localCp = a(localCp);
    int k = 0;
    for (;;)
    {
      if (k < this.b.c.a.size())
      {
        if (((f)this.b.c.a.get(k)).h().equals("com.android.vending")) {
          com.cyou.cma.recommend.i.a = true;
        }
      }
      else
      {
        if ((this.b.b != null) && (LauncherModel.p(this.b)))
        {
          this.b.b.a(LauncherModel.k(this.b), LauncherModel.d(this.b), localCp);
          this.b.b = null;
        }
        LauncherModel.d(this.b).a(new Runnable()
        {
          public final void run()
          {
            if (localCp != null)
            {
              localCp.a(b.c());
              localCp.am();
            }
          }
        });
        return;
      }
      k += 1;
    }
  }
  
  final cp a(cp paramCp)
  {
    synchronized (LauncherModel.f(this.b))
    {
      if (this.f) {
        return null;
      }
      if (LauncherModel.e(this.b) == null) {
        return null;
      }
      cp localCp = (cp)LauncherModel.e(this.b).get();
      if (localCp != paramCp) {
        return null;
      }
      if (localCp == null)
      {
        Log.w("Launcher.Model", "no mCallbacks");
        return null;
      }
      return localCp;
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
    Log.d("Launcher.Model", "mItems size=" + LauncherModel.e.size());
  }
  
  public final void run()
  {
    int m = 0;
    ??? = (cp)LauncherModel.e(this.b).get();
    int k;
    if ((??? != null) && (((cp)???).Q())) {
      k = 0;
    }
    for (;;)
    {
      synchronized (LauncherModel.f(this.b))
      {
        if (this.e)
        {
          label48:
          Process.setThreadPriority(m);
          if ((this.i) || (k == 0)) {
            break label216;
          }
          d();
          a(this.c);
          label77:
          if (!this.f) {
            synchronized (LauncherModel.f(this.b))
            {
              if (this.e) {
                Process.setThreadPriority(10);
              }
              if (!this.i)
              {
                if (k == 0) {
                  break label238;
                }
                f();
              }
            }
          }
        }
      }
      synchronized (LauncherModel.f(this.b))
      {
        Process.setThreadPriority(0);
        ??? = LauncherModel.i.keySet().iterator();
        for (;;)
        {
          if (((Iterator)???).hasNext())
          {
            Object localObject2 = ((Iterator)???).next();
            this.b.a(this.c, (ef)localObject2, (byte[])LauncherModel.i.get(localObject2));
            continue;
            k = 1;
            break;
            m = 10;
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
    LauncherModel.i.clear();
    this.c = null;
    synchronized (LauncherModel.f(this.b))
    {
      if (LauncherModel.g(this.b) == this) {
        LauncherModel.h(this.b);
      }
      return;
    }
  }
}
