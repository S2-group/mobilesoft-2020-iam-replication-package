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
public final class f
  implements Serializable
{
  public static final boolean g;
  private static f h = null;
  private int A;
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
  
  private f(int paramInt1, int paramInt2, String paramString1, long paramLong, String paramString2)
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
  public static f a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: ldc 2
    //   6: monitorenter
    //   7: getstatic 47	cmn/f:h	Lcmn/f;
    //   10: ifnull +12 -> 22
    //   13: getstatic 47	cmn/f:h	Lcmn/f;
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual 205	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   26: astore 13
    //   28: aload 13
    //   30: invokevirtual 209	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   33: astore 9
    //   35: aload 13
    //   37: invokestatic 215	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   40: astore 12
    //   42: new 217	java/util/Date
    //   45: dup
    //   46: invokespecial 218	java/util/Date:<init>	()V
    //   49: invokevirtual 222	java/util/Date:getTime	()J
    //   52: ldc2_w 223
    //   55: ldiv
    //   56: lstore 5
    //   58: lload 5
    //   60: l2i
    //   61: istore 4
    //   63: aload 12
    //   65: ldc -30
    //   67: iload 4
    //   69: invokeinterface 232 3 0
    //   74: istore_1
    //   75: iload_3
    //   76: istore_2
    //   77: aload 12
    //   79: ldc -22
    //   81: iconst_0
    //   82: invokeinterface 232 3 0
    //   87: istore_3
    //   88: iload_3
    //   89: istore_2
    //   90: aload 12
    //   92: ldc -20
    //   94: lconst_0
    //   95: invokeinterface 240 4 0
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
    //   135: aload 12
    //   137: invokeinterface 244 1 0
    //   142: astore 10
    //   144: aload 9
    //   146: invokestatic 247	cmn/f:a	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   149: astore 9
    //   151: aload 9
    //   153: invokevirtual 250	java/lang/String:hashCode	()I
    //   156: istore_3
    //   157: aload 9
    //   159: invokestatic 252	cmn/f:a	(Ljava/lang/String;)J
    //   162: lstore 7
    //   164: aload 10
    //   166: ldc -30
    //   168: iload_2
    //   169: invokeinterface 258 3 0
    //   174: pop
    //   175: aload 10
    //   177: ldc -22
    //   179: iload_3
    //   180: invokeinterface 258 3 0
    //   185: pop
    //   186: aload 10
    //   188: ldc -20
    //   190: lload 7
    //   192: invokeinterface 262 4 0
    //   197: pop
    //   198: invokestatic 267	cmn/a:a	()Lcmn/a;
    //   201: aload 10
    //   203: invokevirtual 270	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   206: aload 13
    //   208: invokevirtual 209	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   211: ldc_w 272
    //   214: invokestatic 278	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   217: astore 10
    //   219: aload 10
    //   221: astore 9
    //   223: aload 10
    //   225: ifnonnull +7 -> 232
    //   228: ldc 77
    //   230: astore 9
    //   232: new 2	cmn/f
    //   235: dup
    //   236: iload_3
    //   237: iload_2
    //   238: getstatic 283	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   241: lload 7
    //   243: aload 9
    //   245: invokespecial 285	cmn/f:<init>	(IILjava/lang/String;JLjava/lang/String;)V
    //   248: astore 10
    //   250: aload 10
    //   252: aload 13
    //   254: putfield 287	cmn/f:z	Landroid/content/Context;
    //   257: aload 10
    //   259: aload 13
    //   261: invokespecial 290	cmn/f:b	(Landroid/content/Context;)V
    //   264: aload 13
    //   266: invokevirtual 293	android/content/Context:getPackageName	()Ljava/lang/String;
    //   269: astore 11
    //   271: aload 11
    //   273: astore 9
    //   275: aload 11
    //   277: ldc_w 295
    //   280: invokevirtual 299	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   283: ifeq +19 -> 302
    //   286: aload 11
    //   288: aload 11
    //   290: bipush 46
    //   292: invokevirtual 303	java/lang/String:lastIndexOf	(I)I
    //   295: iconst_1
    //   296: iadd
    //   297: invokevirtual 307	java/lang/String:substring	(I)Ljava/lang/String;
    //   300: astore 9
    //   302: aload 10
    //   304: aload 9
    //   306: putfield 309	cmn/f:x	Ljava/lang/String;
    //   309: aload 10
    //   311: aload 13
    //   313: invokevirtual 293	android/content/Context:getPackageName	()Ljava/lang/String;
    //   316: putfield 311	cmn/f:y	Ljava/lang/String;
    //   319: aload 10
    //   321: aload 12
    //   323: invokestatic 316	cmn/m:a	(Landroid/content/SharedPreferences;)Ljava/lang/String;
    //   326: putfield 75	cmn/f:j	Ljava/lang/String;
    //   329: aload 13
    //   331: ldc_w 318
    //   334: invokevirtual 322	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   337: checkcast 324	android/telephony/TelephonyManager
    //   340: astore 9
    //   342: aload 10
    //   344: aload 9
    //   346: invokevirtual 327	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   349: putfield 79	cmn/f:k	Ljava/lang/String;
    //   352: aload 10
    //   354: getfield 79	cmn/f:k	Ljava/lang/String;
    //   357: ifnonnull +10 -> 367
    //   360: aload 10
    //   362: ldc 77
    //   364: putfield 79	cmn/f:k	Ljava/lang/String;
    //   367: aload 10
    //   369: aload 9
    //   371: invokevirtual 330	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
    //   374: putfield 81	cmn/f:l	Ljava/lang/String;
    //   377: aload 10
    //   379: getfield 81	cmn/f:l	Ljava/lang/String;
    //   382: ifnonnull +10 -> 392
    //   385: aload 10
    //   387: ldc 77
    //   389: putfield 81	cmn/f:l	Ljava/lang/String;
    //   392: aload 10
    //   394: aload 9
    //   396: invokevirtual 333	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   399: putfield 91	cmn/f:e	Ljava/lang/String;
    //   402: aload 10
    //   404: getfield 91	cmn/f:e	Ljava/lang/String;
    //   407: ifnonnull +10 -> 417
    //   410: aload 10
    //   412: ldc 77
    //   414: putfield 91	cmn/f:e	Ljava/lang/String;
    //   417: aload 10
    //   419: aload 12
    //   421: ldc_w 335
    //   424: iconst_0
    //   425: invokeinterface 232 3 0
    //   430: putfield 337	cmn/f:A	I
    //   433: aload 10
    //   435: putstatic 47	cmn/f:h	Lcmn/f;
    //   438: new 339	android/util/DisplayMetrics
    //   441: dup
    //   442: invokespecial 340	android/util/DisplayMetrics:<init>	()V
    //   445: astore 9
    //   447: aload_0
    //   448: ldc_w 342
    //   451: invokevirtual 322	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   454: checkcast 344	android/view/WindowManager
    //   457: invokeinterface 348 1 0
    //   462: aload 9
    //   464: invokevirtual 354	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   467: aload 10
    //   469: aload 9
    //   471: getfield 357	android/util/DisplayMetrics:densityDpi	I
    //   474: putfield 89	cmn/f:d	I
    //   477: aload 10
    //   479: aload 9
    //   481: getfield 360	android/util/DisplayMetrics:heightPixels	I
    //   484: aload 9
    //   486: getfield 363	android/util/DisplayMetrics:widthPixels	I
    //   489: invokestatic 369	java/lang/Math:min	(II)I
    //   492: i2f
    //   493: aload 9
    //   495: getfield 373	android/util/DisplayMetrics:density	F
    //   498: fdiv
    //   499: f2i
    //   500: putfield 83	cmn/f:a	I
    //   503: aload 10
    //   505: astore_0
    //   506: goto -489 -> 17
    //   509: astore_0
    //   510: aload 10
    //   512: astore_0
    //   513: goto -496 -> 17
    //   516: astore 10
    //   518: iload 4
    //   520: istore_1
    //   521: lconst_0
    //   522: lstore 5
    //   524: iload_1
    //   525: istore_3
    //   526: iload_2
    //   527: istore_1
    //   528: iload_3
    //   529: istore_2
    //   530: goto -424 -> 106
    //   533: astore_0
    //   534: ldc 2
    //   536: monitorexit
    //   537: aload_0
    //   538: athrow
    //   539: astore 9
    //   541: goto -124 -> 417
    //   544: astore 10
    //   546: goto -25 -> 521
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	549	0	paramContext	Context
    //   74	454	1	i1	int
    //   3	527	2	i2	int
    //   1	528	3	i3	int
    //   61	458	4	i4	int
    //   56	467	5	l1	long
    //   116	126	7	l2	long
    //   33	461	9	localObject1	Object
    //   539	1	9	localThrowable	Throwable
    //   142	369	10	localObject2	Object
    //   516	1	10	localException1	Exception
    //   544	1	10	localException2	Exception
    //   269	20	11	str	String
    //   40	380	12	localSharedPreferences	android.content.SharedPreferences
    //   26	304	13	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   438	503	509	java/lang/Throwable
    //   63	75	516	java/lang/Exception
    //   7	17	533	finally
    //   22	58	533	finally
    //   63	75	533	finally
    //   77	88	533	finally
    //   90	102	533	finally
    //   135	206	533	finally
    //   206	219	533	finally
    //   232	271	533	finally
    //   275	302	533	finally
    //   302	329	533	finally
    //   329	367	533	finally
    //   367	392	533	finally
    //   392	417	533	finally
    //   417	438	533	finally
    //   438	503	533	finally
    //   329	367	539	java/lang/Throwable
    //   367	392	539	java/lang/Throwable
    //   392	417	539	java/lang/Throwable
    //   77	88	544	java/lang/Exception
    //   90	102	544	java/lang/Exception
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
    return g.b(new byte[] { (byte)(this.m >> 24 & 0xFF), (byte)(this.m >> 16 & 0xFF), (byte)(this.m >> 8 & 0xFF), (byte)(this.m & 0xFF) });
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
    return g.b(arrayOfByte);
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
