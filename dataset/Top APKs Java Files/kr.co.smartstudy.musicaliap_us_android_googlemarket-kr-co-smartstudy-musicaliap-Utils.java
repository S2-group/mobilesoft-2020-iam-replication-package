package kr.co.smartstudy.musicaliap;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
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
import kr.co.smartstudy.halib.i;
import kr.co.smartstudy.halib.p;
import kr.co.smartstudy.sspatcher.cz;
import org.json.JSONException;
import org.json.JSONObject;

public final class Utils
{
  private static Application a = null;
  
  public Utils() {}
  
  private static float a(float paramFloat)
  {
    return a.getResources().getDisplayMetrics().density * paramFloat;
  }
  
  private static float a(String paramString, Float paramFloat)
  {
    return a.getSharedPreferences("musicaliap_pref", 0).getFloat(paramString, paramFloat.floatValue());
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
  
  public static Boolean a(String paramString, Boolean paramBoolean)
  {
    return Boolean.valueOf(a.getSharedPreferences("musicaliap_pref", 0).getBoolean(paramString, paramBoolean.booleanValue()));
  }
  
  public static void a(int paramInt)
  {
    Toast.makeText(a, a.getString(paramInt), 0).show();
  }
  
  public static void a(Application paramApplication)
  {
    a = paramApplication;
  }
  
  public static void a(Context paramContext)
  {
    paramContext = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = paramContext.getDisplayMetrics();
    paramContext.updateConfiguration(paramContext.getConfiguration(), localDisplayMetrics);
  }
  
  public static void a(View paramView, int paramInt1, int paramInt2, ImageView.ScaleType paramScaleType)
  {
    i localI = i.a(p.a(), a.getResources(), paramInt2, false, true);
    paramView = (ImageView)paramView.findViewById(paramInt1);
    paramView.setImageDrawable(localI);
    paramView.setScaleType(paramScaleType);
  }
  
  public static void a(String paramString)
  {
    Toast.makeText(a, paramString, 0).show();
  }
  
  public static void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = a.getSharedPreferences("musicaliap_pref", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  private static String b(String paramString1, String paramString2)
  {
    return a.getSharedPreferences("musicaliap_pref", 0).getString(paramString1, paramString2);
  }
  
  private static HashSet b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return localHashSet;
      }
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      String str1 = localPackageInfo.packageName.toLowerCase();
      String str2 = localPackageInfo.versionName;
      int j = localPackageInfo.versionCode;
      localHashSet.add(str1);
      i += 1;
    }
  }
  
  public static void b(String paramString, Boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = a.getSharedPreferences("musicaliap_pref", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }
  
  private static void b(String paramString, Float paramFloat)
  {
    SharedPreferences.Editor localEditor = a.getSharedPreferences("musicaliap_pref", 0).edit();
    localEditor.putFloat(paramString, paramFloat.floatValue());
    localEditor.commit();
  }
  
  private static byte[] b(String paramString)
  {
    Object localObject = null;
    try
    {
      byte[] arrayOfByte = new byte[(int)a.getResources().getAssets().openFd(paramString).getLength()];
      localObject = arrayOfByte;
      paramString = a.getResources().getAssets().open(paramString);
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
      localJSONObject.put("time", cz.b());
      cz.a().d(localJSONObject.toString());
      return;
    }
    catch (JSONException paramString) {}
  }
}
