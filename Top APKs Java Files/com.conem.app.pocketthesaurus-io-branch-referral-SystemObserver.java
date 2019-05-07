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
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SystemObserver
{
  public static final String BLANK = "bnc_no_value";
  private static final int GAID_FETCH_TIME_OUT = 1500;
  private static final int STATE_FRESH_INSTALL = 0;
  private static final int STATE_NO_CHANGE = 1;
  private static final int STATE_UPDATE = 2;
  String GAIDString_ = null;
  int LATVal_ = 0;
  private Context context_;
  private boolean isRealHardwareId;
  
  public SystemObserver(Context paramContext)
  {
    this.context_ = paramContext;
    this.isRealHardwareId = true;
  }
  
  public static String getLocalIPAddress()
  {
    Object localObject1 = "";
    Object localObject2 = localObject1;
    for (;;)
    {
      try
      {
        Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        localObject2 = localObject1;
        Object localObject3 = localObject1;
        if (localIterator1.hasNext())
        {
          localObject2 = localObject1;
          Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
          localObject2 = localObject1;
          if (localIterator2.hasNext())
          {
            localObject2 = localObject1;
            localObject3 = (InetAddress)localIterator2.next();
            localObject2 = localObject1;
            if (!((InetAddress)localObject3).isLoopbackAddress())
            {
              localObject2 = localObject1;
              localObject3 = ((InetAddress)localObject3).getHostAddress();
              localObject2 = localObject1;
              int i = ((String)localObject3).indexOf(':');
              if (i < 0)
              {
                i = 1;
                if (i != 0) {
                  localObject1 = localObject3;
                }
              }
              else
              {
                i = 0;
                continue;
              }
            }
          }
        }
        else
        {
          return localObject3;
        }
      }
      catch (Throwable localThrowable)
      {
        localObject3 = localObject2;
      }
    }
  }
  
  private boolean isLowOnMemory()
  {
    ActivityManager localActivityManager = (ActivityManager)this.context_.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  public Object getAdInfoObject()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.context_ });
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public String getAdvertisingId(Object paramObject)
  {
    try
    {
      this.GAIDString_ = ((String)paramObject.getClass().getMethod("getId", new Class[0]).invoke(paramObject, new Object[0]));
      return this.GAIDString_;
    }
    catch (Exception paramObject)
    {
      for (;;) {}
    }
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
  
  public String getISO2CountryCode()
  {
    if (Locale.getDefault() != null) {
      return Locale.getDefault().getCountry();
    }
    return "";
  }
  
  public String getISO2LanguageCode()
  {
    if (Locale.getDefault() != null) {
      return Locale.getDefault().getLanguage();
    }
    return "";
  }
  
  public int getLATValue(Object paramObject)
  {
    for (;;)
    {
      try
      {
        if (!((Boolean)paramObject.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(paramObject, new Object[0])).booleanValue()) {
          continue;
        }
        i = 1;
        this.LATVal_ = i;
      }
      catch (Exception paramObject)
      {
        int i;
        continue;
      }
      return this.LATVal_;
      i = 0;
    }
  }
  
  @SuppressLint({"NewApi"})
  public JSONArray getListOfApps()
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager = this.context_.getPackageManager();
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
              localJSONObject.put(Defines.Jsonkey.AppIdentifier.getKey(), localObject);
              localObject = getURIScheme((String)localObject);
              if (!((String)localObject).equals("bnc_no_value")) {
                localJSONObject.put(Defines.Jsonkey.URIScheme.getKey(), localObject);
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
            localJSONObject.put(Defines.Jsonkey.OS.getKey(), getOS());
            localJSONArray.put(localJSONObject);
            break;
          }
          return localJSONArray;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}catch (JSONException localJSONException) {}
      }
    }
  }
  
  public String getOS()
  {
    return "Android";
  }
  
  public int getOSVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String getPackageName()
  {
    try
    {
      String str = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0).packageName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
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
  
  public String getURIScheme()
  {
    return getURIScheme(this.context_.getPackageName());
  }
  
  /* Error */
  public String getURIScheme(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_3
    //   5: ldc 14
    //   7: astore 4
    //   9: aload 4
    //   11: astore_2
    //   12: aload_0
    //   13: invokespecial 340	io/branch/referral/SystemObserver:isLowOnMemory	()Z
    //   16: ifne +113 -> 129
    //   19: aload_0
    //   20: getfield 40	io/branch/referral/SystemObserver:context_	Landroid/content/Context;
    //   23: invokevirtual 153	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   26: astore_2
    //   27: aload 4
    //   29: astore 5
    //   31: aload_2
    //   32: aload_1
    //   33: iconst_0
    //   34: invokevirtual 344	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   37: getfield 248	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   40: astore_1
    //   41: new 346	java/util/jar/JarFile
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 349	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   49: astore_1
    //   50: aload 6
    //   52: astore_3
    //   53: aload_1
    //   54: aload_1
    //   55: ldc_w 351
    //   58: invokevirtual 355	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   61: invokevirtual 359	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   64: astore_2
    //   65: aload_2
    //   66: astore_3
    //   67: aload_2
    //   68: invokevirtual 364	java/io/InputStream:available	()I
    //   71: newarray byte
    //   73: astore 5
    //   75: aload_2
    //   76: astore_3
    //   77: aload_2
    //   78: aload 5
    //   80: invokevirtual 368	java/io/InputStream:read	([B)I
    //   83: pop
    //   84: aload_2
    //   85: astore_3
    //   86: new 370	io/branch/referral/ApkParser
    //   89: dup
    //   90: invokespecial 371	io/branch/referral/ApkParser:<init>	()V
    //   93: aload 5
    //   95: invokevirtual 375	io/branch/referral/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   98: astore 5
    //   100: aload 5
    //   102: astore_3
    //   103: aload_2
    //   104: ifnull +10 -> 114
    //   107: aload_3
    //   108: astore 5
    //   110: aload_2
    //   111: invokevirtual 378	java/io/InputStream:close	()V
    //   114: aload_3
    //   115: astore_2
    //   116: aload_1
    //   117: ifnull +12 -> 129
    //   120: aload_3
    //   121: astore 5
    //   123: aload_1
    //   124: invokevirtual 379	java/util/jar/JarFile:close	()V
    //   127: aload_3
    //   128: astore_2
    //   129: aload_2
    //   130: areturn
    //   131: astore_1
    //   132: aconst_null
    //   133: astore_1
    //   134: aload_3
    //   135: ifnull +11 -> 146
    //   138: aload 4
    //   140: astore 5
    //   142: aload_3
    //   143: invokevirtual 378	java/io/InputStream:close	()V
    //   146: aload 4
    //   148: astore_2
    //   149: aload_1
    //   150: ifnull -21 -> 129
    //   153: aload 4
    //   155: astore 5
    //   157: aload_1
    //   158: invokevirtual 379	java/util/jar/JarFile:close	()V
    //   161: ldc 14
    //   163: areturn
    //   164: astore_1
    //   165: ldc 14
    //   167: areturn
    //   168: astore_1
    //   169: aconst_null
    //   170: astore_3
    //   171: aconst_null
    //   172: astore_2
    //   173: aload_2
    //   174: ifnull +11 -> 185
    //   177: aload 4
    //   179: astore 5
    //   181: aload_2
    //   182: invokevirtual 378	java/io/InputStream:close	()V
    //   185: aload_3
    //   186: ifnull +11 -> 197
    //   189: aload 4
    //   191: astore 5
    //   193: aload_3
    //   194: invokevirtual 379	java/util/jar/JarFile:close	()V
    //   197: aload 4
    //   199: astore 5
    //   201: aload_1
    //   202: athrow
    //   203: astore_1
    //   204: aload 5
    //   206: areturn
    //   207: astore_2
    //   208: goto -11 -> 197
    //   211: astore 5
    //   213: aload_1
    //   214: astore_3
    //   215: aconst_null
    //   216: astore_2
    //   217: aload 5
    //   219: astore_1
    //   220: goto -47 -> 173
    //   223: astore 5
    //   225: aload_1
    //   226: astore_3
    //   227: aload 5
    //   229: astore_1
    //   230: goto -57 -> 173
    //   233: astore_2
    //   234: goto -100 -> 134
    //   237: astore_1
    //   238: aload_3
    //   239: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	240	0	this	SystemObserver
    //   0	240	1	paramString	String
    //   11	171	2	localObject1	Object
    //   207	1	2	localIOException	java.io.IOException
    //   216	1	2	localObject2	Object
    //   233	1	2	localException	Exception
    //   4	235	3	localObject3	Object
    //   7	191	4	str	String
    //   29	176	5	localObject4	Object
    //   211	7	5	localObject5	Object
    //   223	5	5	localObject6	Object
    //   1	50	6	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   41	50	131	java/lang/Exception
    //   142	146	164	java/io/IOException
    //   157	161	164	java/io/IOException
    //   41	50	168	finally
    //   31	41	203	android/content/pm/PackageManager$NameNotFoundException
    //   110	114	203	android/content/pm/PackageManager$NameNotFoundException
    //   123	127	203	android/content/pm/PackageManager$NameNotFoundException
    //   142	146	203	android/content/pm/PackageManager$NameNotFoundException
    //   157	161	203	android/content/pm/PackageManager$NameNotFoundException
    //   181	185	203	android/content/pm/PackageManager$NameNotFoundException
    //   193	197	203	android/content/pm/PackageManager$NameNotFoundException
    //   201	203	203	android/content/pm/PackageManager$NameNotFoundException
    //   181	185	207	java/io/IOException
    //   193	197	207	java/io/IOException
    //   53	65	211	finally
    //   67	75	223	finally
    //   77	84	223	finally
    //   86	100	223	finally
    //   53	65	233	java/lang/Exception
    //   67	75	233	java/lang/Exception
    //   77	84	233	java/lang/Exception
    //   86	100	233	java/lang/Exception
    //   110	114	237	java/io/IOException
    //   123	127	237	java/io/IOException
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
    if ("bnc_no_value".equals(((PrefHelper)localObject).getAppVersion()))
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
    if (this.context_.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.context_.getSystemService("connectivity")).getNetworkInfo(1);
      return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
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
  
  public boolean prefetchGAdsParams(GAdsParamsFetchEvents paramGAdsParamsFetchEvents)
  {
    if (TextUtils.isEmpty(this.GAIDString_))
    {
      new GAdsPrefetchTask(paramGAdsParamsFetchEvents).executeTask(new Void[0]);
      return true;
    }
    return false;
  }
  
  static abstract interface GAdsParamsFetchEvents
  {
    public abstract void onGAdsFetchFinished();
  }
  
  private class GAdsPrefetchTask
    extends BranchAsyncTask<Void, Void, Void>
  {
    private final SystemObserver.GAdsParamsFetchEvents callback_;
    
    public GAdsPrefetchTask(SystemObserver.GAdsParamsFetchEvents paramGAdsParamsFetchEvents)
    {
      this.callback_ = paramGAdsParamsFetchEvents;
    }
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = new CountDownLatch(1);
      new Thread(new SystemObserver.GAdsPrefetchTask.1(this, paramVarArgs)).start();
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
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (this.callback_ != null) {
        this.callback_.onGAdsFetchFinished();
      }
    }
  }
}
