package nextapp.fx.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import nextapp.maui.k.c;
import nextapp.maui.k.d;

public class b
{
  private static transient long a = 0L;
  private final PackageManager b;
  private final Context c;
  private final Map<String, String> d = new HashMap();
  
  public b(Context paramContext)
  {
    this.c = paramContext;
    this.b = paramContext.getPackageManager();
  }
  
  public static long a()
  {
    return a;
  }
  
  public static void b()
  {
    a = System.currentTimeMillis();
  }
  
  public String a(String paramString)
  {
    return (String)this.d.get(paramString);
  }
  
  /* Error */
  public List<a> a(AppCatalog paramAppCatalog, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 71	nextapp/fx/app/AppCatalog:f	()Lnextapp/fx/app/AppCatalog$a;
    //   4: astore 10
    //   6: aconst_null
    //   7: astore 8
    //   9: aload_1
    //   10: invokevirtual 75	nextapp/fx/app/AppCatalog:g	()Z
    //   13: ifeq +5 -> 18
    //   16: iconst_0
    //   17: istore_2
    //   18: aload_0
    //   19: getfield 43	nextapp/fx/app/b:b	Landroid/content/pm/PackageManager;
    //   22: astore 7
    //   24: aload_1
    //   25: invokevirtual 75	nextapp/fx/app/AppCatalog:g	()Z
    //   28: ifeq +34 -> 62
    //   31: sipush 4096
    //   34: istore_3
    //   35: aload 7
    //   37: iload_3
    //   38: invokevirtual 81	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   41: astore 7
    //   43: aload 7
    //   45: ifnull +13 -> 58
    //   48: aload 7
    //   50: invokeinterface 87 1 0
    //   55: ifne +12 -> 67
    //   58: invokestatic 93	java/util/Collections:emptyList	()Ljava/util/List;
    //   61: areturn
    //   62: iconst_0
    //   63: istore_3
    //   64: goto -29 -> 35
    //   67: new 95	java/util/ArrayList
    //   70: dup
    //   71: aload 7
    //   73: invokeinterface 87 1 0
    //   78: invokespecial 98	java/util/ArrayList:<init>	(I)V
    //   81: astore 11
    //   83: aload 7
    //   85: invokeinterface 102 1 0
    //   90: astore 12
    //   92: aload 12
    //   94: invokeinterface 107 1 0
    //   99: ifeq +416 -> 515
    //   102: aload 12
    //   104: invokeinterface 111 1 0
    //   109: checkcast 113	android/content/pm/PackageInfo
    //   112: astore 13
    //   114: invokestatic 117	nextapp/maui/k/d:c	()Z
    //   117: ifeq +11 -> 128
    //   120: new 119	nextapp/maui/k/c
    //   123: dup
    //   124: invokespecial 120	nextapp/maui/k/c:<init>	()V
    //   127: athrow
    //   128: aload 13
    //   130: getfield 124	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   133: ifnull -41 -> 92
    //   136: aload 13
    //   138: getfield 124	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   141: getfield 130	android/content/pm/ApplicationInfo:flags	I
    //   144: iconst_1
    //   145: iand
    //   146: ifeq +113 -> 259
    //   149: iconst_1
    //   150: istore_3
    //   151: aload 10
    //   153: getstatic 135	nextapp/fx/app/AppCatalog$a:a	Lnextapp/fx/app/AppCatalog$a;
    //   156: if_acmpne +108 -> 264
    //   159: iload_3
    //   160: ifeq -68 -> 92
    //   163: aload_1
    //   164: invokevirtual 138	nextapp/fx/app/AppCatalog:d	()Ljava/lang/String;
    //   167: ifnull +131 -> 298
    //   170: aload_1
    //   171: invokevirtual 138	nextapp/fx/app/AppCatalog:d	()Ljava/lang/String;
    //   174: astore 7
    //   176: aload 13
    //   178: getfield 142	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   181: ifnull -89 -> 92
    //   184: iconst_0
    //   185: istore 5
    //   187: aload 13
    //   189: getfield 142	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   192: astore 9
    //   194: aload 9
    //   196: arraylength
    //   197: istore 6
    //   199: iconst_0
    //   200: istore_3
    //   201: iload 5
    //   203: istore 4
    //   205: iload_3
    //   206: iload 6
    //   208: if_icmpge +18 -> 226
    //   211: aload 7
    //   213: aload 9
    //   215: iload_3
    //   216: aaload
    //   217: invokevirtual 146	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   220: ifeq +71 -> 291
    //   223: iconst_1
    //   224: istore 4
    //   226: iload 4
    //   228: ifeq -136 -> 92
    //   231: aload 8
    //   233: astore 9
    //   235: aload 11
    //   237: aload_0
    //   238: getfield 43	nextapp/fx/app/b:b	Landroid/content/pm/PackageManager;
    //   241: aload 13
    //   243: invokestatic 151	nextapp/fx/app/a:a	(Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)Lnextapp/fx/app/a;
    //   246: invokeinterface 154 2 0
    //   251: pop
    //   252: aload 9
    //   254: astore 8
    //   256: goto -164 -> 92
    //   259: iconst_0
    //   260: istore_3
    //   261: goto -110 -> 151
    //   264: aload 10
    //   266: getstatic 156	nextapp/fx/app/AppCatalog$a:b	Lnextapp/fx/app/AppCatalog$a;
    //   269: if_acmpne +17 -> 286
    //   272: iload_3
    //   273: ifne +8 -> 281
    //   276: iconst_1
    //   277: istore_3
    //   278: goto -119 -> 159
    //   281: iconst_0
    //   282: istore_3
    //   283: goto -124 -> 159
    //   286: iconst_1
    //   287: istore_3
    //   288: goto -129 -> 159
    //   291: iload_3
    //   292: iconst_1
    //   293: iadd
    //   294: istore_3
    //   295: goto -94 -> 201
    //   298: aload 8
    //   300: astore 9
    //   302: aload_1
    //   303: invokevirtual 159	nextapp/fx/app/AppCatalog:e	()Ljava/lang/String;
    //   306: ifnull -71 -> 235
    //   309: aload 13
    //   311: getfield 142	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   314: ifnull -222 -> 92
    //   317: aload 8
    //   319: astore 7
    //   321: aload 8
    //   323: ifnonnull +14 -> 337
    //   326: new 10	nextapp/fx/app/b$a
    //   329: dup
    //   330: aload_0
    //   331: aconst_null
    //   332: invokespecial 162	nextapp/fx/app/b$a:<init>	(Lnextapp/fx/app/b;Lnextapp/fx/app/b$1;)V
    //   335: astore 7
    //   337: iconst_0
    //   338: istore 5
    //   340: aload 13
    //   342: getfield 142	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   345: astore 8
    //   347: aload 8
    //   349: arraylength
    //   350: istore 6
    //   352: iconst_0
    //   353: istore 4
    //   355: iload 5
    //   357: istore_3
    //   358: iload 4
    //   360: iload 6
    //   362: if_icmpge +27 -> 389
    //   365: aload 8
    //   367: iload 4
    //   369: aaload
    //   370: astore 9
    //   372: aload 7
    //   374: invokestatic 165	nextapp/fx/app/b$a:a	(Lnextapp/fx/app/b$a;)Ljava/util/Set;
    //   377: aload 9
    //   379: invokeinterface 170 2 0
    //   384: ifeq +20 -> 404
    //   387: iconst_1
    //   388: istore_3
    //   389: aload 7
    //   391: astore 9
    //   393: iload_3
    //   394: ifne -159 -> 235
    //   397: aload 7
    //   399: astore 8
    //   401: goto -309 -> 92
    //   404: aload 7
    //   406: invokestatic 172	nextapp/fx/app/b$a:b	(Lnextapp/fx/app/b$a;)Ljava/util/Set;
    //   409: aload 9
    //   411: invokeinterface 170 2 0
    //   416: ifeq +12 -> 428
    //   419: iload 4
    //   421: iconst_1
    //   422: iadd
    //   423: istore 4
    //   425: goto -70 -> 355
    //   428: aload_0
    //   429: getfield 43	nextapp/fx/app/b:b	Landroid/content/pm/PackageManager;
    //   432: aload 9
    //   434: iconst_0
    //   435: invokevirtual 176	android/content/pm/PackageManager:getPermissionInfo	(Ljava/lang/String;I)Landroid/content/pm/PermissionInfo;
    //   438: astore 14
    //   440: aload 14
    //   442: getfield 182	android/content/pm/PermissionInfo:group	Ljava/lang/String;
    //   445: ifnull +36 -> 481
    //   448: aload_1
    //   449: invokevirtual 159	nextapp/fx/app/AppCatalog:e	()Ljava/lang/String;
    //   452: aload 14
    //   454: getfield 182	android/content/pm/PermissionInfo:group	Ljava/lang/String;
    //   457: invokevirtual 146	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   460: ifeq +21 -> 481
    //   463: aload 7
    //   465: invokestatic 165	nextapp/fx/app/b$a:a	(Lnextapp/fx/app/b$a;)Ljava/util/Set;
    //   468: aload 9
    //   470: invokeinterface 183 2 0
    //   475: pop
    //   476: iconst_1
    //   477: istore_3
    //   478: goto -89 -> 389
    //   481: aload 7
    //   483: invokestatic 172	nextapp/fx/app/b$a:b	(Lnextapp/fx/app/b$a;)Ljava/util/Set;
    //   486: aload 9
    //   488: invokeinterface 183 2 0
    //   493: pop
    //   494: goto -75 -> 419
    //   497: astore 14
    //   499: aload 7
    //   501: invokestatic 172	nextapp/fx/app/b$a:b	(Lnextapp/fx/app/b$a;)Ljava/util/Set;
    //   504: aload 9
    //   506: invokeinterface 183 2 0
    //   511: pop
    //   512: goto -93 -> 419
    //   515: iload_2
    //   516: ifeq +415 -> 931
    //   519: aload_0
    //   520: getfield 35	nextapp/fx/app/b:c	Landroid/content/Context;
    //   523: invokestatic 188	nextapp/fx/a:b	(Landroid/content/Context;)Z
    //   526: ifeq +405 -> 931
    //   529: aconst_null
    //   530: astore_1
    //   531: aconst_null
    //   532: astore 7
    //   534: aconst_null
    //   535: astore 10
    //   537: aconst_null
    //   538: astore 9
    //   540: aload_0
    //   541: getfield 35	nextapp/fx/app/b:c	Landroid/content/Context;
    //   544: getstatic 193	nextapp/fx/dir/shell/ShellCatalog:a	Lnextapp/fx/connection/e;
    //   547: invokestatic 198	nextapp/fx/connection/SessionManager:a	(Landroid/content/Context;Lnextapp/fx/connection/e;)Lnextapp/fx/connection/a;
    //   550: checkcast 200	nextapp/fx/dir/shell/d
    //   553: astore 12
    //   555: aload 12
    //   557: invokevirtual 204	nextapp/fx/dir/shell/d:m	()Lnextapp/fx/shell/e;
    //   560: astore 8
    //   562: aload 8
    //   564: ldc -50
    //   566: iconst_1
    //   567: invokestatic 211	nextapp/fx/shell/i:a	(Lnextapp/fx/shell/e;Ljava/lang/String;Z)Ljava/util/Map;
    //   570: astore_1
    //   571: aload_1
    //   572: astore 7
    //   574: aload 8
    //   576: ldc -43
    //   578: invokestatic 216	nextapp/fx/shell/i:b	(Lnextapp/fx/shell/e;Ljava/lang/String;)[Lnextapp/fx/shell/c;
    //   581: astore 8
    //   583: aload 8
    //   585: astore 9
    //   587: aload 9
    //   589: astore 7
    //   591: aload_1
    //   592: astore 8
    //   594: aload 12
    //   596: invokestatic 219	nextapp/fx/connection/SessionManager:a	(Lnextapp/fx/connection/a;)V
    //   599: aload 9
    //   601: astore 7
    //   603: aload_1
    //   604: ifnull +146 -> 750
    //   607: aload 11
    //   609: invokeinterface 102 1 0
    //   614: astore 8
    //   616: aload 8
    //   618: invokeinterface 107 1 0
    //   623: ifeq +127 -> 750
    //   626: aload 8
    //   628: invokeinterface 111 1 0
    //   633: checkcast 148	nextapp/fx/app/a
    //   636: astore 9
    //   638: aload_1
    //   639: aload 9
    //   641: getfield 221	nextapp/fx/app/a:f	Ljava/lang/String;
    //   644: invokeinterface 56 2 0
    //   649: checkcast 223	java/lang/Long
    //   652: astore 10
    //   654: aload 10
    //   656: ifnull -40 -> 616
    //   659: aload 9
    //   661: aload 10
    //   663: invokevirtual 226	java/lang/Long:longValue	()J
    //   666: invokevirtual 229	nextapp/fx/app/a:b	(J)V
    //   669: goto -53 -> 616
    //   672: astore 8
    //   674: aconst_null
    //   675: astore_1
    //   676: aload_1
    //   677: astore 7
    //   679: ldc -25
    //   681: ldc -23
    //   683: aload 8
    //   685: invokestatic 239	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   688: pop
    //   689: aload_1
    //   690: astore 7
    //   692: aload 12
    //   694: invokevirtual 241	nextapp/fx/dir/shell/d:g	()V
    //   697: aload 10
    //   699: astore 7
    //   701: aload_1
    //   702: astore 8
    //   704: aload 12
    //   706: invokestatic 219	nextapp/fx/connection/SessionManager:a	(Lnextapp/fx/connection/a;)V
    //   709: aconst_null
    //   710: astore 7
    //   712: goto -109 -> 603
    //   715: astore 8
    //   717: aload 7
    //   719: astore_1
    //   720: aload 12
    //   722: invokestatic 219	nextapp/fx/connection/SessionManager:a	(Lnextapp/fx/connection/a;)V
    //   725: aload 7
    //   727: astore_1
    //   728: aload 8
    //   730: athrow
    //   731: astore 8
    //   733: aload 9
    //   735: astore 7
    //   737: ldc -25
    //   739: ldc -23
    //   741: aload 8
    //   743: invokestatic 239	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   746: pop
    //   747: goto -144 -> 603
    //   750: aload 7
    //   752: ifnull +179 -> 931
    //   755: new 30	java/util/HashMap
    //   758: dup
    //   759: invokespecial 31	java/util/HashMap:<init>	()V
    //   762: astore 9
    //   764: aload 7
    //   766: arraylength
    //   767: istore 4
    //   769: iconst_0
    //   770: istore_3
    //   771: iload_3
    //   772: iload 4
    //   774: if_icmpge +94 -> 868
    //   777: aload 7
    //   779: iload_3
    //   780: aaload
    //   781: astore 10
    //   783: aload 10
    //   785: getfield 245	nextapp/fx/shell/c:a	Ljava/lang/String;
    //   788: astore 8
    //   790: aload 8
    //   792: astore_1
    //   793: aload 8
    //   795: ldc -9
    //   797: invokevirtual 251	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   800: ifeq +17 -> 817
    //   803: aload 8
    //   805: iconst_0
    //   806: aload 8
    //   808: invokevirtual 254	java/lang/String:length	()I
    //   811: iconst_5
    //   812: isub
    //   813: invokevirtual 258	java/lang/String:substring	(II)Ljava/lang/String;
    //   816: astore_1
    //   817: aload_1
    //   818: bipush 45
    //   820: invokevirtual 262	java/lang/String:lastIndexOf	(I)I
    //   823: istore 5
    //   825: aload_1
    //   826: astore 8
    //   828: iload 5
    //   830: iconst_m1
    //   831: if_icmpeq +12 -> 843
    //   834: aload_1
    //   835: iconst_0
    //   836: iload 5
    //   838: invokevirtual 258	java/lang/String:substring	(II)Ljava/lang/String;
    //   841: astore 8
    //   843: aload 9
    //   845: aload 8
    //   847: aload 10
    //   849: getfield 264	nextapp/fx/shell/c:b	J
    //   852: invokestatic 268	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   855: invokeinterface 272 3 0
    //   860: pop
    //   861: iload_3
    //   862: iconst_1
    //   863: iadd
    //   864: istore_3
    //   865: goto -94 -> 771
    //   868: aload 11
    //   870: invokeinterface 102 1 0
    //   875: astore_1
    //   876: aload_1
    //   877: invokeinterface 107 1 0
    //   882: ifeq +49 -> 931
    //   885: aload_1
    //   886: invokeinterface 111 1 0
    //   891: checkcast 148	nextapp/fx/app/a
    //   894: astore 7
    //   896: aload 9
    //   898: aload 7
    //   900: getfield 221	nextapp/fx/app/a:f	Ljava/lang/String;
    //   903: invokeinterface 56 2 0
    //   908: checkcast 223	java/lang/Long
    //   911: astore 8
    //   913: aload 8
    //   915: ifnull -39 -> 876
    //   918: aload 7
    //   920: aload 8
    //   922: invokevirtual 226	java/lang/Long:longValue	()J
    //   925: invokevirtual 274	nextapp/fx/app/a:a	(J)V
    //   928: goto -52 -> 876
    //   931: aload 11
    //   933: areturn
    //   934: astore 9
    //   936: aload 8
    //   938: astore_1
    //   939: aload 9
    //   941: astore 8
    //   943: goto -206 -> 737
    //   946: astore 8
    //   948: goto -231 -> 717
    //   951: astore 8
    //   953: goto -277 -> 676
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	956	0	this	b
    //   0	956	1	paramAppCatalog	AppCatalog
    //   0	956	2	paramBoolean	boolean
    //   34	831	3	i	int
    //   203	572	4	j	int
    //   185	652	5	k	int
    //   197	166	6	m	int
    //   22	897	7	localObject1	Object
    //   7	620	8	localObject2	Object
    //   672	12	8	localJ1	nextapp.fx.shell.j
    //   702	1	8	localAppCatalog	AppCatalog
    //   715	14	8	localObject3	Object
    //   731	11	8	localS1	nextapp.fx.s
    //   788	154	8	localObject4	Object
    //   946	1	8	localObject5	Object
    //   951	1	8	localJ2	nextapp.fx.shell.j
    //   192	705	9	localObject6	Object
    //   934	6	9	localS2	nextapp.fx.s
    //   4	844	10	localObject7	Object
    //   81	851	11	localArrayList	ArrayList
    //   90	631	12	localObject8	Object
    //   112	229	13	localPackageInfo	PackageInfo
    //   438	15	14	localPermissionInfo	android.content.pm.PermissionInfo
    //   497	1	14	localNameNotFoundException	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   428	476	497	android/content/pm/PackageManager$NameNotFoundException
    //   481	494	497	android/content/pm/PackageManager$NameNotFoundException
    //   555	571	672	nextapp/fx/shell/j
    //   555	571	715	finally
    //   540	555	731	nextapp/fx/s
    //   720	725	731	nextapp/fx/s
    //   728	731	731	nextapp/fx/s
    //   594	599	934	nextapp/fx/s
    //   704	709	934	nextapp/fx/s
    //   574	583	946	finally
    //   679	689	946	finally
    //   692	697	946	finally
    //   574	583	951	nextapp/fx/shell/j
  }
  
