package com.dianxinos.dxservice.utils;

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
import com.dianxinos.dxservice.VERSION_DXStatsService;
import com.dianxinos.dxservice.stat.EncryptionUtil;
import com.dianxinos.dxservice.stat.EventConfig;
import com.dianxinos.library.dnet.RequestHelper;
import com.dianxinos.library.dxbase.DXBDataStorageHelper;
import dianxinos.dxstat.org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonUtils
{
  public static final String APPINFO = "appInfo";
  public static final String DATA = "data";
  public static boolean DEBUG = false;
  public static final HashMap<String, String> DEFAULT_URL_MAP;
  public static final String FEEDBACK = "feedback";
  public static boolean LOGD_ENABLED = false;
  public static boolean LOGE_ENABLED = false;
  public static boolean LOGI_ENABLED = false;
  public static final String SELT_KEY_JSON_RESPONSE_RESULT = "js_rsprs";
  public static final String SELT_KEY_RESPONSE_RESULT = "stat_self_rsprs";
  public static final String TOKEN = "token";
  public static String sCurrentProduct = "others";
  public static boolean sIsReportSelfData;
  
  static
  {
    DEBUG = false;
    LOGD_ENABLED = false;
    LOGI_ENABLED = LOGD_ENABLED;
    LOGE_ENABLED = LOGI_ENABLED;
    DEFAULT_URL_MAP = new HashMap();
    sIsReportSelfData = false;
    DEFAULT_URL_MAP.put("feedback", "http://pasta.dianxinos.com/feedback");
    DEFAULT_URL_MAP.put("appInfo", "http://pasta.dianxinos.com/api/tokens");
    DEFAULT_URL_MAP.put("data", "http://pasta.dianxinos.com/api/data");
    DEFAULT_URL_MAP.put("token", "http://pasta.dianxinos.com/api/tokens");
  }
  
  public CommonUtils() {}
  
  public static boolean CheckCurrentNetworkType(Context paramContext, int paramInt)
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
  
  private static void a(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    if (!TextUtils.isEmpty(paramString2)) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  private static boolean a(String paramString)
  {
    paramString = paramString.split("\\.");
    return (paramString.length > 1) && (paramString[1].equals("dianxinos"));
  }
  
  public static void cleanReporter(Context paramContext)
  {
    paramContext.getContentResolver();
    DXBDataStorageHelper.getInstance(paramContext).putString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
  }
  
  public static void closeQuietly(Reader paramReader)
  {
    if (paramReader != null) {}
    try
    {
      paramReader.close();
      return;
    }
    catch (IOException paramReader) {}
  }
  
  public static void commitQuietly(SharedPreferences.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.commit();
      return;
    }
    catch (Exception paramEditor) {}
  }
  
  public static String formatDate(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }
  
  public static JSONObject getJsonData(String paramString, Number paramNumber)
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
  
  public static long getLastReportNetworkErrorTime(Context paramContext)
  {
    return paramContext.getSharedPreferences("rt", 0).getLong("rnet", 0L);
  }
  
  public static long getLastReportTime(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("rt", 0).getLong(paramString, 0L);
  }
  
  public static String getSystemProperty(String paramString)
  {
    try
    {
      Class localClass = Class.forName("android.os.SystemProperties");
      paramString = (String)localClass.getMethod("get", new Class[] { String.class }).invoke(localClass, new Object[] { paramString });
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      if (LOGE_ENABLED) {
        Log.w("stat.CommonUtils", "getSystemProperty has ClassNotFoundException");
      }
      return "";
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        if (LOGE_ENABLED) {
          Log.w("stat.CommonUtils", "getSystemProperty has SecurityException");
        }
      }
    }
    catch (NoSuchMethodException paramString)
    {
      for (;;)
      {
        if (LOGE_ENABLED) {
          Log.w("stat.CommonUtils", "getSystemProperty has NoSuchMethodException");
        }
      }
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;)
      {
        if (LOGE_ENABLED) {
          Log.w("stat.CommonUtils", "getSystemProperty has IllegalArgumentException");
        }
      }
    }
    catch (IllegalAccessException paramString)
    {
      for (;;)
      {
        if (LOGE_ENABLED) {
          Log.w("stat.CommonUtils", "getSystemProperty has IllegalAccessException");
        }
      }
    }
    catch (InvocationTargetException paramString)
    {
      for (;;)
      {
        if (LOGE_ENABLED) {
          Log.w("stat.CommonUtils", "getSystemProperty has InvocationTargetException");
        }
      }
    }
  }
  
  public static String getUploadUrl(String paramString, Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    paramContext = TokenManager.getToken(paramContext);
    String str = EncryptionUtil.encryptAES(paramContext, EventConfig.getAESKey());
    if ("data".equals(paramString)) {
      a("token", str, localHashMap);
    }
    a("tk", paramContext, localHashMap);
    a("sv", VERSION_DXStatsService.getVersion(), localHashMap);
    paramContext = "?" + RequestHelper.format(localHashMap, "UTF-8");
    if ("feedback".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/statistics_feedback" + paramContext;
      }
      if (sCurrentProduct.equals("booster")) {
        return "http://pasta.ds.duapps.com/feedback" + paramContext;
      }
      if (sCurrentProduct.equals("battery")) {
        return "http://pasta.sd.duapps.com/feedback" + paramContext;
      }
      return (String)DEFAULT_URL_MAP.get("feedback") + paramContext;
    }
    if ("appInfo".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/api/tokens" + paramContext;
      }
      if (sCurrentProduct.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/tokens" + paramContext;
      }
      if (sCurrentProduct.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/tokens" + paramContext;
      }
      return (String)DEFAULT_URL_MAP.get("appInfo") + paramContext;
    }
    if ("data".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/api/data" + paramContext;
      }
      if (sCurrentProduct.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/data" + paramContext;
      }
      if (sCurrentProduct.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/data" + paramContext;
      }
      return (String)DEFAULT_URL_MAP.get("data") + paramContext;
    }
    if ("token".equals(paramString))
    {
      if (DEBUG) {
        return "http://sandbox.sjws.baidu.com:8080/api/tokens" + paramContext;
      }
      if (sCurrentProduct.equals("booster")) {
        return "http://pasta.ds.duapps.com/api/tokens" + paramContext;
      }
      if (sCurrentProduct.equals("battery")) {
        return "http://pasta.sd.duapps.com/api/tokens" + paramContext;
      }
      return (String)DEFAULT_URL_MAP.get("token") + paramContext;
    }
    return "";
  }
  
  public static String hashData(String paramString)
  {
    try
    {
      localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      localObject = new String(Base64.encodeBase64(((MessageDigest)localObject).digest()), "UTF-8");
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      do
      {
        localObject = paramString;
      } while (!LOGE_ENABLED);
      Log.e("stat.CommonUtils", "Encoding#1 not found.", localNoSuchAlgorithmException);
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      do
      {
        Object localObject = paramString;
      } while (!LOGE_ENABLED);
      Log.e("stat.CommonUtils", "Encoding#2 not found.", localUnsupportedEncodingException);
    }
    return paramString;
  }
  
  public static boolean isMyReport(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    DXBDataStorageHelper localDXBDataStorageHelper = DXBDataStorageHelper.getInstance(paramContext);
    String str2 = localDXBDataStorageHelper.getString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
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
          break label211;
        }
        str1 = ((ApplicationInfo)localIterator.next()).packageName;
        if ("com.dianxinos.dxservice".equals(str1))
        {
          i = 1;
          j = 0;
          if (j == 0) {
            break label152;
          }
          if (!str3.equals(str2)) {
            break label204;
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
        if (a(str1))
        {
          localObject = str1;
          if (paramContext != null) {
            if (paramContext.compareTo(str1) <= 0) {
              break label206;
            }
          }
        }
        label152:
        label204:
        label206:
        for (localObject = str1;; localObject = paramContext)
        {
          paramContext = (Context)localObject;
          break;
          if (i != 0)
          {
            if (str3.equals("com.dianxinos.dxservice"))
            {
              localDXBDataStorageHelper.putString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
              return true;
            }
          }
          else if ((paramContext == null) || (str3.equals(paramContext)))
          {
            localDXBDataStorageHelper.putString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
            return true;
          }
          return false;
        }
        label211:
        i = 0;
        j = 0;
      }
    }
  }
  
  public static boolean isNetworkConnected(Context paramContext)
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
  
  public static void updateLastReportNetworkErrorTime(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong("rnet", System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static void updateLastReportTime(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong(paramString, System.currentTimeMillis());
    paramContext.commit();
  }
}
