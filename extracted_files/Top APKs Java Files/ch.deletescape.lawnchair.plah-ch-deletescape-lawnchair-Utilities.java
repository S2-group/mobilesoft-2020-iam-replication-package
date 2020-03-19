package ch.deletescape.lawnchair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TtsSpan.TextBuilder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;
import ch.deletescape.lawnchair.backup.RestoreBackupActivity;
import ch.deletescape.lawnchair.config.IThemer;
import ch.deletescape.lawnchair.config.ThemeProvider;
import ch.deletescape.lawnchair.dynamicui.ExtractedColors;
import ch.deletescape.lawnchair.graphics.ShadowGenerator;
import ch.deletescape.lawnchair.overlay.ILauncherClient;
import ch.deletescape.lawnchair.overlay.ILauncherClient.Companion;
import ch.deletescape.lawnchair.pixelify.AdaptiveIconDrawableCompat;
import ch.deletescape.lawnchair.preferences.IPreferenceProvider;
import ch.deletescape.lawnchair.preferences.PreferenceProvider;
import ch.deletescape.lawnchair.shortcuts.DeepShortcutManager;
import ch.deletescape.lawnchair.util.IconNormalizer;
import ch.deletescape.lawnchair.util.LooperExecutor;
import ch.deletescape.lawnchair.util.PackageManagerHelper;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utilities
{
  public static final boolean ATLEAST_LOLLIPOP_MR1;
  public static final boolean ATLEAST_MARSHMALLOW;
  public static final boolean ATLEAST_NOUGAT;
  public static final boolean ATLEAST_NOUGAT_MR1;
  public static final boolean ATLEAST_OREO;
  public static final boolean ATLEAST_OREO_M1;
  private static final String[] BLACKLISTED_APPLICATIONS = { "com.android.vending.billing.InAppBillingService.", "uret.jasi2169.", "com.dimonvideo.luckypatcher", "com.chelpus.", "com.forpda.lp", "zone.jasi2169." };
  private static final int CORE_POOL_SIZE;
  private static final int CPU_COUNT;
  private static final int MAXIMUM_POOL_SIZE;
  public static final Executor THREAD_POOL_EXECUTOR;
  private static final Canvas sCanvas;
  private static final int[] sLoc0;
  private static final int[] sLoc1;
  private static final Rect sOldBounds = new Rect();
  private static final Pattern sTrimPattern;
  
  static
  {
    sCanvas = new Canvas();
    sTrimPattern = Pattern.compile("^[\\s|\\p{javaSpaceChar}]*(.*)[\\s|\\p{javaSpaceChar}]*$");
    sCanvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
    sLoc0 = new int[2];
    sLoc1 = new int[2];
    int i = Build.VERSION.SDK_INT;
    boolean bool2 = false;
    if (i >= 23) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ATLEAST_MARSHMALLOW = bool1;
    if (Build.VERSION.SDK_INT >= 22) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ATLEAST_LOLLIPOP_MR1 = bool1;
    if (Build.VERSION.SDK_INT >= 24) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ATLEAST_NOUGAT = bool1;
    if (Build.VERSION.SDK_INT >= 25) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ATLEAST_NOUGAT_MR1 = bool1;
    if (Build.VERSION.SDK_INT >= 26) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ATLEAST_OREO = bool1;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 27) {
      bool1 = true;
    }
    ATLEAST_OREO_M1 = bool1;
    CPU_COUNT = Runtime.getRuntime().availableProcessors();
    CORE_POOL_SIZE = CPU_COUNT + 1;
    MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  }
  
  public static Bitmap addShadowToIcon(Bitmap paramBitmap)
  {
    return ShadowGenerator.getInstance().recreateIcon(paramBitmap);
  }
  
  public static Bitmap addShadowToIcon(Bitmap paramBitmap, int paramInt)
  {
    return ShadowGenerator.getInstance().recreateIcon(paramBitmap, paramInt);
  }
  
  public static Bitmap badgeIconForUser(Bitmap paramBitmap, UserHandle paramUserHandle, Context paramContext)
  {
    Bitmap localBitmap = paramBitmap;
    if (paramUserHandle != null)
    {
      localBitmap = paramBitmap;
      if (!myUserHandle().equals(paramUserHandle))
      {
        paramBitmap = new FixedSizeBitmapDrawable(paramBitmap);
        paramBitmap = paramContext.getPackageManager().getUserBadgedIcon(paramBitmap, paramUserHandle);
        if ((paramBitmap instanceof BitmapDrawable)) {
          return ((BitmapDrawable)paramBitmap).getBitmap();
        }
        localBitmap = createIconBitmap(paramBitmap, paramContext);
      }
    }
    return localBitmap;
  }
  
  public static Bitmap badgeWithBitmap(Bitmap paramBitmap1, Bitmap paramBitmap2, Context arg2)
  {
    int i = ???.getResources().getDimensionPixelSize(2131165468);
    synchronized (sCanvas)
    {
      sCanvas.setBitmap(paramBitmap1);
      sCanvas.drawBitmap(paramBitmap2, new Rect(0, 0, paramBitmap2.getWidth(), paramBitmap2.getHeight()), new Rect(paramBitmap1.getWidth() - i, paramBitmap1.getHeight() - i, paramBitmap1.getWidth(), paramBitmap1.getHeight()), new Paint(2));
      sCanvas.setBitmap(null);
      return paramBitmap1;
    }
  }
  
  public static float boundToRange(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.max(paramFloat2, Math.min(paramFloat1, paramFloat3));
  }
  
  public static int boundToRange(int paramInt1, int paramInt2, int paramInt3)
  {
    return Math.max(paramInt2, Math.min(paramInt1, paramInt3));
  }
  
  public static int calculateTextHeight(float paramFloat, boolean paramBoolean)
  {
    Object localObject = new Paint();
    ((Paint)localObject).setTextSize(paramFloat);
    localObject = ((Paint)localObject).getFontMetrics();
    int j = (int)Math.ceil(((Paint.FontMetrics)localObject).bottom - ((Paint.FontMetrics)localObject).top);
    int i = j;
    if (paramBoolean) {
      i = j * 2;
    }
    return i;
  }
  
  public static boolean checkOutdatedLawnfeed(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("ch.deletescape.lawnchair.lawnfeed", 0);
      if ((paramContext != null) && (paramContext.versionCode <= 1655))
      {
        boolean bool = paramContext.versionName.equals("dev");
        if (!bool) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static void checkRestoreSuccess(Context paramContext)
  {
    IPreferenceProvider localIPreferenceProvider = getPrefs(paramContext);
    if (localIPreferenceProvider.getRestoreSuccess())
    {
      localIPreferenceProvider.setRestoreSuccess(true);
      paramContext.startActivity(new Intent(paramContext, RestoreBackupActivity.class).putExtra("success", true));
    }
  }
  
  public static void closeSilently(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static Bitmap createBadgedIconBitmap(Drawable paramDrawable, UserHandle paramUserHandle, Context paramContext)
  {
    Bitmap localBitmap2 = createIconBitmap(paramDrawable, paramContext, IconNormalizer.getInstance().getScale(paramDrawable, null));
    Bitmap localBitmap1 = localBitmap2;
    if (isAdaptive(paramDrawable)) {
      localBitmap1 = addShadowToIcon(localBitmap2, localBitmap2.getWidth());
    }
    return badgeIconForUser(localBitmap1, paramUserHandle, paramContext);
  }
  
  public static String createDbSelectionQuery(String paramString, Iterable<?> paramIterable)
  {
    return String.format(Locale.ENGLISH, "%s IN (%s)", new Object[] { paramString, TextUtils.join(", ", paramIterable) });
  }
  
  public static Bitmap createIconBitmap(Cursor paramCursor, int paramInt, Context paramContext)
  {
    paramCursor = paramCursor.getBlob(paramInt);
    try
    {
      paramCursor = createIconBitmap(BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length), paramContext);
      return paramCursor;
    }
    catch (Exception paramCursor)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Bitmap createIconBitmap(Bitmap paramBitmap, Context paramContext)
  {
    int i = getIconBitmapSize();
    if ((i == paramBitmap.getWidth()) && (i == paramBitmap.getHeight())) {
      return paramBitmap;
    }
    return createIconBitmap(new BitmapDrawable(paramContext.getResources(), paramBitmap), paramContext);
  }
  
  public static Bitmap createIconBitmap(Drawable paramDrawable, Context paramContext)
  {
    return createIconBitmap(paramDrawable, paramContext, 1.0F);
  }
  
  private static Bitmap createIconBitmap(Drawable paramDrawable, Context paramContext, float paramFloat)
  {
    for (;;)
    {
      int i;
      int j;
      int k;
      float f;
      synchronized (sCanvas)
      {
        i = getIconBitmapSize();
        Object localObject;
        if ((paramDrawable instanceof PaintDrawable))
        {
          paramContext = (PaintDrawable)paramDrawable;
          paramContext.setIntrinsicWidth(i);
          paramContext.setIntrinsicHeight(i);
        }
        else if ((paramDrawable instanceof BitmapDrawable))
        {
          localObject = (BitmapDrawable)paramDrawable;
          Bitmap localBitmap = ((BitmapDrawable)localObject).getBitmap();
          if ((localBitmap != null) && (localBitmap.getDensity() == 0)) {
            ((BitmapDrawable)localObject).setTargetDensity(paramContext.getResources().getDisplayMetrics());
          }
        }
        j = paramDrawable.getIntrinsicWidth();
        k = paramDrawable.getIntrinsicHeight();
        if ((j <= 0) || (k <= 0)) {
          break label337;
        }
        f = j / k;
        if (j > k)
        {
          j = (int)(i / f);
          k = i;
          paramContext = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
          localObject = sCanvas;
          ((Canvas)localObject).setBitmap(paramContext);
          int m = (i - k) / 2;
          int n = (i - j) / 2;
          sOldBounds.set(paramDrawable.getBounds());
          if (isAdaptive(paramDrawable))
          {
            m = Math.max((int)(0.010416667F * i), Math.min(m, n));
            j = Math.max(k, j);
            paramDrawable.setBounds(m, m, j, j);
          }
          else
          {
            paramDrawable.setBounds(m, n, k + m, j + n);
          }
          ((Canvas)localObject).save(1);
          ((Canvas)localObject).scale(paramFloat, paramFloat, i / 2, i / 2);
          paramDrawable.draw((Canvas)localObject);
          ((Canvas)localObject).restore();
          paramDrawable.setBounds(sOldBounds);
          ((Canvas)localObject).setBitmap(null);
          return paramContext;
        }
      }
      if (k > j)
      {
        k = (int)(i * f);
        j = i;
      }
      else
      {
        label337:
        k = i;
        j = k;
      }
    }
  }
  
  public static Bitmap createIconBitmap(String paramString1, String paramString2, Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramString1 = localPackageManager.getResourcesForApplication(paramString1);
      if (paramString1 != null)
      {
        paramString1 = createIconBitmap(paramString1.getDrawableForDensity(paramString1.getIdentifier(paramString2, null, null), LauncherAppState.getInstance().getInvariantDeviceProfile().fillResIconDpi, null), paramContext);
        return paramString1;
      }
      return null;
    }
    catch (Exception paramString1) {}
    return null;
  }
  
  public static Bitmap createScaledBitmapWithoutShadow(Drawable paramDrawable, Context paramContext)
  {
    RectF localRectF = new RectF();
    return createIconBitmap(paramDrawable, paramContext, Math.min(IconNormalizer.getInstance().getScale(paramDrawable, localRectF), ShadowGenerator.getScaleForBounds(localRectF)));
  }
  
  public static float dpiFromPx(int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    float f = paramDisplayMetrics.densityDpi / 160.0F;
    return paramInt / f;
  }
  
  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    if ((paramDrawable.getIntrinsicWidth() > 0) && (paramDrawable.getIntrinsicHeight() > 0))
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
      paramDrawable.draw(localCanvas);
      return localBitmap;
    }
    return null;
  }
  
  private static void ensureAdaptiveIcon(Drawable paramDrawable)
  {
    if (!isAdaptive(paramDrawable)) {
      throw new IllegalStateException("Not an adaptive icon");
    }
  }
  
  public static int findDominantColorByHue(Bitmap paramBitmap, int paramInt)
  {
    int i1 = paramBitmap.getHeight();
    int i2 = paramBitmap.getWidth();
    paramInt = (int)Math.sqrt(i1 * i2 / paramInt);
    int j = paramInt;
    if (paramInt < 1) {
      j = 1;
    }
    float[] arrayOfFloat = new float[3];
    Object localObject = new float['Ũ'];
    paramInt = -1;
    int i = 0;
    float f1 = -1.0F;
    int m;
    int n;
    float f3;
    while (i < i1)
    {
      k = 0;
      while (k < i2)
      {
        m = paramBitmap.getPixel(k, i);
        if ((0xFF & m >> 24) < 128)
        {
          f2 = f1;
          m = paramInt;
        }
        else
        {
          Color.colorToHSV(m | 0xFF000000, arrayOfFloat);
          n = (int)arrayOfFloat[0];
          f2 = f1;
          m = paramInt;
          if (n >= 0) {
            if (n >= localObject.length)
            {
              f2 = f1;
              m = paramInt;
            }
            else
            {
              f2 = arrayOfFloat[1];
              f3 = arrayOfFloat[2];
              localObject[n] += f2 * f3;
              f2 = f1;
              m = paramInt;
              if (localObject[n] > f1)
              {
                f2 = localObject[n];
                m = n;
              }
            }
          }
        }
        k += j;
        f1 = f2;
        paramInt = m;
      }
      i += j;
    }
    localObject = new SparseArray();
    int k = 0;
    i = -16777216;
    float f2 = -1.0F;
    while (k < i1)
    {
      m = 0;
      while (m < i2)
      {
        n = paramBitmap.getPixel(m, k) | 0xFF000000;
        Color.colorToHSV(n, arrayOfFloat);
        if ((int)arrayOfFloat[0] == paramInt)
        {
          f1 = arrayOfFloat[1];
          f3 = arrayOfFloat[2];
          int i3 = (int)(f1 * 100.0F) + (int)(f3 * 10000.0F);
          f1 *= f3;
          Float localFloat = (Float)((SparseArray)localObject).get(i3);
          if (localFloat != null) {
            f1 = localFloat.floatValue() + f1;
          }
          ((SparseArray)localObject).put(i3, Float.valueOf(f1));
          f3 = f2;
          if (f1 > f2)
          {
            i = n;
            f3 = f1;
          }
        }
        else
        {
          f3 = f2;
        }
        m += j;
        f2 = f3;
      }
      k += j;
    }
    return i;
  }
  
  public static Pair<String, Resources> findSystemApk(String paramString, PackageManager paramPackageManager)
  {
    paramString = paramPackageManager.queryBroadcastReceivers(new Intent(paramString), 0).iterator();
    while (paramString.hasNext())
    {
      Object localObject1 = (ResolveInfo)paramString.next();
      if ((((ResolveInfo)localObject1).activityInfo != null) && ((((ResolveInfo)localObject1).activityInfo.applicationInfo.flags & 0x1) != 0)) {
        localObject1 = ((ResolveInfo)localObject1).activityInfo.packageName;
      }
      try
      {
        localObject2 = Pair.create(localObject1, paramPackageManager.getResourcesForApplication((String)localObject1));
        return localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Object localObject2;
        for (;;) {}
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Failed to find resources for ");
      ((StringBuilder)localObject2).append((String)localObject1);
      Log.w("Launcher.Utilities", ((StringBuilder)localObject2).toString());
    }
    return null;
  }
  
  public static byte[] flattenBitmap(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramBitmap.getWidth() * paramBitmap.getHeight() * 4);
    try
    {
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
      localByteArrayOutputStream.flush();
      localByteArrayOutputStream.close();
      paramBitmap = localByteArrayOutputStream.toByteArray();
      return paramBitmap;
    }
    catch (IOException paramBitmap)
    {
      for (;;) {}
    }
    Log.w("Launcher.Utilities", "Could not write bitmap");
    return null;
  }
  
  public static Paint.FontMetricsInt fontMetricsIntFromFontMetrics(Paint.FontMetrics paramFontMetrics)
  {
    Paint.FontMetricsInt localFontMetricsInt = new Paint.FontMetricsInt();
    localFontMetricsInt.ascent = Math.round(paramFontMetrics.ascent);
    localFontMetricsInt.bottom = Math.round(paramFontMetrics.bottom);
    localFontMetricsInt.descent = Math.round(paramFontMetrics.descent);
    localFontMetricsInt.leading = Math.round(paramFontMetrics.leading);
    localFontMetricsInt.top = Math.round(paramFontMetrics.top);
    return localFontMetricsInt;
  }
  
  public static List<String> getAlternativeIconList(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getAll().keySet().iterator();
    while (paramContext.hasNext())
    {
      String str = (String)paramContext.next();
      if (str.startsWith("alternateIcon_")) {
        localArrayList.add(str.replaceFirst("^alternateIcon_", ""));
      }
    }
    return localArrayList;
  }
  
  public static Drawable getBackground(Drawable paramDrawable)
  {
    ensureAdaptiveIcon(paramDrawable);
    if ((ATLEAST_OREO) && ((paramDrawable instanceof AdaptiveIconDrawable))) {
      return ((AdaptiveIconDrawable)paramDrawable).getBackground();
    }
    if ((paramDrawable instanceof AdaptiveIconDrawableCompat)) {
      return ((AdaptiveIconDrawableCompat)paramDrawable).getBackground();
    }
    return null;
  }
  
  public static int[] getCenterDeltaInScreenSpace(View paramView1, View paramView2, int[] paramArrayOfInt)
  {
    paramView1.getLocationInWindow(sLoc0);
    paramView2.getLocationInWindow(sLoc1);
    int[] arrayOfInt = sLoc0;
    arrayOfInt[0] = ((int)(arrayOfInt[0] + paramView1.getMeasuredWidth() * paramView1.getScaleX() / 2.0F));
    arrayOfInt = sLoc0;
    arrayOfInt[1] = ((int)(arrayOfInt[1] + paramView1.getMeasuredHeight() * paramView1.getScaleY() / 2.0F));
    paramView1 = sLoc1;
    paramView1[0] = ((int)(paramView1[0] + paramView2.getMeasuredWidth() * paramView2.getScaleX() / 2.0F));
    paramView1 = sLoc1;
    paramView1[1] = ((int)(paramView1[1] + paramView2.getMeasuredHeight() * paramView2.getScaleY() / 2.0F));
    paramView1 = paramArrayOfInt;
    if (paramArrayOfInt == null) {
      paramView1 = new int[2];
    }
    paramView1[0] = (sLoc1[0] - sLoc0[0]);
    paramView1[1] = (sLoc1[1] - sLoc0[1]);
    return paramView1;
  }
  
  public static int getColor(Context paramContext, int paramInt1, int paramInt2)
  {
    ExtractedColors localExtractedColors = new ExtractedColors();
    localExtractedColors.load(paramContext);
    return localExtractedColors.getColor(paramInt1, paramInt2);
  }
  
  public static int getColorAccent(Context paramContext)
  {
    paramContext = paramContext.obtainStyledAttributes(new int[] { 16843829 });
    int i = paramContext.getColor(0, 0);
    paramContext.recycle();
    return i;
  }
  
  public static float getDescendantCoordRelativeToAncestor(View paramView1, View paramView2, int[] paramArrayOfInt, boolean paramBoolean)
  {
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = paramArrayOfInt[0];
    arrayOfFloat[1] = paramArrayOfInt[1];
    float f = 1.0F;
    for (View localView = paramView1; (localView != paramView2) && (localView != null); localView = (View)localView.getParent())
    {
      if ((localView != paramView1) || (paramBoolean))
      {
        arrayOfFloat[0] -= localView.getScrollX();
        arrayOfFloat[1] -= localView.getScrollY();
      }
      localView.getMatrix().mapPoints(arrayOfFloat);
      arrayOfFloat[0] += localView.getLeft();
      arrayOfFloat[1] += localView.getTop();
      f *= localView.getScaleX();
    }
    paramArrayOfInt[0] = Math.round(arrayOfFloat[0]);
    paramArrayOfInt[1] = Math.round(arrayOfFloat[1]);
    return f;
  }
  
  public static int getDynamicAccent(Context paramContext)
  {
    if (!getPrefs(paramContext).getEnableDynamicUi()) {
      return getColorAccent(paramContext);
    }
    return getColor(paramContext, 4, getColorAccent(paramContext));
  }
  
  public static int getDynamicBadgeColor(Context paramContext)
  {
    int i = paramContext.getResources().getColor(2131099708);
    if (!getPrefs(paramContext).getEnableDynamicUi()) {
      return i;
    }
    return getColor(paramContext, 4, i);
  }
  
  public static Drawable getForeground(Drawable paramDrawable)
  {
    ensureAdaptiveIcon(paramDrawable);
    if ((ATLEAST_OREO) && ((paramDrawable instanceof AdaptiveIconDrawable))) {
      return ((AdaptiveIconDrawable)paramDrawable).getForeground();
    }
    if ((paramDrawable instanceof AdaptiveIconDrawableCompat)) {
      return ((AdaptiveIconDrawableCompat)paramDrawable).getForeground();
    }
    return null;
  }
  
  private static int getIconBitmapSize()
  {
    return LauncherAppState.getInstance().getInvariantDeviceProfile().iconBitmapSize;
  }
  
  public static Drawable getMyIcon(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationIcon(paramContext.getApplicationInfo());
  }
  
  public static int getNumberOfHotseatRows(Context paramContext)
  {
    if (PreferenceProvider.INSTANCE.getPreferences(paramContext).getTwoRowDock()) {
      return 2;
    }
    return 1;
  }
  
  public static IPreferenceProvider getPrefs(Context paramContext)
  {
    return PreferenceProvider.INSTANCE.getPreferences(paramContext);
  }
  
  /* Error */
  private static String getProp(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: new 864	java/lang/ProcessBuilder
    //   11: dup
    //   12: iconst_2
    //   13: anewarray 129	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: ldc_w 866
    //   21: aastore
    //   22: dup
    //   23: iconst_1
    //   24: aload_0
    //   25: aastore
    //   26: invokespecial 869	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   29: iconst_1
    //   30: invokevirtual 873	java/lang/ProcessBuilder:redirectErrorStream	(Z)Ljava/lang/ProcessBuilder;
    //   33: invokevirtual 877	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   36: astore_3
    //   37: aload_1
    //   38: astore_0
    //   39: new 879	java/io/BufferedReader
    //   42: dup
    //   43: new 881	java/io/InputStreamReader
    //   46: dup
    //   47: aload_3
    //   48: invokevirtual 887	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   51: invokespecial 890	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: invokespecial 893	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   57: astore 5
    //   59: aload 4
    //   61: astore_2
    //   62: aload 5
    //   64: invokevirtual 896	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   67: astore_0
    //   68: aload_0
    //   69: ifnull +8 -> 77
    //   72: aload_0
    //   73: astore_1
    //   74: goto -15 -> 59
    //   77: aload_1
    //   78: astore_0
    //   79: aload 5
    //   81: ifnull +78 -> 159
    //   84: aload_1
    //   85: astore_0
    //   86: aload 5
    //   88: invokevirtual 897	java/io/BufferedReader:close	()V
    //   91: aload_1
    //   92: astore_0
    //   93: goto +66 -> 159
    //   96: astore 4
    //   98: goto +8 -> 106
    //   101: astore_0
    //   102: aload_0
    //   103: astore_2
    //   104: aload_0
    //   105: athrow
    //   106: aload 5
    //   108: ifnull +37 -> 145
    //   111: aload_2
    //   112: ifnull +26 -> 138
    //   115: aload_1
    //   116: astore_0
    //   117: aload 5
    //   119: invokevirtual 897	java/io/BufferedReader:close	()V
    //   122: goto +23 -> 145
    //   125: astore 5
    //   127: aload_1
    //   128: astore_0
    //   129: aload_2
    //   130: aload 5
    //   132: invokevirtual 901	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   135: goto +10 -> 145
    //   138: aload_1
    //   139: astore_0
    //   140: aload 5
    //   142: invokevirtual 897	java/io/BufferedReader:close	()V
    //   145: aload_1
    //   146: astore_0
    //   147: aload 4
    //   149: athrow
    //   150: astore_0
    //   151: goto +60 -> 211
    //   154: astore_1
    //   155: aload_1
    //   156: invokevirtual 904	java/io/IOException:printStackTrace	()V
    //   159: aload_0
    //   160: astore_2
    //   161: aload_3
    //   162: ifnull +47 -> 209
    //   165: aload_3
    //   166: invokevirtual 907	java/lang/Process:destroy	()V
    //   169: aload_0
    //   170: areturn
    //   171: astore_2
    //   172: aload_3
    //   173: astore_1
    //   174: aload_2
    //   175: astore_3
    //   176: goto +15 -> 191
    //   179: astore_0
    //   180: aload_2
    //   181: astore_3
    //   182: goto +29 -> 211
    //   185: astore_3
    //   186: aload_1
    //   187: astore_0
    //   188: aload 5
    //   190: astore_1
    //   191: aload_1
    //   192: astore_2
    //   193: aload_3
    //   194: invokevirtual 904	java/io/IOException:printStackTrace	()V
    //   197: aload_0
    //   198: astore_2
    //   199: aload_1
    //   200: ifnull +9 -> 209
    //   203: aload_1
    //   204: invokevirtual 907	java/lang/Process:destroy	()V
    //   207: aload_0
    //   208: astore_2
    //   209: aload_2
    //   210: areturn
    //   211: aload_3
    //   212: ifnull +7 -> 219
    //   215: aload_3
    //   216: invokevirtual 907	java/lang/Process:destroy	()V
    //   219: aload_0
    //   220: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	paramString1	String
    //   0	221	1	paramString2	String
    //   1	160	2	localObject1	Object
    //   171	10	2	localIOException1	IOException
    //   192	18	2	str	String
    //   36	146	3	localObject2	Object
    //   185	31	3	localIOException2	IOException
    //   6	54	4	localObject3	Object
    //   96	52	4	localObject4	Object
    //   3	115	5	localBufferedReader	java.io.BufferedReader
    //   125	64	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   62	68	96	finally
    //   104	106	96	finally
    //   62	68	101	java/lang/Throwable
    //   117	122	125	java/lang/Throwable
    //   39	59	150	finally
    //   86	91	150	finally
    //   117	122	150	finally
    //   129	135	150	finally
    //   140	145	150	finally
    //   147	150	150	finally
    //   155	159	150	finally
    //   39	59	154	java/io/IOException
    //   86	91	154	java/io/IOException
    //   117	122	154	java/io/IOException
    //   129	135	154	java/io/IOException
    //   140	145	154	java/io/IOException
    //   147	150	154	java/io/IOException
    //   155	159	171	java/io/IOException
    //   8	37	179	finally
    //   193	197	179	finally
    //   8	37	185	java/io/IOException
  }
  
  public static Bitmap getShadowForIcon(Bitmap paramBitmap, int paramInt)
  {
    return ShadowGenerator.getInstance().createShadow(paramBitmap, paramInt);
  }
  
  public static IThemer getThemer()
  {
    return ThemeProvider.INSTANCE.getThemer();
  }
  
  public static boolean hasAlternativeIcon(Context paramContext, ComponentName paramComponentName)
  {
    return getAlternativeIconList(paramContext).contains(paramComponentName.flattenToString());
  }
  
  public static boolean hasStoragePermission(Context paramContext)
  {
    return android.support.v4.content.a.b(paramContext, "android.permission.READ_EXTERNAL_STORAGE") == 0;
  }
  
  public static boolean isAdaptive(Drawable paramDrawable)
  {
    return (paramDrawable != null) && (((ATLEAST_OREO) && ((paramDrawable instanceof AdaptiveIconDrawable))) || ((paramDrawable instanceof AdaptiveIconDrawableCompat)));
  }
  
  public static boolean isAnimatedClock(Context paramContext, ComponentName paramComponentName)
  {
    return (getPrefs(paramContext).getAnimatedClockIcon()) && (isComponentClock(paramComponentName, getPrefs(paramContext).getAnimatedClockIconAlternativeClockApps() ^ true));
  }
  
  public static boolean isAppHidden(Context paramContext, String paramString)
  {
    return getPrefs(paramContext).getHiddenAppsSet().contains(paramString);
  }
  
  public static boolean isAwarenessApiEnabled(Context paramContext)
  {
    return "1".equals(getPrefs(paramContext).getWeatherProvider());
  }
  
  public static boolean isBlacklistedAppInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    boolean bool1;
    for (;;)
    {
      boolean bool2 = paramContext.hasNext();
      bool1 = false;
      int i = 0;
      if (!bool2) {
        break;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      String[] arrayOfString = BLACKLISTED_APPLICATIONS;
      int j = arrayOfString.length;
      while (i < j)
      {
        String str = arrayOfString[i];
        if (localApplicationInfo.packageName.startsWith(str)) {
          return true;
        }
        i += 1;
      }
    }
    if (BLACKLISTED_APPLICATIONS.length == 0) {
      bool1 = true;
    }
    return bool1;
  }
  
  public static boolean isBootCompleted()
  {
    return "1".equals(getProp("sys.boot_completed", "1"));
  }
  
  private static boolean isComponentClock(ComponentName paramComponentName, boolean paramBoolean)
  {
    if (paramComponentName == null) {
      return false;
    }
    if (paramBoolean) {
      return "com.google.android.deskclock/com.android.deskclock.DeskClock".equals(paramComponentName.flattenToString());
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("com.android.deskclock/com.android.deskclock.DeskClock");
    localArrayList.add("com.sec.android.app.clockpackage/com.sec.android.app.clockpackage.ClockPackage");
    localArrayList.add("com.android.deskclock/com.android.deskclock.DeskClockTabActivity");
    return localArrayList.contains(paramComponentName.flattenToString());
  }
  
  public static boolean isLauncherAppTarget(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if ("android.intent.action.MAIN".equals(paramIntent.getAction()))
      {
        bool1 = bool2;
        if (paramIntent.getComponent() != null)
        {
          bool1 = bool2;
          if (paramIntent.getCategories() != null)
          {
            bool1 = bool2;
            if (paramIntent.getCategories().size() == 1)
            {
              bool1 = bool2;
              if (paramIntent.hasCategory("android.intent.category.LAUNCHER"))
              {
                bool1 = bool2;
                if (TextUtils.isEmpty(paramIntent.getDataString()))
                {
                  paramIntent = paramIntent.getExtras();
                  if (paramIntent == null) {
                    return true;
                  }
                  paramIntent = paramIntent.keySet();
                  bool1 = bool2;
                  if (paramIntent.size() == 1)
                  {
                    bool1 = bool2;
                    if (paramIntent.contains("profile")) {
                      bool1 = true;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public static boolean isPowerSaverOn(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    return (paramContext != null) && (paramContext.isPowerSaveMode());
  }
  
  public static boolean isPropertyEnabled(String paramString)
  {
    return Log.isLoggable(paramString, 2);
  }
  
  public static boolean isRtl(Resources paramResources)
  {
    return paramResources.getConfiguration().getLayoutDirection() == 1;
  }
  
  public static boolean isSystemApp(Context paramContext, Intent paramIntent)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramIntent.getComponent();
    if (paramContext == null)
    {
      paramContext = localPackageManager.resolveActivity(paramIntent, 65536);
      if ((paramContext != null) && (paramContext.activityInfo != null)) {
        paramContext = paramContext.activityInfo.packageName;
      } else {
        paramContext = null;
      }
    }
    else
    {
      paramContext = paramContext.getPackageName();
    }
    if (paramContext != null) {}
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext, 0);
      if ((paramContext != null) && (paramContext.applicationInfo != null))
      {
        int i = paramContext.applicationInfo.flags;
        if ((i & 0x1) != 0) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
    return false;
  }
  
  public static boolean isWallapaperAllowed(Context paramContext)
  {
    if (ATLEAST_NOUGAT) {}
    try
    {
      paramContext = (WallpaperManager)paramContext.getSystemService(WallpaperManager.class);
      if (paramContext != null) {
        paramContext = paramContext.getClass().getDeclaredMethod("isSetWallpaperAllowed", new Class[0]).invoke(paramContext, new Object[0]);
      } else {
        paramContext = Boolean.valueOf(false);
      }
      boolean bool = ((Boolean)paramContext).booleanValue();
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return true;
  }
  
  public static void killLauncher()
  {
    System.exit(0);
  }
  
  public static float mapCoordInSelfToDescendent(View paramView1, View paramView2, int[] paramArrayOfInt)
  {
    ArrayList localArrayList = new ArrayList();
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = paramArrayOfInt[0];
    arrayOfFloat[1] = paramArrayOfInt[1];
    while (paramView1 != paramView2)
    {
      localArrayList.add(paramView1);
      paramView1 = (View)paramView1.getParent();
    }
    localArrayList.add(paramView2);
    float f1 = 1.0F;
    paramView2 = new Matrix();
    int i = localArrayList.size() - 1;
    while (i >= 0)
    {
      View localView = (View)localArrayList.get(i);
      if (i > 0) {
        paramView1 = (View)localArrayList.get(i - 1);
      } else {
        paramView1 = null;
      }
      arrayOfFloat[0] += localView.getScrollX();
      arrayOfFloat[1] += localView.getScrollY();
      float f2 = f1;
      if (paramView1 != null)
      {
        arrayOfFloat[0] -= paramView1.getLeft();
        arrayOfFloat[1] -= paramView1.getTop();
        paramView1.getMatrix().invert(paramView2);
        paramView2.mapPoints(arrayOfFloat);
        f2 = f1 * paramView1.getScaleX();
      }
      i -= 1;
      f1 = f2;
    }
    paramArrayOfInt[0] = Math.round(arrayOfFloat[0]);
    paramArrayOfInt[1] = Math.round(arrayOfFloat[1]);
    return f1;
  }
  
  public static UserHandle myUserHandle()
  {
    return Process.myUserHandle();
  }
  
  public static void openURLinBrowser(Context paramContext, String paramString)
  {
    openURLinBrowser(paramContext, paramString, null, null);
  }
  
  public static void openURLinBrowser(Context paramContext, String paramString, Rect paramRect, Bundle paramBundle)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setSourceBounds(paramRect);
      if (paramBundle == null)
      {
        paramContext.startActivity(paramString);
        return;
      }
      paramContext.startActivity(paramString, paramBundle);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      for (;;) {}
    }
    Toast.makeText(paramContext, 2131755192, 0).show();
  }
  
  public static boolean pointInView(View paramView, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f = -paramFloat3;
    return (paramFloat1 >= f) && (paramFloat2 >= f) && (paramFloat1 < paramView.getWidth() + paramFloat3) && (paramFloat2 < paramView.getHeight() + paramFloat3);
  }
  
  public static int pxFromDp(float paramFloat, DisplayMetrics paramDisplayMetrics)
  {
    return Math.round(TypedValue.applyDimension(1, paramFloat, paramDisplayMetrics));
  }
  
  public static int pxFromSp(float paramFloat, DisplayMetrics paramDisplayMetrics)
  {
    return Math.round(TypedValue.applyDimension(2, paramFloat, paramDisplayMetrics));
  }
  
  public static void requestStoragePermission(Activity paramActivity)
  {
    android.support.v4.app.a.a(paramActivity, new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, 666);
  }
  
  public static int resolveAttributeData(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true);
    return localTypedValue.data;
  }
  
  public static void restartLauncher(Context paramContext)
  {
    new LooperExecutor(LauncherModel.getWorkerLooper()).execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(250L);
        }
        catch (Exception localException)
        {
          Log.e("SettingsActivity", "Error waiting", localException);
        }
        Object localObject = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").setPackage(this.val$context.getPackageName()).addFlags(268435456);
        localObject = PendingIntent.getActivity(this.val$context, 0, (Intent)localObject, 1342177280);
        ((AlarmManager)this.val$context.getSystemService("alarm")).setExact(3, SystemClock.elapsedRealtime() + 50L, (PendingIntent)localObject);
        Process.killProcess(Process.myPid());
      }
    });
  }
  
  public static void scaleRectAboutCenter(Rect paramRect, float paramFloat)
  {
    if (paramFloat != 1.0F)
    {
      int i = paramRect.centerX();
      int j = paramRect.centerY();
      paramRect.offset(-i, -j);
      paramRect.left = ((int)(paramRect.left * paramFloat + 0.5F));
      paramRect.top = ((int)(paramRect.top * paramFloat + 0.5F));
      paramRect.right = ((int)(paramRect.right * paramFloat + 0.5F));
      paramRect.bottom = ((int)(paramRect.bottom * paramFloat + 0.5F));
      paramRect.offset(i, j);
    }
  }
  
  public static void sendCustomAccessibilityEvent(View paramView, int paramInt, String paramString)
  {
    AccessibilityManager localAccessibilityManager = (AccessibilityManager)paramView.getContext().getSystemService("accessibility");
    if ((localAccessibilityManager != null) && (localAccessibilityManager.isEnabled()))
    {
      AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt);
      paramView.onInitializeAccessibilityEvent(localAccessibilityEvent);
      localAccessibilityEvent.getText().add(paramString);
      localAccessibilityManager.sendAccessibilityEvent(localAccessibilityEvent);
    }
  }
  
  public static void setAppVisibility(Context paramContext, String paramString, boolean paramBoolean)
  {
    Set localSet = getPrefs(paramContext).getHiddenAppsSet();
    if (paramBoolean) {
      localSet.remove(paramString);
    } else {
      localSet.add(paramString);
    }
    getPrefs(paramContext).setHiddenAppsSet(localSet);
  }
  
  public static void setDefaultLauncher(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = paramContext.getPackageName();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramContext.getPackageName());
    ((StringBuilder)localObject2).append(".FakeLauncher");
    localObject1 = new ComponentName((String)localObject1, ((StringBuilder)localObject2).toString());
    localObject2 = new ComponentName(paramContext, Launcher.class);
    localPackageManager.setComponentEnabledSetting((ComponentName)localObject1, 1, 1);
    localPackageManager.setComponentEnabledSetting((ComponentName)localObject2, 2, 1);
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
    localPackageManager.setComponentEnabledSetting((ComponentName)localObject1, 0, 1);
    localPackageManager.setComponentEnabledSetting((ComponentName)localObject2, 0, 1);
  }
  
  public static void setupPirateLocale(Activity paramActivity)
  {
    if (!PreferenceProvider.INSTANCE.getPreferences(paramActivity).getAyyMatey()) {
      return;
    }
    Locale localLocale = new Locale("pir");
    Locale.setDefault(localLocale);
    Configuration localConfiguration = new Configuration();
    localConfiguration.locale = localLocale;
    paramActivity = paramActivity.getBaseContext().getResources();
    paramActivity.updateConfiguration(localConfiguration, paramActivity.getDisplayMetrics());
  }
  
  public static void showChangelog(Context paramContext) {}
  
  public static void showLawnfeedPopup(Context paramContext)
  {
    if (ILauncherClient.Companion.getEnabledState(paramContext) != 0) {
      return;
    }
    IPreferenceProvider localIPreferenceProvider = getPrefs(paramContext);
    if (!localIPreferenceProvider.getDisableLawnfeedPopup())
    {
      if (localIPreferenceProvider.getShowGoogleNowTab()) {
        return;
      }
      new AlertDialog.Builder(paramContext).setTitle(2131755265).setMessage(2131755264).setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        @SuppressLint({"ApplySharedPref"})
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          PreferenceManager.getDefaultSharedPreferences(this.val$context).edit().putBoolean("pref_showGoogleNowTab", true).commit();
          LauncherAppState.getInstanceNoCreate().getLauncher().scheduleKill();
        }
      }).setNeutralButton(2131755182, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          this.val$prefs.setDisableLawnfeedPopup(true);
        }
      }).setNegativeButton(17039360, null).show();
    }
  }
  
  public static void showOutdatedLawnfeedPopup(Context paramContext)
  {
    if (ILauncherClient.Companion.getEnabledState(paramContext) != 3) {
      return;
    }
    if (getPrefs(paramContext).getShowGoogleNowTab()) {
      PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_showGoogleNowTab", false).commit();
    }
    new AlertDialog.Builder(paramContext).setTitle(2131755268).setMessage(2131755267).setPositiveButton(17039379, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Utilities.openURLinBrowser(this.val$context, "https://lawnchair.info/getlawnfeed.html");
      }
    }).setNegativeButton(17039369, null).show();
  }
  
  public static void showResetAlternativeIcons(Context paramContext, final List<String> paramList)
  {
    if (paramList.size() <= 0) {
      return;
    }
    new AlertDialog.Builder(paramContext).setTitle(2131755346).setMessage(String.format(paramContext.getString(2131755345), new Object[] { Integer.valueOf(paramList.size()) })).setPositiveButton(17039379, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = Utilities.getPrefs(this.val$context);
        Launcher localLauncher = LauncherAppState.getInstanceNoCreate().getLauncher();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          paramAnonymousDialogInterface.removeAlternateIcon((String)localIterator.next());
        }
        localLauncher.scheduleReloadIcons();
      }
    }).setNegativeButton(17039369, null).show();
  }
  
  public static <T> HashSet<T> singletonHashSet(T paramT)
  {
    HashSet localHashSet = new HashSet(1);
    localHashSet.add(paramT);
    return localHashSet;
  }
  
  public static void startActivityForResultSafely(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    try
    {
      paramActivity.startActivityForResult(paramIntent, paramInt);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      Toast.makeText(paramActivity, 2131755076, 0).show();
      paramActivity = new StringBuilder();
      paramActivity.append("Launcher does not have the permission to launch ");
      paramActivity.append(paramIntent);
      paramActivity.append(". Make sure to create a MAIN intent-filter for the corresponding activity or use the exported attribute for this activity.");
      Log.e("Launcher.Utilities", paramActivity.toString(), localSecurityException);
      return;
      Toast.makeText(paramActivity, 2131755076, 0).show();
      return;
    }
    catch (ActivityNotFoundException paramIntent)
    {
      for (;;) {}
    }
  }
  
  public static String trim(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return null;
    }
    return sTrimPattern.matcher(paramCharSequence).replaceAll("$1");
  }
  
  public static void updatePackage(Context paramContext, UserHandle paramUserHandle, String paramString)
  {
    if (!PackageManagerHelper.isAppEnabled(paramContext.getPackageManager(), paramString, 0)) {
      return;
    }
    LauncherAppState localLauncherAppState = LauncherAppState.getInstance();
    localLauncherAppState.getModel().onPackageChanged(paramString, paramUserHandle);
    paramContext = DeepShortcutManager.getInstance(paramContext).queryForPinnedShortcuts(paramString, paramUserHandle);
    if (!paramContext.isEmpty()) {
      localLauncherAppState.getModel().updatePinnedShortcuts(paramString, paramContext, paramUserHandle);
    }
  }
  
  public static CharSequence wrapForTts(CharSequence paramCharSequence, String paramString)
  {
    paramCharSequence = new SpannableString(paramCharSequence);
    paramCharSequence.setSpan(new TtsSpan.TextBuilder(paramString).build(), 0, paramCharSequence.length(), 18);
    return paramCharSequence;
  }
  
  private static class FixedSizeBitmapDrawable
    extends BitmapDrawable
  {
    FixedSizeBitmapDrawable(Bitmap paramBitmap)
    {
      super(paramBitmap);
    }
    
    public int getIntrinsicHeight()
    {
      return getBitmap().getWidth();
    }
    
    public int getIntrinsicWidth()
    {
      return getBitmap().getWidth();
    }
  }
}
