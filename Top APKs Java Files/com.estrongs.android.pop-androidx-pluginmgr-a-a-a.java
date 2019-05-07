package androidx.pluginmgr.a.a;

import android.content.Context;
import java.util.Map;

public class a
  extends androidx.pluginmgr.a.c
{
  private static final String c = a.class.getSimpleName();
  
  public a(Context paramContext)
  {
    super(paramContext);
  }
  
  protected void a()
  {
    this.b.put("getPackageInfo", new aa(this, this.a));
    this.b.put("getPackageUid", new ac(this, this.a));
    this.b.put("getPackageGids", new z(this, this.a));
    this.b.put("currentToCanonicalPackageNames", new k(this, this.a));
    this.b.put("canonicalToCurrentPackageNames", new e(this, this.a));
    this.b.put("getPermissionInfo", new ag(this, this.a));
    this.b.put("queryPermissionsByGroup", new ay(this, this.a));
    this.b.put("getPermissionGroupInfo", new af(this, this.a));
    this.b.put("getAllPermissionGroups", new n(this, this.a));
    this.b.put("getApplicationInfo", new p(this, this.a));
    this.b.put("getActivityInfo", new m(this, this.a));
    this.b.put("getReceiverInfo", new al(this, this.a));
    this.b.put("getServiceInfo", new am(this, this.a));
    this.b.put("getProviderInfo", new ak(this, this.a));
    this.b.put("checkPermission", new f(this, this.a));
    this.b.put("checkUidPermission", new h(this, this.a));
    this.b.put("addPermission", new c(this, this.a));
    this.b.put("removePermission", new bb(this, this.a));
    this.b.put("grantPermission", new ao(this, this.a));
    this.b.put("revokePermission", new bh(this, this.a));
    this.b.put("checkSignatures", new g(this, this.a));
    this.b.put("getPackagesForUid", new ad(this, this.a));
    this.b.put("getNameForUid", new y(this, this.a));
    this.b.put("getUidForSharedUser", new an(this, this.a));
    this.b.put("getFlagsForUid", new r(this, this.a));
    this.b.put("resolveIntent", new bf(this, this.a));
    this.b.put("queryIntentActivities", new at(this, this.a));
    this.b.put("queryIntentActivityOptions", new au(this, this.a));
    this.b.put("queryIntentReceivers", new aw(this, this.a));
    this.b.put("resolveService", new bg(this, this.a));
    this.b.put("queryIntentServices", new ax(this, this.a));
    this.b.put("queryIntentContentProviders", new av(this, this.a));
    this.b.put("getInstalledPackages", new u(this, this.a));
    this.b.put("getPackagesHoldingPermissions", new ae(this, this.a));
    this.b.put("getInstalledApplications", new t(this, this.a));
    this.b.put("getPersistentApplications", new ah(this, this.a));
    this.b.put("resolveContentProvider", new be(this, this.a));
    this.b.put("querySyncProviders", new az(this, this.a));
    this.b.put("queryContentProviders", new ar(this, this.a));
    this.b.put("getInstrumentationInfo", new w(this, this.a));
    this.b.put("queryInstrumentation", new as(this, this.a));
    this.b.put("getInstallerPackageName", new v(this, this.a));
    this.b.put("addPackageToPreferred", new b(this, this.a));
    this.b.put("removePackageFromPreferred", new ba(this, this.a));
    this.b.put("getPreferredPackages", new aj(this, this.a));
    this.b.put("resetPreferredActivities", new bd(this, this.a));
    this.b.put("getLastChosenActivity", new x(this, this.a));
    this.b.put("setLastChosenActivity", new bk(this, this.a));
    this.b.put("addPreferredActivity", new d(this, this.a));
    this.b.put("replacePreferredActivity", new bc(this, this.a));
    this.b.put("clearPackagePreferredActivities", new j(this, this.a));
    this.b.put("getPreferredActivities", new ai(this, this.a));
    this.b.put("getHomeActivities", new s(this, this.a));
    this.b.put("setComponentEnabledSetting", new bj(this, this.a));
    this.b.put("getComponentEnabledSetting", new q(this, this.a));
    this.b.put("setApplicationEnabledSetting", new bi(this, this.a));
    this.b.put("getApplicationEnabledSetting", new o(this, this.a));
    this.b.put("setPackageStoppedState", new bl(this, this.a));
    this.b.put("deleteApplicationCacheFiles", new l(this, this.a));
    this.b.put("clearApplicationUserData", new i(this, this.a));
    this.b.put("getPackageSizeInfo", new ab(this, this.a));
    this.b.put("performDexOpt", new aq(this, this.a));
    this.b.put("movePackage", new ap(this, this.a));
  }
}
