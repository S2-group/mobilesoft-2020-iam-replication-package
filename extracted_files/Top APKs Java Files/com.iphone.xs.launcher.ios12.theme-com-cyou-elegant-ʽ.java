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
import com.cyou.elegant.model.ThemeInfoModel;
import com.cyou.elegant.model.WallPaperUnit;
import com.cyou.elegant.util.ˊ;
import com.cyou.elegant.util.ˋ;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ʽ
{
  private static Bitmap ʻ(File paramFile, int paramInt1, int paramInt2)
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
  
  public static File ʻ(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject;
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null)) {
      localObject = null;
    }
    File localFile;
    do
    {
      return localObject;
      localFile = ʼ(ʻ(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString1), com.ˆ.ʻ.ʽ.ʻ.ʻ(paramString2));
      localObject = localFile;
    } while (localFile != null);
    return ʼ(ʻ(ˈ(paramContext), paramString1), com.ˆ.ʻ.ʽ.ʻ.ʻ(paramString2));
  }
  
  public static File ʻ(String paramString1, String paramString2, String paramString3)
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
  
  public static String ʻ(Activity paramActivity)
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
  
  public static String ʻ(Context paramContext)
  {
    String str = File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + "volley";
    File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str);
    if (ʼ(Environment.getExternalStorageDirectory().getAbsolutePath()))
    {
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      return localFile.getAbsolutePath();
    }
    paramContext = new File(ˈ(paramContext) + str);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static String ʻ(Context paramContext, String paramString)
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (ʼ(str)) {}
    }
    else
    {
      paramContext = ˈ(paramContext);
      if (paramContext != null)
      {
        localObject = paramContext;
        if (ʼ(paramContext)) {}
      }
      else
      {
        return null;
      }
    }
    paramContext = new File(ʻ((String)localObject), paramString);
    if (paramContext.isFile()) {
      paramContext.delete();
    }
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext.getPath();
  }
  
  public static String ʻ(String paramString)
  {
    return paramString + File.separator + "clauncher.cyou.inc" + File.separator + "elegant";
  }
  
  public static String ʻ(String paramString1, String paramString2)
  {
    return ʽ(paramString1) + File.separator + paramString2;
  }
  
  /* Error */
  public static ArrayList<ThemeInfoModel> ʻ(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 10	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokestatic 72	com/cyou/elegant/ʽ:ˈ	(Landroid/content/Context;)Ljava/lang/String;
    //   11: invokestatic 163	com/cyou/elegant/ʽ:ʽ	(Ljava/lang/String;)Ljava/lang/String;
    //   14: ldc -86
    //   16: invokespecial 96	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   19: astore 5
    //   21: aload 5
    //   23: invokevirtual 100	java/io/File:exists	()Z
    //   26: ifne +227 -> 253
    //   29: new 10	java/io/File
    //   32: dup
    //   33: invokestatic 56	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   36: invokevirtual 14	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   39: invokestatic 163	com/cyou/elegant/ʽ:ʽ	(Ljava/lang/String;)Ljava/lang/String;
    //   42: ldc -86
    //   44: invokespecial 96	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   47: astore 5
    //   49: aload 5
    //   51: invokevirtual 100	java/io/File:exists	()Z
    //   54: istore_2
    //   55: iload_2
    //   56: ifne +10 -> 66
    //   59: aconst_null
    //   60: astore_0
    //   61: ldc 2
    //   63: monitorexit
    //   64: aload_0
    //   65: areturn
    //   66: new 172	java/io/ObjectInputStream
    //   69: dup
    //   70: new 174	java/io/FileInputStream
    //   73: dup
    //   74: aload 5
    //   76: invokespecial 177	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   79: invokespecial 180	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   82: astore 4
    //   84: aload 4
    //   86: astore_3
    //   87: aload 4
    //   89: invokevirtual 184	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   92: astore 6
    //   94: aload 6
    //   96: ifnonnull +13 -> 109
    //   99: aload 4
    //   101: invokevirtual 187	java/io/ObjectInputStream:close	()V
    //   104: aconst_null
    //   105: astore_0
    //   106: goto -45 -> 61
    //   109: aload 4
    //   111: astore_3
    //   112: aload 6
    //   114: checkcast 189	java/util/ArrayList
    //   117: astore 6
    //   119: aload 4
    //   121: invokevirtual 187	java/io/ObjectInputStream:close	()V
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
    //   150: invokestatic 191	com/cyou/elegant/ʽ:ʻ	(Landroid/content/Context;Z)Ljava/util/ArrayList;
    //   153: astore_0
    //   154: aload_0
    //   155: astore_3
    //   156: aload_3
    //   157: astore_0
    //   158: aload 4
    //   160: ifnull -99 -> 61
    //   163: aload 4
    //   165: invokevirtual 187	java/io/ObjectInputStream:close	()V
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
    //   184: invokevirtual 100	java/io/File:exists	()Z
    //   187: ifeq +12 -> 199
    //   190: aload 4
    //   192: astore_3
    //   193: aload 5
    //   195: invokevirtual 157	java/io/File:delete	()Z
    //   198: pop
    //   199: aload 4
    //   201: ifnull +8 -> 209
    //   204: aload 4
    //   206: invokevirtual 187	java/io/ObjectInputStream:close	()V
    //   209: aconst_null
    //   210: astore_0
    //   211: goto -150 -> 61
    //   214: astore_0
    //   215: aconst_null
    //   216: astore_3
    //   217: aload_3
    //   218: ifnull +7 -> 225
    //   221: aload_3
    //   222: invokevirtual 187	java/io/ObjectInputStream:close	()V
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
  
  public static JSONArray ʻ(JSONObject paramJSONObject, String paramString1, String paramString2, StringBuffer paramStringBuffer)
  {
    paramJSONObject = paramJSONObject.optJSONObject("data");
    if (paramJSONObject == null)
    {
      ˉ.ʻ().ʼ(paramString1);
      return null;
    }
    String str = paramJSONObject.optString("code");
    paramStringBuffer.append(str);
    if ((str != null) && ("103".equals(str)))
    {
      ˉ.ʻ().ʼ(paramString1);
      return null;
    }
    paramJSONObject = paramJSONObject.optJSONArray(paramString2);
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0))
    {
      ˉ.ʻ().ʼ(paramString1);
      return null;
    }
    return paramJSONObject;
  }
  
  public static void ʻ(Activity paramActivity, ThemeInfoModel paramThemeInfoModel)
  {
    File localFile;
    if (((TextUtils.equals(paramThemeInfoModel.ⁱ, "DIY")) || (TextUtils.equals(paramThemeInfoModel.ⁱ, "OLD"))) && (!TextUtils.isEmpty(paramThemeInfoModel.ﹳ))) {
      localFile = new File(paramThemeInfoModel.ﹳ);
    }
    for (;;)
    {
      Intent localIntent;
      if ((localFile != null) && (localFile.exists()))
      {
        localIntent = ˉ(paramActivity);
        localIntent.putExtra("action", "com.cyou.clauncher.theme.applytheme");
        localIntent.putExtra("ThemePackage", localFile.getAbsolutePath());
        localIntent.putExtra("only_id", paramThemeInfoModel.ᴵ);
        localIntent.putExtra("theme_id", paramThemeInfoModel.ʻ);
        if (!ʽ(paramActivity, paramThemeInfoModel.ᴵ)) {
          break label181;
        }
        paramThemeInfoModel = paramThemeInfoModel.ᴵ;
        localIntent.putExtra("package", paramThemeInfoModel);
      }
      try
      {
        paramActivity.startActivity(localIntent);
        com.cyou.elegant.util.ˆ.ʻ(paramActivity);
        com.cyou.elegant.util.ˆ.ʻ(paramActivity, com.cyou.elegant.util.ˆ.ʽ(paramActivity) + 1);
        paramActivity.setResult(801);
        paramActivity.finish();
        return;
        localFile = ʻ(paramActivity, paramThemeInfoModel.ᴵ, paramThemeInfoModel.ˊ);
        continue;
        label181:
        paramThemeInfoModel = "com.cyou.cma.clauncher.theme.v" + paramThemeInfoModel.ᴵ;
      }
      catch (Exception paramThemeInfoModel)
      {
        for (;;) {}
      }
    }
  }
  
  public static void ʻ(Context paramContext, ThemeInfoModel paramThemeInfoModel)
  {
    com.cyou.elegant.data.ʼ.ʽ(paramContext, paramThemeInfoModel.ᴵ);
    ʼ(paramContext, paramThemeInfoModel);
    ʻ(paramContext, com.cyou.elegant.data.ʼ.ʻ(paramContext, null, null));
  }
  
  public static void ʻ(Context paramContext, WallPaperUnit paramWallPaperUnit)
  {
    if (paramWallPaperUnit.ˏ != null)
    {
      File localFile = new File(paramWallPaperUnit.ˏ);
      if (localFile.exists()) {
        localFile.delete();
      }
    }
    do
    {
      return;
      paramWallPaperUnit = com.ˆ.ʻ.ʽ.ʻ.ʻ(paramWallPaperUnit.ʿ);
      paramContext = ʻ(ˈ(paramContext), ".WallpaperResources", paramWallPaperUnit);
      if ((paramContext != null) && (paramContext.exists())) {
        paramContext.delete();
      }
      paramContext = ʻ(Environment.getExternalStorageDirectory().getAbsolutePath(), ".WallpaperResources", paramWallPaperUnit);
    } while ((paramContext == null) || (!paramContext.exists()));
    paramContext.delete();
  }
  
  public static void ʻ(Context paramContext, File paramFile)
  {
    if (paramFile == null) {
      com.cyou.elegant.util.ˆ.ʻ(paramContext);
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
        localBitmap = ʻ(paramFile, com.cyou.elegant.util.ˉ.ʻ(paramContext), com.cyou.elegant.util.ˉ.ʼ(paramContext));
        if (localBitmap == null)
        {
          localWallpaperManager.setStream(new FileInputStream(paramFile));
          paramFile = paramFile.getName();
          int i = paramFile.indexOf(".");
          if (i == -1) {
            break;
          }
          com.cyou.elegant.util.ˆ.ʻ(paramContext, paramFile.substring(0, i));
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      localWallpaperManager.suggestDesiredDimensions(com.cyou.elegant.util.ˉ.ʻ(paramContext), com.cyou.elegant.util.ˉ.ʼ(paramContext));
      localWallpaperManager.setStream(ˊ.ʻ(localBitmap));
    }
    com.cyou.elegant.util.ˆ.ʻ(paramContext, paramFile);
  }
  
  public static void ʻ(Context paramContext, ArrayList<ThemeInfoModel> paramArrayList)
  {
    ʻ(paramArrayList, new File(ʻ(paramContext, ".ThemeResources"), "theme.bp"));
  }
  
  public static void ʻ(Context paramContext, List<com.cyou.elegant.model.ʻ> paramList)
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
        com.cyou.elegant.model.ʻ localʻ = (com.cyou.elegant.model.ʻ)paramList.next();
        Iterator localIterator = paramContext.iterator();
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (!TextUtils.equals(localʻ.ʼ, localApplicationInfo.packageName)) {
            break;
          }
          localʻ.ـ = true;
        }
      }
    }
  }
  
  public static void ʻ(ThemeInfoModel paramThemeInfoModel, String paramString)
  {
    new ˆ(paramString, paramThemeInfoModel).start();
  }
  
  /* Error */
  public static void ʻ(ArrayList<ThemeInfoModel> paramArrayList, File paramFile)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnull +12 -> 16
    //   7: aload_0
    //   8: invokevirtual 442	java/util/ArrayList:isEmpty	()Z
    //   11: istore_2
    //   12: iload_2
    //   13: ifeq +7 -> 20
    //   16: ldc 2
    //   18: monitorexit
    //   19: return
    //   20: new 444	com/cyou/elegant/ˈ
    //   23: dup
    //   24: aload_1
    //   25: aload_0
    //   26: invokespecial 447	com/cyou/elegant/ˈ:<init>	(Ljava/io/File;Ljava/util/ArrayList;)V
    //   29: invokevirtual 450	java/lang/Thread:start	()V
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
  
  private static void ʻ(ArrayList<String> paramArrayList, String paramString)
  {
    paramString = new File(ʻ(paramString) + File.separator + ".WallpaperResources").listFiles();
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
  
  public static boolean ʻ()
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
  
  public static boolean ʻ(Context paramContext, ThemeInfoModel paramThemeInfoModel, com.cyou.elegant.ʻ.ʻ<File> paramʻ)
  {
    if ((Boolean.parseBoolean(paramThemeInfoModel.ـ)) && (ʽ(paramContext, "com.android.vending"))) {
      try
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramThemeInfoModel.ٴ));
        localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (ActivityNotFoundException localActivityNotFoundException) {}
    }
    paramThemeInfoModel.ﹶ = 3;
    com.cyou.elegant.data.ʼ.ʻ(paramContext, paramThemeInfoModel);
    String str = ʻ(paramContext, ".ThemeResources") + File.separator + paramThemeInfoModel.ᴵ;
    ʻ(paramThemeInfoModel, str);
    com.cyou.elegant.ʻ.ʿ.ʼ();
    new ʿ(paramContext, paramThemeInfoModel, str, paramʻ).start();
    return false;
  }
  
  public static File ʼ(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      paramString2 = null;
    }
    String str;
    File localFile;
    do
    {
      return paramString2;
      str = com.ˆ.ʻ.ʽ.ʻ.ʻ(paramString2);
      localFile = ʻ(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString1, str);
      paramString2 = localFile;
    } while (localFile != null);
    return ʻ(ˈ(paramContext), paramString1, str);
  }
  
  private static File ʼ(String paramString1, String paramString2)
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
    paramString1 = localFile.getParentFile().listFiles(new ʾ(paramString2));
    if ((paramString1 == null) || (paramString1.length == 0)) {
      return null;
    }
    return paramString1[0];
  }
  
  public static String ʼ(Context paramContext)
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
  
  public static void ʼ(Context paramContext, ThemeInfoModel paramThemeInfoModel)
  {
    paramContext = new File(ˈ(paramContext) + File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + ".ThemeResources" + File.separator + paramThemeInfoModel.ᴵ);
    if (paramContext.exists()) {
      com.cyou.elegant.util.ʼ.ʼ(paramContext);
    }
    paramContext = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + ".ThemeResources" + File.separator + paramThemeInfoModel.ᴵ);
    if (paramContext.exists()) {
      com.cyou.elegant.util.ʼ.ʼ(paramContext);
    }
  }
  
  public static void ʼ(Context paramContext, String paramString)
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
  
  public static boolean ʼ()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean ʼ(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      paramString = new File(paramString);
    } while ((paramString.isFile()) || (paramString.getUsableSpace() <= 20971520L));
    return true;
  }
  
  public static float ʽ(Context paramContext)
  {
    if (paramContext == null) {
      return 0.0F;
    }
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }
  
  private static String ʽ(String paramString)
  {
    return ʻ(paramString) + File.separator + ".ThemeResources";
  }
  
  public static boolean ʽ(Context paramContext, String paramString)
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
  
  public static int ʾ(Context paramContext)
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
  
  public static boolean ʾ(Context paramContext, String paramString)
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
  
  public static int ʿ(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static void ʿ(Context paramContext, String paramString)
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
      paramContext.startActivity(com.cyou.elegant.util.ʻ.ʻ(paramContext, paramString));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean ˆ(Context paramContext)
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
  
  public static String ˈ(Context paramContext)
  {
    String str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
    ArrayList localArrayList = ˋ.ʻ(paramContext);
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
  
  public static Intent ˉ(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.setFlags(268435456);
    return localIntent;
  }
  
  public static ArrayList<String> ˊ(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    ʻ(localArrayList, Environment.getExternalStorageDirectory().getAbsolutePath());
    ʻ(localArrayList, ˈ(paramContext));
    return localArrayList;
  }
}
