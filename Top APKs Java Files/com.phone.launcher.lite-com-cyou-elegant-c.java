package com.cyou.elegant;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.android.volley.toolbox.s;
import com.cyou.elegant.model.CountryModel;
import com.cyou.elegant.model.ThemeInfoModel;
import com.cyou.elegant.model.ThemeInfoModel.Preview;
import com.cyou.elegant.model.WallPaperUnit;
import com.cyou.elegant.util.f;
import com.cyou.elegant.util.h;
import com.cyou.elegant.util.i;
import com.cyou.elegant.util.j;
import com.cyou.elegant.widget.l;
import com.cyou.elegant.widget.m;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c
{
  private static Bitmap a(File paramFile, int paramInt1, int paramInt2)
  {
    try
    {
      paramFile = BitmapFactory.decodeFile(paramFile.getAbsolutePath());
      int i = paramFile.getWidth();
      int j = paramFile.getHeight();
      if (i / j > 0.5625D) {
        return paramFile;
      }
      float f1 = paramInt1 / i;
      float f2 = paramInt2 / j;
      Matrix localMatrix = new Matrix();
      localMatrix.postScale(f1, f2);
      paramFile = Bitmap.createBitmap(paramFile, 0, 0, i, j, localMatrix, true);
      return paramFile;
    }
    catch (OutOfMemoryError paramFile)
    {
      paramFile.printStackTrace();
    }
    return null;
  }
  
  public static File a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject;
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null)) {
      localObject = null;
    }
    File localFile;
    do
    {
      return localObject;
      localFile = b(a(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString1), com.e.a.c.a.a(paramString2));
      localObject = localFile;
    } while (localFile != null);
    return b(a(g(paramContext), paramString1), com.e.a.c.a.a(paramString2));
  }
  
  public static File a(String paramString1, String paramString2, String paramString3)
  {
    String str = paramString1 + File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + paramString2;
    paramString1 = new File(str, paramString3 + ".jpg");
    if (paramString1.exists()) {}
    do
    {
      do
      {
        return paramString1;
        paramString2 = new File(str, paramString3 + ".png");
        paramString1 = paramString2;
      } while (paramString2.exists());
      paramString2 = new File(str, paramString3);
      paramString1 = paramString2;
    } while (paramString2.exists());
    return null;
  }
  
  public static String a(Activity paramActivity)
  {
    if (paramActivity == null) {}
    for (;;)
    {
      return "";
      try
      {
        paramActivity = paramActivity.getPackageManager().getApplicationInfo(paramActivity.getPackageName(), 128);
        if ((paramActivity != null) && (paramActivity.metaData != null))
        {
          paramActivity = paramActivity.metaData.get("CYOU_CHANNEL");
          if (paramActivity != null)
          {
            paramActivity = paramActivity.toString();
            return paramActivity;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
    return "";
  }
  
  public static String a(Context paramContext)
  {
    String str = File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + "volley";
    File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str);
    if (a(Environment.getExternalStorageDirectory().getAbsolutePath()))
    {
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      return localFile.getAbsolutePath();
    }
    paramContext = new File(g(paramContext) + str);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static String a(Context paramContext, String paramString)
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (a(str)) {}
    }
    else
    {
      paramContext = g(paramContext);
      if (paramContext != null)
      {
        localObject = paramContext;
        if (a(paramContext)) {}
      }
      else
      {
        return null;
      }
    }
    paramContext = new File(b((String)localObject), paramString);
    if (paramContext.isFile()) {
      paramContext.delete();
    }
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext.getPath();
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return new StringBuilder().append(b(paramString1)).append(File.separator).append(".ThemeResources").toString() + File.separator + paramString2;
  }
  
  public static JSONArray a(JSONObject paramJSONObject, String paramString1, String paramString2, StringBuffer paramStringBuffer)
  {
    paramJSONObject = paramJSONObject.optJSONObject("data");
    if (paramJSONObject == null)
    {
      d.a().b(paramString1);
      return null;
    }
    String str = paramJSONObject.optString("code");
    paramStringBuffer.append(str);
    if ((str != null) && ("103".equals(str)))
    {
      d.a().b(paramString1);
      return null;
    }
    paramJSONObject = paramJSONObject.optJSONArray(paramString2);
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0))
    {
      d.a().b(paramString1);
      return null;
    }
    return paramJSONObject;
  }
  
  public static void a(Activity paramActivity, ThemeInfoModel paramThemeInfoModel)
  {
    File localFile;
    if (((TextUtils.equals(paramThemeInfoModel.w, "DIY")) || (TextUtils.equals(paramThemeInfoModel.w, "OLD"))) && (!TextUtils.isEmpty(paramThemeInfoModel.x))) {
      localFile = new File(paramThemeInfoModel.x);
    }
    for (;;)
    {
      Intent localIntent;
      if ((localFile != null) && (localFile.exists()))
      {
        localIntent = h(paramActivity);
        localIntent.putExtra("action", "com.cyou.clauncher.theme.applytheme");
        localIntent.putExtra("ThemePackage", localFile.getAbsolutePath());
        localIntent.putExtra("only_id", paramThemeInfoModel.r);
        localIntent.putExtra("theme_id", paramThemeInfoModel.a);
        if (!c(paramActivity, paramThemeInfoModel.r)) {
          break label179;
        }
        paramThemeInfoModel = paramThemeInfoModel.r;
        localIntent.putExtra("package", paramThemeInfoModel);
      }
      try
      {
        paramActivity.startActivity(localIntent);
        f.a(paramActivity);
        f.a(paramActivity, f.c(paramActivity) + 1);
        paramActivity.setResult(801);
        paramActivity.finish();
        return;
        localFile = a(paramActivity, paramThemeInfoModel.r, paramThemeInfoModel.i);
        continue;
        label179:
        paramThemeInfoModel = "com.cyou.cma.clauncher.theme.v" + paramThemeInfoModel.r;
      }
      catch (Exception paramThemeInfoModel)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(final Activity paramActivity, final boolean paramBoolean, Runnable paramRunnable)
  {
    com.cyou.elegant.b.a.a(paramActivity, new com.cyou.elegant.b.b()
    {
      public final void a()
      {
        if (c.this != null) {
          c.this.run();
        }
      }
      
      public final void b()
      {
        l localL = new l(paramActivity);
        if (paramBoolean) {
          localL.a(R.string.request_theme_storage);
        }
        localL.a(new m()
        {
          public final void a()
          {
            com.cyou.elegant.b.a.a(c.6.this.b);
          }
        });
        try
        {
          localL.show();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public static void a(Context paramContext, ThemeInfoModel paramThemeInfoModel)
  {
    com.cyou.elegant.data.b.c(paramContext, paramThemeInfoModel.r);
    b(paramContext, paramThemeInfoModel);
    a(paramContext, com.cyou.elegant.data.b.a(paramContext, null, null));
  }
  
  public static void a(Context paramContext, WallPaperUnit paramWallPaperUnit)
  {
    if (paramWallPaperUnit.k != null)
    {
      File localFile = new File(paramWallPaperUnit.k);
      if (localFile.exists()) {
        localFile.delete();
      }
    }
    do
    {
      return;
      paramWallPaperUnit = com.e.a.c.a.a(paramWallPaperUnit.e);
      paramContext = a(g(paramContext), ".WallpaperResources", paramWallPaperUnit);
      if ((paramContext != null) && (paramContext.exists())) {
        paramContext.delete();
      }
      paramContext = a(Environment.getExternalStorageDirectory().getAbsolutePath(), ".WallpaperResources", paramWallPaperUnit);
    } while ((paramContext == null) || (!paramContext.exists()));
    paramContext.delete();
  }
  
  public static void a(Context paramContext, File paramFile)
  {
    if (paramFile == null) {
      f.a(paramContext);
    }
    if (paramFile == null) {
      return;
    }
    WallpaperManager localWallpaperManager = WallpaperManager.getInstance(paramContext);
    for (;;)
    {
      Bitmap localBitmap;
      try
      {
        localBitmap = a(paramFile, h.a(paramContext), h.b(paramContext));
        if (localBitmap == null)
        {
          localWallpaperManager.setStream(new FileInputStream(paramFile));
          paramFile = paramFile.getName();
          int i = paramFile.indexOf(".");
          if (i == -1) {
            break;
          }
          f.a(paramContext, paramFile.substring(0, i));
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      localWallpaperManager.suggestDesiredDimensions(h.a(paramContext), h.b(paramContext));
      localWallpaperManager.setStream(i.a(localBitmap));
    }
    f.a(paramContext, paramFile);
  }
  
  public static void a(Context paramContext, ArrayList<ThemeInfoModel> paramArrayList)
  {
    a(paramArrayList, new File(a(paramContext, ".ThemeResources"), "theme.bp"));
  }
  
  public static void a(Context paramContext, List<com.cyou.elegant.model.a> paramList)
  {
    if ((paramContext == null) || (paramList == null) || (paramList.isEmpty())) {}
    label103:
    for (;;)
    {
      return;
      paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
      paramList = paramList.iterator();
      for (;;)
      {
        if (!paramList.hasNext()) {
          break label103;
        }
        com.cyou.elegant.model.a localA = (com.cyou.elegant.model.a)paramList.next();
        Iterator localIterator = paramContext.iterator();
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (!TextUtils.equals(localA.b, localApplicationInfo.packageName)) {
            break;
          }
          localA.o = true;
        }
      }
    }
  }
  
  public static void a(final ThemeInfoModel paramThemeInfoModel, String paramString)
  {
    new Thread()
    {
      public final void run()
      {
        d localD = d.a();
        File localFile = new File(c.this, com.e.a.c.a.a(paramThemeInfoModel.c) + ".jpg");
        if (!localFile.isFile()) {
          localD.a(paramThemeInfoModel.c, localFile);
        }
        int i = 0;
        while (i < paramThemeInfoModel.l.size())
        {
          localFile = new File(c.this, com.e.a.c.a.a(((ThemeInfoModel.Preview)paramThemeInfoModel.l.get(i)).a) + ".jpg");
          if (!localFile.isFile()) {
            localD.a(((ThemeInfoModel.Preview)paramThemeInfoModel.l.get(i)).a, localFile);
          }
          i += 1;
        }
      }
    }.start();
  }
  
  /* Error */
  private static void a(final ArrayList<ThemeInfoModel> paramArrayList, File paramFile)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnull +12 -> 16
    //   7: aload_0
    //   8: invokevirtual 446	java/util/ArrayList:isEmpty	()Z
    //   11: istore_2
    //   12: iload_2
    //   13: ifeq +7 -> 20
    //   16: ldc 2
    //   18: monitorexit
    //   19: return
    //   20: new 14	com/cyou/elegant/c$5
    //   23: dup
    //   24: aload_1
    //   25: aload_0
    //   26: invokespecial 449	com/cyou/elegant/c$5:<init>	(Ljava/io/File;Ljava/util/ArrayList;)V
    //   29: invokevirtual 452	java/lang/Thread:start	()V
    //   32: goto -16 -> 16
    //   35: astore_0
    //   36: ldc 2
    //   38: monitorexit
    //   39: aload_0
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	paramArrayList	ArrayList<ThemeInfoModel>
    //   0	41	1	paramFile	File
    //   11	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   7	12	35	finally
    //   20	32	35	finally
  }
  
  private static void a(ArrayList<String> paramArrayList, String paramString)
  {
    paramString = new File(b(paramString) + File.separator + ".WallpaperResources").listFiles();
    if (paramString == null) {}
    for (;;)
    {
      return;
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        if (localObject.isFile()) {
          paramArrayList.add(localObject.toString());
        }
        i += 1;
      }
    }
  }
  
  public static boolean a()
  {
    boolean bool = false;
    try
    {
      i = Integer.valueOf(Build.VERSION.SDK).intValue();
      if (i > 10) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        int i = 0;
      }
    }
  }
  
  public static boolean a(Activity paramActivity, final ThemeInfoModel paramThemeInfoModel, final com.cyou.elegant.a.a<File> paramA)
  {
    if ((Boolean.parseBoolean(paramThemeInfoModel.o)) && (c(paramActivity, "com.android.vending"))) {
      try
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramThemeInfoModel.p));
        localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
        localIntent.setFlags(268435456);
        paramActivity.startActivity(localIntent);
        return true;
      }
      catch (ActivityNotFoundException localActivityNotFoundException) {}
    }
    a(paramActivity, true, new Runnable()
    {
      public final void run()
      {
        Activity localActivity = c.this;
        ThemeInfoModel localThemeInfoModel = paramThemeInfoModel;
        com.cyou.elegant.a.a localA = paramA;
        localThemeInfoModel.y = 3;
        com.cyou.elegant.data.b.a(localActivity, localThemeInfoModel);
        String str = c.a(localActivity, ".ThemeResources") + File.separator + localThemeInfoModel.r;
        c.a(localThemeInfoModel, str);
        com.cyou.elegant.a.e.b();
        new c.3(localActivity, localThemeInfoModel, str, localA).start();
      }
    });
    return false;
  }
  
  public static boolean a(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      paramString = new File(paramString);
    } while ((paramString.isFile()) || (paramString.getUsableSpace() <= 20971520L));
    return true;
  }
  
  public static File b(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      paramString2 = null;
    }
    String str;
    File localFile;
    do
    {
      return paramString2;
      str = com.e.a.c.a.a(paramString2);
      localFile = a(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString1, str);
      paramString2 = localFile;
    } while (localFile != null);
    return a(g(paramContext), paramString1, str);
  }
  
  private static File b(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      paramString1 = null;
    }
    File localFile;
    do
    {
      return paramString1;
      localFile = new File(paramString1, paramString2 + ".amr");
      paramString1 = localFile;
    } while (localFile.exists());
    if ((localFile.getParentFile() == null) || (!localFile.getParentFile().exists())) {
      return null;
    }
    paramString1 = localFile.getParentFile().listFiles(new FilenameFilter()
    {
      public final boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.contains(c.this);
      }
    });
    if ((paramString1 == null) || (paramString1.length == 0)) {
      return null;
    }
    return paramString1[0];
  }
  
  public static String b(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    paramContext = ((TelephonyManager)localObject).getSimCountryIso();
    if (TextUtils.isEmpty(paramContext)) {
      paramContext = ((TelephonyManager)localObject).getNetworkCountryIso();
    }
    for (;;)
    {
      localObject = paramContext;
      if (TextUtils.isEmpty(paramContext)) {
        localObject = Locale.getDefault().getCountry();
      }
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return "";
      }
      return ((String)localObject).toUpperCase(Locale.getDefault());
    }
  }
  
  private static String b(String paramString)
  {
    return paramString + File.separator + "clauncher.cyou.inc" + File.separator + "elegant";
  }
  
  public static void b(Context paramContext, ThemeInfoModel paramThemeInfoModel)
  {
    paramContext = new File(g(paramContext) + File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + ".ThemeResources" + File.separator + paramThemeInfoModel.r);
    if (paramContext.exists()) {
      com.cyou.elegant.util.b.b(paramContext);
    }
    paramContext = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + ".ThemeResources" + File.separator + paramThemeInfoModel.r);
    if (paramContext.exists()) {
      com.cyou.elegant.util.b.b(paramContext);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 128);
      paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
      paramString.setFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean b()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static float c(Context paramContext)
  {
    if (paramContext == null) {
      return 0.0F;
    }
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static int d(Context paramContext)
  {
    int i;
    if (paramContext == null) {
      i = 0;
    }
    int j;
    do
    {
      return i;
      paramContext = (WindowManager)paramContext.getSystemService("window");
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
      j = localDisplayMetrics.densityDpi;
      i = j;
    } while (j >= 160);
    return 160;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null) {
      try
      {
        paramString.setFlags(67108864);
        paramString.setFlags(268435456);
        paramContext.startActivity(paramString);
        return true;
      }
      catch (ActivityNotFoundException paramContext) {}
    }
    return false;
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static void e(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return;
    }
    if (paramString.startsWith("https://play.google.com/store/apps/details?")) {
      try
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
        localIntent.setFlags(67108864);
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException) {}
    }
    try
    {
      paramContext.startActivity(com.cyou.elegant.util.a.a(paramContext, paramString));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean f(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext != null) {
      return paramContext.isConnected();
    }
    return false;
  }
  
  public static String g(Context paramContext)
  {
    String str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
    ArrayList localArrayList = j.a(paramContext);
    if (localArrayList == null)
    {
      paramContext = null;
      return paramContext;
    }
    int j = localArrayList.size();
    if ((localArrayList.contains(str2)) && (j == 2))
    {
      localArrayList.remove(str2);
      return (String)localArrayList.get(0);
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label97;
      }
      String str1 = (String)localArrayList.get(i);
      paramContext = str1;
      if (!str1.contains(str2)) {
        break;
      }
      i += 1;
    }
    label97:
    return null;
  }
  
  public static Intent h(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.setFlags(268435456);
    return localIntent;
  }
  
  public static ArrayList<String> i(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    a(localArrayList, Environment.getExternalStorageDirectory().getAbsolutePath());
    a(localArrayList, g(paramContext));
    return localArrayList;
  }
  
  public static void j(Context paramContext)
  {
    Object localObject = com.cyou.elegant.c.b.a(paramContext).b();
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return;
    }
    String str1 = i.b(paramContext);
    String str2 = com.cyou.elegant.util.e.a("moboapps20172018" + str1);
    d.a();
    CountryModel localCountryModel = d.b(paramContext);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("&packageName=" + paramContext.getPackageName());
    localStringBuffer.append("&language=" + localCountryModel.b);
    localStringBuffer.append("&country=" + localCountryModel.c);
    localStringBuffer.append("&versionCode=" + e(paramContext));
    localStringBuffer.append("&token=" + str2);
    localStringBuffer.append("&deviceId=" + str1);
    localStringBuffer.append("&appliedThemes=" + (String)localObject);
    localObject = new s(1, "http://api.u-launcher.com/client/v2/stat/userAction/appliedThemes.json?" + localStringBuffer.toString(), null, null);
    d.a().a(paramContext, (s)localObject, -1, false);
  }
}
