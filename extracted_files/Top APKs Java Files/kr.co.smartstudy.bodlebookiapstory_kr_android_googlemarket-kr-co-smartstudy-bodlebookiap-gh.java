package kr.co.smartstudy.bodlebookiap;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import kr.co.smartstudy.halib.i;
import kr.co.smartstudy.halib.p;
import kr.co.smartstudy.sspatcher.df;
import org.json.JSONException;
import org.json.JSONObject;

public final class gh
{
  static Paint a = new Paint(3);
  private static Application b = null;
  
  public gh() {}
  
  public static float a(String paramString, Float paramFloat)
  {
    return b.getSharedPreferences("bodlebookiap_pref", 0).getFloat(paramString, paramFloat.floatValue());
  }
  
  public static int a(String paramString, Integer paramInteger)
  {
    return b.getSharedPreferences("bodlebookiap_pref", 0).getInt(paramString, paramInteger.intValue());
  }
  
  public static long a(String paramString, Long paramLong)
  {
    return b.getSharedPreferences("bodlebookiap_pref", 0).getLong(paramString, paramLong.longValue());
  }
  
  private static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    float f = paramInt;
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, f, f, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return b.getSharedPreferences("bodlebookiap_pref", 0).getString(paramString1, paramString2);
  }
  
  public static i a(p paramP, String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, int paramInt1, int paramInt2)
  {
    return new i(paramP, paramP.a("@file:" + paramString1 + paramString2, new gi(paramString1, paramInt1, paramInt2)), true, true);
  }
  
  public static void a(int paramInt)
  {
    Toast.makeText(b, b.getString(paramInt), 0).show();
  }
  
  public static void a(Application paramApplication)
  {
    b = paramApplication;
  }
  
  public static void a(Context paramContext)
  {
    paramContext = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = paramContext.getDisplayMetrics();
    Configuration localConfiguration = paramContext.getConfiguration();
    if ((e.C != null) && (!e.C.equals(""))) {
      localConfiguration.locale = new Locale(e.C, localConfiguration.locale.getCountry(), localConfiguration.locale.getVariant());
    }
    if ((ay.b) && ((localConfiguration.screenLayout & 0xF) < 4)) {
      localConfiguration.screenLayout = (localConfiguration.screenLayout & 0xFFFFFFF0 | 0x4);
    }
    paramContext.updateConfiguration(localConfiguration, localDisplayMetrics);
  }
  
  public static void a(View paramView, int paramInt1, int paramInt2, ImageView.ScaleType paramScaleType)
  {
    i localI = i.a(p.a(), b.getResources(), paramInt2, false, true);
    paramView = (ImageView)paramView.findViewById(paramInt1);
    paramView.setImageDrawable(localI);
    paramView.setScaleType(paramScaleType);
  }
  
  public static void a(String paramString)
  {
    Toast.makeText(b, paramString, 0).show();
  }
  
  public static void a(String paramString, Boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("bodlebookiap_pref", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }
  
  public static boolean a(String paramString, boolean paramBoolean)
  {
    return b.getSharedPreferences("bodlebookiap_pref", 0).getBoolean(paramString, false);
  }
  
  public static HashSet<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return localHashSet;
      }
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName.toLowerCase());
      i += 1;
    }
  }
  
  public static void b(String paramString, Float paramFloat)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("bodlebookiap_pref", 0).edit();
    localEditor.putFloat(paramString, paramFloat.floatValue());
    localEditor.commit();
  }
  
  public static void b(String paramString, Integer paramInteger)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("bodlebookiap_pref", 0).edit();
    localEditor.putInt(paramString, paramInteger.intValue());
    localEditor.commit();
  }
  
  public static void b(String paramString, Long paramLong)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("bodlebookiap_pref", 0).edit();
    localEditor.putLong(paramString, paramLong.longValue());
    localEditor.commit();
  }
  
  public static void b(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("bodlebookiap_pref", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  private static byte[] b(String paramString)
  {
    Object localObject = null;
    try
    {
      byte[] arrayOfByte = new byte[(int)b.getResources().getAssets().openFd(paramString).getLength()];
      localObject = arrayOfByte;
      paramString = b.getResources().getAssets().open(paramString);
      localObject = arrayOfByte;
      paramString.read(arrayOfByte);
      localObject = arrayOfByte;
      paramString.close();
      return arrayOfByte;
    }
    catch (Exception paramString) {}
    return localObject;
  }
  
  private static void c(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("event", paramString);
      localJSONObject.put("time", df.b());
      df.a().d(localJSONObject.toString());
      return;
    }
    catch (JSONException paramString) {}
  }
}
