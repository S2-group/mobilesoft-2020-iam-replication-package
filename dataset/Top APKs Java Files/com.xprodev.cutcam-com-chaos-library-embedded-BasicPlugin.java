package com.chaos.library.embedded;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.chaos.library.b;
import com.chaos.library.l;
import com.chaos.library.l.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.interlaken.common.g.aa;
import org.json.JSONException;
import org.json.JSONObject;

public class BasicPlugin
  extends com.chaos.library.d
{
  private final String TAG = "BasicPlugin";
  
  public BasicPlugin(Context paramContext, com.chaos.library.c.a paramA)
  {
    super(paramContext, paramA);
  }
  
  private HashMap<String, String> parseData(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.split("&");
    if (paramString != null)
    {
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String[] arrayOfString = paramString[i].split("=");
        if ((arrayOfString != null) && (arrayOfString.length == 2)) {
          localHashMap.put(arrayOfString[0], arrayOfString[1]);
        }
        i += 1;
      }
    }
    return localHashMap;
  }
  
  public void destroy() {}
  
  public String exec(String paramString, JSONObject paramJSONObject, b paramB)
  {
    if (paramString == null) {
      return null;
    }
    boolean bool = a.a.equals(paramString);
    int i = 0;
    if (bool) {
      try
      {
        if (getContext() == null) {
          return "";
        }
        paramString = getContext().getApplicationContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
    if (a.b.equals(paramString)) {
      try
      {
        if (getContext() == null) {
          return "";
        }
        paramString = new StringBuilder();
        paramString.append(getContext().getApplicationContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode);
        paramString = paramString.toString();
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
    if (a.c.equals(paramString)) {
      try
      {
        if (getContext() == null) {
          return "";
        }
        paramString = new StringBuilder();
        getContext();
        paramString.append(org.interlaken.common.c.d.d());
        paramString = paramString.toString();
        return paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
    if (paramString.equals(a.g))
    {
      getEngine().c();
      return null;
    }
    if (paramString.equals(a.e))
    {
      paramB = "";
      paramString = paramB;
      if (paramJSONObject != null) {
        try
        {
          paramString = paramJSONObject.getString("packageName");
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
          paramString = paramB;
        }
      }
      paramJSONObject = getContext().getPackageManager().getInstalledPackages(0);
      paramB = new ArrayList();
      if (paramJSONObject != null) {
        while (i < paramJSONObject.size())
        {
          paramB.add(((PackageInfo)paramJSONObject.get(i)).packageName);
          i += 1;
        }
      }
      if (paramB.contains(paramString)) {
        return "TRUE";
      }
      return "FALSE";
    }
    if (paramString.equals(a.d))
    {
      getEngine().d();
      return null;
    }
    if (paramString.equals(a.f))
    {
      paramB = "";
      paramString = paramB;
      if (paramJSONObject != null) {
        try
        {
          paramString = paramJSONObject.getString("content");
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
          paramString = paramB;
        }
      }
      ((ClipboardManager)getContext().getSystemService("clipboard")).setText(paramString);
      return null;
    }
    if (paramString.equals(a.i)) {
      return getInfo();
    }
    if (paramString.equals(a.j))
    {
      paramB.a(new l(l.a.b, new String[] { "1111", "2222", "333" }));
      return null;
    }
    if (paramString.equals(a.k))
    {
      paramB.a(new l(l.a.b, new String[] { "1111" }));
      return null;
    }
    if ((!paramString.equals(a.l)) && (paramString.equals(a.h)))
    {
      paramB = "";
      paramString = paramB;
      if (paramJSONObject != null) {
        try
        {
          paramString = paramJSONObject.getString("content");
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
          paramString = paramB;
        }
      }
      Toast.makeText(getContext(), paramString, 0).show();
    }
    return null;
  }
  
  public String getInfo()
  {
    String str1 = aa.a().b(getContext());
    Object localObject = aa.a().a(getContext());
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localObject = parseData((String)localObject);
    }
    try
    {
      str2 = (String)((HashMap)localObject).get("versionCode");
      str3 = (String)((HashMap)localObject).get("versionName");
      str4 = (String)((HashMap)localObject).get("model");
      str5 = (String)((HashMap)localObject).get("manufacturer");
      str6 = (String)((HashMap)localObject).get("sdk");
      str7 = (String)((HashMap)localObject).get("net");
      str8 = (String)((HashMap)localObject).get("ccode");
      str9 = (String)((HashMap)localObject).get("locale");
      str10 = (String)((HashMap)localObject).get("packageName");
      str11 = (String)((HashMap)localObject).get("screenWidth");
      str12 = (String)((HashMap)localObject).get("screenHeight");
      str13 = (String)((HashMap)localObject).get("screenDpi");
      str14 = (String)((HashMap)localObject).get("localTime");
      localObject = (String)((HashMap)localObject).get("localZone");
      localJSONObject = new JSONObject();
      if (TextUtils.isEmpty(str1)) {
        break label505;
      }
      localJSONObject.put("desp", str1);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        JSONObject localJSONObject;
      }
    }
    if (!TextUtils.isEmpty(str2)) {
      localJSONObject.put("versionCode", str2);
    }
    if (!TextUtils.isEmpty(str3)) {
      localJSONObject.put("versionName", str3);
    }
    if (!TextUtils.isEmpty(str4)) {
      localJSONObject.put("model", str4);
    }
    if (!TextUtils.isEmpty(str5)) {
      localJSONObject.put("manufacturer", str5);
    }
    if (!TextUtils.isEmpty(str6)) {
      localJSONObject.put("sdk", str6);
    }
    if (!TextUtils.isEmpty(str7)) {
      localJSONObject.put("net", str7);
    }
    if (!TextUtils.isEmpty(str8)) {
      localJSONObject.put("ccode", str8);
    }
    if (!TextUtils.isEmpty(str9)) {
      localJSONObject.put("locale", str9);
    }
    if (!TextUtils.isEmpty(str10)) {
      localJSONObject.put("packageName", str10);
    }
    if (!TextUtils.isEmpty(str11)) {
      localJSONObject.put("screenWidth", str11);
    }
    if (!TextUtils.isEmpty(str12)) {
      localJSONObject.put("screenHeight", str12);
    }
    if (!TextUtils.isEmpty(str13)) {
      localJSONObject.put("screenDpi", str13);
    }
    if (!TextUtils.isEmpty(str14)) {
      localJSONObject.put("localTime", str14);
    }
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localJSONObject.put("localZone", localObject);
    }
    localObject = localJSONObject.toString();
    return localObject;
    return "";
  }
  
  public String getVersion()
  {
    return "1.0.0";
  }
}
