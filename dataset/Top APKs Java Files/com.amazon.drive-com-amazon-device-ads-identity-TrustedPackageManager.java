package com.amazon.device.ads.identity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;

public final class TrustedPackageManager
{
  private final PackageManager packageManager;
  private final String packageName;
  private volatile Set<Signature> trustedCerts;
  
  public TrustedPackageManager(String paramString, PackageManager paramPackageManager, Set<Signature> paramSet)
  {
    this.packageName = paramString;
    this.packageManager = paramPackageManager;
    this.trustedCerts = paramSet;
  }
  
  private int checkSignature(String paramString)
  {
    if (this.packageName.equals(paramString)) {}
    for (;;)
    {
      return 0;
      if (this.packageManager.checkSignatures(this.packageName, paramString) == 0) {
        continue;
      }
      if (this.trustedCerts == null) {
        return -3;
      }
      try
      {
        paramString = this.packageManager.getPackageInfo(paramString, 64).signatures;
        int j = paramString.length;
        int i = 0;
        label57:
        if (i < j)
        {
          Object localObject = paramString[i];
          boolean bool = this.trustedCerts.contains(localObject);
          if (!bool) {}
        }
        for (i = 1; i == 0; i = 0)
        {
          return -3;
          i += 1;
          break label57;
        }
        return -4;
      }
      catch (PackageManager.NameNotFoundException paramString) {}
    }
  }
  
  private Set<String> getTrustedInstalledPackages()
  {
    Object localObject = this.packageManager.getInstalledPackages(0);
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    label83:
    label123:
    label125:
    label128:
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      String str = localPackageInfo.packageName;
      if ((str.startsWith("android")) || (str.startsWith("com.android")) || (str.startsWith("com.google")))
      {
        i = 1;
        if (i != 0) {
          break label123;
        }
        if (checkSignature(localPackageInfo.packageName) != 0) {
          break label125;
        }
      }
      for (int i = 1;; i = 0)
      {
        if (i == 0) {
          break label128;
        }
        localHashSet.add(localPackageInfo.packageName);
        break;
        i = 0;
        break label83;
        break;
      }
    }
    return localHashSet;
  }
  
  public final JSONArray getTrustedInstalledPackagesJSONArray()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = getTrustedInstalledPackages().iterator();
    while (localIterator.hasNext()) {
      localJSONArray.put((String)localIterator.next());
    }
    return localJSONArray;
  }
}
