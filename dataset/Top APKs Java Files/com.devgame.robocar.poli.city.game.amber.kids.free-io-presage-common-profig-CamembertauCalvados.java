package io.presage.common.profig;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import do.Aveyronnais;
import do.char.AbbayedeTamie;
import do.do.AbbayeduMontdesCats;
import do.int.if.AffideliceauChablis;
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
    Object localObject2;
    try
    {
      Object localObject1 = CamembertdeNormandie.a;
      localObject2 = CamembertdeNormandie.a(paramContext);
      localObject1 = ((CamembertdeNormandie.CamembertauCalvados)localObject2).a();
      if (localObject1 != null) {
        break label106;
      }
      throw ((Throwable)new Exception("androidAdvertisingId is null"));
    }
    catch (Exception localException1) {}
    boolean bool1;
    try
    {
      localObject2 = paramContext.getContentResolver();
      String str = Settings.Secure.getString((ContentResolver)localObject2, "advertising_id");
      AffideliceauChablis.a(str, "Settings.Secure.getStrin…solver, \"advertising_id\")");
      if (Settings.Secure.getInt((ContentResolver)localObject2, "limit_ad_tracking") == 0)
      {
        bool1 = true;
        if (str != null) {
          break label133;
        }
        throw ((Throwable)new Exception("aaid is null"));
      }
    }
    catch (Exception localException2)
    {
      paramContext = b(paramContext);
      bool2 = true;
      bool1 = true;
    }
    for (;;)
    {
      return new EcirdelAubrac(paramContext, bool1, bool2);
      label106:
      bool1 = ((CamembertdeNormandie.CamembertauCalvados)localObject2).b();
      if (!bool1) {}
      for (bool1 = true;; bool1 = false)
      {
        paramContext = localException2;
        break;
      }
      bool1 = false;
      break;
      label133:
      paramContext = localException2;
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
          AffideliceauChablis.a(localObject1, "(this as java.lang.String).getBytes(charset)");
          localObject1 = UUID.nameUUIDFromBytes((byte[])localObject1).toString();
          AffideliceauChablis.a(localObject1, "UUID.nameUUIDFromBytes(m…toByteArray()).toString()");
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
          throw new Aveyronnais("null cannot be cast to non-null type java.lang.String");
        }
      }
      catch (Exception paramContext)
      {
        paramContext = AbbayedeTimadeuc.a;
        return "00000000-0000-0000-0000-000000000000";
      }
      paramContext = paramContext.getBytes((Charset)localObject2);
      AffideliceauChablis.a(paramContext, "(this as java.lang.String).getBytes(charset)");
      paramContext = UUID.nameUUIDFromBytes(paramContext).toString();
      AffideliceauChablis.a(paramContext, "UUID.nameUUIDFromBytes((…toByteArray()).toString()");
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
    AffideliceauChablis.a(paramContext, "apps");
    int i = 0;
    while (i < paramContext.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
      if (((localApplicationInfo.flags & 0x1) != 0) && (localApplicationInfo.packageName != null)) {
        localArrayList.add(localApplicationInfo);
      }
      i += 1;
    }
    AbbayeduMontdesCats.a((List)localArrayList, (Comparator)CamembertauCalvados.a);
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
      AffideliceauChablis.a(paramApplicationInfo2, "rhs.packageName");
      return paramApplicationInfo1.compareTo(paramApplicationInfo2);
    }
  }
}
