package io.branch.referral;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SystemObserver
{
  Context context_;
  boolean isRealHardwareId;
  
  public SystemObserver(Context paramContext)
  {
    this.context_ = paramContext;
    this.isRealHardwareId = true;
  }
  
  public static boolean getBluetoothPresent()
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public final String getAdvertisingId()
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
  
  public final String getAppVersion()
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
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return "bnc_no_value";
  }
  
  public final String getBluetoothVersion()
  {
    try
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
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "bnc_no_value";
  }
  
  public final int getLATValue()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  @SuppressLint({"NewApi"})
  public final JSONArray getListOfApps()
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
          if (localObject == null) {
            localObject = null;
          } else {
            localObject = ((CharSequence)localObject).toString();
          }
          if (localObject != null) {
            localJSONObject.put("name", localObject);
          }
          localObject = localApplicationInfo.packageName;
          if (localObject != null)
          {
            localJSONObject.put(Defines.Jsonkey.AppIdentifier.key, localObject);
            localObject = getURIScheme((String)localObject);
            if (!((String)localObject).equals("bnc_no_value")) {
              localJSONObject.put(Defines.Jsonkey.URIScheme.key, localObject);
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
          localJSONObject.put(Defines.Jsonkey.OS.key, "Android");
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException|PackageManager.NameNotFoundException localJSONException) {}
        return localJSONArray;
      }
    }
  }
  
  public final boolean getNFCPresent()
  {
    try
    {
      boolean bool = this.context_.getPackageManager().hasSystemFeature("android.hardware.nfc");
      return bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public final boolean getTelephonePresent()
  {
    try
    {
      boolean bool = this.context_.getPackageManager().hasSystemFeature("android.hardware.telephony");
      return bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  /* Error */
  public final String getURIScheme(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	io/branch/referral/SystemObserver:context_	Landroid/content/Context;
    //   4: ldc -4
    //   6: invokevirtual 256	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 258	android/app/ActivityManager
    //   12: astore_2
    //   13: new 260	android/app/ActivityManager$MemoryInfo
    //   16: dup
    //   17: invokespecial 261	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   20: astore_3
    //   21: aload_2
    //   22: aload_3
    //   23: invokevirtual 265	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   26: aload_3
    //   27: getfield 268	android/app/ActivityManager$MemoryInfo:lowMemory	Z
    //   30: ifne +163 -> 193
    //   33: aload_0
    //   34: getfield 15	io/branch/referral/SystemObserver:context_	Landroid/content/Context;
    //   37: invokevirtual 75	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   40: astore_2
    //   41: aload_2
    //   42: aload_1
    //   43: iconst_0
    //   44: invokevirtual 272	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   47: getfield 200	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   50: astore_1
    //   51: aconst_null
    //   52: astore 4
    //   54: aconst_null
    //   55: astore_3
    //   56: new 274	java/util/jar/JarFile
    //   59: dup
    //   60: aload_1
    //   61: invokespecial 275	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   64: astore_1
    //   65: aload_1
    //   66: aload_1
    //   67: ldc_w 277
    //   70: invokevirtual 281	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   73: invokevirtual 285	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   76: astore_2
    //   77: aload_2
    //   78: invokevirtual 290	java/io/InputStream:available	()I
    //   81: newarray byte
    //   83: astore_3
    //   84: aload_2
    //   85: aload_3
    //   86: invokevirtual 294	java/io/InputStream:read	([B)I
    //   89: pop
    //   90: new 296	io/branch/referral/ApkParser
    //   93: dup
    //   94: invokespecial 297	io/branch/referral/ApkParser:<init>	()V
    //   97: pop
    //   98: aload_3
    //   99: invokestatic 301	io/branch/referral/ApkParser:decompressXML	([B)Ljava/lang/String;
    //   102: astore_3
    //   103: aload_2
    //   104: ifnull +7 -> 111
    //   107: aload_2
    //   108: invokevirtual 304	java/io/InputStream:close	()V
    //   111: aload_1
    //   112: invokevirtual 305	java/util/jar/JarFile:close	()V
    //   115: aload_3
    //   116: areturn
    //   117: astore_3
    //   118: goto +20 -> 138
    //   121: goto +39 -> 160
    //   124: goto +54 -> 178
    //   127: astore_3
    //   128: aconst_null
    //   129: astore_2
    //   130: goto +8 -> 138
    //   133: astore_3
    //   134: aconst_null
    //   135: astore_1
    //   136: aload_1
    //   137: astore_2
    //   138: aload_2
    //   139: ifnull +7 -> 146
    //   142: aload_2
    //   143: invokevirtual 304	java/io/InputStream:close	()V
    //   146: aload_1
    //   147: ifnull +7 -> 154
    //   150: aload_1
    //   151: invokevirtual 305	java/util/jar/JarFile:close	()V
    //   154: aload_3
    //   155: athrow
    //   156: aconst_null
    //   157: astore_1
    //   158: aload_3
    //   159: astore_2
    //   160: aload_2
    //   161: ifnull +76 -> 237
    //   164: aload_2
    //   165: invokevirtual 304	java/io/InputStream:close	()V
    //   168: goto +69 -> 237
    //   171: aload_1
    //   172: invokevirtual 305	java/util/jar/JarFile:close	()V
    //   175: ldc 92
    //   177: areturn
    //   178: aload_2
    //   179: ifnull +7 -> 186
    //   182: aload_2
    //   183: invokevirtual 304	java/io/InputStream:close	()V
    //   186: aload_1
    //   187: ifnull +6 -> 193
    //   190: goto -19 -> 171
    //   193: ldc 92
    //   195: areturn
    //   196: astore_1
    //   197: ldc 92
    //   199: areturn
    //   200: astore_1
    //   201: goto +43 -> 244
    //   204: astore_1
    //   205: goto -49 -> 156
    //   208: astore_2
    //   209: aload 4
    //   211: astore_2
    //   212: goto -34 -> 178
    //   215: astore_2
    //   216: aload_3
    //   217: astore_2
    //   218: goto -58 -> 160
    //   221: astore_3
    //   222: goto -98 -> 124
    //   225: astore_3
    //   226: goto -105 -> 121
    //   229: astore_1
    //   230: goto -115 -> 115
    //   233: astore_1
    //   234: goto -80 -> 154
    //   237: aload_1
    //   238: ifnull -45 -> 193
    //   241: goto -70 -> 171
    //   244: aconst_null
    //   245: astore_1
    //   246: aload 4
    //   248: astore_2
    //   249: goto -71 -> 178
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	this	SystemObserver
    //   0	252	1	paramString	String
    //   12	171	2	localObject1	Object
    //   208	1	2	localException1	Exception
    //   211	1	2	localObject2	Object
    //   215	1	2	localOutOfMemoryError1	OutOfMemoryError
    //   217	32	2	localObject3	Object
    //   20	96	3	localObject4	Object
    //   117	1	3	localObject5	Object
    //   127	1	3	localObject6	Object
    //   133	84	3	localObject7	Object
    //   221	1	3	localException2	Exception
    //   225	1	3	localOutOfMemoryError2	OutOfMemoryError
    //   52	195	4	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   77	103	117	finally
    //   65	77	127	finally
    //   56	65	133	finally
    //   41	51	196	android/content/pm/PackageManager$NameNotFoundException
    //   142	146	196	android/content/pm/PackageManager$NameNotFoundException
    //   150	154	196	android/content/pm/PackageManager$NameNotFoundException
    //   154	156	196	android/content/pm/PackageManager$NameNotFoundException
    //   164	168	196	java/io/IOException
    //   164	168	196	android/content/pm/PackageManager$NameNotFoundException
    //   171	175	196	java/io/IOException
    //   171	175	196	android/content/pm/PackageManager$NameNotFoundException
    //   182	186	196	java/io/IOException
    //   182	186	196	android/content/pm/PackageManager$NameNotFoundException
    //   56	65	200	java/lang/Exception
    //   56	65	204	java/lang/OutOfMemoryError
    //   65	77	208	java/lang/Exception
    //   65	77	215	java/lang/OutOfMemoryError
    //   77	103	221	java/lang/Exception
    //   77	103	225	java/lang/OutOfMemoryError
    //   107	111	229	java/io/IOException
    //   107	111	229	android/content/pm/PackageManager$NameNotFoundException
    //   111	115	229	java/io/IOException
    //   111	115	229	android/content/pm/PackageManager$NameNotFoundException
    //   142	146	233	java/io/IOException
    //   150	154	233	java/io/IOException
  }
  
  @SuppressLint({"NewApi"})
  public final int getUpdateState$1385f2()
  {
    PrefHelper.getInstance(this.context_);
    Object localObject = getAppVersion();
    if ("bnc_no_value".equals(PrefHelper.prefHelper_.appSharedPrefs_.getString("bnc_app_version", "bnc_no_value")))
    {
      PrefHelper.prefHelper_.prefsEditor_.putString("bnc_app_version", (String)localObject);
      PrefHelper.prefHelper_.prefsEditor_.commit();
      if (Build.VERSION.SDK_INT < 9) {}
    }
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
    if (!PrefHelper.prefHelper_.appSharedPrefs_.getString("bnc_app_version", "bnc_no_value").equals(localObject))
    {
      PrefHelper.prefHelper_.prefsEditor_.putString("bnc_app_version", (String)localObject);
      PrefHelper.prefHelper_.prefsEditor_.commit();
      return 2;
    }
    return 1;
    return 0;
  }
}
