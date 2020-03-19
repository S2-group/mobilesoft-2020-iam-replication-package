package io.ganguo.library.c;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.List;

public class a
{
  private static final String a = "io.ganguo.library.c.a";
  
  public a() {}
  
  public static int a(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  @TargetApi(19)
  public static void a(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    if (paramActivity != null)
    {
      if (Build.VERSION.SDK_INT < 19) {
        return;
      }
      Object localObject = paramActivity.getTheme().obtainStyledAttributes(new int[] { paramInt });
      try
      {
        paramInt = ((TypedArray)localObject).getColor(0, 0);
        ((TypedArray)localObject).recycle();
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramActivity.getWindow().getDecorView().setSystemUiVisibility(1280);
          paramActivity.getWindow().setStatusBarColor(paramInt);
        }
        else if (Build.VERSION.SDK_INT >= 19)
        {
          a(paramActivity, true, paramInt);
          localObject = new com.a.a.a(paramActivity);
          ((com.a.a.a)localObject).a(true);
          ((com.a.a.a)localObject).b(true);
          ((com.a.a.a)localObject).a(paramInt);
        }
        if ((Build.VERSION.SDK_INT >= 23) && (paramBoolean)) {
          paramActivity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
        return;
      }
      finally
      {
        ((TypedArray)localObject).recycle();
      }
    }
  }
  
  @TargetApi(19)
  private static void a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    if (paramActivity != null)
    {
      if (Build.VERSION.SDK_INT < 19) {
        return;
      }
      paramActivity = paramActivity.getWindow();
      WindowManager.LayoutParams localLayoutParams = paramActivity.getAttributes();
      if (paramBoolean) {
        localLayoutParams.flags |= 0x4000000;
      } else {
        localLayoutParams.flags &= 0xFBFFFFFF;
      }
      paramActivity.setAttributes(localLayoutParams);
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramActivity.clearFlags(201326592);
        paramActivity.getDecorView().setSystemUiVisibility(1792);
        paramActivity.addFlags(Integer.MIN_VALUE);
        paramActivity.setStatusBarColor(paramInt);
      }
      return;
    }
  }
  
  public static void a(Window paramWindow, View paramView)
  {
    if (paramWindow.getCurrentFocus() != null)
    {
      InputMethodManager localInputMethodManager = (InputMethodManager)paramWindow.getContext().getSystemService("input_method");
      if (paramView != null) {
        localInputMethodManager.showSoftInput(paramView, 0);
      }
      localInputMethodManager.showSoftInputFromInputMethod(paramWindow.getCurrentFocus().getWindowToken(), 0);
    }
  }
  
  public static boolean a()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static int b(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static int b(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * paramFloat + 0.5F);
  }
  
  @TargetApi(19)
  public static void b(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    if (paramActivity != null)
    {
      if (Build.VERSION.SDK_INT < 19) {
        return;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramActivity.getWindow().getDecorView().setSystemUiVisibility(1280);
        paramActivity.getWindow().setStatusBarColor(paramInt);
      }
      else if (Build.VERSION.SDK_INT >= 19)
      {
        a(paramActivity, true, paramInt);
        com.a.a.a localA = new com.a.a.a(paramActivity);
        localA.a(true);
        localA.b(true);
        localA.a(paramInt);
      }
      if ((Build.VERSION.SDK_INT >= 23) && (paramBoolean)) {
        paramActivity.getWindow().getDecorView().setSystemUiVisibility(9216);
      }
      return;
    }
  }
  
  private static boolean b()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static int c(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return (int)(localDisplayMetrics.widthPixels / localDisplayMetrics.density);
  }
  
  public static String d(Context paramContext)
  {
    String str = "";
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      Context localContext1 = paramContext;
      try
      {
        if (!TextUtils.isEmpty(paramContext)) {
          return ???;
        }
        return "";
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1) {}
      Log.e(a, "获取当前程序版本名称", localNameNotFoundException2);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      paramContext = str;
    }
    Context localContext2 = paramContext;
    return localContext2;
  }
  
  public static boolean e(Context paramContext)
  {
    boolean bool2 = false;
    if (paramContext != null)
    {
      boolean bool1 = bool2;
      if (f(paramContext))
      {
        bool1 = bool2;
        if (g(paramContext))
        {
          bool1 = bool2;
          if (b()) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    return false;
  }
  
  public static boolean f(Context paramContext)
  {
    if (paramContext == null) {
      return Build.MODEL.startsWith("ONEPLUS");
    }
    return (Build.MODEL.startsWith("ONEPLUS")) || (paramContext.getPackageManager().hasSystemFeature("com.oneplus.software.oos"));
  }
  
  private static boolean g(Context paramContext)
  {
    int i = 0;
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      ArrayList localArrayList = new ArrayList();
      if (paramContext != null) {
        while (i < paramContext.size())
        {
          localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
          i += 1;
        }
      }
      return localArrayList.contains("net.oneplus.commonlogtool");
    }
    return false;
  }
}
