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

public final class ao
{
  private final PackageManager a;
  private final String b;
  private volatile Set<Signature> c;
  
  public ao(String paramString, PackageManager paramPackageManager, Set<Signature> paramSet)
  {
    this.b = paramString;
    this.a = paramPackageManager;
    this.c = paramSet;
  }
  
  private int a(String paramString)
  {
    if (this.b.equals(paramString)) {
      return 0;
    }
    if (this.a.checkSignatures(this.b, paramString) == 0) {
      return 0;
    }
    if (this.c == null) {
      return -3;
    }
    try
    {
      paramString = this.a.getPackageInfo(paramString, 64).signatures;
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        boolean bool = this.c.contains(localObject);
        if (bool)
        {
          i = 1;
          break label101;
        }
        i += 1;
      }
      i = 0;
      label101:
      if (i == 0) {
        return -3;
      }
      return 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return -4;
  }
  
  private Set<String> b()
  {
    Object localObject = this.a.getInstalledPackages(0);
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      String str = localPackageInfo.packageName;
      boolean bool = str.startsWith("android");
      int j = 1;
      int i;
      if ((!bool) && (!str.startsWith("com.android")) && (!str.startsWith("com.google"))) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == 0)
      {
        if (a(localPackageInfo.packageName) == 0) {
          i = j;
        } else {
          i = 0;
        }
        if (i != 0) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
    }
    return localHashSet;
  }
  
  public final JSONArray a()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext()) {
      localJSONArray.put((String)localIterator.next());
    }
    return localJSONArray;
  }
}
