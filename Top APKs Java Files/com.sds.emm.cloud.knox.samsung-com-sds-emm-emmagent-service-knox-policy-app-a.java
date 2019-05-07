package com.sds.emm.emmagent.service.knox.policy.app;

import android.app.enterprise.AppControlInfo;
import android.app.enterprise.ApplicationPolicy;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import com.sds.emm.emmagent.core.actionentity.base.RuleType;
import com.sds.emm.emmagent.core.actionentity.filters.SamsungStandardSdk;
import com.sds.emm.emmagent.core.actionentity.result.EmmErrorCode;
import com.sds.emm.emmagent.core.event.EventSender;
import com.sds.emm.emmagent.core.event.internal.app.EMMKnoxAppInstallEventListener;
import com.sds.emm.emmagent.core.event.internal.app.EMMKnoxAppUninstallEventListener;
import com.sds.emm.emmagent.core.event.internal.profile.EMMEasGateKeepingEventListener;
import com.sds.emm.emmagent.core.event.internal.workprofile.EMMWorkProfileCreateEventListener;
import com.sds.emm.emmagent.core.event.internal.workprofile.EMMWorkProfileRemoveEventListener;
import com.sds.emm.emmagent.core.event.system.PackageAddedEventListener;
import com.sds.emm.emmagent.core.event.system.PackageChangedEventListener;
import com.sds.emm.emmagent.core.event.system.PackageRemovedEventListener;
import com.sds.emm.emmagent.core.event.system.PackageReplacedEventListener;
import com.sds.emm.emmagent.core.profile.entity.KnoxAreaProfileEntity;
import com.sds.emm.emmagent.core.support.log.PolicyInvoker;
import com.sds.emm.emmagent.core.support.log.i;
import com.sds.emm.emmagent.service.general.inventory.app.AppEntity;
import com.sds.emm.emmagent.service.general.inventory.app.AppInventoryEntity;
import com.sds.emm.emmagent.service.general.inventory.exchangeactivesync.ExchangeActiveSyncInventoryEntity;
import com.sds.emm.emmagent.service.general.policy.app.m;
import com.sds.emm.emmagent.service.knox.policy.browser.BrowserPolicyEntity;
import com.sec.enterprise.AppIdentity;
import com.sec.enterprise.knox.container.ContainerConfigurationPolicy;
import com.sec.enterprise.knox.container.KnoxContainerManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SamsungStandardSdk(a=com.sds.emm.emmagent.core.actionentity.filters.e.SDK_2_0)
public class a
  extends com.sds.emm.emmagent.core.profile.knox.policy.a<AppPolicyEntity>
  implements EMMKnoxAppInstallEventListener, EMMKnoxAppUninstallEventListener, EMMEasGateKeepingEventListener, EMMWorkProfileCreateEventListener, EMMWorkProfileRemoveEventListener, PackageAddedEventListener, PackageChangedEventListener, PackageRemovedEventListener, PackageReplacedEventListener
{
  @RuleType(a="AppDeletionBlackList")
  private final PolicyInvoker<String> c = new PolicyInvoker();
  @RuleType(a="AppRunningBlackList")
  private final PolicyInvoker<String> d = new PolicyInvoker();
  @RuleType(a="AppModificationMeasure")
  private final PolicyInvoker<String> e = new PolicyInvoker();
  @RuleType(a="ShowDownloadProgressbar")
  private final PolicyInvoker<Void> f = new PolicyInvoker();
  private int g = -1;
  private final Set<String> h = new HashSet();
  private final com.sds.emm.emmagent.core.support.deltalist.b<String> i = new com.sds.emm.emmagent.core.support.deltalist.b();
  private com.sds.emm.emmagent.core.support.deltalist.b<String> j = new com.sds.emm.emmagent.core.support.deltalist.b();
  private List<PackageInfo> k = null;
  private com.sds.emm.emmagent.core.support.deltalist.b<AppEntity> l = null;
  private List<String> m = null;
  private List<String> n = null;
  private List<String> o = null;
  
  public a() {}
  
  private <T> void a(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt, boolean paramBoolean)
  {
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getApplicationPolicy(), "addAppPackageNameToWhiteList", new Object[] { paramString, Boolean.valueOf(paramBoolean) });
  }
  
  private void a(String paramString1, String paramString2, int paramInt)
  {
    if (!this.h.contains(paramString2)) {
      this.h.add(paramString2);
    }
    paramString1 = a().b(new String[] { paramString1, i.a.a(Integer.valueOf(paramInt)), AGENT.co.f.a(f()) });
    this.c.setLogger(paramString1, AGENT.cd.a.a());
    this.d.setLogger(paramString1, AGENT.cd.a.a());
    if (a(paramString1, paramString2, com.sds.emm.emmagent.service.general.policy.app.b.DELETION_BLACK_LIST, paramInt)) {
      b(this.c, paramString2, paramInt);
    }
    if ((a(paramString1, paramString2, com.sds.emm.emmagent.service.general.policy.app.b.RUNNING_BLACK_LIST, paramInt)) && (!com.sds.emm.emmagent.service.general.policy.app.l.b(paramString2))) {
      e(this.d, paramString2, paramInt);
    }
  }
  
  private boolean a(String paramString, int paramInt)
  {
    return AGENT.ch.a.b(paramInt).getApplicationPolicy().getApplicationUninstallationEnabled(paramString);
  }
  
  private <T> String[] a(PolicyInvoker<T> paramPolicyInvoker, boolean paramBoolean, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return (String[])i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getApplicationStateList", new Object[] { Boolean.valueOf(paramBoolean) }), new String[0]);
  }
  
  private <T> void b(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "setApplicationUninstallationDisabled", new Object[] { paramString });
    paramPolicyInvoker.invokeCC(Boolean.valueOf(false), localApplicationPolicy, "getApplicationUninstallationEnabled", new Object[] { paramString });
  }
  
  private void b(String paramString)
  {
    Object localObject = i();
    if (!com.sds.emm.emmagent.core.support.f.a((com.sds.emm.emmagent.core.support.deltalist.b)localObject))
    {
      localObject = ((com.sds.emm.emmagent.core.support.deltalist.b)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        AppEntity localAppEntity = (AppEntity)((Iterator)localObject).next();
        if ((AGENT.ck.g.a(localAppEntity.a(), paramString)) && (localAppEntity.b() != null) && ((localAppEntity.b().contains("com.android.email")) || (localAppEntity.b().contains("com.samsung.android.email")))) {
          this.i.b(localAppEntity.b());
        }
      }
    }
  }
  
  private <T> void b(String paramString, int paramInt)
  {
    AGENT.ch.a.b(paramInt).getApplicationPolicy().setApplicationUninstallationDisabled(paramString);
  }
  
  private com.sds.emm.emmagent.core.support.log.f c(String paramString)
  {
    KnoxAreaProfileEntity localKnoxAreaProfileEntity = AGENT.cd.a.b();
    Object localObject1 = localKnoxAreaProfileEntity.getId();
    com.sds.emm.emmagent.core.support.log.f localF = a().b(new String[] { paramString, i.a.a(Integer.valueOf(this.g)), AGENT.co.f.a(f()) });
    this.d.setLogger(localF, AGENT.cd.a.a());
    if ((this.i != null) && (this.i.d() == 0)) {
      b((String)localObject1);
    }
    Iterator localIterator = this.i.iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (String)localIterator.next();
      if (com.sds.emm.emmagent.core.support.b.b(this.g, (String)localObject1))
      {
        Object localObject2 = new AppEntity(localKnoxAreaProfileEntity.getId(), (String)localObject1);
        String str;
        com.sds.emm.emmagent.service.general.policy.app.b localB;
        com.sds.emm.emmagent.service.general.policy.app.a localA;
        if (AGENT.ck.g.a(paramString, "LockEas"))
        {
          e(this.d, (String)localObject1, this.g);
          localObject1 = com.sds.emm.emmagent.core.controller.l.d();
          str = ((AppEntity)localObject2).a();
          localObject2 = ((AppEntity)localObject2).b();
          localB = com.sds.emm.emmagent.service.general.policy.app.b.RUNNING_BLACK_LIST;
          localA = com.sds.emm.emmagent.service.general.policy.app.a.EAS_GATEKEEPING;
        }
        for (m localM = m.ADD;; localM = m.REMOVE)
        {
          ((EventSender)localObject1).onAppChanged(str, (String)localObject2, localB, localA, localM);
          break;
          if (!AGENT.ck.g.a(paramString, "UnlockEas")) {
            break;
          }
          int i1;
          if ((((AppPolicyEntity)f()).j() != null) && (((AppPolicyEntity)f()).j().contains(localObject1))) {
            i1 = 0;
          } else {
            i1 = 1;
          }
          if (i1 != 0) {
            f(this.d, (String)localObject1, this.g);
          }
          localObject1 = com.sds.emm.emmagent.core.controller.l.d();
          str = ((AppEntity)localObject2).a();
          localObject2 = ((AppEntity)localObject2).b();
          localB = com.sds.emm.emmagent.service.general.policy.app.b.RUNNING_BLACK_LIST;
          localA = com.sds.emm.emmagent.service.general.policy.app.a.EAS_GATEKEEPING;
        }
      }
    }
    return localF;
  }
  
  private <T> void c(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "setApplicationUninstallationEnabled", new Object[] { paramString });
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), localApplicationPolicy, "getApplicationUninstallationEnabled", new Object[] { paramString });
  }
  
  private <T> void c(String paramString, int paramInt)
  {
    AGENT.ch.a.b(paramInt).getApplicationPolicy().setApplicationUninstallationEnabled(paramString);
  }
  
  private <T> boolean d(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return i.a.b(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getApplicationStateEnabled", new Object[] { paramString }));
  }
  
  private <T> void e(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    if (this.h.contains(paramString)) {}
    for (;;)
    {
      i1 = 1;
      break label44;
      if (!com.sds.emm.emmagent.core.support.b.b(paramInt, paramString)) {
        break;
      }
      this.h.add(paramString);
    }
    int i1 = 0;
    label44:
    if (i1 != 0) {
      paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getApplicationPolicy(), "setDisableApplication", new Object[] { paramString });
    }
  }
  
  private <T> void f(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    if (this.h.contains(paramString)) {}
    for (;;)
    {
      i1 = 1;
      break label44;
      if (!com.sds.emm.emmagent.core.support.b.b(paramInt, paramString)) {
        break;
      }
      this.h.add(paramString);
    }
    int i1 = 0;
    label44:
    if (d(paramPolicyInvoker, paramString, paramInt)) {
      i1 = 0;
    }
    if (i1 != 0) {
      paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getApplicationPolicy(), "setEnableApplication", new Object[] { paramString });
    }
  }
  
  private <T> void g(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getApplicationPolicy(), "addAppPackageNameToBlackList", new Object[] { paramString });
  }
  
  private <T> void h(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getApplicationPolicy(), "removeAppPackageNameFromWhiteList", new Object[] { paramString });
  }
  
  private <T> void i(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getContainerConfigurationPolicy(), "addPackageToInstallWhiteList", new Object[] { paramString });
  }
  
  private <T> void j(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getContainerConfigurationPolicy(), "removePackageFromInstallWhiteList", new Object[] { paramString });
  }
  
  protected AppPolicyEntity a(com.sds.emm.emmagent.core.support.log.f paramF, int paramInt1, AppPolicyEntity paramAppPolicyEntity, int paramInt2, int paramInt3)
  {
    if ((paramInt2 < 21300) && (paramInt3 >= 21300))
    {
      paramF = new ArrayList();
      boolean bool = com.sds.emm.emmagent.core.support.f.a(paramAppPolicyEntity.j());
      int i1 = 0;
      String str;
      if (!bool)
      {
        localObject = paramAppPolicyEntity.j().iterator();
        paramInt2 = 0;
        for (;;)
        {
          paramInt1 = paramInt2;
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          str = (String)((Iterator)localObject).next();
          if (((!com.sds.emm.emmagent.core.controller.l.D().c()) || (!com.sds.emm.emmagent.service.general.policy.app.l.c(str))) && ((!com.sds.emm.emmagent.core.controller.l.x().g()) || (!com.sds.emm.emmagent.service.general.policy.app.l.d(str))) && (!AGENT.ck.g.a(str, "com.android.chrome")))
          {
            paramF.add(str);
            paramInt2 |= 0x1;
          }
        }
      }
      paramInt1 = 0;
      Object localObject = h.a();
      paramInt3 = paramInt1;
      if (localObject != null)
      {
        int i2 = localObject.length;
        paramInt2 = i1;
        for (;;)
        {
          paramInt3 = paramInt1;
          if (paramInt2 >= i2) {
            break;
          }
          str = localObject[paramInt2];
          if (com.sds.emm.emmagent.core.controller.l.D().c())
          {
            paramInt3 = paramInt1;
            if (com.sds.emm.emmagent.service.general.policy.app.l.c(str)) {}
          }
          else if (com.sds.emm.emmagent.core.controller.l.x().g())
          {
            paramInt3 = paramInt1;
            if (com.sds.emm.emmagent.service.general.policy.app.l.d(str)) {}
          }
          else if (AGENT.ck.g.a(str, "com.android.chrome"))
          {
            paramInt3 = paramInt1;
          }
          else
          {
            paramInt3 = paramInt1;
            if (!paramF.contains(str))
            {
              paramF.add(str);
              paramInt3 = paramInt1 | 0x1;
            }
          }
          paramInt2 += 1;
          paramInt1 = paramInt3;
        }
      }
      if (paramInt3 != 0) {
        paramAppPolicyEntity.f(paramF);
      }
    }
    return paramAppPolicyEntity;
  }
  
  <T> List<AppControlInfo> a(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getAppPackageNamesAllBlackLists", new Object[0]), new ArrayList());
  }
  
  <T> List<String> a(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt1, int paramInt2)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt2).getApplicationPolicy();
    return i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getRuntimePermissions", new Object[] { paramString, Integer.valueOf(paramInt1) }), new ArrayList());
  }
  
  <T> void a(PolicyInvoker<T> paramPolicyInvoker, AppIdentity paramAppIdentity, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    if (this.h.contains(paramAppIdentity.getPackageName())) {}
    for (;;)
    {
      paramInt = 1;
      break label60;
      if (!com.sds.emm.emmagent.core.support.b.b(paramInt, paramAppIdentity.getPackageName())) {
        break;
      }
      this.h.add(paramAppIdentity.getPackageName());
    }
    paramInt = 0;
    label60:
    if (paramInt != 0) {
      paramPolicyInvoker.invokeCC(Integer.valueOf(0), localApplicationPolicy, "addPackageToBatteryOptimizationWhiteList", new Object[] { paramAppIdentity });
    }
  }
  
  <T> void a(PolicyInvoker<T> paramPolicyInvoker, AppIdentity paramAppIdentity, List<String> paramList, int paramInt1, int paramInt2)
  {
    paramPolicyInvoker.invokeCC(Integer.valueOf(0), AGENT.ch.a.b(paramInt2).getApplicationPolicy(), "applyRuntimePermissions", new Object[] { paramAppIdentity, paramList, Integer.valueOf(paramInt1) });
  }
  
  <T> void a(PolicyInvoker<T> paramPolicyInvoker, String paramString, int paramInt)
  {
    paramPolicyInvoker.invokeCC(Boolean.valueOf(true), AGENT.ch.a.b(paramInt).getApplicationPolicy(), "removeAppPackageNameFromBlackList", new Object[] { paramString });
  }
  
  <T> void a(PolicyInvoker<T> paramPolicyInvoker, List<String> paramList, int paramInt)
  {
    paramPolicyInvoker.invokeIgnoreResultCC(AGENT.ch.a.b(paramInt).getApplicationPolicy(), "addPackagesToPreventStartBlackList", new Object[] { paramList });
  }
  
  <T> void a(PolicyInvoker<T> paramPolicyInvoker, List<com.sds.emm.emmagent.core.support.deltalist.a<String>> paramList, com.sds.emm.emmagent.service.general.policy.app.b paramB, int paramInt, String paramString, boolean paramBoolean)
  {
    paramString = paramList.iterator();
    while (paramString.hasNext())
    {
      com.sds.emm.emmagent.core.support.deltalist.a localA = (com.sds.emm.emmagent.core.support.deltalist.a)paramString.next();
      if (!AGENT.ck.g.a((CharSequence)localA.a())) {}
      for (paramList = localA.a();; paramList = localA.b())
      {
        paramList = (String)paramList;
        break;
      }
      switch (1.b[localA.c().ordinal()])
      {
      default: 
        break;
      case 2: 
        switch (1.a[paramB.ordinal()])
        {
        case 3: 
        default: 
          break;
        case 7: 
          j(paramPolicyInvoker, paramList, paramInt);
          break;
        case 6: 
          b(paramPolicyInvoker, new AppIdentity(paramList, null), paramInt);
          break;
        case 5: 
          h(paramPolicyInvoker, paramList, paramInt);
          break;
        case 4: 
          a(paramPolicyInvoker, paramList, paramInt);
          break;
        case 2: 
          f(paramPolicyInvoker, paramList, paramInt);
          break;
        case 1: 
          if (paramPolicyInvoker != null) {
            c(paramPolicyInvoker, paramList, paramInt);
          } else {
            c(paramList, paramInt);
          }
          break;
        }
        break;
      case 1: 
        switch (1.a[paramB.ordinal()])
        {
        case 3: 
        default: 
          break;
        case 7: 
          i(paramPolicyInvoker, paramList, paramInt);
          break;
        case 6: 
          a(paramPolicyInvoker, new AppIdentity(paramList, null), paramInt);
          break;
        case 5: 
          a(paramPolicyInvoker, paramList, paramInt, paramBoolean);
          break;
        case 4: 
          g(paramPolicyInvoker, paramList, paramInt);
          break;
        case 2: 
          e(paramPolicyInvoker, paramList, paramInt);
          break;
        case 1: 
          if (paramPolicyInvoker != null) {
            b(paramPolicyInvoker, paramList, paramInt);
          } else {
            b(paramList, paramInt);
          }
          break;
        }
        break;
      }
    }
  }
  
  protected void a(com.sds.emm.emmagent.core.support.log.f paramF, int paramInt, AppPolicyEntity paramAppPolicyEntity, com.sds.emm.emmagent.core.profile.general.d paramD)
  {
    String str = AGENT.cd.a.b().getId();
    this.g = paramInt;
    com.sds.emm.emmagent.service.general.policy.app.a localA = com.sds.emm.emmagent.service.general.policy.app.a.KNOX_POLICY;
    Object localObject3 = (AppInventoryEntity)com.sds.emm.emmagent.core.controller.l.g().a(AppInventoryEntity.class);
    Object localObject1 = (AppPolicyEntity)com.sds.emm.emmagent.core.controller.l.o().b(this.g, AppPolicyEntity.class);
    this.m = com.sds.emm.emmagent.core.controller.l.s().a(com.sds.emm.emmagent.core.profile.common.appintegrity.g.MANDATORY, com.sds.emm.emmagent.core.profile.common.appintegrity.e.KNOX);
    if (this.m == null) {
      this.m = new ArrayList();
    }
    this.n = com.sds.emm.emmagent.core.controller.l.s().a(com.sds.emm.emmagent.core.profile.common.appintegrity.h.SYSTEM);
    if (this.n == null) {
      this.n = new ArrayList();
    }
    this.o = com.sds.emm.emmagent.core.controller.l.s().a(com.sds.emm.emmagent.core.profile.common.appintegrity.d.EMM_APP_USE);
    if (this.o == null) {
      this.o = new ArrayList();
    }
    this.k = com.sds.emm.emmagent.core.support.b.a(this.g);
    if (this.k == null) {
      this.k = new ArrayList();
    }
    if (localObject3 != null)
    {
      this.l = ((AppInventoryEntity)localObject3).j();
      if (this.l == null) {
        this.l = new com.sds.emm.emmagent.core.support.deltalist.b();
      }
    }
    new com.sds.emm.emmagent.core.support.deltalist.b();
    new com.sds.emm.emmagent.core.support.deltalist.b();
    new com.sds.emm.emmagent.core.support.deltalist.b();
    paramD = com.sds.emm.emmagent.service.general.policy.app.l.a(((AppInventoryEntity)localObject3).g(), str);
    paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
    com.sds.emm.emmagent.core.support.deltalist.b localB = new com.sds.emm.emmagent.core.support.deltalist.b();
    if (!com.sds.emm.emmagent.core.support.f.a(paramD))
    {
      paramD = paramD.iterator();
      while (paramD.hasNext())
      {
        localObject2 = (String)paramD.next();
        if ((!this.m.contains(localObject2)) && (!this.n.contains(localObject2)) && (!this.o.contains(localObject2))) {
          paramF.b(localObject2);
        } else {
          localB.b(localObject2);
        }
      }
    }
    paramD = com.sds.emm.emmagent.core.support.deltalist.b.a(paramAppPolicyEntity.i());
    Object localObject2 = com.sds.emm.emmagent.service.general.policy.app.l.b(((AppInventoryEntity)localObject3).g(), str);
    if (!com.sds.emm.emmagent.core.support.f.a(((AppPolicyEntity)localObject1).i()))
    {
      paramD.b(((AppPolicyEntity)localObject1).i());
      ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(com.sds.emm.emmagent.service.general.policy.app.l.a(((AppPolicyEntity)localObject1).i(), str, com.sds.emm.emmagent.service.general.policy.app.a.INITIAL_KNOX_POLICY));
    }
    this.c.apply(paramD);
    boolean bool = com.sds.emm.emmagent.service.general.policy.app.l.a(paramF, paramD);
    int i2 = 1;
    int i1;
    if (bool)
    {
      a(this.c, paramF.d(paramD), com.sds.emm.emmagent.service.general.policy.app.b.DELETION_BLACK_LIST, this.g, str, false);
      i1 = 1;
    }
    else
    {
      i1 = 0;
    }
    Object localObject4 = new com.sds.emm.emmagent.core.support.deltalist.b();
    Iterator localIterator;
    if (!com.sds.emm.emmagent.core.support.f.a(paramAppPolicyEntity.i()))
    {
      localIterator = paramAppPolicyEntity.i().iterator();
      while (localIterator.hasNext())
      {
        paramF = new AppEntity(str, (String)localIterator.next());
        if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).a(paramF))
        {
          paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).d(paramF)).d();
          paramF = paramD;
          if (paramD == null) {
            paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
          }
          paramF.b(localA);
        }
        else
        {
          paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
          paramD.b(localA);
          paramF.a(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(paramF);
        }
      }
    }
    if (!com.sds.emm.emmagent.core.support.f.a(this.m))
    {
      ((com.sds.emm.emmagent.core.support.deltalist.b)localObject4).b(this.m);
      localIterator = this.m.iterator();
      while (localIterator.hasNext())
      {
        paramF = new AppEntity(str, (String)localIterator.next());
        if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).a(paramF))
        {
          paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).d(paramF)).d();
          paramF = paramD;
          if (paramD == null) {
            paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
          }
          paramF.b(com.sds.emm.emmagent.service.general.policy.app.a.MANDATORY);
        }
        else
        {
          paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
          paramD.b(com.sds.emm.emmagent.service.general.policy.app.a.MANDATORY);
          paramF.a(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(paramF);
        }
      }
    }
    if (!com.sds.emm.emmagent.core.support.f.a(this.n))
    {
      ((com.sds.emm.emmagent.core.support.deltalist.b)localObject4).b(this.n);
      localIterator = this.n.iterator();
      while (localIterator.hasNext())
      {
        paramF = new AppEntity(str, (String)localIterator.next());
        if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).a(paramF))
        {
          paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).d(paramF)).d();
          paramF = paramD;
          if (paramD == null) {
            paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
          }
          paramF.b(com.sds.emm.emmagent.service.general.policy.app.a.SYSTEM);
        }
        else
        {
          paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
          paramD.b(com.sds.emm.emmagent.service.general.policy.app.a.SYSTEM);
          paramF.a(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(paramF);
        }
      }
    }
    if (!com.sds.emm.emmagent.core.support.f.a(this.o))
    {
      ((com.sds.emm.emmagent.core.support.deltalist.b)localObject4).b(this.o);
      localIterator = this.o.iterator();
      while (localIterator.hasNext())
      {
        paramF = new AppEntity(str, (String)localIterator.next());
        if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).a(paramF))
        {
          paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).d(paramF)).d();
          paramF = paramD;
          if (paramD == null) {
            paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
          }
          paramF.b(com.sds.emm.emmagent.service.general.policy.app.a.EMM_BE_USED);
        }
        else
        {
          paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
          paramD.b(com.sds.emm.emmagent.service.general.policy.app.a.EMM_BE_USED);
          paramF.a(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(paramF);
        }
      }
    }
    if (com.sds.emm.emmagent.service.general.policy.app.l.a(localB, localObject4)) {
      a(null, localB.d((com.sds.emm.emmagent.core.support.deltalist.b)localObject4), com.sds.emm.emmagent.service.general.policy.app.b.DELETION_BLACK_LIST, this.g, str, false);
    } else {
      i2 = 0;
    }
    if ((i1 != 0) || (i2 != 0)) {
      com.sds.emm.emmagent.core.controller.l.d().onAppDeletionBlackListChanged((com.sds.emm.emmagent.core.support.deltalist.b)localObject2);
    }
    new com.sds.emm.emmagent.core.support.deltalist.b();
    new com.sds.emm.emmagent.core.support.deltalist.b();
    new com.sds.emm.emmagent.core.support.deltalist.b();
    localB = com.sds.emm.emmagent.service.general.policy.app.l.a(((AppInventoryEntity)localObject3).h(), str);
    localObject2 = com.sds.emm.emmagent.core.support.deltalist.b.a(paramAppPolicyEntity.j());
    localObject3 = com.sds.emm.emmagent.service.general.policy.app.l.b(((AppInventoryEntity)localObject3).h(), str);
    if (!com.sds.emm.emmagent.core.support.f.a(((AppPolicyEntity)localObject1).j()))
    {
      ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(((AppPolicyEntity)localObject1).j());
      ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).b(com.sds.emm.emmagent.service.general.policy.app.l.a(((AppPolicyEntity)localObject1).j(), str, com.sds.emm.emmagent.service.general.policy.app.a.INITIAL_KNOX_POLICY));
    }
    if (!com.sds.emm.emmagent.core.support.f.a(paramAppPolicyEntity.j()))
    {
      localObject4 = paramAppPolicyEntity.j().iterator();
      while (((Iterator)localObject4).hasNext())
      {
        paramF = new AppEntity(str, (String)((Iterator)localObject4).next());
        if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).a(paramF))
        {
          paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).d(paramF)).d();
          paramF = paramD;
          if (paramD == null) {
            paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
          }
          paramF.b(localA);
        }
        else
        {
          paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
          paramD.b(localA);
          paramF.a(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).b(paramF);
        }
      }
    }
    if (com.sds.emm.emmagent.core.controller.l.D().c())
    {
      paramF = com.sds.emm.emmagent.service.general.policy.app.l.a();
      if (!com.sds.emm.emmagent.core.support.f.a(paramF))
      {
        paramF = paramF.iterator();
        while (paramF.hasNext())
        {
          paramD = (String)paramF.next();
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).c(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).c(new AppEntity(str, paramD));
        }
      }
    }
    if (com.sds.emm.emmagent.core.controller.l.x().g())
    {
      paramF = com.sds.emm.emmagent.service.general.policy.app.l.b();
      if (!com.sds.emm.emmagent.core.support.f.a(paramF))
      {
        paramF = paramF.iterator();
        while (paramF.hasNext())
        {
          paramD = (String)paramF.next();
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).c(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).c(new AppEntity(str, paramD));
        }
      }
    }
    paramF = (BrowserPolicyEntity)com.sds.emm.emmagent.core.controller.l.o().a(paramInt, BrowserPolicyEntity.class);
    if ((paramF != null) && (AGENT.ck.g.a(paramF.c(), "Disallow")))
    {
      if ((this.j != null) && (this.j.d() == 0)) {
        this.j = com.sds.emm.emmagent.service.general.policy.app.l.c();
      }
      paramF = this.j.iterator();
      while (paramF.hasNext())
      {
        localObject4 = (String)paramF.next();
        if (com.sds.emm.emmagent.core.support.b.b(paramInt, (String)localObject4))
        {
          paramD = new AppEntity(str, (String)localObject4);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(localObject4);
          localObject4 = new com.sds.emm.emmagent.core.support.deltalist.b();
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject4).b(localA);
          paramD.a((com.sds.emm.emmagent.core.support.deltalist.b)localObject4);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).b(paramD);
        }
      }
    }
    paramF = (ExchangeActiveSyncInventoryEntity)com.sds.emm.emmagent.core.controller.l.g().a(ExchangeActiveSyncInventoryEntity.class);
    if ((paramF != null) && (paramF.f() != null) && (paramF.f() == com.sds.emm.emmagent.service.general.inventory.exchangeactivesync.a.NON_COMPLIANCE))
    {
      if ((this.i != null) && (this.i.d() == 0)) {
        b(str);
      }
      localObject4 = this.i.iterator();
      while (((Iterator)localObject4).hasNext())
      {
        paramD = (String)((Iterator)localObject4).next();
        if (com.sds.emm.emmagent.core.support.b.b(this.g, paramD))
        {
          paramF = new AppEntity(str, paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(paramD);
          if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).a(paramF))
          {
            paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).d(paramF)).d();
            paramF = paramD;
            if (paramD == null) {
              paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
            }
            paramF.b(com.sds.emm.emmagent.service.general.policy.app.a.EAS_GATEKEEPING);
          }
          else
          {
            paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
            paramD.b(com.sds.emm.emmagent.service.general.policy.app.a.EAS_GATEKEEPING);
            paramF.a(paramD);
            ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).b(paramF);
          }
        }
      }
    }
    if (!com.sds.emm.emmagent.core.support.f.a(this.n))
    {
      paramF = this.n.iterator();
      while (paramF.hasNext())
      {
        paramD = (String)paramF.next();
        if ((localObject2 != null) && (((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).a(paramD)))
        {
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).c(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).c(new AppEntity(str, paramD));
        }
      }
    }
    if (!com.sds.emm.emmagent.core.support.f.a(this.o))
    {
      paramF = this.o.iterator();
      while (paramF.hasNext())
      {
        paramD = (String)paramF.next();
        if ((localObject2 != null) && (((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).a(paramD)))
        {
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).c(paramD);
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).c(new AppEntity(str, paramD));
        }
      }
    }
    if (!com.sds.emm.emmagent.core.support.f.a(((AppPolicyEntity)localObject1).j()))
    {
      localObject1 = ((AppPolicyEntity)localObject1).j().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        paramD = (String)((Iterator)localObject1).next();
        if (((paramD.startsWith("com.android")) || (paramD.startsWith("com.google.android"))) && ((!com.sds.emm.emmagent.core.controller.l.D().c()) || (!com.sds.emm.emmagent.service.general.policy.app.l.c(paramD))) && ((!com.sds.emm.emmagent.core.controller.l.x().g()) || (!com.sds.emm.emmagent.service.general.policy.app.l.d(paramD))))
        {
          paramF = new AppEntity(str, paramD);
          if (AGENT.ck.g.a(paramAppPolicyEntity.f(), "Allow"))
          {
            if ((localB != null) && (!localB.a(paramD))) {
              localB.b(paramD);
            }
            ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).c(paramD);
            ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).c(paramF);
          }
          else if (AGENT.ck.g.a(paramAppPolicyEntity.f(), "Disallow"))
          {
            ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(paramD);
            if (((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).a(paramF))
            {
              paramD = ((AppEntity)((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).d(paramF)).d();
              paramF = paramD;
              if (paramD == null) {
                paramF = new com.sds.emm.emmagent.core.support.deltalist.b();
              }
              paramF.b(localA);
            }
            else
            {
              paramD = new com.sds.emm.emmagent.core.support.deltalist.b();
              paramD.b(localA);
              paramF.a(paramD);
              ((com.sds.emm.emmagent.core.support.deltalist.b)localObject3).b(paramF);
            }
          }
        }
      }
    }
    this.d.apply(localObject2);
    if (this.d.isChanged())
    {
      a(this.d, localB.d((com.sds.emm.emmagent.core.support.deltalist.b)localObject2), com.sds.emm.emmagent.service.general.policy.app.b.RUNNING_BLACK_LIST, this.g, str, false);
      com.sds.emm.emmagent.core.controller.l.d().onAppRunningBlackListChanged((com.sds.emm.emmagent.core.support.deltalist.b)localObject3);
    }
  }
  
  boolean a(com.sds.emm.emmagent.core.support.log.f paramF, String paramString, com.sds.emm.emmagent.service.general.policy.app.b paramB, int paramInt)
  {
    AppInventoryEntity localAppInventoryEntity = (AppInventoryEntity)com.sds.emm.emmagent.core.controller.l.g().a(AppInventoryEntity.class);
    KnoxAreaProfileEntity localKnoxAreaProfileEntity = AGENT.cd.a.b();
    Object localObject2 = null;
    Object localObject1;
    Object localObject3;
    if (localAppInventoryEntity != null)
    {
      switch (1.a[paramB.ordinal()])
      {
      default: 
        break;
      case 6: 
        localObject1 = localAppInventoryEntity.n();
        break;
      case 5: 
        localObject2 = localAppInventoryEntity.e();
        localObject1 = localAppInventoryEntity.p();
        break;
      case 4: 
        localObject2 = localAppInventoryEntity.f();
        localObject1 = localAppInventoryEntity.o();
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
      case 3: 
        localObject1 = localAppInventoryEntity.i();
        break;
      case 2: 
        localObject1 = localAppInventoryEntity.h();
        break;
      }
      localObject1 = localAppInventoryEntity.g();
    }
    else
    {
      localObject1 = null;
    }
    boolean bool1 = true;
    if (localObject1 != null) {
      paramF.a(new String[] { paramB.getReadableName(), ((com.sds.emm.emmagent.core.support.deltalist.b)localObject1).toString() });
    }
    if (!com.sds.emm.emmagent.core.support.f.a((com.sds.emm.emmagent.core.support.deltalist.b)localObject1))
    {
      paramF = new AppEntity(localKnoxAreaProfileEntity.getId(), paramString);
      boolean bool3 = ((com.sds.emm.emmagent.core.support.deltalist.b)localObject1).a(paramF);
      bool1 = bool3;
      if (!com.sds.emm.emmagent.core.support.f.a((com.sds.emm.emmagent.core.support.deltalist.b)localObject2))
      {
        bool2 = bool3;
        if (com.sds.emm.emmagent.service.general.policy.app.b.INSTALLATION_BLACK_LIST == paramB)
        {
          bool2 = bool3;
          if (!bool3)
          {
            localObject1 = ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).iterator();
            do
            {
              bool2 = bool3;
              if (!((Iterator)localObject1).hasNext()) {
                break;
              }
              localObject3 = (AppEntity)((Iterator)localObject1).next();
            } while ((!AGENT.ck.g.a(((AppEntity)localObject3).a(), localKnoxAreaProfileEntity.getId())) || (!paramString.matches(((AppEntity)localObject3).b())));
            bool2 = true;
          }
        }
        bool1 = bool2;
        if (com.sds.emm.emmagent.service.general.policy.app.b.INSTALLATION_WHITE_LIST == paramB)
        {
          bool1 = bool2;
          if (!bool2)
          {
            localObject1 = ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).iterator();
            do
            {
              bool1 = bool2;
              if (!((Iterator)localObject1).hasNext()) {
                break;
              }
              localObject2 = (AppEntity)((Iterator)localObject1).next();
            } while ((!AGENT.ck.g.a(((AppEntity)localObject2).a(), localKnoxAreaProfileEntity.getId())) || (!paramString.matches(((AppEntity)localObject2).b())));
            bool1 = true;
          }
        }
      }
      boolean bool2 = bool1;
      if (com.sds.emm.emmagent.service.general.policy.app.b.INSTALLATION_BLACK_LIST == paramB)
      {
        bool2 = bool1;
        if (bool1)
        {
          paramB = localAppInventoryEntity.e();
          localObject1 = localAppInventoryEntity.p();
          if (paramB.a(paramF)) {
            bool1 = false;
          }
          bool2 = bool1;
          if (bool1)
          {
            paramF = ((com.sds.emm.emmagent.core.support.deltalist.b)localObject1).iterator();
            do
            {
              bool2 = bool1;
              if (!paramF.hasNext()) {
                break;
              }
              paramB = (AppEntity)paramF.next();
            } while ((!AGENT.ck.g.a(paramB.a(), localKnoxAreaProfileEntity.getId())) || (!paramString.matches(paramB.b())));
            break label540;
          }
        }
      }
      return bool2;
    }
    else
    {
      if (com.sds.emm.emmagent.service.general.policy.app.b.INSTALLATION_WHITE_LIST == paramB) {
        break label543;
      }
      if (com.sds.emm.emmagent.service.general.policy.app.b.RUNNING_WHITE_LIST == paramB) {
        return true;
      }
    }
    label540:
    bool1 = false;
    label543:
    return bool1;
  }
  
  protected <T> boolean a(com.sds.emm.emmagent.core.support.log.f paramF, List<String> paramList, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return i.a.b(paramF.a(localApplicationPolicy, "addPackagesToForceStopBlackList", new Object[] { paramList }));
  }
  
  protected AppPolicyEntity b(com.sds.emm.emmagent.core.support.log.f paramF, int paramInt)
  {
    AppPolicyEntity localAppPolicyEntity = new AppPolicyEntity();
    Object localObject2 = com.sds.emm.emmagent.core.support.b.a(paramInt);
    Object localObject1 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    localAppPolicyEntity.a("Disallow");
    localAppPolicyEntity.a(com.sds.emm.emmagent.service.general.policy.app.c.COMPLIANCE_ALERT);
    if (localObject2 != null)
    {
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        if ((localPackageInfo != null) && (!a(localPackageInfo.packageName, paramInt))) {
          ((List)localObject1).add(localPackageInfo.packageName);
        }
      }
    }
    localAppPolicyEntity.e((List)localObject1);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("[");
    if (!((List)localObject1).isEmpty())
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        ((StringBuilder)localObject2).append((String)((Iterator)localObject1).next());
        ((StringBuilder)localObject2).append(", ");
      }
      ((StringBuilder)localObject2).deleteCharAt(((StringBuilder)localObject2).length() - 2);
    }
    ((StringBuilder)localObject2).append("]");
    int i1 = 0;
    paramF.a(new String[] { "Code", "AppDeletionBlackList", ((StringBuilder)localObject2).toString() });
    paramF = a(this.d, false, paramInt);
    int i2;
    if (paramF != null)
    {
      i2 = paramF.length;
      paramInt = 0;
      while (paramInt < i2)
      {
        localObject1 = paramF[paramInt];
        if (!AGENT.ck.g.a((CharSequence)localObject1, "com.android.chrome")) {
          localArrayList.add(localObject1);
        }
        paramInt += 1;
      }
    }
    paramF = h.a();
    if (paramF != null)
    {
      i2 = paramF.length;
      paramInt = i1;
      while (paramInt < i2)
      {
        localObject1 = paramF[paramInt];
        if ((!AGENT.ck.g.a((CharSequence)localObject1, "com.android.chrome")) && (!localArrayList.contains(localObject1))) {
          localArrayList.add(localObject1);
        }
        paramInt += 1;
      }
    }
    localAppPolicyEntity.f(localArrayList);
    return localAppPolicyEntity;
  }
  
  <T> List<AppControlInfo> b(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getAppPackageNamesAllWhiteLists", new Object[0]), new ArrayList());
  }
  
  <T> void b(PolicyInvoker<T> paramPolicyInvoker, AppIdentity paramAppIdentity, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    if (this.h.contains(paramAppIdentity.getPackageName())) {}
    for (;;)
    {
      paramInt = 1;
      break label60;
      if (!com.sds.emm.emmagent.core.support.b.b(paramInt, paramAppIdentity.getPackageName())) {
        break;
      }
      this.h.add(paramAppIdentity.getPackageName());
    }
    paramInt = 0;
    label60:
    if (paramInt != 0) {
      paramPolicyInvoker.invokeCC(Integer.valueOf(0), localApplicationPolicy, "removePackageFromBatteryOptimizationWhiteList", new Object[] { paramAppIdentity });
    }
  }
  
  <T> List<String> c(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    ContainerConfigurationPolicy localContainerConfigurationPolicy = AGENT.ch.a.b(paramInt).getContainerConfigurationPolicy();
    return i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localContainerConfigurationPolicy, "getPackagesFromInstallWhiteList", new Object[0]), new ArrayList());
  }
  
  <T> List<String> d(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getPackagesFromPreventStartBlackList", new Object[0]), new ArrayList());
  }
  
  <T> void e(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    paramPolicyInvoker.invokeIgnoreResultCC(AGENT.ch.a.b(paramInt).getApplicationPolicy(), "clearPreventStartBlackList", new Object[0]);
  }
  
  <T> List<String> f(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getPackagesFromBatteryOptimizationWhiteList", new Object[0]), new ArrayList());
  }
  
  <T> String[] g(PolicyInvoker<T> paramPolicyInvoker, int paramInt)
  {
    ApplicationPolicy localApplicationPolicy = AGENT.ch.a.b(paramInt).getApplicationPolicy();
    return (String[])i.a.a(paramPolicyInvoker.invokeIgnoreResultCC(localApplicationPolicy, "getInstalledApplicationsIDList", new Object[0]), new String[0]);
  }
  
  List<PackageInfo> h()
  {
    return this.k;
  }
  
  com.sds.emm.emmagent.core.support.deltalist.b<AppEntity> i()
  {
    return this.l;
  }
  
  public List<String> j()
  {
    return this.n;
  }
  
  public void onInstallKnoxAppFailed(String paramString1, String paramString2, EmmErrorCode paramEmmErrorCode) {}
  
  public void onInstallKnoxAppStarted(String paramString1, String paramString2) {}
  
  public void onInstallKnoxAppSucceeded(String paramString1, String paramString2)
  {
    if (Build.VERSION.SDK_INT > 23)
    {
      paramString2 = AGENT.cd.a.b();
      if (paramString2 != null) {
        a("InstallKnoxAppSucceeded", paramString1, AGENT.cl.a.a(paramString2.d()));
      }
    }
  }
  
  public void onKnoxContainerCreated(String paramString, int paramInt) {}
  
  public void onKnoxContainerRemoved(@NotNull String paramString)
  {
    Object localObject1 = (AppInventoryEntity)com.sds.emm.emmagent.core.controller.l.g().a(AppInventoryEntity.class);
    Object localObject2 = new com.sds.emm.emmagent.core.support.deltalist.b();
    com.sds.emm.emmagent.core.support.deltalist.b localB = new com.sds.emm.emmagent.core.support.deltalist.b();
    if (!com.sds.emm.emmagent.core.support.f.a(((AppInventoryEntity)localObject1).g()))
    {
      Iterator localIterator = ((AppInventoryEntity)localObject1).g().iterator();
      while (localIterator.hasNext())
      {
        AppEntity localAppEntity = (AppEntity)localIterator.next();
        if (!AGENT.ck.g.a(paramString, localAppEntity.a())) {
          ((com.sds.emm.emmagent.core.support.deltalist.b)localObject2).b(localAppEntity);
        }
      }
    }
    com.sds.emm.emmagent.core.controller.l.d().onAppDeletionBlackListChanged((com.sds.emm.emmagent.core.support.deltalist.b)localObject2);
    if (!com.sds.emm.emmagent.core.support.f.a(((AppInventoryEntity)localObject1).h()))
    {
      localObject1 = ((AppInventoryEntity)localObject1).h().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (AppEntity)((Iterator)localObject1).next();
        if (!AGENT.ck.g.a(paramString, ((AppEntity)localObject2).a())) {
          localB.b(localObject2);
        }
      }
    }
    com.sds.emm.emmagent.core.controller.l.d().onAppRunningBlackListChanged(localB);
  }
  
  public void onLockEas(com.sds.emm.emmagent.service.general.function.lockeas.a paramA)
  {
    c("LockEas");
  }
  
  public void onPackageAdded(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt2)
  {
    if ((paramInt2 > 0) && (!paramBoolean1)) {
      a("PackageAdded", paramString, paramInt2);
    }
  }
  
  public void onPackageChanged(int paramInt1, @Nullable String[] paramArrayOfString, boolean paramBoolean, @NotNull String paramString, int paramInt2) {}
  
  public void onPackageRemoved(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt2)
  {
    if ((paramInt2 > 0) && (!paramBoolean2) && (this.h.contains(paramString))) {
      this.h.remove(paramString);
    }
  }
  
  public void onPackageReplaced(int paramInt1, String paramString, int paramInt2)
  {
    if (paramInt2 > 0) {
      a("PackageReplaced", paramString, paramInt2);
    }
  }
  
  public void onUninstallKnoxAppFailed(String paramString1, String paramString2, EmmErrorCode paramEmmErrorCode) {}
  
  public void onUninstallKnoxAppStarted(String paramString1, String paramString2) {}
  
  public void onUninstallKnoxAppSucceeded(String paramString1, String paramString2)
  {
    if ((Build.VERSION.SDK_INT > 23) && (this.h.contains(paramString1))) {
      this.h.remove(paramString1);
    }
  }
  
  public void onUnlockEas(com.sds.emm.emmagent.service.general.function.lockeas.a paramA)
  {
    c("UnlockEas");
  }
}
