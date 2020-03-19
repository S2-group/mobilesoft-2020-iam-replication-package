package com.lbe.doubleagent;

import Reflection.CtorDef;
import Reflection.MethodDef;
import Reflection.android.content.ParceledListSlice;
import Reflection.android.content.ParceledListSliceJellyBeanMR2;
import Reflection.android.content.pm.IPackageDataObserver;
import Reflection.android.content.pm.IPackageInstaller;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageInstallObserver2;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Process;
import android.text.TextUtils;
import com.lbe.doubleagent.client.DACapabilities;
import com.lbe.doubleagent.client.b;
import com.lbe.doubleagent.client.g;
import com.lbe.doubleagent.client.n;
import com.lbe.doubleagent.service.packageinstaller.PackageInstallInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bg
  extends ac
{
  public static final String a = "package";
  private static final String b = "LBE-Sec";
  private static Map<String, Signature[]> d = new HashMap();
  
  protected bg(Context paramContext, IInterface paramIInterface)
  {
    super(paramContext, paramIInterface, "package");
  }
  
  private static void a(PackageInfo paramPackageInfo)
  {
    if (d.get(paramPackageInfo.packageName) != null) {
      paramPackageInfo.signatures = ((Signature[])d.get(paramPackageInfo.packageName));
    }
    for (;;)
    {
      return;
      Object localObject2 = new File(n.e(b.c()), paramPackageInfo.packageName);
      if (((File)localObject2).exists()) {
        try
        {
          FileInputStream localFileInputStream = new FileInputStream((File)localObject2);
          Object localObject1 = new byte[(int)((File)localObject2).length()];
          int i = localFileInputStream.read((byte[])localObject1);
          localFileInputStream.close();
          if (i == ((File)localObject2).length())
          {
            localObject2 = Parcel.obtain();
            ((Parcel)localObject2).unmarshall((byte[])localObject1, 0, localObject1.length);
            ((Parcel)localObject2).setDataPosition(0);
            int j = ((Parcel)localObject2).readInt();
            if (j > 0)
            {
              localObject1 = new Signature[j];
              i = 0;
              while (i < j)
              {
                localObject1[i] = ((Signature)Signature.CREATOR.createFromParcel((Parcel)localObject2));
                i += 1;
              }
              paramPackageInfo.signatures = ((Signature[])localObject1);
              d.put(paramPackageInfo.packageName, localObject1);
              return;
            }
          }
        }
        catch (FileNotFoundException paramPackageInfo) {}catch (IOException paramPackageInfo) {}
      }
    }
  }
  
  private static void d(List<PackageInfo> paramList)
  {
    if (paramList == null) {}
    for (;;)
    {
      return;
      int j;
      for (int i = 0; i < paramList.size(); i = j + 1)
      {
        j = i;
        if (bx.a(((PackageInfo)paramList.get(i)).packageName))
        {
          paramList.remove(i);
          j = i - 1;
        }
      }
    }
  }
  
  private static void e(List<ApplicationInfo> paramList)
  {
    if (paramList == null) {}
    for (;;)
    {
      return;
      int j;
      for (int i = 0; i < paramList.size(); i = j + 1)
      {
        j = i;
        if (bx.a(((ApplicationInfo)paramList.get(i)).packageName))
        {
          paramList.remove(i);
          j = i - 1;
        }
      }
    }
  }
  
  private static void f(List<String> paramList)
  {
    if (paramList == null) {}
    for (;;)
    {
      return;
      int j;
      for (int i = 0; i < paramList.size(); i = j + 1)
      {
        j = i;
        if (bx.a((String)paramList.get(i)))
        {
          paramList.remove(i);
          j = i - 1;
        }
      }
    }
  }
  
  protected boolean a()
  {
    return true;
  }
  
  protected void b()
  {
    this.j.put("getPackageInfo", new ad());
    this.j.put("getPackageUid", new ag(null));
    this.j.put("getPackageUidEtc", new ag(null));
    this.j.put("isPackageFrozen", new ay());
    this.j.put("isPackageAvailable", new ax());
    this.j.put("getPackageGids", new ac());
    this.j.put("getPackageGidsEtc", new ac());
    this.j.put("getPermissionInfo", new al());
    this.j.put("getPermissionGroupInfo", new ak());
    this.j.put("queryPermissionsByGroup", new bi());
    this.j.put("getAllPermissionGroups", new q());
    this.j.put("getPackagesHoldingPermissions", new ai(null));
    this.j.put("getApplicationInfo", new s());
    this.j.put("getActivityInfo", new p(null));
    this.j.put("getReceiverInfo", new aq(null));
    this.j.put("getServiceInfo", new ar(null));
    this.j.put("getProviderInfo", new ap(null));
    this.j.put("checkPermission", new f(null));
    this.j.put("addPermission", new b(null));
    this.j.put("removePermission", new bl(null));
    this.j.put("grantPermission", new at(null));
    this.j.put("revokePermission", new bs(null));
    this.j.put("grantRuntimePermission", new au());
    this.j.put("revokeRuntimePermission", new bt());
    this.j.put("resetRuntimePermissions", new bo());
    this.j.put("getPermissionFlags", new aj());
    this.j.put("updatePermissionFlags", new bz());
    this.j.put("updatePermissionFlagsForAllApps", new ca());
    this.j.put("shouldShowRequestPermissionRationale", new by());
    this.j.put("checkSignatures", new g(null));
    this.j.put("getNameForUid", new ab(null));
    this.j.put("getUidForSharedUser", new as(null));
    this.j.put("getFlagsForUid", new u(null));
    this.j.put("resolveIntent", new bq(null));
    this.j.put("canForwardTo", new e());
    this.j.put("queryIntentActivities", new bd(null));
    this.j.put("queryIntentActivityOptions", new be(null));
    this.j.put("queryIntentReceivers", new bg(null));
    this.j.put("resolveService", new br(null));
    this.j.put("queryIntentServices", new bh(null));
    this.j.put("queryIntentContentProviders", new bf(null));
    this.j.put("getInstalledApplications", new w(null));
    this.j.put("getPersistentApplications", new am(null));
    this.j.put("resolveContentProvider", new bp(null));
    this.j.put("queryContentProviders", new bb(null));
    this.j.put("querySliceContentProviders", new bb(null));
    this.j.put("getInstrumentationInfo", new z(null));
    this.j.put("queryInstrumentation", new bc(null));
    this.j.put("getInstallerPackageName", new y(null));
    this.j.put("addPackageToPreferred", new a(null));
    this.j.put("removePackageFromPreferred", new bk(null));
    this.j.put("addPreferredActivity", new c(null));
    this.j.put("replacePreferredActivity", new bm(null));
    this.j.put("clearPackagePreferredActivities", new j(null));
    this.j.put("getPreferredActivities", new an(null));
    this.j.put("getHomeActivities", new v(null));
    this.j.put("setComponentEnabledSetting", new bv(null));
    this.j.put("getComponentEnabledSetting", new t(null));
    this.j.put("setApplicationEnabledSetting", new bu(null));
    this.j.put("getApplicationEnabledSetting", new r(null));
    this.j.put("setPackageStoppedState", new bx(null));
    this.j.put("deleteApplicationCacheFiles", new k(null));
    this.j.put("clearApplicationUserData", new i(null));
    this.j.put("getPackageSizeInfo", new af(null));
    this.j.put("performDexOpt", new ba(null));
    this.j.put("movePackage", new az(null));
    this.j.put("getPackagesForUid", new ah(null));
    this.j.put("getInstalledPackages", new x(null));
    if (Build.VERSION.SDK_INT >= 21) {
      this.j.put("getPackageInstaller", new ae(null));
    }
    if (Build.VERSION.SDK_INT >= 24) {
      this.j.put("checkPackageStartable", new d());
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      this.j.put("addOnPermissionsChangeListener", new ae.a(null));
      this.j.put("removeOnPermissionsChangeListener", new ae.a(null));
    }
    this.j.put("addPermission", new ae.a(Boolean.valueOf(true)));
    this.j.put("addPermissionAsync", new ae.a(Boolean.valueOf(true)));
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.j.put("installPackage", new aw());
      this.j.put("installPackageAsUser", new aw());
      if (Build.VERSION.SDK_INT < 18) {
        break label1832;
      }
      this.j.put("deletePackageAsUser", new l());
      if (Build.VERSION.SDK_INT >= 21) {
        this.j.put("deletePackage", new m());
      }
    }
    for (;;)
    {
      this.j.put("freeStorageAndNotify", new o());
      this.j.put("freeStorage", new n());
      return;
      if (Build.VERSION.SDK_INT >= 17)
      {
        this.j.put("installPackage", new av());
        this.j.put("installPackageWithVerification", new av());
        this.j.put("installPackageWithVerificationAndEncryption", new av());
        break;
      }
      if (Build.VERSION.SDK_INT < 14) {
        break;
      }
      this.j.put("installPackage", new av());
      this.j.put("installPackageWithVerification", new av());
      break;
      label1832:
      this.j.put("deletePackage", new l());
    }
  }
  
  static class a
    extends ae
  {
    private a() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  static class aa
    extends ae
  {
    private aa() {}
  }
  
  static class ab
    extends ae
  {
    private ab() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (((Integer)paramArrayOfObject[0]).intValue() == Process.myUid()))
      {
        a(b.h());
        return true;
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class ac
    extends ae
  {
    protected ac() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().g(str)) {
          paramArrayOfObject[0] = paramContext.getPackageName();
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class ad
    extends ae
  {
    protected ad() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 instanceof PackageInfo)) {
        n.a().a(new PackageInfo[] { (PackageInfo)paramObject2 });
      }
      return paramObject2;
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        Object localObject = (String)paramArrayOfObject[0];
        if (bx.a((String)localObject))
        {
          a(null);
          return true;
        }
        if ((paramArrayOfObject.length > 1) && ((paramArrayOfObject[1] instanceof Integer)))
        {
          localObject = n.a().a((String)localObject, ((Integer)paramArrayOfObject[1]).intValue());
          if (localObject != null)
          {
            a(localObject);
            return true;
          }
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class ae
    extends ae
  {
    private ae() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 instanceof IInterface))
      {
        paramObject1 = new bf(paramContext, (IInterface)paramObject2);
        return (IInterface)Proxy.newProxyInstance(paramObject2.getClass().getClassLoader(), new Class[] { IPackageInstaller.Class }, paramObject1);
      }
      return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
    }
  }
  
  static class af
    extends ae
  {
    private af() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        int i = ac.a(paramArrayOfObject, IPackageStatsObserver.class, 0);
        if ((i > 0) && (n.a().f(str)))
        {
          n.a().a(str, (IPackageStatsObserver)paramArrayOfObject[i]);
          a(null);
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class ag
    extends ae
  {
    private ag() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramObject = (String)paramArrayOfObject[0];
        if (n.a().f(paramObject)) {
          if (n.b().a(paramObject)) {
            a(Integer.valueOf(Process.myUid()));
          }
        }
        for (;;)
        {
          return true;
          a(Integer.valueOf(90000));
          continue;
          if (bx.a((String)paramArrayOfObject[0])) {
            a(Integer.valueOf(-1));
          }
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class ah
    extends ae
  {
    private ah() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      paramObject1 = paramObject2;
      if ((paramObject2 instanceof String[]))
      {
        paramObject1 = (String[])paramObject2;
        paramObject1 = n.a().a(paramObject1);
      }
      return paramObject1;
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      int j = 0;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof Integer)))
      {
        int i = ((Integer)paramArrayOfObject[0]).intValue();
        if ((i == Process.myUid()) || (i == b.e()))
        {
          paramObject = new ArrayList();
          paramMethod = n.a().d();
          j = paramMethod.length;
          i = 0;
          while (i < j)
          {
            paramObject.add(paramMethod[i]);
            i += 1;
          }
          i = Binder.getCallingPid();
          if (i != 0)
          {
            paramMethod = g.a().f(i);
            if (paramMethod != null)
            {
              j = paramMethod.length;
              i = 0;
              while (i < j)
              {
                paramObject.remove(paramMethod[i]);
                i += 1;
              }
              j = paramMethod.length;
              i = 0;
              while (i < j)
              {
                paramObject.add(0, paramMethod[i]);
                i += 1;
              }
            }
          }
          bg.c(paramObject);
          a(paramObject.toArray(new String[paramObject.size()]));
          return true;
        }
        if (i == 90000)
        {
          paramObject = new ArrayList();
          paramMethod = n.a().d();
          int k = paramMethod.length;
          i = 0;
          while (i < k)
          {
            paramObject.add(paramMethod[i]);
            i += 1;
          }
          i = Binder.getCallingPid();
          if (i != 0)
          {
            paramMethod = g.a().f(i);
            if (paramMethod != null)
            {
              k = paramMethod.length;
              i = 0;
              while (i < k)
              {
                paramObject.remove(paramMethod[i]);
                i += 1;
              }
              k = paramMethod.length;
              i = 0;
              while (i < k)
              {
                paramObject.add(0, paramMethod[i]);
                i += 1;
              }
            }
          }
          bg.c(paramObject);
          paramMethod = n.b();
          if (paramMethod.e()) {
            for (i = j; i < paramObject.size(); i = j + 1)
            {
              j = i;
              if (paramMethod.a((String)paramObject.get(i)))
              {
                paramObject.remove(i);
                j = i - 1;
              }
            }
          }
          a(paramObject.toArray(new String[paramObject.size()]));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  @TargetApi(18)
  static class ai
    extends ae
  {
    List<PackageInfo> a;
    
    private ai() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if (this.a != null)
      {
        if (paramObject2 != null)
        {
          paramObject2 = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
          if (paramObject2 != null) {
            this.a.addAll(paramObject2);
          }
        }
        paramObject2 = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { this.a });
      }
      for (;;)
      {
        this.a = null;
        return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
      }
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[1] instanceof Integer)))
      {
        String[] arrayOfString = (String[])paramArrayOfObject[0];
        int i = ((Integer)paramArrayOfObject[1]).intValue();
        this.a = n.a().a(arrayOfString, i);
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class aj
    extends ae
  {
    protected aj() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(Integer.valueOf(0));
      return true;
    }
  }
  
  public static class ak
    extends ae
  {
    protected ak() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 == null) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramObject1 = (String)paramArrayOfObject[0];
        return n.a().j(paramObject1);
      }
      return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
    }
  }
  
  public static class al
    extends ae
  {
    protected al() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 == null) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramObject1 = (String)paramArrayOfObject[0];
        return n.a().i(paramObject1);
      }
      return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
    }
  }
  
  static class am
    extends ae
  {
    private am() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if (Build.VERSION.SDK_INT > 23) {
        paramObject1 = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
      }
      for (;;)
      {
        if ((paramObject1 != null) && (paramObject1.size() > 0)) {
          n.a().e(paramObject1);
        }
        paramMethod = paramObject1;
        if (Build.VERSION.SDK_INT > 23) {
          paramMethod = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject1 });
        }
        return paramMethod;
        if ((paramObject2 instanceof List)) {
          paramObject1 = (List)paramObject2;
        } else {
          paramObject1 = new ArrayList();
        }
      }
    }
  }
  
  static class an
    extends ae
  {
    private an() {}
  }
  
  static class ao
    extends ae
  {
    private ao() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 instanceof List))
      {
        paramObject1 = (List)paramObject2;
        n.a().b(paramObject1);
      }
      return paramObject2;
    }
  }
  
  static class ap
    extends bg.p
  {
    private ap()
    {
      super();
    }
    
    protected ComponentInfo a(ComponentName paramComponentName, int paramInt)
    {
      return n.a().d(paramComponentName, paramInt);
    }
  }
  
  static class aq
    extends bg.p
  {
    private aq()
    {
      super();
    }
    
    protected ComponentInfo a(ComponentName paramComponentName, int paramInt)
    {
      return n.a().b(paramComponentName, paramInt);
    }
  }
  
  static class ar
    extends bg.p
  {
    private ar()
    {
      super();
    }
    
    protected ComponentInfo a(ComponentName paramComponentName, int paramInt)
    {
      return n.a().c(paramComponentName, paramInt);
    }
  }
  
  static class as
    extends ae
  {
    private as() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().e(str))
        {
          a(Integer.valueOf(n.b().b(str)));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class at
    extends ae
  {
    private at() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  public static class au
    extends ae
  {
    protected au() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  public static class av
    extends ae
  {
    protected av() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length >= 4) && ((paramArrayOfObject[0] instanceof Uri)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof IPackageInstallObserver))) && ((paramArrayOfObject[3] == null) || ((paramArrayOfObject[3] instanceof String))))
      {
        Uri localUri = (Uri)paramArrayOfObject[0];
        IPackageInstallObserver localIPackageInstallObserver = (IPackageInstallObserver)paramArrayOfObject[1];
        String str = (String)paramArrayOfObject[3];
        if (localUri.getPath() != null)
        {
          paramObject = new PackageInstallInfo();
          paramObject.b = localUri.getPath();
          paramObject.c = localIPackageInstallObserver;
          paramObject.e = str;
          n.a().a(paramObject);
          a(null);
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class aw
    extends ae
  {
    protected aw() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length >= 4) && ((paramArrayOfObject[0] instanceof String)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof IPackageInstallObserver2))) && ((paramArrayOfObject[3] == null) || ((paramArrayOfObject[3] instanceof String))))
      {
        String str1 = (String)paramArrayOfObject[0];
        IPackageInstallObserver2 localIPackageInstallObserver2 = (IPackageInstallObserver2)paramArrayOfObject[2];
        String str2 = (String)paramArrayOfObject[3];
        if (!TextUtils.isEmpty(str1))
        {
          paramObject = new PackageInstallInfo();
          paramObject.b = str1;
          paramObject.d = localIPackageInstallObserver2;
          paramObject.e = str2;
          n.a().a(paramObject);
          a(null);
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class ax
    extends ae
  {
    protected ax() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().f(str))
        {
          a(Boolean.valueOf(true));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class ay
    extends ae
  {
    protected ay() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().f(str))
        {
          a(Boolean.valueOf(false));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class az
    extends ae
  {
    private az() {}
  }
  
  static class b
    extends ae
  {
    private b() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(Boolean.valueOf(false));
      return true;
    }
  }
  
  static class ba
    extends ae
  {
    private ba() {}
  }
  
  static class bb
    extends ae
  {
    private String a;
    private int b;
    
    private bb() {}
    
    private void a(List<ProviderInfo> paramList)
    {
      n.a().d(paramList);
      if (this.a != null)
      {
        int j;
        for (int i = 0; i < paramList.size(); i = j + 1)
        {
          ProviderInfo localProviderInfo = (ProviderInfo)paramList.get(i);
          if (this.a.equals(localProviderInfo.processName))
          {
            j = i;
            if (!bx.a(localProviderInfo.packageName)) {}
          }
          else
          {
            paramList.remove(i);
            j = i - 1;
          }
        }
      }
      b(paramList);
    }
    
    private void b(Object paramObject)
    {
      if (paramObject != null) {
        a((List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject, new Object[0]));
      }
    }
    
    private void b(List<ProviderInfo> paramList)
    {
      List localList = n.a().d(this.a, this.b);
      if (localList != null)
      {
        HashSet localHashSet = new HashSet();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
          localHashSet.add(((ProviderInfo)localIterator.next()).packageName);
        }
        if (localHashSet.size() > 0)
        {
          localIterator = paramList.iterator();
          while (localIterator.hasNext()) {
            if (localHashSet.contains(((ProviderInfo)localIterator.next()).packageName)) {
              localIterator.remove();
            }
          }
          paramList.addAll(localList);
        }
      }
    }
    
    private void c(Object paramObject)
    {
      if ((paramObject instanceof List)) {
        a((List)paramObject);
      }
    }
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((Build.VERSION.SDK_INT >= 23) || ((ParceledListSliceJellyBeanMR2.Class != null) && (paramObject2 != null) && (ParceledListSliceJellyBeanMR2.Class.isInstance(paramObject2))))
      {
        b(paramObject2);
        return paramObject2;
      }
      c(paramObject2);
      return paramObject2;
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      this.a = null;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof String)) && ((paramArrayOfObject[1] instanceof Integer)) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        this.a = ((String)paramArrayOfObject[0]);
        paramArrayOfObject[0] = null;
        paramArrayOfObject[1] = Integer.valueOf(0);
        this.b = ((Integer)paramArrayOfObject[2]).intValue();
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class bc
    extends ae
  {
    private bc() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().f(str))
        {
          if (Build.VERSION.SDK_INT > 23) {
            a(ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { new ArrayList() }));
          }
          for (;;)
          {
            return true;
            a(new ArrayList());
          }
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class bd
    extends ae
  {
    private boolean a = false;
    
    private bd() {}
    
    private boolean a(ResolveInfo paramResolveInfo)
    {
      HashSet localHashSet = new HashSet();
      localHashSet.add("com.google.android.apps.maps");
      return localHashSet.contains(paramResolveInfo.activityInfo.packageName);
    }
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      int i;
      label40:
      int j;
      if (Build.VERSION.SDK_INT > 23)
      {
        paramObject1 = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
        if ((paramObject1 == null) || (paramObject1.size() <= 0)) {
          break label228;
        }
        i = 0;
        if (i >= paramObject1.size()) {
          break label139;
        }
        paramMethod = (ResolveInfo)paramObject1.get(i);
        if ((paramMethod.activityInfo == null) || (!bx.a(paramMethod.activityInfo.packageName))) {
          break label467;
        }
        j = i - 1;
        paramObject1.remove(i);
        i = j;
      }
      label139:
      label226:
      label228:
      label360:
      label422:
      label437:
      label464:
      label467:
      for (;;)
      {
        i += 1;
        break label40;
        if ((paramObject2 instanceof List))
        {
          paramObject1 = (List)paramObject2;
          break;
        }
        paramObject1 = new ArrayList();
        break;
        n.a().a(paramObject1);
        paramMethod = paramObject1.iterator();
        while (paramMethod.hasNext())
        {
          paramContext = (ResolveInfo)paramMethod.next();
          if ((paramContext.activityInfo.enabled) && (paramContext.activityInfo.applicationInfo.enabled)) {}
          for (i = 1;; i = 0)
          {
            if ((this.a) || (i != 0)) {
              break label226;
            }
            paramMethod.remove();
            break;
          }
        }
        paramMethod = paramObject2;
        if (paramArrayOfObject != null)
        {
          paramMethod = paramObject2;
          if (paramArrayOfObject.length > 2)
          {
            paramMethod = paramObject2;
            if ((paramArrayOfObject[0] instanceof Intent)) {
              if (paramArrayOfObject[1] != null)
              {
                paramMethod = paramObject2;
                if (!(paramArrayOfObject[1] instanceof String)) {}
              }
              else
              {
                paramMethod = paramObject2;
                if ((paramArrayOfObject[2] instanceof Integer))
                {
                  paramMethod = (Intent)paramArrayOfObject[0];
                  paramObject2 = (String)paramArrayOfObject[1];
                  i = ((Integer)paramArrayOfObject[2]).intValue();
                  if (this.a)
                  {
                    j = i | 0x200;
                    a(paramObject1, paramMethod, paramObject2, j);
                    if ((paramObject1 == null) || (paramObject1.size() <= 0)) {
                      break label437;
                    }
                    paramArrayOfObject = paramObject1.iterator();
                    i = 1;
                    if (!paramArrayOfObject.hasNext()) {
                      break label422;
                    }
                    paramContext = (ResolveInfo)paramArrayOfObject.next();
                    if ((paramContext.activityInfo == null) || (!TextUtils.equals(paramContext.activityInfo.packageName, b.h()))) {
                      break label464;
                    }
                    i = 0;
                  }
                }
              }
            }
          }
        }
        for (;;)
        {
          break label360;
          j = i & 0xFDFF;
          break;
          if (i != 0) {
            b(paramObject1, paramMethod, paramObject2, j);
          }
          if (Build.VERSION.SDK_INT > 23)
          {
            paramMethod = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject1 });
            return paramMethod;
          }
          return paramObject1;
        }
      }
    }
    
    protected void a(List<ResolveInfo> paramList, Intent paramIntent, String paramString, int paramInt)
    {
      paramIntent = n.a().a(paramIntent, paramString, paramInt);
      if ((paramIntent != null) && (paramIntent.size() > 0))
      {
        paramString = new HashSet();
        Iterator localIterator = paramIntent.iterator();
        while (localIterator.hasNext()) {
          paramString.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
        }
        localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (paramString.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      this.a = false;
      int i;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof Intent)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof String))) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        Intent localIntent = (Intent)paramArrayOfObject[0];
        String str = (String)paramArrayOfObject[1];
        i = ((Integer)paramArrayOfObject[2]).intValue();
        if (str != null) {
          localIntent.setDataAndType(localIntent.getData(), str);
        }
        if ((i & 0x200) == 0) {
          break label133;
        }
      }
      label133:
      for (boolean bool = true;; bool = false)
      {
        this.a = bool;
        paramArrayOfObject[2] = Integer.valueOf(i | 0x200);
        return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      }
    }
    
    protected void b(List<ResolveInfo> paramList, Intent paramIntent, String paramString, int paramInt)
    {
      if (((cy.a(g.a().c().getContentResolver(), paramIntent)) || (paramIntent.hasCategory("android.intent.category.BROWSABLE")) || (paramIntent.hasCategory("android.intent.category.APP_BROWSER"))) && (paramList.size() > 0) && (!a((ResolveInfo)paramList.get(0))))
      {
        paramIntent = g.a().k();
        if (paramIntent != null)
        {
          paramString = new HashSet();
          paramString.add(paramIntent.activityInfo.packageName);
          Iterator localIterator = paramList.iterator();
          while (localIterator.hasNext()) {
            if (paramString.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
              localIterator.remove();
            }
          }
          paramString = new ArrayList();
          paramString.add(paramIntent);
          paramString.addAll(paramList);
          paramList.clear();
          paramList.addAll(paramString);
        }
      }
    }
  }
  
  static class be
    extends ae
  {
    private boolean a = false;
    
    private be() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      Object localObject = null;
      if (Build.VERSION.SDK_INT > 23)
      {
        localObject = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
        if (localObject != null)
        {
          n.a().a((Collection)localObject);
          localObject = ((List)localObject).iterator();
        }
      }
      else
      {
        label50:
        label144:
        for (;;)
        {
          if (!((Iterator)localObject).hasNext()) {
            break label146;
          }
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          if ((localResolveInfo.activityInfo.enabled) && (localResolveInfo.activityInfo.applicationInfo.enabled)) {}
          for (int i = 1;; i = 0)
          {
            if ((this.a) || (i != 0)) {
              break label144;
            }
            ((Iterator)localObject).remove();
            break label50;
            if (!(paramObject2 instanceof List)) {
              break;
            }
            localObject = (List)paramObject2;
            break;
          }
        }
      }
      label146:
      return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      this.a = false;
      int i;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 5) && ((paramArrayOfObject[0] == null) || ((paramArrayOfObject[0] instanceof ComponentName))) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof Intent[]))) && ((paramArrayOfObject[2] == null) || ((paramArrayOfObject[2] instanceof String[]))) && ((paramArrayOfObject[3] instanceof Intent)) && ((paramArrayOfObject[4] == null) || ((paramArrayOfObject[4] instanceof String))) && ((paramArrayOfObject[5] instanceof Integer)))
      {
        Intent localIntent = (Intent)paramArrayOfObject[3];
        String str = (String)paramArrayOfObject[4];
        if (str != null) {
          localIntent.setDataAndType(localIntent.getData(), str);
        }
        i = ((Integer)paramArrayOfObject[5]).intValue();
        if ((i & 0x200) == 0) {
          break label178;
        }
      }
      label178:
      for (boolean bool = true;; bool = false)
      {
        this.a = bool;
        paramArrayOfObject[5] = Integer.valueOf(i | 0x200);
        return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      }
    }
  }
  
  @TargetApi(19)
  static class bf
    extends ae
  {
    private boolean a = false;
    
    private bf() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      int i;
      if (Build.VERSION.SDK_INT > 23)
      {
        paramObject1 = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
        if ((paramObject1 == null) || (paramObject1.size() <= 0)) {
          break label251;
        }
        i = 0;
        label40:
        if (i >= paramObject1.size()) {
          break label139;
        }
        paramMethod = (ResolveInfo)paramObject1.get(i);
        if ((paramMethod.providerInfo == null) || (!bx.a(paramMethod.providerInfo.packageName))) {
          break label398;
        }
        int j = i - 1;
        paramObject1.remove(i);
        i = j;
      }
      label139:
      label228:
      label251:
      label385:
      label398:
      for (;;)
      {
        i += 1;
        break label40;
        if ((paramObject2 instanceof List))
        {
          paramObject1 = (List)paramObject2;
          break;
        }
        paramObject1 = new ArrayList();
        break;
        n.a().a(paramObject1);
        paramMethod = paramObject1.iterator();
        while (paramMethod.hasNext())
        {
          paramContext = (ResolveInfo)paramMethod.next();
          if ((paramContext.providerInfo.enabled) && (paramContext.providerInfo.applicationInfo.enabled)) {}
          for (i = 1;; i = 0)
          {
            if ((this.a) || (i != 0)) {
              break label228;
            }
            paramMethod.remove();
            break;
          }
          if (bx.b(paramContext.providerInfo.authority)) {
            paramMethod.remove();
          }
        }
        paramMethod = paramObject2;
        if (paramArrayOfObject != null)
        {
          paramMethod = paramObject2;
          if (paramArrayOfObject.length > 2)
          {
            paramMethod = paramObject2;
            if ((paramArrayOfObject[0] instanceof Intent)) {
              if (paramArrayOfObject[1] != null)
              {
                paramMethod = paramObject2;
                if (!(paramArrayOfObject[1] instanceof String)) {}
              }
              else
              {
                paramMethod = paramObject2;
                if ((paramArrayOfObject[2] instanceof Integer))
                {
                  paramMethod = (Intent)paramArrayOfObject[0];
                  paramObject2 = (String)paramArrayOfObject[1];
                  i = ((Integer)paramArrayOfObject[2]).intValue();
                  if (!this.a) {
                    break label385;
                  }
                  i |= 0x200;
                }
              }
            }
          }
        }
        for (;;)
        {
          a(paramObject1, paramMethod, paramObject2, i);
          if (Build.VERSION.SDK_INT <= 23) {
            break;
          }
          paramMethod = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject1 });
          return paramMethod;
          i &= 0xFDFF;
        }
        return paramObject1;
      }
    }
    
    protected void a(List<ResolveInfo> paramList, Intent paramIntent, String paramString, int paramInt)
    {
      paramIntent = n.a().d(paramIntent, paramString, paramInt);
      if ((paramIntent != null) && (paramIntent.size() > 0))
      {
        paramString = new HashSet();
        Iterator localIterator = paramIntent.iterator();
        while (localIterator.hasNext()) {
          paramString.add(((ResolveInfo)localIterator.next()).providerInfo.packageName);
        }
        localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (paramString.contains(((ResolveInfo)localIterator.next()).providerInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      this.a = false;
      int i;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof Intent)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof String))) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        Intent localIntent = (Intent)paramArrayOfObject[0];
        String str = (String)paramArrayOfObject[1];
        i = ((Integer)paramArrayOfObject[2]).intValue();
        if (str != null) {
          localIntent.setDataAndType(localIntent.getData(), str);
        }
        if ((i & 0x200) == 0) {
          break label133;
        }
      }
      label133:
      for (boolean bool = true;; bool = false)
      {
        this.a = bool;
        paramArrayOfObject[2] = Integer.valueOf(i | 0x200);
        return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      }
    }
  }
  
  static class bg
    extends bg.bd
  {
    private bg()
    {
      super();
    }
    
    protected void a(List<ResolveInfo> paramList, Intent paramIntent, String paramString, int paramInt)
    {
      paramIntent = n.a().b(paramIntent, paramString, paramInt);
      if ((paramIntent != null) && (paramIntent.size() > 0))
      {
        paramString = new HashSet();
        Iterator localIterator = paramIntent.iterator();
        while (localIterator.hasNext()) {
          paramString.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
        }
        localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (paramString.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
  }
  
  static class bh
    extends ae
  {
    private boolean a = false;
    
    private bh() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if (Build.VERSION.SDK_INT > 23) {
        paramObject1 = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
      }
      int i;
      while ((paramObject1 != null) && (paramObject1.size() > 0))
      {
        i = 0;
        for (;;)
        {
          if (i < paramObject1.size())
          {
            paramMethod = ((ResolveInfo)paramObject1.get(i)).serviceInfo;
            int j = i;
            if (paramMethod != null)
            {
              j = i;
              if (bx.a(paramMethod.packageName))
              {
                paramObject1.remove(i);
                j = i - 1;
              }
            }
            i = j + 1;
            continue;
            if ((paramObject2 instanceof List))
            {
              paramObject1 = (List)paramObject2;
              break;
            }
            paramObject1 = new ArrayList();
            break;
          }
        }
        n.a().a(paramObject1);
        paramMethod = paramObject1.iterator();
        label227:
        while (paramMethod.hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)paramMethod.next();
          if ((localResolveInfo.serviceInfo.enabled) && (localResolveInfo.serviceInfo.applicationInfo.enabled)) {}
          for (i = 1;; i = 0)
          {
            if ((this.a) || (i != 0)) {
              break label227;
            }
            paramMethod.remove();
            break;
          }
        }
      }
      paramMethod = paramObject2;
      if (paramArrayOfObject != null)
      {
        paramMethod = paramObject2;
        if (paramArrayOfObject.length > 2)
        {
          paramMethod = paramObject2;
          if ((paramArrayOfObject[0] instanceof Intent)) {
            if (paramArrayOfObject[1] != null)
            {
              paramMethod = paramObject2;
              if (!(paramArrayOfObject[1] instanceof String)) {}
            }
            else
            {
              paramMethod = paramObject2;
              if ((paramArrayOfObject[2] instanceof Integer))
              {
                paramMethod = (Intent)paramArrayOfObject[0];
                paramObject2 = (String)paramArrayOfObject[1];
                i = ((Integer)paramArrayOfObject[2]).intValue();
                if (!this.a) {
                  break label371;
                }
                i |= 0x200;
              }
            }
          }
        }
      }
      for (;;)
      {
        a(paramObject1, paramMethod, paramObject2, i);
        a(paramContext, paramObject1, paramMethod);
        if (Build.VERSION.SDK_INT <= 23) {
          break;
        }
        paramMethod = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject1 });
        return paramMethod;
        label371:
        i &= 0xFDFF;
      }
      return paramObject1;
    }
    
    protected void a(Context paramContext, List<ResolveInfo> paramList, Intent paramIntent)
    {
      if ((paramList != null) && (paramIntent != null))
      {
        paramIntent = paramIntent.getComponent();
        if ((paramIntent == null) || (!n.a().n(paramIntent.getPackageName())) || (!TextUtils.equals("com.phantom.PhantomService", paramIntent.getClassName()))) {}
      }
      try
      {
        ResolveInfo localResolveInfo = new ResolveInfo();
        localResolveInfo.serviceInfo = new ServiceInfo();
        localResolveInfo.serviceInfo.packageName = paramIntent.getPackageName();
        localResolveInfo.serviceInfo.name = paramIntent.getClassName();
        localResolveInfo.serviceInfo.processName = paramIntent.getPackageName();
        localResolveInfo.serviceInfo.exported = true;
        localResolveInfo.serviceInfo.enabled = true;
        localResolveInfo.serviceInfo.applicationInfo = paramContext.getPackageManager().getApplicationInfo(paramIntent.getPackageName(), 0);
        paramList.add(localResolveInfo);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    
    protected void a(List<ResolveInfo> paramList, Intent paramIntent, String paramString, int paramInt)
    {
      paramIntent = n.a().c(paramIntent, paramString, paramInt);
      if ((paramIntent != null) && (paramIntent.size() > 0))
      {
        paramString = new HashSet();
        Iterator localIterator = paramIntent.iterator();
        while (localIterator.hasNext()) {
          paramString.add(((ResolveInfo)localIterator.next()).serviceInfo.packageName);
        }
        localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (paramString.contains(((ResolveInfo)localIterator.next()).serviceInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      this.a = false;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof Intent)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof String))) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        Intent localIntent = (Intent)paramArrayOfObject[0];
        String str = (String)paramArrayOfObject[1];
        int i = ((Integer)paramArrayOfObject[2]).intValue();
        if (str != null) {
          localIntent.setDataAndType(localIntent.getData(), str);
        }
        this.a = true;
        paramArrayOfObject[2] = Integer.valueOf(i | 0x200);
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class bi
    extends ae
  {
    protected bi() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      List localList;
      if (Build.VERSION.SDK_INT > 23)
      {
        localList = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
        if ((paramArrayOfObject == null) || (paramArrayOfObject.length <= 0) || (!(paramArrayOfObject[0] instanceof String))) {
          break label237;
        }
        paramObject1 = (String)paramArrayOfObject[0];
        paramObject1 = n.a().k(paramObject1);
        if (localList != null) {
          break label100;
        }
      }
      for (;;)
      {
        paramMethod = paramObject1;
        if (Build.VERSION.SDK_INT > 23) {
          paramMethod = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject1 });
        }
        return paramMethod;
        localList = (List)paramObject2;
        break;
        label100:
        if ((paramObject1 != null) && (paramObject1.size() > 0))
        {
          paramMethod = new HashMap();
          paramArrayOfObject = localList.iterator();
          while (paramArrayOfObject.hasNext())
          {
            paramObject2 = (PermissionInfo)paramArrayOfObject.next();
            paramMethod.put(paramObject2.name, paramObject2);
          }
          paramObject1 = paramObject1.iterator();
          while (paramObject1.hasNext())
          {
            paramArrayOfObject = (PermissionInfo)paramObject1.next();
            if (!paramMethod.containsKey(paramArrayOfObject.name)) {
              paramMethod.put(paramArrayOfObject.name, paramArrayOfObject);
            }
          }
          paramObject1 = new ArrayList(paramMethod.values());
          continue;
          label237:
          return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
        }
        else
        {
          paramObject1 = localList;
        }
      }
    }
  }
  
  static class bj
    extends ae
  {
    private bj() {}
  }
  
  static class bk
    extends bg.a
  {
    private bk()
    {
      super();
    }
  }
  
  static class bl
    extends ae
  {
    private bl() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(Boolean.valueOf(false));
      return true;
    }
  }
  
  static class bm
    extends bg.a
  {
    private bm()
    {
      super();
    }
  }
  
  static class bn
    extends ae
  {
    private bn() {}
  }
  
  public static class bo
    extends ae
  {
    protected bo() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  static class bp
    extends ae
  {
    private bp() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 instanceof ProviderInfo)) {
        n.a().a(new ComponentInfo[] { (ProviderInfo)paramObject2 });
      }
      return paramObject2;
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length >= 2) && ((paramArrayOfObject[0] instanceof String)) && ((paramArrayOfObject[1] instanceof Integer)))
      {
        Object localObject = (String)paramArrayOfObject[0];
        if ((n.b().f()) && (TextUtils.equals("com.facebook.katana.provider.AttributionIdProvider", (CharSequence)localObject))) {}
        for (int i = 1; i != 0; i = 0)
        {
          a(null);
          return true;
        }
        i = ((Integer)paramArrayOfObject[1]).intValue();
        if (bx.b((String)localObject))
        {
          a(null);
          return true;
        }
        localObject = n.a().c((String)localObject, i);
        if ((localObject != null) && (((ProviderInfo)localObject).packageName != null) && (bx.a(((ProviderInfo)localObject).packageName)))
        {
          if (((ProviderInfo)localObject).authority != null) {
            paramObject = ((ProviderInfo)localObject).authority;
          }
          a(null);
          return true;
        }
        if (localObject != null)
        {
          a(localObject);
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class bq
    extends ae
  {
    private bq() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof Intent)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof String))) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        paramObject = (Intent)paramArrayOfObject[0];
        paramMethod = (String)paramArrayOfObject[1];
        int i = ((Integer)paramArrayOfObject[2]).intValue();
        if (paramMethod != null) {
          paramObject.setDataAndType(paramObject.getData(), paramMethod);
        }
        paramObject = paramContext.getPackageManager().queryIntentActivities(paramObject, i);
        if ((paramObject == null) || (paramObject.size() <= 0)) {
          break label135;
        }
      }
      label135:
      for (paramObject = (ResolveInfo)paramObject.get(0);; paramObject = null)
      {
        a(paramObject);
        return true;
        return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      }
    }
  }
  
  static class br
    extends ae
  {
    private br() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof Intent)) && ((paramArrayOfObject[1] == null) || ((paramArrayOfObject[1] instanceof String))) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        paramObject = (Intent)paramArrayOfObject[0];
        paramMethod = (String)paramArrayOfObject[1];
        int i = ((Integer)paramArrayOfObject[2]).intValue();
        if (paramMethod != null) {
          paramObject.setDataAndType(paramObject.getData(), paramMethod);
        }
        paramObject = paramContext.getPackageManager().queryIntentServices(paramObject, i);
        if ((paramObject == null) || (paramObject.size() <= 0)) {
          break label135;
        }
      }
      label135:
      for (paramObject = (ResolveInfo)paramObject.get(0);; paramObject = null)
      {
        a(paramObject);
        return true;
        return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      }
    }
  }
  
  static class bs
    extends bg.at
  {
    private bs()
    {
      super();
    }
  }
  
  public static class bt
    extends ae
  {
    protected bt() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  static class bu
    extends ae
  {
    private bu() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  static class bv
    extends ae
  {
    private bv() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof ComponentName)) && ((paramArrayOfObject[1] instanceof Integer)) && ((paramArrayOfObject[2] instanceof Integer)))
      {
        paramObject = (ComponentName)paramArrayOfObject[0];
        int i = ((Integer)paramArrayOfObject[1]).intValue();
        int j = ((Integer)paramArrayOfObject[2]).intValue();
        paramMethod = n.a();
        if (paramMethod.f(paramObject.getPackageName())) {
          paramMethod.a(paramObject, i, j);
        }
      }
      a(null);
      return true;
    }
  }
  
  static class bw
    extends ae
  {
    private bw() {}
  }
  
  static class bx
    extends ae
  {
    private bx() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  public static class by
    extends ae
  {
    protected by() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[1] instanceof String))) {
        paramArrayOfObject[1] = paramContext.getPackageName();
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class bz
    extends ae
  {
    protected bz() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  static class c
    extends bg.a
  {
    private c()
    {
      super();
    }
  }
  
  public static class ca
    extends ae
  {
    protected ca() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      a(null);
      return true;
    }
  }
  
  public static class d
    extends ae
  {
    protected d() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().f(str))
        {
          a(null);
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class e
    extends ae
  {
    protected e() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 3) && ((paramArrayOfObject[2] instanceof Integer)) && ((paramArrayOfObject[3] instanceof Integer)))
      {
        if (paramArrayOfObject[2] == paramArrayOfObject[3])
        {
          a(Boolean.valueOf(true));
          return true;
        }
        a(Boolean.valueOf(false));
        return true;
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class f
    extends ae
  {
    private f() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[0] instanceof String)) && ((paramArrayOfObject[1] instanceof String)))
      {
        String str1 = (String)paramArrayOfObject[0];
        String str2 = (String)paramArrayOfObject[1];
        if (n.a().f(str2))
        {
          a(Integer.valueOf(n.a().a(str1, str2)));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class g
    extends ae
  {
    private g() {}
    
    private static int a(Signature[] paramArrayOfSignature1, Signature[] paramArrayOfSignature2)
    {
      if (paramArrayOfSignature1 == null)
      {
        if (paramArrayOfSignature2 == null) {
          return 1;
        }
        return -1;
      }
      if (paramArrayOfSignature2 == null) {
        return -2;
      }
      if (paramArrayOfSignature1.length != paramArrayOfSignature2.length) {
        return -3;
      }
      if (paramArrayOfSignature1.length == 1)
      {
        if (paramArrayOfSignature1[0].equals(paramArrayOfSignature2[0])) {
          return 0;
        }
        return -3;
      }
      HashSet localHashSet = new HashSet();
      int j = paramArrayOfSignature1.length;
      int i = 0;
      while (i < j)
      {
        localHashSet.add(paramArrayOfSignature1[i]);
        i += 1;
      }
      paramArrayOfSignature1 = new HashSet();
      j = paramArrayOfSignature2.length;
      i = 0;
      while (i < j)
      {
        paramArrayOfSignature1.add(paramArrayOfSignature2[i]);
        i += 1;
      }
      if (localHashSet.equals(paramArrayOfSignature1)) {
        return 0;
      }
      return -3;
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      Object localObject;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[0] instanceof String)) && ((paramArrayOfObject[1] instanceof String)))
      {
        localObject = n.a();
        if ((((n)localObject).g((String)paramArrayOfObject[0])) || (((n)localObject).g((String)paramArrayOfObject[1])))
        {
          paramMethod = ((n)localObject).a((String)paramArrayOfObject[0], 64);
          paramObject = paramMethod;
          if (paramMethod != null) {}
        }
      }
      try
      {
        paramObject = paramContext.getPackageManager().getPackageInfo((String)paramArrayOfObject[0], 64);
        localObject = ((n)localObject).a((String)paramArrayOfObject[1], 64);
        paramMethod = (Method)localObject;
        if (localObject == null) {}
        try
        {
          paramMethod = paramContext.getPackageManager().getPackageInfo((String)paramArrayOfObject[1], 64);
          if (paramObject != null)
          {
            paramObject = paramObject.signatures;
            if (paramMethod == null) {
              break label175;
            }
          }
          label175:
          for (paramMethod = paramMethod.signatures;; paramMethod = null)
          {
            a(Integer.valueOf(a(paramObject, paramMethod)));
            return true;
            paramObject = null;
            break;
          }
          return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
        }
        catch (Exception paramMethod)
        {
          for (;;)
          {
            paramMethod = (Method)localObject;
          }
        }
      }
      catch (Exception paramObject)
      {
        for (;;)
        {
          paramObject = paramMethod;
        }
      }
    }
  }
  
  static class h
    extends ae
  {
    private h() {}
  }
  
  static class i
    extends ae
  {
    private i() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramObject = (String)paramArrayOfObject[0];
        if (paramArrayOfObject[1] != null) {
          IPackageDataObserver.onRemoveCompleted.invoke(paramArrayOfObject[1], new Object[] { paramObject, Boolean.valueOf(true) });
        }
      }
      a(null);
      return true;
    }
  }
  
  static class j
    extends bg.a
  {
    private j()
    {
      super();
    }
  }
  
  static class k
    extends ae
  {
    private k() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramObject = (String)paramArrayOfObject[0];
        if (n.a().f(paramObject)) {
          n.a().c(paramObject);
        }
        if (paramArrayOfObject[1] != null) {
          IPackageDataObserver.onRemoveCompleted.invoke(paramArrayOfObject[1], new Object[] { paramObject, Boolean.valueOf(true) });
        }
      }
      a(null);
      return true;
    }
  }
  
  public static class l
    extends ae
  {
    protected l() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramMethod = (String)paramArrayOfObject[0];
        if (!(paramArrayOfObject[1] instanceof IPackageDeleteObserver)) {
          break label58;
        }
      }
      label58:
      for (paramObject = (IPackageDeleteObserver)paramArrayOfObject[1];; paramObject = null)
      {
        n.a().a(paramMethod, paramObject, null);
        a(null);
        return true;
      }
    }
  }
  
  public static class m
    extends ae
  {
    protected m() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 2) && ((paramArrayOfObject[0] instanceof String)))
      {
        paramMethod = (String)paramArrayOfObject[0];
        if (!(paramArrayOfObject[1] instanceof IPackageDeleteObserver2)) {
          break label58;
        }
      }
      label58:
      for (paramObject = (IPackageDeleteObserver2)paramArrayOfObject[1];; paramObject = null)
      {
        n.a().a(paramMethod, null, paramObject);
        a(null);
        return true;
      }
    }
  }
  
  public static class n
    extends ae
  {
    protected n() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      int i = ac.a(paramArrayOfObject, IntentSender.class, 0);
      if (i >= 0) {
        paramObject = (IntentSender)paramArrayOfObject[i];
      }
      try
      {
        paramObject.sendIntent(paramContext, 0, null, null, null);
        a(null);
        return true;
      }
      catch (Exception paramObject)
      {
        for (;;)
        {
          paramObject.printStackTrace();
        }
      }
    }
  }
  
  public static class o
    extends ae
  {
    protected o() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
      int i = ac.a(paramArrayOfObject, IPackageDataObserver.Class, 0);
      if (i >= 0) {
        IPackageDataObserver.onRemoveCompleted.invoke(paramArrayOfObject[i], new Object[] { paramContext.getPackageName(), Boolean.valueOf(true) });
      }
      a(null);
      return true;
    }
  }
  
  static class p
    extends ae
  {
    private boolean a;
    
    private p() {}
    
    protected ComponentInfo a(ComponentName paramComponentName, int paramInt)
    {
      return n.a().a(paramComponentName, paramInt);
    }
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      paramObject1 = paramObject2;
      if ((paramObject2 instanceof ComponentInfo))
      {
        paramObject1 = (ComponentInfo)paramObject2;
        n.a().a(new ComponentInfo[] { paramObject1 });
        if ((!paramObject1.enabled) || (!paramObject1.applicationInfo.enabled)) {
          break label73;
        }
      }
      label73:
      for (int i = 1;; i = 0)
      {
        paramObject1 = paramObject2;
        if (!this.a)
        {
          paramObject1 = paramObject2;
          if (i == 0) {
            paramObject1 = null;
          }
        }
        return paramObject1;
      }
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      this.a = false;
      int i;
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 1) && ((paramArrayOfObject[0] instanceof ComponentName)) && ((paramArrayOfObject[1] instanceof Integer)))
      {
        Object localObject = (ComponentName)paramArrayOfObject[0];
        i = ((Integer)paramArrayOfObject[1]).intValue();
        localObject = a((ComponentName)localObject, i);
        if (localObject != null)
        {
          a(localObject);
          return true;
        }
        if ((i & 0x200) == 0) {
          break label99;
        }
      }
      for (this.a = true;; this.a = false)
      {
        return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
        label99:
        paramArrayOfObject[1] = Integer.valueOf(i | 0x200);
      }
    }
  }
  
  public static class q
    extends ae
  {
    protected q() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      List localList;
      if (Build.VERSION.SDK_INT > 23)
      {
        localList = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
        if ((paramArrayOfObject == null) || (paramArrayOfObject.length <= 0) || (!(paramArrayOfObject[0] instanceof String))) {
          break label237;
        }
        paramMethod = n.a().e();
        if (localList != null) {
          break label94;
        }
        paramObject1 = paramMethod;
      }
      for (;;)
      {
        paramMethod = paramObject1;
        if (Build.VERSION.SDK_INT > 23) {
          paramMethod = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject1 });
        }
        return paramMethod;
        localList = (List)paramObject2;
        break;
        label94:
        paramObject1 = localList;
        if (paramMethod != null)
        {
          paramObject1 = localList;
          if (paramMethod.size() > 0)
          {
            paramObject1 = new HashMap();
            paramArrayOfObject = localList.iterator();
            while (paramArrayOfObject.hasNext())
            {
              paramObject2 = (PermissionGroupInfo)paramArrayOfObject.next();
              paramObject1.put(paramObject2.name, paramObject2);
            }
            paramMethod = paramMethod.iterator();
            while (paramMethod.hasNext())
            {
              paramArrayOfObject = (PermissionGroupInfo)paramMethod.next();
              if (!paramObject1.containsKey(paramArrayOfObject.name)) {
                paramObject1.put(paramArrayOfObject.name, paramArrayOfObject);
              }
            }
            paramObject1 = new ArrayList(paramObject1.values());
          }
        }
      }
      label237:
      return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
    }
  }
  
  static class r
    extends ae
  {
    private r() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        String str = (String)paramArrayOfObject[0];
        if (n.a().f(str))
        {
          a(Integer.valueOf(1));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  public static class s
    extends ae
  {
    protected s() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 instanceof ApplicationInfo)) {
        n.a().a(new ApplicationInfo[] { (ApplicationInfo)paramObject2 });
      }
      return paramObject2;
    }
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        Object localObject = (String)paramArrayOfObject[0];
        if (bx.a((String)localObject))
        {
          a(null);
          return true;
        }
        if ((paramArrayOfObject.length > 1) && ((paramArrayOfObject[1] instanceof Integer)))
        {
          localObject = n.a().b((String)localObject, ((Integer)paramArrayOfObject[1]).intValue());
          if (localObject != null)
          {
            a(localObject);
            return true;
          }
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class t
    extends ae
  {
    private t() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof ComponentName)))
      {
        ComponentName localComponentName = (ComponentName)paramArrayOfObject[0];
        n localN = n.a();
        if (localN.f(localComponentName.getPackageName()))
        {
          a(Integer.valueOf(localN.a(localComponentName)));
          return true;
        }
        if (bx.a(localComponentName.getPackageName()))
        {
          a(Integer.valueOf(2));
          return true;
        }
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class u
    extends ae
  {
    private u() {}
  }
  
  static class v
    extends ae
  {
    private v() {}
  }
  
  static class w
    extends ae
  {
    private w() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof Integer))) {}
      for (int i = ((Integer)paramArrayOfObject[0]).intValue();; i = 0)
      {
        Object localObject;
        if (paramObject2 != null) {
          if (ParceledListSliceJellyBeanMR2.Class != null)
          {
            localObject = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
            n.a().e((Collection)localObject);
            bg.b((List)localObject);
            paramObject2 = localObject;
            if (localObject == null) {
              paramObject2 = new ArrayList();
            }
            a(paramObject2, i);
            paramObject2 = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject2 });
          }
        }
        for (;;)
        {
          return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
          if (ParceledListSlice.Class != null)
          {
            localObject = new ArrayList();
            ParceledListSlice.populateList.invoke(paramObject2, new Object[] { localObject, ApplicationInfo.CREATOR });
            boolean bool = ((Boolean)ParceledListSlice.isLastSlice.invoke(paramObject2, new Object[0])).booleanValue();
            n.a().e((Collection)localObject);
            bg.b((List)localObject);
            a((List)localObject, i);
            paramObject2 = ParceledListSlice.ctor.newInstance();
            i = 0;
            while (i < ((List)localObject).size())
            {
              ParceledListSlice.append.invoke(paramObject2, new Object[] { ((List)localObject).get(i) });
              i += 1;
            }
            ParceledListSlice.setLastSlice.invoke(paramObject2, new Object[] { Boolean.valueOf(bool) });
          }
          else if ((paramObject2 instanceof List))
          {
            localObject = (List)paramObject2;
            bg.b((List)localObject);
            n.a().e((Collection)localObject);
            a((List)localObject, i);
            continue;
            localObject = new ArrayList();
            a((List)localObject, i);
            if (ParceledListSliceJellyBeanMR2.Class != null)
            {
              paramObject2 = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { localObject });
            }
            else
            {
              paramObject2 = localObject;
              if (ParceledListSlice.Class != null)
              {
                paramObject2 = ParceledListSlice.ctor.newInstance();
                i = 0;
                while (i < ((List)localObject).size())
                {
                  ParceledListSlice.append.invoke(paramObject2, new Object[] { ((List)localObject).get(i) });
                  i += 1;
                }
              }
            }
          }
        }
      }
    }
    
    protected void a(List<ApplicationInfo> paramList, int paramInt)
    {
      List localList = n.a().b(paramInt);
      if ((localList != null) && (localList.size() > 0))
      {
        HashSet localHashSet = new HashSet();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
          localHashSet.add(((ApplicationInfo)localIterator.next()).packageName);
        }
        localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((ApplicationInfo)localIterator.next()).packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(localList);
      }
    }
  }
  
  static class x
    extends ae
  {
    private x() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof Integer))) {}
      for (int i = ((Integer)paramArrayOfObject[0]).intValue();; i = 0)
      {
        Object localObject;
        if (paramObject2 != null) {
          if (ParceledListSliceJellyBeanMR2.Class != null)
          {
            localObject = (List)ParceledListSliceJellyBeanMR2.getList.invoke(paramObject2, new Object[0]);
            bg.a((List)localObject);
            n.a().b((Collection)localObject);
            paramObject2 = localObject;
            if (localObject == null) {
              paramObject2 = new ArrayList();
            }
            a(paramObject2, i);
            paramObject2 = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { paramObject2 });
          }
        }
        for (;;)
        {
          return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
          if (ParceledListSlice.Class != null)
          {
            localObject = new ArrayList();
            ParceledListSlice.populateList.invoke(paramObject2, new Object[] { localObject, PackageInfo.CREATOR });
            boolean bool = ((Boolean)ParceledListSlice.isLastSlice.invoke(paramObject2, new Object[0])).booleanValue();
            n.a().b((Collection)localObject);
            bg.a((List)localObject);
            a((List)localObject, i);
            paramObject2 = ParceledListSlice.ctor.newInstance();
            i = 0;
            while (i < ((List)localObject).size())
            {
              ParceledListSlice.append.invoke(paramObject2, new Object[] { ((List)localObject).get(i) });
              i += 1;
            }
            ParceledListSlice.setLastSlice.invoke(paramObject2, new Object[] { Boolean.valueOf(bool) });
          }
          else if ((paramObject2 instanceof List))
          {
            localObject = (List)paramObject2;
            bg.a((List)localObject);
            n.a().b((Collection)localObject);
            a((List)localObject, i);
            continue;
            localObject = new ArrayList();
            a((List)localObject, i);
            if (ParceledListSliceJellyBeanMR2.Class != null)
            {
              paramObject2 = ParceledListSliceJellyBeanMR2.ctor.newInstance(new Object[] { localObject });
            }
            else
            {
              paramObject2 = localObject;
              if (ParceledListSlice.Class != null)
              {
                paramObject2 = ParceledListSlice.ctor.newInstance();
                i = 0;
                while (i < ((List)localObject).size())
                {
                  ParceledListSlice.append.invoke(paramObject2, new Object[] { ((List)localObject).get(i) });
                  i += 1;
                }
              }
            }
          }
        }
      }
    }
    
    protected void a(List<PackageInfo> paramList, int paramInt)
    {
      List localList = n.a().a(paramInt);
      if ((localList != null) && (localList.size() > 0))
      {
        HashSet localHashSet = new HashSet();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
          localHashSet.add(((PackageInfo)localIterator.next()).packageName);
        }
        localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((PackageInfo)localIterator.next()).packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(localList);
      }
    }
  }
  
  static class y
    extends ae
  {
    private y() {}
    
    protected boolean a(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, Context paramContext)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof String)))
      {
        a(n.a().h((String)paramArrayOfObject[0]));
        return true;
      }
      return super.a(paramObject, paramMethod, paramArrayOfObject, paramContext);
    }
  }
  
  static class z
    extends ae
  {
    private z() {}
    
    protected Object a(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2, Context paramContext)
    {
      if ((paramObject2 instanceof InstrumentationInfo))
      {
        InstrumentationInfo localInstrumentationInfo = (InstrumentationInfo)paramObject2;
        if (n.a().f(localInstrumentationInfo.packageName)) {
          paramObject2 = null;
        }
      }
      for (;;)
      {
        return super.a(paramObject1, paramMethod, paramArrayOfObject, paramObject2, paramContext);
      }
    }
  }
}
