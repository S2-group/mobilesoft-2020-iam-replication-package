package com.justalk.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import com.d.a.a;
import com.google.b.a.j;
import com.juphoon.justalk.JApplication;
import com.juphoon.justalk.m.u;
import com.juphoon.justalk.m.w;
import com.juphoon.justalk.settings.x;
import com.justalk.i;
import com.justalk.m;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class dh
{
  private static DisplayMetrics a;
  private static Bitmap b = null;
  
  public static int a(Activity paramActivity)
  {
    com.d.a.c localC = new a(paramActivity).a();
    int j = localC.d();
    int i = j;
    if (j == 0)
    {
      i = j;
      if (!localC.c())
      {
        i = j;
        if (Settings.Secure.getInt(paramActivity.getContentResolver(), "dev_force_show_navbar", 0) != 0)
        {
          paramActivity = paramActivity.getResources();
          int k = paramActivity.getIdentifier("navigation_bar_height", "dimen", "android");
          i = j;
          if (k > 0) {
            i = paramActivity.getDimensionPixelSize(k);
          }
        }
      }
    }
    return i;
  }
  
  public static Point a(ViewGroup paramViewGroup, View paramView)
  {
    Object localObject = paramView.getParent();
    int i = paramView.getLeft();
    int j = paramView.getTop();
    paramView = (View)localObject;
    int k = i;
    for (i = j; (paramView != paramViewGroup) && ((paramView instanceof ViewGroup)); i = j + i)
    {
      localObject = (ViewGroup)paramView;
      k += ((ViewGroup)localObject).getLeft();
      j = ((ViewGroup)localObject).getTop();
      paramView = paramView.getParent();
    }
    return new Point(k, i);
  }
  
  public static File a()
  {
    String str1 = ar.y();
    new File(str1).mkdirs();
    String str2 = e(ar.e);
    String str3 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
    return new File(str1 + File.separator + str2 + "_" + str3 + ".png");
  }
  
  public static File a(String paramString1, String paramString2)
  {
    int i = 1;
    for (;;)
    {
      File localFile = new File(paramString1 + File.separator + String.format(paramString2, new Object[] { Integer.valueOf(i) }));
      if (!localFile.exists()) {
        return localFile;
      }
      i += 1;
    }
  }
  
  @SuppressLint({"NewApi"})
  public static String a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("mtc_data_directory", 0);
    Object localObject1 = localSharedPreferences.getString("mtc_data_directory_key", null);
    if ("external".equals(localObject1))
    {
      if (!"mounted".equals(Environment.getExternalStorageState())) {
        return null;
      }
      paramContext = Environment.getExternalStorageDirectory().getAbsolutePath();
      localSharedPreferences.edit().putString("mtc_data_directory_key", paramContext).commit();
    }
    for (;;)
    {
      return paramContext;
      if (!"data".equals(localObject1)) {
        break;
      }
      paramContext = paramContext.getFilesDir().getAbsolutePath();
      localSharedPreferences.edit().putString("mtc_data_directory_key", paramContext).commit();
    }
    String str;
    boolean bool;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      str = Environment.getExternalStorageState();
      bool = true;
      if (Build.VERSION.SDK_INT > 10) {
        bool = Environment.isExternalStorageEmulated();
      }
      if ((!"mounted".equals(str)) || ((!bool) && (Environment.isExternalStorageRemovable()))) {
        break label307;
      }
    }
    label302:
    label307:
    for (Object localObject2 = paramContext.getExternalFilesDir(null);; localObject2 = null)
    {
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (new StatFs(((File)localObject2).getPath()).getAvailableBlocks() >= 100) {}
      }
      else
      {
        paramContext = paramContext.getFilesDir();
        localObject1 = paramContext;
        if (paramContext == null)
        {
          localObject1 = paramContext;
          if ("mounted".equals(str)) {
            if (!bool)
            {
              localObject1 = paramContext;
              if (Environment.isExternalStorageRemovable()) {
                break label302;
              }
            }
          }
        }
      }
      for (paramContext = Environment.getExternalStorageDirectory();; paramContext = (Context)localObject1)
      {
        if (paramContext != null)
        {
          paramContext = paramContext.getAbsolutePath();
          localSharedPreferences.edit().putString("mtc_data_directory_key", paramContext).commit();
          break;
          localObject2 = android.support.v4.os.c.a(new File((String)localObject1));
          paramContext = (Context)localObject1;
          if (((String)localObject2).equals("mounted")) {
            break;
          }
          paramContext = (Context)localObject1;
          if (((String)localObject2).equals("unknown")) {
            break;
          }
        }
        paramContext = null;
        break;
      }
    }
  }
  
  public static String a(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      paramContext = localObject;
      if (localApplicationInfo != null)
      {
        paramContext = localObject;
        if (localApplicationInfo.metaData != null) {
          paramContext = localApplicationInfo.metaData.getString(paramString);
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String a(InputStream paramInputStream)
  {
    localStringBuilder = new StringBuilder();
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localStringBuilder.append(new String(arrayOfByte, 0, i));
      }
      return localStringBuilder.toString();
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramActivity = paramActivity.getWindow();
      paramActivity.addFlags(Integer.MIN_VALUE);
      paramActivity.setStatusBarColor(paramInt);
    }
    while (Build.VERSION.SDK_INT < 19) {
      return;
    }
    b(paramActivity, true);
    paramActivity = new a(paramActivity);
    paramActivity.a(true);
    paramActivity.a(paramInt);
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    paramActivity = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = paramActivity.getAttributes();
    if (paramBoolean)
    {
      localLayoutParams.flags |= 0x400;
      paramActivity.setAttributes(localLayoutParams);
      return;
    }
    localLayoutParams.flags &= 0xFBFF;
    paramActivity.setAttributes(localLayoutParams);
  }
  
  public static void a(AppCompatActivity paramAppCompatActivity)
  {
    paramAppCompatActivity.a((Toolbar)paramAppCompatActivity.findViewById(i.toolbar));
    paramAppCompatActivity.g().a(true);
    paramAppCompatActivity.g().b(true);
  }
  
  public static void a(View paramView, boolean paramBoolean)
  {
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int i = 0;
      while (i < paramView.getChildCount())
      {
        a(paramView.getChildAt(i), paramBoolean);
        i += 1;
      }
    }
    if (paramView != null) {
      paramView.setEnabled(paramBoolean);
    }
  }
  
  public static void a(boolean paramBoolean, View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      a(paramVarArgs[i], paramBoolean);
      i += 1;
    }
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static boolean a(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        if (!a(arrayOfFile[i])) {
          return false;
        }
        i += 1;
      }
    }
    return paramFile.delete();
  }
  
  public static boolean a(String paramString)
  {
    List localList = ((ActivityManager)JApplication.a.getSystemService("activity")).getRunningTasks(1);
    return (!localList.isEmpty()) && (((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getClassName().equals(paramString));
  }
  
  public static Bitmap b()
  {
    if (b == null) {
      b = BitmapFactory.decodeResource(JApplication.a.getResources(), m.ic_launcher);
    }
    return b;
  }
  
  public static String b(Context paramContext)
  {
    return a(paramContext) + File.separator + "temp";
  }
  
  public static void b(Activity paramActivity)
  {
    View localView = paramActivity.findViewById(i.content);
    if ((localView instanceof FrameLayout)) {
      ((FrameLayout)localView).setForeground(paramActivity.getResources().getDrawable(com.justalk.h.ab_solid_shadow_holo));
    }
  }
  
  public static void b(Activity paramActivity, int paramInt)
  {
    Window localWindow = paramActivity.getWindow();
    if (Build.VERSION.SDK_INT >= 19) {
      localWindow.getDecorView().setSystemUiVisibility(1792);
    }
    a(paramActivity, 0);
    localWindow.setBackgroundDrawableResource(paramInt);
  }
  
  @TargetApi(19)
  private static void b(Activity paramActivity, boolean paramBoolean)
  {
    paramActivity = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = paramActivity.getAttributes();
    if (paramBoolean) {}
    for (localLayoutParams.flags |= 0x4000000;; localLayoutParams.flags &= 0xFBFFFFFF)
    {
      paramActivity.setAttributes(localLayoutParams);
      return;
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(0);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          boolean bool = TextUtils.equals(((PackageInfo)paramContext.next()).packageName, paramString);
          if (bool) {
            return true;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean b(String paramString)
  {
    j localJ = j.a();
    try
    {
      boolean bool = localJ.b(localJ.a(paramString, null));
      return bool;
    }
    catch (com.google.b.a.h paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static Bitmap c(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindow().getDecorView().getRootView();
    paramActivity.setDrawingCacheEnabled(true);
    paramActivity.buildDrawingCache();
    return paramActivity.getDrawingCache();
  }
  
  public static String c(Context paramContext)
  {
    File localFile = paramContext.getExternalCacheDir();
    if ((localFile == null) || (new StatFs(localFile.getPath()).getAvailableBlocks() < 100)) {
      return paramContext.getCacheDir().getAbsolutePath();
    }
    String str = android.support.v4.os.c.a(localFile);
    if ((str.equals("mounted")) || (str.equals("unknown"))) {
      return localFile.getAbsolutePath();
    }
    return paramContext.getCacheDir().getAbsolutePath();
  }
  
  public static int d(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = JApplication.a.getResources().getDisplayMetrics();
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    int k = a(paramActivity);
    return (i * 16 / 9 - (j + k)) / 2;
  }
  
  public static String d(Context paramContext)
  {
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      File localFile2 = Environment.getExternalStorageDirectory();
      File localFile1;
      if (localFile2 != null)
      {
        localFile1 = localFile2;
        if (new StatFs(localFile2.getPath()).getAvailableBlocks() >= 100) {}
      }
      else
      {
        localFile1 = paramContext.getFilesDir();
      }
      return localFile1.getAbsolutePath();
    }
    return paramContext.getFilesDir().getAbsolutePath();
  }
  
  public static String e(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramContext = Integer.toString(((PackageManager)localObject).getPackageInfo(paramContext.getPackageName(), 0).versionCode);
      localObject = paramContext;
      if (paramContext == null) {
        localObject = "";
      }
      return localObject;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static String f(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject).getPackageInfo(paramContext.getPackageName(), 0).versionName;
      localObject = paramContext;
      if (paramContext == null) {
        localObject = "";
      }
      return localObject;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static boolean g(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {
      return false;
    }
    paramContext = paramContext.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.processName.equals(paramContext)) && (localRunningAppProcessInfo.importance == 100)) {
        return true;
      }
    }
    return false;
  }
  
  public static String h(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == i) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return "";
  }
  
  public static boolean i(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType() != 0;
  }
  
  public static int j(Context paramContext)
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).numActivities;
  }
  
  public static void k(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
    Configuration localConfiguration = localResources.getConfiguration();
    localConfiguration.locale = l(paramContext);
    localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
  }
  
  public static Locale l(Context paramContext)
  {
    paramContext = x.d(paramContext);
    if ((TextUtils.isEmpty(paramContext)) || (paramContext.equals("auto"))) {
      return Locale.getDefault();
    }
    paramContext = paramContext.split(";");
    return new Locale(paramContext[0], paramContext[1]);
  }
  
  public static u m(Context paramContext)
  {
    ArrayList localArrayList = w.a().f();
    paramContext = l(paramContext);
    paramContext = paramContext.getLanguage() + "_" + paramContext.getCountry();
    int i = 0;
    while (i < localArrayList.size())
    {
      u localU = (u)localArrayList.get(i);
      if (paramContext.startsWith(localU.a())) {
        return localU;
      }
      i += 1;
    }
    return null;
  }
  
  public static String n(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getLine1Number();
  }
  
  public static String o(Context paramContext)
  {
    Object localObject = ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso();
    paramContext = (Context)localObject;
    if (localObject != null)
    {
      paramContext = ((String)localObject).replace("null", "");
      localObject = new StringBuilder();
      int i = 0;
      while (i < paramContext.length())
      {
        char c = Character.toUpperCase(paramContext.charAt(i));
        if ((c < 'A') || (c > 'Z')) {
          break;
        }
        ((StringBuilder)localObject).append(c);
        i += 1;
      }
      paramContext = ((StringBuilder)localObject).toString();
    }
    return paramContext;
  }
  
  public static float p(Context paramContext)
  {
    if (a == null)
    {
      a = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(a);
    }
    return a.density;
  }
}
