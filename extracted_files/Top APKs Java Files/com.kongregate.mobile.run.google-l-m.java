package l;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class m
{
  private final SecretKey a;
  private final Context b;
  private final String c;
  private final Map<String, String> d;
  private final Object e = new Object();
  
  private m(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Context is null");
    }
    if (o.a(paramString)) {
      throw new IllegalArgumentException("filename can't be empty");
    }
    this.b = paramContext;
    Object localObject = a(paramContext);
    paramContext = ((SharedPreferences)localObject).getString("secret", null);
    localObject = ((SharedPreferences)localObject).getString("file_postfix", null);
    this.c = (paramString + (String)localObject);
    if ((!o.a(paramContext)) && (!o.a((CharSequence)localObject)))
    {
      this.a = new SecretKeySpec(Base64.decode(paramContext, 0), "AES");
      g.b("shared filename is: " + this.c);
      this.d = e();
      return;
    }
    this.a = null;
    this.d = new HashMap(0);
    g.d("unable to obtain a secret key or shared file location");
  }
  
  private static SharedPreferences a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("kongregate_shared_secret", 0);
    Object localObject2 = localSharedPreferences.getString("secret", null);
    Object localObject1 = localSharedPreferences.getString("file_postfix", null);
    if ((!o.a((CharSequence)localObject2)) && (!o.a((CharSequence)localObject1)))
    {
      g.b("found secret in prefs");
      return localSharedPreferences;
    }
    g.b("check providers for secret");
    Object localObject3 = paramContext.getPackageManager();
    Object localObject5;
    label276:
    Object localObject4;
    if (localObject3 != null)
    {
      Iterator localIterator = ((PackageManager)localObject3).getInstalledPackages(8).iterator();
      localObject3 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject3;
      localObject3 = localObject2;
      localObject5 = localObject1;
      if (!localIterator.hasNext()) {
        break label421;
      }
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)localIterator.next()).providers;
      if (arrayOfProviderInfo == null) {
        break label541;
      }
      int j = arrayOfProviderInfo.length;
      int i = 0;
      while (i < j)
      {
        localObject3 = arrayOfProviderInfo[i];
        if ("com.kongregate.permission.ReadSharedData2".equals(((ProviderInfo)localObject3).readPermission))
        {
          g.b("checking authority: " + ((ProviderInfo)localObject3).authority);
          try
          {
            localObject3 = paramContext.getContentResolver().query(Uri.parse("content://" + ((ProviderInfo)localObject3).authority + "/SharedSecret"), null, null, null, null);
            if (localObject3 == null) {}
          }
          catch (SecurityException localSecurityException)
          {
            for (;;)
            {
              try
              {
                if (!((Cursor)localObject3).moveToFirst()) {
                  break label528;
                }
                localObject2 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("secret"));
                localObject1 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("file_postfix"));
                if (!((Cursor)localObject3).isClosed()) {
                  ((Cursor)localObject3).close();
                }
                localObject5 = localObject1;
                localObject3 = localObject2;
                if (o.a((CharSequence)localObject2)) {
                  break label372;
                }
                localObject5 = localObject1;
                return a(localSharedPreferences, (String)localObject2, (String)localObject1);
              }
              finally
              {
                if (localObject4.isClosed()) {
                  continue;
                }
                localObject4.close();
              }
              localSecurityException = localSecurityException;
              g.c("Permission denied");
              localObject4 = null;
            }
          }
        }
        label372:
        i += 1;
        localObject2 = localObject5;
        localObject1 = localObject4;
      }
      localObject4 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject4;
    }
    for (;;)
    {
      localObject4 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject4;
      break;
      g.d("PackageManager not present");
      localObject5 = localObject2;
      localObject4 = localObject1;
      label421:
      if (!o.a((CharSequence)localObject5))
      {
        paramContext = localObject4;
        if (!o.a(localObject4)) {
          break label509;
        }
      }
      try
      {
        paramContext = KeyGenerator.getInstance("AES");
        paramContext.init(128);
        localObject5 = Base64.encodeToString(paramContext.generateKey().getEncoded(), 0);
        paramContext = Integer.toString(new Random().nextInt(Integer.MAX_VALUE)) + ".shared";
        g.b("generated a secret");
        label509:
        return a(localSharedPreferences, (String)localObject5, paramContext);
      }
      catch (NoSuchAlgorithmException paramContext)
      {
        g.d("unable to find or generate key");
        return localSharedPreferences;
      }
      label528:
      localObject5 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject5;
      break label276;
      label541:
      localObject4 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject4;
    }
  }
  
  private static SharedPreferences a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences.edit().putString("secret", paramString1).putString("file_postfix", paramString2).commit();
    return paramSharedPreferences;
  }
  
  private File a(File paramFile)
  {
    return new File(paramFile + "/" + this.c);
  }
  
  private Cipher a(int paramInt, byte[] paramArrayOfByte)
  {
    if (this.a == null) {
      throw new IllegalStateException("no secret key.");
    }
    paramArrayOfByte = new IvParameterSpec(paramArrayOfByte);
    try
    {
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(paramInt, this.a, paramArrayOfByte);
      return localCipher;
    }
    catch (GeneralSecurityException paramArrayOfByte)
    {
      g.c("unable to encrypt json", paramArrayOfByte);
    }
    return null;
  }
  
  public static m a(Context paramContext, final String paramString)
  {
    paramContext = c.d.a(new Callable() {});
    try
    {
      paramContext = (m)paramContext.get();
      return paramContext;
    }
    catch (InterruptedException paramContext)
    {
      g.d("failed to get shared data store: ", paramContext);
      return null;
    }
    catch (ExecutionException paramContext)
    {
      g.d("failed to get shared data store: ", paramContext);
    }
    return null;
  }
  
  /* Error */
  private static boolean a(File paramFile, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    // Byte code:
    //   0: new 378	java/io/BufferedOutputStream
    //   3: dup
    //   4: new 380	java/io/FileOutputStream
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 383	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   12: invokespecial 386	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   15: astore_3
    //   16: aload_3
    //   17: astore_0
    //   18: aload_3
    //   19: aload_2
    //   20: invokevirtual 389	java/io/BufferedOutputStream:write	([B)V
    //   23: aload_3
    //   24: astore_0
    //   25: aload_3
    //   26: aload_1
    //   27: invokevirtual 389	java/io/BufferedOutputStream:write	([B)V
    //   30: aload_3
    //   31: astore_0
    //   32: aload_3
    //   33: invokevirtual 392	java/io/BufferedOutputStream:flush	()V
    //   36: aload_3
    //   37: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   40: iconst_1
    //   41: ireturn
    //   42: astore_2
    //   43: aconst_null
    //   44: astore_1
    //   45: aload_1
    //   46: astore_0
    //   47: ldc_w 399
    //   50: aload_2
    //   51: invokestatic 320	l/g:c	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   54: aload_1
    //   55: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   58: iconst_0
    //   59: ireturn
    //   60: astore_1
    //   61: aconst_null
    //   62: astore_0
    //   63: aload_0
    //   64: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   67: aload_1
    //   68: athrow
    //   69: astore_1
    //   70: goto -7 -> 63
    //   73: astore_2
    //   74: aload_3
    //   75: astore_1
    //   76: goto -31 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	paramFile	File
    //   0	79	1	paramArrayOfByte1	byte[]
    //   0	79	2	paramArrayOfByte2	byte[]
    //   15	60	3	localBufferedOutputStream	java.io.BufferedOutputStream
    // Exception table:
    //   from	to	target	type
    //   0	16	42	java/io/IOException
    //   0	16	60	finally
    //   18	23	69	finally
    //   25	30	69	finally
    //   32	36	69	finally
    //   47	54	69	finally
    //   18	23	73	java/io/IOException
    //   25	30	73	java/io/IOException
    //   32	36	73	java/io/IOException
  }
  
  @TargetApi(9)
  private static File b(ApplicationInfo paramApplicationInfo)
  {
    paramApplicationInfo = new File(paramApplicationInfo.dataDir + "/kongregate_shared_datastore");
    if (!paramApplicationInfo.exists())
    {
      paramApplicationInfo.mkdirs();
      if (a.a(9))
      {
        paramApplicationInfo.setWritable(true, false);
        paramApplicationInfo.setReadable(true, false);
        paramApplicationInfo.setExecutable(true, false);
      }
    }
    else
    {
      return paramApplicationInfo;
    }
    g.c("app dir may not be shareable");
    return paramApplicationInfo;
  }
  
  /* Error */
  private Map<String, String> b(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: new 57	java/lang/StringBuilder
    //   9: dup
    //   10: ldc_w 428
    //   13: invokespecial 86	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   16: aload_1
    //   17: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokestatic 90	l/g:b	(Ljava/lang/String;)V
    //   26: new 97	java/util/HashMap
    //   29: dup
    //   30: invokespecial 429	java/util/HashMap:<init>	()V
    //   33: astore 6
    //   35: new 431	java/io/BufferedInputStream
    //   38: dup
    //   39: new 433	java/io/FileInputStream
    //   42: dup
    //   43: aload_1
    //   44: invokespecial 434	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   47: invokespecial 437	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   50: astore_3
    //   51: bipush 16
    //   53: newarray byte
    //   55: astore 7
    //   57: aload_3
    //   58: aload 7
    //   60: iconst_0
    //   61: bipush 16
    //   63: invokevirtual 441	java/io/BufferedInputStream:read	([BII)I
    //   66: bipush 16
    //   68: if_icmpeq +20 -> 88
    //   71: ldc_w 443
    //   74: invokestatic 219	l/g:c	(Ljava/lang/String;)V
    //   77: aconst_null
    //   78: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   81: aload_3
    //   82: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   85: aload 6
    //   87: areturn
    //   88: new 445	java/io/ByteArrayOutputStream
    //   91: dup
    //   92: invokespecial 446	java/io/ByteArrayOutputStream:<init>	()V
    //   95: astore_1
    //   96: new 448	javax/crypto/CipherInputStream
    //   99: dup
    //   100: aload_3
    //   101: aload_0
    //   102: iconst_2
    //   103: aload 7
    //   105: invokespecial 450	l/m:a	(I[B)Ljavax/crypto/Cipher;
    //   108: invokespecial 453	javax/crypto/CipherInputStream:<init>	(Ljava/io/InputStream;Ljavax/crypto/Cipher;)V
    //   111: astore 4
    //   113: aload 4
    //   115: invokevirtual 456	javax/crypto/CipherInputStream:read	()I
    //   118: istore_2
    //   119: iload_2
    //   120: iflt +44 -> 164
    //   123: aload_1
    //   124: iload_2
    //   125: invokevirtual 458	java/io/ByteArrayOutputStream:write	(I)V
    //   128: goto -15 -> 113
    //   131: astore 5
    //   133: aload_1
    //   134: astore 4
    //   136: aload_3
    //   137: astore_1
    //   138: aload 4
    //   140: astore_3
    //   141: aload 5
    //   143: astore 4
    //   145: ldc_w 460
    //   148: aload 4
    //   150: invokestatic 320	l/g:c	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   153: aload_3
    //   154: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   157: aload_1
    //   158: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   161: aload 6
    //   163: areturn
    //   164: aload_1
    //   165: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   168: aload_3
    //   169: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   172: aload_1
    //   173: invokevirtual 461	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   176: invokestatic 466	l/f:c	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   179: astore_1
    //   180: aload_1
    //   181: ifnull +59 -> 240
    //   184: aload_1
    //   185: invokevirtual 471	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   188: astore_3
    //   189: aload_3
    //   190: invokeinterface 146 1 0
    //   195: ifeq +45 -> 240
    //   198: aload_3
    //   199: invokeinterface 150 1 0
    //   204: checkcast 165	java/lang/String
    //   207: astore 4
    //   209: aload 6
    //   211: aload 4
    //   213: aload_1
    //   214: aload 4
    //   216: invokestatic 474	l/f:c	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   219: invokevirtual 478	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   222: pop
    //   223: goto -34 -> 189
    //   226: astore_1
    //   227: aconst_null
    //   228: astore_3
    //   229: aload 4
    //   231: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   234: aload_3
    //   235: invokestatic 397	l/d:a	(Ljava/io/Closeable;)V
    //   238: aload_1
    //   239: athrow
    //   240: aload 6
    //   242: areturn
    //   243: astore_1
    //   244: goto -15 -> 229
    //   247: astore 5
    //   249: aload_1
    //   250: astore 4
    //   252: aload 5
    //   254: astore_1
    //   255: goto -26 -> 229
    //   258: astore 4
    //   260: aload_1
    //   261: astore 5
    //   263: aload 4
    //   265: astore_1
    //   266: aload_3
    //   267: astore 4
    //   269: aload 5
    //   271: astore_3
    //   272: goto -43 -> 229
    //   275: astore 4
    //   277: aconst_null
    //   278: astore_1
    //   279: aload 5
    //   281: astore_3
    //   282: goto -137 -> 145
    //   285: astore 4
    //   287: aload_3
    //   288: astore_1
    //   289: aload 5
    //   291: astore_3
    //   292: goto -147 -> 145
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	295	0	this	m
    //   0	295	1	paramFile	File
    //   118	7	2	i	int
    //   50	242	3	localObject1	Object
    //   1	250	4	localObject2	Object
    //   258	6	4	localObject3	Object
    //   267	1	4	localObject4	Object
    //   275	1	4	localIOException1	IOException
    //   285	1	4	localIOException2	IOException
    //   4	1	5	localObject5	Object
    //   131	11	5	localIOException3	IOException
    //   247	6	5	localObject6	Object
    //   261	29	5	localFile	File
    //   33	208	6	localHashMap	HashMap
    //   55	49	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   96	113	131	java/io/IOException
    //   113	119	131	java/io/IOException
    //   123	128	131	java/io/IOException
    //   35	51	226	finally
    //   51	77	243	finally
    //   88	96	243	finally
    //   96	113	247	finally
    //   113	119	247	finally
    //   123	128	247	finally
    //   145	153	258	finally
    //   35	51	275	java/io/IOException
    //   51	77	285	java/io/IOException
    //   88	96	285	java/io/IOException
  }
  
  private static byte[] b(String paramString, Cipher paramCipher)
  {
    paramCipher = new CipherInputStream(new ByteArrayInputStream(paramString.getBytes()), paramCipher);
    paramString = new ByteArrayOutputStream();
    try
    {
      for (;;)
      {
        int i = paramCipher.read();
        if (i < 0) {
          break;
        }
        paramString.write(i);
      }
      return paramString.toByteArray();
    }
    catch (IOException paramCipher)
    {
      g.c("unable to encrypt", paramCipher);
    }
  }
  
  private File c(ApplicationInfo paramApplicationInfo)
  {
    return a(b(paramApplicationInfo));
  }
  
  private static File d()
  {
    File localFile = new File(Environment.getExternalStorageDirectory() + "/.kongregate/data");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return localFile;
  }
  
  private Map<String, String> e()
  {
    HashMap localHashMap = new HashMap(0);
    Object localObject = this.b.getApplicationInfo();
    if (localObject == null)
    {
      g.c("SharedDataStore - unable to load shared data. no app info");
      return localHashMap;
    }
    if (d.b(this.b))
    {
      localObject = a(d());
      Iterator localIterator = f().iterator();
      label58:
      if (!localIterator.hasNext()) {
        break label116;
      }
      File localFile = c((ApplicationInfo)localIterator.next());
      if ((!localFile.exists()) || (localFile.lastModified() <= ((File)localObject).lastModified())) {
        break label142;
      }
      localObject = localFile;
    }
    label116:
    label142:
    for (;;)
    {
      break label58;
      localObject = c((ApplicationInfo)localObject);
      break;
      if (((File)localObject).exists()) {}
      for (localObject = b((File)localObject);; localObject = localHashMap)
      {
        return localObject;
        g.b("no data store found");
      }
    }
  }
  
  private List<ApplicationInfo> f()
  {
    Object localObject = this.b.getPackageManager();
    LinkedList localLinkedList = new LinkedList();
    if (localObject == null)
    {
      g.c("package manager not found. unable to search for kongregate apps");
      return localLinkedList;
    }
    localObject = ((PackageManager)localObject).getInstalledPackages(8).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      ProviderInfo[] arrayOfProviderInfo = localPackageInfo.providers;
      if (arrayOfProviderInfo != null)
      {
        int j = arrayOfProviderInfo.length;
        int i = 0;
        while (i < j)
        {
          if ("com.kongregate.permission.ReadSharedData2".equals(arrayOfProviderInfo[i].readPermission)) {
            localLinkedList.add(localPackageInfo.applicationInfo);
          }
          i += 1;
        }
      }
    }
    return localLinkedList;
  }
  
  public String a(String paramString)
  {
    synchronized (this.e)
    {
      paramString = (String)this.d.get(paramString);
      return paramString;
    }
  }
  
  public Map<String, Object> a()
  {
    synchronized (this.e)
    {
      HashMap localHashMap = new HashMap(this.d);
      return localHashMap;
    }
  }
  
  public void a(String paramString1, String paramString2)
  {
    synchronized (this.e)
    {
      this.d.put(paramString1, paramString2);
      return;
    }
  }
  
  public void b()
  {
    if (this.a == null)
    {
      g.d("no secret key, unable to persist");
      return;
    }
    if (this.b.getApplicationInfo() == null)
    {
      g.c("SharedDataStore unable to commit. no app info");
      return;
    }
    if (this.b.getPackageManager() == null)
    {
      g.c("SharedDataStore unable to commit. no package manager");
      return;
    }
    synchronized (this.e)
    {
      JSONObject localJSONObject = new JSONObject(this.d);
      c.d.a(new Runnable()
      {
        public final void run()
        {
          Object localObject1 = m.this;
          localObject1 = m.a(this.a, this.b);
          byte[] arrayOfByte = this.b.getIV();
          Object localObject2 = m.a(m.this).iterator();
          Object localObject3;
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
            m localM1 = m.this;
            m localM2 = m.this;
            m.a(localM1, m.a((ApplicationInfo)localObject3), (byte[])localObject1, arrayOfByte);
          }
          if (d.b(m.b(m.this)))
          {
            localObject2 = m.this;
            localObject3 = m.this;
            m.a((m)localObject2, m.c(), (byte[])localObject1, arrayOfByte);
          }
        }
      });
      return;
    }
  }
  
  public void b(String paramString)
  {
    synchronized (this.e)
    {
      this.d.remove(paramString);
      return;
    }
  }
}
