package com.dianxinos.dxservice.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import com.dianxinos.DXStatService.stat.TokenManager;
import com.dianxinos.dxservice.stat.h;
import com.dianxinos.dxservice.stat.j;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  public static String a = "others";
  public static boolean b;
  public static boolean c;
  public static boolean d = c;
  public static boolean e = d;
  public static final HashMap<String, String> f = new HashMap();
  public static boolean g;
  
  static
  {
    f.put("feedback", "http://pasta.dianxinos.com/feedback");
    f.put("appInfo", "http://pasta.dianxinos.com/api/tokens");
    f.put("data", "http://pasta.dianxinos.com/api/data");
    f.put("token", "http://pasta.dianxinos.com/api/tokens");
  }
  
  public static long a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("rt", 0).getLong(paramString, 0L);
  }
  
  public static String a(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      localObject = new String(b.a.a.a.a.a.a.a.a(((MessageDigest)localObject).digest()), "UTF-8");
      return localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      if (e)
      {
        Log.e("stat.CommonUtils", "Encoding#2 not found.", localUnsupportedEncodingException);
        return paramString;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      if (e) {
        Log.e("stat.CommonUtils", "Encoding#1 not found.", localNoSuchAlgorithmException);
      }
    }
    return paramString;
  }
  
  public static String a(String paramString, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = TokenManager.getToken(paramContext);
    String str = h.a(paramContext, j.b());
    if ("data".equals(paramString)) {
      a("token", str, localArrayList);
    }
    a("tk", paramContext, localArrayList);
    a("sv", com.dianxinos.dxservice.a.a(), localArrayList);
    paramContext = new StringBuilder();
    paramContext.append("?");
    paramContext.append(URLEncodedUtils.format(localArrayList, "UTF-8"));
    paramContext = paramContext.toString();
    if ("feedback".equals(paramString))
    {
      if (b)
      {
        paramString = new StringBuilder();
        paramString.append("http://sandbox.sjws.baidu.com:8080/statistics_feedback");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("booster"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.ds.duapps.com/feedback");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("battery"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.sd.duapps.com/feedback");
        paramString.append(paramContext);
        return paramString.toString();
      }
      paramString = new StringBuilder();
      paramString.append((String)f.get("feedback"));
      paramString.append(paramContext);
      return paramString.toString();
    }
    if ("appInfo".equals(paramString))
    {
      if (b)
      {
        paramString = new StringBuilder();
        paramString.append("http://sandbox.sjws.baidu.com:8080/api/tokens");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("booster"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.ds.duapps.com/api/tokens");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("battery"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.sd.duapps.com/api/tokens");
        paramString.append(paramContext);
        return paramString.toString();
      }
      paramString = new StringBuilder();
      paramString.append((String)f.get("appInfo"));
      paramString.append(paramContext);
      return paramString.toString();
    }
    if ("data".equals(paramString))
    {
      if (b)
      {
        paramString = new StringBuilder();
        paramString.append("http://sandbox.sjws.baidu.com:8080/api/data");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("booster"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.ds.duapps.com/api/data");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("battery"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.sd.duapps.com/api/data");
        paramString.append(paramContext);
        return paramString.toString();
      }
      paramString = new StringBuilder();
      paramString.append((String)f.get("data"));
      paramString.append(paramContext);
      return paramString.toString();
    }
    if ("token".equals(paramString))
    {
      if (b)
      {
        paramString = new StringBuilder();
        paramString.append("http://sandbox.sjws.baidu.com:8080/api/tokens");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("booster"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.ds.duapps.com/api/tokens");
        paramString.append(paramContext);
        return paramString.toString();
      }
      if (a.equals("battery"))
      {
        paramString = new StringBuilder();
        paramString.append("http://pasta.sd.duapps.com/api/tokens");
        paramString.append(paramContext);
        return paramString.toString();
      }
      paramString = new StringBuilder();
      paramString.append((String)f.get("token"));
      paramString.append(paramContext);
      return paramString.toString();
    }
    return "";
  }
  
  public static String a(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }
  
  public static JSONObject a(String paramString, Number paramNumber)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(paramString, paramNumber);
      return localJSONObject;
    }
    catch (JSONException paramString) {}
    return localJSONObject;
  }
  
  public static void a(SharedPreferences.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.commit();
      return;
    }
    catch (Exception paramEditor) {}
  }
  
  private static void a(String paramString1, String paramString2, List<NameValuePair> paramList)
  {
    if (!TextUtils.isEmpty(paramString2)) {
      paramList.add(new BasicNameValuePair(paramString1, paramString2));
    }
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getActiveNetworkInfo();
    }
    catch (Exception paramContext)
    {
      boolean bool1;
      for (;;) {}
    }
    paramContext = null;
    bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.isConnected()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean a(Context paramContext, int paramInt)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.getType() == paramInt)
      {
        bool1 = bool2;
        if (paramContext.isConnectedOrConnecting()) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static long b(Context paramContext)
  {
    return paramContext.getSharedPreferences("rt", 0).getLong("rnet", 0L);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong(paramString, System.currentTimeMillis());
    paramContext.commit();
  }
  
  private static boolean b(String paramString)
  {
    paramString = paramString.split("\\.");
    return (paramString.length > 1) && (paramString[1].equals("dianxinos"));
  }
  
  public static void c(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong("rnet", System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static boolean d(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    com.dianxinos.library.dxbase.c localC = com.dianxinos.library.dxbase.c.a(paramContext);
    String str1 = localC.b("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
    String str2 = paramContext.getPackageName();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(0);
      paramContext = null;
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        localObject = ((ApplicationInfo)localIterator.next()).packageName;
        if ("com.dianxinos.dxservice".equals(localObject))
        {
          i = 0;
          j = 1;
          break label130;
        }
        if (((String)localObject).equals(str1))
        {
          j = 0;
          i = 1;
          break label130;
        }
        if ((b((String)localObject)) && ((paramContext == null) || (paramContext.compareTo((String)localObject) > 0))) {
          paramContext = (Context)localObject;
        }
      }
      int i = 0;
      int j = i;
      label130:
      if (i != 0)
      {
        if (str2.equals(str1)) {
          return true;
        }
      }
      else if (j != 0)
      {
        if (str2.equals("com.dianxinos.dxservice"))
        {
          localC.a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str2);
          return true;
        }
      }
      else {
        if ((paramContext == null) || (str2.equals(paramContext))) {
          break label192;
        }
      }
      return false;
      label192:
      localC.a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str2);
      return true;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static void e(Context paramContext)
  {
    paramContext.getContentResolver();
    com.dianxinos.library.dxbase.c.a(paramContext).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
  }
}
