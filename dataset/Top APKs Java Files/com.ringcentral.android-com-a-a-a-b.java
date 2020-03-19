package com.a.a.a;

import android.content.pm.PermissionInfo;

public class b
{
  public b() {}
  
  private static PermissionInfo a(PermissionInfo[] paramArrayOfPermissionInfo, String paramString)
  {
    int j = paramArrayOfPermissionInfo.length;
    int i = 0;
    while (i < j)
    {
      PermissionInfo localPermissionInfo = paramArrayOfPermissionInfo[i];
      if (paramString.equals(localPermissionInfo.name)) {
        return localPermissionInfo;
      }
      i += 1;
    }
    return null;
  }
  
  /* Error */
  public static java.util.HashMap<android.content.pm.PackageInfo, java.util.ArrayList<a>> a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 30	java/util/HashMap
    //   3: dup
    //   4: invokespecial 31	java/util/HashMap:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: invokevirtual 37	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore 6
    //   15: aload 6
    //   17: aload_0
    //   18: invokevirtual 41	android/content/Context:getPackageName	()Ljava/lang/String;
    //   21: sipush 4096
    //   24: invokevirtual 47	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   27: astore 7
    //   29: aload 6
    //   31: sipush 4096
    //   34: invokevirtual 51	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   37: invokeinterface 57 1 0
    //   42: astore 8
    //   44: aload 8
    //   46: invokeinterface 63 1 0
    //   51: ifeq +297 -> 348
    //   54: aload 8
    //   56: invokeinterface 67 1 0
    //   61: checkcast 69	android/content/pm/PackageInfo
    //   64: astore 9
    //   66: aload 9
    //   68: getfield 72	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   71: aload 7
    //   73: getfield 72	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   76: invokevirtual 23	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   79: ifne -35 -> 44
    //   82: aload 9
    //   84: getfield 76	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   87: ifnull -43 -> 44
    //   90: new 78	java/util/ArrayList
    //   93: dup
    //   94: invokespecial 79	java/util/ArrayList:<init>	()V
    //   97: astore 10
    //   99: aload 9
    //   101: getfield 76	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   104: astore 11
    //   106: aload 11
    //   108: arraylength
    //   109: istore_2
    //   110: iconst_0
    //   111: istore_1
    //   112: iload_1
    //   113: iload_2
    //   114: if_icmpge +213 -> 327
    //   117: aload 11
    //   119: iload_1
    //   120: aaload
    //   121: astore 12
    //   123: aload 7
    //   125: getfield 76	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   128: aload 12
    //   130: getfield 17	android/content/pm/PermissionInfo:name	Ljava/lang/String;
    //   133: invokestatic 81	com/a/a/a/b:a	([Landroid/content/pm/PermissionInfo;Ljava/lang/String;)Landroid/content/pm/PermissionInfo;
    //   136: astore 13
    //   138: aload 13
    //   140: ifnull +236 -> 376
    //   143: new 83	com/a/a/a/a
    //   146: dup
    //   147: aload 12
    //   149: invokespecial 86	com/a/a/a/a:<init>	(Landroid/content/pm/PermissionInfo;)V
    //   152: astore 14
    //   154: aload 12
    //   156: getfield 90	android/content/pm/PermissionInfo:protectionLevel	I
    //   159: bipush 15
    //   161: iand
    //   162: istore_3
    //   163: aload 13
    //   165: getfield 90	android/content/pm/PermissionInfo:protectionLevel	I
    //   168: bipush 15
    //   170: iand
    //   171: istore 4
    //   173: iload_3
    //   174: iload 4
    //   176: if_icmpge +80 -> 256
    //   179: aload 14
    //   181: iconst_1
    //   182: putfield 94	com/a/a/a/a:b	Z
    //   185: goto +166 -> 351
    //   188: aload 14
    //   190: iconst_1
    //   191: putfield 97	com/a/a/a/a:e	Z
    //   194: aload_0
    //   195: invokestatic 102	com/a/a/a/c:a	(Landroid/content/Context;)Ljava/lang/String;
    //   198: aload_0
    //   199: aload 9
    //   201: getfield 72	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   204: invokestatic 105	com/a/a/a/c:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   207: invokevirtual 23	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   210: ifeq +9 -> 219
    //   213: aload 14
    //   215: iconst_0
    //   216: putfield 97	com/a/a/a/a:e	Z
    //   219: aload 12
    //   221: aload 6
    //   223: invokevirtual 109	android/content/pm/PermissionInfo:loadDescription	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   226: aload 13
    //   228: aload 6
    //   230: invokevirtual 109	android/content/pm/PermissionInfo:loadDescription	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   233: invokestatic 114	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   236: ifne +62 -> 298
    //   239: aload 14
    //   241: iconst_1
    //   242: putfield 117	com/a/a/a/a:d	Z
    //   245: aload 10
    //   247: aload 14
    //   249: invokevirtual 120	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   252: pop
    //   253: goto +123 -> 376
    //   256: iload_3
    //   257: iload 4
    //   259: if_icmple +92 -> 351
    //   262: aload 14
    //   264: iconst_1
    //   265: putfield 123	com/a/a/a/a:c	Z
    //   268: goto +83 -> 351
    //   271: astore_0
    //   272: new 125	java/lang/RuntimeException
    //   275: dup
    //   276: ldc 127
    //   278: aload_0
    //   279: invokespecial 130	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   282: athrow
    //   283: astore 15
    //   285: ldc -124
    //   287: ldc -122
    //   289: aload 15
    //   291: invokestatic 139	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   294: pop
    //   295: goto -76 -> 219
    //   298: aload 12
    //   300: aload 6
    //   302: invokevirtual 142	android/content/pm/PermissionInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   305: aload 13
    //   307: aload 6
    //   309: invokevirtual 142	android/content/pm/PermissionInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   312: invokestatic 114	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   315: ifne -70 -> 245
    //   318: aload 14
    //   320: iconst_1
    //   321: putfield 117	com/a/a/a/a:d	Z
    //   324: goto -79 -> 245
    //   327: aload 10
    //   329: invokevirtual 146	java/util/ArrayList:size	()I
    //   332: ifle -288 -> 44
    //   335: aload 5
    //   337: aload 9
    //   339: aload 10
    //   341: invokevirtual 150	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   344: pop
    //   345: goto -301 -> 44
    //   348: aload 5
    //   350: areturn
    //   351: iload_3
    //   352: iconst_2
    //   353: if_icmpeq +8 -> 361
    //   356: iload_3
    //   357: iconst_3
    //   358: if_icmpne -139 -> 219
    //   361: iload 4
    //   363: iconst_2
    //   364: if_icmpeq -176 -> 188
    //   367: iload 4
    //   369: iconst_3
    //   370: if_icmpne -151 -> 219
    //   373: goto -185 -> 188
    //   376: iload_1
    //   377: iconst_1
    //   378: iadd
    //   379: istore_1
    //   380: goto -268 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	383	0	paramContext	android.content.Context
    //   111	269	1	i	int
    //   109	6	2	j	int
    //   162	197	3	k	int
    //   171	200	4	m	int
    //   7	342	5	localHashMap	java.util.HashMap
    //   13	295	6	localPackageManager	android.content.pm.PackageManager
    //   27	97	7	localPackageInfo1	android.content.pm.PackageInfo
    //   42	13	8	localIterator	java.util.Iterator
    //   64	274	9	localPackageInfo2	android.content.pm.PackageInfo
    //   97	243	10	localArrayList	java.util.ArrayList
    //   104	14	11	arrayOfPermissionInfo	PermissionInfo[]
    //   121	178	12	localPermissionInfo1	PermissionInfo
    //   136	170	13	localPermissionInfo2	PermissionInfo
    //   152	167	14	localA	a
    //   283	7	15	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   15	44	271	android/content/pm/PackageManager$NameNotFoundException
    //   44	110	271	android/content/pm/PackageManager$NameNotFoundException
    //   123	138	271	android/content/pm/PackageManager$NameNotFoundException
    //   143	173	271	android/content/pm/PackageManager$NameNotFoundException
    //   179	185	271	android/content/pm/PackageManager$NameNotFoundException
    //   188	194	271	android/content/pm/PackageManager$NameNotFoundException
    //   194	219	271	android/content/pm/PackageManager$NameNotFoundException
    //   219	245	271	android/content/pm/PackageManager$NameNotFoundException
    //   245	253	271	android/content/pm/PackageManager$NameNotFoundException
    //   262	268	271	android/content/pm/PackageManager$NameNotFoundException
    //   285	295	271	android/content/pm/PackageManager$NameNotFoundException
    //   298	324	271	android/content/pm/PackageManager$NameNotFoundException
    //   327	345	271	android/content/pm/PackageManager$NameNotFoundException
    //   194	219	283	java/lang/Exception
  }
}
