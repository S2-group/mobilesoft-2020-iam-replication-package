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
import com.dianxinos.dxservice.stat.k;
import com.dianxinos.dxservice.stat.m;
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
  public static boolean DEBUG;
  public static boolean bGe;
  public static boolean bGf;
  public static boolean bGg;
  public static String bJQ = "others";
  public static final HashMap<String, String> bJR;
  public static boolean bJS;
  
  static
  {
    DEBUG = false;
    bGg = false;
    bGf = bGg;
    bGe = bGf;
    bJR = new HashMap();
    bJS = false;
    bJR.put("feedback", "http://pasta.dianxinos.com/feedback");
    bJR.put("appInfo", "http://pasta.dianxinos.com/api/tokens");
    bJR.put("data", "http://pasta.dianxinos.com/api/data");
    bJR.put("token", "http://pasta.dianxinos.com/api/tokens");
  }
  
  public static boolean W(Context paramContext, int paramInt)
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
  
  public static String a(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }
  
  private static void a(String paramString1, String paramString2, List<NameValuePair> paramList)
  {
    if (!TextUtils.isEmpty(paramString2)) {
      paramList.add(new BasicNameValuePair(paramString1, paramString2));
    }
  }
  
  public static long aM(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("rt", 0).getLong(paramString, 0L);
  }
  
  public static void aN(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong(paramString, System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static boolean ai(Context paramContext)
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
  
  public static JSONObject b(String paramString, Number paramNumber)
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
  
  public static void c(SharedPreferences.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.commit();
      return;
    }
    catch (Exception paramEditor) {}
  }
  
  public static String f(String paramString, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = TokenManager.getToken(paramContext);
    String str = k.c(paramContext, m.RC());
    if ("data".equals(paramString)) {
      a("token", str, localArrayList);
    }
    a("tk", paramContext, localArrayList);
    a("sv", com.dianxinos.dxservice.a.getVersion(), localArrayList);
    paramContext = "?" + URLEncodedUtils.format(localArrayList, "UTF-8");
    if ("feedback".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/statistics_feedback" + paramContext;
      }
      if (bJQ.equals("booster")) {
        return "http://pasta.ds.duapps.com/feedback" + paramContext;
      }
      if (bJQ.equals("battery")) {
        return "http://pasta.sd.duapps.com/feedback" + paramContext;
      }
      return (String)bJR.get("feedback") + paramContext;
    }
    if ("appInfo".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/api/tokens" + paramContext;
      }
      if (bJQ.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/tokens" + paramContext;
      }
      if (bJQ.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/tokens" + paramContext;
      }
      return (String)bJR.get("appInfo") + paramContext;
    }
    if ("data".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/api/data" + paramContext;
      }
      if (bJQ.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/data" + paramContext;
      }
      if (bJQ.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/data" + paramContext;
      }
      return (String)bJR.get("data") + paramContext;
    }
    if ("token".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/api/tokens" + paramContext;
      }
      if (bJQ.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/tokens" + paramContext;
      }
      if (bJQ.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/tokens" + paramContext;
      }
      return (String)bJR.get("token") + paramContext;
    }
    return "";
  }
  
  public static long gI(Context paramContext)
  {
    return paramContext.getSharedPreferences("rt", 0).getLong("rnet", 0L);
  }
  
  public static void gJ(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong("rnet", System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static boolean gK(Context paramContext)
  {
    Object localObject2 = paramContext.getContentResolver();
    Object localObject1 = paramContext.getPackageManager();
    String str2 = Settings.System.getString((ContentResolver)localObject2, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}");
    String str3 = paramContext.getPackageName();
    for (;;)
    {
      String str1;
      int i;
      int j;
      try
      {
        localObject2 = ((PackageManager)localObject1).getInstalledApplications(0);
        localObject1 = null;
        Iterator localIterator = ((List)localObject2).iterator();
        if (!localIterator.hasNext()) {
          break label222;
        }
        str1 = ((ApplicationInfo)localIterator.next()).packageName;
        if ("com.dianxinos.dxservice".equals(str1))
        {
          i = 1;
          j = 0;
          if (j == 0) {
            break label156;
          }
          if (!str3.equals(str2)) {
            break label214;
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
        if (hS(str1))
        {
          localObject2 = str1;
          if (localObject1 != null) {
            if (((String)localObject1).compareTo(str1) <= 0) {
              break label216;
            }
          }
        }
        label156:
        label214:
        label216:
        for (localObject2 = str1;; localObject2 = localObject1)
        {
          localObject1 = localObject2;
          break;
          if (i != 0)
          {
            if (str3.equals("com.dianxinos.dxservice"))
            {
              com.dianxinos.library.dxbase.c.hx(paramContext).aE("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
              return true;
            }
          }
          else if ((localObject1 == null) || (str3.equals(localObject1)))
          {
            com.dianxinos.library.dxbase.c.hx(paramContext).aE("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
            return true;
          }
          return false;
        }
        label222:
        i = 0;
        j = 0;
      }
    }
  }
  
  public static void gL(Context paramContext)
  {
    paramContext.getContentResolver();
    com.dianxinos.library.dxbase.c.hx(paramContext).aE("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
  }
  
  private static boolean hS(String paramString)
  {
    paramString = paramString.split("\\.");
    return (paramString.length > 1) && (paramString[1].equals("dianxinos"));
  }
  
  public static String hy(String paramString)
  {
    try
    {
      localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      localObject = new String(e.a.a.a.a.a.a.a.Q(((MessageDigest)localObject).digest()), "UTF-8");
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      do
      {
        localObject = paramString;
      } while (!bGe);
      Log.e("stat.CommonUtils", "Encoding#1 not found.", localNoSuchAlgorithmException);
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      do
      {
        Object localObject = paramString;
      } while (!bGe);
      Log.e("stat.CommonUtils", "Encoding#2 not found.", localUnsupportedEncodingException);
    }
    return paramString;
  }
}
