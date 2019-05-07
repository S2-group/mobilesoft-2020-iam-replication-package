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
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public class h
{
  public static final String a = "failed";
  private WeakReference<Activity> b;
  private IAlixPay c;
  private final Object d = IAlixPay.class;
  private boolean e = false;
  private boolean f;
  private String g;
  private String h;
  private String i = null;
  private ServiceConnection j = new i(this);
  private IRemoteServiceCallback k = new j(this);
  
  public h(Activity paramActivity)
  {
    this.b = new WeakReference(paramActivity);
  }
  
  /* Error */
  private String a(String paramString, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: putfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   5: aload_0
    //   6: getfield 36	com/alipay/sdk/util/h:e	Z
    //   9: ifeq +6 -> 15
    //   12: ldc 71
    //   14: areturn
    //   15: aload_0
    //   16: iconst_1
    //   17: putfield 36	com/alipay/sdk/util/h:e	Z
    //   20: aload_0
    //   21: aload_0
    //   22: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   25: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   28: checkcast 77	android/content/Context
    //   31: invokestatic 82	com/alipay/sdk/util/k:f	(Landroid/content/Context;)Ljava/lang/String;
    //   34: putfield 84	com/alipay/sdk/util/h:g	Ljava/lang/String;
    //   37: aload_0
    //   38: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   41: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   44: checkcast 86	android/app/Activity
    //   47: invokevirtual 90	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   50: aload_2
    //   51: aload_0
    //   52: getfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   55: iconst_1
    //   56: invokevirtual 94	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   59: pop
    //   60: aload_0
    //   61: getfield 34	com/alipay/sdk/util/h:d	Ljava/lang/Object;
    //   64: astore_2
    //   65: aload_2
    //   66: monitorenter
    //   67: aload_0
    //   68: getfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   71: astore_3
    //   72: aload_3
    //   73: ifnonnull +13 -> 86
    //   76: aload_0
    //   77: getfield 34	com/alipay/sdk/util/h:d	Ljava/lang/Object;
    //   80: ldc2_w 95
    //   83: invokevirtual 100	java/lang/Object:wait	(J)V
    //   86: aload_2
    //   87: monitorexit
    //   88: aload_0
    //   89: getfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   92: ifnonnull +273 -> 365
    //   95: aload_0
    //   96: aload_0
    //   97: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   100: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   103: checkcast 77	android/content/Context
    //   106: invokestatic 82	com/alipay/sdk/util/k:f	(Landroid/content/Context;)Ljava/lang/String;
    //   109: putfield 102	com/alipay/sdk/util/h:h	Ljava/lang/String;
    //   112: aload_0
    //   113: new 104	java/lang/StringBuilder
    //   116: dup
    //   117: ldc 106
    //   119: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   122: aload_0
    //   123: getfield 84	com/alipay/sdk/util/h:g	Ljava/lang/String;
    //   126: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: ldc 115
    //   131: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload_0
    //   135: getfield 102	com/alipay/sdk/util/h:h	Ljava/lang/String;
    //   138: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: ldc 117
    //   143: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: getstatic 122	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   149: ldc 124
    //   151: ldc 126
    //   153: invokevirtual 132	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   156: ldc -122
    //   158: ldc -120
    //   160: invokevirtual 132	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   163: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: ldc 117
    //   168: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: getstatic 139	android/os/Build:MODEL	Ljava/lang/String;
    //   174: ldc 124
    //   176: ldc 126
    //   178: invokevirtual 132	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   181: ldc -122
    //   183: ldc 126
    //   185: invokevirtual 132	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   188: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc 117
    //   193: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: aload_0
    //   197: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   200: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   203: checkcast 77	android/content/Context
    //   206: invokestatic 144	com/alipay/sdk/util/b:a	(Landroid/content/Context;)Lcom/alipay/sdk/util/b;
    //   209: invokevirtual 147	com/alipay/sdk/util/b:b	()Ljava/lang/String;
    //   212: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: putfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   221: aload_0
    //   222: getfield 84	com/alipay/sdk/util/h:g	Ljava/lang/String;
    //   225: ifnonnull +10 -> 235
    //   228: aload_0
    //   229: getfield 102	com/alipay/sdk/util/h:h	Ljava/lang/String;
    //   232: ifnonnull +45 -> 277
    //   235: aload_0
    //   236: new 104	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   243: aload_0
    //   244: getfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   247: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: ldc 117
    //   252: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: aload_0
    //   256: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   259: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   262: checkcast 77	android/content/Context
    //   265: invokestatic 153	com/alipay/sdk/util/k:g	(Landroid/content/Context;)Ljava/lang/String;
    //   268: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: putfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   277: aload_0
    //   278: getfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   281: astore_1
    //   282: aload_0
    //   283: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   286: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   289: pop
    //   290: aload_1
    //   291: invokestatic 157	com/alipay/sdk/util/d:a	(Ljava/lang/String;)V
    //   294: aload_0
    //   295: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   298: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   301: checkcast 86	android/app/Activity
    //   304: aload_0
    //   305: getfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   308: invokevirtual 161	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   311: aload_0
    //   312: aconst_null
    //   313: putfield 50	com/alipay/sdk/util/h:k	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   316: aload_0
    //   317: aconst_null
    //   318: putfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   321: aload_0
    //   322: aconst_null
    //   323: putfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   326: aload_0
    //   327: iconst_0
    //   328: putfield 36	com/alipay/sdk/util/h:e	Z
    //   331: aload_0
    //   332: getfield 163	com/alipay/sdk/util/h:f	Z
    //   335: ifeq +22 -> 357
    //   338: aload_0
    //   339: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   342: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   345: checkcast 86	android/app/Activity
    //   348: iconst_0
    //   349: invokevirtual 167	android/app/Activity:setRequestedOrientation	(I)V
    //   352: aload_0
    //   353: iconst_0
    //   354: putfield 163	com/alipay/sdk/util/h:f	Z
    //   357: ldc 8
    //   359: areturn
    //   360: astore_1
    //   361: aload_2
    //   362: monitorexit
    //   363: aload_1
    //   364: athrow
    //   365: aload_0
    //   366: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   369: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   372: checkcast 86	android/app/Activity
    //   375: invokevirtual 171	android/app/Activity:getRequestedOrientation	()I
    //   378: ifne +22 -> 400
    //   381: aload_0
    //   382: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   385: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   388: checkcast 86	android/app/Activity
    //   391: iconst_1
    //   392: invokevirtual 167	android/app/Activity:setRequestedOrientation	(I)V
    //   395: aload_0
    //   396: iconst_1
    //   397: putfield 163	com/alipay/sdk/util/h:f	Z
    //   400: aload_0
    //   401: getfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   404: aload_0
    //   405: getfield 50	com/alipay/sdk/util/h:k	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   408: invokeinterface 175 2 0
    //   413: aload_0
    //   414: getfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   417: aload_1
    //   418: invokeinterface 179 2 0
    //   423: astore_1
    //   424: aload_0
    //   425: getfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   428: aload_0
    //   429: getfield 50	com/alipay/sdk/util/h:k	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   432: invokeinterface 182 2 0
    //   437: aload_0
    //   438: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   441: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   444: checkcast 86	android/app/Activity
    //   447: aload_0
    //   448: getfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   451: invokevirtual 161	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   454: aload_0
    //   455: aconst_null
    //   456: putfield 50	com/alipay/sdk/util/h:k	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   459: aload_0
    //   460: aconst_null
    //   461: putfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   464: aload_0
    //   465: aconst_null
    //   466: putfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   469: aload_0
    //   470: iconst_0
    //   471: putfield 36	com/alipay/sdk/util/h:e	Z
    //   474: aload_0
    //   475: getfield 163	com/alipay/sdk/util/h:f	Z
    //   478: ifeq +22 -> 500
    //   481: aload_0
    //   482: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   485: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   488: checkcast 86	android/app/Activity
    //   491: iconst_0
    //   492: invokevirtual 167	android/app/Activity:setRequestedOrientation	(I)V
    //   495: aload_0
    //   496: iconst_0
    //   497: putfield 163	com/alipay/sdk/util/h:f	Z
    //   500: aload_1
    //   501: areturn
    //   502: astore_1
    //   503: aload_0
    //   504: new 104	java/lang/StringBuilder
    //   507: dup
    //   508: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   511: aload_0
    //   512: getfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   515: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: ldc -72
    //   520: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: aload_1
    //   524: invokevirtual 187	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   527: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   530: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   533: putfield 38	com/alipay/sdk/util/h:i	Ljava/lang/String;
    //   536: aload_0
    //   537: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   540: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   543: checkcast 86	android/app/Activity
    //   546: aload_0
    //   547: getfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   550: invokevirtual 161	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   553: aload_0
    //   554: aconst_null
    //   555: putfield 50	com/alipay/sdk/util/h:k	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   558: aload_0
    //   559: aconst_null
    //   560: putfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   563: aload_0
    //   564: aconst_null
    //   565: putfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   568: aload_0
    //   569: iconst_0
    //   570: putfield 36	com/alipay/sdk/util/h:e	Z
    //   573: aload_0
    //   574: getfield 163	com/alipay/sdk/util/h:f	Z
    //   577: ifeq +22 -> 599
    //   580: aload_0
    //   581: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   584: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   587: checkcast 86	android/app/Activity
    //   590: iconst_0
    //   591: invokevirtual 167	android/app/Activity:setRequestedOrientation	(I)V
    //   594: aload_0
    //   595: iconst_0
    //   596: putfield 163	com/alipay/sdk/util/h:f	Z
    //   599: ldc 8
    //   601: areturn
    //   602: astore_1
    //   603: aload_0
    //   604: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   607: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   610: checkcast 86	android/app/Activity
    //   613: aload_0
    //   614: getfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   617: invokevirtual 161	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   620: aload_0
    //   621: aconst_null
    //   622: putfield 50	com/alipay/sdk/util/h:k	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   625: aload_0
    //   626: aconst_null
    //   627: putfield 45	com/alipay/sdk/util/h:j	Landroid/content/ServiceConnection;
    //   630: aload_0
    //   631: aconst_null
    //   632: putfield 61	com/alipay/sdk/util/h:c	Lcom/alipay/android/app/IAlixPay;
    //   635: aload_0
    //   636: iconst_0
    //   637: putfield 36	com/alipay/sdk/util/h:e	Z
    //   640: aload_0
    //   641: getfield 163	com/alipay/sdk/util/h:f	Z
    //   644: ifeq +22 -> 666
    //   647: aload_0
    //   648: getfield 57	com/alipay/sdk/util/h:b	Ljava/lang/ref/WeakReference;
    //   651: invokevirtual 75	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   654: checkcast 86	android/app/Activity
    //   657: iconst_0
    //   658: invokevirtual 167	android/app/Activity:setRequestedOrientation	(I)V
    //   661: aload_0
    //   662: iconst_0
    //   663: putfield 163	com/alipay/sdk/util/h:f	Z
    //   666: aload_1
    //   667: athrow
    //   668: astore_2
    //   669: goto -49 -> 620
    //   672: astore_1
    //   673: goto -120 -> 553
    //   676: astore_2
    //   677: goto -223 -> 454
    //   680: astore_1
    //   681: goto -370 -> 311
    //   684: astore_3
    //   685: goto -599 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	688	0	this	h
    //   0	688	1	paramString	String
    //   0	688	2	paramIntent	Intent
    //   71	2	3	localIAlixPay	IAlixPay
    //   684	1	3	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   67	72	360	finally
    //   76	86	360	finally
    //   86	88	360	finally
    //   88	235	502	java/lang/Exception
    //   235	277	502	java/lang/Exception
    //   277	294	502	java/lang/Exception
    //   365	400	502	java/lang/Exception
    //   400	437	502	java/lang/Exception
    //   88	235	602	finally
    //   235	277	602	finally
    //   277	294	602	finally
    //   365	400	602	finally
    //   400	437	602	finally
    //   503	536	602	finally
    //   603	620	668	java/lang/Throwable
    //   536	553	672	java/lang/Throwable
    //   437	454	676	java/lang/Throwable
    //   294	311	680	java/lang/Throwable
    //   76	86	684	java/lang/InterruptedException
  }
  
  public final String a(String paramString)
  {
    Object localObject1 = ((Context)this.b.get()).getPackageManager().getInstalledPackages(64).iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (((PackageInfo)localObject2).packageName.equals("com.eg.android.AlipayGphone"))
      {
        localObject1 = new k.a();
        ((k.a)localObject1).a = localObject2.signatures[0].toByteArray();
        ((k.a)localObject1).b = ((PackageInfo)localObject2).versionCode;
      }
    }
    while (localObject1 != null)
    {
      localObject2 = k.a(((k.a)localObject1).a);
      if ((localObject2 == null) || (TextUtils.equals((CharSequence)localObject2, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649"))) {
        break;
      }
      paramString = "fake#" + b.a((Context)this.b.get()).b();
      this.b.get();
      d.a(paramString);
      return "failed";
      localObject1 = null;
    }
    if (((k.a)localObject1).b > 78) {}
    try
    {
      localObject1 = new Intent();
      ((Intent)localObject1).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
      ((Activity)this.b.get()).startActivity((Intent)localObject1);
      Thread.sleep(150L);
      localObject1 = new Intent();
      ((Intent)localObject1).setPackage("com.eg.android.AlipayGphone");
      ((Intent)localObject1).setAction("com.eg.android.AlipayGphone.IAlixPay");
      return a(paramString, (Intent)localObject1);
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
}
