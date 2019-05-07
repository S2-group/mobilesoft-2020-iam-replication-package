package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.chimera.DynamiteApplicationContext;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.DynamiteApi;
import gwy;
import gwz;
import gxb;
import gxh;
import gxi;
import gxk;
import gxl;
import gxs;
import gxu;
import hgs;
import hnj;
import hno;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@KeepName
@DynamiteApi
public final class GoogleCertificatesImpl
  extends hgs
{
  public GoogleCertificatesImpl() {}
  
  private static Context a()
  {
    Context localContext = DynamiteApplicationContext.getContext();
    if (localContext != null) {
      return localContext;
    }
    throw new IllegalStateException("Missing DynamiteApplicationContext.");
  }
  
  private static boolean a(gxu paramGxu)
  {
    gwz localGwz = gwy.a;
    if (paramGxu.d == null) {
      paramGxu.d = gxu.a(paramGxu.c, "*");
    }
    if (!localGwz.a(paramGxu.d)) {
      return paramGxu.a(gwy.b());
    }
    return true;
  }
  
  private static boolean a(gxu paramGxu, gxs paramGxs)
  {
    if (!paramGxu.a(gwy.a()))
    {
      String str = paramGxu.a;
      paramGxu = paramGxu.b;
      if ((paramGxs != null) && (paramGxs.b) && (gxs.a.contains(str)))
      {
        paramGxs = paramGxs.a();
        if ((paramGxs != null) && (paramGxs.equals(paramGxu))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Deprecated
  public final hnj getGoogleCertificates()
  {
    ArrayList localArrayList = new ArrayList();
    Collections.addAll(localArrayList, gxk.a());
    HashSet localHashSet = new HashSet();
    Object localObject = a();
    if (localObject == null)
    {
      localObject = new HashSet();
    }
    else
    {
      SystemClock.elapsedRealtime();
      Iterator localIterator = ((Context)localObject).getPackageManager().getInstalledPackages(64).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        String str = localPackageInfo.packageName;
        Signature[] arrayOfSignature = localPackageInfo.signatures;
        localObject = null;
        if ((arrayOfSignature != null) && (localPackageInfo.signatures.length == 1)) {
          localObject = localPackageInfo.signatures[0].toByteArray();
        }
        if ((localObject != null) && (a(new gxu(str, (byte[])localObject)))) {
          localHashSet.add(new gxi((byte[])localObject));
        }
      }
      localObject = localHashSet;
    }
    localArrayList.addAll((Collection)localObject);
    return hno.a((gxh[])localArrayList.toArray(new gxh[0]));
  }
  
  @Deprecated
  public final hnj getGoogleReleaseCertificates()
  {
    return hno.a(gxk.a());
  }
  
  public final boolean isGoogleOrPlatformSigned(gxl paramGxl, hnj paramHnj)
  {
    if (paramGxl.b != null)
    {
      if (paramHnj != null) {
        paramHnj = new gxs((PackageManager)hno.a(paramHnj));
      } else {
        paramHnj = null;
      }
      String str = paramGxl.a;
      gxu localGxu = new gxu(str, paramGxl.b);
      if (!a(localGxu, paramHnj))
      {
        if (a(localGxu))
        {
          if (!paramGxl.c)
          {
            if ((!paramGxl.d) && (gxb.a(a(), str)))
            {
              Log.w("GoogleCertificatesImpl", String.valueOf(str).concat(" is signed with test keys"));
              return true;
            }
            return false;
          }
          return true;
        }
        return false;
      }
      return true;
    }
    return false;
  }
  
  @Deprecated
  public final boolean isGoogleReleaseSigned(String paramString, hnj paramHnj)
  {
    return a(new gxu(paramString, new gxi((byte[])hno.a(paramHnj))), null);
  }
  
  @Deprecated
  public final boolean isGoogleSigned(String paramString, hnj paramHnj)
  {
    paramString = new gxu(paramString, new gxi((byte[])hno.a(paramHnj)));
    return (a(paramString, null)) || (a(paramString));
  }
}
