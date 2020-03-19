package com.twofortyfouram.locale;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class PackageUtilities
{
  private static final Set<String> COMPATIBLE_PACKAGES = ;
  
  public PackageUtilities() {}
  
  private static Set<String> constructPackageSet()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("com.twofortyfouram.locale");
    localHashSet.add("net.dinglisch.android.taskerm");
    localHashSet.add("net.dinglisch.android.tasker");
    localHashSet.add("net.dinglisch.android.taskercupcake");
    return Collections.unmodifiableSet(localHashSet);
  }
  
  public static String getCompatiblePackage(PackageManager paramPackageManager, String paramString)
  {
    paramPackageManager = paramPackageManager.getInstalledPackages(0);
    Iterator localIterator1;
    String str1;
    if (COMPATIBLE_PACKAGES.contains(paramString))
    {
      localIterator1 = paramPackageManager.iterator();
      while (localIterator1.hasNext())
      {
        str1 = ((PackageInfo)localIterator1.next()).packageName;
        if (paramString.equals(str1)) {
          return str1;
        }
      }
    }
    String str2;
    do
    {
      localIterator1 = COMPATIBLE_PACKAGES.iterator();
      Iterator localIterator2;
      while (!localIterator2.hasNext())
      {
        do
        {
          if (!localIterator1.hasNext()) {
            break;
          }
          str1 = (String)localIterator1.next();
        } while (str1.equals(paramString));
        localIterator2 = paramPackageManager.iterator();
      }
      str2 = ((PackageInfo)localIterator2.next()).packageName;
    } while (!str1.equals(str2));
    return str2;
    return null;
  }
}
