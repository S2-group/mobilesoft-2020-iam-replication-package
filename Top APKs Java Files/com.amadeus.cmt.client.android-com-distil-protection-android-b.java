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
  private static final Map<String, String> ˎ;
  private final o<Boolean> ʻ;
  private final o<Boolean> ʼ;
  private final o<Boolean> ʽ;
  private final Resources ˊ;
  private final StackTraceElement[] ˊॱ;
  private final PackageManager ˋ;
  private final k<String, String> ˋॱ;
  private final o<Boolean> ˏ;
  private final o<String> ˏॱ;
  private final o<Integer> ͺ;
  private final Context ॱ;
  private final o<String> ॱˊ;
  private final o<Boolean> ॱॱ;
  private final o<Boolean> ᐝ;
  
  static
  {
    HashMap localHashMap = new HashMap();
    ˎ = localHashMap;
    localHashMap.put("BOOTLOADER", Build.BOOTLOADER);
    ˎ.put("BOARD", Build.BOARD);
    ˎ.put("BRAND", Build.BRAND);
    ˎ.put("DEVICE", Build.DEVICE);
    ˎ.put("DISPLAY", Build.DISPLAY);
    ˎ.put("FINGERPRINT", Build.FINGERPRINT);
    ˎ.put("HARDWARE", Build.HARDWARE);
    ˎ.put("HOST", Build.HOST);
    ˎ.put("ID", Build.ID);
    ˎ.put("MANUFACTURER", Build.MANUFACTURER);
    ˎ.put("MODEL", Build.MODEL);
    ˎ.put("PRODUCT", Build.PRODUCT);
    ˎ.put("TAGS", Build.TAGS);
    ˎ.put("TIME", Long.toString(Build.TIME));
    ˎ.put("TYPE", Build.TYPE);
    ˎ.put("USER", Build.USER);
  }
  
  private b(Context paramContext, Resources paramResources, PackageManager paramPackageManager, o<Boolean> paramO1, o<Boolean> paramO2, o<Boolean> paramO3, o<Boolean> paramO4, o<Boolean> paramO5, o<Boolean> paramO6, k<String, String> paramK, o<String> paramO7, o<String> paramO8, o<Integer> paramO, StackTraceElement[] paramArrayOfStackTraceElement)
  {
    this.ॱ = paramContext;
    this.ˊ = paramResources;
    this.ˋ = paramPackageManager;
    this.ˏ = paramO1;
    this.ॱॱ = paramO2;
    this.ʼ = paramO3;
    this.ʻ = paramO4;
    this.ʽ = paramO5;
    this.ᐝ = paramO6;
    this.ˋॱ = paramK;
    this.ॱˊ = paramO7;
    this.ˏॱ = paramO8;
    this.ͺ = paramO;
    this.ˊॱ = paramArrayOfStackTraceElement;
  }
  
  static o<com.distil.protection.model.j> ˏ(Context paramContext, ContentResolver paramContentResolver, Resources paramResources, PackageManager paramPackageManager, AccountManager paramAccountManager, StackTraceElement[] paramArrayOfStackTraceElement)
  {
    new b(paramContext, paramResources, paramPackageManager, new o()new o {}, new o()new o {}, new o()new o {}, new o()new o {}, new o()new o {}, new o()new k {}, new k() {}, a.ˊ(paramAccountManager), new o()new o {}, new o() {}, paramArrayOfStackTraceElement);
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  private com.distil.protection.model.j ˏ()
  {
    DisplayMetrics localDisplayMetrics = this.ˊ.getDisplayMetrics();
    Object localObject1 = new TreeSet();
    Object localObject2 = this.ˋ.getInstalledPackages(128).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((Set)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
    }
    String str = (String)com.distil.protection.a.a.ˎ().ˊ(com.distil.protection.d.d.ˊ().ˊ(localObject1));
    if (this.ॱ.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") != -1) {}
    for (localObject1 = com.distil.protection.functional.j.ˎ(this.ॱˊ.ˎ());; localObject1 = com.distil.protection.functional.j.ˊ()) {
      break;
    }
    d.ˋ();
    f localF2 = d.ˊ();
    Object localObject4 = this.ॱ.getPackageName();
    f localF1;
    try
    {
      localObject2 = f.ˎ(this.ˋ.getPackageInfo((String)localObject4, 4160));
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
      localF1 = f.ˎ(this.ˋ.getApplicationInfo((String)localObject4, 0));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      localObject3 = f.ˎ(localNameNotFoundException2);
    }
    Object localObject3 = ((f)localObject3).ˎ().ॱ(new k() {});
    localObject4 = com.distil.protection.functional.j.ˏ(this.ˋ.getInstallerPackageName((String)localObject4)).ˊ(e.ˋ()).ˊ(com.distil.protection.d.b.ˋ()).ˊ(com.distil.protection.a.a.ˎ());
    HashMap localHashMap = new HashMap(ˎ);
    localHashMap.put("SERIAL", this.ˏॱ.ˎ());
    return new com.distil.protection.model.j(new c(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels, ((Boolean)this.ʻ.ˎ()).booleanValue(), ((Boolean)this.ʽ.ˎ()).booleanValue(), localF2.ˎ(), (String)this.ˋॱ.ˊ("android_id"), localHashMap, (com.distil.protection.functional.j)localObject1, str), new com.distil.protection.model.d(((Boolean)this.ॱॱ.ˎ()).booleanValue(), ((Boolean)this.ʼ.ˎ()).booleanValue(), ((Boolean)this.ˏ.ˎ()).booleanValue(), ((Boolean)this.ᐝ.ˎ()).booleanValue(), localF3.ˎ(), (com.distil.protection.functional.j)localObject4, ((Integer)this.ͺ.ˎ()).intValue(), localF4.ˎ(), this.ˊॱ, localF7.ˎ(), localJ, localF5.ˎ(), localF6.ˎ(), (com.distil.protection.functional.j)localObject3, Locale.getDefault()));
  }
}
