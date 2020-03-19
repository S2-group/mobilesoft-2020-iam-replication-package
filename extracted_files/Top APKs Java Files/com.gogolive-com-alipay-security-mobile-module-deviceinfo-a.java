package com.alipay.security.mobile.module.deviceinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static a a = new a();
  
  private a() {}
  
  private static a a()
  {
    return a;
  }
  
  private static byte[] a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (localPackageInfo.packageName.equals(paramString))
        {
          paramContext = localPackageInfo.signatures[0].toByteArray();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private static byte[] b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = a(paramContext, paramString);
      paramContext = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramContext))).getPublicKey().getEncoded();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
}
