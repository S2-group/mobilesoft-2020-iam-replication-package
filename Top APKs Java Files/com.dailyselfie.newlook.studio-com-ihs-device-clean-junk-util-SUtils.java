package com.ihs.device.clean.junk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import com.ihs.app.framework.b;
import com.ihs.commons.g.f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SUtils
{
  public static final String EXTERNAL_STORAGE_DIRECTORY_ABSOLUTE_PATH;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
    localStringBuilder.append(File.separator);
    EXTERNAL_STORAGE_DIRECTORY_ABSOLUTE_PATH = localStringBuilder.toString();
  }
  
  public SUtils() {}
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte2 != null) && (paramArrayOfByte3 != null)) {
      try
      {
        paramArrayOfByte2 = new SecretKeySpec(paramArrayOfByte2, "AES");
        Cipher localCipher = Cipher.getInstance("AES/CBC/NoPadding");
        localCipher.init(2, paramArrayOfByte2, new IvParameterSpec(paramArrayOfByte3));
        paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte1);
        int i = paramArrayOfByte1[(paramArrayOfByte1.length - 1)];
        paramArrayOfByte2 = new byte[paramArrayOfByte1.length - i];
        System.arraycopy(paramArrayOfByte1, 0, paramArrayOfByte2, 0, paramArrayOfByte2.length);
        return paramArrayOfByte2;
      }
      catch (Exception paramArrayOfByte1)
      {
        paramArrayOfByte1.printStackTrace();
        return null;
      }
    }
    f.e("JDC", "encryptBytes or key or iv can't be null");
    return null;
  }
  
  /* Error */
  public static byte[] b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: invokevirtual 98	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   9: aload_1
    //   10: invokevirtual 104	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   13: astore_0
    //   14: aload_0
    //   15: invokevirtual 110	java/io/InputStream:available	()I
    //   18: newarray byte
    //   20: astore_3
    //   21: aload_0
    //   22: aload_3
    //   23: invokevirtual 114	java/io/InputStream:read	([B)I
    //   26: istore_2
    //   27: iload_2
    //   28: iconst_m1
    //   29: if_icmpeq +6 -> 35
    //   32: goto -11 -> 21
    //   35: aload_3
    //   36: astore 4
    //   38: aload_0
    //   39: ifnull +81 -> 120
    //   42: aload_3
    //   43: astore 4
    //   45: aload_0
    //   46: invokevirtual 117	java/io/InputStream:close	()V
    //   49: aload_3
    //   50: areturn
    //   51: astore_0
    //   52: aload_0
    //   53: invokevirtual 80	java/lang/Exception:printStackTrace	()V
    //   56: aload 4
    //   58: areturn
    //   59: astore 4
    //   61: aload_0
    //   62: astore_1
    //   63: aload_3
    //   64: astore_0
    //   65: goto +31 -> 96
    //   68: astore_1
    //   69: goto +54 -> 123
    //   72: astore 4
    //   74: aconst_null
    //   75: astore_3
    //   76: aload_0
    //   77: astore_1
    //   78: aload_3
    //   79: astore_0
    //   80: goto +16 -> 96
    //   83: astore_1
    //   84: aload_3
    //   85: astore_0
    //   86: goto +37 -> 123
    //   89: astore 4
    //   91: aconst_null
    //   92: astore_0
    //   93: aload 5
    //   95: astore_1
    //   96: aload_1
    //   97: astore_3
    //   98: aload 4
    //   100: invokevirtual 80	java/lang/Exception:printStackTrace	()V
    //   103: aload_0
    //   104: astore 4
    //   106: aload_1
    //   107: ifnull +13 -> 120
    //   110: aload_0
    //   111: astore 4
    //   113: aload_1
    //   114: invokevirtual 117	java/io/InputStream:close	()V
    //   117: aload_0
    //   118: astore 4
    //   120: aload 4
    //   122: areturn
    //   123: aload_0
    //   124: ifnull +15 -> 139
    //   127: aload_0
    //   128: invokevirtual 117	java/io/InputStream:close	()V
    //   131: goto +8 -> 139
    //   134: astore_0
    //   135: aload_0
    //   136: invokevirtual 80	java/lang/Exception:printStackTrace	()V
    //   139: aload_1
    //   140: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	paramContext	Context
    //   0	141	1	paramString	String
    //   26	4	2	i	int
    //   4	94	3	localObject1	Object
    //   36	21	4	localObject2	Object
    //   59	1	4	localException1	Exception
    //   72	1	4	localException2	Exception
    //   89	10	4	localException3	Exception
    //   104	17	4	localContext	Context
    //   1	93	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   45	49	51	java/lang/Exception
    //   113	117	51	java/lang/Exception
    //   21	27	59	java/lang/Exception
    //   14	21	68	finally
    //   21	27	68	finally
    //   14	21	72	java/lang/Exception
    //   5	14	83	finally
    //   98	103	83	finally
    //   5	14	89	java/lang/Exception
    //   127	131	134	java/lang/Exception
  }
  
  public static HashSet<String> c()
  {
    try
    {
      List localList = b.a().getPackageManager().getInstalledPackages(128);
    }
    catch (Exception localException)
    {
      if (f.b()) {
        throw localException;
      }
      localException.printStackTrace();
      localIterator = null;
    }
    HashSet localHashSet = new HashSet();
    if (localIterator == null) {
      return localHashSet;
    }
    Iterator localIterator = localIterator.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    return localHashSet;
  }
  
  public static byte[] d(File paramFile)
  {
    if (paramFile == null) {
      return null;
    }
    try
    {
      paramFile = new FileInputStream(paramFile);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(4096);
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        int i = paramFile.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      paramFile.close();
      localByteArrayOutputStream.close();
      paramFile = localByteArrayOutputStream.toByteArray();
      return paramFile;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
    return null;
  }
}
