package o;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Process;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class OY
{
  Context ˊ;
  String ˋ = null;
  int ˏ = 0;
  boolean ॱ;
  
  public OY(Context paramContext)
  {
    this.ˊ = paramContext;
    this.ॱ = true;
  }
  
  public static String ʼ()
  {
    Object localObject1 = "";
    Object localObject2 = localObject1;
    try
    {
      Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      label128:
      for (;;)
      {
        localObject2 = localObject1;
        if (!localIterator1.hasNext()) {
          break;
        }
        localObject2 = localObject1;
        Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
        for (;;)
        {
          localObject2 = localObject1;
          if (!localIterator2.hasNext()) {
            break label128;
          }
          localObject2 = localObject1;
          Object localObject3 = (InetAddress)localIterator2.next();
          localObject2 = localObject1;
          if (!((InetAddress)localObject3).isLoopbackAddress())
          {
            localObject2 = localObject1;
            localObject3 = ((InetAddress)localObject3).getHostAddress();
            localObject2 = localObject1;
            int i = ((String)localObject3).indexOf(':');
            if (i < 0) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              localObject1 = localObject3;
              break;
            }
          }
        }
      }
      return localObject1;
    }
    catch (Throwable localThrowable) {}
    return localObject2;
  }
  
  public final String ˊ()
  {
    try
    {
      String str = this.ˊ.getPackageManager().getPackageInfo(this.ˊ.getPackageName(), 0).packageName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public final String ˊ(Object paramObject)
  {
    try
    {
      this.ˋ = ((String)paramObject.getClass().getMethod("getId", new Class[0]).invoke(paramObject, new Object[0]));
      return this.ˋ;
    }
    catch (Exception paramObject)
    {
      for (;;) {}
    }
  }
  
  public final int ˋ(Object paramObject)
  {
    try
    {
      if (!((Boolean)paramObject.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(paramObject, new Object[0])).booleanValue()) {
        break label49;
      }
      i = 1;
    }
    catch (Exception paramObject)
    {
      for (;;)
      {
        continue;
        label49:
        int i = 0;
      }
    }
    this.ˏ = i;
    return this.ˏ;
  }
  
  public final String ˋ()
  {
    try
    {
      Object localObject = this.ˊ.getPackageManager().getPackageInfo(this.ˊ.getPackageName(), 0);
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
  
  /* Error */
  public final String ˋ(String paramString)
  {
    // Byte code:
    //   0: ldc -112
    //   2: astore 8
    //   4: aload_0
    //   5: getfield 31	o/OY:ˊ	Landroid/content/Context;
    //   8: ldc -107
    //   10: invokevirtual 153	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   13: checkcast 155	android/app/ActivityManager
    //   16: astore_2
    //   17: new 157	android/app/ActivityManager$MemoryInfo
    //   20: dup
    //   21: invokespecial 158	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   24: astore_3
    //   25: aload_2
    //   26: aload_3
    //   27: invokevirtual 162	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   30: aload_3
    //   31: getfield 165	android/app/ActivityManager$MemoryInfo:lowMemory	Z
    //   34: ifne +246 -> 280
    //   37: aload_0
    //   38: getfield 31	o/OY:ˊ	Landroid/content/Context;
    //   41: invokevirtual 93	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: astore_2
    //   45: aload 8
    //   47: astore 7
    //   49: aload_2
    //   50: aload_1
    //   51: iconst_0
    //   52: invokevirtual 169	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   55: getfield 174	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   58: astore_1
    //   59: aconst_null
    //   60: astore 5
    //   62: aconst_null
    //   63: astore_3
    //   64: aconst_null
    //   65: astore 7
    //   67: aconst_null
    //   68: astore_2
    //   69: aload_2
    //   70: astore 4
    //   72: aload 7
    //   74: astore 6
    //   76: new 176	java/util/jar/JarFile
    //   79: dup
    //   80: aload_1
    //   81: invokespecial 179	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   84: astore 9
    //   86: aload 9
    //   88: astore_1
    //   89: aload_1
    //   90: astore_3
    //   91: aload_2
    //   92: astore 4
    //   94: aload_1
    //   95: astore 5
    //   97: aload 7
    //   99: astore 6
    //   101: aload 9
    //   103: aload 9
    //   105: ldc -75
    //   107: invokevirtual 185	java/util/jar/JarFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   110: invokevirtual 189	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   113: astore 7
    //   115: aload 7
    //   117: astore_2
    //   118: aload_1
    //   119: astore_3
    //   120: aload_2
    //   121: astore 4
    //   123: aload_1
    //   124: astore 5
    //   126: aload_2
    //   127: astore 6
    //   129: aload 7
    //   131: invokevirtual 195	java/io/InputStream:available	()I
    //   134: newarray byte
    //   136: astore 7
    //   138: aload_1
    //   139: astore_3
    //   140: aload_2
    //   141: astore 4
    //   143: aload_1
    //   144: astore 5
    //   146: aload_2
    //   147: astore 6
    //   149: aload_2
    //   150: aload 7
    //   152: invokevirtual 199	java/io/InputStream:read	([B)I
    //   155: pop
    //   156: aload_1
    //   157: astore_3
    //   158: aload_2
    //   159: astore 4
    //   161: aload_1
    //   162: astore 5
    //   164: aload_2
    //   165: astore 6
    //   167: new 201	o/Ou
    //   170: dup
    //   171: invokespecial 202	o/Ou:<init>	()V
    //   174: pop
    //   175: aload_1
    //   176: astore_3
    //   177: aload_2
    //   178: astore 4
    //   180: aload_1
    //   181: astore 5
    //   183: aload_2
    //   184: astore 6
    //   186: aload 7
    //   188: invokestatic 205	o/Ou:ˋ	([B)Ljava/lang/String;
    //   191: astore 7
    //   193: aload 7
    //   195: astore_3
    //   196: aload_2
    //   197: ifnull +10 -> 207
    //   200: aload_3
    //   201: astore 7
    //   203: aload_2
    //   204: invokevirtual 208	java/io/InputStream:close	()V
    //   207: aload_3
    //   208: astore 7
    //   210: aload_1
    //   211: invokevirtual 209	java/util/jar/JarFile:close	()V
    //   214: aload_3
    //   215: areturn
    //   216: aload 6
    //   218: ifnull +12 -> 230
    //   221: aload 8
    //   223: astore 7
    //   225: aload 6
    //   227: invokevirtual 208	java/io/InputStream:close	()V
    //   230: aload 5
    //   232: ifnull +12 -> 244
    //   235: aload 8
    //   237: astore 7
    //   239: aload 5
    //   241: invokevirtual 209	java/util/jar/JarFile:close	()V
    //   244: ldc -112
    //   246: areturn
    //   247: astore_1
    //   248: aload 4
    //   250: ifnull +12 -> 262
    //   253: aload 8
    //   255: astore 7
    //   257: aload 4
    //   259: invokevirtual 208	java/io/InputStream:close	()V
    //   262: aload_3
    //   263: ifnull +11 -> 274
    //   266: aload 8
    //   268: astore 7
    //   270: aload_3
    //   271: invokevirtual 209	java/util/jar/JarFile:close	()V
    //   274: aload 8
    //   276: astore 7
    //   278: aload_1
    //   279: athrow
    //   280: ldc -112
    //   282: areturn
    //   283: astore_1
    //   284: aload 7
    //   286: areturn
    //   287: astore_1
    //   288: goto -72 -> 216
    //   291: astore_1
    //   292: aload_3
    //   293: areturn
    //   294: astore_1
    //   295: ldc -112
    //   297: areturn
    //   298: astore_2
    //   299: goto -25 -> 274
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	302	0	this	OY
    //   0	302	1	paramString	String
    //   16	188	2	localObject1	Object
    //   298	1	2	localIOException	java.io.IOException
    //   24	269	3	localObject2	Object
    //   70	188	4	localObject3	Object
    //   60	180	5	str1	String
    //   74	152	6	localObject4	Object
    //   47	238	7	localObject5	Object
    //   2	273	8	str2	String
    //   84	20	9	localJarFile	java.util.jar.JarFile
    // Exception table:
    //   from	to	target	type
    //   76	86	247	finally
    //   101	115	247	finally
    //   129	138	247	finally
    //   149	156	247	finally
    //   167	175	247	finally
    //   186	193	247	finally
    //   49	59	283	android/content/pm/PackageManager$NameNotFoundException
    //   203	207	283	android/content/pm/PackageManager$NameNotFoundException
    //   210	214	283	android/content/pm/PackageManager$NameNotFoundException
    //   225	230	283	android/content/pm/PackageManager$NameNotFoundException
    //   239	244	283	android/content/pm/PackageManager$NameNotFoundException
    //   257	262	283	android/content/pm/PackageManager$NameNotFoundException
    //   270	274	283	android/content/pm/PackageManager$NameNotFoundException
    //   278	280	283	android/content/pm/PackageManager$NameNotFoundException
    //   76	86	287	java/lang/Exception
    //   101	115	287	java/lang/Exception
    //   129	138	287	java/lang/Exception
    //   149	156	287	java/lang/Exception
    //   167	175	287	java/lang/Exception
    //   186	193	287	java/lang/Exception
    //   203	207	291	java/io/IOException
    //   210	214	291	java/io/IOException
    //   225	230	294	java/io/IOException
    //   239	244	294	java/io/IOException
    //   257	262	298	java/io/IOException
    //   270	274	298	java/io/IOException
  }
  
  public final Object ˎ()
  {
    try
    {
      Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { this.ˊ });
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  @SuppressLint({"NewApi"})
  public final int ˏ()
  {
    OI.ॱ(this.ˊ);
    Object localObject = ˋ();
    if ((!"bnc_no_value".equals(OI.ˋ.ˊ.getString("bnc_app_version", "bnc_no_value"))) || (Build.VERSION.SDK_INT >= 9)) {}
    try
    {
      localObject = this.ˊ.getPackageManager().getPackageInfo(this.ˊ.getPackageName(), 0);
      long l1 = ((PackageInfo)localObject).lastUpdateTime;
      long l2 = ((PackageInfo)localObject).firstInstallTime;
      if (l1 != l2) {
        return 2;
      }
      return 0;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return 0;
    if (!OI.ˋ.ˊ.getString("bnc_app_version", "bnc_no_value").equals(localObject)) {
      return 2;
    }
    return 1;
  }
  
  @SuppressLint({"NewApi"})
  public final JSONArray ॱ()
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager = this.ˊ.getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    if (localObject != null)
    {
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        JSONObject localJSONObject;
        if ((localApplicationInfo.flags & 0x1) != 1) {
          localJSONObject = new JSONObject();
        }
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
            localJSONObject.put(OF.ˊ.ˉ.ꜞ, localObject);
            localObject = ˋ((String)localObject);
            if (!((String)localObject).equals("bnc_no_value")) {
              localJSONObject.put(OF.ˊ.ˊˊ.ꜞ, localObject);
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
          localJSONObject.put(OF.ˊ.ι.ꜞ, "Android");
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException|PackageManager.NameNotFoundException localJSONException)
        {
          for (;;) {}
        }
      }
    }
    return localJSONArray;
  }
  
  static abstract interface iF
  {
    public abstract void ˎ();
  }
  
  final class if
    extends OA<Void, Void, Void>
  {
    private final Oy ˊ;
    
    public if(Oy paramOy)
    {
      this.ˊ = paramOy;
    }
    
    private Void ॱ()
    {
      final CountDownLatch localCountDownLatch = new CountDownLatch(1);
      new Thread(new Runnable()
      {
        public final void run()
        {
          Process.setThreadPriority(-19);
          Object localObject = OY.this.ˎ();
          OY.this.ˊ(localObject);
          OY.this.ˋ(localObject);
          localCountDownLatch.countDown();
        }
      }).start();
      try
      {
        localCountDownLatch.await(1500L, TimeUnit.MILLISECONDS);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
      return null;
    }
  }
}