  public Iterator<String> c()
  {
    TreeSet localTreeSet = new TreeSet(new Comparator()
    {
      public int a(Map.Entry<String, String> paramAnonymousEntry1, Map.Entry<String, String> paramAnonymousEntry2)
      {
        if (paramAnonymousEntry1 == paramAnonymousEntry2) {
          return 0;
        }
        if ((paramAnonymousEntry1.getValue() != null) && (paramAnonymousEntry2.getValue() != null)) {
          return ((String)paramAnonymousEntry1.getValue()).compareTo((String)paramAnonymousEntry2.getValue());
        }
        if (paramAnonymousEntry1.getValue() == null) {
          return -1;
        }
        if (paramAnonymousEntry2.getValue() == null) {
          return 1;
        }
        return ((String)paramAnonymousEntry1.getKey()).compareTo((String)paramAnonymousEntry2.getKey());
      }
    });
    localTreeSet.addAll(this.d.entrySet());
    new Iterator()
    {
      public String a()
      {
        Map.Entry localEntry = (Map.Entry)this.a.next();
        if (localEntry == null) {
          return null;
        }
        return (String)localEntry.getKey();
      }
      
      public boolean hasNext()
      {
        return this.a.hasNext();
      }
      
      public void remove() {}
    };
  }
  
