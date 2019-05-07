import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.callrecord.auto.AlertActivity;
import com.treeview.folder.UtilsFun;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class ow
{
  public static RelativeLayout a;
  private static String b = "V2_SHARE_IS_FIRST_TIME";
  private static String c = "V2_SHARE_SPEC_DEVICES";
  private static String d = "V2_PREFERENCE_CALL_RECORDER_VALUE_IS_SPEC_DEVICE";
  
  public ow() {}
  
  public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.min(paramFloat3, Math.max(paramFloat2, paramFloat1));
  }
  
  /* Error */
  public static long a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_1
    //   4: invokestatic 47	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore_1
    //   8: new 49	java/util/Random
    //   11: dup
    //   12: invokespecial 50	java/util/Random:<init>	()V
    //   15: invokevirtual 54	java/util/Random:nextInt	()I
    //   18: i2l
    //   19: lstore_2
    //   20: aload_0
    //   21: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   24: getstatic 66	android/provider/ContactsContract$PhoneLookup:CONTENT_FILTER_URI	Landroid/net/Uri;
    //   27: aload_1
    //   28: invokestatic 70	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   31: iconst_2
    //   32: anewarray 72	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc 74
    //   39: aastore
    //   40: dup
    //   41: iconst_1
    //   42: ldc 76
    //   44: aastore
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   51: astore_0
    //   52: aload_0
    //   53: invokeinterface 88 1 0
    //   58: ifeq +25 -> 83
    //   61: aload_0
    //   62: aload_0
    //   63: ldc 76
    //   65: invokeinterface 92 2 0
    //   70: invokeinterface 96 2 0
    //   75: lstore 4
    //   77: lload 4
    //   79: lstore_2
    //   80: goto -28 -> 52
    //   83: lload_2
    //   84: lstore 4
    //   86: aload_0
    //   87: ifnull +12 -> 99
    //   90: aload_0
    //   91: invokeinterface 99 1 0
    //   96: lload_2
    //   97: lstore 4
    //   99: lload 4
    //   101: lreturn
    //   102: astore_0
    //   103: aconst_null
    //   104: astore_0
    //   105: lload_2
    //   106: lstore 4
    //   108: aload_0
    //   109: ifnull -10 -> 99
    //   112: aload_0
    //   113: invokeinterface 99 1 0
    //   118: lload_2
    //   119: lreturn
    //   120: astore_0
    //   121: aload 6
    //   123: astore_1
    //   124: aload_1
    //   125: ifnull +9 -> 134
    //   128: aload_1
    //   129: invokeinterface 99 1 0
    //   134: aload_0
    //   135: athrow
    //   136: astore 6
    //   138: aload_0
    //   139: astore_1
    //   140: aload 6
    //   142: astore_0
    //   143: goto -19 -> 124
    //   146: astore_1
    //   147: goto -42 -> 105
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramContext	Context
    //   0	150	1	paramString	String
    //   19	100	2	l1	long
    //   75	32	4	l2	long
    //   1	121	6	localObject1	Object
    //   136	5	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   20	52	102	java/lang/Exception
    //   20	52	120	finally
    //   52	77	136	finally
    //   52	77	146	java/lang/Exception
  }
  
  public static Bitmap a(Context paramContext, String paramString, boolean paramBoolean)
  {
    Object localObject = Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, a(paramContext, paramString)), "photo");
    paramString = BitmapFactory.decodeResource(paramContext.getResources(), 2131231182);
    try
    {
      paramContext = Bitmap.createScaledBitmap(MediaStore.Images.Media.getBitmap(paramContext.getContentResolver(), (Uri)localObject), paramString.getWidth(), paramString.getHeight(), false);
      int i = paramString.getHeight();
      paramString = Bitmap.createBitmap(paramContext.getWidth(), paramContext.getHeight(), Bitmap.Config.ARGB_8888);
      localObject = new Canvas(paramString);
      Paint localPaint = new Paint();
      Rect localRect = new Rect(0, 0, paramContext.getWidth(), paramContext.getHeight());
      RectF localRectF = new RectF(localRect);
      float f = i;
      localPaint.setAntiAlias(true);
      ((Canvas)localObject).drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      ((Canvas)localObject).drawRoundRect(localRectF, f, f, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      ((Canvas)localObject).drawBitmap(paramContext, localRect, localRect, localPaint);
      return paramString;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = paramString;
      }
    }
  }
  
  public static Boolean a(String paramString, ArrayList<String> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (((String)paramArrayList.next()).equals(paramString)) {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static String a(Context paramContext)
  {
    paramContext = new File(ma.a(paramContext));
    if (!paramContext.exists()) {}
    for (;;)
    {
      try
      {
        paramContext.mkdirs();
        Log.d("dir.getAbsolutePath()", paramContext.getAbsolutePath());
        return paramContext.getAbsolutePath();
      }
      catch (Exception paramContext) {}
      if (!paramContext.canWrite()) {
        return null;
      }
    }
    return null;
  }
  
  @SuppressLint({"NewApi"})
  public static String a(String paramString)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 10)
      {
        MediaMetadataRetriever localMediaMetadataRetriever = new MediaMetadataRetriever();
        localMediaMetadataRetriever.setDataSource(paramString);
        long l = Long.parseLong(localMediaMetadataRetriever.extractMetadata(9)) / 1000L;
        if (l > 3600L) {
          return String.format(Locale.US, "%02d:%02d:%02d", new Object[] { Long.valueOf(l / 3600L), Long.valueOf(l / 60L % 60L), Long.valueOf(l % 60L) });
        }
        paramString = String.format(Locale.US, "%02d:%02d", new Object[] { Long.valueOf(l / 60L % 60L), Long.valueOf(l % 60L) });
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static void a(Activity paramActivity)
  {
    new AlertDialog.Builder(paramActivity).setMessage(paramActivity.getString(2131755313)).setPositiveButton(paramActivity.getString(17039370), new ox(paramActivity)).setNegativeButton(paramActivity.getString(17039360), null).create().show();
  }
  
  public static void a(Service paramService)
  {
    WindowManager.LayoutParams localLayoutParams;
    WindowManager localWindowManager;
    if ((a == null) && (!AlertActivity.a))
    {
      int i = paramService.getResources().getDisplayMetrics().widthPixels;
      localLayoutParams = new WindowManager.LayoutParams(-1, -1, 2010, 384, -3);
      localWindowManager = (WindowManager)paramService.getSystemService("window");
      oy localOy = new oy(paramService, paramService);
      a = localOy;
      Object localObject = View.inflate(paramService, 2131492932, localOy);
      Typeface localTypeface2 = Typeface.createFromAsset(paramService.getAssets(), "fonts/sf-ui-text-medium.ttf");
      Typeface localTypeface1 = Typeface.createFromAsset(paramService.getAssets(), "fonts/sf-ui-text-regular.ttf");
      TextView localTextView1 = (TextView)((View)localObject).findViewById(2131296296);
      TextView localTextView2 = (TextView)((View)localObject).findViewById(2131296403);
      localTextView2.setText(paramService.getResources().getString(2131755051));
      localTextView1.setTypeface(localTypeface2);
      localTextView2.setTypeface(localTypeface1);
      localObject = (Button)((View)localObject).findViewById(2131296341);
      ((Button)localObject).setTypeface(localTypeface1);
      ((Button)localObject).setOnClickListener(new oz(paramService, localOy));
    }
    try
    {
      localWindowManager.addView(a, localLayoutParams);
      return;
    }
    catch (Exception localException)
    {
      Intent localIntent = new Intent(paramService, AlertActivity.class);
      localIntent.setFlags(268435456);
      paramService.startActivity(localIntent);
      if (ma.k(paramService)) {
        ov.a(paramService, null, Boolean.valueOf(false));
      }
      paramService.stopForeground(true);
      paramService.stopSelf();
    }
  }
  
  public static Boolean b(String paramString, ArrayList<String> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (((String)paramArrayList.next()).equals(paramString)) {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
  
  /* Error */
  public static String b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: getstatic 66	android/provider/ContactsContract$PhoneLookup:CONTENT_FILTER_URI	Landroid/net/Uri;
    //   9: aload_1
    //   10: invokestatic 47	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   13: invokestatic 70	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   16: iconst_1
    //   17: anewarray 72	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: ldc 74
    //   24: aastore
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore_0
    //   32: aload_0
    //   33: ifnonnull +19 -> 52
    //   36: aload_1
    //   37: astore_3
    //   38: aload_0
    //   39: ifnull +11 -> 50
    //   42: aload_0
    //   43: invokeinterface 99 1 0
    //   48: aload_1
    //   49: astore_3
    //   50: aload_3
    //   51: areturn
    //   52: aload_1
    //   53: astore_2
    //   54: aload_0
    //   55: invokeinterface 486 1 0
    //   60: ifeq +18 -> 78
    //   63: aload_0
    //   64: aload_0
    //   65: ldc 74
    //   67: invokeinterface 489 2 0
    //   72: invokeinterface 490 2 0
    //   77: astore_2
    //   78: aload_2
    //   79: astore_3
    //   80: aload_0
    //   81: ifnull -31 -> 50
    //   84: aload_0
    //   85: invokeinterface 99 1 0
    //   90: aload_2
    //   91: areturn
    //   92: astore_0
    //   93: aconst_null
    //   94: astore_0
    //   95: aload_1
    //   96: astore_3
    //   97: aload_0
    //   98: ifnull -48 -> 50
    //   101: aload_0
    //   102: invokeinterface 99 1 0
    //   107: aload_1
    //   108: areturn
    //   109: astore_1
    //   110: aload_2
    //   111: astore_0
    //   112: aload_0
    //   113: ifnull +9 -> 122
    //   116: aload_0
    //   117: invokeinterface 99 1 0
    //   122: aload_1
    //   123: athrow
    //   124: astore_1
    //   125: goto -13 -> 112
    //   128: astore_2
    //   129: goto -34 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	paramContext	Context
    //   0	132	1	paramString	String
    //   1	110	2	str1	String
    //   128	1	2	localException	Exception
    //   37	60	3	str2	String
    // Exception table:
    //   from	to	target	type
    //   2	32	92	java/lang/Exception
    //   2	32	109	finally
    //   54	78	124	finally
    //   54	78	128	java/lang/Exception
  }
  
  public static String b(String paramString)
  {
    long l = new File(paramString).length();
    if (l < 1024L) {
      return l + " B";
    }
    int i = (63 - Long.numberOfLeadingZeros(l)) / 10;
    return String.format("%.1f %sB", new Object[] { Double.valueOf(l / (1L << i * 10)), Character.valueOf(" KMGTPE".charAt(i)) });
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = UtilsFun.readableFileSize(UtilsFun.getAvailableExternalMemorySize(ma.b(paramContext)));
      if (paramContext.equals("0")) {
        break label106;
      }
      paramContext = paramContext.trim().split(" ");
      if (paramContext[1].trim().equals("B")) {
        break label106;
      }
      if (paramContext[1].trim().equals("kB")) {
        return false;
      }
      boolean bool = paramContext[1].trim().equals("MB");
      if (!bool) {}
    }
    catch (Exception paramContext)
    {
      float f;
      label97:
      return false;
    }
    try
    {
      f = Float.parseFloat(paramContext[0].trim());
      if (f < 1.0F) {
        break label106;
      }
    }
    catch (Exception paramContext)
    {
      break label97;
    }
    return true;
    label106:
    return false;
  }
  
  public static boolean c(Context paramContext)
  {
    return paramContext.getSharedPreferences(d, 0).getBoolean(c, false);
  }
  
  public static boolean c(String paramString)
  {
    return new File(paramString).delete();
  }
  
  public static String d(String paramString)
  {
    Object localObject = new SimpleDateFormat("dd MM yyyy");
    try
    {
      Date localDate = ((SimpleDateFormat)localObject).parse(paramString);
      ((SimpleDateFormat)localObject).applyPattern("dd MMM yyyy");
      localObject = ((SimpleDateFormat)localObject).format(localDate);
      return localObject;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return paramString;
  }
  
  public static boolean d(Context paramContext)
  {
    boolean bool = false;
    Object localObject = paramContext.getSharedPreferences(d, 0);
    if (((SharedPreferences)localObject).getBoolean(b, false)) {
      return true;
    }
    localObject = ((SharedPreferences)localObject).edit();
    String str1 = Build.HARDWARE.toLowerCase(Locale.US);
    String str2 = Build.BOARD.toLowerCase(Locale.US);
    if ((str2.contains("msm")) || (str2.contains("kirin")) || (str2.contains("hi62")) || (str2.contains("Exynos".toLowerCase(Locale.US))) || (str2.contains("universal")) || (str2.contains("U8420".toLowerCase(Locale.US))) || (str2.contains("APQ".toLowerCase(Locale.US))) || (str2.contains("SC7727S".toLowerCase(Locale.US))) || (str2.contains("mt")) || (str1.contains("mt67")))
    {
      ((SharedPreferences.Editor)localObject).putBoolean(c, true);
      ma.a(paramContext, 4);
      bool = true;
    }
    for (;;)
    {
      ((SharedPreferences.Editor)localObject).putBoolean(b, true);
      ((SharedPreferences.Editor)localObject).commit();
      return bool;
      ((SharedPreferences.Editor)localObject).putBoolean(c, false);
    }
  }
  
  public static String e(String paramString)
  {
    paramString = paramString.trim();
    paramString.replaceAll(" ", "");
    while (paramString.indexOf(" ") != -1) {
      paramString = paramString.replaceAll(" ", "");
    }
    return paramString;
  }
  
  public static boolean e(Context paramContext)
  {
    boolean bool = true;
    int i = 0;
    if (!jq.b) {
      return false;
    }
    paramContext = paramContext.getApplicationContext().getSharedPreferences("COUNT_DELETE_KEY", 0);
    SharedPreferences.Editor localEditor = paramContext.edit();
    int j = paramContext.getInt("COUNT_DELETE_KEY", 0);
    if (j % 2 == 1)
    {
      j += 1;
      if (j != 20000) {
        break label88;
      }
    }
    for (;;)
    {
      localEditor.putInt("COUNT_DELETE_KEY", i);
      localEditor.commit();
      return bool;
      bool = false;
      break;
      label88:
      i = j;
    }
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals("com.callrecord.autopro")) {
        return true;
      }
    }
    return false;
  }
}
