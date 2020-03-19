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
import com.callerscreen.color.phone.ringtone.flash.desktop.BubbleTextView;
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

public final class cds
  extends cdv
{
  static final Object a = new Object();
  final cxl b = new cxl();
  private final HashMap<axi, Bitmap> i = new HashMap();
  private final PackageManager j;
  private final HashMap<ckk, cds.a> k = new HashMap(50);
  private final int l;
  private final int m;
  private final BitmapFactory.Options n;
  private Boolean o;
  private String p;
  private Bitmap q;
  private Canvas r;
  private Paint s;
  
  public cds(Context paramContext, bga paramBga)
  {
    super(paramContext, paramBga);
    this.j = paramContext.getPackageManager();
    this.l = paramContext.getResources().getColor(2131755377);
    this.m = paramContext.getResources().getColor(2131755378);
    this.n = new BitmapFactory.Options();
    this.n.inPreferredConfig = Bitmap.Config.RGB_565;
    e();
  }
  
  private static int a(Context paramContext)
  {
    cvp localCvp = ckr.a().i.a;
    if ((localCvp == null) || (localCvp.c())) {
      return -1;
    }
    return localCvp.a(paramContext).d("target_launcher_version_code");
  }
  
  private ContentValues a(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", cyk.b(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.p);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", cyk.b(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.q == null)
      {
        this.q = Bitmap.createBitmap(Math.max(paramBitmap.getWidth() / 5, 1), Math.max(paramBitmap.getHeight() / 5, 1), Bitmap.Config.RGB_565);
        this.r = new Canvas(this.q);
        this.s = new Paint(3);
      }
      this.r.drawColor(paramInt);
      this.r.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.q.getWidth(), this.q.getHeight()), this.s);
      localContentValues.put("icon_low_res", cyk.b(this.q));
      return localContentValues;
    }
    finally {}
  }
  
  private static Bitmap a(Cursor paramCursor, BitmapFactory.Options paramOptions)
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
  
  private Bitmap a(cds.a paramA, axi paramAxi)
  {
    if (paramA.a == null) {
      return a(paramAxi);
    }
    return paramA.a;
  }
  
  private Drawable a(Resources paramResources, int paramInt, boolean paramBoolean)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.g);
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
        return c();
      }
    }
    return null;
  }
  
  private cds.a a(ComponentName paramComponentName, cdr paramCdr, axi paramAxi, boolean paramBoolean1, boolean paramBoolean2)
  {
    ckk localCkk = new ckk(paramComponentName, paramAxi);
    Object localObject2 = (cds.a)this.k.get(localCkk);
    paramComponentName.toShortString();
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((cds.a)localObject2).d)
      {
        localObject1 = localObject2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localObject1 = new cds.a();
      this.k.put(localCkk, localObject1);
      if (!a(localCkk, (cds.a)localObject1, paramBoolean2))
      {
        if (paramCdr == null) {
          break label234;
        }
        ((cds.a)localObject1).a = cyk.a(paramCdr.a(this.c, this.g), this.c);
      }
    }
    for (;;)
    {
      ContentValues localContentValues = a(((cds.a)localObject1).a, ((cds.a)localObject1).b.toString(), this.l);
      paramComponentName = null;
      try
      {
        localObject2 = this.j.getPackageInfo(this.c.getPackageName(), 0);
        paramComponentName = (ComponentName)localObject2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      a(localContentValues, localCkk.b, paramComponentName, this.e.a(paramAxi));
      if ((TextUtils.isEmpty(((cds.a)localObject1).b)) && (paramCdr != null))
      {
        ((cds.a)localObject1).b = paramCdr.y_();
        ((cds.a)localObject1).c = this.e.a(((cds.a)localObject1).b, paramAxi);
      }
      return localObject1;
      label234:
      if (paramBoolean1)
      {
        paramComponentName = c(paramComponentName.getPackageName(), paramAxi, false);
        if (paramComponentName != null)
        {
          ((cds.a)localObject1).a = paramComponentName.a;
          ((cds.a)localObject1).b = paramComponentName.b;
          ((cds.a)localObject1).c = paramComponentName.c;
        }
      }
      if (((cds.a)localObject1).a == null) {
        ((cds.a)localObject1).a = a(paramAxi);
      }
    }
  }
  
  private cds.a a(String paramString, axi paramAxi, boolean paramBoolean)
  {
    ckk localCkk = new ckk(new ComponentName("feature", paramString), paramAxi);
    Object localObject2 = (cds.a)this.k.get(localCkk);
    Object localObject1;
    ContentValues localContentValues;
    if ((!paramBoolean) && (localObject2 != null))
    {
      localObject1 = localObject2;
      if (!((cds.a)localObject2).d) {}
    }
    else
    {
      localObject1 = new cds.a();
      this.k.put(localCkk, localObject1);
      if ((paramBoolean) || (!a(localCkk, (cds.a)localObject1, false)))
      {
        ((cds.a)localObject1).a = ckl.a(this.c, paramString, paramAxi);
        ((cds.a)localObject1).b = ckl.a(this.c, paramString);
        ((cds.a)localObject1).c = "";
        if (((cds.a)localObject1).a == null) {
          ((cds.a)localObject1).a = a(paramAxi);
        }
        localContentValues = a(((cds.a)localObject1).a, ((cds.a)localObject1).b.toString(), this.l);
        paramString = null;
      }
    }
    try
    {
      localObject2 = this.j.getPackageInfo(this.c.getPackageName(), 0);
      paramString = (String)localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    a(localContentValues, localCkk.b, paramString, this.e.a(paramAxi));
    return localObject1;
  }
  
  private void a(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    if (paramPackageInfo != null)
    {
      paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
      paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    }
    paramContentValues = cdt.a(paramContentValues, paramComponentName);
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      this.h.post(paramContentValues);
      return;
    }
    paramContentValues.run();
  }
  
  /* Error */
  private void a(axi paramAxi, List<cdr> paramList, Set<String> paramSet, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 317	cds:e	Laxj;
    //   4: aload_1
    //   5: invokevirtual 322	axj:a	(Laxi;)J
    //   8: lstore 13
    //   10: aload_0
    //   11: getfield 284	cds:c	Landroid/content/Context;
    //   14: invokevirtual 83	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore 19
    //   19: new 64	java/util/HashMap
    //   22: dup
    //   23: invokespecial 65	java/util/HashMap:<init>	()V
    //   26: astore 18
    //   28: aload 19
    //   30: sipush 8192
    //   33: invokevirtual 457	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   36: astore 17
    //   38: aload 17
    //   40: invokeinterface 463 1 0
    //   45: astore 17
    //   47: aload 17
    //   49: invokeinterface 468 1 0
    //   54: ifeq +44 -> 98
    //   57: aload 17
    //   59: invokeinterface 472 1 0
    //   64: checkcast 404	android/content/pm/PackageInfo
    //   67: astore 19
    //   69: aload 18
    //   71: aload 19
    //   73: getfield 475	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   76: aload 19
    //   78: invokevirtual 278	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: pop
    //   82: goto -35 -> 47
    //   85: astore 17
    //   87: aload 19
    //   89: iconst_0
    //   90: invokevirtual 457	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   93: astore 17
    //   95: goto -57 -> 38
    //   98: new 64	java/util/HashMap
    //   101: dup
    //   102: invokespecial 65	java/util/HashMap:<init>	()V
    //   105: astore 19
    //   107: aload_2
    //   108: invokeinterface 463 1 0
    //   113: astore_2
    //   114: aload_2
    //   115: invokeinterface 468 1 0
    //   120: ifeq +32 -> 152
    //   123: aload_2
    //   124: invokeinterface 472 1 0
    //   129: checkcast 286	cdr
    //   132: astore 17
    //   134: aload 19
    //   136: aload 17
    //   138: invokeinterface 478 1 0
    //   143: aload 17
    //   145: invokevirtual 278	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   148: pop
    //   149: goto -35 -> 114
    //   152: getstatic 370	cds:d	Lcdu;
    //   155: invokevirtual 481	cdu:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   158: astore_2
    //   159: lload 13
    //   161: invokestatic 484	java/lang/Long:toString	(J)Ljava/lang/String;
    //   164: astore 17
    //   166: aload_2
    //   167: ldc_w 378
    //   170: iconst_5
    //   171: anewarray 486	java/lang/String
    //   174: dup
    //   175: iconst_0
    //   176: ldc_w 488
    //   179: aastore
    //   180: dup
    //   181: iconst_1
    //   182: ldc_w 386
    //   185: aastore
    //   186: dup
    //   187: iconst_2
    //   188: ldc_w 402
    //   191: aastore
    //   192: dup
    //   193: iconst_3
    //   194: ldc_w 410
    //   197: aastore
    //   198: dup
    //   199: iconst_4
    //   200: ldc -88
    //   202: aastore
    //   203: ldc_w 490
    //   206: iconst_1
    //   207: anewarray 486	java/lang/String
    //   210: dup
    //   211: iconst_0
    //   212: aload 17
    //   214: aastore
    //   215: aconst_null
    //   216: aconst_null
    //   217: aconst_null
    //   218: invokevirtual 494	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   221: astore_2
    //   222: aconst_null
    //   223: astore 17
    //   225: aload_2
    //   226: ifnonnull +27 -> 253
    //   229: new 496	java/lang/StringBuilder
    //   232: dup
    //   233: ldc_w 498
    //   236: invokespecial 501	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: aload 17
    //   241: invokevirtual 505	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: return
    //   246: astore 17
    //   248: aconst_null
    //   249: astore_2
    //   250: goto -25 -> 225
    //   253: aload_2
    //   254: ldc_w 386
    //   257: invokeinterface 508 2 0
    //   262: istore 7
    //   264: aload_2
    //   265: ldc_w 402
    //   268: invokeinterface 508 2 0
    //   273: istore 8
    //   275: aload_2
    //   276: ldc_w 410
    //   279: invokeinterface 508 2 0
    //   284: istore 9
    //   286: aload_2
    //   287: ldc_w 488
    //   290: invokeinterface 508 2 0
    //   295: istore 10
    //   297: aload_2
    //   298: ldc -88
    //   300: invokeinterface 508 2 0
    //   305: istore 11
    //   307: new 510	java/util/HashSet
    //   310: dup
    //   311: invokespecial 511	java/util/HashSet:<init>	()V
    //   314: astore 20
    //   316: new 513	java/util/Stack
    //   319: dup
    //   320: invokespecial 514	java/util/Stack:<init>	()V
    //   323: astore 17
    //   325: aload_2
    //   326: invokeinterface 517 1 0
    //   331: ifeq +465 -> 796
    //   334: aload_2
    //   335: iload 7
    //   337: invokeinterface 521 2 0
    //   342: astore 23
    //   344: aload 23
    //   346: invokestatic 525	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   349: astore 21
    //   351: aload 21
    //   353: ifnonnull +115 -> 468
    //   356: aload 20
    //   358: aload_2
    //   359: iload 10
    //   361: invokeinterface 528 2 0
    //   366: invokestatic 418	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   369: invokevirtual 532	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   372: pop
    //   373: goto -48 -> 325
    //   376: astore_1
    //   377: aload_1
    //   378: invokevirtual 533	android/database/sqlite/SQLiteException:printStackTrace	()V
    //   381: aload_2
    //   382: invokeinterface 536 1 0
    //   387: aload 20
    //   389: invokevirtual 538	java/util/HashSet:isEmpty	()Z
    //   392: ifne +25 -> 417
    //   395: getstatic 370	cds:d	Lcdu;
    //   398: invokevirtual 376	cdu:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   401: ldc_w 378
    //   404: ldc_w 488
    //   407: aload 20
    //   409: invokestatic 541	cyk:a	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   412: aconst_null
    //   413: invokevirtual 545	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   416: pop
    //   417: aload 19
    //   419: invokevirtual 546	java/util/HashMap:isEmpty	()Z
    //   422: ifne +391 -> 813
    //   425: aload 19
    //   427: invokevirtual 550	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   430: invokeinterface 553 1 0
    //   435: astore_1
    //   436: aload_1
    //   437: invokeinterface 468 1 0
    //   442: ifeq +371 -> 813
    //   445: aload 17
    //   447: aload_1
    //   448: invokeinterface 472 1 0
    //   453: checkcast 555	java/util/Map$Entry
    //   456: invokeinterface 558 1 0
    //   461: invokevirtual 559	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   464: pop
    //   465: goto -29 -> 436
    //   468: aload 18
    //   470: aload 21
    //   472: invokevirtual 341	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   475: invokevirtual 265	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   478: checkcast 404	android/content/pm/PackageInfo
    //   481: astore 22
    //   483: aload 22
    //   485: ifnonnull +119 -> 604
    //   488: aload 23
    //   490: ldc_w 561
    //   493: invokevirtual 565	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   496: ifeq +67 -> 563
    //   499: aload 19
    //   501: aload 21
    //   503: invokevirtual 568	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   506: checkcast 286	cdr
    //   509: astore 22
    //   511: aload 22
    //   513: ifnonnull +39 -> 552
    //   516: aload_0
    //   517: aload 21
    //   519: aload_1
    //   520: invokevirtual 570	cds:b	(Landroid/content/ComponentName;Laxi;)V
    //   523: aload 20
    //   525: aload_2
    //   526: iload 10
    //   528: invokeinterface 528 2 0
    //   533: invokestatic 418	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   536: invokevirtual 532	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   539: pop
    //   540: goto -215 -> 325
    //   543: astore_1
    //   544: aload_2
    //   545: invokeinterface 536 1 0
    //   550: aload_1
    //   551: athrow
    //   552: aload 17
    //   554: aload 22
    //   556: invokevirtual 559	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   559: pop
    //   560: goto -235 -> 325
    //   563: aload_3
    //   564: aload 21
    //   566: invokevirtual 341	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   569: invokeinterface 573 2 0
    //   574: ifne -249 -> 325
    //   577: aload_0
    //   578: aload 21
    //   580: aload_1
    //   581: invokevirtual 570	cds:b	(Landroid/content/ComponentName;Laxi;)V
    //   584: aload 20
    //   586: aload_2
    //   587: iload 10
    //   589: invokeinterface 528 2 0
    //   594: invokestatic 418	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   597: invokevirtual 532	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   600: pop
    //   601: goto -276 -> 325
    //   604: aload 22
    //   606: getfield 577	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   609: getfield 582	android/content/pm/ApplicationInfo:flags	I
    //   612: ldc_w 583
    //   615: iand
    //   616: ifne -291 -> 325
    //   619: aload_2
    //   620: iload 8
    //   622: invokeinterface 587 2 0
    //   627: lstore 15
    //   629: aload_2
    //   630: iload 9
    //   632: invokeinterface 528 2 0
    //   637: istore 5
    //   639: aload 19
    //   641: aload 21
    //   643: invokevirtual 568	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   646: checkcast 286	cdr
    //   649: astore 23
    //   651: iload 5
    //   653: aload 22
    //   655: getfield 413	android/content/pm/PackageInfo:versionCode	I
    //   658: if_icmpne +30 -> 688
    //   661: lload 15
    //   663: aload 22
    //   665: getfield 408	android/content/pm/PackageInfo:lastUpdateTime	J
    //   668: lcmp
    //   669: ifne +19 -> 688
    //   672: aload_0
    //   673: getfield 170	cds:p	Ljava/lang/String;
    //   676: aload_2
    //   677: iload 11
    //   679: invokeinterface 521 2 0
    //   684: invokestatic 591	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   687: pop
    //   688: aload 23
    //   690: ifnonnull +95 -> 785
    //   693: aload 21
    //   695: invokevirtual 341	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   698: astore 22
    //   700: aload 21
    //   702: invokevirtual 594	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   705: astore 23
    //   707: iconst_0
    //   708: istore 6
    //   710: aload 23
    //   712: ldc_w 596
    //   715: invokevirtual 599	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
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
    //   741: invokevirtual 603	java/lang/String:substring	(II)Ljava/lang/String;
    //   744: invokestatic 591	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   747: ifeq +6 -> 753
    //   750: iconst_1
    //   751: istore 5
    //   753: iload 5
    //   755: ifne -430 -> 325
    //   758: aload_0
    //   759: aload 21
    //   761: aload_1
    //   762: invokevirtual 570	cds:b	(Landroid/content/ComponentName;Laxi;)V
    //   765: aload 20
    //   767: aload_2
    //   768: iload 10
    //   770: invokeinterface 528 2 0
    //   775: invokestatic 418	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   778: invokevirtual 532	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   781: pop
    //   782: goto -457 -> 325
    //   785: aload 17
    //   787: aload 23
    //   789: invokevirtual 559	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   792: pop
    //   793: goto -468 -> 325
    //   796: aload_2
    //   797: invokeinterface 536 1 0
    //   802: goto -415 -> 387
    //   805: astore_1
    //   806: aload_1
    //   807: invokevirtual 533	android/database/sqlite/SQLiteException:printStackTrace	()V
    //   810: goto -393 -> 417
    //   813: aload 19
    //   815: invokevirtual 546	java/util/HashMap:isEmpty	()Z
    //   818: ifeq +11 -> 829
    //   821: aload 17
    //   823: invokevirtual 604	java/util/Stack:isEmpty	()Z
    //   826: ifne -581 -> 245
    //   829: new 513	java/util/Stack
    //   832: dup
    //   833: invokespecial 514	java/util/Stack:<init>	()V
    //   836: astore_1
    //   837: aload_1
    //   838: aload 19
    //   840: invokevirtual 608	java/util/HashMap:values	()Ljava/util/Collection;
    //   843: invokevirtual 612	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   846: pop
    //   847: iload 4
    //   849: ifeq +29 -> 878
    //   852: aload_0
    //   853: invokespecial 614	cds:d	()Z
    //   856: ifeq +22 -> 878
    //   859: new 21	cds$d
    //   862: dup
    //   863: aload_0
    //   864: lload 13
    //   866: aload 18
    //   868: aload_1
    //   869: aload 17
    //   871: invokespecial 617	cds$d:<init>	(Lcds;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   874: invokevirtual 618	cds$d:e	()V
    //   877: return
    //   878: new 24	cds$e
    //   881: dup
    //   882: aload_0
    //   883: lload 13
    //   885: aload 18
    //   887: aload_1
    //   888: aload 17
    //   890: iload 4
    //   892: invokespecial 621	cds$e:<init>	(Lcds;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;Z)V
    //   895: invokevirtual 622	cds$e:c	()Z
    //   898: pop
    //   899: return
    //   900: astore_1
    //   901: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	902	0	this	cds
    //   0	902	1	paramAxi	axi
    //   0	902	2	paramList	List<cdr>
    //   0	902	3	paramSet	Set<String>
    //   0	902	4	paramBoolean	boolean
    //   637	117	5	i1	int
    //   708	23	6	i2	int
    //   262	74	7	i3	int
    //   273	348	8	i4	int
    //   284	347	9	i5	int
    //   295	474	10	i6	int
    //   305	373	11	i7	int
    //   718	22	12	i8	int
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
  private boolean a(ckk paramCkk, cds.a paramA, boolean paramBoolean)
  {
    // Byte code:
    //   0: getstatic 370	cds:d	Lcdu;
    //   3: invokevirtual 481	cdu:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 5
    //   8: iload_3
    //   9: ifeq +83 -> 92
    //   12: ldc -84
    //   14: astore 4
    //   16: aload_1
    //   17: getfield 314	ckk:b	Landroid/content/ComponentName;
    //   20: invokevirtual 389	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   23: astore 6
    //   25: aload_0
    //   26: getfield 317	cds:e	Laxj;
    //   29: aload_1
    //   30: getfield 628	ckk:c	Laxi;
    //   33: invokevirtual 322	axj:a	(Laxi;)J
    //   36: invokestatic 484	java/lang/Long:toString	(J)Ljava/lang/String;
    //   39: astore 7
    //   41: aload 5
    //   43: ldc_w 378
    //   46: iconst_2
    //   47: anewarray 486	java/lang/String
    //   50: dup
    //   51: iconst_0
    //   52: aload 4
    //   54: aastore
    //   55: dup
    //   56: iconst_1
    //   57: ldc -93
    //   59: aastore
    //   60: ldc_w 630
    //   63: iconst_2
    //   64: anewarray 486	java/lang/String
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
    //   80: invokevirtual 494	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   83: astore 5
    //   85: aload 5
    //   87: ifnonnull +15 -> 102
    //   90: iconst_0
    //   91: ireturn
    //   92: ldc -104
    //   94: astore 4
    //   96: goto -80 -> 16
    //   99: astore_1
    //   100: iconst_0
    //   101: ireturn
    //   102: aload 5
    //   104: invokeinterface 517 1 0
    //   109: ifeq +109 -> 218
    //   112: iload_3
    //   113: ifeq +67 -> 180
    //   116: aload_0
    //   117: getfield 106	cds:n	Landroid/graphics/BitmapFactory$Options;
    //   120: astore 4
    //   122: aload_2
    //   123: aload 5
    //   125: aload 4
    //   127: invokestatic 632	cds:a	(Landroid/database/Cursor;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   130: putfield 239	cds$a:a	Landroid/graphics/Bitmap;
    //   133: aload_2
    //   134: iload_3
    //   135: putfield 274	cds$a:d	Z
    //   138: aload_2
    //   139: aload 5
    //   141: iconst_1
    //   142: invokeinterface 521 2 0
    //   147: putfield 295	cds$a:b	Ljava/lang/CharSequence;
    //   150: aload_2
    //   151: getfield 295	cds$a:b	Ljava/lang/CharSequence;
    //   154: ifnonnull +32 -> 186
    //   157: aload_2
    //   158: ldc_w 364
    //   161: putfield 295	cds$a:b	Ljava/lang/CharSequence;
    //   164: aload_2
    //   165: ldc_w 364
    //   168: putfield 340	cds$a:c	Ljava/lang/CharSequence;
    //   171: aload 5
    //   173: invokeinterface 536 1 0
    //   178: iconst_1
    //   179: ireturn
    //   180: aconst_null
    //   181: astore 4
    //   183: goto -61 -> 122
    //   186: aload_2
    //   187: aload_0
    //   188: getfield 317	cds:e	Laxj;
    //   191: aload_2
    //   192: getfield 295	cds$a:b	Ljava/lang/CharSequence;
    //   195: aload_1
    //   196: getfield 628	ckk:c	Laxi;
    //   199: invokevirtual 338	axj:a	(Ljava/lang/CharSequence;Laxi;)Ljava/lang/CharSequence;
    //   202: putfield 340	cds$a:c	Ljava/lang/CharSequence;
    //   205: goto -34 -> 171
    //   208: astore_1
    //   209: aload 5
    //   211: invokeinterface 536 1 0
    //   216: iconst_0
    //   217: ireturn
    //   218: aload 5
    //   220: invokeinterface 536 1 0
    //   225: iconst_0
    //   226: ireturn
    //   227: astore_1
    //   228: aload 5
    //   230: invokeinterface 536 1 0
    //   235: aload_1
    //   236: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	237	0	this	cds
    //   0	237	1	paramCkk	ckk
    //   0	237	2	paramA	cds.a
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
  
  private cds.a b(String paramString, axi paramAxi, boolean paramBoolean)
  {
    ckk localCkk = new ckk(new ComponentName("game", paramString), paramAxi);
    cds.a localA = (cds.a)this.k.get(localCkk);
    Context localContext;
    if ((!paramBoolean) && (localA != null))
    {
      localObject = localA;
      if (!localA.d) {}
    }
    else
    {
      localA = new cds.a();
      this.k.put(localCkk, localA);
      if ((paramBoolean) || (!a(localCkk, localA, false)))
      {
        localContext = this.c;
        localObject = ckr.a().i.a;
        if (!((cvp)localObject).b()) {
          break label331;
        }
      }
    }
    label331:
    for (Object localObject = ((cvp)localObject).a(localContext);; localObject = null)
    {
      Resources localResources = localContext.getResources();
      Bitmap localBitmap = vn.a(paramString);
      if (localObject == null) {
        localObject = localBitmap;
      }
      for (;;)
      {
        localA.a = cyk.a(new BitmapDrawable(localResources, (Bitmap)localObject), localContext);
        localA.b = cko.a(paramString);
        localA.c = "";
        if (localA.a == null) {
          localA.a = a(paramAxi);
        }
        localObject = a(localA.a, localA.b.toString(), this.l);
        try
        {
          paramString = this.j.getPackageInfo(this.c.getPackageName(), 0);
          a((ContentValues)localObject, localCkk.b, paramString, this.e.a(paramAxi));
          localObject = localA;
          return localObject;
          new StringBuilder("No themed icon, getting resource for feature ").append(paramString).append(" from current package");
          if (localBitmap != null) {
            localObject = ckr.a().f.a(localBitmap);
          } else {
            localObject = null;
          }
        }
        catch (Exception paramString)
        {
          for (;;)
          {
            paramString.printStackTrace();
            paramString = null;
          }
        }
      }
    }
  }
  
  /* Error */
  private List<cds.a> b(String paramString, axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokespecial 672	cds:c	(Ljava/lang/String;Laxi;)Ljava/util/List;
    //   8: astore 5
    //   10: aload_0
    //   11: getfield 317	cds:e	Laxj;
    //   14: aload_2
    //   15: invokevirtual 322	axj:a	(Laxi;)J
    //   18: lstore_3
    //   19: getstatic 370	cds:d	Lcdu;
    //   22: invokevirtual 376	cdu:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   25: ldc_w 378
    //   28: ldc_w 674
    //   31: iconst_2
    //   32: anewarray 486	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: new 496	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 675	java/lang/StringBuilder:<init>	()V
    //   44: aload_1
    //   45: invokevirtual 658	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc_w 677
    //   51: invokevirtual 658	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 678	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: aastore
    //   58: dup
    //   59: iconst_1
    //   60: lload_3
    //   61: invokestatic 484	java/lang/Long:toString	(J)Ljava/lang/String;
    //   64: aastore
    //   65: invokevirtual 545	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   68: pop
    //   69: aload_0
    //   70: monitorexit
    //   71: aload 5
    //   73: areturn
    //   74: astore_1
    //   75: aload_1
    //   76: invokevirtual 533	android/database/sqlite/SQLiteException:printStackTrace	()V
    //   79: goto -10 -> 69
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	cds
    //   0	87	1	paramString	String
    //   0	87	2	paramAxi	axi
    //   18	43	3	l1	long
    //   8	64	5	localList	List
    // Exception table:
    //   from	to	target	type
    //   19	69	74	android/database/sqlite/SQLiteException
    //   2	19	82	finally
    //   19	69	82	finally
    //   75	79	82	finally
  }
  
  private Drawable c()
  {
    return a(Resources.getSystem(), 17629184, false);
  }
  
  private cds.a c(String paramString, axi paramAxi, boolean paramBoolean)
  {
    ckk localCkk = d(paramString, paramAxi);
    Object localObject2 = (cds.a)this.k.get(localCkk);
    Object localObject1;
    int i1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((cds.a)localObject2).d)
      {
        localObject1 = localObject2;
        if (paramBoolean) {}
      }
    }
    else
    {
      localObject1 = new cds.a();
      if (a(localCkk, (cds.a)localObject1, paramBoolean)) {
        break label259;
      }
      try
      {
        if (axi.a().equals(paramAxi))
        {
          i1 = 0;
          paramString = this.j.getPackageInfo(paramString, i1);
          localObject2 = paramString.applicationInfo;
          if (localObject2 != null) {
            break label145;
          }
          throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
        }
      }
      catch (Exception paramString)
      {
        i1 = 0;
      }
    }
    for (;;)
    {
      if (i1 != 0) {
        this.k.put(localCkk, localObject1);
      }
      return localObject1;
      i1 = 8192;
      break;
      label145:
      ((cds.a)localObject1).a = cyk.a(this.e.a(((ApplicationInfo)localObject2).loadIcon(this.j), paramAxi), this.c);
      ((cds.a)localObject1).b = ((ApplicationInfo)localObject2).loadLabel(this.j);
      ((cds.a)localObject1).c = this.e.a(((cds.a)localObject1).b, paramAxi);
      ((cds.a)localObject1).d = false;
      a(a(((cds.a)localObject1).a, ((cds.a)localObject1).b.toString(), this.m), localCkk.b, paramString, this.e.a(paramAxi));
      i1 = 1;
      continue;
      label259:
      i1 = 1;
    }
  }
  
  private List<cds.a> c(String paramString, axi paramAxi)
  {
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.k.keySet().iterator();
    while (localIterator.hasNext())
    {
      ckk localCkk = (ckk)localIterator.next();
      if ((localCkk.b.getPackageName().equals(paramString)) && (localCkk.c.equals(paramAxi))) {
        localHashSet.add(localCkk);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramAxi = (ckk)paramString.next();
      localArrayList.add(this.k.remove(paramAxi));
    }
    return localArrayList;
  }
  
  private static ckk d(String paramString, axi paramAxi)
  {
    return new ckk(new ComponentName(paramString, paramString + "."), paramAxi);
  }
  
  private boolean d()
  {
    label90:
    for (;;)
    {
      try
      {
        if (this.o != null)
        {
          bool = this.o.booleanValue();
          return bool;
        }
        int i1 = a(this.c);
        if (i1 != -1) {
          if (i1 >= 42)
          {
            break label90;
            this.o = Boolean.valueOf(bool);
            new StringBuilder("Parallel icon update enabled: ").append(this.o);
            bool = this.o.booleanValue();
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
  
  private void e()
  {
    this.p = Locale.getDefault().toString();
  }
  
  final ContentValues a(cdr paramCdr, boolean paramBoolean)
  {
    Object localObject1 = new ckk(paramCdr.a(), paramCdr.b());
    Object localObject2;
    if (!paramBoolean)
    {
      localObject2 = (cds.a)this.k.get(localObject1);
      if ((localObject2 != null) && (!((cds.a)localObject2).d))
      {
        localObject1 = localObject2;
        if (((cds.a)localObject2).a != null) {
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
        localObject2 = new cds.a();
        ((cds.a)localObject2).a = cyk.a(paramCdr.a(this.c, this.g), this.c);
      }
      ((cds.a)localObject2).b = paramCdr.y_();
      ((cds.a)localObject2).c = this.e.a(((cds.a)localObject2).b, paramCdr.b());
      this.k.put(new ckk(paramCdr.a(), paramCdr.b()), localObject2);
      return a(((cds.a)localObject2).a, ((cds.a)localObject2).b.toString(), this.l);
    }
  }
  
  /* Error */
  public final Bitmap a(Intent paramIntent, axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 750	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +13 -> 21
    //   11: aload_0
    //   12: aload_2
    //   13: invokevirtual 242	cds:a	(Laxi;)Landroid/graphics/Bitmap;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: aload_3
    //   23: aload_0
    //   24: getfield 753	cds:f	Laxc;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 758	axc:a	(Landroid/content/Intent;Laxi;)Lawz;
    //   32: aload_2
    //   33: iconst_1
    //   34: iconst_0
    //   35: invokespecial 760	cds:a	(Landroid/content/ComponentName;Lcdr;Laxi;ZZ)Lcds$a;
    //   38: getfield 239	cds$a:a	Landroid/graphics/Bitmap;
    //   41: astore_1
    //   42: goto -25 -> 17
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	cds
    //   0	50	1	paramIntent	Intent
    //   0	50	2	paramAxi	axi
    //   6	17	3	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   11	17	45	finally
    //   21	42	45	finally
  }
  
  /* Error */
  public final Bitmap a(axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 67	cds:i	Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual 763	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifne +28 -> 38
    //   13: aload_0
    //   14: getfield 67	cds:i	Ljava/util/HashMap;
    //   17: astore_3
    //   18: aload_0
    //   19: invokespecial 255	cds:c	()Landroid/graphics/drawable/Drawable;
    //   22: astore_2
    //   23: aload_2
    //   24: ifnonnull +30 -> 54
    //   27: invokestatic 768	fzg:a	()Landroid/graphics/Bitmap;
    //   30: astore_2
    //   31: aload_3
    //   32: aload_1
    //   33: aload_2
    //   34: invokevirtual 278	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   37: pop
    //   38: aload_0
    //   39: getfield 67	cds:i	Ljava/util/HashMap;
    //   42: aload_1
    //   43: invokevirtual 265	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   46: checkcast 174	android/graphics/Bitmap
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: areturn
    //   54: aload_0
    //   55: getfield 317	cds:e	Laxj;
    //   58: aload_2
    //   59: aload_1
    //   60: invokevirtual 707	axj:a	(Landroid/graphics/drawable/Drawable;Laxi;)Landroid/graphics/drawable/Drawable;
    //   63: astore 4
    //   65: aload 4
    //   67: invokevirtual 773	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
    //   70: iconst_1
    //   71: invokestatic 193	java/lang/Math:max	(II)I
    //   74: aload 4
    //   76: invokevirtual 776	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
    //   79: iconst_1
    //   80: invokestatic 193	java/lang/Math:max	(II)I
    //   83: getstatic 779	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   86: invokestatic 197	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   89: astore_2
    //   90: new 199	android/graphics/Canvas
    //   93: dup
    //   94: aload_2
    //   95: invokespecial 202	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   98: astore 5
    //   100: aload 4
    //   102: iconst_0
    //   103: iconst_0
    //   104: aload_2
    //   105: invokevirtual 178	android/graphics/Bitmap:getWidth	()I
    //   108: aload_2
    //   109: invokevirtual 181	android/graphics/Bitmap:getHeight	()I
    //   112: invokevirtual 782	android/graphics/drawable/Drawable:setBounds	(IIII)V
    //   115: aload 4
    //   117: aload 5
    //   119: invokevirtual 786	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   122: aload 5
    //   124: aconst_null
    //   125: invokevirtual 789	android/graphics/Canvas:setBitmap	(Landroid/graphics/Bitmap;)V
    //   128: goto -97 -> 31
    //   131: astore_1
    //   132: aload_0
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	cds
    //   0	136	1	paramAxi	axi
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
  
  public final Drawable a(ActivityInfo paramActivityInfo)
  {
    try
    {
      Resources localResources = this.j.getResourcesForApplication(paramActivityInfo.applicationInfo);
      if (localResources != null)
      {
        int i1 = paramActivityInfo.getIconResource();
        if (i1 != 0) {
          return a(localResources, i1, true);
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
    return c();
  }
  
  public final Drawable a(String paramString)
  {
    paramString = c(paramString);
    return new BitmapDrawable(this.c.getResources(), paramString.a);
  }
  
  public final Drawable a(String paramString, int paramInt)
  {
    try
    {
      paramString = this.j.getResourcesForApplication(paramString);
      if ((paramString != null) && (paramInt != 0)) {
        return a(paramString, paramInt, true);
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
    return c();
  }
  
  public final cds.b a(final BubbleTextView paramBubbleTextView, final ckq paramCkq)
  {
    paramBubbleTextView = new Runnable()
    {
      public final void run()
      {
        if ((paramCkq instanceof ckj)) {
          cds.this.a((ckj)paramCkq, null, false);
        }
        for (;;)
        {
          cds.this.b.execute(new Runnable()
          {
            public final void run()
            {
              cds.2.this.b.a(cds.2.this.a);
            }
          });
          return;
          Object localObject;
          if ((paramCkq instanceof clr))
          {
            clr localClr = (clr)paramCkq;
            cds localCds = cds.this;
            if (localClr.I != null) {}
            for (localObject = localClr.I;; localObject = localClr.d)
            {
              localCds.a(localClr, (Intent)localObject, localClr.y);
              break;
            }
          }
          if ((paramCkq instanceof clp))
          {
            localObject = (clp)paramCkq;
            cds.this.a(((clp)localObject).y, false, (clp)localObject);
          }
        }
      }
    };
    this.h.post(paramBubbleTextView);
    return new cds.b(paramBubbleTextView, this.h);
  }
  
  public final void a()
  {
    this.k.clear();
    d.a(d.getWritableDatabase());
  }
  
  /* Error */
  public final void a(ComponentName paramComponentName, axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 570	cds:b	(Landroid/content/ComponentName;Laxi;)V
    //   8: aload_0
    //   9: getfield 317	cds:e	Laxj;
    //   12: aload_2
    //   13: invokevirtual 322	axj:a	(Laxi;)J
    //   16: lstore_3
    //   17: aload_1
    //   18: invokevirtual 594	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   21: invokestatic 331	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifeq +59 -> 83
    //   27: getstatic 370	cds:d	Lcdu;
    //   30: invokevirtual 376	cdu:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: ldc_w 378
    //   36: ldc_w 674
    //   39: iconst_2
    //   40: anewarray 486	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: new 496	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 675	java/lang/StringBuilder:<init>	()V
    //   52: aload_1
    //   53: invokevirtual 389	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   56: invokevirtual 658	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: ldc_w 826
    //   62: invokevirtual 658	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 678	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: lload_3
    //   72: invokestatic 484	java/lang/Long:toString	(J)Ljava/lang/String;
    //   75: aastore
    //   76: invokevirtual 545	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   79: pop
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: getstatic 370	cds:d	Lcdu;
    //   86: invokevirtual 376	cdu:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   89: ldc_w 378
    //   92: ldc_w 630
    //   95: iconst_2
    //   96: anewarray 486	java/lang/String
    //   99: dup
    //   100: iconst_0
    //   101: aload_1
    //   102: invokevirtual 389	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   105: aastore
    //   106: dup
    //   107: iconst_1
    //   108: lload_3
    //   109: invokestatic 484	java/lang/Long:toString	(J)Ljava/lang/String;
    //   112: aastore
    //   113: invokevirtual 545	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   116: pop
    //   117: goto -37 -> 80
    //   120: astore_1
    //   121: aload_1
    //   122: invokevirtual 533	android/database/sqlite/SQLiteException:printStackTrace	()V
    //   125: goto -45 -> 80
    //   128: astore_1
    //   129: aload_0
    //   130: monitorexit
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	cds
    //   0	133	1	paramComponentName	ComponentName
    //   0	133	2	paramAxi	axi
    //   16	93	3	l1	long
    // Exception table:
    //   from	to	target	type
    //   17	80	120	android/database/sqlite/SQLiteException
    //   83	117	120	android/database/sqlite/SQLiteException
    //   2	17	128	finally
    //   17	80	128	finally
    //   83	117	128	finally
    //   121	125	128	finally
  }
  
  public final void a(final ComponentName paramComponentName, final axi paramAxi, final Runnable paramRunnable)
  {
    this.h.post(new Runnable()
    {
      public final void run()
      {
        cds.this.a(paramComponentName, paramAxi);
        if (paramRunnable != null)
        {
          if (this.d) {
            fzw.c(paramRunnable);
          }
        }
        else {
          return;
        }
        paramRunnable.run();
      }
    });
  }
  
  public final void a(axi paramAxi, boolean paramBoolean, clp paramClp)
  {
    try
    {
      cds.a localA = c(paramClp.c, paramAxi, paramBoolean);
      paramClp.a = a(localA, paramAxi);
      paramClp.v = cyk.a(localA.b);
      paramClp.b = localA.d;
      paramClp.w = localA.c;
      return;
    }
    finally
    {
      paramAxi = finally;
      throw paramAxi;
    }
  }
  
  final void a(cdr paramCdr, PackageInfo paramPackageInfo, long paramLong)
  {
    a(a(paramCdr, false), paramCdr.a(), paramPackageInfo, paramLong);
  }
  
  public final void a(ckj paramCkj)
  {
    try
    {
      cds.a localA = a(paramCkj.h, null, paramCkj.y, false, paramCkj.e);
      if ((localA.a != null) && (!a(localA.a, paramCkj.y)))
      {
        paramCkj.v = cyk.a(localA.b);
        paramCkj.d = localA.a;
        paramCkj.w = localA.c;
        paramCkj.e = localA.d;
      }
      return;
    }
    finally
    {
      paramCkj = finally;
      throw paramCkj;
    }
  }
  
  public final void a(ckj paramCkj, cdr paramCdr, boolean paramBoolean)
  {
    if (paramCdr == null) {}
    for (;;)
    {
      try
      {
        localAxi = paramCkj.y;
        paramCdr = a(paramCkj.h, paramCdr, localAxi, false, paramBoolean);
        paramCkj.v = cyk.a(paramCdr.b);
        paramCkj.w = paramCdr.c;
        paramCkj.e = paramCdr.d;
        paramCkj.d = a(paramCdr, localAxi);
        return;
      }
      finally {}
      axi localAxi = paramCdr.b();
    }
  }
  
  public final void a(ckl paramCkl, String paramString, axi paramAxi)
  {
    try
    {
      paramString = a(paramString, paramAxi, false);
      paramCkl.v = cyk.a(paramString.b);
      paramCkl.f = false;
      paramCkl.g = paramString.d;
      paramCkl.i = a(paramString, paramAxi);
      return;
    }
    finally
    {
      paramCkl = finally;
      throw paramCkl;
    }
  }
  
  public final void a(cko paramCko, String paramString, axi paramAxi)
  {
    try
    {
      paramString = b(paramString, paramAxi, false);
      paramCko.v = cyk.a(paramString.b);
      paramCko.f = false;
      paramCko.g = paramString.d;
      paramCko.i = a(paramString, paramAxi);
      return;
    }
    finally
    {
      paramCko = finally;
      throw paramCko;
    }
  }
  
  /* Error */
  public final void a(clp paramClp, axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield 833	clp:c	Ljava/lang/String;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: aload_1
    //   18: getfield 833	clp:c	Ljava/lang/String;
    //   21: aload_2
    //   22: invokespecial 672	cds:c	(Ljava/lang/String;Laxi;)Ljava/util/List;
    //   25: astore 5
    //   27: aload 5
    //   29: invokeinterface 883 1 0
    //   34: ifne +39 -> 73
    //   37: aload_1
    //   38: aload 5
    //   40: iconst_0
    //   41: invokeinterface 886 2 0
    //   46: checkcast 12	cds$a
    //   49: getfield 239	cds$a:a	Landroid/graphics/Bitmap;
    //   52: putfield 807	clp:a	Landroid/graphics/Bitmap;
    //   55: aload_1
    //   56: aload 5
    //   58: iconst_0
    //   59: invokeinterface 886 2 0
    //   64: checkcast 12	cds$a
    //   67: getfield 295	cds$a:b	Ljava/lang/CharSequence;
    //   70: putfield 841	clp:v	Ljava/lang/CharSequence;
    //   73: aload_0
    //   74: getfield 317	cds:e	Laxj;
    //   77: aload_2
    //   78: invokevirtual 322	axj:a	(Laxi;)J
    //   81: lstore_3
    //   82: getstatic 370	cds:d	Lcdu;
    //   85: invokevirtual 376	cdu:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   88: ldc_w 378
    //   91: ldc_w 674
    //   94: iconst_2
    //   95: anewarray 486	java/lang/String
    //   98: dup
    //   99: iconst_0
    //   100: new 496	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 675	java/lang/StringBuilder:<init>	()V
    //   107: aload_1
    //   108: getfield 833	clp:c	Ljava/lang/String;
    //   111: invokevirtual 658	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: ldc_w 677
    //   117: invokevirtual 658	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 678	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: aastore
    //   124: dup
    //   125: iconst_1
    //   126: lload_3
    //   127: invokestatic 484	java/lang/Long:toString	(J)Ljava/lang/String;
    //   130: aastore
    //   131: invokevirtual 545	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   134: pop
    //   135: goto -122 -> 13
    //   138: astore_1
    //   139: aload_1
    //   140: invokevirtual 533	android/database/sqlite/SQLiteException:printStackTrace	()V
    //   143: goto -130 -> 13
    //   146: astore_1
    //   147: aload_0
    //   148: monitorexit
    //   149: aload_1
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	cds
    //   0	151	1	paramClp	clp
    //   0	151	2	paramAxi	axi
    //   81	46	3	l1	long
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
  
  public final void a(clr paramClr, ComponentName paramComponentName, cdr paramCdr, axi paramAxi, boolean paramBoolean)
  {
    try
    {
      paramComponentName = a(paramComponentName, paramCdr, paramAxi, paramBoolean, false);
      paramClr.v = cyk.a(paramComponentName.b);
      paramClr.f = a(paramComponentName.a, paramAxi);
      paramClr.g = paramComponentName.d;
      paramClr.i = a(paramComponentName, paramAxi);
      return;
    }
    finally
    {
      paramClr = finally;
      throw paramClr;
    }
  }
  
  /* Error */
  public final void a(clr paramClr, Intent paramIntent, axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: invokevirtual 750	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnonnull +32 -> 42
    //   13: aload_1
    //   14: aload_0
    //   15: aload_3
    //   16: invokevirtual 242	cds:a	(Laxi;)Landroid/graphics/Bitmap;
    //   19: putfield 877	clr:i	Landroid/graphics/Bitmap;
    //   22: aload_1
    //   23: ldc_w 364
    //   26: putfield 888	clr:v	Ljava/lang/CharSequence;
    //   29: aload_1
    //   30: iconst_1
    //   31: putfield 889	clr:f	Z
    //   34: aload_1
    //   35: iconst_0
    //   36: putfield 890	clr:g	Z
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: aload_1
    //   44: aload 4
    //   46: aload_0
    //   47: getfield 753	cds:f	Laxc;
    //   50: aload_2
    //   51: aload_3
    //   52: invokevirtual 758	axc:a	(Landroid/content/Intent;Laxi;)Lawz;
    //   55: aload_3
    //   56: iconst_1
    //   57: invokevirtual 893	cds:a	(Lclr;Landroid/content/ComponentName;Lcdr;Laxi;Z)V
    //   60: goto -21 -> 39
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	cds
    //   0	68	1	paramClr	clr
    //   0	68	2	paramIntent	Intent
    //   0	68	3	paramAxi	axi
    //   6	39	4	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	8	63	finally
    //   13	39	63	finally
    //   42	60	63	finally
  }
  
  /* Error */
  public final void a(String paramString, axi paramAxi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokespecial 896	cds:b	(Ljava/lang/String;Laxi;)Ljava/util/List;
    //   8: pop
    //   9: aload_0
    //   10: getfield 85	cds:j	Landroid/content/pm/PackageManager;
    //   13: aload_1
    //   14: sipush 8192
    //   17: invokevirtual 311	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   20: astore 5
    //   22: aload_0
    //   23: getfield 317	cds:e	Laxj;
    //   26: aload_2
    //   27: invokevirtual 322	axj:a	(Laxi;)J
    //   30: lstore_3
    //   31: aload_0
    //   32: getfield 753	cds:f	Laxc;
    //   35: aload_1
    //   36: aload_2
    //   37: invokevirtual 898	axc:a	(Ljava/lang/String;Laxi;)Ljava/util/List;
    //   40: invokeinterface 463 1 0
    //   45: astore_1
    //   46: aload_1
    //   47: invokeinterface 468 1 0
    //   52: ifeq +28 -> 80
    //   55: aload_0
    //   56: aload_1
    //   57: invokeinterface 472 1 0
    //   62: checkcast 286	cdr
    //   65: aload 5
    //   67: lload_3
    //   68: invokevirtual 900	cds:a	(Lcdr;Landroid/content/pm/PackageInfo;J)V
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
    //   0	83	0	this	cds
    //   0	83	1	paramString	String
    //   0	83	2	paramAxi	axi
    //   30	38	3	l1	long
    //   20	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   2	9	74	finally
    //   9	22	74	finally
    //   22	46	74	finally
    //   46	71	74	finally
    //   9	22	79	java/lang/Exception
  }
  
  public final void a(String paramString, axi paramAxi, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      c(paramString, paramAxi);
      ckk localCkk = d(paramString, paramAxi);
      paramAxi = (cds.a)this.k.get(localCkk);
      paramString = paramAxi;
      if (paramAxi == null)
      {
        paramString = new cds.a();
        this.k.put(localCkk, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.b = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.a = cyk.a(paramBitmap, this.c);
      }
      return;
    }
    finally {}
  }
  
  public final void a(Set<String> paramSet, boolean paramBoolean)
  {
    Object localObject2;
    Object localObject3;
    if (a(this.c) < 42)
    {
      ckr.a().f.a = true;
      localObject2 = ckr.a().f;
      localObject3 = ((cxf)localObject2).b;
    }
    ArrayList localArrayList;
    for (;;)
    {
      synchronized (((cxf.b)localObject3).c)
      {
        ((cxf.b)localObject3).a = null;
        ((cxf.b)localObject3).b.clear();
        try
        {
          fjo.K().unbindService((ServiceConnection)localObject3);
          ((cxf)localObject2).b.a(fjo.K());
          this.h.removeCallbacksAndMessages(a);
          e();
          localObject2 = this.e.b().iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localObject3 = (axi)((Iterator)localObject2).next();
            localArrayList = new ArrayList();
            ??? = this.f.a(null, (axi)localObject3);
            if ((??? != null) && (!((List)???).isEmpty())) {
              localArrayList.addAll((Collection)???);
            }
            ??? = ckl.j();
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
          localIllegalArgumentException.printStackTrace();
          continue;
        }
      }
      ckr.a().f.a = false;
    }
    if (axi.a().equals(localIllegalArgumentException)) {}
    for (??? = paramSet;; ??? = Collections.emptySet())
    {
      a(localIllegalArgumentException, localArrayList, (Set)???, paramBoolean);
      break;
    }
  }
  
  public final boolean a(Bitmap paramBitmap, axi paramAxi)
  {
    return this.i.get(paramAxi) == paramBitmap;
  }
  
  public final Drawable b(ckj paramCkj)
  {
    if (paramCkj == null) {
      return null;
    }
    return a(paramCkj.d());
  }
  
  public final CharSequence b(String paramString)
  {
    return c(paramString).v;
  }
  
  public final void b()
  {
    try
    {
      this.o = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void b(ComponentName paramComponentName, axi paramAxi)
  {
    try
    {
      this.k.remove(new ckk(paramComponentName, paramAxi));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public final clp c(String paramString)
  {
    paramString = new clp(paramString);
    a(axi.a(), false, paramString);
    return paramString;
  }
  
  public final CharSequence c(ckj paramCkj)
  {
    if (paramCkj == null) {
      return null;
    }
    return b(paramCkj.d());
  }
  
  static final class a
  {
    public Bitmap a;
    public CharSequence b = "";
    public CharSequence c = "";
    public boolean d;
    
    a() {}
  }
  
  public static final class b
  {
    public final Runnable a;
    public final Handler b;
    
    b(Runnable paramRunnable, Handler paramHandler)
    {
      this.a = paramRunnable;
      this.b = paramHandler;
    }
  }
  
  abstract class c
    implements Runnable
  {
    protected final long a;
    protected final HashMap<String, PackageInfo> b;
    protected final Stack<cdr> c;
    protected final Stack<cdr> d;
    protected final HashSet<String> e = new HashSet();
    protected boolean f;
    protected boolean g;
    protected int h;
    protected final Object i = new Object();
    private ReentrantLock k = new ReentrantLock();
    
    c(HashMap<String, PackageInfo> paramHashMap, Stack<cdr> paramStack1, Stack<cdr> paramStack2, boolean paramBoolean)
    {
      this.a = ???;
      this.b = paramStack1;
      this.c = paramStack2;
      this.d = paramBoolean;
      boolean bool1;
      this.f = bool1;
      if (paramStack2 == null)
      {
        m = n;
        this.h = m;
        return;
      }
      n = paramStack2.size();
      if (paramBoolean == null) {}
      for (;;)
      {
        m += n;
        break;
        m = paramBoolean.size();
      }
    }
    
    abstract void a();
    
    protected final void b()
    {
      ??? = null;
      Object localObject1 = null;
      if (!this.d.isEmpty()) {}
      try
      {
        ??? = (cdr)this.d.pop();
        localObject1 = ???;
      }
      catch (EmptyStackException localEmptyStackException2)
      {
        for (;;) {}
      }
      ??? = localObject1;
      if (localObject1 != null)
      {
        if (!(localObject1 instanceof ckl)) {
          break label130;
        }
        ??? = (ckl)localObject1;
        cds.a(cds.this, ((ckl)???).b, axi.a());
        this.e.add(((cdr)localObject1).a().getPackageName());
      }
      synchronized (this.i)
      {
        for (;;)
        {
          this.h -= 1;
          ??? = localObject1;
          if ((??? == null) && (!this.c.isEmpty())) {}
          try
          {
            localObject1 = (cdr)this.c.pop();
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
            if ((localObject1 instanceof cko))
            {
              ??? = (cko)localObject1;
              cds.b(cds.this, ((cko)???).a, axi.a());
              this.e.add(((cko)???).h().getPackageName());
              continue;
            }
            ??? = ((cdr)localObject1).a().flattenToString();
            localObject7 = cds.this.a((cdr)localObject1, true);
            try
            {
              cdv.d.getWritableDatabase().update("cache", (ContentValues)localObject7, "componentName = ? AND profileId = ?", new String[] { ???, Long.toString(this.a) });
              this.e.add(((cdr)localObject1).a().getPackageName());
            }
            catch (SQLiteException localSQLiteException)
            {
              localSQLiteException.printStackTrace();
            }
          }
        }
      }
      if (((localSQLiteException instanceof ckl)) || ((localSQLiteException instanceof cko))) {
        synchronized (this.i)
        {
          this.h -= 1;
          return;
        }
      }
      localObject7 = (PackageInfo)this.b.get(localCdr.a().getPackageName());
      if (localObject7 != null) {}
      synchronized (cds.this)
      {
        cds.this.a(localCdr, (PackageInfo)localObject7, this.a);
        this.e.add(localCdr.a().getPackageName());
        synchronized (this.i)
        {
          this.h -= 1;
          return;
        }
      }
    }
    
    final boolean c()
    {
      if ((this.d.isEmpty()) && (this.c.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        if (!bool) {
          a();
        }
        return bool;
      }
    }
    
    protected void d()
    {
      if (!this.e.isEmpty())
      {
        ckv localCkv1 = ckr.a().a;
        HashSet localHashSet = this.e;
        axi localAxi = cds.this.e.a(this.a);
        ckv.c localC = localCkv1.j();
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        for (;;)
        {
          Object localObject2;
          synchronized (ckv.s)
          {
            Iterator localIterator = ckv.u.iterator();
            if (!localIterator.hasNext()) {
              break;
            }
            localObject2 = (ckq)localIterator.next();
            if ((localObject2 instanceof ckl))
            {
              ((ckl)localObject2).a(localCkv1.C);
              localArrayList2.add((ckl)localObject2);
            }
          }
          if ((localObject2 instanceof cko))
          {
            ((cko)localObject2).a(localCkv2.C);
            localArrayList2.add((cko)localObject2);
          }
          else if (((localObject2 instanceof clr)) && (localAxi.equals(((ckq)localObject2).y)) && ((((ckq)localObject2).k == 0) || (((ckq)localObject2).k == 1)))
          {
            localObject2 = (clr)localObject2;
            ComponentName localComponentName = ((clr)localObject2).h();
            if ((localComponentName != null) && (localHashSet.contains(localComponentName.getPackageName())))
            {
              ((clr)localObject2).a(localCkv2.C);
              localArrayList2.add(localObject2);
            }
          }
        }
        localCkv2.p.a(localHashSet, localAxi, localArrayList1);
        if (!localArrayList2.isEmpty()) {
          localCkv2.d.post(new ckv.15(localCkv2, localC, localArrayList2, localAxi));
        }
        if (!localArrayList1.isEmpty()) {
          localCkv2.d.post(new ckv.16(localCkv2, localC, localArrayList1));
        }
        localCkv2.a(localC, false);
      }
    }
  }
  
  final class d
    extends cds.c
  {
    d(HashMap<String, PackageInfo> paramHashMap, Stack<cdr> paramStack1, Stack<cdr> paramStack2, boolean paramBoolean)
    {
      super(???, paramStack1, paramStack2, paramBoolean, true);
    }
    
    final void a()
    {
      fzw.a(this);
    }
    
    protected final void d()
    {
      super.d();
      if (this.g) {}
      do
      {
        return;
        this.g = true;
      } while (!this.f);
      cvp localCvp = ckr.a().i.a;
      String str = localCvp.a;
      if (localCvp.b()) {
        anr.a("Theme_Set_Succeed", true, new String[] { "type", str });
      }
      fjo.K().sendBroadcast(bdq.e(str));
    }
    
    final void e()
    {
      int j = this.d.size();
      int m = this.c.size();
      int i = 0;
      while (i < m + j)
      {
        fzw.a(this);
        i += 1;
      }
    }
    
    /* Error */
    public final void run()
    {
      // Byte code:
      //   0: ldc 102
      //   2: invokestatic 107	id:a	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: invokevirtual 109	cds$d:b	()V
      //   9: aload_0
      //   10: getfield 112	cds$d:i	Ljava/lang/Object;
      //   13: astore_1
      //   14: aload_1
      //   15: monitorenter
      //   16: aload_0
      //   17: getfield 116	cds$d:h	I
      //   20: ifne +7 -> 27
      //   23: aload_0
      //   24: invokevirtual 117	cds$d:d	()V
      //   27: aload_1
      //   28: monitorexit
      //   29: invokestatic 119	id:a	()V
      //   32: return
      //   33: astore_2
      //   34: aload_1
      //   35: monitorexit
      //   36: aload_2
      //   37: athrow
      //   38: astore_1
      //   39: invokestatic 119	id:a	()V
      //   42: aload_1
      //   43: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	44	0	this	d
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
  
  final class e
    extends cds.c
  {
    e(HashMap<String, PackageInfo> paramHashMap, Stack<cdr> paramStack1, Stack<cdr> paramStack2, boolean paramBoolean)
    {
      super(???, paramStack1, paramStack2, paramBoolean, bool1);
    }
    
    final void a()
    {
      cds.this.h.postAtTime(this, cds.a, SystemClock.uptimeMillis() + 1L);
    }
    
    protected final void d()
    {
      super.d();
      if ((!cds.a(cds.this)) && (this.f))
      {
        cvp localCvp = ckr.a().i.a;
        String str = localCvp.a;
        if (localCvp.b()) {
          anr.a("Theme_Set_Succeed", true, new String[] { "type", str });
        }
        fjo.K().sendBroadcast(bdq.e(str));
      }
    }
    
    public final void run()
    {
      id.a("iconCacheUpdateOneSerialized");
      try
      {
        b();
        if (c()) {
          d();
        }
        return;
      }
      finally
      {
        id.a();
      }
    }
  }
}