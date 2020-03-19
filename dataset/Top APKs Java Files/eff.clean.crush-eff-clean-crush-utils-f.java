package eff.clean.crush.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.annotation.RequiresApi;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.LruCache;
import eff.clean.crush.c.k;
import eff.clean.crush.constant.u;
import eff.clean.crush.ui.activity.ECPrivacyActivity;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class f
{
  public static int a()
  {
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockCountLong();
      return (int)((float)(l - localStatFs.getAvailableBlocksLong()) / (float)l * 100.0F);
    }
    return 0;
  }
  
  public static int a(long paramLong1, long paramLong2)
  {
    return (int)((float)(paramLong2 - paramLong1) / (float)paramLong2 * 100.0F);
  }
  
  public static List a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return null;
  }
  
  public static List a(Context paramContext, int paramInt)
  {
    for (;;)
    {
      try
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          Object localObject1 = (UsageStatsManager)paramContext.getSystemService("usagestats");
          Object localObject2 = Calendar.getInstance();
          ((Calendar)localObject2).setTime(new Date());
          long l = ((Calendar)localObject2).getTimeInMillis();
          ((Calendar)localObject2).add(13, -paramInt);
          localObject1 = ((UsageStatsManager)localObject1).queryUsageStats(4, ((Calendar)localObject2).getTimeInMillis(), l);
          paramInt = ((List)localObject1).size() - 1;
          if (paramInt >= 0)
          {
            localObject2 = (UsageStats)((List)localObject1).get(paramInt);
            String str = ((UsageStats)((List)localObject1).get(paramInt)).getPackageName();
            if ((localObject2.getClass().getDeclaredField("mLaunchCount").getInt(localObject2) == 0) || (str.equals(paramContext.getPackageName()))) {
              break label185;
            }
            if (!a(paramContext, str)) {
              break label180;
            }
            break label185;
            if (i != 0) {
              ((List)localObject1).remove(paramInt);
            }
            paramInt -= 1;
            continue;
          }
          return localObject1;
        }
      }
      catch (Exception paramContext)
      {
        r.a(paramContext);
      }
      return null;
      label180:
      int i = 0;
      continue;
      label185:
      i = 1;
    }
  }
  
  public static void a(ActivityManager paramActivityManager, String paramString)
  {
    try
    {
      if (paramString.indexOf(":") != -1) {
        paramString = paramString.split(":")[0];
      }
      paramActivityManager.killBackgroundProcesses(paramString);
      return;
    }
    catch (Exception paramActivityManager)
    {
      r.a(paramActivityManager);
    }
  }
  
  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    do
    {
      try
      {
        if (Build.VERSION.SDK_INT < 26)
        {
          Intent localIntent = new Intent();
          localIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
          localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString2);
          localIntent.putExtra("duplicate", false);
          localIntent.putExtra("android.intent.extra.shortcut.ICON", BitmapFactory.decodeResource(paramContext.getResources(), paramInt));
          paramString2 = null;
          if (paramString1.equals(paramContext.getString(2131624109)))
          {
            paramString2 = ECPrivacyActivity.class;
            continue;
            paramString1 = new Intent();
            paramString1.setAction("android.intent.action.MAIN");
            paramString1.setClass(paramContext, paramString2);
            paramString1.addCategory("android.intent.category.LAUNCHER");
            localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString1);
            paramContext.sendBroadcast(localIntent);
          }
        }
        else
        {
          b(paramContext, paramInt, paramString1, paramString2);
          return;
        }
      }
      catch (Exception paramContext)
      {
        r.a(paramContext);
        return;
      }
    } while (paramString2 != null);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = b(paramContext, paramString);
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.applicationInfo.flags;
      if ((i & 0x1) != 0) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return false;
  }
  
  public static PackageInfo b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    r.a("get package info exception.");
    return null;
  }
  
  @RequiresApi(api=26)
  public static void b(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    ShortcutManager localShortcutManager = (ShortcutManager)paramContext.getSystemService("shortcut");
    if (localShortcutManager.isRequestPinShortcutSupported())
    {
      if (paramString1.equals(paramContext.getString(2131624109))) {
        localObject = ECPrivacyActivity.class;
      } else {
        localObject = null;
      }
      if (localObject == null) {
        return;
      }
      Object localObject = new Intent(paramContext, (Class)localObject);
      ((Intent)localObject).setAction(paramString1);
      localShortcutManager.requestPinShortcut(new ShortcutInfo.Builder(paramContext, paramString1).setIcon(Icon.createWithResource(paramContext, paramInt)).setShortLabel(paramString2).setIntent((Intent)localObject).build(), null);
    }
  }
  
  public static String[] b(Context paramContext)
  {
    Object localObject = null;
    for (;;)
    {
      int i;
      try
      {
        int k = u.i.length;
        ArrayList localArrayList = new ArrayList(k);
        int j = 0;
        i = 0;
        if (i < k)
        {
          if (i(paramContext, u.i[i])) {
            break label124;
          }
          localArrayList.add(u.i[i]);
          break label124;
        }
        paramContext = new String[localArrayList.size()];
        i = j;
        Context localContext1 = paramContext;
        try
        {
          if (i >= localArrayList.size()) {
            break label121;
          }
          paramContext[i] = ((String)localArrayList.get(i));
          i += 1;
        }
        catch (Exception localException1) {}
        r.a(localException2);
      }
      catch (Exception localException2)
      {
        paramContext = localObject;
      }
      Context localContext2 = paramContext;
      label121:
      return localContext2;
      label124:
      i += 1;
    }
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return "1.0";
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    return b(paramContext, paramString) != null;
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return 0;
  }
  
  public static Bitmap d(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return null;
      }
      localObject1 = localObject2;
      Object localObject3 = (Bitmap)k.a().b().get(paramString);
      if (localObject3 != null) {
        return localObject3;
      }
      localObject1 = localObject2;
      localObject3 = b(paramContext, paramString);
      if (localObject3 == null) {
        return null;
      }
      localObject1 = localObject2;
      localObject3 = ((PackageInfo)localObject3).applicationInfo.loadIcon(paramContext.getPackageManager());
      localObject1 = localObject2;
      if ((localObject3 instanceof BitmapDrawable))
      {
        localObject1 = localObject2;
        paramContext = ((BitmapDrawable)localObject3).getBitmap();
        try
        {
          localObject1 = Bitmap.createScaledBitmap(paramContext, 40, 40, true);
          paramContext = (Context)localObject1;
        }
        catch (Exception paramString)
        {
          localObject1 = paramContext;
          paramContext = paramString;
          break label201;
        }
      }
      else
      {
        localObject1 = localObject2;
        paramContext = Bitmap.createBitmap(((Drawable)localObject3).getIntrinsicWidth(), ((Drawable)localObject3).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      }
      try
      {
        localObject1 = new Canvas(paramContext);
        ((Canvas)localObject1).setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        ((Drawable)localObject3).setBounds(0, 0, ((Canvas)localObject1).getWidth(), ((Canvas)localObject1).getHeight());
        ((Drawable)localObject3).draw((Canvas)localObject1);
        localObject1 = paramContext;
        k.a().b().put(paramString, paramContext);
        return paramContext;
      }
      catch (Exception paramString)
      {
        localObject1 = paramContext;
        paramContext = paramString;
      }
      r.a(paramContext);
    }
    catch (Exception paramContext) {}
    label201:
    return localObject1;
  }
  
  public static Drawable e(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      Object localObject = localPackageManager.getPackageArchiveInfo(paramString, 1);
      if (localObject != null)
      {
        localObject = ((PackageInfo)localObject).applicationInfo;
        ((ApplicationInfo)localObject).sourceDir = paramString;
        ((ApplicationInfo)localObject).publicSourceDir = paramString;
        try
        {
          paramString = ((ApplicationInfo)localObject).loadIcon(localPackageManager);
          return paramString;
        }
        catch (Exception paramString)
        {
          r.a(paramString);
        }
      }
      return paramContext.getResources().getDrawable(2131165362);
    }
    catch (Exception paramString)
    {
      r.a(paramString);
    }
  }
  
  public static boolean e(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageName();
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), u.v);
      if (!TextUtils.isEmpty(paramContext))
      {
        paramContext = paramContext.split(":");
        int i = 0;
        while (i < paramContext.length)
        {
          ComponentName localComponentName = ComponentName.unflattenFromString(paramContext[i]);
          if (localComponentName != null)
          {
            boolean bool = TextUtils.equals(str, localComponentName.getPackageName());
            if (bool) {
              return true;
            }
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
  }
  
  public static long f(Context paramContext)
  {
    try
    {
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      paramContext.getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.availMem;
      return l;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return 0L;
  }
  
  public static String f(Context paramContext, String paramString)
  {
    try
    {
      paramString = b(paramContext, paramString);
      if (paramString != null)
      {
        paramContext = paramString.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static long g(Context paramContext)
  {
    try
    {
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      paramContext.getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.totalMem;
      return l;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return 1L;
  }
  
  public static void g(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setAction("android.intent.action.DELETE");
      localIntent.addCategory("android.intent.category.DEFAULT");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(paramString);
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
  }
  
  public static long h(Context paramContext)
  {
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).firstInstallTime;
      return l;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return 0L;
  }
  
  public static void h(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    if (Build.VERSION.SDK_INT >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
    }
    else if (Build.VERSION.SDK_INT <= 8)
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra("com.android.settings.ApplicationPkgName", paramString);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static boolean i(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (paramContext != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      try
      {
        if (Build.VERSION.SDK_INT >= 23)
        {
          if (paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).targetSdkVersion >= 23)
          {
            if (paramContext.checkSelfPermission(paramString) != 0) {
              break label78;
            }
            return true;
          }
          int i = PermissionChecker.checkSelfPermission(paramContext, paramString);
          if (i == 0) {
            bool = true;
          }
          return bool;
        }
      }
      catch (Exception paramContext)
      {
        r.a(paramContext);
      }
      return false;
    }
    return false;
    label78:
    return false;
  }
  
  public static boolean j(Context paramContext, String paramString)
  {
    try
    {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      if (paramString == null) {
        return false;
      }
      if (d(paramContext) > 10) {
        paramString.addFlags(32768);
      }
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      r.a(paramContext);
    }
    return false;
  }
  
  public static List k(Context paramContext, String paramString)
  {
    try
    {
      String[] arrayOfString = paramContext.getPackageManager().getPackageInfo(paramString, 4096).requestedPermissions;
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        paramContext = new ArrayList();
        int i = 0;
        for (;;)
        {
          paramString = paramContext;
          try
          {
            if (i >= arrayOfString.length) {
              return paramString;
            }
            paramContext.add(arrayOfString[i]);
            i += 1;
          }
          catch (Exception paramString)
          {
            break label68;
          }
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      paramContext = null;
      label68:
      r.a(paramString);
      paramString = paramContext;
    }
    return paramString;
  }
}
