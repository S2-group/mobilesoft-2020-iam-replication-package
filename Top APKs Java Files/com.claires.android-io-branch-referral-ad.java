package io.branch.referral;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.bluetooth.BluetoothAdapter;
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
import android.telephony.TelephonyManager;
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

class ad
{
  private Context a;
  private boolean b;
  
  public ad(Context paramContext)
  {
    this.a = paramContext;
    this.b = true;
  }
  
  private boolean s()
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
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 7
    //   9: aconst_null
    //   10: astore_3
    //   11: ldc 51
    //   13: astore 5
    //   15: aload 5
    //   17: astore_2
    //   18: aload_0
    //   19: invokespecial 53	io/branch/referral/ad:s	()Z
    //   22: ifne +125 -> 147
    //   25: aload_0
    //   26: getfield 15	io/branch/referral/ad:a	Landroid/content/Context;
    //   29: invokevirtual 57	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   32: astore_2
    //   33: aload 5
    //   35: astore 4
    //   37: aload_2
    //   38: aload_1
    //   39: iconst_0
    //   40: invokevirtual 63	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   43: getfield 69	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   46: astore_1
    //   47: new 71	java/util/jar/JarFile
    //   50: dup
    //   51: aload_1
    //   52: invokespecial 74	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   55: astore_1
    //   56: aload 8
    //   58: astore_3
    //   59: aload 7
    //   61: astore 4
    //   63: aload_1
    //   64: aload_1
    //   65: ldc 76
    //   67: invokevirtual 80	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   70: invokevirtual 84	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   73: astore_2
    //   74: aload_2
    //   75: astore_3
    //   76: aload_2
    //   77: astore 4
    //   79: aload_2
    //   80: invokevirtual 90	java/io/InputStream:available	()I
    //   83: newarray byte
    //   85: astore 6
    //   87: aload_2
    //   88: astore_3
    //   89: aload_2
    //   90: astore 4
    //   92: aload_2
    //   93: aload 6
    //   95: invokevirtual 94	java/io/InputStream:read	([B)I
    //   98: pop
    //   99: aload_2
    //   100: astore_3
    //   101: aload_2
    //   102: astore 4
    //   104: new 96	io/branch/referral/a
    //   107: dup
    //   108: invokespecial 97	io/branch/referral/a:<init>	()V
    //   111: aload 6
    //   113: invokevirtual 100	io/branch/referral/a:a	([B)Ljava/lang/String;
    //   116: astore 6
    //   118: aload 6
    //   120: astore_3
    //   121: aload_2
    //   122: ifnull +10 -> 132
    //   125: aload_3
    //   126: astore 4
    //   128: aload_2
    //   129: invokevirtual 103	java/io/InputStream:close	()V
    //   132: aload_3
    //   133: astore_2
    //   134: aload_1
    //   135: ifnull +12 -> 147
    //   138: aload_3
    //   139: astore 4
    //   141: aload_1
    //   142: invokevirtual 104	java/util/jar/JarFile:close	()V
    //   145: aload_3
    //   146: astore_2
    //   147: aload_2
    //   148: areturn
    //   149: astore_1
    //   150: aconst_null
    //   151: astore_1
    //   152: aload_3
    //   153: astore_2
    //   154: aload_2
    //   155: ifnull +11 -> 166
    //   158: aload 5
    //   160: astore 4
    //   162: aload_2
    //   163: invokevirtual 103	java/io/InputStream:close	()V
    //   166: aload 5
    //   168: astore_2
    //   169: aload_1
    //   170: ifnull -23 -> 147
    //   173: aload 5
    //   175: astore 4
    //   177: aload_1
    //   178: invokevirtual 104	java/util/jar/JarFile:close	()V
    //   181: ldc 51
    //   183: areturn
    //   184: astore_1
    //   185: ldc 51
    //   187: areturn
    //   188: astore_1
    //   189: aconst_null
    //   190: astore_1
    //   191: aload 6
    //   193: astore_3
    //   194: aload_3
    //   195: ifnull +11 -> 206
    //   198: aload 5
    //   200: astore 4
    //   202: aload_3
    //   203: invokevirtual 103	java/io/InputStream:close	()V
    //   206: aload 5
    //   208: astore_2
    //   209: aload_1
    //   210: ifnull -63 -> 147
    //   213: aload 5
    //   215: astore 4
    //   217: aload_1
    //   218: invokevirtual 104	java/util/jar/JarFile:close	()V
    //   221: ldc 51
    //   223: areturn
    //   224: astore_1
    //   225: ldc 51
    //   227: areturn
    //   228: astore_1
    //   229: aconst_null
    //   230: astore_3
    //   231: aconst_null
    //   232: astore_2
    //   233: aload_2
    //   234: ifnull +11 -> 245
    //   237: aload 5
    //   239: astore 4
    //   241: aload_2
    //   242: invokevirtual 103	java/io/InputStream:close	()V
    //   245: aload_3
    //   246: ifnull +11 -> 257
    //   249: aload 5
    //   251: astore 4
    //   253: aload_3
    //   254: invokevirtual 104	java/util/jar/JarFile:close	()V
    //   257: aload 5
    //   259: astore 4
    //   261: aload_1
    //   262: athrow
    //   263: astore_1
    //   264: aload 4
    //   266: areturn
    //   267: astore_2
    //   268: goto -11 -> 257
    //   271: astore 4
    //   273: aload_1
    //   274: astore_3
    //   275: aconst_null
    //   276: astore_2
    //   277: aload 4
    //   279: astore_1
    //   280: goto -47 -> 233
    //   283: astore 4
    //   285: aload_1
    //   286: astore_3
    //   287: aload 4
    //   289: astore_1
    //   290: goto -57 -> 233
    //   293: astore_2
    //   294: goto -100 -> 194
    //   297: astore_2
    //   298: aload 4
    //   300: astore_2
    //   301: goto -147 -> 154
    //   304: astore_1
    //   305: aload_3
    //   306: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	this	ad
    //   0	307	1	paramString	String
    //   17	225	2	localObject1	Object
    //   267	1	2	localIOException	java.io.IOException
    //   276	1	2	localObject2	Object
    //   293	1	2	localOutOfMemoryError	OutOfMemoryError
    //   297	1	2	localException	Exception
    //   300	1	2	localObject3	Object
    //   10	296	3	localObject4	Object
    //   35	230	4	localObject5	Object
    //   271	7	4	localObject6	Object
    //   283	16	4	localObject7	Object
    //   13	245	5	str	String
    //   1	191	6	localObject8	Object
    //   7	53	7	localObject9	Object
    //   4	53	8	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   47	56	149	java/lang/Exception
    //   162	166	184	java/io/IOException
    //   177	181	184	java/io/IOException
    //   47	56	188	java/lang/OutOfMemoryError
    //   202	206	224	java/io/IOException
    //   217	221	224	java/io/IOException
    //   47	56	228	finally
    //   37	47	263	android/content/pm/PackageManager$NameNotFoundException
    //   128	132	263	android/content/pm/PackageManager$NameNotFoundException
    //   141	145	263	android/content/pm/PackageManager$NameNotFoundException
    //   162	166	263	android/content/pm/PackageManager$NameNotFoundException
    //   177	181	263	android/content/pm/PackageManager$NameNotFoundException
    //   202	206	263	android/content/pm/PackageManager$NameNotFoundException
    //   217	221	263	android/content/pm/PackageManager$NameNotFoundException
    //   241	245	263	android/content/pm/PackageManager$NameNotFoundException
    //   253	257	263	android/content/pm/PackageManager$NameNotFoundException
    //   261	263	263	android/content/pm/PackageManager$NameNotFoundException
    //   241	245	267	java/io/IOException
    //   253	257	267	java/io/IOException
    //   63	74	271	finally
    //   79	87	283	finally
    //   92	99	283	finally
    //   104	118	283	finally
    //   63	74	293	java/lang/OutOfMemoryError
    //   79	87	293	java/lang/OutOfMemoryError
    //   92	99	293	java/lang/OutOfMemoryError
    //   104	118	293	java/lang/OutOfMemoryError
    //   63	74	297	java/lang/Exception
    //   79	87	297	java/lang/Exception
    //   92	99	297	java/lang/Exception
    //   104	118	297	java/lang/Exception
    //   128	132	304	java/io/IOException
    //   141	145	304	java/io/IOException
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
    Object localObject = i.a(this.a);
    String str = d();
    if ("bnc_no_value".equals(((i)localObject).e()))
    {
      if (paramBoolean) {
        ((i)localObject).a(str);
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
              localJSONObject.put(g.a.H.a(), localObject);
              localObject = a((String)localObject);
              if (!((String)localObject).equals("bnc_no_value")) {
                localJSONObject.put(g.a.G.a(), localObject);
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
            localJSONObject.put(g.a.z.a(), l());
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
    Object localObject = (TelephonyManager)this.a.getSystemService("phone");
    if (localObject != null)
    {
      localObject = ((TelephonyManager)localObject).getNetworkOperatorName();
      if (localObject != null) {
        return localObject;
      }
    }
    return "bnc_no_value";
  }
  
  public boolean f()
  {
    try
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter != null)
      {
        boolean bool = localBluetoothAdapter.isEnabled();
        return bool;
      }
    }
    catch (SecurityException localSecurityException) {}
    return false;
  }
  
