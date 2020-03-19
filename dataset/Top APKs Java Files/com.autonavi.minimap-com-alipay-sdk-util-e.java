package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.a;

public class e
{
  public static final String a = "failed";
  private Activity b;
  private IAlixPay c;
  private final Object d = IAlixPay.class;
  private boolean e;
  private a f;
  private ServiceConnection g = new f(this);
  private IRemoteServiceCallback h = new g(this);
  
  public e(Activity paramActivity, a paramA)
  {
    this.b = paramActivity;
    this.f = paramA;
  }
  
  /* Error */
  private String a(String paramString, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   4: invokestatic 64	com/alipay/sdk/util/i:f	(Landroid/content/Context;)Ljava/lang/String;
    //   7: astore 5
    //   9: aload_0
    //   10: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   13: invokevirtual 70	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   16: aload_2
    //   17: aload_0
    //   18: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   21: iconst_1
    //   22: invokevirtual 76	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   25: pop
    //   26: aload_0
    //   27: getfield 33	com/alipay/sdk/util/e:d	Ljava/lang/Object;
    //   30: astore_2
    //   31: aload_2
    //   32: monitorenter
    //   33: aload_0
    //   34: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   37: astore 6
    //   39: aload 6
    //   41: ifnonnull +17 -> 58
    //   44: aload_0
    //   45: getfield 33	com/alipay/sdk/util/e:d	Ljava/lang/Object;
    //   48: invokestatic 81	com/alipay/sdk/data/a:b	()Lcom/alipay/sdk/data/a;
    //   51: invokevirtual 84	com/alipay/sdk/data/a:a	()I
    //   54: i2l
    //   55: invokevirtual 88	java/lang/Object:wait	(J)V
    //   58: aload_2
    //   59: monitorexit
    //   60: aload_0
    //   61: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   64: ifnonnull +365 -> 429
    //   67: aload_0
    //   68: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   71: invokestatic 64	com/alipay/sdk/util/i:f	(Landroid/content/Context;)Ljava/lang/String;
    //   74: astore_1
    //   75: aload_0
    //   76: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   79: invokevirtual 92	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   82: iconst_0
    //   83: invokevirtual 98	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   86: astore_2
    //   87: new 100	java/lang/StringBuilder
    //   90: dup
    //   91: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   94: astore 6
    //   96: iconst_0
    //   97: istore_3
    //   98: iload_3
    //   99: aload_2
    //   100: invokeinterface 106 1 0
    //   105: if_icmpge +226 -> 331
    //   108: aload_2
    //   109: iload_3
    //   110: invokeinterface 110 2 0
    //   115: checkcast 112	android/content/pm/PackageInfo
    //   118: astore 7
    //   120: aload 7
    //   122: getfield 116	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   125: getfield 122	android/content/pm/ApplicationInfo:flags	I
    //   128: istore 4
    //   130: iload 4
    //   132: iconst_1
    //   133: iand
    //   134: ifne +69 -> 203
    //   137: iload 4
    //   139: sipush 128
    //   142: iand
    //   143: ifne +60 -> 203
    //   146: iconst_1
    //   147: istore 4
    //   149: iload 4
    //   151: ifeq +40 -> 191
    //   154: aload 7
    //   156: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   159: ldc 127
    //   161: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   164: ifeq +45 -> 209
    //   167: aload 6
    //   169: aload 7
    //   171: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   174: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: aload 7
    //   179: getfield 140	android/content/pm/PackageInfo:versionCode	I
    //   182: invokevirtual 143	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   185: ldc -111
    //   187: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: pop
    //   191: iload_3
    //   192: iconst_1
    //   193: iadd
    //   194: istore_3
    //   195: goto -97 -> 98
    //   198: astore_1
    //   199: aload_2
    //   200: monitorexit
    //   201: aload_1
    //   202: athrow
    //   203: iconst_0
    //   204: istore 4
    //   206: goto -57 -> 149
    //   209: aload 7
    //   211: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   214: ldc -109
    //   216: invokevirtual 151	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   219: ifne -28 -> 191
    //   222: aload 7
    //   224: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   227: ldc -103
    //   229: invokevirtual 157	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   232: ifne -41 -> 191
    //   235: aload 7
    //   237: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   240: ldc -97
    //   242: invokevirtual 157	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   245: ifne -54 -> 191
    //   248: aload 6
    //   250: aload 7
    //   252: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   255: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: ldc -111
    //   260: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: goto -73 -> 191
    //   267: astore_1
    //   268: ldc -95
    //   270: ldc -93
    //   272: aload_1
    //   273: invokestatic 168	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   276: ldc 10
    //   278: astore_2
    //   279: aload_0
    //   280: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   283: aload_0
    //   284: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   287: invokevirtual 172	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   290: aload_0
    //   291: aconst_null
    //   292: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   295: aload_0
    //   296: aconst_null
    //   297: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   300: aload_0
    //   301: aconst_null
    //   302: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   305: aload_2
    //   306: astore_1
    //   307: aload_0
    //   308: getfield 174	com/alipay/sdk/util/e:e	Z
    //   311: ifeq +18 -> 329
    //   314: aload_0
    //   315: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   318: iconst_0
    //   319: invokevirtual 178	android/app/Activity:setRequestedOrientation	(I)V
    //   322: aload_0
    //   323: iconst_0
    //   324: putfield 174	com/alipay/sdk/util/e:e	Z
    //   327: aload_2
    //   328: astore_1
    //   329: aload_1
    //   330: areturn
    //   331: aload 6
    //   333: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   336: astore_2
    //   337: ldc -95
    //   339: ldc -72
    //   341: new 100	java/lang/StringBuilder
    //   344: dup
    //   345: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   348: aload 5
    //   350: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: ldc -70
    //   355: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: aload_1
    //   359: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: ldc -70
    //   364: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: aload_2
    //   368: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   374: invokestatic 189	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   377: ldc 10
    //   379: astore_1
    //   380: aload_0
    //   381: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   384: aload_0
    //   385: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   388: invokevirtual 172	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   391: aload_0
    //   392: aconst_null
    //   393: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   396: aload_0
    //   397: aconst_null
    //   398: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   401: aload_0
    //   402: aconst_null
    //   403: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   406: aload_0
    //   407: getfield 174	com/alipay/sdk/util/e:e	Z
    //   410: ifeq -81 -> 329
    //   413: aload_0
    //   414: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   417: iconst_0
    //   418: invokevirtual 178	android/app/Activity:setRequestedOrientation	(I)V
    //   421: aload_0
    //   422: iconst_0
    //   423: putfield 174	com/alipay/sdk/util/e:e	Z
    //   426: ldc 10
    //   428: areturn
    //   429: aload_0
    //   430: getfield 49	com/alipay/sdk/util/e:f	Lcom/alipay/sdk/util/e$a;
    //   433: ifnull +12 -> 445
    //   436: aload_0
    //   437: getfield 49	com/alipay/sdk/util/e:f	Lcom/alipay/sdk/util/e$a;
    //   440: invokeinterface 191 1 0
    //   445: aload_0
    //   446: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   449: invokevirtual 194	android/app/Activity:getRequestedOrientation	()I
    //   452: ifne +16 -> 468
    //   455: aload_0
    //   456: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   459: iconst_1
    //   460: invokevirtual 178	android/app/Activity:setRequestedOrientation	(I)V
    //   463: aload_0
    //   464: iconst_1
    //   465: putfield 174	com/alipay/sdk/util/e:e	Z
    //   468: aload_0
    //   469: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   472: aload_0
    //   473: getfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   476: invokeinterface 198 2 0
    //   481: aload_0
    //   482: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   485: aload_1
    //   486: invokeinterface 202 2 0
    //   491: astore_2
    //   492: aload_0
    //   493: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   496: aload_0
    //   497: getfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   500: invokeinterface 205 2 0
    //   505: aload_0
    //   506: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   509: aload_0
    //   510: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   513: invokevirtual 172	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   516: aload_0
    //   517: aconst_null
    //   518: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   521: aload_0
    //   522: aconst_null
    //   523: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   526: aload_0
    //   527: aconst_null
    //   528: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   531: aload_2
    //   532: astore_1
    //   533: aload_0
    //   534: getfield 174	com/alipay/sdk/util/e:e	Z
    //   537: ifeq -208 -> 329
    //   540: aload_0
    //   541: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   544: iconst_0
    //   545: invokevirtual 178	android/app/Activity:setRequestedOrientation	(I)V
    //   548: aload_0
    //   549: iconst_0
    //   550: putfield 174	com/alipay/sdk/util/e:e	Z
    //   553: aload_2
    //   554: areturn
    //   555: astore_1
    //   556: aload_0
    //   557: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   560: aload_0
    //   561: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   564: invokevirtual 172	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   567: aload_0
    //   568: aconst_null
    //   569: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   572: aload_0
    //   573: aconst_null
    //   574: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   577: aload_0
    //   578: aconst_null
    //   579: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   582: aload_0
    //   583: getfield 174	com/alipay/sdk/util/e:e	Z
    //   586: ifeq +16 -> 602
    //   589: aload_0
    //   590: getfield 47	com/alipay/sdk/util/e:b	Landroid/app/Activity;
    //   593: iconst_0
    //   594: invokevirtual 178	android/app/Activity:setRequestedOrientation	(I)V
    //   597: aload_0
    //   598: iconst_0
    //   599: putfield 174	com/alipay/sdk/util/e:e	Z
    //   602: aload_1
    //   603: athrow
    //   604: astore_2
    //   605: goto -38 -> 567
    //   608: astore_1
    //   609: goto -319 -> 290
    //   612: astore_1
    //   613: goto -97 -> 516
    //   616: astore_2
    //   617: goto -226 -> 391
    //   620: astore 6
    //   622: goto -564 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	625	0	this	e
    //   0	625	1	paramString	String
    //   0	625	2	paramIntent	Intent
    //   97	98	3	i	int
    //   128	77	4	j	int
    //   7	342	5	str	String
    //   37	295	6	localObject	Object
    //   620	1	6	localInterruptedException	InterruptedException
    //   118	133	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   33	39	198	finally
    //   44	58	198	finally
    //   58	60	198	finally
    //   60	96	267	java/lang/Throwable
    //   98	130	267	java/lang/Throwable
    //   154	191	267	java/lang/Throwable
    //   209	264	267	java/lang/Throwable
    //   331	377	267	java/lang/Throwable
    //   429	445	267	java/lang/Throwable
    //   445	468	267	java/lang/Throwable
    //   468	505	267	java/lang/Throwable
    //   60	96	555	finally
    //   98	130	555	finally
    //   154	191	555	finally
    //   209	264	555	finally
    //   268	276	555	finally
    //   331	377	555	finally
    //   429	445	555	finally
    //   445	468	555	finally
    //   468	505	555	finally
    //   556	567	604	java/lang/Throwable
    //   279	290	608	java/lang/Throwable
    //   505	516	612	java/lang/Throwable
    //   380	391	616	java/lang/Throwable
    //   44	58	620	java/lang/InterruptedException
  }
  
  public final String a(String paramString)
  {
    for (;;)
    {
      try
      {
        localObject = i.a(this.b, "com.eg.android.AlipayGphone");
        if (localObject != null)
        {
          String str = i.a(((i.a)localObject).a);
          if ((str != null) && (!TextUtils.equals(str, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
          {
            a.a("biz", "ClientSignError", str);
            return "failed";
          }
        }
        int i = ((i.a)localObject).b;
        if (i <= 78) {}
      }
      catch (Throwable localThrowable2)
      {
        Object localObject;
        localThrowable2.printStackTrace();
        continue;
      }
      try
      {
        localObject = new Intent();
        ((Intent)localObject).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
        this.b.startActivity((Intent)localObject);
        Thread.sleep(150L);
        localObject = new Intent();
        ((Intent)localObject).setPackage("com.eg.android.AlipayGphone");
        ((Intent)localObject).setAction("com.eg.android.AlipayGphone.IAlixPay");
        return a(paramString, (Intent)localObject);
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
      }
    }
  }
  
  public final void a()
  {
    this.b = null;
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}
