package o;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.launcher3.BubbleTextView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public final class gc
  implements ﭞ.ˊ
{
  private static final byte[] ʹ = { 107, 82, 13, 75, -15, 8, -16, 1, 4, 3, 52, -55, -14, -1, -8, 13, -11, -8, 68, -68, 1, 61, -36, -19, -4, -10, 8, -8, 0, 22, -22, -15, 11, -8, 0, -15, 0, -17, 34, -19, -4, -10, 8, -8, 0, 26, -39, 6, -11, 0, -17, 34, -19, -4, -10, 8, -8, 0, 22, -22, -15, 11, -8, 0, -15, 9, -20, -3, 29, -29, 10, 1, -21, 13, 15, -23, -6, 6, -15, 8, -16, 1, 4, 3, 52, -55, -14, -1, -8, 13, -11, -8, 68, -23, -46, -1, -8, 13, -21, 2, -15, 8, -16, 1, 4, 3, 52, -55, -14, -1, -8, 13, -11, -8, 68, -68, 1, 61, -36, -19, -4, -10, 8, -8, 0, 26, -39, 6, -11, 13, -4, -10, 8, -8, 0, 21, -21, -14, 6, 15, -15, -3, 8, -8, -1, 41, -46, 9, -3 };
  static final Object ˊ = new Object();
  private static int ﹳ = 0;
  private static int ﾞ = 1;
  final Handler ʻ;
  public ﺕ ʼ;
  @qQ
  צּ.if ʽ;
  private final kY ʾ;
  private final HashMap<lt, if> ʿ;
  private final int ˈ;
  private final int ˉ;
  public final HashMap<lf, Bitmap> ˋ;
  private final int ˌ;
  private final BitmapFactory.Options ˍ;
  final iG ˎ;
  final lh ˏ;
  private String ˑ;
  private final Context ͺ;
  private Bitmap ـ;
  final ˊ ᐝ;
  private Canvas ᐧ;
  private Paint ᐨ;
  private final PackageManager ι;
  
  /* Error */
  public gc(Context paramContext, gk paramGk)
  {
    // Byte code:
    //   0: goto +129 -> 129
    //   3: astore_1
    //   4: aload_1
    //   5: invokevirtual 129	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   8: athrow
    //   9: invokestatic 134	o/ho:ˎ	()Landroid/os/Looper;
    //   12: astore_2
    //   13: new 136	android/os/Handler
    //   16: dup
    //   17: aload_2
    //   18: invokespecial 139	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   21: astore_2
    //   22: aload_0
    //   23: aload_2
    //   24: putfield 141	o/gc:ʻ	Landroid/os/Handler;
    //   27: aload_0
    //   28: iconst_0
    //   29: putfield 143	o/gc:ˉ	I
    //   32: aload_1
    //   33: invokevirtual 149	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   36: astore_2
    //   37: aload_2
    //   38: ldc -106
    //   40: invokevirtual 156	android/content/res/Resources:getColor	(I)I
    //   43: istore_3
    //   44: aload_0
    //   45: iload_3
    //   46: putfield 158	o/gc:ˌ	I
    //   49: new 160	android/graphics/BitmapFactory$Options
    //   52: dup
    //   53: invokespecial 161	android/graphics/BitmapFactory$Options:<init>	()V
    //   56: astore_2
    //   57: aload_0
    //   58: aload_2
    //   59: putfield 163	o/gc:ˍ	Landroid/graphics/BitmapFactory$Options;
    //   62: aload_0
    //   63: getfield 163	o/gc:ˍ	Landroid/graphics/BitmapFactory$Options;
    //   66: astore_2
    //   67: getstatic 169	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
    //   70: astore 4
    //   72: aload_2
    //   73: aload 4
    //   75: putfield 172	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   78: aload_0
    //   79: invokestatic 178	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   82: invokevirtual 182	java/util/Locale:toString	()Ljava/lang/String;
    //   85: putfield 184	o/gc:ˑ	Ljava/lang/String;
    //   88: aload_1
    //   89: invokevirtual 188	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   92: checkcast 190	o/Ἵ
    //   95: invokevirtual 193	o/Ἵ:ˊ	()Lo/ḭ;
    //   98: astore_1
    //   99: aload_1
    //   100: aload_0
    //   101: invokeinterface 198 2 0
    //   106: return
    //   107: iconst_1
    //   108: istore_3
    //   109: goto +220 -> 329
    //   112: aload_0
    //   113: getfield 200	o/gc:ᐝ	Lo/gc$ˊ;
    //   116: aload_0
    //   117: getfield 200	o/gc:ᐝ	Lo/gc$ˊ;
    //   120: invokevirtual 204	o/gc$ˊ:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   123: invokestatic 207	o/gc$ˊ:ˊ	(Lo/gc$ˊ;Landroid/database/sqlite/SQLiteDatabase;)V
    //   126: goto -117 -> 9
    //   129: aload_0
    //   130: invokespecial 117	java/lang/Object:<init>	()V
    //   133: aload_0
    //   134: new 209	java/util/HashMap
    //   137: dup
    //   138: invokespecial 210	java/util/HashMap:<init>	()V
    //   141: putfield 212	o/gc:ˋ	Ljava/util/HashMap;
    //   144: aload_0
    //   145: new 214	o/iG
    //   148: dup
    //   149: invokespecial 215	o/iG:<init>	()V
    //   152: putfield 217	o/gc:ˎ	Lo/iG;
    //   155: aload_0
    //   156: new 209	java/util/HashMap
    //   159: dup
    //   160: bipush 50
    //   162: invokespecial 220	java/util/HashMap:<init>	(I)V
    //   165: putfield 222	o/gc:ʿ	Ljava/util/HashMap;
    //   168: aload_0
    //   169: aload_1
    //   170: putfield 224	o/gc:ͺ	Landroid/content/Context;
    //   173: goto +8 -> 181
    //   176: iconst_0
    //   177: istore_3
    //   178: goto +151 -> 329
    //   181: getstatic 110	o/gc:ʹ	[B
    //   184: iconst_2
    //   185: baload
    //   186: iconst_1
    //   187: isub
    //   188: i2b
    //   189: getstatic 110	o/gc:ʹ	[B
    //   192: bipush 21
    //   194: baload
    //   195: i2s
    //   196: getstatic 110	o/gc:ʹ	[B
    //   199: sipush 135
    //   202: baload
    //   203: i2b
    //   204: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   207: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   210: getstatic 110	o/gc:ʹ	[B
    //   213: bipush 47
    //   215: baload
    //   216: i2b
    //   217: bipush 90
    //   219: getstatic 110	o/gc:ʹ	[B
    //   222: bipush 74
    //   224: baload
    //   225: i2b
    //   226: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   229: aconst_null
    //   230: invokevirtual 237	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   233: aload_1
    //   234: aconst_null
    //   235: invokevirtual 243	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   238: checkcast 245	android/content/pm/PackageManager
    //   241: astore 4
    //   243: aload_0
    //   244: aload 4
    //   246: putfield 247	o/gc:ι	Landroid/content/pm/PackageManager;
    //   249: aload_0
    //   250: aload_0
    //   251: getfield 224	o/gc:ͺ	Landroid/content/Context;
    //   254: invokestatic 252	o/lg:ˊ	(Landroid/content/Context;)Lo/lh;
    //   257: putfield 254	o/gc:ˏ	Lo/lh;
    //   260: aload_0
    //   261: aload_0
    //   262: getfield 224	o/gc:ͺ	Landroid/content/Context;
    //   265: invokestatic 259	o/kY:ˊ	(Landroid/content/Context;)Lo/kY;
    //   268: putfield 261	o/gc:ʾ	Lo/kY;
    //   271: aload_0
    //   272: aload_2
    //   273: getfield 265	o/gk:ʾ	I
    //   276: putfield 267	o/gc:ˈ	I
    //   279: aload_0
    //   280: new 14	o/gc$ˊ
    //   283: dup
    //   284: aload_1
    //   285: invokespecial 270	o/gc$ˊ:<init>	(Landroid/content/Context;)V
    //   288: putfield 200	o/gc:ᐝ	Lo/gc$ˊ;
    //   291: aload_0
    //   292: new 272	o/ﺕ
    //   295: dup
    //   296: aload_1
    //   297: aload_0
    //   298: aload_0
    //   299: getfield 267	o/gc:ˈ	I
    //   302: invokespecial 275	o/ﺕ:<init>	(Landroid/content/Context;Lo/gc;I)V
    //   305: putfield 277	o/gc:ʼ	Lo/ﺕ;
    //   308: aload_0
    //   309: getfield 277	o/gc:ʼ	Lo/ﺕ;
    //   312: invokeinterface 282 1 0
    //   317: ifne -141 -> 176
    //   320: goto -213 -> 107
    //   323: astore_1
    //   324: aload_1
    //   325: athrow
    //   326: astore_1
    //   327: aload_1
    //   328: athrow
    //   329: iload_3
    //   330: tableswitch	default:+18->348, 1:+-321->9
    //   348: goto -236 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	351	0	this	gc
    //   0	351	1	paramContext	Context
    //   0	351	2	paramGk	gk
    //   43	287	3	i	int
    //   70	175	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   181	243	3	finally
    //   9	13	323	java/lang/Exception
    //   9	13	326	java/lang/Exception
    //   13	22	326	java/lang/Exception
    //   22	27	326	java/lang/Exception
    //   27	32	326	java/lang/Exception
    //   32	37	326	java/lang/Exception
    //   37	44	326	java/lang/Exception
    //   44	49	326	java/lang/Exception
    //   49	57	326	java/lang/Exception
    //   57	62	326	java/lang/Exception
    //   62	67	326	java/lang/Exception
    //   67	72	326	java/lang/Exception
    //   72	78	326	java/lang/Exception
    //   78	88	326	java/lang/Exception
    //   88	99	326	java/lang/Exception
    //   99	106	326	java/lang/Exception
  }
  
  private ContentValues ˊ(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", jf.ˋ(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.ˑ);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", jf.ˋ(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.ـ == null)
      {
        this.ـ = Bitmap.createBitmap(paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, Bitmap.Config.RGB_565);
        this.ᐧ = new Canvas(this.ـ);
        this.ᐨ = new Paint(3);
      }
      this.ᐧ.drawColor(paramInt);
      this.ᐧ.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.ـ.getWidth(), this.ـ.getHeight()), this.ᐨ);
      localContentValues.put("icon_low_res", jf.ˋ(this.ـ));
      return localContentValues;
    }
    finally {}
  }
  
  private static Bitmap ˊ(Cursor paramCursor, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(0);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor)
    {
      for (;;) {}
    }
    return null;
  }
  
  private Drawable ˊ(Resources paramResources, int paramInt)
  {
    for (;;)
    {
      try
      {
        paramResources = paramResources.getDrawableForDensity(paramInt, this.ˈ);
      }
      catch (Resources.NotFoundException paramResources)
      {
        continue;
      }
      paramResources = null;
      if (paramResources != null) {
        return paramResources;
      }
      paramResources = Resources.getSystem();
      paramInt = 17629184;
    }
  }
  
  private static String ˊ(short paramShort1, short paramShort2, int paramInt)
  {
    int i = paramShort1 + 11;
    paramShort1 = 118 - paramInt;
    for (;;)
    {
      short s;
      try
      {
        byte[] arrayOfByte1 = ʹ;
        paramInt = -1;
        s = 139 - paramShort2;
        byte[] arrayOfByte2 = new byte[i];
        paramShort2 = paramShort1;
        paramShort1 = s;
        paramInt += 1;
        arrayOfByte2[paramInt] = ((byte)paramShort2);
        if (paramInt != i - 1) {
          s = arrayOfByte1[paramShort1];
        } else {
          return new String(arrayOfByte2, 0).intern();
        }
      }
      catch (Exception localException)
      {
        throw localException;
      }
      paramShort2 = paramShort2 - s - 2;
      paramShort1 += 1;
    }
  }
  
  private if ˊ(ComponentName paramComponentName, kV paramKV, lf paramLf, boolean paramBoolean1, boolean paramBoolean2)
  {
    lt localLt = new lt(paramComponentName, paramLf);
    if localIf2 = (if)this.ʿ.get(localLt);
    if localIf1;
    if ((localIf2 != null) && ((!localIf2.ˏ) || (paramBoolean2)))
    {
      localIf1 = localIf2;
      if (localIf2.ˊ != null) {}
    }
    else
    {
      localIf2 = this.ʼ.ˊ(localIf2);
      this.ʿ.put(localLt, localIf2);
      localIf2.ˊ = this.ʼ.ˊ(paramComponentName, true);
      localIf1 = localIf2;
      if (localIf2.ˊ == null)
      {
        localIf1 = localIf2;
        if (!ˊ(localLt, localIf2, paramBoolean2))
        {
          if (paramKV != null)
          {
            localIf2.ˊ = this.ʼ.ˊ(paramComponentName, paramKV);
          }
          else
          {
            if (paramBoolean1)
            {
              paramComponentName = ˊ(paramComponentName.getPackageName(), paramLf, false);
              if (paramComponentName != null)
              {
                localIf2.ˊ = paramComponentName.ˊ;
                localIf2.ˋ = paramComponentName.ˋ;
                localIf2.ˎ = paramComponentName.ˎ;
              }
            }
            if (localIf2.ˊ == null) {
              localIf2.ˊ = ˊ(paramLf);
            }
          }
          localIf1 = localIf2;
          if (localIf2.ˊ != null)
          {
            localIf2.ˊ = this.ʽ.ˊ(localIf2.ˊ);
            localIf1 = localIf2;
          }
        }
      }
    }
    if ((TextUtils.isEmpty(localIf1.ˋ)) && (paramKV != null))
    {
      localIf1.ˋ = this.ʼ.ˊ(localLt.ˋ, paramKV.ˎ().toString());
      localIf1.ˎ = this.ˏ.ˊ(localIf1.ˋ, paramLf);
    }
    return localIf1;
  }
  
  /* Error */
  private if ˊ(String paramString, lf paramLf, boolean paramBoolean)
  {
    // Byte code:
    //   0: goto +1180 -> 1180
    //   3: iconst_1
    //   4: istore 5
    //   6: goto +1511 -> 1517
    //   9: sipush 8192
    //   12: istore 5
    //   14: goto +69 -> 83
    //   17: iconst_0
    //   18: istore 5
    //   20: goto +1039 -> 1059
    //   23: aload 10
    //   25: astore 9
    //   27: iload 5
    //   29: tableswitch	default:+19->48, 1:+1842->1871
    //   48: goto +1632 -> 1680
    //   51: iconst_0
    //   52: istore 6
    //   54: goto +639 -> 693
    //   57: aload 11
    //   59: astore 9
    //   61: iload 6
    //   63: tableswitch	default:+17->80, 1:+422->485
    //   80: goto +1794 -> 1874
    //   83: getstatic 112	o/gc:ﹳ	I
    //   86: bipush 37
    //   88: iadd
    //   89: istore 6
    //   91: iload 6
    //   93: sipush 128
    //   96: irem
    //   97: putstatic 114	o/gc:ﾞ	I
    //   100: iload 6
    //   102: iconst_2
    //   103: irem
    //   104: ifeq -53 -> 51
    //   107: goto +1247 -> 1354
    //   110: getstatic 112	o/gc:ﹳ	I
    //   113: bipush 25
    //   115: iadd
    //   116: istore 5
    //   118: iload 5
    //   120: sipush 128
    //   123: irem
    //   124: putstatic 114	o/gc:ﾞ	I
    //   127: iload 5
    //   129: iconst_2
    //   130: irem
    //   131: ifeq +1660 -> 1791
    //   134: goto +229 -> 363
    //   137: getstatic 112	o/gc:ﹳ	I
    //   140: bipush 37
    //   142: iadd
    //   143: istore 6
    //   145: iload 6
    //   147: sipush 128
    //   150: irem
    //   151: putstatic 114	o/gc:ﾞ	I
    //   154: iload 6
    //   156: iconst_2
    //   157: irem
    //   158: ifeq +1693 -> 1851
    //   161: aload 10
    //   163: astore 9
    //   165: goto +320 -> 485
    //   168: aload 10
    //   170: astore 9
    //   172: iload 5
    //   174: lookupswitch	default:+18->192, 23:+1697->1871
    //   192: goto +807 -> 999
    //   195: bipush 9
    //   197: istore 5
    //   199: goto +1523 -> 1722
    //   202: aload_1
    //   203: astore_2
    //   204: iload 5
    //   206: tableswitch	default:+22->228, 0:+734->940, 1:+245->451
    //   228: aload_1
    //   229: astore_2
    //   230: goto +662 -> 892
    //   233: bipush 23
    //   235: istore 5
    //   237: goto -69 -> 168
    //   240: getstatic 112	o/gc:ﹳ	I
    //   243: bipush 105
    //   245: iadd
    //   246: istore 5
    //   248: iload 5
    //   250: sipush 128
    //   253: irem
    //   254: putstatic 114	o/gc:ﾞ	I
    //   257: iload 5
    //   259: iconst_2
    //   260: irem
    //   261: ifeq +1231 -> 1492
    //   264: aload_1
    //   265: astore_2
    //   266: goto +185 -> 451
    //   269: bipush 11
    //   271: istore 5
    //   273: goto +446 -> 719
    //   276: getstatic 114	o/gc:ﾞ	I
    //   279: bipush 119
    //   281: iadd
    //   282: istore 5
    //   284: iload 5
    //   286: sipush 128
    //   289: irem
    //   290: putstatic 112	o/gc:ﹳ	I
    //   293: iload 5
    //   295: iconst_2
    //   296: irem
    //   297: ifne +1423 -> 1720
    //   300: aload_2
    //   301: areturn
    //   302: bipush 97
    //   304: istore 6
    //   306: goto +1516 -> 1822
    //   309: aload 11
    //   311: astore 10
    //   313: iload 5
    //   315: tableswitch	default:+17->332, 0:+74->389
    //   332: goto +1207 -> 1539
    //   335: aload 11
    //   337: astore 12
    //   339: iload 5
    //   341: lookupswitch	default:+19->360, 56:+-332->9
    //   360: goto +914 -> 1274
    //   363: aload 10
    //   365: astore 9
    //   367: new 459	android/content/pm/PackageManager$NameNotFoundException
    //   370: dup
    //   371: ldc_w 461
    //   374: invokespecial 464	android/content/pm/PackageManager$NameNotFoundException:<init>	(Ljava/lang/String;)V
    //   377: athrow
    //   378: bipush 94
    //   380: istore 6
    //   382: iload 7
    //   384: istore 5
    //   386: goto +933 -> 1319
    //   389: aload 10
    //   391: astore 9
    //   393: getstatic 466	o/jf:ᐝ	Z
    //   396: ifeq +22 -> 418
    //   399: aload 10
    //   401: astore 9
    //   403: new 468	o/lf
    //   406: dup
    //   407: invokestatic 474	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   410: invokespecial 477	o/lf:<init>	(Landroid/os/UserHandle;)V
    //   413: astore 11
    //   415: goto +16 -> 431
    //   418: aload 10
    //   420: astore 9
    //   422: new 468	o/lf
    //   425: dup
    //   426: invokespecial 478	o/lf:<init>	()V
    //   429: astore 11
    //   431: aload 10
    //   433: astore 9
    //   435: aload 11
    //   437: aload_2
    //   438: invokevirtual 482	o/lf:equals	(Ljava/lang/Object;)Z
    //   441: istore 8
    //   443: iload 8
    //   445: ifne +34 -> 479
    //   448: goto +804 -> 1252
    //   451: aload_0
    //   452: getfield 222	o/gc:ʿ	Ljava/util/HashMap;
    //   455: aload 13
    //   457: aload_2
    //   458: invokevirtual 402	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   461: pop
    //   462: goto -186 -> 276
    //   465: astore_1
    //   466: aload_1
    //   467: athrow
    //   468: aload 10
    //   470: getfield 394	o/gc$if:ˏ	Z
    //   473: ifne +624 -> 1097
    //   476: goto +903 -> 1379
    //   479: iconst_0
    //   480: istore 5
    //   482: goto +903 -> 1385
    //   485: iload 5
    //   487: ifne +600 -> 1087
    //   490: aload 9
    //   492: astore_1
    //   493: goto +1214 -> 1707
    //   496: aload 12
    //   498: astore 9
    //   500: aload_0
    //   501: getfield 247	o/gc:ι	Landroid/content/pm/PackageManager;
    //   504: astore 10
    //   506: sipush 8192
    //   509: istore 5
    //   511: aload 10
    //   513: astore 9
    //   515: aload 12
    //   517: astore 10
    //   519: goto +241 -> 760
    //   522: iconst_1
    //   523: istore 5
    //   525: goto -216 -> 309
    //   528: getstatic 114	o/gc:ﾞ	I
    //   531: bipush 113
    //   533: iadd
    //   534: istore 5
    //   536: iload 5
    //   538: sipush 128
    //   541: irem
    //   542: putstatic 112	o/gc:ﹳ	I
    //   545: iload 5
    //   547: iconst_2
    //   548: irem
    //   549: ifne +1071 -> 1620
    //   552: goto +135 -> 687
    //   555: aload 10
    //   557: astore 9
    //   559: aload 10
    //   561: aload_0
    //   562: getfield 254	o/gc:ˏ	Lo/lh;
    //   565: aload 12
    //   567: aload_0
    //   568: getfield 247	o/gc:ι	Landroid/content/pm/PackageManager;
    //   571: invokevirtual 488	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   574: aload_2
    //   575: invokevirtual 491	o/lg:ˊ	(Landroid/graphics/drawable/Drawable;Lo/lf;)Landroid/graphics/drawable/Drawable;
    //   578: aload_0
    //   579: getfield 224	o/gc:ͺ	Landroid/content/Context;
    //   582: invokestatic 494	o/jf:ˊ	(Landroid/graphics/drawable/Drawable;Landroid/content/Context;)Landroid/graphics/Bitmap;
    //   585: putfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   588: aload 10
    //   590: astore 9
    //   592: aload 10
    //   594: aload 12
    //   596: aload_0
    //   597: getfield 247	o/gc:ι	Landroid/content/pm/PackageManager;
    //   600: invokevirtual 498	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   603: putfield 422	o/gc$if:ˋ	Ljava/lang/CharSequence;
    //   606: aload 10
    //   608: astore 9
    //   610: aload 10
    //   612: aload_0
    //   613: getfield 254	o/gc:ˏ	Lo/lh;
    //   616: aload 10
    //   618: getfield 422	o/gc$if:ˋ	Ljava/lang/CharSequence;
    //   621: aload_2
    //   622: invokevirtual 457	o/lg:ˊ	(Ljava/lang/CharSequence;Lo/lf;)Ljava/lang/CharSequence;
    //   625: putfield 424	o/gc$if:ˎ	Ljava/lang/CharSequence;
    //   628: aload 10
    //   630: astore 9
    //   632: aload 10
    //   634: iconst_0
    //   635: putfield 394	o/gc$if:ˏ	Z
    //   638: aload 10
    //   640: astore 9
    //   642: aload_0
    //   643: aload_0
    //   644: aload 10
    //   646: getfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   649: aload 10
    //   651: getfield 422	o/gc$if:ˋ	Ljava/lang/CharSequence;
    //   654: invokeinterface 451 1 0
    //   659: aload_0
    //   660: getfield 158	o/gc:ˌ	I
    //   663: invokespecial 500	o/gc:ˊ	(Landroid/graphics/Bitmap;Ljava/lang/String;I)Landroid/content/ContentValues;
    //   666: aload 13
    //   668: getfield 443	o/lt:ˋ	Landroid/content/ComponentName;
    //   671: aload 11
    //   673: aload_0
    //   674: getfield 254	o/gc:ˏ	Lo/lh;
    //   677: aload_2
    //   678: invokevirtual 503	o/lg:ˊ	(Lo/lf;)J
    //   681: invokevirtual 506	o/gc:ˊ	(Landroid/content/ContentValues;Landroid/content/ComponentName;Landroid/content/pm/PackageInfo;J)V
    //   684: goto -547 -> 137
    //   687: iconst_0
    //   688: istore 5
    //   690: goto +605 -> 1295
    //   693: aload 12
    //   695: astore 10
    //   697: iload 6
    //   699: tableswitch	default:+17->716, 1:+798->1497
    //   716: goto -220 -> 496
    //   719: iload 5
    //   721: lookupswitch	default:+27->748, 11:+912->1633, 89:+749->1470
    //   748: aload_1
    //   749: astore_2
    //   750: goto +536 -> 1286
    //   753: bipush 96
    //   755: istore 5
    //   757: goto +965 -> 1722
    //   760: iconst_1
    //   761: istore 6
    //   763: iconst_1
    //   764: istore 7
    //   766: getstatic 110	o/gc:ʹ	[B
    //   769: bipush 29
    //   771: baload
    //   772: i2b
    //   773: sipush 135
    //   776: getstatic 110	o/gc:ʹ	[B
    //   779: sipush 135
    //   782: baload
    //   783: i2b
    //   784: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   787: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   790: astore 11
    //   792: getstatic 110	o/gc:ʹ	[B
    //   795: bipush 9
    //   797: baload
    //   798: i2b
    //   799: istore 4
    //   801: aload 11
    //   803: iload 4
    //   805: iload 4
    //   807: bipush 100
    //   809: ior
    //   810: i2s
    //   811: getstatic 110	o/gc:ʹ	[B
    //   814: bipush 74
    //   816: baload
    //   817: i2b
    //   818: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   821: iconst_2
    //   822: anewarray 229	java/lang/Class
    //   825: dup
    //   826: iconst_0
    //   827: ldc_w 375
    //   830: aastore
    //   831: dup
    //   832: iconst_1
    //   833: getstatic 512	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   836: aastore
    //   837: invokevirtual 237	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   840: aload 9
    //   842: iconst_2
    //   843: anewarray 4	java/lang/Object
    //   846: dup
    //   847: iconst_0
    //   848: aload_1
    //   849: aastore
    //   850: dup
    //   851: iconst_1
    //   852: iload 5
    //   854: invokestatic 516	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   857: aastore
    //   858: invokevirtual 243	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   861: checkcast 518	android/content/pm/PackageInfo
    //   864: astore 11
    //   866: aload 10
    //   868: astore 9
    //   870: aload 11
    //   872: getfield 522	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   875: astore 12
    //   877: aload 10
    //   879: astore_1
    //   880: iload 6
    //   882: istore 5
    //   884: aload 12
    //   886: ifnull +1015 -> 1901
    //   889: goto -511 -> 378
    //   892: iconst_0
    //   893: istore 5
    //   895: aload_2
    //   896: astore_1
    //   897: goto -695 -> 202
    //   900: aload_1
    //   901: astore_2
    //   902: iload 5
    //   904: tableswitch	default:+24->928, 0:+361->1265, 1:+-453->451
    //   928: aload_1
    //   929: astore_2
    //   930: goto +314 -> 1244
    //   933: bipush 31
    //   935: istore 5
    //   937: goto -769 -> 168
    //   940: getstatic 112	o/gc:ﹳ	I
    //   943: bipush 89
    //   945: iadd
    //   946: istore 5
    //   948: iload 5
    //   950: sipush 128
    //   953: irem
    //   954: putstatic 114	o/gc:ﾞ	I
    //   957: aload_1
    //   958: astore_2
    //   959: iload 5
    //   961: iconst_2
    //   962: irem
    //   963: ifeq +323 -> 1286
    //   966: goto -697 -> 269
    //   969: iload_3
    //   970: ifeq -217 -> 753
    //   973: goto -778 -> 195
    //   976: iload 5
    //   978: tableswitch	default:+18->996, 0:+21->999
    //   996: goto -27 -> 969
    //   999: iload_3
    //   1000: ifeq +751 -> 1751
    //   1003: goto -986 -> 17
    //   1006: new 11	o/gc$if
    //   1009: dup
    //   1010: invokespecial 523	o/gc$if:<init>	()V
    //   1013: astore 10
    //   1015: iconst_1
    //   1016: istore 5
    //   1018: aload_0
    //   1019: aload 13
    //   1021: aload 10
    //   1023: iload_3
    //   1024: invokespecial 408	o/gc:ˊ	(Lo/lt;Lo/gc$if;Z)Z
    //   1027: istore 8
    //   1029: iload 8
    //   1031: ifeq -729 -> 302
    //   1034: goto +579 -> 1613
    //   1037: iload 5
    //   1039: lookupswitch	default:+17->1056, 80:+-571->468
    //   1056: goto +362 -> 1418
    //   1059: aload 10
    //   1061: astore 9
    //   1063: iload 5
    //   1065: tableswitch	default:+19->1084, 0:+806->1871
    //   1084: goto -556 -> 528
    //   1087: bipush 55
    //   1089: istore 5
    //   1091: aload 9
    //   1093: astore_1
    //   1094: goto +9 -> 1103
    //   1097: iconst_0
    //   1098: istore 5
    //   1100: goto -1077 -> 23
    //   1103: aload_1
    //   1104: astore 9
    //   1106: iload 5
    //   1108: lookupswitch	default:+28->1136, 45:+763->1871, 55:+686->1794
    //   1136: goto +571 -> 1707
    //   1139: new 11	o/gc$if
    //   1142: dup
    //   1143: invokespecial 523	o/gc$if:<init>	()V
    //   1146: astore 11
    //   1148: iconst_1
    //   1149: istore 5
    //   1151: aload_0
    //   1152: aload 13
    //   1154: aload 11
    //   1156: iload_3
    //   1157: invokespecial 408	o/gc:ˊ	(Lo/lt;Lo/gc$if;Z)Z
    //   1160: istore 8
    //   1162: iload 8
    //   1164: ifeq +300 -> 1464
    //   1167: goto +440 -> 1607
    //   1170: astore_1
    //   1171: aload 10
    //   1173: astore 9
    //   1175: aload_1
    //   1176: invokevirtual 129	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   1179: athrow
    //   1180: aload_1
    //   1181: aload_2
    //   1182: invokestatic 526	o/gc:ˏ	(Ljava/lang/String;Lo/lf;)Lo/lt;
    //   1185: astore 13
    //   1187: aload_0
    //   1188: getfield 222	o/gc:ʿ	Ljava/util/HashMap;
    //   1191: astore 9
    //   1193: aload 9
    //   1195: aload 13
    //   1197: invokevirtual 391	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1200: astore 9
    //   1202: aload 9
    //   1204: checkcast 11	o/gc$if
    //   1207: astore 10
    //   1209: aload 10
    //   1211: ifnonnull -1208 -> 3
    //   1214: goto +66 -> 1280
    //   1217: getstatic 114	o/gc:ﾞ	I
    //   1220: bipush 107
    //   1222: iadd
    //   1223: istore 5
    //   1225: iload 5
    //   1227: sipush 128
    //   1230: irem
    //   1231: putstatic 112	o/gc:ﹳ	I
    //   1234: iload 5
    //   1236: iconst_2
    //   1237: irem
    //   1238: ifne +173 -> 1411
    //   1241: goto +568 -> 1809
    //   1244: iconst_0
    //   1245: istore 5
    //   1247: aload_2
    //   1248: astore_1
    //   1249: goto -349 -> 900
    //   1252: iconst_1
    //   1253: istore 5
    //   1255: goto +130 -> 1385
    //   1258: aload 11
    //   1260: astore 10
    //   1262: goto +235 -> 1497
    //   1265: aload_1
    //   1266: astore_2
    //   1267: iload_3
    //   1268: ifeq -376 -> 892
    //   1271: goto +486 -> 1757
    //   1274: iconst_0
    //   1275: istore 5
    //   1277: goto +156 -> 1433
    //   1280: iconst_0
    //   1281: istore 5
    //   1283: goto +234 -> 1517
    //   1286: bipush 89
    //   1288: istore 5
    //   1290: aload_2
    //   1291: astore_1
    //   1292: goto -573 -> 719
    //   1295: iload 5
    //   1297: tableswitch	default:+19->1316, 0:+-158->1139
    //   1316: goto -310 -> 1006
    //   1319: iload 6
    //   1321: lookupswitch	default:+27->1348, 91:+-1211->110, 94:+-766->555
    //   1348: aload 10
    //   1350: astore_1
    //   1351: goto +550 -> 1901
    //   1354: iconst_1
    //   1355: istore 6
    //   1357: goto -664 -> 693
    //   1360: iconst_0
    //   1361: istore 5
    //   1363: goto -878 -> 485
    //   1366: bipush 56
    //   1368: istore 5
    //   1370: goto -1035 -> 335
    //   1373: iconst_1
    //   1374: istore 5
    //   1376: goto -1041 -> 335
    //   1379: iconst_1
    //   1380: istore 5
    //   1382: goto -1359 -> 23
    //   1385: aload 10
    //   1387: astore 12
    //   1389: iload 5
    //   1391: tableswitch	default:+17->1408, 1:+-1382->9
    //   1408: goto -191 -> 1217
    //   1411: bipush 39
    //   1413: istore 5
    //   1415: goto +348 -> 1763
    //   1418: aload 10
    //   1420: getfield 394	o/gc$if:ˏ	Z
    //   1423: istore 8
    //   1425: iload 8
    //   1427: ifne -494 -> 933
    //   1430: goto -1197 -> 233
    //   1433: getstatic 112	o/gc:ﹳ	I
    //   1436: bipush 87
    //   1438: iadd
    //   1439: istore 6
    //   1441: iload 6
    //   1443: sipush 128
    //   1446: irem
    //   1447: putstatic 114	o/gc:ﾞ	I
    //   1450: iload 6
    //   1452: iconst_2
    //   1453: irem
    //   1454: ifeq -196 -> 1258
    //   1457: aload 11
    //   1459: astore 10
    //   1461: goto +36 -> 1497
    //   1464: iconst_0
    //   1465: istore 6
    //   1467: goto -1410 -> 57
    //   1470: aload_1
    //   1471: aload_0
    //   1472: getfield 429	o/gc:ʽ	Lo/צּ$if;
    //   1475: aload_1
    //   1476: getfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   1479: invokeinterface 434 2 0
    //   1484: putfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   1487: aload_1
    //   1488: astore_2
    //   1489: goto -1038 -> 451
    //   1492: aload_1
    //   1493: astore_2
    //   1494: goto -1043 -> 451
    //   1497: aload 10
    //   1499: astore 9
    //   1501: aload_0
    //   1502: getfield 247	o/gc:ι	Landroid/content/pm/PackageManager;
    //   1505: astore 11
    //   1507: aload 11
    //   1509: astore 9
    //   1511: goto -751 -> 760
    //   1514: astore_1
    //   1515: aload_1
    //   1516: athrow
    //   1517: iload 5
    //   1519: tableswitch	default:+17->1536, 0:+-380->1139
    //   1536: goto +117 -> 1653
    //   1539: aload 11
    //   1541: astore 9
    //   1543: getstatic 466	o/jf:ᐝ	Z
    //   1546: ifeq +22 -> 1568
    //   1549: aload 11
    //   1551: astore 9
    //   1553: new 468	o/lf
    //   1556: dup
    //   1557: invokestatic 474	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   1560: invokespecial 477	o/lf:<init>	(Landroid/os/UserHandle;)V
    //   1563: astore 10
    //   1565: goto +16 -> 1581
    //   1568: aload 11
    //   1570: astore 9
    //   1572: new 468	o/lf
    //   1575: dup
    //   1576: invokespecial 478	o/lf:<init>	()V
    //   1579: astore 10
    //   1581: aload 11
    //   1583: astore 9
    //   1585: aload 10
    //   1587: aload_2
    //   1588: invokevirtual 482	o/lf:equals	(Ljava/lang/Object;)Z
    //   1591: istore 8
    //   1593: iload 8
    //   1595: ifne -222 -> 1373
    //   1598: goto -232 -> 1366
    //   1601: iconst_0
    //   1602: istore 5
    //   1604: goto -628 -> 976
    //   1607: iconst_1
    //   1608: istore 6
    //   1610: goto -1553 -> 57
    //   1613: bipush 25
    //   1615: istore 6
    //   1617: goto +205 -> 1822
    //   1620: iconst_1
    //   1621: istore 5
    //   1623: goto -328 -> 1295
    //   1626: bipush 24
    //   1628: istore 5
    //   1630: goto -593 -> 1037
    //   1633: aload_1
    //   1634: aload_0
    //   1635: getfield 429	o/gc:ʽ	Lo/צּ$if;
    //   1638: aload_1
    //   1639: getfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   1642: invokeinterface 434 2 0
    //   1647: putfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   1650: goto -1410 -> 240
    //   1653: getstatic 112	o/gc:ﹳ	I
    //   1656: bipush 57
    //   1658: iadd
    //   1659: istore 5
    //   1661: iload 5
    //   1663: sipush 128
    //   1666: irem
    //   1667: putstatic 114	o/gc:ﾞ	I
    //   1670: iload 5
    //   1672: iconst_2
    //   1673: irem
    //   1674: ifeq -48 -> 1626
    //   1677: goto +181 -> 1858
    //   1680: getstatic 114	o/gc:ﾞ	I
    //   1683: bipush 83
    //   1685: iadd
    //   1686: istore 5
    //   1688: iload 5
    //   1690: sipush 128
    //   1693: irem
    //   1694: putstatic 112	o/gc:ﹳ	I
    //   1697: iload 5
    //   1699: iconst_2
    //   1700: irem
    //   1701: ifne +13 -> 1714
    //   1704: goto -103 -> 1601
    //   1707: bipush 45
    //   1709: istore 5
    //   1711: goto -608 -> 1103
    //   1714: iconst_1
    //   1715: istore 5
    //   1717: goto -741 -> 976
    //   1720: aload_2
    //   1721: areturn
    //   1722: aload 10
    //   1724: astore 9
    //   1726: iload 5
    //   1728: lookupswitch	default:+20->1748, 9:+143->1871
    //   1748: goto -609 -> 1139
    //   1751: iconst_1
    //   1752: istore 5
    //   1754: goto -695 -> 1059
    //   1757: iconst_1
    //   1758: istore 5
    //   1760: goto -1558 -> 202
    //   1763: aload 10
    //   1765: astore 11
    //   1767: iload 5
    //   1769: lookupswitch	default:+19->1788, 64:+-495->1274
    //   1788: goto +77 -> 1865
    //   1791: goto -1428 -> 363
    //   1794: aload_1
    //   1795: astore_2
    //   1796: aload_1
    //   1797: getfield 396	o/gc$if:ˊ	Landroid/graphics/Bitmap;
    //   1800: ifnonnull -556 -> 1244
    //   1803: iconst_1
    //   1804: istore 5
    //   1806: goto -906 -> 900
    //   1809: bipush 64
    //   1811: istore 5
    //   1813: goto -50 -> 1763
    //   1816: iconst_0
    //   1817: istore 5
    //   1819: goto -1510 -> 309
    //   1822: aload 10
    //   1824: astore 9
    //   1826: iload 6
    //   1828: lookupswitch	default:+20->1848, 25:+-1343->485
    //   1848: goto -1459 -> 389
    //   1851: aload 10
    //   1853: astore 9
    //   1855: goto -1370 -> 485
    //   1858: bipush 80
    //   1860: istore 5
    //   1862: goto -825 -> 1037
    //   1865: iconst_0
    //   1866: istore 5
    //   1868: goto -371 -> 1497
    //   1871: aload 9
    //   1873: areturn
    //   1874: getstatic 112	o/gc:ﹳ	I
    //   1877: bipush 109
    //   1879: iadd
    //   1880: istore 5
    //   1882: iload 5
    //   1884: sipush 128
    //   1887: irem
    //   1888: putstatic 114	o/gc:ﾞ	I
    //   1891: iload 5
    //   1893: iconst_2
    //   1894: irem
    //   1895: ifeq -1373 -> 522
    //   1898: goto -82 -> 1816
    //   1901: bipush 91
    //   1903: istore 6
    //   1905: aload_1
    //   1906: astore 10
    //   1908: goto -589 -> 1319
    //   1911: astore_1
    //   1912: goto -552 -> 1360
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1915	0	this	gc
    //   0	1915	1	paramString	String
    //   0	1915	2	paramLf	lf
    //   0	1915	3	paramBoolean	boolean
    //   799	11	4	s	short
    //   4	1891	5	i	int
    //   52	1852	6	j	int
    //   382	383	7	k	int
    //   441	1153	8	bool	boolean
    //   25	1847	9	localObject1	Object
    //   23	1884	10	localObject2	Object
    //   57	1709	11	localObject3	Object
    //   337	1051	12	localObject4	Object
    //   455	741	13	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   110	118	465	java/lang/Exception
    //   118	127	465	java/lang/Exception
    //   1139	1148	465	java/lang/Exception
    //   1187	1193	465	java/lang/Exception
    //   1193	1202	465	java/lang/Exception
    //   1202	1209	465	java/lang/Exception
    //   766	866	1170	finally
    //   118	127	1514	java/lang/Exception
    //   1006	1015	1514	java/lang/Exception
    //   1018	1029	1514	java/lang/Exception
    //   1151	1162	1514	java/lang/Exception
    //   1180	1187	1514	java/lang/Exception
    //   1418	1425	1514	java/lang/Exception
    //   1653	1661	1514	java/lang/Exception
    //   1661	1670	1514	java/lang/Exception
    //   367	378	1911	android/content/pm/PackageManager$NameNotFoundException
    //   393	399	1911	android/content/pm/PackageManager$NameNotFoundException
    //   403	415	1911	android/content/pm/PackageManager$NameNotFoundException
    //   422	431	1911	android/content/pm/PackageManager$NameNotFoundException
    //   435	443	1911	android/content/pm/PackageManager$NameNotFoundException
    //   500	506	1911	android/content/pm/PackageManager$NameNotFoundException
    //   559	588	1911	android/content/pm/PackageManager$NameNotFoundException
    //   592	606	1911	android/content/pm/PackageManager$NameNotFoundException
    //   610	628	1911	android/content/pm/PackageManager$NameNotFoundException
    //   632	638	1911	android/content/pm/PackageManager$NameNotFoundException
    //   642	684	1911	android/content/pm/PackageManager$NameNotFoundException
    //   870	877	1911	android/content/pm/PackageManager$NameNotFoundException
    //   1175	1180	1911	android/content/pm/PackageManager$NameNotFoundException
    //   1501	1507	1911	android/content/pm/PackageManager$NameNotFoundException
    //   1543	1549	1911	android/content/pm/PackageManager$NameNotFoundException
    //   1553	1565	1911	android/content/pm/PackageManager$NameNotFoundException
    //   1572	1581	1911	android/content/pm/PackageManager$NameNotFoundException
    //   1585	1593	1911	android/content/pm/PackageManager$NameNotFoundException
  }
  
  private boolean ˊ(lt paramLt, if paramIf, boolean paramBoolean)
  {
    Object localObject2 = this.ᐝ.getReadableDatabase();
    Object localObject1;
    if (paramBoolean) {
      localObject1 = "icon_low_res";
    } else {
      localObject1 = "icon";
    }
    String str1 = paramLt.ˋ.flattenToString();
    String str2 = Long.toString(this.ˏ.ˊ(paramLt.ˎ));
    localObject2 = ((SQLiteDatabase)localObject2).query("icons", new String[] { localObject1, "label" }, "componentName = ? AND profileId = ?", new String[] { str1, str2 }, null, null, null);
    for (;;)
    {
      try
      {
        if (((Cursor)localObject2).moveToNext())
        {
          if (!paramBoolean) {
            break label237;
          }
          localObject1 = this.ˍ;
          paramIf.ˊ = ˊ((Cursor)localObject2, (BitmapFactory.Options)localObject1);
          paramIf.ˏ = paramBoolean;
          paramIf.ˋ = this.ʼ.ˊ(paramLt.ˋ, ((Cursor)localObject2).getString(1));
          if (TextUtils.isEmpty(paramIf.ˋ))
          {
            paramIf.ˋ = "";
            paramIf.ˎ = "";
          }
          else
          {
            paramIf.ˎ = this.ˏ.ˊ(paramIf.ˋ, paramLt.ˎ);
          }
          return true;
        }
      }
      finally
      {
        ((Cursor)localObject2).close();
      }
      return false;
      label237:
      localObject1 = null;
    }
  }
  
  private void ˎ(String paramString, lf paramLf)
  {
    for (;;)
    {
      try
      {
        HashSet localHashSet = new HashSet();
        Iterator localIterator = this.ʿ.keySet().iterator();
        if (localIterator.hasNext())
        {
          lt localLt = (lt)localIterator.next();
          if ((localLt.ˋ.getPackageName().equals(paramString)) && (localLt.ˎ.equals(paramLf))) {
            localHashSet.add(localLt);
          }
        }
        else
        {
          paramString = localHashSet.iterator();
          if (paramString.hasNext())
          {
            paramLf = (lt)paramString.next();
            this.ʿ.remove(paramLf);
          }
          else
          {
            return;
          }
        }
      }
      finally {}
    }
  }
  
  private static lt ˏ(String paramString, lf paramLf)
  {
    return new lt(new ComponentName(paramString, paramString + "."), paramLf);
  }
  
  public final int ˊ()
  {
    synchronized (this.ʿ)
    {
      int i = this.ʿ.size();
      this.ʿ.clear();
      return i;
    }
  }
  
  final ContentValues ˊ(kV paramKV, boolean paramBoolean)
  {
    Object localObject2 = new lt(paramKV.ˊ(), paramKV.ˋ());
    Object localObject1 = null;
    if (!paramBoolean)
    {
      localObject2 = (if)this.ʿ.get(localObject2);
      if ((localObject2 != null) && (!((if)localObject2).ˏ))
      {
        localObject1 = localObject2;
        if (((if)localObject2).ˊ != null) {}
      }
      else
      {
        localObject1 = null;
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject1 = new if();
      ((if)localObject1).ˊ = this.ʼ.ˊ(paramKV.ˊ(), paramKV);
      localObject2 = localObject1;
      if (((if)localObject1).ˊ != null)
      {
        ((if)localObject1).ˊ = this.ʽ.ˊ(((if)localObject1).ˊ);
        localObject2 = localObject1;
      }
    }
    ((if)localObject2).ˋ = paramKV.ˎ();
    ((if)localObject2).ˎ = this.ˏ.ˊ(((if)localObject2).ˋ, paramKV.ˋ());
    this.ʿ.put(new lt(paramKV.ˊ(), paramKV.ˋ()), localObject2);
    return ˊ(((if)localObject2).ˊ, ((if)localObject2).ˋ.toString(), 0);
  }
  
  public final Bitmap ˊ(Intent paramIntent, lf paramLf)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramIntent = ˊ(paramLf);
        return paramIntent;
      }
      paramIntent = ˊ(localComponentName, this.ʾ.ˊ(paramIntent, paramLf), paramLf, true, false).ˊ;
      return paramIntent;
    }
    finally {}
  }
  
  public final Bitmap ˊ(lf paramLf)
  {
    try
    {
      if (!this.ˋ.containsKey(paramLf))
      {
        HashMap localHashMap = this.ˋ;
        Drawable localDrawable = ˊ(Resources.getSystem(), 17629184);
        localDrawable = this.ˏ.ˊ(localDrawable, paramLf);
        Bitmap localBitmap = Bitmap.createBitmap(Math.max(localDrawable.getIntrinsicWidth(), 1), Math.max(localDrawable.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        localDrawable.setBounds(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
        localDrawable.draw(localCanvas);
        localCanvas.setBitmap(null);
        localHashMap.put(paramLf, localBitmap);
      }
      paramLf = (Bitmap)this.ˋ.get(paramLf);
      return paramLf;
    }
    finally {}
  }
  
  public final Drawable ˊ(ActivityInfo paramActivityInfo)
  {
    try
    {
      localResources = this.ι.getResourcesForApplication(paramActivityInfo.applicationInfo);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Resources localResources;
      int i;
      for (;;) {}
    }
    localResources = null;
    if (localResources != null)
    {
      i = paramActivityInfo.getIconResource();
      if (i != 0) {
        return ˊ(localResources, i);
      }
    }
    return ˊ(Resources.getSystem(), 17629184);
  }
  
  public final Drawable ˊ(String paramString, int paramInt)
  {
    try
    {
      paramString = this.ι.getResourcesForApplication(paramString);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = null;
    if ((paramString != null) && (paramInt != 0)) {
      return ˊ(paramString, paramInt);
    }
    return ˊ(Resources.getSystem(), 17629184);
  }
  
  public final If ˊ(BubbleTextView paramBubbleTextView, gn paramGn)
  {
    paramBubbleTextView = new gd(this, paramGn, paramBubbleTextView);
    this.ʻ.post(paramBubbleTextView);
    return new If(paramBubbleTextView, this.ʻ);
  }
  
  /* Error */
  public final void ˊ(ComponentName paramComponentName, Bitmap paramBitmap, String paramString, long paramLong, gk paramGk)
  {
    // Byte code:
    //   0: goto +167 -> 167
    //   3: goto +93 -> 96
    //   6: aload_0
    //   7: aload_2
    //   8: aload 6
    //   10: getfield 695	o/gk:ι	I
    //   13: aload 6
    //   15: getfield 695	o/gk:ι	I
    //   18: iconst_1
    //   19: invokestatic 319	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   22: aload_3
    //   23: iconst_0
    //   24: invokespecial 500	o/gc:ˊ	(Landroid/graphics/Bitmap;Ljava/lang/String;I)Landroid/content/ContentValues;
    //   27: astore_2
    //   28: aload_2
    //   29: ldc_w 697
    //   32: aload_1
    //   33: invokevirtual 533	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   36: invokevirtual 302	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: aload_2
    //   40: ldc_w 699
    //   43: lload 4
    //   45: invokestatic 702	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   48: invokevirtual 705	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   51: aload_0
    //   52: getfield 200	o/gc:ᐝ	Lo/gc$ˊ;
    //   55: invokevirtual 204	o/gc$ˊ:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   58: ldc_w 543
    //   61: aconst_null
    //   62: aload_2
    //   63: iconst_5
    //   64: invokevirtual 709	android/database/sqlite/SQLiteDatabase:insertWithOnConflict	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    //   67: pop2
    //   68: return
    //   69: getstatic 112	o/gc:ﹳ	I
    //   72: bipush 121
    //   74: iadd
    //   75: istore 7
    //   77: iload 7
    //   79: sipush 128
    //   82: irem
    //   83: putstatic 114	o/gc:ﾞ	I
    //   86: iload 7
    //   88: iconst_2
    //   89: irem
    //   90: ifne +6 -> 96
    //   93: goto -90 -> 3
    //   96: getstatic 110	o/gc:ʹ	[B
    //   99: iconst_2
    //   100: baload
    //   101: iconst_1
    //   102: isub
    //   103: i2b
    //   104: getstatic 110	o/gc:ʹ	[B
    //   107: bipush 21
    //   109: baload
    //   110: i2s
    //   111: getstatic 110	o/gc:ʹ	[B
    //   114: sipush 135
    //   117: baload
    //   118: i2b
    //   119: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   122: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   125: getstatic 110	o/gc:ʹ	[B
    //   128: bipush 47
    //   130: baload
    //   131: i2b
    //   132: bipush 90
    //   134: getstatic 110	o/gc:ʹ	[B
    //   137: bipush 74
    //   139: baload
    //   140: i2b
    //   141: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   144: aconst_null
    //   145: invokevirtual 237	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   148: aload 8
    //   150: aconst_null
    //   151: invokevirtual 243	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   154: checkcast 245	android/content/pm/PackageManager
    //   157: astore 8
    //   159: aload 8
    //   161: aload_1
    //   162: invokevirtual 713	android/content/pm/PackageManager:getActivityIcon	(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;
    //   165: pop
    //   166: return
    //   167: aload_0
    //   168: getfield 224	o/gc:ͺ	Landroid/content/Context;
    //   171: astore 8
    //   173: goto -104 -> 69
    //   176: astore 8
    //   178: aload 8
    //   180: invokevirtual 129	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   183: athrow
    //   184: astore 8
    //   186: goto -180 -> 6
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	this	gc
    //   0	189	1	paramComponentName	ComponentName
    //   0	189	2	paramBitmap	Bitmap
    //   0	189	3	paramString	String
    //   0	189	4	paramLong	long
    //   0	189	6	paramGk	gk
    //   75	15	7	i	int
    //   148	24	8	localObject1	Object
    //   176	3	8	localObject2	Object
    //   184	1	8	localNameNotFoundException	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   96	159	176	finally
    //   159	166	184	android/content/pm/PackageManager$NameNotFoundException
    //   167	173	184	android/content/pm/PackageManager$NameNotFoundException
    //   178	184	184	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public final void ˊ(ComponentName paramComponentName, lf paramLf)
  {
    try
    {
      this.ʿ.remove(new lt(paramComponentName, paramLf));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public final void ˊ(ComponentName paramComponentName, lf paramLf, Bitmap paramBitmap)
  {
    try
    {
      lt localLt = new lt(paramComponentName, paramLf);
      if localIf = (if)this.ʿ.get(localLt);
      paramLf = localIf;
      if (localIf == null) {
        paramLf = new if();
      }
      this.ʿ.put(localLt, paramLf);
      paramLf.ˊ = paramBitmap;
      paramLf.ˏ = false;
      new StringBuilder("setCacheEntry()[icon]: for ").append(paramComponentName.getPackageName());
      return;
    }
    finally {}
  }
  
  public final void ˊ(ComponentName paramComponentName, lf paramLf, CharSequence paramCharSequence)
  {
    try
    {
      new StringBuilder("setCacheEntry()[title]:").append(paramCharSequence).append(" for ").append(paramComponentName.getPackageName());
      if (paramCharSequence == null) {
        return;
      }
      lt localLt = new lt(paramComponentName, paramLf);
      if localIf = (if)this.ʿ.get(localLt);
      paramComponentName = localIf;
      if (localIf == null) {
        paramComponentName = new if();
      }
      this.ʿ.put(localLt, paramComponentName);
      paramComponentName.ˋ = paramCharSequence;
      paramComponentName.ˎ = this.ˏ.ˊ(paramComponentName.ˋ, paramLf);
      return;
    }
    finally {}
  }
  
  final void ˊ(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    if (!this.ʼ.ˊ(paramComponentName)) {
      return;
    }
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(Class.forName(ˊ((byte)-ʹ[23], (short)-ʹ[46], (byte)ʹ[''])).getField(ˊ((byte)ʹ[9], (short)(ʹ[3] - 1), (byte)ʹ[70])).getLong(paramPackageInfo)));
    paramComponentName = Class.forName(ˊ((byte)-ʹ[23], (short)-ʹ[46], (byte)ʹ['']));
    short s1 = (byte)ʹ[28];
    short s2 = s1;
    paramContentValues.put("version", Integer.valueOf(paramComponentName.getField(ˊ(s1, s2, (byte)s2)).getInt(paramPackageInfo)));
    this.ᐝ.getWritableDatabase().insertWithOnConflict("icons", null, paramContentValues, 5);
  }
  
  public final void ˊ(String paramString)
  {
    for (;;)
    {
      try
      {
        Object localObject = new HashSet();
        Iterator localIterator = this.ʿ.keySet().iterator();
        if (localIterator.hasNext())
        {
          lt localLt = (lt)localIterator.next();
          if (localLt.ˋ.getPackageName().equals(paramString)) {
            ((HashSet)localObject).add(localLt);
          }
        }
        else
        {
          paramString = ((HashSet)localObject).iterator();
          if (paramString.hasNext())
          {
            localObject = (lt)paramString.next();
            ˊ(((lt)localObject).ˋ.getPackageName(), ((lt)localObject).ˎ);
          }
          else
          {
            return;
          }
        }
      }
      finally {}
    }
  }
  
  public final void ˊ(String paramString, lf paramLf)
  {
    try
    {
      ˎ(paramString, paramLf);
      long l = this.ˏ.ˊ(paramLf);
      this.ᐝ.getWritableDatabase().delete("icons", "componentName LIKE ? AND profileId = ?", new String[] { paramString + "/%", Long.toString(l) });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public final void ˊ(String paramString, lf paramLf, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      ˎ(paramString, paramLf);
      lt localLt = ˏ(paramString, paramLf);
      paramLf = (if)this.ʿ.get(localLt);
      paramString = paramLf;
      if (paramLf == null)
      {
        paramString = new if();
        this.ʿ.put(localLt, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.ˋ = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.ˊ = jf.ˊ(paramBitmap, this.ͺ);
      }
      return;
    }
    finally {}
  }
  
  public final void ˊ(String paramString, lf paramLf, boolean paramBoolean, lq paramLq)
  {
    try
    {
      if localIf = ˊ(paramString, paramLf, paramBoolean);
      if (localIf.ˊ == null) {
        paramString = ˊ(paramLf);
      } else {
        paramString = localIf.ˊ;
      }
      paramLq.ˊ = paramString;
      paramLq.ﹳ = jf.ˊ(localIf.ˋ);
      paramLq.ˋ = localIf.ˏ;
      paramLq.ﾞ = localIf.ˎ;
      return;
    }
    finally {}
  }
  
  /* Error */
  public final void ˊ(Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 141	o/gc:ʻ	Landroid/os/Handler;
    //   4: getstatic 119	o/gc:ˊ	Ljava/lang/Object;
    //   7: invokevirtual 783	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: invokestatic 178	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   14: invokevirtual 182	java/util/Locale:toString	()Ljava/lang/String;
    //   17: putfield 184	o/gc:ˑ	Ljava/lang/String;
    //   20: aload_0
    //   21: getfield 254	o/gc:ˏ	Lo/lh;
    //   24: invokevirtual 786	o/lg:ˋ	()Ljava/util/List;
    //   27: invokeinterface 789 1 0
    //   32: astore 18
    //   34: aload 18
    //   36: invokeinterface 584 1 0
    //   41: ifeq +2302 -> 2343
    //   44: aload 18
    //   46: invokeinterface 588 1 0
    //   51: checkcast 468	o/lf
    //   54: astore 19
    //   56: aload_0
    //   57: getfield 261	o/gc:ʾ	Lo/kY;
    //   60: aconst_null
    //   61: aload 19
    //   63: invokevirtual 792	o/kY:ˊ	(Ljava/lang/String;Lo/lf;)Ljava/util/List;
    //   66: astore 21
    //   68: aload 21
    //   70: ifnull +13 -> 83
    //   73: aload 21
    //   75: invokeinterface 794 1 0
    //   80: ifeq +4 -> 84
    //   83: return
    //   84: getstatic 466	o/jf:ᐝ	Z
    //   87: ifeq +18 -> 105
    //   90: new 468	o/lf
    //   93: dup
    //   94: invokestatic 474	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   97: invokespecial 477	o/lf:<init>	(Landroid/os/UserHandle;)V
    //   100: astore 15
    //   102: goto +12 -> 114
    //   105: new 468	o/lf
    //   108: dup
    //   109: invokespecial 478	o/lf:<init>	()V
    //   112: astore 15
    //   114: aload 15
    //   116: aload 19
    //   118: invokevirtual 482	o/lf:equals	(Ljava/lang/Object;)Z
    //   121: ifeq +9 -> 130
    //   124: aload_1
    //   125: astore 16
    //   127: goto +8 -> 135
    //   130: invokestatic 799	java/util/Collections:emptySet	()Ljava/util/Set;
    //   133: astore 16
    //   135: goto +683 -> 818
    //   138: getstatic 114	o/gc:ﾞ	I
    //   141: bipush 43
    //   143: iadd
    //   144: istore 4
    //   146: iload 4
    //   148: sipush 128
    //   151: irem
    //   152: putstatic 112	o/gc:ﹳ	I
    //   155: iload 4
    //   157: iconst_2
    //   158: irem
    //   159: ifne +1991 -> 2150
    //   162: goto +1731 -> 1893
    //   165: aload 16
    //   167: astore 15
    //   169: iload 4
    //   171: tableswitch	default:+17->188, 0:+298->469
    //   188: goto +1546 -> 1734
    //   191: aload 16
    //   193: astore 17
    //   195: iload 4
    //   197: tableswitch	default:+19->216, 0:+1875->2072
    //   216: goto +1423 -> 1639
    //   219: getstatic 112	o/gc:ﹳ	I
    //   222: bipush 101
    //   224: iadd
    //   225: istore 4
    //   227: iload 4
    //   229: sipush 128
    //   232: irem
    //   233: putstatic 114	o/gc:ﾞ	I
    //   236: iload 4
    //   238: iconst_2
    //   239: irem
    //   240: ifeq +1393 -> 1633
    //   243: goto +646 -> 889
    //   246: aload_0
    //   247: aload 24
    //   249: aload 19
    //   251: invokevirtual 801	o/gc:ˊ	(Landroid/content/ComponentName;Lo/lf;)V
    //   254: aload 23
    //   256: aload 22
    //   258: iload 8
    //   260: invokeinterface 803 2 0
    //   265: invokestatic 516	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   268: invokevirtual 592	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   271: pop
    //   272: goto +1714 -> 1986
    //   275: bipush 67
    //   277: istore 4
    //   279: goto +72 -> 351
    //   282: iconst_0
    //   283: istore 4
    //   285: goto +1874 -> 2159
    //   288: aload 25
    //   290: ifnull +1689 -> 1979
    //   293: goto +295 -> 588
    //   296: goto +817 -> 1113
    //   299: iload 4
    //   301: tableswitch	default:+19->320, 1:+773->1074
    //   320: goto +667 -> 987
    //   323: iload 4
    //   325: tableswitch	default:+19->344, 1:+168->493
    //   344: aload 16
    //   346: astore 15
    //   348: goto +1755 -> 2103
    //   351: iload 4
    //   353: lookupswitch	default:+19->372, 84:+322->675
    //   372: goto +230 -> 602
    //   375: iload 4
    //   377: lookupswitch	default:+19->396, 20:+298->675
    //   396: goto +115 -> 511
    //   399: aload_0
    //   400: getfield 200	o/gc:ᐝ	Lo/gc$ˊ;
    //   403: invokevirtual 204	o/gc$ˊ:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   406: ldc_w 543
    //   409: ldc_w 805
    //   412: aload 23
    //   414: invokestatic 808	o/jf:ˊ	(Ljava/lang/String;Ljava/util/AbstractCollection;)Ljava/lang/String;
    //   417: aconst_null
    //   418: invokevirtual 761	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   421: pop
    //   422: goto +661 -> 1083
    //   425: iconst_0
    //   426: istore 4
    //   428: goto +1231 -> 1659
    //   431: iload 4
    //   433: lookupswitch	default:+19->452, 64:+1182->1615
    //   452: goto +775 -> 1227
    //   455: aload 15
    //   457: astore 16
    //   459: goto +382 -> 841
    //   462: bipush 35
    //   464: istore 4
    //   466: goto +429 -> 895
    //   469: new 209	java/util/HashMap
    //   472: dup
    //   473: invokespecial 210	java/util/HashMap:<init>	()V
    //   476: astore 17
    //   478: aload 21
    //   480: invokeinterface 789 1 0
    //   485: astore 16
    //   487: goto +487 -> 974
    //   490: goto +1595 -> 2085
    //   493: aload 22
    //   495: invokeinterface 565 1 0
    //   500: aload 23
    //   502: invokevirtual 809	java/util/HashSet:isEmpty	()Z
    //   505: ifeq +1189 -> 1694
    //   508: goto +506 -> 1014
    //   511: getstatic 114	o/gc:ﾞ	I
    //   514: bipush 71
    //   516: iadd
    //   517: istore 4
    //   519: iload 4
    //   521: sipush 128
    //   524: irem
    //   525: putstatic 112	o/gc:ﹳ	I
    //   528: iload 4
    //   530: iconst_2
    //   531: irem
    //   532: ifne -42 -> 490
    //   535: goto +1550 -> 2085
    //   538: aload 17
    //   540: astore 15
    //   542: iload 4
    //   544: tableswitch	default:+20->564, 0:+-75->469
    //   564: aload 17
    //   566: astore 16
    //   568: goto +1166 -> 1734
    //   571: aload 22
    //   573: invokeinterface 584 1 0
    //   578: istore 10
    //   580: iload 10
    //   582: ifne +518 -> 1100
    //   585: goto +694 -> 1279
    //   588: bipush 14
    //   590: istore 4
    //   592: goto +693 -> 1285
    //   595: bipush 55
    //   597: istore 4
    //   599: goto +1217 -> 1816
    //   602: new 811	java/util/Stack
    //   605: dup
    //   606: invokespecial 812	java/util/Stack:<init>	()V
    //   609: astore 15
    //   611: aload 17
    //   613: invokevirtual 816	java/util/HashMap:values	()Ljava/util/Collection;
    //   616: astore 16
    //   618: aload 15
    //   620: aload 16
    //   622: invokevirtual 820	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   625: pop
    //   626: new 17	o/gc$ˋ
    //   629: dup
    //   630: aload_0
    //   631: lload 11
    //   633: aload 20
    //   635: aload 15
    //   637: aload 21
    //   639: invokespecial 823	o/gc$ˋ:<init>	(Lo/gc;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   642: astore 15
    //   644: aload 15
    //   646: getfield 826	o/gc$ˋ:ˊ	Lo/gc;
    //   649: getfield 141	o/gc:ʻ	Landroid/os/Handler;
    //   652: aload 15
    //   654: getstatic 119	o/gc:ˊ	Ljava/lang/Object;
    //   657: invokestatic 832	android/os/SystemClock:uptimeMillis	()J
    //   660: lconst_1
    //   661: ladd
    //   662: invokevirtual 836	android/os/Handler:postAtTime	(Ljava/lang/Runnable;Ljava/lang/Object;J)Z
    //   665: pop
    //   666: goto -632 -> 34
    //   669: iconst_1
    //   670: istore 4
    //   672: goto +987 -> 1659
    //   675: new 811	java/util/Stack
    //   678: dup
    //   679: invokespecial 812	java/util/Stack:<init>	()V
    //   682: astore 15
    //   684: aload 15
    //   686: aload 17
    //   688: invokevirtual 816	java/util/HashMap:values	()Ljava/util/Collection;
    //   691: invokevirtual 820	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   694: pop
    //   695: new 17	o/gc$ˋ
    //   698: dup
    //   699: aload_0
    //   700: lload 11
    //   702: aload 20
    //   704: aload 15
    //   706: aload 21
    //   708: invokespecial 823	o/gc$ˋ:<init>	(Lo/gc;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   711: astore 15
    //   713: aload 15
    //   715: getfield 826	o/gc$ˋ:ˊ	Lo/gc;
    //   718: getfield 141	o/gc:ʻ	Landroid/os/Handler;
    //   721: aload 15
    //   723: getstatic 119	o/gc:ˊ	Ljava/lang/Object;
    //   726: invokestatic 832	android/os/SystemClock:uptimeMillis	()J
    //   729: lconst_1
    //   730: ladd
    //   731: invokevirtual 836	android/os/Handler:postAtTime	(Ljava/lang/Runnable;Ljava/lang/Object;J)Z
    //   734: pop
    //   735: goto -701 -> 34
    //   738: getstatic 112	o/gc:ﹳ	I
    //   741: bipush 45
    //   743: iadd
    //   744: istore 4
    //   746: iload 4
    //   748: sipush 128
    //   751: irem
    //   752: putstatic 114	o/gc:ﾞ	I
    //   755: iload 4
    //   757: iconst_2
    //   758: irem
    //   759: ifeq +1206 -> 1965
    //   762: goto -167 -> 595
    //   765: aload 15
    //   767: astore 16
    //   769: iload 4
    //   771: lookupswitch	default:+17->788, 50:+70->841
    //   788: goto -542 -> 246
    //   791: getstatic 114	o/gc:ﾞ	I
    //   794: bipush 31
    //   796: iadd
    //   797: istore 4
    //   799: iload 4
    //   801: sipush 128
    //   804: irem
    //   805: putstatic 112	o/gc:ﹳ	I
    //   808: iload 4
    //   810: iconst_2
    //   811: irem
    //   812: ifne +1328 -> 2140
    //   815: goto +1368 -> 2183
    //   818: aload_0
    //   819: getfield 254	o/gc:ˏ	Lo/lh;
    //   822: aload 19
    //   824: invokevirtual 503	o/lg:ˊ	(Lo/lf;)J
    //   827: lstore 11
    //   829: aload_0
    //   830: getfield 224	o/gc:ͺ	Landroid/content/Context;
    //   833: astore 15
    //   835: goto +1210 -> 2045
    //   838: astore_1
    //   839: aload_1
    //   840: athrow
    //   841: aload 22
    //   843: invokeinterface 554 1 0
    //   848: istore 10
    //   850: iload 10
    //   852: ifne +455 -> 1307
    //   855: goto +222 -> 1077
    //   858: iload 4
    //   860: tableswitch	default:+20->880, 0:+-367->493
    //   880: goto +1223 -> 2103
    //   883: iconst_0
    //   884: istore 4
    //   886: goto -28 -> 858
    //   889: iconst_1
    //   890: istore 4
    //   892: goto +360 -> 1252
    //   895: iload 4
    //   897: lookupswitch	default:+19->916, 16:+-609->288
    //   916: goto -778 -> 138
    //   919: getstatic 112	o/gc:ﹳ	I
    //   922: bipush 75
    //   924: iadd
    //   925: istore 4
    //   927: iload 4
    //   929: sipush 128
    //   932: irem
    //   933: putstatic 114	o/gc:ﾞ	I
    //   936: iload 4
    //   938: iconst_2
    //   939: irem
    //   940: ifeq +541 -> 1481
    //   943: goto +547 -> 1490
    //   946: bipush 10
    //   948: istore 4
    //   950: goto +71 -> 1021
    //   953: bipush 50
    //   955: istore 4
    //   957: goto -192 -> 765
    //   960: bipush 62
    //   962: istore 4
    //   964: goto +1355 -> 2319
    //   967: aload 15
    //   969: astore 16
    //   971: goto -130 -> 841
    //   974: aload 16
    //   976: invokeinterface 584 1 0
    //   981: ifne +627 -> 1608
    //   984: goto -24 -> 960
    //   987: getstatic 112	o/gc:ﹳ	I
    //   990: bipush 91
    //   992: iadd
    //   993: istore 4
    //   995: iload 4
    //   997: sipush 128
    //   1000: irem
    //   1001: putstatic 114	o/gc:ﾞ	I
    //   1004: iload 4
    //   1006: iconst_2
    //   1007: irem
    //   1008: ifeq -733 -> 275
    //   1011: goto +234 -> 1245
    //   1014: bipush 63
    //   1016: istore 4
    //   1018: goto +999 -> 2017
    //   1021: iload 4
    //   1023: lookupswitch	default:+17->1040, 17:+820->1843
    //   1040: goto +825 -> 1865
    //   1043: getstatic 114	o/gc:ﾞ	I
    //   1046: bipush 17
    //   1048: iadd
    //   1049: istore 4
    //   1051: iload 4
    //   1053: sipush 128
    //   1056: irem
    //   1057: putstatic 112	o/gc:ﹳ	I
    //   1060: iload 4
    //   1062: iconst_2
    //   1063: irem
    //   1064: ifne -97 -> 967
    //   1067: aload 15
    //   1069: astore 16
    //   1071: goto -230 -> 841
    //   1074: goto -1040 -> 34
    //   1077: iconst_1
    //   1078: istore 4
    //   1080: goto -757 -> 323
    //   1083: aload 17
    //   1085: invokevirtual 837	java/util/HashMap:isEmpty	()Z
    //   1088: ifne +438 -> 1526
    //   1091: goto +15 -> 1106
    //   1094: iconst_1
    //   1095: istore 4
    //   1097: goto -559 -> 538
    //   1100: iconst_1
    //   1101: istore 4
    //   1103: goto -938 -> 165
    //   1106: bipush 20
    //   1108: istore 4
    //   1110: goto -735 -> 375
    //   1113: getstatic 110	o/gc:ʹ	[B
    //   1116: iconst_2
    //   1117: baload
    //   1118: iconst_1
    //   1119: isub
    //   1120: i2b
    //   1121: getstatic 110	o/gc:ʹ	[B
    //   1124: bipush 21
    //   1126: baload
    //   1127: i2s
    //   1128: getstatic 110	o/gc:ʹ	[B
    //   1131: sipush 135
    //   1134: baload
    //   1135: i2b
    //   1136: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   1139: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1142: getstatic 110	o/gc:ʹ	[B
    //   1145: bipush 47
    //   1147: baload
    //   1148: i2b
    //   1149: bipush 90
    //   1151: getstatic 110	o/gc:ʹ	[B
    //   1154: bipush 74
    //   1156: baload
    //   1157: i2b
    //   1158: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   1161: aconst_null
    //   1162: invokevirtual 237	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1165: aload 15
    //   1167: aconst_null
    //   1168: invokevirtual 243	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1171: checkcast 245	android/content/pm/PackageManager
    //   1174: astore 15
    //   1176: new 209	java/util/HashMap
    //   1179: dup
    //   1180: invokespecial 210	java/util/HashMap:<init>	()V
    //   1183: astore 20
    //   1185: aload 15
    //   1187: sipush 8192
    //   1190: invokevirtual 841	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   1193: invokeinterface 789 1 0
    //   1198: astore 22
    //   1200: goto -281 -> 919
    //   1203: aload 21
    //   1205: aload 25
    //   1207: invokevirtual 842	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   1210: pop
    //   1211: goto +356 -> 1567
    //   1214: aload 22
    //   1216: invokeinterface 554 1 0
    //   1221: ifne +932 -> 2153
    //   1224: goto -341 -> 883
    //   1227: aload 15
    //   1229: aload 24
    //   1231: invokevirtual 416	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   1234: invokeinterface 845 2 0
    //   1239: ifeq +733 -> 1972
    //   1242: goto -289 -> 953
    //   1245: bipush 84
    //   1247: istore 4
    //   1249: goto -898 -> 351
    //   1252: aload 16
    //   1254: astore 17
    //   1256: iload 4
    //   1258: tableswitch	default:+18->1276, 1:+814->2072
    //   1276: goto -705 -> 571
    //   1279: iconst_0
    //   1280: istore 4
    //   1282: goto -1117 -> 165
    //   1285: iload 4
    //   1287: lookupswitch	default:+17->1304, 14:+-84->1203
    //   1304: goto +397 -> 1701
    //   1307: iconst_0
    //   1308: istore 4
    //   1310: goto -987 -> 323
    //   1313: iconst_1
    //   1314: istore 4
    //   1316: goto -1017 -> 299
    //   1319: aload_0
    //   1320: getfield 200	o/gc:ᐝ	Lo/gc$ˊ;
    //   1323: invokevirtual 530	o/gc$ˊ:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   1326: astore 16
    //   1328: lload 11
    //   1330: invokestatic 541	java/lang/Long:toString	(J)Ljava/lang/String;
    //   1333: astore 21
    //   1335: aload 16
    //   1337: ldc_w 543
    //   1340: iconst_5
    //   1341: anewarray 375	java/lang/String
    //   1344: dup
    //   1345: iconst_0
    //   1346: ldc_w 805
    //   1349: aastore
    //   1350: dup
    //   1351: iconst_1
    //   1352: ldc_w 697
    //   1355: aastore
    //   1356: dup
    //   1357: iconst_2
    //   1358: ldc_w 730
    //   1361: aastore
    //   1362: dup
    //   1363: iconst_3
    //   1364: ldc_w 742
    //   1367: aastore
    //   1368: dup
    //   1369: iconst_4
    //   1370: ldc_w 304
    //   1373: aastore
    //   1374: ldc_w 847
    //   1377: iconst_1
    //   1378: anewarray 375	java/lang/String
    //   1381: dup
    //   1382: iconst_0
    //   1383: aload 21
    //   1385: aastore
    //   1386: aconst_null
    //   1387: aconst_null
    //   1388: aconst_null
    //   1389: invokevirtual 551	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   1392: astore 22
    //   1394: aload 22
    //   1396: ldc_w 697
    //   1399: invokeinterface 851 2 0
    //   1404: istore 5
    //   1406: aload 22
    //   1408: ldc_w 730
    //   1411: invokeinterface 851 2 0
    //   1416: istore 6
    //   1418: aload 22
    //   1420: ldc_w 742
    //   1423: invokeinterface 851 2 0
    //   1428: istore 7
    //   1430: aload 22
    //   1432: ldc_w 805
    //   1435: invokeinterface 851 2 0
    //   1440: istore 8
    //   1442: aload 22
    //   1444: ldc_w 304
    //   1447: invokeinterface 851 2 0
    //   1452: istore 9
    //   1454: new 568	java/util/HashSet
    //   1457: dup
    //   1458: invokespecial 569	java/util/HashSet:<init>	()V
    //   1461: astore 23
    //   1463: new 811	java/util/Stack
    //   1466: dup
    //   1467: invokespecial 812	java/util/Stack:<init>	()V
    //   1470: astore 21
    //   1472: goto -734 -> 738
    //   1475: iconst_1
    //   1476: istore 4
    //   1478: goto +681 -> 2159
    //   1481: iconst_1
    //   1482: istore 4
    //   1484: goto -1293 -> 191
    //   1487: goto -444 -> 1043
    //   1490: iconst_0
    //   1491: istore 4
    //   1493: goto -1302 -> 191
    //   1496: goto +37 -> 1533
    //   1499: getstatic 114	o/gc:ﾞ	I
    //   1502: bipush 27
    //   1504: iadd
    //   1505: istore 4
    //   1507: iload 4
    //   1509: sipush 128
    //   1512: irem
    //   1513: putstatic 112	o/gc:ﹳ	I
    //   1516: iload 4
    //   1518: iconst_2
    //   1519: irem
    //   1520: ifne -574 -> 946
    //   1523: goto +620 -> 2143
    //   1526: bipush 25
    //   1528: istore 4
    //   1530: goto -1155 -> 375
    //   1533: getstatic 114	o/gc:ﾞ	I
    //   1536: bipush 63
    //   1538: iadd
    //   1539: istore 4
    //   1541: iload 4
    //   1543: sipush 128
    //   1546: irem
    //   1547: putstatic 112	o/gc:ﹳ	I
    //   1550: iload 4
    //   1552: iconst_2
    //   1553: irem
    //   1554: ifne +657 -> 2211
    //   1557: aload 15
    //   1559: astore 16
    //   1561: goto -720 -> 841
    //   1564: astore_1
    //   1565: aload_1
    //   1566: athrow
    //   1567: getstatic 114	o/gc:ﾞ	I
    //   1570: bipush 111
    //   1572: iadd
    //   1573: istore 4
    //   1575: iload 4
    //   1577: sipush 128
    //   1580: irem
    //   1581: putstatic 112	o/gc:ﹳ	I
    //   1584: aload 15
    //   1586: astore 16
    //   1588: iload 4
    //   1590: iconst_2
    //   1591: irem
    //   1592: ifeq -751 -> 841
    //   1595: aload 15
    //   1597: astore 16
    //   1599: goto -758 -> 841
    //   1602: astore_1
    //   1603: aload_1
    //   1604: invokevirtual 129	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   1607: athrow
    //   1608: bipush 22
    //   1610: istore 4
    //   1612: goto +707 -> 2319
    //   1615: aload 16
    //   1617: getfield 522	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   1620: getfield 854	android/content/pm/ApplicationInfo:flags	I
    //   1623: ldc_w 855
    //   1626: iand
    //   1627: ifne -140 -> 1487
    //   1630: goto +588 -> 2218
    //   1633: iconst_0
    //   1634: istore 4
    //   1636: goto -384 -> 1252
    //   1639: aload 22
    //   1641: invokeinterface 584 1 0
    //   1646: ifne -977 -> 669
    //   1649: goto -1224 -> 425
    //   1652: bipush 64
    //   1654: istore 4
    //   1656: goto -1225 -> 431
    //   1659: aload 16
    //   1661: astore 15
    //   1663: iload 4
    //   1665: tableswitch	default:+19->1684, 0:+-1196->469
    //   1684: goto +50 -> 1734
    //   1687: bipush 16
    //   1689: istore 4
    //   1691: goto -796 -> 895
    //   1694: bipush 87
    //   1696: istore 4
    //   1698: goto +319 -> 2017
    //   1701: aload_0
    //   1702: aload 24
    //   1704: aload 19
    //   1706: invokevirtual 801	o/gc:ˊ	(Landroid/content/ComponentName;Lo/lf;)V
    //   1709: aload 22
    //   1711: iload 8
    //   1713: invokeinterface 803 2 0
    //   1718: istore 4
    //   1720: aload 23
    //   1722: iload 4
    //   1724: invokestatic 516	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1727: invokevirtual 592	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1730: pop
    //   1731: goto -164 -> 1567
    //   1734: aload 22
    //   1736: invokeinterface 588 1 0
    //   1741: astore 15
    //   1743: aload 20
    //   1745: getstatic 110	o/gc:ʹ	[B
    //   1748: bipush 23
    //   1750: baload
    //   1751: ineg
    //   1752: i2b
    //   1753: getstatic 110	o/gc:ʹ	[B
    //   1756: bipush 46
    //   1758: baload
    //   1759: ineg
    //   1760: i2s
    //   1761: getstatic 110	o/gc:ʹ	[B
    //   1764: sipush 135
    //   1767: baload
    //   1768: i2b
    //   1769: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   1772: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1775: getstatic 110	o/gc:ʹ	[B
    //   1778: bipush 28
    //   1780: baload
    //   1781: i2b
    //   1782: getstatic 110	o/gc:ʹ	[B
    //   1785: bipush 70
    //   1787: baload
    //   1788: i2s
    //   1789: getstatic 110	o/gc:ʹ	[B
    //   1792: bipush 47
    //   1794: baload
    //   1795: i2b
    //   1796: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   1799: invokevirtual 734	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1802: aload 15
    //   1804: invokevirtual 856	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1807: aload 15
    //   1809: invokevirtual 402	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1812: pop
    //   1813: goto -1594 -> 219
    //   1816: aload 15
    //   1818: astore 16
    //   1820: iload 4
    //   1822: lookupswitch	default:+18->1840, 55:+-981->841
    //   1840: goto -626 -> 1214
    //   1843: aload_0
    //   1844: getfield 184	o/gc:ˑ	Ljava/lang/String;
    //   1847: aload 22
    //   1849: iload 9
    //   1851: invokeinterface 560 2 0
    //   1856: invokestatic 859	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   1859: ifne -363 -> 1496
    //   1862: goto -1574 -> 288
    //   1865: aload_0
    //   1866: getfield 184	o/gc:ˑ	Ljava/lang/String;
    //   1869: aload 22
    //   1871: iload 9
    //   1873: invokeinterface 560 2 0
    //   1878: invokestatic 859	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   1881: ifne -385 -> 1496
    //   1884: goto -1596 -> 288
    //   1887: iconst_0
    //   1888: istore 4
    //   1890: goto -1352 -> 538
    //   1893: lload 13
    //   1895: getstatic 110	o/gc:ʹ	[B
    //   1898: bipush 23
    //   1900: baload
    //   1901: ineg
    //   1902: i2b
    //   1903: getstatic 110	o/gc:ʹ	[B
    //   1906: bipush 46
    //   1908: baload
    //   1909: ineg
    //   1910: i2s
    //   1911: getstatic 110	o/gc:ʹ	[B
    //   1914: sipush 135
    //   1917: baload
    //   1918: i2b
    //   1919: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   1922: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1925: getstatic 110	o/gc:ʹ	[B
    //   1928: bipush 9
    //   1930: baload
    //   1931: i2b
    //   1932: getstatic 110	o/gc:ʹ	[B
    //   1935: iconst_3
    //   1936: baload
    //   1937: iconst_1
    //   1938: isub
    //   1939: i2s
    //   1940: getstatic 110	o/gc:ʹ	[B
    //   1943: bipush 70
    //   1945: baload
    //   1946: i2b
    //   1947: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   1950: invokevirtual 734	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1953: aload 16
    //   1955: invokevirtual 740	java/lang/reflect/Field:getLong	(Ljava/lang/Object;)J
    //   1958: lcmp
    //   1959: ifeq -1677 -> 282
    //   1962: goto -487 -> 1475
    //   1965: bipush 84
    //   1967: istore 4
    //   1969: goto -153 -> 1816
    //   1972: bipush 68
    //   1974: istore 4
    //   1976: goto -1211 -> 765
    //   1979: bipush 27
    //   1981: istore 4
    //   1983: goto -698 -> 1285
    //   1986: getstatic 112	o/gc:ﹳ	I
    //   1989: bipush 101
    //   1991: iadd
    //   1992: istore 4
    //   1994: iload 4
    //   1996: sipush 128
    //   1999: irem
    //   2000: putstatic 114	o/gc:ﾞ	I
    //   2003: iload 4
    //   2005: iconst_2
    //   2006: irem
    //   2007: ifeq -1552 -> 455
    //   2010: aload 15
    //   2012: astore 16
    //   2014: goto -1173 -> 841
    //   2017: iload 4
    //   2019: lookupswitch	default:+17->2036, 63:+-936->1083
    //   2036: goto -1637 -> 399
    //   2039: iconst_0
    //   2040: istore 4
    //   2042: goto -1743 -> 299
    //   2045: getstatic 114	o/gc:ﾞ	I
    //   2048: bipush 117
    //   2050: iadd
    //   2051: istore 4
    //   2053: iload 4
    //   2055: sipush 128
    //   2058: irem
    //   2059: putstatic 112	o/gc:ﹳ	I
    //   2062: iload 4
    //   2064: iconst_2
    //   2065: irem
    //   2066: ifne -1770 -> 296
    //   2069: goto -956 -> 1113
    //   2072: aload 22
    //   2074: invokeinterface 584 1 0
    //   2079: ifne -985 -> 1094
    //   2082: goto -195 -> 1887
    //   2085: aload 21
    //   2087: invokevirtual 860	java/util/Stack:isEmpty	()Z
    //   2090: ifeq -51 -> 2039
    //   2093: goto -780 -> 1313
    //   2096: bipush 58
    //   2098: istore 4
    //   2100: goto -1669 -> 431
    //   2103: aload 22
    //   2105: iload 5
    //   2107: invokeinterface 560 2 0
    //   2112: invokestatic 864	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   2115: astore 24
    //   2117: aload 20
    //   2119: aload 24
    //   2121: invokevirtual 416	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   2124: invokevirtual 391	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2127: checkcast 518	android/content/pm/PackageInfo
    //   2130: astore 16
    //   2132: aload 16
    //   2134: ifnull -38 -> 2096
    //   2137: goto -485 -> 1652
    //   2140: goto +43 -> 2183
    //   2143: bipush 17
    //   2145: istore 4
    //   2147: goto -1126 -> 1021
    //   2150: goto -257 -> 1893
    //   2153: iconst_1
    //   2154: istore 4
    //   2156: goto -1298 -> 858
    //   2159: iload 4
    //   2161: tableswitch	default:+19->2180, 1:+-1873->288
    //   2180: goto -681 -> 1499
    //   2183: aload 16
    //   2185: invokeinterface 588 1 0
    //   2190: checkcast 445	o/kV
    //   2193: astore 21
    //   2195: aload 17
    //   2197: aload 21
    //   2199: invokevirtual 618	o/kV:ˊ	()Landroid/content/ComponentName;
    //   2202: aload 21
    //   2204: invokevirtual 402	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2207: pop
    //   2208: goto -1234 -> 974
    //   2211: aload 15
    //   2213: astore 16
    //   2215: goto -1374 -> 841
    //   2218: aload 22
    //   2220: iload 6
    //   2222: invokeinterface 867 2 0
    //   2227: lstore 13
    //   2229: aload 22
    //   2231: iload 7
    //   2233: invokeinterface 803 2 0
    //   2238: istore 4
    //   2240: aload 17
    //   2242: aload 24
    //   2244: invokevirtual 596	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2247: checkcast 445	o/kV
    //   2250: astore 25
    //   2252: getstatic 110	o/gc:ʹ	[B
    //   2255: bipush 23
    //   2257: baload
    //   2258: ineg
    //   2259: i2b
    //   2260: getstatic 110	o/gc:ʹ	[B
    //   2263: bipush 46
    //   2265: baload
    //   2266: ineg
    //   2267: i2s
    //   2268: getstatic 110	o/gc:ʹ	[B
    //   2271: sipush 135
    //   2274: baload
    //   2275: i2b
    //   2276: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   2279: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   2282: astore 26
    //   2284: getstatic 110	o/gc:ʹ	[B
    //   2287: bipush 28
    //   2289: baload
    //   2290: i2b
    //   2291: istore_2
    //   2292: iload_2
    //   2293: istore_3
    //   2294: iload 4
    //   2296: aload 26
    //   2298: iload_2
    //   2299: iload_3
    //   2300: iload_3
    //   2301: i2b
    //   2302: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   2305: invokevirtual 734	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   2308: aload 16
    //   2310: invokevirtual 746	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   2313: if_icmpeq -1851 -> 462
    //   2316: goto -629 -> 1687
    //   2319: iload 4
    //   2321: lookupswitch	default:+19->2340, 62:+-1002->1319
    //   2340: goto -1549 -> 791
    //   2343: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2344	0	this	gc
    //   0	2344	1	paramSet	Set<String>
    //   2291	8	2	s1	short
    //   2293	8	3	s2	short
    //   144	2176	4	i	int
    //   1404	702	5	j	int
    //   1416	805	6	k	int
    //   1428	804	7	m	int
    //   258	1454	8	n	int
    //   1452	420	9	i1	int
    //   578	273	10	bool	boolean
    //   631	70	11	localObject1	Object
    //   827	502	11	l1	long
    //   1893	1	13	localObject2	Object
    //   2227	1	13	l2	long
    //   100	2112	15	localObject3	Object
    //   125	2184	16	localObject4	Object
    //   193	2048	17	localObject5	Object
    //   32	13	18	localIterator	Iterator
    //   54	1651	19	localLf	lf
    //   633	1485	20	localHashMap	HashMap
    //   66	2137	21	localObject6	Object
    //   256	1974	22	localObject7	Object
    //   254	1467	23	localObject8	Object
    //   247	1996	24	localComponentName	ComponentName
    //   288	1963	25	localObject9	Object
    //   2282	15	26	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   571	580	838	java/lang/Exception
    //   841	850	838	java/lang/Exception
    //   1701	1709	838	java/lang/Exception
    //   1709	1720	838	java/lang/Exception
    //   1720	1731	838	java/lang/Exception
    //   602	611	1564	java/lang/Exception
    //   611	618	1564	java/lang/Exception
    //   618	626	1564	java/lang/Exception
    //   626	644	1564	java/lang/Exception
    //   644	666	1564	java/lang/Exception
    //   1113	1176	1602	finally
  }
  
  public final void ˊ(eE paramEE)
  {
    for (;;)
    {
      try
      {
        if localIf = ˊ(paramEE.ʼ, null, paramEE.ՙ, false, paramEE.ˏ);
        if (localIf.ˊ != null)
        {
          Bitmap localBitmap = localIf.ˊ;
          lf localLf = paramEE.ՙ;
          if (this.ˋ.get(localLf) != localBitmap) {
            break label116;
          }
          i = 1;
          if (i == 0)
          {
            paramEE.ﹳ = jf.ˊ(localIf.ˋ);
            paramEE.ˋ = localIf.ˊ;
            paramEE.ﾞ = localIf.ˎ;
            paramEE.ˏ = localIf.ˏ;
            this.ʼ.ˊ(paramEE);
          }
        }
        return;
      }
      finally {}
      label116:
      int i = 0;
    }
  }
  
  public final void ˊ(eE paramEE, kV paramKV, boolean paramBoolean)
  {
    if (paramKV == null) {}
    try
    {
      lf localLf = paramEE.ՙ;
      break label21;
      localLf = paramKV.ˋ();
      label21:
      if localIf = ˊ(paramEE.ʼ, paramKV, localLf, false, paramBoolean);
      paramEE.ﹳ = jf.ˊ(localIf.ˋ);
      if (localIf.ˊ == null) {
        paramKV = ˊ(localLf);
      } else {
        paramKV = localIf.ˊ;
      }
      paramEE.ˋ = paramKV;
      paramEE.ﾞ = localIf.ˎ;
      paramEE.ˏ = localIf.ˏ;
      this.ʼ.ˊ(paramEE);
      return;
    }
    finally {}
  }
  
  public final void ˊ(ja paramJa, ComponentName paramComponentName, kV paramKV, lf paramLf, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      try
      {
        if localIf = ˊ(paramComponentName, paramKV, paramLf, paramBoolean1, paramBoolean2);
        if (localIf.ˊ == null) {
          paramKV = ˊ(paramLf);
        } else {
          paramKV = localIf.ˊ;
        }
        paramJa.ˊ(paramKV);
        paramJa.ﹳ = jf.ˊ(localIf.ˋ);
        paramKV = localIf.ˊ;
        if (this.ˋ.get(paramLf) == paramKV)
        {
          paramBoolean1 = true;
          paramJa.ᐝ = paramBoolean1;
          paramJa.ʻ = localIf.ˏ;
          this.ʼ.ˊ(paramJa, paramComponentName);
          return;
        }
      }
      finally {}
      paramBoolean1 = false;
    }
  }
  
  public final void ˊ(ja paramJa, Intent paramIntent, lf paramLf, boolean paramBoolean)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramJa.ˊ(ˊ(paramLf));
        paramJa.ﹳ = "";
        paramJa.ᐝ = true;
        paramJa.ʻ = false;
        return;
      }
      ˊ(paramJa, localComponentName, this.ʾ.ˊ(paramIntent, paramLf), paramLf, true, paramBoolean);
      return;
    }
    finally {}
  }
  
  public final Drawable ˋ()
  {
    return ˊ(Resources.getSystem(), 17629184);
  }
  
  /* Error */
  public final void ˋ(String paramString, lf paramLf)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 751	o/gc:ˊ	(Ljava/lang/String;Lo/lf;)V
    //   8: aload_0
    //   9: getfield 277	o/gc:ʼ	Lo/ﺕ;
    //   12: aload_1
    //   13: aload_2
    //   14: invokeinterface 903 3 0
    //   19: aload_0
    //   20: getfield 247	o/gc:ι	Landroid/content/pm/PackageManager;
    //   23: astore 8
    //   25: goto +345 -> 370
    //   28: bipush 77
    //   30: istore 4
    //   32: goto +218 -> 250
    //   35: iconst_1
    //   36: istore 4
    //   38: goto +237 -> 275
    //   41: goto +52 -> 93
    //   44: iload 4
    //   46: lookupswitch	default:+18->64, 28:+308->354
    //   64: goto +339 -> 403
    //   67: iconst_0
    //   68: istore 4
    //   70: goto +205 -> 275
    //   73: bipush 56
    //   75: istore 4
    //   77: goto +173 -> 250
    //   80: astore_1
    //   81: aload_1
    //   82: invokevirtual 129	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   85: athrow
    //   86: bipush 28
    //   88: istore 4
    //   90: goto -46 -> 44
    //   93: getstatic 110	o/gc:ʹ	[B
    //   96: bipush 29
    //   98: baload
    //   99: i2b
    //   100: sipush 135
    //   103: getstatic 110	o/gc:ʹ	[B
    //   106: sipush 135
    //   109: baload
    //   110: i2b
    //   111: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   114: invokestatic 233	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   117: astore 9
    //   119: getstatic 110	o/gc:ʹ	[B
    //   122: bipush 9
    //   124: baload
    //   125: i2b
    //   126: istore_3
    //   127: aload 9
    //   129: iload_3
    //   130: iload_3
    //   131: bipush 100
    //   133: ior
    //   134: i2s
    //   135: getstatic 110	o/gc:ʹ	[B
    //   138: bipush 74
    //   140: baload
    //   141: i2b
    //   142: invokestatic 227	o/gc:ˊ	(SSI)Ljava/lang/String;
    //   145: iconst_2
    //   146: anewarray 229	java/lang/Class
    //   149: dup
    //   150: iconst_0
    //   151: ldc_w 375
    //   154: aastore
    //   155: dup
    //   156: iconst_1
    //   157: getstatic 512	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   160: aastore
    //   161: invokevirtual 237	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   164: aload 8
    //   166: iconst_2
    //   167: anewarray 4	java/lang/Object
    //   170: dup
    //   171: iconst_0
    //   172: aload_1
    //   173: aastore
    //   174: dup
    //   175: iconst_1
    //   176: sipush 8192
    //   179: invokestatic 516	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   182: aastore
    //   183: invokevirtual 243	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   186: checkcast 518	android/content/pm/PackageInfo
    //   189: astore 8
    //   191: aload_0
    //   192: getfield 254	o/gc:ˏ	Lo/lh;
    //   195: aload_2
    //   196: invokevirtual 503	o/lg:ˊ	(Lo/lf;)J
    //   199: lstore 5
    //   201: aload_0
    //   202: getfield 261	o/gc:ʾ	Lo/kY;
    //   205: aload_1
    //   206: aload_2
    //   207: invokevirtual 792	o/kY:ˊ	(Ljava/lang/String;Lo/lf;)Ljava/util/List;
    //   210: invokeinterface 789 1 0
    //   215: astore_1
    //   216: goto +114 -> 330
    //   219: aload_1
    //   220: invokeinterface 588 1 0
    //   225: checkcast 445	o/kV
    //   228: astore_2
    //   229: aload_0
    //   230: aload_0
    //   231: aload_2
    //   232: iconst_0
    //   233: invokevirtual 905	o/gc:ˊ	(Lo/kV;Z)Landroid/content/ContentValues;
    //   236: aload_2
    //   237: invokevirtual 618	o/kV:ˊ	()Landroid/content/ComponentName;
    //   240: aload 8
    //   242: lload 5
    //   244: invokevirtual 506	o/gc:ˊ	(Landroid/content/ContentValues;Landroid/content/ComponentName;Landroid/content/pm/PackageInfo;J)V
    //   247: goto +52 -> 299
    //   250: iload 4
    //   252: lookupswitch	default:+20->272, 56:+148->400
    //   272: goto -53 -> 219
    //   275: iload 4
    //   277: tableswitch	default:+19->296, 1:+123->400
    //   296: goto -77 -> 219
    //   299: getstatic 114	o/gc:ﾞ	I
    //   302: bipush 65
    //   304: iadd
    //   305: istore 4
    //   307: iload 4
    //   309: sipush 128
    //   312: irem
    //   313: putstatic 112	o/gc:ﹳ	I
    //   316: iload 4
    //   318: iconst_2
    //   319: irem
    //   320: ifeq -234 -> 86
    //   323: bipush 97
    //   325: istore 4
    //   327: goto -283 -> 44
    //   330: getstatic 112	o/gc:ﹳ	I
    //   333: bipush 71
    //   335: iadd
    //   336: istore 4
    //   338: iload 4
    //   340: sipush 128
    //   343: irem
    //   344: putstatic 114	o/gc:ﾞ	I
    //   347: iload 4
    //   349: iconst_2
    //   350: irem
    //   351: ifeq +46 -> 397
    //   354: aload_1
    //   355: invokeinterface 584 1 0
    //   360: istore 7
    //   362: iload 7
    //   364: ifne -336 -> 28
    //   367: goto -294 -> 73
    //   370: getstatic 114	o/gc:ﾞ	I
    //   373: bipush 71
    //   375: iadd
    //   376: istore 4
    //   378: iload 4
    //   380: sipush 128
    //   383: irem
    //   384: putstatic 112	o/gc:ﹳ	I
    //   387: iload 4
    //   389: iconst_2
    //   390: irem
    //   391: ifne -350 -> 41
    //   394: goto -301 -> 93
    //   397: goto -43 -> 354
    //   400: aload_0
    //   401: monitorexit
    //   402: return
    //   403: aload_1
    //   404: invokeinterface 584 1 0
    //   409: istore 7
    //   411: iload 7
    //   413: ifne -346 -> 67
    //   416: goto -381 -> 35
    //   419: aload_0
    //   420: monitorexit
    //   421: return
    //   422: astore_1
    //   423: aload_0
    //   424: monitorexit
    //   425: aload_1
    //   426: athrow
    //   427: astore_1
    //   428: goto -9 -> 419
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	431	0	this	gc
    //   0	431	1	paramString	String
    //   0	431	2	paramLf	lf
    //   126	8	3	s	short
    //   30	361	4	i	int
    //   199	44	5	l	long
    //   360	52	7	bool	boolean
    //   23	218	8	localObject	Object
    //   117	11	9	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   93	191	80	finally
    //   2	19	422	finally
    //   19	25	422	finally
    //   81	86	422	finally
    //   191	216	422	finally
    //   219	247	422	finally
    //   299	316	422	finally
    //   330	347	422	finally
    //   354	362	422	finally
    //   370	387	422	finally
    //   403	411	422	finally
    //   19	25	427	android/content/pm/PackageManager$NameNotFoundException
    //   81	86	427	android/content/pm/PackageManager$NameNotFoundException
    //   191	216	427	android/content/pm/PackageManager$NameNotFoundException
    //   219	247	427	android/content/pm/PackageManager$NameNotFoundException
    //   354	362	427	android/content/pm/PackageManager$NameNotFoundException
    //   403	411	427	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static final class If
  {
    public final gd ˊ;
    public final Handler ˋ;
    
    If(gd paramGd, Handler paramHandler)
    {
      this.ˊ = paramGd;
      this.ˋ = paramHandler;
    }
  }
  
  public static final class if
  {
    public Bitmap ˊ;
    public CharSequence ˋ = "";
    public CharSequence ˎ = "";
    public boolean ˏ;
    
    public if() {}
  }
  
  static final class ˊ
    extends SQLiteOpenHelper
  {
    public ˊ(Context paramContext)
    {
      super("app_icons.db", null, 12);
    }
    
    public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_low_res BLOB, label TEXT, system_state TEXT, PRIMARY KEY (componentName, profileId) );");
    }
    
    public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 != paramInt2)
      {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS icons");
        onCreate(paramSQLiteDatabase);
      }
    }
    
    public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 != paramInt2)
      {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS icons");
        onCreate(paramSQLiteDatabase);
      }
    }
  }
  
  final class ˋ
    implements Runnable
  {
    private final HashSet<String> ʻ = new HashSet();
    private final long ˋ;
    private final HashMap<String, PackageInfo> ˎ;
    private final Stack<kV> ˏ;
    private final Stack<kV> ᐝ;
    
    ˋ(HashMap<String, PackageInfo> paramHashMap, Stack<kV> paramStack1, Stack<kV> paramStack2)
    {
      this.ˋ = ???;
      this.ˎ = paramStack1;
      this.ˏ = paramStack2;
      Object localObject;
      this.ᐝ = localObject;
    }
    
    public final void run()
    {
      Object localObject2;
      Object localObject4;
      Object localObject5;
      lf localLf;
      ArrayList localArrayList1;
      ArrayList localArrayList2;
      if (!this.ᐝ.isEmpty())
      {
        Object localObject1 = (kV)this.ᐝ.pop();
        localObject2 = ((kV)localObject1).ˊ().flattenToString();
        localObject4 = gc.this.ˊ((kV)localObject1, true);
        gc.this.ᐝ.getWritableDatabase().update("icons", (ContentValues)localObject4, "componentName = ? AND profileId = ?", new String[] { localObject2, Long.toString(this.ˋ) });
        this.ʻ.add(((kV)localObject1).ˊ().getPackageName());
        if ((this.ᐝ.isEmpty()) && (!this.ʻ.isEmpty()))
        {
          if (hd.ʻ == null) {
            hd.ʻ = new hd();
          }
          localObject4 = hd.ʻ.ˊ;
          localObject5 = this.ʻ;
          localLf = gc.this.ˏ.ˊ(this.ˋ);
          if (((ho)localObject4).ˈ != null) {
            localObject1 = (ho.ˊ)((ho)localObject4).ˈ.get();
          } else {
            localObject1 = null;
          }
          localArrayList1 = new ArrayList();
          localArrayList2 = new ArrayList();
        }
      }
      label649:
      label654:
      for (;;)
      {
        synchronized (ho.ˍ)
        {
          Iterator localIterator = ho.ˑ.iterator();
          if (localIterator.hasNext())
          {
            localObject2 = (gn)localIterator.next();
            if ((!(localObject2 instanceof ja)) || (!localLf.equals(((gn)localObject2).ՙ)) || (((gn)localObject2).ι != 0)) {
              break label654;
            }
            ja localJa = (ja)localObject2;
            if (localJa.ⁱ != null) {
              localObject2 = localJa.ⁱ.getComponent();
            } else {
              localObject2 = localJa.ˎ.getComponent();
            }
            if ((localObject2 == null) || (!((HashSet)localObject5).contains(((ComponentName)localObject2).getPackageName()))) {
              break label654;
            }
            localObject2 = ((ho)localObject4).ՙ;
            if ((!localJa.ʻ) || (localJa.ʾ < 0L) || (localJa.ᐧ < 3)) {
              break label649;
            }
            bool = true;
            localJa.ˊ((gc)localObject2, bool, false);
            localArrayList2.add(localJa);
            break label654;
          }
          ((ho)localObject4).ˉ.ˊ((HashSet)localObject5, localLf, localArrayList1);
        }
        if (!localArrayList2.isEmpty()) {
          ((ho)localObject4).ˎ.ˊ(new hw((ho)localObject4, localˊ, localArrayList2, localLf));
        }
        if (!localArrayList1.isEmpty()) {
          ((ho)localObject4).ˎ.ˊ(new hx((ho)localObject4, localˊ, localArrayList1));
        }
        ho.ˊ(new hy((ho)localObject4, false, localˊ));
        gc.this.ʻ.postAtTime(this, gc.ˊ, SystemClock.uptimeMillis() + 1L);
        return;
        if (!this.ˏ.isEmpty())
        {
          localObject2 = (kV)this.ˏ.pop();
          localObject4 = (PackageInfo)this.ˎ.get(((kV)localObject2).ˊ().getPackageName());
          if (localObject4 != null) {
            synchronized (gc.this)
            {
              localObject5 = gc.this;
              long l = this.ˋ;
              ((gc)localObject5).ˊ(((gc)localObject5).ˊ((kV)localObject2, false), ((kV)localObject2).ˊ(), (PackageInfo)localObject4, l);
            }
          }
          if (!this.ˏ.isEmpty()) {
            gc.this.ʻ.postAtTime(this, gc.ˊ, SystemClock.uptimeMillis() + 1L);
          }
        }
        return;
        boolean bool = false;
      }
    }
  }
}
