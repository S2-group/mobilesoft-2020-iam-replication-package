package org.chromium.chrome.browser;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.webapps.WebApkInfo;
import org.chromium.chrome.browser.webapps.WebappAuthenticator;
import org.chromium.chrome.browser.webapps.WebappInfo;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.chrome.browser.widget.RoundedIconGenerator;
import org.chromium.webapk.lib.client.WebApkValidator;

public class ShortcutHelper
{
  private static boolean sCheckedIfRequestPinShortcutSupported;
  private static boolean sIsRequestPinShortcutSupported;
  private static Object sShortcutManager;
  
  static
  {
    new ShortcutHelper.Delegate();
  }
  
  public ShortcutHelper() {}
  
  @CalledByNative
  private static void addShortcut(String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, int paramInt)
  {
    Context localContext = ContextUtils.sApplicationContext;
    paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    paramString2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_id", paramString1);
    paramString2.putExtra("org.chromium.chrome.browser.webapp_source", paramInt);
    paramString2.setPackage(localContext.getPackageName());
    ShortcutHelper.Delegate.addShortcutToHomescreen(paramString3, paramBitmap, paramString2);
    if (shouldShowToastWhenAddingShortcut()) {
      showAddedToHomescreenToast(paramString3);
    }
  }
  
  @TargetApi(26)
  static void addShortcutWithShortcutManager(String paramString, Bitmap paramBitmap, Intent paramIntent)
  {
    Object localObject = paramIntent.getStringExtra("org.chromium.chrome.browser.webapp_id");
    Context localContext = ContextUtils.sApplicationContext;
    try
    {
      Class localClass = Class.forName("android.content.pm.ShortcutInfo$Builder");
      localObject = localClass.getDeclaredConstructor(new Class[] { Context.class, String.class }).newInstance(new Object[] { localContext, localObject });
      localClass.getMethod("setShortLabel", new Class[] { CharSequence.class }).invoke(localObject, new Object[] { paramString });
      localClass.getMethod("setLongLabel", new Class[] { CharSequence.class }).invoke(localObject, new Object[] { paramString });
      localClass.getMethod("setIcon", new Class[] { Icon.class }).invoke(localObject, new Object[] { Icon.createWithBitmap(paramBitmap) });
      localClass.getMethod("setIntent", new Class[] { Intent.class }).invoke(localObject, new Object[] { paramIntent });
      paramString = localClass.getMethod("build", new Class[0]).invoke(localObject, new Object[0]);
      paramBitmap = Class.forName("android.content.pm.ShortcutInfo");
      sShortcutManager.getClass().getMethod("requestPinShortcut", new Class[] { paramBitmap, IntentSender.class }).invoke(sShortcutManager, new Object[] { paramString, null });
      return;
    }
    catch (ClassNotFoundException paramString)
    {
      Log.e("ShortcutHelper", "Error adding shortcut with ShortcutManager:", new Object[] { paramString });
      return;
    }
    catch (InstantiationException paramString)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramString)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException paramString)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramString)
    {
      for (;;) {}
    }
  }
  
  @CalledByNative
  private static void addWebapp(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3)
  {
    new ShortcutHelper.1(paramBitmap, paramString3, paramString2, paramString1, paramString5, paramString6, paramInt1, paramInt2, paramLong1, paramLong2, paramString7, paramInt3, paramString4, paramLong3).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public static Intent createAddToHomeIntent(String paramString, Bitmap paramBitmap, Intent paramIntent)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
    return localIntent;
  }
  
  @CalledByNative
  public static Bitmap createHomeScreenIconFromWebIcon(Bitmap paramBitmap)
  {
    int k = Math.min(Math.round(((ActivityManager)ContextUtils.sApplicationContext.getSystemService("activity")).getLauncherLargeIconSize() * 1.25F), Math.max(paramBitmap.getWidth(), paramBitmap.getHeight()));
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
        Log.e("ShortcutHelper", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
      }
    }
    return paramBitmap;
  }
  
  public static Intent createWebappShortcutIntent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setAction(paramString2).putExtra("org.chromium.chrome.browser.webapp_id", paramString1).putExtra("org.chromium.chrome.browser.webapp_url", paramString3).putExtra("org.chromium.chrome.browser.webapp_scope", paramString4).putExtra("org.chromium.chrome.browser.webapp_name", paramString5).putExtra("org.chromium.chrome.browser.webapp_short_name", paramString6).putExtra("org.chromium.chrome.browser.webapp_icon", paramString7).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", paramInt1).putExtra("org.chromium.chrome.browser.webapp_display_mode", paramInt2).putExtra("org.chromium.content_public.common.orientation", paramInt3).putExtra("org.chromium.chrome.browser.theme_color", paramLong1).putExtra("org.chromium.chrome.browser.background_color", paramLong2).putExtra("org.chromium.chrome.browser.is_icon_generated", paramBoolean);
    return localIntent;
  }
  
  public static Bitmap decodeBitmapFromString(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = Base64.decode(paramString, 0);
    return BitmapFactory.decodeByteArray(paramString, 0, paramString.length);
  }
  
  public static String encodeBitmapAsString(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return "";
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 0);
  }
  
  @CalledByNative
  public static Bitmap generateHomeScreenIcon(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject1 = ContextUtils.sApplicationContext;
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
        localObject1 = ((Context)localObject1).getResources();
        if (Build.VERSION.SDK_INT >= 21)
        {
          localObject1 = ((Resources)localObject1).getDrawableForDensity(2130903047, k, null);
          if (!(localObject1 instanceof BitmapDrawable)) {
            break label240;
          }
          localObject1 = ((BitmapDrawable)localObject1).getBitmap();
          localCanvas.drawBitmap((Bitmap)localObject1, null, localRect, new Paint(2));
          k = j - i * 2;
          int m = Math.round(0.0625F * j);
          j = Math.round(0.33333334F * j);
          paramString = new RoundedIconGenerator(k, k, m, Color.rgb(paramInt1, paramInt2, paramInt3), j).generateIconForUrl(paramString, false);
          if (paramString != null) {
            break;
          }
          return null;
        }
      }
      catch (OutOfMemoryError paramString)
      {
        Log.w("ShortcutHelper", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
        return null;
      }
      localObject1 = ((Resources)localObject1).getDrawableForDensity(2130903047, k);
      continue;
      label240:
      localObject1 = null;
    }
    localCanvas.drawBitmap(paramString, i, i, null);
    return localObject2;
  }
  
  public static String getEncodedMac(Context paramContext, String paramString)
  {
    return Base64.encodeToString(WebappAuthenticator.getMacForUrl(paramContext, paramString), 0);
  }
  
  @CalledByNative
  private static int[] getHomeScreenIconAndSplashImageSizes()
  {
    Context localContext = ContextUtils.sApplicationContext;
    int i = getSizeFromResourceInPx(localContext, 2131624230);
    float f1 = localContext.getResources().getDimension(2131624230);
    float f2 = localContext.getResources().getDisplayMetrics().density;
    return new int[] { i, Math.round(f1 / f2 * (f2 - 1.0F)), getSizeFromResourceInPx(localContext, 2131624232), getSizeFromResourceInPx(localContext, 2131624234), getSizeFromResourceInPx(localContext, 2131624238) };
  }
  
  @CalledByNative
  public static String getScopeFromUrl(String paramString)
  {
    Object localObject = Uri.parse(paramString);
    paramString = ((Uri)localObject).getPathSegments();
    int j = paramString.size();
    int i = j;
    if (j > 0)
    {
      i = j;
      if (!((Uri)localObject).getPath().endsWith("/")) {
        i = j - 1;
      }
    }
    Uri.Builder localBuilder = ((Uri)localObject).buildUpon();
    localObject = "/" + TextUtils.join("/", paramString.subList(0, i));
    paramString = (String)localObject;
    if (((String)localObject).length() > 1) {
      paramString = (String)localObject + "/";
    }
    localBuilder.path(paramString);
    localBuilder.fragment("");
    localBuilder.query("");
    return localBuilder.build().toString();
  }
  
  private static int getSizeFromResourceInPx(Context paramContext, int paramInt)
  {
    return Math.round(paramContext.getResources().getDimension(paramInt));
  }
  
  private static int[] integerListToIntArray(List paramList)
  {
    int[] arrayOfInt = new int[paramList.size()];
    int i = 0;
    while (i < paramList.size())
    {
      arrayOfInt[i] = ((Integer)paramList.get(i)).intValue();
      i += 1;
    }
    return arrayOfInt;
  }
  
  @SuppressLint({"WrongConstant"})
  public static boolean isAddToHomeIntentSupported()
  {
    if (isRequestPinShortcutSupported()) {}
    while (!ContextUtils.sApplicationContext.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty()) {
      return true;
    }
    return false;
  }
  
  @CalledByNative
  public static boolean isIconLargeEnoughForLauncher(int paramInt1, int paramInt2)
  {
    int i = ((ActivityManager)ContextUtils.sApplicationContext.getSystemService("activity")).getLauncherLargeIconSize() / 2;
    return (paramInt1 >= i) && (paramInt2 >= i);
  }
  
  static boolean isRequestPinShortcutSupported()
  {
    if ((sCheckedIfRequestPinShortcutSupported) || (BuildInfo.isAtLeastO())) {}
    try
    {
      Class localClass = Class.forName("android.content.pm.ShortcutManager");
      sShortcutManager = ContextUtils.sApplicationContext.getSystemService(localClass);
      sIsRequestPinShortcutSupported = ((Boolean)localClass.getMethod("isRequestPinShortcutSupported", new Class[0]).invoke(sShortcutManager, new Object[0])).booleanValue();
      sCheckedIfRequestPinShortcutSupported = true;
      return sIsRequestPinShortcutSupported;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        Log.e("ShortcutHelper", "Error checking if RequestPinShortcut is supported:", new Object[] { localClassNotFoundException });
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  private static long[] longListToLongArray(List paramList)
  {
    long[] arrayOfLong = new long[paramList.size()];
    int i = 0;
    while (i < paramList.size())
    {
      arrayOfLong[i] = ((Long)paramList.get(i)).longValue();
      i += 1;
    }
    return arrayOfLong;
  }
  
  private static native void nativeOnWebApksRetrieved(long paramLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, String[] paramArrayOfString7, int[] paramArrayOfInt3, int[] paramArrayOfInt4, long[] paramArrayOfLong1, long[] paramArrayOfLong2);
  
  private static native void nativeOnWebappDataStored(long paramLong);
  
  @CalledByNative
  private static String queryWebApkPackage(String paramString)
  {
    return WebApkValidator.queryWebApkPackage(ContextUtils.sApplicationContext, paramString);
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
    Context localContext = ContextUtils.sApplicationContext;
    Iterator localIterator = localContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (WebApkValidator.isValidWebApk(localContext, localPackageInfo.packageName))
      {
        WebApkInfo localWebApkInfo = WebApkInfo.create(localPackageInfo.packageName, "", 0, false);
        if (localWebApkInfo != null)
        {
          localArrayList1.add(localWebApkInfo.mName);
          localArrayList2.add(localWebApkInfo.mShortName);
          localArrayList3.add(localWebApkInfo.mWebApkPackageName);
          localArrayList4.add(Integer.valueOf(localWebApkInfo.mShellApkVersion));
          localArrayList5.add(Integer.valueOf(localPackageInfo.versionCode));
          localArrayList6.add(localWebApkInfo.mUri.toString());
          localArrayList7.add(localWebApkInfo.mScopeUri.toString());
          localArrayList8.add(localWebApkInfo.mManifestUrl);
          localArrayList9.add(localWebApkInfo.mManifestStartUrl);
          localArrayList10.add(Integer.valueOf(localWebApkInfo.mDisplayMode));
          localArrayList11.add(Integer.valueOf(localWebApkInfo.mOrientation));
          localArrayList12.add(Long.valueOf(localWebApkInfo.mThemeColor));
          localArrayList13.add(Long.valueOf(localWebApkInfo.mBackgroundColor));
        }
      }
    }
    nativeOnWebApksRetrieved(paramLong, (String[])localArrayList1.toArray(new String[0]), (String[])localArrayList2.toArray(new String[0]), (String[])localArrayList3.toArray(new String[0]), integerListToIntArray(localArrayList4), integerListToIntArray(localArrayList5), (String[])localArrayList6.toArray(new String[0]), (String[])localArrayList7.toArray(new String[0]), (String[])localArrayList8.toArray(new String[0]), (String[])localArrayList9.toArray(new String[0]), integerListToIntArray(localArrayList10), integerListToIntArray(localArrayList11), longListToLongArray(localArrayList12), longListToLongArray(localArrayList13));
  }
  
  static boolean shouldShowToastWhenAddingShortcut()
  {
    return !isRequestPinShortcutSupported();
  }
  
  static void showAddedToHomescreenToast(String paramString)
  {
    showToast(ContextUtils.sApplicationContext.getString(2131427940, new Object[] { paramString }));
  }
  
  private static void showToast(String paramString)
  {
    org.chromium.ui.widget.Toast.makeText(ContextUtils.sApplicationContext, paramString, 0).mToast.show();
  }
  
  @CalledByNative
  private static void showWebApkInstallInProgressToast()
  {
    showToast(ContextUtils.sApplicationContext.getString(2131427941));
  }
  
  @CalledByNative
  private static void storeWebappSplashImage(String paramString, Bitmap paramBitmap)
  {
    paramString = WebappRegistry.getInstance().getWebappDataStorage(paramString);
    if (paramString != null) {
      new ShortcutHelper.2(paramBitmap, paramString).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
  }
}
