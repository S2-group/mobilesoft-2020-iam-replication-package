package io.install.sdk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo
{
  public static final String BLANK = "blank_no_value";
  private Context context_;
  private boolean isRealHardwareId;
  
  public DeviceInfo(Context paramContext)
  {
    this.context_ = paramContext;
    this.isRealHardwareId = true;
  }
  
  public static String getLocalIPAddress()
  {
    Object localObject1 = "";
    Object localObject2 = localObject1;
    try
    {
      Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      for (;;)
      {
        localObject2 = localObject1;
        if (!localIterator1.hasNext()) {
          break;
        }
        localObject2 = localObject1;
        Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
        Object localObject3;
        int i;
        do
        {
          do
          {
            localObject2 = localObject1;
            if (!localIterator2.hasNext()) {
              break;
            }
            localObject2 = localObject1;
            localObject3 = (InetAddress)localIterator2.next();
            localObject2 = localObject1;
          } while (((InetAddress)localObject3).isLoopbackAddress());
          localObject2 = localObject1;
          localObject3 = ((InetAddress)localObject3).getHostAddress();
          localObject2 = localObject1;
          i = ((String)localObject3).indexOf(':');
          if (i < 0) {
            i = 1;
          } else {
            i = 0;
          }
        } while (i == 0);
        localObject1 = localObject3;
      }
      return localObject1;
    }
    catch (Throwable localThrowable) {}
    return localObject2;
  }
  
  private boolean isLowOnMemory()
  {
    ActivityManager localActivityManager = (ActivityManager)this.context_.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    if (localActivityManager != null) {
      localActivityManager.getMemoryInfo(localMemoryInfo);
    }
    return localMemoryInfo.lowMemory;
  }
  
  public String getAppVersion()
  {
    try
    {
      PackageInfo localPackageInfo = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
      if (localPackageInfo.versionName != null) {
        return localPackageInfo.versionName;
      }
      return "blank_no_value";
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return "blank_no_value";
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
          localJSONObject.put("name", ((ApplicationInfo)localObject2).loadLabel(localPackageManager));
          String str = ((ApplicationInfo)localObject2).packageName;
          if (str != null)
          {
            localJSONObject.put("app_identifier", str);
            str = getURIScheme(str);
            if (!str.equals("blank_no_value")) {
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
        catch (JSONException|PackageManager.NameNotFoundException localJSONException) {}
        return localJSONArray;
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
  
  public String getURIScheme()
  {
    return getURIScheme(this.context_.getPackageName());
  }
  
  /* Error */
  public String getURIScheme(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 249	io/install/sdk/DeviceInfo:isLowOnMemory	()Z
    //   4: ifne +153 -> 157
    //   7: aload_0
    //   8: getfield 19	io/install/sdk/DeviceInfo:context_	Landroid/content/Context;
    //   11: invokevirtual 101	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore_2
    //   15: aload_2
    //   16: aload_1
    //   17: iconst_0
    //   18: invokevirtual 253	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   21: getfield 180	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   24: astore_1
    //   25: new 255	java/util/jar/JarFile
    //   28: dup
    //   29: aload_1
    //   30: invokespecial 258	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   33: astore_2
    //   34: aload_2
    //   35: aload_2
    //   36: ldc_w 260
    //   39: invokevirtual 264	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   42: invokevirtual 268	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   45: astore_3
    //   46: aload_3
    //   47: invokevirtual 273	java/io/InputStream:available	()I
    //   50: newarray byte
    //   52: astore_1
    //   53: aload_3
    //   54: aload_1
    //   55: invokevirtual 277	java/io/InputStream:read	([B)I
    //   58: pop
    //   59: new 279	io/install/sdk/utils/ApkParser
    //   62: dup
    //   63: invokespecial 280	io/install/sdk/utils/ApkParser:<init>	()V
    //   66: aload_1
    //   67: invokevirtual 284	io/install/sdk/utils/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   70: astore_1
    //   71: aload_3
    //   72: ifnull +7 -> 79
    //   75: aload_3
    //   76: invokevirtual 287	java/io/InputStream:close	()V
    //   79: aload_2
    //   80: invokevirtual 288	java/util/jar/JarFile:close	()V
    //   83: aload_1
    //   84: areturn
    //   85: astore_1
    //   86: aload_2
    //   87: astore 4
    //   89: aload_3
    //   90: astore_2
    //   91: goto +26 -> 117
    //   94: astore_1
    //   95: aconst_null
    //   96: astore_3
    //   97: aload_2
    //   98: astore 4
    //   100: aload_3
    //   101: astore_2
    //   102: goto +15 -> 117
    //   105: aconst_null
    //   106: astore_1
    //   107: goto +34 -> 141
    //   110: astore_1
    //   111: aconst_null
    //   112: astore 4
    //   114: aload 4
    //   116: astore_2
    //   117: aload_2
    //   118: ifnull +7 -> 125
    //   121: aload_2
    //   122: invokevirtual 287	java/io/InputStream:close	()V
    //   125: aload 4
    //   127: ifnull +8 -> 135
    //   130: aload 4
    //   132: invokevirtual 288	java/util/jar/JarFile:close	()V
    //   135: aload_1
    //   136: athrow
    //   137: aconst_null
    //   138: astore_1
    //   139: aload_1
    //   140: astore_2
    //   141: aload_1
    //   142: ifnull +7 -> 149
    //   145: aload_1
    //   146: invokevirtual 287	java/io/InputStream:close	()V
    //   149: aload_2
    //   150: ifnull +7 -> 157
    //   153: aload_2
    //   154: invokevirtual 288	java/util/jar/JarFile:close	()V
    //   157: ldc 8
    //   159: areturn
    //   160: astore_1
    //   161: ldc 8
    //   163: areturn
    //   164: astore_1
    //   165: goto -28 -> 137
    //   168: astore_1
    //   169: goto -64 -> 105
    //   172: astore_1
    //   173: aload_3
    //   174: astore_1
    //   175: goto -34 -> 141
    //   178: astore_2
    //   179: goto -96 -> 83
    //   182: astore_2
    //   183: goto -48 -> 135
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	186	0	this	DeviceInfo
    //   0	186	1	paramString	String
    //   14	140	2	localObject1	Object
    //   178	1	2	localIOException1	java.io.IOException
    //   182	1	2	localIOException2	java.io.IOException
    //   45	129	3	localInputStream	java.io.InputStream
    //   87	44	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   46	71	85	finally
    //   34	46	94	finally
    //   25	34	110	finally
    //   15	25	160	android/content/pm/PackageManager$NameNotFoundException
    //   121	125	160	android/content/pm/PackageManager$NameNotFoundException
    //   130	135	160	android/content/pm/PackageManager$NameNotFoundException
    //   135	137	160	android/content/pm/PackageManager$NameNotFoundException
    //   145	149	160	java/io/IOException
    //   145	149	160	android/content/pm/PackageManager$NameNotFoundException
    //   153	157	160	java/io/IOException
    //   153	157	160	android/content/pm/PackageManager$NameNotFoundException
    //   25	34	164	java/lang/Exception
    //   34	46	168	java/lang/Exception
    //   46	71	172	java/lang/Exception
    //   75	79	178	java/io/IOException
    //   75	79	178	android/content/pm/PackageManager$NameNotFoundException
    //   79	83	178	java/io/IOException
    //   79	83	178	android/content/pm/PackageManager$NameNotFoundException
    //   121	125	182	java/io/IOException
    //   130	135	182	java/io/IOException
  }
  
  public String getUniqueID()
  {
    if (this.context_ != null) {
      return Settings.Secure.getString(this.context_.getContentResolver(), "android_id");
    }
    return null;
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
