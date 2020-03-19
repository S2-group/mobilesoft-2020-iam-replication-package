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

class SystemObserver
{
  String GAIDString_ = null;
  int LATVal_ = 0;
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
    //   0: ldc 116
    //   2: astore_3
    //   3: aload_3
    //   4: astore_2
    //   5: aload_0
    //   6: invokespecial 285	io/branch/referral/SystemObserver:isLowOnMemory	()Z
    //   9: ifne +152 -> 161
    //   12: aload_0
    //   13: getfield 31	io/branch/referral/SystemObserver:context_	Landroid/content/Context;
    //   16: invokevirtual 100	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   19: astore 4
    //   21: aload_3
    //   22: astore_2
    //   23: aload 4
    //   25: aload_1
    //   26: iconst_0
    //   27: invokevirtual 289	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   30: getfield 197	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
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
    //   51: new 291	java/util/jar/JarFile
    //   54: dup
    //   55: aload 4
    //   57: invokespecial 294	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   60: astore 4
    //   62: aload 8
    //   64: astore_1
    //   65: aload 7
    //   67: astore_2
    //   68: aload 4
    //   70: aload 4
    //   72: ldc_w 296
    //   75: invokevirtual 300	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   78: invokevirtual 304	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   81: astore 5
    //   83: aload 5
    //   85: astore_1
    //   86: aload 5
    //   88: astore_2
    //   89: aload 5
    //   91: invokevirtual 309	java/io/InputStream:available	()I
    //   94: newarray byte
    //   96: astore 6
    //   98: aload 5
    //   100: astore_1
    //   101: aload 5
    //   103: astore_2
    //   104: aload 5
    //   106: aload 6
    //   108: invokevirtual 313	java/io/InputStream:read	([B)I
    //   111: pop
    //   112: aload 5
    //   114: astore_1
    //   115: aload 5
    //   117: astore_2
    //   118: new 315	io/branch/referral/ApkParser
    //   121: dup
    //   122: invokespecial 316	io/branch/referral/ApkParser:<init>	()V
    //   125: aload 6
    //   127: invokevirtual 320	io/branch/referral/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   130: astore 6
    //   132: aload 6
    //   134: astore_1
    //   135: aload 5
    //   137: ifnull +10 -> 147
    //   140: aload_1
    //   141: astore_2
    //   142: aload 5
    //   144: invokevirtual 323	java/io/InputStream:close	()V
    //   147: aload 4
    //   149: ifnull +10 -> 159
    //   152: aload_1
    //   153: astore_2
    //   154: aload 4
    //   156: invokevirtual 324	java/util/jar/JarFile:close	()V
    //   159: aload_1
    //   160: astore_2
    //   161: aload_2
    //   162: areturn
    //   163: astore_2
    //   164: aload_1
    //   165: areturn
    //   166: astore_2
    //   167: aload 5
    //   169: astore 4
    //   171: aload 4
    //   173: ifnull +10 -> 183
    //   176: aload_3
    //   177: astore_2
    //   178: aload 4
    //   180: invokevirtual 323	java/io/InputStream:close	()V
    //   183: aload_3
    //   184: astore_2
    //   185: aload_1
    //   186: ifnull -25 -> 161
    //   189: aload_3
    //   190: astore_2
    //   191: aload_1
    //   192: invokevirtual 324	java/util/jar/JarFile:close	()V
    //   195: ldc 116
    //   197: areturn
    //   198: astore_1
    //   199: ldc 116
    //   201: areturn
    //   202: astore_1
    //   203: aload 6
    //   205: astore 5
    //   207: aload_2
    //   208: astore 4
    //   210: aload 4
    //   212: ifnull +10 -> 222
    //   215: aload_3
    //   216: astore_2
    //   217: aload 4
    //   219: invokevirtual 323	java/io/InputStream:close	()V
    //   222: aload 5
    //   224: ifnull +10 -> 234
    //   227: aload_3
    //   228: astore_2
    //   229: aload 5
    //   231: invokevirtual 324	java/util/jar/JarFile:close	()V
    //   234: aload_3
    //   235: astore_2
    //   236: aload_1
    //   237: athrow
    //   238: astore_1
    //   239: aload_2
    //   240: areturn
    //   241: astore_2
    //   242: goto -8 -> 234
    //   245: astore_2
    //   246: aload 4
    //   248: astore 5
    //   250: aload_1
    //   251: astore 4
    //   253: aload_2
    //   254: astore_1
    //   255: goto -45 -> 210
    //   258: astore_1
    //   259: aload 4
    //   261: astore_1
    //   262: aload_2
    //   263: astore 4
    //   265: goto -94 -> 171
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	this	SystemObserver
    //   0	268	1	paramString	String
    //   4	158	2	localObject1	Object
    //   163	1	2	localIOException1	java.io.IOException
    //   166	1	2	localException	Exception
    //   177	63	2	str1	String
    //   241	1	2	localIOException2	java.io.IOException
    //   245	18	2	localObject2	Object
    //   2	233	3	str2	String
    //   19	245	4	localObject3	Object
    //   49	200	5	localObject4	Object
    //   36	168	6	localObject5	Object
    //   46	20	7	localObject6	Object
    //   43	20	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   142	147	163	java/io/IOException
    //   154	159	163	java/io/IOException
    //   51	62	166	java/lang/Exception
    //   178	183	198	java/io/IOException
    //   191	195	198	java/io/IOException
    //   51	62	202	finally
    //   23	35	238	android/content/pm/PackageManager$NameNotFoundException
    //   142	147	238	android/content/pm/PackageManager$NameNotFoundException
    //   154	159	238	android/content/pm/PackageManager$NameNotFoundException
    //   178	183	238	android/content/pm/PackageManager$NameNotFoundException
    //   191	195	238	android/content/pm/PackageManager$NameNotFoundException
    //   217	222	238	android/content/pm/PackageManager$NameNotFoundException
    //   229	234	238	android/content/pm/PackageManager$NameNotFoundException
    //   236	238	238	android/content/pm/PackageManager$NameNotFoundException
    //   217	222	241	java/io/IOException
    //   229	234	241	java/io/IOException
    //   68	83	245	finally
    //   89	98	245	finally
    //   104	112	245	finally
    //   118	132	245	finally
    //   68	83	258	java/lang/Exception
    //   89	98	258	java/lang/Exception
    //   104	112	258	java/lang/Exception
    //   118	132	258	java/lang/Exception
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
  
  public boolean prefetchGAdsParams(GAdsParamsFetchEvents paramGAdsParamsFetchEvents)
  {
    boolean bool = false;
    if (TextUtils.isEmpty(this.GAIDString_))
    {
      bool = true;
      new GAdsPrefetchTask(paramGAdsParamsFetchEvents).executeTask(new Void[0]);
    }
    return bool;
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
    
    protected Void doInBackground(final Void... paramVarArgs)
    {
      paramVarArgs = new CountDownLatch(1);
      new Thread(new Runnable()
      {
        public void run()
        {
          Process.setThreadPriority(-19);
          Object localObject = SystemObserver.this.getAdInfoObject();
          SystemObserver.this.getAdvertisingId(localObject);
          SystemObserver.this.getLATValue(localObject);
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
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (this.callback_ != null) {
        this.callback_.onGAdsFetchFinished();
      }
    }
  }
}
