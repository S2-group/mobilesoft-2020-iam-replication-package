package com.cleanmaster.common_transition.report;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import com.cleanmaster.base.util.system.m;
import com.cleanmaster.kinfocreporter.a;
import com.keniu.security.d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

public final class y
  extends a
{
  public y()
  {
    super("cm_userstat_inst");
  }
  
  public static y a(Context paramContext, String paramString)
  {
    y localY = new y();
    PackageManager localPackageManager = d.a().getPackageManager();
    localY.set("pkgname", paramString);
    localY.set("appname", m.q(paramContext, paramString));
    localY.set("vercode", m.x(paramContext, paramString));
    localY.set("source", m.a(localPackageManager, paramString, "unknown"));
    try
    {
      localY.set("cert_md5", b(d.a(), paramString));
      return localY;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localY;
  }
  
  private static String a(PackageInfo paramPackageInfo)
  {
    int i = 0;
    paramPackageInfo = Base64.encode(paramPackageInfo.signatures[0].toByteArray(), 2);
    try
    {
      paramPackageInfo = MessageDigest.getInstance("MD5").digest(paramPackageInfo);
      char[] arrayOfChar1 = "0123456789abcdef".toCharArray();
      char[] arrayOfChar2 = new char[paramPackageInfo.length * 2];
      while (i < paramPackageInfo.length)
      {
        int j = paramPackageInfo[i] & 0xFF;
        arrayOfChar2[(i << 1)] = arrayOfChar1[(j >>> 4)];
        arrayOfChar2[((i << 1) + 1)] = arrayOfChar1[(j & 0xF)];
        i += 1;
      }
      paramPackageInfo = new String(arrayOfChar2);
      return paramPackageInfo;
    }
    catch (NoSuchAlgorithmException paramPackageInfo) {}
    return "";
  }
  
  public static String b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals(paramString)) {
        return a(localPackageInfo);
      }
    }
    return "";
  }
}
