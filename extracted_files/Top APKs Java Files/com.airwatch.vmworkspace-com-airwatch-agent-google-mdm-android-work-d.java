package com.airwatch.agent.google.mdm.android.work;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Pair;
import com.airwatch.agent.e.c;
import com.airwatch.agent.profile.b;
import com.airwatch.agent.utility.i;
import com.airwatch.util.k;
import com.aw.repackage.org.apache.commons.codec.binary.Hex;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TargetApi(21)
public class d
  extends c
{
  public d(ComponentName paramComponentName, Context paramContext)
  {
    super(paramComponentName, new f(paramContext), paramContext);
  }
  
  static String a(X509Certificate paramX509Certificate)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramX509Certificate.getEncoded());
      return new String(Hex.encodeHex(localMessageDigest.digest())).toUpperCase();
    }
    catch (NoSuchAlgorithmException paramX509Certificate)
    {
      throw new RuntimeException("There is no SHA-1 algorithm!!!! HUH????");
    }
  }
  
  private void b(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("AndroidWorkDeviceAdmin: Thumbprint cannot be null");
    }
    paramString = paramString.toUpperCase();
    k.a("AndroidWorkDeviceAdmin", "remove CA cert " + paramString);
    DevicePolicyManager localDevicePolicyManager = j();
    Object localObject = localDevicePolicyManager.getInstalledCaCerts(this.a);
    CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      byte[] arrayOfByte = (byte[])((Iterator)localObject).next();
      String str = a((X509Certificate)localCertificateFactory.generateCertificate(new ByteArrayInputStream(arrayOfByte)));
      if (paramString.equals(str))
      {
        localDevicePolicyManager.uninstallCaCert(this.a, arrayOfByte);
        k.b("AndroidWorkDeviceAdmin", "removed existing CA cert " + str);
      }
    }
  }
  
  private boolean b(byte[] paramArrayOfByte, char[] paramArrayOfChar, String paramString)
  {
    DevicePolicyManager localDevicePolicyManager = j();
    KeyStore localKeyStore = KeyStore.getInstance("PKCS12");
    localKeyStore.load(new ByteArrayInputStream(paramArrayOfByte), paramArrayOfChar);
    paramArrayOfByte = localKeyStore.aliases();
    paramArrayOfChar = new KeyStore.PasswordProtection(paramArrayOfChar);
    while (paramArrayOfByte.hasMoreElements())
    {
      String str = (String)paramArrayOfByte.nextElement();
      if (localKeyStore.entryInstanceOf(str, KeyStore.PrivateKeyEntry.class))
      {
        paramArrayOfByte = (KeyStore.PrivateKeyEntry)localKeyStore.getEntry(str, paramArrayOfChar);
        return localDevicePolicyManager.installKeyPair(this.a, paramArrayOfByte.getPrivateKey(), paramArrayOfByte.getCertificate(), paramString);
      }
    }
    return false;
  }
  
  private DevicePolicyManager j()
  {
    return (DevicePolicyManager)this.c.getSystemService("device_policy");
  }
  
  public Bundle a(String paramString)
  {
    return j().getApplicationRestrictions(this.a, paramString);
  }
  
  public List<Pair<PackageInfo, ApplicationInfo>> a(Pattern paramPattern)
  {
    PackageManager localPackageManager = this.c.getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(8193);
    LinkedList localLinkedList = new LinkedList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.activities != null) && (localPackageInfo.activities.length != 0) && (paramPattern.matcher(localPackageInfo.packageName).find()))
      {
        k.c("AndroidWorkDeviceAdmin", "system package=" + localPackageInfo.packageName);
        try
        {
          ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 8192);
          if ((localApplicationInfo.flags & 0x1) != 0)
          {
            k.a("AndroidWorkDeviceAdmin", "system package=" + localPackageInfo.packageName);
            localLinkedList.add(new Pair(localPackageInfo, localApplicationInfo));
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          k.d("AndroidWorkDeviceAdmin", "could not find package via name returned from package manager?", localNameNotFoundException);
        }
      }
    }
    return localLinkedList;
  }
  
  public void a(String paramString, Bundle paramBundle)
  {
    j().setApplicationRestrictions(this.a, paramString, paramBundle);
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    if (paramString2 != null) {}
    try
    {
      if (paramString2.length() == 0)
      {
        b(paramString3);
        return;
      }
      k.e("AndroidWorkDeviceAdmin", "removing key/pair certs is not supported");
      return;
    }
    catch (Exception paramString2)
    {
      k.d("AndroidWorkDeviceAdmin", "could not remove cert " + paramString1, paramString2);
    }
  }
  
  public boolean a()
  {
    boolean bool = false;
    if (!i()) {
      bool = super.a();
    }
    return bool;
  }
  
  public boolean a(String paramString1, String paramString2)
  {
    try
    {
      j().setGlobalSetting(this.a, paramString1, paramString2);
      k.a("AndroidWorkDeviceAdmin", "successfully set global setting " + paramString1 + " = " + paramString2);
      return true;
    }
    catch (SecurityException paramString1)
    {
      k.d("applying global settings exception :- ", paramString1);
    }
    return false;
  }
  
  public boolean a(String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      a(0, 0, 16, -1L);
      paramBoolean = this.b.a(paramString, 1).booleanValue();
      com.airwatch.agent.d.c().E(true);
      b.a().a(i.a()).q();
      return paramBoolean;
    }
    return this.b.a(paramString, 0).booleanValue();
  }
  
  public boolean a(byte[] paramArrayOfByte, char[] paramArrayOfChar, String paramString)
  {
    if (paramArrayOfChar != null) {}
    try
    {
      if (paramArrayOfChar.length == 0) {
        return j().installCaCert(this.a, paramArrayOfByte);
      }
      boolean bool = b(paramArrayOfByte, paramArrayOfChar, paramString);
      return bool;
    }
    catch (Exception paramArrayOfByte)
    {
      k.d("AndroidWorkDeviceAdmin", "could not install cert", paramArrayOfByte);
    }
    return false;
  }
  
  public void b(String paramString, boolean paramBoolean)
  {
    j().setAccountManagementDisabled(this.a, paramString, paramBoolean);
  }
  
  public void b(boolean paramBoolean)
  {
    j().setScreenCaptureDisabled(this.a, paramBoolean);
  }
  
  public boolean b(String paramString1, String paramString2)
  {
    try
    {
      j().setSecureSetting(this.a, paramString1, paramString2);
      k.a("AndroidWorkDeviceAdmin", "successfully set secure setting " + paramString1 + " = " + paramString2);
      return true;
    }
    catch (SecurityException paramString1)
    {
      k.d("applying secure settings exception :- ", paramString1);
    }
    return false;
  }
  
  public boolean c(String paramString, boolean paramBoolean)
  {
    boolean bool1 = true;
    boolean bool2 = true;
    Object localObject = this.c.getPackageManager();
    DevicePolicyManager localDevicePolicyManager = j();
    try
    {
      if ((((PackageManager)localObject).getApplicationInfo(paramString, 8192).flags & 0x800000) == 0)
      {
        if (paramBoolean)
        {
          k.a("AndroidWorkDeviceAdmin", "enabling system app " + paramString);
          localDevicePolicyManager.enableSystemApp(this.a, paramString);
          paramBoolean = bool1;
        }
        else
        {
          k.a("AndroidWorkDeviceAdmin", "app already disabled: " + paramString);
          paramBoolean = false;
        }
      }
      else
      {
        localObject = new StringBuilder().append("setting application hidden ").append(paramString).append("=");
        if (!paramBoolean)
        {
          bool1 = true;
          k.a("AndroidWorkDeviceAdmin", bool1);
          localObject = this.a;
          if (paramBoolean) {
            break label189;
          }
        }
        label189:
        for (paramBoolean = bool2;; paramBoolean = false)
        {
          return localDevicePolicyManager.setApplicationHidden((ComponentName)localObject, paramString, paramBoolean);
          bool1 = false;
          break;
        }
      }
      return paramBoolean;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      k.a("AndroidWorkDeviceAdmin", "app not found: " + paramString);
      return false;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      k.a("AndroidWorkDeviceAdmin", "could not set app enabled: " + paramString + ": " + localIllegalArgumentException.getMessage());
      return false;
    }
  }
  
  public boolean d(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      j().addUserRestriction(this.a, paramString);
      k.a("AndroidWorkDeviceAdmin", "successfully added user restriction " + paramString);
    }
    catch (SecurityException localSecurityException)
    {
      k.e("AndroidWorkDeviceAdmin", "failed to add user restriction " + paramString + " because of security exception: " + localSecurityException.getMessage());
      return false;
    }
    j().clearUserRestriction(this.a, paramString);
    k.a("AndroidWorkDeviceAdmin", "successfully cleared user restriction " + paramString);
    return true;
  }
  
  public boolean h()
  {
    return j().isProfileOwnerApp(this.c.getPackageName());
  }
  
  boolean i()
  {
    return j().isDeviceOwnerApp(this.c.getPackageName());
  }
}
