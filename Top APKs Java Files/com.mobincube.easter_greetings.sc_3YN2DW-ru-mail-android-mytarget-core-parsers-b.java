package ru.mail.android.mytarget.core.parsers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.android.mytarget.Tracer;

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
  
  public static void a(String paramString, ru.mail.android.mytarget.core.models.b paramB, ru.mail.android.mytarget.core.a paramA, ru.mail.android.mytarget.core.models.c paramC, Context paramContext)
  {
    if (paramString != null) {
      paramString = paramString.trim();
    }
    for (;;)
    {
      if ((paramString != null) && (!paramString.equals("")))
      {
        if (paramString.indexOf("<!doctype html>") == 0)
        {
          int i = paramString.indexOf("bannersJSON:");
          if (i >= 0)
          {
            i += 12;
            int j = paramString.indexOf("{", i);
            if (j >= i)
            {
              int k = paramString.indexOf("};", j);
              if (k >= j + 1)
              {
                int m = paramString.indexOf("</script>", k);
                if (m >= k)
                {
                  String str = (paramString.substring(0, i) + "''};" + paramString.substring(m)).replace("\"", "'");
                  paramString = "{\"html_wrapper\":\"" + str + "\"," + paramString.substring(j + 1, k);
                }
              }
            }
          }
        }
        while (c.a(paramString))
        {
          Tracer.d("Parsing XML...");
          Tracer.d("parse VAST");
          paramA = new a.a(paramContext);
          paramA.f = c.class.getSimpleName();
          paramA.c = b.class.getName();
          paramA.b = paramB.b();
          paramA.d = "Parsing VAST";
          paramA.e = "no unit";
          try
          {
            c.a(paramString, paramB, paramC, paramA);
            return;
          }
          catch (c.a paramString)
          {
            Tracer.d("parse VAST error. message: " + paramString.getMessage());
            a.b(paramString.getMessage(), paramA, "Convert to XML error");
            return;
          }
        }
        Tracer.d("Converting to JSON...");
        try
        {
          paramString = new JSONObject(paramString);
          Tracer.d("done");
          if (a(paramString))
          {
            Tracer.d("parse json");
            ru.mail.android.mytarget.core.parsers.rb.c.a(paramString, paramB, paramA.d(), a(paramContext), paramContext, paramC);
            if (paramA.e()) {
              paramB.h();
            }
            Tracer.d("json parsing finished");
            return;
          }
        }
        catch (Exception paramString)
        {
          Tracer.d("convert to JSON error: " + paramString.getMessage());
          ru.mail.android.mytarget.core.async.a.a("Convert to JSON error", b.class.getName(), 40, "JSON Exception: Convert to JSON error", paramB.b(), paramContext);
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
