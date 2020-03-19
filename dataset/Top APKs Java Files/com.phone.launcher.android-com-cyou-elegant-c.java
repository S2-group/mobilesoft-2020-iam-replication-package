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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
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
import com.cyou.elegant.model.ThemeInfoModel;
import com.cyou.elegant.model.ThemeInfoModel.Preview;
import com.cyou.elegant.model.WallPaperUnit;
import com.cyou.elegant.util.g;
import com.cyou.elegant.util.h;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c
{
  private static Bitmap a(File paramFile, int paramInt)
    throws Exception
  {
    if (paramInt >= 320) {
      return null;
    }
    BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
    localOptions2.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(new FileInputStream(paramFile), null, localOptions2);
    int i = localOptions2.outWidth;
    localOptions2.inJustDecodeBounds = false;
    BitmapFactory.Options localOptions1;
    if (i > 1000)
    {
      localOptions2.inDensity = 320;
      localOptions2.inTargetDensity = paramInt;
      if (localOptions2.inDensity != 320) {
        break label154;
      }
      if (paramInt != 240) {
        break label130;
      }
      localOptions2.inTargetDensity = 213;
      localOptions1 = localOptions2;
    }
    for (;;)
    {
      paramFile = new FileInputStream(paramFile);
      try
      {
        paramFile = BitmapFactory.decodeStream(paramFile, null, localOptions1);
        return paramFile;
      }
      catch (Error paramFile)
      {
        paramFile.printStackTrace();
      }
      localOptions2.inDensity = 240;
      break;
      label130:
      localOptions1 = localOptions2;
      if (paramInt == 160)
      {
        localOptions2.inTargetDensity = 142;
        localOptions1 = localOptions2;
        continue;
        label154:
        localOptions1 = localOptions2;
        if (localOptions2.inDensity == 240)
        {
          localOptions1 = localOptions2;
          if (paramInt == 160) {
            localOptions1 = null;
          }
        }
      }
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
    if (b(Environment.getExternalStorageDirectory().getAbsolutePath()))
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
      if (b(str)) {}
    }
    else
    {
      paramContext = g(paramContext);
      if (paramContext != null)
      {
        localObject = paramContext;
        if (b(paramContext)) {}
      }
      else
      {
        return null;
      }
    }
    paramContext = new File(a((String)localObject), paramString);
    if (paramContext.isFile()) {
      paramContext.delete();
    }
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext.getPath();
  }
  
  public static String a(String paramString)
  {
    return paramString + File.separator + "clauncher.cyou.inc" + File.separator + "elegant";
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return c(paramString1) + File.separator + paramString2;
  }
  
  /* Error */
  public static ArrayList<ThemeInfoModel> a(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 63	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokestatic 83	com/cyou/elegant/c:g	(Landroid/content/Context;)Ljava/lang/String;
    //   11: invokestatic 174	com/cyou/elegant/c:c	(Ljava/lang/String;)Ljava/lang/String;
    //   14: ldc -77
    //   16: invokespecial 107	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   19: astore 5
    //   21: aload 5
    //   23: invokevirtual 111	java/io/File:exists	()Z
    //   26: ifne +227 -> 253
    //   29: new 63	java/io/File
    //   32: dup
    //   33: invokestatic 61	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   36: invokevirtual 67	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   39: invokestatic 174	com/cyou/elegant/c:c	(Ljava/lang/String;)Ljava/lang/String;
    //   42: ldc -77
    //   44: invokespecial 107	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   47: astore 5
    //   49: aload 5
    //   51: invokevirtual 111	java/io/File:exists	()Z
    //   54: istore_2
    //   55: iload_2
    //   56: ifne +10 -> 66
    //   59: aconst_null
    //   60: astore_0
    //   61: ldc 2
    //   63: monitorexit
    //   64: aload_0
    //   65: areturn
    //   66: new 181	java/io/ObjectInputStream
    //   69: dup
    //   70: new 30	java/io/FileInputStream
    //   73: dup
    //   74: aload 5
    //   76: invokespecial 33	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   79: invokespecial 184	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   82: astore 4
    //   84: aload 4
    //   86: astore_3
    //   87: aload 4
    //   89: invokevirtual 188	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   92: astore 6
    //   94: aload 6
    //   96: ifnonnull +13 -> 109
    //   99: aload 4
    //   101: invokevirtual 191	java/io/ObjectInputStream:close	()V
    //   104: aconst_null
    //   105: astore_0
    //   106: goto -45 -> 61
    //   109: aload 4
    //   111: astore_3
    //   112: aload 6
    //   114: checkcast 193	java/util/ArrayList
    //   117: astore 6
    //   119: aload 4
    //   121: invokevirtual 191	java/io/ObjectInputStream:close	()V
    //   124: aload 6
    //   126: astore_0
    //   127: goto -66 -> 61
    //   130: astore_0
    //   131: aload 6
    //   133: astore_0
    //   134: goto -73 -> 61
    //   137: astore_3
    //   138: aconst_null
    //   139: astore 4
    //   141: iload_1
    //   142: ifeq +37 -> 179
    //   145: aload 4
    //   147: astore_3
    //   148: aload_0
    //   149: iconst_0
    //   150: invokestatic 195	com/cyou/elegant/c:a	(Landroid/content/Context;Z)Ljava/util/ArrayList;
    //   153: astore_0
    //   154: aload_0
    //   155: astore_3
    //   156: aload_3
    //   157: astore_0
    //   158: aload 4
    //   160: ifnull -99 -> 61
    //   163: aload 4
    //   165: invokevirtual 191	java/io/ObjectInputStream:close	()V
    //   168: aload_3
    //   169: astore_0
    //   170: goto -109 -> 61
    //   173: astore_0
    //   174: aload_3
    //   175: astore_0
    //   176: goto -115 -> 61
    //   179: aload 4
    //   181: astore_3
    //   182: aload 5
    //   184: invokevirtual 111	java/io/File:exists	()Z
    //   187: ifeq +12 -> 199
    //   190: aload 4
    //   192: astore_3
    //   193: aload 5
    //   195: invokevirtual 168	java/io/File:delete	()Z
    //   198: pop
    //   199: aload 4
    //   201: ifnull +8 -> 209
    //   204: aload 4
    //   206: invokevirtual 191	java/io/ObjectInputStream:close	()V
    //   209: aconst_null
    //   210: astore_0
    //   211: goto -150 -> 61
    //   214: astore_0
    //   215: aconst_null
    //   216: astore_3
    //   217: aload_3
    //   218: ifnull +7 -> 225
    //   221: aload_3
    //   222: invokevirtual 191	java/io/ObjectInputStream:close	()V
    //   225: aload_0
    //   226: athrow
    //   227: astore_0
    //   228: ldc 2
    //   230: monitorexit
    //   231: aload_0
    //   232: athrow
    //   233: astore_0
    //   234: goto -130 -> 104
    //   237: astore_0
    //   238: goto -29 -> 209
    //   241: astore_3
    //   242: goto -17 -> 225
    //   245: astore_0
    //   246: goto -29 -> 217
    //   249: astore_3
    //   250: goto -109 -> 141
    //   253: goto -204 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	256	0	paramContext	Context
    //   0	256	1	paramBoolean	boolean
    //   54	2	2	bool	boolean
    //   86	26	3	localObjectInputStream1	java.io.ObjectInputStream
    //   137	1	3	localException1	Exception
    //   147	75	3	localObject1	Object
    //   241	1	3	localIOException	java.io.IOException
    //   249	1	3	localException2	Exception
    //   82	123	4	localObjectInputStream2	java.io.ObjectInputStream
    //   19	175	5	localFile	File
    //   92	40	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   119	124	130	java/io/IOException
    //   66	84	137	java/lang/Exception
    //   163	168	173	java/io/IOException
    //   66	84	214	finally
    //   3	49	227	finally
    //   49	55	227	finally
    //   99	104	227	finally
    //   119	124	227	finally
    //   163	168	227	finally
    //   204	209	227	finally
    //   221	225	227	finally
    //   225	227	227	finally
    //   99	104	233	java/io/IOException
    //   204	209	237	java/io/IOException
    //   221	225	241	java/io/IOException
    //   87	94	245	finally
    //   112	119	245	finally
    //   148	154	245	finally
    //   182	190	245	finally
    //   193	199	245	finally
    //   87	94	249	java/lang/Exception
    //   112	119	249	java/lang/Exception
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
          break label182;
        }
        paramThemeInfoModel = paramThemeInfoModel.r;
        localIntent.putExtra("package", paramThemeInfoModel);
      }
      try
      {
        paramActivity.startActivity(localIntent);
        com.cyou.elegant.util.e.a(paramActivity);
        com.cyou.elegant.util.e.a(paramActivity, com.cyou.elegant.util.e.c(paramActivity) + 1);
        paramActivity.setResult(801);
        paramActivity.finish();
        return;
        localFile = a(paramActivity, paramThemeInfoModel.r, paramThemeInfoModel.i);
        continue;
        label182:
        paramThemeInfoModel = "com.cyou.cma.clauncher.theme.v" + paramThemeInfoModel.r;
      }
      catch (Exception paramThemeInfoModel)
      {
        for (;;) {}
      }
    }
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
      com.cyou.elegant.util.e.a(paramContext);
    }
    WallpaperManager localWallpaperManager = WallpaperManager.getInstance(paramContext);
    int i = paramContext.getResources().getDisplayMetrics().densityDpi;
    do
    {
      try
      {
        Object localObject = a(paramFile, i);
        if (localObject == null)
        {
          localWallpaperManager.setStream(new FileInputStream(paramFile));
        }
        else
        {
          localObject = g.a((Bitmap)localObject);
          if (localObject != null) {
            localWallpaperManager.setStream((InputStream)localObject);
          }
        }
      }
      catch (FileNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return;
        paramFile = paramFile.getName();
        i = paramFile.indexOf(".");
        if (i != -1)
        {
          com.cyou.elegant.util.e.a(paramContext, paramFile.substring(0, i));
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      com.cyou.elegant.util.e.a(paramContext, paramFile);
      return;
    } while (paramFile != null);
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
  public static void a(final ArrayList<ThemeInfoModel> paramArrayList, File paramFile)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnull +12 -> 16
    //   7: aload_0
    //   8: invokevirtual 452	java/util/ArrayList:isEmpty	()Z
    //   11: istore_2
    //   12: iload_2
    //   13: ifeq +7 -> 20
    //   16: ldc 2
    //   18: monitorexit
    //   19: return
    //   20: new 12	com/cyou/elegant/c$4
    //   23: dup
    //   24: aload_1
    //   25: aload_0
    //   26: invokespecial 455	com/cyou/elegant/c$4:<init>	(Ljava/io/File;Ljava/util/ArrayList;)V
    //   29: invokevirtual 458	java/lang/Thread:start	()V
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
    paramString = new File(a(paramString) + File.separator + ".WallpaperResources").listFiles();
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
  
  public static boolean a(Context paramContext, final ThemeInfoModel paramThemeInfoModel, final com.cyou.elegant.a.a<File> paramA)
  {
    if ((Boolean.parseBoolean(paramThemeInfoModel.o)) && (c(paramContext, "com.android.vending"))) {
      try
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramThemeInfoModel.p));
        localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (ActivityNotFoundException localActivityNotFoundException) {}
    }
    paramThemeInfoModel.y = 3;
    com.cyou.elegant.data.b.a(paramContext, paramThemeInfoModel);
    final String str = a(paramContext, ".ThemeResources") + File.separator + paramThemeInfoModel.r;
    a(paramThemeInfoModel, str);
    com.cyou.elegant.a.e.b();
    new Thread()
    {
      public final void run()
      {
        com.cyou.elegant.a.e.a("theme", paramThemeInfoModel.i, str, paramA);
      }
    }.start();
    return false;
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
  
  public static boolean b(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      paramString = new File(paramString);
    } while ((paramString.isFile()) || (paramString.getUsableSpace() <= 20971520L));
    return true;
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
  
  private static String c(String paramString)
  {
    return a(paramString) + File.separator + ".ThemeResources";
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
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
    ArrayList localArrayList = h.a(paramContext);
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
}
