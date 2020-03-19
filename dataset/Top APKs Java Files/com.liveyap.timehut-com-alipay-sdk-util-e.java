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
  private String a(String paramString, Intent arg2)
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
    //   64: ifnonnull +378 -> 442
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
    //   105: if_icmpge +239 -> 344
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
    //   134: ifne +83 -> 217
    //   137: iload 4
    //   139: sipush 128
    //   142: iand
    //   143: ifne +74 -> 217
    //   146: iconst_1
    //   147: istore 4
    //   149: iload 4
    //   151: ifeq +40 -> 191
    //   154: aload 7
    //   156: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   159: ldc 127
    //   161: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   164: ifeq +59 -> 223
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
    //   199: ldc -109
    //   201: ldc -107
    //   203: aload_1
    //   204: invokestatic 154	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   207: ldc 11
    //   209: astore_1
    //   210: aload_1
    //   211: areturn
    //   212: astore_1
    //   213: aload_2
    //   214: monitorexit
    //   215: aload_1
    //   216: athrow
    //   217: iconst_0
    //   218: istore 4
    //   220: goto -71 -> 149
    //   223: aload 7
    //   225: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   228: ldc -100
    //   230: invokevirtual 160	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   233: ifne -42 -> 191
    //   236: aload 7
    //   238: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   241: ldc -94
    //   243: invokevirtual 166	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   246: ifne -55 -> 191
    //   249: aload 7
    //   251: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   254: ldc -88
    //   256: invokevirtual 166	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   259: ifne -68 -> 191
    //   262: aload 6
    //   264: aload 7
    //   266: getfield 125	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   269: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: ldc -111
    //   274: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: pop
    //   278: goto -87 -> 191
    //   281: astore_1
    //   282: ldc -109
    //   284: ldc -86
    //   286: aload_1
    //   287: invokestatic 154	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   290: invokestatic 175	com/alipay/sdk/app/h:a	()Ljava/lang/String;
    //   293: astore_2
    //   294: aload_0
    //   295: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   298: aload_0
    //   299: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   302: invokevirtual 179	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   305: aload_0
    //   306: aconst_null
    //   307: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   310: aload_0
    //   311: aconst_null
    //   312: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   315: aload_0
    //   316: aconst_null
    //   317: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   320: aload_2
    //   321: astore_1
    //   322: aload_0
    //   323: getfield 181	com/alipay/sdk/util/e:e	Z
    //   326: ifeq -116 -> 210
    //   329: aload_0
    //   330: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   333: iconst_0
    //   334: invokevirtual 185	android/app/Activity:setRequestedOrientation	(I)V
    //   337: aload_0
    //   338: iconst_0
    //   339: putfield 181	com/alipay/sdk/util/e:e	Z
    //   342: aload_2
    //   343: areturn
    //   344: aload 6
    //   346: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: astore_2
    //   350: ldc -109
    //   352: ldc -66
    //   354: new 100	java/lang/StringBuilder
    //   357: dup
    //   358: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   361: aload 5
    //   363: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: ldc -64
    //   368: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: aload_1
    //   372: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: ldc -64
    //   377: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: aload_2
    //   381: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: invokestatic 195	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   390: ldc 11
    //   392: astore_1
    //   393: aload_0
    //   394: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   397: aload_0
    //   398: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   401: invokevirtual 179	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   404: aload_0
    //   405: aconst_null
    //   406: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   409: aload_0
    //   410: aconst_null
    //   411: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   414: aload_0
    //   415: aconst_null
    //   416: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   419: aload_0
    //   420: getfield 181	com/alipay/sdk/util/e:e	Z
    //   423: ifeq -213 -> 210
    //   426: aload_0
    //   427: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   430: iconst_0
    //   431: invokevirtual 185	android/app/Activity:setRequestedOrientation	(I)V
    //   434: aload_0
    //   435: iconst_0
    //   436: putfield 181	com/alipay/sdk/util/e:e	Z
    //   439: ldc 11
    //   441: areturn
    //   442: aload_0
    //   443: getfield 49	com/alipay/sdk/util/e:f	Lcom/alipay/sdk/util/e$a;
    //   446: ifnull +12 -> 458
    //   449: aload_0
    //   450: getfield 49	com/alipay/sdk/util/e:f	Lcom/alipay/sdk/util/e$a;
    //   453: invokeinterface 197 1 0
    //   458: aload_0
    //   459: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   462: invokevirtual 200	android/app/Activity:getRequestedOrientation	()I
    //   465: ifne +16 -> 481
    //   468: aload_0
    //   469: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   472: iconst_1
    //   473: invokevirtual 185	android/app/Activity:setRequestedOrientation	(I)V
    //   476: aload_0
    //   477: iconst_1
    //   478: putfield 181	com/alipay/sdk/util/e:e	Z
    //   481: aload_0
    //   482: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   485: aload_0
    //   486: getfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   489: invokeinterface 204 2 0
    //   494: aload_0
    //   495: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   498: aload_1
    //   499: invokeinterface 208 2 0
    //   504: astore_2
    //   505: aload_0
    //   506: getfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   509: aload_0
    //   510: getfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   513: invokeinterface 211 2 0
    //   518: aload_0
    //   519: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   522: aload_0
    //   523: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   526: invokevirtual 179	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   529: aload_0
    //   530: aconst_null
    //   531: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   534: aload_0
    //   535: aconst_null
    //   536: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   539: aload_0
    //   540: aconst_null
    //   541: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   544: aload_2
    //   545: astore_1
    //   546: aload_0
    //   547: getfield 181	com/alipay/sdk/util/e:e	Z
    //   550: ifeq -340 -> 210
    //   553: aload_0
    //   554: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   557: iconst_0
    //   558: invokevirtual 185	android/app/Activity:setRequestedOrientation	(I)V
    //   561: aload_0
    //   562: iconst_0
    //   563: putfield 181	com/alipay/sdk/util/e:e	Z
    //   566: aload_2
    //   567: areturn
    //   568: astore_1
    //   569: aload_0
    //   570: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   573: aload_0
    //   574: getfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   577: invokevirtual 179	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   580: aload_0
    //   581: aconst_null
    //   582: putfield 45	com/alipay/sdk/util/e:h	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   585: aload_0
    //   586: aconst_null
    //   587: putfield 40	com/alipay/sdk/util/e:g	Landroid/content/ServiceConnection;
    //   590: aload_0
    //   591: aconst_null
    //   592: putfield 53	com/alipay/sdk/util/e:c	Lcom/alipay/android/app/IAlixPay;
    //   595: aload_0
    //   596: getfield 181	com/alipay/sdk/util/e:e	Z
    //   599: ifeq +16 -> 615
    //   602: aload_0
    //   603: getfield 47	com/alipay/sdk/util/e:a	Landroid/app/Activity;
    //   606: iconst_0
    //   607: invokevirtual 185	android/app/Activity:setRequestedOrientation	(I)V
    //   610: aload_0
    //   611: iconst_0
    //   612: putfield 181	com/alipay/sdk/util/e:e	Z
    //   615: aload_1
    //   616: athrow
    //   617: astore_2
    //   618: goto -38 -> 580
    //   621: astore_1
    //   622: goto -317 -> 305
    //   625: astore_1
    //   626: goto -97 -> 529
    //   629: astore_2
    //   630: goto -226 -> 404
    //   633: astore 6
    //   635: goto -577 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	638	0	this	e
    //   0	638	1	paramString	String
    //   97	98	3	i	int
    //   128	91	4	j	int
    //   7	355	5	str	String
    //   37	308	6	localObject	Object
    //   633	1	6	localInterruptedException	InterruptedException
    //   118	147	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   9	26	198	java/lang/Throwable
    //   33	39	212	finally
    //   44	58	212	finally
    //   58	60	212	finally
    //   60	96	281	java/lang/Throwable
    //   98	130	281	java/lang/Throwable
    //   154	191	281	java/lang/Throwable
    //   223	278	281	java/lang/Throwable
    //   344	390	281	java/lang/Throwable
    //   442	458	281	java/lang/Throwable
    //   458	481	281	java/lang/Throwable
    //   481	518	281	java/lang/Throwable
    //   60	96	568	finally
    //   98	130	568	finally
    //   154	191	568	finally
    //   223	278	568	finally
    //   282	294	568	finally
    //   344	390	568	finally
    //   442	458	568	finally
    //   458	481	568	finally
    //   481	518	568	finally
    //   569	580	617	java/lang/Throwable
    //   294	305	621	java/lang/Throwable
    //   518	529	625	java/lang/Throwable
    //   393	404	629	java/lang/Throwable
    //   44	58	633	java/lang/InterruptedException
  }
  
  private void a()
  {
    this.a = null;
  }
  
  public final String a(String paramString)
  {
    for (;;)
    {
      try
      {
        localObject = k.a(this.a, "com.eg.android.AlipayGphone");
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
        this.a.startActivity((Intent)localObject);
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
  
  public static abstract interface a
  {
    public abstract void a();
  }
}