  public String g()
  {
    if (Build.VERSION.SDK_INT >= 8)
    {
      if ((Build.VERSION.SDK_INT >= 18) && (this.a.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))) {
        return "ble";
      }
      if (this.a.getPackageManager().hasSystemFeature("android.hardware.bluetooth")) {
        return "classic";
      }
    }
    return "bnc_no_value";
  }
  
  public boolean h()
  {
    try
    {
      boolean bool = this.a.getPackageManager().hasSystemFeature("android.hardware.nfc");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public boolean i()
  {
    try
    {
      boolean bool = this.a.getPackageManager().hasSystemFeature("android.hardware.telephony");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public String j()
  {
    return Build.MANUFACTURER;
  }
  
  public String k()
  {
    return Build.MODEL;
  }
  
  public String l()
  {
    return "Android";
  }
  
  public int m()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public boolean n()
  {
    return Build.FINGERPRINT.contains("generic");
  }
  
  public DisplayMetrics o()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public boolean p()
  {
    if (this.a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
      return ((ConnectivityManager)this.a.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return false;
  }
  
  public String q()
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
  
  public int r()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.a });
      boolean bool = ((Boolean)localObject.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
      if (bool) {}
      for (int i = 1;; i = 0) {
        return i;
      }
      return 0;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
      return 0;
    }
    catch (Exception localException) {}
  }
}
