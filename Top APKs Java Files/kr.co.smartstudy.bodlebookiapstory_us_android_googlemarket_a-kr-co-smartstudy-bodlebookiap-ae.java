package kr.co.smartstudy.bodlebookiap;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import kr.co.smartstudy.halib.e;
import kr.co.smartstudy.halib.f;
import kr.co.smartstudy.halib.f.a;
import kr.co.smartstudy.halib.f.b;
import kr.co.smartstudy.sspatcher.j;
import kr.co.smartstudy.sspatcher.m;
import kr.co.smartstudy.sspatcher.r;
import kr.co.smartstudy.sspatcher.r.k;
import kr.co.smartstudy.sspatcher.w;
import org.json.JSONException;
import org.json.JSONObject;

public class ae
{
  static Paint a = new Paint(3);
  private static Application b;
  
  public ae() {}
  
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
  
  public static e a(f paramF, String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, final int paramInt1, final int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("@file:");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(paramString2);
    paramF = new e(paramF, paramF.a(localStringBuilder.toString(), new f.a()
    {
      public Bitmap a(f.b paramAnonymousB, j<Long, Long, Long> paramAnonymousJ)
      {
        paramAnonymousB = new BitmapFactory.Options();
        paramAnonymousB.inDither = true;
        paramAnonymousB.inTempStorage = new byte['䀀'];
        paramAnonymousB.inSampleSize = 1;
        paramAnonymousJ = new BitmapFactory.Options();
        paramAnonymousJ.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.a, paramAnonymousJ);
        if ((paramAnonymousJ.outWidth > 0) && (paramAnonymousJ.outWidth > paramInt1 * 2) && (paramAnonymousJ.outHeight > paramInt2 * 2)) {
          paramAnonymousB.inSampleSize = 2;
        }
        paramAnonymousJ = BitmapFactory.decodeFile(this.a, paramAnonymousB);
        paramAnonymousB = paramAnonymousJ;
        if (paramAnonymousJ != null) {
          if (paramAnonymousJ.getWidth() <= paramInt1)
          {
            paramAnonymousB = paramAnonymousJ;
            if (paramAnonymousJ.getHeight() <= paramInt2) {}
          }
          else
          {
            try
            {
              paramAnonymousB = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
              new Canvas(paramAnonymousB).drawBitmap(paramAnonymousJ, null, new Rect(0, 0, paramInt1, paramInt2), ae.a);
              paramAnonymousJ.recycle();
            }
            catch (OutOfMemoryError paramAnonymousB)
            {
              paramAnonymousJ.recycle();
              throw paramAnonymousB;
            }
          }
        }
        paramAnonymousJ = paramAnonymousB;
        if (paramAnonymousB == null)
        {
          paramAnonymousJ = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
          paramAnonymousJ.setPixel(0, 0, 0);
        }
        return paramAnonymousJ;
      }
    }), paramBoolean1, paramBoolean2);
    if (!paramBoolean3) {
      paramF.e();
    }
    return paramF;
  }
  
  public static r.k a()
  {
    r.k localK = r.a().f();
    boolean bool;
    switch (2.b[localK.ordinal()])
    {
    default: 
      return localK;
    case 4: 
      n.f = true;
      return localK;
    case 3: 
      bool = n.e ^ true;
      break;
    case 1: 
    case 2: 
      if (!n.d) {
        return localK;
      }
      bool = false;
      n.d = false;
    }
    n.f = bool;
    return localK;
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
    if (h.C == null) {
      h.C = localConfiguration.locale.getCountry().toLowerCase();
    }
    if ((h.B != null) && (!h.B.equals(""))) {
      localConfiguration.locale = new Locale(h.B, localConfiguration.locale.getCountry(), localConfiguration.locale.getVariant());
    }
    if ((n.b) && ((localConfiguration.screenLayout & 0xF) < 4)) {
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
    e localE = e.a(f.a(), b.getResources(), paramInt2, false, true);
    paramView = (ImageView)paramView.findViewById(paramInt1);
    paramView.setImageDrawable(localE);
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
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = new byte[(int)b.getResources().getAssets().openFd(paramString).getLength()];
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    try
    {
      paramString = b.getResources().getAssets().open(paramString);
      paramString.read(arrayOfByte);
      paramString.close();
      return arrayOfByte;
    }
    catch (Exception paramString) {}
    return null;
    return arrayOfByte;
  }
  
  public static HashSet<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
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
      localJSONObject.put("time", w.b());
      w.a().d(localJSONObject.toString());
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
  
  public static class a
  {
    public a() {}
    
    public static boolean a(Context paramContext, String paramString)
    {
      switch (ae.2.a[h.g.ordinal()])
      {
      default: 
        return false;
      case 5: 
        return c(paramContext, paramString);
      case 4: 
        return f(paramContext, paramString);
      case 3: 
        return e(paramContext, paramString);
      case 2: 
        return g(paramContext, paramString);
      }
      return b(paramContext, paramString);
    }
    
    public static boolean b(final Context paramContext, String paramString)
    {
      if (paramString.length() > 0)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              Object localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("tstore://PRODUCT_VIEW/");
              ((StringBuilder)localObject1).append(this.a);
              ((StringBuilder)localObject1).append("/0");
              localObject1 = ((StringBuilder)localObject1).toString();
              paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject1)));
              return;
            }
            catch (Exception localException)
            {
              m.a("InstallHelper", "", localException);
              return;
              Object localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("http://m.tstore.co.kr/userpoc/mp.jsp?pid=");
              ((StringBuilder)localObject2).append(this.a);
              localObject2 = ((StringBuilder)localObject2).toString();
              paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject2)));
              return;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              for (;;) {}
            }
          }
        }, 500L);
        return true;
      }
      return false;
    }
    
    public static boolean c(final Context paramContext, String paramString)
    {
      if (paramString.length() > 0)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              Object localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("appstore://store?originalProductId=");
              ((StringBuilder)localObject1).append(this.a);
              localObject1 = ((StringBuilder)localObject1).toString();
              paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject1)));
              return;
            }
            catch (Exception localException)
            {
              m.a("InstallHelper", "", localException);
              return;
              Object localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("http://m.nstore.naver.com/appstore/web/detail.nhn?originalProductId=");
              ((StringBuilder)localObject2).append(this.a);
              localObject2 = ((StringBuilder)localObject2).toString();
              paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject2)));
              return;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              for (;;) {}
            }
          }
        }, 500L);
        return true;
      }
      return false;
    }
    
    public static boolean d(final Context paramContext, String paramString)
    {
      if (paramString.length() > 0)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              Intent localIntent = new Intent();
              localIntent.setClassName("android.lgt.appstore", "android.lgt.appstore.Store");
              localIntent.addFlags(268435456);
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("PID=");
              localStringBuilder.append(this.a);
              localIntent.putExtra("payload", localStringBuilder.toString());
              paramContext.startActivity(localIntent);
              return;
            }
            catch (Exception localException1)
            {
              m.a("InstallHelper", "", localException1);
              return;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              for (;;)
              {
                try
                {
                  Object localObject = new StringBuilder();
                  ((StringBuilder)localObject).append("ozstore://STORE/PID=");
                  ((StringBuilder)localObject).append(this.a);
                  localObject = ((StringBuilder)localObject).toString();
                  paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject)));
                  return;
                }
                catch (Exception localException2) {}
                localActivityNotFoundException = localActivityNotFoundException;
              }
            }
          }
        }, 500L);
        return true;
      }
      return false;
    }
    
    public static boolean e(final Context paramContext, String paramString)
    {
      if (paramString.length() > 0)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              Intent localIntent = new Intent();
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("samsungapps://ProductDetail/");
              localStringBuilder.append(this.a);
              localIntent.setData(Uri.parse(localStringBuilder.toString()));
              localIntent.addFlags(335544320);
              paramContext.startActivity(localIntent);
              return;
            }
            catch (Exception localException)
            {
              m.a("InstallHelper", "", localException);
            }
          }
        }, 500L);
        return true;
      }
      return false;
    }
    
    public static boolean f(final Context paramContext, String paramString)
    {
      if (paramString.length() > 0)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              Intent localIntent = new Intent("android.intent.action.VIEW");
              localIntent.addFlags(805306368);
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("amzn://apps/android?p=");
              localStringBuilder.append(this.a);
              localIntent.setData(Uri.parse(localStringBuilder.toString()));
              paramContext.startActivity(localIntent);
              return;
            }
            catch (Exception localException)
            {
              m.a("InstallHelper", "", localException);
            }
          }
        }, 500L);
        return true;
      }
      return false;
    }
    
    public static boolean g(final Context paramContext, String paramString)
    {
      if (paramString.length() > 0)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              Intent localIntent = new Intent("android.intent.action.VIEW");
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("market://details?id=");
              localStringBuilder.append(this.a);
              localIntent.setData(Uri.parse(localStringBuilder.toString()));
              paramContext.startActivity(localIntent);
              return;
            }
            catch (Exception localException)
            {
              m.a("InstallHelper", "", localException);
              return;
              Object localObject = new StringBuilder();
              ((StringBuilder)localObject).append("http://market.android.com/details?id=");
              ((StringBuilder)localObject).append(this.a);
              localObject = ((StringBuilder)localObject).toString();
              paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject)));
              return;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              for (;;) {}
            }
          }
        }, 500L);
        return true;
      }
      return false;
    }
  }
}
