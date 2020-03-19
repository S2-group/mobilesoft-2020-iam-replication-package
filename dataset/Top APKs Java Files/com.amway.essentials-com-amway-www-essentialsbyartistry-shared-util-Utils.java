package com.amway.www.essentialsbyartistry.shared.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\000\n\002\b\003\030\000 \0032\0020\001:\001\003B\005¢\006\002\020\002¨\006\004"}, d2={"Lcom/amway/www/essentialsbyartistry/shared/util/Utils;", "", "()V", "Companion", "app_release"}, k=1, mv={1, 1, 11})
public final class Utils
{
  public static final Companion Companion = new Companion(null);
  
  public Utils() {}
  
  @Metadata(bv={1, 0, 2}, d1={"\000L\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\007\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\006\n\002\020\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\003\032\0020\0042\006\020\005\032\0020\004J\026\020\006\032\0020\0042\006\020\007\032\0020\b2\006\020\t\032\0020\004J\006\020\n\032\0020\013J\016\020\f\032\0020\r2\006\020\016\032\0020\017J\016\020\020\032\0020\r2\006\020\016\032\0020\017J\016\020\021\032\0020\0222\006\020\023\032\0020\013J\016\020\024\032\0020\0222\006\020\007\032\0020\bJ\026\020\025\032\0020\0222\006\020\007\032\0020\b2\006\020\026\032\0020\013J\026\020\027\032\0020\0042\006\020\007\032\0020\b2\006\020\005\032\0020\004J\026\020\030\032\0020\0312\006\020\032\032\0020\0332\006\020\034\032\0020\rJ\026\020\035\032\0020\0312\006\020\036\032\0020\0372\006\020\034\032\0020\r¨\006 "}, d2={"Lcom/amway/www/essentialsbyartistry/shared/util/Utils$Companion;", "", "()V", "convertDpToPixel", "", "dp", "dpFromPx", "context", "Landroid/content/Context;", "px", "generateFileName", "", "getHeight", "", "windowManager", "Landroid/view/WindowManager;", "getWidth", "isFileExist", "", "fileUrl", "isNetworkAvailable", "isPackageExisted", "targetPackage", "pxFromDp", "setButtonDrawableColor", "", "button", "Landroid/widget/Button;", "color", "setTextViewDrawableColor", "textView", "Landroid/widget/TextView;", "app_release"}, k=1, mv={1, 1, 11})
  public static final class Companion
  {
    private Companion() {}
    
    public final float convertDpToPixel(float paramFloat)
    {
      Resources localResources = Resources.getSystem();
      Intrinsics.checkExpressionValueIsNotNull(localResources, "Resources.getSystem()");
      return Math.round(paramFloat * (localResources.getDisplayMetrics().densityDpi / 160.0F));
    }
    
    public final float dpFromPx(@NotNull Context paramContext, float paramFloat)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      paramContext = paramContext.getResources();
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.resources");
      return paramFloat / paramContext.getDisplayMetrics().density;
    }
    
    @NotNull
    public final String generateFileName()
    {
      return "Ess-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
    }
    
    public final int getHeight(@NotNull WindowManager paramWindowManager)
    {
      Intrinsics.checkParameterIsNotNull(paramWindowManager, "windowManager");
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
      return localDisplayMetrics.heightPixels;
    }
    
    public final int getWidth(@NotNull WindowManager paramWindowManager)
    {
      Intrinsics.checkParameterIsNotNull(paramWindowManager, "windowManager");
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
      return localDisplayMetrics.widthPixels;
    }
    
    public final boolean isFileExist(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "fileUrl");
      return new File(paramString).length() > 0L;
    }
    
    public final boolean isNetworkAvailable(@NotNull Context paramContext)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      paramContext = paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
      }
      paramContext = ((ConnectivityManager)paramContext).getActiveNetworkInfo();
      return (paramContext != null) && (paramContext.isConnectedOrConnecting());
    }
    
    public final boolean isPackageExisted(@NotNull Context paramContext, @NotNull String paramString)
    {
      boolean bool2 = false;
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      Intrinsics.checkParameterIsNotNull(paramString, "targetPackage");
      paramContext = paramContext.getPackageManager();
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.packageManager");
      paramContext = paramContext.getInstalledApplications(0);
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "pm.getInstalledApplications(0)");
      paramContext = paramContext.iterator();
      do
      {
        bool1 = bool2;
        if (!paramContext.hasNext()) {
          break;
        }
      } while (!Intrinsics.areEqual(((ApplicationInfo)paramContext.next()).packageName, paramString));
      boolean bool1 = true;
      return bool1;
    }
    
    public final float pxFromDp(@NotNull Context paramContext, float paramFloat)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      paramContext = paramContext.getResources();
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.resources");
      return paramContext.getDisplayMetrics().density * paramFloat;
    }
    
    public final void setButtonDrawableColor(@NotNull Button paramButton, int paramInt)
    {
      Intrinsics.checkParameterIsNotNull(paramButton, "button");
      Drawable[] arrayOfDrawable = paramButton.getCompoundDrawables();
      int j = arrayOfDrawable.length;
      int i = 0;
      while (i < j)
      {
        Drawable localDrawable = arrayOfDrawable[i];
        if (localDrawable != null) {
          localDrawable.setColorFilter((ColorFilter)new PorterDuffColorFilter(ContextCompat.getColor(paramButton.getContext(), paramInt), PorterDuff.Mode.SRC_IN));
        }
        i += 1;
      }
    }
    
    public final void setTextViewDrawableColor(@NotNull TextView paramTextView, int paramInt)
    {
      Intrinsics.checkParameterIsNotNull(paramTextView, "textView");
      Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
      int j = arrayOfDrawable.length;
      int i = 0;
      while (i < j)
      {
        Drawable localDrawable = arrayOfDrawable[i];
        if (localDrawable != null) {
          localDrawable.setColorFilter((ColorFilter)new PorterDuffColorFilter(ContextCompat.getColor(paramTextView.getContext(), paramInt), PorterDuff.Mode.SRC_IN));
        }
        i += 1;
      }
    }
  }
}
