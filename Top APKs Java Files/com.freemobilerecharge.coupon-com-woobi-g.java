package com.woobi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class g
{
  public static String a = ak.c() + "mobile?aid=%s&cid=%s";
  public static String b = ak.c() + "getSingleOffer?&aid=%s&cid=%s";
  public static String c = ak.c() + "getNonIncentSingle?&aid=%s&cid=%s";
  public static String d = ak.c() + "sponsor?&aid=%s&cid=%s";
  public static String e = ak.c() + "count?&aid=%s&cid=%s";
  public static String f = ak.c() + "closeOffer?&aid=%s&cid=%s";
  public static String g = ak.c() + "getPoints?&aid=%s&cid=%s";
  public static float h = 1.0F;
  public static float i = 0.0F;
  public static int j = -1;
  public static ProgressDialog k;
  public static int l;
  public static int m;
  private static final AtomicInteger n = new AtomicInteger(1);
  
  public static int a()
  {
    int i3;
    int i1;
    do
    {
      i3 = n.get();
      int i2 = i3 + 1;
      i1 = i2;
      if (i2 > 16777215) {
        i1 = 1;
      }
    } while (!n.compareAndSet(i3, i1));
    return i3;
  }
  
  public static int a(float paramFloat)
  {
    if ((j == -1) && (u.c)) {
      Log.e("WoobiUtils", "Requested MAX_SCREEN_PIXELS_FOR_TEXT before it was init properly.");
    }
    return (int)(j * paramFloat);
  }
  
  public static LayerDrawable a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object localObject = new float[8];
    Object tmp8_6 = localObject;
    tmp8_6[0] = 5.0F;
    Object tmp13_8 = tmp8_6;
    tmp13_8[1] = 5.0F;
    Object tmp18_13 = tmp13_8;
    tmp18_13[2] = 5.0F;
    Object tmp23_18 = tmp18_13;
    tmp23_18[3] = 5.0F;
    Object tmp28_23 = tmp23_18;
    tmp28_23[4] = 5.0F;
    Object tmp33_28 = tmp28_23;
    tmp33_28[5] = 5.0F;
    Object tmp38_33 = tmp33_28;
    tmp38_33[6] = 5.0F;
    Object tmp44_38 = tmp38_33;
    tmp44_38[7] = 5.0F;
    tmp44_38;
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape((float[])localObject, null, null));
    localShapeDrawable.getPaint().setColor(0);
    localShapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
    localShapeDrawable.getPaint().setStrokeWidth(0.0F);
    localObject = new ShapeDrawable(new RoundRectShape((float[])localObject, null, null));
    ((ShapeDrawable)localObject).getPaint().setColor(-16711738);
    ((ShapeDrawable)localObject).getPaint().setStyle(Paint.Style.FILL);
    return new LayerDrawable(new Drawable[] { localObject, localShapeDrawable });
  }
  
  public static StateListDrawable a(Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    paramBitmap2 = new BitmapDrawable(paramBitmap2);
    localStateListDrawable.addState(new int[] { 16842919 }, paramBitmap2);
    localStateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(paramBitmap1));
    return localStateListDrawable;
  }
  
  public static File a(Context paramContext, String paramString)
  {
    paramContext = new File(paramContext.getFilesDir(), paramString);
    if (paramContext.exists()) {
      return paramContext;
    }
    return null;
  }
  
  public static String a(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences((String)ak.a.get(), 0);
    String str1 = paramContext.getString("client_id", null);
    if (str1 != null) {
      return str1;
    }
    String str2 = Long.toHexString(new Random().nextLong());
    paramContext.edit().putString("client_id", str2).commit();
    return str1;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    int i1 = paramString2.indexOf(paramString1.concat("="));
    if (i1 == -1) {
      return "";
    }
    paramString1 = paramString2.substring(i1 + paramString1.length() + 1);
    int i2 = paramString1.indexOf("&");
    i1 = i2;
    if (i2 == -1) {
      i1 = paramString1.length();
    }
    return paramString1.substring(0, i1);
  }
  
  public static String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.format(paramString1, new Object[] { paramString3, paramString4 }));
    if (paramString5 != null) {
      localStringBuilder.append(String.format("&customParams=%s", new Object[] { paramString5 }));
    }
    if (paramString6 != null) {
      localStringBuilder.append(String.format("&usrStat=%s", new Object[] { paramString6 }));
    }
    if (paramString7 != null) {
      localStringBuilder.append(String.format("&level=%s", new Object[] { paramString7 }));
    }
    if (paramString2 != null) {
      localStringBuilder.append(String.format("&adType=%s", new Object[] { paramString2 }));
    }
    if (paramString8 != null) {
      localStringBuilder.append(String.format("&idfa=%s", new Object[] { paramString8 }));
    }
    if (paramString9 != null) {
      localStringBuilder.append(String.format("&hs=%s", new Object[] { paramString9 }));
    }
    localStringBuilder.append(String.format("&sdkVer=%s", new Object[] { u.b }));
    return localStringBuilder.toString();
  }
  
  public static void a(Activity paramActivity)
  {
    if (h == 1.0F)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      h = localDisplayMetrics.density;
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = a(f, null, paramString1, paramString2, null, null, null, null, null);
    if (u.c) {
      Log.i("WoobiUtils", "closed offer: " + paramContext);
    }
    new c(new i()).execute(new String[] { paramContext });
  }
  
  public static boolean a(String paramString)
  {
    return (paramString.startsWith("https://play.google.com/store/apps/details")) || (Uri.parse(paramString).getScheme().equals("market"));
  }
  
  public static int b(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static String b(String paramString)
  {
    if (paramString != null) {
      return paramString.substring(paramString.lastIndexOf("/") + 1);
    }
    return null;
  }
  
  public static final void b()
  {
    a = ak.c() + "mobile?aid=%s&cid=%s";
    b = ak.c() + "getSingleOffer?&aid=%s&cid=%s";
    c = ak.c() + "getNonIncentSingle?&aid=%s&cid=%s";
    d = ak.c() + "sponsor?&aid=%s&cid=%s";
    e = ak.c() + "count?&aid=%s&cid=%s";
    f = ak.c() + "closeOffer?&aid=%s&cid=%s";
    g = ak.c() + "getPoints?aid=%s&cid=%s";
  }
  
  public static void b(Activity paramActivity)
  {
    int i1 = 0;
    TypedValue localTypedValue = new TypedValue();
    if ((Build.VERSION.SDK_INT < 11) || (paramActivity.getApplicationContext().getTheme().resolveAttribute(16843499, localTypedValue, true))) {
      i1 = TypedValue.complexToDimensionPixelSize(localTypedValue.data, paramActivity.getApplicationContext().getResources().getDisplayMetrics());
    }
    i = i1;
    if (u.c) {
      Log.i("WoobiUtils", "ACTION_BAR_HEIGHT: " + i);
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    return new File(paramContext.getFilesDir(), paramString).delete();
  }
  
  public static final String c(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getLanguage();
  }
  
  public static void c()
  {
    if (k != null)
    {
      k.dismiss();
      k = null;
    }
  }
  
  public static void c(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    l = localDisplayMetrics.heightPixels;
    m = localDisplayMetrics.widthPixels;
    if (l >= m)
    {
      j = m;
      return;
    }
    j = l;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    for (;;)
    {
      try
      {
        paramString = new ZipInputStream(new FileInputStream(new File(paramContext.getFilesDir(), paramString)));
        Object localObject = paramString.getNextEntry();
        if (localObject == null)
        {
          paramString.close();
          return true;
        }
        Log.i("WoobiUtils", "Unzipping file " + ((ZipEntry)localObject).getName());
        localObject = paramContext.getFilesDir() + "/" + ((ZipEntry)localObject).getName();
        FileOutputStream localFileOutputStream = new FileOutputStream((String)localObject, true);
        i1 = paramString.read();
        if (i1 == -1)
        {
          localFileOutputStream.close();
          Log.i("WoobiUtils", "Unziped file to: " + (String)localObject);
          continue;
        }
        localFileOutputStream.write(i1);
      }
      catch (Exception paramContext)
      {
        Log.e("WoobiUtils", "Unzip exception", paramContext);
        return false;
      }
      int i1 = paramString.read();
    }
  }
  
  public static int d()
  {
    return m;
  }
  
  public static void d(Activity paramActivity)
  {
    ProgressDialog localProgressDialog = new ProgressDialog(paramActivity);
    k = localProgressDialog;
    localProgressDialog.setCancelable(true);
    if ((!k.isShowing()) && (paramActivity != null) && (!paramActivity.isFinishing()))
    {
      k.show();
      k.setContentView(new ProgressBar(paramActivity));
    }
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((!localPackageInfo.packageName.equals("com.google.market")) && (!localPackageInfo.packageName.equals("com.android.vending")));
    return true;
  }
  
  public static String e(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static void e(Activity paramActivity)
  {
    String str1 = an.a("OPTOUT_DIAG_TITLE", an.a("OPTOUT_DIAG_TITLE"));
    String str3 = an.a("OPTOUT_DIAG_TEXT_ANDROID", an.a("OPTOUT_DIAG_TEXT_ANDROID"));
    String str2 = an.a("OPTOUT_DIAG_OK", an.a("OPTOUT_DIAG_OK"));
    LinearLayout localLinearLayout1 = new LinearLayout(paramActivity);
    LinearLayout localLinearLayout2 = new LinearLayout(paramActivity);
    localLinearLayout1.setOrientation(1);
    localLinearLayout2.setOrientation(0);
    TextView localTextView = new TextView(paramActivity);
    localTextView.setText(str3);
    localLinearLayout2.addView(localTextView);
    localLinearLayout1.addView(localLinearLayout2);
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setTitle(str1);
    paramActivity.setView(localLinearLayout1);
    paramActivity.setCancelable(false);
    paramActivity.setNegativeButton(str2, new h());
    paramActivity.create().show();
  }
}
