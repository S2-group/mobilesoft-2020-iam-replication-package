package org.onepf.oms.appstore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.onepf.oms.c;

public final class f
  extends c
{
  private final Context a;
  private org.onepf.oms.appstore.nokiaUtils.b b = null;
  
  public f(Context paramContext)
  {
    org.onepf.oms.a.b.a("NokiaStore.NokiaStore");
    this.a = paramContext;
  }
  
  private boolean c()
  {
    try
    {
      Object localObject = this.a.getPackageManager().getPackageInfo("com.nokia.payment.iapenabler", 64);
      if (((PackageInfo)localObject).signatures.length == 1)
      {
        localObject = localObject.signatures[0].toByteArray();
        localObject = MessageDigest.getInstance("SHA1").digest((byte[])localObject);
        int j = "C476A7D71C4CB92641A699C1F1CAC93CA81E0396".length();
        byte[] arrayOfByte = new byte[j / 2];
        int i = 0;
        while (i < j)
        {
          arrayOfByte[(i / 2)] = ((byte)((Character.digit("C476A7D71C4CB92641A699C1F1CAC93CA81E0396".charAt(i), 16) << 4) + Character.digit("C476A7D71C4CB92641A699C1F1CAC93CA81E0396".charAt(i + 1), 16)));
          i += 2;
        }
        if (Arrays.equals((byte[])localObject, arrayOfByte))
        {
          org.onepf.oms.a.b.b(new Object[] { "isBillingAvailable", "NIAP signature verified" });
          return true;
        }
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  public final String a()
  {
    return "com.nokia.nstore";
  }
  
  public final boolean a(String paramString)
  {
    org.onepf.oms.a.b.a("NokiaStore.isBillingAvailable");
    org.onepf.oms.a.b.a(new Object[] { "packageName = ", paramString });
    paramString = this.a.getPackageManager().getInstalledPackages(0).iterator();
    while (paramString.hasNext()) {
      if ("com.nokia.payment.iapenabler".equals(((PackageInfo)paramString.next()).packageName)) {
        return c();
      }
    }
    return false;
  }
  
  public final org.onepf.oms.b b()
  {
    if (this.b == null) {
      this.b = new org.onepf.oms.appstore.nokiaUtils.b(this.a);
    }
    return this.b;
  }
}
