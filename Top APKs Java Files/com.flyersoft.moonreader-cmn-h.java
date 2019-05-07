package cmn;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.Log;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@TargetApi(4)
public final class h
  implements Serializable
{
  public static final boolean g;
  private static h h = null;
  private Boolean A = null;
  public int a = -1;
  public int b = -1;
  public int c = -1;
  public int d = -1;
  public String e = "";
  public Set f = new HashSet();
  private int i = -1;
  private String j = null;
  private String k = "";
  private String l = "";
  private final int m;
  private final long n;
  private final String o;
  private final int p;
  private final String q;
  private final String r;
  private final String s;
  private final String t;
  private final String u;
  private int v;
  private String w;
  private String x;
  private String y;
  private Context z;
  
  static
  {
    if (("google_sdk".equals(Build.PRODUCT)) || ("sdk".equals(Build.PRODUCT))) {}
    for (boolean bool = true;; bool = false)
    {
      g = bool;
      return;
    }
  }
  
  private h(int paramInt1, int paramInt2, String paramString1, long paramLong, String paramString2)
  {
    this.m = paramInt1;
    this.p = paramInt2;
    this.q = paramString1;
    this.n = paramLong;
    this.o = paramString2;
    this.r = (Build.BRAND + " " + Build.DEVICE);
    this.s = Build.MODEL;
    paramString1 = "";
    Field[] arrayOfField = Build.class.getFields();
    paramInt2 = arrayOfField.length;
    paramInt1 = 0;
    if (paramInt1 < paramInt2)
    {
      paramString2 = arrayOfField[paramInt1];
      if (paramString2.getName().equals("MANUFACTURER")) {}
      for (;;)
      {
        try
        {
          paramString2 = (String)paramString2.get(null);
          paramString1 = paramString2;
          paramInt1 += 1;
        }
        catch (Exception paramString2)
        {
          paramString2.printStackTrace();
        }
      }
    }
    this.t = paramString1;
    this.u = Build.PRODUCT;
  }
  
  public static long a(String paramString)
  {
    int i1 = 0;
    long l1 = 0L;
    try
    {
      byte[] arrayOfByte = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
      long l2;
      int i2;
      for (i1 = 0;; i1 = i2)
      {
        l2 = l1;
        if (i1 >= 8) {
          break;
        }
        i2 = arrayOfByte[i1];
        l2 = i2;
        i2 = i1 + 1;
        l1 = (l2 & 0xFF) << i1 * 8 | l1;
      }
      return l2;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.e("scm", "MD5 not found!");
      for (;;)
      {
        l2 = l1;
        if (i1 >= paramString.length()) {
          break;
        }
        l1 = l1 * 7265812761L + (paramString.charAt(i1) + '{') * 41;
        i1 += 1;
      }
    }
  }
  
  /* Error */
  public static h a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: ldc 2
    //   6: monitorenter
    //   7: getstatic 48	cmn/h:h	Lcmn/h;
    //   10: ifnull +12 -> 22
    //   13: getstatic 48	cmn/h:h	Lcmn/h;
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual 208	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   26: astore 12
    //   28: aload 12
    //   30: invokevirtual 212	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   33: astore 9
    //   35: aload 12
    //   37: invokestatic 218	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   40: astore 13
    //   42: new 220	java/util/Date
    //   45: dup
    //   46: invokespecial 221	java/util/Date:<init>	()V
    //   49: invokevirtual 225	java/util/Date:getTime	()J
    //   52: ldc2_w 226
    //   55: ldiv
    //   56: lstore 5
    //   58: lload 5
    //   60: l2i
    //   61: istore 4
    //   63: aload 13
    //   65: ldc -27
    //   67: iload 4
    //   69: invokeinterface 235 3 0
    //   74: istore_1
    //   75: iload_3
    //   76: istore_2
    //   77: aload 13
    //   79: ldc -19
    //   81: iconst_0
    //   82: invokeinterface 235 3 0
    //   87: istore_3
    //   88: iload_3
    //   89: istore_2
    //   90: aload 13
    //   92: ldc -17
    //   94: lconst_0
    //   95: invokeinterface 243 4 0
    //   100: lstore 5
    //   102: iload_1
    //   103: istore_2
    //   104: iload_3
    //   105: istore_1
    //   106: iload 4
    //   108: iload_2
    //   109: if_icmpeq +26 -> 135
    //   112: iload_1
    //   113: istore_3
    //   114: lload 5
    //   116: lstore 7
    //   118: iload_1
    //   119: ifne +87 -> 206
    //   122: iload_1
    //   123: istore_3
    //   124: lload 5
    //   126: lstore 7
    //   128: lload 5
    //   130: lconst_0
    //   131: lcmp
    //   132: ifne +74 -> 206
    //   135: aload 13
    //   137: invokeinterface 247 1 0
    //   142: astore 10
    //   144: aload 9
    //   146: invokestatic 250	cmn/h:a	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   149: astore 9
    //   151: aload 9
    //   153: invokevirtual 253	java/lang/String:hashCode	()I
    //   156: istore_3
    //   157: aload 9
    //   159: invokestatic 255	cmn/h:a	(Ljava/lang/String;)J
    //   162: lstore 7
    //   164: aload 10
    //   166: ldc -27
    //   168: iload_2
    //   169: invokeinterface 261 3 0
    //   174: pop
    //   175: aload 10
    //   177: ldc -19
    //   179: iload_3
    //   180: invokeinterface 261 3 0
    //   185: pop
    //   186: aload 10
    //   188: ldc -17
    //   190: lload 7
    //   192: invokeinterface 265 4 0
    //   197: pop
    //   198: invokestatic 270	cmn/a:a	()Lcmn/a;
    //   201: aload 10
    //   203: invokevirtual 273	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   206: aload 12
    //   208: invokevirtual 212	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   211: ldc_w 275
    //   214: invokestatic 281	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   217: astore 10
    //   219: aload 10
    //   221: astore 9
    //   223: aload 10
    //   225: ifnonnull +7 -> 232
    //   228: ldc 78
    //   230: astore 9
    //   232: new 2	cmn/h
    //   235: dup
    //   236: iload_3
    //   237: iload_2
    //   238: getstatic 286	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   241: lload 7
    //   243: aload 9
    //   245: invokespecial 288	cmn/h:<init>	(IILjava/lang/String;JLjava/lang/String;)V
    //   248: astore 10
    //   250: aload 10
    //   252: aload 12
    //   254: putfield 290	cmn/h:z	Landroid/content/Context;
    //   257: aload 10
    //   259: aload 12
    //   261: invokespecial 293	cmn/h:b	(Landroid/content/Context;)V
    //   264: aload 12
    //   266: invokevirtual 296	android/content/Context:getPackageName	()Ljava/lang/String;
    //   269: astore 11
    //   271: aload 11
    //   273: astore 9
    //   275: aload 11
    //   277: ldc_w 298
    //   280: invokevirtual 302	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   283: ifeq +19 -> 302
    //   286: aload 11
    //   288: aload 11
    //   290: bipush 46
    //   292: invokevirtual 306	java/lang/String:lastIndexOf	(I)I
    //   295: iconst_1
    //   296: iadd
    //   297: invokevirtual 310	java/lang/String:substring	(I)Ljava/lang/String;
    //   300: astore 9
    //   302: aload 10
    //   304: aload 9
    //   306: putfield 312	cmn/h:x	Ljava/lang/String;
    //   309: aload 10
    //   311: aload 12
    //   313: invokevirtual 296	android/content/Context:getPackageName	()Ljava/lang/String;
    //   316: putfield 314	cmn/h:y	Ljava/lang/String;
    //   319: aload 10
    //   321: aload 13
    //   323: invokestatic 319	cmn/p:a	(Landroid/content/SharedPreferences;)Ljava/lang/String;
    //   326: putfield 76	cmn/h:j	Ljava/lang/String;
    //   329: aload 12
    //   331: ldc_w 321
    //   334: invokevirtual 325	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   337: checkcast 327	android/telephony/TelephonyManager
    //   340: astore 9
    //   342: aload 10
    //   344: aload 9
    //   346: invokevirtual 330	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   349: putfield 80	cmn/h:k	Ljava/lang/String;
    //   352: aload 10
    //   354: getfield 80	cmn/h:k	Ljava/lang/String;
    //   357: ifnonnull +10 -> 367
    //   360: aload 10
    //   362: ldc 78
    //   364: putfield 80	cmn/h:k	Ljava/lang/String;
    //   367: aload 10
    //   369: aload 9
    //   371: invokevirtual 333	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
    //   374: putfield 82	cmn/h:l	Ljava/lang/String;
    //   377: aload 10
    //   379: getfield 82	cmn/h:l	Ljava/lang/String;
    //   382: ifnonnull +10 -> 392
    //   385: aload 10
    //   387: ldc 78
    //   389: putfield 82	cmn/h:l	Ljava/lang/String;
    //   392: aload 10
    //   394: aload 9
    //   396: invokevirtual 336	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   399: putfield 92	cmn/h:e	Ljava/lang/String;
    //   402: aload 10
    //   404: getfield 92	cmn/h:e	Ljava/lang/String;
    //   407: ifnonnull +10 -> 417
    //   410: aload 10
    //   412: ldc 78
    //   414: putfield 92	cmn/h:e	Ljava/lang/String;
    //   417: aload 10
    //   419: putstatic 48	cmn/h:h	Lcmn/h;
    //   422: new 338	android/util/DisplayMetrics
    //   425: dup
    //   426: invokespecial 339	android/util/DisplayMetrics:<init>	()V
    //   429: astore 9
    //   431: aload_0
    //   432: ldc_w 341
    //   435: invokevirtual 325	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   438: checkcast 343	android/view/WindowManager
    //   441: invokeinterface 347 1 0
    //   446: aload 9
    //   448: invokevirtual 353	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   451: aload 10
    //   453: aload 9
    //   455: getfield 356	android/util/DisplayMetrics:densityDpi	I
    //   458: putfield 90	cmn/h:d	I
    //   461: aload 10
    //   463: aload 9
    //   465: getfield 359	android/util/DisplayMetrics:heightPixels	I
    //   468: aload 9
    //   470: getfield 362	android/util/DisplayMetrics:widthPixels	I
    //   473: invokestatic 368	java/lang/Math:min	(II)I
    //   476: i2f
    //   477: aload 9
    //   479: getfield 372	android/util/DisplayMetrics:density	F
    //   482: fdiv
    //   483: f2i
    //   484: putfield 84	cmn/h:a	I
    //   487: aload 10
    //   489: astore_0
    //   490: goto -473 -> 17
    //   493: astore_0
    //   494: aload 10
    //   496: astore_0
    //   497: goto -480 -> 17
    //   500: astore 10
    //   502: iload 4
    //   504: istore_1
    //   505: lconst_0
    //   506: lstore 5
    //   508: iload_1
    //   509: istore_3
    //   510: iload_2
    //   511: istore_1
    //   512: iload_3
    //   513: istore_2
    //   514: goto -408 -> 106
    //   517: astore_0
    //   518: ldc 2
    //   520: monitorexit
    //   521: aload_0
    //   522: athrow
    //   523: astore 9
    //   525: goto -108 -> 417
    //   528: astore 10
    //   530: goto -25 -> 505
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	533	0	paramContext	Context
    //   74	438	1	i1	int
    //   3	511	2	i2	int
    //   1	512	3	i3	int
    //   61	442	4	i4	int
    //   56	451	5	l1	long
    //   116	126	7	l2	long
    //   33	445	9	localObject1	Object
    //   523	1	9	localThrowable	Throwable
    //   142	353	10	localObject2	Object
    //   500	1	10	localException1	Exception
    //   528	1	10	localException2	Exception
    //   269	20	11	str	String
    //   26	304	12	localContext	Context
    //   40	282	13	localSharedPreferences	android.content.SharedPreferences
    // Exception table:
    //   from	to	target	type
    //   422	487	493	java/lang/Throwable
    //   63	75	500	java/lang/Exception
    //   7	17	517	finally
    //   22	58	517	finally
    //   63	75	517	finally
    //   77	88	517	finally
    //   90	102	517	finally
    //   135	206	517	finally
    //   206	219	517	finally
    //   232	271	517	finally
    //   275	302	517	finally
    //   302	329	517	finally
    //   329	367	517	finally
    //   367	392	517	finally
    //   392	417	517	finally
    //   417	422	517	finally
    //   422	487	517	finally
    //   329	367	523	java/lang/Throwable
    //   367	392	523	java/lang/Throwable
    //   392	417	523	java/lang/Throwable
    //   77	88	528	java/lang/Exception
    //   90	102	528	java/lang/Exception
  }
  
  private static String a(ContentResolver paramContentResolver)
  {
    Object localObject = Settings.Secure.getString(paramContentResolver, "android_id");
    if ((localObject != null) && (!((String)localObject).equals("9774d56d682e549c")))
    {
      paramContentResolver = (ContentResolver)localObject;
      if (!((String)localObject).equals("67ef2b122f51423f")) {}
    }
    else
    {
      paramContentResolver = "";
    }
    localObject = paramContentResolver;
    if (paramContentResolver.length() == 0)
    {
      paramContentResolver = new Random();
      localObject = new StringBuffer();
      int i1 = 0;
      while (i1 < 16)
      {
        ((StringBuffer)localObject).append(paramContentResolver.nextInt(16) + 97);
        i1 += 1;
      }
      localObject = ((StringBuffer)localObject).toString();
    }
    return localObject;
  }
  
  private void b(Context paramContext)
  {
    if (this.w != null) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    this.f.clear();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(paramContext.getPackageName())) {
        this.i = localPackageInfo.versionCode;
      }
      if (localPackageInfo.packageName.equals("com.android.vending")) {
        this.b = localPackageInfo.versionCode;
      }
      if (localPackageInfo.packageName.contains("swisscodemonkeys"))
      {
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(",");
        }
        int i2 = localPackageInfo.packageName.lastIndexOf('.');
        localStringBuilder.append(localPackageInfo.packageName.substring(i2 + 1));
      }
      this.f.add(localPackageInfo.packageName);
      i1 += 1;
    }
    this.w = localStringBuilder.toString();
    this.v = i1;
    try
    {
      paramContext = new StatFs("/data/app");
      this.c = ((int)(paramContext.getAvailableBlocks() * paramContext.getBlockSize() / 1024L));
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public final String a()
  {
    return this.k;
  }
  
  public final String b()
  {
    return this.l;
  }
  
  public final String c()
  {
    return this.r;
  }
  
  public final String d()
  {
    return this.z.getResources().getConfiguration().locale.getLanguage();
  }
  
  public final int e()
  {
    return this.v;
  }
  
  public final int f()
  {
    return this.i;
  }
  
  public final String g()
  {
    return this.q;
  }
  
  public final String h()
  {
    return this.o;
  }
  
  public final String i()
  {
    return i.b(new byte[] { (byte)(this.m >> 24 & 0xFF), (byte)(this.m >> 16 & 0xFF), (byte)(this.m >> 8 & 0xFF), (byte)(this.m & 0xFF) });
  }
  
  public final String j()
  {
    byte[] arrayOfByte = new byte[8];
    int i1 = 0;
    while (i1 < 8)
    {
      arrayOfByte[i1] = ((byte)(int)(this.n >> i1 * 8 & 0xFF));
      i1 += 1;
    }
    return i.b(arrayOfByte);
  }
  
  public final int k()
  {
    return this.p;
  }
  
  public final String l()
  {
    return this.y;
  }
  
  public final String m()
  {
    return this.s;
  }
  
  public final String n()
  {
    return this.t;
  }
  
  public final String o()
  {
    return this.u;
  }
}
