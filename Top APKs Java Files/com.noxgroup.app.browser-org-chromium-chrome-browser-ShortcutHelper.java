package org.chromium.chrome.browser;

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
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.webapps.WebApkInfo;
import org.chromium.chrome.browser.webapps.WebappAuthenticator;
import org.chromium.chrome.browser.webapps.WebappDataStorage;
import org.chromium.chrome.browser.webapps.WebappInfo;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.chrome.browser.widget.RoundedIconGenerator;
import org.chromium.ui.widget.Toast;
import org.chromium.webapk.lib.client.WebApkValidator;

public class ShortcutHelper
{
  private static boolean sCheckedIfRequestPinShortcutSupported;
  private static Delegate sDelegate = new Delegate();
  private static boolean sIsRequestPinShortcutSupported;
  private static ShortcutManager sShortcutManager;
  
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
    Delegate.addShortcutToHomescreen(paramString3, paramBitmap, paramString2);
    if (shouldShowToastWhenAddingShortcut()) {
      showAddedToHomescreenToast(paramString3);
    }
  }
  
  @CalledByNative
  private static void addWebapp(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final String paramString6, String paramString7, Bitmap paramBitmap, final int paramInt1, final int paramInt2, final int paramInt3, final long paramLong1, long paramLong2, final String paramString8, final long paramLong3)
  {
    new AsyncTask() {}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
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
      for (;;) {}
    }
    Log.e("ShortcutHelper", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
    return paramBitmap;
  }
  
  public static Intent createWebappShortcutIntent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, String paramString8, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(ContextUtils.sApplicationContext.getPackageName()).setAction(paramString2).putExtra("org.chromium.chrome.browser.webapp_id", paramString1).putExtra("org.chromium.chrome.browser.webapp_url", paramString3).putExtra("org.chromium.chrome.browser.webapp_scope", paramString4).putExtra("org.chromium.chrome.browser.webapp_name", paramString5).putExtra("org.chromium.chrome.browser.webapp_short_name", paramString6).putExtra("org.chromium.chrome.browser.webapp_icon", paramString7).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", paramInt1).putExtra("org.chromium.chrome.browser.webapp_display_mode", paramInt2).putExtra("org.chromium.content_public.common.orientation", paramInt3).putExtra("org.chromium.chrome.browser.theme_color", paramLong1).putExtra("org.chromium.chrome.browser.background_color", paramLong2).putExtra("org.chromium.chrome.browser.webapp_splash_screen_url", paramString8).putExtra("org.chromium.chrome.browser.is_icon_generated", paramBoolean);
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
    try
    {
      localObject2 = Bitmap.createBitmap(j, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas((Bitmap)localObject2);
      float f = j;
      int i = (int)(0.083333336F * f);
      Rect localRect = new Rect(0, 0, j, j);
      localObject1 = ApiCompatibilityUtils.getDrawableForDensity$5e0d298e(((Context)localObject1).getResources(), k);
      if ((localObject1 instanceof BitmapDrawable)) {
        localObject1 = ((BitmapDrawable)localObject1).getBitmap();
      } else {
        localObject1 = null;
      }
      localCanvas.drawBitmap((Bitmap)localObject1, null, localRect, new Paint(2));
      j -= i * 2;
      k = Math.round(0.0625F * f);
      int m = Math.round(f * 0.33333334F);
      paramString = new RoundedIconGenerator(j, j, k, Color.rgb(paramInt1, paramInt2, paramInt3), m).generateIconForUrl(paramString, false);
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
    Log.w("ShortcutHelper", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
    return null;
  }
  
  public static String getEncodedMac(Context paramContext, String paramString)
  {
    return Base64.encodeToString(WebappAuthenticator.getMacForUrl(paramContext, paramString), 0);
  }
  
  @CalledByNative
  private static int[] getHomeScreenIconAndSplashImageSizes()
  {
    Context localContext = ContextUtils.sApplicationContext;
    int i = getSizeFromResourceInPx(localContext, 2131167730);
    float f1 = localContext.getResources().getDimension(2131167730);
    float f2 = localContext.getResources().getDisplayMetrics().density;
    return new int[] { i, Math.round(f1 / f2 * (f2 - 1.0F)), getSizeFromResourceInPx(localContext, 2131167731), getSizeFromResourceInPx(localContext, 2131167732), getSizeFromResourceInPx(localContext, 2131167729) };
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
    localObject = new StringBuilder("/");
    ((StringBuilder)localObject).append(TextUtils.join("/", paramString.subList(0, i)));
    localObject = ((StringBuilder)localObject).toString();
    paramString = (String)localObject;
    if (((String)localObject).length() > 1)
    {
      paramString = new StringBuilder();
      paramString.append((String)localObject);
      paramString.append("/");
      paramString = paramString.toString();
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
  
  @SuppressLint({"WrongConstant"})
  public static boolean isAddToHomeIntentSupported()
  {
    if (isRequestPinShortcutSupported()) {
      return true;
    }
    return !ContextUtils.sApplicationContext.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty();
  }
  
  @CalledByNative
  public static boolean isIconLargeEnoughForLauncher(int paramInt1, int paramInt2)
  {
    int i = ((ActivityManager)ContextUtils.sApplicationContext.getSystemService("activity")).getLauncherLargeIconSize() / 2;
    return (paramInt1 >= i) && (paramInt2 >= i);
  }
  
  private static boolean isRequestPinShortcutSupported()
  {
    if (!sCheckedIfRequestPinShortcutSupported)
    {
      if (Build.VERSION.SDK_INT >= 26)
      {
        sShortcutManager = (ShortcutManager)ContextUtils.sApplicationContext.getSystemService(ShortcutManager.class);
        StrictMode.ThreadPolicy localThreadPolicy = StrictMode.allowThreadDiskReads();
        sIsRequestPinShortcutSupported = sShortcutManager.isRequestPinShortcutSupported();
        StrictMode.setThreadPolicy(localThreadPolicy);
      }
      sCheckedIfRequestPinShortcutSupported = true;
    }
    return sIsRequestPinShortcutSupported;
  }
  
  private static native void nativeOnWebApksRetrieved(long paramLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, String[] paramArrayOfString7, int[] paramArrayOfInt3, int[] paramArrayOfInt4, long[] paramArrayOfLong1, long[] paramArrayOfLong2, long[] paramArrayOfLong3, boolean[] paramArrayOfBoolean);
  
  private static native void nativeOnWebappDataStored(long paramLong);
  
  @CalledByNative
  private static String queryWebApkPackage(String paramString)
  {
    return WebApkValidator.queryWebApkPackage(ContextUtils.sApplicationContext, paramString);
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
    Context localContext = ContextUtils.sApplicationContext;
    Object localObject1 = localContext.getPackageManager();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (WebApkValidator.isValidWebApk(localContext, ((PackageInfo)localObject2).packageName))
      {
        WebApkInfo localWebApkInfo = WebApkInfo.create(((PackageInfo)localObject2).packageName, "", 0, false);
        if (localWebApkInfo != null)
        {
          localArrayList4.add(localWebApkInfo.mName);
          localArrayList5.add(localWebApkInfo.mShortName);
          localArrayList6.add(localWebApkInfo.mApkPackageName);
          localArrayList7.add(Integer.valueOf(localWebApkInfo.mShellApkVersion));
          localArrayList8.add(Integer.valueOf(((PackageInfo)localObject2).versionCode));
          localArrayList9.add(localWebApkInfo.mUri.toString());
          localArrayList10.add(localWebApkInfo.mScopeUri.toString());
          localArrayList11.add(localWebApkInfo.mManifestUrl);
          localArrayList12.add(localWebApkInfo.mManifestStartUrl);
          localArrayList13.add(Integer.valueOf(localWebApkInfo.mDisplayMode));
          localArrayList14.add(Integer.valueOf(localWebApkInfo.mOrientation));
          localArrayList15.add(Long.valueOf(localWebApkInfo.mThemeColor));
          localArrayList3.add(Long.valueOf(localWebApkInfo.mBackgroundColor));
          localObject2 = WebappRegistry.getInstance().getWebappDataStorage(localWebApkInfo.mId);
          long l = 0L;
          boolean bool;
          if (localObject2 != null)
          {
            l = ((WebappDataStorage)localObject2).getLastCheckForWebManifestUpdateTimeMs();
            bool = ((WebappDataStorage)localObject2).shouldRelaxUpdates();
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
    nativeOnWebApksRetrieved(paramLong, (String[])localArrayList4.toArray(new String[0]), (String[])localArrayList5.toArray(new String[0]), (String[])localArrayList6.toArray(new String[0]), CollectionUtil.integerListToIntArray(localArrayList7), CollectionUtil.integerListToIntArray(localArrayList8), (String[])localArrayList9.toArray(new String[0]), (String[])localArrayList10.toArray(new String[0]), (String[])localArrayList11.toArray(new String[0]), (String[])localArrayList12.toArray(new String[0]), CollectionUtil.integerListToIntArray(localArrayList13), CollectionUtil.integerListToIntArray(localArrayList14), CollectionUtil.longListToLongArray(localArrayList15), CollectionUtil.longListToLongArray(localArrayList3), CollectionUtil.longListToLongArray(localArrayList1), CollectionUtil.booleanListToBooleanArray(localArrayList2));
  }
  
  private static boolean shouldShowToastWhenAddingShortcut()
  {
    return !isRequestPinShortcutSupported();
  }
  
  private static void showAddedToHomescreenToast(String paramString)
  {
    showToast(ContextUtils.sApplicationContext.getString(2131886364, new Object[] { paramString }));
  }
  
  public static void showToast(String paramString)
  {
    Toast.makeText(ContextUtils.sApplicationContext, paramString, 0).show();
  }
  
  @CalledByNative
  private static void showWebApkInstallInProgressToast()
  {
    showToast(ContextUtils.sApplicationContext.getString(2131887504));
  }
  
  @CalledByNative
  private static void storeWebappSplashImage(final String paramString, Bitmap paramBitmap)
  {
    paramString = WebappRegistry.getInstance().getWebappDataStorage(paramString);
    if (paramString != null) {
      new AsyncTask() {}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
  }
  
  public static final class Delegate
  {
    public Delegate() {}
    
    public static void addShortcutToHomescreen(String paramString, Bitmap paramBitmap, Intent paramIntent)
    {
      if (ShortcutHelper.access$000())
      {
        ShortcutHelper.access$100(paramString, paramBitmap, paramIntent);
        return;
      }
      paramString = ShortcutHelper.createAddToHomeIntent(paramString, paramBitmap, paramIntent);
      ContextUtils.sApplicationContext.sendBroadcast(paramString);
    }
  }
}
