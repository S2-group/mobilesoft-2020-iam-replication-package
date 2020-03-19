package anagog.pd.a;

import anagog.pd.service.MobilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class bc
{
  private static char[] p = { 67, -17056, 31285, 105, -17058, 31241, 14308, -2903, -20079, 28492, 11321, -5638, -22823, 25486, 8567, -8594, -25762, 22670, 4367, -21459, 27498, 9865, -6696, -24333, 32303, 15704, -1891, -18504, 109, -17028, 31233, 14307, -2892, -20045, 28496, 11301, -5676, -22823, 25485, 8565, 19900, -3923, 14332, 31239, 95, -17056, 31273, 14292, -2913, -10873, 26786, -20491, -7594, 109, -17085, 31296, -31799, 16108, -1602, -19374, 30494, 12922, 37, -17136, -6530, 1626, -17589, 31828, 12790, -3407, -18537, 26945, 10792, -4128, -24361, 26068, 10109, -10191, -25328, 24216, 5092, -10944, -27050, 19213, 3236, -15947, -32125, 17493, -1747, -17670, 32644, 12440, -3483, -18656, 26625, 11718, -4374, -24518, 25866, -8904, 24627, -22664, -5475, 10688, 27809, 32, -17085, 31237, 14323, -2897, -20066, 28484, 11299, -5716, -22896, 25490, 8549, -8658, -25826, 22729, 5566, -11417, -28656, 19718, 2815, -14414, -31536, 32, -17085, 31237, 14323, -2897, -20066, 28484, 11299, -5697, -11898, 27844, -21593, -6568, 9485, 24683, 32, -17084, 31241, 14333, -2907, -20019, 112, -17085, 11606, -28621, -13806, 30477, -20475, 40, -17083, 31322, 44, -2, 17031, -6245, 23582, -7822, 9768, 27602, -22395, -4700, 13180, 5895, -21955, 28007, 8336, -7215, -22807, 47, -17085, 31234, 14329, -2898, 107, -17081, 31247, 14306, -2901, -20075, 28498, 11391, 19588, -3671, 14073, 31506, 58, 67, -17088, 31253, 14256, -2941, -20079, 28492, 11315, -5643, -22820, 25473, 8548, -8657, -25854, 22656, 5525, -11400, -28589, 19717, 2784, -14412, -31591, 16975, -194 };
  private static long q = 4673844060256124208L;
  private static int r = 0;
  private static int s = 1;
  public long a = 0L;
  public long b = 0L;
  public String c = a('\000', 15, 3).intern();
  public long d = 0L;
  private MobilityService e;
  private m f;
  private String g = a('\000', 3, 0).intern();
  private ArrayList<a> h = new ArrayList();
  private Map<String, b> i = new HashMap();
  private Map<Long, String> j = new TreeMap();
  private long k = 0L;
  private String l = "";
  private String m = "";
  private String n = a('ᅜ', 10, 18).intern();
  private long o = 0L;
  
  public bc(MobilityService paramMobilityService, m paramM)
  {
    this.e = paramMobilityService;
    this.f = paramM;
    this.l = this.e.getApplicationContext().getPackageName();
    try
    {
      this.m = this.e.getPackageManager().getPackageInfo(this.l, 0).applicationInfo.loadLabel(this.e.getPackageManager()).toString();
      f();
      b();
      this.k = this.e.getSharedPreferences(this.n, 0).getLong(a('\000', 12, 28).intern(), System.currentTimeMillis());
      return;
    }
    catch (Exception paramMobilityService)
    {
      for (;;)
      {
        this.m = this.l;
      }
    }
  }
  
  private static String a(char paramChar, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt1];
    int i1 = 0;
    label7:
    if (i1 < paramInt1) {}
    for (int i2 = 1;; i2 = 0) {
      switch (i2)
      {
      default: 
        break;
      case 0: 
        return new String(arrayOfChar);
      case 1: 
        arrayOfChar[i1] = ((char)(int)(p[(paramInt2 + i1)] ^ i1 * q ^ paramChar));
        i1 += 1;
        i2 = s + 13;
        r = i2 % 128;
        if (i2 % 2 != 0) {
          break label7;
        }
        break label7;
      }
    }
  }
  
  private void f()
  {
    Object localObject = this.e.getPackageManager().getInstalledApplications(128);
    SharedPreferences localSharedPreferences = this.e.getSharedPreferences(this.n, 0);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      b localB = new b();
      if (!this.i.containsKey(localApplicationInfo.packageName))
      {
        localB.a = localSharedPreferences.getLong(localApplicationInfo.packageName, 0L);
        String str = localApplicationInfo.loadLabel(this.e.getPackageManager()).toString();
        if ((str == null) || (str.length() == 0)) {}
        for (localB.b = localApplicationInfo.packageName;; localB.b = str)
        {
          this.i.put(localApplicationInfo.packageName, localB);
          break;
        }
      }
    }
  }
  
  public final void a()
  {
    Object localObject1 = this.e.getSharedPreferences(this.n, 0);
    SharedPreferences.Editor localEditor = ((SharedPreferences)localObject1).edit();
    int i1 = 0;
    int i2;
    label93:
    label106:
    label187:
    Object localObject2;
    int i3;
    label279:
    label300:
    Map.Entry localEntry;
    label894:
    label1116:
    int i4;
    if (i1 < this.h.size())
    {
      for (;;)
      {
        i2 = 1;
        switch (i2)
        {
        }
      }
      this.a = 0L;
      localObject1 = this.i.entrySet().iterator();
      this.j.clear();
      if (!((Iterator)localObject1).hasNext()) {
        break label1809;
      }
      for (;;)
      {
        i1 = 51;
        switch (i1)
        {
        }
      }
      Iterator localIterator = this.j.entrySet().iterator();
      localObject1 = "";
      i1 = this.j.size();
      i2 = s + 41;
      r = i2 % 128;
      if (i2 % 2 != 0)
      {
        for (;;)
        {
          localObject2 = localObject1;
          i2 = i1;
          if (localIterator.hasNext()) {
            i3 = 1;
          }
          for (;;)
          {
            switch (i3)
            {
            default: 
              i2 = i1;
              localObject2 = localObject1;
              i3 = 0;
              localObject1 = localObject2;
              i1 = i2;
            }
          }
          if (!this.i.containsKey(((a)this.h.get(i1)).b)) {
            break label1803;
          }
          i2 = 45;
          switch (i2)
          {
          default: 
            localObject2 = (b)this.i.get(((a)this.h.get(i1)).b);
            ((b)localObject2).a = (((b)localObject2).a + ((a)this.h.get(i1)).e - ((a)this.h.get(i1)).d);
            this.i.put(((a)this.h.get(i1)).b, localObject2);
            localEditor.putLong(((a)this.h.get(i1)).b, ((b)localObject2).a);
          }
          for (;;)
          {
            ((a)this.h.get(i1)).d = ((a)this.h.get(i1)).e;
            localEditor.putLong(((a)this.h.get(i1)).b + a('\000', 5, 44).intern() + ((a)this.h.get(i1)).a, ((a)this.h.get(i1)).d);
            i1 += 1;
            break;
            localObject2 = new b();
            ((b)localObject2).a = ((SharedPreferences)localObject1).getLong(((a)this.h.get(i1)).b, 0L);
            ((b)localObject2).a = (((b)localObject2).a + ((a)this.h.get(i1)).e - ((a)this.h.get(i1)).d);
            a('䷲', 4, 40).intern();
            ((b)localObject2).b = ((a)this.h.get(i1)).b;
            this.i.put(((a)this.h.get(i1)).b, localObject2);
            localEditor.putLong(((a)this.h.get(i1)).b, ((b)localObject2).a);
          }
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          if (((b)((Map.Entry)localObject2).getValue()).a < 0L)
          {
            i1 = r + 1;
            s = i1 % 128;
            if (i1 % 2 != 0) {
              break label93;
            }
            break label93;
          }
          this.j.put(Long.valueOf(((b)((Map.Entry)localObject2).getValue()).a), ((b)((Map.Entry)localObject2).getValue()).b);
          long l1 = this.a;
          this.a = (((b)((Map.Entry)localObject2).getValue()).a + l1);
          i1 = s + 59;
          r = i1 % 128;
          if (i1 % 2 != 0) {}
          for (i1 = 0;; i1 = 1) {
            switch (i1)
            {
            }
          }
          localEntry = (Map.Entry)localIterator.next();
          if (this.a <= 0L) {
            break label1815;
          }
          for (;;)
          {
            i3 = 77;
            localObject2 = localObject1;
            i2 = i1;
            switch (i3)
            {
            }
          }
          l1 = ((Long)localEntry.getKey()).longValue();
          float f1 = 100.0F * (float)l1 / (float)this.a;
          String str = String.format(a(54690, 4, 49).intern(), new Object[] { Long.valueOf(l1) }) + a('\000', 3, 53).intern() + String.format(a(33772, 6, 56).intern(), new Object[] { Float.valueOf(f1) }) + a('\000', 2, 62).intern() + (String)localEntry.getValue() + a(58945, 1, 64).intern() + (String)localObject1;
          i2 = r + 125;
          s = i2 % 128;
          if (i2 % 2 != 0) {
            break label1846;
          }
          for (;;)
          {
            i3 = 44;
            localObject2 = str;
            i2 = i1;
            switch (i3)
            {
            }
          }
          if (!this.m.equals((String)localEntry.getValue())) {
            break label1853;
          }
          for (;;)
          {
            i4 = 0;
            label1178:
            localObject2 = str;
            i3 = i1;
            localObject1 = str;
            i2 = i1;
            switch (i4)
            {
            }
          }
          this.b = i3;
          this.d = ((Long)localEntry.getKey()).longValue();
          i2 = i3;
          localObject1 = localObject2;
          label1250:
          i1 = i2 - 1;
        }
        if (!this.m.equals((String)localEntry.getValue())) {
          break label1822;
        }
        i1 = i2;
        for (;;)
        {
          i3 = 1;
          label1282:
          localObject1 = localObject2;
          i2 = i1;
          switch (i3)
          {
          }
        }
        i2 = s + 99;
        r = i2 % 128;
        if (i2 % 2 == 0) {
          break label1859;
        }
        localObject1 = localObject2;
        i2 = 84;
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      i3 = i1;
      switch (i2)
      {
      case 78: 
      default: 
        break;
      case 84: 
        this.b = i1;
        this.d = ((Long)localEntry.getKey()).longValue();
        i2 = i1;
        break label1250;
        this.c = a('ؔ', 34, 65).intern();
        if (((String)localObject1).length() > 0)
        {
          this.c = (a(56684, 6, 99).intern() + this.a / 1000L + a('\000', 22, 105).intern() + (System.currentTimeMillis() - this.k) / 1000L + a('\000', 9, 127).intern());
          this.c = (this.c + this.m + a(53670, 6, 136).intern() + this.b + a('\000', 6, 142).intern() + this.d + a(58945, 1, 64).intern());
          this.c += (String)localObject1;
        }
        localEditor.putLong(a('\000', 12, 28).intern(), this.k);
        localEditor.commit();
        return;
        i2 = r + 29;
        s = i2 % 128;
        if (i2 % 2 == 0)
        {
          i2 = 1;
          label1686:
          switch (i2)
          {
          }
          if (!this.i.containsKey(((a)this.h.get(i1)).b)) {
            break label1835;
          }
          i2 = 78;
          label1733:
          switch (i2)
          {
          }
          break label300;
          if (!((Iterator)localObject1).hasNext()) {
            break label1841;
          }
        }
        label1765:
        label1803:
        label1809:
        label1815:
        label1822:
        label1835:
        label1841:
        for (i1 = 0;; i1 = 1)
        {
          switch (i1)
          {
          }
          break label1765;
          break label187;
          i2 = 0;
          break;
          i2 = 7;
          break label279;
          i1 = 31;
          break label106;
          i3 = 91;
          break label894;
          i3 = 0;
          i1 = i2;
          break label1282;
          i2 = 0;
          break label1686;
          i2 = 76;
          break label1733;
        }
        label1846:
        i3 = 84;
        break label1116;
        label1853:
        i4 = 1;
        break label1178;
        label1859:
        i2 = 78;
        localObject1 = localObject2;
      }
    }
  }
  
  /* Error */
  public final void b()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: getfield 304	anagog/pd/a/bc:e	Lanagog/pd/service/MobilityService;
    //   6: aload_0
    //   7: getfield 298	anagog/pd/a/bc:n	Ljava/lang/String;
    //   10: iconst_0
    //   11: invokevirtual 350	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   14: astore 13
    //   16: aconst_null
    //   17: astore 9
    //   19: getstatic 249	anagog/pd/a/bc:s	I
    //   22: bipush 35
    //   24: iadd
    //   25: istore_2
    //   26: iload_2
    //   27: sipush 128
    //   30: irem
    //   31: putstatic 247	anagog/pd/a/bc:r	I
    //   34: iload_2
    //   35: iconst_2
    //   36: irem
    //   37: ifeq +3 -> 40
    //   40: iload_1
    //   41: iconst_1
    //   42: iadd
    //   43: istore_3
    //   44: new 502	java/lang/ProcessBuilder
    //   47: dup
    //   48: iconst_0
    //   49: anewarray 261	java/lang/String
    //   52: invokespecial 505	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   55: iconst_2
    //   56: anewarray 261	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: iconst_0
    //   62: iconst_2
    //   63: sipush 148
    //   66: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   69: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   72: aastore
    //   73: dup
    //   74: iconst_1
    //   75: sipush 11643
    //   78: iconst_2
    //   79: sipush 150
    //   82: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   85: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   88: aastore
    //   89: invokevirtual 509	java/lang/ProcessBuilder:command	([Ljava/lang/String;)Ljava/lang/ProcessBuilder;
    //   92: iconst_1
    //   93: invokevirtual 513	java/lang/ProcessBuilder:redirectErrorStream	(Z)Ljava/lang/ProcessBuilder;
    //   96: invokevirtual 517	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   99: astore 8
    //   101: aload 8
    //   103: astore 9
    //   105: aload 8
    //   107: ifnull +37 -> 144
    //   110: iconst_0
    //   111: istore_1
    //   112: aload 8
    //   114: astore 9
    //   116: iload_1
    //   117: tableswitch	default:+23->140, 0:+107->224, 1:+36->153
    //   140: aload 8
    //   142: astore 9
    //   144: iconst_1
    //   145: istore_1
    //   146: aload 9
    //   148: astore 8
    //   150: goto -38 -> 112
    //   153: ldc2_w 518
    //   156: invokestatic 525	android/os/SystemClock:sleep	(J)V
    //   159: iload_3
    //   160: iconst_3
    //   161: if_icmpgt +1694 -> 1855
    //   164: iconst_1
    //   165: istore_2
    //   166: aload 8
    //   168: astore 9
    //   170: iload_3
    //   171: istore_1
    //   172: iload_2
    //   173: tableswitch	default:+23->196, 0:+26->199, 1:+-133->40
    //   196: goto -32 -> 164
    //   199: getstatic 249	anagog/pd/a/bc:s	I
    //   202: bipush 107
    //   204: iadd
    //   205: istore_1
    //   206: iload_1
    //   207: sipush 128
    //   210: irem
    //   211: putstatic 247	anagog/pd/a/bc:r	I
    //   214: aload 8
    //   216: astore 9
    //   218: iload_1
    //   219: iconst_2
    //   220: irem
    //   221: ifeq +3 -> 224
    //   224: aload 9
    //   226: ifnonnull +4 -> 230
    //   229: return
    //   230: aconst_null
    //   231: astore 8
    //   233: new 527	java/io/BufferedReader
    //   236: dup
    //   237: new 529	java/io/InputStreamReader
    //   240: dup
    //   241: aload 9
    //   243: invokevirtual 535	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   246: invokespecial 538	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   249: invokespecial 541	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   252: astore 12
    //   254: new 269	java/util/ArrayList
    //   257: dup
    //   258: invokespecial 270	java/util/ArrayList:<init>	()V
    //   261: astore 14
    //   263: new 269	java/util/ArrayList
    //   266: dup
    //   267: invokespecial 270	java/util/ArrayList:<init>	()V
    //   270: astore 15
    //   272: getstatic 247	anagog/pd/a/bc:r	I
    //   275: iconst_1
    //   276: iadd
    //   277: istore_1
    //   278: iload_1
    //   279: sipush 128
    //   282: irem
    //   283: putstatic 249	anagog/pd/a/bc:s	I
    //   286: iload_1
    //   287: iconst_2
    //   288: irem
    //   289: ifne +3 -> 292
    //   292: aload 12
    //   294: invokevirtual 544	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   297: astore 9
    //   299: aload 9
    //   301: ifnonnull +1559 -> 1860
    //   304: goto +1674 -> 1978
    //   307: aload 14
    //   309: invokevirtual 547	java/util/ArrayList:isEmpty	()Z
    //   312: ifeq +1728 -> 2040
    //   315: iconst_0
    //   316: istore_1
    //   317: goto +1698 -> 2015
    //   320: aload 8
    //   322: ldc_w 548
    //   325: iconst_3
    //   326: sipush 152
    //   329: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   332: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   335: invokevirtual 552	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   338: astore 9
    //   340: aload 9
    //   342: arraylength
    //   343: bipush 9
    //   345: if_icmpge +1521 -> 1866
    //   348: goto +1697 -> 2045
    //   351: aload 9
    //   353: iconst_0
    //   354: aaload
    //   355: astore 10
    //   357: aload 9
    //   359: iconst_1
    //   360: aaload
    //   361: astore 16
    //   363: aload 9
    //   365: iconst_2
    //   366: aaload
    //   367: astore 17
    //   369: aload 9
    //   371: iconst_3
    //   372: aaload
    //   373: astore 18
    //   375: aload 9
    //   377: iconst_4
    //   378: aaload
    //   379: astore 19
    //   381: aload 9
    //   383: iconst_5
    //   384: aaload
    //   385: astore 20
    //   387: aload 9
    //   389: bipush 6
    //   391: aaload
    //   392: astore 21
    //   394: aload 9
    //   396: bipush 7
    //   398: aaload
    //   399: astore 22
    //   401: aload 9
    //   403: bipush 8
    //   405: aaload
    //   406: astore 11
    //   408: aload 8
    //   410: iconst_0
    //   411: iconst_3
    //   412: sipush 155
    //   415: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   418: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   421: invokevirtual 556	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   424: istore_1
    //   425: aload 8
    //   427: iload_1
    //   428: iconst_3
    //   429: iadd
    //   430: aload 8
    //   432: iconst_0
    //   433: iconst_1
    //   434: sipush 158
    //   437: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   440: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   443: iload_1
    //   444: invokevirtual 559	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   447: invokevirtual 563	java/lang/String:substring	(II)Ljava/lang/String;
    //   450: astore 9
    //   452: aload 8
    //   454: ldc_w 564
    //   457: iconst_2
    //   458: sipush 159
    //   461: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   464: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   467: iload_1
    //   468: invokevirtual 559	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   471: istore_1
    //   472: aload 8
    //   474: iload_1
    //   475: iconst_2
    //   476: iadd
    //   477: aload 8
    //   479: ldc_w 565
    //   482: iconst_1
    //   483: sipush 161
    //   486: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   489: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   492: iload_1
    //   493: invokevirtual 559	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   496: invokevirtual 563	java/lang/String:substring	(II)Ljava/lang/String;
    //   499: astore 8
    //   501: aload 9
    //   503: invokestatic 568	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   506: invokevirtual 470	java/lang/Long:longValue	()J
    //   509: aload 8
    //   511: invokestatic 568	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   514: invokevirtual 470	java/lang/Long:longValue	()J
    //   517: ladd
    //   518: lstore 5
    //   520: aload 14
    //   522: aload 11
    //   524: invokevirtual 571	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   527: pop
    //   528: aload 15
    //   530: lload 5
    //   532: invokestatic 464	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   535: invokevirtual 571	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   538: pop
    //   539: aload 11
    //   541: sipush 23601
    //   544: bipush 7
    //   546: sipush 162
    //   549: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   552: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   555: invokevirtual 575	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   558: istore 7
    //   560: iload 7
    //   562: ifne +42 -> 604
    //   565: bipush 51
    //   567: istore_1
    //   568: aload 11
    //   570: astore 8
    //   572: aload 10
    //   574: astore 9
    //   576: iload_1
    //   577: lookupswitch	default:+27->604, 51:+95->672, 84:+33->610
    //   604: bipush 84
    //   606: istore_1
    //   607: goto -39 -> 568
    //   610: getstatic 247	anagog/pd/a/bc:r	I
    //   613: bipush 27
    //   615: iadd
    //   616: istore_1
    //   617: iload_1
    //   618: sipush 128
    //   621: irem
    //   622: putstatic 249	anagog/pd/a/bc:s	I
    //   625: iload_1
    //   626: iconst_2
    //   627: irem
    //   628: ifne +3 -> 631
    //   631: sipush 6004
    //   634: bipush 6
    //   636: sipush 169
    //   639: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   642: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   645: astore 9
    //   647: getstatic 247	anagog/pd/a/bc:r	I
    //   650: bipush 91
    //   652: iadd
    //   653: istore_1
    //   654: iload_1
    //   655: sipush 128
    //   658: irem
    //   659: putstatic 249	anagog/pd/a/bc:s	I
    //   662: iload_1
    //   663: iconst_2
    //   664: irem
    //   665: ifne +1306 -> 1971
    //   668: aload 9
    //   670: astore 8
    //   672: aload 8
    //   674: iconst_0
    //   675: iconst_5
    //   676: sipush 175
    //   679: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   682: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   685: invokevirtual 575	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   688: istore 7
    //   690: aload 8
    //   692: astore 10
    //   694: aload 9
    //   696: astore 11
    //   698: iload 7
    //   700: ifne +48 -> 748
    //   703: bipush 55
    //   705: istore_1
    //   706: aload 9
    //   708: astore 11
    //   710: aload 8
    //   712: astore 10
    //   714: aload 10
    //   716: astore 9
    //   718: aload 11
    //   720: astore 8
    //   722: iload_1
    //   723: lookupswitch	default:+25->748, 41:+31->754, 55:+72->795
    //   748: bipush 41
    //   750: istore_1
    //   751: goto -37 -> 714
    //   754: getstatic 247	anagog/pd/a/bc:r	I
    //   757: bipush 33
    //   759: iadd
    //   760: istore_1
    //   761: iload_1
    //   762: sipush 128
    //   765: irem
    //   766: putstatic 249	anagog/pd/a/bc:s	I
    //   769: iload_1
    //   770: iconst_2
    //   771: irem
    //   772: ifne +3 -> 775
    //   775: sipush 6004
    //   778: bipush 6
    //   780: sipush 169
    //   783: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   786: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   789: astore 8
    //   791: aload 8
    //   793: astore 9
    //   795: aload 9
    //   797: iconst_0
    //   798: bipush 8
    //   800: sipush 180
    //   803: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   806: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   809: invokevirtual 575	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   812: istore 7
    //   814: iload 7
    //   816: ifne +1056 -> 1872
    //   819: bipush 77
    //   821: istore_1
    //   822: aload 8
    //   824: astore 11
    //   826: aload 9
    //   828: astore 10
    //   830: aload 10
    //   832: astore 9
    //   834: aload 11
    //   836: astore 8
    //   838: iload_1
    //   839: lookupswitch	default:+25->864, 29:+36->875, 77:+77->916
    //   864: aload 10
    //   866: astore 9
    //   868: aload 11
    //   870: astore 8
    //   872: goto -53 -> 819
    //   875: getstatic 249	anagog/pd/a/bc:s	I
    //   878: bipush 119
    //   880: iadd
    //   881: istore_1
    //   882: iload_1
    //   883: sipush 128
    //   886: irem
    //   887: putstatic 247	anagog/pd/a/bc:r	I
    //   890: iload_1
    //   891: iconst_2
    //   892: irem
    //   893: ifeq +3 -> 896
    //   896: sipush 6004
    //   899: bipush 6
    //   901: sipush 169
    //   904: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   907: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   910: astore 8
    //   912: aload 8
    //   914: astore 9
    //   916: aload 9
    //   918: astore 11
    //   920: aload 8
    //   922: astore 10
    //   924: aload 8
    //   926: sipush 19702
    //   929: iconst_4
    //   930: sipush 188
    //   933: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   936: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   939: invokevirtual 575	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   942: ifne +1166 -> 2108
    //   945: iconst_0
    //   946: istore_1
    //   947: aload 8
    //   949: astore 10
    //   951: goto +1128 -> 2079
    //   954: sipush 6004
    //   957: bipush 6
    //   959: sipush 169
    //   962: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   965: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   968: astore 9
    //   970: getstatic 247	anagog/pd/a/bc:r	I
    //   973: bipush 55
    //   975: iadd
    //   976: istore_1
    //   977: iload_1
    //   978: sipush 128
    //   981: irem
    //   982: putstatic 249	anagog/pd/a/bc:s	I
    //   985: iload_1
    //   986: iconst_2
    //   987: irem
    //   988: ifne +955 -> 1943
    //   991: aload 9
    //   993: astore 8
    //   995: aload 8
    //   997: iconst_0
    //   998: iconst_1
    //   999: sipush 192
    //   1002: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1005: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1008: invokevirtual 552	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1011: iconst_0
    //   1012: aaload
    //   1013: astore 8
    //   1015: new 6	anagog/pd/a/bc$a
    //   1018: dup
    //   1019: aload_0
    //   1020: aload 9
    //   1022: aload 16
    //   1024: aload 17
    //   1026: aload 18
    //   1028: aload 19
    //   1030: aload 20
    //   1032: aload 21
    //   1034: aload 22
    //   1036: aload 8
    //   1038: invokespecial 578	anagog/pd/a/bc$a:<init>	(Lanagog/pd/a/bc;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1041: astore 9
    //   1043: aload 9
    //   1045: lload 5
    //   1047: putfield 436	anagog/pd/a/bc$a:e	J
    //   1050: getstatic 249	anagog/pd/a/bc:s	I
    //   1053: bipush 65
    //   1055: iadd
    //   1056: istore_1
    //   1057: iload_1
    //   1058: sipush 128
    //   1061: irem
    //   1062: putstatic 247	anagog/pd/a/bc:r	I
    //   1065: iload_1
    //   1066: iconst_2
    //   1067: irem
    //   1068: ifeq +898 -> 1966
    //   1071: iconst_0
    //   1072: istore_1
    //   1073: iload_1
    //   1074: istore_3
    //   1075: iload_1
    //   1076: aload_0
    //   1077: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1080: invokevirtual 415	java/util/ArrayList:size	()I
    //   1083: if_icmplt +1063 -> 2146
    //   1086: bipush 58
    //   1088: istore_2
    //   1089: goto +1028 -> 2117
    //   1092: aload_0
    //   1093: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1096: iload_1
    //   1097: invokevirtual 430	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1100: checkcast 6	anagog/pd/a/bc$a
    //   1103: getfield 452	anagog/pd/a/bc$a:a	Ljava/lang/String;
    //   1106: aload 16
    //   1108: invokevirtual 486	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1111: istore 7
    //   1113: iload 7
    //   1115: ifne +771 -> 1886
    //   1118: bipush 30
    //   1120: istore_2
    //   1121: iload_1
    //   1122: istore_3
    //   1123: iload_2
    //   1124: lookupswitch	default:+28->1152, 17:+31->1155, 30:+132->1256
    //   1152: goto -34 -> 1118
    //   1155: getstatic 249	anagog/pd/a/bc:s	I
    //   1158: bipush 53
    //   1160: iadd
    //   1161: istore_3
    //   1162: iload_3
    //   1163: sipush 128
    //   1166: irem
    //   1167: putstatic 247	anagog/pd/a/bc:r	I
    //   1170: iload_1
    //   1171: istore_2
    //   1172: iload_3
    //   1173: iconst_2
    //   1174: irem
    //   1175: ifne +35 -> 1210
    //   1178: bipush 67
    //   1180: istore_3
    //   1181: iload_3
    //   1182: lookupswitch	default:+26->1208, 17:+594->1776, 67:+36->1218
    //   1208: iload_1
    //   1209: istore_2
    //   1210: bipush 17
    //   1212: istore_3
    //   1213: iload_2
    //   1214: istore_1
    //   1215: goto -34 -> 1181
    //   1218: aload_0
    //   1219: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1222: iload_1
    //   1223: invokevirtual 430	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1226: checkcast 6	anagog/pd/a/bc$a
    //   1229: getfield 579	anagog/pd/a/bc$a:c	Ljava/lang/String;
    //   1232: aload 8
    //   1234: invokevirtual 486	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1237: ifne +655 -> 1892
    //   1240: iconst_0
    //   1241: istore_2
    //   1242: goto +912 -> 2154
    //   1245: aload_0
    //   1246: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1249: iload_2
    //   1250: invokevirtual 582	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   1253: pop
    //   1254: iload_2
    //   1255: istore_3
    //   1256: iload_3
    //   1257: iconst_1
    //   1258: iadd
    //   1259: istore_1
    //   1260: goto -187 -> 1073
    //   1263: getstatic 247	anagog/pd/a/bc:r	I
    //   1266: bipush 41
    //   1268: iadd
    //   1269: istore_2
    //   1270: iload_2
    //   1271: sipush 128
    //   1274: irem
    //   1275: putstatic 249	anagog/pd/a/bc:s	I
    //   1278: iload_1
    //   1279: istore 4
    //   1281: iload_2
    //   1282: iconst_2
    //   1283: irem
    //   1284: ifne +3 -> 1287
    //   1287: aload_0
    //   1288: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1291: iload 4
    //   1293: invokevirtual 430	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1296: checkcast 6	anagog/pd/a/bc$a
    //   1299: lload 5
    //   1301: putfield 436	anagog/pd/a/bc$a:e	J
    //   1304: iconst_0
    //   1305: istore_1
    //   1306: iload_1
    //   1307: ifeq +73 -> 1380
    //   1310: aload 9
    //   1312: aload 8
    //   1314: putfield 579	anagog/pd/a/bc$a:c	Ljava/lang/String;
    //   1317: aload 9
    //   1319: aload 13
    //   1321: new 445	java/lang/StringBuilder
    //   1324: dup
    //   1325: invokespecial 446	java/lang/StringBuilder:<init>	()V
    //   1328: aload 9
    //   1330: getfield 431	anagog/pd/a/bc$a:b	Ljava/lang/String;
    //   1333: invokevirtual 450	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1336: iconst_0
    //   1337: iconst_5
    //   1338: bipush 44
    //   1340: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1343: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1346: invokevirtual 450	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1349: aload 9
    //   1351: getfield 452	anagog/pd/a/bc$a:a	Ljava/lang/String;
    //   1354: invokevirtual 450	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1357: invokevirtual 453	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1360: lload 5
    //   1362: invokeinterface 362 4 0
    //   1367: putfield 437	anagog/pd/a/bc$a:d	J
    //   1370: aload_0
    //   1371: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1374: aload 9
    //   1376: invokevirtual 571	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1379: pop
    //   1380: getstatic 249	anagog/pd/a/bc:s	I
    //   1383: bipush 103
    //   1385: iadd
    //   1386: istore_1
    //   1387: iload_1
    //   1388: sipush 128
    //   1391: irem
    //   1392: putstatic 247	anagog/pd/a/bc:r	I
    //   1395: iload_1
    //   1396: iconst_2
    //   1397: irem
    //   1398: ifne +34 -> 1432
    //   1401: bipush 33
    //   1403: istore_1
    //   1404: iload_1
    //   1405: lookupswitch	default:+27->1432, 11:+400->1805, 33:+-1113->292
    //   1432: bipush 11
    //   1434: istore_1
    //   1435: goto -31 -> 1404
    //   1438: aload_0
    //   1439: getfield 306	anagog/pd/a/bc:f	Lanagog/pd/a/m;
    //   1442: astore 8
    //   1444: invokestatic 587	anagog/pd/a/gr:a	()Lanagog/pd/a/gr;
    //   1447: getstatic 592	anagog/pd/service/f$a:c	Lanagog/pd/service/f$a;
    //   1450: iconst_1
    //   1451: anewarray 4	java/lang/Object
    //   1454: dup
    //   1455: iconst_0
    //   1456: bipush 19
    //   1458: bipush 120
    //   1460: ldc_w 593
    //   1463: invokestatic 598	anagog/pd/a/m:a	(IIC)Ljava/lang/String;
    //   1466: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1469: aastore
    //   1470: invokevirtual 601	anagog/pd/a/gr:a	(Lanagog/pd/service/f$a;[Ljava/lang/Object;)Z
    //   1473: pop
    //   1474: aload 8
    //   1476: getfield 604	anagog/pd/a/m:b	Lanagog/pd/service/e/a;
    //   1479: aload 14
    //   1481: aload 15
    //   1483: invokevirtual 609	anagog/pd/service/e/a:a	(Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    //   1486: aload 8
    //   1488: aload 8
    //   1490: getfield 604	anagog/pd/a/m:b	Lanagog/pd/service/e/a;
    //   1493: invokevirtual 612	anagog/pd/a/m:a	(Lanagog/pd/service/e/a;)V
    //   1496: getstatic 249	anagog/pd/a/bc:s	I
    //   1499: bipush 69
    //   1501: iadd
    //   1502: istore_1
    //   1503: iload_1
    //   1504: sipush 128
    //   1507: irem
    //   1508: putstatic 247	anagog/pd/a/bc:r	I
    //   1511: iload_1
    //   1512: iconst_2
    //   1513: irem
    //   1514: ifeq +3 -> 1517
    //   1517: aload 12
    //   1519: invokevirtual 615	java/io/BufferedReader:close	()V
    //   1522: return
    //   1523: astore 8
    //   1525: invokestatic 587	anagog/pd/a/gr:a	()Lanagog/pd/a/gr;
    //   1528: getstatic 592	anagog/pd/service/f$a:c	Lanagog/pd/service/f$a;
    //   1531: iconst_2
    //   1532: anewarray 4	java/lang/Object
    //   1535: dup
    //   1536: iconst_0
    //   1537: iconst_0
    //   1538: bipush 24
    //   1540: sipush 193
    //   1543: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1546: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1549: aastore
    //   1550: dup
    //   1551: iconst_1
    //   1552: aload 8
    //   1554: aastore
    //   1555: invokevirtual 601	anagog/pd/a/gr:a	(Lanagog/pd/service/f$a;[Ljava/lang/Object;)Z
    //   1558: pop
    //   1559: return
    //   1560: astore 9
    //   1562: invokestatic 587	anagog/pd/a/gr:a	()Lanagog/pd/a/gr;
    //   1565: getstatic 592	anagog/pd/service/f$a:c	Lanagog/pd/service/f$a;
    //   1568: iconst_2
    //   1569: anewarray 4	java/lang/Object
    //   1572: dup
    //   1573: iconst_0
    //   1574: iconst_0
    //   1575: bipush 24
    //   1577: sipush 193
    //   1580: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1583: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1586: aastore
    //   1587: dup
    //   1588: iconst_1
    //   1589: aload 9
    //   1591: aastore
    //   1592: invokevirtual 601	anagog/pd/a/gr:a	(Lanagog/pd/service/f$a;[Ljava/lang/Object;)Z
    //   1595: pop
    //   1596: aload 8
    //   1598: ifnull -1369 -> 229
    //   1601: aload 8
    //   1603: invokevirtual 615	java/io/BufferedReader:close	()V
    //   1606: return
    //   1607: astore 8
    //   1609: invokestatic 587	anagog/pd/a/gr:a	()Lanagog/pd/a/gr;
    //   1612: getstatic 592	anagog/pd/service/f$a:c	Lanagog/pd/service/f$a;
    //   1615: iconst_2
    //   1616: anewarray 4	java/lang/Object
    //   1619: dup
    //   1620: iconst_0
    //   1621: iconst_0
    //   1622: bipush 24
    //   1624: sipush 193
    //   1627: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1630: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1633: aastore
    //   1634: dup
    //   1635: iconst_1
    //   1636: aload 8
    //   1638: aastore
    //   1639: invokevirtual 601	anagog/pd/a/gr:a	(Lanagog/pd/service/f$a;[Ljava/lang/Object;)Z
    //   1642: pop
    //   1643: return
    //   1644: astore 8
    //   1646: aconst_null
    //   1647: astore 9
    //   1649: aload 9
    //   1651: ifnull +8 -> 1659
    //   1654: aload 9
    //   1656: invokevirtual 615	java/io/BufferedReader:close	()V
    //   1659: aload 8
    //   1661: athrow
    //   1662: astore 9
    //   1664: invokestatic 587	anagog/pd/a/gr:a	()Lanagog/pd/a/gr;
    //   1667: getstatic 592	anagog/pd/service/f$a:c	Lanagog/pd/service/f$a;
    //   1670: iconst_2
    //   1671: anewarray 4	java/lang/Object
    //   1674: dup
    //   1675: iconst_0
    //   1676: iconst_0
    //   1677: bipush 24
    //   1679: sipush 193
    //   1682: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1685: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1688: aastore
    //   1689: dup
    //   1690: iconst_1
    //   1691: aload 9
    //   1693: aastore
    //   1694: invokevirtual 601	anagog/pd/a/gr:a	(Lanagog/pd/service/f$a;[Ljava/lang/Object;)Z
    //   1697: pop
    //   1698: goto -39 -> 1659
    //   1701: getstatic 247	anagog/pd/a/bc:r	I
    //   1704: bipush 57
    //   1706: iadd
    //   1707: istore_1
    //   1708: iload_1
    //   1709: sipush 128
    //   1712: irem
    //   1713: putstatic 249	anagog/pd/a/bc:s	I
    //   1716: iload_1
    //   1717: iconst_2
    //   1718: irem
    //   1719: ifeq +29 -> 1748
    //   1722: iconst_0
    //   1723: istore_1
    //   1724: iload_1
    //   1725: tableswitch	default:+23->1748, 0:+-771->954, 1:+28->1753
    //   1748: iconst_1
    //   1749: istore_1
    //   1750: goto -26 -> 1724
    //   1753: sipush 6004
    //   1756: bipush 6
    //   1758: sipush 169
    //   1761: invokestatic 259	anagog/pd/a/bc:a	(CII)Ljava/lang/String;
    //   1764: invokevirtual 265	java/lang/String:intern	()Ljava/lang/String;
    //   1767: astore 9
    //   1769: aload 9
    //   1771: astore 8
    //   1773: goto -778 -> 995
    //   1776: iload_1
    //   1777: istore_2
    //   1778: aload_0
    //   1779: getfield 272	anagog/pd/a/bc:h	Ljava/util/ArrayList;
    //   1782: iload_1
    //   1783: invokevirtual 430	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1786: checkcast 6	anagog/pd/a/bc$a
    //   1789: getfield 579	anagog/pd/a/bc$a:c	Ljava/lang/String;
    //   1792: aload 8
    //   1794: invokevirtual 486	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1797: ifne +409 -> 2206
    //   1800: iconst_1
    //   1801: istore_3
    //   1802: goto +375 -> 2177
    //   1805: aload 12
    //   1807: invokevirtual 544	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1810: astore 9
    //   1812: aload 9
    //   1814: ifnonnull +83 -> 1897
    //   1817: bipush 23
    //   1819: istore_1
    //   1820: aload 9
    //   1822: astore 8
    //   1824: iload_1
    //   1825: lookupswitch	default:+27->1852, 23:+-1518->307, 92:+-1505->320
    //   1852: goto -35 -> 1817
    //   1855: iconst_0
    //   1856: istore_2
    //   1857: goto -1691 -> 166
    //   1860: bipush 64
    //   1862: istore_1
    //   1863: goto +118 -> 1981
    //   1866: bipush 31
    //   1868: istore_1
    //   1869: goto +179 -> 2048
    //   1872: bipush 29
    //   1874: istore_1
    //   1875: aload 9
    //   1877: astore 10
    //   1879: aload 8
    //   1881: astore 11
    //   1883: goto -1053 -> 830
    //   1886: bipush 17
    //   1888: istore_2
    //   1889: goto -768 -> 1121
    //   1892: iconst_1
    //   1893: istore_2
    //   1894: goto +260 -> 2154
    //   1897: bipush 92
    //   1899: istore_1
    //   1900: goto -80 -> 1820
    //   1903: astore 8
    //   1905: aload 12
    //   1907: astore 9
    //   1909: goto -260 -> 1649
    //   1912: astore 10
    //   1914: aload 8
    //   1916: astore 9
    //   1918: aload 10
    //   1920: astore 8
    //   1922: goto -273 -> 1649
    //   1925: astore 9
    //   1927: aload 12
    //   1929: astore 8
    //   1931: goto -369 -> 1562
    //   1934: astore 8
    //   1936: aload 9
    //   1938: astore 8
    //   1940: goto -1839 -> 101
    //   1943: aload 9
    //   1945: astore 8
    //   1947: goto -952 -> 995
    //   1950: iconst_1
    //   1951: istore_1
    //   1952: goto -646 -> 1306
    //   1955: aload 9
    //   1957: astore 8
    //   1959: aload 10
    //   1961: astore 9
    //   1963: goto -968 -> 995
    //   1966: iconst_0
    //   1967: istore_1
    //   1968: goto -895 -> 1073
    //   1971: aload 9
    //   1973: astore 8
    //   1975: goto -1303 -> 672
    //   1978: bipush 32
    //   1980: istore_1
    //   1981: aload 9
    //   1983: astore 8
    //   1985: iload_1
    //   1986: lookupswitch	default:+26->2012, 32:+-1679->307, 64:+-1666->320
    //   2012: goto -34 -> 1978
    //   2015: iload_1
    //   2016: tableswitch	default:+24->2040, 0:+-499->1517, 1:+-578->1438
    //   2040: iconst_1
    //   2041: istore_1
    //   2042: goto -27 -> 2015
    //   2045: bipush 63
    //   2047: istore_1
    //   2048: iload_1
    //   2049: lookupswitch	default:+27->2076, 31:+-1698->351, 63:+-669->1380
    //   2076: goto -31 -> 2045
    //   2079: iload_1
    //   2080: tableswitch	default:+24->2104, 0:+-125->1955, 1:+-379->1701
    //   2104: aload 9
    //   2106: astore 11
    //   2108: iconst_1
    //   2109: istore_1
    //   2110: aload 11
    //   2112: astore 9
    //   2114: goto -35 -> 2079
    //   2117: iload_2
    //   2118: lookupswitch	default:+26->2144, 58:+-168->1950, 75:+-1026->1092
    //   2144: iload_1
    //   2145: istore_3
    //   2146: bipush 75
    //   2148: istore_2
    //   2149: iload_3
    //   2150: istore_1
    //   2151: goto -34 -> 2117
    //   2154: iload_2
    //   2155: tableswitch	default:+17->2172, 1:+-892->1263
    //   2172: iload_1
    //   2173: istore_2
    //   2174: goto -929 -> 1245
    //   2177: iload_1
    //   2178: istore_2
    //   2179: iload_1
    //   2180: istore 4
    //   2182: iload_3
    //   2183: tableswitch	default:+21->2204, 0:+-896->1287, 1:+-938->1245
    //   2204: iload_1
    //   2205: istore_2
    //   2206: iconst_0
    //   2207: istore_3
    //   2208: iload_2
    //   2209: istore_1
    //   2210: goto -33 -> 2177
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2213	0	this	bc
    //   1	2209	1	i1	int
    //   25	2184	2	i2	int
    //   43	2165	3	i3	int
    //   1279	902	4	i4	int
    //   518	843	5	l1	long
    //   558	556	7	bool	boolean
    //   99	1390	8	localObject1	Object
    //   1523	79	8	localIOException1	java.io.IOException
    //   1607	30	8	localIOException2	java.io.IOException
    //   1644	16	8	localObject2	Object
    //   1771	109	8	localObject3	Object
    //   1903	12	8	localObject4	Object
    //   1920	10	8	localObject5	Object
    //   1934	1	8	localIOException3	java.io.IOException
    //   1938	46	8	localObject6	Object
    //   17	1358	9	localObject7	Object
    //   1560	30	9	localThrowable1	Throwable
    //   1647	8	9	localObject8	Object
    //   1662	30	9	localIOException4	java.io.IOException
    //   1767	150	9	localObject9	Object
    //   1925	31	9	localThrowable2	Throwable
    //   1961	152	9	localObject10	Object
    //   355	1523	10	localObject11	Object
    //   1912	48	10	localObject12	Object
    //   406	1705	11	localObject13	Object
    //   252	1676	12	localBufferedReader	java.io.BufferedReader
    //   14	1306	13	localSharedPreferences	SharedPreferences
    //   261	1219	14	localArrayList1	ArrayList
    //   270	1212	15	localArrayList2	ArrayList
    //   361	746	16	str1	String
    //   367	658	17	str2	String
    //   373	654	18	str3	String
    //   379	650	19	str4	String
    //   385	646	20	str5	String
    //   392	641	21	str6	String
    //   399	636	22	str7	String
    // Exception table:
    //   from	to	target	type
    //   1517	1522	1523	java/io/IOException
    //   233	254	1560	java/lang/Throwable
    //   1601	1606	1607	java/io/IOException
    //   233	254	1644	finally
    //   1654	1659	1662	java/io/IOException
    //   254	272	1903	finally
    //   292	299	1903	finally
    //   307	315	1903	finally
    //   320	348	1903	finally
    //   408	560	1903	finally
    //   631	647	1903	finally
    //   672	690	1903	finally
    //   775	791	1903	finally
    //   795	814	1903	finally
    //   896	912	1903	finally
    //   924	945	1903	finally
    //   954	970	1903	finally
    //   995	1050	1903	finally
    //   1075	1086	1903	finally
    //   1092	1113	1903	finally
    //   1218	1240	1903	finally
    //   1245	1254	1903	finally
    //   1287	1304	1903	finally
    //   1310	1380	1903	finally
    //   1438	1496	1903	finally
    //   1753	1769	1903	finally
    //   1778	1800	1903	finally
    //   1805	1812	1903	finally
    //   1562	1596	1912	finally
    //   254	272	1925	java/lang/Throwable
    //   292	299	1925	java/lang/Throwable
    //   307	315	1925	java/lang/Throwable
    //   320	348	1925	java/lang/Throwable
    //   408	560	1925	java/lang/Throwable
    //   631	647	1925	java/lang/Throwable
    //   672	690	1925	java/lang/Throwable
    //   775	791	1925	java/lang/Throwable
    //   795	814	1925	java/lang/Throwable
    //   896	912	1925	java/lang/Throwable
    //   924	945	1925	java/lang/Throwable
    //   954	970	1925	java/lang/Throwable
    //   995	1050	1925	java/lang/Throwable
    //   1075	1086	1925	java/lang/Throwable
    //   1092	1113	1925	java/lang/Throwable
    //   1218	1240	1925	java/lang/Throwable
    //   1245	1254	1925	java/lang/Throwable
    //   1287	1304	1925	java/lang/Throwable
    //   1310	1380	1925	java/lang/Throwable
    //   1438	1496	1925	java/lang/Throwable
    //   1753	1769	1925	java/lang/Throwable
    //   1778	1800	1925	java/lang/Throwable
    //   1805	1812	1925	java/lang/Throwable
    //   44	101	1934	java/io/IOException
  }
  
  public final void c()
  {
    SharedPreferences.Editor localEditor = this.e.getSharedPreferences(this.n, 0).edit();
    localEditor.clear();
    localEditor.commit();
    this.i.clear();
    this.h.clear();
    this.k = System.currentTimeMillis();
    f();
    b();
  }
  
  public final long d()
  {
    return this.d;
  }
  
  public final long e()
  {
    return this.a;
  }
  
  public final class a
  {
    private static char[] n = { 80, -4441, -8712, -13277, -17578, -21920, -26437, -30846, 30251, 25963, 21631, 17087, 12762, 8267, 8959, -13251, -225, -4400, -26225, -30488, 31732, -27338, -22988, -18462, -16247, -11846, -7375, -7642, 3300, 16352, 11827, 22875, 18550, 31419, 26005, 44, -4370, -8754, -13287, -17589, -21957, 7531, -3159, -16244, -11954, -23017, -18656, -31235, -25896, 44, -4370, -8756, -13303, -17659, -10529, 14365, 2876, 6893, 28074, 31873, 20050, 20770, -24386, 5114, -712, -12796, -8227, -22397, -17995, -29889, 93 };
    private static long o = 1164909321911856846L;
    public String a = "";
    public String b = "";
    public String c = "";
    long d = 0L;
    long e = 0L;
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";
    private String k = "";
    private String l = "";
    
    public a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
    {
      this.f = paramString1;
      this.a = paramString2;
      this.g = paramString3;
      this.h = paramString4;
      this.i = paramString5;
      this.j = paramString6;
      this.k = paramString7;
      this.l = paramString8;
      this.b = paramString9;
      this.d = 0L;
    }
    
    private static String a(char paramChar, int paramInt1, int paramInt2)
    {
      char[] arrayOfChar = new char[paramInt1];
      int i2;
      for (int i1 = 0;; i1 = i2 + 1)
      {
        i2 = i1;
        int i3;
        if (i1 < paramInt1)
        {
          i3 = 71;
          i2 = i1;
        }
        for (i1 = i3;; i1 = 15) {
          switch (i1)
          {
          }
        }
        arrayOfChar[i2] = ((char)(int)(n[(paramInt2 + i2)] ^ i2 * o ^ paramChar));
      }
      return new String(arrayOfChar);
    }
    
    public final String toString()
    {
      return a('\000', 14, 0).intern() + this.f + a('⋓', 6, 14).intern() + this.a + a('篘', 7, 20).intern() + this.g + a(57866, 8, 27).intern() + this.h + a('\000', 6, 35).intern() + this.i + a('ᵇ', 8, 41).intern() + this.j + a('\000', 5, 49).intern() + this.k + a(55027, 9, 54).intern() + this.l + a('Ꮦ', 7, 63).intern() + this.b + a('\000', 1, 70).intern();
    }
  }
  
  public final class b
  {
    long a;
    String b;
    
    public b() {}
    
    public b()
    {
      this.a = 0L;
      this.b = "";
    }
  }
}
