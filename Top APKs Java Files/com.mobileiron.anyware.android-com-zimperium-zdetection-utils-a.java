package com.zimperium.zdetection.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Environment;
import android.text.TextUtils;
import com.zimperium.zdetection.internal.ZDetectionInternal;
import com.zimperium.zips.ZIAPSignatureParser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class a
{
  public static String a(String paramString)
  {
    try
    {
      ApplicationInfo localApplicationInfo = ZDetectionInternal.getAppContext().getPackageManager().getApplicationInfo(paramString, 0);
      String str = paramString;
      if (localApplicationInfo != null) {
        str = localApplicationInfo.loadLabel(ZDetectionInternal.getAppContext().getPackageManager()).toString();
      }
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      a("\tException: " + localNameNotFoundException, new Object[0]);
    }
    return paramString;
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      for (String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF); str.length() < 2; str = "0" + str) {}
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static List<PublicKey> a(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
        if (paramContext != null)
        {
          int j = paramContext.length;
          i = 0;
          if (i < j)
          {
            paramString = paramContext[i];
            try
            {
              paramString = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramString.toByteArray())).getPublicKey();
              if (paramString != null)
              {
                a("\tAdding: " + paramString, new Object[0]);
                localArrayList.add(paramString);
              }
              else
              {
                a("\tPublicKey is null", new Object[0]);
              }
            }
            catch (Exception paramString)
            {
              a("Exception: " + paramString, new Object[0]);
            }
          }
        }
        return localArrayList;
      }
      catch (Exception paramContext)
      {
        a("\tException: " + paramContext, new Object[0]);
      }
      i += 1;
    }
  }
  
  public static List<String> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      label84:
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.flags & 0x1) != 0) {}
        for (int i = 1;; i = 0)
        {
          if ((i != 0) && (!paramBoolean)) {
            break label84;
          }
          localArrayList.add(localApplicationInfo.packageName);
          break;
        }
      }
    }
    return localArrayList;
  }
  
  public static List<File> a(File paramFile)
  {
    a("getApksInDirectory(" + paramFile + ")", new Object[0]);
    return a(paramFile, "*");
  }
  
  public static List<File> a(File paramFile, String paramString)
  {
    a("getApksByPackage(): " + paramFile.getAbsolutePath(), new Object[0]);
    a("\tPackage=" + paramString, new Object[0]);
    PackageManager localPackageManager = ZDetectionInternal.getAppContext().getPackageManager();
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      File localFile;
      try
      {
        paramFile = new LinkedList(Arrays.asList(paramFile.listFiles()));
        if (!paramFile.isEmpty())
        {
          localFile = (File)paramFile.remove();
          if (!localFile.isDirectory()) {
            break label211;
          }
          a("\tChecking directory:" + localFile.getAbsolutePath(), new Object[0]);
          paramFile.addAll(Arrays.asList(localFile.listFiles()));
        }
        if (!localFile.getName().toLowerCase().endsWith(".apk")) {
          continue;
        }
      }
      catch (Exception paramFile)
      {
        a("Exception: " + paramFile, new Object[0]);
        a("\tReturning: " + localArrayList.size() + " files.", new Object[0]);
        return localArrayList;
      }
      label211:
      PackageInfo localPackageInfo = localPackageManager.getPackageArchiveInfo(localFile.getAbsolutePath(), 0);
      if ((paramString.equals("*")) || (TextUtils.equals(paramString, localPackageInfo.packageName)))
      {
        a("\tAdding :" + localFile.getAbsolutePath(), new Object[0]);
        localArrayList.add(localFile);
      }
    }
  }
  
  private static void a(String paramString, Object... paramVarArgs)
  {
    ZLog.i("ApkUtil: " + paramString, paramVarArgs);
  }
  
  public static byte[] a(PublicKey paramPublicKey)
  {
    try
    {
      Object localObject = new X509EncodedKeySpec(paramPublicKey.getEncoded());
      KeyFactory localKeyFactory = KeyFactory.getInstance(paramPublicKey.getAlgorithm());
      if (paramPublicKey.getAlgorithm().compareTo("RSA") == 0) {}
      for (paramPublicKey = ((RSAPublicKey)localKeyFactory.generatePublic((KeySpec)localObject)).getModulus().toByteArray();; paramPublicKey = ((DSAPublicKey)localKeyFactory.generatePublic((KeySpec)localObject)).getY().toByteArray())
      {
        localObject = paramPublicKey;
        if (paramPublicKey != null) {
          localObject = ZIAPSignatureParser.removeZeros(paramPublicKey);
        }
        return localObject;
        if (paramPublicKey.getAlgorithm().compareTo("DSA") != 0) {
          break;
        }
      }
    }
    catch (NoSuchAlgorithmException paramPublicKey)
    {
      for (;;)
      {
        paramPublicKey.printStackTrace();
        paramPublicKey = null;
      }
    }
    catch (InvalidKeySpecException paramPublicKey)
    {
      for (;;)
      {
        paramPublicKey.printStackTrace();
        paramPublicKey = null;
      }
    }
  }
  
  public static String b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new ByteArrayInputStream(paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures[0].toByteArray());
      paramContext = ((X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(paramContext)).getPublicKey();
      if (paramContext.getAlgorithm().equalsIgnoreCase("RSA"))
      {
        paramContext = ((RSAPublicKey)paramContext).getModulus().toByteArray();
        paramString = MessageDigest.getInstance("MD5");
        if (paramContext[0] == 0) {}
        for (paramContext = paramString.digest(Arrays.copyOfRange(paramContext, 1, paramContext.length));; paramContext = paramString.digest(paramContext)) {
          return a(paramContext);
        }
      }
      return "";
    }
    catch (Throwable paramContext)
    {
      a("\tApkUtil getApkSignature: Exception: " + paramContext, new Object[0]);
    }
  }
  
  public static String b(File paramFile)
  {
    a("getApkSource()", new Object[0]);
    a("\tAPK path=" + paramFile.getPath(), new Object[0]);
    a("\tDOWNLOADS path=" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), new Object[0]);
    if (f(paramFile.getPath())) {
      return "CATEGORY_DOWNLOADED";
    }
    return "CATEGORY_INSTALLED";
  }
  
  public static String b(String paramString)
  {
    String str = "";
    try
    {
      ApplicationInfo localApplicationInfo = ZDetectionInternal.getAppContext().getPackageManager().getApplicationInfo(paramString, 0);
      paramString = str;
      if (localApplicationInfo != null) {
        paramString = localApplicationInfo.publicSourceDir;
      }
      return paramString;
    }
    catch (Exception paramString)
    {
      a("\tException: " + paramString, new Object[0]);
    }
    return "";
  }
  
  public static String c(Context paramContext, String paramString)
  {
    PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 0);
    paramContext.getPackageManager().getApplicationInfo(paramString, 0);
    return localPackageInfo.applicationInfo.sourceDir;
  }
  
  public static boolean c(String paramString)
  {
    try
    {
      ZDetectionInternal.getAppContext().getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static String d(String paramString)
  {
    Object localObject = new File(paramString);
    if (((File)localObject).exists())
    {
      PackageInfo localPackageInfo = ZDetectionInternal.getAppContext().getPackageManager().getPackageArchiveInfo(((File)localObject).getAbsolutePath(), 0);
      localObject = paramString;
      if (localPackageInfo != null)
      {
        localObject = paramString;
        if (!TextUtils.isEmpty(localPackageInfo.packageName)) {
          localObject = localPackageInfo.packageName;
        }
      }
      return localObject;
    }
    return "";
  }
  
  public static boolean e(String paramString)
  {
    File localFile = Environment.getDataDirectory();
    return (paramString != null) && ((paramString.startsWith(localFile.getPath())) || (paramString.startsWith("/mnt/asec/")) || (paramString.startsWith("/system/app/")) || (paramString.startsWith("/system/priv-app/")));
  }
  
  public static boolean f(String paramString)
  {
    File localFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    return (paramString != null) && ((paramString.startsWith(localFile.getPath())) || (paramString.startsWith(Environment.getExternalStorageDirectory().getPath())) || (paramString.startsWith("/sdcard")));
  }
}
