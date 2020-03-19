package io.presage.common.profig.do;

import agz;
import ahu;
import aje;
import ajs;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import do.VacherinduhautDoubsMontdOr;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class CamembertauCalvados
{
  public static final CamembertauCalvados a = new CamembertauCalvados();
  
  private CamembertauCalvados() {}
  
  public static AbbayeduMontdesCats a(Context paramContext)
  {
    try
    {
      localObject = AbbayedeTamie.a(paramContext);
      String str = ((CamembertdeNormandie)localObject).a();
      if (str != null) {
        return new AbbayeduMontdesCats(str, ((CamembertdeNormandie)localObject).b() ^ true, false);
      }
      throw ((Throwable)new IllegalStateException("androidAdvertisingId is null"));
    }
    catch (Exception localException1)
    {
      Object localObject;
      label50:
      for (;;) {}
    }
    try
    {
      localObject = b(paramContext);
      return localObject;
    }
    catch (Exception localException2)
    {
      break label50;
    }
    return new AbbayeduMontdesCats(c(paramContext), true, true);
  }
  
  private static String a()
  {
    try
    {
      localObject = b();
      if (localObject == null) {
        break label53;
      }
      if (((CharSequence)localObject).length() != 0) {
        break label59;
      }
      i = 1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        label53:
        continue;
        label59:
        int i = 0;
      }
    }
    if (i == 0)
    {
      localObject = ((String)localObject).getBytes(agz.a);
      ajs.a(localObject, "(this as java.lang.String).getBytes(charset)");
      localObject = UUID.nameUUIDFromBytes((byte[])localObject).toString();
      return localObject;
    }
    return null;
  }
  
  private static String a(Context paramContext, ApplicationInfo paramApplicationInfo)
  {
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(paramApplicationInfo.packageName, 128).firstInstallTime;
      paramContext = new StringBuilder();
      paramContext.append(String.valueOf(l));
      paramContext = paramContext.toString();
      paramApplicationInfo = agz.a;
      if (paramContext != null)
      {
        paramContext = paramContext.getBytes(paramApplicationInfo);
        ajs.a(paramContext, "(this as java.lang.String).getBytes(charset)");
        paramContext = UUID.nameUUIDFromBytes(paramContext).toString();
        ajs.a(paramContext, "UUID.nameUUIDFromBytes((…toByteArray()).toString()");
        return paramContext;
      }
      throw new VacherinduhautDoubsMontdOr("null cannot be cast to non-null type java.lang.String");
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "00000000-0000-0000-0000-000000000000";
  }
  
  private static AbbayeduMontdesCats b(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    String str = Settings.Secure.getString(paramContext, "advertising_id");
    boolean bool;
    if (Settings.Secure.getInt(paramContext, "limit_ad_tracking") == 0) {
      bool = true;
    } else {
      bool = false;
    }
    if (str != null) {
      return new AbbayeduMontdesCats(str, bool, false);
    }
    throw ((Throwable)new IllegalStateException("Amazon Fire TV aaid is null"));
  }
  
  private static String b()
    throws IOException
  {
    List localList = aje.c(new File("/sys/class/net/wlan0/address"));
    if ((((Collection)localList).isEmpty() ^ true)) {
      return (String)localList.get(0);
    }
    return null;
  }
  
  private static String c(Context paramContext)
  {
    Object localObject = a();
    if (localObject != null) {
      return localObject;
    }
    localObject = d(paramContext);
    if (localObject == null) {
      return "00000000-0000-0000-0000-000000000000";
    }
    return a(paramContext, (ApplicationInfo)localObject);
  }
  
  private static ApplicationInfo d(Context paramContext)
  {
    try
    {
      paramContext = e(paramContext);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static ApplicationInfo e(Context paramContext)
  {
    if (paramContext != null)
    {
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
    return null;
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
