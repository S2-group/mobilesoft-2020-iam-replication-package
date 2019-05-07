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
import com.alipay.sdk.app.ab;
import java.util.Iterator;
import java.util.List;

public class g
{
  public static final String a = "failed";
  private Activity b;
  private IAlixPay c;
  private Object d = IAlixPay.class;
  private boolean e = false;
  private ServiceConnection f = new h(this);
  private IRemoteServiceCallback g = new i(this);
  
  public g(Activity paramActivity)
  {
    this.b = paramActivity;
  }
  
  /* Error */
  private String a(String paramString, Intent arg2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	com/alipay/sdk/util/g:e	Z
    //   4: ifeq +6 -> 10
    //   7: ldc 55
    //   9: areturn
    //   10: aload_0
    //   11: iconst_1
    //   12: putfield 31	com/alipay/sdk/util/g:e	Z
    //   15: aload_0
    //   16: getfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   19: ifnonnull +20 -> 39
    //   22: aload_0
    //   23: getfield 45	com/alipay/sdk/util/g:b	Landroid/app/Activity;
    //   26: invokevirtual 61	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   29: aload_2
    //   30: aload_0
    //   31: getfield 38	com/alipay/sdk/util/g:f	Landroid/content/ServiceConnection;
    //   34: iconst_1
    //   35: invokevirtual 67	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   38: pop
    //   39: aload_0
    //   40: getfield 29	com/alipay/sdk/util/g:d	Ljava/lang/Object;
    //   43: astore_2
    //   44: aload_2
    //   45: monitorenter
    //   46: aload_0
    //   47: getfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   50: ifnonnull +13 -> 63
    //   53: aload_0
    //   54: getfield 29	com/alipay/sdk/util/g:d	Ljava/lang/Object;
    //   57: ldc2_w 68
    //   60: invokevirtual 73	java/lang/Object:wait	(J)V
    //   63: aload_2
    //   64: monitorexit
    //   65: aload_0
    //   66: getfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   69: ifnonnull +30 -> 99
    //   72: aload_0
    //   73: getfield 45	com/alipay/sdk/util/g:b	Landroid/app/Activity;
    //   76: aload_0
    //   77: getfield 38	com/alipay/sdk/util/g:f	Landroid/content/ServiceConnection;
    //   80: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   83: goto +8 -> 91
    //   86: aload_0
    //   87: aconst_null
    //   88: putfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   91: aload_0
    //   92: iconst_0
    //   93: putfield 31	com/alipay/sdk/util/g:e	Z
    //   96: ldc 8
    //   98: areturn
    //   99: aload_0
    //   100: getfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   103: aload_0
    //   104: getfield 43	com/alipay/sdk/util/g:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   107: invokeinterface 81 2 0
    //   112: aload_0
    //   113: getfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   116: aload_1
    //   117: invokeinterface 85 2 0
    //   122: astore_1
    //   123: aload_0
    //   124: getfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   127: aload_0
    //   128: getfield 43	com/alipay/sdk/util/g:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   131: invokeinterface 88 2 0
    //   136: aload_0
    //   137: aconst_null
    //   138: putfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   141: aload_1
    //   142: astore_2
    //   143: aload_0
    //   144: getfield 45	com/alipay/sdk/util/g:b	Landroid/app/Activity;
    //   147: aload_0
    //   148: getfield 38	com/alipay/sdk/util/g:f	Landroid/content/ServiceConnection;
    //   151: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   154: goto +60 -> 214
    //   157: astore_1
    //   158: aload_2
    //   159: monitorexit
    //   160: aload_1
    //   161: athrow
    //   162: astore_1
    //   163: aload_0
    //   164: getfield 45	com/alipay/sdk/util/g:b	Landroid/app/Activity;
    //   167: aload_0
    //   168: getfield 38	com/alipay/sdk/util/g:f	Landroid/content/ServiceConnection;
    //   171: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   174: goto +8 -> 182
    //   177: aload_0
    //   178: aconst_null
    //   179: putfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   182: aload_0
    //   183: iconst_0
    //   184: putfield 31	com/alipay/sdk/util/g:e	Z
    //   187: aload_1
    //   188: athrow
    //   189: aconst_null
    //   190: astore_1
    //   191: aload_1
    //   192: astore_2
    //   193: aload_0
    //   194: getfield 45	com/alipay/sdk/util/g:b	Landroid/app/Activity;
    //   197: aload_0
    //   198: getfield 38	com/alipay/sdk/util/g:f	Landroid/content/ServiceConnection;
    //   201: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   204: goto +10 -> 214
    //   207: aload_0
    //   208: aconst_null
    //   209: putfield 49	com/alipay/sdk/util/g:c	Lcom/alipay/android/app/IAlixPay;
    //   212: aload_2
    //   213: astore_1
    //   214: aload_0
    //   215: iconst_0
    //   216: putfield 31	com/alipay/sdk/util/g:e	Z
    //   219: aload_1
    //   220: areturn
    //   221: astore_1
    //   222: goto -33 -> 189
    //   225: astore_1
    //   226: goto -140 -> 86
    //   229: astore_2
    //   230: goto -39 -> 191
    //   233: astore_1
    //   234: goto -27 -> 207
    //   237: astore_2
    //   238: goto -61 -> 177
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	241	0	this	g
    //   0	241	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   46	63	157	finally
    //   63	65	157	finally
    //   39	46	162	finally
    //   65	72	162	finally
    //   99	123	162	finally
    //   123	141	162	finally
    //   158	162	162	finally
    //   39	46	221	java/lang/Exception
    //   65	72	221	java/lang/Exception
    //   99	123	221	java/lang/Exception
    //   158	162	221	java/lang/Exception
    //   72	83	225	java/lang/Exception
    //   123	141	229	java/lang/Exception
    //   143	154	233	java/lang/Exception
    //   193	204	233	java/lang/Exception
    //   163	174	237	java/lang/Exception
  }
  
  public final String a(String paramString)
  {
    Object localObject = this.b.getPackageManager().getInstalledPackages(64).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localPackageInfo.packageName.equals("com.eg.android.AlipayGphone"))
      {
        localObject = localPackageInfo.signatures[0].toByteArray();
        break label64;
      }
    }
    localObject = null;
    label64:
    localObject = j.a((byte[])localObject);
    if ((localObject != null) && (!TextUtils.equals((CharSequence)localObject, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649"))) {
      return ab.b();
    }
    localObject = new Intent();
    ((Intent)localObject).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.MspService");
    ((Intent)localObject).setAction("com.eg.android.AlipayGphone.IAlixPay");
    return a(paramString, (Intent)localObject);
  }
}
