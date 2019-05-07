package com.xtify.android.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.xmlpull.v1.XmlSerializer;

class aw
{
  private static final String a = MainService.class.getName();
  private String b;
  private Long c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private boolean i = false;
  private String j;
  private String k;
  private Integer l;
  private Integer m;
  private String n;
  private String o;
  private List<l> p = new ArrayList();
  
  aw() {}
  
  public aw a(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    l localL = null;
    int i1 = TimeZone.getDefault().getOffset(System.currentTimeMillis());
    a(paramString);
    b(Integer.valueOf(i1));
    i(TimeZone.getDefault().getID());
    j(Locale.getDefault().toString());
    this.p.clear();
    if (!paramBoolean1) {
      paramString = (TelephonyManager)paramContext.getSystemService("phone");
    }
    for (;;)
    {
      try
      {
        i1 = Integer.parseInt(Build.VERSION.SDK);
        if (i1 <= 3) {}
      }
      catch (Exception localException)
      {
        Object localObject;
        Iterator localIterator;
        continue;
      }
      try
      {
        localObject = Build.class.getDeclaredField("MANUFACTURER");
        if (localObject != null) {
          b((String)((Field)localObject).get(null));
        }
      }
      catch (IllegalAccessException localIllegalAccessException) {}catch (IllegalArgumentException localIllegalArgumentException) {}catch (NoSuchFieldException localNoSuchFieldException) {}catch (SecurityException localSecurityException)
      {
        continue;
      }
    }
    c(Build.DEVICE);
    d(paramString.getSimOperatorName());
    e(paramString.getSimOperator());
    f(paramString.getNetworkOperatorName());
    a(paramString.isNetworkRoaming());
    g(Build.VERSION.RELEASE);
    h(Build.MODEL);
    a(Integer.valueOf(1045));
    a(Long.valueOf(System.currentTimeMillis()));
    if (paramBoolean2)
    {
      localObject = paramContext.getPackageManager();
      localIterator = ((PackageManager)localObject).getInstalledPackages(4).iterator();
      paramContext = localL;
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.packageName == null) || ((!localPackageInfo.packageName.startsWith("android.")) && (!localPackageInfo.packageName.startsWith("com.android.")) && (!localPackageInfo.packageName.equals("android"))))
        {
          paramString = localPackageInfo.applicationInfo;
          ServiceInfo[] arrayOfServiceInfo = localPackageInfo.services;
          localL = new l();
          label333:
          int i2;
          if (paramString.labelRes != 0)
          {
            localL.a((String)((PackageManager)localObject).getText(localPackageInfo.packageName, paramString.labelRes, paramString));
            localL.b(localPackageInfo.packageName);
            localL.c(localPackageInfo.versionName);
            paramString = paramContext;
            if (arrayOfServiceInfo != null)
            {
              i2 = arrayOfServiceInfo.length;
              i1 = 0;
            }
          }
          for (;;)
          {
            paramString = paramContext;
            if (i1 < i2)
            {
              paramString = arrayOfServiceInfo[i1];
              if ((paramString != null) && (a.equals(paramString.name)))
              {
                localL.a(true);
                if (paramContext != null) {
                  break label532;
                }
                paramContext = new Intent(MainService.a);
                paramContext.addCategory(MainService.a);
                paramContext = ((PackageManager)localObject).queryIntentServices(paramContext, 96);
                localL.a(a((PackageManager)localObject, paramContext, localPackageInfo.packageName));
                paramString = paramContext;
              }
            }
            else
            {
              this.p.add(localL);
              paramContext = paramString;
              break;
              if (paramString.nonLocalizedLabel == null) {
                break label333;
              }
              localL.a((String)paramString.nonLocalizedLabel);
              break label333;
            }
            i1 += 1;
          }
        }
      }
    }
    return this;
  }
  
  public Integer a(PackageManager paramPackageManager, List<ResolveInfo> paramList, String paramString)
  {
    paramPackageManager = paramList.iterator();
    while (paramPackageManager.hasNext())
    {
      paramList = (ResolveInfo)paramPackageManager.next();
      if (paramString.equals(paramList.serviceInfo.packageName))
      {
        paramList = paramList.filter;
        if (paramList != null)
        {
          int i2 = paramList.countCategories();
          int i1 = 0;
          while (i1 < i2)
          {
            String str = paramList.getCategory(i1);
            if ((str != null) && (str.matches(MainService.a + ".V[1-9][0-9]*"))) {
              return Integer.valueOf(Integer.parseInt(str.replace(MainService.a + ".V", "")));
            }
            i1 += 1;
          }
        }
      }
    }
    return null;
  }
  
  public void a(Integer paramInteger)
  {
    this.l = paramInteger;
  }
  
  public void a(Long paramLong)
  {
    this.c = paramLong;
  }
  
  public void a(String paramString)
  {
    this.b = paramString;
  }
  
  public void a(String paramString, long paramLong)
  {
    Object localObject = this.p.iterator();
    while (((Iterator)localObject).hasNext())
    {
      l localL = (l)((Iterator)localObject).next();
      if ((localL != null) && (localL.a() != null) && (localL.a().equals(paramString))) {
        localL.a(paramLong);
      }
    }
    for (int i1 = 1;; i1 = 0)
    {
      if (i1 == 0)
      {
        localObject = new l();
        ((l)localObject).b(paramString);
        ((l)localObject).a(paramLong);
        this.p.add(localObject);
      }
      return;
    }
  }
  
  public void a(XmlSerializer paramXmlSerializer, String paramString)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    Object localObject2 = null;
    paramXmlSerializer.startTag(paramString, "deviceStatistics");
    HashMap localHashMap = new HashMap();
    localHashMap.put("deviceId", this.b);
    if (this.c == null)
    {
      localObject1 = null;
      localHashMap.put("timestamp", localObject1);
      localHashMap.put("manufacturer", this.d);
      localHashMap.put("model", this.e);
      localHashMap.put("simOperator", this.f);
      localHashMap.put("simMccMnc", this.g);
      localHashMap.put("network", this.h);
      if (this.i) {
        break label380;
      }
      localObject1 = null;
      label129:
      localHashMap.put("isRoaming", localObject1);
      if (this.l != null) {
        break label391;
      }
      localObject1 = null;
      label148:
      localHashMap.put("sdkVersion", localObject1);
      if (this.m != null) {
        break label405;
      }
    }
    label380:
    label391:
    label405:
    for (Object localObject1 = localObject2;; localObject1 = Integer.toString(this.m.intValue()))
    {
      localHashMap.put("gmtOffset", localObject1);
      localHashMap.put("timezone", this.n);
      localHashMap.put("locale", this.o);
      a(paramXmlSerializer, paramString, localHashMap);
      if (((this.j != null) && (this.j.length() > 0)) || ((this.k != null) && (this.k.length() > 0)))
      {
        paramXmlSerializer.startTag(paramString, "os");
        localObject1 = new HashMap();
        ((HashMap)localObject1).put("version", this.j);
        ((HashMap)localObject1).put("make", this.k);
        a(paramXmlSerializer, paramString, (HashMap)localObject1);
        paramXmlSerializer.endTag(paramString, "os");
      }
      if (this.p.size() <= 0) {
        break label430;
      }
      paramXmlSerializer.startTag(paramString, "apps");
      localObject1 = this.p.iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((l)((Iterator)localObject1).next()).a(paramXmlSerializer, paramString);
      }
      localObject1 = Long.toString(this.c.longValue());
      break;
      localObject1 = Boolean.toString(this.i);
      break label129;
      localObject1 = Integer.toString(this.l.intValue());
      break label148;
    }
    paramXmlSerializer.endTag(paramString, "apps");
    label430:
    paramXmlSerializer.endTag(paramString, "deviceStatistics");
  }
  
  public void a(XmlSerializer paramXmlSerializer, String paramString, HashMap<String, String> paramHashMap)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)paramHashMap.get(str1);
      if (str2 != null)
      {
        paramXmlSerializer.startTag(paramString, str1);
        paramXmlSerializer.text(str2);
        paramXmlSerializer.endTag(paramString, str1);
      }
    }
  }
  
  public void a(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }
  
  public void b(Integer paramInteger)
  {
    this.m = paramInteger;
  }
  
  public void b(String paramString)
  {
    this.d = paramString;
  }
  
  public void b(String paramString, long paramLong)
  {
    Object localObject = this.p.iterator();
    while (((Iterator)localObject).hasNext())
    {
      l localL = (l)((Iterator)localObject).next();
      if ((localL != null) && (localL.a() != null) && (localL.a().equals(paramString))) {
        localL.b(paramLong);
      }
    }
    for (int i1 = 1;; i1 = 0)
    {
      if (i1 == 0)
      {
        localObject = new l();
        ((l)localObject).b(paramString);
        ((l)localObject).b(paramLong);
        this.p.add(localObject);
      }
      return;
    }
  }
  
  public void c(String paramString)
  {
    this.e = paramString;
  }
  
  public void d(String paramString)
  {
    this.f = paramString;
  }
  
  public void e(String paramString)
  {
    this.g = paramString;
  }
  
  public void f(String paramString)
  {
    this.h = paramString;
  }
  
  public void g(String paramString)
  {
    this.j = paramString;
  }
  
  public void h(String paramString)
  {
    this.k = paramString;
  }
  
  public void i(String paramString)
  {
    this.n = paramString;
  }
  
  public void j(String paramString)
  {
    this.o = paramString;
  }
}
