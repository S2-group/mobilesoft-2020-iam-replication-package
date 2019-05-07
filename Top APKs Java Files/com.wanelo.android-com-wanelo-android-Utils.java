package com.wanelo.android;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.wanelo.android.config.Constants;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class Utils
{
  public static final CharsetEncoder ASCII_ENCODER = Charset.forName("US-ASCII").newEncoder();
  
  public Utils() {}
  
  public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outHeight;
    int k = paramOptions.outWidth;
    int i = 1;
    if ((j > paramInt2) || (k > paramInt1))
    {
      if (k > j) {
        i = Math.round(j / paramInt2);
      }
    }
    else {
      return i;
    }
    return Math.round(k / paramInt1);
  }
  
  public static float calculateMemCachePercent(int paramInt)
  {
    if (paramInt <= 16) {
      return 0.125F;
    }
    if (paramInt >= 64) {
      return 0.3F;
    }
    return 0.125F + 0.17500001F * (paramInt - 16) / 48.0F;
  }
  
  public static Collator createCollator()
  {
    Collator localCollator = Collator.getInstance();
    localCollator.setDecomposition(0);
    return localCollator;
  }
  
  public static boolean doesContainGsfPackage(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      paramContext = paramContext.getInstalledPackages(0);
      bool1 = bool2;
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        do
        {
          bool1 = bool2;
          if (!paramContext.hasNext()) {
            break;
          }
        } while (!((PackageInfo)paramContext.next()).packageName.equals("com.google.android.gsf"));
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static void fixEditTextLeak(EditText paramEditText)
  {
    paramEditText.setInputType(524432);
  }
  
  public static Bitmap getBitmapFromUri(Context paramContext, Uri paramUri, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    if (paramUri != null)
    {
      localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
      String str = paramUri.getScheme();
      if ((!"content".equals(str)) && (!"file".equals(str))) {}
    }
    else
    {
      try
      {
        paramContext = paramContext.getContentResolver();
        BitmapFactory.decodeStream(paramContext.openInputStream(paramUri), null, (BitmapFactory.Options)localObject);
        ((BitmapFactory.Options)localObject).inSampleSize = calculateInSampleSize((BitmapFactory.Options)localObject, paramInt1, paramInt2);
        ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
        localObject = BitmapFactory.decodeStream(paramContext.openInputStream(paramUri), null, (BitmapFactory.Options)localObject);
        return localObject;
      }
      catch (Exception paramContext)
      {
        Log.w("resolveUri", "Unable to open content: " + paramUri, paramContext);
        return null;
      }
      catch (OutOfMemoryError paramContext)
      {
        Log.w("resolveUri", "OutOfMemoryError: " + paramUri, paramContext);
        return null;
      }
    }
    BitmapFactory.decodeFile(paramUri.toString(), (BitmapFactory.Options)localObject);
    ((BitmapFactory.Options)localObject).inSampleSize = calculateInSampleSize((BitmapFactory.Options)localObject, paramInt1, paramInt2);
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramUri.toString(), (BitmapFactory.Options)localObject);
  }
  
  public static Integer getCurrentAppVersion(Context paramContext)
  {
    int i = 0;
    try
    {
      int j = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      i = j;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return Integer.valueOf(i);
  }
  
  public static String getCurrentAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "unknown";
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return StringUtils.capitalize(str2);
    }
    return StringUtils.capitalize(str1) + " " + str2;
  }
  
  public static Map<String, String> getExternalUrlHttpHeaders(String paramString)
  {
    HashMap localHashMap = new HashMap();
    String str = "http://www.wanelo.com";
    if (StringUtils.isNotBlank(paramString)) {
      str = paramString;
    }
    localHashMap.put("Referer", str);
    return localHashMap;
  }
  
  public static int getFeedGridColumnCount(Context paramContext)
  {
    int i = 2;
    if (isLandscape(paramContext)) {
      i = 2 + 1;
    }
    int j = i;
    if (isTablet(paramContext)) {
      j = i + 1;
    }
    return j;
  }
  
  public static int getFollowableProductColumnCount(Context paramContext)
  {
    if ((isTablet(paramContext)) || (isLandscape(paramContext))) {
      return 6;
    }
    return 4;
  }
  
  public static int getMemoryClass(Context paramContext)
  {
    return ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass();
  }
  
  public static int getMinWidth(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    if (paramContext.widthPixels > paramContext.heightPixels) {
      return paramContext.heightPixels;
    }
    return paramContext.widthPixels;
  }
  
  public static int getProductColumnCount(Context paramContext)
  {
    int i = 3;
    if (isLandscape(paramContext)) {
      i = 3 + 1;
    }
    int j = i;
    if (isTablet(paramContext)) {
      j = i + 1;
    }
    return j;
  }
  
  public static String getStringWithPlural(Context paramContext, long paramLong, int paramInt1, int paramInt2)
  {
    if (paramLong == 1L) {
      return paramContext.getResources().getString(paramInt1, new Object[] { Long.valueOf(paramLong) });
    }
    if ((paramLong > 1L) || (paramLong < 1L)) {
      return paramContext.getResources().getString(paramInt2, new Object[] { Long.valueOf(paramLong) });
    }
    return "";
  }
  
  public static int getThemeTrendingProductColumnCount(Context paramContext)
  {
    if ((isLandscape(paramContext)) && (!isLargeScreen(paramContext))) {
      return 8;
    }
    return getFollowableProductColumnCount(paramContext);
  }
  
  public static boolean hasEditTextLeak()
  {
    return (Build.VERSION.SDK_INT >= 3) && (Build.VERSION.SDK_INT <= 17);
  }
  
  public static void hideKeyboard(View paramView)
  {
    ((InputMethodManager)paramView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static boolean isAsciiString(String paramString)
  {
    return ASCII_ENCODER.canEncode(paramString);
  }
  
  public static boolean isBigScreen(Context paramContext)
  {
    return getMinWidth(paramContext) > 720;
  }
  
  public static boolean isKindleFire()
  {
    return (isKindleFireFirstGen()) || ((Build.MANUFACTURER.equals("Amazon")) && (Build.MODEL.startsWith("KF")));
  }
  
  public static boolean isKindleFireFirstGen()
  {
    return (Build.MANUFACTURER.equals("Amazon")) && (Build.MODEL.equals("Kindle Fire"));
  }
  
  public static boolean isLandscape(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 2;
  }
  
  public static boolean isLargeScreen(Context paramContext)
  {
    int i = paramContext.getResources().getConfiguration().screenLayout & 0xF;
    return (i == 3) || (i == 4);
  }
  
  public static boolean isNewerGinderbread()
  {
    return Build.VERSION.SDK_INT >= 9;
  }
  
  public static boolean isOlderThanHoneycomb()
  {
    return Build.VERSION.SDK_INT < 11;
  }
  
  public static boolean isSearchAction(int paramInt, KeyEvent paramKeyEvent)
  {
    return (paramInt == 3) || (paramInt == 6) || (paramInt == 2) || ((paramInt == 0) && (paramKeyEvent != null) && (paramKeyEvent.getKeyCode() == 66) && (paramKeyEvent.getAction() == 0));
  }
  
  public static boolean isSubmitAction(int paramInt, KeyEvent paramKeyEvent)
  {
    return (paramInt == 6) || (paramInt == 2) || (paramInt == 7) || ((paramInt == 0) && (paramKeyEvent != null) && (paramKeyEvent.getKeyCode() == 66) && (paramKeyEvent.getAction() == 0));
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131427332);
  }
  
  public static String shortenAndFormatNumber(int paramInt)
  {
    return shortenAndFormatNumber(new Long(paramInt).longValue());
  }
  
  public static String shortenAndFormatNumber(long paramLong)
  {
    localDecimalFormat = Constants.NUMBER_FORMAT;
    int j = 0;
    int i = 0;
    float f1 = (float)paramLong;
    float f2 = f1;
    if (f1 > 9999.0F) {}
    for (;;)
    {
      j = i;
      f2 = f1;
      if (f1 > 1000.0F) {}
      Object localObject1;
      try
      {
        f1 /= 1000.0F;
        i += 1;
      }
      finally {}
    }
    if (f2 > 100.0F) {}
    for (localObject1 = Constants.NUMBER_FORMAT_WITHOUT_DECIMAL;; localObject1 = Constants.NUMBER_FORMAT)
    {
      localObject1 = ((DecimalFormat)localObject1).format(f2) + Constants.NUMBER_NAMES[j];
      return localObject1;
    }
  }
}
