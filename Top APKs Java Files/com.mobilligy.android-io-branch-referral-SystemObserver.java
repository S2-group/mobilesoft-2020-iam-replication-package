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
  private static final int STATE_FRESH_INSTALL = 0;
  private static final int STATE_NO_CHANGE = 1;
  private static final int STATE_UPDATE = 2;
  private Context context_;
  private boolean isRealHardwareId;
  
  public SystemObserver(Context paramContext)
  {
    this.context_ = paramContext;
    this.isRealHardwareId = true;
  }
  
  private boolean isLowOnMemory()
  {
    ActivityManager localActivityManager = (ActivityManager)this.context_.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  public String getAdvertisingId()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.context_ });
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
      Object localObject = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
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
      if ((Build.VERSION.SDK_INT >= 18) && (this.context_.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))) {
        return "ble";
      }
      if (this.context_.getPackageManager().hasSystemFeature("android.hardware.bluetooth")) {
        return "classic";
      }
    }
    return "bnc_no_value";
  }
  
  public String getCarrier()
  {
    Object localObject = (TelephonyManager)this.context_.getSystemService("phone");
    if (localObject != null)
    {
      localObject = ((TelephonyManager)localObject).getNetworkOperatorName();
      if (localObject != null) {
        return localObject;
      }
    }
    return "bnc_no_value";
  }
  
  @SuppressLint({"NewApi"})
  public JSONArray getListOfApps()
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager = this.context_.getPackageManager();
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
      boolean bool = this.context_.getPackageManager().hasSystemFeature("android.hardware.nfc");
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
    ((WindowManager)this.context_.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public boolean getTelephonePresent()
  {
    try
    {
      boolean bool = this.context_.getPackageManager().hasSystemFeature("android.hardware.telephony");
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public String getURIScheme()
  {
    return getURIScheme(this.context_.getPackageName());
  }
  
  /* Error */
  public String getURIScheme(String paramString)
  {
    // Byte code:
    //   0: ldc 8
    //   2: astore 6
    //   4: aload 6
    //   6: astore_3
    //   7: aload_0
    //   8: invokespecial 302	io/branch/referral/SystemObserver:isLowOnMemory	()Z
    //   11: ifne +171 -> 182
    //   14: aload_0
    //   15: getfield 26	io/branch/referral/SystemObserver:context_	Landroid/content/Context;
    //   18: invokevirtual 95	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   21: astore_2
    //   22: aload 6
    //   24: astore_3
    //   25: aload_2
    //   26: aload_1
    //   27: iconst_0
    //   28: invokevirtual 306	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   31: getfield 217	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   34: astore_1
    //   35: aconst_null
    //   36: astore 9
    //   38: aconst_null
    //   39: astore 4
    //   41: aconst_null
    //   42: astore 8
    //   44: aconst_null
    //   45: astore_3
    //   46: aconst_null
    //   47: astore_2
    //   48: aconst_null
    //   49: astore 11
    //   51: aconst_null
    //   52: astore 10
    //   54: aconst_null
    //   55: astore 7
    //   57: aconst_null
    //   58: astore 5
    //   60: new 308	java/util/jar/JarFile
    //   63: dup
    //   64: aload_1
    //   65: invokespecial 311	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   68: astore_1
    //   69: aload 11
    //   71: astore_2
    //   72: aload 10
    //   74: astore_3
    //   75: aload 7
    //   77: astore 5
    //   79: aload_1
    //   80: aload_1
    //   81: ldc_w 313
    //   84: invokevirtual 317	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   87: invokevirtual 321	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   90: astore 4
    //   92: aload 4
    //   94: astore_2
    //   95: aload 4
    //   97: astore_3
    //   98: aload 4
    //   100: astore 5
    //   102: aload 4
    //   104: invokevirtual 326	java/io/InputStream:available	()I
    //   107: newarray byte
    //   109: astore 7
    //   111: aload 4
    //   113: astore_2
    //   114: aload 4
    //   116: astore_3
    //   117: aload 4
    //   119: astore 5
    //   121: aload 4
    //   123: aload 7
    //   125: invokevirtual 330	java/io/InputStream:read	([B)I
    //   128: pop
    //   129: aload 4
    //   131: astore_2
    //   132: aload 4
    //   134: astore_3
    //   135: aload 4
    //   137: astore 5
    //   139: new 332	io/branch/referral/ApkParser
    //   142: dup
    //   143: invokespecial 333	io/branch/referral/ApkParser:<init>	()V
    //   146: aload 7
    //   148: invokevirtual 337	io/branch/referral/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   151: astore 7
    //   153: aload 7
    //   155: astore_2
    //   156: aload 4
    //   158: ifnull +10 -> 168
    //   161: aload_2
    //   162: astore_3
    //   163: aload 4
    //   165: invokevirtual 340	java/io/InputStream:close	()V
    //   168: aload_2
    //   169: astore_3
    //   170: aload_1
    //   171: ifnull +11 -> 182
    //   174: aload_2
    //   175: astore_3
    //   176: aload_1
    //   177: invokevirtual 341	java/util/jar/JarFile:close	()V
    //   180: aload_2
    //   181: astore_3
    //   182: aload_3
    //   183: areturn
    //   184: astore_1
    //   185: aload 8
    //   187: astore_1
    //   188: aload 5
    //   190: ifnull +11 -> 201
    //   193: aload 6
    //   195: astore_3
    //   196: aload 5
    //   198: invokevirtual 340	java/io/InputStream:close	()V
    //   201: aload 6
    //   203: astore_3
    //   204: aload_1
    //   205: ifnull -23 -> 182
    //   208: aload 6
    //   210: astore_3
    //   211: aload_1
    //   212: invokevirtual 341	java/util/jar/JarFile:close	()V
    //   215: ldc 8
    //   217: areturn
    //   218: astore_1
    //   219: aload 9
    //   221: astore_1
    //   222: aload_3
    //   223: astore_2
    //   224: aload_2
    //   225: ifnull +10 -> 235
    //   228: aload 6
    //   230: astore_3
    //   231: aload_2
    //   232: invokevirtual 340	java/io/InputStream:close	()V
    //   235: aload 6
    //   237: astore_3
    //   238: aload_1
    //   239: ifnull -57 -> 182
    //   242: aload 6
    //   244: astore_3
    //   245: aload_1
    //   246: invokevirtual 341	java/util/jar/JarFile:close	()V
    //   249: ldc 8
    //   251: areturn
    //   252: astore_1
    //   253: aload_2
    //   254: ifnull +10 -> 264
    //   257: aload 6
    //   259: astore_3
    //   260: aload_2
    //   261: invokevirtual 340	java/io/InputStream:close	()V
    //   264: aload 4
    //   266: ifnull +11 -> 277
    //   269: aload 6
    //   271: astore_3
    //   272: aload 4
    //   274: invokevirtual 341	java/util/jar/JarFile:close	()V
    //   277: aload 6
    //   279: astore_3
    //   280: aload_1
    //   281: athrow
    //   282: astore_1
    //   283: aload_3
    //   284: areturn
    //   285: astore_1
    //   286: aload_2
    //   287: areturn
    //   288: astore_2
    //   289: goto -12 -> 277
    //   292: astore_3
    //   293: aload_1
    //   294: astore 4
    //   296: aload_3
    //   297: astore_1
    //   298: goto -45 -> 253
    //   301: astore_1
    //   302: ldc 8
    //   304: areturn
    //   305: astore_2
    //   306: aload_3
    //   307: astore_2
    //   308: goto -84 -> 224
    //   311: astore_1
    //   312: ldc 8
    //   314: areturn
    //   315: astore_2
    //   316: goto -128 -> 188
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	319	0	this	SystemObserver
    //   0	319	1	paramString	String
    //   21	266	2	localObject1	Object
    //   288	1	2	localIOException	java.io.IOException
    //   305	1	2	localOutOfMemoryError	OutOfMemoryError
    //   307	1	2	localObject2	Object
    //   315	1	2	localException	Exception
    //   6	278	3	localObject3	Object
    //   292	15	3	localObject4	Object
    //   39	256	4	localObject5	Object
    //   58	139	5	localObject6	Object
    //   2	276	6	str	String
    //   55	99	7	localObject7	Object
    //   42	144	8	localObject8	Object
    //   36	184	9	localObject9	Object
    //   52	21	10	localObject10	Object
    //   49	21	11	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   60	69	184	java/lang/Exception
    //   60	69	218	java/lang/OutOfMemoryError
    //   60	69	252	finally
    //   25	35	282	android/content/pm/PackageManager$NameNotFoundException
    //   163	168	282	android/content/pm/PackageManager$NameNotFoundException
    //   176	180	282	android/content/pm/PackageManager$NameNotFoundException
    //   196	201	282	android/content/pm/PackageManager$NameNotFoundException
    //   211	215	282	android/content/pm/PackageManager$NameNotFoundException
    //   231	235	282	android/content/pm/PackageManager$NameNotFoundException
    //   245	249	282	android/content/pm/PackageManager$NameNotFoundException
    //   260	264	282	android/content/pm/PackageManager$NameNotFoundException
    //   272	277	282	android/content/pm/PackageManager$NameNotFoundException
    //   280	282	282	android/content/pm/PackageManager$NameNotFoundException
    //   163	168	285	java/io/IOException
    //   176	180	285	java/io/IOException
    //   260	264	288	java/io/IOException
    //   272	277	288	java/io/IOException
    //   79	92	292	finally
    //   102	111	292	finally
    //   121	129	292	finally
    //   139	153	292	finally
    //   231	235	301	java/io/IOException
    //   245	249	301	java/io/IOException
    //   79	92	305	java/lang/OutOfMemoryError
    //   102	111	305	java/lang/OutOfMemoryError
    //   121	129	305	java/lang/OutOfMemoryError
    //   139	153	305	java/lang/OutOfMemoryError
    //   196	201	311	java/io/IOException
    //   211	215	311	java/io/IOException
    //   79	92	315	java/lang/Exception
    //   102	111	315	java/lang/Exception
    //   121	129	315	java/lang/Exception
    //   139	153	315	java/lang/Exception
  }
  
  public String getUniqueID(boolean paramBoolean)
  {
    if (this.context_ != null)
    {
      String str1 = null;
      if (!paramBoolean) {
        str1 = Settings.Secure.getString(this.context_.getContentResolver(), "android_id");
      }
      String str2 = str1;
      if (str1 == null)
      {
        str2 = UUID.randomUUID().toString();
        this.isRealHardwareId = false;
      }
      return str2;
    }
    return "bnc_no_value";
  }
  
  @SuppressLint({"NewApi"})
  public int getUpdateState(boolean paramBoolean)
  {
    Object localObject = PrefHelper.getInstance(this.context_);
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
        localObject = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
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
    if (this.context_.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
      return ((ConnectivityManager)this.context_.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return false;
  }
  
  public boolean hasRealHardwareId()
  {
    return this.isRealHardwareId;
  }
  
  public boolean isSimulator()
  {
    return Build.FINGERPRINT.contains("generic");
  }
}
