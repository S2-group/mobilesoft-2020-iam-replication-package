package com.prism.gaia.client.hook.d.af;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IInterface;
import android.os.Process;
import com.prism.gaia.client.b.c;
import com.prism.gaia.client.f.l;
import com.prism.gaia.d;
import com.prism.gaia.helper.c.j;
import com.prism.gaia.helper.utils.m;
import com.prism.gaia.os.GaiaUserHandle;
import com.prism.gaia.server.s;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class a
{
  a() {}
  
  static class a
    extends com.prism.gaia.client.hook.a.e
  {
    a() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (ComponentName)paramVarArgs[0];
      paramMethod = (Intent)paramVarArgs[1];
      paramVarArgs = (String)paramVarArgs[2];
      return Boolean.valueOf(l.a().a(paramObject, paramMethod, paramVarArgs));
    }
    
    public String m()
    {
      return "activitySupportsIntent";
    }
  }
  
  static class aa
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(aa.class);
    
    aa() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      PermissionGroupInfo localPermissionGroupInfo = l.a().a(str, i);
      if (localPermissionGroupInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG name:", str, " use INSIDE" });
        return localPermissionGroupInfo;
      }
      paramObject = (PermissionGroupInfo)paramMethod.invoke(paramObject, new Object[] { paramMethod, paramVarArgs });
      if (paramObject != null)
      {
        paramMethod = c.d().a().getApplicationInfo(paramObject.packageName, 128);
        if ((paramMethod != null) && (com.prism.gaia.helper.utils.e.b(paramMethod)))
        {
          m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG name:", str, " use OUTSIDE ", paramObject });
          return paramObject;
        }
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG name:", str, " use NULL" });
      return null;
    }
    
    public String m()
    {
      return "getPermissionGroupInfo";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class ab
    extends com.prism.gaia.client.hook.a.e
  {
    ab() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getPermissions";
    }
  }
  
  static class ac
    extends com.prism.gaia.client.hook.a.e
  {
    ac() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.b(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getPreferredActivities";
    }
  }
  
  static class ad
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(ad.class);
    
    ad() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      ComponentName localComponentName = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      if (com.prism.gaia.b.a(localComponentName.getPackageName()))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use OUTSIDE or NULL because host" });
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      int j = GaiaUserHandle.myVuserId();
      ProviderInfo localProviderInfo = l.a().d(localComponentName, i, j);
      if (localProviderInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use INSIDE" });
        return localProviderInfo;
      }
      paramObject = (ProviderInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject == null) || (!com.prism.gaia.helper.utils.e.b(paramObject.applicationInfo)))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use NULL" });
        return null;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use OUTSIDE" });
      m.d(a, "call system getProviderInfo with args: %s", paramVarArgs);
      return paramObject;
    }
    
    public String m()
    {
      return "getProviderInfo";
    }
  }
  
  static class ae
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(ae.class);
    
    ae() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      ComponentName localComponentName = (ComponentName)paramVarArgs[0];
      if (com.prism.gaia.b.a(localComponentName.getPackageName())) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      int i = ((Integer)paramVarArgs[1]).intValue();
      ActivityInfo localActivityInfo = l.a().a(localComponentName, i, 0);
      if (localActivityInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use INSIDE" });
        return localActivityInfo;
      }
      paramObject = (ActivityInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject == null) || (!com.prism.gaia.helper.utils.e.b(paramObject.applicationInfo)))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use NULL" });
        return null;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG comp:", localComponentName, " use OUTSIDE" });
      m.d(a, "call system getReceiverInfo with args: %s", paramVarArgs);
      return paramObject;
    }
    
    public String m()
    {
      return "getReceiverInfo";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class af
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(af.class);
    
    af() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      ComponentName localComponentName = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = GaiaUserHandle.myVuserId();
      ServiceInfo localServiceInfo = l.a().c(localComponentName, i, j);
      if (localServiceInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " component:", localComponentName, " service:", localServiceInfo.toString(), " use INSIDE" });
        return localServiceInfo;
      }
      paramObject = (ServiceInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject != null) && (com.prism.gaia.helper.utils.e.b(paramObject.applicationInfo)))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " component:", localComponentName, " service:", paramObject.toString(), " use OUTSIDE" });
        return paramObject;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " component:", localComponentName, " use NULL" });
      return paramObject;
    }
    
    public String m()
    {
      return "getServiceInfo";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class ag
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(ag.class);
    
    ag() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      if (com.prism.gaia.a.a.a().a(str)) {
        return Boolean.valueOf(true);
      }
      if (d.a(str)) {
        return Boolean.valueOf(false);
      }
      m.d(a, "call system isPackageAvailable with args: %s", paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "isPackageAvailable";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class ah
    extends com.prism.gaia.client.hook.a.e
  {
    ah() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return Boolean.valueOf(false);
    }
    
    public String m()
    {
      return "isPackageForzen";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class ai
    extends com.prism.gaia.client.hook.a.e
  {
    ai() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[2]).intValue();
      paramVarArgs = l.a().d(paramObject, i, 0);
      paramObject = paramVarArgs;
      if (j.a(paramMethod)) {
        paramObject = j.a(paramVarArgs);
      }
      return paramObject;
    }
    
    public String m()
    {
      return "queryContentProviders";
    }
  }
  
  static class aj
    extends com.prism.gaia.client.hook.a.e
  {
    aj() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = j.a(paramMethod);
      int i = GaiaUserHandle.myVuserId();
      paramMethod = l.a().e((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod;
      if (bool) {
        paramObject = j.a(paramMethod);
      }
      return paramObject;
    }
    
    public String m()
    {
      return "queryIntentActivities";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  @TargetApi(19)
  static class ak
    extends com.prism.gaia.client.hook.a.e
  {
    ak() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      j.a(paramMethod);
      int i = GaiaUserHandle.myVuserId();
      paramVarArgs = l.a().c((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramVarArgs;
      if (j.a(paramMethod)) {
        paramObject = j.a(paramVarArgs);
      }
      return paramObject;
    }
    
    public String m()
    {
      return "queryIntentContentProviders";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class al
    extends com.prism.gaia.client.hook.a.e
  {
    al() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = j.a(paramMethod);
      int i = GaiaUserHandle.myVuserId();
      paramMethod = l.a().d((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod;
      if (bool) {
        paramObject = j.a(paramMethod);
      }
      return paramObject;
    }
    
    public String m()
    {
      return "queryIntentReceivers";
    }
  }
  
  static class am
    extends com.prism.gaia.client.hook.a.e
  {
    am() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = j.a(paramMethod);
      int i = GaiaUserHandle.myVuserId();
      paramMethod = l.a().f((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod;
      if (bool) {
        paramObject = j.a(paramMethod);
      }
      return paramObject;
    }
    
    public String m()
    {
      return "queryIntentServices";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class an
    extends com.prism.gaia.client.hook.a.e
  {
    an() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "removePackageFromPreferred";
    }
  }
  
  static class ao
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(ao.class);
    
    ao() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = GaiaUserHandle.myVuserId();
      ProviderInfo localProviderInfo = l.a().c(str, i, j);
      if (localProviderInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " authority:", str, " use INSIDE" });
        return localProviderInfo;
      }
      paramObject = (ProviderInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject == null) || (!com.prism.gaia.helper.utils.e.b(paramObject.applicationInfo)))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " authority:", str, " use NULL over OUTSIDE" });
        return null;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " authority:", str, " use OUTSIDE" });
      return paramObject;
    }
    
    public String m()
    {
      return "resolveContentProvider";
    }
  }
  
  static class ap
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(ap.class);
    
    ap() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Intent localIntent = (Intent)paramVarArgs[0];
      Object localObject = (String)paramVarArgs[1];
      int i = ((Integer)paramVarArgs[2]).intValue();
      int j = GaiaUserHandle.myVuserId();
      localObject = l.a().b(localIntent, (String)localObject, i, j);
      if (localObject != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG intent:", localIntent, " use INSIDE" });
        return localObject;
      }
      paramObject = (ResolveInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if (paramObject == null)
      {
        m.a(a, "INSIDE_OUTSIDE_TAG", " resolved null  USE NULL 1");
        return null;
      }
      paramMethod = c.d().a().getApplicationInfo(paramObject.activityInfo.packageName, 128);
      if (!com.prism.gaia.helper.utils.e.b(paramMethod))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " resolveInfo: ", paramObject, " app flags:", Integer.toHexString(paramMethod.flags), " intent:", localIntent, " use NULL 2" });
        return null;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG intent:", localIntent, " use OUTSIDE" });
      m.d(a, "call system resolveIntent with args: %s", paramVarArgs);
      return paramObject;
    }
    
    public String m()
    {
      return "resolveIntent";
    }
  }
  
  static class aq
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(aq.class);
    
    aq() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Intent localIntent = (Intent)paramVarArgs[0];
      Object localObject = (String)paramVarArgs[1];
      int i = ((Integer)paramVarArgs[2]).intValue();
      int j = GaiaUserHandle.myVuserId();
      localObject = l.a().a(localIntent, (String)localObject, i, j);
      if (localObject != null) {
        return localObject;
      }
      paramObject = (ResolveInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if (paramObject != null)
      {
        paramMethod = com.prism.gaia.helper.utils.e.a(paramObject);
        paramMethod = c.d().a().getApplicationInfo(paramMethod, 128);
        if ((paramMethod != null) && (com.prism.gaia.helper.utils.e.b(paramMethod)))
        {
          m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG intent:", localIntent, " use OUTSIDE or NULL" });
          return paramObject;
        }
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG intent:", localIntent, " use NULL" });
      return null;
    }
    
    public String m()
    {
      return "resolveService";
    }
  }
  
  static class ar
    extends com.prism.gaia.client.hook.a.e
  {
    ar() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "revokeRuntimePermission";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class as
    extends com.prism.gaia.client.hook.a.e
  {
    as() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "setApplicationBlockedSettingAsUser";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class at
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(at.class);
    
    at() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return Integer.valueOf(0);
    }
    
    public String m()
    {
      return "setApplicationEnabledSetting";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class au
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(au.class);
    
    au() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = ((Integer)paramVarArgs[2]).intValue();
      int k = ((Integer)paramVarArgs[3]).intValue();
      m.a(a, new Object[] { "SetComponentEnabledSetting( ", paramVarArgs, " )" });
      l.a().a(paramObject, i, j, k);
      return Integer.valueOf(0);
    }
    
    public String m()
    {
      return "setComponentEnabledSetting";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class av
    extends com.prism.gaia.client.hook.a.e
  {
    av() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "setPackageStoppedState";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class aw
    extends com.prism.gaia.client.hook.a.e
  {
    aw() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      ((Integer)paramVarArgs[0]).intValue();
      ((Integer)paramVarArgs[1]).intValue();
      return Integer.valueOf(0);
    }
    
    public String m()
    {
      return "checkUidSignatures";
    }
  }
  
  static class ax
    extends com.prism.gaia.client.hook.a.e
  {
    ax() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      return l.a().c(i);
    }
    
    public String m()
    {
      return "getNameForUid";
    }
  }
  
  static class b
    extends com.prism.gaia.client.hook.a.e
  {
    b() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return Integer.valueOf(0);
    }
    
    public String m()
    {
      return "addPackageToPreferred";
    }
  }
  
  static class c
    extends com.prism.gaia.client.hook.a.e
  {
    c() {}
    
    public Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2)
    {
      return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2);
    }
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (String)paramVarArgs[0];
      paramMethod = (String)paramVarArgs[1];
      int i = GaiaUserHandle.myVuserId();
      return Integer.valueOf(l.a().a(paramObject, paramMethod, i));
    }
    
    public String m()
    {
      return "checkPermission";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  static class d
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(d.class);
    
    d() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      if ((paramVarArgs.length == 2) && ((paramVarArgs[0] instanceof String)) && ((paramVarArgs[1] instanceof String)))
      {
        Object localObject1 = c.d().i().getPackageManager();
        Object localObject3 = (String)paramVarArgs[0];
        Object localObject2 = (String)paramVarArgs[1];
        m.g(a, "check signature matched for pkg(%s) and pkg(%s)", new Object[] { localObject3, localObject2 });
        try
        {
          localObject3 = ((PackageManager)localObject1).getPackageInfo((String)localObject3, 64);
          localObject1 = ((PackageManager)localObject1).getPackageInfo((String)localObject2, 64);
          localObject2 = ((PackageInfo)localObject3).signatures;
          localObject1 = ((PackageInfo)localObject1).signatures;
          if (com.prism.gaia.helper.utils.a.a((Object[])localObject2))
          {
            if (!com.prism.gaia.helper.utils.a.a((Object[])localObject1)) {
              return Integer.valueOf(-1);
            }
            return Integer.valueOf(1);
          }
          if (com.prism.gaia.helper.utils.a.a((Object[])localObject1)) {
            return Integer.valueOf(-2);
          }
          if (Arrays.equals((Object[])localObject2, (Object[])localObject1)) {
            return Integer.valueOf(0);
          }
          return Integer.valueOf(-3);
        }
        catch (Throwable localThrowable) {}
      }
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "checkSignatures";
    }
  }
  
  static class e
    extends com.prism.gaia.client.hook.a.e
  {
    e() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "clearPackagePersistentPreferredActivities";
    }
  }
  
  static class f
    extends com.prism.gaia.client.hook.a.e
  {
    f() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "clearPackagePreferredActivities";
    }
  }
  
  static class g
    extends com.prism.gaia.client.hook.a.e
  {
    g() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "deleteApplicationCacheFiles";
    }
  }
  
  static class h
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(h.class);
    
    h() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      m.a(a, "call ", paramVarArgs);
      paramObject = (String)paramVarArgs[0];
      try
      {
        com.prism.gaia.a.a.a().c(paramObject);
        paramMethod = (IPackageDeleteObserver2)paramVarArgs[1];
        if (paramMethod != null) {
          paramMethod.onPackageDeleted(paramObject, 0, "done.");
        }
      }
      catch (Throwable paramObject)
      {
        for (;;) {}
      }
      return Integer.valueOf(0);
    }
    
    public String m()
    {
      return "deletePackage";
    }
  }
  
  static class i
    extends a.h
  {
    private static final String a = com.prism.gaia.b.a(i.class);
    
    i() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return super.b(paramObject, paramMethod, paramVarArgs);
    }
    
    public String m()
    {
      return "deletePackageAsUser";
    }
  }
  
  static class j
    extends com.prism.gaia.client.hook.a.e
  {
    j() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      if ((paramVarArgs[(paramVarArgs.length - 1)] instanceof IPackageDataObserver)) {
        ((IPackageDataObserver)paramVarArgs[(paramVarArgs.length - 1)]).onRemoveCompleted(null, true);
      }
      return Integer.valueOf(0);
    }
    
    public String m()
    {
      return "freeStorageAndNotify";
    }
  }
  
  static class k
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(k.class);
    
    k() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      ComponentName localComponentName = (ComponentName)paramVarArgs[0];
      if (com.prism.gaia.b.a(localComponentName.getPackageName())) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      int i = GaiaUserHandle.myVuserId();
      int j = ((Integer)paramVarArgs[1]).intValue();
      ActivityInfo localActivityInfo = l.a().b(localComponentName, j, i);
      if (localActivityInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " info:", localActivityInfo, " use INSIDE" });
        return localActivityInfo;
      }
      paramObject = (ActivityInfo)paramMethod.invoke(paramObject, paramVarArgs);
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " comp:", localComponentName, " use OUTSIDE or NULL" });
      if ((paramObject == null) || (!com.prism.gaia.helper.utils.e.b(paramObject.applicationInfo))) {
        return null;
      }
      m.d(a, "call system getActivityInfo with args: %s", paramVarArgs);
      return paramObject;
    }
    
    public String m()
    {
      return "getActivityInfo";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class l
    extends com.prism.gaia.client.hook.a.e
  {
    l() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getApplicationBlockedSettingAsUser";
    }
  }
  
  static class m
    extends com.prism.gaia.client.hook.a.e
  {
    m() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getApplicationEnabledSetting";
    }
  }
  
  static class n
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(n.class);
    
    n() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = GaiaUserHandle.myVuserId();
      ApplicationInfo localApplicationInfo = l.a().b(str, i, j);
      if (localApplicationInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " pkg:", str, " use INSIDE" });
        return localApplicationInfo;
      }
      paramObject = (ApplicationInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject != null) && (com.prism.gaia.helper.utils.e.b(paramObject)))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " pkg:", str, " use OUTSIDE" });
        return paramObject;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " pkg:", str, " use NULL" });
      return null;
    }
    
    public String m()
    {
      return "getApplicationInfo";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class o
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(o.class);
    
    o() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      ComponentName localComponentName = (ComponentName)paramVarArgs[0];
      if (localComponentName != null) {
        return Integer.valueOf(l.a().a(localComponentName, 0));
      }
      m.d(a, "call system getComponentEnabledSetting with args: %s", paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getComponentEnabledSetting";
    }
  }
  
  static class p
    extends com.prism.gaia.client.hook.a.e
  {
    p() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = GaiaUserHandle.myVuserId();
      paramVarArgs = l.a().a(i, j);
      paramObject = paramVarArgs;
      if (j.a(paramMethod)) {
        paramObject = j.a(paramVarArgs);
      }
      return paramObject;
    }
    
    public String m()
    {
      return "getInstalledApplications";
    }
  }
  
  static class q
    extends com.prism.gaia.client.hook.a.e
  {
    q() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = GaiaUserHandle.myVuserId();
      if (d()) {}
      for (paramObject = new ArrayList(com.prism.gaia.a.a.a().c());; paramObject = c.d().a().getInstalledPackages(i))
      {
        paramObject.addAll(l.a().b(i, j));
        paramVarArgs = paramObject;
        if (j.a(paramMethod)) {
          paramVarArgs = j.a(paramObject);
        }
        return paramVarArgs;
      }
    }
    
    public String m()
    {
      return "getInstalledPackages";
    }
  }
  
  static class r
    extends com.prism.gaia.client.hook.a.e
  {
    r() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return "com.android.vending";
    }
    
    public String m()
    {
      return "getInstallerPackageName";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class s
    extends com.prism.gaia.client.hook.a.e
  {
    s() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      com.prism.gaia.client.hook.e.a.a(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getPackageGids";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class t
    extends a.s
  {
    t() {}
    
    public String m()
    {
      return super.m() + "Etc";
    }
  }
  
  static final class u
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(u.class);
    
    u() {}
    
    public boolean a(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (paramVarArgs != null)
      {
        bool1 = bool2;
        if (paramVarArgs[0] != null) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      m.a(a, "call: pkg:", str);
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = GaiaUserHandle.myVuserId();
      PackageInfo localPackageInfo = l.a().a(str, i, j);
      if (localPackageInfo != null)
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " pkg:", str, " use INSIDE" });
        return localPackageInfo;
      }
      paramObject = (PackageInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject != null) && (com.prism.gaia.helper.utils.e.b(c.d().a().getApplicationInfo(paramObject.packageName, 128))))
      {
        m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " pkg:", str, " use OUTSIDE" });
        return paramObject;
      }
      m.a(a, new Object[] { "INSIDE_OUTSIDE_TAG", " pkg:", str, " use NULL" });
      return null;
    }
    
    public String m()
    {
      return "getPackageInfo";
    }
    
    public boolean n()
    {
      boolean bool = c.d().k();
      m.a(a, "isEnable isguest:", Boolean.valueOf(bool));
      return bool;
    }
  }
  
  static class v
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(v.class);
    
    v() {}
    
    public Object b(Object paramObject, final Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (IInterface)paramMethod.invoke(paramObject, paramVarArgs);
      paramMethod = l.a().c();
      Proxy.newProxyInstance(paramObject.getClass().getClassLoader(), paramObject.getClass().getInterfaces(), new InvocationHandler()
      {
        public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
        {
          throw new Runtime("d2j fail translate: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.genRegGraph(UnSSATransformer.java:344)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:276)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
        }
      });
    }
    
    public String m()
    {
      return "getPackageInstaller";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class w
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(w.class);
    
    w() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      if (com.prism.gaia.b.a(str)) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      return Integer.valueOf(GaiaUserHandle.getVappId(l.a().d(str, 0)));
    }
    
    public String m()
    {
      return "getPackageUid";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  static class x
    extends a.w
  {
    x() {}
    
    public String m()
    {
      return super.m() + "Etc";
    }
  }
  
  static class y
    extends com.prism.gaia.client.hook.a.e
  {
    private static final String a = com.prism.gaia.b.a(y.class);
    
    y() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = Binder.getCallingUid();
      if (i == c.d().s()) {
        i = h();
      }
      for (;;)
      {
        paramObject = l.a().b(j);
        paramMethod = l.a().b(i);
        paramVarArgs = l.a().b(Process.myUid());
        com.prism.gaia.helper.b.b localB = new com.prism.gaia.helper.b.b(2);
        if ((paramMethod != null) && (paramMethod.length > 0)) {
          localB.addAll(Arrays.asList(paramMethod));
        }
        for (;;)
        {
          paramObject = (String[])localB.toArray(new String[localB.size()]);
          m.a(a, new Object[] { "call uid:", Integer.valueOf(i), " callingUid:", Integer.valueOf(j), "  result:", paramObject });
          return paramObject;
          if ((paramObject != null) && (paramObject.length > 0)) {
            localB.addAll(Arrays.asList(paramObject));
          } else if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
            localB.addAll(Arrays.asList(paramVarArgs));
          }
        }
      }
    }
    
    public String m()
    {
      return "getPackagesForUid";
    }
    
    public boolean n()
    {
      return d();
    }
  }
  
  @TargetApi(17)
  static class z
    extends com.prism.gaia.client.hook.a.e
  {
    z() {}
    
    public Object b(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String m()
    {
      return "getPermissionFlags";
    }
  }
}
