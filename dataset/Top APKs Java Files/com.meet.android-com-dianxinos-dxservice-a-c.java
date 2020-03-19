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
import com.dianxinos.a.a.d;
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
  public static boolean b = false;
  public static boolean c = false;
  public static boolean d = c;
  public static boolean e = d;
  public static final HashMap<String, String> f = new HashMap();
  public static boolean g = false;
  
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
      localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      localObject = new String(b.a.a.a.a.a.a.a.a(((MessageDigest)localObject).digest()), "UTF-8");
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      do
      {
        localObject = paramString;
      } while (!e);
      Log.e("stat.CommonUtils", "Encoding#1 not found.", localNoSuchAlgorithmException);
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      do
      {
        Object localObject = paramString;
      } while (!e);
      Log.e("stat.CommonUtils", "Encoding#2 not found.", localUnsupportedEncodingException);
    }
    return paramString;
  }
  
  public static String a(String paramString, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = d.a(paramContext);
    String str = h.a(paramContext, j.b());
    if ("data".equals(paramString)) {
      a("token", str, localArrayList);
    }
    a("tk", paramContext, localArrayList);
    a("sv", com.dianxinos.dxservice.a.a(), localArrayList);
    paramContext = "?" + URLEncodedUtils.format(localArrayList, "UTF-8");
    if ("feedback".equals(paramString))
    {
      if (b) {
        return "http://sandbox.sjws.baidu.com:8080/statistics_feedback" + paramContext;
      }
      if (a.equals("booster")) {
        return "http://pasta.ds.duapps.com/feedback" + paramContext;
      }
      if (a.equals("battery")) {
        return "http://pasta.sd.duapps.com/feedback" + paramContext;
      }
      return (String)f.get("feedback") + paramContext;
    }
    if ("appInfo".equals(paramString))
    {
      if (b) {
        return "http://sandbox.sjws.baidu.com:8080/api/tokens" + paramContext;
      }
      if (a.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/tokens" + paramContext;
      }
      if (a.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/tokens" + paramContext;
      }
      return (String)f.get("appInfo") + paramContext;
    }
    if ("data".equals(paramString))
    {
      if (b) {
        return "http://sandbox.sjws.baidu.com:8080/api/data" + paramContext;
      }
      if (a.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/data" + paramContext;
      }
      if (a.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/data" + paramContext;
      }
      return (String)f.get("data") + paramContext;
    }
    if ("token".equals(paramString))
    {
      if (b) {
        return "http://sandbox.sjws.baidu.com:8080/api/tokens" + paramContext;
      }
      if (a.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/tokens" + paramContext;
      }
      if (a.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/tokens" + paramContext;
      }
      return (String)f.get("token") + paramContext;
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
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected()))
      {
        bool = true;
        return bool;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
        continue;
        boolean bool = false;
      }
    }
  }
  
  public static boolean a(Context paramContext, int paramInt)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.getType() == paramInt) && (paramContext.isConnectedOrConnecting())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
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
    com.dianxinos.b.a.c localC = com.dianxinos.b.a.c.a(paramContext);
    String str2 = localC.b("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
    String str3 = paramContext.getPackageName();
    for (;;)
    {
      String str1;
      int i;
      int j;
      try
      {
        localObject = ((PackageManager)localObject).getInstalledApplications(0);
        paramContext = null;
        Iterator localIterator = ((List)localObject).iterator();
        if (!localIterator.hasNext()) {
          break label214;
        }
        str1 = ((ApplicationInfo)localIterator.next()).packageName;
        if ("com.dianxinos.dxservice".equals(str1))
        {
          i = 1;
          j = 0;
          if (j == 0) {
            break label153;
          }
          if (!str3.equals(str2)) {
            break label207;
          }
          return true;
        }
      }
      catch (Exception paramContext)
      {
        return true;
      }
      if (str1.equals(str2))
      {
        i = 0;
        j = 1;
      }
      else
      {
        if (b(str1))
        {
          localObject = str1;
          if (paramContext != null) {
            if (paramContext.compareTo(str1) <= 0) {
              break label209;
            }
          }
        }
        label153:
        label207:
        label209:
        for (localObject = str1;; localObject = paramContext)
        {
          paramContext = (Context)localObject;
          break;
          if (i != 0)
          {
            if (str3.equals("com.dianxinos.dxservice"))
            {
              localC.a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
              return true;
            }
          }
          else if ((paramContext == null) || (str3.equals(paramContext)))
          {
            localC.a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
            return true;
          }
          return false;
        }
        label214:
        i = 0;
        j = 0;
      }
    }
  }
  
  public static void e(Context paramContext)
  {
    paramContext.getContentResolver();
    com.dianxinos.b.a.c.a(paramContext).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
  }
}
