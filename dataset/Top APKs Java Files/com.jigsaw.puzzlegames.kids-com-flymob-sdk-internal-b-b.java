package com.flymob.sdk.internal.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b
{
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static void a(String paramString, Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("com.flymob.sdk.uid.xml", 0).edit();
    paramContext.putString("custom_uuid", paramString);
    paramContext.apply();
  }
  
  public static String b(Context paramContext)
  {
    String str2 = h(paramContext);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      str1 = UUID.randomUUID().toString();
      a(str1, paramContext);
    }
    return str1;
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0)
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getDeviceId();
          return paramContext;
        }
      }
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static String d(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if ((localWifiManager != null) && (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0))
      {
        paramContext = localWifiManager.getConnectionInfo().getMacAddress();
        return paramContext;
      }
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext).getId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      h.a("Error getting advertising id: " + paramContext.getMessage());
      return null;
    }
    catch (Error paramContext)
    {
      paramContext.printStackTrace();
      h.a("Google play services not found: " + paramContext.getMessage(), true);
    }
    return null;
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  public static List<String> f(Context paramContext)
  {
    int i = 0;
    localLinkedList = new LinkedList();
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      int j = paramContext.length;
      while (i < j)
      {
        Object localObject = paramContext[i];
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(localObject.toByteArray());
        localLinkedList.add(new String(Base64.encode(localMessageDigest.digest(), 0)));
        i += 1;
      }
      return localLinkedList;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      h.a("getHashKey error: " + paramContext.getMessage());
      return localLinkedList;
    }
    catch (Exception paramContext)
    {
      h.a("getHashKey error: " + paramContext.getMessage());
    }
  }
  
  public static JSONObject g(Context paramContext)
  {
    Object localObject1;
    if (!p.g(paramContext))
    {
      localObject1 = p.f(paramContext);
      if (localObject1 == null) {}
    }
    try
    {
      localObject1 = new JSONObject((String)localObject1);
      Object localObject3;
      for (;;)
      {
        localObject3 = localObject1;
        if (localObject1 != null) {
          break;
        }
        try
        {
          Object localObject4 = paramContext.getPackageManager().getInstalledPackages(0);
          Collections.sort((List)localObject4, new Comparator()
          {
            public int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
            {
              long l1 = paramAnonymousPackageInfo1.firstInstallTime;
              long l2 = paramAnonymousPackageInfo2.firstInstallTime;
              if (l1 > l2) {
                return -1;
              }
              if (l1 == l2) {
                return 0;
              }
              return 1;
            }
          });
          localObject3 = new JSONObject();
          localObject1 = null;
        }
        catch (Throwable paramContext)
        {
          try
          {
            ((JSONObject)localObject3).put("count", ((List)localObject4).size());
            localObject1 = new JSONArray();
            localObject4 = ((List)localObject4).iterator();
            while (((Iterator)localObject4).hasNext()) {
              ((JSONArray)localObject1).put(((PackageInfo)((Iterator)localObject4).next()).packageName);
            }
            ((JSONObject)localObject3).put("packages", localObject1);
            p.b(new Date().getTime(), paramContext);
            p.a(((JSONObject)localObject3).toString(), paramContext);
            return localObject3;
          }
          catch (Throwable paramContext) {}
          paramContext = paramContext;
          return localObject1;
        }
      }
      return localObject3;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject2 = null;
      }
    }
  }
  
  private static String h(Context paramContext)
  {
    Object localObject = null;
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("com.flymob.sdk.uid.xml", 0);
    paramContext = localObject;
    if (localSharedPreferences != null) {
      paramContext = localSharedPreferences.getString("custom_uuid", null);
    }
    return paramContext;
  }
}
