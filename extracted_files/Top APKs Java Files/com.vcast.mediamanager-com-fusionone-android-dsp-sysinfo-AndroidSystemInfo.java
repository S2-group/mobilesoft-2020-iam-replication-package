package com.fusionone.android.dsp.sysinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.fusionone.dsp.framework.BundleActivator;
import com.fusionone.dsp.framework.BundleContext;
import com.fusionone.dsp.service.systeminfo.SystemInfo;
import com.synchronoss.android.util.Log;
import java.util.Iterator;
import java.util.List;

public class AndroidSystemInfo
  implements BundleActivator, SystemInfo
{
  protected static Log a;
  private static final String b = AndroidSystemInfo.class.getSimpleName();
  private static TelephonyManager c;
  private static Context d = null;
  
  public AndroidSystemInfo() {}
  
  public static String a()
  {
    a.a(b, "BuA  app reading MDN", new Object[0]);
    Object localObject2 = Settings.System.getString(d.getContentResolver(), "f1.emulate.line1Number");
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((String)localObject2).startsWith("+")) {
        localObject1 = ((String)localObject2).substring(1);
      }
      localObject2 = localObject1;
      if (!b((String)localObject1)) {
        localObject2 = null;
      }
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((String)localObject2).length() == 10) {
          localObject1 = "1" + (String)localObject2;
        }
      }
      a.a(b, "BuA  app emulated MDN=" + (String)localObject1, new Object[0]);
      return localObject1;
    }
    localObject2 = b();
    if ((localObject2 == null) || (((String)localObject2).length() == 0))
    {
      a.a(b, "BuA  app issue root cause  TelephonyManager.getLine1Number() is NULL", new Object[0]);
      localObject2 = null;
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((String)localObject2).startsWith("+")) {
          localObject1 = ((String)localObject2).substring(1);
        }
      }
      localObject2 = localObject1;
      if (!b((String)localObject1)) {
        localObject2 = null;
      }
      localObject1 = localObject2;
      if (localObject2 == null) {
        break;
      }
      localObject1 = localObject2;
      if (((String)localObject2).length() != 10) {
        break;
      }
      return "1" + (String)localObject2;
      a.a(b, "BuA  app found deviceMDN  New ", new Object[0]);
    }
  }
  
  private static String b()
  {
    Object localObject1 = null;
    int i = 1;
    Object localObject3;
    for (;;)
    {
      Object localObject2 = localObject1;
      if (i <= 2L) {
        try
        {
          String str = c.getLine1Number();
          if (str != null)
          {
            localObject1 = str;
            localObject2 = str;
            if (str.length() != 0) {
              break;
            }
          }
          else
          {
            localObject1 = str;
            a.a(b, "BuA  app TelephonyManager.getLine1Number() is NULL, attempt count= %s", new Object[] { Integer.valueOf(i) });
            if (i < 2L)
            {
              localObject1 = str;
              Thread.sleep(1000L);
            }
            i += 1;
            localObject1 = str;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          a.a(b, " BuA  app getRawProperty, getDeviceMDN " + localException, new Object[0]);
          localObject3 = localObject1;
        }
      }
    }
    return localObject3;
  }
  
  private static boolean b(String paramString)
  {
    if (paramString == null) {}
    while ((paramString.startsWith("0000")) || (paramString.startsWith("10000")) || (paramString.length() < 10)) {
      return false;
    }
    return paramString.matches("[0-9]+");
  }
  
  private static String c()
  {
    try
    {
      String str = d.getPackageManager().getPackageInfo(d.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return null;
  }
  
  private static String d()
  {
    String str2 = Build.DEVICE;
    String str1 = str2;
    if (str2 != null) {
      str1 = str2.replace(" ", "");
    }
    return str1;
  }
  
  private static String e()
  {
    Iterator localIterator = d.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.toLowerCase().startsWith("com.android.providers.htc"))
      {
        a.a(b, "******* BuA  app conformOEM workaround found HTC pkg so returning htc", new Object[0]);
        return "htc";
      }
    }
    return null;
  }
  
  private static String f()
  {
    String str1 = null;
    int i = 1;
    String str2;
    for (;;)
    {
      str2 = str1;
      if (i <= 4L)
      {
        str2 = str1;
        try
        {
          str1 = c.getDeviceId();
          str2 = str1;
          if (str1 == null)
          {
            str2 = str1;
            a.a(b, "BuA  app TelephonyManager.getDeviceId() is NULL, attempt count=%d ", new Object[] { Integer.valueOf(i) });
            if (i < 4L)
            {
              str2 = str1;
              Thread.sleep(2000L);
            }
            i += 1;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          a.b(b, "BuA  app getRawProperty, getDeviceId " + localException, new Object[0]);
        }
      }
    }
    return str2;
  }
  
  public final String a(String paramString)
  {
    int j = 1;
    if ("device_id".equals(paramString))
    {
      paramString = Settings.System.getString(d.getContentResolver(), "f1.emulate.deviceIMEI");
      if (paramString == null) {}
    }
    String str;
    int i;
    for (;;)
    {
      if (paramString != null)
      {
        return paramString.toLowerCase();
        paramString = f();
        if (paramString == null)
        {
          a.a(b, "BuA  app issue root cause TelephonyManager.getDeviceId() is NULL, fall back on FB<timestamp in millis>", new Object[0]);
          paramString = "fb" + System.currentTimeMillis();
        }
        else
        {
          a.a(b, "BuA  app devID read", new Object[0]);
          continue;
          if ("device_manufacturer".equals(paramString))
          {
            paramString = Build.MANUFACTURER;
            if (paramString != null)
            {
              str = paramString;
              if (!paramString.toLowerCase().contains("unknown")) {}
            }
            else
            {
              a.a(b, "******* BuA  app unknown manufacturer, reading workaround", new Object[0]);
              str = e();
            }
            paramString = str;
            if (str != null) {
              paramString = str.replace(" ", "");
            }
          }
          else if ("device_model".equals(paramString))
          {
            paramString = d();
          }
          else if ("device_software_version".equals(paramString))
          {
            paramString = c();
          }
          else if ("device_mdn".equals(paramString))
          {
            paramString = a();
          }
          else if ("f1.emulate.sso_token".equals(paramString))
          {
            paramString = Settings.System.getString(d.getContentResolver(), "f1.emulate.sso_token");
            a.a(b, "manualToken is %s ", new Object[] { paramString });
            if (paramString == null) {
              paramString = null;
            }
          }
          else if ("device_sso_client".equals(paramString))
          {
            paramString = Settings.System.getString(d.getContentResolver(), "f1.disable.sso");
            a.a(b, "sso disabled  %s", new Object[] { paramString });
            if (paramString == null)
            {
              paramString = d.getPackageManager();
              if (paramString == null) {
                break label636;
              }
              i = j;
              if (!paramString.hasSystemFeature("com.verizon.hardware.telephony.lte"))
              {
                i = j;
                if (!paramString.hasSystemFeature("com.verizon.hardware.telephony.ehrpd"))
                {
                  i = j;
                  if (!paramString.hasSystemFeature("com.vzw.telephony.lte"))
                  {
                    if (!paramString.hasSystemFeature("com.vzw.telephony.ehrpd")) {
                      break label411;
                    }
                    i = j;
                  }
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        paramString = "true";
        break;
        label411:
        i = 0;
        continue;
      }
      paramString = "false";
      break;
      if ("f1.disable.validation".equals(paramString))
      {
        str = Settings.System.getString(d.getContentResolver(), "f1.disable.validation");
        paramString = str;
        if (str != null) {
          break;
        }
        paramString = null;
        break;
      }
      if ("device_imsi".equals(paramString))
      {
        paramString = c.getSimSerialNumber();
        break;
      }
      if ("network_type".equals(paramString)) {
        switch (c.getNetworkType())
        {
        default: 
          paramString = "unknown";
          break;
        case 2: 
          paramString = "cdma";
          break;
        case 1: 
          paramString = "gsm";
          break;
        }
      }
      if ("firmware".equals(paramString))
      {
        str = Build.VERSION.SDK;
        paramString = str;
        if (str == null) {
          break;
        }
        paramString = str.replace(" ", "");
        break;
      }
      if ("platfrom_version".equals(paramString))
      {
        str = Build.VERSION.RELEASE;
        paramString = str;
        if (str == null) {
          break;
        }
        paramString = str.replace(" ", "");
        break;
      }
      if ("android_device_model".equals(paramString))
      {
        paramString = d();
        break;
      }
      paramString = null;
      break;
      return null;
      label636:
      i = 0;
    }
  }
  
  public void start(BundleContext paramBundleContext)
  {
    Context localContext = (Context)paramBundleContext.d(Context.class.getName());
    d = localContext;
    c = (TelephonyManager)localContext.getSystemService("phone");
    a = (Log)paramBundleContext.d(Log.class.getName());
  }
  
  public void stop(BundleContext paramBundleContext) {}
}
