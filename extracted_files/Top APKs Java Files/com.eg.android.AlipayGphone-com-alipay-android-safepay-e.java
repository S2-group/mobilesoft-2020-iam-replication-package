package com.alipay.android.safepay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alipay.android.client.AlipayApplication;
import com.alipay.android.client.a.m;
import com.alipay.android.client.a.o;
import com.alipay.android.client.br;
import com.alipay.android.client.d.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;
import org.json.JSONException;
import org.json.JSONObject;

public final class e
{
  Activity a = null;
  private ProgressDialog b = null;
  private DialogInterface.OnClickListener c = null;
  private Handler d;
  
  public e()
  {
    m.a();
    this.d = new v(this);
  }
  
  public static Intent a(String paramString)
  {
    m.a("777", paramString);
    try
    {
      Thread.sleep(1000L);
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addFlags(268435456);
      localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
      return localIntent;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public static PackageInfo a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getPackageArchiveInfo(paramString, 128);
  }
  
  public static String a(PackageInfo paramPackageInfo)
  {
    Object localObject = null;
    try
    {
      JSONObject localJSONObject = b(paramPackageInfo.versionName);
      paramPackageInfo = localObject;
      if (localJSONObject.getString("needUpdate").equalsIgnoreCase("true")) {
        paramPackageInfo = localJSONObject.getString("updateUrl");
      }
      return paramPackageInfo;
    }
    catch (Exception paramPackageInfo)
    {
      paramPackageInfo.printStackTrace();
    }
    return null;
  }
  
  private void a(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getCacheDir();
    paramContext = paramContext.getAbsolutePath() + "/temp.apk";
    Object localObject = new File(paramContext);
    if (((File)localObject).isFile()) {
      ((File)localObject).deleteOnExit();
    }
    localObject = new Bundle();
    ((Bundle)localObject).putInt("needupdate", paramInt1);
    ((Bundle)localObject).putString("cachepath", paramContext);
    if (paramInt1 >= 0)
    {
      paramContext = new Message();
      paramContext.what = 443;
      paramContext.obj = localObject;
      paramContext.arg1 = paramInt2;
      this.d.sendMessage(paramContext);
    }
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase("com.alipay.android.app")) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString1);
      paramString1 = new File(paramString2);
      paramString1.createNewFile();
      paramString1 = new FileOutputStream(paramString1);
      paramString2 = new byte['Ѐ'];
      for (;;)
      {
        int i = paramContext.read(paramString2);
        if (i <= 0) {
          break;
        }
        paramString1.write(paramString2, 0, i);
      }
      paramString1.close();
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    paramContext.close();
    return true;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    try
    {
      boolean bool = com.alipay.android.a.a.a.a(paramString1, paramString2, false, null, AlipayApplication.g);
      return bool;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static PackageInfo b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo("com.alipay.android.app", 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static JSONObject b(String paramString)
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("action", "update");
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("platform", "android");
      localJSONObject2.put("version", paramString);
      localJSONObject2.put("partner", "");
      localJSONObject1.put("data", localJSONObject2);
      paramString = c(localJSONObject1.toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static boolean b(String paramString1, String paramString2)
  {
    paramString1 = new StringTokenizer(paramString1, ".");
    paramString2 = new StringTokenizer(paramString2, ".");
    Integer localInteger1;
    Integer localInteger2;
    do
    {
      if ((!paramString1.hasMoreTokens()) || (!paramString2.hasMoreTokens())) {
        break;
      }
      localInteger1 = new Integer(paramString1.nextToken());
      localInteger2 = new Integer(paramString2.nextToken());
      if (localInteger1.intValue() > localInteger2.intValue()) {
        return true;
      }
    } while (localInteger2.intValue() <= localInteger1.intValue());
    return false;
  }
  
  /* Error */
  private static JSONObject c(String paramString)
  {
    // Byte code:
    //   0: new 388	com/alipay/android/a/b/a
    //   3: dup
    //   4: invokestatic 392	com/alipay/android/client/d/b:b	()Ljava/lang/String;
    //   7: getstatic 330	com/alipay/android/client/AlipayApplication:g	Lcom/alipay/android/client/AlipayApplication;
    //   10: invokespecial 395	com/alipay/android/a/b/a:<init>	(Ljava/lang/String;Landroid/content/Context;)V
    //   13: astore_1
    //   14: aload_1
    //   15: monitorenter
    //   16: aload_1
    //   17: aload_0
    //   18: iconst_0
    //   19: invokevirtual 398	com/alipay/android/a/b/a:a	(Ljava/lang/String;Z)Ljava/lang/String;
    //   22: astore_0
    //   23: aload_1
    //   24: monitorexit
    //   25: new 121	org/json/JSONObject
    //   28: dup
    //   29: aload_0
    //   30: invokespecial 399	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   33: astore_0
    //   34: aload_0
    //   35: ifnull +8 -> 43
    //   38: aload_0
    //   39: invokevirtual 365	org/json/JSONObject:toString	()Ljava/lang/String;
    //   42: pop
    //   43: aload_0
    //   44: areturn
    //   45: astore_0
    //   46: aload_1
    //   47: monitorexit
    //   48: aload_0
    //   49: athrow
    //   50: astore_0
    //   51: aload_0
    //   52: invokevirtual 136	java/lang/Exception:printStackTrace	()V
    //   55: aconst_null
    //   56: astore_0
    //   57: goto -23 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	60	0	paramString	String
    // Exception table:
    //   from	to	target	type
    //   16	25	45	finally
    //   46	48	45	finally
    //   14	16	50	java/lang/Exception
    //   25	34	50	java/lang/Exception
    //   48	50	50	java/lang/Exception
  }
  
  public final ProgressDialog a(Activity paramActivity, Handler paramHandler, ProgressDialog paramProgressDialog, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (a(paramActivity, 0)) {}
    try
    {
      new z().a(paramString1, paramString2, paramString3, paramString4, paramString5, paramHandler, paramActivity);
      return paramProgressDialog;
    }
    catch (Exception paramActivity) {}
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addDataScheme("package");
    AlipayApplication.c.h();
    AlipayApplication.c.a(paramActivity, paramHandler, paramString1, paramString2, paramString3, paramString4, paramString5);
    paramHandler = new q();
    if (AlipayApplication.b != null)
    {
      paramActivity.getApplicationContext().unregisterReceiver(AlipayApplication.b);
      AlipayApplication.b = null;
    }
    AlipayApplication.b = paramHandler;
    paramActivity.getApplicationContext().registerReceiver(paramHandler, localIntentFilter);
    return paramProgressDialog;
    return paramProgressDialog;
  }
  
  public final ProgressDialog a(Activity paramActivity, Handler paramHandler, String paramString1, String paramString2)
  {
    if (a(paramActivity, 0)) {}
    try
    {
      new z().a(z.a(paramString1, paramString2), paramHandler, paramActivity);
      return null;
    }
    catch (Exception paramActivity) {}
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addDataScheme("package");
    paramHandler = new y(paramActivity, paramHandler, paramString1, paramString2);
    if (AlipayApplication.b != null)
    {
      paramActivity.getApplicationContext().unregisterReceiver(AlipayApplication.b);
      AlipayApplication.b = null;
    }
    AlipayApplication.b = paramHandler;
    paramActivity.getApplicationContext().registerReceiver(paramHandler, localIntentFilter);
    return null;
    return null;
  }
  
  final void a()
  {
    try
    {
      if (this.b != null)
      {
        this.b.dismiss();
        this.b = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final boolean a(Activity paramActivity, int paramInt)
  {
    this.a = paramActivity;
    Object localObject1 = paramActivity.getCacheDir();
    Object localObject2 = ((File)localObject1).getAbsolutePath() + "/temp.apk";
    localObject1 = new Bundle();
    ((Bundle)localObject1).putInt("Pay_Style", paramInt);
    ((Bundle)localObject1).putString("cachepath", (String)localObject2);
    localObject2 = this.d;
    this.a = paramActivity;
    Object localObject3 = paramActivity.getCacheDir();
    localObject3 = ((File)localObject3).getAbsolutePath() + "/temp.apk";
    File localFile = new File((String)localObject3);
    boolean bool = a(paramActivity);
    if (!bool)
    {
      this.b = m.a(paramActivity, paramActivity.getString(2131362779));
      o localO = new o(paramActivity);
      if (!localFile.exists()) {
        a(paramActivity, "mobile_sp.apk", (String)localObject3);
      }
      new Thread(new r(paramActivity, (String)localObject3, localO, (Bundle)localObject1, (Handler)localObject2)).start();
    }
    return bool;
  }
  
  public final boolean a(Activity paramActivity, int paramInt, DialogInterface.OnClickListener paramOnClickListener, Handler paramHandler)
  {
    this.a = paramActivity;
    this.c = paramOnClickListener;
    int i;
    if (!a(paramActivity))
    {
      paramOnClickListener = new IntentFilter();
      paramOnClickListener.addAction("android.intent.action.PACKAGE_ADDED");
      paramOnClickListener.addDataScheme("package");
      AlipayApplication.c.h();
      AlipayApplication.c.a(this.a, paramHandler, "", "", "", "", "");
      paramHandler = new w();
      if (AlipayApplication.b != null)
      {
        this.a.getApplicationContext().unregisterReceiver(AlipayApplication.b);
        AlipayApplication.b = null;
      }
      AlipayApplication.b = paramHandler;
      this.a.getApplicationContext().registerReceiver(paramHandler, paramOnClickListener);
      i = 0;
    }
    for (;;)
    {
      a(paramActivity, i, paramInt);
      if (i >= 0)
      {
        return true;
        paramOnClickListener = b(paramActivity);
        com.google.zxing.a.a(b.S, "MobileSecurePayHelper", "detectSafepayUpdate want mSafepayVersion:2.3.1, real versionName=" + paramOnClickListener.versionName);
        if (b("2.3.1", paramOnClickListener.versionName)) {
          i = 1;
        }
      }
      else
      {
        return false;
      }
      i = -1;
    }
  }
  
  public final boolean a(Activity paramActivity, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.a = paramActivity;
    this.c = paramOnClickListener;
    int i;
    if (!a(paramActivity)) {
      i = 0;
    }
    for (;;)
    {
      a(paramActivity, i, 1);
      if (i >= 0)
      {
        return true;
        paramOnClickListener = b(paramActivity);
        com.google.zxing.a.a(b.S, "MobileSecurePayHelper", "detectSafepayUpdate want mSafepayVersion:2.3.1, real versionName=" + paramOnClickListener.versionName);
        if (b("2.3.1", paramOnClickListener.versionName)) {
          i = 1;
        }
      }
      else
      {
        return false;
      }
      i = -1;
    }
  }
  
  public final boolean a(Activity paramActivity, Handler paramHandler, String paramString)
  {
    this.a = paramActivity;
    Object localObject = paramActivity.getCacheDir();
    localObject = ((File)localObject).getAbsolutePath() + "/temp.apk";
    boolean bool = a(paramActivity);
    o localO = new o(paramActivity);
    if (!bool)
    {
      paramActivity = new Bundle();
      paramActivity.putInt("needupdate", 3);
      paramActivity.putString("cachepath", (String)localObject);
      paramString = new Message();
      paramString.what = 443;
      paramString.obj = paramActivity;
      paramHandler.sendMessage(paramString);
      return bool;
    }
    this.b = m.a(paramActivity, paramString);
    new Thread(new s(this, localO, paramActivity, (String)localObject, paramHandler)).start();
    return bool;
  }
}
