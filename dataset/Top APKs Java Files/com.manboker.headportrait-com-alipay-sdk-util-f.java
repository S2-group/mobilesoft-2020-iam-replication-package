package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import java.util.Iterator;
import java.util.List;

public class f
{
  public Activity a;
  private IAlixPay b;
  private final Object c = IAlixPay.class;
  private boolean d = false;
  private boolean e;
  private String f;
  private String g;
  private String h = null;
  private ServiceConnection i = new h(this);
  private IRemoteServiceCallback j = new i(this);
  
  public f(Activity paramActivity)
  {
    this.a = paramActivity;
  }
  
  /* Error */
  private String a(String paramString, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: putfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   5: aload_0
    //   6: getfield 32	com/alipay/sdk/util/f:d	Z
    //   9: ifeq +8 -> 17
    //   12: ldc 60
    //   14: astore_1
    //   15: aload_1
    //   16: areturn
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 32	com/alipay/sdk/util/f:d	Z
    //   22: aload_0
    //   23: aload_0
    //   24: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   27: invokestatic 65	com/alipay/sdk/util/j:f	(Landroid/content/Context;)Ljava/lang/String;
    //   30: putfield 67	com/alipay/sdk/util/f:f	Ljava/lang/String;
    //   33: aload_0
    //   34: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   37: invokevirtual 73	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   40: aload_2
    //   41: aload_0
    //   42: getfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   45: iconst_1
    //   46: invokevirtual 79	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   49: pop
    //   50: aload_0
    //   51: getfield 30	com/alipay/sdk/util/f:c	Ljava/lang/Object;
    //   54: astore_2
    //   55: aload_2
    //   56: monitorenter
    //   57: aload_0
    //   58: getfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   61: astore_3
    //   62: aload_3
    //   63: ifnonnull +13 -> 76
    //   66: aload_0
    //   67: getfield 30	com/alipay/sdk/util/f:c	Ljava/lang/Object;
    //   70: ldc2_w 80
    //   73: invokevirtual 85	java/lang/Object:wait	(J)V
    //   76: aload_2
    //   77: monitorexit
    //   78: aload_0
    //   79: getfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   82: ifnonnull +236 -> 318
    //   85: aload_0
    //   86: aload_0
    //   87: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   90: invokestatic 65	com/alipay/sdk/util/j:f	(Landroid/content/Context;)Ljava/lang/String;
    //   93: putfield 87	com/alipay/sdk/util/f:g	Ljava/lang/String;
    //   96: aload_0
    //   97: new 89	java/lang/StringBuilder
    //   100: dup
    //   101: ldc 91
    //   103: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   106: aload_0
    //   107: getfield 67	com/alipay/sdk/util/f:f	Ljava/lang/String;
    //   110: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: ldc 100
    //   115: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload_0
    //   119: getfield 87	com/alipay/sdk/util/f:g	Ljava/lang/String;
    //   122: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: ldc 102
    //   127: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: getstatic 107	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   133: ldc 109
    //   135: ldc 111
    //   137: invokevirtual 117	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   140: ldc 119
    //   142: ldc 121
    //   144: invokevirtual 117	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   147: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: ldc 102
    //   152: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: getstatic 124	android/os/Build:MODEL	Ljava/lang/String;
    //   158: ldc 109
    //   160: ldc 111
    //   162: invokevirtual 117	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   165: ldc 119
    //   167: ldc 111
    //   169: invokevirtual 117	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   172: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: ldc 102
    //   177: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload_0
    //   181: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   184: invokestatic 129	com/alipay/sdk/util/b:a	(Landroid/content/Context;)Lcom/alipay/sdk/util/b;
    //   187: invokevirtual 132	com/alipay/sdk/util/b:b	()Ljava/lang/String;
    //   190: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: putfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   199: aload_0
    //   200: getfield 67	com/alipay/sdk/util/f:f	Ljava/lang/String;
    //   203: ifnonnull +10 -> 213
    //   206: aload_0
    //   207: getfield 87	com/alipay/sdk/util/f:g	Ljava/lang/String;
    //   210: ifnonnull +39 -> 249
    //   213: aload_0
    //   214: new 89	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   221: aload_0
    //   222: getfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   225: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: ldc 102
    //   230: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload_0
    //   234: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   237: invokestatic 138	com/alipay/sdk/util/j:g	(Landroid/content/Context;)Ljava/lang/String;
    //   240: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: putfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   249: aload_0
    //   250: getfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   253: invokestatic 142	com/alipay/sdk/util/d:a	(Ljava/lang/String;)V
    //   256: ldc -112
    //   258: astore_1
    //   259: aload_0
    //   260: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   263: aload_0
    //   264: getfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   267: invokevirtual 148	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   270: aload_0
    //   271: aconst_null
    //   272: putfield 46	com/alipay/sdk/util/f:j	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   275: aload_0
    //   276: aconst_null
    //   277: putfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   280: aload_0
    //   281: aconst_null
    //   282: putfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   285: aload_0
    //   286: iconst_0
    //   287: putfield 32	com/alipay/sdk/util/f:d	Z
    //   290: aload_0
    //   291: getfield 150	com/alipay/sdk/util/f:e	Z
    //   294: ifeq -279 -> 15
    //   297: aload_0
    //   298: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   301: iconst_0
    //   302: invokevirtual 154	android/app/Activity:setRequestedOrientation	(I)V
    //   305: aload_0
    //   306: iconst_0
    //   307: putfield 150	com/alipay/sdk/util/f:e	Z
    //   310: ldc -112
    //   312: areturn
    //   313: astore_1
    //   314: aload_2
    //   315: monitorexit
    //   316: aload_1
    //   317: athrow
    //   318: aload_0
    //   319: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   322: invokevirtual 158	android/app/Activity:getRequestedOrientation	()I
    //   325: ifne +16 -> 341
    //   328: aload_0
    //   329: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   332: iconst_1
    //   333: invokevirtual 154	android/app/Activity:setRequestedOrientation	(I)V
    //   336: aload_0
    //   337: iconst_1
    //   338: putfield 150	com/alipay/sdk/util/f:e	Z
    //   341: aload_0
    //   342: getfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   345: aload_0
    //   346: getfield 46	com/alipay/sdk/util/f:j	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   349: invokeinterface 162 2 0
    //   354: aload_0
    //   355: getfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   358: aload_1
    //   359: invokeinterface 166 2 0
    //   364: astore_2
    //   365: aload_0
    //   366: getfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   369: aload_0
    //   370: getfield 46	com/alipay/sdk/util/f:j	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   373: invokeinterface 169 2 0
    //   378: aload_0
    //   379: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   382: aload_0
    //   383: getfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   386: invokevirtual 148	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   389: aload_0
    //   390: aconst_null
    //   391: putfield 46	com/alipay/sdk/util/f:j	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   394: aload_0
    //   395: aconst_null
    //   396: putfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   399: aload_0
    //   400: aconst_null
    //   401: putfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   404: aload_0
    //   405: iconst_0
    //   406: putfield 32	com/alipay/sdk/util/f:d	Z
    //   409: aload_2
    //   410: astore_1
    //   411: aload_0
    //   412: getfield 150	com/alipay/sdk/util/f:e	Z
    //   415: ifeq -400 -> 15
    //   418: aload_0
    //   419: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   422: iconst_0
    //   423: invokevirtual 154	android/app/Activity:setRequestedOrientation	(I)V
    //   426: aload_0
    //   427: iconst_0
    //   428: putfield 150	com/alipay/sdk/util/f:e	Z
    //   431: aload_2
    //   432: areturn
    //   433: astore_1
    //   434: aload_0
    //   435: new 89	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   442: aload_0
    //   443: getfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   446: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: ldc -85
    //   451: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: aload_1
    //   455: invokevirtual 174	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   458: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   464: putfield 34	com/alipay/sdk/util/f:h	Ljava/lang/String;
    //   467: aload_1
    //   468: invokevirtual 177	java/lang/Throwable:printStackTrace	()V
    //   471: ldc -112
    //   473: astore_1
    //   474: aload_0
    //   475: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   478: aload_0
    //   479: getfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   482: invokevirtual 148	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   485: aload_0
    //   486: aconst_null
    //   487: putfield 46	com/alipay/sdk/util/f:j	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   490: aload_0
    //   491: aconst_null
    //   492: putfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   495: aload_0
    //   496: aconst_null
    //   497: putfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   500: aload_0
    //   501: iconst_0
    //   502: putfield 32	com/alipay/sdk/util/f:d	Z
    //   505: aload_0
    //   506: getfield 150	com/alipay/sdk/util/f:e	Z
    //   509: ifeq -494 -> 15
    //   512: aload_0
    //   513: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   516: iconst_0
    //   517: invokevirtual 154	android/app/Activity:setRequestedOrientation	(I)V
    //   520: aload_0
    //   521: iconst_0
    //   522: putfield 150	com/alipay/sdk/util/f:e	Z
    //   525: ldc -112
    //   527: areturn
    //   528: astore_1
    //   529: aload_0
    //   530: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   533: aload_0
    //   534: getfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   537: invokevirtual 148	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   540: aload_0
    //   541: aconst_null
    //   542: putfield 46	com/alipay/sdk/util/f:j	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   545: aload_0
    //   546: aconst_null
    //   547: putfield 41	com/alipay/sdk/util/f:i	Landroid/content/ServiceConnection;
    //   550: aload_0
    //   551: aconst_null
    //   552: putfield 52	com/alipay/sdk/util/f:b	Lcom/alipay/android/app/IAlixPay;
    //   555: aload_0
    //   556: iconst_0
    //   557: putfield 32	com/alipay/sdk/util/f:d	Z
    //   560: aload_0
    //   561: getfield 150	com/alipay/sdk/util/f:e	Z
    //   564: ifeq +16 -> 580
    //   567: aload_0
    //   568: getfield 48	com/alipay/sdk/util/f:a	Landroid/app/Activity;
    //   571: iconst_0
    //   572: invokevirtual 154	android/app/Activity:setRequestedOrientation	(I)V
    //   575: aload_0
    //   576: iconst_0
    //   577: putfield 150	com/alipay/sdk/util/f:e	Z
    //   580: aload_1
    //   581: athrow
    //   582: astore_2
    //   583: goto -43 -> 540
    //   586: astore_2
    //   587: goto -102 -> 485
    //   590: astore_1
    //   591: goto -202 -> 389
    //   594: astore_2
    //   595: goto -325 -> 270
    //   598: astore_3
    //   599: goto -523 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	602	0	this	f
    //   0	602	1	paramString	String
    //   0	602	2	paramIntent	Intent
    //   61	2	3	localIAlixPay	IAlixPay
    //   598	1	3	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   57	62	313	finally
    //   66	76	313	finally
    //   76	78	313	finally
    //   78	213	433	java/lang/Throwable
    //   213	249	433	java/lang/Throwable
    //   249	256	433	java/lang/Throwable
    //   318	341	433	java/lang/Throwable
    //   341	378	433	java/lang/Throwable
    //   78	213	528	finally
    //   213	249	528	finally
    //   249	256	528	finally
    //   318	341	528	finally
    //   341	378	528	finally
    //   434	471	528	finally
    //   529	540	582	java/lang/Throwable
    //   474	485	586	java/lang/Throwable
    //   378	389	590	java/lang/Throwable
    //   259	270	594	java/lang/Throwable
    //   66	76	598	java/lang/InterruptedException
  }
  
