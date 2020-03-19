package cmn;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
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

@TargetApi(5)
public final class j
  implements Serializable
{
  public static final boolean g;
  private static j h = null;
  private String A = "";
  private Context B;
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
  private String z = "";
  
  static
  {
    if (("google_sdk".equals(Build.PRODUCT)) || ("sdk".equals(Build.PRODUCT))) {}
    for (boolean bool = true;; bool = false)
    {
      g = bool;
      return;
    }
  }
  
  private j(int paramInt1, int paramInt2, String paramString1, long paramLong, String paramString2)
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
  @android.annotation.SuppressLint({"CommitPrefEdits"})
  public static j a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: ldc 2
    //   6: monitorenter
    //   7: getstatic 48	cmn/j:h	Lcmn/j;
    //   10: ifnull +12 -> 22
    //   13: getstatic 48	cmn/j:h	Lcmn/j;
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual 210	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   26: astore 12
    //   28: aload 12
    //   30: invokevirtual 214	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   33: astore 9
    //   35: aload 12
    //   37: invokestatic 220	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   40: astore 13
    //   42: new 222	java/util/Date
    //   45: dup
    //   46: invokespecial 223	java/util/Date:<init>	()V
    //   49: invokevirtual 227	java/util/Date:getTime	()J
    //   52: ldc2_w 228
    //   55: ldiv
    //   56: lstore 5
    //   58: lload 5
    //   60: l2i
    //   61: istore 4
    //   63: aload 13
    //   65: ldc -25
    //   67: iload 4
    //   69: invokeinterface 237 3 0
    //   74: istore_1
    //   75: iload_3
    //   76: istore_2
    //   77: aload 13
    //   79: ldc -17
    //   81: iconst_0
    //   82: invokeinterface 237 3 0
    //   87: istore_3
    //   88: iload_3
    //   89: istore_2
    //   90: aload 13
    //   92: ldc -15
    //   94: lconst_0
    //   95: invokeinterface 245 4 0
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
    //   137: invokeinterface 249 1 0
    //   142: astore 10
    //   144: aload 9
    //   146: invokestatic 252	cmn/j:a	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   149: astore 9
    //   151: aload 9
    //   153: invokevirtual 255	java/lang/String:hashCode	()I
    //   156: istore_3
    //   157: aload 9
    //   159: invokestatic 257	cmn/j:a	(Ljava/lang/String;)J
    //   162: lstore 7
    //   164: aload 10
    //   166: ldc -25
    //   168: iload_2
    //   169: invokeinterface 263 3 0
    //   174: pop
    //   175: aload 10
    //   177: ldc -17
    //   179: iload_3
    //   180: invokeinterface 263 3 0
    //   185: pop
    //   186: aload 10
    //   188: ldc -15
    //   190: lload 7
    //   192: invokeinterface 267 4 0
    //   197: pop
    //   198: invokestatic 272	cmn/a:a	()Lcmn/a;
    //   201: aload 10
    //   203: invokevirtual 275	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   206: aload 12
    //   208: invokevirtual 214	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   211: ldc_w 277
    //   214: invokestatic 283	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   217: astore 10
    //   219: aload 10
    //   221: astore 9
    //   223: aload 10
    //   225: ifnonnull +7 -> 232
    //   228: ldc 78
    //   230: astore 9
    //   232: new 2	cmn/j
    //   235: dup
    //   236: iload_3
    //   237: iload_2
    //   238: getstatic 288	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   241: lload 7
    //   243: aload 9
    //   245: invokespecial 290	cmn/j:<init>	(IILjava/lang/String;JLjava/lang/String;)V
    //   248: astore 10
    //   250: aload 10
    //   252: aload 12
    //   254: putfield 292	cmn/j:B	Landroid/content/Context;
    //   257: aload 10
    //   259: aload 12
    //   261: invokespecial 295	cmn/j:b	(Landroid/content/Context;)V
    //   264: aload 12
    //   266: invokevirtual 298	android/content/Context:getPackageName	()Ljava/lang/String;
    //   269: astore 11
    //   271: aload 11
    //   273: astore 9
    //   275: aload 11
    //   277: invokestatic 301	cmn/j:b	(Ljava/lang/String;)Z
    //   280: ifeq +19 -> 299
    //   283: aload 11
    //   285: aload 11
    //   287: bipush 46
    //   289: invokevirtual 305	java/lang/String:lastIndexOf	(I)I
    //   292: iconst_1
    //   293: iadd
    //   294: invokevirtual 309	java/lang/String:substring	(I)Ljava/lang/String;
    //   297: astore 9
    //   299: aload 10
    //   301: aload 9
    //   303: putfield 311	cmn/j:x	Ljava/lang/String;
    //   306: aload 10
    //   308: aload 12
    //   310: invokevirtual 298	android/content/Context:getPackageName	()Ljava/lang/String;
    //   313: putfield 313	cmn/j:y	Ljava/lang/String;
    //   316: aload 10
    //   318: aload 13
    //   320: invokestatic 318	cmn/aj:a	(Landroid/content/SharedPreferences;)Ljava/lang/String;
    //   323: putfield 76	cmn/j:j	Ljava/lang/String;
    //   326: aload 10
    //   328: aload 12
    //   330: ldc_w 320
    //   333: invokestatic 323	cmn/j:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   336: putfield 96	cmn/j:A	Ljava/lang/String;
    //   339: aload 12
    //   341: ldc_w 325
    //   344: invokevirtual 329	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   347: checkcast 331	android/telephony/TelephonyManager
    //   350: astore 9
    //   352: aload 10
    //   354: aload 9
    //   356: invokevirtual 334	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   359: putfield 80	cmn/j:k	Ljava/lang/String;
    //   362: aload 10
    //   364: getfield 80	cmn/j:k	Ljava/lang/String;
    //   367: ifnonnull +10 -> 377
    //   370: aload 10
    //   372: ldc 78
    //   374: putfield 80	cmn/j:k	Ljava/lang/String;
    //   377: aload 10
    //   379: aload 9
    //   381: invokevirtual 337	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
    //   384: putfield 82	cmn/j:l	Ljava/lang/String;
    //   387: aload 10
    //   389: getfield 82	cmn/j:l	Ljava/lang/String;
    //   392: ifnonnull +10 -> 402
    //   395: aload 10
    //   397: ldc 78
    //   399: putfield 82	cmn/j:l	Ljava/lang/String;
    //   402: aload 10
    //   404: aload 9
    //   406: invokevirtual 340	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   409: putfield 92	cmn/j:e	Ljava/lang/String;
    //   412: aload 10
    //   414: getfield 92	cmn/j:e	Ljava/lang/String;
    //   417: ifnonnull +10 -> 427
    //   420: aload 10
    //   422: ldc 78
    //   424: putfield 92	cmn/j:e	Ljava/lang/String;
    //   427: new 342	android/util/DisplayMetrics
    //   430: dup
    //   431: invokespecial 343	android/util/DisplayMetrics:<init>	()V
    //   434: astore 9
    //   436: aload_0
    //   437: ldc_w 345
    //   440: invokevirtual 329	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   443: checkcast 347	android/view/WindowManager
    //   446: invokeinterface 351 1 0
    //   451: aload 9
    //   453: invokevirtual 357	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   456: aload 10
    //   458: aload 9
    //   460: getfield 360	android/util/DisplayMetrics:densityDpi	I
    //   463: putfield 90	cmn/j:d	I
    //   466: aload 10
    //   468: aload 9
    //   470: getfield 363	android/util/DisplayMetrics:heightPixels	I
    //   473: aload 9
    //   475: getfield 366	android/util/DisplayMetrics:widthPixels	I
    //   478: invokestatic 372	java/lang/Math:min	(II)I
    //   481: i2f
    //   482: aload 9
    //   484: getfield 376	android/util/DisplayMetrics:density	F
    //   487: fdiv
    //   488: f2i
    //   489: putfield 84	cmn/j:a	I
    //   492: aload_0
    //   493: invokevirtual 380	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   496: aload_0
    //   497: invokevirtual 298	android/content/Context:getPackageName	()Ljava/lang/String;
    //   500: invokevirtual 386	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   503: astore_0
    //   504: aload_0
    //   505: ifnull +43 -> 548
    //   508: aload 10
    //   510: aload_0
    //   511: putfield 94	cmn/j:z	Ljava/lang/String;
    //   514: aload 10
    //   516: putstatic 48	cmn/j:h	Lcmn/j;
    //   519: aload 10
    //   521: astore_0
    //   522: goto -505 -> 17
    //   525: astore_0
    //   526: ldc 2
    //   528: monitorexit
    //   529: aload_0
    //   530: athrow
    //   531: astore 10
    //   533: iload 4
    //   535: istore_1
    //   536: lconst_0
    //   537: lstore 5
    //   539: iload_1
    //   540: istore_3
    //   541: iload_2
    //   542: istore_1
    //   543: iload_3
    //   544: istore_2
    //   545: goto -439 -> 106
    //   548: ldc 78
    //   550: astore_0
    //   551: goto -43 -> 508
    //   554: astore_0
    //   555: goto -41 -> 514
    //   558: astore 9
    //   560: goto -133 -> 427
    //   563: astore 10
    //   565: goto -29 -> 536
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	568	0	paramContext	Context
    //   74	469	1	i1	int
    //   3	542	2	i2	int
    //   1	543	3	i3	int
    //   61	473	4	i4	int
    //   56	482	5	l1	long
    //   116	126	7	l2	long
    //   33	450	9	localObject1	Object
    //   558	1	9	localException1	Exception
    //   142	378	10	localObject2	Object
    //   531	1	10	localException2	Exception
    //   563	1	10	localException3	Exception
    //   269	17	11	str	String
    //   26	314	12	localContext	Context
    //   40	279	13	localSharedPreferences	android.content.SharedPreferences
    // Exception table:
    //   from	to	target	type
    //   7	17	525	finally
    //   22	58	525	finally
    //   63	75	525	finally
    //   77	88	525	finally
    //   90	102	525	finally
    //   135	206	525	finally
    //   206	219	525	finally
    //   232	271	525	finally
    //   275	299	525	finally
    //   299	339	525	finally
    //   339	377	525	finally
    //   377	402	525	finally
    //   402	427	525	finally
    //   427	504	525	finally
    //   508	514	525	finally
    //   514	519	525	finally
    //   63	75	531	java/lang/Exception
    //   427	504	554	java/lang/Exception
    //   508	514	554	java/lang/Exception
    //   339	377	558	java/lang/Exception
    //   377	402	558	java/lang/Exception
    //   402	427	558	java/lang/Exception
    //   77	88	563	java/lang/Exception
    //   90	102	563	java/lang/Exception
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
  
  private static String a(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString(paramString);
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return "";
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
      if (b(localPackageInfo.packageName))
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
  
  public static boolean b(String paramString)
  {
    return (paramString.startsWith("com.appspot.swisscodemonkeys.")) || (paramString.startsWith("com.apptornado.")) || (paramString.startsWith("com.appbrain."));
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
    return this.B.getResources().getConfiguration().locale.getLanguage();
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
    return this.z;
  }
  
  public final String h()
  {
    return this.q;
  }
  
  public final String i()
  {
    return this.o;
  }
  
  public final String j()
  {
    return k.b(new byte[] { (byte)(this.m >> 24 & 0xFF), (byte)(this.m >> 16 & 0xFF), (byte)(this.m >> 8 & 0xFF), (byte)(this.m & 0xFF) });
  }
  
  public final String k()
  {
    byte[] arrayOfByte = new byte[8];
    int i1 = 0;
    while (i1 < 8)
    {
      arrayOfByte[i1] = ((byte)(int)(this.n >> i1 * 8 & 0xFF));
      i1 += 1;
    }
    return k.b(arrayOfByte);
  }
  
  public final int l()
  {
    return this.p;
  }
  
  public final String m()
  {
    return this.y;
  }
  
  public final String n()
  {
    return this.s;
  }
  
  public final String o()
  {
    return this.t;
  }
  
  public final String p()
  {
    return this.u;
  }
}
