package a.a;

import android.content.Context;
import android.content.SharedPreferences;

final class ab
  implements Runnable
{
  ab(SharedPreferences paramSharedPreferences, Context paramContext, String paramString, long paramLong) {}
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 18	a/a/ab:a	Landroid/content/SharedPreferences;
    //   4: invokeinterface 39 1 0
    //   9: astore_2
    //   10: aload_2
    //   11: getstatic 43	a/a/aa:a	Ljava/lang/String;
    //   14: invokeinterface 49 2 0
    //   19: pop
    //   20: invokestatic 54	cmn/a:a	()Lcmn/a;
    //   23: aload_2
    //   24: invokevirtual 57	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   27: aload_0
    //   28: getfield 18	a/a/ab:a	Landroid/content/SharedPreferences;
    //   31: invokestatic 60	a/a/aa:b	()Ljava/lang/String;
    //   34: iconst_0
    //   35: invokeinterface 64 3 0
    //   40: istore_1
    //   41: invokestatic 69	a/a/t:a	()La/a/t;
    //   44: invokevirtual 73	a/a/t:g	()Lcom/appbrain/RemoteSettings;
    //   47: ldc 75
    //   49: ldc 77
    //   51: invokeinterface 83 3 0
    //   56: invokestatic 89	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   59: iload_1
    //   60: if_icmple +290 -> 350
    //   63: iconst_1
    //   64: istore_1
    //   65: aload_0
    //   66: getfield 20	a/a/ab:b	Landroid/content/Context;
    //   69: invokestatic 91	a/a/aa:c	()Ljava/lang/String;
    //   72: invokevirtual 97	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   75: astore_2
    //   76: aload_2
    //   77: astore_3
    //   78: iload_1
    //   79: ifeq +91 -> 170
    //   82: new 99	org/apache/http/client/methods/HttpGet
    //   85: dup
    //   86: new 101	java/lang/StringBuilder
    //   89: dup
    //   90: aload_0
    //   91: getfield 22	a/a/ab:c	Ljava/lang/String;
    //   94: invokestatic 107	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   97: invokespecial 110	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   100: ldc 112
    //   102: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokespecial 120	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   111: astore_3
    //   112: invokestatic 125	cmn/h:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   115: aload_3
    //   116: invokevirtual 131	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   119: astore_3
    //   120: aload_3
    //   121: invokeinterface 137 1 0
    //   126: astore 4
    //   128: aload_0
    //   129: getfield 20	a/a/ab:b	Landroid/content/Context;
    //   132: invokestatic 91	a/a/aa:c	()Ljava/lang/String;
    //   135: iconst_0
    //   136: invokevirtual 141	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   139: astore 5
    //   141: aload 4
    //   143: aload 5
    //   145: invokeinterface 147 2 0
    //   150: aload 5
    //   152: invokevirtual 152	java/io/FileOutputStream:close	()V
    //   155: aload_3
    //   156: invokestatic 155	cmn/h:a	(Lorg/apache/http/HttpResponse;)V
    //   159: aload_0
    //   160: getfield 20	a/a/ab:b	Landroid/content/Context;
    //   163: invokestatic 91	a/a/aa:c	()Ljava/lang/String;
    //   166: invokevirtual 97	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   169: astore_3
    //   170: new 157	java/util/ArrayList
    //   173: dup
    //   174: invokespecial 158	java/util/ArrayList:<init>	()V
    //   177: astore 4
    //   179: aload_0
    //   180: getfield 20	a/a/ab:b	Landroid/content/Context;
    //   183: invokevirtual 162	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   186: iconst_0
    //   187: invokevirtual 168	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   190: invokeinterface 174 1 0
    //   195: astore_2
    //   196: aload_2
    //   197: invokeinterface 180 1 0
    //   202: ifne +117 -> 319
    //   205: aload_3
    //   206: invokestatic 185	a/a/ac:a	(Ljava/io/InputStream;)La/a/ac;
    //   209: astore_2
    //   210: aload 4
    //   212: aload_2
    //   213: invokestatic 188	a/a/aa:a	(Ljava/util/List;La/a/ac;)Ljava/lang/String;
    //   216: astore 4
    //   218: aload_0
    //   219: getfield 18	a/a/ab:a	Landroid/content/SharedPreferences;
    //   222: invokeinterface 39 1 0
    //   227: astore 5
    //   229: aload 5
    //   231: invokestatic 60	a/a/aa:b	()Ljava/lang/String;
    //   234: aload_2
    //   235: invokevirtual 191	a/a/ac:a	()I
    //   238: invokeinterface 195 3 0
    //   243: pop
    //   244: aload 5
    //   246: getstatic 43	a/a/aa:a	Ljava/lang/String;
    //   249: aload 4
    //   251: invokeinterface 199 3 0
    //   256: pop
    //   257: aload 5
    //   259: invokestatic 201	a/a/aa:d	()Ljava/lang/String;
    //   262: aload_0
    //   263: getfield 24	a/a/ab:d	J
    //   266: invokeinterface 205 4 0
    //   271: pop
    //   272: invokestatic 54	cmn/a:a	()Lcmn/a;
    //   275: aload 5
    //   277: invokevirtual 57	cmn/a:a	(Landroid/content/SharedPreferences$Editor;)V
    //   280: aload_3
    //   281: invokevirtual 208	java/io/FileInputStream:close	()V
    //   284: invokestatic 212	a/a/aa:e	()Ljava/util/concurrent/atomic/AtomicBoolean;
    //   287: iconst_0
    //   288: invokevirtual 218	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   291: return
    //   292: astore_2
    //   293: iconst_1
    //   294: istore_1
    //   295: aconst_null
    //   296: astore_2
    //   297: goto -221 -> 76
    //   300: astore 4
    //   302: aload_3
    //   303: invokestatic 155	cmn/h:a	(Lorg/apache/http/HttpResponse;)V
    //   306: aload 4
    //   308: athrow
    //   309: astore_3
    //   310: aload_3
    //   311: invokevirtual 221	java/lang/Throwable:printStackTrace	()V
    //   314: aload_2
    //   315: astore_3
    //   316: goto -146 -> 170
    //   319: aload 4
    //   321: aload_2
    //   322: invokeinterface 225 1 0
    //   327: checkcast 227	android/content/pm/PackageInfo
    //   330: getfield 230	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   333: invokeinterface 234 2 0
    //   338: pop
    //   339: goto -143 -> 196
    //   342: astore_2
    //   343: aload_2
    //   344: invokevirtual 221	java/lang/Throwable:printStackTrace	()V
    //   347: goto -63 -> 284
    //   350: iconst_0
    //   351: istore_1
    //   352: goto -287 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	355	0	this	ab
    //   40	312	1	i	int
    //   9	226	2	localObject1	Object
    //   292	1	2	localFileNotFoundException	java.io.FileNotFoundException
    //   296	26	2	localObject2	Object
    //   342	2	2	localThrowable1	Throwable
    //   77	226	3	localObject3	Object
    //   309	2	3	localThrowable2	Throwable
    //   315	1	3	localObject4	Object
    //   126	124	4	localObject5	Object
    //   300	20	4	localObject6	Object
    //   139	137	5	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   65	76	292	java/io/FileNotFoundException
    //   120	155	300	finally
    //   112	120	309	java/lang/Throwable
    //   155	170	309	java/lang/Throwable
    //   302	309	309	java/lang/Throwable
    //   170	196	342	java/lang/Throwable
    //   196	284	342	java/lang/Throwable
    //   319	339	342	java/lang/Throwable
  }
}
