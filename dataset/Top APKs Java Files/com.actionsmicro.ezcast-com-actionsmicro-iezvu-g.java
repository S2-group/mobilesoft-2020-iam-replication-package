package com.actionsmicro.iezvu;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.actionsmicro.androidkit.ezcast.DeviceInfo;
import com.actionsmicro.g.c;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

public class g
{
  private static final byte[] a = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  
  public static int a(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString2, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext)) {
      return paramContext.getInt(paramString1, paramInt);
    }
  }
  
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString2, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext)) {
      return paramContext.getInt(paramString1, 1);
    }
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String a(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private static String a(String paramString1, String paramString2, int paramInt)
  {
    paramString1 = Pattern.compile(paramString2, 16).split(paramString1);
    paramString2 = new StringBuilder();
    int j = paramString1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString1[i];
      paramString2.append(String.format("%" + paramInt + 's', new Object[] { localObject }));
      i += 1;
    }
    return paramString2.toString();
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = Base64.encodeToString(a(paramString2.getBytes("UTF-8"), paramString1.getBytes("UTF-8"), paramString3), 2);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static String a(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject != null) {
      return b(paramJSONObject.toString(), paramString);
    }
    return null;
  }
  
  public static List<ApplicationInfo> a(PackageManager paramPackageManager)
  {
    return paramPackageManager.getInstalledApplications(0);
  }
  
  /* Error */
  public static HttpResponse a(java.net.URL paramURL, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: ldc -108
    //   2: new 91	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   9: ldc -106
    //   11: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_0
    //   15: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   18: ldc -101
    //   20: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_1
    //   24: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokestatic 160	com/actionsmicro/g/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   33: aload_0
    //   34: invokevirtual 165	java/net/URL:getHost	()Ljava/lang/String;
    //   37: ifnull +14 -> 51
    //   40: aload_0
    //   41: invokevirtual 165	java/net/URL:getHost	()Ljava/lang/String;
    //   44: ldc2_w 166
    //   47: invokestatic 172	com/actionsmicro/g/i:a	(Ljava/lang/String;J)Ljava/net/InetAddress;
    //   50: pop
    //   51: new 174	java/net/URI
    //   54: dup
    //   55: new 91	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   62: aload_1
    //   63: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc -80
    //   68: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokespecial 179	java/net/URI:<init>	(Ljava/lang/String;)V
    //   77: astore_3
    //   78: aload_0
    //   79: invokevirtual 183	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   82: astore_2
    //   83: aload_2
    //   84: sipush 1000
    //   87: invokevirtual 189	java/net/URLConnection:setConnectTimeout	(I)V
    //   90: aload_2
    //   91: sipush 1000
    //   94: invokevirtual 192	java/net/URLConnection:setReadTimeout	(I)V
    //   97: aload_2
    //   98: invokevirtual 196	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   101: astore_2
    //   102: new 198	org/apache/http/params/BasicHttpParams
    //   105: dup
    //   106: invokespecial 199	org/apache/http/params/BasicHttpParams:<init>	()V
    //   109: astore 4
    //   111: aload 4
    //   113: sipush 1000
    //   116: invokestatic 205	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   119: aload 4
    //   121: sipush 1000
    //   124: invokestatic 208	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   127: new 210	org/apache/http/impl/client/DefaultHttpClient
    //   130: dup
    //   131: aload 4
    //   133: invokespecial 213	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   136: astore 4
    //   138: new 215	org/apache/http/client/methods/HttpPost
    //   141: dup
    //   142: aload_3
    //   143: invokespecial 218	org/apache/http/client/methods/HttpPost:<init>	(Ljava/net/URI;)V
    //   146: astore_3
    //   147: new 220	org/apache/http/entity/mime/MultipartEntity
    //   150: dup
    //   151: getstatic 226	org/apache/http/entity/mime/HttpMultipartMode:BROWSER_COMPATIBLE	Lorg/apache/http/entity/mime/HttpMultipartMode;
    //   154: invokespecial 229	org/apache/http/entity/mime/MultipartEntity:<init>	(Lorg/apache/http/entity/mime/HttpMultipartMode;)V
    //   157: astore 5
    //   159: aload 5
    //   161: ldc -25
    //   163: new 233	com/actionsmicro/ezdisplay/activity/b
    //   166: dup
    //   167: aload_2
    //   168: ldc -22
    //   170: ldc -20
    //   172: invokespecial 239	com/actionsmicro/ezdisplay/activity/b:<init>	(Ljava/io/InputStream;ILjava/lang/String;)V
    //   175: invokevirtual 243	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   178: aload_3
    //   179: aload 5
    //   181: invokevirtual 247	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   184: aload 4
    //   186: aload_3
    //   187: invokeinterface 253 2 0
    //   192: astore_2
    //   193: ldc -108
    //   195: new 91	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   202: ldc -1
    //   204: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_0
    //   208: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   211: ldc -101
    //   213: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: aload_1
    //   217: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: ldc_w 257
    //   223: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 160	com/actionsmicro/g/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: aload_2
    //   233: areturn
    //   234: astore_0
    //   235: aconst_null
    //   236: astore_2
    //   237: ldc -108
    //   239: new 91	java/lang/StringBuilder
    //   242: dup
    //   243: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   246: ldc_w 259
    //   249: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: aload_0
    //   253: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   256: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   259: invokestatic 160	com/actionsmicro/g/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   262: aload_0
    //   263: invokevirtual 260	java/lang/Throwable:printStackTrace	()V
    //   266: aload_2
    //   267: areturn
    //   268: astore_0
    //   269: goto -32 -> 237
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	272	0	paramURL	java.net.URL
    //   0	272	1	paramString	String
    //   82	185	2	localObject1	Object
    //   77	110	3	localObject2	Object
    //   109	76	4	localObject3	Object
    //   157	23	5	localMultipartEntity	org.apache.http.entity.mime.MultipartEntity
    // Exception table:
    //   from	to	target	type
    //   0	51	234	java/lang/Throwable
    //   51	193	234	java/lang/Throwable
    //   193	232	268	java/lang/Throwable
  }
  
  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString2, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext))
    {
      paramContext.edit();
      paramContext = paramContext.edit();
      paramContext.putInt(paramString1, paramInt);
      paramContext.commit();
      return;
    }
  }
  
  public static void a(Context paramContext, Long paramLong, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString2, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext))
    {
      paramContext.edit();
      paramContext = paramContext.edit();
      paramContext.putLong(paramString1, paramLong.longValue());
      paramContext.commit();
      return;
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if ((paramString3 != null) && (!paramString3.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString3, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext))
    {
      paramContext.edit();
      paramContext = paramContext.edit();
      paramContext.putString(paramString2, paramString1);
      paramContext.commit();
      return;
    }
  }
  
  public static void a(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse != null) {}
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramHttpResponse.getEntity().getContent(), "UTF-8"));
      String str;
      for (paramHttpResponse = new StringBuilder();; paramHttpResponse = paramHttpResponse.append(str))
      {
        str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
      }
      com.actionsmicro.g.g.a("dumpHttpResponse", "Response: " + paramHttpResponse);
      return;
    }
    catch (UnsupportedEncodingException paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
      return;
    }
    catch (IllegalStateException paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
      return;
    }
    catch (IOException paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
    }
  }
  
  public static boolean a()
  {
    return Build.MANUFACTURER.toLowerCase().equalsIgnoreCase("amazon");
  }
  
  public static boolean a(DeviceInfo paramDeviceInfo)
  {
    return (paramDeviceInfo.getIpAddress() != null) && (paramDeviceInfo.getIpAddress().getHostAddress() != null) && ((paramDeviceInfo.getIpAddress().getHostAddress().equals("192.168.111.1")) || (paramDeviceInfo.getIpAddress().getHostAddress().equals("192.168.203.1")) || (paramDeviceInfo.getIpAddress().getHostAddress().equals("192.168.168.1")));
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    return c(paramString2).compareTo(c(paramString1)) < 0;
  }
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return a(paramArrayOfByte1, paramArrayOfByte2, "AES/ECB/PKCS5Padding");
  }
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString)
  {
    if (paramString.equals("AES/ECB/PKCS5Padding")) {
      return b(paramArrayOfByte1, paramArrayOfByte2);
    }
    if (paramString.equals("AES/CBC/PKCS5Padding")) {
      return c(paramArrayOfByte1, paramArrayOfByte2);
    }
    return null;
  }
  
  public static Long b(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString2, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext)) {
      return Long.valueOf(paramContext.getLong(paramString1, 0L));
    }
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      String str = paramContext.getSimCountryIso();
      if ((str != null) && (str.length() == 2)) {
        return str.toLowerCase(Locale.US);
      }
      if (paramContext.getPhoneType() != 2)
      {
        paramContext = paramContext.getNetworkCountryIso();
        if ((paramContext != null) && (paramContext.length() == 2))
        {
          paramContext = paramContext.toLowerCase(Locale.US);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String b(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Base64.encodeToString(a(paramString2.getBytes("UTF-8"), paramString1.getBytes("UTF-8")), 0);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static String b(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = new String(b(paramString2.getBytes("UTF-8"), paramString1.getBytes("UTF-8"), paramString3));
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static InetAddress b(String paramString)
  {
    int i = d(paramString);
    paramString = ByteBuffer.allocate(4);
    paramString.order(ByteOrder.LITTLE_ENDIAN);
    paramString.putInt(i);
    paramString.position(0);
    try
    {
      paramString = InetAddress.getByAddress(paramString.array());
      return paramString;
    }
    catch (UnknownHostException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean b(DeviceInfo paramDeviceInfo)
  {
    return paramDeviceInfo.getIpAddress().getHostAddress().equals("192.168.203.1");
  }
  
  private static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(1, paramArrayOfByte1);
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1) {}
    return null;
  }
  
  public static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString)
  {
    if (paramString.equals("AES/ECB/PKCS5Padding")) {
      return d(paramArrayOfByte1, paramArrayOfByte2);
    }
    if (paramString.equals("AES/CBC/PKCS5Padding")) {
      return e(paramArrayOfByte1, paramArrayOfByte2);
    }
    return null;
  }
  
  public static String c(Context paramContext)
  {
    if (c.a(paramContext)) {
      return "tb";
    }
    return "ph";
  }
  
  public static String c(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty())) {}
    for (paramContext = paramContext.getSharedPreferences(paramString2, 0);; paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext)) {
      return paramContext.getString(paramString1, "");
    }
  }
  
  private static String c(String paramString)
  {
    return a(paramString, ".", 4);
  }
  
  public static boolean c(DeviceInfo paramDeviceInfo)
  {
    return paramDeviceInfo.getIpAddress().getHostAddress().equals("192.168.168.1");
  }
  
  private static byte[] c(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
      paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(1, paramArrayOfByte1, localIvParameterSpec);
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1) {}
    return null;
  }
  
  private static int d(String paramString)
  {
    int i = 0;
    if (paramString.length() != 7) {
      return 0;
    }
    int j = 0;
    while (i < paramString.length())
    {
      j = j * 26 + paramString.charAt(6 - i) - 97;
      i += 1;
    }
    return j;
  }
  
  public static String d(Context paramContext)
  {
    if (((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected())
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if ((paramContext != null) && (!TextUtils.isEmpty(paramContext.getSSID()))) {
        return paramContext.getSSID();
      }
    }
    return null;
  }
  
  public static void d(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, a(paramContext, paramString1, 0, paramString2) + 1, paramString1, paramString2);
  }
  
  private static byte[] d(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      paramArrayOfByte2 = Base64.decode(paramArrayOfByte2, 0);
      paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(2, paramArrayOfByte1);
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1) {}
    return null;
  }
  
  private static byte[] e(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      paramArrayOfByte2 = Base64.decode(paramArrayOfByte2, 0);
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
      paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(2, paramArrayOfByte1, localIvParameterSpec);
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1) {}
    return null;
  }
}
