package com.buzzvil.locker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Targeting
{
  Context a;
  HashSet<String> b;
  m c;
  
  public Targeting(Context paramContext)
  {
    this.a = paramContext;
    this.c = new m()
    {
      protected Boolean a(String paramAnonymousString)
      {
        if ("true".equals(paramAnonymousString)) {
          return Boolean.valueOf(true);
        }
        if ("false".equals(paramAnonymousString)) {
          return Boolean.valueOf(false);
        }
        return Boolean.valueOf(Targeting.a(Targeting.this, paramAnonymousString));
      }
    };
  }
  
  private boolean a(String paramString)
  {
    if (this.b == null)
    {
      this.b = new HashSet();
      Iterator localIterator = this.a.getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        this.b.add(localApplicationInfo.packageName);
      }
    }
    return this.b.contains(paramString);
  }
  
  public boolean isTargetedApp(String paramString)
  {
    for (;;)
    {
      int i;
      try
      {
        if (TextUtils.isEmpty(paramString)) {
          return true;
        }
        if (paramString.contains(","))
        {
          paramString = paramString.split(",");
          int j = paramString.length;
          i = 0;
          if (i < j)
          {
            String str = paramString[i];
            if (str.startsWith("-"))
            {
              if (a(str.substring(1))) {
                break label96;
              }
              return true;
            }
            if (!a(str)) {
              break label96;
            }
            return true;
          }
        }
        else
        {
          boolean bool = this.c.b(paramString);
          return bool;
        }
      }
      catch (NullPointerException paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
      }
      return false;
      label96:
      i += 1;
    }
  }
  
  public boolean isTargetingSucceeded(JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject.isNull("target_app")) {
      return true;
    }
    return isTargetedApp(paramJSONObject.getString("target_app"));
  }
}
