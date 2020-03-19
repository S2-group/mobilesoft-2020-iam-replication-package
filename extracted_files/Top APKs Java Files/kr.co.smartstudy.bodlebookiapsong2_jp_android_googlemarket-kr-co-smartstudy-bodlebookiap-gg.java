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
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import kr.co.smartstudy.halib.l;
import kr.co.smartstudy.halib.s;
import kr.co.smartstudy.sspatcher.bt;
import kr.co.smartstudy.sspatcher.dd;
import kr.co.smartstudy.sspatcher.dt;
import org.json.JSONException;
import org.json.JSONObject;

public class gg
{
  static Paint a = new Paint(3);
  private static Application b = null;
  
  public gg() {}
  
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
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt)
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
  
  public static l a(s paramS, String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, int paramInt1, int paramInt2)
  {
    paramS = new l(paramS, paramS.a("@file:" + paramString1 + paramString2, new gh(paramString1, paramInt1, paramInt2)), paramBoolean1, paramBoolean2);
    if (!paramBoolean3) {
      paramS.e();
    }
    return paramS;
  }
  
  public static dd a()
  {
    boolean bool = true;
    dd localDd = bt.a().f();
    switch (gi.b[localDd.ordinal()])
    {
    default: 
    case 1: 
    case 2: 
      do
      {
        return localDd;
      } while (!bd.d);
      bd.d = false;
      bd.f = false;
      return localDd;
    case 3: 
      if (!bd.e) {}
      for (;;)
      {
        bd.f = bool;
        return localDd;
        bool = false;
      }
    }
    bd.f = true;
    return localDd;
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
    if (k.D == null) {
      k.D = localConfiguration.locale.getCountry().toLowerCase();
    }
    if ((k.C != null) && (!k.C.equals(""))) {
      localConfiguration.locale = new Locale(k.C, localConfiguration.locale.getCountry(), localConfiguration.locale.getVariant());
    }
    if ((bd.b) && ((localConfiguration.screenLayout & 0xF) < 4)) {
      localConfiguration.screenLayout = (localConfiguration.screenLayout & 0xFFFFFFF0 | 0x4);
    }
    paramContext.updateConfiguration(localConfiguration, localDisplayMetrics);
  }
  
  public static void a(View paramView)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return;
    }
    paramView.setSystemUiVisibility(5894);
  }
  
  public static void a(View paramView, int paramInt1, int paramInt2, ImageView.ScaleType paramScaleType)
  {
    l localL = l.a(s.a(), b.getResources(), paramInt2, false, true);
    paramView = (ImageView)paramView.findViewById(paramInt1);
    paramView.setImageDrawable(localL);
    paramView.setScaleType(paramScaleType);
  }
  
  public static void a(String paramString, Boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("bodlebookiap_pref", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }
  
  public static boolean a(String paramString, boolean paramBoolean)
  {
    return b.getSharedPreferences("bodlebookiap_pref", 0).getBoolean(paramString, paramBoolean);
  }
  
  public static byte[] a(String paramString)
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
  
  public static HashSet<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName.toLowerCase());
      i += 1;
    }
    return localHashSet;
  }
  
  public static void b(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("event", paramString);
      localJSONObject.put("time", dt.b());
      dt.a().d(localJSONObject.toString());
      return;
    }
    catch (JSONException paramString) {}
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
  
  public static void c(String paramString)
  {
    Toast.makeText(b, paramString, 0).show();
  }
}
