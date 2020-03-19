package com.setup.configurator;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.gson.e;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;

public class x
{
  public static String a = f.b;
  public static String b = f.a;
  private static String c = "org.xbmc.kodi";
  
  public static r a(ArrayList<r> paramArrayList)
  {
    return (r)paramArrayList.get(new Random().nextInt(paramArrayList.size()));
  }
  
  public static File a(InputStream paramInputStream)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(i.h);
      ((StringBuilder)localObject).append("/media");
      ((StringBuilder)localObject).append("/splash.png");
      localObject = new File(((StringBuilder)localObject).toString());
      FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      localFileOutputStream.close();
      paramInputStream.close();
      return localObject;
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
    return null;
  }
  
  public static String a()
  {
    Object localObject = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = ((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getAvailableBlocks() / 1048576L;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Available MB : ");
    ((StringBuilder)localObject).append(l);
    Log.e("memory", ((StringBuilder)localObject).toString());
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(l);
      ((StringBuilder)localObject).append("");
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(l);
      localStringBuilder.append("");
      return localStringBuilder.toString();
    }
  }
  
  static String a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("APP", 0).getString(paramString, "na");
  }
  
  public static void a(Context paramContext, String paramString, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).edit();
    paramContext.putInt(paramString, paramInt);
    paramContext.apply();
  }
  
  static void a(Context paramContext, String paramString, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).edit();
    paramContext.putLong(paramString, paramLong);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, String paramString, u paramU)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).edit();
    paramContext.putString(paramString, new e().b(paramU));
    paramContext.commit();
  }
  
  static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.apply();
  }
  
  static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).edit();
    paramContext.putBoolean(paramString, paramBoolean);
    paramContext.apply();
  }
  
  public static void a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(i.h);
    localStringBuilder.append(paramString);
    paramString = new File(localStringBuilder.toString());
    if (!paramString.isDirectory()) {
      paramString.mkdirs();
    }
  }
  
  public static void a(final String paramString, Context paramContext)
  {
    a("/media");
    new AsyncTask()
    {
      boolean a = true;
      
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        x.e(this.b);
        try
        {
          new File(paramString);
          paramAnonymousArrayOfObject = new StringBuilder();
          paramAnonymousArrayOfObject.append(i.h);
          paramAnonymousArrayOfObject.append("/media");
          paramAnonymousArrayOfObject.append("/splash.png");
          paramAnonymousArrayOfObject = new FileOutputStream(paramAnonymousArrayOfObject.toString());
          BitmapFactory.decodeFile(paramString).compress(Bitmap.CompressFormat.PNG, 100, paramAnonymousArrayOfObject);
          paramAnonymousArrayOfObject.flush();
          paramAnonymousArrayOfObject.close();
        }
        catch (Exception paramAnonymousArrayOfObject)
        {
          paramAnonymousArrayOfObject.printStackTrace();
          this.a = false;
        }
        return null;
      }
      
      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        if (this.a)
        {
          Toast.makeText(this.b, "Boot Screen Change Successfully", 1).show();
          return;
        }
        Toast.makeText(this.b, "Error in changing Boot Screen", 1).show();
      }
    }.execute(new Object[] { null, null, null });
  }
  
  public static boolean a(int paramInt)
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l2;
    long l1;
    if (Build.VERSION.SDK_INT >= 18)
    {
      l2 = localStatFs.getBlockSizeLong();
      l1 = localStatFs.getAvailableBlocksLong();
    }
    else
    {
      l2 = localStatFs.getBlockSize();
      l1 = localStatFs.getAvailableBlocks();
    }
    return paramInt * 1024L * 1024L < l1 * l2;
  }
  
  public static boolean a(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f1 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    float f2 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    return Math.sqrt(f2 * f2 + f1 * f1) >= 7.0D;
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equalsIgnoreCase(c)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(File paramFile)
  {
    if (paramFile.isDirectory()) {
      try
      {
        File[] arrayOfFile = paramFile.listFiles();
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          if (localFile.exists()) {
            a(localFile);
          } else {
            Log.e("deletion_child", "Deletion failed");
          }
          i += 1;
        }
        return paramFile.delete();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public static boolean a(String paramString, ArrayList<String> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (paramString.equalsIgnoreCase((String)paramArrayList.next())) {
        return true;
      }
    }
    return false;
  }
  
  static long b(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("APP", 0).getLong(paramString, 0L);
  }
  
  public static String b(ArrayList<String> paramArrayList)
  {
    return (String)paramArrayList.get(new Random().nextInt(paramArrayList.size()));
  }
  
  public static ArrayList<String> b(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).getString("build_path", "na");
    if (paramContext.equals("na")) {
      return new ArrayList();
    }
    return new ArrayList(Arrays.asList(paramContext.split(",")));
  }
  
  public static void b()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          FileUtils.deleteDirectory(new File(i.n));
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
        try
        {
          FileUtils.deleteDirectory(new File(i.o));
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
        try
        {
          FileUtils.deleteDirectory(new File(i.p));
          return;
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
        }
      }
    }).start();
  }
  
  static int c(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("APP", 0).getInt(paramString, 0);
  }
  
  public static ArrayList<String> c(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).getString("update_files", "na");
    if (paramContext.equals("na")) {
      return new ArrayList();
    }
    return new ArrayList(Arrays.asList(paramContext.split(",")));
  }
  
  public static ArrayList<String> d(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).getString("update_files_names", "na");
    if (paramContext.equals("na")) {
      return new ArrayList();
    }
    return new ArrayList(Arrays.asList(paramContext.split(",")));
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void e(Context paramContext)
  {
    paramContext = paramContext.getAssets();
    try
    {
      paramContext = paramContext.open("splash.png");
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    paramContext = a(paramContext);
    if ((paramContext != null) && (paramContext.exists())) {
      Log.d("File", "File created successfully!");
    }
  }
  
  static boolean e(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("APP", 0).getBoolean(paramString, false);
  }
  
  public static u f(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).getString(paramString, "");
    try
    {
      paramContext = (u)new e().a(paramContext, u.class);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String f(Context paramContext)
  {
    Object localObject1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    try
    {
      Object localObject2;
      if ((!((String)localObject1).equals("na")) && (!((String)localObject1).equals("")))
      {
        localObject2 = localObject1;
        if (!((String)localObject1).equals("null")) {
          return localObject2;
        }
      }
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("This should be mac_address : ");
        ((StringBuilder)localObject1).append(paramContext);
        Log.d("Device Id", ((StringBuilder)localObject1).toString());
        return paramContext;
      }
      catch (Exception localException1)
      {
        localObject2 = localException1;
      }
      localException2.printStackTrace();
    }
    catch (Exception localException2)
    {
      paramContext = localException1;
    }
    Context localContext = paramContext;
    return localContext;
  }
  
  public static boolean g(Context paramContext)
  {
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.isConnected()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("APP", 0).getString(paramString, "");
    try
    {
      boolean bool = ((u)new e().a(paramContext, u.class)).e();
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getPackageInfo(c, 0);
      paramContext = ((PackageInfo)localObject).versionName;
      int i = ((PackageInfo)localObject).versionCode;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(paramContext);
      ((StringBuilder)localObject).append(" - ");
      ((StringBuilder)localObject).append(i);
      Log.d("Kodi Version", ((StringBuilder)localObject).toString());
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void h(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, "No application can handle this request. Please install a web browser.", 1).show();
      paramString.printStackTrace();
    }
  }
}
