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
import ru.mail.android.mytarget.core.AdParams;
import ru.mail.android.mytarget.core.async.Sender;
import ru.mail.android.mytarget.core.models.AdData;
import ru.mail.android.mytarget.core.models.AdService;
import ru.mail.android.mytarget.core.parsers.rb.RBJSONParser;

public class ResponseParser
{
  private static final String HTML_SIGN = "<!doctype html>";
  private static final String JSON_END_TOKEN = "};";
  private static final String JSON_START_TOKEN = "{";
  private static final String JSON_TOKEN = "bannersJSON:";
  private static final String SCRIPT_TOKEN = "</script>";
  public static final int VERSION = 2;
  private static final String WRAPPER_TOKEN = "\"html_wrapper\":";
  
  private ResponseParser() {}
  
  private static boolean checkVersion(JSONObject paramJSONObject)
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
  
  private static String clearFromHTMLCode(String paramString)
  {
    String str = paramString;
    if (paramString.indexOf("<!doctype html>") == 0)
    {
      int i = paramString.indexOf("bannersJSON:");
      str = paramString;
      if (i >= 0)
      {
        i += "bannersJSON:".length();
        int j = paramString.indexOf("{", i);
        str = paramString;
        if (j >= i)
        {
          int k = paramString.indexOf("};", j);
          str = paramString;
          if (k >= "{".length() + j)
          {
            int m = paramString.indexOf("</script>", k);
            str = paramString;
            if (m >= k)
            {
              str = (paramString.substring(0, i) + "''" + "};" + paramString.substring(m)).replace("\"", "'");
              str = "{\"html_wrapper\":\"" + str + "\"," + paramString.substring("{".length() + j, k);
            }
          }
        }
      }
    }
    return str;
  }
  
  private static ArrayList<String> getInstalledAppBundles(Context paramContext)
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
  
  private static void parseJsonData(JSONObject paramJSONObject, AdData paramAdData, AdParams paramAdParams, AdService paramAdService, Context paramContext)
  {
    Tracer.d("parse json");
    RBJSONParser.parse(paramJSONObject, paramAdData, paramAdParams.getFormat(), getInstalledAppBundles(paramContext), paramContext, paramAdService);
    if (paramAdParams.isCheckExclude()) {
      paramAdData.checkExclude();
    }
    Tracer.d("json parsing finished");
  }
  
  public static void parseResponse(String paramString, AdData paramAdData, AdParams paramAdParams, AdService paramAdService, Context paramContext)
  {
    Object localObject = paramString;
    if (paramString != null) {
      localObject = paramString.trim();
    }
    if ((localObject != null) && (!((String)localObject).equals("")))
    {
      paramString = clearFromHTMLCode((String)localObject);
      if (VASTParser.isVast(paramString))
      {
        Tracer.d("Parsing XML...");
        parseVASTdata(paramString, paramAdData, paramAdService, paramContext);
        return;
      }
      Tracer.d("Converting to JSON...");
      try
      {
        localObject = new JSONObject(paramString);
        Tracer.d("done");
        if (checkVersion((JSONObject)localObject))
        {
          parseJsonData((JSONObject)localObject, paramAdData, paramAdParams, paramAdService, paramContext);
          return;
        }
      }
      catch (Exception paramAdParams)
      {
        Tracer.d("convert to JSON error: " + paramAdParams.getMessage());
        Sender.sendLogMessageWithData("Convert to JSON error", ResponseParser.class.getName(), 40, "JSON Exception: Convert to JSON error", paramString, paramAdData.getUrl(), paramContext);
        return;
      }
      Tracer.d("invalid json version");
      return;
    }
    Tracer.d("data is empty");
  }
  
  private static void parseVASTdata(String paramString, AdData paramAdData, AdService paramAdService, Context paramContext)
  {
    Tracer.d("parse VAST");
    paramContext = new ParseErrorMessages.SenderContainer(paramContext);
    paramContext.className = VASTParser.class.getSimpleName();
    paramContext.loggerClass = ResponseParser.class.getName();
    paramContext.url = paramAdData.getUrl();
    paramContext.operation = "Parsing VAST";
    paramContext.unit = "no unit";
    try
    {
      VASTParser.parseVast(paramString, paramAdData, paramAdService, paramContext);
      return;
    }
    catch (VASTParser.VASTExeption paramString)
    {
      Tracer.d("parse VAST error. message: " + paramString.getMessage());
      ParseErrorMessages.errorVASTMessage(paramString.getMessage(), paramContext, null, "Convert to XML error");
    }
  }
}
