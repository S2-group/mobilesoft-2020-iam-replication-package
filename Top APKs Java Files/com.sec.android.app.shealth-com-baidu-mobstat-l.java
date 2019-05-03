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

public final class l
{
  static l a = new l();
  
  public l() {}
  
  private static void a$16966e69(ArrayList<a> paramArrayList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(System.currentTimeMillis());
    try
    {
      JSONArray localJSONArray = new JSONArray();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        JSONObject localJSONObject = ((a)paramArrayList.next()).a();
        if (localJSONObject != null) {
          localJSONArray.put(localJSONObject);
        }
      }
      paramArrayList = new JSONObject();
      paramArrayList.put("app_apk", localJSONArray);
      paramArrayList.put("meta-data", localStringBuilder.toString());
      paramArrayList = cv.a.a(paramArrayList.toString().getBytes());
    }
    catch (Exception paramArrayList)
    {
      co.c().b(paramArrayList);
      paramArrayList = "";
    }
    if (!TextUtils.isEmpty(paramArrayList)) {
      t.e.a(System.currentTimeMillis(), paramArrayList);
    }
  }
  
  private static ArrayList<PackageInfo> d(Context paramContext)
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
      co.c().b(localException);
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public final void a(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = d(paramContext).iterator();
      while (localIterator.hasNext())
      {
        paramContext = (PackageInfo)localIterator.next();
        Object localObject = paramContext.applicationInfo;
        if (localObject != null)
        {
          String str2 = paramContext.packageName;
          String str3 = paramContext.versionName;
          String str1 = "";
          Signature[] arrayOfSignature = paramContext.signatures;
          paramContext = str1;
          if (arrayOfSignature != null)
          {
            paramContext = str1;
            if (arrayOfSignature.length != 0) {
              paramContext = arrayOfSignature[0].toChars().toString();
            }
          }
          str1 = db.b.a(paramContext.getBytes());
          paramContext = "";
          localObject = ((ApplicationInfo)localObject).sourceDir;
          if (!TextUtils.isEmpty((CharSequence)localObject)) {
            paramContext = db.b.a(new File((String)localObject));
          }
          localArrayList.add(new a(str2, str3, str1, paramContext));
        }
      }
      a$16966e69(localArrayList);
      return;
    }
    finally {}
  }
  
  static final class a
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
    
    public final JSONObject a()
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
        co.c().b(localJSONException);
      }
      return null;
    }
  }
}
