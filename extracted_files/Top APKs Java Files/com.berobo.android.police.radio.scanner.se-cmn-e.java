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

public final class e
  implements Serializable
{
  private static e e = null;
  public int a = -1;
  public int b = -1;
  public int c = -1;
  public Set d = new HashSet();
  private String f = null;
  private String g = null;
  private String h = "";
  private String i = "";
  private final int j;
  private final long k;
  private final String l;
  private final int m;
  private final String n;
  private final String o;
  private final String p;
  private final String q;
  private final String r;
  private int s;
  private String t;
  private String u;
  private String v;
  private String w;
  private int x;
  
  private e(int paramInt1, int paramInt2, String paramString1, long paramLong, String paramString2)
  {
    this.j = paramInt1;
    this.m = paramInt2;
    this.n = paramString1;
    this.k = paramLong;
    this.l = paramString2;
    this.o = (Build.BRAND + " " + Build.DEVICE);
    this.p = Build.MODEL;
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
    this.q = paramString1;
    this.r = Build.PRODUCT;
  }
  
  private static long a(String paramString)
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
  public static e a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: ldc 2
    //   6: monitorenter
    //   7: getstatic 39	cmn/e:e	Lcmn/e;
    //   10: ifnull +12 -> 22
    //   13: getstatic 39	cmn/e:e	Lcmn/e;
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual 187	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   26: astore 13
    //   28: aload 13
    //   30: invokevirtual 191	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   33: astore 9
    //   35: aload 13
    //   37: invokestatic 197	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   40: astore 12
    //   42: new 199	java/util/Date
    //   45: dup
    //   46: invokespecial 200	java/util/Date:<init>	()V
    //   49: invokevirtual 204	java/util/Date:getTime	()J
    //   52: ldc2_w 205
    //   55: ldiv
    //   56: lstore 5
    //   58: lload 5
    //   60: l2i
    //   61: istore 4
    //   63: aload 12
    //   65: ldc -48
    //   67: iload 4
    //   69: invokeinterface 214 3 0
    //   74: istore_1
    //   75: iload_3
    //   76: istore_2
    //   77: aload 12
    //   79: ldc -40
    //   81: iconst_0
    //   82: invokeinterface 214 3 0
    //   87: istore_3
    //   88: iload_3
    //   89: istore_2
    //   90: aload 12
    //   92: ldc -38
    //   94: lconst_0
    //   95: invokeinterface 222 4 0
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
    //   137: invokeinterface 226 1 0
    //   142: astore 10
    //   144: aload 9
    //   146: invokestatic 229	cmn/e:a	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   149: astore 9
    //   151: aload 9
    //   153: invokevirtual 232	java/lang/String:hashCode	()I
    //   156: istore_3
    //   157: aload 9
    //   159: invokestatic 234	cmn/e:a	(Ljava/lang/String;)J
    //   162: lstore 7
    //   164: aload 10
    //   166: ldc -48
    //   168: iload_2
    //   169: invokeinterface 240 3 0
    //   174: pop
    //   175: aload 10
    //   177: ldc -40
    //   179: iload_3
    //   180: invokeinterface 240 3 0
    //   185: pop
    //   186: aload 10
    //   188: ldc -38
    //   190: lload 7
    //   192: invokeinterface 244 4 0
    //   197: pop
    //   198: invokestatic 249	cmn/a:a	()Lcmn/a;
    //   201: aload 10
    //   203: invokevirtual 252	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   206: aload 13
    //   208: invokevirtual 191	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   211: ldc -2
    //   213: invokestatic 260	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   216: astore 10
    //   218: aload 10
    //   220: astore 9
    //   222: aload 10
    //   224: ifnonnull +7 -> 231
    //   227: ldc 52
    //   229: astore 9
    //   231: new 2	cmn/e
    //   234: dup
    //   235: iload_3
    //   236: iload_2
    //   237: getstatic 265	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   240: lload 7
    //   242: aload 9
    //   244: invokespecial 267	cmn/e:<init>	(IILjava/lang/String;JLjava/lang/String;)V
    //   247: astore 10
    //   249: aload 10
    //   251: aload 13
    //   253: invokevirtual 271	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   256: invokevirtual 277	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   259: getfield 283	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   262: invokevirtual 288	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   265: putfield 290	cmn/e:u	Ljava/lang/String;
    //   268: aload 10
    //   270: aload 13
    //   272: invokespecial 293	cmn/e:b	(Landroid/content/Context;)V
    //   275: aload 13
    //   277: invokevirtual 296	android/content/Context:getPackageName	()Ljava/lang/String;
    //   280: astore 11
    //   282: aload 11
    //   284: astore 9
    //   286: aload 11
    //   288: ldc_w 298
    //   291: invokevirtual 302	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   294: ifeq +19 -> 313
    //   297: aload 11
    //   299: aload 11
    //   301: bipush 46
    //   303: invokevirtual 306	java/lang/String:lastIndexOf	(I)I
    //   306: iconst_1
    //   307: iadd
    //   308: invokevirtual 310	java/lang/String:substring	(I)Ljava/lang/String;
    //   311: astore 9
    //   313: aload 10
    //   315: aload 9
    //   317: putfield 312	cmn/e:v	Ljava/lang/String;
    //   320: aload 10
    //   322: aload 13
    //   324: invokevirtual 296	android/content/Context:getPackageName	()Ljava/lang/String;
    //   327: putfield 314	cmn/e:w	Ljava/lang/String;
    //   330: aload 10
    //   332: aload 12
    //   334: invokestatic 319	cmn/j:a	(Landroid/content/SharedPreferences;)Ljava/lang/String;
    //   337: putfield 50	cmn/e:g	Ljava/lang/String;
    //   340: aload 13
    //   342: ldc_w 321
    //   345: invokevirtual 325	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   348: checkcast 327	android/telephony/TelephonyManager
    //   351: astore 9
    //   353: aload 10
    //   355: aload 9
    //   357: invokevirtual 330	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   360: putfield 54	cmn/e:h	Ljava/lang/String;
    //   363: aload 10
    //   365: getfield 54	cmn/e:h	Ljava/lang/String;
    //   368: ifnonnull +10 -> 378
    //   371: aload 10
    //   373: ldc 52
    //   375: putfield 54	cmn/e:h	Ljava/lang/String;
    //   378: aload 10
    //   380: aload 9
    //   382: invokevirtual 333	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
    //   385: putfield 56	cmn/e:i	Ljava/lang/String;
    //   388: aload 10
    //   390: getfield 56	cmn/e:i	Ljava/lang/String;
    //   393: ifnonnull +10 -> 403
    //   396: aload 10
    //   398: ldc 52
    //   400: putfield 56	cmn/e:i	Ljava/lang/String;
    //   403: aload 10
    //   405: aload 12
    //   407: ldc_w 335
    //   410: iconst_0
    //   411: invokeinterface 214 3 0
    //   416: putfield 337	cmn/e:x	I
    //   419: aload 10
    //   421: putstatic 39	cmn/e:e	Lcmn/e;
    //   424: new 339	android/util/DisplayMetrics
    //   427: dup
    //   428: invokespecial 340	android/util/DisplayMetrics:<init>	()V
    //   431: astore 9
    //   433: aload_0
    //   434: ldc_w 342
    //   437: invokevirtual 325	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   440: checkcast 344	android/view/WindowManager
    //   443: invokeinterface 348 1 0
    //   448: aload 9
    //   450: invokevirtual 354	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   453: aload 10
    //   455: aload 9
    //   457: getfield 357	android/util/DisplayMetrics:densityDpi	I
    //   460: putfield 62	cmn/e:c	I
    //   463: aload 10
    //   465: astore_0
    //   466: goto -449 -> 17
    //   469: astore_0
    //   470: aload 10
    //   472: astore_0
    //   473: goto -456 -> 17
    //   476: astore 10
    //   478: iload 4
    //   480: istore_1
    //   481: lconst_0
    //   482: lstore 5
    //   484: iload_1
    //   485: istore_3
    //   486: iload_2
    //   487: istore_1
    //   488: iload_3
    //   489: istore_2
    //   490: goto -384 -> 106
    //   493: astore_0
    //   494: ldc 2
    //   496: monitorexit
    //   497: aload_0
    //   498: athrow
    //   499: astore 9
    //   501: goto -98 -> 403
    //   504: astore 10
    //   506: goto -25 -> 481
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	509	0	paramContext	Context
    //   74	414	1	i1	int
    //   3	487	2	i2	int
    //   1	488	3	i3	int
    //   61	418	4	i4	int
    //   56	427	5	l1	long
    //   116	125	7	l2	long
    //   33	423	9	localObject1	Object
    //   499	1	9	localThrowable	Throwable
    //   142	329	10	localObject2	Object
    //   476	1	10	localException1	Exception
    //   504	1	10	localException2	Exception
    //   280	20	11	str	String
    //   40	366	12	localSharedPreferences	android.content.SharedPreferences
    //   26	315	13	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   424	463	469	java/lang/Throwable
    //   63	75	476	java/lang/Exception
    //   7	17	493	finally
    //   22	58	493	finally
    //   63	75	493	finally
    //   77	88	493	finally
    //   90	102	493	finally
    //   135	206	493	finally
    //   206	218	493	finally
    //   231	282	493	finally
    //   286	313	493	finally
    //   313	340	493	finally
    //   340	378	493	finally
    //   378	403	493	finally
    //   403	424	493	finally
    //   424	463	493	finally
    //   340	378	499	java/lang/Throwable
    //   378	403	499	java/lang/Throwable
    //   77	88	504	java/lang/Exception
    //   90	102	504	java/lang/Exception
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
    if (this.t != null) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    this.v = "notfound";
    this.d.clear();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(paramContext.getPackageName())) {
        this.f = localPackageInfo.versionCode;
      }
      if (localPackageInfo.packageName.equals("com.android.vending")) {
        this.a = localPackageInfo.versionCode;
      }
      if (localPackageInfo.packageName.contains("swisscodemonkeys"))
      {
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(",");
        }
        int i2 = localPackageInfo.packageName.lastIndexOf('.');
        localStringBuilder.append(localPackageInfo.packageName.substring(i2 + 1));
      }
      this.d.add(localPackageInfo.packageName);
      i1 += 1;
    }
    this.t = localStringBuilder.toString();
    this.s = i1;
    try
    {
      paramContext = new StatFs("/data/app");
      this.b = ((int)(paramContext.getAvailableBlocks() * paramContext.getBlockSize() / 1024L));
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public final String a()
  {
    return this.h;
  }
  
  public final String b()
  {
    return this.i;
  }
  
  public final String c()
  {
    return this.o;
  }
  
  public final String d()
  {
    return this.u;
  }
  
  public final int e()
  {
    return this.s;
  }
  
  public final String f()
  {
    if (this.f == null) {
      return "";
    }
    return this.f;
  }
  
  public final String g()
  {
    return this.n;
  }
  
  public final String h()
  {
    return this.l;
  }
  
  public final String i()
  {
    return f.b(new byte[] { (byte)(this.j >> 24 & 0xFF), (byte)(this.j >> 16 & 0xFF), (byte)(this.j >> 8 & 0xFF), (byte)(this.j & 0xFF) });
  }
  
  public final String j()
  {
    byte[] arrayOfByte = new byte[8];
    int i1 = 0;
    while (i1 < 8)
    {
      arrayOfByte[i1] = ((byte)(int)(this.k >> i1 * 8 & 0xFF));
      i1 += 1;
    }
    return f.b(arrayOfByte);
  }
  
  public final int k()
  {
    return this.m;
  }
  
  public final String l()
  {
    return this.w;
  }
  
  public final String m()
  {
    return this.p;
  }
  
  public final String n()
  {
    return this.q;
  }
  
  public final String o()
  {
    return this.r;
  }
}
