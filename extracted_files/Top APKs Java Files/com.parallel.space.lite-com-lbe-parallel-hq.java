package com.lbe.parallel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseArray;
import com.lbe.parallel.model.PackageManagerDied;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class hq
{
  private static List<hr> a = new ArrayList();
  private static Set<String> b = new HashSet();
  private static Set<String> c = new HashSet();
  private static hq h;
  private ao<String, HashSet<String>> d = new ao();
  private SharedPreferences e;
  private Handler f;
  private fq g;
  private volatile boolean i = false;
  private Runnable j = new Runnable()
  {
    public final void run()
    {
      hq.a(hq.this).removeCallbacks(this);
      new Thread(new Runnable()
      {
        public final void run()
        {
          try
          {
            SharedPreferences.Editor localEditor = hq.b(hq.this).edit();
            Iterator localIterator = hq.c(hq.this).entrySet().iterator();
            while (localIterator.hasNext())
            {
              Map.Entry localEntry = (Map.Entry)localIterator.next();
              localEditor.putStringSet((String)localEntry.getKey(), (Set)localEntry.getValue());
            }
            localObject.commit();
          }
          finally {}
        }
      }).start();
    }
  };
  
  static
  {
    a.add(new hr.b());
    a.add(new hr.d());
    a.add(new hr.a());
    a.add(new hr.e());
    a.add(new hr.c());
    c.add("com.excelliance.dualaid");
    c.add("com.lbe.parallel");
    c.add("com.lbe.parallel.intl");
    c.add("com.parallel.space.lite");
  }
  
  private hq(Context paramContext)
  {
    this.e = paramContext.getSharedPreferences("dependence_app", 0);
    this.f = new Handler(paramContext.getMainLooper());
    this.g = new fq(paramContext);
    this.g.getInstalledPackages(0);
    try
    {
      a();
      return;
    }
    catch (PackageManagerDied paramContext) {}
  }
  
  public static hq a(Context paramContext)
  {
    try
    {
      if (h == null) {
        h = new hq(paramContext);
      }
      paramContext = h;
      return paramContext;
    }
    finally {}
  }
  
  /* Error */
  private void a()
    throws PackageManagerDied
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 85	com/lbe/parallel/hq:i	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: new 34	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 37	java/util/ArrayList:<init>	()V
    //   21: astore 6
    //   23: new 41	java/util/HashSet
    //   26: dup
    //   27: invokespecial 42	java/util/HashSet:<init>	()V
    //   30: astore 5
    //   32: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   35: invokeinterface 141 1 0
    //   40: astore 7
    //   42: aload 7
    //   44: invokeinterface 147 1 0
    //   49: ifeq +295 -> 344
    //   52: aload 7
    //   54: invokeinterface 151 1 0
    //   59: checkcast 153	com/lbe/parallel/hr
    //   62: astore 8
    //   64: new 41	java/util/HashSet
    //   67: dup
    //   68: invokespecial 42	java/util/HashSet:<init>	()V
    //   71: astore 9
    //   73: aload 8
    //   75: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   78: invokevirtual 157	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   81: astore 10
    //   83: aload 10
    //   85: invokeinterface 147 1 0
    //   90: ifeq +115 -> 205
    //   93: aload 10
    //   95: invokeinterface 151 1 0
    //   100: checkcast 159	java/lang/String
    //   103: astore 11
    //   105: aload_0
    //   106: getfield 122	com/lbe/parallel/hq:g	Lcom/lbe/parallel/fq;
    //   109: aload 11
    //   111: aload 8
    //   113: getfield 162	com/lbe/parallel/hr:a	I
    //   116: invokestatic 165	com/lbe/parallel/hr:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Ljava/lang/Object;
    //   119: astore 4
    //   121: aload 5
    //   123: aload 11
    //   125: invokeinterface 72 2 0
    //   130: pop
    //   131: aload 4
    //   133: ifnull -50 -> 83
    //   136: new 167	android/util/SparseArray
    //   139: dup
    //   140: invokespecial 168	android/util/SparseArray:<init>	()V
    //   143: astore 12
    //   145: aload 12
    //   147: aload 8
    //   149: getfield 162	com/lbe/parallel/hr:a	I
    //   152: aload 4
    //   154: invokevirtual 172	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   157: aload 8
    //   159: getfield 175	com/lbe/parallel/hr:e	Ljava/util/HashMap;
    //   162: aload 11
    //   164: aload 12
    //   166: invokevirtual 180	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: goto -87 -> 83
    //   173: astore 4
    //   175: aload_0
    //   176: monitorexit
    //   177: aload 4
    //   179: athrow
    //   180: astore 4
    //   182: aconst_null
    //   183: astore 4
    //   185: aload 8
    //   187: aload 11
    //   189: invokevirtual 183	com/lbe/parallel/hr:a	(Ljava/lang/String;)Z
    //   192: ifeq +70 -> 262
    //   195: aload 6
    //   197: aload 8
    //   199: invokeinterface 55 2 0
    //   204: pop
    //   205: aload 6
    //   207: aload 8
    //   209: invokeinterface 186 2 0
    //   214: ifne -172 -> 42
    //   217: aload 9
    //   219: invokeinterface 190 1 0
    //   224: ifle -182 -> 42
    //   227: aload 8
    //   229: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   232: aload 9
    //   234: invokevirtual 194	java/util/HashSet:removeAll	(Ljava/util/Collection;)Z
    //   237: pop
    //   238: aload 8
    //   240: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   243: invokevirtual 195	java/util/HashSet:size	()I
    //   246: ifne -204 -> 42
    //   249: aload 6
    //   251: aload 8
    //   253: invokeinterface 55 2 0
    //   258: pop
    //   259: goto -217 -> 42
    //   262: aload 9
    //   264: aload 11
    //   266: invokeinterface 72 2 0
    //   271: pop
    //   272: goto -141 -> 131
    //   275: astore 12
    //   277: aload 5
    //   279: aload 11
    //   281: invokeinterface 72 2 0
    //   286: pop
    //   287: aload 11
    //   289: aload 8
    //   291: getfield 162	com/lbe/parallel/hr:a	I
    //   294: invokestatic 198	com/lbe/parallel/hr:a	(Ljava/lang/String;I)Ljava/lang/Object;
    //   297: astore 4
    //   299: aload 4
    //   301: ifnonnull +1087 -> 1388
    //   304: new 82	com/lbe/parallel/model/PackageManagerDied
    //   307: dup
    //   308: new 200	java/lang/StringBuilder
    //   311: dup
    //   312: ldc -54
    //   314: invokespecial 205	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   317: aload 8
    //   319: getfield 162	com/lbe/parallel/hr:a	I
    //   322: invokevirtual 209	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc -45
    //   327: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: aload 11
    //   332: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   338: aload 12
    //   340: invokespecial 221	com/lbe/parallel/model/PackageManagerDied:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   343: athrow
    //   344: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   347: aload 6
    //   349: invokeinterface 222 2 0
    //   354: pop
    //   355: getstatic 44	com/lbe/parallel/hq:b	Ljava/util/Set;
    //   358: aload 5
    //   360: invokeinterface 225 2 0
    //   365: pop
    //   366: new 41	java/util/HashSet
    //   369: dup
    //   370: invokespecial 42	java/util/HashSet:<init>	()V
    //   373: astore 4
    //   375: aload_0
    //   376: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   379: ldc -29
    //   381: ldc -27
    //   383: invokeinterface 235 3 0
    //   388: astore 6
    //   390: aload 6
    //   392: invokestatic 241	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   395: ifne +50 -> 445
    //   398: aload 6
    //   400: ldc -13
    //   402: invokevirtual 247	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   405: astore 6
    //   407: aload 6
    //   409: arraylength
    //   410: istore_2
    //   411: iconst_0
    //   412: istore_1
    //   413: iload_1
    //   414: iload_2
    //   415: if_icmpge +30 -> 445
    //   418: aload 6
    //   420: iload_1
    //   421: aaload
    //   422: astore 7
    //   424: aload 7
    //   426: invokestatic 241	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   429: ifne +962 -> 1391
    //   432: aload 4
    //   434: aload 7
    //   436: invokeinterface 72 2 0
    //   441: pop
    //   442: goto +949 -> 1391
    //   445: aload 5
    //   447: invokeinterface 190 1 0
    //   452: aload 4
    //   454: invokeinterface 190 1 0
    //   459: if_icmple +102 -> 561
    //   462: iconst_1
    //   463: istore_1
    //   464: iload_1
    //   465: ifne +199 -> 664
    //   468: aload 5
    //   470: invokeinterface 248 1 0
    //   475: astore 4
    //   477: aload 4
    //   479: invokeinterface 147 1 0
    //   484: ifeq +894 -> 1378
    //   487: aload 4
    //   489: invokeinterface 151 1 0
    //   494: checkcast 159	java/lang/String
    //   497: astore 6
    //   499: new 41	java/util/HashSet
    //   502: dup
    //   503: invokespecial 42	java/util/HashSet:<init>	()V
    //   506: astore 7
    //   508: aload_0
    //   509: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   512: aload 6
    //   514: invokeinterface 250 2 0
    //   519: ifeq +879 -> 1398
    //   522: aload 7
    //   524: aload_0
    //   525: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   528: aload 6
    //   530: new 41	java/util/HashSet
    //   533: dup
    //   534: invokespecial 42	java/util/HashSet:<init>	()V
    //   537: invokeinterface 254 3 0
    //   542: invokevirtual 255	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   545: pop
    //   546: aload_0
    //   547: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   550: aload 6
    //   552: aload 7
    //   554: invokevirtual 256	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   557: pop
    //   558: goto -81 -> 477
    //   561: aload 5
    //   563: invokeinterface 248 1 0
    //   568: astore 6
    //   570: aload 6
    //   572: invokeinterface 147 1 0
    //   577: ifeq +806 -> 1383
    //   580: aload 4
    //   582: aload 6
    //   584: invokeinterface 151 1 0
    //   589: checkcast 159	java/lang/String
    //   592: invokeinterface 257 2 0
    //   597: ifne -27 -> 570
    //   600: iconst_1
    //   601: istore_1
    //   602: goto -138 -> 464
    //   605: iload_1
    //   606: ifeq +58 -> 664
    //   609: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   612: invokeinterface 141 1 0
    //   617: astore 4
    //   619: aload 4
    //   621: invokeinterface 147 1 0
    //   626: ifeq +30 -> 656
    //   629: aload 4
    //   631: invokeinterface 151 1 0
    //   636: checkcast 153	com/lbe/parallel/hr
    //   639: astore 5
    //   641: aload 5
    //   643: aconst_null
    //   644: putfield 259	com/lbe/parallel/hr:d	Ljava/util/HashSet;
    //   647: aload 5
    //   649: aconst_null
    //   650: putfield 175	com/lbe/parallel/hr:e	Ljava/util/HashMap;
    //   653: goto -34 -> 619
    //   656: aload_0
    //   657: iconst_1
    //   658: putfield 85	com/lbe/parallel/hq:i	Z
    //   661: goto -650 -> 11
    //   664: aload_0
    //   665: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   668: invokeinterface 263 1 0
    //   673: invokeinterface 268 1 0
    //   678: invokeinterface 271 1 0
    //   683: pop
    //   684: new 200	java/lang/StringBuilder
    //   687: dup
    //   688: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   691: astore 4
    //   693: aload 5
    //   695: invokeinterface 248 1 0
    //   700: astore 5
    //   702: aload 5
    //   704: invokeinterface 147 1 0
    //   709: ifeq +27 -> 736
    //   712: aload 4
    //   714: aload 5
    //   716: invokeinterface 151 1 0
    //   721: checkcast 159	java/lang/String
    //   724: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   727: ldc -13
    //   729: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   732: pop
    //   733: goto -31 -> 702
    //   736: aload_0
    //   737: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   740: invokevirtual 274	com/lbe/parallel/ao:clear	()V
    //   743: aload_0
    //   744: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   747: invokeinterface 263 1 0
    //   752: ldc -29
    //   754: aload 4
    //   756: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   759: invokeinterface 278 3 0
    //   764: invokeinterface 271 1 0
    //   769: pop
    //   770: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   773: invokeinterface 141 1 0
    //   778: astore 5
    //   780: aload 5
    //   782: invokeinterface 147 1 0
    //   787: ifeq +250 -> 1037
    //   790: aload 5
    //   792: invokeinterface 151 1 0
    //   797: checkcast 153	com/lbe/parallel/hr
    //   800: astore 6
    //   802: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   805: invokeinterface 141 1 0
    //   810: astore 7
    //   812: aload 7
    //   814: invokeinterface 147 1 0
    //   819: ifeq -39 -> 780
    //   822: aload 7
    //   824: invokeinterface 151 1 0
    //   829: checkcast 153	com/lbe/parallel/hr
    //   832: astore 8
    //   834: aload 6
    //   836: aload 8
    //   838: if_acmpeq -26 -> 812
    //   841: aload 6
    //   843: getfield 175	com/lbe/parallel/hr:e	Ljava/util/HashMap;
    //   846: invokevirtual 282	java/util/HashMap:keySet	()Ljava/util/Set;
    //   849: invokeinterface 248 1 0
    //   854: astore 9
    //   856: aload 9
    //   858: invokeinterface 147 1 0
    //   863: ifeq -51 -> 812
    //   866: aload 9
    //   868: invokeinterface 151 1 0
    //   873: checkcast 159	java/lang/String
    //   876: astore 11
    //   878: aload 6
    //   880: getfield 175	com/lbe/parallel/hr:e	Ljava/util/HashMap;
    //   883: aload 11
    //   885: invokevirtual 286	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   888: checkcast 167	android/util/SparseArray
    //   891: astore 10
    //   893: aload 10
    //   895: ifnull -39 -> 856
    //   898: aload 10
    //   900: aload 8
    //   902: getfield 162	com/lbe/parallel/hr:a	I
    //   905: invokevirtual 290	android/util/SparseArray:indexOfKey	(I)I
    //   908: iflt +39 -> 947
    //   911: aload 10
    //   913: aload 8
    //   915: getfield 162	com/lbe/parallel/hr:a	I
    //   918: invokevirtual 293	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   921: astore 4
    //   923: aload 8
    //   925: aload 4
    //   927: invokevirtual 295	com/lbe/parallel/hr:a	(Ljava/lang/Object;)Z
    //   930: ifeq -74 -> 856
    //   933: aload 6
    //   935: getfield 259	com/lbe/parallel/hr:d	Ljava/util/HashSet;
    //   938: aload 8
    //   940: invokevirtual 296	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   943: pop
    //   944: goto -132 -> 812
    //   947: aload_0
    //   948: getfield 122	com/lbe/parallel/hq:g	Lcom/lbe/parallel/fq;
    //   951: aload 11
    //   953: aload 8
    //   955: getfield 162	com/lbe/parallel/hr:a	I
    //   958: invokestatic 165	com/lbe/parallel/hr:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Ljava/lang/Object;
    //   961: astore 4
    //   963: aload 10
    //   965: aload 8
    //   967: getfield 162	com/lbe/parallel/hr:a	I
    //   970: aload 4
    //   972: invokevirtual 172	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   975: goto -52 -> 923
    //   978: astore 12
    //   980: aload 11
    //   982: aload 8
    //   984: getfield 162	com/lbe/parallel/hr:a	I
    //   987: invokestatic 198	com/lbe/parallel/hr:a	(Ljava/lang/String;I)Ljava/lang/Object;
    //   990: astore 4
    //   992: aload 4
    //   994: ifnonnull +381 -> 1375
    //   997: new 82	com/lbe/parallel/model/PackageManagerDied
    //   1000: dup
    //   1001: new 200	java/lang/StringBuilder
    //   1004: dup
    //   1005: ldc -54
    //   1007: invokespecial 205	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1010: aload 6
    //   1012: getfield 162	com/lbe/parallel/hr:a	I
    //   1015: invokevirtual 209	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1018: ldc -45
    //   1020: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1023: aload 11
    //   1025: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1028: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1031: aload 12
    //   1033: invokespecial 221	com/lbe/parallel/model/PackageManagerDied:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1036: athrow
    //   1037: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   1040: invokeinterface 141 1 0
    //   1045: astore 4
    //   1047: aload 4
    //   1049: invokeinterface 147 1 0
    //   1054: ifeq +39 -> 1093
    //   1057: aload 4
    //   1059: invokeinterface 151 1 0
    //   1064: checkcast 153	com/lbe/parallel/hr
    //   1067: astore 5
    //   1069: aload 5
    //   1071: getfield 259	com/lbe/parallel/hr:d	Ljava/util/HashSet;
    //   1074: invokevirtual 195	java/util/HashSet:size	()I
    //   1077: ifeq -30 -> 1047
    //   1080: aload 5
    //   1082: aload 5
    //   1084: getfield 259	com/lbe/parallel/hr:d	Ljava/util/HashSet;
    //   1087: invokestatic 299	com/lbe/parallel/hq:a	(Lcom/lbe/parallel/hr;Ljava/util/Set;)V
    //   1090: goto -43 -> 1047
    //   1093: getstatic 39	com/lbe/parallel/hq:a	Ljava/util/List;
    //   1096: invokeinterface 141 1 0
    //   1101: astore 4
    //   1103: aload 4
    //   1105: invokeinterface 147 1 0
    //   1110: ifeq +240 -> 1350
    //   1113: aload 4
    //   1115: invokeinterface 151 1 0
    //   1120: checkcast 153	com/lbe/parallel/hr
    //   1123: astore 5
    //   1125: aload 5
    //   1127: getfield 259	com/lbe/parallel/hr:d	Ljava/util/HashSet;
    //   1130: invokevirtual 157	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1133: astore 6
    //   1135: aload 6
    //   1137: invokeinterface 147 1 0
    //   1142: ifeq +32 -> 1174
    //   1145: aload 6
    //   1147: invokeinterface 151 1 0
    //   1152: checkcast 153	com/lbe/parallel/hr
    //   1155: astore 7
    //   1157: aload 5
    //   1159: getfield 301	com/lbe/parallel/hr:c	Ljava/util/HashSet;
    //   1162: aload 7
    //   1164: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   1167: invokevirtual 255	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   1170: pop
    //   1171: goto -36 -> 1135
    //   1174: aload 5
    //   1176: getfield 301	com/lbe/parallel/hr:c	Ljava/util/HashSet;
    //   1179: aload 5
    //   1181: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   1184: invokevirtual 194	java/util/HashSet:removeAll	(Ljava/util/Collection;)Z
    //   1187: pop
    //   1188: aload 5
    //   1190: aconst_null
    //   1191: putfield 259	com/lbe/parallel/hr:d	Ljava/util/HashSet;
    //   1194: aload 5
    //   1196: aconst_null
    //   1197: putfield 175	com/lbe/parallel/hr:e	Ljava/util/HashMap;
    //   1200: aload 5
    //   1202: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   1205: invokevirtual 157	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1208: astore 6
    //   1210: aload 6
    //   1212: invokeinterface 147 1 0
    //   1217: ifeq -114 -> 1103
    //   1220: aload 6
    //   1222: invokeinterface 151 1 0
    //   1227: checkcast 159	java/lang/String
    //   1230: astore 7
    //   1232: aload 5
    //   1234: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   1237: invokevirtual 195	java/util/HashSet:size	()I
    //   1240: iconst_1
    //   1241: if_icmple +91 -> 1332
    //   1244: new 41	java/util/HashSet
    //   1247: dup
    //   1248: invokespecial 42	java/util/HashSet:<init>	()V
    //   1251: astore 8
    //   1253: aload 5
    //   1255: getfield 156	com/lbe/parallel/hr:b	Ljava/util/HashSet;
    //   1258: invokevirtual 157	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1261: astore 9
    //   1263: aload 9
    //   1265: invokeinterface 147 1 0
    //   1270: ifeq +36 -> 1306
    //   1273: aload 9
    //   1275: invokeinterface 151 1 0
    //   1280: checkcast 159	java/lang/String
    //   1283: astore 10
    //   1285: aload 7
    //   1287: aload 10
    //   1289: invokestatic 305	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   1292: ifne -29 -> 1263
    //   1295: aload 8
    //   1297: aload 10
    //   1299: invokevirtual 296	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1302: pop
    //   1303: goto -40 -> 1263
    //   1306: aload 8
    //   1308: aload 5
    //   1310: getfield 301	com/lbe/parallel/hr:c	Ljava/util/HashSet;
    //   1313: invokevirtual 255	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   1316: pop
    //   1317: aload_0
    //   1318: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   1321: aload 7
    //   1323: aload 8
    //   1325: invokevirtual 256	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1328: pop
    //   1329: goto -119 -> 1210
    //   1332: aload_0
    //   1333: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   1336: aload 7
    //   1338: aload 5
    //   1340: getfield 301	com/lbe/parallel/hr:c	Ljava/util/HashSet;
    //   1343: invokevirtual 256	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1346: pop
    //   1347: goto -137 -> 1210
    //   1350: aload_0
    //   1351: getfield 116	com/lbe/parallel/hq:f	Landroid/os/Handler;
    //   1354: aload_0
    //   1355: getfield 90	com/lbe/parallel/hq:j	Ljava/lang/Runnable;
    //   1358: invokevirtual 309	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   1361: pop
    //   1362: aload_0
    //   1363: iconst_1
    //   1364: putfield 85	com/lbe/parallel/hq:i	Z
    //   1367: goto -1356 -> 11
    //   1370: astore 12
    //   1372: goto -1187 -> 185
    //   1375: goto -412 -> 963
    //   1378: iconst_1
    //   1379: istore_1
    //   1380: goto -775 -> 605
    //   1383: iconst_0
    //   1384: istore_1
    //   1385: goto -921 -> 464
    //   1388: goto -1257 -> 131
    //   1391: iload_1
    //   1392: iconst_1
    //   1393: iadd
    //   1394: istore_1
    //   1395: goto -982 -> 413
    //   1398: iconst_0
    //   1399: istore_1
    //   1400: goto -795 -> 605
    //   1403: astore 4
    //   1405: aconst_null
    //   1406: astore 4
    //   1408: goto -445 -> 963
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1411	0	this	hq
    //   412	988	1	k	int
    //   410	6	2	m	int
    //   6	2	3	bool	boolean
    //   119	34	4	localObject1	Object
    //   173	5	4	localObject2	Object
    //   180	1	4	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   183	931	4	localObject3	Object
    //   1403	1	4	localNameNotFoundException2	PackageManager.NameNotFoundException
    //   1406	1	4	localObject4	Object
    //   30	1309	5	localObject5	Object
    //   21	1200	6	localObject6	Object
    //   40	1297	7	localObject7	Object
    //   62	1262	8	localObject8	Object
    //   71	1203	9	localObject9	Object
    //   81	1217	10	localObject10	Object
    //   103	921	11	str	String
    //   143	22	12	localSparseArray	SparseArray
    //   275	64	12	localRuntimeException1	RuntimeException
    //   978	54	12	localRuntimeException2	RuntimeException
    //   1370	1	12	localNameNotFoundException3	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   2	7	173	finally
    //   14	42	173	finally
    //   42	83	173	finally
    //   83	105	173	finally
    //   105	121	173	finally
    //   121	131	173	finally
    //   136	170	173	finally
    //   185	205	173	finally
    //   205	259	173	finally
    //   262	272	173	finally
    //   277	299	173	finally
    //   304	344	173	finally
    //   344	411	173	finally
    //   424	442	173	finally
    //   445	462	173	finally
    //   468	477	173	finally
    //   477	558	173	finally
    //   561	570	173	finally
    //   570	600	173	finally
    //   609	619	173	finally
    //   619	653	173	finally
    //   656	661	173	finally
    //   664	702	173	finally
    //   702	733	173	finally
    //   736	780	173	finally
    //   780	812	173	finally
    //   812	834	173	finally
    //   841	856	173	finally
    //   856	893	173	finally
    //   898	923	173	finally
    //   923	944	173	finally
    //   947	963	173	finally
    //   963	975	173	finally
    //   980	992	173	finally
    //   997	1037	173	finally
    //   1037	1047	173	finally
    //   1047	1090	173	finally
    //   1093	1103	173	finally
    //   1103	1135	173	finally
    //   1135	1171	173	finally
    //   1174	1210	173	finally
    //   1210	1263	173	finally
    //   1263	1303	173	finally
    //   1306	1329	173	finally
    //   1332	1347	173	finally
    //   1350	1367	173	finally
    //   105	121	180	android/content/pm/PackageManager$NameNotFoundException
    //   105	121	275	java/lang/RuntimeException
    //   121	131	275	java/lang/RuntimeException
    //   947	963	978	java/lang/RuntimeException
    //   121	131	1370	android/content/pm/PackageManager$NameNotFoundException
    //   947	963	1403	android/content/pm/PackageManager$NameNotFoundException
  }
  
  private static void a(hr paramHr, Set<hr> paramSet)
  {
    for (;;)
    {
      Object localObject = new LinkedList(a);
      ((List)localObject).removeAll(paramHr.d);
      ((List)localObject).remove(paramHr);
      HashSet localHashSet = new HashSet();
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          break label126;
        }
        hr localHr = (hr)((Iterator)localObject).next();
        Iterator localIterator = paramSet.iterator();
        if (localIterator.hasNext())
        {
          if (!((hr)localIterator.next()).d.contains(localHr)) {
            break;
          }
          localHashSet.add(localHr);
          paramHr.d.add(localHr);
        }
      }
      label126:
      if (localHashSet.size() <= 0) {
        break;
      }
      paramSet = localHashSet;
    }
  }
  
  private void a(Set<String> paramSet, String paramString)
    throws PackageManagerDied
  {
    if (c.contains(paramString)) {
      return;
    }
    SparseArray localSparseArray = new SparseArray();
    Iterator localIterator = a.iterator();
    label32:
    label125:
    label155:
    for (;;)
    {
      Object localObject1;
      if (localIterator.hasNext())
      {
        localHr = (hr)localIterator.next();
        if (localHr.a(paramSet)) {
          continue;
        }
        localObject1 = null;
        if (localSparseArray.indexOfKey(localHr.a) < 0) {
          break label125;
        }
        localObject1 = localSparseArray.get(localHr.a);
      }
      for (;;)
      {
        if (!localHr.a(localObject1)) {
          break label155;
        }
        paramSet.addAll(localHr.b);
        paramSet.addAll(localHr.c);
        break label32;
        break;
        try
        {
          Object localObject2 = hr.a(this.g, paramString, localHr.a);
          localObject1 = localObject2;
        }
        catch (RuntimeException paramSet)
        {
          throw new PackageManagerDied("packageManagerdie type=" + localHr.a + "  " + paramString, paramSet);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;) {}
        }
        localSparseArray.put(localHr.a, localObject1);
      }
    }
  }
  
  /* Error */
  public final boolean a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 85	com/lbe/parallel/hq:i	Z
    //   6: ifeq +22 -> 28
    //   9: aload_0
    //   10: aload_1
    //   11: invokevirtual 332	com/lbe/parallel/hq:b	(Ljava/lang/String;)[Ljava/lang/String;
    //   14: pop
    //   15: aload_0
    //   16: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   19: aload_1
    //   20: invokevirtual 335	com/lbe/parallel/ao:containsKey	(Ljava/lang/Object;)Z
    //   23: istore_2
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_2
    //   27: ireturn
    //   28: iconst_0
    //   29: istore_2
    //   30: goto -6 -> 24
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	hq
    //   0	38	1	paramString	String
    //   23	7	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	24	33	finally
  }
  
  /* Error */
  public final String[] b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 85	com/lbe/parallel/hq:i	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifne +9 -> 17
    //   11: aconst_null
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: new 41	java/util/HashSet
    //   20: dup
    //   21: invokespecial 42	java/util/HashSet:<init>	()V
    //   24: astore_3
    //   25: aload_0
    //   26: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   29: aload_1
    //   30: invokevirtual 335	com/lbe/parallel/ao:containsKey	(Ljava/lang/Object;)Z
    //   33: ifeq +37 -> 70
    //   36: aload_3
    //   37: aload_0
    //   38: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   41: aload_1
    //   42: invokevirtual 336	com/lbe/parallel/ao:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   45: checkcast 338	java/util/Collection
    //   48: invokevirtual 255	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   51: pop
    //   52: aload_3
    //   53: aload_3
    //   54: invokevirtual 195	java/util/HashSet:size	()I
    //   57: anewarray 159	java/lang/String
    //   60: invokevirtual 342	java/util/HashSet:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   63: checkcast 344	[Ljava/lang/String;
    //   66: astore_1
    //   67: goto -54 -> 13
    //   70: aload_0
    //   71: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   74: aload_1
    //   75: invokeinterface 250 2 0
    //   80: ifeq -28 -> 52
    //   83: aload_3
    //   84: aload_0
    //   85: getfield 105	com/lbe/parallel/hq:e	Landroid/content/SharedPreferences;
    //   88: aload_1
    //   89: new 41	java/util/HashSet
    //   92: dup
    //   93: invokespecial 42	java/util/HashSet:<init>	()V
    //   96: invokeinterface 254 3 0
    //   101: invokevirtual 255	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   104: pop
    //   105: aload_0
    //   106: getfield 95	com/lbe/parallel/hq:d	Lcom/lbe/parallel/ao;
    //   109: aload_1
    //   110: aload_3
    //   111: invokevirtual 256	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: goto -63 -> 52
    //   118: astore_1
    //   119: aload_0
    //   120: monitorexit
    //   121: aload_1
    //   122: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	hq
    //   0	123	1	paramString	String
    //   6	2	2	bool	boolean
    //   24	87	3	localHashSet	HashSet
    // Exception table:
    //   from	to	target	type
    //   2	7	118	finally
    //   17	52	118	finally
    //   52	67	118	finally
    //   70	115	118	finally
  }
  
  public final String[] c(String paramString)
  {
    Object localObject = null;
    for (;;)
    {
      try
      {
        boolean bool = this.i;
        if (!bool) {
          return localObject;
        }
        if (b.contains(paramString))
        {
          localObject = new HashSet();
          Iterator localIterator = this.d.keySet().iterator();
          if (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            Set localSet = (Set)this.d.get(str);
            if ((localSet != null) && (localSet.contains(paramString))) {
              ((Set)localObject).add(str);
            }
          }
          else
          {
            localObject = (String[])((Set)localObject).toArray(new String[((Set)localObject).size()]);
          }
        }
      }
      finally {}
    }
  }
  
  public final String[] d(String paramString)
    throws PackageManagerDied
  {
    for (;;)
    {
      HashSet localHashSet;
      try
      {
        if (!this.i) {
          a();
        }
        SystemClock.elapsedRealtime();
        localHashSet = new HashSet();
        if (this.d.containsKey(paramString))
        {
          localHashSet.addAll((Collection)this.d.get(paramString));
          paramString = (String[])localHashSet.toArray(new String[localHashSet.size()]);
          return paramString;
        }
        if (this.e.contains(paramString))
        {
          localHashSet.addAll(this.e.getStringSet(paramString, new HashSet()));
          this.d.put(paramString, localHashSet);
          continue;
        }
        a(localHashSet, paramString);
      }
      finally {}
      this.d.put(paramString, localHashSet);
      this.f.removeCallbacks(this.j);
      this.f.postDelayed(this.j, 10000L);
    }
  }
}
