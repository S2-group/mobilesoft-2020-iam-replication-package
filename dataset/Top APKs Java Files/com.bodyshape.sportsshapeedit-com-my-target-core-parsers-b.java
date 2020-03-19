package com.my.target.core.parsers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.my.target.Tracer;
import com.my.target.core.models.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class b
{
  private b() {}
  
  private static ArrayList<String> a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.flags & 0x1) == 0) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    return paramContext;
  }
  
  public static void a(String paramString, com.my.target.core.models.c paramC, com.my.target.core.a paramA, d paramD, Context paramContext)
  {
    Object localObject = paramString;
    if (paramString != null) {
      localObject = paramString.trim();
    }
    if ((localObject != null) && (!((String)localObject).equals("")))
    {
      if (((String)localObject).indexOf("<!doctype html>") == 0)
      {
        int i = ((String)localObject).indexOf("bannersJSON:");
        if (i >= 0)
        {
          i += 12;
          int j = ((String)localObject).indexOf("{", i);
          if (j >= i)
          {
            int k = ((String)localObject).indexOf("};", j);
            if (k >= j + 1)
            {
              int m = ((String)localObject).indexOf("</script>", k);
              if (m >= k)
              {
                paramString = (((String)localObject).substring(0, i) + "''};" + ((String)localObject).substring(m)).replace("\"", "'");
                paramString = "{\"html_wrapper\":\"" + paramString + "\"," + ((String)localObject).substring(j + 1, k);
                break label201;
              }
            }
          }
        }
      }
      paramString = (String)localObject;
      label201:
      if (c.a(paramString))
      {
        Tracer.d("Parsing XML...");
        Tracer.d("parse VAST");
        paramContext = new a.a(paramContext);
        paramContext.f = c.class.getSimpleName();
        paramContext.c = b.class.getName();
        paramContext.b = paramC.b();
        paramContext.d = "Parsing VAST";
        paramContext.e = "no unit";
        try
        {
          c.a(paramString, paramC, paramD, paramContext);
          return;
        }
        catch (c.a paramC)
        {
          paramA = paramC.getMessage();
          Tracer.d("parse VAST error. message: " + paramA);
          paramC = paramA;
          if (paramA.contains("(")) {
            paramC = paramA.substring(0, paramA.indexOf("("));
          }
          com.my.target.core.async.a.a(paramC + ", Operation: " + paramContext.d + ", Unit: " + paramContext.e, paramContext.c, "VAST Exception:  Convert to XML error", paramString, paramContext.b, paramContext.a);
          return;
        }
      }
      Tracer.d("Converting to JSON...");
      try
      {
        localObject = new JSONObject(paramString);
        Tracer.d("done");
        if (a((JSONObject)localObject))
        {
          Tracer.d("parse json");
          com.my.target.core.parsers.rb.c.a((JSONObject)localObject, paramC, paramA.c(), a(paramContext), paramContext, paramD);
          if (paramA.e()) {
            paramC.h();
          }
          Tracer.d("json parsing finished");
          return;
        }
        Tracer.d("invalid json version");
        return;
      }
      catch (Exception paramA)
      {
        Tracer.d("convert to JSON error: " + paramA.getMessage());
        com.my.target.core.async.a.a("Convert to JSON error", b.class.getName(), "JSON Exception: Convert to JSON error", paramString, paramC.b(), paramContext);
        return;
      }
    }
    Tracer.d("data is empty");
  }
  
  private static boolean a(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("version");
      Tracer.d("json version: " + paramJSONObject);
      int i = paramJSONObject.indexOf(".");
      if (i > 0)
      {
        paramJSONObject = paramJSONObject.substring(0, i);
        i = Integer.parseInt(paramJSONObject, 10);
        if (i == 2) {
          return true;
        }
      }
    }
    catch (JSONException|NumberFormatException paramJSONObject)
    {
      for (;;) {}
    }
    return false;
  }
}
