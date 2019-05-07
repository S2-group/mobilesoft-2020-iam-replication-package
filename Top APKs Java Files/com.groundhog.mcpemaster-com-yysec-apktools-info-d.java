package com.yysec.apktools.info;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

public enum d
{
  static String c = "YYLOGCAT";
  public long A;
  public String b = "1.4.2e";
  public int d;
  public int e;
  public String f;
  public String g;
  public String h;
  public String i;
  public String j;
  public long k;
  public long l;
  public String m;
  public String n;
  public String o;
  public String p;
  public String q;
  public String r;
  public String s;
  public String t;
  public String u;
  public String v;
  public String w;
  public String x;
  public String y;
  public int z;
  
  public static String a()
  {
    return "142e";
  }
  
  public static String a(String paramString)
  {
    try
    {
      String str = Base64.encodeToString(b.a(e.a(paramString, "gbk")), 2);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  private String c()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("eqtype", this.d);
      localJSONObject.put("emulatorfeature", this.e);
      localJSONObject.put("emulatormsg", this.f);
      localJSONObject.put("eqmodel", this.g);
      localJSONObject.put("eqscreen", this.h);
      localJSONObject.put("eqosver", this.i);
      localJSONObject.put("eqcpu", this.j);
      localJSONObject.put("eqmem", this.k);
      localJSONObject.put("appname", this.n);
      localJSONObject.put("appver", this.o);
      localJSONObject.put("secsdkver", this.b);
      localJSONObject.put("network", this.p);
      localJSONObject.put("networktype", this.q);
      localJSONObject.put("ipadd", this.r);
      localJSONObject.put("mac", this.s);
      localJSONObject.put("imei", this.t);
      localJSONObject.put("currtime", System.currentTimeMillis());
      localJSONObject.put("runapplis", this.v);
      localJSONObject.put("apps", this.w);
      localJSONObject.put("currmem", this.l);
      localJSONObject.put("currservice", this.m);
      localJSONObject.put("errormsg", this.y);
      localJSONObject.put("time", this.A);
      localJSONObject.put("exceptionmsg", this.x);
      localJSONObject.put("errcode", this.z);
      return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  /* Error */
  public String a(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: putfield 200	com/yysec/apktools/info/d:x	Ljava/lang/String;
    //   5: aload_0
    //   6: aload_2
    //   7: putfield 192	com/yysec/apktools/info/d:y	Ljava/lang/String;
    //   10: aload_0
    //   11: aload_3
    //   12: invokestatic 228	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   15: putfield 204	com/yysec/apktools/info/d:z	I
    //   18: aload_0
    //   19: lload 4
    //   21: putfield 196	com/yysec/apktools/info/d:A	J
    //   24: new 230	java/lang/StringBuffer
    //   27: dup
    //   28: invokespecial 231	java/lang/StringBuffer:<init>	()V
    //   31: astore_1
    //   32: iconst_2
    //   33: newarray long
    //   35: astore_2
    //   36: new 233	android/os/StatFs
    //   39: dup
    //   40: invokestatic 239	android/os/Environment:getDataDirectory	()Ljava/io/File;
    //   43: invokevirtual 244	java/io/File:getPath	()Ljava/lang/String;
    //   46: invokespecial 247	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   49: astore_3
    //   50: aload_3
    //   51: invokevirtual 251	android/os/StatFs:getBlockSize	()I
    //   54: i2l
    //   55: lstore 4
    //   57: aload_2
    //   58: iconst_0
    //   59: aload_3
    //   60: invokevirtual 254	android/os/StatFs:getBlockCount	()I
    //   63: i2l
    //   64: lload 4
    //   66: lmul
    //   67: lastore
    //   68: new 233	android/os/StatFs
    //   71: dup
    //   72: invokestatic 239	android/os/Environment:getDataDirectory	()Ljava/io/File;
    //   75: invokevirtual 244	java/io/File:getPath	()Ljava/lang/String;
    //   78: invokespecial 247	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   81: astore_3
    //   82: aload_3
    //   83: invokevirtual 251	android/os/StatFs:getBlockSize	()I
    //   86: i2l
    //   87: lstore 4
    //   89: aload_2
    //   90: iconst_1
    //   91: aload_3
    //   92: invokevirtual 257	android/os/StatFs:getAvailableBlocks	()I
    //   95: i2l
    //   96: lload 4
    //   98: lmul
    //   99: lastore
    //   100: aload_0
    //   101: aload_2
    //   102: iconst_1
    //   103: laload
    //   104: putfield 184	com/yysec/apktools/info/d:l	J
    //   107: aload_1
    //   108: ldc_w 259
    //   111: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   114: pop
    //   115: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   118: ldc_w 270
    //   121: invokevirtual 276	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   124: checkcast 278	android/app/ActivityManager
    //   127: bipush 30
    //   129: invokevirtual 282	android/app/ActivityManager:getRunningServices	(I)Ljava/util/List;
    //   132: astore_3
    //   133: new 230	java/lang/StringBuffer
    //   136: dup
    //   137: invokespecial 231	java/lang/StringBuffer:<init>	()V
    //   140: astore_2
    //   141: aload_3
    //   142: invokeinterface 288 1 0
    //   147: astore_3
    //   148: aload_3
    //   149: invokeinterface 294 1 0
    //   154: ifne +162 -> 316
    //   157: aload_0
    //   158: aload_2
    //   159: invokevirtual 295	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   162: putfield 188	com/yysec/apktools/info/d:m	Ljava/lang/String;
    //   165: aload_1
    //   166: ldc_w 297
    //   169: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   172: pop
    //   173: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   176: invokevirtual 301	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   179: astore_2
    //   180: aload_2
    //   181: iconst_0
    //   182: invokevirtual 306	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   185: astore 8
    //   187: new 308	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 309	java/lang/StringBuilder:<init>	()V
    //   194: astore_3
    //   195: aload 8
    //   197: invokeinterface 288 1 0
    //   202: astore 8
    //   204: aload 8
    //   206: invokeinterface 294 1 0
    //   211: ifne +193 -> 404
    //   214: aload_0
    //   215: aload_3
    //   216: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: putfield 180	com/yysec/apktools/info/d:w	Ljava/lang/String;
    //   222: aload_1
    //   223: ldc_w 312
    //   226: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   229: pop
    //   230: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   233: ldc_w 270
    //   236: invokevirtual 276	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   239: checkcast 278	android/app/ActivityManager
    //   242: invokevirtual 316	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   245: astore_3
    //   246: new 230	java/lang/StringBuffer
    //   249: dup
    //   250: invokespecial 231	java/lang/StringBuffer:<init>	()V
    //   253: astore_2
    //   254: aload_3
    //   255: invokeinterface 288 1 0
    //   260: astore_3
    //   261: aload_3
    //   262: invokeinterface 294 1 0
    //   267: ifne +248 -> 515
    //   270: aload_0
    //   271: aload_2
    //   272: invokevirtual 295	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   275: putfield 176	com/yysec/apktools/info/d:v	Ljava/lang/String;
    //   278: aload_1
    //   279: ldc_w 318
    //   282: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   285: pop
    //   286: aload_0
    //   287: invokespecial 320	com/yysec/apktools/info/d:c	()Ljava/lang/String;
    //   290: areturn
    //   291: astore_3
    //   292: aload_2
    //   293: iconst_0
    //   294: ldc2_w 321
    //   297: lastore
    //   298: goto -230 -> 68
    //   301: astore_2
    //   302: aload_0
    //   303: ldc2_w 321
    //   306: putfield 184	com/yysec/apktools/info/d:l	J
    //   309: goto -202 -> 107
    //   312: astore_1
    //   313: goto -27 -> 286
    //   316: aload_3
    //   317: invokeinterface 326 1 0
    //   322: checkcast 328	android/app/ActivityManager$RunningServiceInfo
    //   325: astore 8
    //   327: aload 8
    //   329: getfield 332	android/app/ActivityManager$RunningServiceInfo:service	Landroid/content/ComponentName;
    //   332: invokevirtual 337	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   335: invokestatic 340	com/yysec/apktools/info/AppInfosUtils:a	(Ljava/lang/String;)Z
    //   338: ifne -190 -> 148
    //   341: aload_2
    //   342: new 308	java/lang/StringBuilder
    //   345: dup
    //   346: aload 8
    //   348: getfield 332	android/app/ActivityManager$RunningServiceInfo:service	Landroid/content/ComponentName;
    //   351: invokevirtual 337	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   354: invokestatic 345	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   357: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   360: ldc_w 348
    //   363: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: aload 8
    //   368: getfield 332	android/app/ActivityManager$RunningServiceInfo:service	Landroid/content/ComponentName;
    //   371: invokevirtual 354	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   374: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: ldc_w 356
    //   380: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   386: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   389: pop
    //   390: goto -242 -> 148
    //   393: astore_2
    //   394: aload_0
    //   395: ldc_w 358
    //   398: putfield 188	com/yysec/apktools/info/d:m	Ljava/lang/String;
    //   401: goto -236 -> 165
    //   404: aload 8
    //   406: invokeinterface 326 1 0
    //   411: checkcast 360	android/content/pm/PackageInfo
    //   414: astore 9
    //   416: aload 9
    //   418: getfield 363	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   421: invokestatic 340	com/yysec/apktools/info/AppInfosUtils:a	(Ljava/lang/String;)Z
    //   424: ifne -220 -> 204
    //   427: aload_3
    //   428: aload 9
    //   430: getfield 363	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   433: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: pop
    //   437: aload_3
    //   438: ldc_w 365
    //   441: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: pop
    //   445: aload_3
    //   446: aload 9
    //   448: getfield 368	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   451: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: pop
    //   455: aload_3
    //   456: ldc_w 370
    //   459: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   462: pop
    //   463: aload_3
    //   464: ldc_w 365
    //   467: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: pop
    //   471: aload_3
    //   472: aload 9
    //   474: getfield 374	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   477: aload_2
    //   478: invokevirtual 380	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   481: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
    //   484: pop
    //   485: aload_3
    //   486: ldc_w 370
    //   489: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: pop
    //   493: aload_3
    //   494: ldc_w 385
    //   497: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: pop
    //   501: goto -297 -> 204
    //   504: astore_2
    //   505: aload_0
    //   506: ldc_w 358
    //   509: putfield 180	com/yysec/apktools/info/d:w	Ljava/lang/String;
    //   512: goto -290 -> 222
    //   515: aload_3
    //   516: invokeinterface 326 1 0
    //   521: checkcast 387	android/app/ActivityManager$RunningAppProcessInfo
    //   524: astore 8
    //   526: aload 8
    //   528: getfield 390	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   531: invokestatic 340	com/yysec/apktools/info/AppInfosUtils:a	(Ljava/lang/String;)Z
    //   534: ifne -273 -> 261
    //   537: aload_2
    //   538: new 308	java/lang/StringBuilder
    //   541: dup
    //   542: ldc_w 392
    //   545: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   548: aload 8
    //   550: getfield 390	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   553: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   556: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   559: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   562: pop
    //   563: aload_2
    //   564: ldc_w 394
    //   567: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   570: pop
    //   571: aload 8
    //   573: getfield 398	android/app/ActivityManager$RunningAppProcessInfo:pkgList	[Ljava/lang/String;
    //   576: astore 8
    //   578: aload 8
    //   580: arraylength
    //   581: istore 7
    //   583: iconst_0
    //   584: istore 6
    //   586: iload 6
    //   588: iload 7
    //   590: if_icmplt +25 -> 615
    //   593: aload_2
    //   594: ldc_w 400
    //   597: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   600: pop
    //   601: goto -340 -> 261
    //   604: astore_2
    //   605: aload_0
    //   606: ldc_w 358
    //   609: putfield 176	com/yysec/apktools/info/d:v	Ljava/lang/String;
    //   612: goto -334 -> 278
    //   615: aload_2
    //   616: new 308	java/lang/StringBuilder
    //   619: dup
    //   620: aload 8
    //   622: iload 6
    //   624: aaload
    //   625: invokestatic 345	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   628: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   631: ldc_w 402
    //   634: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   640: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   643: pop
    //   644: iload 6
    //   646: iconst_1
    //   647: iadd
    //   648: istore 6
    //   650: goto -64 -> 586
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	653	0	this	d
    //   0	653	1	paramString1	String
    //   0	653	2	paramString2	String
    //   0	653	3	paramString3	String
    //   0	653	4	paramLong	long
    //   584	65	6	i1	int
    //   581	10	7	i2	int
    //   185	436	8	localObject	Object
    //   414	59	9	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   36	68	291	java/lang/Exception
    //   32	36	301	java/lang/Exception
    //   68	107	301	java/lang/Exception
    //   107	115	312	java/lang/Exception
    //   115	148	312	java/lang/Exception
    //   148	165	312	java/lang/Exception
    //   165	173	312	java/lang/Exception
    //   222	230	312	java/lang/Exception
    //   278	286	312	java/lang/Exception
    //   302	309	312	java/lang/Exception
    //   316	390	312	java/lang/Exception
    //   394	401	312	java/lang/Exception
    //   505	512	312	java/lang/Exception
    //   605	612	312	java/lang/Exception
    //   115	148	393	java/lang/SecurityException
    //   148	165	393	java/lang/SecurityException
    //   316	390	393	java/lang/SecurityException
    //   173	204	504	java/lang/Exception
    //   204	222	504	java/lang/Exception
    //   404	501	504	java/lang/Exception
    //   230	261	604	java/lang/Exception
    //   261	278	604	java/lang/Exception
    //   515	583	604	java/lang/Exception
    //   593	601	604	java/lang/Exception
    //   615	644	604	java/lang/Exception
  }
  
  /* Error */
  public void b()
  {
    // Byte code:
    //   0: new 230	java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial 231	java/lang/StringBuffer:<init>	()V
    //   7: astore 9
    //   9: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   12: ldc_w 406
    //   15: invokevirtual 276	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   18: checkcast 408	android/view/WindowManager
    //   21: astore 7
    //   23: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   26: invokevirtual 301	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   29: astore 8
    //   31: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   34: ldc_w 410
    //   37: invokevirtual 276	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   40: checkcast 412	android/telephony/TelephonyManager
    //   43: astore 10
    //   45: aload 7
    //   47: invokeinterface 416 1 0
    //   52: astore 11
    //   54: aload 11
    //   56: invokevirtual 421	android/view/Display:getWidth	()I
    //   59: i2f
    //   60: fstore_3
    //   61: aload 11
    //   63: invokevirtual 424	android/view/Display:getHeight	()I
    //   66: i2f
    //   67: fstore_3
    //   68: new 426	android/util/DisplayMetrics
    //   71: dup
    //   72: invokespecial 427	android/util/DisplayMetrics:<init>	()V
    //   75: astore 12
    //   77: aload 11
    //   79: aload 12
    //   81: invokevirtual 431	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   84: aload 12
    //   86: getfield 434	android/util/DisplayMetrics:widthPixels	I
    //   89: i2f
    //   90: aload 12
    //   92: getfield 438	android/util/DisplayMetrics:xdpi	F
    //   95: fdiv
    //   96: f2d
    //   97: ldc2_w 439
    //   100: invokestatic 446	java/lang/Math:pow	(DD)D
    //   103: dstore_1
    //   104: aload 12
    //   106: getfield 449	android/util/DisplayMetrics:heightPixels	I
    //   109: i2f
    //   110: aload 12
    //   112: getfield 452	android/util/DisplayMetrics:ydpi	F
    //   115: fdiv
    //   116: f2d
    //   117: ldc2_w 439
    //   120: invokestatic 446	java/lang/Math:pow	(DD)D
    //   123: dload_1
    //   124: dadd
    //   125: invokestatic 456	java/lang/Math:sqrt	(D)D
    //   128: ldc2_w 457
    //   131: dcmpl
    //   132: iflt +3 -> 135
    //   135: aload_0
    //   136: iconst_0
    //   137: putfield 96	com/yysec/apktools/info/d:d	I
    //   140: aload 9
    //   142: ldc_w 460
    //   145: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   148: pop
    //   149: invokestatic 463	com/yysec/apktools/info/AppInfosUtils:a	()Ljava/util/HashMap;
    //   152: astore 11
    //   154: aload_0
    //   155: aload 11
    //   157: ldc_w 465
    //   160: invokevirtual 471	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   163: checkcast 224	java/lang/Integer
    //   166: invokevirtual 474	java/lang/Integer:intValue	()I
    //   169: putfield 104	com/yysec/apktools/info/d:e	I
    //   172: aload_0
    //   173: aload 11
    //   175: ldc_w 476
    //   178: invokevirtual 471	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   181: checkcast 342	java/lang/String
    //   184: putfield 108	com/yysec/apktools/info/d:f	Ljava/lang/String;
    //   187: aload 9
    //   189: ldc_w 478
    //   192: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   195: pop
    //   196: aload_0
    //   197: getstatic 483	android/os/Build:MODEL	Ljava/lang/String;
    //   200: putfield 115	com/yysec/apktools/info/d:g	Ljava/lang/String;
    //   203: aload 9
    //   205: ldc_w 485
    //   208: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   211: pop
    //   212: aload 7
    //   214: invokeinterface 416 1 0
    //   219: astore 7
    //   221: aload 7
    //   223: invokevirtual 421	android/view/Display:getWidth	()I
    //   226: i2f
    //   227: fstore_3
    //   228: aload 7
    //   230: invokevirtual 424	android/view/Display:getHeight	()I
    //   233: i2f
    //   234: fstore 4
    //   236: new 426	android/util/DisplayMetrics
    //   239: dup
    //   240: invokespecial 427	android/util/DisplayMetrics:<init>	()V
    //   243: astore 11
    //   245: aload 7
    //   247: aload 11
    //   249: invokevirtual 431	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   252: aload 11
    //   254: getfield 434	android/util/DisplayMetrics:widthPixels	I
    //   257: i2f
    //   258: aload 11
    //   260: getfield 438	android/util/DisplayMetrics:xdpi	F
    //   263: fdiv
    //   264: f2d
    //   265: ldc2_w 439
    //   268: invokestatic 446	java/lang/Math:pow	(DD)D
    //   271: dstore_1
    //   272: aload 11
    //   274: getfield 449	android/util/DisplayMetrics:heightPixels	I
    //   277: i2f
    //   278: aload 11
    //   280: getfield 452	android/util/DisplayMetrics:ydpi	F
    //   283: fdiv
    //   284: f2d
    //   285: ldc2_w 439
    //   288: invokestatic 446	java/lang/Math:pow	(DD)D
    //   291: dload_1
    //   292: dadd
    //   293: invokestatic 456	java/lang/Math:sqrt	(D)D
    //   296: dstore_1
    //   297: aload_0
    //   298: new 308	java/lang/StringBuilder
    //   301: dup
    //   302: fload_3
    //   303: invokestatic 488	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   306: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   309: ldc_w 490
    //   312: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: fload 4
    //   317: invokevirtual 493	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   320: ldc_w 490
    //   323: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: ldc_w 495
    //   329: iconst_1
    //   330: anewarray 497	java/lang/Object
    //   333: dup
    //   334: iconst_0
    //   335: dload_1
    //   336: invokestatic 502	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   339: aastore
    //   340: invokestatic 506	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   343: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: putfield 119	com/yysec/apktools/info/d:h	Ljava/lang/String;
    //   352: aload 9
    //   354: ldc_w 508
    //   357: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   360: pop
    //   361: aload_0
    //   362: getstatic 513	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   365: putfield 123	com/yysec/apktools/info/d:i	Ljava/lang/String;
    //   368: aload 9
    //   370: ldc_w 515
    //   373: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   376: pop
    //   377: aload_0
    //   378: new 517	java/io/BufferedReader
    //   381: dup
    //   382: new 519	java/io/FileReader
    //   385: dup
    //   386: ldc_w 521
    //   389: invokespecial 522	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   392: invokespecial 525	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   395: invokevirtual 528	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   398: ldc_w 530
    //   401: iconst_2
    //   402: invokevirtual 534	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   405: iconst_1
    //   406: aaload
    //   407: putfield 127	com/yysec/apktools/info/d:j	Ljava/lang/String;
    //   410: aload 9
    //   412: ldc_w 536
    //   415: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   418: pop
    //   419: new 233	android/os/StatFs
    //   422: dup
    //   423: invokestatic 239	android/os/Environment:getDataDirectory	()Ljava/io/File;
    //   426: invokevirtual 244	java/io/File:getPath	()Ljava/lang/String;
    //   429: invokespecial 247	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   432: astore 7
    //   434: aload_0
    //   435: aload 7
    //   437: invokevirtual 251	android/os/StatFs:getBlockSize	()I
    //   440: i2l
    //   441: aload 7
    //   443: invokevirtual 254	android/os/StatFs:getBlockCount	()I
    //   446: i2l
    //   447: lmul
    //   448: putfield 131	com/yysec/apktools/info/d:k	J
    //   451: aload 9
    //   453: ldc_w 538
    //   456: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   459: pop
    //   460: ldc_w 540
    //   463: astore 7
    //   465: aload 8
    //   467: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   470: invokevirtual 541	android/content/Context:getPackageName	()Ljava/lang/String;
    //   473: iconst_0
    //   474: invokevirtual 545	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   477: astore 8
    //   479: new 308	java/lang/StringBuilder
    //   482: dup
    //   483: aload 8
    //   485: getfield 363	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   488: invokestatic 345	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   491: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   494: ldc_w 365
    //   497: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: aload 8
    //   502: getfield 368	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   505: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   508: ldc_w 370
    //   511: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   517: astore 8
    //   519: aload 8
    //   521: astore 7
    //   523: ldc_w 358
    //   526: aload 7
    //   528: invokevirtual 549	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   531: ifeq +275 -> 806
    //   534: aload_0
    //   535: ldc_w 358
    //   538: putfield 138	com/yysec/apktools/info/d:n	Ljava/lang/String;
    //   541: aload_0
    //   542: ldc_w 358
    //   545: putfield 142	com/yysec/apktools/info/d:o	Ljava/lang/String;
    //   548: aload 9
    //   550: ldc_w 551
    //   553: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   556: pop
    //   557: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   560: ldc_w 553
    //   563: invokevirtual 276	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   566: checkcast 555	android/net/ConnectivityManager
    //   569: invokevirtual 559	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   572: astore 7
    //   574: aload 7
    //   576: ifnonnull +297 -> 873
    //   579: aload_0
    //   580: ldc_w 561
    //   583: putfield 148	com/yysec/apktools/info/d:p	Ljava/lang/String;
    //   586: aload 9
    //   588: ldc_w 563
    //   591: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   594: pop
    //   595: aload 10
    //   597: invokevirtual 566	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   600: astore 7
    //   602: aload 7
    //   604: ldc_w 568
    //   607: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   610: ifne +14 -> 624
    //   613: aload 7
    //   615: ldc_w 573
    //   618: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   621: ifeq +343 -> 964
    //   624: aload_0
    //   625: ldc_w 575
    //   628: putfield 152	com/yysec/apktools/info/d:q	Ljava/lang/String;
    //   631: aload 9
    //   633: ldc_w 577
    //   636: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   639: pop
    //   640: invokestatic 583	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   643: astore 7
    //   645: aload 7
    //   647: invokeinterface 588 1 0
    //   652: istore 6
    //   654: iload 6
    //   656: ifne +416 -> 1072
    //   659: aload 9
    //   661: ldc_w 590
    //   664: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   667: pop
    //   668: aload_0
    //   669: getstatic 268	com/yysec/apktools/info/AppInfosUtils:c	Landroid/content/Context;
    //   672: ldc_w 592
    //   675: invokevirtual 276	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   678: checkcast 594	android/net/wifi/WifiManager
    //   681: invokevirtual 598	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   684: invokevirtual 603	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   687: putfield 160	com/yysec/apktools/info/d:s	Ljava/lang/String;
    //   690: aload 9
    //   692: ldc_w 605
    //   695: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   698: pop
    //   699: aload_0
    //   700: invokestatic 608	com/yysec/apktools/info/AppInfosUtils:getImei	()Ljava/lang/String;
    //   703: putfield 164	com/yysec/apktools/info/d:t	Ljava/lang/String;
    //   706: aload 9
    //   708: ldc_w 610
    //   711: invokevirtual 263	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   714: pop
    //   715: return
    //   716: astore 11
    //   718: aload_0
    //   719: iconst_m1
    //   720: putfield 96	com/yysec/apktools/info/d:d	I
    //   723: goto -583 -> 140
    //   726: astore 11
    //   728: aload_0
    //   729: ldc_w 358
    //   732: putfield 115	com/yysec/apktools/info/d:g	Ljava/lang/String;
    //   735: goto -532 -> 203
    //   738: astore 7
    //   740: aload_0
    //   741: ldc_w 358
    //   744: putfield 119	com/yysec/apktools/info/d:h	Ljava/lang/String;
    //   747: goto -395 -> 352
    //   750: astore 7
    //   752: aload_0
    //   753: ldc_w 358
    //   756: putfield 123	com/yysec/apktools/info/d:i	Ljava/lang/String;
    //   759: goto -391 -> 368
    //   762: astore 7
    //   764: aload_0
    //   765: ldc_w 358
    //   768: putfield 127	com/yysec/apktools/info/d:j	Ljava/lang/String;
    //   771: goto -361 -> 410
    //   774: astore 7
    //   776: aload_0
    //   777: ldc2_w 321
    //   780: putfield 131	com/yysec/apktools/info/d:k	J
    //   783: goto -332 -> 451
    //   786: astore 8
    //   788: aload 8
    //   790: invokevirtual 611	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   793: goto -270 -> 523
    //   796: astore 7
    //   798: ldc_w 358
    //   801: astore 7
    //   803: goto -280 -> 523
    //   806: aload 7
    //   808: ldc_w 365
    //   811: invokevirtual 614	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   814: istore 5
    //   816: iload 5
    //   818: ifge +20 -> 838
    //   821: aload_0
    //   822: ldc_w 358
    //   825: putfield 138	com/yysec/apktools/info/d:n	Ljava/lang/String;
    //   828: aload_0
    //   829: ldc_w 358
    //   832: putfield 142	com/yysec/apktools/info/d:o	Ljava/lang/String;
    //   835: goto -287 -> 548
    //   838: aload_0
    //   839: aload 7
    //   841: iconst_0
    //   842: iload 5
    //   844: invokevirtual 618	java/lang/String:substring	(II)Ljava/lang/String;
    //   847: putfield 138	com/yysec/apktools/info/d:n	Ljava/lang/String;
    //   850: aload_0
    //   851: aload 7
    //   853: iload 5
    //   855: iconst_1
    //   856: iadd
    //   857: aload 7
    //   859: invokevirtual 621	java/lang/String:length	()I
    //   862: iconst_1
    //   863: isub
    //   864: invokevirtual 618	java/lang/String:substring	(II)Ljava/lang/String;
    //   867: putfield 142	com/yysec/apktools/info/d:o	Ljava/lang/String;
    //   870: goto -322 -> 548
    //   873: aload 7
    //   875: invokevirtual 626	android/net/NetworkInfo:getType	()I
    //   878: iconst_1
    //   879: if_icmpne +25 -> 904
    //   882: aload_0
    //   883: ldc_w 592
    //   886: putfield 148	com/yysec/apktools/info/d:p	Ljava/lang/String;
    //   889: goto -303 -> 586
    //   892: astore 7
    //   894: aload_0
    //   895: ldc_w 358
    //   898: putfield 148	com/yysec/apktools/info/d:p	Ljava/lang/String;
    //   901: goto -315 -> 586
    //   904: aload 7
    //   906: invokevirtual 626	android/net/NetworkInfo:getType	()I
    //   909: ifne +45 -> 954
    //   912: aload_0
    //   913: new 308	java/lang/StringBuilder
    //   916: dup
    //   917: ldc_w 628
    //   920: invokespecial 346	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   923: aload 7
    //   925: invokevirtual 631	android/net/NetworkInfo:getSubtypeName	()Ljava/lang/String;
    //   928: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   931: ldc_w 385
    //   934: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   937: aload 7
    //   939: invokevirtual 634	android/net/NetworkInfo:getSubtype	()I
    //   942: invokevirtual 637	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   945: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   948: putfield 148	com/yysec/apktools/info/d:p	Ljava/lang/String;
    //   951: goto -365 -> 586
    //   954: aload_0
    //   955: ldc_w 639
    //   958: putfield 148	com/yysec/apktools/info/d:p	Ljava/lang/String;
    //   961: goto -375 -> 586
    //   964: aload 7
    //   966: ldc_w 641
    //   969: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   972: ifne +14 -> 986
    //   975: aload 7
    //   977: ldc_w 643
    //   980: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   983: ifeq +25 -> 1008
    //   986: aload_0
    //   987: ldc_w 645
    //   990: putfield 152	com/yysec/apktools/info/d:q	Ljava/lang/String;
    //   993: goto -362 -> 631
    //   996: astore 7
    //   998: aload_0
    //   999: ldc_w 358
    //   1002: putfield 152	com/yysec/apktools/info/d:q	Ljava/lang/String;
    //   1005: goto -374 -> 631
    //   1008: aload 7
    //   1010: ldc_w 647
    //   1013: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1016: ifne +36 -> 1052
    //   1019: aload 7
    //   1021: ldc_w 649
    //   1024: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1027: ifne +25 -> 1052
    //   1030: aload 7
    //   1032: ldc_w 651
    //   1035: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1038: ifne +14 -> 1052
    //   1041: aload 7
    //   1043: ldc_w 653
    //   1046: invokevirtual 571	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1049: ifeq +13 -> 1062
    //   1052: aload_0
    //   1053: ldc_w 655
    //   1056: putfield 152	com/yysec/apktools/info/d:q	Ljava/lang/String;
    //   1059: goto -428 -> 631
    //   1062: aload_0
    //   1063: ldc_w 358
    //   1066: putfield 152	com/yysec/apktools/info/d:q	Ljava/lang/String;
    //   1069: goto -438 -> 631
    //   1072: aload 7
    //   1074: invokeinterface 658 1 0
    //   1079: checkcast 579	java/net/NetworkInterface
    //   1082: invokevirtual 661	java/net/NetworkInterface:getInetAddresses	()Ljava/util/Enumeration;
    //   1085: astore 8
    //   1087: aload 8
    //   1089: invokeinterface 588 1 0
    //   1094: ifeq -449 -> 645
    //   1097: aload 8
    //   1099: invokeinterface 658 1 0
    //   1104: checkcast 663	java/net/InetAddress
    //   1107: astore 10
    //   1109: aload 10
    //   1111: invokevirtual 666	java/net/InetAddress:isLoopbackAddress	()Z
    //   1114: ifne -27 -> 1087
    //   1117: aload_0
    //   1118: aload 10
    //   1120: invokevirtual 669	java/net/InetAddress:getHostAddress	()Ljava/lang/String;
    //   1123: invokevirtual 670	java/lang/String:toString	()Ljava/lang/String;
    //   1126: putfield 156	com/yysec/apktools/info/d:r	Ljava/lang/String;
    //   1129: goto -484 -> 645
    //   1132: astore 7
    //   1134: aload_0
    //   1135: ldc_w 358
    //   1138: putfield 156	com/yysec/apktools/info/d:r	Ljava/lang/String;
    //   1141: goto -482 -> 659
    //   1144: astore 7
    //   1146: aload_0
    //   1147: ldc_w 358
    //   1150: putfield 160	com/yysec/apktools/info/d:s	Ljava/lang/String;
    //   1153: goto -463 -> 690
    //   1156: astore 7
    //   1158: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1159	0	this	d
    //   103	233	1	d1	double
    //   60	243	3	f1	float
    //   234	82	4	f2	float
    //   814	43	5	i1	int
    //   652	3	6	bool	boolean
    //   21	625	7	localObject1	Object
    //   738	1	7	localException1	Exception
    //   750	1	7	localException2	Exception
    //   762	1	7	localException3	Exception
    //   774	1	7	localException4	Exception
    //   796	1	7	localException5	Exception
    //   801	73	7	str	String
    //   892	84	7	localException6	Exception
    //   996	77	7	localException7	Exception
    //   1132	1	7	localException8	Exception
    //   1144	1	7	localException9	Exception
    //   1156	1	7	localException10	Exception
    //   29	491	8	localObject2	Object
    //   786	3	8	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   1085	13	8	localEnumeration	java.util.Enumeration
    //   7	700	9	localStringBuffer	StringBuffer
    //   43	1076	10	localObject3	Object
    //   52	227	11	localObject4	Object
    //   716	1	11	localException11	Exception
    //   726	1	11	localException12	Exception
    //   75	36	12	localDisplayMetrics	android.util.DisplayMetrics
    // Exception table:
    //   from	to	target	type
    //   45	135	716	java/lang/Exception
    //   135	140	716	java/lang/Exception
    //   196	203	726	java/lang/Exception
    //   212	352	738	java/lang/Exception
    //   361	368	750	java/lang/Exception
    //   377	410	762	java/lang/Exception
    //   419	451	774	java/lang/Exception
    //   465	519	786	android/content/pm/PackageManager$NameNotFoundException
    //   465	519	796	java/lang/Exception
    //   788	793	796	java/lang/Exception
    //   557	574	892	java/lang/Exception
    //   579	586	892	java/lang/Exception
    //   873	889	892	java/lang/Exception
    //   904	951	892	java/lang/Exception
    //   954	961	892	java/lang/Exception
    //   595	624	996	java/lang/Exception
    //   624	631	996	java/lang/Exception
    //   964	986	996	java/lang/Exception
    //   986	993	996	java/lang/Exception
    //   1008	1052	996	java/lang/Exception
    //   1052	1059	996	java/lang/Exception
    //   1062	1069	996	java/lang/Exception
    //   640	645	1132	java/lang/Exception
    //   645	654	1132	java/lang/Exception
    //   1072	1087	1132	java/lang/Exception
    //   1087	1129	1132	java/lang/Exception
    //   668	690	1144	java/lang/Exception
    //   9	45	1156	java/lang/Exception
    //   140	196	1156	java/lang/Exception
    //   203	212	1156	java/lang/Exception
    //   352	361	1156	java/lang/Exception
    //   368	377	1156	java/lang/Exception
    //   410	419	1156	java/lang/Exception
    //   451	460	1156	java/lang/Exception
    //   523	548	1156	java/lang/Exception
    //   548	557	1156	java/lang/Exception
    //   586	595	1156	java/lang/Exception
    //   631	640	1156	java/lang/Exception
    //   659	668	1156	java/lang/Exception
    //   690	715	1156	java/lang/Exception
    //   718	723	1156	java/lang/Exception
    //   728	735	1156	java/lang/Exception
    //   740	747	1156	java/lang/Exception
    //   752	759	1156	java/lang/Exception
    //   764	771	1156	java/lang/Exception
    //   776	783	1156	java/lang/Exception
    //   806	816	1156	java/lang/Exception
    //   821	835	1156	java/lang/Exception
    //   838	870	1156	java/lang/Exception
    //   894	901	1156	java/lang/Exception
    //   998	1005	1156	java/lang/Exception
    //   1134	1141	1156	java/lang/Exception
    //   1146	1153	1156	java/lang/Exception
  }
  
  public String toString()
  {
    return "ReportInfo [eqtype=" + this.d + ", eqmodel=" + this.g + ", emulatorfeature=" + this.e + ", emulatormsg=" + this.f + ", eqscreen=" + this.h + ", eqosver=" + this.i + ", eqcpu=" + this.j + ", eqmem=" + this.k + ", currmem=" + this.l + ", currservice=" + this.m + ", appname=" + this.n + ", appver=" + this.o + ", secsdkver=" + this.b + ", network=" + this.p + ", networktype=" + this.q + ", ipadd=" + this.r + ", mac=" + this.s + ", imei=" + this.t + ", currtime=" + this.u + ", runapplis=" + this.v + ", apps=" + this.w + ", exceptionmsg=" + this.x + ", errmsg=" + this.y + ", errcode=" + this.z + ", time=" + this.A + ", logver=" + "142" + "]";
  }
}
