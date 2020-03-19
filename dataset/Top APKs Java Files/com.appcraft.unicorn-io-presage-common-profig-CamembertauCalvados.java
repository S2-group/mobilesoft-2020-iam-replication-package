package io.presage.common.profig;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import f.c.b;
import f.d.d;
import f.e.k;
import io.presage.common.if.AbbayedeTimadeuc;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class CamembertauCalvados
{
  public static final CamembertauCalvados a = new CamembertauCalvados();
  
  private CamembertauCalvados() {}
  
  public static EcirdelAubrac a(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool3 = true;
    try
    {
      localObject1 = CamembertdeNormandie.a;
      localObject2 = CamembertdeNormandie.a(paramContext);
      localObject1 = ((CamembertdeNormandie.CamembertauCalvados)localObject2).a();
      if (localObject1 != null)
      {
        bool1 = true ^ ((CamembertdeNormandie.CamembertauCalvados)localObject2).b();
        paramContext = (Context)localObject1;
      }
      else
      {
        throw ((Throwable)new Exception("androidAdvertisingId is null"));
      }
    }
    catch (Exception localException1)
    {
      Object localObject1;
      Object localObject2;
      label91:
      for (;;) {}
    }
    try
    {
      localObject2 = paramContext.getContentResolver();
      localObject1 = Settings.Secure.getString((ContentResolver)localObject2, "advertising_id");
      f.g.b.f.a(localObject1, "Settings.Secure.getStrin…solver, \"advertising_id\")");
      if (Settings.Secure.getInt((ContentResolver)localObject2, "limit_ad_tracking") != 0) {
        break label134;
      }
      bool1 = true;
    }
    catch (Exception localException2)
    {
      break label104;
      bool1 = false;
      if (localException2 == null) {
        break label91;
      }
      paramContext = localException2;
      break label113;
    }
    throw ((Throwable)new Exception("aaid is null"));
    label104:
    paramContext = b(paramContext);
    bool2 = true;
    bool1 = bool3;
    label113:
    return new EcirdelAubrac(paramContext, bool1, bool2);
  }
  
  private static String a()
    throws IOException
  {
    List localList = k.c(new File("/sys/class/net/wlan0/address"));
    if ((((Collection)localList).isEmpty() ^ true)) {
      return (String)localList.get(0);
    }
    return null;
  }
  
  private static String b(Context paramContext)
  {
    try
    {
      localObject = a();
      if (localObject == null) {
        break label74;
      }
      if (((CharSequence)localObject).length() != 0) {
        break label204;
      }
      i = 1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        label74:
        continue;
        label204:
        int i = 0;
      }
    }
    if (i == 0)
    {
      localObject = ((String)localObject).getBytes(b.a);
      f.g.b.f.a(localObject, "(this as java.lang.String).getBytes(charset)");
      localObject = UUID.nameUUIDFromBytes((byte[])localObject).toString();
      f.g.b.f.a(localObject, "UUID.nameUUIDFromBytes(m…toByteArray()).toString()");
      return localObject;
      localObject = AbbayedeTimadeuc.a;
    }
    for (;;)
    {
      try
      {
        localObject = c(paramContext);
        if (localObject == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
      }
      catch (Exception paramContext)
      {
        long l;
        continue;
      }
      try
      {
        l = paramContext.getPackageManager().getPackageInfo(((ApplicationInfo)localObject).packageName, 128).firstInstallTime;
        paramContext = new StringBuilder();
        paramContext.append(String.valueOf(l));
        paramContext = paramContext.toString();
        localObject = b.a;
        if (paramContext != null)
        {
          paramContext = paramContext.getBytes((Charset)localObject);
          f.g.b.f.a(paramContext, "(this as java.lang.String).getBytes(charset)");
          paramContext = UUID.nameUUIDFromBytes(paramContext).toString();
          f.g.b.f.a(paramContext, "UUID.nameUUIDFromBytes((…toByteArray()).toString()");
          return paramContext;
        }
        throw new f.f("null cannot be cast to non-null type java.lang.String");
      }
      catch (Exception paramContext) {}
    }
    paramContext = AbbayedeTimadeuc.a;
    return "00000000-0000-0000-0000-000000000000";
    paramContext = AbbayedeTimadeuc.a;
    return "00000000-0000-0000-0000-000000000000";
  }
  
  private static ApplicationInfo c(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    if (paramContext.size() == 0) {
      return null;
    }
    f.g.b.f.a(paramContext, "apps");
    int i = 0;
    while (i < paramContext.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (((localApplicationInfo.flags & 0x1) != 0) && (localApplicationInfo.packageName != null)) {
        localArrayList.add(localApplicationInfo);
      }
      i += 1;
    }
    d.a((List)localArrayList, (Comparator)CamembertauCalvados.a);
    return (ApplicationInfo)localArrayList.get(0);
  }
  
  static final class CamembertauCalvados<T>
    implements Comparator<ApplicationInfo>
  {
    public static final CamembertauCalvados a = new CamembertauCalvados();
    
    CamembertauCalvados() {}
    
    private static int a(ApplicationInfo paramApplicationInfo1, ApplicationInfo paramApplicationInfo2)
    {
      paramApplicationInfo1 = paramApplicationInfo1.packageName;
      paramApplicationInfo2 = paramApplicationInfo2.packageName;
      f.g.b.f.a(paramApplicationInfo2, "rhs.packageName");
      return paramApplicationInfo1.compareTo(paramApplicationInfo2);
    }
  }
}
