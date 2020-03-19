package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import com.tencent.map.geolocation.internal.TencentExtraKeys;
import com.tencent.map.geolocation.internal.TencentLog;
import com.tencent.tencentmap.lbssdk.service.e;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
import org.eclipse.jdt.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

public final class j
  implements f
{
  private aj a;
  
  public j(aj paramAj)
  {
    this.a = paramAj;
  }
  
  private static double a(double paramDouble)
  {
    return paramDouble * 3.141592653589793D / 180.0D;
  }
  
  public static double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    paramDouble1 = a(paramDouble1);
    paramDouble3 = a(paramDouble3);
    paramDouble2 = a(paramDouble2);
    paramDouble4 = a(paramDouble4);
    return Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((paramDouble1 - paramDouble3) / 2.0D), 2.0D) + Math.cos(paramDouble1) * Math.cos(paramDouble3) * Math.pow(Math.sin((paramDouble2 - paramDouble4) / 2.0D), 2.0D))) * 2.0D * 6378.137D * 10000.0D) / 10000.0D * 1000.0D;
  }
  
  public static double a(double paramDouble, int paramInt)
  {
    try
    {
      if (Double.isNaN(paramDouble)) {
        return 0.0D;
      }
      paramDouble = BigDecimal.valueOf(paramDouble).setScale(paramInt, RoundingMode.HALF_DOWN).doubleValue();
      return paramDouble;
    }
    catch (Exception localException) {}
    return 0.0D;
  }
  
  @SuppressLint({"MissingPermission"})
  public static int a(Context paramContext)
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localConnectivityManager == null) {
        paramContext = null;
      } else {
        paramContext = localConnectivityManager.getActiveNetworkInfo();
      }
      if ((paramContext != null) && (paramContext.isConnected()))
      {
        if (Build.VERSION.SDK_INT >= 16)
        {
          if (localConnectivityManager.isActiveNetworkMetered()) {
            return dd.b;
          }
          return dd.c;
        }
        if (1 == paramContext.getType()) {
          return dd.c;
        }
        return dd.b;
      }
      int i = dd.a;
      return i;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return dd.a;
  }
  
  public static String a(ei paramEi, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static List<ScanResult> a(WifiManager paramWifiManager)
  {
    if (paramWifiManager != null) {
      try
      {
        paramWifiManager = paramWifiManager.getScanResults();
      }
      catch (Throwable paramWifiManager)
      {
        cs.a("Wifis", "cannot getScanResults", paramWifiManager);
      }
    } else {
      paramWifiManager = null;
    }
    Object localObject = paramWifiManager;
    if (paramWifiManager == null) {
      localObject = Collections.emptyList();
    }
    return localObject;
  }
  
  public static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
    return;
  }
  
  private static void a(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("getCellInfoWithJsonFormat: ");
    localStringBuilder.append(paramString);
    localStringBuilder.append("isGsm=");
    boolean bool = true;
    if (paramInt3 != 1) {
      bool = false;
    }
    localStringBuilder.append(bool);
    localStringBuilder.append(", mcc,mnc=");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(",");
    localStringBuilder.append(paramInt2);
    localStringBuilder.append(", lac,cid=");
    localStringBuilder.append(paramInt4);
    localStringBuilder.append(",");
    localStringBuilder.append(paramInt5);
  }
  
  public static void a(String paramString1, int paramInt, String paramString2)
  {
    TencentLog localTencentLog = TencentExtraKeys.getTencentLog();
    if ((paramString2 != null) && (localTencentLog != null)) {
      localTencentLog.println(paramString1, paramInt, paramString2);
    }
  }
  
  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString2);
    localStringBuilder.append(" exception: ");
    localStringBuilder.append(Log.getStackTraceString(paramThrowable));
    a(paramString1, 6, localStringBuilder.toString());
  }
  
  public static void a(List<ScanResult> paramList)
  {
    HashSet localHashSet = new HashSet();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = ((ScanResult)paramList.next()).BSSID;
      if ((str != null) && (!str.equals("000000000000")) && (!str.equals("00-00-00-00-00-00")) && (!str.equals("00:00:00:00:00:00")))
      {
        if (localHashSet.contains(str)) {
          paramList.remove();
        } else {
          localHashSet.add(str);
        }
      }
      else {
        paramList.remove();
      }
    }
  }
  
  public static boolean a(Location paramLocation, double[] paramArrayOfDouble)
  {
    int n = (int)(paramLocation.getLatitude() * 1000000.0D);
    int i1 = (int)(paramLocation.getLongitude() * 1000000.0D);
    paramLocation = g("tencent_loc_lib");
    int k = 0;
    int m = 0;
    while (k < paramLocation.length())
    {
      int i2 = paramLocation.charAt(k);
      int j = 256;
      int i = j;
      if (i2 >= 65)
      {
        i = j;
        if (i2 <= 90) {
          i = i2 - 65;
        }
      }
      j = i;
      if (i2 >= 97)
      {
        j = i;
        if (i2 <= 122) {
          j = i2 - 97 + 64;
        }
      }
      i = j;
      if (i2 >= 48)
      {
        i = j;
        if (i2 <= 57) {
          i = i2 + 128 - 48;
        }
      }
      m += i;
      k += 1;
    }
    paramLocation = new double[2];
    try
    {
      e.b(n ^ m, i1 ^ m, paramLocation);
      paramArrayOfDouble[0] = paramLocation[0];
      paramArrayOfDouble[1] = paramLocation[1];
      return true;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public static byte[] a(File paramFile)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 308	java/io/File:exists	()Z
    //   4: ifeq +128 -> 132
    //   7: aload_0
    //   8: invokevirtual 311	java/io/File:length	()J
    //   11: lconst_0
    //   12: lcmp
    //   13: ifne +6 -> 19
    //   16: goto +116 -> 132
    //   19: new 313	java/io/ByteArrayOutputStream
    //   22: dup
    //   23: invokespecial 314	java/io/ByteArrayOutputStream:<init>	()V
    //   26: astore 4
    //   28: aconst_null
    //   29: astore_3
    //   30: aconst_null
    //   31: astore_2
    //   32: sipush 4096
    //   35: newarray byte
    //   37: astore 5
    //   39: new 316	java/io/BufferedInputStream
    //   42: dup
    //   43: new 318	java/io/FileInputStream
    //   46: dup
    //   47: aload_0
    //   48: invokespecial 321	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   51: invokespecial 324	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   54: astore_0
    //   55: aload_0
    //   56: aload 5
    //   58: invokevirtual 328	java/io/BufferedInputStream:read	([B)I
    //   61: istore_1
    //   62: iload_1
    //   63: iconst_m1
    //   64: if_icmpeq +15 -> 79
    //   67: aload 4
    //   69: aload 5
    //   71: iconst_0
    //   72: iload_1
    //   73: invokevirtual 332	java/io/ByteArrayOutputStream:write	([BII)V
    //   76: goto -21 -> 55
    //   79: aload 4
    //   81: invokevirtual 336	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   84: astore_2
    //   85: aload_0
    //   86: invokestatic 338	c/t/m/g/j:a	(Ljava/io/Closeable;)V
    //   89: aload 4
    //   91: invokestatic 338	c/t/m/g/j:a	(Ljava/io/Closeable;)V
    //   94: aload_2
    //   95: areturn
    //   96: astore_3
    //   97: aload_0
    //   98: astore_2
    //   99: aload_3
    //   100: astore_0
    //   101: goto +20 -> 121
    //   104: astore_3
    //   105: aload_0
    //   106: astore_2
    //   107: aload_3
    //   108: astore_0
    //   109: goto +10 -> 119
    //   112: astore_0
    //   113: goto +8 -> 121
    //   116: astore_0
    //   117: aload_3
    //   118: astore_2
    //   119: aload_0
    //   120: athrow
    //   121: aload_2
    //   122: invokestatic 338	c/t/m/g/j:a	(Ljava/io/Closeable;)V
    //   125: aload 4
    //   127: invokestatic 338	c/t/m/g/j:a	(Ljava/io/Closeable;)V
    //   130: aload_0
    //   131: athrow
    //   132: iconst_0
    //   133: newarray byte
    //   135: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	paramFile	File
    //   61	12	1	i	int
    //   31	91	2	localObject1	Object
    //   29	1	3	localObject2	Object
    //   96	4	3	localObject3	Object
    //   104	14	3	localIOException	IOException
    //   26	100	4	localByteArrayOutputStream	ByteArrayOutputStream
    //   37	33	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   55	62	96	finally
    //   67	76	96	finally
    //   79	85	96	finally
    //   55	62	104	java/io/IOException
    //   67	76	104	java/io/IOException
    //   79	85	104	java/io/IOException
    //   39	55	112	finally
    //   119	121	112	finally
    //   39	55	116	java/io/IOException
  }
  
  public static byte[] a(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(256);
    byte[] arrayOfByte = new byte['Ā'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream.close();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static byte[] a(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramArrayOfByte.length);
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      try
      {
        localByteArrayOutputStream.close();
        return paramArrayOfByte;
      }
      catch (Throwable localThrowable1) {}
      localThrowable2.printStackTrace();
    }
    catch (Throwable localThrowable2)
    {
      paramArrayOfByte = localObject;
    }
    return paramArrayOfByte;
  }
  
  public static int b(WifiManager paramWifiManager)
  {
    if (paramWifiManager == null) {
      return 4;
    }
    try
    {
      int i = paramWifiManager.getWifiState();
      return i;
    }
    catch (Throwable paramWifiManager) {}
    return 4;
  }
  
  public static String b(Context paramContext)
  {
    if (paramContext == null) {
      return "{}";
    }
    try
    {
      Object localObject1 = (WifiManager)paramContext.getSystemService("wifi");
      Object localObject2 = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if ((localObject1 != null) && (localObject2 != null))
      {
        paramContext = ((WifiManager)localObject1).getConnectionInfo();
        localObject1 = ((ConnectivityManager)localObject2).getNetworkInfo(1);
        if ((paramContext != null) && (localObject1 != null) && (((NetworkInfo)localObject1).isConnected()))
        {
          localObject1 = paramContext.getBSSID();
          if ((localObject1 != null) && (!((String)localObject1).equals("000000000000")) && (!((String)localObject1).equals("00-00-00-00-00-00")) && (!((String)localObject1).equals("00:00:00:00:00:00")))
          {
            int i = paramContext.getRssi();
            if ((i < -100) || (i > -20)) {
              break label241;
            }
            paramContext = paramContext.getSSID().replace("\"", "").replace("|", "");
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("{");
            ((StringBuilder)localObject2).append("\"mac\":\"");
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append("\",\"rssi\":");
            ((StringBuilder)localObject2).append(i);
            ((StringBuilder)localObject2).append(",\"ssid\":\"");
            ((StringBuilder)localObject2).append(paramContext);
            ((StringBuilder)localObject2).append("\"}");
            return ((StringBuilder)localObject2).toString();
          }
          return "{}";
        }
        return "{}";
      }
      return "{}";
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return "{}";
    label241:
    return "{}";
  }
  
  public static void b(String paramString)
  {
    long l1;
    if (paramString != null)
    {
      if (paramString.length() == 0) {
        return;
      }
      l1 = 0L;
    }
    File localFile;
    label116:
    for (Object localObject = null;; localObject = localFile)
    {
      try
      {
        File[] arrayOfFile = new File(paramString).listFiles();
        int j = arrayOfFile.length;
        int i = 0;
        for (paramString = (String)localObject; i < j; paramString = (String)localObject)
        {
          localFile = arrayOfFile[i];
          if (paramString == null) {
            break label116;
          }
          localObject = paramString;
          if (paramString.lastModified() > localFile.lastModified()) {
            break label116;
          }
          long l2 = localFile.length();
          i += 1;
          l1 += l2;
        }
        if ((l1 >= 104857600L) && (paramString != null)) {
          paramString.delete();
        }
        return;
      }
      catch (Throwable paramString)
      {
        return;
      }
      return;
    }
  }
  
  public static byte[] b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    DeflaterOutputStream localDeflaterOutputStream = new DeflaterOutputStream(localByteArrayOutputStream);
    try
    {
      localDeflaterOutputStream.write(paramArrayOfByte, 0, paramArrayOfByte.length);
      localDeflaterOutputStream.finish();
      localDeflaterOutputStream.flush();
      localDeflaterOutputStream.close();
      return localByteArrayOutputStream.toByteArray();
    }
    catch (Exception paramArrayOfByte) {}
    return null;
  }
  
  public static int c(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int i;
    if (paramContext == null) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return -1;
    }
    try
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if (paramContext != null)
      {
        i = paramContext.getType();
        return i;
      }
      return -1;
    }
    catch (Exception paramContext) {}
    return -1;
  }
  
  public static String c(String paramString)
  {
    if (paramString != null)
    {
      paramString = paramString.split(";");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String str = paramString[i].trim();
        int k = str.indexOf("charset=");
        if (-1 != k) {
          return str.substring(k + 8, str.length());
        }
        i += 1;
      }
    }
    return "GBK";
  }
  
  @Nullable
  public static byte[] c(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    InflaterInputStream localInflaterInputStream = new InflaterInputStream(localByteArrayInputStream);
    byte[] arrayOfByte2 = new byte['Ѐ'];
    paramArrayOfByte = new byte[0];
    int i = 0;
    try
    {
      int k;
      byte[] arrayOfByte1;
      do
      {
        k = localInflaterInputStream.read(arrayOfByte2);
        int j = i;
        arrayOfByte1 = paramArrayOfByte;
        if (k > 0)
        {
          j = i + k;
          arrayOfByte1 = new byte[j];
          System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, paramArrayOfByte.length);
          System.arraycopy(arrayOfByte2, 0, arrayOfByte1, paramArrayOfByte.length, k);
        }
        i = j;
        paramArrayOfByte = arrayOfByte1;
      } while (k > 0);
      return null;
    }
    catch (Exception paramArrayOfByte)
    {
      try
      {
        localByteArrayInputStream.close();
        localInflaterInputStream.close();
        return arrayOfByte1;
      }
      catch (IOException paramArrayOfByte) {}
      paramArrayOfByte = paramArrayOfByte;
      return null;
    }
  }
  
  public static void d(String paramString)
  {
    a("#", 4, paramString);
  }
  
  public static boolean d(Context paramContext)
  {
    Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int i;
    if (localObject == null) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return false;
    }
    try
    {
      paramContext = ((ConnectivityManager)localObject).getNetworkInfo(0);
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(1);
      boolean bool1;
      if (localObject != null)
      {
        boolean bool2 = ((NetworkInfo)localObject).isConnected();
        bool1 = bool2;
        if (!bool2)
        {
          bool1 = bool2;
          if (paramContext != null)
          {
            bool1 = paramContext.isConnected();
            return bool1;
          }
        }
      }
      else
      {
        bool1 = false;
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      Object localObject2 = paramContext.getPackageManager().getInstalledPackages(8192);
      Object localObject1 = "B2E6AD38C9EA55FC,8B51908CDE852ABE,6F350C90F0B8C2F7,18A9EF1D9AFDB74D,B0F7B644A29E1C82,2E0111E09CC76BBD,8FFA91856B5C9ED1,569EA369CE0CE6BE,490A7AD1901C1407,15A805FD6A78BF07,92DFF18D49ED5200,D80FF7253FEDC748,2044A6443164DDC8,488324C2DC64A6D5,E0EC01B2D80C2E57".split(",");
      localObject2 = ((List)localObject2).iterator();
      boolean bool;
      int i;
      PackageInfo localPackageInfo;
      int j;
      for (;;)
      {
        bool = ((Iterator)localObject2).hasNext();
        i = 0;
        if (!bool) {
          break;
        }
        localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        j = localObject1.length;
        while (i < j)
        {
          String str = localObject1[i];
          bool = h(localPackageInfo.packageName).equalsIgnoreCase(str);
          if (bool) {
            return 1;
          }
          i += 1;
        }
      }
      int k;
      int m;
      return 3;
    }
    catch (Throwable paramContext)
    {
      try
      {
        paramContext.getPackageManager();
        paramContext = "B2E6AD38C9EA55FC,8B51908CDE852ABE,6F350C90F0B8C2F7,18A9EF1D9AFDB74D,B0F7B644A29E1C82,2E0111E09CC76BBD,8FFA91856B5C9ED1,569EA369CE0CE6BE,490A7AD1901C1407,15A805FD6A78BF07,92DFF18D49ED5200,D80FF7253FEDC748,2044A6443164DDC8,488324C2DC64A6D5,E0EC01B2D80C2E57".split(",");
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(Environment.getExternalStorageDirectory().getAbsolutePath());
        ((StringBuilder)localObject1).append(File.separatorChar);
        ((StringBuilder)localObject1).append("Android");
        ((StringBuilder)localObject1).append(File.separatorChar);
        ((StringBuilder)localObject1).append("data");
        localObject1 = new File(((StringBuilder)localObject1).toString()).listFiles();
        k = localObject1.length;
        i = 0;
        while (i < k)
        {
          localObject2 = localObject1[i];
          m = paramContext.length;
          j = 0;
          while (j < m)
          {
            localPackageInfo = paramContext[j];
            bool = h(((File)localObject2).getName()).equalsIgnoreCase(localPackageInfo);
            if (bool) {
              return 1;
            }
            j += 1;
          }
          i += 1;
        }
        return 2;
      }
      catch (Throwable paramContext) {}
      paramContext = paramContext;
      return 3;
    }
  }
  
  public static String e(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString;
  }
  
  public static boolean f(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (paramString.contains("latitude")) {
        return true;
      }
      paramString = localJSONObject.optJSONArray("cells");
      if ((paramString != null) && (paramString.length() > 0)) {
        return true;
      }
      paramString = localJSONObject.optJSONArray("wifis");
      return (paramString != null) && (paramString.length() > 0);
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static String g(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      StringBuilder localStringBuilder = new StringBuilder();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(Integer.toHexString(localObject[i] & 0xFF));
        localStringBuilder.append("");
        i += 1;
      }
      localObject = localStringBuilder.toString();
      return localObject;
    }
    catch (Exception localException) {}
    return paramString;
  }
  
  private static String h(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      byte[] arrayOfByte = ((MessageDigest)localObject).digest();
      StringBuilder localStringBuilder = new StringBuilder();
      int j = arrayOfByte.length;
      int i = 0;
      while (i < j)
      {
        String str = Integer.toHexString(arrayOfByte[i] & 0xFF);
        localObject = str;
        if (str.length() != 2)
        {
          localObject = new StringBuilder("0");
          ((StringBuilder)localObject).append(str);
          localObject = ((StringBuilder)localObject).toString();
        }
        localStringBuilder.append((String)localObject);
        localStringBuilder.append("");
        i += 1;
      }
      localObject = localStringBuilder.toString().substring(8, 24);
      return localObject;
    }
    catch (Exception localException) {}
    return paramString;
  }
  
  public final int a()
  {
    return this.a.a;
  }
  
  public final String a(String paramString)
  {
    return this.a.a(paramString);
  }
  
  public final String b()
  {
    return this.a.b;
  }
  
  public final int c()
  {
    return this.a.c;
  }
  
  public final byte[] d()
  {
    return this.a.d;
  }
}
