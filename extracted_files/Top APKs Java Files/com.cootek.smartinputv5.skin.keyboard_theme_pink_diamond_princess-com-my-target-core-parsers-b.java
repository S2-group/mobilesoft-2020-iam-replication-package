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
    if ((paramString != null) && (!paramString.equals("")))
    {
      Object localObject = paramString;
      if (paramString.indexOf("<!doctype html>") == 0)
      {
        int i = paramString.indexOf("bannersJSON:");
        localObject = paramString;
        if (i >= 0)
        {
          i += 12;
          int k = paramString.indexOf("{", i);
          localObject = paramString;
          if (k >= i)
          {
            int j = paramString.indexOf("};", k);
            k += 1;
            localObject = paramString;
            if (j >= k)
            {
              int m = paramString.indexOf("</script>", j);
              localObject = paramString;
              if (m >= j)
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append(paramString.substring(0, i));
                ((StringBuilder)localObject).append("''};");
                ((StringBuilder)localObject).append(paramString.substring(m));
                localObject = ((StringBuilder)localObject).toString().replace("\"", "'");
                StringBuilder localStringBuilder = new StringBuilder("{\"html_wrapper\":\"");
                localStringBuilder.append((String)localObject);
                localStringBuilder.append("\",");
                localStringBuilder.append(paramString.substring(k, j));
                localObject = localStringBuilder.toString();
              }
            }
          }
        }
      }
      if (c.a((String)localObject))
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
          c.a((String)localObject, paramC, paramD, paramA);
          return;
        }
        catch (c.a paramString)
        {
          paramC = paramString.getMessage();
          paramString = new StringBuilder("parse VAST error. message: ");
          paramString.append(paramC);
          Tracer.d(paramString.toString());
          paramString = paramC;
          if (paramC.contains("(")) {
            paramString = paramC.substring(0, paramC.indexOf("("));
          }
          paramC = new StringBuilder();
          paramC.append(paramString);
          paramC.append(", Operation: ");
          paramC.append(paramA.d);
          paramC.append(", Unit: ");
          paramC.append(paramA.e);
          com.my.target.core.async.a.a(paramC.toString(), paramA.c, "VAST Exception:  Convert to XML error", (String)localObject, paramA.b, paramA.a);
          return;
        }
      }
      Tracer.d("Converting to JSON...");
      try
      {
        paramString = new JSONObject((String)localObject);
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
        Tracer.d("invalid json version");
        return;
      }
      catch (Exception paramString)
      {
        paramA = new StringBuilder("convert to JSON error: ");
        paramA.append(paramString.getMessage());
        Tracer.d(paramA.toString());
        com.my.target.core.async.a.a("Convert to JSON error", b.class.getName(), "JSON Exception: Convert to JSON error", (String)localObject, paramC.b(), paramContext);
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
      StringBuilder localStringBuilder = new StringBuilder("json version: ");
      localStringBuilder.append(paramJSONObject);
      Tracer.d(localStringBuilder.toString());
      int i = paramJSONObject.indexOf(".");
      if (i > 0)
      {
        paramJSONObject = paramJSONObject.substring(0, i);
        i = Integer.parseInt(paramJSONObject, 10);
        if (i == 2) {
          return true;
        }
      }
      return false;
    }
    catch (JSONException|NumberFormatException paramJSONObject) {}
    return false;
  }
}
