package com.mobknowsdk.f;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.mobknowsdk.e.e;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class b
{
  public boolean a = false;
  private TelephonyManager b;
  private Context c;
  private e d;
  
  public b(Context paramContext)
  {
    this.c = paramContext;
    this.d = new e(paramContext);
    this.b = ((TelephonyManager)this.c.getSystemService("phone"));
  }
  
  private String a(String paramString)
  {
    try
    {
      long l = this.c.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      paramString = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new Date(l));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.h, paramString, this.a);
    }
    return "";
  }
  
  private String j()
  {
    try
    {
      String str = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.a, localException, this.a);
    }
    return "";
  }
  
  private String k()
  {
    if (this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      try
      {
        String str = this.b.getDeviceId();
        if (str != null) {
          return str;
        }
      }
      catch (Exception localException)
      {
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.b, localException, this.a);
        return "";
      }
    }
    return "";
  }
  
  private String l()
  {
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      if (!this.d.a(com.mobknowsdk.e.d.c).equals("")) {
        return this.d.a(com.mobknowsdk.e.d.c);
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.o, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.a, true);
      return "";
    }
    if (Looper.getMainLooper().equals(Looper.myLooper())) {
      return "";
    }
    Object localObject1 = null;
    try
    {
      if (this.c.getApplicationContext() != null) {
        localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(this.c.getApplicationContext());
      }
      Object localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = AdvertisingIdClient.getAdvertisingIdInfo(this.c);
      }
      if (localObject2 != null)
      {
        localObject1 = ((AdvertisingIdClient.Info)localObject2).getId();
        this.d.a(com.mobknowsdk.e.d.c, (String)localObject1);
      }
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      for (;;)
      {
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.c, localGooglePlayServicesNotAvailableException, this.a);
      }
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      for (;;)
      {
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.d, localGooglePlayServicesRepairableException, this.a);
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.e, localIOException, this.a);
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.f, localNullPointerException, this.a);
      }
    }
    return this.d.a(com.mobknowsdk.e.d.c, "");
  }
  
  @TargetApi(17)
  private String m()
  {
    try
    {
      String str = WebSettings.getDefaultUserAgent(this.c);
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  /* Error */
  private String n()
  {
    // Byte code:
    //   0: getstatic 218	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 17
    //   5: if_icmplt +19 -> 24
    //   8: aload_0
    //   9: invokespecial 220	com/mobknowsdk/f/b:m	()Ljava/lang/String;
    //   12: astore_1
    //   13: aload_1
    //   14: ldc 88
    //   16: invokevirtual 155	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   19: ifne +5 -> 24
    //   22: aload_1
    //   23: areturn
    //   24: ldc -50
    //   26: iconst_2
    //   27: anewarray 137	java/lang/Class
    //   30: dup
    //   31: iconst_0
    //   32: ldc 31
    //   34: aastore
    //   35: dup
    //   36: iconst_1
    //   37: ldc -34
    //   39: aastore
    //   40: invokevirtual 226	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   43: astore_1
    //   44: aload_1
    //   45: iconst_1
    //   46: invokevirtual 232	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   49: aload_1
    //   50: iconst_2
    //   51: anewarray 4	java/lang/Object
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 21	com/mobknowsdk/f/b:c	Landroid/content/Context;
    //   60: aastore
    //   61: dup
    //   62: iconst_1
    //   63: aconst_null
    //   64: aastore
    //   65: invokevirtual 236	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   68: checkcast 206	android/webkit/WebSettings
    //   71: invokevirtual 239	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   74: astore_2
    //   75: aload_1
    //   76: iconst_0
    //   77: invokevirtual 232	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   80: aload_2
    //   81: areturn
    //   82: astore_1
    //   83: ldc -15
    //   85: invokestatic 246	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   88: areturn
    //   89: astore_2
    //   90: aload_1
    //   91: iconst_0
    //   92: invokevirtual 232	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   95: aload_2
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	b
    //   12	64	1	localObject1	Object
    //   82	9	1	localException	Exception
    //   74	7	2	str	String
    //   89	7	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   24	49	82	java/lang/Exception
    //   75	80	82	java/lang/Exception
    //   90	97	82	java/lang/Exception
    //   49	75	89	finally
  }
  
  private String o()
  {
    return "15";
  }
  
  private String p()
  {
    int i = 0;
    if (this.d.a("check_user_app_bundles", "0").equals("0")) {
      return "";
    }
    for (;;)
    {
      try
      {
        Object localObject = this.c.getPackageManager();
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
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.k, localException, this.a);
        return "";
      }
    }
  }
  
  public String a()
  {
    Object localObject = l();
    if (!((String)localObject).equals("")) {}
    String str;
    do
    {
      do
      {
        return localObject;
        str = j();
        localObject = str;
      } while (!str.equals(""));
      str = k();
      localObject = str;
    } while (!str.equals(""));
    return "";
  }
  
  public Map<Object, String> a(Map<Object, String> paramMap)
  {
    String str = a();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.k, str);
    }
    str = j();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.d, str);
    }
    str = k();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.f, str);
    }
    str = l();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.g, str);
    }
    str = f();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.D, str);
    }
    str = g();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.H, str);
    }
    str = h();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.I, str);
    }
    str = i();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.N, str);
    }
    return paramMap;
  }
  
  public String b()
  {
    String str = n();
    if (str.equals("")) {
      str = this.d.a(com.mobknowsdk.a.a.b.v);
    }
    for (;;)
    {
      if (str.equals("")) {
        com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.g, "EMPTY USER AGENT", this.a);
      }
      return str;
      this.d.a(com.mobknowsdk.a.a.b.v, str);
    }
  }
  
  public Map<Object, String> b(Map<Object, String> paramMap)
  {
    String str = e();
    if (!str.equals(""))
    {
      paramMap.put(com.mobknowsdk.a.a.b.i, str);
      paramMap.put(com.mobknowsdk.a.a.b.j, str);
    }
    str = a(str);
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.n, str);
    }
    str = o();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.s, str);
    }
    str = c();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.t, str);
    }
    return paramMap;
  }
  
  public String c()
  {
    return "1.7.1B";
  }
  
  public Map<com.mobknowsdk.a.a.b, String> c(Map<com.mobknowsdk.a.a.b, String> paramMap)
  {
    String str = p();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.u, str);
    }
    str = d();
    if (!str.equals("")) {
      paramMap.put(com.mobknowsdk.a.a.b.R, str);
    }
    return paramMap;
  }
  
  public String d()
  {
    int j = 0;
    if (this.d.a("check_user_permissions", "0").equals("0")) {
      return "";
    }
    if (Build.VERSION.SDK_INT >= 16) {}
    String str2;
    label189:
    Object localObject;
    for (String str1 = "[";; localObject = str2)
    {
      int i;
      int k;
      try
      {
        PackageInfo localPackageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 4096);
        i = 0;
        if (i < localPackageInfo.requestedPermissions.length)
        {
          k = j;
          str2 = str1;
          if ((localPackageInfo.requestedPermissionsFlags[i] & 0x2) == 0) {
            break label189;
          }
          k = j + 1;
          str2 = str1 + "\"" + localPackageInfo.requestedPermissions[i] + "\",";
          break label189;
        }
        str2 = str1;
        if (j > 0) {
          str2 = str1.substring(0, str1.length() - 1);
        }
        str1 = str2 + "]";
        return str1;
      }
      catch (Exception localException) {}
      return null;
      i += 1;
      j = k;
    }
  }
  
  public Map<Object, String> d(Map<Object, String> paramMap)
  {
    return paramMap;
  }
  
  public String e()
  {
    try
    {
      String str = this.c.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.i, localException, this.a);
    }
    return "";
  }
  
  public Map<Object, String> e(Map<Object, String> paramMap)
  {
    return d(a(b(paramMap)));
  }
  
  public String f()
  {
    try
    {
      String str = "" + Build.VERSION.SDK_INT;
      return str;
    }
    catch (Exception localException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.j, localException, this.a);
    }
    return "";
  }
  
  public String g()
  {
    try
    {
      String str = "" + Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.n, localException, this.a);
    }
    return "";
  }
  
  public String h()
  {
    try
    {
      String str = "" + Build.MANUFACTURER;
      return str;
    }
    catch (Exception localException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.m, localException, this.a);
    }
    return "";
  }
  
  public String i()
  {
    try
    {
      String str = "" + Build.PRODUCT;
      return str;
    }
    catch (Exception localException)
    {
      com.mobknowsdk.b.d.a(this.c, b.class, com.mobknowsdk.b.b.l, localException, this.a);
    }
    return "";
  }
}
