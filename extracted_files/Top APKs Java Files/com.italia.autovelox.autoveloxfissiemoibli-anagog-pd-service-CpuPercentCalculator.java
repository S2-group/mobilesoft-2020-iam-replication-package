package anagog.pd.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class CpuPercentCalculator
{
  private static int ʽ = 1;
  private static int ˏॱ;
  private static final byte[] ॱˋ = { 14, -64, -112, -95, 83, -14, -2, 12, -1, -10, 15, -71, -12, 82, 3, -7, 0, -5, 5, -7, -71, 70, 9, 3, -82, 3, 5, -5, 11, -11, -8, 11, -3, 17, -17, 5, -7, -57, 0, 0, 11, 2, -4, 4, 52, 13, 5, 83, -14, -2, 12, -1, -10, 15, -52, 77, -59, 33, -79, 82, -13, 14, 2, -9, 8, -1, -83, 89, -20, 15, -72, -12, 52, 30, 7, -89, 65, 6, -6, 8, 5, -78, 76, -11, 19, -15, 13, -68, 0, 0, -15, -7, -5, 27, -3, 0, 5, -5, 50, 15, 13, -3, -46, 84, -11, 4, -8, -40, -18, 21, -17, -1, 27, -12, 1, -19, 19, 12, -8, 3, -7, -6, 13, -67, -57, -33, 21, 18, 1, -49, 45, 5, -33, 21, 4, -8, 11, 7, 45, 75, 68, -17, 7, 5, 27, 5, -19, 11, -47, -3, 5, -9, -55, 69, -14, 11, -15, 17, -17, 19, -5, 3, 6, -6, 1, -15, 8, 23, -72, 6, -83, 33, -1, -9, 68, 6, -6, 1, -15, 8 };
  private static int ᐝ;
  public Map<String, PkgInfo> mAllPackages = new HashMap();
  public long mCurrentCpuTime = 0L;
  public long mPreviousSelfUsage = 0L;
  public long mRank = 0L;
  public long mSelfUsage = 0L;
  public Map<Long, String> mSortedMap = new TreeMap();
  public String mStatusString = ˎ((byte)73, (byte)ॱˋ[0], (short)(ॱˋ['§'] - 1));
  private String ʻ = ˎ((byte)(ॱˋ[44] - 1), (byte)ॱˋ[22], (short)(ˏॱ & 0x1EC));
  private String ʼ = "";
  private long ˊ = 0L;
  private HashSet<String> ˊॱ;
  private String ˋ = "";
  private String ˎ = ˎ((byte)35, (byte)ॱˋ[41], (short)41);
  Context ˏ;
  ArrayList<PidInfo> ॱ = new ArrayList();
  
  static
  {
    ˏॱ = 122;
    ᐝ = 0;
  }
  
  public CpuPercentCalculator(Context paramContext)
  {
    this.ˏ = paramContext;
    this.ˊॱ = new HashSet();
    this.ˋ = this.ˏ.getApplicationContext().getPackageName();
    try
    {
      this.ʼ = this.ˏ.getPackageManager().getPackageInfo(this.ˋ, 0).applicationInfo.loadLabel(this.ˏ.getPackageManager()).toString();
      updatePackageStatistics();
      ˋ();
      this.ˊ = this.ˏ.getSharedPreferences(this.ʻ, 0).getLong(ˎ((byte)ॱˋ[55], (byte)ॱˋ[28], (short)(ˏॱ - 1)), System.currentTimeMillis());
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        this.ʼ = this.ˋ;
      }
    }
  }
  
  private static String ˎ(short paramShort1, short paramShort2, int paramInt)
  {
    short s1 = paramShort1 + 32;
    int i = paramShort2 + 1;
    byte[] arrayOfByte1 = ॱˋ;
    paramShort1 = paramInt + 4;
    byte[] arrayOfByte2 = new byte[i];
    if (arrayOfByte1 == null)
    {
      paramShort2 = ʽ + 75;
      ᐝ = paramShort2 % 128;
      if (paramShort2 % 2 == 0) {
        break label118;
      }
      paramShort2 = paramShort1;
      short s2 = 0;
      paramInt = paramShort1;
      paramShort1 = s2;
    }
    for (;;)
    {
      paramShort2 += s1;
      paramInt += 1;
      for (;;)
      {
        arrayOfByte2[paramShort1] = ((byte)paramShort2);
        if (paramShort1 != i - 1) {
          break;
        }
        return new String(arrayOfByte2, 0).intern();
        paramShort2 = s1;
        paramInt = paramShort1;
        paramShort1 = 0;
      }
      s1 = arrayOfByte1[paramInt];
      paramShort1 += 1;
      continue;
      label118:
      paramShort2 = paramShort1;
      paramInt = paramShort1;
      paramShort1 = 0;
    }
  }
  
  public void clearHistory()
  {
    SharedPreferences.Editor localEditor = this.ˏ.getSharedPreferences(this.ʻ, 0).edit();
    localEditor.clear();
    localEditor.commit();
    this.mAllPackages.clear();
    this.ॱ.clear();
    this.ˊ = System.currentTimeMillis();
    updatePackageStatistics();
    ˋ();
  }
  
  public HashSet<String> getRunningApps()
  {
    return this.ˊॱ;
  }
  
  public long getmCurrentCpuTime()
  {
    return this.mCurrentCpuTime;
  }
  
  public long getmSelfUsage()
  {
    return this.mSelfUsage;
  }
  
  public void initListOfRunningApps(Set<String> paramSet)
  {
    if (this.ˊॱ == null) {
      this.ˊॱ = new HashSet();
    }
    this.ˊॱ.addAll(paramSet);
  }
  
  public void resetRunningAppsList()
  {
    this.ˊॱ = new HashSet();
  }
  
  public void setmCurrentCpuTime(long paramLong)
  {
    this.mCurrentCpuTime = paramLong;
  }
  
  public void updatePackageStatistics()
  {
    Object localObject = this.ˏ.getPackageManager().getInstalledApplications(128);
    SharedPreferences localSharedPreferences = this.ˏ.getSharedPreferences(this.ʻ, 0);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      PkgInfo localPkgInfo = new PkgInfo();
      if (!this.mAllPackages.containsKey(localApplicationInfo.packageName))
      {
        localPkgInfo.ˎ = localSharedPreferences.getLong(localApplicationInfo.packageName, 0L);
        String str = localApplicationInfo.loadLabel(this.ˏ.getPackageManager()).toString();
        if ((str == null) || (str.length() == 0)) {}
        for (localPkgInfo.ˊ = localApplicationInfo.packageName;; localPkgInfo.ˊ = str)
        {
          this.mAllPackages.put(localApplicationInfo.packageName, localPkgInfo);
          break;
        }
      }
    }
  }
  
  /* Error */
  void ˋ()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: getfield 177	anagog/pd/service/CpuPercentCalculator:ˏ	Landroid/content/Context;
    //   6: aload_0
    //   7: getfield 171	anagog/pd/service/CpuPercentCalculator:ʻ	Ljava/lang/String;
    //   10: iconst_0
    //   11: invokevirtual 228	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   14: astore 16
    //   16: aconst_null
    //   17: astore 12
    //   19: getstatic 128	anagog/pd/service/CpuPercentCalculator:ʽ	I
    //   22: bipush 57
    //   24: iadd
    //   25: istore 4
    //   27: iload 4
    //   29: sipush 128
    //   32: irem
    //   33: putstatic 126	anagog/pd/service/CpuPercentCalculator:ᐝ	I
    //   36: iload 4
    //   38: iconst_2
    //   39: irem
    //   40: ifeq +3 -> 43
    //   43: iload_3
    //   44: iconst_1
    //   45: iadd
    //   46: istore 5
    //   48: new 328	java/lang/ProcessBuilder
    //   51: dup
    //   52: iconst_0
    //   53: anewarray 242	java/lang/String
    //   56: invokespecial 331	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   59: astore 13
    //   61: getstatic 124	anagog/pd/service/CpuPercentCalculator:ˏॱ	I
    //   64: sipush 468
    //   67: iand
    //   68: i2b
    //   69: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   72: bipush 114
    //   74: baload
    //   75: i2b
    //   76: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   79: bipush 109
    //   81: baload
    //   82: i2s
    //   83: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   86: astore 14
    //   88: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   91: bipush 45
    //   93: baload
    //   94: i2b
    //   95: istore_1
    //   96: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   99: bipush 114
    //   101: baload
    //   102: i2b
    //   103: istore_2
    //   104: aload 13
    //   106: iconst_2
    //   107: anewarray 242	java/lang/String
    //   110: dup
    //   111: iconst_0
    //   112: aload 14
    //   114: aastore
    //   115: dup
    //   116: iconst_1
    //   117: iload_1
    //   118: iload_2
    //   119: iload_2
    //   120: sipush 134
    //   123: ior
    //   124: i2s
    //   125: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   128: aastore
    //   129: invokevirtual 335	java/lang/ProcessBuilder:command	([Ljava/lang/String;)Ljava/lang/ProcessBuilder;
    //   132: iconst_1
    //   133: invokevirtual 339	java/lang/ProcessBuilder:redirectErrorStream	(Z)Ljava/lang/ProcessBuilder;
    //   136: invokevirtual 343	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   139: astore 13
    //   141: aload 13
    //   143: astore 12
    //   145: aload 12
    //   147: ifnonnull +1427 -> 1574
    //   150: bipush 31
    //   152: istore_3
    //   153: aload 12
    //   155: astore 14
    //   157: iload_3
    //   158: lookupswitch	default:+26->184, 31:+29->187, 53:+161->319
    //   184: goto -34 -> 150
    //   187: getstatic 126	anagog/pd/service/CpuPercentCalculator:ᐝ	I
    //   190: bipush 123
    //   192: iadd
    //   193: istore_3
    //   194: iload_3
    //   195: sipush 128
    //   198: irem
    //   199: putstatic 128	anagog/pd/service/CpuPercentCalculator:ʽ	I
    //   202: aload 12
    //   204: astore 13
    //   206: iload_3
    //   207: iconst_2
    //   208: irem
    //   209: ifne +31 -> 240
    //   212: iconst_0
    //   213: istore_3
    //   214: iload_3
    //   215: tableswitch	default:+21->236, 0:+1278->1493, 1:+34->249
    //   236: aload 12
    //   238: astore 13
    //   240: iconst_1
    //   241: istore_3
    //   242: aload 13
    //   244: astore 12
    //   246: goto -32 -> 214
    //   249: ldc2_w 344
    //   252: invokestatic 350	android/os/SystemClock:sleep	(J)V
    //   255: aload 12
    //   257: astore 13
    //   259: iload 5
    //   261: iconst_3
    //   262: if_icmple +50 -> 312
    //   265: bipush 31
    //   267: istore 4
    //   269: aload 12
    //   271: astore 13
    //   273: aload 13
    //   275: astore 12
    //   277: iload 5
    //   279: istore_3
    //   280: aload 13
    //   282: astore 14
    //   284: iload 4
    //   286: lookupswitch	default:+26->312, 23:+-243->43, 31:+33->319
    //   312: bipush 23
    //   314: istore 4
    //   316: goto -43 -> 273
    //   319: aload 14
    //   321: ifnonnull +4 -> 325
    //   324: return
    //   325: aload 14
    //   327: invokevirtual 356	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   330: astore 13
    //   332: sipush 4096
    //   335: newarray byte
    //   337: astore 14
    //   339: ldc -93
    //   341: astore 12
    //   343: aload 13
    //   345: aload 14
    //   347: invokevirtual 362	java/io/InputStream:read	([B)I
    //   350: istore_3
    //   351: iload_3
    //   352: iconst_m1
    //   353: if_icmpeq +37 -> 390
    //   356: new 364	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 365	java/lang/StringBuilder:<init>	()V
    //   363: aload 12
    //   365: invokevirtual 369	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: new 242	java/lang/String
    //   371: dup
    //   372: aload 14
    //   374: iconst_0
    //   375: iload_3
    //   376: invokespecial 372	java/lang/String:<init>	([BII)V
    //   379: invokevirtual 369	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   385: astore 12
    //   387: goto -44 -> 343
    //   390: aload 12
    //   392: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   395: bipush 82
    //   397: baload
    //   398: i2b
    //   399: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   402: bipush 45
    //   404: baload
    //   405: i2b
    //   406: sipush 145
    //   409: i2s
    //   410: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   413: invokestatic 377	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   416: invokevirtual 381	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   419: astore 17
    //   421: iconst_1
    //   422: istore_3
    //   423: iload_3
    //   424: aload 17
    //   426: arraylength
    //   427: if_icmpge +1153 -> 1580
    //   430: goto +1213 -> 1643
    //   433: aload 17
    //   435: iload_3
    //   436: aaload
    //   437: astore 12
    //   439: bipush 60
    //   441: i2b
    //   442: istore_1
    //   443: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   446: bipush 41
    //   448: baload
    //   449: i2b
    //   450: istore_2
    //   451: aload 12
    //   453: iload_1
    //   454: iload_2
    //   455: iload_2
    //   456: sipush 161
    //   459: ior
    //   460: i2s
    //   461: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   464: invokevirtual 381	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   467: astore 13
    //   469: iload_3
    //   470: istore 5
    //   472: aload 13
    //   474: arraylength
    //   475: bipush 9
    //   477: if_icmplt +1234 -> 1711
    //   480: iconst_0
    //   481: istore 4
    //   483: goto +1196 -> 1679
    //   486: aload 13
    //   488: iconst_0
    //   489: aaload
    //   490: astore 14
    //   492: aload 13
    //   494: iconst_1
    //   495: aaload
    //   496: astore 18
    //   498: aload 13
    //   500: iconst_2
    //   501: aaload
    //   502: astore 19
    //   504: aload 13
    //   506: iconst_3
    //   507: aaload
    //   508: astore 20
    //   510: aload 13
    //   512: iconst_4
    //   513: aaload
    //   514: astore 21
    //   516: aload 13
    //   518: iconst_5
    //   519: aaload
    //   520: astore 22
    //   522: aload 13
    //   524: bipush 6
    //   526: aaload
    //   527: astore 23
    //   529: aload 13
    //   531: bipush 7
    //   533: aaload
    //   534: astore 24
    //   536: aload 13
    //   538: bipush 8
    //   540: aaload
    //   541: astore 15
    //   543: aload_0
    //   544: getfield 182	anagog/pd/service/CpuPercentCalculator:ˊॱ	Ljava/util/HashSet;
    //   547: aload 15
    //   549: invokevirtual 384	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   552: pop
    //   553: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   556: bipush 64
    //   558: baload
    //   559: i2b
    //   560: istore_1
    //   561: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   564: bipush 41
    //   566: baload
    //   567: i2b
    //   568: istore_2
    //   569: aload 12
    //   571: iload_1
    //   572: iload_2
    //   573: iload_2
    //   574: bipush 49
    //   576: ior
    //   577: i2s
    //   578: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   581: invokevirtual 388	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   584: istore 4
    //   586: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   589: bipush 7
    //   591: baload
    //   592: i2b
    //   593: istore_1
    //   594: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   597: bipush 16
    //   599: baload
    //   600: i2b
    //   601: istore_2
    //   602: aload 12
    //   604: iload 4
    //   606: iconst_3
    //   607: iadd
    //   608: aload 12
    //   610: iload_1
    //   611: iload_2
    //   612: iload_2
    //   613: sipush 136
    //   616: ior
    //   617: i2s
    //   618: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   621: iload 4
    //   623: invokevirtual 391	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   626: invokevirtual 395	java/lang/String:substring	(II)Ljava/lang/String;
    //   629: astore 13
    //   631: aload 12
    //   633: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   636: iconst_4
    //   637: baload
    //   638: i2b
    //   639: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   642: bipush 114
    //   644: baload
    //   645: i2b
    //   646: getstatic 124	anagog/pd/service/CpuPercentCalculator:ˏॱ	I
    //   649: iconst_2
    //   650: isub
    //   651: i2s
    //   652: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   655: iload 4
    //   657: invokevirtual 391	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   660: istore 4
    //   662: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   665: bipush 22
    //   667: baload
    //   668: i2b
    //   669: istore_1
    //   670: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   673: bipush 16
    //   675: baload
    //   676: i2b
    //   677: istore_2
    //   678: aload 12
    //   680: iload 4
    //   682: iconst_2
    //   683: iadd
    //   684: aload 12
    //   686: iload_1
    //   687: iload_2
    //   688: iload_2
    //   689: sipush 135
    //   692: ior
    //   693: i2s
    //   694: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   697: iload 4
    //   699: invokevirtual 391	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   702: invokevirtual 395	java/lang/String:substring	(II)Ljava/lang/String;
    //   705: astore 12
    //   707: aload 13
    //   709: invokestatic 401	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   712: invokevirtual 404	java/lang/Long:longValue	()J
    //   715: aload 12
    //   717: invokestatic 401	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   720: invokevirtual 404	java/lang/Long:longValue	()J
    //   723: ladd
    //   724: lstore 9
    //   726: iload_3
    //   727: istore 5
    //   729: aload 15
    //   731: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   734: bipush 10
    //   736: baload
    //   737: i2b
    //   738: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   741: bipush 77
    //   743: baload
    //   744: i2b
    //   745: sipush 170
    //   748: i2s
    //   749: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   752: invokevirtual 408	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   755: ifeq +1000 -> 1755
    //   758: iconst_1
    //   759: istore 4
    //   761: goto +959 -> 1720
    //   764: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   767: iconst_4
    //   768: baload
    //   769: i2b
    //   770: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   773: bipush 18
    //   775: baload
    //   776: i2b
    //   777: sipush 158
    //   780: i2s
    //   781: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   784: astore 13
    //   786: aload 13
    //   788: astore 12
    //   790: aload 13
    //   792: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   795: bipush 10
    //   797: baload
    //   798: i2b
    //   799: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   802: bipush 43
    //   804: baload
    //   805: i2b
    //   806: sipush 136
    //   809: i2s
    //   810: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   813: invokevirtual 408	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   816: ifeq +771 -> 1587
    //   819: goto +945 -> 1764
    //   822: aload 13
    //   824: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   827: sipush 139
    //   830: baload
    //   831: i2b
    //   832: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   835: bipush 74
    //   837: baload
    //   838: i2b
    //   839: bipush 113
    //   841: i2s
    //   842: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   845: invokevirtual 408	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   848: ifeq +753 -> 1601
    //   851: goto +968 -> 1819
    //   854: aload 12
    //   856: astore 15
    //   858: aload 13
    //   860: astore 14
    //   862: iload_3
    //   863: istore 5
    //   865: aload 13
    //   867: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   870: bipush 13
    //   872: baload
    //   873: i2b
    //   874: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   877: bipush 14
    //   879: baload
    //   880: i2b
    //   881: getstatic 124	anagog/pd/service/CpuPercentCalculator:ˏॱ	I
    //   884: sipush 479
    //   887: iand
    //   888: i2s
    //   889: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   892: invokevirtual 408	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   895: ifeq +1020 -> 1915
    //   898: iconst_1
    //   899: istore 4
    //   901: goto +978 -> 1879
    //   904: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   907: iconst_4
    //   908: baload
    //   909: i2b
    //   910: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   913: bipush 18
    //   915: baload
    //   916: i2b
    //   917: sipush 158
    //   920: i2s
    //   921: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   924: astore 13
    //   926: aload 13
    //   928: astore 12
    //   930: goto -108 -> 822
    //   933: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   936: iconst_4
    //   937: baload
    //   938: i2b
    //   939: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   942: bipush 18
    //   944: baload
    //   945: i2b
    //   946: sipush 158
    //   949: i2s
    //   950: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   953: astore 12
    //   955: aload 12
    //   957: astore 13
    //   959: goto -105 -> 854
    //   962: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   965: iconst_4
    //   966: baload
    //   967: i2b
    //   968: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   971: bipush 18
    //   973: baload
    //   974: i2b
    //   975: sipush 158
    //   978: i2s
    //   979: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   982: astore 12
    //   984: getstatic 128	anagog/pd/service/CpuPercentCalculator:ʽ	I
    //   987: bipush 47
    //   989: iadd
    //   990: istore 4
    //   992: iload 4
    //   994: sipush 128
    //   997: irem
    //   998: putstatic 126	anagog/pd/service/CpuPercentCalculator:ᐝ	I
    //   1001: iload 4
    //   1003: iconst_2
    //   1004: irem
    //   1005: ifeq +550 -> 1555
    //   1008: aload 12
    //   1010: astore 13
    //   1012: getstatic 124	anagog/pd/service/CpuPercentCalculator:ˏॱ	I
    //   1015: bipush 31
    //   1017: iand
    //   1018: i2b
    //   1019: istore_1
    //   1020: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   1023: bipush 16
    //   1025: baload
    //   1026: i2b
    //   1027: istore_2
    //   1028: aload 12
    //   1030: iload_1
    //   1031: iload_2
    //   1032: iload_2
    //   1033: sipush 165
    //   1036: ior
    //   1037: i2s
    //   1038: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   1041: invokevirtual 381	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1044: iconst_0
    //   1045: aaload
    //   1046: astore 12
    //   1048: new 6	anagog/pd/service/CpuPercentCalculator$PidInfo
    //   1051: dup
    //   1052: aload_0
    //   1053: aload 13
    //   1055: aload 18
    //   1057: aload 19
    //   1059: aload 20
    //   1061: aload 21
    //   1063: aload 22
    //   1065: aload 23
    //   1067: aload 24
    //   1069: aload 12
    //   1071: invokespecial 411	anagog/pd/service/CpuPercentCalculator$PidInfo:<init>	(Lanagog/pd/service/CpuPercentCalculator;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1074: astore 13
    //   1076: aload 13
    //   1078: lload 9
    //   1080: putfield 413	anagog/pd/service/CpuPercentCalculator$PidInfo:ˋ	J
    //   1083: iconst_0
    //   1084: istore 5
    //   1086: iload_3
    //   1087: istore 4
    //   1089: iload 5
    //   1091: istore_3
    //   1092: iload_3
    //   1093: istore 5
    //   1095: iload 4
    //   1097: istore 7
    //   1099: iload_3
    //   1100: aload_0
    //   1101: getfield 145	anagog/pd/service/CpuPercentCalculator:ॱ	Ljava/util/ArrayList;
    //   1104: invokevirtual 416	java/util/ArrayList:size	()I
    //   1107: if_icmpge +852 -> 1959
    //   1110: iconst_1
    //   1111: istore 6
    //   1113: iload_3
    //   1114: istore 5
    //   1116: iload 4
    //   1118: istore_3
    //   1119: goto +813 -> 1932
    //   1122: iload 5
    //   1124: istore 4
    //   1126: iload_3
    //   1127: istore 6
    //   1129: aload_0
    //   1130: getfield 145	anagog/pd/service/CpuPercentCalculator:ॱ	Ljava/util/ArrayList;
    //   1133: iload 5
    //   1135: invokevirtual 420	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1138: checkcast 6	anagog/pd/service/CpuPercentCalculator$PidInfo
    //   1141: getfield 423	anagog/pd/service/CpuPercentCalculator$PidInfo:Pid	Ljava/lang/String;
    //   1144: aload 18
    //   1146: invokevirtual 426	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1149: ifeq +858 -> 2007
    //   1152: bipush 66
    //   1154: istore 7
    //   1156: iload 5
    //   1158: istore 4
    //   1160: goto +808 -> 1968
    //   1163: aload_0
    //   1164: getfield 145	anagog/pd/service/CpuPercentCalculator:ॱ	Ljava/util/ArrayList;
    //   1167: iload 4
    //   1169: invokevirtual 420	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1172: checkcast 6	anagog/pd/service/CpuPercentCalculator$PidInfo
    //   1175: getfield 429	anagog/pd/service/CpuPercentCalculator$PidInfo:LastName	Ljava/lang/String;
    //   1178: aload 12
    //   1180: invokevirtual 426	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1183: istore 11
    //   1185: iload 4
    //   1187: istore 6
    //   1189: iload_3
    //   1190: istore 5
    //   1192: iload 11
    //   1194: ifeq +37 -> 1231
    //   1197: iconst_0
    //   1198: istore 7
    //   1200: iload 7
    //   1202: tableswitch	default:+22->1224, 0:+42->1244, 1:+186->1388
    //   1224: iload_3
    //   1225: istore 5
    //   1227: iload 4
    //   1229: istore 6
    //   1231: iconst_1
    //   1232: istore 7
    //   1234: iload 6
    //   1236: istore 4
    //   1238: iload 5
    //   1240: istore_3
    //   1241: goto -41 -> 1200
    //   1244: getstatic 126	anagog/pd/service/CpuPercentCalculator:ᐝ	I
    //   1247: bipush 49
    //   1249: iadd
    //   1250: istore 5
    //   1252: iload 5
    //   1254: sipush 128
    //   1257: irem
    //   1258: putstatic 128	anagog/pd/service/CpuPercentCalculator:ʽ	I
    //   1261: iload 5
    //   1263: iconst_2
    //   1264: irem
    //   1265: ifne +3 -> 1268
    //   1268: aload_0
    //   1269: getfield 145	anagog/pd/service/CpuPercentCalculator:ॱ	Ljava/util/ArrayList;
    //   1272: iload 4
    //   1274: invokevirtual 420	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1277: checkcast 6	anagog/pd/service/CpuPercentCalculator$PidInfo
    //   1280: lload 9
    //   1282: putfield 413	anagog/pd/service/CpuPercentCalculator$PidInfo:ˋ	J
    //   1285: iconst_0
    //   1286: istore 4
    //   1288: iload_3
    //   1289: istore 5
    //   1291: iload 4
    //   1293: ifeq +724 -> 2017
    //   1296: aload 13
    //   1298: aload 12
    //   1300: putfield 429	anagog/pd/service/CpuPercentCalculator$PidInfo:LastName	Ljava/lang/String;
    //   1303: new 364	java/lang/StringBuilder
    //   1306: dup
    //   1307: invokespecial 365	java/lang/StringBuilder:<init>	()V
    //   1310: aload 13
    //   1312: getfield 432	anagog/pd/service/CpuPercentCalculator$PidInfo:Name	Ljava/lang/String;
    //   1315: invokevirtual 369	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1318: astore 12
    //   1320: bipush 63
    //   1322: i2b
    //   1323: istore_1
    //   1324: getstatic 122	anagog/pd/service/CpuPercentCalculator:ॱˋ	[B
    //   1327: bipush 43
    //   1329: baload
    //   1330: i2b
    //   1331: istore_2
    //   1332: aload 13
    //   1334: aload 16
    //   1336: aload 12
    //   1338: iload_1
    //   1339: iload_2
    //   1340: iload_2
    //   1341: bipush 82
    //   1343: ior
    //   1344: i2s
    //   1345: invokestatic 138	anagog/pd/service/CpuPercentCalculator:ˎ	(SSI)Ljava/lang/String;
    //   1348: invokevirtual 369	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1351: aload 13
    //   1353: getfield 423	anagog/pd/service/CpuPercentCalculator$PidInfo:Pid	Ljava/lang/String;
    //   1356: invokevirtual 369	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1359: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1362: lload 9
    //   1364: invokeinterface 240 4 0
    //   1369: putfield 433	anagog/pd/service/CpuPercentCalculator$PidInfo:ˎ	J
    //   1372: aload_0
    //   1373: getfield 145	anagog/pd/service/CpuPercentCalculator:ॱ	Ljava/util/ArrayList;
    //   1376: aload 13
    //   1378: invokevirtual 434	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1381: pop
    //   1382: iload_3
    //   1383: istore 5
    //   1385: goto +632 -> 2017
    //   1388: aload_0
    //   1389: getfield 145	anagog/pd/service/CpuPercentCalculator:ॱ	Ljava/util/ArrayList;
    //   1392: iload 4
    //   1394: invokevirtual 437	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   1397: pop
    //   1398: getstatic 128	anagog/pd/service/CpuPercentCalculator:ʽ	I
    //   1401: bipush 97
    //   1403: iadd
    //   1404: istore 7
    //   1406: iload 7
    //   1408: sipush 128
    //   1411: irem
    //   1412: putstatic 126	anagog/pd/service/CpuPercentCalculator:ᐝ	I
    //   1415: iload 4
    //   1417: istore 6
    //   1419: iload_3
    //   1420: istore 5
    //   1422: iload 7
    //   1424: iconst_2
    //   1425: irem
    //   1426: ifeq +46 -> 1472
    //   1429: iconst_0
    //   1430: istore 6
    //   1432: iload_3
    //   1433: istore 5
    //   1435: iload 6
    //   1437: istore_3
    //   1438: iload 4
    //   1440: istore 8
    //   1442: iload 5
    //   1444: istore 6
    //   1446: iload_3
    //   1447: tableswitch	default:+21->1468, 0:+115->1562, 1:+34->1481
    //   1468: iload 4
    //   1470: istore 6
    //   1472: iconst_1
    //   1473: istore_3
    //   1474: iload 6
    //   1476: istore 4
    //   1478: goto -40 -> 1438
    //   1481: iload 8
    //   1483: iconst_1
    //   1484: iadd
    //   1485: istore_3
    //   1486: iload 6
    //   1488: istore 4
    //   1490: goto -398 -> 1092
    //   1493: ldc2_w 344
    //   1496: invokestatic 350	android/os/SystemClock:sleep	(J)V
    //   1499: iload 5
    //   1501: iconst_3
    //   1502: if_icmple +114 -> 1616
    //   1505: iconst_1
    //   1506: istore 4
    //   1508: aload 12
    //   1510: astore 13
    //   1512: aload 13
    //   1514: astore 12
    //   1516: iload 5
    //   1518: istore_3
    //   1519: aload 13
    //   1521: astore 14
    //   1523: iload 4
    //   1525: tableswitch	default:+23->1548, 0:+-1482->43, 1:+-1206->319
    //   1548: aload 13
    //   1550: astore 12
    //   1552: goto -47 -> 1505
    //   1555: aload 12
    //   1557: astore 13
    //   1559: goto -547 -> 1012
    //   1562: iload 4
    //   1564: iconst_1
    //   1565: iadd
    //   1566: istore_3
    //   1567: iload 5
    //   1569: istore 4
    //   1571: goto -479 -> 1092
    //   1574: bipush 53
    //   1576: istore_3
    //   1577: goto -1424 -> 153
    //   1580: bipush 33
    //   1582: istore 4
    //   1584: goto +63 -> 1647
    //   1587: iconst_0
    //   1588: istore 4
    //   1590: aload 13
    //   1592: astore 14
    //   1594: aload 12
    //   1596: astore 15
    //   1598: goto +177 -> 1775
    //   1601: bipush 22
    //   1603: istore 4
    //   1605: aload 13
    //   1607: astore 14
    //   1609: aload 12
    //   1611: astore 15
    //   1613: goto +218 -> 1831
    //   1616: iconst_0
    //   1617: istore 4
    //   1619: aload 12
    //   1621: astore 13
    //   1623: goto -111 -> 1512
    //   1626: astore 12
    //   1628: return
    //   1629: astore 13
    //   1631: goto -1486 -> 145
    //   1634: iconst_1
    //   1635: istore 4
    //   1637: goto -349 -> 1288
    //   1640: goto -628 -> 1012
    //   1643: bipush 31
    //   1645: istore 4
    //   1647: iload 4
    //   1649: tableswitch	default:+27->1676, 31:+-1216->433, 32:+27->1676, 33:+-1325->324
    //   1676: goto -33 -> 1643
    //   1679: iload_3
    //   1680: istore 5
    //   1682: iload 4
    //   1684: tableswitch	default:+24->1708, 0:+-1198->486, 1:+333->2017
    //   1708: iload_3
    //   1709: istore 5
    //   1711: iconst_1
    //   1712: istore 4
    //   1714: iload 5
    //   1716: istore_3
    //   1717: goto -38 -> 1679
    //   1720: aload 15
    //   1722: astore 13
    //   1724: aload 14
    //   1726: astore 12
    //   1728: iload 4
    //   1730: tableswitch	default:+22->1752, 0:+-940->790, 1:+-966->764
    //   1752: iload_3
    //   1753: istore 5
    //   1755: iconst_0
    //   1756: istore 4
    //   1758: iload 5
    //   1760: istore_3
    //   1761: goto -41 -> 1720
    //   1764: iconst_1
    //   1765: istore 4
    //   1767: aload 12
    //   1769: astore 15
    //   1771: aload 13
    //   1773: astore 14
    //   1775: aload 14
    //   1777: astore 13
    //   1779: aload 15
    //   1781: astore 12
    //   1783: iload 4
    //   1785: tableswitch	default:+23->1808, 0:+-963->822, 1:+-881->904
    //   1808: aload 14
    //   1810: astore 13
    //   1812: aload 15
    //   1814: astore 12
    //   1816: goto -52 -> 1764
    //   1819: bipush 44
    //   1821: istore 4
    //   1823: aload 12
    //   1825: astore 15
    //   1827: aload 13
    //   1829: astore 14
    //   1831: aload 14
    //   1833: astore 12
    //   1835: aload 15
    //   1837: astore 13
    //   1839: iload 4
    //   1841: lookupswitch	default:+27->1868, 22:+-987->854, 44:+-908->933
    //   1868: aload 14
    //   1870: astore 13
    //   1872: aload 15
    //   1874: astore 12
    //   1876: goto -57 -> 1819
    //   1879: iload 4
    //   1881: tableswitch	default:+23->1904, 0:+-241->1640, 1:+-919->962
    //   1904: iload_3
    //   1905: istore 5
    //   1907: aload 13
    //   1909: astore 14
    //   1911: aload 12
    //   1913: astore 15
    //   1915: iconst_0
    //   1916: istore 4
    //   1918: aload 15
    //   1920: astore 12
    //   1922: aload 14
    //   1924: astore 13
    //   1926: iload 5
    //   1928: istore_3
    //   1929: goto -50 -> 1879
    //   1932: iload 6
    //   1934: tableswitch	default:+22->1956, 0:+-300->1634, 1:+-812->1122
    //   1956: iload_3
    //   1957: istore 7
    //   1959: iconst_0
    //   1960: istore 6
    //   1962: iload 7
    //   1964: istore_3
    //   1965: goto -33 -> 1932
    //   1968: iload 4
    //   1970: istore 8
    //   1972: iload_3
    //   1973: istore 6
    //   1975: iload 7
    //   1977: lookupswitch	default:+27->2004, 36:+-496->1481, 66:+-814->1163
    //   2004: iload_3
    //   2005: istore 6
    //   2007: bipush 36
    //   2009: istore 7
    //   2011: iload 6
    //   2013: istore_3
    //   2014: goto -46 -> 1968
    //   2017: iload 5
    //   2019: iconst_1
    //   2020: iadd
    //   2021: istore_3
    //   2022: goto -1599 -> 423
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2025	0	this	CpuPercentCalculator
    //   95	1244	1	s1	short
    //   103	1241	2	s2	short
    //   1	2021	3	i	int
    //   25	1944	4	j	int
    //   46	1975	5	k	int
    //   1111	901	6	m	int
    //   1097	913	7	n	int
    //   1440	531	8	i1	int
    //   724	639	9	l	long
    //   1183	10	11	bool	boolean
    //   17	1603	12	localObject1	Object
    //   1626	1	12	localIOException1	java.io.IOException
    //   1726	195	12	localObject2	Object
    //   59	1563	13	localObject3	Object
    //   1629	1	13	localIOException2	java.io.IOException
    //   1722	203	13	localObject4	Object
    //   86	1837	14	localObject5	Object
    //   541	1378	15	localObject6	Object
    //   14	1321	16	localSharedPreferences	SharedPreferences
    //   419	15	17	arrayOfString	String[]
    //   496	649	18	str1	String
    //   502	556	19	str2	String
    //   508	552	20	str3	String
    //   514	548	21	str4	String
    //   520	544	22	str5	String
    //   527	539	23	str6	String
    //   534	534	24	str7	String
    // Exception table:
    //   from	to	target	type
    //   325	339	1626	java/io/IOException
    //   343	351	1626	java/io/IOException
    //   356	387	1626	java/io/IOException
    //   390	421	1626	java/io/IOException
    //   423	430	1626	java/io/IOException
    //   443	469	1626	java/io/IOException
    //   472	480	1626	java/io/IOException
    //   543	726	1626	java/io/IOException
    //   729	758	1626	java/io/IOException
    //   764	786	1626	java/io/IOException
    //   790	819	1626	java/io/IOException
    //   822	851	1626	java/io/IOException
    //   865	898	1626	java/io/IOException
    //   904	926	1626	java/io/IOException
    //   933	955	1626	java/io/IOException
    //   962	984	1626	java/io/IOException
    //   1012	1083	1626	java/io/IOException
    //   1099	1110	1626	java/io/IOException
    //   1129	1152	1626	java/io/IOException
    //   1163	1185	1626	java/io/IOException
    //   1268	1285	1626	java/io/IOException
    //   1296	1320	1626	java/io/IOException
    //   1324	1382	1626	java/io/IOException
    //   1388	1398	1626	java/io/IOException
    //   48	141	1629	java/io/IOException
  }
  
  void ˏ()
  {
    Object localObject1 = this.ˏ.getSharedPreferences(this.ʻ, 0);
    SharedPreferences.Editor localEditor = ((SharedPreferences)localObject1).edit();
    int i = 0;
    int j;
    label118:
    Object localObject2;
    label269:
    short s1;
    short s2;
    Map.Entry localEntry;
    if (i < this.ॱ.size())
    {
      for (;;)
      {
        j = 65;
        switch (j)
        {
        }
      }
      this.mCurrentCpuTime = 0L;
      localObject1 = this.mAllPackages.entrySet().iterator();
      this.mSortedMap.clear();
      for (;;)
      {
        if (((Iterator)localObject1).hasNext())
        {
          for (;;)
          {
            i = 0;
            switch (i)
            {
            }
          }
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          if (((PkgInfo)((Map.Entry)localObject2).getValue()).ˎ < 0L)
          {
            i = ᐝ + 77;
            ʽ = i % 128;
            if (i % 2 == 0) {}
            for (i = 34;; i = 50) {
              switch (i)
              {
              }
            }
            if (this.mAllPackages.containsKey(((PidInfo)this.ॱ.get(i)).Name))
            {
              for (;;)
              {
                k = 0;
                j = i;
                switch (k)
                {
                }
              }
              j = ᐝ + 29;
              ʽ = j % 128;
              k = i;
              if (j % 2 == 0) {}
              localObject2 = (PkgInfo)this.mAllPackages.get(((PidInfo)this.ॱ.get(k)).Name);
              ((PkgInfo)localObject2).ˎ = (((PkgInfo)localObject2).ˎ + ((PidInfo)this.ॱ.get(k)).ˋ - ((PidInfo)this.ॱ.get(k)).ˎ);
              this.mAllPackages.put(((PidInfo)this.ॱ.get(k)).Name, localObject2);
              localEditor.putLong(((PidInfo)this.ॱ.get(k)).Name, ((PkgInfo)localObject2).ˎ);
              j = k;
              for (;;)
              {
                ((PidInfo)this.ॱ.get(j)).ˎ = ((PidInfo)this.ॱ.get(j)).ˋ;
                localObject2 = new StringBuilder().append(((PidInfo)this.ॱ.get(j)).Name);
                s1 = (byte)63;
                s2 = (byte)ॱˋ[43];
                localEditor.putLong(ˎ(s1, s2, (short)(s2 | 0x52)) + ((PidInfo)this.ॱ.get(j)).Pid, ((PidInfo)this.ॱ.get(j)).ˎ);
                i = j + 1;
                break;
                localObject2 = new PkgInfo();
                ((PkgInfo)localObject2).ˎ = ((SharedPreferences)localObject1).getLong(((PidInfo)this.ॱ.get(j)).Name, 0L);
                ((PkgInfo)localObject2).ˎ = (((PkgInfo)localObject2).ˎ + ((PidInfo)this.ॱ.get(j)).ˋ - ((PidInfo)this.ॱ.get(j)).ˎ);
                s1 = (byte)-ॱˋ[102];
                s2 = (byte)ॱˋ[14];
                ˎ(s1, s2, (short)(s2 | 0xA4));
                ((PkgInfo)localObject2).ˊ = ((PidInfo)this.ॱ.get(j)).Name;
                this.mAllPackages.put(((PidInfo)this.ॱ.get(j)).Name, localObject2);
                localEditor.putLong(((PidInfo)this.ॱ.get(j)).Name, ((PkgInfo)localObject2).ˎ);
              }
            }
          }
          else
          {
            this.mSortedMap.put(Long.valueOf(((PkgInfo)((Map.Entry)localObject2).getValue()).ˎ), ((PkgInfo)((Map.Entry)localObject2).getValue()).ˊ);
            long l = this.mCurrentCpuTime;
            this.mCurrentCpuTime = (((PkgInfo)((Map.Entry)localObject2).getValue()).ˎ + l);
            continue;
            Iterator localIterator = this.mSortedMap.entrySet().iterator();
            localObject1 = "";
            i = this.mSortedMap.size();
            localObject2 = localObject1;
            j = i;
            if (localIterator.hasNext()) {
              k = 32;
            }
            for (;;)
            {
              switch (k)
              {
              default: 
                j = i;
                localObject2 = localObject1;
                k = 66;
                localObject1 = localObject2;
                i = j;
              }
            }
            localEntry = (Map.Entry)localIterator.next();
            localObject2 = localObject1;
            j = i;
            if (this.mCurrentCpuTime > 0L)
            {
              k = 78;
              localObject2 = localObject1;
            }
            for (;;)
            {
              localObject1 = localObject2;
              switch (k)
              {
              default: 
                j = i;
                k = 85;
                i = j;
              }
            }
            j = ᐝ + 73;
            ʽ = j % 128;
            if (j % 2 == 0) {}
            l = ((Long)localEntry.getKey()).longValue();
            float f = 100.0F * (float)l / (float)this.mCurrentCpuTime;
            localObject1 = new StringBuilder().append(String.format(ˎ((byte)ॱˋ[18], (byte)ॱˋ[14], (short)''), new Object[] { Long.valueOf(l) })).append(ˎ((byte)ॱˋ[55], (byte)ॱˋ[41], (short)'¥'));
            s1 = (byte)ॱˋ[18];
            localObject1 = ((StringBuilder)localObject1).append(String.format(ˎ(s1, (byte)s1, (short)36), new Object[] { Float.valueOf(f) }));
            s1 = (byte)ॱˋ[18];
            s2 = (byte)ॱˋ[114];
            localObject1 = ((StringBuilder)localObject1).append(ˎ(s1, s2, (short)(s2 | 0x5C))).append((String)localEntry.getValue());
            s1 = (byte)(ॱˋ[73] + 1);
            s2 = (byte)ॱˋ[16];
            localObject1 = ˎ(s1, s2, (short)(s2 | 0xAA)) + (String)localObject2;
            j = ᐝ + 117;
            ʽ = j % 128;
            if (j % 2 == 0)
            {
              label1316:
              if (!this.ʼ.equals((String)localEntry.getValue())) {
                break label2169;
              }
              for (;;)
              {
                k = 0;
                label1339:
                localObject2 = localObject1;
                j = i;
                switch (k)
                {
                }
              }
              k = ʽ + 109;
              ᐝ = k % 128;
              localObject2 = localObject1;
              j = i;
              if (k % 2 != 0) {
                k = 69;
              }
              for (;;)
              {
                switch (k)
                {
                default: 
                  j = i;
                  localObject2 = localObject1;
                  k = 28;
                  localObject1 = localObject2;
                  i = j;
                }
              }
              this.mRank = i;
              this.mSelfUsage = ((Long)localEntry.getKey()).longValue();
              j = ʽ + 85;
              ᐝ = j % 128;
              if (j % 2 == 0) {
                break label2189;
              }
            }
          }
        }
      }
    }
    label2069:
    label2117:
    label2169:
    label2183:
    label2189:
    for (int k = 24;; k = 70)
    {
      localObject2 = localObject1;
      j = i;
      switch (k)
      {
      default: 
        break;
      case 24: 
        i -= 1;
        break;
        this.mRank = i;
        this.mSelfUsage = ((Long)localEntry.getKey()).longValue();
        j = i;
        localObject2 = localObject1;
      case 70: 
        i = j - 1;
        localObject1 = localObject2;
        break;
        s1 = (byte)-ॱˋ[102];
        s2 = (byte)ॱˋ[57];
        this.mStatusString = ˎ(s1, s2, (short)(s2 | 0x14));
        if (((String)localObject1).length() > 0)
        {
          localObject2 = new StringBuilder().append(ˎ((byte)ॱˋ[44], (byte)ॱˋ[18], (short)'')).append(this.mCurrentCpuTime / 1000L).append(ˎ((byte)ॱˋ[16], (byte)ॱˋ[109], (short)ॱˋ[16])).append((System.currentTimeMillis() - this.ˊ) / 1000L);
          s1 = (byte)ॱˋ[16];
          s2 = (byte)ॱˋ[64];
          this.mStatusString = ˎ(s1, s2, (short)(s2 | 0x23));
          localObject2 = new StringBuilder().append(this.mStatusString).append(this.ʼ).append(ˎ((byte)ॱˋ[16], (byte)ॱˋ[18], (short)94)).append(this.mRank).append(ˎ((byte)ॱˋ[16], (byte)ॱˋ[18], (short)99)).append(this.mSelfUsage);
          s1 = (byte)(ॱˋ[73] + 1);
          s2 = (byte)ॱˋ[16];
          this.mStatusString = ˎ(s1, s2, (short)(s2 | 0xAA));
          this.mStatusString += (String)localObject1;
        }
        localEditor.putLong(ˎ((byte)ॱˋ[55], (byte)ॱˋ[28], (short)(ˏॱ - 1)), this.ˊ);
        localEditor.commit();
        return;
        k = ʽ + 125;
        ᐝ = k % 128;
        j = i;
        if (k % 2 != 0) {
          k = 1;
        }
        for (;;)
        {
          switch (k)
          {
          case 0: 
          default: 
            j = i;
            k = 0;
            i = j;
          }
        }
        int m;
        if (this.mAllPackages.containsKey(((PidInfo)this.ॱ.get(i)).Name))
        {
          for (;;)
          {
            m = 13;
            k = i;
            j = i;
            switch (m)
            {
            }
          }
          if (!((Iterator)localObject1).hasNext()) {
            break label2183;
          }
        }
        for (i = 0;; i = 1)
        {
          switch (i)
          {
          }
          break label2117;
          break label1316;
          j = 12;
          break;
          k = 1;
          break label269;
          i = 1;
          break label118;
          k = 96;
          break label1339;
          m = 94;
          break label2069;
        }
      }
    }
  }
  
  public class PidInfo
  {
    private static int ʻ;
    private static int ˊ = 200;
    private static int ˊॱ = 0;
    private static final byte[] ˏ;
    public String LastName = "";
    public String Name = "";
    public String Pc = "";
    public String Pid = "";
    public String Rss = "";
    public String Status = "";
    public String User = "";
    public String Wchan = "";
    public String pPid = "";
    public String vSize = "";
    long ˋ = 0L;
    long ˎ = 0L;
    
    static
    {
      ʻ = 1;
      ˏ = new byte[] { 1, 101, -107, -87, -15, 47, 30, -3, -57, -15, 77, -35, 22, -8, -42, -15, 45, 22, -8, -42, -15, 43, 16, 9, -11, -43, -15, 83, -38, 19, 14, -24, -43, -15, 45, 16, -41, 22, -8, -30, 34, -11, 6, -82, 56, -9, 27, -17, 10, -56, -15, 48, 30, -22, 16, -2, -5, -57, -15, 52, 9, 2, -10, 10, -52 };
    }
    
    public PidInfo() {}
    
    public PidInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
    {
      this.User = paramString1;
      this.Pid = paramString2;
      this.pPid = paramString3;
      this.vSize = paramString4;
      this.Rss = paramString5;
      this.Wchan = paramString6;
      this.Pc = paramString7;
      this.Status = paramString8;
      this.Name = paramString9;
      this.ˎ = 0L;
    }
    
    private static String ॱ(byte paramByte1, short paramShort, byte paramByte2)
    {
      paramShort = 57 - paramShort;
      paramByte2 = 93 - paramByte2;
      byte[] arrayOfByte1 = ˏ;
      int j = 14 - paramByte1;
      byte[] arrayOfByte2 = new byte[j];
      if (arrayOfByte1 == null)
      {
        paramByte1 = ʻ + 97;
        ˊॱ = paramByte1 % 128;
        if (paramByte1 % 2 == 0) {
          break label182;
        }
      }
      label182:
      for (paramByte1 = 1;; paramByte1 = 0)
      {
        byte b;
        int i;
        switch (paramByte1)
        {
        default: 
          break;
        case 0: 
          paramByte1 = paramShort;
          short s = 0;
          b = paramByte2;
          i = paramShort;
          paramShort = s;
          b = i + b + 3;
          paramByte2 = paramByte1;
          paramByte1 = b;
        case 1: 
          for (;;)
          {
            arrayOfByte2[paramShort] = ((byte)paramByte1);
            paramByte2 += 1;
            if (paramShort == j - 1)
            {
              return new String(arrayOfByte2, 0).intern();
              b = paramShort;
              paramByte1 = paramByte2;
              paramShort = 0;
              paramByte2 = b;
            }
            else
            {
              i = arrayOfByte1[paramByte2];
              paramShort += 1;
              b = paramByte1;
              paramByte1 = paramByte2;
              break;
              b = paramShort;
              paramByte1 = paramByte2 + paramShort + 3;
              paramShort = 0;
              paramByte2 = b;
            }
          }
        }
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      byte b = (byte)(ˏ[0] - 1);
      localStringBuilder = localStringBuilder.append(ॱ(b, (byte)(b | 0x15), (byte)(ˏ[30] - 1))).append(this.User).append(ॱ((byte)-ˏ[13], (byte)ˏ[21], (byte)(ˏ[51] + 1))).append(this.Pid);
      b = (byte)(ˏ[42] + 1);
      short s = (byte)(ˏ[51] + 1);
      localStringBuilder = localStringBuilder.append(ॱ(b, s, (byte)s)).append(this.pPid);
      b = (byte)ˏ[42];
      s = (byte)32;
      localStringBuilder = localStringBuilder.append(ॱ(b, s, (byte)(s | 0x11))).append(this.vSize);
      b = (byte)-ˏ[13];
      s = (byte)54;
      localStringBuilder = localStringBuilder.append(ॱ(b, s, (byte)(s - 5))).append(this.Rss);
      b = (byte)ˏ[42];
      s = (byte)(ˏ[0] - 1);
      localStringBuilder = localStringBuilder.append(ॱ(b, s, (byte)(s | 0x31))).append(this.Wchan);
      b = (byte)ˏ[23];
      return ॱ(b, (byte)(b | 0x10), (byte)(ˏ[51] + 1)) + this.Pc + ॱ((byte)-ˏ[56], (byte)-ˏ[13], (byte)(ˏ[51] + 1)) + this.Status + ॱ((byte)(ˏ[42] + 1), (byte)-ˏ[28], (byte)(ˏ[51] + 1)) + this.Name + ॱ((byte)(ˏ[30] - 1), (byte)32, (byte)(ˏ[0] - 1));
    }
  }
  
  public class PkgInfo
  {
    String ˊ = "";
    long ˎ = 0L;
    
    public PkgInfo() {}
  }
}
