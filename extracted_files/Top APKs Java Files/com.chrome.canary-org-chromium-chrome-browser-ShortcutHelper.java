package org.chromium.chrome.browser;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.Toast;
import aqh;
import aql;
import aqv;
import asm;
import axc;
import axe;
import axf;
import bML;
import bNG;
import bNs;
import bNv;
import bOU;
import cnK;
import cnu;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.webapps.WebappRegistry;

public class ShortcutHelper
{
  private static boolean a;
  private static boolean b;
  private static ShortcutManager c;
  
  static
  {
    new axf();
  }
  
  public ShortcutHelper() {}
  
  private static int a(Context paramContext, int paramInt)
  {
    return Math.round(paramContext.getResources().getDimension(paramInt));
  }
  
  public static Intent a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, String paramString8, boolean paramBoolean1, boolean paramBoolean2)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(aql.a.getPackageName()).setAction(paramString2).putExtra("org.chromium.chrome.browser.webapp_id", paramString1).putExtra("org.chromium.chrome.browser.webapp_url", paramString3).putExtra("org.chromium.chrome.browser.webapp_scope", paramString4).putExtra("org.chromium.chrome.browser.webapp_name", paramString5).putExtra("org.chromium.chrome.browser.webapp_short_name", paramString6).putExtra("org.chromium.chrome.browser.webapp_icon", paramString7).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", paramInt1).putExtra("org.chromium.chrome.browser.webapp_display_mode", paramInt2).putExtra("org.chromium.content_public.common.orientation", paramInt3).putExtra("org.chromium.chrome.browser.theme_color", paramLong1).putExtra("org.chromium.chrome.browser.background_color", paramLong2).putExtra("org.chromium.chrome.browser.webapp_splash_screen_url", paramString8).putExtra("org.chromium.chrome.browser.is_icon_generated", paramBoolean1).putExtra("org.chromium.chrome.browser.webapp_icon_adaptive", paramBoolean2);
    return localIntent;
  }
  
  public static String a(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return "";
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 0);
  }
  
  public static void a(String paramString)
  {
    b(aql.a.getString(2131951910, new Object[] { paramString }));
  }
  
  @TargetApi(26)
  public static void a(String paramString, Bitmap paramBitmap, boolean paramBoolean, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("org.chromium.chrome.browser.webapp_id");
    Context localContext = aql.a;
    if (paramBoolean) {
      paramBitmap = Icon.createWithAdaptiveBitmap(paramBitmap);
    } else {
      paramBitmap = Icon.createWithBitmap(paramBitmap);
    }
    paramString = new ShortcutInfo.Builder(localContext, str).setShortLabel(paramString).setLongLabel(paramString).setIcon(paramBitmap).setIntent(paramIntent).build();
    try
    {
      c.requestPinShortcut(paramString, null);
      return;
    }
    catch (IllegalStateException paramString) {}
  }
  
  @SuppressLint({"WrongConstant"})
  public static boolean a()
  {
    if (c()) {
      return true;
    }
    return !aql.a.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty();
  }
  
  @CalledByNative
  private static void addShortcut(String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, boolean paramBoolean, int paramInt)
  {
    Context localContext = aql.a;
    paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    paramString2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_id", paramString1);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_source", paramInt);
    paramString2.setPackage(localContext.getPackageName());
    axf.a(paramString3, paramBitmap, paramBoolean, paramString2);
    if (b()) {
      a(paramString3);
    }
  }
  
  @CalledByNative
  private static void addWebapp(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Bitmap paramBitmap, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, String paramString8, long paramLong3)
  {
    new axc(paramBitmap, paramString3, paramString2, paramString1, paramString5, paramString6, paramInt1, paramInt2, paramLong1, paramLong2, paramString8, paramString7, paramBoolean, paramInt3, paramString4, paramLong3).a(asm.a);
  }
  
  public static void b(String paramString)
  {
    cnu.a(aql.a, paramString, 0).a.show();
  }
  
  public static boolean b()
  {
    return !c();
  }
  
  public static Bitmap c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = Base64.decode(paramString, 0);
    return BitmapFactory.decodeByteArray(paramString, 0, paramString.length);
  }
  
  /* Error */
  public static boolean c()
  {
    // Byte code:
    //   0: getstatic 286	org/chromium/chrome/browser/ShortcutHelper:b	Z
    //   3: ifne +94 -> 97
    //   6: getstatic 292	android/os/Build$VERSION:SDK_INT	I
    //   9: bipush 26
    //   11: if_icmplt +82 -> 93
    //   14: getstatic 46	aql:a	Landroid/content/Context;
    //   17: ldc -71
    //   19: invokevirtual 296	android/content/Context:getSystemService	(Ljava/lang/Class;)Ljava/lang/Object;
    //   22: checkcast 185	android/content/pm/ShortcutManager
    //   25: putstatic 183	org/chromium/chrome/browser/ShortcutHelper:c	Landroid/content/pm/ShortcutManager;
    //   28: invokestatic 301	aqP:b	()LaqP;
    //   31: astore_2
    //   32: aconst_null
    //   33: astore_0
    //   34: getstatic 183	org/chromium/chrome/browser/ShortcutHelper:c	Landroid/content/pm/ShortcutManager;
    //   37: invokevirtual 304	android/content/pm/ShortcutManager:isRequestPinShortcutSupported	()Z
    //   40: putstatic 306	org/chromium/chrome/browser/ShortcutHelper:a	Z
    //   43: aload_2
    //   44: ifnull +49 -> 93
    //   47: aload_2
    //   48: invokevirtual 309	aqP:close	()V
    //   51: goto +42 -> 93
    //   54: astore_1
    //   55: goto +8 -> 63
    //   58: astore_1
    //   59: aload_1
    //   60: astore_0
    //   61: aload_1
    //   62: athrow
    //   63: aload_2
    //   64: ifnull +27 -> 91
    //   67: aload_0
    //   68: ifnull +19 -> 87
    //   71: aload_2
    //   72: invokevirtual 309	aqP:close	()V
    //   75: goto +16 -> 91
    //   78: astore_2
    //   79: aload_0
    //   80: aload_2
    //   81: invokestatic 314	acT:a	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   84: goto +7 -> 91
    //   87: aload_2
    //   88: invokevirtual 309	aqP:close	()V
    //   91: aload_1
    //   92: athrow
    //   93: iconst_1
    //   94: putstatic 286	org/chromium/chrome/browser/ShortcutHelper:b	Z
    //   97: getstatic 306	org/chromium/chrome/browser/ShortcutHelper:a	Z
    //   100: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   33	47	0	localObject1	Object
    //   54	1	1	localObject2	Object
    //   58	34	1	localThrowable1	Throwable
    //   31	41	2	localAqP	aqP
    //   78	10	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   34	43	54	finally
    //   61	63	54	finally
    //   34	43	58	java/lang/Throwable
    //   71	75	78	java/lang/Throwable
  }
  
  @CalledByNative
  public static Bitmap createHomeScreenIconFromWebIcon(Bitmap paramBitmap, boolean paramBoolean)
  {
    int j = Math.min(Math.round(((ActivityManager)aql.a.getSystemService("activity")).getLauncherLargeIconSize() * 1.25F), Math.max(paramBitmap.getWidth(), paramBitmap.getHeight()));
    Rect localRect = new Rect(0, 0, j, j);
    int i;
    if (paramBoolean)
    {
      i = Math.round(j * 0.15454549F);
    }
    else
    {
      i = paramBitmap.getWidth() - 1;
      int k = paramBitmap.getHeight() - 1;
      if ((Color.alpha(paramBitmap.getPixel(0, 0)) != 0) && (Color.alpha(paramBitmap.getPixel(i, k)) != 0) && (Color.alpha(paramBitmap.getPixel(0, k)) != 0) && (Color.alpha(paramBitmap.getPixel(i, 0)) != 0)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        i = Math.round(j * 0.045454547F);
      } else {
        i = 0;
      }
    }
    j = i * 2 + j;
    localRect.offset(i, i);
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(j, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint(1);
      localPaint.setFilterBitmap(true);
      localCanvas.drawBitmap(paramBitmap, null, localRect, localPaint);
      return localBitmap;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;) {}
    }
    aqv.c("ShortcutHelper", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
    return paramBitmap;
  }
  
  public static String d(String paramString)
  {
    return Base64.encodeToString(bNs.a(paramString), 0);
  }
  
  @CalledByNative
  public static Bitmap generateHomeScreenIcon(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject1 = aql.a;
    Object localObject2 = (ActivityManager)((Context)localObject1).getSystemService("activity");
    int j = ((ActivityManager)localObject2).getLauncherLargeIconSize();
    int k = ((ActivityManager)localObject2).getLauncherLargeIconDensity();
    try
    {
      localObject2 = Bitmap.createBitmap(j, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas((Bitmap)localObject2);
      float f = j;
      int i = (int)(0.083333336F * f);
      Rect localRect = new Rect(0, 0, j, j);
      localObject1 = ((Context)localObject1).getResources();
      if (Build.VERSION.SDK_INT >= 21) {
        localObject1 = ((Resources)localObject1).getDrawableForDensity(2131755012, k, null);
      } else {
        localObject1 = ((Resources)localObject1).getDrawableForDensity(2131755012, k);
      }
      if ((localObject1 instanceof BitmapDrawable)) {
        localObject1 = ((BitmapDrawable)localObject1).getBitmap();
      } else {
        localObject1 = null;
      }
      localCanvas.drawBitmap((Bitmap)localObject1, null, localRect, new Paint(2));
      j -= i * 2;
      k = Math.round(0.0625F * f);
      int m = Math.round(f * 0.33333334F);
      paramString = new bOU(j, j, k, Color.rgb(paramInt1, paramInt2, paramInt3), m).a(paramString, false);
      if (paramString == null) {
        return null;
      }
      f = i;
      localCanvas.drawBitmap(paramString, f, f, null);
      return localObject2;
    }
    catch (OutOfMemoryError paramString)
    {
      for (;;) {}
    }
    aqv.b("ShortcutHelper", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
    return null;
  }
  
  @CalledByNative
  private static int[] getHomeScreenIconAndSplashImageSizes()
  {
    Context localContext = aql.a;
    int i = a(localContext, 2131165883);
    float f1 = localContext.getResources().getDimension(2131165883);
    float f2 = localContext.getResources().getDisplayMetrics().density;
    return new int[] { i, Math.round(f1 / f2 * (f2 - 1.0F)), a(localContext, 2131165884), a(localContext, 2131165885), a(localContext, 2131165882) };
  }
  
  @CalledByNative
  public static String getScopeFromUrl(String paramString)
  {
    Uri localUri = Uri.parse(paramString);
    Object localObject = localUri.getEncodedPath();
    int i;
    if (localObject == null) {
      i = -1;
    } else {
      i = ((String)localObject).lastIndexOf("/");
    }
    if (i < 0)
    {
      paramString = "/";
    }
    else
    {
      paramString = (String)localObject;
      if (i < ((String)localObject).length() - 1) {
        paramString = ((String)localObject).substring(0, i + 1);
      }
    }
    localObject = localUri.buildUpon();
    ((Uri.Builder)localObject).encodedPath(paramString);
    ((Uri.Builder)localObject).fragment("");
    ((Uri.Builder)localObject).query("");
    return ((Uri.Builder)localObject).build().toString();
  }
  
  @CalledByNative
  public static boolean isIconLargeEnoughForLauncher(int paramInt1, int paramInt2)
  {
    int i = ((ActivityManager)aql.a.getSystemService("activity")).getLauncherLargeIconSize() / 2;
    return (paramInt1 >= i) && (paramInt2 >= i);
  }
  
  private static native void nativeOnWebApksRetrieved(long paramLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, String[] paramArrayOfString7, int[] paramArrayOfInt3, int[] paramArrayOfInt4, long[] paramArrayOfLong1, long[] paramArrayOfLong2, long[] paramArrayOfLong3, boolean[] paramArrayOfBoolean);
  
  private static native void nativeOnWebappDataStored(long paramLong);
  
  @CalledByNative
  private static String queryFirstWebApkPackage(String paramString)
  {
    return cnK.a(aql.a, paramString);
  }
  
  @CalledByNative
  public static void retrieveWebApks(long paramLong)
  {
    ArrayList localArrayList2 = new ArrayList();
    Object localObject15 = new ArrayList();
    Object localObject14 = new ArrayList();
    Object localObject13 = new ArrayList();
    Object localObject12 = new ArrayList();
    Object localObject11 = new ArrayList();
    Object localObject10 = new ArrayList();
    Object localObject9 = new ArrayList();
    Object localObject8 = new ArrayList();
    Object localObject3 = new ArrayList();
    Object localObject2 = new ArrayList();
    Object localObject7 = new ArrayList();
    Object localObject6 = new ArrayList();
    Object localObject1 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject4 = aql.a;
    Object localObject5 = ((Context)localObject4).getPackageManager();
    localObject5 = ((PackageManager)localObject5).getInstalledPackages(0).iterator();
    while (((Iterator)localObject5).hasNext())
    {
      Object localObject16 = (PackageInfo)((Iterator)localObject5).next();
      if (cnK.b((Context)localObject4, ((PackageInfo)localObject16).packageName))
      {
        bML localBML = bML.a(((PackageInfo)localObject16).packageName, "", 0, false, false, null);
        if (localBML != null)
        {
          localArrayList2.add(localBML.m);
          ((List)localObject15).add(localBML.n);
          ((List)localObject14).add(localBML.b());
          ((List)localObject13).add(Integer.valueOf(localBML.c));
          ((List)localObject12).add(Integer.valueOf(((PackageInfo)localObject16).versionCode));
          ((List)localObject11).add(localBML.k.toString());
          ((List)localObject10).add(localBML.l.toString());
          ((List)localObject9).add(localBML.d);
          ((List)localObject8).add(localBML.e);
          ((List)localObject3).add(Integer.valueOf(localBML.o));
          ((List)localObject2).add(Integer.valueOf(localBML.p));
          ((List)localObject7).add(Long.valueOf(localBML.r));
          ((List)localObject6).add(Long.valueOf(localBML.s));
          localObject16 = WebappRegistry.a().b(localBML.j);
          long l = 0L;
          boolean bool;
          if (localObject16 != null)
          {
            l = ((bNv)localObject16).g();
            bool = ((bNv)localObject16).j();
          }
          else
          {
            bool = false;
          }
          ((List)localObject1).add(Long.valueOf(l));
          localArrayList1.add(Boolean.valueOf(bool));
        }
      }
    }
    int i = 0;
    localObject4 = (String[])localArrayList2.toArray(new String[0]);
    localObject5 = (String[])((List)localObject15).toArray(new String[0]);
    localObject14 = (String[])((List)localObject14).toArray(new String[0]);
    localObject13 = aqh.a((List)localObject13);
    localObject12 = aqh.a((List)localObject12);
    localObject11 = (String[])((List)localObject11).toArray(new String[0]);
    localObject10 = (String[])((List)localObject10).toArray(new String[0]);
    localObject9 = (String[])((List)localObject9).toArray(new String[0]);
    localObject8 = (String[])((List)localObject8).toArray(new String[0]);
    localObject3 = aqh.a((List)localObject3);
    localObject2 = aqh.a((List)localObject2);
    localObject7 = aqh.b((List)localObject7);
    localObject6 = aqh.b((List)localObject6);
    localObject1 = aqh.b((List)localObject1);
    localObject15 = new boolean[localArrayList1.size()];
    while (i < localArrayList1.size())
    {
      localObject15[i] = ((Boolean)localArrayList1.get(i)).booleanValue();
      i += 1;
    }
    nativeOnWebApksRetrieved(paramLong, (String[])localObject4, (String[])localObject5, (String[])localObject14, (int[])localObject13, (int[])localObject12, (String[])localObject11, (String[])localObject10, (String[])localObject9, (String[])localObject8, (int[])localObject3, (int[])localObject2, (long[])localObject7, (long[])localObject6, (long[])localObject1, (boolean[])localObject15);
  }
  
  @CalledByNative
  private static void showWebApkInstallInProgressToast()
  {
    b(aql.a.getString(2131953270));
  }
  
  @CalledByNative
  private static void storeWebappSplashImage(String paramString, Bitmap paramBitmap)
  {
    paramString = WebappRegistry.a().b(paramString);
    if (paramString != null) {
      new axe(paramBitmap, paramString).a(asm.a);
    }
  }
}
