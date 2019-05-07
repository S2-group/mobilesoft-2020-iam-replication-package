package com.emoji.face.sticker.home.screen;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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
import com.emoji.face.sticker.home.screen.desktop.BubbleTextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public final class cln
  extends clq
{
  static final Object Code = new Object();
  private final HashMap<bcj, Bitmap> D = new HashMap();
  private final PackageManager L;
  final dfw V = new dfw();
  private final HashMap<csi, aux> a = new HashMap(50);
  private final int b;
  private final int c;
  private final BitmapFactory.Options d;
  private Boolean e;
  private String f;
  private Bitmap g;
  private Canvas h;
  private Paint i;
  
  public cln(Context paramContext, bkn paramBkn)
  {
    super(paramContext, paramBkn);
    this.L = paramContext.getPackageManager();
    this.b = paramContext.getResources().getColor(2131820958);
    this.c = paramContext.getResources().getColor(2131820959);
    this.d = new BitmapFactory.Options();
    this.d.inPreferredConfig = Bitmap.Config.RGB_565;
    B();
  }
  
  private void B()
  {
    this.f = Locale.getDefault().toString();
  }
  
  private static int Code(Context paramContext)
  {
    dea localDea = csp.Code().D.Code;
    if ((localDea == null) || (localDea.I())) {
      return -1;
    }
    return localDea.Code(paramContext).Z("target_launcher_version_code");
  }
  
  private ContentValues Code(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", dgx.V(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.f);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", dgx.V(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
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
      localContentValues.put("icon_low_res", dgx.V(this.g));
      return localContentValues;
    }
    finally {}
  }
  
  private static Bitmap Code(Cursor paramCursor, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(0);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor) {}
    return null;
  }
  
  private Bitmap Code(aux paramAux, bcj paramBcj)
  {
    if (paramAux.Code == null) {
      return Code(paramBcj);
    }
    return paramAux.Code;
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
        return I();
      }
    }
    return null;
  }
  
  private aux Code(ComponentName paramComponentName, clm paramClm, bcj paramBcj, boolean paramBoolean1, boolean paramBoolean2)
  {
    csi localCsi = new csi(paramComponentName, paramBcj);
    Object localObject2 = (aux)this.a.get(localCsi);
    paramComponentName.toShortString();
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((aux)localObject2).Z)
      {
        localObject1 = localObject2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localObject1 = new aux();
      this.a.put(localCsi, localObject1);
      if (!Code(localCsi, (aux)localObject1, paramBoolean2))
      {
        if (paramClm == null) {
          break label234;
        }
        ((aux)localObject1).Code = dgx.Code(paramClm.Code(this.I, this.S), this.I);
      }
    }
    for (;;)
    {
      ContentValues localContentValues = Code(((aux)localObject1).Code, ((aux)localObject1).V.toString(), this.b);
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
          hbh.Code(localException);
        }
      }
      Code(localContentValues, localCsi.V, paramComponentName, this.B.Code(paramBcj));
      if ((TextUtils.isEmpty(((aux)localObject1).V)) && (paramClm != null))
      {
        ((aux)localObject1).V = paramClm.D_();
        ((aux)localObject1).I = this.B.Code(((aux)localObject1).V, paramBcj);
      }
      return localObject1;
      label234:
      if (paramBoolean1)
      {
        paramComponentName = I(paramComponentName.getPackageName(), paramBcj, false);
        if (paramComponentName != null)
        {
          ((aux)localObject1).Code = paramComponentName.Code;
          ((aux)localObject1).V = paramComponentName.V;
          ((aux)localObject1).I = paramComponentName.I;
        }
      }
      if (((aux)localObject1).Code == null) {
        ((aux)localObject1).Code = Code(paramBcj);
      }
    }
  }
  
  private aux Code(String paramString, bcj paramBcj, boolean paramBoolean)
  {
    csi localCsi = new csi(new ComponentName("feature", paramString), paramBcj);
    Object localObject2 = (aux)this.a.get(localCsi);
    Object localObject1;
    ContentValues localContentValues;
    if ((!paramBoolean) && (localObject2 != null))
    {
      localObject1 = localObject2;
      if (!((aux)localObject2).Z) {}
    }
    else
    {
      localObject1 = new aux();
      this.a.put(localCsi, localObject1);
      if ((paramBoolean) || (!Code(localCsi, (aux)localObject1, false)))
      {
        ((aux)localObject1).Code = csj.Code(this.I, paramString, paramBcj);
        ((aux)localObject1).V = csj.Code(this.I, paramString);
        ((aux)localObject1).I = "";
        if (((aux)localObject1).Code == null) {
          ((aux)localObject1).Code = Code(paramBcj);
        }
        localContentValues = Code(((aux)localObject1).Code, ((aux)localObject1).V.toString(), this.b);
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
        hbh.Code(localException);
      }
    }
    Code(localContentValues, localCsi.V, paramString, this.B.Code(paramBcj));
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
    paramContentValues = new clo(paramContentValues, paramComponentName);
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      this.F.post(paramContentValues);
      return;
    }
    paramContentValues.run();
  }
  
  /* Error */
  private void Code(bcj paramBcj, List<clm> paramList, Set<String> paramSet, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   4: aload_1
    //   5: invokevirtual 331	com/emoji/face/sticker/home/screen/bck:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)J
    //   8: lstore 13
    //   10: aload_0
    //   11: getfield 295	com/emoji/face/sticker/home/screen/cln:I	Landroid/content/Context;
    //   14: invokevirtual 84	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore 19
    //   19: new 65	java/util/HashMap
    //   22: dup
    //   23: invokespecial 66	java/util/HashMap:<init>	()V
    //   26: astore 18
    //   28: aload 19
    //   30: sipush 8192
    //   33: invokevirtual 468	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   36: astore 17
    //   38: aload 17
    //   40: invokeinterface 474 1 0
    //   45: astore 17
    //   47: aload 17
    //   49: invokeinterface 479 1 0
    //   54: ifeq +44 -> 98
    //   57: aload 17
    //   59: invokeinterface 483 1 0
    //   64: checkcast 415	android/content/pm/PackageInfo
    //   67: astore 19
    //   69: aload 18
    //   71: aload 19
    //   73: getfield 486	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   76: aload 19
    //   78: invokevirtual 289	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: pop
    //   82: goto -35 -> 47
    //   85: astore 17
    //   87: aload 19
    //   89: iconst_0
    //   90: invokevirtual 468	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   93: astore 17
    //   95: goto -57 -> 38
    //   98: new 65	java/util/HashMap
    //   101: dup
    //   102: invokespecial 66	java/util/HashMap:<init>	()V
    //   105: astore 19
    //   107: aload_2
    //   108: invokeinterface 474 1 0
    //   113: astore_2
    //   114: aload_2
    //   115: invokeinterface 479 1 0
    //   120: ifeq +32 -> 152
    //   123: aload_2
    //   124: invokeinterface 483 1 0
    //   129: checkcast 297	com/emoji/face/sticker/home/screen/clm
    //   132: astore 17
    //   134: aload 19
    //   136: aload 17
    //   138: invokeinterface 489 1 0
    //   143: aload 17
    //   145: invokevirtual 289	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   148: pop
    //   149: goto -35 -> 114
    //   152: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   155: invokevirtual 492	com/emoji/face/sticker/home/screen/clp:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   158: astore_2
    //   159: lload 13
    //   161: invokestatic 495	java/lang/Long:toString	(J)Ljava/lang/String;
    //   164: astore 17
    //   166: aload_2
    //   167: ldc_w 389
    //   170: iconst_5
    //   171: anewarray 497	java/lang/String
    //   174: dup
    //   175: iconst_0
    //   176: ldc_w 499
    //   179: aastore
    //   180: dup
    //   181: iconst_1
    //   182: ldc_w 397
    //   185: aastore
    //   186: dup
    //   187: iconst_2
    //   188: ldc_w 413
    //   191: aastore
    //   192: dup
    //   193: iconst_3
    //   194: ldc_w 421
    //   197: aastore
    //   198: dup
    //   199: iconst_4
    //   200: ldc -73
    //   202: aastore
    //   203: ldc_w 501
    //   206: iconst_1
    //   207: anewarray 497	java/lang/String
    //   210: dup
    //   211: iconst_0
    //   212: aload 17
    //   214: aastore
    //   215: aconst_null
    //   216: aconst_null
    //   217: aconst_null
    //   218: invokevirtual 505	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   221: astore_2
    //   222: aconst_null
    //   223: astore 17
    //   225: aload_2
    //   226: ifnonnull +27 -> 253
    //   229: new 507	java/lang/StringBuilder
    //   232: dup
    //   233: ldc_w 509
    //   236: invokespecial 512	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: aload 17
    //   241: invokevirtual 516	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: return
    //   246: astore 17
    //   248: aconst_null
    //   249: astore_2
    //   250: goto -25 -> 225
    //   253: aload_2
    //   254: ldc_w 397
    //   257: invokeinterface 519 2 0
    //   262: istore 7
    //   264: aload_2
    //   265: ldc_w 413
    //   268: invokeinterface 519 2 0
    //   273: istore 8
    //   275: aload_2
    //   276: ldc_w 421
    //   279: invokeinterface 519 2 0
    //   284: istore 9
    //   286: aload_2
    //   287: ldc_w 499
    //   290: invokeinterface 519 2 0
    //   295: istore 10
    //   297: aload_2
    //   298: ldc -73
    //   300: invokeinterface 519 2 0
    //   305: istore 11
    //   307: new 521	java/util/HashSet
    //   310: dup
    //   311: invokespecial 522	java/util/HashSet:<init>	()V
    //   314: astore 20
    //   316: new 524	java/util/Stack
    //   319: dup
    //   320: invokespecial 525	java/util/Stack:<init>	()V
    //   323: astore 17
    //   325: aload_2
    //   326: invokeinterface 528 1 0
    //   331: ifeq +465 -> 796
    //   334: aload_2
    //   335: iload 7
    //   337: invokeinterface 532 2 0
    //   342: astore 23
    //   344: aload 23
    //   346: invokestatic 536	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   349: astore 21
    //   351: aload 21
    //   353: ifnonnull +115 -> 468
    //   356: aload 20
    //   358: aload_2
    //   359: iload 10
    //   361: invokeinterface 539 2 0
    //   366: invokestatic 429	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   369: invokevirtual 543	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   372: pop
    //   373: goto -48 -> 325
    //   376: astore_1
    //   377: aload_1
    //   378: invokestatic 358	com/emoji/face/sticker/home/screen/hbh:Code	(Ljava/lang/Throwable;)V
    //   381: aload_2
    //   382: invokeinterface 546 1 0
    //   387: aload 20
    //   389: invokevirtual 548	java/util/HashSet:isEmpty	()Z
    //   392: ifne +25 -> 417
    //   395: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   398: invokevirtual 387	com/emoji/face/sticker/home/screen/clp:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   401: ldc_w 389
    //   404: ldc_w 499
    //   407: aload 20
    //   409: invokestatic 551	com/emoji/face/sticker/home/screen/dgx:Code	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   412: aconst_null
    //   413: invokevirtual 555	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   416: pop
    //   417: aload 19
    //   419: invokevirtual 556	java/util/HashMap:isEmpty	()Z
    //   422: ifne +391 -> 813
    //   425: aload 19
    //   427: invokevirtual 560	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   430: invokeinterface 563 1 0
    //   435: astore_1
    //   436: aload_1
    //   437: invokeinterface 479 1 0
    //   442: ifeq +371 -> 813
    //   445: aload 17
    //   447: aload_1
    //   448: invokeinterface 483 1 0
    //   453: checkcast 565	java/util/Map$Entry
    //   456: invokeinterface 568 1 0
    //   461: invokevirtual 569	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   464: pop
    //   465: goto -29 -> 436
    //   468: aload 18
    //   470: aload 21
    //   472: invokevirtual 350	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   475: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   478: checkcast 415	android/content/pm/PackageInfo
    //   481: astore 22
    //   483: aload 22
    //   485: ifnonnull +119 -> 604
    //   488: aload 23
    //   490: ldc_w 571
    //   493: invokevirtual 575	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   496: ifeq +67 -> 563
    //   499: aload 19
    //   501: aload 21
    //   503: invokevirtual 578	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   506: checkcast 297	com/emoji/face/sticker/home/screen/clm
    //   509: astore 22
    //   511: aload 22
    //   513: ifnonnull +39 -> 552
    //   516: aload_0
    //   517: aload 21
    //   519: aload_1
    //   520: invokevirtual 580	com/emoji/face/sticker/home/screen/cln:V	(Landroid/content/ComponentName;Lcom/emoji/face/sticker/home/screen/bcj;)V
    //   523: aload 20
    //   525: aload_2
    //   526: iload 10
    //   528: invokeinterface 539 2 0
    //   533: invokestatic 429	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   536: invokevirtual 543	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   539: pop
    //   540: goto -215 -> 325
    //   543: astore_1
    //   544: aload_2
    //   545: invokeinterface 546 1 0
    //   550: aload_1
    //   551: athrow
    //   552: aload 17
    //   554: aload 22
    //   556: invokevirtual 569	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   559: pop
    //   560: goto -235 -> 325
    //   563: aload_3
    //   564: aload 21
    //   566: invokevirtual 350	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   569: invokeinterface 583 2 0
    //   574: ifne -249 -> 325
    //   577: aload_0
    //   578: aload 21
    //   580: aload_1
    //   581: invokevirtual 580	com/emoji/face/sticker/home/screen/cln:V	(Landroid/content/ComponentName;Lcom/emoji/face/sticker/home/screen/bcj;)V
    //   584: aload 20
    //   586: aload_2
    //   587: iload 10
    //   589: invokeinterface 539 2 0
    //   594: invokestatic 429	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   597: invokevirtual 543	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   600: pop
    //   601: goto -276 -> 325
    //   604: aload 22
    //   606: getfield 587	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   609: getfield 592	android/content/pm/ApplicationInfo:flags	I
    //   612: ldc_w 593
    //   615: iand
    //   616: ifne -291 -> 325
    //   619: aload_2
    //   620: iload 8
    //   622: invokeinterface 597 2 0
    //   627: lstore 15
    //   629: aload_2
    //   630: iload 9
    //   632: invokeinterface 539 2 0
    //   637: istore 5
    //   639: aload 19
    //   641: aload 21
    //   643: invokevirtual 578	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   646: checkcast 297	com/emoji/face/sticker/home/screen/clm
    //   649: astore 23
    //   651: iload 5
    //   653: aload 22
    //   655: getfield 424	android/content/pm/PackageInfo:versionCode	I
    //   658: if_icmpne +30 -> 688
    //   661: lload 15
    //   663: aload 22
    //   665: getfield 419	android/content/pm/PackageInfo:lastUpdateTime	J
    //   668: lcmp
    //   669: ifne +19 -> 688
    //   672: aload_0
    //   673: getfield 131	com/emoji/face/sticker/home/screen/cln:f	Ljava/lang/String;
    //   676: aload_2
    //   677: iload 11
    //   679: invokeinterface 532 2 0
    //   684: invokestatic 601	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   687: pop
    //   688: aload 23
    //   690: ifnonnull +95 -> 785
    //   693: aload 21
    //   695: invokevirtual 350	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   698: astore 22
    //   700: aload 21
    //   702: invokevirtual 604	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   705: astore 23
    //   707: iconst_0
    //   708: istore 6
    //   710: aload 23
    //   712: ldc_w 606
    //   715: invokevirtual 609	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   718: istore 12
    //   720: iload 6
    //   722: istore 5
    //   724: iload 12
    //   726: iconst_m1
    //   727: if_icmpeq +26 -> 753
    //   730: iload 6
    //   732: istore 5
    //   734: aload 22
    //   736: aload 23
    //   738: iconst_0
    //   739: iload 12
    //   741: invokevirtual 613	java/lang/String:substring	(II)Ljava/lang/String;
    //   744: invokestatic 601	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   747: ifeq +6 -> 753
    //   750: iconst_1
    //   751: istore 5
    //   753: iload 5
    //   755: ifne -430 -> 325
    //   758: aload_0
    //   759: aload 21
    //   761: aload_1
    //   762: invokevirtual 580	com/emoji/face/sticker/home/screen/cln:V	(Landroid/content/ComponentName;Lcom/emoji/face/sticker/home/screen/bcj;)V
    //   765: aload 20
    //   767: aload_2
    //   768: iload 10
    //   770: invokeinterface 539 2 0
    //   775: invokestatic 429	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   778: invokevirtual 543	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   781: pop
    //   782: goto -457 -> 325
    //   785: aload 17
    //   787: aload 23
    //   789: invokevirtual 569	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   792: pop
    //   793: goto -468 -> 325
    //   796: aload_2
    //   797: invokeinterface 546 1 0
    //   802: goto -415 -> 387
    //   805: astore_1
    //   806: aload_1
    //   807: invokestatic 358	com/emoji/face/sticker/home/screen/hbh:Code	(Ljava/lang/Throwable;)V
    //   810: goto -393 -> 417
    //   813: aload 19
    //   815: invokevirtual 556	java/util/HashMap:isEmpty	()Z
    //   818: ifeq +11 -> 829
    //   821: aload 17
    //   823: invokevirtual 614	java/util/Stack:isEmpty	()Z
    //   826: ifne -581 -> 245
    //   829: new 524	java/util/Stack
    //   832: dup
    //   833: invokespecial 525	java/util/Stack:<init>	()V
    //   836: astore_1
    //   837: aload_1
    //   838: aload 19
    //   840: invokevirtual 618	java/util/HashMap:values	()Ljava/util/Collection;
    //   843: invokevirtual 622	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   846: pop
    //   847: iload 4
    //   849: ifeq +29 -> 878
    //   852: aload_0
    //   853: invokespecial 624	com/emoji/face/sticker/home/screen/cln:Z	()Z
    //   856: ifeq +22 -> 878
    //   859: new 24	com/emoji/face/sticker/home/screen/cln$prn
    //   862: dup
    //   863: aload_0
    //   864: lload 13
    //   866: aload 18
    //   868: aload_1
    //   869: aload 17
    //   871: invokespecial 627	com/emoji/face/sticker/home/screen/cln$prn:<init>	(Lcom/emoji/face/sticker/home/screen/cln;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   874: invokevirtual 628	com/emoji/face/sticker/home/screen/cln$prn:B	()V
    //   877: return
    //   878: new 15	com/emoji/face/sticker/home/screen/cln$com1
    //   881: dup
    //   882: aload_0
    //   883: lload 13
    //   885: aload 18
    //   887: aload_1
    //   888: aload 17
    //   890: iload 4
    //   892: invokespecial 631	com/emoji/face/sticker/home/screen/cln$com1:<init>	(Lcom/emoji/face/sticker/home/screen/cln;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;Z)V
    //   895: invokevirtual 632	com/emoji/face/sticker/home/screen/cln$com1:I	()Z
    //   898: pop
    //   899: return
    //   900: astore_1
    //   901: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	902	0	this	cln
    //   0	902	1	paramBcj	bcj
    //   0	902	2	paramList	List<clm>
    //   0	902	3	paramSet	Set<String>
    //   0	902	4	paramBoolean	boolean
    //   637	117	5	j	int
    //   708	23	6	k	int
    //   262	74	7	m	int
    //   273	348	8	n	int
    //   284	347	9	i1	int
    //   295	474	10	i2	int
    //   305	373	11	i3	int
    //   718	22	12	i4	int
    //   8	876	13	l1	long
    //   627	35	15	l2	long
    //   36	22	17	localObject1	Object
    //   85	1	17	localRuntimeException	RuntimeException
    //   93	147	17	localObject2	Object
    //   246	1	17	localSQLiteException	SQLiteException
    //   323	566	17	localStack	Stack
    //   26	860	18	localHashMap	HashMap
    //   17	822	19	localObject3	Object
    //   314	452	20	localHashSet	HashSet
    //   349	411	21	localComponentName	ComponentName
    //   481	254	22	localObject4	Object
    //   342	446	23	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   28	38	85	java/lang/RuntimeException
    //   152	222	246	android/database/sqlite/SQLiteException
    //   325	351	376	android/database/sqlite/SQLiteException
    //   356	373	376	android/database/sqlite/SQLiteException
    //   468	483	376	android/database/sqlite/SQLiteException
    //   488	511	376	android/database/sqlite/SQLiteException
    //   516	540	376	android/database/sqlite/SQLiteException
    //   552	560	376	android/database/sqlite/SQLiteException
    //   563	601	376	android/database/sqlite/SQLiteException
    //   604	688	376	android/database/sqlite/SQLiteException
    //   693	707	376	android/database/sqlite/SQLiteException
    //   710	720	376	android/database/sqlite/SQLiteException
    //   734	750	376	android/database/sqlite/SQLiteException
    //   758	782	376	android/database/sqlite/SQLiteException
    //   785	793	376	android/database/sqlite/SQLiteException
    //   325	351	543	finally
    //   356	373	543	finally
    //   377	381	543	finally
    //   468	483	543	finally
    //   488	511	543	finally
    //   516	540	543	finally
    //   552	560	543	finally
    //   563	601	543	finally
    //   604	688	543	finally
    //   693	707	543	finally
    //   710	720	543	finally
    //   734	750	543	finally
    //   758	782	543	finally
    //   785	793	543	finally
    //   395	417	805	android/database/sqlite/SQLiteException
    //   87	95	900	java/lang/RuntimeException
  }
  
  /* Error */
  private boolean Code(csi paramCsi, aux paramAux, boolean paramBoolean)
  {
    // Byte code:
    //   0: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   3: invokevirtual 492	com/emoji/face/sticker/home/screen/clp:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 5
    //   8: iload_3
    //   9: ifeq +83 -> 92
    //   12: ldc -71
    //   14: astore 4
    //   16: aload_1
    //   17: getfield 323	com/emoji/face/sticker/home/screen/csi:V	Landroid/content/ComponentName;
    //   20: invokevirtual 400	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   23: astore 6
    //   25: aload_0
    //   26: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   29: aload_1
    //   30: getfield 638	com/emoji/face/sticker/home/screen/csi:I	Lcom/emoji/face/sticker/home/screen/bcj;
    //   33: invokevirtual 331	com/emoji/face/sticker/home/screen/bck:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)J
    //   36: invokestatic 495	java/lang/Long:toString	(J)Ljava/lang/String;
    //   39: astore 7
    //   41: aload 5
    //   43: ldc_w 389
    //   46: iconst_2
    //   47: anewarray 497	java/lang/String
    //   50: dup
    //   51: iconst_0
    //   52: aload 4
    //   54: aastore
    //   55: dup
    //   56: iconst_1
    //   57: ldc -78
    //   59: aastore
    //   60: ldc_w 640
    //   63: iconst_2
    //   64: anewarray 497	java/lang/String
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
    //   80: invokevirtual 505	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   83: astore 5
    //   85: aload 5
    //   87: ifnonnull +15 -> 102
    //   90: iconst_0
    //   91: ireturn
    //   92: ldc -89
    //   94: astore 4
    //   96: goto -80 -> 16
    //   99: astore_1
    //   100: iconst_0
    //   101: ireturn
    //   102: aload 5
    //   104: invokeinterface 528 1 0
    //   109: ifeq +109 -> 218
    //   112: iload_3
    //   113: ifeq +67 -> 180
    //   116: aload_0
    //   117: getfield 107	com/emoji/face/sticker/home/screen/cln:d	Landroid/graphics/BitmapFactory$Options;
    //   120: astore 4
    //   122: aload_2
    //   123: aload 5
    //   125: aload 4
    //   127: invokestatic 642	com/emoji/face/sticker/home/screen/cln:Code	(Landroid/database/Cursor;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   130: putfield 252	com/emoji/face/sticker/home/screen/cln$aux:Code	Landroid/graphics/Bitmap;
    //   133: aload_2
    //   134: iload_3
    //   135: putfield 285	com/emoji/face/sticker/home/screen/cln$aux:Z	Z
    //   138: aload_2
    //   139: aload 5
    //   141: iconst_1
    //   142: invokeinterface 532 2 0
    //   147: putfield 306	com/emoji/face/sticker/home/screen/cln$aux:V	Ljava/lang/CharSequence;
    //   150: aload_2
    //   151: getfield 306	com/emoji/face/sticker/home/screen/cln$aux:V	Ljava/lang/CharSequence;
    //   154: ifnonnull +32 -> 186
    //   157: aload_2
    //   158: ldc_w 375
    //   161: putfield 306	com/emoji/face/sticker/home/screen/cln$aux:V	Ljava/lang/CharSequence;
    //   164: aload_2
    //   165: ldc_w 375
    //   168: putfield 349	com/emoji/face/sticker/home/screen/cln$aux:I	Ljava/lang/CharSequence;
    //   171: aload 5
    //   173: invokeinterface 546 1 0
    //   178: iconst_1
    //   179: ireturn
    //   180: aconst_null
    //   181: astore 4
    //   183: goto -61 -> 122
    //   186: aload_2
    //   187: aload_0
    //   188: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   191: aload_2
    //   192: getfield 306	com/emoji/face/sticker/home/screen/cln$aux:V	Ljava/lang/CharSequence;
    //   195: aload_1
    //   196: getfield 638	com/emoji/face/sticker/home/screen/csi:I	Lcom/emoji/face/sticker/home/screen/bcj;
    //   199: invokevirtual 347	com/emoji/face/sticker/home/screen/bck:Code	(Ljava/lang/CharSequence;Lcom/emoji/face/sticker/home/screen/bcj;)Ljava/lang/CharSequence;
    //   202: putfield 349	com/emoji/face/sticker/home/screen/cln$aux:I	Ljava/lang/CharSequence;
    //   205: goto -34 -> 171
    //   208: astore_1
    //   209: aload 5
    //   211: invokeinterface 546 1 0
    //   216: iconst_0
    //   217: ireturn
    //   218: aload 5
    //   220: invokeinterface 546 1 0
    //   225: iconst_0
    //   226: ireturn
    //   227: astore_1
    //   228: aload 5
    //   230: invokeinterface 546 1 0
    //   235: aload_1
    //   236: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	237	0	this	cln
    //   0	237	1	paramCsi	csi
    //   0	237	2	paramAux	aux
    //   0	237	3	paramBoolean	boolean
    //   14	168	4	localObject1	Object
    //   6	223	5	localObject2	Object
    //   23	47	6	str1	String
    //   39	36	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   0	8	99	android/database/sqlite/SQLiteException
    //   16	85	99	android/database/sqlite/SQLiteException
    //   102	112	208	android/database/sqlite/SQLiteException
    //   116	122	208	android/database/sqlite/SQLiteException
    //   122	171	208	android/database/sqlite/SQLiteException
    //   186	205	208	android/database/sqlite/SQLiteException
    //   102	112	227	finally
    //   116	122	227	finally
    //   122	171	227	finally
    //   186	205	227	finally
  }
  
  private Drawable I()
  {
    return Code(Resources.getSystem(), 17629184, false);
  }
  
  private aux I(String paramString, bcj paramBcj, boolean paramBoolean)
  {
    csi localCsi = Z(paramString, paramBcj);
    Object localObject2 = (aux)this.a.get(localCsi);
    Object localObject1;
    int j;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((aux)localObject2).Z)
      {
        localObject1 = localObject2;
        if (paramBoolean) {}
      }
    }
    else
    {
      localObject1 = new aux();
      if (Code(localCsi, (aux)localObject1, paramBoolean)) {
        break label259;
      }
      try
      {
        if (bcj.Code().equals(paramBcj))
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
        this.a.put(localCsi, localObject1);
      }
      return localObject1;
      j = 8192;
      break;
      label145:
      ((aux)localObject1).Code = dgx.Code(this.B.Code(((ApplicationInfo)localObject2).loadIcon(this.L), paramBcj), this.I);
      ((aux)localObject1).V = ((ApplicationInfo)localObject2).loadLabel(this.L);
      ((aux)localObject1).I = this.B.Code(((aux)localObject1).V, paramBcj);
      ((aux)localObject1).Z = false;
      Code(Code(((aux)localObject1).Code, ((aux)localObject1).V.toString(), this.c), localCsi.V, paramString, this.B.Code(paramBcj));
      j = 1;
      continue;
      label259:
      j = 1;
    }
  }
  
  private List<aux> I(String paramString, bcj paramBcj)
  {
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.a.keySet().iterator();
    while (localIterator.hasNext())
    {
      csi localCsi = (csi)localIterator.next();
      if ((localCsi.V.getPackageName().equals(paramString)) && (localCsi.I.equals(paramBcj))) {
        localHashSet.add(localCsi);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramBcj = (csi)paramString.next();
      localArrayList.add(this.a.remove(paramBcj));
    }
    return localArrayList;
  }
  
  private aux V(String paramString, bcj paramBcj, boolean paramBoolean)
  {
    csi localCsi = new csi(new ComponentName("game", paramString), paramBcj);
    aux localAux = (aux)this.a.get(localCsi);
    Context localContext;
    if ((!paramBoolean) && (localAux != null))
    {
      localObject = localAux;
      if (!localAux.Z) {}
    }
    else
    {
      localAux = new aux();
      this.a.put(localCsi, localAux);
      if ((paramBoolean) || (!Code(localCsi, localAux, false)))
      {
        localContext = this.I;
        localObject = csp.Code().D.Code;
        if (!((dea)localObject).V()) {
          break label331;
        }
      }
    }
    label331:
    for (Object localObject = ((dea)localObject).Code(localContext);; localObject = null)
    {
      Resources localResources = localContext.getResources();
      Bitmap localBitmap = uz.Code(paramString);
      if (localObject == null) {
        localObject = localBitmap;
      }
      for (;;)
      {
        localAux.Code = dgx.Code(new BitmapDrawable(localResources, (Bitmap)localObject), localContext);
        localAux.V = csm.Code(paramString);
        localAux.I = "";
        if (localAux.Code == null) {
          localAux.Code = Code(paramBcj);
        }
        localObject = Code(localAux.Code, localAux.V.toString(), this.b);
        try
        {
          paramString = this.L.getPackageInfo(this.I.getPackageName(), 0);
          Code((ContentValues)localObject, localCsi.V, paramString, this.B.Code(paramBcj));
          localObject = localAux;
          return localObject;
          new StringBuilder("No themed icon, getting resource for feature ").append(paramString).append(" from current package");
          if (localBitmap != null) {
            localObject = csp.Code().C.Code(localBitmap);
          } else {
            localObject = null;
          }
        }
        catch (Exception paramString)
        {
          for (;;)
          {
            hbh.Code(paramString);
            paramString = null;
          }
        }
      }
    }
  }
  
  /* Error */
  private List<aux> V(String paramString, bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokespecial 724	com/emoji/face/sticker/home/screen/cln:I	(Ljava/lang/String;Lcom/emoji/face/sticker/home/screen/bcj;)Ljava/util/List;
    //   8: astore 5
    //   10: aload_0
    //   11: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   14: aload_2
    //   15: invokevirtual 331	com/emoji/face/sticker/home/screen/bck:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)J
    //   18: lstore_3
    //   19: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   22: invokevirtual 387	com/emoji/face/sticker/home/screen/clp:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   25: ldc_w 389
    //   28: ldc_w 726
    //   31: iconst_2
    //   32: anewarray 497	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: new 507	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 727	java/lang/StringBuilder:<init>	()V
    //   44: aload_1
    //   45: invokevirtual 711	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc_w 729
    //   51: invokevirtual 711	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 730	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: aastore
    //   58: dup
    //   59: iconst_1
    //   60: lload_3
    //   61: invokestatic 495	java/lang/Long:toString	(J)Ljava/lang/String;
    //   64: aastore
    //   65: invokevirtual 555	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   68: pop
    //   69: aload_0
    //   70: monitorexit
    //   71: aload 5
    //   73: areturn
    //   74: astore_1
    //   75: aload_1
    //   76: invokestatic 358	com/emoji/face/sticker/home/screen/hbh:Code	(Ljava/lang/Throwable;)V
    //   79: goto -10 -> 69
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	cln
    //   0	87	1	paramString	String
    //   0	87	2	paramBcj	bcj
    //   18	43	3	l	long
    //   8	64	5	localList	List
    // Exception table:
    //   from	to	target	type
    //   19	69	74	android/database/sqlite/SQLiteException
    //   2	19	82	finally
    //   19	69	82	finally
    //   75	79	82	finally
  }
  
  private static csi Z(String paramString, bcj paramBcj)
  {
    return new csi(new ComponentName(paramString, paramString + "."), paramBcj);
  }
  
  private boolean Z()
  {
    label90:
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
            break label90;
            this.e = Boolean.valueOf(bool);
            new StringBuilder("Parallel icon update enabled: ").append(this.e);
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
  
  final ContentValues Code(clm paramClm, boolean paramBoolean)
  {
    Object localObject1 = new csi(paramClm.Code(), paramClm.V());
    Object localObject2;
    if (!paramBoolean)
    {
      localObject2 = (aux)this.a.get(localObject1);
      if ((localObject2 != null) && (!((aux)localObject2).Z))
      {
        localObject1 = localObject2;
        if (((aux)localObject2).Code != null) {
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
        localObject2 = new aux();
        ((aux)localObject2).Code = dgx.Code(paramClm.Code(this.I, this.S), this.I);
      }
      ((aux)localObject2).V = paramClm.D_();
      ((aux)localObject2).I = this.B.Code(((aux)localObject2).V, paramClm.V());
      this.a.put(new csi(paramClm.Code(), paramClm.V()), localObject2);
      return Code(((aux)localObject2).Code, ((aux)localObject2).V.toString(), this.b);
    }
  }
  
  /* Error */
  public final Bitmap Code(Intent paramIntent, bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 753	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +13 -> 21
    //   11: aload_0
    //   12: aload_2
    //   13: invokevirtual 255	com/emoji/face/sticker/home/screen/cln:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)Landroid/graphics/Bitmap;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: aload_3
    //   23: aload_0
    //   24: getfield 756	com/emoji/face/sticker/home/screen/cln:C	Lcom/emoji/face/sticker/home/screen/bcd;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 761	com/emoji/face/sticker/home/screen/bcd:Code	(Landroid/content/Intent;Lcom/emoji/face/sticker/home/screen/bcj;)Lcom/emoji/face/sticker/home/screen/bca;
    //   32: aload_2
    //   33: iconst_1
    //   34: iconst_0
    //   35: invokespecial 763	com/emoji/face/sticker/home/screen/cln:Code	(Landroid/content/ComponentName;Lcom/emoji/face/sticker/home/screen/clm;Lcom/emoji/face/sticker/home/screen/bcj;ZZ)Lcom/emoji/face/sticker/home/screen/cln$aux;
    //   38: getfield 252	com/emoji/face/sticker/home/screen/cln$aux:Code	Landroid/graphics/Bitmap;
    //   41: astore_1
    //   42: goto -25 -> 17
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	cln
    //   0	50	1	paramIntent	Intent
    //   0	50	2	paramBcj	bcj
    //   6	17	3	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   11	17	45	finally
    //   21	42	45	finally
  }
  
  /* Error */
  public final Bitmap Code(bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 68	com/emoji/face/sticker/home/screen/cln:D	Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual 766	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifne +28 -> 38
    //   13: aload_0
    //   14: getfield 68	com/emoji/face/sticker/home/screen/cln:D	Ljava/util/HashMap;
    //   17: astore_3
    //   18: aload_0
    //   19: invokespecial 268	com/emoji/face/sticker/home/screen/cln:I	()Landroid/graphics/drawable/Drawable;
    //   22: astore_2
    //   23: aload_2
    //   24: ifnonnull +30 -> 54
    //   27: invokestatic 771	com/emoji/face/sticker/home/screen/hsk:Code	()Landroid/graphics/Bitmap;
    //   30: astore_2
    //   31: aload_3
    //   32: aload_1
    //   33: aload_2
    //   34: invokevirtual 289	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   37: pop
    //   38: aload_0
    //   39: getfield 68	com/emoji/face/sticker/home/screen/cln:D	Ljava/util/HashMap;
    //   42: aload_1
    //   43: invokevirtual 278	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   46: checkcast 187	android/graphics/Bitmap
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: areturn
    //   54: aload_0
    //   55: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   58: aload_2
    //   59: aload_1
    //   60: invokevirtual 670	com/emoji/face/sticker/home/screen/bck:Code	(Landroid/graphics/drawable/Drawable;Lcom/emoji/face/sticker/home/screen/bcj;)Landroid/graphics/drawable/Drawable;
    //   63: astore 4
    //   65: aload 4
    //   67: invokevirtual 776	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
    //   70: iconst_1
    //   71: invokestatic 206	java/lang/Math:max	(II)I
    //   74: aload 4
    //   76: invokevirtual 779	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
    //   79: iconst_1
    //   80: invokestatic 206	java/lang/Math:max	(II)I
    //   83: getstatic 782	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   86: invokestatic 210	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   89: astore_2
    //   90: new 212	android/graphics/Canvas
    //   93: dup
    //   94: aload_2
    //   95: invokespecial 215	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   98: astore 5
    //   100: aload 4
    //   102: iconst_0
    //   103: iconst_0
    //   104: aload_2
    //   105: invokevirtual 191	android/graphics/Bitmap:getWidth	()I
    //   108: aload_2
    //   109: invokevirtual 194	android/graphics/Bitmap:getHeight	()I
    //   112: invokevirtual 785	android/graphics/drawable/Drawable:setBounds	(IIII)V
    //   115: aload 4
    //   117: aload 5
    //   119: invokevirtual 789	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   122: aload 5
    //   124: aconst_null
    //   125: invokevirtual 792	android/graphics/Canvas:setBitmap	(Landroid/graphics/Bitmap;)V
    //   128: goto -97 -> 31
    //   131: astore_1
    //   132: aload_0
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	cln
    //   0	136	1	paramBcj	bcj
    //   22	87	2	localObject	Object
    //   17	15	3	localHashMap	HashMap
    //   63	53	4	localDrawable	Drawable
    //   98	25	5	localCanvas	Canvas
    // Exception table:
    //   from	to	target	type
    //   2	23	131	finally
    //   27	31	131	finally
    //   31	38	131	finally
    //   38	50	131	finally
    //   54	128	131	finally
  }
  
  public final Drawable Code(ActivityInfo paramActivityInfo)
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
    return I();
  }
  
  public final Drawable Code(String paramString)
  {
    paramString = I(paramString);
    return new BitmapDrawable(this.I.getResources(), paramString.Code);
  }
  
  public final Drawable Code(String paramString, int paramInt)
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
    return I();
  }
  
  public final con Code(final BubbleTextView paramBubbleTextView, final cso paramCso)
  {
    paramBubbleTextView = new Runnable()
    {
      public final void run()
      {
        if ((paramCso instanceof csh)) {
          cln.this.Code((csh)paramCso, null, false);
        }
        for (;;)
        {
          cln.this.V.execute(new Runnable()
          {
            public final void run()
            {
              cln.2.this.V.Code(cln.2.this.Code);
            }
          });
          return;
          Object localObject;
          if ((paramCso instanceof cto))
          {
            cto localCto = (cto)paramCso;
            cln localCln = cln.this;
            if (localCto.y != null) {}
            for (localObject = localCto.y;; localObject = localCto.Z)
            {
              localCln.Code(localCto, (Intent)localObject, localCto.o);
              break;
            }
          }
          if ((paramCso instanceof ctm))
          {
            localObject = (ctm)paramCso;
            cln.this.Code(((ctm)localObject).o, false, (ctm)localObject);
          }
        }
      }
    };
    this.F.post(paramBubbleTextView);
    return new con(paramBubbleTextView, this.F);
  }
  
  public final void Code()
  {
    this.a.clear();
    Z.Code(Z.getWritableDatabase());
  }
  
  /* Error */
  public final void Code(ComponentName paramComponentName, bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 580	com/emoji/face/sticker/home/screen/cln:V	(Landroid/content/ComponentName;Lcom/emoji/face/sticker/home/screen/bcj;)V
    //   8: aload_0
    //   9: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   12: aload_2
    //   13: invokevirtual 331	com/emoji/face/sticker/home/screen/bck:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)J
    //   16: lstore_3
    //   17: aload_1
    //   18: invokevirtual 604	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   21: invokestatic 340	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifeq +59 -> 83
    //   27: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   30: invokevirtual 387	com/emoji/face/sticker/home/screen/clp:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: ldc_w 389
    //   36: ldc_w 726
    //   39: iconst_2
    //   40: anewarray 497	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: new 507	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 727	java/lang/StringBuilder:<init>	()V
    //   52: aload_1
    //   53: invokevirtual 400	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   56: invokevirtual 711	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: ldc_w 829
    //   62: invokevirtual 711	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 730	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: lload_3
    //   72: invokestatic 495	java/lang/Long:toString	(J)Ljava/lang/String;
    //   75: aastore
    //   76: invokevirtual 555	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   79: pop
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   86: invokevirtual 387	com/emoji/face/sticker/home/screen/clp:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   89: ldc_w 389
    //   92: ldc_w 640
    //   95: iconst_2
    //   96: anewarray 497	java/lang/String
    //   99: dup
    //   100: iconst_0
    //   101: aload_1
    //   102: invokevirtual 400	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   105: aastore
    //   106: dup
    //   107: iconst_1
    //   108: lload_3
    //   109: invokestatic 495	java/lang/Long:toString	(J)Ljava/lang/String;
    //   112: aastore
    //   113: invokevirtual 555	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   116: pop
    //   117: goto -37 -> 80
    //   120: astore_1
    //   121: aload_1
    //   122: invokestatic 358	com/emoji/face/sticker/home/screen/hbh:Code	(Ljava/lang/Throwable;)V
    //   125: goto -45 -> 80
    //   128: astore_1
    //   129: aload_0
    //   130: monitorexit
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	cln
    //   0	133	1	paramComponentName	ComponentName
    //   0	133	2	paramBcj	bcj
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
  
  public final void Code(final ComponentName paramComponentName, final bcj paramBcj, final Runnable paramRunnable)
  {
    this.F.post(new Runnable()
    {
      public final void run()
      {
        cln.this.Code(paramComponentName, paramBcj);
        if (paramRunnable != null)
        {
          if (this.Z) {
            htd.I(paramRunnable);
          }
        }
        else {
          return;
        }
        paramRunnable.run();
      }
    });
  }
  
  public final void Code(bcj paramBcj, boolean paramBoolean, ctm paramCtm)
  {
    try
    {
      aux localAux = I(paramCtm.I, paramBcj, paramBoolean);
      paramCtm.Code = Code(localAux, paramBcj);
      paramCtm.l = dgx.Code(localAux.V);
      paramCtm.V = localAux.Z;
      paramCtm.m = localAux.I;
      return;
    }
    finally
    {
      paramBcj = finally;
      throw paramBcj;
    }
  }
  
  final void Code(clm paramClm, PackageInfo paramPackageInfo, long paramLong)
  {
    Code(Code(paramClm, false), paramClm.Code(), paramPackageInfo, paramLong);
  }
  
  public final void Code(csh paramCsh)
  {
    try
    {
      aux localAux = Code(paramCsh.F, null, paramCsh.o, false, paramCsh.B);
      if ((localAux.Code != null) && (!Code(localAux.Code, paramCsh.o)))
      {
        paramCsh.l = dgx.Code(localAux.V);
        paramCsh.Z = localAux.Code;
        paramCsh.m = localAux.I;
        paramCsh.B = localAux.Z;
      }
      return;
    }
    finally
    {
      paramCsh = finally;
      throw paramCsh;
    }
  }
  
  public final void Code(csh paramCsh, clm paramClm, boolean paramBoolean)
  {
    if (paramClm == null) {}
    for (;;)
    {
      try
      {
        localBcj = paramCsh.o;
        paramClm = Code(paramCsh.F, paramClm, localBcj, false, paramBoolean);
        paramCsh.l = dgx.Code(paramClm.V);
        paramCsh.m = paramClm.I;
        paramCsh.B = paramClm.Z;
        paramCsh.Z = Code(paramClm, localBcj);
        return;
      }
      finally {}
      bcj localBcj = paramClm.V();
    }
  }
  
  public final void Code(csj paramCsj, String paramString, bcj paramBcj)
  {
    try
    {
      paramString = Code(paramString, paramBcj, false);
      paramCsj.l = dgx.Code(paramString.V);
      paramCsj.C = false;
      paramCsj.S = paramString.Z;
      paramCsj.D = Code(paramString, paramBcj);
      return;
    }
    finally
    {
      paramCsj = finally;
      throw paramCsj;
    }
  }
  
  public final void Code(csm paramCsm, String paramString, bcj paramBcj)
  {
    try
    {
      paramString = V(paramString, paramBcj, false);
      paramCsm.l = dgx.Code(paramString.V);
      paramCsm.C = false;
      paramCsm.S = paramString.Z;
      paramCsm.D = Code(paramString, paramBcj);
      return;
    }
    finally
    {
      paramCsm = finally;
      throw paramCsm;
    }
  }
  
  /* Error */
  public final void Code(ctm paramCtm, bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield 836	com/emoji/face/sticker/home/screen/ctm:I	Ljava/lang/String;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: aload_1
    //   18: getfield 836	com/emoji/face/sticker/home/screen/ctm:I	Ljava/lang/String;
    //   21: aload_2
    //   22: invokespecial 724	com/emoji/face/sticker/home/screen/cln:I	(Ljava/lang/String;Lcom/emoji/face/sticker/home/screen/bcj;)Ljava/util/List;
    //   25: astore 5
    //   27: aload 5
    //   29: invokeinterface 886 1 0
    //   34: ifne +39 -> 73
    //   37: aload_1
    //   38: aload 5
    //   40: iconst_0
    //   41: invokeinterface 889 2 0
    //   46: checkcast 12	com/emoji/face/sticker/home/screen/cln$aux
    //   49: getfield 252	com/emoji/face/sticker/home/screen/cln$aux:Code	Landroid/graphics/Bitmap;
    //   52: putfield 810	com/emoji/face/sticker/home/screen/ctm:Code	Landroid/graphics/Bitmap;
    //   55: aload_1
    //   56: aload 5
    //   58: iconst_0
    //   59: invokeinterface 889 2 0
    //   64: checkcast 12	com/emoji/face/sticker/home/screen/cln$aux
    //   67: getfield 306	com/emoji/face/sticker/home/screen/cln$aux:V	Ljava/lang/CharSequence;
    //   70: putfield 844	com/emoji/face/sticker/home/screen/ctm:l	Ljava/lang/CharSequence;
    //   73: aload_0
    //   74: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   77: aload_2
    //   78: invokevirtual 331	com/emoji/face/sticker/home/screen/bck:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)J
    //   81: lstore_3
    //   82: getstatic 381	com/emoji/face/sticker/home/screen/cln:Z	Lcom/emoji/face/sticker/home/screen/clp;
    //   85: invokevirtual 387	com/emoji/face/sticker/home/screen/clp:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   88: ldc_w 389
    //   91: ldc_w 726
    //   94: iconst_2
    //   95: anewarray 497	java/lang/String
    //   98: dup
    //   99: iconst_0
    //   100: new 507	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 727	java/lang/StringBuilder:<init>	()V
    //   107: aload_1
    //   108: getfield 836	com/emoji/face/sticker/home/screen/ctm:I	Ljava/lang/String;
    //   111: invokevirtual 711	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: ldc_w 729
    //   117: invokevirtual 711	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 730	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: aastore
    //   124: dup
    //   125: iconst_1
    //   126: lload_3
    //   127: invokestatic 495	java/lang/Long:toString	(J)Ljava/lang/String;
    //   130: aastore
    //   131: invokevirtual 555	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   134: pop
    //   135: goto -122 -> 13
    //   138: astore_1
    //   139: aload_1
    //   140: invokestatic 358	com/emoji/face/sticker/home/screen/hbh:Code	(Ljava/lang/Throwable;)V
    //   143: goto -130 -> 13
    //   146: astore_1
    //   147: aload_0
    //   148: monitorexit
    //   149: aload_1
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	cln
    //   0	151	1	paramCtm	ctm
    //   0	151	2	paramBcj	bcj
    //   81	46	3	l	long
    //   6	51	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   82	135	138	android/database/sqlite/SQLiteException
    //   2	8	146	finally
    //   16	73	146	finally
    //   73	82	146	finally
    //   82	135	146	finally
    //   139	143	146	finally
  }
  
  public final void Code(cto paramCto, ComponentName paramComponentName, clm paramClm, bcj paramBcj, boolean paramBoolean)
  {
    try
    {
      paramComponentName = Code(paramComponentName, paramClm, paramBcj, paramBoolean, false);
      paramCto.l = dgx.Code(paramComponentName.V);
      paramCto.C = Code(paramComponentName.Code, paramBcj);
      paramCto.S = paramComponentName.Z;
      paramCto.D = Code(paramComponentName, paramBcj);
      return;
    }
    finally
    {
      paramCto = finally;
      throw paramCto;
    }
  }
  
  /* Error */
  public final void Code(cto paramCto, Intent paramIntent, bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: invokevirtual 753	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnonnull +32 -> 42
    //   13: aload_1
    //   14: aload_0
    //   15: aload_3
    //   16: invokevirtual 255	com/emoji/face/sticker/home/screen/cln:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)Landroid/graphics/Bitmap;
    //   19: putfield 880	com/emoji/face/sticker/home/screen/cto:D	Landroid/graphics/Bitmap;
    //   22: aload_1
    //   23: ldc_w 375
    //   26: putfield 891	com/emoji/face/sticker/home/screen/cto:l	Ljava/lang/CharSequence;
    //   29: aload_1
    //   30: iconst_1
    //   31: putfield 892	com/emoji/face/sticker/home/screen/cto:C	Z
    //   34: aload_1
    //   35: iconst_0
    //   36: putfield 893	com/emoji/face/sticker/home/screen/cto:S	Z
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: aload_1
    //   44: aload 4
    //   46: aload_0
    //   47: getfield 756	com/emoji/face/sticker/home/screen/cln:C	Lcom/emoji/face/sticker/home/screen/bcd;
    //   50: aload_2
    //   51: aload_3
    //   52: invokevirtual 761	com/emoji/face/sticker/home/screen/bcd:Code	(Landroid/content/Intent;Lcom/emoji/face/sticker/home/screen/bcj;)Lcom/emoji/face/sticker/home/screen/bca;
    //   55: aload_3
    //   56: iconst_1
    //   57: invokevirtual 896	com/emoji/face/sticker/home/screen/cln:Code	(Lcom/emoji/face/sticker/home/screen/cto;Landroid/content/ComponentName;Lcom/emoji/face/sticker/home/screen/clm;Lcom/emoji/face/sticker/home/screen/bcj;Z)V
    //   60: goto -21 -> 39
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	cln
    //   0	68	1	paramCto	cto
    //   0	68	2	paramIntent	Intent
    //   0	68	3	paramBcj	bcj
    //   6	39	4	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	8	63	finally
    //   13	39	63	finally
    //   42	60	63	finally
  }
  
  /* Error */
  public final void Code(String paramString, bcj paramBcj)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokespecial 899	com/emoji/face/sticker/home/screen/cln:V	(Ljava/lang/String;Lcom/emoji/face/sticker/home/screen/bcj;)Ljava/util/List;
    //   8: pop
    //   9: aload_0
    //   10: getfield 86	com/emoji/face/sticker/home/screen/cln:L	Landroid/content/pm/PackageManager;
    //   13: aload_1
    //   14: sipush 8192
    //   17: invokevirtual 320	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   20: astore 5
    //   22: aload_0
    //   23: getfield 326	com/emoji/face/sticker/home/screen/cln:B	Lcom/emoji/face/sticker/home/screen/bck;
    //   26: aload_2
    //   27: invokevirtual 331	com/emoji/face/sticker/home/screen/bck:Code	(Lcom/emoji/face/sticker/home/screen/bcj;)J
    //   30: lstore_3
    //   31: aload_0
    //   32: getfield 756	com/emoji/face/sticker/home/screen/cln:C	Lcom/emoji/face/sticker/home/screen/bcd;
    //   35: aload_1
    //   36: aload_2
    //   37: invokevirtual 901	com/emoji/face/sticker/home/screen/bcd:Code	(Ljava/lang/String;Lcom/emoji/face/sticker/home/screen/bcj;)Ljava/util/List;
    //   40: invokeinterface 474 1 0
    //   45: astore_1
    //   46: aload_1
    //   47: invokeinterface 479 1 0
    //   52: ifeq +28 -> 80
    //   55: aload_0
    //   56: aload_1
    //   57: invokeinterface 483 1 0
    //   62: checkcast 297	com/emoji/face/sticker/home/screen/clm
    //   65: aload 5
    //   67: lload_3
    //   68: invokevirtual 903	com/emoji/face/sticker/home/screen/cln:Code	(Lcom/emoji/face/sticker/home/screen/clm;Landroid/content/pm/PackageInfo;J)V
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
    //   0	83	0	this	cln
    //   0	83	1	paramString	String
    //   0	83	2	paramBcj	bcj
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
  
  public final void Code(String paramString, bcj paramBcj, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      I(paramString, paramBcj);
      csi localCsi = Z(paramString, paramBcj);
      paramBcj = (aux)this.a.get(localCsi);
      paramString = paramBcj;
      if (paramBcj == null)
      {
        paramString = new aux();
        this.a.put(localCsi, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.V = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.Code = dgx.Code(paramBitmap, this.I);
      }
      return;
    }
    finally {}
  }
  
  public final void Code(Set<String> paramSet, boolean paramBoolean)
  {
    Object localObject2;
    Object localObject3;
    if (Code(this.I) < 42)
    {
      csp.Code().C.Code = true;
      localObject2 = csp.Code().C;
      localObject3 = ((dfq)localObject2).V;
    }
    ArrayList localArrayList;
    for (;;)
    {
      synchronized (((dfq.con)localObject3).I)
      {
        ((dfq.con)localObject3).Code = null;
        ((dfq.con)localObject3).V.clear();
        try
        {
          hez.H().unbindService((ServiceConnection)localObject3);
          ((dfq)localObject2).V.Code(hez.H());
          this.F.removeCallbacksAndMessages(Code);
          B();
          localObject2 = this.B.V().iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localObject3 = (bcj)((Iterator)localObject2).next();
            localArrayList = new ArrayList();
            ??? = this.C.Code(null, (bcj)localObject3);
            if ((??? != null) && (!((List)???).isEmpty())) {
              localArrayList.addAll((Collection)???);
            }
            ??? = csj.L();
            if (!((List)???).isEmpty()) {
              localArrayList.addAll((Collection)???);
            }
            if (!localArrayList.isEmpty()) {
              break;
            }
          }
          return;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          hbh.Code(localIllegalArgumentException);
          continue;
        }
      }
      csp.Code().C.Code = false;
    }
    if (bcj.Code().equals(localIllegalArgumentException)) {}
    for (??? = paramSet;; ??? = Collections.emptySet())
    {
      Code(localIllegalArgumentException, localArrayList, (Set)???, paramBoolean);
      break;
    }
  }
  
  public final boolean Code(Bitmap paramBitmap, bcj paramBcj)
  {
    return this.D.get(paramBcj) == paramBitmap;
  }
  
  public final ctm I(String paramString)
  {
    paramString = new ctm(paramString);
    Code(bcj.Code(), false, paramString);
    return paramString;
  }
  
  public final CharSequence I(csh paramCsh)
  {
    if (paramCsh == null) {
      return null;
    }
    return V(paramCsh.Z());
  }
  
  public final Drawable V(csh paramCsh)
  {
    if (paramCsh == null) {
      return null;
    }
    return Code(paramCsh.Z());
  }
  
  public final CharSequence V(String paramString)
  {
    return I(paramString).l;
  }
  
  public final void V()
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
  
  public final void V(ComponentName paramComponentName, bcj paramBcj)
  {
    try
    {
      this.a.remove(new csi(paramComponentName, paramBcj));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  static final class aux
  {
    public Bitmap Code;
    public CharSequence I = "";
    public CharSequence V = "";
    public boolean Z;
    
    aux() {}
  }
  
  final class com1
    extends cln.nul
  {
    com1(HashMap<String, PackageInfo> paramHashMap, Stack<clm> paramStack1, Stack<clm> paramStack2, boolean paramBoolean)
    {
      super(???, paramStack1, paramStack2, paramBoolean, bool1);
    }
    
    final void Code()
    {
      cln.this.F.postAtTime(this, cln.Code, SystemClock.uptimeMillis() + 1L);
    }
    
    protected final void Z()
    {
      super.Z();
      if ((!cln.Code(cln.this)) && (this.C))
      {
        dea localDea = csp.Code().D.Code;
        String str = localDea.Code;
        if (localDea.V()) {
          ase.Code("Theme_Set_Succeed", true, new String[] { "type", str });
        }
        hez.H().sendBroadcast(bie.B(str));
      }
    }
    
    public final void run()
    {
      gv.Code("iconCacheUpdateOneSerialized");
      try
      {
        V();
        if (I()) {
          Z();
        }
        return;
      }
      finally
      {
        gv.Code();
      }
    }
  }
  
  public static final class con
  {
    public final Runnable Code;
    public final Handler V;
    
    con(Runnable paramRunnable, Handler paramHandler)
    {
      this.Code = paramRunnable;
      this.V = paramHandler;
    }
  }
  
  abstract class nul
    implements Runnable
  {
    protected final HashSet<String> B = new HashSet();
    protected boolean C;
    protected final long Code;
    protected final Object D = new Object();
    protected int F;
    protected final Stack<clm> I;
    protected boolean S;
    protected final HashMap<String, PackageInfo> V;
    protected final Stack<clm> Z;
    private ReentrantLock a = new ReentrantLock();
    
    nul(HashMap<String, PackageInfo> paramHashMap, Stack<clm> paramStack1, Stack<clm> paramStack2, boolean paramBoolean)
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
    
    abstract void Code();
    
    final boolean I()
    {
      if ((this.Z.isEmpty()) && (this.I.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        if (!bool) {
          Code();
        }
        return bool;
      }
    }
    
    protected final void V()
    {
      ??? = null;
      Object localObject1 = null;
      if (!this.Z.isEmpty()) {}
      try
      {
        ??? = (clm)this.Z.pop();
        localObject1 = ???;
      }
      catch (EmptyStackException localEmptyStackException2)
      {
        for (;;) {}
      }
      ??? = localObject1;
      if (localObject1 != null)
      {
        if (!(localObject1 instanceof csj)) {
          break label130;
        }
        ??? = (csj)localObject1;
        cln.Code(cln.this, ((csj)???).V, bcj.Code());
        this.B.add(((clm)localObject1).Code().getPackageName());
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
            localObject1 = (clm)this.I.pop();
            ??? = localObject1;
          }
          catch (EmptyStackException localEmptyStackException1)
          {
            label130:
            Object localObject7;
            for (;;) {}
          }
          if (??? == null)
          {
            return;
            if ((localObject1 instanceof csm))
            {
              ??? = (csm)localObject1;
              cln.V(cln.this, ((csm)???).Code, bcj.Code());
              this.B.add(((csm)???).F().getPackageName());
              continue;
            }
            ??? = ((clm)localObject1).Code().flattenToString();
            localObject7 = cln.this.Code((clm)localObject1, true);
            try
            {
              clq.Z.getWritableDatabase().update("cache", (ContentValues)localObject7, "componentName = ? AND profileId = ?", new String[] { ???, Long.toString(this.Code) });
              this.B.add(((clm)localObject1).Code().getPackageName());
            }
            catch (SQLiteException localSQLiteException)
            {
              hbh.Code(localSQLiteException);
            }
          }
        }
      }
      if (((localSQLiteException instanceof csj)) || ((localSQLiteException instanceof csm))) {
        synchronized (this.D)
        {
          this.F -= 1;
          return;
        }
      }
      localObject7 = (PackageInfo)this.V.get(localClm.Code().getPackageName());
      if (localObject7 != null) {}
      synchronized (cln.this)
      {
        cln.this.Code(localClm, (PackageInfo)localObject7, this.Code);
        this.B.add(localClm.Code().getPackageName());
        synchronized (this.D)
        {
          this.F -= 1;
          return;
        }
      }
    }
    
    protected void Z()
    {
      if (!this.B.isEmpty())
      {
        cst localCst1 = csp.Code().Code;
        HashSet localHashSet = this.B;
        bcj localBcj = cln.this.B.Code(this.Code);
        cst.nul localNul = localCst1.L();
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        for (;;)
        {
          Object localObject2;
          synchronized (cst.i)
          {
            Iterator localIterator = cst.k.iterator();
            if (!localIterator.hasNext()) {
              break;
            }
            localObject2 = (cso)localIterator.next();
            if ((localObject2 instanceof csj))
            {
              ((csj)localObject2).Code(localCst1.s);
              localArrayList2.add((csj)localObject2);
            }
          }
          if ((localObject2 instanceof csm))
          {
            ((csm)localObject2).Code(localCst2.s);
            localArrayList2.add((csm)localObject2);
          }
          else if (((localObject2 instanceof cto)) && (localBcj.equals(((cso)localObject2).o)) && ((((cso)localObject2).a == 0) || (((cso)localObject2).a == 1)))
          {
            localObject2 = (cto)localObject2;
            ComponentName localComponentName = ((cto)localObject2).F();
            if ((localComponentName != null) && (localHashSet.contains(localComponentName.getPackageName())))
            {
              ((cto)localObject2).Code(localCst2.s);
              localArrayList2.add(localObject2);
            }
          }
        }
        localCst2.f.Code(localHashSet, localBcj, localArrayList1);
        if (!localArrayList2.isEmpty()) {
          localCst2.Z.post(new cst.15(localCst2, localNul, localArrayList2, localBcj));
        }
        if (!localArrayList1.isEmpty()) {
          localCst2.Z.post(new cst.16(localCst2, localNul, localArrayList1));
        }
        localCst2.Code(localNul, false);
      }
    }
  }
  
  final class prn
    extends cln.nul
  {
    prn(HashMap<String, PackageInfo> paramHashMap, Stack<clm> paramStack1, Stack<clm> paramStack2, boolean paramBoolean)
    {
      super(???, paramStack1, paramStack2, paramBoolean, true);
    }
    
    final void B()
    {
      int j = this.Z.size();
      int k = this.I.size();
      int i = 0;
      while (i < k + j)
      {
        htd.Code(this);
        i += 1;
      }
    }
    
    final void Code()
    {
      htd.Code(this);
    }
    
    protected final void Z()
    {
      super.Z();
      if (this.S) {}
      do
      {
        return;
        this.S = true;
      } while (!this.C);
      dea localDea = csp.Code().D.Code;
      String str = localDea.Code;
      if (localDea.V()) {
        ase.Code("Theme_Set_Succeed", true, new String[] { "type", str });
      }
      hez.H().sendBroadcast(bie.B(str));
    }
    
    /* Error */
    public final void run()
    {
      // Byte code:
      //   0: ldc 101
      //   2: invokestatic 106	com/emoji/face/sticker/home/screen/gv:Code	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: invokevirtual 108	com/emoji/face/sticker/home/screen/cln$prn:V	()V
      //   9: aload_0
      //   10: getfield 111	com/emoji/face/sticker/home/screen/cln$prn:D	Ljava/lang/Object;
      //   13: astore_1
      //   14: aload_1
      //   15: monitorenter
      //   16: aload_0
      //   17: getfield 114	com/emoji/face/sticker/home/screen/cln$prn:F	I
      //   20: ifne +7 -> 27
      //   23: aload_0
      //   24: invokevirtual 115	com/emoji/face/sticker/home/screen/cln$prn:Z	()V
      //   27: aload_1
      //   28: monitorexit
      //   29: invokestatic 117	com/emoji/face/sticker/home/screen/gv:Code	()V
      //   32: return
      //   33: astore_2
      //   34: aload_1
      //   35: monitorexit
      //   36: aload_2
      //   37: athrow
      //   38: astore_1
      //   39: invokestatic 117	com/emoji/face/sticker/home/screen/gv:Code	()V
      //   42: aload_1
      //   43: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	44	0	this	prn
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
}
