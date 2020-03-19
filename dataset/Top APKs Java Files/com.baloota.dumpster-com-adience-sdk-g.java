package com.adience.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.adience.sdk.e.d;
import com.adience.sdk.e.m;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class g
{
  private static SharedPreferences a;
  private Context b;
  private JSONArray c;
  private JSONArray d;
  
  g(Context paramContext)
  {
    this.b = paramContext;
    a = paramContext.getSharedPreferences("bl", 0);
    this.d = new JSONArray();
    paramContext = a.getString("df", "[]");
    try
    {
      this.c = new JSONArray(paramContext);
      return;
    }
    catch (JSONException localJSONException)
    {
      this.c = new JSONArray();
      m.a(354, localJSONException, 355, new Object[] { paramContext });
    }
  }
  
  public JSONArray a()
    throws aa
  {
    List localList = this.b.getPackageManager().getInstalledApplications(0);
    this.d = new JSONArray();
    int i = 0;
    if (i >= this.c.length())
    {
      if (!a.edit().putString("dt", this.d.toString()).commit()) {
        throw new aa();
      }
    }
    else
    {
      Iterator localIterator = localList.iterator();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          i += 1;
          break;
        }
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        try
        {
          if (this.c.get(i).equals(localApplicationInfo.packageName)) {
            this.d.put(localApplicationInfo.packageName);
          }
        }
        catch (JSONException localJSONException)
        {
          m.a(354, localJSONException, 356, new Object[] { Integer.valueOf(i), this.c.toString() });
        }
      }
    }
    d.c(354, 362, new Object[] { this.d.toString() });
    return this.d;
  }
  
  public void a(JSONArray paramJSONArray)
    throws aa
  {
    this.c = paramJSONArray;
    if (!a.edit().putString("df", paramJSONArray.toString()).commit()) {
      throw new aa();
    }
    d.c(354, 361, new Object[] { this.c.toString() });
  }
  
  public JSONArray b()
  {
    return this.d;
  }
}
