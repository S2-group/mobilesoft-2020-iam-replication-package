package com.mobileiron.compliance.apps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;
import com.mobileiron.MainService;
import com.mobileiron.common.ae;
import com.mobileiron.common.ag;
import com.mobileiron.common.s;
import com.mobileiron.common.u;
import com.mobileiron.common.utils.ad;
import com.mobileiron.common.utils.ah;
import com.mobileiron.common.x;
import com.mobileiron.compliance.MSComplianceManager;
import com.mobileiron.compliance.localcompliance.LocalComplianceManager;
import com.mobileiron.config.ConfigMarshaller;
import com.mobileiron.l;
import com.mobileiron.locksmith.f;
import com.mobileiron.signal.Slot;
import com.mobileiron.ui.LockSmithPasswordProxy;
import com.mobileiron.ui.MIZoneAppsActivity;
import com.mobileiron.ui.MIZoneErrActivity;
import com.mobileiron.ui.VerifyUserCredsActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class MSAppConnectManager
  extends com.mobileiron.compliance.a
  implements Slot
{
  private boolean b;
  private boolean c;
  private boolean d;
  private boolean e;
  private f f = f.a();
  private int g = -1;
  private Thread h;
  private int i;
  private boolean j;
  private Set k;
  private ArrayList l;
  private Handler m;
  private boolean n;
  private int o;
  private boolean p;
  private int q;
  private boolean r;
  
  public MSAppConnectManager(Context paramContext, String paramString)
  {
    super(paramContext, paramString);
    com.mobileiron.signal.b.a().a(this);
    this.e = true;
    this.m = new a(this);
    this.k = new HashSet();
    this.l = new ArrayList();
  }
  
  private void F()
  {
    ae.d("MSAppConnectManager", "Verify user creds.");
    if (!E())
    {
      f.a();
      if (f.n()) {}
    }
    else
    {
      if (!this.b) {
        break label43;
      }
    }
    ae.d("MSAppConnectManager", "Skip verify credentials since passcode is not required or user is already verified.");
    G();
    return;
    label43:
    ae.d("MSAppConnectManager", "Start user credential verification activity.");
    Intent localIntent = new Intent(this.a, VerifyUserCredsActivity.class);
    com.mobileiron.signal.b.a().a(com.mobileiron.signal.a.aI, new Object[] { localIntent, Integer.valueOf(1002), this });
  }
  
  private void G()
  {
    ae.d("MSAppConnectManager", "create password");
    if (!com.mobileiron.locksmith.e.a(this.a, "com.forgepond.locksmith"))
    {
      Toast.makeText(this.a, 2131493225, 1).show();
      ae.a("MSAppConnectManager", "Secure Apps Manager signature mismatch.");
      e(false);
      return;
    }
    try
    {
      Object localObject = N();
      ae.e("MSAppConnectManager", "createSecureAppsPassword ... ITPolicy: " + (String)localObject);
      f.a().b((String)localObject);
      localObject = new Intent();
      ((Intent)localObject).putExtra("mi_pkg", this.a.getPackageName());
      f.a(this, "com.forgepond.locksmith.setup.CreatePassword", 1001, (Intent)localObject);
      return;
    }
    catch (com.mobileiron.locksmith.g localG)
    {
      Toast.makeText(this.a, 2131493225, 1).show();
      ae.d("MSAppConnectManager", "LockSmithKeyMismatchException: MSAppConnectManager: createSecureAppsPassword()");
      return;
    }
    catch (com.mobileiron.locksmith.h localH)
    {
      ae.d("MSAppConnectManager", "NoLockSmithException: MSAppConnectManager: createSecureAppsPassword()");
    }
  }
  
  private boolean H()
  {
    s localS1 = r();
    String str = localS1.h("appConnectEnabled");
    s localS2 = localS1.j("itPolicy");
    if (localS2 == null) {
      throw new IllegalStateException("Malformed or non-existant ITPolicy");
    }
    boolean bool2 = com.mobileiron.compliance.utils.b.a(localS2, "SecurityPolicy").i("MIZone");
    boolean bool1 = bool2;
    if (str != null)
    {
      bool1 = localS1.i("appConnectEnabled");
      boolean bool3 = localS1.i("docsEnabled");
      boolean bool4 = localS1.i("webEnabled");
      if ((bool2) && ((bool1) || (bool3) || (bool4))) {
        bool1 = true;
      }
    }
    else
    {
      return bool1;
    }
    return false;
  }
  
  private boolean I()
  {
    String str = N();
    ae.e("MSAppConnectManager", "updatePasscodePolicy. ITPolicy: " + str);
    try
    {
      f.a().b(str);
      return true;
    }
    catch (com.mobileiron.locksmith.h localH)
    {
      ae.a("MSAppConnectManager", "NoLockSmithException in updatePasscodePolicy() while setting password policy");
    }
    return false;
  }
  
  private boolean J()
  {
    if (this.g == 3) {
      return true;
    }
    if ((L()) && (this.f.h()))
    {
      if (this.c)
      {
        ae.d("MSAppConnectManager", "isCompliant: compliance required key in memory");
        try
        {
          if (this.f.i())
          {
            ae.d("MSAppConnectManager", "Logged in. CONFIG COMPLETE.");
            this.c = false;
            return true;
          }
        }
        catch (com.mobileiron.locksmith.h localH)
        {
          ae.a("MSAppConnectManager", "NoLockSmithException in isConfigComplete()");
          return false;
        }
        return false;
      }
      ae.d("MSAppConnectManager", "isCompliant? TRUE. Installed & Secured.");
      return true;
    }
    ae.d("MSAppConnectManager", "isCompliant? FALSE");
    return false;
  }
  
  private void K()
  {
    if (s()) {
      return;
    }
    synchronized (MSComplianceManager.a().c("MSAppConnectManager.setConfigResultIfRequired"))
    {
      if (s()) {
        return;
      }
    }
    if (MSComplianceManager.a().a(this))
    {
      if (!M()) {
        break label68;
      }
      ae.a("MSAppConnectManager", "Config error in secure apps list.");
      a(1);
    }
    for (;;)
    {
      return;
      label68:
      if (com.mobileiron.a.c.b())
      {
        ae.a("MSAppConnectManager", "TIMA KeyStore is locked.");
        a(1);
      }
      else if (J())
      {
        a(0);
      }
    }
  }
  
  private boolean L()
  {
    boolean bool = false;
    if (!com.mobileiron.c.h().b("skip_ac_app_compliance", false)) {
      if (this.l == null) {
        break label38;
      }
    }
    label38:
    for (int i1 = this.l.size();; i1 = 0)
    {
      if (i1 == 0) {
        bool = true;
      }
      return bool;
    }
  }
  
  private boolean M()
  {
    return (this.g == 2) || (this.g == -1);
  }
  
  private String N()
  {
    return r().h("itPolicy");
  }
  
  private void a(Vector paramVector, Map paramMap)
  {
    this.l.clear();
    Iterator localIterator = paramVector.iterator();
    while (localIterator.hasNext())
    {
      u localU = (u)localIterator.next();
      String str = localU.a();
      if ((localU.m()) || (paramMap.containsKey(str)) || (e(str)))
      {
        int i1 = com.mobileiron.common.utils.c.a((PackageInfo)paramMap.get(str), localU.b(), localU.i());
        if ((!paramMap.containsKey(str)) && (com.mobileiron.common.utils.c.a(this.a, str) != -1))
        {
          ae.d("MSAppConnectManager", "Application " + localU.a() + " signature does not match Secure Apps Manager signature.");
        }
        else if (i1 == 2)
        {
          ae.e("MSAppConnectManager", "Application " + localU.a() + " is already installed.");
        }
        else if (i1 == 3)
        {
          ae.e("MSAppConnectManager", "Application " + localU.a() + " needs to be updated.");
          this.l.add(localU.a());
        }
        else if (i1 == 1)
        {
          ae.e("MSAppConnectManager", "Application " + localU.a() + " needs to be installed.");
          this.l.add(localU.a());
        }
      }
    }
    paramVector = com.mobileiron.locksmith.d.a(paramVector, paramMap).iterator();
    while (paramVector.hasNext())
    {
      paramMap = (PackageInfo)paramVector.next();
      ae.e("MSAppConnectManager", "Application " + paramMap.packageName + " needs to be uninstalled.");
      this.l.add(paramMap.packageName);
    }
    ae.d("MSAppConnectManager", "NumAppsNeedingAttention: " + this.l.size());
    com.mobileiron.signal.b.a().a(com.mobileiron.signal.a.C, new Object[] { Integer.valueOf(this.l.size()) });
  }
  
  private void a(Object[] paramArrayOfObject)
  {
    com.mobileiron.signal.b.a(paramArrayOfObject, new Class[] { Integer.class, Integer.class });
    int i1 = ((Integer)paramArrayOfObject[0]).intValue();
    int i2 = ((Integer)paramArrayOfObject[1]).intValue();
    ae.d("MSAppConnectManager", "slotActivityResult. RequestCode: " + i1 + " ResultCode: " + i2);
    if (i1 == 1001) {
      if (i2 == -1)
      {
        com.mobileiron.signal.b.a().b(com.mobileiron.signal.a.E, new Object[0]);
        ae.d("MSAppConnectManager", "Send connect now.");
        ConfigMarshaller.c().a(true);
        ae.d("MSAppConnectManager", "AppConnect check-in required in handlePasscodeResult.");
        b(new Object[] { ag.b });
        this.b = false;
      }
    }
    do
    {
      do
      {
        do
        {
          return;
          if (i2 == 1)
          {
            ae.d("MSAppConnectManager", "Secure apps blocked or create passcode cancelled.");
            return;
          }
        } while (i2 != 2);
        com.mobileiron.signal.b.a().b(com.mobileiron.signal.a.E, new Object[0]);
        return;
      } while (i1 != 1002);
      if (i2 == -1)
      {
        ae.d("MSAppConnectManager", "User credentials verified.");
        this.b = true;
        G();
        return;
      }
    } while (i2 != 1);
    ae.d("MSAppConnectManager", "User credential verification cancelled");
    MSComplianceManager.a().b(this);
  }
  
  private void b(Object[] paramArrayOfObject)
  {
    int i2 = paramArrayOfObject.length;
    int i1 = 0;
    for (;;)
    {
      if (i1 < i2)
      {
        Object localObject = paramArrayOfObject[i1];
        if ((localObject == ag.a) || (localObject == ag.b)) {
          com.mobileiron.locksmith.a.a().a(true);
        }
      }
      else
      {
        return;
      }
      i1 += 1;
    }
  }
  
  private void e(boolean paramBoolean)
  {
    ae.d("MSAppConnectManager", "Launch secure apps store.");
    MIZoneAppsActivity.a(this.a, paramBoolean);
  }
  
  private static void f(boolean paramBoolean)
  {
    com.mobileiron.c.h().a("appconnect_enabled", paramBoolean);
  }
  
  private void g(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.h == null)) {
      ae.d("MSAppConnectManager", "Updating secure apps list.");
    }
    try
    {
      com.mobileiron.locksmith.d.a();
      this.e = false;
      Vector localVector = com.mobileiron.locksmith.d.b();
      if (localVector == null) {
        break label309;
      }
      localMap = com.mobileiron.locksmith.d.b(this.a);
      this.i = 0;
      this.j = false;
      Iterator localIterator = localVector.iterator();
      while (localIterator.hasNext())
      {
        u localU = (u)localIterator.next();
        String str = localU.a();
        if ((localU.m()) || (localMap.containsKey(localU.a())) || (this.k.contains(str))) {
          this.i += 1;
        }
        if ("com.forgepond.locksmith".equals(str)) {
          this.j = true;
        }
      }
    }
    catch (Exception localException)
    {
      Map localMap;
      for (;;)
      {
        ae.a("MSAppConnectManager", "Failed to get secure apps list");
      }
      ae.e("MSAppConnectManager", "Number of secure apps: " + this.i);
      ae.e("MSAppConnectManager", "Locksmith included: " + this.j);
      a(localException, localMap);
      if (this.i != 0) {
        break label272;
      }
    }
    ae.d("MSAppConnectManager", "Secure apps are not required yet.");
    int i1 = 3;
    for (this.g = i1;; this.g = 2)
    {
      com.mobileiron.c.h().a("secure_apps_state", this.g);
      com.mobileiron.signal.b.a().a(com.mobileiron.signal.a.B, new Object[0]);
      return;
      label272:
      if (!this.j)
      {
        ae.a("MSAppConnectManager", "Locksmith is not included: configuration error");
        i1 = 2;
        break;
      }
      if (!L())
      {
        i1 = 1;
        break;
      }
      i1 = 0;
      break;
      label309:
      ae.a("MSAppConnectManager", "computeSecureAppsState: SecureApplications null");
    }
  }
  
  public static MSAppConnectManager y()
  {
    return (MSAppConnectManager)MSComplianceManager.a().e("AppConnectManager");
  }
  
  public final boolean A()
  {
    if (com.mobileiron.compliance.work.h.d(this.a)) {}
    int i1;
    do
    {
      do
      {
        return false;
      } while ((this.r) || (!z()));
      int i2 = this.g;
      i1 = i2;
      if (i2 == -1) {
        i1 = com.mobileiron.c.h().b("secure_apps_state", -1);
      }
    } while (i1 == 3);
    return true;
  }
  
  public final boolean B()
  {
    return !C().isEmpty();
  }
  
  public final List C()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.a.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.contains("forgepond.")) {
        localArrayList.add(localApplicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public final boolean D()
  {
    return (A()) && (this.f.h());
  }
  
  public final boolean E()
  {
    s localS = r();
    if (localS == null)
    {
      ae.d("MSAppConnectManager", "Config unavailable. Defaulting to passcode required");
      return true;
    }
    localS = com.mobileiron.compliance.utils.b.a(s.a(localS.h("itPolicy")), "AppConnectPolicy");
    if (localS.g("PASSWORDREQUIRED"))
    {
      boolean bool = localS.i("PASSWORDREQUIRED");
      ae.d("MSAppConnectManager", "Config available. Passcode required: " + bool);
      return bool;
    }
    ae.d("MSAppConnectManager", "Defaulting to passcode required");
    return true;
  }
  
  public final void a(s paramS)
  {
    ae.d("MSAppConnectManager", "updateCloseloop ...");
    if (com.mobileiron.compliance.work.h.d(this.a))
    {
      ae.d("MSAppConnectManager", "updateCloseloop: No AppConnect in afw managed client.");
      paramS.c("prv_appconnect_state", d.a(d.a));
      return;
    }
    if (!z())
    {
      ae.d("MSAppConnectManager", "updateCloseloop: AppConnect is not enabled in config.");
      paramS.c("prv_appconnect_state", d.a(d.a));
      return;
    }
    if (M())
    {
      ae.d("MSAppConnectManager", "updateCloseloop: Configuration error in list of apps pulled from VSP.");
      paramS.c("prv_appconnect_state", d.a(d.e));
      return;
    }
    Object localObject = d.d;
    if (!this.f.h())
    {
      localObject = d.c;
      if (!L()) {
        localObject = d.b;
      }
    }
    ae.d("MSAppConnectManager", "app connect state: " + localObject);
    paramS.c("prv_appconnect_state", d.a((d)localObject));
    try
    {
      localObject = this.f.b();
      if (localObject != null)
      {
        ae.d("MSAppConnectManager", "AppConnect closed loop");
        paramS.c("prv_appconnect_passcode", (String)localObject);
        this.f.c();
      }
    }
    catch (com.mobileiron.locksmith.h localH1)
    {
      for (;;)
      {
        ae.d("MSAppConnectManager", "PsscdScrt not found.");
      }
    }
    localObject = "Unavailable";
    try
    {
      String str = this.f.s();
      localObject = str;
    }
    catch (com.mobileiron.locksmith.h localH2)
    {
      for (;;)
      {
        ae.d("MSAppConnectManager", "Encryption Mode not found.");
      }
    }
    paramS.c("Secure Apps Encryption Mode", (String)localObject);
  }
  
  public final boolean a(String paramString1, String paramString2)
  {
    if (!z()) {}
    while ((!com.mobileiron.common.utils.c.c(paramString2)) || (this.p)) {
      return false;
    }
    ae.d("MSAppConnectManager", "appListChanged ...");
    if (com.mobileiron.locksmith.d.c())
    {
      g(false);
      K();
    }
    for (;;)
    {
      ae.d("MSAppConnectManager", "Setting AppConfig check-in required in MSAppConnectManager.appListChanged()");
      com.mobileiron.locksmith.a.a().a(true);
      return false;
      ae.a("MSAppConnectManager", "appListChanged: SecureApplications null");
    }
  }
  
  public final void b(String paramString) {}
  
  public final boolean b(s paramS)
  {
    s localS = r();
    localS.m("blocked");
    paramS.m("blocked");
    return !localS.equals(paramS);
  }
  
  public final void c(boolean paramBoolean)
  {
    this.c = paramBoolean;
  }
  
  public final boolean c(String paramString)
  {
    boolean bool = false;
    try
    {
      if (this.f.i())
      {
        if ("UNKNOWN".equals(com.mobileiron.locksmith.c.a(paramString)))
        {
          ae.d("MSAppConnectManager", "Setting forceInitiateCheckIn");
          this.d = true;
        }
        bool = com.mobileiron.locksmith.c.a(paramString, false);
      }
      return bool;
    }
    catch (com.mobileiron.locksmith.h paramString)
    {
      ae.a("MSAppConnectManager", "NoLockSmithException in canCommunicateWithNow()");
    }
    return false;
  }
  
  public final int d()
  {
    Object localObject = com.mobileiron.compliance.utils.c.a();
    ((com.mobileiron.compliance.utils.c)localObject).a(com.mobileiron.compliance.utils.e.l);
    ((com.mobileiron.compliance.utils.c)localObject).b(com.mobileiron.compliance.utils.e.l);
    if (com.mobileiron.compliance.work.h.d(this.a))
    {
      ae.d("MSAppConnectManager", "NOT CAPABLE in afw managed client.");
      return 4;
    }
    ae.d("MSAppConnectManager", "getComplianceState ...");
    this.r = false;
    boolean bool1 = H();
    boolean bool2 = z();
    if (bool2 != bool1)
    {
      ae.d("MSAppConnectManager", "Initiating AppConfig check-in in MSAppConnectManager.computeEnabledFromCore");
      f(true);
      com.mobileiron.locksmith.a.a().a(true);
      com.mobileiron.locksmith.a.a().b();
    }
    f(bool1);
    if ((bool2) && (!bool1)) {
      com.mobileiron.a.b.a().c(this.a);
    }
    s localS;
    if (z())
    {
      localS = r();
      if (localS == null)
      {
        ae.b("MSAppConnectManager", "config is null in isAcAppsRemovalRequired");
        bool1 = false;
        if (!bool1) {
          break label204;
        }
      }
    }
    label204:
    for (bool1 = true;; bool1 = false)
    {
      ad.a(bool1);
      if (z()) {
        break label209;
      }
      ae.d("MSAppConnectManager", "AppConnect not enabled in config. COMPLIANT!");
      return 0;
      bool1 = "allow".equals(com.mobileiron.compliance.utils.b.a(s.a(localS.h("itPolicy")), "AppConnectPolicy").h("REMOVE_APPS_ON_RETIRE"));
      break;
    }
    label209:
    ae.d("MSAppConnectManager", "AppConnect is enabled. Check if secure apps list has ever been downloaded yet.");
    if (com.mobileiron.locksmith.c.a()) {
      return 3;
    }
    g(this.e);
    if (this.g == 3)
    {
      ae.d("MSAppConnectManager", "Secure apps not required. COMPLIANT!");
      return 0;
    }
    if ((com.mobileiron.a.b.a().a(this.a, true)) && (!com.mobileiron.a.b.a().a(this.a)))
    {
      ae.d("MSAppConnectManager", "Valid KLM Key found. SE Container not available. WANT_ASYNC!");
      return 3;
    }
    if (this.g == 2)
    {
      ae.a("MSAppConnectManager", "Secure apps config error. INVALID CONFIG.");
      if ((!"NONE".equals(ad.a(this.a))) && (com.mobileiron.locksmith.d.b(this.a).isEmpty())) {
        ((com.mobileiron.compliance.utils.c)localObject).a(com.mobileiron.compliance.utils.e.l, 2131493461);
      }
      return 1;
    }
    if (this.g == 1)
    {
      if (com.mobileiron.locksmith.c.a("com.forgepond.locksmith", true)) {
        I();
      }
      ae.d("MSAppConnectManager", "Secure apps changed. WANT_ASYNCH.");
      return 3;
    }
    ae.d("MSAppConnectManager", "Secure apps are up-to-date. Check if LockSmith has been wiped");
    localObject = com.mobileiron.locksmith.c.a("com.forgepond.locksmith");
    if ("RETIRE".equals(localObject))
    {
      ae.d("MSAppConnectManager", "LockSmith has been wiped. DO NOTHING.");
      com.mobileiron.signal.b.a().a(com.mobileiron.signal.a.Y, new Object[] { com.mobileiron.locksmith.c.d("com.forgepond.locksmith") });
      return 0;
    }
    if (("UNKNOWN".equals(localObject)) || (this.d))
    {
      ae.b("MSAppConnectManager", "Enabled but no auth policy for LockSmith or Secure email client yet.");
      ae.d("MSAppConnectManager", "Forced initiate check-in? " + this.d);
      i1 = ConfigMarshaller.c().f().N();
      if (((i1 <= -2) || (i1 >= 4)) && (f.r() >= 2000))
      {
        i1 = 1;
        if (i1 == 0) {
          break label612;
        }
        ae.d("MSAppConnectManager", "Number of force check-ins: " + this.q);
        if (this.q >= 2) {
          break label601;
        }
        ae.d("MSAppConnectManager", "Require auth policy from VSP. Starting AppConfig check-in.");
        com.mobileiron.locksmith.a.a().a(true);
        ConfigMarshaller.c().a(true);
        this.d = false;
        this.q += 1;
      }
      for (;;)
      {
        return 1;
        i1 = 0;
        break;
        label601:
        ae.d("MSAppConnectManager", "No auth policy from VSP after multiple check-ins. Giving up.");
      }
      label612:
      ae.d("MSAppConnectManager", "OLD VSP or OLD LockSmith: Auth policy from VSP not required.");
    }
    while ("AUTHORIZED".equals(localObject))
    {
      int i1;
      ae.d("MSAppConnectManager", "LockSmith is accessible. Check for password.");
      if (I()) {
        break;
      }
      ae.a("MSAppConnectManager", "Unable to update passcode policy in LockSmith. INVALID CONFIG.");
      return 1;
    }
    ae.a("MSAppConnectManager", "Enabled but not authorized to access LockSmith. INVALID CONFIG.");
    return 1;
    if (!this.f.h())
    {
      if (com.mobileiron.a.c.b())
      {
        ae.d("MSAppConnectManager", "TIMAKeyStore is locked. DO NOTHING.");
        return 0;
      }
      ae.d("MSAppConnectManager", "No password set for the secure apps. WANT_ASYNCH.");
      return 3;
    }
    ae.d("MSAppConnectManager", "Secure apps have password. Check if compliance requires key in memory.");
    if (this.c) {
      ae.d("MSAppConnectManager", "Compliance requires key in memory");
    }
    try
    {
      if (!this.f.i())
      {
        ae.d("MSAppConnectManager", "Not logged in. WANT_ASYNCH.");
        return 3;
      }
      ae.d("MSAppConnectManager", "Already logged in.");
      this.c = false;
      ae.d("MSAppConnectManager", "COMPLIANT!");
      return 0;
    }
    catch (com.mobileiron.locksmith.h localH)
    {
      ae.a("MSAppConnectManager", "NoLockSmithException in getComplianceState(). INVALID CONFIG.");
    }
    return 1;
  }
  
  public final void d(boolean paramBoolean)
  {
    this.p = paramBoolean;
  }
  
  public final boolean d(String paramString)
  {
    if (this.q == 2) {
      return com.mobileiron.locksmith.c.a(paramString, false);
    }
    return com.mobileiron.locksmith.c.a(paramString, true);
  }
  
  public final int e()
  {
    throw new IllegalStateException("MSAppConnectManager told to applySynch");
  }
  
  public final boolean e(String paramString)
  {
    return (this.k.contains(paramString)) || ("com.forgepond.locksmith".equals(paramString));
  }
  
  public final void f()
  {
    ae.d("MSAppConnectManager", "applyAsynch ...");
    if (!A())
    {
      ae.a("MSAppConnectManager", "AppConnect not enabled in config. MSAppConnectManager told to applyAsynch. How'd we get here?");
      a(1);
    }
    do
    {
      return;
      if (!com.mobileiron.locksmith.c.a()) {
        break;
      }
    } while (this.h != null);
    this.h = new b(this);
    this.h.setName("ForceFetchThread");
    this.h.start();
    return;
    com.mobileiron.a.b localB = com.mobileiron.a.b.a();
    if ((localB.a(this.a, false)) && (!localB.a(this.a)))
    {
      ae.d("MSAppConnectManager", "SEContainer is enabled but no container available yet. Create SEContainer and add secure apps to it.");
      if (localB.b(this.a) != -1)
      {
        Object localObject = com.mobileiron.locksmith.d.a(this.a).iterator();
        PackageInfo localPackageInfo;
        while (((Iterator)localObject).hasNext())
        {
          localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          localB.b(this.a, localPackageInfo.packageName);
        }
        localObject = com.mobileiron.compliance.utils.b.a();
        if ((l.a().c()) || ((localObject != null) && (((String)localObject).endsWith("D"))))
        {
          localObject = new String[2];
          localObject[0] = "com.mobileiron.helloac4knox.trusted";
          localObject[1] = "com.mobileiron.helloac4knox.secured";
          int i2 = localObject.length;
          int i1 = 0;
          while (i1 < i2)
          {
            localPackageInfo = localObject[i1];
            if (com.mobileiron.common.utils.c.b(this.a, localPackageInfo)) {
              localB.b(this.a, localPackageInfo);
            }
            i1 += 1;
          }
        }
      }
      a(0);
      return;
    }
    if (M())
    {
      ae.a("MSAppConnectManager", "Config error in secure apps list. MSAppConnectManager told to applyAsynch. How'd we get here?");
      a(1);
      return;
    }
    if (!L())
    {
      ae.d("MSAppConnectManager", "applyAsynch: Apps changed.");
      MSComplianceManager.a().b(this);
      return;
    }
    if (!this.f.h())
    {
      if (!E())
      {
        f.a();
        if (f.n())
        {
          ae.d("MSAppConnectManager", "applyAsynch: Password not required. Directly proceeding to create passcode.");
          G();
          return;
        }
      }
      ae.d("MSAppConnectManager", "applyAsynch: No password present.");
      MSComplianceManager.a().b(this);
      return;
    }
    if (this.c)
    {
      ae.d("MSAppConnectManager", "applyAsynch: Compliance requires key in memory.");
      try
      {
        if (!this.f.i())
        {
          ae.d("MSAppConnectManager", "applyAsynch: Login required.");
          MSComplianceManager.a().b(this);
          return;
        }
      }
      catch (com.mobileiron.locksmith.h localH)
      {
        ae.a("MSAppConnectManager", "NoLockSmithException in applyAsynch()");
        a(1);
      }
    }
    for (;;)
    {
      ae.d("MSAppConnectManager", "applyAsynch DONE!");
      a(0);
      return;
      ae.d("MSAppConnectManager", "applyAsynch: Already logged in.");
      this.c = false;
    }
  }
  
  public final void g()
  {
    ae.c("MSAppConnectManager", "cancelAsynch");
    com.mobileiron.signal.b.a().a(com.mobileiron.signal.a.u, new Object[0]);
  }
  
  public final void h()
  {
    ae.d("MSAppConnectManager", "onRetire ...");
    this.r = true;
    if ((MainService.a()) && (ad.e()) && (com.mobileiron.locksmith.d.k()) && (com.mobileiron.compliance.utils.g.b()))
    {
      Iterator localIterator = C().iterator();
      while (localIterator.hasNext()) {
        com.mobileiron.common.utils.c.a((String)localIterator.next(), false);
      }
    }
    try
    {
      this.f.e();
      ae.d("MSAppConnectManager", "LockSmithConnector retired!");
      if (!MainService.a()) {
        com.mobileiron.signal.b.a().a(com.mobileiron.signal.a.B, new Object[0]);
      }
      return;
    }
    catch (com.mobileiron.locksmith.h localH)
    {
      for (;;)
      {
        ae.d("MSAppConnectManager", "NoLockSmithException: onRetire(). Can't retire!");
        ad.a(new File(Environment.getExternalStorageDirectory(), "AppConnect"), null);
      }
    }
  }
  
  public final int i()
  {
    return 2130837702;
  }
  
  public final com.mobileiron.signal.a[] j()
  {
    return new com.mobileiron.signal.a[] { com.mobileiron.signal.a.D, com.mobileiron.signal.a.E, com.mobileiron.signal.a.F, com.mobileiron.signal.a.b, com.mobileiron.signal.a.av, com.mobileiron.signal.a.aK, com.mobileiron.signal.a.aQ, com.mobileiron.signal.a.aW };
  }
  
  public final int k()
  {
    return 2130837700;
  }
  
  public final int l()
  {
    return 2131493279;
  }
  
  public final String m()
  {
    if ((!E()) || (D())) {
      return this.a.getString(2131493487);
    }
    return this.a.getString(2131493488);
  }
  
  public final void n()
  {
    ae.d("MSAppConnectManager", "userStart ...");
    if (!L())
    {
      ae.d("MSAppConnectManager", "userStart: Start app install activity.");
      if (!LocalComplianceManager.y().G()) {
        MIZoneErrActivity.a(this.a, com.mobileiron.locksmith.c.d("com.forgepond.locksmith"));
      }
    }
    do
    {
      return;
      if (D())
      {
        ae.d("MSAppConnectManager", "Secure apps installed and secure.");
        e(true);
        return;
      }
      if (M())
      {
        ae.a("MSAppConnectManager", "Secure apps config error.");
        e(false);
        return;
      }
      e(true);
      return;
      if (!this.f.h())
      {
        ae.d("MSAppConnectManager", "userStart: Start passcode setup/user verification.");
        if (!com.mobileiron.locksmith.c.a("com.forgepond.locksmith", false))
        {
          String str = com.mobileiron.locksmith.c.d("com.forgepond.locksmith");
          ae.a("MSAppConnectManager", "Cannot access secure apps: " + str);
          MIZoneErrActivity.a(this.a, str);
          return;
        }
        if (com.mobileiron.a.c.b())
        {
          ae.d("MSAppConnectManager", "TIMAKeyStore is locked. DO NOTHING.");
          MIZoneErrActivity.a(this.a, this.a.getString(2131493374));
          return;
        }
        F();
        return;
      }
    } while (!this.c);
    ae.d("MSAppConnectManager", "userStart: Compliance requires key in memory.");
    try
    {
      if (!this.f.i())
      {
        ae.d("MSAppConnectManager", "userStart: Start password activity.");
        LockSmithPasswordProxy.a(this.a);
        return;
      }
    }
    catch (com.mobileiron.locksmith.h localH)
    {
      ae.a("MSAppConnectManager", "NoLockSmithException in userStart(). FAILURE.");
      a(1);
      return;
    }
    ae.d("MSAppConnectManager", "userStart: Already logged in. SUCCESS!");
    this.c = false;
    a(0);
  }
  
  public final void o()
  {
    if (!z()) {
      return;
    }
    ae.d("MSAppConnectManager", "checkedIn ...");
    ae.d("MSAppConnectManager", "Initiating AppConfig check-in if required in MSAppConnectManager.checkedIn");
    com.mobileiron.locksmith.a.a().b();
    label69:
    u localU;
    if (!com.mobileiron.locksmith.d.c())
    {
      this.e = true;
      if (!this.f.c(r().h("appConnectChecksum"))) {
        break label154;
      }
      ae.d("MSAppConnectManager", "AppConnect checksums match");
      g(this.e);
      localU = com.mobileiron.common.utils.c.e("com.forgepond.locksmith");
      if ((localU != null) && (localU.q())) {
        break label165;
      }
    }
    label154:
    label165:
    for (int i1 = 0;; i1 = localU.b())
    {
      ae.d("MSAppConnectManager", "locksmith required version: " + i1);
      this.f.b(i1);
      K();
      return;
      this.e = "1".equals(r().h("appconn"));
      break;
      ae.d("MSAppConnectManager", "AppConnect checksums do not match");
      break label69;
    }
  }
  
  public final boolean p()
  {
    return (super.p()) || (!H());
  }
  
  public void slot(com.mobileiron.signal.a paramA, Object[] paramArrayOfObject)
  {
    ae.d("MSAppConnectManager", "slot: " + paramA);
    switch (c.a[paramA.ordinal()])
    {
    default: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      do
      {
        do
        {
          do
          {
            return;
            this.k.clear();
            this.l.clear();
            ae.d("MSAppConnectManager", "Starting AppConfig check-in on APP_CONNECT_APPS_SIGNAL");
            com.mobileiron.locksmith.a.a().a(true);
            com.mobileiron.common.d.b().a(true);
            ConfigMarshaller.c().a(true);
          } while (this.f.h());
          ae.d("MSAppConnectManager", "Proceeding to create passcode if required.");
          F();
          return;
          K();
          return;
          K();
          return;
        } while (com.mobileiron.compliance.utils.b.m() >= 70);
        ae.d("MSAppConnectManager", "slotDeviceAdminChanged ...");
        this.n = true;
        ae.d("MSAppConnectManager", "Starting AppConfig check-in in MSAppConnectManager.slotDeviceAdminChanged");
        com.mobileiron.locksmith.a.a().a(true);
        ConfigMarshaller.c().a(true);
        return;
        this.q = 0;
      } while (!this.n);
      ae.d("MSAppConnectManager", "slotProcessedAppConfigs ...");
      if ((com.mobileiron.compliance.utils.g.b() == com.mobileiron.locksmith.c.a("com.forgepond.locksmith", false)) || (this.o >= 2))
      {
        ae.d("MSAppConnectManager", "clear device admin change flag.");
        this.n = false;
        this.m.removeMessages(101);
        this.o = 0;
        return;
      }
      long l1 = (this.o * 2 + 1) * 60000L;
      ae.d("MSAppConnectManager", "Start delayed AppConfig check-in: " + l1);
      this.m.sendEmptyMessageDelayed(101, l1);
      this.o += 1;
      return;
    case 6: 
      com.mobileiron.signal.b.a(paramArrayOfObject, new Class[] { String.class });
      paramA = (String)paramArrayOfObject[0];
      this.k.add(paramA);
      return;
    case 7: 
      b(paramArrayOfObject);
      return;
    }
    a(paramArrayOfObject);
  }
  
  public final boolean z()
  {
    if (com.mobileiron.compliance.work.h.d(this.a)) {
      return false;
    }
    return com.mobileiron.c.h().b("appconnect_enabled", false);
  }
}
