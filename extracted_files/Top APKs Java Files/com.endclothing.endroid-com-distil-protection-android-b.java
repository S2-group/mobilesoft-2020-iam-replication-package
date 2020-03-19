package com.distil.protection.android;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import com.distil.protection.a.e;
import com.distil.protection.functional.f;
import com.distil.protection.functional.k;
import com.distil.protection.functional.o;
import com.distil.protection.model.c;
import com.distil.protection.model.r;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

final class b
  implements o<com.distil.protection.model.j>
{
  private static final Map<String, String> a;
  private final Context b;
  private final Resources c;
  private final PackageManager d;
  private final o<Boolean> e;
  private final o<Boolean> f;
  private final o<Boolean> g;
  private final o<Boolean> h;
  private final o<Boolean> i;
  private final o<Boolean> j;
  private final k<String, String> k;
  private final o<String> l;
  private final o<String> m;
  private final o<Integer> n;
  private final StackTraceElement[] o;
  
  static
  {
    HashMap localHashMap = new HashMap();
    a = localHashMap;
    localHashMap.put("BOOTLOADER", Build.BOOTLOADER);
    a.put("BOARD", Build.BOARD);
    a.put("BRAND", Build.BRAND);
    a.put("DEVICE", Build.DEVICE);
    a.put("DISPLAY", Build.DISPLAY);
    a.put("FINGERPRINT", Build.FINGERPRINT);
    a.put("HARDWARE", Build.HARDWARE);
    a.put("HOST", Build.HOST);
    a.put("ID", Build.ID);
    a.put("MANUFACTURER", Build.MANUFACTURER);
    a.put("MODEL", Build.MODEL);
    a.put("PRODUCT", Build.PRODUCT);
    a.put("TAGS", Build.TAGS);
    a.put("TIME", Long.toString(Build.TIME));
    a.put("TYPE", Build.TYPE);
    a.put("USER", Build.USER);
  }
  
  private b(Context paramContext, Resources paramResources, PackageManager paramPackageManager, o<Boolean> paramO1, o<Boolean> paramO2, o<Boolean> paramO3, o<Boolean> paramO4, o<Boolean> paramO5, o<Boolean> paramO6, k<String, String> paramK, o<String> paramO7, o<String> paramO8, o<Integer> paramO, StackTraceElement[] paramArrayOfStackTraceElement)
  {
    this.b = paramContext;
    this.c = paramResources;
    this.d = paramPackageManager;
    this.e = paramO1;
    this.f = paramO2;
    this.g = paramO3;
    this.h = paramO4;
    this.i = paramO5;
    this.j = paramO6;
    this.k = paramK;
    this.l = paramO7;
    this.m = paramO8;
    this.n = paramO;
    this.o = paramArrayOfStackTraceElement;
  }
  
  static o<com.distil.protection.model.j> a(Context paramContext, ContentResolver paramContentResolver, Resources paramResources, PackageManager paramPackageManager, AccountManager paramAccountManager, StackTraceElement[] paramArrayOfStackTraceElement)
  {
    new b(paramContext, paramResources, paramPackageManager, new o()new o {}, new o()new o {}, new o()new o {}, new o()new o {}, new o()new o {}, new o()new k {}, new k() {}, a.a(paramAccountManager), new o()new o {}, new o() {}, paramArrayOfStackTraceElement);
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  private com.distil.protection.model.j a()
  {
    DisplayMetrics localDisplayMetrics = this.c.getDisplayMetrics();
    Object localObject1 = new TreeSet();
    Object localObject2 = this.d.getInstalledPackages(128).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((Set)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
    }
    String str = (String)com.distil.protection.a.a.ˎ().ˊ(com.distil.protection.d.d.ˊ().ˊ(localObject1));
    if (this.b.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") != -1) {}
    for (localObject1 = com.distil.protection.functional.j.ˎ(this.l.ˎ());; localObject1 = com.distil.protection.functional.j.ˊ()) {
      break;
    }
    d.a();
    f localF2 = d.b();
    Object localObject4 = this.b.getPackageName();
    f localF1;
    try
    {
      localObject2 = f.ˎ(this.d.getPackageInfo((String)localObject4, 4160));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException1)
    {
      localF1 = f.ˎ(localNameNotFoundException1);
    }
    f localF3 = localF1.ˋ(new k() {});
    f localF4 = localF1.ˋ(new k() {});
    f localF5 = localF1.ˋ(new k() {});
    f localF6 = localF1.ˋ(new k() {});
    f localF7 = localF1.ˋ(new k() {});
    com.distil.protection.functional.j localJ = localF1.ˎ().ॱ(new k() {});
    try
    {
      localF1 = f.ˎ(this.d.getApplicationInfo((String)localObject4, 0));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      localObject3 = f.ˎ(localNameNotFoundException2);
    }
    Object localObject3 = ((f)localObject3).ˎ().ॱ(new k() {});
    localObject4 = com.distil.protection.functional.j.ˏ(this.d.getInstallerPackageName((String)localObject4)).ˊ(e.ˋ()).ˊ(com.distil.protection.d.b.ˋ()).ˊ(com.distil.protection.a.a.ˎ());
    HashMap localHashMap = new HashMap(a);
    localHashMap.put("SERIAL", this.m.ˎ());
    return new com.distil.protection.model.j(new c(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels, ((Boolean)this.h.ˎ()).booleanValue(), ((Boolean)this.i.ˎ()).booleanValue(), localF2.ˎ(), (String)this.k.ˊ("android_id"), localHashMap, (com.distil.protection.functional.j)localObject1, str), new com.distil.protection.model.d(((Boolean)this.f.ˎ()).booleanValue(), ((Boolean)this.g.ˎ()).booleanValue(), ((Boolean)this.e.ˎ()).booleanValue(), ((Boolean)this.j.ˎ()).booleanValue(), localF3.ˎ(), (com.distil.protection.functional.j)localObject4, ((Integer)this.n.ˎ()).intValue(), localF4.ˎ(), this.o, localF7.ˎ(), localJ, localF5.ˎ(), localF6.ˎ(), (com.distil.protection.functional.j)localObject3, Locale.getDefault()));
  }
}
