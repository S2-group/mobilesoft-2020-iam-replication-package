package com.lbe.parallel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseArray;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import com.lbe.parallel.model.PackageManagerDied;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class he
{
  private static List<hf> a = new ArrayList();
  private static Set<String> b = new HashSet();
  private static Set<String> c = new HashSet();
  private static he h;
  private ao<String, HashSet<String>> d = new ao();
  private SharedPreferences e;
  private Handler f;
  private PackageManagerWrapper g;
  private volatile boolean i = false;
  private Runnable j = new Runnable()
  {
    public final void run()
    {
      he.a(he.this).removeCallbacks(this);
      new Thread(new Runnable()
      {
        public final void run()
        {
          try
          {
            SharedPreferences.Editor localEditor = he.b(he.this).edit();
            Iterator localIterator = he.c(he.this).entrySet().iterator();
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
    a.add(new hf.b());
    a.add(new hf.d());
    a.add(new hf.a());
    a.add(new hf.e());
    a.add(new hf.c());
    c.add("com.excelliance.dualaid");
    c.add("com.lbe.parallel");
    c.add("com.lbe.parallel.intl");
  }
  
  private he(Context paramContext)
  {
    this.e = paramContext.getSharedPreferences("dependence_app", 0);
    this.f = new Handler(paramContext.getMainLooper());
    this.g = new PackageManagerWrapper(paramContext);
    this.g.getInstalledPackages(0);
    try
    {
      a();
      return;
    }
    catch (PackageManagerDied paramContext) {}
  }
  
  public static he a(Context paramContext)
  {
    try
    {
      if (h == null) {
        h = new he(paramContext);
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
    //   3: getfield 83	com/lbe/parallel/he:i	Z
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
    //   32: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   35: invokeinterface 139 1 0
    //   40: astore 7
    //   42: aload 7
    //   44: invokeinterface 145 1 0
    //   49: ifeq +299 -> 348
    //   52: aload 7
    //   54: invokeinterface 149 1 0
    //   59: checkcast 151	com/lbe/parallel/hf
    //   62: astore 8
    //   64: new 41	java/util/HashSet
    //   67: dup
    //   68: invokespecial 42	java/util/HashSet:<init>	()V
    //   71: astore 9
    //   73: aload 8
    //   75: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   78: invokevirtual 155	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   81: astore 10
    //   83: aload 10
    //   85: invokeinterface 145 1 0
    //   90: ifeq +115 -> 205
    //   93: aload 10
    //   95: invokeinterface 149 1 0
    //   100: checkcast 157	java/lang/String
    //   103: astore 11
    //   105: aload_0
    //   106: getfield 120	com/lbe/parallel/he:g	Lcom/lbe/doubleagent/utility/PackageManagerWrapper;
    //   109: aload 11
    //   111: aload 8
    //   113: getfield 160	com/lbe/parallel/hf:a	I
    //   116: invokestatic 163	com/lbe/parallel/hf:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Ljava/lang/Object;
    //   119: astore 4
    //   121: aload 5
    //   123: aload 11
    //   125: invokeinterface 72 2 0
    //   130: pop
    //   131: aload 4
    //   133: ifnull -50 -> 83
    //   136: new 165	android/util/SparseArray
    //   139: dup
    //   140: invokespecial 166	android/util/SparseArray:<init>	()V
    //   143: astore 12
    //   145: aload 12
    //   147: aload 8
    //   149: getfield 160	com/lbe/parallel/hf:a	I
    //   152: aload 4
    //   154: invokevirtual 170	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   157: aload 8
    //   159: getfield 173	com/lbe/parallel/hf:e	Ljava/util/HashMap;
    //   162: aload 11
    //   164: aload 12
    //   166: invokevirtual 178	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
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
    //   189: invokevirtual 181	com/lbe/parallel/hf:a	(Ljava/lang/String;)Z
    //   192: ifeq +70 -> 262
    //   195: aload 6
    //   197: aload 8
    //   199: invokeinterface 55 2 0
    //   204: pop
    //   205: aload 6
    //   207: aload 8
    //   209: invokeinterface 184 2 0
    //   214: ifne -172 -> 42
    //   217: aload 9
    //   219: invokeinterface 188 1 0
    //   224: ifle -182 -> 42
    //   227: aload 8
    //   229: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   232: aload 9
    //   234: invokevirtual 192	java/util/HashSet:removeAll	(Ljava/util/Collection;)Z
    //   237: pop
    //   238: aload 8
    //   240: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   243: invokevirtual 193	java/util/HashSet:size	()I
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
    //   287: aload_0
    //   288: getfield 120	com/lbe/parallel/he:g	Lcom/lbe/doubleagent/utility/PackageManagerWrapper;
    //   291: aload 11
    //   293: aload 8
    //   295: getfield 160	com/lbe/parallel/hf:a	I
    //   298: invokestatic 195	com/lbe/parallel/hf:b	(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Ljava/lang/Object;
    //   301: astore 4
    //   303: aload 4
    //   305: ifnonnull +1091 -> 1396
    //   308: new 80	com/lbe/parallel/model/PackageManagerDied
    //   311: dup
    //   312: new 197	java/lang/StringBuilder
    //   315: dup
    //   316: ldc -57
    //   318: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   321: aload 8
    //   323: getfield 160	com/lbe/parallel/hf:a	I
    //   326: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   329: ldc -48
    //   331: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: aload 11
    //   336: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   342: aload 12
    //   344: invokespecial 218	com/lbe/parallel/model/PackageManagerDied:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   347: athrow
    //   348: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   351: aload 6
    //   353: invokeinterface 219 2 0
    //   358: pop
    //   359: getstatic 44	com/lbe/parallel/he:b	Ljava/util/Set;
    //   362: aload 5
    //   364: invokeinterface 222 2 0
    //   369: pop
    //   370: new 41	java/util/HashSet
    //   373: dup
    //   374: invokespecial 42	java/util/HashSet:<init>	()V
    //   377: astore 4
    //   379: aload_0
    //   380: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   383: ldc -32
    //   385: ldc -30
    //   387: invokeinterface 232 3 0
    //   392: astore 6
    //   394: aload 6
    //   396: invokestatic 238	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   399: ifne +50 -> 449
    //   402: aload 6
    //   404: ldc -16
    //   406: invokevirtual 244	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   409: astore 6
    //   411: aload 6
    //   413: arraylength
    //   414: istore_2
    //   415: iconst_0
    //   416: istore_1
    //   417: iload_1
    //   418: iload_2
    //   419: if_icmpge +30 -> 449
    //   422: aload 6
    //   424: iload_1
    //   425: aaload
    //   426: astore 7
    //   428: aload 7
    //   430: invokestatic 238	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   433: ifne +966 -> 1399
    //   436: aload 4
    //   438: aload 7
    //   440: invokeinterface 72 2 0
    //   445: pop
    //   446: goto +953 -> 1399
    //   449: aload 5
    //   451: invokeinterface 188 1 0
    //   456: aload 4
    //   458: invokeinterface 188 1 0
    //   463: if_icmple +102 -> 565
    //   466: iconst_1
    //   467: istore_1
    //   468: iload_1
    //   469: ifne +199 -> 668
    //   472: aload 5
    //   474: invokeinterface 245 1 0
    //   479: astore 4
    //   481: aload 4
    //   483: invokeinterface 145 1 0
    //   488: ifeq +898 -> 1386
    //   491: aload 4
    //   493: invokeinterface 149 1 0
    //   498: checkcast 157	java/lang/String
    //   501: astore 6
    //   503: new 41	java/util/HashSet
    //   506: dup
    //   507: invokespecial 42	java/util/HashSet:<init>	()V
    //   510: astore 7
    //   512: aload_0
    //   513: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   516: aload 6
    //   518: invokeinterface 247 2 0
    //   523: ifeq +883 -> 1406
    //   526: aload 7
    //   528: aload_0
    //   529: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   532: aload 6
    //   534: new 41	java/util/HashSet
    //   537: dup
    //   538: invokespecial 42	java/util/HashSet:<init>	()V
    //   541: invokeinterface 251 3 0
    //   546: invokevirtual 252	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   549: pop
    //   550: aload_0
    //   551: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   554: aload 6
    //   556: aload 7
    //   558: invokevirtual 253	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   561: pop
    //   562: goto -81 -> 481
    //   565: aload 5
    //   567: invokeinterface 245 1 0
    //   572: astore 6
    //   574: aload 6
    //   576: invokeinterface 145 1 0
    //   581: ifeq +810 -> 1391
    //   584: aload 4
    //   586: aload 6
    //   588: invokeinterface 149 1 0
    //   593: checkcast 157	java/lang/String
    //   596: invokeinterface 254 2 0
    //   601: ifne -27 -> 574
    //   604: iconst_1
    //   605: istore_1
    //   606: goto -138 -> 468
    //   609: iload_1
    //   610: ifeq +58 -> 668
    //   613: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   616: invokeinterface 139 1 0
    //   621: astore 4
    //   623: aload 4
    //   625: invokeinterface 145 1 0
    //   630: ifeq +30 -> 660
    //   633: aload 4
    //   635: invokeinterface 149 1 0
    //   640: checkcast 151	com/lbe/parallel/hf
    //   643: astore 5
    //   645: aload 5
    //   647: aconst_null
    //   648: putfield 256	com/lbe/parallel/hf:d	Ljava/util/HashSet;
    //   651: aload 5
    //   653: aconst_null
    //   654: putfield 173	com/lbe/parallel/hf:e	Ljava/util/HashMap;
    //   657: goto -34 -> 623
    //   660: aload_0
    //   661: iconst_1
    //   662: putfield 83	com/lbe/parallel/he:i	Z
    //   665: goto -654 -> 11
    //   668: aload_0
    //   669: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   672: invokeinterface 260 1 0
    //   677: invokeinterface 265 1 0
    //   682: invokeinterface 268 1 0
    //   687: pop
    //   688: new 197	java/lang/StringBuilder
    //   691: dup
    //   692: invokespecial 269	java/lang/StringBuilder:<init>	()V
    //   695: astore 4
    //   697: aload 5
    //   699: invokeinterface 245 1 0
    //   704: astore 5
    //   706: aload 5
    //   708: invokeinterface 145 1 0
    //   713: ifeq +27 -> 740
    //   716: aload 4
    //   718: aload 5
    //   720: invokeinterface 149 1 0
    //   725: checkcast 157	java/lang/String
    //   728: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: ldc -16
    //   733: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: pop
    //   737: goto -31 -> 706
    //   740: aload_0
    //   741: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   744: invokevirtual 271	com/lbe/parallel/ao:clear	()V
    //   747: aload_0
    //   748: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   751: invokeinterface 260 1 0
    //   756: ldc -32
    //   758: aload 4
    //   760: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   763: invokeinterface 275 3 0
    //   768: invokeinterface 268 1 0
    //   773: pop
    //   774: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   777: invokeinterface 139 1 0
    //   782: astore 5
    //   784: aload 5
    //   786: invokeinterface 145 1 0
    //   791: ifeq +254 -> 1045
    //   794: aload 5
    //   796: invokeinterface 149 1 0
    //   801: checkcast 151	com/lbe/parallel/hf
    //   804: astore 6
    //   806: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   809: invokeinterface 139 1 0
    //   814: astore 7
    //   816: aload 7
    //   818: invokeinterface 145 1 0
    //   823: ifeq -39 -> 784
    //   826: aload 7
    //   828: invokeinterface 149 1 0
    //   833: checkcast 151	com/lbe/parallel/hf
    //   836: astore 8
    //   838: aload 6
    //   840: aload 8
    //   842: if_acmpeq -26 -> 816
    //   845: aload 6
    //   847: getfield 173	com/lbe/parallel/hf:e	Ljava/util/HashMap;
    //   850: invokevirtual 279	java/util/HashMap:keySet	()Ljava/util/Set;
    //   853: invokeinterface 245 1 0
    //   858: astore 9
    //   860: aload 9
    //   862: invokeinterface 145 1 0
    //   867: ifeq -51 -> 816
    //   870: aload 9
    //   872: invokeinterface 149 1 0
    //   877: checkcast 157	java/lang/String
    //   880: astore 11
    //   882: aload 6
    //   884: getfield 173	com/lbe/parallel/hf:e	Ljava/util/HashMap;
    //   887: aload 11
    //   889: invokevirtual 283	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   892: checkcast 165	android/util/SparseArray
    //   895: astore 10
    //   897: aload 10
    //   899: ifnull -39 -> 860
    //   902: aload 10
    //   904: aload 8
    //   906: getfield 160	com/lbe/parallel/hf:a	I
    //   909: invokevirtual 287	android/util/SparseArray:indexOfKey	(I)I
    //   912: iflt +39 -> 951
    //   915: aload 10
    //   917: aload 8
    //   919: getfield 160	com/lbe/parallel/hf:a	I
    //   922: invokevirtual 290	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   925: astore 4
    //   927: aload 8
    //   929: aload 4
    //   931: invokevirtual 292	com/lbe/parallel/hf:a	(Ljava/lang/Object;)Z
    //   934: ifeq -74 -> 860
    //   937: aload 6
    //   939: getfield 256	com/lbe/parallel/hf:d	Ljava/util/HashSet;
    //   942: aload 8
    //   944: invokevirtual 293	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   947: pop
    //   948: goto -132 -> 816
    //   951: aload_0
    //   952: getfield 120	com/lbe/parallel/he:g	Lcom/lbe/doubleagent/utility/PackageManagerWrapper;
    //   955: aload 11
    //   957: aload 8
    //   959: getfield 160	com/lbe/parallel/hf:a	I
    //   962: invokestatic 163	com/lbe/parallel/hf:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Ljava/lang/Object;
    //   965: astore 4
    //   967: aload 10
    //   969: aload 8
    //   971: getfield 160	com/lbe/parallel/hf:a	I
    //   974: aload 4
    //   976: invokevirtual 170	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   979: goto -52 -> 927
    //   982: astore 12
    //   984: aload_0
    //   985: getfield 120	com/lbe/parallel/he:g	Lcom/lbe/doubleagent/utility/PackageManagerWrapper;
    //   988: aload 11
    //   990: aload 8
    //   992: getfield 160	com/lbe/parallel/hf:a	I
    //   995: invokestatic 195	com/lbe/parallel/hf:b	(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Ljava/lang/Object;
    //   998: astore 4
    //   1000: aload 4
    //   1002: ifnonnull +381 -> 1383
    //   1005: new 80	com/lbe/parallel/model/PackageManagerDied
    //   1008: dup
    //   1009: new 197	java/lang/StringBuilder
    //   1012: dup
    //   1013: ldc -57
    //   1015: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1018: aload 6
    //   1020: getfield 160	com/lbe/parallel/hf:a	I
    //   1023: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1026: ldc -48
    //   1028: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: aload 11
    //   1033: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1036: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1039: aload 12
    //   1041: invokespecial 218	com/lbe/parallel/model/PackageManagerDied:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1044: athrow
    //   1045: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   1048: invokeinterface 139 1 0
    //   1053: astore 4
    //   1055: aload 4
    //   1057: invokeinterface 145 1 0
    //   1062: ifeq +39 -> 1101
    //   1065: aload 4
    //   1067: invokeinterface 149 1 0
    //   1072: checkcast 151	com/lbe/parallel/hf
    //   1075: astore 5
    //   1077: aload 5
    //   1079: getfield 256	com/lbe/parallel/hf:d	Ljava/util/HashSet;
    //   1082: invokevirtual 193	java/util/HashSet:size	()I
    //   1085: ifeq -30 -> 1055
    //   1088: aload 5
    //   1090: aload 5
    //   1092: getfield 256	com/lbe/parallel/hf:d	Ljava/util/HashSet;
    //   1095: invokestatic 296	com/lbe/parallel/he:a	(Lcom/lbe/parallel/hf;Ljava/util/Set;)V
    //   1098: goto -43 -> 1055
    //   1101: getstatic 39	com/lbe/parallel/he:a	Ljava/util/List;
    //   1104: invokeinterface 139 1 0
    //   1109: astore 4
    //   1111: aload 4
    //   1113: invokeinterface 145 1 0
    //   1118: ifeq +240 -> 1358
    //   1121: aload 4
    //   1123: invokeinterface 149 1 0
    //   1128: checkcast 151	com/lbe/parallel/hf
    //   1131: astore 5
    //   1133: aload 5
    //   1135: getfield 256	com/lbe/parallel/hf:d	Ljava/util/HashSet;
    //   1138: invokevirtual 155	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1141: astore 6
    //   1143: aload 6
    //   1145: invokeinterface 145 1 0
    //   1150: ifeq +32 -> 1182
    //   1153: aload 6
    //   1155: invokeinterface 149 1 0
    //   1160: checkcast 151	com/lbe/parallel/hf
    //   1163: astore 7
    //   1165: aload 5
    //   1167: getfield 298	com/lbe/parallel/hf:c	Ljava/util/HashSet;
    //   1170: aload 7
    //   1172: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   1175: invokevirtual 252	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   1178: pop
    //   1179: goto -36 -> 1143
    //   1182: aload 5
    //   1184: getfield 298	com/lbe/parallel/hf:c	Ljava/util/HashSet;
    //   1187: aload 5
    //   1189: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   1192: invokevirtual 192	java/util/HashSet:removeAll	(Ljava/util/Collection;)Z
    //   1195: pop
    //   1196: aload 5
    //   1198: aconst_null
    //   1199: putfield 256	com/lbe/parallel/hf:d	Ljava/util/HashSet;
    //   1202: aload 5
    //   1204: aconst_null
    //   1205: putfield 173	com/lbe/parallel/hf:e	Ljava/util/HashMap;
    //   1208: aload 5
    //   1210: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   1213: invokevirtual 155	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1216: astore 6
    //   1218: aload 6
    //   1220: invokeinterface 145 1 0
    //   1225: ifeq -114 -> 1111
    //   1228: aload 6
    //   1230: invokeinterface 149 1 0
    //   1235: checkcast 157	java/lang/String
    //   1238: astore 7
    //   1240: aload 5
    //   1242: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   1245: invokevirtual 193	java/util/HashSet:size	()I
    //   1248: iconst_1
    //   1249: if_icmple +91 -> 1340
    //   1252: new 41	java/util/HashSet
    //   1255: dup
    //   1256: invokespecial 42	java/util/HashSet:<init>	()V
    //   1259: astore 8
    //   1261: aload 5
    //   1263: getfield 154	com/lbe/parallel/hf:b	Ljava/util/HashSet;
    //   1266: invokevirtual 155	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1269: astore 9
    //   1271: aload 9
    //   1273: invokeinterface 145 1 0
    //   1278: ifeq +36 -> 1314
    //   1281: aload 9
    //   1283: invokeinterface 149 1 0
    //   1288: checkcast 157	java/lang/String
    //   1291: astore 10
    //   1293: aload 7
    //   1295: aload 10
    //   1297: invokestatic 302	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   1300: ifne -29 -> 1271
    //   1303: aload 8
    //   1305: aload 10
    //   1307: invokevirtual 293	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1310: pop
    //   1311: goto -40 -> 1271
    //   1314: aload 8
    //   1316: aload 5
    //   1318: getfield 298	com/lbe/parallel/hf:c	Ljava/util/HashSet;
    //   1321: invokevirtual 252	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   1324: pop
    //   1325: aload_0
    //   1326: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   1329: aload 7
    //   1331: aload 8
    //   1333: invokevirtual 253	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1336: pop
    //   1337: goto -119 -> 1218
    //   1340: aload_0
    //   1341: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   1344: aload 7
    //   1346: aload 5
    //   1348: getfield 298	com/lbe/parallel/hf:c	Ljava/util/HashSet;
    //   1351: invokevirtual 253	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1354: pop
    //   1355: goto -137 -> 1218
    //   1358: aload_0
    //   1359: getfield 114	com/lbe/parallel/he:f	Landroid/os/Handler;
    //   1362: aload_0
    //   1363: getfield 88	com/lbe/parallel/he:j	Ljava/lang/Runnable;
    //   1366: invokevirtual 306	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   1369: pop
    //   1370: aload_0
    //   1371: iconst_1
    //   1372: putfield 83	com/lbe/parallel/he:i	Z
    //   1375: goto -1364 -> 11
    //   1378: astore 12
    //   1380: goto -1195 -> 185
    //   1383: goto -416 -> 967
    //   1386: iconst_1
    //   1387: istore_1
    //   1388: goto -779 -> 609
    //   1391: iconst_0
    //   1392: istore_1
    //   1393: goto -925 -> 468
    //   1396: goto -1265 -> 131
    //   1399: iload_1
    //   1400: iconst_1
    //   1401: iadd
    //   1402: istore_1
    //   1403: goto -986 -> 417
    //   1406: iconst_0
    //   1407: istore_1
    //   1408: goto -799 -> 609
    //   1411: astore 4
    //   1413: aconst_null
    //   1414: astore 4
    //   1416: goto -449 -> 967
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1419	0	this	he
    //   416	992	1	k	int
    //   414	6	2	m	int
    //   6	2	3	bool	boolean
    //   119	34	4	localObject1	Object
    //   173	5	4	localObject2	Object
    //   180	1	4	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   183	939	4	localObject3	Object
    //   1411	1	4	localNameNotFoundException2	PackageManager.NameNotFoundException
    //   1414	1	4	localObject4	Object
    //   30	1317	5	localObject5	Object
    //   21	1208	6	localObject6	Object
    //   40	1305	7	localObject7	Object
    //   62	1270	8	localObject8	Object
    //   71	1211	9	localObject9	Object
    //   81	1225	10	localObject10	Object
    //   103	929	11	str	String
    //   143	22	12	localSparseArray	SparseArray
    //   275	68	12	localRuntimeException1	RuntimeException
    //   982	58	12	localRuntimeException2	RuntimeException
    //   1378	1	12	localNameNotFoundException3	PackageManager.NameNotFoundException
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
    //   277	303	173	finally
    //   308	348	173	finally
    //   348	415	173	finally
    //   428	446	173	finally
    //   449	466	173	finally
    //   472	481	173	finally
    //   481	562	173	finally
    //   565	574	173	finally
    //   574	604	173	finally
    //   613	623	173	finally
    //   623	657	173	finally
    //   660	665	173	finally
    //   668	706	173	finally
    //   706	737	173	finally
    //   740	784	173	finally
    //   784	816	173	finally
    //   816	838	173	finally
    //   845	860	173	finally
    //   860	897	173	finally
    //   902	927	173	finally
    //   927	948	173	finally
    //   951	967	173	finally
    //   967	979	173	finally
    //   984	1000	173	finally
    //   1005	1045	173	finally
    //   1045	1055	173	finally
    //   1055	1098	173	finally
    //   1101	1111	173	finally
    //   1111	1143	173	finally
    //   1143	1179	173	finally
    //   1182	1218	173	finally
    //   1218	1271	173	finally
    //   1271	1311	173	finally
    //   1314	1337	173	finally
    //   1340	1355	173	finally
    //   1358	1375	173	finally
    //   105	121	180	android/content/pm/PackageManager$NameNotFoundException
    //   105	121	275	java/lang/RuntimeException
    //   121	131	275	java/lang/RuntimeException
    //   951	967	982	java/lang/RuntimeException
    //   121	131	1378	android/content/pm/PackageManager$NameNotFoundException
    //   951	967	1411	android/content/pm/PackageManager$NameNotFoundException
  }
  
  private static void a(hf paramHf, Set<hf> paramSet)
  {
    for (;;)
    {
      Object localObject = new LinkedList(a);
      ((List)localObject).removeAll(paramHf.d);
      ((List)localObject).remove(paramHf);
      HashSet localHashSet = new HashSet();
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          break label126;
        }
        hf localHf = (hf)((Iterator)localObject).next();
        Iterator localIterator = paramSet.iterator();
        if (localIterator.hasNext())
        {
          if (!((hf)localIterator.next()).d.contains(localHf)) {
            break;
          }
          localHashSet.add(localHf);
          paramHf.d.add(localHf);
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
        localHf = (hf)localIterator.next();
        if (localHf.a(paramSet)) {
          continue;
        }
        localObject1 = null;
        if (localSparseArray.indexOfKey(localHf.a) < 0) {
          break label125;
        }
        localObject1 = localSparseArray.get(localHf.a);
      }
      for (;;)
      {
        if (!localHf.a(localObject1)) {
          break label155;
        }
        paramSet.addAll(localHf.b);
        paramSet.addAll(localHf.c);
        break label32;
        break;
        try
        {
          Object localObject2 = hf.a(this.g, paramString, localHf.a);
          localObject1 = localObject2;
        }
        catch (RuntimeException paramSet)
        {
          throw new PackageManagerDied("packageManagerdie type=" + localHf.a + "  " + paramString, paramSet);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;) {}
        }
        localSparseArray.put(localHf.a, localObject1);
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
    //   3: getfield 83	com/lbe/parallel/he:i	Z
    //   6: ifeq +22 -> 28
    //   9: aload_0
    //   10: aload_1
    //   11: invokevirtual 329	com/lbe/parallel/he:b	(Ljava/lang/String;)[Ljava/lang/String;
    //   14: pop
    //   15: aload_0
    //   16: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   19: aload_1
    //   20: invokevirtual 332	com/lbe/parallel/ao:containsKey	(Ljava/lang/Object;)Z
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
    //   0	38	0	this	he
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
    //   3: getfield 83	com/lbe/parallel/he:i	Z
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
    //   26: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   29: aload_1
    //   30: invokevirtual 332	com/lbe/parallel/ao:containsKey	(Ljava/lang/Object;)Z
    //   33: ifeq +37 -> 70
    //   36: aload_3
    //   37: aload_0
    //   38: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   41: aload_1
    //   42: invokevirtual 333	com/lbe/parallel/ao:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   45: checkcast 335	java/util/Collection
    //   48: invokevirtual 252	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   51: pop
    //   52: aload_3
    //   53: aload_3
    //   54: invokevirtual 193	java/util/HashSet:size	()I
    //   57: anewarray 157	java/lang/String
    //   60: invokevirtual 339	java/util/HashSet:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   63: checkcast 341	[Ljava/lang/String;
    //   66: astore_1
    //   67: goto -54 -> 13
    //   70: aload_0
    //   71: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   74: aload_1
    //   75: invokeinterface 247 2 0
    //   80: ifeq -28 -> 52
    //   83: aload_3
    //   84: aload_0
    //   85: getfield 103	com/lbe/parallel/he:e	Landroid/content/SharedPreferences;
    //   88: aload_1
    //   89: new 41	java/util/HashSet
    //   92: dup
    //   93: invokespecial 42	java/util/HashSet:<init>	()V
    //   96: invokeinterface 251 3 0
    //   101: invokevirtual 252	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   104: pop
    //   105: aload_0
    //   106: getfield 93	com/lbe/parallel/he:d	Lcom/lbe/parallel/ao;
    //   109: aload_1
    //   110: aload_3
    //   111: invokevirtual 253	com/lbe/parallel/ao:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: goto -63 -> 52
    //   118: astore_1
    //   119: aload_0
    //   120: monitorexit
    //   121: aload_1
    //   122: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	he
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
