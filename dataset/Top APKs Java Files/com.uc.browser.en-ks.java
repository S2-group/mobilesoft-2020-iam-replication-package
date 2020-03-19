import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public final class ks
{
  private static float a = 0.0F;
  
  public static int a(Context paramContext)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness");
      return i;
    }
    catch (Exception paramContext) {}
    return 255;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    if ((a < 1.0E-5F) && (a > -1.0E-5F)) {
      a = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(a * paramFloat + 0.5F);
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    if (paramActivity == null) {
      return;
    }
    a(paramActivity.getWindow(), paramBoolean);
  }
  
  public static void a(Window paramWindow, boolean paramBoolean)
  {
    if (paramWindow == null) {
      return;
    }
    if (paramBoolean)
    {
      paramWindow.clearFlags(1024);
      return;
    }
    paramWindow.setFlags(1024, 1024);
  }
  
  public static boolean a(Activity paramActivity)
  {
    if (paramActivity == null) {}
    do
    {
      do
      {
        return false;
        paramActivity = paramActivity.getWindow();
      } while (paramActivity == null);
      paramActivity = paramActivity.getAttributes();
    } while ((paramActivity == null) || ((paramActivity.flags & 0x400) != 1024));
    return true;
  }
  
  public static boolean a(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    if ((paramActivity == null) || (paramIntent == null)) {
      return false;
    }
    try
    {
      paramActivity.startActivityForResult(paramIntent, paramInt);
      return true;
    }
    catch (Exception paramIntent)
    {
      Toast.makeText(paramActivity, ts.b().a(645), 0).show();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext.startActivity(paramIntent);
      return true;
    }
    catch (Exception paramIntent)
    {
      Toast.makeText(paramContext, ts.b().a(645), 0).show();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    if ((paramContext == null) || (paramContext.isEmpty())) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((ApplicationInfo)paramContext.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public static void b(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getSystemService("statusbar");
      if (Build.VERSION.SDK_INT <= 16) {}
      for (paramContext = localObject.getClass().getMethod("collapse", new Class[0]);; paramContext = localObject.getClass().getMethod("collapsePanels", new Class[0]))
      {
        paramContext.invoke(localObject, new Object[0]);
        return;
      }
      return;
    }
    catch (Exception paramContext) {}
  }
}
