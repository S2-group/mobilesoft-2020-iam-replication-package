package io.branch.referral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class aj
{
  String a = null;
  int b = 0;
  Context c;
  boolean d;
  
  public aj(Context paramContext)
  {
    this.c = paramContext;
    this.d = true;
  }
  
  public static String f()
  {
    Object localObject1 = "";
    Object localObject2 = localObject1;
    try
    {
      Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      localObject2 = localObject1;
      if (localIterator1.hasNext())
      {
        localObject2 = localObject1;
        Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
        label123:
        for (;;)
        {
          localObject2 = localObject1;
          if (!localIterator2.hasNext()) {
            break;
          }
          localObject2 = localObject1;
          Object localObject3 = (InetAddress)localIterator2.next();
          localObject2 = localObject1;
          if (!((InetAddress)localObject3).isLoopbackAddress())
          {
            localObject2 = localObject1;
            localObject3 = ((InetAddress)localObject3).getHostAddress();
            localObject2 = localObject1;
            int i = ((String)localObject3).indexOf(':');
            if (i < 0) {}
            for (i = 1;; i = 0)
            {
              if (i == 0) {
                break label123;
              }
              localObject1 = localObject3;
              break;
            }
          }
        }
      }
      return localObject1;
    }
    catch (Throwable localThrowable) {}
    return localObject2;
  }
  
  public final String a()
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
  
  public final String a(Object paramObject)
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
  public final String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_3
    //   7: aload_0
    //   8: getfield 29	io/branch/referral/aj:c	Landroid/content/Context;
    //   11: ldc -122
    //   13: invokevirtual 138	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   16: checkcast 140	android/app/ActivityManager
    //   19: astore 5
    //   21: new 142	android/app/ActivityManager$MemoryInfo
    //   24: dup
    //   25: invokespecial 143	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   28: astore 6
    //   30: aload 5
    //   32: aload 6
    //   34: invokevirtual 147	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   37: aload 6
    //   39: getfield 150	android/app/ActivityManager$MemoryInfo:lowMemory	Z
    //   42: ifne +186 -> 228
    //   45: aload_0
    //   46: getfield 29	io/branch/referral/aj:c	Landroid/content/Context;
    //   49: invokevirtual 91	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   52: astore 5
    //   54: aload 5
    //   56: aload_1
    //   57: iconst_0
    //   58: invokevirtual 154	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   61: getfield 159	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   64: astore_1
    //   65: new 161	java/util/jar/JarFile
    //   68: dup
    //   69: aload_1
    //   70: invokespecial 164	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   73: astore_1
    //   74: aload 4
    //   76: astore_2
    //   77: aload_1
    //   78: aload_1
    //   79: ldc -90
    //   81: invokevirtual 170	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   84: invokevirtual 174	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   87: astore_3
    //   88: aload_3
    //   89: astore_2
    //   90: aload_3
    //   91: invokevirtual 180	java/io/InputStream:available	()I
    //   94: newarray byte
    //   96: astore 4
    //   98: aload_3
    //   99: astore_2
    //   100: aload_3
    //   101: aload 4
    //   103: invokevirtual 184	java/io/InputStream:read	([B)I
    //   106: pop
    //   107: aload_3
    //   108: astore_2
    //   109: new 186	io/branch/referral/b
    //   112: dup
    //   113: invokespecial 187	io/branch/referral/b:<init>	()V
    //   116: pop
    //   117: aload_3
    //   118: astore_2
    //   119: aload 4
    //   121: invokestatic 190	io/branch/referral/b:a	([B)Ljava/lang/String;
    //   124: astore 4
    //   126: aload_3
    //   127: ifnull +7 -> 134
    //   130: aload_3
    //   131: invokevirtual 193	java/io/InputStream:close	()V
    //   134: aload_1
    //   135: invokevirtual 194	java/util/jar/JarFile:close	()V
    //   138: aload 4
    //   140: areturn
    //   141: astore_1
    //   142: aconst_null
    //   143: astore_1
    //   144: aload_3
    //   145: astore_2
    //   146: aload_1
    //   147: ifnull +7 -> 154
    //   150: aload_1
    //   151: invokevirtual 193	java/io/InputStream:close	()V
    //   154: aload_2
    //   155: ifnull +7 -> 162
    //   158: aload_2
    //   159: invokevirtual 194	java/util/jar/JarFile:close	()V
    //   162: ldc -60
    //   164: areturn
    //   165: astore_1
    //   166: ldc -60
    //   168: areturn
    //   169: astore_3
    //   170: aconst_null
    //   171: astore_1
    //   172: aload_2
    //   173: ifnull +7 -> 180
    //   176: aload_2
    //   177: invokevirtual 193	java/io/InputStream:close	()V
    //   180: aload_1
    //   181: ifnull +7 -> 188
    //   184: aload_1
    //   185: invokevirtual 194	java/util/jar/JarFile:close	()V
    //   188: aload_3
    //   189: athrow
    //   190: astore_1
    //   191: ldc -60
    //   193: areturn
    //   194: astore_1
    //   195: aload 4
    //   197: areturn
    //   198: astore_1
    //   199: goto -11 -> 188
    //   202: astore_3
    //   203: goto -31 -> 172
    //   206: astore_2
    //   207: aconst_null
    //   208: astore_3
    //   209: aload_1
    //   210: astore_2
    //   211: aload_3
    //   212: astore_1
    //   213: goto -67 -> 146
    //   216: astore_2
    //   217: aload_1
    //   218: astore_2
    //   219: aload_3
    //   220: astore_1
    //   221: goto -75 -> 146
    //   224: astore_1
    //   225: aload 4
    //   227: areturn
    //   228: ldc -60
    //   230: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	this	aj
    //   0	231	1	paramString	String
    //   1	176	2	localObject1	Object
    //   206	1	2	localException1	Exception
    //   210	1	2	str1	String
    //   216	1	2	localException2	Exception
    //   218	1	2	str2	String
    //   6	139	3	localInputStream	java.io.InputStream
    //   169	20	3	localObject2	Object
    //   202	1	3	localObject3	Object
    //   208	12	3	localObject4	Object
    //   3	223	4	localObject5	Object
    //   19	36	5	localObject6	Object
    //   28	10	6	localMemoryInfo	android.app.ActivityManager.MemoryInfo
    // Exception table:
    //   from	to	target	type
    //   65	74	141	java/lang/Exception
    //   150	154	165	java/io/IOException
    //   158	162	165	java/io/IOException
    //   65	74	169	finally
    //   54	65	190	android/content/pm/PackageManager$NameNotFoundException
    //   150	154	190	android/content/pm/PackageManager$NameNotFoundException
    //   158	162	190	android/content/pm/PackageManager$NameNotFoundException
    //   176	180	190	android/content/pm/PackageManager$NameNotFoundException
    //   184	188	190	android/content/pm/PackageManager$NameNotFoundException
    //   188	190	190	android/content/pm/PackageManager$NameNotFoundException
    //   130	134	194	android/content/pm/PackageManager$NameNotFoundException
    //   134	138	194	android/content/pm/PackageManager$NameNotFoundException
    //   176	180	198	java/io/IOException
    //   184	188	198	java/io/IOException
    //   77	88	202	finally
    //   90	98	202	finally
    //   100	107	202	finally
    //   109	117	202	finally
    //   119	126	202	finally
    //   77	88	206	java/lang/Exception
    //   90	98	216	java/lang/Exception
    //   100	107	216	java/lang/Exception
    //   109	117	216	java/lang/Exception
    //   119	126	216	java/lang/Exception
    //   130	134	224	java/io/IOException
    //   134	138	224	java/io/IOException
  }
  
  public final String a(boolean paramBoolean)
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
  
  public final int b(Object paramObject)
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
  public final JSONArray b()
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
              localJSONObject.put(o.a.U.aW, localObject);
              localObject = a((String)localObject);
              if (!((String)localObject).equals("bnc_no_value")) {
                localJSONObject.put(o.a.T.aW, localObject);
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
            localJSONObject.put(o.a.H.aW, "Android");
            localJSONArray.put(localJSONObject);
            break;
          }
          return localJSONArray;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}catch (JSONException localJSONException) {}
      }
    }
  }
  
  public final String c()
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
  
  @SuppressLint({"NewApi"})
  public final int d()
  {
    r.a(this.c);
    Object localObject = c();
    if ("bnc_no_value".equals(r.d("bnc_app_version"))) {
      if (Build.VERSION.SDK_INT < 9) {}
    }
    while (!r.d("bnc_app_version").equals(localNameNotFoundException))
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
    }
    return 1;
  }
  
  public final Object e()
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
  
  private final class b
    extends e<Void, Void, Void>
  {
    private final aj.a b;
    
    public b(aj.a paramA)
    {
      this.b = paramA;
    }
    
    private Void a()
    {
      final CountDownLatch localCountDownLatch = new CountDownLatch(1);
      new Thread(new Runnable()
      {
        public final void run()
        {
          Process.setThreadPriority(-19);
          Object localObject = aj.this.e();
          aj.this.a(localObject);
          aj.this.b(localObject);
          localCountDownLatch.countDown();
        }
      }).start();
      try
      {
        localCountDownLatch.await(1500L, TimeUnit.MILLISECONDS);
        return null;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
  }
}
