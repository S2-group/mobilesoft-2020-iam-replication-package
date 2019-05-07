package com.fusionmedia.investing_base.controller;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;
import com.fusionmedia.investing_base.BaseInvestingApplication;
import com.fusionmedia.investing_base.SecurePreferences;
import com.fusionmedia.investing_base.controller.content_provider.InvestingContract.InstrumentCommentsDict;
import com.fusionmedia.investing_base.controller.content_provider.InvestingContract.ScreenDataDict;
import com.fusionmedia.investing_base.controller.content_provider.MetaDataHelper;
import com.fusionmedia.investing_base.l.a;
import com.fusionmedia.investing_base.l.f;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class q
{
  public static boolean A = false;
  public static boolean B = false;
  public static final List<Integer> C = Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(29), Integer.valueOf(31) });
  public static final List<Integer> D = Arrays.asList(new Integer[] { Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(32), Integer.valueOf(34), Integer.valueOf(35), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(39), Integer.valueOf(49) });
  public static final List<Integer> E = Arrays.asList(new Integer[] { Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(33), Integer.valueOf(38) });
  public static final List<Integer> F = Arrays.asList(new Integer[] { Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16) });
  public static final List<Integer> G = Arrays.asList(new Integer[] { Integer.valueOf(43), Integer.valueOf(44), Integer.valueOf(45), Integer.valueOf(46) });
  public static long H = -1L;
  public static boolean I = false;
  public static boolean J = false;
  public static boolean K = false;
  public static boolean L = false;
  public static boolean M = false;
  public static boolean N = false;
  public static int O = -1;
  public static boolean P = false;
  public static int Q = -1;
  public static int R = 1;
  public static Set<String> S = new HashSet();
  public static boolean T = false;
  public static String U = "";
  public static String V = "";
  public static int W = -1;
  public static boolean X = false;
  public static boolean Y = true;
  public static boolean Z = false;
  public static boolean a = false;
  public static int aa = 0;
  public static int ab = 0;
  public static int ac = 1;
  public static int ad = 0;
  public static int ae = 0;
  public static int af = -1;
  public static boolean ag = false;
  public static int ah = 0;
  public static boolean ai = true;
  public static double aj;
  public static String ak;
  public static long al = 0L;
  public static boolean b = false;
  public static boolean c = false;
  public static boolean d = false;
  public static boolean e = false;
  public static boolean f = false;
  public static boolean g = false;
  public static boolean h = false;
  public static long i = 0L;
  public static int j = 0;
  public static boolean k = false;
  public static boolean l = false;
  public static boolean m = false;
  public static boolean n = false;
  public static boolean o = false;
  public static boolean p = false;
  public static long q = -1L;
  public static volatile int r = 0;
  public static String s = "$";
  public static boolean t = false;
  public static boolean u = true;
  public static boolean v = false;
  public static boolean w = false;
  public static boolean x = false;
  public static final Character y = Character.valueOf('‏');
  public static final Locale z = new Locale("en", Locale.getDefault().getCountry());
  
  public static int a(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static long a(long paramLong, Context paramContext)
  {
    paramContext = Calendar.getInstance();
    paramContext.set(1, Integer.valueOf(a(paramLong, "yyyy")).intValue());
    paramContext.set(2, Integer.valueOf(a(paramLong, "MM")).intValue() - 1);
    paramContext.set(5, Integer.valueOf(a(paramLong, "dd")).intValue());
    paramContext.set(11, 0);
    paramContext.set(12, 0);
    paramContext.set(13, 0);
    paramContext.set(14, 0);
    return paramContext.getTimeInMillis();
  }
  
  public static long a(Cursor paramCursor)
  {
    return 1000L * paramCursor.getLong(paramCursor.getColumnIndex("comment_date"));
  }
  
  public static long a(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", z);
    Object localObject = null;
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString.getTime();
    }
    catch (ParseException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
  }
  
  public static long a(String paramString1, String paramString2)
  {
    paramString2 = new SimpleDateFormat(paramString2, z);
    try
    {
      long l1 = paramString2.parse(paramString1).getTime();
      return l1;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0L;
  }
  
  /* Error */
  public static Intent a(android.os.Bundle paramBundle, Activity paramActivity, MetaDataHelper paramMetaDataHelper)
  {
    // Byte code:
    //   0: new 348	android/content/Intent
    //   3: dup
    //   4: ldc_w 350
    //   7: invokespecial 353	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   10: astore_3
    //   11: aload_3
    //   12: ldc_w 355
    //   15: invokevirtual 359	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   18: pop
    //   19: aload_0
    //   20: ifnull +168 -> 188
    //   23: aload_0
    //   24: getstatic 363	com/fusionmedia/investing_base/controller/c:w	Ljava/lang/String;
    //   27: invokevirtual 369	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   30: ifnull +130 -> 160
    //   33: aload_3
    //   34: ldc_w 371
    //   37: aload_0
    //   38: getstatic 363	com/fusionmedia/investing_base/controller/c:w	Ljava/lang/String;
    //   41: invokevirtual 369	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   44: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   47: pop
    //   48: aload_0
    //   49: ifnull +158 -> 207
    //   52: aload_0
    //   53: getstatic 363	com/fusionmedia/investing_base/controller/c:w	Ljava/lang/String;
    //   56: invokevirtual 369	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: ifnull +142 -> 201
    //   62: aload_1
    //   63: getstatic 380	com/fusionmedia/investing_base/l$f:article_share_template	I
    //   66: bipush 6
    //   68: anewarray 4	java/lang/Object
    //   71: dup
    //   72: iconst_0
    //   73: aload_2
    //   74: getstatic 383	com/fusionmedia/investing_base/l$f:article_share_opening_text	I
    //   77: invokevirtual 389	com/fusionmedia/investing_base/controller/content_provider/MetaDataHelper:getTerm	(I)Ljava/lang/String;
    //   80: aastore
    //   81: dup
    //   82: iconst_1
    //   83: aload_0
    //   84: getstatic 363	com/fusionmedia/investing_base/controller/c:w	Ljava/lang/String;
    //   87: invokevirtual 369	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   90: aastore
    //   91: dup
    //   92: iconst_2
    //   93: aload_2
    //   94: getstatic 392	com/fusionmedia/investing_base/l$f:article_share_link_title	I
    //   97: invokevirtual 389	com/fusionmedia/investing_base/controller/content_provider/MetaDataHelper:getTerm	(I)Ljava/lang/String;
    //   100: aastore
    //   101: dup
    //   102: iconst_3
    //   103: aload_0
    //   104: getstatic 394	com/fusionmedia/investing_base/controller/c:y	Ljava/lang/String;
    //   107: invokevirtual 369	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   110: aastore
    //   111: dup
    //   112: iconst_4
    //   113: aload_2
    //   114: getstatic 397	com/fusionmedia/investing_base/l$f:article_share_download_text	I
    //   117: invokevirtual 389	com/fusionmedia/investing_base/controller/content_provider/MetaDataHelper:getTerm	(I)Ljava/lang/String;
    //   120: aastore
    //   121: dup
    //   122: iconst_5
    //   123: aload_2
    //   124: getstatic 400	com/fusionmedia/investing_base/l$f:article_share_link	I
    //   127: invokevirtual 389	com/fusionmedia/investing_base/controller/content_provider/MetaDataHelper:getTerm	(I)Ljava/lang/String;
    //   130: aastore
    //   131: invokevirtual 405	android/app/Activity:getString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   134: astore_1
    //   135: aload_0
    //   136: ifnull +109 -> 245
    //   139: aload_0
    //   140: getstatic 363	com/fusionmedia/investing_base/controller/c:w	Ljava/lang/String;
    //   143: invokevirtual 369	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   146: ifnull +74 -> 220
    //   149: aload_3
    //   150: ldc_w 407
    //   153: aload_1
    //   154: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   157: pop
    //   158: aload_3
    //   159: areturn
    //   160: aload_3
    //   161: ldc_w 371
    //   164: ldc -42
    //   166: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   169: pop
    //   170: goto -122 -> 48
    //   173: astore 4
    //   175: aload_3
    //   176: ldc_w 371
    //   179: ldc -42
    //   181: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   184: pop
    //   185: goto -137 -> 48
    //   188: aload_3
    //   189: ldc_w 371
    //   192: ldc -42
    //   194: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   197: pop
    //   198: goto -150 -> 48
    //   201: ldc -42
    //   203: astore_1
    //   204: goto -69 -> 135
    //   207: ldc -42
    //   209: astore_1
    //   210: goto -75 -> 135
    //   213: astore_1
    //   214: ldc -42
    //   216: astore_1
    //   217: goto -82 -> 135
    //   220: aload_3
    //   221: ldc_w 407
    //   224: ldc -42
    //   226: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   229: pop
    //   230: aload_3
    //   231: areturn
    //   232: astore_0
    //   233: aload_3
    //   234: ldc_w 407
    //   237: ldc -42
    //   239: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   242: pop
    //   243: aload_3
    //   244: areturn
    //   245: aload_3
    //   246: ldc_w 407
    //   249: ldc -42
    //   251: invokevirtual 375	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   254: pop
    //   255: aload_3
    //   256: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	257	0	paramBundle	android.os.Bundle
    //   0	257	1	paramActivity	Activity
    //   0	257	2	paramMetaDataHelper	MetaDataHelper
    //   10	246	3	localIntent	Intent
    //   173	1	4	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   23	48	173	java/lang/Exception
    //   160	170	173	java/lang/Exception
    //   188	198	173	java/lang/Exception
    //   52	135	213	java/lang/Exception
    //   139	158	232	java/lang/Exception
    //   220	230	232	java/lang/Exception
    //   245	255	232	java/lang/Exception
  }
  
  /* Error */
  public static android.os.Bundle a(Activity paramActivity, Long paramLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokevirtual 412	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   6: getstatic 418	com/fusionmedia/investing_base/controller/content_provider/InvestingContract$SpecificCalendarDict:CONTENT_URI	Landroid/net/Uri;
    //   9: aconst_null
    //   10: ldc_w 420
    //   13: iconst_1
    //   14: anewarray 422	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: aload_1
    //   20: invokestatic 425	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   23: aastore
    //   24: aconst_null
    //   25: invokevirtual 431	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore_1
    //   29: aload_1
    //   30: invokeinterface 435 1 0
    //   35: ifeq +89 -> 124
    //   38: new 365	android/os/Bundle
    //   41: dup
    //   42: invokespecial 436	android/os/Bundle:<init>	()V
    //   45: astore_0
    //   46: aload_0
    //   47: getstatic 363	com/fusionmedia/investing_base/controller/c:w	Ljava/lang/String;
    //   50: aload_1
    //   51: aload_1
    //   52: ldc_w 438
    //   55: invokeinterface 314 2 0
    //   60: invokeinterface 440 2 0
    //   65: invokevirtual 443	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: aload_0
    //   69: getstatic 394	com/fusionmedia/investing_base/controller/c:y	Ljava/lang/String;
    //   72: aload_1
    //   73: aload_1
    //   74: ldc_w 445
    //   77: invokeinterface 314 2 0
    //   82: invokeinterface 440 2 0
    //   87: invokevirtual 443	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: aload_0
    //   91: getstatic 447	com/fusionmedia/investing_base/controller/c:x	Ljava/lang/String;
    //   94: aload_1
    //   95: aload_1
    //   96: ldc_w 438
    //   99: invokeinterface 314 2 0
    //   104: invokeinterface 440 2 0
    //   109: invokevirtual 443	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   112: aload_1
    //   113: ifnull +9 -> 122
    //   116: aload_1
    //   117: invokeinterface 450 1 0
    //   122: aload_0
    //   123: areturn
    //   124: ldc_w 452
    //   127: ldc_w 454
    //   130: invokestatic 458	com/fusionmedia/investing_base/controller/d:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   133: aconst_null
    //   134: astore_0
    //   135: goto -23 -> 112
    //   138: astore_0
    //   139: aload_2
    //   140: astore_1
    //   141: aload_1
    //   142: ifnull +9 -> 151
    //   145: aload_1
    //   146: invokeinterface 450 1 0
    //   151: aload_0
    //   152: athrow
    //   153: astore_0
    //   154: goto -13 -> 141
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramActivity	Activity
    //   0	157	1	paramLong	Long
    //   1	139	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	29	138	finally
    //   29	112	153	finally
    //   124	133	153	finally
  }
  
  public static String a(long paramLong)
  {
    if (a(paramLong, "dd").equals(a(System.currentTimeMillis(), "dd"))) {}
    for (String str = "HH:mm:ss";; str = "dd/MM") {
      return a(paramLong, str);
    }
  }
  
  public static String a(long paramLong1, long paramLong2, long paramLong3, long paramLong4, Context paramContext)
  {
    paramContext = MetaDataHelper.getInstance(paramContext);
    if ((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() == 0L) && (Long.valueOf(paramLong3).longValue() == 0L) && (Long.valueOf(paramLong4).longValue() >= 0L) && (Long.valueOf(paramLong4).longValue() <= 59L))
    {
      if ((paramContext.getTerm(l.f.just_now) != null) && (!paramContext.getTerm(l.f.just_now).isEmpty()) && (!paramContext.getTerm(l.f.just_now).contains("_")) && (!paramContext.getTerm(l.f.minute_ago).equals("just_now"))) {
        return paramContext.getTerm(l.f.just_now);
      }
      return null;
    }
    if ((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() == 0L) && (Long.valueOf(paramLong3).longValue() == 1L))
    {
      if ((paramContext.getTerm(l.f.minute_ago) != null) && (!paramContext.getTerm(l.f.minute_ago).isEmpty()) && (!paramContext.getTerm(l.f.minute_ago).contains("_")) && (!paramContext.getTerm(l.f.minute_ago).equals("minute_ago"))) {
        return paramContext.getTerm(l.f.minute_ago);
      }
      return null;
    }
    if ((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() == 0L) && (Long.valueOf(paramLong3).longValue() > 1L) && (Long.valueOf(paramLong3).longValue() <= 59L))
    {
      if ((paramContext.getTerm(l.f.x_minutes_ago) != null) && (!paramContext.getTerm(l.f.x_minutes_ago).isEmpty()) && (!paramContext.getTerm(l.f.x_minutes_ago).contains("_")) && (!paramContext.getTerm(l.f.x_minutes_ago).equals("x_minutes_ago"))) {
        return paramContext.getTerm(l.f.x_minutes_ago).replace("%x%", String.valueOf(paramLong3));
      }
      return null;
    }
    if ((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() == 1L))
    {
      if ((paramContext.getTerm(l.f.hour_ago) != null) && (!paramContext.getTerm(l.f.hour_ago).isEmpty()) && (!paramContext.getTerm(l.f.hour_ago).contains("_")) && (!paramContext.getTerm(l.f.minute_ago).equals("hour_ago"))) {
        return paramContext.getTerm(l.f.hour_ago);
      }
      return null;
    }
    if (((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() > 1L) && (Long.valueOf(paramLong2).longValue() <= 23L)) || ((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() <= 23L) && (Long.valueOf(paramLong3).longValue() >= 0L) && (Long.valueOf(paramLong3).longValue() <= 59L)) || ((Long.valueOf(paramLong1).longValue() == 0L) && (Long.valueOf(paramLong2).longValue() <= 23L) && (Long.valueOf(paramLong3).longValue() == 59L) && (Long.valueOf(paramLong4).longValue() >= 0L) && (Long.valueOf(paramLong4).longValue() <= 59L)))
    {
      if ((paramContext.getTerm(l.f.x_hours_ago) != null) && (!paramContext.getTerm(l.f.x_hours_ago).isEmpty()) && (!paramContext.getTerm(l.f.x_hours_ago).contains("_")) && (!paramContext.getTerm(l.f.x_hours_ago).equals("x_hours_ago"))) {
        return paramContext.getTerm(l.f.x_hours_ago).replace("%x%", String.valueOf(paramLong2));
      }
      return null;
    }
    return null;
  }
  
  public static String a(long paramLong, String paramString)
  {
    return a(paramLong, paramString, z);
  }
  
  public static String a(long paramLong, String paramString, Context paramContext)
  {
    return a(paramLong, paramString, z, paramContext);
  }
  
  public static String a(long paramLong, String paramString, Locale paramLocale)
  {
    String str2;
    if ((paramString != null) && (paramString.equals("MMMM d")))
    {
      str2 = ((SimpleDateFormat)SimpleDateFormat.getDateInstance(1, paramLocale)).toPattern();
      if (paramString.contains("de")) {
        str1 = "[^Mm]*[Yy]+[^Mm]*";
      }
    }
    for (String str1 = str2.replaceAll(str1, "");; str1 = paramString)
    {
      str2 = new SimpleDateFormat(str1, paramLocale).format(new Date(paramLong));
      str1 = str2;
      if (paramString != null)
      {
        str1 = str2;
        if (paramString.equals("MMMM d"))
        {
          str1 = str2;
          if (paramLocale.getLanguage().equalsIgnoreCase("AR"))
          {
            paramString = str2.substring(0, str2.indexOf(" "));
            str1 = str2.replace(paramString, new BigDecimal(paramString).toString());
          }
        }
      }
      return str1;
      str1 = "[^DdMm]*[Yy]+[^DdMm]*";
      break;
    }
  }
  
  public static String a(long paramLong, String paramString, Locale paramLocale, Context paramContext)
  {
    String str2;
    if ((paramString != null) && (paramString.equals("MMMM d")))
    {
      str2 = ((SimpleDateFormat)SimpleDateFormat.getDateInstance(1, paramLocale)).toPattern();
      if (paramString.contains("de")) {
        str1 = "[^Mm]*[Yy]+[^Mm]*";
      }
    }
    for (String str1 = str2.replaceAll(str1, "");; str1 = paramString)
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1, paramLocale);
      str2 = localSimpleDateFormat.format(new Date(paramLong));
      String str3 = localSimpleDateFormat.format(new Date(System.currentTimeMillis()));
      str1 = str2;
      if (paramString != null)
      {
        str1 = str2;
        if (paramString.equals("MMMM d"))
        {
          str1 = str2;
          if (paramLocale.getLanguage().equalsIgnoreCase("AR"))
          {
            paramString = str2.substring(0, str2.indexOf(" "));
            str1 = str2.replace(paramString, new BigDecimal(paramString).toString());
          }
        }
      }
      try
      {
        paramString = a(localSimpleDateFormat.parse(str1), localSimpleDateFormat.parse(str3), paramContext);
        if (paramString == null) {
          break label209;
        }
        return paramString;
      }
      catch (ParseException paramString)
      {
        paramString.printStackTrace();
      }
      str1 = "[^DdMm]*[Yy]+[^DdMm]*";
      break;
      label209:
      return new SimpleDateFormat("MMM d", paramLocale).format(new Date(paramLong));
    }
  }
  
  public static String a(long paramLong, Locale paramLocale)
  {
    return DateFormat.getDateInstance(2, paramLocale).format(new Date(paramLong));
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    MetaDataHelper localMetaDataHelper = MetaDataHelper.getInstance(paramContext);
    switch (paramInt)
    {
    case 2: 
    default: 
      return "";
    case 0: 
      return localMetaDataHelper.getTerm(paramContext.getString(l.f.earningCal_before));
    case 1: 
      return localMetaDataHelper.getTerm(paramContext.getString(l.f.earningCal_during));
    }
    return localMetaDataHelper.getTerm(paramContext.getString(l.f.earningCal_after));
  }
  
  public static String a(Cursor paramCursor, Context paramContext)
  {
    int i1 = 6;
    if (!paramCursor.moveToFirst()) {
      return "";
    }
    int i2 = paramCursor.getColumnIndex("article_time");
    StringBuilder localStringBuilder = new StringBuilder("UNION select ");
    paramContext = a.c;
    long l1;
    if (paramCursor.getCount() < 6)
    {
      i1 = paramCursor.getCount() - 1;
      paramContext = a.a;
      paramCursor.move(i1);
      l1 = paramCursor.getLong(i2);
      i2 = 0;
      label84:
      if (i2 >= paramCursor.getColumnCount()) {
        break label336;
      }
      if (!paramCursor.getColumnName(i2).equals("_id")) {
        break label159;
      }
      localStringBuilder.append("-999999").append(",");
    }
    for (;;)
    {
      i2 += 1;
      break label84;
      if (paramCursor.getCount() != 6) {
        break;
      }
      i1 = paramCursor.getCount() - 1;
      paramContext = a.b;
      break;
      label159:
      if (paramCursor.getColumnName(i2).equals("article_time")) {
        localStringBuilder.append(1L + l1 + 34L + 34L).append(",");
      } else if (paramCursor.getColumnName(i2).equals("article_title")) {
        localStringBuilder.append("\"commercial\"").append(",");
      } else if (paramCursor.getColumnName(i2).equals("item_oredr"))
      {
        if (paramContext == a.a) {
          localStringBuilder.append(i1 + 1).append(",");
        } else if (paramContext == a.b) {
          localStringBuilder.append(i1).append(",");
        } else {
          localStringBuilder.append(i1 - 1).append(",");
        }
      }
      else {
        localStringBuilder.append("\"\"").append(",");
      }
    }
    label336:
    return localStringBuilder.deleteCharAt(localStringBuilder.length() - 1).toString();
  }
  
  public static String a(Cursor paramCursor, Context paramContext, long paramLong, boolean paramBoolean)
  {
    int i1 = 7;
    if (!paramCursor.moveToFirst()) {
      return "";
    }
    Object localObject = a.c;
    if (paramCursor.getCount() < 6) {
      if ((paramCursor.getCount() == 6) && (paramLong == 37L) && (paramBoolean))
      {
        i1 = paramCursor.getCount() - 2;
        paramContext = a.a;
      }
    }
    int i2;
    for (;;)
    {
      i2 = paramCursor.getColumnIndex("last_updated_uts");
      localObject = new StringBuilder("UNION select ");
      paramCursor.move(i1);
      paramLong = 0L;
      try
      {
        long l1 = paramCursor.getLong(i2);
        paramLong = l1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          continue;
          if (paramCursor.getColumnName(i2).equals("last_updated_uts")) {
            ((StringBuilder)localObject).append(1L + paramLong + 34L + 34L).append(",");
          } else if (paramCursor.getColumnName(i2).equals("article_title")) {
            ((StringBuilder)localObject).append("\"commercial\"").append(",");
          } else if (paramCursor.getColumnName(i2).equals("item_oredr"))
          {
            if (paramContext == a.a) {
              ((StringBuilder)localObject).append(i1 + 1).append(",");
            } else if (paramContext == a.b) {
              ((StringBuilder)localObject).append(i1).append(",");
            } else {
              ((StringBuilder)localObject).append(i1 - 1).append(",");
            }
          }
          else {
            ((StringBuilder)localObject).append("\"\"").append(",");
          }
        }
      }
      i2 = 0;
      for (;;)
      {
        if (i2 >= paramCursor.getColumnCount()) {
          break label484;
        }
        if (!paramCursor.getColumnName(i2).equals("_id")) {
          break;
        }
        ((StringBuilder)localObject).append("-999999").append(",");
        i2 += 1;
      }
      i1 = paramCursor.getCount() - 1;
      break;
      if (paramCursor.getCount() == 6)
      {
        if ((paramLong == 37L) && (paramBoolean)) {}
        for (i1 = paramCursor.getCount() - 2;; i1 = paramCursor.getCount() - 1)
        {
          paramContext = a.b;
          break;
        }
      }
      if ((paramLong == 37L) && (paramBoolean))
      {
        i1 = 16;
        paramContext = (Context)localObject;
      }
      else
      {
        paramContext = (Context)localObject;
        if (paramCursor.getCount() <= 7)
        {
          i1 = paramCursor.getCount() - 1;
          paramContext = (Context)localObject;
        }
      }
    }
    label484:
    return ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1).toString();
  }
  
  public static String a(Cursor paramCursor, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (!paramCursor.moveToFirst()) {
      return "";
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramString1 = a(paramCursor, paramString1, paramString2, paramString3);
    while (paramCursor.moveToNext())
    {
      paramString2 = a(paramCursor.getLong(paramCursor.getColumnIndex(paramString3)), "dd");
      if (!localArrayList1.contains(paramString2))
      {
        localArrayList2.add(Long.valueOf(paramCursor.getLong(paramCursor.getColumnIndex(paramString3))));
        localArrayList1.add(paramString2);
      }
    }
    paramCursor = a(paramCursor, paramContext, paramString3, "", localArrayList2);
    return paramCursor + paramString1;
  }
  
  public static String a(Cursor paramCursor1, Context paramContext, String paramString1, String paramString2, String paramString3, Cursor paramCursor2, boolean paramBoolean)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramString1 = a(paramCursor1, paramString1, paramString2, paramString3);
    paramCursor2.moveToPosition(-1);
    int i1;
    while (paramCursor2.moveToNext())
    {
      long l1 = a(paramCursor2.getString(paramCursor2.getColumnIndex("holiday_start")));
      long l2 = a(paramCursor2.getString(paramCursor2.getColumnIndex("holiday_end")));
      while (l1 <= l2)
      {
        paramString2 = Calendar.getInstance();
        paramString2.setTimeInMillis(l1);
        i1 = paramString2.get(5);
        if (!localArrayList1.contains(Integer.valueOf(i1)))
        {
          localArrayList2.add(Long.valueOf(43200000L + l1));
          localArrayList1.add(Integer.valueOf(i1));
          if (paramBoolean) {
            return b(paramCursor1, paramContext, paramString3, "", localArrayList2) + paramString1;
          }
        }
        l1 = b(l1);
      }
    }
    paramCursor1.moveToPosition(-1);
    while (paramCursor1.moveToNext())
    {
      paramString2 = Calendar.getInstance();
      paramString2.setTimeInMillis(paramCursor1.getLong(paramCursor1.getColumnIndex(paramString3)));
      i1 = paramString2.get(5);
      if (!localArrayList1.contains(Integer.valueOf(i1)))
      {
        localArrayList2.add(Long.valueOf(paramCursor1.getLong(paramCursor1.getColumnIndex(paramString3))));
        localArrayList1.add(Integer.valueOf(i1));
      }
    }
    return b(paramCursor1, paramContext, paramString3, "", localArrayList2) + paramString1;
  }
  
  private static String a(Cursor paramCursor, Context paramContext, String paramString1, String paramString2, ArrayList<Long> paramArrayList)
  {
    StringBuilder localStringBuilder = new StringBuilder(" UNION select ");
    int i1 = 0;
    if (i1 < paramCursor.getColumnCount())
    {
      if (paramCursor.getColumnName(i1).equals(paramString1)) {
        localStringBuilder.append("%d").append(",");
      }
      for (;;)
      {
        i1 += 1;
        break;
        localStringBuilder.append("\"\"").append(",");
      }
    }
    paramCursor = localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    paramString1 = paramArrayList.iterator();
    while (paramString1.hasNext())
    {
      long l1 = ((Long)paramString1.next()).longValue();
      paramString2 = paramString2 + String.format(Locale.US, paramCursor.toString(), new Object[] { Long.valueOf(a(l1, paramContext)) });
    }
    return paramString2;
  }
  
  public static String a(Cursor paramCursor, String paramString, Context paramContext)
  {
    return a(a(paramCursor), paramString, z, paramContext);
  }
  
  public static String a(Cursor paramCursor, String paramString1, String paramString2, String paramString3)
  {
    if (!paramCursor.moveToFirst()) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(" UNION select ");
    System.currentTimeMillis();
    int i1 = 0;
    if (i1 < paramCursor.getColumnCount())
    {
      if (paramCursor.getColumnName(i1).equals(paramString3)) {
        localStringBuilder.append(System.currentTimeMillis()).append(",");
      }
      for (;;)
      {
        i1 += 1;
        break;
        if (paramCursor.getColumnName(i1).equals(paramString2)) {
          localStringBuilder.append("\"current_timestamp\"").append(",");
        } else if (paramCursor.getColumnName(i1).equals(paramString1)) {
          localStringBuilder.append("-100").append(",");
        } else {
          localStringBuilder.append("\"\"").append(",");
        }
      }
    }
    return localStringBuilder.deleteCharAt(localStringBuilder.length() - 1).toString();
  }
  
  public static String a(Iterable<? extends CharSequence> paramIterable, String paramString)
  {
    paramIterable = paramIterable.iterator();
    if (!paramIterable.hasNext()) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder((CharSequence)paramIterable.next());
    while (paramIterable.hasNext()) {
      localStringBuilder.append(paramString).append((CharSequence)paramIterable.next());
    }
    return localStringBuilder.toString();
  }
  
  public static String a(Date paramDate1, Date paramDate2, Context paramContext)
  {
    long l4 = paramDate2.getTime() - paramDate1.getTime();
    long l1 = 1000L * 60L;
    long l3 = 60L * l1;
    long l5 = l3 * 24L;
    long l2 = l4 / l5;
    l5 = l4 % l5;
    l4 = l5 / l3;
    l3 = l5 % l3;
    return a(l2, l4, l3 / l1, l3 % l1 / 1000L, paramContext);
  }
  
  public static String a(Locale paramLocale)
  {
    String str = paramLocale.getLanguage();
    if (str.equalsIgnoreCase("IW")) {
      paramLocale = "HE";
    }
    do
    {
      return paramLocale;
      if (str.equalsIgnoreCase("IN")) {
        return "ID";
      }
      paramLocale = str;
    } while (!str.equalsIgnoreCase("JI"));
    return "YI";
  }
  
  public static void a()
  {
    N = false;
    I = false;
    H = -1L;
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    try
    {
      paramActivity = paramActivity.findViewById(paramInt);
      if (paramActivity != null)
      {
        a(paramActivity);
        if ((paramActivity instanceof ViewGroup)) {
          a((ViewGroup)paramActivity);
        }
      }
      System.gc();
      return;
    }
    catch (Throwable paramActivity) {}
  }
  
  public static void a(Context paramContext, View paramView)
  {
    if ((paramView != null) && (paramContext != null))
    {
      d.a("focus", "view class:" + paramView.getClass().getName());
      paramView.requestFocus();
      ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 1);
    }
  }
  
  public static void a(View paramView)
  {
    try
    {
      paramView.setOnClickListener(null);
    }
    catch (Throwable localThrowable5)
    {
      try
      {
        paramView.setOnCreateContextMenuListener(null);
      }
      catch (Throwable localThrowable5)
      {
        try
        {
          paramView.setOnFocusChangeListener(null);
        }
        catch (Throwable localThrowable5)
        {
          try
          {
            paramView.setOnKeyListener(null);
          }
          catch (Throwable localThrowable5)
          {
            try
            {
              paramView.setOnLongClickListener(null);
            }
            catch (Throwable localThrowable5)
            {
              try
              {
                for (;;)
                {
                  paramView.setOnClickListener(null);
                  Object localObject = paramView.getBackground();
                  if (localObject != null) {
                    ((Drawable)localObject).setCallback(null);
                  }
                  if ((paramView instanceof ImageView))
                  {
                    localObject = (ImageView)paramView;
                    Drawable localDrawable = ((ImageView)localObject).getDrawable();
                    if (localDrawable != null) {
                      localDrawable.setCallback(null);
                    }
                    ((ImageView)localObject).setImageDrawable(null);
                    ((ImageView)localObject).setBackgroundDrawable(null);
                  }
                  if ((paramView instanceof WebView))
                  {
                    ((WebView)paramView).removeAllViews();
                    ((WebView)paramView).destroyDrawingCache();
                    ((WebView)paramView).destroy();
                  }
                  return;
                  localThrowable1 = localThrowable1;
                  continue;
                  localThrowable2 = localThrowable2;
                  continue;
                  localThrowable3 = localThrowable3;
                  continue;
                  localThrowable4 = localThrowable4;
                  continue;
                  localThrowable5 = localThrowable5;
                }
              }
              catch (Throwable localThrowable6)
              {
                for (;;) {}
              }
            }
          }
        }
      }
    }
  }
  
  public static void a(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    localValueAnimator.addUpdateListener(new r(paramInt1, paramView));
    if (paramInt3 >= 0) {
      localValueAnimator.setDuration(paramInt3);
    }
    localValueAnimator.start();
  }
  
  private static void a(ViewGroup paramViewGroup)
  {
    paramViewGroup.getChildCount();
    int i1 = 0;
    while (i1 < paramViewGroup.getChildCount())
    {
      View localView = paramViewGroup.getChildAt(i1);
      a(localView);
      if ((localView instanceof ViewGroup)) {
        a((ViewGroup)localView);
      }
      i1 += 1;
    }
    try
    {
      paramViewGroup.removeAllViews();
      return;
    }
    catch (Throwable paramViewGroup) {}
  }
  
  public static boolean a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean a(int paramInt, boolean paramBoolean, BaseInvestingApplication paramBaseInvestingApplication)
  {
    if (paramBaseInvestingApplication.getResources().getString(paramInt).equals("pref_is_paid")) {
      return Boolean.valueOf(paramBaseInvestingApplication.f.d(paramBaseInvestingApplication.getResources().getString(paramInt))).booleanValue();
    }
    return paramBaseInvestingApplication.M().getBoolean(paramBaseInvestingApplication.getResources().getString(paramInt), paramBoolean);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(BaseInvestingApplication paramBaseInvestingApplication)
  {
    boolean bool1 = false;
    try
    {
      if ((!paramBaseInvestingApplication.m().equals("en")) && (!paramBaseInvestingApplication.m().equals("he")) && (!paramBaseInvestingApplication.m().equals("ar")) && (!paramBaseInvestingApplication.m().equals("es")) && (!paramBaseInvestingApplication.m().equals("fr")) && (!paramBaseInvestingApplication.m().equals("zh")) && (!paramBaseInvestingApplication.m().equals("ru")) && (!paramBaseInvestingApplication.m().equals("de")) && (!paramBaseInvestingApplication.m().equals("it")) && (!paramBaseInvestingApplication.m().equals("tr")))
      {
        boolean bool2 = paramBaseInvestingApplication.m().equals("ai");
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (NullPointerException paramBaseInvestingApplication) {}
    return false;
  }
  
  public static float b(Context paramContext, float paramFloat)
  {
    return paramFloat / paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static int b(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static long b(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    localCalendar.set(5, Integer.valueOf(a(paramLong, "dd")).intValue() + 1);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    return localCalendar.getTimeInMillis();
  }
  
  public static long b(String paramString)
  {
    paramString = Pattern.compile("[0-9]+$").matcher(paramString);
    long l1 = -1L;
    if (paramString.find()) {
      l1 = Long.valueOf(paramString.group()).longValue();
    }
    return l1;
  }
  
  public static String b()
  {
    double d1 = Math.random();
    if (d1 < 0.09D) {
      return "ad_ex" + String.valueOf((int)Math.floor(d1 * 100.0D));
    }
    if (d1 < 0.1D) {
      return "ad_bc";
    }
    return "ad_opt";
  }
  
  public static String b(Cursor paramCursor, Context paramContext)
  {
    if (DateUtils.isToday(a(paramCursor))) {
      return a(a(paramCursor), "HH:mm", z, paramContext);
    }
    return a(a(paramCursor), "MM-dd-yyyy HH:mm", z, paramContext);
  }
  
  public static String b(Cursor paramCursor, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (!paramCursor.moveToFirst()) {
      return "";
    }
    paramContext = "";
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    paramString1 = a(paramCursor, paramString1, paramString2, paramString3);
    while (paramCursor.moveToNext())
    {
      paramString2 = "" + paramCursor.getInt(paramCursor.getColumnIndex(paramString3));
      if (!localArrayList2.contains(paramString2))
      {
        localArrayList1.add(Integer.valueOf(paramCursor.getInt(paramCursor.getColumnIndex(paramString3))));
        localArrayList2.add(paramString2);
      }
    }
    paramString2 = new StringBuilder(" UNION select ");
    int i1 = 0;
    if (i1 < paramCursor.getColumnCount())
    {
      if (paramCursor.getColumnName(i1).equals(paramString3)) {
        paramString2.append("%d").append(",");
      }
      for (;;)
      {
        i1 += 1;
        break;
        paramString2.append("\"\"").append(",");
      }
    }
    paramString2 = paramString2.deleteCharAt(paramString2.length() - 1);
    paramString3 = localArrayList1.iterator();
    long l1;
    for (paramCursor = paramContext; paramString3.hasNext(); paramCursor = paramCursor + String.format(paramString2.toString(), new Object[] { Long.valueOf(l1) })) {
      l1 = ((Integer)paramString3.next()).intValue();
    }
    return paramCursor + paramString1;
  }
  
  private static String b(Cursor paramCursor, Context paramContext, String paramString1, String paramString2, ArrayList<Long> paramArrayList)
  {
    return a(paramCursor, paramContext, paramString1, paramString2, paramArrayList);
  }
  
  public static void b(int paramInt, boolean paramBoolean, BaseInvestingApplication paramBaseInvestingApplication)
  {
    if (paramBaseInvestingApplication.getResources().getString(paramInt).equals("pref_is_paid"))
    {
      paramBaseInvestingApplication.f.a(paramBaseInvestingApplication.getResources().getString(paramInt), String.valueOf(paramBoolean));
      return;
    }
    paramBaseInvestingApplication.M().edit().putBoolean(paramBaseInvestingApplication.getResources().getString(paramInt), paramBoolean).commit();
  }
  
  public static void b(Context paramContext, View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 2);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    int i1 = paramContext.getContentResolver().delete(InvestingContract.InstrumentCommentsDict.CONTENT_URI, "_id IN (SELECT _id FROM comments WHERE content_id= ?  ORDER BY comment_date DESC LIMIT -1 OFFSET 15 )", new String[] { String.valueOf(paramString) });
    d.a("DIMA", "i" + i1);
  }
  
  public static boolean b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean b(BaseInvestingApplication paramBaseInvestingApplication)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramBaseInvestingApplication.getApplicationContext()).getString("numericFormat", "us").contains("eu");
  }
  
  public static void c(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(e(paramContext))));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(d(paramContext))));
        return;
      }
      catch (Exception localException)
      {
        Toast.makeText(paramContext, "Google play is not available", 1).show();
      }
    }
  }
  
  public static void c(Context paramContext, String paramString)
  {
    int i1 = paramContext.getContentResolver().delete(InvestingContract.InstrumentCommentsDict.CONTENT_URI, "content_id=?", new String[] { String.valueOf(paramString) });
    i = 0L;
    d.a("DIMA", "i:" + i1);
  }
  
  public static boolean c(String paramString)
  {
    return paramString.matches("-?\\d+(\\.\\d+)?");
  }
  
  public static String d(Context paramContext)
  {
    int i1 = l.f.link_googleplay_web;
    if (Build.MANUFACTURER.equals("Amazon")) {
      i1 = l.f.link_amazon_web;
    }
    return paramContext.getString(i1);
  }
  
  public static boolean d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Patterns.EMAIL_ADDRESS.matcher(paramString).matches();
  }
  
  public static String e(Context paramContext)
  {
    int i1 = l.f.link_googleplay_app;
    if (Build.MANUFACTURER.equals("Amazon")) {
      i1 = l.f.link_amazon_store_app;
    }
    return paramContext.getString(i1);
  }
  
  public static boolean e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.matches("[\\p{L}]+", paramString);
  }
  
  /* Error */
  public static boolean f(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 1083	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: getstatic 1195	com/fusionmedia/investing_base/controller/content_provider/InvestingContract$UserQuotes:CONTENT_URI	Landroid/net/Uri;
    //   7: iconst_1
    //   8: anewarray 422	java/lang/String
    //   11: dup
    //   12: iconst_0
    //   13: ldc_w 1197
    //   16: aastore
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: invokevirtual 431	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   23: astore_0
    //   24: aload_0
    //   25: invokeinterface 616 1 0
    //   30: istore_1
    //   31: iload_1
    //   32: ifle +17 -> 49
    //   35: iconst_1
    //   36: istore_2
    //   37: aload_0
    //   38: ifnull +9 -> 47
    //   41: aload_0
    //   42: invokeinterface 450 1 0
    //   47: iload_2
    //   48: ireturn
    //   49: iconst_0
    //   50: istore_2
    //   51: goto -14 -> 37
    //   54: astore_3
    //   55: aconst_null
    //   56: astore_0
    //   57: aload_0
    //   58: ifnull +9 -> 67
    //   61: aload_0
    //   62: invokeinterface 450 1 0
    //   67: aload_3
    //   68: athrow
    //   69: astore_3
    //   70: goto -13 -> 57
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	paramContext	Context
    //   30	2	1	i1	int
    //   36	15	2	bool	boolean
    //   54	14	3	localObject1	Object
    //   69	1	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	24	54	finally
    //   24	31	69	finally
  }
  
  public static boolean f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.matches("^(?=(?:.*[a-zA-Z]){2})(?=(?:.*\\d){2}).{4,15}$", paramString);
  }
  
  public static int g(Context paramContext)
  {
    try
    {
      int i1 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static final String g(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      StringBuilder localStringBuilder = new StringBuilder();
      int i2 = localObject.length;
      int i1 = 0;
      while (i1 < i2)
      {
        for (paramString = Integer.toHexString(localObject[i1] & 0xFF); paramString.length() < 2; paramString = "0" + paramString) {}
        localStringBuilder.append(paramString);
        i1 += 1;
      }
      paramString = localStringBuilder.toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "0";
  }
  
  public static String h(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i1 = 0;
      while (i1 < paramString.length)
      {
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i1] & 0xFF));
        i1 += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static boolean i(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if (paramContext.getResources().getBoolean(l.a.isTabletWidth))
      {
        boolean bool3 = paramContext.getResources().getBoolean(l.a.isTabletHight);
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (Resources.NotFoundException paramContext) {}
    return false;
  }
  
  public static boolean i(String paramString)
  {
    try
    {
      Long.parseLong(paramString);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int j(Context paramContext)
  {
    paramContext = paramContext.getResources();
    int i1 = paramContext.getIdentifier("status_bar_height", "dimen", "android");
    if (i1 > 0) {
      return paramContext.getDimensionPixelSize(i1);
    }
    if (Build.VERSION.SDK_INT >= 23) {}
    for (i1 = 24;; i1 = 25) {
      return (int)Math.ceil(i1 * paramContext.getDisplayMetrics().density);
    }
  }
  
  public static boolean j(String paramString)
  {
    try
    {
      Integer.parseInt(paramString);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static void k(Context paramContext)
  {
    l(paramContext);
    m(paramContext);
  }
  
  public static void l(Context paramContext)
  {
    Log.e("0101", "clearNews: ");
    int i1 = paramContext.getContentResolver().delete(InvestingContract.ScreenDataDict.CONTENT_URI, "app_mmt_ID = ? ", new String[] { String.valueOf(2) });
    d.a("0101", "number rows deleted:" + i1);
  }
  
  public static void m(Context paramContext)
  {
    Log.e("0101", "clearAnalysis: ");
    int i1 = paramContext.getContentResolver().delete(InvestingContract.ScreenDataDict.CONTENT_URI, "app_mmt_ID = ? ", new String[] { String.valueOf(4) });
    d.a("0101", "number rows deleted:" + i1);
  }
  
  static enum a
  {
    private a() {}
  }
}
