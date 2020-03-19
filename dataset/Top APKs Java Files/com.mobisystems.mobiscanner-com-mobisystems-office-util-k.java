package com.mobisystems.office.util;

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
import android.os.Process;
import android.provider.Settings.Global;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.app.Fragment;
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
import com.mobisystems.MSBuildConfig;
import com.mobisystems.android.ui.VersionCompatibilityUtils;
import com.mobisystems.android.ui.j;
import com.mobisystems.login.ILogin;
import com.mobisystems.office.Component;
import com.mobisystems.office.ak;
import com.mobisystems.office.common.R.bool;
import com.mobisystems.registration.f;
import com.mobisystems.services.FileDownloadService;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class k
{
  public static final Executor a = Executors.newCachedThreadPool();
  public static final Executor b = Executors.newSingleThreadExecutor();
  public static final Executor c = Executors.newSingleThreadExecutor();
  public static final Executor d = Executors.newFixedThreadPool(f);
  private static final int e;
  private static final int f;
  private static boolean g;
  private static Method h;
  private static Field i;
  private static Field j;
  private static Field k;
  
  static
  {
    int m = Runtime.getRuntime().availableProcessors();
    e = m;
    f = m << 1;
  }
  
  public static ComponentName A()
  {
    return new ComponentName(com.mobisystems.android.a.get(), "com.mobisystems.eula.EulaActivity");
  }
  
  public static ComponentName B()
  {
    return new ComponentName(com.mobisystems.android.a.get(), "com.mobisystems.office.files.FileBrowser");
  }
  
  public static boolean C()
  {
    String str = Build.BRAND.toLowerCase(Locale.ENGLISH);
    return (str.contains("samsung")) || (str.contains("prestigio"));
  }
  
  public static InputManager D()
  {
    Object localObject = com.mobisystems.android.a.get().getSystemService("input");
    if ((localObject instanceof InputManager)) {
      return (InputManager)localObject;
    }
    return null;
  }
  
  public static int E()
  {
    try
    {
      int m = Settings.System.getInt(com.mobisystems.android.a.get().getContentResolver(), "accelerometer_rotation");
      return m;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return 1;
  }
  
  /* Error */
  private static void F()
  {
    // Byte code:
    //   0: getstatic 135	com/mobisystems/office/util/k:g	Z
    //   3: ifeq +4 -> 7
    //   6: return
    //   7: ldc -119
    //   9: ldc -117
    //   11: iconst_0
    //   12: anewarray 141	java/lang/Class
    //   15: invokevirtual 145	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   18: astore_0
    //   19: aload_0
    //   20: putstatic 147	com/mobisystems/office/util/k:h	Ljava/lang/reflect/Method;
    //   23: aload_0
    //   24: iconst_1
    //   25: invokevirtual 153	java/lang/reflect/Method:setAccessible	(Z)V
    //   28: getstatic 147	com/mobisystems/office/util/k:h	Ljava/lang/reflect/Method;
    //   31: invokevirtual 157	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   34: ldc -97
    //   36: invokevirtual 163	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   39: astore_0
    //   40: aload_0
    //   41: putstatic 165	com/mobisystems/office/util/k:i	Ljava/lang/reflect/Field;
    //   44: aload_0
    //   45: iconst_1
    //   46: invokevirtual 168	java/lang/reflect/Field:setAccessible	(Z)V
    //   49: getstatic 147	com/mobisystems/office/util/k:h	Ljava/lang/reflect/Method;
    //   52: invokevirtual 157	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   55: ldc -86
    //   57: invokevirtual 163	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   60: astore_0
    //   61: aload_0
    //   62: putstatic 172	com/mobisystems/office/util/k:j	Ljava/lang/reflect/Field;
    //   65: aload_0
    //   66: iconst_1
    //   67: invokevirtual 168	java/lang/reflect/Field:setAccessible	(Z)V
    //   70: getstatic 147	com/mobisystems/office/util/k:h	Ljava/lang/reflect/Method;
    //   73: invokevirtual 157	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   76: ldc -82
    //   78: invokevirtual 163	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   81: astore_0
    //   82: aload_0
    //   83: putstatic 176	com/mobisystems/office/util/k:k	Ljava/lang/reflect/Field;
    //   86: aload_0
    //   87: iconst_1
    //   88: invokevirtual 168	java/lang/reflect/Field:setAccessible	(Z)V
    //   91: iconst_1
    //   92: putstatic 135	com/mobisystems/office/util/k:g	Z
    //   95: return
    //   96: astore_0
    //   97: goto +13 -> 110
    //   100: astore_0
    //   101: aload_0
    //   102: invokestatic 181	com/mobisystems/android/ui/c:a	(Ljava/lang/Throwable;)V
    //   105: iconst_1
    //   106: putstatic 135	com/mobisystems/office/util/k:g	Z
    //   109: return
    //   110: iconst_1
    //   111: putstatic 135	com/mobisystems/office/util/k:g	Z
    //   114: aload_0
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   18	69	0	localObject1	Object
    //   96	1	0	localObject2	Object
    //   100	15	0	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   7	91	96	finally
    //   101	105	96	finally
    //   7	91	100	java/lang/Throwable
  }
  
  public static int a(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject.hashCode();
    }
    return 0;
  }
  
  public static int a(String paramString)
  {
    PackageManager localPackageManager = com.mobisystems.android.a.get().getPackageManager();
    try
    {
      int m = localPackageManager.getPackageInfo(paramString, 1).versionCode;
      return m;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public static long a(String paramString, int paramInt1, int paramInt2)
  {
    Object localObject1 = com.mobisystems.android.a.get().getSharedPreferences("com.mobisystems.office.update.prefs", 0);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("_update_hour_of_day");
    int i1 = ((SharedPreferences)localObject1).getInt(((StringBuilder)localObject2).toString(), -1);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("_update_minute_of_day");
    int n = ((SharedPreferences)localObject1).getInt(((StringBuilder)localObject2).toString(), -1);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("_update_second_of_day");
    int i2 = ((SharedPreferences)localObject1).getInt(((StringBuilder)localObject2).toString(), -1);
    int m;
    if ((i1 >= paramInt1) && (i1 < paramInt2) && (n >= 0) && (n <= 59) && (i2 >= 0))
    {
      m = i2;
      if (i2 <= 59) {}
    }
    else
    {
      localObject2 = new Random(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
      i1 = ((Random)localObject2).nextInt(paramInt2 - paramInt1) + paramInt1;
      n = ((Random)localObject2).nextInt(60);
      m = ((Random)localObject2).nextInt(60);
      localObject1 = ((SharedPreferences)localObject1).edit();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("_update_hour_of_day");
      ((SharedPreferences.Editor)localObject1).putInt(((StringBuilder)localObject2).toString(), i1);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("_update_minute_of_day");
      ((SharedPreferences.Editor)localObject1).putInt(((StringBuilder)localObject2).toString(), n);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("_update_second_of_day");
      ((SharedPreferences.Editor)localObject1).putInt(((StringBuilder)localObject2).toString(), m);
      ((SharedPreferences.Editor)localObject1).apply();
    }
    localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).set(11, i1);
    ((Calendar)localObject1).set(12, n);
    ((Calendar)localObject1).set(13, m);
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
    com.mobisystems.office.c.a.a(3, "AlarmsManager", ((StringBuilder)localObject2).toString());
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
  
  public static Bitmap a(int paramInt1, int paramInt2, int paramInt3)
  {
    Bitmap localBitmap1 = b(paramInt1);
    Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, paramInt2, paramInt3, false);
    if (localBitmap2 != localBitmap1) {
      localBitmap1.recycle();
    }
    return localBitmap2;
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
    if ((paramDrawable != null) && (Build.VERSION.SDK_INT >= 24))
    {
      if (!VersionCompatibilityUtils.i()) {
        return paramDrawable;
      }
      if (E() == 1) {
        return paramDrawable;
      }
      int m = com.mobisystems.util.k.a(32.0F);
      paramDrawable = new LayerDrawable(new Drawable[] { paramDrawable });
      paramDrawable.setLayerInset(0, 0, m, 0, 0);
      return paramDrawable;
    }
    return paramDrawable;
  }
  
  public static Bundle a(Activity paramActivity, Bundle paramBundle)
  {
    if ((paramActivity != null) && (Build.VERSION.SDK_INT >= 24) && (paramActivity.isInMultiWindowMode())) {
      if (!VersionCompatibilityUtils.i()) {
        return paramBundle;
      }
    }
    try
    {
      m = Settings.System.getInt(paramActivity.getContentResolver(), "accelerometer_rotation");
      if (m == 1) {
        return paramBundle;
      }
    }
    catch (Throwable localThrowable)
    {
      int m;
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
        m = com.mobisystems.util.k.a(32.0F);
        paramActivity.offset(m, m);
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
      paramView = h.invoke(paramView, new Object[0]);
      paramView = (View.OnClickListener)i.get(paramView);
      return paramView;
    }
    catch (Throwable paramView)
    {
      com.mobisystems.android.ui.c.a(paramView);
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
    int m = 0;
    while (m < paramArrayOfString.length)
    {
      if (b(paramArrayOfString[m])) {
        return paramArrayOfString[m];
      }
      m += 1;
    }
    return null;
  }
  
  /* Error */
  public static List<ApplicationInfo> a(int paramInt)
  {
    // Byte code:
    //   0: invokestatic 70	com/mobisystems/android/a:get	()Lcom/mobisystems/android/a;
    //   3: invokevirtual 192	com/mobisystems/android/a:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 478	android/os/Build$VERSION:SDK_INT	I
    //   10: bipush 22
    //   12: if_icmplt +11 -> 23
    //   15: aload_3
    //   16: sipush 128
    //   19: invokevirtual 650	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   22: areturn
    //   23: aload_3
    //   24: sipush 128
    //   27: invokevirtual 650	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   30: astore_1
    //   31: aload_1
    //   32: areturn
    //   33: new 652	java/util/ArrayList
    //   36: dup
    //   37: invokespecial 653	java/util/ArrayList:<init>	()V
    //   40: astore 5
    //   42: aconst_null
    //   43: astore 4
    //   45: aconst_null
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: invokestatic 30	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   52: ldc_w 655
    //   55: invokevirtual 659	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   58: astore 6
    //   60: aload_2
    //   61: astore_1
    //   62: new 661	java/io/BufferedReader
    //   65: dup
    //   66: new 663	java/io/InputStreamReader
    //   69: dup
    //   70: aload 6
    //   72: invokevirtual 669	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   75: invokespecial 672	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   78: invokespecial 675	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   81: astore_2
    //   82: aload_2
    //   83: invokevirtual 678	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   86: astore_1
    //   87: aload_1
    //   88: ifnull +36 -> 124
    //   91: aload 5
    //   93: aload_3
    //   94: aload_1
    //   95: aload_1
    //   96: bipush 58
    //   98: invokevirtual 681	java/lang/String:indexOf	(I)I
    //   101: iconst_1
    //   102: iadd
    //   103: invokevirtual 685	java/lang/String:substring	(I)Ljava/lang/String;
    //   106: sipush 128
    //   109: invokevirtual 198	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   112: getfield 689	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   115: invokeinterface 694 2 0
    //   120: pop
    //   121: goto -39 -> 82
    //   124: aload 6
    //   126: invokevirtual 697	java/lang/Process:waitFor	()I
    //   129: pop
    //   130: aload_2
    //   131: invokevirtual 700	java/io/BufferedReader:close	()V
    //   134: aload 5
    //   136: areturn
    //   137: astore_3
    //   138: aload_2
    //   139: astore_1
    //   140: aload_3
    //   141: astore_2
    //   142: goto +40 -> 182
    //   145: astore_3
    //   146: goto +11 -> 157
    //   149: astore_2
    //   150: goto +32 -> 182
    //   153: astore_3
    //   154: aload 4
    //   156: astore_2
    //   157: aload_2
    //   158: astore_1
    //   159: aload_3
    //   160: invokestatic 703	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   163: aload_2
    //   164: ifnull +15 -> 179
    //   167: aload_2
    //   168: invokevirtual 700	java/io/BufferedReader:close	()V
    //   171: aload 5
    //   173: areturn
    //   174: astore_1
    //   175: aload_1
    //   176: invokestatic 703	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   179: aload 5
    //   181: areturn
    //   182: aload_1
    //   183: ifnull +15 -> 198
    //   186: aload_1
    //   187: invokevirtual 700	java/io/BufferedReader:close	()V
    //   190: goto +8 -> 198
    //   193: astore_1
    //   194: aload_1
    //   195: invokestatic 703	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   198: aload_2
    //   199: athrow
    //   200: astore_1
    //   201: goto -168 -> 33
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	204	0	paramInt	int
    //   30	129	1	localObject1	Object
    //   174	13	1	localIOException1	IOException
    //   193	2	1	localIOException2	IOException
    //   200	1	1	localException1	Exception
    //   46	96	2	localObject2	Object
    //   149	1	2	localObject3	Object
    //   156	43	2	localObject4	Object
    //   6	88	3	localPackageManager	PackageManager
    //   137	4	3	localObject5	Object
    //   145	1	3	localException2	Exception
    //   153	7	3	localException3	Exception
    //   43	112	4	localObject6	Object
    //   40	140	5	localArrayList	java.util.ArrayList
    //   58	67	6	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   82	87	137	finally
    //   91	121	137	finally
    //   124	130	137	finally
    //   82	87	145	java/lang/Exception
    //   91	121	145	java/lang/Exception
    //   124	130	145	java/lang/Exception
    //   49	60	149	finally
    //   62	82	149	finally
    //   159	163	149	finally
    //   49	60	153	java/lang/Exception
    //   62	82	153	java/lang/Exception
    //   130	134	174	java/io/IOException
    //   167	171	174	java/io/IOException
    //   186	190	193	java/io/IOException
    //   23	31	200	java/lang/Exception
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
    catch (Throwable paramActivity)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(paramActivity);
      }
    }
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
      try
      {
        com.mobisystems.office.a.a.a("ms_error").a("tag", "translucent-orientation").a("exception", paramActivity.getClass().getName()).a("message", paramActivity.getMessage()).a("trace", paramActivity.toString()).a();
        return;
      }
      catch (Throwable paramActivity)
      {
        com.mobisystems.android.ui.c.a(paramActivity);
      }
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
    if (!a(com.mobisystems.android.a.get()))
    {
      com.mobisystems.office.exceptions.b.a(paramActivity, new k.1(paramRunnable2));
      return;
    }
    if (paramRunnable1 != null) {
      paramRunnable1.run();
    }
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    Object localObject = Uri.parse(f.a(paramString2, paramString3));
    if (paramString2.contains("getProductFile.php")) {
      try
      {
        localObject = File.createTempFile("temp", ".apk", FileDownloadService.a);
        Intent localIntent = new Intent(paramActivity, FileDownloadService.class);
        paramString2 = Uri.parse(f.a(paramString2, paramString3));
        localIntent.putExtra("actionMode", 1);
        localIntent.putExtra("fileUrl", paramString2.toString());
        localIntent.putExtra("fileName", paramString1);
        localIntent.putExtra("dstFile", (Serializable)localObject);
        localIntent.putExtra("fileComponent", Component.OfficeFileBrowser);
        android.support.v4.content.c.startForegroundService(paramActivity, localIntent);
        return;
      }
      catch (IOException paramActivity)
      {
        if (MSBuildConfig.DBG) {
          com.google.a.a.a.a.a.a.a(paramActivity);
        }
        return;
      }
    }
    com.mobisystems.util.a.a(paramActivity, a((Uri)localObject));
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = new ComponentName(paramContext.getPackageName(), paramString);
    int m;
    if (paramBoolean) {
      m = 1;
    } else {
      m = 2;
    }
    try
    {
      localPackageManager.setComponentEnabledSetting(paramContext, m, 1);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void a(Window paramWindow)
  {
    if ((paramWindow != null) && (Build.VERSION.SDK_INT >= 24))
    {
      if (!VersionCompatibilityUtils.i()) {
        return;
      }
      paramWindow = paramWindow.getDecorView();
      int m;
      if (E() == 0) {
        m = com.mobisystems.util.k.a(32.0F);
      } else {
        m = 0;
      }
      paramWindow.setPadding(0, m, 0, 0);
      return;
    }
  }
  
  public static void a(Class paramClass, int paramInt, Intent paramIntent)
  {
    com.mobisystems.android.a.c.post(new l(paramClass, paramInt, paramIntent));
  }
  
  public static boolean a()
  {
    String str = Build.MODEL;
    return (str != null) && ((str.startsWith("SM-G950")) || (str.startsWith("SM-G955")) || (str.startsWith("SM-G965")));
  }
  
  public static boolean a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (!b(paramActivity, paramString1, paramString2, paramString4))
    {
      if (paramString3 != null) {
        return b(paramActivity, paramString1, paramString3, paramString4);
      }
      return false;
    }
    return true;
  }
  
  private static boolean a(Activity paramActivity, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    try
    {
      if (a(com.mobisystems.android.a.get())) {
        a(paramActivity, paramString1, paramString2, paramString3);
      } else {
        com.mobisystems.office.exceptions.b.a(paramActivity, null);
      }
      return true;
    }
    catch (Throwable paramActivity)
    {
      com.google.a.a.a.a.a.a.a(paramActivity);
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
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(paramDialog);
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Throwable paramContext) {}
    return true;
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean)
  {
    if ((paramBoolean) && ((Build.MODEL.equalsIgnoreCase("KFSAWA")) || (Build.MODEL.equalsIgnoreCase("KFSAWI")) || (Build.MODEL.equalsIgnoreCase("KFTHWA")) || (Build.MODEL.equalsIgnoreCase("KFTHWI")))) {
      return false;
    }
    if (VersionCompatibilityUtils.h()) {
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
      int m = paramContext.smallestScreenWidthDp;
      return m >= 600;
    }
    catch (Throwable paramContext)
    {
      com.mobisystems.android.ui.c.a(paramContext);
    }
    return paramBoolean;
  }
  
  public static boolean a(Fragment paramFragment)
  {
    paramFragment = paramFragment.getArguments();
    if (paramFragment == null) {
      return false;
    }
    return d(paramFragment.getInt("on_back_task_id", -1));
  }
  
  private static boolean a(InputDevice paramInputDevice)
  {
    try
    {
      boolean bool = ((Boolean)InputDevice.class.getMethod("isExternal", new Class[0]).invoke(paramInputDevice, new Object[0])).booleanValue();
      return !bool;
    }
    catch (Throwable paramInputDevice)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(paramInputDevice);
      }
    }
    return false;
  }
  
  public static boolean a(KeyEvent paramKeyEvent)
  {
    int m = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (m < 16) {
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
    int n = paramKeyEvent.getKeyCode();
    int i1 = paramVarArgs.length;
    int m = 0;
    while (m < i1)
    {
      if (n == paramVarArgs[m]) {
        return true;
      }
      m += 1;
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
  
  public static int b(Context paramContext)
  {
    try
    {
      int m = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return m;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return 0;
  }
  
  public static Bitmap b(int paramInt)
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
  
  public static View.OnLongClickListener b(View paramView)
  {
    
    try
    {
      paramView = h.invoke(paramView, new Object[0]);
      paramView = (View.OnLongClickListener)j.get(paramView);
      return paramView;
    }
    catch (Throwable paramView)
    {
      com.mobisystems.android.ui.c.a(paramView);
    }
    return null;
  }
  
  public static String b(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return null;
    }
    paramActivity = (ActivityManager)paramActivity.getSystemService("activity");
    int m = 0;
    paramActivity = paramActivity.getRecentTasks(5, 0);
    if (paramActivity.size() == 0) {
      return null;
    }
    while (m < paramActivity.size())
    {
      Intent localIntent = ((ActivityManager.RecentTaskInfo)paramActivity.get(m)).baseIntent;
      if ((localIntent.getFlags() & 0x800000) == 0) {
        return localIntent.getComponent().getClassName();
      }
      m += 1;
    }
    return null;
  }
  
  public static String b(String[] paramArrayOfString)
  {
    int m = 0;
    while (m < paramArrayOfString.length)
    {
      if (c(paramArrayOfString[m])) {
        return paramArrayOfString[m];
      }
      m += 1;
    }
    return null;
  }
  
  public static boolean b()
  {
    String str = Build.MODEL;
    return (str != null) && ((str.startsWith("SM-G950")) || (str.startsWith("SM-G955")));
  }
  
  public static boolean b(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    return a(paramActivity, paramString1, paramString2, paramString3, false);
  }
  
  public static boolean b(KeyEvent paramKeyEvent)
  {
    int m = paramKeyEvent.getKeyCode();
    if (m == 82) {
      return true;
    }
    return com.mobisystems.d.a(paramKeyEvent, m, 82);
  }
  
  public static boolean b(KeyEvent paramKeyEvent, int... paramVarArgs)
  {
    return (b(paramKeyEvent)) || (a(paramKeyEvent, paramVarArgs));
  }
  
  public static boolean b(String paramString)
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
  
  public static int c(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    int n = paramContext.getDefaultDisplay().getRotation();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i1 = localDisplayMetrics.widthPixels;
    int i2 = localDisplayMetrics.heightPixels;
    int m = 1;
    if (((n != 0) && (n != 2)) || ((i2 <= i1) && (((n != 1) && (n != 3)) || (i1 <= i2)))) {}
    switch (n)
    {
    default: 
      break;
      switch (n)
      {
      default: 
        return 1;
      }
    case 2: 
      return 8;
    case 0: 
      m = 0;
    }
    return m;
  }
  
  public static Drawable c(int paramInt)
  {
    return a(null, paramInt);
  }
  
  public static View.OnTouchListener c(View paramView)
  {
    
    try
    {
      paramView = h.invoke(paramView, new Object[0]);
      paramView = (View.OnTouchListener)k.get(paramView);
      return paramView;
    }
    catch (Throwable paramView)
    {
      com.mobisystems.android.ui.c.a(paramView);
    }
    return null;
  }
  
  public static void c()
  {
    System.exit(1);
  }
  
  public static boolean c(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent();
    if (paramActivity == null) {
      return false;
    }
    return d(paramActivity.getIntExtra("on_back_task_id", -1));
  }
  
  public static boolean c(String paramString)
  {
    return (b(paramString)) && (e(paramString));
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
    catch (Throwable paramActivity)
    {
      if (MSBuildConfig.DBG) {
        Log.w("DBG", paramActivity);
      }
    }
  }
  
  public static boolean d()
  {
    return a(com.mobisystems.android.a.get());
  }
  
  private static boolean d(int paramInt)
  {
    if (paramInt == -1) {
      return false;
    }
    if (!n.a(paramInt)) {
      return false;
    }
    VersionCompatibilityUtils.g().a(paramInt, 0);
    return true;
  }
  
  public static boolean d(Context paramContext)
  {
    return (c(paramContext) == 0) || (c(paramContext) == 8);
  }
  
  public static boolean d(String paramString)
  {
    try
    {
      com.mobisystems.util.a.a(ak.a(paramString));
      return true;
    }
    catch (Throwable paramString)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(paramString);
      }
    }
    return false;
  }
  
  public static boolean e()
  {
    return Build.MANUFACTURER.toLowerCase().contains("hisense");
  }
  
  public static boolean e(Context paramContext)
  {
    if (c(paramContext) != 1) {
      return c(paramContext) == 9;
    }
    return true;
  }
  
  public static boolean e(String paramString)
  {
    try
    {
      paramString = com.mobisystems.android.a.get().getPackageManager().getLaunchIntentForPackage(paramString);
    }
    catch (Throwable paramString)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(paramString);
      }
      paramString = null;
    }
    return paramString != null;
  }
  
  public static String f(String paramString)
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
        com.mobisystems.office.c.a.a(3, "MetaName", localStringBuilder2.toString());
        return "cannot-be-loaded";
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        StringBuilder localStringBuilder2 = new StringBuilder("Failed to load meta-data \"");
        localStringBuilder2.append(paramString);
        localStringBuilder2.append("\" not-found:");
        localStringBuilder2.append(localNameNotFoundException.getMessage());
        com.mobisystems.office.c.a.a(3, "MetaName", localStringBuilder2.toString());
        return "not-found";
      }
    }
    else
    {
      StringBuilder localStringBuilder1 = new StringBuilder("Failed to load meta-data \"");
      localStringBuilder1.append(paramString);
      localStringBuilder1.append("\" is-empty.");
      com.mobisystems.office.c.a.a(3, "MetaName", localStringBuilder1.toString());
    }
    label189:
    return "cannot-be-loaded";
  }
  
  public static boolean f()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean f(Context paramContext)
  {
    if (n())
    {
      paramContext = (WindowManager)paramContext.getSystemService("window");
      if ((paramContext != null) && (paramContext.getDefaultDisplay().getRotation() == 3)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean g()
  {
    return b("com.android.vending");
  }
  
  public static boolean g(Context paramContext)
  {
    if (paramContext != null) {
      return paramContext.getResources().getBoolean(R.bool.tablet);
    }
    return false;
  }
  
  public static int h()
  {
    try
    {
      int m = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0).versionCode;
      return m;
    }
    catch (Exception localException)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(localException);
      }
    }
    return 0;
  }
  
  public static boolean i()
  {
    String str = com.mobisystems.connect.client.connect.d.i();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    return localIntent.resolveActivity(com.mobisystems.android.a.get().getPackageManager()) != null;
  }
  
  public static String j()
  {
    String str = com.mobisystems.android.a.get().g().j();
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    return com.mobisystems.googlesignin.a.e();
  }
  
  @Deprecated
  public static int k()
  {
    return c(com.mobisystems.android.a.get());
  }
  
  public static boolean l()
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
  
  public static String m()
  {
    try
    {
      String str = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(localNameNotFoundException);
      }
    }
    return null;
  }
  
  public static boolean n()
  {
    if (Build.VERSION.SDK_INT > 24) {
      return true;
    }
    if ("samsung".equalsIgnoreCase(Build.MANUFACTURER)) {}
    try
    {
      if (Build.VERSION.SDK_INT >= 17) {
        m = Settings.Global.getInt(com.mobisystems.android.a.get().getContentResolver(), "navigationbar_hide_bar_enabled");
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      int m;
      for (;;) {}
    }
    m = -1;
    if (-1 != m) {
      return true;
    }
    return false;
  }
  
  public static String o()
  {
    String str = "";
    int m = Process.myPid();
    Iterator localIterator = ((ActivityManager)com.mobisystems.android.a.get().getSystemService("activity")).getRunningAppProcesses().iterator();
    do
    {
      localObject = str;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
    } while (((ActivityManager.RunningAppProcessInfo)localObject).pid != m);
    Object localObject = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
    return localObject;
  }
  
  @Deprecated
  public static String p()
  {
    return null;
  }
  
  public static String q()
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
  
  public static String r()
  {
    String str = "";
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)com.mobisystems.android.a.get().getSystemService("phone");
      if (localTelephonyManager != null) {
        str = localTelephonyManager.getLine1Number();
      }
      return str;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  public static boolean s()
  {
    if (Build.MODEL.compareTo("S2720") == 0) {
      return false;
    }
    try
    {
      boolean bool = com.mobisystems.android.a.get().getPackageManager().hasSystemFeature("android.hardware.touchscreen");
      return bool;
    }
    catch (Throwable localThrowable) {}
    return true;
  }
  
  public static void t()
  {
    new k.2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public static boolean u()
  {
    try
    {
      int m = com.mobisystems.android.a.get().getPackageManager().getApplicationInfo(com.mobisystems.android.a.get().getPackageName(), 128).flags;
      if ((m & 0x1) != 0) {
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(localThrowable);
      }
    }
    return false;
  }
  
  public static long v()
  {
    long l = 0L;
    try
    {
      PackageInfo localPackageInfo = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0);
      if (localPackageInfo != null) {
        l = localPackageInfo.firstInstallTime;
      }
      return l;
    }
    catch (Throwable localThrowable)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(localThrowable);
      }
    }
    return 0L;
  }
  
  public static long w()
  {
    long l = 0L;
    try
    {
      PackageInfo localPackageInfo = com.mobisystems.android.a.get().getPackageManager().getPackageInfo(com.mobisystems.android.a.get().getPackageName(), 0);
      if (localPackageInfo != null) {
        l = localPackageInfo.lastUpdateTime;
      }
      return l;
    }
    catch (Throwable localThrowable)
    {
      if (MSBuildConfig.DBG) {
        com.google.a.a.a.a.a.a.a(localThrowable);
      }
    }
    return 0L;
  }
  
  public static Locale x()
  {
    if (Build.VERSION.SDK_INT < 24) {
      return com.mobisystems.android.a.get().getResources().getConfiguration().locale;
    }
    return com.mobisystems.android.a.get().getResources().getConfiguration().getLocales().get(0);
  }
  
  public static String y()
  {
    return x().toString();
  }
  
  public static String z()
  {
    return x().getLanguage();
  }
}
