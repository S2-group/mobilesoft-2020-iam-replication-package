import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserManager;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.chromium.net.NetworkException;

public final class kqh
{
  public final Context a;
  private final kpo b;
  
  kqh(Context paramContext, kpo paramKpo)
  {
    this.a = paramContext;
    this.b = paramKpo;
  }
  
  public static boolean a(Throwable paramThrowable)
  {
    return (paramThrowable != null) && ((paramThrowable.getCause() instanceof NetworkException)) && (((NetworkException)paramThrowable.getCause()).getErrorCode() == 7);
  }
  
  public final List a(Intent paramIntent)
  {
    ArrayList localArrayList = new ArrayList();
    Uri localUri = paramIntent.getData();
    if (localUri == null)
    {
      int i = paramIntent.getIntExtra("android.intent.extra.UID", 0);
      paramIntent = this.a.getPackageManager().getPackagesForUid(i);
      if (paramIntent != null) {
        localArrayList.addAll(Arrays.asList(paramIntent));
      }
    }
    else
    {
      localArrayList.add(localUri.getSchemeSpecificPart());
    }
    return localArrayList;
  }
  
  public final Map a(int paramInt)
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
    HashSet localHashSet1 = new HashSet(((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      localHashSet1.add(((ResolveInfo)((Iterator)localObject1).next()).activityInfo.packageName);
    }
    localObject1 = new Intent("android.intent.action.DIAL", null);
    ((Intent)localObject1).addCategory("android.intent.category.DEFAULT");
    localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
    HashSet localHashSet2 = new HashSet(((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      localHashSet2.add(((ResolveInfo)((Iterator)localObject1).next()).activityInfo.packageName);
    }
    localObject1 = ((DevicePolicyManager)this.a.getSystemService("device_policy")).getActiveAdmins();
    HashSet localHashSet3 = new HashSet();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext()) {
        localHashSet3.add(((ComponentName)((Iterator)localObject1).next()).getPackageName());
      }
    }
    localObject1 = localPackageManager.getInstalledPackages(paramInt);
    HashMap localHashMap = new HashMap();
    Iterator localIterator = ((List)localObject1).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      try
      {
        Object localObject2 = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 8192);
        if (!qfw.a((ApplicationInfo)localObject2))
        {
          localObject1 = localPackageInfo.packageName;
        }
        else
        {
          rsd localRsd = qzn.k.createBuilder();
          try
          {
            localObject1 = localPackageInfo.packageName;
            localRsd.copyOnWrite();
            Object localObject3 = (qzn)localRsd.instance;
            if (localObject1 != null)
            {
              ((qzn)localObject3).a |= 0x1;
              ((qzn)localObject3).b = ((String)localObject1);
              int i = localPackageInfo.versionCode;
              localRsd.copyOnWrite();
              localObject1 = (qzn)localRsd.instance;
              ((qzn)localObject1).a |= 0x10;
              ((qzn)localObject1).g = i;
              localObject1 = pfc.b(localPackageInfo.versionName);
              localRsd.copyOnWrite();
              localObject3 = (qzn)localRsd.instance;
              if (localObject1 != null)
              {
                ((qzn)localObject3).a |= 0x8;
                ((qzn)localObject3).f = ((String)localObject1);
                localObject1 = ((ApplicationInfo)localObject2).loadLabel(localPackageManager).toString();
                localRsd.copyOnWrite();
                localObject3 = (qzn)localRsd.instance;
                if (localObject1 != null)
                {
                  ((qzn)localObject3).a |= 0x2;
                  ((qzn)localObject3).c = ((String)localObject1);
                  Object localObject4;
                  if (((paramInt & 0x40) != 0) && (kqi.a != null))
                  {
                    localObject1 = localPackageInfo.signatures;
                    int j = localObject1.length;
                    i = 0;
                    while (i < j)
                    {
                      localObject3 = localObject1[i];
                      localObject3 = rqo.a(kqi.a.digest(((Signature)localObject3).toByteArray()));
                      localRsd.copyOnWrite();
                      localObject4 = (qzn)localRsd.instance;
                      if (localObject3 != null)
                      {
                        if (!((qzn)localObject4).d.a()) {
                          ((qzn)localObject4).d = rsc.mutableCopy(((qzn)localObject4).d);
                        }
                        ((qzn)localObject4).d.add(localObject3);
                        i += 1;
                      }
                      else
                      {
                        throw new NullPointerException();
                      }
                    }
                  }
                  if ((((ApplicationInfo)localObject2).flags & 0x1) != 0) {
                    localRsd.A(2);
                  }
                  if ((((ApplicationInfo)localObject2).flags & 0x80) != 0) {
                    localRsd.A(3);
                  }
                  if (localHashSet1.contains(localPackageInfo.packageName)) {
                    localRsd.A(4);
                  }
                  if (localHashSet2.contains(localPackageInfo.packageName)) {
                    localRsd.A(5);
                  }
                  boolean bool = localHashSet3.contains(localPackageInfo.packageName);
                  localRsd.copyOnWrite();
                  localObject1 = (qzn)localRsd.instance;
                  ((qzn)localObject1).a |= 0x20;
                  ((qzn)localObject1).i = bool;
                  localObject2 = this.b.a(localPackageInfo).entrySet().iterator();
                  while (((Iterator)localObject2).hasNext())
                  {
                    localObject1 = (Map.Entry)((Iterator)localObject2).next();
                    localObject3 = qzo.d.createBuilder();
                    localObject4 = (String)((Map.Entry)localObject1).getKey();
                    ((rsd)localObject3).copyOnWrite();
                    qzo localQzo = (qzo)((rsd)localObject3).instance;
                    if (localObject4 != null)
                    {
                      localQzo.a |= 0x1;
                      localQzo.b = ((String)localObject4);
                      if ((Build.VERSION.SDK_INT >= 23) && (localPackageInfo.applicationInfo.targetSdkVersion >= 23))
                      {
                        if (((Boolean)((Map.Entry)localObject1).getValue()).booleanValue()) {
                          localObject1 = qri.c;
                        } else {
                          localObject1 = qri.d;
                        }
                        ((rsd)localObject3).b((qri)localObject1);
                      }
                      else
                      {
                        ((rsd)localObject3).b(qri.b);
                      }
                      localRsd.copyOnWrite();
                      localObject1 = (qzn)localRsd.instance;
                      if (!((qzn)localObject1).j.a()) {
                        ((qzn)localObject1).j = rsc.mutableCopy(((qzn)localObject1).j);
                      }
                      ((qzn)localObject1).j.add((qzo)((rsd)localObject3).build());
                    }
                    else
                    {
                      throw new NullPointerException();
                    }
                  }
                  try
                  {
                    localPackageManager.getApplicationInfo(localPackageInfo.packageName, 0);
                    localRsd.z(3);
                  }
                  catch (PackageManager.NameNotFoundException localNameNotFoundException1)
                  {
                    localRsd.z(2);
                  }
                }
                else
                {
                  throw new NullPointerException();
                }
              }
              else
              {
                throw new NullPointerException();
              }
            }
            else
            {
              throw new NullPointerException();
            }
          }
          catch (Exception localException)
          {
            kph.a.b("SupervisionCommonUtils", localException, "error when trying to add %s to device info", new Object[] { localPackageInfo.packageName });
            localHashMap.put(((qzn)localRsd.instance).b, (qzn)localRsd.build());
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2)
      {
        kph.a.a("SupervisionCommonUtils", "Failed to getApplicationInfo %s", new Object[] { localPackageInfo.packageName });
      }
    }
    localHashMap.size();
    return localHashMap;
  }
  
  public final boolean a()
  {
    UserManager localUserManager = (UserManager)this.a.getSystemService("user");
    if (localUserManager != null)
    {
      if (Build.VERSION.SDK_INT < 23) {
        return localUserManager.getSerialNumberForUser(Process.myUserHandle()) == 0L;
      }
      return localUserManager.isSystemUser();
    }
    return false;
  }
}
