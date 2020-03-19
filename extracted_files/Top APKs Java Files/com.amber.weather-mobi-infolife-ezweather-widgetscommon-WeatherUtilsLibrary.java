package mobi.infolife.ezweather.widgetscommon;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import mobi.infolife.ezweather.d.a.h;
import mobi.infolife.ezweather.sdk.c.c;

public class WeatherUtilsLibrary
{
  private static final String CLASS_PATH = WeatherUtilsLibrary.class.getPackage().getName();
  private static final String TAG = WeatherUtilsLibrary.class.getName();
  public static final int UNIT_IMPERIAL = 0;
  public static final int UNIT_METRIC = 1;
  public static final int UNIT_TYPE_ONE = 0;
  public static final int UNIT_TYPE_THREE = 2;
  public static final int UNIT_TYPE_TWO = 1;
  static final String WEATHER_PACKAGE_NAME = "mobi.infolife.ezweather";
  private static final String WIDGET_API_LEVEL_LABEL = "WIDGET_API_LEVEL";
  public static final String sBase64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6ujgQJwJXmQotc+s3nKXNzX9MvVsvxua5fXbYiDfIqKSGM/QEmauJdF0qNuDOsga0JtM49YfUVkKiXawKorFRqn8AmPranL9anJImP4GiWEaFzVYsixwUJrH5LMdxgA+wTfS8wlmEhLhjCzKvRnZPlgGEHQiLDzcSxARPHWg4dQgfegWieIElGuqmaQHFg6UFd3DM3m6T8mEvZN2mv8iMqVdCBP3VSPs1+RXHSLrsZP/3kwEFpInuGbzhsQMTwKCTsvOGwnWzg5XhS8OXQ0vw3a6HW9mLgwQSKf5tNRy7omAUDjkWU+hw5u11ZrIhiKwhYSoc3gJhAiqJilzbDmoIwIDAQAB";
  
  public WeatherUtilsLibrary() {}
  
  public static boolean checkWeatherNeedUpdate(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = h.a(paramContext, paramString1, "WIDGET_API_LEVEL");
    paramContext = h.a(paramContext, paramString2, "WIDGET_API_LEVEL");
    if ((paramString1 == null) || (paramContext == null)) {
      return false;
    }
    return ((Integer)paramString1).intValue() != ((Integer)paramContext).intValue();
  }
  
