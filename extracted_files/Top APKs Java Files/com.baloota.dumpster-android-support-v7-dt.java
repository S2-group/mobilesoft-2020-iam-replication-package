package android.support.v7;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.adience.adboost.AdBoost;
import com.baloota.dumpster.DumpsterApplication;
import com.baloota.dumpster.b;
import com.baloota.dumpster.bean.telize.GeoIp;
import com.baloota.dumpster.billing.Upgrade;
import com.baloota.dumpster.handler.cloud.a.a;
import com.baloota.dumpster.handler.files.e;
import com.baloota.dumpster.service.DumpsterManager;
import com.baloota.dumpster.ui.ContactSupport;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.upalytics.sdk.Upalytics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.Retrofit.Builder;

public class dt
{
  static final String[] a = { "B", "KB", "MB", "GB", "TB" };
  private static final String b = dt.class.getSimpleName();
  private static Boolean c = null;
  private static Boolean d = null;
  private static boolean e = false;
  private static HashMap<String, Integer> f = null;
  private static String g = null;
  private static String h = null;
  
  public dt() {}
  
  public static void A(Context paramContext)
  {
    if (!i(paramContext)) {
      localObject = null;
    }
    try
    {
      MediaPlayer localMediaPlayer = MediaPlayer.create(paramContext, 2131230720);
      paramContext = localMediaPlayer;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        com.baloota.dumpster.logger.a.a(paramContext, localException.getMessage(), localException, false);
        paramContext = localObject;
      }
    }
    if (paramContext != null)
    {
      paramContext.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
      {
        public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
        {
          paramAnonymousMediaPlayer.start();
        }
      });
      paramContext.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
      {
        public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
        {
          paramAnonymousMediaPlayer.release();
        }
      });
    }
  }
  
  @SuppressLint({"NewApi"})
  public static void B(Context paramContext)
  {
    try
    {
      pj.a(paramContext, "Dumpster");
      com.baloota.dumpster.logger.a.b(paramContext, b, "JNI library loaded successfully");
      return;
    }
    catch (Exception localException)
    {
      com.baloota.dumpster.logger.a.a(paramContext, b, "Failed to load JNI library: " + localException, localException);
    }
  }
  
  public static String C(Context paramContext)
  {
    String str2 = d(com.baloota.dumpster.preferences.a.j(paramContext));
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      com.baloota.dumpster.preferences.a.c(paramContext, "date");
      str1 = "deleted_date DESC, display_name ASC";
    }
    return str1;
  }
  
  public static String D(Context paramContext)
  {
    if (TextUtils.isEmpty(h)) {}
    try
    {
      String str = "" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
      h = str + "_" + new StringBuilder().append(Build.BRAND).append(Build.MODEL).toString().replaceAll("[^\\p{Alpha}\\p{Digit}]+", "");
      return h;
    }
    catch (Exception localException)
    {
      com.baloota.dumpster.logger.a.a(paramContext, "Failed to get device id!", localException);
    }
    return null;
  }
  
  public static dy E(Context paramContext)
  {
    dy localDy = dy.a;
    if ((com.baloota.dumpster.preferences.a.I(paramContext)) || (com.baloota.dumpster.preferences.a.M(paramContext))) {
      localDy = dy.b;
    }
    while (!com.baloota.dumpster.preferences.a.H(paramContext)) {
      return localDy;
    }
    return dy.c;
  }
  
  public static void F(Context paramContext)
  {
    if (du.c("dataSdkSWEnabled")) {}
    try
    {
      Upalytics.start(paramContext);
      return;
    }
    catch (Exception localException)
    {
      com.baloota.dumpster.logger.a.a(paramContext, localException.getMessage(), localException);
    }
  }
  
  public static float a(float paramFloat, Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F * paramFloat;
  }
  
  public static int a(long paramLong)
  {
    if (paramLong <= 0L) {
      return 0;
    }
    return (int)(Math.log10(paramLong) / Math.log10(1024.0D));
  }
  
  private static Bitmap a(String paramString, int paramInt)
  {
    int i = 1;
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(paramString), null, localOptions);
      while ((localOptions.outWidth / i / 2 >= paramInt) && (localOptions.outHeight / i / 2 >= paramInt)) {
        i *= 2;
      }
      localOptions = new BitmapFactory.Options();
      localOptions.inSampleSize = i;
      paramString = BitmapFactory.decodeStream(new FileInputStream(paramString), null, localOptions);
      return paramString;
    }
    catch (FileNotFoundException paramString) {}
    return null;
  }
  
  public static Object a(String paramString, Class paramClass)
  {
    try
    {
      paramString = new Gson().a(paramString, paramClass);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String a(long paramLong, int paramInt)
  {
    if (paramLong < 0L) {
      return "";
    }
    if (paramLong == 0L) {
      return "0 " + a[0];
    }
    return new DecimalFormat("#,##0.00").format(paramLong / Math.pow(1024.0D, paramInt)) + " " + a[paramInt];
  }
  
  /* Error */
  public static String a(Context paramContext, File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 299	java/io/FileInputStream
    //   6: dup
    //   7: aload_1
    //   8: invokespecial 345	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   11: astore_2
    //   12: aload_2
    //   13: astore_1
    //   14: aload_2
    //   15: invokestatic 348	android/support/v7/dt:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   18: astore_3
    //   19: aload_3
    //   20: astore_0
    //   21: aload_0
    //   22: astore_1
    //   23: aload_2
    //   24: ifnull +9 -> 33
    //   27: aload_2
    //   28: invokevirtual 351	java/io/FileInputStream:close	()V
    //   31: aload_0
    //   32: astore_1
    //   33: aload_1
    //   34: areturn
    //   35: astore_3
    //   36: aconst_null
    //   37: astore_2
    //   38: aload_2
    //   39: astore_1
    //   40: aload_0
    //   41: aload_3
    //   42: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   45: aload_3
    //   46: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   49: aload 4
    //   51: astore_1
    //   52: aload_2
    //   53: ifnull -20 -> 33
    //   56: aload_2
    //   57: invokevirtual 351	java/io/FileInputStream:close	()V
    //   60: aconst_null
    //   61: areturn
    //   62: astore_0
    //   63: aconst_null
    //   64: areturn
    //   65: astore_0
    //   66: aconst_null
    //   67: astore_1
    //   68: aload_1
    //   69: ifnull +7 -> 76
    //   72: aload_1
    //   73: invokevirtual 351	java/io/FileInputStream:close	()V
    //   76: aload_0
    //   77: athrow
    //   78: astore_1
    //   79: aload_0
    //   80: areturn
    //   81: astore_1
    //   82: goto -6 -> 76
    //   85: astore_0
    //   86: goto -18 -> 68
    //   89: astore_3
    //   90: goto -52 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramContext	Context
    //   0	93	1	paramFile	File
    //   11	46	2	localFileInputStream	FileInputStream
    //   18	2	3	str	String
    //   35	11	3	localException1	Exception
    //   89	1	3	localException2	Exception
    //   1	49	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	12	35	java/lang/Exception
    //   56	60	62	java/lang/Exception
    //   3	12	65	finally
    //   27	31	78	java/lang/Exception
    //   72	76	81	java/lang/Exception
    //   14	19	85	finally
    //   40	49	85	finally
    //   14	19	89	java/lang/Exception
  }
  
  private static String a(InputStream paramInputStream)
    throws IOException, NoSuchAlgorithmException
  {
    int i = 0;
    char[] arrayOfChar = "0123456789abcdef".toCharArray();
    Object localObject = new byte['က'];
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    for (;;)
    {
      j = paramInputStream.read((byte[])localObject);
      if (j == -1) {
        break;
      }
      localMessageDigest.update((byte[])localObject, 0, j);
    }
    paramInputStream = localMessageDigest.digest();
    localObject = new StringBuilder(32);
    int j = paramInputStream.length;
    while (i < j)
    {
      int k = paramInputStream[i];
      ((StringBuilder)localObject).append(arrayOfChar[(k >> 4 & 0xF)]);
      ((StringBuilder)localObject).append(arrayOfChar[(k & 0xF)]);
      i += 1;
    }
    return ((StringBuilder)localObject).toString();
  }
  
  public static String a(Object paramObject)
  {
    try
    {
      paramObject = new Gson().a(paramObject);
      return paramObject;
    }
    catch (Throwable paramObject) {}
    return null;
  }
  
  public static String a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes());
      paramString = new BigInteger(1, localMessageDigest.digest()).toString(16);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString) {}
    return null;
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      Object localObject2 = Integer.toHexString(paramArrayOfByte[i]);
      int j = ((String)localObject2).length();
      Object localObject1 = localObject2;
      if (j == 1) {
        localObject1 = "0" + (String)localObject2;
      }
      localObject2 = localObject1;
      if (j > 2) {
        localObject2 = ((String)localObject1).substring(j - 2, j);
      }
      localStringBuilder.append(((String)localObject2).toUpperCase());
      if (i < paramArrayOfByte.length - 1) {
        localStringBuilder.append(':');
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String a(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(paramArrayOfString[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void a(Activity paramActivity)
  {
    if (a())
    {
      localObject = paramActivity.getString(2131296653);
      try
      {
        paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)localObject)));
        return;
      }
      catch (Exception localException2)
      {
        com.baloota.dumpster.logger.a.a(paramActivity, "Failed to open china market_url: " + (String)localObject + ": " + localException2);
        return;
      }
    }
    Object localObject = new Intent("android.intent.action.VIEW", Uri.parse(paramActivity.getString(2131296660)));
    try
    {
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    catch (Exception localException1)
    {
      String str = paramActivity.getString(2131296653);
      try
      {
        paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        return;
      }
      catch (Exception localException3)
      {
        com.baloota.dumpster.logger.a.a(paramActivity, "Failed to open market_url: " + str + ": " + localException3);
      }
    }
  }
  
  public static void a(final Activity paramActivity, int paramInt)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        GooglePlayServicesUtil.getErrorDialog(this.a, paramActivity, 0).show();
      }
    });
  }
  
  public static void a(Activity paramActivity, int paramInt, String paramString)
  {
    if (a(paramActivity, true)) {
      paramActivity.startActivityForResult(AccountPicker.newChooseAccountIntent(null, null, new String[] { "com.google" }, false, paramString, null, null, null), paramInt);
    }
  }
  
  public static void a(Activity paramActivity, Class<? extends Activity> paramClass, boolean paramBoolean)
  {
    paramActivity.startActivity(new Intent(paramActivity.getApplicationContext(), paramClass));
    if (paramBoolean) {
      paramActivity.overridePendingTransition(2131034122, 2131034124);
    }
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    for (;;)
    {
      Configuration localConfiguration;
      try
      {
        Resources localResources = paramActivity.getResources();
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        localConfiguration = localResources.getConfiguration();
        if (be.i[0].equals(paramString))
        {
          localConfiguration.locale = Locale.getDefault();
          localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
          return;
        }
        if (be.i[1].equals(paramString))
        {
          localConfiguration.locale = new Locale("en");
          continue;
        }
        if (!be.i[2].equals(paramString)) {
          break label115;
        }
      }
      catch (Exception paramString)
      {
        com.baloota.dumpster.logger.a.a(paramActivity, paramString.getMessage(), paramString);
        return;
      }
      localConfiguration.locale = new Locale("es");
      continue;
      label115:
      if (be.i[3].equals(paramString)) {
        localConfiguration.locale = new Locale("fr");
      } else if (be.i[4].equals(paramString)) {
        localConfiguration.locale = new Locale("de");
      } else if (be.i[5].equals(paramString)) {
        localConfiguration.locale = new Locale("pt");
      } else if (be.i[6].equals(paramString)) {
        localConfiguration.locale = new Locale("ru");
      } else if (be.i[7].equals(paramString)) {
        localConfiguration.locale = new Locale("zh");
      } else if (be.i[8].equals(paramString)) {
        localConfiguration.locale = new Locale("ja");
      } else if (be.i[9].equals(paramString)) {
        localConfiguration.locale = new Locale("ko");
      } else if (be.i[10].equals(paramString)) {
        localConfiguration.locale = new Locale("tr");
      } else if (be.i[11].equals(paramString)) {
        localConfiguration.locale = new Locale("he");
      } else if (be.i[12].equals(paramString)) {
        localConfiguration.locale = new Locale("ar");
      } else {
        localConfiguration.locale = Locale.getDefault();
      }
    }
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean, long paramLong, final String paramString1, String paramString2)
  {
    if (paramBoolean) {}
    try
    {
      dp.a(paramActivity, 2131296625, 2131296623, paramLong, new dp.a()
      {
        public void a(Exception paramAnonymousException)
        {
          ds.a(this.a, 2131296624, 0);
        }
        
        public void a(Long paramAnonymousLong1, Long paramAnonymousLong2, Object paramAnonymousObject) {}
        
        public void a(String paramAnonymousString)
        {
          dt.a(this.a, paramString1, paramAnonymousString);
        }
      });
      return;
    }
    catch (Exception paramString1)
    {
      com.baloota.dumpster.logger.a.a(paramActivity.getApplicationContext(), paramString1.getMessage(), paramString1);
    }
    b(paramActivity, paramString1, paramString2);
    return;
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, ContactSupport.class);
    localIntent.putExtra("BUNDLE_CAUSE", paramInt);
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean a()
  {
    return com.baloota.dumpster.a.b.equals(b.a());
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    try
    {
      int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramActivity);
      if (i != 0)
      {
        if ((GooglePlayServicesUtil.isUserRecoverableError(i)) && (paramBoolean)) {
          a(paramActivity, i);
        }
        return false;
      }
      return true;
    }
    catch (Exception localException)
    {
      com.baloota.dumpster.logger.a.a(paramActivity, localException.getMessage(), localException, false);
    }
    return false;
  }
  
  static boolean a(Activity paramActivity, String[] paramArrayOfString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    Context localContext;
    Object localObject;
    int j;
    int i;
    if (paramArrayOfString != null)
    {
      localContext = paramActivity.getApplicationContext();
      localObject = du.d("vipUsers_prod");
      bool1 = bool2;
      if (localObject != null)
      {
        localObject = Arrays.asList((Object[])localObject);
        bool1 = bool2;
        if (localObject != null)
        {
          j = paramArrayOfString.length;
          i = 0;
        }
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        String str1 = paramArrayOfString[i];
        com.baloota.dumpster.logger.a.d(localContext, "Checking gtm if account " + str1 + " is vip");
        String str2 = c(localContext, str1);
        if ((!TextUtils.isEmpty(str2)) && (((List)localObject).contains(str2)))
        {
          com.baloota.dumpster.logger.a.d(localContext, "account " + str1 + " is vip according to GTM! checking server..");
          b(paramActivity, str1);
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean a(Context paramContext)
  {
    return ((PowerManager)paramContext.getSystemService("power")).isScreenOn();
  }
  
  public static boolean a(Context paramContext, String paramString)
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
  
  /* Error */
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 8
    //   3: iconst_0
    //   4: istore 10
    //   6: iconst_0
    //   7: istore 11
    //   9: iconst_0
    //   10: istore 9
    //   12: aload_1
    //   13: ifnonnull +9 -> 22
    //   16: iconst_0
    //   17: istore 4
    //   19: iload 4
    //   21: ireturn
    //   22: iload 4
    //   24: ifeq +159 -> 183
    //   27: ldc_w 670
    //   30: aload_0
    //   31: invokestatic 672	android/support/v7/dt:a	(FLandroid/content/Context;)F
    //   34: f2i
    //   35: istore 6
    //   37: aload_3
    //   38: ifnull +238 -> 276
    //   41: aload_3
    //   42: ldc_w 674
    //   45: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   48: ifeq +228 -> 276
    //   51: aconst_null
    //   52: astore 12
    //   54: aconst_null
    //   55: astore_3
    //   56: aload_1
    //   57: iload 6
    //   59: invokestatic 679	android/support/v7/dt:a	(Ljava/lang/String;I)Landroid/graphics/Bitmap;
    //   62: astore 13
    //   64: aload_1
    //   65: invokestatic 682	android/support/v7/dt:c	(Ljava/lang/String;)I
    //   68: istore 7
    //   70: aload 13
    //   72: astore_1
    //   73: iload 7
    //   75: ifle +39 -> 114
    //   78: new 684	android/graphics/Matrix
    //   81: dup
    //   82: invokespecial 685	android/graphics/Matrix:<init>	()V
    //   85: astore_1
    //   86: aload_1
    //   87: iload 7
    //   89: i2f
    //   90: invokevirtual 689	android/graphics/Matrix:postRotate	(F)Z
    //   93: pop
    //   94: aload 13
    //   96: iconst_0
    //   97: iconst_0
    //   98: aload 13
    //   100: invokevirtual 694	android/graphics/Bitmap:getWidth	()I
    //   103: aload 13
    //   105: invokevirtual 697	android/graphics/Bitmap:getHeight	()I
    //   108: aload_1
    //   109: iconst_1
    //   110: invokestatic 701	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   113: astore_1
    //   114: aload_1
    //   115: ifnull +934 -> 1049
    //   118: aload_1
    //   119: iload 6
    //   121: iload 6
    //   123: iconst_2
    //   124: invokestatic 707	android/media/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;
    //   127: astore_1
    //   128: new 709	java/io/FileOutputStream
    //   131: dup
    //   132: aload_2
    //   133: invokespecial 710	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   136: astore_2
    //   137: aload_2
    //   138: astore 13
    //   140: aload_1
    //   141: astore_3
    //   142: aload_1
    //   143: getstatic 716	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   146: bipush 90
    //   148: aload_2
    //   149: invokevirtual 720	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   152: pop
    //   153: iconst_1
    //   154: istore 5
    //   156: aload_2
    //   157: ifnull +7 -> 164
    //   160: aload_2
    //   161: invokevirtual 723	java/io/OutputStream:close	()V
    //   164: iload 5
    //   166: istore 4
    //   168: aload_1
    //   169: ifnull +11 -> 180
    //   172: aload_1
    //   173: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   176: iload 5
    //   178: istore 4
    //   180: iload 4
    //   182: ireturn
    //   183: aload_0
    //   184: invokevirtual 267	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   187: invokevirtual 273	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   190: getfield 729	android/util/DisplayMetrics:widthPixels	I
    //   193: ldc_w 730
    //   196: aload_0
    //   197: invokestatic 672	android/support/v7/dt:a	(FLandroid/content/Context;)F
    //   200: f2i
    //   201: isub
    //   202: istore 6
    //   204: goto -167 -> 37
    //   207: astore 12
    //   209: aconst_null
    //   210: astore_2
    //   211: aconst_null
    //   212: astore_1
    //   213: aload_2
    //   214: astore 13
    //   216: aload_1
    //   217: astore_3
    //   218: aload_0
    //   219: aload 12
    //   221: invokevirtual 731	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   224: aload 12
    //   226: iconst_0
    //   227: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   230: aload_2
    //   231: ifnull +7 -> 238
    //   234: aload_2
    //   235: invokevirtual 723	java/io/OutputStream:close	()V
    //   238: aload_1
    //   239: ifnull +804 -> 1043
    //   242: aload_1
    //   243: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   246: iconst_0
    //   247: istore 4
    //   249: goto -69 -> 180
    //   252: astore_0
    //   253: aload 12
    //   255: astore_1
    //   256: aload_3
    //   257: astore_2
    //   258: aload_2
    //   259: ifnull +7 -> 266
    //   262: aload_2
    //   263: invokevirtual 723	java/io/OutputStream:close	()V
    //   266: aload_1
    //   267: ifnull +7 -> 274
    //   270: aload_1
    //   271: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   274: aload_0
    //   275: athrow
    //   276: aload_3
    //   277: ifnull +161 -> 438
    //   280: aload_3
    //   281: ldc_w 733
    //   284: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   287: ifeq +151 -> 438
    //   290: aconst_null
    //   291: astore_3
    //   292: aload_1
    //   293: invokestatic 736	android/support/v7/dt:b	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   296: astore_1
    //   297: aload_1
    //   298: ifnull +734 -> 1032
    //   301: new 709	java/io/FileOutputStream
    //   304: dup
    //   305: aload_2
    //   306: invokespecial 710	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   309: astore_2
    //   310: iload 5
    //   312: ifne +55 -> 367
    //   315: aload_1
    //   316: iload 6
    //   318: iload 6
    //   320: iconst_2
    //   321: invokestatic 707	android/media/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;
    //   324: astore_1
    //   325: aload_2
    //   326: astore_3
    //   327: aload_1
    //   328: astore 13
    //   330: aload_1
    //   331: getstatic 716	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   334: bipush 90
    //   336: aload_2
    //   337: invokevirtual 720	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   340: pop
    //   341: iconst_1
    //   342: istore 5
    //   344: aload_2
    //   345: ifnull +7 -> 352
    //   348: aload_2
    //   349: invokevirtual 723	java/io/OutputStream:close	()V
    //   352: iload 5
    //   354: istore 4
    //   356: aload_1
    //   357: ifnull -338 -> 19
    //   360: aload_1
    //   361: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   364: iload 5
    //   366: ireturn
    //   367: goto -42 -> 325
    //   370: astore 12
    //   372: aconst_null
    //   373: astore_2
    //   374: aconst_null
    //   375: astore_1
    //   376: aload_2
    //   377: astore_3
    //   378: aload_1
    //   379: astore 13
    //   381: aload_0
    //   382: aload 12
    //   384: invokevirtual 731	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   387: aload 12
    //   389: iconst_0
    //   390: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   393: aload_2
    //   394: ifnull +7 -> 401
    //   397: aload_2
    //   398: invokevirtual 723	java/io/OutputStream:close	()V
    //   401: iload 9
    //   403: istore 4
    //   405: aload_1
    //   406: ifnull -387 -> 19
    //   409: aload_1
    //   410: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   413: iconst_0
    //   414: ireturn
    //   415: astore_0
    //   416: aconst_null
    //   417: astore_2
    //   418: aload_3
    //   419: astore_1
    //   420: aload_2
    //   421: ifnull +7 -> 428
    //   424: aload_2
    //   425: invokevirtual 723	java/io/OutputStream:close	()V
    //   428: aload_1
    //   429: ifnull +7 -> 436
    //   432: aload_1
    //   433: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   436: aload_0
    //   437: athrow
    //   438: iload 9
    //   440: istore 4
    //   442: ldc_w 738
    //   445: aload_3
    //   446: invokevirtual 518	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   449: ifeq -430 -> 19
    //   452: iload 11
    //   454: istore 8
    //   456: aload_0
    //   457: invokevirtual 660	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   460: astore_3
    //   461: iload 11
    //   463: istore 8
    //   465: aload_3
    //   466: aload_1
    //   467: iconst_0
    //   468: invokevirtual 741	android/content/pm/PackageManager:getPackageArchiveInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   471: astore 12
    //   473: iload 11
    //   475: istore 8
    //   477: aload 12
    //   479: getfield 747	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   482: aload_1
    //   483: putfield 752	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   486: iload 11
    //   488: istore 8
    //   490: aload 12
    //   492: getfield 747	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   495: aload_1
    //   496: putfield 755	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   499: iload 11
    //   501: istore 8
    //   503: aload 12
    //   505: getfield 747	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   508: aload_3
    //   509: invokevirtual 759	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   512: astore_1
    //   513: iload 9
    //   515: istore 4
    //   517: aload_1
    //   518: ifnull -499 -> 19
    //   521: iload 9
    //   523: istore 4
    //   525: iload 11
    //   527: istore 8
    //   529: aload_1
    //   530: instanceof 761
    //   533: ifeq -514 -> 19
    //   536: iload 11
    //   538: istore 8
    //   540: aload_1
    //   541: checkcast 761	android/graphics/drawable/BitmapDrawable
    //   544: invokevirtual 765	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   547: astore 17
    //   549: iload 9
    //   551: istore 4
    //   553: aload 17
    //   555: ifnull -536 -> 19
    //   558: aconst_null
    //   559: astore_3
    //   560: aconst_null
    //   561: astore 14
    //   563: aconst_null
    //   564: astore 13
    //   566: aconst_null
    //   567: astore 12
    //   569: aconst_null
    //   570: astore 16
    //   572: aconst_null
    //   573: astore 15
    //   575: aload 17
    //   577: iload 6
    //   579: iload 6
    //   581: invokestatic 768	android/media/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
    //   584: astore_1
    //   585: aload_1
    //   586: ifnull +12 -> 598
    //   589: aload_1
    //   590: astore_3
    //   591: aload_1
    //   592: invokevirtual 771	android/graphics/Bitmap:isRecycled	()Z
    //   595: ifeq +434 -> 1029
    //   598: aload_1
    //   599: astore_3
    //   600: aload 17
    //   602: getstatic 777	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   605: iconst_1
    //   606: invokevirtual 781	android/graphics/Bitmap:copy	(Landroid/graphics/Bitmap$Config;Z)Landroid/graphics/Bitmap;
    //   609: astore 17
    //   611: aload 17
    //   613: astore_1
    //   614: aload 16
    //   616: astore 12
    //   618: aload_1
    //   619: astore_3
    //   620: new 709	java/io/FileOutputStream
    //   623: dup
    //   624: aload_2
    //   625: invokespecial 710	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   628: astore_2
    //   629: iload 10
    //   631: istore 5
    //   633: aload_1
    //   634: ifnull +28 -> 662
    //   637: iload 10
    //   639: istore 5
    //   641: aload_1
    //   642: invokevirtual 771	android/graphics/Bitmap:isRecycled	()Z
    //   645: ifne +17 -> 662
    //   648: aload_1
    //   649: getstatic 784	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   652: bipush 100
    //   654: aload_2
    //   655: invokevirtual 720	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   658: pop
    //   659: iconst_1
    //   660: istore 5
    //   662: aload_2
    //   663: ifnull +11 -> 674
    //   666: iload 5
    //   668: istore 8
    //   670: aload_2
    //   671: invokevirtual 723	java/io/OutputStream:close	()V
    //   674: iload 5
    //   676: istore 4
    //   678: aload_1
    //   679: ifnull -660 -> 19
    //   682: iload 5
    //   684: istore 8
    //   686: aload_1
    //   687: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   690: iload 5
    //   692: ireturn
    //   693: astore_1
    //   694: aload_0
    //   695: aload_1
    //   696: invokevirtual 731	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   699: aload_1
    //   700: iconst_0
    //   701: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   704: iload 8
    //   706: ireturn
    //   707: astore_2
    //   708: aload_3
    //   709: astore_1
    //   710: aload 15
    //   712: astore 13
    //   714: aload 13
    //   716: astore 12
    //   718: aload_1
    //   719: astore_3
    //   720: aload_0
    //   721: aload_2
    //   722: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   725: aload_2
    //   726: iconst_0
    //   727: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   730: aload 13
    //   732: ifnull +12 -> 744
    //   735: iload 11
    //   737: istore 8
    //   739: aload 13
    //   741: invokevirtual 723	java/io/OutputStream:close	()V
    //   744: iload 9
    //   746: istore 4
    //   748: aload_1
    //   749: ifnull -730 -> 19
    //   752: iload 11
    //   754: istore 8
    //   756: aload_1
    //   757: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   760: iconst_0
    //   761: ireturn
    //   762: astore_2
    //   763: aconst_null
    //   764: astore_1
    //   765: aload 13
    //   767: astore 12
    //   769: aload_1
    //   770: astore_3
    //   771: aload_0
    //   772: aload_2
    //   773: invokevirtual 731	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   776: aload_2
    //   777: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   780: aload 13
    //   782: ifnull +12 -> 794
    //   785: iload 11
    //   787: istore 8
    //   789: aload 13
    //   791: invokevirtual 723	java/io/OutputStream:close	()V
    //   794: iload 9
    //   796: istore 4
    //   798: aload_1
    //   799: ifnull -780 -> 19
    //   802: iload 11
    //   804: istore 8
    //   806: aload_1
    //   807: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   810: iconst_0
    //   811: ireturn
    //   812: astore_2
    //   813: aconst_null
    //   814: astore_1
    //   815: aload 12
    //   817: ifnull +12 -> 829
    //   820: iload 11
    //   822: istore 8
    //   824: aload 12
    //   826: invokevirtual 723	java/io/OutputStream:close	()V
    //   829: aload_1
    //   830: ifnull +11 -> 841
    //   833: iload 11
    //   835: istore 8
    //   837: aload_1
    //   838: invokevirtual 726	android/graphics/Bitmap:recycle	()V
    //   841: iload 11
    //   843: istore 8
    //   845: aload_2
    //   846: athrow
    //   847: astore_0
    //   848: goto -684 -> 164
    //   851: astore_0
    //   852: goto -614 -> 238
    //   855: astore_2
    //   856: goto -590 -> 266
    //   859: astore_0
    //   860: goto -508 -> 352
    //   863: astore_0
    //   864: goto -463 -> 401
    //   867: astore_2
    //   868: goto -440 -> 428
    //   871: astore_2
    //   872: goto -198 -> 674
    //   875: astore_2
    //   876: goto -132 -> 744
    //   879: astore_2
    //   880: goto -86 -> 794
    //   883: astore_3
    //   884: goto -55 -> 829
    //   887: astore_2
    //   888: goto -73 -> 815
    //   891: astore_2
    //   892: aload_3
    //   893: astore_1
    //   894: goto -79 -> 815
    //   897: astore_3
    //   898: aload_2
    //   899: astore 12
    //   901: aload_3
    //   902: astore_2
    //   903: goto -88 -> 815
    //   906: astore_2
    //   907: goto -142 -> 765
    //   910: astore_2
    //   911: goto -146 -> 765
    //   914: astore_3
    //   915: aload_2
    //   916: astore 13
    //   918: aload_3
    //   919: astore_2
    //   920: goto -155 -> 765
    //   923: astore_2
    //   924: aconst_null
    //   925: astore_1
    //   926: aload 14
    //   928: astore 13
    //   930: goto -216 -> 714
    //   933: astore_2
    //   934: aload 14
    //   936: astore 13
    //   938: goto -224 -> 714
    //   941: astore_2
    //   942: aload 14
    //   944: astore 13
    //   946: goto -232 -> 714
    //   949: astore_3
    //   950: aload_2
    //   951: astore 13
    //   953: aload_3
    //   954: astore_2
    //   955: goto -241 -> 714
    //   958: astore_2
    //   959: aload 15
    //   961: astore 13
    //   963: goto -249 -> 714
    //   966: astore_3
    //   967: aload_2
    //   968: astore 13
    //   970: aload_3
    //   971: astore_2
    //   972: goto -258 -> 714
    //   975: astore_0
    //   976: aload_3
    //   977: astore_1
    //   978: goto -558 -> 420
    //   981: astore_0
    //   982: aload 13
    //   984: astore_1
    //   985: aload_3
    //   986: astore_2
    //   987: goto -567 -> 420
    //   990: astore 12
    //   992: aconst_null
    //   993: astore_1
    //   994: goto -618 -> 376
    //   997: astore 12
    //   999: goto -623 -> 376
    //   1002: astore_0
    //   1003: aload_3
    //   1004: astore_2
    //   1005: goto -747 -> 258
    //   1008: astore_0
    //   1009: aload 13
    //   1011: astore_2
    //   1012: aload_3
    //   1013: astore_1
    //   1014: goto -756 -> 258
    //   1017: astore 12
    //   1019: aconst_null
    //   1020: astore_2
    //   1021: goto -808 -> 213
    //   1024: astore 12
    //   1026: goto -813 -> 213
    //   1029: goto -415 -> 614
    //   1032: aconst_null
    //   1033: astore_2
    //   1034: aconst_null
    //   1035: astore_1
    //   1036: iload 8
    //   1038: istore 5
    //   1040: goto -696 -> 344
    //   1043: iconst_0
    //   1044: istore 4
    //   1046: goto -866 -> 180
    //   1049: aconst_null
    //   1050: astore_1
    //   1051: goto -923 -> 128
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1054	0	paramContext	Context
    //   0	1054	1	paramString1	String
    //   0	1054	2	paramString2	String
    //   0	1054	3	paramString3	String
    //   0	1054	4	paramBoolean1	boolean
    //   0	1054	5	paramBoolean2	boolean
    //   35	545	6	i	int
    //   68	20	7	j	int
    //   1	1036	8	bool1	boolean
    //   10	785	9	bool2	boolean
    //   4	634	10	bool3	boolean
    //   7	835	11	bool4	boolean
    //   52	1	12	localObject1	Object
    //   207	47	12	localThrowable1	Throwable
    //   370	18	12	localThrowable2	Throwable
    //   471	429	12	localObject2	Object
    //   990	1	12	localThrowable3	Throwable
    //   997	1	12	localThrowable4	Throwable
    //   1017	1	12	localThrowable5	Throwable
    //   1024	1	12	localThrowable6	Throwable
    //   62	948	13	localObject3	Object
    //   561	382	14	localObject4	Object
    //   573	387	15	localObject5	Object
    //   570	45	16	localObject6	Object
    //   547	65	17	localBitmap	Bitmap
    // Exception table:
    //   from	to	target	type
    //   56	70	207	java/lang/Throwable
    //   78	114	207	java/lang/Throwable
    //   118	128	207	java/lang/Throwable
    //   56	70	252	finally
    //   78	114	252	finally
    //   118	128	252	finally
    //   292	297	370	java/lang/Throwable
    //   301	310	370	java/lang/Throwable
    //   292	297	415	finally
    //   301	310	415	finally
    //   456	461	693	java/lang/Throwable
    //   465	473	693	java/lang/Throwable
    //   477	486	693	java/lang/Throwable
    //   490	499	693	java/lang/Throwable
    //   503	513	693	java/lang/Throwable
    //   529	536	693	java/lang/Throwable
    //   540	549	693	java/lang/Throwable
    //   670	674	693	java/lang/Throwable
    //   686	690	693	java/lang/Throwable
    //   739	744	693	java/lang/Throwable
    //   756	760	693	java/lang/Throwable
    //   789	794	693	java/lang/Throwable
    //   806	810	693	java/lang/Throwable
    //   824	829	693	java/lang/Throwable
    //   837	841	693	java/lang/Throwable
    //   845	847	693	java/lang/Throwable
    //   575	585	707	java/lang/IllegalStateException
    //   591	598	707	java/lang/IllegalStateException
    //   600	611	707	java/lang/IllegalStateException
    //   575	585	762	java/lang/Throwable
    //   575	585	812	finally
    //   160	164	847	java/io/IOException
    //   234	238	851	java/io/IOException
    //   262	266	855	java/io/IOException
    //   348	352	859	java/io/IOException
    //   397	401	863	java/io/IOException
    //   424	428	867	java/io/IOException
    //   670	674	871	java/io/IOException
    //   739	744	875	java/io/IOException
    //   789	794	879	java/io/IOException
    //   824	829	883	java/io/IOException
    //   591	598	887	finally
    //   600	611	887	finally
    //   620	629	891	finally
    //   720	730	891	finally
    //   771	780	891	finally
    //   641	659	897	finally
    //   591	598	906	java/lang/Throwable
    //   600	611	906	java/lang/Throwable
    //   620	629	910	java/lang/Throwable
    //   641	659	914	java/lang/Throwable
    //   575	585	923	java/io/FileNotFoundException
    //   591	598	933	java/io/FileNotFoundException
    //   600	611	933	java/io/FileNotFoundException
    //   620	629	941	java/io/FileNotFoundException
    //   641	659	949	java/io/FileNotFoundException
    //   620	629	958	java/lang/IllegalStateException
    //   641	659	966	java/lang/IllegalStateException
    //   315	325	975	finally
    //   330	341	981	finally
    //   381	393	981	finally
    //   315	325	990	java/lang/Throwable
    //   330	341	997	java/lang/Throwable
    //   128	137	1002	finally
    //   142	153	1008	finally
    //   218	230	1008	finally
    //   128	137	1017	java/lang/Throwable
    //   142	153	1024	java/lang/Throwable
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean)
  {
    boolean bool = true;
    if ((paramBoolean) && (d != null)) {
      return d.booleanValue();
    }
    if (b(paramContext, false).length > 1) {}
    for (paramBoolean = bool;; paramBoolean = false)
    {
      d = Boolean.valueOf(paramBoolean);
      return d.booleanValue();
    }
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramBoolean1)
    {
      bool1 = bool2;
      if (d(paramContext, paramBoolean2)) {
        bool1 = true;
      }
    }
    paramBoolean1 = bool1;
    if (!bool1) {
      paramBoolean1 = com.baloota.dumpster.preferences.a.H(paramContext);
    }
    return paramBoolean1;
  }
  
  public static PackageInfo b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 1);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  /* Error */
  @TargetApi(10)
  public static Bitmap b(String paramString)
  {
    // Byte code:
    //   0: getstatic 810	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 10
    //   5: if_icmplt +47 -> 52
    //   8: new 812	android/media/MediaMetadataRetriever
    //   11: dup
    //   12: invokespecial 813	android/media/MediaMetadataRetriever:<init>	()V
    //   15: astore_1
    //   16: aload_1
    //   17: aload_0
    //   18: invokevirtual 816	android/media/MediaMetadataRetriever:setDataSource	(Ljava/lang/String;)V
    //   21: aload_1
    //   22: ldc2_w 817
    //   25: invokevirtual 822	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   28: astore_0
    //   29: aload_1
    //   30: invokevirtual 825	android/media/MediaMetadataRetriever:release	()V
    //   33: aload_0
    //   34: areturn
    //   35: astore_0
    //   36: aload_1
    //   37: invokevirtual 825	android/media/MediaMetadataRetriever:release	()V
    //   40: aconst_null
    //   41: areturn
    //   42: astore_0
    //   43: aconst_null
    //   44: areturn
    //   45: astore_0
    //   46: aload_1
    //   47: invokevirtual 825	android/media/MediaMetadataRetriever:release	()V
    //   50: aload_0
    //   51: athrow
    //   52: aload_0
    //   53: iconst_1
    //   54: invokestatic 828	android/media/ThumbnailUtils:createVideoThumbnail	(Ljava/lang/String;I)Landroid/graphics/Bitmap;
    //   57: areturn
    //   58: astore_1
    //   59: aload_0
    //   60: areturn
    //   61: astore_1
    //   62: goto -12 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	paramString	String
    //   15	32	1	localMediaMetadataRetriever	android.media.MediaMetadataRetriever
    //   58	1	1	localRuntimeException1	RuntimeException
    //   61	1	1	localRuntimeException2	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   16	29	35	java/lang/Exception
    //   36	40	42	java/lang/RuntimeException
    //   16	29	45	finally
    //   29	33	58	java/lang/RuntimeException
    //   46	50	61	java/lang/RuntimeException
  }
  
  public static String b(long paramLong)
  {
    if (paramLong < 0L) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramLong == 0L) {
      localStringBuilder.append("0 KB");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if (paramLong < 1024L)
      {
        localStringBuilder.append(NumberFormat.getInstance().format(paramLong));
        localStringBuilder.append(" Bytes");
      }
      else if (paramLong < 1048576L)
      {
        localStringBuilder.append(NumberFormat.getInstance().format(paramLong / 1024L));
        localStringBuilder.append(" KB");
      }
      else if (paramLong < 1073741824L)
      {
        localStringBuilder.append(NumberFormat.getInstance().format(paramLong / 1048576L));
        localStringBuilder.append(" MB");
      }
      else
      {
        localStringBuilder.append(NumberFormat.getInstance().format(paramLong / 1073741824L));
        localStringBuilder.append(" GB");
      }
    }
  }
  
  public static String b(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 128).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  static void b(final Activity paramActivity, final String paramString)
  {
    Context localContext = paramActivity.getApplicationContext();
    com.baloota.dumpster.logger.a.d(localContext, "Checking server if user " + paramString + " is vip");
    com.baloota.dumpster.handler.cloud.a.a(localContext, paramString);
    com.baloota.dumpster.handler.cloud.a.a(localContext, new a.a()
    {
      public void a(at paramAnonymousAt)
      {
        if (paramAnonymousAt.e().booleanValue())
        {
          com.baloota.dumpster.logger.a.d(this.a, "getUserInfo success! user " + paramString + " is vip");
          dt.c(paramActivity);
          ds.a(this.a, 2131296677, 0);
          return;
        }
        com.baloota.dumpster.logger.a.b(this.a, "checked for vip with subscribed account " + paramString + "!");
      }
      
      public void a(Exception paramAnonymousException)
      {
        com.baloota.dumpster.logger.a.d(this.a, "account " + paramString + " is not vip");
        com.baloota.dumpster.handler.cloud.a.a(this.a, "");
        if (!dp.a(paramAnonymousException)) {}
      }
    });
  }
  
  private static void b(Activity paramActivity, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (paramString1 != null)
      {
        int i = paramString1.lastIndexOf('.');
        localObject1 = localObject2;
        if (i > 0)
        {
          paramString1 = paramString1.substring(i + 1);
          localObject1 = localObject2;
          if (paramString1 != null) {
            localObject1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString1.toLowerCase());
          }
        }
      }
      if (localObject1 != null) {
        localIntent.setType((String)localObject1);
      }
      localIntent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + paramString2));
      paramActivity.startActivity(localIntent);
      DumpsterApplication.a(paramActivity);
      return;
    }
    catch (ActivityNotFoundException paramString1)
    {
      Toast.makeText(paramActivity.getApplicationContext(), 2131296534, 1).show();
      com.baloota.dumpster.logger.a.a(paramActivity.getApplicationContext(), paramString1.getMessage(), paramString1, false);
      return;
    }
    catch (Exception paramString1)
    {
      com.baloota.dumpster.logger.a.a(paramActivity.getApplicationContext(), paramString1.getMessage(), paramString1);
    }
  }
  
  public static void b(Context paramContext, File paramFile)
  {
    if ((paramContext != null) && (paramFile != null)) {
      paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(paramFile)));
    }
  }
  
  public static boolean b()
  {
    return com.baloota.dumpster.a.c.equals(b.a());
  }
  
  public static boolean b(Activity paramActivity)
  {
    Context localContext = paramActivity.getApplicationContext();
    if (!d(localContext, true))
    {
      com.baloota.dumpster.logger.a.d(localContext, "Checking if device contains a vip account..");
      String[] arrayOfString = l(localContext);
      if ((arrayOfString != null) && (!el.a(localContext))) {
        return a(paramActivity, arrayOfString);
      }
    }
    else
    {
      com.baloota.dumpster.logger.a.d(localContext, "Skipping vip check because device is already premium");
      return true;
    }
    return false;
  }
  
  /* Error */
  @SuppressLint({"NewApi"})
  public static File[] b(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: new 951	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 952	java/util/ArrayList:<init>	()V
    //   10: astore 9
    //   12: getstatic 810	android/os/Build$VERSION:SDK_INT	I
    //   15: bipush 19
    //   17: if_icmplt +151 -> 168
    //   20: aload_0
    //   21: aconst_null
    //   22: invokevirtual 956	android/content/Context:getExternalFilesDirs	(Ljava/lang/String;)[Ljava/io/File;
    //   25: astore 6
    //   27: aload 6
    //   29: ifnull +145 -> 174
    //   32: aload 6
    //   34: arraylength
    //   35: istore_3
    //   36: iconst_0
    //   37: istore_2
    //   38: iload_2
    //   39: iload_3
    //   40: if_icmpge +390 -> 430
    //   43: aload 6
    //   45: iload_2
    //   46: aaload
    //   47: astore_0
    //   48: aload_0
    //   49: ifnull +59 -> 108
    //   52: aload_0
    //   53: invokevirtual 961	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   56: ldc_w 963
    //   59: invokevirtual 966	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   62: istore 5
    //   64: iload 5
    //   66: ifle +42 -> 108
    //   69: new 958	java/io/File
    //   72: dup
    //   73: aload_0
    //   74: invokevirtual 961	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   77: iconst_0
    //   78: iload 5
    //   80: invokevirtual 423	java/lang/String:substring	(II)Ljava/lang/String;
    //   83: invokespecial 967	java/io/File:<init>	(Ljava/lang/String;)V
    //   86: astore_0
    //   87: aload_0
    //   88: invokevirtual 970	java/io/File:exists	()Z
    //   91: ifeq +17 -> 108
    //   94: aload_0
    //   95: invokevirtual 973	java/io/File:isDirectory	()Z
    //   98: ifeq +10 -> 108
    //   101: aload 9
    //   103: aload_0
    //   104: invokevirtual 976	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   107: pop
    //   108: iload_2
    //   109: iconst_1
    //   110: iadd
    //   111: istore_2
    //   112: goto -74 -> 38
    //   115: astore 6
    //   117: aload_0
    //   118: aload 6
    //   120: invokevirtual 977	java/lang/NullPointerException:getMessage	()Ljava/lang/String;
    //   123: aload 6
    //   125: iconst_0
    //   126: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   129: aconst_null
    //   130: astore 6
    //   132: goto -105 -> 27
    //   135: astore 6
    //   137: aload_0
    //   138: aload 6
    //   140: invokevirtual 978	java/lang/SecurityException:getMessage	()Ljava/lang/String;
    //   143: aload 6
    //   145: iconst_0
    //   146: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   149: aconst_null
    //   150: astore 6
    //   152: goto -125 -> 27
    //   155: astore 6
    //   157: aload_0
    //   158: aload 6
    //   160: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   163: aload 6
    //   165: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   168: aconst_null
    //   169: astore 6
    //   171: goto -144 -> 27
    //   174: invokestatic 984	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   177: astore 8
    //   179: aload 9
    //   181: aload 8
    //   183: invokevirtual 976	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   186: pop
    //   187: new 986	java/io/BufferedReader
    //   190: dup
    //   191: new 988	java/io/FileReader
    //   194: dup
    //   195: ldc_w 990
    //   198: invokespecial 991	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   201: invokespecial 994	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   204: astore 7
    //   206: aload 7
    //   208: astore 6
    //   210: aload 7
    //   212: invokevirtual 997	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   215: astore 10
    //   217: aload 10
    //   219: ifnull +339 -> 558
    //   222: aload 10
    //   224: ifnull -18 -> 206
    //   227: aload 7
    //   229: astore 6
    //   231: aload 10
    //   233: ldc_w 999
    //   236: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   239: ifne +18 -> 257
    //   242: aload 7
    //   244: astore 6
    //   246: aload 10
    //   248: ldc_w 1001
    //   251: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   254: ifeq -48 -> 206
    //   257: aload 7
    //   259: astore 6
    //   261: aload 10
    //   263: ldc_w 1003
    //   266: invokevirtual 1005	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   269: ifne +18 -> 287
    //   272: aload 7
    //   274: astore 6
    //   276: aload 10
    //   278: ldc_w 1007
    //   281: invokevirtual 1005	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   284: ifeq -78 -> 206
    //   287: aload 7
    //   289: astore 6
    //   291: aload 10
    //   293: ldc_w 1009
    //   296: invokevirtual 1005	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   299: ifne -93 -> 206
    //   302: aload 7
    //   304: astore 6
    //   306: aload 10
    //   308: ldc_w 1011
    //   311: invokevirtual 1014	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   314: astore 10
    //   316: aload 7
    //   318: astore 6
    //   320: aload 10
    //   322: arraylength
    //   323: iconst_1
    //   324: if_icmple -118 -> 206
    //   327: aload 7
    //   329: astore 6
    //   331: new 958	java/io/File
    //   334: dup
    //   335: aload 10
    //   337: iconst_1
    //   338: aaload
    //   339: invokespecial 967	java/io/File:<init>	(Ljava/lang/String;)V
    //   342: astore 10
    //   344: aload 7
    //   346: astore 6
    //   348: aload 10
    //   350: invokevirtual 970	java/io/File:exists	()Z
    //   353: ifeq -147 -> 206
    //   356: aload 7
    //   358: astore 6
    //   360: aload 10
    //   362: invokevirtual 973	java/io/File:isDirectory	()Z
    //   365: ifeq -159 -> 206
    //   368: aload 7
    //   370: astore 6
    //   372: aload 8
    //   374: invokevirtual 961	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   377: aload 10
    //   379: invokevirtual 961	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   382: invokevirtual 518	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   385: ifne -179 -> 206
    //   388: aload 7
    //   390: astore 6
    //   392: aload 9
    //   394: aload 10
    //   396: invokevirtual 976	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   399: pop
    //   400: goto -194 -> 206
    //   403: astore 8
    //   405: aload 7
    //   407: astore 6
    //   409: aload_0
    //   410: aload 8
    //   412: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   415: aload 8
    //   417: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   420: aload 7
    //   422: ifnull +8 -> 430
    //   425: aload 7
    //   427: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   430: iload_1
    //   431: ifeq +193 -> 624
    //   434: new 951	java/util/ArrayList
    //   437: dup
    //   438: invokespecial 952	java/util/ArrayList:<init>	()V
    //   441: astore_0
    //   442: iconst_0
    //   443: istore_2
    //   444: iload 4
    //   446: istore_3
    //   447: iload_2
    //   448: aload 9
    //   450: invokevirtual 1018	java/util/ArrayList:size	()I
    //   453: if_icmpge +145 -> 598
    //   456: iconst_0
    //   457: istore_3
    //   458: iload_3
    //   459: aload 9
    //   461: invokevirtual 1018	java/util/ArrayList:size	()I
    //   464: if_icmpge +127 -> 591
    //   467: iload_2
    //   468: iload_3
    //   469: if_icmpeq +82 -> 551
    //   472: new 124	java/lang/StringBuilder
    //   475: dup
    //   476: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   479: aload 9
    //   481: iload_2
    //   482: invokevirtual 1022	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   485: checkcast 958	java/io/File
    //   488: invokevirtual 961	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   491: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: ldc_w 1024
    //   497: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   503: new 124	java/lang/StringBuilder
    //   506: dup
    //   507: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   510: aload 9
    //   512: iload_3
    //   513: invokevirtual 1022	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   516: checkcast 958	java/io/File
    //   519: invokevirtual 961	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   522: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: ldc_w 1024
    //   528: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   537: ifeq +14 -> 551
    //   540: aload_0
    //   541: aload 9
    //   543: iload_2
    //   544: invokevirtual 1022	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   547: invokevirtual 976	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   550: pop
    //   551: iload_3
    //   552: iconst_1
    //   553: iadd
    //   554: istore_3
    //   555: goto -97 -> 458
    //   558: aload 7
    //   560: ifnull -130 -> 430
    //   563: aload 7
    //   565: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   568: goto -138 -> 430
    //   571: astore_0
    //   572: goto -142 -> 430
    //   575: astore_0
    //   576: aconst_null
    //   577: astore 6
    //   579: aload 6
    //   581: ifnull +8 -> 589
    //   584: aload 6
    //   586: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   589: aload_0
    //   590: athrow
    //   591: iload_2
    //   592: iconst_1
    //   593: iadd
    //   594: istore_2
    //   595: goto -151 -> 444
    //   598: iload_3
    //   599: aload_0
    //   600: invokevirtual 1018	java/util/ArrayList:size	()I
    //   603: if_icmpge +21 -> 624
    //   606: aload 9
    //   608: aload_0
    //   609: iload_3
    //   610: invokevirtual 1022	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   613: invokevirtual 1027	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   616: pop
    //   617: iload_3
    //   618: iconst_1
    //   619: iadd
    //   620: istore_3
    //   621: goto -23 -> 598
    //   624: aload 9
    //   626: aload 9
    //   628: invokevirtual 1018	java/util/ArrayList:size	()I
    //   631: anewarray 958	java/io/File
    //   634: invokevirtual 1031	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   637: checkcast 1033	[Ljava/io/File;
    //   640: checkcast 1033	[Ljava/io/File;
    //   643: areturn
    //   644: astore_0
    //   645: goto -215 -> 430
    //   648: astore 6
    //   650: goto -61 -> 589
    //   653: astore_0
    //   654: goto -75 -> 579
    //   657: astore 8
    //   659: aconst_null
    //   660: astore 7
    //   662: goto -257 -> 405
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	665	0	paramContext	Context
    //   0	665	1	paramBoolean	boolean
    //   37	558	2	i	int
    //   35	586	3	j	int
    //   1	444	4	k	int
    //   62	17	5	m	int
    //   25	19	6	arrayOfFile	File[]
    //   115	9	6	localNullPointerException	NullPointerException
    //   130	1	6	localObject1	Object
    //   135	9	6	localSecurityException	SecurityException
    //   150	1	6	localObject2	Object
    //   155	9	6	localException1	Exception
    //   169	416	6	localObject3	Object
    //   648	1	6	localIOException	IOException
    //   204	457	7	localBufferedReader	java.io.BufferedReader
    //   177	196	8	localFile	File
    //   403	13	8	localException2	Exception
    //   657	1	8	localException3	Exception
    //   10	617	9	localArrayList	ArrayList
    //   215	180	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   20	27	115	java/lang/NullPointerException
    //   20	27	135	java/lang/SecurityException
    //   20	27	155	java/lang/Exception
    //   210	217	403	java/lang/Exception
    //   231	242	403	java/lang/Exception
    //   246	257	403	java/lang/Exception
    //   261	272	403	java/lang/Exception
    //   276	287	403	java/lang/Exception
    //   291	302	403	java/lang/Exception
    //   306	316	403	java/lang/Exception
    //   320	327	403	java/lang/Exception
    //   331	344	403	java/lang/Exception
    //   348	356	403	java/lang/Exception
    //   360	368	403	java/lang/Exception
    //   372	388	403	java/lang/Exception
    //   392	400	403	java/lang/Exception
    //   563	568	571	java/io/IOException
    //   187	206	575	finally
    //   425	430	644	java/io/IOException
    //   584	589	648	java/io/IOException
    //   210	217	653	finally
    //   231	242	653	finally
    //   246	257	653	finally
    //   261	272	653	finally
    //   276	287	653	finally
    //   291	302	653	finally
    //   306	316	653	finally
    //   320	327	653	finally
    //   331	344	653	finally
    //   348	356	653	finally
    //   360	368	653	finally
    //   372	388	653	finally
    //   392	400	653	finally
    //   409	420	653	finally
    //   187	206	657	java/lang/Exception
  }
  
  public static int c(String paramString)
  {
    int j = 0;
    try
    {
      paramString = new ExifInterface(paramString);
      int i = j;
      int k;
      if (paramString != null)
      {
        k = paramString.getAttributeInt("Orientation", 1);
        if (k != 6) {
          break label37;
        }
        i = 90;
      }
      label37:
      do
      {
        return i;
        if (k == 3) {
          return 180;
        }
        i = j;
      } while (k != 8);
      return 270;
    }
    catch (Exception paramString) {}
    return 0;
  }
  
  public static String c(long paramLong)
  {
    if (paramLong < 0L) {
      return "";
    }
    long l1 = paramLong / 1000L;
    long l2 = paramLong / 60000L % 60L;
    paramLong = paramLong / 3600000L % 24L;
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramLong > 0L)
    {
      localStringBuilder.append(paramLong);
      localStringBuilder.append("h ");
    }
    if (l2 > 0L)
    {
      localStringBuilder.append(l2);
      localStringBuilder.append("m ");
    }
    localStringBuilder.append(l1 % 60L);
    localStringBuilder.append("s");
    return localStringBuilder.toString();
  }
  
  public static String c(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("v");
    localStringBuilder.append(b(paramContext));
    if (a()) {
      localStringBuilder.append("");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(" (");
      localStringBuilder.append(E(paramContext).toString());
      localStringBuilder.append(")");
    }
  }
  
  public static String c(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      paramString = new BigInteger(1, localMessageDigest.digest()).toString(16);
      return paramString;
    }
    catch (Exception paramString)
    {
      com.baloota.dumpster.logger.a.a(paramContext, paramString.getMessage(), paramString);
    }
    return null;
  }
  
  static void c(Activity paramActivity)
  {
    com.baloota.dumpster.logger.a.c(paramActivity, "Activating VIP! ..");
    Context localContext = paramActivity.getApplicationContext();
    com.baloota.dumpster.preferences.a.I(localContext, true);
    com.baloota.dumpster.preferences.a.K(localContext, true);
    paramActivity.finish();
    paramActivity.startActivity(paramActivity.getIntent());
  }
  
  public static void c(Context paramContext, File paramFile)
  {
    if ((paramContext != null) && (paramFile != null))
    {
      if (Build.VERSION.SDK_INT >= 19) {
        paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(paramFile)));
      }
    }
    else {
      return;
    }
    Object localObject = paramFile.getAbsolutePath();
    Uri localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    localObject = localContentResolver.query(localUri, new String[] { "_id" }, "_data = ?", new String[] { localObject }, null);
    if ((localObject != null) && (((Cursor)localObject).moveToFirst()))
    {
      long l = ((Cursor)localObject).getLong(((Cursor)localObject).getColumnIndexOrThrow("_id"));
      localContentResolver.delete(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, l), null, null);
    }
    ((Cursor)localObject).close();
    paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(paramFile.getParentFile())));
    paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(paramFile.getParentFile())));
  }
  
  @TargetApi(9)
  public static boolean c()
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT >= 9) {}
    try
    {
      bool = Environment.isExternalStorageRemovable();
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean c(Context paramContext, boolean paramBoolean)
  {
    if ((paramBoolean) && (c != null)) {
      return c.booleanValue();
    }
    for (;;)
    {
      try
      {
        if (!s(paramContext)) {
          continue;
        }
        Object localObject = System.getenv("PATH");
        if (localObject == null) {
          continue;
        }
        localObject = Arrays.asList(((String)localObject).split(":"));
        i = 0;
        if (i >= ((List)localObject).size()) {
          continue;
        }
        File localFile = new File((String)((List)localObject).get(i), "su");
        if (!localFile.exists()) {
          continue;
        }
        com.baloota.dumpster.logger.a.d(paramContext, (String)((List)localObject).get(i) + "/su");
        if (Build.VERSION.SDK_INT < 9) {
          continue;
        }
        if (!localFile.canExecute()) {
          continue;
        }
        com.baloota.dumpster.logger.a.d(paramContext, (String)((List)localObject).get(i) + "/su - canExecute");
        paramBoolean = true;
      }
      catch (Exception localException)
      {
        int i;
        com.baloota.dumpster.logger.a.a(paramContext, localException.getMessage(), localException);
        paramBoolean = false;
        continue;
      }
      c = Boolean.valueOf(paramBoolean);
      return paramBoolean;
      paramBoolean = true;
      continue;
      i += 1;
    }
  }
  
  public static String d(Context paramContext)
  {
    if (TextUtils.isEmpty(g)) {}
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      if ((paramContext != null) && (paramContext.length > 0))
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(paramContext[0].toByteArray());
        g = a(localMessageDigest.digest());
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return g;
  }
  
  public static String d(Context paramContext, String paramString)
  {
    try
    {
      paramString = dj.a(paramString, dj.a("iJpfRJ7aefR4QvlBOYTvig==:sEMZg1WHfbURxQvc+y2DeXNBqxn7yJaDGgCyY0La4EQ=")).toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      com.baloota.dumpster.logger.a.a(paramContext, b, "Failed to encrypt string: " + paramString, paramString);
    }
    return null;
  }
  
  static String d(String paramString)
  {
    String str = "";
    if ("date".equals(paramString)) {
      str = "deleted_date DESC, display_name ASC";
    }
    do
    {
      return str;
      if ("size".equals(paramString)) {
        return "size DESC, deleted_date DESC";
      }
      if ("type".equals(paramString)) {
        return "item_type_code ASC, deleted_date DESC";
      }
    } while (!"name".equals(paramString));
    return "display_name COLLATE NOCASE ASC, deleted_date DESC";
  }
  
  public static void d(Activity paramActivity)
  {
    Context localContext = paramActivity.getApplicationContext();
    e(paramActivity);
    t(localContext);
    new AsyncTask()
    {
      protected Void a(Void... paramAnonymousVarArgs)
      {
        dt.z(this.a);
        dt.p(this.a);
        com.baloota.dumpster.preferences.a.a(this.a);
        return null;
      }
    }.execute(new Void[0]);
    if (!j(localContext)) {
      localContext.startService(new Intent(localContext, DumpsterManager.class));
    }
    new AsyncTask()
    {
      protected Void a(Void... paramAnonymousVarArgs)
      {
        e.g(this.a);
        return null;
      }
    }.execute(new Void[] { null, null, null });
    try
    {
      ea.a(localContext);
      return;
    }
    catch (Exception paramActivity)
    {
      com.baloota.dumpster.logger.a.a(localContext, paramActivity.getMessage(), paramActivity);
    }
  }
  
  public static boolean d()
  {
    return ("sdk".equals(Build.PRODUCT)) || ("google_sdk".equals(Build.PRODUCT));
  }
  
  public static boolean d(Context paramContext, boolean paramBoolean)
  {
    return (com.baloota.dumpster.preferences.a.A(paramContext, paramBoolean)) || (com.baloota.dumpster.preferences.a.H(paramContext, paramBoolean));
  }
  
  public static File e(Context paramContext, String paramString)
  {
    long l = 0L;
    Context localContext = null;
    Object localObject = null;
    int i;
    if (paramString != null)
    {
      File[] arrayOfFile = b(paramContext, false);
      int j = arrayOfFile.length;
      i = 0;
      paramContext = localObject;
      localContext = paramContext;
      if (i < j)
      {
        localContext = arrayOfFile[i];
        if ((localContext == null) || (!paramString.startsWith(localContext.getAbsolutePath())) || (localContext.getAbsolutePath().length() <= l)) {
          break label99;
        }
        l = localContext.getAbsolutePath().length();
        paramContext = localContext;
      }
    }
    label99:
    for (;;)
    {
      i += 1;
      break;
      return localContext;
    }
  }
  
  public static String e(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    do
    {
      return str;
      str = new String(paramString);
      paramString = str;
      if (str.contains("display_name")) {
        paramString = str.replace("display_name", "original_name");
      }
      str = paramString;
    } while (!paramString.contains("item_type_code"));
    return paramString.replace("item_type_code", "file_type_code");
  }
  
  public static void e(Activity paramActivity)
  {
    Upgrade.a(paramActivity.getApplicationContext());
    du.a(paramActivity.getApplicationContext(), true);
  }
  
  /* Error */
  public static boolean e(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: invokevirtual 660	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore 6
    //   9: aload 6
    //   11: ifnull +253 -> 264
    //   14: aload 6
    //   16: sipush 128
    //   19: invokevirtual 1262	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   22: astore 7
    //   24: aload 7
    //   26: astore_0
    //   27: iload 4
    //   29: istore_3
    //   30: aload 6
    //   32: ifnull +212 -> 244
    //   35: iload 4
    //   37: istore_3
    //   38: aload_0
    //   39: ifnull +205 -> 244
    //   42: iconst_0
    //   43: istore_1
    //   44: iconst_0
    //   45: istore_3
    //   46: iload_1
    //   47: aload_0
    //   48: invokeinterface 1152 1 0
    //   53: if_icmpge +191 -> 244
    //   56: aload_0
    //   57: iload_1
    //   58: invokeinterface 1153 2 0
    //   63: checkcast 749	android/content/pm/ApplicationInfo
    //   66: astore 8
    //   68: aload 6
    //   70: aload 8
    //   72: getfield 1265	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   75: sipush 4096
    //   78: invokevirtual 666	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   81: astore 7
    //   83: iload_3
    //   84: istore 4
    //   86: aload 7
    //   88: ifnull +146 -> 234
    //   91: iload_3
    //   92: istore 4
    //   94: aload 7
    //   96: getfield 1268	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   99: ifnull +135 -> 234
    //   102: iconst_0
    //   103: istore_2
    //   104: iload_2
    //   105: aload 7
    //   107: getfield 1268	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   110: arraylength
    //   111: if_icmpge +120 -> 231
    //   114: aload 7
    //   116: getfield 1268	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   119: iload_2
    //   120: aaload
    //   121: astore 9
    //   123: iload_3
    //   124: istore 4
    //   126: ldc_w 1270
    //   129: aload 6
    //   131: aload 9
    //   133: iconst_0
    //   134: invokevirtual 1274	android/content/pm/PackageManager:getPermissionInfo	(Ljava/lang/String;I)Landroid/content/pm/PermissionInfo;
    //   137: getfield 1278	android/content/pm/PermissionInfo:name	Ljava/lang/String;
    //   140: invokevirtual 518	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   143: ifeq +27 -> 170
    //   146: aload 8
    //   148: getfield 1265	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   151: ldc_w 1280
    //   154: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   157: istore 5
    //   159: iload_3
    //   160: istore 4
    //   162: iload 5
    //   164: ifne +6 -> 170
    //   167: iconst_1
    //   168: istore 4
    //   170: iload_2
    //   171: iconst_1
    //   172: iadd
    //   173: istore_2
    //   174: iload 4
    //   176: istore_3
    //   177: goto -73 -> 104
    //   180: astore 7
    //   182: aconst_null
    //   183: astore 6
    //   185: aload_0
    //   186: aload 7
    //   188: invokevirtual 1281	java/lang/AbstractMethodError:getMessage	()Ljava/lang/String;
    //   191: aload 7
    //   193: iconst_0
    //   194: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   197: aconst_null
    //   198: astore_0
    //   199: goto -172 -> 27
    //   202: astore 7
    //   204: aconst_null
    //   205: astore 6
    //   207: aload_0
    //   208: aload 7
    //   210: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   213: aload 7
    //   215: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   218: aconst_null
    //   219: astore_0
    //   220: goto -193 -> 27
    //   223: astore 7
    //   225: aconst_null
    //   226: astore 7
    //   228: goto -145 -> 83
    //   231: iload_3
    //   232: istore 4
    //   234: iload_1
    //   235: iconst_1
    //   236: iadd
    //   237: istore_1
    //   238: iload 4
    //   240: istore_3
    //   241: goto -195 -> 46
    //   244: iload_3
    //   245: ireturn
    //   246: astore 9
    //   248: iload_3
    //   249: istore 4
    //   251: goto -81 -> 170
    //   254: astore 7
    //   256: goto -49 -> 207
    //   259: astore 7
    //   261: goto -76 -> 185
    //   264: aconst_null
    //   265: astore_0
    //   266: goto -239 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	269	0	paramContext	Context
    //   43	195	1	i	int
    //   103	71	2	j	int
    //   29	220	3	bool1	boolean
    //   1	249	4	bool2	boolean
    //   157	6	5	bool3	boolean
    //   7	199	6	localPackageManager	PackageManager
    //   22	93	7	localObject1	Object
    //   180	12	7	localAbstractMethodError1	AbstractMethodError
    //   202	12	7	localException1	Exception
    //   223	1	7	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   226	1	7	localObject2	Object
    //   254	1	7	localException2	Exception
    //   259	1	7	localAbstractMethodError2	AbstractMethodError
    //   66	81	8	localApplicationInfo	android.content.pm.ApplicationInfo
    //   121	11	9	str	String
    //   246	1	9	localNameNotFoundException2	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   3	9	180	java/lang/AbstractMethodError
    //   3	9	202	java/lang/Exception
    //   68	83	223	android/content/pm/PackageManager$NameNotFoundException
    //   126	159	246	android/content/pm/PackageManager$NameNotFoundException
    //   14	24	254	java/lang/Exception
    //   14	24	259	java/lang/AbstractMethodError
  }
  
  public static boolean e(Context paramContext, boolean paramBoolean)
  {
    return a(paramContext, true, paramBoolean);
  }
  
  public static void f(Activity paramActivity)
  {
    Context localContext = paramActivity.getApplicationContext();
    try
    {
      AdBoost.appStarted(paramActivity);
      if (!e(localContext, true))
      {
        com.baloota.dumpster.logger.a.b(paramActivity, b, "Initializing video ad..");
        ee.a(paramActivity);
        if (du.c("serveInterstitial_new"))
        {
          com.baloota.dumpster.logger.a.b(paramActivity, b, "Loading interstitial ad..");
          eb.a(paramActivity);
          if (!du.c("serveAds")) {
            break label121;
          }
          com.baloota.dumpster.logger.a.b(paramActivity, b, "Loading native ad..");
          ed.a(localContext);
        }
      }
      else
      {
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        com.baloota.dumpster.logger.a.a(paramActivity, "AdBoost initialization failed: " + localException, localException);
        continue;
        com.baloota.dumpster.logger.a.b(localContext, b, "Not loading interstitial ad because GTM");
      }
      label121:
      com.baloota.dumpster.logger.a.b(paramActivity, b, "Not loading native ad because GTM");
    }
  }
  
  public static void f(Context paramContext, String paramString)
  {
    b(paramContext, new File(paramString));
  }
  
  /* Error */
  public static String[] f(Context paramContext)
  {
    // Byte code:
    //   0: new 1319	java/util/HashSet
    //   3: dup
    //   4: invokespecial 1320	java/util/HashSet:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: invokevirtual 660	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore_3
    //   14: aload_3
    //   15: ifnull +448 -> 463
    //   18: aload_3
    //   19: sipush 128
    //   22: invokevirtual 1262	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   25: astore 4
    //   27: aload 4
    //   29: astore 5
    //   31: aload_3
    //   32: astore 4
    //   34: aload 5
    //   36: astore_3
    //   37: aload 4
    //   39: ifnull +397 -> 436
    //   42: aload_3
    //   43: ifnull +393 -> 436
    //   46: iconst_0
    //   47: istore_1
    //   48: iload_1
    //   49: aload_3
    //   50: invokeinterface 1152 1 0
    //   55: if_icmpge +381 -> 436
    //   58: aload_3
    //   59: iload_1
    //   60: invokeinterface 1153 2 0
    //   65: checkcast 749	android/content/pm/ApplicationInfo
    //   68: astore 7
    //   70: aload 4
    //   72: aload 7
    //   74: getfield 1265	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   77: sipush 4096
    //   80: invokevirtual 666	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   83: astore 5
    //   85: aload 5
    //   87: ifnull +342 -> 429
    //   90: aload 5
    //   92: getfield 1268	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   95: ifnull +334 -> 429
    //   98: iconst_0
    //   99: istore_2
    //   100: iload_2
    //   101: aload 5
    //   103: getfield 1268	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   106: arraylength
    //   107: if_icmpge +322 -> 429
    //   110: aload 5
    //   112: getfield 1268	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   115: iload_2
    //   116: aaload
    //   117: astore 8
    //   119: aload 4
    //   121: aload 8
    //   123: iconst_0
    //   124: invokevirtual 1274	android/content/pm/PackageManager:getPermissionInfo	(Ljava/lang/String;I)Landroid/content/pm/PermissionInfo;
    //   127: astore 8
    //   129: ldc_w 1270
    //   132: aload 8
    //   134: getfield 1278	android/content/pm/PermissionInfo:name	Ljava/lang/String;
    //   137: invokevirtual 518	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   140: ifne +17 -> 157
    //   143: ldc_w 1322
    //   146: aload 8
    //   148: getfield 1278	android/content/pm/PermissionInfo:name	Ljava/lang/String;
    //   151: invokevirtual 518	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   154: ifeq +113 -> 267
    //   157: aload 7
    //   159: getfield 1265	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   162: ldc_w 1280
    //   165: invokevirtual 677	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   168: ifne +99 -> 267
    //   171: aload_0
    //   172: aload 7
    //   174: getfield 1325	android/content/pm/ApplicationInfo:processName	Ljava/lang/String;
    //   177: invokestatic 1327	android/support/v7/dt:j	(Landroid/content/Context;Ljava/lang/String;)Z
    //   180: ifeq +157 -> 337
    //   183: aload 6
    //   185: new 124	java/lang/StringBuilder
    //   188: dup
    //   189: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   192: ldc_w 1329
    //   195: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: ldc_w 1331
    //   201: iconst_1
    //   202: anewarray 4	java/lang/Object
    //   205: dup
    //   206: iconst_0
    //   207: new 124	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 1333
    //   217: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload 4
    //   222: aload 7
    //   224: invokevirtual 1337	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   227: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   230: ldc_w 1333
    //   233: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: aastore
    //   240: invokestatic 1340	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   243: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: ldc_w 1342
    //   249: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: aload 7
    //   254: getfield 1265	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   257: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   263: invokevirtual 1343	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   266: pop
    //   267: iload_2
    //   268: iconst_1
    //   269: iadd
    //   270: istore_2
    //   271: goto -171 -> 100
    //   274: astore 4
    //   276: aconst_null
    //   277: astore_3
    //   278: aload_0
    //   279: aload 4
    //   281: invokevirtual 1281	java/lang/AbstractMethodError:getMessage	()Ljava/lang/String;
    //   284: aload 4
    //   286: iconst_0
    //   287: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   290: aconst_null
    //   291: astore 5
    //   293: aload_3
    //   294: astore 4
    //   296: aload 5
    //   298: astore_3
    //   299: goto -262 -> 37
    //   302: astore 4
    //   304: aconst_null
    //   305: astore_3
    //   306: aload_0
    //   307: aload 4
    //   309: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   312: aload 4
    //   314: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   317: aconst_null
    //   318: astore 5
    //   320: aload_3
    //   321: astore 4
    //   323: aload 5
    //   325: astore_3
    //   326: goto -289 -> 37
    //   329: astore 5
    //   331: aconst_null
    //   332: astore 5
    //   334: goto -249 -> 85
    //   337: aload 6
    //   339: new 124	java/lang/StringBuilder
    //   342: dup
    //   343: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   346: ldc_w 1345
    //   349: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: ldc_w 1331
    //   355: iconst_1
    //   356: anewarray 4	java/lang/Object
    //   359: dup
    //   360: iconst_0
    //   361: new 124	java/lang/StringBuilder
    //   364: dup
    //   365: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   368: ldc_w 1333
    //   371: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: aload 4
    //   376: aload 7
    //   378: invokevirtual 1337	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   381: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   384: ldc_w 1333
    //   387: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   393: aastore
    //   394: invokestatic 1340	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   397: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: ldc_w 1342
    //   403: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: aload 7
    //   408: getfield 1265	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   411: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   417: invokevirtual 1343	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   420: pop
    //   421: goto -154 -> 267
    //   424: astore 8
    //   426: goto -159 -> 267
    //   429: iload_1
    //   430: iconst_1
    //   431: iadd
    //   432: istore_1
    //   433: goto -385 -> 48
    //   436: aload 6
    //   438: aload 6
    //   440: invokevirtual 1346	java/util/HashSet:size	()I
    //   443: anewarray 58	java/lang/String
    //   446: invokevirtual 1347	java/util/HashSet:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   449: checkcast 1348	[Ljava/lang/String;
    //   452: areturn
    //   453: astore 4
    //   455: goto -149 -> 306
    //   458: astore 4
    //   460: goto -182 -> 278
    //   463: aconst_null
    //   464: astore 4
    //   466: goto -439 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	469	0	paramContext	Context
    //   47	386	1	i	int
    //   99	172	2	j	int
    //   13	313	3	localObject1	Object
    //   25	196	4	localObject2	Object
    //   274	11	4	localAbstractMethodError1	AbstractMethodError
    //   294	1	4	localObject3	Object
    //   302	11	4	localException1	Exception
    //   321	54	4	localObject4	Object
    //   453	1	4	localException2	Exception
    //   458	1	4	localAbstractMethodError2	AbstractMethodError
    //   464	1	4	localObject5	Object
    //   29	295	5	localObject6	Object
    //   329	1	5	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   332	1	5	localObject7	Object
    //   7	432	6	localHashSet	java.util.HashSet
    //   68	339	7	localApplicationInfo	android.content.pm.ApplicationInfo
    //   117	30	8	localObject8	Object
    //   424	1	8	localNameNotFoundException2	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   9	14	274	java/lang/AbstractMethodError
    //   9	14	302	java/lang/Exception
    //   70	85	329	android/content/pm/PackageManager$NameNotFoundException
    //   119	157	424	android/content/pm/PackageManager$NameNotFoundException
    //   157	267	424	android/content/pm/PackageManager$NameNotFoundException
    //   337	421	424	android/content/pm/PackageManager$NameNotFoundException
    //   18	27	453	java/lang/Exception
    //   18	27	458	java/lang/AbstractMethodError
  }
  
  /* Error */
  @TargetApi(10)
  public static com.baloota.dumpster.bean.ItemMetadata g(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 810	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 10
    //   5: if_icmplt +69 -> 74
    //   8: new 812	android/media/MediaMetadataRetriever
    //   11: dup
    //   12: invokespecial 813	android/media/MediaMetadataRetriever:<init>	()V
    //   15: astore_0
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual 816	android/media/MediaMetadataRetriever:setDataSource	(Ljava/lang/String;)V
    //   21: lconst_0
    //   22: lstore_2
    //   23: aload_0
    //   24: bipush 9
    //   26: invokevirtual 1352	android/media/MediaMetadataRetriever:extractMetadata	(I)Ljava/lang/String;
    //   29: invokestatic 1358	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   32: lstore 4
    //   34: lload 4
    //   36: lstore_2
    //   37: new 1360	com/baloota/dumpster/bean/ItemMetadata
    //   40: dup
    //   41: aload_0
    //   42: bipush 7
    //   44: invokevirtual 1352	android/media/MediaMetadataRetriever:extractMetadata	(I)Ljava/lang/String;
    //   47: lload_2
    //   48: invokestatic 1363	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   51: aload_0
    //   52: iconst_2
    //   53: invokevirtual 1352	android/media/MediaMetadataRetriever:extractMetadata	(I)Ljava/lang/String;
    //   56: aload_0
    //   57: iconst_1
    //   58: invokevirtual 1352	android/media/MediaMetadataRetriever:extractMetadata	(I)Ljava/lang/String;
    //   61: invokespecial 1366	com/baloota/dumpster/bean/ItemMetadata:<init>	(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V
    //   64: astore_0
    //   65: aload_0
    //   66: areturn
    //   67: astore_0
    //   68: aconst_null
    //   69: areturn
    //   70: astore_1
    //   71: goto -34 -> 37
    //   74: aconst_null
    //   75: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	paramContext	Context
    //   0	76	1	paramString	String
    //   22	26	2	l1	long
    //   32	3	4	l2	long
    // Exception table:
    //   from	to	target	type
    //   16	21	67	java/lang/Exception
    //   37	65	67	java/lang/Exception
    //   23	34	70	java/lang/Exception
  }
  
  public static boolean g(Context paramContext)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.hexamob.androidrecyclebin", 1);
      i = 1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        label29:
        int i = 0;
      }
    }
    try
    {
      paramContext.getPackageInfo("com.hexamob.androidrecyclebinpro", 1);
      j = 1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      j = 0;
      break label29;
    }
    if ((i != 0) || (j != 0)) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean h(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.ryosoftware.recyclebin", 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean h(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (paramString != null) {}
    try
    {
      bool = paramString.equals(paramContext.getResources().getConfiguration().locale.getLanguage());
      return bool;
    }
    catch (Exception paramString)
    {
      com.baloota.dumpster.logger.a.a(paramContext, paramString.getMessage(), paramString);
    }
    return false;
  }
  
  public static SQLiteDatabase i(Context paramContext, String paramString)
  {
    return SQLiteDatabase.openDatabase(paramContext.getDatabasePath(paramString).getPath(), null, 1);
  }
  
  public static boolean i(Context paramContext)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    if (paramContext != null)
    {
      switch (paramContext.getRingerMode())
      {
      default: 
        return false;
      case 2: 
        return false;
      case 0: 
        return true;
      }
      return true;
    }
    return false;
  }
  
  public static boolean j(Context paramContext)
  {
    try
    {
      List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          if (DumpsterManager.class.getName().equals(((ActivityManager.RunningServiceInfo)localList.get(i)).service.getClassName()))
          {
            int j = ((ActivityManager.RunningServiceInfo)localList.get(i)).pid;
            if (j != 0) {
              return true;
            }
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      com.baloota.dumpster.logger.a.a(paramContext, localThrowable.getMessage(), localThrowable, false);
      return false;
    }
  }
  
  private static boolean j(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      int i = 0;
      while (i < paramContext.size())
      {
        boolean bool = ((ActivityManager.RunningAppProcessInfo)paramContext.get(i)).processName.split(":")[0].equals(paramString);
        if (bool) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramContext) {}
  }
  
  public static List<ActivityManager.RunningAppProcessInfo> k(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    try
    {
      Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if (localObject == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      String str = paramContext.getPackageName();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if (localRunningAppProcessInfo.processName.contains(str)) {
          localArrayList.add(localRunningAppProcessInfo);
        }
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      com.baloota.dumpster.logger.a.a(paramContext, localThrowable.getMessage(), localThrowable, false);
      return null;
    }
  }
  
  /* Error */
  public static String[] l(Context paramContext)
  {
    // Byte code:
    //   0: new 951	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 952	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: getstatic 1451	android/util/Patterns:EMAIL_ADDRESS	Ljava/util/regex/Pattern;
    //   11: astore 4
    //   13: aload_0
    //   14: invokestatic 1456	android/accounts/AccountManager:get	(Landroid/content/Context;)Landroid/accounts/AccountManager;
    //   17: invokevirtual 1460	android/accounts/AccountManager:getAccounts	()[Landroid/accounts/Account;
    //   20: astore 5
    //   22: aload 5
    //   24: arraylength
    //   25: istore_2
    //   26: iconst_0
    //   27: istore_1
    //   28: iload_1
    //   29: iload_2
    //   30: if_icmpge +70 -> 100
    //   33: aload 5
    //   35: iload_1
    //   36: aaload
    //   37: astore 6
    //   39: aload 4
    //   41: aload 6
    //   43: getfield 1463	android/accounts/Account:name	Ljava/lang/String;
    //   46: invokevirtual 1469	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   49: invokevirtual 1474	java/util/regex/Matcher:matches	()Z
    //   52: ifeq +27 -> 79
    //   55: aload 6
    //   57: getfield 1476	android/accounts/Account:type	Ljava/lang/String;
    //   60: ldc_w 478
    //   63: invokevirtual 1479	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   66: ifeq +13 -> 79
    //   69: aload_3
    //   70: aload 6
    //   72: getfield 1463	android/accounts/Account:name	Ljava/lang/String;
    //   75: invokevirtual 976	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   78: pop
    //   79: iload_1
    //   80: iconst_1
    //   81: iadd
    //   82: istore_1
    //   83: goto -55 -> 28
    //   86: astore 4
    //   88: aload_0
    //   89: aload 4
    //   91: invokevirtual 978	java/lang/SecurityException:getMessage	()Ljava/lang/String;
    //   94: aload 4
    //   96: iconst_0
    //   97: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   100: aload_3
    //   101: aload_3
    //   102: invokevirtual 1018	java/util/ArrayList:size	()I
    //   105: anewarray 58	java/lang/String
    //   108: invokevirtual 1031	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   111: checkcast 1348	[Ljava/lang/String;
    //   114: checkcast 1348	[Ljava/lang/String;
    //   117: areturn
    //   118: astore 4
    //   120: aload_0
    //   121: aload 4
    //   123: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   126: aload 4
    //   128: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   131: goto -31 -> 100
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	paramContext	Context
    //   27	56	1	i	int
    //   25	6	2	j	int
    //   7	95	3	localArrayList	ArrayList
    //   11	29	4	localPattern	java.util.regex.Pattern
    //   86	9	4	localSecurityException	SecurityException
    //   118	9	4	localException	Exception
    //   20	14	5	arrayOfAccount	android.accounts.Account[]
    //   37	34	6	localAccount	android.accounts.Account
    // Exception table:
    //   from	to	target	type
    //   8	26	86	java/lang/SecurityException
    //   39	79	86	java/lang/SecurityException
    //   8	26	118	java/lang/Exception
    //   39	79	118	java/lang/Exception
  }
  
  public static boolean m(Context paramContext)
  {
    return a(paramContext, true);
  }
  
  public static File[] n(Context paramContext)
  {
    return b(paramContext, true);
  }
  
  /* Error */
  public static String[] o(Context paramContext)
  {
    // Byte code:
    //   0: new 951	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 952	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aconst_null
    //   10: astore 4
    //   12: aconst_null
    //   13: astore_2
    //   14: aconst_null
    //   15: astore_3
    //   16: new 986	java/io/BufferedReader
    //   19: dup
    //   20: new 988	java/io/FileReader
    //   23: dup
    //   24: ldc_w 990
    //   27: invokespecial 991	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   30: invokespecial 994	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   33: astore_1
    //   34: aload_1
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 997	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull +56 -> 98
    //   45: aload_1
    //   46: astore_2
    //   47: aload 5
    //   49: aload_3
    //   50: invokevirtual 976	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   53: pop
    //   54: goto -20 -> 34
    //   57: astore_3
    //   58: aload_1
    //   59: astore_2
    //   60: aload_0
    //   61: aload_3
    //   62: invokevirtual 1488	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   65: aload_3
    //   66: iconst_0
    //   67: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   70: aload_1
    //   71: ifnull +7 -> 78
    //   74: aload_1
    //   75: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   78: aload 5
    //   80: aload 5
    //   82: invokevirtual 1018	java/util/ArrayList:size	()I
    //   85: anewarray 58	java/lang/String
    //   88: invokevirtual 1031	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   91: checkcast 1348	[Ljava/lang/String;
    //   94: checkcast 1348	[Ljava/lang/String;
    //   97: areturn
    //   98: aload_1
    //   99: ifnull -21 -> 78
    //   102: aload_1
    //   103: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   106: goto -28 -> 78
    //   109: astore_0
    //   110: goto -32 -> 78
    //   113: astore_2
    //   114: aload_3
    //   115: astore_1
    //   116: aload_2
    //   117: astore_3
    //   118: aload_1
    //   119: astore_2
    //   120: aload_0
    //   121: aload_3
    //   122: invokevirtual 1489	java/lang/OutOfMemoryError:getMessage	()Ljava/lang/String;
    //   125: aload_3
    //   126: iconst_0
    //   127: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   130: aload_1
    //   131: ifnull -53 -> 78
    //   134: aload_1
    //   135: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   138: goto -60 -> 78
    //   141: astore_0
    //   142: goto -64 -> 78
    //   145: astore_3
    //   146: aload 4
    //   148: astore_1
    //   149: aload_1
    //   150: astore_2
    //   151: aload_0
    //   152: aload_3
    //   153: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   156: aload_3
    //   157: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   160: aload_1
    //   161: ifnull -83 -> 78
    //   164: aload_1
    //   165: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   168: goto -90 -> 78
    //   171: astore_0
    //   172: goto -94 -> 78
    //   175: astore_0
    //   176: aload_2
    //   177: ifnull +7 -> 184
    //   180: aload_2
    //   181: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   184: aload_0
    //   185: athrow
    //   186: astore_0
    //   187: goto -109 -> 78
    //   190: astore_1
    //   191: goto -7 -> 184
    //   194: astore_0
    //   195: goto -19 -> 176
    //   198: astore_3
    //   199: goto -50 -> 149
    //   202: astore_3
    //   203: goto -85 -> 118
    //   206: astore_3
    //   207: aconst_null
    //   208: astore_1
    //   209: goto -151 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	212	0	paramContext	Context
    //   33	132	1	localObject1	Object
    //   190	1	1	localIOException	IOException
    //   208	1	1	localObject2	Object
    //   13	47	2	localObject3	Object
    //   113	4	2	localOutOfMemoryError1	OutOfMemoryError
    //   119	62	2	localObject4	Object
    //   15	35	3	str	String
    //   57	58	3	localFileNotFoundException1	FileNotFoundException
    //   117	9	3	localOutOfMemoryError2	OutOfMemoryError
    //   145	12	3	localException1	Exception
    //   198	1	3	localException2	Exception
    //   202	1	3	localOutOfMemoryError3	OutOfMemoryError
    //   206	1	3	localFileNotFoundException2	FileNotFoundException
    //   10	137	4	localObject5	Object
    //   7	74	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   36	41	57	java/io/FileNotFoundException
    //   47	54	57	java/io/FileNotFoundException
    //   102	106	109	java/io/IOException
    //   16	34	113	java/lang/OutOfMemoryError
    //   134	138	141	java/io/IOException
    //   16	34	145	java/lang/Exception
    //   164	168	171	java/io/IOException
    //   16	34	175	finally
    //   120	130	175	finally
    //   151	160	175	finally
    //   74	78	186	java/io/IOException
    //   180	184	190	java/io/IOException
    //   36	41	194	finally
    //   47	54	194	finally
    //   60	70	194	finally
    //   36	41	198	java/lang/Exception
    //   47	54	198	java/lang/Exception
    //   36	41	202	java/lang/OutOfMemoryError
    //   47	54	202	java/lang/OutOfMemoryError
    //   16	34	206	java/io/FileNotFoundException
  }
  
  public static boolean p(Context paramContext)
  {
    return c(paramContext, false);
  }
  
  public static boolean q(Context paramContext)
  {
    e = false;
    try
    {
      e = uh.a();
      return e;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        com.baloota.dumpster.logger.a.b(paramContext, "isRootAccessGiven ex " + localException.getClass().getName() + " " + localException.getMessage());
      }
    }
  }
  
  public static boolean r(Context paramContext)
  {
    return com.baloota.dumpster.preferences.a.v(paramContext);
  }
  
  @TargetApi(11)
  public static boolean s(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 11) {}
    try
    {
      String[] arrayOfString = o(paramContext);
      bool1 = bool2;
      String str1;
      int j;
      int i;
      if (arrayOfString != null)
      {
        str1 = Environment.getExternalStorageDirectory().getAbsolutePath();
        j = arrayOfString.length;
        i = 0;
      }
      for (;;)
      {
        bool1 = bool2;
        if (i < j)
        {
          String str2 = arrayOfString[i];
          if ((str2.contains(str1)) && (str2.contains(" fuse ")))
          {
            com.baloota.dumpster.logger.a.d(paramContext, "DumpsterUtils.java found fuse [" + str2 + "]");
            bool1 = true;
          }
        }
        else
        {
          return bool1;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      com.baloota.dumpster.logger.a.a(paramContext, localException.getMessage(), localException);
    }
  }
  
  @SuppressLint({"NewApi"})
  public static void t(Context paramContext)
  {
    try
    {
      long l = com.baloota.dumpster.preferences.a.l(paramContext);
      int i = (int)((System.currentTimeMillis() - l) / 86400000L);
      l = com.baloota.dumpster.preferences.a.m(paramContext);
      int j = (int)((System.currentTimeMillis() - l) / 86400000L);
      GeoIp localGeoIp = com.baloota.dumpster.preferences.a.C(paramContext);
      du.a("daysSinceInstall", Integer.valueOf(i));
      du.a("daysSinceInstallCurrentVersion", Integer.valueOf(j));
      du.a("filesSaved", Long.valueOf(com.baloota.dumpster.preferences.a.A(paramContext)));
      du.a("openedApp", Long.valueOf(com.baloota.dumpster.preferences.a.B(paramContext)));
      if ((localGeoIp != null) && (!TextUtils.isEmpty(localGeoIp.getCountry_code()))) {
        du.a("countryCode", localGeoIp.getCountry_code());
      }
      return;
    }
    catch (Exception localException)
    {
      com.baloota.dumpster.logger.a.a(paramContext, localException.getMessage(), localException);
    }
  }
  
  /* Error */
  public static int u(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 10
    //   6: iconst_0
    //   7: istore 5
    //   9: iconst_0
    //   10: istore 6
    //   12: invokestatic 1562	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   15: ldc_w 1564
    //   18: invokevirtual 1568	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   21: astore 8
    //   23: new 986	java/io/BufferedReader
    //   26: dup
    //   27: new 1570	java/io/InputStreamReader
    //   30: dup
    //   31: aload 8
    //   33: invokevirtual 1576	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   36: invokespecial 1579	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   39: sipush 1024
    //   42: invokespecial 1582	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   45: astore 9
    //   47: aload 9
    //   49: astore 7
    //   51: aload 9
    //   53: invokevirtual 997	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   56: astore 12
    //   58: aload 12
    //   60: ifnull +486 -> 546
    //   63: aload 9
    //   65: astore 7
    //   67: aload 12
    //   69: ldc_w 1584
    //   72: invokevirtual 1005	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   75: ifeq -28 -> 47
    //   78: aload 9
    //   80: astore 7
    //   82: aload 12
    //   84: ldc_w 1586
    //   87: invokevirtual 1014	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   90: astore 12
    //   92: aload 12
    //   94: ifnull +452 -> 546
    //   97: aload 9
    //   99: astore 7
    //   101: aload 12
    //   103: iconst_1
    //   104: aaload
    //   105: invokestatic 1589	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   108: istore_2
    //   109: aload 9
    //   111: astore 7
    //   113: aload 8
    //   115: invokevirtual 1592	java/lang/Process:waitFor	()I
    //   118: pop
    //   119: aload 9
    //   121: astore 7
    //   123: aload 8
    //   125: invokevirtual 1595	java/lang/Process:exitValue	()I
    //   128: istore 4
    //   130: iload 4
    //   132: istore_1
    //   133: iload_2
    //   134: istore_3
    //   135: aload 9
    //   137: ifnull +13 -> 150
    //   140: aload 9
    //   142: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   145: iload_2
    //   146: istore_3
    //   147: iload 4
    //   149: istore_1
    //   150: iload 5
    //   152: istore_2
    //   153: iload_1
    //   154: ifne +169 -> 323
    //   157: iload 5
    //   159: istore_2
    //   160: iload_3
    //   161: ifle +162 -> 323
    //   164: ldc_w 1597
    //   167: iconst_1
    //   168: anewarray 4	java/lang/Object
    //   171: dup
    //   172: iconst_0
    //   173: iload_3
    //   174: invokestatic 1535	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   177: aastore
    //   178: invokestatic 1340	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   181: astore 7
    //   183: iload 5
    //   185: istore_2
    //   186: aload 7
    //   188: ifnull +135 -> 323
    //   191: new 958	java/io/File
    //   194: dup
    //   195: aload 7
    //   197: invokespecial 967	java/io/File:<init>	(Ljava/lang/String;)V
    //   200: astore 8
    //   202: iload 5
    //   204: istore_2
    //   205: aload 8
    //   207: ifnull +116 -> 323
    //   210: iload 5
    //   212: istore_2
    //   213: aload 8
    //   215: invokevirtual 970	java/io/File:exists	()Z
    //   218: ifeq +105 -> 323
    //   221: iload 5
    //   223: istore_2
    //   224: aload 8
    //   226: invokevirtual 1600	java/io/File:canRead	()Z
    //   229: ifeq +94 -> 323
    //   232: aload 11
    //   234: astore 7
    //   236: new 986	java/io/BufferedReader
    //   239: dup
    //   240: new 988	java/io/FileReader
    //   243: dup
    //   244: aload 8
    //   246: invokespecial 1601	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   249: sipush 1024
    //   252: invokespecial 1582	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   255: astore 8
    //   257: aload 8
    //   259: invokevirtual 997	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   262: astore 7
    //   264: iload 6
    //   266: istore_1
    //   267: aload 7
    //   269: ifnull +40 -> 309
    //   272: aload 7
    //   274: ldc_w 1603
    //   277: invokevirtual 1005	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   280: ifeq -23 -> 257
    //   283: aload 7
    //   285: ldc_w 1586
    //   288: invokevirtual 1014	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   291: astore 7
    //   293: iload 6
    //   295: istore_1
    //   296: aload 7
    //   298: ifnull +11 -> 309
    //   301: aload 7
    //   303: iconst_3
    //   304: aaload
    //   305: invokestatic 1589	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   308: istore_1
    //   309: iload_1
    //   310: istore_2
    //   311: aload 8
    //   313: ifnull +10 -> 323
    //   316: aload 8
    //   318: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   321: iload_1
    //   322: istore_2
    //   323: iload_2
    //   324: ireturn
    //   325: astore 8
    //   327: aconst_null
    //   328: astore 9
    //   330: iconst_0
    //   331: istore_2
    //   332: aload 9
    //   334: astore 7
    //   336: aload_0
    //   337: new 124	java/lang/StringBuilder
    //   340: dup
    //   341: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   344: ldc_w 1605
    //   347: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: aload 8
    //   352: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   355: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: ldc_w 1516
    //   361: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   367: invokestatic 1504	com/baloota/dumpster/logger/a:b	(Landroid/content/Context;Ljava/lang/String;)V
    //   370: aload 9
    //   372: ifnull +167 -> 539
    //   375: aload 9
    //   377: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   380: iconst_0
    //   381: istore_1
    //   382: iload_2
    //   383: istore_3
    //   384: goto -234 -> 150
    //   387: astore 7
    //   389: iconst_0
    //   390: istore_1
    //   391: iload_2
    //   392: istore_3
    //   393: goto -243 -> 150
    //   396: astore_0
    //   397: aconst_null
    //   398: astore 7
    //   400: aload 7
    //   402: ifnull +8 -> 410
    //   405: aload 7
    //   407: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   410: aload_0
    //   411: athrow
    //   412: astore 9
    //   414: aload 10
    //   416: astore 8
    //   418: aload 8
    //   420: astore 7
    //   422: aload_0
    //   423: new 124	java/lang/StringBuilder
    //   426: dup
    //   427: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   430: ldc_w 1607
    //   433: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: aload 9
    //   438: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   441: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: ldc_w 1516
    //   447: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   453: invokestatic 1504	com/baloota/dumpster/logger/a:b	(Landroid/content/Context;Ljava/lang/String;)V
    //   456: iload 5
    //   458: istore_2
    //   459: aload 8
    //   461: ifnull -138 -> 323
    //   464: aload 8
    //   466: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   469: iconst_0
    //   470: ireturn
    //   471: astore_0
    //   472: iconst_0
    //   473: ireturn
    //   474: astore_0
    //   475: aload 7
    //   477: ifnull +8 -> 485
    //   480: aload 7
    //   482: invokevirtual 1015	java/io/BufferedReader:close	()V
    //   485: aload_0
    //   486: athrow
    //   487: astore 7
    //   489: iload 4
    //   491: istore_1
    //   492: iload_2
    //   493: istore_3
    //   494: goto -344 -> 150
    //   497: astore 7
    //   499: goto -89 -> 410
    //   502: astore_0
    //   503: iload_1
    //   504: ireturn
    //   505: astore 7
    //   507: goto -22 -> 485
    //   510: astore_0
    //   511: aload 8
    //   513: astore 7
    //   515: goto -40 -> 475
    //   518: astore 9
    //   520: goto -102 -> 418
    //   523: astore_0
    //   524: goto -124 -> 400
    //   527: astore 8
    //   529: iconst_0
    //   530: istore_2
    //   531: goto -199 -> 332
    //   534: astore 8
    //   536: goto -204 -> 332
    //   539: iconst_0
    //   540: istore_1
    //   541: iload_2
    //   542: istore_3
    //   543: goto -393 -> 150
    //   546: iconst_0
    //   547: istore_2
    //   548: goto -439 -> 109
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	551	0	paramContext	Context
    //   132	409	1	i	int
    //   108	440	2	j	int
    //   134	409	3	k	int
    //   128	362	4	m	int
    //   7	450	5	n	int
    //   10	284	6	i1	int
    //   49	286	7	localObject1	Object
    //   387	1	7	localException1	Exception
    //   398	83	7	localObject2	Object
    //   487	1	7	localException2	Exception
    //   497	1	7	localException3	Exception
    //   505	1	7	localException4	Exception
    //   513	1	7	localObject3	Object
    //   21	296	8	localObject4	Object
    //   325	26	8	localException5	Exception
    //   416	96	8	localObject5	Object
    //   527	1	8	localException6	Exception
    //   534	1	8	localException7	Exception
    //   45	331	9	localBufferedReader	java.io.BufferedReader
    //   412	25	9	localException8	Exception
    //   518	1	9	localException9	Exception
    //   4	411	10	localObject6	Object
    //   1	232	11	localObject7	Object
    //   56	46	12	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   12	47	325	java/lang/Exception
    //   375	380	387	java/lang/Exception
    //   12	47	396	finally
    //   236	257	412	java/lang/Exception
    //   464	469	471	java/lang/Exception
    //   236	257	474	finally
    //   422	456	474	finally
    //   140	145	487	java/lang/Exception
    //   405	410	497	java/lang/Exception
    //   316	321	502	java/lang/Exception
    //   480	485	505	java/lang/Exception
    //   257	264	510	finally
    //   272	293	510	finally
    //   301	309	510	finally
    //   257	264	518	java/lang/Exception
    //   272	293	518	java/lang/Exception
    //   301	309	518	java/lang/Exception
    //   51	58	523	finally
    //   67	78	523	finally
    //   82	92	523	finally
    //   101	109	523	finally
    //   113	119	523	finally
    //   123	130	523	finally
    //   336	370	523	finally
    //   51	58	527	java/lang/Exception
    //   67	78	527	java/lang/Exception
    //   82	92	527	java/lang/Exception
    //   101	109	527	java/lang/Exception
    //   113	119	534	java/lang/Exception
    //   123	130	534	java/lang/Exception
  }
  
  /* Error */
  public static boolean v(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: invokevirtual 1098	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: getstatic 1613	com/baloota/dumpster/DumpsterContentProvider:a	Landroid/net/Uri;
    //   13: iconst_1
    //   14: anewarray 58	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc_w 1615
    //   22: aastore
    //   23: ldc_w 1617
    //   26: iconst_4
    //   27: anewarray 58	java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: iconst_0
    //   33: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: iconst_1
    //   40: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   43: aastore
    //   44: dup
    //   45: iconst_2
    //   46: iconst_4
    //   47: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   50: aastore
    //   51: dup
    //   52: iconst_3
    //   53: iconst_5
    //   54: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   57: aastore
    //   58: aconst_null
    //   59: invokevirtual 1108	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 6
    //   64: aload 6
    //   66: astore 4
    //   68: aload 4
    //   70: ifnull +177 -> 247
    //   73: aload 4
    //   75: astore 5
    //   77: aload 4
    //   79: invokeinterface 1622 1 0
    //   84: istore_1
    //   85: iload_1
    //   86: ifeq +161 -> 247
    //   89: aload 4
    //   91: astore 5
    //   93: aload 4
    //   95: iconst_0
    //   96: invokeinterface 1120 2 0
    //   101: lstore_2
    //   102: aload 4
    //   104: ifnull +140 -> 244
    //   107: aload 4
    //   109: invokeinterface 1131 1 0
    //   114: lload_2
    //   115: lconst_0
    //   116: lcmp
    //   117: ifne +98 -> 215
    //   120: iconst_1
    //   121: ireturn
    //   122: astore_0
    //   123: lconst_0
    //   124: lstore_2
    //   125: goto -23 -> 102
    //   128: astore 6
    //   130: aconst_null
    //   131: astore 4
    //   133: aload 4
    //   135: astore 5
    //   137: aload_0
    //   138: aload 6
    //   140: invokevirtual 1623	android/database/SQLException:getMessage	()Ljava/lang/String;
    //   143: aload 6
    //   145: iconst_0
    //   146: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   149: aload 4
    //   151: ifnull +88 -> 239
    //   154: aload 4
    //   156: invokeinterface 1131 1 0
    //   161: lconst_0
    //   162: lstore_2
    //   163: goto -49 -> 114
    //   166: astore 6
    //   168: aload 5
    //   170: astore 4
    //   172: aload_0
    //   173: aload 6
    //   175: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   178: aload 6
    //   180: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   183: aload 5
    //   185: ifnull +54 -> 239
    //   188: aload 5
    //   190: invokeinterface 1131 1 0
    //   195: lconst_0
    //   196: lstore_2
    //   197: goto -83 -> 114
    //   200: astore_0
    //   201: aload 4
    //   203: ifnull +10 -> 213
    //   206: aload 4
    //   208: invokeinterface 1131 1 0
    //   213: aload_0
    //   214: athrow
    //   215: iconst_0
    //   216: ireturn
    //   217: astore_0
    //   218: aload 5
    //   220: astore 4
    //   222: goto -21 -> 201
    //   225: astore 6
    //   227: aload 4
    //   229: astore 5
    //   231: goto -63 -> 168
    //   234: astore 6
    //   236: goto -103 -> 133
    //   239: lconst_0
    //   240: lstore_2
    //   241: goto -127 -> 114
    //   244: goto -130 -> 114
    //   247: lconst_0
    //   248: lstore_2
    //   249: goto -147 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	paramContext	Context
    //   84	2	1	bool	boolean
    //   101	148	2	l	long
    //   1	227	4	localObject1	Object
    //   4	226	5	localObject2	Object
    //   62	3	6	localCursor	Cursor
    //   128	16	6	localSQLException1	android.database.SQLException
    //   166	13	6	localException1	Exception
    //   225	1	6	localException2	Exception
    //   234	1	6	localSQLException2	android.database.SQLException
    // Exception table:
    //   from	to	target	type
    //   93	102	122	java/lang/Exception
    //   6	64	128	android/database/SQLException
    //   6	64	166	java/lang/Exception
    //   6	64	200	finally
    //   172	183	200	finally
    //   77	85	217	finally
    //   93	102	217	finally
    //   137	149	217	finally
    //   77	85	225	java/lang/Exception
    //   77	85	234	android/database/SQLException
    //   93	102	234	android/database/SQLException
  }
  
  /* Error */
  public static long w(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: invokevirtual 1098	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: getstatic 1613	com/baloota/dumpster/DumpsterContentProvider:a	Landroid/net/Uri;
    //   13: iconst_1
    //   14: anewarray 58	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc_w 1626
    //   22: aastore
    //   23: ldc_w 1628
    //   26: iconst_1
    //   27: anewarray 58	java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: iconst_0
    //   33: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   36: aastore
    //   37: aconst_null
    //   38: invokevirtual 1108	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +165 -> 210
    //   48: aload 4
    //   50: invokeinterface 1113 1 0
    //   55: istore_1
    //   56: iload_1
    //   57: ifeq +153 -> 210
    //   60: aload 4
    //   62: iconst_0
    //   63: invokeinterface 1120 2 0
    //   68: lstore_2
    //   69: aload 4
    //   71: ifnull +10 -> 81
    //   74: aload 4
    //   76: invokeinterface 1131 1 0
    //   81: lload_2
    //   82: lreturn
    //   83: astore_0
    //   84: ldc2_w 817
    //   87: lstore_2
    //   88: goto -19 -> 69
    //   91: astore 5
    //   93: aconst_null
    //   94: astore 4
    //   96: aload_0
    //   97: aload 5
    //   99: invokevirtual 1623	android/database/SQLException:getMessage	()Ljava/lang/String;
    //   102: aload 5
    //   104: iconst_0
    //   105: invokestatic 107	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   108: aload 4
    //   110: ifnull +96 -> 206
    //   113: aload 4
    //   115: invokeinterface 1131 1 0
    //   120: ldc2_w 817
    //   123: lreturn
    //   124: astore 5
    //   126: aload 6
    //   128: astore 4
    //   130: aload 5
    //   132: astore 6
    //   134: aload 4
    //   136: astore 5
    //   138: aload_0
    //   139: aload 6
    //   141: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   144: aload 6
    //   146: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   149: aload 4
    //   151: ifnull +55 -> 206
    //   154: aload 4
    //   156: invokeinterface 1131 1 0
    //   161: ldc2_w 817
    //   164: lreturn
    //   165: astore_0
    //   166: aload 5
    //   168: ifnull +10 -> 178
    //   171: aload 5
    //   173: invokeinterface 1131 1 0
    //   178: aload_0
    //   179: athrow
    //   180: astore_0
    //   181: aload 4
    //   183: astore 5
    //   185: goto -19 -> 166
    //   188: astore_0
    //   189: aload 4
    //   191: astore 5
    //   193: goto -27 -> 166
    //   196: astore 6
    //   198: goto -64 -> 134
    //   201: astore 5
    //   203: goto -107 -> 96
    //   206: ldc2_w 817
    //   209: lreturn
    //   210: ldc2_w 817
    //   213: lstore_2
    //   214: goto -145 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	paramContext	Context
    //   55	2	1	bool	boolean
    //   68	146	2	l	long
    //   41	149	4	localObject1	Object
    //   1	1	5	localObject2	Object
    //   91	12	5	localSQLException1	android.database.SQLException
    //   124	7	5	localException1	Exception
    //   136	56	5	localObject3	Object
    //   201	1	5	localSQLException2	android.database.SQLException
    //   4	141	6	localException2	Exception
    //   196	1	6	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   60	69	83	java/lang/Exception
    //   6	43	91	android/database/SQLException
    //   6	43	124	java/lang/Exception
    //   6	43	165	finally
    //   138	149	165	finally
    //   48	56	180	finally
    //   60	69	180	finally
    //   96	108	188	finally
    //   48	56	196	java/lang/Exception
    //   48	56	201	android/database/SQLException
    //   60	69	201	android/database/SQLException
  }
  
  /* Error */
  public static long x(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: invokevirtual 1098	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: getstatic 1632	com/baloota/dumpster/handler/files/FileSystemContentProvider:a	Landroid/net/Uri;
    //   13: iconst_1
    //   14: anewarray 58	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc_w 1626
    //   22: aastore
    //   23: ldc_w 1634
    //   26: iconst_3
    //   27: anewarray 58	java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: iconst_0
    //   33: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: iconst_1
    //   40: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   43: aastore
    //   44: dup
    //   45: iconst_2
    //   46: iconst_4
    //   47: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   50: aastore
    //   51: aconst_null
    //   52: invokevirtual 1108	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   55: astore 4
    //   57: aload 4
    //   59: ifnull +164 -> 223
    //   62: aload 4
    //   64: invokeinterface 1113 1 0
    //   69: istore_1
    //   70: iload_1
    //   71: ifeq +152 -> 223
    //   74: aload 4
    //   76: iconst_0
    //   77: invokeinterface 1120 2 0
    //   82: lstore_2
    //   83: aload 4
    //   85: ifnull +10 -> 95
    //   88: aload 4
    //   90: invokeinterface 1131 1 0
    //   95: lload_2
    //   96: lreturn
    //   97: astore_0
    //   98: ldc2_w 817
    //   101: lstore_2
    //   102: goto -19 -> 83
    //   105: astore 5
    //   107: aconst_null
    //   108: astore 4
    //   110: aload_0
    //   111: aload 5
    //   113: invokevirtual 1623	android/database/SQLException:getMessage	()Ljava/lang/String;
    //   116: aload 5
    //   118: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   121: aload 4
    //   123: ifnull +96 -> 219
    //   126: aload 4
    //   128: invokeinterface 1131 1 0
    //   133: ldc2_w 817
    //   136: lreturn
    //   137: astore 5
    //   139: aload 6
    //   141: astore 4
    //   143: aload 5
    //   145: astore 6
    //   147: aload 4
    //   149: astore 5
    //   151: aload_0
    //   152: aload 6
    //   154: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   157: aload 6
    //   159: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   162: aload 4
    //   164: ifnull +55 -> 219
    //   167: aload 4
    //   169: invokeinterface 1131 1 0
    //   174: ldc2_w 817
    //   177: lreturn
    //   178: astore_0
    //   179: aload 5
    //   181: ifnull +10 -> 191
    //   184: aload 5
    //   186: invokeinterface 1131 1 0
    //   191: aload_0
    //   192: athrow
    //   193: astore_0
    //   194: aload 4
    //   196: astore 5
    //   198: goto -19 -> 179
    //   201: astore_0
    //   202: aload 4
    //   204: astore 5
    //   206: goto -27 -> 179
    //   209: astore 6
    //   211: goto -64 -> 147
    //   214: astore 5
    //   216: goto -106 -> 110
    //   219: ldc2_w 817
    //   222: lreturn
    //   223: ldc2_w 817
    //   226: lstore_2
    //   227: goto -144 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	paramContext	Context
    //   69	2	1	bool	boolean
    //   82	145	2	l	long
    //   55	148	4	localObject1	Object
    //   1	1	5	localObject2	Object
    //   105	12	5	localSQLException1	android.database.SQLException
    //   137	7	5	localException1	Exception
    //   149	56	5	localObject3	Object
    //   214	1	5	localSQLException2	android.database.SQLException
    //   4	154	6	localException2	Exception
    //   209	1	6	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   74	83	97	java/lang/Exception
    //   6	57	105	android/database/SQLException
    //   6	57	137	java/lang/Exception
    //   6	57	178	finally
    //   151	162	178	finally
    //   62	70	193	finally
    //   74	83	193	finally
    //   110	121	201	finally
    //   62	70	209	java/lang/Exception
    //   62	70	214	android/database/SQLException
    //   74	83	214	android/database/SQLException
  }
  
  /* Error */
  public static long y(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: invokevirtual 1098	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: getstatic 1632	com/baloota/dumpster/handler/files/FileSystemContentProvider:a	Landroid/net/Uri;
    //   13: iconst_1
    //   14: anewarray 58	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc_w 1626
    //   22: aastore
    //   23: ldc_w 1637
    //   26: iconst_1
    //   27: anewarray 58	java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: iconst_5
    //   33: invokestatic 1619	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   36: aastore
    //   37: aconst_null
    //   38: invokevirtual 1108	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +164 -> 209
    //   48: aload 4
    //   50: invokeinterface 1113 1 0
    //   55: istore_1
    //   56: iload_1
    //   57: ifeq +152 -> 209
    //   60: aload 4
    //   62: iconst_0
    //   63: invokeinterface 1120 2 0
    //   68: lstore_2
    //   69: aload 4
    //   71: ifnull +10 -> 81
    //   74: aload 4
    //   76: invokeinterface 1131 1 0
    //   81: lload_2
    //   82: lreturn
    //   83: astore_0
    //   84: ldc2_w 817
    //   87: lstore_2
    //   88: goto -19 -> 69
    //   91: astore 5
    //   93: aconst_null
    //   94: astore 4
    //   96: aload_0
    //   97: aload 5
    //   99: invokevirtual 1623	android/database/SQLException:getMessage	()Ljava/lang/String;
    //   102: aload 5
    //   104: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: aload 4
    //   109: ifnull +96 -> 205
    //   112: aload 4
    //   114: invokeinterface 1131 1 0
    //   119: ldc2_w 817
    //   122: lreturn
    //   123: astore 5
    //   125: aload 6
    //   127: astore 4
    //   129: aload 5
    //   131: astore 6
    //   133: aload 4
    //   135: astore 5
    //   137: aload_0
    //   138: aload 6
    //   140: invokevirtual 102	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   143: aload 6
    //   145: invokestatic 227	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   148: aload 4
    //   150: ifnull +55 -> 205
    //   153: aload 4
    //   155: invokeinterface 1131 1 0
    //   160: ldc2_w 817
    //   163: lreturn
    //   164: astore_0
    //   165: aload 5
    //   167: ifnull +10 -> 177
    //   170: aload 5
    //   172: invokeinterface 1131 1 0
    //   177: aload_0
    //   178: athrow
    //   179: astore_0
    //   180: aload 4
    //   182: astore 5
    //   184: goto -19 -> 165
    //   187: astore_0
    //   188: aload 4
    //   190: astore 5
    //   192: goto -27 -> 165
    //   195: astore 6
    //   197: goto -64 -> 133
    //   200: astore 5
    //   202: goto -106 -> 96
    //   205: ldc2_w 817
    //   208: lreturn
    //   209: ldc2_w 817
    //   212: lstore_2
    //   213: goto -144 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	216	0	paramContext	Context
    //   55	2	1	bool	boolean
    //   68	145	2	l	long
    //   41	148	4	localObject1	Object
    //   1	1	5	localObject2	Object
    //   91	12	5	localSQLException1	android.database.SQLException
    //   123	7	5	localException1	Exception
    //   135	56	5	localObject3	Object
    //   200	1	5	localSQLException2	android.database.SQLException
    //   4	140	6	localException2	Exception
    //   195	1	6	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   60	69	83	java/lang/Exception
    //   6	43	91	android/database/SQLException
    //   6	43	123	java/lang/Exception
    //   6	43	164	finally
    //   137	148	164	finally
    //   48	56	179	finally
    //   60	69	179	finally
    //   96	107	187	finally
    //   48	56	195	java/lang/Exception
    //   48	56	200	android/database/SQLException
    //   60	69	200	android/database/SQLException
  }
  
  public static void z(Context paramContext)
  {
    Object localObject1 = com.baloota.dumpster.preferences.a.C(paramContext);
    if ((localObject1 == null) || (System.currentTimeMillis() > ((GeoIp)localObject1).getLast_update() + 604800000L)) {}
    try
    {
      localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
      if (localObject1 == null) {
        break label95;
      }
      localObject1 = ((TelephonyManager)localObject1).getSimCountryIso();
      if ((localObject1 == null) || (((String)localObject1).length() != 2)) {
        break label95;
      }
      localObject1 = ((String)localObject1).toUpperCase();
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        com.baloota.dumpster.logger.a.a(paramContext, localException1.getMessage(), localException1, false);
        label95:
        Object localObject2 = null;
      }
      try
      {
        ((em)new Retrofit.Builder().baseUrl("http://www.telize.com").addConverterFactory(GsonConverterFactory.create()).build().create(em.class)).a().enqueue(new Callback()
        {
          public void onFailure(Throwable paramAnonymousThrowable)
          {
            com.baloota.dumpster.logger.a.a(this.a, "Get GeoIP failed: " + paramAnonymousThrowable, paramAnonymousThrowable, false);
            try
            {
              String str = this.a.getResources().getConfiguration().locale.getCountry();
              com.baloota.dumpster.preferences.a.a(this.a, new GeoIp(System.currentTimeMillis(), str));
              return;
            }
            catch (Exception localException)
            {
              com.baloota.dumpster.logger.a.a(this.a, paramAnonymousThrowable.getMessage(), paramAnonymousThrowable, false);
            }
          }
          
          public void onResponse(Response<GeoIp> paramAnonymousResponse, Retrofit paramAnonymousRetrofit)
          {
            if (paramAnonymousResponse != null)
            {
              paramAnonymousResponse = (GeoIp)paramAnonymousResponse.body();
              if (paramAnonymousResponse != null)
              {
                paramAnonymousResponse.setLast_update(System.currentTimeMillis());
                com.baloota.dumpster.preferences.a.a(this.a, paramAnonymousResponse);
              }
            }
          }
        });
        return;
      }
      catch (Exception localException2)
      {
        com.baloota.dumpster.logger.a.a(paramContext, b, "Failed to get GeoIp: " + localException2, localException2);
      }
    }
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      com.baloota.dumpster.preferences.a.a(paramContext, new GeoIp(System.currentTimeMillis(), (String)localObject1));
      return;
    }
  }
}
