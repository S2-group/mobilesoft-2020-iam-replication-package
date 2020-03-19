package com.kaspersky.pctrl.kmsshared;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.util.Predicate;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.kaspersky.pctrl.kmsshared.settings.KpcSettings;
import com.kaspersky.pctrl.kmsshared.settings.sections.GeneralSettingsSection;
import com.kaspersky.pctrl.kmsshared.settings.sections.GeneralSettingsSection.ProductMode;
import com.kaspersky.pctrl.kmsshared.settings.sections.WizardSettingsSection;
import com.kaspersky.pctrl.platformspecific.autostart.IAutoStartManager;
import com.kaspersky.pctrl.platformspecific.autostart.IAutoStartManager.AutoStartState;
import com.kaspersky.pctrl.platformspecific.locktasks.ILockTasksManager;
import com.kaspersky.pctrl.platformspecific.locktasks.ILockTasksManager.LockedState;
import com.kaspersky.pctrl.platformspecific.protectapp.IProtectAppManager;
import com.kaspersky.pctrl.platformspecific.protectapp.IProtectAppManager.ProtectAppState;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import s.car;
import s.ckd;
import s.dah;
import s.dld;
import s.fqx;
import s.fts;
import s.fup;
import s.hnc;

public final class Utils
{
  public static final String a;
  public static final String b;
  public static final String c;
  public static final String d;
  public static final List<String> e = Collections.singletonList("");
  private static final File f = new File("/data/anr/traces.txt");
  private static volatile Boolean g;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory());
    localStringBuilder.append("/SafeKids_logs/");
    a = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(a);
    localStringBuilder.append("ucp_error/");
    b = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(a);
    localStringBuilder.append("anr/");
    c = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(a);
    localStringBuilder.append("stacktrace/");
    d = localStringBuilder.toString();
  }
  
  public static float a(float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, hnc.i().getResources().getDisplayMetrics());
  }
  
  public static int a(int paramInt)
  {
    return (int)a(paramInt);
  }
  
  public static ResolveInfo a(PackageManager paramPackageManager)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.addCategory("android.intent.category.DEFAULT");
    return paramPackageManager.resolveActivity(localIntent, 65536);
  }
  
  public static RectF a(RectF paramRectF)
  {
    return new RectF(a(paramRectF.left), a(paramRectF.top), a(paramRectF.right), a(paramRectF.bottom));
  }
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Android_");
    localStringBuilder.append(Build.VERSION.RELEASE);
    return localStringBuilder.toString();
  }
  
  public static String a(int paramInt, Context paramContext)
  {
    return a(paramInt, paramContext.getResources());
  }
  
  public static String a(int paramInt, Resources paramResources)
  {
    try
    {
      paramResources = car.a(paramResources.openRawResource(paramInt));
      return paramResources;
    }
    catch (Exception paramResources)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String a(long paramLong)
  {
    int k = (int)(paramLong / 1000L);
    int j = k / 60;
    int i = j / 60;
    k %= 60;
    j %= 60;
    if (i > 0) {
      return String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) });
    }
    return String.format(Locale.getDefault(), "%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(k) });
  }
  
  public static String a(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      paramContext = localObject;
      if (paramString != null) {
        paramContext = paramString.versionName;
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String a(Context paramContext, String paramString, int paramInt)
  {
    if (paramString == null) {
      return "";
    }
    int i = paramString.toLowerCase(Locale.US).indexOf("<body");
    if (i >= 0)
    {
      paramInt = paramContext.getResources().getColor(paramInt);
      paramContext = new StringBuilder();
      paramContext.append("<style>body{background-color: #");
      paramContext.append(String.format("%06X", new Object[] { Integer.valueOf(paramInt) }).substring(2));
      paramContext.append(";}</style>");
      paramContext = paramContext.toString();
      return new StringBuilder(paramString).insert(i, paramContext).toString();
    }
    return paramString;
  }
  
  public static String a(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    if (paramBitmap != null)
    {
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
      paramBitmap = Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 0);
    }
    else
    {
      paramBitmap = null;
    }
    try
    {
      localByteArrayOutputStream.close();
      return paramBitmap;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return paramBitmap;
  }
  
  private static String a(String paramString, int paramInt)
  {
    paramString = paramString.split("#");
    if (paramString.length > paramInt) {
      return paramString[paramInt];
    }
    return "";
  }
  
  @Deprecated
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    return ckd.a(paramString1, paramString2, paramString3);
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    if ((i(paramActivity.getApplicationContext())) && (paramBoolean)) {
      paramActivity.finish();
    }
  }
  
  public static void a(Context paramContext, IBinder paramIBinder)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramIBinder, 0);
  }
  
  public static void a(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 0);
  }
  
  public static void a(Point paramPoint)
  {
    WindowManager localWindowManager = (WindowManager)hnc.i().getSystemService("window");
    if (localWindowManager != null)
    {
      localWindowManager.getDefaultDisplay().getSize(paramPoint);
      return;
    }
    paramPoint.set(0, 0);
  }
  
  @SuppressLint({"PrivateApi"})
  public static void a(ActionBar paramActionBar, boolean paramBoolean)
  {
    try
    {
      paramActionBar.getClass().getDeclaredMethod("setShowHideAnimationEnabled", new Class[] { Boolean.TYPE }).invoke(paramActionBar, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception paramActionBar) {}
  }
  
  public static void a(View paramView)
  {
    paramView.setFilterTouchesWhenObscured(true);
  }
  
  public static void a(String paramString)
  {
    if (paramString == null) {
      return;
    }
    Intent localIntent = new Intent("android.intent.action.DELETE");
    localIntent.setData(Uri.fromParts("package", paramString, null));
    localIntent.addFlags(268435456);
    hnc.i().startActivity(localIntent);
  }
  
  @Deprecated
  public static <T> void a(Collection<? extends T> paramCollection, Predicate<? super T> paramPredicate)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (!paramPredicate.apply(paramCollection.next())) {
        paramCollection.remove();
      }
    }
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = a(paramContext, "com.android.chrome");
    if (paramContext != null)
    {
      paramContext = paramContext.split("\\.");
      if ((paramContext.length != 0) && (Integer.parseInt(paramContext[0]) < 45)) {
        return true;
      }
    }
    return false;
  }
  
  public static float b(float paramFloat)
  {
    return paramFloat / (hnc.i().getResources().getDisplayMetrics().densityDpi / 160.0F);
  }
  
  public static String b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = paramContext.getPackageInfo(paramString, 0);
      if (paramString != null)
      {
        paramContext = paramContext.getApplicationLabel(paramString.applicationInfo).toString();
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String b(String paramString)
  {
    return a(paramString, 1).toUpperCase(Locale.getDefault());
  }
  
  public static void b(Context paramContext)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(1, 1);
  }
  
  public static void b(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).restartInput(paramView);
  }
  
  public static boolean b()
  {
    Object localObject = (ConnectivityManager)hnc.i().getSystemService("connectivity");
    boolean bool2 = false;
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      boolean bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((NetworkInfo)localObject).isConnectedOrConnecting()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public static String c(String paramString)
  {
    return a(paramString, 2).toUpperCase(Locale.getDefault());
  }
  
  public static void c()
  {
    try
    {
      killParasiteProcessesNative();
      return;
    }
    catch (Exception localException) {}
  }
  
  public static boolean c(Context paramContext)
  {
    if (g == null) {
      g = Boolean.valueOf(paramContext.getResources().getBoolean(2131034117));
    }
    return g.booleanValue();
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Old package name should not be null");
    }
    try
    {
      paramContext = ((DevicePolicyManager)paramContext.getSystemService("device_policy")).getActiveAdmins();
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          boolean bool = ((ComponentName)paramContext.next()).getPackageName().equals(paramString);
          if (bool) {
            return true;
          }
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  private static String d(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    int i = paramString.lastIndexOf('.');
    if (i == -1) {
      return paramString;
    }
    if (i == paramString.length() - 1) {
      return "";
    }
    return paramString.substring(i);
  }
  
  public static boolean d()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
  
  public static boolean d(Context paramContext)
  {
    if (c(paramContext)) {
      return paramContext.getResources().getBoolean(2131034116);
    }
    return false;
  }
  
  public static void e(Context paramContext)
  {
    String str = o(paramContext);
    if (str != null)
    {
      if (c(paramContext, str))
      {
        e(str);
        return;
      }
      a(str);
    }
  }
  
  private static void e(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Package name should not be null");
    }
    Context localContext = hnc.i();
    paramString = localContext.getPackageManager().getLaunchIntentForPackage(paramString);
    try
    {
      localContext.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      Log.e("KidSafe", "Failed to launch old package");
      paramString.printStackTrace();
    }
  }
  
  public static boolean e()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean f()
  {
    return KpcSettings.getGeneralSettings().getProductMode() == GeneralSettingsSection.ProductMode.CHILD_MODE;
  }
  
  public static boolean f(Context paramContext)
  {
    String str = o(paramContext);
    if (str != null) {
      return c(paramContext, str);
    }
    return false;
  }
  
  public static boolean g()
  {
    return KpcSettings.getGeneralSettings().getProductMode() == GeneralSettingsSection.ProductMode.PARENT_MODE;
  }
  
  public static boolean g(Context paramContext)
  {
    return o(paramContext) != null;
  }
  
  public static Set<String> h(Context paramContext)
  {
    Object localObject = (InputMethodManager)paramContext.getSystemService("input_method");
    HashSet localHashSet = new HashSet();
    if (localObject != null)
    {
      localObject = ((InputMethodManager)localObject).getEnabledInputMethodList().iterator();
      while (((Iterator)localObject).hasNext()) {
        localHashSet.add(((InputMethodInfo)((Iterator)localObject).next()).getPackageName());
      }
      paramContext = a(paramContext.getPackageManager()).activityInfo;
      if (paramContext != null) {
        localHashSet.remove(paramContext.packageName);
      }
    }
    return localHashSet;
  }
  
  public static boolean h()
  {
    return KpcSettings.f().d().booleanValue();
  }
  
  public static boolean i(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addFlags(268435456);
    localIntent.addCategory("android.intent.category.HOME");
    PackageManager localPackageManager = paramContext.getPackageManager();
    if ((localPackageManager.queryIntentActivities(localIntent, 0).size() <= 0) && (a(localPackageManager) == null)) {
      return false;
    }
    paramContext.startActivity(localIntent);
    return true;
  }
  
  public static boolean j(Context paramContext)
  {
    return GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0;
  }
  
  public static boolean k(Context paramContext)
  {
    boolean bool1;
    if ((fts.d(paramContext)) && (hnc.M().a()) && (hnc.N().a()) && ((!hnc.au().a().b()) || (hnc.au().a().c() == IAutoStartManager.AutoStartState.ALLOW)) && ((!hnc.au().b().b()) || (hnc.au().b().c() == IProtectAppManager.ProtectAppState.ALLOW)) && ((!hnc.au().c().b()) || (hnc.au().c().c() == ILockTasksManager.LockedState.ALLOW))) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    boolean bool2 = bool1;
    if (e())
    {
      if ((bool1) && (hnc.Z().c())) {
        return true;
      }
      bool2 = false;
    }
    return bool2;
  }
  
  private static native int killParasiteProcessesNative();
  
  public static boolean l(Context paramContext)
  {
    return (fts.d(paramContext)) && (hnc.M().a());
  }
  
  public static boolean m(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    int i = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (i >= 23)
    {
      if (paramContext.getLockTaskModeState() == 2) {
        bool = true;
      }
      return bool;
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return paramContext.isInLockTaskMode();
    }
    return false;
  }
  
  public static void n(Context paramContext)
  {
    Intent localIntent = new Intent("android.settings.SETTINGS");
    localIntent.setFlags(335609856);
    paramContext.startActivity(localIntent);
  }
  
  private static String o(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str;
    ApplicationInfo localApplicationInfo;
    do
    {
      Iterator localIterator1 = e.iterator();
      Iterator localIterator2;
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext()) {
          break;
        }
        str = (String)localIterator1.next();
        localIterator2 = localPackageManager.getInstalledApplications(0).iterator();
      }
      localApplicationInfo = (ApplicationInfo)localIterator2.next();
    } while ((!localApplicationInfo.packageName.equalsIgnoreCase(str)) || (d(paramContext.getApplicationInfo().manageSpaceActivityName).equals(d(localApplicationInfo.manageSpaceActivityName))));
    return str;
    return null;
  }
}
