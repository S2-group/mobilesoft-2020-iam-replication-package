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
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class am
{
  private Context a;
  private boolean b;
  
  public am(Context paramContext)
  {
    this.a = paramContext;
    this.b = true;
  }
  
  private boolean m()
  {
    ActivityManager localActivityManager = (ActivityManager)this.a.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  /* Error */
  public String a(String paramString)
  {
    // Byte code:
    //   0: ldc 49
    //   2: astore_3
    //   3: aload_3
    //   4: astore_2
    //   5: aload_0
    //   6: invokespecial 51	io/branch/referral/am:m	()Z
    //   9: ifne +151 -> 160
    //   12: aload_0
    //   13: getfield 15	io/branch/referral/am:a	Landroid/content/Context;
    //   16: invokevirtual 55	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   19: astore 4
    //   21: aload_3
    //   22: astore_2
    //   23: aload 4
    //   25: aload_1
    //   26: iconst_0
    //   27: invokevirtual 61	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   30: getfield 67	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   33: astore 4
    //   35: aconst_null
    //   36: astore 6
    //   38: aconst_null
    //   39: astore_1
    //   40: aconst_null
    //   41: astore_2
    //   42: aconst_null
    //   43: astore 8
    //   45: aconst_null
    //   46: astore 7
    //   48: aconst_null
    //   49: astore 5
    //   51: new 69	java/util/jar/JarFile
    //   54: dup
    //   55: aload 4
    //   57: invokespecial 72	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   60: astore 4
    //   62: aload 8
    //   64: astore_1
    //   65: aload 7
    //   67: astore_2
    //   68: aload 4
    //   70: aload 4
    //   72: ldc 74
    //   74: invokevirtual 78	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   77: invokevirtual 82	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   80: astore 5
    //   82: aload 5
    //   84: astore_1
    //   85: aload 5
    //   87: astore_2
    //   88: aload 5
    //   90: invokevirtual 88	java/io/InputStream:available	()I
    //   93: newarray byte
    //   95: astore 6
    //   97: aload 5
    //   99: astore_1
    //   100: aload 5
    //   102: astore_2
    //   103: aload 5
    //   105: aload 6
    //   107: invokevirtual 92	java/io/InputStream:read	([B)I
    //   110: pop
    //   111: aload 5
    //   113: astore_1
    //   114: aload 5
    //   116: astore_2
    //   117: new 94	io/branch/referral/b
    //   120: dup
    //   121: invokespecial 95	io/branch/referral/b:<init>	()V
    //   124: aload 6
    //   126: invokevirtual 98	io/branch/referral/b:a	([B)Ljava/lang/String;
    //   129: astore 6
    //   131: aload 6
    //   133: astore_1
    //   134: aload 5
    //   136: ifnull +10 -> 146
    //   139: aload_1
    //   140: astore_2
    //   141: aload 5
    //   143: invokevirtual 101	java/io/InputStream:close	()V
    //   146: aload 4
    //   148: ifnull +10 -> 158
    //   151: aload_1
    //   152: astore_2
    //   153: aload 4
    //   155: invokevirtual 102	java/util/jar/JarFile:close	()V
    //   158: aload_1
    //   159: astore_2
    //   160: aload_2
    //   161: areturn
    //   162: astore_2
    //   163: aload_1
    //   164: areturn
    //   165: astore_2
    //   166: aload 5
    //   168: astore 4
    //   170: aload 4
    //   172: ifnull +10 -> 182
    //   175: aload_3
    //   176: astore_2
    //   177: aload 4
    //   179: invokevirtual 101	java/io/InputStream:close	()V
    //   182: aload_3
    //   183: astore_2
    //   184: aload_1
    //   185: ifnull -25 -> 160
    //   188: aload_3
    //   189: astore_2
    //   190: aload_1
    //   191: invokevirtual 102	java/util/jar/JarFile:close	()V
    //   194: ldc 49
    //   196: areturn
    //   197: astore_1
    //   198: ldc 49
    //   200: areturn
    //   201: astore_1
    //   202: aload 6
    //   204: astore 5
    //   206: aload_2
    //   207: astore 4
    //   209: aload 4
    //   211: ifnull +10 -> 221
    //   214: aload_3
    //   215: astore_2
    //   216: aload 4
    //   218: invokevirtual 101	java/io/InputStream:close	()V
    //   221: aload 5
    //   223: ifnull +10 -> 233
    //   226: aload_3
    //   227: astore_2
    //   228: aload 5
    //   230: invokevirtual 102	java/util/jar/JarFile:close	()V
    //   233: aload_3
    //   234: astore_2
    //   235: aload_1
    //   236: athrow
    //   237: astore_1
    //   238: aload_2
    //   239: areturn
    //   240: astore_2
    //   241: goto -8 -> 233
    //   244: astore_2
    //   245: aload 4
    //   247: astore 5
    //   249: aload_1
    //   250: astore 4
    //   252: aload_2
    //   253: astore_1
    //   254: goto -45 -> 209
    //   257: astore_1
    //   258: aload 4
    //   260: astore_1
    //   261: aload_2
    //   262: astore 4
    //   264: goto -94 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	267	0	this	am
    //   0	267	1	paramString	String
    //   4	157	2	localObject1	Object
    //   162	1	2	localIOException1	java.io.IOException
    //   165	1	2	localException	Exception
    //   176	63	2	str1	String
    //   240	1	2	localIOException2	java.io.IOException
    //   244	18	2	localObject2	Object
    //   2	232	3	str2	String
    //   19	244	4	localObject3	Object
    //   49	199	5	localObject4	Object
    //   36	167	6	localObject5	Object
    //   46	20	7	localObject6	Object
    //   43	20	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   141	146	162	java/io/IOException
    //   153	158	162	java/io/IOException
    //   51	62	165	java/lang/Exception
    //   177	182	197	java/io/IOException
    //   190	194	197	java/io/IOException
    //   51	62	201	finally
    //   23	35	237	android/content/pm/PackageManager$NameNotFoundException
    //   141	146	237	android/content/pm/PackageManager$NameNotFoundException
    //   153	158	237	android/content/pm/PackageManager$NameNotFoundException
    //   177	182	237	android/content/pm/PackageManager$NameNotFoundException
    //   190	194	237	android/content/pm/PackageManager$NameNotFoundException
    //   216	221	237	android/content/pm/PackageManager$NameNotFoundException
    //   228	233	237	android/content/pm/PackageManager$NameNotFoundException
    //   235	237	237	android/content/pm/PackageManager$NameNotFoundException
    //   216	221	240	java/io/IOException
    //   228	233	240	java/io/IOException
    //   68	82	244	finally
    //   88	97	244	finally
    //   103	111	244	finally
    //   117	131	244	finally
    //   68	82	257	java/lang/Exception
    //   88	97	257	java/lang/Exception
    //   103	111	257	java/lang/Exception
    //   117	131	257	java/lang/Exception
  }
  
  public String a(boolean paramBoolean)
  {
    if (this.a != null)
    {
      String str1 = null;
      if (!paramBoolean) {
        str1 = Settings.Secure.getString(this.a.getContentResolver(), "android_id");
      }
      String str2 = str1;
      if (str1 == null)
      {
        str2 = UUID.randomUUID().toString();
        this.b = false;
      }
      return str2;
    }
    return "bnc_no_value";
  }
  
  public boolean a()
  {
    return this.b;
  }
  
  @SuppressLint({"NewApi"})
  public int b(boolean paramBoolean)
  {
    Object localObject = p.a(this.a);
    String str = d();
    if ("bnc_no_value".equals(((p)localObject).e()))
    {
      if (paramBoolean) {
        ((p)localObject).a(str);
      }
      if (Build.VERSION.SDK_INT < 9) {}
    }
    do
    {
      try
      {
        localObject = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0);
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
    return a(this.a.getPackageName());
  }
  
  @SuppressLint({"NewApi"})
  public JSONArray c()
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager = this.a.getPackageManager();
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
              localJSONObject.put(n.a.J.a(), localObject);
              localObject = a((String)localObject);
              if (!((String)localObject).equals("bnc_no_value")) {
                localJSONObject.put(n.a.I.a(), localObject);
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
            localJSONObject.put(n.a.B.a(), g());
            localJSONArray.put(localJSONObject);
            break;
          }
          return localJSONArray;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}catch (JSONException localJSONException) {}
      }
    }
  }
  
  public String d()
  {
    try
    {
      PackageInfo localPackageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0);
      if (localPackageInfo.versionName != null) {
        return localPackageInfo.versionName;
      }
      return "bnc_no_value";
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
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
    ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public boolean j()
  {
    if (this.a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getNetworkInfo(1);
      return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
    }
    return false;
  }
  
  public String k()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.a });
      localObject = (String)localObject.getClass().getMethod("getId", new Class[0]).invoke(localObject, new Object[0]);
      return localObject;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
      return null;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public int l()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.a });
      boolean bool = ((Boolean)localObject.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
      if (bool) {
        return 1;
      }
      return 0;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
      return 0;
    }
    catch (Exception localException) {}
    return 0;
  }
}
