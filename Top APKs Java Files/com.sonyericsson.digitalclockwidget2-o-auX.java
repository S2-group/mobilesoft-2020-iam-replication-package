package o;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StatFs;
import android.provider.Settings.Secure;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

@TargetApi(4)
public final class auX
  implements Serializable
{
  private static auX ᐨ = null;
  private String ʹ;
  public final int ʻ;
  public final String ʼ;
  public final String ʽ;
  public final String ʾ;
  public int ʿ;
  public String ˈ;
  public int ˉ = -1;
  public int ˊ = -1;
  public String ˋ = "";
  public int ˌ = -1;
  public int ˍ = -1;
  public String ˎ = "";
  public final int ˏ;
  public int ˑ = -1;
  public final String ͺ;
  private String ՙ;
  private HashSet י = new HashSet();
  public String ـ = "";
  public final String ᐝ;
  public Context ᐧ;
  public final String ι;
  private String ﹳ = null;
  private final long ﾞ;
  
  static
  {
    if (!"google_sdk".equals(Build.PRODUCT)) {
      "sdk".equals(Build.PRODUCT);
    }
  }
  
  private auX(int paramInt1, int paramInt2, String paramString1, long paramLong, String paramString2)
  {
    this.ˏ = paramInt1;
    this.ʻ = paramInt2;
    this.ʼ = paramString1;
    this.ﾞ = paramLong;
    this.ᐝ = paramString2;
    this.ʽ = (Build.BRAND + " " + Build.DEVICE);
    this.ͺ = Build.MODEL;
    paramString1 = "";
    Field[] arrayOfField = Build.class.getFields();
    paramInt2 = arrayOfField.length;
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      Field localField = arrayOfField[paramInt1];
      paramString2 = paramString1;
      if (localField.getName().equals("MANUFACTURER")) {
        try
        {
          paramString2 = (String)localField.get(null);
        }
        catch (Exception paramString2)
        {
          paramString2.printStackTrace();
          paramString2 = paramString1;
        }
      }
      paramInt1 += 1;
      paramString1 = paramString2;
    }
    this.ι = paramString1;
    this.ʾ = Build.PRODUCT;
  }
  
  public static long ˊ(String paramString)
  {
    long l = 0L;
    for (;;)
    {
      try
      {
        byte[] arrayOfByte = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
        i = 0;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        continue;
      }
      return l;
      int i = 0;
      if (i < paramString.length())
      {
        l = 7265812761L * l + (paramString.charAt(i) + '{') * 41;
        i += 1;
      }
      else
      {
        return l;
        while (i < 8)
        {
          l = (localNoSuchAlgorithmException[i] & 0xFF) << (i << 3) | l;
          i += 1;
        }
      }
    }
  }
  
  private static String ˊ(ContentResolver paramContentResolver)
  {
    Object localObject = Settings.Secure.getString(paramContentResolver, "android_id");
    paramContentResolver = (ContentResolver)localObject;
    if ((localObject != null) && (!paramContentResolver.equals("9774d56d682e549c")))
    {
      localObject = paramContentResolver;
      if (!paramContentResolver.equals("67ef2b122f51423f")) {}
    }
    else
    {
      localObject = "";
    }
    if (((String)localObject).length() == 0)
    {
      paramContentResolver = new Random();
      localObject = new StringBuffer();
      int i = 0;
      while (i < 16)
      {
        ((StringBuffer)localObject).append(paramContentResolver.nextInt(16) + 97);
        i += 1;
      }
      return ((StringBuffer)localObject).toString();
    }
    return localObject;
  }
  
  /* Error */
  public static auX ˊ(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 44	o/auX:ᐨ	Lo/auX;
    //   6: ifnull +12 -> 18
    //   9: getstatic 44	o/auX:ᐨ	Lo/auX;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: aload_0
    //   19: invokevirtual 218	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   22: astore 12
    //   24: aload 12
    //   26: invokevirtual 222	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: astore 10
    //   31: aload 12
    //   33: invokestatic 228	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   36: astore 13
    //   38: new 230	java/util/Date
    //   41: dup
    //   42: invokespecial 231	java/util/Date:<init>	()V
    //   45: invokevirtual 235	java/util/Date:getTime	()J
    //   48: ldc2_w 236
    //   51: ldiv
    //   52: l2i
    //   53: istore 5
    //   55: iload 5
    //   57: istore_3
    //   58: iconst_0
    //   59: istore 4
    //   61: lconst_0
    //   62: lstore 6
    //   64: iload_3
    //   65: istore_2
    //   66: iload 4
    //   68: istore_1
    //   69: aload 13
    //   71: ldc -17
    //   73: iload_3
    //   74: invokeinterface 245 3 0
    //   79: istore_3
    //   80: iload_3
    //   81: istore_2
    //   82: iload 4
    //   84: istore_1
    //   85: aload 13
    //   87: ldc -9
    //   89: iconst_0
    //   90: invokeinterface 245 3 0
    //   95: istore 4
    //   97: iload_3
    //   98: istore_2
    //   99: iload 4
    //   101: istore_1
    //   102: aload 13
    //   104: ldc -7
    //   106: lconst_0
    //   107: invokeinterface 253 4 0
    //   112: lstore 8
    //   114: iload_3
    //   115: istore_2
    //   116: iload 4
    //   118: istore_1
    //   119: lload 8
    //   121: lstore 6
    //   123: goto +408 -> 531
    //   126: aload 13
    //   128: invokeinterface 257 1 0
    //   133: astore 11
    //   135: aload 10
    //   137: invokestatic 259	o/auX:ˊ	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   140: astore 10
    //   142: aload 10
    //   144: invokevirtual 262	java/lang/String:hashCode	()I
    //   147: istore_3
    //   148: aload 10
    //   150: invokestatic 264	o/auX:ˊ	(Ljava/lang/String;)J
    //   153: lstore 8
    //   155: aload 11
    //   157: ldc -17
    //   159: iload_2
    //   160: invokeinterface 270 3 0
    //   165: pop
    //   166: aload 11
    //   168: ldc -9
    //   170: iload_3
    //   171: invokeinterface 270 3 0
    //   176: pop
    //   177: aload 11
    //   179: ldc -7
    //   181: lload 8
    //   183: invokeinterface 274 4 0
    //   188: pop
    //   189: invokestatic 279	o/if:ˊ	()Lo/if;
    //   192: aload 11
    //   194: invokevirtual 282	o/if:ˊ	(Landroid/content/SharedPreferences$Editor;)V
    //   197: aload 12
    //   199: invokevirtual 222	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   202: ldc -71
    //   204: invokestatic 191	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   207: astore 11
    //   209: aload 11
    //   211: astore 10
    //   213: aload 11
    //   215: ifnonnull +7 -> 222
    //   218: ldc 72
    //   220: astore 10
    //   222: new 2	o/auX
    //   225: dup
    //   226: iload_3
    //   227: iload_2
    //   228: getstatic 287	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   231: lload 8
    //   233: aload 10
    //   235: invokespecial 289	o/auX:<init>	(IILjava/lang/String;JLjava/lang/String;)V
    //   238: astore 11
    //   240: aload 11
    //   242: aload 12
    //   244: putfield 291	o/auX:ᐧ	Landroid/content/Context;
    //   247: aload 11
    //   249: aload 12
    //   251: invokespecial 294	o/auX:ˋ	(Landroid/content/Context;)V
    //   254: aload 12
    //   256: invokevirtual 297	android/content/Context:getPackageName	()Ljava/lang/String;
    //   259: astore 10
    //   261: aload 10
    //   263: ldc_w 299
    //   266: invokevirtual 303	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   269: ifne +294 -> 563
    //   272: aload 10
    //   274: ldc_w 305
    //   277: invokevirtual 303	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   280: ifne +283 -> 563
    //   283: aload 10
    //   285: ldc_w 307
    //   288: invokevirtual 303	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   291: ifeq +277 -> 568
    //   294: goto +269 -> 563
    //   297: iload_1
    //   298: ifeq +275 -> 573
    //   301: aload 10
    //   303: aload 10
    //   305: bipush 46
    //   307: invokevirtual 310	java/lang/String:lastIndexOf	(I)I
    //   310: iconst_1
    //   311: iadd
    //   312: invokevirtual 314	java/lang/String:substring	(I)Ljava/lang/String;
    //   315: astore 10
    //   317: goto +3 -> 320
    //   320: aload 11
    //   322: aload 10
    //   324: putfield 316	o/auX:ՙ	Ljava/lang/String;
    //   327: aload 11
    //   329: aload 12
    //   331: invokevirtual 297	android/content/Context:getPackageName	()Ljava/lang/String;
    //   334: putfield 318	o/auX:ˈ	Ljava/lang/String;
    //   337: aload 11
    //   339: aload 13
    //   341: invokestatic 323	o/ˋ:ˊ	(Landroid/content/SharedPreferences;)Ljava/lang/String;
    //   344: putfield 70	o/auX:ﹳ	Ljava/lang/String;
    //   347: aload 12
    //   349: ldc_w 325
    //   352: invokevirtual 329	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   355: checkcast 331	android/telephony/TelephonyManager
    //   358: astore 10
    //   360: aload 11
    //   362: aload 10
    //   364: invokevirtual 334	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   367: putfield 74	o/auX:ˋ	Ljava/lang/String;
    //   370: aload 11
    //   372: getfield 74	o/auX:ˋ	Ljava/lang/String;
    //   375: ifnonnull +10 -> 385
    //   378: aload 11
    //   380: ldc 72
    //   382: putfield 74	o/auX:ˋ	Ljava/lang/String;
    //   385: aload 11
    //   387: aload 10
    //   389: invokevirtual 337	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
    //   392: putfield 76	o/auX:ˎ	Ljava/lang/String;
    //   395: aload 11
    //   397: getfield 76	o/auX:ˎ	Ljava/lang/String;
    //   400: ifnonnull +10 -> 410
    //   403: aload 11
    //   405: ldc 72
    //   407: putfield 76	o/auX:ˎ	Ljava/lang/String;
    //   410: aload 11
    //   412: aload 10
    //   414: invokevirtual 340	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   417: putfield 86	o/auX:ـ	Ljava/lang/String;
    //   420: aload 11
    //   422: getfield 86	o/auX:ـ	Ljava/lang/String;
    //   425: ifnonnull +10 -> 435
    //   428: aload 11
    //   430: ldc 72
    //   432: putfield 86	o/auX:ـ	Ljava/lang/String;
    //   435: aload 11
    //   437: putstatic 44	o/auX:ᐨ	Lo/auX;
    //   440: new 342	android/util/DisplayMetrics
    //   443: dup
    //   444: invokespecial 343	android/util/DisplayMetrics:<init>	()V
    //   447: astore 10
    //   449: aload_0
    //   450: ldc_w 345
    //   453: invokevirtual 329	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   456: checkcast 347	android/view/WindowManager
    //   459: invokeinterface 351 1 0
    //   464: aload 10
    //   466: invokevirtual 357	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   469: aload 11
    //   471: aload 10
    //   473: getfield 360	android/util/DisplayMetrics:densityDpi	I
    //   476: putfield 84	o/auX:ˑ	I
    //   479: aload 11
    //   481: aload 10
    //   483: getfield 363	android/util/DisplayMetrics:heightPixels	I
    //   486: aload 10
    //   488: getfield 366	android/util/DisplayMetrics:widthPixels	I
    //   491: invokestatic 372	java/lang/Math:min	(II)I
    //   494: i2f
    //   495: aload 10
    //   497: getfield 376	android/util/DisplayMetrics:density	F
    //   500: fdiv
    //   501: f2i
    //   502: putfield 78	o/auX:ˉ	I
    //   505: ldc 2
    //   507: monitorexit
    //   508: aload 11
    //   510: areturn
    //   511: astore_0
    //   512: ldc 2
    //   514: monitorexit
    //   515: aload_0
    //   516: athrow
    //   517: astore 11
    //   519: goto +12 -> 531
    //   522: astore 10
    //   524: goto -89 -> 435
    //   527: astore_0
    //   528: goto -23 -> 505
    //   531: iload 5
    //   533: iload_2
    //   534: if_icmpeq -408 -> 126
    //   537: iload_1
    //   538: istore_3
    //   539: lload 6
    //   541: lstore 8
    //   543: iload_1
    //   544: ifne -347 -> 197
    //   547: iload_1
    //   548: istore_3
    //   549: lload 6
    //   551: lstore 8
    //   553: lload 6
    //   555: lconst_0
    //   556: lcmp
    //   557: ifne -360 -> 197
    //   560: goto -434 -> 126
    //   563: iconst_1
    //   564: istore_1
    //   565: goto -268 -> 297
    //   568: iconst_0
    //   569: istore_1
    //   570: goto -273 -> 297
    //   573: goto -253 -> 320
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	576	0	paramContext	Context
    //   68	502	1	i	int
    //   65	470	2	j	int
    //   57	492	3	k	int
    //   59	58	4	m	int
    //   53	482	5	n	int
    //   62	492	6	l1	long
    //   112	440	8	l2	long
    //   29	467	10	localObject1	Object
    //   522	1	10	localThrowable	Throwable
    //   133	376	11	localObject2	Object
    //   517	1	11	localException	Exception
    //   22	326	12	localContext	Context
    //   36	304	13	localSharedPreferences	android.content.SharedPreferences
    // Exception table:
    //   from	to	target	type
    //   3	16	511	finally
    //   18	55	511	finally
    //   69	80	511	finally
    //   85	97	511	finally
    //   102	114	511	finally
    //   126	197	511	finally
    //   197	209	511	finally
    //   222	294	511	finally
    //   301	317	511	finally
    //   320	347	511	finally
    //   347	385	511	finally
    //   385	410	511	finally
    //   410	435	511	finally
    //   435	440	511	finally
    //   440	505	511	finally
    //   505	508	511	finally
    //   69	80	517	java/lang/Exception
    //   85	97	517	java/lang/Exception
    //   102	114	517	java/lang/Exception
    //   347	385	522	java/lang/Throwable
    //   385	410	522	java/lang/Throwable
    //   410	435	522	java/lang/Throwable
    //   440	505	527	java/lang/Throwable
  }
  
  private void ˋ(Context paramContext)
  {
    if (this.ʹ != null) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    this.י.clear();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(paramContext.getPackageName())) {
        this.ˊ = localPackageInfo.versionCode;
      }
      if (localPackageInfo.packageName.equals("com.android.vending")) {
        this.ˌ = localPackageInfo.versionCode;
      }
      String str = localPackageInfo.packageName;
      int j;
      if ((str.startsWith("com.appspot.swisscodemonkeys.")) || (str.startsWith("com.apptornado.")) || (str.startsWith("com.appbrain."))) {
        j = 1;
      } else {
        j = 0;
      }
      if (j != 0)
      {
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(",");
        }
        j = localPackageInfo.packageName.lastIndexOf('.');
        localStringBuilder.append(localPackageInfo.packageName.substring(j + 1));
      }
      this.י.add(localPackageInfo.packageName);
      i += 1;
    }
    this.ʹ = localStringBuilder.toString();
    this.ʿ = i;
    try
    {
      paramContext = new StatFs("/data/app");
      this.ˍ = ((int)(paramContext.getAvailableBlocks() * paramContext.getBlockSize() / 1024L));
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public final String ˊ()
  {
    byte[] arrayOfByte = new byte[8];
    int i = 0;
    while (i < 8)
    {
      arrayOfByte[i] = ((byte)(int)(this.ﾞ >> (i << 3) & 0xFF));
      i += 1;
    }
    return ͺ.ˋ(arrayOfByte);
  }
}
