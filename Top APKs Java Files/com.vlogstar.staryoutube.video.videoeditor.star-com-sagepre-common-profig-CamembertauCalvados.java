package com.sagepre.common.profig;

import agz;
import ahu;
import aje;
import ajs;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import com.sagepre.common.if.AbbayedeTimadeuc;
import do.VacherinduhautDoubsMontdOr;
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
    for (;;)
    {
      boolean bool1;
      try
      {
        localObject1 = CamembertdeNormandie.a;
        localObject2 = CamembertdeNormandie.a(paramContext);
        localObject1 = ((CamembertdeNormandie.CamembertauCalvados)localObject2).a();
        if (localObject1 == null) {
          throw ((Throwable)new Exception("androidAdvertisingId is null"));
        }
        bool1 = ((CamembertdeNormandie.CamembertauCalvados)localObject2).b();
        bool1 = true ^ bool1;
        paramContext = (Context)localObject1;
      }
      catch (Exception localException1)
      {
        Object localObject1;
        Object localObject2;
        continue;
      }
      try
      {
        localObject2 = paramContext.getContentResolver();
        localObject1 = Settings.Secure.getString((ContentResolver)localObject2, "advertising_id");
        ajs.a(localObject1, "Settings.Secure.getStrin…solver, \"advertising_id\")");
        if (Settings.Secure.getInt((ContentResolver)localObject2, "limit_ad_tracking") != 0) {
          break label147;
        }
        bool1 = true;
      }
      catch (Exception localException2)
      {
        continue;
        bool1 = false;
      }
    }
    if (localObject1 == null) {
      throw ((Throwable)new Exception("aaid is null"));
    }
    paramContext = (Context)localObject1;
    break label126;
    paramContext = b(paramContext);
    bool2 = true;
    bool1 = bool3;
    label126:
    return new EcirdelAubrac(paramContext, bool1, bool2);
  }
  
  private static String a()
    throws IOException
  {
    List localList = aje.c(new File("/sys/class/net/wlan0/address"));
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
      localObject = ((String)localObject).getBytes(agz.a);
      ajs.a(localObject, "(this as java.lang.String).getBytes(charset)");
      localObject = UUID.nameUUIDFromBytes((byte[])localObject).toString();
      ajs.a(localObject, "UUID.nameUUIDFromBytes(m…toByteArray()).toString()");
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
        localObject = agz.a;
        if (paramContext == null) {
          throw new VacherinduhautDoubsMontdOr("null cannot be cast to non-null type java.lang.String");
        }
        paramContext = paramContext.getBytes((Charset)localObject);
        ajs.a(paramContext, "(this as java.lang.String).getBytes(charset)");
        paramContext = UUID.nameUUIDFromBytes(paramContext).toString();
        ajs.a(paramContext, "UUID.nameUUIDFromBytes((…toByteArray()).toString()");
        return paramContext;
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
    ajs.a(paramContext, "apps");
    int i = 0;
    while (i < paramContext.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (((localApplicationInfo.flags & 0x1) != 0) && (localApplicationInfo.packageName != null)) {
        localArrayList.add(localApplicationInfo);
      }
      i += 1;
    }
    ahu.a((List)localArrayList, (Comparator)CamembertauCalvados.a);
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
      ajs.a(paramApplicationInfo2, "rhs.packageName");
      return paramApplicationInfo1.compareTo(paramApplicationInfo2);
    }
  }
}
