package com.skplanet.internal.dodo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class dodo048
{
  private static final String JSON_VERSION = "{\"name\":\"%s\", \"code\":%d}";
  private final AtomicInteger BASE_UNIQUE = new AtomicInteger();
  private final Activity _activity;
  private final dodo048.dodo044 _comm;
  private final Context _context;
  private boolean _defined = false;
  private BroadcastReceiver _receiver;
  private final WebView _web;
  
  public dodo048(Context paramContext, WebView paramWebView, dodo048.dodo044 paramDodo044)
  {
    this._context = paramContext;
    if ((paramContext instanceof Activity)) {}
    for (this._activity = ((Activity)paramContext);; this._activity = null)
    {
      this._web = paramWebView;
      this._receiver = null;
      this._comm = paramDodo044;
      return;
    }
  }
  
  private boolean doRegisterReceiver(String paramString1, String paramString2)
  {
    if (this._receiver != null) {}
    do
    {
      return false;
      paramString1 = dodo009.dodo005(paramString1);
    } while (paramString1 == null);
    this._receiver = new dodo048.dodo047(this, paramString2);
    this._context.registerReceiver(this._receiver, paramString1);
    return true;
  }
  
  private boolean doSendBroadcast(String paramString)
  {
    paramString = dodo009.dodo006(paramString);
    if (paramString != null)
    {
      this._context.sendBroadcast(paramString);
      return true;
    }
    return false;
  }
  
  private boolean doStartActivity(String paramString)
  {
    paramString = dodo009.dodo006(paramString);
    if (paramString != null)
    {
      this._context.startActivity(paramString);
      return true;
    }
    return false;
  }
  
  private boolean doStartActivityForResult(String paramString1, String paramString2)
  {
    paramString1 = dodo009.dodo006(paramString1);
    if (paramString1 != null)
    {
      this._activity.startActivityForResult(paramString1, Integer.parseInt(paramString2));
      return true;
    }
    return false;
  }
  
  private boolean doUnregisterReceiver()
  {
    if (this._receiver == null) {
      return false;
    }
    this._context.unregisterReceiver(this._receiver);
    this._receiver = null;
    return true;
  }
  
  @JavascriptInterface
  public final boolean checkIapWebInstance()
  {
    return this._defined;
  }
  
  @JavascriptInterface
  public final native boolean checkLockPassword(String paramString1, String paramString2, String paramString3);
  
  @JavascriptInterface
  public final void exitGracefully()
  {
    doUnregisterReceiver();
  }
  
  final String generateUID()
  {
    return String.valueOf(this.BASE_UNIQUE.getAndIncrement());
  }
  
  /* Error */
  @JavascriptInterface
  public final String getPackageVersion(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 127	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 36	com/skplanet/internal/dodo/dodo048:_context	Landroid/content/Context;
    //   13: invokevirtual 131	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   16: astore_2
    //   17: aload_2
    //   18: aload_1
    //   19: iconst_0
    //   20: invokevirtual 137	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   23: astore_1
    //   24: aload_1
    //   25: ifnull -18 -> 7
    //   28: ldc 8
    //   30: iconst_2
    //   31: anewarray 4	java/lang/Object
    //   34: dup
    //   35: iconst_0
    //   36: aload_1
    //   37: getfield 142	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   40: aastore
    //   41: dup
    //   42: iconst_1
    //   43: aload_1
    //   44: getfield 146	android/content/pm/PackageInfo:versionCode	I
    //   47: invokestatic 149	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   50: aastore
    //   51: invokestatic 153	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   54: astore_1
    //   55: aload_1
    //   56: areturn
    //   57: astore_1
    //   58: aconst_null
    //   59: areturn
    //   60: astore_1
    //   61: aconst_null
    //   62: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	dodo048
    //   0	63	1	paramString	String
    //   16	2	2	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   17	24	57	finally
    //   28	55	57	finally
    //   17	24	60	android/content/pm/PackageManager$NameNotFoundException
    //   28	55	60	android/content/pm/PackageManager$NameNotFoundException
  }
  
  @JavascriptInterface
  public final native String getSystemInfo(String paramString);
  
  @JavascriptInterface
  public final void hasWeb(boolean paramBoolean)
  {
    this._defined = paramBoolean;
  }
  
  @JavascriptInterface
  public final boolean isInstalledPackage(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    Iterator localIterator = this._context.getPackageManager().getInstalledApplications(8192).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  @JavascriptInterface
  public final native void onPaymentResult(String paramString1, String paramString2);
  
  @JavascriptInterface
  public final String requestAction(String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString1)) {}
    do
    {
      do
      {
        do
        {
          do
          {
            return null;
            paramString3 = generateUID();
            if (!"startActivity".equals(paramString1)) {
              break;
            }
          } while (!doStartActivity(paramString2));
          return paramString3;
          if (!"sendBroadcast".equals(paramString1)) {
            break;
          }
        } while (!doSendBroadcast(paramString2));
        return paramString3;
        if (!"registerReceiver".equals(paramString1)) {
          break;
        }
      } while (!doRegisterReceiver(paramString2, paramString3));
      return paramString3;
      if ("unregisterReceiver".equals(paramString1))
      {
        doUnregisterReceiver();
        return paramString3;
      }
    } while ((!"startActivityForResult".equals(paramString1)) || (!doStartActivityForResult(paramString2, paramString3)));
    return paramString3;
  }
  
  @JavascriptInterface
  public final native void sendTmoneyBalanceRequest(String paramString);
  
  @JavascriptInterface
  public final native void sendTmoneyBillingRequest(String paramString);
  
  @JavascriptInterface
  public final native int verifyReceipt(String paramString1, String paramString2);
}
