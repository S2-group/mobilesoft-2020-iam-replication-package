package com.igexin.getuiext.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class h
{
  public h() {}
  
  public static int a(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return -1;
  }
  
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int j = Integer.valueOf(paramString2).intValue();
    int i = 0;
    while (i < paramContext.size())
    {
      paramString2 = (PackageInfo)paramContext.get(i);
      if (paramString1.equals(paramString2.packageName))
      {
        if (paramString2.versionCode < j) {
          return paramString2.versionCode;
        }
        return -2;
      }
      i += 1;
    }
    return -1;
  }
  
  public static String a(Context paramContext)
  {
    Object localObject1 = null;
    Object localObject2 = paramContext.getApplicationContext().getPackageName();
    try
    {
      localObject2 = paramContext.getPackageManager().getApplicationInfo((String)localObject2, 128);
      paramContext = localObject1;
      if (((ApplicationInfo)localObject2).metaData != null) {
        paramContext = ((ApplicationInfo)localObject2).metaData.getString("PUSH_APPID");
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static JSONObject a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    int i = a(paramContext, paramString1, paramString2);
    if ((i == -2) || ((!paramBoolean) && (i == -1))) {
      return null;
    }
    paramString2 = new JSONObject();
    try
    {
      paramString2.put("packageName", paramString1);
      if (i == -1) {}
      for (paramContext = "";; paramContext = b(paramContext, paramString1))
      {
        paramString2.put("checksum", paramContext);
        paramString2.put("versionCode", i);
        return paramString2;
      }
      return paramString2;
    }
    catch (JSONException paramContext) {}
  }
  
  public static String b(Context paramContext, String paramString)
  {
    String str = e.a(paramContext, paramString);
    if (str != null) {
      return b(paramContext, paramString, str);
    }
    return "0";
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = "0";
    Object localObject2 = new File(paramString2);
    paramString1 = paramContext;
    paramString2 = paramContext;
    try
    {
      Object localObject1 = MessageDigest.getInstance("MD5");
      paramString1 = paramContext;
      if (localObject1 != null)
      {
        paramString1 = paramContext;
        paramString2 = paramContext;
        localObject2 = new FileInputStream((File)localObject2);
        paramString1 = paramContext;
        paramString2 = paramContext;
        byte[] arrayOfByte1 = new byte['⠀'];
        for (;;)
        {
          paramString1 = paramContext;
          paramString2 = paramContext;
          int i = ((FileInputStream)localObject2).read(arrayOfByte1);
          if (i <= 0) {
            break;
          }
          if (i < 10240)
          {
            paramString1 = paramContext;
            paramString2 = paramContext;
            byte[] arrayOfByte2 = new byte[i];
            paramString1 = paramContext;
            paramString2 = paramContext;
            System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
            paramString1 = paramContext;
            paramString2 = paramContext;
            ((MessageDigest)localObject1).update(arrayOfByte2);
          }
          else
          {
            paramString1 = paramContext;
            paramString2 = paramContext;
            ((MessageDigest)localObject1).update(arrayOfByte1);
          }
        }
        paramString1 = paramContext;
        paramString2 = paramContext;
        localObject1 = ((MessageDigest)localObject1).digest();
        paramString1 = paramContext;
        paramString2 = paramContext;
        paramContext = g.a((byte[])localObject1, 0, localObject1.length);
        paramString1 = paramContext;
        paramString2 = paramContext;
        ((FileInputStream)localObject2).close();
        return paramContext;
      }
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      return "0";
    }
    catch (FileNotFoundException paramContext)
    {
      return paramString1;
    }
    catch (IOException paramContext) {}
    return paramString2;
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
    }
    return false;
  }
  
  public static int c()
  {
    return new Random(System.currentTimeMillis()).nextInt(1000);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1);
      if (paramContext != null) {
        return true;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public void a()
  {
    Object localObject = Environment.getExternalStorageDirectory().getPath();
    localObject = new File((String)localObject + "/libs");
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdir();
    }
    localObject = new File(((File)localObject).getPath() + "/notify.db");
    if (((File)localObject).exists()) {
      ((File)localObject).delete();
    }
    try
    {
      if (((File)localObject).createNewFile())
      {
        localObject = new FileOutputStream((File)localObject);
        ((FileOutputStream)localObject).write(String.valueOf(System.currentTimeMillis()).getBytes());
        ((FileOutputStream)localObject).close();
      }
      return;
    }
    catch (IOException localIOException) {}catch (FileNotFoundException localFileNotFoundException) {}
  }
  
  public long b()
  {
    int i = 0;
    long l1 = 0L;
    Object localObject = new File(Environment.getExternalStorageDirectory().getPath() + "/libs/notify.db");
    long l2 = l1;
    long l3;
    long l4;
    if (((File)localObject).exists())
    {
      l2 = l1;
      l3 = l1;
      l4 = l1;
    }
    try
    {
      localObject = new FileInputStream((File)localObject);
      l2 = l1;
      l3 = l1;
      l4 = l1;
      byte[] arrayOfByte1 = new byte[30];
      for (;;)
      {
        l2 = l1;
        l3 = l1;
        l4 = l1;
        int j = ((FileInputStream)localObject).read(arrayOfByte1, i, 15);
        if (j == -1) {
          break;
        }
        i += j;
      }
      l2 = l1;
      l3 = l1;
      l4 = l1;
      byte[] arrayOfByte2 = new byte[i];
      l2 = l1;
      l3 = l1;
      l4 = l1;
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
      l2 = l1;
      l3 = l1;
      l4 = l1;
      l1 = Long.parseLong(new String(arrayOfByte2));
      l2 = l1;
      l3 = l1;
      l4 = l1;
      ((FileInputStream)localObject).close();
      l2 = l1;
      return l2;
    }
    catch (Exception localException)
    {
      return l2;
    }
    catch (IOException localIOException)
    {
      return l3;
    }
    catch (FileNotFoundException localFileNotFoundException) {}
    return l4;
  }
}
