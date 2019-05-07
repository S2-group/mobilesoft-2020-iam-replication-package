package com.meitu.pushkit;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.meitu.mkit.a.d;
import com.meitu.pushkit.sdk.b;
import com.meitu.pushkit.sdk.info.PopInfo;
import com.meitu.pushkit.sdk.info.PushChannel;
import com.meitu.pushkit.sdk.info.PushInfo;
import com.meitu.secret.SigEntity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import okhttp3.ai;
import okhttp3.an;
import okhttp3.ao;
import okhttp3.v;
import org.json.JSONArray;
import org.json.JSONObject;

public class f
{
  public static int a()
  {
    return c.a().j().getPushChannelId();
  }
  
  public static PushInfo a(String paramString)
  {
    PushInfo localPushInfo = new PushInfo();
    try
    {
      Object localObject = new JSONObject(paramString);
      localPushInfo.payload = paramString;
      paramString = Uri.parse(((JSONObject)localObject).optString("sdk_uri"));
      if (!"mtpushsdk".equals(paramString.getScheme())) {
        return null;
      }
      localPushInfo.taskType = paramString.getQueryParameter("task_type");
      localPushInfo.id = ((JSONObject)localObject).optString("id");
      localPushInfo.title = ((JSONObject)localObject).optString("title");
      localPushInfo.desc = ((JSONObject)localObject).optString("desc");
      localPushInfo.uri = ((JSONObject)localObject).optString("uri");
      localPushInfo.url = ((JSONObject)localObject).optString("url");
      localPushInfo.attachment = ((JSONObject)localObject).optString("attachment");
      localPushInfo.extra = ((JSONObject)localObject).optString("extra");
      paramString = new PopInfo();
      localObject = ((JSONObject)localObject).optJSONObject("pop");
      if (localObject != null)
      {
        paramString.title = ((JSONObject)localObject).optString("title");
        paramString.desc = ((JSONObject)localObject).optString("desc");
        localObject = ((JSONObject)localObject).optJSONArray("buttons");
        if (localObject != null)
        {
          paramString.buttons = new String[((JSONArray)localObject).length()];
          int i = 0;
          while (i < ((JSONArray)localObject).length())
          {
            paramString.buttons[i] = ((String)((JSONArray)localObject).opt(i));
            i += 1;
          }
        }
        localPushInfo.popInfo = paramString;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    return localPushInfo;
  }
  
  public static void a(Context paramContext)
  {
    if (TextUtils.isEmpty(c.a().f())) {
      d.b("[warning] MtPushApi MtAppId must be set");
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (c.a().b()) {
      return;
    }
    if (c.a().m())
    {
      a(paramContext, paramString, true);
      return;
    }
    a(paramContext, paramString, false);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    if ("0".equals(paramString1)) {
      return;
    }
    new Thread(new f.6(paramContext, paramString1, paramString2)).start();
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    d.a(" newToken = " + paramString + " forcibleRequest " + paramBoolean);
    if (TextUtils.isEmpty(paramString)) {
      d.a("deviceToken is null");
    }
    String str;
    do
    {
      return;
      a(paramContext);
      if (paramBoolean)
      {
        e(paramContext, paramString);
        return;
      }
      a(paramString, paramContext);
      str = c.a().k();
      d.a(" oldToken = " + str);
    } while (str.equals(paramString));
    b(paramString, paramContext);
    e(paramContext, paramString);
  }
  
  public static void a(PushInfo paramPushInfo, Context paramContext)
  {
    if (paramPushInfo == null) {
      return;
    }
    Object localObject = b(paramContext, "PUSH_KIT_APP_ID");
    localObject = new Intent("com.meitu.pushkit.action." + (String)localObject);
    Bundle localBundle = new Bundle();
    localBundle.putInt("key_action", 10000);
    localBundle.putSerializable("key_push_info", paramPushInfo);
    ((Intent)localObject).putExtras(localBundle);
    paramContext.sendBroadcast((Intent)localObject);
  }
  
  public static void a(String paramString, Context paramContext)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    Object localObject = b(paramContext, "PUSH_KIT_APP_ID");
    localObject = new Intent("com.meitu.pushkit.action." + (String)localObject);
    Bundle localBundle = new Bundle();
    localBundle.putInt("key_action", 10001);
    localBundle.putString("key_token", paramString);
    ((Intent)localObject).putExtras(localBundle);
    paramContext.sendBroadcast((Intent)localObject);
  }
  
  private static void a(Map<String, String> paramMap, SigEntity paramSigEntity)
  {
    paramMap.put("sig", paramSigEntity.sig);
    paramMap.put("sig_time", paramSigEntity.sigTime);
    paramMap.put("sig_version", paramSigEntity.sigVersion);
  }
  
  public static String b()
  {
    String str2 = c.a().k();
    d.a(" getDeviceToken  = " + str2);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      str1 = c.a().l();
      d.a(" getBackupToken  = " + str1);
    }
    return str1;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      return paramContext.metaData.getString(paramString);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    if ("0".equals(paramString1)) {
      return;
    }
    a(paramContext);
    Object localObject = new HashMap();
    String str = g(paramContext);
    ((Map)localObject).put("app_id", str);
    ((Map)localObject).put("task_id", paramString1);
    ((Map)localObject).put("task_type", paramString2);
    ((Map)localObject).put("device_token", b());
    ((Map)localObject).put("channel", a() + "");
    ((Map)localObject).put("os_version", c());
    ((Map)localObject).put("country", h(paramContext));
    ((Map)localObject).put("device_model", Build.MODEL);
    ((Map)localObject).put("manufacturer", Build.MANUFACTURER);
    paramContext = ((Map)localObject).values().toArray();
    paramString1 = new String[paramContext.length];
    int i = 0;
    while (i < paramContext.length)
    {
      paramString1[i] = (paramContext[i] + "");
      i += 1;
    }
    a((Map)localObject, SigEntity.generatorSig("push/message/ack.json", paramString1, str));
    paramContext = c.a().g() + str + "/push/message/ack.json";
    paramString1 = new v();
    if (localObject != null)
    {
      paramString2 = ((Map)localObject).entrySet().iterator();
      while (paramString2.hasNext())
      {
        localObject = (Map.Entry)paramString2.next();
        paramString1.a((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue() + "");
      }
    }
    paramString1 = paramString1.a();
    paramString1 = new an().a(paramContext).a(paramString1).a();
    new ai().a(paramString1).a(new f.7(paramContext));
  }
  
  public static void b(String paramString, Context paramContext)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    Object localObject = b(paramContext, "PUSH_KIT_APP_ID");
    localObject = new Intent("com.meitu.pushkit.action." + (String)localObject);
    Bundle localBundle = new Bundle();
    localBundle.putInt("key_action", 10002);
    localBundle.putString("key_token", paramString);
    ((Intent)localObject).putExtras(localBundle);
    paramContext.sendBroadcast((Intent)localObject);
  }
  
  public static boolean b(Context paramContext)
  {
    boolean bool = c.a().c();
    PushChannel localPushChannel = c.a().j();
    d.a("Current Channel " + localPushChannel + "  isRequestingPushChannel " + bool);
    if ((localPushChannel == PushChannel.NONE) && (!bool))
    {
      e(paramContext);
      return false;
    }
    return true;
  }
  
  public static String c()
  {
    return Build.VERSION.SDK_INT + "";
  }
  
  public static void c(Context paramContext)
  {
    String str = b();
    if (TextUtils.isEmpty(str)) {}
    while ((!c.a().m()) || (c.a().b())) {
      return;
    }
    a(paramContext, str, true);
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    if ("0".equals(paramString1)) {
      return;
    }
    a(paramContext);
    Object localObject = new HashMap();
    String str = g(paramContext);
    ((Map)localObject).put("app_id", str);
    ((Map)localObject).put("task_id", paramString1);
    ((Map)localObject).put("task_type", paramString2);
    ((Map)localObject).put("device_token", b());
    ((Map)localObject).put("channel", a() + "");
    ((Map)localObject).put("os_version", c());
    ((Map)localObject).put("country", h(paramContext));
    ((Map)localObject).put("device_model", Build.MODEL);
    ((Map)localObject).put("manufacturer", Build.MANUFACTURER);
    paramContext = ((Map)localObject).values().toArray();
    paramString1 = new String[paramContext.length];
    int i = 0;
    while (i < paramContext.length)
    {
      paramString1[i] = (paramContext[i] + "");
      i += 1;
    }
    a((Map)localObject, SigEntity.generatorSig("push/message/clicked.json", paramString1, str));
    paramContext = c.a().g() + str + "/push/message/clicked.json";
    paramString1 = new v();
    if (localObject != null)
    {
      paramString2 = ((Map)localObject).entrySet().iterator();
      while (paramString2.hasNext())
      {
        localObject = (Map.Entry)paramString2.next();
        paramString1.a((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue() + "");
      }
    }
    paramString1 = paramString1.a();
    paramString1 = new an().a(paramContext).a(paramString1).a();
    new ai().a(paramString1).a(new f.8(paramContext));
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static void e(Context paramContext)
  {
    paramContext = new Thread(new f.3(paramContext));
    paramContext.setName("requestPushChannel ");
    paramContext.start();
  }
  
  private static void e(Context paramContext, String paramString)
  {
    paramContext = new Thread(new f.1(paramContext, paramString));
    paramContext.setName("requestBindToken ");
    paramContext.start();
  }
  
  public static void f(Context paramContext)
  {
    a(paramContext);
    if (TextUtils.isEmpty(b.a())) {
      return;
    }
    String str = g(paramContext);
    paramContext = new HashMap();
    paramContext.put("app_id", str);
    paramContext.put("channel", a() + "");
    paramContext.put("device_token", b());
    paramContext.put("uid", b.a());
    Object localObject1 = paramContext.values().toArray();
    Object localObject2 = new String[localObject1.length];
    int i = 0;
    while (i < localObject1.length)
    {
      localObject2[i] = (localObject1[i] + "");
      i += 1;
    }
    a(paramContext, SigEntity.generatorSig("push/token/unbind.json", (String[])localObject2, str));
    str = c.a().g() + str + "/push/token/unbind.json";
    d.a(" start to unbind ");
    localObject1 = new v();
    if (paramContext != null)
    {
      paramContext = paramContext.entrySet().iterator();
      while (paramContext.hasNext())
      {
        localObject2 = (Map.Entry)paramContext.next();
        ((v)localObject1).a((String)((Map.Entry)localObject2).getKey(), (String)((Map.Entry)localObject2).getValue() + "");
      }
    }
    paramContext = ((v)localObject1).a();
    paramContext = new an().a(str).a(paramContext).a();
    new ai().a(paramContext).a(new f.5());
  }
  
  private static void f(Context paramContext, String paramString)
  {
    try
    {
      c.a().a(true);
      c.a().e(paramString);
      c.a().f(paramString);
      Object localObject1 = new HashMap();
      Object localObject2 = g(paramContext);
      ((Map)localObject1).put("app_id", localObject2);
      ((Map)localObject1).put("device_token", paramString);
      ((Map)localObject1).put("country", h(paramContext));
      ((Map)localObject1).put("lang", i(paramContext));
      ((Map)localObject1).put("channel", a() + "");
      ((Map)localObject1).put("os_type", "2");
      if (!TextUtils.isEmpty(b.a())) {
        ((Map)localObject1).put("uid", b.a());
      }
      if (!TextUtils.isEmpty(l(paramContext))) {
        ((Map)localObject1).put("device_id", l(paramContext));
      }
      ((Map)localObject1).put("source", c.a().o());
      ((Map)localObject1).put("manufacturer", Build.MANUFACTURER);
      ((Map)localObject1).put("device_model", Build.MODEL);
      ((Map)localObject1).put("os_version", c());
      ((Map)localObject1).put("version", k(paramContext));
      paramContext = ((Map)localObject1).values().toArray();
      paramString = new String[paramContext.length];
      int i = 0;
      while (i < paramContext.length)
      {
        paramString[i] = (paramContext[i] + "");
        i += 1;
      }
      a((Map)localObject1, SigEntity.generatorSig("push/token/bind.json", paramString, (String)localObject2));
      paramContext = c.a().g() + (String)localObject2 + "/push/token/bind.json";
      d.a(" start to bindToken  channel = " + c.a().j());
      paramString = new v();
      if (localObject1 != null)
      {
        localObject1 = ((Map)localObject1).entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          paramString.a((String)((Map.Entry)localObject2).getKey(), (String)((Map.Entry)localObject2).getValue() + "");
        }
      }
      paramString = paramString.a();
    }
    catch (Exception paramContext)
    {
      d.a(" bind token Exception -2--");
      c.a().e("");
      c.a().d(true);
      c.a().a(false);
      paramContext.printStackTrace();
      return;
    }
    paramContext = new an().a(paramContext).a(paramString).a();
    new ai().a(paramContext).a(new f.2());
  }
  
  public static String g(Context paramContext)
  {
    return c.a().f();
  }
  
  private static void g(Context paramContext, String paramString)
  {
    Object localObject1;
    try
    {
      c.a().b(true);
      localObject1 = new HashMap();
      Object localObject2 = g(paramContext);
      ((Map)localObject1).put("app_id", localObject2);
      if (!TextUtils.isEmpty(paramString)) {
        ((Map)localObject1).put("device_token", paramString);
      }
      ((Map)localObject1).put("device_id", l(paramContext));
      ((Map)localObject1).put("sdk_version", "1.3.1");
      ((Map)localObject1).put("client_channels", c.a().e());
      ((Map)localObject1).put("lang", i(paramContext));
      ((Map)localObject1).put("channel", a() + "");
      ((Map)localObject1).put("has_gms", n(paramContext) + "");
      ((Map)localObject1).put("manufacturer", Build.MANUFACTURER);
      ((Map)localObject1).put("device_model", Build.MODEL);
      ((Map)localObject1).put("os_version", c());
      ((Map)localObject1).put("version", k(paramContext));
      ((Map)localObject1).put("country", Locale.getDefault().getCountry());
      paramString = ((Map)localObject1).values().toArray();
      Object localObject3 = new String[paramString.length];
      int i = 0;
      while (i < paramString.length)
      {
        localObject3[i] = (paramString[i] + "");
        i += 1;
      }
      a((Map)localObject1, SigEntity.generatorSig("push/strategy/channel.json", (String[])localObject3, (String)localObject2));
      paramString = c.a().g() + (String)localObject2 + "/push/strategy/channel.json";
      d.a(" start to requestPushChannel  ");
      localObject2 = new v();
      if (localObject1 != null)
      {
        localObject1 = ((Map)localObject1).entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject1).next();
          ((v)localObject2).a((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue() + "");
        }
      }
      localObject1 = ((v)localObject2).a();
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      c.a().b(false);
      return;
    }
    paramString = new an().a(paramString).a((ao)localObject1).a();
    new ai().a(paramString).a(new f.4(paramContext));
  }
  
  public static String h(Context paramContext)
  {
    String str = c.a().r();
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = Locale.getDefault().getCountry();
    }
    return paramContext;
  }
  
