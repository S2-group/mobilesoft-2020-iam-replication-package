package cmn;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import java.util.Random;
import java.util.Set;

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
  private String z;
  
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
    //   7: getstatic 43	cmn/f:h	Lcmn/f;
    //   10: ifnull +12 -> 22
    //   13: getstatic 43	cmn/f:h	Lcmn/f;
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual 201	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   26: astore 13
    //   28: aload 13
    //   30: invokevirtual 205	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   33: astore 9
    //   35: aload 13
    //   37: invokestatic 211	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   40: astore 12
    //   42: new 213	java/util/Date
    //   45: dup
    //   46: invokespecial 214	java/util/Date:<init>	()V
    //   49: invokevirtual 218	java/util/Date:getTime	()J
    //   52: ldc2_w 219
    //   55: ldiv
    //   56: lstore 5
    //   58: lload 5
    //   60: l2i
    //   61: istore 4
    //   63: aload 12
    //   65: ldc -34
    //   67: iload 4
    //   69: invokeinterface 228 3 0
    //   74: istore_1
    //   75: iload_3
    //   76: istore_2
    //   77: aload 12
    //   79: ldc -26
    //   81: iconst_0
    //   82: invokeinterface 228 3 0
    //   87: istore_3
    //   88: iload_3
    //   89: istore_2
    //   90: aload 12
    //   92: ldc -24
    //   94: lconst_0
    //   95: invokeinterface 236 4 0
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
    //   137: invokeinterface 240 1 0
    //   142: astore 10
    //   144: aload 9
    //   146: invokestatic 243	cmn/f:a	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   149: astore 9
    //   151: aload 9
    //   153: invokevirtual 246	java/lang/String:hashCode	()I
    //   156: istore_3
    //   157: aload 9
    //   159: invokestatic 248	cmn/f:a	(Ljava/lang/String;)J
    //   162: lstore 7
    //   164: aload 10
    //   166: ldc -34
    //   168: iload_2
    //   169: invokeinterface 254 3 0
    //   174: pop
    //   175: aload 10
    //   177: ldc -26
    //   179: iload_3
    //   180: invokeinterface 254 3 0
    //   185: pop
    //   186: aload 10
    //   188: ldc -24
    //   190: lload 7
    //   192: invokeinterface 258 4 0
    //   197: pop
    //   198: invokestatic 263	cmn/a:a	()Lcmn/a;
    //   201: aload 10
    //   203: invokevirtual 266	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   206: aload 13
    //   208: invokevirtual 205	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   211: ldc_w 268
    //   214: invokestatic 274	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   217: astore 10
    //   219: aload 10
    //   221: astore 9
    //   223: aload 10
    //   225: ifnonnull +7 -> 232
    //   228: ldc 73
    //   230: astore 9
    //   232: new 2	cmn/f
    //   235: dup
    //   236: iload_3
    //   237: iload_2
    //   238: getstatic 279	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   241: lload 7
    //   243: aload 9
    //   245: invokespecial 281	cmn/f:<init>	(IILjava/lang/String;JLjava/lang/String;)V
    //   248: astore 10
    //   250: aload 10
    //   252: aload 13
    //   254: invokevirtual 285	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   257: invokevirtual 291	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   260: getfield 297	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   263: invokevirtual 302	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   266: putfield 304	cmn/f:x	Ljava/lang/String;
    //   269: aload 10
    //   271: aload 13
    //   273: invokespecial 307	cmn/f:b	(Landroid/content/Context;)V
    //   276: aload 13
    //   278: invokevirtual 310	android/content/Context:getPackageName	()Ljava/lang/String;
    //   281: astore 11
    //   283: aload 11
    //   285: astore 9
    //   287: aload 11
    //   289: ldc_w 312
    //   292: invokevirtual 316	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   295: ifeq +19 -> 314
    //   298: aload 11
    //   300: aload 11
    //   302: bipush 46
    //   304: invokevirtual 320	java/lang/String:lastIndexOf	(I)I
    //   307: iconst_1
    //   308: iadd
    //   309: invokevirtual 324	java/lang/String:substring	(I)Ljava/lang/String;
    //   312: astore 9
    //   314: aload 10
    //   316: aload 9
    //   318: putfield 326	cmn/f:y	Ljava/lang/String;
    //   321: aload 10
    //   323: aload 13
    //   325: invokevirtual 310	android/content/Context:getPackageName	()Ljava/lang/String;
    //   328: putfield 328	cmn/f:z	Ljava/lang/String;
    //   331: aload 10
    //   333: aload 12
    //   335: invokestatic 333	cmn/k:a	(Landroid/content/SharedPreferences;)Ljava/lang/String;
    //   338: putfield 71	cmn/f:j	Ljava/lang/String;
    //   341: aload 13
    //   343: ldc_w 335
    //   346: invokevirtual 339	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   349: checkcast 341	android/telephony/TelephonyManager
    //   352: astore 9
    //   354: aload 10
    //   356: aload 9
    //   358: invokevirtual 344	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   361: putfield 75	cmn/f:k	Ljava/lang/String;
    //   364: aload 10
    //   366: getfield 75	cmn/f:k	Ljava/lang/String;
    //   369: ifnonnull +10 -> 379
    //   372: aload 10
    //   374: ldc 73
    //   376: putfield 75	cmn/f:k	Ljava/lang/String;
    //   379: aload 10
    //   381: aload 9
    //   383: invokevirtual 347	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
    //   386: putfield 77	cmn/f:l	Ljava/lang/String;
    //   389: aload 10
    //   391: getfield 77	cmn/f:l	Ljava/lang/String;
    //   394: ifnonnull +10 -> 404
    //   397: aload 10
    //   399: ldc 73
    //   401: putfield 77	cmn/f:l	Ljava/lang/String;
    //   404: aload 10
    //   406: aload 9
    //   408: invokevirtual 350	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   411: putfield 87	cmn/f:e	Ljava/lang/String;
    //   414: aload 10
    //   416: getfield 87	cmn/f:e	Ljava/lang/String;
    //   419: ifnonnull +10 -> 429
    //   422: aload 10
    //   424: ldc 73
    //   426: putfield 87	cmn/f:e	Ljava/lang/String;
    //   429: aload 10
    //   431: aload 12
    //   433: ldc_w 352
    //   436: iconst_0
    //   437: invokeinterface 228 3 0
    //   442: putfield 354	cmn/f:A	I
    //   445: aload 10
    //   447: putstatic 43	cmn/f:h	Lcmn/f;
    //   450: new 356	android/util/DisplayMetrics
    //   453: dup
    //   454: invokespecial 357	android/util/DisplayMetrics:<init>	()V
    //   457: astore 9
    //   459: aload_0
    //   460: ldc_w 359
    //   463: invokevirtual 339	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   466: checkcast 361	android/view/WindowManager
    //   469: invokeinterface 365 1 0
    //   474: aload 9
    //   476: invokevirtual 371	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   479: aload 10
    //   481: aload 9
    //   483: getfield 374	android/util/DisplayMetrics:densityDpi	I
    //   486: putfield 85	cmn/f:d	I
    //   489: aload 10
    //   491: aload 9
    //   493: getfield 377	android/util/DisplayMetrics:heightPixels	I
    //   496: aload 9
    //   498: getfield 380	android/util/DisplayMetrics:widthPixels	I
    //   501: invokestatic 386	java/lang/Math:min	(II)I
    //   504: i2f
    //   505: aload 9
    //   507: getfield 390	android/util/DisplayMetrics:density	F
    //   510: fdiv
    //   511: f2i
    //   512: putfield 79	cmn/f:a	I
    //   515: aload 10
    //   517: astore_0
    //   518: goto -501 -> 17
    //   521: astore_0
    //   522: aload 10
    //   524: astore_0
    //   525: goto -508 -> 17
    //   528: astore 10
    //   530: iload 4
    //   532: istore_1
    //   533: lconst_0
    //   534: lstore 5
    //   536: iload_1
    //   537: istore_3
    //   538: iload_2
    //   539: istore_1
    //   540: iload_3
    //   541: istore_2
    //   542: goto -436 -> 106
    //   545: astore_0
    //   546: ldc 2
    //   548: monitorexit
    //   549: aload_0
    //   550: athrow
    //   551: astore 9
    //   553: goto -124 -> 429
    //   556: astore 10
    //   558: goto -25 -> 533
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	561	0	paramContext	Context
    //   74	466	1	i1	int
    //   3	539	2	i2	int
    //   1	540	3	i3	int
    //   61	470	4	i4	int
    //   56	479	5	l1	long
    //   116	126	7	l2	long
    //   33	473	9	localObject1	Object
    //   551	1	9	localThrowable	Throwable
    //   142	381	10	localObject2	Object
    //   528	1	10	localException1	Exception
    //   556	1	10	localException2	Exception
    //   281	20	11	str	String
    //   40	392	12	localSharedPreferences	android.content.SharedPreferences
    //   26	316	13	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   450	515	521	java/lang/Throwable
    //   63	75	528	java/lang/Exception
    //   7	17	545	finally
    //   22	58	545	finally
    //   63	75	545	finally
    //   77	88	545	finally
    //   90	102	545	finally
    //   135	206	545	finally
    //   206	219	545	finally
    //   232	283	545	finally
    //   287	314	545	finally
    //   314	341	545	finally
    //   341	379	545	finally
    //   379	404	545	finally
    //   404	429	545	finally
    //   429	450	545	finally
    //   450	515	545	finally
    //   341	379	551	java/lang/Throwable
    //   379	404	551	java/lang/Throwable
    //   404	429	551	java/lang/Throwable
    //   77	88	556	java/lang/Exception
    //   90	102	556	java/lang/Exception
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
    return this.x;
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
    return this.z;
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
