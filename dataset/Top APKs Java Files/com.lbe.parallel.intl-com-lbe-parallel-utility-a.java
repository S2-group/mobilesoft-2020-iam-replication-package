package com.lbe.parallel.utility;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.v4.app.y.a;
import android.support.v7.app.c;
import android.support.v7.app.c.a;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.lbe.doubleagent.client.IOUtils;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import com.lbe.multidroid.service.h;
import com.lbe.parallel.DAApp;
import com.lbe.parallel.aw;
import com.lbe.parallel.f.a;
import com.lbe.parallel.fy;
import com.lbe.parallel.fz;
import com.lbe.parallel.gt;
import com.lbe.parallel.jd;
import com.lbe.parallel.jp;
import com.lbe.parallel.jv;
import com.lbe.parallel.model.EmptyPackageInfo;
import com.lbe.parallel.receiver.NotificationOnClickReceiver;
import com.lbe.parallel.ui.HomeActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class a
{
  public a()
  {
    new aw(10);
  }
  
  public a(byte paramByte)
  {
    new aw();
  }
  
  public static float a(float paramFloat)
  {
    return Math.round(paramFloat * 10.0F) / 10.0F;
  }
  
  private static int a(int paramInt, String paramString)
  {
    try
    {
      Object localObject = DAApp.o().getSystemService("appops");
      paramInt = ((Integer)Class.forName("android.app.AppOpsManager").getDeclaredMethod("checkOp", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localObject, new Object[] { Integer.valueOf(24), Integer.valueOf(paramInt), paramString })).intValue();
      return paramInt;
    }
    catch (Exception paramString) {}
    return 2;
  }
  
  public static int a(Activity paramActivity)
  {
    paramActivity = paramActivity.getTheme().obtainStyledAttributes(new int[] { 2130772048 });
    int i = paramActivity.getDimensionPixelSize(0, 0);
    paramActivity.recycle();
    return i;
  }
  
  public static ObjectAnimator a(View paramView, int paramInt, Interpolator paramInterpolator, final Runnable paramRunnable, float... paramVarArgs)
  {
    paramView = ObjectAnimator.ofFloat(paramView, View.ALPHA, paramVarArgs).setDuration(paramInt);
    paramView.setInterpolator(paramInterpolator);
    paramView.addListener(new gt()
    {
      public final void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        this.a.removeListener(this);
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
    });
    return paramView;
  }
  
  public static ObjectAnimator a(View paramView, Interpolator paramInterpolator, final Runnable paramRunnable, float... paramVarArgs)
  {
    paramView = ObjectAnimator.ofPropertyValuesHolder(paramView, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(View.SCALE_X, paramVarArgs), PropertyValuesHolder.ofFloat(View.SCALE_Y, paramVarArgs) });
    paramView.setInterpolator(paramInterpolator);
    paramView.setDuration(300L);
    paramView.addListener(new gt()
    {
      public final void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        this.a.removeListener(this);
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
    });
    return paramView;
  }
  
  public static PackageInfo a(Context paramContext, String paramString)
  {
    paramContext = new PackageManagerWrapper(paramContext);
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Bitmap a(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = BitmapFactory.decodeResource(paramResources, paramInt);
      return paramResources;
    }
    catch (Exception paramResources)
    {
      paramResources.printStackTrace();
      return null;
    }
    catch (OutOfMemoryError paramResources) {}
    return null;
  }
  
  public static Bitmap a(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      paramResources = BitmapFactory.decodeResource(paramResources, paramInt1);
      if (paramResources != null)
      {
        if ((paramResources.getWidth() == paramInt2) && (paramResources.getHeight() == paramInt3)) {
          return paramResources;
        }
        paramResources = Bitmap.createScaledBitmap(paramResources, paramInt2, paramInt3, true);
        return paramResources;
      }
    }
    catch (OutOfMemoryError paramResources)
    {
      return null;
    }
    catch (Exception paramResources) {}
    return null;
  }
  
  public static Bitmap a(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {}
    for (Bitmap.Config localConfig = paramBitmap.getConfig();; localConfig = null) {
      return a(paramBitmap, localConfig);
    }
  }
  
  public static Bitmap a(Bitmap paramBitmap, Bitmap.Config paramConfig)
  {
    int j = -1;
    if (paramBitmap == null) {
      return null;
    }
    int i = paramBitmap.getWidth();
    int k = paramBitmap.getHeight();
    if (paramConfig != null)
    {
      paramConfig = Bitmap.createBitmap(i, k, paramConfig);
      Canvas localCanvas = new Canvas(paramConfig);
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
      localCanvas.save(31);
      localCanvas.restore();
      if (paramConfig == null) {
        break label110;
      }
      i = paramConfig.getWidth();
      label72:
      if (paramConfig == null) {
        break label115;
      }
      i = paramConfig.getHeight();
      label81:
      if (paramConfig == null) {
        break label120;
      }
    }
    label110:
    label115:
    label120:
    for (paramBitmap = paramConfig.getConfig();; paramBitmap = null)
    {
      i = j;
      if (paramConfig != null) {
        i = paramConfig.getByteCount();
      }
      return paramConfig;
      paramConfig = Bitmap.Config.ARGB_8888;
      break;
      i = -1;
      break label72;
      i = -1;
      break label81;
    }
  }
  
  public static Bitmap a(View paramView)
  {
    Bitmap.Config localConfig = null;
    int j = -1;
    if (paramView == null) {
      return null;
    }
    try
    {
      paramView.destroyDrawingCache();
      paramView.setDrawingCacheBackgroundColor(0);
      if (!paramView.isDrawingCacheEnabled()) {
        paramView.setDrawingCacheEnabled(true);
      }
      paramView = paramView.getDrawingCache();
    }
    catch (Throwable paramView)
    {
      for (;;)
      {
        paramView = null;
        continue;
        int i = -1;
        continue;
        i = -1;
      }
    }
    if (paramView != null)
    {
      i = paramView.getWidth();
      if (paramView == null) {
        break label87;
      }
      i = paramView.getHeight();
      if (paramView != null) {
        localConfig = paramView.getConfig();
      }
      i = j;
      if (paramView != null) {
        i = paramView.getByteCount();
      }
      return paramView;
    }
  }
  
  public static Drawable a(Context paramContext, fz paramFz)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      Resources localResources = paramContext.getPackageManager().getResourcesForApplication(paramFz.a());
      localObject1 = localObject3;
      if (localResources != null)
      {
        localObject1 = localObject2;
        if (Build.VERSION.SDK_INT >= 21)
        {
          localObject1 = localObject2;
          paramContext = localResources.getDrawableForDensity(paramFz.c(), paramContext.getResources().getDisplayMetrics().densityDpi, null);
          localObject1 = paramContext;
          if (paramContext == null)
          {
            localObject1 = paramContext;
            return localResources.getDrawable(paramFz.c());
          }
        }
        else
        {
          localObject1 = localObject2;
          paramContext = localResources.getDrawable(paramFz.c());
          return paramContext;
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localObject1;
  }
  
  public static Drawable a(PackageInfo paramPackageInfo)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramPackageInfo == null) {}
    for (;;)
    {
      return localObject1;
      if ((paramPackageInfo instanceof EmptyPackageInfo)) {
        continue;
      }
      localObject1 = DAApp.o();
      fz localFz = com.lbe.multidroid.service.b.a((Context)localObject1).b().c(((DAApp)localObject1).q(), paramPackageInfo.packageName);
      if (localFz != null) {
        return a((Context)localObject1, localFz);
      }
      localObject1 = ((DAApp)localObject1).getPackageManager();
      try
      {
        paramPackageInfo = paramPackageInfo.applicationInfo.loadIcon((PackageManager)localObject1);
        localObject1 = paramPackageInfo;
        if (paramPackageInfo != null) {
          continue;
        }
        return DAApp.o().getResources().getDrawable(17301651);
      }
      catch (Exception paramPackageInfo)
      {
        for (;;)
        {
          paramPackageInfo.printStackTrace();
          paramPackageInfo = localObject2;
        }
      }
      catch (OutOfMemoryError paramPackageInfo)
      {
        for (;;)
        {
          paramPackageInfo.printStackTrace();
          paramPackageInfo = localObject2;
        }
      }
    }
  }
  
  public static ImageLoader.ImageContainer a(ImageView paramImageView, String paramString, int paramInt1, int paramInt2)
  {
    if ((paramImageView == null) || (TextUtils.isEmpty(paramString))) {
      return null;
    }
    return a(paramImageView, paramString, paramInt1, paramInt2, paramInt2);
  }
  
  public static ImageLoader.ImageContainer a(ImageView paramImageView, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramImageView == null) || (TextUtils.isEmpty(paramString))) {
      return null;
    }
    paramImageView.setTag(paramString);
    return android.support.v4.app.b.b().get(paramString, ImageLoader.getImageListener(paramImageView, paramInt1, paramInt1), paramInt2, paramInt3);
  }
  
  public static ImageLoader.ImageContainer a(String paramString, ImageLoader.ImageListener paramImageListener)
  {
    f.a.b(DAApp.o());
    return f.a.g().get(paramString, paramImageListener);
  }
  
  public static ImageLoader.ImageContainer a(String paramString, ap paramAp, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    return android.support.v4.app.b.b().get(paramString, new ah(paramString, paramAp), paramInt, paramInt);
  }
  
  public static CharSequence a(String paramString)
  {
    PackageManagerWrapper localPackageManagerWrapper = new PackageManagerWrapper(DAApp.o());
    try
    {
      paramString = b(localPackageManagerWrapper.getPackageInfo(paramString, 0));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return "";
  }
  
  public static String a()
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)DAApp.o().getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getClassName();
  }
  
  public static String a(List<PackageInfo> paramList)
  {
    String str = DAApp.o().getResources().getString(2131100046);
    StringBuffer localStringBuffer = new StringBuffer();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localStringBuffer.append(b((PackageInfo)paramList.next())).append(str);
    }
    if (localStringBuffer.length() > 0) {
      return localStringBuffer.substring(0, localStringBuffer.length() - 1);
    }
    return "";
  }
  
  public static String a(Set<String> paramSet)
  {
    StringBuffer localStringBuffer = new StringBuffer("[");
    if ((paramSet != null) && (paramSet.size() > 0))
    {
      paramSet = paramSet.iterator();
      while (paramSet.hasNext()) {
        localStringBuffer.append((String)paramSet.next()).append(";");
      }
    }
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
  
  public static String a(String[] paramArrayOfString)
  {
    StringBuffer localStringBuffer = new StringBuffer("[");
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        localStringBuffer.append(paramArrayOfString[i]).append(";");
        i += 1;
      }
    }
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
  
  public static ArrayList<PackageInfo> a(Context paramContext, int paramInt)
  {
    String[] arrayOfString = com.lbe.multidroid.service.b.a(paramContext).c().f(paramInt);
    paramContext = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    boolean bool = z.a().a("show_gms_packages_in_home");
    if (arrayOfString != null)
    {
      int i = arrayOfString.length;
      paramInt = 0;
      while (paramInt < i)
      {
        Object localObject = arrayOfString[paramInt];
        try
        {
          localObject = paramContext.getPackageInfo((String)localObject, 0);
          if ((localObject != null) && ((bool) || (!fy.c.contains(((PackageInfo)localObject).packageName))))
          {
            if (localArrayList.size() >= 4) {
              break;
            }
            localArrayList.add(localObject);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
        paramInt += 1;
      }
    }
    return localArrayList;
  }
  
  public static List<ImageLoader.ImageContainer> a(List<ImageLoader.ImageContainer> paramList, ImageLoader.ImageContainer paramImageContainer)
  {
    if ((paramList != null) && (paramImageContainer != null)) {
      paramList.add(paramImageContainer);
    }
    return paramList;
  }
  
  public static void a(Context paramContext)
  {
    String str;
    if (ad.j()) {
      str = jd.a().b("last_error_report");
    }
    for (;;)
    {
      HashMap localHashMap;
      if (!TextUtils.isEmpty(str))
      {
        android.support.v4.app.b.a(paramContext);
        if (!ad.j()) {
          break label283;
        }
        jd.a().a("last_error_report", "");
        localHashMap = new HashMap();
        localHashMap.put("packagename", paramContext.getPackageName());
        localHashMap.put("version_code", "185");
        localHashMap.put("version_name", "3.1.6210");
        localHashMap.put("channel", "B1");
        localHashMap.put("device_model", Build.MODEL);
        localHashMap.put("device_version", Build.VERSION.RELEASE);
        localHashMap.put("device_fingerprint", Build.FINGERPRINT);
        localHashMap.put("sdkInt", String.valueOf(Build.VERSION.SDK_INT));
        localHashMap.put("country", Locale.getDefault().getCountry());
        localHashMap.put("language", Locale.getDefault().getLanguage());
        localHashMap.put("androidId", ad.a(paramContext));
        localHashMap.put("stacktrace", str);
      }
      try
      {
        localHashMap.put("imei", q.a(MessageDigest.getInstance("SHA-1").digest(ad.k(paramContext).getBytes())).toUpperCase());
        new Thread(new j(paramContext, localHashMap)).start();
        return;
        str = z.a().c("last_error_report");
        continue;
        label283:
        z.a().f("last_error_report");
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localHashMap.put("imei", ad.k(paramContext));
        }
      }
    }
  }
  
  public static void a(Context paramContext, int paramInt, String paramString, com.lbe.parallel.ui.upgrade.a paramA, boolean paramBoolean)
  {
    Object localObject = null;
    View localView = LayoutInflater.from(paramContext).inflate(2130903253, null);
    TextView localTextView = (TextView)localView.findViewById(2131558550);
    localTextView.setText(paramString);
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        paramString = new c.a(paramContext).b(localView).a(false).a(2131099806, new ai(paramA, paramContext)).b();
        paramContext = paramString;
        continue;
      }
      catch (Exception paramContext)
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 17)
          {
            localTextView.setTextDirection(5);
            paramContext = paramString;
          }
          if (paramContext != null)
          {
            paramContext.getWindow().setType(2003);
            paramContext.show();
            paramContext = paramContext.a(-1);
            if (paramContext != null) {
              paramContext.setTextColor(Color.parseColor("#00B2EE"));
            }
          }
          return;
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            paramContext = paramString;
          }
        }
        paramContext = paramContext;
        paramContext = null;
      }
      paramString = localObject;
      try
      {
        paramA = new c.a(paramContext).b(localView).a(false).a(2131099806, new ak(paramA, paramContext)).b(2131099805, new aj(paramA, paramInt)).b();
        paramContext = paramA;
        paramString = paramA;
        if (Build.VERSION.SDK_INT >= 17)
        {
          paramString = paramA;
          localTextView.setTextDirection(5);
          paramContext = paramA;
        }
      }
      catch (Exception paramContext)
      {
        paramContext = paramString;
      }
    }
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", paramUri));
  }
  
  public static void a(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:" + paramString1));
    paramString1.putExtra("android.intent.extra.SUBJECT", paramString2);
    paramString1.putExtra("android.intent.extra.TEXT", "");
    paramContext.startActivity(Intent.createChooser(paramString1, paramString2));
  }
  
  public static void a(View paramView, Rect paramRect)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    paramRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
  }
  
  public static void a(View paramView, Runnable paramRunnable)
  {
    a(paramView, true, paramRunnable);
  }
  
  public static void a(View paramView, String paramString)
  {
    int j = 0;
    Rect localRect = new Rect();
    a(paramView, localRect);
    paramView = paramView.getContext().getApplicationContext();
    int k = f(paramView);
    Toast localToast = Toast.makeText(paramView, paramString, 0);
    Object localObject = localToast.getView().findViewById(16908299);
    if ((localObject != null) && ((localObject instanceof TextView)))
    {
      localObject = ((TextView)localObject).getPaint();
      j = (int)((TextPaint)localObject).measureText(paramString) + (ad.a(paramView, 24) << 1);
    }
    for (int i = (int)(((TextPaint)localObject).getFontMetrics().descent - ((TextPaint)localObject).getFontMetrics().ascent) + (ad.a(paramView, 16) << 1);; i = 0)
    {
      j = localRect.left + localRect.width() / 2 - j / 2;
      i = localRect.top - i - k;
      if (e(paramView)) {
        localToast.setGravity(8388661, j, i);
      }
      for (;;)
      {
        localToast.show();
        return;
        localToast.setGravity(8388659, j, i);
      }
    }
  }
  
  public static void a(View paramView, boolean paramBoolean, Runnable paramRunnable)
  {
    if (paramView == null) {
      return;
    }
    paramView.getViewTreeObserver().addOnPreDrawListener(new am(paramView, paramRunnable, paramBoolean));
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().resolveActivity(paramIntent, 0);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.activityInfo != null)
      {
        bool1 = bool2;
        if (paramContext.activityInfo.exported) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean a(Context paramContext, String paramString, int paramInt)
  {
    Object localObject1 = new PackageManagerWrapper(paramContext);
    try
    {
      Object localObject2 = ((PackageManagerWrapper)localObject1).getPackageInfo(paramString, 0);
      if (localObject2 != null)
      {
        localObject2 = ((PackageInfo)localObject2).applicationInfo.loadLabel((PackageManager)localObject1);
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2)) {
          localObject1 = paramString;
        }
        localObject2 = paramContext.getResources();
        localObject1 = ((Resources)localObject2).getString(2131099984, new Object[] { localObject1 });
        NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
        Intent localIntent = new Intent(paramContext, HomeActivity.class);
        localIntent.putExtra("EXTRA_PACKAGE_NAME", paramString);
        localNotificationManager.notify(paramInt, new y.a(paramContext).a(2130837760).a().a((CharSequence)localObject1).b(((Resources)localObject2).getString(2131099983)).a(PendingIntent.getActivity(paramContext, 0, localIntent, 134217728)).b());
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int j;
    int i;
    if (paramArrayOfString != null)
    {
      bool1 = bool2;
      if (paramArrayOfString.length > 0)
      {
        j = paramArrayOfString.length;
        i = 0;
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (TextUtils.equals(paramArrayOfString[i], paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static int b(String[] paramArrayOfString, String paramString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        if (paramArrayOfString[i].contains(paramString.trim().toLowerCase())) {
          return i;
        }
        i += 1;
      }
    }
    return -1;
  }
  
  public static long b(String paramString)
  {
    long[] arrayOfLong = new long[1];
    arrayOfLong[0] = 0L;
    IOUtils.scanDir(new v(arrayOfLong), paramString);
    return arrayOfLong[0];
  }
  
  public static PackageInfo b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Bitmap b(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return null;
    }
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Rect localRect = new Rect(0, 0, i, j);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawCircle(i / 2, j / 2, Math.min(i, j) / 2, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  public static CharSequence b(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo instanceof EmptyPackageInfo)) {
      return null;
    }
    PackageManagerWrapper localPackageManagerWrapper = new PackageManagerWrapper(DAApp.o());
    try
    {
      paramPackageInfo = paramPackageInfo.applicationInfo.loadLabel(localPackageManagerWrapper);
      return paramPackageInfo;
    }
    catch (Exception paramPackageInfo)
    {
      paramPackageInfo.printStackTrace();
    }
    return "";
  }
  
  public static String b(List<CharSequence> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer("");
    if (paramList.size() > 0)
    {
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        localStringBuffer.append((CharSequence)paramList.get(i));
        if (i < j - 1) {
          localStringBuffer.append(",");
        }
        i += 1;
      }
    }
    return localStringBuffer.toString();
  }
  
  public static void b(View paramView, Runnable paramRunnable)
  {
    paramView.getViewTreeObserver().addOnGlobalLayoutListener(new an(paramView, paramRunnable));
  }
  
  public static boolean b(Context paramContext)
  {
    boolean bool = true;
    try
    {
      int i = a(Process.myUid(), paramContext.getPackageName());
      if (i == 0) {
        return true;
      }
      if (i == 3)
      {
        if (android.support.v4.content.b.a(paramContext, "android.permission.SYSTEM_ALERT_WINDOW") == 0) {
          return bool;
        }
        return false;
      }
      if (i == 2)
      {
        if (Build.VERSION.SDK_INT >= 19) {
          return false;
        }
        i = android.support.v4.content.b.a(paramContext, "android.permission.SYSTEM_ALERT_WINDOW");
        if (i == 0) {
          return bool;
        }
        return false;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    bool = false;
    return bool;
  }
  
  public static Set<String> c(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = new PackageManagerWrapper(paramContext).getInstalledPackages(0);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (!f.a.a(localPackageInfo.applicationInfo)) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
    }
    return localHashSet;
  }
  
  public static void c(View paramView, Runnable paramRunnable)
  {
    AtomicInteger localAtomicInteger = new AtomicInteger(0);
    paramView.getViewTreeObserver().addOnGlobalLayoutListener(new ao(localAtomicInteger, paramView, paramRunnable));
  }
  
  public static void c(List<ImageLoader.ImageContainer> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ImageLoader.ImageContainer localImageContainer = (ImageLoader.ImageContainer)localIterator.next();
        try
        {
          localImageContainer.cancelRequest();
        }
        catch (Exception localException) {}
      }
      paramList.clear();
    }
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setFlags(268435456);
    localIntent.setData(Uri.fromParts("package", paramString, null));
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean c(String paramString)
  {
    return com.lbe.multidroid.service.b.a(DAApp.o()).c().g(DAApp.o().q(), paramString);
  }
  
  public static byte[] c(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return null;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    paramBitmap = localByteArrayOutputStream.toByteArray();
    try
    {
      localByteArrayOutputStream.close();
      return paramBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramBitmap;
  }
  
  public static Set<String> d(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Set localSet1 = k(paramContext);; localSet1 = j(paramContext))
    {
      Set localSet2;
      if (localSet1 != null)
      {
        localSet2 = localSet1;
        if (localSet1.size() > 0) {}
      }
      else
      {
        localSet2 = i(paramContext);
      }
      return localSet2;
    }
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    return new PackageManagerWrapper(paramContext).getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean e(Context paramContext)
  {
    return (Build.VERSION.SDK_INT >= 17) && (paramContext.getResources().getConfiguration().getLayoutDirection() == 1);
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = new PackageManagerWrapper(paramContext);
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static int f(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return g(paramContext);
    }
    return 0;
  }
  
  public static void f(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    localIntent.addFlags(268435456);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext) {}
  }
  
  public static int g(Context paramContext)
  {
    int i = 0;
    Resources localResources = paramContext.getResources();
    int j = localResources.getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = localResources.getDimensionPixelSize(j);
    }
    j = i;
    if (i == 0) {
      j = paramContext.getResources().getDimensionPixelSize(2131230771);
    }
    return j;
  }
  
  public static void g(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static int h(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    int i = g(paramContext);
    int j = ad.b(paramContext, 2131230899);
    int k = ad.b(paramContext, 2131230894);
    i = localDisplayMetrics.heightPixels - i - j - k;
    j = localDisplayMetrics.heightPixels;
    return i;
  }
  
  public static boolean h(Context paramContext, String paramString)
  {
    Object localObject1 = new PackageManagerWrapper(paramContext);
    try
    {
      if (((PackageManagerWrapper)localObject1).getPackageInfo("com.android.vending", 0) != null)
      {
        Object localObject2 = paramContext.getPackageName();
        localObject1 = paramContext.getResources();
        String str = ((Resources)localObject1).getString(2131099921);
        NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse("market://details?id=" + (String)localObject2));
        localIntent.addFlags(268435456);
        localObject2 = new Intent(paramContext, NotificationOnClickReceiver.class);
        ((Intent)localObject2).putExtra("EXTRA_REAL_INTENT", localIntent);
        ((Intent)localObject2).putExtra("EXTRA_NOTIFICATION_TYPE", 1);
        if (Build.VERSION.SDK_INT >= 21) {
          localNotificationManager.notify(2333, new y.a(paramContext).a(2130837780).a().a(str + paramString).b(((Resources)localObject1).getString(2131099922)).a(PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject2, 134217728)).b());
        }
        for (;;)
        {
          jv.h("event_notify_upgrade");
          break;
          localNotificationManager.notify(2333, new y.a(paramContext).a(2130837780).a().a(str + paramString).b(((Resources)localObject1).getString(2131099922)).a(PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject2, 134217728)).b());
        }
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
  }
  
  private static Set<String> i(Context paramContext)
  {
    Iterator localIterator = null;
    HashSet localHashSet = null;
    try
    {
      Object localObject = new jp(paramContext).e();
      if (((Set)localObject).size() > 0) {
        localHashSet = new HashSet();
      }
      int i;
      localException1.printStackTrace();
    }
    catch (Exception localException1)
    {
      try
      {
        paramContext = new PackageManagerWrapper(paramContext);
        localIterator = ((Set)localObject).iterator();
        do
        {
          do
          {
            if (!localIterator.hasNext()) {
              break;
            }
            localObject = (String)localIterator.next();
          } while ((((String)localObject).contains("com.lbe.parallel")) || (f.a.a(paramContext.getApplicationInfo((String)localObject, 0))));
          localHashSet.add(localObject);
          i = localHashSet.size();
        } while (i < 5);
        return localHashSet;
      }
      catch (Exception localException3)
      {
        for (;;)
        {
          paramContext = localException1;
          Exception localException2 = localException3;
        }
      }
      localException1 = localException1;
      paramContext = localIterator;
    }
    return paramContext;
  }
  
  public static void i(Context paramContext, String paramString)
  {
    paramString = a(paramContext, paramString);
    if (paramString == null) {}
    while ((fy.b.contains(paramString.packageName)) || (!aa.a(paramContext, aa.a(paramString), true))) {
      return;
    }
    aa.b(paramContext, paramString);
    new Handler(Looper.getMainLooper()).postDelayed(new al(paramString, paramContext), 3000L);
  }
  
  /* Error */
  private static Set<String> j(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 392
    //   4: invokevirtual 754	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   7: checkcast 394	android/app/ActivityManager
    //   10: iconst_5
    //   11: iconst_2
    //   12: invokevirtual 1283	android/app/ActivityManager:getRecentTasks	(II)Ljava/util/List;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull +160 -> 177
    //   20: aload_3
    //   21: invokeinterface 1034 1 0
    //   26: ifle +151 -> 177
    //   29: new 1064	java/util/HashSet
    //   32: dup
    //   33: invokespecial 1065	java/util/HashSet:<init>	()V
    //   36: astore_2
    //   37: new 155	com/lbe/doubleagent/utility/PackageManagerWrapper
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 158	com/lbe/doubleagent/utility/PackageManagerWrapper:<init>	(Landroid/content/Context;)V
    //   45: astore_0
    //   46: aload_3
    //   47: invokeinterface 427 1 0
    //   52: astore_3
    //   53: aload_3
    //   54: invokeinterface 432 1 0
    //   59: ifeq +99 -> 158
    //   62: aload_3
    //   63: invokeinterface 436 1 0
    //   68: checkcast 1285	android/app/ActivityManager$RecentTaskInfo
    //   71: astore 4
    //   73: aload 4
    //   75: getfield 1289	android/app/ActivityManager$RecentTaskInfo:baseIntent	Landroid/content/Intent;
    //   78: ifnull -25 -> 53
    //   81: aload 4
    //   83: getfield 1289	android/app/ActivityManager$RecentTaskInfo:baseIntent	Landroid/content/Intent;
    //   86: invokevirtual 1293	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   89: ifnull -36 -> 53
    //   92: aload 4
    //   94: getfield 1289	android/app/ActivityManager$RecentTaskInfo:baseIntent	Landroid/content/Intent;
    //   97: invokevirtual 1293	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   100: invokevirtual 1294	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   103: astore 4
    //   105: aload 4
    //   107: invokestatic 344	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   110: ifne -57 -> 53
    //   113: aload 4
    //   115: ldc_w 1238
    //   118: invokevirtual 981	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   121: ifne -68 -> 53
    //   124: aload_0
    //   125: aload 4
    //   127: iconst_0
    //   128: invokevirtual 1242	com/lbe/doubleagent/utility/PackageManagerWrapper:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   131: invokestatic 1071	com/lbe/parallel/f$a:a	(Landroid/content/pm/ApplicationInfo;)Z
    //   134: ifne -81 -> 53
    //   137: aload_2
    //   138: aload 4
    //   140: invokeinterface 1072 2 0
    //   145: pop
    //   146: aload_2
    //   147: invokeinterface 463 1 0
    //   152: istore_1
    //   153: iload_1
    //   154: iconst_5
    //   155: if_icmplt -102 -> 53
    //   158: aload_2
    //   159: areturn
    //   160: astore_2
    //   161: aconst_null
    //   162: astore_0
    //   163: aload_2
    //   164: invokevirtual 174	java/lang/Exception:printStackTrace	()V
    //   167: aload_0
    //   168: areturn
    //   169: astore_3
    //   170: aload_2
    //   171: astore_0
    //   172: aload_3
    //   173: astore_2
    //   174: goto -11 -> 163
    //   177: aconst_null
    //   178: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	paramContext	Context
    //   152	4	1	i	int
    //   36	123	2	localHashSet	HashSet
    //   160	11	2	localException1	Exception
    //   173	1	2	localException2	Exception
    //   15	48	3	localObject1	Object
    //   169	4	3	localException3	Exception
    //   71	68	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	16	160	java/lang/Exception
    //   20	37	160	java/lang/Exception
    //   37	53	169	java/lang/Exception
    //   53	153	169	java/lang/Exception
  }
  
  public static void j(Context paramContext, String paramString)
  {
    try
    {
      if (paramContext.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("fb://facewebmodal/f?href=" + paramString)));
        return;
      }
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (Exception localException)
    {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
        return;
      }
      catch (Exception paramContext) {}
    }
  }
  
  private static Set<String> k(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      Object localObject1;
      Object localObject2;
      Object localObject3;
      try
      {
        localObject1 = (UsageStatsManager)paramContext.getSystemService("usagestats");
        long l = System.currentTimeMillis();
        localObject1 = ((UsageStatsManager)localObject1).queryUsageStats(0, l - 3600000L, l);
        if (localObject1 == null) {
          break label274;
        }
        localObject2 = new TreeMap(new w());
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = (UsageStats)((Iterator)localObject1).next();
          ((SortedMap)localObject2).put(Long.valueOf(((UsageStats)localObject3).getLastTimeUsed()), ((UsageStats)localObject3).getPackageName());
          continue;
          paramContext.printStackTrace();
        }
      }
      catch (Exception paramContext)
      {
        localObject1 = null;
      }
      for (;;)
      {
        return localObject1;
        if (((SortedMap)localObject2).isEmpty()) {
          break;
        }
        localObject1 = new HashSet();
        try
        {
          paramContext = new PackageManagerWrapper(paramContext);
          localObject2 = ((SortedMap)localObject2).entrySet().iterator();
          int i;
          do
          {
            do
            {
              if (!((Iterator)localObject2).hasNext()) {
                break;
              }
              localObject3 = (Map.Entry)((Iterator)localObject2).next();
            } while (((((Map.Entry)localObject3).getValue() != null) && (((String)((Map.Entry)localObject3).getValue()).contains("com.lbe.parallel"))) || (f.a.a(paramContext.getApplicationInfo((String)((Map.Entry)localObject3).getValue(), 0))));
            ((Set)localObject1).add(((Map.Entry)localObject3).getValue());
            i = ((Set)localObject1).size();
          } while (i < 5);
          return localObject1;
        }
        catch (Exception paramContext) {}
      }
    }
    label274:
    return null;
  }
  
  public static boolean k(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
  }
}
