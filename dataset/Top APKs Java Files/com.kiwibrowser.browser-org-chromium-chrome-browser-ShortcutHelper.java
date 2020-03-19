package org.chromium.chrome.browser;

import TU;
import Uh;
import Um;
import Ux;
import XA;
import XG;
import XI;
import aaW;
import aaY;
import aaZ;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.Toast;
import bCi;
import bCy;
import bcC;
import bcG;
import bcQ;
import bcb;
import ben;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.webapps.WebappRegistry;

public class ShortcutHelper
{
  private static boolean a;
  private static boolean b;
  private static ShortcutManager c;
  private static aaZ d;
  
  static
  {
    if (!ShortcutHelper.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      e = bool;
      d = new aaZ();
      return;
    }
  }
  
  public ShortcutHelper() {}
  
  private static int a(Context paramContext, int paramInt)
  {
    return Math.round(paramContext.getResources().getDimension(paramInt));
  }
  
  public static Intent a(String paramString, Bitmap paramBitmap, Intent paramIntent)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
    return localIntent;
  }
  
  public static Intent a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, String paramString8, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(Um.a.getPackageName()).setAction(paramString2).putExtra("org.chromium.chrome.browser.webapp_id", paramString1).putExtra("org.chromium.chrome.browser.webapp_url", paramString3).putExtra("org.chromium.chrome.browser.webapp_scope", paramString4).putExtra("org.chromium.chrome.browser.webapp_name", paramString5).putExtra("org.chromium.chrome.browser.webapp_short_name", paramString6).putExtra("org.chromium.chrome.browser.webapp_icon", paramString7).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", paramInt1).putExtra("org.chromium.chrome.browser.webapp_display_mode", paramInt2).putExtra("org.chromium.content_public.common.orientation", paramInt3).putExtra("org.chromium.chrome.browser.theme_color", paramLong1).putExtra("org.chromium.chrome.browser.background_color", paramLong2).putExtra("org.chromium.chrome.browser.webapp_splash_screen_url", paramString8).putExtra("org.chromium.chrome.browser.is_icon_generated", paramBoolean);
    return localIntent;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    return Base64.encodeToString(bcC.a(paramContext, paramString), 0);
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
    if ((!e) && (!ThreadUtils.d())) {
      throw new AssertionError();
    }
    bCi.a(Um.a, paramString, 0).a.show();
  }
  
  @SuppressLint({"WrongConstant"})
  public static boolean a()
  {
    if (f()) {}
    while (!Um.a.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty()) {
      return true;
    }
    return false;
  }
  
  @CalledByNative
  private static void addShortcut(String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, int paramInt)
  {
    Context localContext = Um.a;
    paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    paramString2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_id", paramString1);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_source", paramInt);
    paramString2.setPackage(localContext.getPackageName());
    aaZ.a(paramString3, paramBitmap, paramString2);
    if (e()) {
      d(paramString3);
    }
  }
  
  @CalledByNative
  private static void addWebapp(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, String paramString8, long paramLong3)
  {
    new aaW(paramBitmap, paramString3, paramString2, paramString1, paramString5, paramString6, paramInt1, paramInt2, paramLong1, paramLong2, paramString8, paramString7, paramInt3, paramString4, paramLong3).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public static Bitmap b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = Base64.decode(paramString, 0);
    return BitmapFactory.decodeByteArray(paramString, 0, paramString.length);
  }
  
  @CalledByNative
  public static Bitmap createHomeScreenIconFromWebIcon(Bitmap paramBitmap)
  {
    int k = Math.min(Math.round(((ActivityManager)Um.a.getSystemService("activity")).getLauncherLargeIconSize() * 1.25F), Math.max(paramBitmap.getWidth(), paramBitmap.getHeight()));
    Rect localRect = new Rect(0, 0, k, k);
    int i = paramBitmap.getWidth() - 1;
    int j = paramBitmap.getHeight() - 1;
    if ((Color.alpha(paramBitmap.getPixel(0, 0)) != 0) && (Color.alpha(paramBitmap.getPixel(i, j)) != 0) && (Color.alpha(paramBitmap.getPixel(0, j)) != 0) && (Color.alpha(paramBitmap.getPixel(i, 0)) != 0)) {}
    for (i = 1;; i = 0)
    {
      j = k;
      if (i != 0)
      {
        i = Math.round(0.045454547F * k);
        j = k + i * 2;
        localRect.offset(i, i);
      }
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
        Ux.c("ShortcutHelper", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
      }
    }
    return paramBitmap;
  }
  
  private static void d(String paramString)
  {
    a(Um.a.getString(XI.af, new Object[] { paramString }));
  }
  
  private static boolean e()
  {
    return !f();
  }
  
  /* Error */
  private static boolean f()
  {
    // Byte code:
    //   0: getstatic 411	org/chromium/chrome/browser/ShortcutHelper:b	Z
    //   3: ifne +53 -> 56
    //   6: getstatic 416	android/os/Build$VERSION:SDK_INT	I
    //   9: bipush 26
    //   11: if_icmplt +41 -> 52
    //   14: getstatic 78	Um:a	Landroid/content/Context;
    //   17: ldc_w 304
    //   20: invokevirtual 419	android/content/Context:getSystemService	(Ljava/lang/Class;)Ljava/lang/Object;
    //   23: checkcast 304	android/content/pm/ShortcutManager
    //   26: putstatic 302	org/chromium/chrome/browser/ShortcutHelper:c	Landroid/content/pm/ShortcutManager;
    //   29: invokestatic 424	UU:c	()LUU;
    //   32: astore_2
    //   33: aconst_null
    //   34: astore_1
    //   35: getstatic 302	org/chromium/chrome/browser/ShortcutHelper:c	Landroid/content/pm/ShortcutManager;
    //   38: invokevirtual 427	android/content/pm/ShortcutManager:isRequestPinShortcutSupported	()Z
    //   41: putstatic 429	org/chromium/chrome/browser/ShortcutHelper:a	Z
    //   44: aload_2
    //   45: ifnull +7 -> 52
    //   48: aload_2
    //   49: invokevirtual 432	UU:close	()V
    //   52: iconst_1
    //   53: putstatic 411	org/chromium/chrome/browser/ShortcutHelper:b	Z
    //   56: getstatic 429	org/chromium/chrome/browser/ShortcutHelper:a	Z
    //   59: ireturn
    //   60: astore_1
    //   61: aload_1
    //   62: athrow
    //   63: astore_0
    //   64: aload_2
    //   65: ifnull +11 -> 76
    //   68: aload_1
    //   69: ifnull +18 -> 87
    //   72: aload_2
    //   73: invokevirtual 432	UU:close	()V
    //   76: aload_0
    //   77: athrow
    //   78: astore_2
    //   79: aload_1
    //   80: aload_2
    //   81: invokestatic 437	KU:a	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   84: goto -8 -> 76
    //   87: aload_2
    //   88: invokevirtual 432	UU:close	()V
    //   91: goto -15 -> 76
    //   94: astore_0
    //   95: goto -31 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   63	14	0	localObject1	Object
    //   94	1	0	localObject2	Object
    //   34	1	1	localObject3	Object
    //   60	20	1	localThrowable1	Throwable
    //   32	41	2	localUU	UU
    //   78	10	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   35	44	60	java/lang/Throwable
    //   61	63	63	finally
    //   72	76	78	java/lang/Throwable
    //   35	44	94	finally
  }
  
  @CalledByNative
  public static Bitmap generateHomeScreenIcon(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject1 = Um.a;
    Object localObject2 = (ActivityManager)((Context)localObject1).getSystemService("activity");
    int j = ((ActivityManager)localObject2).getLauncherLargeIconSize();
    int k = ((ActivityManager)localObject2).getLauncherLargeIconDensity();
    Canvas localCanvas;
    int i;
    for (;;)
    {
      try
      {
        localObject2 = Bitmap.createBitmap(j, j, Bitmap.Config.ARGB_8888);
        localCanvas = new Canvas((Bitmap)localObject2);
        i = (int)(0.083333336F * j);
        Rect localRect = new Rect(0, 0, j, j);
        int m = XG.b;
        localObject1 = TU.a(((Context)localObject1).getResources(), m, k);
        if ((localObject1 instanceof BitmapDrawable))
        {
          localObject1 = ((BitmapDrawable)localObject1).getBitmap();
          localCanvas.drawBitmap((Bitmap)localObject1, null, localRect, new Paint(2));
          k = j - i * 2;
          m = Math.round(0.0625F * j);
          j = Math.round(0.33333334F * j);
          paramString = new ben(k, k, m, Color.rgb(paramInt1, paramInt2, paramInt3), j).a(paramString, false);
          if (paramString != null) {
            break;
          }
          return null;
        }
      }
      catch (OutOfMemoryError paramString)
      {
        Ux.b("ShortcutHelper", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
        return null;
      }
      if (!e) {
        throw new AssertionError("The drawable was not a bitmap drawable as expected");
      }
      localObject1 = null;
    }
    localCanvas.drawBitmap(paramString, i, i, null);
    return localObject2;
  }
  
  @CalledByNative
  private static int[] getHomeScreenIconAndSplashImageSizes()
  {
    Context localContext = Um.a;
    int i = a(localContext, XA.dP);
    float f1 = localContext.getResources().getDimension(XA.dP);
    float f2 = localContext.getResources().getDisplayMetrics().density;
    return new int[] { i, Math.round(f1 / f2 * (f2 - 1.0F)), a(localContext, XA.dQ), a(localContext, XA.dR), a(localContext, XA.dO) };
  }
  
  @CalledByNative
  public static String getScopeFromUrl(String paramString)
  {
    Uri localUri = Uri.parse(paramString);
    Object localObject = localUri.getEncodedPath();
    int i;
    if (localObject == null)
    {
      i = -1;
      if (i >= 0) {
        break label68;
      }
      paramString = "/";
    }
    for (;;)
    {
      localObject = localUri.buildUpon();
      ((Uri.Builder)localObject).encodedPath(paramString);
      ((Uri.Builder)localObject).fragment("");
      ((Uri.Builder)localObject).query("");
      return ((Uri.Builder)localObject).build().toString();
      i = ((String)localObject).lastIndexOf("/");
      break;
      label68:
      paramString = (String)localObject;
      if (i < ((String)localObject).length() - 1) {
        paramString = ((String)localObject).substring(0, i + 1);
      }
    }
  }
  
  @CalledByNative
  public static boolean isIconLargeEnoughForLauncher(int paramInt1, int paramInt2)
  {
    int i = ((ActivityManager)Um.a.getSystemService("activity")).getLauncherLargeIconSize() / 2;
    return (paramInt1 >= i) && (paramInt2 >= i);
  }
  
  private static native void nativeOnWebApksRetrieved(long paramLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, String[] paramArrayOfString7, int[] paramArrayOfInt3, int[] paramArrayOfInt4, long[] paramArrayOfLong1, long[] paramArrayOfLong2, long[] paramArrayOfLong3, boolean[] paramArrayOfBoolean);
  
  private static native void nativeOnWebappDataStored(long paramLong);
  
  @CalledByNative
  private static String queryWebApkPackage(String paramString)
  {
    return bCy.a(Um.a, paramString);
  }
  
  @CalledByNative
  public static void retrieveWebApks(long paramLong)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    ArrayList localArrayList5 = new ArrayList();
    ArrayList localArrayList6 = new ArrayList();
    ArrayList localArrayList7 = new ArrayList();
    ArrayList localArrayList8 = new ArrayList();
    ArrayList localArrayList9 = new ArrayList();
    ArrayList localArrayList10 = new ArrayList();
    ArrayList localArrayList11 = new ArrayList();
    ArrayList localArrayList12 = new ArrayList();
    ArrayList localArrayList13 = new ArrayList();
    ArrayList localArrayList14 = new ArrayList();
    ArrayList localArrayList15 = new ArrayList();
    Context localContext = Um.a;
    Iterator localIterator = localContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if (bCy.b(localContext, ((PackageInfo)localObject).packageName))
      {
        bcb localBcb = bcb.a(((PackageInfo)localObject).packageName, "", 0, false);
        if (localBcb != null)
        {
          localArrayList1.add(localBcb.j);
          localArrayList2.add(localBcb.k);
          localArrayList3.add(localBcb.b);
          localArrayList4.add(Integer.valueOf(localBcb.c));
          localArrayList5.add(Integer.valueOf(((PackageInfo)localObject).versionCode));
          localArrayList6.add(localBcb.h.toString());
          localArrayList7.add(localBcb.i.toString());
          localArrayList8.add(localBcb.d);
          localArrayList9.add(localBcb.e);
          localArrayList10.add(Integer.valueOf(localBcb.l));
          localArrayList11.add(Integer.valueOf(localBcb.m));
          localArrayList12.add(Long.valueOf(localBcb.o));
          localArrayList13.add(Long.valueOf(localBcb.p));
          localObject = WebappRegistry.a().b(localBcb.g);
          long l = 0L;
          boolean bool = false;
          if (localObject != null)
          {
            l = ((bcG)localObject).g();
            bool = ((bcG)localObject).j();
          }
          localArrayList14.add(Long.valueOf(l));
          localArrayList15.add(Boolean.valueOf(bool));
        }
      }
    }
    nativeOnWebApksRetrieved(paramLong, (String[])localArrayList1.toArray(new String[0]), (String[])localArrayList2.toArray(new String[0]), (String[])localArrayList3.toArray(new String[0]), Uh.b(localArrayList4), Uh.b(localArrayList5), (String[])localArrayList6.toArray(new String[0]), (String[])localArrayList7.toArray(new String[0]), (String[])localArrayList8.toArray(new String[0]), (String[])localArrayList9.toArray(new String[0]), Uh.b(localArrayList10), Uh.b(localArrayList11), Uh.c(localArrayList12), Uh.c(localArrayList13), Uh.c(localArrayList14), Uh.a(localArrayList15));
  }
  
  @CalledByNative
  private static void showWebApkInstallInProgressToast()
  {
    a(Um.a.getString(XI.pN));
  }
  
  @CalledByNative
  private static void storeWebappSplashImage(String paramString, Bitmap paramBitmap)
  {
    paramString = WebappRegistry.a().b(paramString);
    if (paramString != null) {
      new aaY(paramBitmap, paramString).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
  }
}
