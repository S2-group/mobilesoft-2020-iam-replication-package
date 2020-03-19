package a.a.b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class aj
{
  String a = null;
  int b = 0;
  private Context c;
  private boolean d;
  
  public aj(Context paramContext)
  {
    this.c = paramContext;
    this.d = true;
  }
  
  private boolean m()
  {
    ActivityManager localActivityManager = (ActivityManager)this.c.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  public String a(Object paramObject)
  {
    try
    {
      this.a = ((String)paramObject.getClass().getMethod("getId", new Class[0]).invoke(paramObject, new Object[0]));
      return this.a;
    }
    catch (Exception paramObject)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_3
    //   5: ldc 84
    //   7: astore 4
    //   9: aload 4
    //   11: astore_2
    //   12: aload_0
    //   13: invokespecial 86	a/a/b/aj:m	()Z
    //   16: ifne +112 -> 128
    //   19: aload_0
    //   20: getfield 29	a/a/b/aj:c	Landroid/content/Context;
    //   23: invokevirtual 90	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   26: astore_2
    //   27: aload 4
    //   29: astore 5
    //   31: aload_2
    //   32: aload_1
    //   33: iconst_0
    //   34: invokevirtual 96	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   37: getfield 101	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   40: astore_1
    //   41: new 103	java/util/jar/JarFile
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 106	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   49: astore_1
    //   50: aload 6
    //   52: astore_3
    //   53: aload_1
    //   54: aload_1
    //   55: ldc 108
    //   57: invokevirtual 112	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   60: invokevirtual 116	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   63: astore_2
    //   64: aload_2
    //   65: astore_3
    //   66: aload_2
    //   67: invokevirtual 122	java/io/InputStream:available	()I
    //   70: newarray byte
    //   72: astore 5
    //   74: aload_2
    //   75: astore_3
    //   76: aload_2
    //   77: aload 5
    //   79: invokevirtual 126	java/io/InputStream:read	([B)I
    //   82: pop
    //   83: aload_2
    //   84: astore_3
    //   85: new 128	a/a/b/b
    //   88: dup
    //   89: invokespecial 129	a/a/b/b:<init>	()V
    //   92: aload 5
    //   94: invokevirtual 132	a/a/b/b:a	([B)Ljava/lang/String;
    //   97: astore 5
    //   99: aload 5
    //   101: astore_3
    //   102: aload_2
    //   103: ifnull +10 -> 113
    //   106: aload_3
    //   107: astore 5
    //   109: aload_2
    //   110: invokevirtual 135	java/io/InputStream:close	()V
    //   113: aload_3
    //   114: astore_2
    //   115: aload_1
    //   116: ifnull +12 -> 128
    //   119: aload_3
    //   120: astore 5
    //   122: aload_1
    //   123: invokevirtual 136	java/util/jar/JarFile:close	()V
    //   126: aload_3
    //   127: astore_2
    //   128: aload_2
    //   129: areturn
    //   130: astore_1
    //   131: aconst_null
    //   132: astore_1
    //   133: aload_3
    //   134: ifnull +11 -> 145
    //   137: aload 4
    //   139: astore 5
    //   141: aload_3
    //   142: invokevirtual 135	java/io/InputStream:close	()V
    //   145: aload 4
    //   147: astore_2
    //   148: aload_1
    //   149: ifnull -21 -> 128
    //   152: aload 4
    //   154: astore 5
    //   156: aload_1
    //   157: invokevirtual 136	java/util/jar/JarFile:close	()V
    //   160: ldc 84
    //   162: areturn
    //   163: astore_1
    //   164: ldc 84
    //   166: areturn
    //   167: astore_1
    //   168: aconst_null
    //   169: astore_3
    //   170: aconst_null
    //   171: astore_2
    //   172: aload_2
    //   173: ifnull +11 -> 184
    //   176: aload 4
    //   178: astore 5
    //   180: aload_2
    //   181: invokevirtual 135	java/io/InputStream:close	()V
    //   184: aload_3
    //   185: ifnull +11 -> 196
    //   188: aload 4
    //   190: astore 5
    //   192: aload_3
    //   193: invokevirtual 136	java/util/jar/JarFile:close	()V
    //   196: aload 4
    //   198: astore 5
    //   200: aload_1
    //   201: athrow
    //   202: astore_1
    //   203: aload 5
    //   205: areturn
    //   206: astore_2
    //   207: goto -11 -> 196
    //   210: astore 5
    //   212: aload_1
    //   213: astore_3
    //   214: aconst_null
    //   215: astore_2
    //   216: aload 5
    //   218: astore_1
    //   219: goto -47 -> 172
    //   222: astore 5
    //   224: aload_1
    //   225: astore_3
    //   226: aload 5
    //   228: astore_1
    //   229: goto -57 -> 172
    //   232: astore_2
    //   233: goto -100 -> 133
    //   236: astore_1
    //   237: aload_3
    //   238: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	239	0	this	aj
    //   0	239	1	paramString	String
    //   11	170	2	localObject1	Object
    //   206	1	2	localIOException	java.io.IOException
    //   215	1	2	localObject2	Object
    //   232	1	2	localException	Exception
    //   4	234	3	localObject3	Object
    //   7	190	4	str	String
    //   29	175	5	localObject4	Object
    //   210	7	5	localObject5	Object
    //   222	5	5	localObject6	Object
    //   1	50	6	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   41	50	130	java/lang/Exception
    //   141	145	163	java/io/IOException
    //   156	160	163	java/io/IOException
    //   41	50	167	finally
    //   31	41	202	android/content/pm/PackageManager$NameNotFoundException
    //   109	113	202	android/content/pm/PackageManager$NameNotFoundException
    //   122	126	202	android/content/pm/PackageManager$NameNotFoundException
    //   141	145	202	android/content/pm/PackageManager$NameNotFoundException
    //   156	160	202	android/content/pm/PackageManager$NameNotFoundException
    //   180	184	202	android/content/pm/PackageManager$NameNotFoundException
    //   192	196	202	android/content/pm/PackageManager$NameNotFoundException
    //   200	202	202	android/content/pm/PackageManager$NameNotFoundException
    //   180	184	206	java/io/IOException
    //   192	196	206	java/io/IOException
    //   53	64	210	finally
    //   66	74	222	finally
    //   76	83	222	finally
    //   85	99	222	finally
    //   53	64	232	java/lang/Exception
    //   66	74	232	java/lang/Exception
    //   76	83	232	java/lang/Exception
    //   85	99	232	java/lang/Exception
    //   109	113	236	java/io/IOException
    //   122	126	236	java/io/IOException
  }
  
  public String a(boolean paramBoolean)
  {
    if (this.c != null)
    {
      String str1 = null;
      if (!paramBoolean) {
        str1 = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
      }
      String str2 = str1;
      if (str1 == null)
      {
        str2 = UUID.randomUUID().toString();
        this.d = false;
      }
      return str2;
    }
    return "bnc_no_value";
  }
  
  public boolean a()
  {
    return this.d;
  }
  
  public boolean a(a paramA)
  {
    if (TextUtils.isEmpty(this.a))
    {
      new b(paramA).a(new Void[0]);
      return true;
    }
    return false;
  }
  
  public int b(Object paramObject)
  {
    for (;;)
    {
      try
      {
        if (!((Boolean)paramObject.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(paramObject, new Object[0])).booleanValue()) {
          continue;
        }
        i = 1;
        this.b = i;
      }
      catch (Exception paramObject)
      {
        int i;
        continue;
      }
      return this.b;
      i = 0;
    }
  }
  
  @SuppressLint({"NewApi"})
  public int b(boolean paramBoolean)
  {
    Object localObject = r.a(this.c);
    String str = e();
    if ("bnc_no_value".equals(((r)localObject).e()))
    {
      if (paramBoolean) {
        ((r)localObject).a(str);
      }
      if (Build.VERSION.SDK_INT < 9) {}
    }
    do
    {
      try
      {
        localObject = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
        long l1 = ((PackageInfo)localObject).lastUpdateTime;
        long l2 = ((PackageInfo)localObject).firstInstallTime;
        if (l1 != l2) {
          return 2;
        }
        return 0;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      return 0;
      if (localNameNotFoundException.e().equals(str)) {
        break;
      }
    } while (!paramBoolean);
    localNameNotFoundException.a(str);
    return 2;
    return 1;
  }
  
  public String b()
  {
    try
    {
      String str = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).packageName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public String c()
  {
    return a(this.c.getPackageName());
  }
  
  @SuppressLint({"NewApi"})
  public JSONArray d()
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager = this.c.getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    Iterator localIterator;
    if (localObject != null) {
      localIterator = ((List)localObject).iterator();
    }
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      JSONObject localJSONObject;
      if (localIterator.hasNext())
      {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo.flags & 0x1) != 1) {
          localJSONObject = new JSONObject();
        }
      }
      else
      {
        try
        {
          localObject = localApplicationInfo.loadLabel(localPackageManager);
          if (localObject == null) {}
          for (localObject = null;; localObject = ((CharSequence)localObject).toString())
          {
            if (localObject != null) {
              localJSONObject.put("name", localObject);
            }
            localObject = localApplicationInfo.packageName;
            if (localObject != null)
            {
              localJSONObject.put(n.a.N.a(), localObject);
              localObject = a((String)localObject);
              if (!((String)localObject).equals("bnc_no_value")) {
                localJSONObject.put(n.a.M.a(), localObject);
              }
            }
            localObject = localApplicationInfo.publicSourceDir;
            if (localObject != null) {
              localJSONObject.put("public_source_dir", localObject);
            }
            localObject = localApplicationInfo.sourceDir;
            if (localObject != null) {
              localJSONObject.put("source_dir", localObject);
            }
            localObject = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096);
            if (localObject != null)
            {
              if (((PackageInfo)localObject).versionCode >= 9)
              {
                localJSONObject.put("install_date", ((PackageInfo)localObject).firstInstallTime);
                localJSONObject.put("last_update_date", ((PackageInfo)localObject).lastUpdateTime);
              }
              localJSONObject.put("version_code", ((PackageInfo)localObject).versionCode);
              if (((PackageInfo)localObject).versionName != null) {
                localJSONObject.put("version_name", ((PackageInfo)localObject).versionName);
              }
            }
            localJSONObject.put(n.a.F.a(), h());
            localJSONArray.put(localJSONObject);
            break;
          }
          return localJSONArray;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}catch (JSONException localJSONException) {}
      }
    }
  }
  
  public String e()
  {
    try
    {
      Object localObject = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
      if (((PackageInfo)localObject).versionName != null)
      {
        localObject = ((PackageInfo)localObject).versionName;
        return localObject;
      }
      return "bnc_no_value";
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "bnc_no_value";
  }
  
  public String f()
  {
    return Build.MANUFACTURER;
  }
  
  public String g()
  {
    return Build.MODEL;
  }
  
  public String h()
  {
    return "Android";
  }
  
  public int i()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public DisplayMetrics j()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)this.c.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public boolean k()
  {
    if (this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.c.getSystemService("connectivity")).getNetworkInfo(1);
      return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
    }
    return false;
  }
  
  public Object l()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.c });
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  static abstract interface a
  {
    public abstract void c();
  }
  
  private class b
    extends f<Void, Void, Void>
  {
    private final aj.a b;
    
    public b(aj.a paramA)
    {
      this.b = paramA;
    }
    
    protected Void a(final Void... paramVarArgs)
    {
      paramVarArgs = new CountDownLatch(1);
      new Thread(new Runnable()
      {
        public void run()
        {
          Process.setThreadPriority(-19);
          Object localObject = aj.this.l();
          aj.this.a(localObject);
          aj.this.b(localObject);
          paramVarArgs.countDown();
        }
      }).start();
      try
      {
        paramVarArgs.await(1500L, TimeUnit.MILLISECONDS);
        return null;
      }
      catch (InterruptedException paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void a(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (this.b != null) {
        this.b.c();
      }
    }
  }
}
