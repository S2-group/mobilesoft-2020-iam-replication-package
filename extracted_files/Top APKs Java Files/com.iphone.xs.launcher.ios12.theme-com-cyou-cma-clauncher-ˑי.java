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
import com.cyou.cma.ʼˏ;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class ˑי
  implements Runnable
{
  boolean ʻ;
  private Context ʽ;
  private Thread ʾ;
  private boolean ʿ;
  private boolean ˆ;
  private boolean ˈ;
  private HashMap<Object, CharSequence> ˉ;
  private boolean ˊ = false;
  private boolean ˋ = false;
  
  ˑי(LauncherModel paramLauncherModel, Context paramContext, boolean paramBoolean)
  {
    this.ʽ = paramContext;
    this.ʿ = paramBoolean;
    this.ˉ = new HashMap();
  }
  
  ˑי(LauncherModel paramLauncherModel, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this(paramLauncherModel, paramContext, paramBoolean1);
    this.ʻ = paramBoolean4;
  }
  
  private void ʻ(Context paramContext)
  {
    Object localObject = paramContext.getFileStreamPath("flag_all_app");
    boolean bool = ((File)localObject).exists();
    if (bool) {
      new ˑᵎ(this, (File)localObject).start();
    }
    if (!bool) {
      return;
    }
    LauncherModel.ᐧ(this.ʼ);
    localObject = paramContext.getContentResolver();
    ((ContentResolver)localObject).delete(יˑ.ʻ, null, null);
    paramContext = new Intent("android.intent.action.MAIN", null);
    paramContext.addCategory("android.intent.category.LAUNCHER");
    PackageManager localPackageManager = this.ʽ.getPackageManager();
    List localList = localPackageManager.queryIntentActivities(paramContext, 0);
    ContentValues[] arrayOfContentValues = new ContentValues[localList.size()];
    int j = localList.size();
    int i = 0;
    if (i < j)
    {
      paramContext = (ResolveInfo)localList.get(i);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("package_name", paramContext.activityInfo.packageName);
      localContentValues.put("class_name", paramContext.activityInfo.name);
      paramContext = paramContext.loadLabel(localPackageManager);
      if (paramContext == null) {}
      for (paramContext = "";; paramContext = paramContext.toString())
      {
        localContentValues.put("title", ʼˏ.ʻ(paramContext));
        localContentValues.put("app_index", Integer.valueOf(i));
        localContentValues.put("item_type", Integer.valueOf(0));
        localContentValues.put("container", Integer.valueOf(65336));
        localContentValues.put("hide", Integer.valueOf(0));
        arrayOfContentValues[i] = localContentValues;
        i += 1;
        break;
      }
    }
    ((ContentResolver)localObject).bulkInsert(יˑ.ʻ, arrayOfContentValues);
    LauncherModel.ˎ(this.ʼ).ʿ.ʾ();
  }
  
  private static boolean ʻ(ˉʿ[][][] paramArrayOfˉʿ, ˉʿ paramˉʿ)
  {
    int k = paramˉʿ.ᵔ;
    if (paramˉʿ.ᵎ == -101L)
    {
      if (paramArrayOfˉʿ[Launcher.ʻ][paramˉʿ.ᵢ][0] != null)
      {
        Log.e("Launcher.Model", "Error loading shortcut into hotseat " + paramˉʿ + " into position (" + paramˉʿ.ᵔ + ":" + paramˉʿ.ᵢ + "," + paramˉʿ.ⁱ + ") occupied by " + paramArrayOfˉʿ[Launcher.ʻ][paramˉʿ.ᵢ][0]);
        return false;
      }
      paramArrayOfˉʿ[Launcher.ʻ][paramˉʿ.ᵢ][0] = paramˉʿ;
      return true;
    }
    if (paramˉʿ.ᵎ != -100L) {
      return true;
    }
    int i = paramˉʿ.ᵢ;
    int j;
    while (i < paramˉʿ.ᵢ + paramˉʿ.ﹳ)
    {
      j = paramˉʿ.ⁱ;
      while (j < paramˉʿ.ⁱ + paramˉʿ.ﹶ)
      {
        if (paramArrayOfˉʿ[k][i][j] != null)
        {
          Log.e("Launcher.Model", "Error loading shortcut " + paramˉʿ + " into cell (" + k + "-" + paramˉʿ.ᵔ + ":" + i + "," + j + ") occupied by " + paramArrayOfˉʿ[k][i][j]);
          return false;
        }
        j += 1;
      }
      i += 1;
    }
    i = paramˉʿ.ᵢ;
    while (i < paramˉʿ.ᵢ + paramˉʿ.ﹳ)
    {
      j = paramˉʿ.ⁱ;
      while (j < paramˉʿ.ⁱ + paramˉʿ.ﹶ)
      {
        paramArrayOfˉʿ[k][i][j] = paramˉʿ;
        j += 1;
      }
      i += 1;
    }
    return true;
  }
  
  /* Error */
  private void ʾ()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   4: invokestatic 288	com/cyou/cma/clauncher/LauncherModel:ʼ	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   7: ifne +2250 -> 2257
    //   10: aload_0
    //   11: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   14: invokestatic 291	com/cyou/cma/clauncher/LauncherModel:ˊ	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/ˈⁱ;
    //   17: getstatic 296	com/cyou/cma/ʻ/ʻ:ʻ	Lcom/cyou/cma/ʻ/ʻ;
    //   20: invokevirtual 301	com/cyou/cma/clauncher/ˈⁱ:ʻ	(Lcom/cyou/cma/ʻ/ʻ;)V
    //   23: invokestatic 307	android/os/SystemClock:uptimeMillis	()J
    //   26: lstore 27
    //   28: aload_0
    //   29: getfield 35	com/cyou/cma/clauncher/ˑי:ʽ	Landroid/content/Context;
    //   32: astore 40
    //   34: aload 40
    //   36: invokevirtual 81	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   39: astore 39
    //   41: aload 40
    //   43: invokevirtual 109	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: astore 41
    //   48: aload 40
    //   50: invokestatic 313	android/appwidget/AppWidgetManager:getInstance	(Landroid/content/Context;)Landroid/appwidget/AppWidgetManager;
    //   53: astore 42
    //   55: aload 41
    //   57: invokevirtual 316	android/content/pm/PackageManager:isSafeMode	()Z
    //   60: istore 31
    //   62: getstatic 319	com/cyou/cma/clauncher/LauncherModel:ʿ	Ljava/util/ArrayList;
    //   65: invokevirtual 324	java/util/ArrayList:clear	()V
    //   68: getstatic 326	com/cyou/cma/clauncher/LauncherModel:ˆ	Ljava/util/ArrayList;
    //   71: invokevirtual 324	java/util/ArrayList:clear	()V
    //   74: getstatic 327	com/cyou/cma/clauncher/LauncherModel:ˉ	Ljava/util/HashMap;
    //   77: invokevirtual 328	java/util/HashMap:clear	()V
    //   80: getstatic 330	com/cyou/cma/clauncher/LauncherModel:ʾ	Ljava/util/HashMap;
    //   83: invokevirtual 328	java/util/HashMap:clear	()V
    //   86: getstatic 332	com/cyou/cma/clauncher/LauncherModel:ˊ	Ljava/util/HashMap;
    //   89: invokevirtual 328	java/util/HashMap:clear	()V
    //   92: new 321	java/util/ArrayList
    //   95: dup
    //   96: invokespecial 333	java/util/ArrayList:<init>	()V
    //   99: astore 37
    //   101: aload 39
    //   103: getstatic 336	com/cyou/cma/clauncher/יٴ:ʻ	Landroid/net/Uri;
    //   106: aconst_null
    //   107: aconst_null
    //   108: aconst_null
    //   109: aconst_null
    //   110: invokevirtual 340	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   113: astore 38
    //   115: ldc -46
    //   117: iconst_3
    //   118: newarray int
    //   120: dup
    //   121: iconst_0
    //   122: getstatic 224	com/cyou/cma/clauncher/Launcher:ʻ	I
    //   125: iconst_1
    //   126: iadd
    //   127: iastore
    //   128: dup
    //   129: iconst_1
    //   130: invokestatic 342	com/cyou/cma/clauncher/LauncherModel:ˎ	()I
    //   133: iconst_1
    //   134: iadd
    //   135: iastore
    //   136: dup
    //   137: iconst_2
    //   138: invokestatic 345	com/cyou/cma/clauncher/LauncherModel:ˏ	()I
    //   141: iconst_1
    //   142: iadd
    //   143: iastore
    //   144: invokestatic 351	java/lang/reflect/Array:newInstance	(Ljava/lang/Class;[I)Ljava/lang/Object;
    //   147: checkcast 353	[[[Lcom/cyou/cma/clauncher/ˉʿ;
    //   150: astore 43
    //   152: aload 38
    //   154: ldc_w 355
    //   157: invokeinterface 361 2 0
    //   162: istore_1
    //   163: aload 38
    //   165: ldc_w 363
    //   168: invokeinterface 361 2 0
    //   173: istore_2
    //   174: aload 38
    //   176: ldc -97
    //   178: invokeinterface 361 2 0
    //   183: istore_3
    //   184: aload 38
    //   186: ldc_w 365
    //   189: invokeinterface 361 2 0
    //   194: istore 4
    //   196: aload 38
    //   198: ldc_w 367
    //   201: invokeinterface 361 2 0
    //   206: istore 5
    //   208: aload 38
    //   210: ldc_w 369
    //   213: invokeinterface 361 2 0
    //   218: istore 6
    //   220: aload 38
    //   222: ldc_w 371
    //   225: invokeinterface 361 2 0
    //   230: istore 7
    //   232: aload 38
    //   234: ldc -77
    //   236: invokeinterface 361 2 0
    //   241: istore 8
    //   243: aload 38
    //   245: ldc_w 373
    //   248: invokeinterface 361 2 0
    //   253: istore 9
    //   255: aload 38
    //   257: ldc_w 375
    //   260: invokeinterface 361 2 0
    //   265: istore 10
    //   267: aload 38
    //   269: ldc_w 377
    //   272: invokeinterface 361 2 0
    //   277: istore 11
    //   279: aload 38
    //   281: ldc_w 379
    //   284: invokeinterface 361 2 0
    //   289: istore 12
    //   291: aload 38
    //   293: ldc_w 381
    //   296: invokeinterface 361 2 0
    //   301: istore 13
    //   303: aload 38
    //   305: ldc_w 383
    //   308: invokeinterface 361 2 0
    //   313: istore 14
    //   315: aload 38
    //   317: ldc_w 385
    //   320: invokeinterface 361 2 0
    //   325: istore 15
    //   327: aload 38
    //   329: ldc_w 387
    //   332: invokeinterface 361 2 0
    //   337: pop
    //   338: aload 38
    //   340: ldc_w 389
    //   343: invokeinterface 361 2 0
    //   348: pop
    //   349: aload 38
    //   351: ldc_w 391
    //   354: invokeinterface 361 2 0
    //   359: istore 16
    //   361: aload 38
    //   363: ldc_w 393
    //   366: invokeinterface 361 2 0
    //   371: istore 17
    //   373: aload 38
    //   375: ldc_w 395
    //   378: invokeinterface 361 2 0
    //   383: istore 18
    //   385: aload 38
    //   387: ldc_w 397
    //   390: invokeinterface 361 2 0
    //   395: istore 19
    //   397: aload 38
    //   399: ldc_w 399
    //   402: invokeinterface 361 2 0
    //   407: istore 20
    //   409: aload 38
    //   411: ldc_w 401
    //   414: invokeinterface 361 2 0
    //   419: istore 21
    //   421: aload 38
    //   423: ldc_w 402
    //   426: invokeinterface 361 2 0
    //   431: istore 22
    //   433: aload 38
    //   435: ldc_w 404
    //   438: invokeinterface 361 2 0
    //   443: istore 23
    //   445: aload 38
    //   447: ldc_w 406
    //   450: invokeinterface 361 2 0
    //   455: istore 24
    //   457: aload_0
    //   458: getfield 408	com/cyou/cma/clauncher/ˑי:ˆ	Z
    //   461: ifne +1725 -> 2186
    //   464: aload 38
    //   466: invokeinterface 411 1 0
    //   471: istore 32
    //   473: iload 32
    //   475: ifeq +1711 -> 2186
    //   478: aload 38
    //   480: iload 9
    //   482: invokeinterface 415 2 0
    //   487: istore 26
    //   489: aconst_null
    //   490: astore 34
    //   492: aconst_null
    //   493: astore 36
    //   495: aload 38
    //   497: iload 19
    //   499: invokeinterface 419 2 0
    //   504: astore 44
    //   506: ldc_w 421
    //   509: aload 44
    //   511: invokevirtual 427	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   514: pop
    //   515: iload 26
    //   517: tableswitch	default:+1768->2285, 0:+39->556, 1:+39->556, 2:+1016->1533, 3:+1768->2285, 4:+1240->1757, 5:+1540->2057
    //   556: aload 38
    //   558: iload 20
    //   560: invokeinterface 419 2 0
    //   565: astore 45
    //   567: aload 38
    //   569: iload 21
    //   571: invokeinterface 419 2 0
    //   576: astore 46
    //   578: aload 38
    //   580: iload 22
    //   582: invokeinterface 415 2 0
    //   587: istore 25
    //   589: aload 38
    //   591: iload 23
    //   593: invokeinterface 419 2 0
    //   598: astore 47
    //   600: aload 38
    //   602: iload 24
    //   604: invokeinterface 419 2 0
    //   609: astore 48
    //   611: aload 38
    //   613: iload_2
    //   614: invokeinterface 419 2 0
    //   619: astore 33
    //   621: aload 33
    //   623: iconst_0
    //   624: invokestatic 431	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   627: astore 49
    //   629: iload 26
    //   631: ifne +536 -> 1167
    //   634: aload 44
    //   636: ifnull +504 -> 1140
    //   639: aload_0
    //   640: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   643: aload 41
    //   645: aload 49
    //   647: aload_0
    //   648: getfield 42	com/cyou/cma/clauncher/ˑי:ˉ	Ljava/util/HashMap;
    //   651: aload 44
    //   653: invokevirtual 434	com/cyou/cma/clauncher/LauncherModel:ʻ	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Ljava/util/HashMap;Ljava/lang/String;)Lcom/cyou/cma/clauncher/ᐧˆ;
    //   656: astore 33
    //   658: aload 33
    //   660: aload 49
    //   662: putfield 439	com/cyou/cma/clauncher/ᐧˆ:ʼ	Landroid/content/Intent;
    //   665: ldc_w 421
    //   668: aload 44
    //   670: invokevirtual 427	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   673: ifeq +24 -> 697
    //   676: aload 40
    //   678: ldc_w 441
    //   681: iconst_0
    //   682: invokevirtual 445	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   685: ldc_w 447
    //   688: iconst_1
    //   689: invokeinterface 453 3 0
    //   694: ifeq -237 -> 457
    //   697: aload 33
    //   699: ifnull +778 -> 1477
    //   702: aload 33
    //   704: aload 38
    //   706: iload_1
    //   707: invokeinterface 457 2 0
    //   712: putfield 459	com/cyou/cma/clauncher/ᐧˆ:ᐧ	J
    //   715: aload 38
    //   717: iload 8
    //   719: invokeinterface 415 2 0
    //   724: istore 26
    //   726: aload 33
    //   728: iload 26
    //   730: i2l
    //   731: putfield 460	com/cyou/cma/clauncher/ᐧˆ:ᵎ	J
    //   734: aload 33
    //   736: aload 38
    //   738: iload 11
    //   740: invokeinterface 415 2 0
    //   745: putfield 461	com/cyou/cma/clauncher/ᐧˆ:ᵔ	I
    //   748: aload 33
    //   750: aload 38
    //   752: iload 12
    //   754: invokeinterface 415 2 0
    //   759: putfield 462	com/cyou/cma/clauncher/ᐧˆ:ᵢ	I
    //   762: aload 33
    //   764: aload 38
    //   766: iload 13
    //   768: invokeinterface 415 2 0
    //   773: putfield 463	com/cyou/cma/clauncher/ᐧˆ:ⁱ	I
    //   776: aload 33
    //   778: aload 44
    //   780: putfield 465	com/cyou/cma/clauncher/ᐧˆ:ˋ	Ljava/lang/String;
    //   783: aload 33
    //   785: aload 45
    //   787: putfield 468	com/cyou/cma/clauncher/ᐧˆ:י	Ljava/lang/String;
    //   790: aload 33
    //   792: aload 46
    //   794: putfield 471	com/cyou/cma/clauncher/ᐧˆ:ˑ	Ljava/lang/String;
    //   797: aload 33
    //   799: iload 25
    //   801: putfield 474	com/cyou/cma/clauncher/ᐧˆ:ٴ	I
    //   804: aload 33
    //   806: aload 47
    //   808: putfield 477	com/cyou/cma/clauncher/ᐧˆ:ˉˉ	Ljava/lang/String;
    //   811: aload 33
    //   813: aload 48
    //   815: putfield 480	com/cyou/cma/clauncher/ᐧˆ:ˈˈ	Ljava/lang/String;
    //   818: ldc_w 421
    //   821: aload 33
    //   823: getfield 465	com/cyou/cma/clauncher/ᐧˆ:ˋ	Ljava/lang/String;
    //   826: invokevirtual 427	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   829: ifne +13 -> 842
    //   832: aload 43
    //   834: aload 33
    //   836: invokestatic 482	com/cyou/cma/clauncher/ˑי:ʻ	([[[Lcom/cyou/cma/clauncher/ˉʿ;Lcom/cyou/cma/clauncher/ˉʿ;)Z
    //   839: ifeq -382 -> 457
    //   842: ldc_w 421
    //   845: aload 44
    //   847: invokevirtual 427	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   850: ifeq +1438 -> 2288
    //   853: aload 43
    //   855: aload 33
    //   857: invokestatic 482	com/cyou/cma/clauncher/ˑי:ʻ	([[[Lcom/cyou/cma/clauncher/ˉʿ;Lcom/cyou/cma/clauncher/ˉʿ;)Z
    //   860: ifne +1428 -> 2288
    //   863: aload 43
    //   865: iload 26
    //   867: aaload
    //   868: aload 33
    //   870: getfield 462	com/cyou/cma/clauncher/ᐧˆ:ᵢ	I
    //   873: aaload
    //   874: aload 33
    //   876: getfield 463	com/cyou/cma/clauncher/ᐧˆ:ⁱ	I
    //   879: aload 33
    //   881: aastore
    //   882: getstatic 319	com/cyou/cma/clauncher/LauncherModel:ʿ	Ljava/util/ArrayList;
    //   885: invokevirtual 486	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   888: astore 35
    //   890: aload 35
    //   892: invokeinterface 491 1 0
    //   897: ifeq +1382 -> 2279
    //   900: aload 35
    //   902: invokeinterface 495 1 0
    //   907: checkcast 210	com/cyou/cma/clauncher/ˉʿ
    //   910: astore 34
    //   912: aload 34
    //   914: getfield 218	com/cyou/cma/clauncher/ˉʿ:ᵎ	J
    //   917: aload 33
    //   919: getfield 460	com/cyou/cma/clauncher/ᐧˆ:ᵎ	J
    //   922: lcmp
    //   923: ifne -33 -> 890
    //   926: aload 34
    //   928: getfield 227	com/cyou/cma/clauncher/ˉʿ:ᵢ	I
    //   931: aload 33
    //   933: getfield 462	com/cyou/cma/clauncher/ᐧˆ:ᵢ	I
    //   936: if_icmpne -46 -> 890
    //   939: aload 34
    //   941: getfield 255	com/cyou/cma/clauncher/ˉʿ:ⁱ	I
    //   944: aload 33
    //   946: getfield 463	com/cyou/cma/clauncher/ᐧˆ:ⁱ	I
    //   949: if_icmpne -59 -> 890
    //   952: aload 34
    //   954: ifnull +1334 -> 2288
    //   957: getstatic 319	com/cyou/cma/clauncher/LauncherModel:ʿ	Ljava/util/ArrayList;
    //   960: aload 34
    //   962: invokevirtual 498	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   965: pop
    //   966: goto +1322 -> 2288
    //   969: getstatic 327	com/cyou/cma/clauncher/LauncherModel:ˉ	Ljava/util/HashMap;
    //   972: iload 26
    //   974: i2l
    //   975: invokestatic 501	com/cyou/cma/clauncher/LauncherModel:ʻ	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/ˆᵎ;
    //   978: aload 33
    //   980: invokevirtual 506	com/cyou/cma/clauncher/ˆᵎ:ʾ	(Lcom/cyou/cma/clauncher/ˉʿ;)V
    //   983: getstatic 330	com/cyou/cma/clauncher/LauncherModel:ʾ	Ljava/util/HashMap;
    //   986: aload 33
    //   988: getfield 459	com/cyou/cma/clauncher/ᐧˆ:ᐧ	J
    //   991: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   994: aload 33
    //   996: invokevirtual 514	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   999: pop
    //   1000: aload_0
    //   1001: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1004: getstatic 332	com/cyou/cma/clauncher/LauncherModel:ˊ	Ljava/util/HashMap;
    //   1007: aload 33
    //   1009: aload 38
    //   1011: iload 5
    //   1013: invokevirtual 517	com/cyou/cma/clauncher/LauncherModel:ʻ	(Ljava/util/HashMap;Lcom/cyou/cma/clauncher/ᐧˆ;Landroid/database/Cursor;I)Z
    //   1016: pop
    //   1017: goto -560 -> 457
    //   1020: astore 33
    //   1022: ldc -27
    //   1024: ldc_w 519
    //   1027: aload 33
    //   1029: invokestatic 523	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1032: pop
    //   1033: goto -576 -> 457
    //   1036: astore 33
    //   1038: aload 38
    //   1040: invokeinterface 526 1 0
    //   1045: aload 37
    //   1047: invokevirtual 527	java/util/ArrayList:size	()I
    //   1050: ifle +1146 -> 2196
    //   1053: aload 39
    //   1055: getstatic 336	com/cyou/cma/clauncher/יٴ:ʻ	Landroid/net/Uri;
    //   1058: invokevirtual 531	android/content/ContentResolver:acquireContentProviderClient	(Landroid/net/Uri;)Landroid/content/ContentProviderClient;
    //   1061: astore 33
    //   1063: aload 37
    //   1065: invokevirtual 486	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1068: astore 34
    //   1070: aload 34
    //   1072: invokeinterface 491 1 0
    //   1077: ifeq +1119 -> 2196
    //   1080: aload 34
    //   1082: invokeinterface 495 1 0
    //   1087: checkcast 508	java/lang/Long
    //   1090: invokevirtual 534	java/lang/Long:longValue	()J
    //   1093: lstore 29
    //   1095: aload 33
    //   1097: lload 29
    //   1099: invokestatic 537	com/cyou/cma/clauncher/יٴ:ʻ	(J)Landroid/net/Uri;
    //   1102: aconst_null
    //   1103: aconst_null
    //   1104: invokevirtual 540	android/content/ContentProviderClient:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   1107: pop
    //   1108: goto -38 -> 1070
    //   1111: astore 35
    //   1113: ldc -27
    //   1115: new 231	java/lang/StringBuilder
    //   1118: dup
    //   1119: ldc_w 542
    //   1122: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1125: lload 29
    //   1127: invokevirtual 545	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1130: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1133: invokestatic 547	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1136: pop
    //   1137: goto -67 -> 1070
    //   1140: aload_0
    //   1141: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1144: aload 41
    //   1146: aload 49
    //   1148: aload 40
    //   1150: aload 38
    //   1152: iload 5
    //   1154: iload_3
    //   1155: aload_0
    //   1156: getfield 42	com/cyou/cma/clauncher/ˑי:ˉ	Ljava/util/HashMap;
    //   1159: invokevirtual 550	com/cyou/cma/clauncher/LauncherModel:ʻ	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;IILjava/util/HashMap;)Lcom/cyou/cma/clauncher/ᐧˆ;
    //   1162: astore 33
    //   1164: goto -506 -> 658
    //   1167: aload 33
    //   1169: invokestatic 556	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1172: ifne +73 -> 1245
    //   1175: invokestatic 561	com/cyou/cma/SwitchService:getInstance	()Lcom/cyou/cma/SwitchService;
    //   1178: ldc_w 563
    //   1181: iconst_0
    //   1182: invokevirtual 566	com/cyou/cma/SwitchService:isSwitchOn	(Ljava/lang/String;Z)Z
    //   1185: ifne +25 -> 1210
    //   1188: aload 33
    //   1190: ldc_w 568
    //   1193: invokevirtual 571	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1196: ifeq +14 -> 1210
    //   1199: aload 33
    //   1201: ldc_w 573
    //   1204: invokevirtual 571	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1207: ifne -750 -> 457
    //   1210: invokestatic 561	com/cyou/cma/SwitchService:getInstance	()Lcom/cyou/cma/SwitchService;
    //   1213: ldc_w 575
    //   1216: iconst_0
    //   1217: invokevirtual 566	com/cyou/cma/SwitchService:isSwitchOn	(Ljava/lang/String;Z)Z
    //   1220: ifne +25 -> 1245
    //   1223: aload 33
    //   1225: ldc_w 568
    //   1228: invokevirtual 571	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1231: ifeq +14 -> 1245
    //   1234: aload 33
    //   1236: ldc_w 577
    //   1239: invokevirtual 571	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1242: ifne -785 -> 457
    //   1245: aload 38
    //   1247: iload 16
    //   1249: invokeinterface 419 2 0
    //   1254: astore 50
    //   1256: aconst_null
    //   1257: astore 33
    //   1259: aload 50
    //   1261: invokestatic 556	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1264: istore 32
    //   1266: aload 33
    //   1268: astore 35
    //   1270: iload 32
    //   1272: ifne +11 -> 1283
    //   1275: aload 50
    //   1277: iconst_0
    //   1278: invokestatic 431	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   1281: astore 35
    //   1283: aload 36
    //   1285: astore 33
    //   1287: aload 35
    //   1289: ifnull +100 -> 1389
    //   1292: aload 35
    //   1294: invokevirtual 581	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   1297: astore 50
    //   1299: aload 36
    //   1301: astore 33
    //   1303: aload 50
    //   1305: ifnull +84 -> 1389
    //   1308: aload 34
    //   1310: astore 33
    //   1312: aload 41
    //   1314: aload 35
    //   1316: invokevirtual 581	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   1319: invokevirtual 585	android/content/pm/PackageManager:getActivityIcon	(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;
    //   1322: pop
    //   1323: aload 34
    //   1325: astore 33
    //   1327: aload_0
    //   1328: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1331: aload 41
    //   1333: aload 35
    //   1335: aload 40
    //   1337: aload 38
    //   1339: iload 5
    //   1341: iload_3
    //   1342: aload_0
    //   1343: getfield 42	com/cyou/cma/clauncher/ˑי:ˉ	Ljava/util/HashMap;
    //   1346: invokevirtual 550	com/cyou/cma/clauncher/LauncherModel:ʻ	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;IILjava/util/HashMap;)Lcom/cyou/cma/clauncher/ᐧˆ;
    //   1349: astore 34
    //   1351: aload 34
    //   1353: astore 33
    //   1355: aload 35
    //   1357: ldc 96
    //   1359: invokevirtual 588	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   1362: pop
    //   1363: aload 34
    //   1365: astore 33
    //   1367: aload 34
    //   1369: aload 35
    //   1371: putfield 439	com/cyou/cma/clauncher/ᐧˆ:ʼ	Landroid/content/Intent;
    //   1374: aload 34
    //   1376: astore 33
    //   1378: aload 34
    //   1380: aload 35
    //   1382: putfield 591	com/cyou/cma/clauncher/ᐧˆ:ᐧᐧ	Landroid/content/Intent;
    //   1385: aload 34
    //   1387: astore 33
    //   1389: aload 33
    //   1391: astore 34
    //   1393: aload 33
    //   1395: ifnonnull +920 -> 2315
    //   1398: aload_0
    //   1399: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1402: aload 38
    //   1404: aload 40
    //   1406: iload 4
    //   1408: iload 6
    //   1410: iload 7
    //   1412: iload 5
    //   1414: iload_3
    //   1415: aload 49
    //   1417: invokestatic 594	com/cyou/cma/clauncher/LauncherModel:ʻ	(Lcom/cyou/cma/clauncher/LauncherModel;Landroid/database/Cursor;Landroid/content/Context;IIIIILandroid/content/Intent;)Lcom/cyou/cma/clauncher/ᐧˆ;
    //   1420: astore 34
    //   1422: aload 34
    //   1424: aload 49
    //   1426: putfield 439	com/cyou/cma/clauncher/ᐧˆ:ʼ	Landroid/content/Intent;
    //   1429: aload 34
    //   1431: aload 35
    //   1433: putfield 591	com/cyou/cma/clauncher/ᐧˆ:ᐧᐧ	Landroid/content/Intent;
    //   1436: goto +879 -> 2315
    //   1439: astore 35
    //   1441: aload 35
    //   1443: invokevirtual 597	java/net/URISyntaxException:printStackTrace	()V
    //   1446: aload 33
    //   1448: astore 35
    //   1450: goto -167 -> 1283
    //   1453: astore 33
    //   1455: aload 38
    //   1457: invokeinterface 526 1 0
    //   1462: aload 33
    //   1464: athrow
    //   1465: getstatic 319	com/cyou/cma/clauncher/LauncherModel:ʿ	Ljava/util/ArrayList;
    //   1468: aload 33
    //   1470: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1473: pop
    //   1474: goto -491 -> 983
    //   1477: aload 38
    //   1479: iload_1
    //   1480: invokeinterface 457 2 0
    //   1485: lstore 29
    //   1487: ldc -27
    //   1489: new 231	java/lang/StringBuilder
    //   1492: dup
    //   1493: ldc_w 274
    //   1496: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1499: lload 29
    //   1501: invokevirtual 545	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1504: ldc_w 602
    //   1507: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1510: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1513: invokestatic 264	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1516: pop
    //   1517: aload 39
    //   1519: lload 29
    //   1521: invokestatic 537	com/cyou/cma/clauncher/יٴ:ʻ	(J)Landroid/net/Uri;
    //   1524: aconst_null
    //   1525: aconst_null
    //   1526: invokevirtual 92	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   1529: pop
    //   1530: goto -1073 -> 457
    //   1533: aload 38
    //   1535: iload_1
    //   1536: invokeinterface 457 2 0
    //   1541: lstore 29
    //   1543: getstatic 327	com/cyou/cma/clauncher/LauncherModel:ˉ	Ljava/util/HashMap;
    //   1546: lload 29
    //   1548: invokestatic 501	com/cyou/cma/clauncher/LauncherModel:ʻ	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/ˆᵎ;
    //   1551: astore 33
    //   1553: aload 33
    //   1555: aload 38
    //   1557: iload_3
    //   1558: invokeinterface 419 2 0
    //   1563: putfield 605	com/cyou/cma/clauncher/ˆᵎ:ˏ	Ljava/lang/CharSequence;
    //   1566: aload 33
    //   1568: lload 29
    //   1570: putfield 606	com/cyou/cma/clauncher/ˆᵎ:ᐧ	J
    //   1573: aload 38
    //   1575: iload 8
    //   1577: invokeinterface 415 2 0
    //   1582: istore 25
    //   1584: aload 33
    //   1586: iload 25
    //   1588: i2l
    //   1589: putfield 607	com/cyou/cma/clauncher/ˆᵎ:ᵎ	J
    //   1592: aload 33
    //   1594: aload 38
    //   1596: iload 11
    //   1598: invokeinterface 415 2 0
    //   1603: putfield 608	com/cyou/cma/clauncher/ˆᵎ:ᵔ	I
    //   1606: aload 33
    //   1608: aload 38
    //   1610: iload 12
    //   1612: invokeinterface 415 2 0
    //   1617: putfield 609	com/cyou/cma/clauncher/ˆᵎ:ᵢ	I
    //   1620: aload 33
    //   1622: aload 38
    //   1624: iload 13
    //   1626: invokeinterface 415 2 0
    //   1631: putfield 610	com/cyou/cma/clauncher/ˆᵎ:ⁱ	I
    //   1634: aload 33
    //   1636: aload 38
    //   1638: iload 18
    //   1640: invokeinterface 419 2 0
    //   1645: putfield 613	com/cyou/cma/clauncher/ˆᵎ:ʽʽ	Ljava/lang/String;
    //   1648: aload 38
    //   1650: iload 17
    //   1652: invokeinterface 419 2 0
    //   1657: astore 34
    //   1659: aload 34
    //   1661: invokestatic 556	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1664: ifne +10 -> 1674
    //   1667: aload 33
    //   1669: aload 34
    //   1671: putfield 616	com/cyou/cma/clauncher/ˆᵎ:ʻʻ	Ljava/lang/String;
    //   1674: aload 43
    //   1676: aload 33
    //   1678: invokestatic 482	com/cyou/cma/clauncher/ˑי:ʻ	([[[Lcom/cyou/cma/clauncher/ˉʿ;Lcom/cyou/cma/clauncher/ˉʿ;)Z
    //   1681: ifeq -1224 -> 457
    //   1684: iload 25
    //   1686: tableswitch	default:+636->2322, -101:+59->1745, -100:+59->1745
    //   1708: getstatic 330	com/cyou/cma/clauncher/LauncherModel:ʾ	Ljava/util/HashMap;
    //   1711: aload 33
    //   1713: getfield 606	com/cyou/cma/clauncher/ˆᵎ:ᐧ	J
    //   1716: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1719: aload 33
    //   1721: invokevirtual 514	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1724: pop
    //   1725: getstatic 327	com/cyou/cma/clauncher/LauncherModel:ˉ	Ljava/util/HashMap;
    //   1728: aload 33
    //   1730: getfield 606	com/cyou/cma/clauncher/ˆᵎ:ᐧ	J
    //   1733: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1736: aload 33
    //   1738: invokevirtual 514	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1741: pop
    //   1742: goto -1285 -> 457
    //   1745: getstatic 319	com/cyou/cma/clauncher/LauncherModel:ʿ	Ljava/util/ArrayList;
    //   1748: aload 33
    //   1750: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1753: pop
    //   1754: goto -46 -> 1708
    //   1757: aload 38
    //   1759: iload 10
    //   1761: invokeinterface 415 2 0
    //   1766: istore 25
    //   1768: aload 38
    //   1770: iload_1
    //   1771: invokeinterface 457 2 0
    //   1776: lstore 29
    //   1778: aload 42
    //   1780: iload 25
    //   1782: invokevirtual 620	android/appwidget/AppWidgetManager:getAppWidgetInfo	(I)Landroid/appwidget/AppWidgetProviderInfo;
    //   1785: astore 33
    //   1787: iload 31
    //   1789: ifne +89 -> 1878
    //   1792: aload 33
    //   1794: ifnull +22 -> 1816
    //   1797: aload 33
    //   1799: getfield 626	android/appwidget/AppWidgetProviderInfo:provider	Landroid/content/ComponentName;
    //   1802: ifnull +14 -> 1816
    //   1805: aload 33
    //   1807: getfield 626	android/appwidget/AppWidgetProviderInfo:provider	Landroid/content/ComponentName;
    //   1810: invokevirtual 631	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   1813: ifnonnull +65 -> 1878
    //   1816: new 231	java/lang/StringBuilder
    //   1819: dup
    //   1820: ldc_w 633
    //   1823: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1826: lload 29
    //   1828: invokevirtual 545	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1831: ldc_w 635
    //   1834: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1837: iload 25
    //   1839: invokevirtual 248	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1842: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1845: astore 33
    //   1847: ldc -27
    //   1849: aload 33
    //   1851: invokestatic 264	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1854: pop
    //   1855: getstatic 637	com/cyou/cma/clauncher/Launcher:ٴ	Ljava/util/ArrayList;
    //   1858: aload 33
    //   1860: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1863: pop
    //   1864: aload 37
    //   1866: lload 29
    //   1868: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1871: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1874: pop
    //   1875: goto -1418 -> 457
    //   1878: new 639	com/cyou/cma/clauncher/ˏˆ
    //   1881: dup
    //   1882: iload 25
    //   1884: invokespecial 642	com/cyou/cma/clauncher/ˏˆ:<init>	(I)V
    //   1887: astore 33
    //   1889: aload 33
    //   1891: lload 29
    //   1893: putfield 643	com/cyou/cma/clauncher/ˏˆ:ᐧ	J
    //   1896: aload 33
    //   1898: aload 38
    //   1900: iload 11
    //   1902: invokeinterface 415 2 0
    //   1907: putfield 644	com/cyou/cma/clauncher/ˏˆ:ᵔ	I
    //   1910: aload 33
    //   1912: aload 38
    //   1914: iload 12
    //   1916: invokeinterface 415 2 0
    //   1921: putfield 645	com/cyou/cma/clauncher/ˏˆ:ᵢ	I
    //   1924: aload 33
    //   1926: aload 38
    //   1928: iload 13
    //   1930: invokeinterface 415 2 0
    //   1935: putfield 646	com/cyou/cma/clauncher/ˏˆ:ⁱ	I
    //   1938: aload 33
    //   1940: aload 38
    //   1942: iload 14
    //   1944: invokeinterface 415 2 0
    //   1949: putfield 647	com/cyou/cma/clauncher/ˏˆ:ﹳ	I
    //   1952: aload 33
    //   1954: aload 38
    //   1956: iload 15
    //   1958: invokeinterface 415 2 0
    //   1963: putfield 648	com/cyou/cma/clauncher/ˏˆ:ﹶ	I
    //   1966: aload 38
    //   1968: iload 8
    //   1970: invokeinterface 415 2 0
    //   1975: istore 25
    //   1977: iload 25
    //   1979: bipush -100
    //   1981: if_icmpeq +22 -> 2003
    //   1984: iload 25
    //   1986: bipush -101
    //   1988: if_icmpeq +15 -> 2003
    //   1991: ldc -27
    //   1993: ldc_w 650
    //   1996: invokestatic 264	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1999: pop
    //   2000: goto -1543 -> 457
    //   2003: aload 33
    //   2005: aload 38
    //   2007: iload 8
    //   2009: invokeinterface 415 2 0
    //   2014: i2l
    //   2015: putfield 651	com/cyou/cma/clauncher/ˏˆ:ᵎ	J
    //   2018: aload 43
    //   2020: aload 33
    //   2022: invokestatic 482	com/cyou/cma/clauncher/ˑי:ʻ	([[[Lcom/cyou/cma/clauncher/ˉʿ;Lcom/cyou/cma/clauncher/ˉʿ;)Z
    //   2025: ifeq -1568 -> 457
    //   2028: getstatic 330	com/cyou/cma/clauncher/LauncherModel:ʾ	Ljava/util/HashMap;
    //   2031: aload 33
    //   2033: getfield 643	com/cyou/cma/clauncher/ˏˆ:ᐧ	J
    //   2036: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2039: aload 33
    //   2041: invokevirtual 514	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2044: pop
    //   2045: getstatic 326	com/cyou/cma/clauncher/LauncherModel:ˆ	Ljava/util/ArrayList;
    //   2048: aload 33
    //   2050: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2053: pop
    //   2054: goto -1597 -> 457
    //   2057: aload 38
    //   2059: iload_2
    //   2060: invokeinterface 419 2 0
    //   2065: invokestatic 657	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   2068: iconst_0
    //   2069: anewarray 653	java/lang/Class
    //   2072: invokevirtual 661	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   2075: iconst_0
    //   2076: anewarray 4	java/lang/Object
    //   2079: invokevirtual 666	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   2082: checkcast 668	com/cyou/cma/clauncher/ʼـ
    //   2085: astore 33
    //   2087: aload 33
    //   2089: aload 38
    //   2091: iload_1
    //   2092: invokeinterface 457 2 0
    //   2097: putfield 669	com/cyou/cma/clauncher/ʼـ:ᐧ	J
    //   2100: aload 33
    //   2102: aload 38
    //   2104: iload 12
    //   2106: invokeinterface 415 2 0
    //   2111: putfield 670	com/cyou/cma/clauncher/ʼـ:ᵢ	I
    //   2114: aload 33
    //   2116: aload 38
    //   2118: iload 13
    //   2120: invokeinterface 415 2 0
    //   2125: putfield 671	com/cyou/cma/clauncher/ʼـ:ⁱ	I
    //   2128: aload 33
    //   2130: aload 38
    //   2132: iload 11
    //   2134: invokeinterface 415 2 0
    //   2139: putfield 672	com/cyou/cma/clauncher/ʼـ:ᵔ	I
    //   2142: aload 33
    //   2144: aload 38
    //   2146: iload 8
    //   2148: invokeinterface 415 2 0
    //   2153: i2l
    //   2154: putfield 673	com/cyou/cma/clauncher/ʼـ:ᵎ	J
    //   2157: getstatic 330	com/cyou/cma/clauncher/LauncherModel:ʾ	Ljava/util/HashMap;
    //   2160: aload 33
    //   2162: getfield 669	com/cyou/cma/clauncher/ʼـ:ᐧ	J
    //   2165: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2168: aload 33
    //   2170: invokevirtual 514	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2173: pop
    //   2174: getstatic 319	com/cyou/cma/clauncher/LauncherModel:ʿ	Ljava/util/ArrayList;
    //   2177: aload 33
    //   2179: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2182: pop
    //   2183: goto -1726 -> 457
    //   2186: aload 38
    //   2188: invokeinterface 526 1 0
    //   2193: goto -1148 -> 1045
    //   2196: ldc_w 675
    //   2199: new 231	java/lang/StringBuilder
    //   2202: dup
    //   2203: ldc_w 677
    //   2206: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2209: invokestatic 307	android/os/SystemClock:uptimeMillis	()J
    //   2212: lload 27
    //   2214: lsub
    //   2215: invokevirtual 545	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2218: ldc_w 679
    //   2221: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2224: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2227: invokestatic 682	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2230: pop
    //   2231: aload_0
    //   2232: monitorenter
    //   2233: aload_0
    //   2234: getfield 408	com/cyou/cma/clauncher/ˑי:ˆ	Z
    //   2237: ifeq +10 -> 2247
    //   2240: aload_0
    //   2241: invokevirtual 685	java/lang/Object:notify	()V
    //   2244: aload_0
    //   2245: monitorexit
    //   2246: return
    //   2247: aload_0
    //   2248: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   2251: invokestatic 687	com/cyou/cma/clauncher/LauncherModel:ʽ	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   2254: pop
    //   2255: aload_0
    //   2256: monitorexit
    //   2257: aload_0
    //   2258: invokespecial 207	com/cyou/cma/clauncher/ˑי:ʿ	()V
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
    //   0	2325	0	this	ˑי
    //   162	1930	1	i	int
    //   173	1887	2	j	int
    //   183	1375	3	k	int
    //   194	1213	4	m	int
    //   206	1207	5	n	int
    //   218	1191	6	i1	int
    //   230	1181	7	i2	int
    //   241	1906	8	i3	int
    //   253	228	9	i4	int
    //   265	1495	10	i5	int
    //   277	1856	11	i6	int
    //   289	1816	12	i7	int
    //   301	1818	13	i8	int
    //   313	1630	14	i9	int
    //   325	1632	15	i10	int
    //   359	889	16	i11	int
    //   371	1280	17	i12	int
    //   383	1256	18	i13	int
    //   395	103	19	i14	int
    //   407	152	20	i15	int
    //   419	151	21	i16	int
    //   431	150	22	i17	int
    //   443	149	23	i18	int
    //   455	148	24	i19	int
    //   587	1402	25	i20	int
    //   487	1802	26	i21	int
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
    //   150	1869	43	arrayOfˉʿ	ˉʿ[][][]
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
  
  private void ʿ()
  {
    if (!ʼˏ.ʻʻ(this.ʽ)) {
      return;
    }
    long l = SystemClock.uptimeMillis();
    LauncherModel.ʻ(this.ʼ, false);
    ˑˊ localˑˊ = (ˑˊ)LauncherModel.ʿ(this.ʼ).get();
    if (localˑˊ == null)
    {
      Log.w("Launcher.Model", "LoaderTask running with no launcher");
      return;
    }
    if (localˑˊ.ˏ())
    {
      LauncherModel.ˋ(this.ʼ);
      return;
    }
    LauncherModel.ʾ(this.ʼ).ʻ(new ˑᵔ(this, localˑˊ));
    int k = localˑˊ.ﹳﹳ();
    Object localObject3 = LauncherModel.ʻ(this.ʼ);
    Object localObject2 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.7D));
    Object localObject1 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.3D));
    ArrayList localArrayList = new ArrayList(5);
    localObject3 = ((ArrayList)localObject3).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      ˉʿ localˉʿ = (ˉʿ)((Iterator)localObject3).next();
      if (localˉʿ.ᵎ == -101L) {
        localArrayList.add(localˉʿ);
      } else if (localˉʿ.ᵔ == k) {
        ((ArrayList)localObject2).add(localˉʿ);
      } else {
        ((ArrayList)localObject1).add(localˉʿ);
      }
    }
    LauncherModel.ʾ(this.ʼ).ʻ(new ˑᵢ(this, localˑˊ, localArrayList));
    int m = ((ArrayList)localObject2).size();
    int i = 0;
    if (i < m)
    {
      if (i + 2 <= m) {}
      for (j = 2;; j = m - i)
      {
        LauncherModel.ʾ(this.ʼ).ʻ(new ˑⁱ(this, localˑˊ, (ArrayList)localObject2, i, j));
        i += 2;
        break;
      }
    }
    int j = LauncherModel.ˆ.size();
    i = 0;
    while (i < j)
    {
      localObject2 = (ˏˆ)LauncherModel.ˆ.get(i);
      if (((ˏˆ)localObject2).ᵔ == k) {
        LauncherModel.ʾ(this.ʼ).ʻ(new ˑﹳ(this, localˑˊ, (ˏˆ)localObject2));
      }
      i += 1;
    }
    LauncherModel.ʾ(this.ʼ).ʻ(new ˑﹶ(this));
    m = ((ArrayList)localObject1).size();
    i = 0;
    if (i < m)
    {
      if (i + 2 <= m) {}
      for (j = 2;; j = m - i)
      {
        LauncherModel.ʾ(this.ʼ).ʻ(new ˑﾞ(this, localˑˊ, (ArrayList)localObject1, i, j));
        i += 2;
        break;
      }
    }
    j = LauncherModel.ˆ.size();
    i = 0;
    while (i < j)
    {
      localObject1 = (ˏˆ)LauncherModel.ˆ.get(i);
      if (((ˏˆ)localObject1).ᵔ != k) {
        LauncherModel.ʾ(this.ʼ).ʻ(new יʻ(this, localˑˊ, (ˏˆ)localObject1));
      }
      i += 1;
    }
    localObject1 = new HashMap(LauncherModel.ˉ);
    LauncherModel.ʾ(this.ʼ).ʻ(new יʼ(this, localˑˊ, (HashMap)localObject1));
    LauncherModel.ʾ(this.ʼ).ʻ(new ˑـ(this, localˑˊ));
    LauncherModel.ʾ(this.ʼ).ʻ(new ˑٴ(this, l));
    if ((ʻ(localˑˊ) != null) && (this.ʼ.ʼ != null))
    {
      this.ʼ.ʼ.ʻ(LauncherModel.ˎ(this.ʼ), LauncherModel.ʾ(this.ʼ), localˑˊ);
      this.ʼ.ʼ = null;
    }
    LauncherModel.ʻ(this.ʼ, true);
  }
  
  /* Error */
  private void ˆ()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 33	com/cyou/cma/clauncher/ˑי:ˋ	Z
    //   4: ifne +13 -> 17
    //   7: aload_0
    //   8: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   11: invokestatic 787	com/cyou/cma/clauncher/LauncherModel:ˏ	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   14: ifne +978 -> 992
    //   17: invokestatic 307	android/os/SystemClock:uptimeMillis	()J
    //   20: lstore 4
    //   22: aload_0
    //   23: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   26: invokestatic 696	com/cyou/cma/clauncher/LauncherModel:ʿ	(Lcom/cyou/cma/clauncher/LauncherModel;)Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 700	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 702	com/cyou/cma/clauncher/ˑˊ
    //   35: ifnull +918 -> 953
    //   38: new 94	android/content/Intent
    //   41: dup
    //   42: ldc 96
    //   44: aconst_null
    //   45: invokespecial 99	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   48: astore 12
    //   50: aload 12
    //   52: ldc 101
    //   54: invokevirtual 105	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   57: pop
    //   58: aload_0
    //   59: getfield 35	com/cyou/cma/clauncher/ˑי:ʽ	Landroid/content/Context;
    //   62: invokevirtual 109	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   65: astore 13
    //   67: aload 13
    //   69: sipush 8192
    //   72: invokevirtual 791	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   75: astore 9
    //   77: aload_0
    //   78: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   81: aload 9
    //   83: invokeinterface 121 1 0
    //   88: invokestatic 794	com/cyou/cma/clauncher/LauncherModel:ʻ	(Lcom/cyou/cma/clauncher/LauncherModel;I)I
    //   91: pop
    //   92: invokestatic 799	com/cyou/cma/clauncher/ʽ:ʼ	()Ljava/util/HashMap;
    //   95: astore 14
    //   97: invokestatic 802	com/cyou/cma/clauncher/ʽ:ʻ	()Ljava/util/Map;
    //   100: astore 15
    //   102: aload_0
    //   103: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   106: getfield 805	com/cyou/cma/clauncher/LauncherModel:ʽ	Lcom/cyou/cma/clauncher/ˋ;
    //   109: astore 9
    //   111: aload 9
    //   113: getfield 809	com/cyou/cma/clauncher/ˋ:ʻ	Ljava/util/ArrayList;
    //   116: invokevirtual 324	java/util/ArrayList:clear	()V
    //   119: aload 9
    //   121: getfield 811	com/cyou/cma/clauncher/ˋ:ʼ	Ljava/util/ArrayList;
    //   124: invokevirtual 324	java/util/ArrayList:clear	()V
    //   127: aload 9
    //   129: getfield 813	com/cyou/cma/clauncher/ˋ:ʽ	Ljava/util/ArrayList;
    //   132: invokevirtual 324	java/util/ArrayList:clear	()V
    //   135: aload 9
    //   137: getfield 815	com/cyou/cma/clauncher/ˋ:ʾ	Ljava/util/ArrayList;
    //   140: invokevirtual 324	java/util/ArrayList:clear	()V
    //   143: aload 14
    //   145: invokevirtual 328	java/util/HashMap:clear	()V
    //   148: aload 15
    //   150: invokeinterface 818 1 0
    //   155: new 321	java/util/ArrayList
    //   158: dup
    //   159: invokespecial 333	java/util/ArrayList:<init>	()V
    //   162: astore 16
    //   164: aload_0
    //   165: getfield 35	com/cyou/cma/clauncher/ˑי:ʽ	Landroid/content/Context;
    //   168: invokevirtual 81	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   171: getstatic 86	com/cyou/cma/clauncher/יˑ:ʻ	Landroid/net/Uri;
    //   174: bipush 11
    //   176: anewarray 423	java/lang/String
    //   179: dup
    //   180: iconst_0
    //   181: ldc_w 355
    //   184: aastore
    //   185: dup
    //   186: iconst_1
    //   187: ldc -124
    //   189: aastore
    //   190: dup
    //   191: iconst_2
    //   192: ldc -108
    //   194: aastore
    //   195: dup
    //   196: iconst_3
    //   197: ldc -75
    //   199: aastore
    //   200: dup
    //   201: iconst_4
    //   202: ldc -79
    //   204: aastore
    //   205: dup
    //   206: iconst_5
    //   207: ldc -97
    //   209: aastore
    //   210: dup
    //   211: bipush 6
    //   213: ldc -77
    //   215: aastore
    //   216: dup
    //   217: bipush 7
    //   219: ldc -90
    //   221: aastore
    //   222: dup
    //   223: bipush 8
    //   225: ldc_w 820
    //   228: aastore
    //   229: dup
    //   230: bipush 9
    //   232: ldc_w 822
    //   235: aastore
    //   236: dup
    //   237: bipush 10
    //   239: ldc_w 395
    //   242: aastore
    //   243: aconst_null
    //   244: aconst_null
    //   245: ldc_w 824
    //   248: invokevirtual 340	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   251: astore 17
    //   253: aload 17
    //   255: invokeinterface 411 1 0
    //   260: ifeq +638 -> 898
    //   263: aload 17
    //   265: bipush 6
    //   267: invokeinterface 415 2 0
    //   272: istore_1
    //   273: aload 17
    //   275: iconst_4
    //   276: invokeinterface 415 2 0
    //   281: istore_2
    //   282: aload 17
    //   284: bipush 7
    //   286: invokeinterface 415 2 0
    //   291: istore_3
    //   292: aload 17
    //   294: iconst_0
    //   295: invokeinterface 457 2 0
    //   300: lstore 6
    //   302: aload 17
    //   304: bipush 10
    //   306: invokeinterface 419 2 0
    //   311: astore 11
    //   313: aload 17
    //   315: iconst_5
    //   316: invokeinterface 419 2 0
    //   321: astore 9
    //   323: iload_2
    //   324: ifne +432 -> 756
    //   327: aload 17
    //   329: iconst_1
    //   330: invokeinterface 419 2 0
    //   335: astore 10
    //   337: aload 17
    //   339: iconst_2
    //   340: invokeinterface 419 2 0
    //   345: astore 18
    //   347: aload 10
    //   349: aload 18
    //   351: invokestatic 827	com/cyou/cma/ʼˏ:ʻ	(Ljava/lang/String;Ljava/lang/String;)Z
    //   354: ifne -101 -> 253
    //   357: aload 12
    //   359: new 628	android/content/ComponentName
    //   362: dup
    //   363: aload 10
    //   365: aload 18
    //   367: invokespecial 829	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   370: invokevirtual 833	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   373: pop
    //   374: aload 13
    //   376: aload 12
    //   378: iconst_0
    //   379: invokevirtual 837	android/content/pm/PackageManager:resolveActivity	(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   382: astore 11
    //   384: aload 11
    //   386: ifnonnull +248 -> 634
    //   389: new 839	com/cyou/cma/clauncher/ـ
    //   392: dup
    //   393: invokespecial 840	com/cyou/cma/clauncher/ـ:<init>	()V
    //   396: astore 11
    //   398: aload 11
    //   400: iconst_1
    //   401: putfield 842	com/cyou/cma/clauncher/ـ:ˉ	Z
    //   404: aload 11
    //   406: iconst_1
    //   407: putfield 844	com/cyou/cma/clauncher/ـ:ˆ	I
    //   410: aload 11
    //   412: new 628	android/content/ComponentName
    //   415: dup
    //   416: aload 10
    //   418: aload 18
    //   420: invokespecial 829	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   423: putfield 846	com/cyou/cma/clauncher/ـ:ʾ	Landroid/content/ComponentName;
    //   426: aload 9
    //   428: astore 10
    //   430: aload 9
    //   432: ifnonnull +7 -> 439
    //   435: ldc -99
    //   437: astore 10
    //   439: aload 11
    //   441: aload 10
    //   443: putfield 847	com/cyou/cma/clauncher/ـ:ˏ	Ljava/lang/CharSequence;
    //   446: aload 11
    //   448: aload 11
    //   450: getfield 846	com/cyou/cma/clauncher/ـ:ʾ	Landroid/content/ComponentName;
    //   453: invokevirtual 850	com/cyou/cma/clauncher/ـ:ʻ	(Landroid/content/ComponentName;)V
    //   456: aload 11
    //   458: aload_0
    //   459: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   462: invokestatic 291	com/cyou/cma/clauncher/LauncherModel:ˊ	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/ˈⁱ;
    //   465: invokevirtual 853	com/cyou/cma/clauncher/ˈⁱ:ʾ	()Landroid/graphics/Bitmap;
    //   468: putfield 856	com/cyou/cma/clauncher/ـ:ʼ	Landroid/graphics/Bitmap;
    //   471: aload 11
    //   473: astore 9
    //   475: aload 9
    //   477: lload 6
    //   479: putfield 857	com/cyou/cma/clauncher/ـ:ᐧ	J
    //   482: aload 17
    //   484: iconst_3
    //   485: invokeinterface 457 2 0
    //   490: lconst_1
    //   491: lcmp
    //   492: ifne +169 -> 661
    //   495: iconst_1
    //   496: istore 8
    //   498: aload 9
    //   500: iload 8
    //   502: putfield 859	com/cyou/cma/clauncher/ـ:ˑ	Z
    //   505: aload 17
    //   507: bipush 9
    //   509: invokeinterface 457 2 0
    //   514: lconst_1
    //   515: lcmp
    //   516: ifne +151 -> 667
    //   519: iconst_1
    //   520: istore 8
    //   522: aload 9
    //   524: iload 8
    //   526: putfield 860	com/cyou/cma/clauncher/ـ:ʿ	Z
    //   529: aload 9
    //   531: invokevirtual 862	com/cyou/cma/clauncher/ـ:ʻ	()V
    //   534: aload 9
    //   536: iload_3
    //   537: putfield 865	com/cyou/cma/clauncher/ـ:ــ	I
    //   540: aload 9
    //   542: iload_2
    //   543: putfield 868	com/cyou/cma/clauncher/ـ:ᴵ	I
    //   546: aload 9
    //   548: iload_1
    //   549: i2l
    //   550: putfield 869	com/cyou/cma/clauncher/ـ:ᵎ	J
    //   553: aload 9
    //   555: iconst_1
    //   556: putfield 872	com/cyou/cma/clauncher/ـ:ʾʾ	I
    //   559: aload_0
    //   560: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   563: getfield 805	com/cyou/cma/clauncher/LauncherModel:ʽ	Lcom/cyou/cma/clauncher/ˋ;
    //   566: aload 9
    //   568: invokevirtual 875	com/cyou/cma/clauncher/ˋ:ʻ	(Lcom/cyou/cma/clauncher/ـ;)Z
    //   571: ifeq +146 -> 717
    //   574: iload_1
    //   575: sipush 65336
    //   578: if_icmpne +95 -> 673
    //   581: aload 15
    //   583: aload 9
    //   585: getfield 857	com/cyou/cma/clauncher/ـ:ᐧ	J
    //   588: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   591: invokeinterface 878 2 0
    //   596: ifne -343 -> 253
    //   599: aload 15
    //   601: aload 9
    //   603: getfield 857	com/cyou/cma/clauncher/ـ:ᐧ	J
    //   606: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   609: aload 9
    //   611: invokeinterface 879 3 0
    //   616: pop
    //   617: goto -364 -> 253
    //   620: astore 9
    //   622: aload_0
    //   623: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   626: iconst_0
    //   627: invokestatic 794	com/cyou/cma/clauncher/LauncherModel:ʻ	(Lcom/cyou/cma/clauncher/LauncherModel;I)I
    //   630: pop
    //   631: goto -539 -> 92
    //   634: new 839	com/cyou/cma/clauncher/ـ
    //   637: dup
    //   638: aload 13
    //   640: aload 11
    //   642: aload_0
    //   643: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   646: invokestatic 291	com/cyou/cma/clauncher/LauncherModel:ˊ	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/ˈⁱ;
    //   649: aload_0
    //   650: getfield 42	com/cyou/cma/clauncher/ˑי:ˉ	Ljava/util/HashMap;
    //   653: invokespecial 882	com/cyou/cma/clauncher/ـ:<init>	(Landroid/content/pm/PackageManager;Landroid/content/pm/ResolveInfo;Lcom/cyou/cma/clauncher/ˈⁱ;Ljava/util/HashMap;)V
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
    //   677: invokestatic 884	com/cyou/cma/clauncher/LauncherModel:ʼ	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/ˆᵎ;
    //   680: astore 10
    //   682: aload 10
    //   684: iconst_1
    //   685: putfield 885	com/cyou/cma/clauncher/ˆᵎ:ʾʾ	I
    //   688: aload 10
    //   690: iconst_1
    //   691: putfield 886	com/cyou/cma/clauncher/ˆᵎ:ᴵ	I
    //   694: aload 10
    //   696: ldc2_w 887
    //   699: putfield 607	com/cyou/cma/clauncher/ˆᵎ:ᵎ	J
    //   702: aload 10
    //   704: invokevirtual 891	com/cyou/cma/clauncher/ˆᵎ:b_	()V
    //   707: aload 10
    //   709: aload 9
    //   711: invokevirtual 506	com/cyou/cma/clauncher/ˆᵎ:ʾ	(Lcom/cyou/cma/clauncher/ˉʿ;)V
    //   714: goto -461 -> 253
    //   717: aload 16
    //   719: aload 9
    //   721: invokevirtual 600	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   724: pop
    //   725: ldc_w 675
    //   728: new 231	java/lang/StringBuilder
    //   731: dup
    //   732: ldc_w 893
    //   735: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   738: aload 9
    //   740: getfield 847	com/cyou/cma/clauncher/ـ:ˏ	Ljava/lang/CharSequence;
    //   743: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   746: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   749: invokestatic 896	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   752: pop
    //   753: goto -500 -> 253
    //   756: iload_2
    //   757: iconst_1
    //   758: if_icmpne -505 -> 253
    //   761: aload 14
    //   763: lload 6
    //   765: invokestatic 884	com/cyou/cma/clauncher/LauncherModel:ʼ	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/ˆᵎ;
    //   768: astore 18
    //   770: aload 9
    //   772: astore 10
    //   774: aload 9
    //   776: ifnonnull +7 -> 783
    //   779: ldc -99
    //   781: astore 10
    //   783: aload 18
    //   785: aload 10
    //   787: putfield 605	com/cyou/cma/clauncher/ˆᵎ:ˏ	Ljava/lang/CharSequence;
    //   790: aload 18
    //   792: ldc2_w 887
    //   795: putfield 607	com/cyou/cma/clauncher/ˆᵎ:ᵎ	J
    //   798: aload 18
    //   800: lload 6
    //   802: putfield 606	com/cyou/cma/clauncher/ˆᵎ:ᐧ	J
    //   805: aload 18
    //   807: iload_3
    //   808: putfield 897	com/cyou/cma/clauncher/ˆᵎ:ــ	I
    //   811: aload 18
    //   813: iconst_1
    //   814: putfield 885	com/cyou/cma/clauncher/ˆᵎ:ʾʾ	I
    //   817: aload 18
    //   819: iconst_1
    //   820: putfield 886	com/cyou/cma/clauncher/ˆᵎ:ᴵ	I
    //   823: aload 17
    //   825: bipush 8
    //   827: invokeinterface 415 2 0
    //   832: iconst_1
    //   833: if_icmpne +59 -> 892
    //   836: iconst_1
    //   837: istore 8
    //   839: aload 18
    //   841: iload 8
    //   843: putfield 898	com/cyou/cma/clauncher/ˆᵎ:ʿ	Z
    //   846: aload 18
    //   848: aload 11
    //   850: putfield 613	com/cyou/cma/clauncher/ˆᵎ:ʽʽ	Ljava/lang/String;
    //   853: aload 15
    //   855: aload 18
    //   857: getfield 606	com/cyou/cma/clauncher/ˆᵎ:ᐧ	J
    //   860: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   863: invokeinterface 878 2 0
    //   868: ifne -615 -> 253
    //   871: aload 15
    //   873: aload 18
    //   875: getfield 606	com/cyou/cma/clauncher/ˆᵎ:ᐧ	J
    //   878: invokestatic 511	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   881: aload 18
    //   883: invokeinterface 879 3 0
    //   888: pop
    //   889: goto -636 -> 253
    //   892: iconst_0
    //   893: istore 8
    //   895: goto -56 -> 839
    //   898: aload 17
    //   900: ifnull +10 -> 910
    //   903: aload 17
    //   905: invokeinterface 526 1 0
    //   910: aload_0
    //   911: getfield 35	com/cyou/cma/clauncher/ˑי:ʽ	Landroid/content/Context;
    //   914: aload 16
    //   916: invokestatic 901	com/cyou/cma/clauncher/ʽ:ʼ	(Landroid/content/Context;Ljava/util/ArrayList;)V
    //   919: aload 16
    //   921: invokevirtual 324	java/util/ArrayList:clear	()V
    //   924: ldc_w 675
    //   927: new 231	java/lang/StringBuilder
    //   930: dup
    //   931: ldc_w 903
    //   934: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   937: invokestatic 307	android/os/SystemClock:uptimeMillis	()J
    //   940: lload 4
    //   942: lsub
    //   943: invokevirtual 545	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   946: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   949: invokestatic 682	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   952: pop
    //   953: aload_0
    //   954: monitorenter
    //   955: aload_0
    //   956: getfield 408	com/cyou/cma/clauncher/ˑי:ˆ	Z
    //   959: ifeq +16 -> 975
    //   962: aload_0
    //   963: monitorexit
    //   964: return
    //   965: astore 9
    //   967: aload 9
    //   969: invokevirtual 904	java/lang/Exception:printStackTrace	()V
    //   972: goto -62 -> 910
    //   975: aload_0
    //   976: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   979: invokestatic 906	com/cyou/cma/clauncher/LauncherModel:ˑ	(Lcom/cyou/cma/clauncher/LauncherModel;)Z
    //   982: pop
    //   983: aload_0
    //   984: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   987: invokestatic 909	com/cyou/cma/clauncher/LauncherModel:י	(Lcom/cyou/cma/clauncher/LauncherModel;)V
    //   990: aload_0
    //   991: monitorexit
    //   992: aload_0
    //   993: invokespecial 280	com/cyou/cma/clauncher/ˑי:ˈ	()V
    //   996: new 231	java/lang/StringBuilder
    //   999: dup
    //   1000: invokespecial 910	java/lang/StringBuilder:<init>	()V
    //   1003: aload_0
    //   1004: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1007: invokestatic 195	com/cyou/cma/clauncher/LauncherModel:ˎ	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/LauncherApplication;
    //   1010: invokevirtual 911	com/cyou/cma/clauncher/LauncherApplication:getPackageName	()Ljava/lang/String;
    //   1013: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1016: ldc_w 913
    //   1019: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1022: invokevirtual 258	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1025: astore 9
    //   1027: aload_0
    //   1028: getfield 26	com/cyou/cma/clauncher/ˑי:ʼ	Lcom/cyou/cma/clauncher/LauncherModel;
    //   1031: invokestatic 195	com/cyou/cma/clauncher/LauncherModel:ˎ	(Lcom/cyou/cma/clauncher/LauncherModel;)Lcom/cyou/cma/clauncher/LauncherApplication;
    //   1034: aload 9
    //   1036: iconst_4
    //   1037: invokevirtual 914	com/cyou/cma/clauncher/LauncherApplication:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1040: astore 9
    //   1042: aload 9
    //   1044: ldc_w 916
    //   1047: iconst_0
    //   1048: invokeinterface 453 3 0
    //   1053: ifne -89 -> 964
    //   1056: new 918	java/lang/Thread
    //   1059: dup
    //   1060: new 920	com/cyou/cma/clauncher/ˑᐧ
    //   1063: dup
    //   1064: aload_0
    //   1065: aload 9
    //   1067: invokespecial 923	com/cyou/cma/clauncher/ˑᐧ:<init>	(Lcom/cyou/cma/clauncher/ˑי;Landroid/content/SharedPreferences;)V
    //   1070: invokespecial 925	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   1073: invokevirtual 926	java/lang/Thread:start	()V
    //   1076: return
    //   1077: astore 9
    //   1079: aload_0
    //   1080: monitorexit
    //   1081: aload 9
    //   1083: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1084	0	this	ˑי
    //   272	404	1	i	int
    //   281	478	2	j	int
    //   291	517	3	k	int
    //   20	921	4	l1	long
    //   300	501	6	l2	long
    //   496	398	8	bool	boolean
    //   75	535	9	localObject1	Object
    //   620	1	9	localException1	Exception
    //   656	119	9	localـ	ـ
    //   965	3	9	localException2	Exception
    //   1025	41	9	localObject2	Object
    //   1077	5	9	localObject3	Object
    //   335	451	10	localObject4	Object
    //   311	538	11	localObject5	Object
    //   48	329	12	localIntent	Intent
    //   65	574	13	localPackageManager	PackageManager
    //   95	667	14	localHashMap	HashMap
    //   100	772	15	localMap	java.util.Map
    //   162	758	16	localArrayList	ArrayList
    //   251	653	17	localCursor	android.database.Cursor
    //   345	537	18	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   67	92	620	java/lang/Exception
    //   903	910	965	java/lang/Exception
    //   955	964	1077	finally
    //   975	992	1077	finally
    //   1079	1081	1077	finally
  }
  
  private void ˈ()
  {
    if (this.ʻ) {}
    do
    {
      return;
      localˑˊ = (ˑˊ)LauncherModel.ʿ(this.ʼ).get();
    } while (localˑˊ == null);
    if (localˑˊ.ˏ())
    {
      LauncherModel.ـ(this.ʼ);
      return;
    }
    ˑˊ localˑˊ = ʻ(localˑˊ);
    int i = 0;
    for (;;)
    {
      if (i < this.ʼ.ʽ.ʻ.size())
      {
        if (((ـ)this.ʼ.ʽ.ʻ.get(i)).ˉ().equals("com.android.vending")) {
          com.cyou.cma.recommend.ـ.ʻ = true;
        }
      }
      else
      {
        if ((this.ʼ.ʼ != null) && (LauncherModel.ٴ(this.ʼ)))
        {
          this.ʼ.ʼ.ʻ(LauncherModel.ˎ(this.ʼ), LauncherModel.ʾ(this.ʼ), localˑˊ);
          this.ʼ.ʼ = null;
        }
        LauncherModel.ʾ(this.ʼ).ʻ(new ˑᴵ(this, localˑˊ));
        return;
      }
      i += 1;
    }
  }
  
  public final void run()
  {
    int j = 0;
    ??? = (ˑˊ)LauncherModel.ʿ(this.ʼ).get();
    int i;
    if ((??? != null) && (((ˑˊ)???).ˑˑ())) {
      i = 0;
    }
    for (;;)
    {
      synchronized (LauncherModel.ˆ(this.ʼ))
      {
        if (this.ʿ)
        {
          label48:
          Process.setThreadPriority(j);
          if ((this.ˊ) || (i == 0)) {
            break label216;
          }
          ʾ();
          ʻ(this.ʽ);
          label77:
          if (!this.ˆ) {
            synchronized (LauncherModel.ˆ(this.ʼ))
            {
              if (this.ʿ) {
                Process.setThreadPriority(10);
              }
              if (!this.ˊ)
              {
                if (i == 0) {
                  break label238;
                }
                ˆ();
              }
            }
          }
        }
      }
      synchronized (LauncherModel.ˆ(this.ʼ))
      {
        Process.setThreadPriority(0);
        ??? = LauncherModel.ˊ.keySet().iterator();
        for (;;)
        {
          if (((Iterator)???).hasNext())
          {
            Object localObject2 = ((Iterator)???).next();
            this.ʼ.ʻ(this.ʽ, (ᐧˆ)localObject2, (byte[])LauncherModel.ˊ.get(localObject2));
            continue;
            i = 1;
            break;
            j = 10;
            break label48;
            localObject3 = finally;
            throw localObject3;
            label216:
            ʻ(this.ʽ);
            ˆ();
            break label77;
            localObject4 = finally;
            throw localObject4;
            label238:
            ʾ();
          }
        }
      }
    }
    LauncherModel.ˊ.clear();
    this.ʽ = null;
    synchronized (LauncherModel.ˆ(this.ʼ))
    {
      if (LauncherModel.ˈ(this.ʼ) == this) {
        LauncherModel.ˉ(this.ʼ);
      }
      return;
    }
  }
  
  final ˑˊ ʻ(ˑˊ paramˑˊ)
  {
    synchronized (LauncherModel.ˆ(this.ʼ))
    {
      if (this.ˆ) {
        return null;
      }
      if (LauncherModel.ʿ(this.ʼ) == null) {
        return null;
      }
      ˑˊ localˑˊ = (ˑˊ)LauncherModel.ʿ(this.ʼ).get();
      if (localˑˊ != paramˑˊ) {
        return null;
      }
      if (localˑˊ == null)
      {
        Log.w("Launcher.Model", "no mCallbacks");
        return null;
      }
      return localˑˊ;
    }
  }
  
  final boolean ʻ()
  {
    return this.ʿ;
  }
  
  public final void ʼ()
  {
    try
    {
      this.ˆ = true;
      notify();
      return;
    }
    finally {}
  }
  
  public final void ʽ()
  {
    Log.d("Launcher.Model", "mLoaderTask.mContext=" + this.ʽ);
    Log.d("Launcher.Model", "mLoaderTask.mWaitThread=" + this.ʾ);
    Log.d("Launcher.Model", "mLoaderTask.mIsLaunching=" + this.ʿ);
    Log.d("Launcher.Model", "mLoaderTask.mStopped=" + this.ˆ);
    Log.d("Launcher.Model", "mLoaderTask.mLoadAndBindStepFinished=" + this.ˈ);
    Log.d("Launcher.Model", "mItems size=" + LauncherModel.ʿ.size());
  }
}
