package com.mobileiron.acom.mdm.knox.a;

import android.content.Context;
import com.mobileiron.acom.core.android.AndroidRelease;
import com.mobileiron.acom.core.android.d;
import com.mobileiron.acom.core.android.g;
import com.mobileiron.acom.core.utils.n;
import com.mobileiron.acom.core.utils.o;
import com.mobileiron.acom.mdm.knox.b.b;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.EnterpriseDeviceManager;
import com.samsung.android.knox.EnterpriseKnoxManager;
import com.samsung.android.knox.accounts.EmailPolicy;
import com.samsung.android.knox.accounts.ExchangeAccountPolicy;
import com.samsung.android.knox.application.AppControlInfo;
import com.samsung.android.knox.application.ApplicationPolicy;
import com.samsung.android.knox.bluetooth.BluetoothPolicy;
import com.samsung.android.knox.datetime.DateTimePolicy;
import com.samsung.android.knox.devicesecurity.PasswordPolicy;
import com.samsung.android.knox.keystore.CertificatePolicy;
import com.samsung.android.knox.multiuser.MultiUserManager;
import com.samsung.android.knox.net.wifi.WifiControlInfo;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.samsung.android.knox.restriction.AdvancedRestrictionPolicy;
import com.samsung.android.knox.restriction.PhoneRestrictionPolicy;
import com.samsung.android.knox.restriction.RestrictionPolicy;
import com.samsung.android.knox.restriction.RestrictionPolicy.USBInterface;
import com.samsung.android.knox.restriction.RoamingPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;

public final class a
{
  private static final Logger a = n.a("KnoxDeviceUtils");
  private static final String[] b = { "com.android.browser", "com.android.chrome", "com.sec.android.app.sbrowser", "com.sec.webbrowserminiapp" };
  private static EnterpriseDeviceManager c;
  private static EnterpriseKnoxManager d;
  private static RestrictionPolicy e;
  private static PasswordPolicy f;
  private static ApplicationPolicy g;
  private static ExchangeAccountPolicy h;
  private static EmailPolicy i;
  private static PhoneRestrictionPolicy j;
  private static RoamingPolicy k;
  private static DateTimePolicy l;
  private static CertificatePolicy m;
  private static MultiUserManager n;
  private static WifiPolicy o;
  private static BluetoothPolicy p;
  private static AdvancedRestrictionPolicy q;
  
  public static boolean A()
  {
    boolean bool = false;
    l("isMultiUserModeSupported");
    if (n != null) {}
    try
    {
      n.multipleUsersAllowed();
      bool = true;
      return bool;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      a.info("MultiUserMode is NOT supported");
    }
    return false;
  }
  
  public static boolean A(boolean paramBoolean)
  {
    boolean bool2 = false;
    if (com.mobileiron.acom.mdm.knox.b.a.f())
    {
      d("disableUsbHidProtocol");
      if (e != null)
      {
        boolean bool1 = bool2;
        try
        {
          int i2 = e.getUsbExceptionList();
          switch (i2)
          {
          }
          for (;;)
          {
            int i1 = i2;
            if (i2 == 64536)
            {
              bool1 = bool2;
              a.warn("getUsbExceptionList returned error code {}", Integer.valueOf(64536));
              i1 = 0;
            }
            if (paramBoolean)
            {
              bool1 = bool2;
              i1 &= (RestrictionPolicy.USBInterface.HID.getValue() ^ 0xFFFFFFFF);
            }
            for (;;)
            {
              bool1 = bool2;
              paramBoolean = e.setUsbExceptionList(i1);
              bool1 = paramBoolean;
              a.info("KNOX API RestrictionPolicy.setUsbExceptionList({}) returns: {}", a(i1), Boolean.valueOf(paramBoolean));
              return paramBoolean;
              i1 = i2;
              if (!paramBoolean)
              {
                bool1 = bool2;
                i1 = RestrictionPolicy.USBInterface.HID.getValue();
                continue;
                i1 = i2;
                if (paramBoolean)
                {
                  bool1 = bool2;
                  i1 = RestrictionPolicy.USBInterface.OFF.getValue() & (RestrictionPolicy.USBInterface.HID.getValue() ^ 0xFFFFFFFF);
                  continue;
                  bool1 = bool2;
                  i2 = RestrictionPolicy.USBInterface.HID.getValue();
                  i1 |= i2;
                }
              }
            }
          }
        }
        catch (SecurityException localSecurityException)
        {
          a.warn("KNOX API RestrictionPolicy.setUsbExceptionList SecurityException", localSecurityException);
          return bool1;
        }
      }
    }
    return false;
  }
  
