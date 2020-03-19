package com.apalon.am3.c;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.apalon.am3.a.h;
import com.apalon.am3.d.b;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class a
  extends m
{
  private int a;
  private List<String> b;
  private int c;
  
  public a(JSONObject paramJSONObject, int paramInt)
  {
    super(paramJSONObject, paramInt);
    String str = paramJSONObject.optString("_068e13c11c8e7a33196e72147b99f184");
    if ("exist".equals(str))
    {
      this.a = 1;
      str = paramJSONObject.optString("_4a9a34d721ab8fa21f1731d6a622a392");
      if (!"AND".equals(str)) {
        break label126;
      }
      this.c = 1;
    }
    for (;;)
    {
      paramJSONObject = paramJSONObject.optJSONArray("_2724fb4a95dd8eb8755a7ac8123239fb");
      if ((paramJSONObject == null) || (paramJSONObject.length() <= 0)) {
        return;
      }
      this.b = new LinkedList();
      paramInt = 0;
      while (paramInt < paramJSONObject.length())
      {
        this.b.add(paramJSONObject.getString(paramInt));
        paramInt += 1;
      }
      if (!"not-exist".equals(str)) {
        break;
      }
      this.a = 2;
      break;
      label126:
      if ("OR".equals(str)) {
        this.c = 2;
      }
    }
  }
  
  private boolean a(PackageManager paramPackageManager, String paramString, List<PackageInfo> paramList)
  {
    if (paramList == null) {
      try
      {
        paramPackageManager.getPackageInfo(paramString, 128);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        return false;
      }
    }
    paramPackageManager = paramList.iterator();
    while (paramPackageManager.hasNext()) {
      if (((PackageInfo)paramPackageManager.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean a(PackageManager paramPackageManager, List<PackageInfo> paramList)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      if (!a(paramPackageManager, (String)localIterator.next(), paramList)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean b(PackageManager paramPackageManager, List<PackageInfo> paramList)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      if (a(paramPackageManager, (String)localIterator.next(), paramList)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean c(PackageManager paramPackageManager, List<PackageInfo> paramList)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      if (a(paramPackageManager, (String)localIterator.next(), paramList)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean d(PackageManager paramPackageManager, List<PackageInfo> paramList)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      if (!a(paramPackageManager, (String)localIterator.next(), paramList)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean a(b paramB, n paramN)
  {
    if ((this.b == null) || (this.c == 0) || (this.a == 0)) {
      return false;
    }
    paramN = h.a().getPackageManager();
    paramB = null;
    if (this.b.size() > 100) {
      paramB = paramN.getInstalledPackages(128);
    }
    if (this.c == 1)
    {
      if (this.a == 1) {
        return a(paramN, paramB);
      }
      return b(paramN, paramB);
    }
    if (this.a == 1) {
      return c(paramN, paramB);
    }
    return d(paramN, paramB);
  }
}
