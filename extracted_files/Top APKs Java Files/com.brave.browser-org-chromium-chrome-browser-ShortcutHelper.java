package org.chromium.chrome.browser;

import WE;
import WO;
import Wb;
import Wm;
import Wz;
import aaC;
import aaE;
import aaw;
import aei;
import aek;
import ael;
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
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.Toast;
import bNN;
import bNx;
import bmR;
import bmV;
import bmm;
import bng;
import boC;
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
  private static ael d = new ael();
  
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
    localIntent.setPackage(WE.a.getPackageName()).setAction(paramString2).putExtra("org.chromium.chrome.browser.webapp_id", paramString1).putExtra("org.chromium.chrome.browser.webapp_url", paramString3).putExtra("org.chromium.chrome.browser.webapp_scope", paramString4).putExtra("org.chromium.chrome.browser.webapp_name", paramString5).putExtra("org.chromium.chrome.browser.webapp_short_name", paramString6).putExtra("org.chromium.chrome.browser.webapp_icon", paramString7).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", paramInt1).putExtra("org.chromium.chrome.browser.webapp_display_mode", paramInt2).putExtra("org.chromium.content_public.common.orientation", paramInt3).putExtra("org.chromium.chrome.browser.theme_color", paramLong1).putExtra("org.chromium.chrome.browser.background_color", paramLong2).putExtra("org.chromium.chrome.browser.webapp_splash_screen_url", paramString8).putExtra("org.chromium.chrome.browser.is_icon_generated", paramBoolean);
    return localIntent;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    return Base64.encodeToString(bmR.a(paramContext, paramString), 0);
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
    if ((!e) && (!ThreadUtils.e())) {
      throw new AssertionError();
    }
    bNx.a(WE.a, paramString, 0).a.show();
  }
  
  @SuppressLint({"WrongConstant"})
  public static boolean a()
  {
    if (f()) {
      return true;
    }
    return !WE.a.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty();
  }
  
  @CalledByNative
  private static void addShortcut(String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, int paramInt)
  {
    Context localContext = WE.a;
    paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    paramString2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_id", paramString1);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_source", paramInt);
    paramString2.setPackage(localContext.getPackageName());
    ael.a(paramString3, paramBitmap, paramString2);
    if (e()) {
      d(paramString3);
    }
  }
  
  @CalledByNative
  private static void addWebapp(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, String paramString8, long paramLong3)
  {
    new aei(paramBitmap, paramString3, paramString2, paramString1, paramString5, paramString6, paramInt1, paramInt2, paramLong1, paramLong2, paramString8, paramString7, paramInt3, paramString4, paramLong3).a(Wm.a);
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
    int k = Math.min(Math.round(((ActivityManager)WE.a.getSystemService("activity")).getLauncherLargeIconSize() * 1.25F), Math.max(paramBitmap.getWidth(), paramBitmap.getHeight()));
    Rect localRect = new Rect(0, 0, k, k);
    int i = paramBitmap.getWidth() - 1;
    int j = paramBitmap.getHeight() - 1;
    if ((Color.alpha(paramBitmap.getPixel(0, 0)) != 0) && (Color.alpha(paramBitmap.getPixel(i, j)) != 0) && (Color.alpha(paramBitmap.getPixel(0, j)) != 0) && (Color.alpha(paramBitmap.getPixel(i, 0)) != 0)) {
      i = 1;
    } else {
      i = 0;
    }
    j = k;
    if (i != 0)
    {
      i = Math.round(k * 0.045454547F);
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
      WO.c("ShortcutHelper", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
    }
    return paramBitmap;
  }
  
  private static void d(String paramString)
  {
    a(WE.a.getString(aaE.ax, new Object[] { paramString }));
  }
  
  private static boolean e()
  {
    return !f();
  }
  
  /* Error */
  private static boolean f()
  {
    // Byte code:
    //   0: getstatic 406	org/chromium/chrome/browser/ShortcutHelper:b	Z
    //   3: ifne +95 -> 98
    //   6: getstatic 411	android/os/Build$VERSION:SDK_INT	I
    //   9: bipush 26
    //   11: if_icmplt +83 -> 94
    //   14: getstatic 78	WE:a	Landroid/content/Context;
    //   17: ldc_w 299
    //   20: invokevirtual 414	android/content/Context:getSystemService	(Ljava/lang/Class;)Ljava/lang/Object;
    //   23: checkcast 299	android/content/pm/ShortcutManager
    //   26: putstatic 297	org/chromium/chrome/browser/ShortcutHelper:c	Landroid/content/pm/ShortcutManager;
    //   29: invokestatic 419	Xj:c	()LXj;
    //   32: astore_2
    //   33: aconst_null
    //   34: astore_0
    //   35: getstatic 297	org/chromium/chrome/browser/ShortcutHelper:c	Landroid/content/pm/ShortcutManager;
    //   38: invokevirtual 422	android/content/pm/ShortcutManager:isRequestPinShortcutSupported	()Z
    //   41: putstatic 424	org/chromium/chrome/browser/ShortcutHelper:a	Z
    //   44: aload_2
    //   45: ifnull +49 -> 94
    //   48: aload_2
    //   49: invokevirtual 427	Xj:close	()V
    //   52: goto +42 -> 94
    //   55: astore_1
    //   56: goto +8 -> 64
    //   59: astore_1
    //   60: aload_1
    //   61: astore_0
    //   62: aload_1
    //   63: athrow
    //   64: aload_2
    //   65: ifnull +27 -> 92
    //   68: aload_0
    //   69: ifnull +19 -> 88
    //   72: aload_2
    //   73: invokevirtual 427	Xj:close	()V
    //   76: goto +16 -> 92
    //   79: astore_2
    //   80: aload_0
    //   81: aload_2
    //   82: invokestatic 432	Nj:a	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   85: goto +7 -> 92
    //   88: aload_2
    //   89: invokevirtual 427	Xj:close	()V
    //   92: aload_1
    //   93: athrow
    //   94: iconst_1
    //   95: putstatic 406	org/chromium/chrome/browser/ShortcutHelper:b	Z
    //   98: getstatic 424	org/chromium/chrome/browser/ShortcutHelper:a	Z
    //   101: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   34	47	0	localObject1	Object
    //   55	1	1	localObject2	Object
    //   59	34	1	localThrowable1	Throwable
    //   32	41	2	localXj	Xj
    //   79	10	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   35	44	55	finally
    //   62	64	55	finally
    //   35	44	59	java/lang/Throwable
    //   72	76	79	java/lang/Throwable
  }
  
  @CalledByNative
  public static Bitmap generateHomeScreenIcon(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject1 = WE.a;
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
      int m = aaC.b;
      localObject1 = Wb.a(((Context)localObject1).getResources(), m, k);
      if ((localObject1 instanceof BitmapDrawable))
      {
        localObject1 = ((BitmapDrawable)localObject1).getBitmap();
      }
      else
      {
        if (!e) {
          break label235;
        }
        localObject1 = null;
      }
      localCanvas.drawBitmap((Bitmap)localObject1, null, localRect, new Paint(2));
      j -= i * 2;
      k = Math.round(0.0625F * f);
      m = Math.round(f * 0.33333334F);
      paramString = new boC(j, j, k, Color.rgb(paramInt1, paramInt2, paramInt3), m).a(paramString, false);
      if (paramString == null) {
        return null;
      }
      f = i;
      localCanvas.drawBitmap(paramString, f, f, null);
      return localObject2;
      label235:
      throw new AssertionError("The drawable was not a bitmap drawable as expected");
    }
    catch (OutOfMemoryError paramString)
    {
      WO.b("ShortcutHelper", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
    }
    return null;
  }
  
  @CalledByNative
  private static int[] getHomeScreenIconAndSplashImageSizes()
  {
    Context localContext = WE.a;
    int i = a(localContext, aaw.eb);
    float f1 = localContext.getResources().getDimension(aaw.eb);
    float f2 = localContext.getResources().getDisplayMetrics().density;
    return new int[] { i, Math.round(f1 / f2 * (f2 - 1.0F)), a(localContext, aaw.ec), a(localContext, aaw.ed), a(localContext, aaw.ea) };
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
    int i = ((ActivityManager)WE.a.getSystemService("activity")).getLauncherLargeIconSize() / 2;
    return (paramInt1 >= i) && (paramInt2 >= i);
  }
  
  private static native void nativeOnWebApksRetrieved(long paramLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, String[] paramArrayOfString7, int[] paramArrayOfInt3, int[] paramArrayOfInt4, long[] paramArrayOfLong1, long[] paramArrayOfLong2, long[] paramArrayOfLong3, boolean[] paramArrayOfBoolean);
  
  private static native void nativeOnWebappDataStored(long paramLong);
  
  @CalledByNative
  private static String queryWebApkPackage(String paramString)
  {
    return bNN.a(WE.a, paramString);
  }
  
  @CalledByNative
  public static void retrieveWebApks(long paramLong)
  {
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
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Context localContext = WE.a;
    Object localObject1 = localContext.getPackageManager();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (bNN.b(localContext, ((PackageInfo)localObject2).packageName))
      {
        bmm localBmm = bmm.a(((PackageInfo)localObject2).packageName, "", 0, false);
        if (localBmm != null)
        {
          localArrayList4.add(localBmm.l);
          localArrayList5.add(localBmm.m);
          localArrayList6.add(localBmm.c);
          localArrayList7.add(Integer.valueOf(localBmm.d));
          localArrayList8.add(Integer.valueOf(((PackageInfo)localObject2).versionCode));
          localArrayList9.add(localBmm.j.toString());
          localArrayList10.add(localBmm.k.toString());
          localArrayList11.add(localBmm.e);
          localArrayList12.add(localBmm.f);
          localArrayList13.add(Integer.valueOf(localBmm.n));
          localArrayList14.add(Integer.valueOf(localBmm.o));
          localArrayList15.add(Long.valueOf(localBmm.q));
          localArrayList3.add(Long.valueOf(localBmm.r));
          localObject2 = WebappRegistry.a().b(localBmm.i);
          long l = 0L;
          boolean bool;
          if (localObject2 != null)
          {
            l = ((bmV)localObject2).g();
            bool = ((bmV)localObject2).j();
          }
          else
          {
            bool = false;
          }
          localArrayList1.add(Long.valueOf(l));
          localArrayList2.add(Boolean.valueOf(bool));
        }
        else {}
      }
    }
    nativeOnWebApksRetrieved(paramLong, (String[])localArrayList4.toArray(new String[0]), (String[])localArrayList5.toArray(new String[0]), (String[])localArrayList6.toArray(new String[0]), Wz.b(localArrayList7), Wz.b(localArrayList8), (String[])localArrayList9.toArray(new String[0]), (String[])localArrayList10.toArray(new String[0]), (String[])localArrayList11.toArray(new String[0]), (String[])localArrayList12.toArray(new String[0]), Wz.b(localArrayList13), Wz.b(localArrayList14), Wz.c(localArrayList15), Wz.c(localArrayList3), Wz.c(localArrayList1), Wz.a(localArrayList2));
  }
  
  @CalledByNative
  private static void showWebApkInstallInProgressToast()
  {
    a(WE.a.getString(aaE.rH));
  }
  
  @CalledByNative
  private static void storeWebappSplashImage(String paramString, Bitmap paramBitmap)
  {
    paramString = WebappRegistry.a().b(paramString);
    if (paramString != null) {
      new aek(paramBitmap, paramString).a(Wm.a);
    }
  }
}
