package com.trendmicro.mpa.datacollect;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.trendmicro.mpa.c;
import com.trendmicro.mpa.c.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class b
{
  static String a = c.a(b.class);
  
  b() {}
  
  /* Error */
  public static long a(Context paramContext)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore 4
    //   3: aconst_null
    //   4: astore 8
    //   6: new 28	java/io/FileReader
    //   9: dup
    //   10: ldc 30
    //   12: invokespecial 33	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore_0
    //   16: new 35	java/io/BufferedReader
    //   19: dup
    //   20: aload_0
    //   21: sipush 8192
    //   24: invokespecial 38	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore 8
    //   29: aload 8
    //   31: astore 9
    //   33: aload_0
    //   34: astore 10
    //   36: aload 8
    //   38: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   41: astore 11
    //   43: aload 11
    //   45: ifnonnull +32 -> 77
    //   48: aload 8
    //   50: ifnull +8 -> 58
    //   53: aload 8
    //   55: invokevirtual 45	java/io/BufferedReader:close	()V
    //   58: lload 4
    //   60: lstore 6
    //   62: aload_0
    //   63: ifnull +11 -> 74
    //   66: aload_0
    //   67: invokevirtual 46	java/io/FileReader:close	()V
    //   70: lload 4
    //   72: lstore 6
    //   74: lload 6
    //   76: lreturn
    //   77: aload 8
    //   79: astore 9
    //   81: aload_0
    //   82: astore 10
    //   84: aload 11
    //   86: ldc 48
    //   88: invokevirtual 54	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   91: astore 11
    //   93: lload 4
    //   95: lstore_2
    //   96: aload 11
    //   98: ifnull +44 -> 142
    //   101: lload 4
    //   103: lstore_2
    //   104: aload 8
    //   106: astore 9
    //   108: aload_0
    //   109: astore 10
    //   111: aload 11
    //   113: arraylength
    //   114: ifle +28 -> 142
    //   117: aload 8
    //   119: astore 9
    //   121: aload_0
    //   122: astore 10
    //   124: aload 11
    //   126: iconst_1
    //   127: aaload
    //   128: invokestatic 60	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   131: invokevirtual 64	java/lang/Integer:intValue	()I
    //   134: istore_1
    //   135: iload_1
    //   136: sipush 1024
    //   139: imul
    //   140: i2l
    //   141: lstore_2
    //   142: aload 8
    //   144: ifnull +8 -> 152
    //   147: aload 8
    //   149: invokevirtual 45	java/io/BufferedReader:close	()V
    //   152: lload_2
    //   153: lstore 6
    //   155: aload_0
    //   156: ifnull -82 -> 74
    //   159: aload_0
    //   160: invokevirtual 46	java/io/FileReader:close	()V
    //   163: lload_2
    //   164: lreturn
    //   165: astore_0
    //   166: lload_2
    //   167: lreturn
    //   168: astore 9
    //   170: aconst_null
    //   171: astore_0
    //   172: getstatic 70	com/trendmicro/mpa/c$a:e	Z
    //   175: ifeq +17 -> 192
    //   178: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   181: aload 9
    //   183: invokevirtual 73	java/io/IOException:getMessage	()Ljava/lang/String;
    //   186: aload 9
    //   188: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   191: pop
    //   192: aload_0
    //   193: ifnull +7 -> 200
    //   196: aload_0
    //   197: invokevirtual 45	java/io/BufferedReader:close	()V
    //   200: lload 4
    //   202: lstore 6
    //   204: aload 8
    //   206: ifnull -132 -> 74
    //   209: aload 8
    //   211: invokevirtual 46	java/io/FileReader:close	()V
    //   214: lconst_0
    //   215: lreturn
    //   216: astore_0
    //   217: lconst_0
    //   218: lreturn
    //   219: astore 11
    //   221: aconst_null
    //   222: astore 8
    //   224: aconst_null
    //   225: astore_0
    //   226: aload 8
    //   228: astore 9
    //   230: aload_0
    //   231: astore 10
    //   233: getstatic 70	com/trendmicro/mpa/c$a:e	Z
    //   236: ifeq +24 -> 260
    //   239: aload 8
    //   241: astore 9
    //   243: aload_0
    //   244: astore 10
    //   246: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   249: aload 11
    //   251: invokevirtual 79	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   254: aload 11
    //   256: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   259: pop
    //   260: aload 8
    //   262: ifnull +8 -> 270
    //   265: aload 8
    //   267: invokevirtual 45	java/io/BufferedReader:close	()V
    //   270: lload 4
    //   272: lstore 6
    //   274: aload_0
    //   275: ifnull -201 -> 74
    //   278: aload_0
    //   279: invokevirtual 46	java/io/FileReader:close	()V
    //   282: lconst_0
    //   283: lreturn
    //   284: astore_0
    //   285: lconst_0
    //   286: lreturn
    //   287: astore 8
    //   289: aconst_null
    //   290: astore 9
    //   292: aconst_null
    //   293: astore_0
    //   294: aload 9
    //   296: ifnull +8 -> 304
    //   299: aload 9
    //   301: invokevirtual 45	java/io/BufferedReader:close	()V
    //   304: aload_0
    //   305: ifnull +7 -> 312
    //   308: aload_0
    //   309: invokevirtual 46	java/io/FileReader:close	()V
    //   312: aload 8
    //   314: athrow
    //   315: astore_0
    //   316: goto -4 -> 312
    //   319: astore 8
    //   321: aconst_null
    //   322: astore 9
    //   324: goto -30 -> 294
    //   327: astore 8
    //   329: aload 10
    //   331: astore_0
    //   332: goto -38 -> 294
    //   335: astore 9
    //   337: aload 8
    //   339: astore 10
    //   341: aload 9
    //   343: astore 8
    //   345: aload_0
    //   346: astore 9
    //   348: aload 10
    //   350: astore_0
    //   351: goto -57 -> 294
    //   354: astore 11
    //   356: aconst_null
    //   357: astore 8
    //   359: goto -133 -> 226
    //   362: astore 11
    //   364: goto -138 -> 226
    //   367: astore 9
    //   369: aconst_null
    //   370: astore 10
    //   372: aload_0
    //   373: astore 8
    //   375: aload 10
    //   377: astore_0
    //   378: goto -206 -> 172
    //   381: astore 9
    //   383: aload_0
    //   384: astore 10
    //   386: aload 8
    //   388: astore_0
    //   389: aload 10
    //   391: astore 8
    //   393: goto -221 -> 172
    //   396: astore_0
    //   397: lconst_0
    //   398: lreturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	399	0	paramContext	Context
    //   134	6	1	i	int
    //   95	72	2	l1	long
    //   1	270	4	l2	long
    //   60	213	6	l3	long
    //   4	262	8	localBufferedReader1	java.io.BufferedReader
    //   287	26	8	localObject1	Object
    //   319	1	8	localObject2	Object
    //   327	11	8	localObject3	Object
    //   343	49	8	localObject4	Object
    //   31	89	9	localBufferedReader2	java.io.BufferedReader
    //   168	19	9	localIOException1	java.io.IOException
    //   228	95	9	localBufferedReader3	java.io.BufferedReader
    //   335	7	9	localObject5	Object
    //   346	1	9	localContext	Context
    //   367	1	9	localIOException2	java.io.IOException
    //   381	1	9	localIOException3	java.io.IOException
    //   34	356	10	localObject6	Object
    //   41	84	11	localObject7	Object
    //   219	36	11	localNumberFormatException1	NumberFormatException
    //   354	1	11	localNumberFormatException2	NumberFormatException
    //   362	1	11	localNumberFormatException3	NumberFormatException
    // Exception table:
    //   from	to	target	type
    //   147	152	165	java/io/IOException
    //   159	163	165	java/io/IOException
    //   6	16	168	java/io/IOException
    //   196	200	216	java/io/IOException
    //   209	214	216	java/io/IOException
    //   6	16	219	java/lang/NumberFormatException
    //   265	270	284	java/io/IOException
    //   278	282	284	java/io/IOException
    //   6	16	287	finally
    //   299	304	315	java/io/IOException
    //   308	312	315	java/io/IOException
    //   16	29	319	finally
    //   36	43	327	finally
    //   84	93	327	finally
    //   111	117	327	finally
    //   124	135	327	finally
    //   233	239	327	finally
    //   246	260	327	finally
    //   172	192	335	finally
    //   16	29	354	java/lang/NumberFormatException
    //   36	43	362	java/lang/NumberFormatException
    //   84	93	362	java/lang/NumberFormatException
    //   111	117	362	java/lang/NumberFormatException
    //   124	135	362	java/lang/NumberFormatException
    //   16	29	367	java/io/IOException
    //   36	43	381	java/io/IOException
    //   84	93	381	java/io/IOException
    //   111	117	381	java/io/IOException
    //   124	135	381	java/io/IOException
    //   53	58	396	java/io/IOException
    //   66	70	396	java/io/IOException
  }
  
  public static a a()
  {
    a localA = new a();
    if (!c()) {
      return localA;
    }
    String str;
    try
    {
      localObject = new StatFs(Environment.getExternalStorageDirectory().getPath());
      localA.a = (((StatFs)localObject).getAvailableBlocks() * ((StatFs)localObject).getBlockSize());
      localA.b = (((StatFs)localObject).getBlockCount() * ((StatFs)localObject).getBlockSize());
      return localA;
    }
    catch (Exception localException)
    {
      while (!c.a.e) {}
      str = a;
      if (localException.getMessage() == null) {}
    }
    for (Object localObject = localException.getMessage();; localObject = "")
    {
      Log.e(str, (String)localObject, localException);
      break;
    }
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  public static a b()
  {
    a localA = new a();
    if (!c()) {
      return localA;
    }
    String str;
    try
    {
      localObject = new StatFs(Environment.getDataDirectory().getPath());
      localA.a = (((StatFs)localObject).getAvailableBlocks() * ((StatFs)localObject).getBlockSize());
      localA.b = (((StatFs)localObject).getBlockCount() * ((StatFs)localObject).getBlockSize());
      return localA;
    }
    catch (Exception localException)
    {
      while (!c.a.e) {}
      str = a;
      if (localException.getMessage() == null) {}
    }
    for (Object localObject = localException.getMessage();; localObject = "")
    {
      Log.e(str, (String)localObject, localException);
      break;
    }
  }
  
  public static List<PackageInfo> b(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = new ArrayList();
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      int i = 0;
      if (i < ((List)localObject).size())
      {
        PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
        if (localPackageInfo == null) {}
        for (;;)
        {
          i += 1;
          break;
          String str1 = localPackageInfo.packageName;
          String str2 = localPackageInfo.versionName;
          int j = localPackageInfo.versionCode;
          if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)) && (j > 0) && (!a(localPackageInfo.applicationInfo))) {
            paramContext.add(localPackageInfo);
          }
        }
      }
    }
    return paramContext;
  }
  
  public static int c(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    int i = -1;
    try
    {
      int j = Settings.System.getInt(paramContext, "screen_brightness");
      i = j;
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      while (!c.a.e) {}
      Log.e(a, paramContext.getMessage(), paramContext);
    }
    return i;
    return -1;
  }
  
  public static boolean c()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static int d(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    int i = -1;
    try
    {
      int j = Settings.System.getInt(paramContext, "screen_brightness_mode");
      i = j;
    }
    catch (Exception paramContext)
    {
      while (!c.a.e) {}
      Log.e(a, paramContext.getMessage(), paramContext.getCause());
    }
    return i;
    return -1;
  }
  
  /* Error */
  public static String d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: new 35	java/io/BufferedReader
    //   6: dup
    //   7: new 216	java/io/InputStreamReader
    //   10: dup
    //   11: new 218	java/io/FileInputStream
    //   14: dup
    //   15: new 97	java/io/File
    //   18: dup
    //   19: ldc -36
    //   21: invokespecial 221	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 224	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   27: invokespecial 227	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   30: sipush 1024
    //   33: invokespecial 38	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   36: astore_3
    //   37: aconst_null
    //   38: astore 4
    //   40: aload_3
    //   41: astore_2
    //   42: aload_3
    //   43: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: astore 5
    //   48: aload 5
    //   50: ifnull +291 -> 341
    //   53: iconst_0
    //   54: ifne +66 -> 120
    //   57: aload_3
    //   58: astore_2
    //   59: aload 5
    //   61: ldc -27
    //   63: invokevirtual 233	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   66: ifeq +54 -> 120
    //   69: aload 5
    //   71: ifnull +91 -> 162
    //   74: aload_3
    //   75: astore_2
    //   76: aload 5
    //   78: bipush 58
    //   80: invokevirtual 237	java/lang/String:indexOf	(I)I
    //   83: istore_0
    //   84: iload_0
    //   85: iconst_m1
    //   86: if_icmpeq +141 -> 227
    //   89: aload_3
    //   90: astore_2
    //   91: aload 5
    //   93: iload_0
    //   94: iconst_1
    //   95: iadd
    //   96: invokevirtual 241	java/lang/String:substring	(I)Ljava/lang/String;
    //   99: invokevirtual 244	java/lang/String:trim	()Ljava/lang/String;
    //   102: astore 4
    //   104: aload 4
    //   106: astore_2
    //   107: aload_3
    //   108: ifnull +10 -> 118
    //   111: aload_3
    //   112: invokevirtual 45	java/io/BufferedReader:close	()V
    //   115: aload 4
    //   117: astore_2
    //   118: aload_2
    //   119: areturn
    //   120: aload 4
    //   122: ifnonnull -82 -> 40
    //   125: aload_3
    //   126: astore_2
    //   127: aload 5
    //   129: ldc -10
    //   131: invokevirtual 233	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   134: istore_1
    //   135: iload_1
    //   136: ifeq -96 -> 40
    //   139: aload 5
    //   141: astore 4
    //   143: goto -103 -> 40
    //   146: astore_2
    //   147: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   150: aload_2
    //   151: invokevirtual 249	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   154: aload_2
    //   155: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   158: pop
    //   159: aload 4
    //   161: areturn
    //   162: aload 4
    //   164: ifnull +63 -> 227
    //   167: aload_3
    //   168: astore_2
    //   169: aload 4
    //   171: bipush 58
    //   173: invokevirtual 237	java/lang/String:indexOf	(I)I
    //   176: istore_0
    //   177: iload_0
    //   178: iconst_m1
    //   179: if_icmpeq +48 -> 227
    //   182: aload_3
    //   183: astore_2
    //   184: aload 4
    //   186: iload_0
    //   187: iconst_1
    //   188: iadd
    //   189: invokevirtual 241	java/lang/String:substring	(I)Ljava/lang/String;
    //   192: invokevirtual 244	java/lang/String:trim	()Ljava/lang/String;
    //   195: astore 4
    //   197: aload 4
    //   199: astore_2
    //   200: aload_3
    //   201: ifnull -83 -> 118
    //   204: aload_3
    //   205: invokevirtual 45	java/io/BufferedReader:close	()V
    //   208: aload 4
    //   210: areturn
    //   211: astore_2
    //   212: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   215: aload_2
    //   216: invokevirtual 249	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   219: aload_2
    //   220: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   223: pop
    //   224: aload 4
    //   226: areturn
    //   227: aload 6
    //   229: astore_2
    //   230: aload_3
    //   231: ifnull -113 -> 118
    //   234: aload_3
    //   235: invokevirtual 45	java/io/BufferedReader:close	()V
    //   238: aconst_null
    //   239: areturn
    //   240: astore_2
    //   241: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   244: aload_2
    //   245: invokevirtual 249	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   248: aload_2
    //   249: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   252: pop
    //   253: aconst_null
    //   254: areturn
    //   255: astore 4
    //   257: aconst_null
    //   258: astore_3
    //   259: aload_3
    //   260: astore_2
    //   261: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   264: aload 4
    //   266: invokevirtual 250	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   269: aload 4
    //   271: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   274: pop
    //   275: aload 6
    //   277: astore_2
    //   278: aload_3
    //   279: ifnull -161 -> 118
    //   282: aload_3
    //   283: invokevirtual 45	java/io/BufferedReader:close	()V
    //   286: aconst_null
    //   287: areturn
    //   288: astore_2
    //   289: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   292: aload_2
    //   293: invokevirtual 249	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   296: aload_2
    //   297: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   300: pop
    //   301: aconst_null
    //   302: areturn
    //   303: astore_3
    //   304: aconst_null
    //   305: astore_2
    //   306: aload_2
    //   307: ifnull +7 -> 314
    //   310: aload_2
    //   311: invokevirtual 45	java/io/BufferedReader:close	()V
    //   314: aload_3
    //   315: athrow
    //   316: astore_2
    //   317: getstatic 17	com/trendmicro/mpa/datacollect/b:a	Ljava/lang/String;
    //   320: aload_2
    //   321: invokevirtual 249	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   324: aload_2
    //   325: invokestatic 78	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   328: pop
    //   329: goto -15 -> 314
    //   332: astore_3
    //   333: goto -27 -> 306
    //   336: astore 4
    //   338: goto -79 -> 259
    //   341: aconst_null
    //   342: astore 5
    //   344: goto -275 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   83	106	0	i	int
    //   134	2	1	bool	boolean
    //   41	86	2	localObject1	Object
    //   146	9	2	localIOException1	java.io.IOException
    //   168	32	2	localObject2	Object
    //   211	9	2	localIOException2	java.io.IOException
    //   229	1	2	localObject3	Object
    //   240	9	2	localIOException3	java.io.IOException
    //   260	18	2	localObject4	Object
    //   288	9	2	localIOException4	java.io.IOException
    //   305	6	2	localObject5	Object
    //   316	9	2	localIOException5	java.io.IOException
    //   36	247	3	localBufferedReader	java.io.BufferedReader
    //   303	12	3	localObject6	Object
    //   332	1	3	localObject7	Object
    //   38	187	4	localObject8	Object
    //   255	15	4	localException1	Exception
    //   336	1	4	localException2	Exception
    //   46	297	5	str	String
    //   1	275	6	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   111	115	146	java/io/IOException
    //   204	208	211	java/io/IOException
    //   234	238	240	java/io/IOException
    //   3	37	255	java/lang/Exception
    //   282	286	288	java/io/IOException
    //   3	37	303	finally
    //   310	314	316	java/io/IOException
    //   42	48	332	finally
    //   59	69	332	finally
    //   76	84	332	finally
    //   91	104	332	finally
    //   127	135	332	finally
    //   169	177	332	finally
    //   184	197	332	finally
    //   261	275	332	finally
    //   42	48	336	java/lang/Exception
    //   59	69	336	java/lang/Exception
    //   76	84	336	java/lang/Exception
    //   91	104	336	java/lang/Exception
    //   127	135	336	java/lang/Exception
    //   169	177	336	java/lang/Exception
    //   184	197	336	java/lang/Exception
  }
  
  public static int e(Context paramContext)
  {
    int i = -1;
    paramContext = paramContext.getContentResolver();
    try
    {
      int j = Settings.System.getInt(paramContext, "screen_off_timeout");
      i = j;
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      while (!c.a.e) {}
      Log.e(a, paramContext.getMessage(), paramContext);
    }
    return i;
    return -1;
  }
  
  static class a
  {
    long a = -1L;
    long b = -1L;
    
    a() {}
  }
}
