package play.amusing.net.excellentcleaner.utils;

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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.LruCache;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class b
{
  public static Bitmap a(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    Object localObject2;
    try
    {
      File localFile = new File(paramString);
      localObject1 = localObject3;
      localObject2 = localObject4;
      if (paramString.endsWith(".mp4"))
      {
        localObject1 = localObject3;
        localObject2 = localObject4;
        if (localFile.length() <= 104857600L)
        {
          localObject1 = localObject3;
          paramString = ThumbnailUtils.createVideoThumbnail(paramString, paramInt3);
          localObject1 = paramString;
          paramString = ThumbnailUtils.extractThumbnail(paramString, paramInt1, paramInt2, 2);
          return paramString;
        }
      }
    }
    catch (Exception paramString)
    {
      d.a(paramString);
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public static String a(long paramLong)
  {
    if (paramLong > 86400000L)
    {
      paramLong /= 86400000L;
      if (paramLong == 1L) {
        return String.format(Locale.getDefault(), "%d day", new Object[] { Long.valueOf(paramLong) });
      }
      return String.format(Locale.getDefault(), "%d days", new Object[] { Long.valueOf(paramLong) });
    }
    if (paramLong > 3600000L)
    {
      paramLong /= 3600000L;
      if (paramLong == 1L) {
        return String.format(Locale.getDefault(), "%d hour", new Object[] { Long.valueOf(paramLong) });
      }
      return String.format(Locale.getDefault(), "%d hours", new Object[] { Long.valueOf(paramLong) });
    }
    if (paramLong > 60000L)
    {
      i = (int)paramLong / 60000;
      if (i == 1) {
        return String.format(Locale.getDefault(), "%d minute", new Object[] { Integer.valueOf(i) });
      }
      return String.format(Locale.getDefault(), "%d minutes", new Object[] { Integer.valueOf(i) });
    }
    int i = (int)paramLong / 1000;
    return String.format(Locale.getDefault(), "%d seconds", new Object[] { Integer.valueOf(i) });
  }
  
  public static List<PackageInfo> a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      d.a(paramContext);
    }
    return null;
  }
  
  public static List<UsageStats> a(Context paramContext, int paramInt)
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
        d.a(paramContext);
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
      d.a(paramActivityManager);
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
          if (paramString1.equals(paramContext.getString(2131689665)))
          {
            paramString2 = o.class;
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
        d.a(paramContext);
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
      d.a(paramContext);
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
    d.a("get package info exception.");
    return null;
  }
  
  @RequiresApi(api=26)
  public static void b(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    ShortcutManager localShortcutManager = (ShortcutManager)paramContext.getSystemService("shortcut");
    if (localShortcutManager.isRequestPinShortcutSupported())
    {
      if (paramString1.equals(paramContext.getString(2131689665))) {
        localObject = o.class;
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
    Object localObject2 = null;
    Object localObject1 = localObject2;
    for (;;)
    {
      int i;
      try
      {
        int k = play.amusing.net.excellentcleaner.constant.b.q.length;
        localObject1 = localObject2;
        ArrayList localArrayList = new ArrayList(k);
        int j = 0;
        i = 0;
        if (i < k)
        {
          localObject1 = localObject2;
          if (!k(paramContext, play.amusing.net.excellentcleaner.constant.b.q[i]))
          {
            localObject1 = localObject2;
            localArrayList.add(play.amusing.net.excellentcleaner.constant.b.q[i]);
          }
        }
        else
        {
          localObject1 = localObject2;
          paramContext = new String[localArrayList.size()];
          i = j;
          localObject1 = paramContext;
          localObject2 = paramContext;
          if (i < localArrayList.size())
          {
            localObject1 = paramContext;
            paramContext[i] = ((String)localArrayList.get(i));
            i += 1;
            continue;
          }
          return localObject2;
        }
      }
      catch (Exception paramContext)
      {
        d.a(paramContext);
        localObject2 = localObject1;
      }
      i += 1;
    }
  }
  
  public static PackageInfo c(Context paramContext, String paramString)
  {
    try
    {
      if (new File(paramString).exists())
      {
        paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 1);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      d.a(paramContext);
    }
    return null;
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
      d.a(paramContext);
    }
    return "1.0";
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
      d.a(paramContext);
    }
    return 0;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    return b(paramContext, paramString) != null;
  }
  
  public static Bitmap e(Context paramContext, String paramString)
  {
    Canvas localCanvas = null;
    Object localObject1 = localCanvas;
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return null;
      }
      localObject1 = localCanvas;
      Object localObject2 = (Bitmap)play.amusing.net.excellentcleaner.d.d.a().b().get(paramString);
      if (localObject2 != null) {
        return localObject2;
      }
      localObject1 = localCanvas;
      localObject2 = b(paramContext, paramString);
      if (localObject2 == null) {
        return null;
      }
      localObject1 = localCanvas;
      localObject2 = ((PackageInfo)localObject2).applicationInfo.loadIcon(paramContext.getPackageManager());
      localObject1 = localCanvas;
      if ((localObject2 instanceof BitmapDrawable))
      {
        localObject1 = localCanvas;
        paramContext = ((BitmapDrawable)localObject2).getBitmap();
        localObject1 = paramContext;
        paramContext = Bitmap.createScaledBitmap(paramContext, 40, 40, true);
      }
      else
      {
        localObject1 = localCanvas;
        paramContext = Bitmap.createBitmap(((Drawable)localObject2).getIntrinsicWidth(), ((Drawable)localObject2).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        localObject1 = paramContext;
        localCanvas = new Canvas(paramContext);
        localObject1 = paramContext;
        ((Drawable)localObject2).setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
        localObject1 = paramContext;
        ((Drawable)localObject2).draw(localCanvas);
      }
      localObject1 = paramContext;
      play.amusing.net.excellentcleaner.d.d.a().b().put(paramString, paramContext);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      d.a(paramContext);
    }
    return localObject1;
  }
  
  public static boolean e(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageName();
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), play.amusing.net.excellentcleaner.constant.b.s);
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
      d.a(paramContext);
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
      d.a(paramContext);
    }
    return 0L;
  }
  
  public static Drawable f(Context paramContext, String paramString)
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
          d.a(paramString);
        }
      }
      return paramContext.getResources().getDrawable(2131230889);
    }
    catch (Exception paramString)
    {
      d.a(paramString);
    }
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
      d.a(paramContext);
    }
    return 1L;
  }
  
  public static String g(Context paramContext, String paramString)
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
      d.a(paramContext);
    }
    return null;
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
      d.a(paramContext);
    }
    return 0L;
  }
  
  public static void h(Context paramContext, String paramString)
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
      d.a(paramContext);
    }
  }
  
  public static void i(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.addFlags(268435456);
      localIntent.setAction("android.intent.action.VIEW");
      File localFile = new File(paramString);
      paramString = Uri.fromFile(localFile);
      String str = paramContext.getPackageName();
      if (Build.VERSION.SDK_INT >= 24)
      {
        paramString = FileProvider.getUriForFile(paramContext, str, localFile);
        localIntent.addFlags(1);
      }
      localIntent.setDataAndType(paramString, "application/vnd.android.package-archive");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      d.a(paramContext);
    }
  }
  
  public static void j(Context paramContext, String paramString)
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
  
  public static boolean k(Context paramContext, String paramString)
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
        d.a(paramContext);
      }
      return false;
    }
    return false;
    label78:
    return false;
  }
  
  public static boolean l(Context paramContext, String paramString)
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
      d.a(paramContext);
    }
    return false;
  }
  
  public static List<String> m(Context paramContext, String paramString)
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
      d.a(paramString);
      paramString = paramContext;
    }
    return paramString;
  }
}
