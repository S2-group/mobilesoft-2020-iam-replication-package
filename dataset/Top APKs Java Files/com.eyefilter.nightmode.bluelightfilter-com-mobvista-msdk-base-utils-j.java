package com.mobvista.msdk.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.mobvista.msdk.MobVistaConstans;
import com.mobvista.msdk.b.b;
import com.mobvista.msdk.base.d.h;
import com.mobvista.msdk.base.d.o;
import com.mobvista.msdk.base.entity.CampaignEx;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

public final class j
{
  static List<String> a;
  private static String b = "[一-龥]";
  private static Pattern c = Pattern.compile("[一-龥]");
  private static int d = 1;
  private static boolean e = true;
  
  public static double a(Double paramDouble)
  {
    try
    {
      paramDouble = new DecimalFormat("#.00").format(paramDouble);
      if (p.b(paramDouble))
      {
        double d1 = Double.parseDouble(paramDouble);
        return d1;
      }
    }
    catch (Exception paramDouble)
    {
      paramDouble.printStackTrace();
    }
    return 0.0D;
  }
  
  public static int a(Activity paramActivity)
  {
    if (paramActivity == null) {
      return 0;
    }
    try
    {
      paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(paramActivity, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.heightPixels;
      return i;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return 0;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  /* Error */
  public static long a(java.io.File paramFile)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_2
    //   2: aconst_null
    //   3: astore 6
    //   5: aload_0
    //   6: invokevirtual 128	java/io/File:exists	()Z
    //   9: ifeq +37 -> 46
    //   12: new 130	java/io/FileInputStream
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 133	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   20: astore_0
    //   21: aload_0
    //   22: invokevirtual 137	java/io/FileInputStream:available	()I
    //   25: istore_1
    //   26: iload_1
    //   27: i2l
    //   28: lstore_2
    //   29: lload_2
    //   30: lstore 4
    //   32: aload_0
    //   33: ifnull +10 -> 43
    //   36: aload_0
    //   37: invokevirtual 140	java/io/FileInputStream:close	()V
    //   40: lload_2
    //   41: lstore 4
    //   43: lload 4
    //   45: lreturn
    //   46: aload_0
    //   47: invokevirtual 143	java/io/File:createNewFile	()Z
    //   50: pop
    //   51: ldc -111
    //   53: ldc -109
    //   55: invokestatic 152	com/mobvista/msdk/base/utils/g:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: aconst_null
    //   59: astore_0
    //   60: goto -31 -> 29
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_0
    //   66: lload_2
    //   67: lstore 4
    //   69: aload_0
    //   70: ifnull -27 -> 43
    //   73: aload_0
    //   74: invokevirtual 140	java/io/FileInputStream:close	()V
    //   77: lconst_0
    //   78: lreturn
    //   79: astore_0
    //   80: lconst_0
    //   81: lreturn
    //   82: astore_0
    //   83: aload 6
    //   85: ifnull +8 -> 93
    //   88: aload 6
    //   90: invokevirtual 140	java/io/FileInputStream:close	()V
    //   93: aload_0
    //   94: athrow
    //   95: astore_0
    //   96: lload_2
    //   97: lreturn
    //   98: astore 6
    //   100: goto -7 -> 93
    //   103: astore 7
    //   105: aload_0
    //   106: astore 6
    //   108: aload 7
    //   110: astore_0
    //   111: goto -28 -> 83
    //   114: astore 6
    //   116: goto -50 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	paramFile	java.io.File
    //   25	2	1	i	int
    //   1	96	2	l1	long
    //   30	38	4	l2	long
    //   3	86	6	localObject1	Object
    //   98	1	6	localException1	Exception
    //   106	1	6	localFile	java.io.File
    //   114	1	6	localException2	Exception
    //   103	6	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	21	63	java/lang/Exception
    //   46	58	63	java/lang/Exception
    //   73	77	79	java/lang/Exception
    //   5	21	82	finally
    //   46	58	82	finally
    //   36	40	95	java/lang/Exception
    //   88	93	98	java/lang/Exception
    //   21	26	103	finally
    //   21	26	114	java/lang/Exception
  }
  
  public static Drawable a(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      return new BitmapDrawable(paramBitmap);
    }
    return null;
  }
  
  public static String a(int paramInt)
  {
    if (paramInt <= 0) {
      return "00:00";
    }
    try
    {
      int j = paramInt / 60;
      if (j < 60) {
        return b(j) + ":" + b(paramInt % 60);
      }
      int i = j / 60;
      if (i > 99) {
        return "99:59:59";
      }
      j %= 60;
      String str = b(i) + ":" + b(j) + ":" + b(paramInt - i * 3600 - j * 60);
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }
  
  public static String a(JSONArray paramJSONArray)
  {
    Object localObject = com.mobvista.msdk.base.c.a.c().i();
    b.a();
    com.mobvista.msdk.b.a localA = b.b((String)localObject);
    localObject = localA;
    if (localA == null)
    {
      b.a();
      localObject = b.b();
    }
    int j = ((com.mobvista.msdk.b.a)localObject).k();
    if (paramJSONArray.length() > j)
    {
      localObject = new JSONArray();
      int i = 0;
      for (;;)
      {
        if (i < j) {
          try
          {
            ((JSONArray)localObject).put(paramJSONArray.get(i));
            i += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
      return ((JSONArray)localObject).toString();
    }
    return paramJSONArray.toString();
  }
  
  public static void a(View paramView)
  {
    if (paramView == null) {}
    for (;;)
    {
      return;
      try
      {
        if (Build.VERSION.SDK_INT >= 11)
        {
          paramView.setSystemUiVisibility(4102);
          return;
        }
      }
      catch (Throwable paramView)
      {
        paramView.printStackTrace();
      }
    }
  }
  
  public static void a(ImageView paramImageView)
  {
    if (paramImageView == null) {}
    do
    {
      return;
      try
      {
        paramImageView.setImageResource(0);
        paramImageView.setImageDrawable(null);
        paramImageView.setImageURI(null);
        paramImageView.setImageBitmap(null);
        return;
      }
      catch (Throwable paramImageView) {}
    } while (!MobVistaConstans.DEBUG);
    paramImageView.printStackTrace();
  }
  
  public static void a(List<CampaignEx> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      CampaignEx localCampaignEx = (CampaignEx)paramList.get(i);
      if ((!a(com.mobvista.msdk.base.c.a.c().g(), localCampaignEx.getPackageName())) && (localCampaignEx != null) && (localCampaignEx.getOfferType() == 99)) {
        localArrayList.add(localCampaignEx);
      }
      i += 1;
    }
    o.a(h.a(com.mobvista.msdk.base.c.a.c().g())).a(localArrayList);
  }
  
  public static boolean a()
  {
    return e;
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = "wifi".equals(paramContext.getTypeName().toLowerCase(Locale.US));
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (a == null) {
      a = new ArrayList();
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager();
        if ((com.mobvista.msdk.base.c.a.b != null) && (com.mobvista.msdk.base.c.a.b.size() != 0)) {
          break label172;
        }
        com.mobvista.msdk.base.c.a.b = new ArrayList();
        paramContext = paramContext.getInstalledPackages(0);
        i = 0;
        if (i >= paramContext.size()) {
          break label172;
        }
        com.mobvista.msdk.base.c.a.b.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
        continue;
        if (i < com.mobvista.msdk.base.c.a.b.size())
        {
          paramContext = (String)com.mobvista.msdk.base.c.a.b.get(i);
          a.add(paramContext);
          i += 1;
          continue;
        }
        boolean bool = a.contains(paramString);
        return bool;
      }
      catch (Throwable paramContext)
      {
        if (MobVistaConstans.DEBUG) {
          paramContext.printStackTrace();
        }
        return false;
      }
      return a.contains(paramString);
      label172:
      int i = 0;
    }
  }
  
  public static boolean a(CampaignEx paramCampaignEx)
  {
    if (paramCampaignEx != null) {
      try
      {
        int i = paramCampaignEx.getRetarget_offer();
        return i == 1;
      }
      catch (Exception paramCampaignEx)
      {
        paramCampaignEx.printStackTrace();
      }
    }
    return false;
  }
  
  public static <T extends String> boolean a(T paramT)
  {
    return (paramT == null) || (paramT.length() == 0);
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    try
    {
      if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0)
      {
        g.a("CommonUtils", "Permission " + paramString + " is granted");
        return true;
      }
      g.a("CommonUtils", "Permission " + paramString + " is NOT granted");
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int b()
  {
    int i = d;
    d = i + 1;
    return i;
  }
  
  public static int b(Activity paramActivity)
  {
    if (paramActivity == null) {
      return 0;
    }
    try
    {
      paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(paramActivity, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.widthPixels;
      return i;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return 0;
  }
  
  public static int b(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * paramFloat + 0.5F);
  }
  
  private static String b(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 10)) {}
    try
    {
      return "0" + Integer.toString(paramInt);
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return String.valueOf(paramInt);
    return "";
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static <T extends String> boolean b(T paramT)
  {
    return (paramT != null) && (paramT.length() > 0);
  }
  
  public static double c(String paramString)
  {
    double d1 = 0.0D;
    try
    {
      if (!TextUtils.isEmpty(paramString)) {
        d1 = Double.parseDouble(paramString);
      }
      return d1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0.0D;
  }
  
  public static void c(Context paramContext)
  {
    try
    {
      if ((a(paramContext, "com.instagram.android")) || (a(paramContext, "com.facebook.katana")))
      {
        e = true;
        return;
      }
      e = false;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      i = paramContext.getResources().getDimensionPixelSize(i);
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static float e(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int f(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return -1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if ((paramContext != null) && (paramContext.size() > 0))
        {
          int i = paramContext.size();
          return i;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return -1;
  }
  
  public static int g(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int h(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().heightPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int i(Context paramContext)
  {
    try
    {
      if (paramContext.getResources().getIdentifier("config_showNavigationBar", "bool", "android") != 0)
      {
        int i = paramContext.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        i = paramContext.getResources().getDimensionPixelSize(i);
        return i;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
}
