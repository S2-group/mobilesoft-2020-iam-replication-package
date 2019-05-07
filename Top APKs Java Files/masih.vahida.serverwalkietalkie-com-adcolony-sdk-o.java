package com.adcolony.sdk;

import a.h;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.SparseIntArray;
import com.a.e.g.u;
import com.purplebrain.adbuddiz.sdk.f.a;
import com.purplebrain.adbuddiz.sdk.f.b;
import com.purplebrain.adbuddiz.sdk.i.d;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;

public class o
{
  boolean a;
  boolean b;
  JSONObject c = new JSONObject();
  
  public o() {}
  
  public static u a()
  {
    SparseIntArray localSparseIntArray = new SparseIntArray();
    localSparseIntArray.put(1024, 5);
    localSparseIntArray.put(2048, 5);
    localSparseIntArray.put(4096, 5);
    localSparseIntArray.put(8192, 5);
    localSparseIntArray.put(16384, 5);
    localSparseIntArray.put(32768, 5);
    localSparseIntArray.put(65536, 5);
    localSparseIntArray.put(131072, 5);
    localSparseIntArray.put(262144, 2);
    localSparseIntArray.put(524288, 2);
    localSparseIntArray.put(1048576, 2);
    int i = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
    int j;
    if (i < 16777216)
    {
      i = 3145728;
      j = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
      if (j >= 16777216) {
        break label166;
      }
      j /= 2;
    }
    for (;;)
    {
      return new u(i, j, localSparseIntArray);
      if (i < 33554432)
      {
        i = 6291456;
        break;
      }
      i = 12582912;
      break;
      label166:
      j = j / 4 * 3;
    }
  }
  
  @SuppressLint({"NewApi"})
  public static String a(Context paramContext)
  {
    b localB = com.purplebrain.adbuddiz.sdk.e.g.a().b();
    if ((localB == null) || (!localB.a(a.d))) {
      return null;
    }
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static String a(String paramString)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramString.getBytes("UTF-8"));
      localGZIPOutputStream.flush();
      localGZIPOutputStream.close();
      paramString = a(localByteArrayOutputStream.toByteArray());
      return paramString;
    }
    catch (Exception paramString)
    {
      com.purplebrain.adbuddiz.sdk.i.i.a("ABDataEncryptionHelper.gzipAndEncode()", paramString);
    }
    return null;
  }
  
  @SuppressLint({"TrulyRandom"})
  private static String a(byte[] paramArrayOfByte)
  {
    try
    {
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(d.a("bWZSYjVSNUtlc2tkYjJ4Tg=="));
      SecretKeySpec localSecretKeySpec = b();
      if (localSecretKeySpec == null) {
        return null;
      }
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(1, localSecretKeySpec, localIvParameterSpec);
      paramArrayOfByte = new String(d.a(localCipher.doFinal(paramArrayOfByte)));
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      com.purplebrain.adbuddiz.sdk.i.i.a("ABDataEncryptionHelper.encrypt()", paramArrayOfByte);
    }
    return null;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static String b(Context paramContext)
  {
    paramContext = c(paramContext);
    if (paramContext == null) {
      return null;
    }
    return h.a("MD5", paramContext);
  }
  
  private static SecretKeySpec b()
  {
    Object localObject = com.purplebrain.adbuddiz.sdk.e.g.a().b();
    if (localObject == null) {}
    StringBuilder localStringBuilder;
    for (localObject = null; localObject == null; localObject = localStringBuilder.toString())
    {
      return null;
      localStringBuilder = new StringBuilder();
      int i = 0;
      while (i < 12)
      {
        localStringBuilder.append(((b)localObject).b.charAt(i));
        localStringBuilder.append("D01FX2jKW5g=".charAt(i));
        i += 1;
      }
    }
    return new SecretKeySpec(d.a((String)localObject), "AES");
  }
  
  public static String c(Context paramContext)
  {
    b localB = com.purplebrain.adbuddiz.sdk.e.g.a().b();
    if ((localB == null) || (!localB.a(a.b))) {
      return null;
    }
    if (!h.b(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      return null;
    }
    paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if (paramContext == null) {
      return null;
    }
    return paramContext.getMacAddress();
  }
  
  public static String d(Context paramContext)
  {
    paramContext = e(paramContext);
    if (paramContext == null) {
      return null;
    }
    return h.a("MD5", paramContext);
  }
  
  public static String e(Context paramContext)
  {
    b localB = com.purplebrain.adbuddiz.sdk.e.g.a().b();
    if ((localB == null) || (!localB.a(a.a))) {}
    while (!h.b(paramContext, "android.permission.READ_PHONE_STATE")) {
      return null;
    }
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }
  
  public static List f(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (!com.purplebrain.adbuddiz.sdk.e.g.a().b().a(a.e)) {
      return localArrayList;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      return localArrayList;
    }
    catch (RuntimeException paramContext) {}
  }
  
  public static JSONArray g(Context paramContext)
  {
    Object localObject = com.purplebrain.adbuddiz.sdk.e.g.a().b();
    JSONArray localJSONArray = h.d(paramContext, "r");
    paramContext = new ArrayList();
    int i = 0;
    while (i < localJSONArray.length())
    {
      JSONObject localJSONObject = localJSONArray.getJSONObject(i);
      com.purplebrain.adbuddiz.sdk.h.i localI = new com.purplebrain.adbuddiz.sdk.h.i();
      localI.a = localJSONObject.getString("r");
      localI.b = localJSONObject.getLong("t");
      localI.c = localJSONObject.getLong("d");
      localI.d = localJSONObject.getLong("rs");
      localI.e = localJSONObject.getLong("s");
      if (localJSONObject.has("nt")) {
        localI.f = Integer.valueOf(localJSONObject.getInt("nt"));
      }
      if (localJSONObject.has("nst")) {
        localI.g = Integer.valueOf(localJSONObject.getInt("nst"));
      }
      long l = System.currentTimeMillis() - localI.b;
      if ((localObject == null) || ((l >= 0L) && (l < ((b)localObject).l))) {
        paramContext.add(localI);
      }
      i += 1;
    }
    localObject = new JSONArray();
    paramContext = paramContext.iterator();
    while (paramContext.hasNext()) {
      ((JSONArray)localObject).put(((com.purplebrain.adbuddiz.sdk.h.i)paramContext.next()).a());
    }
    return localObject;
  }
  
  public final o a(boolean paramBoolean)
  {
    this.a = paramBoolean;
    g.a(this.c, "confirmation_enabled", true);
    return this;
  }
  
  public final o b(boolean paramBoolean)
  {
    this.b = paramBoolean;
    g.a(this.c, "results_enabled", true);
    return this;
  }
}
