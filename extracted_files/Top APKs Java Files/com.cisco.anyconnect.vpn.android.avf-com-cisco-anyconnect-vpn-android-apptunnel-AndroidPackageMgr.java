package com.cisco.anyconnect.vpn.android.apptunnel;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.cisco.anyconnect.vpn.android.util.AppLog;
import com.cisco.anyconnect.vpn.android.util.AppLog.Severity;
import com.cisco.anyconnect.vpn.android.util.ByteUtils;
import com.cisco.anyconnect.vpn.android.util.MultiUserUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AndroidPackageMgr
  implements IPackageMgr
{
  private static final String ENTITY_NAME = "AndroidPackageMgr";
  private final Context mContext;
  
  public AndroidPackageMgr(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private DetailedPackageInfo getDetailedPackageInfo(PackageInfo paramPackageInfo)
  {
    return new DetailedPackageInfo.Builder(paramPackageInfo.packageName, paramPackageInfo.versionCode).withSharedUid(paramPackageInfo.sharedUserId).withUid(MultiUserUtils.getUserFreeAppId(paramPackageInfo.applicationInfo.uid)).withSignature(getSignature(paramPackageInfo)).build();
  }
  
  private String getSignature(PackageInfo paramPackageInfo)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = paramPackageInfo.signatures;
      paramPackageInfo = (PackageInfo)localObject1;
      if (localObject2.length < 0)
      {
        localObject2 = new ByteArrayInputStream(localObject2[0].toByteArray());
        paramPackageInfo = null;
      }
    }
    catch (Exception paramPackageInfo)
    {
      Object localObject2;
      label38:
      AppLog.logDebugMessage(AppLog.Severity.DBG_INFO, "AndroidPackageMgr", "getSignature failed", paramPackageInfo);
      return null;
    }
    try
    {
      localObject1 = CertificateFactory.getInstance("X509");
      paramPackageInfo = (PackageInfo)localObject1;
    }
    catch (CertificateException localCertificateException)
    {
      break label38;
    }
    paramPackageInfo = (X509Certificate)paramPackageInfo.generateCertificate((InputStream)localObject2);
    localObject1 = MessageDigest.getInstance("SHA-1");
    ((MessageDigest)localObject1).update(paramPackageInfo.getEncoded());
    paramPackageInfo = ByteUtils.bytesToHexString(((MessageDigest)localObject1).digest(), null);
    return paramPackageInfo;
  }
  
  public Set<AppInfo> getAllPackages()
  {
    HashSet localHashSet = new HashSet();
    PackageManager localPackageManager = this.mContext.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(0).iterator();
    Object localObject2;
    Object localObject3;
    int i;
    boolean bool;
    if (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      localObject3 = ((PackageInfo)localObject2).applicationInfo.packageName;
      i = MultiUserUtils.getUserFreeAppId(((PackageInfo)localObject2).applicationInfo.uid);
      if (((PackageInfo)localObject2).sharedUserId != null) {}
      for (bool = true;; bool = false)
      {
        localHashSet.add(new AppInfo((String)localObject3, i, bool));
        break;
      }
    }
    for (;;)
    {
      try
      {
        localObject2 = MultiUserUtils.getAdditionalUserIds(this.mContext).iterator();
        localObject1 = localHashSet;
        if (((Iterator)localObject2).hasNext())
        {
          i = ((Integer)((Iterator)localObject2).next()).intValue();
          localObject1 = ((List)localPackageManager.getClass().getMethod("getInstalledPackages", new Class[] { Integer.TYPE, Integer.TYPE }).invoke(localPackageManager, new Object[] { Integer.valueOf(0), Integer.valueOf(i) })).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject3 = (PackageInfo)((Iterator)localObject1).next();
            String str = ((PackageInfo)localObject3).applicationInfo.packageName;
            i = MultiUserUtils.getUserFreeAppId(((PackageInfo)localObject3).applicationInfo.uid);
            if (((PackageInfo)localObject3).sharedUserId == null) {
              break label314;
            }
            bool = true;
            localHashSet.add(new AppInfo(str, i, bool));
          }
        }
        else
        {
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        localObject1 = localHashSet;
        if (!(localException.getCause() instanceof SecurityException))
        {
          AppLog.logDebugMessage(AppLog.Severity.DBG_ERROR, "AndroidPackageMgr", "getAllPackagesOnSystem failed", localException);
          localObject1 = null;
        }
      }
      label314:
      bool = false;
    }
  }
  
  public DetailedPackageInfo getPackageInfo(String paramString)
  {
    try
    {
      Object localObject = this.mContext.getPackageManager().getPackageInfo(paramString, 64);
      if (localObject != null)
      {
        localObject = getDetailedPackageInfo((PackageInfo)localObject);
        return localObject;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Iterator localIterator = MultiUserUtils.getAdditionalUserIds(this.mContext).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = MultiUserUtils.getPackageInfo(paramString, 64, ((Integer)localIterator.next()).intValue());
        if (localPackageInfo != null) {
          return getDetailedPackageInfo(localPackageInfo);
        }
      }
    }
    return null;
  }
}