  public static String i(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale;
    paramContext = Locale.getDefault().toString();
    d.a(" getLang = " + paramContext);
    return paramContext;
  }
  
  public static String j(Context paramContext)
  {
    Object localObject = "";
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("PUSH_KIT_APP_ID");
      localObject = paramContext;
      paramContext = paramContext.substring(0, paramContext.length() - 1);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localObject;
  }
  
  public static String k(Context paramContext)
  {
    paramContext = m(paramContext);
    if (paramContext != null) {
      return paramContext.versionName;
    }
    return "";
  }
  
  public static String l(Context paramContext)
  {
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    String str1 = c.a().h();
    if (!TextUtils.isEmpty(str1))
    {
      d.a("deviceId =" + str1);
      return str1;
    }
    str1 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10 + Build.SERIAL;
    String str2 = new UUID(str1.hashCode(), paramContext.hashCode()).toString();
    c.a().c(str2);
    d.a("buildInfo =" + str1 + " androidId =" + paramContext + " deviceId =" + str2);
    return str2;
  }
  
  private static PackageInfo m(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static int n(Context paramContext)
  {
    ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int j = paramContext.size();
    int i = 0;
    if (i < j) {
      if (!((PackageInfo)paramContext.get(i)).applicationInfo.processName.contains("com.google.android.gms")) {}
    }
    for (i = 1;; i = 0)
    {
      d.a(" has_gms " + i);
      return i;
      i += 1;
      break;
    }
  }
}
