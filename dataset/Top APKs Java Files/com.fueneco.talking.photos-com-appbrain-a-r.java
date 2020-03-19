package com.appbrain.a;

import android.content.Context;
import android.content.SharedPreferences;

final class r
  implements Runnable
{
  r(SharedPreferences paramSharedPreferences, Context paramContext, String paramString, long paramLong) {}
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 18	com/appbrain/a/r:a	Landroid/content/SharedPreferences;
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
    //   27: getfield 18	com/appbrain/a/r:a	Landroid/content/SharedPreferences;
    //   30: invokestatic 60	com/appbrain/a/q:d	()Ljava/lang/String;
    //   33: iconst_0
    //   34: invokeinterface 64 3 0
    //   39: istore_1
    //   40: invokestatic 69	com/appbrain/a/m:a	()Lcom/appbrain/a/m;
    //   43: invokevirtual 73	com/appbrain/a/m:i	()Lcom/appbrain/d;
    //   46: ldc 75
    //   48: ldc 77
    //   50: invokeinterface 82 3 0
    //   55: invokestatic 88	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   58: iload_1
    //   59: if_icmple +299 -> 358
    //   62: iconst_1
    //   63: istore_1
    //   64: aload_0
    //   65: getfield 20	com/appbrain/a/r:b	Landroid/content/Context;
    //   68: invokestatic 91	com/appbrain/a/q:e	()Ljava/lang/String;
    //   71: invokevirtual 97	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   74: astore_2
    //   75: aload_2
    //   76: astore_3
    //   77: iload_1
    //   78: ifeq +91 -> 169
    //   81: new 99	org/apache/http/client/methods/HttpGet
    //   84: dup
    //   85: new 101	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   92: aload_0
    //   93: getfield 22	com/appbrain/a/r:c	Ljava/lang/String;
    //   96: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: ldc 108
    //   101: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokespecial 114	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   110: astore_3
    //   111: invokestatic 119	cmn/k:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   114: aload_3
    //   115: invokevirtual 125	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   118: astore_3
    //   119: aload_3
    //   120: invokeinterface 131 1 0
    //   125: astore 4
    //   127: aload_0
    //   128: getfield 20	com/appbrain/a/r:b	Landroid/content/Context;
    //   131: invokestatic 91	com/appbrain/a/q:e	()Ljava/lang/String;
    //   134: iconst_0
    //   135: invokevirtual 135	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   138: astore 5
    //   140: aload 4
    //   142: aload 5
    //   144: invokeinterface 141 2 0
    //   149: aload 5
    //   151: invokevirtual 146	java/io/FileOutputStream:close	()V
    //   154: aload_3
    //   155: invokestatic 149	cmn/k:a	(Lorg/apache/http/HttpResponse;)V
    //   158: aload_0
    //   159: getfield 20	com/appbrain/a/r:b	Landroid/content/Context;
    //   162: invokestatic 91	com/appbrain/a/q:e	()Ljava/lang/String;
    //   165: invokevirtual 97	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   168: astore_3
    //   169: aload_3
    //   170: invokestatic 154	com/appbrain/a/s:a	(Ljava/io/InputStream;)Lcom/appbrain/a/s;
    //   173: astore_2
    //   174: aload_2
    //   175: invokevirtual 156	com/appbrain/a/s:b	()V
    //   178: aload_0
    //   179: getfield 20	com/appbrain/a/r:b	Landroid/content/Context;
    //   182: invokevirtual 160	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   185: astore 4
    //   187: aload 4
    //   189: iconst_0
    //   190: invokevirtual 166	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   193: invokeinterface 172 1 0
    //   198: astore 5
    //   200: aload 5
    //   202: invokeinterface 178 1 0
    //   207: ifeq +77 -> 284
    //   210: aload 5
    //   212: invokeinterface 182 1 0
    //   217: checkcast 184	android/content/pm/PackageInfo
    //   220: astore 6
    //   222: aload_2
    //   223: aload 6
    //   225: invokestatic 52	cmn/a:a	()Lcmn/a;
    //   228: aload 4
    //   230: aload 6
    //   232: getfield 187	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   235: invokevirtual 190	cmn/a:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/String;
    //   238: invokevirtual 193	com/appbrain/a/s:a	(Landroid/content/pm/PackageInfo;Ljava/lang/String;)V
    //   241: goto -41 -> 200
    //   244: astore_2
    //   245: aload_2
    //   246: invokevirtual 196	java/lang/Throwable:printStackTrace	()V
    //   249: invokestatic 200	com/appbrain/a/q:g	()Ljava/util/concurrent/atomic/AtomicBoolean;
    //   252: iconst_0
    //   253: invokevirtual 206	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   256: return
    //   257: astore_2
    //   258: iconst_1
    //   259: istore_1
    //   260: aconst_null
    //   261: astore_2
    //   262: goto -187 -> 75
    //   265: astore 4
    //   267: aload_3
    //   268: invokestatic 149	cmn/k:a	(Lorg/apache/http/HttpResponse;)V
    //   271: aload 4
    //   273: athrow
    //   274: astore_3
    //   275: aload_3
    //   276: invokevirtual 196	java/lang/Throwable:printStackTrace	()V
    //   279: aload_2
    //   280: astore_3
    //   281: goto -112 -> 169
    //   284: aload_2
    //   285: invokevirtual 208	com/appbrain/a/s:c	()Ljava/lang/String;
    //   288: astore 4
    //   290: aload_0
    //   291: getfield 18	com/appbrain/a/r:a	Landroid/content/SharedPreferences;
    //   294: invokeinterface 39 1 0
    //   299: astore 5
    //   301: aload 5
    //   303: invokestatic 60	com/appbrain/a/q:d	()Ljava/lang/String;
    //   306: aload_2
    //   307: invokevirtual 211	com/appbrain/a/s:a	()I
    //   310: invokeinterface 215 3 0
    //   315: pop
    //   316: aload 5
    //   318: ldc 41
    //   320: aload 4
    //   322: invokeinterface 219 3 0
    //   327: pop
    //   328: aload 5
    //   330: invokestatic 222	com/appbrain/a/q:f	()Ljava/lang/String;
    //   333: aload_0
    //   334: getfield 24	com/appbrain/a/r:d	J
    //   337: invokeinterface 226 4 0
    //   342: pop
    //   343: invokestatic 52	cmn/a:a	()Lcmn/a;
    //   346: aload 5
    //   348: invokevirtual 55	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   351: aload_3
    //   352: invokevirtual 229	java/io/FileInputStream:close	()V
    //   355: goto -106 -> 249
    //   358: iconst_0
    //   359: istore_1
    //   360: goto -296 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	363	0	this	r
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
