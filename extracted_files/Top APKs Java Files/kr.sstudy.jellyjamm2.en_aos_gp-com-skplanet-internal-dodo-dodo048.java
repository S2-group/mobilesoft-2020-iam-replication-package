package com.skplanet.internal.dodo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.skplanet.dodo.IapPlugin;
import com.skplanet.dodo.IapWeb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
  
  private String reqIpAuth(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString2);
    localStringBuffer.append(paramString2).append("?mdn=").append(paramString1).append("&ext=").append(paramString3);
    paramString1 = new DefaultHttpClient();
    paramString2 = new HttpGet(localStringBuffer.toString());
    try
    {
      paramString1 = paramString1.execute(paramString2);
      if (paramString1.getStatusLine().getStatusCode() == 200)
      {
        paramString1 = paramString1.getEntity();
        if (paramString1 == null) {
          break label124;
        }
        try
        {
          paramString1 = EntityUtils.toString(paramString1);
          return paramString1;
        }
        catch (IllegalStateException paramString1)
        {
          paramString1.printStackTrace();
          return "result=fail&accessToken=internal_illegal_state_error";
        }
      }
      return "result=fail&accessToken=external_http_error";
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return "result=fail&accessToken=internal_io_error";
    }
    label124:
    return "result=fail&accessToken=external_entity_null";
  }
  
  @JavascriptInterface
  public boolean checkIapWebInstance()
  {
    return this._defined;
  }
  
  @JavascriptInterface
  public native boolean checkLockPassword(String paramString1, String paramString2, String paramString3);
  
  @JavascriptInterface
  public String createAccessToken(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    int i = 0;
    StringBuffer localStringBuffer = new StringBuffer();
    ConnectivityManager localConnectivityManager = (ConnectivityManager)this._context.getSystemService("connectivity");
    Object localObject1 = localConnectivityManager.getActiveNetworkInfo();
    if (localObject1 == null) {
      return "Don't get networkinfo";
    }
    if (((NetworkInfo)localObject1).isConnected())
    {
      paramInt = ((NetworkInfo)localObject1).getType();
      if (paramInt != 1) {
        break label375;
      }
      paramInt = 0;
      localConnectivityManager.startUsingNetworkFeature(0, "enableHIPRI");
      int j;
      do
      {
        if (i != 0) {
          break;
        }
        if (localConnectivityManager.getNetworkInfo(5).getState() == NetworkInfo.State.CONNECTED) {
          i = 1;
        }
        SystemClock.sleep(100L);
        j = paramInt + 100;
        paramInt = j;
      } while (j <= 8000);
    }
    for (paramInt = 1;; paramInt = 0)
    {
      label172:
      label184:
      Object localObject2;
      label223:
      Object localObject3;
      if (paramString2.substring(0, paramString2.lastIndexOf(":")).equalsIgnoreCase("http"))
      {
        localObject1 = paramString2.substring(7);
        if (((String)localObject1).lastIndexOf(":") == -1) {
          break label356;
        }
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).lastIndexOf(":"));
        localConnectivityManager.requestRouteToHost(5, IapWeb.lookupHost((String)localObject1));
        if (paramInt != 0) {
          break label571;
        }
        localObject1 = null;
        String[] arrayOfString = reqIpAuth(paramString1, paramString2, paramString3).split("&");
        i = arrayOfString.length;
        paramInt = 0;
        localObject2 = null;
        paramString2 = (String)localObject1;
        localObject1 = localObject2;
        if (paramInt >= i) {
          break label402;
        }
        localObject2 = arrayOfString[paramInt].split("=");
        localObject3 = localObject2[0];
        localObject2 = localObject2[1];
        if (!localObject3.equals("result")) {
          break label384;
        }
        paramString2 = (String)localObject2;
      }
      for (;;)
      {
        paramInt += 1;
        break label223;
        return "Don't connect network";
        if (paramString2.substring(0, paramString2.lastIndexOf(":")).equalsIgnoreCase("https"))
        {
          localObject1 = paramString2.substring(8);
          break;
        }
        i = Integer.valueOf(paramString3).intValue();
        if ((i >= 1) && (i < paramString2.length()))
        {
          localObject1 = paramString2.substring(i);
          break;
        }
        localObject1 = paramString2.substring(7);
        break;
        label356:
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).lastIndexOf("/"));
        break label172;
        label375:
        if (paramInt == 0) {}
        paramInt = 0;
        break label184;
        label384:
        if (localObject3.equals("accessToken"))
        {
          localObject1 = localObject2;
          continue;
          label402:
          if (paramString2.equals("success"))
          {
            paramString2 = setAccessToken((String)localObject1);
            if (paramString2.equals("success"))
            {
              localStringBuffer.append("result=success&accessToken=").append((String)localObject1).append("&mdn=").append(paramString1).append("&ext=").append(paramString3).append("&reason=");
              if (!paramString3.equalsIgnoreCase("nonstop")) {
                break label603;
              }
            }
          }
          for (;;)
          {
            return localStringBuffer.toString();
            localStringBuffer.append("result=fail&accessToken=").append("&mdn=").append(paramString1).append("&ext=").append(paramString3).append("&reason=").append(paramString2);
            break;
            localStringBuffer.append("result=fail&accessToken=").append("&mdn=").append(paramString1).append("&ext=").append(paramString3).append("&reason=").append((String)localObject1);
            break;
            label571:
            localStringBuffer.append("result=fail&accessToke=&mdn=").append(paramString1).append("&ext=").append(paramString3).append("&reason=internal_connection_timeout");
            break;
            label603:
            localConnectivityManager.stopUsingNetworkFeature(0, "enableHIPRI");
          }
        }
      }
    }
  }
  
  @JavascriptInterface
  public void exitGracefully()
  {
    doUnregisterReceiver();
  }
  
  final String generateUID()
  {
    return String.valueOf(this.BASE_UNIQUE.getAndIncrement());
  }
  
  /* Error */
  @JavascriptInterface
  public String getAccessToken()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	com/skplanet/internal/dodo/dodo048:_context	Landroid/content/Context;
    //   4: invokevirtual 325	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: getfield 36	com/skplanet/internal/dodo/dodo048:_context	Landroid/content/Context;
    //   13: invokevirtual 328	android/content/Context:getPackageName	()Ljava/lang/String;
    //   16: iconst_1
    //   17: invokevirtual 334	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   20: getfield 340	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   23: getfield 345	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   26: astore_2
    //   27: new 105	java/lang/StringBuffer
    //   30: dup
    //   31: invokespecial 106	java/lang/StringBuffer:<init>	()V
    //   34: astore_3
    //   35: aload_3
    //   36: aload_2
    //   37: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   40: ldc_w 347
    //   43: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   46: ldc_w 349
    //   49: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   52: pop
    //   53: new 105	java/lang/StringBuffer
    //   56: dup
    //   57: invokespecial 106	java/lang/StringBuffer:<init>	()V
    //   60: astore_2
    //   61: aload_2
    //   62: ldc_w 351
    //   65: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   68: pop
    //   69: new 353	java/io/File
    //   72: dup
    //   73: aload_3
    //   74: invokevirtual 123	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   77: invokespecial 354	java/io/File:<init>	(Ljava/lang/String;)V
    //   80: astore 4
    //   82: aload 4
    //   84: invokevirtual 357	java/io/File:length	()J
    //   87: l2i
    //   88: istore_1
    //   89: iload_1
    //   90: sipush 1024
    //   93: if_icmpgt +118 -> 211
    //   96: iload_1
    //   97: iconst_1
    //   98: if_icmplt +113 -> 211
    //   101: iload_1
    //   102: newarray byte
    //   104: astore_3
    //   105: new 359	java/io/FileInputStream
    //   108: dup
    //   109: aload 4
    //   111: invokespecial 362	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   114: astore 4
    //   116: aload 4
    //   118: aload_3
    //   119: invokevirtual 366	java/io/FileInputStream:read	([B)I
    //   122: iconst_m1
    //   123: if_icmpne -7 -> 116
    //   126: new 226	java/lang/String
    //   129: dup
    //   130: aload_3
    //   131: invokespecial 369	java/lang/String:<init>	([B)V
    //   134: astore_3
    //   135: aload_2
    //   136: ldc_w 286
    //   139: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   142: ldc_w 371
    //   145: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   148: aload_3
    //   149: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   152: pop
    //   153: aload_2
    //   154: invokevirtual 123	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   157: areturn
    //   158: astore_2
    //   159: aload_2
    //   160: invokevirtual 372	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   163: ldc_w 374
    //   166: areturn
    //   167: astore_3
    //   168: aload_3
    //   169: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   172: aload_2
    //   173: ldc_w 376
    //   176: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   179: ldc_w 371
    //   182: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   185: pop
    //   186: goto -33 -> 153
    //   189: astore_3
    //   190: aload_3
    //   191: invokevirtual 377	java/io/FileNotFoundException:printStackTrace	()V
    //   194: aload_2
    //   195: ldc_w 376
    //   198: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   201: ldc_w 371
    //   204: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   207: pop
    //   208: goto -55 -> 153
    //   211: aload_2
    //   212: ldc_w 376
    //   215: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   218: ldc_w 371
    //   221: invokevirtual 110	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   224: pop
    //   225: aload_2
    //   226: invokevirtual 123	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   229: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	this	dodo048
    //   88	14	1	i	int
    //   7	147	2	localObject1	Object
    //   158	68	2	localNameNotFoundException	PackageManager.NameNotFoundException
    //   34	115	3	localObject2	Object
    //   167	2	3	localIOException	IOException
    //   189	2	3	localFileNotFoundException	FileNotFoundException
    //   80	37	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   8	27	158	android/content/pm/PackageManager$NameNotFoundException
    //   116	153	167	java/io/IOException
    //   105	116	189	java/io/FileNotFoundException
    //   116	153	189	java/io/FileNotFoundException
    //   168	186	189	java/io/FileNotFoundException
  }
  
  @JavascriptInterface
  public String getDataDirectory()
  {
    return IapPlugin.dataDir;
  }
  
  @JavascriptInterface
  public int getDeviceHeight()
  {
    if (this._activity == null) {
      return -1;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this._activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  @JavascriptInterface
  public int getDeviceWidth()
  {
    if (this._activity == null) {
      return -1;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this._activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  @JavascriptInterface
  public boolean getMobileState()
  {
    return ((ConnectivityManager)this._context.getSystemService("connectivity")).getNetworkInfo(5).getState() == NetworkInfo.State.CONNECTED;
  }
  
  @JavascriptInterface
  public int getOrientation()
  {
    if (this._activity == null) {
      return -1;
    }
    if (Build.VERSION.SDK_INT < 8) {
      return this._activity.getWindowManager().getDefaultDisplay().getOrientation();
    }
    return this._activity.getWindowManager().getDefaultDisplay().getRotation();
  }
  
  /* Error */
  @JavascriptInterface
  public String getPackageVersion(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 428	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 36	com/skplanet/internal/dodo/dodo048:_context	Landroid/content/Context;
    //   13: invokevirtual 325	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   16: astore_2
    //   17: aload_2
    //   18: aload_1
    //   19: iconst_0
    //   20: invokevirtual 334	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   23: astore_1
    //   24: aload_1
    //   25: ifnull -18 -> 7
    //   28: ldc 8
    //   30: iconst_2
    //   31: anewarray 4	java/lang/Object
    //   34: dup
    //   35: iconst_0
    //   36: aload_1
    //   37: getfield 431	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   40: aastore
    //   41: dup
    //   42: iconst_1
    //   43: aload_1
    //   44: getfield 434	android/content/pm/PackageInfo:versionCode	I
    //   47: invokestatic 437	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   50: aastore
    //   51: invokestatic 441	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
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
  public native String getSystemInfo(String paramString);
  
  @JavascriptInterface
  public void hasWeb(boolean paramBoolean)
  {
    this._defined = paramBoolean;
  }
  
  @JavascriptInterface
  public boolean isInstalledPackage(String paramString)
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
  public native void onPaymentResult(String paramString1, String paramString2);
  
  @JavascriptInterface
  public String requestAction(String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString1)) {
      paramString1 = null;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return paramString1;
            paramString3 = generateUID();
            if (!"startActivity".equals(paramString1)) {
              break;
            }
            paramString1 = paramString3;
          } while (doStartActivity(paramString2));
          return null;
          if (!"sendBroadcast".equals(paramString1)) {
            break;
          }
          paramString1 = paramString3;
        } while (doSendBroadcast(paramString2));
        return null;
        if (!"registerReceiver".equals(paramString1)) {
          break;
        }
        paramString1 = paramString3;
      } while (doRegisterReceiver(paramString2, paramString3));
      return null;
      if ("unregisterReceiver".equals(paramString1))
      {
        doUnregisterReceiver();
        return paramString3;
      }
      if (!"startActivityForResult".equals(paramString1)) {
        break;
      }
      paramString1 = paramString3;
    } while (doStartActivityForResult(paramString2, paramString3));
    return null;
    return null;
  }
  
  @JavascriptInterface
  public native void sendTmoneyBalanceRequest(String paramString);
  
  @JavascriptInterface
  public native void sendTmoneyBillingRequest(String paramString);
  
  @JavascriptInterface
  public String setAccessToken(String paramString)
  {
    Object localObject2 = this._context.getPackageManager();
    Object localObject1 = null;
    try
    {
      localObject2 = ((PackageManager)localObject2).getPackageInfo(this._context.getPackageName(), 1).applicationInfo.dataDir;
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        try
        {
          localObject1 = new FileOutputStream((File)localObject2);
          try
          {
            ((FileOutputStream)localObject1).write(paramString.getBytes());
            ((FileOutputStream)localObject1).close();
            return "success";
          }
          catch (IOException paramString)
          {
            paramString.printStackTrace();
            return "internal_io_error";
          }
          localNameNotFoundException = localNameNotFoundException;
          localNameNotFoundException.printStackTrace();
        }
        catch (FileNotFoundException paramString)
        {
          paramString.printStackTrace();
        }
        ((File)localObject1).mkdirs();
      }
    }
    localObject2 = new StringBuffer();
    ((StringBuffer)localObject2).append((String)localObject1);
    ((StringBuffer)localObject2).append("/files/");
    localObject1 = new File(((StringBuffer)localObject2).toString());
    ((StringBuffer)localObject2).append("iaptoken");
    localObject2 = new File(((StringBuffer)localObject2).toString());
    if (((File)localObject2).exists()) {
      ((File)localObject2).delete();
    }
    return "internal_file_not_found";
  }
  
  @JavascriptInterface
  public boolean stopMobileState()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)this._context.getSystemService("connectivity");
    if (localConnectivityManager.getNetworkInfo(5).getState() == NetworkInfo.State.CONNECTED)
    {
      localConnectivityManager.stopUsingNetworkFeature(0, "enableHIPRI");
      return true;
    }
    return false;
  }
  
  @JavascriptInterface
  public native int verifyReceipt(String paramString1, String paramString2);
}
