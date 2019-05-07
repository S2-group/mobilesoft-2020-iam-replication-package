package com.hellotalk.b;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import com.c.b;
import com.c.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class l
{
  Context a = null;
  private ProgressDialog b = null;
  @SuppressLint({"HandlerLeak"})
  private Handler c = new m(this);
  
  public l(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public static PackageInfo b(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getPackageArchiveInfo(paramString, 128);
  }
  
  public String a(PackageInfo paramPackageInfo)
  {
    Object localObject = null;
    try
    {
      JSONObject localJSONObject = a(paramPackageInfo.versionName);
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
  
  public JSONObject a(String paramString)
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
      paramString = b(localJSONObject1.toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public void a(Context paramContext, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setIcon(b.info);
    localBuilder.setTitle(paramContext.getResources().getString(c.confirm_install_hint));
    localBuilder.setMessage(paramContext.getResources().getString(c.confirm_install));
    localBuilder.setPositiveButton(17039370, new p(this, paramString, paramContext));
    localBuilder.setNegativeButton(paramContext.getResources().getString(17039360), null);
    localBuilder.show();
  }
  
  public boolean a()
  {
    boolean bool = b();
    if (!bool)
    {
      String str = this.a.getCacheDir().getAbsolutePath() + "/temp.apk";
      a(this.a, "alipay_plugin_20120428msp.apk", str);
      this.b = e.a(this.a, null, "正在检测安全支付服务版本", false, true);
      new Thread(new n(this, str)).start();
    }
    return bool;
  }
  
  public boolean a(Context paramContext, String paramString1, String paramString2)
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
        if (i <= 0)
        {
          paramString1.close();
          paramContext.close();
          return true;
        }
        paramString1.write(paramString2, 0, i);
      }
      return false;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  public JSONObject b(String paramString)
  {
    // Byte code:
    //   0: new 256	com/hellotalk/b/u
    //   3: dup
    //   4: aload_0
    //   5: getfield 22	com/hellotalk/b/l:a	Landroid/content/Context;
    //   8: invokespecial 257	com/hellotalk/b/u:<init>	(Landroid/content/Context;)V
    //   11: astore_2
    //   12: aload_2
    //   13: monitorenter
    //   14: aload_2
    //   15: aload_1
    //   16: ldc_w 259
    //   19: invokevirtual 262	com/hellotalk/b/u:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   22: astore_1
    //   23: aload_2
    //   24: monitorexit
    //   25: new 60	org/json/JSONObject
    //   28: dup
    //   29: aload_1
    //   30: invokespecial 263	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   33: astore_1
    //   34: aload_1
    //   35: ifnull +13 -> 48
    //   38: ldc_w 265
    //   41: aload_1
    //   42: invokevirtual 104	org/json/JSONObject:toString	()Ljava/lang/String;
    //   45: invokestatic 268	com/hellotalk/b/e:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_1
    //   49: areturn
    //   50: astore_1
    //   51: aload_2
    //   52: monitorexit
    //   53: aload_1
    //   54: athrow
    //   55: astore_1
    //   56: aload_1
    //   57: invokevirtual 77	java/lang/Exception:printStackTrace	()V
    //   60: aconst_null
    //   61: astore_1
    //   62: goto -28 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	l
    //   0	65	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   14	25	50	finally
    //   51	53	50	finally
    //   12	14	55	java/lang/Exception
    //   25	34	55	java/lang/Exception
    //   53	55	55	java/lang/Exception
  }
  
  public boolean b()
  {
    List localList = this.a.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      if (((PackageInfo)localList.get(i)).packageName.equalsIgnoreCase("com.alipay.android.app")) {
        return true;
      }
      i += 1;
    }
  }
  
  void c()
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
}
