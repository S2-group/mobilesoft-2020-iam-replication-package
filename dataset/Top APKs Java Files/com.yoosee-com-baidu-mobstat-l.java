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
import org.json.JSONException;
import org.json.JSONObject;

public class l
{
  static l a = new l();
  
  public l() {}
  
  private void a(Context paramContext, ArrayList<a> paramArrayList)
  {
    paramContext = new StringBuilder();
    paramContext.append(System.currentTimeMillis());
    JSONArray localJSONArray;
    try
    {
      localJSONArray = new JSONArray();
      paramArrayList = paramArrayList.iterator();
      for (;;)
      {
        if (paramArrayList.hasNext())
        {
          JSONObject localJSONObject = ((a)paramArrayList.next()).a();
          if (localJSONObject != null)
          {
            localJSONArray.put(localJSONObject);
            continue;
            if (TextUtils.isEmpty(paramContext)) {
              break;
            }
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      eg.c().b(paramContext);
      paramContext = "";
    }
    for (;;)
    {
      t.e.a(System.currentTimeMillis(), paramContext);
      return;
      paramArrayList = new JSONObject();
      paramArrayList.put("app_apk", localJSONArray);
      paramArrayList.put("meta-data", paramContext.toString());
      paramContext = ev.a.a(paramArrayList.toString().getBytes());
    }
  }
  
  private void b(Context paramContext)
  {
    a(paramContext, c(paramContext));
  }
  
  private ArrayList<a> c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = d(paramContext).iterator();
    Object localObject;
    String str1;
    String str2;
    while (localIterator.hasNext())
    {
      paramContext = (PackageInfo)localIterator.next();
      localObject = paramContext.applicationInfo;
      if (localObject != null)
      {
        str1 = paramContext.packageName;
        str2 = paramContext.versionName;
        paramContext = paramContext.signatures;
        if ((paramContext == null) || (paramContext.length == 0)) {
          break label147;
        }
      }
    }
    label147:
    for (paramContext = paramContext[0].toChars().toString();; paramContext = "")
    {
      String str3 = fb.b.a(paramContext.getBytes());
      paramContext = "";
      localObject = ((ApplicationInfo)localObject).sourceDir;
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        paramContext = fb.b.a(new File((String)localObject));
      }
      localArrayList.add(new a(str1, str2, str3, paramContext));
      break;
      return localArrayList;
    }
  }
  
  private ArrayList<PackageInfo> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager();
    if (localObject == null) {
      return localArrayList;
    }
    paramContext = new ArrayList(1);
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(64);
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        eg.c().b(localException);
      }
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      ApplicationInfo localApplicationInfo = ((PackageInfo)localObject).applicationInfo;
      if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
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
  
  static class a
  {
    private String a;
    private String b;
    private String c;
    private String d;
    
    public a(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      String str = paramString1;
      if (paramString1 == null) {
        str = "";
      }
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = "";
      }
      paramString2 = paramString3;
      if (paramString3 == null) {
        paramString2 = "";
      }
      paramString3 = paramString4;
      if (paramString4 == null) {
        paramString3 = "";
      }
      this.a = str;
      this.b = paramString1;
      this.c = paramString2;
      this.d = paramString3;
    }
    
    public JSONObject a()
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("n", this.a);
        localJSONObject.put("v", this.b);
        localJSONObject.put("c", this.c);
        localJSONObject.put("a", this.d);
        return localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        eg.c().b(localJSONException);
      }
      return null;
    }
  }
}
