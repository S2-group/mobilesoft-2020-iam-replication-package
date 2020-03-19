package ee.mtakso.client.helper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import ee.mtakso.client.TaxifyClientApplication;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class TaxifyUtils
{
  private static String TAG = "taxify_" + TaxifyUtils.class.getSimpleName();
  
  public TaxifyUtils() {}
  
  public static Map<String, Boolean> areAppsInstalled(Context paramContext, @NonNull List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    Object localObject = paramList.iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashMap.put((String)((Iterator)localObject).next(), Boolean.valueOf(false));
    }
    int i;
    do
    {
      while (!paramContext.hasNext())
      {
        try
        {
          localObject = new ArrayList(paramList);
          paramList = paramList.iterator();
          if (paramList.hasNext())
          {
            localHashMap.put((String)paramList.next(), Boolean.valueOf(false));
            break;
          }
          while (paramContext == null) {}
        }
        catch (Exception paramContext)
        {
          Crashlytics.logException(paramContext);
          return localHashMap;
        }
        paramContext = paramContext.getPackageManager().getInstalledPackages(128).iterator();
      }
      paramList = (PackageInfo)paramContext.next();
      if (((List)localObject).remove(paramList.packageName)) {
        localHashMap.put(paramList.packageName, Boolean.valueOf(true));
      }
      i = ((List)localObject).size();
    } while (i >= 1);
    return localHashMap;
  }
  
  public static String capitalizeEachWord(@Nullable String paramString)
  {
    if (paramString != null) {
      return WordUtils.capitalizeFully(paramString, new char[] { 32, 9, 13, 10, 12, 45, 46, 95 });
    }
    return null;
  }
  
  public static String convertToHex(@NonNull byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int m = paramArrayOfByte.length;
    int i = 0;
    int n;
    int k;
    int j;
    if (i < m)
    {
      n = paramArrayOfByte[i];
      k = n >>> 4 & 0xF;
      j = 0;
    }
    for (;;)
    {
      if ((k >= 0) && (k <= 9)) {}
      for (char c = (char)(k + 48);; c = (char)(k - 10 + 97))
      {
        localStringBuilder.append(c);
        k = n & 0xF;
        if (j < 1) {
          break label101;
        }
        i += 1;
        break;
      }
      return localStringBuilder.toString();
      label101:
      j += 1;
    }
  }
  
  public static Bitmap createDrawableFromView(Context paramContext, View paramView)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
    paramView.measure(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    paramView.layout(0, 0, localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    paramView.buildDrawingCache();
    paramContext = Bitmap.createBitmap(paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
    paramView.draw(new Canvas(paramContext));
    return paramContext;
  }
  
  public static void decreaseTextToFitView(double paramDouble, TextView paramTextView, String paramString, float paramFloat)
  {
    Rect localRect = new Rect();
    TextPaint localTextPaint = paramTextView.getPaint();
    localTextPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    while (localRect.width() + localRect.left > paramDouble)
    {
      paramTextView.setTextSize(0, paramTextView.getTextSize() - 2.0F * paramFloat);
      localTextPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    }
  }
  
  public static String generateDeviceUuid()
  {
    Object localObject2 = TaxifyClientApplication.getTaxifyInstance();
    str = Settings.Secure.getString(((Context)localObject2).getApplicationContext().getContentResolver(), "android_id");
    try
    {
      Object localObject1 = ((TelephonyManager)((Context)localObject2).getSystemService("phone")).getDeviceId();
      if (StringUtils.isBlank((CharSequence)localObject1))
      {
        localObject1 = ((WifiManager)((Context)localObject2).getSystemService("wifi")).getConnectionInfo().getMacAddress();
        Log.d(TAG, "Generated device UID from MAC address");
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (StringUtils.isBlank((CharSequence)localObject1))
        {
          localObject2 = str;
          Log.d(TAG, "Generated device UID from androidID");
        }
        localObject1 = MessageDigest.getInstance("SHA-1");
        localObject2 = ((String)localObject2).getBytes("UTF-8");
        ((MessageDigest)localObject1).update((byte[])localObject2, 0, localObject2.length);
        localObject1 = ((MessageDigest)localObject1).digest();
        if ((localObject1 == null) || (localObject1.length <= 0)) {
          break;
        }
        return convertToHex((byte[])localObject1);
        Log.d(TAG, "Generated device UID from deviceID");
      }
      return UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      Log.d(TAG, "Generated device UID from androidID");
    }
  }
  
  public static Spanned getHtmlText(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return Html.fromHtml(paramString, 0);
    }
    return Html.fromHtml(paramString);
  }
  
  public static String getUserEmailFromAccounts(Context paramContext)
  {
    Pattern localPattern = Patterns.EMAIL_ADDRESS;
    paramContext = AccountManager.get(paramContext).getAccounts();
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramContext[i];
      if (localPattern.matcher(localObject.name).matches()) {
        return localObject.name;
      }
      i += 1;
    }
    return "";
  }
  
  public static void increaseTextToFitView(double paramDouble, TextView paramTextView, String paramString, float paramFloat1, float paramFloat2)
  {
    Rect localRect = new Rect();
    TextPaint localTextPaint = paramTextView.getPaint();
    localTextPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    float f = paramTextView.getTextSize();
    while ((localRect.width() + localRect.left < paramDouble) && (f < paramFloat2))
    {
      f = paramTextView.getTextSize();
      paramTextView.setTextSize(0, 2.0F * paramFloat1 + f);
      localTextPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    }
    if (f > paramFloat2) {}
    for (;;)
    {
      paramTextView.setTextSize(0, paramFloat2);
      return;
      paramFloat2 = f;
    }
  }
  
  public static boolean isGpsTurnedOff(Object paramObject)
  {
    return !((LocationManager)paramObject).isProviderEnabled("gps");
  }
  
  public static void removeOnGlobalLayoutListener(View paramView, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.getViewTreeObserver().removeOnGlobalLayoutListener(paramOnGlobalLayoutListener);
      return;
    }
    paramView.getViewTreeObserver().removeGlobalOnLayoutListener(paramOnGlobalLayoutListener);
  }
  
  public static String removeSpaces(String paramString)
  {
    return paramString.replaceAll(" ", "");
  }
  
  public static Bitmap resizeImageWithAspectRatio(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBitmap == null) {
      return null;
    }
    int i = paramBitmap.getHeight();
    int j = paramBitmap.getWidth();
    if (paramInt1 > 0) {}
    for (float f = paramInt1 / i;; f = paramInt2 / j)
    {
      Log.d(TAG, "Resizing image with height = " + i + ", width = " + j + ", to newHeight = " + paramInt1 + ", newWidth = " + paramInt2 + ", by scale = " + f);
      Matrix localMatrix = new Matrix();
      localMatrix.postScale(f, f);
      return Bitmap.createBitmap(paramBitmap, 0, 0, j, i, localMatrix, false);
      if (paramInt2 <= 0) {
        break;
      }
    }
  }
  
  public static String toTaxifyDateFormat(long paramLong)
  {
    return new SimpleDateFormat("MMMM dd HH:mm").format(Long.valueOf(paramLong));
  }
}
