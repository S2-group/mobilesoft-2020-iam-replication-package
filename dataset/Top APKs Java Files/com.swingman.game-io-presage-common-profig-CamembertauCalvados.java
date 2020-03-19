package io.presage.common.profig;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import do.VacherinduhautDoubsMontdOr;
import do.char.AbbayedeTamie;
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
    Object localObject3;
    try
    {
      Object localObject1 = CamembertdeNormandie.a;
      localObject3 = CamembertdeNormandie.a(paramContext);
      localObject1 = ((CamembertdeNormandie.CamembertauCalvados)localObject3).a();
      if (localObject1 == null) {
        throw ((Throwable)new Exception("androidAdvertisingId is null"));
      }
    }
    catch (Exception localException1) {}
    for (;;)
    {
      String str;
      try
      {
        Object localObject2 = paramContext.getContentResolver();
        localObject3 = Settings.Secure.getString((ContentResolver)localObject2, "advertising_id");
        do.int.if.AffideliceauChablis.a(localObject3, "Settings.Secure.getStrin…solver, \"advertising_id\")");
        if (Settings.Secure.getInt((ContentResolver)localObject2, "limit_ad_tracking") != 0) {
          break label129;
        }
        bool1 = true;
        localObject2 = localObject3;
        if (localObject3 == null) {
          throw ((Throwable)new Exception("aaid is null"));
        }
      }
      catch (Exception localException2)
      {
        str = b(paramContext);
        bool2 = true;
        bool1 = true;
      }
      for (;;)
      {
        return new EcirdelAubrac(str, bool1, bool2);
        bool1 = ((CamembertdeNormandie.CamembertauCalvados)localObject3).b();
        if (!bool1) {
          bool1 = true;
        } else {
          bool1 = false;
        }
      }
      label129:
      boolean bool1 = false;
    }
  }
  
  private static String a()
    throws IOException
  {
    List localList = do.for.EcirdelAubrac.c(new File("/sys/class/net/wlan0/address"));
    if (!((Collection)localList).isEmpty()) {}
    for (int i = 1; i != 0; i = 0) {
      return (String)localList.get(0);
    }
    return null;
  }
  
  private static String b(Context paramContext)
  {
    try
    {
      Object localObject1 = a();
      if (localObject1 != null)
      {
        if (((CharSequence)localObject1).length() == 0) {}
        for (int i = 1; i == 0; i = 0)
        {
          localObject1 = ((String)localObject1).getBytes(AbbayedeTamie.a);
          do.int.if.AffideliceauChablis.a(localObject1, "(this as java.lang.String).getBytes(charset)");
          localObject1 = UUID.nameUUIDFromBytes((byte[])localObject1).toString();
          do.int.if.AffideliceauChablis.a(localObject1, "UUID.nameUUIDFromBytes(m…toByteArray()).toString()");
          return localObject1;
        }
      }
      Object localObject2;
      long l;
      return paramContext;
    }
    catch (Exception localException)
    {
      localObject2 = AbbayedeTimadeuc.a;
      try
      {
        localObject2 = c(paramContext);
        if (localObject2 == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
      }
      catch (Exception paramContext)
      {
        paramContext = AbbayedeTimadeuc.a;
        return "00000000-0000-0000-0000-000000000000";
      }
      try
      {
        l = paramContext.getPackageManager().getPackageInfo(((ApplicationInfo)localObject2).packageName, 128).firstInstallTime;
        paramContext = String.valueOf(l);
        localObject2 = AbbayedeTamie.a;
        if (paramContext == null) {
          throw new VacherinduhautDoubsMontdOr("null cannot be cast to non-null type java.lang.String");
        }
      }
      catch (Exception paramContext)
      {
        paramContext = AbbayedeTimadeuc.a;
        return "00000000-0000-0000-0000-000000000000";
      }
      paramContext = paramContext.getBytes((Charset)localObject2);
      do.int.if.AffideliceauChablis.a(paramContext, "(this as java.lang.String).getBytes(charset)");
      paramContext = UUID.nameUUIDFromBytes(paramContext).toString();
      do.int.if.AffideliceauChablis.a(paramContext, "UUID.nameUUIDFromBytes((…toByteArray()).toString()");
    }
  }
  
  private static ApplicationInfo c(Context paramContext)
  {
    if (paramContext == null) {}
    ArrayList localArrayList;
    do
    {
      do
      {
        return null;
        paramContext = paramContext.getPackageManager();
      } while (paramContext == null);
      paramContext = paramContext.getInstalledApplications(128);
      localArrayList = new ArrayList();
    } while (paramContext.size() == 0);
    do.int.if.AffideliceauChablis.a(paramContext, "apps");
    int i = 0;
    while (i < paramContext.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (((localApplicationInfo.flags & 0x1) != 0) && (localApplicationInfo.packageName != null)) {
        localArrayList.add(localApplicationInfo);
      }
      i += 1;
    }
    do.do.AffideliceauChablis.a((List)localArrayList, (Comparator)CamembertauCalvados.a);
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
      do.int.if.AffideliceauChablis.a(paramApplicationInfo2, "rhs.packageName");
      return paramApplicationInfo1.compareTo(paramApplicationInfo2);
    }
  }
}
