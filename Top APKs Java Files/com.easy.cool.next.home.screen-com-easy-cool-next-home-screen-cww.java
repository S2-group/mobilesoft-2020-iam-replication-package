package com.easy.cool.next.home.screen;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.easy.cool.next.home.screen.desktop.BubbleTextView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

public class cww
  extends cwz
{
  static final Object Code = new Object();
  private final HashMap<bnu, Bitmap> D = new HashMap();
  private final PackageManager L;
  final drw V = new drw();
  private final HashMap<ddt, S> a = new HashMap(50);
  private final int b;
  private final int c;
  private final BitmapFactory.Options d;
  private Boolean e;
  private String f;
  private Bitmap g;
  private Canvas h;
  private Paint i;
  
  public cww(Context paramContext, bwd paramBwd)
  {
    super(paramContext, paramBwd);
    this.L = paramContext.getPackageManager();
    this.b = paramContext.getResources().getColor(2131821022);
    this.c = paramContext.getResources().getColor(2131821023);
    this.d = new BitmapFactory.Options();
    this.d.inPreferredConfig = Bitmap.Config.RGB_565;
    B();
  }
  
  private S B(String paramString, bnu paramBnu)
  {
    return V(paramString, paramBnu, false);
  }
  
  private void B()
  {
    this.f = Locale.getDefault().toString();
  }
  
  private static ddt C(String paramString, bnu paramBnu)
  {
    return new ddt(new ComponentName(paramString, paramString + "."), paramBnu);
  }
  
  public static int Code(Context paramContext)
  {
    dpz localDpz = dea.Code().a();
    if ((localDpz == null) || (localDpz.B()))
    {
      efu.V("Launcher.IconCache", "No icon processor for current theme, safe to update icons in parallel");
      return -1;
    }
    return localDpz.Code(paramContext).Code("target_launcher_version_code", 0);
  }
  
  private ContentValues Code(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", dsy.Code(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.f);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", dsy.Code(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.g == null)
      {
        this.g = Bitmap.createBitmap(Math.max(paramBitmap.getWidth() / 5, 1), Math.max(paramBitmap.getHeight() / 5, 1), Bitmap.Config.RGB_565);
        this.h = new Canvas(this.g);
        this.i = new Paint(3);
      }
      this.h.drawColor(paramInt);
      this.h.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.g.getWidth(), this.g.getHeight()), this.i);
      localContentValues.put("icon_low_res", dsy.Code(this.g));
      return localContentValues;
    }
    finally {}
  }
  
  private static Bitmap Code(Cursor paramCursor, int paramInt, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(paramInt);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor) {}
    return null;
  }
  
  private Bitmap Code(S paramS, bnu paramBnu)
  {
    if (paramS.Code == null) {
      return Code(paramBnu);
    }
    return paramS.Code;
  }
  
  private Drawable Code(Resources paramResources, int paramInt, boolean paramBoolean)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.S);
      if (paramResources != null) {
        return paramResources;
      }
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;)
      {
        paramResources = null;
      }
      if (paramBoolean) {
        return Code();
      }
    }
    return null;
  }
  
  private S Code(ComponentName paramComponentName, cwv paramCwv, bnu paramBnu, boolean paramBoolean1, boolean paramBoolean2)
  {
    ddt localDdt = new ddt(paramComponentName, paramBnu);
    Object localObject2 = (S)this.a.get(localDdt);
    paramComponentName.toShortString();
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((S)localObject2).Z)
      {
        localObject1 = localObject2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localObject1 = new S();
      this.a.put(localDdt, localObject1);
      if (!Code(localDdt, (S)localObject1, paramBoolean2))
      {
        if (paramCwv == null) {
          break label234;
        }
        ((S)localObject1).Code = dsy.Code(paramCwv.Code(this.I, this.S), this.I);
      }
    }
    for (;;)
    {
      ContentValues localContentValues = Code(((S)localObject1).Code, ((S)localObject1).V.toString(), this.b);
      paramComponentName = null;
      try
      {
        localObject2 = this.L.getPackageInfo(this.I.getPackageName(), 0);
        paramComponentName = (ComponentName)localObject2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          ThrowableExtension.printStackTrace(localException);
        }
      }
      Code(localContentValues, localDdt.V, paramComponentName, this.B.Code(paramBnu));
      if ((TextUtils.isEmpty(((S)localObject1).V)) && (paramCwv != null))
      {
        ((S)localObject1).V = paramCwv.I();
        ((S)localObject1).I = this.B.Code(((S)localObject1).V, paramBnu);
      }
      return localObject1;
      label234:
      if (paramBoolean1)
      {
        paramComponentName = I(paramComponentName.getPackageName(), paramBnu, false);
        if (paramComponentName != null)
        {
          ((S)localObject1).Code = paramComponentName.Code;
          ((S)localObject1).V = paramComponentName.V;
          ((S)localObject1).I = paramComponentName.I;
        }
      }
      if (((S)localObject1).Code == null) {
        ((S)localObject1).Code = Code(paramBnu);
      }
    }
  }
  
  private S Code(String paramString, bnu paramBnu, boolean paramBoolean)
  {
    ddt localDdt = new ddt(new ComponentName("feature", paramString), paramBnu);
    Object localObject2 = (S)this.a.get(localDdt);
    Object localObject1;
    ContentValues localContentValues;
    if ((!paramBoolean) && (localObject2 != null))
    {
      localObject1 = localObject2;
      if (!((S)localObject2).Z) {}
    }
    else
    {
      localObject1 = new S();
      this.a.put(localDdt, localObject1);
      if ((paramBoolean) || (!Code(localDdt, (S)localObject1, false)))
      {
        ((S)localObject1).Code = ddu.Code(this.I, paramString, paramBnu);
        ((S)localObject1).V = ddu.Code(this.I, paramString);
        ((S)localObject1).I = "";
        if (((S)localObject1).Code == null) {
          ((S)localObject1).Code = Code(paramBnu);
        }
        localContentValues = Code(((S)localObject1).Code, ((S)localObject1).V.toString(), this.b);
        paramString = null;
      }
    }
    try
    {
      localObject2 = this.L.getPackageInfo(this.I.getPackageName(), 0);
      paramString = (String)localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localException);
      }
    }
    Code(localContentValues, localDdt.V, paramString, this.B.Code(paramBnu));
    return localObject1;
  }
  
  private void Code(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    if (paramPackageInfo != null)
    {
      paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
      paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    }
    paramContentValues = new cwx(paramContentValues, paramComponentName);
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      this.F.post(paramContentValues);
      return;
    }
    paramContentValues.run();
  }
  
  /* Error */
  private void Code(bnu paramBnu, List<cwv> paramList, Set<String> paramSet, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   4: aload_1
    //   5: invokevirtual 351	com/easy/cool/next/home/screen/bnv:Code	(Lcom/easy/cool/next/home/screen/bnu;)J
    //   8: lstore 11
    //   10: aload_0
    //   11: getfield 315	com/easy/cool/next/home/screen/cww:I	Landroid/content/Context;
    //   14: invokevirtual 84	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore 17
    //   19: new 65	java/util/HashMap
    //   22: dup
    //   23: invokespecial 66	java/util/HashMap:<init>	()V
    //   26: astore 16
    //   28: aload 17
    //   30: sipush 8192
    //   33: invokevirtual 488	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   36: astore 15
    //   38: aload 15
    //   40: invokeinterface 494 1 0
    //   45: astore 15
    //   47: aload 15
    //   49: invokeinterface 499 1 0
    //   54: ifeq +44 -> 98
    //   57: aload 15
    //   59: invokeinterface 503 1 0
    //   64: checkcast 436	android/content/pm/PackageInfo
    //   67: astore 17
    //   69: aload 16
    //   71: aload 17
    //   73: getfield 506	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   76: aload 17
    //   78: invokevirtual 309	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: pop
    //   82: goto -35 -> 47
    //   85: astore 15
    //   87: aload 17
    //   89: iconst_0
    //   90: invokevirtual 488	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   93: astore 15
    //   95: goto -57 -> 38
    //   98: new 65	java/util/HashMap
    //   101: dup
    //   102: invokespecial 66	java/util/HashMap:<init>	()V
    //   105: astore 17
    //   107: aload_2
    //   108: invokeinterface 494 1 0
    //   113: astore_2
    //   114: aload_2
    //   115: invokeinterface 499 1 0
    //   120: ifeq +32 -> 152
    //   123: aload_2
    //   124: invokeinterface 503 1 0
    //   129: checkcast 317	com/easy/cool/next/home/screen/cwv
    //   132: astore 15
    //   134: aload 17
    //   136: aload 15
    //   138: invokeinterface 509 1 0
    //   143: aload 15
    //   145: invokevirtual 309	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   148: pop
    //   149: goto -35 -> 114
    //   152: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   155: invokevirtual 512	com/easy/cool/next/home/screen/cwy:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   158: astore_2
    //   159: lload 11
    //   161: invokestatic 515	java/lang/Long:toString	(J)Ljava/lang/String;
    //   164: astore 15
    //   166: aload_2
    //   167: ldc_w 406
    //   170: iconst_5
    //   171: anewarray 517	java/lang/String
    //   174: dup
    //   175: iconst_0
    //   176: ldc_w 519
    //   179: aastore
    //   180: dup
    //   181: iconst_1
    //   182: ldc_w 418
    //   185: aastore
    //   186: dup
    //   187: iconst_2
    //   188: ldc_w 434
    //   191: aastore
    //   192: dup
    //   193: iconst_3
    //   194: ldc_w 442
    //   197: aastore
    //   198: dup
    //   199: iconst_4
    //   200: ldc -46
    //   202: aastore
    //   203: ldc_w 521
    //   206: iconst_1
    //   207: anewarray 517	java/lang/String
    //   210: dup
    //   211: iconst_0
    //   212: aload 15
    //   214: aastore
    //   215: aconst_null
    //   216: aconst_null
    //   217: aconst_null
    //   218: invokevirtual 525	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   221: astore_2
    //   222: aconst_null
    //   223: astore 15
    //   225: aload_2
    //   226: ifnonnull +37 -> 263
    //   229: ldc -83
    //   231: new 143	java/lang/StringBuilder
    //   234: dup
    //   235: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   238: ldc_w 527
    //   241: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload 15
    //   246: invokevirtual 530	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   249: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: invokestatic 416	com/easy/cool/next/home/screen/efu:Z	(Ljava/lang/String;Ljava/lang/String;)V
    //   255: return
    //   256: astore 15
    //   258: aconst_null
    //   259: astore_2
    //   260: goto -35 -> 225
    //   263: aload_2
    //   264: ldc_w 418
    //   267: invokeinterface 534 2 0
    //   272: istore 5
    //   274: aload_2
    //   275: ldc_w 434
    //   278: invokeinterface 534 2 0
    //   283: istore 6
    //   285: aload_2
    //   286: ldc_w 442
    //   289: invokeinterface 534 2 0
    //   294: istore 7
    //   296: aload_2
    //   297: ldc_w 519
    //   300: invokeinterface 534 2 0
    //   305: istore 8
    //   307: aload_2
    //   308: ldc -46
    //   310: invokeinterface 534 2 0
    //   315: istore 9
    //   317: new 536	java/util/HashSet
    //   320: dup
    //   321: invokespecial 537	java/util/HashSet:<init>	()V
    //   324: astore 18
    //   326: new 539	java/util/Stack
    //   329: dup
    //   330: invokespecial 540	java/util/Stack:<init>	()V
    //   333: astore 15
    //   335: aload_2
    //   336: invokeinterface 543 1 0
    //   341: ifeq +411 -> 752
    //   344: aload_2
    //   345: iload 5
    //   347: invokeinterface 547 2 0
    //   352: astore 21
    //   354: aload 21
    //   356: invokestatic 551	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   359: astore 19
    //   361: aload 19
    //   363: ifnonnull +115 -> 478
    //   366: aload 18
    //   368: aload_2
    //   369: iload 8
    //   371: invokeinterface 554 2 0
    //   376: invokestatic 450	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   379: invokevirtual 558	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   382: pop
    //   383: goto -48 -> 335
    //   386: astore_1
    //   387: aload_1
    //   388: invokestatic 377	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   391: aload_2
    //   392: invokeinterface 561 1 0
    //   397: aload 18
    //   399: invokevirtual 563	java/util/HashSet:isEmpty	()Z
    //   402: ifne +25 -> 427
    //   405: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   408: invokevirtual 404	com/easy/cool/next/home/screen/cwy:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   411: ldc_w 406
    //   414: ldc_w 519
    //   417: aload 18
    //   419: invokestatic 566	com/easy/cool/next/home/screen/dsy:Code	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   422: aconst_null
    //   423: invokevirtual 570	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   426: pop
    //   427: aload 17
    //   429: invokevirtual 571	java/util/HashMap:isEmpty	()Z
    //   432: ifne +337 -> 769
    //   435: aload 17
    //   437: invokevirtual 575	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   440: invokeinterface 578 1 0
    //   445: astore_1
    //   446: aload_1
    //   447: invokeinterface 499 1 0
    //   452: ifeq +317 -> 769
    //   455: aload 15
    //   457: aload_1
    //   458: invokeinterface 503 1 0
    //   463: checkcast 580	java/util/Map$Entry
    //   466: invokeinterface 583 1 0
    //   471: invokevirtual 584	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   474: pop
    //   475: goto -29 -> 446
    //   478: aload 16
    //   480: aload 19
    //   482: invokevirtual 369	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   485: invokevirtual 299	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   488: checkcast 436	android/content/pm/PackageInfo
    //   491: astore 20
    //   493: aload 20
    //   495: ifnonnull +119 -> 614
    //   498: aload 21
    //   500: ldc_w 586
    //   503: invokevirtual 590	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   506: ifeq +67 -> 573
    //   509: aload 17
    //   511: aload 19
    //   513: invokevirtual 593	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   516: checkcast 317	com/easy/cool/next/home/screen/cwv
    //   519: astore 20
    //   521: aload 20
    //   523: ifnonnull +39 -> 562
    //   526: aload_0
    //   527: aload 19
    //   529: aload_1
    //   530: invokevirtual 595	com/easy/cool/next/home/screen/cww:V	(Landroid/content/ComponentName;Lcom/easy/cool/next/home/screen/bnu;)V
    //   533: aload 18
    //   535: aload_2
    //   536: iload 8
    //   538: invokeinterface 554 2 0
    //   543: invokestatic 450	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   546: invokevirtual 558	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   549: pop
    //   550: goto -215 -> 335
    //   553: astore_1
    //   554: aload_2
    //   555: invokeinterface 561 1 0
    //   560: aload_1
    //   561: athrow
    //   562: aload 15
    //   564: aload 20
    //   566: invokevirtual 584	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   569: pop
    //   570: goto -235 -> 335
    //   573: aload_3
    //   574: aload 19
    //   576: invokevirtual 369	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   579: invokeinterface 598 2 0
    //   584: ifne -249 -> 335
    //   587: aload_0
    //   588: aload 19
    //   590: aload_1
    //   591: invokevirtual 595	com/easy/cool/next/home/screen/cww:V	(Landroid/content/ComponentName;Lcom/easy/cool/next/home/screen/bnu;)V
    //   594: aload 18
    //   596: aload_2
    //   597: iload 8
    //   599: invokeinterface 554 2 0
    //   604: invokestatic 450	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   607: invokevirtual 558	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   610: pop
    //   611: goto -276 -> 335
    //   614: aload 20
    //   616: getfield 602	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   619: getfield 607	android/content/pm/ApplicationInfo:flags	I
    //   622: ldc_w 608
    //   625: iand
    //   626: ifne -291 -> 335
    //   629: aload_2
    //   630: iload 6
    //   632: invokeinterface 612 2 0
    //   637: lstore 13
    //   639: aload_2
    //   640: iload 7
    //   642: invokeinterface 554 2 0
    //   647: istore 10
    //   649: aload 17
    //   651: aload 19
    //   653: invokevirtual 593	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   656: checkcast 317	com/easy/cool/next/home/screen/cwv
    //   659: astore 21
    //   661: iload 10
    //   663: aload 20
    //   665: getfield 445	android/content/pm/PackageInfo:versionCode	I
    //   668: if_icmpne +32 -> 700
    //   671: lload 13
    //   673: aload 20
    //   675: getfield 440	android/content/pm/PackageInfo:lastUpdateTime	J
    //   678: lcmp
    //   679: ifne +21 -> 700
    //   682: aload_0
    //   683: getfield 135	com/easy/cool/next/home/screen/cww:f	Ljava/lang/String;
    //   686: aload_2
    //   687: iload 9
    //   689: invokeinterface 547 2 0
    //   694: invokestatic 616	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   697: ifeq +3 -> 700
    //   700: aload 21
    //   702: ifnonnull +39 -> 741
    //   705: aload_0
    //   706: aload 19
    //   708: invokespecial 619	com/easy/cool/next/home/screen/cww:Code	(Landroid/content/ComponentName;)Z
    //   711: ifne -376 -> 335
    //   714: aload_0
    //   715: aload 19
    //   717: aload_1
    //   718: invokevirtual 595	com/easy/cool/next/home/screen/cww:V	(Landroid/content/ComponentName;Lcom/easy/cool/next/home/screen/bnu;)V
    //   721: aload 18
    //   723: aload_2
    //   724: iload 8
    //   726: invokeinterface 554 2 0
    //   731: invokestatic 450	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   734: invokevirtual 558	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   737: pop
    //   738: goto -403 -> 335
    //   741: aload 15
    //   743: aload 21
    //   745: invokevirtual 584	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   748: pop
    //   749: goto -414 -> 335
    //   752: aload_2
    //   753: invokeinterface 561 1 0
    //   758: goto -361 -> 397
    //   761: astore_1
    //   762: aload_1
    //   763: invokestatic 377	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   766: goto -339 -> 427
    //   769: aload 17
    //   771: invokevirtual 571	java/util/HashMap:isEmpty	()Z
    //   774: ifeq +11 -> 785
    //   777: aload 15
    //   779: invokevirtual 620	java/util/Stack:isEmpty	()Z
    //   782: ifne -527 -> 255
    //   785: new 539	java/util/Stack
    //   788: dup
    //   789: invokespecial 540	java/util/Stack:<init>	()V
    //   792: astore_1
    //   793: aload_1
    //   794: aload 17
    //   796: invokevirtual 624	java/util/HashMap:values	()Ljava/util/Collection;
    //   799: invokevirtual 628	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   802: pop
    //   803: iload 4
    //   805: ifeq +30 -> 835
    //   808: aload_0
    //   809: invokespecial 630	com/easy/cool/next/home/screen/cww:Z	()Z
    //   812: ifeq +23 -> 835
    //   815: new 21	com/easy/cool/next/home/screen/cww$l
    //   818: dup
    //   819: aload_0
    //   820: lload 11
    //   822: aload 16
    //   824: aload_1
    //   825: aload 15
    //   827: iconst_1
    //   828: invokespecial 633	com/easy/cool/next/home/screen/cww$l:<init>	(Lcom/easy/cool/next/home/screen/cww;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;Z)V
    //   831: invokevirtual 635	com/easy/cool/next/home/screen/cww$l:C	()V
    //   834: return
    //   835: new 15	com/easy/cool/next/home/screen/cww$T
    //   838: dup
    //   839: aload_0
    //   840: lload 11
    //   842: aload 16
    //   844: aload_1
    //   845: aload 15
    //   847: iload 4
    //   849: invokespecial 636	com/easy/cool/next/home/screen/cww$T:<init>	(Lcom/easy/cool/next/home/screen/cww;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;Z)V
    //   852: invokevirtual 638	com/easy/cool/next/home/screen/cww$T:I	()Z
    //   855: pop
    //   856: return
    //   857: astore_1
    //   858: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	859	0	this	cww
    //   0	859	1	paramBnu	bnu
    //   0	859	2	paramList	List<cwv>
    //   0	859	3	paramSet	Set<String>
    //   0	859	4	paramBoolean	boolean
    //   272	74	5	j	int
    //   283	348	6	k	int
    //   294	347	7	m	int
    //   305	420	8	n	int
    //   315	373	9	i1	int
    //   647	22	10	i2	int
    //   8	833	11	l1	long
    //   637	35	13	l2	long
    //   36	22	15	localObject1	Object
    //   85	1	15	localRuntimeException	RuntimeException
    //   93	152	15	localObject2	Object
    //   256	1	15	localSQLiteException	SQLiteException
    //   333	513	15	localStack	Stack
    //   26	817	16	localHashMap	HashMap
    //   17	778	17	localObject3	Object
    //   324	398	18	localHashSet	HashSet
    //   359	357	19	localComponentName	ComponentName
    //   491	183	20	localObject4	Object
    //   352	392	21	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   28	38	85	java/lang/RuntimeException
    //   152	222	256	android/database/sqlite/SQLiteException
    //   335	361	386	android/database/sqlite/SQLiteException
    //   366	383	386	android/database/sqlite/SQLiteException
    //   478	493	386	android/database/sqlite/SQLiteException
    //   498	521	386	android/database/sqlite/SQLiteException
    //   526	550	386	android/database/sqlite/SQLiteException
    //   562	570	386	android/database/sqlite/SQLiteException
    //   573	611	386	android/database/sqlite/SQLiteException
    //   614	700	386	android/database/sqlite/SQLiteException
    //   705	738	386	android/database/sqlite/SQLiteException
    //   741	749	386	android/database/sqlite/SQLiteException
    //   335	361	553	finally
    //   366	383	553	finally
    //   387	391	553	finally
    //   478	493	553	finally
    //   498	521	553	finally
    //   526	550	553	finally
    //   562	570	553	finally
    //   573	611	553	finally
    //   614	700	553	finally
    //   705	738	553	finally
    //   741	749	553	finally
    //   405	427	761	android/database/sqlite/SQLiteException
    //   87	95	857	java/lang/RuntimeException
  }
  
  private boolean Code(ComponentName paramComponentName)
  {
    boolean bool2 = false;
    String str = paramComponentName.getPackageName();
    paramComponentName = paramComponentName.getClassName();
    int j = paramComponentName.lastIndexOf(".");
    boolean bool1 = bool2;
    if (j != -1)
    {
      bool1 = bool2;
      if (TextUtils.equals(str, paramComponentName.substring(0, j))) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  /* Error */
  private boolean Code(ddt paramDdt, S paramS, boolean paramBoolean)
  {
    // Byte code:
    //   0: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   3: invokevirtual 512	com/easy/cool/next/home/screen/cwy:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 5
    //   8: iload_3
    //   9: ifeq +83 -> 92
    //   12: ldc -44
    //   14: astore 4
    //   16: aload_1
    //   17: getfield 343	com/easy/cool/next/home/screen/ddt:V	Landroid/content/ComponentName;
    //   20: invokevirtual 421	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   23: astore 6
    //   25: aload_0
    //   26: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   29: aload_1
    //   30: getfield 654	com/easy/cool/next/home/screen/ddt:I	Lcom/easy/cool/next/home/screen/bnu;
    //   33: invokevirtual 351	com/easy/cool/next/home/screen/bnv:Code	(Lcom/easy/cool/next/home/screen/bnu;)J
    //   36: invokestatic 515	java/lang/Long:toString	(J)Ljava/lang/String;
    //   39: astore 7
    //   41: aload 5
    //   43: ldc_w 406
    //   46: iconst_2
    //   47: anewarray 517	java/lang/String
    //   50: dup
    //   51: iconst_0
    //   52: aload 4
    //   54: aastore
    //   55: dup
    //   56: iconst_1
    //   57: ldc -50
    //   59: aastore
    //   60: ldc_w 656
    //   63: iconst_2
    //   64: anewarray 517	java/lang/String
    //   67: dup
    //   68: iconst_0
    //   69: aload 6
    //   71: aastore
    //   72: dup
    //   73: iconst_1
    //   74: aload 7
    //   76: aastore
    //   77: aconst_null
    //   78: aconst_null
    //   79: aconst_null
    //   80: invokevirtual 525	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   83: astore 5
    //   85: aload 5
    //   87: ifnonnull +15 -> 102
    //   90: iconst_0
    //   91: ireturn
    //   92: ldc -61
    //   94: astore 4
    //   96: goto -80 -> 16
    //   99: astore_1
    //   100: iconst_0
    //   101: ireturn
    //   102: aload 5
    //   104: invokeinterface 543 1 0
    //   109: ifeq +110 -> 219
    //   112: iload_3
    //   113: ifeq +68 -> 181
    //   116: aload_0
    //   117: getfield 107	com/easy/cool/next/home/screen/cww:d	Landroid/graphics/BitmapFactory$Options;
    //   120: astore 4
    //   122: aload_2
    //   123: aload 5
    //   125: iconst_0
    //   126: aload 4
    //   128: invokestatic 658	com/easy/cool/next/home/screen/cww:Code	(Landroid/database/Cursor;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   131: putfield 279	com/easy/cool/next/home/screen/cww$S:Code	Landroid/graphics/Bitmap;
    //   134: aload_2
    //   135: iload_3
    //   136: putfield 305	com/easy/cool/next/home/screen/cww$S:Z	Z
    //   139: aload_2
    //   140: aload 5
    //   142: iconst_1
    //   143: invokeinterface 547 2 0
    //   148: putfield 326	com/easy/cool/next/home/screen/cww$S:V	Ljava/lang/CharSequence;
    //   151: aload_2
    //   152: getfield 326	com/easy/cool/next/home/screen/cww$S:V	Ljava/lang/CharSequence;
    //   155: ifnonnull +32 -> 187
    //   158: aload_2
    //   159: ldc_w 392
    //   162: putfield 326	com/easy/cool/next/home/screen/cww$S:V	Ljava/lang/CharSequence;
    //   165: aload_2
    //   166: ldc_w 392
    //   169: putfield 368	com/easy/cool/next/home/screen/cww$S:I	Ljava/lang/CharSequence;
    //   172: aload 5
    //   174: invokeinterface 561 1 0
    //   179: iconst_1
    //   180: ireturn
    //   181: aconst_null
    //   182: astore 4
    //   184: goto -62 -> 122
    //   187: aload_2
    //   188: aload_0
    //   189: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   192: aload_2
    //   193: getfield 326	com/easy/cool/next/home/screen/cww$S:V	Ljava/lang/CharSequence;
    //   196: aload_1
    //   197: getfield 654	com/easy/cool/next/home/screen/ddt:I	Lcom/easy/cool/next/home/screen/bnu;
    //   200: invokevirtual 366	com/easy/cool/next/home/screen/bnv:Code	(Ljava/lang/CharSequence;Lcom/easy/cool/next/home/screen/bnu;)Ljava/lang/CharSequence;
    //   203: putfield 368	com/easy/cool/next/home/screen/cww$S:I	Ljava/lang/CharSequence;
    //   206: goto -34 -> 172
    //   209: astore_1
    //   210: aload 5
    //   212: invokeinterface 561 1 0
    //   217: iconst_0
    //   218: ireturn
    //   219: aload 5
    //   221: invokeinterface 561 1 0
    //   226: iconst_0
    //   227: ireturn
    //   228: astore_1
    //   229: aload 5
    //   231: invokeinterface 561 1 0
    //   236: aload_1
    //   237: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	238	0	this	cww
    //   0	238	1	paramDdt	ddt
    //   0	238	2	paramS	S
    //   0	238	3	paramBoolean	boolean
    //   14	169	4	localObject1	Object
    //   6	224	5	localObject2	Object
    //   23	47	6	str1	String
    //   39	36	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   0	8	99	android/database/sqlite/SQLiteException
    //   16	85	99	android/database/sqlite/SQLiteException
    //   102	112	209	android/database/sqlite/SQLiteException
    //   116	122	209	android/database/sqlite/SQLiteException
    //   122	172	209	android/database/sqlite/SQLiteException
    //   187	206	209	android/database/sqlite/SQLiteException
    //   102	112	228	finally
    //   116	122	228	finally
    //   122	172	228	finally
    //   187	206	228	finally
  }
  
  private Bitmap I(bnu paramBnu)
  {
    Object localObject = Code();
    if (localObject == null) {
      return erf.Code();
    }
    paramBnu = this.B.Code((Drawable)localObject, paramBnu);
    localObject = Bitmap.createBitmap(Math.max(paramBnu.getIntrinsicWidth(), 1), Math.max(paramBnu.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramBnu.setBounds(0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
    paramBnu.draw(localCanvas);
    localCanvas.setBitmap(null);
    return localObject;
  }
  
  private S I(String paramString, bnu paramBnu, boolean paramBoolean)
  {
    ddt localDdt = C(paramString, paramBnu);
    Object localObject2 = (S)this.a.get(localDdt);
    Object localObject1;
    int j;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((S)localObject2).Z)
      {
        localObject1 = localObject2;
        if (paramBoolean) {}
      }
    }
    else
    {
      localObject1 = new S();
      if (Code(localDdt, (S)localObject1, paramBoolean)) {
        break label259;
      }
      try
      {
        if (bnu.Code().equals(paramBnu))
        {
          j = 0;
          paramString = this.L.getPackageInfo(paramString, j);
          localObject2 = paramString.applicationInfo;
          if (localObject2 != null) {
            break label145;
          }
          throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
        }
      }
      catch (Exception paramString)
      {
        j = 0;
      }
    }
    for (;;)
    {
      if (j != 0) {
        this.a.put(localDdt, localObject1);
      }
      return localObject1;
      j = 8192;
      break;
      label145:
      ((S)localObject1).Code = dsy.Code(this.B.Code(((ApplicationInfo)localObject2).loadIcon(this.L), paramBnu), this.I);
      ((S)localObject1).V = ((ApplicationInfo)localObject2).loadLabel(this.L);
      ((S)localObject1).I = this.B.Code(((S)localObject1).V, paramBnu);
      ((S)localObject1).Z = false;
      Code(Code(((S)localObject1).Code, ((S)localObject1).V.toString(), this.c), localDdt.V, paramString, this.B.Code(paramBnu));
      j = 1;
      continue;
      label259:
      j = 1;
    }
  }
  
  private List<S> I(String paramString, bnu paramBnu)
  {
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.a.keySet().iterator();
    while (localIterator.hasNext())
    {
      ddt localDdt = (ddt)localIterator.next();
      if ((localDdt.V.getPackageName().equals(paramString)) && (localDdt.I.equals(paramBnu))) {
        localHashSet.add(localDdt);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramBnu = (ddt)paramString.next();
      localArrayList.add(this.a.remove(paramBnu));
    }
    return localArrayList;
  }
  
  private S V(String paramString, bnu paramBnu, boolean paramBoolean)
  {
    ddt localDdt = new ddt(new ComponentName("game", paramString), paramBnu);
    Object localObject2 = (S)this.a.get(localDdt);
    Object localObject1;
    ContentValues localContentValues;
    if ((!paramBoolean) && (localObject2 != null))
    {
      localObject1 = localObject2;
      if (!((S)localObject2).Z) {}
    }
    else
    {
      localObject1 = new S();
      this.a.put(localDdt, localObject1);
      if ((paramBoolean) || (!Code(localDdt, (S)localObject1, false)))
      {
        ((S)localObject1).Code = ddx.Code(this.I, paramString, paramBnu);
        ((S)localObject1).V = ddx.Code(this.I, paramString);
        ((S)localObject1).I = "";
        if (((S)localObject1).Code == null) {
          ((S)localObject1).Code = Code(paramBnu);
        }
        localContentValues = Code(((S)localObject1).Code, ((S)localObject1).V.toString(), this.b);
        paramString = null;
      }
    }
    try
    {
      localObject2 = this.L.getPackageInfo(this.I.getPackageName(), 0);
      paramString = (String)localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localException);
      }
    }
    Code(localContentValues, localDdt.V, paramString, this.B.Code(paramBnu));
    return localObject1;
  }
  
  private List<cwv> V(bnu paramBnu)
  {
    ArrayList localArrayList = new ArrayList();
    paramBnu = this.C.Code(null, paramBnu);
    if ((paramBnu != null) && (!paramBnu.isEmpty())) {
      localArrayList.addAll(paramBnu);
    }
    paramBnu = ddu.b();
    if ((paramBnu != null) && (!paramBnu.isEmpty())) {
      localArrayList.addAll(paramBnu);
    }
    return localArrayList;
  }
  
  private S Z(String paramString, bnu paramBnu)
  {
    return Code(paramString, paramBnu, false);
  }
  
  private boolean Z()
  {
    label100:
    for (;;)
    {
      try
      {
        if (this.e != null)
        {
          bool = this.e.booleanValue();
          return bool;
        }
        int j = Code(this.I);
        if (j != -1) {
          if (j >= 42)
          {
            break label100;
            this.e = Boolean.valueOf(bool);
            efu.V("Launcher.IconCache", "Parallel icon update enabled: " + this.e);
            bool = this.e.booleanValue();
          }
          else
          {
            bool = false;
            continue;
          }
        }
        boolean bool = true;
      }
      finally {}
    }
  }
  
  ContentValues Code(cwv paramCwv, boolean paramBoolean)
  {
    Object localObject1 = new ddt(paramCwv.Code(), paramCwv.V());
    Object localObject2;
    if (!paramBoolean)
    {
      localObject2 = (S)this.a.get(localObject1);
      if ((localObject2 != null) && (!((S)localObject2).Z))
      {
        localObject1 = localObject2;
        if (((S)localObject2).Code != null) {
          break label63;
        }
      }
    }
    for (localObject1 = null;; localObject1 = null)
    {
      label63:
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = new S();
        ((S)localObject2).Code = dsy.Code(paramCwv.Code(this.I, this.S), this.I);
      }
      ((S)localObject2).V = paramCwv.I();
      ((S)localObject2).I = this.B.Code(((S)localObject2).V, paramCwv.V());
      this.a.put(new ddt(paramCwv.Code(), paramCwv.V()), localObject2);
      return Code(((S)localObject2).Code, ((S)localObject2).V.toString(), this.b);
    }
  }
  
  /* Error */
  public Bitmap Code(Intent paramIntent, bnu paramBnu)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 765	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +13 -> 21
    //   11: aload_0
    //   12: aload_2
    //   13: invokevirtual 282	com/easy/cool/next/home/screen/cww:Code	(Lcom/easy/cool/next/home/screen/bnu;)Landroid/graphics/Bitmap;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: aload_3
    //   23: aload_0
    //   24: getfield 732	com/easy/cool/next/home/screen/cww:C	Lcom/easy/cool/next/home/screen/bno;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 768	com/easy/cool/next/home/screen/bno:Code	(Landroid/content/Intent;Lcom/easy/cool/next/home/screen/bnu;)Lcom/easy/cool/next/home/screen/bnl;
    //   32: aload_2
    //   33: iconst_1
    //   34: iconst_0
    //   35: invokespecial 770	com/easy/cool/next/home/screen/cww:Code	(Landroid/content/ComponentName;Lcom/easy/cool/next/home/screen/cwv;Lcom/easy/cool/next/home/screen/bnu;ZZ)Lcom/easy/cool/next/home/screen/cww$S;
    //   38: getfield 279	com/easy/cool/next/home/screen/cww$S:Code	Landroid/graphics/Bitmap;
    //   41: astore_1
    //   42: goto -25 -> 17
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	cww
    //   0	50	1	paramIntent	Intent
    //   0	50	2	paramBnu	bnu
    //   6	17	3	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   11	17	45	finally
    //   21	42	45	finally
  }
  
  public Bitmap Code(bnu paramBnu)
  {
    try
    {
      if (!this.D.containsKey(paramBnu)) {
        this.D.put(paramBnu, I(paramBnu));
      }
      paramBnu = (Bitmap)this.D.get(paramBnu);
      return paramBnu;
    }
    finally {}
  }
  
  public Drawable Code()
  {
    return Code(Resources.getSystem(), 17629184, false);
  }
  
  public Drawable Code(ActivityInfo paramActivityInfo)
  {
    try
    {
      Resources localResources = this.L.getResourcesForApplication(paramActivityInfo.applicationInfo);
      if (localResources != null)
      {
        int j = paramActivityInfo.getIconResource();
        if (j != 0) {
          return Code(localResources, j, true);
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
    return Code();
  }
  
  public Drawable Code(String paramString)
  {
    paramString = I(paramString);
    return new BitmapDrawable(this.I.getResources(), paramString.Code);
  }
  
  public Drawable Code(String paramString, int paramInt)
  {
    try
    {
      paramString = this.L.getResourcesForApplication(paramString);
      if ((paramString != null) && (paramInt != 0)) {
        return Code(paramString, paramInt, true);
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
    return Code();
  }
  
  public Y Code(final BubbleTextView paramBubbleTextView, final ddz paramDdz)
  {
    paramBubbleTextView = new Runnable()
    {
      public void run()
      {
        if ((paramDdz instanceof dds)) {
          cww.this.Code((dds)paramDdz, null, false);
        }
        for (;;)
        {
          cww.this.V.execute(new Runnable()
          {
            public void run()
            {
              cww.2.this.V.Code(cww.2.this.Code);
            }
          });
          return;
          Object localObject;
          if ((paramDdz instanceof dfb))
          {
            dfb localDfb = (dfb)paramDdz;
            cww localCww = cww.this;
            if (localDfb.w != null) {}
            for (localObject = localDfb.w;; localObject = localDfb.Z)
            {
              localCww.Code(localDfb, (Intent)localObject, localDfb.n, false);
              break;
            }
          }
          if ((paramDdz instanceof dez))
          {
            localObject = (dez)paramDdz;
            cww.this.Code(((dez)localObject).n, false, (dez)localObject);
          }
        }
      }
    };
    this.F.post(paramBubbleTextView);
    return new Y(paramBubbleTextView, this.F);
  }
  
  /* Error */
  public void Code(ComponentName paramComponentName, bnu paramBnu)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 595	com/easy/cool/next/home/screen/cww:V	(Landroid/content/ComponentName;Lcom/easy/cool/next/home/screen/bnu;)V
    //   8: aload_0
    //   9: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   12: aload_2
    //   13: invokevirtual 351	com/easy/cool/next/home/screen/bnv:Code	(Lcom/easy/cool/next/home/screen/bnu;)J
    //   16: lstore_3
    //   17: aload_1
    //   18: invokevirtual 643	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   21: invokestatic 360	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifeq +59 -> 83
    //   27: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   30: invokevirtual 404	com/easy/cool/next/home/screen/cwy:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: ldc_w 406
    //   36: ldc_w 817
    //   39: iconst_2
    //   40: anewarray 517	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: new 143	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   52: aload_1
    //   53: invokevirtual 421	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   56: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: ldc_w 819
    //   62: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: lload_3
    //   72: invokestatic 515	java/lang/Long:toString	(J)Ljava/lang/String;
    //   75: aastore
    //   76: invokevirtual 570	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   79: pop
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   86: invokevirtual 404	com/easy/cool/next/home/screen/cwy:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   89: ldc_w 406
    //   92: ldc_w 656
    //   95: iconst_2
    //   96: anewarray 517	java/lang/String
    //   99: dup
    //   100: iconst_0
    //   101: aload_1
    //   102: invokevirtual 421	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   105: aastore
    //   106: dup
    //   107: iconst_1
    //   108: lload_3
    //   109: invokestatic 515	java/lang/Long:toString	(J)Ljava/lang/String;
    //   112: aastore
    //   113: invokevirtual 570	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   116: pop
    //   117: goto -37 -> 80
    //   120: astore_1
    //   121: aload_1
    //   122: invokestatic 377	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   125: goto -45 -> 80
    //   128: astore_1
    //   129: aload_0
    //   130: monitorexit
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	cww
    //   0	133	1	paramComponentName	ComponentName
    //   0	133	2	paramBnu	bnu
    //   16	93	3	l	long
    // Exception table:
    //   from	to	target	type
    //   17	80	120	android/database/sqlite/SQLiteException
    //   83	117	120	android/database/sqlite/SQLiteException
    //   2	17	128	finally
    //   17	80	128	finally
    //   83	117	128	finally
    //   121	125	128	finally
  }
  
  public void Code(final ComponentName paramComponentName, final bnu paramBnu, final Runnable paramRunnable, final boolean paramBoolean)
  {
    this.F.post(new Runnable()
    {
      public void run()
      {
        cww.this.Code(paramComponentName, paramBnu);
        if (paramRunnable != null)
        {
          if (paramBoolean) {
            esa.I(paramRunnable);
          }
        }
        else {
          return;
        }
        paramRunnable.run();
      }
    });
  }
  
  public void Code(bnu paramBnu, boolean paramBoolean, dez paramDez)
  {
    try
    {
      S localS = I(paramDez.I, paramBnu, paramBoolean);
      paramDez.Code = Code(localS, paramBnu);
      paramDez.k = dsy.Code(localS.V);
      paramDez.V = localS.Z;
      paramDez.l = localS.I;
      return;
    }
    finally
    {
      paramBnu = finally;
      throw paramBnu;
    }
  }
  
  void Code(cwv paramCwv, PackageInfo paramPackageInfo, long paramLong)
  {
    Code(Code(paramCwv, false), paramCwv.Code(), paramPackageInfo, paramLong);
  }
  
  public void Code(dds paramDds)
  {
    try
    {
      S localS = Code(paramDds.S, null, paramDds.n, false, paramDds.Z);
      if ((localS.Code != null) && (!Code(localS.Code, paramDds.n)))
      {
        paramDds.k = dsy.Code(localS.V);
        paramDds.I = localS.Code;
        paramDds.l = localS.I;
        paramDds.Z = localS.Z;
      }
      return;
    }
    finally
    {
      paramDds = finally;
      throw paramDds;
    }
  }
  
  public void Code(dds paramDds, cwv paramCwv, boolean paramBoolean)
  {
    if (paramCwv == null) {}
    for (;;)
    {
      try
      {
        localBnu = paramDds.n;
        paramCwv = Code(paramDds.S, paramCwv, localBnu, false, paramBoolean);
        paramDds.k = dsy.Code(paramCwv.V);
        paramDds.l = paramCwv.I;
        paramDds.Z = paramCwv.Z;
        paramDds.I = Code(paramCwv, localBnu);
        return;
      }
      finally {}
      bnu localBnu = paramCwv.V();
    }
  }
  
  public void Code(ddu paramDdu, String paramString, bnu paramBnu)
  {
    try
    {
      paramString = Z(paramString, paramBnu);
      paramDdu.k = dsy.Code(paramString.V);
      paramDdu.C = false;
      paramDdu.S = paramString.Z;
      paramDdu.Code(Code(paramString, paramBnu));
      return;
    }
    finally
    {
      paramDdu = finally;
      throw paramDdu;
    }
  }
  
  public void Code(ddx paramDdx, String paramString, bnu paramBnu)
  {
    try
    {
      paramString = B(paramString, paramBnu);
      paramDdx.k = dsy.Code(paramString.V);
      paramDdx.C = false;
      paramDdx.S = paramString.Z;
      paramDdx.Code(Code(paramString, paramBnu));
      return;
    }
    finally
    {
      paramDdx = finally;
      throw paramDdx;
    }
  }
  
  /* Error */
  public void Code(dez paramDez, bnu paramBnu)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +14 -> 17
    //   6: aload_1
    //   7: getfield 826	com/easy/cool/next/home/screen/dez:I	Ljava/lang/String;
    //   10: astore 5
    //   12: aload 5
    //   14: ifnonnull +6 -> 20
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: aload_1
    //   22: getfield 826	com/easy/cool/next/home/screen/dez:I	Ljava/lang/String;
    //   25: aload_2
    //   26: invokespecial 878	com/easy/cool/next/home/screen/cww:I	(Ljava/lang/String;Lcom/easy/cool/next/home/screen/bnu;)Ljava/util/List;
    //   29: astore 5
    //   31: aload 5
    //   33: invokeinterface 737 1 0
    //   38: ifne +39 -> 77
    //   41: aload_1
    //   42: aload 5
    //   44: iconst_0
    //   45: invokeinterface 881 2 0
    //   50: checkcast 12	com/easy/cool/next/home/screen/cww$S
    //   53: getfield 279	com/easy/cool/next/home/screen/cww$S:Code	Landroid/graphics/Bitmap;
    //   56: putfield 801	com/easy/cool/next/home/screen/dez:Code	Landroid/graphics/Bitmap;
    //   59: aload_1
    //   60: aload 5
    //   62: iconst_0
    //   63: invokeinterface 881 2 0
    //   68: checkcast 12	com/easy/cool/next/home/screen/cww$S
    //   71: getfield 326	com/easy/cool/next/home/screen/cww$S:V	Ljava/lang/CharSequence;
    //   74: putfield 834	com/easy/cool/next/home/screen/dez:k	Ljava/lang/CharSequence;
    //   77: aload_0
    //   78: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   81: aload_2
    //   82: invokevirtual 351	com/easy/cool/next/home/screen/bnv:Code	(Lcom/easy/cool/next/home/screen/bnu;)J
    //   85: lstore_3
    //   86: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   89: invokevirtual 404	com/easy/cool/next/home/screen/cwy:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   92: ldc_w 406
    //   95: ldc_w 817
    //   98: iconst_2
    //   99: anewarray 517	java/lang/String
    //   102: dup
    //   103: iconst_0
    //   104: new 143	java/lang/StringBuilder
    //   107: dup
    //   108: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   111: aload_1
    //   112: getfield 826	com/easy/cool/next/home/screen/dez:I	Ljava/lang/String;
    //   115: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: ldc_w 883
    //   121: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: aastore
    //   128: dup
    //   129: iconst_1
    //   130: lload_3
    //   131: invokestatic 515	java/lang/Long:toString	(J)Ljava/lang/String;
    //   134: aastore
    //   135: invokevirtual 570	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   138: pop
    //   139: goto -122 -> 17
    //   142: astore_1
    //   143: aload_1
    //   144: invokestatic 377	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   147: goto -130 -> 17
    //   150: astore_1
    //   151: aload_0
    //   152: monitorexit
    //   153: aload_1
    //   154: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	155	0	this	cww
    //   0	155	1	paramDez	dez
    //   0	155	2	paramBnu	bnu
    //   85	46	3	l	long
    //   10	51	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   86	139	142	android/database/sqlite/SQLiteException
    //   6	12	150	finally
    //   20	77	150	finally
    //   77	86	150	finally
    //   86	139	150	finally
    //   143	147	150	finally
  }
  
  public void Code(dfb paramDfb, ComponentName paramComponentName, cwv paramCwv, bnu paramBnu, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramComponentName = Code(paramComponentName, paramCwv, paramBnu, paramBoolean1, paramBoolean2);
      paramDfb.k = dsy.Code(paramComponentName.V);
      paramDfb.C = Code(paramComponentName.Code, paramBnu);
      paramDfb.S = paramComponentName.Z;
      paramDfb.Code(Code(paramComponentName, paramBnu));
      return;
    }
    finally
    {
      paramDfb = finally;
      throw paramDfb;
    }
  }
  
  /* Error */
  public void Code(dfb paramDfb, Intent paramIntent, bnu paramBnu, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: invokevirtual 765	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +32 -> 42
    //   13: aload_1
    //   14: aload_0
    //   15: aload_3
    //   16: invokevirtual 282	com/easy/cool/next/home/screen/cww:Code	(Lcom/easy/cool/next/home/screen/bnu;)Landroid/graphics/Bitmap;
    //   19: invokevirtual 890	com/easy/cool/next/home/screen/dfb:Code	(Landroid/graphics/Bitmap;)V
    //   22: aload_1
    //   23: ldc_w 392
    //   26: putfield 887	com/easy/cool/next/home/screen/dfb:k	Ljava/lang/CharSequence;
    //   29: aload_1
    //   30: iconst_1
    //   31: putfield 888	com/easy/cool/next/home/screen/dfb:C	Z
    //   34: aload_1
    //   35: iconst_0
    //   36: putfield 889	com/easy/cool/next/home/screen/dfb:S	Z
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: aload_1
    //   44: aload 5
    //   46: aload_0
    //   47: getfield 732	com/easy/cool/next/home/screen/cww:C	Lcom/easy/cool/next/home/screen/bno;
    //   50: aload_2
    //   51: aload_3
    //   52: invokevirtual 768	com/easy/cool/next/home/screen/bno:Code	(Landroid/content/Intent;Lcom/easy/cool/next/home/screen/bnu;)Lcom/easy/cool/next/home/screen/bnl;
    //   55: aload_3
    //   56: iconst_1
    //   57: iload 4
    //   59: invokevirtual 893	com/easy/cool/next/home/screen/cww:Code	(Lcom/easy/cool/next/home/screen/dfb;Landroid/content/ComponentName;Lcom/easy/cool/next/home/screen/cwv;Lcom/easy/cool/next/home/screen/bnu;ZZ)V
    //   62: goto -23 -> 39
    //   65: astore_1
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	this	cww
    //   0	70	1	paramDfb	dfb
    //   0	70	2	paramIntent	Intent
    //   0	70	3	paramBnu	bnu
    //   0	70	4	paramBoolean	boolean
    //   6	39	5	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	8	65	finally
    //   13	39	65	finally
    //   42	62	65	finally
  }
  
  /* Error */
  public void Code(String paramString, bnu paramBnu)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 896	com/easy/cool/next/home/screen/cww:V	(Ljava/lang/String;Lcom/easy/cool/next/home/screen/bnu;)Ljava/util/List;
    //   8: pop
    //   9: aload_0
    //   10: getfield 86	com/easy/cool/next/home/screen/cww:L	Landroid/content/pm/PackageManager;
    //   13: aload_1
    //   14: sipush 8192
    //   17: invokevirtual 340	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   20: astore 5
    //   22: aload_0
    //   23: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   26: aload_2
    //   27: invokevirtual 351	com/easy/cool/next/home/screen/bnv:Code	(Lcom/easy/cool/next/home/screen/bnu;)J
    //   30: lstore_3
    //   31: aload_0
    //   32: getfield 732	com/easy/cool/next/home/screen/cww:C	Lcom/easy/cool/next/home/screen/bno;
    //   35: aload_1
    //   36: aload_2
    //   37: invokevirtual 736	com/easy/cool/next/home/screen/bno:Code	(Ljava/lang/String;Lcom/easy/cool/next/home/screen/bnu;)Ljava/util/List;
    //   40: invokeinterface 494 1 0
    //   45: astore_1
    //   46: aload_1
    //   47: invokeinterface 499 1 0
    //   52: ifeq +28 -> 80
    //   55: aload_0
    //   56: aload_1
    //   57: invokeinterface 503 1 0
    //   62: checkcast 317	com/easy/cool/next/home/screen/cwv
    //   65: aload 5
    //   67: lload_3
    //   68: invokevirtual 898	com/easy/cool/next/home/screen/cww:Code	(Lcom/easy/cool/next/home/screen/cwv;Landroid/content/pm/PackageInfo;J)V
    //   71: goto -25 -> 46
    //   74: astore_1
    //   75: aload_0
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	cww
    //   0	83	1	paramString	String
    //   0	83	2	paramBnu	bnu
    //   30	38	3	l	long
    //   20	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   2	9	74	finally
    //   9	22	74	finally
    //   22	46	74	finally
    //   46	71	74	finally
    //   9	22	79	java/lang/Exception
  }
  
  public void Code(String paramString, bnu paramBnu, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      I(paramString, paramBnu);
      ddt localDdt = C(paramString, paramBnu);
      paramBnu = (S)this.a.get(localDdt);
      paramString = paramBnu;
      if (paramBnu == null)
      {
        paramString = new S();
        this.a.put(localDdt, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.V = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.Code = dsy.Code(paramBitmap, this.I);
      }
      return;
    }
    finally {}
  }
  
  public void Code(Set<String> paramSet, boolean paramBoolean)
  {
    if (Code(this.I) < 42)
    {
      dea.Code().L().Code(true);
      dea.Code().L().Code();
    }
    bnu localBnu;
    List localList;
    for (;;)
    {
      this.F.removeCallbacksAndMessages(Code);
      B();
      Iterator localIterator = this.B.V().iterator();
      if (localIterator.hasNext())
      {
        localBnu = (bnu)localIterator.next();
        localList = V(localBnu);
        if ((localList != null) && (!localList.isEmpty())) {
          break;
        }
      }
      return;
      dea.Code().L().Code(false);
    }
    if (bnu.Code().equals(localBnu)) {}
    for (Object localObject = paramSet;; localObject = Collections.emptySet())
    {
      Code(localBnu, localList, (Set)localObject, paramBoolean);
      break;
    }
  }
  
  public boolean Code(Bitmap paramBitmap, bnu paramBnu)
  {
    return this.D.get(paramBnu) == paramBitmap;
  }
  
  public dez I(String paramString)
  {
    paramString = new dez(paramString);
    Code(bnu.Code(), false, paramString);
    return paramString;
  }
  
  public CharSequence I(dds paramDds)
  {
    if (paramDds == null) {
      return null;
    }
    return V(paramDds.C());
  }
  
  public void I()
  {
    try
    {
      this.e = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Drawable V(dds paramDds)
  {
    if (paramDds == null) {
      return null;
    }
    return Code(paramDds.C());
  }
  
  public CharSequence V(String paramString)
  {
    return I(paramString).k;
  }
  
  /* Error */
  public List<S> V(String paramString, bnu paramBnu)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokespecial 878	com/easy/cool/next/home/screen/cww:I	(Ljava/lang/String;Lcom/easy/cool/next/home/screen/bnu;)Ljava/util/List;
    //   8: astore 5
    //   10: aload_0
    //   11: getfield 346	com/easy/cool/next/home/screen/cww:B	Lcom/easy/cool/next/home/screen/bnv;
    //   14: aload_2
    //   15: invokevirtual 351	com/easy/cool/next/home/screen/bnv:Code	(Lcom/easy/cool/next/home/screen/bnu;)J
    //   18: lstore_3
    //   19: getstatic 398	com/easy/cool/next/home/screen/cww:Z	Lcom/easy/cool/next/home/screen/cwy;
    //   22: invokevirtual 404	com/easy/cool/next/home/screen/cwy:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   25: ldc_w 406
    //   28: ldc_w 817
    //   31: iconst_2
    //   32: anewarray 517	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: new 143	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   44: aload_1
    //   45: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc_w 883
    //   51: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: aastore
    //   58: dup
    //   59: iconst_1
    //   60: lload_3
    //   61: invokestatic 515	java/lang/Long:toString	(J)Ljava/lang/String;
    //   64: aastore
    //   65: invokevirtual 570	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   68: pop
    //   69: aload_0
    //   70: monitorexit
    //   71: aload 5
    //   73: areturn
    //   74: astore_1
    //   75: aload_1
    //   76: invokestatic 377	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   79: goto -10 -> 69
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	cww
    //   0	87	1	paramString	String
    //   0	87	2	paramBnu	bnu
    //   18	43	3	l	long
    //   8	64	5	localList	List
    // Exception table:
    //   from	to	target	type
    //   19	69	74	android/database/sqlite/SQLiteException
    //   2	19	82	finally
    //   19	69	82	finally
    //   75	79	82	finally
  }
  
  public void V()
  {
    this.a.clear();
    Z.Code(Z.getWritableDatabase());
  }
  
  public void V(ComponentName paramComponentName, bnu paramBnu)
  {
    try
    {
      this.a.remove(new ddt(paramComponentName, paramBnu));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public dez Z(dds paramDds)
  {
    return I(paramDds.C());
  }
  
  static class S
  {
    public Bitmap Code;
    public CharSequence I = "";
    public CharSequence V = "";
    public boolean Z;
    
    S() {}
  }
  
  class T
    extends cww.yU
  {
    T(HashMap<String, PackageInfo> paramHashMap, Stack<cwv> paramStack1, Stack<cwv> paramStack2, boolean paramBoolean)
    {
      super(???, paramStack1, paramStack2, paramBoolean, bool1);
    }
    
    protected void B()
    {
      super.B();
      if ((!cww.Code(cww.this)) && (this.C))
      {
        dpz localDpz = dea.Code().a();
        String str = localDpz.Code;
        if (localDpz.V()) {
          bdf.Code("Theme_Set_Succeed", true, new String[] { "type", str });
        }
        ecw.ap().sendBroadcast(bts.B(str));
      }
    }
    
    void Code()
    {
      cww.this.F.postAtTime(this, cww.Code, SystemClock.uptimeMillis() + 1L);
    }
    
    public void run()
    {
      hk.Code("iconCacheUpdateOneSerialized");
      try
      {
        V();
        if (I()) {
          B();
        }
        return;
      }
      finally
      {
        hk.Code();
      }
    }
  }
  
  public static class Y
  {
    private final Runnable Code;
    private final Handler V;
    
    Y(Runnable paramRunnable, Handler paramHandler)
    {
      this.Code = paramRunnable;
      this.V = paramHandler;
    }
    
    public void Code()
    {
      this.V.removeCallbacks(this.Code);
    }
  }
  
  class l
    extends cww.yU
  {
    l(HashMap<String, PackageInfo> paramHashMap, Stack<cwv> paramStack1, Stack<cwv> paramStack2, boolean paramBoolean)
    {
      super(???, paramStack1, paramStack2, paramBoolean, bool1);
    }
    
    protected void B()
    {
      super.B();
      if (this.S) {}
      do
      {
        return;
        this.S = true;
      } while (!this.C);
      dpz localDpz = dea.Code().a();
      String str = localDpz.Code;
      if (localDpz.V()) {
        bdf.Code("Theme_Set_Succeed", true, new String[] { "type", str });
      }
      ecw.ap().sendBroadcast(bts.B(str));
    }
    
    void C()
    {
      int j = this.Z.size();
      int k = this.I.size();
      int i = 0;
      while (i < k + j)
      {
        Code();
        i += 1;
      }
    }
    
    void Code()
    {
      esa.Code(this);
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 96
      //   2: invokestatic 101	com/easy/cool/next/home/screen/hk:Code	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: invokevirtual 103	com/easy/cool/next/home/screen/cww$l:V	()V
      //   9: aload_0
      //   10: getfield 107	com/easy/cool/next/home/screen/cww$l:D	Ljava/lang/Object;
      //   13: astore_1
      //   14: aload_1
      //   15: monitorenter
      //   16: aload_0
      //   17: getfield 110	com/easy/cool/next/home/screen/cww$l:F	I
      //   20: ifne +7 -> 27
      //   23: aload_0
      //   24: invokevirtual 111	com/easy/cool/next/home/screen/cww$l:B	()V
      //   27: aload_1
      //   28: monitorexit
      //   29: invokestatic 112	com/easy/cool/next/home/screen/hk:Code	()V
      //   32: return
      //   33: astore_2
      //   34: aload_1
      //   35: monitorexit
      //   36: aload_2
      //   37: athrow
      //   38: astore_1
      //   39: invokestatic 112	com/easy/cool/next/home/screen/hk:Code	()V
      //   42: aload_1
      //   43: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	44	0	this	l
      //   38	5	1	localObject2	Object
      //   33	4	2	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   16	27	33	finally
      //   27	29	33	finally
      //   34	36	33	finally
      //   5	16	38	finally
      //   36	38	38	finally
    }
  }
  
  abstract class yU
    implements Runnable
  {
    protected final HashSet<String> B = new HashSet();
    protected boolean C;
    protected final long Code;
    protected final Object D = new Object();
    protected int F;
    protected final Stack<cwv> I;
    protected boolean S;
    protected final HashMap<String, PackageInfo> V;
    protected final Stack<cwv> Z;
    private ReentrantLock a = new ReentrantLock();
    
    yU(HashMap<String, PackageInfo> paramHashMap, Stack<cwv> paramStack1, Stack<cwv> paramStack2, boolean paramBoolean)
    {
      this.Code = ???;
      this.V = paramStack1;
      this.I = paramStack2;
      this.Z = paramBoolean;
      boolean bool1;
      this.C = bool1;
      if (paramStack2 == null)
      {
        i = j;
        this.F = i;
        return;
      }
      j = paramStack2.size();
      if (paramBoolean == null) {}
      for (;;)
      {
        i += j;
        break;
        i = paramBoolean.size();
      }
    }
    
    protected void B()
    {
      if (!this.B.isEmpty()) {
        dea.Code().Z().Code(this.B, cww.this.B.Code(this.Code));
      }
    }
    
    abstract void Code();
    
    final boolean I()
    {
      boolean bool = Z();
      if (!bool) {
        Code();
      }
      return bool;
    }
    
    protected void V()
    {
      ??? = null;
      Object localObject1 = null;
      if (!this.Z.isEmpty()) {}
      try
      {
        ??? = (cwv)this.Z.pop();
        localObject1 = ???;
      }
      catch (EmptyStackException localEmptyStackException2)
      {
        for (;;) {}
      }
      ??? = localObject1;
      if (localObject1 != null)
      {
        if (!(localObject1 instanceof ddu)) {
          break label132;
        }
        ??? = (ddu)localObject1;
        cww.Code(cww.this, ((ddu)???).V, ((ddu)???).V(), true);
        this.B.add(((cwv)localObject1).Code().getPackageName());
      }
      synchronized (this.D)
      {
        for (;;)
        {
          this.F -= 1;
          ??? = localObject1;
          if ((??? == null) && (!this.I.isEmpty())) {}
          try
          {
            localObject1 = (cwv)this.I.pop();
            ??? = localObject1;
          }
          catch (EmptyStackException localEmptyStackException1)
          {
            label132:
            Object localObject7;
            for (;;) {}
          }
          if (??? == null)
          {
            return;
            if ((localObject1 instanceof ddx))
            {
              ??? = (ddx)localObject1;
              cww.V(cww.this, ((ddx)???).Code, ((ddx)???).V(), true);
              this.B.add(((ddx)???).Code().getPackageName());
              continue;
            }
            ??? = ((cwv)localObject1).Code().flattenToString();
            localObject7 = cww.this.Code((cwv)localObject1, true);
            try
            {
              cwz.Z.getWritableDatabase().update("cache", (ContentValues)localObject7, "componentName = ? AND profileId = ?", new String[] { ???, Long.toString(this.Code) });
              this.B.add(((cwv)localObject1).Code().getPackageName());
            }
            catch (SQLiteException localSQLiteException)
            {
              ThrowableExtension.printStackTrace(localSQLiteException);
            }
          }
        }
      }
      if (((localSQLiteException instanceof ddu)) || ((localSQLiteException instanceof ddx))) {
        synchronized (this.D)
        {
          this.F -= 1;
          return;
        }
      }
      localObject7 = (PackageInfo)this.V.get(localCwv.Code().getPackageName());
      if (localObject7 != null) {}
      synchronized (cww.this)
      {
        cww.this.Code(localCwv, (PackageInfo)localObject7, this.Code);
        this.B.add(localCwv.Code().getPackageName());
        synchronized (this.D)
        {
          this.F -= 1;
          return;
        }
      }
    }
    
    final boolean Z()
    {
      return (this.Z.isEmpty()) && (this.I.isEmpty());
    }
  }
}
