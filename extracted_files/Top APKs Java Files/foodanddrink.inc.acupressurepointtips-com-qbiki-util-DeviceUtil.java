package com.qbiki.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import java.util.Iterator;
import java.util.List;

public class DeviceUtil
{
  private static final int TABLET_SMALLEST_SCREEN_DIMENSION = 600;
  private static Boolean mGoogleMapsAvailable = null;
  private static Boolean mPlayStoreInstalled = null;
  
  public DeviceUtil() {}
  
  public static float dpToFloatPx(Context paramContext, float paramFloat)
  {
    return paramFloat * paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static int dpToPx(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    return getScreenSize(paramContext).y;
  }
  
  @TargetApi(9)
  public static int getScreenOrientation(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    int i = paramContext.getDefaultDisplay().getRotation();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    int j = localDisplayMetrics.widthPixels;
    int k = localDisplayMetrics.heightPixels;
    if (((i != 0) && (i != 2)) || ((k > j) || (((i == 1) || (i == 3)) && (j > k))))
    {
      switch (i)
      {
      default: 
        return 1;
      case 0: 
        return 1;
      case 1: 
        return 0;
      case 2: 
        if (Build.VERSION.SDK_INT >= 9) {
          return 9;
        }
        return 1;
      }
      if (Build.VERSION.SDK_INT >= 9) {
        return 8;
      }
      return 0;
    }
    switch (i)
    {
    default: 
      return 0;
    case 0: 
      return 0;
    case 1: 
      if (Build.VERSION.SDK_INT >= 9) {
        return 9;
      }
      return 1;
    case 2: 
      if (Build.VERSION.SDK_INT >= 9) {
        return 8;
      }
      return 0;
    }
    return 1;
  }
  
  @TargetApi(13)
  public static Point getScreenSize(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (Build.VERSION.SDK_INT >= 13)
    {
      Point localPoint = new Point();
      paramContext.getDefaultDisplay().getSize(localPoint);
      return localPoint;
    }
    paramContext = paramContext.getDefaultDisplay();
    return new Point(paramContext.getWidth(), paramContext.getHeight());
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return getScreenSize(paramContext).x;
  }
  
  public static int getSmallestScreenDimension(Context paramContext)
  {
    paramContext = getScreenSize(paramContext);
    return Math.min(paramContext.x, paramContext.y);
  }
  
  public static boolean isGoogleMapsAvailable()
  {
    if (mGoogleMapsAvailable != null) {
      return mGoogleMapsAvailable.booleanValue();
    }
    try
    {
      Class.forName("com.google.android.maps.MapActivity");
      mGoogleMapsAvailable = Boolean.valueOf(true);
      return mGoogleMapsAvailable.booleanValue();
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        mGoogleMapsAvailable = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean isGoogleMapsAvailable(Context paramContext, boolean paramBoolean)
  {
    boolean bool = isGoogleMapsAvailable();
    if ((!bool) && (paramBoolean)) {
      DialogUtil.showAlert(paramContext, 2131296425, 2131296444);
    }
    return bool;
  }
  
  public static boolean isKindleFire()
  {
    return Build.MODEL.equals("Kindle Fire");
  }
  
  public static boolean isPlayStoreAppInstalled(Activity paramActivity)
  {
    if (paramActivity == null) {
      return false;
    }
    if (mPlayStoreInstalled != null) {
      return mPlayStoreInstalled.booleanValue();
    }
    mPlayStoreInstalled = Boolean.valueOf(false);
    paramActivity = paramActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    while (paramActivity.hasNext()) {
      if (((PackageInfo)paramActivity.next()).packageName.equals("com.android.vending")) {
        mPlayStoreInstalled = Boolean.valueOf(true);
      }
    }
    return mPlayStoreInstalled.booleanValue();
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return pxToDp(paramContext, getSmallestScreenDimension(paramContext)) >= 600;
  }
  
  public static boolean isTabletNew(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static int pxToDp(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static float pxToFloatDp(Context paramContext, float paramFloat)
  {
    return paramFloat / paramContext.getResources().getDisplayMetrics().density;
  }
  
  @TargetApi(16)
  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
}
