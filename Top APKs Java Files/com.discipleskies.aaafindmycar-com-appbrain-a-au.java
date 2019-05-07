package com.appbrain.a;

import android.content.Context;
import android.content.SharedPreferences;

final class au
  implements Runnable
{
  au(SharedPreferences paramSharedPreferences, Context paramContext, String paramString, long paramLong) {}
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 18	com/appbrain/a/au:a	Landroid/content/SharedPreferences;
    //   4: invokeinterface 39 1 0
    //   9: astore_2
    //   10: aload_2
    //   11: ldc 41
    //   13: invokeinterface 47 2 0
    //   18: pop
    //   19: invokestatic 52	cmn/a:a	()Lcmn/a;
    //   22: aload_2
    //   23: invokevirtual 55	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   26: aload_0
    //   27: getfield 18	com/appbrain/a/au:a	Landroid/content/SharedPreferences;
    //   30: invokestatic 60	com/appbrain/a/at:d	()Ljava/lang/String;
    //   33: iconst_0
    //   34: invokeinterface 64 3 0
    //   39: istore_1
    //   40: invokestatic 69	com/appbrain/a/al:a	()Lcom/appbrain/a/al;
    //   43: invokevirtual 73	com/appbrain/a/al:i	()Lcom/appbrain/RemoteSettings;
    //   46: ldc 75
    //   48: ldc 77
    //   50: invokeinterface 83 3 0
    //   55: invokestatic 89	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   58: iload_1
    //   59: if_icmple +299 -> 358
    //   62: iconst_1
    //   63: istore_1
    //   64: aload_0
    //   65: getfield 20	com/appbrain/a/au:b	Landroid/content/Context;
    //   68: invokestatic 92	com/appbrain/a/at:e	()Ljava/lang/String;
    //   71: invokevirtual 98	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   74: astore_2
    //   75: aload_2
    //   76: astore_3
    //   77: iload_1
    //   78: ifeq +91 -> 169
    //   81: new 100	org/apache/http/client/methods/HttpGet
    //   84: dup
    //   85: new 102	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   92: aload_0
    //   93: getfield 22	com/appbrain/a/au:c	Ljava/lang/String;
    //   96: invokevirtual 107	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: ldc 109
    //   101: invokevirtual 107	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokespecial 115	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   110: astore_3
    //   111: invokestatic 120	cmn/k:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   114: aload_3
    //   115: invokevirtual 126	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   118: astore_3
    //   119: aload_3
    //   120: invokeinterface 132 1 0
    //   125: astore 4
    //   127: aload_0
    //   128: getfield 20	com/appbrain/a/au:b	Landroid/content/Context;
    //   131: invokestatic 92	com/appbrain/a/at:e	()Ljava/lang/String;
    //   134: iconst_0
    //   135: invokevirtual 136	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   138: astore 5
    //   140: aload 4
    //   142: aload 5
    //   144: invokeinterface 142 2 0
    //   149: aload 5
    //   151: invokevirtual 147	java/io/FileOutputStream:close	()V
    //   154: aload_3
    //   155: invokestatic 150	cmn/k:a	(Lorg/apache/http/HttpResponse;)V
    //   158: aload_0
    //   159: getfield 20	com/appbrain/a/au:b	Landroid/content/Context;
    //   162: invokestatic 92	com/appbrain/a/at:e	()Ljava/lang/String;
    //   165: invokevirtual 98	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   168: astore_3
    //   169: aload_3
    //   170: invokestatic 155	com/appbrain/a/av:a	(Ljava/io/InputStream;)Lcom/appbrain/a/av;
    //   173: astore_2
    //   174: aload_2
    //   175: invokevirtual 157	com/appbrain/a/av:b	()V
    //   178: aload_0
    //   179: getfield 20	com/appbrain/a/au:b	Landroid/content/Context;
    //   182: invokevirtual 161	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   185: astore 4
    //   187: aload 4
    //   189: iconst_0
    //   190: invokevirtual 167	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   193: invokeinterface 173 1 0
    //   198: astore 5
    //   200: aload 5
    //   202: invokeinterface 179 1 0
    //   207: ifeq +77 -> 284
    //   210: aload 5
    //   212: invokeinterface 183 1 0
    //   217: checkcast 185	android/content/pm/PackageInfo
    //   220: astore 6
    //   222: aload_2
    //   223: aload 6
    //   225: invokestatic 52	cmn/a:a	()Lcmn/a;
    //   228: aload 4
    //   230: aload 6
    //   232: getfield 188	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   235: invokevirtual 191	cmn/a:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/String;
    //   238: invokevirtual 194	com/appbrain/a/av:a	(Landroid/content/pm/PackageInfo;Ljava/lang/String;)V
    //   241: goto -41 -> 200
    //   244: astore_2
    //   245: aload_2
    //   246: invokevirtual 197	java/lang/Throwable:printStackTrace	()V
    //   249: invokestatic 201	com/appbrain/a/at:g	()Ljava/util/concurrent/atomic/AtomicBoolean;
    //   252: iconst_0
    //   253: invokevirtual 207	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   256: return
    //   257: astore_2
    //   258: iconst_1
    //   259: istore_1
    //   260: aconst_null
    //   261: astore_2
    //   262: goto -187 -> 75
    //   265: astore 4
    //   267: aload_3
    //   268: invokestatic 150	cmn/k:a	(Lorg/apache/http/HttpResponse;)V
    //   271: aload 4
    //   273: athrow
    //   274: astore_3
    //   275: aload_3
    //   276: invokevirtual 197	java/lang/Throwable:printStackTrace	()V
    //   279: aload_2
    //   280: astore_3
    //   281: goto -112 -> 169
    //   284: aload_2
    //   285: invokevirtual 209	com/appbrain/a/av:c	()Ljava/lang/String;
    //   288: astore 4
    //   290: aload_0
    //   291: getfield 18	com/appbrain/a/au:a	Landroid/content/SharedPreferences;
    //   294: invokeinterface 39 1 0
    //   299: astore 5
    //   301: aload 5
    //   303: invokestatic 60	com/appbrain/a/at:d	()Ljava/lang/String;
    //   306: aload_2
    //   307: invokevirtual 212	com/appbrain/a/av:a	()I
    //   310: invokeinterface 216 3 0
    //   315: pop
    //   316: aload 5
    //   318: ldc 41
    //   320: aload 4
    //   322: invokeinterface 220 3 0
    //   327: pop
    //   328: aload 5
    //   330: invokestatic 223	com/appbrain/a/at:f	()Ljava/lang/String;
    //   333: aload_0
    //   334: getfield 24	com/appbrain/a/au:d	J
    //   337: invokeinterface 227 4 0
    //   342: pop
    //   343: invokestatic 52	cmn/a:a	()Lcmn/a;
    //   346: aload 5
    //   348: invokevirtual 55	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   351: aload_3
    //   352: invokevirtual 230	java/io/FileInputStream:close	()V
    //   355: goto -106 -> 249
    //   358: iconst_0
    //   359: istore_1
    //   360: goto -296 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	363	0	this	au
    //   39	321	1	i	int
    //   9	214	2	localObject1	Object
    //   244	2	2	localThrowable1	Throwable
    //   257	1	2	localFileNotFoundException	java.io.FileNotFoundException
    //   261	46	2	localObject2	Object
    //   76	192	3	localObject3	Object
    //   274	2	3	localThrowable2	Throwable
    //   280	72	3	localObject4	Object
    //   125	104	4	localObject5	Object
    //   265	7	4	localObject6	Object
    //   288	33	4	str	String
    //   138	209	5	localObject7	Object
    //   220	11	6	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   169	200	244	java/lang/Throwable
    //   200	241	244	java/lang/Throwable
    //   284	355	244	java/lang/Throwable
    //   64	75	257	java/io/FileNotFoundException
    //   119	154	265	finally
    //   111	119	274	java/lang/Throwable
    //   154	169	274	java/lang/Throwable
    //   267	274	274	java/lang/Throwable
  }
}
