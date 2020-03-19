package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

final class ce
{
  static final ce bdN = new ce();
  private List<Map<String, String>> b = new ArrayList();
  
  ce() {}
  
  private String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      localStringBuffer.append(new JSONObject((Map)localIterator.next()).toString());
    }
    return localStringBuffer.toString();
  }
  
  private static String a(String paramString)
  {
    try
    {
      paramString = bq.m(paramString.getBytes());
      return paramString;
    }
    catch (Exception paramString)
    {
      bv.d(paramString);
    }
    return "";
  }
  
  public final void a(Context paramContext)
  {
    Object localObject1;
    try
    {
      this.b.clear();
      localObject1 = paramContext.getPackageManager();
      if (localObject1 != null)
      {
        localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
          Object localObject2 = localPackageInfo.applicationInfo;
          if ((localObject2 != null) && ((((ApplicationInfo)localObject2).flags & 0x1) == 0))
          {
            localObject2 = new HashMap();
            ((Map)localObject2).put("n", localPackageInfo.packageName);
            ((Map)localObject2).put("v", String.valueOf(localPackageInfo.versionName));
            this.b.add(localObject2);
          }
        }
      }
      localObject1 = a(a());
    }
    finally {}
    long l = System.currentTimeMillis();
    ae.bcW.a(paramContext, l, (String)localObject1);
  }
}
