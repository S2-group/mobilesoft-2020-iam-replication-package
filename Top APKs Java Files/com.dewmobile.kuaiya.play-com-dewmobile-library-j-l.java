package com.dewmobile.library.j;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l
  extends a
{
  public boolean a;
  public boolean b;
  public String c;
  public long d;
  public int e;
  public String f;
  public String g;
  
  public l() {}
  
  public l(JSONObject paramJSONObject)
  {
    super(paramJSONObject);
    this.a = paramJSONObject.optBoolean("f");
    this.b = paramJSONObject.optBoolean("h");
    this.c = paramJSONObject.optString("m");
    this.e = paramJSONObject.optInt("sort");
  }
  
  public static List<l> a()
  {
    Object localObject1 = null;
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = m.b("dm_diamond_material", null);
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {
      for (;;)
      {
        try
        {
          Object localObject3 = new JSONObject((String)localObject2);
          localObject2 = ((JSONObject)localObject3).optJSONArray("materials");
          String str = ((JSONObject)localObject3).optDouble("b", 0.6D) + "";
          localObject3 = str.split(".");
          if (localObject3.length > 1)
          {
            if (Integer.valueOf(localObject3[1]).intValue() <= 0) {
              localObject1 = localObject3[0];
            }
            m.a("cur_trans_get_diamond", (String)localObject1);
            if (localObject2 == null) {
              return localArrayList;
            }
          }
          else
          {
            localObject1 = Double.valueOf(str).intValue() + "";
            continue;
          }
          if (i >= ((JSONArray)localObject2).length()) {
            break;
          }
          localObject3 = ((JSONArray)localObject2).getJSONObject(i);
          localObject1 = new l();
          ((l)localObject1).E = ((JSONObject)localObject3).optInt("mid");
          ((l)localObject1).F = ((JSONObject)localObject3).optString("pkg");
          ((l)localObject1).W = (((JSONObject)localObject3).optDouble("price") + "");
          ((l)localObject1).G = ((JSONObject)localObject3).optString("title");
          ((l)localObject1).K = ((JSONObject)localObject3).optString("url");
          ((l)localObject1).H = ((JSONObject)localObject3).optLong("size");
          ((l)localObject1).T = ((JSONObject)localObject3).optString("md5");
          ((l)localObject1).f = ((JSONObject)localObject3).optString("d");
          ((l)localObject1).L = ((JSONObject)localObject3).optString("icon");
          ((l)localObject1).g = (((JSONObject)localObject3).optDouble("priceDownload") + "");
          localObject3 = ((l)localObject1).W.split(".");
          if (localObject3.length > 1)
          {
            if (Integer.valueOf(localObject3[1]).intValue() <= 0) {
              ((l)localObject1).W = localObject3[0];
            }
            localObject3 = ((l)localObject1).g.split(".");
            if (localObject3.length > 1)
            {
              if (Integer.valueOf(localObject3[1]).intValue() <= 0) {
                ((l)localObject1).g = localObject3[0];
              }
              localArrayList.add(localObject1);
              i += 1;
            }
          }
          else
          {
            ((l)localObject1).W = (Double.valueOf(((l)localObject1).W).intValue() + "");
            continue;
          }
          localException.g = (Double.valueOf(localException.g).intValue() + "");
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return localArrayList;
        }
      }
    }
    return localArrayList;
  }
  
  public static final List<l> a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      paramContext = paramContext.getInstalledPackages(128);
      localArrayList2 = new ArrayList();
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        localArrayList2.add(((PackageInfo)paramContext.next()).packageName);
      }
    }
    catch (Exception paramContext)
    {
      ArrayList localArrayList2;
      for (;;)
      {
        paramContext = new ArrayList();
      }
      paramContext = a().iterator();
      while (paramContext.hasNext())
      {
        l localL = (l)paramContext.next();
        if (!localArrayList2.contains(localL.F)) {
          localArrayList1.add(localL);
        }
      }
    }
    return localArrayList1;
  }
  
  public String f()
  {
    return "pa_" + this.F + "_" + this.E + "_" + this.I;
  }
  
  public JSONObject m()
  {
    JSONObject localJSONObject = super.m();
    try
    {
      localJSONObject.put("f", this.a);
      localJSONObject.put("h", this.b);
      localJSONObject.put("m", this.c);
      localJSONObject.put("sort", this.e);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
}
