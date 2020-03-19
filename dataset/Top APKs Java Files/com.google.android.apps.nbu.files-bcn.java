import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class bcn
  implements bca
{
  public static final String a = bcn.class.getSimpleName();
  public final bcb b;
  public final ncn c;
  public final lmd d;
  private final PackageManager e;
  private final Context f;
  private Method g;
  private final bcc h;
  private final duw i;
  private final crb j;
  
  bcn(PackageManager paramPackageManager, Context paramContext, bcc paramBcc, bcb paramBcb, ncn paramNcn, lmd paramLmd, duw paramDuw, crb paramCrb)
  {
    this.e = paramPackageManager;
    this.f = paramContext;
    this.h = paramBcc;
    this.b = paramBcb;
    this.c = paramNcn;
    this.i = paramDuw;
    this.d = paramLmd;
    this.j = paramCrb;
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int m = paramArrayOfByte.length;
    int k = 0;
    while (k < m)
    {
      localStringBuilder.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[k]) }));
      k += 1;
    }
    return localStringBuilder.toString();
  }
  
  private final boolean b(String paramString1, String paramString2)
  {
    try
    {
      Object localObject = this.e.getPackageInfo(paramString1, 64);
      if ((((PackageInfo)localObject).signatures != null) && (((PackageInfo)localObject).signatures.length == 1))
      {
        localObject = localObject.signatures[0];
        localObject = MessageDigest.getInstance("SHA1").digest(((Signature)localObject).toByteArray());
        if (a((byte[])localObject).equals(paramString2)) {
          return true;
        }
        String.format("Signatures don't match. Expected %s, actual is %s.", new Object[] { paramString2, a((byte[])localObject) });
      }
      else
      {
        String.format("Package has %d signatures. Expected 1.", new Object[] { Integer.valueOf(((PackageInfo)localObject).signatures.length) });
      }
    }
    catch (PackageManager.NameNotFoundException paramString2)
    {
      paramString1 = String.valueOf(paramString1);
      if (paramString1.length() != 0) {
        "Couldn't get package for ".concat(paramString1);
      } else {
        new String("Couldn't get package for ");
      }
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      Log.e(a, "Error occurred getting signature hash.", paramString1);
    }
    return false;
  }
  
  final ben a(PackageInfo paramPackageInfo, long paramLong)
  {
    long l = this.h.a(paramPackageInfo.packageName);
    paramPackageInfo = (nqb)((nqc)ben.m.a(bt.ci, null)).c(a(paramPackageInfo)).d(paramPackageInfo.packageName).e("application/application").a(paramLong).b(l).a(paramPackageInfo.applicationInfo.icon).b(paramPackageInfo.applicationInfo.sourceDir).d();
    if (!nqb.a(paramPackageInfo, Boolean.TRUE.booleanValue())) {
      throw new nss();
    }
    return (ben)paramPackageInfo;
  }
  
  public final String a(PackageInfo paramPackageInfo)
  {
    CharSequence localCharSequence = this.e.getApplicationLabel(paramPackageInfo.applicationInfo);
    if (localCharSequence == null)
    {
      if (paramPackageInfo.packageName == null) {
        return "";
      }
      return paramPackageInfo.packageName;
    }
    return localCharSequence.toString();
  }
  
  public final List a(int paramInt)
  {
    Object localObject2 = a(bt.Z, bt.ac);
    Object localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).add(5, -paramInt);
    long l1 = ((Calendar)localObject1).getTimeInMillis();
    localObject1 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (PackageInfo)((Iterator)localObject2).next();
      if (((PackageInfo)localObject3).firstInstallTime <= l1)
      {
        long l2 = this.h.a(((PackageInfo)localObject3).packageName);
        if ((l2 >= 0L) && (l2 < l1)) {
          ((List)localObject1).add(a((PackageInfo)localObject3, this.b.b(((PackageInfo)localObject3).packageName)));
        }
      }
    }
    localObject2 = new HashSet(this.j.a("unused_apps_blacklist"));
    Object localObject3 = new ArrayList();
    localObject1 = (ArrayList)localObject1;
    int m = ((ArrayList)localObject1).size();
    paramInt = 0;
    while (paramInt < m)
    {
      Object localObject4 = ((ArrayList)localObject1).get(paramInt);
      int k = paramInt + 1;
      localObject4 = (ben)localObject4;
      paramInt = k;
      if (!((Set)localObject2).contains(((ben)localObject4).d))
      {
        ((List)localObject3).add(localObject4);
        paramInt = k;
      }
    }
    Collections.sort((List)localObject3, bco.a);
    return localObject3;
  }
  
  public final List a(int paramInt1, int paramInt2)
  {
    knx.d();
    Object localObject = this.e.getInstalledPackages(0);
    if ((paramInt1 == bt.Y) && (paramInt2 == bt.ab)) {
      return localObject;
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    label94:
    label122:
    label159:
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      int k;
      if (paramInt1 == bt.Z) {
        if ((localPackageInfo.applicationInfo.flags & 0x89) != 0)
        {
          k = 1;
          if (k == 0) {
            break label122;
          }
          k = 0;
        }
      }
      for (;;)
      {
        if (k == 0) {
          break label159;
        }
        localArrayList.add(localPackageInfo);
        break;
        k = 0;
        break label94;
        if (paramInt2 == bt.ac)
        {
          String str = localPackageInfo.packageName;
          if (this.f.getPackageName().equals(str))
          {
            k = 0;
            continue;
          }
        }
        k = 1;
      }
    }
    return localArrayList;
  }
  
  public final nck a()
  {
    boolean bool = this.i.f();
    if (bool) {}
    for (int k = bt.Z;; k = bt.Y) {
      return naw.a(this.c.a(mmq.a(new bcp(this, k))), mmq.b(new bcr(this, bool)), this.c);
    }
  }
  
  public final nck a(String paramString)
  {
    ndb localNdb = new ndb();
    bcz localBcz = new bcz(paramString, localNdb);
    try
    {
      if (this.g == null)
      {
        this.g = this.e.getClass().getDeclaredMethod("deleteApplicationCacheFiles", new Class[] { String.class, IPackageDataObserver.class });
        this.g.setAccessible(true);
      }
      this.g.invoke(this.e, new Object[] { paramString, localBcz });
      return localNdb;
    }
    catch (InvocationTargetException paramString)
    {
      localNdb.a(paramString.getTargetException());
      Log.e(a, "deleteApplicationCache failed: ", paramString.getTargetException());
      return localNdb;
    }
    catch (Exception paramString)
    {
      localNdb.a(paramString);
      Log.e(a, "Failed to invoke deleteApplicationCache: ", paramString);
    }
    return localNdb;
  }
  
  public final boolean a(String paramString, int paramInt)
  {
    try
    {
      PackageInfo localPackageInfo = this.e.getPackageInfo(paramString, 0);
      if (localPackageInfo.versionCode >= 2307781) {
        return true;
      }
      String.format("Expected version >= %d. Actual version is %d", new Object[] { Integer.valueOf(2307781), Integer.valueOf(localPackageInfo.versionCode) });
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label77;
      }
    }
    "Could not get packageInfo for ".concat(paramString);
    for (;;)
    {
      return false;
      label77:
      new String("Could not get packageInfo for ");
    }
  }
  
  public final boolean a(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramString1 = this.e.resolveContentProvider(paramString1, 0);
    boolean bool1 = bool2;
    if (paramString1 != null)
    {
      bool1 = bool2;
      if (b(paramString1.packageName, paramString2)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public final boolean b(String paramString)
  {
    try
    {
      this.e.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public final boolean c(String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = this.e.getPackageInfo(paramString, 0);
      long l1 = paramString.firstInstallTime;
      long l2 = paramString.lastUpdateTime;
      if (l1 >= l2) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public final boolean d(String paramString)
  {
    try
    {
      boolean bool = this.e.getPackageInfo(paramString, 0).applicationInfo.enabled;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
}
