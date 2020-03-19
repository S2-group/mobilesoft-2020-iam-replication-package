package com.ihs.b.d.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.ihs.commons.i.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class e
  extends a
{
  private boolean g;
  
  public e(Context paramContext, n paramN, d paramD)
  {
    super(paramContext, paramN, paramD);
  }
  
  private JSONArray e()
  {
    JSONArray localJSONArray = new JSONArray();
    List localList = a().getPackageManager().getInstalledPackages(128);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localJSONArray.put(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    return localJSONArray;
  }
  
  protected boolean b()
  {
    return this.g;
  }
  
  protected String c()
  {
    return "kSharedPreferenceKey_InstalledPackages";
  }
  
  public void d()
  {
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray = e();
    if (localJSONArray != null) {
      localHashMap.put("installed_apps", localJSONArray);
    }
    this.g = true;
    a(localHashMap);
  }
}
