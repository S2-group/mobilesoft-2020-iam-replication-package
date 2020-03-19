package com.sika524.android.quickshortcut.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.sika524.android.quickshortcut.d.i;
import com.sika524.android.quickshortcut.d.u;
import com.sika524.android.quickshortcut.entity.ActivityItem;
import java.util.List;

public final class b
  extends AsyncTask
{
  private static final String a = b.class.getSimpleName();
  private static b g;
  private Context b;
  private boolean c;
  private boolean d;
  private Throwable e;
  private g f;
  
  private b(Context paramContext, g paramG)
  {
    this.b = paramContext;
    this.f = paramG;
  }
  
  public static b a(Context paramContext, g paramG)
  {
    if (g != null) {
      g.cancel(false);
    }
    g = new b(paramContext, paramG);
    return g;
  }
  
  private ActivityItem a(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    ActivityItem localActivityItem = new ActivityItem();
    localActivityItem.d(paramPackageManager.getApplicationLabel(paramApplicationInfo).toString().replaceAll("\n", ""));
    localActivityItem.b(paramApplicationInfo.packageName);
    localActivityItem.a(paramApplicationInfo.icon);
    if (!i.a())
    {
      paramPackageManager = paramPackageManager.getApplicationIcon(paramApplicationInfo);
      if ((paramPackageManager instanceof BitmapDrawable)) {
        localActivityItem.a(((BitmapDrawable)paramPackageManager).getBitmap());
      }
    }
    else
    {
      return localActivityItem;
    }
    int i = paramPackageManager.getIntrinsicWidth();
    int j = paramPackageManager.getIntrinsicHeight();
    paramApplicationInfo = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_4444);
    Canvas localCanvas = new Canvas(paramApplicationInfo);
    paramPackageManager.setBounds(0, 0, i, j);
    paramPackageManager.draw(localCanvas);
    localActivityItem.a(paramApplicationInfo);
    return localActivityItem;
  }
  
  public static boolean a()
  {
    return (g != null) && (g.d);
  }
  
  public static boolean b()
  {
    return (g != null) && (g.c);
  }
  
  public static Throwable c()
  {
    if (g != null) {
      return g.e;
    }
    return null;
  }
  
  /* Error */
  protected Throwable a(Void... arg1)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 142	com/sika524/android/quickshortcut/c/b:c	Z
    //   5: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   8: ldc -106
    //   10: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: aload_0
    //   14: iconst_1
    //   15: anewarray 157	java/util/List
    //   18: dup
    //   19: iconst_0
    //   20: aconst_null
    //   21: checkcast 157	java/util/List
    //   24: aastore
    //   25: invokevirtual 161	com/sika524/android/quickshortcut/c/b:publishProgress	([Ljava/lang/Object;)V
    //   28: aconst_null
    //   29: astore 8
    //   31: aload_0
    //   32: getfield 34	com/sika524/android/quickshortcut/c/b:b	Landroid/content/Context;
    //   35: invokevirtual 167	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   38: astore 11
    //   40: getstatic 172	com/sika524/android/quickshortcut/app/App:a	Ljava/util/List;
    //   43: ifnonnull +698 -> 741
    //   46: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   49: ldc -82
    //   51: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: getstatic 179	com/sika524/android/quickshortcut/d/t:a	Ljava/lang/Object;
    //   57: astore_1
    //   58: aload_1
    //   59: monitorenter
    //   60: aload 11
    //   62: iconst_0
    //   63: invokevirtual 183	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   66: astore 10
    //   68: aload_1
    //   69: monitorexit
    //   70: aload 10
    //   72: ifnonnull +156 -> 228
    //   75: iconst_0
    //   76: istore_2
    //   77: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   80: new 185	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   87: ldc -68
    //   89: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: iload_2
    //   93: invokevirtual 195	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   96: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: new 198	java/util/ArrayList
    //   105: dup
    //   106: aload 10
    //   108: invokeinterface 201 1 0
    //   113: invokespecial 203	java/util/ArrayList:<init>	(I)V
    //   116: astore 9
    //   118: aload_0
    //   119: getfield 34	com/sika524/android/quickshortcut/c/b:b	Landroid/content/Context;
    //   122: invokestatic 208	com/sika524/android/quickshortcut/d/y:a	(Landroid/content/Context;)Z
    //   125: ifeq +559 -> 684
    //   128: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   131: ldc -46
    //   133: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: aload 10
    //   138: invokeinterface 214 1 0
    //   143: astore_1
    //   144: aload_1
    //   145: invokeinterface 219 1 0
    //   150: ifeq +89 -> 239
    //   153: aload_1
    //   154: invokeinterface 223 1 0
    //   159: checkcast 75	android/content/pm/ApplicationInfo
    //   162: astore 10
    //   164: new 225	com/sika524/android/quickshortcut/entity/c
    //   167: dup
    //   168: invokespecial 226	com/sika524/android/quickshortcut/entity/c:<init>	()V
    //   171: astore 12
    //   173: aload 12
    //   175: aload 10
    //   177: putfield 229	com/sika524/android/quickshortcut/entity/c:a	Landroid/content/pm/ApplicationInfo;
    //   180: aload 12
    //   182: aload 11
    //   184: aload 10
    //   186: invokevirtual 55	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   189: putfield 232	com/sika524/android/quickshortcut/entity/c:b	Ljava/lang/CharSequence;
    //   192: aload 9
    //   194: aload 12
    //   196: invokeinterface 236 2 0
    //   201: pop
    //   202: goto -58 -> 144
    //   205: astore_1
    //   206: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   209: ldc -18
    //   211: aload_1
    //   212: invokestatic 241	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   215: aconst_null
    //   216: putstatic 172	com/sika524/android/quickshortcut/app/App:a	Ljava/util/List;
    //   219: aload_1
    //   220: areturn
    //   221: astore 8
    //   223: aload_1
    //   224: monitorexit
    //   225: aload 8
    //   227: athrow
    //   228: aload 10
    //   230: invokeinterface 201 1 0
    //   235: istore_2
    //   236: goto -159 -> 77
    //   239: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   242: ldc -13
    //   244: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   247: aload 9
    //   249: new 245	com/sika524/android/quickshortcut/d/c
    //   252: dup
    //   253: invokespecial 246	com/sika524/android/quickshortcut/d/c:<init>	()V
    //   256: invokestatic 252	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   259: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   262: ldc -2
    //   264: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   267: aload 9
    //   269: putstatic 172	com/sika524/android/quickshortcut/app/App:a	Ljava/util/List;
    //   272: goto +769 -> 1041
    //   275: iload_2
    //   276: aload 9
    //   278: invokeinterface 201 1 0
    //   283: if_icmpge +747 -> 1030
    //   286: invokestatic 258	com/sika524/android/quickshortcut/d/v:a	()V
    //   289: aload 9
    //   291: iload_2
    //   292: invokeinterface 262 2 0
    //   297: checkcast 225	com/sika524/android/quickshortcut/entity/c
    //   300: astore 12
    //   302: aload 12
    //   304: getfield 265	com/sika524/android/quickshortcut/entity/c:c	Lcom/sika524/android/quickshortcut/entity/b;
    //   307: ifnonnull +706 -> 1013
    //   310: new 267	com/sika524/android/quickshortcut/entity/b
    //   313: dup
    //   314: invokespecial 268	com/sika524/android/quickshortcut/entity/b:<init>	()V
    //   317: astore 10
    //   319: aload 8
    //   321: astore_1
    //   322: aload 12
    //   324: getfield 229	com/sika524/android/quickshortcut/entity/c:a	Landroid/content/pm/ApplicationInfo;
    //   327: astore 13
    //   329: aload 8
    //   331: astore_1
    //   332: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   335: new 185	java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   342: ldc_w 270
    //   345: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: aload 13
    //   350: getfield 78	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   353: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   362: aload 8
    //   364: astore_1
    //   365: aload 10
    //   367: aload_0
    //   368: aload 11
    //   370: aload 13
    //   372: invokespecial 272	com/sika524/android/quickshortcut/c/b:a	(Landroid/content/pm/PackageManager;Landroid/content/pm/ApplicationInfo;)Lcom/sika524/android/quickshortcut/entity/ActivityItem;
    //   375: invokevirtual 275	com/sika524/android/quickshortcut/entity/b:a	(Lcom/sika524/android/quickshortcut/entity/ActivityItem;)V
    //   378: aload 8
    //   380: astore_1
    //   381: aload 10
    //   383: new 198	java/util/ArrayList
    //   386: dup
    //   387: invokespecial 276	java/util/ArrayList:<init>	()V
    //   390: invokevirtual 279	com/sika524/android/quickshortcut/entity/b:a	(Ljava/util/List;)V
    //   393: aload 8
    //   395: astore_1
    //   396: aload 13
    //   398: getfield 78	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   401: astore 8
    //   403: aload 8
    //   405: astore_1
    //   406: getstatic 179	com/sika524/android/quickshortcut/d/t:a	Ljava/lang/Object;
    //   409: astore 14
    //   411: aload 8
    //   413: astore_1
    //   414: aload 14
    //   416: monitorenter
    //   417: aload 11
    //   419: aload 13
    //   421: getfield 78	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   424: iconst_1
    //   425: invokevirtual 283	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   428: astore 15
    //   430: aload 14
    //   432: monitorexit
    //   433: aload 15
    //   435: ifnull +562 -> 997
    //   438: aload 8
    //   440: astore_1
    //   441: aload 15
    //   443: getfield 289	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   446: ifnull +551 -> 997
    //   449: aload 8
    //   451: astore_1
    //   452: aload 15
    //   454: getfield 289	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   457: astore 14
    //   459: aload 8
    //   461: astore_1
    //   462: aload 14
    //   464: arraylength
    //   465: istore 7
    //   467: iconst_0
    //   468: istore_3
    //   469: iload_3
    //   470: iload 7
    //   472: if_icmpge +525 -> 997
    //   475: aload 14
    //   477: iload_3
    //   478: aaload
    //   479: astore 16
    //   481: aload 16
    //   483: ifnull +194 -> 677
    //   486: aload 8
    //   488: astore_1
    //   489: aload 16
    //   491: getfield 294	android/content/pm/ActivityInfo:exported	Z
    //   494: ifeq +183 -> 677
    //   497: aload 8
    //   499: astore_1
    //   500: new 48	com/sika524/android/quickshortcut/entity/ActivityItem
    //   503: dup
    //   504: invokespecial 49	com/sika524/android/quickshortcut/entity/ActivityItem:<init>	()V
    //   507: astore 15
    //   509: aload 8
    //   511: astore_1
    //   512: aload 12
    //   514: getfield 232	com/sika524/android/quickshortcut/entity/c:b	Ljava/lang/CharSequence;
    //   517: ifnonnull +351 -> 868
    //   520: aload 8
    //   522: astore_1
    //   523: aload 15
    //   525: aload 11
    //   527: aload 13
    //   529: invokevirtual 55	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   532: invokeinterface 60 1 0
    //   537: ldc 62
    //   539: ldc 64
    //   541: invokevirtual 70	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   544: invokevirtual 73	com/sika524/android/quickshortcut/entity/ActivityItem:d	(Ljava/lang/String;)V
    //   547: aload 8
    //   549: astore_1
    //   550: aload 15
    //   552: aload 16
    //   554: aload 11
    //   556: invokevirtual 298	android/content/pm/ActivityInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   559: invokeinterface 60 1 0
    //   564: ldc 62
    //   566: ldc 64
    //   568: invokevirtual 70	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   571: invokevirtual 300	com/sika524/android/quickshortcut/entity/ActivityItem:c	(Ljava/lang/String;)V
    //   574: aload 8
    //   576: astore_1
    //   577: aload 15
    //   579: aload 13
    //   581: getfield 78	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   584: invokevirtual 80	com/sika524/android/quickshortcut/entity/ActivityItem:b	(Ljava/lang/String;)V
    //   587: aload 8
    //   589: astore_1
    //   590: aload 15
    //   592: aload 16
    //   594: getfield 303	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   597: invokevirtual 305	com/sika524/android/quickshortcut/entity/ActivityItem:a	(Ljava/lang/String;)V
    //   600: aload 8
    //   602: astore_1
    //   603: aload 15
    //   605: aload 13
    //   607: getfield 84	android/content/pm/ApplicationInfo:icon	I
    //   610: invokevirtual 87	com/sika524/android/quickshortcut/entity/ActivityItem:a	(I)V
    //   613: aload 8
    //   615: astore_1
    //   616: invokestatic 92	com/sika524/android/quickshortcut/d/i:a	()Z
    //   619: ifne +42 -> 661
    //   622: aload 8
    //   624: astore_1
    //   625: aload 11
    //   627: aload 13
    //   629: invokevirtual 96	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   632: astore 16
    //   634: aload 8
    //   636: astore_1
    //   637: aload 16
    //   639: instanceof 98
    //   642: ifeq +254 -> 896
    //   645: aload 8
    //   647: astore_1
    //   648: aload 15
    //   650: aload 16
    //   652: checkcast 98	android/graphics/drawable/BitmapDrawable
    //   655: invokevirtual 102	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   658: invokevirtual 105	com/sika524/android/quickshortcut/entity/ActivityItem:a	(Landroid/graphics/Bitmap;)V
    //   661: aload 8
    //   663: astore_1
    //   664: aload 10
    //   666: invokevirtual 308	com/sika524/android/quickshortcut/entity/b:c	()Ljava/util/List;
    //   669: aload 15
    //   671: invokeinterface 236 2 0
    //   676: pop
    //   677: iload_3
    //   678: iconst_1
    //   679: iadd
    //   680: istore_3
    //   681: goto -212 -> 469
    //   684: aload 10
    //   686: invokeinterface 214 1 0
    //   691: astore_1
    //   692: aload_1
    //   693: invokeinterface 219 1 0
    //   698: ifeq -431 -> 267
    //   701: aload_1
    //   702: invokeinterface 223 1 0
    //   707: checkcast 75	android/content/pm/ApplicationInfo
    //   710: astore 10
    //   712: new 225	com/sika524/android/quickshortcut/entity/c
    //   715: dup
    //   716: invokespecial 226	com/sika524/android/quickshortcut/entity/c:<init>	()V
    //   719: astore 12
    //   721: aload 12
    //   723: aload 10
    //   725: putfield 229	com/sika524/android/quickshortcut/entity/c:a	Landroid/content/pm/ApplicationInfo;
    //   728: aload 9
    //   730: aload 12
    //   732: invokeinterface 236 2 0
    //   737: pop
    //   738: goto -46 -> 692
    //   741: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   744: ldc_w 310
    //   747: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   750: getstatic 172	com/sika524/android/quickshortcut/app/App:a	Ljava/util/List;
    //   753: astore 9
    //   755: goto +286 -> 1041
    //   758: astore 13
    //   760: aload 14
    //   762: monitorexit
    //   763: aload 8
    //   765: astore_1
    //   766: aload 13
    //   768: athrow
    //   769: astore 8
    //   771: getstatic 315	android/os/Build$VERSION:SDK_INT	I
    //   774: bipush 15
    //   776: if_icmplt +40 -> 816
    //   779: aload 8
    //   781: invokevirtual 318	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   784: instanceof 320
    //   787: ifeq +29 -> 816
    //   790: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   793: new 185	java/lang/StringBuilder
    //   796: dup
    //   797: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   800: ldc_w 322
    //   803: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   806: aload_1
    //   807: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   813: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   816: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   819: ldc_w 324
    //   822: aload 8
    //   824: invokestatic 241	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   827: aload 12
    //   829: aconst_null
    //   830: putfield 265	com/sika524/android/quickshortcut/entity/c:c	Lcom/sika524/android/quickshortcut/entity/b;
    //   833: aload_1
    //   834: astore 8
    //   836: aload 10
    //   838: astore_1
    //   839: iload_2
    //   840: aload 9
    //   842: invokeinterface 201 1 0
    //   847: iconst_1
    //   848: isub
    //   849: if_icmpne +173 -> 1022
    //   852: aload_1
    //   853: iconst_1
    //   854: invokevirtual 327	com/sika524/android/quickshortcut/entity/b:a	(Z)V
    //   857: aload_1
    //   858: invokestatic 332	com/sika524/android/quickshortcut/c/a:a	(Lcom/sika524/android/quickshortcut/entity/b;)V
    //   861: iload_2
    //   862: iconst_1
    //   863: iadd
    //   864: istore_2
    //   865: goto -590 -> 275
    //   868: aload 8
    //   870: astore_1
    //   871: aload 15
    //   873: aload 12
    //   875: getfield 232	com/sika524/android/quickshortcut/entity/c:b	Ljava/lang/CharSequence;
    //   878: invokeinterface 60 1 0
    //   883: ldc 62
    //   885: ldc 64
    //   887: invokevirtual 70	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   890: invokevirtual 73	com/sika524/android/quickshortcut/entity/ActivityItem:d	(Ljava/lang/String;)V
    //   893: goto -346 -> 547
    //   896: aload 8
    //   898: astore_1
    //   899: aload 16
    //   901: invokevirtual 111	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
    //   904: istore 5
    //   906: aload 8
    //   908: astore_1
    //   909: aload 16
    //   911: invokevirtual 114	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
    //   914: istore 6
    //   916: iload 5
    //   918: istore 4
    //   920: iload 5
    //   922: ifgt +124 -> 1046
    //   925: iconst_1
    //   926: istore 4
    //   928: goto +118 -> 1046
    //   931: aload 8
    //   933: astore_1
    //   934: iload 4
    //   936: iload 5
    //   938: getstatic 120	android/graphics/Bitmap$Config:ARGB_4444	Landroid/graphics/Bitmap$Config;
    //   941: invokestatic 126	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   944: astore 17
    //   946: aload 8
    //   948: astore_1
    //   949: new 128	android/graphics/Canvas
    //   952: dup
    //   953: aload 17
    //   955: invokespecial 130	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   958: astore 18
    //   960: aload 8
    //   962: astore_1
    //   963: aload 16
    //   965: iconst_0
    //   966: iconst_0
    //   967: iload 4
    //   969: iload 5
    //   971: invokevirtual 134	android/graphics/drawable/Drawable:setBounds	(IIII)V
    //   974: aload 8
    //   976: astore_1
    //   977: aload 16
    //   979: aload 18
    //   981: invokevirtual 138	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   984: aload 8
    //   986: astore_1
    //   987: aload 15
    //   989: aload 17
    //   991: invokevirtual 105	com/sika524/android/quickshortcut/entity/ActivityItem:a	(Landroid/graphics/Bitmap;)V
    //   994: goto -333 -> 661
    //   997: aload 8
    //   999: astore_1
    //   1000: aload 12
    //   1002: aload 10
    //   1004: putfield 265	com/sika524/android/quickshortcut/entity/c:c	Lcom/sika524/android/quickshortcut/entity/b;
    //   1007: aload 10
    //   1009: astore_1
    //   1010: goto -171 -> 839
    //   1013: aload 12
    //   1015: getfield 265	com/sika524/android/quickshortcut/entity/c:c	Lcom/sika524/android/quickshortcut/entity/b;
    //   1018: astore_1
    //   1019: goto -180 -> 839
    //   1022: aload_1
    //   1023: iconst_0
    //   1024: invokevirtual 327	com/sika524/android/quickshortcut/entity/b:a	(Z)V
    //   1027: goto -170 -> 857
    //   1030: getstatic 27	com/sika524/android/quickshortcut/c/b:a	Ljava/lang/String;
    //   1033: ldc_w 334
    //   1036: invokestatic 155	com/sika524/android/quickshortcut/d/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1039: aconst_null
    //   1040: areturn
    //   1041: iconst_0
    //   1042: istore_2
    //   1043: goto -768 -> 275
    //   1046: iload 6
    //   1048: istore 5
    //   1050: iload 6
    //   1052: ifgt -121 -> 931
    //   1055: iconst_1
    //   1056: istore 5
    //   1058: goto -127 -> 931
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1061	0	this	b
    //   76	967	2	i	int
    //   468	213	3	j	int
    //   918	50	4	k	int
    //   904	153	5	m	int
    //   914	137	6	n	int
    //   465	8	7	i1	int
    //   29	1	8	localObject1	Object
    //   221	173	8	localObject2	Object
    //   401	363	8	str	String
    //   769	54	8	localException	Exception
    //   834	164	8	arrayOfVoid	Void[]
    //   116	725	9	localObject3	Object
    //   66	942	10	localObject4	Object
    //   38	588	11	localPackageManager	PackageManager
    //   171	843	12	localC	com.sika524.android.quickshortcut.entity.c
    //   327	301	13	localApplicationInfo	ApplicationInfo
    //   758	9	13	localObject5	Object
    //   409	352	14	localObject6	Object
    //   428	560	15	localObject7	Object
    //   479	499	16	localDrawable	Drawable
    //   944	46	17	localBitmap	Bitmap
    //   958	22	18	localCanvas	Canvas
    // Exception table:
    //   from	to	target	type
    //   31	60	205	java/lang/Exception
    //   77	144	205	java/lang/Exception
    //   144	202	205	java/lang/Exception
    //   225	228	205	java/lang/Exception
    //   228	236	205	java/lang/Exception
    //   239	267	205	java/lang/Exception
    //   267	272	205	java/lang/Exception
    //   275	319	205	java/lang/Exception
    //   684	692	205	java/lang/Exception
    //   692	738	205	java/lang/Exception
    //   741	755	205	java/lang/Exception
    //   771	816	205	java/lang/Exception
    //   816	833	205	java/lang/Exception
    //   839	857	205	java/lang/Exception
    //   857	861	205	java/lang/Exception
    //   1013	1019	205	java/lang/Exception
    //   1022	1027	205	java/lang/Exception
    //   60	70	221	finally
    //   223	225	221	finally
    //   417	433	758	finally
    //   760	763	758	finally
    //   322	329	769	java/lang/Exception
    //   332	362	769	java/lang/Exception
    //   365	378	769	java/lang/Exception
    //   381	393	769	java/lang/Exception
    //   396	403	769	java/lang/Exception
    //   406	411	769	java/lang/Exception
    //   414	417	769	java/lang/Exception
    //   441	449	769	java/lang/Exception
    //   452	459	769	java/lang/Exception
    //   462	467	769	java/lang/Exception
    //   489	497	769	java/lang/Exception
    //   500	509	769	java/lang/Exception
    //   512	520	769	java/lang/Exception
    //   523	547	769	java/lang/Exception
    //   550	574	769	java/lang/Exception
    //   577	587	769	java/lang/Exception
    //   590	600	769	java/lang/Exception
    //   603	613	769	java/lang/Exception
    //   616	622	769	java/lang/Exception
    //   625	634	769	java/lang/Exception
    //   637	645	769	java/lang/Exception
    //   648	661	769	java/lang/Exception
    //   664	677	769	java/lang/Exception
    //   766	769	769	java/lang/Exception
    //   871	893	769	java/lang/Exception
    //   899	906	769	java/lang/Exception
    //   909	916	769	java/lang/Exception
    //   934	946	769	java/lang/Exception
    //   949	960	769	java/lang/Exception
    //   963	974	769	java/lang/Exception
    //   977	984	769	java/lang/Exception
    //   987	994	769	java/lang/Exception
    //   1000	1007	769	java/lang/Exception
  }
  
  protected void a(Throwable paramThrowable)
  {
    super.onPostExecute(paramThrowable);
    this.c = false;
    this.e = paramThrowable;
    if (this.e == null)
    {
      this.d = true;
      return;
    }
    u.a(a, "Failed to read. Interrupt filter", this.e);
    a.a();
  }
  
  protected void a(List... paramVarArgs)
  {
    if (this.f != null)
    {
      u.a(a, "Callback started");
      this.f.a();
    }
  }
  
  public void d()
  {
    u.a(a, "Execute reader");
    if (Build.VERSION.SDK_INT < 11)
    {
      execute(new Void[] { (Void)null });
      return;
    }
    executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[] { (Void)null });
  }
}
