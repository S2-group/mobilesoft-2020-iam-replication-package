package a;

import android.app.Activity;
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
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.kaspersky.batterysaver.ApplicationContext;
import com.kaspersky.batterysaver.utils.ActivityUtils;
import com.kaspersky.batterysaver.utils.ActivityUtils.NoActivityException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class avt
{
  private static final String a = "avt";
  private static final Collection<String> b = bpn.b(new String[] { "com.miui.securitycenter" });
  private static final Set<String> e = bpn.b(new String[] { "com.google.android.gms", "com.android.dialer", "com.asus.message", "com.android.mms", "com.google.android.deskclock" });
  private final PackageManager c;
  private final String d;
  
  @Inject
  public avt(@ApplicationContext Context paramContext)
  {
    this.c = paramContext.getPackageManager();
    this.d = paramContext.getPackageName();
  }
  
  @Nullable
  private static ResolveInfo a(PackageManager paramPackageManager)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.addCategory("android.intent.category.DEFAULT");
    return paramPackageManager.resolveActivity(localIntent, 65536);
  }
  
  private List<ResolveInfo> a(Intent paramIntent, int paramInt)
  {
    try
    {
      paramIntent = this.c.queryIntentActivities(paramIntent, paramInt);
      return paramIntent;
    }
    catch (Throwable paramIntent)
    {
      for (;;) {}
    }
    return Collections.emptyList();
  }
  
  @Nullable
  public Intent a(String paramString)
  {
    return this.c.getLaunchIntentForPackage(paramString);
  }
  
  public ApplicationInfo a(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getApplicationInfo(paramString, paramInt);
  }
  
  @NonNull
  public Set<String> a()
  {
    HashSet localHashSet = new HashSet();
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = a((Intent)localObject, 0).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
    }
    ((Intent)localObject).removeCategory("android.intent.category.LAUNCHER");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = a((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName);
    }
    return localHashSet;
  }
  
  public final Set<String> a(Context paramContext)
  {
    paramContext = (Set)bqs.b(new String[] { "android.settings.APPLICATION_DETAILS_SETTINGS", "android.intent.action.POWER_USAGE_SUMMARY" }).a(new avu(paramContext)).a(new avv(this)).b(avw.a).a(avx.a).a(bpo.c());
    paramContext.addAll(b);
    if ("huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
      paramContext.add("com.kaspersky.batterysaver");
    }
    return paramContext;
  }
  
  public void a(Context paramContext, String paramString)
    throws ActivityUtils.NoActivityException
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    if (!(paramContext instanceof Activity))
    {
      localIntent.addFlags(268435456);
      localIntent.addFlags(1073741824);
      localIntent.addFlags(8388608);
      localIntent.addFlags(67108864);
      localIntent.addFlags(32768);
    }
    ActivityUtils.a(paramContext, localIntent);
  }
  
  public boolean a(Intent paramIntent)
  {
    return a(paramIntent, 65536).isEmpty() ^ true;
  }
  
  public boolean a(ApplicationInfo paramApplicationInfo)
  {
    int i = paramApplicationInfo.flags;
    boolean bool = true;
    if ((i & 0x1) == 0)
    {
      if ((paramApplicationInfo.flags & 0x80) != 0) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public boolean a(ApplicationInfo paramApplicationInfo, @Nullable Collection<String> paramCollection, @Nullable String paramString)
  {
    if (paramApplicationInfo.packageName.equals(this.d)) {
      return false;
    }
    if (e.contains(paramApplicationInfo.packageName)) {
      return false;
    }
    if (!a(paramApplicationInfo)) {
      return true;
    }
    if (b(paramApplicationInfo)) {
      return false;
    }
    String str = paramString;
    if (paramString == null)
    {
      ResolveInfo localResolveInfo = a(this.c);
      str = paramString;
      if (localResolveInfo != null) {
        str = localResolveInfo.activityInfo.packageName;
      }
    }
    if (paramApplicationInfo.packageName.equals(str)) {
      return false;
    }
    paramString = paramCollection;
    if (paramCollection == null) {
      paramString = a();
    }
    return paramString.contains(paramApplicationInfo.packageName);
  }
  
  @Nullable
  public String b()
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = this.c.resolveActivity((Intent)localObject, 65536);
    if ((localObject != null) && (((ResolveInfo)localObject).activityInfo != null)) {
      return ((ResolveInfo)localObject).activityInfo.packageName;
    }
    return null;
  }
  
  public String b(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return c(a(paramString, 0));
  }
  
  public Set<String> b(Context paramContext)
  {
    Object localObject = ((InputMethodManager)paramContext.getSystemService("input_method")).getEnabledInputMethodList();
    paramContext = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((InputMethodInfo)((Iterator)localObject).next()).getPackageName());
    }
    localObject = a(this.c);
    if (localObject != null) {
      paramContext.remove(((ResolveInfo)localObject).activityInfo.packageName);
    }
    return paramContext;
  }
  
  public boolean b(ApplicationInfo paramApplicationInfo)
  {
    try
    {
      PackageInfo localPackageInfo = this.c.getPackageInfo("android", 64);
      if (localPackageInfo == null) {
        return false;
      }
      paramApplicationInfo = this.c.getPackageInfo(paramApplicationInfo.packageName, 64);
      if (paramApplicationInfo == null) {
        return false;
      }
      boolean bool = localPackageInfo.signatures[0].equals(paramApplicationInfo.signatures[0]);
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramApplicationInfo)
    {
      aoc.a(paramApplicationInfo);
    }
    return false;
  }
  
  public String c(ApplicationInfo paramApplicationInfo)
  {
    return (String)this.c.getApplicationLabel(paramApplicationInfo);
  }
  
  public Set<String> c()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.c.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    return localHashSet;
  }
  
  public void c(Context paramContext)
    throws ActivityUtils.NoActivityException
  {
    Intent localIntent = new Intent("android.intent.action.POWER_USAGE_SUMMARY");
    if (!(paramContext instanceof Activity))
    {
      localIntent.addFlags(268435456);
      localIntent.addFlags(1073741824);
      localIntent.addFlags(8388608);
      localIntent.addFlags(67108864);
      localIntent.addFlags(32768);
    }
    localIntent.addFlags(65536);
    ActivityUtils.a(paramContext, localIntent);
  }
  
  public boolean c(String paramString)
  {
    boolean bool = false;
    try
    {
      int i = this.c.getApplicationInfo(paramString, 0).flags;
      if ((i & 0x200000) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public List<ApplicationInfo> d()
  {
    return this.c.getInstalledApplications(0);
  }
}
