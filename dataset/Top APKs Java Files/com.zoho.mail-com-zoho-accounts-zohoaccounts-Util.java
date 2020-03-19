package com.zoho.accounts.zohoaccounts;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Base64;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;

class Util
{
  private static final String a = "iamlib.properties";
  
  Util() {}
  
  public static int a(String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, "string", paramContext.getPackageName());
  }
  
  static Bitmap a(Context paramContext, UserData paramUserData, String paramString1, String paramString2)
  {
    paramContext = URLUtils.a(paramContext, paramUserData, paramString1);
    paramUserData = new HashMap();
    paramString1 = new StringBuilder();
    paramString1.append("Zoho-oauthtoken ");
    paramString1.append(paramString2);
    paramUserData.put("Authorization", paramString1.toString());
    return NetworkingUtil.b(paramContext, paramUserData);
  }
  
  static String a(Context paramContext)
  {
    return a(paramContext, "instcook");
  }
  
  static String a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("iamlib.properties", 0).getString(paramString, null);
  }
  
  static String a(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.isEmpty()) {
        return paramString;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String[] arrayOfString = paramString[i].split("=");
        localStringBuilder.append(arrayOfString[0]);
        localStringBuilder.append("=");
        localStringBuilder.append(URLEncoder.encode(arrayOfString[1], "UTF-8"));
        localStringBuilder.append("&");
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return paramString;
  }
  
  static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("iamlib.properties", 0).edit().putString(paramString1, paramString2).apply();
  }
  
  static int b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.startsWith("com.zoho")) {
        i += 1;
      }
    }
    return i;
  }
  
  static int b(String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, "color", paramContext.getPackageName());
  }
  
  static IAMErrorCodes b(String paramString)
  {
    IAMErrorCodes[] arrayOfIAMErrorCodes = IAMErrorCodes.a();
    int j = arrayOfIAMErrorCodes.length;
    int i = 0;
    while (i < j)
    {
      IAMErrorCodes localIAMErrorCodes = arrayOfIAMErrorCodes[i];
      if (localIAMErrorCodes.b().equalsIgnoreCase(paramString)) {
        return localIAMErrorCodes;
      }
      i += 1;
    }
    return IAMErrorCodes.n;
  }
  
  static String b(Context paramContext, String paramString)
  {
    paramContext = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(a(paramContext, "privatekey"), 10)));
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", Security.getProvider("BC"));
    localCipher.init(1, paramContext);
    return Base64.encodeToString(localCipher.doFinal(paramString.getBytes("UTF-8")), 0);
  }
  
  static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("iamlib.properties", 0).getString(paramString1, paramString2);
  }
  
  static void b(Context paramContext, UserData paramUserData, String paramString1, String paramString2)
  {
    new Util.1(paramContext, paramUserData, paramString1, paramString2).execute(new Void[0]);
  }
  
  static int c(String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, "layout", paramContext.getPackageName());
  }
  
  static String c(Context paramContext, String paramString)
  {
    paramContext = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(a(paramContext, "privatekey"), 10)));
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", Security.getProvider("BC"));
    localCipher.init(2, paramContext);
    return new String(localCipher.doFinal(Base64.decode(paramString, 0)));
  }
  
  static void c(Context paramContext)
  {
    if ((a(paramContext, "publickey") == null) || (a(paramContext, "privatekey") == null))
    {
      Object localObject1 = KeyPairGenerator.getInstance("RSA", "BC");
      ((KeyPairGenerator)localObject1).initialize(2048);
      Object localObject2 = ((KeyPairGenerator)localObject1).generateKeyPair();
      localObject1 = Base64.encodeToString(((KeyPair)localObject2).getPublic().getEncoded(), 10);
      localObject2 = Base64.encodeToString(((KeyPair)localObject2).getPrivate().getEncoded(), 10);
      a(paramContext, "publickey", (String)localObject1);
      a(paramContext, "privatekey", (String)localObject2);
    }
  }
  
  static int d(String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName());
  }
  
  static String d(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("c_id", "string", paramContext.getPackageName());
    if (i != 0) {
      return paramContext.getResources().getString(i);
    }
    Log.e("c_id not present in strings.xml");
    return null;
  }
  
  private static int e(String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, "array", paramContext.getPackageName());
  }
  
  private static int f(String paramString, Context paramContext)
  {
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
  }
}
