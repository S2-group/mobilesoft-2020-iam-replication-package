import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.apps.messaging.shared.datamodel.BugleContentProvider;
import com.google.android.apps.messaging.shared.datamodel.data.ParticipantData;
import com.google.android.apps.messaging.shared.datamodel.data.ParticipantData.a;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class cie
  extends chu
  implements LoaderManager.LoaderCallbacks<Cursor>
{
  public static boolean b;
  public static final Lock c = new ReentrantLock();
  public static String d;
  public final Context e;
  public LoaderManager f;
  public cif g;
  
  public cie(Context paramContext, cif paramCif)
  {
    this.e = paramContext;
    this.g = paramCif;
  }
  
  public static ckv a(cmc paramCmc)
  {
    return new ckv(ParticipantData.getFromCursor(paramCmc));
  }
  
  /* Error */
  public static void a(boolean paramBoolean)
  {
    // Byte code:
    //   0: getstatic 28	cie:c	Ljava/util/concurrent/locks/Lock;
    //   3: invokeinterface 54 1 0
    //   8: getstatic 58	dfv:b	Z
    //   11: ifeq +38 -> 49
    //   14: iload_0
    //   15: ifeq +34 -> 49
    //   18: iconst_1
    //   19: istore_1
    //   20: iload_1
    //   21: invokestatic 62	diy:b	(Z)V
    //   24: getstatic 68	cth:r	Lcth;
    //   27: invokevirtual 72	cth:L	()Ldnf;
    //   30: ldc 74
    //   32: iload_0
    //   33: invokevirtual 79	dnf:b	(Ljava/lang/String;Z)V
    //   36: iload_0
    //   37: putstatic 80	cie:b	Z
    //   40: getstatic 28	cie:c	Ljava/util/concurrent/locks/Lock;
    //   43: invokeinterface 83 1 0
    //   48: return
    //   49: iconst_0
    //   50: istore_1
    //   51: goto -31 -> 20
    //   54: astore_2
    //   55: getstatic 28	cie:c	Ljava/util/concurrent/locks/Lock;
    //   58: invokeinterface 83 1 0
    //   63: aload_2
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	paramBoolean	boolean
    //   19	32	1	bool	boolean
    //   54	10	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   8	14	54	finally
    //   20	40	54	finally
  }
  
  public static boolean a()
  {
    if (dfv.b) {}
    String str;
    do
    {
      do
      {
        return false;
      } while (TextUtils.equals(d, ""));
      str = f();
      d = str;
    } while (TextUtils.equals(str, ""));
    return true;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int j;
    int i;
    if (paramPackageInfo != null)
    {
      bool1 = bool2;
      if (paramPackageInfo.providers != null)
      {
        paramPackageInfo = paramPackageInfo.providers;
        j = paramPackageInfo.length;
        i = 0;
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if ("com.android.blockednumber".equalsIgnoreCase(paramPackageInfo[i].authority)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean d()
  {
    if (!cth.r.aT().d()) {}
    do
    {
      return false;
      if (!a()) {
        return true;
      }
    } while (cth.r.T().b(cth.r.I()));
    return true;
  }
  
  public static boolean e()
  {
    if (!a()) {}
    while ((!cth.r.aT().d()) || (cth.r.T().b(cth.r.I()))) {
      return false;
    }
    c.lock();
    try
    {
      boolean bool = b;
      if (bool) {
        return true;
      }
      bool = cth.r.L().a("app_already_migrated_blocked_contacts", false);
      b = bool;
      return bool;
    }
    finally
    {
      c.unlock();
    }
  }
  
  private static String f()
  {
    Object localObject = cth.r.I().getPackageManager();
    if (d != null) {
      try
      {
        if (a(((PackageManager)localObject).getPackageInfo(d, 8)))
        {
          String str = d;
          return str;
        }
      }
      catch (Exception localException)
      {
        djr.e("Bugle", localException, "Unable to check if system blocked contacts are available");
      }
    }
    localObject = ((PackageManager)localObject).getInstalledPackages(8).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (a(localPackageInfo)) {
        return localPackageInfo.packageName;
      }
    }
    return "";
  }
  
  protected final void b()
  {
    this.g = null;
    if (this.f != null)
    {
      this.f.destroyLoader(1);
      this.f = null;
    }
  }
  
  public final Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle)
  {
    diy.a(1, paramInt);
    paramBundle = paramBundle.getString("bindingId");
    if (isBound(paramBundle))
    {
      Uri localUri = BugleContentProvider.p;
      return new bxk(paramBundle, this.e, localUri, ParticipantData.a.a, "blocked=1", null, null);
    }
    return null;
  }
  
  public final void onLoaderReset(Loader<Cursor> paramLoader)
  {
    diy.a(1, paramLoader.getId());
    diy.a(isBound(((bxk)paramLoader).a));
    this.g.a(null);
  }
}
