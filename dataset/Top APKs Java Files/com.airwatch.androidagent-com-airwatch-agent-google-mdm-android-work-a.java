package com.airwatch.agent.google.mdm.android.work;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Pair;
import com.airwatch.agent.AirWatchApp;
import com.airwatch.agent.ad;
import com.airwatch.agent.profile.o;
import com.airwatch.agent.utility.s;
import com.airwatch.bizlib.command.CommandType;
import com.airwatch.util.n;
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

public class a
  extends com.airwatch.agent.f.c
{
  private static final String i = a.class.getSimpleName();
  
  public a(ComponentName paramComponentName)
  {
    super(paramComponentName, new c());
  }
  
  private static String a(X509Certificate paramX509Certificate)
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
  
  public static List a(Pattern paramPattern)
  {
    PackageManager localPackageManager = AirWatchApp.f().getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(8193);
    LinkedList localLinkedList = new LinkedList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.activities != null) && (localPackageInfo.activities.length != 0) && (paramPattern.matcher(localPackageInfo.packageName).find()))
      {
        n.c(i, "system package=" + localPackageInfo.packageName);
        try
        {
          ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 8192);
          if ((localApplicationInfo.flags & 0x1) != 0)
          {
            n.a(i, "system package=" + localPackageInfo.packageName);
            localLinkedList.add(new Pair(localPackageInfo, localApplicationInfo));
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          n.b(i, "could not find package via name returned from package manager?", localNameNotFoundException);
        }
      }
    }
    return localLinkedList;
  }
  
  private static DevicePolicyManager x()
  {
    return (DevicePolicyManager)AirWatchApp.f().getSystemService("device_policy");
  }
  
  public final void a(String paramString1, String paramString2, String paramString3)
  {
    if (paramString2 != null) {}
    for (;;)
    {
      try
      {
        if (paramString2.length() != 0) {
          break;
        }
        if (paramString3 == null) {
          throw new IllegalArgumentException(i + ": Thumbprint cannot be null");
        }
      }
      catch (Exception paramString2)
      {
        n.b(i, "could not remove cert " + paramString1, paramString2);
        return;
      }
      paramString2 = paramString3.toUpperCase();
      n.a(i, "remove CA cert " + paramString2);
      paramString3 = x();
      Object localObject = paramString3.getInstalledCaCerts(this.a);
      CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        byte[] arrayOfByte = (byte[])((Iterator)localObject).next();
        String str = a((X509Certificate)localCertificateFactory.generateCertificate(new ByteArrayInputStream(arrayOfByte)));
        if (paramString2.equals(str))
        {
          paramString3.uninstallCaCert(this.a, arrayOfByte);
          n.b(i, "removed existing CA cert " + str);
        }
      }
    }
    n.e(i, "removing key/pair certs is not supported");
  }
  
  public final boolean a(int paramInt)
  {
    n.a(i, "wipeDevice(" + paramInt + ")");
    int j = 0;
    if ((paramInt == CommandType.WIPE_ALL.value) || (paramInt == CommandType.WIPE_EXTERNAL_STORAGE.value)) {
      j = 1;
    }
    x().wipeData(j);
    return true;
  }
  
  public final boolean a(String paramString1, String paramString2)
  {
    try
    {
      x().setGlobalSetting(this.a, paramString1, paramString2);
      n.a(i, "successfully set global setting " + paramString1 + " = " + paramString2);
      return true;
    }
    catch (SecurityException paramString1)
    {
      n.d("applying global settings exception :- ", paramString1);
    }
    return false;
  }
  
  public final boolean a(String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      a(0, 0, 16, -1L);
      paramBoolean = this.b.a(paramString, 1).booleanValue();
      ad.c().U(true);
      o.a().a(s.b()).s();
      return paramBoolean;
    }
    return this.b.a(paramString, 0).booleanValue();
  }
  
  public final boolean a(byte[] paramArrayOfByte, char[] paramArrayOfChar, String paramString)
  {
    if (paramArrayOfChar != null) {}
    try
    {
      if (paramArrayOfChar.length == 0) {
        return x().installCaCert(this.a, paramArrayOfByte);
      }
      DevicePolicyManager localDevicePolicyManager = x();
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
          boolean bool = localDevicePolicyManager.installKeyPair(this.a, paramArrayOfByte.getPrivateKey(), paramArrayOfByte.getCertificate(), paramString);
          return bool;
        }
      }
    }
    catch (Exception paramArrayOfByte)
    {
      n.b(i, "could not install cert", paramArrayOfByte);
      return false;
    }
    return false;
  }
  
  public final void b(String paramString, boolean paramBoolean)
  {
    x().setAccountManagementDisabled(this.a, paramString, paramBoolean);
  }
  
  public final boolean b(String paramString1, String paramString2)
  {
    try
    {
      x().setSecureSetting(this.a, paramString1, paramString2);
      n.a(i, "successfully set secure setting " + paramString1 + " = " + paramString2);
      return true;
    }
    catch (SecurityException paramString1)
    {
      n.d("applying secure settings exception :- ", paramString1);
    }
    return false;
  }
  
  public final void c(boolean paramBoolean)
  {
    x().setScreenCaptureDisabled(this.a, paramBoolean);
  }
  
  public final boolean c(String paramString, boolean paramBoolean)
  {
    boolean bool2 = true;
    Object localObject = AirWatchApp.f().getPackageManager();
    DevicePolicyManager localDevicePolicyManager = x();
    try
    {
      if ((((PackageManager)localObject).getApplicationInfo(paramString, 8192).flags & 0x800000) == 0)
      {
        if (paramBoolean)
        {
          n.a(i, "enabling system app " + paramString);
          localDevicePolicyManager.enableSystemApp(this.a, paramString);
          return true;
        }
        n.a(i, "app already disabled: " + paramString);
        return false;
      }
      localObject = i;
      StringBuilder localStringBuilder = new StringBuilder("setting application hidden ").append(paramString).append("=");
      boolean bool1;
      if (!paramBoolean)
      {
        bool1 = true;
        n.a((String)localObject, bool1);
        localObject = this.a;
        if (paramBoolean) {
          break label176;
        }
      }
      label176:
      for (paramBoolean = bool2;; paramBoolean = false)
      {
        paramBoolean = localDevicePolicyManager.setApplicationHidden((ComponentName)localObject, paramString, paramBoolean);
        return paramBoolean;
        bool1 = false;
        break;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      n.a(i, "app not found: " + paramString);
      return false;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      n.a(i, "could not set app enabled: " + paramString + ": " + localIllegalArgumentException.getMessage());
    }
  }
  
  public final boolean d(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      x().addUserRestriction(this.a, paramString);
      n.a(i, "successfully added user restriction " + paramString);
    }
    catch (SecurityException localSecurityException)
    {
      n.e(i, "failed to add user restriction " + paramString + " because of security exception: " + localSecurityException.getMessage());
      return false;
    }
    x().clearUserRestriction(this.a, paramString);
    n.a(i, "successfully cleared user restriction " + paramString);
    return true;
  }
  
  public final boolean e()
  {
    boolean bool = false;
    if (!x().isDeviceOwnerApp(AirWatchApp.f().getPackageName())) {
      bool = super.e();
    }
    return bool;
  }
}
