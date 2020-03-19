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
      PackageInfo localPackageInfo = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
      if (localPackageInfo.versionName != null) {
        return localPackageInfo.versionName;
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
  
  public int getLATValue()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.context_ });
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
      Object localObject2;
      JSONObject localJSONObject;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if ((((ApplicationInfo)localObject2).flags & 0x1) != 1) {
          localJSONObject = new JSONObject();
        }
      }
      else
      {
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
        catch (JSONException localJSONException)
        {
          continue;
          return localJSONArray;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
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
    //   6: astore_2
    //   7: aload_0
    //   8: invokespecial 310	io/branch/referral/SystemObserver:isLowOnMemory	()Z
    //   11: ifne +167 -> 178
    //   14: aload_0
    //   15: getfield 26	io/branch/referral/SystemObserver:context_	Landroid/content/Context;
    //   18: invokevirtual 95	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   21: astore_2
    //   22: aload 6
    //   24: astore_3
    //   25: aload_2
    //   26: aload_1
    //   27: iconst_0
    //   28: invokevirtual 314	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   31: getfield 226	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
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
    //   60: new 316	java/util/jar/JarFile
    //   63: dup
    //   64: aload_1
    //   65: invokespecial 319	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   68: astore_1
    //   69: aload 11
    //   71: astore_2
    //   72: aload 10
    //   74: astore_3
    //   75: aload 7
    //   77: astore 5
    //   79: aload_1
    //   80: aload_1
    //   81: ldc_w 321
    //   84: invokevirtual 325	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   87: invokevirtual 329	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   90: astore 4
    //   92: aload 4
    //   94: astore_2
    //   95: aload 4
    //   97: astore_3
    //   98: aload 4
    //   100: astore 5
    //   102: aload 4
    //   104: invokevirtual 334	java/io/InputStream:available	()I
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
    //   125: invokevirtual 338	java/io/InputStream:read	([B)I
    //   128: pop
    //   129: aload 4
    //   131: astore_2
    //   132: aload 4
    //   134: astore_3
    //   135: aload 4
    //   137: astore 5
    //   139: new 340	io/branch/referral/ApkParser
    //   142: dup
    //   143: invokespecial 341	io/branch/referral/ApkParser:<init>	()V
    //   146: aload 7
    //   148: invokevirtual 345	io/branch/referral/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   151: astore 7
    //   153: aload 7
    //   155: astore_2
    //   156: aload 4
    //   158: ifnull +10 -> 168
    //   161: aload_2
    //   162: astore_3
    //   163: aload 4
    //   165: invokevirtual 348	java/io/InputStream:close	()V
    //   168: aload_1
    //   169: ifnull +146 -> 315
    //   172: aload_2
    //   173: astore_3
    //   174: aload_1
    //   175: invokevirtual 349	java/util/jar/JarFile:close	()V
    //   178: aload_2
    //   179: areturn
    //   180: astore_1
    //   181: aload_2
    //   182: areturn
    //   183: astore_1
    //   184: aload 8
    //   186: astore_1
    //   187: aload 5
    //   189: ifnull +11 -> 200
    //   192: aload 6
    //   194: astore_3
    //   195: aload 5
    //   197: invokevirtual 348	java/io/InputStream:close	()V
    //   200: aload 6
    //   202: astore_2
    //   203: aload_1
    //   204: ifnull -26 -> 178
    //   207: aload 6
    //   209: astore_3
    //   210: aload_1
    //   211: invokevirtual 349	java/util/jar/JarFile:close	()V
    //   214: ldc 8
    //   216: areturn
    //   217: astore_1
    //   218: aload 9
    //   220: astore_1
    //   221: aload_3
    //   222: astore_2
    //   223: aload_2
    //   224: ifnull +10 -> 234
    //   227: aload 6
    //   229: astore_3
    //   230: aload_2
    //   231: invokevirtual 348	java/io/InputStream:close	()V
    //   234: aload 6
    //   236: astore_2
    //   237: aload_1
    //   238: ifnull -60 -> 178
    //   241: aload 6
    //   243: astore_3
    //   244: aload_1
    //   245: invokevirtual 349	java/util/jar/JarFile:close	()V
    //   248: ldc 8
    //   250: areturn
    //   251: astore_1
    //   252: aload_2
    //   253: ifnull +10 -> 263
    //   256: aload 6
    //   258: astore_3
    //   259: aload_2
    //   260: invokevirtual 348	java/io/InputStream:close	()V
    //   263: aload 4
    //   265: ifnull +11 -> 276
    //   268: aload 6
    //   270: astore_3
    //   271: aload 4
    //   273: invokevirtual 349	java/util/jar/JarFile:close	()V
    //   276: aload 6
    //   278: astore_3
    //   279: aload_1
    //   280: athrow
    //   281: astore_1
    //   282: aload_3
    //   283: areturn
    //   284: astore_2
    //   285: goto -9 -> 276
    //   288: astore_3
    //   289: aload_1
    //   290: astore 4
    //   292: aload_3
    //   293: astore_1
    //   294: goto -42 -> 252
    //   297: astore_1
    //   298: ldc 8
    //   300: areturn
    //   301: astore_2
    //   302: aload_3
    //   303: astore_2
    //   304: goto -81 -> 223
    //   307: astore_1
    //   308: ldc 8
    //   310: areturn
    //   311: astore_2
    //   312: goto -125 -> 187
    //   315: aload_2
    //   316: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	317	0	this	SystemObserver
    //   0	317	1	paramString	String
    //   6	254	2	localObject1	Object
    //   284	1	2	localIOException	java.io.IOException
    //   301	1	2	localOutOfMemoryError	OutOfMemoryError
    //   303	1	2	localObject2	Object
    //   311	5	2	localException	Exception
    //   24	259	3	localObject3	Object
    //   288	15	3	localObject4	Object
    //   39	252	4	localObject5	Object
    //   58	138	5	localObject6	Object
    //   2	275	6	str	String
    //   55	99	7	localObject7	Object
    //   42	143	8	localObject8	Object
    //   36	183	9	localObject9	Object
    //   52	21	10	localObject10	Object
    //   49	21	11	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   163	168	180	java/io/IOException
    //   174	178	180	java/io/IOException
    //   60	69	183	java/lang/Exception
    //   60	69	217	java/lang/OutOfMemoryError
    //   60	69	251	finally
    //   25	35	281	android/content/pm/PackageManager$NameNotFoundException
    //   163	168	281	android/content/pm/PackageManager$NameNotFoundException
    //   174	178	281	android/content/pm/PackageManager$NameNotFoundException
    //   195	200	281	android/content/pm/PackageManager$NameNotFoundException
    //   210	214	281	android/content/pm/PackageManager$NameNotFoundException
    //   230	234	281	android/content/pm/PackageManager$NameNotFoundException
    //   244	248	281	android/content/pm/PackageManager$NameNotFoundException
    //   259	263	281	android/content/pm/PackageManager$NameNotFoundException
    //   271	276	281	android/content/pm/PackageManager$NameNotFoundException
    //   279	281	281	android/content/pm/PackageManager$NameNotFoundException
    //   259	263	284	java/io/IOException
    //   271	276	284	java/io/IOException
    //   79	92	288	finally
    //   102	111	288	finally
    //   121	129	288	finally
    //   139	153	288	finally
    //   230	234	297	java/io/IOException
    //   244	248	297	java/io/IOException
    //   79	92	301	java/lang/OutOfMemoryError
    //   102	111	301	java/lang/OutOfMemoryError
    //   121	129	301	java/lang/OutOfMemoryError
    //   139	153	301	java/lang/OutOfMemoryError
    //   195	200	307	java/io/IOException
    //   210	214	307	java/io/IOException
    //   79	92	311	java/lang/Exception
    //   102	111	311	java/lang/Exception
    //   121	129	311	java/lang/Exception
    //   139	153	311	java/lang/Exception
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
