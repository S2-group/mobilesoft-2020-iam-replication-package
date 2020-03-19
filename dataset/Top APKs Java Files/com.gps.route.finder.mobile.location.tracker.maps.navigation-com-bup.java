package com;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public final class bup
{
  private static buh a;
  private static String b = "";
  
  static buh a(Context paramContext)
  {
    if (a != null) {
      return a;
    }
    String str2 = bum.a(paramContext);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!"".equals(str2)) {}
    }
    else
    {
      str1 = b(paramContext);
    }
    try
    {
      a = (buh)new Gson().fromJson(str1, buh.class);
      return a;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private static String a()
  {
    Object localObject1 = bug.a();
    try
    {
      Object localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).update(((String)localObject1).getBytes("UTF-8"));
      localObject1 = ((MessageDigest)localObject2).digest();
      localObject2 = new StringBuilder();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        String str = Integer.toHexString(localObject1[i] & 0xFF);
        if (str.length() == 1) {
          ((StringBuilder)localObject2).append("0");
        }
        ((StringBuilder)localObject2).append(str);
        i += 1;
      }
      localObject1 = ((StringBuilder)localObject2).toString();
      return localObject1;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      return "";
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return "";
  }
  
  public static void a(String paramString)
  {
    b = paramString;
  }
  
  static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  static String b(Context paramContext)
  {
    Object localObject = d(paramContext);
    if ((localObject == null) || ("".equals(localObject))) {
      return null;
    }
    paramContext = a();
    localObject = ((String)localObject).toCharArray();
    paramContext = paramContext.toCharArray();
    int j = 0;
    int i = 0;
    while (j < localObject.length)
    {
      localObject[j] = ((char)(localObject[j] ^ paramContext[i]));
      int k = i + 1;
      i = k;
      if (k == paramContext.length) {
        i = 0;
      }
      j += 1;
    }
    return String.valueOf((char[])localObject);
  }
  
  static boolean c(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
    }
    return false;
  }
  
  private static String d(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Object localObject3 = new byte['Ѐ'];
    try
    {
      paramContext = paramContext.getAssets().open(b);
      for (;;)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        int i = paramContext.read((byte[])localObject3);
        if (i == -1) {
          break;
        }
        localObject1 = paramContext;
        localObject2 = paramContext;
        localByteArrayOutputStream.write((byte[])localObject3, 0, i);
      }
      try
      {
        localByteArrayOutputStream.close();
        if (localObject2 != null) {
          ((InputStream)localObject2).close();
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          localIOException.printStackTrace();
        }
      }
    }
    catch (IOException paramContext)
    {
      paramContext = paramContext;
      localObject2 = localObject1;
      paramContext.printStackTrace();
      try
      {
        localByteArrayOutputStream.close();
        if (localObject1 != null) {
          ((InputStream)localObject1).close();
        }
      }
      catch (IOException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
        }
      }
      localObject2 = "";
      for (;;)
      {
        return localObject2;
        localObject1 = paramContext;
        localObject2 = paramContext;
        localObject3 = localByteArrayOutputStream.toString("UTF-8");
        localObject1 = localObject3;
        try
        {
          localByteArrayOutputStream.close();
          localObject2 = localObject1;
          if (paramContext != null)
          {
            paramContext.close();
            return localObject1;
          }
        }
        catch (IOException paramContext)
        {
          paramContext.printStackTrace();
          return localObject1;
        }
      }
    }
    finally {}
    throw paramContext;
  }
}
