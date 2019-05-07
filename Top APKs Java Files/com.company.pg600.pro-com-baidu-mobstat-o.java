package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class o
{
  static o a = new o();
  
  public o() {}
  
  private void a(Context paramContext, ArrayList<p> paramArrayList)
  {
    paramContext = new StringBuilder();
    paramContext.append(System.currentTimeMillis());
    for (;;)
    {
      try
      {
        localJSONArray = new JSONArray();
        paramArrayList = paramArrayList.iterator();
        if (paramArrayList.hasNext()) {
          continue;
        }
        paramArrayList = new JSONObject();
        paramArrayList.put("app_apk", localJSONArray);
        paramArrayList.put("meta-data", paramContext.toString());
        paramContext = cj.a(paramArrayList.toString().getBytes());
      }
      catch (Exception paramContext)
      {
        JSONArray localJSONArray;
        JSONObject localJSONObject;
        bc.b(paramContext);
        paramContext = "";
        continue;
        y.e.a(System.currentTimeMillis(), paramContext);
      }
      if (!TextUtils.isEmpty(paramContext)) {
        continue;
      }
      return;
      localJSONObject = ((p)paramArrayList.next()).a();
      if (localJSONObject != null) {
        localJSONArray.put(localJSONObject);
      }
    }
  }
  
  private void b(Context paramContext)
  {
    a(paramContext, c(paramContext));
  }
  
  private ArrayList<p> c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = d(paramContext).iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      paramContext = (PackageInfo)localIterator.next();
      localObject = paramContext.applicationInfo;
    } while (localObject == null);
    String str1 = paramContext.packageName;
    String str2 = paramContext.versionName;
    paramContext = paramContext.signatures;
    label70:
    label73:
    String str3;
    if (paramContext == null)
    {
      paramContext = "";
      str3 = cp.a(paramContext.getBytes());
      paramContext = "";
      localObject = ((ApplicationInfo)localObject).sourceDir;
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        break label140;
      }
    }
    for (;;)
    {
      localArrayList.add(new p(str1, str2, str3, paramContext));
      break;
      if (paramContext.length == 0) {
        break label70;
      }
      paramContext = paramContext[0].toChars().toString();
      break label73;
      label140:
      paramContext = cp.a(new File((String)localObject));
    }
  }
  
  private ArrayList<PackageInfo> d(Context paramContext)
  {
    localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager();
    if (localObject != null) {
      paramContext = new ArrayList(1);
    }
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(64);
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        bc.b(localException);
        continue;
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
          localArrayList.add(localPackageInfo);
        }
      }
    }
    paramContext = paramContext.iterator();
    if (!paramContext.hasNext())
    {
      return localArrayList;
      return localArrayList;
    }
  }
  
  public void a(Context paramContext)
  {
    try
    {
      b(paramContext);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
}
