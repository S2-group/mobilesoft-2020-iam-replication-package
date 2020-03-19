package com.mobilesdk.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.mobilesdk.d.f;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class b
{
  private TelephonyManager a;
  private Context b;
  private f c;
  
  public b(Context paramContext)
  {
    this.b = paramContext;
    this.c = new f(paramContext);
    this.a = ((TelephonyManager)this.b.getSystemService("phone"));
  }
  
  private String a(String paramString)
  {
    return "7";
  }
  
  private String b(String paramString)
  {
    return "lite";
  }
  
  private String c()
  {
    Object localObject = f();
    if (!((String)localObject).equals("")) {}
    String str;
    do
    {
      do
      {
        return localObject;
        str = d();
        localObject = str;
      } while (!str.equals(""));
      str = e();
      localObject = str;
    } while (!str.equals(""));
    return "";
  }
  
  private String c(String paramString)
  {
    try
    {
      long l = this.b.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      paramString = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new Date(l));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return "";
  }
  
  private String d()
  {
    try
    {
      String str = Settings.Secure.getString(this.b.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private String e()
  {
    if (this.b.getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      try
      {
        String str = this.a.getDeviceId();
        if (str != null) {
          return str;
        }
      }
      catch (Exception localException)
      {
        return "";
      }
    }
    return "";
  }
  
  /* Error */
  private String f()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 23	com/mobilesdk/e/b:c	Lcom/mobilesdk/d/f;
    //   4: getstatic 133	com/mobilesdk/d/e:b	Lcom/mobilesdk/d/e;
    //   7: invokevirtual 136	com/mobilesdk/d/f:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   10: ldc 47
    //   12: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   15: ifne +14 -> 29
    //   18: aload_0
    //   19: getfield 23	com/mobilesdk/e/b:c	Lcom/mobilesdk/d/f;
    //   22: getstatic 133	com/mobilesdk/d/e:b	Lcom/mobilesdk/d/e;
    //   25: invokevirtual 136	com/mobilesdk/d/f:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   28: areturn
    //   29: aload_0
    //   30: getfield 17	com/mobilesdk/e/b:b	Landroid/content/Context;
    //   33: invokevirtual 111	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   36: invokestatic 142	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   39: astore_1
    //   40: aload_1
    //   41: astore_2
    //   42: aload_1
    //   43: ifnonnull +11 -> 54
    //   46: aload_0
    //   47: getfield 17	com/mobilesdk/e/b:b	Landroid/content/Context;
    //   50: invokestatic 142	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   53: astore_2
    //   54: aload_2
    //   55: invokevirtual 147	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   58: astore_1
    //   59: aload_0
    //   60: getfield 23	com/mobilesdk/e/b:c	Lcom/mobilesdk/d/f;
    //   63: getstatic 133	com/mobilesdk/d/e:b	Lcom/mobilesdk/d/e;
    //   66: aload_1
    //   67: invokevirtual 150	com/mobilesdk/d/f:a	(Ljava/lang/Object;Ljava/lang/String;)V
    //   70: aload_1
    //   71: areturn
    //   72: astore_1
    //   73: aload_1
    //   74: invokevirtual 153	java/lang/NullPointerException:printStackTrace	()V
    //   77: ldc 47
    //   79: areturn
    //   80: astore_2
    //   81: aconst_null
    //   82: astore_1
    //   83: aload_2
    //   84: invokevirtual 154	com/google/android/gms/common/GooglePlayServicesNotAvailableException:printStackTrace	()V
    //   87: aload_1
    //   88: astore_2
    //   89: goto -35 -> 54
    //   92: astore_2
    //   93: aconst_null
    //   94: astore_1
    //   95: aload_2
    //   96: invokevirtual 155	com/google/android/gms/common/GooglePlayServicesRepairableException:printStackTrace	()V
    //   99: aload_1
    //   100: astore_2
    //   101: goto -47 -> 54
    //   104: astore_2
    //   105: aconst_null
    //   106: astore_1
    //   107: aload_2
    //   108: invokevirtual 156	java/io/IOException:printStackTrace	()V
    //   111: aload_1
    //   112: astore_2
    //   113: goto -59 -> 54
    //   116: astore_2
    //   117: goto -10 -> 107
    //   120: astore_2
    //   121: goto -26 -> 95
    //   124: astore_2
    //   125: goto -42 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	this	b
    //   39	32	1	localObject1	Object
    //   72	2	1	localNullPointerException	NullPointerException
    //   82	30	1	localObject2	Object
    //   41	14	2	localObject3	Object
    //   80	4	2	localGooglePlayServicesNotAvailableException1	com.google.android.gms.common.GooglePlayServicesNotAvailableException
    //   88	1	2	localObject4	Object
    //   92	4	2	localGooglePlayServicesRepairableException1	com.google.android.gms.common.GooglePlayServicesRepairableException
    //   100	1	2	localObject5	Object
    //   104	4	2	localIOException1	java.io.IOException
    //   112	1	2	localObject6	Object
    //   116	1	2	localIOException2	java.io.IOException
    //   120	1	2	localGooglePlayServicesRepairableException2	com.google.android.gms.common.GooglePlayServicesRepairableException
    //   124	1	2	localGooglePlayServicesNotAvailableException2	com.google.android.gms.common.GooglePlayServicesNotAvailableException
    // Exception table:
    //   from	to	target	type
    //   54	70	72	java/lang/NullPointerException
    //   29	40	80	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   29	40	92	com/google/android/gms/common/GooglePlayServicesRepairableException
    //   29	40	104	java/io/IOException
    //   46	54	116	java/io/IOException
    //   46	54	120	com/google/android/gms/common/GooglePlayServicesRepairableException
    //   46	54	124	com/google/android/gms/common/GooglePlayServicesNotAvailableException
  }
  
  private String g()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        Object localObject = this.b.getPackageManager();
        String str = "{\"apps\":[";
        Iterator localIterator = ((PackageManager)localObject).getInstalledApplications(128).iterator();
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (localApplicationInfo.sourceDir.startsWith("/data/app/"))
          {
            str = str + "{\"title\":\"" + localApplicationInfo.loadLabel((PackageManager)localObject) + "\",";
            str = str + "\"pakeagename\":\"" + localApplicationInfo.packageName + "\"}, ";
            i += 1;
          }
        }
        else
        {
          localObject = str;
          if (i > 0) {
            localObject = str.substring(0, str.length() - 2);
          }
          str = (String)localObject + "]}";
          return str;
        }
      }
      catch (Exception localException)
      {
        return "";
      }
    }
  }
  
  public String a()
  {
    try
    {
      String str1;
      if (Looper.getMainLooper().equals(Looper.myLooper()))
      {
        String str3 = new WebView(this.b).getSettings().getUserAgentString();
        str1 = str3;
        if (!str3.equals(""))
        {
          this.c.a(com.mobilesdk.a.a.b.u, str3);
          return str3;
        }
      }
      else
      {
        str1 = this.c.a(com.mobilesdk.a.a.b.u);
        return str1;
      }
    }
    catch (Exception localException)
    {
      String str2 = this.c.a(com.mobilesdk.a.a.b.u);
      return str2;
    }
  }
  
  public Map a(Map paramMap)
  {
    String str = c();
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.j, str);
    }
    str = d();
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.d, str);
    }
    str = e();
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.e, str);
    }
    str = f();
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.f, str);
    }
    return paramMap;
  }
  
  public String b()
  {
    try
    {
      String str = this.b.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public Map b(Map paramMap)
  {
    String str = b();
    if (!str.equals(""))
    {
      paramMap.put(com.mobilesdk.a.a.b.h, str);
      paramMap.put(com.mobilesdk.a.a.b.i, str);
    }
    str = c(str);
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.m, str);
    }
    str = a(str);
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.r, str);
    }
    str = b(str);
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.s, str);
    }
    return paramMap;
  }
  
  public Map c(Map paramMap)
  {
    String str = g();
    if (!str.equals("")) {
      paramMap.put(com.mobilesdk.a.a.b.t, str);
    }
    return paramMap;
  }
  
  public Map d(Map paramMap)
  {
    return paramMap;
  }
  
  public Map e(Map paramMap)
  {
    return d(a(b(paramMap)));
  }
}
