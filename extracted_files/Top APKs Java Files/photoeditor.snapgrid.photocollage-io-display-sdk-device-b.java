package io.display.sdk.device;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
{
  public String a;
  public boolean b = true;
  public String c = "";
  public String d = "";
  public String e = "";
  c f;
  private HashMap<String, String> g;
  private String h = "";
  private String i = "";
  private String j = "";
  private int k = 0;
  private long l;
  
  public b(final Context paramContext, final c paramC)
  {
    this.f = paramC;
    e();
    this.g = new HashMap();
    this.g.put("model", Build.MODEL);
    this.g.put("make", Build.MANUFACTURER);
    this.g.put("os", "android");
    this.g.put("osver", Build.VERSION.RELEASE);
    this.g.put("hardware", Build.HARDWARE);
    this.g.put("fingerprint", Build.FINGERPRINT);
    this.g.put("brand", Build.BRAND);
    this.g.put("product", Build.PRODUCT);
    this.g.put("cpuCores", Integer.toString(this.k));
    this.g.put("cpuModel", this.j);
    this.g.put("cpuVendor", this.i);
    this.g.put("cpuArch", System.getProperty("os.arch"));
    if (Build.VERSION.SDK_INT >= 24) {
      this.g.put("locale", LocaleList.getDefault().toLanguageTags());
    }
    a(paramContext);
    this.g.put("inch", e(paramContext));
    this.g.put("carrier", g(paramContext));
    this.g.put("net", f(paramContext));
    try
    {
      ((Activity)paramContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          WebView localWebView = new WebView(paramContext);
          b.a(b.this).put("ua", localWebView.getSettings().getUserAgentString());
          localWebView.destroy();
        }
      });
      paramC = new a(paramC)
      {
        /* Error */
        public void a(String paramAnonymousString)
        {
          // Byte code:
          //   0: new 27	org/json/JSONObject
          //   3: dup
          //   4: aload_1
          //   5: invokespecial 29	org/json/JSONObject:<init>	(Ljava/lang/String;)V
          //   8: astore_1
          //   9: aload_0
          //   10: getfield 16	io/display/sdk/device/b$2:b	Lio/display/sdk/device/b;
          //   13: aload_1
          //   14: ldc 31
          //   16: invokevirtual 35	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   19: putfield 38	io/display/sdk/device/b:a	Ljava/lang/String;
          //   22: aload_1
          //   23: ldc 40
          //   25: invokevirtual 44	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
          //   28: pop
          //   29: aload_0
          //   30: getfield 18	io/display/sdk/device/b$2:a	Lio/display/sdk/device/c;
          //   33: invokevirtual 49	io/display/sdk/device/c:a	()V
          //   36: return
          //   37: astore_2
          //   38: aload_2
          //   39: invokevirtual 52	org/json/JSONException:printStackTrace	()V
          //   42: goto -20 -> 22
          //   45: astore_1
          //   46: aload_1
          //   47: invokevirtual 52	org/json/JSONException:printStackTrace	()V
          //   50: goto -21 -> 29
          //   53: astore_1
          //   54: aload_1
          //   55: invokevirtual 52	org/json/JSONException:printStackTrace	()V
          //   58: goto -29 -> 29
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	61	0	this	2
          //   0	61	1	paramAnonymousString	String
          //   37	2	2	localJSONException	org.json.JSONException
          // Exception table:
          //   from	to	target	type
          //   9	22	37	org/json/JSONException
          //   0	9	45	org/json/JSONException
          //   38	42	45	org/json/JSONException
          //   54	58	45	org/json/JSONException
          //   22	29	53	org/json/JSONException
        }
      };
      if (Build.VERSION.SDK_INT >= 11)
      {
        paramC.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Context[] { paramContext });
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.g.put("ua", "");
      }
      paramC.execute(new Context[] { paramContext });
    }
  }
  
  @TargetApi(17)
  private void c(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    paramContext.getSize(localPoint);
    this.g.put("w", String.valueOf(localPoint.x));
    this.g.put("h", String.valueOf(localPoint.y));
  }
  
  private void d(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.g.put("w", String.valueOf(paramContext.getWidth()));
    this.g.put("h", String.valueOf(paramContext.getHeight()));
  }
  
  private String e(Context paramContext)
  {
    if ((paramContext instanceof Activity))
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((Activity)paramContext).getWindow().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      int m = localDisplayMetrics.widthPixels;
      int n = localDisplayMetrics.heightPixels;
      int i1 = localDisplayMetrics.densityDpi;
      double d2 = m / i1;
      double d1 = n / i1;
      d2 = Math.pow(d2, 2.0D);
      return String.valueOf(Math.sqrt(Math.pow(d1, 2.0D) + d2));
    }
    return "";
  }
  
  private void e()
  {
    Object localObject = new StringBuffer();
    if (new File("/proc/cpuinfo").exists()) {
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          ((StringBuffer)localObject).append(str + "\n");
        }
        this.h = ((StringBuffer)localObject).toString();
      }
      catch (IOException localIOException) {}
    }
    for (;;)
    {
      localObject = Pattern.compile("vendor_id\\s*: (.*)").matcher(this.h);
      if (((Matcher)localObject).find()) {
        this.i = ((Matcher)localObject).group(1);
      }
      localObject = Pattern.compile("model name\\s*: (.*)").matcher(this.h);
      if (((Matcher)localObject).find()) {
        this.j = ((Matcher)localObject).group(1);
      }
      localObject = Pattern.compile("^processor").matcher(this.h);
      while (((Matcher)localObject).find()) {
        this.k += 1;
      }
      if (localIOException != null) {
        localIOException.close();
      }
    }
  }
  
  private String f(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if ((localConnectivityManager.getActiveNetworkInfo() == null) || (!localConnectivityManager.getActiveNetworkInfo().isConnected())) {
      return "not_connected";
    }
    switch (localConnectivityManager.getActiveNetworkInfo().getType())
    {
    case 2: 
    case 3: 
    default: 
      return "";
    case 0: 
    case 4: 
      switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
      {
      default: 
        return "mobile";
      case 1: 
      case 2: 
      case 4: 
      case 7: 
      case 11: 
        return "2G";
      case 3: 
      case 5: 
      case 6: 
      case 8: 
      case 9: 
      case 10: 
      case 12: 
      case 14: 
      case 15: 
        return "3G";
      }
      return "4G";
    }
    return "wifi";
  }
  
  private String g(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  private List<ApplicationInfo> h(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(128);
  }
  
  public int a()
  {
    return Integer.valueOf(c().get("w").toString()).intValue();
  }
  
  public void a(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      c(paramContext);
      return;
    }
    d(paramContext);
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    this.c = paramString1;
    this.d = paramString2;
    this.e = paramString3;
    this.l = new Date().getTime();
  }
  
  public int b()
  {
    return Integer.valueOf(c().get("h").toString()).intValue();
  }
  
  public JSONArray b(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    List localList = h(paramContext);
    int m = 0;
    for (;;)
    {
      if (m < localList.size())
      {
        JSONObject localJSONObject = new JSONObject();
        if ((((ApplicationInfo)localList.get(m)).flags & 0x1) != 1) {}
        try
        {
          localJSONObject.put("package", ((ApplicationInfo)localList.get(m)).packageName);
          localJSONObject.put("installedAt", String.valueOf(paramContext.getPackageManager().getPackageInfo(((ApplicationInfo)localList.get(m)).packageName, 0).firstInstallTime));
          localJSONArray.put(localJSONObject);
          m += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  public HashMap c()
  {
    return this.g;
  }
  
  public boolean d()
  {
    return new Date().getTime() - this.l > 600000L;
  }
  
  class a
    extends AsyncTask<Context, String, String>
  {
    a() {}
    
    protected String a(Context[] paramArrayOfContext)
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        paramArrayOfContext = a.a(paramArrayOfContext[0]);
        localJSONObject.put("id", paramArrayOfContext.a());
        localJSONObject.put("dnt", paramArrayOfContext.b());
        return localJSONObject.toString();
      }
      catch (Exception paramArrayOfContext)
      {
        for (;;)
        {
          Log.i("io.display.sdk", "couldn't get advertising ID");
        }
      }
    }
  }
}