  public final String a(String paramString)
  {
    try
    {
      localObject1 = this.a.getPackageManager().getInstalledPackages(64).iterator();
      do
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject3 = (PackageInfo)((Iterator)localObject1).next();
      } while (!((PackageInfo)localObject3).packageName.equals("com.eg.android.AlipayGphone"));
      localObject1 = new k();
      ((k)localObject1).a = localObject3.signatures[0].toByteArray();
      ((k)localObject1).b = ((PackageInfo)localObject3).versionCode;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        Object localObject1;
        Object localObject3;
        int k;
        localThrowable2.printStackTrace();
        continue;
        Object localObject2 = null;
      }
    }
    if (localObject1 != null)
    {
      localObject3 = j.a(((k)localObject1).a);
      if ((localObject3 != null) && (!TextUtils.equals((CharSequence)localObject3, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
      {
        d.a("fake#" + b.a(this.a).b());
        return "failed";
      }
    }
    k = ((k)localObject1).b;
    if (k > 78) {}
    try
    {
      localObject1 = new Intent();
      ((Intent)localObject1).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
      this.a.startActivity((Intent)localObject1);
      Thread.sleep(150L);
      localObject1 = new Intent();
      ((Intent)localObject1).setPackage("com.eg.android.AlipayGphone");
      ((Intent)localObject1).setAction("com.eg.android.AlipayGphone.IAlixPay");
      return a(paramString, (Intent)localObject1);
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        localThrowable1.printStackTrace();
      }
    }
  }
}
