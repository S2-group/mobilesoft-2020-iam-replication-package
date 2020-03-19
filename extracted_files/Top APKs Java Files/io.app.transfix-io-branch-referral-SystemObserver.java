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

public class SystemObserver
{
  public static final String BLANK = "bnc_no_value";
  private Context a;
  private boolean b;
  
  public SystemObserver(Context paramContext)
  {
    this.a = paramContext;
    this.b = true;
  }
  
  private boolean a()
  {
    ActivityManager localActivityManager = (ActivityManager)this.a.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  public String getAdvertisingId()
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
  
  public String getAppVersion()
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
  
  public boolean getBluetoothPresent()
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
  
  public String getBluetoothVersion()
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
  
  public String getCarrier()
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
  
  public int getLATValue()
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
  
  @SuppressLint({"NewApi"})
  public JSONArray getListOfApps()
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager = this.a.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledApplications(128);
    if (localObject1 != null) {
      localObject1 = ((List)localObject1).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        return localJSONArray;
      }
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      if ((((ApplicationInfo)localObject2).flags & 0x1) != 1)
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          String str = ((ApplicationInfo)localObject2).loadLabel(localPackageManager).toString();
          if (str != null) {
            localJSONObject.put("name", str);
          }
          str = ((ApplicationInfo)localObject2).packageName;
          if (str != null)
          {
            localJSONObject.put("app_identifier", str);
            str = getURIScheme(str);
            if (!str.equals("bnc_no_value")) {
              localJSONObject.put("uri_scheme", str);
            }
          }
          str = ((ApplicationInfo)localObject2).publicSourceDir;
          if (str != null) {
            localJSONObject.put("public_source_dir", str);
          }
          str = ((ApplicationInfo)localObject2).sourceDir;
          if (str != null) {
            localJSONObject.put("source_dir", str);
          }
          localObject2 = localPackageManager.getPackageInfo(((ApplicationInfo)localObject2).packageName, 4096);
          if (localObject2 != null)
          {
            if (((PackageInfo)localObject2).versionCode >= 9)
            {
              localJSONObject.put("install_date", ((PackageInfo)localObject2).firstInstallTime);
              localJSONObject.put("last_update_date", ((PackageInfo)localObject2).lastUpdateTime);
            }
            localJSONObject.put("version_code", ((PackageInfo)localObject2).versionCode);
            if (((PackageInfo)localObject2).versionName != null) {
              localJSONObject.put("version_name", ((PackageInfo)localObject2).versionName);
            }
          }
          localJSONObject.put("os", getOS());
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException localJSONException) {}catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      }
    }
  }
  
  public boolean getNFCPresent()
  {
    try
    {
      boolean bool = this.a.getPackageManager().hasSystemFeature("android.hardware.nfc");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public String getOS()
  {
    return "Android";
  }
  
  public int getOSVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String getPhoneBrand()
  {
    return Build.MANUFACTURER;
  }
  
  public String getPhoneModel()
  {
    return Build.MODEL;
  }
  
  public DisplayMetrics getScreenDisplay()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public boolean getTelephonePresent()
  {
    try
    {
      boolean bool = this.a.getPackageManager().hasSystemFeature("android.hardware.telephony");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public String getURIScheme()
  {
    return getURIScheme(this.a.getPackageName());
  }
  
  /* Error */
  public String getURIScheme(String paramString)
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
    //   11: ldc 8
    //   13: astore 5
    //   15: aload 5
    //   17: astore_2
    //   18: aload_0
    //   19: invokespecial 303	io/branch/referral/SystemObserver:a	()Z
    //   22: ifne +126 -> 148
    //   25: aload_0
    //   26: getfield 19	io/branch/referral/SystemObserver:a	Landroid/content/Context;
    //   29: invokevirtual 87	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   32: astore_2
    //   33: aload 5
    //   35: astore 4
    //   37: aload_2
    //   38: aload_1
    //   39: iconst_0
    //   40: invokevirtual 307	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   43: getfield 219	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   46: astore_1
    //   47: new 309	java/util/jar/JarFile
    //   50: dup
    //   51: aload_1
    //   52: invokespecial 312	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   55: astore_1
    //   56: aload 8
    //   58: astore_3
    //   59: aload 7
    //   61: astore 4
    //   63: aload_1
    //   64: aload_1
    //   65: ldc_w 314
    //   68: invokevirtual 318	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   71: invokevirtual 322	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   74: astore_2
    //   75: aload_2
    //   76: astore_3
    //   77: aload_2
    //   78: astore 4
    //   80: aload_2
    //   81: invokevirtual 327	java/io/InputStream:available	()I
    //   84: newarray byte
    //   86: astore 6
    //   88: aload_2
    //   89: astore_3
    //   90: aload_2
    //   91: astore 4
    //   93: aload_2
    //   94: aload 6
    //   96: invokevirtual 331	java/io/InputStream:read	([B)I
    //   99: pop
    //   100: aload_2
    //   101: astore_3
    //   102: aload_2
    //   103: astore 4
    //   105: new 333	io/branch/referral/ApkParser
    //   108: dup
    //   109: invokespecial 334	io/branch/referral/ApkParser:<init>	()V
    //   112: aload 6
    //   114: invokevirtual 338	io/branch/referral/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   117: astore 6
    //   119: aload 6
    //   121: astore_3
    //   122: aload_2
    //   123: ifnull +10 -> 133
    //   126: aload_3
    //   127: astore 4
    //   129: aload_2
    //   130: invokevirtual 341	java/io/InputStream:close	()V
    //   133: aload_3
    //   134: astore_2
    //   135: aload_1
    //   136: ifnull +12 -> 148
    //   139: aload_3
    //   140: astore 4
    //   142: aload_1
    //   143: invokevirtual 342	java/util/jar/JarFile:close	()V
    //   146: aload_3
    //   147: astore_2
    //   148: aload_2
    //   149: areturn
    //   150: astore_1
    //   151: aconst_null
    //   152: astore_1
    //   153: aload_3
    //   154: astore_2
    //   155: aload_2
    //   156: ifnull +11 -> 167
    //   159: aload 5
    //   161: astore 4
    //   163: aload_2
    //   164: invokevirtual 341	java/io/InputStream:close	()V
    //   167: aload 5
    //   169: astore_2
    //   170: aload_1
    //   171: ifnull -23 -> 148
    //   174: aload 5
    //   176: astore 4
    //   178: aload_1
    //   179: invokevirtual 342	java/util/jar/JarFile:close	()V
    //   182: ldc 8
    //   184: areturn
    //   185: astore_1
    //   186: ldc 8
    //   188: areturn
    //   189: astore_1
    //   190: aconst_null
    //   191: astore_1
    //   192: aload 6
    //   194: astore_3
    //   195: aload_3
    //   196: ifnull +11 -> 207
    //   199: aload 5
    //   201: astore 4
    //   203: aload_3
    //   204: invokevirtual 341	java/io/InputStream:close	()V
    //   207: aload 5
    //   209: astore_2
    //   210: aload_1
    //   211: ifnull -63 -> 148
    //   214: aload 5
    //   216: astore 4
    //   218: aload_1
    //   219: invokevirtual 342	java/util/jar/JarFile:close	()V
    //   222: ldc 8
    //   224: areturn
    //   225: astore_1
    //   226: ldc 8
    //   228: areturn
    //   229: astore_1
    //   230: aconst_null
    //   231: astore_3
    //   232: aconst_null
    //   233: astore_2
    //   234: aload_2
    //   235: ifnull +11 -> 246
    //   238: aload 5
    //   240: astore 4
    //   242: aload_2
    //   243: invokevirtual 341	java/io/InputStream:close	()V
    //   246: aload_3
    //   247: ifnull +11 -> 258
    //   250: aload 5
    //   252: astore 4
    //   254: aload_3
    //   255: invokevirtual 342	java/util/jar/JarFile:close	()V
    //   258: aload 5
    //   260: astore 4
    //   262: aload_1
    //   263: athrow
    //   264: astore_1
    //   265: aload 4
    //   267: areturn
    //   268: astore_1
    //   269: aload_3
    //   270: areturn
    //   271: astore_2
    //   272: goto -14 -> 258
    //   275: astore 4
    //   277: aload_1
    //   278: astore_3
    //   279: aconst_null
    //   280: astore_2
    //   281: aload 4
    //   283: astore_1
    //   284: goto -50 -> 234
    //   287: astore 4
    //   289: aload_1
    //   290: astore_3
    //   291: aload 4
    //   293: astore_1
    //   294: goto -60 -> 234
    //   297: astore_2
    //   298: goto -103 -> 195
    //   301: astore_2
    //   302: aload 4
    //   304: astore_2
    //   305: goto -150 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	308	0	this	SystemObserver
    //   0	308	1	paramString	String
    //   17	226	2	localObject1	Object
    //   271	1	2	localIOException	java.io.IOException
    //   280	1	2	localObject2	Object
    //   297	1	2	localOutOfMemoryError	OutOfMemoryError
    //   301	1	2	localException	Exception
    //   304	1	2	localObject3	Object
    //   10	281	3	localObject4	Object
    //   35	231	4	localObject5	Object
    //   275	7	4	localObject6	Object
    //   287	16	4	localObject7	Object
    //   13	246	5	str	String
    //   1	192	6	localObject8	Object
    //   7	53	7	localObject9	Object
    //   4	53	8	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   47	56	150	java/lang/Exception
    //   163	167	185	java/io/IOException
    //   178	182	185	java/io/IOException
    //   47	56	189	java/lang/OutOfMemoryError
    //   203	207	225	java/io/IOException
    //   218	222	225	java/io/IOException
    //   47	56	229	finally
    //   37	47	264	android/content/pm/PackageManager$NameNotFoundException
    //   129	133	264	android/content/pm/PackageManager$NameNotFoundException
    //   142	146	264	android/content/pm/PackageManager$NameNotFoundException
    //   163	167	264	android/content/pm/PackageManager$NameNotFoundException
    //   178	182	264	android/content/pm/PackageManager$NameNotFoundException
    //   203	207	264	android/content/pm/PackageManager$NameNotFoundException
    //   218	222	264	android/content/pm/PackageManager$NameNotFoundException
    //   242	246	264	android/content/pm/PackageManager$NameNotFoundException
    //   254	258	264	android/content/pm/PackageManager$NameNotFoundException
    //   262	264	264	android/content/pm/PackageManager$NameNotFoundException
    //   129	133	268	java/io/IOException
    //   142	146	268	java/io/IOException
    //   242	246	271	java/io/IOException
    //   254	258	271	java/io/IOException
    //   63	75	275	finally
    //   80	88	287	finally
    //   93	100	287	finally
    //   105	119	287	finally
    //   63	75	297	java/lang/OutOfMemoryError
    //   80	88	297	java/lang/OutOfMemoryError
    //   93	100	297	java/lang/OutOfMemoryError
    //   105	119	297	java/lang/OutOfMemoryError
    //   63	75	301	java/lang/Exception
    //   80	88	301	java/lang/Exception
    //   93	100	301	java/lang/Exception
    //   105	119	301	java/lang/Exception
  }
  
  public String getUniqueID(boolean paramBoolean)
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
  
  @SuppressLint({"NewApi"})
  public int getUpdateState(boolean paramBoolean)
  {
    Object localObject = PrefHelper.getInstance(this.a);
    String str = getAppVersion();
    if (((PrefHelper)localObject).getAppVersion() == "bnc_no_value")
    {
      if (paramBoolean) {
        ((PrefHelper)localObject).setAppVersion(str);
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
      if (localNameNotFoundException.getAppVersion().equals(str)) {
        break;
      }
    } while (!paramBoolean);
    localNameNotFoundException.setAppVersion(str);
    return 2;
    return 1;
  }
  
  public boolean getWifiConnected()
  {
    if (this.a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
      return ((ConnectivityManager)this.a.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return false;
  }
  
  public boolean hasRealHardwareId()
  {
    return this.b;
  }
  
  public boolean isSimulator()
  {
    return Build.FINGERPRINT.contains("generic");
  }
}