  public static int[] concatArrays(int[] paramArrayOfInt, int[]... paramVarArgs)
  {
    int j = paramArrayOfInt.length;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j += paramVarArgs[i].length;
      i += 1;
    }
    int[] arrayOfInt = Arrays.copyOf(paramArrayOfInt, j);
    j = paramArrayOfInt.length;
    k = paramVarArgs.length;
    i = 0;
    while (i < k)
    {
      paramArrayOfInt = paramVarArgs[i];
      System.arraycopy(paramArrayOfInt, 0, arrayOfInt, j, paramArrayOfInt.length);
      j += paramArrayOfInt.length;
      i += 1;
    }
    return arrayOfInt;
  }
  
  public static void downloadPlugin(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString1 != null)
    {
      localObject1 = localObject2;
      if (!paramString1.equals("")) {
        localObject1 = Uri.parse(paramString1);
      }
    }
    try
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString2));
      paramString1.setFlags(268435456);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (ActivityNotFoundException paramString1)
    {
      while (localObject1 == null) {}
      paramString1 = new Intent("android.intent.action.VIEW", (Uri)localObject1);
      paramString1.setFlags(268435456);
      paramContext.startActivity(paramString1);
    }
  }
  
  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  public static int[] getAllWidgetIds(Context paramContext)
  {
    return concatArrays(getWidgetIds(paramContext, "FourTwoWidget"), new int[][] { getWidgetIds(paramContext, "FourOneWidget"), getWidgetIds(paramContext, "OneOneWidget") });
  }
  
  public static int getBoolId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "bool", paramContext.getPackageName());
  }
  
  public static String getCalendarTypeByState(Context paramContext, Date paramDate)
  {
    try
    {
      i = mobi.infolife.ezweather.sdk.c.a.a(paramContext, false).c();
      switch (i)
      {
      default: 
        return null;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        int i = 0;
      }
      return String.format(paramContext.getString(2131232149), new Object[] { paramDate, paramDate });
    }
    return String.format(paramContext.getString(2131232150), new Object[] { paramDate, paramDate });
  }
  
  public static String getFormattedSunTime(long paramLong)
  {
    String str = "--";
    if (paramLong > 0L) {
      str = new SimpleDateFormat("hh:mmaa", Locale.ENGLISH).format(new Date(paramLong));
    }
    return str;
  }
  
  public static String getFormattedTimeName(long paramLong)
  {
    String str = "--";
    if (paramLong > 0L) {
      str = new SimpleDateFormat("hha", Locale.ENGLISH).format(new Date(paramLong));
    }
    return str;
  }
  
  public static List<PackageInfo> getInstalledAppList(Context paramContext, int paramInt)
  {
    return paramContext.getPackageManager().getInstalledPackages(paramInt);
  }
  
  public static Context getPluginAppContext(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getResourseIdByName(Class<?> paramClass, String paramString1, String paramString2)
  {
    int j = 0;
    for (;;)
    {
      try
      {
        Class[] arrayOfClass = paramClass.getClasses();
        int k = arrayOfClass.length;
        int i = 0;
        if (i < k)
        {
          paramClass = arrayOfClass[i];
          if (paramClass.getName().split("\\$")[1].equals(paramString1))
          {
            i = j;
            if (paramClass != null) {
              i = paramClass.getField(paramString2).getInt(paramClass);
            }
            return i;
          }
          i += 1;
        }
        else
        {
          paramClass = null;
        }
      }
      catch (NullPointerException paramClass)
      {
        return 0;
      }
      catch (NoSuchFieldException paramClass)
      {
        return 0;
      }
      catch (IllegalAccessException paramClass)
      {
        return 0;
      }
      catch (SecurityException paramClass)
      {
        return 0;
      }
      catch (IllegalArgumentException paramClass)
      {
        return 0;
      }
    }
  }
  
  static float getScale(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static String getShortDayNameFromMillsSeconds(String paramString)
  {
    if ("--".equals(paramString)) {
      return "--";
    }
    localObject = null;
    try
    {
      long l = Long.parseLong(paramString);
      paramString = Long.valueOf(l);
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
    return String.format("%ta", new Object[] { new Date(paramString.longValue()) }).toUpperCase();
  }
  
  public static String getTranslatedUV(Context paramContext, String paramString)
  {
    String str;
    if ("Low".equals(paramString)) {
      str = paramContext.getResources().getString(2131231953);
    }
    do
    {
      return str;
      if ("Moderate".equals(paramString)) {
        return paramContext.getResources().getString(2131231954);
      }
      if ("High".equals(paramString)) {
        return paramContext.getResources().getString(2131231952);
      }
      if ("Very High".equals(paramString)) {
        return paramContext.getResources().getString(2131231955);
      }
      str = paramString;
    } while (!"Extreme".equals(paramString));
    return paramContext.getResources().getString(2131231951);
  }
  
  public static int getWidgetAPILevel(Context paramContext, String paramString)
  {
    return h.a(paramContext, paramString, "WIDGET_API_LEVEL", -2);
  }
  
  public static int[] getWidgetIds(Context paramContext, String paramString)
  {
    AppWidgetManager localAppWidgetManager = AppWidgetManager.getInstance(paramContext.getApplicationContext());
    if ("com.amber.weather".equals(paramContext.getApplicationContext().getPackageName())) {
      return localAppWidgetManager.getAppWidgetIds(new ComponentName(paramContext.getApplicationContext(), "mobi.infolife.ezweather." + paramString));
    }
    return localAppWidgetManager.getAppWidgetIds(new ComponentName(paramContext.getApplicationContext(), CLASS_PATH + "." + paramString));
  }
  
  public static int[] getWidgetIdsExceptOneOneWidget(Context paramContext)
  {
    return concatArrays(getWidgetIds(paramContext, "FourTwoWidget"), new int[][] { getWidgetIds(paramContext, "FourOneWidget") });
  }
  
  public static String getWindDirectionFromDegree(Context paramContext, String paramString)
  {
    int i = 0;
    String str1;
    int j;
    if (paramString.contains("."))
    {
      str1 = paramString.substring(0, paramString.indexOf("."));
      try
      {
        j = Integer.parseInt(str1);
        i = j;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localException1.printStackTrace();
        }
      }
      if ((i != 0) && (360 != i)) {
        break label90;
      }
      str1 = paramContext.getResources().getString(2131232070);
    }
    label90:
    do
    {
      do
      {
        return str1;
        try
        {
          j = Integer.parseInt(paramString);
          i = j;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
        break;
        if ((i > 0) && (i < 45)) {
          return paramContext.getResources().getString(2131232072);
        }
        if (i == 45) {
          return paramContext.getResources().getString(2131232071);
        }
        if ((i > 45) && (i < 90)) {
          return paramContext.getResources().getString(2131232062);
        }
        if (i == 90) {
          return paramContext.getResources().getString(2131232061);
        }
        if ((i > 90) && (i < 135)) {
          return paramContext.getResources().getString(2131232063);
        }
        if (i == 135) {
          return paramContext.getResources().getString(2131232079);
        }
        if ((i > 135) && (i < 180)) {
          return paramContext.getResources().getString(2131232080);
        }
        if (i == 180) {
          return paramContext.getResources().getString(2131232078);
        }
        if ((i > 180) && (i < 225)) {
          return paramContext.getResources().getString(2131232081);
        }
        if (i == 225) {
          return paramContext.getResources().getString(2131232082);
        }
        if ((i > 225) && (i < 270)) {
          return paramContext.getResources().getString(2131232089);
        }
        if (i == 270) {
          return paramContext.getResources().getString(2131232087);
        }
        if ((i > 270) && (i < 315)) {
          return paramContext.getResources().getString(2131232088);
        }
        if (i == 315) {
          return paramContext.getResources().getString(2131232074);
        }
        str2 = paramString;
      } while (i <= 315);
      String str2 = paramString;
    } while (i >= 360);
    return paramContext.getResources().getString(2131232073);
  }
  
  public static void gotoMarket(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, 2131230992, 1).show();
      paramString.printStackTrace();
    }
  }
  
  public static boolean isLight(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      int i = Integer.parseInt(paramString1);
      int j = Integer.parseInt(paramString2);
      int k = Integer.parseInt(paramString3);
      if ((k < i) || (k >= j)) {
        break label32;
      }
    }
    catch (Exception paramString1)
    {
      label32:
      do
      {
        paramString1 = new Date(System.currentTimeMillis());
        if (paramString1.getHours() < 7) {
          break;
        }
      } while (paramString1.getHours() <= 18);
    }
    return true;
    return false;
    return false;
  }
  
  public static boolean isWidgetEnable(Context paramContext, String paramString)
  {
    Context localContext = getPluginAppContext(paramContext, paramString);
    if (localContext != null)
    {
      int i = getBoolId(localContext, "isFree");
      if ((i == 0) || (!localContext.getResources().getBoolean(i))) {}
    }
    while ((mobi.infolife.ezweather.d.a.a.d(paramContext, paramString)) || (PreferencesLibrary.isLimitedFreeRedeemAll(paramContext)) || (mobi.infolife.ezweather.d.a.a.b(paramContext, paramString)) || (mobi.infolife.ezweather.d.a.a.c(paramContext, paramString)) || (mobi.infolife.ezweather.d.a.a.e(paramContext, paramString)) || (PreferencesLibrary.isForAllPaid(paramContext)) || (PreferencesLibrary.isWidgetUnlocked(paramContext, paramString))) {
      return true;
    }
    return false;
  }
  
  private static void resetWidgetDataByWidgetId(Context paramContext, int paramInt1, int paramInt2)
  {
    if (WidgetPreferences.getWeatherDataIdByWidgetId(paramContext, paramInt2) == paramInt1)
    {
      c localC = c.a(paramContext);
      localC.a(new WeatherUtilsLibrary.3(localC, paramContext, paramInt2));
    }
  }
  
  public static void resetWidgetDataIdIfNeed(Context paramContext, int paramInt)
  {
    int j = 0;
    int[] arrayOfInt = getWidgetIds(paramContext, "FourTwoWidget");
    int k = arrayOfInt.length;
    int i = 0;
    while (i < k)
    {
      resetWidgetDataByWidgetId(paramContext, paramInt, arrayOfInt[i]);
      i += 1;
    }
    arrayOfInt = getWidgetIds(paramContext, "FourOneWidget");
    k = arrayOfInt.length;
    i = 0;
    while (i < k)
    {
      resetWidgetDataByWidgetId(paramContext, paramInt, arrayOfInt[i]);
      i += 1;
    }
    arrayOfInt = getWidgetIds(paramContext, "OneOneWidget");
    k = arrayOfInt.length;
    i = j;
    while (i < k)
    {
      resetWidgetDataByWidgetId(paramContext, paramInt, arrayOfInt[i]);
      i += 1;
    }
  }
  
  public static void showByDialog(Context paramContext, int paramInt1, int paramInt2, Activity paramActivity)
  {
    try
    {
      paramActivity = new WeakReference(paramActivity);
      if ((paramActivity.get() != null) && (!((Activity)paramActivity.get()).isFinishing()))
      {
        paramActivity = new android.app.AlertDialog.Builder(paramContext);
        paramActivity.setTitle(paramContext.getString(paramInt1));
        paramActivity.setMessage(paramContext.getString(paramInt2));
        paramActivity.setPositiveButton(2131231409, new WeatherUtilsLibrary.2());
      }
      try
      {
        paramActivity.show();
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void showDialog(Context paramContext, int paramInt1, int paramInt2, Activity paramActivity)
  {
    try
    {
      paramActivity = new WeakReference(paramActivity);
      if ((paramActivity.get() != null) && (!((Activity)paramActivity.get()).isFinishing()))
      {
        paramActivity = new android.support.v7.app.AlertDialog.Builder(paramContext);
        paramActivity.setTitle(paramContext.getString(paramInt1));
        paramActivity.setMessage(paramContext.getString(paramInt2));
        paramActivity.setPositiveButton(2131231409, new WeatherUtilsLibrary.1());
      }
      try
      {
        paramActivity.show();
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startApplicationWithPackageName(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, 2131231811, 1).show();
    }
  }
}
