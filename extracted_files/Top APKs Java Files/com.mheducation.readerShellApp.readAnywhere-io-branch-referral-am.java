package io.branch.referral;

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

class am
{
  String a = null;
  int b = 0;
  private Context c;
  private boolean d;
  
  public am(Context paramContext)
  {
    this.c = paramContext;
    this.d = true;
  }
  
  private boolean l()
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
    //   0: aload_0
    //   1: invokespecial 84	io/branch/referral/am:l	()Z
    //   4: ifne +178 -> 182
    //   7: aload_0
    //   8: getfield 29	io/branch/referral/am:c	Landroid/content/Context;
    //   11: invokevirtual 88	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore_2
    //   15: aload_2
    //   16: aload_1
    //   17: iconst_0
    //   18: invokevirtual 94	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   21: getfield 99	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   24: astore_3
    //   25: aconst_null
    //   26: astore_2
    //   27: aconst_null
    //   28: astore_1
    //   29: aconst_null
    //   30: astore 4
    //   32: aconst_null
    //   33: astore 5
    //   35: new 101	java/util/jar/JarFile
    //   38: dup
    //   39: aload_3
    //   40: invokespecial 104	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   43: astore_3
    //   44: aload 5
    //   46: astore_1
    //   47: aload 4
    //   49: astore_2
    //   50: aload_3
    //   51: aload_3
    //   52: ldc 106
    //   54: invokevirtual 110	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   57: invokevirtual 114	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   60: astore 4
    //   62: aload 4
    //   64: astore_1
    //   65: aload 4
    //   67: astore_2
    //   68: aload 4
    //   70: invokevirtual 120	java/io/InputStream:available	()I
    //   73: newarray byte
    //   75: astore 5
    //   77: aload 4
    //   79: astore_1
    //   80: aload 4
    //   82: astore_2
    //   83: aload 4
    //   85: aload 5
    //   87: invokevirtual 124	java/io/InputStream:read	([B)I
    //   90: pop
    //   91: aload 4
    //   93: astore_1
    //   94: aload 4
    //   96: astore_2
    //   97: new 126	io/branch/referral/b
    //   100: dup
    //   101: invokespecial 127	io/branch/referral/b:<init>	()V
    //   104: aload 5
    //   106: invokevirtual 130	io/branch/referral/b:a	([B)Ljava/lang/String;
    //   109: astore 5
    //   111: aload 4
    //   113: ifnull +11 -> 124
    //   116: aload 4
    //   118: invokevirtual 133	java/io/InputStream:close	()V
    //   121: goto +3 -> 124
    //   124: aload_3
    //   125: invokevirtual 134	java/util/jar/JarFile:close	()V
    //   128: aload 5
    //   130: areturn
    //   131: astore 4
    //   133: aload_1
    //   134: astore_2
    //   135: aload 4
    //   137: astore_1
    //   138: goto +6 -> 144
    //   141: astore_1
    //   142: aconst_null
    //   143: astore_3
    //   144: aload_2
    //   145: ifnull +7 -> 152
    //   148: aload_2
    //   149: invokevirtual 133	java/io/InputStream:close	()V
    //   152: aload_3
    //   153: ifnull +7 -> 160
    //   156: aload_3
    //   157: invokevirtual 134	java/util/jar/JarFile:close	()V
    //   160: aload_1
    //   161: athrow
    //   162: aconst_null
    //   163: astore_3
    //   164: aload_1
    //   165: astore_2
    //   166: aload_2
    //   167: ifnull +7 -> 174
    //   170: aload_2
    //   171: invokevirtual 133	java/io/InputStream:close	()V
    //   174: aload_3
    //   175: ifnull +7 -> 182
    //   178: aload_3
    //   179: invokevirtual 134	java/util/jar/JarFile:close	()V
    //   182: ldc -120
    //   184: areturn
    //   185: astore_1
    //   186: ldc -120
    //   188: areturn
    //   189: astore_2
    //   190: goto -28 -> 162
    //   193: astore_1
    //   194: goto -28 -> 166
    //   197: astore_1
    //   198: goto -70 -> 128
    //   201: astore_1
    //   202: goto +7 -> 209
    //   205: astore_2
    //   206: goto -46 -> 160
    //   209: aload 5
    //   211: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	212	0	this	am
    //   0	212	1	paramString	String
    //   14	157	2	localObject1	Object
    //   189	1	2	localException	Exception
    //   205	1	2	localIOException	java.io.IOException
    //   24	155	3	localObject2	Object
    //   30	87	4	localInputStream	java.io.InputStream
    //   131	5	4	localObject3	Object
    //   33	177	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   50	62	131	finally
    //   68	77	131	finally
    //   83	91	131	finally
    //   97	111	131	finally
    //   35	44	141	finally
    //   15	25	185	android/content/pm/PackageManager$NameNotFoundException
    //   148	152	185	android/content/pm/PackageManager$NameNotFoundException
    //   156	160	185	android/content/pm/PackageManager$NameNotFoundException
    //   160	162	185	android/content/pm/PackageManager$NameNotFoundException
    //   170	174	185	java/io/IOException
    //   170	174	185	android/content/pm/PackageManager$NameNotFoundException
    //   178	182	185	java/io/IOException
    //   178	182	185	android/content/pm/PackageManager$NameNotFoundException
    //   35	44	189	java/lang/Exception
    //   50	62	193	java/lang/Exception
    //   68	77	193	java/lang/Exception
    //   83	91	193	java/lang/Exception
    //   97	111	193	java/lang/Exception
    //   116	121	197	java/io/IOException
    //   124	128	197	java/io/IOException
    //   116	121	201	android/content/pm/PackageManager$NameNotFoundException
    //   124	128	201	android/content/pm/PackageManager$NameNotFoundException
    //   148	152	205	java/io/IOException
    //   156	160	205	java/io/IOException
  }
  
  public String a(boolean paramBoolean)
  {
    Object localObject = this.c;
    if (localObject != null)
    {
      String str = null;
      if (!paramBoolean) {
        str = Settings.Secure.getString(((Context)localObject).getContentResolver(), "android_id");
      }
      localObject = str;
      if (str == null)
      {
        localObject = UUID.randomUUID().toString();
        this.d = false;
      }
      return localObject;
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
    try
    {
      Class localClass = paramObject.getClass();
      int i = 0;
      if (((Boolean)localClass.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(paramObject, new Object[0])).booleanValue()) {
        i = 1;
      }
      this.b = i;
    }
    catch (Exception paramObject)
    {
      for (;;) {}
    }
    return this.b;
  }
  
  @SuppressLint({"NewApi"})
  public int b(boolean paramBoolean)
  {
    Object localObject = p.a(this.c);
    String str = d();
    if ("bnc_no_value".equals(((p)localObject).e()))
    {
      if (paramBoolean) {
        ((p)localObject).a(str);
      }
      if (Build.VERSION.SDK_INT < 9) {}
    }
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
    if (!((p)localObject).e().equals(str))
    {
      if (paramBoolean) {
        ((p)localObject).a(str);
      }
      return 2;
    }
    return 1;
    return 0;
  }
  
  public String b()
  {
    return a(this.c.getPackageName());
  }
  
  @SuppressLint({"NewApi"})
  public JSONArray c()
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
          if (localObject == null) {
            localObject = null;
          } else {
            localObject = ((CharSequence)localObject).toString();
          }
          if (localObject != null) {
            localJSONObject.put("name", localObject);
          }
          localObject = localApplicationInfo.packageName;
          if (localObject != null)
          {
            localJSONObject.put(m.a.M.a(), localObject);
            localObject = a((String)localObject);
            if (!((String)localObject).equals("bnc_no_value")) {
              localJSONObject.put(m.a.L.a(), localObject);
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
          localJSONObject.put(m.a.E.a(), g());
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException|PackageManager.NameNotFoundException localJSONException) {}
        return localJSONArray;
      }
    }
  }
  
  public String d()
  {
    try
    {
      PackageInfo localPackageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
      if (localPackageInfo.versionName != null) {
        return localPackageInfo.versionName;
      }
      return "bnc_no_value";
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return "bnc_no_value";
  }
  
  public String e()
  {
    return Build.MANUFACTURER;
  }
  
  public String f()
  {
    return Build.MODEL;
  }
  
  public String g()
  {
    return "Android";
  }
  
  public int h()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public DisplayMetrics i()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)this.c.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public boolean j()
  {
    int i = this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
    boolean bool2 = false;
    if (i == 0)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.c.getSystemService("connectivity")).getNetworkInfo(1);
      boolean bool1 = bool2;
      if (localNetworkInfo != null)
      {
        bool1 = bool2;
        if (localNetworkInfo.isConnected()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public Object k()
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
    private final am.a b;
    
    public b(am.a paramA)
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
          Object localObject = am.this.k();
          am.this.a(localObject);
          am.this.b(localObject);
          paramVarArgs.countDown();
        }
      }).start();
      try
      {
        paramVarArgs.await(1500L, TimeUnit.MILLISECONDS);
      }
      catch (InterruptedException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      paramVoid = this.b;
      if (paramVoid != null) {
        paramVoid.c();
      }
    }
  }
}
