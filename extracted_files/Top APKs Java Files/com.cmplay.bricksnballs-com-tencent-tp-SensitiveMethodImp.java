package com.tencent.tp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SensitiveMethodImp
  implements d
{
  private static final String a = "android.permission.READ_PHONE_STATE";
  private static String b = null;
  private static String c = null;
  
  public SensitiveMethodImp() {}
  
  public String a(Context paramContext)
  {
    if (b != null) {
      return b;
    }
    if (!o.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      b = "NO_PERMISSION_MANIFEST";
      return b;
    }
    if (!o.b(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      b = "NO_PERMISSION_SDK_23";
      return b;
    }
    if (paramContext != null) {
      try
      {
        b = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        return b;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          b = null;
        }
      }
    }
    return b;
  }
  
  public String b(Context paramContext)
  {
    String str = null;
    if (paramContext != null) {
      try
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext != null)
        {
          paramContext = paramContext.getConnectionInfo();
          if (paramContext == null) {
            break label43;
          }
          return paramContext.getSSID();
        }
        return "null";
      }
      catch (Throwable paramContext)
      {
        str = "null";
      }
    }
    return str;
    label43:
    return "null";
  }
  
  public String c(Context paramContext)
  {
    if (paramContext != null) {
      for (;;)
      {
        try
        {
          localObject1 = (WifiManager)paramContext.getSystemService("wifi");
          if (localObject1 == null) {
            continue;
          }
          localObject1 = ((WifiManager)localObject1).getConnectionInfo();
          if (localObject1 == null) {
            continue;
          }
          localObject3 = ((WifiInfo)localObject1).getMacAddress();
          localObject1 = localObject3;
          if (localObject3 != null) {
            localObject1 = ((String)localObject3).replace(":", " ");
          }
        }
        catch (Throwable localThrowable)
        {
          Object localObject1;
          Object localObject3;
          Object localObject2 = null;
          continue;
        }
        if (localObject1 != null)
        {
          localObject3 = localObject1;
          if (!((String)localObject1).equals("02 00 00 00 00 00")) {}
        }
        else
        {
          localObject3 = j(paramContext);
        }
        return localObject3;
        localObject1 = null;
        continue;
        localObject1 = null;
      }
    }
    return null;
  }
  
  public String d(Context paramContext)
  {
    String str = null;
    if (paramContext != null) {
      try
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext != null)
        {
          WifiInfo localWifiInfo = paramContext.getConnectionInfo();
          if (localWifiInfo == null) {
            break label127;
          }
          str = localWifiInfo.getBSSID();
          paramContext = str;
          if (str != null) {
            paramContext = str.replace(":", " ");
          }
          paramContext = paramContext + "(";
          paramContext = paramContext + localWifiInfo.getSSID().replace("\"", "");
          return paramContext + ")";
        }
        return "null";
      }
      catch (Throwable paramContext)
      {
        str = "null";
      }
    }
    return str;
    label127:
    return "null";
  }
  
  public String e(Context paramContext)
  {
    if (c != null) {
      return c;
    }
    if (!o.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      c = "NO_PERMISSION_MANIFEST";
      return c;
    }
    if ((Build.VERSION.SDK_INT >= 23) && (!o.b(paramContext, "android.permission.READ_PHONE_STATE")))
    {
      c = "NO_PERMISSION_SDK_23";
      return c;
    }
    if (paramContext != null) {
      try
      {
        c = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
        return c;
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          c = null;
        }
      }
    }
    return c;
  }
  
  public String f(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (Build.VERSION.SDK_INT > 8) {}
    for (;;)
    {
      try
      {
        paramContext = Build.class.getFields();
        int i = 0;
        if (i < paramContext.length)
        {
          if (paramContext[i].getName().equals("SERIAL"))
          {
            paramContext = paramContext[i];
            localObject1 = localObject2;
            if (paramContext != null) {
              localObject1 = paramContext.get(Build.class).toString();
            }
            return localObject1;
          }
          i += 1;
        }
        else
        {
          paramContext = null;
        }
      }
      catch (Throwable paramContext)
      {
        return null;
      }
    }
  }
  
  public String g(Context paramContext)
  {
    String str = null;
    if (paramContext != null) {}
    try
    {
      str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return str;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public String h(Context paramContext)
  {
    try
    {
      paramContext = Build.FINGERPRINT;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public String i(Context paramContext)
  {
    try
    {
      paramContext = Build.BRAND;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public String j(Context paramContext)
  {
    for (;;)
    {
      Object localObject1;
      try
      {
        localEnumeration = NetworkInterface.getNetworkInterfaces();
        localObject1 = null;
        paramContext = null;
      }
      catch (Throwable paramContext)
      {
        Enumeration localEnumeration;
        NetworkInterface localNetworkInterface;
        Object localObject2;
        int i;
        StringBuilder localStringBuilder;
        int j;
        localObject1 = null;
        paramContext = null;
        continue;
      }
      try
      {
        if (localEnumeration.hasMoreElements())
        {
          localNetworkInterface = (NetworkInterface)localEnumeration.nextElement();
          localObject2 = localNetworkInterface.getClass().getMethods();
          i = 0;
          if (i >= localObject2.length) {
            break label298;
          }
          if (localObject2[i].getName().equals("getHardwareAddress"))
          {
            localObject2 = localObject2[i];
            if (localObject2 == null)
            {
              localObject2 = null;
              return localObject2;
            }
          }
          else
          {
            i += 1;
            continue;
          }
        }
      }
      catch (Throwable localThrowable1)
      {
        continue;
      }
      try
      {
        localObject2 = (byte[])((Method)localObject2).invoke(localNetworkInterface, new Object[0]);
        if ((localObject2 == null) || (localObject2.length == 0)) {
          continue;
        }
        localStringBuilder = new StringBuilder();
        j = localObject2.length;
        i = 0;
        if (i < j)
        {
          localStringBuilder.append(String.format("%02x ", new Object[] { Byte.valueOf(localObject2[i]) }));
          i += 1;
          continue;
        }
        if (localStringBuilder.length() > 0) {
          localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        }
        localObject2 = localStringBuilder.toString();
        if (localNetworkInterface.getName().equals("wlan0"))
        {
          paramContext = (Context)localObject1;
          localObject1 = localObject2;
        }
        else
        {
          boolean bool = localNetworkInterface.getName().equals("eth1");
          if (bool)
          {
            localObject1 = paramContext;
            paramContext = (Context)localObject2;
          }
        }
      }
      catch (Throwable localThrowable2) {}
      localObject2 = paramContext;
      if (paramContext == null)
      {
        if (localObject1 != null) {
          return localObject1;
        }
        return null;
        continue;
        Object localObject3 = paramContext;
        paramContext = (Context)localObject1;
        localObject1 = localObject3;
        break label304;
        label298:
        localObject3 = null;
        continue;
        label304:
        localObject3 = localObject1;
        localObject1 = paramContext;
        paramContext = (Context)localObject3;
      }
    }
  }
  
  public String k(Context paramContext)
  {
    StringBuffer localStringBuffer;
    int i;
    if (paramContext != null)
    {
      localStringBuffer = new StringBuffer();
      if (Build.VERSION.SDK_INT >= 10)
      {
        try
        {
          localClass = Class.forName("android.hardware.Camera");
          j = ((Integer)localClass.getMethod("getNumberOfCameras", new Class[0]).invoke(localClass, new Object[0])).intValue();
          if (j != 0) {
            break label424;
          }
          localObject3 = "N";
          localObject1 = "N";
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            Class localClass;
            int j;
            Object localObject1;
            Object localObject7;
            Object localObject5;
            int k;
            Object localObject4 = "E";
            Object localObject3 = "E";
            continue;
            paramContext = "Y";
            continue;
            localObject2 = "N";
            continue;
            localObject6 = null;
            continue;
            localObject2 = "X";
            localObject3 = "X";
            continue;
            i += 1;
            localObject2 = localObject4;
          }
        }
        localObject4 = Class.forName("android.hardware.Camera$CameraInfo");
        localObject7 = ((Class)localObject4).newInstance();
        localObject5 = localClass.getMethods();
        k = localObject5.length;
        i = 0;
      }
    }
    for (;;)
    {
      Object localObject6;
      if (i < k)
      {
        if (!localObject5[i].getName().equals("getCameraInfo")) {
          break label450;
        }
        localObject6 = localObject5[i];
        Field localField1 = ((Class)localObject4).getField("facing");
        Field localField2 = ((Class)localObject4).getField("CAMERA_FACING_BACK");
        Field localField3 = ((Class)localObject4).getField("CAMERA_FACING_FRONT");
        localObject4 = localObject1;
        localObject5 = localObject3;
        if (localObject6 != null)
        {
          i = 0;
          localObject4 = localObject1;
          localObject5 = localObject3;
          if (i < j)
          {
            localObject6.invoke(localClass, new Object[] { Integer.valueOf(i), localObject7 });
            k = localField1.getInt(localObject7);
            int m = localField2.getInt(localObject7);
            int n = localField3.getInt(localObject7);
            if (k == m)
            {
              localObject1 = "Y";
              localObject4 = localObject1;
              if (j != 1) {
                break label437;
              }
              localObject3 = "N";
              localObject4 = localObject1;
              break label437;
            }
            localObject4 = localObject1;
            if (k != n) {
              break label437;
            }
            localObject5 = "Y";
            localObject4 = localObject1;
            localObject3 = localObject5;
            if (j != 1) {
              break label437;
            }
            localObject4 = "N";
            localObject3 = localObject5;
            break label437;
          }
        }
        localObject3 = localObject4;
        localObject4 = localObject5;
        try
        {
          localObject1 = (SensorManager)paramContext.getSystemService("sensor");
          if (((SensorManager)localObject1).getDefaultSensor(1) != null) {
            break label391;
          }
          paramContext = "N";
          if (((SensorManager)localObject1).getDefaultSensor(4) == null) {
            break label398;
          }
          localObject1 = "Y";
        }
        catch (Throwable paramContext)
        {
          for (;;)
          {
            paramContext = "E";
            localObject2 = "E";
          }
        }
        localStringBuffer.append((String)localObject3).append((String)localObject4).append(paramContext).append((String)localObject1);
        return localStringBuffer.toString();
      }
      label391:
      label398:
      Object localObject2;
      label424:
      label437:
      return null;
      label450:
      i += 1;
    }
  }
  
  public String l(Context paramContext)
  {
    PackageInfo localPackageInfo = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = localPackageInfo;
    if (localPackageManager != null) {}
    try
    {
      paramContext = new Intent("android.intent.action.MAIN");
      paramContext.addCategory("android.intent.category.LAUNCHER");
      paramContext = localPackageManager.resolveActivity(paramContext, 65536).activityInfo.packageName;
      localPackageInfo = localPackageManager.getPackageInfo(paramContext, 64);
      paramContext = w.b(localPackageInfo.signatures[0].toByteArray()) + "(" + paramContext + ")";
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public List m(Context paramContext)
  {
    if (paramContext == null) {
      return new ArrayList();
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return new ArrayList();
    }
    return paramContext.getInstalledPackages(0);
  }
  
  public List n(Context paramContext)
  {
    if (paramContext == null) {
      return new ArrayList();
    }
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return new ArrayList();
    }
    return paramContext.getRunningServices(10000);
  }
}
