package a.a.a;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class bc
{
  Context a;
  boolean b;
  
  public bc(Context paramContext)
  {
    this.a = paramContext;
    this.b = true;
  }
  
  public static boolean d()
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
    catch (Exception localException) {}
    return false;
  }
  
  /* Error */
  public final String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 5
    //   11: aconst_null
    //   12: astore_2
    //   13: aload_0
    //   14: getfield 15	a/a/a/bc:a	Landroid/content/Context;
    //   17: ldc 40
    //   19: invokevirtual 46	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   22: checkcast 48	android/app/ActivityManager
    //   25: astore 7
    //   27: new 50	android/app/ActivityManager$MemoryInfo
    //   30: dup
    //   31: invokespecial 51	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   34: astore 8
    //   36: aload 7
    //   38: aload 8
    //   40: invokevirtual 55	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   43: aload 8
    //   45: getfield 58	android/app/ActivityManager$MemoryInfo:lowMemory	Z
    //   48: ifne +234 -> 282
    //   51: aload_0
    //   52: getfield 15	a/a/a/bc:a	Landroid/content/Context;
    //   55: invokevirtual 62	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   58: astore 7
    //   60: aload 7
    //   62: aload_1
    //   63: iconst_0
    //   64: invokevirtual 68	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   67: getfield 74	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   70: astore_1
    //   71: new 76	java/util/jar/JarFile
    //   74: dup
    //   75: aload_1
    //   76: invokespecial 79	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   79: astore_1
    //   80: aload 6
    //   82: astore_3
    //   83: aload 5
    //   85: astore 4
    //   87: aload_1
    //   88: aload_1
    //   89: ldc 81
    //   91: invokevirtual 85	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   94: invokevirtual 89	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   97: astore_2
    //   98: aload_2
    //   99: astore_3
    //   100: aload_2
    //   101: astore 4
    //   103: aload_2
    //   104: invokevirtual 95	java/io/InputStream:available	()I
    //   107: newarray byte
    //   109: astore 5
    //   111: aload_2
    //   112: astore_3
    //   113: aload_2
    //   114: astore 4
    //   116: aload_2
    //   117: aload 5
    //   119: invokevirtual 99	java/io/InputStream:read	([B)I
    //   122: pop
    //   123: aload_2
    //   124: astore_3
    //   125: aload_2
    //   126: astore 4
    //   128: new 101	a/a/a/c
    //   131: dup
    //   132: invokespecial 102	a/a/a/c:<init>	()V
    //   135: pop
    //   136: aload_2
    //   137: astore_3
    //   138: aload_2
    //   139: astore 4
    //   141: aload 5
    //   143: invokestatic 105	a/a/a/c:a	([B)Ljava/lang/String;
    //   146: astore 5
    //   148: aload_2
    //   149: ifnull +7 -> 156
    //   152: aload_2
    //   153: invokevirtual 108	java/io/InputStream:close	()V
    //   156: aload_1
    //   157: invokevirtual 109	java/util/jar/JarFile:close	()V
    //   160: aload 5
    //   162: areturn
    //   163: astore_1
    //   164: aconst_null
    //   165: astore_1
    //   166: aload_1
    //   167: ifnull +7 -> 174
    //   170: aload_1
    //   171: invokevirtual 108	java/io/InputStream:close	()V
    //   174: aload_2
    //   175: ifnull +7 -> 182
    //   178: aload_2
    //   179: invokevirtual 109	java/util/jar/JarFile:close	()V
    //   182: ldc 111
    //   184: areturn
    //   185: astore_1
    //   186: ldc 111
    //   188: areturn
    //   189: astore_1
    //   190: aconst_null
    //   191: astore_1
    //   192: aload 4
    //   194: ifnull +8 -> 202
    //   197: aload 4
    //   199: invokevirtual 108	java/io/InputStream:close	()V
    //   202: aload_1
    //   203: ifnull +7 -> 210
    //   206: aload_1
    //   207: invokevirtual 109	java/util/jar/JarFile:close	()V
    //   210: ldc 111
    //   212: areturn
    //   213: astore_1
    //   214: ldc 111
    //   216: areturn
    //   217: astore_2
    //   218: aconst_null
    //   219: astore_1
    //   220: aload_3
    //   221: ifnull +7 -> 228
    //   224: aload_3
    //   225: invokevirtual 108	java/io/InputStream:close	()V
    //   228: aload_1
    //   229: ifnull +7 -> 236
    //   232: aload_1
    //   233: invokevirtual 109	java/util/jar/JarFile:close	()V
    //   236: aload_2
    //   237: athrow
    //   238: astore_1
    //   239: ldc 111
    //   241: areturn
    //   242: astore_1
    //   243: aload 5
    //   245: areturn
    //   246: astore_1
    //   247: goto -11 -> 236
    //   250: astore_2
    //   251: goto -31 -> 220
    //   254: astore_2
    //   255: goto -63 -> 192
    //   258: astore_2
    //   259: aconst_null
    //   260: astore_3
    //   261: aload_1
    //   262: astore_2
    //   263: aload_3
    //   264: astore_1
    //   265: goto -99 -> 166
    //   268: astore_3
    //   269: aload_1
    //   270: astore_3
    //   271: aload_2
    //   272: astore_1
    //   273: aload_3
    //   274: astore_2
    //   275: goto -109 -> 166
    //   278: astore_1
    //   279: aload 5
    //   281: areturn
    //   282: ldc 111
    //   284: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	285	0	this	bc
    //   0	285	1	paramString	String
    //   12	167	2	localInputStream	java.io.InputStream
    //   217	20	2	localObject1	Object
    //   250	1	2	localObject2	Object
    //   254	1	2	localOutOfMemoryError	OutOfMemoryError
    //   258	1	2	localException1	Exception
    //   262	13	2	str1	String
    //   4	260	3	localObject3	Object
    //   268	1	3	localException2	Exception
    //   270	4	3	str2	String
    //   1	197	4	localObject4	Object
    //   9	271	5	localObject5	Object
    //   6	75	6	localObject6	Object
    //   25	36	7	localObject7	Object
    //   34	10	8	localMemoryInfo	android.app.ActivityManager.MemoryInfo
    // Exception table:
    //   from	to	target	type
    //   71	80	163	java/lang/Exception
    //   170	174	185	java/io/IOException
    //   178	182	185	java/io/IOException
    //   71	80	189	java/lang/OutOfMemoryError
    //   197	202	213	java/io/IOException
    //   206	210	213	java/io/IOException
    //   71	80	217	finally
    //   60	71	238	android/content/pm/PackageManager$NameNotFoundException
    //   170	174	238	android/content/pm/PackageManager$NameNotFoundException
    //   178	182	238	android/content/pm/PackageManager$NameNotFoundException
    //   197	202	238	android/content/pm/PackageManager$NameNotFoundException
    //   206	210	238	android/content/pm/PackageManager$NameNotFoundException
    //   224	228	238	android/content/pm/PackageManager$NameNotFoundException
    //   232	236	238	android/content/pm/PackageManager$NameNotFoundException
    //   236	238	238	android/content/pm/PackageManager$NameNotFoundException
    //   152	156	242	android/content/pm/PackageManager$NameNotFoundException
    //   156	160	242	android/content/pm/PackageManager$NameNotFoundException
    //   224	228	246	java/io/IOException
    //   232	236	246	java/io/IOException
    //   87	98	250	finally
    //   103	111	250	finally
    //   116	123	250	finally
    //   128	136	250	finally
    //   141	148	250	finally
    //   87	98	254	java/lang/OutOfMemoryError
    //   103	111	254	java/lang/OutOfMemoryError
    //   116	123	254	java/lang/OutOfMemoryError
    //   128	136	254	java/lang/OutOfMemoryError
    //   141	148	254	java/lang/OutOfMemoryError
    //   87	98	258	java/lang/Exception
    //   103	111	268	java/lang/Exception
    //   116	123	268	java/lang/Exception
    //   128	136	268	java/lang/Exception
    //   141	148	268	java/lang/Exception
    //   152	156	278	java/io/IOException
    //   156	160	278	java/io/IOException
  }
  
  @SuppressLint({"NewApi"})
  public final JSONArray a()
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
              localJSONObject.put(aa.I.au, localObject);
              localObject = a((String)localObject);
              if (!((String)localObject).equals("bnc_no_value")) {
                localJSONObject.put(aa.H.au, localObject);
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
            localJSONObject.put(aa.A.au, "Android");
            localJSONArray.put(localJSONObject);
            break;
          }
          return localJSONArray;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}catch (JSONException localJSONException) {}
      }
    }
  }
  
  public final String b()
  {
    try
    {
      Object localObject = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0);
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
  
  public final String c()
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
  
  public final String e()
  {
    try
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
    }
    catch (Exception localException) {}
    return "bnc_no_value";
  }
  
  public final boolean f()
  {
    try
    {
      boolean bool = this.a.getPackageManager().hasSystemFeature("android.hardware.nfc");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public final boolean g()
  {
    try
    {
      boolean bool = this.a.getPackageManager().hasSystemFeature("android.hardware.telephony");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public final int h()
  {
    ae.a(this.a);
    Object localObject = b();
    if ("bnc_no_value".equals(ae.c("bnc_app_version")))
    {
      ae.a("bnc_app_version", (String)localObject);
      if (Build.VERSION.SDK_INT >= 9) {
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
      }
      return 0;
    }
    if (!ae.c("bnc_app_version").equals(localNameNotFoundException))
    {
      ae.a("bnc_app_version", localNameNotFoundException);
      return 2;
    }
    return 1;
  }
  
  public final String i()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.a.a.a").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.a });
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
  
  public final int j()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.a.a.a").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.a });
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
