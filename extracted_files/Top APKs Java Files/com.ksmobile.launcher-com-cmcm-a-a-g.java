package com.cmcm.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ksmobile.launcher.ds;
import com.ksmobile.launcher.dx;
import com.ksmobile.launcher.e.b;
import com.ksmobile.launcher.h;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class g
{
  private static g a = new g();
  private int b = 3;
  private int c = 0;
  
  private g() {}
  
  public static g a()
  {
    return a;
  }
  
  public static List<PackageInfo> a(Context paramContext)
  {
    return a(paramContext.getPackageManager(), 0);
  }
  
  private static List<PackageInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    Object localObject = null;
    try
    {
      paramPackageManager = paramPackageManager.getInstalledPackages(paramInt);
      localObject = paramPackageManager;
      if (paramPackageManager == null) {
        localObject = new ArrayList();
      }
      return localObject;
    }
    catch (Exception paramPackageManager)
    {
      for (;;)
      {
        paramPackageManager = (PackageManager)localObject;
      }
    }
  }
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    try
    {
      paramArrayOfByte2 = new SecretKeySpec(paramArrayOfByte2, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      localCipher.init(1, paramArrayOfByte2, new IvParameterSpec(paramArrayOfByte3));
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte1);
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1)
    {
      paramArrayOfByte1.printStackTrace();
    }
    return null;
  }
  
  public static List<h> b()
  {
    ArrayList localArrayList = new ArrayList();
    dx localDx = ds.a().g();
    if (localDx == null) {
      return localArrayList;
    }
    return localDx.r();
  }
  
  public static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    try
    {
      paramArrayOfByte2 = new SecretKeySpec(paramArrayOfByte2, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      localCipher.init(2, paramArrayOfByte2, new IvParameterSpec(paramArrayOfByte3));
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte1);
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1)
    {
      paramArrayOfByte1.printStackTrace();
    }
    return null;
  }
  
  protected static HttpClient c()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 1);
    ConnManagerParams.setTimeout(localBasicHttpParams, 5000L);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 5000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 5000);
    return new DefaultHttpClient(n.a(localBasicHttpParams), localBasicHttpParams);
  }
  
  public d a(Context paramContext, List<c> paramList)
  {
    boolean bool = true;
    localD = new d();
    Object localObject = paramList;
    if (paramList == null) {
      localObject = c.a(paramContext, null);
    }
    paramContext = e.h().a(4).a();
    try
    {
      paramList = c.a((List)localObject);
      if (paramList != null)
      {
        paramContext = c.a(a(null, paramContext.toASCIIString(), paramList, true));
        if (!TextUtils.isEmpty(paramContext))
        {
          localD.a(paramContext);
          paramContext = localD.a();
          if ((paramContext == null) || (paramContext.isEmpty())) {
            break label109;
          }
        }
      }
      for (;;)
      {
        localD.a(bool);
        return localD;
        this.c = 0;
        label109:
        bool = false;
      }
      return localD.a(false);
    }
    catch (Exception paramContext)
    {
      this.c = 0;
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  public java.io.InputStream a(HttpClient paramHttpClient, String paramString, org.apache.http.HttpEntity paramHttpEntity, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore 6
    //   3: aload_1
    //   4: ifnonnull +8 -> 12
    //   7: invokestatic 210	com/cmcm/a/a/g:c	()Lorg/apache/http/client/HttpClient;
    //   10: astore 6
    //   12: new 212	org/apache/http/client/methods/HttpPost
    //   15: dup
    //   16: aload_2
    //   17: invokespecial 215	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   20: astore_1
    //   21: aload_1
    //   22: ldc -39
    //   24: ldc -37
    //   26: invokevirtual 223	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   29: iload 4
    //   31: ifeq +11 -> 42
    //   34: aload_1
    //   35: ldc -31
    //   37: ldc -29
    //   39: invokevirtual 223	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   42: aload_1
    //   43: ldc -27
    //   45: ldc -25
    //   47: invokevirtual 223	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   50: aload_3
    //   51: ifnull +8 -> 59
    //   54: aload_1
    //   55: aload_3
    //   56: invokevirtual 235	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   59: aload 6
    //   61: aload_1
    //   62: invokeinterface 241 2 0
    //   67: astore_3
    //   68: aload_3
    //   69: invokeinterface 247 1 0
    //   74: invokeinterface 253 1 0
    //   79: istore 5
    //   81: iload 5
    //   83: sipush 200
    //   86: if_icmpeq +16 -> 102
    //   89: aload_0
    //   90: iconst_3
    //   91: putfield 20	com/cmcm/a/a/g:b	I
    //   94: aload_0
    //   95: iload 5
    //   97: putfield 22	com/cmcm/a/a/g:c	I
    //   100: aconst_null
    //   101: areturn
    //   102: aload_0
    //   103: sipush 200
    //   106: putfield 22	com/cmcm/a/a/g:c	I
    //   109: aload_3
    //   110: invokeinterface 257 1 0
    //   115: invokeinterface 263 1 0
    //   120: astore_2
    //   121: aload_3
    //   122: ldc_w 265
    //   125: invokeinterface 269 2 0
    //   130: astore_3
    //   131: aload_3
    //   132: ifnull +99 -> 231
    //   135: aload_3
    //   136: invokeinterface 274 1 0
    //   141: ldc -29
    //   143: invokevirtual 280	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   146: ifeq +85 -> 231
    //   149: new 282	java/util/zip/GZIPInputStream
    //   152: dup
    //   153: new 284	java/io/BufferedInputStream
    //   156: dup
    //   157: aload_2
    //   158: invokespecial 287	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   161: invokespecial 288	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   164: astore_2
    //   165: aload_2
    //   166: areturn
    //   167: astore_1
    //   168: aconst_null
    //   169: areturn
    //   170: astore_1
    //   171: aload_0
    //   172: iconst_2
    //   173: putfield 20	com/cmcm/a/a/g:b	I
    //   176: goto -8 -> 168
    //   179: astore_1
    //   180: aload_1
    //   181: athrow
    //   182: astore_2
    //   183: aload_0
    //   184: iconst_1
    //   185: putfield 20	com/cmcm/a/a/g:b	I
    //   188: aload_1
    //   189: invokevirtual 291	org/apache/http/client/methods/HttpPost:abort	()V
    //   192: aload_2
    //   193: invokevirtual 292	java/io/IOException:printStackTrace	()V
    //   196: goto -28 -> 168
    //   199: astore_1
    //   200: goto -32 -> 168
    //   203: astore_1
    //   204: goto -36 -> 168
    //   207: astore_1
    //   208: goto -40 -> 168
    //   211: astore_1
    //   212: goto -44 -> 168
    //   215: astore_1
    //   216: goto -48 -> 168
    //   219: astore_1
    //   220: goto -52 -> 168
    //   223: astore_1
    //   224: goto -56 -> 168
    //   227: astore_1
    //   228: goto -60 -> 168
    //   231: aload_2
    //   232: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	233	0	this	g
    //   0	233	1	paramHttpClient	HttpClient
    //   0	233	2	paramString	String
    //   0	233	3	paramHttpEntity	org.apache.http.HttpEntity
    //   0	233	4	paramBoolean	boolean
    //   79	17	5	i	int
    //   1	59	6	localHttpClient	HttpClient
    // Exception table:
    //   from	to	target	type
    //   54	59	167	org/apache/http/client/ClientProtocolException
    //   59	81	167	org/apache/http/client/ClientProtocolException
    //   89	100	167	org/apache/http/client/ClientProtocolException
    //   102	131	167	org/apache/http/client/ClientProtocolException
    //   135	165	167	org/apache/http/client/ClientProtocolException
    //   54	59	170	org/apache/http/conn/ConnectTimeoutException
    //   59	81	170	org/apache/http/conn/ConnectTimeoutException
    //   89	100	170	org/apache/http/conn/ConnectTimeoutException
    //   102	131	170	org/apache/http/conn/ConnectTimeoutException
    //   135	165	170	org/apache/http/conn/ConnectTimeoutException
    //   54	59	179	finally
    //   59	81	179	finally
    //   89	100	179	finally
    //   102	131	179	finally
    //   135	165	179	finally
    //   171	176	179	finally
    //   183	196	179	finally
    //   54	59	182	java/io/IOException
    //   59	81	182	java/io/IOException
    //   89	100	182	java/io/IOException
    //   102	131	182	java/io/IOException
    //   135	165	182	java/io/IOException
    //   54	59	199	java/lang/Exception
    //   59	81	199	java/lang/Exception
    //   89	100	199	java/lang/Exception
    //   102	131	199	java/lang/Exception
    //   135	165	199	java/lang/Exception
    //   54	59	203	java/lang/NullPointerException
    //   59	81	203	java/lang/NullPointerException
    //   89	100	203	java/lang/NullPointerException
    //   102	131	203	java/lang/NullPointerException
    //   135	165	203	java/lang/NullPointerException
    //   54	59	207	org/apache/http/NoHttpResponseException
    //   59	81	207	org/apache/http/NoHttpResponseException
    //   89	100	207	org/apache/http/NoHttpResponseException
    //   102	131	207	org/apache/http/NoHttpResponseException
    //   135	165	207	org/apache/http/NoHttpResponseException
    //   54	59	211	org/apache/http/conn/ConnectionPoolTimeoutException
    //   59	81	211	org/apache/http/conn/ConnectionPoolTimeoutException
    //   89	100	211	org/apache/http/conn/ConnectionPoolTimeoutException
    //   102	131	211	org/apache/http/conn/ConnectionPoolTimeoutException
    //   135	165	211	org/apache/http/conn/ConnectionPoolTimeoutException
    //   54	59	215	java/net/MalformedURLException
    //   59	81	215	java/net/MalformedURLException
    //   89	100	215	java/net/MalformedURLException
    //   102	131	215	java/net/MalformedURLException
    //   135	165	215	java/net/MalformedURLException
    //   54	59	219	java/net/SocketException
    //   59	81	219	java/net/SocketException
    //   89	100	219	java/net/SocketException
    //   102	131	219	java/net/SocketException
    //   135	165	219	java/net/SocketException
    //   54	59	223	java/net/ConnectException
    //   59	81	223	java/net/ConnectException
    //   89	100	223	java/net/ConnectException
    //   102	131	223	java/net/ConnectException
    //   135	165	223	java/net/ConnectException
    //   54	59	227	java/net/SocketTimeoutException
    //   59	81	227	java/net/SocketTimeoutException
    //   89	100	227	java/net/SocketTimeoutException
    //   102	131	227	java/net/SocketTimeoutException
    //   135	165	227	java/net/SocketTimeoutException
  }
  
  public String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = b.a(paramString1);
      if (paramString1 == null) {
        return null;
      }
      OutputStream localOutputStream = paramString1.getOutputStream();
      localOutputStream.write(b.b(paramString2));
      localOutputStream.flush();
      localOutputStream.close();
      this.c = paramString1.getResponseCode();
      if (this.c == 200) {
        return b.a(paramString1.getInputStream());
      }
      this.b = 3;
      com.ksmobile.launcher.e.c.a().b("ResponseCode = " + String.valueOf(paramString1.getResponseCode()));
      return null;
    }
    catch (UnknownServiceException paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("UnknownServiceException");
      return null;
    }
    catch (SocketTimeoutException paramString1)
    {
      this.b = 2;
      com.ksmobile.launcher.e.c.a().b("SocketTimeoutException");
      return null;
    }
    catch (ConnectException paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("ConnectException");
      return null;
    }
    catch (SocketException paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("SocketException");
      return null;
    }
    catch (ConnectionPoolTimeoutException paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("ConnectionPoolTimeoutException");
      return null;
    }
    catch (ConnectTimeoutException paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("ConnectTimeoutException");
      return null;
    }
    catch (NoHttpResponseException paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("NoHttpResponseException");
      return null;
    }
    catch (IOException paramString1)
    {
      this.b = 1;
      return null;
    }
    catch (Exception paramString1)
    {
      com.ksmobile.launcher.e.c.a().b("exception:" + paramString1.getMessage());
      return null;
    }
    catch (ClientProtocolException paramString1)
    {
      return null;
    }
    catch (NullPointerException paramString1)
    {
      return null;
    }
    catch (IllegalAccessError paramString1)
    {
      return null;
    }
    catch (IllegalStateException paramString1)
    {
      return null;
    }
    catch (MalformedURLException paramString1) {}
    return null;
  }
  
  public void a(int paramInt)
  {
    this.c = paramInt;
  }
  
  public d b(Context paramContext, List<c> paramList)
  {
    localP = new p();
    Object localObject = paramList;
    if (paramList == null) {
      localObject = c.a(paramContext, null);
    }
    paramContext = new o().a(4).a().toASCIIString();
    try
    {
      paramList = c.c((List)localObject);
      if (paramList != null)
      {
        paramContext = a(paramContext, paramList);
        if (!TextUtils.isEmpty(paramContext))
        {
          localP.a(paramContext);
          paramContext = localP.a();
          if ((paramContext == null) || (paramContext.isEmpty())) {
            break label108;
          }
        }
      }
      label108:
      for (boolean bool = true;; bool = false)
      {
        localP.a(bool);
        return localP;
        this.c = 0;
      }
      return localP.a(false);
    }
    catch (Exception paramContext)
    {
      this.c = 0;
      paramContext.printStackTrace();
    }
  }
  
  public void b(Context paramContext)
  {
    i.a().a(paramContext);
    f.a(paramContext);
  }
  
  public int d()
  {
    return this.b;
  }
  
  public int e()
  {
    return this.c;
  }
}
