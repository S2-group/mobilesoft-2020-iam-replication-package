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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class ud
{
  public static boolean a = false;
  public static boolean b = false;
  public static boolean c = b;
  public static boolean d = c;
  
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
      localObject = new String(bdk.a(((MessageDigest)localObject).digest()), "UTF-8");
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      do
      {
        localObject = paramString;
      } while (!d);
      Log.e("stat.CommonUtils", "Encoding#1 not found.", localNoSuchAlgorithmException);
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      do
      {
        Object localObject = paramString;
      } while (!d);
      Log.e("stat.CommonUtils", "Encoding#2 not found.", localUnsupportedEncodingException);
    }
    return paramString;
  }
  
  public static String a(String paramString, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = gx.a(paramContext);
    String str = te.a(paramContext, tg.b());
    if ("data".equals(paramString)) {
      a("token", str, localArrayList);
    }
    a("tk", paramContext, localArrayList);
    a("sv", rz.a(), localArrayList);
    paramContext = "?" + URLEncodedUtils.format(localArrayList, "UTF-8");
    if ("feedback".equals(paramString))
    {
      if (a) {
        return "http://t1.tira.cn:8125/feedback" + paramContext;
      }
      return "http://pasta.dianxinos.com/feedback" + paramContext;
    }
    if ("appInfo".equals(paramString))
    {
      if (a) {
        return "http://t1.tira.cn:8125/api/tokens" + paramContext;
      }
      return "http://pasta.dianxinos.com/api/tokens" + paramContext;
    }
    if ("data".equals(paramString))
    {
      if (a) {
        return "http://t1.tira.cn:8125/api/data" + paramContext;
      }
      return "http://pasta.dianxinos.com/api/data" + paramContext;
    }
    if ("token".equals(paramString))
    {
      if (a) {
        return "http://t1.tira.cn:8125/api/tokens" + paramContext;
      }
      return "http://pasta.dianxinos.com/api/tokens" + paramContext;
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
  
  private static void a(String paramString1, String paramString2, List paramList)
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
    paramContext = paramContext.getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
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
  
  public static void b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("rt", 0).edit();
    paramContext.putLong(paramString, System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static boolean b(Context paramContext)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject = paramContext.getPackageManager();
    String str2 = Settings.System.getString(localContentResolver, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}");
    String str3 = paramContext.getPackageName();
    localObject = ((PackageManager)localObject).getInstalledApplications(0);
    paramContext = null;
    Iterator localIterator = ((List)localObject).iterator();
    String str1;
    int i;
    int j;
    if (localIterator.hasNext())
    {
      str1 = ((ApplicationInfo)localIterator.next()).packageName;
      if ("com.dianxinos.dxservice".equals(str1))
      {
        i = 1;
        j = 0;
      }
    }
    for (;;)
    {
      if (j != 0)
      {
        if (!str3.equals(str2)) {
          break label202;
        }
        return true;
        if (str1.equals(str2))
        {
          i = 0;
          j = 1;
          continue;
        }
        if (!b(str1)) {
          break label204;
        }
        localObject = str1;
        if (paramContext != null) {
          if (paramContext.compareTo(str1) <= 0) {
            break label204;
          }
        }
      }
      label202:
      label204:
      for (localObject = str1;; localObject = paramContext)
      {
        paramContext = (Context)localObject;
        break;
        if (i != 0)
        {
          if (str3.equals("com.dianxinos.dxservice"))
          {
            Settings.System.putString(localContentResolver, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
            return true;
          }
        }
        else if ((paramContext == null) || (str3.equals(paramContext)))
        {
          Settings.System.putString(localContentResolver, "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", str3);
          return true;
        }
        return false;
      }
      i = 0;
      j = 0;
    }
  }
  
  private static boolean b(String paramString)
  {
    paramString = paramString.split("\\.");
    return (paramString.length > 1) && (paramString[1].equals("dianxinos"));
  }
  
  public static void c(Context paramContext)
  {
    Settings.System.putString(paramContext.getContentResolver(), "android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC76WR}", "");
  }
}
