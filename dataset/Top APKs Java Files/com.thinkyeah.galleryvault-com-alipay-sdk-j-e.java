package com.alipay.sdk.j;

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
import com.alipay.sdk.app.o;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public class e
{
  private WeakReference a;
  private IAlixPay b;
  private Object c = IAlixPay.class;
  private boolean d = false;
  private boolean e;
  private ServiceConnection f = new f(this);
  private IRemoteServiceCallback g = new g(this);
  
  public e(Activity paramActivity)
  {
    this.a = new WeakReference(paramActivity);
  }
  
  /* Error */
  private String a(String paramString, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/alipay/sdk/j/e:d	Z
    //   4: ifeq +6 -> 10
    //   7: ldc 59
    //   9: areturn
    //   10: aload_0
    //   11: iconst_1
    //   12: putfield 28	com/alipay/sdk/j/e:d	Z
    //   15: iconst_0
    //   16: istore_3
    //   17: iload_3
    //   18: iconst_3
    //   19: if_icmpge +93 -> 112
    //   22: aload_0
    //   23: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   26: ifnonnull +26 -> 52
    //   29: aload_0
    //   30: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   33: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   36: checkcast 65	android/app/Activity
    //   39: invokevirtual 69	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   42: aload_2
    //   43: aload_0
    //   44: getfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   47: iconst_1
    //   48: invokevirtual 75	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   51: pop
    //   52: aload_0
    //   53: getfield 26	com/alipay/sdk/j/e:c	Ljava/lang/Object;
    //   56: astore 4
    //   58: aload 4
    //   60: monitorenter
    //   61: aload_0
    //   62: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   65: astore 5
    //   67: aload 5
    //   69: ifnonnull +13 -> 82
    //   72: aload_0
    //   73: getfield 26	com/alipay/sdk/j/e:c	Ljava/lang/Object;
    //   76: ldc2_w 76
    //   79: invokevirtual 81	java/lang/Object:wait	(J)V
    //   82: aload 4
    //   84: monitorexit
    //   85: aload_0
    //   86: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   89: ifnonnull +23 -> 112
    //   92: iload_3
    //   93: iconst_1
    //   94: iadd
    //   95: istore_3
    //   96: goto -79 -> 17
    //   99: astore_1
    //   100: aload 4
    //   102: monitorexit
    //   103: ldc 83
    //   105: areturn
    //   106: astore_1
    //   107: aload 4
    //   109: monitorexit
    //   110: aload_1
    //   111: athrow
    //   112: aload_0
    //   113: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   116: ifnonnull +69 -> 185
    //   119: aload_0
    //   120: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   123: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   126: checkcast 65	android/app/Activity
    //   129: aload_0
    //   130: getfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   133: invokevirtual 87	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   136: aload_0
    //   137: aconst_null
    //   138: putfield 40	com/alipay/sdk/j/e:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   141: aload_0
    //   142: aconst_null
    //   143: putfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   146: aload_0
    //   147: aconst_null
    //   148: putfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   151: aload_0
    //   152: iconst_0
    //   153: putfield 28	com/alipay/sdk/j/e:d	Z
    //   156: aload_0
    //   157: getfield 89	com/alipay/sdk/j/e:e	Z
    //   160: ifeq +22 -> 182
    //   163: aload_0
    //   164: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   167: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   170: checkcast 65	android/app/Activity
    //   173: iconst_0
    //   174: invokevirtual 93	android/app/Activity:setRequestedOrientation	(I)V
    //   177: aload_0
    //   178: iconst_0
    //   179: putfield 89	com/alipay/sdk/j/e:e	Z
    //   182: ldc 83
    //   184: areturn
    //   185: aload_0
    //   186: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   189: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   192: checkcast 65	android/app/Activity
    //   195: invokevirtual 97	android/app/Activity:getRequestedOrientation	()I
    //   198: ifne +22 -> 220
    //   201: aload_0
    //   202: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   205: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   208: checkcast 65	android/app/Activity
    //   211: iconst_1
    //   212: invokevirtual 93	android/app/Activity:setRequestedOrientation	(I)V
    //   215: aload_0
    //   216: iconst_1
    //   217: putfield 89	com/alipay/sdk/j/e:e	Z
    //   220: aload_0
    //   221: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   224: aload_0
    //   225: getfield 40	com/alipay/sdk/j/e:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   228: invokeinterface 101 2 0
    //   233: aload_0
    //   234: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   237: aload_1
    //   238: invokeinterface 105 2 0
    //   243: astore_1
    //   244: aload_0
    //   245: getfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   248: aload_0
    //   249: getfield 40	com/alipay/sdk/j/e:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   252: invokeinterface 108 2 0
    //   257: aload_0
    //   258: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   261: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   264: checkcast 65	android/app/Activity
    //   267: aload_0
    //   268: getfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   271: invokevirtual 87	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   274: aload_0
    //   275: aconst_null
    //   276: putfield 40	com/alipay/sdk/j/e:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   279: aload_0
    //   280: aconst_null
    //   281: putfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   284: aload_0
    //   285: aconst_null
    //   286: putfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   289: aload_0
    //   290: iconst_0
    //   291: putfield 28	com/alipay/sdk/j/e:d	Z
    //   294: aload_0
    //   295: getfield 89	com/alipay/sdk/j/e:e	Z
    //   298: ifeq +22 -> 320
    //   301: aload_0
    //   302: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   305: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   308: checkcast 65	android/app/Activity
    //   311: iconst_0
    //   312: invokevirtual 93	android/app/Activity:setRequestedOrientation	(I)V
    //   315: aload_0
    //   316: iconst_0
    //   317: putfield 89	com/alipay/sdk/j/e:e	Z
    //   320: aload_1
    //   321: areturn
    //   322: astore_1
    //   323: aload_0
    //   324: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   327: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   330: checkcast 65	android/app/Activity
    //   333: aload_0
    //   334: getfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   337: invokevirtual 87	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   340: aload_0
    //   341: aconst_null
    //   342: putfield 40	com/alipay/sdk/j/e:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   345: aload_0
    //   346: aconst_null
    //   347: putfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   350: aload_0
    //   351: aconst_null
    //   352: putfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   355: aload_0
    //   356: iconst_0
    //   357: putfield 28	com/alipay/sdk/j/e:d	Z
    //   360: aload_0
    //   361: getfield 89	com/alipay/sdk/j/e:e	Z
    //   364: ifeq +22 -> 386
    //   367: aload_0
    //   368: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   371: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   374: checkcast 65	android/app/Activity
    //   377: iconst_0
    //   378: invokevirtual 93	android/app/Activity:setRequestedOrientation	(I)V
    //   381: aload_0
    //   382: iconst_0
    //   383: putfield 89	com/alipay/sdk/j/e:e	Z
    //   386: ldc 83
    //   388: areturn
    //   389: astore_1
    //   390: aload_0
    //   391: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   394: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   397: checkcast 65	android/app/Activity
    //   400: aload_0
    //   401: getfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   404: invokevirtual 87	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   407: aload_0
    //   408: aconst_null
    //   409: putfield 40	com/alipay/sdk/j/e:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   412: aload_0
    //   413: aconst_null
    //   414: putfield 35	com/alipay/sdk/j/e:f	Landroid/content/ServiceConnection;
    //   417: aload_0
    //   418: aconst_null
    //   419: putfield 51	com/alipay/sdk/j/e:b	Lcom/alipay/android/app/IAlixPay;
    //   422: aload_0
    //   423: iconst_0
    //   424: putfield 28	com/alipay/sdk/j/e:d	Z
    //   427: aload_0
    //   428: getfield 89	com/alipay/sdk/j/e:e	Z
    //   431: ifeq +22 -> 453
    //   434: aload_0
    //   435: getfield 47	com/alipay/sdk/j/e:a	Ljava/lang/ref/WeakReference;
    //   438: invokevirtual 63	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   441: checkcast 65	android/app/Activity
    //   444: iconst_0
    //   445: invokevirtual 93	android/app/Activity:setRequestedOrientation	(I)V
    //   448: aload_0
    //   449: iconst_0
    //   450: putfield 89	com/alipay/sdk/j/e:e	Z
    //   453: aload_1
    //   454: athrow
    //   455: astore_2
    //   456: goto -49 -> 407
    //   459: astore_1
    //   460: goto -120 -> 340
    //   463: astore_2
    //   464: goto -190 -> 274
    //   467: astore_1
    //   468: goto -332 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	471	0	this	e
    //   0	471	1	paramString	String
    //   0	471	2	paramIntent	Intent
    //   16	80	3	i	int
    //   56	52	4	localObject	Object
    //   65	3	5	localIAlixPay	IAlixPay
    // Exception table:
    //   from	to	target	type
    //   72	82	99	java/lang/InterruptedException
    //   61	67	106	finally
    //   72	82	106	finally
    //   100	103	106	finally
    //   112	119	322	java/lang/Exception
    //   185	220	322	java/lang/Exception
    //   220	257	322	java/lang/Exception
    //   112	119	389	finally
    //   185	220	389	finally
    //   220	257	389	finally
    //   390	407	455	java/lang/Exception
    //   323	340	459	java/lang/Exception
    //   257	274	463	java/lang/Exception
    //   119	136	467	java/lang/Exception
  }
  
  public final String a(String paramString)
  {
    Object localObject = ((Context)this.a.get()).getPackageManager().getInstalledPackages(64).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)((Iterator)localObject).next();
    } while (!localPackageInfo.packageName.equals("com.eg.android.AlipayGphone"));
    for (localObject = localPackageInfo.signatures[0].toByteArray();; localObject = null)
    {
      localObject = h.a((byte[])localObject);
      if ((localObject == null) || (TextUtils.equals((CharSequence)localObject, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649"))) {
        break;
      }
      return o.b();
    }
    localObject = new Intent();
    ((Intent)localObject).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.MspService");
    ((Intent)localObject).setAction("com.eg.android.AlipayGphone.IAlixPay");
    return a(paramString, (Intent)localObject);
  }
}
