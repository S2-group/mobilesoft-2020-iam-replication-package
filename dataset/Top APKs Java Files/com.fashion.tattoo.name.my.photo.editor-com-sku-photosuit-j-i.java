package com.sku.photosuit.j;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.alarmservice.AlarmReceiver;
import com.android.objects.FrameData;
import com.android.objects.ImageData;
import com.google.gson.Gson;
import com.sku.Object.FontModel;
import com.sku.feeds.FeedsActivity;
import com.sku.feeds.ResponseData;
import com.sku.photosuit.MyApplication;
import com.sku.photosuit.aq.a;
import com.sku.photosuit.as.b;
import com.sku.photosuit.ax.c.a;
import com.sku.photosuit.ax.e;
import com.sku.photosuit.ax.e.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class i
{
  public static String a = "Utils";
  public static String b = "reference";
  static NotificationCompat.Builder c;
  private static int d = (int)System.currentTimeMillis();
  private static Date e;
  
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
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
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
    catch (Exception paramString1) {}
    return 0;
  }
  
  public static long a(int paramInt)
  {
    long l1 = c.b;
    try
    {
      Object localObject = Calendar.getInstance();
      Date localDate = ((Calendar)localObject).getTime();
      ((Calendar)localObject).add(6, paramInt);
      ((Calendar)localObject).set(11, a(6, 18));
      ((Calendar)localObject).set(12, a(1, 59));
      ((Calendar)localObject).set(13, 0);
      ((Calendar)localObject).set(14, 0);
      localObject = ((Calendar)localObject).getTime();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS aa", Locale.US);
      localSimpleDateFormat.format(localDate);
      localSimpleDateFormat.format((Date)localObject);
      long l2 = ((Date)localObject).getTime();
      return l2;
    }
    catch (Exception localException) {}
    return l1;
  }
  
  public static long a(Date paramDate1, Date paramDate2)
  {
    long l1;
    try
    {
      l1 = paramDate2.getTime() - paramDate1.getTime();
      Object localObject = System.out;
      StringBuilder localStringBuilder = new StringBuilder("startDate : ");
      localStringBuilder.append(paramDate1);
      ((PrintStream)localObject).println(localStringBuilder.toString());
      paramDate1 = System.out;
      localObject = new StringBuilder("endDate : ");
      ((StringBuilder)localObject).append(paramDate2);
      paramDate1.println(((StringBuilder)localObject).toString());
      paramDate1 = System.out;
      paramDate2 = new StringBuilder("different : ");
      paramDate2.append(l1);
      paramDate1.println(paramDate2.toString());
      long l2 = l1 / 86400000L;
      long l3 = l1 % 86400000L;
      l1 = l3 / 3600000L;
      long l4 = l3 % 3600000L;
      try
      {
        l3 = l4 / 60000L;
        l4 = l4 % 60000L / 1000L;
        System.out.printf("%d days, %d hours, %d minutes, %d seconds%n", new Object[] { Long.valueOf(l2), Long.valueOf(l1), Long.valueOf(l3), Long.valueOf(l4) });
        return l1;
      }
      catch (Exception paramDate1) {}
      paramDate1.printStackTrace();
    }
    catch (Exception paramDate1)
    {
      l1 = 0L;
    }
    return l1;
  }
  
  public static Bitmap a(FrameLayout paramFrameLayout)
  {
    try
    {
      paramFrameLayout.setDrawingCacheEnabled(true);
      paramFrameLayout.buildDrawingCache();
      Bitmap localBitmap = Bitmap.createBitmap(paramFrameLayout.getDrawingCache());
      paramFrameLayout.destroyDrawingCache();
      paramFrameLayout.setDrawingCacheEnabled(false);
      return localBitmap;
    }
    catch (Exception paramFrameLayout)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Bitmap a(ImageView paramImageView)
  {
    try
    {
      paramImageView.setDrawingCacheEnabled(true);
      paramImageView.buildDrawingCache();
      Bitmap localBitmap = Bitmap.createBitmap(paramImageView.getDrawingCache());
      paramImageView.destroyDrawingCache();
      paramImageView.setDrawingCacheEnabled(false);
      return localBitmap;
    }
    catch (Exception paramImageView)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static File a()
  {
    try
    {
      if (l()) {
        localFile = new File(Environment.getExternalStorageDirectory(), "Tattoo Name On My Photo Editor");
      } else {
        localFile = new File(Environment.getRootDirectory(), "Tattoo Name On My Photo Editor");
      }
      if ((!localFile.exists()) && (!localFile.mkdirs())) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localFile.getPath());
      localStringBuilder.append(File.separator);
      localStringBuilder.append(new Date().getTime());
      localStringBuilder.append(".jpg");
      File localFile = new File(localStringBuilder.toString());
      return localFile;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String a(double paramDouble)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(new DecimalFormat("##.##").format(paramDouble));
    localStringBuilder.append(" Km");
    return localStringBuilder.toString();
  }
  
  private static String a(long paramLong)
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
      for (;;) {}
    }
    return "";
  }
  
  public static String a(Activity paramActivity, boolean paramBoolean)
  {
    String str = "";
    Object localObject = str;
    if (l())
    {
      localObject = str;
      if (y(paramActivity))
      {
        localObject = new File(c.a, "Tattoo Name On My Photo Editor");
        new StringBuilder("External Storage: ").append(((File)localObject).getAbsolutePath());
        if (!((File)localObject).exists()) {
          ((File)localObject).mkdirs();
        }
        localObject = new File(c.a, "Tattoo Name On My Photo Editor");
        if (!((File)localObject).exists()) {
          ((File)localObject).mkdirs();
        }
        File localFile = new File(c.a, "Tattoo Name On My Photo Editor");
        if (localFile.exists()) {
          str = localFile.getAbsolutePath();
        }
        localObject = str;
        if (new File(c.a).exists())
        {
          localObject = str;
          if (!localFile.exists())
          {
            localObject = str;
            if (!paramBoolean) {}
          }
        }
      }
    }
    try
    {
      localObject = new AlertDialog.Builder(b(paramActivity));
      ((AlertDialog.Builder)localObject).setMessage(2131624090);
      ((AlertDialog.Builder)localObject).setCancelable(true);
      ((AlertDialog.Builder)localObject).setPositiveButton(2131623991, new i.3(paramActivity));
      ((AlertDialog.Builder)localObject).setNegativeButton(2131623987, new i.4());
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).show();
      int i = Color.parseColor(b(paramActivity, "APP_COLOR_THEME", "#212121"));
      ((AlertDialog)localObject).getButton(-2).setTextColor(i);
      ((AlertDialog)localObject).getButton(-1).setTextColor(i);
      localObject = str;
      return localObject;
    }
    catch (Exception paramActivity) {}
    return str;
  }
  
  public static String a(Context paramContext, Bitmap paramBitmap, String paramString)
  {
    if (y(paramContext))
    {
      paramContext = c.t;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext);
      localStringBuilder.append("Temp/TempImage/");
      paramContext = new File(localStringBuilder.toString());
      paramContext.mkdirs();
      localStringBuilder = new StringBuilder("Image_");
      localStringBuilder.append(paramString);
      localStringBuilder.append(".png");
      paramContext = new File(paramContext, localStringBuilder.toString());
      if (paramContext.exists()) {
        paramContext.delete();
      }
      try
      {
        paramString = new FileOutputStream(paramContext);
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString);
        paramString.flush();
        paramString.close();
      }
      catch (Exception paramBitmap)
      {
        paramBitmap.printStackTrace();
      }
      paramBitmap = new StringBuilder("file://");
      paramBitmap.append(paramContext.getAbsolutePath());
      return paramBitmap.toString();
    }
    return null;
  }
  
  public static String a(Context paramContext, Bitmap paramBitmap, String paramString1, int paramInt, String paramString2)
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Tattoo Name On My Photo Editor");
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
        localObject2 = new File(str, "Tattoo Name On My Photo Editor");
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
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString1);
      ((StringBuilder)localObject2).append(".");
      ((StringBuilder)localObject2).append(paramString2);
      paramString1 = new File((File)localObject1, ((StringBuilder)localObject2).toString());
      try
      {
        if (!paramString1.exists()) {
          paramString1.createNewFile();
        }
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
          paramBitmap = paramString1.toString();
          return paramBitmap;
        }
        catch (Exception paramBitmap)
        {
          f.a(paramContext, "savePicture", paramBitmap);
          return null;
        }
        paramContext = new StringBuilder();
      }
      catch (IOException paramBitmap)
      {
        f.a(paramContext, "savePicture", paramBitmap);
        return null;
      }
    }
    paramContext.append(localObject1);
    paramContext.append(" not success");
    return null;
  }
  
  public static String a(Bitmap paramBitmap)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(c.t);
    ((StringBuilder)localObject1).append("stickers/00_my Work");
    localObject1 = new File(((StringBuilder)localObject1).toString());
    if (!((File)localObject1).exists()) {
      ((File)localObject1).mkdir();
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((File)localObject1).getPath());
    ((StringBuilder)localObject2).append("/cropped_");
    ((StringBuilder)localObject2).append(System.currentTimeMillis() / 1000L);
    ((StringBuilder)localObject2).append(".data");
    localObject1 = ((StringBuilder)localObject2).toString();
    try
    {
      localObject2 = new FileOutputStream((String)localObject1);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject2);
      ((FileOutputStream)localObject2).close();
      return localObject1;
    }
    catch (IOException paramBitmap)
    {
      new StringBuilder("IOException ").append(paramBitmap.getMessage());
      return localObject1;
    }
    catch (FileNotFoundException paramBitmap)
    {
      new StringBuilder("FileNotFoundException ").append(paramBitmap.getMessage());
    }
    return localObject1;
  }
  
  public static String a(Bitmap paramBitmap, int paramInt)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(c.t);
    ((StringBuilder)localObject1).append("Temp/AutoErase");
    localObject1 = new File(((StringBuilder)localObject1).toString());
    if (!((File)localObject1).exists()) {
      ((File)localObject1).mkdir();
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((File)localObject1).getPath());
    ((StringBuilder)localObject2).append("/cropped_");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(".png");
    localObject1 = ((StringBuilder)localObject2).toString();
    try
    {
      localObject2 = new FileOutputStream((String)localObject1);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject2);
      ((FileOutputStream)localObject2).close();
      return localObject1;
    }
    catch (IOException paramBitmap)
    {
      new StringBuilder("IOException ").append(paramBitmap.getMessage());
      return localObject1;
    }
    catch (FileNotFoundException paramBitmap)
    {
      new StringBuilder("FileNotFoundException ").append(paramBitmap.getMessage());
    }
    return localObject1;
  }
  
  public static String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = paramString.toCharArray();
    int m = paramString.length;
    int j = 0;
    int i;
    for (int k = 1; j < m; k = i)
    {
      char c2 = paramString[j];
      char c1;
      if (Character.isSpaceChar(c2))
      {
        i = 1;
        c1 = c2;
      }
      else
      {
        i = k;
        c1 = c2;
        if (k != 0)
        {
          c1 = Character.toTitleCase(c2);
          i = 0;
        }
      }
      localStringBuilder.append(c1);
      j += 1;
    }
    return localStringBuilder.toString();
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
      for (;;) {}
    }
    return "";
  }
  
  public static String a(String paramString, Element paramElement)
  {
    paramString = paramElement.getElementsByTagName(paramString).item(0).getChildNodes().item(0);
    if (paramString != null) {
      return paramString.getNodeValue();
    }
    return " ";
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
    new StringBuilder().append(paramArrayList);
    return paramArrayList;
  }
  
  public static String a(Element paramElement)
  {
    if (paramElement.getElementsByTagName("media:content") != null)
    {
      NodeList localNodeList = paramElement.getElementsByTagName("media:content");
      paramElement = "";
      int i = 0;
      int j = localNodeList.getLength();
      while (i < j)
      {
        paramElement = localNodeList.item(i).getAttributes().getNamedItem("url").getNodeValue();
        i += 1;
      }
      return paramElement;
    }
    return "null";
  }
  
  public static void a(Activity paramActivity)
  {
    a(paramActivity, "Common_Counter", b(paramActivity, "Common_Counter", 0) + 1);
    try
    {
      paramActivity.sendBroadcast(new Intent("android.intent.action.SETCOMMONPREF").putExtra("some_msg", b(paramActivity, "Common_Counter", 0)));
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void a(Activity paramActivity, a paramA)
  {
    paramA.a(b(paramActivity, "User_Agent", ""));
    paramA.b();
    paramA.a();
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    try
    {
      ((MyApplication)paramActivity.getApplicationContext()).showToast(paramActivity, paramString);
      return;
    }
    catch (Exception paramActivity) {}
  }
  
  public static void a(Context paramContext, Intent paramIntent, String paramString)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      new StringBuilder().append(localResolveInfo.activityInfo.packageName.toLowerCase());
      if (localResolveInfo.activityInfo.packageName.toLowerCase().startsWith(paramString))
      {
        paramIntent.setPackage(localResolveInfo.activityInfo.packageName);
        return;
      }
    }
  }
  
  public static void a(Context paramContext, Bitmap paramBitmap, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Object localObject = a;
      StringBuilder localStringBuilder = new StringBuilder("showNotificationbitmap: content :");
      localStringBuilder.append(paramString2);
      Log.e((String)localObject, localStringBuilder.toString());
      localObject = new Intent(paramContext, FeedsActivity.class);
      a(paramContext, "FromSplash", Boolean.valueOf(false));
      a(paramContext, "notification_url", paramString3);
      paramString3 = PendingIntent.getActivity(paramContext, d, (Intent)localObject, 1073741824);
      new StringBuilder("showNotificationbitmap: ---bitmap : ").append(paramBitmap);
      c = new NotificationCompat.Builder(paramContext, "CHANNEL_ID").setSmallIcon(2131231052).setContentTitle(paramString1).setContentText(paramString2).addAction(2131230960, "Read More", paramString3).setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(paramString2))).setAutoCancel(true).setPriority(0).setLargeIcon(paramBitmap);
      if (Build.VERSION.SDK_INT >= 26)
      {
        paramBitmap = new NotificationChannel("CHANNEL_ID", "channel_name", 3);
        paramBitmap.setDescription("channel_description");
        ((NotificationManager)paramContext.getSystemService(NotificationManager.class)).createNotificationChannel(paramBitmap);
        return;
      }
      NotificationManagerCompat.from(paramContext).notify(1555, c.build());
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, ResponseData paramResponseData)
  {
    new i.a(paramContext, paramResponseData).execute(new String[0]);
  }
  
  public static void a(Context paramContext, File paramFile)
  {
    try
    {
      if (paramFile.exists()) {
        paramFile.delete();
      }
      b(paramContext, paramFile);
      return;
    }
    catch (Exception paramContext) {}
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
      paramContext.printStackTrace();
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
      paramContext.printStackTrace();
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
      paramContext.printStackTrace();
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
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, ArrayList<ResponseData> paramArrayList)
  {
    new StringBuilder("SaveresponseList: ----").append(paramArrayList.toString());
    Type localType = new i.6().getType();
    a(paramContext, "SaveResponse", new Gson().a(paramArrayList, localType));
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = (LocationManager)paramContext.getSystemService("location");
    try
    {
      bool1 = paramContext.isProviderEnabled("gps");
    }
    catch (Exception localException)
    {
      boolean bool1;
      for (;;) {}
    }
    bool1 = false;
    try
    {
      bool2 = paramContext.isProviderEnabled("network");
    }
    catch (Exception paramContext)
    {
      boolean bool2;
      for (;;) {}
    }
    bool2 = false;
    return (bool1) || (bool2);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramString != null) {
      try
      {
        int i = paramString.trim().length();
        if (i > 0) {
          return true;
        }
      }
      catch (Exception paramString)
      {
        f.a(paramContext, a, paramString);
      }
    }
    return false;
  }
  
  private static boolean a(File paramFile)
  {
    if (!paramFile.exists()) {
      return true;
    }
    if (!paramFile.isDirectory()) {
      return false;
    }
    String[] arrayOfString = paramFile.list();
    if (arrayOfString != null)
    {
      int i = 0;
      while (i < arrayOfString.length)
      {
        File localFile = new File(paramFile, arrayOfString[i]);
        if (localFile.isDirectory())
        {
          if (!a(localFile)) {
            return false;
          }
        }
        else if (!localFile.delete()) {
          return false;
        }
        i += 1;
      }
    }
    new StringBuilder("Temp Image Dir Deleted :- ").append(paramFile.delete());
    return paramFile.delete();
  }
  
  public static int b(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getInt(paramString, paramInt);
  }
  
  public static long b(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getLong(paramString, 0L);
  }
  
  public static ContextThemeWrapper b(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      return new ContextThemeWrapper(paramActivity, 16973941);
    }
    return new ContextThemeWrapper(paramActivity, 16973837);
  }
  
  /* Error */
  public static com.sku.photosuit.ax.d b(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 878	com/sku/photosuit/ax/d:a	()Lcom/sku/photosuit/ax/d;
    //   3: astore_2
    //   4: aload_2
    //   5: astore_3
    //   6: aload_2
    //   7: ifnull +215 -> 222
    //   10: aload_2
    //   11: getfield 881	com/sku/photosuit/ax/d:b	Lcom/sku/photosuit/ax/e;
    //   14: ifnull +210 -> 224
    //   17: iconst_1
    //   18: istore_1
    //   19: goto +3 -> 22
    //   22: aload_2
    //   23: astore_3
    //   24: iload_1
    //   25: ifne +197 -> 222
    //   28: aload_0
    //   29: iconst_1
    //   30: invokestatic 886	com/sku/photosuit/bg/f:a	(Landroid/content/Context;Z)Ljava/io/File;
    //   33: astore_3
    //   34: new 105	java/lang/StringBuilder
    //   37: dup
    //   38: ldc_w 888
    //   41: invokespecial 110	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: aload_3
    //   45: invokevirtual 274	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   48: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: new 890	com/sku/photosuit/ax/c$a
    //   55: dup
    //   56: invokespecial 891	com/sku/photosuit/ax/c$a:<init>	()V
    //   59: astore 4
    //   61: aload 4
    //   63: iconst_1
    //   64: putfield 895	com/sku/photosuit/ax/c$a:i	Z
    //   67: aload 4
    //   69: iconst_1
    //   70: putfield 898	com/sku/photosuit/ax/c$a:h	Z
    //   73: aload 4
    //   75: iconst_1
    //   76: putfield 901	com/sku/photosuit/ax/c$a:m	Z
    //   79: aload 4
    //   81: getstatic 905	com/sku/photosuit/ay/d:e	I
    //   84: putfield 908	com/sku/photosuit/ax/c$a:j	I
    //   87: aload 4
    //   89: iconst_1
    //   90: putfield 911	com/sku/photosuit/ax/c$a:g	Z
    //   93: aload 4
    //   95: getstatic 917	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   98: invokevirtual 920	com/sku/photosuit/ax/c$a:a	(Landroid/graphics/Bitmap$Config;)Lcom/sku/photosuit/ax/c$a;
    //   101: invokevirtual 923	com/sku/photosuit/ax/c$a:a	()Lcom/sku/photosuit/ax/c;
    //   104: astore 4
    //   106: new 925	com/sku/photosuit/ax/e$a
    //   109: dup
    //   110: aload_0
    //   111: invokespecial 926	com/sku/photosuit/ax/e$a:<init>	(Landroid/content/Context;)V
    //   114: invokevirtual 929	com/sku/photosuit/ax/e$a:a	()Lcom/sku/photosuit/ax/e$a;
    //   117: astore_0
    //   118: aload_0
    //   119: iconst_1
    //   120: putfield 931	com/sku/photosuit/ax/e$a:d	Z
    //   123: aload_0
    //   124: new 933	com/sku/photosuit/as/b
    //   127: dup
    //   128: aload_3
    //   129: invokespecial 934	com/sku/photosuit/as/b:<init>	(Ljava/io/File;)V
    //   132: invokevirtual 937	com/sku/photosuit/ax/e$a:a	(Lcom/sku/photosuit/ar/a;)Lcom/sku/photosuit/ax/e$a;
    //   135: astore_0
    //   136: aload_0
    //   137: aload 4
    //   139: putfield 941	com/sku/photosuit/ax/e$a:f	Lcom/sku/photosuit/ax/c;
    //   142: getstatic 944	com/sku/photosuit/ay/g:b	I
    //   145: istore_1
    //   146: aload_0
    //   147: getfield 947	com/sku/photosuit/ax/e$a:b	Ljava/util/concurrent/Executor;
    //   150: ifnonnull +10 -> 160
    //   153: aload_0
    //   154: getfield 949	com/sku/photosuit/ax/e$a:c	Ljava/util/concurrent/Executor;
    //   157: ifnull +13 -> 170
    //   160: ldc_w 951
    //   163: iconst_0
    //   164: anewarray 4	java/lang/Object
    //   167: invokestatic 956	com/sku/photosuit/bg/d:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   170: aload_0
    //   171: iload_1
    //   172: putfield 957	com/sku/photosuit/ax/e$a:e	I
    //   175: aload_0
    //   176: new 959	com/sku/photosuit/aw/c
    //   179: dup
    //   180: invokespecial 960	com/sku/photosuit/aw/c:<init>	()V
    //   183: invokevirtual 963	com/sku/photosuit/ax/e$a:a	(Lcom/sku/photosuit/av/b;)Lcom/sku/photosuit/ax/e$a;
    //   186: invokevirtual 966	com/sku/photosuit/ax/e$a:b	()Lcom/sku/photosuit/ax/e;
    //   189: astore_3
    //   190: invokestatic 878	com/sku/photosuit/ax/d:a	()Lcom/sku/photosuit/ax/d;
    //   193: astore_0
    //   194: aload_0
    //   195: aload_3
    //   196: invokevirtual 969	com/sku/photosuit/ax/d:a	(Lcom/sku/photosuit/ax/e;)V
    //   199: aload_0
    //   200: areturn
    //   201: astore_2
    //   202: goto +14 -> 216
    //   205: astore_3
    //   206: aload_2
    //   207: astore_0
    //   208: aload_3
    //   209: astore_2
    //   210: goto +6 -> 216
    //   213: astore_2
    //   214: aconst_null
    //   215: astore_0
    //   216: aload_2
    //   217: invokevirtual 151	java/lang/Exception:printStackTrace	()V
    //   220: aload_0
    //   221: astore_3
    //   222: aload_3
    //   223: areturn
    //   224: iconst_0
    //   225: istore_1
    //   226: goto -204 -> 22
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	229	0	paramContext	Context
    //   18	208	1	i	int
    //   3	20	2	localD	com.sku.photosuit.ax.d
    //   201	6	2	localException1	Exception
    //   209	1	2	localObject1	Object
    //   213	4	2	localException2	Exception
    //   5	191	3	localObject2	Object
    //   205	4	3	localException3	Exception
    //   221	2	3	localContext	Context
    //   59	79	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   194	199	201	java/lang/Exception
    //   10	17	205	java/lang/Exception
    //   28	160	205	java/lang/Exception
    //   160	170	205	java/lang/Exception
    //   170	194	205	java/lang/Exception
    //   0	4	213	java/lang/Exception
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getString(paramString1, paramString2);
  }
  
  public static String b(Bitmap paramBitmap)
  {
    Object localObject1 = new File(c.t);
    if (((File)localObject1).exists())
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((File)localObject1).getPath());
      ((StringBuilder)localObject2).append("/cropped.data");
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = new File((String)localObject1);
      if (((File)localObject2).exists()) {
        ((File)localObject2).delete();
      }
      try
      {
        localObject2 = new FileOutputStream((String)localObject1);
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject2);
        ((FileOutputStream)localObject2).close();
        return localObject1;
      }
      catch (IOException paramBitmap)
      {
        new StringBuilder("IOException ").append(paramBitmap.getMessage());
        return localObject1;
      }
      catch (FileNotFoundException paramBitmap)
      {
        new StringBuilder("FileNotFoundException ").append(paramBitmap.getMessage());
        return localObject1;
      }
    }
    return null;
  }
  
  public static String b(ArrayList<ImageData> paramArrayList)
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
    new StringBuilder().append(paramArrayList);
    return paramArrayList;
  }
  
  public static ArrayList<ImageData> b()
  {
    ArrayList localArrayList = new ArrayList();
    if (l())
    {
      Object localObject1 = new File(c.a, "Tattoo Name On My Photo Editor");
      if (((File)localObject1).isDirectory())
      {
        localObject1 = ((File)localObject1).listFiles();
        int i = 0;
        while (i < localObject1.length)
        {
          ImageData localImageData = new ImageData();
          if (localObject1[i].getAbsolutePath().contains("Tattoo Name On My Photo Editor"))
          {
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(localObject1[i].getName());
            localObject2 = ((StringBuilder)localObject2).toString();
            if (g((String)localObject2) == 2)
            {
              Object localObject3 = ((String)localObject2).split("_", 3);
              localObject2 = localObject3[1];
              localObject3 = localObject3[2].replace(".jpg", "");
              if ((localObject2 != null) && (localObject3 != null))
              {
                localImageData.id = ((String)localObject2);
                localImageData.file = ((String)localObject3);
                localImageData.sdcardPath = localObject1[i].getAbsolutePath();
                localImageData.lastModified = localObject1[i].lastModified();
                localArrayList.add(localImageData);
              }
            }
          }
          i += 1;
        }
        Collections.sort(localArrayList, new i.1());
      }
    }
    return localArrayList;
  }
  
  public static void b(Context paramContext, File paramFile)
  {
    try
    {
      ((MyApplication)paramContext.getApplicationContext()).refreshdeleteGallery(paramFile);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean b(Context paramContext, String paramString, Boolean paramBoolean)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getBoolean(paramString, paramBoolean.booleanValue());
  }
  
  public static boolean b(String paramString)
  {
    Object localObject = new File(Environment.getExternalStorageDirectory(), "Tattoo Name On My Photo Editor");
    if (((File)localObject).isDirectory())
    {
      localObject = ((File)localObject).listFiles();
      int i = 0;
      while (i < localObject.length)
      {
        if (localObject[i].getAbsolutePath().contains(paramString)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean b(Date paramDate1, Date paramDate2)
  {
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder("compare: date1: ");
    localStringBuilder.append(paramDate1);
    localStringBuilder.append(" date2 :");
    localStringBuilder.append(paramDate2);
    Log.e(str, localStringBuilder.toString());
    if ((paramDate1 != null) && (paramDate2 != null)) {
      try
      {
        boolean bool = paramDate1.before(paramDate2);
        if (bool) {
          return true;
        }
      }
      catch (Exception paramDate1)
      {
        paramDate1.printStackTrace();
      }
    }
    return false;
  }
  
  public static String c(String paramString)
  {
    Object localObject = new File(Environment.getExternalStorageDirectory(), "Tattoo Name On My Photo Editor");
    if (((File)localObject).isDirectory())
    {
      localObject = ((File)localObject).listFiles();
      int i = 0;
      while (i < localObject.length)
      {
        String str = localObject[i].getAbsolutePath();
        if (str.contains(paramString)) {
          return str;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static ArrayList<FrameData> c()
  {
    ArrayList localArrayList = new ArrayList();
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Tattoo Name On My Photo Editor");
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
        localObject2 = new File(str, "Tattoo Name On My Photo Editor");
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
    if ((bool1) && (((File)localObject1).isDirectory()))
    {
      localObject1 = ((File)localObject1).listFiles();
      int i = 0;
      while (i < localObject1.length)
      {
        localObject2 = new FrameData();
        new StringBuilder("path:").append(localObject1[i].getAbsolutePath());
        if ((localObject1[i].getAbsolutePath().contains("Tattoo Name On My Photo Editor")) && (localObject1[i].getName().startsWith("Tattoo Name On My Photo Editor")))
        {
          ((FrameData)localObject2).sdcardPath = localObject1[i].getAbsolutePath();
          ((FrameData)localObject2).lastModified = localObject1[i].lastModified();
          localArrayList.add(localObject2);
        }
        i += 1;
      }
      Collections.sort(localArrayList, new i.2());
    }
    return localArrayList;
  }
  
  public static ArrayList<FontModel> c(ArrayList<FontModel> paramArrayList)
  {
    int j = paramArrayList.size();
    Random localRandom = new Random();
    localRandom.nextInt();
    int i = 0;
    while (i < j)
    {
      int k = localRandom.nextInt(j - i) + i;
      FontModel localFontModel = (FontModel)paramArrayList.get(i);
      paramArrayList.set(i, paramArrayList.get(k));
      paramArrayList.set(k, localFontModel);
      i += 1;
    }
    return paramArrayList;
  }
  
  public static void c(Context paramContext, File paramFile)
  {
    try
    {
      ((MyApplication)paramContext.getApplicationContext()).refreshGallery(paramFile);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean c(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public static boolean c(Context paramContext, String paramString)
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
  
  public static String d()
  {
    try
    {
      Object localObject = new StatFs(Environment.getDataDirectory().getPath());
      long l = ((StatFs)localObject).getBlockSize();
      localObject = a(((StatFs)localObject).getAvailableBlocks() * l);
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static Date d(String paramString)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = localObject3;
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
      localObject1 = localObject3;
      try
      {
        paramString = localSimpleDateFormat.parse(paramString);
      }
      catch (ParseException paramString)
      {
        localObject1 = localObject3;
        paramString.printStackTrace();
        paramString = localObject2;
      }
      localObject1 = paramString;
      new StringBuilder("onCreate: ---current date : ").append(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return localObject1;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      paramContext = new ContextThemeWrapper(paramContext, 16973941);
    } else {
      paramContext = new ContextThemeWrapper(paramContext, 16973837);
    }
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  public static boolean d(Context paramContext)
  {
    return b(paramContext, c.d, Boolean.valueOf(false));
  }
  
  public static String e()
  {
    try
    {
      Object localObject = new StatFs(Environment.getDataDirectory().getPath());
      long l = ((StatFs)localObject).getBlockSize();
      localObject = a(((StatFs)localObject).getBlockCount() * l);
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String e(String paramString)
  {
    Object localObject6 = null;
    Object localObject5 = null;
    Object localObject2 = null;
    Object localObject1;
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
      localObject1 = localObject2;
      try
      {
        paramString = localSimpleDateFormat.parse(paramString);
      }
      catch (Exception localException1)
      {
        paramString = (String)localObject1;
        localObject1 = localSimpleDateFormat;
        break label93;
      }
      catch (ParseException paramString)
      {
        localObject1 = localException1;
        paramString.printStackTrace();
        paramString = localObject6;
      }
      localObject1 = paramString;
      Object localObject3 = new SimpleDateFormat("dd MMM yyyy");
      try
      {
        new StringBuilder("onCreate: ---current date : ").append(paramString);
      }
      catch (Exception localException3)
      {
        localObject1 = localObject3;
        localObject3 = localException3;
      }
      localException2.printStackTrace();
    }
    catch (Exception localException2)
    {
      localObject1 = null;
      paramString = localObject5;
    }
    label93:
    Object localObject4 = localObject1;
    return localObject4.format(paramString);
  }
  
  public static boolean e(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder("IsAdViewDisabled:- ");
    localStringBuilder.append(paramContext);
    localStringBuilder.append(" ");
    localStringBuilder.append(b(paramContext, c.c, Boolean.valueOf(false)));
    return b(paramContext, c.c, Boolean.valueOf(false));
  }
  
  public static String f()
  {
    try
    {
      if (m())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = localStatFs.getBlockSize();
        return a(localStatFs.getAvailableBlocks() * l);
      }
      return "";
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static Document f(String paramString)
  {
    if (paramString.length() == 0) {
      return null;
    }
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    try
    {
      paramString = localDocumentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(paramString)));
      return paramString;
    }
    catch (IOException paramString)
    {
      new StringBuilder("DOC IOException is- ").append(paramString.getMessage());
    }
    catch (SAXException paramString)
    {
      new StringBuilder("DOC SAXException is- ").append(paramString.getMessage());
    }
    catch (ParserConfigurationException paramString)
    {
      new StringBuilder("DOC ParserConfigurationException is- ").append(paramString.getMessage());
    }
    return null;
  }
  
  public static boolean f(Context paramContext)
  {
    String str1 = b(paramContext, "last_user_ad_update_date", "01-01-2016");
    String str2 = a(new Date().getTime(), "dd-MM-yyyy");
    int i = a(a(str1, "dd-MM-yyyy", "yyyy-MM-dd"), a(str2, "dd-MM-yyyy", "yyyy-MM-dd"));
    if (b(paramContext, "show_ad_after_days", 0) == 0) {
      return true;
    }
    return i > b(paramContext, "show_ad_after_days", 0);
  }
  
  public static int g(Context paramContext)
  {
    int i = b(paramContext, "google_ad_ratio", 100);
    if (a(1, b(paramContext, "facebook_ad_ratio", 0) + i) > i) {
      return 2;
    }
    return 1;
  }
  
  private static int g(String paramString)
  {
    int i = 0;
    int k;
    for (int j = 0; i < paramString.length(); j = k)
    {
      k = j;
      if (paramString.charAt(i) == '_') {
        k = j + 1;
      }
      i += 1;
    }
    return j;
  }
  
  public static String g()
  {
    try
    {
      if (m())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = localStatFs.getBlockSize();
        return a(localStatFs.getBlockCount() * l);
      }
      return "";
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static Typeface h(Context paramContext)
  {
    try
    {
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), "fonts/OpenSans-Regular.ttf");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void h()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(c.t);
    localStringBuilder.append("Temp/");
    a(new File(localStringBuilder.toString()));
  }
  
  public static Date i()
  {
    try
    {
      Object localObject1 = Calendar.getInstance().getTime();
      Object localObject2 = System.out;
      StringBuilder localStringBuilder = new StringBuilder("Current time => ");
      localStringBuilder.append(localObject1);
      ((PrintStream)localObject2).println(localStringBuilder.toString());
      localObject2 = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
      localObject1 = ((SimpleDateFormat)localObject2).format((Date)localObject1);
      try
      {
        e = ((SimpleDateFormat)localObject2).parse((String)localObject1);
      }
      catch (ParseException localParseException)
      {
        localParseException.printStackTrace();
      }
      return e;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static boolean i(Context paramContext)
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
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static Typeface j(Context paramContext)
  {
    try
    {
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), "fonts/OpenSans-Regular.ttf");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void j() {}
  
  public static String k()
  {
    return "http://www.trendingtattoo.com/feed/";
  }
  
  public static String k(Context paramContext)
  {
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (str == null) {
        return "No DeviceId";
      }
      paramContext = str;
      if (str.length() > 0) {
        break label36;
      }
      return "No DeviceId";
    }
    catch (Exception paramContext)
    {
      label36:
      for (;;) {}
    }
    paramContext = "No DeviceId";
    return paramContext;
  }
  
  public static int l(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  private static boolean l()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static int m(Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 480;
  }
  
  private static boolean m()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static int n(Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().heightPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 480;
  }
  
  public static void o(Context paramContext)
  {
    a(paramContext, "notification_generate_count", b(paramContext, "notification_generate_count", 0) + 1);
  }
  
  public static void p(Context paramContext)
  {
    int i = b(paramContext, "interstitial_position", 7);
    a(paramContext, "progress_ad_counter", 0);
    a(paramContext, "ad_counter_progress", 0);
    if (i > 3) {
      a(paramContext, "progress_ad_count", a(i - 2, i));
    } else {
      a(paramContext, "progress_ad_count", a(1, i));
    }
    b(paramContext, "progress_ad_count", 0);
  }
  
  public static void q(Context paramContext)
  {
    a(paramContext, "progress_ad_counter", b(paramContext, "progress_ad_counter", 0) + 1);
    a(paramContext, "ad_counter_progress", b(paramContext, "ad_counter_progress", 0) + 1);
  }
  
  public static boolean r(Context paramContext)
  {
    try
    {
      int i = b(paramContext, "progress_ad_counter", 0);
      int k = b(paramContext, "interstitial_position", 7);
      int j = b(paramContext, "ad_counter_progress", 0);
      k = b(paramContext, "progress_ad_count", k);
      if (j == k)
      {
        a(paramContext, "progress_ad_counter", 0);
        return true;
      }
      if (i >= k)
      {
        a(paramContext, "progress_ad_counter", 0);
        return false;
      }
      b(paramContext, "ad_counter_progress", 0);
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void s(Context paramContext)
  {
    a(paramContext, "rotate_ad_counter", b(paramContext, "rotate_ad_counter", 0) + 1);
    a(paramContext, "ad_counter_rotate", b(paramContext, "ad_counter_rotate", 0) + 1);
  }
  
  public static boolean t(Context paramContext)
  {
    try
    {
      int i = b(paramContext, "rotate_ad_counter", 0);
      int j = b(paramContext, "rotate_position", 7);
      int k = b(paramContext, "ad_counter_rotate", 0);
      int m = b(paramContext, "rotate_ad_count", j);
      if (k == j)
      {
        a(paramContext, "rotate_ad_counter", 0);
        a(paramContext, "ad_counter_rotate", 0);
        if (j > 3) {
          a(paramContext, "rotate_ad_count", a(j - 2, j));
        } else {
          a(paramContext, "rotate_ad_count", a(1, j));
        }
        b(paramContext, "rotate_ad_count", 0);
        return true;
      }
      if (i >= j)
      {
        a(paramContext, "rotate_ad_counter", 0);
        a(paramContext, "ad_counter_rotate", 0);
        if (j > 3) {
          a(paramContext, "rotate_ad_count", a(j - 2, j));
        } else {
          a(paramContext, "rotate_ad_count", a(1, j));
        }
        b(paramContext, "rotate_ad_count", 0);
        return false;
      }
      if (b(paramContext, "ad_counter_rotate", 0) >= m)
      {
        a(paramContext, "ad_counter_rotate", 0);
        a(paramContext, "rotate_ad_count", j + 1);
        b(paramContext, "rotate_ad_count", 0);
        return true;
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void u(Context paramContext)
  {
    b(paramContext, "REPEAT_TIME", "N/A");
    try
    {
      new AlarmReceiver().b(paramContext);
    }
    catch (Exception localException)
    {
      new StringBuilder("In Stop Profile ").append(localException.toString());
    }
    try
    {
      new AlarmReceiver().a(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      new StringBuilder("In Stop Profile ").append(paramContext.toString());
    }
  }
  
  public static int v(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      int i = 0;
      while (paramContext.hasNext())
      {
        int k = ((ApplicationInfo)paramContext.next()).flags;
        int j = 1;
        if ((k & 0x1) == 0) {
          j = 0;
        }
        if (j == 0) {
          i += 1;
        }
      }
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static com.sku.photosuit.ax.d w(Context paramContext)
  {
    Object localObject5 = null;
    for (;;)
    {
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(Environment.getExternalStorageDirectory().getAbsolutePath());
        ((StringBuilder)localObject1).append("/Tattoo Name On My Photo Editor");
        localObject1 = ((StringBuilder)localObject1).toString();
        if ((!"mounted".equals(Environment.getExternalStorageState())) || (!com.sku.photosuit.bg.f.a(paramContext))) {
          break label290;
        }
        localObject1 = new File(Environment.getExternalStorageDirectory(), (String)localObject1);
        if (localObject1 != null)
        {
          localObject4 = localObject1;
          if (!((File)localObject1).exists())
          {
            localObject4 = localObject1;
            if (((File)localObject1).mkdirs()) {}
          }
        }
        else
        {
          localObject4 = paramContext.getCacheDir();
        }
        localObject1 = new c.a();
        ((c.a)localObject1).i = true;
        ((c.a)localObject1).h = true;
        ((c.a)localObject1).j = com.sku.photosuit.ay.d.e;
        ((c.a)localObject1).g = false;
        localObject1 = ((c.a)localObject1).a(Bitmap.Config.RGB_565).a();
        e.a localA = new e.a(paramContext);
        localA.f = ((com.sku.photosuit.ax.c)localObject1);
        localObject4 = localA.a(new b((File)localObject4)).a(new com.sku.photosuit.aw.c()).b();
        localObject1 = com.sku.photosuit.ax.d.a();
      }
      catch (Exception localException1)
      {
        Object localObject1;
        Object localObject4;
        Object localObject2 = localObject5;
        continue;
      }
      try
      {
        ((com.sku.photosuit.ax.d)localObject1).a((e)localObject4);
        return localObject1;
      }
      catch (Exception localException3)
      {
        continue;
      }
      try
      {
        localObject4 = new c.a();
        ((c.a)localObject4).i = true;
        ((c.a)localObject4).j = com.sku.photosuit.ay.d.e;
        ((c.a)localObject4).h = true;
        ((c.a)localObject4).g = false;
        localObject4 = ((c.a)localObject4).a(Bitmap.Config.RGB_565).a();
        paramContext = new e.a(paramContext);
        paramContext.f = ((com.sku.photosuit.ax.c)localObject4);
        localObject4 = paramContext.a(new com.sku.photosuit.aw.c()).b();
        paramContext = com.sku.photosuit.ax.d.a();
      }
      catch (Exception paramContext)
      {
        continue;
      }
      try
      {
        paramContext.a((e)localObject4);
        return paramContext;
      }
      catch (Exception localException2)
      {
        return paramContext;
      }
      return localObject1;
      label290:
      Object localObject3 = null;
    }
  }
  
  public static ArrayList<ResponseData> x(Context paramContext)
  {
    paramContext = b(paramContext, "SaveResponse", "");
    new StringBuilder("GetresponseList: response :").append(paramContext.toString());
    Type localType = new i.5().getType();
    return (ArrayList)new Gson().a(paramContext, localType);
  }
  
  private static boolean y(Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
  }
}