  public List<g> d()
  {
    if (d.c()) {
      throw new c();
    }
    HashMap localHashMap = new HashMap();
    Object localObject1 = this.b.getInstalledPackages(4096);
    if ((localObject1 == null) || (((List)localObject1).size() == 0)) {
      return Collections.emptyList();
    }
    Iterator localIterator = ((List)localObject1).iterator();
    PackageInfo localPackageInfo;
    int i;
    label113:
    Object localObject2;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localPackageInfo = (PackageInfo)localIterator.next();
        if (d.c()) {
          throw new c();
        }
        String[] arrayOfString = localPackageInfo.requestedPermissions;
        if (arrayOfString != null)
        {
          int j = arrayOfString.length;
          i = 0;
          if (i < j)
          {
            localObject1 = arrayOfString[i];
            localObject2 = (g)localHashMap.get(localObject1);
            if (localObject2 == null) {
              break label155;
            }
            ((g)localObject2).a(localPackageInfo);
          }
        }
      }
    }
    for (;;)
    {
      i += 1;
      break label113;
      break;
      try
      {
        label155:
        localObject2 = new g(this.b, (String)localObject1);
        ((g)localObject2).a(localPackageInfo);
        localHashMap.put(localObject1, localObject2);
        localObject2 = ((g)localObject2).a();
        if (!this.d.containsKey(localObject2))
        {
          localObject1 = this.b.getPermissionGroupInfo((String)localObject2, 0);
          if (localObject1 == null)
          {
            this.d.put(localObject2, null);
          }
          else
          {
            localObject1 = ((PermissionGroupInfo)localObject1).loadLabel(this.b);
            Map localMap = this.d;
            if (localObject1 == null) {}
            for (localObject1 = null;; localObject1 = ((CharSequence)localObject1).toString())
            {
              localMap.put(localObject2, localObject1);
              break;
            }
            return new ArrayList(localHashMap.values());
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  private class a
  {
    private final Set<String> b = new HashSet();
    private final Set<String> c = new HashSet();
    
    private a() {}
  }
}
