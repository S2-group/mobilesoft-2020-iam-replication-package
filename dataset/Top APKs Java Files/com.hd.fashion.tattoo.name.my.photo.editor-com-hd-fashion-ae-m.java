package com.hd.fashion.ae;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.FrameLayout;
import com.android.alarmservice.AlarmReceiver;
import com.android.objects.ImageData;
import com.hd.fashion.MyApplication;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class m
{
  public static String a = "Utils";
  
  public static int a(int paramInt1, int paramInt2)
  {
    int i = Math.round((float)(paramInt1 + Math.random() * (paramInt2 - paramInt1 + 1)));
    paramInt1 = i;
    if (i >= paramInt2) {
      paramInt1 = paramInt2;
    }
    return paramInt1;
  }
  
  public static int a(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    try
    {
      paramString1 = localSimpleDateFormat.parse(paramString1);
      paramString2 = localSimpleDateFormat.parse(paramString2);
      if (paramString1 != null)
      {
        if (paramString2 == null) {
          return 0;
        }
        long l = (paramString2.getTime() - paramString1.getTime()) / 86400000L;
        return (int)l;
      }
      return 0;
    }
    catch (Exception paramString1)
    {
      g.a(paramString1);
    }
    return 0;
  }
  
  public static long a(int paramInt)
  {
    long l1 = d.a;
    try
    {
      Object localObject1 = a;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("freq:");
      ((StringBuilder)localObject2).append(paramInt);
      g.a((String)localObject1, ((StringBuilder)localObject2).toString());
      localObject1 = Calendar.getInstance();
      localObject2 = ((Calendar)localObject1).getTime();
      ((Calendar)localObject1).add(6, a(paramInt, paramInt + 2));
      ((Calendar)localObject1).set(11, a(6, 21));
      ((Calendar)localObject1).set(12, a(1, 59));
      ((Calendar)localObject1).set(13, 0);
      ((Calendar)localObject1).set(14, 0);
      localObject1 = ((Calendar)localObject1).getTime();
      Object localObject3 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS aa", Locale.getDefault());
      localObject2 = ((SimpleDateFormat)localObject3).format((Date)localObject2);
      localObject3 = ((SimpleDateFormat)localObject3).format((Date)localObject1);
      g.a(a, (String)localObject2);
      g.a(a, (String)localObject3);
      long l2 = ((Date)localObject1).getTime();
      return l2;
    }
    catch (Exception localException)
    {
      g.a(localException);
    }
    return l1;
  }
  
  public static Bitmap a(FrameLayout paramFrameLayout)
  {
    try
    {
      paramFrameLayout.buildDrawingCache();
      Bitmap localBitmap = Bitmap.createBitmap(paramFrameLayout.getDrawingCache());
      paramFrameLayout.destroyDrawingCache();
      return localBitmap;
    }
    catch (Exception paramFrameLayout)
    {
      g.b(paramFrameLayout);
    }
    return null;
  }
  
  public static ContextThemeWrapper a(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      return new ContextThemeWrapper(paramActivity, 16973941);
    }
    return new ContextThemeWrapper(paramActivity, 16973837);
  }
  
  public static String a(long paramLong)
  {
    String str = null;
    long l = paramLong;
    if (paramLong >= 1024L) {
      str = "KB";
    }
    try
    {
      paramLong /= 1024L;
      l = paramLong;
      if (paramLong >= 1024L)
      {
        str = "MB";
        l = paramLong / 1024L;
      }
      StringBuilder localStringBuilder = new StringBuilder(Long.toString(l));
      int i = localStringBuilder.length() - 3;
      while (i > 0)
      {
        localStringBuilder.insert(i, ',');
        i -= 3;
      }
      if (str != null) {
        localStringBuilder.append(str);
      }
      str = localStringBuilder.toString();
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    g.a(str);
    return "";
  }
  
  public static String a(long paramLong, String paramString)
  {
    paramString = new SimpleDateFormat(paramString, Locale.getDefault());
    try
    {
      paramString = paramString.format(new Date(paramLong));
      return paramString;
    }
    catch (Exception paramString)
    {
      g.a(paramString);
    }
    return "";
  }
  
  public static String a(Activity paramActivity, ImageData paramImageData)
  {
    for (;;)
    {
      try
      {
        paramActivity = new StringBuilder();
        paramActivity.append("Tattoo Maker App_");
        paramActivity.append(paramImageData.id);
        paramActivity.append("_");
        paramActivity.append(paramImageData.name);
        paramActivity.append(".jpg");
        String str1 = paramActivity.toString();
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        paramImageData = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Creative Photo Maker Apps");
        boolean bool3 = paramImageData.exists();
        boolean bool2 = true;
        paramActivity = paramImageData;
        boolean bool1 = bool2;
        if (!bool3)
        {
          bool1 = paramImageData.mkdirs();
          if (bool1) {
            break label237;
          }
          paramActivity = new StringBuilder();
          paramActivity.append(paramImageData);
          paramActivity.append(" not success");
          g.a("savePicture", paramActivity.toString());
          paramImageData = new File(str2, "Creative Photo Maker Apps");
          paramActivity = paramImageData;
          bool1 = bool2;
          if (!paramImageData.exists())
          {
            bool1 = paramImageData.mkdirs();
            paramActivity = paramImageData;
          }
        }
        if ((bool1) && (paramActivity.isDirectory()))
        {
          paramActivity = paramActivity.listFiles();
          int j = paramActivity.length;
          int i = 0;
          if (i < j)
          {
            paramImageData = paramActivity[i].getAbsolutePath();
            bool1 = paramImageData.contains(str1);
            if (bool1) {
              return paramImageData;
            }
            i += 1;
            continue;
          }
        }
        return null;
      }
      catch (Exception paramActivity)
      {
        g.a(paramActivity);
      }
      label237:
      paramActivity = paramImageData;
    }
  }
  
  public static String a(Activity paramActivity, boolean paramBoolean)
  {
    g.a(a, "canReadWritePermission");
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Creative Photo Maker Apps");
    Object localObject1 = localObject3;
    if (!((File)localObject3).exists())
    {
      bool = ((File)localObject3).mkdirs();
      if (!bool)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localObject3);
        ((StringBuilder)localObject1).append(" not success");
        g.a("savePicture", ((StringBuilder)localObject1).toString());
        localObject3 = new File(str, "Creative Photo Maker Apps");
        localObject1 = localObject3;
        if (!((File)localObject3).exists())
        {
          bool = ((File)localObject3).mkdirs();
          localObject1 = localObject3;
          break label129;
        }
      }
      else
      {
        localObject1 = localObject3;
        break label129;
      }
    }
    boolean bool = true;
    label129:
    if (bool)
    {
      paramActivity = new StringBuilder();
      paramActivity.append(localObject1);
      paramActivity.append(" is success");
      g.a("savePicture", paramActivity.toString());
      paramActivity = ((File)localObject1).getAbsolutePath();
      localObject1 = a;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("External Storage dirPath: ");
      ((StringBuilder)localObject3).append(paramActivity);
      g.a((String)localObject1, ((StringBuilder)localObject3).toString());
      return paramActivity;
    }
    if (paramBoolean)
    {
      try
      {
        com.hd.fashion.ad.a.a(paramActivity, "Google Play Store", "Sd Card Scanner", paramActivity.getString(2131623989));
      }
      catch (Exception localException)
      {
        g.a(localException);
      }
      try
      {
        Object localObject2 = new AlertDialog.Builder(a(paramActivity));
        ((AlertDialog.Builder)localObject2).setMessage(2131624103);
        ((AlertDialog.Builder)localObject2).setCancelable(true);
        ((AlertDialog.Builder)localObject2).setPositiveButton(2131623989, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (paramAnonymousDialogInterface != null) {}
            try
            {
              paramAnonymousDialogInterface.dismiss();
              paramAnonymousDialogInterface = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
              paramAnonymousDialogInterface.setData(Uri.fromParts("package", this.a.getPackageName(), null));
              this.a.startActivityForResult(paramAnonymousDialogInterface, d.e);
              return;
            }
            catch (Exception paramAnonymousDialogInterface)
            {
              for (;;) {}
            }
            g.a(this.a, "CreateDir", paramAnonymousDialogInterface);
          }
        });
        ((AlertDialog.Builder)localObject2).setNegativeButton(2131623975, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (paramAnonymousDialogInterface != null) {
              try
              {
                paramAnonymousDialogInterface.dismiss();
                return;
              }
              catch (Exception paramAnonymousDialogInterface)
              {
                g.a(paramAnonymousDialogInterface);
              }
            }
          }
        });
        localObject2 = ((AlertDialog.Builder)localObject2).create();
        ((AlertDialog)localObject2).show();
        ((AlertDialog)localObject2).getButton(-1).setTextColor(paramActivity.getResources().getColor(2131099684));
        ((AlertDialog)localObject2).getButton(-2).setTextColor(paramActivity.getResources().getColor(2131099801));
        return "";
      }
      catch (Exception paramActivity)
      {
        g.a(paramActivity);
      }
    }
    return "";
  }
  
  public static String a(Context paramContext, Bitmap paramBitmap, String paramString1, int paramInt, String paramString2)
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Creative Photo Maker Apps");
    boolean bool3 = ((File)localObject2).exists();
    boolean bool2 = true;
    Object localObject1 = localObject2;
    boolean bool1 = bool2;
    if (!bool3)
    {
      bool1 = ((File)localObject2).mkdirs();
      if (!bool1)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localObject2);
        ((StringBuilder)localObject1).append(" not success");
        g.a("savePicture", ((StringBuilder)localObject1).toString());
        localObject2 = new File(str, "Creative Photo Maker Apps");
        localObject1 = localObject2;
        bool1 = bool2;
        if (!((File)localObject2).exists())
        {
          bool1 = ((File)localObject2).mkdirs();
          localObject1 = localObject2;
        }
      }
      else
      {
        localObject1 = localObject2;
      }
    }
    if (bool1)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(localObject1);
      ((StringBuilder)localObject2).append(" is success");
      g.a("savePicture", ((StringBuilder)localObject2).toString());
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString1);
      ((StringBuilder)localObject2).append(".");
      ((StringBuilder)localObject2).append(paramString2);
      paramString1 = new File((File)localObject1, ((StringBuilder)localObject2).toString());
      try
      {
        paramString1.createNewFile();
        localObject1 = new FileOutputStream(paramString1);
        try
        {
          if (paramString2.equalsIgnoreCase("png")) {
            paramBitmap.compress(Bitmap.CompressFormat.PNG, paramInt, (OutputStream)localObject1);
          } else if (paramString2.equalsIgnoreCase("jpg")) {
            paramBitmap.compress(Bitmap.CompressFormat.JPEG, paramInt, (OutputStream)localObject1);
          } else {
            paramBitmap.compress(Bitmap.CompressFormat.PNG, paramInt, (OutputStream)localObject1);
          }
          ((FileOutputStream)localObject1).close();
          c(paramContext, paramString1);
          paramBitmap = new StringBuilder();
          paramBitmap.append("file:");
          paramBitmap.append(paramString1.getAbsolutePath());
          g.a("savePicture", paramBitmap.toString());
          paramBitmap = paramString1.getAbsolutePath();
          return paramBitmap;
        }
        catch (Exception paramBitmap)
        {
          g.a("savePicture", "cannot save picture");
          g.a(paramContext, "savePicture", paramBitmap);
          return null;
        }
        paramContext = new StringBuilder();
      }
      catch (IOException paramBitmap)
      {
        g.a("savePicture", "cannot save picture");
        g.a(paramContext, "savePicture", paramBitmap);
        return null;
      }
    }
    paramContext.append(localObject1);
    paramContext.append(" not success");
    g.a("savePicture", paramContext.toString());
    return null;
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    paramString2 = new SimpleDateFormat(paramString2, Locale.getDefault());
    try
    {
      paramString1 = paramString2.parse(paramString1);
      paramString1 = new SimpleDateFormat(paramString3, Locale.getDefault()).format(paramString1);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      g.a(paramString1);
    }
    return "";
  }
  
  public static String a(ArrayList<ImageData> paramArrayList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      ImageData localImageData = (ImageData)paramArrayList.next();
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(localImageData.id.trim());
    }
    paramArrayList = localStringBuilder.toString().replace(" ", "");
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(paramArrayList);
    g.a("", localStringBuilder.toString());
    return paramArrayList;
  }
  
  public static ArrayList<String> a()
  {
    ArrayList localArrayList = new ArrayList();
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Creative Photo Maker Apps");
    boolean bool3 = localFile.exists();
    boolean bool2 = true;
    Object localObject = localFile;
    boolean bool1 = bool2;
    if (!bool3)
    {
      bool1 = localFile.mkdirs();
      if (!bool1)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(localFile);
        ((StringBuilder)localObject).append(" not success");
        g.a("savePicture", ((StringBuilder)localObject).toString());
        localFile = new File(str, "Creative Photo Maker Apps");
        localObject = localFile;
        bool1 = bool2;
        if (!localFile.exists())
        {
          bool1 = localFile.mkdirs();
          localObject = localFile;
        }
      }
      else
      {
        localObject = localFile;
      }
    }
    if ((bool1) && (((File)localObject).isDirectory()))
    {
      localObject = ((File)localObject).listFiles();
      int i = 0;
      while (i < localObject.length)
      {
        if ((localObject[i].getAbsolutePath().contains("Tattoo Maker App")) && (localObject[i].getName().startsWith("Tattoo Maker App"))) {
          localArrayList.add(localObject[i].getAbsolutePath());
        }
        i += 1;
      }
    }
    Collections.reverse(localArrayList);
    return localArrayList;
  }
  
  public static void a(Activity paramActivity, com.hd.fashion.cd.a paramA, boolean paramBoolean)
  {
    try
    {
      com.hd.fashion.aa.a localA = new com.hd.fashion.aa.a();
      paramA.a(b(paramActivity, "USER_AGENT", ""));
      paramA.a(60000);
      paramA.a(true);
      if ((paramBoolean) && (b(paramActivity, "token", "").length() > 0)) {
        paramA.a(localA.k(), b(paramActivity, "token", ""));
      }
      paramA.a(localA.m(), localA.n());
      paramA.a(localA.j(), paramActivity.getPackageName());
      paramA.a(localA.o(), b(paramActivity));
      paramA.a(localA.l(), "1");
      return;
    }
    catch (Exception paramActivity)
    {
      g.a(paramActivity);
    }
  }
  
  public static void a(Context paramContext, Intent paramIntent, String paramString)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(localResolveInfo.activityInfo.packageName.toLowerCase());
      g.a("packageName:", localStringBuilder.toString());
      if (localResolveInfo.activityInfo.packageName.toLowerCase().startsWith(paramString))
      {
        paramIntent.setPackage(localResolveInfo.activityInfo.packageName);
        return;
      }
    }
  }
  
  public static void a(Context paramContext, File paramFile)
  {
    try
    {
      if (paramFile.exists())
      {
        g.a("removeFromDownload", "exists");
        if (paramFile.delete()) {
          g.a("removeFromDownload", " deleted from download folder");
        }
      }
      else
      {
        g.a("removeFromDownload", " file not exist to delete from download folder");
      }
      b(paramContext, paramFile);
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static void a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0).edit();
      paramContext.putInt(paramString, paramInt);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static void a(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0).edit();
      paramContext.putLong(paramString, paramLong);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static void a(Context paramContext, String paramString, Boolean paramBoolean)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0).edit();
      paramContext.putBoolean(paramString, paramBoolean.booleanValue());
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0).edit();
      paramContext.putString(paramString1, paramString2);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static void a(boolean paramBoolean, Context paramContext)
  {
    String str1 = b(paramContext, "REPEAT_TIME", "N/A");
    String str2 = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("notification service previous repeated ");
    localStringBuilder.append(str1);
    g.a(str2, localStringBuilder.toString());
    if (paramBoolean)
    {
      if (b(paramContext, "send_push", Boolean.valueOf(true)))
      {
        g(paramContext);
        return;
      }
      i(paramContext);
      return;
    }
    i(paramContext);
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localObject != null)
      {
        paramContext = ((ConnectivityManager)localObject).getNetworkInfo(0);
        localObject = ((ConnectivityManager)localObject).getNetworkInfo(1);
        if ((paramContext == null) || (!paramContext.isConnectedOrConnecting()))
        {
          if (localObject != null)
          {
            boolean bool = ((NetworkInfo)localObject).isConnectedOrConnecting();
            if (!bool) {}
          }
        }
        else {
          return true;
        }
        return false;
      }
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return new File(paramString).exists();
  }
  
  private static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  public static int b(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getInt(paramString, paramInt);
  }
  
  public static long b(Context paramContext, String paramString, long paramLong)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getLong(paramString, paramLong);
  }
  
  public static long b(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString1.length() != 0) && (paramString2 != null) && (paramString2.length() != 0)) {
      try
      {
        long l1 = Long.parseLong(paramString1);
        long l2 = Long.parseLong(paramString2);
        return l2 - l1;
      }
      catch (Exception paramString1)
      {
        g.a(paramString1);
      }
    }
    return 0L;
  }
  
  public static Bitmap b(int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(320, 320, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(paramInt);
    float f = 'ŀ';
    localCanvas.drawRect(0.0F, 0.0F, f, f, localPaint);
    return localBitmap;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (str == null) {
        return "No DeviceId";
      }
      paramContext = str;
      if (str.length() <= 0) {
        return "No DeviceId";
      }
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
      paramContext = "No DeviceId";
    }
    return paramContext;
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getString(paramString1, paramString2);
  }
  
  public static void b(Context paramContext, File paramFile)
  {
    try
    {
      ((MyApplication)paramContext.getApplicationContext()).a(paramFile);
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static boolean b()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean b(Activity paramActivity, ImageData paramImageData)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Tattoo Maker App_");
      localStringBuilder.append(paramImageData.id);
      localStringBuilder.append("_");
      localStringBuilder.append(paramImageData.name);
      localStringBuilder.append(".jpg");
      paramImageData = localStringBuilder.toString();
      paramActivity = new File(a(paramActivity, false), paramImageData);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("photoname:");
      localStringBuilder.append(paramImageData);
      g.a("isPhotoExistInSDCard", localStringBuilder.toString());
      paramImageData = new StringBuilder();
      paramImageData.append("file:");
      paramImageData.append(paramActivity.getAbsolutePath());
      g.a("isPhotoExistInSDCard", paramImageData.toString());
      if (paramActivity.exists())
      {
        paramImageData = new StringBuilder();
        paramImageData.append("file exists:");
        paramImageData.append(paramActivity.getAbsolutePath());
        g.a("isPhotoExistInSDCard", paramImageData.toString());
        return true;
      }
    }
    catch (Exception paramActivity)
    {
      g.a(paramActivity);
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString, Boolean paramBoolean)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getBoolean(paramString, paramBoolean.booleanValue());
  }
  
  public static int c(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
    return 0;
  }
  
  public static String c()
  {
    try
    {
      Object localObject = new StatFs(Environment.getDataDirectory().getPath());
      long l = ((StatFs)localObject).getBlockSize();
      localObject = a(((StatFs)localObject).getAvailableBlocks() * l);
      return localObject;
    }
    catch (Exception localException)
    {
      g.a(localException);
    }
    return "";
  }
  
  public static void c(Context paramContext, File paramFile)
  {
    try
    {
      ((MyApplication)paramContext.getApplicationContext()).b(paramFile);
      return;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
    return 480;
  }
  
  public static String d()
  {
    try
    {
      Object localObject = new StatFs(Environment.getDataDirectory().getPath());
      long l = ((StatFs)localObject).getBlockSize();
      localObject = a(((StatFs)localObject).getBlockCount() * l);
      return localObject;
    }
    catch (Exception localException)
    {
      g.a(localException);
    }
    return "";
  }
  
  public static Typeface e(Context paramContext)
  {
    try
    {
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), "shruti.ttf");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
    return null;
  }
  
  public static String e()
  {
    try
    {
      if (b())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = localStatFs.getBlockSize();
        return a(localStatFs.getAvailableBlocks() * l);
      }
      return "";
    }
    catch (Exception localException)
    {
      g.a(localException);
    }
    return "";
  }
  
  public static String f()
  {
    try
    {
      if (b())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = localStatFs.getBlockSize();
        return a(localStatFs.getBlockCount() * l);
      }
      return "";
    }
    catch (Exception localException)
    {
      g.a(localException);
    }
    return "";
  }
  
  public static void f(Context paramContext)
  {
    a(paramContext, "notification_generate_count", b(paramContext, "notification_generate_count", 0) + 1);
  }
  
  public static File g()
  {
    for (;;)
    {
      File localFile;
      try
      {
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("JPEG_");
        ((StringBuilder)localObject1).append(new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()));
        ((StringBuilder)localObject1).append("_");
        String str1 = ((StringBuilder)localObject1).toString();
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Creative Photo Maker Apps");
        boolean bool3 = localFile.exists();
        boolean bool2 = true;
        localObject1 = localFile;
        boolean bool1 = bool2;
        if (!bool3)
        {
          bool1 = localFile.mkdirs();
          if (bool1) {
            break label200;
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(localFile);
          ((StringBuilder)localObject1).append(" not success");
          g.a("savePicture", ((StringBuilder)localObject1).toString());
          localFile = new File(str2, "Creative Photo Maker Apps");
          localObject1 = localFile;
          bool1 = bool2;
          if (!localFile.exists())
          {
            bool1 = localFile.mkdirs();
            localObject1 = localFile;
          }
        }
        if (bool1)
        {
          localObject1 = File.createTempFile(str1, ".jpg", (File)localObject1);
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        g.b(localException);
      }
      return null;
      label200:
      Object localObject2 = localFile;
    }
  }
  
  public static void g(Context paramContext)
  {
    i(paramContext);
    h(paramContext);
  }
  
  public static void h(Context paramContext)
  {
    try
    {
      new AlarmReceiver().a(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      String str = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("In Stop Profile ");
      localStringBuilder.append(paramContext.toString());
      g.a(str, localStringBuilder.toString());
    }
  }
  
  public static void i(Context paramContext)
  {
    try
    {
      new AlarmReceiver().b(paramContext);
      g.a(a, "after stoping : Alarm is not active");
      return;
    }
    catch (Exception paramContext)
    {
      String str = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("In Stop Profile ");
      localStringBuilder.append(paramContext.toString());
      g.a(str, localStringBuilder.toString());
    }
  }
  
  public static int j(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      int i = 0;
      while (paramContext.hasNext())
      {
        boolean bool = a((ApplicationInfo)paramContext.next());
        if (!bool) {
          i += 1;
        }
      }
      return i;
    }
    catch (Exception paramContext)
    {
      g.a(paramContext);
    }
    return 0;
  }
}