  public static boolean B()
  {
    boolean bool2 = false;
    f("isDisabledNewAdminInstallation");
    boolean bool1 = bool2;
    if (g != null) {}
    try
    {
      boolean bool3 = g.isNewAdminInstallationEnabled(false);
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API ApplicationPolicy.isNewAdminInstallationEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean B(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (com.mobileiron.acom.mdm.knox.b.a.g())
    {
      bool1 = bool2;
      if (e("enableIrisUnlock")) {
        bool1 = b(f, paramBoolean);
      }
    }
    return bool1;
  }
  
  public static boolean C()
  {
    boolean bool2 = false;
    d("isDisabledCellularData");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isCellularDataAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isCellularDataAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean C(boolean paramBoolean)
  {
    boolean bool;
    if (!com.mobileiron.acom.mdm.knox.b.a.i()) {
      bool = E(paramBoolean);
    }
    do
    {
      return bool;
      E(true);
      bool = false;
    } while (!e("enableFaceUnlock"));
    return c(f, paramBoolean);
  }
  
  public static boolean D()
  {
    boolean bool2 = false;
    d("isDisabledUsbHidProtocol");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      int i1 = e.getUsbExceptionList();
      a.debug("Current USB exceptions: {}", a(i1));
      int i2 = RestrictionPolicy.USBInterface.HID.getValue();
      bool1 = bool2;
      if ((i1 & i2) == 0) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.getUsbExceptionList() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean D(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableFactoryReset");
    if (e != null) {
      if (paramBoolean) {
        break label52;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowFactoryReset(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowFactoryReset({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label52:
        a.warn("KNOX API RestrictionPolicy.allowFactoryReset({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static Map<Integer, String> E()
  {
    if ((b.b() >= 13) && (e("getSupportedBiometricAuthentications"))) {
      try
      {
        Map localMap = f.getSupportedBiometricAuthentications();
        return localMap;
      }
      catch (SecurityException localSecurityException)
      {
        a.info("KNOX API PasswordPolicy getSupportedBiometricAuthentications issues SecurityException");
      }
    }
    return new HashMap();
  }
  
  private static boolean E(boolean paramBoolean)
  {
    if (f("enableFaceUnlockLegacy"))
    {
      if (paramBoolean) {}
      try
      {
        paramBoolean = g.setEnableApplication("com.samsung.android.bio.face.service");
        a.info("KNOX API ApplicationPolicy.setEnableApplication({}) returns: {}", "com.samsung.android.bio.face.service", Boolean.valueOf(paramBoolean));
        return paramBoolean;
      }
      catch (SecurityException localSecurityException)
      {
        a.warn("enableFaceUnlockLegacy SecurityException", localSecurityException);
        return false;
      }
      paramBoolean = g.setDisableApplication("com.samsung.android.bio.face.service");
      a.info("KNOX API ApplicationPolicy.setDisableApplication({}) returns: {}", "com.samsung.android.bio.face.service", Boolean.valueOf(paramBoolean));
      return paramBoolean;
    }
    return false;
  }
  
  public static boolean F()
  {
    if ((com.mobileiron.acom.mdm.knox.b.a.g()) && (e("isIrisUnlockEnabled"))) {
      try
      {
        boolean bool = f.isBiometricAuthenticationEnabled(2);
        return bool;
      }
      catch (SecurityException localSecurityException)
      {
        a.warn("KNOX API isIrisUnlockEnabled SecurityException", localSecurityException);
      }
    }
    return true;
  }
  
  public static boolean G()
  {
    if (!com.mobileiron.acom.mdm.knox.b.a.i())
    {
      if (f("enableFaceUnlockLegacy")) {
        return g.getApplicationStateEnabled("com.samsung.android.bio.face.service");
      }
      return false;
    }
    if (e("isFaceUnlockEnabled")) {
      try
      {
        boolean bool = f.isBiometricAuthenticationEnabled(4);
        return bool;
      }
      catch (SecurityException localSecurityException)
      {
        a.warn("KNOX API PasswordPolicy.isBiometricAuthenticationEnabled SecurityException", localSecurityException);
      }
    }
    return true;
  }
  
  public static boolean H()
  {
    boolean bool2 = false;
    d("isDisabledFactoryReset");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isFactoryResetAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isFactoryResetAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static List<String> I()
  {
    f("getAppsBlackList");
    try
    {
      List localList = e(g.getAppPackageNamesAllBlackLists());
      return localList;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API ApplicationPolicy.getAppPackageNamesAllBlackLists SecurityException", localSecurityException);
    }
    return new ArrayList();
  }
  
  public static List<String> J()
  {
    f("getAppsBlackList");
    try
    {
      List localList = e(g.getAppPackageNamesAllWhiteLists());
      return localList;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API ApplicationPolicy.getAppPackageNamesAllWhiteLists SecurityException", localSecurityException);
    }
    return new ArrayList();
  }
  
  public static List<String> K()
  {
    m("getWifiSsidsBlackList");
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (o != null) {}
    try
    {
      localObject1 = o.getWifiSsidsFromBlackLists();
      return f((List)localObject1);
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        a.warn("KNOX API WifiPolicy.getWifiSsidsFromBlackLists() SecurityException", localSecurityException);
        Object localObject2 = localObject3;
      }
    }
  }
  
  public static List<String> L()
  {
    m("getWifiSsidsWhiteList");
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (o != null) {}
    try
    {
      localObject1 = o.getWifiSsidsFromWhiteLists();
      return f((List)localObject1);
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        a.warn("KNOX API WifiPolicy.getWifiSsidsFromWhiteLists() SecurityException", localSecurityException);
        Object localObject2 = localObject3;
      }
    }
  }
  
  private static boolean M()
  {
    if (b.a()) {
      if (d.h()) {
        return com.mobileiron.acom.mdm.knox.c.a.a();
      }
    }
    for (;;)
    {
      try
      {
        if ((q == null) && (c("premiumPrivilegesEnforced")))
        {
          AdvancedRestrictionPolicy localAdvancedRestrictionPolicy = d.getAdvancedRestrictionPolicy();
          q = localAdvancedRestrictionPolicy;
          if (localAdvancedRestrictionPolicy == null) {
            a.warn("{}, AdvancedRestrictionPolicy = null", "premiumPrivilegesEnforced");
          }
        }
        if (q == null) {
          break label106;
        }
        i1 = 1;
        if (i1 != 0)
        {
          q.allowFirmwareAutoUpdate(q.isFirmwareAutoUpdateAllowed(false));
          return true;
        }
      }
      catch (Exception localException)
      {
        a.debug("Exception while testing premium privileges: {}", localException.toString());
      }
      return false;
      label106:
      int i1 = 0;
    }
  }
  
  public static String a()
  {
    Object localObject2 = null;
    b("getActiveSyncId");
    Object localObject1 = localObject2;
    if (c != null) {
      if ((AndroidRelease.i()) && (b.b() >= 19))
      {
        f("getActiveSyncId");
        if (g == null) {}
      }
    }
    try
    {
      Object localObject3 = g.getRuntimePermissions("com.samsung.android.email.provider", 1);
      localObject1 = localObject3;
      if (!((List)localObject3).contains("android.permission.CALL_PHONE"))
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("android.permission.ACCESS_COARSE_LOCATION");
        ((List)localObject1).add("android.permission.CALL_PHONE");
      }
      localObject3 = new AppIdentity("com.samsung.android.email.provider", null);
      int i1 = g.applyRuntimePermissions((AppIdentity)localObject3, (List)localObject1, 1);
      if (i1 != 0) {
        a.warn("getActiveSyncId: Failed to grant runtime permissions {} to package {} result returns: {}", new Object[] { localObject1, "com.samsung.android.email.provider", Integer.valueOf(i1) });
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        a.warn("getActiveSyncId SecurityException", localSecurityException);
      }
    }
    if ((h == null) && (b("getActiveSyncId")))
    {
      localObject1 = c.getExchangeAccountPolicy();
      h = (ExchangeAccountPolicy)localObject1;
      if (localObject1 == null) {
        a.warn("{}, ExchangeAccountPolicy = null", "getActiveSyncId");
      }
    }
    if (h != null) {}
    localObject1 = localObject2;
    if (h != null) {
      localObject1 = h.getDeviceId();
    }
    return localObject1;
  }
  
  private static String a(int paramInt)
  {
    if (paramInt == -1) {
      return "0xffffffff";
    }
    return "0x" + Integer.toString(paramInt, 16);
  }
  
  public static boolean a(PasswordPolicy paramPasswordPolicy, boolean paramBoolean)
  {
    int i1 = 1;
    for (;;)
    {
      boolean bool2;
      int i2;
      try
      {
        boolean bool1 = paramPasswordPolicy.isBiometricAuthenticationEnabled(2);
        bool2 = paramPasswordPolicy.isBiometricAuthenticationEnabled(4);
        if (paramBoolean)
        {
          if (bool1)
          {
            i1 = 3;
            break label87;
            bool1 = paramPasswordPolicy.setBiometricAuthenticationEnabled(i2, paramBoolean);
            a.info("KNOX API PasswordPolicy.setBiometricAuthenticationEnabled(BIOMETRIC_AUTHENTICATION_FINGERPRINT, {}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
            return bool1;
          }
        }
        else
        {
          bool1 = paramPasswordPolicy.setBiometricAuthenticationEnabled(1, paramBoolean);
          continue;
        }
        i2 = i1;
      }
      catch (SecurityException paramPasswordPolicy)
      {
        a.warn("enableFingerprintUnlock SecurityException", paramPasswordPolicy);
        return false;
      }
      label87:
      if (bool2) {
        i2 = i1 | 0x4;
      }
    }
  }
  
  private static boolean a(String paramString)
  {
    return ("com.android.chrome".equals(paramString)) && (AndroidRelease.k()) && (d.b());
  }
  
  public static boolean a(List<String> paramList)
  {
    f("setAppsBlackList");
    boolean bool1 = true;
    Object localObject = I();
    try
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        if (!paramList.contains(str))
        {
          if (!o.a(str)) {
            a.info("KNOX API ApplicationPolicy.setEnableApplication() returns: {}", Boolean.valueOf(g.setEnableApplication(str)));
          }
          a.info("KNOX API ApplicationPolicy.removeAppPackageNameFromBlackList() returns: {}", Boolean.valueOf(g.removeAppPackageNameFromBlackList(str)));
          continue;
          return bool2;
        }
      }
    }
    catch (SecurityException paramList)
    {
      a.warn("KNOX API SecurityException in setAppsBlackList", paramList);
      bool2 = false;
    }
    do
    {
      paramList = paramList.iterator();
      bool2 = bool1;
    } while (!paramList.hasNext());
    localObject = (String)paramList.next();
    if (!o.a((String)localObject)) {
      a.info("KNOX API ApplicationPolicy.setDisableApplication({}) returns: {}", localObject, Boolean.valueOf(g.setDisableApplication((String)localObject)));
    }
    boolean bool2 = g.addAppPackageNameToBlackList((String)localObject);
    a.info("KNOX API ApplicationPolicy.addAppPackageNameToBlackList({}) returns: {}", localObject, Boolean.valueOf(bool2));
    if (!bool2) {
      bool1 = false;
    }
    for (;;)
    {
      break;
    }
  }
  
  public static boolean a(boolean paramBoolean)
  {
    f("disableAndroidBrowser");
    boolean bool1;
    if (g != null)
    {
      boolean bool2 = true;
      String[] arrayOfString = b;
      int i2 = arrayOfString.length;
      int i1 = 0;
      bool1 = bool2;
      if (i1 < i2)
      {
        String str = arrayOfString[i1];
        bool1 = bool2;
        if (!a(str))
        {
          bool1 = bool2;
          if ((g.getApplicationName(str) != null) && (!paramBoolean)) {
            break label132;
          }
        }
        for (;;)
        {
          try
          {
            bool1 = g.setDisableApplication(str);
            a.info("KNOX API ApplicationPolicy.setDisableApplication({}) returns: {}", str, Boolean.valueOf(bool1));
            bool1 = bool2 & bool1;
          }
          catch (SecurityException localSecurityException1)
          {
            a.warn("KNOX API ApplicationPolicy.setDisableApplication({}) SecurityException", str, localSecurityException1);
            bool1 = false;
            continue;
          }
          i1 += 1;
          bool2 = bool1;
          break;
          try
          {
            label132:
            bool1 = g.setEnableApplication(str);
            a.info("KNOX API ApplicationPolicy.setEnableApplication({}) returns: {}", str, Boolean.valueOf(bool1));
            bool1 = bool2 & bool1;
          }
          catch (SecurityException localSecurityException2)
          {
            a.warn("KNOX API ApplicationPolicy.setEnableApplication({}) SecurityException", str, localSecurityException2);
            bool1 = false;
          }
        }
      }
    }
    else
    {
      bool1 = false;
    }
    return bool1;
  }
  
  public static boolean b()
  {
    f("isDisabledAndroidBrowser");
    if (g != null)
    {
      String[] arrayOfString = b;
      int i2 = arrayOfString.length;
      int i1 = 0;
      while (i1 < i2)
      {
        String str = arrayOfString[i1];
        if ((!a(str)) && (g.getApplicationName(str) != null)) {
          try
          {
            boolean bool = g.getApplicationStateEnabled(str);
            if (bool) {
              return false;
            }
          }
          catch (SecurityException localSecurityException)
          {
            a.warn("KNOX API ApplicationPolicy.getApplicationStateEnabled({}) SecurityException", str, localSecurityException);
            return false;
          }
        }
        i1 += 1;
      }
    }
    return true;
  }
  
  public static boolean b(PasswordPolicy paramPasswordPolicy, boolean paramBoolean)
  {
    int i1 = 2;
    for (;;)
    {
      boolean bool2;
      int i2;
      try
      {
        boolean bool1 = paramPasswordPolicy.isBiometricAuthenticationEnabled(1);
        bool2 = paramPasswordPolicy.isBiometricAuthenticationEnabled(4);
        if (paramBoolean)
        {
          if (bool1)
          {
            i1 = 3;
            break label87;
            bool1 = paramPasswordPolicy.setBiometricAuthenticationEnabled(i2, paramBoolean);
            a.info("KNOX API PasswordPolicy.setBiometricAuthenticationEnabled(BIOMETRIC_AUTHENTICATION_IRIS, {}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
            return bool1;
          }
        }
        else
        {
          bool1 = paramPasswordPolicy.setBiometricAuthenticationEnabled(2, paramBoolean);
          continue;
        }
        i2 = i1;
      }
      catch (SecurityException paramPasswordPolicy)
      {
        a.warn("KNOX API PasswordPolicy.setBiometricAuthenticationEnabled SecurityException", paramPasswordPolicy);
        return false;
      }
      label87:
      if (bool2) {
        i2 = i1 | 0x4;
      }
    }
  }
  
  private static boolean b(String paramString)
  {
    if (c == null)
    {
      EnterpriseDeviceManager localEnterpriseDeviceManager = EnterpriseDeviceManager.getInstance(g.a());
      c = localEnterpriseDeviceManager;
      if (localEnterpriseDeviceManager == null) {
        a.warn("{}, EnterpriseDeviceManager = null", paramString);
      }
    }
    return c != null;
  }
  
  public static boolean b(List<String> paramList)
  {
    f("setAppsWhiteList");
    try
    {
      Iterator localIterator = J().iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (String)localIterator.next();
        a.info("KNOX API ApplicationPolicy.removeAppPackageNameFromWhiteList({}) returns: {}", localObject, Boolean.valueOf(g.removeAppPackageNameFromWhiteList((String)localObject)));
      }
      String str;
      int i3;
      int i1;
      int i2;
      return true;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API ApplicationPolicy.removeAppPackageNameFromWhiteList() SecurityException", localSecurityException);
      try
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          str = (String)paramList.next();
          a.info("KNOX API appPolicy.addAppPackageNameToWhiteList({}) returns: {}", str, Boolean.valueOf(g.addAppPackageNameToWhiteList(str)));
        }
        f("enableWhiteListedApps");
      }
      catch (SecurityException paramList)
      {
        a.warn("SecurityException in setAppWhitelist", paramList);
        return false;
      }
      paramList = g.getInstalledApplicationsIDList();
      i3 = paramList.length;
      i1 = 0;
      if (i1 < i3)
      {
        str = paramList[i1];
        if (!g.getApplicationStateEnabled(str))
        {
          localObject = J().iterator();
          do
          {
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
          } while (!Pattern.compile((String)((Iterator)localObject).next()).matcher(str).matches());
        }
        for (i2 = 1;; i2 = 0)
        {
          if (i2 != 0) {
            a.info("KNOX API ApplicationPolicy.setEnableApplication(" + str + ") returns: {} ", Boolean.valueOf(g.setEnableApplication(str)));
          }
          i1 += 1;
          break;
        }
      }
    }
  }
  
  public static boolean b(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    g("disableEmailAccountCreation");
    if (i != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = i.allowAccountAddition(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API EmailPolicy.allowAccountAddition({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API APIEmailPolicy.allowAccountAddition({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean c()
  {
    boolean bool2 = false;
    g("isDisabledEmailAccountCreation");
    boolean bool1 = bool2;
    if (i != null) {}
    try
    {
      boolean bool3 = i.isAccountAdditionAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API APIEmailPolicy.isAccountAdditionAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean c(PasswordPolicy paramPasswordPolicy, boolean paramBoolean)
  {
    int i1 = 4;
    for (;;)
    {
      boolean bool2;
      int i2;
      try
      {
        boolean bool1 = paramPasswordPolicy.isBiometricAuthenticationEnabled(1);
        bool2 = paramPasswordPolicy.isBiometricAuthenticationEnabled(2);
        if (paramBoolean)
        {
          if (bool1)
          {
            i1 = 5;
            break label87;
            bool1 = paramPasswordPolicy.setBiometricAuthenticationEnabled(i2, paramBoolean);
            a.info("KNOX API PasswordPolicy.setBiometricAuthenticationEnabled(BIOMETRIC_AUTHENTICATION_FACE, {}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
            return bool1;
          }
        }
        else
        {
          bool1 = paramPasswordPolicy.setBiometricAuthenticationEnabled(4, paramBoolean);
          continue;
        }
        i2 = i1;
      }
      catch (SecurityException paramPasswordPolicy)
      {
        a.warn("KNOX API PasswordPolicy.enableFaceUnlock SecurityException", paramPasswordPolicy);
        return false;
      }
      label87:
      if (bool2) {
        i2 = i1 | 0x2;
      }
    }
  }
  
  private static boolean c(String paramString)
  {
    if (d == null)
    {
      d = EnterpriseKnoxManager.getInstance(g.a());
      if (c == null) {
        a.warn("{}, EnterpriseKnoxManager = null", paramString);
      }
    }
    return d != null;
  }
  
  public static boolean c(List<String> paramList)
  {
    boolean bool2 = false;
    m("setWifiSsidsBlackList");
    if (o != null) {
      for (;;)
      {
        try
        {
          a.info("KNOX API WifiPolicy.clearWifiSsidsFromWhiteList() returns: {}", Boolean.valueOf(o.clearWifiSsidsFromWhiteList()));
          if (paramList.isEmpty()) {}
        }
        catch (SecurityException localSecurityException1)
        {
          try
          {
            bool1 = o.addWifiSsidsToBlackList(paramList);
          }
          catch (SecurityException localSecurityException2)
          {
            Logger localLogger;
            WifiPolicy localWifiPolicy;
            bool1 = false;
          }
          try
          {
            a.info("KNOX API WifiPolicy.addWifiSsidsToBlackList({}) returned returns: {}", paramList, Boolean.valueOf(bool1));
          }
          catch (SecurityException localSecurityException3)
          {
            continue;
          }
          try
          {
            localLogger = a;
            localWifiPolicy = o;
            if (!paramList.isEmpty()) {
              bool2 = true;
            }
            localLogger.info("KNOX API WifiPolicy.activateWifiSsidRestriction() returns: {}", Boolean.valueOf(localWifiPolicy.activateWifiSsidRestriction(bool2)));
            return bool1;
          }
          catch (SecurityException paramList)
          {
            a.warn("KNOX API WifiPolicy.activateWifiSsidRestriction() SecurityException", paramList);
            return bool1;
          }
          localSecurityException1 = localSecurityException1;
          a.warn("KNOX API WifiPolicy.clearWifiSsidsFromWhiteList() SecurityException", localSecurityException1);
          continue;
          a.warn("KNOX API WifiPolicy.addWifiSsidsToBlackList({}) SecurityException", paramList, localSecurityException2);
          continue;
        }
        boolean bool1 = false;
      }
    }
    return false;
  }
  
  public static boolean c(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableGoogleBackup");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.setBackup(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.setBackup({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.setBackup({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean d()
  {
    boolean bool2 = false;
    d("isDisabledGoogleBackup");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isBackupAllowed(false);
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isBackupAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean d(String paramString)
  {
    if ((e == null) && (b(paramString)))
    {
      RestrictionPolicy localRestrictionPolicy = c.getRestrictionPolicy();
      e = localRestrictionPolicy;
      if (localRestrictionPolicy == null) {
        a.warn("{}, RestrictionPolicy = null", paramString);
      }
    }
    return e != null;
  }
  
  /* Error */
  public static boolean d(List<String> paramList)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: ldc_w 645
    //   5: invokestatic 301	com/mobileiron/acom/mdm/knox/a/a:m	(Ljava/lang/String;)Z
    //   8: pop
    //   9: getstatic 303	com/mobileiron/acom/mdm/knox/a/a:o	Lcom/samsung/android/knox/net/wifi/WifiPolicy;
    //   12: ifnull +222 -> 234
    //   15: getstatic 49	com/mobileiron/acom/mdm/knox/a/a:a	Lorg/slf4j/Logger;
    //   18: ldc_w 599
    //   21: getstatic 303	com/mobileiron/acom/mdm/knox/a/a:o	Lcom/samsung/android/knox/net/wifi/WifiPolicy;
    //   24: invokevirtual 602	com/samsung/android/knox/net/wifi/WifiPolicy:clearWifiSsidsFromWhiteList	()Z
    //   27: invokestatic 140	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   30: invokeinterface 478 3 0
    //   35: aload_0
    //   36: invokeinterface 605 1 0
    //   41: ifne +168 -> 209
    //   44: getstatic 303	com/mobileiron/acom/mdm/knox/a/a:o	Lcom/samsung/android/knox/net/wifi/WifiPolicy;
    //   47: aload_0
    //   48: invokevirtual 648	com/samsung/android/knox/net/wifi/WifiPolicy:addWifiSsidsToWhiteList	(Ljava/util/List;)Z
    //   51: istore_2
    //   52: getstatic 49	com/mobileiron/acom/mdm/knox/a/a:a	Lorg/slf4j/Logger;
    //   55: ldc_w 650
    //   58: aload_0
    //   59: iload_2
    //   60: invokestatic 140	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   63: invokeinterface 143 4 0
    //   68: getstatic 303	com/mobileiron/acom/mdm/knox/a/a:o	Lcom/samsung/android/knox/net/wifi/WifiPolicy;
    //   71: invokevirtual 308	com/samsung/android/knox/net/wifi/WifiPolicy:getWifiSsidsFromBlackLists	()Ljava/util/List;
    //   74: astore_0
    //   75: aload_0
    //   76: ifnull +110 -> 186
    //   79: aload_0
    //   80: invokeinterface 461 1 0
    //   85: astore_0
    //   86: iconst_0
    //   87: istore_1
    //   88: iload_1
    //   89: istore_3
    //   90: aload_0
    //   91: invokeinterface 466 1 0
    //   96: ifeq +92 -> 188
    //   99: aload_0
    //   100: invokeinterface 470 1 0
    //   105: checkcast 652	com/samsung/android/knox/net/wifi/WifiControlInfo
    //   108: astore 4
    //   110: invokestatic 515	com/mobileiron/acom/core/android/g:a	()Landroid/content/Context;
    //   113: invokevirtual 657	android/content/Context:getPackageName	()Ljava/lang/String;
    //   116: aload 4
    //   118: getfield 661	com/samsung/android/knox/net/wifi/WifiControlInfo:adminPackageName	Ljava/lang/String;
    //   121: invokevirtual 448	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +107 -> 231
    //   127: aload 4
    //   129: getfield 665	com/samsung/android/knox/net/wifi/WifiControlInfo:entries	Ljava/util/List;
    //   132: invokeinterface 605 1 0
    //   137: istore_3
    //   138: iload_3
    //   139: ifne +92 -> 231
    //   142: iconst_1
    //   143: istore_1
    //   144: goto -56 -> 88
    //   147: astore 4
    //   149: getstatic 49	com/mobileiron/acom/mdm/knox/a/a:a	Lorg/slf4j/Logger;
    //   152: ldc_w 617
    //   155: aload 4
    //   157: invokeinterface 151 3 0
    //   162: goto -127 -> 35
    //   165: astore 4
    //   167: iconst_0
    //   168: istore_2
    //   169: getstatic 49	com/mobileiron/acom/mdm/knox/a/a:a	Lorg/slf4j/Logger;
    //   172: ldc_w 667
    //   175: aload_0
    //   176: aload 4
    //   178: invokeinterface 214 4 0
    //   183: goto -115 -> 68
    //   186: iconst_0
    //   187: istore_3
    //   188: getstatic 49	com/mobileiron/acom/mdm/knox/a/a:a	Lorg/slf4j/Logger;
    //   191: ldc_w 612
    //   194: getstatic 303	com/mobileiron/acom/mdm/knox/a/a:o	Lcom/samsung/android/knox/net/wifi/WifiPolicy;
    //   197: iload_3
    //   198: invokevirtual 615	com/samsung/android/knox/net/wifi/WifiPolicy:activateWifiSsidRestriction	(Z)Z
    //   201: invokestatic 140	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   204: invokeinterface 478 3 0
    //   209: iload_2
    //   210: ireturn
    //   211: astore_0
    //   212: getstatic 49	com/mobileiron/acom/mdm/knox/a/a:a	Lorg/slf4j/Logger;
    //   215: ldc_w 621
    //   218: aload_0
    //   219: invokeinterface 151 3 0
    //   224: iload_2
    //   225: ireturn
    //   226: astore 4
    //   228: goto -59 -> 169
    //   231: goto -87 -> 144
    //   234: iconst_0
    //   235: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	236	0	paramList	List<String>
    //   87	57	1	bool1	boolean
    //   1	224	2	bool2	boolean
    //   89	109	3	bool3	boolean
    //   108	20	4	localWifiControlInfo	WifiControlInfo
    //   147	9	4	localSecurityException1	SecurityException
    //   165	12	4	localSecurityException2	SecurityException
    //   226	1	4	localSecurityException3	SecurityException
    // Exception table:
    //   from	to	target	type
    //   15	35	147	java/lang/SecurityException
    //   44	52	165	java/lang/SecurityException
    //   68	75	211	java/lang/SecurityException
    //   79	86	211	java/lang/SecurityException
    //   90	138	211	java/lang/SecurityException
    //   188	209	211	java/lang/SecurityException
    //   52	68	226	java/lang/SecurityException
  }
  
  public static boolean d(boolean paramBoolean)
  {
    f("disableAndroidMarket");
    if (g != null)
    {
      if (paramBoolean) {
        try
        {
          g.disableAndroidMarket();
          a.info("KNOX API ApplicationPolicy.disableAndroidMarket()");
          return true;
        }
        catch (SecurityException localSecurityException1)
        {
          a.warn("KNOX API ApplicationPolicy.disableAndroidMarket SecurityException", localSecurityException1);
          return false;
        }
      }
      try
      {
        g.enableAndroidMarket();
        a.info("KNOX API ApplicationPolicy.enableAndroidMarket()");
        return true;
      }
      catch (SecurityException localSecurityException2)
      {
        a.warn("KNOX API ApplicationPolicy.enableAndroidMarket() SecurityException", localSecurityException2);
      }
    }
    return false;
  }
  
  private static List<String> e(List<AppControlInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList == null) {
      return localArrayList;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Iterator localIterator = ((AppControlInfo)paramList.next()).entries.iterator();
      while (localIterator.hasNext()) {
        localArrayList.add((String)localIterator.next());
      }
    }
    return localArrayList;
  }
  
  public static boolean e()
  {
    boolean bool2 = false;
    f("isDisabledAndroidMarket");
    boolean bool1 = bool2;
    if (g != null) {}
    try
    {
      boolean bool3 = g.getApplicationStateEnabled("com.android.vending");
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API ApplicationPolicy.getApplicationStateEnabled({}) SecurityException", "com.android.vending", localSecurityException);
    }
    return false;
  }
  
  private static boolean e(String paramString)
  {
    if ((f == null) && (b(paramString)))
    {
      PasswordPolicy localPasswordPolicy = c.getPasswordPolicy();
      f = localPasswordPolicy;
      if (localPasswordPolicy == null) {
        a.warn("{}, PasswordPolicy = null", paramString);
      }
    }
    return f != null;
  }
  
  public static boolean e(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    h("disableIncomingSms");
    if (j != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = j.allowIncomingSms(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API PhoneRestrictionPolicy.allowIncomingSms({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API PhoneRestrictionPolicy.allowIncomingSms({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  private static List<String> f(List<WifiControlInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (WifiControlInfo)paramList.next();
        if (g.a().getPackageName().equals(((WifiControlInfo)localObject).adminPackageName))
        {
          localObject = ((WifiControlInfo)localObject).entries.iterator();
          while (((Iterator)localObject).hasNext()) {
            localArrayList.add((String)((Iterator)localObject).next());
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean f()
  {
    boolean bool2 = false;
    h("isDisabledIncomingSms");
    boolean bool1 = bool2;
    if (j != null) {}
    try
    {
      boolean bool3 = j.isIncomingSmsAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API PhoneRestrictionPolicy.isIncomingSmsAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean f(String paramString)
  {
    if ((g == null) && (b(paramString)))
    {
      ApplicationPolicy localApplicationPolicy = c.getApplicationPolicy();
      g = localApplicationPolicy;
      if (localApplicationPolicy == null) {
        a.warn("{}, ApplicationPolicy = null", paramString);
      }
    }
    return g != null;
  }
  
  public static boolean f(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    h("disableOutgoingSms");
    if (j != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = j.allowOutgoingSms(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API PhoneRestrictionPolicy.allowOutgoingSms({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API PhoneRestrictionPolicy.allowOutgoingSms({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean g()
  {
    boolean bool2 = false;
    h("isDisabledOutgoingSms");
    boolean bool1 = bool2;
    if (j != null) {}
    try
    {
      boolean bool3 = j.isOutgoingSmsAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API PhoneRestrictionPolicy.isOutgoingSmsAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean g(String paramString)
  {
    if ((i == null) && (b(paramString)))
    {
      EmailPolicy localEmailPolicy = c.getEmailPolicy();
      i = localEmailPolicy;
      if (localEmailPolicy == null) {
        a.warn("{}, emailPolicy = null", paramString);
      }
    }
    return i != null;
  }
  
  public static boolean g(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    h("disableIncomingMms");
    if (j != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = j.allowIncomingMms(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API PhoneRestrictionPolicy.allowIncomingMms({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API PhoneRestrictionPolicy.allowIncomingMms({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean h()
  {
    boolean bool2 = false;
    h("isDisabledIncomingMms");
    boolean bool1 = bool2;
    if (j != null) {}
    try
    {
      boolean bool3 = j.isIncomingMmsAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API PhoneRestrictionPolicy.isIncomingMmsAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean h(String paramString)
  {
    if ((j == null) && (b(paramString)))
    {
      PhoneRestrictionPolicy localPhoneRestrictionPolicy = c.getPhoneRestrictionPolicy();
      j = localPhoneRestrictionPolicy;
      if (localPhoneRestrictionPolicy == null) {
        a.warn("{}, phoneRestrictionPolicy = null", paramString);
      }
    }
    return j != null;
  }
  
  public static boolean h(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    h("disableOutgoingMms");
    if (j != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = j.allowOutgoingMms(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API PhoneRestrictionPolicy.allowOutgoingMms({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API PhoneRestrictionPolicy.allowOutgoingMms({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException.getMessage());
      }
    }
    return bool2;
  }
  
  public static boolean i()
  {
    boolean bool2 = false;
    h("isDisabledOutgoingMms");
    boolean bool1 = bool2;
    if (j != null) {}
    try
    {
      boolean bool3 = j.isOutgoingMmsAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API PhoneRestrictionPolicy.isOutgoingMmsAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean i(String paramString)
  {
    if ((k == null) && (b(paramString)))
    {
      RoamingPolicy localRoamingPolicy = c.getRoamingPolicy();
      k = localRoamingPolicy;
      if (localRoamingPolicy == null) {
        a.warn("{}, roamingPolicy = null", paramString);
      }
    }
    return k != null;
  }
  
  public static boolean i(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    e("disablePasswordVisibility");
    if (f != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = f.setPasswordVisibilityEnabled(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API PasswordPolicy.setPasswordVisibilityEnabled({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API PasswordPolicy.setPasswordVisibilityEnabled SecurityException", localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean j()
  {
    boolean bool2 = false;
    e("isDisabledPasswordVisibility");
    boolean bool1 = bool2;
    if (f != null) {}
    try
    {
      boolean bool3 = f.isPasswordVisibilityEnabled();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API PasswordPolicy.isPasswordVisibilityEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean j(String paramString)
  {
    if ((l == null) && (b(paramString)))
    {
      DateTimePolicy localDateTimePolicy = c.getDateTimePolicy();
      l = localDateTimePolicy;
      if (localDateTimePolicy == null) {
        a.warn("{}, dateTimePolicy = null", paramString);
      }
    }
    return l != null;
  }
  
  public static boolean j(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableDeveloperOptions");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowDeveloperMode(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowDeveloperMode({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowDeveloperMode({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean k()
  {
    boolean bool2 = false;
    d("isDisabledDeveloperOptions");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isDeveloperModeAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isDeveloperModeAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean k(String paramString)
  {
    if ((m == null) && (c(paramString)))
    {
      CertificatePolicy localCertificatePolicy = d.getCertificatePolicy();
      m = localCertificatePolicy;
      if (localCertificatePolicy == null) {
        a.warn("{}, certificatePolicy = null", paramString);
      }
    }
    return m != null;
  }
  
  public static boolean k(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableOtaUpgrade");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowOTAUpgrade(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowOTAUpgrade({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowOTAUpgrade({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean l()
  {
    boolean bool2 = false;
    d("isDisabledOtaUpgrade");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isOTAUpgradeAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isOTAUpgradeAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean l(String paramString)
  {
    if ((n == null) && (b(paramString)))
    {
      MultiUserManager localMultiUserManager = c.getMultiUserManager();
      n = localMultiUserManager;
      if (localMultiUserManager == null) {
        a.warn("{}, multiUserManager = null", paramString);
      }
    }
    return n != null;
  }
  
  public static boolean l(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableRecoveryMode");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowFirmwareRecovery(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowFirmwareRecovery({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowFirmwareRecovery({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean m()
  {
    boolean bool2 = false;
    d("isDisabledRecoveryMode");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isFirmwareRecoveryAllowed(false);
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isFirmwareRecoveryAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  private static boolean m(String paramString)
  {
    if ((o == null) && (b(paramString)))
    {
      WifiPolicy localWifiPolicy = c.getWifiPolicy();
      o = localWifiPolicy;
      if (localWifiPolicy == null) {
        a.warn("{}, wifiPolicy = null", paramString);
      }
    }
    return o != null;
  }
  
  public static boolean m(boolean paramBoolean)
  {
    boolean bool = false;
    i("disableRoamingVoiceCalls");
    if (k != null) {
      if (paramBoolean) {
        break label47;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        k.setRoamingVoiceCalls(paramBoolean);
        a.info("KNOX API RoamingPolicy.setRoamingVoiceCalls({})", Boolean.valueOf(paramBoolean));
        bool = true;
        return bool;
      }
      catch (SecurityException localSecurityException)
      {
        label47:
        a.warn("KNOX API RoamingPolicy.setRoamingVoiceCalls({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return false;
  }
  
  public static boolean n()
  {
    boolean bool2 = false;
    i("isDisabledRoamingVoiceCalls");
    boolean bool1 = bool2;
    if (k != null) {}
    try
    {
      boolean bool3 = k.isRoamingVoiceCallsEnabled();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RoamingPolicy.isRoamingVoiceCallsEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean n(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableSafeMode");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowSafeMode(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowSafeMode({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowSafeMode({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean o()
  {
    boolean bool2 = false;
    d("isDisabledSafeMode");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isSafeModeAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isSafeModeAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean o(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableSettingChanges");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowSettingsChanges(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowSettingsChanges({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowSettingsChanges({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean p()
  {
    boolean bool2 = false;
    d("isDisabledSettingChanges");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isSettingsChangesAllowed(false);
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isSettingsChangesAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean p(boolean paramBoolean)
  {
    boolean bool1 = true;
    boolean bool3 = false;
    d("disableBluetoothTethering");
    if ((p == null) && (b("disableBluetoothTethering")))
    {
      BluetoothPolicy localBluetoothPolicy = c.getBluetoothPolicy();
      p = localBluetoothPolicy;
      if (localBluetoothPolicy == null) {
        a.warn("{}, bluetoothPolicy = null", "disableBluetoothTethering");
      }
    }
    if (p != null) {}
    boolean bool2 = bool3;
    if (e != null)
    {
      bool2 = bool3;
      if (p != null)
      {
        if (paramBoolean) {
          break label177;
        }
        paramBoolean = true;
        if ((p.isBluetoothUUIDRestrictionActive()) && (!paramBoolean)) {
          break label202;
        }
      }
    }
    for (;;)
    {
      try
      {
        bool2 = p.addBluetoothUUIDsToWhiteList(Arrays.asList(new String[] { "00001115-0000-1000-8000-00805F9B34FB", "00001116-0000-1000-8000-00805F9B34FB" }));
        a.info("KNOX API BluetoothPolicy.addBluetoothUUIDsToWhiteList(PANU_UUID,NAP_UUID) returns: {}", Boolean.valueOf(bool2));
        bool1 = bool2 & true;
      }
      catch (SecurityException localSecurityException1)
      {
        label177:
        a.warn("KNOX API BluetoothPolicy.addBluetoothUUIDsToWhiteList(PANU_UUID,NAP_UUID) SecurityException", localSecurityException1);
        bool1 = false;
        continue;
      }
      try
      {
        bool2 = e.setBluetoothTethering(paramBoolean);
        a.info("KNOX API RestrictionPolicy.setBluetoothTethering({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool2));
        bool2 = bool1 & bool2;
        return bool2;
      }
      catch (SecurityException localSecurityException3)
      {
        a.warn("KNOX API RestrictionPolicy.setBluetoothTethering({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException3);
      }
      paramBoolean = false;
      break;
      try
      {
        label202:
        bool2 = p.removeBluetoothUUIDsFromWhiteList(Arrays.asList(new String[] { "00001115-0000-1000-8000-00805F9B34FB", "00001116-0000-1000-8000-00805F9B34FB" }));
        a.info("KNOX API BluetoothPolicy.removeBluetoothUUIDsFromWhiteList(PANU_UUID,NAP_UUID) returns: {}", Boolean.valueOf(bool2));
        bool1 = bool2 & true;
      }
      catch (SecurityException localSecurityException2)
      {
        a.warn("KNOX API BluetoothPolicy.removeBluetoothUUIDsFromWhiteList(PANU_UUID,NAP_UUID) SecurityException", localSecurityException2);
        bool1 = false;
      }
    }
    return false;
  }
  
  public static boolean q()
  {
    boolean bool2 = false;
    d("isDisabledBluetoothTethering");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isBluetoothTetheringEnabled();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isBluetoothTetheringEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean q(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableUsbTethering");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.setUsbTethering(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.setUsbTethering({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.setUsbTethering({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean r()
  {
    boolean bool2 = false;
    d("isDisabledUsbTethering");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isUsbTetheringEnabled();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isUsbTetheringEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean r(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableWifiTethering");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.setWifiTethering(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.setWifiTethering({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.setWifiTethering({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean s()
  {
    boolean bool2 = false;
    d("isDisabledWifiTethering");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isWifiTetheringEnabled();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isWifiTetheringEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean s(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableUsbMediaPlayer");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.setUsbMediaPlayerAvailability(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.setUsbMediaPlayerAvailability({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.setUsbMediaPlayerAvailability({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean t()
  {
    boolean bool2 = false;
    d("isDisabledUsbMediaPlayer");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isUsbMediaPlayerAvailable(false);
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isUsbMediaPlayerAvailable() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean t(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    j("disableManualDateChanging");
    if (l != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = l.setDateTimeChangeEnabled(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API DateTimePolicy.setDateTimeChangeEnabled({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API DateTimePolicy.setDateTimeChangeEnabled({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean u()
  {
    boolean bool2 = false;
    j("isDisabledManualDateChanging");
    boolean bool1 = bool2;
    if (l != null) {}
    try
    {
      boolean bool3 = l.isDateTimeChangeEnabled();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API DateTimePolicy.isDateTimeChangeEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean u(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    k("disableCrlCheck");
    if (m != null) {
      if (paramBoolean) {
        break label57;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = m.enableRevocationCheck("*", paramBoolean);
        bool2 = bool1;
        a.info("KNOX API CertificatePolicy.enableRevocationCheck({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label57:
        a.warn("KNOX API CertificatePolicy.enableRevocationCheck({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean v()
  {
    return M();
  }
  
  public static boolean v(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableGoogleCrashReport");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowGoogleCrashReport(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowGoogleCrashReport({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowGoogleCrashReport({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean w()
  {
    boolean bool2 = false;
    k("isDisabledCrlCheck");
    boolean bool1 = bool2;
    if (m != null) {}
    try
    {
      boolean bool3 = m.isRevocationCheckEnabled("*");
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API CertificatePolicy.isRevocationCheckEnabled() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean w(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableGoogleAccountsAutosync");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.allowGoogleAccountsAutoSync(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.allowGoogleAccountsAutoSync({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.allowGoogleAccountsAutoSync({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean x()
  {
    boolean bool2 = false;
    d("isDisabledGoogleCrashReport");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isGoogleCrashReportAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isGoogleCrashReportAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean x(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    l("disableMultiUserMode");
    if (n != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = n.allowMultipleUsers(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API MultiUserManager.allowMultipleUsers({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API MultiUserManager.allowMultipleUsers({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
  
  public static boolean y()
  {
    boolean bool2 = false;
    d("isDisabledGoogleAccountsAutosync");
    boolean bool1 = bool2;
    if (e != null) {}
    try
    {
      boolean bool3 = e.isGoogleAccountsAutoSyncAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API RestrictionPolicy.isGoogleAccountsAutoSyncAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean y(boolean paramBoolean)
  {
    f("disableNewAdminInstallation");
    boolean bool1 = false;
    boolean bool2 = false;
    if (g != null) {
      bool2 = bool1;
    }
    try
    {
      bool1 = g.preventNewAdminInstallation(paramBoolean);
      bool2 = bool1;
      a.info("KNOX API ApplicationPolicy.preventNewAdminInstallation({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
      bool2 = bool1;
      if (!bool1)
      {
        bool2 = bool1;
        a.info("preventNewAdminInstallation failed - some device admins were already installed");
        bool2 = bool1;
      }
      return bool2;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API ApplicationPolicy.preventNewAdminInstallation({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
    }
    return bool2;
  }
  
  public static boolean z()
  {
    boolean bool2 = false;
    l("isDisabledGoogleAccountsAutosync");
    boolean bool1 = bool2;
    if (n != null) {}
    try
    {
      boolean bool3 = n.multipleUsersAllowed();
      bool1 = bool2;
      if (!bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (SecurityException localSecurityException)
    {
      a.warn("KNOX API MultiUserManager.multipleUsersAllowed() SecurityException", localSecurityException);
    }
    return false;
  }
  
  public static boolean z(boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    d("disableCellularData");
    if (e != null) {
      if (paramBoolean) {
        break label54;
      }
    }
    for (paramBoolean = true;; paramBoolean = false) {
      try
      {
        bool1 = e.setCellularData(paramBoolean);
        bool2 = bool1;
        a.info("KNOX API RestrictionPolicy.setCellularData({}) returns: {}", Boolean.valueOf(paramBoolean), Boolean.valueOf(bool1));
        return bool1;
      }
      catch (SecurityException localSecurityException)
      {
        label54:
        a.warn("KNOX API RestrictionPolicy.setCellularData({}) SecurityException", Boolean.valueOf(paramBoolean), localSecurityException);
      }
    }
    return bool2;
  }
}
