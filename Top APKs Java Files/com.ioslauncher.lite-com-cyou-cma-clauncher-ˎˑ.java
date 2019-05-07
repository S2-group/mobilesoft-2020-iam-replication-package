package com.cyou.cma.clauncher;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.cyou.cma.ʻ;
import com.cyou.cma.ʻٴ;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ˎˑ
  implements Runnable
{
  boolean ʻ;
  private Context ʽ;
  private boolean ʾ;
  private boolean ʿ;
  private boolean ˆ = false;
  private boolean ˈ = false;
  
  ˎˑ(ˋי paramˋי, Context paramContext, boolean paramBoolean)
  {
    this.ʽ = paramContext;
    this.ʾ = paramBoolean;
  }
  
  ˎˑ(ˋי paramˋי, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this(paramˋי, paramContext, paramBoolean1);
    this.ʻ = paramBoolean4;
  }
  
  private void ʻ(Context paramContext)
  {
    Object localObject = paramContext.getFileStreamPath("flag_all_app");
    boolean bool = ((File)localObject).exists();
    if (bool) {
      new ˎᴵ(this, (File)localObject).start();
    }
    if (!bool) {
      return;
    }
    ˋי.ᐧ(this.ʼ);
    localObject = paramContext.getContentResolver();
    ((ContentResolver)localObject).delete(ˏـ.ʻ, null, null);
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
        localContentValues.put("title", ʻٴ.ʻ(paramContext));
        localContentValues.put("app_index", Integer.valueOf(i));
        localContentValues.put("item_type", Integer.valueOf(0));
        localContentValues.put("container", Integer.valueOf(65336));
        localContentValues.put("hide", Integer.valueOf(0));
        arrayOfContentValues[i] = localContentValues;
        i += 1;
        break;
      }
    }
    ((ContentResolver)localObject).bulkInsert(ˏـ.ʻ, arrayOfContentValues);
    ˋי.ˎ(this.ʼ).ʿ.ʽ();
  }
  
  private static boolean ʻ(ˆٴ[][][] paramArrayOfˆٴ, ˆٴ paramˆٴ)
  {
    int k = paramˆٴ.ᵎ;
    if (paramˆٴ.ᴵ == -101L)
    {
      if (paramArrayOfˆٴ[Launcher.ʼ][paramˆٴ.ᵔ][0] != null)
      {
        Log.e("Launcher.Model", "Error loading shortcut into hotseat " + paramˆٴ + " into position (" + paramˆٴ.ᵎ + ":" + paramˆٴ.ᵔ + "," + paramˆٴ.ᵢ + ") occupied by " + paramArrayOfˆٴ[Launcher.ʼ][paramˆٴ.ᵔ][0]);
        return false;
      }
      paramArrayOfˆٴ[Launcher.ʼ][paramˆٴ.ᵔ][0] = paramˆٴ;
      return true;
    }
    if (paramˆٴ.ᴵ != -100L) {
      return true;
    }
    int i = paramˆٴ.ᵔ;
    int j;
    while (i < paramˆٴ.ᵔ + paramˆٴ.ⁱ)
    {
      j = paramˆٴ.ᵢ;
      while (j < paramˆٴ.ᵢ + paramˆٴ.ﹳ)
      {
        if (paramArrayOfˆٴ[k][i][j] != null)
        {
          Log.e("Launcher.Model", "Error loading shortcut " + paramˆٴ + " into cell (" + k + "-" + paramˆٴ.ᵎ + ":" + i + "," + j + ") occupied by " + paramArrayOfˆٴ[k][i][j]);
          return false;
        }
        j += 1;
      }
      i += 1;
    }
    i = paramˆٴ.ᵔ;
    while (i < paramˆٴ.ᵔ + paramˆٴ.ⁱ)
    {
      j = paramˆٴ.ᵢ;
      while (j < paramˆٴ.ᵢ + paramˆٴ.ﹳ)
      {
        paramArrayOfˆٴ[k][i][j] = paramˆٴ;
        j += 1;
      }
      i += 1;
    }
    return true;
  }
  
  /* Error */
  private void ʽ()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   4: invokestatic 277	com/cyou/cma/clauncher/ˋי:ʼ	(Lcom/cyou/cma/clauncher/ˋי;)Z
    //   7: ifne +1634 -> 1641
    //   10: aload_0
    //   11: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   14: invokestatic 281	com/cyou/cma/clauncher/ˋי:ˊ	(Lcom/cyou/cma/clauncher/ˋי;)Lcom/cyou/cma/clauncher/ˆˉ;
    //   17: getstatic 286	com/cyou/cma/ʻ/ʻ:ʻ	Lcom/cyou/cma/ʻ/ʻ;
    //   20: invokevirtual 291	com/cyou/cma/clauncher/ˆˉ:ʻ	(Lcom/cyou/cma/ʻ/ʻ;)V
    //   23: invokestatic 297	android/os/SystemClock:uptimeMillis	()J
    //   26: lstore 19
    //   28: aload_0
    //   29: getfield 29	com/cyou/cma/clauncher/ˎˑ:ʽ	Landroid/content/Context;
    //   32: astore 29
    //   34: aload 29
    //   36: invokevirtual 70	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   39: astore 28
    //   41: aload 29
    //   43: invokevirtual 98	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: astore 30
    //   48: aload 29
    //   50: invokestatic 303	android/appwidget/AppWidgetManager:getInstance	(Landroid/content/Context;)Landroid/appwidget/AppWidgetManager;
    //   53: astore 31
    //   55: aload 30
    //   57: invokevirtual 306	android/content/pm/PackageManager:isSafeMode	()Z
    //   60: istore 23
    //   62: getstatic 309	com/cyou/cma/clauncher/ˋי:ʿ	Ljava/util/ArrayList;
    //   65: invokevirtual 314	java/util/ArrayList:clear	()V
    //   68: getstatic 316	com/cyou/cma/clauncher/ˋי:ˆ	Ljava/util/ArrayList;
    //   71: invokevirtual 314	java/util/ArrayList:clear	()V
    //   74: getstatic 320	com/cyou/cma/clauncher/ˋי:ˉ	Ljava/util/HashMap;
    //   77: invokevirtual 323	java/util/HashMap:clear	()V
    //   80: getstatic 325	com/cyou/cma/clauncher/ˋי:ʾ	Ljava/util/HashMap;
    //   83: invokevirtual 323	java/util/HashMap:clear	()V
    //   86: getstatic 327	com/cyou/cma/clauncher/ˋי:ˊ	Ljava/util/HashMap;
    //   89: invokevirtual 323	java/util/HashMap:clear	()V
    //   92: new 311	java/util/ArrayList
    //   95: dup
    //   96: invokespecial 328	java/util/ArrayList:<init>	()V
    //   99: astore 26
    //   101: aload 28
    //   103: getstatic 331	com/cyou/cma/clauncher/ˏᐧ:ʻ	Landroid/net/Uri;
    //   106: aconst_null
    //   107: aconst_null
    //   108: aconst_null
    //   109: aconst_null
    //   110: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   113: astore 27
    //   115: ldc -57
    //   117: iconst_3
    //   118: newarray int
    //   120: dup
    //   121: iconst_0
    //   122: getstatic 213	com/cyou/cma/clauncher/Launcher:ʼ	I
    //   125: iconst_1
    //   126: iadd
    //   127: iastore
    //   128: dup
    //   129: iconst_1
    //   130: invokestatic 338	com/cyou/cma/clauncher/ˋי:ˋ	()I
    //   133: iconst_1
    //   134: iadd
    //   135: iastore
    //   136: dup
    //   137: iconst_2
    //   138: invokestatic 340	com/cyou/cma/clauncher/ˋי:ˎ	()I
    //   141: iconst_1
    //   142: iadd
    //   143: iastore
    //   144: invokestatic 346	java/lang/reflect/Array:newInstance	(Ljava/lang/Class;[I)Ljava/lang/Object;
    //   147: checkcast 348	[[[Lcom/cyou/cma/clauncher/ˆٴ;
    //   150: astore 32
    //   152: aload 27
    //   154: ldc_w 350
    //   157: invokeinterface 356 2 0
    //   162: istore_1
    //   163: aload 27
    //   165: ldc_w 358
    //   168: invokeinterface 356 2 0
    //   173: istore_2
    //   174: aload 27
    //   176: ldc -108
    //   178: invokeinterface 356 2 0
    //   183: istore_3
    //   184: aload 27
    //   186: ldc_w 360
    //   189: invokeinterface 356 2 0
    //   194: istore 4
    //   196: aload 27
    //   198: ldc_w 362
    //   201: invokeinterface 356 2 0
    //   206: istore 5
    //   208: aload 27
    //   210: ldc_w 364
    //   213: invokeinterface 356 2 0
    //   218: istore 6
    //   220: aload 27
    //   222: ldc_w 366
    //   225: invokeinterface 356 2 0
    //   230: istore 7
    //   232: aload 27
    //   234: ldc -88
    //   236: invokeinterface 356 2 0
    //   241: istore 8
    //   243: aload 27
    //   245: ldc_w 368
    //   248: invokeinterface 356 2 0
    //   253: istore 9
    //   255: aload 27
    //   257: ldc_w 370
    //   260: invokeinterface 356 2 0
    //   265: istore 10
    //   267: aload 27
    //   269: ldc_w 372
    //   272: invokeinterface 356 2 0
    //   277: istore 11
    //   279: aload 27
    //   281: ldc_w 374
    //   284: invokeinterface 356 2 0
    //   289: istore 12
    //   291: aload 27
    //   293: ldc_w 376
    //   296: invokeinterface 356 2 0
    //   301: istore 13
    //   303: aload 27
    //   305: ldc_w 378
    //   308: invokeinterface 356 2 0
    //   313: istore 14
    //   315: aload 27
    //   317: ldc_w 380
    //   320: invokeinterface 356 2 0
    //   325: istore 15
    //   327: aload 27
    //   329: ldc_w 382
    //   332: invokeinterface 356 2 0
    //   337: pop
    //   338: aload 27
    //   340: ldc_w 384
    //   343: invokeinterface 356 2 0
    //   348: pop
    //   349: aload 27
    //   351: ldc_w 386
    //   354: invokeinterface 356 2 0
    //   359: istore 16
    //   361: aload 27
    //   363: ldc_w 388
    //   366: invokeinterface 356 2 0
    //   371: istore 17
    //   373: aload_0
    //   374: getfield 390	com/cyou/cma/clauncher/ˎˑ:ʿ	Z
    //   377: ifne +1193 -> 1570
    //   380: aload 27
    //   382: invokeinterface 393 1 0
    //   387: istore 24
    //   389: iload 24
    //   391: ifeq +1179 -> 1570
    //   394: aload 27
    //   396: iload 9
    //   398: invokeinterface 397 2 0
    //   403: istore 18
    //   405: aload 27
    //   407: iload 17
    //   409: invokeinterface 401 2 0
    //   414: astore 33
    //   416: iload 18
    //   418: tableswitch	default:+1240->1658, 0:+38->456, 1:+38->456, 2:+526->944, 3:+1240->1658, 4:+723->1141, 5:+1023->1441
    //   456: aload 27
    //   458: iload_2
    //   459: invokeinterface 401 2 0
    //   464: astore 25
    //   466: aload 25
    //   468: iconst_0
    //   469: invokestatic 405	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   472: astore 34
    //   474: iload 18
    //   476: ifne +356 -> 832
    //   479: aload 33
    //   481: ifnull +326 -> 807
    //   484: aload_0
    //   485: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   488: aload 30
    //   490: aload 34
    //   492: aload 29
    //   494: aload 27
    //   496: iload 4
    //   498: iload 5
    //   500: iload_3
    //   501: aload 33
    //   503: invokevirtual 408	com/cyou/cma/clauncher/ˋי:ʻ	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;IIILjava/lang/String;)Lcom/cyou/cma/clauncher/ـʽ;
    //   506: astore 25
    //   508: aload 25
    //   510: aload 34
    //   512: putfield 413	com/cyou/cma/clauncher/ـʽ:ʼ	Landroid/content/Intent;
    //   515: aload 25
    //   517: ifnull +371 -> 888
    //   520: aload 25
    //   522: aload 27
    //   524: iload_1
    //   525: invokeinterface 417 2 0
    //   530: putfield 420	com/cyou/cma/clauncher/ـʽ:ٴ	J
    //   533: aload 27
    //   535: iload 8
    //   537: invokeinterface 397 2 0
    //   542: istore 18
    //   544: aload 25
    //   546: iload 18
    //   548: i2l
    //   549: putfield 421	com/cyou/cma/clauncher/ـʽ:ᴵ	J
    //   552: aload 25
    //   554: aload 27
    //   556: iload 11
    //   558: invokeinterface 397 2 0
    //   563: putfield 422	com/cyou/cma/clauncher/ـʽ:ᵎ	I
    //   566: aload 25
    //   568: aload 27
    //   570: iload 12
    //   572: invokeinterface 397 2 0
    //   577: putfield 423	com/cyou/cma/clauncher/ـʽ:ᵔ	I
    //   580: aload 25
    //   582: aload 27
    //   584: iload 13
    //   586: invokeinterface 397 2 0
    //   591: putfield 424	com/cyou/cma/clauncher/ـʽ:ᵢ	I
    //   594: aload 25
    //   596: aload 33
    //   598: putfield 426	com/cyou/cma/clauncher/ـʽ:ˋ	Ljava/lang/String;
    //   601: aload 32
    //   603: aload 25
    //   605: invokestatic 428	com/cyou/cma/clauncher/ˎˑ:ʻ	([[[Lcom/cyou/cma/clauncher/ˆٴ;Lcom/cyou/cma/clauncher/ˆٴ;)Z
    //   608: ifeq -235 -> 373
    //   611: iload 18
    //   613: tableswitch	default:+1048->1661, -101:+263->876, -100:+263->876
    //   636: getstatic 320	com/cyou/cma/clauncher/ˋי:ˉ	Ljava/util/HashMap;
    //   639: iload 18
    //   641: i2l
    //   642: invokestatic 431	com/cyou/cma/clauncher/ˋי:ʻ	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/ʿˑ;
    //   645: aload 25
    //   647: invokevirtual 436	com/cyou/cma/clauncher/ʿˑ:ʾ	(Lcom/cyou/cma/clauncher/ˆٴ;)V
    //   650: getstatic 325	com/cyou/cma/clauncher/ˋי:ʾ	Ljava/util/HashMap;
    //   653: aload 25
    //   655: getfield 420	com/cyou/cma/clauncher/ـʽ:ٴ	J
    //   658: invokestatic 441	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   661: aload 25
    //   663: invokevirtual 444	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   666: pop
    //   667: aload_0
    //   668: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   671: getstatic 327	com/cyou/cma/clauncher/ˋי:ˊ	Ljava/util/HashMap;
    //   674: aload 25
    //   676: aload 27
    //   678: iload 5
    //   680: invokevirtual 447	com/cyou/cma/clauncher/ˋי:ʻ	(Ljava/util/HashMap;Lcom/cyou/cma/clauncher/ـʽ;Landroid/database/Cursor;I)Z
    //   683: pop
    //   684: goto -311 -> 373
    //   687: astore 25
    //   689: ldc -38
    //   691: ldc_w 449
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
    //   722: getstatic 331	com/cyou/cma/clauncher/ˏᐧ:ʻ	Landroid/net/Uri;
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
    //   754: checkcast 438	java/lang/Long
    //   757: invokevirtual 477	java/lang/Long:longValue	()J
    //   760: lstore 21
    //   762: aload 25
    //   764: lload 21
    //   766: invokestatic 480	com/cyou/cma/clauncher/ˏᐧ:ʻ	(J)Landroid/net/Uri;
    //   769: aconst_null
    //   770: aconst_null
    //   771: invokevirtual 483	android/content/ContentProviderClient:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   774: pop
    //   775: goto -38 -> 737
    //   778: astore 27
    //   780: ldc -38
    //   782: new 220	java/lang/StringBuilder
    //   785: dup
    //   786: ldc_w 485
    //   789: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   792: lload 21
    //   794: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   797: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   800: invokestatic 490	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   803: pop
    //   804: goto -67 -> 737
    //   807: aload_0
    //   808: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   811: aload 30
    //   813: aload 34
    //   815: aload 29
    //   817: aload 27
    //   819: iload 4
    //   821: iload 5
    //   823: iload_3
    //   824: invokevirtual 493	com/cyou/cma/clauncher/ˋי:ʻ	(Landroid/content/pm/PackageManager;Landroid/content/Intent;Landroid/content/Context;Landroid/database/Cursor;III)Lcom/cyou/cma/clauncher/ـʽ;
    //   827: astore 25
    //   829: goto -321 -> 508
    //   832: aload_0
    //   833: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   836: aload 27
    //   838: aload 29
    //   840: iload 4
    //   842: iload 6
    //   844: iload 7
    //   846: iload 5
    //   848: iload_3
    //   849: invokestatic 496	com/cyou/cma/clauncher/ˋי:ʻ	(Lcom/cyou/cma/clauncher/ˋי;Landroid/database/Cursor;Landroid/content/Context;IIIII)Lcom/cyou/cma/clauncher/ـʽ;
    //   852: astore 25
    //   854: aload 25
    //   856: aload 34
    //   858: putfield 413	com/cyou/cma/clauncher/ـʽ:ʼ	Landroid/content/Intent;
    //   861: goto -346 -> 515
    //   864: astore 25
    //   866: aload 27
    //   868: invokeinterface 456 1 0
    //   873: aload 25
    //   875: athrow
    //   876: getstatic 309	com/cyou/cma/clauncher/ˋי:ʿ	Ljava/util/ArrayList;
    //   879: aload 25
    //   881: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   884: pop
    //   885: goto -235 -> 650
    //   888: aload 27
    //   890: iload_1
    //   891: invokeinterface 417 2 0
    //   896: lstore 21
    //   898: ldc -38
    //   900: new 220	java/lang/StringBuilder
    //   903: dup
    //   904: ldc_w 263
    //   907: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   910: lload 21
    //   912: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   915: ldc_w 502
    //   918: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   921: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   924: invokestatic 253	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   927: pop
    //   928: aload 28
    //   930: lload 21
    //   932: invokestatic 480	com/cyou/cma/clauncher/ˏᐧ:ʻ	(J)Landroid/net/Uri;
    //   935: aconst_null
    //   936: aconst_null
    //   937: invokevirtual 81	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   940: pop
    //   941: goto -568 -> 373
    //   944: aload 27
    //   946: iload_1
    //   947: invokeinterface 417 2 0
    //   952: lstore 21
    //   954: getstatic 320	com/cyou/cma/clauncher/ˋי:ˉ	Ljava/util/HashMap;
    //   957: lload 21
    //   959: invokestatic 431	com/cyou/cma/clauncher/ˋי:ʻ	(Ljava/util/HashMap;J)Lcom/cyou/cma/clauncher/ʿˑ;
    //   962: astore 25
    //   964: aload 25
    //   966: aload 27
    //   968: iload_3
    //   969: invokeinterface 401 2 0
    //   974: putfield 505	com/cyou/cma/clauncher/ʿˑ:ˎ	Ljava/lang/CharSequence;
    //   977: aload 25
    //   979: lload 21
    //   981: putfield 506	com/cyou/cma/clauncher/ʿˑ:ٴ	J
    //   984: aload 27
    //   986: iload 8
    //   988: invokeinterface 397 2 0
    //   993: istore 18
    //   995: aload 25
    //   997: iload 18
    //   999: i2l
    //   1000: putfield 507	com/cyou/cma/clauncher/ʿˑ:ᴵ	J
    //   1003: aload 25
    //   1005: aload 27
    //   1007: iload 11
    //   1009: invokeinterface 397 2 0
    //   1014: putfield 508	com/cyou/cma/clauncher/ʿˑ:ᵎ	I
    //   1017: aload 25
    //   1019: aload 27
    //   1021: iload 12
    //   1023: invokeinterface 397 2 0
    //   1028: putfield 509	com/cyou/cma/clauncher/ʿˑ:ᵔ	I
    //   1031: aload 25
    //   1033: aload 27
    //   1035: iload 13
    //   1037: invokeinterface 397 2 0
    //   1042: putfield 510	com/cyou/cma/clauncher/ʿˑ:ᵢ	I
    //   1045: aload 25
    //   1047: aload 27
    //   1049: iload 16
    //   1051: invokeinterface 401 2 0
    //   1056: putfield 513	com/cyou/cma/clauncher/ʿˑ:ᐧᐧ	Ljava/lang/String;
    //   1059: aload 32
    //   1061: aload 25
    //   1063: invokestatic 428	com/cyou/cma/clauncher/ˎˑ:ʻ	([[[Lcom/cyou/cma/clauncher/ˆٴ;Lcom/cyou/cma/clauncher/ˆٴ;)Z
    //   1066: ifeq -693 -> 373
    //   1069: iload 18
    //   1071: tableswitch	default:+593->1664, -101:+58->1129, -100:+58->1129
    //   1092: getstatic 325	com/cyou/cma/clauncher/ˋי:ʾ	Ljava/util/HashMap;
    //   1095: aload 25
    //   1097: getfield 506	com/cyou/cma/clauncher/ʿˑ:ٴ	J
    //   1100: invokestatic 441	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1103: aload 25
    //   1105: invokevirtual 444	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1108: pop
    //   1109: getstatic 320	com/cyou/cma/clauncher/ˋי:ˉ	Ljava/util/HashMap;
    //   1112: aload 25
    //   1114: getfield 506	com/cyou/cma/clauncher/ʿˑ:ٴ	J
    //   1117: invokestatic 441	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1120: aload 25
    //   1122: invokevirtual 444	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1125: pop
    //   1126: goto -753 -> 373
    //   1129: getstatic 309	com/cyou/cma/clauncher/ˋי:ʿ	Ljava/util/ArrayList;
    //   1132: aload 25
    //   1134: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1137: pop
    //   1138: goto -46 -> 1092
    //   1141: aload 27
    //   1143: iload 10
    //   1145: invokeinterface 397 2 0
    //   1150: istore 18
    //   1152: aload 27
    //   1154: iload_1
    //   1155: invokeinterface 417 2 0
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
    //   1200: new 220	java/lang/StringBuilder
    //   1203: dup
    //   1204: ldc_w 530
    //   1207: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1210: lload 21
    //   1212: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1215: ldc_w 532
    //   1218: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1221: iload 18
    //   1223: invokevirtual 237	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1226: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1229: astore 25
    //   1231: ldc -38
    //   1233: aload 25
    //   1235: invokestatic 253	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1238: pop
    //   1239: getstatic 535	com/cyou/cma/clauncher/Launcher:י	Ljava/util/ArrayList;
    //   1242: aload 25
    //   1244: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1247: pop
    //   1248: aload 26
    //   1250: lload 21
    //   1252: invokestatic 441	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1255: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1258: pop
    //   1259: goto -886 -> 373
    //   1262: new 537	com/cyou/cma/clauncher/ˋˆ
    //   1265: dup
    //   1266: iload 18
    //   1268: invokespecial 540	com/cyou/cma/clauncher/ˋˆ:<init>	(I)V
    //   1271: astore 25
    //   1273: aload 25
    //   1275: lload 21
    //   1277: putfield 541	com/cyou/cma/clauncher/ˋˆ:ٴ	J
    //   1280: aload 25
    //   1282: aload 27
    //   1284: iload 11
    //   1286: invokeinterface 397 2 0
    //   1291: putfield 542	com/cyou/cma/clauncher/ˋˆ:ᵎ	I
    //   1294: aload 25
    //   1296: aload 27
    //   1298: iload 12
    //   1300: invokeinterface 397 2 0
    //   1305: putfield 543	com/cyou/cma/clauncher/ˋˆ:ᵔ	I
    //   1308: aload 25
    //   1310: aload 27
    //   1312: iload 13
    //   1314: invokeinterface 397 2 0
    //   1319: putfield 544	com/cyou/cma/clauncher/ˋˆ:ᵢ	I
    //   1322: aload 25
    //   1324: aload 27
    //   1326: iload 14
    //   1328: invokeinterface 397 2 0
    //   1333: putfield 545	com/cyou/cma/clauncher/ˋˆ:ⁱ	I
    //   1336: aload 25
    //   1338: aload 27
    //   1340: iload 15
    //   1342: invokeinterface 397 2 0
    //   1347: putfield 546	com/cyou/cma/clauncher/ˋˆ:ﹳ	I
    //   1350: aload 27
    //   1352: iload 8
    //   1354: invokeinterface 397 2 0
    //   1359: istore 18
    //   1361: iload 18
    //   1363: bipush -100
    //   1365: if_icmpeq +22 -> 1387
    //   1368: iload 18
    //   1370: bipush -101
    //   1372: if_icmpeq +15 -> 1387
    //   1375: ldc -38
    //   1377: ldc_w 548
    //   1380: invokestatic 253	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1383: pop
    //   1384: goto -1011 -> 373
    //   1387: aload 25
    //   1389: aload 27
    //   1391: iload 8
    //   1393: invokeinterface 397 2 0
    //   1398: i2l
    //   1399: putfield 549	com/cyou/cma/clauncher/ˋˆ:ᴵ	J
    //   1402: aload 32
    //   1404: aload 25
    //   1406: invokestatic 428	com/cyou/cma/clauncher/ˎˑ:ʻ	([[[Lcom/cyou/cma/clauncher/ˆٴ;Lcom/cyou/cma/clauncher/ˆٴ;)Z
    //   1409: ifeq -1036 -> 373
    //   1412: getstatic 325	com/cyou/cma/clauncher/ˋי:ʾ	Ljava/util/HashMap;
    //   1415: aload 25
    //   1417: getfield 541	com/cyou/cma/clauncher/ˋˆ:ٴ	J
    //   1420: invokestatic 441	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1423: aload 25
    //   1425: invokevirtual 444	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1428: pop
    //   1429: getstatic 316	com/cyou/cma/clauncher/ˋי:ˆ	Ljava/util/ArrayList;
    //   1432: aload 25
    //   1434: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1437: pop
    //   1438: goto -1065 -> 373
    //   1441: aload 27
    //   1443: iload_2
    //   1444: invokeinterface 401 2 0
    //   1449: invokestatic 555	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1452: iconst_0
    //   1453: anewarray 551	java/lang/Class
    //   1456: invokevirtual 559	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   1459: iconst_0
    //   1460: anewarray 4	java/lang/Object
    //   1463: invokevirtual 564	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   1466: checkcast 566	com/cyou/cma/clauncher/ʼˊ
    //   1469: astore 25
    //   1471: aload 25
    //   1473: aload 27
    //   1475: iload_1
    //   1476: invokeinterface 417 2 0
    //   1481: putfield 567	com/cyou/cma/clauncher/ʼˊ:ٴ	J
    //   1484: aload 25
    //   1486: aload 27
    //   1488: iload 12
    //   1490: invokeinterface 397 2 0
    //   1495: putfield 568	com/cyou/cma/clauncher/ʼˊ:ᵔ	I
    //   1498: aload 25
    //   1500: aload 27
    //   1502: iload 13
    //   1504: invokeinterface 397 2 0
    //   1509: putfield 569	com/cyou/cma/clauncher/ʼˊ:ᵢ	I
    //   1512: aload 25
    //   1514: aload 27
    //   1516: iload 11
    //   1518: invokeinterface 397 2 0
    //   1523: putfield 570	com/cyou/cma/clauncher/ʼˊ:ᵎ	I
    //   1526: aload 25
    //   1528: aload 27
    //   1530: iload 8
    //   1532: invokeinterface 397 2 0
    //   1537: i2l
    //   1538: putfield 571	com/cyou/cma/clauncher/ʼˊ:ᴵ	J
    //   1541: getstatic 325	com/cyou/cma/clauncher/ˋי:ʾ	Ljava/util/HashMap;
    //   1544: aload 25
    //   1546: getfield 567	com/cyou/cma/clauncher/ʼˊ:ٴ	J
    //   1549: invokestatic 441	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1552: aload 25
    //   1554: invokevirtual 444	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1557: pop
    //   1558: getstatic 309	com/cyou/cma/clauncher/ˋי:ʿ	Ljava/util/ArrayList;
    //   1561: aload 25
    //   1563: invokevirtual 500	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1566: pop
    //   1567: goto -1194 -> 373
    //   1570: aload 27
    //   1572: invokeinterface 456 1 0
    //   1577: goto -865 -> 712
    //   1580: ldc_w 573
    //   1583: new 220	java/lang/StringBuilder
    //   1586: dup
    //   1587: ldc_w 575
    //   1590: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1593: invokestatic 297	android/os/SystemClock:uptimeMillis	()J
    //   1596: lload 19
    //   1598: lsub
    //   1599: invokevirtual 488	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1602: ldc_w 577
    //   1605: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 580	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1614: pop
    //   1615: aload_0
    //   1616: monitorenter
    //   1617: aload_0
    //   1618: getfield 390	com/cyou/cma/clauncher/ˎˑ:ʿ	Z
    //   1621: ifeq +10 -> 1631
    //   1624: aload_0
    //   1625: invokevirtual 583	java/lang/Object:notify	()V
    //   1628: aload_0
    //   1629: monitorexit
    //   1630: return
    //   1631: aload_0
    //   1632: getfield 20	com/cyou/cma/clauncher/ˎˑ:ʼ	Lcom/cyou/cma/clauncher/ˋי;
    //   1635: invokestatic 585	com/cyou/cma/clauncher/ˋי:ʽ	(Lcom/cyou/cma/clauncher/ˋי;)Z
    //   1638: pop
    //   1639: aload_0
    //   1640: monitorexit
    //   1641: aload_0
    //   1642: invokespecial 196	com/cyou/cma/clauncher/ˎˑ:ʾ	()V
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
    //   0	1667	0	this	ˎˑ
    //   162	1314	1	i	int
    //   173	1271	2	j	int
    //   183	786	3	k	int
    //   194	647	4	m	int
    //   206	641	5	n	int
    //   218	625	6	i1	int
    //   230	615	7	i2	int
    //   241	1290	8	i3	int
    //   253	144	9	i4	int
    //   265	879	10	i5	int
    //   277	1240	11	i6	int
    //   289	1200	12	i7	int
    //   301	1202	13	i8	int
    //   313	1014	14	i9	int
    //   325	1016	15	i10	int
    //   359	691	16	i11	int
    //   371	37	17	i12	int
    //   403	970	18	i13	int
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
    //   113	593	27	localCursor	Cursor
    //   778	793	27	localRemoteException	android.os.RemoteException
    //   39	890	28	localContentResolver	ContentResolver
    //   32	807	29	localContext	Context
    //   46	766	30	localPackageManager	PackageManager
    //   53	1110	31	localAppWidgetManager	android.appwidget.AppWidgetManager
    //   150	1253	32	arrayOfˆٴ	ˆٴ[][][]
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
  
  private void ʾ()
  {
    long l = SystemClock.uptimeMillis();
    ˋי.ʻ(this.ʼ, false);
    ˎˉ localˎˉ = (ˎˉ)ˋי.ʿ(this.ʼ).get();
    if (localˎˉ == null)
    {
      Log.w("Launcher.Model", "LoaderTask running with no launcher");
      return;
    }
    if (localˎˉ.ʿ())
    {
      ˋי.ˋ(this.ʼ);
      return;
    }
    ˋי.ʾ(this.ʼ).ʻ(new ˎᵎ(this, localˎˉ));
    int k = localˎˉ.ʾʾ();
    Object localObject3 = ˋי.ʻ(this.ʼ);
    Object localObject2 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.7D));
    Object localObject1 = new ArrayList((int)(((ArrayList)localObject3).size() * 0.3D));
    ArrayList localArrayList = new ArrayList(5);
    localObject3 = ((ArrayList)localObject3).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      ˆٴ localˆٴ = (ˆٴ)((Iterator)localObject3).next();
      if (localˆٴ.ᴵ == -101L) {
        localArrayList.add(localˆٴ);
      } else if (localˆٴ.ᵎ == k) {
        ((ArrayList)localObject2).add(localˆٴ);
      } else {
        ((ArrayList)localObject1).add(localˆٴ);
      }
    }
    ˋי.ʾ(this.ʼ).ʻ(new ˎᵔ(this, localˎˉ, localArrayList));
    int m = ((ArrayList)localObject2).size();
    int i = 0;
    if (i < m)
    {
      if (i + 2 <= m) {}
      for (j = 2;; j = m - i)
      {
        ˋי.ʾ(this.ʼ).ʻ(new ˎᵢ(this, localˎˉ, (ArrayList)localObject2, i, j));
        i += 2;
        break;
      }
    }
    int j = ˋי.ˆ.size();
    i = 0;
    while (i < j)
    {
      localObject2 = (ˋˆ)ˋי.ˆ.get(i);
      if (((ˋˆ)localObject2).ᵎ == k) {
        ˋי.ʾ(this.ʼ).ʻ(new ˎⁱ(this, localˎˉ, (ˋˆ)localObject2));
      }
      i += 1;
    }
    ˋי.ʾ(this.ʼ).ʻ(new ˎﹳ(this));
    m = ((ArrayList)localObject1).size();
    i = 0;
    if (i < m)
    {
      if (i + 2 <= m) {}
      for (j = 2;; j = m - i)
      {
        ˋי.ʾ(this.ʼ).ʻ(new ˎﹶ(this, localˎˉ, (ArrayList)localObject1, i, j));
        i += 2;
        break;
      }
    }
    j = ˋי.ˆ.size();
    i = 0;
    while (i < j)
    {
      localObject1 = (ˋˆ)ˋי.ˆ.get(i);
      if (((ˋˆ)localObject1).ᵎ != k) {
        ˋי.ʾ(this.ʼ).ʻ(new ˎﾞ(this, localˎˉ, (ˋˆ)localObject1));
      }
      i += 1;
    }
    localObject1 = new HashMap(ˋי.ˉ);
    ˋי.ʾ(this.ʼ).ʻ(new ˏʻ(this, localˎˉ, (HashMap)localObject1));
    ˋי.ʾ(this.ʼ).ʻ(new ˎי(this, localˎˉ));
    ˋי.ʾ(this.ʼ).ʻ(new ˎـ(this, l));
    if ((ʻ(localˎˉ) != null) && (this.ʼ.ʼ != null))
    {
      this.ʼ.ʼ.ʻ(ˋי.ˎ(this.ʼ), ˋי.ʾ(this.ʼ), localˎˉ);
      this.ʼ.ʼ = null;
    }
    ˋי.ʻ(this.ʼ, true);
  }
  
  private void ʿ()
  {
    if ((this.ˈ) || (!ˋי.ˏ(this.ʼ))) {
      if (!ʻ.ʻ().ﾞﾞ()) {}
    }
    label485:
    label508:
    label653:
    label659:
    label665:
    label709:
    do
    {
      return;
      long l1 = SystemClock.uptimeMillis();
      Object localObject1;
      ArrayList localArrayList;
      Cursor localCursor;
      Object localObject3;
      if ((ˎˉ)ˋי.ʿ(this.ʼ).get() != null)
      {
        Intent localIntent = new Intent("android.intent.action.MAIN", null);
        localIntent.addCategory("android.intent.category.LAUNCHER");
        PackageManager localPackageManager = this.ʽ.getPackageManager();
        localObject1 = localPackageManager.getInstalledApplications(8192);
        ˋי.ʻ(this.ʼ, ((List)localObject1).size());
        HashMap localHashMap = ʽ.ʼ();
        Map localMap = ʽ.ʻ();
        localObject1 = this.ʼ.ʽ;
        ((ˋ)localObject1).ʻ.clear();
        ((ˋ)localObject1).ʼ.clear();
        ((ˋ)localObject1).ʽ.clear();
        ((ˋ)localObject1).ʾ.clear();
        localHashMap.clear();
        localMap.clear();
        localArrayList = new ArrayList();
        localCursor = this.ʽ.getContentResolver().query(ˏـ.ʻ, new String[] { "_id", "package_name", "class_name", "hide", "item_type", "title", "container", "app_index", "is_classify_folder", "is_new_install", "folder_type" }, null, null, "package_name , class_name");
        while (localCursor.moveToNext())
        {
          int i = localCursor.getInt(6);
          int j = localCursor.getInt(4);
          int k = localCursor.getInt(7);
          long l2 = localCursor.getLong(0);
          Object localObject4 = localCursor.getString(10);
          localObject1 = localCursor.getString(5);
          Object localObject5;
          boolean bool;
          if (j == 0)
          {
            localObject3 = localCursor.getString(1);
            localObject5 = localCursor.getString(2);
            if (!ʻٴ.ʻ((String)localObject3, (String)localObject5))
            {
              localIntent.setComponent(new ComponentName((String)localObject3, (String)localObject5));
              localObject4 = localPackageManager.resolveActivity(localIntent, 0);
              if (localObject4 == null)
              {
                localObject4 = new ـ();
                ((ـ)localObject4).ˉ = true;
                ((ـ)localObject4).ˆ = 1;
                ((ـ)localObject4).ʾ = new ComponentName((String)localObject3, (String)localObject5);
                localObject3 = localObject1;
                if (localObject1 == null) {
                  localObject3 = "";
                }
                ((ـ)localObject4).ˎ = ((CharSequence)localObject3);
                ((ـ)localObject4).ʻ(((ـ)localObject4).ʾ);
                ((ـ)localObject4).ʼ = ˋי.ˊ(this.ʼ).ʾ();
                localObject1 = localObject4;
                ((ـ)localObject1).ٴ = l2;
                if (localCursor.getLong(3) != 1L) {
                  break label653;
                }
                bool = true;
                ((ـ)localObject1).ˏ = bool;
                if (localCursor.getLong(9) != 1L) {
                  break label659;
                }
              }
              for (bool = true;; bool = false)
              {
                ((ـ)localObject1).ʿ = bool;
                ((ـ)localObject1).ʻ();
                ((ـ)localObject1).ʼʼ = k;
                ((ـ)localObject1).ᐧ = j;
                ((ـ)localObject1).ᴵ = i;
                ((ـ)localObject1).ʽʽ = 1;
                if (!this.ʼ.ʽ.ʻ((ـ)localObject1)) {
                  break label709;
                }
                if (i != 65336) {
                  break label665;
                }
                if (localMap.containsKey(Long.valueOf(((ـ)localObject1).ٴ))) {
                  break;
                }
                localMap.put(Long.valueOf(((ـ)localObject1).ٴ), localObject1);
                break;
                localObject1 = new ـ(localPackageManager, (ResolveInfo)localObject4, ˋי.ˊ(this.ʼ));
                break label485;
                bool = false;
                break label508;
              }
              localObject3 = ˋי.ʼ(localHashMap, i);
              ((ʿˑ)localObject3).ʽʽ = 1;
              ((ʿˑ)localObject3).ᐧ = 1;
              ((ʿˑ)localObject3).ᴵ = -200L;
              ((ʿˑ)localObject3).a_();
              ((ʿˑ)localObject3).ʾ((ˆٴ)localObject1);
              continue;
              localArrayList.add(localObject1);
              Log.i("app2", "doubleApps =" + ((ـ)localObject1).ˎ);
            }
          }
          else if (j == 1)
          {
            localObject5 = ˋי.ʼ(localHashMap, l2);
            localObject3 = localObject1;
            if (localObject1 == null) {
              localObject3 = "";
            }
            ((ʿˑ)localObject5).ˎ = ((CharSequence)localObject3);
            ((ʿˑ)localObject5).ᴵ = -200L;
            ((ʿˑ)localObject5).ٴ = l2;
            ((ʿˑ)localObject5).ʼʼ = k;
            ((ʿˑ)localObject5).ʽʽ = 1;
            ((ʿˑ)localObject5).ᐧ = 1;
            if (localCursor.getInt(8) == 1) {}
            for (bool = true;; bool = false)
            {
              ((ʿˑ)localObject5).ʿ = bool;
              ((ʿˑ)localObject5).ᐧᐧ = ((String)localObject4);
              if (localMap.containsKey(Long.valueOf(((ʿˑ)localObject5).ٴ))) {
                break;
              }
              localMap.put(Long.valueOf(((ʿˑ)localObject5).ٴ), localObject5);
              break;
            }
          }
        }
        if (localCursor == null) {}
      }
      try
      {
        localCursor.close();
        ʽ.ʼ(this.ʽ, localArrayList);
        localArrayList.clear();
        Log.d("app2", "loadAllAppsByBatch time is=" + (SystemClock.uptimeMillis() - l1));
        localObject1 = this.ʼ.ʽ.ʻ;
        localObject3 = new ʾᵢ();
        ((ʾᵢ)localObject3).ʻ(ˋי.ˎ(this.ʼ), (ArrayList)localObject1, ˋי.ᴵ(this.ʼ));
        this.ʼ.ʼ = ((ʾᵢ)localObject3);
        try
        {
          if (this.ʿ) {
            return;
          }
        }
        finally {}
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
        ˋי.ˑ(this.ʼ);
        ˋי.י(this.ʼ);
      }
      ˆ();
    } while (!TextUtils.isEmpty(ʻ.ʻ().ˈˈ()));
    new Thread(new ˎٴ(this)).start();
  }
  
  private void ˆ()
  {
    if (this.ʻ) {}
    do
    {
      return;
      localˎˉ = (ˎˉ)ˋי.ʿ(this.ʼ).get();
    } while (localˎˉ == null);
    if (localˎˉ.ʿ())
    {
      ˋי.ـ(this.ʼ);
      return;
    }
    ˎˉ localˎˉ = ʻ(localˎˉ);
    if ((this.ʼ.ʼ != null) && (ˋי.ٴ(this.ʼ)))
    {
      this.ʼ.ʼ.ʻ(ˋי.ˎ(this.ʼ), ˋי.ʾ(this.ʼ), localˎˉ);
      this.ʼ.ʼ = null;
    }
    ˋי.ʾ(this.ʼ).ʻ(new ˎᐧ(this, localˎˉ));
  }
  
  public final void run()
  {
    int j = 0;
    ??? = (ˎˉ)ˋי.ʿ(this.ʼ).get();
    int i;
    if ((??? != null) && (((ˎˉ)???).ʻʻ())) {
      i = 0;
    }
    for (;;)
    {
      synchronized (ˋי.ˆ(this.ʼ))
      {
        if (this.ʾ)
        {
          label48:
          Process.setThreadPriority(j);
          if ((this.ˆ) || (i == 0)) {
            break label216;
          }
          ʽ();
          ʻ(this.ʽ);
          label77:
          if (!this.ʿ) {
            synchronized (ˋי.ˆ(this.ʼ))
            {
              if (this.ʾ) {
                Process.setThreadPriority(10);
              }
              if (!this.ˆ)
              {
                if (i == 0) {
                  break label238;
                }
                ʿ();
              }
            }
          }
        }
      }
      synchronized (ˋי.ˆ(this.ʼ))
      {
        Process.setThreadPriority(0);
        ??? = ˋי.ˊ.keySet().iterator();
        for (;;)
        {
          if (((Iterator)???).hasNext())
          {
            Object localObject2 = ((Iterator)???).next();
            this.ʼ.ʻ(this.ʽ, (ـʽ)localObject2, (byte[])ˋי.ˊ.get(localObject2));
            continue;
            i = 1;
            break;
            j = 10;
            break label48;
            localObject3 = finally;
            throw localObject3;
            label216:
            ʻ(this.ʽ);
            ʿ();
            break label77;
            localObject4 = finally;
            throw localObject4;
            label238:
            ʽ();
          }
        }
      }
    }
    ˋי.ˊ.clear();
    this.ʽ = null;
    synchronized (ˋי.ˆ(this.ʼ))
    {
      if (ˋי.ˈ(this.ʼ) == this) {
        ˋי.ˉ(this.ʼ);
      }
      return;
    }
  }
  
  final ˎˉ ʻ(ˎˉ paramˎˉ)
  {
    synchronized (ˋי.ˆ(this.ʼ))
    {
      if (this.ʿ) {
        return null;
      }
      if (ˋי.ʿ(this.ʼ) == null) {
        return null;
      }
      ˎˉ localˎˉ = (ˎˉ)ˋי.ʿ(this.ʼ).get();
      if (localˎˉ != paramˎˉ) {
        return null;
      }
      if (localˎˉ == null)
      {
        Log.w("Launcher.Model", "no mCallbacks");
        return null;
      }
      return localˎˉ;
    }
  }
  
  final boolean ʻ()
  {
    return this.ʾ;
  }
  
  public final void ʼ()
  {
    try
    {
      this.ʿ = true;
      notify();
      return;
    }
    finally {}
  }
}
