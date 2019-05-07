package com.qisi.plugin.deduplication.peer;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.qisi.plugin.manager.App;
import com.qisi.plugin.utils.Md5Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class BasePeerHelper
{
  protected final Set<String> mValidOldSignatureList = new HashSet();
  protected final Set<String> mValidPrefixList = new HashSet();
  protected final Set<String> mValidSignatureList = new HashSet();
  
  public BasePeerHelper() {}
  
  private boolean checkWithSignature(String paramString1, String paramString2)
  {
    if (this.mValidSignatureList.contains(paramString2)) {
      return true;
    }
    if (this.mValidOldSignatureList.contains(paramString2))
    {
      paramString2 = this.mValidPrefixList.iterator();
      while (paramString2.hasNext()) {
        if (paramString1.startsWith((String)paramString2.next())) {
          return true;
        }
      }
    }
    return false;
  }
  
  @NonNull
  public List<ApplicationInfo> getThemeAppsFromPM()
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = App.getContext().getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo != null)
        {
          String str = localApplicationInfo.packageName;
          if ((!TextUtils.isEmpty(str)) && (isValidPeer(str))) {
            localArrayList.add(localApplicationInfo);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public boolean isValidPeer(String paramString)
  {
    Object localObject = App.getContext();
    if ((TextUtils.isEmpty(paramString)) || (localObject == null)) {}
    for (;;)
    {
      return false;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getPackageInfo(paramString, 64);
        if ((localObject == null) || (((PackageInfo)localObject).signatures == null)) {
          continue;
        }
        localObject = ((PackageInfo)localObject).signatures;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          String str = Md5Util.getMd5Pass(localObject[i].toCharsString());
          if (!TextUtils.isEmpty(str))
          {
            boolean bool = checkWithSignature(paramString, str);
            if (bool) {
              return true;
            }
          }
          i += 1;
        }
        return false;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
}
