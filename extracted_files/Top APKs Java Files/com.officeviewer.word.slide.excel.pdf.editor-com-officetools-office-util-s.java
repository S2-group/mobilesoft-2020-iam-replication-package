package com.officetools.office.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.input.InputManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Parcelable;
import android.os.Process;
import android.provider.Settings.Global;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.app.Fragment;
import android.support.v4.content.c;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import com.mb.mon.MonetizationUtils;
import com.mobisystems.j;
import com.mobisystems.office.common.R.drawable;
import com.mobisystems.office.common.R.string;
import com.officetools.android.ui.VersionCompatibilityUtils;
import com.officetools.office.Component;
import com.officetools.office.InstallOtherProductActivityFallback;
import com.officetools.office.ah;
import com.officetools.services.FileDownloadService;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public final class s
{
  public static final Executor a = Executors.newCachedThreadPool();
  public static final Executor b = Executors.newSingleThreadExecutor();
  public static final Executor c = Executors.newSingleThreadExecutor();
  public static final Executor d = Executors.newFixedThreadPool(g);
  public static boolean e = false;
  private static final int f;
  private static final int g;
  private static Boolean h;
  private static boolean i = false;
  private static Method j;
  private static Field k;
  private static Field l;
  private static Field m;
  private static boolean n = true;
  
  static
  {
    int i1 = Runtime.getRuntime().availableProcessors();
    f = i1;
    g = i1 << 1;
  }
  
  public static InputManager A()
  {
    Object localObject = com.mobisystems.android.a.get().getSystemService("input");
    if ((localObject instanceof InputManager)) {
      return (InputManager)localObject;
    }
    return null;
  }
  
  public static void B() {}
  
  public static int C()
  {
    try
    {
      int i1 = Settings.System.getInt(com.mobisystems.android.a.get().getContentResolver(), "accelerometer_rotation");
      return i1;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return 1;
  }
  
  public static Boolean D()
  {
    if (!n) {
      return null;
    }
    if (("alcatel".equalsIgnoreCase(Build.MANUFACTURER)) || ("tcl".equalsIgnoreCase(Build.MANUFACTURER))) {}
    try
    {
      Object localObject = com.mobisystems.android.a.get().getSystemService("statusbar");
      boolean bool = ((Boolean)Class.forName("android.app.StatusBarManager").getMethod("getNavigationBarState", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
      return Boolean.valueOf(bool);
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    n = false;
    return null;
  }
  
  private static boolean E()
  {
    if (Build.VERSION.SDK_INT > 24) {
      return true;
    }
    boolean bool = "samsung".equalsIgnoreCase(Build.MANUFACTURER);
    i2 = -1;
    if (bool) {}
    try
    {
      if (Build.VERSION.SDK_INT >= 17) {
        i1 = Settings.Global.getInt(com.mobisystems.android.a.get().getContentResolver(), "navigationbar_hide_bar_enabled");
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException1)
    {
      try
      {
        if (Build.VERSION.SDK_INT < 17) {
          break label90;
        }
        i1 = Settings.System.getInt(com.mobisystems.android.a.get().getContentResolver(), "enable_navbar");
        return 1 == i1;
        localSettingNotFoundException1 = localSettingNotFoundException1;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        for (;;)
        {
          int i1 = i2;
        }
      }
    }
    i1 = -1;
    if (-1 != i1) {
      return true;
    }
    if ("huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
      i1 = i2;
    }
  }
  
  /* Error */
  private static void F()
  {
    // Byte code:
    //   0: getstatic 172	com/officetools/office/util/s:i	Z
    //   3: ifeq +4 -> 7
    //   6: return
    //   7: ldc -82
    //   9: ldc -80
    //   11: iconst_0
    //   12: anewarray 124	java/lang/Class
    //   15: invokevirtual 179	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   18: astore_0
    //   19: aload_0
    //   20: putstatic 181	com/officetools/office/util/s:j	Ljava/lang/reflect/Method;
    //   23: aload_0
    //   24: iconst_1
    //   25: invokevirtual 185	java/lang/reflect/Method:setAccessible	(Z)V
    //   28: getstatic 181	com/officetools/office/util/s:j	Ljava/lang/reflect/Method;
    //   31: invokevirtual 189	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   34: ldc -65
    //   36: invokevirtual 195	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   39: astore_0
    //   40: aload_0
    //   41: putstatic 197	com/officetools/office/util/s:k	Ljava/lang/reflect/Field;
    //   44: aload_0
    //   45: iconst_1
    //   46: invokevirtual 200	java/lang/reflect/Field:setAccessible	(Z)V
    //   49: getstatic 181	com/officetools/office/util/s:j	Ljava/lang/reflect/Method;
    //   52: invokevirtual 189	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   55: ldc -54
    //   57: invokevirtual 195	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   60: astore_0
    //   61: aload_0
    //   62: putstatic 204	com/officetools/office/util/s:l	Ljava/lang/reflect/Field;
    //   65: aload_0
    //   66: iconst_1
    //   67: invokevirtual 200	java/lang/reflect/Field:setAccessible	(Z)V
    //   70: getstatic 181	com/officetools/office/util/s:j	Ljava/lang/reflect/Method;
    //   73: invokevirtual 189	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   76: ldc -50
    //   78: invokevirtual 195	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   81: astore_0
    //   82: aload_0
    //   83: putstatic 208	com/officetools/office/util/s:m	Ljava/lang/reflect/Field;
    //   86: aload_0
    //   87: iconst_1
    //   88: invokevirtual 200	java/lang/reflect/Field:setAccessible	(Z)V
    //   91: iconst_1
    //   92: putstatic 172	com/officetools/office/util/s:i	Z
    //   95: return
    //   96: astore_0
    //   97: goto +14 -> 111
    //   100: astore_0
    //   101: aload_0
    //   102: invokestatic 213	com/officetools/android/ui/e:b	(Ljava/lang/Throwable;)Z
    //   105: pop
    //   106: iconst_1
    //   107: putstatic 172	com/officetools/office/util/s:i	Z
    //   110: return
    //   111: iconst_1
    //   112: putstatic 172	com/officetools/office/util/s:i	Z
    //   115: aload_0
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   18	69	0	localObject1	Object
    //   96	1	0	localObject2	Object
    //   100	16	0	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   7	91	96	finally
    //   101	106	96	finally
    //   7	91	100	java/lang/Throwable
  }
  
  private static boolean G()
  {
    String str;
    boolean bool1;
    if (h == null)
    {
      str = Build.ID;
      boolean bool2 = TextUtils.isEmpty(str);
      bool1 = false;
      if (bool2) {}
    }
    try
    {
      i1 = Integer.valueOf(str.substring(0, str.indexOf('-')).replaceAll("[^0-9]", "")).intValue();
    }
    catch (Throwable localThrowable)
    {
      int i1;
      for (;;) {}
    }
    com.officetools.android.ui.e.b(false);
    i1 = 0;
    if (i1 >= 68) {
      bool1 = true;
    }
    h = Boolean.valueOf(bool1);
    return h.booleanValue();
  }
  
  public static int a(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject.hashCode();
    }
    return 0;
  }
  
  public static long a(String paramString, int paramInt1, int paramInt2)
  {
    Object localObject1 = com.mobisystems.android.a.get().getSharedPreferences("com.officetools.office.update.prefs", 0);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("_update_hour_of_day");
    int i3 = ((SharedPreferences)localObject1).getInt(((StringBuilder)localObject2).toString(), -1);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("_update_minute_of_day");
    int i2 = ((SharedPreferences)localObject1).getInt(((StringBuilder)localObject2).toString(), -1);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("_update_second_of_day");
    int i4 = ((SharedPreferences)localObject1).getInt(((StringBuilder)localObject2).toString(), -1);
    int i1;
    if ((i3 >= paramInt1) && (i3 < paramInt2) && (i2 >= 0) && (i2 <= 59) && (i4 >= 0))
    {
      i1 = i4;
      if (i4 <= 59) {}
    }
    else
    {
      localObject2 = new Random(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
      i3 = ((Random)localObject2).nextInt(paramInt2 - paramInt1) + paramInt1;
      i2 = ((Random)localObject2).nextInt(60);
      i1 = ((Random)localObject2).nextInt(60);
      localObject1 = ((SharedPreferences)localObject1).edit();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("_update_hour_of_day");
      ((SharedPreferences.Editor)localObject1).putInt(((StringBuilder)localObject2).toString(), i3);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("_update_minute_of_day");
      ((SharedPreferences.Editor)localObject1).putInt(((StringBuilder)localObject2).toString(), i2);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("_update_second_of_day");
      ((SharedPreferences.Editor)localObject1).putInt(((StringBuilder)localObject2).toString(), i1);
      ((SharedPreferences.Editor)localObject1).apply();
    }
    localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).set(11, i3);
    ((Calendar)localObject1).set(12, i2);
    ((Calendar)localObject1).set(13, i1);
    if (((Calendar)localObject1).before(Calendar.getInstance())) {
      ((Calendar)localObject1).setTimeInMillis(((Calendar)localObject1).getTimeInMillis() + 86400000L);
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("( hours from ");
    ((StringBuilder)localObject2).append(paramInt1);
    ((StringBuilder)localObject2).append(" to ");
    ((StringBuilder)localObject2).append(paramInt2);
    ((StringBuilder)localObject2).append("): ");
    ((StringBuilder)localObject2).append(com.mobisystems.a.a.b.format(((Calendar)localObject1).getTime()));
    com.officetools.office.e.a.a(3, "AlarmsManager", ((StringBuilder)localObject2).toString());
    return ((Calendar)localObject1).getTimeInMillis();
  }
  
  public static Intent a(Uri paramUri)
  {
    Intent localIntent;
    if (paramUri.getScheme().equals("nook"))
    {
      localIntent = new Intent();
      localIntent.setAction(paramUri.getHost());
      localIntent.putExtra("product_details_ean", paramUri.getQueryParameter("ean"));
      return localIntent;
    }
    if (paramUri.getScheme().equals("tstore"))
    {
      paramUri = paramUri.getQueryParameter("data");
      localIntent = new Intent();
      localIntent.addFlags(536870912);
      localIntent.setClassName("com.skt.skaf.A000Z00040", "com.skt.skaf.A000Z00040.A000Z00040");
      localIntent.setAction("COLLAB_ACTION");
      localIntent.putExtra("com.skt.skaf.COL.URI", paramUri.getBytes());
      localIntent.putExtra("com.skt.skaf.COL.REQUESTER", "A000Z00040");
      return localIntent;
    }
    return new Intent("android.intent.action.VIEW", paramUri);
  }
  
  public static Intent a(Uri paramUri, String paramString)
  {
    if (paramUri != null)
    {
      Intent localIntent1 = new Intent("android.intent.action.EDIT");
      if (Build.VERSION.SDK_INT >= 26)
      {
        localIntent1.putExtra("output", paramUri);
        localIntent1.addFlags(1);
      }
      localIntent1.setDataAndType(paramUri, paramString);
      paramUri = com.mobisystems.f.a.b.t();
      paramString = com.mobisystems.f.a.b.u();
      if (a(j.e) == null)
      {
        if (paramUri == null) {
          return localIntent1;
        }
        if (com.mobisystems.android.a.get().getPackageManager().queryIntentActivities(localIntent1, 0).size() > 0)
        {
          Intent localIntent2 = new Intent(com.mobisystems.android.a.get(), InstallOtherProductActivityFallback.class);
          localIntent2.putExtra("app_name", "PhotoSuite");
          localIntent2.putExtra("url_extra", paramUri);
          localIntent2.putExtra("fallback_url_extra", paramString);
          paramUri = new LabeledIntent(localIntent2, com.mobisystems.android.a.get().getPackageName(), com.mobisystems.android.a.get().getString(R.string.install_sth, new Object[] { "PhotoSuite" }), R.drawable.photosuite);
          paramString = Intent.createChooser(localIntent1, null);
          paramString.putExtra("android.intent.extra.INITIAL_INTENTS", new Parcelable[] { paramUri });
          return paramString;
        }
      }
      else
      {
        return localIntent1;
      }
    }
    return null;
  }
  
  public static Bitmap a(int paramInt)
  {
    Drawable localDrawable = a(null, paramInt);
    if ((localDrawable instanceof BitmapDrawable)) {
      return BitmapFactory.decodeResource(com.mobisystems.android.a.get().getResources(), paramInt);
    }
    Bitmap localBitmap = Bitmap.createBitmap(localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    localDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  public static Bitmap a(int paramInt1, int paramInt2, int paramInt3)
  {
    Bitmap localBitmap1 = a(paramInt1);
    Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, paramInt2, paramInt3, false);
    if (localBitmap2 != localBitmap1) {
      localBitmap1.recycle();
    }
    return localBitmap2;
  }
  
  public static Bitmap a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt2, paramInt3, Bitmap.Config.ARGB_8888);
    localBitmap.eraseColor(paramInt1);
    Drawable localDrawable = a(null, paramInt6).mutate();
    localDrawable.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
    paramInt1 = (paramInt2 - paramInt4) / 2;
    paramInt4 = (paramInt3 - paramInt5) / 2;
    localDrawable.setBounds(paramInt1, paramInt4, paramInt2 - paramInt1, paramInt3 - paramInt4);
    localDrawable.draw(new Canvas(localBitmap));
    return localBitmap;
  }
  
  public static Bitmap a(Bitmap paramBitmap, float paramFloat)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    RectF localRectF = new RectF(0.0F, 0.0F, localBitmap.getWidth(), localBitmap.getHeight());
    Rect localRect = new Rect(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  public static Drawable a(Context paramContext, int paramInt)
  {
    Object localObject = paramContext;
    if (paramContext == null) {
      localObject = com.mobisystems.android.a.get();
    }
    return android.support.v7.a.a.b.b((Context)localObject, paramInt);
  }
  
  public static Drawable a(Drawable paramDrawable)
  {
    if ((paramDrawable != null) && (Build.VERSION.SDK_INT >= 24) && (VersionCompatibilityUtils.o()))
    {
      if (G()) {
        return paramDrawable;
      }
      if (C() == 1) {
        return paramDrawable;
      }
      int i1 = com.mobisystems.util.s.a(32.0F);
      paramDrawable = new LayerDrawable(new Drawable[] { paramDrawable });
      paramDrawable.setLayerInset(0, 0, i1, 0, 0);
      return paramDrawable;
    }
    return paramDrawable;
  }
  
  public static Bundle a(Activity paramActivity, Bundle paramBundle)
  {
    if ((paramActivity != null) && (Build.VERSION.SDK_INT >= 24) && (paramActivity.isInMultiWindowMode())) {
      if (!VersionCompatibilityUtils.o()) {
        return paramBundle;
      }
    }
    try
    {
      i1 = Settings.System.getInt(paramActivity.getContentResolver(), "accelerometer_rotation");
      if (i1 == 1) {
        return paramBundle;
      }
    }
    catch (Throwable localThrowable)
    {
      int i1;
      Object localObject;
      int[] arrayOfInt;
      for (;;) {}
    }
    paramActivity = paramActivity.getWindow().getDecorView();
    localObject = com.mobisystems.android.a.get().getResources().getDisplayMetrics();
    localObject = new Rect(0, 0, ((DisplayMetrics)localObject).widthPixels, ((DisplayMetrics)localObject).heightPixels);
    arrayOfInt = new int[2];
    paramActivity.getLocationOnScreen(arrayOfInt);
    paramActivity = new Rect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramActivity.getWidth(), arrayOfInt[1] + paramActivity.getHeight());
    if ((!((Rect)localObject).equals(paramActivity)) && (!paramActivity.contains((Rect)localObject)))
    {
      if (!paramActivity.equals(new Rect(0, 0, 0, 0)))
      {
        i1 = com.mobisystems.util.s.a(32.0F);
        paramActivity.offset(i1, i1);
        if (!((Rect)localObject).contains(paramActivity)) {
          paramActivity.set(Math.max(paramActivity.left, ((Rect)localObject).left), Math.max(paramActivity.top, ((Rect)localObject).top), Math.min(paramActivity.right, ((Rect)localObject).right), Math.min(paramActivity.bottom, ((Rect)localObject).bottom));
        }
      }
    }
    else {
      paramActivity.set(0, 0, 0, 0);
    }
    paramActivity = ActivityOptions.makeBasic().setLaunchBounds(paramActivity).toBundle();
    if (paramBundle == null) {
      return paramActivity;
    }
    paramBundle.putAll(paramActivity);
    return paramBundle;
    return paramBundle;
  }
  
  public static View.OnClickListener a(View paramView)
  {
    
    try
    {
      paramView = j.invoke(paramView, new Object[0]);
      paramView = (View.OnClickListener)k.get(paramView);
      return paramView;
    }
    catch (Throwable paramView)
    {
      com.officetools.android.ui.e.b(paramView);
    }
    return null;
  }
  
  public static <T> T a(Intent paramIntent, String paramString)
  {
    return paramIntent.getSerializableExtra(paramString);
  }
  
  public static <T> T a(Intent paramIntent, String paramString, T paramT)
  {
    paramIntent = paramIntent.getSerializableExtra(paramString);
    if (paramIntent != null) {
      return paramIntent;
    }
    return paramT;
  }
  
  public static <T> T a(Bundle paramBundle, String paramString)
  {
    if (paramBundle == null) {
      return null;
    }
    return paramBundle.getSerializable(paramString);
  }
  
  public static <T> T a(Bundle paramBundle, String paramString, T paramT)
  {
    if (paramBundle == null) {
      return paramT;
    }
    paramBundle = paramBundle.getSerializable(paramString);
    if (paramBundle != null) {
      return paramBundle;
    }
    return paramT;
  }
  
  public static <T> T a(Callable<T> paramCallable)
  {
    paramCallable = new FutureTask(paramCallable);
    com.mobisystems.android.a.a.post(paramCallable);
    try
    {
      paramCallable = paramCallable.get();
      return paramCallable;
    }
    catch (Throwable paramCallable)
    {
      com.officetools.android.ui.e.b(paramCallable);
    }
    return null;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = (String)Class.forName("android.os.SystemProperties").getMethod("get", new Class[] { String.class, String.class }).invoke(null, new Object[] { paramString1, paramString2 });
      return paramString1;
    }
    catch (Exception paramString1)
    {
      Log.e("GetSystemProperty", "Exception while getting system property: ", paramString1);
    }
    return paramString2;
  }
  
  public static String a(String[] paramArrayOfString)
  {
    int i1 = 0;
    while (i1 < paramArrayOfString.length)
    {
      if (a(paramArrayOfString[i1])) {
        return paramArrayOfString[i1];
      }
      i1 += 1;
    }
    return null;
  }
  
  public static void a()
  {
    System.exit(1);
  }
  
  public static void a(int paramInt, Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramActivity = paramActivity.getWindow();
      if (paramActivity != null) {
        paramActivity.setStatusBarColor(paramInt);
      }
    }
  }
  
  public static void a(Activity paramActivity)
  {
    Intent localIntent = new Intent("com.android.settings.TTS_SETTINGS");
    try
    {
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Throwable paramActivity) {}
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    try
    {
      paramActivity.setRequestedOrientation(paramInt);
      return;
    }
    catch (Throwable paramActivity)
    {
      com.officetools.android.ui.e.a(paramActivity, "translucent-orientation");
    }
  }
  
  public static void a(Activity paramActivity, Intent paramIntent, String paramString)
  {
    if (paramActivity.getIntent() != null) {
      paramActivity = paramActivity.getIntent().getAction();
    } else {
      paramActivity = null;
    }
    Object localObject = paramActivity;
    if (paramActivity == null) {
      localObject = paramString;
    }
    paramIntent.setAction((String)localObject);
  }
  
  public static void a(Activity paramActivity, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    if (!b())
    {
      com.officetools.office.exceptions.b.a(paramActivity, new s.1(paramRunnable2));
      return;
    }
    if (paramRunnable1 != null) {
      paramRunnable1.run();
    }
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = com.mobisystems.registration.e.a(paramString2, paramString3);
    Object localObject = str;
    if (paramString4 != null) {
      localObject = MonetizationUtils.a(str, paramString4);
    }
    paramString4 = Uri.parse((String)localObject);
    if (paramString2.contains("getProductFile.php")) {}
    try
    {
      paramString4 = File.createTempFile("temp", ".apk", FileDownloadService.a);
      localObject = new Intent(paramActivity, FileDownloadService.class);
      paramString2 = Uri.parse(com.mobisystems.registration.e.a(paramString2, paramString3));
      ((Intent)localObject).putExtra("actionMode", 1);
      ((Intent)localObject).putExtra("fileUrl", paramString2.toString());
      ((Intent)localObject).putExtra("fileName", paramString1);
      ((Intent)localObject).putExtra("dstFile", paramString4);
      ((Intent)localObject).putExtra("fileComponent", Component.OfficeFileBrowser);
      c.startForegroundService(paramActivity, (Intent)localObject);
      return;
    }
    catch (IOException paramActivity) {}
    com.mobisystems.util.a.a(paramActivity, a(paramString4));
    return;
  }
  
  public static void a(Window paramWindow)
  {
    if ((paramWindow != null) && (Build.VERSION.SDK_INT >= 24) && (VersionCompatibilityUtils.o()))
    {
      if (G()) {
        return;
      }
      paramWindow = paramWindow.getDecorView();
      int i1;
      if (C() == 0) {
        i1 = com.mobisystems.util.s.a(32.0F);
      } else {
        i1 = 0;
      }
      paramWindow.setPadding(0, i1, 0, 0);
      return;
    }
  }
  
  public static void a(Class paramClass, int paramInt, Intent paramIntent)
  {
    com.mobisystems.android.a.a.post(new t(paramClass, paramInt, paramIntent));
  }
  
  public static void a(String paramString1, Activity paramActivity, String paramString2)
  {
    paramString1 = com.mobisystems.registration.e.a(paramString1, paramString2);
    try
    {
      paramActivity.startActivity(a(Uri.parse(paramString1)));
      return;
    }
    catch (Exception paramString1)
    {
      com.g.a.a.a.a.a.a.a(paramString1);
    }
  }
  
  public static boolean a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    return a(paramActivity, paramString1, paramString2, paramString3, false, null);
  }
  
  public static boolean a(Activity paramActivity, String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    try
    {
      if (b()) {
        a(paramActivity, paramString1, paramString2, paramString3, paramString4);
      } else {
        com.officetools.office.exceptions.b.a(paramActivity, null);
      }
      return true;
    }
    catch (Throwable paramActivity)
    {
      com.g.a.a.a.a.a.a.a(paramActivity);
    }
    return false;
  }
  
  public static boolean a(Dialog paramDialog)
  {
    try
    {
      paramDialog.show();
      return true;
    }
    catch (Throwable paramDialog)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean)
  {
    if ((paramBoolean) && ((Build.MODEL.equalsIgnoreCase("KFSAWA")) || (Build.MODEL.equalsIgnoreCase("KFSAWI")) || (Build.MODEL.equalsIgnoreCase("KFTHWA")) || (Build.MODEL.equalsIgnoreCase("HUAWEI M2-801W")) || (Build.MODEL.equalsIgnoreCase("KFTHWI")))) {
      return false;
    }
    if (VersionCompatibilityUtils.n()) {
      return true;
    }
    paramContext = paramContext.getResources().getConfiguration();
    if ((paramContext.screenLayout & 0xF) >= 3) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    try
    {
      int i1 = paramContext.smallestScreenWidthDp;
      return i1 >= 600;
    }
    catch (Throwable paramContext)
    {
      com.officetools.android.ui.e.b(paramContext);
    }
    return paramBoolean;
  }
  
  public static boolean a(Rect paramRect)
  {
    boolean bool1 = a(com.mobisystems.android.a.get(), true);
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    bool1 = bool2;
    if (E())
    {
      WindowManager localWindowManager = (WindowManager)com.mobisystems.android.a.get().getSystemService("window");
      bool1 = bool2;
      if (localWindowManager != null)
      {
        bool1 = bool2;
        if (localWindowManager.getDefaultDisplay().getRotation() == 3)
        {
          if (paramRect.left > 0) {
            e = true;
          }
          bool1 = e;
        }
      }
    }
    return bool1;
  }
  
  public static boolean a(Fragment paramFragment)
  {
    paramFragment = paramFragment.getArguments();
    if (paramFragment == null) {
      return false;
    }
    return c(paramFragment.getInt("on_back_task_id", -1));
  }
  
  private static boolean a(InputDevice paramInputDevice)
  {
    try
    {
      boolean bool = ((Boolean)InputDevice.class.getMethod("isExternal", new Class[0]).invoke(paramInputDevice, new Object[0])).booleanValue();
      return !bool;
    }
    catch (Throwable paramInputDevice) {}
    return false;
  }
  
  public static boolean a(KeyEvent paramKeyEvent)
  {
    int i1 = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (i1 < 16) {
      return false;
    }
    Object localObject = com.mobisystems.android.a.get().getSystemService("input");
    InputManager localInputManager = null;
    if ((localObject instanceof InputManager)) {
      localInputManager = (InputManager)localObject;
    }
    if (localInputManager != null) {
      bool = a(localInputManager.getInputDevice(paramKeyEvent.getDeviceId()));
    }
    return bool;
  }
  
  public static boolean a(KeyEvent paramKeyEvent, int... paramVarArgs)
  {
    int i2 = paramKeyEvent.getKeyCode();
    int i3 = paramVarArgs.length;
    int i1 = 0;
    while (i1 < i3)
    {
      if (i2 == paramVarArgs[i1]) {
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  public static <T1, T2> boolean a(T1 paramT1, T2 paramT2)
  {
    if (paramT1 == paramT2) {
      return true;
    }
    if ((paramT1 != null) && (paramT2 != null)) {
      return paramT1.equals(paramT2);
    }
    return false;
  }
  
  public static boolean a(Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      paramRunnable.run();
      return true;
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    PackageManager localPackageManager = com.mobisystems.android.a.get().getPackageManager();
    try
    {
      boolean bool = localPackageManager.getApplicationInfo(paramString, 0).enabled;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean a(List<?> paramList1, List<?> paramList2)
  {
    if (paramList1 == paramList2) {
      return true;
    }
    if (paramList1 != null)
    {
      if (paramList2 == null) {
        return false;
      }
      if (paramList1.size() != paramList2.size()) {
        return false;
      }
      return paramList1.equals(paramList2);
    }
    return false;
  }
  
  public static Drawable b(int paramInt)
  {
    return a(null, paramInt);
  }
  
  public static View.OnLongClickListener b(View paramView)
  {
    
    try
    {
      paramView = j.invoke(paramView, new Object[0]);
      paramView = (View.OnLongClickListener)l.get(paramView);
      return paramView;
    }
    catch (Throwable paramView)
    {
      com.officetools.android.ui.e.b(paramView);
    }
    return null;
  }
  
  public static String b(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return null;
    }
    paramActivity = (ActivityManager)paramActivity.getSystemService("activity");
    int i1 = 0;
    paramActivity = paramActivity.getRecentTasks(5, 0);
    if (paramActivity.size() == 0) {
      return null;
    }
    while (i1 < paramActivity.size())
    {
      Intent localIntent = ((ActivityManager.RecentTaskInfo)paramActivity.get(i1)).baseIntent;
      if ((localIntent.getFlags() & 0x800000) == 0) {
        return localIntent.getComponent().getClassName();
      }
      i1 += 1;
    }
    return null;
  }
  
  public static String b(String[] paramArrayOfString)
  {
    int i1 = 0;
    while (i1 < paramArrayOfString.length)
    {
      if (b(paramArrayOfString[i1])) {
        return paramArrayOfString[i1];
      }
      i1 += 1;
    }
    return null;
  }
  
  public static void b(Runnable paramRunnable)
  {
    paramRunnable = new FutureTask(paramRunnable, null);
    com.mobisystems.android.a.a.post(paramRunnable);
    try
    {
      paramRunnable.get();
      return;
    }
    catch (Throwable paramRunnable)
    {
      com.officetools.android.ui.e.b(paramRunnable);
    }
  }
  
  public static boolean b()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)com.mobisystems.android.a.get().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        boolean bool = localNetworkInfo.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Throwable localThrowable) {}
    return true;
  }
  
  public static boolean b(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (!a(paramActivity, paramString1, paramString2, paramString4))
    {
      if (paramString3 != null) {
        return a(paramActivity, paramString1, paramString3, paramString4);
      }
      return false;
    }
    return true;
  }
  
  public static boolean b(KeyEvent paramKeyEvent)
  {
    int i1 = paramKeyEvent.getKeyCode();
    if (i1 == 82) {
      return true;
    }
    return com.mobisystems.e.a(paramKeyEvent, i1, 82);
  }
  
  public static boolean b(KeyEvent paramKeyEvent, int... paramVarArgs)
  {
    return (b(paramKeyEvent)) || (a(paramKeyEvent, paramVarArgs));
  }
  
  public static boolean b(String paramString)
  {
    return (a(paramString)) && (d(paramString));
  }
  
  public static int c()
  {
    String str = com.mobisystems.android.a.get().getPackageName();
    PackageManager localPackageManager = com.mobisystems.android.a.get().getPackageManager();
    try
    {
      int i1 = localPackageManager.getPackageInfo(str, 0).versionCode;
      return i1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public static View.OnTouchListener c(View paramView)
  {
    
    try
    {
      paramView = j.invoke(paramView, new Object[0]);
      paramView = (View.OnTouchListener)m.get(paramView);
      return paramView;
    }
    catch (Throwable paramView)
    {
      com.officetools.android.ui.e.b(paramView);
    }
    return null;
  }
  
  private static boolean c(int paramInt)
  {
    if (paramInt == -1) {
      return false;
    }
    if (!v.a(paramInt)) {
      return false;
    }
    VersionCompatibilityUtils.m().a(paramInt);
    return true;
  }
  
  public static boolean c(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent();
    if (paramActivity == null) {
      return false;
    }
    return c(paramActivity.getIntExtra("on_back_task_id", -1));
  }
  
  public static boolean c(String paramString)
  {
    try
    {
      com.mobisystems.util.a.a(ah.d(paramString));
      return true;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void d(Activity paramActivity)
  {
    if (paramActivity == null) {
      return;
    }
    try
    {
      Object localObject2 = paramActivity.getIntent();
      if (localObject2 == null) {
        return;
      }
      Intent localIntent = null;
      Object localObject1 = ((Intent)localObject2).getParcelableExtra("prevActivityIntent");
      if (localObject1 == null)
      {
        localObject1 = ((Intent)localObject2).getStringExtra("prevActivityIntent");
        if (localObject1 != null)
        {
          localObject2 = new ComponentName(com.mobisystems.android.a.get(), (String)localObject1);
          localIntent = new Intent((String)localObject1);
          localIntent.setComponent((ComponentName)localObject2);
        }
      }
      else if ((localObject1 instanceof Intent))
      {
        localIntent = new Intent((Intent)localObject1);
      }
      if (localIntent != null)
      {
        localIntent.addFlags(268566528);
        localIntent.addCategory("android.intent.action.MAIN");
        paramActivity.startActivity(localIntent);
      }
      return;
    }
    catch (Throwable paramActivity) {}
  }
  
  public static boolean d()
  {
    return a("com.android.vending");
  }
  
  public static boolean d(String paramString)
  {
    try
    {
      paramString = com.mobisystems.android.a.get().getPackageManager().getLaunchIntentForPackage(paramString);
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    paramString = null;
    return paramString != null;
  }
  
  public static int e()
  {
    try
    {
      int i1 = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0).versionCode;
      return i1;
    }
    catch (Exception localException)
    {
      com.officetools.android.ui.e.b(localException);
    }
    return 0;
  }
  
  public static int e(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramActivity = paramActivity.getWindow();
      if (paramActivity != null) {
        return paramActivity.getStatusBarColor();
      }
    }
    return 0;
  }
  
  public static String e(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      try
      {
        Object localObject = com.mobisystems.android.a.get().getPackageManager();
        if (localObject == null) {
          break label189;
        }
        localObject = ((PackageManager)localObject).getApplicationInfo(com.mobisystems.android.a.get().getPackageName(), 128);
        if (localObject == null) {
          break label189;
        }
        localObject = ((ApplicationInfo)localObject).metaData;
        if (localObject == null) {
          break label189;
        }
        localObject = ((Bundle)localObject).getString(paramString);
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        localStringBuilder2 = new StringBuilder("Failed to load meta-data \"");
        localStringBuilder2.append(paramString);
        localStringBuilder2.append("\" not-found:");
        localStringBuilder2.append(localThrowable.getMessage());
        com.officetools.office.e.a.a(3, "MetaName", localStringBuilder2.toString());
        return "cannot-be-loaded";
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        StringBuilder localStringBuilder2 = new StringBuilder("Failed to load meta-data \"");
        localStringBuilder2.append(paramString);
        localStringBuilder2.append("\" not-found:");
        localStringBuilder2.append(localNameNotFoundException.getMessage());
        com.officetools.office.e.a.a(3, "MetaName", localStringBuilder2.toString());
        return "not-found";
      }
    }
    else
    {
      StringBuilder localStringBuilder1 = new StringBuilder("Failed to load meta-data \"");
      localStringBuilder1.append(paramString);
      localStringBuilder1.append("\" is-empty.");
      com.officetools.office.e.a.a(3, "MetaName", localStringBuilder1.toString());
    }
    label189:
    return "cannot-be-loaded";
  }
  
  public static boolean f()
  {
    String str = com.mobisystems.connect.client.connect.e.i();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    return localIntent.resolveActivity(com.mobisystems.android.a.get().getPackageManager()) != null;
  }
  
  public static int g()
  {
    WindowManager localWindowManager = (WindowManager)com.mobisystems.android.a.get().getSystemService("window");
    int i2 = localWindowManager.getDefaultDisplay().getRotation();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i3 = localDisplayMetrics.widthPixels;
    int i4 = localDisplayMetrics.heightPixels;
    int i1 = 0;
    if (((i2 != 0) && (i2 != 2)) || ((i4 <= i3) && (((i2 != 1) && (i2 != 3)) || (i3 <= i4)))) {}
    switch (i2)
    {
    default: 
      return 0;
      switch (i2)
      {
      }
    case 1: 
    case 3: 
      i1 = 1;
    }
    return i1;
  }
  
  public static boolean h()
  {
    String str = Locale.getDefault().getCountry();
    if ("US".equals(str)) {
      return true;
    }
    if ("LR".equals(str)) {
      return true;
    }
    return "MM".equals(str);
  }
  
  public static String i()
  {
    try
    {
      String str = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      com.officetools.android.ui.e.b(localNameNotFoundException);
    }
    return null;
  }
  
  public static boolean j()
  {
    if (a(com.mobisystems.android.a.get(), true)) {
      return false;
    }
    WindowManager localWindowManager = (WindowManager)com.mobisystems.android.a.get().getSystemService("window");
    if (localWindowManager != null)
    {
      int i1 = localWindowManager.getDefaultDisplay().getRotation();
      if ((i1 == 1) || (i1 == 3)) {
        return true;
      }
    }
    return false;
  }
  
  public static String k()
  {
    String str = "";
    int i1 = Process.myPid();
    Iterator localIterator = ((ActivityManager)com.mobisystems.android.a.get().getSystemService("activity")).getRunningAppProcesses().iterator();
    do
    {
      localObject = str;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
    } while (((ActivityManager.RunningAppProcessInfo)localObject).pid != i1);
    Object localObject = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
    return localObject;
  }
  
  public static String l()
  {
    localObject3 = com.mobisystems.registration2.o.b;
    for (;;)
    {
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)com.mobisystems.android.a.get().getSystemService("phone");
        if ((localTelephonyManager != null) && (localTelephonyManager.getPhoneType() != 0))
        {
          new StringBuilder("Device type is ").append(localTelephonyManager.getPhoneType());
          localObject1 = localObject3;
          if (!VersionCompatibilityUtils.m().a("android.permission.READ_PHONE_STATE")) {
            continue;
          }
          localObject1 = localTelephonyManager.getDeviceId();
        }
      }
      catch (Throwable localThrowable1)
      {
        Object localObject1;
        Object localObject2 = localObject3;
        continue;
        return localObject2;
      }
      try
      {
        localObject3 = new StringBuilder("Device id from phone is |");
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append("|");
        return localObject1;
      }
      catch (Throwable localThrowable2) {}
    }
    localObject1 = null;
    return localObject1;
  }
  
  public static String m()
  {
    String str2 = "";
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)com.mobisystems.android.a.get().getSystemService("phone");
      String str1 = str2;
      if (localTelephonyManager != null)
      {
        str1 = str2;
        if (localTelephonyManager.getPhoneType() != 0) {
          str1 = localTelephonyManager.getNetworkOperator();
        }
      }
      return str1;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  /* Error */
  public static List<ApplicationInfo> n()
  {
    // Byte code:
    //   0: invokestatic 74	com/mobisystems/android/a:get	()Lcom/mobisystems/android/a;
    //   3: invokevirtual 480	com/mobisystems/android/a:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore 4
    //   8: getstatic 158	android/os/Build$VERSION:SDK_INT	I
    //   11: bipush 22
    //   13: if_icmplt +12 -> 25
    //   16: aload 4
    //   18: sipush 128
    //   21: invokevirtual 1334	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   24: areturn
    //   25: aload 4
    //   27: sipush 128
    //   30: invokevirtual 1334	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   33: astore_0
    //   34: aload_0
    //   35: areturn
    //   36: new 1336	java/util/ArrayList
    //   39: dup
    //   40: invokespecial 1337	java/util/ArrayList:<init>	()V
    //   43: astore_3
    //   44: aconst_null
    //   45: astore_2
    //   46: aconst_null
    //   47: astore_1
    //   48: aload_1
    //   49: astore_0
    //   50: invokestatic 36	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   53: ldc_w 1339
    //   56: invokevirtual 1343	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   59: astore 5
    //   61: aload_1
    //   62: astore_0
    //   63: new 1345	java/io/BufferedReader
    //   66: dup
    //   67: new 1347	java/io/InputStreamReader
    //   70: dup
    //   71: aload 5
    //   73: invokevirtual 1353	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   76: invokespecial 1356	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   79: invokespecial 1359	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   82: astore_1
    //   83: aload_1
    //   84: invokevirtual 1362	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   87: astore_0
    //   88: aload_0
    //   89: ifnull +36 -> 125
    //   92: aload_3
    //   93: aload 4
    //   95: aload_0
    //   96: aload_0
    //   97: bipush 58
    //   99: invokevirtual 229	java/lang/String:indexOf	(I)I
    //   102: iconst_1
    //   103: iadd
    //   104: invokevirtual 1365	java/lang/String:substring	(I)Ljava/lang/String;
    //   107: sipush 128
    //   110: invokevirtual 1157	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   113: getfield 1369	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   116: invokeinterface 1372 2 0
    //   121: pop
    //   122: goto -39 -> 83
    //   125: aload 5
    //   127: invokevirtual 1375	java/lang/Process:waitFor	()I
    //   130: pop
    //   131: aload_1
    //   132: invokevirtual 1378	java/io/BufferedReader:close	()V
    //   135: aload_3
    //   136: areturn
    //   137: astore_2
    //   138: aload_1
    //   139: astore_0
    //   140: aload_2
    //   141: astore_1
    //   142: goto +39 -> 181
    //   145: astore_2
    //   146: goto +12 -> 158
    //   149: astore_1
    //   150: goto +31 -> 181
    //   153: astore_0
    //   154: aload_2
    //   155: astore_1
    //   156: aload_0
    //   157: astore_2
    //   158: aload_1
    //   159: astore_0
    //   160: aload_2
    //   161: invokestatic 967	com/g/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   164: aload_1
    //   165: ifnull +14 -> 179
    //   168: aload_1
    //   169: invokevirtual 1378	java/io/BufferedReader:close	()V
    //   172: aload_3
    //   173: areturn
    //   174: astore_0
    //   175: aload_0
    //   176: invokestatic 967	com/g/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   179: aload_3
    //   180: areturn
    //   181: aload_0
    //   182: ifnull +15 -> 197
    //   185: aload_0
    //   186: invokevirtual 1378	java/io/BufferedReader:close	()V
    //   189: goto +8 -> 197
    //   192: astore_0
    //   193: aload_0
    //   194: invokestatic 967	com/g/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   197: aload_1
    //   198: athrow
    //   199: astore_0
    //   200: goto -164 -> 36
    // Local variable table:
    //   start	length	slot	name	signature
    //   33	107	0	localObject1	Object
    //   153	4	0	localException1	Exception
    //   159	1	0	localObject2	Object
    //   174	12	0	localIOException1	IOException
    //   192	2	0	localIOException2	IOException
    //   199	1	0	localException2	Exception
    //   47	95	1	localObject3	Object
    //   149	1	1	localObject4	Object
    //   155	43	1	localException3	Exception
    //   45	1	2	localObject5	Object
    //   137	4	2	localObject6	Object
    //   145	10	2	localException4	Exception
    //   157	4	2	localException5	Exception
    //   43	137	3	localArrayList	java.util.ArrayList
    //   6	88	4	localPackageManager	PackageManager
    //   59	67	5	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   83	88	137	finally
    //   92	122	137	finally
    //   125	131	137	finally
    //   83	88	145	java/lang/Exception
    //   92	122	145	java/lang/Exception
    //   125	131	145	java/lang/Exception
    //   50	61	149	finally
    //   63	83	149	finally
    //   160	164	149	finally
    //   50	61	153	java/lang/Exception
    //   63	83	153	java/lang/Exception
    //   131	135	174	java/io/IOException
    //   168	172	174	java/io/IOException
    //   185	189	192	java/io/IOException
    //   25	34	199	java/lang/Exception
  }
  
  public static boolean o()
  {
    if (com.mobisystems.android.a.get().getResources().getConfiguration().touchscreen == 1) {
      return false;
    }
    try
    {
      boolean bool = com.mobisystems.android.a.get().getPackageManager().hasSystemFeature("android.hardware.touchscreen");
      return bool;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return true;
  }
  
  public static void p()
  {
    new s.2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public static boolean q()
  {
    long l1 = s();
    long l2 = t();
    if ((l1 != 0L) && (l2 != 0L)) {
      return l1 != l2;
    }
    return false;
  }
  
  public static boolean r()
  {
    try
    {
      int i1 = com.mobisystems.android.a.get().getPackageManager().getApplicationInfo(com.mobisystems.android.a.get().getPackageName(), 128).flags;
      if ((i1 & 0x1) != 0) {
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static long s()
  {
    long l1 = 0L;
    try
    {
      PackageInfo localPackageInfo = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0);
      if (localPackageInfo != null) {
        l1 = localPackageInfo.firstInstallTime;
      }
      return l1;
    }
    catch (Throwable localThrowable) {}
    return 0L;
  }
  
  public static long t()
  {
    long l1 = 0L;
    try
    {
      PackageInfo localPackageInfo = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0);
      if (localPackageInfo != null) {
        l1 = localPackageInfo.lastUpdateTime;
      }
      return l1;
    }
    catch (Throwable localThrowable) {}
    return 0L;
  }
  
  public static Locale u()
  {
    if (Build.VERSION.SDK_INT < 24) {
      return com.mobisystems.android.a.get().getResources().getConfiguration().locale;
    }
    return com.mobisystems.android.a.get().getResources().getConfiguration().getLocales().get(0);
  }
  
  public static String v()
  {
    return u().toString();
  }
  
  public static String w()
  {
    return u().getLanguage();
  }
  
  public static ComponentName x()
  {
    return new ComponentName(com.mobisystems.android.a.get(), "com.officetools.window.EulaActivity");
  }
  
  public static ComponentName y()
  {
    return new ComponentName(com.mobisystems.android.a.get(), "com.officetools.office.files.HomeBrowserActivity");
  }
  
  public static boolean z()
  {
    return true;
  }
}
