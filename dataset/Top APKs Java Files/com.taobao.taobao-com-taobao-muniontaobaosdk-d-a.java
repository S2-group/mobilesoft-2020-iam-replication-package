package com.taobao.muniontaobaosdk.d;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.taobao.util.TaoLog;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.taobao.statistic.core.Device;
import com.taobao.statistic.core.DeviceInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class a
{
  private static String a;
  private static String b;
  private static Context c;
  private static TelephonyManager d;
  
  public a() {}
  
  public static String getAccept(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new com.taobao.muniontaobaosdk.p4p.a.a.a(paramContext, null).encode(paramString);
      TaoLog.Loge("Munion", "[traceData] error:" + paramString.getMessage());
    }
    catch (Exception paramString)
    {
      try
      {
        new StringBuilder().append("[accept] is :").append(paramContext).toString();
        return paramContext;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      paramString = paramString;
      paramContext = "";
    }
    return paramContext;
  }
  
  public static Context getAppContext()
  {
    return c;
  }
  
  public static int getAppNum()
  {
    try
    {
      int i = c.getPackageManager().getInstalledPackages(0).toArray().length;
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public static String getFileData(String paramString)
    throws FileNotFoundException
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      paramString = c.openFileInput(paramString);
      localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramString.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      localByteArrayOutputStream.close();
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    paramString.close();
    paramString = localByteArrayOutputStream.toString();
    return paramString;
  }
  
  public static String getModel()
  {
    return Build.MODEL;
  }
  
  public static String getOSVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String getUserAgent()
  {
    if (b == null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      Object localObject = Build.VERSION.RELEASE;
      if (((String)localObject).length() > 0)
      {
        localStringBuffer.append((String)localObject);
        localStringBuffer.append("; ");
        localObject = Locale.getDefault();
        String str = ((Locale)localObject).getLanguage();
        if (str == null) {
          break label239;
        }
        localStringBuffer.append(str.toLowerCase());
        localObject = ((Locale)localObject).getCountry();
        if (localObject != null)
        {
          localStringBuffer.append("-");
          localStringBuffer.append(((String)localObject).toLowerCase());
        }
      }
      for (;;)
      {
        localObject = Build.MODEL;
        if (((String)localObject).length() > 0)
        {
          localStringBuffer.append("; ");
          localStringBuffer.append((String)localObject);
        }
        localObject = Build.ID;
        if (((String)localObject).length() > 0)
        {
          localStringBuffer.append(" Build/");
          localStringBuffer.append((String)localObject);
        }
        b = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2 (TAOBAO-ANDROID-%s)", new Object[] { localStringBuffer, "20130606" });
        if (Log.isLoggable("Munion", 3)) {
          new StringBuilder().append("Phone's user-agent is:  ").append(localStringBuffer).toString();
        }
        return b;
        localStringBuffer.append("1.0");
        break;
        label239:
        localStringBuffer.append("en");
      }
    }
    return "";
  }
  
  public static String getUtdid()
  {
    return DeviceInfo.getDevice(c).getUdid();
  }
  
  public static String getUtdid(Context paramContext)
  {
    if ((a == null) && (paramContext != null))
    {
      a = DeviceInfo.getDevice(paramContext).getUdid();
      return a;
    }
    return a;
  }
  
  public static boolean isNetConnect()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)c.getSystemService("connectivity");
    if (localConnectivityManager.getActiveNetworkInfo() != null) {
      return localConnectivityManager.getActiveNetworkInfo().isAvailable();
    }
    return false;
  }
  
  public static void saveToFile(String paramString1, String paramString2)
    throws FileNotFoundException
  {
    if (paramString2 != null) {}
    try
    {
      Context localContext1 = c;
      Context localContext2 = c;
      paramString1 = localContext1.openFileOutput(paramString1, 0);
      paramString1.write(paramString2.getBytes());
      paramString1.close();
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void setAppContext(Context paramContext)
  {
    c = paramContext;
    if (c != null) {
      d = (TelephonyManager)c.getSystemService("phone");
    }
  }
}
