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
    if (paramString != null) {
      paramString = paramString.trim();
    }
    for (;;)
    {
      if ((paramString != null) && (!paramString.equals("")))
      {
        String str = paramString;
        if (paramString.indexOf("<!doctype html>") == 0)
        {
          int i = paramString.indexOf("bannersJSON:");
          str = paramString;
          if (i >= 0)
          {
            i += 12;
            int j = paramString.indexOf("{", i);
            str = paramString;
            if (j >= i)
            {
              int k = paramString.indexOf("};", j);
              str = paramString;
              if (k >= j + 1)
              {
                int m = paramString.indexOf("</script>", k);
                str = paramString;
                if (m >= k)
                {
                  str = (paramString.substring(0, i) + "''};" + paramString.substring(m)).replace("\"", "'");
                  str = "{\"html_wrapper\":\"" + str + "\"," + paramString.substring(j + 1, k);
                }
              }
            }
          }
        }
        if (c.a(str))
        {
          Tracer.d("Parsing XML...");
          Tracer.d("parse VAST");
          paramA = new a.a(paramContext);
          paramA.f = c.class.getSimpleName();
          paramA.c = b.class.getName();
          paramA.b = paramC.b();
          paramA.d = "Parsing VAST";
          paramA.e = "no unit";
          try
          {
            c.a(str, paramC, paramD, paramA);
            return;
          }
          catch (c.a paramString)
          {
            paramC = paramString.getMessage();
            Tracer.d("parse VAST error. message: " + paramC);
            paramString = paramC;
            if (paramC.contains("(")) {
              paramString = paramC.substring(0, paramC.indexOf("("));
            }
            com.my.target.core.async.a.a(paramString + ", Operation: " + paramA.d + ", Unit: " + paramA.e, paramA.c, "VAST Exception:  Convert to XML error", str, paramA.b, paramA.a);
            return;
          }
        }
        Tracer.d("Converting to JSON...");
        try
        {
          paramString = new JSONObject(str);
          Tracer.d("done");
          if (a(paramString))
          {
            Tracer.d("parse json");
            com.my.target.core.parsers.rb.c.a(paramString, paramC, paramA.c(), a(paramContext), paramContext, paramD);
            if (paramA.e()) {
              paramC.h();
            }
            Tracer.d("json parsing finished");
            return;
          }
        }
        catch (Exception paramString)
        {
          Tracer.d("convert to JSON error: " + paramString.getMessage());
          com.my.target.core.async.a.a("Convert to JSON error", b.class.getName(), "JSON Exception: Convert to JSON error", str, paramC.b(), paramContext);
          return;
        }
        Tracer.d("invalid json version");
        return;
      }
      Tracer.d("data is empty");
      return;
    }
  }
  
  private static boolean a(JSONObject paramJSONObject)
  {
    boolean bool2 = false;
    try
    {
      paramJSONObject = paramJSONObject.getString("version");
      Tracer.d("json version: " + paramJSONObject);
      int i = paramJSONObject.indexOf(".");
      boolean bool1 = bool2;
      if (i > 0) {
        paramJSONObject = paramJSONObject.substring(0, i);
      }
      try
      {
        i = Integer.parseInt(paramJSONObject, 10);
        bool1 = bool2;
        if (i == 2) {
          bool1 = true;
        }
        return bool1;
      }
      catch (NumberFormatException paramJSONObject)
      {
        return false;
      }
      return false;
    }
    catch (JSONException paramJSONObject) {}
  }
}
