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
  public static final String b = "failed";
  public Activity a;
  private IAlixPay c;
  private final Object d = IAlixPay.class;
  private boolean e;
  private a f;
  private ServiceConnection g = new f(this);
  private IRemoteServiceCallback h = new g(this);
  
  public e(Activity paramActivity, a paramA)
  {
    this.a = paramActivity;
    this.f = paramA;
  }
  
  /* Error */
  private String a(String paramString, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   4: invokestatic 64	com/alipay/sdk/util/k:f	(Landroid/content/Context;)Ljava/lang/String;
    //   7: astore 5
    //   9: aload_0
    //   10: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
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
    //   64: ifnonnull +306 -> 370
    //   67: aload_0
    //   68: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   71: invokestatic 64	com/alipay/sdk/util/k:f	(Landroid/content/Context;)Ljava/lang/String;
    //   74: astore_1
    //   75: aload_0
    //   76: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
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
    //   105: if_icmpge +151 -> 256
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
    //   134: ifne +511 -> 645
    //   137: iload 4
    //   139: sipush 128
    //   142: iand
    //   143: ifne +502 -> 645
    //   146: iconst_1
    //   147: istore 4
    //   149: goto +3 -> 152
    //   152: iload 4
    //   154: ifeq +497 -> 651
    //   157: aload 7
    //   159: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   162: ldc 127
    //   164: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   167: ifeq +36 -> 203
    //   170: aload 6
    //   172: aload 7
    //   174: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   177: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload 6
    //   183: aload 7
    //   185: getfield 140	android/content/pm/PackageInfo:versionCode	I
    //   188: invokevirtual 143	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   191: pop
    //   192: aload 6
    //   194: ldc -111
    //   196: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: pop
    //   200: goto +451 -> 651
    //   203: aload 7
    //   205: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   208: ldc -109
    //   210: invokevirtual 151	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   213: ifne +438 -> 651
    //   216: aload 7
    //   218: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   221: ldc -103
    //   223: invokevirtual 157	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   226: ifne +425 -> 651
    //   229: aload 7
    //   231: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   234: ldc -97
    //   236: invokevirtual 157	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   239: ifne +412 -> 651
    //   242: aload 6
    //   244: aload 7
    //   246: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   249: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: pop
    //   253: goto -61 -> 192
    //   256: aload 6
    //   258: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: astore_2
    //   262: new 100	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   269: astore 6
    //   271: aload 6
    //   273: aload 5
    //   275: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload 6
    //   281: ldc -91
    //   283: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload 6
    //   289: aload_1
    //   290: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: aload 6
    //   296: ldc -91
    //   298: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: pop
    //   302: aload 6
    //   304: aload_2
    //   305: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: ldc -89
    //   311: ldc -87
    //   313: aload 6
    //   315: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokestatic 174	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   321: aload_0
    //   322: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   325: aload_0
    //   326: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   329: invokevirtual 178	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   332: aload_0
    //   333: aconst_null
    //   334: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   337: aload_0
    //   338: aconst_null
    //   339: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   342: aload_0
    //   343: aconst_null
    //   344: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   347: aload_0
    //   348: getfield 180	com/alipay/sdk/util/e:e	Z
    //   351: ifeq +16 -> 367
    //   354: aload_0
    //   355: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   358: iconst_0
    //   359: invokevirtual 184	android/app/Activity:setRequestedOrientation	(I)V
    //   362: aload_0
    //   363: iconst_0
    //   364: putfield 180	com/alipay/sdk/util/e:e	Z
    //   367: ldc 11
    //   369: areturn
    //   370: aload_0
    //   371: getfield 49	com/alipay/sdk/util/e:f	Lcom/alipay/sdk/util/e$a;
    //   374: ifnull +12 -> 386
    //   377: aload_0
    //   378: getfield 49	com/alipay/sdk/util/e:f	Lcom/alipay/sdk/util/e$a;
    //   381: invokeinterface 186 1 0
    //   386: aload_0
    //   387: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   390: invokevirtual 189	android/app/Activity:getRequestedOrientation	()I
    //   393: ifne +16 -> 409
    //   396: aload_0
    //   397: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   400: iconst_1
    //   401: invokevirtual 184	android/app/Activity:setRequestedOrientation	(I)V
    //   404: aload_0
    //   405: iconst_1
    //   406: putfield 180	com/alipay/sdk/util/e:e	Z
    //   409: aload_0
    //   410: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   413: aload_0
    //   414: getfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   417: invokeinterface 193 2 0
    //   422: aload_0
    //   423: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   426: aload_1
    //   427: invokeinterface 197 2 0
    //   432: astore_1
    //   433: aload_0
    //   434: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   437: aload_0
    //   438: getfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   441: invokeinterface 200 2 0
    //   446: aload_0
    //   447: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   450: aload_0
    //   451: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   454: invokevirtual 178	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   457: aload_0
    //   458: aconst_null
    //   459: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   462: aload_0
    //   463: aconst_null
    //   464: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   467: aload_0
    //   468: aconst_null
    //   469: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   472: aload_0
    //   473: getfield 180	com/alipay/sdk/util/e:e	Z
    //   476: ifeq +16 -> 492
    //   479: aload_0
    //   480: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   483: iconst_0
    //   484: invokevirtual 184	android/app/Activity:setRequestedOrientation	(I)V
    //   487: aload_0
    //   488: iconst_0
    //   489: putfield 180	com/alipay/sdk/util/e:e	Z
    //   492: aload_1
    //   493: areturn
    //   494: astore_1
    //   495: goto +64 -> 559
    //   498: astore_1
    //   499: ldc -89
    //   501: ldc -54
    //   503: aload_1
    //   504: invokestatic 205	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   507: invokestatic 209	com/alipay/sdk/app/h:a	()Ljava/lang/String;
    //   510: astore_1
    //   511: aload_0
    //   512: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   515: aload_0
    //   516: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   519: invokevirtual 178	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   522: aload_0
    //   523: aconst_null
    //   524: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   527: aload_0
    //   528: aconst_null
    //   529: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   532: aload_0
    //   533: aconst_null
    //   534: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   537: aload_0
    //   538: getfield 180	com/alipay/sdk/util/e:e	Z
    //   541: ifeq +16 -> 557
    //   544: aload_0
    //   545: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   548: iconst_0
    //   549: invokevirtual 184	android/app/Activity:setRequestedOrientation	(I)V
    //   552: aload_0
    //   553: iconst_0
    //   554: putfield 180	com/alipay/sdk/util/e:e	Z
    //   557: aload_1
    //   558: areturn
    //   559: aload_0
    //   560: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   563: aload_0
    //   564: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   567: invokevirtual 178	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   570: aload_0
    //   571: aconst_null
    //   572: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   575: aload_0
    //   576: aconst_null
    //   577: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   580: aload_0
    //   581: aconst_null
    //   582: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   585: aload_0
    //   586: getfield 180	com/alipay/sdk/util/e:e	Z
    //   589: ifeq +16 -> 605
    //   592: aload_0
    //   593: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   596: iconst_0
    //   597: invokevirtual 184	android/app/Activity:setRequestedOrientation	(I)V
    //   600: aload_0
    //   601: iconst_0
    //   602: putfield 180	com/alipay/sdk/util/e:e	Z
    //   605: aload_1
    //   606: athrow
    //   607: astore_1
    //   608: aload_2
    //   609: monitorexit
    //   610: aload_1
    //   611: athrow
    //   612: astore_1
    //   613: ldc -89
    //   615: ldc -45
    //   617: aload_1
    //   618: invokestatic 205	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   621: ldc 11
    //   623: areturn
    //   624: astore 6
    //   626: goto -568 -> 58
    //   629: astore_1
    //   630: goto -298 -> 332
    //   633: astore_2
    //   634: goto -177 -> 457
    //   637: astore_2
    //   638: goto -116 -> 522
    //   641: astore_2
    //   642: goto -72 -> 570
    //   645: iconst_0
    //   646: istore 4
    //   648: goto -496 -> 152
    //   651: iload_3
    //   652: iconst_1
    //   653: iadd
    //   654: istore_3
    //   655: goto -557 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	658	0	this	e
    //   0	658	1	paramString	String
    //   0	658	2	paramIntent	Intent
    //   97	558	3	i	int
    //   128	519	4	j	int
    //   7	267	5	str	String
    //   37	277	6	localObject	Object
    //   624	1	6	localInterruptedException	InterruptedException
    //   118	127	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   60	96	494	finally
    //   98	130	494	finally
    //   157	192	494	finally
    //   192	200	494	finally
    //   203	253	494	finally
    //   256	321	494	finally
    //   370	386	494	finally
    //   386	409	494	finally
    //   409	446	494	finally
    //   499	511	494	finally
    //   60	96	498	java/lang/Throwable
    //   98	130	498	java/lang/Throwable
    //   157	192	498	java/lang/Throwable
    //   192	200	498	java/lang/Throwable
    //   203	253	498	java/lang/Throwable
    //   256	321	498	java/lang/Throwable
    //   370	386	498	java/lang/Throwable
    //   386	409	498	java/lang/Throwable
    //   409	446	498	java/lang/Throwable
    //   33	39	607	finally
    //   44	58	607	finally
    //   58	60	607	finally
    //   9	26	612	java/lang/Throwable
    //   44	58	624	java/lang/InterruptedException
    //   321	332	629	java/lang/Throwable
    //   446	457	633	java/lang/Throwable
    //   511	522	637	java/lang/Throwable
    //   559	570	641	java/lang/Throwable
  }
  
  private void a()
  {
    this.a = null;
  }
  
  public final String a(String paramString)
  {
    try
    {
      Object localObject = k.a(this.a, "com.eg.android.AlipayGphone");
      if (localObject != null)
      {
        String str = k.a(((k.a)localObject).a);
        if ((str != null) && (!TextUtils.equals(str, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
        {
          a.a("biz", "ClientSignError", str);
          return "failed";
        }
      }
      int i = ((k.a)localObject).b;
      if (i > 78) {
        try
        {
          localObject = new Intent();
          ((Intent)localObject).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
          this.a.startActivity((Intent)localObject);
          Thread.sleep(150L);
        }
        catch (Throwable localThrowable1)
        {
          localThrowable1.printStackTrace();
        }
      }
      localIntent = new Intent();
    }
    catch (Throwable localThrowable2)
    {
      localThrowable2.printStackTrace();
    }
    Intent localIntent;
    localIntent.setPackage("com.eg.android.AlipayGphone");
    localIntent.setAction("com.eg.android.AlipayGphone.IAlixPay");
    return a(paramString, localIntent);
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}
