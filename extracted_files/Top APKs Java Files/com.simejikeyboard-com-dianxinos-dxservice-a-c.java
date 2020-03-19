package com.dianxinos.dxservice.a;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.dianxinos.DXStatService.stat.TokenManager;
import com.dianxinos.a.a.f;
import com.dianxinos.dxservice.stat.s;
import com.dianxinos.dxservice.stat.u;
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
    paramContext = TokenManager.getToken(paramContext);
    String str = s.a(paramContext, u.b());
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
    Object localObject1 = paramContext.getContentResolver();
    Object localObject4 = paramContext.getPackageManager();
    String str1 = Settings.System.getString((ContentResolver)localObject1, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}");
    String str2 = paramContext.getPackageName();
    Object localObject3 = null;
    localObject1 = null;
    for (;;)
    {
      try
      {
        Iterator localIterator = ((PackageManager)localObject4).getInstalledApplications(0).iterator();
        localObject3 = localObject1;
        localObject4 = localObject1;
        if (!localIterator.hasNext()) {
          continue;
        }
        localObject3 = localObject1;
        localObject4 = ((ApplicationInfo)localIterator.next()).packageName;
        localObject3 = localObject1;
        boolean bool = "com.dianxinos.dxservice".equals(localObject4);
        if (!bool) {
          continue;
        }
        i = 1;
        j = 0;
      }
      catch (Exception localException)
      {
        localObject4 = localObject3;
        if (!e) {
          continue;
        }
        Log.e("stat.CommonUtils", "Failed to get install applications", localException);
        localObject4 = localObject3;
        int i = 0;
        int j = 0;
        localObject2 = localObject4;
        continue;
        if (i == 0) {
          continue;
        }
        if (!str2.equals("com.dianxinos.dxservice")) {
          break label288;
        }
        f.a(paramContext).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str2);
        return true;
        if ((localObject2 != null) && (!str2.equals(localObject2))) {
          break label288;
        }
        f.a(paramContext).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str2);
        return true;
      }
      if (j == 0) {
        continue;
      }
      if (!str2.equals(str1)) {
        break label288;
      }
      return true;
      localObject3 = localObject1;
      if (!((String)localObject4).equals(str1)) {
        continue;
      }
      i = 0;
      j = 1;
    }
    localObject3 = localObject1;
    if (b((String)localObject4))
    {
      localObject3 = localObject4;
      if (localObject1 != null)
      {
        localObject3 = localObject1;
        i = ((String)localObject1).compareTo((String)localObject4);
        if (i <= 0) {
          break label290;
        }
      }
    }
    Object localObject2;
    label288:
    label290:
    for (localObject3 = localObject4;; localObject3 = localObject2)
    {
      localObject1 = localObject3;
      break;
      return false;
    }
  }
  
  public static void e(Context paramContext)
  {
    paramContext.getContentResolver();
    f.a(paramContext).a("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
  }
}
