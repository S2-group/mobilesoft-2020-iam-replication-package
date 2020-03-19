package com.taobao.dp.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import com.taobao.dp.c.g;
import com.taobao.dp.client.IInitResultListener;
import com.taobao.dp.http.DefaultUrlRequestService;
import com.taobao.dp.http.IUrlRequestService;
import com.taobao.wireless.security.adapter.common.SPUtility2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class c
  implements Runnable
{
  private Context a;
  private IUrlRequestService b;
  private String c = null;
  private String d = null;
  private b e;
  private String f;
  private String g;
  private IInitResultListener h;
  private com.taobao.dp.client.a i;
  private h j;
  private boolean k;
  private volatile int l;
  
  public c(Context paramContext, String paramString1, String paramString2, h paramH, com.taobao.dp.client.a paramA, IUrlRequestService paramIUrlRequestService, IInitResultListener paramIInitResultListener, boolean paramBoolean)
  {
    this.a = paramContext;
    this.f = paramString1;
    this.g = paramString2;
    this.j = paramH;
    this.e = new b(paramContext);
    this.i = paramA;
    this.h = paramIInitResultListener;
    if (paramIUrlRequestService != null) {}
    for (this.b = paramIUrlRequestService;; this.b = new DefaultUrlRequestService())
    {
      this.k = paramBoolean;
      this.l = 200;
      return;
    }
  }
  
  private String a(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null) {
      localObject1 = localObject2;
    }
    try
    {
      if (!"".equals(paramString))
      {
        Object localObject3 = new a(this.a);
        localObject1 = ((a)localObject3).f();
        localObject2 = a.o();
        localObject3 = ((a)localObject3).h();
        String str1 = a.m();
        String str2 = a.l();
        String str3 = a.d();
        localObject1 = (String)localObject1 + (String)localObject2 + (String)localObject3 + str1 + str2 + str3;
        com.taobao.dp.a.b.a();
        paramString = com.taobao.dp.a.b.a(paramString, "MD5");
        com.taobao.dp.a.b.a();
        localObject2 = com.taobao.dp.a.b.a((String)localObject1, "MD5");
        localObject1 = new byte[paramString.length + 7];
        System.arraycopy(paramString, 0, localObject1, 0, paramString.length);
        System.arraycopy(localObject2, 4, localObject1, paramString.length, 7);
        int m = com.taobao.dp.c.c.a((byte[])localObject1);
        paramString = new byte[localObject1.length + 1];
        System.arraycopy(localObject1, 0, paramString, 0, localObject1.length);
        paramString[localObject1.length] = m;
        localObject1 = Base64.encodeToString(paramString, 2);
      }
      return localObject1;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private String a(String paramString1, String paramString2, String paramString3)
  {
    a localA = new a(this.a);
    com.taobao.dp.bean.b localB = a.r();
    Object localObject3 = new com.taobao.dp.bean.a();
    Object localObject1 = this.e;
    Object localObject2 = b.a(this.f, this.g, this.a, this.i);
    if (localObject2 == null)
    {
      localObject1 = null;
      localObject2 = new StringBuilder();
      paramString3 = ((StringBuilder)localObject2).append("1.2;").append(paramString3).append(";;").append(paramString1).append(";1;").append(b(a.g())).append(";").append(b(a.t())).append(";").append(b(localA.u())).append(";https;").append(localA.i()).append(";").append(localA.v()).append(";").append(localA.s()).append(";").append(((com.taobao.dp.bean.a)localObject3).a).append(";").append(((com.taobao.dp.bean.a)localObject3).b).append(";").append(b(((com.taobao.dp.bean.a)localObject3).c)).append(";").append(b(((com.taobao.dp.bean.a)localObject3).d)).append(";").append(b(a.d())).append(";").append(b(a.e())).append(";").append(b(Build.MANUFACTURER)).append(";").append(b(Build.MODEL)).append(";");
      if (!a.w()) {
        break label1394;
      }
      paramString1 = "1";
      label303:
      paramString3 = paramString3.append(paramString1).append(";").append(b(a.a())).append(";");
      if (localB != null) {
        break label1401;
      }
      paramString1 = "";
      label336:
      paramString3 = paramString3.append(paramString1).append(";");
      if (localB != null) {
        break label1413;
      }
      paramString1 = "";
      label355:
      paramString3 = paramString3.append(paramString1).append(";").append(b(a.c())).append(";").append(b(a.b())).append(";");
      if (!localA.x()) {
        break label1425;
      }
      paramString1 = "1";
      label405:
      localObject3 = paramString3.append(paramString1).append(";").append(a.y()).append(";").append(a.q()).append(";").append(b(a.n())).append(";").append(b(a.m())).append(";");
      paramString3 = com.taobao.wireless.security.adapter.datacollection.b.c();
      paramString1 = paramString3;
      if (paramString3 == null) {
        paramString1 = "";
      }
      localObject3 = ((StringBuilder)localObject3).append(paramString1).append(";").append(b(Build.DISPLAY)).append(";").append(b(Build.VERSION.INCREMENTAL)).append(";").append(b(Build.DEVICE)).append(";").append(com.taobao.dp.c.h.a(this.a)).append(";").append(b(localA.f())).append(";");
      paramString3 = com.taobao.wireless.security.adapter.datacollection.b.b();
      paramString1 = paramString3;
      if (paramString3 == null) {
        paramString1 = "";
      }
      paramString3 = ((StringBuilder)localObject3).append(paramString1).append(";");
      paramString1 = com.taobao.wireless.security.adapter.datacollection.f.c();
      if (paramString1 == null) {
        break label1432;
      }
      label599:
      paramString3 = paramString3.append(b(paramString1)).append(";");
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = "";
      }
      paramString3 = paramString3.append(paramString1).append(";");
      paramString2 = SPUtility2.readFromSPUnified("DataCollectionData", "key_nick", "");
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = "";
      }
      paramString2 = paramString3.append(b(paramString1)).append(";");
      paramString1 = com.taobao.wireless.security.adapter.datacollection.d.a();
      if (paramString1 == null) {
        break label1438;
      }
      label674:
      paramString2 = paramString2.append(b(paramString1)).append(";");
      paramString1 = com.taobao.wireless.security.adapter.datacollection.d.b();
      if (paramString1 == null) {
        break label1444;
      }
      label696:
      paramString2 = paramString2.append(b(paramString1)).append(";").append(b(a.j())).append(";");
      paramString1 = com.taobao.wireless.security.adapter.datacollection.f.d();
      if (paramString1 == null) {
        break label1450;
      }
      paramString1 = paramString1.trim();
      label737:
      paramString2 = paramString2.append(b(paramString1)).append(";");
      paramString1 = g.a("ro.serialno", null);
      if (paramString1 == null) {
        break label1456;
      }
      paramString1 = paramString1.trim();
      label768:
      paramString2 = paramString2.append(b(paramString1)).append(";").append(localA.z()).append(";").append(b(a.o())).append(";").append(b(a.p())).append(";;;").append(b(a.k())).append(";");
      if (!a.A()) {
        break label1462;
      }
      paramString1 = "1";
      label847:
      paramString2 = paramString2.append(paramString1).append(";").append(b(Build.BOARD)).append(";").append(b(Build.BRAND)).append(";").append(b(Build.PRODUCT)).append(";").append(b(Build.TAGS)).append(";").append(b(g.a("ro.kernel.qemu", "0"))).append(";").append(localA.B()).append(";").append(b(g.a("gsm.version.baseband", ""))).append(";");
      if (localB != null) {
        break label1469;
      }
      paramString1 = "";
      label974:
      paramString2 = paramString2.append(paramString1).append(";");
      if (localB != null) {
        break label1481;
      }
    }
    label1394:
    label1401:
    label1413:
    label1425:
    label1432:
    label1438:
    label1444:
    label1450:
    label1456:
    label1462:
    label1469:
    label1481:
    for (paramString1 = "";; paramString1 = Float.valueOf(localB.c()))
    {
      paramString2 = paramString2.append(paramString1).append(";");
      paramString1 = (String)localObject1;
      if (localObject1 == null) {
        paramString1 = "a000:";
      }
      paramString2.append(paramString1);
      return ((StringBuilder)localObject2).toString();
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(((b.a)localObject2).a);
      localObject2 = ((b.a)localObject2).b.split(",");
      if ((localObject2 != null) && (localObject2.length > 0))
      {
        ((StringBuilder)localObject1).append(',');
        Map localMap = a(this.a);
        byte[] arrayOfByte = new byte[localObject2.length / 8 + 1];
        int m = 0;
        while (m < arrayOfByte.length)
        {
          arrayOfByte[m] = 0;
          m += 1;
        }
        int i3 = localObject2.length;
        int n = 0;
        m = 0;
        Object localObject4;
        int i2;
        int i1;
        while (m < i3)
        {
          localObject4 = localObject2[m];
          i2 = arrayOfByte[(n / 8)];
          i1 = i2;
          if (localMap.containsKey(localObject4)) {
            i1 = i2 | 128 >> n % 8;
          }
          arrayOfByte[(n / 8)] = ((byte)(i1 & 0xFF));
          n += 1;
          m += 1;
        }
        ((StringBuilder)localObject1).append(Base64.encodeToString(arrayOfByte, 2));
        ((StringBuilder)localObject1).append(',');
        localMap = b(this.a);
        m = 0;
        while (m < arrayOfByte.length)
        {
          arrayOfByte[m] = 0;
          m += 1;
        }
        i3 = localObject2.length;
        n = 0;
        m = 0;
        while (m < i3)
        {
          localObject4 = localObject2[m];
          i2 = arrayOfByte[(n / 8)];
          i1 = i2;
          if (localMap.containsKey(localObject4)) {
            i1 = i2 | 128 >> n % 8;
          }
          arrayOfByte[(n / 8)] = ((byte)(i1 & 0xFF));
          n += 1;
          m += 1;
        }
        ((StringBuilder)localObject1).append(Base64.encodeToString(arrayOfByte, 2));
      }
      localObject1 = ((StringBuilder)localObject1).toString();
      break;
      paramString1 = "0";
      break label303;
      paramString1 = Double.valueOf(localB.a());
      break label336;
      paramString1 = Double.valueOf(localB.b());
      break label355;
      paramString1 = "0";
      break label405;
      paramString1 = "";
      break label599;
      paramString1 = "";
      break label674;
      paramString1 = "";
      break label696;
      paramString1 = "";
      break label737;
      paramString1 = "";
      break label768;
      paramString1 = "0";
      break label847;
      paramString1 = Long.valueOf(localB.d());
      break label974;
    }
  }
  
  private static Map a(Context paramContext)
  {
    localHashMap = new HashMap();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {
            localHashMap.put(localPackageInfo.packageName, Integer.valueOf(localPackageInfo.applicationInfo.uid));
          }
        }
      }
      return localHashMap;
    }
    catch (Throwable paramContext) {}
  }
  
  private void a(String paramString1, String paramString2)
  {
    Object localObject2 = new a(this.a);
    String str1 = ((a)localObject2).f();
    Object localObject1 = a.g();
    String str2 = a.o();
    localObject2 = ((a)localObject2).h();
    String str3 = a.m();
    String str4 = a.l();
    String str5 = a.d();
    String str6 = a.e();
    String str7 = a.a();
    String str8 = com.taobao.dp.c.h.a(this.a);
    str1 = str1 + (String)localObject1 + str2 + (String)localObject2 + str3 + str4 + str5 + str6 + str7 + str8;
    com.taobao.dp.a.b.a();
    str1 = com.taobao.dp.c.d.a(com.taobao.dp.a.b.a(str1, "MD5"));
    if ((str1 != null) && (!"".equals(str1)))
    {
      localObject1 = this.e;
      long l1 = b.a();
      long l2 = System.currentTimeMillis();
      if ((paramString2 == null) || (paramString2.length() <= 0) || (l2 - l1 >= 86400000L) || (!str1.equals(this.e.a(this.f, this.g)))) {
        break label225;
      }
    }
    label225:
    while (paramString1 == null) {
      return;
    }
    paramString1 = a(paramString1, paramString2, "3");
    try
    {
      new com.taobao.dp.http.a(this.a, this.f, this.g, this.i, this.b, new e(this, str1)).a(com.taobao.dp.http.c.c, paramString1);
      return;
    }
    catch (Exception paramString1) {}
  }
  
  private static String b(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return "";
    }
    try
    {
      paramString = paramString.replace("^", "\\^").replace(';', '^');
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  private static Map b(Context paramContext)
  {
    localHashMap = new HashMap();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
            localHashMap.put(localPackageInfo.packageName, Integer.valueOf(localPackageInfo.applicationInfo.uid));
          } else if ((localPackageInfo.applicationInfo.flags & 0x80) != 0) {
            localHashMap.put(localPackageInfo.packageName, Integer.valueOf(localPackageInfo.applicationInfo.uid));
          }
        }
      }
      return localHashMap;
    }
    catch (Throwable paramContext) {}
  }
  
  private void b(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      this.c = paramString2;
      this.d = paramString3;
      this.j.notifyDidChanged(this, a(paramString2));
      if ((!com.taobao.dp.http.c.c.equals(paramString1)) && (!com.taobao.dp.http.c.a.equals(paramString1))) {
        a(paramString2, paramString3);
      }
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  private void c(String paramString)
  {
    try
    {
      paramString = a("", paramString, "0");
      new com.taobao.dp.http.a(this.a, this.f, this.g, this.i, this.b, new d(this)).a(com.taobao.dp.http.c.a, paramString);
      return;
    }
    catch (Exception paramString) {}
  }
  
  public final IInitResultListener a()
  {
    return this.h;
  }
  
  public final void a(int paramInt)
  {
    this.l = paramInt;
  }
  
  public final com.taobao.dp.client.a b()
  {
    return this.i;
  }
  
  public final IUrlRequestService c()
  {
    return this.b;
  }
  
  public final boolean d()
  {
    return this.k;
  }
  
  public final int e()
  {
    return this.l;
  }
  
  public final String f()
  {
    return this.c;
  }
  
  public final String g()
  {
    if (this.d == null) {
      return "";
    }
    return this.d;
  }
  
  public final void run()
  {
    this.f = "device_print_res_k1";
    this.g = "";
    try
    {
      Object localObject2 = this.e.a(this.f, this.g, this.i);
      String str1 = this.e.c(this.i);
      if (str1 == null) {
        str1 = "";
      }
      for (;;)
      {
        Object localObject1;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (!"".equals(localObject2)) {}
        }
        else
        {
          localObject1 = this.e;
          localObject1 = b.b(this.i);
          String str2 = ((b.b)localObject1).a;
          String str3 = ((b.b)localObject1).b;
          localObject1 = localObject2;
          if (str2 != null)
          {
            localObject1 = localObject2;
            if (str2.length() > 0)
            {
              localObject1 = localObject2;
              if (!str2.equals(this.f))
              {
                localObject2 = this.e.a(str2, str3, this.i);
                localObject1 = localObject2;
                if (localObject2 != null)
                {
                  localObject1 = localObject2;
                  if (((String)localObject2).length() > 0)
                  {
                    this.e.a((String)localObject2, this.f, this.g, this.i);
                    localObject1 = localObject2;
                  }
                }
              }
            }
          }
        }
        this.d = str1;
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (!"".equals(localObject1)) {}
        }
        else
        {
          localObject1 = this.e.a(this.i);
          if ((localObject1 != null) && (((String)localObject1).length() > 0))
          {
            localObject2 = this.e.a((String)localObject1, this.f, this.g);
            if ((localObject2 != null) && (((String)localObject2).length() > 0)) {
              this.e.a((String)localObject2, this.f, this.g, this.i);
            }
            if (localObject2 != null)
            {
              int m = ((String)localObject2).length();
              if (m != 0) {
                break label374;
              }
            }
            try
            {
              com.taobao.dp.a.a.a();
              localObject1 = com.taobao.dp.a.a.b((String)localObject1);
              new com.taobao.dp.http.a(this.a, this.f, this.g, this.i, this.b, new f(this, str1)).a(com.taobao.dp.http.c.b, new JSONObject((String)localObject1));
              return;
            }
            catch (Exception localException1)
            {
              c(str1);
              return;
            }
          }
          c(str1);
          return;
        }
        label374:
        this.c = ((String)localObject2);
        this.j.notifyDidChanged(this, a((String)localObject2));
        this.j.onInitFinished(this, 200);
        this.e.b((String)localObject2, this.f, this.g, this.i);
        a((String)localObject2, str1);
        return;
      }
      return;
    }
    catch (Exception localException2) {}
  }
}
